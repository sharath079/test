package com.patientapp.controller.reports.impl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.controller.reports.base.PrivilegeGroupControllerBase;
import java.sql.ResultSet;
import java.util.Map;
public class PrivilegeGroupControllerImpl  extends PrivilegeGroupControllerBase
{
	private static final Logger logger = Logger.getLogger(PrivilegeGroupControllerImpl.class);
    public PrivilegeGroupControllerImpl(JsonObject userSessionInfo, Session organisationSession)
    {
    	super(userSessionInfo, organisationSession);
    }
    public PrivilegeGroupControllerImpl()
    {
    	super();
    }
    public Map<String, String>  getUpdatedSearchParams(String formName, Map<String, String> paramsMap)
	{		
		return paramsMap;		
	}
	public  void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName)
	{
	}
	public  void processQueryResultList(JsonArray resultList, String tableName)
	{
	}
	public  void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause)
	{
	}
}
