package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "ContactUsLabelBean")*/
public class ContactUsLabel extends RulesBean{
	public String getcontactUsIdFieldName() {return "contactId";} public String contactId_LABEL_ENGLISH = "Primary Key";
		public String getfullNameFieldName() {return "fullName";} public String fullName_LABEL_ENGLISH = "FullName";
	public String getemailIdFieldName() {return "emailId";} public String emailId_LABEL_ENGLISH = "Email Id";
	public String getcontactNoFieldName() {return "contactNo";} public String contactNo_LABEL_ENGLISH = "Contact No";

	
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
		if (sBeanField.matches((getcontactUsIdFieldName()))) sBeanFieldLabel = contactId_LABEL_ENGLISH;
				if (sBeanField.matches((getfullNameFieldName()))) sBeanFieldLabel = fullName_LABEL_ENGLISH;
		if (sBeanField.matches((getemailIdFieldName()))) sBeanFieldLabel = emailId_LABEL_ENGLISH;
		if (sBeanField.matches((getcontactNoFieldName()))) sBeanFieldLabel = contactNo_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
