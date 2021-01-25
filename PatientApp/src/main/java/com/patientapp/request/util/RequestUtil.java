package com.patientapp.request.util;
import java.sql.ResultSet;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.patientapp.util.CommonUtil;
import com.patientapp.request.util.SessionUtil;
import com.patientapp.bean.PrivilegeGroupItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl;
public class RequestUtil
{
	public static boolean isUserLoggedIn(HttpServletRequest request, HttpServletResponse response, Session masterSession)
	{
		try
		{
			String loginUserType = RequestUtil.getCookieStringValueFromRequest(request, "loginusertype");
			String selfServiceUserType = RequestUtil.getCookieStringValueFromRequest(request, "selfserviceusertype");
			String sessionId = getCookieStringValueFromRequest(request, "sessionid");
			JsonObject loginSessionInfo = SessionUtil.getLoginSessionInfo(sessionId, masterSession);
			if(loginSessionInfo == null)
			{
				return false;
			}
			if(loginUserType.equalsIgnoreCase("Admin"))
			{
				int userId = getCookieIntValueFromRequest(request, "userid");
				if(userId == loginSessionInfo.get("userId").getAsInt())
				{
					return true;
				}
			}
			else
			{
				if(1>2)
				{
				}
				
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return false;
	}
	public static Integer getCookieIntValueFromRequest(HttpServletRequest request, String cookieName) throws Exception
	{
		try
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (int i = 0; i < cookies.length; i++)
				{
					Cookie cookie = cookies[i];
					if (cookieName.equals(cookie.getName()))
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
	public static String getCookieStringValueFromRequest(HttpServletRequest request, String cookieName) throws Exception
	{
		try
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (int i = 0; i < cookies.length; i++)
				{
					Cookie cookie = cookies[i];
					if (cookieName.equals(cookie.getName()))
					{
						return cookie.getValue();
					}
				}
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return "";
	}
	public static JsonObject getPrivilegeGroupItemsList(JsonObject userSessionInfo, Session masterSession)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
/*			JsonArray privilegeGroupItemsList = new JsonArray();
			int isSuperUser = userSessionInfo.get("isSuperUser").getAsInt();
			if(isSuperUser == 1)
			{
				dataObject.add("privilegeGroupItemsList", privilegeGroupItemsList);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
*/			
			String loginUserType = userSessionInfo.get("loginUserType").getAsString();
			String selfServiceUserType = userSessionInfo.get("selfServiceUserType").getAsString();
			Transaction tx = masterSession.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			if(loginUserType.equalsIgnoreCase("Admin"))
			{
				return getAdminUserPrivilegesList(userSessionInfo, masterSession);
			}
			else
			{
				if(1>2)
				{
				}
				
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "User action privileges information could not be retrieved.");
		return dataObject;
	}
	public static JsonObject getAdminUserPrivilegesList(JsonObject userSessionInfo, Session masterSession)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonArray privilegeGroupItemsList = new JsonArray();
			int userId = userSessionInfo.get("userId").getAsInt();
			String loginUserType = userSessionInfo.get("loginUserType").getAsString();
			String selfServiceUserType = userSessionInfo.get("selfServiceUserType").getAsString();
			String hql = " FROM " 
				+ " PrivilegeGroupItems where "
				+ " privilegeGroupId in (SELECT privilegeGroupId from EmployeeRoles where userInfoId = :userInfoId) ";
			Query query = masterSession.createQuery(hql);
			query.setParameter("userInfoId", userId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItems.getDataObject(masterSession);
				privilegeGroupItemsList.add(privilegeGroupItemsDataObject);
			}
			dataObject.add("privilegeGroupItemsList", privilegeGroupItemsList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "User action privileges information could not be retrieved.");
		return dataObject;
	}
	
	public static int doesUserHasPrivilege(Session masterSession, String privilegeObjectType, String privilegeObjectName, String privilegeActionType, JsonObject userSessionInfo)
	{
		try
		{
/*			int isSuperUser = userSessionInfo.get("isSuperUser").getAsInt();
			if(isSuperUser == 1)
			{
				return 1;
			}
*/			String loginUserType = userSessionInfo.get("loginUserType").getAsString();
			String selfServiceUserType = userSessionInfo.get("selfServiceUserType").getAsString();
			Transaction tx = masterSession.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			if(loginUserType.equalsIgnoreCase("Admin"))
			{
				return doesAdminUserHasPrivilege(masterSession, privilegeObjectType, privilegeObjectName, privilegeActionType, userSessionInfo);
			}
			else
			{
				if(1>2)
				{
				}
				
			}
			return 0;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	public static int doesAdminUserHasPrivilege(Session masterSession, String privilegeObjectType, String privilegeObjectName, String privilegeActionType, JsonObject userSessionInfo)
	{
		try
		{
			int userId = userSessionInfo.get("userId").getAsInt();
			String countHql = " SELECT count(*) FROM " 
				+ " PrivilegeGroupItems where " 
				+ " privilegeActionType = :privilegeActionType " 
				+ " and privilegeObjectType = :privilegeObjectType " 
				+ " and privilegeObjectName = :privilegeObjectName " 
				+ " and privilegeGroupId in (SELECT privilegeGroupId from EmployeeRoles where userInfoId = :userInfoId) ";
			Query countQuery = masterSession.createQuery(countHql);
			countQuery.setParameter("privilegeActionType", privilegeActionType);
			countQuery.setParameter("privilegeObjectType", privilegeObjectType);
			countQuery.setParameter("privilegeObjectName", privilegeObjectName);
			countQuery.setParameter("userInfoId", userId);
			Long resultsCount = (Long) countQuery.uniqueResult();
			if (resultsCount > 0)
			{
				return 1;
			}
			return 0;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	
	public static boolean isPrivilegeItemExists(String privilegeActionType, String privilegeObjectType, String privilegeObjectName, JsonArray selfServicePrivilegeItemsList)
	{
		for(int i = 0; i< selfServicePrivilegeItemsList.size(); i++)
		{
			JsonObject selfServicePrivilegeItemInfo = selfServicePrivilegeItemsList.get(i).getAsJsonObject();
			String selfServicePrivilegeActionType = selfServicePrivilegeItemInfo.get("privilegeActionType").getAsString();
			String selfServicePrivilegeObjectType = selfServicePrivilegeItemInfo.get("privilegeObjectType").getAsString();
			String selfServicePrivilegeObjectName = selfServicePrivilegeItemInfo.get("privilegeObjectName").getAsString();
			if(selfServicePrivilegeActionType.equalsIgnoreCase(privilegeActionType)
					 && selfServicePrivilegeObjectType.equalsIgnoreCase(privilegeObjectType)
					 && selfServicePrivilegeObjectName.equalsIgnoreCase(privilegeObjectName))
			{
				return true;
			}
		}
		return false;
	}
    public static int configureSysadminPrivileges(Session masterSession, int userInfoId) throws Exception
    {
        try
        {
        	JsonObject userSessionInfo = new JsonObject(); 
        	userSessionInfo.addProperty("loggedInEmployeeId", userInfoId);
        	userSessionInfo.addProperty("userId", userInfoId);
        	userSessionInfo.addProperty("loginEntityType", "");
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl  privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
			com.patientapp.bean.PrivilegeGroup privilegeGroup = new com.patientapp.bean.PrivilegeGroup();
			privilegeGroup.setPrivilegeGroupName("Sysadmin");
			privilegeGroup.setPrivilegeCode("SYSADMIN");
			privilegeGroup.setApplicableUserType("Admin");
			privilegeGroup.setSelfServiceUser("");
			privilegeGroup.setPrivilegeGroupDescription("sysadmin");
			privilegeGroupControllerImpl.setManagedBean(privilegeGroup);
			privilegeGroupControllerImpl.setManagedBean("PrivilegeGroupBean", privilegeGroup);
			if (privilegeGroupControllerImpl.getHasTransactionFailed() == false)
			{
				JsonObject paramsInfoObj = new JsonObject();
				privilegeGroupControllerImpl.create(paramsInfoObj);
			}
			privilegeGroup = (com.patientapp.bean.PrivilegeGroup)privilegeGroupControllerImpl.getManagedBean();
			Integer privilegeGroupId = privilegeGroup.getPrivilegeGroupId();
			if(privilegeGroupId == null || privilegeGroupId < 0)
			{
				System.err.println("Default initialization information could not be created as Sysadmin Privilege Group could not be created.");
				return 0;
			}
			JsonArray sysadminPrivilegesList = new JsonArray();
			JsonObject privilegeInfo = new JsonObject();
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Create");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "View");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Update");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Delete");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Upload");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Download");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "UserInfo");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Create");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "View");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Update");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Delete");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Upload");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Download");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Create");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "View");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Update");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Delete");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Upload");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Download");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroupItems");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Create");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "View");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Update");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Delete");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Upload");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "Download");
			privilegeInfo.addProperty("privilegeObjectType", "Entity");
			privilegeInfo.addProperty("privilegeObjectName", "EmployeeRoles");
			sysadminPrivilegesList.add(privilegeInfo);
			privilegeInfo = new JsonObject();
			privilegeInfo.addProperty("privilegeActionType", "UpdatePrivilegeGroupItems");
			privilegeInfo.addProperty("privilegeObjectType", "Other");
			privilegeInfo.addProperty("privilegeObjectName", "PrivilegeGroup");
			sysadminPrivilegesList.add(privilegeInfo);
			for(int i =0; i< sysadminPrivilegesList.size(); i++)
			{
				JsonObject privilegeGroupItemInfo = sysadminPrivilegesList.get(i).getAsJsonObject();
				String privilegeActionType = privilegeGroupItemInfo.get("privilegeActionType").getAsString();
				String privilegeObjectType = privilegeGroupItemInfo.get("privilegeObjectType").getAsString();
				String privilegeObjectName = privilegeGroupItemInfo.get("privilegeObjectName").getAsString();
				com.patientapp.bean.PrivilegeGroupItems privilegeGroupItems = new com.patientapp.bean.PrivilegeGroupItems();
				privilegeGroupItems.setPrivilegeGroupId(privilegeGroupId);
				privilegeGroupItems.setPrivilegeActionType(privilegeActionType);
				privilegeGroupItems.setPrivilegeObjectType(privilegeObjectType);
				privilegeGroupItems.setPrivilegeObjectName(privilegeObjectName);
				PrivilegeGroupItemsControllerImpl  privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
				privilegeGroupItemsControllerImpl.setManagedBean(privilegeGroupItems);
				privilegeGroupItemsControllerImpl.create();
				if(privilegeGroupItemsControllerImpl.getHasTransactionFailed() == true)
				{
					System.err.println(privilegeGroupItemsControllerImpl.getTransactionFailureMessage());
					return 0;
				}
			}
			com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl  employeeRolesControllerImpl = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(masterSession, userSessionInfo);
			com.patientapp.bean.EmployeeRoles employeeRoles = new com.patientapp.bean.EmployeeRoles();
			employeeRoles.setUserInfoId(userInfoId);
			employeeRoles.setPrivilegeGroupId(privilegeGroupId);
			employeeRoles.setDescription("");
			employeeRolesControllerImpl.setManagedBean(employeeRoles);
			employeeRolesControllerImpl.setManagedBean("EmployeeRolesBean", employeeRoles);
			if (employeeRolesControllerImpl.getHasTransactionFailed() == false)
			{
				JsonObject paramsInfoObj = new JsonObject();
				employeeRolesControllerImpl.create(paramsInfoObj);
			}
			employeeRoles = (com.patientapp.bean.EmployeeRoles)employeeRolesControllerImpl.getManagedBean();
			Integer employeeRolesId = employeeRoles.getEmployeeRolesId();
			if(employeeRolesId == null || employeeRolesId < 0)
			{
				System.err.println("Default initialization information could not be created as Sysadmin Roles could not be assigned.");
				return 0;
			}
			return 1;
        }
        catch (Exception e)
        {
        	CommonUtil.writeTolog(e);
            return 0;
        }
    }
}
