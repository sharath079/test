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
import com.patientapp.controller.reports.impl.LoginSessionInfoControllerImpl;
/**
 *
 * @author root
 */
public  abstract class LoginSessionInfoControllerBase
{
    private static final Logger logger = Logger.getLogger(LoginSessionInfoControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public LoginSessionInfoControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public LoginSessionInfoControllerBase()
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
          
            JsonObject loginSessionInfoObj = getLoginSessionInfo(organisationSession, paramsMap);
            if (loginSessionInfoObj.get("success").getAsInt() != 1)
            {
                return loginSessionInfoObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("loginSessionInfo",        
            loginSessionInfoObj.get("loginSessionInfo").getAsJsonObject());
        
        
                
        LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl();
        loginSessionInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getLoginSessionInfo(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer loginSessionInfoId = Integer.valueOf(paramsMap.get("loginSessionInfoId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select lsi.loginSessionInfoId as lsi_loginSessionInfoId , lsi.loginUserType as lsi_loginUserType , lsi.selfServiceUserType as lsi_selfServiceUserType , lsi.sessionId as lsi_sessionId , lsi.userId as lsi_userId , lsi.loginTime as lsi_loginTime  from LoginSessionInfo lsi  Where 2>1  and lsi.loginSessionInfoId = ?   ";
            
        	Query query;
        	String queryCode = "LoginSessionInfo";
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
	        	         
	        query.setParameter(paramsPosIndex, loginSessionInfoId);
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
				
            	String lsi_loginSessionInfoId = String.valueOf(resultRowColumnsList[0]);
            	if(lsi_loginSessionInfoId.equals("null"))
            	{
            		lsi_loginSessionInfoId  = "";
            	}
            	
            	String lsi_loginUserType = String.valueOf(resultRowColumnsList[1]);
            	if(lsi_loginUserType.equals("null"))
            	{
            		lsi_loginUserType  = "";
            	}
            	
            	String lsi_selfServiceUserType = String.valueOf(resultRowColumnsList[2]);
            	if(lsi_selfServiceUserType.equals("null"))
            	{
            		lsi_selfServiceUserType  = "";
            	}
            	
            	String lsi_sessionId = String.valueOf(resultRowColumnsList[3]);
            	if(lsi_sessionId.equals("null"))
            	{
            		lsi_sessionId  = "";
            	}
            	
            	String lsi_userId = String.valueOf(resultRowColumnsList[4]);
            	if(lsi_userId.equals("null"))
            	{
            		lsi_userId  = "";
            	}
            	
            	String lsi_loginTime = String.valueOf(resultRowColumnsList[5]);
            	if(lsi_loginTime.equals("null"))
            	{
            		lsi_loginTime  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("loginUserType", lsi_loginUserType);
                
                	
                    resultsDataObj.addProperty("selfServiceUserType", lsi_selfServiceUserType);
                
                	
                    resultsDataObj.addProperty("sessionId", lsi_sessionId);
                
                	
                    resultsDataObj.addProperty("userId", lsi_userId);
                
                	
                    resultsDataObj.addProperty("loginTime", lsi_loginTime);
                
                dataObject.addProperty("success", 1);
                dataObject.add("loginSessionInfo", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("loginSessionInfo information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "loginSessionInfo information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
