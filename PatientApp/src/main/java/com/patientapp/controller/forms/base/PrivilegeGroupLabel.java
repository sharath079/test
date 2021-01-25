package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "PrivilegeGroupLabelBean")*/
public class PrivilegeGroupLabel extends RulesBean{
	public String getprivilegeGroupIdFieldName() {return "privilegeGroupId";} public String privilegeGroupId_LABEL_ENGLISH = "Primary Key";
		public String getprivilegeGroupNameFieldName() {return "privilegeGroupName";} public String privilegeGroupName_LABEL_ENGLISH = "Privilege Group Name";
	public String getprivilegeCodeFieldName() {return "privilegeCode";} public String privilegeCode_LABEL_ENGLISH = "Privilege Code";
	public String getapplicableUserTypeFieldName() {return "applicableUserType";} public String applicableUserType_LABEL_ENGLISH = "Applicable User Type";
	public String getselfServiceUserFieldName() {return "selfServiceUser";} public String selfServiceUser_LABEL_ENGLISH = "Self Service User";
	public String getprivilegeGroupDescriptionFieldName() {return "privilegeGroupDescription";} public String privilegeGroupDescription_LABEL_ENGLISH = "Privilege Group Description";

	
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
		if (sBeanField.matches((getprivilegeGroupIdFieldName()))) sBeanFieldLabel = privilegeGroupId_LABEL_ENGLISH;
				if (sBeanField.matches((getprivilegeGroupNameFieldName()))) sBeanFieldLabel = privilegeGroupName_LABEL_ENGLISH;
		if (sBeanField.matches((getprivilegeCodeFieldName()))) sBeanFieldLabel = privilegeCode_LABEL_ENGLISH;
		if (sBeanField.matches((getapplicableUserTypeFieldName()))) sBeanFieldLabel = applicableUserType_LABEL_ENGLISH;
		if (sBeanField.matches((getselfServiceUserFieldName()))) sBeanFieldLabel = selfServiceUser_LABEL_ENGLISH;
		if (sBeanField.matches((getprivilegeGroupDescriptionFieldName()))) sBeanFieldLabel = privilegeGroupDescription_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
