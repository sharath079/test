package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "TaskSentInfoLabelBean")*/
public class TaskSentInfoLabel extends RulesBean{
	public String gettaskSentInfoIdFieldName() {return "taskSentInfoId";} public String taskSentInfoId_LABEL_ENGLISH = "Primary Key";
		public String gettargetEntityIdFieldName() {return "targetEntityId";} public String targetEntityId_LABEL_ENGLISH = "target Entity Id";
	public String gettargetUserIdFieldName() {return "targetUserId";} public String targetUserId_LABEL_ENGLISH = "target User Id";
	public String getnotificationMediumFieldName() {return "notificationMedium";} public String notificationMedium_LABEL_ENGLISH = "NotificationMedium";
	public String getlayoutInfoTextFieldName() {return "layoutInfoText";} public String layoutInfoText_LABEL_ENGLISH = "LayoutInfoText";
	public String getnotificationSentTimeFieldName() {return "notificationSentTime";} public String notificationSentTime_LABEL_ENGLISH = "NotificationLastSentTime";

		public String gettaskInfoFieldName() {return "taskInfoId";} public String taskInfo_LABEL_ENGLISH = "Task";

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
		if (sBeanField.matches((gettaskSentInfoIdFieldName()))) sBeanFieldLabel = taskSentInfoId_LABEL_ENGLISH;
				if (sBeanField.matches((gettaskInfoFieldName()))) sBeanFieldLabel = taskInfo_LABEL_ENGLISH;
		if (sBeanField.matches((gettargetEntityIdFieldName()))) sBeanFieldLabel = targetEntityId_LABEL_ENGLISH;
		if (sBeanField.matches((gettargetUserIdFieldName()))) sBeanFieldLabel = targetUserId_LABEL_ENGLISH;
		if (sBeanField.matches((getnotificationMediumFieldName()))) sBeanFieldLabel = notificationMedium_LABEL_ENGLISH;
		if (sBeanField.matches((getlayoutInfoTextFieldName()))) sBeanFieldLabel = layoutInfoText_LABEL_ENGLISH;
		if (sBeanField.matches((getnotificationSentTimeFieldName()))) sBeanFieldLabel = notificationSentTime_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
