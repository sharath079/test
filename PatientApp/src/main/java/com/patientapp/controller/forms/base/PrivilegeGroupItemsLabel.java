package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "PrivilegeGroupItemsLabelBean")*/
public class PrivilegeGroupItemsLabel extends RulesBean{
	public String getprivilegeGroupItemsIdFieldName() {return "privilegeGroupItemsId";} public String privilegeGroupItemsId_LABEL_ENGLISH = "Primary Key";
		public String getprivilegeActionTypeFieldName() {return "privilegeActionType";} public String privilegeActionType_LABEL_ENGLISH = "Action Type";
	public String getprivilegeObjectTypeFieldName() {return "privilegeObjectType";} public String privilegeObjectType_LABEL_ENGLISH = "Object Type";
	public String getprivilegeObjectNameFieldName() {return "privilegeObjectName";} public String privilegeObjectName_LABEL_ENGLISH = "Object Name";

	
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
		if (sBeanField.matches((getprivilegeGroupItemsIdFieldName()))) sBeanFieldLabel = privilegeGroupItemsId_LABEL_ENGLISH;
				if (sBeanField.matches((getprivilegeActionTypeFieldName()))) sBeanFieldLabel = privilegeActionType_LABEL_ENGLISH;
		if (sBeanField.matches((getprivilegeObjectTypeFieldName()))) sBeanFieldLabel = privilegeObjectType_LABEL_ENGLISH;
		if (sBeanField.matches((getprivilegeObjectNameFieldName()))) sBeanFieldLabel = privilegeObjectName_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
