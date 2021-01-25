package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "PatientLabelBean")*/
public class PatientLabel extends RulesBean{
	public String getpatientIdFieldName() {return "patientId";} public String patientId_LABEL_ENGLISH = "Primary Key";
		public String getpatientNameFieldName() {return "patientName";} public String patientName_LABEL_ENGLISH = "PatientName";
	public String getpatientGenderFieldName() {return "patientGender";} public String patientGender_LABEL_ENGLISH = "PatientGender";
	public String getselectDoctorFieldName() {return "selectDoctor";} public String selectDoctor_LABEL_ENGLISH = "SelectDoctor";


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
		if (sBeanField.matches((getpatientIdFieldName()))) sBeanFieldLabel = patientId_LABEL_ENGLISH;
				if (sBeanField.matches((getpatientNameFieldName()))) sBeanFieldLabel = patientName_LABEL_ENGLISH;
		if (sBeanField.matches((getpatientGenderFieldName()))) sBeanFieldLabel = patientGender_LABEL_ENGLISH;
		if (sBeanField.matches((getselectDoctorFieldName()))) sBeanFieldLabel = selectDoctor_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
