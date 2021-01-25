package com.patientapp.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.patientapp.request.util.AjaxRequestHandler;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class PrivilegeChecker
{
	public static int getLoginBranchId(HttpServletRequest request)
	{
		Cookie[] cookieList = request.getCookies();
		if (cookieList != null)
		{
			for (int i = 0; i < cookieList.length; i++)
			{
				if (cookieList[i].getName().equals("loginbranchid"))
				{
					String loginBranchIdVal = cookieList[i].getValue();
					int loginBranchId = Integer.parseInt(loginBranchIdVal);
					return loginBranchId;
				}
			}
		}
		return -1;
	}
	public static int checkEmployeePrivilege(Session masterSession, String privilegeType, String transactionEntity, Integer employeeId, JsonObject retValObject)
	{
		ResultSet countSet = null;
		// String privilegeType = entityRequestTypeActionMap.get(requestType);
		try
		{
			Transaction tx = masterSession.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			String countHql = " SELECT count(*) FROM " 
				+ " PrivilegeGroupItems where " 
				+ " privilegeActionType = :privilegeActionType " 
				+ " and privilegeObjectType = :privilegeObjectType " 
				+ " and privilegeObjectName = :privilegeObjectName " 
				+ " and privilegeGroupId in (SELECT privilegeGroupId from EmployeeRoles where userInfoId = :userInfoId) ";
			Query countQuery = masterSession.createQuery(countHql);
			countQuery.setParameter("privilegeActionType", privilegeType);
			countQuery.setParameter("privilegeObjectName", transactionEntity);
			countQuery.setParameter("privilegeObjectName", transactionEntity);
			countQuery.setParameter("userInfoId", employeeId);
			Long resultsCount = (Long) countQuery.uniqueResult();
			if (resultsCount > 0)
			{
				return 1;
			}
			retValObject.addProperty("alert", "You dont have privilege to do this action.");
			retValObject.addProperty("success", 0);
			return 0;
//			return 1;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		retValObject.addProperty("alert", "Your request could not be processed as, privilege information could not be retrieved.");
		retValObject.addProperty("success", 0);
		return 0;
	}
	public static int getIsSuperUser(HttpServletRequest request) throws Exception
	{
		int isSuperUser = 0;
		try
		{
			String issuperuserString = request.getParameter("issuperuser");
			if (issuperuserString != null && issuperuserString.length() > 0)
			{
				return Integer.parseInt(issuperuserString.trim());
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return isSuperUser;
	}
	public static boolean isLoginRequired(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int employeeId = getEmployeeIdFromRequestCoockies(request);
			if (employeeId > 0)
			{
				if (CommonUtil.employeeSessionMap.contains(employeeId))
				{
					return false;
				}
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return true;
	}
	public static Integer getEmployeeIdFromRequestCoockies(HttpServletRequest request) throws Exception
	{
		try
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (int i = 0; i < cookies.length; i++)
				{
					Cookie cookie = cookies[i];
					if ("employeeid".equals(cookie.getName()))
					{
						return Integer.parseInt((cookie.getValue()));
					}
				}
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return -1;
	}
	public static Integer getIsSuperUserFromRequestCoockies(HttpServletRequest request) throws Exception
	{
		try
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (int i = 0; i < cookies.length; i++)
				{
					Cookie cookie = cookies[i];
					if ("issuperuser".equals(cookie.getName()))
					{
						return Integer.parseInt((cookie.getValue()));
					}
				}
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	/*
	 * privilegeObjectType : entity/report/other
	 */
	public static JsonObject getPrivilegeInfo(HttpServletRequest request)
	{
		String originalRequestURI = request.getRequestURI();
		String requestURI = originalRequestURI;
		String applicationUrlPrefix = CommonUtil.getApplicationUrlPrefix();
		if (requestURI.startsWith(applicationUrlPrefix))
		{
			requestURI = requestURI.substring(applicationUrlPrefix.length());
		}
		JsonObject privilegeInfo = new JsonObject();
		if (requestURI.startsWith("/entity/"))
		{
			String privilegeType = "";
			String entityInfo = requestURI.substring("/entity/".length());
			int entityInfoEndIndex = entityInfo.indexOf(".html");
			if (entityInfoEndIndex > 0)
			{
				entityInfo = entityInfo.substring(0, entityInfoEndIndex);
			}
			String entityName = entityInfo;
			if (entityInfo.startsWith("Create"))
			{
				privilegeType = "CREATE";
				entityName = entityName.substring("Create".length());
			}
			else if (entityInfo.endsWith("Dashboard"))
			{
				privilegeType = "SEARCH";
				entityName = entityName.substring(0, entityInfo.lastIndexOf("Dashboard"));
			}
			else if (entityInfo.startsWith("View"))
			{
				privilegeType = "VIEW";
				entityName = entityName.substring("View".length());
			}
			privilegeInfo.addProperty("privilegeObjectType", "entity");
			privilegeInfo.addProperty("privilegeObjectName", entityName);
			privilegeInfo.addProperty("privilegeType", privilegeType);
			return privilegeInfo;
		}
		else if (requestURI.startsWith("/AjaxServlet"))
		{
			String requestType = request.getParameter("requestType").trim();
			String objectType = request.getParameter("objectType");
			if (entityRequestTypeActionMap.containsKey(requestType))
			{
				privilegeInfo.addProperty("privilegeObjectType", "entity");
				privilegeInfo.addProperty("privilegeObjectName", objectType);
				privilegeInfo.addProperty("privilegeType", entityRequestTypeActionMap.get(requestType));
				return privilegeInfo;
			}
		}
		return null;
	}
	private static HashMap<String, String> entityRequestTypeActionMap = new HashMap<String, String>();
	static
	{
		entityRequestTypeActionMap.put("/createEntity", "CREATE");
		entityRequestTypeActionMap.put("/retrieveEntityList", "SEARCH");
		entityRequestTypeActionMap.put("/retrieveEntity", "VIEW");
		entityRequestTypeActionMap.put("/updateEntity", "UPDATE");
		entityRequestTypeActionMap.put("/deleteEntity", "DELETE");
		entityRequestTypeActionMap.put("/fetchEntityDefaultData", "CREATE");
		entityRequestTypeActionMap.put("/fetchEntityTestData", "CREATE");
		/*
		 * entityRequestTypeActionMap.put("executeCustomAPI", "");
		 * entityRequestTypeActionMap.put("getChildEntityList", "VIEW");
		 * entityRequestTypeActionMap.put("executeAction", "APPROVE");
		 * entityRequestTypeActionMap.put("lookupRowSelected", "VIEW");
		 * entityRequestTypeActionMap.put("selectOptionChanged", "VIEW");
		 * entityRequestTypeActionMap.put("uploadData", "VIEW");
		 * entityRequestTypeActionMap.put("downloadData", "VIEW");
		 */
	}
}
