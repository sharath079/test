package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "OrganisationsLabelBean")*/
public class OrganisationsLabel extends RulesBean{
	public String getorganisationsIdFieldName() {return "organisationsId";} public String organisationsId_LABEL_ENGLISH = "Primary Key";
		public String getorganisationNameFieldName() {return "organisationName";} public String organisationName_LABEL_ENGLISH = "Company Name";
	public String getaddressLine1FieldName() {return "addressLine1";} public String addressLine1_LABEL_ENGLISH = "Address Line1";
	public String getaddressLine2FieldName() {return "addressLine2";} public String addressLine2_LABEL_ENGLISH = "Address Line2";
	public String getcityFieldName() {return "city";} public String city_LABEL_ENGLISH = "City";
	public String getresidentStateFieldName() {return "residentState";} public String residentState_LABEL_ENGLISH = "Resident State";
	public String getpinCodeFieldName() {return "pinCode";} public String pinCode_LABEL_ENGLISH = "Pin Code";
	public String getdatabaseNameFieldName() {return "databaseName";} public String databaseName_LABEL_ENGLISH = "Database Name";
	public String getcountryFieldName() {return "country";} public String country_LABEL_ENGLISH = "Country";

	
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
		if (sBeanField.matches((getorganisationsIdFieldName()))) sBeanFieldLabel = organisationsId_LABEL_ENGLISH;
				if (sBeanField.matches((getorganisationNameFieldName()))) sBeanFieldLabel = organisationName_LABEL_ENGLISH;
		if (sBeanField.matches((getaddressLine1FieldName()))) sBeanFieldLabel = addressLine1_LABEL_ENGLISH;
		if (sBeanField.matches((getaddressLine2FieldName()))) sBeanFieldLabel = addressLine2_LABEL_ENGLISH;
		if (sBeanField.matches((getcityFieldName()))) sBeanFieldLabel = city_LABEL_ENGLISH;
		if (sBeanField.matches((getresidentStateFieldName()))) sBeanFieldLabel = residentState_LABEL_ENGLISH;
		if (sBeanField.matches((getpinCodeFieldName()))) sBeanFieldLabel = pinCode_LABEL_ENGLISH;
		if (sBeanField.matches((getdatabaseNameFieldName()))) sBeanFieldLabel = databaseName_LABEL_ENGLISH;
		if (sBeanField.matches((getcountryFieldName()))) sBeanFieldLabel = country_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
