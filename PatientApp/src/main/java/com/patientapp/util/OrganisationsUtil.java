package com.patientapp.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import org.hibernate.Session;
import com.patientapp.request.service.ServiceAPIUtil;
import com.google.gson.JsonObject;
import com.patientapp.bean.Organisations;
import com.patientapp.controller.forms.impl.OrganisationsControllerImpl;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.SettingsUtil;
public class OrganisationsUtil
{
	public void initializeStoreDB(Session session, Organisations organisations, OrganisationsControllerImpl implObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		String isDBInitialized = "NO";
		if ("YES".equalsIgnoreCase(isDBInitialized))
		{
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Organisatin DB could not be created.");
			CommonUtil.writeToLog("Organisatin DB could not be created.");
		}
		BufferedReader br = null;
		String DMLFileName = null;
		String customScriptFileName = null;
		String databaseName = organisations.getDatabaseName();
		Connection connection = null;
		try
		{
			DMLFileName = "/dml/MySQL/Schema/MYSQLDMLOrganisation.sql";			
			customScriptFileName = "/dml/MySQL/Schema/CustomScript.sql";	
			String dbUser = CommonUtil.getPropertyValue("DB_USER_NAME");
			String dbPass = CommonUtil.getPropertyValue("DB_PASSWORD");
			String sqlCommand = "CREATE DATABASE " + databaseName + "  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;";
			CommonUtil.runSqlCommand(sqlCommand);
			CommonUtil.runSqlScript(DMLFileName.replace('\\', '/'), databaseName);
			CommonUtil.runSqlScript(customScriptFileName.replace('\\', '/'), databaseName);
			connection = CommonUtil.getConnection();
			Session organisationSession = SessionFactoryBuilder.getDBSession(databaseName);
			int setOfBooksInfoId = -1;
			
			System.out.println("Company DB created");
		}
		catch (Exception e)
		{
			CommonUtil.writeToLog(this, e);
			dataObject.addProperty("alert", "Organisatin DB could not be created.");
		}
		finally
		{
			if (connection != null)
			{
				connection.close();
			}
		}
	}
	public Integer addHeadOfficeBranchInfo(Session session, Integer setOfBooksInfoId, OrganisationsControllerImpl implObj) throws Exception
	{
		try
		{
			JsonObject bankBranchInfoDataObject = new JsonObject();
			bankBranchInfoDataObject.addProperty("setOfBooksInfoId", setOfBooksInfoId);
			bankBranchInfoDataObject.addProperty("branchName", "HEAD OFFICE");
			bankBranchInfoDataObject.addProperty("branchCode", "HEAD_OFFICE");
			bankBranchInfoDataObject.addProperty("isSubBranch", "NO");
			bankBranchInfoDataObject.addProperty("parentBranchId", -1);
			bankBranchInfoDataObject.addProperty("costCenterId", -1);
			bankBranchInfoDataObject.addProperty("locationId", -1);
			bankBranchInfoDataObject.addProperty("addressLine1", "");
			bankBranchInfoDataObject.addProperty("addressLine2", "");
			bankBranchInfoDataObject.addProperty("countryId", -1);
			bankBranchInfoDataObject.addProperty("stateId", -1);
			bankBranchInfoDataObject.addProperty("cityId", -1);
			bankBranchInfoDataObject.addProperty("postalCode", "");
			bankBranchInfoDataObject.addProperty("phoneNumber", "");
			bankBranchInfoDataObject.addProperty("branchSupervisorName", "");
			bankBranchInfoDataObject.addProperty("fax", "");
			bankBranchInfoDataObject.addProperty("additionalInfo", "");
			bankBranchInfoDataObject.addProperty("isheadoffice", "");
			bankBranchInfoDataObject.addProperty("warehouseId", -1);
			
			return 1;
		}
		catch (Exception e)
		{
			CommonUtil.writeToLog(this, e);
			implObj.setTransactionFailureMessage("Head Office Branch could not be added");
			return 0;
		}
	}
}
