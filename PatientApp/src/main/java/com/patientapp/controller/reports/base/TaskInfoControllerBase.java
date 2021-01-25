/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patientapp.controller.reports.base;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.layout.LayoutParserUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.BusinessAPIs;
import com.patientapp.controller.reports.impl.TaskInfoControllerImpl;
/**
 *
 * @author root
 */
public  abstract class TaskInfoControllerBase
{
    private static final Logger logger = Logger.getLogger(TaskInfoControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public TaskInfoControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public TaskInfoControllerBase()
    {
    }
    public static JsonObject getPageData(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        JsonObject fieldsDataWithoutOverrideWhereClause = new JsonObject();
        JsonObject fieldsDataWithOverrideWhereClause = new JsonObject();   
        JsonObject layoutCustomDataFieldsObject = new JsonObject();     
    	JsonObject tablesData = new JsonObject();
    	JsonObject graphsData = new JsonObject();
          
            JsonObject taskInfoObj = getTaskInfo(organisationSession, paramsMap);
            if (taskInfoObj.get("success").getAsInt() != 1)
            {
                return taskInfoObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("taskInfo",        
            taskInfoObj.get("taskInfo").getAsJsonObject());
        
        
                
        TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl();
        taskInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    		   
            JsonObject taskLayoutParametersObj = getTaskLayoutParameters(organisationSession, paramsMap);
            if (taskLayoutParametersObj.get("success").getAsInt() != 1)
            {
                return taskLayoutParametersObj;
            }
            tablesData.add("taskLayoutParametersDataRowsList", 
            taskLayoutParametersObj.get("taskLayoutParametersDataRowsList").getAsJsonArray());
        
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getTaskInfo(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer taskInfoId = Integer.valueOf(paramsMap.get("taskInfoId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select ti.taskInfoId as ti_taskInfoId , ti.taskName as ti_taskName , ti.taskDescription as ti_taskDescription , ti.taskType as ti_taskType , ti.apiName as ti_apiName , ti.targetEntityQuery as ti_targetEntityQuery , ti.targetUserIdColumnAlias as ti_targetUserIdColumnAlias , ti.targetEntityIdColumnAlias as ti_targetEntityIdColumnAlias , ti.enableInAppNotification as ti_enableInAppNotification , ti.inAppNotificationLayout as ti_inAppNotificationLayout , ti.enableEmailNotification as ti_enableEmailNotification , ti.emailColumnAlias as ti_emailColumnAlias , ti.emailNotificationLayout as ti_emailNotificationLayout , ti.emailSubject as ti_emailSubject , ti.enableSmsNotification as ti_enableSmsNotification , ti.smsColumnAlias as ti_smsColumnAlias , ti.smsNotificationLayout as ti_smsNotificationLayout , ti.sMSText as ti_sMSText , ti.isActive as ti_isActive , ti.taskStartDate as ti_taskStartDate , ti.taskFrequency as ti_taskFrequency , ti.taskFrequencyUnit as ti_taskFrequencyUnit , ti.isRecurring as ti_isRecurring , ti.firstRunType as ti_firstRunType , ti.dateColumnAlias as ti_dateColumnAlias , ti.firstRunDateCalculationMethod as ti_firstRunDateCalculationMethod , ti.firstRunDateGapInYears as ti_firstRunDateGapInYears , ti.firstRunDateGapInMonths as ti_firstRunDateGapInMonths , ti.firstRunDateGapInDays as ti_firstRunDateGapInDays , ti.firstRunDateGapInHours as ti_firstRunDateGapInHours , ti.firstRunDateGapInMinutes as ti_firstRunDateGapInMinutes , ti.firstRunDateGapInSeconds as ti_firstRunDateGapInSeconds  from TaskInfo ti  Where 2>1  and ti.taskInfoId = ?   ";
            
        	Query query;
        	String queryCode = "TaskInfo";
        	String useNativeQuery = LayoutParserUtil.getUseNativeQueryStatus(organisationSession, queryCode);
        	if(useNativeQuery.equalsIgnoreCase("Yes"))
        	{
        		query = organisationSession.createSQLQuery(selectQuery);
        	}
        	else
        	{
        		query = organisationSession.createQuery(selectQuery);
        	}
            int paramsPosIndex = 0;
	        	         
	        query.setParameter(paramsPosIndex, taskInfoId);
            paramsPosIndex++;                
	        
	        //based on conditional parameter
      		
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         if(iterator.hasNext())
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
				
            	String ti_taskInfoId = String.valueOf(resultRowColumnsList[0]);
            	if(ti_taskInfoId.equals("null"))
            	{
            		ti_taskInfoId  = "";
            	}
            	
            	String ti_taskName = String.valueOf(resultRowColumnsList[1]);
            	if(ti_taskName.equals("null"))
            	{
            		ti_taskName  = "";
            	}
            	
            	String ti_taskDescription = String.valueOf(resultRowColumnsList[2]);
            	if(ti_taskDescription.equals("null"))
            	{
            		ti_taskDescription  = "";
            	}
            	
            	String ti_taskType = String.valueOf(resultRowColumnsList[3]);
            	if(ti_taskType.equals("null"))
            	{
            		ti_taskType  = "";
            	}
            	
            	String ti_apiName = String.valueOf(resultRowColumnsList[4]);
            	if(ti_apiName.equals("null"))
            	{
            		ti_apiName  = "";
            	}
            	
            	String ti_targetEntityQuery = String.valueOf(resultRowColumnsList[5]);
            	if(ti_targetEntityQuery.equals("null"))
            	{
            		ti_targetEntityQuery  = "";
            	}
            	
            	String ti_targetUserIdColumnAlias = String.valueOf(resultRowColumnsList[6]);
            	if(ti_targetUserIdColumnAlias.equals("null"))
            	{
            		ti_targetUserIdColumnAlias  = "";
            	}
            	
            	String ti_targetEntityIdColumnAlias = String.valueOf(resultRowColumnsList[7]);
            	if(ti_targetEntityIdColumnAlias.equals("null"))
            	{
            		ti_targetEntityIdColumnAlias  = "";
            	}
            	
            	String ti_enableInAppNotification = String.valueOf(resultRowColumnsList[8]);
            	if(ti_enableInAppNotification.equals("null"))
            	{
            		ti_enableInAppNotification  = "";
            	}
            	
            	String ti_inAppNotificationLayout = String.valueOf(resultRowColumnsList[9]);
            	if(ti_inAppNotificationLayout.equals("null"))
            	{
            		ti_inAppNotificationLayout  = "";
            	}
            	
            	String ti_enableEmailNotification = String.valueOf(resultRowColumnsList[10]);
            	if(ti_enableEmailNotification.equals("null"))
            	{
            		ti_enableEmailNotification  = "";
            	}
            	
            	String ti_emailColumnAlias = String.valueOf(resultRowColumnsList[11]);
            	if(ti_emailColumnAlias.equals("null"))
            	{
            		ti_emailColumnAlias  = "";
            	}
            	
            	String ti_emailNotificationLayout = String.valueOf(resultRowColumnsList[12]);
            	if(ti_emailNotificationLayout.equals("null"))
            	{
            		ti_emailNotificationLayout  = "";
            	}
            	
            	String ti_emailSubject = String.valueOf(resultRowColumnsList[13]);
            	if(ti_emailSubject.equals("null"))
            	{
            		ti_emailSubject  = "";
            	}
            	
            	String ti_enableSmsNotification = String.valueOf(resultRowColumnsList[14]);
            	if(ti_enableSmsNotification.equals("null"))
            	{
            		ti_enableSmsNotification  = "";
            	}
            	
            	String ti_smsColumnAlias = String.valueOf(resultRowColumnsList[15]);
            	if(ti_smsColumnAlias.equals("null"))
            	{
            		ti_smsColumnAlias  = "";
            	}
            	
            	String ti_smsNotificationLayout = String.valueOf(resultRowColumnsList[16]);
            	if(ti_smsNotificationLayout.equals("null"))
            	{
            		ti_smsNotificationLayout  = "";
            	}
            	
            	String ti_sMSText = String.valueOf(resultRowColumnsList[17]);
            	if(ti_sMSText.equals("null"))
            	{
            		ti_sMSText  = "";
            	}
            	
            	String ti_isActive = String.valueOf(resultRowColumnsList[18]);
            	if(ti_isActive.equals("null"))
            	{
            		ti_isActive  = "";
            	}
            	
            	String ti_taskStartDate = String.valueOf(resultRowColumnsList[19]);
            	if(ti_taskStartDate.equals("null"))
            	{
            		ti_taskStartDate  = "";
            	}
            	
            	String ti_taskFrequency = String.valueOf(resultRowColumnsList[20]);
            	if(ti_taskFrequency.equals("null"))
            	{
            		ti_taskFrequency  = "";
            	}
            	
            	String ti_taskFrequencyUnit = String.valueOf(resultRowColumnsList[21]);
            	if(ti_taskFrequencyUnit.equals("null"))
            	{
            		ti_taskFrequencyUnit  = "";
            	}
            	
            	String ti_isRecurring = String.valueOf(resultRowColumnsList[22]);
            	if(ti_isRecurring.equals("null"))
            	{
            		ti_isRecurring  = "";
            	}
            	
            	String ti_firstRunType = String.valueOf(resultRowColumnsList[23]);
            	if(ti_firstRunType.equals("null"))
            	{
            		ti_firstRunType  = "";
            	}
            	
            	String ti_dateColumnAlias = String.valueOf(resultRowColumnsList[24]);
            	if(ti_dateColumnAlias.equals("null"))
            	{
            		ti_dateColumnAlias  = "";
            	}
            	
            	String ti_firstRunDateCalculationMethod = String.valueOf(resultRowColumnsList[25]);
            	if(ti_firstRunDateCalculationMethod.equals("null"))
            	{
            		ti_firstRunDateCalculationMethod  = "";
            	}
            	
            	String ti_firstRunDateGapInYears = String.valueOf(resultRowColumnsList[26]);
            	if(ti_firstRunDateGapInYears.equals("null"))
            	{
            		ti_firstRunDateGapInYears  = "";
            	}
            	
            	String ti_firstRunDateGapInMonths = String.valueOf(resultRowColumnsList[27]);
            	if(ti_firstRunDateGapInMonths.equals("null"))
            	{
            		ti_firstRunDateGapInMonths  = "";
            	}
            	
            	String ti_firstRunDateGapInDays = String.valueOf(resultRowColumnsList[28]);
            	if(ti_firstRunDateGapInDays.equals("null"))
            	{
            		ti_firstRunDateGapInDays  = "";
            	}
            	
            	String ti_firstRunDateGapInHours = String.valueOf(resultRowColumnsList[29]);
            	if(ti_firstRunDateGapInHours.equals("null"))
            	{
            		ti_firstRunDateGapInHours  = "";
            	}
            	
            	String ti_firstRunDateGapInMinutes = String.valueOf(resultRowColumnsList[30]);
            	if(ti_firstRunDateGapInMinutes.equals("null"))
            	{
            		ti_firstRunDateGapInMinutes  = "";
            	}
            	
            	String ti_firstRunDateGapInSeconds = String.valueOf(resultRowColumnsList[31]);
            	if(ti_firstRunDateGapInSeconds.equals("null"))
            	{
            		ti_firstRunDateGapInSeconds  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("taskName", ti_taskName);
                
                	
                    resultsDataObj.addProperty("taskDescription", ti_taskDescription);
                
                	
                    resultsDataObj.addProperty("taskType", ti_taskType);
                
                	
                    resultsDataObj.addProperty("apiName", ti_apiName);
                
                	
                    resultsDataObj.addProperty("targetEntityQuery", ti_targetEntityQuery);
                
                	
                    resultsDataObj.addProperty("targetUserIdColumnAlias", ti_targetUserIdColumnAlias);
                
                	
                    resultsDataObj.addProperty("targetEntityIdColumnAlias", ti_targetEntityIdColumnAlias);
                
                	
                    resultsDataObj.addProperty("enableInAppNotification", ti_enableInAppNotification);
                
                	
                    resultsDataObj.addProperty("inAppNotificationLayout", ti_inAppNotificationLayout);
                
                	
                    resultsDataObj.addProperty("enableEmailNotification", ti_enableEmailNotification);
                
                	
                    resultsDataObj.addProperty("emailColumnAlias", ti_emailColumnAlias);
                
                	
                    resultsDataObj.addProperty("emailNotificationLayout", ti_emailNotificationLayout);
                
                	
                    resultsDataObj.addProperty("emailSubject", ti_emailSubject);
                
                	
                    resultsDataObj.addProperty("enableSmsNotification", ti_enableSmsNotification);
                
                	
                    resultsDataObj.addProperty("smsColumnAlias", ti_smsColumnAlias);
                
                	
                    resultsDataObj.addProperty("smsNotificationLayout", ti_smsNotificationLayout);
                
                	
                    resultsDataObj.addProperty("sMSText", ti_sMSText);
                
                	
                    resultsDataObj.addProperty("isActive", ti_isActive);
                
                	
                    resultsDataObj.addProperty("taskStartDate", ti_taskStartDate);
                
                	
                    resultsDataObj.addProperty("taskFrequency", ti_taskFrequency);
                
                	
                    resultsDataObj.addProperty("taskFrequencyUnit", ti_taskFrequencyUnit);
                
                	
                    resultsDataObj.addProperty("isRecurring", ti_isRecurring);
                
                	
                    resultsDataObj.addProperty("firstRunType", ti_firstRunType);
                
                	
                    resultsDataObj.addProperty("dateColumnAlias", ti_dateColumnAlias);
                
                	
                    resultsDataObj.addProperty("firstRunDateCalculationMethod", ti_firstRunDateCalculationMethod);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInYears", ti_firstRunDateGapInYears);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInMonths", ti_firstRunDateGapInMonths);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInDays", ti_firstRunDateGapInDays);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInHours", ti_firstRunDateGapInHours);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInMinutes", ti_firstRunDateGapInMinutes);
                
                	
                    resultsDataObj.addProperty("firstRunDateGapInSeconds", ti_firstRunDateGapInSeconds);
                
                dataObject.addProperty("success", 1);
                dataObject.add("taskInfo", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("taskInfo information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "taskInfo information could not be retrieved.");
        return dataObject;
    }

	
   
public static JsonObject getTaskLayoutParameters(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            int noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));
            int noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));
            noOfRecordsToFetch = 100000;
              
			
			
			Integer taskInfoId = Integer.valueOf(paramsMap.get("taskInfoId"));							
							
			
			
			
			
			
			
			
			
			
            
            String countQueryText = "select count(*)  from TaskLayoutParameters tlp  Where 2>1  and tlp.taskInfoId = ? ";
            String selectQuery = "select tlp.taskLayoutParametersId as tlp_taskLayoutParametersId , tlp.taskInfoId as tlp_taskInfoId , tlp.parameterName as tlp_parameterName , tlp.parameterValueType as tlp_parameterValueType , tlp.parameterValue as tlp_parameterValue  from TaskLayoutParameters tlp  Where 2>1  and tlp.taskInfoId = ? ";
            
        	selectQuery += "  ";
        	selectQuery += "  ";
        	countQueryText += "  ";
        	Query query;
        	String queryCode = "TaskLayoutParameters";
        	String useNativeQuery = LayoutParserUtil.getUseNativeQueryStatus(organisationSession, queryCode);
        	if(useNativeQuery.equalsIgnoreCase("Yes"))
        	{
        		query = organisationSession.createSQLQuery(selectQuery);
        	}
        	else
        	{
        		query = organisationSession.createQuery(selectQuery);
        	}
        	int paramsPosIndex = 0;
        	
            
            query.setParameter(paramsPosIndex, taskInfoId);
            paramsPosIndex++;
            
             
      		//based on conditional parameter
      		
	        query.setFirstResult(noOfRecordsAlreadyFetched);
	        query.setMaxResults(noOfRecordsToFetch);
	        JsonArray taskLayoutParametersDataRowsList = new JsonArray();
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
            	//Loop query columns
	        	JsonObject taskLayoutParametersObject = new JsonObject();
            	
            	String tlp_taskLayoutParametersId = String.valueOf(resultRowColumnsList[0]);
            	if(tlp_taskLayoutParametersId.equals("null"))
            	{
            		tlp_taskLayoutParametersId  = "";
            	}
            	taskLayoutParametersObject.addProperty("tlp_taskLayoutParametersId", tlp_taskLayoutParametersId);  
            	
            	String tlp_taskInfoId = String.valueOf(resultRowColumnsList[1]);
            	if(tlp_taskInfoId.equals("null"))
            	{
            		tlp_taskInfoId  = "";
            	}
            	taskLayoutParametersObject.addProperty("tlp_taskInfoId", tlp_taskInfoId);  
            	
            	String tlp_parameterName = String.valueOf(resultRowColumnsList[2]);
            	if(tlp_parameterName.equals("null"))
            	{
            		tlp_parameterName  = "";
            	}
            	taskLayoutParametersObject.addProperty("tlp_parameterName", tlp_parameterName);  
            	
            	String tlp_parameterValueType = String.valueOf(resultRowColumnsList[3]);
            	if(tlp_parameterValueType.equals("null"))
            	{
            		tlp_parameterValueType  = "";
            	}
            	taskLayoutParametersObject.addProperty("tlp_parameterValueType", tlp_parameterValueType);  
            	
            	String tlp_parameterValue = String.valueOf(resultRowColumnsList[4]);
            	if(tlp_parameterValue.equals("null"))
            	{
            		tlp_parameterValue  = "";
            	}
            	taskLayoutParametersObject.addProperty("tlp_parameterValue", tlp_parameterValue);  
            	
                 
            		taskLayoutParametersObject.addProperty("parameterName", tlp_parameterName);            		                        
                 
            		taskLayoutParametersObject.addProperty("parameterValueType", tlp_parameterValueType);            		                        
                 
            		taskLayoutParametersObject.addProperty("parameterValue", tlp_parameterValue);            		                        
                           
            	    
                           
                int hierarchyLevel = 1;
                 
				TaskInfoControllerImpl TaskInfoControllerImpl = new TaskInfoControllerImpl(); 
				TaskInfoControllerImpl.processQueryResultItemDataObject(resultRowColumnsList, taskLayoutParametersObject, "taskLayoutParameters");
                taskLayoutParametersDataRowsList.add(taskLayoutParametersObject);
            	
                                
                //template for leaf node
            	    
                                
            }
            TaskInfoControllerImpl TaskInfoControllerImpl = new TaskInfoControllerImpl();
            TaskInfoControllerImpl.processQueryResultList(taskLayoutParametersDataRowsList, "taskLayoutParameters");
               
            dataObject.addProperty("success", 1);
            dataObject.add("taskLayoutParametersDataRowsList", taskLayoutParametersDataRowsList);
            return dataObject;
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));            
            dataObject.addProperty("alert", "taskLayoutParameters information could not retrieved");
            dataObject.addProperty("success", 0);
            return dataObject;
        }
        finally
        {            
        }
    }
    
    
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
