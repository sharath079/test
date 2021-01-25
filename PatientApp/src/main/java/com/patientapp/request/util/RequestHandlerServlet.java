package com.patientapp.request.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.sql.Connection;
import com.patientapp.request.util.StaticFileUtil;
import com.patientapp.request.service.ServiceAPIUtil;
import com.patientapp.util.SettingsUtil;
import com.patientapp.request.util.ApplicationRequestHandlerServlet;
import com.patientapp.request.util.RequestUtil;
import com.patientapp.request.util.StaticRequestHandler;
import com.patientapp.request.util.StaticRequestUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.request.util.AjaxRequestHandler;
import com.patientapp.request.util.AjaxRequestUtil;
import com.patientapp.util.PrivilegeChecker;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.SettingsUtil;
@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class RequestHandlerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Session masterSession = null;
		Session organisationSession = null;
		int isOrganisationSessionCreated = 0;
		Connection connection = null;
		int success = 0;
		try
		{
			masterSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName());
			connection = CommonUtil.getConnection();
			String projectPath = SettingsUtil.getProjectPath();
			String webContentFilesPath = projectPath + "/src/main/resources/WebContent"; 
			int isValidStaticUrl = 0;
			int isValidAjaxUrl = 0;			
			boolean isUserLoggedIn = RequestUtil.isUserLoggedIn(request, response, masterSession);
			SessionUtil sessionUtil = new SessionUtil();
			JsonObject userSessionInfo = sessionUtil.getLoggedInUserInfo(request, response, masterSession);
			JsonObject requestInfo = sessionUtil.getRequestInfo(request, response);
			String objectType = requestInfo.get("objectType").getAsString();
			
			organisationSession = masterSession;
			
			String privilegeObjectType = "";
			String privilegeObjectName = "";
			String privilegeActionType = "";
			int isPrivilegeRequired = 1;
			int isLoginRequired = 1;			
			AjaxRequestUtil ajaxRequestUtil = new AjaxRequestUtil();
			JsonObject ajaxUrlInfo = ajaxRequestUtil.getAjaxUrlInfo(userSessionInfo, requestInfo, masterSession);
			if(ajaxUrlInfo!=null)
			{
				isValidAjaxUrl = ajaxUrlInfo.get("isValidAjaxUrl").getAsInt();					
				isLoginRequired = ajaxUrlInfo.get("isLoginRequired").getAsInt();					
				isPrivilegeRequired = ajaxUrlInfo.get("isPrivilegeRequired").getAsInt();
				privilegeActionType = ajaxUrlInfo.get("privilegeActionType").getAsString();
				privilegeObjectType = ajaxUrlInfo.get("privilegeObjectType").getAsString();
				privilegeObjectName = ajaxUrlInfo.get("privilegeObjectName").getAsString();
			}
			if(isValidAjaxUrl!=1)
			{
				StaticRequestUtil staticRequestUtil = new StaticRequestUtil();
				JsonObject staticUrlInfo = staticRequestUtil.getStaticUrlInfo(userSessionInfo, requestInfo, masterSession);
				if(staticUrlInfo!=null)
				{
					isValidStaticUrl = staticUrlInfo.get("isValidStaticUrl").getAsInt();
					isLoginRequired = staticUrlInfo.get("isLoginRequired").getAsInt();					
					isPrivilegeRequired = staticUrlInfo.get("isPrivilegeRequired").getAsInt();
					privilegeActionType = staticUrlInfo.get("privilegeActionType").getAsString();
					privilegeObjectType = staticUrlInfo.get("privilegeObjectType").getAsString();
					privilegeObjectName = staticUrlInfo.get("privilegeObjectName").getAsString();
				}
				if(isValidStaticUrl!=1)
				{
		            String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
					String originalRequestURI = request.getRequestURI();			
					String requestURI = originalRequestURI;
					if(requestURI.startsWith(applicationUrlPrefix))
					{
						requestURI = requestURI.substring(applicationUrlPrefix.length());
					}
					String staticResourcesFilesPath = projectPath + "/src/main/resources/WebContent/html";
					String htmlFilePath = staticResourcesFilesPath + requestURI + ".html";
					File file = new File(htmlFilePath);
					if(file.exists())
					{
						StaticFileUtil staticFileUtil = new StaticFileUtil();
						staticFileUtil.handleStaticFileRequest(htmlFilePath, request, response);
						return;						
					}
					else
					{
						response.sendRedirect("/page-not-found");
						return;						
					}
				}
			}
			if(isPrivilegeRequired==1)
			{
				int hasPrivilege = RequestUtil.doesUserHasPrivilege(masterSession, privilegeObjectType, privilegeObjectName, privilegeActionType, userSessionInfo);
				if(hasPrivilege!=1)
				{
					if(isValidAjaxUrl==1)
					{
						JsonObject dataObject = new JsonObject();
						dataObject.addProperty("alert", "You do not have access to this functionality !!");
						response.setContentType("application/json");
						response.getWriter().write(new Gson().toJson(dataObject));				
						return;
					}
					else if(isValidStaticUrl==1)
					{
						response.sendRedirect("/privilege-required");
						return;
					}					
				}
			}
			if(isLoginRequired==1 && isUserLoggedIn==false)
			{
				if(isValidAjaxUrl==1)
				{
					int allowAccess = 0;
					String requestType = request.getParameter("requestType");
					if(requestType==null)
					{
						requestType = "";
					}
					requestType = requestType.trim();
					if ("/createEntity".equalsIgnoreCase(requestType))
					{
						Set<String> accessAllowedEntitiesList = new HashSet<String>();
						
						if(accessAllowedEntitiesList.contains(objectType))
						{
							allowAccess = 1;
						}				  
					}
					if(allowAccess!=1)
					{
						JsonObject dataObject = new JsonObject();
						dataObject.addProperty("alert", "You will have to login to access this functionality !!");
						response.setContentType("application/json");
						response.getWriter().write(new Gson().toJson(dataObject));				
						return;						
					}
				}
				else if(isValidStaticUrl==1)
				{
					String redirectUrl = SettingsUtil.sHomePageBeforeLoginUrl;
					if(redirectUrl.length() == 0)
					{
						redirectUrl = "/login";
					}
					response.sendRedirect(redirectUrl);
					return;
				}
			}
			if(isValidAjaxUrl==1)
			{
				//check for privilege and issue error if no privilege
				AjaxRequestHandler arh = new AjaxRequestHandler();
				JsonObject dataObject = arh.handleAjaxRequest(request, response, userSessionInfo, requestInfo, masterSession, organisationSession);
				if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
				{
					success = 1;
				}
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(dataObject));				
				return;
			}
			else if(isValidStaticUrl==1)
			{
				//check for privilege and issue error if no privilege
				StaticRequestHandler staticRequestHandler = new StaticRequestHandler();
				JsonObject dataObject = staticRequestHandler.handleStaticRequest(request, response, userSessionInfo, requestInfo, masterSession, organisationSession);
				if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
				{
					success = 1;
				}
				return;
			}
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
				Transaction tx = masterSession.getTransaction();
				if (tx.isActive())
				{
					if(success==1)
					{
						tx.commit();						
					}
					else
					{
						tx.rollback();						
					}					
				}
				masterSession.close();
				if(isOrganisationSessionCreated==1)
				{
					Transaction orgTx = organisationSession.getTransaction();
					if (orgTx.isActive())
					{
						if(success==1)
						{
							orgTx.commit();						
						}
						else
						{
							orgTx.rollback();						
						}					
					}
					organisationSession.close();
				}
			}
			catch (Exception e2)
			{
				//e2.printStackTrace();
				CommonUtil.writeTolog(e2);
			}
		}
		//PrintWriter out = response.getWriter();
	}
}
