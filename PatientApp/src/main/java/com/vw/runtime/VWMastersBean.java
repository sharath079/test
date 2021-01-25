package com.vw.runtime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class VWMastersBean extends VWCoreMastersBean
{

	public List<String> getUserType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Admin");
		
		list.add("SelfService");
		return list;
	}

	public List<String> getFirstRunDateCalculationMethod()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("FORWARD");
		
		list.add("BACKWARD");
		return list;
	}

	public List<String> getCountry()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("US");
		
		list.add("UK");
		
		list.add("Ind");
		return list;
	}

	public List<String> getPrivilegeType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Create");
		
		list.add("Search");
		
		list.add("View");
		
		list.add("Update");
		
		list.add("Delete");
		
		list.add("Verify");
		
		list.add("Approve");
		return list;
	}

	public List<String> getEmployeeStatus()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("GradeA");
		
		list.add("GradeB");
		return list;
	}

	public List<String> getEmploymentType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Permanent");
		
		list.add("Temp");
		return list;
	}

	public List<String> getNotificationMedium()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("USER_ID");
		
		list.add("NOTIFICATION_START_DATE");
		
		list.add("NOTIFICATION_END_DATE");
		return list;
	}

	public List<String> getLayoutParameterValueType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("USER_ID");
		
		list.add("ENTITY_ID");
		
		list.add("NOTIFICATION_START_DATE");
		
		list.add("NOTIFICATION_END_DATE");
		return list;
	}

	public List<String> getYesNoOption()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Yes");
		
		list.add("No");
		return list;
	}

	public List<String> getBooleanString()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("true");
		
		list.add("false");
		return list;
	}

	public List<String> getGender()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Male");
		
		list.add("Female");
		return list;
	}

	public List<String> getDurationUnitType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("YEAR");
		
		list.add("MONTH");
		
		list.add("WEEK");
		
		list.add("DAY");
		
		list.add("HOUR");
		
		list.add("MINUTE");
		
		list.add("SECOND");
		return list;
	}

	public List<String> getFirstRunType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Immediate");
		
		list.add("SpecifyDate");
		
		list.add("Manual");
		
		list.add("UseRelativeDate");
		return list;
	}

	public List<String> getCity()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Hyd");
		
		list.add("Mmmbai");
		return list;
	}

	public List<String> getTaskType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Notification");
		
		list.add("APICall");
		return list;
	}

	public List<String> getTaskTimeCalculationType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		
		list.add("");
		
		list.add("Immediate");
		
		list.add("UseRelativeDate");
		return list;
	}

	
}
