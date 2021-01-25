package com.patientapp.request.util;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class ApplicationAjaxRequestUtil
{
	public JsonObject getAdminAjaxUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession)
	{
		int validAjaxUrl = 0;
		JsonObject dataObject = new JsonObject();
		int isLoginRequired = 1;
		int isPrivilegeRequired = 1;
		// Create|View|Update|Search|Delete|Verify|Approve|Download|Upload
		String privilegeActionType = "View";
		String privilegeObjectType = "Other";
		String privilegeObjectName = "";
		int isRequestHandled = 0;
		try
		{
			String requestURI = requestInfo.get("requestURI").getAsString();
			String effectiveRequestType = requestURI;
			String requestType = requestInfo.get("requestType").getAsString();
			if (requestURI.equals("/AjaxServlet")
					 || requestURI.equals("/AjaxServletLogin")
					 || requestURI.equals("/AjaxServletLogout"))
			{
				effectiveRequestType = requestType;
			}
			//to handle requests mentioned in APIRequests sheet
			requestType = effectiveRequestType;
			if(requestType==null)
			{
				requestType = "";
			}
			requestType = requestType.trim();
			if (requestType == null)
			{
				//invalid url
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("isValidAjaxUrl", validAjaxUrl);
		dataObject.addProperty("isLoginRequired", isLoginRequired);
		dataObject.addProperty("isPrivilegeRequired", isPrivilegeRequired);
		dataObject.addProperty("privilegeActionType", privilegeActionType);
		dataObject.addProperty("privilegeObjectType", privilegeObjectType);
		dataObject.addProperty("privilegeObjectName", privilegeObjectName);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
}
