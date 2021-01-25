package com.patientapp.request.util;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.SettingsUtil;
public class ApplicationStaticRequestHandler
{
	public JsonObject handleStaticRequest(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession) throws IOException
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 0;
		try
		{
            String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
			String originalRequestURI = request.getRequestURI();			
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
			String filePath = "";
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
}
