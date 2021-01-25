package com.patientapp.request.util;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.PrivilegeChecker;
import com.patientapp.util.SettingsUtil;
public class StaticRequestUtil
{
	public JsonObject getStaticUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession) throws Exception
	{
		int isUserLoggedIn =  userSessionInfo.get("isUserLoggedIn").getAsInt();
		if(isUserLoggedIn==1)
		{
			String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
			if(1>2)
			{
			}
			
		}
		return getAdminStaticUrlInfo(userSessionInfo, requestInfo, masterSession);
	}
	public JsonObject getAdminStaticUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession) throws IOException
	{
		int validStaticUrl = 0;
		JsonObject dataObject = new JsonObject();
		try
		{
			ApplicationStaticRequestUtil applicationStaticRequestUtil = new ApplicationStaticRequestUtil();
			dataObject = applicationStaticRequestUtil.getAdminStaticUrlInfo(userSessionInfo, requestInfo, masterSession);
			if (dataObject.has("isRequestHandled"))
			{
				int isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
				if (isRequestHandled == 1)
				{
					return dataObject;
				}
			}
			Set<String> entitiesList = new HashSet<String>();
						entitiesList.add("Organisations");
			entitiesList.add("UserInfo");
			entitiesList.add("PrivilegeGroup");
			entitiesList.add("PrivilegeGroupItems");
			entitiesList.add("EmployeeRoles");
			entitiesList.add("LoginSessionInfo");
			entitiesList.add("ConfigProperties");
			entitiesList.add("TaskInfo");
			entitiesList.add("TaskExecutionInfo");
			entitiesList.add("TaskLayoutParameters");
			entitiesList.add("EmailAttachmentLayout");
			entitiesList.add("TaskScheduleInfo");
			entitiesList.add("TaskSentInfo");
			entitiesList.add("Patient");
			entitiesList.add("Doctor");
			entitiesList.add("Hospital");
			entitiesList.add("ContactUs");

			Set<String> reportsList = new HashSet<String>();
			
			reportsList.add("Organisations");
			reportsList.add("UserInfo");
			reportsList.add("PrivilegeGroup");
			reportsList.add("LoginSessionInfo");
			reportsList.add("ConfigProperties");
			reportsList.add("TaskInfo");
			reportsList.add("TaskScheduleInfo");
			reportsList.add("TaskSentInfo");
			reportsList.add("Doctor");
			reportsList.add("Hospital");
			reportsList.add("ContactUs");
			int isLoginRequired = 1;
			int isPrivilegeRequired = 1;
			// Create|View|Update|Search|Delete|Verify|Approve|Download|Upload
			String privilegeActionType = "View";
			String privilegeObjectType = "Other";
			String privilegeObjectName = "";
			String requestURI = requestInfo.get("requestURI").getAsString();
			String objectType = requestInfo.get("objectType").getAsString();
			//boolean isLoginRequired = PrivilegeChecker.isLoginRequired(request, response);
			if (requestURI.startsWith("/entity/"))
			{
				//response.setContentType("text/html");
				String[] URLSplit = requestURI.split("/");
				String entityFileName = URLSplit[2];
				String entityName = "";
				if(entityFileName.endsWith(".html"))
				{
					if(entityFileName.endsWith("Dashboard.html"))
					{
						privilegeActionType = "View";
						entityName = entityFileName.substring(0, entityFileName.length() - "Dashboard.html".length());
					}
					else if(entityFileName.startsWith("Create"))
					{
						privilegeActionType = "Create";
						entityName = entityFileName.substring("Create".length(), entityFileName.length() - ".html".length());
					}					
					else if(entityFileName.startsWith("View"))
					{
						privilegeActionType = "View";
						entityName = entityFileName.substring("View".length(), entityFileName.length() - ".html".length());
					}					
				}
				if(entitiesList.contains(entityName))
				{
					validStaticUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = entityName;
				}				  
			}
			else if (requestURI.startsWith("/theme/"))
			{
				validStaticUrl = 1;					
			}
			else if (requestURI.endsWith(".woff2"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if (requestURI.endsWith(".woff"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}						
			else if (requestURI.startsWith("/printFormat"))
			{
				validStaticUrl = 1;					
			}
			else if (requestURI.startsWith("/layoutFormat"))
			{
				validStaticUrl = 1;					
			}			
			else if (requestURI.endsWith(".js"))
			{
				validStaticUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if (requestURI.startsWith("/reports/"))
			{
				String[] URLSplit = requestURI.split("/");
				String reportFileName = URLSplit[2];
				if(reportsList.contains(reportFileName))
				{
					validStaticUrl = 1;
					privilegeObjectType = "Report";					
					privilegeObjectName = reportFileName;			
					privilegeActionType = "accessLayout";			
				}				  
			}
			else if (requestURI.endsWith(".css"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if (requestURI.endsWith(".gif") 
					|| requestURI.endsWith(".png")
					|| requestURI.endsWith(".jpg")
				|| requestURI.endsWith(".jpeg"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if (requestURI.equals("/login") 
					|| requestURI.equals("/")
					|| requestURI.equals("/privilege-required")
					|| requestURI.equals("/page-not-found"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}			
			else if (requestURI.equals("/forgot-password"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}			
			else if (requestURI.equals("/reset-password"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}			
			else if (requestURI.equals("/emailid-activation-link"))
			{
				validStaticUrl = 1;					
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}			
			
			else if (requestURI.equals("/home"))
			{
				validStaticUrl = 1;					
				isPrivilegeRequired = 0;
			}			
			else if (requestURI.equals("/downloadZipFile"))
			{
				validStaticUrl = 1;					
				privilegeActionType = "View";
				privilegeObjectType = "Entity";
				privilegeObjectName = objectType;
			}
			else if (requestURI.equals("/ViewImageFile"))
			{
				validStaticUrl = 1;					
				isPrivilegeRequired = 0;
				isLoginRequired = 0;
				privilegeActionType = "View";
				privilegeObjectType = "Entity";
				privilegeObjectName = objectType;
			}
			else if (requestURI.equals("/download"))
			{
				validStaticUrl = 1;
				//isLoginRequired = 0;
				privilegeActionType = "View";
				privilegeObjectType = "Entity";
				privilegeObjectName = objectType;
				if(objectType.length() == 0)
				{
					privilegeActionType = "DownloadAll";
					privilegeObjectName = "DownloadAllEntities";
				}
			}
			else if (requestURI.equals("/viewFile"))
			{
				validStaticUrl = 1;
				privilegeActionType = "View";
				privilegeObjectType = "Entity";
				privilegeObjectName = objectType;
			}
			else if ("/UploadServlet".equalsIgnoreCase(requestURI))
			{
				validStaticUrl = 1;					
				isPrivilegeRequired = 0;
				isLoginRequired = 0;
			}
			else if (requestURI.startsWith("/repository"))
			{
				validStaticUrl = 1;					
			}
			else
			{
				//Handle application specific static urls
				//ApplicationRequestHandlerServlet applicationRequestHandlerServlet = new ApplicationRequestHandlerServlet();
				if(1 > 2)
				{
				}
								else if(requestURI.equalsIgnoreCase("/contact-us"))
				{
					validStaticUrl = 1;
					isLoginRequired = 0;
					isPrivilegeRequired = 0;
					privilegeActionType = "";
					privilegeObjectType = "";
					privilegeObjectName = "";
				}

			}
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
