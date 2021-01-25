package com.patientapp.request.util;
import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.layout.QueryBuilderReporting;
import com.patientapp.util.PrivilegeChecker;
public class APIRequestHandler
{
	protected JsonObject handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, String organisationsDBName, Connection storeConn, Connection genericConn)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 0;
		try
		{
			String requestType = request.getParameter("requestType").trim();
			if (requestType == null)
			{
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Request type is null");
			}
			else if ("transferToLedger".equalsIgnoreCase(requestType))
			{
				isRequestHandled = 1;
				System.out.println("After getting request type value 2");
				String payrollRunInfoIdString = request.getParameter("payrollRunInfoId").trim();
				if (payrollRunInfoIdString != null && payrollRunInfoIdString.length() > 0)
				{
					Integer payrollRunInfoId = Integer.parseInt(payrollRunInfoIdString);
//					PayrollRunInfoUtil utilObj = new PayrollRunInfoUtil();
//					utilObj.processPayrollRunCustomizations(payrollRunInfoId, dataObject);
				}
			}
			else if ("updateDestinationInventory".equalsIgnoreCase(requestType))
			{
				isRequestHandled = 1;
				System.out.println("After getting request type value 2");
				String warehouseConsignmentInfoIdString = request.getParameter("warehouseConsignmentInfoId").trim();
				if (warehouseConsignmentInfoIdString != null && warehouseConsignmentInfoIdString.length() > 0)
				{
					Integer warehouseConsignmentInfoId = Integer.parseInt(warehouseConsignmentInfoIdString);
//					WarehouseConsignmentInfoUtil utilObj = new WarehouseConsignmentInfoUtil();
//					utilObj.processWarehouseConsignmentCustomizations(warehouseConsignmentInfoId, dataObject);
				}
			}
			else if ("sendEmail".equalsIgnoreCase(requestType))
			{
				isRequestHandled = 1;
//				String pageLinkNameDisplayPrefix = request.getParameter("pageLinkNameDisplayPrefix").trim();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
//				dataObject = CommonUtil.sendEmail(storeConn, paramsMap);
				// dataObject = CommonUtil.sendSMS(storeConn, paramsMap);
				if (dataObject.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("isRequestHandled", isRequestHandled);
					return dataObject;
				}
			}			
			else
			{
				isRequestHandled = 0;
			}
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
}
