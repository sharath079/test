package com.patientapp.controller.forms.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigDecimal;
import java.sql.Time;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import javax.servlet.http.HttpServletRequest;
import com.patientapp.request.service.HttpUtil;
import com.patientapp.request.service.ServiceAPIUtil;
import com.patientapp.request.service.RequestReceived;
	
//
import com.patientapp.bean.TaskExecutionInfo;
import com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl;



import com.patientapp.bean.TaskLayoutParameters;
import com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl;



import com.patientapp.bean.EmailAttachmentLayout;
import com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl;

import com.patientapp.bean.TaskInfo;
import com.patientapp.controller.forms.base.TaskInfoLabel;
import com.vw.runtime.BusinessRulesController;
import com.vw.runtime.VWViewObject;
import com.patientapp.util.ZipExtraction;
import com.vw.runtime.VWSessionObject;
import com.vw.runtime.VWMastersBean;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.UploadDownloadUtil;
@SuppressWarnings({"unused","unchecked"})
/**
 *
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 *
 */
public abstract class TaskInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'TaskName' 
	 *		 * 'TaskDescription' 
	 *		 * 'TaskType' 
	 *		 * 'ApiName' 
	 *		 * 'TargetEntityQuery' 
	 *		 * 'TargetUserIdColumnAlias' 
	 *		 * 'TargetEntityIdColumnAlias' 
	 *		 * 'EnableInAppNotification' 
	 *		 * 'InAppNotificationLayout' 
	 *		 * 'EnableEmailNotification' 
	 *		 * 'EmailColumnAlias' 
	 *		 * 'EmailNotificationLayout' 
	 *		 * 'EmailSubject' 
	 *		 * 'EnableSmsNotification' 
	 *		 * 'SmsColumnAlias' 
	 *		 * 'SmsNotificationLayout' 
	 *		 * 'SMSText' 
	 *		 * 'IsActive' 
	 *		 * 'TaskStartDate' 
	 *		 * 'TaskFrequency' 
	 *		 * 'TaskFrequencyUnit' 
	 *		 * 'IsRecurring' 
	 *		 * 'FirstRunType' 
	 *		 * 'DateColumnAlias' 
	 *		 * 'FirstRunDateCalculationMethod' 
	 *		 * 'FirstRunDateGapInYears' 
	 *		 * 'FirstRunDateGapInMonths' 
	 *		 * 'FirstRunDateGapInDays' 
	 *		 * 'FirstRunDateGapInHours' 
	 *		 * 'FirstRunDateGapInMinutes' 
	 *		 * 'FirstRunDateGapInSeconds' 
	 *	
	 *
	 */
	/*
	 * userSessionInfo Parameters:
	 * ================
	 * loggedInEmployeeId
	 * isUserLoggedIn
	 * loginEntityType 
	 * userId;
	 */	
	Integer iPKValue=0;
	protected JsonObject userSessionInfo = new JsonObject();
	protected JsonObject additionalInfo = new JsonObject();
	protected boolean isTransactionUpdatable;
	protected boolean isDeletionAllowed;
	protected TaskInfoLabel TaskInfoLabelLocal = new TaskInfoLabel();
	protected TaskInfo TaskInfoLocal = new TaskInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public TaskInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public TaskInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public TaskInfoControllerBase()
	{
		super();
	}
	public JsonObject getUserSessionInfo() 
	{
		return userSessionInfo;
	}
	public JsonObject getAdditionalInfo() 
	{
		return additionalInfo;
	}
	public void writeToLog(String logMessage) 
	{
		System.out.println(logMessage);
	}
 	protected String getPkFieldName()
	{
		return "TaskInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((TaskInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((TaskInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((TaskInfo)obj).getTaskInfoId();
	}
	public String getManagedBeanName()
    {
		return "TaskInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "TaskInfoLabelBean";
    }
	protected Class<TaskInfo> setBeanClass()
	{
		return TaskInfo.class;
	}
	protected Class<TaskInfoLabel> setBeanLabelClass()
	{
		return TaskInfoLabel.class;
	}
	protected TaskInfo getLocalManagedBean()
    {
		return (TaskInfo)getManagedBean();
    }
	
	protected String getAuthAmtCcy()
	{
		return "INR";
	}
	protected BigDecimal getAuthAmt()
	{
		return BigDecimal.ZERO;
	}
	public void refreshFromUI()
	{
		doRefreshFromUIImpl();
	}
	public void doBeforeTransactionApproved()
	{
		/*
		 * Use below code as required in impl
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		/*TaskInfo taskInfo = (TaskInfo)getManagedBean();
		String areChangesEffected = taskInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			taskInfo.setAreChangesEffected("YES");			
		}*/
		JsonObject dataObject = new JsonObject();		
			
		int generatedRequestId = -1;
			
		doBeforeTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doAfterTransactionApproved()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);		
	}
	public void doBeforeTransactionRolledback()
	{
		/*
		 * Use below code as required in impl
		 */
		/*TaskInfo taskInfo = (TaskInfo)getManagedBean();
		String areChangesEffected = taskInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			taskInfo.setAreChangesEffected("NO");			
		}*/
		int generatedRequestId = -1;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();	doBeforeTransactionRolledbackImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isAuthorizationEnabled()
	{
		return true;		
	}
	public boolean isRollbackRequired()
	{
		return true;
	}	
	public boolean isSubmitRequired()
	{
		return true;
	}	
	public boolean isTransactionUpdatable()
	{
		/*
		 * Use below code as required in impl
		 */		
		/*TaskInfo taskInfo = (TaskInfo)getManagedBean();
		String areChangesEffected = taskInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			return false;
		}
		else
		{
			return true;			
		}*/		
		isTransactionUpdatable = true;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsUpdatableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		return isTransactionUpdatable;
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		TaskInfo taskInfo = (TaskInfo)getManagedBean();
		String sCurrentStatus = taskInfo.getVwTxnStatus();
		if(isRollbackRequired()==true)
		{
			if("COMPLETED".equalsIgnoreCase(sCurrentStatus) && sAction.matches(ACTION_UPDATE))
			{
				if(!("ROLLBACK".equalsIgnoreCase(sAction)))
				{
					String sParams[] = new String[1];
					sParams[0] = sAction;
					addApplicationResponse("VWT", "ERR0016", sParams);
					handleResponse(); // GDN: do not move it outside
					return false;			
				}
			}			
		}
		return true;		
	}
	/*
	 * Business logic where clause parameters can be added based on the entity and the attribute 
	 * on which the lookup popup is opened. 
	 */
	public HashMap doBeforeLookupEntitiesRetrieved(String callingEntityName, String callingAttributeName)
	{
		HashMap customSearchParams = new HashMap();
	    /*
	     * Sample handling in impl
	     */
		/*if("SetOfBooksInfo".equalsIgnoreCase(callingEntityName))
		{
			if("ParentAccountGroup".equalsIgnoreCase(callingEntityName))
			{
				SetOfBooksInfo setOfBooksInfo = ((AccountGroups)getManagedBean()).getSetOfBooksInfo();
				customSearchParams.put("SetOfBooksInfo", setOfBooksInfo);				
			}
		}*/
		doBeforeLookupEntitiesRetrievedImpl(callingEntityName, callingAttributeName, customSearchParams);
		return customSearchParams;
	}
	public void doAfterSelectOptionChanged(String attributeName)
	{
		TaskInfo taskInfo = (TaskInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("taskType".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("enableInAppNotification".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("enableEmailNotification".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("enableSmsNotification".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("isActive".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("taskFrequencyUnit".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("isRecurring".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("firstRunType".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("firstRunDateCalculationMethod".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(taskInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterTaskInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		TaskInfo taskInfo = (TaskInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("taskName") && initParamsInfo.get("taskName").isJsonNull()==false)
		{
			String taskName = initParamsInfo.get("taskName").getAsString();
			taskInfo.setTaskName(taskName);			
		}if(initParamsInfo.has("taskDescription") && initParamsInfo.get("taskDescription").isJsonNull()==false)
		{
			String taskDescription = initParamsInfo.get("taskDescription").getAsString();
			taskInfo.setTaskDescription(taskDescription);			
		}if(initParamsInfo.has("taskType") && initParamsInfo.get("taskType").isJsonNull()==false)
		{
			String taskType = initParamsInfo.get("taskType").getAsString();
			taskInfo.setTaskType(taskType);			
		}if(initParamsInfo.has("apiName") && initParamsInfo.get("apiName").isJsonNull()==false)
		{
			String apiName = initParamsInfo.get("apiName").getAsString();
			taskInfo.setApiName(apiName);			
		}if(initParamsInfo.has("targetEntityQuery") && initParamsInfo.get("targetEntityQuery").isJsonNull()==false)
		{
			String targetEntityQuery = initParamsInfo.get("targetEntityQuery").getAsString();
			taskInfo.setTargetEntityQuery(targetEntityQuery);			
		}if(initParamsInfo.has("targetUserIdColumnAlias") && initParamsInfo.get("targetUserIdColumnAlias").isJsonNull()==false)
		{
			String targetUserIdColumnAlias = initParamsInfo.get("targetUserIdColumnAlias").getAsString();
			taskInfo.setTargetUserIdColumnAlias(targetUserIdColumnAlias);			
		}if(initParamsInfo.has("targetEntityIdColumnAlias") && initParamsInfo.get("targetEntityIdColumnAlias").isJsonNull()==false)
		{
			String targetEntityIdColumnAlias = initParamsInfo.get("targetEntityIdColumnAlias").getAsString();
			taskInfo.setTargetEntityIdColumnAlias(targetEntityIdColumnAlias);			
		}if(initParamsInfo.has("enableInAppNotification") && initParamsInfo.get("enableInAppNotification").isJsonNull()==false)
		{
			String enableInAppNotification = initParamsInfo.get("enableInAppNotification").getAsString();
			taskInfo.setEnableInAppNotification(enableInAppNotification);			
		}if(initParamsInfo.has("inAppNotificationLayout") && initParamsInfo.get("inAppNotificationLayout").isJsonNull()==false)
		{
			String inAppNotificationLayout = initParamsInfo.get("inAppNotificationLayout").getAsString();
			taskInfo.setInAppNotificationLayout(inAppNotificationLayout);			
		}if(initParamsInfo.has("enableEmailNotification") && initParamsInfo.get("enableEmailNotification").isJsonNull()==false)
		{
			String enableEmailNotification = initParamsInfo.get("enableEmailNotification").getAsString();
			taskInfo.setEnableEmailNotification(enableEmailNotification);			
		}if(initParamsInfo.has("emailColumnAlias") && initParamsInfo.get("emailColumnAlias").isJsonNull()==false)
		{
			String emailColumnAlias = initParamsInfo.get("emailColumnAlias").getAsString();
			taskInfo.setEmailColumnAlias(emailColumnAlias);			
		}if(initParamsInfo.has("emailNotificationLayout") && initParamsInfo.get("emailNotificationLayout").isJsonNull()==false)
		{
			String emailNotificationLayout = initParamsInfo.get("emailNotificationLayout").getAsString();
			taskInfo.setEmailNotificationLayout(emailNotificationLayout);			
		}if(initParamsInfo.has("emailSubject") && initParamsInfo.get("emailSubject").isJsonNull()==false)
		{
			String emailSubject = initParamsInfo.get("emailSubject").getAsString();
			taskInfo.setEmailSubject(emailSubject);			
		}if(initParamsInfo.has("enableSmsNotification") && initParamsInfo.get("enableSmsNotification").isJsonNull()==false)
		{
			String enableSmsNotification = initParamsInfo.get("enableSmsNotification").getAsString();
			taskInfo.setEnableSmsNotification(enableSmsNotification);			
		}if(initParamsInfo.has("smsColumnAlias") && initParamsInfo.get("smsColumnAlias").isJsonNull()==false)
		{
			String smsColumnAlias = initParamsInfo.get("smsColumnAlias").getAsString();
			taskInfo.setSmsColumnAlias(smsColumnAlias);			
		}if(initParamsInfo.has("smsNotificationLayout") && initParamsInfo.get("smsNotificationLayout").isJsonNull()==false)
		{
			String smsNotificationLayout = initParamsInfo.get("smsNotificationLayout").getAsString();
			taskInfo.setSmsNotificationLayout(smsNotificationLayout);			
		}if(initParamsInfo.has("sMSText") && initParamsInfo.get("sMSText").isJsonNull()==false)
		{
			String sMSText = initParamsInfo.get("sMSText").getAsString();
			taskInfo.setSMSText(sMSText);			
		}if(initParamsInfo.has("isActive") && initParamsInfo.get("isActive").isJsonNull()==false)
		{
			String isActive = initParamsInfo.get("isActive").getAsString();
			taskInfo.setIsActive(isActive);			
		}
		
		if(initParamsInfo.has("taskStartDate") && initParamsInfo.get("taskStartDate").isJsonNull()==false)
		{
			String taskStartDate = initParamsInfo.get("taskStartDate").getAsString();
			if(taskStartDate.length() > 0)
			{
				try
				{
					taskInfo.setTaskStartDate(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(taskStartDate));
				}
				catch(Exception e)
				{
				}
			}
		}if(initParamsInfo.has("taskFrequency") && initParamsInfo.get("taskFrequency").isJsonNull()==false)
		{
			Integer taskFrequency = null;
			try
			{
			 	taskFrequency = initParamsInfo.get("taskFrequency").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setTaskFrequency(taskFrequency);
		}if(initParamsInfo.has("taskFrequencyUnit") && initParamsInfo.get("taskFrequencyUnit").isJsonNull()==false)
		{
			String taskFrequencyUnit = initParamsInfo.get("taskFrequencyUnit").getAsString();
			taskInfo.setTaskFrequencyUnit(taskFrequencyUnit);			
		}if(initParamsInfo.has("isRecurring") && initParamsInfo.get("isRecurring").isJsonNull()==false)
		{
			String isRecurring = initParamsInfo.get("isRecurring").getAsString();
			taskInfo.setIsRecurring(isRecurring);			
		}if(initParamsInfo.has("firstRunType") && initParamsInfo.get("firstRunType").isJsonNull()==false)
		{
			String firstRunType = initParamsInfo.get("firstRunType").getAsString();
			taskInfo.setFirstRunType(firstRunType);			
		}if(initParamsInfo.has("dateColumnAlias") && initParamsInfo.get("dateColumnAlias").isJsonNull()==false)
		{
			String dateColumnAlias = initParamsInfo.get("dateColumnAlias").getAsString();
			taskInfo.setDateColumnAlias(dateColumnAlias);			
		}if(initParamsInfo.has("firstRunDateCalculationMethod") && initParamsInfo.get("firstRunDateCalculationMethod").isJsonNull()==false)
		{
			String firstRunDateCalculationMethod = initParamsInfo.get("firstRunDateCalculationMethod").getAsString();
			taskInfo.setFirstRunDateCalculationMethod(firstRunDateCalculationMethod);			
		}if(initParamsInfo.has("firstRunDateGapInYears") && initParamsInfo.get("firstRunDateGapInYears").isJsonNull()==false)
		{
			Integer firstRunDateGapInYears = null;
			try
			{
			 	firstRunDateGapInYears = initParamsInfo.get("firstRunDateGapInYears").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInYears(firstRunDateGapInYears);
		}if(initParamsInfo.has("firstRunDateGapInMonths") && initParamsInfo.get("firstRunDateGapInMonths").isJsonNull()==false)
		{
			Integer firstRunDateGapInMonths = null;
			try
			{
			 	firstRunDateGapInMonths = initParamsInfo.get("firstRunDateGapInMonths").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInMonths(firstRunDateGapInMonths);
		}if(initParamsInfo.has("firstRunDateGapInDays") && initParamsInfo.get("firstRunDateGapInDays").isJsonNull()==false)
		{
			Integer firstRunDateGapInDays = null;
			try
			{
			 	firstRunDateGapInDays = initParamsInfo.get("firstRunDateGapInDays").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInDays(firstRunDateGapInDays);
		}if(initParamsInfo.has("firstRunDateGapInHours") && initParamsInfo.get("firstRunDateGapInHours").isJsonNull()==false)
		{
			Integer firstRunDateGapInHours = null;
			try
			{
			 	firstRunDateGapInHours = initParamsInfo.get("firstRunDateGapInHours").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInHours(firstRunDateGapInHours);
		}if(initParamsInfo.has("firstRunDateGapInMinutes") && initParamsInfo.get("firstRunDateGapInMinutes").isJsonNull()==false)
		{
			Integer firstRunDateGapInMinutes = null;
			try
			{
			 	firstRunDateGapInMinutes = initParamsInfo.get("firstRunDateGapInMinutes").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInMinutes(firstRunDateGapInMinutes);
		}if(initParamsInfo.has("firstRunDateGapInSeconds") && initParamsInfo.get("firstRunDateGapInSeconds").isJsonNull()==false)
		{
			Integer firstRunDateGapInSeconds = null;
			try
			{
			 	firstRunDateGapInSeconds = initParamsInfo.get("firstRunDateGapInSeconds").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskInfo.setFirstRunDateGapInSeconds(firstRunDateGapInSeconds);
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(taskInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		TaskInfo taskInfo = (TaskInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		TaskInfo taskInfo = (TaskInfo)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(taskInfo, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{
		isTransactionUpdatable = isTransactionUpdatable();
		if(isTransactionUpdatable==false)
		{
			return false;
		}
		isDeletionAllowed = true;
		/*
		 * set "isDeletionAllowed" flag to false in impl 
		 * if deletion has to be prevented
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsDeletableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isDeletionAllowed==false)
		{
			addCustomResponse("This transaction cannot be deleted !!");
		}
		return isDeletionAllowed;
	}	
	public void beforeDeleteTransaction(boolean clearSession)
	{
		try
		{JsonObject dataObject = new JsonObject();		if(isTransactionUpdatable()==false)
			{
				addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
			}
			TaskInfo taskInfo = (TaskInfo)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*			Set<Object> taskExecutionInfoList = taskInfo.getTaskExecutionInfoes();
			Iterator<Object> taskExecutionInfoListIterator = taskExecutionInfoList.iterator();
			while(taskExecutionInfoListIterator.hasNext())
			{
				TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)taskExecutionInfoListIterator.next();
				TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(getDBSession());
				taskExecutionInfoControllerImpl.setPrivateManagedBean(taskExecutionInfo);
				taskExecutionInfoControllerImpl.deleteWithoutCommit(false);
			}			



			Set<Object> taskLayoutParametersList = taskInfo.getTaskLayoutParameterses();
			Iterator<Object> taskLayoutParametersListIterator = taskLayoutParametersList.iterator();
			while(taskLayoutParametersListIterator.hasNext())
			{
				TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)taskLayoutParametersListIterator.next();
				TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(getDBSession());
				taskLayoutParametersControllerImpl.setPrivateManagedBean(taskLayoutParameters);
				taskLayoutParametersControllerImpl.deleteWithoutCommit(false);
			}			



			Set<Object> emailAttachmentLayoutList = taskInfo.getEmailAttachmentLayoutes();
			Iterator<Object> emailAttachmentLayoutListIterator = emailAttachmentLayoutList.iterator();
			while(emailAttachmentLayoutListIterator.hasNext())
			{
				EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)emailAttachmentLayoutListIterator.next();
				EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(getDBSession());
				emailAttachmentLayoutControllerImpl.setPrivateManagedBean(emailAttachmentLayout);
				emailAttachmentLayoutControllerImpl.deleteWithoutCommit(false);
			}			

			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doBeforeDeleteTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void afterDeleteTransaction()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doAfterDeleteTransactionImpl();				
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeCreateTransaction(JsonObject paramsInfoObj)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		doBeforeCreateTransactionImpl(paramsInfoObj);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		//setHasTransactionFailed(false);
	}
	protected void afterCreateTransaction(JsonObject paramsInfoObj)
	{
		/*
		if (isActionFromUI())
		{
			try
			{
				if (!isEntityAuditType(getManagedBeanName()))
				{
					// Refetch MQ Record as the session does not contain MessageQueueBean
					String sCurrentLCNo = getLcNoImpl();
					com.patientapp.bean.MessageQueue messageQueue = new com.patientapp.bean.MessageQueue();
						if (isExists(sCurrentLCNo))
						{
							com.patientapp.controller.forms.impl.MessageQueueControllerImpl messageController = new com.patientapp.controller.forms.impl.MessageQueueControllerImpl();
							List<?> lst = messageController.retrieveUniqueTransaction("LcNo", sCurrentLCNo);
							messageQueue = (com.patientapp.bean.MessageQueue) lst.get(0);
							// Fetch newly created entity id 
							String sEntityId = getPKValue().toString();
							// Create a new MQ record 
							com.vw.runtime.rest.MessageQueueService mqservice = new com.vw.runtime.rest.MessageQueueService();
							String sMsgGroupName=getGroupName();
							if (!isExists(sMsgGroupName))
							{
								if (!isExists(messageQueue.getMsgGroup()))
								{
									sMsgGroupName="EXPORT";
								}
							}
							String sMsgDirection="I";
							String sMsgCategory="";
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "TaskInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
						}
				}
			}
			catch (Exception e)
			{
				writeToLog(CommonUtil.getStackTrace(e));
			}
		}
		*/
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterCreateTransactionImpl(paramsInfoObj);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterCreateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterCreateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		//doBeforeUpdateTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void afterUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterUpdateTransactionImpl(paramsInfoObject);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterUpdateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterUpdateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected HashMap<String, Object> getSearchParams()
	{
		debugCode("In getSearchParams() TaskInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (TaskInfoSearch)getManagedBean("TaskInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getTaskName()))
			{
				searchParams.put(TaskInfoLabelLocal.gettaskNameFieldName(),searchBeanLocal.getTaskName());
			}	
			if (isExists(searchBeanLocal.getTaskDescription()))
			{
				searchParams.put(TaskInfoLabelLocal.gettaskDescriptionFieldName(),searchBeanLocal.getTaskDescription());
			}	
			if (isExists(searchBeanLocal.getTaskType()))
			{
				searchParams.put(TaskInfoLabelLocal.gettaskTypeFieldName(),searchBeanLocal.getTaskType());
			}	
			if (isExists(searchBeanLocal.getApiName()))
			{
				searchParams.put(TaskInfoLabelLocal.getapiNameFieldName(),searchBeanLocal.getApiName());
			}	
			if (isExists(searchBeanLocal.getTargetEntityQuery()))
			{
				searchParams.put(TaskInfoLabelLocal.gettargetEntityQueryFieldName(),searchBeanLocal.getTargetEntityQuery());
			}	
			if (isExists(searchBeanLocal.getTargetUserIdColumnAlias()))
			{
				searchParams.put(TaskInfoLabelLocal.gettargetUserIdColumnAliasFieldName(),searchBeanLocal.getTargetUserIdColumnAlias());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(TaskInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() TaskInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues TaskInfoContollerBase");
		targetBean = (TaskInfo)targetBean;((TaskInfo)targetBean).setTaskInfoId(((TaskInfo)sourceBean).getTaskInfoId());
				((TaskInfo)targetBean).setTaskName(((TaskInfo)sourceBean).getTaskName());
		((TaskInfo)targetBean).setTaskDescription(((TaskInfo)sourceBean).getTaskDescription());
		((TaskInfo)targetBean).setTaskType(((TaskInfo)sourceBean).getTaskType());
		((TaskInfo)targetBean).setApiName(((TaskInfo)sourceBean).getApiName());
		((TaskInfo)targetBean).setTargetEntityQuery(((TaskInfo)sourceBean).getTargetEntityQuery());
		((TaskInfo)targetBean).setTargetUserIdColumnAlias(((TaskInfo)sourceBean).getTargetUserIdColumnAlias());
		((TaskInfo)targetBean).setTargetEntityIdColumnAlias(((TaskInfo)sourceBean).getTargetEntityIdColumnAlias());
		((TaskInfo)targetBean).setEnableInAppNotification(((TaskInfo)sourceBean).getEnableInAppNotification());
		((TaskInfo)targetBean).setInAppNotificationLayout(((TaskInfo)sourceBean).getInAppNotificationLayout());
		((TaskInfo)targetBean).setEnableEmailNotification(((TaskInfo)sourceBean).getEnableEmailNotification());
		((TaskInfo)targetBean).setEmailColumnAlias(((TaskInfo)sourceBean).getEmailColumnAlias());
		((TaskInfo)targetBean).setEmailNotificationLayout(((TaskInfo)sourceBean).getEmailNotificationLayout());
		((TaskInfo)targetBean).setEmailSubject(((TaskInfo)sourceBean).getEmailSubject());
		((TaskInfo)targetBean).setEnableSmsNotification(((TaskInfo)sourceBean).getEnableSmsNotification());
		((TaskInfo)targetBean).setSmsColumnAlias(((TaskInfo)sourceBean).getSmsColumnAlias());
		((TaskInfo)targetBean).setSmsNotificationLayout(((TaskInfo)sourceBean).getSmsNotificationLayout());
		((TaskInfo)targetBean).setSMSText(((TaskInfo)sourceBean).getSMSText());
		((TaskInfo)targetBean).setIsActive(((TaskInfo)sourceBean).getIsActive());
		((TaskInfo)targetBean).setTaskStartDate(((TaskInfo)sourceBean).getTaskStartDate());
		((TaskInfo)targetBean).setTaskFrequency(((TaskInfo)sourceBean).getTaskFrequency());
		((TaskInfo)targetBean).setTaskFrequencyUnit(((TaskInfo)sourceBean).getTaskFrequencyUnit());
		((TaskInfo)targetBean).setIsRecurring(((TaskInfo)sourceBean).getIsRecurring());
		((TaskInfo)targetBean).setFirstRunType(((TaskInfo)sourceBean).getFirstRunType());
		((TaskInfo)targetBean).setDateColumnAlias(((TaskInfo)sourceBean).getDateColumnAlias());
		((TaskInfo)targetBean).setFirstRunDateCalculationMethod(((TaskInfo)sourceBean).getFirstRunDateCalculationMethod());
		((TaskInfo)targetBean).setFirstRunDateGapInYears(((TaskInfo)sourceBean).getFirstRunDateGapInYears());
		((TaskInfo)targetBean).setFirstRunDateGapInMonths(((TaskInfo)sourceBean).getFirstRunDateGapInMonths());
		((TaskInfo)targetBean).setFirstRunDateGapInDays(((TaskInfo)sourceBean).getFirstRunDateGapInDays());
		((TaskInfo)targetBean).setFirstRunDateGapInHours(((TaskInfo)sourceBean).getFirstRunDateGapInHours());
		((TaskInfo)targetBean).setFirstRunDateGapInMinutes(((TaskInfo)sourceBean).getFirstRunDateGapInMinutes());
		((TaskInfo)targetBean).setFirstRunDateGapInSeconds(((TaskInfo)sourceBean).getFirstRunDateGapInSeconds());

		doAfterSetValues();
		debugCode("Out setValues TaskInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.TaskInfo entityBean = (TaskInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("taskInfoId")) 
	 	{
			return entityBean.getTaskInfoId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("TaskName")) 
	 	{
			return entityBean.getTaskName();
		}
	 	if (sFieldName.equalsIgnoreCase("TaskDescription")) 
	 	{
			return entityBean.getTaskDescription();
		}
	 	if (sFieldName.equalsIgnoreCase("TaskType")) 
	 	{
			return entityBean.getTaskType();
		}
	 	if (sFieldName.equalsIgnoreCase("ApiName")) 
	 	{
			return entityBean.getApiName();
		}
	 	if (sFieldName.equalsIgnoreCase("TargetEntityQuery")) 
	 	{
			return entityBean.getTargetEntityQuery();
		}
	 	if (sFieldName.equalsIgnoreCase("TargetUserIdColumnAlias")) 
	 	{
			return entityBean.getTargetUserIdColumnAlias();
		}
	 	if (sFieldName.equalsIgnoreCase("TargetEntityIdColumnAlias")) 
	 	{
			return entityBean.getTargetEntityIdColumnAlias();
		}
	 	if (sFieldName.equalsIgnoreCase("EnableInAppNotification")) 
	 	{
			return entityBean.getEnableInAppNotification();
		}
	 	if (sFieldName.equalsIgnoreCase("InAppNotificationLayout")) 
	 	{
			return entityBean.getInAppNotificationLayout();
		}
	 	if (sFieldName.equalsIgnoreCase("EnableEmailNotification")) 
	 	{
			return entityBean.getEnableEmailNotification();
		}
	 	if (sFieldName.equalsIgnoreCase("EmailColumnAlias")) 
	 	{
			return entityBean.getEmailColumnAlias();
		}
	 	if (sFieldName.equalsIgnoreCase("EmailNotificationLayout")) 
	 	{
			return entityBean.getEmailNotificationLayout();
		}
	 	if (sFieldName.equalsIgnoreCase("EmailSubject")) 
	 	{
			return entityBean.getEmailSubject();
		}
	 	if (sFieldName.equalsIgnoreCase("EnableSmsNotification")) 
	 	{
			return entityBean.getEnableSmsNotification();
		}
	 	if (sFieldName.equalsIgnoreCase("SmsColumnAlias")) 
	 	{
			return entityBean.getSmsColumnAlias();
		}
	 	if (sFieldName.equalsIgnoreCase("SmsNotificationLayout")) 
	 	{
			return entityBean.getSmsNotificationLayout();
		}
	 	if (sFieldName.equalsIgnoreCase("SMSText")) 
	 	{
			return entityBean.getSMSText();
		}
	 	if (sFieldName.equalsIgnoreCase("IsActive")) 
	 	{
			return entityBean.getIsActive();
		}
	 	if (sFieldName.equalsIgnoreCase("TaskStartDate")) 
	 	{
			return entityBean.getTaskStartDate();
		}
	 	if (sFieldName.equalsIgnoreCase("TaskFrequency")) 
	 	{
			return entityBean.getTaskFrequency();
		}
	 	if (sFieldName.equalsIgnoreCase("TaskFrequencyUnit")) 
	 	{
			return entityBean.getTaskFrequencyUnit();
		}
	 	if (sFieldName.equalsIgnoreCase("IsRecurring")) 
	 	{
			return entityBean.getIsRecurring();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunType")) 
	 	{
			return entityBean.getFirstRunType();
		}
	 	if (sFieldName.equalsIgnoreCase("DateColumnAlias")) 
	 	{
			return entityBean.getDateColumnAlias();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateCalculationMethod")) 
	 	{
			return entityBean.getFirstRunDateCalculationMethod();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInYears")) 
	 	{
			return entityBean.getFirstRunDateGapInYears();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInMonths")) 
	 	{
			return entityBean.getFirstRunDateGapInMonths();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInDays")) 
	 	{
			return entityBean.getFirstRunDateGapInDays();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInHours")) 
	 	{
			return entityBean.getFirstRunDateGapInHours();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInMinutes")) 
	 	{
			return entityBean.getFirstRunDateGapInMinutes();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInSeconds")) 
	 	{
			return entityBean.getFirstRunDateGapInSeconds();
		}

	 return null;
	}
	
	
	protected void doEnrichValues(Boolean doUpdateRules)
	{
		JsonObject paramsInfoObject  = new JsonObject();
		doEnrichValues(doUpdateRules, paramsInfoObject);
	}
	protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject)
	{
		if (doUpdateRules)
		{
			callBusinessUpdateRules();
		}	
		doEnrichValuesImpl(paramsInfoObject);
	}
	protected void doAfterEnrichValues()
	{
		doAfterEnrichValuesImpl();
		doCreateAuditRecord();
	}
	private void doCreateAuditRecord()
	{
		
	}	
	protected void doAfterSelectRow()
	{
		doAfterSelectRowImpl();
	}
	protected void doEnrichSystemValues(String sAction,String sNextStatus)
	{
		debugCode("In doEnrichSystemValues(String sAction) TaskInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((TaskInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((TaskInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((TaskInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((TaskInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((TaskInfo) localManagedBean).setVwTxnStatus("CREATED");
			((TaskInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((TaskInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((TaskInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) TaskInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) TaskInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) TaskInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations TaskInfoControllerBase");
		//NG: Important comment
		//managedBean = (TaskInfo) getManagedBean();
		localManagedBean = getLocalManagedBean();
		setPrivateManagedBean(localManagedBean);
		doDataTypeValidation();
		doLengthValidation();
		doMandatoryValidation();
		doAllowedValuesValidation();
		doAllowedDecimalValidation();
		if (!isFromChild())
		{
			doUniqueValidation();
		}	
		callBusinessValidateRules();
		doValidationsImpl();
		debugCode("Out doValidations TaskInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation TaskInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation TaskInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation TaskInfoControllerBase");
				
		String sFieldValue4 = ((TaskInfo) localManagedBean).getTaskType();if (!isExists(sFieldValue4,localMasters.getTaskType())) addAllowedValuesResponse(TaskInfoLabelLocal.gettaskTypeFieldName());String sFieldValue9 = ((TaskInfo) localManagedBean).getEnableInAppNotification();if (!isExists(sFieldValue9,localMasters.getYesNoOption())) addAllowedValuesResponse(TaskInfoLabelLocal.getenableInAppNotificationFieldName());String sFieldValue11 = ((TaskInfo) localManagedBean).getEnableEmailNotification();if (!isExists(sFieldValue11,localMasters.getYesNoOption())) addAllowedValuesResponse(TaskInfoLabelLocal.getenableEmailNotificationFieldName());String sFieldValue15 = ((TaskInfo) localManagedBean).getEnableSmsNotification();if (!isExists(sFieldValue15,localMasters.getYesNoOption())) addAllowedValuesResponse(TaskInfoLabelLocal.getenableSmsNotificationFieldName());String sFieldValue19 = ((TaskInfo) localManagedBean).getIsActive();if (!isExists(sFieldValue19,localMasters.getYesNoOption())) addAllowedValuesResponse(TaskInfoLabelLocal.getisActiveFieldName());String sFieldValue22 = ((TaskInfo) localManagedBean).getTaskFrequencyUnit();if (!isExists(sFieldValue22,localMasters.getDurationUnitType())) addAllowedValuesResponse(TaskInfoLabelLocal.gettaskFrequencyUnitFieldName());String sFieldValue23 = ((TaskInfo) localManagedBean).getIsRecurring();if (!isExists(sFieldValue23,localMasters.getYesNoOption())) addAllowedValuesResponse(TaskInfoLabelLocal.getisRecurringFieldName());String sFieldValue24 = ((TaskInfo) localManagedBean).getFirstRunType();if (!isExists(sFieldValue24,localMasters.getFirstRunType())) addAllowedValuesResponse(TaskInfoLabelLocal.getfirstRunTypeFieldName());String sFieldValue26 = ((TaskInfo) localManagedBean).getFirstRunDateCalculationMethod();if (!isExists(sFieldValue26,localMasters.getFirstRunDateCalculationMethod())) addAllowedValuesResponse(TaskInfoLabelLocal.getfirstRunDateCalculationMethodFieldName());

		debugCode("Out doAllowedValuesValidation TaskInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation TaskInfoControllerBase");
				
		String sFieldValue2 = ((TaskInfo) localManagedBean).getTaskName();String sFieldValue4 = ((TaskInfo) localManagedBean).getTaskType();String sFieldValue19 = ((TaskInfo) localManagedBean).getIsActive();Integer sFieldValue21 = ((TaskInfo) localManagedBean).getTaskFrequency();		String sFieldValue22 = ((TaskInfo) localManagedBean).getTaskFrequencyUnit();String sFieldValue23 = ((TaskInfo) localManagedBean).getIsRecurring();String sFieldValue24 = ((TaskInfo) localManagedBean).getFirstRunType();
		if (!isExists(sFieldValue2)) addMandatoryResponse(TaskInfoLabelLocal.gettaskNameFieldName());
		if (!isExists(sFieldValue4)) addMandatoryResponse(TaskInfoLabelLocal.gettaskTypeFieldName());
		if (!isExists(sFieldValue19)) addMandatoryResponse(TaskInfoLabelLocal.getisActiveFieldName());
		if (!isExists(sFieldValue21)) addMandatoryResponse(TaskInfoLabelLocal.gettaskFrequencyFieldName());
		if (!isExists(sFieldValue22)) addMandatoryResponse(TaskInfoLabelLocal.gettaskFrequencyUnitFieldName());
		if (!isExists(sFieldValue23)) addMandatoryResponse(TaskInfoLabelLocal.getisRecurringFieldName());
		if (!isExists(sFieldValue24)) addMandatoryResponse(TaskInfoLabelLocal.getfirstRunTypeFieldName());
debugCode("Out doMandatoryValidation TaskInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation TaskInfoControllerBase");
				
		String sFieldValue2 = ((TaskInfo) localManagedBean).getTaskName();String sFieldValue3 = ((TaskInfo) localManagedBean).getTaskDescription();String sFieldValue4 = ((TaskInfo) localManagedBean).getTaskType();String sFieldValue5 = ((TaskInfo) localManagedBean).getApiName();String sFieldValue6 = ((TaskInfo) localManagedBean).getTargetEntityQuery();String sFieldValue7 = ((TaskInfo) localManagedBean).getTargetUserIdColumnAlias();String sFieldValue8 = ((TaskInfo) localManagedBean).getTargetEntityIdColumnAlias();String sFieldValue9 = ((TaskInfo) localManagedBean).getEnableInAppNotification();String sFieldValue10 = ((TaskInfo) localManagedBean).getInAppNotificationLayout();String sFieldValue11 = ((TaskInfo) localManagedBean).getEnableEmailNotification();String sFieldValue12 = ((TaskInfo) localManagedBean).getEmailColumnAlias();String sFieldValue13 = ((TaskInfo) localManagedBean).getEmailNotificationLayout();String sFieldValue14 = ((TaskInfo) localManagedBean).getEmailSubject();String sFieldValue15 = ((TaskInfo) localManagedBean).getEnableSmsNotification();String sFieldValue16 = ((TaskInfo) localManagedBean).getSmsColumnAlias();String sFieldValue17 = ((TaskInfo) localManagedBean).getSmsNotificationLayout();String sFieldValue18 = ((TaskInfo) localManagedBean).getSMSText();String sFieldValue19 = ((TaskInfo) localManagedBean).getIsActive();
		
		Date sFieldValue20 = ((TaskInfo) localManagedBean).getTaskStartDate();Integer sFieldValue21 = ((TaskInfo) localManagedBean).getTaskFrequency();		String sFieldValue22 = ((TaskInfo) localManagedBean).getTaskFrequencyUnit();String sFieldValue23 = ((TaskInfo) localManagedBean).getIsRecurring();String sFieldValue24 = ((TaskInfo) localManagedBean).getFirstRunType();String sFieldValue25 = ((TaskInfo) localManagedBean).getDateColumnAlias();String sFieldValue26 = ((TaskInfo) localManagedBean).getFirstRunDateCalculationMethod();Integer sFieldValue27 = ((TaskInfo) localManagedBean).getFirstRunDateGapInYears();		Integer sFieldValue28 = ((TaskInfo) localManagedBean).getFirstRunDateGapInMonths();		Integer sFieldValue29 = ((TaskInfo) localManagedBean).getFirstRunDateGapInDays();		Integer sFieldValue30 = ((TaskInfo) localManagedBean).getFirstRunDateGapInHours();		Integer sFieldValue31 = ((TaskInfo) localManagedBean).getFirstRunDateGapInMinutes();		Integer sFieldValue32 = ((TaskInfo) localManagedBean).getFirstRunDateGapInSeconds();		
		if (!isLengthAllowed(sFieldValue2,"500")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskNameFieldName(),"500");
		if (!isLengthAllowed(sFieldValue3,"500")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskDescriptionFieldName(),"500");
		if (!isLengthAllowed(sFieldValue4,"20")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskTypeFieldName(),"20");
		if (!isLengthAllowed(sFieldValue5,"500")) addMaxLengthResponse(TaskInfoLabelLocal.getapiNameFieldName(),"500");
		if (!isLengthAllowed(sFieldValue6,"100")) addMaxLengthResponse(TaskInfoLabelLocal.gettargetEntityQueryFieldName(),"100");
		if (!isLengthAllowed(sFieldValue7,"100")) addMaxLengthResponse(TaskInfoLabelLocal.gettargetUserIdColumnAliasFieldName(),"100");
		if (!isLengthAllowed(sFieldValue8,"100")) addMaxLengthResponse(TaskInfoLabelLocal.gettargetEntityIdColumnAliasFieldName(),"100");
		if (!isLengthAllowed(sFieldValue9,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getenableInAppNotificationFieldName(),"20");
		if (!isLengthAllowed(sFieldValue10,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getinAppNotificationLayoutFieldName(),"100");
		if (!isLengthAllowed(sFieldValue11,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getenableEmailNotificationFieldName(),"20");
		if (!isLengthAllowed(sFieldValue12,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getemailColumnAliasFieldName(),"100");
		if (!isLengthAllowed(sFieldValue13,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getemailNotificationLayoutFieldName(),"100");
		if (!isLengthAllowed(sFieldValue14,"500")) addMaxLengthResponse(TaskInfoLabelLocal.getemailSubjectFieldName(),"500");
		if (!isLengthAllowed(sFieldValue15,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getenableSmsNotificationFieldName(),"20");
		if (!isLengthAllowed(sFieldValue16,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getsmsColumnAliasFieldName(),"100");
		if (!isLengthAllowed(sFieldValue17,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getsmsNotificationLayoutFieldName(),"100");
		if (!isLengthAllowed(sFieldValue18,"500")) addMaxLengthResponse(TaskInfoLabelLocal.getsMSTextFieldName(),"500");
		if (!isLengthAllowed(sFieldValue19,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getisActiveFieldName(),"20");
		if (!isLengthAllowed(sFieldValue20,"50")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskStartDateFieldName(),"50");
		if (!isLengthAllowed(sFieldValue21,"10")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskFrequencyFieldName(),"10");
		if (!isLengthAllowed(sFieldValue22,"20")) addMaxLengthResponse(TaskInfoLabelLocal.gettaskFrequencyUnitFieldName(),"20");
		if (!isLengthAllowed(sFieldValue23,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getisRecurringFieldName(),"20");
		if (!isLengthAllowed(sFieldValue24,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunTypeFieldName(),"20");
		if (!isLengthAllowed(sFieldValue25,"100")) addMaxLengthResponse(TaskInfoLabelLocal.getdateColumnAliasFieldName(),"100");
		if (!isLengthAllowed(sFieldValue26,"20")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateCalculationMethodFieldName(),"20");
		if (!isLengthAllowed(sFieldValue27,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInYearsFieldName(),"10");
		if (!isLengthAllowed(sFieldValue28,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInMonthsFieldName(),"10");
		if (!isLengthAllowed(sFieldValue29,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInDaysFieldName(),"10");
		if (!isLengthAllowed(sFieldValue30,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInHoursFieldName(),"10");
		if (!isLengthAllowed(sFieldValue31,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInMinutesFieldName(),"10");
		if (!isLengthAllowed(sFieldValue32,"10")) addMaxLengthResponse(TaskInfoLabelLocal.getfirstRunDateGapInSecondsFieldName(),"10");
debugCode("Out doLengthValidation TaskInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation TaskInfoControllerBase");
		debugCode("Out doDataTypeValidation TaskInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation TaskInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation TaskInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel TaskInfoContollerBase");
		String sLabel = new TaskInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel TaskInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int taskInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("taskInfoId", taskInfoId);
			JsonObject responseData = retrieveTaskInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'TaskInfo' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject taskInfoDataObject = responseData.get("taskInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, taskInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'TaskInfo' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveTaskInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer taskInfoId = -1;
		if(requestParams.has("taskInfoId"))
		{
			taskInfoId = requestParams.get("taskInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("taskInfoId", String.valueOf(taskInfoId));
				String taskName = "";
		if(requestParams.has("taskName"))
		{
			paramsMap.put("taskName", requestParams.get("taskName").getAsString());
		}
		String taskDescription = "";
		if(requestParams.has("taskDescription"))
		{
			paramsMap.put("taskDescription", requestParams.get("taskDescription").getAsString());
		}
		String taskType = "";
		if(requestParams.has("taskType"))
		{
			paramsMap.put("taskType", requestParams.get("taskType").getAsString());
		}
		String apiName = "";
		if(requestParams.has("apiName"))
		{
			paramsMap.put("apiName", requestParams.get("apiName").getAsString());
		}
		String targetEntityQuery = "";
		if(requestParams.has("targetEntityQuery"))
		{
			paramsMap.put("targetEntityQuery", requestParams.get("targetEntityQuery").getAsString());
		}
		String targetUserIdColumnAlias = "";
		if(requestParams.has("targetUserIdColumnAlias"))
		{
			paramsMap.put("targetUserIdColumnAlias", requestParams.get("targetUserIdColumnAlias").getAsString());
		}
		String targetEntityIdColumnAlias = "";
		if(requestParams.has("targetEntityIdColumnAlias"))
		{
			paramsMap.put("targetEntityIdColumnAlias", requestParams.get("targetEntityIdColumnAlias").getAsString());
		}
		String enableInAppNotification = "";
		if(requestParams.has("enableInAppNotification"))
		{
			paramsMap.put("enableInAppNotification", requestParams.get("enableInAppNotification").getAsString());
		}
		String inAppNotificationLayout = "";
		if(requestParams.has("inAppNotificationLayout"))
		{
			paramsMap.put("inAppNotificationLayout", requestParams.get("inAppNotificationLayout").getAsString());
		}
		String enableEmailNotification = "";
		if(requestParams.has("enableEmailNotification"))
		{
			paramsMap.put("enableEmailNotification", requestParams.get("enableEmailNotification").getAsString());
		}
		String emailColumnAlias = "";
		if(requestParams.has("emailColumnAlias"))
		{
			paramsMap.put("emailColumnAlias", requestParams.get("emailColumnAlias").getAsString());
		}
		String emailNotificationLayout = "";
		if(requestParams.has("emailNotificationLayout"))
		{
			paramsMap.put("emailNotificationLayout", requestParams.get("emailNotificationLayout").getAsString());
		}
		String emailSubject = "";
		if(requestParams.has("emailSubject"))
		{
			paramsMap.put("emailSubject", requestParams.get("emailSubject").getAsString());
		}
		String enableSmsNotification = "";
		if(requestParams.has("enableSmsNotification"))
		{
			paramsMap.put("enableSmsNotification", requestParams.get("enableSmsNotification").getAsString());
		}
		String smsColumnAlias = "";
		if(requestParams.has("smsColumnAlias"))
		{
			paramsMap.put("smsColumnAlias", requestParams.get("smsColumnAlias").getAsString());
		}
		String smsNotificationLayout = "";
		if(requestParams.has("smsNotificationLayout"))
		{
			paramsMap.put("smsNotificationLayout", requestParams.get("smsNotificationLayout").getAsString());
		}
		String sMSText = "";
		if(requestParams.has("sMSText"))
		{
			paramsMap.put("sMSText", requestParams.get("sMSText").getAsString());
		}
		String isActive = "";
		if(requestParams.has("isActive"))
		{
			paramsMap.put("isActive", requestParams.get("isActive").getAsString());
		}
		String taskStartDate = "";
		if(requestParams.has("taskStartDate"))
		{
			paramsMap.put("taskStartDate", requestParams.get("taskStartDate").getAsString());
		}
		String taskFrequency = "";
		if(requestParams.has("taskFrequency"))
		{
			paramsMap.put("taskFrequency", requestParams.get("taskFrequency").getAsString());
		}
		String taskFrequencyUnit = "";
		if(requestParams.has("taskFrequencyUnit"))
		{
			paramsMap.put("taskFrequencyUnit", requestParams.get("taskFrequencyUnit").getAsString());
		}
		String isRecurring = "";
		if(requestParams.has("isRecurring"))
		{
			paramsMap.put("isRecurring", requestParams.get("isRecurring").getAsString());
		}
		String firstRunType = "";
		if(requestParams.has("firstRunType"))
		{
			paramsMap.put("firstRunType", requestParams.get("firstRunType").getAsString());
		}
		String dateColumnAlias = "";
		if(requestParams.has("dateColumnAlias"))
		{
			paramsMap.put("dateColumnAlias", requestParams.get("dateColumnAlias").getAsString());
		}
		String firstRunDateCalculationMethod = "";
		if(requestParams.has("firstRunDateCalculationMethod"))
		{
			paramsMap.put("firstRunDateCalculationMethod", requestParams.get("firstRunDateCalculationMethod").getAsString());
		}
		String firstRunDateGapInYears = "";
		if(requestParams.has("firstRunDateGapInYears"))
		{
			paramsMap.put("firstRunDateGapInYears", requestParams.get("firstRunDateGapInYears").getAsString());
		}
		String firstRunDateGapInMonths = "";
		if(requestParams.has("firstRunDateGapInMonths"))
		{
			paramsMap.put("firstRunDateGapInMonths", requestParams.get("firstRunDateGapInMonths").getAsString());
		}
		String firstRunDateGapInDays = "";
		if(requestParams.has("firstRunDateGapInDays"))
		{
			paramsMap.put("firstRunDateGapInDays", requestParams.get("firstRunDateGapInDays").getAsString());
		}
		String firstRunDateGapInHours = "";
		if(requestParams.has("firstRunDateGapInHours"))
		{
			paramsMap.put("firstRunDateGapInHours", requestParams.get("firstRunDateGapInHours").getAsString());
		}
		String firstRunDateGapInMinutes = "";
		if(requestParams.has("firstRunDateGapInMinutes"))
		{
			paramsMap.put("firstRunDateGapInMinutes", requestParams.get("firstRunDateGapInMinutes").getAsString());
		}
		String firstRunDateGapInSeconds = "";
		if(requestParams.has("firstRunDateGapInSeconds"))
		{
			paramsMap.put("firstRunDateGapInSeconds", requestParams.get("firstRunDateGapInSeconds").getAsString());
		}

		JsonObject taskInfoListObject = retrieveTaskInfoList(paramsMap);
		if(taskInfoListObject!=null && taskInfoListObject.has("success") && taskInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskInfoList = taskInfoListObject.get("taskInfoList").getAsJsonArray();
			if(taskInfoList.size()>0)
			{
				dataObject.add("taskInfoDataObject", taskInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'TaskInfo' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getTaskInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			Map<String, String> paramsMap = new HashMap<String, String>();
						String taskName = "";
			if(searchParams.has("taskName"))
			{
				paramsMap.put("taskName", searchParams.get("taskName").getAsString());
			}
			String taskDescription = "";
			if(searchParams.has("taskDescription"))
			{
				paramsMap.put("taskDescription", searchParams.get("taskDescription").getAsString());
			}
			String taskType = "";
			if(searchParams.has("taskType"))
			{
				paramsMap.put("taskType", searchParams.get("taskType").getAsString());
			}
			String apiName = "";
			if(searchParams.has("apiName"))
			{
				paramsMap.put("apiName", searchParams.get("apiName").getAsString());
			}
			String targetEntityQuery = "";
			if(searchParams.has("targetEntityQuery"))
			{
				paramsMap.put("targetEntityQuery", searchParams.get("targetEntityQuery").getAsString());
			}
			String targetUserIdColumnAlias = "";
			if(searchParams.has("targetUserIdColumnAlias"))
			{
				paramsMap.put("targetUserIdColumnAlias", searchParams.get("targetUserIdColumnAlias").getAsString());
			}
			String targetEntityIdColumnAlias = "";
			if(searchParams.has("targetEntityIdColumnAlias"))
			{
				paramsMap.put("targetEntityIdColumnAlias", searchParams.get("targetEntityIdColumnAlias").getAsString());
			}
			String enableInAppNotification = "";
			if(searchParams.has("enableInAppNotification"))
			{
				paramsMap.put("enableInAppNotification", searchParams.get("enableInAppNotification").getAsString());
			}
			String inAppNotificationLayout = "";
			if(searchParams.has("inAppNotificationLayout"))
			{
				paramsMap.put("inAppNotificationLayout", searchParams.get("inAppNotificationLayout").getAsString());
			}
			String enableEmailNotification = "";
			if(searchParams.has("enableEmailNotification"))
			{
				paramsMap.put("enableEmailNotification", searchParams.get("enableEmailNotification").getAsString());
			}
			String emailColumnAlias = "";
			if(searchParams.has("emailColumnAlias"))
			{
				paramsMap.put("emailColumnAlias", searchParams.get("emailColumnAlias").getAsString());
			}
			String emailNotificationLayout = "";
			if(searchParams.has("emailNotificationLayout"))
			{
				paramsMap.put("emailNotificationLayout", searchParams.get("emailNotificationLayout").getAsString());
			}
			String emailSubject = "";
			if(searchParams.has("emailSubject"))
			{
				paramsMap.put("emailSubject", searchParams.get("emailSubject").getAsString());
			}
			String enableSmsNotification = "";
			if(searchParams.has("enableSmsNotification"))
			{
				paramsMap.put("enableSmsNotification", searchParams.get("enableSmsNotification").getAsString());
			}
			String smsColumnAlias = "";
			if(searchParams.has("smsColumnAlias"))
			{
				paramsMap.put("smsColumnAlias", searchParams.get("smsColumnAlias").getAsString());
			}
			String smsNotificationLayout = "";
			if(searchParams.has("smsNotificationLayout"))
			{
				paramsMap.put("smsNotificationLayout", searchParams.get("smsNotificationLayout").getAsString());
			}
			String sMSText = "";
			if(searchParams.has("sMSText"))
			{
				paramsMap.put("sMSText", searchParams.get("sMSText").getAsString());
			}
			String isActive = "";
			if(searchParams.has("isActive"))
			{
				paramsMap.put("isActive", searchParams.get("isActive").getAsString());
			}
			String taskStartDate = "";
			if(searchParams.has("taskStartDate"))
			{
				paramsMap.put("taskStartDate", searchParams.get("taskStartDate").getAsString());
			}
			String taskFrequency = "";
			if(searchParams.has("taskFrequency"))
			{
				paramsMap.put("taskFrequency", searchParams.get("taskFrequency").getAsString());
			}
			String taskFrequencyUnit = "";
			if(searchParams.has("taskFrequencyUnit"))
			{
				paramsMap.put("taskFrequencyUnit", searchParams.get("taskFrequencyUnit").getAsString());
			}
			String isRecurring = "";
			if(searchParams.has("isRecurring"))
			{
				paramsMap.put("isRecurring", searchParams.get("isRecurring").getAsString());
			}
			String firstRunType = "";
			if(searchParams.has("firstRunType"))
			{
				paramsMap.put("firstRunType", searchParams.get("firstRunType").getAsString());
			}
			String dateColumnAlias = "";
			if(searchParams.has("dateColumnAlias"))
			{
				paramsMap.put("dateColumnAlias", searchParams.get("dateColumnAlias").getAsString());
			}
			String firstRunDateCalculationMethod = "";
			if(searchParams.has("firstRunDateCalculationMethod"))
			{
				paramsMap.put("firstRunDateCalculationMethod", searchParams.get("firstRunDateCalculationMethod").getAsString());
			}
			String firstRunDateGapInYears = "";
			if(searchParams.has("firstRunDateGapInYears"))
			{
				paramsMap.put("firstRunDateGapInYears", searchParams.get("firstRunDateGapInYears").getAsString());
			}
			String firstRunDateGapInMonths = "";
			if(searchParams.has("firstRunDateGapInMonths"))
			{
				paramsMap.put("firstRunDateGapInMonths", searchParams.get("firstRunDateGapInMonths").getAsString());
			}
			String firstRunDateGapInDays = "";
			if(searchParams.has("firstRunDateGapInDays"))
			{
				paramsMap.put("firstRunDateGapInDays", searchParams.get("firstRunDateGapInDays").getAsString());
			}
			String firstRunDateGapInHours = "";
			if(searchParams.has("firstRunDateGapInHours"))
			{
				paramsMap.put("firstRunDateGapInHours", searchParams.get("firstRunDateGapInHours").getAsString());
			}
			String firstRunDateGapInMinutes = "";
			if(searchParams.has("firstRunDateGapInMinutes"))
			{
				paramsMap.put("firstRunDateGapInMinutes", searchParams.get("firstRunDateGapInMinutes").getAsString());
			}
			String firstRunDateGapInSeconds = "";
			if(searchParams.has("firstRunDateGapInSeconds"))
			{
				paramsMap.put("firstRunDateGapInSeconds", searchParams.get("firstRunDateGapInSeconds").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject taskInfoListObject = retrieveTaskInfoList(paramsMap);
			if(taskInfoListObject!=null && taskInfoListObject.has("success") && taskInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray taskInfoList = taskInfoListObject.get("taskInfoList").getAsJsonArray();
				if(taskInfoList.size()>0)
				{
					dataObject.add("taskInfo", taskInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'TaskInfo' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getTaskInfoInJson(int taskInfoId)
	{
		JsonObject TaskInfoData = null;
		List<Integer> taskInfoIdsList = new ArrayList<>();
		taskInfoIdsList.add(taskInfoId);
		JsonArray taskInfoList = getTaskInfoListInJson(taskInfoIdsList);
		if(taskInfoList!=null && taskInfoList.size()>0)
		{
			TaskInfoData = taskInfoList.get(0).getAsJsonObject();
		}
		return TaskInfoData;
	}
	public JsonArray getTaskInfoListInJson(List<Integer> taskInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray taskInfoObjectsList = new JsonArray();
		JsonObject taskInfoListObject = retrieveTaskInfoList(paramsMap, taskInfoIdsList);
		if(taskInfoListObject!=null && taskInfoListObject.has("success") && taskInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskInfoList = taskInfoListObject.get("taskInfoList").getAsJsonArray();
			for (int i =0; i< taskInfoList.size(); i++)
			{
				JsonObject taskInfoDataObject = taskInfoList.get(i).getAsJsonObject();
				int taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
								com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray taskExecutionInfoList = taskExecutionInfoImplObj.getTaskExecutionInfoListFromParentInJson(taskInfoId);
				taskInfoDataObject.add("taskExecutionInfoList", taskExecutionInfoList);
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray taskLayoutParametersList = taskLayoutParametersImplObj.getTaskLayoutParametersListFromParentInJson(taskInfoId);
				taskInfoDataObject.add("taskLayoutParametersList", taskLayoutParametersList);
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray emailAttachmentLayoutList = emailAttachmentLayoutImplObj.getEmailAttachmentLayoutListFromParentInJson(taskInfoId);
				taskInfoDataObject.add("emailAttachmentLayoutList", emailAttachmentLayoutList);

				taskInfoObjectsList.add(taskInfoDataObject);
			}
		}
		return taskInfoObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray taskInfoList)
	{
		String errorMessage = "taskInfoList: \n";
		for (int i =0; i< taskInfoList.size(); i++)
		{
			JsonObject taskInfoDataObject = taskInfoList.get(i).getAsJsonObject();
			JsonObject taskInfo = taskInfoDataObject.get("dataObject").getAsJsonObject();if(!taskInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "taskInfo could not be uploaded verify data \n"; 
			}
			else if(taskInfoDataObject.has("isSuccessfullyUploaded") 
					&& taskInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += taskInfoDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    		    if(taskInfo.has("taskExecutionInfoList"))
		    {
			    JsonArray taskExecutionInfoList = taskInfo.get("taskExecutionInfoList").getAsJsonArray();
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);		    
				errorMessage += taskExecutionInfoImplObj.getUploadedDataErrorMessage(session, taskExecutionInfoList);
		    }
		    if(taskInfo.has("taskLayoutParametersList"))
		    {
			    JsonArray taskLayoutParametersList = taskInfo.get("taskLayoutParametersList").getAsJsonArray();
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);		    
				errorMessage += taskLayoutParametersImplObj.getUploadedDataErrorMessage(session, taskLayoutParametersList);
		    }
		    if(taskInfo.has("emailAttachmentLayoutList"))
		    {
			    JsonArray emailAttachmentLayoutList = taskInfo.get("emailAttachmentLayoutList").getAsJsonArray();
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);		    
				errorMessage += emailAttachmentLayoutImplObj.getUploadedDataErrorMessage(session, emailAttachmentLayoutList);
		    }

		}
		return errorMessage;		
	}
	public String getLoginBasedWhereClause(java.util.Map<String, String> paramsMap)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND taskInfoId = :taskInfoId ";
			return loginBasedWhereClause;
		}return "";		
	}
	public void setLoginBasedWhereClauseParams(java.util.Map<String, String> paramsMap, Query query)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskInfoId", userId);
		}
		
	}
	public String getParentFilterClauseForTaskInfo(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";return parentFilterClause;
	}
	public String getLookupDisplayFilterClause()
	{
		String lookupDisplayFilterClause = "";
		String lookupDisplayQueryColumn = " AND concat(";
		int i= 0;
				if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "taskName";
		i++;
		if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "taskDescription";
		i++;
		if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "apiName";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForTaskInfo(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveTaskInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> taskInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("taskInfoId"))
		{
			int taskInfoId = Integer.parseInt(paramsMap.get("taskInfoId"));
			if(taskInfoId>0)
			{
				taskInfoIdsList.add(taskInfoId);
			}
		}
		return retrieveTaskInfoList(paramsMap, taskInfoIdsList);
	}
	public JsonObject retrieveTaskInfoList(java.util.Map<String, String> paramsMap, List<Integer> taskInfoIdsList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			int noOfRecordsAlreadyFetched = 0;
			int noOfRecordsToFetch = 0;
			String overrideWhereClause="";
			String whereClause ="";
			String orderByClause = "";
			String overrideOrderByClause="";
			String lookupDisplayPrefix="";
			String txnStatus="";
			JsonArray additionalParamsList = new JsonArray();
			if(paramsMap.containsKey("noOfRecordsAlreadyFetched"))
			{
				noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));				
			}
			if(paramsMap.containsKey("noOfRecordsToFetch"))
			{
				noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));				
			}
			if(paramsMap.containsKey("overrideWhereClause"))
			{
				overrideWhereClause = paramsMap.get("overrideWhereClause");				
			}
			if(paramsMap.containsKey("whereClause"))
			{
				whereClause = paramsMap.get("whereClause");				
			}
			if(paramsMap.containsKey("orderByClause"))
			{
				orderByClause = paramsMap.get("orderByClause");				
			}
			if(paramsMap.containsKey("overrideOrderByClause"))
			{
				overrideOrderByClause = paramsMap.get("overrideOrderByClause");				
			}
			if(paramsMap.containsKey("lookupDisplayPrefix"))
			{
				lookupDisplayPrefix = paramsMap.get("lookupDisplayPrefix");
			}
			if(paramsMap.containsKey("additionalParamsList"))
			{
				additionalParamsList = new Gson().fromJson(paramsMap.get("additionalParamsList"), JsonArray.class);
			}
			if(paramsMap.containsKey("txnStatus"))
			{
				txnStatus = paramsMap.get("txnStatus");
			}
						String taskName = paramsMap.get("taskName");
			if(taskName==null)
			{
				taskName = "";
			}
			String taskDescription = paramsMap.get("taskDescription");
			if(taskDescription==null)
			{
				taskDescription = "";
			}
			String taskType = paramsMap.get("taskType");
			if(taskType==null)
			{
				taskType = "";
			}
			String apiName = paramsMap.get("apiName");
			if(apiName==null)
			{
				apiName = "";
			}
			String targetEntityQuery = paramsMap.get("targetEntityQuery");
			if(targetEntityQuery==null)
			{
				targetEntityQuery = "";
			}
			String targetUserIdColumnAlias = paramsMap.get("targetUserIdColumnAlias");
			if(targetUserIdColumnAlias==null)
			{
				targetUserIdColumnAlias = "";
			}
			String targetEntityIdColumnAlias = paramsMap.get("targetEntityIdColumnAlias");
			if(targetEntityIdColumnAlias==null)
			{
				targetEntityIdColumnAlias = "";
			}
			String enableInAppNotification = paramsMap.get("enableInAppNotification");
			if(enableInAppNotification==null)
			{
				enableInAppNotification = "";
			}
			String inAppNotificationLayout = paramsMap.get("inAppNotificationLayout");
			if(inAppNotificationLayout==null)
			{
				inAppNotificationLayout = "";
			}
			String enableEmailNotification = paramsMap.get("enableEmailNotification");
			if(enableEmailNotification==null)
			{
				enableEmailNotification = "";
			}
			String emailColumnAlias = paramsMap.get("emailColumnAlias");
			if(emailColumnAlias==null)
			{
				emailColumnAlias = "";
			}
			String emailNotificationLayout = paramsMap.get("emailNotificationLayout");
			if(emailNotificationLayout==null)
			{
				emailNotificationLayout = "";
			}
			String emailSubject = paramsMap.get("emailSubject");
			if(emailSubject==null)
			{
				emailSubject = "";
			}
			String enableSmsNotification = paramsMap.get("enableSmsNotification");
			if(enableSmsNotification==null)
			{
				enableSmsNotification = "";
			}
			String smsColumnAlias = paramsMap.get("smsColumnAlias");
			if(smsColumnAlias==null)
			{
				smsColumnAlias = "";
			}
			String smsNotificationLayout = paramsMap.get("smsNotificationLayout");
			if(smsNotificationLayout==null)
			{
				smsNotificationLayout = "";
			}
			String sMSText = paramsMap.get("sMSText");
			if(sMSText==null)
			{
				sMSText = "";
			}
			String isActive = paramsMap.get("isActive");
			if(isActive==null)
			{
				isActive = "";
			}
			String taskStartDate = paramsMap.get("taskStartDate");
			if(taskStartDate==null)
			{
				taskStartDate = "";
			}
			String taskFrequency = paramsMap.get("taskFrequency");
			if(taskFrequency==null)
			{
				taskFrequency = "";
			}
			String taskFrequencyUnit = paramsMap.get("taskFrequencyUnit");
			if(taskFrequencyUnit==null)
			{
				taskFrequencyUnit = "";
			}
			String isRecurring = paramsMap.get("isRecurring");
			if(isRecurring==null)
			{
				isRecurring = "";
			}
			String firstRunType = paramsMap.get("firstRunType");
			if(firstRunType==null)
			{
				firstRunType = "";
			}
			String dateColumnAlias = paramsMap.get("dateColumnAlias");
			if(dateColumnAlias==null)
			{
				dateColumnAlias = "";
			}
			String firstRunDateCalculationMethod = paramsMap.get("firstRunDateCalculationMethod");
			if(firstRunDateCalculationMethod==null)
			{
				firstRunDateCalculationMethod = "";
			}
			String firstRunDateGapInYears = paramsMap.get("firstRunDateGapInYears");
			if(firstRunDateGapInYears==null)
			{
				firstRunDateGapInYears = "";
			}
			String firstRunDateGapInMonths = paramsMap.get("firstRunDateGapInMonths");
			if(firstRunDateGapInMonths==null)
			{
				firstRunDateGapInMonths = "";
			}
			String firstRunDateGapInDays = paramsMap.get("firstRunDateGapInDays");
			if(firstRunDateGapInDays==null)
			{
				firstRunDateGapInDays = "";
			}
			String firstRunDateGapInHours = paramsMap.get("firstRunDateGapInHours");
			if(firstRunDateGapInHours==null)
			{
				firstRunDateGapInHours = "";
			}
			String firstRunDateGapInMinutes = paramsMap.get("firstRunDateGapInMinutes");
			if(firstRunDateGapInMinutes==null)
			{
				firstRunDateGapInMinutes = "";
			}
			String firstRunDateGapInSeconds = paramsMap.get("firstRunDateGapInSeconds");
			if(firstRunDateGapInSeconds==null)
			{
				firstRunDateGapInSeconds = "";
			}
String hql = "FROM TaskInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String taskInfoIdFilterClass = "";
			if (taskInfoIdsList != null && taskInfoIdsList.size() > 0)
			{
				taskInfoIdFilterClass = " AND taskInfoId in (:idsList) ";
			}
						String taskNameFilterClass = "";
			if (taskName.length() > 0)
			{		
				
				taskNameFilterClass = " AND taskName LIKE :taskName ";	
				
			}
			String taskDescriptionFilterClass = "";
			if (taskDescription.length() > 0)
			{		
				
				taskDescriptionFilterClass = " AND taskDescription LIKE :taskDescription ";	
				
			}
			String taskTypeFilterClass = "";
			if (taskType.length() > 0)
			{		
				
				taskTypeFilterClass = " AND taskType LIKE :taskType ";	
				
			}
			String apiNameFilterClass = "";
			if (apiName.length() > 0)
			{		
				
				apiNameFilterClass = " AND apiName LIKE :apiName ";	
				
			}
			String targetEntityQueryFilterClass = "";
			if (targetEntityQuery.length() > 0)
			{		
				
				targetEntityQueryFilterClass = " AND targetEntityQuery LIKE :targetEntityQuery ";	
				
			}
			String targetUserIdColumnAliasFilterClass = "";
			if (targetUserIdColumnAlias.length() > 0)
			{		
				
				targetUserIdColumnAliasFilterClass = " AND targetUserIdColumnAlias LIKE :targetUserIdColumnAlias ";	
				
			}
			String targetEntityIdColumnAliasFilterClass = "";
			if (targetEntityIdColumnAlias.length() > 0)
			{		
				
				targetEntityIdColumnAliasFilterClass = " AND targetEntityIdColumnAlias LIKE :targetEntityIdColumnAlias ";	
				
			}
			String enableInAppNotificationFilterClass = "";
			if (enableInAppNotification.length() > 0)
			{		
				
				enableInAppNotificationFilterClass = " AND enableInAppNotification LIKE :enableInAppNotification ";	
				
			}
			String inAppNotificationLayoutFilterClass = "";
			if (inAppNotificationLayout.length() > 0)
			{		
				
				inAppNotificationLayoutFilterClass = " AND inAppNotificationLayout LIKE :inAppNotificationLayout ";	
				
			}
			String enableEmailNotificationFilterClass = "";
			if (enableEmailNotification.length() > 0)
			{		
				
				enableEmailNotificationFilterClass = " AND enableEmailNotification LIKE :enableEmailNotification ";	
				
			}
			String emailColumnAliasFilterClass = "";
			if (emailColumnAlias.length() > 0)
			{		
				
				emailColumnAliasFilterClass = " AND emailColumnAlias LIKE :emailColumnAlias ";	
				
			}
			String emailNotificationLayoutFilterClass = "";
			if (emailNotificationLayout.length() > 0)
			{		
				
				emailNotificationLayoutFilterClass = " AND emailNotificationLayout LIKE :emailNotificationLayout ";	
				
			}
			String emailSubjectFilterClass = "";
			if (emailSubject.length() > 0)
			{		
				
				emailSubjectFilterClass = " AND emailSubject LIKE :emailSubject ";	
				
			}
			String enableSmsNotificationFilterClass = "";
			if (enableSmsNotification.length() > 0)
			{		
				
				enableSmsNotificationFilterClass = " AND enableSmsNotification LIKE :enableSmsNotification ";	
				
			}
			String smsColumnAliasFilterClass = "";
			if (smsColumnAlias.length() > 0)
			{		
				
				smsColumnAliasFilterClass = " AND smsColumnAlias LIKE :smsColumnAlias ";	
				
			}
			String smsNotificationLayoutFilterClass = "";
			if (smsNotificationLayout.length() > 0)
			{		
				
				smsNotificationLayoutFilterClass = " AND smsNotificationLayout LIKE :smsNotificationLayout ";	
				
			}
			String sMSTextFilterClass = "";
			if (sMSText.length() > 0)
			{		
				
				sMSTextFilterClass = " AND sMSText LIKE :sMSText ";	
				
			}
			String isActiveFilterClass = "";
			if (isActive.length() > 0)
			{		
				
				isActiveFilterClass = " AND isActive LIKE :isActive ";	
				
			}
			String taskStartDateFilterClass = "";
			if (taskStartDate.length() > 0)
			{
				
				
				taskStartDateFilterClass = " AND taskStartDate = :taskStartDate ";			
			}
			String taskFrequencyFilterClass = "";
			if (taskFrequency.length() > 0)
			{			
				
				taskFrequencyFilterClass = " AND taskFrequency = :taskFrequency ";
				
			}
			String taskFrequencyUnitFilterClass = "";
			if (taskFrequencyUnit.length() > 0)
			{		
				
				taskFrequencyUnitFilterClass = " AND taskFrequencyUnit LIKE :taskFrequencyUnit ";	
				
			}
			String isRecurringFilterClass = "";
			if (isRecurring.length() > 0)
			{		
				
				isRecurringFilterClass = " AND isRecurring LIKE :isRecurring ";	
				
			}
			String firstRunTypeFilterClass = "";
			if (firstRunType.length() > 0)
			{		
				
				firstRunTypeFilterClass = " AND firstRunType LIKE :firstRunType ";	
				
			}
			String dateColumnAliasFilterClass = "";
			if (dateColumnAlias.length() > 0)
			{		
				
				dateColumnAliasFilterClass = " AND dateColumnAlias LIKE :dateColumnAlias ";	
				
			}
			String firstRunDateCalculationMethodFilterClass = "";
			if (firstRunDateCalculationMethod.length() > 0)
			{		
				
				firstRunDateCalculationMethodFilterClass = " AND firstRunDateCalculationMethod LIKE :firstRunDateCalculationMethod ";	
				
			}
			String firstRunDateGapInYearsFilterClass = "";
			if (firstRunDateGapInYears.length() > 0)
			{			
				
				firstRunDateGapInYearsFilterClass = " AND firstRunDateGapInYears = :firstRunDateGapInYears ";
				
			}
			String firstRunDateGapInMonthsFilterClass = "";
			if (firstRunDateGapInMonths.length() > 0)
			{			
				
				firstRunDateGapInMonthsFilterClass = " AND firstRunDateGapInMonths = :firstRunDateGapInMonths ";
				
			}
			String firstRunDateGapInDaysFilterClass = "";
			if (firstRunDateGapInDays.length() > 0)
			{			
				
				firstRunDateGapInDaysFilterClass = " AND firstRunDateGapInDays = :firstRunDateGapInDays ";
				
			}
			String firstRunDateGapInHoursFilterClass = "";
			if (firstRunDateGapInHours.length() > 0)
			{			
				
				firstRunDateGapInHoursFilterClass = " AND firstRunDateGapInHours = :firstRunDateGapInHours ";
				
			}
			String firstRunDateGapInMinutesFilterClass = "";
			if (firstRunDateGapInMinutes.length() > 0)
			{			
				
				firstRunDateGapInMinutesFilterClass = " AND firstRunDateGapInMinutes = :firstRunDateGapInMinutes ";
				
			}
			String firstRunDateGapInSecondsFilterClass = "";
			if (firstRunDateGapInSeconds.length() > 0)
			{			
				
				firstRunDateGapInSecondsFilterClass = " AND firstRunDateGapInSeconds = :firstRunDateGapInSeconds ";
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForTaskInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					taskInfoIdFilterClass +
										taskNameFilterClass +
					taskDescriptionFilterClass +
					taskTypeFilterClass +
					apiNameFilterClass +
					targetEntityQueryFilterClass +
					targetUserIdColumnAliasFilterClass +
					targetEntityIdColumnAliasFilterClass +
					enableInAppNotificationFilterClass +
					inAppNotificationLayoutFilterClass +
					enableEmailNotificationFilterClass +
					emailColumnAliasFilterClass +
					emailNotificationLayoutFilterClass +
					emailSubjectFilterClass +
					enableSmsNotificationFilterClass +
					smsColumnAliasFilterClass +
					smsNotificationLayoutFilterClass +
					sMSTextFilterClass +
					isActiveFilterClass +
					taskStartDateFilterClass +
					taskFrequencyFilterClass +
					taskFrequencyUnitFilterClass +
					isRecurringFilterClass +
					firstRunTypeFilterClass +
					dateColumnAliasFilterClass +
					firstRunDateCalculationMethodFilterClass +
					firstRunDateGapInYearsFilterClass +
					firstRunDateGapInMonthsFilterClass +
					firstRunDateGapInDaysFilterClass +
					firstRunDateGapInHoursFilterClass +
					firstRunDateGapInMinutesFilterClass +
					firstRunDateGapInSecondsFilterClass +

					lookupDisplayFilterClause+
					txnStatusFilterClass;
			if(overrideWhereClause.equalsIgnoreCase("Yes"))
			{
				attributesFilterClause +=  whereClause;
			}
			String whereClauseText = 
			getLoginBasedWhereClause(paramsMap) +
			attributesFilterClause;
			if(overrideOrderByClause.equalsIgnoreCase("Yes"))
			{
				orderByClauseText =  orderByClause;
			}
			hql += whereClauseText +
			doGetUpdatedQueryStringForSearchImpl(hql) +
			orderByClauseText;
			Query query = getDBSession().createQuery(hql);
			if (taskInfoIdsList != null && taskInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", taskInfoIdsList);
			}
						if (taskName.length() > 0)
			{		
				
				query.setParameter("taskName", taskName);	
				
			}
			if (taskDescription.length() > 0)
			{		
				
				query.setParameter("taskDescription", taskDescription);	
				
			}
			if (taskType.length() > 0)
			{		
				
				query.setParameter("taskType", taskType);	
				
			}
			if (apiName.length() > 0)
			{		
				
				query.setParameter("apiName", apiName);	
				
			}
			if (targetEntityQuery.length() > 0)
			{		
				
				query.setParameter("targetEntityQuery", targetEntityQuery);	
				
			}
			if (targetUserIdColumnAlias.length() > 0)
			{		
				
				query.setParameter("targetUserIdColumnAlias", targetUserIdColumnAlias);	
				
			}
			if (targetEntityIdColumnAlias.length() > 0)
			{		
				
				query.setParameter("targetEntityIdColumnAlias", targetEntityIdColumnAlias);	
				
			}
			if (enableInAppNotification.length() > 0)
			{		
				
				query.setParameter("enableInAppNotification", enableInAppNotification);	
				
			}
			if (inAppNotificationLayout.length() > 0)
			{		
				
				query.setParameter("inAppNotificationLayout", inAppNotificationLayout);	
				
			}
			if (enableEmailNotification.length() > 0)
			{		
				
				query.setParameter("enableEmailNotification", enableEmailNotification);	
				
			}
			if (emailColumnAlias.length() > 0)
			{		
				
				query.setParameter("emailColumnAlias", emailColumnAlias);	
				
			}
			if (emailNotificationLayout.length() > 0)
			{		
				
				query.setParameter("emailNotificationLayout", emailNotificationLayout);	
				
			}
			if (emailSubject.length() > 0)
			{		
				
				query.setParameter("emailSubject", emailSubject);	
				
			}
			if (enableSmsNotification.length() > 0)
			{		
				
				query.setParameter("enableSmsNotification", enableSmsNotification);	
				
			}
			if (smsColumnAlias.length() > 0)
			{		
				
				query.setParameter("smsColumnAlias", smsColumnAlias);	
				
			}
			if (smsNotificationLayout.length() > 0)
			{		
				
				query.setParameter("smsNotificationLayout", smsNotificationLayout);	
				
			}
			if (sMSText.length() > 0)
			{		
				
				query.setParameter("sMSText", sMSText);	
				
			}
			if (isActive.length() > 0)
			{		
				
				query.setParameter("isActive", isActive);	
				
			}
			if (taskStartDate.length() > 0)
			{
				
				
				query.setParameter("taskStartDate", CommonUtil.getDBFormattedDateTimeStamp(taskStartDate));			
			}
			if (taskFrequency.length() > 0)
			{			
				
				query.setParameter("taskFrequency", Integer.parseInt(taskFrequency));
				
			}
			if (taskFrequencyUnit.length() > 0)
			{		
				
				query.setParameter("taskFrequencyUnit", taskFrequencyUnit);	
				
			}
			if (isRecurring.length() > 0)
			{		
				
				query.setParameter("isRecurring", isRecurring);	
				
			}
			if (firstRunType.length() > 0)
			{		
				
				query.setParameter("firstRunType", firstRunType);	
				
			}
			if (dateColumnAlias.length() > 0)
			{		
				
				query.setParameter("dateColumnAlias", dateColumnAlias);	
				
			}
			if (firstRunDateCalculationMethod.length() > 0)
			{		
				
				query.setParameter("firstRunDateCalculationMethod", firstRunDateCalculationMethod);	
				
			}
			if (firstRunDateGapInYears.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInYears", Integer.parseInt(firstRunDateGapInYears));
				
			}
			if (firstRunDateGapInMonths.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInMonths", Integer.parseInt(firstRunDateGapInMonths));
				
			}
			if (firstRunDateGapInDays.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInDays", Integer.parseInt(firstRunDateGapInDays));
				
			}
			if (firstRunDateGapInHours.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInHours", Integer.parseInt(firstRunDateGapInHours));
				
			}
			if (firstRunDateGapInMinutes.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInMinutes", Integer.parseInt(firstRunDateGapInMinutes));
				
			}
			if (firstRunDateGapInSeconds.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInSeconds", Integer.parseInt(firstRunDateGapInSeconds));
				
			}
if(lookupDisplayPrefix.length() > 0)
			{
				lookupDisplayPrefix = "%"+lookupDisplayPrefix+"%";
				query.setParameter("lookupDisplayPrefix", lookupDisplayPrefix);
			}
			if(txnStatus.length() > 0)
			{
				query.setParameter("txnStatus", txnStatus);
			}
	        for(int i =0; i< additionalParamsList.size(); i++)
	        {
	        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
	        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
				query.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
	        }
			setLoginBasedWhereClauseParams(paramsMap, query);
	    	setParentFilterClauseParamsForTaskInfo(paramsMap, query);
			java.util.Map<String, Object> queryParamsMap = new java.util.HashMap<String, Object>(); 
			JsonObject userSessionInfo = getUserSessionInfo();			
			String loggedInEmployeeId = "-1";//userSessionInfo.get("loggedInEmployeeId").getAsString();			
			Object loggedInEmployeeIdObj= loggedInEmployeeId;  
			queryParamsMap.put("loggedInEmployeeId", loggedInEmployeeIdObj);
			doUpdateQueryWithParameterValuesImpl(query, queryParamsMap);			
			if(noOfRecordsToFetch>0)
			{
				query.setMaxResults(noOfRecordsToFetch);
				query.setFirstResult(noOfRecordsAlreadyFetched);				
			}
			List resultsList = query.list();
			JsonArray taskInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskInfo taskInfo = (TaskInfo) it.next();
				JsonObject taskInfoDataObject = taskInfo.getDataObject(getDBSession());
				taskInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskInfo.getVwTxnStatus()));
				taskInfoList.add(taskInfoDataObject);
				doAfterSearchResultRowAddedImpl(taskInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from TaskInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (taskInfoIdsList != null && taskInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", taskInfoIdsList);
				}
								if (taskName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("taskName", taskName);
					
					
					
					
				}
				if (taskDescription.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("taskDescription", taskDescription);
					
					
					
					
				}
				if (taskType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("taskType", taskType);
					
					
					
					
				}
				if (apiName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("apiName", apiName);
					
					
					
					
				}
				if (targetEntityQuery.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("targetEntityQuery", targetEntityQuery);
					
					
					
					
				}
				if (targetUserIdColumnAlias.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("targetUserIdColumnAlias", targetUserIdColumnAlias);
					
					
					
					
				}
				if (targetEntityIdColumnAlias.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("targetEntityIdColumnAlias", targetEntityIdColumnAlias);
					
					
					
					
				}
				if (enableInAppNotification.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("enableInAppNotification", enableInAppNotification);
					
					
					
					
				}
				if (inAppNotificationLayout.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("inAppNotificationLayout", inAppNotificationLayout);
					
					
					
					
				}
				if (enableEmailNotification.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("enableEmailNotification", enableEmailNotification);
					
					
					
					
				}
				if (emailColumnAlias.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("emailColumnAlias", emailColumnAlias);
					
					
					
					
				}
				if (emailNotificationLayout.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("emailNotificationLayout", emailNotificationLayout);
					
					
					
					
				}
				if (emailSubject.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("emailSubject", emailSubject);
					
					
					
					
				}
				if (enableSmsNotification.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("enableSmsNotification", enableSmsNotification);
					
					
					
					
				}
				if (smsColumnAlias.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("smsColumnAlias", smsColumnAlias);
					
					
					
					
				}
				if (smsNotificationLayout.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("smsNotificationLayout", smsNotificationLayout);
					
					
					
					
				}
				if (sMSText.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("sMSText", sMSText);
					
					
					
					
				}
				if (isActive.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("isActive", isActive);
					
					
					
					
				}
				if (taskStartDate.length() > 0)
				{
					
					
					countQuery.setParameter("taskStartDate", CommonUtil.getDBFormattedDateTimeStamp(taskStartDate));
					
					
					
					
					
					
					
					
					
				}
				if (taskFrequency.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("taskFrequency", Integer.parseInt(taskFrequency));
					
				}
				if (taskFrequencyUnit.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("taskFrequencyUnit", taskFrequencyUnit);
					
					
					
					
				}
				if (isRecurring.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("isRecurring", isRecurring);
					
					
					
					
				}
				if (firstRunType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunType", firstRunType);
					
					
					
					
				}
				if (dateColumnAlias.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("dateColumnAlias", dateColumnAlias);
					
					
					
					
				}
				if (firstRunDateCalculationMethod.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateCalculationMethod", firstRunDateCalculationMethod);
					
					
					
					
				}
				if (firstRunDateGapInYears.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInYears", Integer.parseInt(firstRunDateGapInYears));
					
				}
				if (firstRunDateGapInMonths.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInMonths", Integer.parseInt(firstRunDateGapInMonths));
					
				}
				if (firstRunDateGapInDays.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInDays", Integer.parseInt(firstRunDateGapInDays));
					
				}
				if (firstRunDateGapInHours.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInHours", Integer.parseInt(firstRunDateGapInHours));
					
				}
				if (firstRunDateGapInMinutes.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInMinutes", Integer.parseInt(firstRunDateGapInMinutes));
					
				}
				if (firstRunDateGapInSeconds.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInSeconds", Integer.parseInt(firstRunDateGapInSeconds));
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForTaskInfo(paramsMap, countQuery);
		        for(int i =0; i< additionalParamsList.size(); i++)
		        {
		        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
		        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
		        	countQuery.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
		        }
				if(txnStatus.length() > 0)
				{
					countQuery.setParameter("txnStatus", txnStatus);
				}
				Long matchingSearchResultsCount = (Long) countQuery.uniqueResult();
				dataObject.addProperty("matchingSearchResultsCount", matchingSearchResultsCount);
			}
			dataObject.add("taskInfoList",   taskInfoList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getDashboardGraphsData()
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		String hql = " SELECT concat(month(E.vwCreationDate), '/', year(E.vwCreationDate)), count(*) "
				+ "   from TaskInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
		Query query = getDBSession().createQuery(hql);
		List resultsList = query.list();
		JsonArray monthlyTransactionList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("monthName", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			monthlyTransactionList.add(monthlyTransactionInfo);
		}
		dataObject.add("monthlyTransactionList", monthlyTransactionList);
		hql = " SELECT E.vwTxnStatus, count(*) "
				+ "   from TaskInfo E GROUP BY E.vwTxnStatus ";
		query = getDBSession().createQuery(hql);
		resultsList = query.list();
		JsonArray transactionStatusList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("statusDescription", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			transactionStatusList.add(monthlyTransactionInfo);
		}
		dataObject.add("transactionStatusList", transactionStatusList);
		//dataObject.add("monthlyTransactionList", new JsonArray());
		//dataObject.add("transactionStatusList", new JsonArray());
		dataObject.addProperty("success", 1);
		return dataObject;
	}
	public JsonObject retrieveDependentTaskInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveTaskInfoList(paramsMap);
	}
	public TaskInfo getTaskInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from TaskInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			TaskInfo taskInfo = (TaskInfo) it.next();
			return taskInfo;
		}
		return null;
	}
	private void callBusinessValidateRules()
	{
		loadInitObjects();
	
	}	
	private void callBusinessUpdateRules()
	{
		loadInitObjects();
		//$$INCLUDE_UPDATE_RULES$$
		TaskInfo taskInfo = (TaskInfo)getManagedBean();
		JsonObject taskInfoDataObject = taskInfo.getDataObject(getDBSession());copyTaskInfoFromJson(taskInfo, taskInfoDataObject);
		setManagedBean(taskInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData TaskInfoContollerBase");
			TaskInfo currentBean = (TaskInfo)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "TaskName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTaskName(sStringTestData);iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "TaskDescription".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTaskDescription(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "TaskType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTaskType(sStringTestData);iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "ApiName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setApiName(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "TargetEntityQuery".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTargetEntityQuery(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "TargetUserIdColumnAlias".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTargetUserIdColumnAlias(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "TargetEntityIdColumnAlias".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTargetEntityIdColumnAlias(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "EnableInAppNotification".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEnableInAppNotification(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "InAppNotificationLayout".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setInAppNotificationLayout(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "EnableEmailNotification".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEnableEmailNotification(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "EmailColumnAlias".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEmailColumnAlias(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "EmailNotificationLayout".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEmailNotificationLayout(sStringTestData);iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "EmailSubject".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEmailSubject(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "EnableSmsNotification".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEnableSmsNotification(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "SmsColumnAlias".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSmsColumnAlias(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "SmsNotificationLayout".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSmsNotificationLayout(sStringTestData);iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "SMSText".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSMSText(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "IsActive".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setIsActive(sStringTestData);
		
		currentBean.setTaskStartDate(new Date());currentBean.setTaskFrequency(1);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "TaskFrequencyUnit".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTaskFrequencyUnit(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "IsRecurring".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setIsRecurring(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "FirstRunType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setFirstRunType(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "DateColumnAlias".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setDateColumnAlias(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "FirstRunDateCalculationMethod".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setFirstRunDateCalculationMethod(sStringTestData);currentBean.setFirstRunDateGapInYears(1);currentBean.setFirstRunDateGapInMonths(1);currentBean.setFirstRunDateGapInDays(1);currentBean.setFirstRunDateGapInHours(1);currentBean.setFirstRunDateGapInMinutes(1);currentBean.setFirstRunDateGapInSeconds(1);

		setManagedBean(currentBean);
		debugCode("Out setTestData TaskInfoContollerBase");
	}
	// end Test Data
	public void copyTaskInfoFromJson(TaskInfo taskInfo, JsonObject taskInfoDataObject)
	{
		copyTaskInfoFromJson(taskInfo, taskInfoDataObject, false);
	}
	public void copyTaskInfoFromJson(TaskInfo taskInfo, JsonObject taskInfoDataObject, boolean excludePasswordFields)
	{	
				
		if(taskInfoDataObject.has("taskName"))
		{
			String taskName = taskInfoDataObject.get("taskName").getAsString();
			taskInfo.setTaskName(taskName);
		}if(taskInfoDataObject.has("taskDescription"))
		{
			String taskDescription = taskInfoDataObject.get("taskDescription").getAsString();
			taskInfo.setTaskDescription(taskDescription);
		}if(taskInfoDataObject.has("taskType"))
		{
			String taskType = taskInfoDataObject.get("taskType").getAsString();
			taskInfo.setTaskType(taskType);
		}if(taskInfoDataObject.has("apiName"))
		{
			String apiName = taskInfoDataObject.get("apiName").getAsString();
			taskInfo.setApiName(apiName);
		}if(taskInfoDataObject.has("targetEntityQuery"))
		{
			String targetEntityQuery = taskInfoDataObject.get("targetEntityQuery").getAsString();
			taskInfo.setTargetEntityQuery(targetEntityQuery);
		}if(taskInfoDataObject.has("targetUserIdColumnAlias"))
		{
			String targetUserIdColumnAlias = taskInfoDataObject.get("targetUserIdColumnAlias").getAsString();
			taskInfo.setTargetUserIdColumnAlias(targetUserIdColumnAlias);
		}if(taskInfoDataObject.has("targetEntityIdColumnAlias"))
		{
			String targetEntityIdColumnAlias = taskInfoDataObject.get("targetEntityIdColumnAlias").getAsString();
			taskInfo.setTargetEntityIdColumnAlias(targetEntityIdColumnAlias);
		}if(taskInfoDataObject.has("enableInAppNotification"))
		{
			String enableInAppNotification = taskInfoDataObject.get("enableInAppNotification").getAsString();
			taskInfo.setEnableInAppNotification(enableInAppNotification);
		}if(taskInfoDataObject.has("inAppNotificationLayout"))
		{
			String inAppNotificationLayout = taskInfoDataObject.get("inAppNotificationLayout").getAsString();
			taskInfo.setInAppNotificationLayout(inAppNotificationLayout);
		}if(taskInfoDataObject.has("enableEmailNotification"))
		{
			String enableEmailNotification = taskInfoDataObject.get("enableEmailNotification").getAsString();
			taskInfo.setEnableEmailNotification(enableEmailNotification);
		}if(taskInfoDataObject.has("emailColumnAlias"))
		{
			String emailColumnAlias = taskInfoDataObject.get("emailColumnAlias").getAsString();
			taskInfo.setEmailColumnAlias(emailColumnAlias);
		}if(taskInfoDataObject.has("emailNotificationLayout"))
		{
			String emailNotificationLayout = taskInfoDataObject.get("emailNotificationLayout").getAsString();
			taskInfo.setEmailNotificationLayout(emailNotificationLayout);
		}if(taskInfoDataObject.has("emailSubject"))
		{
			String emailSubject = taskInfoDataObject.get("emailSubject").getAsString();
			taskInfo.setEmailSubject(emailSubject);
		}if(taskInfoDataObject.has("enableSmsNotification"))
		{
			String enableSmsNotification = taskInfoDataObject.get("enableSmsNotification").getAsString();
			taskInfo.setEnableSmsNotification(enableSmsNotification);
		}if(taskInfoDataObject.has("smsColumnAlias"))
		{
			String smsColumnAlias = taskInfoDataObject.get("smsColumnAlias").getAsString();
			taskInfo.setSmsColumnAlias(smsColumnAlias);
		}if(taskInfoDataObject.has("smsNotificationLayout"))
		{
			String smsNotificationLayout = taskInfoDataObject.get("smsNotificationLayout").getAsString();
			taskInfo.setSmsNotificationLayout(smsNotificationLayout);
		}if(taskInfoDataObject.has("sMSText"))
		{
			String sMSText = taskInfoDataObject.get("sMSText").getAsString();
			taskInfo.setSMSText(sMSText);
		}if(taskInfoDataObject.has("isActive"))
		{
			String isActive = taskInfoDataObject.get("isActive").getAsString();
			taskInfo.setIsActive(isActive);
		}
		
		if(taskInfoDataObject.has("taskStartDate"))
		{
			String taskStartDate = taskInfoDataObject.get("taskStartDate").getAsString();
			if(taskStartDate.length() > 0)
			{
				try
				{
					taskInfo.setTaskStartDate(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(taskStartDate));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid Task Start Date");
				}
			}
		}if(taskInfoDataObject.has("taskFrequency"))
		{
			Integer taskFrequency = null;
			try
			{
			 	taskFrequency = taskInfoDataObject.get("taskFrequency").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(taskFrequency != null)
			{
				taskInfo.setTaskFrequency(taskFrequency);
			}
		}if(taskInfoDataObject.has("taskFrequencyUnit"))
		{
			String taskFrequencyUnit = taskInfoDataObject.get("taskFrequencyUnit").getAsString();
			taskInfo.setTaskFrequencyUnit(taskFrequencyUnit);
		}if(taskInfoDataObject.has("isRecurring"))
		{
			String isRecurring = taskInfoDataObject.get("isRecurring").getAsString();
			taskInfo.setIsRecurring(isRecurring);
		}if(taskInfoDataObject.has("firstRunType"))
		{
			String firstRunType = taskInfoDataObject.get("firstRunType").getAsString();
			taskInfo.setFirstRunType(firstRunType);
		}if(taskInfoDataObject.has("dateColumnAlias"))
		{
			String dateColumnAlias = taskInfoDataObject.get("dateColumnAlias").getAsString();
			taskInfo.setDateColumnAlias(dateColumnAlias);
		}if(taskInfoDataObject.has("firstRunDateCalculationMethod"))
		{
			String firstRunDateCalculationMethod = taskInfoDataObject.get("firstRunDateCalculationMethod").getAsString();
			taskInfo.setFirstRunDateCalculationMethod(firstRunDateCalculationMethod);
		}if(taskInfoDataObject.has("firstRunDateGapInYears"))
		{
			Integer firstRunDateGapInYears = null;
			try
			{
			 	firstRunDateGapInYears = taskInfoDataObject.get("firstRunDateGapInYears").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInYears != null)
			{
				taskInfo.setFirstRunDateGapInYears(firstRunDateGapInYears);
			}
		}if(taskInfoDataObject.has("firstRunDateGapInMonths"))
		{
			Integer firstRunDateGapInMonths = null;
			try
			{
			 	firstRunDateGapInMonths = taskInfoDataObject.get("firstRunDateGapInMonths").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInMonths != null)
			{
				taskInfo.setFirstRunDateGapInMonths(firstRunDateGapInMonths);
			}
		}if(taskInfoDataObject.has("firstRunDateGapInDays"))
		{
			Integer firstRunDateGapInDays = null;
			try
			{
			 	firstRunDateGapInDays = taskInfoDataObject.get("firstRunDateGapInDays").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInDays != null)
			{
				taskInfo.setFirstRunDateGapInDays(firstRunDateGapInDays);
			}
		}if(taskInfoDataObject.has("firstRunDateGapInHours"))
		{
			Integer firstRunDateGapInHours = null;
			try
			{
			 	firstRunDateGapInHours = taskInfoDataObject.get("firstRunDateGapInHours").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInHours != null)
			{
				taskInfo.setFirstRunDateGapInHours(firstRunDateGapInHours);
			}
		}if(taskInfoDataObject.has("firstRunDateGapInMinutes"))
		{
			Integer firstRunDateGapInMinutes = null;
			try
			{
			 	firstRunDateGapInMinutes = taskInfoDataObject.get("firstRunDateGapInMinutes").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInMinutes != null)
			{
				taskInfo.setFirstRunDateGapInMinutes(firstRunDateGapInMinutes);
			}
		}if(taskInfoDataObject.has("firstRunDateGapInSeconds"))
		{
			Integer firstRunDateGapInSeconds = null;
			try
			{
			 	firstRunDateGapInSeconds = taskInfoDataObject.get("firstRunDateGapInSeconds").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInSeconds != null)
			{
				taskInfo.setFirstRunDateGapInSeconds(firstRunDateGapInSeconds);
			}
		}
		
	}
		
	public JsonObject createTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		return createTaskInfo(taskInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject taskInfoDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createTaskInfo(JsonObject taskInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		TaskInfo taskInfo = new TaskInfo();
		setLoginBasedValues(paramsInfoObj, taskInfoDataObject);
		Session session = getDBSession();				
		copyTaskInfoFromJson(taskInfo, taskInfoDataObject);
		if(taskInfoDataObject.has("legacyRecordId"))
		{
			taskInfo.setLegacyRecordId(taskInfoDataObject.get("legacyRecordId").getAsInt());
		}
				taskInfo.setVwCreatedBy(createdBy);
		taskInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(taskInfo);
		setManagedBean("TaskInfoBean", taskInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		taskInfo = (TaskInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("taskInfoId", taskInfo.getTaskInfoId());
			JsonObject taskInfoObj = taskInfo.getDataObject(getDBSession());
			taskInfoObj.addProperty("nextAction", getActionForCurrentStatus(taskInfo.getVwTxnStatus()));
			dataObject.add("taskInfoDataObject", taskInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskInfoId", taskInfoId);
		JsonObject responseData = retrieveTaskInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'TaskInfo' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
		if(taskInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(taskInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromTaskInfo(taskInfoDataObject);
		boolean excludePasswordFields = true;
		copyTaskInfoFromJson(taskInfo, taskInfoDataObject, excludePasswordFields);setPrivateManagedBean(taskInfo);
		setManagedBean("TaskInfoBean", taskInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		taskInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
		{
			update();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction updated Successfully");
			dataObject.addProperty("taskInfoId", taskInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
	}
	public JsonObject deleteTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskInfoId", taskInfoId);
		JsonObject responseData = retrieveTaskInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'TaskInfo' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);setPrivateManagedBean(taskInfo);
		setManagedBean("TaskInfo", taskInfo);
		if (getHasTransactionFailed() == false)
		{
			delete();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction deleted Successfully");
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject fetchTaskInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		TaskInfo taskInfo = new TaskInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfo", taskInfo);
			doAfterTaskInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskInfo", taskInfo.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchTaskInfoTestData(int obj, JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		TaskInfo taskInfo = new TaskInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskInfoFromJson(taskInfo, taskInfoDataObject);
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfo", taskInfo);
			doAfterTaskInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskInfo", taskInfo.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject lookupRowSelectedForTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		TaskInfo taskInfo = new TaskInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskInfoFromJson(taskInfo, taskInfoDataObject);	String attributeName = taskInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = taskInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfo", taskInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskInfo", taskInfo.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Lookup nformation could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject selectOptionChangedForTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		TaskInfo taskInfo = new TaskInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskInfoFromJson(taskInfo, taskInfoDataObject);	
			String attributeName = taskInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfo", taskInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskInfo", taskInfo.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Information could not be refreshed.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Information could not be refreshed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject doExecuteCustomAPI(JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
			String customEventName = taskInfoDataObject.get("customEventName").getAsString();
			TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfoBean", taskInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskInfo", taskInfo.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", customEventName + " processed successfully.");
				dataObject.add("additionalInfo", getAdditionalInfo());
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.add("additionalInfo", getAdditionalInfo());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		finally
		{
			finalizeTransaction();
		}
	}
	public JsonObject executeAuthorisationOnTaskInfo(JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
			String currentStatus = taskInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(taskInfoDataObject.has("currentAction"))
			{
				currentAction = taskInfoDataObject.get("currentAction").getAsString();
			}
			TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
			setPrivateManagedBean(taskInfo);
			setManagedBean("TaskInfoBean", taskInfo);
			if(taskInfo.getIsRequestUnderProcesss() == 1)
			{
				setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(!isActionAllowedOnTransaction(currentAction))
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(currentAction.equalsIgnoreCase(ACTION_REJECT))
			{
				executeAction(taskInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(taskInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(taskInfo, "TaskInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedtaskInfoDataObject = taskInfo.getDataObject(getDBSession());
				updatedtaskInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskInfo.getVwTxnStatus()));
				dataObject.add("taskInfo", updatedtaskInfoDataObject);
				if(!currentAction.equalsIgnoreCase(ACTION_REJECT))
				{
					currentAction = getActionForCurrentStatus(currentStatus);
				}
				dataObject.addProperty("alert", "Transaction "+ currentAction + " successfully.");
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public boolean isActionAllowedOnTransaction(String currentAction)
	{
		TaskInfo taskInfo = (TaskInfo) getManagedBean();
		String currentStatus = taskInfo.getVwTxnStatus();
		if(currentStatus.equalsIgnoreCase("REJECTED"))
		{
			setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
			return false;
		}
		if(currentAction.equalsIgnoreCase(ACTION_REJECT))
		{
			if(!(currentStatus.equalsIgnoreCase("CREATED")
					||currentStatus.equalsIgnoreCase("MODIFIED")))
			{
				setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
				return false;
			}
		}
		return true;
	}
	
	
	public JsonObject downloadTaskInfoData() throws Exception
	{
		return downloadTaskInfoData(null);
	}
	public JsonObject downloadTaskInfoData(HSSFWorkbook workbook) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
		try
		{
			
			boolean saveWorkbook = false;
			HSSFSheet sheet = null;
			if(workbook == null)
			{
				String projectTemplatesPath = com.patientapp.util.SettingsUtil.getProjectTemplatesPath();
				FileInputStream file = new FileInputStream(new File(projectTemplatesPath+"DownloadTemplate.xls"));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			sheet = workbook.cloneSheet(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "TaskInfo");
			HSSFFont defaultFont = workbook.createFont();
			defaultFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			HSSFFont boldfont = workbook.createFont();
			boldfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			CellStyle dataStyle = workbook.createCellStyle(); // this style is used for the cells where bold text is needed other than header
			dataStyle.setFont(defaultFont);
			dataStyle.setWrapText(true);
			CellStyle headerStyle = sheet.getRow(0).getCell(0).getCellStyle();
			Cell cell;
			Row row;
			
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("currentRowPosition", 0);
			rowColumnIndexObject.addProperty("entityDataCellIndex", 0);
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			int headerCellCount = entityDataCellIndex;
			int columnWidth = 3000;
			String headerName = "";
			row = sheet.createRow(currentRowPosition++);cell = row.createCell(headerCellCount++);
			headerName = "S.No";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "taskInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "taskName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "taskDescription";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "taskType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "apiName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "targetEntityQuery";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "targetUserIdColumnAlias";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "targetEntityIdColumnAlias";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "enableInAppNotification";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "inAppNotificationLayout";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "enableEmailNotification";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "emailColumnAlias";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "emailNotificationLayout";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "emailSubject";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "enableSmsNotification";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "smsColumnAlias";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "smsNotificationLayout";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "sMSText";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "isActive";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "taskStartDate";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "taskFrequency";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "taskFrequencyUnit";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "isRecurring";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "dateColumnAlias";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateCalculationMethod";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInYears";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInMonths";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInDays";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInHours";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInMinutes";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInSeconds";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			headerStyle = workbook.createCellStyle(); // this style is used for table header in the report
			headerStyle.setFont(boldfont);
			headerStyle.setWrapText(true);
			Transaction tx = getDBSession().getTransaction();
			if(!tx.isActive())
			{
				tx.begin();
			}
			String hql = "From TaskInfo ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				TaskInfo taskInfo = (TaskInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer taskInfoId = taskInfo.getTaskInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(taskInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String taskName = taskInfo.getTaskName();
				cell.setCellValue(taskName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String taskDescription = taskInfo.getTaskDescription();
				cell.setCellValue(taskDescription);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String taskType = taskInfo.getTaskType();
				cell.setCellValue(taskType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String apiName = taskInfo.getApiName();
				cell.setCellValue(apiName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String targetEntityQuery = taskInfo.getTargetEntityQuery();
				cell.setCellValue(targetEntityQuery);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String targetUserIdColumnAlias = taskInfo.getTargetUserIdColumnAlias();
				cell.setCellValue(targetUserIdColumnAlias);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String targetEntityIdColumnAlias = taskInfo.getTargetEntityIdColumnAlias();
				cell.setCellValue(targetEntityIdColumnAlias);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String enableInAppNotification = taskInfo.getEnableInAppNotification();
				cell.setCellValue(enableInAppNotification);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String inAppNotificationLayout = taskInfo.getInAppNotificationLayout();
				cell.setCellValue(inAppNotificationLayout);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String enableEmailNotification = taskInfo.getEnableEmailNotification();
				cell.setCellValue(enableEmailNotification);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String emailColumnAlias = taskInfo.getEmailColumnAlias();
				cell.setCellValue(emailColumnAlias);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String emailNotificationLayout = taskInfo.getEmailNotificationLayout();
				cell.setCellValue(emailNotificationLayout);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String emailSubject = taskInfo.getEmailSubject();
				cell.setCellValue(emailSubject);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String enableSmsNotification = taskInfo.getEnableSmsNotification();
				cell.setCellValue(enableSmsNotification);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String smsColumnAlias = taskInfo.getSmsColumnAlias();
				cell.setCellValue(smsColumnAlias);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String smsNotificationLayout = taskInfo.getSmsNotificationLayout();
				cell.setCellValue(smsNotificationLayout);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String sMSText = taskInfo.getSMSText();
				cell.setCellValue(sMSText);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String isActive = taskInfo.getIsActive();
				cell.setCellValue(isActive);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	
				
				Date taskStartDate = taskInfo.getTaskStartDate();
				if(taskStartDate!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateTimeStampFormat(taskStartDate));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer taskFrequency = taskInfo.getTaskFrequency();
				if(taskFrequency!=null)
				{
					cell.setCellValue(String.valueOf(taskFrequency));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String taskFrequencyUnit = taskInfo.getTaskFrequencyUnit();
				cell.setCellValue(taskFrequencyUnit);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String isRecurring = taskInfo.getIsRecurring();
				cell.setCellValue(isRecurring);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String firstRunType = taskInfo.getFirstRunType();
				cell.setCellValue(firstRunType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String dateColumnAlias = taskInfo.getDateColumnAlias();
				cell.setCellValue(dateColumnAlias);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String firstRunDateCalculationMethod = taskInfo.getFirstRunDateCalculationMethod();
				cell.setCellValue(firstRunDateCalculationMethod);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInYears = taskInfo.getFirstRunDateGapInYears();
				if(firstRunDateGapInYears!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInYears));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInMonths = taskInfo.getFirstRunDateGapInMonths();
				if(firstRunDateGapInMonths!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInMonths));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInDays = taskInfo.getFirstRunDateGapInDays();
				if(firstRunDateGapInDays!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInDays));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInHours = taskInfo.getFirstRunDateGapInHours();
				if(firstRunDateGapInHours!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInHours));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInMinutes = taskInfo.getFirstRunDateGapInMinutes();
				if(firstRunDateGapInMinutes!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInMinutes));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInSeconds = taskInfo.getFirstRunDateGapInSeconds();
				if(firstRunDateGapInSeconds!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInSeconds));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
				taskExecutionInfoImplObj.downloadTaskExecutionInfoData(sheet, headerStyle, dataStyle, rowColumnIndexObject, taskInfoId);
				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
				taskLayoutParametersImplObj.downloadTaskLayoutParametersData(sheet, headerStyle, dataStyle, rowColumnIndexObject, taskInfoId);
				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
				emailAttachmentLayoutImplObj.downloadEmailAttachmentLayoutData(sheet, headerStyle, dataStyle, rowColumnIndexObject, taskInfoId);

			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "TaskInfo.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "TaskInfo.xls";
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				int fileId = CommonUtil.saveFile(fileName, savedFileName, session);
				if(fileId < 0)
				{
					dataObject.addProperty("alert", "Your request could not be processed.");
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadTaskInfoData(JsonObject taskInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = taskInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = taskInfoDataObject.get("inputFilesZip").getAsString();
		String savedFileName = CommonUtil.getFilePath("", fileId, getDBSession());
		FileInputStream file = new FileInputStream(new File(savedFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		String inputFilesPath = "";
		if(inputFilesZip.length() > 0)
		{
			String zipFilePath = CommonUtil.getFilePath(inputFilesZip, -1, getDBSession());
	        String extractedZipFilePath = FilenameUtils.removeExtension(zipFilePath);
	        int isExtracted = ZipExtraction.extractZipFile(extractedZipFilePath, zipFilePath);
	        if(isExtracted != 1)
	        {
	    		dataObject.addProperty("alert", "TaskInfo Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		taskInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadTaskInfoData(workbook, taskInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "TaskInfo Data uploaded successfully.");
		dataObject.addProperty("success", 1);
		dataObject.addProperty("fileId", fileId);
		return dataObject;
	}
	public HashMap<Integer, String> getHeaderRowColumnNamesMap(Row headerRow, int entityDataCellIndex)
	{
		HashMap<Integer, String> cellIndexParameterMap = new HashMap<Integer, String>();
		boolean continueReadingRow = true;
		int headerRowCellIndex = entityDataCellIndex + 1;
		while (continueReadingRow)
		{
			Cell headerCell = headerRow.getCell(headerRowCellIndex);
			boolean cellExist = false;
			if(headerCell != null)
			{
				String parameterKeyName = headerCell.getStringCellValue();
				if(parameterKeyName != null && parameterKeyName.length() > 0)
				{
					cellIndexParameterMap.put(headerRowCellIndex, parameterKeyName);
					headerRowCellIndex++;
					cellExist = true;
				}
			}
			if(cellExist == false)
			{
				continueReadingRow = false;
			}
		}
		return cellIndexParameterMap;		
	}
	public JsonObject uploadTaskInfoData(HSSFWorkbook workbook, JsonObject taskInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("TaskInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = taskInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = taskInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = taskInfoDataObject.get("fileId").getAsInt();
				//savedFileName = CommonUtil.fileIDAndNamesMap.get(fileId);
				savedFileName = CommonUtil.getFilePath("", fileId, session);
				FileInputStream file = new FileInputStream(new File(savedFileName));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			successCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			successCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont successFontColor = workbook.createFont();
			successFontColor.setColor(HSSFColor.GREEN.index);
			successCellStyle.setFont(successFontColor);
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			errorCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			errorCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont errorFontColor = workbook.createFont();
			errorFontColor.setColor(HSSFColor.RED.index);
			errorCellStyle.setFont(errorFontColor);
			JsonObject dataUploadInfo = processExcelSheetData(workbook, sheet, successCellStyle, errorCellStyle, areSourceDestinationSame, inputFilesPath);			
			if(dataUploadInfo!=null && dataUploadInfo.has("dataListToRetry") && dataUploadInfo.get("dataListToRetry").isJsonNull()==false)
			{
				int retryCount = 0;
				JsonArray dataListToRetry = dataUploadInfo.get("dataListToRetry").getAsJsonArray();
				dataObject.add("dataListToRetry", dataListToRetry);			
				if(dataUploadInfo.has("retryCount") && dataUploadInfo.get("retryCount").isJsonNull()==false)
				{
					retryCount = dataUploadInfo.get("retryCount").getAsInt();
				}				
				dataObject.addProperty("retryCount", retryCount);
			}
			if(saveWorkbook == true)
			{
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				dataObject.addProperty("alert", "TaskInfo Data uploaded successfully.");
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject processExcelSheetData(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, Integer areSourceDestinationSame, String inputFilesPath) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			int statusCellIndex = 50;
			int errorCount = 0;
			Cell lastCell = null;
			Row row = null;
			int totalDefinedRowsInSheet = sheet.getLastRowNum() + 1;
			int currentRowPosition = 0;
			int entityDataCellIndex = 0;
			int pendingRecordsCount = 0;
			int batchsize = 100;
			Row headerRow = sheet.getRow(currentRowPosition);
			HashMap<Integer, String> cellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex);
			currentRowPosition++;
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex);
			rowColumnIndexObject.addProperty("areSourceDestinationSame", areSourceDestinationSame);
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			JsonArray dataListToRetry = new JsonArray();
			JsonObject taskInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject taskInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray taskInfoList = taskInfoListObj.get("taskInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadTaskInfoList(taskInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(taskInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(taskInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
			}
			dataObject.addProperty("success", 1);
			dataObject.add("dataListToRetry", dataListToRetry);			
			dataObject.addProperty("retryCount", totalRetryCount);			
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchData(HSSFWorkbook workbook, HSSFSheet sheet, int totalDefinedRowsInSheet, int batchsize, JsonObject rowColumnIndexObject, HashMap<Integer, String> cellIndexParameterMap, Integer areSourceDestinationSame,String inputFilesPath) throws Exception	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{			
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			int statusCellIndex = 50;
			JsonArray taskInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject taskInfo = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("taskExecutionInfo"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
					JsonObject responseData  = taskExecutionInfoImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray taskExecutionInfoList = responseData.get("taskExecutionInfoList").getAsJsonArray();
					taskInfo.add("taskExecutionInfoList", taskExecutionInfoList);
					currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
					row = sheet.getRow(currentRowPosition-1);
					Cell rowCell = row.getCell(entityDataCellIndex-2);
					if(rowCell != null)
					{
						String sEntityLoopEndToken = row.getCell(entityDataCellIndex-2).getStringCellValue();
						if(sEntityLoopEndToken != null && sEntityLoopEndToken.equalsIgnoreCase("END"))
						{
							break;					
						}
					}
					continue;
				}
				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("taskLayoutParameters"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
					JsonObject responseData  = taskLayoutParametersImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray taskLayoutParametersList = responseData.get("taskLayoutParametersList").getAsJsonArray();
					taskInfo.add("taskLayoutParametersList", taskLayoutParametersList);
					currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
					row = sheet.getRow(currentRowPosition-1);
					Cell rowCell = row.getCell(entityDataCellIndex-2);
					if(rowCell != null)
					{
						String sEntityLoopEndToken = row.getCell(entityDataCellIndex-2).getStringCellValue();
						if(sEntityLoopEndToken != null && sEntityLoopEndToken.equalsIgnoreCase("END"))
						{
							break;					
						}
					}
					continue;
				}
				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("emailAttachmentLayout"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
					JsonObject responseData  = emailAttachmentLayoutImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray emailAttachmentLayoutList = responseData.get("emailAttachmentLayoutList").getAsJsonArray();
					taskInfo.add("emailAttachmentLayoutList", emailAttachmentLayoutList);
					currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
					row = sheet.getRow(currentRowPosition-1);
					Cell rowCell = row.getCell(entityDataCellIndex-2);
					if(rowCell != null)
					{
						String sEntityLoopEndToken = row.getCell(entityDataCellIndex-2).getStringCellValue();
						if(sEntityLoopEndToken != null && sEntityLoopEndToken.equalsIgnoreCase("END"))
						{
							break;					
						}
					}
					continue;
				}

				if(pendingRecordsCount == batchsize && batchsize>0)
				{
					break;
				}
				JsonObject taskInfoUploadObj	= new JsonObject();
				taskInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					taskInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("taskInfoId"))
						{
							String taskInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(taskInfoId != null && taskInfoId.trim().length() > 0)
							{
								try
								{
									Integer iTaskInfoId = Integer.parseInt(taskInfoId);
									if(areSourceDestinationSame == 1)
									{
										TaskInfo taskInfoObj = (TaskInfo)session.get(TaskInfo.class, iTaskInfoId);
										if(taskInfoObj != null)
										{ 
											taskInfo.addProperty("taskInfoId", iTaskInfoId);
										}
										else
										{
											taskInfoUploadObj.addProperty("isDataFetched", 0);
											taskInfoUploadObj.addProperty("msg", "This TaskInfo could not be uploaded as there appears to be some problem with the data(TaskInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										TaskInfo taskInfoObj = getTaskInfoByLegacyRecordId(session, iTaskInfoId);
										if(taskInfoObj != null)
										{ 
											taskInfo.addProperty("taskInfoId", taskInfoObj.getTaskInfoId());
										}
										taskInfo.addProperty("legacyRecordId", iTaskInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									taskInfoUploadObj.addProperty("isDataFetched", 0);
									taskInfoUploadObj.addProperty("msg", "This TaskInfo could not be uploaded as there appears to be some problem with the data(TaskInfo Id). ");
									continue;
								}
							}
						}
						else
						{
							String parameterValue = row.getCell(cellIndex++).getStringCellValue();
							if(inputFilesPath.length() > 0)
							{
								
							}
							taskInfo.addProperty(parameterName, parameterValue);
						}
					}
					taskInfoUploadObj.add("dataObject", taskInfo);		    
					taskInfoList.add(taskInfoUploadObj);
					pendingRecordsCount++;
					
				}
				catch (Exception e)
				{
					writeToLog(CommonUtil.getStackTrace(e));
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					continue;
				}
			}
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			dataObject.add("taskInfoList", taskInfoList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int  areSomeRecordsUploaded(JsonObject previousRetryCountMap, JsonObject retryCountMap) throws Exception
	{
		int previousRetryCount = 0;
		int retryCount = 0;
		int areSomeRecordsUploaded = 0;
		if(previousRetryCountMap.has("TaskInfo") && previousRetryCountMap.get("TaskInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("TaskInfo").getAsInt();
		}
		if(retryCountMap.has("TaskInfo") && retryCountMap.get("TaskInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("TaskInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    		com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

		return 0;
	}
	public void updateRetryCountMapForTaskInfoList(JsonArray taskInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < taskInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject taskInfoDataObject = taskInfoList.get(i).getAsJsonObject();
			JsonObject taskInfo = taskInfoDataObject.get("dataObject").getAsJsonObject();
			if(taskInfoDataObject.has("retryUpload") && taskInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = taskInfoDataObject.get("retryUpload").getAsInt();
			}
			if(taskInfoDataObject.has("retryChildEntitiesUpload") && taskInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = taskInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(taskInfoDataObject.has("partialUploadUnderProcess") && taskInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = taskInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    		    if(taskInfo.has("taskExecutionInfoList"))
		    {
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(getDBSession());
				JsonArray taskExecutionInfoList = taskInfo.get("taskExecutionInfoList").getAsJsonArray();
				taskExecutionInfoImplObj.updateRetryCountMapForTaskExecutionInfoList(taskExecutionInfoList, retryCountMap);
		    }
		    if(taskInfo.has("taskLayoutParametersList"))
		    {
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(getDBSession());
				JsonArray taskLayoutParametersList = taskInfo.get("taskLayoutParametersList").getAsJsonArray();
				taskLayoutParametersImplObj.updateRetryCountMapForTaskLayoutParametersList(taskLayoutParametersList, retryCountMap);
		    }
		    if(taskInfo.has("emailAttachmentLayoutList"))
		    {
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(getDBSession());
				JsonArray emailAttachmentLayoutList = taskInfo.get("emailAttachmentLayoutList").getAsJsonArray();
				emailAttachmentLayoutImplObj.updateRetryCountMapForEmailAttachmentLayoutList(emailAttachmentLayoutList, retryCountMap);
		    }

		}
	    retryCountMap.addProperty("TaskInfo", retryCount);
	}
	public JsonObject uploadTaskInfoList(JsonArray taskInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(taskInfoList);
			if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("alert", responseData.get("alert").getAsString());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			int retryCount = responseData.get("retryCount").getAsInt();
			dataObject.addProperty("success", 1);
			dataObject.addProperty("retryCount", retryCount);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject updateStatusMsg(JsonArray taskInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < taskInfoList.size(); i++)
			{
				JsonObject taskInfoDataObject = taskInfoList.get(i).getAsJsonObject();
				JsonObject taskInfo = taskInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = taskInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = taskInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = taskInfoDataObject.get("errorMessage").getAsString();
				row = sheet.getRow(dataRowIndex);
				lastCell = row.createCell(statusCellIndex);
				if(isSuccessfullyUploaded == 1)
				{
					lastCell.setCellStyle(successCellStyle);
				}
				else
				{
					lastCell.setCellStyle(errorCellStyle);
				} 
				lastCell.setCellValue(errorMessage);
			    			    if(isSuccessfullyUploaded == 1 && taskInfo.has("taskExecutionInfoList"))
			    {
					com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(getDBSession());
					JsonArray taskExecutionInfoList = taskInfo.get("taskExecutionInfoList").getAsJsonArray(); 
					responseData  = taskExecutionInfoImplObj.updateStatusMsg(taskExecutionInfoList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
				}
			    if(isSuccessfullyUploaded == 1 && taskInfo.has("taskLayoutParametersList"))
			    {
					com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(getDBSession());
					JsonArray taskLayoutParametersList = taskInfo.get("taskLayoutParametersList").getAsJsonArray(); 
					responseData  = taskLayoutParametersImplObj.updateStatusMsg(taskLayoutParametersList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
				}
			    if(isSuccessfullyUploaded == 1 && taskInfo.has("emailAttachmentLayoutList"))
			    {
					com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(getDBSession());
					JsonArray emailAttachmentLayoutList = taskInfo.get("emailAttachmentLayoutList").getAsJsonArray(); 
					responseData  = emailAttachmentLayoutImplObj.updateStatusMsg(emailAttachmentLayoutList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
				}

			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadData(JsonArray taskInfoList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < taskInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject taskInfoDataObject = taskInfoList.get(i).getAsJsonObject();
				JsonObject taskInfo = taskInfoDataObject.get("dataObject").getAsJsonObject();
				taskInfoDataObject.addProperty("retryUpload", 0);
				taskInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				taskInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = taskInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, taskInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					taskInfoDataObject.addProperty("retryUpload", 1);
					taskInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					taskInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					taskInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int taskInfoId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !taskInfo.has("taskInfoId"))
				{
					taskInfo.addProperty("attributeNamePrefix", "");
					
					taskInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = taskInfoImplObj.getTaskInfoByLookupFields(session,  taskInfo);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						if(responseData.has("resultsCount")==false)
						{
							continue;							
						}
					}
					else
					{
						isEntityPresent = 1;
					}
					if(isEntityPresent==1)
					{
						JsonObject taskInfoObject = responseData.get("taskInfoDataObject").getAsJsonObject();
						taskInfoId = taskInfoObject.get("taskInfoId").getAsInt();
						taskInfo.addProperty("taskInfoId", taskInfoId);
						updateEntity = true;
					}
				}
				else if(taskInfo.has("taskInfoId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = taskInfoImplObj.createTaskInfo(taskInfo);
				}
				else
				{
					responseData = taskInfoImplObj.updateTaskInfo(taskInfo);
				}
				taskInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					taskInfoId =-1;
					taskInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					taskInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
					if (tx.isActive())
					{
						tx.rollback();						
					}
					continue;
				}
				else
				{
					if (tx.isActive())
					{
						tx.commit();						
					}
				}
				taskInfoId = responseData.get("taskInfoId").getAsInt();
				taskInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    			    if(taskInfo.has("taskExecutionInfoList"))
			    {
				    JsonArray taskExecutionInfoList = taskInfo.get("taskExecutionInfoList").getAsJsonArray();
					com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session, getUserSessionInfo());
					responseData  = taskExecutionInfoImplObj.deleteTaskExecutionInfoListIfKeyColumnsNotFound(session, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = taskExecutionInfoImplObj.uploadData(taskExecutionInfoList, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int taskExecutionInfoListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += taskExecutionInfoListRetryCount;
					if(taskExecutionInfoListRetryCount>0)
					{
						taskInfoDataObject.addProperty("retryChildEntitiesUpload", 1);
					}
				}
			    if(taskInfo.has("taskLayoutParametersList"))
			    {
				    JsonArray taskLayoutParametersList = taskInfo.get("taskLayoutParametersList").getAsJsonArray();
					com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session, getUserSessionInfo());
					responseData  = taskLayoutParametersImplObj.deleteTaskLayoutParametersListIfKeyColumnsNotFound(session, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = taskLayoutParametersImplObj.uploadData(taskLayoutParametersList, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int taskLayoutParametersListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += taskLayoutParametersListRetryCount;
					if(taskLayoutParametersListRetryCount>0)
					{
						taskInfoDataObject.addProperty("retryChildEntitiesUpload", 1);
					}
				}
			    if(taskInfo.has("emailAttachmentLayoutList"))
			    {
				    JsonArray emailAttachmentLayoutList = taskInfo.get("emailAttachmentLayoutList").getAsJsonArray();
					com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session, getUserSessionInfo());
					responseData  = emailAttachmentLayoutImplObj.deleteEmailAttachmentLayoutListIfKeyColumnsNotFound(session, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = emailAttachmentLayoutImplObj.uploadData(emailAttachmentLayoutList, taskInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int emailAttachmentLayoutListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += emailAttachmentLayoutListRetryCount;
					if(emailAttachmentLayoutListRetryCount>0)
					{
						taskInfoDataObject.addProperty("retryChildEntitiesUpload", 1);
					}
				}

			}
			dataObject.addProperty("retryCount", retryCount);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject taskInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getTaskInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From TaskInfo where 1 = 1  ";
			String countHql = "select count(*)  from TaskInfo where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as TaskInfo could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskInfo taskInfo = (TaskInfo) it.next();
				JsonObject taskInfoDataObject = taskInfo.getDataObject(session);
				dataObject.add("taskInfoDataObject", taskInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as TaskInfo could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'TaskInfo' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{return 0;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	
	public int executeAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		/*if(!ServiceAPIUtil.REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus))
		{
			isAPIExecuted = 1;
			return isAPIExecuted;
		}*/		
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getApiName();
		isAPIExecuted = executeAPI(session, requestParametersInfo, requestId, apiName, retValObject);
		if (isAPIExecuted == 1)
		{
			if (retValObject.get("isRequestProcessed").getAsInt() == 1)
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_SUCCESSFULL, requestId);
			}
			else
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_FAILED, requestId);
			}
		}
		return isAPIExecuted;
	}
    	public int executeAPI(Session session, JsonObject requestParametersInfo, int requestId, String apiName, JsonObject retValObject)
	{
		JsonObject dataObject = new JsonObject();
		int isAPIExecuted = 0;
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		try
		{
			if(1 > 2)
			{
			}else if(apiName.equals("userLogin"))
			{
				com.patientapp.request.util.SessionUtil loginObject = new com.patientapp.request.util.SessionUtil();
				//dataObject = loginObject.userLogin(requestParametersInfo);
				isAPIExecuted = 1;
			}
			else if(apiName.equals("userLoginWithBranch"))
			{
				com.patientapp.request.util.SessionUtil loginObject = new com.patientapp.request.util.SessionUtil();
				//dataObject = loginObject.userLoginWithBranch(requestParametersInfo);
				isAPIExecuted = 1;
			}
			if(isAPIExecuted == 1)
			{
				retValObject.add("responseData", dataObject);
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("isRequestProcessed", 1);
				}
				else
				{
					retValObject.addProperty("isRequestProcessed", 0);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	public int executeRollbackAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getRollbackAPIName();
		JsonObject dataObject = new JsonObject();
		try
		{
			//String apiName  = inputDataObject.get("apiName").getAsString();  
			if(1 > 2)
			{
			}if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					ServiceAPIUtil.updateRequestReceivedInfoRolledback(session,  requestId);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}

	public JsonObject getAPIData(Session session, String apiName, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		try
		{
			if(1 > 2)
			{
			}
		else if(apiName.equals("getTaskInfoPropertyValue"))
			{
				JsonObject inputDataObject = getTaskInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getTaskInfo"))
			{
				JsonObject inputDataObject = getTaskInfo(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}		dataObject.addProperty("success", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public int updateAPIStatus(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		String apiName = requestReceived.getApiName();
		JsonObject dataObject = new JsonObject();
		try
		{
			if (1 > 2)
			{
			}
			else if (apiName.equals("doBeforeTransactionApprovedForTaskInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForTaskInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("success", dataObject.get("success").getAsInt());
				}
				else
				{
					retValObject.addProperty("success", 0);
				}
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	
	public JsonObject updateAPIStatus(Session session, RequestReceived requestReceived)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int requestId = requestReceived.getRequestReceivedId();
			JsonObject paramsInfo = new Gson().fromJson(requestReceived.getParamsInfo(), JsonObject.class);
			JsonObject requestReceivedParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
			Integer taskInfoId = requestReceivedParametersInfo.get("taskInfoId").getAsInt();
			TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
			taskInfo.setIsRequestUnderProcesss(0);
			session.merge(taskInfo);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("alert", "Your request could not be processsed as request status could not be updated.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getTaskInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskInfoId = inputForGetAPI.get("taskInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
		taskInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getTaskInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskInfoId = inputForGetAPI.get("taskInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskInfoId", String.valueOf(taskInfoId));
		JsonObject taskInfoListObject = retrieveTaskInfoList(paramsMap);
		if(taskInfoListObject!=null && taskInfoListObject.has("success") && taskInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskInfoList = taskInfoListObject.get("taskInfoList").getAsJsonArray();
			if(taskInfoList.size()>0)
			{
				dataObject.add("taskInfo", taskInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'TaskInfo' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskInfoDataObject, TaskInfo taskInfo);
	public abstract void doExecuteCustomAPIImpl(String customAPIMessage);
	public abstract void doEnrichValuesImpl(JsonObject paramsInfoObject);
	public abstract void doAfterEnrichValuesImpl();
	public abstract void doValidationsImpl();
	public abstract void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex);
	public abstract void doAfterSetValues();
	public abstract void doAfterSelectRowImpl();
	public abstract void doAfterCreateTransactionImpl(JsonObject paramsInfoObj);
	public abstract void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj);	
	public abstract void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doAfterCreateTransactionCommittedImpl();
	public abstract void doAfterUpdateTransactionCommittedImpl();
	public abstract String doGetUpdatedQueryStringForSearchImpl(String queryString);
	public abstract void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap);
	public abstract String doGetOrderByClauseSearchImpl();
	public abstract void doAfterUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doBeforeDeleteTransactionImpl();
	public abstract void doAfterDeleteTransactionImpl();
	public abstract void doAfterLookupRowSelectedImpl(TaskInfo taskInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(TaskInfo taskInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(TaskInfo taskInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
