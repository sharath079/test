package com.patientapp.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import com.patientapp.util.CommonUtil;
public class SettingsUtil
{
	static Properties configProperties;
	static Properties authorizationFlowConfigProperties;
	static boolean isInitialized = false;
	private static String mFilesTempDirectory = "";
	public static int schedulerStarted = 0;
	private static String sProjectpath = "";
	private static String sProjectFilesPath = "";
	private static String sProjectTemplatesPath = "";
	private static int isInitialised = 0;
	public static String sHomePageBeforeLoginUrl = "";
	public static void initaliseVariables()
	{
		if(isInitialised!=1)		
		{
			sProjectpath = System.getProperty("user.dir");
			sProjectFilesPath = sProjectpath + File.separator + "temp" + File.separator;			
			sProjectTemplatesPath = sProjectpath + File.separator + "src" + File.separator+"main"+File.separator+"resources"+File.separator+"templates"+File.separator;
			loadConfigProperties();
			String filesTempDirectory = configProperties.getProperty("FILES_TEMP_DIRECTORY");
			if (filesTempDirectory != null && filesTempDirectory.trim().length() > 0)
			{
				mFilesTempDirectory = filesTempDirectory;
			}
			isInitialised = 1;
		}
	}
	public static String getProjectPath()
	{
		initaliseVariables();
		return sProjectpath;
	}
	public static String getProjectFilesPath()
	{
		initaliseVariables();
		return sProjectFilesPath;
	}
	public static String getProjectTemplatesPath()
	{
		initaliseVariables();
		return sProjectTemplatesPath;
	}
	public static String getDatabaseName()
	{
		return CommonUtil.getMasterDBName();
	}
	public static Properties getConfigProperties()
	{
		return configProperties;
	}
	public static Properties getAuthorizationFlowConfigProperties()
	{
		initaliseVariables();
		return authorizationFlowConfigProperties;
	}
	public static String getRazorPayKeyId()
	{
		initaliseVariables();
		String ProductionMode = authorizationFlowConfigProperties.getProperty("ProductionMode");
		if(ProductionMode.equals("0"))
		{
			 return authorizationFlowConfigProperties.getProperty("RazorPayKeyIdTest");
		}
		return authorizationFlowConfigProperties.getProperty("RazorPayKeyIdLive");
	}
	public static String getRazorPayKeyValue()
	{
		initaliseVariables();
		String ProductionMode = authorizationFlowConfigProperties.getProperty("ProductionMode");
		if(ProductionMode.equals("0"))
		{
			 return authorizationFlowConfigProperties.getProperty("RazorPayKeyValueTest");
		}
		return authorizationFlowConfigProperties.getProperty("RazorPayKeyValueLive");
	}
	public static String getRazorPayAccountNumber()
	{
		initaliseVariables();
		String ProductionMode = authorizationFlowConfigProperties.getProperty("ProductionMode");
		if(ProductionMode.equals("0"))
		{
			 return authorizationFlowConfigProperties.getProperty("RazorPayAccountNumberTest");
		}
		return authorizationFlowConfigProperties.getProperty("RazorPayAccountNumberLive");
	}
	public static void setConfigProperties(Properties configProperties)
	{
		SettingsUtil.configProperties = configProperties;
	}
	public static String getProjectDirectory()
	{
		return getProjectPath();
	}
	private File getFileFromResources(String fileName)
	{
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null)
		{
			throw new IllegalArgumentException("file is not found!");
		}
		else
		{
			return new File(resource.getFile());
		}
	}
	public static void loadConfigProperties()
	{
		if (isInitialized == true)
		{
			return;
		}
		InputStream input = null;
		configProperties = new Properties();
		authorizationFlowConfigProperties = new Properties();
		try
		{
			String filename = "/project-config.properties";
			input = SettingsUtil.class.getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Unable to find " + filename + " in the classpath");
				return;
			}
			configProperties.load(input);
			String authorizationFlowFilename = "/application-config.properties";
			input = SettingsUtil.class.getResourceAsStream(authorizationFlowFilename);
			if (input == null)
			{
				System.out.println("Unable to find " + authorizationFlowFilename + " in the classpath");
				return;
			}
			authorizationFlowConfigProperties.load(input);
			isInitialized = true;
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	public static String getFilesTempDirectory()
	{
		initaliseVariables();
		String filesTempDirectory = mFilesTempDirectory;
		if (filesTempDirectory == null || filesTempDirectory.trim().length() == 0)
		{
			filesTempDirectory = sProjectpath;
		}
		return filesTempDirectory;
	}
}
