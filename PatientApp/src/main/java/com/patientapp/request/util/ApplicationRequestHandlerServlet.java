package com.patientapp.request.util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.patientapp.request.util.StaticFileUtil;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.SettingsUtil;
public class ApplicationRequestHandlerServlet
{
	public JsonObject handleAjaxRequest(HttpServletRequest request, HttpServletResponse response)
	{
		int isRequestHandled = 0;
		JsonObject dataObject = new JsonObject();
		try
		{
            String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
			String originalRequestURI = request.getRequestURI();			
			String filePath = "";
			String requestURI = originalRequestURI;
			if(requestURI.startsWith(applicationUrlPrefix))
			{
				requestURI = requestURI.substring(applicationUrlPrefix.length());
			}
			String projectPath = SettingsUtil.getProjectPath();
			String webContentFilesPath = projectPath + "/src/main/resources/WebContent"; 
			String staticHtmlFilesPath = projectPath + "/src/main/resources/WebContent/base/html"; 
			String staticResourcesFilesPath = projectPath + "/src/main/resources/WebContent/resources"; 
			String reportFilesPath = projectPath + "/src/main/resources/WebContent/reports";
			if (requestURI.startsWith("/dashboard/associate"))
			{
				response.setContentType("text/html");
				filePath = staticHtmlFilesPath + "/custom/AssociateDashboard.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
				isRequestHandled = 1;
				dataObject.addProperty("success", 1);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
			}
			else if (requestURI.startsWith("/register/associate"))
			{
				response.setContentType("text/html");
				filePath = staticHtmlFilesPath + "/custom/AssociateRegistration.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);
				isRequestHandled = 1;
				dataObject.addProperty("success", 1);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
			}
			else if (requestURI.startsWith("/associate/login"))
			{
				response.setContentType("text/html");
				filePath = webContentFilesPath + "/impl/AssociateLogin.html";
				StaticFileUtil staticFileUtil = new StaticFileUtil();
				staticFileUtil.handleStaticFileRequest(filePath, request, response);				
				isRequestHandled = 1;
				dataObject.addProperty("success", 1);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
			}
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
}
