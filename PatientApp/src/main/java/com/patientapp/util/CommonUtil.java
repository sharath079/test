package com.patientapp.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.sql.Blob;
import org.hibernate.Hibernate;
import com.patientapp.request.service.ServiceAPIUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.Tasks;
import com.patientapp.bean.FileUpload;
import com.patientapp.util.layout.LayoutParser;
import com.patientapp.util.SmtpAuthenticator;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.SettingsUtil;
public class CommonUtil
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonUtil.class);
	public static HashMap<Integer, String> fileIDAndNamesMap = new HashMap<Integer, String>();
	public static int sequenceCount = 1;
	public static boolean configPropertiesLoaded = false;
	public static Properties projectConfigProperties;
	public static Set<Integer> employeeSessionMap = new HashSet<Integer>();
	public static String getStackTrace(Throwable e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	public static String getStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	public static final String SEQUENCE_NO_CODE_JOURNAL_VOUCHER = "JOURNAL_VOUCHER";
	public static final String SEQUENCE_NO_CODE_EXPENSE_VOUCHER = "EXPENSE_VOUCHER";
	public static final String SEQUENCE_NO_CODE_EXPENSE_PAYMNET_VOUCHER = "EXPENSE_PAYMNET_VOUCHER";
	public static final String SEQUENCE_NO_CODE_INCOME_VOUCHER = "INCOME_VOUCHER";
	public static final String SEQUENCE_NO_CODE_INCOME_PAYMNET_VOUCHER = "INCOME_PAYMNET_VOUCHER";
	public static final String SEQUENCE_NO_CODE_PAYMENT_AGAINST_ACCOUNT = "PAYMENT_AGAINST_ACCOUNT";
	public static final String SEQUENCE_NO_CODE_RECEIPT_AGAINST_ACCOUNT = "RECEIPT_AGAINST_ACCOUNT";
	public static final String SEQUENCE_NO_CODE_WAREHOUSE_CONSIGNMENT = "WAREHOUSE_CONSIGNMENT";
	public static final String SEQUENCE_NO_CODE_INVENTORY_ADJUSTMENT = "INVENTORY_ADJUSTMENT";
	public static final String SEQUENCE_NO_CODE_PURCHASE_QUOTATION = "PURCHASE_QUOTATION";
	public static final String SEQUENCE_NO_CODE_PURCHASE_ORDER = "PURCHASE_ORDER";
	public static final String SEQUENCE_NO_CODE_MATERIAL_RECEIPT = "MATERIAL_RECEIPT";
	public static final String SEQUENCE_NO_CODE_PURCHASE_INVOICE = "PURCHASE_INVOICE";
	public static final String SEQUENCE_NO_CODE_PAYMENT_TO_SUPPLIER = "PAYMENT_TO_SUPPLIER";
	public static final String SEQUENCE_NO_CODE_DEBIT_NOTE = "DEBIT_NOTE";
	public static final String SEQUENCE_NO_CODE_PAYMENT_FROM_SUPPLIER = "PAYMENT_FROM_SUPPLIER";
	public static final String SEQUENCE_NO_CODE_SALES_INVOICE = "SALES_INVOICE";
	public static final String SEQUENCE_NO_CODE_CREDIT_NOTE = "CREDIT_NOTE";
	public static final String SEQUENCE_NO_CODE_PAYMENT_FROM_CUSTOMER = "PAYMENT_FROM_CUSTOMER";
	public static final String SEQUENCE_NO_CODE_PAYMENT_TO_CUSTOMER = "PAYMENT_TO_CUSTOMER";
	public static final String SEQUENCE_NO_CODE_SALES_QUOTATION = "SALES_QUOTATION";
	public static final String SEQUENCE_NO_CODE_SALES_ORDER = "SALES_ORDER";
	public static final String SEQUENCE_NO_CODE_DELIVERY_CHALLAN = "DELIVERY_CHALLAN";
	public static final String SEQUENCE_NO_CODE_EXPENSE_CLAIM = "EXPENSE_CLAIM";
	public static final String SEQUENCE_NO_CODE_EXPENSE_CLAIM_PAYMENT = "EXPENSE_CLAIM_PAYMENT";
	public static final String SEQUENCE_NO_CODE_JOURNAL_ENTRY = "JOURNAL_ENTRY";
	public static final String SEQUENCE_NO_CODE_ASSET = "ASSET";
	public static final String SEQUENCE_NO_CODE_ASSET_DEPRECIATION = "ASSET_DEPRECIATION";
	public static final String SEQUENCE_NO_CODE_PRODUCTION_ENTRY = "PRODUCTION_ENTRY";
	public static final String SEQUENCE_NO_CODE_JOB_WORK_IN = "JOB_WORK_IN";
	public static final String SEQUENCE_NO_CODE_JOB_WORK_OUT = "JOB_WORK_OUT";
	public static final String STOCK_MOVEMENT_TYPE_STOCK_ADJUSTMENT_INCREASE = "STOCK_ADJUSTMENT_INCREASE";
	public static final String STOCK_MOVEMENT_TYPE_STOCK_ADJUSTMENT_DECREASE = "STOCK_ADJUSTMENT_DECREASE";
	public static final String STOCK_MOVEMENT_TYPE_INIT_BAL = "INIT_BAL";
	public static final String STOCK_MOVEMENT_TYPE_NEWLY_ADDED = "NEWLY_ADDED"; // used in product item ids movement
	public static final String STOCK_MOVEMENT_TYPE_RETAIL_SALES = "RETAIL_SALES";
	public static final String STOCK_MOVEMENT_TYPE_RETAIL_SALES_RETURN = "RETAIL_SALES_RETURN";
	public static final String STOCK_MOVEMENT_TYPE_DELIVERY_CHALLAN = "DELIVERY_CHALLAN";
	public static final String STOCK_MOVEMENT_TYPE_SALES_REJECTION_MATERIAL_RECEIPT = "SALES_REJECTION_MATERIAL_RECEIPT";
	public static final String STOCK_MOVEMENT_TYPE_SALES_RETURN_MATERIAL_RECEIPT = "SALES_RETURN_MATERIAL_RECEIPT";
	public static final String STOCK_MOVEMENT_TYPE_MATERIAL_RECEIPT = "MATERIAL_RECEIPT";
	public static final String STOCK_MOVEMENT_TYPE_PURCHASE_RETURN_DELIVERY_CHALLAN = "PURCHASE_RETURN_DELIVERY_CHALLAN";
	public static final String STOCK_MOVEMENT_TYPE_PURCHASE_REJECTION_DELIVERY_CHALLAN = "PURCHASE_REJECTION_DELIVERY_CHALLAN";
	//
	public static final String STOCK_MOVEMENT_TYPE_FROM_WAREHOUSE_TRANSFER = "FROM_WAREHOUSE_TRANSFER";
	public static final String STOCK_MOVEMENT_TYPE_TO_WAREHOUSE_TRANSFER = "TO_WAREHOUSE_TRANSFER";
	public static final String STOCK_MOVEMENT_TYPE_DEDUCT_DERIVED_PROD_INPUT = "DEDUCT_DERIVED_PROD_INPUT";
	public static final String STOCK_MOVEMENT_TYPE_REVERT_DERIVED_PROD_INPUT = "REVERT_DERIVED_PROD_INPUT";
	public static final String STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_CONSUMABLE_ITEMS = "PRODUCTION_ENTRY_CONSUMABLE_ITEMS";
	public static final String STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_UTILITY_ITEMS = "PRODUCTION_ENTRY_UTILITY_ITEMS";
	public static final String STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_INPUT_ITEMS = "PRODUCTION_ENTRY_INPUT_ITEMS";
	public static final String STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_OUTPUT_ITEMS = "PRODUCTION_ENTRY_OUTPUT_ITEMS";
	public static final String STOCK_MOVEMENT_TYPE_MACHINE_SCHEDULED_MAINTENANCE = "MACHINE_SCHEDULED_MAINTENANCE";
	public static final String STOCK_MOVEMENT_TYPE_MACHINE_BREAKDOWN_MAINTENANCE = "MACHINE_BREAKDOWN_MAINTENANCE";
	public static final String STOCK_MOVEMENT_TYPE_MACHINE_SCHEDULED_MAINTENANCE_OLD_ITEM = "MACHINE_SCHEDULED_MAINTENANCE_OLD_ITEM";
	public static final String STOCK_MOVEMENT_TYPE_MACHINE_BREAKDOWN_MAINTENANCE_OLD_ITEM = "MACHINE_BREAKDOWN_MAINTENANCE_OLD_ITEM";
	public static final ArrayList<String> STOCK_MOVEMENT_TYPE_OUTPUTS_LIST = new ArrayList<String>();
	static
	{
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_RETAIL_SALES_RETURN);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_DELIVERY_CHALLAN);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_RETAIL_SALES);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PURCHASE_REJECTION_DELIVERY_CHALLAN);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PURCHASE_RETURN_DELIVERY_CHALLAN);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_STOCK_ADJUSTMENT_DECREASE);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_CONSUMABLE_ITEMS);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_INPUT_ITEMS);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_UTILITY_ITEMS);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_MACHINE_BREAKDOWN_MAINTENANCE);
		STOCK_MOVEMENT_TYPE_OUTPUTS_LIST.add(STOCK_MOVEMENT_TYPE_MACHINE_SCHEDULED_MAINTENANCE);
	}
	public static final ArrayList<String> STOCK_MOVEMENT_TYPE_INPUTS_LIST = new ArrayList<String>();
	static
	{
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_INIT_BAL);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_MATERIAL_RECEIPT);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_STOCK_ADJUSTMENT_INCREASE);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_TO_WAREHOUSE_TRANSFER);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_SALES_REJECTION_MATERIAL_RECEIPT);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_SALES_RETURN_MATERIAL_RECEIPT);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_FROM_WAREHOUSE_TRANSFER);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_MACHINE_SCHEDULED_MAINTENANCE_OLD_ITEM);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_MACHINE_BREAKDOWN_MAINTENANCE_OLD_ITEM);
		STOCK_MOVEMENT_TYPE_INPUTS_LIST.add(STOCK_MOVEMENT_TYPE_PRODUCTION_ENTRY_OUTPUT_ITEMS);
	}
	public static final HashMap<String, Integer> MONTH_TO_NUMERIC_MAP = new HashMap<String, Integer>();
	static
	{
		MONTH_TO_NUMERIC_MAP.put("JANUARY", 1);
		MONTH_TO_NUMERIC_MAP.put("FEBRUARY", 2);
		MONTH_TO_NUMERIC_MAP.put("MARCH", 3);
		MONTH_TO_NUMERIC_MAP.put("APRIL", 4);
		MONTH_TO_NUMERIC_MAP.put("MAY", 5);
		MONTH_TO_NUMERIC_MAP.put("JUNE", 6);
		MONTH_TO_NUMERIC_MAP.put("JULY", 7);
		MONTH_TO_NUMERIC_MAP.put("AUGUST", 8);
		MONTH_TO_NUMERIC_MAP.put("SEPTEMBER", 9);
		MONTH_TO_NUMERIC_MAP.put("OCTOBER", 10);
		MONTH_TO_NUMERIC_MAP.put("NOVEMBER", 11);
		MONTH_TO_NUMERIC_MAP.put("DECEMBER", 12);
	}
	public java.util.Date getFinancialYearStartDateFromDate(Date date) throws Exception
	{
		int startingMonth = 4;// GeneralSettingsReporting.getFinancialYearStartingMonth(genericConn,
								// storeConn);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		int dateMonth = calendar.get(Calendar.MONTH);
		if (dateMonth + 1 < startingMonth)
		{
			dateYear--;
		}
		calendar.set(dateYear, startingMonth - 1, 1);
		return calendar.getTime();
	}
	public Date getMonthEndDate(Date date) throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 0);
		Date endDate = calendar.getTime();
		return endDate;
	}
	public Date getNextMonthStartDate(Date date) throws Exception
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	public Date[] getFinancialYearStartAndEndDatesFromDate(Session session, Date date) throws Exception
	{
		int startingMonth = 4;// GeneralSettingsReporting.getFinancialYearStartingMonth(genericConn,
								// storeConn);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dateFinancialYear = calendar.get(Calendar.YEAR);
		int dateMonth = calendar.get(Calendar.MONTH);
		if (dateMonth + 1 < startingMonth)
		{
			dateFinancialYear--;
		}
		calendar.set(dateFinancialYear, startingMonth - 1, 1);
		Date startDate = calendar.getTime();
		calendar.set(dateFinancialYear + 1, startingMonth - 1, 0);
		Date endDate = calendar.getTime();
		Date[] startAndEndDates =
		{ startDate, endDate };
		return startAndEndDates;
	}
	public static void writeToLog(String logMessage)
	{
		System.out.println(logMessage);
	}
	public static void writeToLog(Object obj, Exception e)
	{
		System.out.println(getStackTrace(e));
	}
	public static void writeToLog(Class classObject, Exception e)
	{
		System.out.println(getStackTrace(e));
	}
	public static Integer getCurrentTime()
	{
		String sTimePattern = "HHmmssSSS";
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(sTimePattern);
		String sTime = simpleTimeFormat.format(new Date());
		return Integer.parseInt(sTime);
	}
	public static ArrayList executeRuleCalculateLineItemPriceInfo(BigDecimal sTagItemQuantity, BigDecimal sTagUnitPriceWithoutDiscountInput, String sTagApplyDiscount, String sTagDiscountType, BigDecimal sTagDiscountPercentage, BigDecimal sTagUnitDiscountAmount, BigDecimal sTagCgstTaxRate, BigDecimal sTagSgstTaxRate, BigDecimal sTagIgstTaxRate, BigDecimal sTagLineItemUnitDiscountAmount, BigDecimal sTagUnitPrice, BigDecimal sTagLineItemTotalDiscountAmount, BigDecimal sTagUnitPriceBeforeTaxes, BigDecimal sTagUnitPriceAfterTaxes, BigDecimal sTagCgstTaxAmount, BigDecimal sTagSgstTaxAmount, BigDecimal sTagIgstTaxAmount, BigDecimal sTagSubTotalBeforeTaxes, BigDecimal sTagSubTotalAfterTaxes)
	{
		BigDecimal unitPriceWithoutDiscountInput = sTagUnitPriceWithoutDiscountInput;
		BigDecimal lineItemUnitDiscountAmount = new BigDecimal(0);
		if (sTagApplyDiscount.equals("YES"))
		{
			if (sTagDiscountType.equals("PERCENTAGE"))
			{
				lineItemUnitDiscountAmount = unitPriceWithoutDiscountInput.multiply(sTagDiscountPercentage).divide(new BigDecimal(100));
			}
			else
			{
				lineItemUnitDiscountAmount = sTagUnitDiscountAmount;
			}
		}
		sTagUnitPrice = unitPriceWithoutDiscountInput.subtract(lineItemUnitDiscountAmount);
		sTagLineItemUnitDiscountAmount = lineItemUnitDiscountAmount;
		sTagUnitPriceBeforeTaxes = sTagUnitPrice;
		sTagUnitPriceAfterTaxes = sTagUnitPrice;
		if (sTagCgstTaxRate == null)
		{
			sTagCgstTaxRate = new BigDecimal(0);
		}
		if (sTagSgstTaxRate == null)
		{
			sTagSgstTaxRate = new BigDecimal(0);
		}
		if (sTagIgstTaxRate == null)
		{
			sTagIgstTaxRate = new BigDecimal(0);
		}
		BigDecimal sTagUnitCgstAmount = sTagUnitPrice.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitSgstAmount = sTagUnitPrice.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		BigDecimal sTagUnitIgstAmount = sTagUnitPrice.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagUnitPriceAfterTaxes = sTagUnitPriceAfterTaxes.add(sTagUnitCgstAmount).add(sTagUnitSgstAmount).add(sTagUnitIgstAmount);
		sTagLineItemTotalDiscountAmount = sTagLineItemUnitDiscountAmount.multiply(sTagItemQuantity);
		BigDecimal sTagSubTotal = sTagItemQuantity.multiply(sTagUnitPrice);
		sTagSubTotalBeforeTaxes = sTagSubTotal;
		sTagCgstTaxAmount = sTagSubTotal.multiply(sTagCgstTaxRate).divide(new BigDecimal(100));
		sTagSgstTaxAmount = sTagSubTotal.multiply(sTagSgstTaxRate).divide(new BigDecimal(100));
		sTagIgstTaxAmount = sTagSubTotal.multiply(sTagIgstTaxRate).divide(new BigDecimal(100));
		sTagSubTotalAfterTaxes = sTagSubTotal.add(sTagCgstTaxAmount).add(sTagSgstTaxAmount).add(sTagIgstTaxAmount);
		List returnArray = new ArrayList();
		returnArray.add(sTagLineItemUnitDiscountAmount);
		returnArray.add(sTagUnitPrice);
		returnArray.add(sTagLineItemTotalDiscountAmount);
		returnArray.add(sTagUnitPriceBeforeTaxes);
		returnArray.add(sTagUnitPriceAfterTaxes);
		returnArray.add(sTagCgstTaxAmount);
		returnArray.add(sTagSgstTaxAmount);
		returnArray.add(sTagIgstTaxAmount);
		returnArray.add(sTagSubTotalBeforeTaxes);
		returnArray.add(sTagSubTotal);
		returnArray.add(sTagSubTotalAfterTaxes);
		return (ArrayList) returnArray;
	}
	public static String getStringInLowerCase(String str)
	{
		if (str == null)
		{
			str = "";
		}
		if (str.length() == 0)
		{
			return str;
		}
		if (str.length() == 1)
		{
			return str.toLowerCase();
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	public static String getStringInUpperCase(String str)
	{
		if (str == null)
		{
			str = "";
		}
		if (str.length() == 0)
		{
			return str;
		}
		if (str.length() == 1)
		{
			return str.toUpperCase();
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	public static void closeStatement(Statement stmt) throws Exception
	{
		try
		{
			if (stmt != null)
			{
				stmt.close();
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	public static void closeResultSet(ResultSet rs) throws Exception
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	public static boolean isEntityExists(Connection genericConn, String selectQuery, Object... queryParams) throws Exception
	{
		PreparedStatement selectEntitiesCountStmt = null;
		ResultSet entitiesCountResultSet = null;
		try
		{
			selectEntitiesCountStmt = genericConn.prepareStatement(selectQuery);
			for (int i = 0; i < queryParams.length; i++)
			{
				selectEntitiesCountStmt.setObject(i + 1, queryParams[i]);
			}
			entitiesCountResultSet = selectEntitiesCountStmt.executeQuery();
			if (entitiesCountResultSet.next())
			{
				int matchingCount = entitiesCountResultSet.getInt(1);
				if (matchingCount >= 1)
				{
					return true;
				}
			}
		}
		catch (Exception e)
		{
			logger.error(CommonUtil.getStackTrace(e));
			throw e;
		}
		finally
		{
			CommonUtil.closeResultSet(entitiesCountResultSet);
			CommonUtil.closeStatement(selectEntitiesCountStmt);
		}
		return false;
	}
	public static int getMatchingEntityId(Connection genericConn, Connection storeConn, String selectQuery, Object... queryParams) throws Exception
	{
		PreparedStatement selectEntityInfoStmt = null;
		ResultSet entityInfoResultSet = null;
		try
		{
			selectEntityInfoStmt = storeConn.prepareStatement(selectQuery);
			for (int i = 0; i < queryParams.length; i++)
			{
				selectEntityInfoStmt.setObject(i + 1, queryParams[i]);
			}
			entityInfoResultSet = selectEntityInfoStmt.executeQuery();
			if (entityInfoResultSet.next())
			{
				int entityId = entityInfoResultSet.getInt(1);
				return entityId;
			}
		}
		catch (Exception e)
		{
			logger.error(CommonUtil.getStackTrace(e));
			throw e;
		}
		finally
		{
			CommonUtil.closeResultSet(entityInfoResultSet);
			CommonUtil.closeStatement(selectEntityInfoStmt);
		}
		return -1;
	}
	public static String getMasterDBName()
	{
		String masterDBName = "";
		try
		{
			if (configPropertiesLoaded == false)
			{
				loadConfigProperties();
			}
			if (projectConfigProperties.containsKey("DATABASE_NAME"))
			{
				masterDBName = projectConfigProperties.getProperty("DATABASE_NAME");
			}
			return masterDBName;
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return masterDBName;
	}
	public static Session getNewMasterSession(Session masterSession, JsonObject userSessionInfo)
	{
		Session newMasterSession = null;
		try
		{
			boolean createNewSession = true;
			newMasterSession = SessionFactoryBuilder.getDBSession(CommonUtil.getMasterDBName(), createNewSession);
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return newMasterSession;
	}
	public static Session getNewOrganisationSession(Session masterSession, JsonObject userSessionInfo)
	{
		Session newOrganisationSession = null;
		try
		{
			int organisationsId = userSessionInfo.get("organisationsId").getAsInt();
			JsonObject organisationsSearchParams = new JsonObject();
			organisationsSearchParams.addProperty("organisationsId", organisationsId);
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsControllerImpl = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(masterSession, userSessionInfo);
			JsonObject organisationsResponseData = organisationsControllerImpl.retrieveOrganisations(organisationsSearchParams, new JsonObject());
			if(!organisationsResponseData.has("success") || organisationsResponseData.get("success").getAsInt() != 1)
			{
				return null;
			}
			JsonObject organisationsDataObject = organisationsResponseData.get("organisationsDataObject").getAsJsonObject();
			String databaseName = organisationsDataObject.get("databaseName").getAsString();
			boolean createNewSession = true;
			newOrganisationSession = SessionFactoryBuilder.getDBSession(databaseName, createNewSession);
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return newOrganisationSession;
	}
	public static String getApplicationUrlPrefix()
	{
	    String companyName = CommonUtil.getPropertyValue("COMPANY_NAME");
	    String applicationName = CommonUtil.getPropertyValue("APPLICATION_NAME");
	    String applicationUrlPrefix = "";
	    if(companyName!=null && companyName.length()>0)
	    {
	    	applicationUrlPrefix += "/" + companyName.toLowerCase();
	    }
	    if(applicationName!=null && applicationName.length()>0)
	    {
	    	applicationUrlPrefix += "/" + applicationName.toLowerCase();
	    }            
	    return applicationUrlPrefix;
	}
	public static String getPropertyValue(String propertyName)
	{
		String propertyValue = "";
		try
		{
			if (configPropertiesLoaded == false)
			{
				loadConfigProperties();
			}
			if (projectConfigProperties.containsKey(propertyName))
			{
				propertyValue = projectConfigProperties.getProperty(propertyName);
			}
			return propertyValue;
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return propertyValue;
	}
	public static void loadConfigProperties()
	{
		InputStream input = null;
		projectConfigProperties = new Properties();
		try
		{
			String filename = "/project-config.properties";
			input = SettingsUtil.class.getResourceAsStream(filename);
			projectConfigProperties.load(input);
			configPropertiesLoaded = true;
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (Exception e)
				{
					writeTolog(e);
				}
			}
		}
	}
	public static Connection getConnection() throws Exception
	{
		System.out.println("Before connection for master db");	
		String dbUserName  = CommonUtil.getPropertyValue("DB_USER_NAME");
		String dbPassword  = CommonUtil.getPropertyValue("DB_PASSWORD");
		String dbHostName  = CommonUtil.getPropertyValue("DB_HOST_NAME");
		String dbHostPort  = CommonUtil.getPropertyValue("DB_HOST_PORT");		
		System.out.println("DB User name = " +  dbUserName + "DB password = " +  dbPassword + "DB Host name = " +  dbHostName + "DB port = " +  dbHostPort);
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHostName + ":" + dbHostPort + "/" + getMasterDBName(), dbUserName, dbPassword);
		conn.setAutoCommit(false);
		return conn;
	}
	public static Connection getConnection(String dbName) throws Exception
	{	
		System.out.println("Before connection for specific db");
		String dbUserName  = CommonUtil.getPropertyValue("DB_USER_NAME");
		String dbPassword  = CommonUtil.getPropertyValue("DB_PASSWORD");
		String dbHostName  = CommonUtil.getPropertyValue("DB_HOST_NAME");
		String dbHostPort  = CommonUtil.getPropertyValue("DB_HOST_PORT");	
		System.out.println("DB User name = " +  dbUserName + "DB password = " +  dbPassword + "DB Host name = " +  dbHostName + "DB port = " +  dbHostPort);
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHostName + ":" + dbHostPort + "/" + dbName, dbUserName, dbPassword);
		conn.setAutoCommit(false);
		return conn;
	}
	public static DataSource getDataSource()
	{
		return getDataSource("localhost", 3306, "htserpv6_4_1_1");
	}
	public static DataSource getDataSource(String hostIp, int hostPort, String dbName)
	{
		DataSource dbSource = new DataSource();
		dbSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dbSource.setUrl("jdbc:mysql://" + hostIp + ":" + hostPort + "/" + dbName);
		dbSource.setUsername(getPropertyValue("DB_USER_NAME"));
		dbSource.setPassword(getPropertyValue("DB_PASSWORD"));
		dbSource.setTestOnBorrow(true);
		dbSource.setValidationQuery("select 1;");
		dbSource.setValidationInterval(3000000); // Set this appropriately in production
		return dbSource;
	}
	public static String getMatchingEntityText(Connection genericConn, Connection storeConn, String selectQuery, Object... queryParams) throws Exception
	{
		PreparedStatement selectEntityInfoStmt = null;
		ResultSet entityInfoResultSet = null;
		try
		{
			selectEntityInfoStmt = storeConn.prepareStatement(selectQuery);
			for (int i = 0; i < queryParams.length; i++)
			{
				selectEntityInfoStmt.setObject(i + 1, queryParams[i]);
			}
			entityInfoResultSet = selectEntityInfoStmt.executeQuery();
			String entityText = "";
			int matchCount = 0;
			while (entityInfoResultSet.next())
			{
				if (matchCount > 0)
				{
					entityText += ", ";
				}
				entityText += entityInfoResultSet.getString(1);
				matchCount++;
			}
			return entityText;
		}
		catch (Exception e)
		{
			logger.error(CommonUtil.getStackTrace(e));
			throw e;
		}
		finally
		{
			CommonUtil.closeResultSet(entityInfoResultSet);
			CommonUtil.closeStatement(selectEntityInfoStmt);
		}
	}
	public static void writeTolog(String msg)
	{
		System.out.println(msg);
	}
	public static void writeTolog(Exception e)
	{
		String msg = getStackTrace(e);
		System.out.println(msg);
	}
	public static void writeTolog(Class currentClass, Exception e)
	{
		String msg = getStackTrace(e);
		System.out.println(msg);
	}
	public static JsonObject getPageLinksList(Connection storeConn, Map<String, String> paramsMap) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		PreparedStatement selectStmt = null;
		ResultSet resultSet = null;
		try
		{
			String pageLinkNameDisplayPrefix = paramsMap.get("pageLinkNameDisplayPrefix");
			pageLinkNameDisplayPrefix += "%";
			String selectQuery = "select " + " pageLinkUrl, " + "  pageLinkName " + " from " + " PageLinks " + " where" + "  2>1  " + " and  pageLinkName like ? ";
			selectStmt = storeConn.prepareStatement(selectQuery);
			int paramPosIndex = 1;
			selectStmt.setString(paramPosIndex++, pageLinkNameDisplayPrefix);
			resultSet = selectStmt.executeQuery();
			JsonArray pageLinksList = new JsonArray();
			while (resultSet.next())
			{
				JsonObject pageLinkInfo = new JsonObject();
				pageLinkInfo.addProperty("pageLinkUrl", resultSet.getString("pageLinkUrl"));
				pageLinkInfo.addProperty("pageLinkName", resultSet.getString("pageLinkName"));
				pageLinksList.add(pageLinkInfo);
			}
			dataObject.addProperty("success", 1);
			dataObject.add("pageLinksList", pageLinksList);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(CommonUtil.class, e);
		}
		finally
		{
			CommonUtil.closeResultSet(resultSet);
			CommonUtil.closeStatement(selectStmt);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "page links list could not be retrieved.");
		return dataObject;
	}
	public static String getFileContent(String fileName) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try
		{
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null)
			{
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			return everything;
		}
		finally
		{
			br.close();
		}
	}
	public static JsonObject writeContentToFile(String updatedFileContent, String filePathString) throws Exception
	{
		BufferedWriter bw = null;
		FileWriter fw = null;
		JsonObject dataObject = new JsonObject();
		try
		{
			String content = updatedFileContent;
			fw = new FileWriter(filePathString);
			bw = new BufferedWriter(fw);
			bw.write(content);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "content could not be generated.");
			return dataObject;
		}
		finally
		{
			try
			{
				if (bw != null)
				{
					bw.close();
				}
				if (fw != null)
				{
					fw.close();
				}
			}
			catch (Exception e)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "content could not be generated.");
				return dataObject;
			}
		}
	}
	public static String escapeHTMLText(String htmlText)
	{
		if (htmlText == null || htmlText.length() == 0)
		{
			return htmlText;
		}
		return htmlText.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&#039;");
	}
	public static String generatePDFFromHTML(String pageHtml, String widthInInches, String heightInInches, String filesTempDirectoryPathString, String outputFileName) throws Exception
	{
		String finalHTMLText  = "<html><head><style type='text/css'>@page { size: " + widthInInches + " " + heightInInches + " ; margin: 0cm }</style></head><body>" + pageHtml + "</body></html>";		
		ITextRenderer renderer = new ITextRenderer();		
		renderer.setDocumentFromString(finalHTMLText);
		renderer.layout();
		String fileName = outputFileName + "_" + CommonUtil.getFileNameByCurrentTime() + ".pdf";
		String fileAbsolutePath = filesTempDirectoryPathString + File.separator + fileName;
		FileOutputStream fos = new FileOutputStream(fileAbsolutePath);
		renderer.createPDF(fos);
		fos.close();
		return fileAbsolutePath;
	}
	public static String getFileNameByCurrentTime()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
		String currentTimeString = dateFormat.format(new Date());
		Random rand = new Random();
		int value = rand.nextInt(1000);
		return currentTimeString + Integer.toString(value);
	}
	public static void copyFileOrDirectoryUsingApacheCommonIO(File source, File dest) throws IOException
	{
		copyFileOrDirectoryUsingApacheCommonIO(source, dest, false);
	}
	public static void copyFileOrDirectoryUsingApacheCommonIO(File source, File dest, boolean isDirectoryFileFilter) throws IOException
	{
		try
		{
			if (source.isDirectory())
			{
				if (isDirectoryFileFilter == true)
				{
					// FileUtils.copyDirectory(source, dest, FileFilterUtils.directoryFileFilter());
				}
				else
				{
					FileUtils.copyDirectory(source, dest);
				}
			}
			else
			{
				FileUtils.copyFile(source, dest);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
	}
	public static JsonObject generateReportPDF(Connection storeConn, Map<String, String> paramsMap) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String printFormatCode = "";
			JsonArray URLParameterNameAndValuesList = new JsonArray();
			String htmlContent = null;//Tasks.getLayoutHTMLWithDataFromFile(null, printFormatCode, URLParameterNameAndValuesList);
			if(htmlContent == null)
			{
				return null;	
			}
			String mainDiv = "<div style='position: absolute;left: 100px;top: 100px;'>";
			String finalHtml = mainDiv + htmlContent + "</div>";
			String filesTempDirectoryString = getPropertyValue("FILES_TEMP_DIRECTORY");
			File filesTempDirectory = new File(filesTempDirectoryString);
			if(!(filesTempDirectory.exists()))
			{
				filesTempDirectory.mkdirs();
			}
			String projectPath = SettingsUtil.getProjectPath();
			String filesTempDirectoryPathString = projectPath + File.separatorChar + filesTempDirectoryString + File.separatorChar;
			String outputFileName = printFormatCode;
			double pageWidth = 10.5;
			double pageHeight = 12;
			String pageWidthAttribute = pageWidth + "in";
			String pageHeightAttribute = pageHeight + "in";
			String fileAbsolutePath = CommonUtil.generatePDFFromHTML(finalHtml, pageWidthAttribute, pageHeightAttribute, filesTempDirectoryPathString, outputFileName);
			int sequenceCount = CommonUtil.sequenceCount;
			// Maintaining file related mapping id and fileAbsolutePath
			//fileIDAndNamesMap.put(sequenceCount, fileAbsolutePath);
			int fileId = sequenceCount;
			sequenceCount += 1;
			dataObject.addProperty("success", 1);
			dataObject.addProperty("fileId", fileId);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(CommonUtil.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Report pdf file could not be generated.");
		}
		finally
		{
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Report pdf file could not be generated");
		return dataObject;
	}
	public static JsonObject sendEmail(Connection storeConn, Map<String, String> paramsMap) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		dataObject.addProperty("isRequestHandled", 1);
		try
		{
			JsonArray attachmentFilesList = new JsonArray();
			int isAttachment = 0;
			String fileName = "/home/hts10/eclipse/DigiXOffice360/WebContent/util/test/TestDropDown.html";
			String emailMessage = new String(Files.readAllBytes(Paths.get(fileName)));
			String subject = "Test Email";
			String[] toEmailIdsList =
			{ "gavvalaranjith@gmail.com" };
			int mailSent = sendEmail(toEmailIdsList, subject, emailMessage, isAttachment, attachmentFilesList, "");
			if (mailSent == 1)
			{
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", "Email sent successfully");
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(CommonUtil.class, e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Email could not be sent.");
		return dataObject;
	}
	public static int sendEmail(String[] toEmailIdsList, String subject, String emailMessage, int isAttachment, JsonArray attachmentFilesList, String emailFormat) throws Exception
	{
		String fromEmailId  = CommonUtil.getPropertyValue("NOTIFICATION_EMAIL_ID");
		String fromEmailIdPassword  = CommonUtil.getPropertyValue("NOTIFICATION_EMAIL_PASSWORD");
		boolean isValidEmail = Tasks.isValidEmail(fromEmailId);
		if(isValidEmail==false)
		{
			return 0;
		}
		String from = fromEmailId;
		String host = CommonUtil.getPropertyValue("NOTIFICATION_EMAIL_SMTP_SERVER");
		String isLiveDataUnderUseForDevelopment = CommonUtil.getPropertyValue("IS_LIVE_DATA_UNDER_USE_FOR_DEVELOPEMENT");
		String notificationTestEmailId = CommonUtil.getPropertyValue("NOTIFICATION_TEST_EMAIL_ID");
		if(isLiveDataUnderUseForDevelopment.trim().length() > 0)
		{
			if(isLiveDataUnderUseForDevelopment.equalsIgnoreCase("1"))
			{
				isValidEmail = Tasks.isValidEmail(notificationTestEmailId);
				if(isValidEmail==false)
				{
					return 0;
				}
				toEmailIdsList = new String[1];
				toEmailIdsList[0] = notificationTestEmailId;
			}
		}					
		String password = fromEmailIdPassword;
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtps.host", host);
		properties.put("mail.smtps.starttls.enable", "true");
		properties.put("mail.transport.protocol.rfc822", "smtps");
		properties.put("mail.smtps.ssl.enable", "true");
		// properties.put("mail.smtps.port", "587");
		properties.put("mail.smtps.ssl.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtps.auth", "true");
		// properties.put("mail.smtp.ssl.socketFactory.port", "465");
		// properties.put("mail.smtp.socketFactory.port", "465");
		/*
		 * properties.put("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory"); properties.setProperty("mail.smtp.port",
		 * "465");
		 */
		// properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", true);
		// Get the default Session object.
		SmtpAuthenticator authenticator = new SmtpAuthenticator(from, password);
//		Session session =  Session.getDefaultInstance(properties, authenticator);
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, authenticator);
		try
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, ""));
			// message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(toEmailId));
			for (int i = 0; i < toEmailIdsList.length; i++)
			{
				String toEmailID = toEmailIdsList[i];
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailID));
			}
//			if (markSelfCopy == true)
//			{
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
//			}
			// Set Subject: header field
			message.setSubject(subject);
			String html = emailMessage;
			// Now set the actual message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			// message.setText(body, "UTF-8", "html");
			// messageBodyPart.setText(body, "UTF-8", "html");
			// MimeBodyPart textPart = new MimeBodyPart();
			// textPart.setText( text, "utf-8" );
			Multipart multipart = new MimeMultipart("related");
			// multipart.addBodyPart(messageBodyPart);
			// multipart.addBodyPart(textPart);
			if (!emailFormat.equals("ATTACHMENT"))
			{
				MimeBodyPart htmlPart = new MimeBodyPart();
				htmlPart.setContent(html, "text/html; charset=utf-8");
				multipart.addBodyPart(htmlPart);
			}
			// JsonArray attachmentFilesList = new JsonArray();
			if (isAttachment == 1 && attachmentFilesList != null)
			{
				for (int i = 0; i < attachmentFilesList.size(); i++)
				{
					JsonObject attachmentFileInfo = attachmentFilesList.get(i).getAsJsonObject();
					String filePath = attachmentFileInfo.get("filePath").getAsString();
					String fileName = "";
					if (attachmentFileInfo.has("fileDisplayName") && attachmentFileInfo.get("fileDisplayName").isJsonNull() == false)
					{
						fileName = attachmentFileInfo.get("fileDisplayName").getAsString();
					}
					int isInlineAttachment = 0;
					if (attachmentFileInfo.has("isInlineAttachment"))
					{
						isInlineAttachment = attachmentFileInfo.get("isInlineAttachment").getAsInt();
					}
					if (isInlineAttachment == 1)
					{
						MimeBodyPart imagePart = new MimeBodyPart();
						imagePart.attachFile(filePath);
						imagePart.setContentID("<" + (i + 1) + ">");
						imagePart.setDisposition(MimeBodyPart.INLINE);
						multipart.addBodyPart(imagePart);
					}
					else
					{
						messageBodyPart = new MimeBodyPart();
						String attachmentPath = filePath;
						String file = attachmentPath;
						javax.activation.DataSource source = new FileDataSource(file);
						messageBodyPart.setDataHandler(new DataHandler(source));
						if (fileName == null || fileName.length() == 0)
						{
							File f = new File(attachmentPath);
							fileName = f.getName();
						}
						messageBodyPart.setFileName(fileName);
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}
			message.setContent(multipart);
			message.saveChanges();
			// Send message
			Transport.send(message);
			/*
			 * Transport trnsport; trnsport = session.getTransport("smtps");
			 * trnsport.connect(); trnsport.send(message); trnsport.close();
			 */
		}
		catch (MessagingException mex)
		{
			logger.error(CommonUtil.getStackTrace(mex));
			return 0;
		}
		return 1;
	}
	// public static JsonObject sendSMS(Connection storeConn, Map<String, String> paramsMap) throws Exception
	public static JsonObject sendSMS(Connection storeConn, String[] toNos, String message) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		dataObject.addProperty("isRequestHandled", 1);
		try
		{
			// toNos = { "919440229402", "918247071955" };
			// String message = "Hi, Hello, How are You";
			String isLiveDataUnderUseForDevelopment = CommonUtil.getPropertyValue("IS_LIVE_DATA_UNDER_USE_FOR_DEVELOPEMENT");
			String notificationTestContactNo = CommonUtil.getPropertyValue("NOTIFICATION_TEST_CONTACT_NO");
			if(isLiveDataUnderUseForDevelopment.trim().length() > 0)
			{
				if(isLiveDataUnderUseForDevelopment.equalsIgnoreCase("1"))
				{					
					boolean isValidPhoneNumber = isValidPhoneNumver(notificationTestContactNo);
					if(isValidPhoneNumber==false)
					{
						dataObject.addProperty("success", 0);
						dataObject.addProperty("alert", "Invalid phone number");
						return dataObject;
					}
					toNos = new String[1];
					toNos[0] = notificationTestContactNo;
				}
			}	
			String userName = "";
			String password = "";
			String senderId = "TXTLCL";
			String smsAPIUrl = "https://api.textlocal.in/send/?apikey=ulsBanJbEo8-0ugTMDsN8L3ThCrqmebELgPXDDdaiG&";
			String smsAPIUrlParameters = "user=%USERNAME%&password=%PASSWORD%&message=%MESSAGE%&sender=%SENDERID%&numbers=%MOBILENOS%";
			String mobileNosDelimiter = ",";
			String useCountryCodeAsPrefix = "YES";
			int mailSent = sendSMS(toNos, message, userName, password, senderId, smsAPIUrl, smsAPIUrlParameters, mobileNosDelimiter, useCountryCodeAsPrefix, dataObject);
			if (mailSent == 1)
			{
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", "SMS sent successfully");
				return dataObject;
			}
			else
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", dataObject.get("smsErrorMsg").getAsString());
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(CommonUtil.class, e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "SMS could not be sent.");
		return dataObject;
	}
	public static int sendSMS(String[] toNos, String message, String userName, String password, String senderId, String smsAPIUrl, String smsAPIUrlParameters, String mobileNosDelimiter, String useCountryCodeAsPrefix, JsonObject retValObject) throws Exception
	{
		try
		{
			URL obj = new URL(smsAPIUrl);
			URLConnection con = obj.openConnection();
			smsAPIUrlParameters = smsAPIUrlParameters.replaceAll("%USERNAME%", userName);
			smsAPIUrlParameters = smsAPIUrlParameters.replaceAll("%PASSWORD%", password);
			smsAPIUrlParameters = smsAPIUrlParameters.replaceAll("%SENDERID%", senderId);
			String toNosString = "";
			int useCountryCode = 0;
			if ("YES".equalsIgnoreCase(useCountryCodeAsPrefix))
			{
				useCountryCode = 1;
			}
			for (int i = 0; i < toNos.length; i++)
			{
				String toNo = toNos[i];
				if (i > 0)
				{
					toNosString += mobileNosDelimiter;
				}
				toNo = toNo.replaceAll("\\+", "");
//				if (useCountryCode == 1)
				if (useCountryCode == 0)
				{
					toNo = toNo.substring(2);
				}
				toNosString += toNo;
			}
			smsAPIUrlParameters = smsAPIUrlParameters.replaceAll("%MOBILENOS%", toNosString);
			smsAPIUrlParameters = smsAPIUrlParameters.replaceAll("%MESSAGE%", message);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(smsAPIUrlParameters);
			wr.flush();
			wr.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();
			// toChange
			JsonObject responseObject = new Gson().fromJson(response.toString(), JsonObject.class);
			String responseKeyName = "status";
			String successResponseValue = "success";
			int responseCode = -1;
			if (smsAPIUrlParameters.startsWith("https"))
			{
				responseCode = ((HttpsURLConnection) con).getResponseCode();
			}
			else
			{
				responseCode = ((HttpURLConnection) con).getResponseCode();
			}
			if (responseCode == 200)
			{
				if (responseObject.get(responseKeyName).getAsString().equals(successResponseValue))
				{
					logger.debug(response.toString());
					return 1;
				}
				else
				{
					retValObject.addProperty("smsErrorMsg", "SMS request could not be sent");
					return 0;
				}
			}
			else
			{
				retValObject.addProperty("smsErrorMsg", "SMS request could not be sent");
				return 0;
			}
		}
		catch (Exception e)
		{
			logger.error(CommonUtil.getStackTrace(e));
			retValObject.addProperty("smsErrorMsg", "SMS could not be sent");
			return 0;
		}
	}
	public static String preparePlaceHolders(int length)
	{
		StringBuilder builder = new StringBuilder(length * 2 - 1);
		for (int i = 0; i < length; i++)
		{
			if (i > 0)
			{
				builder.append(',');
			}
			builder.append('?');
		}
		return builder.toString();
	}
	public static void setSqlQueryValues(PreparedStatement preparedStatement, Object[] values, int offset) throws Exception
	{
		if (values == null)
		{
			return;
		}
		for (int i = 0; i < values.length; i++)
		{
			preparedStatement.setObject(i + 1 + offset, values[i]);
		}
	}
	public static String getDateInRegularDateFormat(Date date)
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	public static String getDateInRegularDateTimeFormat(Date date)
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(date);
	}
	public static String getDateInRegularDateTimeStampFormat(Date date)
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	public static String getTimeInRegularTimeFormat(Time time)
	{
		if (time == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(time);
	}
	public static String getTimeInRegular12HoursFormat(Time time)
	{
		if (time == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		return dateFormat.format(time);
	}
	public static String getTimeInRegular12HoursFormatWithSeconds(Time time)
	{
		if (time == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		return dateFormat.format(time);
	}
	public static String getNumberToTime(Integer iTime)
	{
		String sTime = "";
		if(iTime == null)
		{
			return sTime;
		}
		sTime = iTime.toString();
		Integer iHowManyToAdd = 9 - sTime.length();
		sTime = StringUtils.leftPad(sTime, sTime.length() + iHowManyToAdd, "0");
		String sTimeHH = sTime.substring(0, 2);
		String sTimeMM = sTime.substring(2, 4);
		String sTimeSS = sTime.substring(4, 6);
		String sTimeMMM = sTime.substring(6, 9);
		sTime = sTimeHH + ":" + sTimeMM + ":" + sTimeSS + ":" + sTimeMMM;
		return sTime;
	}
	public static Date getDBFormattedDate(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("dd/MM/yyyy").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static Date getDBFormattedDateTime(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static Date getDBFormattedDateTimeStamp(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static Date getDBFormattedTime(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("HH:mm:ss").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static Date getDBFormattedTimeFrom12HoursFormat(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("hh:mm a").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static Date getDBFormattedTimeFrom12HoursFormatWithSeconds(String date)
	{
		try
		{
			if (date == null)
			{
				return null;
			}
			return (new java.text.SimpleDateFormat("hh:mm:ss a").parse(date));
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
    public static String getDBFormattedUTCTime(Date date) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
	public static final String TIME_FORMAT_12HOURS_REGEX = "(1[012]|0?[1-9]):[0-5][0-9](\\s)*(?i)(am|pm)";
	public static final String TIME_FORMAT_12HOURS_WITH_SEC_REGEX = "(1[012]|0?[1-9]):[0-5][0-9]:[0-5][0-9](\\s)*(?i)(am|pm)";
	public static boolean isValid12HoursFormatTime(String time)
	{
		boolean validTime = false;
		Pattern pattern = Pattern.compile(TIME_FORMAT_12HOURS_REGEX);
		Matcher matcher = pattern.matcher(time);
		if(matcher.matches())
		{
			validTime = true;
		}
		return validTime;
	}
	public static boolean isValid12HoursFormatTimeWithSeconds(String time)
	{
		boolean validTime = false;
		Pattern pattern = Pattern.compile(TIME_FORMAT_12HOURS_WITH_SEC_REGEX);
		Matcher matcher = pattern.matcher(time);
		if(matcher.matches())
		{
			validTime = true;
		}
		return validTime;
	}
	public static String getTimeFrom12HoursFormat(String time)
	{
		try
		{
			 time = time.replaceAll("\\s", ""); 
			 SimpleDateFormat df = new SimpleDateFormat("hh:mma");
			 SimpleDateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
	    	 Date date= df.parse(time);
	    	 return outputformat.format(date);
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static String getTimeFrom12HoursFormatWithSeconds(String time)
	{
		try
		{
			 time = time.replaceAll("\\s", ""); 
			 SimpleDateFormat df = new SimpleDateFormat("hh:mm:ssa");
			 SimpleDateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
	    	 Date date= df.parse(time);
	    	 return outputformat.format(date);
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static String getDatabaseName(Session session)
	{
		try
		{
			SessionImpl sessionIml = (SessionImpl) session;
			String URL = sessionIml.connection().getMetaData().getURL();
			String[] URLSplit = URL.split("/");
			return URLSplit[URLSplit.length - 1];
		}
		catch (Exception e)
		{
			writeTolog(e);
			return null;
		}
	}
	public static JsonObject getBranchInfo(Session session, int branchInfoId) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject inputForGetAPI = new JsonObject();
			inputForGetAPI.addProperty("branchInfoId", branchInfoId);
			JsonObject requestParametersInfo = new JsonObject();
			requestParametersInfo.add("inputForGetAPI", inputForGetAPI);
			requestParametersInfo.addProperty("DBName", CommonUtil.getDatabaseName(session));
			
			
			
			
			JsonObject inputDataObject = new JsonObject();
			
			
			
			return inputDataObject;
		}
		catch (Exception e)
		{
			writeTolog(e);
			dataObject.addProperty("alert", "Your request could not be processed as Data from api could not be retrieved");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public static String getReportsFolderPath()
	{
		
		
		
		
		String sProject = ".." + File.separatorChar + "DigiXOffice360MS" + File.separatorChar + "Reports" + File.separatorChar + "";
		return sProject;
		
		
	}
	public static Integer getLoginBranchIdFromRequest(HttpServletRequest request) throws Exception
	{
		try
		{
			String loginbranchidString = request.getParameter("loginbranchid");
			if (loginbranchidString != null && loginbranchidString.length() > 0)
			{
				return Integer.parseInt(loginbranchidString.trim());
			}
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return -1;
	}
	public static Integer getEmployeeIdFromRequest(HttpServletRequest request) throws Exception
	{
		try
		{
			String employeeidString = request.getParameter("employeeid");
			if (employeeidString != null && employeeidString.length() > 0)
			{
				return Integer.parseInt(employeeidString.trim());
			}
		}
		catch (Exception e)
		{
			writeTolog(e);
		}
		return -1;
	}
	public static void runSqlCommand(String sqlCommand) throws Exception
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		try
		{
			conn = getConnection("testdb");
			stmt = conn.prepareStatement(sqlCommand);
			stmt.executeUpdate();
			System.out.println("command execution completed");
			conn.commit();
		}
		catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
		finally
		{
			stmt.close();
			conn.close();
		}
	}
	public static void runSqlScript(String scriptFilePath, String databaseName) throws Exception
	{
		Connection conn = null;
		try
		{
			conn = getConnection(databaseName);
			conn.setAutoCommit(false);
			ScriptRunner sr = new ScriptRunner(conn);
			sr.setAutoCommit(false);
			sr.setStopOnError(true);			
			InputStream is = CommonUtil.class.getResourceAsStream(scriptFilePath);
			Reader reader = new InputStreamReader(is);	
			sr.runScript(reader);		
			conn.rollback();
			sr.closeConnection();
		}
		catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
		finally
		{
			conn.close();
		}
	}
    public static final String HTTP_CONTENT_TYPE_APPLICATION_EXCEL = "application/vnd.ms-excel";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_JSON = "application/json";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_PDF = "application/pdf";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_ZIP_COMPRESSED = "application/x-zip-compressed";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_ZIP = "application/zip";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String HTTP_CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String HTTP_CONTENT_TYPE_CSS = "text/css";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_TEXT = "text/plain";
    public static final String HTTP_CONTENT_TYPE_APPLICATION_SQL = "application/sql";
    public static final String HTTP_CONTENT_TYPE_TEXT_X_SQL = "text/x-sql";
    public static final String HTTP_RESPONSE_TYPE = "HTTP_RESPONSE_TYPE";
    public static final String HTTP_CONTENT_TYPE_FILE = "file";
    public static final String HTTP_CONTENT_TYPE_TEXT = "text";
    public static final String HTTP_CONTENT_TYPE_EXCEL = "excel";
    public static final String HTTP_CONTENT_TYPE_ZIP = "zip";
    public static final String HTTP_CONTENT_TYPE_SQL = "sql";
    public static final String HTTP_CONTENT_TYPE_IMAGE_GIF = "image/gif";
    public static final String HTTP_CONTENT_TYPE_IMAGE_PNG = "image/png";
    public static final String HTTP_CONTENT_TYPE_IMAGE_JPG = "image/jpg";
    public static final String HTTP_CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_TYPE_GIF = "GIF";
    public static final String IMAGE_TYPE_PNG = "PNG";
    public static final String IMAGE_TYPE_JPG = "JPG";
    public static final String IMAGE_TYPE_JPEG = "JPEG";
    public static final String FILE_EXTENSION_TYPE_TEXT = "txt";
    public static final String FILE_EXTENSION_TYPE_PDF = "pdf";
    public static final String FILE_EXTENSION_TYPE_SQL = "sql";
    public static final String FILE_EXTENSION_TYPE_XLS = "xls";
    public static final String FILE_EXTENSION_TYPE_ZIP = "zip";
    public static String getFileContentTypeFromExtension(String fileName)
    {
        String fileType = null;
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
        if (fileExtension != null && fileExtension.length() > 0)
        {
            if (IMAGE_TYPE_JPG.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_IMAGE_JPG.toLowerCase();
            }
            else if (IMAGE_TYPE_JPEG.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_IMAGE_JPEG.toLowerCase();
            }
            else if (IMAGE_TYPE_GIF.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_IMAGE_GIF.toLowerCase();
            }
            else if (IMAGE_TYPE_PNG.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_IMAGE_PNG.toLowerCase();
            }
            else if (FILE_EXTENSION_TYPE_TEXT.equalsIgnoreCase(fileExtension) || FILE_EXTENSION_TYPE_SQL.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_APPLICATION_TEXT.toLowerCase();
            }
            else if (FILE_EXTENSION_TYPE_PDF.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_APPLICATION_PDF.toLowerCase();
            }
            else if (FILE_EXTENSION_TYPE_ZIP.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_APPLICATION_ZIP.toLowerCase();
            }
            else if (FILE_EXTENSION_TYPE_XLS.equalsIgnoreCase(fileExtension))
            {
                fileType = HTTP_CONTENT_TYPE_APPLICATION_EXCEL.toLowerCase();
            }
        }
        return fileType;
    }
    public static String getFileNameForUpload(String fileName, int randomNumber) throws Exception
    {    	
		if (fileName.lastIndexOf("\\") >= 0)
		{
			fileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\"));
		}
		else
		{
			fileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\") + 1);
		}
		//String savedFileName = filePath + fileName;
		String savedFileName = fileName;
		if(randomNumber>0)
		{
			savedFileName += "_" + String.valueOf(randomNumber);
		}    	
		return savedFileName;
    }
	public static int isLoginIdRegistered(Session session, JsonObject userSessionInfo, String loginEmailId)
	{
		int isLoginIdRegistered = 0;
		
		return CommonUtil.isUserInfoLoginIdRegistered(session, loginEmailId);
	}
	public static int isUserInfoLoginIdRegistered(Session session, String loginId)
	{
		Transaction tx = session.getTransaction();
		try
		{
			if (!tx.isActive())
			{
				tx.begin();
			}
			String countHql = "select count(*)  from UserInfo where loginId = :loginId ";
			Query countQuery = session.createQuery(countHql);
			countQuery.setParameter("loginId", loginId);
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount > 0)
			{
				return 1;
			}
			return 0;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 1;
	}
	public static String getRandomNo()
	{
		Random rand = new Random();
		return String.valueOf(rand.nextInt(900000)+100000);
	}
	public static int isSingleColumnQuery(List recordsList)
	{
		Iterator iterator = recordsList.iterator(); 
		if(iterator.hasNext())
		{
			Class cls = iterator.next().getClass();
			String classType = cls.getName();	    	
	    	if("[Ljava.lang.Object;".startsWith(classType))
	    	{
	    		return 0;
	    	}	
		}		
    	return 1;
	}
	public static JsonObject saveFileUploadInputFile(String filePath, String fileName, Session session)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String newFileName = "";
			if (fileName.lastIndexOf("\\") >= 0)
			{
				newFileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\"));
			}
			else
			{
				newFileName = CommonUtil.getFileNameByCurrentTime() + "_" + fileName.substring(fileName.lastIndexOf("\\") + 1);
			}
			String savedFileName = filePath + File.separatorChar+ fileName;
			int fileId = CommonUtil.saveFile(newFileName, savedFileName, session);
			if(fileId < 0)
			{
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			dataObject.addProperty("success", 1);
			dataObject.addProperty("fileName", fileName);
			return dataObject;
		} 
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public static int saveFile(String fileName, String filePath,  Session masterSession)
	{
		Blob blob = null;
		try
		{
			Transaction tx = masterSession.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
	        FileUpload fileUpload = new FileUpload();
	        File file = new File(filePath);
	        FileInputStream inputStream = new FileInputStream(file);
	        blob = Hibernate.getLobCreator(masterSession).createBlob(inputStream, file.length());
	        fileUpload.setFileName(fileName);
	        fileUpload.setFileBlob(blob);
	        int fileId = (Integer) masterSession.save(fileUpload);
	        blob.free();
	        return fileId;
		} 
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			return -1;
		}
	}
	public static String getFilePath(String fileName, int fileId,  Session masterSession)
	{
		String filePath = "";
		try
		{
			String hql = "From FileUpload where 1 = 1  ";
			String fileIdFilterClause = "";
			String fileNameFilterClause = "";
			if(fileId > 0)
			{
				fileIdFilterClause = " AND fileUploadId = :fileUploadId ";
			}
			else 
			{
				fileNameFilterClause = " AND fileName = :fileName ";
			}
			hql += fileIdFilterClause+fileNameFilterClause;
			Query query = masterSession.createQuery(hql);
			if(fileId > 0)
			{
				query.setParameter("fileUploadId", fileId);
			}
			else
			{
				query.setParameter("fileName", fileName);
			}
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				FileUpload fileUpload = (FileUpload) it.next();
				String filesPath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
				String resultFileName = fileUpload.getFileName();
				filePath = filesPath + resultFileName; 
		        Blob blob = fileUpload.getFileBlob();
		        byte[] blobBytes = blob.getBytes(1, (int) blob.length());
		        FileOutputStream outputStream = new FileOutputStream(filePath);
		        outputStream.write(blobBytes);
		        outputStream.close();
		        blob.free();
			}
			return filePath;
		} 
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			return "";
		}
	}
    public static String getHash(String input) throws Exception
    {
        if (input == null)
        {
            input = "";
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(input.getBytes("US-ASCII"));
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	public static boolean isValidPhoneNumver(String phoneNumber)
	{
		// The given argument to compile() method
		// is regular expression. With the help of
		// regular expression we can validate mobile
		// number.
		// 1) Begins with 0 or 91
		// 2) Then contains 4 or 5 or 6 0r 7 or 8 or 9.
		// 3) Then contains 9 digits
		Pattern p = Pattern.compile("(0|91)?[4-9][0-9]{9}");
		// Pattern class contains matcher() method
		// to find matching between given number
		// and regular expression
		Matcher m = p.matcher(phoneNumber);
		return (m.find() && m.group().equals(phoneNumber));
	}
}
