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
import com.patientapp.controller.reports.impl.ContactUsControllerImpl;
/**
 *
 * @author root
 */
public  abstract class ContactUsControllerBase
{
    private static final Logger logger = Logger.getLogger(ContactUsControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public ContactUsControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public ContactUsControllerBase()
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
          
            JsonObject contactUsObj = getContactUs(organisationSession, paramsMap);
            if (contactUsObj.get("success").getAsInt() != 1)
            {
                return contactUsObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("contactUs",        
            contactUsObj.get("contactUs").getAsJsonObject());
        
        
                
        ContactUsControllerImpl contactUsControllerImpl = new ContactUsControllerImpl();
        contactUsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getContactUs(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer contactUsId = Integer.valueOf(paramsMap.get("contactUsId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select cu.contactUsId as cu_contactUsId , cu.fullName as cu_fullName , cu.emailId as cu_emailId , cu.contactNo as cu_contactNo  from ContactUs cu  Where 2>1  and cu.contactUsId = ?   ";
            
        	Query query;
        	String queryCode = "ContactUs";
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
	        	         
	        query.setParameter(paramsPosIndex, contactUsId);
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
				
            	String cu_contactUsId = String.valueOf(resultRowColumnsList[0]);
            	if(cu_contactUsId.equals("null"))
            	{
            		cu_contactUsId  = "";
            	}
            	
            	String cu_fullName = String.valueOf(resultRowColumnsList[1]);
            	if(cu_fullName.equals("null"))
            	{
            		cu_fullName  = "";
            	}
            	
            	String cu_emailId = String.valueOf(resultRowColumnsList[2]);
            	if(cu_emailId.equals("null"))
            	{
            		cu_emailId  = "";
            	}
            	
            	String cu_contactNo = String.valueOf(resultRowColumnsList[3]);
            	if(cu_contactNo.equals("null"))
            	{
            		cu_contactNo  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("fullName", cu_fullName);
                
                	
                    resultsDataObj.addProperty("emailId", cu_emailId);
                
                	
                    resultsDataObj.addProperty("contactNo", cu_contactNo);
                
                dataObject.addProperty("success", 1);
                dataObject.add("contactUs", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("contactUs information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "contactUs information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
