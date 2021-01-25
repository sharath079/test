package com.patientapp.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "TaskExecutionInfoLabelBean")*/
public class TaskExecutionInfoLabel extends RulesBean{
	public String gettaskExecutionInfoIdFieldName() {return "taskExecutionInfoId";} public String taskExecutionInfoId_LABEL_ENGLISH = "Primary Key";
		public String gettaskTimeCalculationTypeFieldName() {return "taskTimeCalculationType";} public String taskTimeCalculationType_LABEL_ENGLISH = "Task Time Calculation Type";
	public String getfirstRunDateCalculationMethodFieldName() {return "firstRunDateCalculationMethod";} public String firstRunDateCalculationMethod_LABEL_ENGLISH = "First Run Date Calculation Method";
	public String getfirstRunDateGapInYearsFieldName() {return "firstRunDateGapInYears";} public String firstRunDateGapInYears_LABEL_ENGLISH = "First Run Date Gap In Years";
	public String getfirstRunDateGapInMonthsFieldName() {return "firstRunDateGapInMonths";} public String firstRunDateGapInMonths_LABEL_ENGLISH = "First Run Date Gap In Months";
	public String getfirstRunDateGapInDaysFieldName() {return "firstRunDateGapInDays";} public String firstRunDateGapInDays_LABEL_ENGLISH = "First Run Date Gap In Days";
	public String getfirstRunDateGapInHoursFieldName() {return "firstRunDateGapInHours";} public String firstRunDateGapInHours_LABEL_ENGLISH = "First Run Date Gap In Hours";
	public String getfirstRunDateGapInMinutesFieldName() {return "firstRunDateGapInMinutes";} public String firstRunDateGapInMinutes_LABEL_ENGLISH = "First Run Date Gap In Minutes";
	public String getfirstRunDateGapInSecondsFieldName() {return "firstRunDateGapInSeconds";} public String firstRunDateGapInSeconds_LABEL_ENGLISH = "First Run Date Gap In Seconds";

	
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
		if (sBeanField.matches((gettaskExecutionInfoIdFieldName()))) sBeanFieldLabel = taskExecutionInfoId_LABEL_ENGLISH;
				if (sBeanField.matches((gettaskTimeCalculationTypeFieldName()))) sBeanFieldLabel = taskTimeCalculationType_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateCalculationMethodFieldName()))) sBeanFieldLabel = firstRunDateCalculationMethod_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInYearsFieldName()))) sBeanFieldLabel = firstRunDateGapInYears_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInMonthsFieldName()))) sBeanFieldLabel = firstRunDateGapInMonths_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInDaysFieldName()))) sBeanFieldLabel = firstRunDateGapInDays_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInHoursFieldName()))) sBeanFieldLabel = firstRunDateGapInHours_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInMinutesFieldName()))) sBeanFieldLabel = firstRunDateGapInMinutes_LABEL_ENGLISH;
		if (sBeanField.matches((getfirstRunDateGapInSecondsFieldName()))) sBeanFieldLabel = firstRunDateGapInSeconds_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
