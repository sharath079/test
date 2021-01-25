package com;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patientapp.request.service.ServiceAPIUtil;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.SettingsUtil;
import com.patientapp.util.Tasks;
import com.patientapp.request.util.SessionUtil;
import com.patientapp.request.util.RequestUtil;
import com.patientapp.util.QueriesDataLoader;
import com.google.gson.JsonObject;
import com.patientapp.util.SessionFactoryBuilder;
@SpringBootApplication
@RestController
public class PatientAppApplication extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		System.out.println("Change mysql jar mysql-connector-jar(8.0.21) file depending on version you are using on local system.......");
		String filesTempPath = SettingsUtil.getProjectFilesPath();
		try
		{
			if (!(new File(filesTempPath).exists()))
			{
				FileUtils.forceMkdir(new File(filesTempPath));
			}
		}
		catch (Exception e)
		{
			CommonUtil.getStackTrace(e);
			return;
		}
		String projectPath = SettingsUtil.getProjectPath();
		System.out.println("Project path : " + projectPath);
		int masterDBInitialized = initializeDatabase();
		if(masterDBInitialized == 1)
		{
			SessionUtil.initialiseUserTypePrivilegesList();
			Tasks.startScheduler();
			SpringApplication.run(PatientAppApplication.class, args);
			
			
			
			
			
			
		}
		else
		{
	        System.out.println("Application could not ne started as Master Database could not be initialized.");
		}
	}
    public static int initializeDatabase()
    {
        try
        {
            Connection connection = CommonUtil.getConnection();
            connection.close();
        }
        catch (Exception e)
        {
            return initializeMasterDB();
        }
        System.out.println("Database already exists. Not initializing again.");
        return 1;
    }
    public static int initializeMasterDB()
    {
		BufferedReader br = null;
		String DMLFileName = null;
		String customScriptFileName = null;
		String databaseName = CommonUtil.getMasterDBName();
		try
		{
			String dbUser = CommonUtil.getPropertyValue("DB_USER_NAME");
			String dbPass = CommonUtil.getPropertyValue("DB_PASSWORD");
			DMLFileName = "/dml/MySQL/Schema/MYSQLDML.sql";
			customScriptFileName = "/dml/MySQL/Schema/CustomScript.sql";
			String sqlCommand = "CREATE DATABASE " + databaseName + "  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;";
			CommonUtil.runSqlCommand(sqlCommand);
			CommonUtil.runSqlScript(DMLFileName.replace('\\', '/'), databaseName);
			
			String OrgDmlFileName = null;
			OrgDmlFileName = "/dml/MySQL/Schema/MYSQLDMLOrganisation.sql";
			CommonUtil.runSqlScript(OrgDmlFileName.replace('\\', '/'), databaseName);
			CommonUtil.runSqlScript(customScriptFileName.replace('\\', '/'), databaseName);
			QueriesDataLoader.executeQueriesScript(databaseName);
			return initializeApplicationDefaultData();
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			return 0;
		}
    }
    public static int addAdminAccount(Session masterSession) throws Exception
    {
        try
        {
            String userId ="sysadmin";
            String userPassword ="welcome123";
            String userPasswordHash = CommonUtil.getHash(userPassword);
        	JsonObject userSessionInfo = new JsonObject(); 
        	userSessionInfo.addProperty("loggedInEmployeeId", -1);
        	userSessionInfo.addProperty("userId", -1);
        	userSessionInfo.addProperty("loginEntityType", "");
			com.patientapp.controller.forms.impl.UserInfoControllerImpl  userInfoControllerImpl = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(masterSession, userSessionInfo);
			com.patientapp.bean.UserInfo userInfo = new com.patientapp.bean.UserInfo();
			userInfo.setUserFirstName("Admin");
			userInfo.setUserLastName("System");
			userInfo.setLoginId(userId);
			userInfo.setLoginEmailId("abc@gmail.com");
			userInfo.setLoginPassword(userPassword);
			userInfo.setOrganisationsUserOrgId(-1);
			userInfoControllerImpl.setManagedBean(userInfo);
			userInfoControllerImpl.setManagedBean("UserInfoBean", userInfo);
			if (userInfoControllerImpl.getHasTransactionFailed() == false)
			{
				JsonObject paramsInfoObj = new JsonObject();
				userInfoControllerImpl.create(paramsInfoObj);
			}
			userInfo = (com.patientapp.bean.UserInfo)userInfoControllerImpl.getManagedBean();
			Integer userInfoId = userInfo.getUserInfoId();
			if(userInfoId == null || userInfoId < 0)
			{
				System.err.println("Default initialization information could not be created.");
				return 0;
			}
			int adminPrivilegesAdded = RequestUtil.configureSysadminPrivileges(masterSession, userInfoId);
			if(adminPrivilegesAdded != 1)
			{
				return 0;
			}
			return userInfoId;
        }
        catch (Exception e)
        {
        	CommonUtil.writeTolog(e);
            return 0;
        }
    }
    public static int initializeApplicationDefaultData() throws Exception
    {
		Session masterSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName());
		int success = 0;
        try
        {
        	int userInfoId = addAdminAccount(masterSession);
        	if(userInfoId <= 0)
        	{
        		return 0;
        	}
        	String projectPath = SettingsUtil.getProjectPath();
        	String seedDataFilePath = projectPath+ File.separatorChar+"src"+File.separatorChar+"main"+File.separatorChar+"resources"+File.separatorChar+"data"+File.separatorChar + "seedData.xls";
        	File seedDataFile = new File(seedDataFilePath);
        	if(!seedDataFile.exists())
        	{
				success = 1;
        		return 1;
        	}
        	JsonObject requestInfo = new JsonObject();
        	JsonObject paramsInfo = new JsonObject();
        	paramsInfo.addProperty("areSourceDestinationSame", 0);
        	paramsInfo.addProperty("fileName", seedDataFilePath);
        	requestInfo.add("paramsInfo", paramsInfo);
        	JsonObject userSessionInfo = new JsonObject(); 
        	userSessionInfo.addProperty("loggedInEmployeeId", userInfoId);
        	userSessionInfo.addProperty("userId", userInfoId);
        	userSessionInfo.addProperty("loginEntityType", "");
			JsonObject dataObject = com.patientapp.util.UploadDownloadUtil.uploadData(userSessionInfo, requestInfo, masterSession, masterSession);
			if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
			{
				success = 1;
				return 1;
			}
            return 0;
        }
        catch (Exception e)
        {
        	CommonUtil.writeTolog(e);
            return 0;
        }
        finally
        {
			try
			{
				Transaction tx = masterSession.getTransaction();
				if (tx.isActive())
				{
					if(success==1)
					{
						tx.commit();						
					}
					else
					{
						tx.rollback();						
					}					
				}
				masterSession.close();
			}
			catch (Exception e2)
			{
				CommonUtil.writeTolog(e2);
			}
        }
    }
}
