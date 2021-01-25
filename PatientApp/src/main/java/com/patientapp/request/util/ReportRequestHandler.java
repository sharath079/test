package com.patientapp.request.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.patientapp.controller.reports.base.OrganisationsControllerBase;
import com.patientapp.controller.reports.impl.OrganisationsControllerImpl;
import com.patientapp.controller.reports.base.UserInfoControllerBase;
import com.patientapp.controller.reports.impl.UserInfoControllerImpl;
import com.patientapp.controller.reports.base.PrivilegeGroupControllerBase;
import com.patientapp.controller.reports.impl.PrivilegeGroupControllerImpl;
import com.patientapp.controller.reports.base.LoginSessionInfoControllerBase;
import com.patientapp.controller.reports.impl.LoginSessionInfoControllerImpl;
import com.patientapp.controller.reports.base.ConfigPropertiesControllerBase;
import com.patientapp.controller.reports.impl.ConfigPropertiesControllerImpl;
import com.patientapp.controller.reports.base.TaskInfoControllerBase;
import com.patientapp.controller.reports.impl.TaskInfoControllerImpl;
import com.patientapp.controller.reports.base.TaskScheduleInfoControllerBase;
import com.patientapp.controller.reports.impl.TaskScheduleInfoControllerImpl;
import com.patientapp.controller.reports.base.TaskSentInfoControllerBase;
import com.patientapp.controller.reports.impl.TaskSentInfoControllerImpl;
import com.patientapp.controller.reports.base.DoctorControllerBase;
import com.patientapp.controller.reports.impl.DoctorControllerImpl;
import com.patientapp.controller.reports.base.HospitalControllerBase;
import com.patientapp.controller.reports.impl.HospitalControllerImpl;
import com.patientapp.controller.reports.base.ContactUsControllerBase;
import com.patientapp.controller.reports.impl.ContactUsControllerImpl;
import com.patientapp.util.CommonUtil;
public class ReportRequestHandler
{
	protected JsonObject handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, JsonObject requestInfo, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int isRequestHandled = 0;
			String requestURI = requestInfo.get("requestURI").getAsString();
			String objectType = requestInfo.get("objectType").getAsString();
			if(requestURI==null)
			{
				isRequestHandled = 1;
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Request URI is null");
			}
			else if("/getReportInfo".equalsIgnoreCase(requestURI))
			{				
				return getReportInfo(objectType, request, organisationSession, userSessionInfo);
			}
			else if("/getSearchResults".equalsIgnoreCase(requestURI))
			{
				return getSearchResults(objectType, request, organisationSession, userSessionInfo);
			}
		    else if("/getLookupResults".equalsIgnoreCase(requestURI))
			{				
				return getLookupResults(objectType, request, organisationSession);						
			}
			else
			{
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				dataObject.addProperty("success", 0);
			}					
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;			
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}		
		dataObject.addProperty("msg", "Error while handling the request !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	private JsonObject getReportInfo(String objectType, HttpServletRequest request, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			
				if (objectType.equalsIgnoreCase("Organisations"))
				{			
					dataObject = getOrganisationsInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("UserInfo"))
				{			
					dataObject = getUserInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("PrivilegeGroup"))
				{			
					dataObject = getPrivilegeGroupInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("LoginSessionInfo"))
				{			
					dataObject = getLoginSessionInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("ConfigProperties"))
				{			
					dataObject = getConfigPropertiesInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskInfo"))
				{			
					dataObject = getTaskInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskScheduleInfo"))
				{			
					dataObject = getTaskScheduleInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskSentInfo"))
				{			
					dataObject = getTaskSentInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("Doctor"))
				{			
					dataObject = getDoctorInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("Hospital"))
				{			
					dataObject = getHospitalInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("ContactUs"))
				{			
					dataObject = getContactUsInfo(request, organisationSession);			
					return dataObject;
				}
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}
	private JsonObject getSearchResults(String objectType, HttpServletRequest request, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			String searchFormName = request.getParameter("searchFormName").trim();
			if (1>2)
			{
			}
			
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}
	private JsonObject getLookupResults(String objectType, HttpServletRequest request, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			String searchFormName = request.getParameter("searchFormName").trim();
			String lookupEntityName = request.getParameter("lookupEntityName").trim();
			if (1>2)
			{
			}
			
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}	
	
		private JsonObject getOrganisationsInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String organisationsId = "";
			try {
			organisationsId = request.getParameter("organisationsId").trim();	}
			catch (Exception e) {
				organisationsId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("organisationsId", organisationsId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = OrganisationsControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getUserInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String userInfoId = "";
			try {
			userInfoId = request.getParameter("userInfoId").trim();	}
			catch (Exception e) {
				userInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("userInfoId", userInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = UserInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getPrivilegeGroupInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String privilegeGroupId = "";
			try {
			privilegeGroupId = request.getParameter("privilegeGroupId").trim();	}
			catch (Exception e) {
				privilegeGroupId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("privilegeGroupId", privilegeGroupId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = PrivilegeGroupControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getLoginSessionInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String loginSessionInfoId = "";
			try {
			loginSessionInfoId = request.getParameter("loginSessionInfoId").trim();	}
			catch (Exception e) {
				loginSessionInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("loginSessionInfoId", loginSessionInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = LoginSessionInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getConfigPropertiesInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String configPropertiesId = "";
			try {
			configPropertiesId = request.getParameter("configPropertiesId").trim();	}
			catch (Exception e) {
				configPropertiesId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("configPropertiesId", configPropertiesId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = ConfigPropertiesControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskInfoId = "";
			try {
			taskInfoId = request.getParameter("taskInfoId").trim();	}
			catch (Exception e) {
				taskInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskInfoId", taskInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskScheduleInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskScheduleInfoId = "";
			try {
			taskScheduleInfoId = request.getParameter("taskScheduleInfoId").trim();	}
			catch (Exception e) {
				taskScheduleInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskScheduleInfoId", taskScheduleInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskScheduleInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskSentInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskSentInfoId = "";
			try {
			taskSentInfoId = request.getParameter("taskSentInfoId").trim();	}
			catch (Exception e) {
				taskSentInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskSentInfoId", taskSentInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskSentInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getDoctorInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String doctorId = "";
			try {
			doctorId = request.getParameter("doctorId").trim();	}
			catch (Exception e) {
				doctorId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("doctorId", doctorId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = DoctorControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getHospitalInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String hospitalId = "";
			try {
			hospitalId = request.getParameter("hospitalId").trim();	}
			catch (Exception e) {
				hospitalId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("hospitalId", hospitalId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = HospitalControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getContactUsInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String contactUsId = "";
			try {
			contactUsId = request.getParameter("contactUsId").trim();	}
			catch (Exception e) {
				contactUsId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("contactUsId", contactUsId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = ContactUsControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
public static void populateCustomDataFields(Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject,  String layoutName, Session organisationSession, JsonObject fieldsDataWithOverrideWhereClause)
{	
	
	if(layoutName.equalsIgnoreCase("Organisations"))
	{
		OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl();
		organisationsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("UserInfo"))
	{
		UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl();
		userInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("PrivilegeGroup"))
	{
		PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl();
		privilegeGroupControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("LoginSessionInfo"))
	{
		LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl();
		loginSessionInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("ConfigProperties"))
	{
		ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl();
		configPropertiesControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskInfo"))
	{
		TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl();
		taskInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskScheduleInfo"))
	{
		TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl();
		taskScheduleInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskSentInfo"))
	{
		TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl();
		taskSentInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("Doctor"))
	{
		DoctorControllerImpl doctorControllerImpl = new DoctorControllerImpl();
		doctorControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("Hospital"))
	{
		HospitalControllerImpl hospitalControllerImpl = new HospitalControllerImpl();
		hospitalControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("ContactUs"))
	{
		ContactUsControllerImpl contactUsControllerImpl = new ContactUsControllerImpl();
		contactUsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
}			
}
