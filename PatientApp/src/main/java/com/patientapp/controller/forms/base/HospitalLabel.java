package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "HospitalLabelBean")*/
public class HospitalLabel extends RulesBean{
	public String gethospitalIdFieldName() {return "hospitalId";} public String hospitalId_LABEL_ENGLISH = "Primary Key";
		public String gethospNameFieldName() {return "hospName";} public String hospName_LABEL_ENGLISH = "HospName";
	public String gethospAddressFieldName() {return "hospAddress";} public String hospAddress_LABEL_ENGLISH = "HospAddress";

	
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
		if (sBeanField.matches((gethospitalIdFieldName()))) sBeanFieldLabel = hospitalId_LABEL_ENGLISH;
				if (sBeanField.matches((gethospNameFieldName()))) sBeanFieldLabel = hospName_LABEL_ENGLISH;
		if (sBeanField.matches((gethospAddressFieldName()))) sBeanFieldLabel = hospAddress_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
