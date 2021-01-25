package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "TaskInfoLabelBean")*/
public class TaskInfoLabel extends RulesBean{
	public String gettaskInfoIdFieldName() {return "taskInfoId";} public String taskInfoId_LABEL_ENGLISH = "Primary Key";
		public String gettaskNameFieldName() {return "taskName";} public String taskName_LABEL_ENGLISH = "Task Name";
	public String gettaskDescriptionFieldName() {return "taskDescription";} public String taskDescription_LABEL_ENGLISH = "Task Description";
	public String gettaskTypeFieldName() {return "taskType";} public String taskType_LABEL_ENGLISH = "Task Type";
	public String getapiNameFieldName() {return "apiName";} public String apiName_LABEL_ENGLISH = "API Name";
	public String gettargetEntityQueryFieldName() {return "targetEntityQuery";} public String targetEntityQuery_LABEL_ENGLISH = "Target Entity Query";
	public String gettargetUserIdColumnAliasFieldName() {return "targetUserIdColumnAlias";} public String targetUserIdColumnAlias_LABEL_ENGLISH = "Target User Id Column Alias";
	public String gettargetEntityIdColumnAliasFieldName() {return "targetEntityIdColumnAlias";} public String targetEntityIdColumnAlias_LABEL_ENGLISH = "Target Entity Id Column Alias";
	public String getenableInAppNotificationFieldName() {return "enableInAppNotification";} public String enableInAppNotification_LABEL_ENGLISH = "Enable In App Notification";
	public String getinAppNotificationLayoutFieldName() {return "inAppNotificationLayout";} public String inAppNotificationLayout_LABEL_ENGLISH = "InApp Notification Layout";
	public String getenableEmailNotificationFieldName() {return "enableEmailNotification";} public String enableEmailNotification_LABEL_ENGLISH = "Enable Email Notification";
	public String getemailColumnAliasFieldName() {return "emailColumnAlias";} public String emailColumnAlias_LABEL_ENGLISH = "Email Column Alias";
	public String getemailNotificationLayoutFieldName() {return "emailNotificationLayout";} public String emailNotificationLayout_LABEL_ENGLISH = "Email Notifaction Layout";
	public String getemailSubjectFieldName() {return "emailSubject";} public String emailSubject_LABEL_ENGLISH = "Email Subject";
	public String getenableSmsNotificationFieldName() {return "enableSmsNotification";} public String enableSmsNotification_LABEL_ENGLISH = "EnableSms Notification ";
	public String getsmsColumnAliasFieldName() {return "smsColumnAlias";} public String smsColumnAlias_LABEL_ENGLISH = "Sms Column Alias";
	public String getsmsNotificationLayoutFieldName() {return "smsNotificationLayout";} public String smsNotificationLayout_LABEL_ENGLISH = "Sms Notifaction Layout";
	public String getsMSTextFieldName() {return "sMSText";} public String sMSText_LABEL_ENGLISH = "SMS Text";
	public String getisActiveFieldName() {return "isActive";} public String isActive_LABEL_ENGLISH = "Is Active";
	public String gettaskStartDateFieldName() {return "taskStartDate";} public String taskStartDate_LABEL_ENGLISH = "Task Start Date";
	public String gettaskFrequencyFieldName() {return "taskFrequency";} public String taskFrequency_LABEL_ENGLISH = "Task Frequency";
	public String gettaskFrequencyUnitFieldName() {return "taskFrequencyUnit";} public String taskFrequencyUnit_LABEL_ENGLISH = "Task Frequency Unit";
	public String getisRecurringFieldName() {return "isRecurring";} public String isRecurring_LABEL_ENGLISH = "Is Recurring";
	public String getfirstRunTypeFieldName() {return "firstRunType";} public String firstRunType_LABEL_ENGLISH = "First Run Type";
	public String getdateColumnAliasFieldName() {return "dateColumnAlias";} public String dateColumnAlias_LABEL_ENGLISH = "Date Column Alias";
	public String getfirstRunDateCalculationMethodFieldName() {return "firstRunDateCalculationMethod";} public String firstRunDateCalculationMethod_LABEL_ENGLISH = "First Run Date Calculation Method";
	public String getfirstRunDateGapInYearsFieldName() {return "firstRunDateGapInYears";} public String firstRunDateGapInYears_LABEL_ENGLISH = "First Run Date Gap In Years";
	public String getfirstRunDateGapInMonthsFieldName() {return "firstRunDateGapInMonths";} public String firstRunDateGapInMonths_LABEL_ENGLISH = "First Run Date Gap In Months";
	public String getfirstRunDateGapInDaysFieldName() {return "firstRunDateGapInDays";} public String firstRunDateGapInDays_LABEL_ENGLISH = "First Run Date Gap In Days";
	public String getfirstRunDateGapInHoursFieldName() {return "firstRunDateGapInHours";} public String firstRunDateGapInHours_LABEL_ENGLISH = "First Run Date Gap In Hours";
	public String getfirstRunDateGapInMinutesFieldName() {return "firstRunDateGapInMinutes";} public String firstRunDateGapInMinutes_LABEL_ENGLISH = "First Run Date Gap In Minutes";
	public String getfirstRunDateGapInSecondsFieldName() {return "firstRunDateGapInSeconds";} public String firstRunDateGapInSeconds_LABEL_ENGLISH = "First Run Date Gap In Seconds";

	
	public String getvwLastModifiedDateFieldName() {return "vwLastModifiedDate";} public String vwLastModifiedDate_LABEL_ENGLISH = "Update Date";
	public String getvwLastModifiedTimeFieldName() {return "vwLastModifiedTime";} public String vwLastModifiedTime_LABEL_ENGLISH = "Update Time";
	public String getvwLastActionFieldName() {return "vwLastAction";} public String vwLastAction_LABEL_ENGLISH = "Last Action";
	public String getvwModifiedByFieldName() {return "vwModifiedBy";} public String vwModifiedBy_LABEL_ENGLISH = "Modified By";
	public String getvwTxnRemarksFieldName() {return "vwTxnRemarks";} public String vwTxnRemarks_LABEL_ENGLISH = "Remarks";
	public String getvwTxnStatusFieldName() {return "vwTxnStatus";} public String vwTxnStatus_LABEL_ENGLISH = "Status";
	public String LANG_ENGLISH = "ENGLISH";
	public String getLabel(String sBeanField)
	{
		return getLabel(sBeanField, LANG_ENGLISH);
	}
	public String getLabel(String sBeanField, String sLang)
	{
		String sBeanFieldLabel = "<<No Label Found !!!>>";
		if (sBeanField.matches((gettaskInfoIdFieldName()))) sBeanFieldLabel = taskInfoId_LABEL_ENGLISH;
				if (sBeanField.matches((gettaskNameFieldName()))) sBeanFieldLabel = taskName_LABEL_ENGLISH;
		if (sBeanField.matches((gettaskDescriptionFieldName()))) sBeanFieldLabel = taskDescription_LABEL_ENGLISH;
		if (sBeanField.matches((gettaskTypeFieldName()))) sBeanFieldLabel = taskType_LABEL_ENGLISH;
		if (sBeanField.matches((getapiNameFieldName()))) sBeanFieldLabel = apiName_LABEL_ENGLISH;
		if (sBeanField.matches((gettargetEntityQueryFieldName()))) sBeanFieldLabel = targetEntityQuery_LABEL_ENGLISH;
		if (sBeanField.matches((gettargetUserIdColumnAliasFieldName()))) sBeanFieldLabel = targetUserIdColumnAlias_LABEL_ENGLISH;
		if (sBeanField.matches((gettargetEntityIdColumnAliasFieldName()))) sBeanFieldLabel = targetEntityIdColumnAlias_LABEL_ENGLISH;
		if (sBeanField.matches((getenableInAppNotificationFieldName()))) sBeanFieldLabel = enableInAppNotification_LABEL_ENGLISH;
		if (sBeanField.matches((getinAppNotificationLayoutFieldName()))) sBeanFieldLabel = inAppNotificationLayout_LABEL_ENGLISH;
		if (sBeanField.matches((getenableEmailNotificationFieldName()))) sBeanFieldLabel = enableEmailNotification_LABEL_ENGLISH;
		if (sBeanField.matches((getemailColumnAliasFieldName()))) sBeanFieldLabel = emailColumnAlias_LABEL_ENGLISH;
		if (sBeanField.matches((getemailNotificationLayoutFieldName()))) sBeanFieldLabel = emailNotificationLayout_LABEL_ENGLISH;
		if (sBeanField.matches((getemailSubjectFieldName()))) sBeanFieldLabel = emailSubject_LABEL_ENGLISH;
		if (sBeanField.matches((getenableSmsNotificationFieldName()))) sBeanFieldLabel = enableSmsNotification_LABEL_ENGLISH;
		if (sBeanField.matches((getsmsColumnAliasFieldName()))) sBeanFieldLabel = smsColumnAlias_LABEL_ENGLISH;
		if (sBeanField.matches((getsmsNotificationLayoutFieldName()))) sBeanFieldLabel = smsNotificationLayout_LABEL_ENGLISH;
		if (sBeanField.matches((getsMSTextFieldName()))) sBeanFieldLabel = sMSText_LABEL_ENGLISH;
		if (sBeanField.matches((getisActiveFieldName()))) sBeanFieldLabel = isActive_LABEL_ENGLISH;
		if (sBeanField.matches((gettaskStartDateFieldName()))) sBeanFieldLabel = taskStartDate_LABEL_ENGLISH;
		if (sBeanField.matches((gettaskFrequencyFieldName()))) sBeanFieldLabel = taskFrequency_LABEL_ENGLISH;
		if (sBeanField.matches((gettaskFrequencyUnitFieldName()))) sBeanFieldLabel = taskFrequencyUnit_LABEL_ENGLISH;
		if (sBeanField.matches((getisRecurringFieldName()))) sBeanFieldLabel = isRecurring_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunTypeFieldName()))) sBeanFieldLabel = firstRunType_LABEL_ENGLISH;
		if (sBeanField.matches((getdateColumnAliasFieldName()))) sBeanFieldLabel = dateColumnAlias_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateCalculationMethodFieldName()))) sBeanFieldLabel = firstRunDateCalculationMethod_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInYearsFieldName()))) sBeanFieldLabel = firstRunDateGapInYears_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInMonthsFieldName()))) sBeanFieldLabel = firstRunDateGapInMonths_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInDaysFieldName()))) sBeanFieldLabel = firstRunDateGapInDays_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInHoursFieldName()))) sBeanFieldLabel = firstRunDateGapInHours_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInMinutesFieldName()))) sBeanFieldLabel = firstRunDateGapInMinutes_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInSecondsFieldName()))) sBeanFieldLabel = firstRunDateGapInSeconds_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
