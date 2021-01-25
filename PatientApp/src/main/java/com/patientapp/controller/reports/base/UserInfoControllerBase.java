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
import com.patientapp.controller.reports.impl.UserInfoControllerImpl;
/**
 *
 * @author root
 */
public  abstract class UserInfoControllerBase
{
    private static final Logger logger = Logger.getLogger(UserInfoControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public UserInfoControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public UserInfoControllerBase()
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
          
            JsonObject userInfoObj = getUserInfo(organisationSession, paramsMap);
            if (userInfoObj.get("success").getAsInt() != 1)
            {
                return userInfoObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("userInfo",        
            userInfoObj.get("userInfo").getAsJsonObject());
        
        
                
        UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl();
        userInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    		   
            JsonObject employeeRolesObj = getEmployeeRoles(organisationSession, paramsMap);
            if (employeeRolesObj.get("success").getAsInt() != 1)
            {
                return employeeRolesObj;
            }
            tablesData.add("employeeRolesDataRowsList", 
            employeeRolesObj.get("employeeRolesDataRowsList").getAsJsonArray());
        
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getUserInfo(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer userInfoId = Integer.valueOf(paramsMap.get("userInfoId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select ui.userInfoId as ui_userInfoId , ui.userFirstName as ui_userFirstName , ui.userLastName as ui_userLastName , ifnull((select organisationName from Organisations  where organisationsId = ui.organisationsId), 'Not Found') as ui_organisationsUserOrgDisplayText , ui.organisationsUserOrgId as ui_organisationsUserOrgId , ui.loginId as ui_loginId , ui.loginEmailId as ui_loginEmailId , ui.contactNo as ui_contactNo , ui.loginPassword as ui_loginPassword , ui.resetToken as ui_resetToken  from UserInfo ui  Where 2>1  and ui.userInfoId = ?   ";
            
        	Query query;
        	String queryCode = "UserInfo";
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
	        	         
	        query.setParameter(paramsPosIndex, userInfoId);
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
				
            	String ui_userInfoId = String.valueOf(resultRowColumnsList[0]);
            	if(ui_userInfoId.equals("null"))
            	{
            		ui_userInfoId  = "";
            	}
            	
            	String ui_userFirstName = String.valueOf(resultRowColumnsList[1]);
            	if(ui_userFirstName.equals("null"))
            	{
            		ui_userFirstName  = "";
            	}
            	
            	String ui_userLastName = String.valueOf(resultRowColumnsList[2]);
            	if(ui_userLastName.equals("null"))
            	{
            		ui_userLastName  = "";
            	}
            	
            	String ui_organisationsUserOrgDisplayText = String.valueOf(resultRowColumnsList[3]);
            	if(ui_organisationsUserOrgDisplayText.equals("null"))
            	{
            		ui_organisationsUserOrgDisplayText  = "";
            	}
            	
            	String ui_organisationsUserOrgId = String.valueOf(resultRowColumnsList[4]);
            	if(ui_organisationsUserOrgId.equals("null"))
            	{
            		ui_organisationsUserOrgId  = "";
            	}
            	
            	String ui_loginId = String.valueOf(resultRowColumnsList[5]);
            	if(ui_loginId.equals("null"))
            	{
            		ui_loginId  = "";
            	}
            	
            	String ui_loginEmailId = String.valueOf(resultRowColumnsList[6]);
            	if(ui_loginEmailId.equals("null"))
            	{
            		ui_loginEmailId  = "";
            	}
            	
            	String ui_contactNo = String.valueOf(resultRowColumnsList[7]);
            	if(ui_contactNo.equals("null"))
            	{
            		ui_contactNo  = "";
            	}
            	
            	String ui_loginPassword = String.valueOf(resultRowColumnsList[8]);
            	if(ui_loginPassword.equals("null"))
            	{
            		ui_loginPassword  = "";
            	}
            	
            	String ui_resetToken = String.valueOf(resultRowColumnsList[9]);
            	if(ui_resetToken.equals("null"))
            	{
            		ui_resetToken  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("userFirstName", ui_userFirstName);
                
                	
                    resultsDataObj.addProperty("userLastName", ui_userLastName);
                
                	
                    resultsDataObj.addProperty("organisationsUserOrg", ui_organisationsUserOrgDisplayText);
                
                	
                    resultsDataObj.addProperty("loginId", ui_loginId);
                
                	
                    resultsDataObj.addProperty("loginEmailId", ui_loginEmailId);
                
                	
                    resultsDataObj.addProperty("contactNo", ui_contactNo);
                
                	
                    resultsDataObj.addProperty("loginPassword", ui_loginPassword);
                
                	
                    resultsDataObj.addProperty("resetToken", ui_resetToken);
                
                dataObject.addProperty("success", 1);
                dataObject.add("userInfo", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("userInfo information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "userInfo information could not be retrieved.");
        return dataObject;
    }

	
   
public static JsonObject getEmployeeRoles(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            int noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));
            int noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));
            noOfRecordsToFetch = 100000;
              
			
			
			Integer userInfoId = Integer.valueOf(paramsMap.get("userInfoId"));							
							
			
			
			
			
			
			
			
			
			
            
            String countQueryText = "select count(*)  from EmployeeRoles er  Where 2>1  and er.userInfoId = ? ";
            String selectQuery = "select er.employeeRolesId as er_employeeRolesId , er.userInfoId as er_userInfoId , ifnull((select privilegeGroupName from PrivilegeGroup  where privilegeGroupId = er.privilegeGroupId), 'Not Found') as er_privilegeGroupDisplayText , er.privilegeGroupId as er_privilegeGroupId , er.description as er_description  from EmployeeRoles er  Where 2>1  and er.userInfoId = ? ";
            
        	selectQuery += "  ";
        	selectQuery += "  ";
        	countQueryText += "  ";
        	Query query;
        	String queryCode = "EmployeeRoles";
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
        	
            
            query.setParameter(paramsPosIndex, userInfoId);
            paramsPosIndex++;
            
             
      		//based on conditional parameter
      		
	        query.setFirstResult(noOfRecordsAlreadyFetched);
	        query.setMaxResults(noOfRecordsToFetch);
	        JsonArray employeeRolesDataRowsList = new JsonArray();
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
	        	JsonObject employeeRolesObject = new JsonObject();
            	
            	String er_employeeRolesId = String.valueOf(resultRowColumnsList[0]);
            	if(er_employeeRolesId.equals("null"))
            	{
            		er_employeeRolesId  = "";
            	}
            	employeeRolesObject.addProperty("er_employeeRolesId", er_employeeRolesId);  
            	
            	String er_userInfoId = String.valueOf(resultRowColumnsList[1]);
            	if(er_userInfoId.equals("null"))
            	{
            		er_userInfoId  = "";
            	}
            	employeeRolesObject.addProperty("er_userInfoId", er_userInfoId);  
            	
            	String er_privilegeGroupDisplayText = String.valueOf(resultRowColumnsList[2]);
            	if(er_privilegeGroupDisplayText.equals("null"))
            	{
            		er_privilegeGroupDisplayText  = "";
            	}
            	employeeRolesObject.addProperty("er_privilegeGroupDisplayText", er_privilegeGroupDisplayText);  
            	
            	String er_privilegeGroupId = String.valueOf(resultRowColumnsList[3]);
            	if(er_privilegeGroupId.equals("null"))
            	{
            		er_privilegeGroupId  = "";
            	}
            	employeeRolesObject.addProperty("er_privilegeGroupId", er_privilegeGroupId);  
            	
            	String er_description = String.valueOf(resultRowColumnsList[4]);
            	if(er_description.equals("null"))
            	{
            		er_description  = "";
            	}
            	employeeRolesObject.addProperty("er_description", er_description);  
            	
                 
            		employeeRolesObject.addProperty("privilegeGroup", er_privilegeGroupDisplayText);            		                        
                 
            		employeeRolesObject.addProperty("description", er_description);            		                        
                           
            	    
                           
                int hierarchyLevel = 1;
                 
				UserInfoControllerImpl UserInfoControllerImpl = new UserInfoControllerImpl(); 
				UserInfoControllerImpl.processQueryResultItemDataObject(resultRowColumnsList, employeeRolesObject, "employeeRoles");
                employeeRolesDataRowsList.add(employeeRolesObject);
            	
                                
                //template for leaf node
            	    
                                
            }
            UserInfoControllerImpl UserInfoControllerImpl = new UserInfoControllerImpl();
            UserInfoControllerImpl.processQueryResultList(employeeRolesDataRowsList, "employeeRoles");
               
            dataObject.addProperty("success", 1);
            dataObject.add("employeeRolesDataRowsList", employeeRolesDataRowsList);
            return dataObject;
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));            
            dataObject.addProperty("alert", "employeeRoles information could not retrieved");
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
