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
import com.patientapp.controller.reports.impl.PrivilegeGroupControllerImpl;
/**
 *
 * @author root
 */
public  abstract class PrivilegeGroupControllerBase
{
    private static final Logger logger = Logger.getLogger(PrivilegeGroupControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public PrivilegeGroupControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public PrivilegeGroupControllerBase()
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
          
            JsonObject privilegeGroupObj = getPrivilegeGroup(organisationSession, paramsMap);
            if (privilegeGroupObj.get("success").getAsInt() != 1)
            {
                return privilegeGroupObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("privilegeGroup",        
            privilegeGroupObj.get("privilegeGroup").getAsJsonObject());
        
        
                
        PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl();
        privilegeGroupControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    		   
            JsonObject privilegeGroupItemsObj = getPrivilegeGroupItems(organisationSession, paramsMap);
            if (privilegeGroupItemsObj.get("success").getAsInt() != 1)
            {
                return privilegeGroupItemsObj;
            }
            tablesData.add("privilegeGroupItemsDataRowsList", 
            privilegeGroupItemsObj.get("privilegeGroupItemsDataRowsList").getAsJsonArray());
        
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getPrivilegeGroup(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer privilegeGroupId = Integer.valueOf(paramsMap.get("privilegeGroupId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select pg.privilegeGroupId as pg_privilegeGroupId , pg.privilegeGroupName as pg_privilegeGroupName , pg.privilegeCode as pg_privilegeCode , pg.applicableUserType as pg_applicableUserType , pg.selfServiceUser as pg_selfServiceUser , pg.privilegeGroupDescription as pg_privilegeGroupDescription  from PrivilegeGroup pg  Where 2>1  and pg.privilegeGroupId = ?   ";
            
        	Query query;
        	String queryCode = "PrivilegeGroup";
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
	        	         
	        query.setParameter(paramsPosIndex, privilegeGroupId);
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
				
            	String pg_privilegeGroupId = String.valueOf(resultRowColumnsList[0]);
            	if(pg_privilegeGroupId.equals("null"))
            	{
            		pg_privilegeGroupId  = "";
            	}
            	
            	String pg_privilegeGroupName = String.valueOf(resultRowColumnsList[1]);
            	if(pg_privilegeGroupName.equals("null"))
            	{
            		pg_privilegeGroupName  = "";
            	}
            	
            	String pg_privilegeCode = String.valueOf(resultRowColumnsList[2]);
            	if(pg_privilegeCode.equals("null"))
            	{
            		pg_privilegeCode  = "";
            	}
            	
            	String pg_applicableUserType = String.valueOf(resultRowColumnsList[3]);
            	if(pg_applicableUserType.equals("null"))
            	{
            		pg_applicableUserType  = "";
            	}
            	
            	String pg_selfServiceUser = String.valueOf(resultRowColumnsList[4]);
            	if(pg_selfServiceUser.equals("null"))
            	{
            		pg_selfServiceUser  = "";
            	}
            	
            	String pg_privilegeGroupDescription = String.valueOf(resultRowColumnsList[5]);
            	if(pg_privilegeGroupDescription.equals("null"))
            	{
            		pg_privilegeGroupDescription  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("privilegeGroupName", pg_privilegeGroupName);
                
                	
                    resultsDataObj.addProperty("privilegeCode", pg_privilegeCode);
                
                	
                    resultsDataObj.addProperty("applicableUserType", pg_applicableUserType);
                
                	
                    resultsDataObj.addProperty("selfServiceUser", pg_selfServiceUser);
                
                	
                    resultsDataObj.addProperty("privilegeGroupDescription", pg_privilegeGroupDescription);
                
                dataObject.addProperty("success", 1);
                dataObject.add("privilegeGroup", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("privilegeGroup information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "privilegeGroup information could not be retrieved.");
        return dataObject;
    }

	
   
public static JsonObject getPrivilegeGroupItems(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            int noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));
            int noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));
            noOfRecordsToFetch = 100000;
              
			
			
			Integer privilegeGroupId = Integer.valueOf(paramsMap.get("privilegeGroupId"));							
							
			
			
			
			
			
			
			
			
			
            
            String countQueryText = "select count(*)  from PrivilegeGroupItems pgi  Where 2>1  and pgi.privilegeGroupId = ? ";
            String selectQuery = "select pgi.privilegeGroupItemsId as pgi_privilegeGroupItemsId , pgi.privilegeGroupId as pgi_privilegeGroupId , pgi.privilegeActionType as pgi_privilegeActionType , pgi.privilegeObjectType as pgi_privilegeObjectType , pgi.privilegeObjectName as pgi_privilegeObjectName  from PrivilegeGroupItems pgi  Where 2>1  and pgi.privilegeGroupId = ? ";
            
        	selectQuery += "  ";
        	selectQuery += "  ";
        	countQueryText += "  ";
        	Query query;
        	String queryCode = "PrivilegeGroupItems";
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
        	
            
            query.setParameter(paramsPosIndex, privilegeGroupId);
            paramsPosIndex++;
            
             
      		//based on conditional parameter
      		
	        query.setFirstResult(noOfRecordsAlreadyFetched);
	        query.setMaxResults(noOfRecordsToFetch);
	        JsonArray privilegeGroupItemsDataRowsList = new JsonArray();
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
	        	JsonObject privilegeGroupItemsObject = new JsonObject();
            	
            	String pgi_privilegeGroupItemsId = String.valueOf(resultRowColumnsList[0]);
            	if(pgi_privilegeGroupItemsId.equals("null"))
            	{
            		pgi_privilegeGroupItemsId  = "";
            	}
            	privilegeGroupItemsObject.addProperty("pgi_privilegeGroupItemsId", pgi_privilegeGroupItemsId);  
            	
            	String pgi_privilegeGroupId = String.valueOf(resultRowColumnsList[1]);
            	if(pgi_privilegeGroupId.equals("null"))
            	{
            		pgi_privilegeGroupId  = "";
            	}
            	privilegeGroupItemsObject.addProperty("pgi_privilegeGroupId", pgi_privilegeGroupId);  
            	
            	String pgi_privilegeActionType = String.valueOf(resultRowColumnsList[2]);
            	if(pgi_privilegeActionType.equals("null"))
            	{
            		pgi_privilegeActionType  = "";
            	}
            	privilegeGroupItemsObject.addProperty("pgi_privilegeActionType", pgi_privilegeActionType);  
            	
            	String pgi_privilegeObjectType = String.valueOf(resultRowColumnsList[3]);
            	if(pgi_privilegeObjectType.equals("null"))
            	{
            		pgi_privilegeObjectType  = "";
            	}
            	privilegeGroupItemsObject.addProperty("pgi_privilegeObjectType", pgi_privilegeObjectType);  
            	
            	String pgi_privilegeObjectName = String.valueOf(resultRowColumnsList[4]);
            	if(pgi_privilegeObjectName.equals("null"))
            	{
            		pgi_privilegeObjectName  = "";
            	}
            	privilegeGroupItemsObject.addProperty("pgi_privilegeObjectName", pgi_privilegeObjectName);  
            	
                 
            		privilegeGroupItemsObject.addProperty("privilegeActionType", pgi_privilegeActionType);            		                        
                 
            		privilegeGroupItemsObject.addProperty("privilegeObjectType", pgi_privilegeObjectType);            		                        
                 
            		privilegeGroupItemsObject.addProperty("privilegeObjectName", pgi_privilegeObjectName);            		                        
                           
            	    
                           
                int hierarchyLevel = 1;
                 
				PrivilegeGroupControllerImpl PrivilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(); 
				PrivilegeGroupControllerImpl.processQueryResultItemDataObject(resultRowColumnsList, privilegeGroupItemsObject, "privilegeGroupItems");
                privilegeGroupItemsDataRowsList.add(privilegeGroupItemsObject);
            	
                                
                //template for leaf node
            	    
                                
            }
            PrivilegeGroupControllerImpl PrivilegeGroupControllerImpl = new PrivilegeGroupControllerImpl();
            PrivilegeGroupControllerImpl.processQueryResultList(privilegeGroupItemsDataRowsList, "privilegeGroupItems");
               
            dataObject.addProperty("success", 1);
            dataObject.add("privilegeGroupItemsDataRowsList", privilegeGroupItemsDataRowsList);
            return dataObject;
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));            
            dataObject.addProperty("alert", "privilegeGroupItems information could not retrieved");
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
