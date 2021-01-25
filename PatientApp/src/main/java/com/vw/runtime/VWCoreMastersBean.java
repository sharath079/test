package com.vw.runtime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
public class VWCoreMastersBean
{
	public List getGender()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Male");
		list.add("Female");
		list.add("Others");
		return list;
	}
	public List getCity()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Bangalore");
		list.add("Mysore");
		return list;
	}
	public List getResidentState()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Karnataka");
		list.add("Telangana");
		list.add("Seemandhra");
		list.add("Tamilnadu");
		return list;
	}
	public List getCountry()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("India");
		return list;
	}
	public List getEmployeeStatus()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Active");
		list.add("Ex-Employee");
		list.add("Notice Period");
		list.add("Suspended");
		return list;
	}
	public List getEmploymentType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Permanent");
		list.add("Contractor");
		return list;
	}
	public List getOrgRoles()
	{
		String VWROLE_CREATOR = "CREATOR";
		String VWROLE_APPROVER = "APPROVER";
		String VWROLE_VIEWER = "VIEWER";
		List<String> list = new ArrayList<String>();
		list.add(VWROLE_CREATOR);
		list.add(VWROLE_APPROVER);
		list.add(VWROLE_VIEWER);
		return list;
	}
	public List getWeekdays()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("Monday");
		list.add("Tuesday");
		list.add("Wednesday");
		list.add("Thursday");
		list.add("Friday");
		list.add("Saturday");
		list.add("Sunday");
		return list;
	}
	public List getCategoryTypes()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("SMS");
		list.add("Mail");
		return list;
	}
	public List getPriorityType()
	{
		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("High");
		list.add("Medium");
		list.add("Low");
		return list;
	}
	public List getBooleanString()
	{
		List<String> list = new ArrayList<String>();
		list.add("true");
		list.add("false");
		return list;
	}
}
