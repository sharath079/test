package com.patientapp.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.TasksUtil;
import com.patientapp.util.Tasks;
import com.patientapp.bean.TaskScheduleInfo;
import com.patientapp.bean.TaskSentInfo;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.SettingsUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.patientapp.util.layout.LayoutParser;
import com.patientapp.util.layout.LayoutParserUtil;
public class Tasks
{
	//Replace Database name
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Tasks.class);
	static
	{
		initHTMLLayoutWhereClauseMetadata();	
	}	
	public static void main(String args[])throws Exception
	{		
		startScheduler();
	}
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static int NOTIFICATION_SCHEDULAR_SECONDS = 60;//1 Day = 86400 Seconds
    public static void startScheduler()
    {
        final Runnable runnableScheduler = new Runnable()
        {
            public void run()
            {
            	processTasks();
            }		
        };
        final ScheduledFuture<?> schedulerHandle = scheduler.scheduleAtFixedRate(runnableScheduler, 1, NOTIFICATION_SCHEDULAR_SECONDS, TimeUnit.SECONDS);
    }	
    public static void processTasks() 
    {
    	try
		{     		
    		processTask();
        	processTaskSchedule(); 
		}
    	catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
    }
	public static void processTask()throws Exception
	{
		Session organisationSession = null;
		Transaction tx = null;
		try
		{							
			organisationSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName());
			String selectQuery = "select"
					+ " taskInfoId, "					
					+ " taskType, "					
					+ " taskStartDate, "
					+ " targetEntityQuery, "
					+ " targetUserIdColumnAlias, "
					+ " targetEntityIdColumnAlias, "
					+ " firstRunType, "					
					+ " enableEmailNotification, "
					+ " enableSmsNotification, "
					+ " enableInAppNotification, "
					+ " emailNotificationLayout, "
					+ " smsNotificationLayout "
					+ " from "
					+ " TaskInfo "
					+ " where 1=1 "
					+ " and isActive = 'Yes' ";
			String OrderByClause = " ";
			Query query = organisationSession.createSQLQuery(selectQuery + OrderByClause);
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			while (iterator.hasNext())
			{
				Object[] resultsRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultsRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultsRowColumnsList = new Object[1];
					resultsRowColumnsList[0] = (Object) iterator.next();
				}
				tx = organisationSession.beginTransaction();
				int status = processTaskInfo(resultsRowColumnsList, organisationSession);
				if (status == 0)
				{
					if (tx != null)
						tx.rollback();
				}
				else
				{
					tx.commit();
				}
			}					
			return;			
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification info could not be processed.");
		}	
		finally
		{
			organisationSession.close(); 	
		}
		return;
	}
	private static int processTaskInfo(Object[] resultRowColumnsList, Session organisationSession)throws Exception
	{
		int taskInfoId = (Integer)resultRowColumnsList[0];				
		String taskType = String.valueOf(resultRowColumnsList[1]);
		String taskStartDateString = String.valueOf(resultRowColumnsList[2]);
		Date taskStartDate = null;
		if(!taskStartDateString.equalsIgnoreCase("null"))
		{
			taskStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskStartDateString);	
		}				  				
		String targetEntityQuery = String.valueOf(resultRowColumnsList[3]);
		String queryCode = targetEntityQuery;
		String targetUserIdColumnAlias = String.valueOf(resultRowColumnsList[4]);;
		String targetEntityIdColumnAlias = String.valueOf(resultRowColumnsList[5]);  
		String firstRunType = String.valueOf(resultRowColumnsList[6]);
		String enableEmailNotification = String.valueOf(resultRowColumnsList[7]);
		String enableSmsNotification = String.valueOf(resultRowColumnsList[8]);
		String enableInAppNotification = String.valueOf(resultRowColumnsList[9]);
		//Execute Target User Query
		JsonArray WhereClausesList =  new JsonArray();
		JsonArray queryParameterNameAndValueslist =  new JsonArray();
        JsonObject queryOutputObj = LayoutParserUtil.getQueryOutputNew(organisationSession, queryCode,  WhereClausesList, queryParameterNameAndValueslist);
        if (queryOutputObj.get("success").getAsInt() != 1)
        {
        	CommonUtil.writeTolog(queryOutputObj.get("alert").getAsString());            	
            return 0;
        }
        JsonArray queryOutputRecordsList = queryOutputObj.get("queryOutputRecordsList").getAsJsonArray();
        for(int i = 0;i<queryOutputRecordsList.size();i++)
        {
        	int targetUserId = -1;
        	int targetEntityId = -1;
        	JsonObject queryOutputRecordInfoObj = queryOutputRecordsList.get(i).getAsJsonObject();	            	
        	JsonArray outputRecordColumnsList = queryOutputRecordInfoObj.get("outputRecordColumnsList").getAsJsonArray();		            	
        	for(int j=0; j< outputRecordColumnsList.size();j++)
        	{
        		JsonObject outputRecordColumnInfoObj = outputRecordColumnsList.get(j).getAsJsonObject();
        		String columnAlias = outputRecordColumnInfoObj.get("columnAlias").getAsString();
        		if(columnAlias.equalsIgnoreCase(targetUserIdColumnAlias))
        		{
        			targetUserId = Integer.parseInt(outputRecordColumnInfoObj.get("columnValue").getAsString());		            			
        		}
        		if(columnAlias.equalsIgnoreCase(targetEntityIdColumnAlias))
        		{
        			String targetEntityIdString  = outputRecordColumnInfoObj.get("columnValue").getAsString();
        			if(targetEntityIdString.trim().length()>0)
        			{
        				targetEntityId = Integer.parseInt(outputRecordColumnInfoObj.get("columnValue").getAsString());	
        			}
        		}	            		
        	}			            	
        	if(targetUserId <= 0)
        	{
        		CommonUtil.writeTolog("Target user id is not existed in query executed output record");            	
        		return 0;
        	}        	
        	if(firstRunType.equalsIgnoreCase("Manual"))
        	{
        		int status = processManualTask(organisationSession, taskInfoId, outputRecordColumnsList, targetUserId, targetEntityId, enableEmailNotification, enableSmsNotification, enableInAppNotification);
        		if(status==0)				
    				return 0;
        	}
        	else
        	{
            	//Calculating task start date
        		taskStartDate = getTaskStartDateBasedOnFirstRunType(organisationSession, taskInfoId, firstRunType, outputRecordColumnsList);
        		int status = addTaskScheduleInfoBasedOnNotificationMedium(organisationSession,taskInfoId, targetUserId, targetEntityId, enableEmailNotification, enableSmsNotification, enableInAppNotification, taskStartDate);
        		if(status==0)				
    				return 0;
	        }
        }
		return 1;
	}
	private static int addTaskScheduleInfoBasedOnNotificationMedium(Session organisationSession, int taskInfoId, int targetUserId, int targetEntityId, String enableEmailNotification, String enableSmsNotification, String enableInAppNotification, Date taskStartDate)throws Exception
	{
		if (enableEmailNotification.equalsIgnoreCase("YES"))
		{
			int status = addTaskScheduleInfoIfNotExist(organisationSession, taskInfoId, targetUserId, targetEntityId, "EMAIL", taskStartDate);
			if(status==0)				
				return 0;				
		}
		if (enableSmsNotification.equalsIgnoreCase("YES"))
		{
			int status = addTaskScheduleInfoIfNotExist(organisationSession, taskInfoId, targetUserId, targetEntityId, "SMS", taskStartDate);
			if(status==0)				
				return 0;	
		}
		if (enableInAppNotification.equalsIgnoreCase("YES"))
		{				
			int status = addTaskScheduleInfoIfNotExist(organisationSession, taskInfoId, targetUserId, targetEntityId, "IN_APP", taskStartDate);
			if(status==0)				
				return 0;	
		}
		return 1;
	}
	private static int processManualTask(Session organisationSession, int taskInfoId, JsonArray outputRecordColumnsList, int targetUserId, int targetEntityId, String enableEmailNotification, String enableSmsNotification, String enableInAppNotification)throws Exception
	{
		JsonObject taskInfoReturnedObj = getTaskInfo(organisationSession, taskInfoId);
		if(taskInfoReturnedObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(taskInfoReturnedObj.get("alert").getAsString());
			return 0;
		}
		JsonObject taskInfoObj = taskInfoReturnedObj.get("taskObj").getAsJsonObject();
		String dateColumnAlias = taskInfoObj.get("dateColumnAlias").getAsString();
		JsonObject taskExecutionInfoReturnObj = getTaskExecutionInfo(organisationSession, taskInfoId);        		
		if(taskExecutionInfoReturnObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(taskExecutionInfoReturnObj.get("alert").getAsString());
			return 0;
		}
		JsonArray taskExecutionsList = taskExecutionInfoReturnObj.get("taskExecutionsList").getAsJsonArray();				
		if (enableEmailNotification.equalsIgnoreCase("YES"))
		{
			int status = addTaskScheduleInfoForManualTask(organisationSession, taskInfoId, targetUserId, targetEntityId, "EMAIL", taskExecutionsList, dateColumnAlias, outputRecordColumnsList);
			if(status==0)				
				return 0;				
		}
		if (enableSmsNotification.equalsIgnoreCase("YES"))
		{
			int status = addTaskScheduleInfoForManualTask(organisationSession, taskInfoId, targetUserId, targetEntityId, "SMS", taskExecutionsList, dateColumnAlias, outputRecordColumnsList);			
			if(status==0)				
				return 0;	
		}
		if (enableInAppNotification.equalsIgnoreCase("YES"))
		{	
			int status = addTaskScheduleInfoForManualTask(organisationSession, taskInfoId, targetUserId, targetEntityId, "IN_APP", taskExecutionsList, dateColumnAlias, outputRecordColumnsList);			
			if(status==0)				
				return 0;	
		}			
		return 1;
	}
	private static int addTaskScheduleInfoForManualTask(Session organisationSession, int taskInfoId, int targetUserId, int targetEntityId, String notificationMedium, JsonArray taskExecutionsList, String dateColumnAlias, JsonArray outputRecordColumnsList)throws Exception
	{
		JsonObject dataObject = checkTaskUserIdInScheduleInfoOrNot(organisationSession, taskInfoId, targetUserId, targetEntityId, notificationMedium);
    	if(dataObject.get("success").getAsInt()!=1)
    	{
    		CommonUtil.writeTolog(dataObject.get("alert").getAsString());            		
    		return 0;
    	}
    	int recordsCount = dataObject.get("recordsCount").getAsInt();
    	if(recordsCount > 0)
    	{
    		return -1;
    	}		 		
		for (int i = 0; i < taskExecutionsList.size(); i++)
		{
			JsonObject taskExectionInfoObj = taskExecutionsList.get(i).getAsJsonObject();
			String taskTimeCalculationType = taskExectionInfoObj.get("taskTimeCalculationType").getAsString();  
			Date taskStartDate =  null;			
			if(taskTimeCalculationType.equalsIgnoreCase("UseRelativeDate"))
			{    		    				
				taskStartDate = getTaskStartDateForUseRelativeDate(organisationSession, taskExectionInfoObj, dateColumnAlias,  outputRecordColumnsList, taskTimeCalculationType); 
			}
			if(taskTimeCalculationType.equalsIgnoreCase("Immediate"))
	    	{
	    		taskStartDate = new Date();
	    	}				
			JsonObject emailTaskScheduleInfoObj = addTaskScheduleInfo(organisationSession, taskInfoId, targetEntityId, targetUserId, notificationMedium, taskStartDate);
			if (emailTaskScheduleInfoObj.get("success").getAsInt() != 1)
			{
				CommonUtil.writeTolog(emailTaskScheduleInfoObj.get("alert").getAsString());
				return 0;
			}				
		}
		return 1;
	}
	private static int addTaskScheduleInfoIfNotExist(Session organisationSession, int taskInfoId, int targetUserId, int targetEntityId, String notificationMedium, Date taskStartDate) throws Exception
	{
		JsonObject dataObject = checkTaskUserIdInScheduleInfoOrNot(organisationSession, taskInfoId, targetUserId, targetEntityId, notificationMedium);
    	if(dataObject.get("success").getAsInt()!=1)
    	{
    		CommonUtil.writeTolog(dataObject.get("alert").getAsString());            		
    		return 0;
    	}
    	int recordsCount = dataObject.get("recordsCount").getAsInt();
    	if(recordsCount > 0)
    	{
    		return -1;
    	}		            	
		JsonObject emailTaskScheduleInfoObj = addTaskScheduleInfo(organisationSession, taskInfoId, targetEntityId, targetUserId, notificationMedium, taskStartDate);
		if (emailTaskScheduleInfoObj.get("success").getAsInt() != 1)
		{
			CommonUtil.writeTolog(emailTaskScheduleInfoObj.get("alert").getAsString());
			return 0;
		}
		return 1;
	}
	public static void processTaskSchedule()
	{	
		Session organisationSession = null;
		Transaction tx = null;
		try
		{									
			organisationSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName());			
			String selectQuery = "select"					
					+ " taskInfoId, "
					+ " targetUserId, "
					+ " targetEntityId, "					
					+ " notificationMedium, "					
					+ " nextNotificationTime "					
					+ " from "
					+ " TaskScheduleInfo "
					+ " where 1=1 "
					+ " and timestamp(nextNotificationTime) <= timestamp(DATE_FORMAT(NOW(), '%Y-%m-%d %k:%i:%s')) ";
			Query query = organisationSession.createSQLQuery(selectQuery);
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			while (iterator.hasNext())
			{
				Object[] resultRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultRowColumnsList = new Object[1];
					resultRowColumnsList[0] = (Object) iterator.next();
				}
				tx = organisationSession.beginTransaction();			        	
				int status  = processTaskScheduleInfo(resultRowColumnsList, organisationSession);					
				if(status == 0)
				{
					if (tx!=null) tx.rollback();
				}
				else
				{
					tx.commit();
				}
			}				
			return;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification could not be sent.");
		}	
		finally
		{
			organisationSession.close();
		}
	}
    private static int processTaskScheduleInfo(Object[] resultRowColumnsList, Session organisationSession)throws Exception
	{
		int taskInfoId = (Integer)resultRowColumnsList[0];								
		int targetUserId = (Integer)resultRowColumnsList[1];
		int targetEntityId = (Integer)resultRowColumnsList[2];				
		String notificationMedium = String.valueOf(resultRowColumnsList[3]);				
		String nextNotificationTimeText = String.valueOf(resultRowColumnsList[4]);		
		Date nextNotificationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nextNotificationTimeText);
		JsonObject taskInfoReturnedObj = getTaskInfo(organisationSession, taskInfoId);
		if(taskInfoReturnedObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(taskInfoReturnedObj.get("alert").getAsString());
			return 0;
		}
		JsonObject taskInfoObj = taskInfoReturnedObj.get("taskObj").getAsJsonObject();								
		String apiName = taskInfoObj.get("apiName").getAsString();
		if(notificationMedium.equalsIgnoreCase("API"))
		{
			int status =  TasksUtil.executeTaskAPI(organisationSession, apiName, taskInfoObj, taskInfoId,  targetUserId, targetEntityId, notificationMedium, nextNotificationTime);
			if(status == 0)
			{
				return 0;				
			}			
		}		
		else
		{
			return sendNotification(organisationSession, taskInfoObj, taskInfoId, targetUserId, targetEntityId, notificationMedium, nextNotificationTime);	
		}
		return 1;						
	}
	public static int  sendNotification(Session organisationSession, JsonObject taskInfoObj, int taskInfoId, int targetUserId, int targetEntityId, String notificationMedium, Date nextNotificationTime)throws Exception
	{
		String targetEntityQuery = taskInfoObj.get("targetEntityQuery").getAsString();
		String targetUserIdColumnAlias = taskInfoObj.get("targetUserIdColumnAlias").getAsString();
		String targetEntityIdColumnAlias = taskInfoObj.get("targetEntityIdColumnAlias").getAsString();	
		String emailNotifactionLayout = taskInfoObj.get("emailNotificationLayout").getAsString();
		String smsNotifactionLayout = taskInfoObj.get("smsNotificationLayout").getAsString();
		String taskName = taskInfoObj.get("taskName").getAsString();
		String apiName = taskInfoObj.get("apiName").getAsString();
		String emailColumnAlias = taskInfoObj.get("emailColumnAlias").getAsString();
		String smsColumnAlias = taskInfoObj.get("smsColumnAlias").getAsString();				
		int taskFrequency = taskInfoObj.get("taskFrequency").getAsInt();
		String isRecurring = taskInfoObj.get("isRecurring").getAsString();
		String taskFrequencyUnit = taskInfoObj.get("taskFrequencyUnit").getAsString();
		String enableEmailNotification = taskInfoObj.get("enableEmailNotification").getAsString();
		String enableSmsNotification = taskInfoObj.get("enableSmsNotification").getAsString();
		String enableInAppNotification = taskInfoObj.get("enableInAppNotification").getAsString();
		JsonArray taskLayoutParametersList = taskInfoObj.get("taskLayoutParametersList").getAsJsonArray();
		JsonArray emailAttachmentLayoutsList = taskInfoObj.get("emailAttachmentLayoutsList").getAsJsonArray();
		JsonArray notificationLayoutParameterNameAndValuesList = new JsonArray();
		JsonObject parameterAndValuesListInfoObj = getNotificationLayoutParameterNameAndValuesList(taskInfoObj, taskLayoutParametersList, nextNotificationTime, targetUserId, targetEntityId);
		if(parameterAndValuesListInfoObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(parameterAndValuesListInfoObj.get("alert").getAsString());
			return 0;
		}
		notificationLayoutParameterNameAndValuesList = parameterAndValuesListInfoObj.get("notificationLayoutParameterNameAndValuesList").getAsJsonArray();				
		JsonObject userInfoObj  = getTargetUserInfoFromTargetUsersQuery(organisationSession, targetEntityQuery, targetUserIdColumnAlias,  targetUserId, targetEntityIdColumnAlias, targetEntityId);
		if(userInfoObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(userInfoObj.get("alert").getAsString());
			return 0;
		}
		JsonObject userInfo = userInfoObj.get("userInfoObj").getAsJsonObject();
		String emailId =  "";
		if(userInfo.has(emailColumnAlias))
		{
			emailId =  userInfo.get(emailColumnAlias).getAsString();	
		}
		String phoneNumber =  "";
		if(userInfo.has(smsColumnAlias))
		{
			phoneNumber =  userInfo.get(smsColumnAlias).getAsString();
		}
		String layoutInfoObjText = "";
		int notificationSentStatus = 0;
		if(enableEmailNotification.equalsIgnoreCase("YES") && notificationMedium.equalsIgnoreCase("EMAIL"))
		{
			if(emailId.length()>0)
			{
				boolean isValidEmail = isValidEmail(emailId);
				if(isValidEmail==false)
				{
					return 1;
				}
				String subject = taskName;
				String emailSubject = taskInfoObj.get("emailSubject").getAsString();
				if(!emailSubject.equals("null"))
				{
					if(emailSubject.trim().length()>0)
					{						
						JsonObject emailSubjectDataFieldTokensQueryInfoObj = new JsonObject();
						int status = LayoutParser.prepareDataFieldTokensQueryInfoFromText(emailSubject, emailNotifactionLayout, emailSubjectDataFieldTokensQueryInfoObj);
						if(status == 0)
						{
							return 0;
						}
						JsonArray pageQueriesList = new JsonArray();
						LayoutParser.getLayoutQueriesListFromMetadata(emailNotifactionLayout, pageQueriesList);
						if(pageQueriesList.size() == 0)
						{
							String searchParameterListText = "";			
							JsonObject layoutFormatJsonObj = LayoutParser.getJsonFromHTML(emailNotifactionLayout, searchParameterListText);
							pageQueriesList = layoutFormatJsonObj.get("layoutQueriesList").getAsJsonArray();
						}
						subject = LayoutParserUtil.getDataFieldTokensOutputText(organisationSession, emailSubject, emailSubjectDataFieldTokensQueryInfoObj, emailNotifactionLayout, notificationLayoutParameterNameAndValuesList, pageQueriesList);
					}
				}
				notificationSentStatus = sendEmailNotification(organisationSession, emailNotifactionLayout, notificationLayoutParameterNameAndValuesList, emailId, subject, emailAttachmentLayoutsList, isRecurring, taskInfoId, targetEntityId, targetUserId, notificationMedium, taskFrequency, taskFrequencyUnit);							
			}
		}
		if(enableSmsNotification.equalsIgnoreCase("YES")  && notificationMedium.equalsIgnoreCase("SMS"))
		{
			if(phoneNumber.length()>0)
			{					
				String smsText = taskInfoObj.get("sMSText").getAsString();				
				JsonObject smsTextDataFieldTokensQueryInfoObj = new JsonObject();
				int status = LayoutParser.prepareDataFieldTokensQueryInfoFromText(smsText, smsNotifactionLayout, smsTextDataFieldTokensQueryInfoObj);
				if(status == 0)
				{
					return 0;
				}
				JsonArray pageQueriesList = new JsonArray();
				LayoutParser.getLayoutQueriesListFromMetadata(smsNotifactionLayout, pageQueriesList);
				String smsOutputText = LayoutParserUtil.getDataFieldTokensOutputText(organisationSession, smsText, smsTextDataFieldTokensQueryInfoObj, smsNotifactionLayout, notificationLayoutParameterNameAndValuesList, pageQueriesList);
				String[] phoneNumbers = new String[1];
				phoneNumbers[0] = phoneNumber;
				String smsMessage = smsOutputText;
				if(smsMessage.length()==0)
				{
					smsMessage = "no data found";
				}
				JsonObject dataObject = CommonUtil.sendSMS(null, phoneNumbers, smsMessage);
				if (dataObject.get("success").getAsInt() != 1)
				{
					CommonUtil.writeTolog(dataObject.get("alert").getAsString());
					return 0;	
				}	
				notificationSentStatus = 1;
			}																		            												
		}
		if(enableInAppNotification.equalsIgnoreCase("YES")  && notificationMedium.equalsIgnoreCase("IN_APP"))
		{			
			JsonObject layoutInfoObj = new JsonObject();
			layoutInfoObj.addProperty("layoutName", emailNotifactionLayout);					
			layoutInfoObj.add("notificationLayoutParameterNameAndValuesList", notificationLayoutParameterNameAndValuesList);
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();													
			layoutInfoObjText = gson.toJson(layoutInfoObj);
			notificationSentStatus = 1;
		}
		if(notificationSentStatus == 0)
		{
			return 0;
		}
		Date tasksentTime = new Date();
		JsonObject tasksentAddedInfoObj = addTaskSentInfo(organisationSession, taskInfoId,  targetEntityId, targetUserId, notificationMedium, tasksentTime, layoutInfoObjText);
		if(tasksentAddedInfoObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(tasksentAddedInfoObj.get("alert").getAsString());
			return 0;	
		}
		if(isRecurring.equalsIgnoreCase("NO"))
		{
			tasksentTime =  null;
		}
		JsonObject taskNextNotificationTimeUpdationInfoObj = updateNextNotificationTime(organisationSession, notificationMedium, taskInfoId, targetEntityId, targetUserId, taskFrequency, taskFrequencyUnit, tasksentTime); 
		if (taskNextNotificationTimeUpdationInfoObj.get("success").getAsInt() != 1)
		{
			CommonUtil.writeTolog(taskNextNotificationTimeUpdationInfoObj.get("alert").getAsString());
			return 0;	
		}		
		return 1;
	}
	private static Date getTaskStartDateBasedOnFirstRunType(Session organisationSession, int taskInfoId, String firstRunType, JsonArray outputRecordColumnsList)
	{
    	Date taskStartDate = null;
    	try
		{
        	if(firstRunType.equalsIgnoreCase("Immediate"))
        	{
        		taskStartDate = new Date();
        	}
        	if(firstRunType.equalsIgnoreCase("SpecifyDate"))
        	{
        		JsonObject taskInfoReturnedObj = getTaskInfo(organisationSession, taskInfoId);
				if(taskInfoReturnedObj.get("success").getAsInt()!=1)
				{
					CommonUtil.writeTolog(taskInfoReturnedObj.get("alert").getAsString());
					return null;
				}
				JsonObject taskInfoObj = taskInfoReturnedObj.get("taskObj").getAsJsonObject();
				String taskStartDateString = taskInfoObj.get("taskStartDate").getAsString();
			    taskStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskStartDateString);  		    										
        	}        	
        	if(firstRunType.equalsIgnoreCase("UseRelativeDate"))
        	{
        		JsonObject taskInfoReturnedObj = getTaskInfo(organisationSession, taskInfoId);
				if(taskInfoReturnedObj.get("success").getAsInt()!=1)
				{
					CommonUtil.writeTolog(taskInfoReturnedObj.get("alert").getAsString());
					return null;
				}
				JsonObject taskInfoObj = taskInfoReturnedObj.get("taskObj").getAsJsonObject();
				String dateColumnAlias = taskInfoObj.get("dateColumnAlias").getAsString();
				taskStartDate = getTaskStartDateForUseRelativeDate(organisationSession, taskInfoObj, dateColumnAlias,  outputRecordColumnsList, firstRunType);        		
        	}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	return taskStartDate;
	}
	private static Date getTaskStartDateForUseRelativeDate(Session organisationSession, JsonObject taskInfoObj, String dateColumnAlias,  JsonArray outputRecordColumnsList, String firstRunType)throws Exception
	{
		Date taskStartDate = null;				
		String firstRunDateCalculationMethod = taskInfoObj.get("firstRunDateCalculationMethod").getAsString();    			
		String entityDateString = "";
		for(int j=0; j< outputRecordColumnsList.size();j++)
    	{
    		JsonObject outputRecordColumnInfoObj = outputRecordColumnsList.get(j).getAsJsonObject();
    		String columnAlias = outputRecordColumnInfoObj.get("columnAlias").getAsString();
    		if(columnAlias.equalsIgnoreCase(dateColumnAlias))
    		{
    			entityDateString = outputRecordColumnInfoObj.get("columnValue").getAsString();		            			
    		}            		
    	}		  
	    Date entityDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entityDateString);  		    				
		taskStartDate = getDateUsingDateGap(entityDate,  firstRunDateCalculationMethod, taskInfoObj);
		return taskStartDate;
	}
	private static JsonObject getTaskExecutionInfo(Session organisationSession, int taskInfoId)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{							
			String selectQuery = "select"					
					+ " taskTimeCalculationType, "
					+ " firstRunDateCalculationMethod, "
					+ " firstRunDateGapInYears, "
					+ " firstRunDateGapInMonths, "
					+ " firstRunDateGapInDays, "
					+ " firstRunDateGapInHours, "
					+ " firstRunDateGapInMinutes, "
					+ " firstRunDateGapInSeconds "																				
					+ " from "
					+ " TaskExecutionInfo "
					+ " where "
					+ " 1=1"
					+ " and taskInfoId = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);			
			query.setParameter(0, taskInfoId);				
			JsonArray taskExecutionsList = new JsonArray();
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         while (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   					
				String taskTimeCalculationType = String.valueOf(resultRowColumnsList[0]);
				String firstRunDateCalculationMethod = String.valueOf(resultRowColumnsList[1]);
				String firstRunDateGapInYearsString = String.valueOf(resultRowColumnsList[2]);
				int firstRunDateGapInYears = 0;
				if(firstRunDateGapInYearsString.equals("null"))
				{
					firstRunDateGapInYearsString = "";
				}
				if(firstRunDateGapInYearsString.trim().length()>0)
				{
					firstRunDateGapInYears = Integer.parseInt(firstRunDateGapInYearsString);
				}
				String firstRunDateGapInMonthsString = String.valueOf(resultRowColumnsList[3]);
				int firstRunDateGapInMonths = 0;
				if(firstRunDateGapInMonthsString.equals("null"))
				{
					firstRunDateGapInMonthsString = "";
				}
				if(firstRunDateGapInMonthsString.trim().length()>0)
				{
					firstRunDateGapInMonths = Integer.parseInt(firstRunDateGapInMonthsString);
				}				
				String firstRunDateGapInDaysString = String.valueOf(resultRowColumnsList[4]);
				int firstRunDateGapInDays = 0;
				if(firstRunDateGapInDaysString.equals("null"))
				{
					firstRunDateGapInDaysString = "";
				}
				if(firstRunDateGapInDaysString.trim().length()>0)
				{
					firstRunDateGapInDays = Integer.parseInt(firstRunDateGapInDaysString);
				}				
				String firstRunDateGapInHoursString = String.valueOf(resultRowColumnsList[5]);
				int firstRunDateGapInHours = 0;
				if(firstRunDateGapInHoursString.equals("null"))
				{
					firstRunDateGapInHoursString = "";
				}
				if(firstRunDateGapInHoursString.trim().length()>0)
				{
					firstRunDateGapInHours = Integer.parseInt(firstRunDateGapInHoursString);
				}				
				String firstRunDateGapInMinutesString = String.valueOf(resultRowColumnsList[6]);
				int firstRunDateGapInMinutes = 0;
				if(firstRunDateGapInMinutesString.equals("null"))
				{
					firstRunDateGapInMinutesString = "";
				}
				if(firstRunDateGapInMinutesString.trim().length()>0)
				{
					firstRunDateGapInMinutes = Integer.parseInt(firstRunDateGapInMinutesString);
				}				
				String firstRunDateGapInSecondsString = String.valueOf(resultRowColumnsList[7]);
				int firstRunDateGapInSeconds = 0;
				if(firstRunDateGapInSecondsString.equals("null"))
				{
					firstRunDateGapInSecondsString = "";
				}
				if(firstRunDateGapInSecondsString.trim().length()>0)
				{
					firstRunDateGapInSeconds = Integer.parseInt(firstRunDateGapInSecondsString);
				}
				JsonObject taskExectionInfoObj =  new JsonObject();
				taskExectionInfoObj.addProperty("taskTimeCalculationType", taskTimeCalculationType);
				taskExectionInfoObj.addProperty("firstRunDateCalculationMethod", firstRunDateCalculationMethod);
				taskExectionInfoObj.addProperty("firstRunDateGapInYears", firstRunDateGapInYears);
				taskExectionInfoObj.addProperty("firstRunDateGapInMonths", firstRunDateGapInMonths);
				taskExectionInfoObj.addProperty("firstRunDateGapInDays", firstRunDateGapInDays);
				taskExectionInfoObj.addProperty("firstRunDateGapInHours", firstRunDateGapInHours);
				taskExectionInfoObj.addProperty("firstRunDateGapInMinutes", firstRunDateGapInMinutes);
				taskExectionInfoObj.addProperty("firstRunDateGapInSeconds", firstRunDateGapInSeconds);
				taskExecutionsList.add(taskExectionInfoObj);
			}
			dataObject.addProperty("success", 1);						
			dataObject.add("taskExecutionsList", taskExecutionsList);						
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Task execution information could not be retrieved.");
		}		
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Task execution information could not be retrieved.");
		return dataObject;
	}
	private static int sendEmailNotification(Session organisationSession, String emailNotifactionLayout, JsonArray notificationLayoutParameterNameAndValuesList, String emailId, String subject, JsonArray emailAttachmentLayoutsList, String isRecurring, int taskInfoId, int targetEntityId, int targetUserId, String notificationMedium, int taskFrequency, String taskFrequencyUnit)throws Exception
	{
		String[] toEmailIdsList = new String[1];
		toEmailIdsList[0] = emailId;		
		int isAttachment = 0;					
		JsonArray attachmentFilesList = new JsonArray();
		String emailFormat = "";
		JsonObject emailAttachmentsInfoObj = getEmailAttachements(organisationSession, emailAttachmentLayoutsList, notificationLayoutParameterNameAndValuesList);
		if(emailAttachmentsInfoObj.get("success").getAsInt()!=1)
		{
			CommonUtil.writeTolog(emailAttachmentsInfoObj.get("alert").getAsString());
			return 0;						
		}
		attachmentFilesList = emailAttachmentsInfoObj.get("emailAttachmentsList").getAsJsonArray();
		if(attachmentFilesList.size()>0)
		{
			emailFormat = "ATTACHEMENT";
			isAttachment =1;
		}
		int layoutEmailStatus = sendLayoutEmail(organisationSession, emailNotifactionLayout, notificationLayoutParameterNameAndValuesList, toEmailIdsList, subject, isAttachment, attachmentFilesList, emailFormat);			
		return layoutEmailStatus;
	}
	public static int sendLayoutEmail(Session organisationSession, String emailNotifactionLayout, JsonArray notificationLayoutParameterNameAndValuesList, String[] toEmailIdsList, String subject, int isAttachment, JsonArray attachmentFilesList, String emailFormat) throws Exception
	{
		String htmlContent = getLayoutHTMLWithDataFromFile(organisationSession, emailNotifactionLayout, notificationLayoutParameterNameAndValuesList);
		if(htmlContent == null)
		{
			return 0;	
		}
		String emailMessage = htmlContent;
		int isEmailSent = CommonUtil.sendEmail(toEmailIdsList, subject, emailMessage, isAttachment, attachmentFilesList, emailFormat);
		return isEmailSent;
	}
	public static String getLayoutHTMLWithDataFromFile(Session organisationSession, String layoutName, JsonArray notificationLayoutParameterNameAndValuesList)
	{		
		try
		{
			String searchParameterListText = "";			
			JsonObject layoutFormatJsonObj = LayoutParser.getJsonFromHTML(layoutName, searchParameterListText);
			JsonArray fieldsQueriesList = new JsonArray();
			JsonArray dataFieldsList = new JsonArray();
			JsonArray tablesList = new JsonArray();
			JsonArray graphsList = new JsonArray();
			JsonArray searchFormsList = new JsonArray();
			JsonArray searchFieldsList = new JsonArray();
			JsonArray URLParametersList = new JsonArray();
			JsonArray layoutQueriesList = new JsonArray();
			JsonObject metadataInfo = new JsonObject();			
			JsonObject defaultProperties = new JsonObject();
			JsonObject fetchDataInfoObj = new JsonObject();			
			fetchDataInfoObj.add("URLParameterNameAndValuesList", notificationLayoutParameterNameAndValuesList);
			fetchDataInfoObj.add("queryCodeAndQueryResultsDataMapInfoObj", new JsonObject());
			fetchDataInfoObj.addProperty("fetchData", 1);
			fetchDataInfoObj.addProperty("dataFieldsText", "");
			JsonObject printFormatMetaDataObj = LayoutParser.getHTMLFromJson(organisationSession, layoutName, layoutFormatJsonObj, fieldsQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList,searchFieldsList,  metadataInfo, URLParametersList, layoutQueriesList, fetchDataInfoObj, defaultProperties);
			if (printFormatMetaDataObj.get("success").getAsInt() != 1)
			{
				System.err.println(printFormatMetaDataObj.get("alert").getAsString());
				return null;
			}
			String rootHTMLWithData = printFormatMetaDataObj.get("rootHTMLWithOutData").getAsString();
			String layoutHTMLWithData = "<html>" + rootHTMLWithData + "</html>";
			return layoutHTMLWithData;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
		}
		System.out.println("Layout html with data could not be prepared for " + layoutName);
		return null;
	}
	private static JsonObject getNotificationLayoutParameterNameAndValuesList(JsonObject taskObj, JsonArray taskLayoutParametersList, Date nextNotificationTime, int targetUserId, int targetEntityId)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonArray notificationLayoutParameterNameAndValuesList = new JsonArray();
			for (int j = 0; j < taskLayoutParametersList.size(); j++)
			{
				JsonObject notificationLayoutParameterInfoObj = taskLayoutParametersList.get(j).getAsJsonObject();
				String parameterName = notificationLayoutParameterInfoObj.get("parameterName").getAsString();
				String parameterValueType = notificationLayoutParameterInfoObj.get("parameterValueType").getAsString();
				String parameterValue = "";		
				String parameterValueDataType = "String";
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(nextNotificationTime);		
				if(parameterValueType.equalsIgnoreCase("NOTIFICATION_START_DATE"))
				{	
					String fromDateString = TasksUtil.calculateTaskStartDate(nextNotificationTime);
					parameterValue = String.valueOf(fromDateString);
				}
				if(parameterValueType.equalsIgnoreCase("NOTIFICATION_END_DATE"))
				{						
					String toDateString = TasksUtil.calculateTaskEndDate(nextNotificationTime);
					parameterValue = String.valueOf(toDateString);
				}								
				if(parameterValueType.equalsIgnoreCase("USER_ID"))
				{
					parameterValue = String.valueOf(targetUserId);
					parameterValueDataType = "Integer";
				}
				if(parameterValueType.equalsIgnoreCase("ENTITY_ID"))
				{
					parameterValue = String.valueOf(targetEntityId);
					parameterValueDataType = "Integer";
				}
				JsonObject parameterNameAndValueMapInfoObj = new JsonObject();
				parameterNameAndValueMapInfoObj.addProperty("parameterName", parameterName);
				parameterNameAndValueMapInfoObj.addProperty("parameterValue", parameterValue);
				parameterNameAndValueMapInfoObj.addProperty("parameterValueDataType", parameterValueDataType);
				notificationLayoutParameterNameAndValuesList.add(parameterNameAndValueMapInfoObj);
			}
			dataObject.addProperty("success", 1);
			dataObject.add("notificationLayoutParameterNameAndValuesList", notificationLayoutParameterNameAndValuesList);		
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
		}
		finally
		{
		}		
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Notification layout parameter name and values list map could not built.");		
		return dataObject;
	}
	private static JsonObject addTaskSentInfo(Session organisationSession, int taskInfoId, int targetEntityId,  int targetUserId, String notificationMedium, Date TasksentTime, String layoutInfoText)throws Exception
	{
		JsonObject dataObject = new JsonObject();			
		try
		{			
			TaskSentInfo taskSentInfo = new TaskSentInfo();
			taskSentInfo.setTargetEntityId(targetEntityId);
			taskSentInfo.setTargetUserId(targetUserId);
			taskSentInfo.setNotificationMedium(notificationMedium);
			taskSentInfo.setNotificationSentTime(TasksentTime);
			taskSentInfo.setLayoutInfoText(layoutInfoText);
			int insertCount = (int) organisationSession.save(taskSentInfo);
			if (insertCount > 0)
			{				
				dataObject.addProperty("success", 1);				
				return dataObject;
			}
			else
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Task sent information could not be added.");
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
		}
		finally
		{					
		}		
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Task sent information could not be added.");		
		return dataObject;
	}
	private static JsonObject updateNextNotificationTime(Session organisationSession, String notificationMedium, int taskInfoId,  int targetEntityId, int targetUserId, int taskFrequency, String taskFrequencyUnit, Date TasksentTime)throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{                      
			if(TasksentTime == null)
			{
				dataObject = updateNextNotificationTimeToNull(organisationSession, taskInfoId, targetEntityId, targetUserId, notificationMedium);
				return dataObject;
			}
			String TasksentTimeString  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(TasksentTime);			
			String intervalQuery = "";
			if(taskFrequencyUnit.equalsIgnoreCase("YEAR"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? YEAR)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("MONTH"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? MONTH)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("WEEK"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? WEEK)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("DAY"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? DAY)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("HOUR"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? HOUR)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("MINUTE"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? MINUTE)";
			}
			if(taskFrequencyUnit.equalsIgnoreCase("SECOND"))
			{
				intervalQuery = "DATE_ADD(?, INTERVAL ? SECOND)";
			}			
			String updateQuery = " update TaskScheduleInfo set notificationLastSentTime = ?, nextNotificationTime = " + intervalQuery + " where 2>1 and taskInfoId = ? and targetEntityId = ? and targetUserId = ? and notificationMedium = ?";
			Query query = organisationSession.createSQLQuery(updateQuery);			
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, TasksentTimeString);
			query.setParameter(paramsPosIndex++, TasksentTimeString);			
			query.setParameter(paramsPosIndex++, taskFrequency);
			query.setParameter(paramsPosIndex++, taskInfoId);
			query.setParameter(paramsPosIndex++, targetEntityId);
			query.setParameter(paramsPosIndex++, targetUserId);
			query.setParameter(paramsPosIndex++, notificationMedium);
			int isNextNotificationUpdated = query.executeUpdate();
			dataObject.addProperty("success", 1);			
			dataObject.addProperty("isNextNotificationUpdated", isNextNotificationUpdated);			
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("next notification time could not be updated.");
		}
		finally
		{						
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "next notification time could not be updated.");
		return dataObject;
	}
	private static JsonObject updateNextNotificationTimeToNull(Session organisationSession, int taskInfoId, int targetEntityId, int targetUserId, String notificationMedium) throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{                      
			String updateQuery = " update TaskScheduleInfo set nextNotificationTime = null where 2>1 and taskInfoId = ? and targetEntityId = ? and targetUserId = ? and notificationMedium = ?";
			Query query = organisationSession.createSQLQuery(updateQuery);
			int paramsPosIndex = 0;			;
			query.setParameter(paramsPosIndex++, taskInfoId);
			query.setParameter(paramsPosIndex++, targetEntityId);
			query.setParameter(paramsPosIndex++, targetUserId);
			query.setParameter(paramsPosIndex++, notificationMedium);			
			int isNextNotificationUpdated = query.executeUpdate();
			dataObject.addProperty("success", 1);			
			dataObject.addProperty("isNextNotificationUpdated", isNextNotificationUpdated);			
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("next notification time could not be updated.");
		}
		finally
		{						
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "next notification time could not be updated.");
		return dataObject;
	}
	private static JsonObject getTargetUserInfoFromTargetUsersQuery(Session organisationSession, String queryCode, String targetUserIdColumnAlias, int targetUserId, String targetEntityIdColumnAlias, int targetEntityId)
	{
		JsonObject dataObject = new JsonObject();		
		try
		{
			JsonObject userInfoObj = new JsonObject();
			JsonArray WhereClausesList =  new JsonArray();
			JsonObject whereClauseInfoObj =  new JsonObject();
			whereClauseInfoObj.addProperty("whereClauseType", "QUERY_COLUMN");
			whereClauseInfoObj.addProperty("queryColumnId", targetUserIdColumnAlias);
			WhereClausesList.add(whereClauseInfoObj);
			if(targetEntityIdColumnAlias.trim().length() > 0)
			{
				JsonObject entityWhereClauseInfoObj =  new JsonObject();
				entityWhereClauseInfoObj.addProperty("whereClauseType", "QUERY_COLUMN");
				entityWhereClauseInfoObj.addProperty("queryColumnId", targetEntityIdColumnAlias);
				WhereClausesList.add(entityWhereClauseInfoObj);				
			}			
			JsonArray queryParameterNameAndValueslist =  new JsonArray();
			JsonObject queryParameterNameAndValueInfoObj = new JsonObject();
			queryParameterNameAndValueInfoObj.addProperty("parameterName", "parameterName");
			queryParameterNameAndValueInfoObj.addProperty("parameterValue", targetUserId);
			queryParameterNameAndValueInfoObj.addProperty("parameterValueDataType", "Integer");
			queryParameterNameAndValueslist.add(queryParameterNameAndValueInfoObj);
			if(targetEntityIdColumnAlias.trim().length() > 0)
			{
				JsonObject entityQueryParameterNameAndValueInfoObj = new JsonObject();
				entityQueryParameterNameAndValueInfoObj.addProperty("parameterName", "parameterName");
				entityQueryParameterNameAndValueInfoObj.addProperty("parameterValue", targetEntityId);
				entityQueryParameterNameAndValueInfoObj.addProperty("parameterValueDataType", "Integer");
				queryParameterNameAndValueslist.add(entityQueryParameterNameAndValueInfoObj);				
			}
            JsonObject queryOutputObj = LayoutParserUtil.getQueryOutputNew(organisationSession, queryCode,  WhereClausesList, queryParameterNameAndValueslist);
            if (queryOutputObj.get("success").getAsInt() != 1)
            {
                return queryOutputObj;
            }
            JsonArray queryOutputRecordsList = queryOutputObj.get("queryOutputRecordsList").getAsJsonArray();
            for(int i = 0;i<queryOutputRecordsList.size();i++)
            {            	
            	JsonObject queryOutputRecordInfoObj = queryOutputRecordsList.get(i).getAsJsonObject();	            	
            	JsonArray outputRecordColumnsList = queryOutputRecordInfoObj.get("outputRecordColumnsList").getAsJsonArray();
            	for(int j=0; j< outputRecordColumnsList.size();j++)
            	{
            		JsonObject outputRecordColumnInfoObj = outputRecordColumnsList.get(j).getAsJsonObject();
            		String columnAlias = outputRecordColumnInfoObj.get("columnAlias").getAsString();
            		String columnValue = "";
             		try
					{
            			columnValue = outputRecordColumnInfoObj.get("columnValue").getAsString();
					}
					catch (Exception e)
					{
						columnValue = "";
					}
            		userInfoObj.addProperty(columnAlias, columnValue);
            	}	            	
            }			
			dataObject.addProperty("success", 1);
			dataObject.add("userInfoObj", userInfoObj);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Task user  information  could not be retrieved.");
		}
		finally
		{			
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Task user  information  could not be retrieved.");
		return dataObject;
	}
	private static JsonObject checkAPICallTaskExistency(Session organisationSession, int taskInfoId, int targetEntityId, int targetUserId)throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{			
			String selectQuery = "SELECT " + 
					"    COUNT(*)" + 
					" FROM" + 
					"    TaskScheduleInfo " + 
					" WHERE" + 
					"    taskInfoId = ?" + 
					"        AND targetUserId = ? " + 
					"        AND targetEntityId = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, taskInfoId);
			query.setParameter(paramsPosIndex++, targetUserId);
			query.setParameter(paramsPosIndex++, targetEntityId);
			query.setParameter(paramsPosIndex++, taskInfoId);			
			int recordsCount = 0;
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         if (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   			
				recordsCount = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
			}		
			dataObject.addProperty("success", 1);
			dataObject.addProperty("recordsCount", recordsCount);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
		}
		finally
		{			
		}		
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Problem has occured while checking whether api call task name existed or not.");
		return dataObject;
	}
	private static JsonObject checkTaskUserIdInScheduleInfoOrNot(Session organisationSession, int taskInfoId, int targetUserId, int targetEntityId, String notificationMedium)throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{			
			String selectQuery = "SELECT " + 
					"    COUNT(*)" + 
					" FROM" + 
					"    TaskScheduleInfo " +					
					" WHERE " + 
					"    taskInfoId = ? " + 
					"        AND targetUserId = ? " + 
					"        AND targetEntityId = ? "+
					"        AND notificationMedium = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, taskInfoId);
			query.setParameter(paramsPosIndex++, targetUserId);
			query.setParameter(paramsPosIndex++, targetEntityId);
			query.setParameter(paramsPosIndex++, notificationMedium);
			int recordsCount = 0;
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			if (iterator.hasNext())
			{
				Object[] resultRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultRowColumnsList = new Object[1];
					resultRowColumnsList[0] = (Object) iterator.next();
				}
				recordsCount = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
			}	
			dataObject.addProperty("success", 1);
			dataObject.addProperty("recordsCount", recordsCount);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
		}
		finally
		{			
		}
		logger.debug("Problem has occured while checking whether notification user id in schedule info or not.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Problem has occured while checking whether notification user id in schedule info or not.");
		return dataObject;
	}
	private static JsonObject addTaskScheduleInfo(Session organisationSession, int taskInfoId, int targetEntityId,  int targetUserId, String notificationMedium, Date taskStartDate)throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{			
			if(taskStartDate == null)
			{
				System.err.println("Task start date should not be null while adding task schedule information.");
				return null;
			}
			TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
			taskScheduleInfo.setTaskInfoId(taskInfoId);
			taskScheduleInfo.setTargetEntityId(targetEntityId);
			taskScheduleInfo.setTargetUserId(targetUserId);
			taskScheduleInfo.setNotificationMedium(notificationMedium);
			taskScheduleInfo.setNextNotificationTime(taskStartDate);
			int insertCount = (int) organisationSession.save(taskScheduleInfo);
			if (insertCount > 0)
			{				
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", "Task schedule information created successfully.");
				return dataObject;
			}
			else
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Task schedule information could not be created.");
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
		}
		finally
		{			
		}
		logger.debug("Print Format Information could not be created");
		dataObject.addProperty("alert", "Print Format Information could not be created");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getTaskInfo(Session organisationSession, int taskInfoId)throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{					
			String selectQuery = "select"				
					+ " taskName, "										
					+ " targetEntityQuery, "
					+ " targetUserIdColumnAlias, "
					+ " targetEntityIdColumnAlias, "					
					+ " enableInAppNotification, "
					+ " enableEmailNotification, "
					+ " emailColumnAlias, "					
					+ " emailNotificationLayout, "
					+ " enableSmsNotification, "
					+ " smsColumnAlias, "
					+ " smsNotificationLayout, "
					+ " taskStartDate, "
					+ " taskFrequency, "
					+ " taskFrequencyUnit, "
					+ " dateColumnAlias, "
					+ " emailSubject, "				
					+ " firstRunDateCalculationMethod, "
					+ " firstRunDateGapInYears, "
					+ " firstRunDateGapInMonths, "
					+ " firstRunDateGapInDays, "
					+ " firstRunDateGapInHours, "
					+ " firstRunDateGapInMinutes, "
					+ " firstRunDateGapInSeconds, "
					+ " isRecurring, "					
					+ " apiName, "
					+ " sMSText "
					+ " from "
					+ " TaskInfo "
					+ " where 1=1"
					+ " and taskInfoId = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);					
			query.setParameter(0, taskInfoId);							
			JsonObject taskObj = new JsonObject();
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			if (iterator.hasNext())
			{
				Object[] resultRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultRowColumnsList = new Object[1];
					resultRowColumnsList[0] = (Object) iterator.next();
				}
				String taskName = String.valueOf(resultRowColumnsList[0]);
				String targetEntityQuery = String.valueOf(resultRowColumnsList[1]);				
				String targetUserIdColumnAlias = String.valueOf(resultRowColumnsList[2]);
				String targetEntityIdColumnAlias = String.valueOf(resultRowColumnsList[3]);				
				String enableInAppNotification = String.valueOf(resultRowColumnsList[4]);  	
				String enableEmailNotification = String.valueOf(resultRowColumnsList[5]);
				String emailColumnAlias = String.valueOf(resultRowColumnsList[6]);				
				String emailNotificationLayout = String.valueOf(resultRowColumnsList[7]);
				String enableSmsNotification = String.valueOf(resultRowColumnsList[8]);
				String smsColumnAlias = String.valueOf(resultRowColumnsList[9]);
				String smsNotificationLayout = String.valueOf(resultRowColumnsList[10]);								
				int taskFrequency = (Integer)resultRowColumnsList[12];
				String taskFrequencyUnit = String.valueOf(resultRowColumnsList[13]);
				String apiName = String.valueOf(resultRowColumnsList[24]);
				String sMSText = String.valueOf(resultRowColumnsList[25]);				
				String dateColumnAlias = String.valueOf(resultRowColumnsList[14]);
				String emailSubject = String.valueOf(resultRowColumnsList[15]);
				if(emailSubject == null)
				{
					emailSubject = "";
				}
				String firstRunDateCalculationMethod = String.valueOf(resultRowColumnsList[16]);
				String firstRunDateGapInYearsString = String.valueOf(resultRowColumnsList[17]);
				String taskStartDate = String.valueOf(resultRowColumnsList[11]);	
				if(firstRunDateGapInYearsString.equals("null"))
				{
					firstRunDateGapInYearsString = "";
				}
				int firstRunDateGapInYears = 0;
				if(firstRunDateGapInYearsString.trim().length()>0)
				{
					firstRunDateGapInYears = Integer.parseInt(firstRunDateGapInYearsString);
				}
				String firstRunDateGapInMonthsString = String.valueOf(resultRowColumnsList[18]);
				if(firstRunDateGapInMonthsString.equals("null"))
				{
					firstRunDateGapInMonthsString = "";
				}
				int firstRunDateGapInMonths = 0;
				if(firstRunDateGapInMonthsString.trim().length()>0)
				{
					firstRunDateGapInMonths = Integer.parseInt(firstRunDateGapInMonthsString);
				}				
				String firstRunDateGapInDaysString = String.valueOf(resultRowColumnsList[19]);
				if(firstRunDateGapInDaysString.equals("null"))
				{
					firstRunDateGapInDaysString = "";
				}
				int firstRunDateGapInDays = 0;
				if(firstRunDateGapInDaysString.trim().length()>0)
				{
					firstRunDateGapInDays = Integer.parseInt(firstRunDateGapInDaysString);
				}				
				String firstRunDateGapInHoursString = String.valueOf(resultRowColumnsList[20]);
				if(firstRunDateGapInHoursString.equals("null"))
				{
					firstRunDateGapInHoursString = "";
				}
				int firstRunDateGapInHours = 0;
				if(firstRunDateGapInHoursString.trim().length()>0)
				{
					firstRunDateGapInHours = Integer.parseInt(firstRunDateGapInHoursString);
				}				
				String firstRunDateGapInMinutesString = String.valueOf(resultRowColumnsList[21]);
				if(firstRunDateGapInMinutesString.equals("null"))
				{
					firstRunDateGapInMinutesString = "";
				}
				int firstRunDateGapInMinutes = 0;
				if(firstRunDateGapInMinutesString.trim().length()>0)
				{
					firstRunDateGapInMinutes = Integer.parseInt(firstRunDateGapInMinutesString);
				}				
				String firstRunDateGapInSecondsString = String.valueOf(resultRowColumnsList[22]);
				if(firstRunDateGapInSecondsString.equals("null"))
				{
					firstRunDateGapInSecondsString = "";
				}
				int firstRunDateGapInSeconds = 0;
				if(firstRunDateGapInSecondsString.trim().length()>0)
				{
					firstRunDateGapInSeconds = Integer.parseInt(firstRunDateGapInSecondsString);
				}
				String isRecurring = String.valueOf(resultRowColumnsList[23]);
				taskObj.addProperty("taskInfoId", taskInfoId);
				taskObj.addProperty("taskName", taskName);
				taskObj.addProperty("targetEntityQuery", targetEntityQuery);
				taskObj.addProperty("targetUserIdColumnAlias", targetUserIdColumnAlias);
				taskObj.addProperty("targetEntityIdColumnAlias", targetEntityIdColumnAlias);				
				taskObj.addProperty("emailColumnAlias", emailColumnAlias);
				taskObj.addProperty("smsColumnAlias", smsColumnAlias);
				taskObj.addProperty("enableEmailNotification", enableEmailNotification);
				taskObj.addProperty("enableSmsNotification", enableSmsNotification);
				taskObj.addProperty("enableInAppNotification", enableInAppNotification);
				taskObj.addProperty("emailNotificationLayout", emailNotificationLayout);
				taskObj.addProperty("smsNotificationLayout", smsNotificationLayout);
				taskObj.addProperty("taskFrequency", taskFrequency);
				taskObj.addProperty("taskFrequencyUnit", taskFrequencyUnit);
				taskObj.addProperty("apiName", apiName);
				taskObj.addProperty("sMSText", sMSText);				
				taskObj.addProperty("taskFrequencyUnit", taskFrequencyUnit);
				taskObj.addProperty("dateColumnAlias", dateColumnAlias);
				taskObj.addProperty("emailSubject", emailSubject);				
				taskObj.addProperty("firstRunDateCalculationMethod", firstRunDateCalculationMethod);
				taskObj.addProperty("firstRunDateGapInYears", firstRunDateGapInYears);
				taskObj.addProperty("firstRunDateGapInMonths", firstRunDateGapInMonths);
				taskObj.addProperty("firstRunDateGapInDays", firstRunDateGapInDays);
				taskObj.addProperty("firstRunDateGapInHours", firstRunDateGapInHours);
				taskObj.addProperty("firstRunDateGapInMinutes", firstRunDateGapInMinutes);
				taskObj.addProperty("firstRunDateGapInSeconds", firstRunDateGapInSeconds);
				taskObj.addProperty("isRecurring", isRecurring);
				taskObj.addProperty("taskStartDate", taskStartDate);
				JsonObject taskLayoutParametersInfoObj = getNotificationLayoutParameters(organisationSession, taskInfoId);
				if (taskLayoutParametersInfoObj.get("success").getAsInt() != 1)
				{
					return taskLayoutParametersInfoObj;
				}
				JsonArray taskLayoutParametersList = taskLayoutParametersInfoObj.get("taskLayoutParametersList").getAsJsonArray();
				JsonObject emailAttachmentLayoutInfoObj = getNotificationEmailAttachmentLayoutInfo(organisationSession, taskInfoId);
				if (emailAttachmentLayoutInfoObj.get("success").getAsInt() != 1)
				{
					return emailAttachmentLayoutInfoObj;
				}
				JsonArray emailAttachmentLayoutsList = emailAttachmentLayoutInfoObj.get("emailAttachmentLayoutsList").getAsJsonArray();
				taskObj.add("taskLayoutParametersList", taskLayoutParametersList);
				taskObj.add("emailAttachmentLayoutsList", emailAttachmentLayoutsList);
			}
			dataObject.addProperty("success", 1);						
			dataObject.add("taskObj", taskObj);						
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification information could not be retrieved.");
		}		
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Notification information could not be retrieved.");
		return dataObject;
	}
	private static JsonObject getEmailAttachements(Session organisationSession, JsonArray emailAttachmentLayoutsList, JsonArray notificationLayoutParameterNameAndValuesList)
	{
    	JsonObject dataObject  = new JsonObject();
    	try
    	{
    		JsonArray emailAttachmentsList = new JsonArray();
    		for(int i =0 ;i< emailAttachmentLayoutsList.size();i++)
    		{
    			JsonObject emailAttachmentLayoutInfoObj =  emailAttachmentLayoutsList.get(i).getAsJsonObject();
    			String printFormatCode = emailAttachmentLayoutInfoObj.get("emailAttachmentLayoutName").getAsString();
				String htmlContent = getLayoutHTMLWithDataFromFile(organisationSession, printFormatCode, notificationLayoutParameterNameAndValuesList);
				if(htmlContent == null)
				{
					return null;	
				}
				String filesTempDirectoryString = CommonUtil.getPropertyValue("FILES_TEMP_DIRECTORY");
				File filesTempDirectory = new File(filesTempDirectoryString);
				if(!(filesTempDirectory.exists()))
				{
					filesTempDirectory.mkdirs();
				}
				String projectPath = SettingsUtil.getProjectPath();
				String filesTempDirectoryPathString = projectPath + File.separatorChar + filesTempDirectoryString + File.separatorChar;		
				String outputFileName = printFormatCode;
	            double pageWidth = 10.5;
	            double pageHeight = 12;
	            String pageWidthAttribute = pageWidth + "in";	            
	            String pageHeightAttribute = pageHeight + "in";	            
	            String fileAbsolutePath = CommonUtil.generatePDFFromHTML(htmlContent, pageWidthAttribute, pageHeightAttribute, filesTempDirectoryPathString, outputFileName);
	            JsonObject emailAttachmentInfoObj = new JsonObject();
	            emailAttachmentInfoObj.addProperty("filePath", fileAbsolutePath);
	            emailAttachmentInfoObj.addProperty("fileDisplayName", outputFileName);
	            emailAttachmentInfoObj.addProperty("isInlineAttachment", 1);
	            emailAttachmentsList.add(emailAttachmentInfoObj);				
    		}
    		dataObject.addProperty("success", 1);	    		    		 	
    		dataObject.add("emailAttachmentsList", emailAttachmentsList);	    		    		 	
    		return dataObject;
    	}
    	catch (Exception e) 
    	{
    		CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Email attachments list could not be generated.");
		}
    	finally 
    	{
    	}
    	dataObject.addProperty("success", 0);
    	dataObject.addProperty("alert", "Email attachments list could not be generated");
    	return dataObject;
	}
	private static JsonObject getNotificationEmailAttachmentLayoutInfo(Session organisationSession, int taskInfoId)throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{
			String selectQuery = " SELECT  " + 
					"    emailAttachmentLayoutName " + 
					" FROM " + 
					"    EmailAttachmentLayout " + 
					" WHERE " + 
					"    taskInfoId = ? ";		
			Query query = organisationSession.createSQLQuery(selectQuery);			
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, taskInfoId);
			JsonArray emailAttachmentLayoutsList = new JsonArray();
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         while (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   				
				JsonObject emailLayoutAttachmentInfoObj = new JsonObject();
				emailLayoutAttachmentInfoObj.addProperty("emailAttachmentLayoutName", String.valueOf(resultRowColumnsList[0]));				
				emailAttachmentLayoutsList.add(emailLayoutAttachmentInfoObj);
			}
			dataObject.addProperty("success", 1);
			dataObject.add("emailAttachmentLayoutsList", emailAttachmentLayoutsList);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification email attachment layouts list information  could not be retrieved.");
		}
		finally
		{			
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Notification email attachment layouts list information  could not be retrieved.");
		return dataObject;
	}
	private static JsonObject getNotificationLayoutParameters(Session organisationSession, int taskInfoId) throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{
			String selectQuery = " SELECT  " + 
					"    parameterName, " + 
					"    parameterValueType " + 
					" FROM " + 
					"    TaskLayoutParameters " + 
					" WHERE " + 
					"    taskInfoId = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);			
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, taskInfoId);
			JsonArray taskLayoutParametersList = new JsonArray();
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         while (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   
				JsonObject parameterInfoObj = new JsonObject();
				parameterInfoObj.addProperty("parameterName", String.valueOf(resultRowColumnsList[0]));
				parameterInfoObj.addProperty("parameterValueType", String.valueOf(resultRowColumnsList[1]));
				taskLayoutParametersList.add(parameterInfoObj);
			}
			dataObject.addProperty("success", 1);
			dataObject.add("taskLayoutParametersList", taskLayoutParametersList);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(Tasks.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification layout parameters list information  could not be retrieved.");
		}
		finally
		{			
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Notification layout parameters list information could not be retrieved.");
		return dataObject;
	}
	private static Date getDateUsingDateGap(Date date, String firstRunDateCalculationMethod, JsonObject taskInfoObj)
	{		
		Date updatedDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		int firstRunDateGapInYears = taskInfoObj.get("firstRunDateGapInYears").getAsInt();
		int firstRunDateGapInMonths = taskInfoObj.get("firstRunDateGapInMonths").getAsInt();
		int firstRunDateGapInDays = taskInfoObj.get("firstRunDateGapInDays").getAsInt();
		int firstRunDateGapInHours = taskInfoObj.get("firstRunDateGapInHours").getAsInt();
		int firstRunDateGapInMinutes = taskInfoObj.get("firstRunDateGapInMinutes").getAsInt();
		int firstRunDateGapInSeconds = taskInfoObj.get("firstRunDateGapInSeconds").getAsInt();
		if(firstRunDateCalculationMethod.equalsIgnoreCase("BACKWARD"))
		{																	
			if(firstRunDateGapInYears > 0)
			{
				calendar.add(Calendar.YEAR, (-1* firstRunDateGapInYears));
			}			
			if(firstRunDateGapInMonths > 0)
			{
				calendar.add(Calendar.MONTH, (-1* firstRunDateGapInMonths));
			}
			if(firstRunDateGapInDays > 0)
			{
				   calendar.add(Calendar.DATE, (-1* firstRunDateGapInDays));
			}
			if(firstRunDateGapInHours > 0)
			{
				   calendar.add(Calendar.HOUR, (-1* firstRunDateGapInHours));
			}
			if(firstRunDateGapInMinutes > 0)
			{
				   calendar.add(Calendar.MINUTE, (-1* firstRunDateGapInMinutes));
			}
			if(firstRunDateGapInSeconds > 0)
			{
				   calendar.add(Calendar.SECOND, (-1* firstRunDateGapInSeconds));
			}
			updatedDate = calendar.getTime();															
		}	
		if(firstRunDateCalculationMethod.equalsIgnoreCase("FORWARD"))
		{																	
			if(firstRunDateGapInYears > 0)
			{
				calendar.add(Calendar.YEAR, (firstRunDateGapInYears));
			}			
			if(firstRunDateGapInMonths > 0)
			{
				calendar.add(Calendar.MONTH, (firstRunDateGapInMonths));
			}
			if(firstRunDateGapInDays > 0)
			{
				   calendar.add(Calendar.DATE, (firstRunDateGapInDays));
			}
			if(firstRunDateGapInHours > 0)
			{
				   calendar.add(Calendar.HOUR, (firstRunDateGapInHours));
			}
			if(firstRunDateGapInMinutes > 0)
			{
				   calendar.add(Calendar.MINUTE, (firstRunDateGapInMinutes));
			}
			if(firstRunDateGapInSeconds > 0)
			{
				   calendar.add(Calendar.SECOND, (firstRunDateGapInSeconds));
			}			
			updatedDate = calendar.getTime();															
		}			
		return updatedDate;
	}
	public static  void initHTMLLayoutWhereClauseMetadata()
	{
		try
		{
			URL htmlLayoutWhereClauseMetadataFileURL = Tasks.class.getResource("/Metadata/LayoutMetadata.xls");
			if(htmlLayoutWhereClauseMetadataFileURL != null)
			{
				String htmlLayoutQueriesMetadataFilePath = htmlLayoutWhereClauseMetadataFileURL.getPath();		
				loadMetaData("ALL_HTML_LAYOUT_QUERIES_LIST", htmlLayoutQueriesMetadataFilePath, "LayoutQueries", false);
				loadMetaData("ALL_HTML_LAYOUT_COMPONENTS_LIST", htmlLayoutQueriesMetadataFilePath, "LayoutComponents", false);
				loadMetaData("ALL_HTML_LAYOUT_DATA_FIELDS_LIST", htmlLayoutQueriesMetadataFilePath, "LayoutDataFields", false);				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void loadMetaData(String sMetadataType, String sMetaDataFile, String sheetName, Boolean isItCoreEntity) throws IOException, FileNotFoundException, CloneNotSupportedException, Exception
	{		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(sMetaDataFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetName);
		if(sheet == null)
		{
			System.err.println("Sheet "+sheetName +" doesn`t exist");
			return;
		}
		HSSFRow row;
		int maxRows = sheet.getLastRowNum();
		for (int i = 0; i < 12 || i <= maxRows; i++)
		{
			row = sheet.getRow(i);
			if (row != null)
			{
				if (i > 1)
				{
					String primaryKey = "";
					if (row.getCell(1) != null)
					{
						primaryKey = row.getCell(1).getStringCellValue();
					}
					if (isExists(primaryKey))
					{
						if (sMetadataType.matches("ALL_HTML_LAYOUT_QUERIES_LIST"))
						{
							try
							{
								LayoutParser.populateHTMLLayoutQueriesList(row, isItCoreEntity);
							}
							catch (Exception e)
							{								
								throw e;
							}
						}
						if (sMetadataType.matches("ALL_HTML_LAYOUT_COMPONENTS_LIST"))
						{
							try
							{
								LayoutParser.populateHTMLLayoutComponentsList(row, isItCoreEntity);
							}
							catch (Exception e)
							{								
								throw e;
							}
						}
						if (sMetadataType.matches("ALL_HTML_LAYOUT_DATA_FIELDS_LIST"))
						{
							try
							{
								LayoutParser.populateHTMLLayoutDataFieldsList(row, isItCoreEntity);
							}
							catch (Exception e)
							{								
								throw e;
							}
						}
					}
				}
			}
		}
	}
	protected static boolean isExists(String sString)
	{
		return (sString != null && sString.trim().length() > 0);
	}
	protected void debugCode(Integer i)
	{
		System.out.println(i);
	}
	protected static void debugCode(String sString)
	{
		System.out.println(sString);
	}
	public static boolean isValidEmail(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	} 
}
