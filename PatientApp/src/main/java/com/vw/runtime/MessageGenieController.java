package com.vw.runtime;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import com.google.gson.JsonObject;
@SuppressWarnings("unused")
public abstract class MessageGenieController extends VWDefaultController
{
	VWResponseBean vwResponseBean = new VWResponseBean();
	VWMastersBean vwMastersBean = new VWMastersBean();
	public MessageGenieController(Session session)
	{
		super(session);
	}
	public MessageGenieController()
	{
		super();
	}
	public void executeRuleTest(String sTag1M)
	{
		if (isExists(sTag1M))
		{
			if (sTag1M.equalsIgnoreCase("NAGARAJ"))
			{
				String[] sParams = new String[3];
				sParams[0] = sTag1M;
				sParams[1] = "NAGARAJ";
				addApplicationResponse("MG","700", sParams);
			}
		}
	}		
	protected String getCurrentLcNo()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String lcNo = vwSessionObject.getParamValue("LC_NUMBER");
		return lcNo;
	}
	protected void setCurrentLcNo(String sLCNo)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("LC_NUMBER", sLCNo);
	}
	protected void setGroupName(String sGroupName)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("GROUP_NAME",sGroupName);
	}
	public String getGroupName()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String sGroupName = vwSessionObject.getParamValue("GROUP_NAME");
		return sGroupName;
	}
	protected String getCurrentMessageQueueId()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String sMessageQueueId = vwSessionObject.getParamValue("MESSAGEQUEUE_ID");
		return sMessageQueueId;
	}
	protected void setCurrentMessageQueueId(String sMessageQueueId)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setParamValue("MESSAGEQUEUE_ID",sMessageQueueId);
	}
	protected BigDecimal convertSwiftAmtToBigDecimal(String str)
	{
		if (isExists(str))
		{
			str = removeUnwanted(str);
			str = StringUtils.replace(str, ",", ".");
			int dotIndex = StringUtils.lastIndexOf(str, ".");
		}
		return new BigDecimal(str);
	}
	protected String generateMGUID()
	{
		String MG_MGID_PREFIX = "VWMG";
		String MG_MGID_FORMAT = "ddMMYYYYHHmmssmm";
		SimpleDateFormat sdfDate = new SimpleDateFormat(MG_MGID_FORMAT);
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
		return MG_MGID_PREFIX + strDate;
	}
	protected String removeUnwanted(String str)
	{
		String sLastTwoChars = StringUtils.substring(str, str.length() - 1, str.length());
		if (StringUtils.contains(sLastTwoChars, NEW_LINE_CHAR))
		{
			str = StringUtils.substring(str, 0, str.length() - 1);
		}
		str = StringUtils.remove(str, "&#xd;");
		return str;
	}
	protected String handleErrors(String sErrorCode, String sTag, String str)
	{
		String[] sParams = new String[2];
		sParams[0] = sTag;
		sParams[1] = str;
		String sErrorDesc = getErrorMessage(sErrorCode, sParams);
		addApplicationResponse("MG-SWIFT-HELPER", sErrorCode, sParams);
		return sErrorDesc;
	}
	protected String getErrorMessage(String sErrorCode, String[] sParams)
	{
		return vwResponseBean.getMessage(isActionFromUI(),sErrorCode, sParams);
	}
	protected String getErrorMessage(String sErrorCode)
	{
		return vwResponseBean.getMessage(isActionFromUI(),sErrorCode);
	}
	// End Utility Methods
	abstract public void doValidations();
	abstract protected void doEnrichValues(Boolean dUpdateRules, JsonObject paramsInfoObj);
	abstract protected void doAfterEnrichValues();
	abstract protected void doEnrichSystemValues(String sAction);
	abstract protected HashMap<?, ?> getSearchParams();
	abstract public String getLabel(String sResponseField);
	abstract protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex);
	// Trade Helper Methods END
}
