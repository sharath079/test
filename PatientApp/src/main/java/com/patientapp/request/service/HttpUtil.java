package com.patientapp.request.service;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class HttpUtil
{
	public static JsonObject sendHttpRequest(String apiUrl)throws Exception
	{
		return sendHttpRequest(apiUrl, null);
	}
	public static JsonObject sendHttpRequest(String apiUrl, JsonObject additionalParams)throws Exception
	{ 
		JsonObject dataObject = new JsonObject();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try
		{
			HttpGet httpGet = new HttpGet(apiUrl);
			if(additionalParams != null)
			{
				String RazorPayKeyId = additionalParams.get("RazorPayKeyId").getAsString();
				String RazorPayKeyValue = additionalParams.get("RazorPayKeyValue").getAsString();
				String RazorPayKey = RazorPayKeyId+":"+RazorPayKeyValue;
	            String encoding = Base64.getEncoder().encodeToString(RazorPayKey.getBytes("UTF-8"));
				httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
			}
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try
			{
				HttpEntity contentEntity = response.getEntity();
				InputStream in = contentEntity.getContent();
				String responseText = IOUtils.toString(in, "UTF-8");
				EntityUtils.consume(contentEntity);
                JsonObject responseInfo = new Gson().fromJson(responseText, JsonObject.class);
                dataObject.add("responseData", responseInfo);				
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			httpclient.close();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject sendHttpPostRequest(String apiUrl, List<NameValuePair> nvps)throws Exception
	{
		return sendHttpPostRequest(apiUrl, nvps, null);
	}
	public static JsonObject sendHttpPostRequest(String apiUrl, List<NameValuePair> nvps, JsonObject additionalParams)throws Exception
	{ 
		JsonObject dataObject = new JsonObject();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try
		{
			HttpPost httpPost = new HttpPost(apiUrl);
			if(additionalParams != null)
			{
				String RazorPayKeyId = additionalParams.get("RazorPayKeyId").getAsString();
				String RazorPayKeyValue = additionalParams.get("RazorPayKeyValue").getAsString();
				String RazorPayKey = RazorPayKeyId+":"+RazorPayKeyValue;
	            String encoding = Base64.getEncoder().encodeToString(RazorPayKey.getBytes("UTF-8"));
				httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try
			{
				HttpEntity contentEntity = response.getEntity();
				InputStream in = contentEntity.getContent();
				String responseText = IOUtils.toString(in, "UTF-8");
				System.out.println(responseText);
				EntityUtils.consume(contentEntity);
                JsonObject responseInfo = new Gson().fromJson(responseText, JsonObject.class);
                dataObject.add("responseData", responseInfo);				
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			httpclient.close();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getRequestParametersInfo(HttpServletRequest request)
	{
		JsonObject parametersInfo = new JsonObject();
		String parametersInfoText = request.getParameter("params").trim();
		if(parametersInfoText!=null && parametersInfoText.length()>0)
		{
            parametersInfo = new Gson().fromJson(parametersInfoText, JsonObject.class);
		}
		return parametersInfo;		
	}
	public static String getHttpRequestResponseAttributeText(JsonObject httpRequestInfo, String attributeName) throws Exception
	{
		try
		{
			int isHttpRequestSuccuessfullySubmitted = 0;
			if(httpRequestInfo!=null && httpRequestInfo.has("success") && httpRequestInfo.get("success").isJsonNull()==false)
			{
				isHttpRequestSuccuessfullySubmitted = httpRequestInfo.get("success").getAsInt();
			}
			if(isHttpRequestSuccuessfullySubmitted!=1)
			{
				return null;				
			}
			JsonObject responseData = new JsonObject();
			if(httpRequestInfo!=null && httpRequestInfo.has("responseData") && httpRequestInfo.get("responseData").isJsonNull()==false)
			{
				responseData = httpRequestInfo.get("responseData").getAsJsonObject();
			}
			int isRequestSuccessfullyExecuted = 0;
			if(responseData!=null && responseData.has("success") && responseData.get("success").isJsonNull()==false)
			{
				isRequestSuccessfullyExecuted = responseData.get("success").getAsInt();
			}
			if(isRequestSuccessfullyExecuted!=1)
			{
				return null;				
			}
			String attributeValue = null;
			try
			{
				if(responseData!=null && responseData.has(attributeName) && responseData.get(attributeName).isJsonNull()==false)
				{
					attributeValue = responseData.get(attributeName).getAsString();
				}				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return attributeValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;				
	}
	public static int getHttpRequestResponseAttributeInt(JsonObject httpRequestInfo, String attributeName) throws Exception
	{		
		try
		{
			int isHttpRequestSuccuessfullySubmitted = 0;
			if(httpRequestInfo!=null && httpRequestInfo.has("success") && httpRequestInfo.get("success").isJsonNull()==false)
			{
				isHttpRequestSuccuessfullySubmitted = httpRequestInfo.get("success").getAsInt();
			}
			if(isHttpRequestSuccuessfullySubmitted!=1)
			{
				return 0;
			}
			JsonObject responseData = new JsonObject();
			if(httpRequestInfo!=null && httpRequestInfo.has("responseData") && httpRequestInfo.get("responseData").isJsonNull()==false)
			{
				responseData = httpRequestInfo.get("responseData").getAsJsonObject();
			}
			int isRequestSuccessfullyExecuted = 0;
			if(responseData!=null && responseData.has("success") && responseData.get("success").isJsonNull()==false)
			{
				isRequestSuccessfullyExecuted = responseData.get("success").getAsInt();
			}
			if(isRequestSuccessfullyExecuted!=1)
			{
				return 0;				
			}
			int attributeValue = 0;
			try
			{
				if(responseData!=null && responseData.has(attributeName) && responseData.get(attributeName).isJsonNull()==false)
				{
					attributeValue = responseData.get(attributeName).getAsInt();
				}				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return attributeValue;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static int isHttpRequestSuccessfullyExecuted(JsonObject httpRequestInfo) throws Exception
	{		
		try
		{
			int isHttpRequestSuccuessfullySubmitted = 0;
			if(httpRequestInfo!=null && httpRequestInfo.has("success") && httpRequestInfo.get("success").isJsonNull()==false)
			{
				isHttpRequestSuccuessfullySubmitted = httpRequestInfo.get("success").getAsInt();
			}
			if(isHttpRequestSuccuessfullySubmitted!=1)
			{
				return 0;
			}
			JsonObject responseData = new JsonObject();
			if(httpRequestInfo!=null && httpRequestInfo.has("responseData") && httpRequestInfo.get("responseData").isJsonNull()==false)
			{
				responseData = httpRequestInfo.get("responseData").getAsJsonObject();
			}
			int isRequestSuccessfullyExecuted = 0;
			if(responseData!=null && responseData.has("success") && responseData.get("success").isJsonNull()==false)
			{
				isRequestSuccessfullyExecuted = responseData.get("success").getAsInt();
			}
			if(isRequestSuccessfullyExecuted!=1)
			{
				return 0;				
			}
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static int isHttpRequestSuccessfullySubmitted(JsonObject httpRequestInfo) throws Exception
	{		
		try
		{
			int isHttpRequestSuccuessfullySubmitted = 0;
			if(httpRequestInfo!=null && httpRequestInfo.has("success") && httpRequestInfo.get("success").isJsonNull()==false)
			{
				isHttpRequestSuccuessfullySubmitted = httpRequestInfo.get("success").getAsInt();
			}
			if(isHttpRequestSuccuessfullySubmitted!=1)
			{
				return 0;
			}
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
