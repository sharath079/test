package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "LoginSessionInfoLabelBean")*/
public class LoginSessionInfoLabel extends RulesBean{
	public String getloginSessionInfoIdFieldName() {return "loginSessionInfoId";} public String loginSessionInfoId_LABEL_ENGLISH = "Primary Key";
		public String getloginUserTypeFieldName() {return "loginUserType";} public String loginUserType_LABEL_ENGLISH = "Login User Type";
	public String getselfServiceUserTypeFieldName() {return "selfServiceUserType";} public String selfServiceUserType_LABEL_ENGLISH = "SelfServiceUserType";
	public String getsessionIdFieldName() {return "sessionId";} public String sessionId_LABEL_ENGLISH = "SessionId";
	public String getuserIdFieldName() {return "userId";} public String userId_LABEL_ENGLISH = "UserId";
	public String getloginTimeFieldName() {return "loginTime";} public String loginTime_LABEL_ENGLISH = "LoginTime";

	
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
		if (sBeanField.matches((getloginSessionInfoIdFieldName()))) sBeanFieldLabel = loginSessionInfoId_LABEL_ENGLISH;
				if (sBeanField.matches((getloginUserTypeFieldName()))) sBeanFieldLabel = loginUserType_LABEL_ENGLISH;
		if (sBeanField.matches((getselfServiceUserTypeFieldName()))) sBeanFieldLabel = selfServiceUserType_LABEL_ENGLISH;
		if (sBeanField.matches((getsessionIdFieldName()))) sBeanFieldLabel = sessionId_LABEL_ENGLISH;
		if (sBeanField.matches((getuserIdFieldName()))) sBeanFieldLabel = userId_LABEL_ENGLISH;
		if (sBeanField.matches((getloginTimeFieldName()))) sBeanFieldLabel = loginTime_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
