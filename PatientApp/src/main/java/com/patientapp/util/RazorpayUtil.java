package com.patientapp.util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.poi.ss.formula.functions.Days360;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.request.service.HttpUtil;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import com.patientapp.util.CommonUtil;
public class RazorpayUtil
{
	/*
	 *This api accepts amount in paisa and returns razorpayPaymentOrderId for that amount to capture party amounts
	 *Returns empty string as response if razorpayorderId not generated
	 */ 
	public static String getRazorpayPaymentOrderId(Integer amountInPaisa, int referenceId)
	{
		try
		{
			String RazorPayKeyId = com.patientapp.util.SettingsUtil.getRazorPayKeyId();
			String RazorPayKeyValue = com.patientapp.util.SettingsUtil.getRazorPayKeyValue();
			JsonObject additionalParams = new JsonObject();
			additionalParams.addProperty("RazorPayKeyId", RazorPayKeyId);
			additionalParams.addProperty("RazorPayKeyValue", RazorPayKeyValue);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("amount", String.valueOf(amountInPaisa)));
			nvps.add(new BasicNameValuePair("currency", "INR"));
			nvps.add(new BasicNameValuePair("receipt", String.valueOf(referenceId)));
			JsonObject httpRequestInfo = com.patientapp.request.service.HttpUtil.sendHttpPostRequest("https://api.razorpay.com/v1/orders", nvps, additionalParams);
			JsonObject responseData = httpRequestInfo.get("responseData").getAsJsonObject();
			String razorpayPaymentOrderId = responseData.get("id").getAsString();
			return razorpayPaymentOrderId;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return "";
	}
	/*
	 *This api accepts razorpayOrderId, razorpayPaymentId, razorpaySignature from successfull payment response.
	 *Returns true as response if the siganture valid.
	 *Signature verification helps us to confirm the payment made through the razorpay 
	 */ 
	public static boolean isRazorpaySignatureValid(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature)
	{
		boolean isSignatureVerified = false;
		try
		{
			String RazorPayKeyValue = com.patientapp.util.SettingsUtil.getRazorPayKeyValue();
			String secret =  RazorPayKeyValue;
			String generated_signature = getRazorPaySignatute(razorpayOrderId + "|" + razorpayPaymentId, secret);
			if (generated_signature.equals(razorpaySignature)) 
			{
				isSignatureVerified = true;
			}			
			return isSignatureVerified;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
			return false;
		}
	}
	private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
	public static String getRazorPaySignatute(String data, String secret) throws Exception
    {
        String result ="";
        try 
        {
            // get an hmac_sha256 key from the raw secret bytes
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
            // get an hmac_sha256 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
            // base64-encode the hmac
            result = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
            return result;
        } 
        catch (Exception e) 
        {
            CommonUtil.writeTolog(e);
        }
        return result;
    }	
	/*
	 *This api accepts referenceIdPrefix(Provide as per your payout requirement), orderId(Your transaction primary key)
	 *Returns string as response(This will be unique for every razorpay payout)
	 */ 
	public static String getRazorpayPayoutReferecneId(String referenceIdPrefix, int orderId)
	{
		return referenceIdPrefix + orderId + "_" + System.currentTimeMillis() + "_" + CommonUtil.getRandomNo();
	}
	/*
	 *This api accepts referenceId as input parameter 
	 *Returns JsonObject as response, this object has success attribute 1 for successfull response from razorpay 
	 *Response has JsonArray as value with key items(List of payouts with matching referenceId)
	 *But here list returned with only single object or none
	 *If matching payout found returns single object JasonArray else returns empty jsonArray('items')
	 */ 
	public static JsonObject getRazorpayTransactionStatus(String referenceId)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String RazorPayKeyId = com.patientapp.util.SettingsUtil.getRazorPayKeyId();
			String RazorPayKeyValue = com.patientapp.util.SettingsUtil.getRazorPayKeyValue();
			String RazorPayAccountNumber = com.patientapp.util.SettingsUtil.getRazorPayAccountNumber();
			JsonObject additionalParams = new JsonObject();
			additionalParams.addProperty("RazorPayKeyId", RazorPayKeyId);
			additionalParams.addProperty("RazorPayKeyValue", RazorPayKeyValue);
			String reference_id = referenceId;
			String account_number = RazorPayAccountNumber;
			String statusFetchUrl = "https://api.razorpay.com/v1/payouts?account_number="+account_number+"&reference_id="+reference_id;
			JsonObject razorPayStatusResponse = HttpUtil.sendHttpRequest(statusFetchUrl, additionalParams);
			if(!razorPayStatusResponse.has("success") || razorPayStatusResponse.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Payments could not be transferred as payment gateway could not be connected ");
				return dataObject;
			}
			JsonObject responseData = razorPayStatusResponse.get("responseData").getAsJsonObject();
			responseData.addProperty("success", 1);
			return responseData;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	/*
	 * This api accepts payee razorpay fundAccountId, amount to be transferred in paisa, currency format(ex:INR), mode of payment(ex:IMPS, NEFT,etc..), purpose of transfer, referenceId(to be unique for every razorpay payout), and transaction narration
	 *Returns JsonObject as response, this object has success attribute 1 for successfull payout from razorpay, razorpayPayoutId is the key generated in razorpay payout api  
	 */ 
	public static JsonObject transferOnlineThroughRazorpay(String fundAccountId, BigDecimal amount, String currency, String mode, String purpose, String referenceId, String narration) 
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String RazorPayKeyId = com.patientapp.util.SettingsUtil.getRazorPayKeyId();
			String RazorPayKeyValue = com.patientapp.util.SettingsUtil.getRazorPayKeyValue();
			String RazorPayAccountNumber = com.patientapp.util.SettingsUtil.getRazorPayAccountNumber();
			JsonObject razorPayStatusResponse = getRazorpayTransactionStatus(referenceId);				
			if(!razorPayStatusResponse.has("success") || razorPayStatusResponse.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Payments could not be transferred as payment gateway could not be connected ");
				return dataObject;
			}
			JsonArray items = razorPayStatusResponse.get("items").getAsJsonArray();
			for(int i = 0; i < items.size(); i++)
			{
				JsonObject payoutInfo = items.get(i).getAsJsonObject();
				String razorpayPayoutId = payoutInfo.get("id").getAsString();
				String status = payoutInfo.get("status").getAsString();
				if(status.equalsIgnoreCase("processed"))
				{
					dataObject.addProperty("success", 1);
					dataObject.addProperty("razorpayPayoutId", razorpayPayoutId);
					return dataObject;
				}
			}
			JsonObject additionalParams = new JsonObject();
			additionalParams.addProperty("RazorPayKeyId", RazorPayKeyId);
			additionalParams.addProperty("RazorPayKeyValue", RazorPayKeyValue);
			String razorPayAccountNumber = RazorPayAccountNumber;
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("account_number", razorPayAccountNumber));
			nvps.add(new BasicNameValuePair("fund_account_id", fundAccountId));
			nvps.add(new BasicNameValuePair("amount", String.valueOf(amount.intValue())));
			nvps.add(new BasicNameValuePair("currency", currency));
			nvps.add(new BasicNameValuePair("mode", mode));
			nvps.add(new BasicNameValuePair("purpose", purpose));
			nvps.add(new BasicNameValuePair("reference_id", referenceId));
			nvps.add(new BasicNameValuePair("narration", "For testing transfer API"));
			JsonObject razorPayPayoutResponse =  HttpUtil.sendHttpPostRequest("https://api.razorpay.com/v1/payouts", nvps, additionalParams);
			if (razorPayPayoutResponse == null || !razorPayPayoutResponse.has("success") || razorPayPayoutResponse.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Your request could not be processed as razorpay could not be connected.");
				return dataObject;
			}
			JsonObject responseData = razorPayPayoutResponse.get("responseData").getAsJsonObject();
			String status = responseData.get("status").getAsString();
			String razorpayPayoutId = responseData.get("id").getAsString();
			if(!status.equalsIgnoreCase("processed")
					&& !status.equalsIgnoreCase("processing"))
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", responseData.get("failure_reason").getAsString());
				return dataObject;
			}
			dataObject.addProperty("success", 1);
			dataObject.addProperty("razorpayPayoutId", razorpayPayoutId);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
}
