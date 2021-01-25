package com.patientapp.request.util;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.JsonObject;
public class AjaxRequestUtil
{
	public JsonObject getAjaxUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession)  throws Exception
	{
		int isUserLoggedIn =  userSessionInfo.get("isUserLoggedIn").getAsInt();
		if(isUserLoggedIn==1)
		{
			String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
			if(1>2)
			{
			}
			
		}
		return getAdminAjaxUrlInfo(userSessionInfo, requestInfo, masterSession);
	}
	public JsonObject getAdminAjaxUrlInfo(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession)
	{
		int validAjaxUrl = 0;
		JsonObject dataObject = new JsonObject();
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
		try
		{
			String requestURI = requestInfo.get("requestURI").getAsString();
			String effectiveRequestType = requestURI;
			String requestType = requestInfo.get("requestType").getAsString();
			String objectType = requestInfo.get("objectType").getAsString();
/*			if (requestURI.equals("/AjaxServlet")
					 || requestURI.equals("/AjaxServletLogin")
					 || requestURI.equals("/AjaxServletLogout"))
			{
				effectiveRequestType = requestType;
			}
			//to handle requests mentioned in APIRequests sheet
			requestType = effectiveRequestType;
*/
			ApplicationAjaxRequestUtil applicationAjaxRequestUtil = new ApplicationAjaxRequestUtil();
			dataObject = applicationAjaxRequestUtil.getAdminAjaxUrlInfo(userSessionInfo, requestInfo, masterSession);
			if (dataObject.has("isRequestHandled"))
			{
				int isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
				if (isRequestHandled == 1)
				{
					return dataObject;
				}
			}
			if(1 > 2)
			{
			}
			if(requestType==null)
			{
				requestType = "";
			}
			requestType = requestType.trim();
			if (requestType == null)
			{
				//invalid url
			}
			
						else if(requestURI.equalsIgnoreCase("/createEntity")
					&& objectType.equalsIgnoreCase("ContactUs"))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
				privilegeActionType = "";
				privilegeObjectType = "";
				privilegeObjectName = "";
			}

			else if ("/retrieveEntityList".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeActionType = "View";
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
				}				  
			}
			else if ("/createEntity".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;
					privilegeActionType = "Create";
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
				}
				if(1 > 2)
				{
				}
				
			}
			else if ("/updateEntity".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeActionType = "Update";
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
				}				  
			}
			else if ("/retrieveEntity".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					privilegeActionType = "View";
				}				  
			}
			else if ("/deleteEntity".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					privilegeActionType = "Delete";
				}				  
			}
			else if ("/retrieveDependentEntityList".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					privilegeActionType = "View";
				}				  
			}
			else if ("/fetchEntityDefaultData".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					isPrivilegeRequired = 0;
				}				  
				if(1 > 2)
				{
				}
				
			}
			else if ("/fetchEntityTestData".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					isPrivilegeRequired = 0;
				}				  
			}
			else if ("/entityLookupRowSelected".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					isPrivilegeRequired = 0;
				}				  
			}
			else if ("/selectOptionChanged".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					isPrivilegeRequired = 0;
				}				  
				if(1 > 2)
				{
				}
				
			}
			else if ("/executeCustomAPI".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isPrivilegeRequired = 1;
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				String customEventName = paramsInfo.get("customEventName").getAsString();
				if(1 > 2)
				{
				}
				
			}
			else if ("/executeEntityAuthorisationAction".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
					String vwTxnStatus = paramsInfo.get("currentStatus").getAsString(); 
					if(vwTxnStatus.equalsIgnoreCase("CREATED") 
							|| vwTxnStatus.equalsIgnoreCase("MODIFIED"))
					{
						privilegeActionType = "Verify";
					}
					else if(vwTxnStatus.equalsIgnoreCase("VERIFIED"))
					{
						privilegeActionType = "Approve";
					}
					else if(vwTxnStatus.equalsIgnoreCase("COMPLETED"))
					{
						privilegeActionType = "Rollback";
					}
					else
					{
						privilegeActionType = "";
					}
				}				  
			}
			else if ("/downloadEntityData".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					privilegeActionType = "Download";
				}				  
			}
			else if ("/uploadEntityData".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					privilegeObjectType = "Entity";
					privilegeObjectName = objectType;
					privilegeActionType = "Upload";
				}				  
			}
			else if ("getDashboardGraphsData".equalsIgnoreCase(requestURI))
			{
				if(entitiesList.contains(objectType))
				{
					validAjaxUrl = 1;					
					isPrivilegeRequired = 0;
				}				  
			}
			else if ("getLoggedInOrgName".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isPrivilegeRequired = 0;
			}
			else if ("/downloadAllEntities".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				privilegeActionType = "DownloadAll";
				privilegeObjectName = "DownloadAllEntities";
			}
			else if ("/uploadAllEntities".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				privilegeActionType = "UploadAll";
				privilegeObjectName = "UploadAllEntities";
			}
			else if ("/loginAdmin".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if ("/loginGenericUser".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if ("/resendActivationLink".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if ("/sendResetPasswordLink".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			else if ("/resetPassword".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isLoginRequired = 0;
				isPrivilegeRequired = 0;
			}
			
			else if ("/logoutUser".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isPrivilegeRequired = 0;
				isLoginRequired = 0;
			}
			else if ("/getLoginUserPrivilegeItems".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isPrivilegeRequired = 0;
				isLoginRequired = 0;
			}
			else if(requestURI.equalsIgnoreCase("/updatePrivilegeGroupItemsList"))
			{
				validAjaxUrl = 1;
				isLoginRequired = 1;
				isPrivilegeRequired = 1;
				privilegeActionType = "UpdatePrivilegeGroupItems";
				privilegeObjectType = "Other";
				privilegeObjectName = "PrivilegeGroup";
			}
			else if ("/AjaxServlet".equalsIgnoreCase(requestURI))
			{
				validAjaxUrl = 1;
				isPrivilegeRequired = 0;
				isLoginRequired = 0;
			}
			else
			{
				if (requestURI.equalsIgnoreCase("/getReportInfo") || requestURI.equalsIgnoreCase("/getSearchResults") || requestURI.equalsIgnoreCase("/getLookupResults"))
				{					
					if(reportsList.contains(objectType))
					{
						validAjaxUrl = 1;															
						privilegeActionType = "accessLayout";
						privilegeObjectType = "Report";
						privilegeObjectName = objectType;
					}
				}								
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
		return dataObject;
	}
}
