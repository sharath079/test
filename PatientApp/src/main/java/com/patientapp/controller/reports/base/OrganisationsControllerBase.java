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
import com.patientapp.controller.reports.impl.OrganisationsControllerImpl;
/**
 *
 * @author root
 */
public  abstract class OrganisationsControllerBase
{
    private static final Logger logger = Logger.getLogger(OrganisationsControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public OrganisationsControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public OrganisationsControllerBase()
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
          
            JsonObject organisationsObj = getOrganisations(organisationSession, paramsMap);
            if (organisationsObj.get("success").getAsInt() != 1)
            {
                return organisationsObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("organisations",        
            organisationsObj.get("organisations").getAsJsonObject());
        
        
                
        OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl();
        organisationsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getOrganisations(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer organisationsId = Integer.valueOf(paramsMap.get("organisationsId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select o.organisationsId as o_organisationsId , o.organisationName as o_organisationName , o.addressLine1 as o_addressLine1 , o.addressLine2 as o_addressLine2 , o.city as o_city , o.residentState as o_residentState , o.pinCode as o_pinCode , o.databaseName as o_databaseName , o.country as o_country  from Organisations o  Where 2>1  and o.organisationsId = ?   ";
            
        	Query query;
        	String queryCode = "Organisations";
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
	        	         
	        query.setParameter(paramsPosIndex, organisationsId);
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
				
            	String o_organisationsId = String.valueOf(resultRowColumnsList[0]);
            	if(o_organisationsId.equals("null"))
            	{
            		o_organisationsId  = "";
            	}
            	
            	String o_organisationName = String.valueOf(resultRowColumnsList[1]);
            	if(o_organisationName.equals("null"))
            	{
            		o_organisationName  = "";
            	}
            	
            	String o_addressLine1 = String.valueOf(resultRowColumnsList[2]);
            	if(o_addressLine1.equals("null"))
            	{
            		o_addressLine1  = "";
            	}
            	
            	String o_addressLine2 = String.valueOf(resultRowColumnsList[3]);
            	if(o_addressLine2.equals("null"))
            	{
            		o_addressLine2  = "";
            	}
            	
            	String o_city = String.valueOf(resultRowColumnsList[4]);
            	if(o_city.equals("null"))
            	{
            		o_city  = "";
            	}
            	
            	String o_residentState = String.valueOf(resultRowColumnsList[5]);
            	if(o_residentState.equals("null"))
            	{
            		o_residentState  = "";
            	}
            	
            	String o_pinCode = String.valueOf(resultRowColumnsList[6]);
            	if(o_pinCode.equals("null"))
            	{
            		o_pinCode  = "";
            	}
            	
            	String o_databaseName = String.valueOf(resultRowColumnsList[7]);
            	if(o_databaseName.equals("null"))
            	{
            		o_databaseName  = "";
            	}
            	
            	String o_country = String.valueOf(resultRowColumnsList[8]);
            	if(o_country.equals("null"))
            	{
            		o_country  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("organisationName", o_organisationName);
                
                	
                    resultsDataObj.addProperty("addressLine1", o_addressLine1);
                
                	
                    resultsDataObj.addProperty("addressLine2", o_addressLine2);
                
                	
                    resultsDataObj.addProperty("city", o_city);
                
                	
                    resultsDataObj.addProperty("residentState", o_residentState);
                
                	
                    resultsDataObj.addProperty("pinCode", o_pinCode);
                
                	
                    resultsDataObj.addProperty("databaseName", o_databaseName);
                
                	
                    resultsDataObj.addProperty("country", o_country);
                
                dataObject.addProperty("success", 1);
                dataObject.add("organisations", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("organisations information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "organisations information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
