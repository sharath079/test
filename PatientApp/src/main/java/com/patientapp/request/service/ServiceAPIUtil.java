package com.patientapp.request.service;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.EntitiesHttpAPIUtil;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.SettingsUtil;
public class ServiceAPIUtil
{
	public static String REQUEST_STATUS_PENDING = "PENDING";
	public static String REQUEST_STATUS_FAILED = "FAILED";
	public static String REQUEST_STATUS_SUCCESSFULL = "SUCCESSFULL";
	private static String SERVICE_API_REQUEST_RESTFUL_URL = "$$SERVICE_HOSTNAME$$:$$SERVICE_PORT$$/HelloServlets/service/$$SERVICE_NAME_LOWER$$/addrequest";
	private static String API_REQUEST_STATUS_REST_API_URL = "$$SERVICE_HOSTNAME$$:$$SERVICE_PORT$$/HelloServlets/service/$$SERVICE_NAME_LOWER$$/requeststatus";
	private static String API_ROLLBACK_REST_API_URL = "$$SERVICE_HOSTNAME$$:$$SERVICE_PORT$$/HelloServlets/service/$$SERVICE_NAME_LOWER$$/rollbackrequest";
	private static String API_ROLLBACK_REQUEST_STATUS_REST_API_URL = "$$SERVICE_HOSTNAME$$:$$SERVICE_PORT$$/HelloServlets/service/$$SERVICE_NAME_LOWER$$/rollbackrequeststatus";
	private static String GET_SERVICE_API_DATA_REQUEST_RESTFUL_URL = "$$SERVICE_HOSTNAME$$:$$SERVICE_PORT$$/HelloServlets/service/$$SERVICE_NAME_LOWER$$/getdatafromapi";
	private static String masterDBName = "";
	public static void runScheduler()
	{
		masterDBName = CommonUtil.getMasterDBName();
		final Runnable runnableScheduler = new Runnable()
		{
			public void run()
			{
				try
				{
					executePendingRequests();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final ScheduledFuture<?> schedulerHandle = scheduler.scheduleAtFixedRate(runnableScheduler, 1, 10, TimeUnit.SECONDS);
	}
	public static JsonObject acceptRequest(HttpServletRequest request)
	{
		int referenceRequestId = -1;
		int transactionReferenceId = -1;
		JsonObject dataObject = new JsonObject();
		String targetServiceName = "";
		String targetAPIName = "";
		String rollbackAPIName = "";
		int executeImmediately = 0;
		try
		{
			JsonObject requestParametersInfo = new JsonObject();
			JsonObject parametersInfo = new JsonObject();
			String parametersInfoText = request.getParameter("params").trim();
			if (parametersInfoText != null && parametersInfoText.length() > 0)
			{
				parametersInfo = new Gson().fromJson(parametersInfoText, JsonObject.class);
				if (parametersInfo.has("executeImmediately") && parametersInfo.get("executeImmediately").isJsonNull() == false)
				{
					executeImmediately = parametersInfo.get("executeImmediately").getAsInt();
				}
			}
			if (executeImmediately == 1)
			{
				requestParametersInfo = parametersInfo.get("requestParametersInfo").getAsJsonObject();
				Session session = SessionFactoryBuilder.getDBSession(masterDBName);
				targetAPIName = parametersInfo.get("targetAPIName").getAsString();
				if (requestParametersInfo.has("DBName"))
				{
					session = SessionFactoryBuilder.getDBSession(requestParametersInfo.get("DBName").getAsString());
				}
				return EntitiesHttpAPIUtil.executeAPI(session, targetAPIName, requestParametersInfo);
			}
			else
			{
				if (parametersInfoText != null && parametersInfoText.length() > 0)
				{
					referenceRequestId = parametersInfo.get("referenceRequestId").getAsInt();
					targetServiceName = parametersInfo.get("targetServiceName").getAsString();
					targetAPIName = parametersInfo.get("targetAPIName").getAsString();
					rollbackAPIName = parametersInfo.get("rollbackAPIName").getAsString();
					transactionReferenceId = parametersInfo.get("transactionReferenceId").getAsInt();
					requestParametersInfo = parametersInfo.get("requestParametersInfo").getAsJsonObject();
					System.out.println("Request parameters received : " + requestParametersInfo);
				}
				return acceptRequest(referenceRequestId, targetServiceName, targetAPIName, rollbackAPIName, requestParametersInfo, transactionReferenceId);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject acceptRequest(int referenceRequestId, String targetServiceName, String targetAPIName, String rollbackAPIName, JsonObject requestParametersInfo, int transactionReferenceId)
	{
		int requestId = -1;
		JsonObject dataObject = new JsonObject();
		Session session = null;
		try
		{
			JsonObject parametersInfo = new JsonObject();
			parametersInfo.addProperty("referenceRequestId", referenceRequestId);
			parametersInfo.addProperty("targetServiceName", targetServiceName);
			parametersInfo.addProperty("targetAPIName", targetAPIName);
			parametersInfo.add("requestParametersInfo", requestParametersInfo);
			parametersInfo.addProperty("transactionReferenceId", transactionReferenceId);
			session = SessionFactoryBuilder.getDBSession(requestParametersInfo.get("DBName").getAsString());
			RequestReceived existingRequestInfo = getExistingRequestInfo(session, referenceRequestId, targetServiceName, targetAPIName, transactionReferenceId);
			if (existingRequestInfo != null)
			{
				dataObject.addProperty("msg", "Request already exists");
				dataObject.addProperty("generatedRequestId", existingRequestInfo.getRequestReceivedId());
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			requestId = addRequestInfo(session, referenceRequestId, targetServiceName, targetAPIName, rollbackAPIName, parametersInfo, transactionReferenceId);
			dataObject.addProperty("generatedRequestId", requestId);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			requestId = -1;
		}
		finally 
		{
			if(session != null)
			{
					session.close();
			}
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getServiceRequestStatus(HttpServletRequest request)
	{
		JsonObject dataObject = new JsonObject();
		Session session = null;
		try
		{
			int targetServiceRequestId = Integer.parseInt(request.getParameter("targetservicerequestid"));
			String DBName = request.getParameter("DBName");
			session = SessionFactoryBuilder.getDBSession(DBName);
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, targetServiceRequestId);
			String currentRequestStatus = requestReceived.getCurrentRequestStatus();
			String sentRequestsStatus = requestReceived.getSentRequestsStatus();
			String requestStatus = REQUEST_STATUS_PENDING;
			if (REQUEST_STATUS_FAILED.equalsIgnoreCase(currentRequestStatus) || REQUEST_STATUS_FAILED.equalsIgnoreCase(sentRequestsStatus))
			{
				requestStatus = REQUEST_STATUS_FAILED;
			}
			else if (REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(currentRequestStatus) && REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(sentRequestsStatus))
			{
				requestStatus = REQUEST_STATUS_SUCCESSFULL;
			}
			dataObject.addProperty("requestStatus", requestStatus);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null)
			{
				session.close();
			}
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getServiceRequestRollbackStatus(HttpServletRequest request)
	{
		JsonObject dataObject = new JsonObject();
		Session session = null;
		try
		{
			int targetServiceRequestId = Integer.parseInt(request.getParameter("targetservicerequestid"));
			String DBName = request.getParameter("DBName");
			session = SessionFactoryBuilder.getDBSession(DBName);
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, targetServiceRequestId);
			int isRequestRolledBack = requestReceived.getIsRequestRolledBack();
			dataObject.addProperty("isRequestRolledBack", isRequestRolledBack);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(session != null)
			{
				session.close();
			}
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject rollbackRequestReceived(HttpServletRequest request)
	{
		int referenceRequestId = -1;
		int transactionReferenceId = -1;
		JsonObject dataObject = new JsonObject();
		String targetServiceName = "";
		String targetAPIName = "";
		Session session = null;
		try
		{
			JsonObject parametersInfo = new JsonObject();
			String parametersInfoText = request.getParameter("params").trim();
			if (parametersInfoText != null && parametersInfoText.length() > 0)
			{
				parametersInfo = new Gson().fromJson(parametersInfoText, JsonObject.class);
				referenceRequestId = parametersInfo.get("referenceRequestId").getAsInt();
				transactionReferenceId = -1;//parametersInfo.get("transactionReferenceId").getAsInt();
				targetServiceName = parametersInfo.get("targetServiceName").getAsString();
				targetAPIName = parametersInfo.get("targetAPIName").getAsString();
				session = SessionFactoryBuilder.getDBSession(parametersInfo.get("DBName").getAsString());
			}
			RequestReceived existingRequestInfo = getExistingRequestInfo(session, referenceRequestId, targetServiceName, targetAPIName, transactionReferenceId);
			if (existingRequestInfo == null)
			{
				dataObject.addProperty("msg", "No such request exists");
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			rollbackRequestReceived(session, existingRequestInfo.getRequestReceivedId());
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static RequestReceived getExistingRequestInfo(Session session, int referenceRequestId, String targetServiceName, String targetAPIName, int transactionReferenceId) throws Exception
	{
		// session = SessionFactoryBuilder.getDBSession(masterDBName);
		String hql = "from RequestReceived where serviceName = :serviceName and apiName = :apiName and referenceRequestId = :referenceRequestId and transactionReferenceId = :transactionReferenceId ";
		Query query = session.createQuery(hql);
		query.setParameter("serviceName", targetServiceName);
		query.setParameter("apiName", targetAPIName);
		query.setParameter("referenceRequestId", referenceRequestId);
		query.setParameter("transactionReferenceId", transactionReferenceId);
		int matchingCount = 0;
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			RequestReceived requestReceived = (RequestReceived) it.next();
			return requestReceived;
		}
		return null;
	}
	public static JsonObject addRequestSentInfo(Session session, int referenceRequestId, String targetServiceName, String targetAPIName)
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			RequestSent requestSent = new RequestSent();
			requestSent.setReferenceRequestId(referenceRequestId);
			requestSent.setRequestStatus(REQUEST_STATUS_PENDING);
			requestSent.setTargetServiceRequestId(-1);
			requestSent.setRollBackRequest(0);
			requestSent.setServiceName(targetServiceName);
			requestSent.setApiName(targetAPIName);
			requestSent.setIsRequestRolledBack(0);
			int requestSentInfoId = (Integer) session.save(requestSent);
			tx.commit();
			dataObject.addProperty("success", 1);
			dataObject.addProperty("requestSentInfoId", requestSentInfoId);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static boolean isSentRequestAdded(int referenceRequestId, String targetServiceName, String targetAPIName) throws Exception
	{
		Session session = null;
		session = SessionFactoryBuilder.getDBSession(masterDBName);
		String hql = "from RequestSent where serviceName = :serviceName and apiName = :apiName and referenceRequestId = :referenceRequestId";
		Query query = session.createQuery(hql);
		query.setParameter("serviceName", targetServiceName);
		query.setParameter("apiName", targetAPIName);
		query.setParameter("referenceRequestId", referenceRequestId);
		int matchingCount = 0;
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			matchingCount++;
		}
		if (matchingCount > 0)
		{
			return true;
		}
		return false;
	}
	public static RequestSent getSentRequest(Session session, int referenceRequestId, String targetServiceName, String targetAPIName) throws Exception
	{
		String hql = "from RequestSent where serviceName = :serviceName and apiName = :apiName and referenceRequestId = :referenceRequestId";
		Query query = session.createQuery(hql);
		query.setParameter("serviceName", targetServiceName);
		query.setParameter("apiName", targetAPIName);
		query.setParameter("referenceRequestId", referenceRequestId);
		int matchingCount = 0;
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			return (RequestSent) it.next();
		}
		return null;
	}
	public static int sendAPIRequest(Session session, String targetServiceName, String targetAPIName, String rollbackAPIName, int referenceRequestId, JsonObject requestParametersInfo)
	{
		JsonObject retValObject = new JsonObject();
		return sendAPIRequest(session, targetServiceName, targetAPIName, rollbackAPIName, referenceRequestId, 0, requestParametersInfo, retValObject);
	}
	public static int sendAPIRequest(Session session, String targetServiceName, String targetAPIName, String rollbackAPIName, int referenceRequestId, int executeImmediately, JsonObject requestParametersInfo, JsonObject retValObject)
	{
		String apiUrl = SERVICE_API_REQUEST_RESTFUL_URL.replace("$$SERVICE_NAME_LOWER$$", targetServiceName.toLowerCase());
		apiUrl = getTargetServiceURL(apiUrl, targetServiceName);
		try
		{
			int sendHttpAPIRequest = 0;
			int requestSentInfoId = -1;
			if (executeImmediately != 1)
			{
				RequestSent requestSent = getSentRequest(session, referenceRequestId, targetServiceName, targetAPIName);
				if (requestSent == null)
				{
					int isSentRequestAdded = 0;
					JsonObject requestSentInfo = addRequestSentInfo(session, referenceRequestId, targetServiceName, targetAPIName);
					if (requestSentInfo != null && requestSentInfo.has("success") && requestSentInfo.get("success").isJsonNull() == false)
					{
						isSentRequestAdded = requestSentInfo.get("success").getAsInt();
						requestSentInfoId = requestSentInfo.get("requestSentInfoId").getAsInt();
					}
					if (isSentRequestAdded == 1)
					{
						sendHttpAPIRequest = 1;
					}
				}
				else
				{
					requestSentInfoId = requestSent.getRequestSentId();
				}
			}
			if (sendHttpAPIRequest == 1 || executeImmediately == 1)
			{
				JsonObject httpRequestInfo = sendHttpAPIRequest(session, apiUrl, targetServiceName, targetAPIName, rollbackAPIName, referenceRequestId, executeImmediately, requestParametersInfo);
				int isHttpRequestSuccessfullyExecuted = HttpUtil.isHttpRequestSuccessfullyExecuted(httpRequestInfo);
				if (isHttpRequestSuccessfullyExecuted != 1)
				{
					return 0;
				}
				JsonObject responseData = httpRequestInfo.get("responseData").getAsJsonObject();
				retValObject.add("responseData", responseData);
				if (executeImmediately != 1)
				{
					int generatedRequestId = responseData.get("generatedRequestId").getAsInt();
					updateSentRequestInfo(session, requestSentInfoId, generatedRequestId);
				}
				return 1;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static int rollbackSentRequest(Session session, String apiUrl, int transactionReferenceId, RequestSent requestSent) throws Exception
	{
		String targetServiceName = requestSent.getServiceName();
		String targetAPIName = requestSent.getApiName();
		int referenceRequestId = requestSent.getReferenceRequestId();
		int targetServiceRequestId = requestSent.getTargetServiceRequestId();
		try
		{
			JsonObject httpRequestInfo = sendHttpAPIRollbackRequest(session, apiUrl, targetServiceName, targetAPIName, referenceRequestId, targetServiceRequestId, transactionReferenceId);
			int isHttpRequestSuccessfullyExecuted = HttpUtil.isHttpRequestSuccessfullyExecuted(httpRequestInfo);
			if (isHttpRequestSuccessfullyExecuted != 1)
			{
				return 0;
			}
			JsonObject responseData = httpRequestInfo.get("responseData").getAsJsonObject();
			updateRequestSentRollbackInfo(session, requestSent);
			return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static JsonObject sendHttpAPIRequest(Session session, String apiUrl, String targetServiceName, String targetAPIName, String rollbackAPIName, int referenceRequestId, int executeImmediately, JsonObject requestParametersInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject apiParametersInfo = new JsonObject();
			apiParametersInfo.addProperty("referenceRequestId", referenceRequestId);
			apiParametersInfo.addProperty("targetServiceName", targetServiceName);
			apiParametersInfo.addProperty("targetAPIName", targetAPIName);
			apiParametersInfo.addProperty("rollbackAPIName", rollbackAPIName);
			apiParametersInfo.addProperty("transactionReferenceId", -1);
			apiParametersInfo.addProperty("executeImmediately", executeImmediately);
			apiParametersInfo.add("requestParametersInfo", requestParametersInfo);
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String apiParametersInfoText = gson.toJson(apiParametersInfo);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", apiParametersInfoText));
			JsonObject httpRequestInfo = HttpUtil.sendHttpPostRequest(apiUrl, nvps);
			return httpRequestInfo;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getDataFromAPI(HttpServletRequest request)
	{
		JsonObject apiData = new JsonObject();
		Session session = null;
		try
		{
			System.out.println("Debug API Data 7");
			JsonObject paramsInfo = HttpUtil.getRequestParametersInfo(request);
			String targetServiceName = paramsInfo.get("targetServiceName").getAsString();
			String targetAPIName = paramsInfo.get("targetAPIName").getAsString();
			JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
			System.out.println("Get API Request parameters received : " + requestParametersInfo);
			session = SessionFactoryBuilder.getDBSession(requestParametersInfo.get("DBName").getAsString());
			apiData = EntitiesHttpAPIUtil.getDataFromAPI(session, targetAPIName, paramsInfo);
			return apiData;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return apiData;
	}
	public static JsonObject getDataFromAPI(Session session, String targetServiceName, String targetAPIName, JsonObject requestParametersInfo)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String apiUrl = GET_SERVICE_API_DATA_REQUEST_RESTFUL_URL.replace("$$SERVICE_NAME_LOWER$$", targetServiceName.toLowerCase());
			apiUrl = getTargetServiceURL(apiUrl, targetServiceName);
			JsonObject apiParametersInfo = new JsonObject();
			apiParametersInfo.addProperty("targetServiceName", targetServiceName);
			apiParametersInfo.addProperty("targetAPIName", targetAPIName);
			apiParametersInfo.add("requestParametersInfo", requestParametersInfo);
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String apiParametersInfoText = gson.toJson(apiParametersInfo);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", apiParametersInfoText));
			System.out.println("Debug API Data 3");
			JsonObject httpRequestInfo = HttpUtil.sendHttpPostRequest(apiUrl, nvps);
			System.out.println("Debug API Data 4");
			int isHttpRequestSuccessfullyExecuted = HttpUtil.isHttpRequestSuccessfullyExecuted(httpRequestInfo);
			if (isHttpRequestSuccessfullyExecuted == 1)
			{
				JsonObject responseData = httpRequestInfo.get("responseData").getAsJsonObject();
				return responseData;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("alert", "Data from api could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject sendHttpAPIRollbackRequest(Session session, String apiUrl, String targetServiceName, String targetAPIName, int referenceRequestId, int targetServiceRequestId, int transactionReferenceId) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject apiParametersInfo = new JsonObject();
			apiParametersInfo.addProperty("referenceRequestId", referenceRequestId);
			apiParametersInfo.addProperty("targetServiceName", targetServiceName);
			apiParametersInfo.addProperty("targetAPIName", targetAPIName);
			apiParametersInfo.addProperty("targetServiceRequestId", targetServiceRequestId);
			apiParametersInfo.addProperty("transactionReferenceId", transactionReferenceId);
			apiParametersInfo.addProperty("DBName", CommonUtil.getDatabaseName(session));
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String apiParametersInfoText = gson.toJson(apiParametersInfo);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("params", apiParametersInfoText));
			JsonObject httpRequestInfo = HttpUtil.sendHttpPostRequest(apiUrl, nvps);
			return httpRequestInfo;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int addRequestInfo(Session session, int referenceRequestId, String serviceName, String apiName, String rollbackAPIName, JsonObject parametersInfo, int transactionReferenceId)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.disableHtmlEscaping();
		Gson gson = builder.create();
		String parametersInfoText = gson.toJson(parametersInfo);
		return addRequestInfo(session, referenceRequestId, serviceName, apiName, rollbackAPIName, parametersInfoText, transactionReferenceId);
	}
	public static int addRequestInfo(Session session, int referenceRequestId, String serviceName, String apiName, String rollbackAPIName, String parametersInfoText, int transactionReferenceId)
	{
		int requestId = -1;
		try
		{
//			session = SessionFactoryBuilder.getDBSession(masterDBName);
			Transaction tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			RequestReceived rr = new RequestReceived();
			rr.setIsRequestRolledBack(0);
			rr.setServiceName(serviceName);
			rr.setApiName(apiName);
			rr.setRollbackAPIName(rollbackAPIName);
			rr.setParamsInfo(parametersInfoText);
			rr.setRollBackRequest(0);
			rr.setReferenceRequestId(referenceRequestId);
			rr.setTransactionReferenceId(transactionReferenceId);
			rr.setCurrentRequestStatus(REQUEST_STATUS_PENDING);
			rr.setSentRequestsStatus(REQUEST_STATUS_PENDING);
			rr.setAreSentRequestsRolledBack(0);
			requestId = (Integer) session.save(rr);
			tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			requestId = -1;
		}
		return requestId;
	}
	public static void updateSentRequestInfo(Session session, int requestSentInfoId, int generatedRequestId) throws Exception
	{
		Transaction tx = null;
		try
		{
			RequestSent rsInfo = (RequestSent) session.get(RequestSent.class, requestSentInfoId);
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			rsInfo.setTargetServiceRequestId(generatedRequestId);
			session.update(rsInfo);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestSentRollbackInfo(Session session, RequestSent rsInfo) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			rsInfo.setRollBackRequest(1);
			session.update(rsInfo);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestSentAsRolledBack(Session session, RequestSent rsInfo) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			rsInfo.setIsRequestRolledBack(1);
			session.update(rsInfo);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestSentStatus(Session session, RequestSent rsInfo, String requestStatus) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			rsInfo.setRequestStatus(requestStatus);
			session.update(rsInfo);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestReceivedStatus(Session session, String requestStatus, int requestReceivedId)
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestReceivedId);
			if (!tx.isActive())
			{
				tx.begin();
			}
			requestReceived.setCurrentRequestStatus(requestStatus);
			session.update(requestReceived);
			tx.commit();
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestReceivedInfoRolledback(Session session, int requestReceivedId) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestReceivedId);
			if (!tx.isActive())
			{
				tx.begin();
			}
			requestReceived.setIsRequestRolledBack(1);
			session.update(requestReceived);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void updateRequestReceivedSentRequestsRolledback(Session session, int requestReceivedId) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestReceivedId);
			if (!tx.isActive())
			{
				tx.begin();
			}
			requestReceived.setAreSentRequestsRolledBack(1);
			session.update(requestReceived);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static void rollbackRequestReceived(Session session, int requestReceivedId) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestReceivedId);
			if (!tx.isActive())
			{
				tx.begin();
			}
			requestReceived.setRollBackRequest(1);
			session.update(requestReceived);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	public static void updateRequestReceivedSentRequestsStatus(Session session, RequestReceived requestReceivedInfo, String sentRequestsStatus) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			requestReceivedInfo.setSentRequestsStatus(sentRequestsStatus);
			session.update(requestReceivedInfo);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static int isSentRequestRolledBack(Session session, RequestSent rsInfo, String apiUrl) throws Exception
	{
		try
		{
			int targetServiceRequestId = rsInfo.getTargetServiceRequestId();
			JsonObject httpRequestInfo = HttpUtil.sendHttpRequest(apiUrl + "?targetservicerequestid=" + targetServiceRequestId + "&DBName=" + CommonUtil.getDatabaseName(session));
			int isRequestExecutedSuccessfully = HttpUtil.isHttpRequestSuccessfullyExecuted(httpRequestInfo);
			if (isRequestExecutedSuccessfully != 1)
			{
				return 0;
			}
			int isRequestRolledBack = HttpUtil.getHttpRequestResponseAttributeInt(httpRequestInfo, "isRequestRolledBack");
			return isRequestRolledBack;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public static void updateSentRequestsStatus(Session session, int requestId, RequestReceived rrInfo) throws Exception
	{
		try
		{
			String hql = "from RequestSent where  referenceRequestId = :referenceRequestId) ";
			Query query = session.createQuery(hql);
			query.setParameter("referenceRequestId", requestId);
			List resultsList = query.list();
			int rollbackRequest = rrInfo.getRollBackRequest();
			int successfullRequestsCount = 0;
			int pendingRequestsCount = 0;
			int failedRequestsCount = 0;
			int rolledbackRequestsCount = 0;
			int totalRequestsSentCount = 0;
			ArrayList<RequestSent> sentRequestsList = new ArrayList<RequestSent>();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				RequestSent rsInfo = (RequestSent) it.next();
				sentRequestsList.add(rsInfo);
			}
			for (int i = 0; i < sentRequestsList.size(); i++)
			{
				RequestSent rsInfo = sentRequestsList.get(i);
				String targetServiceName = rsInfo.getServiceName();
				if (rollbackRequest == 1)
				{
					int rollbackSentRequest = rsInfo.getRollBackRequest();
					int transactionReferenceId = rrInfo.getTransactionReferenceId();
					if (rollbackSentRequest != 1)
					{
						String apiUrl = API_ROLLBACK_REST_API_URL.replace("$$SERVICE_NAME_LOWER$$", targetServiceName.toLowerCase());
						apiUrl = getTargetServiceURL(apiUrl, targetServiceName);
						rollbackSentRequest(session, apiUrl, transactionReferenceId, rsInfo);
					}
					else
					{
						int isRequestRolledBack = rsInfo.getIsRequestRolledBack();
						if (isRequestRolledBack != 1)
						{
							// getTargetServiceAPIUrl()
							String apiUrl = API_ROLLBACK_REQUEST_STATUS_REST_API_URL.replace("$$SERVICE_NAME_LOWER$$", targetServiceName.toLowerCase());
							apiUrl = getTargetServiceURL(apiUrl, targetServiceName);
							int isSentRequestRolledBack = isSentRequestRolledBack(session, rsInfo, apiUrl);
							if (isSentRequestRolledBack == 1)
							{
								updateRequestSentAsRolledBack(session, rsInfo);
							}
						}
						else
						{
							rolledbackRequestsCount++;
						}
					}
				}
				else
				{
					String requestStatus = rsInfo.getRequestStatus();
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(requestStatus))
					{
						requestStatus = getSentRequestStatus(session, rsInfo);
					}
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(requestStatus))
					{
						pendingRequestsCount++;
					}
					if (REQUEST_STATUS_FAILED.equalsIgnoreCase(requestStatus))
					{
						failedRequestsCount++;
					}
					if (REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(requestStatus))
					{
						successfullRequestsCount++;
					}
				}
				totalRequestsSentCount++;
			}
			if (rollbackRequest == 1)
			{
				if (rolledbackRequestsCount == totalRequestsSentCount)
				{
					updateRequestReceivedSentRequestsRolledback(session, requestId);
				}
			}
			else
			{
				if (totalRequestsSentCount == successfullRequestsCount)
				{
					RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestId);
					updateRequestReceivedSentRequestsStatus(session, requestReceived, REQUEST_STATUS_SUCCESSFULL);
				}
				else if (failedRequestsCount > 0)
				{
					RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestId);
					updateRequestReceivedSentRequestsStatus(session, requestReceived, REQUEST_STATUS_FAILED);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static String getSentRequestStatus(Session session, RequestSent rsInfo) throws Exception
	{
		try
		{
			int targetServiceRequestId = rsInfo.getTargetServiceRequestId();
			String targetServiceName = rsInfo.getServiceName();
			String apiUrl = API_REQUEST_STATUS_REST_API_URL.replace("$$SERVICE_NAME_LOWER$$", targetServiceName.toLowerCase());
			apiUrl = getTargetServiceURL(apiUrl, targetServiceName);
			JsonObject httpRequestInfo = HttpUtil.sendHttpRequest(apiUrl + "?targetservicerequestid=" + targetServiceRequestId + "&DBName=" + CommonUtil.getDatabaseName(session));
			int isTargetRequestStatusRetrieved = HttpUtil.isHttpRequestSuccessfullyExecuted(httpRequestInfo);
			if (isTargetRequestStatusRetrieved != 1)
			{
				return REQUEST_STATUS_PENDING;
			}
			String requestStatus = HttpUtil.getHttpRequestResponseAttributeText(httpRequestInfo, "requestStatus");
			if (REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(requestStatus) || REQUEST_STATUS_FAILED.equalsIgnoreCase(requestStatus))
			{
				updateRequestSentStatus(session, rsInfo, requestStatus);
			}
			return requestStatus;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return REQUEST_STATUS_PENDING;
	}
	/*
	 * public static void executePendingRequests(String serviceName) throws Exception
	 * {
	 * Session session = null;
	 * session = SessionFactoryBuilder.getDBSession(masterDBName);
	 * String hql = "from RequestReceived where serviceName = :serviceName ";
	 * Query query = session.createQuery(hql);
	 * query.setParameter("serviceName", serviceName);
	 * List resultsList = query.list();
	 * for (Iterator it = resultsList.iterator(); it.hasNext();)
	 * {
	 * RequestReceived rrInfo = (RequestReceived) it.next();
	 * int requestId = rrInfo.getRequestReceivedId();
	 * int rollBackRequest = rrInfo.getRollBackRequest();
	 * if (rollBackRequest == 1)
	 * {
	 * int isRequestRolledBack = rrInfo.getIsRequestRolledBack();
	 * 
	 * if (isRequestRolledBack != 1)
	 * {
	 * EntitiesHttpAPIUtil.executeHttpRollbackAPI(session, requestId);
	 * 
	 * if ("Core".equalsIgnoreCase(serviceName))
	 * {
	 * CoreService.rollbackRequest(session, requestId);
	 * }
	 * else if ("Sales".equalsIgnoreCase(serviceName))
	 * {
	 * SalesService.rollbackRequest(session, requestId);
	 * }
	 * 
	 * }
	 * int areSentRequestsRolledBack = rrInfo.getAreSentRequestsRolledBack();
	 * if (areSentRequestsRolledBack != 1)
	 * {
	 * updateSentRequestsStatus(session, requestId, rrInfo);
	 * }
	 * }
	 * else
	 * {
	 * String currentRequestStatus = rrInfo.getCurrentRequestStatus();
	 * int referenceRequestId = rrInfo.getReferenceRequestId();
	 * if (REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus) && referenceRequestId > 0)
	 * {
	 * EntitiesHttpAPIUtil.executeHttpAPI(session, requestId);
	 * 
	 * if ("Core".equalsIgnoreCase(serviceName))
	 * {
	 * CoreService.executeRequest(session, requestId);
	 * }
	 * else if ("Sales".equalsIgnoreCase(serviceName))
	 * {
	 * SalesService.executeRequest(session, requestId);
	 * }
	 * 
	 * }
	 * if (REQUEST_STATUS_FAILED.equalsIgnoreCase(currentRequestStatus))
	 * {
	 * rollbackRequestReceived(session, requestId);
	 * }
	 * String sentRequestsStatus = rrInfo.getSentRequestsStatus();
	 * if (REQUEST_STATUS_PENDING.equalsIgnoreCase(sentRequestsStatus) && referenceRequestId > 0)
	 * {
	 * updateSentRequestsStatus(session, requestId, rrInfo);
	 * }
	 * if (REQUEST_STATUS_FAILED.equalsIgnoreCase(sentRequestsStatus))
	 * {
	 * rollbackRequestReceived(session, requestId);
	 * }
	 * if (REQUEST_STATUS_PENDING.equalsIgnoreCase(sentRequestsStatus) && referenceRequestId < 0)
	 * {
	 * // If sent requests status of all the requests originated form this request are successfull then update sentRequestsStatus of this request as SUCCESSFULL
	 * // updateSentRequestsStatus(session, requestId, rrInfo);
	 * }
	 * }
	 * }
	 * }
	 */
	public static void executePendingRequests() throws Exception
	{
		PreparedStatement selectOrganisationsStmt = null;
		ResultSet organisationsSet = null;
		String microServiceName = "Default";
		Connection connection = null;
		try
		{
			connection = CommonUtil.getConnection(masterDBName);
			selectOrganisationsStmt = connection.prepareStatement("select databaseName from Organisations " + "where vwTxnStatus = 'AUTHORISED'");
			organisationsSet = selectOrganisationsStmt.executeQuery();
			while (organisationsSet.next())
			{
				String organisationsDBName = organisationsSet.getString("databaseName");
				executePendingRequestsInOrganisation(organisationsDBName, microServiceName);
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		finally
		{
			if (connection != null)
			{
				connection.close();
			}
		}
	}
	public static void executePendingRequestsInOrganisation(String organisationsDBName, String serviceName) throws Exception
	{
		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getDBSession(organisationsDBName);
			String hql = "from RequestReceived where serviceName = :serviceName ";
			Query query = session.createQuery(hql);
			query.setParameter("serviceName", serviceName);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				RequestReceived rrInfo = (RequestReceived) it.next();
				int requestId = rrInfo.getRequestReceivedId();
				int rollBackRequest = rrInfo.getRollBackRequest();
				int referenceRequestId = rrInfo.getReferenceRequestId();
				String currentRequestStatus = rrInfo.getCurrentRequestStatus();
				if (rollBackRequest == 1)
				{
					int isRequestRolledBack = rrInfo.getIsRequestRolledBack();
					if (isRequestRolledBack != 1 && referenceRequestId > 0)
					{
						String rollbackAPIName = rrInfo.getRollbackAPIName().trim();
						if (rollbackAPIName.length() > 0 && REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(currentRequestStatus))
						{
							EntitiesHttpAPIUtil.executeHttpRollbackAPI(session, requestId);
						}
						else
						{
							updateRequestReceivedInfoRolledback(session, requestId);
						}
					}
					int areSentRequestsRolledBack = rrInfo.getAreSentRequestsRolledBack();
					if (areSentRequestsRolledBack != 1 && referenceRequestId > 0)
					{
						updateSentRequestsStatus(session, requestId, rrInfo);
					}
					if (isRequestRolledBack != 1 && referenceRequestId < 0)
					{
						isRequestRolledBack = getRequestReceivedIsRequestRolledBack(session, requestId);
						if(isRequestRolledBack == 1)
						{
							updateRequestReceivedInfoRolledback(session, requestId);
						}
					}
					if (areSentRequestsRolledBack != 1 && referenceRequestId < 0)
					{
						areSentRequestsRolledBack = getRequestReceivedAreSentRequestsRolledBack(session, requestId);
						if(areSentRequestsRolledBack == 1)
						{
							updateRequestReceivedSentRequestsRolledback(session, requestId);
						}
					}
					if (isRequestRolledBack == 1 && areSentRequestsRolledBack == 1 && referenceRequestId < 0)
					{
						EntitiesHttpAPIUtil.updateAPIStatus(session, requestId);
					}
					updateRequestsRollbackRequestStatus(session, rrInfo, serviceName);
				}
				else
				{
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus) && referenceRequestId > 0)
					{
						EntitiesHttpAPIUtil.executeHttpAPI(session, requestId);
					}
					if (REQUEST_STATUS_FAILED.equalsIgnoreCase(currentRequestStatus))
					{
						rollbackRequestReceived(session, requestId);
					}
					String sentRequestsStatus = rrInfo.getSentRequestsStatus();
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(sentRequestsStatus) && referenceRequestId > 0)
					{
						updateSentRequestsStatus(session, requestId, rrInfo);
					}
					if (REQUEST_STATUS_FAILED.equalsIgnoreCase(sentRequestsStatus))
					{
						rollbackRequestReceived(session, requestId);
					}
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(sentRequestsStatus) && referenceRequestId < 0)
					{
						// If sent requests status of all the requests originated form this request are successfull then update sentRequestsStatus of this request as SUCCESSFULL
						// If sent requests status of atleast one of the requests originated form this request is FAILED then update sentRequestsStatus of this request as FAILED
						sentRequestsStatus = getRequestReceivedSentRequestsStatus(session, requestId);
						updateRequestReceivedSentRequestsStatus(session, rrInfo, sentRequestsStatus);
					}
					if (REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus) && referenceRequestId < 0)
					{
						// If "currentRequestStatus" of all the requests originated form this request are successfull then update "currentRequestStatus" of this request as SUCCESSFULL
						// If "currentRequestStatus" of atleast one of the requests originated form this request is FAILED then update "currentRequestStatus" of this request as FAILED
						currentRequestStatus = getRequestReceivedCurrentRequestStatus(session, requestId);
						updateRequestReceivedStatus(session, currentRequestStatus, requestId);
					}
					if (referenceRequestId < 0 && REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(currentRequestStatus) && REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(sentRequestsStatus))
					{
						// updateAPIStatus(); //isRequestUnderProcess is set to "0"
						EntitiesHttpAPIUtil.updateAPIStatus(session, requestId);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			session.close();
		}
	}
	public static void updateRequestsRollbackRequestStatus(Session session, RequestReceived rrInfo, String serviceName) throws Exception
	{
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			int referenceRequestId = rrInfo.getReferenceRequestId();
			if (referenceRequestId > 0)
			{
				RequestReceived referenceRequestReceived = (RequestReceived) session.get(RequestReceived.class, referenceRequestId);
				int rollbackRequest = referenceRequestReceived.getRollBackRequest();
				String referenceRequestServiceName = referenceRequestReceived.getServiceName();
				if (rollbackRequest == 0 && referenceRequestServiceName.equalsIgnoreCase(serviceName))
				{
					referenceRequestReceived.setRollBackRequest(1);
					session.update(referenceRequestReceived);
				}
			}
			String hql = "from RequestReceived where referenceRequestId = :referenceRequestId " + " and serviceName = :serviceName ";
			Query query = session.createQuery(hql);
			query.setParameter("referenceRequestId", rrInfo.getRequestReceivedId());
			query.setParameter("serviceName", serviceName);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				RequestReceived childRequestInfo = (RequestReceived) it.next();
				int rollbackRequest = childRequestInfo.getRollBackRequest();
				if (rollbackRequest == 0)
				{
					childRequestInfo.setRollBackRequest(1);
					session.update(childRequestInfo);
				}
			}
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
		}
	}
	public static String getRequestReceivedSentRequestsStatus(Session session, Integer requestId) throws Exception
	{
		String hql = "from RequestReceived where referenceRequestId = :referenceRequestId ";
		Query query = session.createQuery(hql);
		query.setParameter("referenceRequestId", requestId);
		List resultsList = query.list();
		Integer totalRequestsCount = 0;
		Integer totalSuccessfullRequestsCount = 0;
		Integer totalPendingRequestsCount = 0;
		Integer totalFailedRequestsCount = 0;
		String requestStatus = REQUEST_STATUS_PENDING;
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			RequestReceived rrInfo = (RequestReceived) it.next();
			totalRequestsCount++;
			String sentRequestsStatus = rrInfo.getSentRequestsStatus();
			if (REQUEST_STATUS_PENDING.equalsIgnoreCase(sentRequestsStatus))
			{
				totalPendingRequestsCount++;
			}
			else if (REQUEST_STATUS_FAILED.equalsIgnoreCase(sentRequestsStatus))
			{
				totalFailedRequestsCount++;
			}
			else if (REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(sentRequestsStatus))
			{
				totalSuccessfullRequestsCount++;
			}
		}
		if (totalRequestsCount == totalSuccessfullRequestsCount)
		{
			requestStatus = REQUEST_STATUS_SUCCESSFULL;
		}
		else if (totalPendingRequestsCount > 0)
		{
			requestStatus = REQUEST_STATUS_PENDING;
		}
		else if (totalFailedRequestsCount > 0)
		{
			requestStatus = REQUEST_STATUS_FAILED;
		}
		return requestStatus;
	}
	public static String getRequestReceivedCurrentRequestStatus(Session session, Integer requestId) throws Exception
	{
		String hql = "from RequestReceived where referenceRequestId = :referenceRequestId ";
		Query query = session.createQuery(hql);
		query.setParameter("referenceRequestId", requestId);
		List resultsList = query.list();
		Integer totalRequestsCount = 0;
		Integer totalSuccessfullRequestsCount = 0;
		Integer totalPendingRequestsCount = 0;
		Integer totalFailedRequestsCount = 0;
		String requestStatus = REQUEST_STATUS_PENDING;
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			RequestReceived rrInfo = (RequestReceived) it.next();
			totalRequestsCount++;
			String currentRequestStatus = rrInfo.getCurrentRequestStatus();
			if (REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus))
			{
				totalPendingRequestsCount++;
			}
			else if (REQUEST_STATUS_FAILED.equalsIgnoreCase(currentRequestStatus))
			{
				totalFailedRequestsCount++;
			}
			else if (REQUEST_STATUS_SUCCESSFULL.equalsIgnoreCase(currentRequestStatus))
			{
				totalSuccessfullRequestsCount++;
			}
		}
		if (totalRequestsCount == totalSuccessfullRequestsCount)
		{
			requestStatus = REQUEST_STATUS_SUCCESSFULL;
		}
		else if (totalPendingRequestsCount > 0)
		{
			requestStatus = REQUEST_STATUS_PENDING;
		}
		else if (totalFailedRequestsCount > 0)
		{
			requestStatus = REQUEST_STATUS_FAILED;
		}
		return requestStatus;
	}
	public static Integer getRequestReceivedIsRequestRolledBack(Session session, Integer requestId) throws Exception
	{
		String hql = "from RequestReceived where referenceRequestId = :referenceRequestId ";
		Query query = session.createQuery(hql);
		query.setParameter("referenceRequestId", requestId);
		List resultsList = query.list();
		Integer totalRequestsCount = 0;
		Integer totalRequestsRolledBackCount = 0;
		Integer totalPendingRequestsCount = 0;
		Integer isRequestRolledBack = 0;
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			RequestReceived rrInfo = (RequestReceived) it.next();
			totalRequestsCount++;
			Integer isCurrentRequestRolledBack = rrInfo.getIsRequestRolledBack();
			if (isCurrentRequestRolledBack == 1)
			{
				totalRequestsRolledBackCount++;
			}
			else
			{
				totalPendingRequestsCount++;
			}
		}
		if (totalRequestsCount == totalRequestsRolledBackCount)
		{
			isRequestRolledBack = 1;
		}
		return isRequestRolledBack;
	}
	public static Integer getRequestReceivedAreSentRequestsRolledBack(Session session, Integer requestId) throws Exception
	{
		String hql = "from RequestReceived where referenceRequestId = :referenceRequestId ";
		Query query = session.createQuery(hql);
		query.setParameter("referenceRequestId", requestId);
		List resultsList = query.list();
		Integer totalRequestsCount = 0;
		Integer sentRequestsRolledBackCount = 0;
		Integer totalPendingRequestsCount = 0;
		Integer areSentRequestsRolledBack = 0;
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			RequestReceived rrInfo = (RequestReceived) it.next();
			totalRequestsCount++;
			Integer sentRequestsRolledBack = rrInfo.getAreSentRequestsRolledBack();
			if (sentRequestsRolledBack == 1)
			{
				sentRequestsRolledBackCount++;
			}
			else
			{
				totalPendingRequestsCount++;
			}
		}
		if (totalRequestsCount == sentRequestsRolledBackCount)
		{
			areSentRequestsRolledBack = 1;
		}
		return areSentRequestsRolledBack;
	}
	public static String getTargetServiceURL(String URL, String targetServiceName)
	{
		String targetServiceURL = URL;
		if(1 > 2)
		{
		}
				else if(targetServiceName.equalsIgnoreCase("Core"))
		{
			targetServiceURL = targetServiceURL.replace("$$SERVICE_HOSTNAME$$", "http://localhost");
			targetServiceURL = targetServiceURL.replace("$$SERVICE_PORT$$", "8085");
		}

		return targetServiceURL;
	}
}
