package com.patientapp.request.util;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class ApplicationAjaxRequestHandler
{
	public JsonObject handleAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession,  Session organisationSession)
	{
		int isRequestHandled = 0;
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject.addProperty("success", 0);
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	public int isCustomAjaxUrl(HttpServletRequest request, HttpServletResponse response)
	{
		int customAjaxUrl = 0;
		Set<String> entitiesList = new HashSet<String>();
		entitiesList.add("");
		try
		{
            String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
			String originalRequestURI = request.getRequestURI();			
			String requestURI = originalRequestURI;
			if(requestURI.startsWith(applicationUrlPrefix))
			{
				requestURI = requestURI.substring(applicationUrlPrefix.length());
			}
			String requestType = request.getParameter("requestType").trim();
			EntityRequestHandler entityRequestHandler = new EntityRequestHandler();
			if (requestURI.equals("/AjaxServlet")
					 || requestURI.equals("/AjaxServletLogin")
					 || requestURI.equals("/AjaxServletLogout"))
			{
				if (requestType == null)
				{
					//invalid url
				}
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return customAjaxUrl;
	}
}
