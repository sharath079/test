package com.vw.runtime.exception;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
public class VWCoreResponseBean 
{
	HashMap responseCodesMap = new HashMap();
	private String responseDesc;
	private String responseField;
	private String responseFieldLabel;
	private String responseType;
	private String responseCode;
	private String responseLang;
	private boolean showResponse;
	public String NEW_LINE="<br/>";
	public String RESPONSE_FIELD_KEY = "<<FieldLabel>>";
	public String RESPONSE_DEF_LANGUAGE="ENGLISH";
	public String RESPONSE_TYPE_FAILED= "FAILED";
	public String RESPONSE_TYPE_SUCESS= "SUCESS";
	public String RESPONSE_TYPE_APPLICATION= "APPLICATION";
	public String RESPONSE_TYPE_CUSTOM= "CUSTOM";
	public String RESPONSE_SUBTYPE_CUSTOM_MESSAGE="CUSTOM";
	public String RESPONSE_SUBTYPE_SUCESS_LOGIN="LOGIN SUCESS";
	public String RESPONSE_SUBTYPE_FAILED_MANDATORY="MANDATORY";
	public String RESPONSE_SUBTYPE_FAILED_LOGIN="LOGIN FAILED";
	public String RESPONSE_SUBTYPE_FAILED_ACTIONNOTALLOWED="ACTION NOT ALLOWED";
	public String RESPONSE_SUBTYPE_FAILED_MAXLENGTH="MAXLENGTH";
	public String RESPONSE_SUBTYPE_FAILED_UNIQUE="FAILED UNIQUE";
	public String RESPONSE_SUBTYPE_NOT_AUTHORISER="NOT AN AUTHORIZER";
	public String RESPONSE_SUBTYPE_FAILED_AUTHLIMIT="FAILED AUTH LIMIT";
	public String RESPONSE_SUBTYPE_FAILED_ALLOWED_VALUES="NOT_ALLOWED_VALUES";
	public String RESPONSE_SUBTYPE_FAILED_ALLOWED_DECIMAL_VALUES="NOT_ALLOWED_DECIMAL_VALUES";
	public VWCoreResponseBean()
	{
		responseCodesMap.put("SUCESS0001","Welcome <<FieldLabel>> you have sucessfully loggedin");
		responseCodesMap.put("SUCESS0002","Transaction was sucessfull.");
		responseCodesMap.put("ERR0001","<<FieldLabel>> is missing !!!");
		responseCodesMap.put("ERR0002","Login Failed, Please try again or contact Administrator !!!");
		responseCodesMap.put("ERR0003","<<FieldLabel>> is NOT allowed for you, Please contact Administrator !!!");
		responseCodesMap.put("ERR0004","Maximum Length Allowed for <<FieldLabel>> + !!!");
		responseCodesMap.put("ERR0005","<<FieldLabel>> already exists !!!");
		responseCodesMap.put("ERR0006","Authorisation Failed, You are not an Authorised Signatory, Please contact Administrator !!!");
		responseCodesMap.put("ERR0007","Insufficient Authorisation Limit, Please contact Administrator !!!");
		responseCodesMap.put("ERR0008","<<FieldLabel>> is having an Invalid Entry(Check for Allowed Codes) !!!");
		responseCodesMap.put("ERR0009","<<FieldLabel>> is having an Invalid number of decimal places for the given currency !!!");
		responseCodesMap.put("ERR0010","<<0>> must be lesser than <<1>> !!!");
		responseCodesMap.put("ERR0011","<<0>> must be lesser than <<1>> !!!");
		responseCodesMap.put("ERR0012","<<0>> must not be equal  to <<1>> !!!");
	}
	public void resetResponse()
	{
		setShowResponse(false);
	}
	public String getResponseDesc()
	{
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc)
	{
		this.responseDesc = responseDesc;
	}
	public String getResponseField()
	{
		return responseField;
	}
	public void setResponseField(String responseField)
	{
		this.responseField = responseField;
	}
	public String getResponseFieldLabel()
	{
		return responseFieldLabel;
	}
	public void setResponseFieldLabel(String responseFieldLabel)
	{
		this.responseFieldLabel = responseFieldLabel;
	}
	public String getResponseType()
	{
		return responseType;
	}
	public void setResponseType(String responseType)
	{
		this.responseType = responseType;
	}
	public String getResponseCode()
	{
		return responseCode;
	}
	public void setResponseCode(String responseCode)
	{
		this.responseCode = responseCode;
	}
	public String getResponseLang() 
	{
		return responseLang;
	}
	public void setResponseLang(String responseLang) 
	{
		this.responseLang = responseLang;
	}
	public boolean isShowResponse()
	{
		return showResponse;
	}
	public void setShowResponse(boolean showResponse)
	{
		this.showResponse = showResponse;
	}
	public String getMessage(String sResponseCode, String[] sParams)
	{
		String sErrorStr = (String) responseCodesMap.get(sResponseCode);
		for (int i = 0; i < sParams.length; i++)
		{
			String sTemp = sParams[i];
			sErrorStr = StringUtils.replace(sErrorStr, "<<" + i + ">>", sParams[i]);
		}
		return "RULE "+ sResponseCode+ " is Violated. " + sErrorStr + " !!!.\n";
	}
	public String getMessage(String sResponseCode)
	{
		String sErrorStr = (String) responseCodesMap.get(sResponseCode);
		return "RULE "+ sResponseCode + " is Violated. "+ sErrorStr + " !!!.\n";
	}
	public String getResponseMessage(String sResponseCode)
	{
		String sErrorStr = (String) responseCodesMap.get(sResponseCode);
		return sErrorStr;
	}
}
