package com.patientapp.util;
import java.util.Date;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.request.util.RequestUtil;
public class TasksUtil
{
	public static int executeTaskAPI(Session organisationSession, String apiName, JsonObject taskInfoObj, int  taskInfoId, int  targetUserId, int targetEntityId, String notificationMedium, Date nextNotificationTime)
	{
		if (apiName.equalsIgnoreCase("api1"))
		{
			System.out.println("API 1");
		}
		if (apiName.equalsIgnoreCase("api2"))
		{
			System.out.println("API 2");
		}
		if (apiName.equalsIgnoreCase("AstrologerReplyNotificationToAdmin") || apiName.equalsIgnoreCase("NewServiceRequestNotificationToAdmin"))
		{
			try
			{
				int doesUserHasAccessToLayout = doesUserHasAccessToLayout(organisationSession, taskInfoObj, taskInfoId,  targetUserId, targetEntityId, notificationMedium, nextNotificationTime);
				if(doesUserHasAccessToLayout ==1)
				{
					return Tasks.sendNotification(organisationSession, taskInfoObj, taskInfoId, targetUserId, targetEntityId, notificationMedium, nextNotificationTime);
				}
				else
				{
					return 1;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return 0;			
		}
		return 1;
	}
	public static String calculateTaskStartDate(Date nextNotificationTime)
	{
		return "02/06/2020";
	}
	public static String calculateTaskEndDate(Date nextNotificationTime)
	{
		return "02/06/2020";
	}
	//doesUserHasAccessToLayout
	public static int doesUserHasAccessToLayout(Session organisationSession, JsonObject taskInfoObj, int  taskInfoId, int  targetUserId, int targetEntityId, String notificationMedium, Date nextNotificationTime) throws Exception
	{
		String enableEmailNotification = taskInfoObj.get("enableEmailNotification").getAsString();
		String emailNotifactionLayout = taskInfoObj.get("emailNotificationLayout").getAsString();
		if (enableEmailNotification.equalsIgnoreCase("No"))
		{
			return 1;
		}
		String privilegeObjectType = "Report";
		String privilegeObjectName = emailNotifactionLayout;
		String privilegeActionType = "accessLayout";
		JsonObject userSessionInfo = new JsonObject();
		userSessionInfo.addProperty("userId", targetUserId);
		userSessionInfo.addProperty("loginUserType", "Admin");
		userSessionInfo.addProperty("selfServiceUserType", "");
		int hasPrivilege = RequestUtil.doesUserHasPrivilege(organisationSession, privilegeObjectType, privilegeObjectName, privilegeActionType, userSessionInfo);
		if (hasPrivilege != 1)
		{
			return 0;
		}
		return 1;		
	}
}
