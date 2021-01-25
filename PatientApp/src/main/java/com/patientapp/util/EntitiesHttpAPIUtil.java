package com.patientapp.util;
import org.apache.http.protocol.ResponseDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class EntitiesHttpAPIUtil
{
	public static void executeHttpAPI(Session session, int requestId)
	{
		int isAPIExecuted = 0;
		int isTransactionSuccessfullyExecuted = 0;
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		try
		{
						if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
				isAPIExecuted = organisationsImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session);
				isAPIExecuted = userInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
				isAPIExecuted = privilegeGroupImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
				isAPIExecuted = privilegeGroupItemsImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
				isAPIExecuted = employeeRolesImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session);
				isAPIExecuted = loginSessionInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session);
				isAPIExecuted = configPropertiesImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
				isAPIExecuted = taskInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
				isAPIExecuted = taskExecutionInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
				isAPIExecuted = taskLayoutParametersImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
				isAPIExecuted = emailAttachmentLayoutImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session);
				isAPIExecuted = taskScheduleInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session);
				isAPIExecuted = taskSentInfoImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
				isAPIExecuted = patientImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session);
				isAPIExecuted = doctorImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalImplObj = new com.patientapp.controller.forms.impl.HospitalControllerImpl(session);
				isAPIExecuted = hospitalImplObj.executeAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsImplObj = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(session);
				isAPIExecuted = contactUsImplObj.executeAPI(session, requestId, dataObject);
			}

		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			isTransactionSuccessfullyExecuted = 0;
		}
		finally
		{
			if(isAPIExecuted == 1)
			{
				isTransactionSuccessfullyExecuted = dataObject.get("success").getAsInt();
				if (isTransactionSuccessfullyExecuted == 1)
				{
					if (tx.isActive())
					{
						tx.commit();
					}
				}
				else
				{
					if (tx.isActive())
					{
						tx.rollback();
					}
				}
			}
		}
	}
	public static JsonObject executeAPI(Session session, String apiName, JsonObject requestParametersInfo)
	{
		int requestId = -1;
		int isAPIExecuted = 0;
		int isTransactionSuccessfullyExecuted = 0;
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		try
		{
			  
			  
			  
			  
				if(apiName.equals("executeQueriesScript"))
				{
					JsonObject responseData = QueriesDataLoader.executeQueriesScript(requestParametersInfo);
					dataObject.add("responseData", responseData);
					dataObject.addProperty("success", responseData.get("success").getAsInt());
					isAPIExecuted = 1;
				}
			  
			  
						if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
				isAPIExecuted = organisationsImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session);
				isAPIExecuted = userInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
				isAPIExecuted = privilegeGroupImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
				isAPIExecuted = privilegeGroupItemsImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
				isAPIExecuted = employeeRolesImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session);
				isAPIExecuted = loginSessionInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session);
				isAPIExecuted = configPropertiesImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
				isAPIExecuted = taskInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
				isAPIExecuted = taskExecutionInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
				isAPIExecuted = taskLayoutParametersImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
				isAPIExecuted = emailAttachmentLayoutImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session);
				isAPIExecuted = taskScheduleInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session);
				isAPIExecuted = taskSentInfoImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
				isAPIExecuted = patientImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session);
				isAPIExecuted = doctorImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalImplObj = new com.patientapp.controller.forms.impl.HospitalControllerImpl(session);
				isAPIExecuted = hospitalImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsImplObj = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(session);
				isAPIExecuted = contactUsImplObj.executeAPI(session, requestParametersInfo, requestId, apiName, dataObject);
			}

			if(isAPIExecuted == 1)
			{
				JsonObject responseData = dataObject.get("responseData").getAsJsonObject();
				return responseData;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			isTransactionSuccessfullyExecuted = 0;
		}
		finally
		{
			if(isAPIExecuted == 1)
			{
				isTransactionSuccessfullyExecuted = dataObject.get("success").getAsInt();
				if (isTransactionSuccessfullyExecuted == 1)
				{
					if (tx.isActive())
					{
						tx.commit();
					}
				}
				else
				{
					if (tx.isActive())
					{
						tx.rollback();
					}
				}
			}
			session.close();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static void executeHttpRollbackAPI(Session session, int requestId)
	{
		int isAPIExecuted = 0;
		int isTransactionSuccessfullyExecuted = 0;
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		try
		{
						if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
				isAPIExecuted = organisationsImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session);
				isAPIExecuted = userInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
				isAPIExecuted = privilegeGroupImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
				isAPIExecuted = privilegeGroupItemsImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
				isAPIExecuted = employeeRolesImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session);
				isAPIExecuted = loginSessionInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session);
				isAPIExecuted = configPropertiesImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
				isAPIExecuted = taskInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
				isAPIExecuted = taskExecutionInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
				isAPIExecuted = taskLayoutParametersImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
				isAPIExecuted = emailAttachmentLayoutImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session);
				isAPIExecuted = taskScheduleInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session);
				isAPIExecuted = taskSentInfoImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
				isAPIExecuted = patientImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session);
				isAPIExecuted = doctorImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalImplObj = new com.patientapp.controller.forms.impl.HospitalControllerImpl(session);
				isAPIExecuted = hospitalImplObj.executeRollbackAPI(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsImplObj = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(session);
				isAPIExecuted = contactUsImplObj.executeRollbackAPI(session, requestId, dataObject);
			}

		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			isTransactionSuccessfullyExecuted = 0;
		}
		finally
		{
			if(isAPIExecuted == 1)
			{
				isTransactionSuccessfullyExecuted = dataObject.get("success").getAsInt();
				if (isTransactionSuccessfullyExecuted == 1)
				{
					if (tx.isActive())
					{
						tx.commit();
					}
				}
				else
				{
					if (tx.isActive())
					{
						tx.rollback();
					}
				}
			}
		}
	}
	public static JsonObject getDataFromAPI(Session session, String apiName, JsonObject paramsInfo)
	{
		int isAPIExecuted = 0;
		JsonObject responseData = new JsonObject();
		responseData.addProperty("success", 0);
				if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
			responseData = organisationsImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session);
			responseData = userInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
			responseData = privilegeGroupImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
			responseData = privilegeGroupItemsImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
			responseData = employeeRolesImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session);
			responseData = loginSessionInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session);
			responseData = configPropertiesImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
			responseData = taskInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
			responseData = taskExecutionInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
			responseData = taskLayoutParametersImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
			responseData = emailAttachmentLayoutImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session);
			responseData = taskScheduleInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session);
			responseData = taskSentInfoImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
			responseData = patientImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session);
			responseData = doctorImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalImplObj = new com.patientapp.controller.forms.impl.HospitalControllerImpl(session);
			responseData = hospitalImplObj.getAPIData(session, apiName, paramsInfo);
		}
		if(responseData.get("success").getAsInt() == 0)
		{
			com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsImplObj = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(session);
			responseData = contactUsImplObj.getAPIData(session, apiName, paramsInfo);
		}

		return responseData;
	}
	public static void updateAPIStatus(Session session, int requestId)
	{
		int isAPIExecuted = 0;
		int isTransactionSuccessfullyExecuted = 0;
		JsonObject dataObject = new JsonObject();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		try
		{
						if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
				isAPIExecuted = organisationsImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session);
				isAPIExecuted = userInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
				isAPIExecuted = privilegeGroupImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
				isAPIExecuted = privilegeGroupItemsImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
				isAPIExecuted = employeeRolesImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session);
				isAPIExecuted = loginSessionInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session);
				isAPIExecuted = configPropertiesImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
				isAPIExecuted = taskInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
				isAPIExecuted = taskExecutionInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
				isAPIExecuted = taskLayoutParametersImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
				isAPIExecuted = emailAttachmentLayoutImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session);
				isAPIExecuted = taskScheduleInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session);
				isAPIExecuted = taskSentInfoImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
				isAPIExecuted = patientImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session);
				isAPIExecuted = doctorImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalImplObj = new com.patientapp.controller.forms.impl.HospitalControllerImpl(session);
				isAPIExecuted = hospitalImplObj.updateAPIStatus(session, requestId, dataObject);
			}
			if(isAPIExecuted!=1)
			{
				com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsImplObj = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(session);
				isAPIExecuted = contactUsImplObj.updateAPIStatus(session, requestId, dataObject);
			}

		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			isTransactionSuccessfullyExecuted = 0;
		}
		finally
		{
			if(isAPIExecuted == 1)
			{
				isTransactionSuccessfullyExecuted = dataObject.get("success").getAsInt();
				if (isTransactionSuccessfullyExecuted == 1)
				{
					if (tx.isActive())
					{
						tx.commit();
					}
				}
				else
				{
					if (tx.isActive())
					{
						tx.rollback();
					}
				}
			}
		}
	}
}
