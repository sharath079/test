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
import com.patientapp.controller.reports.impl.HospitalControllerImpl;
/**
 *
 * @author root
 */
public  abstract class HospitalControllerBase
{
    private static final Logger logger = Logger.getLogger(HospitalControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public HospitalControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public HospitalControllerBase()
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
          
            JsonObject hospitalObj = getHospital(organisationSession, paramsMap);
            if (hospitalObj.get("success").getAsInt() != 1)
            {
                return hospitalObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("hospital",        
            hospitalObj.get("hospital").getAsJsonObject());
        
        
                
        HospitalControllerImpl hospitalControllerImpl = new HospitalControllerImpl();
        hospitalControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getHospital(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer hospitalId = Integer.valueOf(paramsMap.get("hospitalId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select h.hospitalId as h_hospitalId , h.hospName as h_hospName , h.hospAddress as h_hospAddress  from Hospital h  Where 2>1  and h.hospitalId = ?   ";
            
        	Query query;
        	String queryCode = "Hospital";
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
	        	         
	        query.setParameter(paramsPosIndex, hospitalId);
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
				
            	String h_hospitalId = String.valueOf(resultRowColumnsList[0]);
            	if(h_hospitalId.equals("null"))
            	{
            		h_hospitalId  = "";
            	}
            	
            	String h_hospName = String.valueOf(resultRowColumnsList[1]);
            	if(h_hospName.equals("null"))
            	{
            		h_hospName  = "";
            	}
            	
            	String h_hospAddress = String.valueOf(resultRowColumnsList[2]);
            	if(h_hospAddress.equals("null"))
            	{
            		h_hospAddress  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("hospName", h_hospName);
                
                	
                    resultsDataObj.addProperty("hospAddress", h_hospAddress);
                
                dataObject.addProperty("success", 1);
                dataObject.add("hospital", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("hospital information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "hospital information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
