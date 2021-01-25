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
import com.patientapp.controller.reports.impl.TaskSentInfoControllerImpl;
/**
 *
 * @author root
 */
public  abstract class TaskSentInfoControllerBase
{
    private static final Logger logger = Logger.getLogger(TaskSentInfoControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public TaskSentInfoControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public TaskSentInfoControllerBase()
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
          
            JsonObject taskSentInfoObj = getTaskSentInfo(organisationSession, paramsMap);
            if (taskSentInfoObj.get("success").getAsInt() != 1)
            {
                return taskSentInfoObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("taskSentInfo",        
            taskSentInfoObj.get("taskSentInfo").getAsJsonObject());
        
        
                
        TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl();
        taskSentInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getTaskSentInfo(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer taskSentInfoId = Integer.valueOf(paramsMap.get("taskSentInfoId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select tsi.taskSentInfoId as tsi_taskSentInfoId , ifnull((select taskName from TaskInfo  where taskInfoId = tsi.taskInfoId), 'Not Found') as tsi_taskInfoDisplayText , tsi.taskInfoId as tsi_taskInfoId , tsi.targetEntityId as tsi_targetEntityId , tsi.targetUserId as tsi_targetUserId , tsi.notificationMedium as tsi_notificationMedium , tsi.layoutInfoText as tsi_layoutInfoText , tsi.notificationSentTime as tsi_notificationSentTime  from TaskSentInfo tsi  Where 2>1  and tsi.taskSentInfoId = ?   ";
            
        	Query query;
        	String queryCode = "TaskSentInfo";
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
	        	         
	        query.setParameter(paramsPosIndex, taskSentInfoId);
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
				
            	String tsi_taskSentInfoId = String.valueOf(resultRowColumnsList[0]);
            	if(tsi_taskSentInfoId.equals("null"))
            	{
            		tsi_taskSentInfoId  = "";
            	}
            	
            	String tsi_taskInfoDisplayText = String.valueOf(resultRowColumnsList[1]);
            	if(tsi_taskInfoDisplayText.equals("null"))
            	{
            		tsi_taskInfoDisplayText  = "";
            	}
            	
            	String tsi_taskInfoId = String.valueOf(resultRowColumnsList[2]);
            	if(tsi_taskInfoId.equals("null"))
            	{
            		tsi_taskInfoId  = "";
            	}
            	
            	String tsi_targetEntityId = String.valueOf(resultRowColumnsList[3]);
            	if(tsi_targetEntityId.equals("null"))
            	{
            		tsi_targetEntityId  = "";
            	}
            	
            	String tsi_targetUserId = String.valueOf(resultRowColumnsList[4]);
            	if(tsi_targetUserId.equals("null"))
            	{
            		tsi_targetUserId  = "";
            	}
            	
            	String tsi_notificationMedium = String.valueOf(resultRowColumnsList[5]);
            	if(tsi_notificationMedium.equals("null"))
            	{
            		tsi_notificationMedium  = "";
            	}
            	
            	String tsi_layoutInfoText = String.valueOf(resultRowColumnsList[6]);
            	if(tsi_layoutInfoText.equals("null"))
            	{
            		tsi_layoutInfoText  = "";
            	}
            	
            	String tsi_notificationSentTime = String.valueOf(resultRowColumnsList[7]);
            	if(tsi_notificationSentTime.equals("null"))
            	{
            		tsi_notificationSentTime  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("taskInfo", tsi_taskInfoDisplayText);
                
                	
                    resultsDataObj.addProperty("targetEntityId", tsi_targetEntityId);
                
                	
                    resultsDataObj.addProperty("targetUserId", tsi_targetUserId);
                
                	
                    resultsDataObj.addProperty("notificationMedium", tsi_notificationMedium);
                
                	
                    resultsDataObj.addProperty("layoutInfoText", tsi_layoutInfoText);
                
                	
                    resultsDataObj.addProperty("notificationSentTime", tsi_notificationSentTime);
                
                dataObject.addProperty("success", 1);
                dataObject.add("taskSentInfo", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("taskSentInfo information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "taskSentInfo information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
