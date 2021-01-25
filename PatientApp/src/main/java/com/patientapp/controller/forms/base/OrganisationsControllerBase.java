package com.patientapp.controller.forms.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigDecimal;
import java.sql.Time;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import javax.servlet.http.HttpServletRequest;
import com.patientapp.request.service.HttpUtil;
import com.patientapp.request.service.ServiceAPIUtil;
import com.patientapp.request.service.RequestReceived;
	
//

import com.patientapp.bean.Organisations;
import com.patientapp.controller.forms.base.OrganisationsLabel;
import com.vw.runtime.BusinessRulesController;
import com.vw.runtime.VWViewObject;
import com.patientapp.util.ZipExtraction;
import com.vw.runtime.VWSessionObject;
import com.vw.runtime.VWMastersBean;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.UploadDownloadUtil;
@SuppressWarnings({"unused","unchecked"})
/**
 *
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 *
 */
public abstract class OrganisationsControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'OrganisationName' 
	 *		 * 'AddressLine1' 
	 *		 * 'AddressLine2' 
	 *		 * 'City' 
	 *		 * 'ResidentState' 
	 *		 * 'PinCode' 
	 *		 * 'DatabaseName' 
	 *		 * 'Country' 
	 *	
	 *
	 */
	/*
	 * userSessionInfo Parameters:
	 * ================
	 * loggedInEmployeeId
	 * isUserLoggedIn
	 * loginEntityType 
	 * userId;
	 */	
	Integer iPKValue=0;
	protected JsonObject userSessionInfo = new JsonObject();
	protected JsonObject additionalInfo = new JsonObject();
	protected boolean isTransactionUpdatable;
	protected boolean isDeletionAllowed;
	protected OrganisationsLabel OrganisationsLabelLocal = new OrganisationsLabel();
	protected Organisations OrganisationsLocal = new Organisations();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public OrganisationsControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public OrganisationsControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public OrganisationsControllerBase()
	{
		super();
	}
	public JsonObject getUserSessionInfo() 
	{
		return userSessionInfo;
	}
	public JsonObject getAdditionalInfo() 
	{
		return additionalInfo;
	}
	public void writeToLog(String logMessage) 
	{
		System.out.println(logMessage);
	}
 	protected String getPkFieldName()
	{
		return "Organisations" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((Organisations)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((Organisations)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((Organisations)obj).getOrganisationsId();
	}
	public String getManagedBeanName()
    {
		return "OrganisationsBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "OrganisationsLabelBean";
    }
	protected Class<Organisations> setBeanClass()
	{
		return Organisations.class;
	}
	protected Class<OrganisationsLabel> setBeanLabelClass()
	{
		return OrganisationsLabel.class;
	}
	protected Organisations getLocalManagedBean()
    {
		return (Organisations)getManagedBean();
    }
	
	protected String getAuthAmtCcy()
	{
		return "INR";
	}
	protected BigDecimal getAuthAmt()
	{
		return BigDecimal.ZERO;
	}
	public void refreshFromUI()
	{
		doRefreshFromUIImpl();
	}
	public void doBeforeTransactionApproved()
	{
		/*
		 * Use below code as required in impl
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		/*Organisations organisations = (Organisations)getManagedBean();
		String areChangesEffected = organisations.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			organisations.setAreChangesEffected("YES");			
		}*/
		JsonObject dataObject = new JsonObject();		
			
		int generatedRequestId = -1;
			
		doBeforeTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doAfterTransactionApproved()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);		
	}
	public void doBeforeTransactionRolledback()
	{
		/*
		 * Use below code as required in impl
		 */
		/*Organisations organisations = (Organisations)getManagedBean();
		String areChangesEffected = organisations.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			organisations.setAreChangesEffected("NO");			
		}*/
		int generatedRequestId = -1;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();	doBeforeTransactionRolledbackImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isAuthorizationEnabled()
	{
		return true;		
	}
	public boolean isRollbackRequired()
	{
		return true;
	}	
	public boolean isSubmitRequired()
	{
		return true;
	}	
	public boolean isTransactionUpdatable()
	{
		/*
		 * Use below code as required in impl
		 */		
		/*Organisations organisations = (Organisations)getManagedBean();
		String areChangesEffected = organisations.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			return false;
		}
		else
		{
			return true;			
		}*/		
		isTransactionUpdatable = true;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsUpdatableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		return isTransactionUpdatable;
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		Organisations organisations = (Organisations)getManagedBean();
		String sCurrentStatus = organisations.getVwTxnStatus();
		if(isRollbackRequired()==true)
		{
			if("COMPLETED".equalsIgnoreCase(sCurrentStatus) && sAction.matches(ACTION_UPDATE))
			{
				if(!("ROLLBACK".equalsIgnoreCase(sAction)))
				{
					String sParams[] = new String[1];
					sParams[0] = sAction;
					addApplicationResponse("VWT", "ERR0016", sParams);
					handleResponse(); // GDN: do not move it outside
					return false;			
				}
			}			
		}
		return true;		
	}
	/*
	 * Business logic where clause parameters can be added based on the entity and the attribute 
	 * on which the lookup popup is opened. 
	 */
	public HashMap doBeforeLookupEntitiesRetrieved(String callingEntityName, String callingAttributeName)
	{
		HashMap customSearchParams = new HashMap();
	    /*
	     * Sample handling in impl
	     */
		/*if("SetOfBooksInfo".equalsIgnoreCase(callingEntityName))
		{
			if("ParentAccountGroup".equalsIgnoreCase(callingEntityName))
			{
				SetOfBooksInfo setOfBooksInfo = ((AccountGroups)getManagedBean()).getSetOfBooksInfo();
				customSearchParams.put("SetOfBooksInfo", setOfBooksInfo);				
			}
		}*/
		doBeforeLookupEntitiesRetrievedImpl(callingEntityName, callingAttributeName, customSearchParams);
		return customSearchParams;
	}
	public void doAfterSelectOptionChanged(String attributeName)
	{
		Organisations organisations = (Organisations)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("city".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("residentState".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("country".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(organisations, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterOrganisationsLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		Organisations organisations = (Organisations)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("organisationName") && initParamsInfo.get("organisationName").isJsonNull()==false)
		{
			String organisationName = initParamsInfo.get("organisationName").getAsString();
			organisations.setOrganisationName(organisationName);			
		}if(initParamsInfo.has("addressLine1") && initParamsInfo.get("addressLine1").isJsonNull()==false)
		{
			String addressLine1 = initParamsInfo.get("addressLine1").getAsString();
			organisations.setAddressLine1(addressLine1);			
		}if(initParamsInfo.has("addressLine2") && initParamsInfo.get("addressLine2").isJsonNull()==false)
		{
			String addressLine2 = initParamsInfo.get("addressLine2").getAsString();
			organisations.setAddressLine2(addressLine2);			
		}if(initParamsInfo.has("city") && initParamsInfo.get("city").isJsonNull()==false)
		{
			String city = initParamsInfo.get("city").getAsString();
			organisations.setCity(city);			
		}if(initParamsInfo.has("residentState") && initParamsInfo.get("residentState").isJsonNull()==false)
		{
			String residentState = initParamsInfo.get("residentState").getAsString();
			organisations.setResidentState(residentState);			
		}if(initParamsInfo.has("pinCode") && initParamsInfo.get("pinCode").isJsonNull()==false)
		{
			String pinCode = initParamsInfo.get("pinCode").getAsString();
			organisations.setPinCode(pinCode);			
		}if(initParamsInfo.has("databaseName") && initParamsInfo.get("databaseName").isJsonNull()==false)
		{
			String databaseName = initParamsInfo.get("databaseName").getAsString();
			organisations.setDatabaseName(databaseName);			
		}if(initParamsInfo.has("country") && initParamsInfo.get("country").isJsonNull()==false)
		{
			String country = initParamsInfo.get("country").getAsString();
			organisations.setCountry(country);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(organisations, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		Organisations organisations = (Organisations)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		Organisations organisations = (Organisations)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(organisations, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{
		isTransactionUpdatable = isTransactionUpdatable();
		if(isTransactionUpdatable==false)
		{
			return false;
		}
		isDeletionAllowed = true;
		/*
		 * set "isDeletionAllowed" flag to false in impl 
		 * if deletion has to be prevented
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsDeletableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isDeletionAllowed==false)
		{
			addCustomResponse("This transaction cannot be deleted !!");
		}
		return isDeletionAllowed;
	}	
	public void beforeDeleteTransaction(boolean clearSession)
	{
		try
		{JsonObject dataObject = new JsonObject();		
				
				
			if(isTransactionUpdatable()==false)
			{
				addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
			}
			Organisations organisations = (Organisations)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*
			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doBeforeDeleteTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void afterDeleteTransaction()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doAfterDeleteTransactionImpl();				
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeCreateTransaction(JsonObject paramsInfoObj)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		doBeforeCreateTransactionImpl(paramsInfoObj);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		//setHasTransactionFailed(false);
	}
	protected void afterCreateTransaction(JsonObject paramsInfoObj)
	{
		/*
		if (isActionFromUI())
		{
			try
			{
				if (!isEntityAuditType(getManagedBeanName()))
				{
					// Refetch MQ Record as the session does not contain MessageQueueBean
					String sCurrentLCNo = getLcNoImpl();
					com.patientapp.bean.MessageQueue messageQueue = new com.patientapp.bean.MessageQueue();
						if (isExists(sCurrentLCNo))
						{
							com.patientapp.controller.forms.impl.MessageQueueControllerImpl messageController = new com.patientapp.controller.forms.impl.MessageQueueControllerImpl();
							List<?> lst = messageController.retrieveUniqueTransaction("LcNo", sCurrentLCNo);
							messageQueue = (com.patientapp.bean.MessageQueue) lst.get(0);
							// Fetch newly created entity id 
							String sEntityId = getPKValue().toString();
							// Create a new MQ record 
							com.vw.runtime.rest.MessageQueueService mqservice = new com.vw.runtime.rest.MessageQueueService();
							String sMsgGroupName=getGroupName();
							if (!isExists(sMsgGroupName))
							{
								if (!isExists(messageQueue.getMsgGroup()))
								{
									sMsgGroupName="EXPORT";
								}
							}
							String sMsgDirection="I";
							String sMsgCategory="";
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "Organisations", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
						}
				}
			}
			catch (Exception e)
			{
				writeToLog(CommonUtil.getStackTrace(e));
			}
		}
		*/
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterCreateTransactionImpl(paramsInfoObj);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterCreateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterCreateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		//doBeforeUpdateTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void afterUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterUpdateTransactionImpl(paramsInfoObject);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterUpdateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterUpdateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected HashMap<String, Object> getSearchParams()
	{
		debugCode("In getSearchParams() OrganisationsContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (OrganisationsSearch)getManagedBean("OrganisationsSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getOrganisationName()))
			{
				searchParams.put(OrganisationsLabelLocal.getorganisationNameFieldName(),searchBeanLocal.getOrganisationName());
			}	
			if (isExists(searchBeanLocal.getAddressLine1()))
			{
				searchParams.put(OrganisationsLabelLocal.getaddressLine1FieldName(),searchBeanLocal.getAddressLine1());
			}	
			if (isExists(searchBeanLocal.getAddressLine2()))
			{
				searchParams.put(OrganisationsLabelLocal.getaddressLine2FieldName(),searchBeanLocal.getAddressLine2());
			}	
			if (isExists(searchBeanLocal.getCity()))
			{
				searchParams.put(OrganisationsLabelLocal.getcityFieldName(),searchBeanLocal.getCity());
			}	
			if (isExists(searchBeanLocal.getResidentState()))
			{
				searchParams.put(OrganisationsLabelLocal.getresidentStateFieldName(),searchBeanLocal.getResidentState());
			}	
			if (isExists(searchBeanLocal.getPinCode()))
			{
				searchParams.put(OrganisationsLabelLocal.getpinCodeFieldName(),searchBeanLocal.getPinCode());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(OrganisationsLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() OrganisationsContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues OrganisationsContollerBase");
		targetBean = (Organisations)targetBean;((Organisations)targetBean).setOrganisationsId(((Organisations)sourceBean).getOrganisationsId());
				((Organisations)targetBean).setOrganisationName(((Organisations)sourceBean).getOrganisationName());
		((Organisations)targetBean).setAddressLine1(((Organisations)sourceBean).getAddressLine1());
		((Organisations)targetBean).setAddressLine2(((Organisations)sourceBean).getAddressLine2());
		((Organisations)targetBean).setCity(((Organisations)sourceBean).getCity());
		((Organisations)targetBean).setResidentState(((Organisations)sourceBean).getResidentState());
		((Organisations)targetBean).setPinCode(((Organisations)sourceBean).getPinCode());
		((Organisations)targetBean).setDatabaseName(((Organisations)sourceBean).getDatabaseName());
		((Organisations)targetBean).setCountry(((Organisations)sourceBean).getCountry());

		doAfterSetValues();
		debugCode("Out setValues OrganisationsContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.Organisations entityBean = (Organisations)entityObject;
	 	if (sFieldName.equalsIgnoreCase("organisationsId")) 
	 	{
			return entityBean.getOrganisationsId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("OrganisationName")) 
	 	{
			return entityBean.getOrganisationName();
		}
	 	if (sFieldName.equalsIgnoreCase("AddressLine1")) 
	 	{
			return entityBean.getAddressLine1();
		}
	 	if (sFieldName.equalsIgnoreCase("AddressLine2")) 
	 	{
			return entityBean.getAddressLine2();
		}
	 	if (sFieldName.equalsIgnoreCase("City")) 
	 	{
			return entityBean.getCity();
		}
	 	if (sFieldName.equalsIgnoreCase("ResidentState")) 
	 	{
			return entityBean.getResidentState();
		}
	 	if (sFieldName.equalsIgnoreCase("PinCode")) 
	 	{
			return entityBean.getPinCode();
		}
	 	if (sFieldName.equalsIgnoreCase("DatabaseName")) 
	 	{
			return entityBean.getDatabaseName();
		}
	 	if (sFieldName.equalsIgnoreCase("Country")) 
	 	{
			return entityBean.getCountry();
		}

	 return null;
	}
	
	
	protected void doEnrichValues(Boolean doUpdateRules)
	{
		JsonObject paramsInfoObject  = new JsonObject();
		doEnrichValues(doUpdateRules, paramsInfoObject);
	}
	protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject)
	{
		if (doUpdateRules)
		{
			callBusinessUpdateRules();
		}	
		doEnrichValuesImpl(paramsInfoObject);
	}
	protected void doAfterEnrichValues()
	{
		doAfterEnrichValuesImpl();
		doCreateAuditRecord();
	}
	private void doCreateAuditRecord()
	{
		
	}	
	protected void doAfterSelectRow()
	{
		doAfterSelectRowImpl();
	}
	protected void doEnrichSystemValues(String sAction,String sNextStatus)
	{
		debugCode("In doEnrichSystemValues(String sAction) OrganisationsControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((Organisations) localManagedBean).setVwLastModifiedDate(new Date());
		((Organisations) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((Organisations) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((Organisations) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((Organisations) localManagedBean).setVwTxnStatus("CREATED");
			((Organisations) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((Organisations) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((Organisations) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) OrganisationsControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) OrganisationsControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) OrganisationsControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations OrganisationsControllerBase");
		//NG: Important comment
		//managedBean = (Organisations) getManagedBean();
		localManagedBean = getLocalManagedBean();
		setPrivateManagedBean(localManagedBean);
		doDataTypeValidation();
		doLengthValidation();
		doMandatoryValidation();
		doAllowedValuesValidation();
		doAllowedDecimalValidation();
		if (!isFromChild())
		{
			doUniqueValidation();
		}	
		callBusinessValidateRules();
		doValidationsImpl();
		debugCode("Out doValidations OrganisationsControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation OrganisationsControllerBase");
		debugCode("Out doAllowedDecimalValidation OrganisationsControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation OrganisationsControllerBase");
				
		String sFieldValue5 = ((Organisations) localManagedBean).getCity();if (!isExists(sFieldValue5,localMasters.getCity())) addAllowedValuesResponse(OrganisationsLabelLocal.getcityFieldName());String sFieldValue6 = ((Organisations) localManagedBean).getResidentState();if (!isExists(sFieldValue6,localMasters.getResidentState())) addAllowedValuesResponse(OrganisationsLabelLocal.getresidentStateFieldName());String sFieldValue9 = ((Organisations) localManagedBean).getCountry();if (!isExists(sFieldValue9,localMasters.getCountry())) addAllowedValuesResponse(OrganisationsLabelLocal.getcountryFieldName());

		debugCode("Out doAllowedValuesValidation OrganisationsControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation OrganisationsControllerBase");
				
		String sFieldValue2 = ((Organisations) localManagedBean).getOrganisationName();String sFieldValue3 = ((Organisations) localManagedBean).getAddressLine1();String sFieldValue5 = ((Organisations) localManagedBean).getCity();String sFieldValue6 = ((Organisations) localManagedBean).getResidentState();String sFieldValue7 = ((Organisations) localManagedBean).getPinCode();String sFieldValue8 = ((Organisations) localManagedBean).getDatabaseName();String sFieldValue9 = ((Organisations) localManagedBean).getCountry();
		if (!isExists(sFieldValue2)) addMandatoryResponse(OrganisationsLabelLocal.getorganisationNameFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(OrganisationsLabelLocal.getaddressLine1FieldName());
		if (!isExists(sFieldValue5)) addMandatoryResponse(OrganisationsLabelLocal.getcityFieldName());
		if (!isExists(sFieldValue6)) addMandatoryResponse(OrganisationsLabelLocal.getresidentStateFieldName());
		if (!isExists(sFieldValue7)) addMandatoryResponse(OrganisationsLabelLocal.getpinCodeFieldName());
		if (!isExists(sFieldValue8)) addMandatoryResponse(OrganisationsLabelLocal.getdatabaseNameFieldName());
		if (!isExists(sFieldValue9)) addMandatoryResponse(OrganisationsLabelLocal.getcountryFieldName());
debugCode("Out doMandatoryValidation OrganisationsControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation OrganisationsControllerBase");
				
		String sFieldValue2 = ((Organisations) localManagedBean).getOrganisationName();String sFieldValue3 = ((Organisations) localManagedBean).getAddressLine1();String sFieldValue4 = ((Organisations) localManagedBean).getAddressLine2();String sFieldValue5 = ((Organisations) localManagedBean).getCity();String sFieldValue6 = ((Organisations) localManagedBean).getResidentState();String sFieldValue7 = ((Organisations) localManagedBean).getPinCode();String sFieldValue8 = ((Organisations) localManagedBean).getDatabaseName();String sFieldValue9 = ((Organisations) localManagedBean).getCountry();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getorganisationNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getaddressLine1FieldName(),"50");
		if (!isLengthAllowed(sFieldValue4,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getaddressLine2FieldName(),"50");
		if (!isLengthAllowed(sFieldValue5,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getcityFieldName(),"50");
		if (!isLengthAllowed(sFieldValue6,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getresidentStateFieldName(),"50");
		if (!isLengthAllowed(sFieldValue7,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getpinCodeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue8,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getdatabaseNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue9,"50")) addMaxLengthResponse(OrganisationsLabelLocal.getcountryFieldName(),"50");
debugCode("Out doLengthValidation OrganisationsControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation OrganisationsControllerBase");
		debugCode("Out doDataTypeValidation OrganisationsControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation OrganisationsContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
						
			String sFieldValue2Uniq = ((Organisations) localManagedBean).getOrganisationName();String sFieldValue8Uniq = ((Organisations) localManagedBean).getDatabaseName();

						handleUniqueValidation(OrganisationsLabelLocal.getorganisationNameFieldName(),sFieldValue2Uniq);
			handleUniqueValidation(OrganisationsLabelLocal.getdatabaseNameFieldName(),sFieldValue8Uniq);
		}	
		debugCode("In doUniqueValidation OrganisationsContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel OrganisationsContollerBase");
		String sLabel = new OrganisationsLabel().getLabel(sResponseField); 
		debugCode("Out getLabel OrganisationsContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int organisationsId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("organisationsId", organisationsId);
			JsonObject responseData = retrieveOrganisations(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Organisations' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject organisationsDataObject = responseData.get("organisationsDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, organisationsDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Organisations' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveOrganisations(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer organisationsId = -1;
		if(requestParams.has("organisationsId"))
		{
			organisationsId = requestParams.get("organisationsId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("organisationsId", String.valueOf(organisationsId));
				String organisationName = "";
		if(requestParams.has("organisationName"))
		{
			paramsMap.put("organisationName", requestParams.get("organisationName").getAsString());
		}
		String addressLine1 = "";
		if(requestParams.has("addressLine1"))
		{
			paramsMap.put("addressLine1", requestParams.get("addressLine1").getAsString());
		}
		String addressLine2 = "";
		if(requestParams.has("addressLine2"))
		{
			paramsMap.put("addressLine2", requestParams.get("addressLine2").getAsString());
		}
		String city = "";
		if(requestParams.has("city"))
		{
			paramsMap.put("city", requestParams.get("city").getAsString());
		}
		String residentState = "";
		if(requestParams.has("residentState"))
		{
			paramsMap.put("residentState", requestParams.get("residentState").getAsString());
		}
		String pinCode = "";
		if(requestParams.has("pinCode"))
		{
			paramsMap.put("pinCode", requestParams.get("pinCode").getAsString());
		}
		String databaseName = "";
		if(requestParams.has("databaseName"))
		{
			paramsMap.put("databaseName", requestParams.get("databaseName").getAsString());
		}
		String country = "";
		if(requestParams.has("country"))
		{
			paramsMap.put("country", requestParams.get("country").getAsString());
		}

		JsonObject organisationsListObject = retrieveOrganisationsList(paramsMap);
		if(organisationsListObject!=null && organisationsListObject.has("success") && organisationsListObject.get("success").getAsInt()==1)
		{
			JsonArray organisationsList = organisationsListObject.get("organisationsList").getAsJsonArray();
			if(organisationsList.size()>0)
			{
				dataObject.add("organisationsDataObject", organisationsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Organisations' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getOrganisations(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			Map<String, String> paramsMap = new HashMap<String, String>();
						String organisationName = "";
			if(searchParams.has("organisationName"))
			{
				paramsMap.put("organisationName", searchParams.get("organisationName").getAsString());
			}
			String addressLine1 = "";
			if(searchParams.has("addressLine1"))
			{
				paramsMap.put("addressLine1", searchParams.get("addressLine1").getAsString());
			}
			String addressLine2 = "";
			if(searchParams.has("addressLine2"))
			{
				paramsMap.put("addressLine2", searchParams.get("addressLine2").getAsString());
			}
			String city = "";
			if(searchParams.has("city"))
			{
				paramsMap.put("city", searchParams.get("city").getAsString());
			}
			String residentState = "";
			if(searchParams.has("residentState"))
			{
				paramsMap.put("residentState", searchParams.get("residentState").getAsString());
			}
			String pinCode = "";
			if(searchParams.has("pinCode"))
			{
				paramsMap.put("pinCode", searchParams.get("pinCode").getAsString());
			}
			String databaseName = "";
			if(searchParams.has("databaseName"))
			{
				paramsMap.put("databaseName", searchParams.get("databaseName").getAsString());
			}
			String country = "";
			if(searchParams.has("country"))
			{
				paramsMap.put("country", searchParams.get("country").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject organisationsListObject = retrieveOrganisationsList(paramsMap);
			if(organisationsListObject!=null && organisationsListObject.has("success") && organisationsListObject.get("success").getAsInt()==1)
			{
				JsonArray organisationsList = organisationsListObject.get("organisationsList").getAsJsonArray();
				if(organisationsList.size()>0)
				{
					dataObject.add("organisations", organisationsList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Organisations' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getOrganisationsInJson(int organisationsId)
	{
		JsonObject OrganisationsData = null;
		List<Integer> organisationsIdsList = new ArrayList<>();
		organisationsIdsList.add(organisationsId);
		JsonArray organisationsList = getOrganisationsListInJson(organisationsIdsList);
		if(organisationsList!=null && organisationsList.size()>0)
		{
			OrganisationsData = organisationsList.get(0).getAsJsonObject();
		}
		return OrganisationsData;
	}
	public JsonArray getOrganisationsListInJson(List<Integer> organisationsIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray organisationsObjectsList = new JsonArray();
		JsonObject organisationsListObject = retrieveOrganisationsList(paramsMap, organisationsIdsList);
		if(organisationsListObject!=null && organisationsListObject.has("success") && organisationsListObject.get("success").getAsInt()==1)
		{
			JsonArray organisationsList = organisationsListObject.get("organisationsList").getAsJsonArray();
			for (int i =0; i< organisationsList.size(); i++)
			{
				JsonObject organisationsDataObject = organisationsList.get(i).getAsJsonObject();
				int organisationsId = organisationsDataObject.get("organisationsId").getAsInt();
				
				organisationsObjectsList.add(organisationsDataObject);
			}
		}
		return organisationsObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray organisationsList)
	{
		String errorMessage = "organisationsList: \n";
		for (int i =0; i< organisationsList.size(); i++)
		{
			JsonObject organisationsDataObject = organisationsList.get(i).getAsJsonObject();
			JsonObject organisations = organisationsDataObject.get("dataObject").getAsJsonObject();
						
			errorMessage += "Company Name : "+ organisations.get("organisationName").getAsString();

			if(!organisationsDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "organisations could not be uploaded verify data \n"; 
			}
			else if(organisationsDataObject.has("isSuccessfullyUploaded") 
					&& organisationsDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += organisationsDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    
		}
		return errorMessage;		
	}
	public String getLoginBasedWhereClause(java.util.Map<String, String> paramsMap)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("Organisations".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND organisationsId = :organisationsId ";
			return loginBasedWhereClause;
		}return "";		
	}
	public void setLoginBasedWhereClauseParams(java.util.Map<String, String> paramsMap, Query query)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("Organisations".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("organisationsId", userId);
		}
		
	}
	public String getParentFilterClauseForOrganisations(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";return parentFilterClause;
	}
	public String getLookupDisplayFilterClause()
	{
		String lookupDisplayFilterClause = "";
		String lookupDisplayQueryColumn = " AND concat(";
		int i= 0;
				if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "organisationName";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForOrganisations(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveOrganisationsList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> organisationsIdsList = new ArrayList<>();
		if(paramsMap.containsKey("organisationsId"))
		{
			int organisationsId = Integer.parseInt(paramsMap.get("organisationsId"));
			if(organisationsId>0)
			{
				organisationsIdsList.add(organisationsId);
			}
		}
		return retrieveOrganisationsList(paramsMap, organisationsIdsList);
	}
	public JsonObject retrieveOrganisationsList(java.util.Map<String, String> paramsMap, List<Integer> organisationsIdsList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			int noOfRecordsAlreadyFetched = 0;
			int noOfRecordsToFetch = 0;
			String overrideWhereClause="";
			String whereClause ="";
			String orderByClause = "";
			String overrideOrderByClause="";
			String lookupDisplayPrefix="";
			String txnStatus="";
			JsonArray additionalParamsList = new JsonArray();
			if(paramsMap.containsKey("noOfRecordsAlreadyFetched"))
			{
				noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));				
			}
			if(paramsMap.containsKey("noOfRecordsToFetch"))
			{
				noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));				
			}
			if(paramsMap.containsKey("overrideWhereClause"))
			{
				overrideWhereClause = paramsMap.get("overrideWhereClause");				
			}
			if(paramsMap.containsKey("whereClause"))
			{
				whereClause = paramsMap.get("whereClause");				
			}
			if(paramsMap.containsKey("orderByClause"))
			{
				orderByClause = paramsMap.get("orderByClause");				
			}
			if(paramsMap.containsKey("overrideOrderByClause"))
			{
				overrideOrderByClause = paramsMap.get("overrideOrderByClause");				
			}
			if(paramsMap.containsKey("lookupDisplayPrefix"))
			{
				lookupDisplayPrefix = paramsMap.get("lookupDisplayPrefix");
			}
			if(paramsMap.containsKey("additionalParamsList"))
			{
				additionalParamsList = new Gson().fromJson(paramsMap.get("additionalParamsList"), JsonArray.class);
			}
			if(paramsMap.containsKey("txnStatus"))
			{
				txnStatus = paramsMap.get("txnStatus");
			}
						String organisationName = paramsMap.get("organisationName");
			if(organisationName==null)
			{
				organisationName = "";
			}
			String addressLine1 = paramsMap.get("addressLine1");
			if(addressLine1==null)
			{
				addressLine1 = "";
			}
			String addressLine2 = paramsMap.get("addressLine2");
			if(addressLine2==null)
			{
				addressLine2 = "";
			}
			String city = paramsMap.get("city");
			if(city==null)
			{
				city = "";
			}
			String residentState = paramsMap.get("residentState");
			if(residentState==null)
			{
				residentState = "";
			}
			String pinCode = paramsMap.get("pinCode");
			if(pinCode==null)
			{
				pinCode = "";
			}
			String databaseName = paramsMap.get("databaseName");
			if(databaseName==null)
			{
				databaseName = "";
			}
			String country = paramsMap.get("country");
			if(country==null)
			{
				country = "";
			}
String hql = "FROM Organisations where 1 = 1 ";
			String orderByClauseText = "  ";
			String organisationsIdFilterClass = "";
			if (organisationsIdsList != null && organisationsIdsList.size() > 0)
			{
				organisationsIdFilterClass = " AND organisationsId in (:idsList) ";
			}
						String organisationNameFilterClass = "";
			if (organisationName.length() > 0)
			{
				
				
				
				
				
				
				
				organisationNameFilterClass = " AND organisationName LIKE :organisationName ";
				
				
				
				
			}
			String addressLine1FilterClass = "";
			if (addressLine1.length() > 0)
			{
				
				
				
				
				
				
				
				addressLine1FilterClass = " AND addressLine1 LIKE :addressLine1 ";
				
				
				
				
			}
			String addressLine2FilterClass = "";
			if (addressLine2.length() > 0)
			{
				
				
				
				
				
				
				
				addressLine2FilterClass = " AND addressLine2 LIKE :addressLine2 ";
				
				
				
				
			}
			String cityFilterClass = "";
			if (city.length() > 0)
			{
				
				
				
				
				
				
				
				cityFilterClass = " AND city LIKE :city ";
				
				
				
				
			}
			String residentStateFilterClass = "";
			if (residentState.length() > 0)
			{
				
				
				
				
				
				
				
				residentStateFilterClass = " AND residentState LIKE :residentState ";
				
				
				
				
			}
			String pinCodeFilterClass = "";
			if (pinCode.length() > 0)
			{
				
				
				
				
				
				
				
				pinCodeFilterClass = " AND pinCode LIKE :pinCode ";
				
				
				
				
			}
			String databaseNameFilterClass = "";
			if (databaseName.length() > 0)
			{
				
				
				
				
				
				
				
				databaseNameFilterClass = " AND databaseName LIKE :databaseName ";
				
				
				
				
			}
			String countryFilterClass = "";
			if (country.length() > 0)
			{
				
				
				
				
				
				
				
				countryFilterClass = " AND country LIKE :country ";
				
				
				
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForOrganisations(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					organisationsIdFilterClass +
										organisationNameFilterClass +
					addressLine1FilterClass +
					addressLine2FilterClass +
					cityFilterClass +
					residentStateFilterClass +
					pinCodeFilterClass +
					databaseNameFilterClass +
					countryFilterClass +

					lookupDisplayFilterClause+
					txnStatusFilterClass;
			if(overrideWhereClause.equalsIgnoreCase("Yes"))
			{
				attributesFilterClause +=  whereClause;
			}
			String whereClauseText = 
			getLoginBasedWhereClause(paramsMap) +
			attributesFilterClause;
			if(overrideOrderByClause.equalsIgnoreCase("Yes"))
			{
				orderByClauseText =  orderByClause;
			}
			hql += whereClauseText +
			doGetUpdatedQueryStringForSearchImpl(hql) +
			orderByClauseText;
			Query query = getDBSession().createQuery(hql);
			if (organisationsIdsList != null && organisationsIdsList.size() > 0)
			{
				query.setParameterList("idsList", organisationsIdsList);
			}
						if (organisationName.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("organisationName", organisationName);
				
				
				
				
			}
			if (addressLine1.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("addressLine1", addressLine1);
				
				
				
				
			}
			if (addressLine2.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("addressLine2", addressLine2);
				
				
				
				
			}
			if (city.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("city", city);
				
				
				
				
			}
			if (residentState.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("residentState", residentState);
				
				
				
				
			}
			if (pinCode.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("pinCode", pinCode);
				
				
				
				
			}
			if (databaseName.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("databaseName", databaseName);
				
				
				
				
			}
			if (country.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("country", country);
				
				
				
				
			}
if(lookupDisplayPrefix.length() > 0)
			{
				lookupDisplayPrefix = "%"+lookupDisplayPrefix+"%";
				query.setParameter("lookupDisplayPrefix", lookupDisplayPrefix);
			}
			if(txnStatus.length() > 0)
			{
				query.setParameter("txnStatus", txnStatus);
			}
	        for(int i =0; i< additionalParamsList.size(); i++)
	        {
	        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
	        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
				query.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
	        }
			setLoginBasedWhereClauseParams(paramsMap, query);
	    	setParentFilterClauseParamsForOrganisations(paramsMap, query);
			java.util.Map<String, Object> queryParamsMap = new java.util.HashMap<String, Object>(); 
			JsonObject userSessionInfo = getUserSessionInfo();			
			String loggedInEmployeeId = "-1";//userSessionInfo.get("loggedInEmployeeId").getAsString();			
			Object loggedInEmployeeIdObj= loggedInEmployeeId;  
			queryParamsMap.put("loggedInEmployeeId", loggedInEmployeeIdObj);
			doUpdateQueryWithParameterValuesImpl(query, queryParamsMap);			
			if(noOfRecordsToFetch>0)
			{
				query.setMaxResults(noOfRecordsToFetch);
				query.setFirstResult(noOfRecordsAlreadyFetched);				
			}
			List resultsList = query.list();
			JsonArray organisationsList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Organisations organisations = (Organisations) it.next();
				JsonObject organisationsDataObject = organisations.getDataObject(getDBSession());
				organisationsDataObject.addProperty("nextAction", getActionForCurrentStatus(organisations.getVwTxnStatus()));
				organisationsList.add(organisationsDataObject);
				doAfterSearchResultRowAddedImpl(organisationsDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from Organisations where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (organisationsIdsList != null && organisationsIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", organisationsIdsList);
				}
								if (organisationName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("organisationName", organisationName);
					
					
					
					
				}
				if (addressLine1.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("addressLine1", addressLine1);
					
					
					
					
				}
				if (addressLine2.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("addressLine2", addressLine2);
					
					
					
					
				}
				if (city.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("city", city);
					
					
					
					
				}
				if (residentState.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("residentState", residentState);
					
					
					
					
				}
				if (pinCode.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("pinCode", pinCode);
					
					
					
					
				}
				if (databaseName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("databaseName", databaseName);
					
					
					
					
				}
				if (country.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("country", country);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForOrganisations(paramsMap, countQuery);
		        for(int i =0; i< additionalParamsList.size(); i++)
		        {
		        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
		        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
		        	countQuery.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
		        }
				if(txnStatus.length() > 0)
				{
					countQuery.setParameter("txnStatus", txnStatus);
				}
				Long matchingSearchResultsCount = (Long) countQuery.uniqueResult();
				dataObject.addProperty("matchingSearchResultsCount", matchingSearchResultsCount);
			}
			dataObject.add("organisationsList",   organisationsList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getDashboardGraphsData()
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		String hql = " SELECT concat(month(E.vwCreationDate), '/', year(E.vwCreationDate)), count(*) "
				+ "   from Organisations E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
		Query query = getDBSession().createQuery(hql);
		List resultsList = query.list();
		JsonArray monthlyTransactionList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("monthName", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			monthlyTransactionList.add(monthlyTransactionInfo);
		}
		dataObject.add("monthlyTransactionList", monthlyTransactionList);
		hql = " SELECT E.vwTxnStatus, count(*) "
				+ "   from Organisations E GROUP BY E.vwTxnStatus ";
		query = getDBSession().createQuery(hql);
		resultsList = query.list();
		JsonArray transactionStatusList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("statusDescription", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			transactionStatusList.add(monthlyTransactionInfo);
		}
		dataObject.add("transactionStatusList", transactionStatusList);
		//dataObject.add("monthlyTransactionList", new JsonArray());
		//dataObject.add("transactionStatusList", new JsonArray());
		dataObject.addProperty("success", 1);
		return dataObject;
	}
	public JsonObject retrieveDependentOrganisationsList(java.util.Map<String, String> paramsMap)
	{
		return retrieveOrganisationsList(paramsMap);
	}
	public Organisations getOrganisationsByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from Organisations where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Organisations organisations = (Organisations) it.next();
			return organisations;
		}
		return null;
	}
	private void callBusinessValidateRules()
	{
		loadInitObjects();
	
	}	
	private void callBusinessUpdateRules()
	{
		loadInitObjects();
		//$$INCLUDE_UPDATE_RULES$$
		Organisations organisations = (Organisations)getManagedBean();
		JsonObject organisationsDataObject = organisations.getDataObject(getDBSession());copyOrganisationsFromJson(organisations, organisationsDataObject);
		setManagedBean(organisations);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData OrganisationsContollerBase");
			Organisations currentBean = (Organisations)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "OrganisationName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setOrganisationName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "AddressLine1".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setAddressLine1(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "AddressLine2".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setAddressLine2(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "City".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setCity(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "ResidentState".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setResidentState(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PinCode".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPinCode(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "DatabaseName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setDatabaseName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "Country".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setCountry(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData OrganisationsContollerBase");
	}
	// end Test Data
	public void copyOrganisationsFromJson(Organisations organisations, JsonObject organisationsDataObject)
	{
		copyOrganisationsFromJson(organisations, organisationsDataObject, false);
	}
	public void copyOrganisationsFromJson(Organisations organisations, JsonObject organisationsDataObject, boolean excludePasswordFields)
	{	
				
		if(organisationsDataObject.has("organisationName"))
		{
			String organisationName = organisationsDataObject.get("organisationName").getAsString();
			organisations.setOrganisationName(organisationName);
		}if(organisationsDataObject.has("addressLine1"))
		{
			String addressLine1 = organisationsDataObject.get("addressLine1").getAsString();
			organisations.setAddressLine1(addressLine1);
		}if(organisationsDataObject.has("addressLine2"))
		{
			String addressLine2 = organisationsDataObject.get("addressLine2").getAsString();
			organisations.setAddressLine2(addressLine2);
		}if(organisationsDataObject.has("city"))
		{
			String city = organisationsDataObject.get("city").getAsString();
			organisations.setCity(city);
		}if(organisationsDataObject.has("residentState"))
		{
			String residentState = organisationsDataObject.get("residentState").getAsString();
			organisations.setResidentState(residentState);
		}if(organisationsDataObject.has("pinCode"))
		{
			String pinCode = organisationsDataObject.get("pinCode").getAsString();
			organisations.setPinCode(pinCode);
		}if(organisationsDataObject.has("databaseName"))
		{
			String databaseName = organisationsDataObject.get("databaseName").getAsString();
			organisations.setDatabaseName(databaseName);
		}if(organisationsDataObject.has("country"))
		{
			String country = organisationsDataObject.get("country").getAsString();
			organisations.setCountry(country);
		}
		
	}
		
	public JsonObject createOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		return createOrganisations(organisationsDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject organisationsDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createOrganisations(JsonObject organisationsDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		Organisations organisations = new Organisations();
		setLoginBasedValues(paramsInfoObj, organisationsDataObject);
		Session session = getDBSession();				
		copyOrganisationsFromJson(organisations, organisationsDataObject);
		if(organisationsDataObject.has("legacyRecordId"))
		{
			organisations.setLegacyRecordId(organisationsDataObject.get("legacyRecordId").getAsInt());
		}
				organisations.setVwCreatedBy(createdBy);
		organisations.setVwCreationDate(new Date());
		setPrivateManagedBean(organisations);
		setManagedBean("OrganisationsBean", organisations);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		organisations = (Organisations) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("organisationsId", organisations.getOrganisationsId());
			JsonObject organisationsObj = organisations.getDataObject(getDBSession());
			organisationsObj.addProperty("nextAction", getActionForCurrentStatus(organisations.getVwTxnStatus()));
			dataObject.add("organisationsDataObject", organisationsObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer organisationsId = organisationsDataObject.get("organisationsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("organisationsId", organisationsId);
		JsonObject responseData = retrieveOrganisations(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Organisations' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);
		if(organisations == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(organisations.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromOrganisations(organisationsDataObject);
		boolean excludePasswordFields = true;
		copyOrganisationsFromJson(organisations, organisationsDataObject, excludePasswordFields);setPrivateManagedBean(organisations);
		setManagedBean("OrganisationsBean", organisations);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		organisations.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
		{
			update();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction updated Successfully");
			dataObject.addProperty("organisationsId", organisationsId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromOrganisations(JsonObject organisationsDataObject) throws Exception
	{
	}
	public JsonObject deleteOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer organisationsId = organisationsDataObject.get("organisationsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("organisationsId", organisationsId);
		JsonObject responseData = retrieveOrganisations(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Organisations' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);setPrivateManagedBean(organisations);
		setManagedBean("Organisations", organisations);
		if (getHasTransactionFailed() == false)
		{
			delete();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction deleted Successfully");
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject fetchOrganisationsDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		Organisations organisations = new Organisations();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(organisations);
			setManagedBean("Organisations", organisations);
			doAfterOrganisationsLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("organisations", organisations.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchOrganisationsTestData(int obj, JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		Organisations organisations = new Organisations();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrganisationsFromJson(organisations, organisationsDataObject);
			setPrivateManagedBean(organisations);
			setManagedBean("Organisations", organisations);
			doAfterOrganisationsLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("organisations", organisations.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject lookupRowSelectedForOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		Organisations organisations = new Organisations();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrganisationsFromJson(organisations, organisationsDataObject);	String attributeName = organisationsDataObject.get("attributeName").getAsString();
			Integer attributeId = organisationsDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(organisations);
			setManagedBean("Organisations", organisations);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("organisations", organisations.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Lookup nformation could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject selectOptionChangedForOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		Organisations organisations = new Organisations();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrganisationsFromJson(organisations, organisationsDataObject);	
			String attributeName = organisationsDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(organisations);
			setManagedBean("Organisations", organisations);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("organisations", organisations.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Information could not be refreshed.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Information could not be refreshed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject doExecuteCustomAPI(JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer organisationsId = organisationsDataObject.get("organisationsId").getAsInt();
			String customEventName = organisationsDataObject.get("customEventName").getAsString();
			Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);
			setPrivateManagedBean(organisations);
			setManagedBean("OrganisationsBean", organisations);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("organisations", organisations.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", customEventName + " processed successfully.");
				dataObject.add("additionalInfo", getAdditionalInfo());
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.add("additionalInfo", getAdditionalInfo());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		finally
		{
			finalizeTransaction();
		}
	}
	public JsonObject executeAuthorisationOnOrganisations(JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer organisationsId = organisationsDataObject.get("organisationsId").getAsInt();
			String currentStatus = organisationsDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(organisationsDataObject.has("currentAction"))
			{
				currentAction = organisationsDataObject.get("currentAction").getAsString();
			}
			Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);
			setPrivateManagedBean(organisations);
			setManagedBean("OrganisationsBean", organisations);
			if(organisations.getIsRequestUnderProcesss() == 1)
			{
				setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(!isActionAllowedOnTransaction(currentAction))
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(currentAction.equalsIgnoreCase(ACTION_REJECT))
			{
				executeAction(organisations, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(organisations, "ClassInfoBean", currentStatus);
			}
//			executeAction(organisations, "OrganisationsBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedorganisationsDataObject = organisations.getDataObject(getDBSession());
				updatedorganisationsDataObject.addProperty("nextAction", getActionForCurrentStatus(organisations.getVwTxnStatus()));
				dataObject.add("organisations", updatedorganisationsDataObject);
				if(!currentAction.equalsIgnoreCase(ACTION_REJECT))
				{
					currentAction = getActionForCurrentStatus(currentStatus);
				}
				dataObject.addProperty("alert", "Transaction "+ currentAction + " successfully.");
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public boolean isActionAllowedOnTransaction(String currentAction)
	{
		Organisations organisations = (Organisations) getManagedBean();
		String currentStatus = organisations.getVwTxnStatus();
		if(currentStatus.equalsIgnoreCase("REJECTED"))
		{
			setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
			return false;
		}
		if(currentAction.equalsIgnoreCase(ACTION_REJECT))
		{
			if(!(currentStatus.equalsIgnoreCase("CREATED")
					||currentStatus.equalsIgnoreCase("MODIFIED")))
			{
				setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
				return false;
			}
		}
		return true;
	}
	
	
	public JsonObject downloadOrganisationsData() throws Exception
	{
		return downloadOrganisationsData(null);
	}
	public JsonObject downloadOrganisationsData(HSSFWorkbook workbook) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
		try
		{
			
			boolean saveWorkbook = false;
			HSSFSheet sheet = null;
			if(workbook == null)
			{
				String projectTemplatesPath = com.patientapp.util.SettingsUtil.getProjectTemplatesPath();
				FileInputStream file = new FileInputStream(new File(projectTemplatesPath+"DownloadTemplate.xls"));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			sheet = workbook.cloneSheet(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Organisations");
			HSSFFont defaultFont = workbook.createFont();
			defaultFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			HSSFFont boldfont = workbook.createFont();
			boldfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			CellStyle dataStyle = workbook.createCellStyle(); // this style is used for the cells where bold text is needed other than header
			dataStyle.setFont(defaultFont);
			dataStyle.setWrapText(true);
			CellStyle headerStyle = sheet.getRow(0).getCell(0).getCellStyle();
			Cell cell;
			Row row;
			
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("currentRowPosition", 0);
			rowColumnIndexObject.addProperty("entityDataCellIndex", 0);
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			int headerCellCount = entityDataCellIndex;
			int columnWidth = 3000;
			String headerName = "";
			row = sheet.createRow(currentRowPosition++);cell = row.createCell(headerCellCount++);
			headerName = "S.No";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "organisationsId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "organisationName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "addressLine1";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "addressLine2";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "city";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "residentState";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "pinCode";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "databaseName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "country";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			headerStyle = workbook.createCellStyle(); // this style is used for table header in the report
			headerStyle.setFont(boldfont);
			headerStyle.setWrapText(true);
			Transaction tx = getDBSession().getTransaction();
			if(!tx.isActive())
			{
				tx.begin();
			}
			String hql = "From Organisations ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				Organisations organisations = (Organisations) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer organisationsId = organisations.getOrganisationsId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(organisationsId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String organisationName = organisations.getOrganisationName();
				cell.setCellValue(organisationName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String addressLine1 = organisations.getAddressLine1();
				cell.setCellValue(addressLine1);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String addressLine2 = organisations.getAddressLine2();
				cell.setCellValue(addressLine2);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String city = organisations.getCity();
				cell.setCellValue(city);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String residentState = organisations.getResidentState();
				cell.setCellValue(residentState);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String pinCode = organisations.getPinCode();
				cell.setCellValue(pinCode);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String databaseName = organisations.getDatabaseName();
				cell.setCellValue(databaseName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String country = organisations.getCountry();
				cell.setCellValue(country);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "Organisations.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "Organisations.xls";
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				int fileId = CommonUtil.saveFile(fileName, savedFileName, session);
				if(fileId < 0)
				{
					dataObject.addProperty("alert", "Your request could not be processed.");
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadOrganisationsData(JsonObject organisationsDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = organisationsDataObject.get("fileId").getAsInt();
		String inputFilesZip = organisationsDataObject.get("inputFilesZip").getAsString();
		String savedFileName = CommonUtil.getFilePath("", fileId, getDBSession());
		FileInputStream file = new FileInputStream(new File(savedFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		String inputFilesPath = "";
		if(inputFilesZip.length() > 0)
		{
			String zipFilePath = CommonUtil.getFilePath(inputFilesZip, -1, getDBSession());
	        String extractedZipFilePath = FilenameUtils.removeExtension(zipFilePath);
	        int isExtracted = ZipExtraction.extractZipFile(extractedZipFilePath, zipFilePath);
	        if(isExtracted != 1)
	        {
	    		dataObject.addProperty("alert", "Organisations Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		organisationsDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadOrganisationsData(workbook, organisationsDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Organisations Data uploaded successfully.");
		dataObject.addProperty("success", 1);
		dataObject.addProperty("fileId", fileId);
		return dataObject;
	}
	public HashMap<Integer, String> getHeaderRowColumnNamesMap(Row headerRow, int entityDataCellIndex)
	{
		HashMap<Integer, String> cellIndexParameterMap = new HashMap<Integer, String>();
		boolean continueReadingRow = true;
		int headerRowCellIndex = entityDataCellIndex + 1;
		while (continueReadingRow)
		{
			Cell headerCell = headerRow.getCell(headerRowCellIndex);
			boolean cellExist = false;
			if(headerCell != null)
			{
				String parameterKeyName = headerCell.getStringCellValue();
				if(parameterKeyName != null && parameterKeyName.length() > 0)
				{
					cellIndexParameterMap.put(headerRowCellIndex, parameterKeyName);
					headerRowCellIndex++;
					cellExist = true;
				}
			}
			if(cellExist == false)
			{
				continueReadingRow = false;
			}
		}
		return cellIndexParameterMap;		
	}
	public JsonObject uploadOrganisationsData(HSSFWorkbook workbook, JsonObject organisationsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("Organisations");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = organisationsDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = organisationsDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = organisationsDataObject.get("fileId").getAsInt();
				//savedFileName = CommonUtil.fileIDAndNamesMap.get(fileId);
				savedFileName = CommonUtil.getFilePath("", fileId, session);
				FileInputStream file = new FileInputStream(new File(savedFileName));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			successCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			successCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont successFontColor = workbook.createFont();
			successFontColor.setColor(HSSFColor.GREEN.index);
			successCellStyle.setFont(successFontColor);
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			errorCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			errorCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont errorFontColor = workbook.createFont();
			errorFontColor.setColor(HSSFColor.RED.index);
			errorCellStyle.setFont(errorFontColor);
			JsonObject dataUploadInfo = processExcelSheetData(workbook, sheet, successCellStyle, errorCellStyle, areSourceDestinationSame, inputFilesPath);			
			if(dataUploadInfo!=null && dataUploadInfo.has("dataListToRetry") && dataUploadInfo.get("dataListToRetry").isJsonNull()==false)
			{
				int retryCount = 0;
				JsonArray dataListToRetry = dataUploadInfo.get("dataListToRetry").getAsJsonArray();
				dataObject.add("dataListToRetry", dataListToRetry);			
				if(dataUploadInfo.has("retryCount") && dataUploadInfo.get("retryCount").isJsonNull()==false)
				{
					retryCount = dataUploadInfo.get("retryCount").getAsInt();
				}				
				dataObject.addProperty("retryCount", retryCount);
			}
			if(saveWorkbook == true)
			{
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				dataObject.addProperty("alert", "Organisations Data uploaded successfully.");
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject processExcelSheetData(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, Integer areSourceDestinationSame, String inputFilesPath) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			int statusCellIndex = 50;
			int errorCount = 0;
			Cell lastCell = null;
			Row row = null;
			int totalDefinedRowsInSheet = sheet.getLastRowNum() + 1;
			int currentRowPosition = 0;
			int entityDataCellIndex = 0;
			int pendingRecordsCount = 0;
			int batchsize = 100;
			Row headerRow = sheet.getRow(currentRowPosition);
			HashMap<Integer, String> cellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex);
			currentRowPosition++;
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex);
			rowColumnIndexObject.addProperty("areSourceDestinationSame", areSourceDestinationSame);
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			JsonArray dataListToRetry = new JsonArray();
			JsonObject organisations = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject organisationsListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray organisationsList = organisationsListObj.get("organisationsList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadOrganisationsList(organisationsList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(organisationsList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(organisationsList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
			}
			dataObject.addProperty("success", 1);
			dataObject.add("dataListToRetry", dataListToRetry);			
			dataObject.addProperty("retryCount", totalRetryCount);			
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchData(HSSFWorkbook workbook, HSSFSheet sheet, int totalDefinedRowsInSheet, int batchsize, JsonObject rowColumnIndexObject, HashMap<Integer, String> cellIndexParameterMap, Integer areSourceDestinationSame,String inputFilesPath) throws Exception	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{			
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			int statusCellIndex = 50;
			JsonArray organisationsList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject organisations = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    
				if(pendingRecordsCount == batchsize && batchsize>0)
				{
					break;
				}
				JsonObject organisationsUploadObj	= new JsonObject();
				organisationsUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					organisations = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("organisationsId"))
						{
							String organisationsId = row.getCell(cellIndex++).getStringCellValue();
							if(organisationsId != null && organisationsId.trim().length() > 0)
							{
								try
								{
									Integer iOrganisationsId = Integer.parseInt(organisationsId);
									if(areSourceDestinationSame == 1)
									{
										Organisations organisationsObj = (Organisations)session.get(Organisations.class, iOrganisationsId);
										if(organisationsObj != null)
										{ 
											organisations.addProperty("organisationsId", iOrganisationsId);
										}
										else
										{
											organisationsUploadObj.addProperty("isDataFetched", 0);
											organisationsUploadObj.addProperty("msg", "This Organisations could not be uploaded as there appears to be some problem with the data(Organisations Id is not exist). ");
											continue;
										}
									}
									else
									{
										Organisations organisationsObj = getOrganisationsByLegacyRecordId(session, iOrganisationsId);
										if(organisationsObj != null)
										{ 
											organisations.addProperty("organisationsId", organisationsObj.getOrganisationsId());
										}
										organisations.addProperty("legacyRecordId", iOrganisationsId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									organisationsUploadObj.addProperty("isDataFetched", 0);
									organisationsUploadObj.addProperty("msg", "This Organisations could not be uploaded as there appears to be some problem with the data(Organisations Id). ");
									continue;
								}
							}
						}
						else
						{
							String parameterValue = row.getCell(cellIndex++).getStringCellValue();
							if(inputFilesPath.length() > 0)
							{
								
							}
							organisations.addProperty(parameterName, parameterValue);
						}
					}
					organisationsUploadObj.add("dataObject", organisations);		    
					organisationsList.add(organisationsUploadObj);
					pendingRecordsCount++;
					
				}
				catch (Exception e)
				{
					writeToLog(CommonUtil.getStackTrace(e));
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					continue;
				}
			}
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			dataObject.add("organisationsList", organisationsList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int  areSomeRecordsUploaded(JsonObject previousRetryCountMap, JsonObject retryCountMap) throws Exception
	{
		int previousRetryCount = 0;
		int retryCount = 0;
		int areSomeRecordsUploaded = 0;
		if(previousRetryCountMap.has("Organisations") && previousRetryCountMap.get("Organisations").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("Organisations").getAsInt();
		}
		if(retryCountMap.has("Organisations") && retryCountMap.get("Organisations").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("Organisations").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForOrganisationsList(JsonArray organisationsList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < organisationsList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject organisationsDataObject = organisationsList.get(i).getAsJsonObject();
			JsonObject organisations = organisationsDataObject.get("dataObject").getAsJsonObject();
			if(organisationsDataObject.has("retryUpload") && organisationsDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = organisationsDataObject.get("retryUpload").getAsInt();
			}
			if(organisationsDataObject.has("retryChildEntitiesUpload") && organisationsDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = organisationsDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(organisationsDataObject.has("partialUploadUnderProcess") && organisationsDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = organisationsDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("Organisations", retryCount);
	}
	public JsonObject uploadOrganisationsList(JsonArray organisationsList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(organisationsList);
			if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("alert", responseData.get("alert").getAsString());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			int retryCount = responseData.get("retryCount").getAsInt();
			dataObject.addProperty("success", 1);
			dataObject.addProperty("retryCount", retryCount);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject updateStatusMsg(JsonArray organisationsList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < organisationsList.size(); i++)
			{
				JsonObject organisationsDataObject = organisationsList.get(i).getAsJsonObject();
				JsonObject organisations = organisationsDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = organisationsDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = organisationsDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = organisationsDataObject.get("errorMessage").getAsString();
				row = sheet.getRow(dataRowIndex);
				lastCell = row.createCell(statusCellIndex);
				if(isSuccessfullyUploaded == 1)
				{
					lastCell.setCellStyle(successCellStyle);
				}
				else
				{
					lastCell.setCellStyle(errorCellStyle);
				} 
				lastCell.setCellValue(errorMessage);
			    
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadData(JsonArray organisationsList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < organisationsList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject organisationsDataObject = organisationsList.get(i).getAsJsonObject();
				JsonObject organisations = organisationsDataObject.get("dataObject").getAsJsonObject();
				organisationsDataObject.addProperty("retryUpload", 0);
				organisationsDataObject.addProperty("retryChildEntitiesUpload", 0);
				organisationsDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = organisationsImplObj.getEntityInfoUpdatedWithLookupIds(session, organisations, retValObject);
				if(areAllLookupsFound!=1)
				{
					organisationsDataObject.addProperty("retryUpload", 1);
					organisationsDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					organisationsDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					organisationsDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int organisationsId = -1;
				int keyColumnsCount = 0;
								keyColumnsCount++;

				if(keyColumnsCount > 0 && !organisations.has("organisationsId"))
				{
					organisations.addProperty("attributeNamePrefix", "");
					
					organisations.addProperty("attributeNamePrefix", "");
					JsonObject responseData = organisationsImplObj.getOrganisationsByLookupFields(session,  organisations);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						if(responseData.has("resultsCount")==false)
						{
							continue;							
						}
					}
					else
					{
						isEntityPresent = 1;
					}
					if(isEntityPresent==1)
					{
						JsonObject organisationsObject = responseData.get("organisationsDataObject").getAsJsonObject();
						organisationsId = organisationsObject.get("organisationsId").getAsInt();
						organisations.addProperty("organisationsId", organisationsId);
						updateEntity = true;
					}
				}
				else if(organisations.has("organisationsId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = organisationsImplObj.createOrganisations(organisations);
				}
				else
				{
					responseData = organisationsImplObj.updateOrganisations(organisations);
				}
				organisationsDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					organisationsId =-1;
					organisationsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					organisationsDataObject.addProperty("isSuccessfullyUploaded", 0);
					if (tx.isActive())
					{
						tx.rollback();						
					}
					continue;
				}
				else
				{
					if (tx.isActive())
					{
						tx.commit();						
					}
				}
				organisationsId = responseData.get("organisationsId").getAsInt();
				organisationsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
			}
			dataObject.addProperty("retryCount", retryCount);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject organisations, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getOrganisationsByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From Organisations where 1 = 1  ";
			String countHql = "select count(*)  from Organisations where 1 = 1 ";
						
			String organisationName = requestParams.get("organisationName").getAsString();
			hql += " and organisationName = :organisationName ";
			countHql += " and organisationName = :organisationName ";
Query countQuery = session.createQuery(countHql);			countQuery.setParameter("organisationName", organisationName);
Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Organisations could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);			query.setParameter("organisationName", organisationName);
List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Organisations organisations = (Organisations) it.next();
				JsonObject organisationsDataObject = organisations.getDataObject(session);
				dataObject.add("organisationsDataObject", organisationsDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Organisations could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			String organisationName = searchObject.get(attributeNamePrefix + "_" + "organisationName").getAsString();
			requestParams.addProperty("organisationName", organisationName);
dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Organisations' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			if(searchObject.has(attributeNamePrefix + "_" + "organisationName"))
			{
				String organisationName = searchObject.get(attributeNamePrefix + "_" + "organisationName").getAsString();
				if(organisationName!=null && organisationName.length()>0)
				{
					return 1;
				}
			}

			return 0;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	
	public int executeAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		/*if(!ServiceAPIUtil.REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus))
		{
			isAPIExecuted = 1;
			return isAPIExecuted;
		}*/		
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getApiName();
		isAPIExecuted = executeAPI(session, requestParametersInfo, requestId, apiName, retValObject);
		if (isAPIExecuted == 1)
		{
			if (retValObject.get("isRequestProcessed").getAsInt() == 1)
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_SUCCESSFULL, requestId);
			}
			else
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_FAILED, requestId);
			}
		}
		return isAPIExecuted;
	}
    	public int executeAPI(Session session, JsonObject requestParametersInfo, int requestId, String apiName, JsonObject retValObject)
	{
		JsonObject dataObject = new JsonObject();
		int isAPIExecuted = 0;
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		try
		{
			if(1 > 2)
			{
			}else if(apiName.equals("userLogin"))
			{
				com.patientapp.request.util.SessionUtil loginObject = new com.patientapp.request.util.SessionUtil();
				//dataObject = loginObject.userLogin(requestParametersInfo);
				isAPIExecuted = 1;
			}
			else if(apiName.equals("userLoginWithBranch"))
			{
				com.patientapp.request.util.SessionUtil loginObject = new com.patientapp.request.util.SessionUtil();
				//dataObject = loginObject.userLoginWithBranch(requestParametersInfo);
				isAPIExecuted = 1;
			}
			if(isAPIExecuted == 1)
			{
				retValObject.add("responseData", dataObject);
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("isRequestProcessed", 1);
				}
				else
				{
					retValObject.addProperty("isRequestProcessed", 0);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	public int executeRollbackAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getRollbackAPIName();
		JsonObject dataObject = new JsonObject();
		try
		{
			//String apiName  = inputDataObject.get("apiName").getAsString();  
			if(1 > 2)
			{
			}if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					ServiceAPIUtil.updateRequestReceivedInfoRolledback(session,  requestId);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}

	public JsonObject getAPIData(Session session, String apiName, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		try
		{
			if(1 > 2)
			{
			}
		else if(apiName.equals("getOrganisationsPropertyValue"))
			{
				JsonObject inputDataObject = getOrganisationsPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getOrganisations"))
			{
				JsonObject inputDataObject = getOrganisations(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}		dataObject.addProperty("success", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public int updateAPIStatus(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		String apiName = requestReceived.getApiName();
		JsonObject dataObject = new JsonObject();
		try
		{
			if (1 > 2)
			{
			}
			else if (apiName.equals("doBeforeTransactionApprovedForOrganisations"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForOrganisations"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("success", dataObject.get("success").getAsInt());
				}
				else
				{
					retValObject.addProperty("success", 0);
				}
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	
	public JsonObject updateAPIStatus(Session session, RequestReceived requestReceived)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int requestId = requestReceived.getRequestReceivedId();
			JsonObject paramsInfo = new Gson().fromJson(requestReceived.getParamsInfo(), JsonObject.class);
			JsonObject requestReceivedParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
			Integer organisationsId = requestReceivedParametersInfo.get("organisationsId").getAsInt();
			Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);
			organisations.setIsRequestUnderProcesss(0);
			session.merge(organisations);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("alert", "Your request could not be processsed as request status could not be updated.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getOrganisationsPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer organisationsId = inputForGetAPI.get("organisationsId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		Organisations organisations = (Organisations) session.get(Organisations.class, organisationsId);
		organisations.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getOrganisations(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer organisationsId = inputForGetAPI.get("organisationsId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("organisationsId", String.valueOf(organisationsId));
		JsonObject organisationsListObject = retrieveOrganisationsList(paramsMap);
		if(organisationsListObject!=null && organisationsListObject.has("success") && organisationsListObject.get("success").getAsInt()==1)
		{
			JsonArray organisationsList = organisationsListObject.get("organisationsList").getAsJsonArray();
			if(organisationsList.size()>0)
			{
				dataObject.add("organisations", organisationsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Organisations' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject organisationsDataObject, Organisations organisations);
	public abstract void doExecuteCustomAPIImpl(String customAPIMessage);
	public abstract void doEnrichValuesImpl(JsonObject paramsInfoObject);
	public abstract void doAfterEnrichValuesImpl();
	public abstract void doValidationsImpl();
	public abstract void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex);
	public abstract void doAfterSetValues();
	public abstract void doAfterSelectRowImpl();
	public abstract void doAfterCreateTransactionImpl(JsonObject paramsInfoObj);
	public abstract void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj);	
	public abstract void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doAfterCreateTransactionCommittedImpl();
	public abstract void doAfterUpdateTransactionCommittedImpl();
	public abstract String doGetUpdatedQueryStringForSearchImpl(String queryString);
	public abstract void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap);
	public abstract String doGetOrderByClauseSearchImpl();
	public abstract void doAfterUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doBeforeDeleteTransactionImpl();
	public abstract void doAfterDeleteTransactionImpl();
	public abstract void doAfterLookupRowSelectedImpl(Organisations organisations, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(Organisations organisations, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(Organisations organisations, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
