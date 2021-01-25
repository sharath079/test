package com.patientapp.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.SettingsUtil;
public class VWTCoreCommonUtil
{
	static String mProjectDirectory = "";
	static Properties configProperties;
	static boolean isInitialized = false;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VWTCoreCommonUtil.class);
	public static void writeToLog(Class currentClass, Exception e)
	{
		String msg = getStackTrace(e);
		System.out.println(msg);
	}
	public static void writeTolog(Class currentClass, Exception e)
	{
		String msg = getStackTrace(e);
		System.out.println(msg);
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
	public static String getStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	public static Connection getConnection() throws Exception
	{
		// DataSource dbSource = getDataSource();
		// Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":" + 3306 + "/" + CommonUtil.getMasterDBName() + "_1", "root", "root1234");
		// Connection conn = dbSource.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	public static String getProjectDirectory()
	{
		return SettingsUtil.getProjectDirectory();
	}
	public static String getPropertyValue(String propertyName)
	{
		loadConfigProperties();
		Properties configProperties = getConfigProperties();
		return configProperties.getProperty(propertyName);
	}
	public static Properties getConfigProperties()
	{
		return configProperties;
	}
	public static void setConfigProperties(Properties configProperties)
	{
		configProperties = configProperties;
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
			e.printStackTrace();
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
	public static String getDateInRegularDateFormat(Date date)
	{
		if (date == null)
		{
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
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
	public static void loadConfigProperties()
	{
		if (isInitialized == true)
		{
			return;
		}
		InputStream input = null;
		configProperties = new Properties();
		try
		{
			String filename = "/resources/config.properties";
			input = VWTCoreCommonUtil.class.getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Unable to find " + filename + " in the classpath");
				return;
			}
			configProperties.load(input);
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
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			throw e;
		}
		finally
		{
			VWTCoreCommonUtil.closeResultSet(entitiesCountResultSet);
			VWTCoreCommonUtil.closeStatement(selectEntitiesCountStmt);
		}
		return false;
	}
	public static String getTextForThermalPrinting(JsonObject elementPropertiesInfo, String printText)
	{
		StringBuilder prefixText = new StringBuilder();
		StringBuilder suffixText = new StringBuilder();
		String modifiedPrintText = printText;
		// JsonObject elementPropertiesInfo =
		// htmlElementInfo.get("elementPropertiesInfo").getAsJsonObject();
		String totalCharacterCountText = elementPropertiesInfo.get("totalCharacterCount").getAsJsonObject().get("value").getAsString();
		String lineAlignmentText = elementPropertiesInfo.get("alignment").getAsJsonObject().get("value").getAsString();
		String newLinesBeforeText = elementPropertiesInfo.get("newLinesBefore").getAsJsonObject().get("value").getAsString();
		String newLinesAfterText = elementPropertiesInfo.get("newLinesAfter").getAsJsonObject().get("value").getAsString();
		String emptySpacesOnLeftText = elementPropertiesInfo.get("emptySpacesOnLeft").getAsJsonObject().get("value").getAsString();
		String emptySpacesOnRightText = elementPropertiesInfo.get("emptySpacesOnRight").getAsJsonObject().get("value").getAsString();
		String emptySpacesInsideText = elementPropertiesInfo.get("emptySpacesInside").getAsJsonObject().get("value").getAsString();
		String fontWeight = elementPropertiesInfo.get("fonWeight").getAsJsonObject().get("value").getAsString();
		String textAlignment = elementPropertiesInfo.get("horizantalAlignment").getAsJsonObject().get("value").getAsString();
		int totalCharacterCount = 0;
		try
		{
			totalCharacterCount = Integer.parseInt(totalCharacterCountText);
		}
		catch (Exception e)
		{
		}
		int lineAlignment = 0;
		try
		{
			lineAlignment = Integer.parseInt(lineAlignmentText);
		}
		catch (Exception e)
		{
		}
		int newLinesBefore = 0;
		try
		{
			newLinesBefore = Integer.parseInt(newLinesBeforeText);
		}
		catch (Exception e)
		{
		}
		int newLinesAfter = 0;
		try
		{
			newLinesAfter = Integer.parseInt(newLinesAfterText);
		}
		catch (Exception e)
		{
		}
		int emptySpacesOnLeft = 0;
		try
		{
			emptySpacesOnLeft = Integer.parseInt(emptySpacesOnLeftText);
		}
		catch (Exception e)
		{
		}
		int emptySpacesOnRight = 0;
		try
		{
			emptySpacesOnRight = Integer.parseInt(emptySpacesOnRightText);
		}
		catch (Exception e)
		{
		}
		int emptySpacesInside = 0;
		try
		{
			emptySpacesInside = Integer.parseInt(emptySpacesInsideText);
		}
		catch (Exception e)
		{
		}
		if (newLinesBefore > 0)
		{
			for (int i = 1; i <= newLinesBefore; i++)
			{
				prefixText.append("\n");
			}
		}
		if (emptySpacesOnLeft > 0)
		{
			for (int i = 1; i <= emptySpacesOnLeft; i++)
			{
				prefixText.append(" ");
			}
		}
		// cut the String if string length exceeds the total characters count
		if (totalCharacterCount <= printText.length() && totalCharacterCount > 0)
		{
			modifiedPrintText = modifiedPrintText.substring(0, totalCharacterCount);
		}
		else
		{
			if (emptySpacesOnRight > 0)
			{
				for (int i = 1; i <= emptySpacesOnRight; i++)
				{
					suffixText.append(" ");
				}
			}
		}
//        if (emptySpacesOnRight > 0)
//        {
//            for (int i = 1; i <= emptySpacesOnRight; i++)
//            {
//                suffixText.append(" ");
//            }
//        }
		if (newLinesAfter > 0)
		{
			for (int i = 1; i <= newLinesAfter; i++)
			{
				suffixText.append("\n");
			}
		}
		if (totalCharacterCount > 0)
		{
			int spacesCountToPad = 0;
			int printTextLength = printText.length();
			if (printTextLength < totalCharacterCount)
			{
				spacesCountToPad = totalCharacterCount - printTextLength;
			}
			if (spacesCountToPad > 0)
			{
				int spacesToPadOnLeft = 0;
				int spacesToPadOnRight = 0;
				if ("right".equalsIgnoreCase(textAlignment))
				{
					spacesToPadOnLeft = spacesCountToPad;
				}
				else if ("center".equalsIgnoreCase(textAlignment))
				{
					spacesToPadOnLeft = spacesCountToPad / 2;
					spacesToPadOnRight = spacesCountToPad - spacesToPadOnLeft;
				}
				else
				{
					spacesToPadOnRight = spacesCountToPad;
				}
				StringBuilder spacesPaddedPrintText = new StringBuilder();
				for (int i = 1; i <= spacesToPadOnLeft; i++)
				{
					spacesPaddedPrintText.append(" ");
				}
				spacesPaddedPrintText.append(printText);
				for (int i = 1; i <= spacesToPadOnRight; i++)
				{
					spacesPaddedPrintText.append(" ");
				}
				modifiedPrintText = spacesPaddedPrintText.substring(0);
			}
		}
		String alignmentText = "";
		String fontWeightPrefixText = "";
		String fontWeightSuffixText = "";
		if ("bold".equalsIgnoreCase(fontWeight))
		{
			fontWeightPrefixText = "%BON%";
			fontWeightSuffixText = "%BOFF%";
		}
		if (lineAlignment == 1)
		{
			alignmentText = "%AL%";
		}
		else if (lineAlignment == 2)
		{
			alignmentText = "%AC%";
		}
		else if (lineAlignment == 3)
		{
			alignmentText = "%AR%";
		}
		modifiedPrintText = alignmentText + fontWeightPrefixText + prefixText + modifiedPrintText + suffixText + fontWeightSuffixText;
		return modifiedPrintText;
	}
	public static String escapeHTMLText(String htmlText)
	{
		if (htmlText == null || htmlText.length() == 0)
		{
			return htmlText;
		}
		return htmlText.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&#039;");
	}
	public static String getNumberRounded(double num, int scale)
	{
		NumberFormat format = DecimalFormat.getInstance();
		format.setRoundingMode(RoundingMode.FLOOR);
		format.setMinimumFractionDigits(scale);
		format.setMaximumFractionDigits(scale);
		// num = 34567.789;
		return format.format(num).replaceAll(",", "");
	}
	public static String getNumberInWords(String numberString)
	{
		Double num = 0.0;
		try
		{
			num = Double.parseDouble(numberString);
		}
		catch (Exception e)
		{
			return numberString;
		}
		int integerValue = num.intValue();
		String numberInWords = "";
		String integerValueInWords = getNumberInWords(integerValue);
		numberInWords = integerValueInWords;
		if (numberInWords.length() > 0)
		{
			numberInWords += " Rupees ";
		}
		String decimalValueInWords = "";
		int decimalIntValue = 0;
		if (numberString.indexOf(".") > 0)
		{
			int decimalStartIndex = numberString.indexOf(".") + 1;
			if (numberString.length() > decimalStartIndex + 1)
			{
				decimalIntValue = Integer.parseInt(numberString.substring(decimalStartIndex));
				decimalValueInWords = getNumberInWords(decimalIntValue);
				if (decimalValueInWords.length() > 0)
				{
					numberInWords += " " + decimalValueInWords + " Paisa";
				}
			}
		}
		numberInWords += " Only .";
		return numberInWords;
	}
	public static String getNumberInWords(int number)
	{
		String numberString = String.valueOf(number);
		StringBuilder reverseNumberString = new StringBuilder();
		StringBuilder numberInWords = new StringBuilder();
		for (int i = 0; i < numberString.length(); i++)
		{
			reverseNumberString.append(numberString.charAt((numberString.length() - 1) - i));
		}
		for (int i = 0; i < reverseNumberString.length(); i++)
		{
			int digitIndex = i + 1; // whether the digit represents units/tens/hundreds etc
			if (digitIndex == 1 || digitIndex == 2)
			{
				int twoDigitNumber = 0;
				if (digitIndex == 1)
				{
					if (reverseNumberString.length() > i + 1)
					{
						continue;
					}
					else
					{
						twoDigitNumber = Integer.parseInt(reverseNumberString.substring(i, i + 1));
					}
				}
				else
				{
					char firstDigit = reverseNumberString.charAt(i - 1);
					char secondDigit = reverseNumberString.charAt(i);
					StringBuilder twoDigitNumberString = new StringBuilder();
					twoDigitNumberString.append(secondDigit);
					twoDigitNumberString.append(firstDigit);
					twoDigitNumber = Integer.parseInt(twoDigitNumberString.substring(0, 2));
				}
				String twoDigitNumberInWords = get2DigitNumberInWords(twoDigitNumber);
				numberInWords.append(twoDigitNumberInWords);
			}
			else if (digitIndex == 3)
			{
				int twoDigitNumber = 0;
				twoDigitNumber = Integer.parseInt(reverseNumberString.substring(i, i + 1));
				String twoDigitNumberInWords = get2DigitNumberInWords(twoDigitNumber);
				if (twoDigitNumberInWords.length() > 0)
				{
					numberInWords.insert(0, twoDigitNumberInWords + " Hundreds ");
				}
			}
			else if (digitIndex == 4 || digitIndex == 5)
			{
				int twoDigitNumber = 0;
				if (digitIndex == 4)
				{
					if (reverseNumberString.length() > i + 1)
					{
						continue;
					}
					else
					{
						twoDigitNumber = Integer.parseInt(reverseNumberString.substring(i, i + 1));
					}
				}
				else
				{
					char firstDigit = reverseNumberString.charAt(i - 1);
					char secondDigit = reverseNumberString.charAt(i);
					StringBuilder twoDigitNumberString = new StringBuilder();
					twoDigitNumberString.append(secondDigit);
					twoDigitNumberString.append(firstDigit);
					twoDigitNumber = Integer.parseInt(twoDigitNumberString.substring(0, 2));
				}
				String twoDigitNumberInWords = get2DigitNumberInWords(twoDigitNumber);
				if (twoDigitNumberInWords.length() > 0)
				{
					numberInWords.insert(0, twoDigitNumberInWords + " Thousands ");
				}
			}
			else if (digitIndex == 6 || digitIndex == 7)
			{
				int twoDigitNumber = 0;
				if (digitIndex == 6)
				{
					if (reverseNumberString.length() > i + 1)
					{
						continue;
					}
					else
					{
						twoDigitNumber = Integer.parseInt(reverseNumberString.substring(i, i + 1));
					}
				}
				else
				{
					char firstDigit = reverseNumberString.charAt(i - 1);
					char secondDigit = reverseNumberString.charAt(i);
					StringBuilder twoDigitNumberString = new StringBuilder();
					twoDigitNumberString.append(secondDigit);
					twoDigitNumberString.append(firstDigit);
					twoDigitNumber = Integer.parseInt(twoDigitNumberString.substring(0, 2));
				}
				String twoDigitNumberInWords = get2DigitNumberInWords(twoDigitNumber);
				if (twoDigitNumberInWords.length() > 0)
				{
					numberInWords.insert(0, twoDigitNumberInWords + " Lakhs ");
				}
			}
			else
			{
				// int remainingNumber = 0;
				String remainingNumberString = reverseNumberString.substring(i);
				String remainingNumberStringInCorrectOrder = getReverseString(remainingNumberString);
				String remainingNumberInWords = getNumberInWords(Integer.parseInt(remainingNumberStringInCorrectOrder));
				if (remainingNumberInWords.length() > 0)
				{
					numberInWords.insert(0, remainingNumberInWords + " Crores ");
				}
				break;
			}
		}
		return numberInWords.substring(0);
	}
	public static String get2DigitNumberInWords(int twoDigitNumber)
	{
		if (twoDigitNumber >= 0 && twoDigitNumber < 20)
		{
			if (twoDigitNumber == 0)
			{
				return "";
			}
			else if (twoDigitNumber == 1)
			{
				return "One";
			}
			else if (twoDigitNumber == 2)
			{
				return "Two";
			}
			else if (twoDigitNumber == 3)
			{
				return "Three";
			}
			else if (twoDigitNumber == 4)
			{
				return "Four";
			}
			else if (twoDigitNumber == 5)
			{
				return "Five";
			}
			else if (twoDigitNumber == 6)
			{
				return "Six";
			}
			else if (twoDigitNumber == 7)
			{
				return "Seven";
			}
			else if (twoDigitNumber == 8)
			{
				return "Eight";
			}
			else if (twoDigitNumber == 9)
			{
				return "Nine";
			}
			else if (twoDigitNumber == 10)
			{
				return "Ten";
			}
			else if (twoDigitNumber == 11)
			{
				return "Eleven";
			}
			else if (twoDigitNumber == 12)
			{
				return "Twelve";
			}
			else if (twoDigitNumber == 13)
			{
				return "Thirteen";
			}
			else if (twoDigitNumber == 14)
			{
				return "Fourteen";
			}
			else if (twoDigitNumber == 15)
			{
				return "Fifteen";
			}
			else if (twoDigitNumber == 16)
			{
				return "Sixteen";
			}
			else if (twoDigitNumber == 17)
			{
				return "Seventeen";
			}
			else if (twoDigitNumber == 18)
			{
				return "Eighteen";
			}
			else if (twoDigitNumber == 19)
			{
				return "Nineteen";
			}
		}
		else
		{
			int tensDigit = twoDigitNumber / 10;
			int unitsDigit = twoDigitNumber % 10;
			String unitsDigitInWords = "";
			String tensDigitInWords = "";
			if (tensDigit == 2)
			{
				tensDigitInWords = "Twenty";
			}
			else if (tensDigit == 3)
			{
				tensDigitInWords = "Thirty";
			}
			else if (tensDigit == 4)
			{
				tensDigitInWords = "Forty";
			}
			else if (tensDigit == 5)
			{
				tensDigitInWords = "Fifty";
			}
			else if (tensDigit == 6)
			{
				tensDigitInWords = "Sixty";
			}
			else if (tensDigit == 7)
			{
				tensDigitInWords = "Seventy";
			}
			else if (tensDigit == 8)
			{
				tensDigitInWords = "Eighty";
			}
			else if (tensDigit == 9)
			{
				tensDigitInWords = "Ninety";
			}
			if (unitsDigit == 1)
			{
				unitsDigitInWords = "One";
			}
			else if (unitsDigit == 2)
			{
				unitsDigitInWords = "Two";
			}
			else if (unitsDigit == 3)
			{
				unitsDigitInWords = "Three";
			}
			else if (unitsDigit == 4)
			{
				unitsDigitInWords = "Four";
			}
			else if (unitsDigit == 5)
			{
				unitsDigitInWords = "Five";
			}
			else if (unitsDigit == 6)
			{
				unitsDigitInWords = "Six";
			}
			else if (unitsDigit == 7)
			{
				unitsDigitInWords = "Seven";
			}
			else if (unitsDigit == 8)
			{
				unitsDigitInWords = "Eight";
			}
			else if (unitsDigit == 9)
			{
				unitsDigitInWords = "Nine";
			}
			return tensDigitInWords + " " + unitsDigitInWords;
		}
		return "";
	}
	public static String getReverseString(String str)
	{
		StringBuilder reverseString = new StringBuilder();
		for (int i = 0; i < str.length(); i++)
		{
			reverseString.append(str.charAt((str.length() - 1) - i));
		}
		return reverseString.substring(0);
	}
	public static int getMatchingEntityId(Session organisationSession, String selectQuery, Object... queryParams) throws Exception
	{
		try
		{
			Query query = organisationSession.createSQLQuery(selectQuery);
			for (int i = 0; i < queryParams.length; i++)
			{
				query.setParameter(i, queryParams[i]);
			}
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			if (iterator.hasNext())
			{
				Object[] resultRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultRowColumnsList = new Object[1];
					resultRowColumnsList[0] = (Object) iterator.next();
				}
				int entityId = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
				return entityId;
			}
		}
		catch (Exception e)
		{
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			throw e;
		}
		finally
		{			
		}
		return -1;
	}
}
