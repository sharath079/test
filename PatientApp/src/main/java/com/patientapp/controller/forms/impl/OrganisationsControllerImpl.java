package com.patientapp.controller.forms.impl;
import org.hibernate.Session;
import java.util.HashMap;
import com.patientapp.bean.Organisations;
import com.patientapp.util.*;
import java.math.BigDecimal;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import com.vw.runtime.VWResponseBean;
import com.patientapp.controller.forms.base.OrganisationsLabel;
import com.patientapp.controller.forms.base.OrganisationsControllerBase;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.vw.runtime.IRulesFields;
import com.vw.runtime.RulesBean;
import com.vw.runtime.VWMastersBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;


import com.patientapp.util.CommonUtil;
import com.patientapp.util.OrganisationsUtil;

@SuppressWarnings("unused")
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 * 
 */
/*@ManagedBean(name = "OrganisationsControllerImpl")
@RequestScoped*/
public class OrganisationsControllerImpl extends OrganisationsControllerBase 
{
	public OrganisationsControllerImpl(Session session)
	{
		super(session);
	}
	public OrganisationsControllerImpl(Session session, JsonObject standardReqParams)
	{
		super(session, standardReqParams);
	}
	public OrganisationsControllerImpl()
	{
		super();
	}
	
	
	public void doEnrichValuesImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doAfterEnrichValuesImpl()
	{
		//Custom handling
	}
	
	
	
	public void doValidationsImpl()
	{
		//Custom handling
	}
	
	
	
	public String getLcNoImpl()
	{
		return "";
	}
	
	
	
	public void doAfterSetValues()
	{
		//Custom handling
	}		
	
	
	
	public void doAfterSelectRowImpl()
	{
		//Custom handling
	}
	
	
	
	public void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
		try
		{
			
		} 
		catch (Exception e)
		{
        	CommonUtil.writeTolog(e);
			addCustomResponse("Verification token could not be generated to verify email id.");
			return;
		}
	}
	
	
	
	public void doAfterCreateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	public void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap)
	{
		//Custom handling
	}
	public String doGetUpdatedQueryStringForSearchImpl(String queryString)
	{
		//Custom handling
		return "";
	}
	
	
	public void doAfterCreateTransactionCommittedImpl()
	{
		//Custom handling
	}
	
	public void doAfterUpdateTransactionCommittedImpl()
	{
		//Custom handling
	}
	
	
	public void doAfterUpdateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doBeforeDeleteTransactionImpl()
	{
		//Custom handling	
	}
	
	
	
	public void doAfterDeleteTransactionImpl()
	{
		//Custom handling	
	}
	
	
	
	public void doAfterEntityLoadedImpl(Organisations organisations, JsonObject initParamsInfo)
	{
		//Custom handling	
	}
	
	
	
	public JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject organisationsDataObject, Organisations organisations)
	{
		// Custom handling
		JsonObject dataObject = new JsonObject();
		try
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("alert", "This API (" + apiName + ") has no implementation !!");
		dataObject.addProperty("success", 0);
		return dataObject; 		
	}
	
	
	
	public void doExecuteCustomAPIImpl(String customAPIMessage)
	{
		// Custom handling
	}
	
	public void doAfterLookupRowSelectedImpl(Organisations organisations, String attributeName)
	{
		//Custom handling	
	}
	public void doAfterSelectOptionChangedImpl(Organisations organisations, String attributeName)
	{
		//Custom handling	
	}
	public void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams)
	{
		//Custom handling	
	}
	public void verifyIfTransactionIsDeletableImpl()
	{
		//Custom handling
	}
	public void verifyIfTransactionIsUpdatableImpl()
	{
		//Custom handling
	}
	

public void doBeforeTransactionApprovedImpl()
{
	// Custom handling
	try
	{
		OrganisationsUtil organisationsUtil = new OrganisationsUtil();
		organisationsUtil.initializeStoreDB(getDBSession(), (Organisations) getPrivateManagedBean(), this);
	}
	catch (Exception e)
	{
		addCustomResponse("Error while creating Organisations database");
		writeToLog(CommonUtil.getStackTrace(e));
	}
}

	public void doAfterTransactionApprovedImpl()
	{
		//Custom handling
	}
	
	
	public void doBeforeTransactionRolledbackImpl()
	{
		//Custom handling
	}
	
	
	
	public void doRefreshFromUIImpl()
	{
		//Custom handling
	}
	
	
	
	public void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex)
	{
		//Custom handling
	}
	
	
	
	//Custom code
	
	
	
	public void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap)
	{
		//Custom handling
	}
	
	
	
	public String doGetOrderByClauseSearchImpl()
	{
		//Custom handling
		String orderByClause = "  ";
		return orderByClause;
	}
	
}



