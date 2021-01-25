package com.patientapp.controller.forms.impl;
import org.hibernate.Session;
import java.util.HashMap;
import com.patientapp.bean.PrivilegeGroup;
import com.patientapp.util.*;
import java.math.BigDecimal;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import com.vw.runtime.VWResponseBean;
import com.patientapp.controller.forms.base.PrivilegeGroupLabel;
import com.patientapp.controller.forms.base.PrivilegeGroupControllerBase;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.vw.runtime.IRulesFields;
import com.vw.runtime.RulesBean;
import com.vw.runtime.VWMastersBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;


import org.hibernate.Query;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

@SuppressWarnings("unused")
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 * 
 */
/*@ManagedBean(name = "PrivilegeGroupControllerImpl")
@RequestScoped*/
public class PrivilegeGroupControllerImpl extends PrivilegeGroupControllerBase 
{
	public PrivilegeGroupControllerImpl(Session session)
	{
		super(session);
	}
	public PrivilegeGroupControllerImpl(Session session, JsonObject standardReqParams)
	{
		super(session, standardReqParams);
	}
	public PrivilegeGroupControllerImpl()
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
		org.hibernate.Session session = getDBSession();
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) getManagedBean();
		String privilegeCode = privilegeGroup.getPrivilegeCode();
		if(privilegeCode.equalsIgnoreCase("SYSADMIN"))
		{
			setTransactionFailureMessage("This Privilege group could not be updated as it is Admin Default Privilege group.");
			return;
		}
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
		org.hibernate.Session session = getDBSession();
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) getManagedBean();
		String privilegeCode = privilegeGroup.getPrivilegeCode();
		if(privilegeCode.equalsIgnoreCase("SYSADMIN"))
		{
			setTransactionFailureMessage("This Privilege group could not be deleted as it is Admin Default Privilege group.");
			return;
		}
	}

	
	
	public void doAfterDeleteTransactionImpl()
	{
		//Custom handling	
	}
	
	
	
	public void doAfterEntityLoadedImpl(PrivilegeGroup privilegeGroup, JsonObject initParamsInfo)
	{
		//Custom handling	
	}
	
	

public JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject privilegeGroupDataObject, PrivilegeGroup privilegeGroup)
{
	// Custom handling
	JsonObject dataObject = new JsonObject();
	Session session = getDBSession();
	try
	{
		String privilegeCode = privilegeGroup.getPrivilegeCode();
		if(privilegeCode.equalsIgnoreCase("SYSADMIN"))
		{
			dataObject.addProperty("alert", "This Privilege group could not be deleted as it is Admin Default Privilege group.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if("updatePrivilegeGroupItemsList".equalsIgnoreCase(apiName))
		{
			int privilegeGroupId = privilegeGroup.getPrivilegeGroupId();
			String sql = "DELETE FROM PrivilegeGroupItems 	where privilegeGroupId = :privilegeGroupId ";
			Query nq = session.createQuery(sql);
			nq.setParameter("privilegeGroupId", privilegeGroupId);
			nq.executeUpdate();
			JsonArray privilegeGroupItemsList = privilegeGroupDataObject.get("privilegeGroupItemsList").getAsJsonArray();
			for(int i =0; i< privilegeGroupItemsList.size(); i++)
			{
				JsonObject privilegeGroupItemInfo = privilegeGroupItemsList.get(i).getAsJsonObject();
				String privilegeActionType = privilegeGroupItemInfo.get("privilegeActionType").getAsString();
				String privilegeObjectType = privilegeGroupItemInfo.get("privilegeObjectType").getAsString();
				String privilegeObjectName = privilegeGroupItemInfo.get("privilegeObjectName").getAsString();
				com.patientapp.bean.PrivilegeGroupItems privilegeGroupItems = new com.patientapp.bean.PrivilegeGroupItems();
				privilegeGroupItems.setPrivilegeGroupId(privilegeGroupId);
				privilegeGroupItems.setPrivilegeActionType(privilegeActionType);
				privilegeGroupItems.setPrivilegeObjectType(privilegeObjectType);
				privilegeGroupItems.setPrivilegeObjectName(privilegeObjectName);
				PrivilegeGroupItemsControllerImpl  privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(getDBSession(), getUserSessionInfo());
				privilegeGroupItemsControllerImpl.setManagedBean(privilegeGroupItems);
				privilegeGroupItemsControllerImpl.create();
				if(privilegeGroupItemsControllerImpl.getHasTransactionFailed() == true)
				{
					dataObject.addProperty("alert", privilegeGroupItemsControllerImpl.getTransactionFailureMessage());
					dataObject.addProperty("success", 0);
					return dataObject; 		
				}
			}
			dataObject.addProperty("success", 1);
			dataObject.addProperty("alert", "Privilege Group Items updated successfully.");
			return dataObject; 		
		}			
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
	
	public void doAfterLookupRowSelectedImpl(PrivilegeGroup privilegeGroup, String attributeName)
	{
		//Custom handling	
	}
	public void doAfterSelectOptionChangedImpl(PrivilegeGroup privilegeGroup, String attributeName)
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
		//Custom handling
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





