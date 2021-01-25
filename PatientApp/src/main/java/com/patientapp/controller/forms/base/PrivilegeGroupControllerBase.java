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
import com.patientapp.bean.PrivilegeGroupItems;
import com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl;

import com.patientapp.bean.PrivilegeGroup;
import com.patientapp.controller.forms.base.PrivilegeGroupLabel;
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
public abstract class PrivilegeGroupControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'PrivilegeGroupName' 
	 *		 * 'PrivilegeCode' 
	 *		 * 'ApplicableUserType' 
	 *		 * 'SelfServiceUser' 
	 *		 * 'PrivilegeGroupDescription' 
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
	protected PrivilegeGroupLabel PrivilegeGroupLabelLocal = new PrivilegeGroupLabel();
	protected PrivilegeGroup PrivilegeGroupLocal = new PrivilegeGroup();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public PrivilegeGroupControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public PrivilegeGroupControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public PrivilegeGroupControllerBase()
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
		return "PrivilegeGroup" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((PrivilegeGroup)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((PrivilegeGroup)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((PrivilegeGroup)obj).getPrivilegeGroupId();
	}
	public String getManagedBeanName()
    {
		return "PrivilegeGroupBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "PrivilegeGroupLabelBean";
    }
	protected Class<PrivilegeGroup> setBeanClass()
	{
		return PrivilegeGroup.class;
	}
	protected Class<PrivilegeGroupLabel> setBeanLabelClass()
	{
		return PrivilegeGroupLabel.class;
	}
	protected PrivilegeGroup getLocalManagedBean()
    {
		return (PrivilegeGroup)getManagedBean();
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
		/*PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		String areChangesEffected = privilegeGroup.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			privilegeGroup.setAreChangesEffected("YES");			
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
		/*PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		String areChangesEffected = privilegeGroup.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			privilegeGroup.setAreChangesEffected("NO");			
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
		/*PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		String areChangesEffected = privilegeGroup.getAreChangesEffected();
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
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		String sCurrentStatus = privilegeGroup.getVwTxnStatus();
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
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("applicableUserType".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(privilegeGroup, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterPrivilegeGroupLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("privilegeGroupName") && initParamsInfo.get("privilegeGroupName").isJsonNull()==false)
		{
			String privilegeGroupName = initParamsInfo.get("privilegeGroupName").getAsString();
			privilegeGroup.setPrivilegeGroupName(privilegeGroupName);			
		}if(initParamsInfo.has("privilegeCode") && initParamsInfo.get("privilegeCode").isJsonNull()==false)
		{
			String privilegeCode = initParamsInfo.get("privilegeCode").getAsString();
			privilegeGroup.setPrivilegeCode(privilegeCode);			
		}if(initParamsInfo.has("applicableUserType") && initParamsInfo.get("applicableUserType").isJsonNull()==false)
		{
			String applicableUserType = initParamsInfo.get("applicableUserType").getAsString();
			privilegeGroup.setApplicableUserType(applicableUserType);			
		}if(initParamsInfo.has("selfServiceUser") && initParamsInfo.get("selfServiceUser").isJsonNull()==false)
		{
			String selfServiceUser = initParamsInfo.get("selfServiceUser").getAsString();
			privilegeGroup.setSelfServiceUser(selfServiceUser);			
		}if(initParamsInfo.has("privilegeGroupDescription") && initParamsInfo.get("privilegeGroupDescription").isJsonNull()==false)
		{
			String privilegeGroupDescription = initParamsInfo.get("privilegeGroupDescription").getAsString();
			privilegeGroup.setPrivilegeGroupDescription(privilegeGroupDescription);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(privilegeGroup, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(privilegeGroup, attributeName);
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
			PrivilegeGroup privilegeGroup = (PrivilegeGroup)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*			Set<Object> privilegeGroupItemsList = privilegeGroup.getPrivilegeGroupItemses();
			Iterator<Object> privilegeGroupItemsListIterator = privilegeGroupItemsList.iterator();
			while(privilegeGroupItemsListIterator.hasNext())
			{
				PrivilegeGroupItems privilegeGroupItems = (PrivilegeGroupItems)privilegeGroupItemsListIterator.next();
				PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(getDBSession());
				privilegeGroupItemsControllerImpl.setPrivateManagedBean(privilegeGroupItems);
				privilegeGroupItemsControllerImpl.deleteWithoutCommit(false);
			}			

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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "PrivilegeGroup", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() PrivilegeGroupContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (PrivilegeGroupSearch)getManagedBean("PrivilegeGroupSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getPrivilegeGroupName()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getprivilegeGroupNameFieldName(),searchBeanLocal.getPrivilegeGroupName());
			}	
			if (isExists(searchBeanLocal.getPrivilegeCode()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getprivilegeCodeFieldName(),searchBeanLocal.getPrivilegeCode());
			}	
			if (isExists(searchBeanLocal.getApplicableUserType()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getapplicableUserTypeFieldName(),searchBeanLocal.getApplicableUserType());
			}	
			if (isExists(searchBeanLocal.getSelfServiceUser()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getselfServiceUserFieldName(),searchBeanLocal.getSelfServiceUser());
			}	
			if (isExists(searchBeanLocal.getPrivilegeGroupDescription()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getprivilegeGroupDescriptionFieldName(),searchBeanLocal.getPrivilegeGroupDescription());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(PrivilegeGroupLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() PrivilegeGroupContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues PrivilegeGroupContollerBase");
		targetBean = (PrivilegeGroup)targetBean;((PrivilegeGroup)targetBean).setPrivilegeGroupId(((PrivilegeGroup)sourceBean).getPrivilegeGroupId());
				((PrivilegeGroup)targetBean).setPrivilegeGroupName(((PrivilegeGroup)sourceBean).getPrivilegeGroupName());
		((PrivilegeGroup)targetBean).setPrivilegeCode(((PrivilegeGroup)sourceBean).getPrivilegeCode());
		((PrivilegeGroup)targetBean).setApplicableUserType(((PrivilegeGroup)sourceBean).getApplicableUserType());
		((PrivilegeGroup)targetBean).setSelfServiceUser(((PrivilegeGroup)sourceBean).getSelfServiceUser());
		((PrivilegeGroup)targetBean).setPrivilegeGroupDescription(((PrivilegeGroup)sourceBean).getPrivilegeGroupDescription());

		doAfterSetValues();
		debugCode("Out setValues PrivilegeGroupContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.PrivilegeGroup entityBean = (PrivilegeGroup)entityObject;
	 	if (sFieldName.equalsIgnoreCase("privilegeGroupId")) 
	 	{
			return entityBean.getPrivilegeGroupId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("PrivilegeGroupName")) 
	 	{
			return entityBean.getPrivilegeGroupName();
		}
	 	if (sFieldName.equalsIgnoreCase("PrivilegeCode")) 
	 	{
			return entityBean.getPrivilegeCode();
		}
	 	if (sFieldName.equalsIgnoreCase("ApplicableUserType")) 
	 	{
			return entityBean.getApplicableUserType();
		}
	 	if (sFieldName.equalsIgnoreCase("SelfServiceUser")) 
	 	{
			return entityBean.getSelfServiceUser();
		}
	 	if (sFieldName.equalsIgnoreCase("PrivilegeGroupDescription")) 
	 	{
			return entityBean.getPrivilegeGroupDescription();
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
		debugCode("In doEnrichSystemValues(String sAction) PrivilegeGroupControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((PrivilegeGroup) localManagedBean).setVwLastModifiedDate(new Date());
		((PrivilegeGroup) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((PrivilegeGroup) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((PrivilegeGroup) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((PrivilegeGroup) localManagedBean).setVwTxnStatus("CREATED");
			((PrivilegeGroup) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((PrivilegeGroup) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((PrivilegeGroup) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) PrivilegeGroupControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) PrivilegeGroupControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) PrivilegeGroupControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations PrivilegeGroupControllerBase");
		//NG: Important comment
		//managedBean = (PrivilegeGroup) getManagedBean();
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
		debugCode("Out doValidations PrivilegeGroupControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation PrivilegeGroupControllerBase");
		debugCode("Out doAllowedDecimalValidation PrivilegeGroupControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation PrivilegeGroupControllerBase");
				
		String sFieldValue4 = ((PrivilegeGroup) localManagedBean).getApplicableUserType();if (!isExists(sFieldValue4,localMasters.getUserType())) addAllowedValuesResponse(PrivilegeGroupLabelLocal.getapplicableUserTypeFieldName());

		debugCode("Out doAllowedValuesValidation PrivilegeGroupControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation PrivilegeGroupControllerBase");
				
		String sFieldValue2 = ((PrivilegeGroup) localManagedBean).getPrivilegeGroupName();String sFieldValue3 = ((PrivilegeGroup) localManagedBean).getPrivilegeCode();String sFieldValue4 = ((PrivilegeGroup) localManagedBean).getApplicableUserType();
		if (!isExists(sFieldValue2)) addMandatoryResponse(PrivilegeGroupLabelLocal.getprivilegeGroupNameFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(PrivilegeGroupLabelLocal.getprivilegeCodeFieldName());
		if (!isExists(sFieldValue4)) addMandatoryResponse(PrivilegeGroupLabelLocal.getapplicableUserTypeFieldName());
debugCode("Out doMandatoryValidation PrivilegeGroupControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation PrivilegeGroupControllerBase");
				
		String sFieldValue2 = ((PrivilegeGroup) localManagedBean).getPrivilegeGroupName();String sFieldValue3 = ((PrivilegeGroup) localManagedBean).getPrivilegeCode();String sFieldValue4 = ((PrivilegeGroup) localManagedBean).getApplicableUserType();String sFieldValue5 = ((PrivilegeGroup) localManagedBean).getSelfServiceUser();String sFieldValue6 = ((PrivilegeGroup) localManagedBean).getPrivilegeGroupDescription();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(PrivilegeGroupLabelLocal.getprivilegeGroupNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(PrivilegeGroupLabelLocal.getprivilegeCodeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue4,"50")) addMaxLengthResponse(PrivilegeGroupLabelLocal.getapplicableUserTypeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue5,"50")) addMaxLengthResponse(PrivilegeGroupLabelLocal.getselfServiceUserFieldName(),"50");
		if (!isLengthAllowed(sFieldValue6,"50")) addMaxLengthResponse(PrivilegeGroupLabelLocal.getprivilegeGroupDescriptionFieldName(),"50");
debugCode("Out doLengthValidation PrivilegeGroupControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation PrivilegeGroupControllerBase");
		debugCode("Out doDataTypeValidation PrivilegeGroupControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation PrivilegeGroupContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
						
			String sFieldValue2Uniq = ((PrivilegeGroup) localManagedBean).getPrivilegeGroupName();String sFieldValue3Uniq = ((PrivilegeGroup) localManagedBean).getPrivilegeCode();

						handleUniqueValidation(PrivilegeGroupLabelLocal.getprivilegeGroupNameFieldName(),sFieldValue2Uniq);
			handleUniqueValidation(PrivilegeGroupLabelLocal.getprivilegeCodeFieldName(),sFieldValue3Uniq);
		}	
		debugCode("In doUniqueValidation PrivilegeGroupContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel PrivilegeGroupContollerBase");
		String sLabel = new PrivilegeGroupLabel().getLabel(sResponseField); 
		debugCode("Out getLabel PrivilegeGroupContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int privilegeGroupId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("privilegeGroupId", privilegeGroupId);
			JsonObject responseData = retrievePrivilegeGroup(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Privilege Group' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject privilegeGroupDataObject = responseData.get("privilegeGroupDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, privilegeGroupDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Privilege Group' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrievePrivilegeGroup(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupId = -1;
		if(requestParams.has("privilegeGroupId"))
		{
			privilegeGroupId = requestParams.get("privilegeGroupId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("privilegeGroupId", String.valueOf(privilegeGroupId));
				String privilegeGroupName = "";
		if(requestParams.has("privilegeGroupName"))
		{
			paramsMap.put("privilegeGroupName", requestParams.get("privilegeGroupName").getAsString());
		}
		String privilegeCode = "";
		if(requestParams.has("privilegeCode"))
		{
			paramsMap.put("privilegeCode", requestParams.get("privilegeCode").getAsString());
		}
		String applicableUserType = "";
		if(requestParams.has("applicableUserType"))
		{
			paramsMap.put("applicableUserType", requestParams.get("applicableUserType").getAsString());
		}
		String selfServiceUser = "";
		if(requestParams.has("selfServiceUser"))
		{
			paramsMap.put("selfServiceUser", requestParams.get("selfServiceUser").getAsString());
		}
		String privilegeGroupDescription = "";
		if(requestParams.has("privilegeGroupDescription"))
		{
			paramsMap.put("privilegeGroupDescription", requestParams.get("privilegeGroupDescription").getAsString());
		}

		JsonObject privilegeGroupListObject = retrievePrivilegeGroupList(paramsMap);
		if(privilegeGroupListObject!=null && privilegeGroupListObject.has("success") && privilegeGroupListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupList = privilegeGroupListObject.get("privilegeGroupList").getAsJsonArray();
			if(privilegeGroupList.size()>0)
			{
				dataObject.add("privilegeGroupDataObject", privilegeGroupList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getPrivilegeGroup(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String privilegeGroupName = "";
			if(searchParams.has("privilegeGroupName"))
			{
				paramsMap.put("privilegeGroupName", searchParams.get("privilegeGroupName").getAsString());
			}
			String privilegeCode = "";
			if(searchParams.has("privilegeCode"))
			{
				paramsMap.put("privilegeCode", searchParams.get("privilegeCode").getAsString());
			}
			String applicableUserType = "";
			if(searchParams.has("applicableUserType"))
			{
				paramsMap.put("applicableUserType", searchParams.get("applicableUserType").getAsString());
			}
			String selfServiceUser = "";
			if(searchParams.has("selfServiceUser"))
			{
				paramsMap.put("selfServiceUser", searchParams.get("selfServiceUser").getAsString());
			}
			String privilegeGroupDescription = "";
			if(searchParams.has("privilegeGroupDescription"))
			{
				paramsMap.put("privilegeGroupDescription", searchParams.get("privilegeGroupDescription").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject privilegeGroupListObject = retrievePrivilegeGroupList(paramsMap);
			if(privilegeGroupListObject!=null && privilegeGroupListObject.has("success") && privilegeGroupListObject.get("success").getAsInt()==1)
			{
				JsonArray privilegeGroupList = privilegeGroupListObject.get("privilegeGroupList").getAsJsonArray();
				if(privilegeGroupList.size()>0)
				{
					dataObject.add("privilegeGroup", privilegeGroupList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getPrivilegeGroupInJson(int privilegeGroupId)
	{
		JsonObject PrivilegeGroupData = null;
		List<Integer> privilegeGroupIdsList = new ArrayList<>();
		privilegeGroupIdsList.add(privilegeGroupId);
		JsonArray privilegeGroupList = getPrivilegeGroupListInJson(privilegeGroupIdsList);
		if(privilegeGroupList!=null && privilegeGroupList.size()>0)
		{
			PrivilegeGroupData = privilegeGroupList.get(0).getAsJsonObject();
		}
		return PrivilegeGroupData;
	}
	public JsonArray getPrivilegeGroupListInJson(List<Integer> privilegeGroupIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray privilegeGroupObjectsList = new JsonArray();
		JsonObject privilegeGroupListObject = retrievePrivilegeGroupList(paramsMap, privilegeGroupIdsList);
		if(privilegeGroupListObject!=null && privilegeGroupListObject.has("success") && privilegeGroupListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupList = privilegeGroupListObject.get("privilegeGroupList").getAsJsonArray();
			for (int i =0; i< privilegeGroupList.size(); i++)
			{
				JsonObject privilegeGroupDataObject = privilegeGroupList.get(i).getAsJsonObject();
				int privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
								com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray privilegeGroupItemsList = privilegeGroupItemsImplObj.getPrivilegeGroupItemsListFromParentInJson(privilegeGroupId);
				privilegeGroupDataObject.add("privilegeGroupItemsList", privilegeGroupItemsList);

				privilegeGroupObjectsList.add(privilegeGroupDataObject);
			}
		}
		return privilegeGroupObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray privilegeGroupList)
	{
		String errorMessage = "privilegeGroupList: \n";
		for (int i =0; i< privilegeGroupList.size(); i++)
		{
			JsonObject privilegeGroupDataObject = privilegeGroupList.get(i).getAsJsonObject();
			JsonObject privilegeGroup = privilegeGroupDataObject.get("dataObject").getAsJsonObject();
						
			errorMessage += "Privilege Group Name : "+ privilegeGroup.get("privilegeGroupName").getAsString();errorMessage += "Privilege Code : "+ privilegeGroup.get("privilegeCode").getAsString();

			if(!privilegeGroupDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "privilegeGroup could not be uploaded verify data \n"; 
			}
			else if(privilegeGroupDataObject.has("isSuccessfullyUploaded") 
					&& privilegeGroupDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += privilegeGroupDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    		    if(privilegeGroup.has("privilegeGroupItemsList"))
		    {
			    JsonArray privilegeGroupItemsList = privilegeGroup.get("privilegeGroupItemsList").getAsJsonArray();
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);		    
				errorMessage += privilegeGroupItemsImplObj.getUploadedDataErrorMessage(session, privilegeGroupItemsList);
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
		else if("PrivilegeGroup".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND privilegeGroupId = :privilegeGroupId ";
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
		else if("PrivilegeGroup".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("privilegeGroupId", userId);
		}
		
	}
	public String getParentFilterClauseForPrivilegeGroup(java.util.Map<String, String> paramsMap)
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
		lookupDisplayQueryColumn += "privilegeGroupName";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForPrivilegeGroup(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrievePrivilegeGroupList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> privilegeGroupIdsList = new ArrayList<>();
		if(paramsMap.containsKey("privilegeGroupId"))
		{
			int privilegeGroupId = Integer.parseInt(paramsMap.get("privilegeGroupId"));
			if(privilegeGroupId>0)
			{
				privilegeGroupIdsList.add(privilegeGroupId);
			}
		}
		return retrievePrivilegeGroupList(paramsMap, privilegeGroupIdsList);
	}
	public JsonObject retrievePrivilegeGroupList(java.util.Map<String, String> paramsMap, List<Integer> privilegeGroupIdsList)
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
						String privilegeGroupName = paramsMap.get("privilegeGroupName");
			if(privilegeGroupName==null)
			{
				privilegeGroupName = "";
			}
			String privilegeCode = paramsMap.get("privilegeCode");
			if(privilegeCode==null)
			{
				privilegeCode = "";
			}
			String applicableUserType = paramsMap.get("applicableUserType");
			if(applicableUserType==null)
			{
				applicableUserType = "";
			}
			String selfServiceUser = paramsMap.get("selfServiceUser");
			if(selfServiceUser==null)
			{
				selfServiceUser = "";
			}
			String privilegeGroupDescription = paramsMap.get("privilegeGroupDescription");
			if(privilegeGroupDescription==null)
			{
				privilegeGroupDescription = "";
			}
String hql = "FROM PrivilegeGroup where 1 = 1 ";
			String orderByClauseText = "  ";
			String privilegeGroupIdFilterClass = "";
			if (privilegeGroupIdsList != null && privilegeGroupIdsList.size() > 0)
			{
				privilegeGroupIdFilterClass = " AND privilegeGroupId in (:idsList) ";
			}
						String privilegeGroupNameFilterClass = "";
			if (privilegeGroupName.length() > 0)
			{
				
				
				
				
				
				
				
				privilegeGroupNameFilterClass = " AND privilegeGroupName LIKE :privilegeGroupName ";
				
				
				
				
			}
			String privilegeCodeFilterClass = "";
			if (privilegeCode.length() > 0)
			{
				
				
				
				
				
				
				
				privilegeCodeFilterClass = " AND privilegeCode LIKE :privilegeCode ";
				
				
				
				
			}
			String applicableUserTypeFilterClass = "";
			if (applicableUserType.length() > 0)
			{
				
				
				
				
				
				
				
				applicableUserTypeFilterClass = " AND applicableUserType LIKE :applicableUserType ";
				
				
				
				
			}
			String selfServiceUserFilterClass = "";
			if (selfServiceUser.length() > 0)
			{
				
				
				
				
				
				
				
				selfServiceUserFilterClass = " AND selfServiceUser LIKE :selfServiceUser ";
				
				
				
				
			}
			String privilegeGroupDescriptionFilterClass = "";
			if (privilegeGroupDescription.length() > 0)
			{
				
				
				
				
				
				
				
				privilegeGroupDescriptionFilterClass = " AND privilegeGroupDescription LIKE :privilegeGroupDescription ";
				
				
				
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForPrivilegeGroup(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					privilegeGroupIdFilterClass +
										privilegeGroupNameFilterClass +
					privilegeCodeFilterClass +
					applicableUserTypeFilterClass +
					selfServiceUserFilterClass +
					privilegeGroupDescriptionFilterClass +

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
			if (privilegeGroupIdsList != null && privilegeGroupIdsList.size() > 0)
			{
				query.setParameterList("idsList", privilegeGroupIdsList);
			}
						if (privilegeGroupName.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("privilegeGroupName", privilegeGroupName);
				
				
				
				
			}
			if (privilegeCode.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("privilegeCode", privilegeCode);
				
				
				
				
			}
			if (applicableUserType.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("applicableUserType", applicableUserType);
				
				
				
				
			}
			if (selfServiceUser.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("selfServiceUser", selfServiceUser);
				
				
				
				
			}
			if (privilegeGroupDescription.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("privilegeGroupDescription", privilegeGroupDescription);
				
				
				
				
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
	    	setParentFilterClauseParamsForPrivilegeGroup(paramsMap, query);
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
			JsonArray privilegeGroupList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroup privilegeGroup = (PrivilegeGroup) it.next();
				JsonObject privilegeGroupDataObject = privilegeGroup.getDataObject(getDBSession());
				privilegeGroupDataObject.addProperty("nextAction", getActionForCurrentStatus(privilegeGroup.getVwTxnStatus()));
				privilegeGroupList.add(privilegeGroupDataObject);
				doAfterSearchResultRowAddedImpl(privilegeGroupDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from PrivilegeGroup where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (privilegeGroupIdsList != null && privilegeGroupIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", privilegeGroupIdsList);
				}
								if (privilegeGroupName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeGroupName", privilegeGroupName);
					
					
					
					
				}
				if (privilegeCode.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeCode", privilegeCode);
					
					
					
					
				}
				if (applicableUserType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("applicableUserType", applicableUserType);
					
					
					
					
				}
				if (selfServiceUser.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("selfServiceUser", selfServiceUser);
					
					
					
					
				}
				if (privilegeGroupDescription.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("privilegeGroupDescription", privilegeGroupDescription);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForPrivilegeGroup(paramsMap, countQuery);
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
			dataObject.add("privilegeGroupList",   privilegeGroupList);
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
				+ "   from PrivilegeGroup E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from PrivilegeGroup E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentPrivilegeGroupList(java.util.Map<String, String> paramsMap)
	{
		return retrievePrivilegeGroupList(paramsMap);
	}
	public PrivilegeGroup getPrivilegeGroupByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from PrivilegeGroup where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			PrivilegeGroup privilegeGroup = (PrivilegeGroup) it.next();
			return privilegeGroup;
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
		PrivilegeGroup privilegeGroup = (PrivilegeGroup)getManagedBean();
		JsonObject privilegeGroupDataObject = privilegeGroup.getDataObject(getDBSession());copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject);
		setManagedBean(privilegeGroup);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData PrivilegeGroupContollerBase");
			PrivilegeGroup currentBean = (PrivilegeGroup)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeGroupName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeGroupName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeCode".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeCode(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "ApplicableUserType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setApplicableUserType(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "SelfServiceUser".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSelfServiceUser(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PrivilegeGroupDescription".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPrivilegeGroupDescription(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData PrivilegeGroupContollerBase");
	}
	// end Test Data
	public void copyPrivilegeGroupFromJson(PrivilegeGroup privilegeGroup, JsonObject privilegeGroupDataObject)
	{
		copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject, false);
	}
	public void copyPrivilegeGroupFromJson(PrivilegeGroup privilegeGroup, JsonObject privilegeGroupDataObject, boolean excludePasswordFields)
	{	
				
		if(privilegeGroupDataObject.has("privilegeGroupName"))
		{
			String privilegeGroupName = privilegeGroupDataObject.get("privilegeGroupName").getAsString();
			privilegeGroup.setPrivilegeGroupName(privilegeGroupName);
		}if(privilegeGroupDataObject.has("privilegeCode"))
		{
			String privilegeCode = privilegeGroupDataObject.get("privilegeCode").getAsString();
			privilegeGroup.setPrivilegeCode(privilegeCode);
		}if(privilegeGroupDataObject.has("applicableUserType"))
		{
			String applicableUserType = privilegeGroupDataObject.get("applicableUserType").getAsString();
			privilegeGroup.setApplicableUserType(applicableUserType);
		}if(privilegeGroupDataObject.has("selfServiceUser"))
		{
			String selfServiceUser = privilegeGroupDataObject.get("selfServiceUser").getAsString();
			privilegeGroup.setSelfServiceUser(selfServiceUser);
		}if(privilegeGroupDataObject.has("privilegeGroupDescription"))
		{
			String privilegeGroupDescription = privilegeGroupDataObject.get("privilegeGroupDescription").getAsString();
			privilegeGroup.setPrivilegeGroupDescription(privilegeGroupDescription);
		}
		
	}
		
	public JsonObject createPrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		return createPrivilegeGroup(privilegeGroupDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject privilegeGroupDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createPrivilegeGroup(JsonObject privilegeGroupDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		PrivilegeGroup privilegeGroup = new PrivilegeGroup();
		setLoginBasedValues(paramsInfoObj, privilegeGroupDataObject);
		Session session = getDBSession();				
		copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject);
		if(privilegeGroupDataObject.has("legacyRecordId"))
		{
			privilegeGroup.setLegacyRecordId(privilegeGroupDataObject.get("legacyRecordId").getAsInt());
		}
				privilegeGroup.setVwCreatedBy(createdBy);
		privilegeGroup.setVwCreationDate(new Date());
		setPrivateManagedBean(privilegeGroup);
		setManagedBean("PrivilegeGroupBean", privilegeGroup);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		privilegeGroup = (PrivilegeGroup) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("privilegeGroupId", privilegeGroup.getPrivilegeGroupId());
			JsonObject privilegeGroupObj = privilegeGroup.getDataObject(getDBSession());
			privilegeGroupObj.addProperty("nextAction", getActionForCurrentStatus(privilegeGroup.getVwTxnStatus()));
			dataObject.add("privilegeGroupDataObject", privilegeGroupObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updatePrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("privilegeGroupId", privilegeGroupId);
		JsonObject responseData = retrievePrivilegeGroup(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Privilege Group' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
		if(privilegeGroup == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(privilegeGroup.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromPrivilegeGroup(privilegeGroupDataObject);
		boolean excludePasswordFields = true;
		copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject, excludePasswordFields);setPrivateManagedBean(privilegeGroup);
		setManagedBean("PrivilegeGroupBean", privilegeGroup);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		privilegeGroup.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("privilegeGroupId", privilegeGroupId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromPrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
	}
	public JsonObject deletePrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("privilegeGroupId", privilegeGroupId);
		JsonObject responseData = retrievePrivilegeGroup(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Privilege Group' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);setPrivateManagedBean(privilegeGroup);
		setManagedBean("PrivilegeGroup", privilegeGroup);
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
	public JsonObject fetchPrivilegeGroupDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		PrivilegeGroup privilegeGroup = new PrivilegeGroup();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroup", privilegeGroup);
			doAfterPrivilegeGroupLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroup", privilegeGroup.getDataObject(getDBSession()));
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
	public JsonObject fetchPrivilegeGroupTestData(int obj, JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		PrivilegeGroup privilegeGroup = new PrivilegeGroup();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject);
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroup", privilegeGroup);
			doAfterPrivilegeGroupLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroup", privilegeGroup.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForPrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		PrivilegeGroup privilegeGroup = new PrivilegeGroup();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject);	String attributeName = privilegeGroupDataObject.get("attributeName").getAsString();
			Integer attributeId = privilegeGroupDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroup", privilegeGroup);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroup", privilegeGroup.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForPrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		PrivilegeGroup privilegeGroup = new PrivilegeGroup();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPrivilegeGroupFromJson(privilegeGroup, privilegeGroupDataObject);	
			String attributeName = privilegeGroupDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroup", privilegeGroup);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroup", privilegeGroup.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
			String customEventName = privilegeGroupDataObject.get("customEventName").getAsString();
			PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroupBean", privilegeGroup);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("privilegeGroup", privilegeGroup.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnPrivilegeGroup(JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
			String currentStatus = privilegeGroupDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(privilegeGroupDataObject.has("currentAction"))
			{
				currentAction = privilegeGroupDataObject.get("currentAction").getAsString();
			}
			PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
			setPrivateManagedBean(privilegeGroup);
			setManagedBean("PrivilegeGroupBean", privilegeGroup);
			if(privilegeGroup.getIsRequestUnderProcesss() == 1)
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
				executeAction(privilegeGroup, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(privilegeGroup, "ClassInfoBean", currentStatus);
			}
//			executeAction(privilegeGroup, "PrivilegeGroupBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedprivilegeGroupDataObject = privilegeGroup.getDataObject(getDBSession());
				updatedprivilegeGroupDataObject.addProperty("nextAction", getActionForCurrentStatus(privilegeGroup.getVwTxnStatus()));
				dataObject.add("privilegeGroup", updatedprivilegeGroupDataObject);
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
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) getManagedBean();
		String currentStatus = privilegeGroup.getVwTxnStatus();
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
	
	
	public JsonObject downloadPrivilegeGroupData() throws Exception
	{
		return downloadPrivilegeGroupData(null);
	}
	public JsonObject downloadPrivilegeGroupData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "PrivilegeGroup");
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
			headerName = "privilegeGroupId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeGroupName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "privilegeCode";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "applicableUserType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "selfServiceUser";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "privilegeGroupDescription";
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
			String hql = "From PrivilegeGroup ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				PrivilegeGroup privilegeGroup = (PrivilegeGroup) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer privilegeGroupId = privilegeGroup.getPrivilegeGroupId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(privilegeGroupId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeGroupName = privilegeGroup.getPrivilegeGroupName();
				cell.setCellValue(privilegeGroupName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeCode = privilegeGroup.getPrivilegeCode();
				cell.setCellValue(privilegeCode);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String applicableUserType = privilegeGroup.getApplicableUserType();
				cell.setCellValue(applicableUserType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String selfServiceUser = privilegeGroup.getSelfServiceUser();
				cell.setCellValue(selfServiceUser);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String privilegeGroupDescription = privilegeGroup.getPrivilegeGroupDescription();
				cell.setCellValue(privilegeGroupDescription);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
				privilegeGroupItemsImplObj.downloadPrivilegeGroupItemsData(sheet, headerStyle, dataStyle, rowColumnIndexObject, privilegeGroupId);

			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "PrivilegeGroup.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "PrivilegeGroup.xls";
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
	public JsonObject uploadPrivilegeGroupData(JsonObject privilegeGroupDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = privilegeGroupDataObject.get("fileId").getAsInt();
		String inputFilesZip = privilegeGroupDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Privilege Group Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		privilegeGroupDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadPrivilegeGroupData(workbook, privilegeGroupDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Privilege Group Data uploaded successfully.");
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
	public JsonObject uploadPrivilegeGroupData(HSSFWorkbook workbook, JsonObject privilegeGroupDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("PrivilegeGroup");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = privilegeGroupDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = privilegeGroupDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = privilegeGroupDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Privilege Group Data uploaded successfully.");
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
			JsonObject privilegeGroup = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject privilegeGroupListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray privilegeGroupList = privilegeGroupListObj.get("privilegeGroupList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadPrivilegeGroupList(privilegeGroupList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(privilegeGroupList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(privilegeGroupList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray privilegeGroupList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject privilegeGroup = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("privilegeGroupItems"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session);
					JsonObject responseData  = privilegeGroupItemsImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray privilegeGroupItemsList = responseData.get("privilegeGroupItemsList").getAsJsonArray();
					privilegeGroup.add("privilegeGroupItemsList", privilegeGroupItemsList);
					currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
					row = sheet.getRow(currentRowPosition-1);
					Cell rowCell = row.getCell(entityDataCellIndex-2);
					if(rowCell != null)
					{
						String sEntityLoopEndToken = row.getCell(entityDataCellIndex-2).getStringCellValue();
						if(sEntityLoopEndToken != null && sEntityLoopEndToken.equalsIgnoreCase("END"))
						{
							break;					
						}
					}
					continue;
				}

				if(pendingRecordsCount == batchsize && batchsize>0)
				{
					break;
				}
				JsonObject privilegeGroupUploadObj	= new JsonObject();
				privilegeGroupUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					privilegeGroup = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("privilegeGroupId"))
						{
							String privilegeGroupId = row.getCell(cellIndex++).getStringCellValue();
							if(privilegeGroupId != null && privilegeGroupId.trim().length() > 0)
							{
								try
								{
									Integer iPrivilegeGroupId = Integer.parseInt(privilegeGroupId);
									if(areSourceDestinationSame == 1)
									{
										PrivilegeGroup privilegeGroupObj = (PrivilegeGroup)session.get(PrivilegeGroup.class, iPrivilegeGroupId);
										if(privilegeGroupObj != null)
										{ 
											privilegeGroup.addProperty("privilegeGroupId", iPrivilegeGroupId);
										}
										else
										{
											privilegeGroupUploadObj.addProperty("isDataFetched", 0);
											privilegeGroupUploadObj.addProperty("msg", "This Privilege Group could not be uploaded as there appears to be some problem with the data(PrivilegeGroup Id is not exist). ");
											continue;
										}
									}
									else
									{
										PrivilegeGroup privilegeGroupObj = getPrivilegeGroupByLegacyRecordId(session, iPrivilegeGroupId);
										if(privilegeGroupObj != null)
										{ 
											privilegeGroup.addProperty("privilegeGroupId", privilegeGroupObj.getPrivilegeGroupId());
										}
										privilegeGroup.addProperty("legacyRecordId", iPrivilegeGroupId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									privilegeGroupUploadObj.addProperty("isDataFetched", 0);
									privilegeGroupUploadObj.addProperty("msg", "This Privilege Group could not be uploaded as there appears to be some problem with the data(PrivilegeGroup Id). ");
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
							privilegeGroup.addProperty(parameterName, parameterValue);
						}
					}
					privilegeGroupUploadObj.add("dataObject", privilegeGroup);		    
					privilegeGroupList.add(privilegeGroupUploadObj);
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
			dataObject.add("privilegeGroupList", privilegeGroupList);
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
		if(previousRetryCountMap.has("PrivilegeGroup") && previousRetryCountMap.get("PrivilegeGroup").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("PrivilegeGroup").getAsInt();
		}
		if(retryCountMap.has("PrivilegeGroup") && retryCountMap.get("PrivilegeGroup").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("PrivilegeGroup").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    		com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

		return 0;
	}
	public void updateRetryCountMapForPrivilegeGroupList(JsonArray privilegeGroupList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < privilegeGroupList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject privilegeGroupDataObject = privilegeGroupList.get(i).getAsJsonObject();
			JsonObject privilegeGroup = privilegeGroupDataObject.get("dataObject").getAsJsonObject();
			if(privilegeGroupDataObject.has("retryUpload") && privilegeGroupDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = privilegeGroupDataObject.get("retryUpload").getAsInt();
			}
			if(privilegeGroupDataObject.has("retryChildEntitiesUpload") && privilegeGroupDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = privilegeGroupDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(privilegeGroupDataObject.has("partialUploadUnderProcess") && privilegeGroupDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = privilegeGroupDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    		    if(privilegeGroup.has("privilegeGroupItemsList"))
		    {
				com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(getDBSession());
				JsonArray privilegeGroupItemsList = privilegeGroup.get("privilegeGroupItemsList").getAsJsonArray();
				privilegeGroupItemsImplObj.updateRetryCountMapForPrivilegeGroupItemsList(privilegeGroupItemsList, retryCountMap);
		    }

		}
	    retryCountMap.addProperty("PrivilegeGroup", retryCount);
	}
	public JsonObject uploadPrivilegeGroupList(JsonArray privilegeGroupList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(privilegeGroupList);
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
	public JsonObject updateStatusMsg(JsonArray privilegeGroupList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < privilegeGroupList.size(); i++)
			{
				JsonObject privilegeGroupDataObject = privilegeGroupList.get(i).getAsJsonObject();
				JsonObject privilegeGroup = privilegeGroupDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = privilegeGroupDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = privilegeGroupDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = privilegeGroupDataObject.get("errorMessage").getAsString();
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
			    			    if(isSuccessfullyUploaded == 1 && privilegeGroup.has("privilegeGroupItemsList"))
			    {
					com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(getDBSession());
					JsonArray privilegeGroupItemsList = privilegeGroup.get("privilegeGroupItemsList").getAsJsonArray(); 
					responseData  = privilegeGroupItemsImplObj.updateStatusMsg(privilegeGroupItemsList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
				}

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
	public JsonObject uploadData(JsonArray privilegeGroupList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < privilegeGroupList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject privilegeGroupDataObject = privilegeGroupList.get(i).getAsJsonObject();
				JsonObject privilegeGroup = privilegeGroupDataObject.get("dataObject").getAsJsonObject();
				privilegeGroupDataObject.addProperty("retryUpload", 0);
				privilegeGroupDataObject.addProperty("retryChildEntitiesUpload", 0);
				privilegeGroupDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = privilegeGroupImplObj.getEntityInfoUpdatedWithLookupIds(session, privilegeGroup, retValObject);
				if(areAllLookupsFound!=1)
				{
					privilegeGroupDataObject.addProperty("retryUpload", 1);
					privilegeGroupDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					privilegeGroupDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					privilegeGroupDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int privilegeGroupId = -1;
				int keyColumnsCount = 0;
								keyColumnsCount++;
				keyColumnsCount++;

				if(keyColumnsCount > 0 && !privilegeGroup.has("privilegeGroupId"))
				{
					privilegeGroup.addProperty("attributeNamePrefix", "");
					
					privilegeGroup.addProperty("attributeNamePrefix", "");
					JsonObject responseData = privilegeGroupImplObj.getPrivilegeGroupByLookupFields(session,  privilegeGroup);
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
						JsonObject privilegeGroupObject = responseData.get("privilegeGroupDataObject").getAsJsonObject();
						privilegeGroupId = privilegeGroupObject.get("privilegeGroupId").getAsInt();
						privilegeGroup.addProperty("privilegeGroupId", privilegeGroupId);
						updateEntity = true;
					}
				}
				else if(privilegeGroup.has("privilegeGroupId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = privilegeGroupImplObj.createPrivilegeGroup(privilegeGroup);
				}
				else
				{
					responseData = privilegeGroupImplObj.updatePrivilegeGroup(privilegeGroup);
				}
				privilegeGroupDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					privilegeGroupId =-1;
					privilegeGroupDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					privilegeGroupDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				privilegeGroupId = responseData.get("privilegeGroupId").getAsInt();
				privilegeGroupDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    			    if(privilegeGroup.has("privilegeGroupItemsList"))
			    {
				    JsonArray privilegeGroupItemsList = privilegeGroup.get("privilegeGroupItemsList").getAsJsonArray();
					com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl privilegeGroupItemsImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupItemsControllerImpl(session, getUserSessionInfo());
					responseData  = privilegeGroupItemsImplObj.deletePrivilegeGroupItemsListIfKeyColumnsNotFound(session, privilegeGroupId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = privilegeGroupItemsImplObj.uploadData(privilegeGroupItemsList, privilegeGroupId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int privilegeGroupItemsListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += privilegeGroupItemsListRetryCount;
					if(privilegeGroupItemsListRetryCount>0)
					{
						privilegeGroupDataObject.addProperty("retryChildEntitiesUpload", 1);
					}
				}

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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject privilegeGroup, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getPrivilegeGroupByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From PrivilegeGroup where 1 = 1  ";
			String countHql = "select count(*)  from PrivilegeGroup where 1 = 1 ";
						
			String privilegeGroupName = requestParams.get("privilegeGroupName").getAsString();
			hql += " and privilegeGroupName = :privilegeGroupName ";
			countHql += " and privilegeGroupName = :privilegeGroupName ";String privilegeCode = requestParams.get("privilegeCode").getAsString();
			hql += " and privilegeCode = :privilegeCode ";
			countHql += " and privilegeCode = :privilegeCode ";
Query countQuery = session.createQuery(countHql);			countQuery.setParameter("privilegeGroupName", privilegeGroupName);
			countQuery.setParameter("privilegeCode", privilegeCode);
Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Privilege Group could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);			query.setParameter("privilegeGroupName", privilegeGroupName);
			query.setParameter("privilegeCode", privilegeCode);
List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				PrivilegeGroup privilegeGroup = (PrivilegeGroup) it.next();
				JsonObject privilegeGroupDataObject = privilegeGroup.getDataObject(session);
				dataObject.add("privilegeGroupDataObject", privilegeGroupDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Privilege Group could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			String privilegeGroupName = searchObject.get(attributeNamePrefix + "_" + "privilegeGroupName").getAsString();
			requestParams.addProperty("privilegeGroupName", privilegeGroupName);String privilegeCode = searchObject.get(attributeNamePrefix + "_" + "privilegeCode").getAsString();
			requestParams.addProperty("privilegeCode", privilegeCode);
dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Privilege Group' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			if(searchObject.has(attributeNamePrefix + "_" + "privilegeGroupName"))
			{
				String privilegeGroupName = searchObject.get(attributeNamePrefix + "_" + "privilegeGroupName").getAsString();
				if(privilegeGroupName!=null && privilegeGroupName.length()>0)
				{
					return 1;
				}
			}if(searchObject.has(attributeNamePrefix + "_" + "privilegeCode"))
			{
				String privilegeCode = searchObject.get(attributeNamePrefix + "_" + "privilegeCode").getAsString();
				if(privilegeCode!=null && privilegeCode.length()>0)
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
		else if(apiName.equals("getPrivilegeGroupPropertyValue"))
			{
				JsonObject inputDataObject = getPrivilegeGroupPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getPrivilegeGroup"))
			{
				JsonObject inputDataObject = getPrivilegeGroup(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForPrivilegeGroup"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForPrivilegeGroup"))
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
			Integer privilegeGroupId = requestReceivedParametersInfo.get("privilegeGroupId").getAsInt();
			PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
			privilegeGroup.setIsRequestUnderProcesss(0);
			session.merge(privilegeGroup);
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
	public JsonObject getPrivilegeGroupPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer privilegeGroupId = inputForGetAPI.get("privilegeGroupId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		PrivilegeGroup privilegeGroup = (PrivilegeGroup) session.get(PrivilegeGroup.class, privilegeGroupId);
		privilegeGroup.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getPrivilegeGroup(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer privilegeGroupId = inputForGetAPI.get("privilegeGroupId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("privilegeGroupId", String.valueOf(privilegeGroupId));
		JsonObject privilegeGroupListObject = retrievePrivilegeGroupList(paramsMap);
		if(privilegeGroupListObject!=null && privilegeGroupListObject.has("success") && privilegeGroupListObject.get("success").getAsInt()==1)
		{
			JsonArray privilegeGroupList = privilegeGroupListObject.get("privilegeGroupList").getAsJsonArray();
			if(privilegeGroupList.size()>0)
			{
				dataObject.add("privilegeGroup", privilegeGroupList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Privilege Group' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject privilegeGroupDataObject, PrivilegeGroup privilegeGroup);
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
	public abstract void doAfterLookupRowSelectedImpl(PrivilegeGroup privilegeGroup, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(PrivilegeGroup privilegeGroup, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(PrivilegeGroup privilegeGroup, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
