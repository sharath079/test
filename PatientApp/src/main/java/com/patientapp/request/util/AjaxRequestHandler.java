package com.patientapp.request.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.patientapp.request.service.ServiceAPIUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.patientapp.util.layout.QueryBuilderReporting;
import com.patientapp.request.util.RequestUtil;
import com.patientapp.request.util.SessionUtil;
import com.patientapp.request.util.SessionUtil;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.UploadDownloadUtil;
import com.patientapp.util.PrivilegeChecker;
import com.patientapp.util.SessionFactoryBuilder;
public class AjaxRequestHandler
{
	public static Set<String> coreEntitiesList = new HashSet<String>();
	static
	{
				coreEntitiesList.add("Organisations");
		coreEntitiesList.add("UserInfo");
		coreEntitiesList.add("PrivilegeGroup");
		coreEntitiesList.add("PrivilegeGroupItems");
		coreEntitiesList.add("EmployeeRoles");
		coreEntitiesList.add("LoginSessionInfo");

	}
	public JsonObject handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		Connection connection = null;
		Connection organisationConnection = null;
		try
		{
			connection = CommonUtil.getConnection();
			
			organisationConnection = connection;
			int isRequestHandled = 0;
			String requestType = requestInfo.get("requestType").getAsString();
			String requestURI = requestInfo.get("requestURI").getAsString();
			String objectType = requestInfo.get("objectType").getAsString();
			EntityRequestHandler entityRequestHandler = new EntityRequestHandler();
			SessionUtil sessionUtil = new SessionUtil();
			if (requestType == null || requestType.length() == 0)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Request type is null");
			}
			ApplicationAjaxRequestHandler applicationAjaxRequestHandler = new ApplicationAjaxRequestHandler();
			dataObject = applicationAjaxRequestHandler.handleAjaxRequest(userSessionInfo, requestInfo, masterSession,  organisationSession);
			if (dataObject.has("isRequestHandled"))
			{
				isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
				if (isRequestHandled == 1)
				{
					return dataObject;
				}
			}
			if(1 > 2)
			{
			}
			else if ("/loginGenericUser".equalsIgnoreCase(requestURI))
			{
				dataObject = sessionUtil.loginGenericUser(request, response, userSessionInfo, requestInfo, connection,  masterSession);
			}
			else if ("/resendActivationLink".equalsIgnoreCase(requestURI))
			{
				dataObject = sessionUtil.resendActivationLink(request, response, userSessionInfo, requestInfo, connection,  masterSession);
			}
			else if ("/sendResetPasswordLink".equalsIgnoreCase(requestURI))
			{
				dataObject = sessionUtil.sendResetPasswordLink(request, response, userSessionInfo, requestInfo, connection,  masterSession);
			}
			else if ("/resetPassword".equalsIgnoreCase(requestURI))
			{
				dataObject = sessionUtil.resetPassword(request, response, userSessionInfo, requestInfo, connection,  masterSession);
			}
			
			
			else if ("/retrieveEntityList".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleGetSearchResultsAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/createEntity".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleSaveAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/updateEntity".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleUpdateAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/retrieveEntity".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleGetEntityInfoAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/deleteEntity".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleDeleteAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/retrieveDependentEntityList".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleGetChildEntityListAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/fetchEntityDefaultData".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleLoadDefaultDataAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/fetchEntityTestData".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleLoadTestDataAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/entityLookupRowSelected".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleLookupRowSelectedAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/selectOptionChanged".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleSelectOptionChangedAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/executeCustomAPI".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleCustomAPIAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/executeEntityAuthorisationAction".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleExecuteActionAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/downloadEntityData".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleDownloadDataAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/uploadEntityData".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleUploadDataAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/getDashboardGraphsData".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleDashboardDataAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/updateLoginPassword".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleUpdatePasswordAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("getLoggedInOrgName".equalsIgnoreCase(requestURI))
			{
				int employeeId = userSessionInfo.get("userId").getAsInt();
				String organisationName = getEmployeeOrganisationName(connection, employeeId);
				dataObject.addProperty("organisationName", organisationName);
				dataObject.addProperty("success", 1);
				dataObject.addProperty("isRequestHandled", 1);
			}
			else if ("/downloadAllEntities".equalsIgnoreCase(requestURI))
			{
				dataObject = UploadDownloadUtil.downloadData(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/uploadAllEntities".equalsIgnoreCase(requestURI))
			{
				dataObject = UploadDownloadUtil.uploadData(userSessionInfo, requestInfo, masterSession, organisationSession);
			}
			else if ("/loginAdmin".equalsIgnoreCase(requestURI))
			{
				isRequestHandled = 1;
				dataObject = SessionUtil.loginAdmin(request, response, requestInfo, masterSession);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
			}
			else if ("/logoutUser".equalsIgnoreCase(requestURI))
			{
				isRequestHandled = 1;
				dataObject = SessionUtil.logoutUser(response, userSessionInfo, masterSession);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
			}
			else if ("/getLoginUserPrivilegeItems".equalsIgnoreCase(requestURI))
			{
				isRequestHandled = 1;
				dataObject = RequestUtil.getPrivilegeGroupItemsList(userSessionInfo, masterSession);
				dataObject.addProperty("isRequestHandled", isRequestHandled);
			}
			else if ("/updatePrivilegeGroupItemsList".equalsIgnoreCase(requestURI))
			{
				dataObject = entityRequestHandler.handleAPIRequestAjaxRequest(userSessionInfo, requestInfo, masterSession, organisationSession, requestType);
			}
			else if (requestURI.endsWith("/requeststatus"))
			{
				AjaxRequestHandler arh = new AjaxRequestHandler();
				dataObject = ServiceAPIUtil.getServiceRequestStatus(request);
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));
			}
			else if (requestURI.endsWith("/addrequest"))
			{
				AjaxRequestHandler arh = new AjaxRequestHandler();
				dataObject = ServiceAPIUtil.acceptRequest(request);
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));
			}
			else if (requestURI.endsWith("/getdatafromapi"))
			{
				AjaxRequestHandler arh = new AjaxRequestHandler();
				dataObject = ServiceAPIUtil.getDataFromAPI(request);
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));
			}
			else if (requestURI.endsWith("/rollbackrequeststatus"))
			{
				AjaxRequestHandler arh = new AjaxRequestHandler();
				dataObject = ServiceAPIUtil.getServiceRequestRollbackStatus(request);
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));
			}
			else if (requestURI.endsWith("/rollbackrequest"))
			{
				AjaxRequestHandler arh = new AjaxRequestHandler();
				dataObject = ServiceAPIUtil.rollbackRequestReceived(request);
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));
			}
			else if ("/generateReportPDF".equalsIgnoreCase(requestURI))
			{
				isRequestHandled = 1;
				String printFormatInfoObj = request.getParameter("printFormatInfoObj").trim();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				paramsMap.put("printFormatInfoObj", printFormatInfoObj);
				dataObject = CommonUtil.generateReportPDF(organisationConnection, paramsMap);
				if (dataObject.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("isRequestHandled", isRequestHandled);
					return dataObject;
				}
			}
			else
			{
				String organisationsDBNameForReports = null;
				if (!"/loginUser".equalsIgnoreCase(requestType)
						&& !"/logoutUser".equalsIgnoreCase(requestType))
				{
					
					
					organisationsDBNameForReports = CommonUtil.getMasterDBName();
				}					
				APIRequestHandler apiRequestHandler = new APIRequestHandler();
				//dataObject = apiRequestHandler.handleAjaxRequest(request, response, organisationsDBNameForReports, organisationConnection, connection);
				try
				{
					if (dataObject.has("isRequestHandled"))
					{
						isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (isRequestHandled == 1)
				{
					return dataObject;
				}
				
				
				
				
				ReportRequestHandler reportRequestHandler = new ReportRequestHandler();
				dataObject = reportRequestHandler.handleAjaxRequest(request, response,  requestInfo, organisationSession, userSessionInfo);
				try
				{
					isRequestHandled = dataObject.get("isRequestHandled").getAsInt();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (isRequestHandled == 1)
				{
					return dataObject;
				}			
				
				
			}
			/*dataObject.addProperty("msg", "Unknown request type !!");
			dataObject.addProperty("alert", "Unknown request type !!");
			dataObject.addProperty("success", 0);*/
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (Exception e2)
			{
				dataObject.addProperty("msg", "Error while closing the connection!!");
				dataObject.addProperty("success", 0);
				CommonUtil.writeTolog(e2);
				return dataObject;
			}
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static String getEmployeeOrganisationDBName(Connection genericConn, Integer employeeId)
	{
		PreparedStatement getOrganisationDBNameStmt = null;
		ResultSet idSet = null;
		try
		{
			getOrganisationDBNameStmt = genericConn.prepareStatement("Select databaseName from Organisations where organisationsId = (select organisationsUserOrgId from UserInfo where userInfoId = ?) ");
			getOrganisationDBNameStmt.setInt(1, employeeId);
			idSet = getOrganisationDBNameStmt.executeQuery();
			if (idSet.next())
			{
				return idSet.getString("databaseName");
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
	public static String getEmployeeOrganisationName(Connection genericConn, Integer employeeId)
	{
		PreparedStatement getOrganisationDBNameStmt = null;
		ResultSet idSet = null;
		try
		{
			getOrganisationDBNameStmt = genericConn.prepareStatement("Select organisationName from Organisations where organisationsId = (select organisationsUserOrgId from UserInfo where userInfoId = ?) ");
			getOrganisationDBNameStmt.setInt(1, employeeId);
			idSet = getOrganisationDBNameStmt.executeQuery();
			if (idSet.next())
			{
				return idSet.getString("organisationName");
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
}
