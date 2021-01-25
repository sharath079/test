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
import com.patientapp.bean.PrivilegeGroup;
import com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl;
import com.patientapp.controller.forms.base.PrivilegeGroupLabel;
import com.patientapp.bean.PrivilegeGroup;
import com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl;
//

import com.patientapp.bean.PrivilegeGroupItems;
import com.patientapp.controller.forms.base.PrivilegeGroupItemsLabel;
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
public abstract class PrivilegeGroupItemsControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'PrivilegeActionType' 
	 *		 * 'PrivilegeObjectType' 
	 *		 * 'PrivilegeObjectName' 
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
	protected PrivilegeGroupItemsLabel PrivilegeGroupItemsLabelLocal = new PrivilegeGroupItemsLabel();
	protected PrivilegeGroupItems PrivilegeGroupItemsLocal = new PrivilegeGroupItems();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public PrivilegeGroupItemsControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public PrivilegeGroupItemsControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public PrivilegeGroupItemsControllerBase()
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
		return "PrivilegeGroupItems" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((PrivilegeGroupItems)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((PrivilegeGroupItems)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((PrivilegeGroupItems)obj).getPrivilegeGroupItemsId();
	}
	public String getManagedBeanName()
    {
		return "PrivilegeGroupItemsBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "PrivilegeGroupItemsLabelBean";
    }
	protected Class<PrivilegeGroupItems> setBeanClass()
	{
		return PrivilegeGroupItems.class;
	}
	protected Class<PrivilegeGroupItemsLabel> setBeanLabelClass()
	{
		return PrivilegeGroupItemsLabel.class;
	}
	protected PrivilegeGroupItems getLocalManagedBean()
    {
		return (PrivilegeGroupItems)getManagedBean();
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
		/*PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		String areChangesEffected = privilegeGroupItems.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			privilegeGroupItems.setAreChangesEffected("YES");			
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
		/*PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		String areChangesEffected = privilegeGroupItems.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			privilegeGroupItems.setAreChangesEffected("NO");			
		}*/
		int generatedRequestId = -1;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();	
			
			doBeforeTransactionRolledbackImpl();
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
	{PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = privilegeGroupControllerImpl.isTransactionUpdatable();
		return isParentTransactionUpdatable;
		
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		String sCurrentStatus = privilegeGroupItems.getVwTxnStatus();
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
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(privilegeGroupItems, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterPrivilegeGroupItemsLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("privilegeActionType") && initParamsInfo.get("privilegeActionType").isJsonNull()==false)
		{
			String privilegeActionType = initParamsInfo.get("privilegeActionType").getAsString();
			privilegeGroupItems.setPrivilegeActionType(privilegeActionType);			
		}if(initParamsInfo.has("privilegeObjectType") && initParamsInfo.get("privilegeObjectType").isJsonNull()==false)
		{
			String privilegeObjectType = initParamsInfo.get("privilegeObjectType").getAsString();
			privilegeGroupItems.setPrivilegeObjectType(privilegeObjectType);			
		}if(initParamsInfo.has("privilegeObjectName") && initParamsInfo.get("privilegeObjectName").isJsonNull()==false)
		{
			String privilegeObjectName = initParamsInfo.get("privilegeObjectName").getAsString();
			privilegeGroupItems.setPrivilegeObjectName(privilegeObjectName);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(privilegeGroupItems, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(privilegeGroupItems, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = privilegeGroupControllerImpl.isTransactionUpdatable();
		if(isParentTransactionUpdatable==false)
		{
			addCustomResponse("This transaction cannot be deleted !!");
		}
		return isParentTransactionUpdatable;
		
	}	
	public void beforeDeleteTransaction(boolean clearSession)
	{
		try
		{
			
			JsonObject dataObject = new JsonObject();		if(isTransactionUpdatable()==false)
			{
				addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
			}
			PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "PrivilegeGroupItems", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() PrivilegeGroupItemsContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (PrivilegeGroupItemsSearch)getManagedBean("PrivilegeGroupItemsSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getPrivilegeActionType()))
			{
				searchParams.put(PrivilegeGroupItemsLabelLocal.getprivilegeActionTypeFieldName(),searchBeanLocal.getPrivilegeActionType());
			}	
			if (isExists(searchBeanLocal.getPrivilegeObjectType()))
			{
				searchParams.put(PrivilegeGroupItemsLabelLocal.getprivilegeObjectTypeFieldName(),searchBeanLocal.getPrivilegeObjectType());
			}	
			if (isExists(searchBeanLocal.getPrivilegeObjectName()))
			{
				searchParams.put(PrivilegeGroupItemsLabelLocal.getprivilegeObjectNameFieldName(),searchBeanLocal.getPrivilegeObjectName());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(PrivilegeGroupItemsLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() PrivilegeGroupItemsContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues PrivilegeGroupItemsContollerBase");
		targetBean = (PrivilegeGroupItems)targetBean;PrivilegeGroup PrivilegeGroupLocal = (PrivilegeGroup)getManagedBean("PrivilegeGroupBean");
		((PrivilegeGroupItems)targetBean).setPrivilegeGroupId(PrivilegeGroupLocal.getPrivilegeGroupId());
		((PrivilegeGroupItems)targetBean).setPrivilegeGroupItemsId(((PrivilegeGroupItems)sourceBean).getPrivilegeGroupItemsId());
				((PrivilegeGroupItems)targetBean).setPrivilegeActionType(((PrivilegeGroupItems)sourceBean).getPrivilegeActionType());
		((PrivilegeGroupItems)targetBean).setPrivilegeObjectType(((PrivilegeGroupItems)sourceBean).getPrivilegeObjectType());
		((PrivilegeGroupItems)targetBean).setPrivilegeObjectName(((PrivilegeGroupItems)sourceBean).getPrivilegeObjectName());

		doAfterSetValues();
		debugCode("Out setValues PrivilegeGroupItemsContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.PrivilegeGroupItems entityBean = (PrivilegeGroupItems)entityObject;
	 	if (sFieldName.equalsIgnoreCase("privilegeGroupItemsId")) 
	 	{
			return entityBean.getPrivilegeGroupItemsId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("PrivilegeActionType")) 
	 	{
			return entityBean.getPrivilegeActionType();
		}
	 	if (sFieldName.equalsIgnoreCase("PrivilegeObjectType")) 
	 	{
			return entityBean.getPrivilegeObjectType();
		}
	 	if (sFieldName.equalsIgnoreCase("PrivilegeObjectName")) 
	 	{
			return entityBean.getPrivilegeObjectName();
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
		debugCode("In doEnrichSystemValues(String sAction) PrivilegeGroupItemsControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((PrivilegeGroupItems) localManagedBean).setVwLastModifiedDate(new Date());
		((PrivilegeGroupItems) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((PrivilegeGroupItems) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((PrivilegeGroupItems) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((PrivilegeGroupItems) localManagedBean).setVwTxnStatus("CREATED");
			((PrivilegeGroupItems) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((PrivilegeGroupItems) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((PrivilegeGroupItems) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) PrivilegeGroupItemsControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) PrivilegeGroupItemsControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) PrivilegeGroupItemsControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations PrivilegeGroupItemsControllerBase");
		//NG: Important comment
		//managedBean = (PrivilegeGroupItems) getManagedBean();
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
		debugCode("Out doValidations PrivilegeGroupItemsControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation PrivilegeGroupItemsControllerBase");
		debugCode("Out doAllowedDecimalValidation PrivilegeGroupItemsControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation PrivilegeGroupItemsControllerBase");debugCode("Out doAllowedValuesValidation PrivilegeGroupItemsControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation PrivilegeGroupItemsControllerBase");
				
		String sFieldValue2 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeActionType();String sFieldValue3 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeObjectType();String sFieldValue4 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeObjectName();
		if (!isExists(sFieldValue2)) addMandatoryResponse(PrivilegeGroupItemsLabelLocal.getprivilegeActionTypeFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(PrivilegeGroupItemsLabelLocal.getprivilegeObjectTypeFieldName());
		if (!isExists(sFieldValue4)) addMandatoryResponse(PrivilegeGroupItemsLabelLocal.getprivilegeObjectNameFieldName());
debugCode("Out doMandatoryValidation PrivilegeGroupItemsControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation PrivilegeGroupItemsControllerBase");
				
		String sFieldValue2 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeActionType();String sFieldValue3 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeObjectType();String sFieldValue4 = ((PrivilegeGroupItems) localManagedBean).getPrivilegeObjectName();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(PrivilegeGroupItemsLabelLocal.getprivilegeActionTypeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(PrivilegeGroupItemsLabelLocal.getprivilegeObjectTypeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue4,"50")) addMaxLengthResponse(PrivilegeGroupItemsLabelLocal.getprivilegeObjectNameFieldName(),"50");
debugCode("Out doLengthValidation PrivilegeGroupItemsControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation PrivilegeGroupItemsControllerBase");
		debugCode("Out doDataTypeValidation PrivilegeGroupItemsControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation PrivilegeGroupItemsContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			PrivilegeGroup PrivilegeGroupLocal = (PrivilegeGroup)getManagedBean("PrivilegeGroupBean");
			if (PrivilegeGroupLocal!=null)
			{
				iFieldValueFK = PrivilegeGroupLocal.getPrivilegeGroupId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation PrivilegeGroupItemsContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel PrivilegeGroupItemsContollerBase");
		String sLabel = new PrivilegeGroupItemsLabel().getLabel(sResponseField); 
		debugCode("Out getLabel PrivilegeGroupItemsContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int privilegeGroupItemsId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
			JsonObject responseData = retrievePrivilegeGroupItems(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Privilege Group Items' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject privilegeGroupItemsDataObject = responseData.get("privilegeGroupItemsDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, privilegeGroupItemsDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Privilege Group Items' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrievePrivilegeGroupItems(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupItemsId = -1;
		if(requestParams.has("privilegeGroupItemsId"))
		{
			privilegeGroupItemsId = requestParams.get("privilegeGroupItemsId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("privilegeGroupItemsId", String.valueOf(privilegeGroupItemsId));
				String privilegeActionType = "";
		if(requestParams.has("privilegeActionType"))
		{
			paramsMap.put("privilegeActionType", requestParams.get("privilegeActionType").getAsString());
		}
		String privilegeObjectType = "";
		if(requestParams.has("privilegeObjectType"))
		{
			paramsMap.put("privilegeObjectType", requestParams.get("privilegeObjectType").getAsString());
		}
		String privilegeObjectName = "";
		if(requestParams.has("privilegeObjectName"))
		{
			paramsMap.put("privilegeObjectName", requestParams.get("privilegeObjectName").getAsString());
		}
	
		Integer privilegeGroupId = -1;;
		if(requestParams.has("privilegeGroupId"))
		{
			paramsMap.put("privilegeGroupId", requestParams.get("privilegeGroupId").getAsString());
		}JsonObject privilegeGroupItemsListObject = retrievePrivilegeGroupItemsList(paramsMap);
		if(privilegeGroupItemsListObject!=null && privilegeGroupItemsListObject.has("success") && privilegeGroupItemsListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupItemsList = privilegeGroupItemsListObject.get("privilegeGroupItemsList").getAsJsonArray();
			if(privilegeGroupItemsList.size()>0)
			{
				dataObject.add("privilegeGroupItemsDataObject", privilegeGroupItemsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group Items' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getPrivilegeGroupItems(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String privilegeActionType = "";
			if(searchParams.has("privilegeActionType"))
			{
				paramsMap.put("privilegeActionType", searchParams.get("privilegeActionType").getAsString());
			}
			String privilegeObjectType = "";
			if(searchParams.has("privilegeObjectType"))
			{
				paramsMap.put("privilegeObjectType", searchParams.get("privilegeObjectType").getAsString());
			}
			String privilegeObjectName = "";
			if(searchParams.has("privilegeObjectName"))
			{
				paramsMap.put("privilegeObjectName", searchParams.get("privilegeObjectName").getAsString());
			}

			
				
			Integer privilegeGroupId = -1;;
			if(searchParams.has("privilegeGroupId"))
			{
				paramsMap.put("privilegeGroupId", searchParams.get("privilegeGroupId").getAsString());
			}
			
			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject privilegeGroupItemsListObject = retrievePrivilegeGroupItemsList(paramsMap);
			if(privilegeGroupItemsListObject!=null && privilegeGroupItemsListObject.has("success") && privilegeGroupItemsListObject.get("success").getAsInt()==1)
			{
				JsonArray privilegeGroupItemsList = privilegeGroupItemsListObject.get("privilegeGroupItemsList").getAsJsonArray();
				if(privilegeGroupItemsList.size()>0)
				{
					dataObject.add("privilegeGroupItems", privilegeGroupItemsList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group Items' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getPrivilegeGroupItemsInJson(int privilegeGroupItemsId)
	{
		JsonObject PrivilegeGroupItemsData = null;
		List<Integer> privilegeGroupItemsIdsList = new ArrayList<>();
		privilegeGroupItemsIdsList.add(privilegeGroupItemsId);
		JsonArray privilegeGroupItemsList = getPrivilegeGroupItemsListInJson(privilegeGroupItemsIdsList);
		if(privilegeGroupItemsList!=null && privilegeGroupItemsList.size()>0)
		{
			PrivilegeGroupItemsData = privilegeGroupItemsList.get(0).getAsJsonObject();
		}
		return PrivilegeGroupItemsData;
	}
	public JsonArray getPrivilegeGroupItemsListInJson(List<Integer> privilegeGroupItemsIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray privilegeGroupItemsObjectsList = new JsonArray();
		JsonObject privilegeGroupItemsListObject = retrievePrivilegeGroupItemsList(paramsMap, privilegeGroupItemsIdsList);
		if(privilegeGroupItemsListObject!=null && privilegeGroupItemsListObject.has("success") && privilegeGroupItemsListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupItemsList = privilegeGroupItemsListObject.get("privilegeGroupItemsList").getAsJsonArray();
			for (int i =0; i< privilegeGroupItemsList.size(); i++)
			{
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
				int privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
				
				privilegeGroupItemsObjectsList.add(privilegeGroupItemsDataObject);
			}
		}
		return privilegeGroupItemsObjectsList;
	}
	
	public JsonArray getPrivilegeGroupItemsListFromParentInJson(int privilegeGroupId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("privilegeGroupId", String.valueOf(privilegeGroupId));
		JsonArray privilegeGroupItemsObjectsList = new JsonArray();
		JsonObject privilegeGroupItemsListObject = retrievePrivilegeGroupItemsList(paramsMap);
		if(privilegeGroupItemsListObject!=null && privilegeGroupItemsListObject.has("success") && privilegeGroupItemsListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupItemsList = privilegeGroupItemsListObject.get("privilegeGroupItemsList").getAsJsonArray();
			for (int i =0; i< privilegeGroupItemsList.size(); i++)
			{
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
				int privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
				
			    privilegeGroupItemsObjectsList.add(privilegeGroupItemsDataObject);
			}
		}
		return privilegeGroupItemsObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray privilegeGroupItemsList)
	{
		String errorMessage = "privilegeGroupItemsList: \n";
		for (int i =0; i< privilegeGroupItemsList.size(); i++)
		{
			JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
			JsonObject privilegeGroupItems = privilegeGroupItemsDataObject.get("dataObject").getAsJsonObject();
			
			if(!privilegeGroupItemsDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "privilegeGroupItems could not be uploaded verify data \n"; 
			}
			else if(privilegeGroupItemsDataObject.has("isSuccessfullyUploaded") 
					&& privilegeGroupItemsDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += privilegeGroupItemsDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("PrivilegeGroupItems".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND privilegeGroupItemsId = :privilegeGroupItemsId ";
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
		else if("PrivilegeGroupItems".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("privilegeGroupItemsId", userId);
		}
		
	}
	public String getParentFilterClauseForPrivilegeGroupItems(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";		String privilegeGroupFilterClause = " select privilegeGroupId from PrivilegeGroup where 1=1  ";
		int privilegeGroupId = -1;
		if(paramsMap.containsKey("privilegeGroupId"))
		{
			privilegeGroupId = Integer.parseInt(paramsMap.get("privilegeGroupId"));			
		}
		if(privilegeGroupId>0)
		{
			privilegeGroupFilterClause += " and privilegeGroupId = :privilegeGroupId  ";
		}
					
		
parentFilterClause = " and privilegeGroupId in (" + privilegeGroupFilterClause + ")";
		return parentFilterClause;
	}
	public String getLookupDisplayFilterClause()
	{
		String lookupDisplayFilterClause = "";
		String lookupDisplayQueryColumn = " AND concat(";
		int i= 0;
		 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForPrivilegeGroupItems(java.util.Map<String, String> paramsMap, Query query)
	{		int privilegeGroupId = -1;
		if(paramsMap.containsKey("privilegeGroupId"))
		{
			privilegeGroupId = Integer.parseInt(paramsMap.get("privilegeGroupId"));			
		}
		if(privilegeGroupId>0)
		{
			query.setParameter("privilegeGroupId", privilegeGroupId);
		}			

	}
	public JsonObject retrievePrivilegeGroupItemsList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> privilegeGroupItemsIdsList = new ArrayList<>();
		if(paramsMap.containsKey("privilegeGroupItemsId"))
		{
			int privilegeGroupItemsId = Integer.parseInt(paramsMap.get("privilegeGroupItemsId"));
			if(privilegeGroupItemsId>0)
			{
				privilegeGroupItemsIdsList.add(privilegeGroupItemsId);
			}
		}
		return retrievePrivilegeGroupItemsList(paramsMap, privilegeGroupItemsIdsList);
	}
	public JsonObject retrievePrivilegeGroupItemsList(java.util.Map<String, String> paramsMap, List<Integer> privilegeGroupItemsIdsList)
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
						String privilegeActionType = paramsMap.get("privilegeActionType");
			if(privilegeActionType==null)
			{
				privilegeActionType = "";
			}
			String privilegeObjectType = paramsMap.get("privilegeObjectType");
			if(privilegeObjectType==null)
			{
				privilegeObjectType = "";
			}
			String privilegeObjectName = paramsMap.get("privilegeObjectName");
			if(privilegeObjectName==null)
			{
				privilegeObjectName = "";
			}

			
			String hql = "FROM PrivilegeGroupItems where 1 = 1 ";
			String orderByClauseText = "  ";
			String privilegeGroupItemsIdFilterClass = "";
			if (privilegeGroupItemsIdsList != null && privilegeGroupItemsIdsList.size() > 0)
			{
				privilegeGroupItemsIdFilterClass = " AND privilegeGroupItemsId in (:idsList) ";
			}
						String privilegeActionTypeFilterClass = "";
			if (privilegeActionType.length() > 0)
			{		
				
				privilegeActionTypeFilterClass = " AND privilegeActionType LIKE :privilegeActionType ";	
				
			}
			String privilegeObjectTypeFilterClass = "";
			if (privilegeObjectType.length() > 0)
			{		
				
				privilegeObjectTypeFilterClass = " AND privilegeObjectType LIKE :privilegeObjectType ";	
				
			}
			String privilegeObjectNameFilterClass = "";
			if (privilegeObjectName.length() > 0)
			{		
				
				privilegeObjectNameFilterClass = " AND privilegeObjectName LIKE :privilegeObjectName ";	
				
			}

			
			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForPrivilegeGroupItems(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					privilegeGroupItemsIdFilterClass +
										privilegeActionTypeFilterClass +
					privilegeObjectTypeFilterClass +
					privilegeObjectNameFilterClass +

					lookupDisplayFilterClause+
					txnStatusFilterClass;
			if(overrideWhereClause.equalsIgnoreCase("Yes"))
			{
				attributesFilterClause +=  whereClause;
			}
			String whereClauseText = 
			getLoginBasedWhereClause(paramsMap) +
			
			parentFilterClause +
			
			attributesFilterClause;
			if(overrideOrderByClause.equalsIgnoreCase("Yes"))
			{
				orderByClauseText =  orderByClause;
			}
			hql += whereClauseText +
			doGetUpdatedQueryStringForSearchImpl(hql) +
			orderByClauseText;
			Query query = getDBSession().createQuery(hql);
			if (privilegeGroupItemsIdsList != null && privilegeGroupItemsIdsList.size() > 0)
			{
				query.setParameterList("idsList", privilegeGroupItemsIdsList);
			}
						if (privilegeActionType.length() > 0)
			{		
				
				query.setParameter("privilegeActionType", privilegeActionType);	
				
			}
			if (privilegeObjectType.length() > 0)
			{		
				
				query.setParameter("privilegeObjectType", privilegeObjectType);	
				
			}
			if (privilegeObjectName.length() > 0)
			{		
				
				query.setParameter("privilegeObjectName", privilegeObjectName);	
				
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
	    	setParentFilterClauseParamsForPrivilegeGroupItems(paramsMap, query);
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
			JsonArray privilegeGroupItemsList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItems.getDataObject(getDBSession());
				privilegeGroupItemsDataObject.addProperty("nextAction", getActionForCurrentStatus(privilegeGroupItems.getVwTxnStatus()));
				privilegeGroupItemsList.add(privilegeGroupItemsDataObject);
				doAfterSearchResultRowAddedImpl(privilegeGroupItemsDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from PrivilegeGroupItems where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (privilegeGroupItemsIdsList != null && privilegeGroupItemsIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", privilegeGroupItemsIdsList);
				}
								if (privilegeActionType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeActionType", privilegeActionType);
					
					
					
					
				}
				if (privilegeObjectType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeObjectType", privilegeObjectType);
					
					
					
					
				}
				if (privilegeObjectName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeObjectName", privilegeObjectName);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForPrivilegeGroupItems(paramsMap, countQuery);
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
			dataObject.add("privilegeGroupItemsList",   privilegeGroupItemsList);
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
				+ "   from PrivilegeGroupItems E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from PrivilegeGroupItems E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentPrivilegeGroupItemsList(java.util.Map<String, String> paramsMap)
	{
		return retrievePrivilegeGroupItemsList(paramsMap);
	}
	public PrivilegeGroupItems getPrivilegeGroupItemsByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from PrivilegeGroupItems where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
			return privilegeGroupItems;
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
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)getManagedBean();
		JsonObject privilegeGroupItemsDataObject = privilegeGroupItems.getDataObject(getDBSession());copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject);
		setManagedBean(privilegeGroupItems);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData PrivilegeGroupItemsContollerBase");
			PrivilegeGroupItems currentBean = (PrivilegeGroupItems)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeActionType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeActionType(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeObjectType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeObjectType(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeObjectName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeObjectName(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData PrivilegeGroupItemsContollerBase");
	}
	// end Test Data
	public void copyPrivilegeGroupItemsFromJson(PrivilegeGroupItems privilegeGroupItems, JsonObject privilegeGroupItemsDataObject)
	{
		copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject, false);
	}
	public void copyPrivilegeGroupItemsFromJson(PrivilegeGroupItems privilegeGroupItems, JsonObject privilegeGroupItemsDataObject, boolean excludePasswordFields)
	{	
				
		if(privilegeGroupItemsDataObject.has("privilegeActionType"))
		{
			String privilegeActionType = privilegeGroupItemsDataObject.get("privilegeActionType").getAsString();
			privilegeGroupItems.setPrivilegeActionType(privilegeActionType);
		}if(privilegeGroupItemsDataObject.has("privilegeObjectType"))
		{
			String privilegeObjectType = privilegeGroupItemsDataObject.get("privilegeObjectType").getAsString();
			privilegeGroupItems.setPrivilegeObjectType(privilegeObjectType);
		}if(privilegeGroupItemsDataObject.has("privilegeObjectName"))
		{
			String privilegeObjectName = privilegeGroupItemsDataObject.get("privilegeObjectName").getAsString();
			privilegeGroupItems.setPrivilegeObjectName(privilegeObjectName);
		}
		
	}
		
	public JsonObject createPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		return createPrivilegeGroupItems(privilegeGroupItemsDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject privilegeGroupItemsDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		PrivilegeGroupItems privilegeGroupItems = new PrivilegeGroupItems();
		setLoginBasedValues(paramsInfoObj, privilegeGroupItemsDataObject);
		Session session = getDBSession();				
		copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject);
		if(privilegeGroupItemsDataObject.has("legacyRecordId"))
		{
			privilegeGroupItems.setLegacyRecordId(privilegeGroupItemsDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer privilegeGroupId = privilegeGroupItemsDataObject.get("privilegeGroupId").getAsInt();
		com.patientapp.bean.PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
		privilegeGroupItems.setPrivilegeGroupId(privilegeGroupId);
		PrivilegeGroupControllerImpl privilegeGroupImplObj = new PrivilegeGroupControllerImpl(session);
		setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setPrivateManagedBean(privilegeGroup);
		if(!privilegeGroupImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(privilegeGroupImplObj.getTransactionFailureMessage());
		}privilegeGroupItems.setVwCreatedBy(createdBy);
		privilegeGroupItems.setVwCreationDate(new Date());
		setPrivateManagedBean(privilegeGroupItems);
		setManagedBean("PrivilegeGroupItemsBean", privilegeGroupItems);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		privilegeGroupItems = (PrivilegeGroupItems) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("privilegeGroupItemsId", privilegeGroupItems.getPrivilegeGroupItemsId());
			JsonObject privilegeGroupItemsObj = privilegeGroupItems.getDataObject(getDBSession());
			privilegeGroupItemsObj.addProperty("nextAction", getActionForCurrentStatus(privilegeGroupItems.getVwTxnStatus()));
			dataObject.add("privilegeGroupItemsDataObject", privilegeGroupItemsObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updatePrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
		JsonObject responseData = retrievePrivilegeGroupItems(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Privilege Group Items' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
		if(privilegeGroupItems == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(privilegeGroupItems.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromPrivilegeGroupItems(privilegeGroupItemsDataObject);
		boolean excludePasswordFields = true;
		copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject, excludePasswordFields);
			
		com.patientapp.bean.PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupItems.getPrivilegeGroupId());
		PrivilegeGroupControllerImpl privilegeGroupImplObj = new PrivilegeGroupControllerImpl(session);
		setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setPrivateManagedBean(privilegeGroup);
		if(!privilegeGroupImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(privilegeGroupImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(privilegeGroupItems);
		setManagedBean("PrivilegeGroupItemsBean", privilegeGroupItems);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		privilegeGroupItems.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
	}
	public JsonObject deletePrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
		JsonObject responseData = retrievePrivilegeGroupItems(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Privilege Group Items' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
			
		com.patientapp.bean.PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupItems.getPrivilegeGroupId());
		PrivilegeGroupControllerImpl privilegeGroupImplObj = new PrivilegeGroupControllerImpl(session);
		setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setManagedBean(privilegeGroupImplObj.getManagedBeanName(), privilegeGroup);
		privilegeGroupImplObj.setPrivateManagedBean(privilegeGroup);
		if(!privilegeGroupImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(privilegeGroupImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(privilegeGroupItems);
		setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
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
	public JsonObject fetchPrivilegeGroupItemsDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		PrivilegeGroupItems privilegeGroupItems = new PrivilegeGroupItems();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
			doAfterPrivilegeGroupItemsLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItems.getDataObject(getDBSession()));
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
	public JsonObject fetchPrivilegeGroupItemsTestData(int obj, JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		PrivilegeGroupItems privilegeGroupItems = new PrivilegeGroupItems();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject);
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
			doAfterPrivilegeGroupItemsLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItems.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		PrivilegeGroupItems privilegeGroupItems = new PrivilegeGroupItems();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject);	
			
			Integer privilegeGroupId = privilegeGroupItemsDataObject.get("privilegeGroupId").getAsInt();
			privilegeGroupItems.setPrivilegeGroupId(privilegeGroupId);
			
			String attributeName = privilegeGroupItemsDataObject.get("attributeName").getAsString();
			Integer attributeId = privilegeGroupItemsDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItems.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		PrivilegeGroupItems privilegeGroupItems = new PrivilegeGroupItems();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupItemsFromJson(privilegeGroupItems, privilegeGroupItemsDataObject);	
			String attributeName = privilegeGroupItemsDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItems.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
			String customEventName = privilegeGroupItemsDataObject.get("customEventName").getAsString();
			PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItemsBean", privilegeGroupItems);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItems.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnPrivilegeGroupItems(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer privilegeGroupItemsId = privilegeGroupItemsDataObject.get("privilegeGroupItemsId").getAsInt();
			String currentStatus = privilegeGroupItemsDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(privilegeGroupItemsDataObject.has("currentAction"))
			{
				currentAction = privilegeGroupItemsDataObject.get("currentAction").getAsString();
			}
			PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
			setPrivateManagedBean(privilegeGroupItems);
			setManagedBean("PrivilegeGroupItemsBean", privilegeGroupItems);
			if(privilegeGroupItems.getIsRequestUnderProcesss() == 1)
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
				executeAction(privilegeGroupItems, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(privilegeGroupItems, "ClassInfoBean", currentStatus);
			}
//			executeAction(privilegeGroupItems, "PrivilegeGroupItemsBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedprivilegeGroupItemsDataObject = privilegeGroupItems.getDataObject(getDBSession());
				updatedprivilegeGroupItemsDataObject.addProperty("nextAction", getActionForCurrentStatus(privilegeGroupItems.getVwTxnStatus()));
				dataObject.add("privilegeGroupItems", updatedprivilegeGroupItemsDataObject);
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
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) getManagedBean();
		String currentStatus = privilegeGroupItems.getVwTxnStatus();
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
	
	
	public JsonObject downloadPrivilegeGroupItemsData() throws Exception
	{
		return downloadPrivilegeGroupItemsData(null);
	}
	public JsonObject downloadPrivilegeGroupItemsData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadPrivilegeGroupItemsData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadPrivilegeGroupItemsData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer privilegeGroupId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
		try
		{
			
			Cell cell;
			Row row;
			
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			int headerCellCount = entityDataCellIndex;
			int columnWidth = 3000;
			String headerName = "";
			row = sheet.createRow(currentRowPosition++);
			
			headerCellCount = headerCellCount-2;
			cell = row.createCell(headerCellCount++);
			headerName = "LineItem";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "PrivilegeGroupItems";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "S.No";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeGroupItemsId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeActionType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeObjectType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeObjectName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From PrivilegeGroupItems ";
			
			hql += " WHERE privilegeGroupId  = :privilegeGroupId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("privilegeGroupId", privilegeGroupId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer privilegeGroupItemsId = privilegeGroupItems.getPrivilegeGroupItemsId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(privilegeGroupItemsId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeActionType = privilegeGroupItems.getPrivilegeActionType();
				cell.setCellValue(privilegeActionType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeObjectType = privilegeGroupItems.getPrivilegeObjectType();
				cell.setCellValue(privilegeObjectType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeObjectName = privilegeGroupItems.getPrivilegeObjectName();
				cell.setCellValue(privilegeObjectName);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			
			if(entriesExist == false)
			{
				row = sheet.getRow(currentRowPosition-1);
				sheet.removeRow(row);
			}
			else
			{
				row = sheet.getRow(currentRowPosition-1);
				cell = row.createCell(entityDataCellIndex-2);
				cell.setCellStyle(dataStyle);
				cell.setCellValue("END");
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
	public JsonObject uploadPrivilegeGroupItemsData(JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = privilegeGroupItemsDataObject.get("fileId").getAsInt();
		String inputFilesZip = privilegeGroupItemsDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Privilege Group Items Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		privilegeGroupItemsDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadPrivilegeGroupItemsData(workbook, privilegeGroupItemsDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Privilege Group Items Data uploaded successfully.");
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
	public JsonObject uploadPrivilegeGroupItemsData(HSSFWorkbook workbook, JsonObject privilegeGroupItemsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("PrivilegeGroupItems");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = privilegeGroupItemsDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = privilegeGroupItemsDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = privilegeGroupItemsDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Privilege Group Items Data uploaded successfully.");
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
			JsonObject privilegeGroupItems = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject privilegeGroupItemsListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray privilegeGroupItemsList = privilegeGroupItemsListObj.get("privilegeGroupItemsList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadPrivilegeGroupItemsList(privilegeGroupItemsList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(privilegeGroupItemsList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(privilegeGroupItemsList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray privilegeGroupItemsList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject privilegeGroupItems = new JsonObject();
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
				JsonObject privilegeGroupItemsUploadObj	= new JsonObject();
				privilegeGroupItemsUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					privilegeGroupItems = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("privilegeGroupItemsId"))
						{
							String privilegeGroupItemsId = row.getCell(cellIndex++).getStringCellValue();
							if(privilegeGroupItemsId != null && privilegeGroupItemsId.trim().length() > 0)
							{
								try
								{
									Integer iPrivilegeGroupItemsId = Integer.parseInt(privilegeGroupItemsId);
									if(areSourceDestinationSame == 1)
									{
										PrivilegeGroupItems privilegeGroupItemsObj = (PrivilegeGroupItems)session.get(PrivilegeGroupItems.class, iPrivilegeGroupItemsId);
										if(privilegeGroupItemsObj != null)
										{ 
											privilegeGroupItems.addProperty("privilegeGroupItemsId", iPrivilegeGroupItemsId);
										}
										else
										{
											privilegeGroupItemsUploadObj.addProperty("isDataFetched", 0);
											privilegeGroupItemsUploadObj.addProperty("msg", "This Privilege Group Items could not be uploaded as there appears to be some problem with the data(PrivilegeGroupItems Id is not exist). ");
											continue;
										}
									}
									else
									{
										PrivilegeGroupItems privilegeGroupItemsObj = getPrivilegeGroupItemsByLegacyRecordId(session, iPrivilegeGroupItemsId);
										if(privilegeGroupItemsObj != null)
										{ 
											privilegeGroupItems.addProperty("privilegeGroupItemsId", privilegeGroupItemsObj.getPrivilegeGroupItemsId());
										}
										privilegeGroupItems.addProperty("legacyRecordId", iPrivilegeGroupItemsId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									privilegeGroupItemsUploadObj.addProperty("isDataFetched", 0);
									privilegeGroupItemsUploadObj.addProperty("msg", "This Privilege Group Items could not be uploaded as there appears to be some problem with the data(PrivilegeGroupItems Id). ");
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
							privilegeGroupItems.addProperty(parameterName, parameterValue);
						}
					}
					privilegeGroupItemsUploadObj.add("dataObject", privilegeGroupItems);		    
					privilegeGroupItemsList.add(privilegeGroupItemsUploadObj);
					pendingRecordsCount++;
					
					Cell rowCell = row.getCell(entityDataCellIndex-2);
					if(rowCell != null)
					{
						String sEntityLoopEndToken = row.getCell(entityDataCellIndex-2).getStringCellValue();
						if(sEntityLoopEndToken != null && sEntityLoopEndToken.equalsIgnoreCase("END"))
						{
							break;					
						}
					}
				}
				catch (Exception e)
				{
					writeToLog(CommonUtil.getStackTrace(e));
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					continue;
				}
			}
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			dataObject.add("privilegeGroupItemsList", privilegeGroupItemsList);
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
		if(previousRetryCountMap.has("PrivilegeGroupItems") && previousRetryCountMap.get("PrivilegeGroupItems").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("PrivilegeGroupItems").getAsInt();
		}
		if(retryCountMap.has("PrivilegeGroupItems") && retryCountMap.get("PrivilegeGroupItems").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("PrivilegeGroupItems").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForPrivilegeGroupItemsList(JsonArray privilegeGroupItemsList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < privilegeGroupItemsList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
			JsonObject privilegeGroupItems = privilegeGroupItemsDataObject.get("dataObject").getAsJsonObject();
			if(privilegeGroupItemsDataObject.has("retryUpload") && privilegeGroupItemsDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = privilegeGroupItemsDataObject.get("retryUpload").getAsInt();
			}
			if(privilegeGroupItemsDataObject.has("retryChildEntitiesUpload") && privilegeGroupItemsDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = privilegeGroupItemsDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(privilegeGroupItemsDataObject.has("partialUploadUnderProcess") && privilegeGroupItemsDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = privilegeGroupItemsDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("PrivilegeGroupItems", retryCount);
	}
	public JsonObject uploadPrivilegeGroupItemsList(JsonArray privilegeGroupItemsList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(privilegeGroupItemsList);
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
	public JsonObject updateStatusMsg(JsonArray privilegeGroupItemsList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < privilegeGroupItemsList.size(); i++)
			{
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
				JsonObject privilegeGroupItems = privilegeGroupItemsDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = privilegeGroupItemsDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = privilegeGroupItemsDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = privilegeGroupItemsDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray privilegeGroupItemsList) throws Exception
	
	{
		return uploadData(privilegeGroupItemsList, -1);	
	}
	public JsonObject uploadData(JsonArray privilegeGroupItemsList, Integer privilegeGroupId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < privilegeGroupItemsList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItemsList.get(i).getAsJsonObject();
				JsonObject privilegeGroupItems = privilegeGroupItemsDataObject.get("dataObject").getAsJsonObject();
				privilegeGroupItemsDataObject.addProperty("retryUpload", 0);
				privilegeGroupItemsDataObject.addProperty("retryChildEntitiesUpload", 0);
				privilegeGroupItemsDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = privilegeGroupItemsImplObj.getEntityInfoUpdatedWithLookupIds(session, privilegeGroupItems, retValObject);
				if(areAllLookupsFound!=1)
				{
					privilegeGroupItemsDataObject.addProperty("retryUpload", 1);
					privilegeGroupItemsDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					privilegeGroupItemsDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					privilegeGroupItemsDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int privilegeGroupItemsId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !privilegeGroupItems.has("privilegeGroupItemsId"))
				{
					privilegeGroupItems.addProperty("attributeNamePrefix", "");
					
					privilegeGroupItems.addProperty("privilegeGroupId", privilegeGroupId);
					
					privilegeGroupItems.addProperty("attributeNamePrefix", "");
					JsonObject responseData = privilegeGroupItemsImplObj.getPrivilegeGroupItemsByLookupFields(session,  privilegeGroupItems);
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
						JsonObject privilegeGroupItemsObject = responseData.get("privilegeGroupItemsDataObject").getAsJsonObject();
						privilegeGroupItemsId = privilegeGroupItemsObject.get("privilegeGroupItemsId").getAsInt();
						privilegeGroupItems.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
						updateEntity = true;
					}
				}
				else if(privilegeGroupItems.has("privilegeGroupItemsId"))
				{
					updateEntity = true;
				}
				
				if(privilegeGroupId > 0)
				{
					privilegeGroupItems.addProperty("privilegeGroupId", privilegeGroupId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = privilegeGroupItemsImplObj.createPrivilegeGroupItems(privilegeGroupItems);
				}
				else
				{
					responseData = privilegeGroupItemsImplObj.updatePrivilegeGroupItems(privilegeGroupItems);
				}
				privilegeGroupItemsDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					privilegeGroupItemsId =-1;
					privilegeGroupItemsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					privilegeGroupItemsDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				privilegeGroupItemsId = responseData.get("privilegeGroupItemsId").getAsInt();
				privilegeGroupItemsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject privilegeGroupItems, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getPrivilegeGroupItemsByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer privilegeGroupId = -1;
			if(requestParams.has("privilegeGroupId") && requestParams.get("privilegeGroupId").isJsonNull()==false)
			{
				privilegeGroupId = requestParams.get("privilegeGroupId").getAsInt();
			}
			
			String hql = "From PrivilegeGroupItems where 1 = 1   and privilegeGroupId = :privilegeGroupId ";
			String countHql = "select count(*)  from PrivilegeGroupItems where 1 = 1  and privilegeGroupId = :privilegeGroupId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("privilegeGroupId", privilegeGroupId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Privilege Group Items could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("privilegeGroupId", privilegeGroupId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
				JsonObject privilegeGroupItemsDataObject = privilegeGroupItems.getDataObject(session);
				dataObject.add("privilegeGroupItemsDataObject", privilegeGroupItemsDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Privilege Group Items could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			
			
			if(searchObject.has("privilegeGroupId") && searchObject.get("privilegeGroupId").isJsonNull()==false)
			{
				requestParams.addProperty("privilegeGroupId", searchObject.get("privilegeGroupId").getAsInt());
			}
			
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Privilege Group Items' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			
			return 0;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	
	public JsonObject deletePrivilegeGroupItemsListIfKeyColumnsNotFound(Session session, Integer privilegeGroupId)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int keyColumnsCount = 0;
			
			if(keyColumnsCount > 0)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String hql = "From PrivilegeGroupItems WHERE privilegeGroupId = :privilegeGroupId ";
			Query query = session.createQuery(hql);
			query.setParameter("privilegeGroupId", privilegeGroupId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) it.next();
				int privilegeGroupItemsId = privilegeGroupItems.getPrivilegeGroupItemsId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
			    privilegeGroupItemsImplObj.setPrivateManagedBean(privilegeGroupItems);
			    privilegeGroupItemsImplObj.setManagedBean("PrivilegeGroupItems", privilegeGroupItems);
			    privilegeGroupItemsImplObj.delete();
				if (privilegeGroupItemsImplObj.getHasTransactionFailed() == true)
				{
					dataObject.addProperty("alert", getTransactionFailureMessage());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Privilege Group Items could not be deleted");
		dataObject.addProperty("success", 0);
		return dataObject;
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
			}
			
			
			
			
			
			
			
			else if(apiName.equals("userLogin"))
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
			}
			
			if (isAPIExecuted == 1)
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
			else if(apiName.equals("getPrivilegeGroupItemsPropertyValue"))
			{
				JsonObject inputDataObject = getPrivilegeGroupItemsPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getPrivilegeGroupItems"))
			{
				JsonObject inputDataObject = getPrivilegeGroupItems(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}		
			
			dataObject.addProperty("success", 0);
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
			else if (apiName.equals("doBeforeTransactionApprovedForPrivilegeGroupItems"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForPrivilegeGroupItems"))
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
			Integer privilegeGroupItemsId = requestReceivedParametersInfo.get("privilegeGroupItemsId").getAsInt();
			PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
			privilegeGroupItems.setIsRequestUnderProcesss(0);
			session.merge(privilegeGroupItems);
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
	public JsonObject getPrivilegeGroupItemsPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer privilegeGroupItemsId = inputForGetAPI.get("privilegeGroupItemsId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems) session.get(PrivilegeGroupItems.class, privilegeGroupItemsId);
		privilegeGroupItems.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getPrivilegeGroupItems(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer privilegeGroupItemsId = inputForGetAPI.get("privilegeGroupItemsId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("privilegeGroupItemsId", String.valueOf(privilegeGroupItemsId));
		JsonObject privilegeGroupItemsListObject = retrievePrivilegeGroupItemsList(paramsMap);
		if(privilegeGroupItemsListObject!=null && privilegeGroupItemsListObject.has("success") && privilegeGroupItemsListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupItemsList = privilegeGroupItemsListObject.get("privilegeGroupItemsList").getAsJsonArray();
			if(privilegeGroupItemsList.size()>0)
			{
				dataObject.add("privilegeGroupItems", privilegeGroupItemsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group Items' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject privilegeGroupItemsDataObject, PrivilegeGroupItems privilegeGroupItems);
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
	public abstract void doAfterLookupRowSelectedImpl(PrivilegeGroupItems privilegeGroupItems, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(PrivilegeGroupItems privilegeGroupItems, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(PrivilegeGroupItems privilegeGroupItems, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
