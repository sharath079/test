package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "EmployeeRolesLabelBean")*/
public class EmployeeRolesLabel extends RulesBean{
	public String getemployeeRolesIdFieldName() {return "employeeRolesId";} public String employeeRolesId_LABEL_ENGLISH = "Primary Key";
		public String getdescriptionFieldName() {return "description";} public String description_LABEL_ENGLISH = "Description";

		public String getprivilegeGroupFieldName() {return "privilegeGroupId";} public String privilegeGroup_LABEL_ENGLISH = "Privilege Group";

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
		if (sBeanField.matches((getemployeeRolesIdFieldName()))) sBeanFieldLabel = employeeRolesId_LABEL_ENGLISH;
				if (sBeanField.matches((getprivilegeGroupFieldName()))) sBeanFieldLabel = privilegeGroup_LABEL_ENGLISH;
		if (sBeanField.matches((getdescriptionFieldName()))) sBeanFieldLabel = description_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
