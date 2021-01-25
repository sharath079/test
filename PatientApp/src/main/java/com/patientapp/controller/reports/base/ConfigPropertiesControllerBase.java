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
import com.patientapp.controller.reports.impl.ConfigPropertiesControllerImpl;
/**
 *
 * @author root
 */
public  abstract class ConfigPropertiesControllerBase
{
    private static final Logger logger = Logger.getLogger(ConfigPropertiesControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public ConfigPropertiesControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public ConfigPropertiesControllerBase()
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
          
            JsonObject configPropertiesObj = getConfigProperties(organisationSession, paramsMap);
            if (configPropertiesObj.get("success").getAsInt() != 1)
            {
                return configPropertiesObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("configProperties",        
            configPropertiesObj.get("configProperties").getAsJsonObject());
        
        
                
        ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl();
        configPropertiesControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getConfigProperties(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer configPropertiesId = Integer.valueOf(paramsMap.get("configPropertiesId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select cp.configPropertiesId as cp_configPropertiesId , cp.propertyName as cp_propertyName , cp.propertyValue as cp_propertyValue  from ConfigProperties cp  Where 2>1  and cp.configPropertiesId = ?   ";
            
        	Query query;
        	String queryCode = "ConfigProperties";
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
	        	         
	        query.setParameter(paramsPosIndex, configPropertiesId);
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
				
            	String cp_configPropertiesId = String.valueOf(resultRowColumnsList[0]);
            	if(cp_configPropertiesId.equals("null"))
            	{
            		cp_configPropertiesId  = "";
            	}
            	
            	String cp_propertyName = String.valueOf(resultRowColumnsList[1]);
            	if(cp_propertyName.equals("null"))
            	{
            		cp_propertyName  = "";
            	}
            	
            	String cp_propertyValue = String.valueOf(resultRowColumnsList[2]);
            	if(cp_propertyValue.equals("null"))
            	{
            		cp_propertyValue  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("propertyName", cp_propertyName);
                
                	
                    resultsDataObj.addProperty("propertyValue", cp_propertyValue);
                
                dataObject.addProperty("success", 1);
                dataObject.add("configProperties", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("configProperties information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "configProperties information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
