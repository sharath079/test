package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "UserInfoLabelBean")*/
public class UserInfoLabel extends RulesBean{
	public String getuserInfoIdFieldName() {return "userInfoId";} public String userInfoId_LABEL_ENGLISH = "Primary Key";
		public String getuserFirstNameFieldName() {return "userFirstName";} public String userFirstName_LABEL_ENGLISH = "First Name";
	public String getuserLastNameFieldName() {return "userLastName";} public String userLastName_LABEL_ENGLISH = "Last Name";
	public String getloginIdFieldName() {return "loginId";} public String loginId_LABEL_ENGLISH = "Login Id";
	public String getloginEmailIdFieldName() {return "loginEmailId";} public String loginEmailId_LABEL_ENGLISH = "Email ID";
	public String getcontactNoFieldName() {return "contactNo";} public String contactNo_LABEL_ENGLISH = "Contact No";
	public String getloginPasswordFieldName() {return "loginPassword";} public String loginPassword_LABEL_ENGLISH = "Login Password";
	public String getresetTokenFieldName() {return "resetToken";} public String resetToken_LABEL_ENGLISH = "Reset Token";

		public String getorganisationsUserOrgFieldName() {return "organisationsUserOrgId";} public String organisationsUserOrg_LABEL_ENGLISH = "User Organisation";

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
		if (sBeanField.matches((getuserInfoIdFieldName()))) sBeanFieldLabel = userInfoId_LABEL_ENGLISH;
				if (sBeanField.matches((getuserFirstNameFieldName()))) sBeanFieldLabel = userFirstName_LABEL_ENGLISH;
		if (sBeanField.matches((getuserLastNameFieldName()))) sBeanFieldLabel = userLastName_LABEL_ENGLISH;
		if (sBeanField.matches((getorganisationsUserOrgFieldName()))) sBeanFieldLabel = organisationsUserOrg_LABEL_ENGLISH;
		if (sBeanField.matches((getloginIdFieldName()))) sBeanFieldLabel = loginId_LABEL_ENGLISH;
		if (sBeanField.matches((getloginEmailIdFieldName()))) sBeanFieldLabel = loginEmailId_LABEL_ENGLISH;
		if (sBeanField.matches((getcontactNoFieldName()))) sBeanFieldLabel = contactNo_LABEL_ENGLISH;
		if (sBeanField.matches((getloginPasswordFieldName()))) sBeanFieldLabel = loginPassword_LABEL_ENGLISH;
		if (sBeanField.matches((getresetTokenFieldName()))) sBeanFieldLabel = resetToken_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
