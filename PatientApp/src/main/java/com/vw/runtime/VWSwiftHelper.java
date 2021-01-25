package com.vw.runtime;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import com.google.gson.JsonObject;
//import com.re.bean.MessageQueue;
//import com.patientapp.controller.forms.base.MessageQueueLabel;
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
@SuppressWarnings( {"unchecked", "rawtypes","unused"})
public class VWSwiftHelper extends MessageGenieController
{
	private static final String SWIFT_MESSAGE_END = "-}";
	private static final String EMPTY_STRING = "";
	private static final String FORWARD_SLASH = "/";
	private static final String SWIFT_TAG79 = ":79:";
	//private static final String SWIFT_TAG79_RIGHT_COLON = "79:";
	private static final String SWIFT_FIELD_SEPARATOR = ":";
	private static final String SWIFT_DECIMAL_SEPARATOR = ",";
	private static final String DECIMAL_SEPARATOR = ".";
	String sFileContent = "";
	String sParseErr = "";
	String[] sParams = null;
	public VWSwiftHelper(Session session)
	{
		super(session);
		debugCode("Out VWSwiftHelper Constructor : With Session parameter");
	}
	public VWSwiftHelper()
	{
		super();
		debugCode("Out VWSwiftHelper Constructor : With Session parameter");
	}
	public String getParseErrors()
	{
		return sParseErr;
	}
	public void setFileContent(String sContent)
	{
		System.out.println("*****************BEGIN*****************");
		this.sFileContent = sContent;
		System.out.println("*****************END*****************");
	}
	public String getFileContent()
	{
		return this.sFileContent;
	}
	public String getTagValue(String sEntityName, String sTag, String isCompositeField, String sTotalParts, String sPart, String sBIndex, String sEIndex, String sSeparator, String isMandatoryField)
	{
		if (!isExists(sTag))
		{
			return EMPTY_STRING;
		}
		String sTagValue = EMPTY_STRING;
		sTag = handleHeaderTags(sTag);
		sTagValue = handleSingleField(sFileContent, sTag);
		sTagValue = handleCompositeField(sEntityName, sTag, isCompositeField, sTotalParts, sPart, sBIndex, sEIndex, sSeparator, isMandatoryField, sTagValue);
		return sTagValue;
	}
	public String handleHeaderTags(String sTag)
	{
		if(sTag.matches(":1:"))
		{
			return "{1:";
		}
		if(sTag.matches(":2:"))
		{
			return "{2:";
		}
		if(sTag.matches(":5:"))
		{
			return "{5:";
		}
		if(sTag.matches(":6:"))
		{
			return "{6:";
		}
		return sTag;
	}
	public void setTagValuetoSwiftMessage(String sTag,String sTagValue, String isCompositeField, String sTotalParts, String sPart, String sBIndex, String sEIndex, String sSeparator)
	{
		if(!isExists(sTag))
		{
			return;
		}
		if (!isExists(sTagValue))
		{
			if(!Boolean.valueOf(isCompositeField)) 
			{
				return;
			}		
		}
		if (StringUtils.contains(sFileContent,sTag))
		{
			String sCurrentTagValue = handleSingleField(sFileContent,sTag);
			String sCurrentTagBackup = sCurrentTagValue; 
			if (Boolean.valueOf(isCompositeField))
			{
				if (isExists(sSeparator))
				{
					if (sSeparator.matches(NEW_LINE_CHAR))
					{
						sCurrentTagValue = sCurrentTagValue + NEW_LINE_CHAR + sTagValue;
					}
					if (sSeparator.matches(FORWARD_SLASH))
					{
						sCurrentTagValue = sCurrentTagValue + FORWARD_SLASH + sTagValue;
					}	
				}else
				{
					sCurrentTagValue = StringUtils.overlay(sCurrentTagValue, sTagValue, Integer.parseInt(sBIndex), Integer.parseInt(sBIndex)+ Integer.parseInt(sEIndex));
				}
			}
			sFileContent = StringUtils.replace(sFileContent, sTag + sCurrentTagBackup, sTag + sCurrentTagValue);
		}else
		{
			//sFileContent = sFileContent + sTag + sTagValue + NEW_LINE_CHAR;
			String sTagNewLine = NEW_LINE_CHAR;
			if(!isExists(sTagValue))
			{
				if(!Boolean.valueOf(isCompositeField))
				{
					sTagNewLine = "";
				}
			}
			sFileContent = sFileContent + sTag + sTagValue + sTagNewLine;
		}
		handleLastCompositeField(sTag,isCompositeField,sTotalParts,sPart,sSeparator);
	}
	private void handleLastCompositeField(String sTag, String isCompositeField, String sTotalParts, String sPart, String sSeparator)
	{
		if (Boolean.valueOf(isCompositeField))
		{
			if ((sTotalParts==sPart) || (sSeparator.matches("@REPEAT@"))) // NG: the last part of a subfields is being addressed here 
			{
				String sAllPartsOfTagBackup = handleSingleFieldCore(sFileContent, sTag, false);
				if ((isExists(sSeparator) && (!sSeparator.matches("@REPEAT@"))))
				{
					String sAllPartsOfTagAfterTrim = StringUtils.replaceChars(sAllPartsOfTagBackup, sSeparator,"");
					if (!isExists(sAllPartsOfTagAfterTrim)) 
					{
						sFileContent = StringUtils.replace(sFileContent, sTag + sAllPartsOfTagBackup,"");
					}else 
					{	
						if (sTag.contains(":39A:")) 
						{
							String s="";
						}
						String sAllPartsOfTag =stripRight(sAllPartsOfTagBackup, sSeparator);
						if(isExists(sAllPartsOfTag))
						{ 
							sFileContent = StringUtils.replace(sFileContent, sTag + sAllPartsOfTagBackup, sTag + sAllPartsOfTag);
						}else 
						{
							sFileContent = StringUtils.replace(sFileContent, sTag + sAllPartsOfTagBackup, "");
						}
					}	
				}else 
				{
					if (sAllPartsOfTagBackup.matches(NEW_LINE_CHAR))
					{
						sFileContent = StringUtils.replace(sFileContent, sTag + sAllPartsOfTagBackup,"");
					}
				}
			}	
		}
	}
	private String stripRight(String sStr, String sSeparator)
	{
		String sAfterTrimString = "";
		if (sSeparator.contains(NEW_LINE_CHAR)) 
		{
			sAfterTrimString = StringUtils.replace(sStr, NEW_LINE_CHAR, "@NEW@");
			sAfterTrimString = sStr.replaceAll(sSeparator + "+$", "");
			sAfterTrimString = sAfterTrimString + NEW_LINE_CHAR;
		}else
		{		
			String[] allLines = sStr.split("\\" + NEW_LINE_CHAR,-1);
			for(String item: allLines) 
			{
				String sTemp = item;
				sTemp = StringUtils.removeEnd(sTemp," ");
				sTemp = sTemp.replaceAll(sSeparator + "+$", "");
				if(isExists(sAfterTrimString))
				{
					sAfterTrimString = sAfterTrimString + NEW_LINE_CHAR + sTemp;
				}else
				{
					sAfterTrimString = sTemp ;
				}
			}
		}	
		return sAfterTrimString;
	}
	@SuppressWarnings("deprecation")
	private String handleCompositeField(String sEntityName, String sTag, String isCompositeField, String sTotalParts, String sPart, String sBIndex, String sEIndex, String sSeparator, String isMandatoryField, String sTagValue)
	{
		if (Boolean.valueOf(isCompositeField))
		{
			Integer iParts = Integer.parseInt(sPart);
			Integer iTotalParts = Integer.parseInt(sTotalParts);
			if (isExists(sSeparator))
			{
				if (sSeparator.contains("@REPEAT@"))
				{
					if (sPart.contains("0"))
						{
							int maxTimes = StringUtils.countMatches(sFileContent, sTag);
							String[] sAllRepeatTags = StringUtils.substringsBetween(sFileContent, sTag , SWIFT_FIELD_SEPARATOR);
							if (isExists(sAllRepeatTags))
							{
								int maxArrayTimes = sAllRepeatTags.length;
								if (maxTimes > iTotalParts) 
								{
									sParseErr = sParseErr + handleErrors("C71", sTag, sTotalParts);
								}else 
								{	
									sTagValue = StringUtils.concatenate(sAllRepeatTags);
									if (maxTimes != maxArrayTimes)
									{
										String sRemaining = StringUtils.substringAfter(sFileContent, sAllRepeatTags[maxArrayTimes - 1]);
										sTagValue = sTagValue + handleSingleField(sRemaining, sTag);
									}
								}	
							}
						//}
					}
				} else
				{
					//Changed from StringUtils to regex to get blank values also in the array
					String sTagValues[] = sTagValue.split("\\" + sSeparator,-1);
					if ((sTagValues.length - 1) >= iParts - 1)
					{
						sTagValue = sTagValues[iParts - 1];
						sTagValue = extractFixedLength(sTag, sBIndex, sEIndex, sTagValue, iTotalParts, iParts);
					} else
					{
						sTagValue = EMPTY_STRING;
					}
			}
			} else
			{
				sTagValue = extractFixedLength(sTag, sBIndex, sEIndex, sTagValue, iTotalParts, iParts);
			}
		}
		return sTagValue;
	}
	private String handleSingleField(String sFileContent, String sTag)
	{
		return handleSingleFieldCore(sFileContent,sTag,true);
	}
	private String handleSingleFieldCore(String sFileContent, String sTag, boolean bRemoveUnwanted)
	{
		String sTagValue = EMPTY_STRING;
		sTagValue = StringUtils.substringBetween(sFileContent, sTag, SWIFT_FIELD_SEPARATOR);
		// Begin Last Field Handling
		if (!isExists(sTagValue)) 
		{
			if (isExists(sFileContent,sTag)) 
			{
				sTagValue = StringUtils.substringBetween(sFileContent, sTag, SWIFT_MESSAGE_END);
			}
		}
		if (StringUtils.contains(sTagValue, SWIFT_MESSAGE_END))
		{
			sTagValue = StringUtils.substringBefore(sTagValue,SWIFT_MESSAGE_END);
		}
		String sRemaining = EMPTY_STRING;
		Integer iCount = 0;
		if (!isExists(sTagValue))
		{
			sRemaining = StringUtils.substringAfter(sFileContent, sTag);
			iCount = StringUtils.countMatches(sRemaining, SWIFT_FIELD_SEPARATOR);
			if (iCount == 0)
			{
				sTagValue = sRemaining;
			}
		}
		// End Last Field Handling
		if (bRemoveUnwanted) 
		{
			sTagValue = removeUnwanted(sTagValue);
		}	
		return sTagValue;
	}
	private String extractFixedLength(String sTag, String sBIndex, String sEIndex, String sTagValue, Integer iTotalParts, Integer iParts)
	{
		if (!sBIndex.contains("0") || !sEIndex.contains("0"))
		{
			if (isExists(sTagValue))
			{
				String sPartValue = EMPTY_STRING;
				String sCleanTagValue = StringUtils.replace(sTagValue,FORWARD_SLASH,EMPTY_STRING);
				Integer iBIndex = Integer.parseInt(sBIndex);
				Integer iEIndex = Integer.parseInt(sEIndex);
				sPartValue = StringUtils.mid(sCleanTagValue , iBIndex, iEIndex);
				sCleanTagValue = EMPTY_STRING;
				if (isExists(sPartValue))
				{
					sTagValue = sPartValue;
				}else 
				{
					sTagValue="";
				}
				if (iTotalParts == iParts) // Length Handling
				{
					if (sTagValue.length() > (iEIndex))
					{
						sParseErr = sParseErr + handleErrors("SWIFT_FIELD_LENGTH_ERROR", sTag, sTagValue);
					}
				}
			}
		}
		return sTagValue;
	}
	// Begin Data Type Handling
	public Byte getValue(Byte bByte, String sTag, String str)
	{
		if (isExists(str))
		{
			return Byte.parseByte(str);
		}
		return bByte;
	}
	public Integer getValue(Integer iInteger, String sTag, String str)
	{
		try
		{
			str = removeUnwanted(str);
			if (isExists(str))
			{
				return Integer.parseInt(str);
			}
		} catch (Exception e)
		{
			sParseErr = sParseErr + handleErrors("VWT_INTEGER", sTag, str);
		}
		return 0;
	}
	public String getValue(Integer iInteger)
	{
		if (isExists(iInteger))
		{
			return iInteger.toString();
		}
		return "";
	}
	public Long getValue(Long lLong, String sTag, String str)
	{
		try
		{
			if (isExists(str))
			{
				str = removeUnwanted(str);
				return Long.parseLong(str);
			}
		} catch (Exception e)
		{
			sParseErr = sParseErr + handleErrors("VWT_LONG", sTag, str);
		}
		return (long) 0;
	}
	public String getValue(Long lLong)
	{
		if (isExists(lLong))
		{
			return lLong.toString();
		}
		return "";
	}
	public Float getValue(Float fFloat, String sTag, String str)
	{
		try
		{
			if (isExists(str))
			{
				str = removeUnwanted(str);
				return Float.parseFloat(str);
			}
		} catch (Exception e)
		{
			sParseErr = sParseErr + handleErrors("VWT_FLOAT", sTag, str);
		}
		return (float) 0;
	}
	public String getValue(Float fFloat)
	{
		if (isExists(fFloat))
		{
			return StringUtils.replace(fFloat.toString(),DECIMAL_SEPARATOR,SWIFT_DECIMAL_SEPARATOR);
		}
		return "";
	}
	public Double getValue(Double dDouble, String sTag, String str)
	{
		try
		{
			if (isExists(str))
			{
				if (!str.contains(SWIFT_DECIMAL_SEPARATOR))
				{
					throw new Exception();
				}
				str = removeUnwanted(str);
				str = StringUtils.replace(str, SWIFT_DECIMAL_SEPARATOR, DECIMAL_SEPARATOR);
				return Double.parseDouble(str);
			}
		} catch (Exception e)
		{
			sParseErr = sParseErr + handleErrors("T43", sTag, str);
		}
		return 0.0;
	}
	public String getValue(Double dDouble)
	{
		if (isExists(dDouble))
		{
			return StringUtils.replace(dDouble.toString(),DECIMAL_SEPARATOR,SWIFT_DECIMAL_SEPARATOR);
		}
		return "";
	}
	public BigDecimal getValue(BigDecimal dbBigDecimal, String sTag, String str)
	{
		if (isExists(str))
		{
			try
			{
				if (!str.contains(","))
				{
					throw new Exception();
				}
				dbBigDecimal = convertSwiftAmtToBigDecimal(str);
			} catch (Exception e)
			{
				sParseErr = sParseErr + handleErrors("T40", sTag, str);
			}
		}
		return dbBigDecimal;
	}
	public String getValue(BigDecimal dbBigDecimal)
	{
		if (isExists(dbBigDecimal))
		{
			String sAmtinString = dbBigDecimal.toPlainString();
			sAmtinString = StringUtils.replace(sAmtinString,DECIMAL_SEPARATOR,SWIFT_DECIMAL_SEPARATOR);
			String sAfterDecimal = StringUtils.substringAfter(sAmtinString,SWIFT_DECIMAL_SEPARATOR);
			String sAfterDecimalNew = StringUtils.replace(sAfterDecimal,"0","");
			return StringUtils.replace(sAmtinString,sAfterDecimal,sAfterDecimalNew);
		}
		return "";
	}
	public Boolean getValue(Boolean bBoolean, String sTag, String str)
	{
		try
		{
			if (isExists(str))
			{
				str = removeUnwanted(str);
				return Boolean.parseBoolean(str);
			}
		} catch (Exception e)
		{
			sParseErr = sParseErr + handleErrors("VW_BOOLEAN", sTag, str);
		}
		return bBoolean;
	}
	public String getValue(Boolean bBoolean)
	{
		if (isExists(bBoolean))
		{
			return bBoolean.toString();
		}
		return "";
	}
	public String getValue(String bString, String sTag, String str)
	{
		if (isExists(str))
		{
			str = removeUnwanted(str);
			return str;
		}
		return EMPTY_STRING;
	}
	public String getValue(String bString)
	{
		if (isExists(bString))
		{
			return bString;
		}
		return "";
	}
	public Date getValue(Date dDate, String sTag, String str)
	{
		Date date = dDate;
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		formatter.setLenient(false);
		if (isExists(str))
		{
			try
			{
				str = removeUnwanted(str);
				date = formatter.parse(str);
			} catch (ParseException e)
			{
				sParseErr = sParseErr + handleErrors("T50", sTag, str);
			}
			return date;
		}
		return dDate;
	}
	public String getValue(Date dDate)
	{
		String sDate="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		if (isExists(dDate))
		{
			sDate = formatter.format(dDate);
			return sDate;
		}
		return "";
	}
	public Object getValue(Object bObj, String sTag, String str)
	{
		if (isExists(str))
		{
			return Byte.parseByte(str);
		}
		return bObj;
	}
	// End Data Type Handling
	protected boolean isExists(String sStr)
	{
		if (StringUtils.contains(sStr, NEW_LINE_CHAR))
		{
			return true;
		}
		if (sStr != null && sStr.trim().length() > 0)
		{
			return true;
		} else
		{
			return false;
		}
	}
	public void doValidations()
	{
	}
	public void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObj)
	{
	}
	public void doAfterEnrichValues()
	{
	}
	public HashMap getSearchParams()
	{
		return null;
	}
	public String getLabel(String sResponseField)
	{
		return null;
	}
	public Class<?> setBeanClass()
	{
		return this.getClass();
//		return MessageQueue.class;
	}
	public String getManagedBeanName()
	{
		return null;
	}
	public void setValues(Object sourceBean, Object targetBean, Boolean bSetAsManagedBean)
	{
	}
	public Class<?> setBeanLabelClass()
	{
		return this.getClass();
//		return  MessageQueueLabel.class;
	}
	public String getManagedBeanNameLabel()
	{
		return null;
	}
	public void setPKValue(Object entityObj)
	{
	}
	public void doAfterSelectRow()
	{
	}
	public void afterCreateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void beforeCreateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void afterCreateTransactionCommitted()
	{
	}
	public void afterUpdateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void beforeUpdateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void afterUpdateTransactionCommitted()
	{
	}
	public String getPkFieldName()
	{
		return "";
	}
	@Override
	public Object getFieldValue(Object entityBean, String sFieldName)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		// TODO Auto-generated method stub
	}
	@Override
	protected void setTxnStatus(String sStatus)
	{
		// TODO Auto-generated method stub
	}
	@Override
	protected void doEnrichSystemValues(String sAction, String sNextStatus)
	{
		// TODO Auto-generated method stub
	}
	@Override
	protected void doEnrichSystemValues(String sAction)
	{
		// TODO Auto-generated method stub
	}
}
