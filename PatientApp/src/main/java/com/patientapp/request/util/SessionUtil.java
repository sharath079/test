package com.patientapp.request.util;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.protocol.ResponseDate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.patientapp.util.Tasks;
import com.google.gson.JsonArray;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl;
import com.patientapp.bean.LoginSessionInfo;
import com.patientapp.bean.UserInfo;
public class SessionUtil
{
	
	public static void initialiseUserTypePrivilegesList()
	{
		JsonObject allowedPrivilegeInfo = new JsonObject();
		
	}
	public static JsonObject loginAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject requestInfo, Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
			JsonObject responseData = loginAdmin(paramsInfo, session);
			if (responseData.has("success") &&  responseData.get("success").isJsonNull() == false && responseData.get("success").getAsInt() == 1)
			{
				if(responseData.has("sessionId"))
				{
					String sessionId = responseData.get("sessionId").getAsString();
					response.addCookie(new Cookie("sessionid", sessionId));
					response.addCookie(new Cookie("loginentitype", responseData.get("loginUserType").getAsString()));
					response.addCookie(new Cookie("loginusertype", responseData.get("loginUserType").getAsString()));
					response.addCookie(new Cookie("selfserviceusertype", responseData.get("selfServiceUserType").getAsString()));
					response.addCookie(new Cookie("usertype", responseData.get("selfServiceUserType").getAsString()));
					response.addCookie(new Cookie("employeeid", responseData.get("userId").getAsString()));
					response.addCookie(new Cookie("userid", responseData.get("userId").getAsString()));
					response.addCookie(new Cookie("issuperuser", responseData.get("isSuperUser").getAsString()));
					response.addCookie(new Cookie("organisationsid", responseData.get("organisationsId").getAsString()));
					response.addCookie(new Cookie("username", responseData.get("userName").getAsString()));
				}
				
				return responseData;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Login could not be possible");
		return dataObject;
	}
	public static JsonObject loginAdmin(JsonObject paramsInfo, Session session)
	{
		JsonObject dataObject = new JsonObject();
		Integer userId = -1;
		Integer isSuperUser = 0;
		Integer organisationsId = 0;
		Integer loginBranchId = -1;
		Session organisationSession = session;
		boolean createLoginSession = true;
		try
		{
			String loginPassword = paramsInfo.get("loginPassword").getAsString();
			String loginId = "";paramsInfo.get("loginPassword").getAsString();
			if(paramsInfo.has("loginEmailId"))
			{
				loginId = paramsInfo.get("loginEmailId").getAsString();
			}
			else if(paramsInfo.has("loginId"))
			{
				loginId = paramsInfo.get("loginId").getAsString();
			}
			if(paramsInfo.has("loginBranchId"))
			{
				loginBranchId = paramsInfo.get("loginBranchId").getAsInt();
			}
			Transaction tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			String passwordHash = CommonUtil.getHash(loginPassword);
			String hql = "FROM UserInfo where loginId = :loginId "
					+ " AND loginPassword = :loginPassword ";
			Query query = session.createQuery(hql);
			query.setParameter("loginId", loginId);
			query.setParameter("loginPassword", passwordHash);
			List userInfoList = query.list();
			for (Iterator it = userInfoList.iterator(); it.hasNext();)
			{
				UserInfo userInfo = (UserInfo) it.next();
				userId = userInfo.getUserInfoId();
				organisationsId = userInfo.getOrganisationsUserOrgId();
				break;
			}
			if (userId < 0)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Invalid credentials !!");
				return dataObject;
			}
			if (loginId.equals("sysadmin") && loginPassword.equals("welcome123"))
			{
				isSuperUser = 1;
			}
			JsonObject userSessionInfo = new JsonObject();
			userSessionInfo.addProperty("loggedInEmployeeId", userId);
			userSessionInfo.addProperty("loginEntityType", "");
			userSessionInfo.addProperty("loginUserType", "Admin");
			userSessionInfo.addProperty("userId", String.valueOf(userId));
			userSessionInfo.addProperty("organisationsId", String.valueOf(organisationsId));
			
			
			dataObject.addProperty("loginUserType", "Admin");
			dataObject.addProperty("selfServiceUserType", "");
			dataObject.addProperty("userId", String.valueOf(userId));
			dataObject.addProperty("organisationsId", String.valueOf(organisationsId));
			dataObject.addProperty("userName", loginId);
			dataObject.addProperty("isSuperUser", String.valueOf(isSuperUser));
			if(createLoginSession == true)
			{
				JsonObject responseData = createLoginSessionInfo(dataObject, session);
				if (!responseData.has("success") || responseData.get("success").isJsonNull() ==true || responseData.get("success").getAsInt() != 1)
				{
					return responseData;
				}
				dataObject.addProperty("sessionId", responseData.get("sessionId").getAsString());
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Login could not be possible");
			return dataObject;
		}
		finally 
		{
			
		}
	}
	
	
	public JsonObject getLoggedInUserInfo(HttpServletRequest request, HttpServletResponse response, Session masterSession) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		boolean isUserLoggedIn = RequestUtil.isUserLoggedIn(request, response, masterSession);
		int userId = -1;
		int loggedInEmployeeId = -1;
		int organisationsId = -1;
		int isSuperUser = 0;
		String sessionId = "";
		if(isUserLoggedIn==true)
		{
			sessionId = RequestUtil.getCookieStringValueFromRequest(request, "sessionid");
			String loginUserType = RequestUtil.getCookieStringValueFromRequest(request, "loginusertype");
			String selfServiceUserType = "";
			if(!loginUserType.equalsIgnoreCase("Admin"))
			{
				selfServiceUserType = RequestUtil.getCookieStringValueFromRequest(request, "selfserviceusertype");
			}
			loggedInEmployeeId = CommonUtil.getEmployeeIdFromRequest(request);
			String loginEntityType = RequestUtil.getCookieStringValueFromRequest(request, "selfserviceusertype");
			userId = RequestUtil.getCookieIntValueFromRequest(request, "userid");
			isSuperUser = RequestUtil.getCookieIntValueFromRequest(request, "issuperuser");
			organisationsId = RequestUtil.getCookieIntValueFromRequest(request, "organisationsid");
			dataObject.addProperty("sessionId", sessionId);
			dataObject.addProperty("loggedInEmployeeId", loggedInEmployeeId);
			dataObject.addProperty("organisationsId", organisationsId);
			dataObject.addProperty("userId", userId);
			dataObject.addProperty("isUserLoggedIn", 1);
			dataObject.addProperty("loginEntityType", loginEntityType);
			dataObject.addProperty("loginUserType", loginUserType);
			dataObject.addProperty("selfServiceUserType", selfServiceUserType);
			dataObject.addProperty("isSuperUser", isSuperUser);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		dataObject.addProperty("sessionId", sessionId);
		dataObject.addProperty("loggedInEmployeeId", loggedInEmployeeId);
		dataObject.addProperty("userId", userId);
		dataObject.addProperty("isUserLoggedIn", 0);
		dataObject.addProperty("loginEntityType", "");
		dataObject.addProperty("loginUserType", "");
		dataObject.addProperty("selfServiceUserType", "");
		dataObject.addProperty("isSuperUser", isSuperUser);
		dataObject.addProperty("success", 1);
		return dataObject;
	}
	public JsonObject getRequestInfo(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		String objectType = "";
		String requestType = "";
		int loginBranchId = -1;
		int objectId = -1;
		String attributeName = "";
		JsonObject paramsInfo = new JsonObject();
		String sObjectType = request.getParameter("objectType");
		String sRequestType = request.getParameter("requestType");
		String loginbranchidString = request.getParameter("loginbranchid");
		String sObjectId = request.getParameter("objectId");
		String sAttributeName = request.getParameter("attributeName");
		if(sObjectType != null)
		{
			objectType = sObjectType.trim();
		}
		if(sRequestType != null)
		{
			requestType = sRequestType.trim();
		}
		if (loginbranchidString != null && loginbranchidString.length() > 0)
		{
			loginBranchId= Integer.parseInt(loginbranchidString.trim());
		}
		if(sAttributeName != null)
		{
			attributeName = sAttributeName.trim();
		}
		if (sObjectId != null && sObjectId.length() > 0)
		{
			objectId = Integer.parseInt(sObjectId.trim());
		}
		String sParamsInfo = request.getParameter("paramsInfo");
		if(sParamsInfo != null)
		{
			paramsInfo = new Gson().fromJson(sParamsInfo, JsonObject.class);
		}
        String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
		String originalRequestURI = request.getRequestURI();			
		String requestURI = originalRequestURI;
		if(requestURI.startsWith(applicationUrlPrefix))
		{
			requestURI = requestURI.substring(applicationUrlPrefix.length());
		}
		dataObject.addProperty("objectType", objectType);
		dataObject.addProperty("requestURI", requestURI);
		dataObject.addProperty("requestType", requestType);
		dataObject.addProperty("loginBranchId", loginBranchId);
		dataObject.addProperty("objectId", objectId);
		dataObject.addProperty("attributeName", attributeName);
		dataObject.add("paramsInfo", paramsInfo);
		return dataObject;
	}
	public JsonObject loginGenericUser(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Connection genericConn,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			dataObject = loginAdmin(request, response, requestInfo, session);
			if (dataObject.get("success").getAsInt() == 1)
			{
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "The user id/password entered are incorrect");
		return dataObject;
	}
	public JsonObject resendActivationLink(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Connection genericConn,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Verification link could not be sent, as given Email Id is not associated with any registered account.");
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Verification link could not be sent.");
		return dataObject;
	}
	public JsonObject sendResetPasswordLink(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Connection genericConn,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject = sendResetPasswordLinkForAdmin(request, response, userSessionInfo, requestInfo, session);
			if (dataObject.get("success").getAsInt() == 1)
			{
				return dataObject;
			}
			
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Reset pasword link could not be sent, as given Email Id is not associated with any registered account.");
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Verification link could not be sent.");
		return dataObject;
	}
	public JsonObject sendResetPasswordLinkForAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		try
		{
			if (!tx.isActive())
			{
				tx.begin();
			}
			JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
			String loginEmailId = paramsInfo.get("loginEmailId").getAsString();
			String hql = "FROM UserInfo where  "
					+ " loginEmailId = :loginEmailId ";
			Query query = session.createQuery(hql);
			query.setParameter("loginEmailId", loginEmailId);
			List $$ENTITY_NAME_INIT_LOWER$$List = query.list();
			for (Iterator it = $$ENTITY_NAME_INIT_LOWER$$List.iterator(); it.hasNext();)
			{
				com.patientapp.bean.UserInfo userInfo = (com.patientapp.bean.UserInfo) it.next();
				String resetToken = CommonUtil.getRandomNo();
				userInfo.setResetToken(resetToken);
				session.merge(userInfo);
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", "Reset password link is now sent to your email. Check your email and click on that link to reset your password.");
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Your activation link could not be generated.");
		return dataObject;
	}
	public JsonObject resetPasswordForAdmin(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		try
		{
			if (!tx.isActive())
			{
				tx.begin();
			}
			JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
			String loginPassword = paramsInfo.get("loginPassword").getAsString();
			String loginEmailId = paramsInfo.get("loginEmailId").getAsString();
			String token = paramsInfo.get("token").getAsString();
			String hql = "FROM UserInfo where  "
					+ " resetToken = :resetToken "
					+ "  AND loginEmailId = :loginEmailId  ";
			Query query = session.createQuery(hql);
			query.setParameter("loginEmailId", loginEmailId);
			query.setParameter("resetToken", token);
			List userInfoList = query.list();
			for (Iterator it = userInfoList.iterator(); it.hasNext();)
			{
				com.patientapp.bean.UserInfo userInfo = (com.patientapp.bean.UserInfo) it.next();
				String passwordHash = CommonUtil.getHash(loginPassword);
				userInfo.setLoginPassword(passwordHash);
				session.merge(userInfo);
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", "Password updated successfully. Try to login with your new Password.");
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Password could not be updated.");
		return dataObject;
	}
	public JsonObject resetPassword(HttpServletRequest request, HttpServletResponse response, JsonObject userSessionInfo, JsonObject requestInfo, Connection genericConn,  Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject = resetPasswordForAdmin(request, response, userSessionInfo, requestInfo, session);
			if (dataObject.get("success").getAsInt() == 1)
			{
				return dataObject;
			}
			
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Pasword could not be updated, as given details not associated with any registered account.");
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Verification link could not be sent.");
		return dataObject;
	}
	
	public static JsonObject createLoginSessionInfo(JsonObject userSessionInfo, Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String loginUserType = userSessionInfo.get("loginUserType").getAsString();
			String selfServiceUserType = userSessionInfo.get("selfServiceUserType").getAsString();
			int userId = userSessionInfo.get("userId").getAsInt();
			Date loginTime = new Date();
			LoginSessionInfoControllerImpl  loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(session, userSessionInfo);
			LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
			loginSessionInfo.setLoginUserType(loginUserType);
			loginSessionInfo.setSelfServiceUserType(selfServiceUserType);
			loginSessionInfo.setUserId(userId);
			loginSessionInfo.setLoginTime(new Date());
			loginSessionInfoControllerImpl.setManagedBean("LoginSessionInfoBean", loginSessionInfo);
			loginSessionInfoControllerImpl.setManagedBean(loginSessionInfo);
			loginSessionInfoControllerImpl.create();
			if (loginSessionInfoControllerImpl.getHasTransactionFailed() == true)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Login could not be processed as session could not be created.");
				return dataObject;
			}
			int loginSessionInfoId = loginSessionInfoControllerImpl.getPKValue();
			String sessionId = loginSessionInfoId+String.valueOf(loginTime.getTime());
			loginSessionInfo = (LoginSessionInfo) loginSessionInfoControllerImpl.getManagedBean();
			loginSessionInfo.setSessionId(sessionId);
			session.merge(loginSessionInfo);
			dataObject.addProperty("success", 1);
			dataObject.addProperty("sessionId", sessionId);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Login could not be processed as session could not be created.");
		return dataObject;
	}
	public static JsonObject logoutUser(HttpServletResponse response, JsonObject userSessionInfo, Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String loginUserType = userSessionInfo.get("loginUserType").getAsString();
			String selfServiceUserType = userSessionInfo.get("selfServiceUserType").getAsString();
			String sessionId = userSessionInfo.get("sessionId").getAsString();
			LoginSessionInfoControllerImpl  loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(session, userSessionInfo);
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("sessionId", sessionId);
			JsonObject responseData = loginSessionInfoControllerImpl.getLoginSessionInfo(session, searchParams, "", "", "");
			if(responseData.has("success") && responseData.get("success").getAsInt() ==1)
			{
				JsonObject loginSessionInfoDataObject = responseData.get("loginSessionInfo").getAsJsonObject();
				int loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
				LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
				session.delete(loginSessionInfo);
			}
			response.addCookie(new Cookie("sessionid", ""));
			response.addCookie(new Cookie("loginentitype", ""));
			response.addCookie(new Cookie("loginusertype", ""));
			response.addCookie(new Cookie("selfserviceusertype", ""));
			response.addCookie(new Cookie("usertype", ""));
			response.addCookie(new Cookie("employeeid", ""));
			response.addCookie(new Cookie("userid", ""));
			response.addCookie(new Cookie("issuperuser", ""));
			response.addCookie(new Cookie("organisationsid", ""));
			response.addCookie(new Cookie("username", ""));
			
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Your Request could not be processed.");
		return dataObject;
	}
	public static JsonObject getLoginSessionInfo(String sessionId, Session session)
	{
		try
		{
			LoginSessionInfoControllerImpl  loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(session);
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("sessionId", sessionId);
			JsonObject responseData = loginSessionInfoControllerImpl.getLoginSessionInfo(session, searchParams, "", "", "");
			if(responseData.has("success") && responseData.get("success").getAsInt() ==1)
			{
				return responseData.get("loginSessionInfo").getAsJsonObject();
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
    public static int initializeLoginUserSessionMap()
    {
		Session masterSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName());
		int success = 0;
        try
        {
        	JsonObject userSessionInfo = new JsonObject(); 
        	userSessionInfo.addProperty("loggedInEmployeeId", -1);
        	userSessionInfo.addProperty("userId", -1);
        	userSessionInfo.addProperty("loginEntityType", "");
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			LoginSessionInfoControllerImpl  loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
			JsonObject loginSessionInfoRespData = loginSessionInfoControllerImpl.retrieveLoginSessionInfoList(paramsMap);
			if(!loginSessionInfoRespData.has("success") || loginSessionInfoRespData.get("success").getAsInt() != 1)
			{
				return 0;
			}
			JsonArray loginSessionInfoList = loginSessionInfoRespData.get("loginSessionInfoList").getAsJsonArray();
			for(int i =0; i< loginSessionInfoList.size(); i++)
			{
				JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
				String loginUserType = loginSessionInfoDataObject.get("loginUserType").getAsString(); 
				String selfServiceUserType = loginSessionInfoDataObject.get("selfServiceUserType").getAsString();
				int userId = loginSessionInfoDataObject.get("userId").getAsInt();
				String sessionId = loginSessionInfoDataObject.get("sessionId").getAsString();
				if(loginUserType.equalsIgnoreCase("Admin"))
				{
					//adminSessionMap.put(sessionId, userId);
				}
				else
				{
					if(1>2)
					{
					}
					
				}
			}
			success = 1;
            return 1;
        }
        catch (Exception e)
        {
        	CommonUtil.writeTolog(e);
            return 0;
        }
        finally
        {
			try
			{
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
			}
			catch (Exception e2)
			{
				CommonUtil.writeTolog(e2);
			}
        }
    }
}
