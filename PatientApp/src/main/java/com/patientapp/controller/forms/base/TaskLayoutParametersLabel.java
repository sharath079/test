package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "TaskLayoutParametersLabelBean")*/
public class TaskLayoutParametersLabel extends RulesBean{
	public String gettaskLayoutParametersIdFieldName() {return "taskLayoutParametersId";} public String taskLayoutParametersId_LABEL_ENGLISH = "Primary Key";
		public String getparameterNameFieldName() {return "parameterName";} public String parameterName_LABEL_ENGLISH = "Parameter Name";
	public String getparameterValueTypeFieldName() {return "parameterValueType";} public String parameterValueType_LABEL_ENGLISH = "Parameter Value Type";
	public String getparameterValueFieldName() {return "parameterValue";} public String parameterValue_LABEL_ENGLISH = "Parameter Value";

	
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
		if (sBeanField.matches((gettaskLayoutParametersIdFieldName()))) sBeanFieldLabel = taskLayoutParametersId_LABEL_ENGLISH;
				if (sBeanField.matches((getparameterNameFieldName()))) sBeanFieldLabel = parameterName_LABEL_ENGLISH;
		if (sBeanField.matches((getparameterValueTypeFieldName()))) sBeanFieldLabel = parameterValueType_LABEL_ENGLISH;
		if (sBeanField.matches((getparameterValueFieldName()))) sBeanFieldLabel = parameterValue_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
