package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "EmailAttachmentLayoutLabelBean")*/
public class EmailAttachmentLayoutLabel extends RulesBean{
	public String getemailAttachmentLayoutIdFieldName() {return "emailAttachmentLayoutId";} public String emailAttachmentLayoutId_LABEL_ENGLISH = "Primary Key";
		public String getemailAttachmentLayoutNameFieldName() {return "emailAttachmentLayoutName";} public String emailAttachmentLayoutName_LABEL_ENGLISH = "Email Attachment Layout Name";
	public String getcommentsFieldName() {return "comments";} public String comments_LABEL_ENGLISH = "Comments";

	
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
		if (sBeanField.matches((getemailAttachmentLayoutIdFieldName()))) sBeanFieldLabel = emailAttachmentLayoutId_LABEL_ENGLISH;
				if (sBeanField.matches((getemailAttachmentLayoutNameFieldName()))) sBeanFieldLabel = emailAttachmentLayoutName_LABEL_ENGLISH;
		if (sBeanField.matches((getcommentsFieldName()))) sBeanFieldLabel = comments_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
