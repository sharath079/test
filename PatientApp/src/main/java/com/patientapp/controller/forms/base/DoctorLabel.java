package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "DoctorLabelBean")*/
public class DoctorLabel extends RulesBean{
	public String getdoctorIdFieldName() {return "doctorId";} public String doctorId_LABEL_ENGLISH = "Primary Key";
		public String getdoctorNameFieldName() {return "doctorName";} public String doctorName_LABEL_ENGLISH = "DoctorName";
	public String gethospitalNameFieldName() {return "hospitalName";} public String hospitalName_LABEL_ENGLISH = "HospitalName";

	
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
		if (sBeanField.matches((getdoctorIdFieldName()))) sBeanFieldLabel = doctorId_LABEL_ENGLISH;
				if (sBeanField.matches((getdoctorNameFieldName()))) sBeanFieldLabel = doctorName_LABEL_ENGLISH;
		if (sBeanField.matches((gethospitalNameFieldName()))) sBeanFieldLabel = hospitalName_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
