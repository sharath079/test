package com.patientapp.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.hibernate.Session;
import com.patientapp.request.util.SessionUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
public class UploadDownloadUtil
{
	public static JsonObject downloadData(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
		try
		{
			SessionUtil sessionUtil = new SessionUtil();
			String projectTemplatesPath = com.patientapp.util.SettingsUtil.getProjectTemplatesPath();
			FileInputStream file = new FileInputStream(new File(projectTemplatesPath+"DownloadTemplate.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
						
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(masterSession, userSessionInfo);
			dataObject = organisationsControllerImpl.downloadOrganisationsData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(masterSession, userSessionInfo);
			dataObject = userInfoControllerImpl.downloadUserInfoData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
			dataObject = privilegeGroupControllerImpl.downloadPrivilegeGroupData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
  
			
			  
			
						
			com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
			dataObject = loginSessionInfoControllerImpl.downloadLoginSessionInfoData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}

						
			com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesControllerImpl = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
			dataObject = configPropertiesControllerImpl.downloadConfigPropertiesData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(organisationSession, userSessionInfo);
			dataObject = taskInfoControllerImpl.downloadTaskInfoData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
    
			
			    
			
			    
			
						
			com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
			dataObject = taskScheduleInfoControllerImpl.downloadTaskScheduleInfoData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
			dataObject = taskSentInfoControllerImpl.downloadTaskSentInfoData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
    
			
						
			com.patientapp.controller.forms.impl.DoctorControllerImpl doctorControllerImpl = new com.patientapp.controller.forms.impl.DoctorControllerImpl(organisationSession, userSessionInfo);
			dataObject = doctorControllerImpl.downloadDoctorData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalControllerImpl = new com.patientapp.controller.forms.impl.HospitalControllerImpl(organisationSession, userSessionInfo);
			dataObject = hospitalControllerImpl.downloadHospitalData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			
			com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsControllerImpl = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(organisationSession, userSessionInfo);
			dataObject = contactUsControllerImpl.downloadContactUsData(workbook);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}

			workbook.removeSheetAt(0);
			String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "Upload.xls";
			String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "Upload.xls";
			FileOutputStream out = new FileOutputStream(new File(savedFileName));
			workbook.write(out);
			out.close();
//			int fileId = CommonUtil.sequenceCount;
//			CommonUtil.fileIDAndNamesMap.put(fileId, savedFileName);
//			CommonUtil.sequenceCount++;
			int fileId = CommonUtil.saveFile(fileName, savedFileName, organisationSession);
			if(fileId < 0)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Your request could not be processed.");
			}
			else
			{
				dataObject.addProperty("fileId", fileId);
				dataObject.addProperty("success", 1);
			}
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public static JsonArray getDataToRetry(JsonArray dataObjectsList)
	{
		JsonArray dataListToRetry = new JsonArray();
		for(int i=0; i<dataObjectsList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject dataObject = dataObjectsList.get(i).getAsJsonObject();
			if(dataObject.has("retryUpload") && dataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = dataObject.get("retryUpload").getAsInt();
			}
			if(dataObject.has("retryChildEntitiesUpload") && dataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = dataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(dataObject.has("partialUploadUnderProcess") && dataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = dataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				dataListToRetry.add(dataObject);
			}
		}
		return dataListToRetry;
	}
	public static int  areSomeRecordsUploaded(JsonObject previousRetryCountMap, JsonObject retryCountMap, Session masterSession, Session organisationSession) throws Exception
	{
		int areSomeRecordsUploaded = 0;
				
		com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(masterSession);
		areSomeRecordsUploaded = organisationsControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		
		com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(masterSession);
		areSomeRecordsUploaded = userInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		
		com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(masterSession);
		areSomeRecordsUploaded = privilegeGroupControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
  
		
		  
		
				
		com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(masterSession);
		areSomeRecordsUploaded = loginSessionInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesControllerImpl = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(organisationSession);
		areSomeRecordsUploaded = configPropertiesControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(organisationSession);
		areSomeRecordsUploaded = taskInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(organisationSession);
		areSomeRecordsUploaded = taskExecutionInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(organisationSession);
		areSomeRecordsUploaded = taskLayoutParametersControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(organisationSession);
		areSomeRecordsUploaded = emailAttachmentLayoutControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(organisationSession);
		areSomeRecordsUploaded = taskScheduleInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(organisationSession);
		areSomeRecordsUploaded = taskSentInfoControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.PatientControllerImpl patientControllerImpl = new com.patientapp.controller.forms.impl.PatientControllerImpl(organisationSession);
		areSomeRecordsUploaded = patientControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.DoctorControllerImpl doctorControllerImpl = new com.patientapp.controller.forms.impl.DoctorControllerImpl(organisationSession);
		areSomeRecordsUploaded = doctorControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalControllerImpl = new com.patientapp.controller.forms.impl.HospitalControllerImpl(organisationSession);
		areSomeRecordsUploaded = hospitalControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}
		com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsControllerImpl = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(organisationSession);
		areSomeRecordsUploaded = contactUsControllerImpl.areSomeRecordsUploaded(previousRetryCountMap, retryCountMap);
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

		return 0;
	}
	public static JsonObject retryUploads(JsonObject retryListMap, Session masterSession, Session organisationSession, JsonObject userSessionInfo) throws Exception
	{
		JsonArray dataObjectsListToRetry = null;
		JsonArray uploadRetryList = null;
		JsonObject dataObject = new JsonObject();
		JsonObject responseData = new JsonObject();
		JsonObject updatedRetryListMap = new JsonObject();
		int retryObjectsCount = 0;
		JsonObject retryCountMap = new JsonObject();
				
		if(retryListMap.has("OrganisationsUploadRetryList") && retryListMap.get("OrganisationsUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("OrganisationsUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(masterSession, userSessionInfo);
			responseData = organisationsControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("OrganisationsUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			organisationsControllerImpl.updateRetryCountMapForOrganisationsList(uploadRetryList, retryCountMap);
		}
		
		if(retryListMap.has("UserInfoUploadRetryList") && retryListMap.get("UserInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("UserInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(masterSession, userSessionInfo);
			responseData = userInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("UserInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			userInfoControllerImpl.updateRetryCountMapForUserInfoList(uploadRetryList, retryCountMap);
		}
		
		if(retryListMap.has("PrivilegeGroupUploadRetryList") && retryListMap.get("PrivilegeGroupUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("PrivilegeGroupUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
			responseData = privilegeGroupControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("PrivilegeGroupUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			privilegeGroupControllerImpl.updateRetryCountMapForPrivilegeGroupList(uploadRetryList, retryCountMap);
		}
  
		
		  
		
				
		if(retryListMap.has("LoginSessionInfoUploadRetryList") && retryListMap.get("LoginSessionInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("LoginSessionInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
			responseData = loginSessionInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("LoginSessionInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			loginSessionInfoControllerImpl.updateRetryCountMapForLoginSessionInfoList(uploadRetryList, retryCountMap);
		}

				if(retryListMap.has("ConfigPropertiesUploadRetryList") && retryListMap.get("ConfigPropertiesUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("ConfigPropertiesUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesControllerImpl = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
			responseData = configPropertiesControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("ConfigPropertiesUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			configPropertiesControllerImpl.updateRetryCountMapForConfigPropertiesList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("TaskInfoUploadRetryList") && retryListMap.get("TaskInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(organisationSession, userSessionInfo);
			responseData = taskInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("TaskInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			taskInfoControllerImpl.updateRetryCountMapForTaskInfoList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("TaskExecutionInfoUploadRetryList") && retryListMap.get("TaskExecutionInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskExecutionInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
			responseData = taskExecutionInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("TaskExecutionInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			taskExecutionInfoControllerImpl.updateRetryCountMapForTaskExecutionInfoList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("TaskLayoutParametersUploadRetryList") && retryListMap.get("TaskLayoutParametersUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskLayoutParametersUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
			responseData = taskLayoutParametersControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("TaskLayoutParametersUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			taskLayoutParametersControllerImpl.updateRetryCountMapForTaskLayoutParametersList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("EmailAttachmentLayoutUploadRetryList") && retryListMap.get("EmailAttachmentLayoutUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("EmailAttachmentLayoutUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
			responseData = emailAttachmentLayoutControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("EmailAttachmentLayoutUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			emailAttachmentLayoutControllerImpl.updateRetryCountMapForEmailAttachmentLayoutList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("TaskScheduleInfoUploadRetryList") && retryListMap.get("TaskScheduleInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskScheduleInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
			responseData = taskScheduleInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("TaskScheduleInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			taskScheduleInfoControllerImpl.updateRetryCountMapForTaskScheduleInfoList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("TaskSentInfoUploadRetryList") && retryListMap.get("TaskSentInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskSentInfoUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
			responseData = taskSentInfoControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("TaskSentInfoUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			taskSentInfoControllerImpl.updateRetryCountMapForTaskSentInfoList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("PatientUploadRetryList") && retryListMap.get("PatientUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("PatientUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.PatientControllerImpl patientControllerImpl = new com.patientapp.controller.forms.impl.PatientControllerImpl(organisationSession, userSessionInfo);
			responseData = patientControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("PatientUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			patientControllerImpl.updateRetryCountMapForPatientList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("DoctorUploadRetryList") && retryListMap.get("DoctorUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("DoctorUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.DoctorControllerImpl doctorControllerImpl = new com.patientapp.controller.forms.impl.DoctorControllerImpl(organisationSession, userSessionInfo);
			responseData = doctorControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("DoctorUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			doctorControllerImpl.updateRetryCountMapForDoctorList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("HospitalUploadRetryList") && retryListMap.get("HospitalUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("HospitalUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalControllerImpl = new com.patientapp.controller.forms.impl.HospitalControllerImpl(organisationSession, userSessionInfo);
			responseData = hospitalControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("HospitalUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			hospitalControllerImpl.updateRetryCountMapForHospitalList(uploadRetryList, retryCountMap);
		}
		if(retryListMap.has("ContactUsUploadRetryList") && retryListMap.get("ContactUsUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("ContactUsUploadRetryList").getAsJsonArray();		
			com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsControllerImpl = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(organisationSession, userSessionInfo);
			responseData = contactUsControllerImpl.uploadData(uploadRetryList);
			dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(uploadRetryList);
			updatedRetryListMap.add("ContactUsUploadRetryList", dataObjectsListToRetry);
			int retryCount = responseData.get("retryCount").getAsInt();
			retryObjectsCount += retryCount;
			contactUsControllerImpl.updateRetryCountMapForContactUsList(uploadRetryList, retryCountMap);
		}

		dataObject.addProperty("retryObjectsCount", retryObjectsCount);
		dataObject.add("retryListMap", updatedRetryListMap);
		dataObject.add("retryCountMap", retryCountMap);
		return dataObject;
	}
	public static JsonObject updateStatusMsg(HSSFWorkbook workbook, JsonObject retryListMap, Session masterSession, Session organisationSession) throws Exception
	{
		JsonArray uploadRetryList = null;
		JsonObject dataObject = new JsonObject();
		HSSFCellStyle successCellStyle = workbook.createCellStyle();
		successCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
		successCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont successFontColor = workbook.createFont();
		successFontColor.setColor(HSSFColor.GREEN.index);
		successCellStyle.setFont(successFontColor);
		HSSFCellStyle errorCellStyle = workbook.createCellStyle();
		errorCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
		errorCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont errorFontColor = workbook.createFont();
		errorFontColor.setColor(HSSFColor.RED.index);
		errorCellStyle.setFont(errorFontColor);
		int statusCellIndex = 30;
				
		if(retryListMap.has("OrganisationsUploadRetryList") && retryListMap.get("OrganisationsUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("OrganisationsUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("Organisations");
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(masterSession);
			organisationsControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		
		if(retryListMap.has("UserInfoUploadRetryList") && retryListMap.get("UserInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("UserInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("UserInfo");
			com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(masterSession);
			userInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		
		if(retryListMap.has("PrivilegeGroupUploadRetryList") && retryListMap.get("PrivilegeGroupUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("PrivilegeGroupUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("PrivilegeGroup");
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(masterSession);
			privilegeGroupControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
  
		
		  
		
				
		if(retryListMap.has("LoginSessionInfoUploadRetryList") && retryListMap.get("LoginSessionInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("LoginSessionInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("LoginSessionInfo");
			com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(masterSession);
			loginSessionInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}

				if(retryListMap.has("ConfigPropertiesUploadRetryList") && retryListMap.get("ConfigPropertiesUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("ConfigPropertiesUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("ConfigProperties");
			com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesControllerImpl = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(organisationSession);
			configPropertiesControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("TaskInfoUploadRetryList") && retryListMap.get("TaskInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("TaskInfo");
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(organisationSession);
			taskInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("TaskExecutionInfoUploadRetryList") && retryListMap.get("TaskExecutionInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskExecutionInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("TaskExecutionInfo");
			com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(organisationSession);
			taskExecutionInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("TaskLayoutParametersUploadRetryList") && retryListMap.get("TaskLayoutParametersUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskLayoutParametersUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("TaskLayoutParameters");
			com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(organisationSession);
			taskLayoutParametersControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("EmailAttachmentLayoutUploadRetryList") && retryListMap.get("EmailAttachmentLayoutUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("EmailAttachmentLayoutUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("EmailAttachmentLayout");
			com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(organisationSession);
			emailAttachmentLayoutControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("TaskScheduleInfoUploadRetryList") && retryListMap.get("TaskScheduleInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskScheduleInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("TaskScheduleInfo");
			com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(organisationSession);
			taskScheduleInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("TaskSentInfoUploadRetryList") && retryListMap.get("TaskSentInfoUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("TaskSentInfoUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("TaskSentInfo");
			com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(organisationSession);
			taskSentInfoControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("PatientUploadRetryList") && retryListMap.get("PatientUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("PatientUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("Patient");
			com.patientapp.controller.forms.impl.PatientControllerImpl patientControllerImpl = new com.patientapp.controller.forms.impl.PatientControllerImpl(organisationSession);
			patientControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("DoctorUploadRetryList") && retryListMap.get("DoctorUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("DoctorUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("Doctor");
			com.patientapp.controller.forms.impl.DoctorControllerImpl doctorControllerImpl = new com.patientapp.controller.forms.impl.DoctorControllerImpl(organisationSession);
			doctorControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("HospitalUploadRetryList") && retryListMap.get("HospitalUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("HospitalUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("Hospital");
			com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalControllerImpl = new com.patientapp.controller.forms.impl.HospitalControllerImpl(organisationSession);
			hospitalControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}
		if(retryListMap.has("ContactUsUploadRetryList") && retryListMap.get("ContactUsUploadRetryList").isJsonNull()==false)
		{
			uploadRetryList = retryListMap.get("ContactUsUploadRetryList").getAsJsonArray();		
			HSSFSheet sheet = workbook.getSheet("ContactUs");
			com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsControllerImpl = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(organisationSession);
			contactUsControllerImpl.updateStatusMsg(uploadRetryList, sheet, successCellStyle, errorCellStyle, statusCellIndex);
		}

		return dataObject;
	}
	public static JsonObject uploadData(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		JsonObject retryListMap = new JsonObject();
		JsonArray uploadRetryList = null;			 
		int retryObjectsCount = 0;
		int previousRetryObjectsCount = 0;
		JsonObject retryCountMap = new JsonObject();
		JsonObject previousRetryCountMap = new JsonObject();
		JsonObject updatedRetryListMap = new JsonObject();
		Session instantMasterSession = null; 
		Session instantOrganisationSession = null; 
		try
		{
			instantMasterSession = CommonUtil.getNewMasterSession(masterSession, userSessionInfo);
			instantOrganisationSession = instantMasterSession;
			
			SessionUtil sessionUtil = new SessionUtil();
			JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
			int areSourceDestinationSame = paramsInfo.get("areSourceDestinationSame").getAsInt();
			String inputFilesZip = "";
			int fileId = -1;
			String savedFileName = "";
			JsonObject requestParamsInfo = new JsonObject();
			requestParamsInfo.addProperty("areSourceDestinationSame", areSourceDestinationSame);
			if(paramsInfo.has("fileId"))
			{
				fileId = paramsInfo.get("fileId").getAsInt();
//				savedFileName = CommonUtil.fileIDAndNamesMap.get(fileId);
				savedFileName = CommonUtil.getFilePath("", fileId, organisationSession);
				requestParamsInfo.addProperty("fileId", fileId);
			}
			else if(paramsInfo.has("fileName"))
			{
				savedFileName = paramsInfo.get("fileName").getAsString();
			}
			if(paramsInfo.has("inputFilesZip"))
			{
				inputFilesZip = paramsInfo.get("inputFilesZip").getAsString();
			}
			String inputFilesPath = "";
			if(inputFilesZip.length() > 0)
			{
				String zipFilePath = CommonUtil.getFilePath(inputFilesZip, -1, organisationSession);
		        String extractedZipFilePath = FilenameUtils.removeExtension(zipFilePath);
		        int isExtracted = ZipExtraction.extractZipFile(extractedZipFilePath, zipFilePath);
		        if(isExtracted != 1)
		        {
		    		dataObject.addProperty("alert", "Data could not be uploaded as input files zip could not be extracted.");
		    		dataObject.addProperty("success", 0);
		    		return dataObject;
		        }
		        inputFilesPath = extractedZipFilePath;
			}
			requestParamsInfo.addProperty("inputFilesPath", inputFilesPath);
			FileInputStream file = new FileInputStream(new File(savedFileName));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			requestParamsInfo.addProperty("fileId", fileId);
						
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(instantMasterSession, userSessionInfo);
			dataObject = organisationsControllerImpl.uploadOrganisationsData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("OrganisationsUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			
			com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(instantMasterSession, userSessionInfo);
			dataObject = userInfoControllerImpl.uploadUserInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("UserInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(instantMasterSession, userSessionInfo);
			dataObject = privilegeGroupControllerImpl.uploadPrivilegeGroupData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("PrivilegeGroupUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
  
			
			  
			
						
			com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(instantMasterSession, userSessionInfo);
			dataObject = loginSessionInfoControllerImpl.uploadLoginSessionInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("LoginSessionInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}

						com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesControllerImpl = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = configPropertiesControllerImpl.uploadConfigPropertiesData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("ConfigPropertiesUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = taskInfoControllerImpl.uploadTaskInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("TaskInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = taskExecutionInfoControllerImpl.uploadTaskExecutionInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("TaskExecutionInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = taskLayoutParametersControllerImpl.uploadTaskLayoutParametersData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("TaskLayoutParametersUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = emailAttachmentLayoutControllerImpl.uploadEmailAttachmentLayoutData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("EmailAttachmentLayoutUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = taskScheduleInfoControllerImpl.uploadTaskScheduleInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("TaskScheduleInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoControllerImpl = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = taskSentInfoControllerImpl.uploadTaskSentInfoData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("TaskSentInfoUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.PatientControllerImpl patientControllerImpl = new com.patientapp.controller.forms.impl.PatientControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = patientControllerImpl.uploadPatientData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("PatientUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.DoctorControllerImpl doctorControllerImpl = new com.patientapp.controller.forms.impl.DoctorControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = doctorControllerImpl.uploadDoctorData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("DoctorUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.HospitalControllerImpl hospitalControllerImpl = new com.patientapp.controller.forms.impl.HospitalControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = hospitalControllerImpl.uploadHospitalData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("HospitalUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}
			com.patientapp.controller.forms.impl.ContactUsControllerImpl contactUsControllerImpl = new com.patientapp.controller.forms.impl.ContactUsControllerImpl(instantOrganisationSession, userSessionInfo);
			dataObject = contactUsControllerImpl.uploadContactUsData(workbook, requestParamsInfo);
			if (dataObject == null || !dataObject.has("success") || dataObject.get("success").getAsInt() != 1)
			{
				return dataObject;
			}
			if(dataObject.has("dataListToRetry") && dataObject.get("dataListToRetry").isJsonNull()==false)
			{
				uploadRetryList = dataObject.get("dataListToRetry").getAsJsonArray();
				retryListMap.add("ContactUsUploadRetryList", uploadRetryList);
				int retryCount = dataObject.get("retryCount").getAsInt();
				retryObjectsCount += retryCount;
			}

			do
			{
				JsonObject retryAttemptInfo = retryUploads(retryListMap, instantMasterSession, instantOrganisationSession, userSessionInfo);
				previousRetryObjectsCount = retryObjectsCount;
				previousRetryCountMap = retryCountMap;
				retryCountMap = retryAttemptInfo.get("retryCountMap").getAsJsonObject();
				retryObjectsCount = retryAttemptInfo.get("retryObjectsCount").getAsInt();
				updatedRetryListMap = retryAttemptInfo.get("retryListMap").getAsJsonObject();
				updateStatusMsg(workbook, retryListMap, masterSession, organisationSession);
				retryListMap = updatedRetryListMap;
			}
			while(areSomeRecordsUploaded(previousRetryCountMap, retryCountMap, instantMasterSession, instantOrganisationSession)==1);
			FileOutputStream out = new FileOutputStream(new File(savedFileName));
			workbook.write(out);
			out.close();
			dataObject.addProperty("alert", "Data uploaded successfully.");
			dataObject.addProperty("fileId", fileId);
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;
		}
		finally 
		{
			if(instantMasterSession != null)
			{
				instantMasterSession.close();
			}
			
		}
	}
	public void retryDataUpload(JsonObject retryListMap, Session masterSession, Session organisationSession, JsonObject userSessionInfo) throws Exception
	{
		JsonObject previousRetryCountMap = new JsonObject();
		JsonObject retryCountMap = new JsonObject();
		do
		{
			JsonObject retryAttemptInfo = retryUploads(retryListMap, masterSession, organisationSession, userSessionInfo);
			previousRetryCountMap = retryCountMap;
			retryCountMap = retryAttemptInfo.get("retryCountMap").getAsJsonObject();
			retryListMap = retryAttemptInfo.get("retryListMap").getAsJsonObject();
		}
		while(areSomeRecordsUploaded(previousRetryCountMap, retryCountMap, masterSession, organisationSession)==1);
	}
}
