package com.patientapp.request.util;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class ApplicationStaticRequestUtil
{
	public JsonObject getAdminStaticUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession) throws IOException
	{
		int validStaticUrl = 0;
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 0;
		try
		{
			int isLoginRequired = 1;
			int isPrivilegeRequired = 1;
			// Create|View|Update|Search|Delete|Verify|Approve|Download|Upload
			String privilegeActionType = "View";
			String privilegeObjectType = "Other";
			String privilegeObjectName = "";
			String requestURI = requestInfo.get("requestURI").getAsString();
			//boolean isLoginRequired = PrivilegeChecker.isLoginRequired(request, response);
			if (requestURI.startsWith(""))
			{
			}
//			else if ()
//			{
//			}
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			dataObject.addProperty("isValidStaticUrl", validStaticUrl);
			dataObject.addProperty("isLoginRequired", isLoginRequired);
			dataObject.addProperty("isPrivilegeRequired", isPrivilegeRequired);
			dataObject.addProperty("privilegeActionType", privilegeActionType);
			dataObject.addProperty("privilegeObjectType", privilegeObjectType);
			dataObject.addProperty("privilegeObjectName", privilegeObjectName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dataObject;
	}
}
