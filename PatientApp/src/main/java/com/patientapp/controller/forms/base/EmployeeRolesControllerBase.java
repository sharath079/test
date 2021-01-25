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
import com.patientapp.bean.UserInfo;
import com.patientapp.controller.forms.impl.UserInfoControllerImpl;
import com.patientapp.controller.forms.base.UserInfoLabel;
import com.patientapp.bean.UserInfo;
import com.patientapp.controller.forms.impl.UserInfoControllerImpl;
//

import com.patientapp.bean.EmployeeRoles;
import com.patientapp.controller.forms.base.EmployeeRolesLabel;
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
public abstract class EmployeeRolesControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'PrivilegeGroup' 
	 *		 * 'Description' 
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
	protected EmployeeRolesLabel EmployeeRolesLabelLocal = new EmployeeRolesLabel();
	protected EmployeeRoles EmployeeRolesLocal = new EmployeeRoles();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public EmployeeRolesControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public EmployeeRolesControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public EmployeeRolesControllerBase()
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
		return "EmployeeRoles" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((EmployeeRoles)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((EmployeeRoles)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((EmployeeRoles)obj).getEmployeeRolesId();
	}
	public String getManagedBeanName()
    {
		return "EmployeeRolesBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "EmployeeRolesLabelBean";
    }
	protected Class<EmployeeRoles> setBeanClass()
	{
		return EmployeeRoles.class;
	}
	protected Class<EmployeeRolesLabel> setBeanLabelClass()
	{
		return EmployeeRolesLabel.class;
	}
	protected EmployeeRoles getLocalManagedBean()
    {
		return (EmployeeRoles)getManagedBean();
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
		/*EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		String areChangesEffected = employeeRoles.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			employeeRoles.setAreChangesEffected("YES");			
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
		/*EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		String areChangesEffected = employeeRoles.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			employeeRoles.setAreChangesEffected("NO");			
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
	{UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = userInfoControllerImpl.isTransactionUpdatable();
		return isParentTransactionUpdatable;
		
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		String sCurrentStatus = employeeRoles.getVwTxnStatus();
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
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(employeeRoles, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterEmployeeRolesLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				if(initParamsInfo.has("privilegeGroup") && initParamsInfo.get("privilegeGroup").isJsonNull()==false)
		{
			Integer privilegeGroupId = initParamsInfo.get("privilegeGroupId").getAsInt();
			employeeRoles.setPrivilegeGroupId(privilegeGroupId);
		}if(initParamsInfo.has("description") && initParamsInfo.get("description").isJsonNull()==false)
		{
			String description = initParamsInfo.get("description").getAsString();
			employeeRoles.setDescription(description);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(employeeRoles, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}
				else if("privilegeGroup".equalsIgnoreCase(attributeName))
		{
			  
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(employeeRoles, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = userInfoControllerImpl.isTransactionUpdatable();
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
			EmployeeRoles employeeRoles = (EmployeeRoles)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "EmployeeRoles", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() EmployeeRolesContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (EmployeeRolesSearch)getManagedBean("EmployeeRolesSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getPrivilegeGroup()))
			{
				searchParams.put(EmployeeRolesLabelLocal.getprivilegeGroupFieldName(),searchBeanLocal.getPrivilegeGroup());
			}	
			if (isExists(searchBeanLocal.getDescription()))
			{
				searchParams.put(EmployeeRolesLabelLocal.getdescriptionFieldName(),searchBeanLocal.getDescription());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(EmployeeRolesLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() EmployeeRolesContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues EmployeeRolesContollerBase");
		targetBean = (EmployeeRoles)targetBean;UserInfo UserInfoLocal = (UserInfo)getManagedBean("UserInfoBean");
		((EmployeeRoles)targetBean).setUserInfoId(UserInfoLocal.getUserInfoId());
		((EmployeeRoles)targetBean).setEmployeeRolesId(((EmployeeRoles)sourceBean).getEmployeeRolesId());
				((EmployeeRoles)targetBean).setDescription(((EmployeeRoles)sourceBean).getDescription());

				((EmployeeRoles)targetBean).setPrivilegeGroupId(((EmployeeRoles)sourceBean).getPrivilegeGroupId());

				/*if (bSetAsManagedBean)
		{			
			PrivilegeGroup PrivilegeGroupLocal = (PrivilegeGroup)(( EmployeeRoles)sourceBean).getPrivilegeGroup();
			setManagedBean("PrivilegeGroupBean", PrivilegeGroupLocal);
		}*/

		doAfterSetValues();
		debugCode("Out setValues EmployeeRolesContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.EmployeeRoles entityBean = (EmployeeRoles)entityObject;
	 	if (sFieldName.equalsIgnoreCase("employeeRolesId")) 
	 	{
			return entityBean.getEmployeeRolesId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("Description")) 
	 	{
			return entityBean.getDescription();
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
		debugCode("In doEnrichSystemValues(String sAction) EmployeeRolesControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((EmployeeRoles) localManagedBean).setVwLastModifiedDate(new Date());
		((EmployeeRoles) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((EmployeeRoles) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((EmployeeRoles) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((EmployeeRoles) localManagedBean).setVwTxnStatus("CREATED");
			((EmployeeRoles) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((EmployeeRoles) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((EmployeeRoles) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) EmployeeRolesControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) EmployeeRolesControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) EmployeeRolesControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations EmployeeRolesControllerBase");
		//NG: Important comment
		//managedBean = (EmployeeRoles) getManagedBean();
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
		debugCode("Out doValidations EmployeeRolesControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation EmployeeRolesControllerBase");
		debugCode("Out doAllowedDecimalValidation EmployeeRolesControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation EmployeeRolesControllerBase");debugCode("Out doAllowedValuesValidation EmployeeRolesControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation EmployeeRolesControllerBase");		Integer iFieldValue2 = ((EmployeeRoles) localManagedBean).getPrivilegeGroupId();
		if (iFieldValue2 <= 0) addMandatoryResponse(EmployeeRolesLabelLocal.getprivilegeGroupFieldName());

		debugCode("Out doMandatoryValidation EmployeeRolesControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation EmployeeRolesControllerBase");
				
		String sFieldValue3 = ((EmployeeRoles) localManagedBean).getDescription();

				Integer iFieldValue2 = ((EmployeeRoles) localManagedBean).getPrivilegeGroupId();

				if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(EmployeeRolesLabelLocal.getdescriptionFieldName(),"50");

				//if (!isLengthAllowed(iFieldValue2,"")) addMaxLengthResponse(EmployeeRolesLabelLocal.getprivilegeGroupFieldName(),"");

		debugCode("Out doLengthValidation EmployeeRolesControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation EmployeeRolesControllerBase");
		debugCode("Out doDataTypeValidation EmployeeRolesControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation EmployeeRolesContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			UserInfo UserInfoLocal = (UserInfo)getManagedBean("UserInfoBean");
			if (UserInfoLocal!=null)
			{
				iFieldValueFK = UserInfoLocal.getUserInfoId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation EmployeeRolesContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel EmployeeRolesContollerBase");
		String sLabel = new EmployeeRolesLabel().getLabel(sResponseField); 
		debugCode("Out getLabel EmployeeRolesContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int employeeRolesId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("employeeRolesId", employeeRolesId);
			JsonObject responseData = retrieveEmployeeRoles(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Employee Roles' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject employeeRolesDataObject = responseData.get("employeeRolesDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, employeeRolesDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Employee Roles' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveEmployeeRoles(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer employeeRolesId = -1;
		if(requestParams.has("employeeRolesId"))
		{
			employeeRolesId = requestParams.get("employeeRolesId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("employeeRolesId", String.valueOf(employeeRolesId));
				String description = "";
		if(requestParams.has("description"))
		{
			paramsMap.put("description", requestParams.get("description").getAsString());
		}

				Integer privilegeGroupId = -1;
		if(requestParams.has("privilegeGroupId"))
		{
			paramsMap.put("privilegeGroupId", requestParams.get("privilegeGroupId").getAsString());
		}

			
		Integer userInfoId = -1;;
		if(requestParams.has("userInfoId"))
		{
			paramsMap.put("userInfoId", requestParams.get("userInfoId").getAsString());
		}JsonObject employeeRolesListObject = retrieveEmployeeRolesList(paramsMap);
		if(employeeRolesListObject!=null && employeeRolesListObject.has("success") && employeeRolesListObject.get("success").getAsInt()==1)
		{
			JsonArray employeeRolesList = employeeRolesListObject.get("employeeRolesList").getAsJsonArray();
			if(employeeRolesList.size()>0)
			{
				dataObject.add("employeeRolesDataObject", employeeRolesList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Employee Roles' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getEmployeeRoles(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String description = "";
			if(searchParams.has("description"))
			{
				paramsMap.put("description", searchParams.get("description").getAsString());
			}

						Integer privilegeGroupId = -1;
			if(searchParams.has("privilegeGroupId"))
			{
				paramsMap.put("privilegeGroupId", searchParams.get("privilegeGroupId").getAsString());
			}

				
			Integer userInfoId = -1;;
			if(searchParams.has("userInfoId"))
			{
				paramsMap.put("userInfoId", searchParams.get("userInfoId").getAsString());
			}
			
			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject employeeRolesListObject = retrieveEmployeeRolesList(paramsMap);
			if(employeeRolesListObject!=null && employeeRolesListObject.has("success") && employeeRolesListObject.get("success").getAsInt()==1)
			{
				JsonArray employeeRolesList = employeeRolesListObject.get("employeeRolesList").getAsJsonArray();
				if(employeeRolesList.size()>0)
				{
					dataObject.add("employeeRoles", employeeRolesList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Employee Roles' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getEmployeeRolesInJson(int employeeRolesId)
	{
		JsonObject EmployeeRolesData = null;
		List<Integer> employeeRolesIdsList = new ArrayList<>();
		employeeRolesIdsList.add(employeeRolesId);
		JsonArray employeeRolesList = getEmployeeRolesListInJson(employeeRolesIdsList);
		if(employeeRolesList!=null && employeeRolesList.size()>0)
		{
			EmployeeRolesData = employeeRolesList.get(0).getAsJsonObject();
		}
		return EmployeeRolesData;
	}
	public JsonArray getEmployeeRolesListInJson(List<Integer> employeeRolesIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray employeeRolesObjectsList = new JsonArray();
		JsonObject employeeRolesListObject = retrieveEmployeeRolesList(paramsMap, employeeRolesIdsList);
		if(employeeRolesListObject!=null && employeeRolesListObject.has("success") && employeeRolesListObject.get("success").getAsInt()==1)
		{
			JsonArray employeeRolesList = employeeRolesListObject.get("employeeRolesList").getAsJsonArray();
			for (int i =0; i< employeeRolesList.size(); i++)
			{
				JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
				int employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
				
				employeeRolesObjectsList.add(employeeRolesDataObject);
			}
		}
		return employeeRolesObjectsList;
	}
	
	public JsonArray getEmployeeRolesListFromParentInJson(int userInfoId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userInfoId", String.valueOf(userInfoId));
		JsonArray employeeRolesObjectsList = new JsonArray();
		JsonObject employeeRolesListObject = retrieveEmployeeRolesList(paramsMap);
		if(employeeRolesListObject!=null && employeeRolesListObject.has("success") && employeeRolesListObject.get("success").getAsInt()==1)
		{
			JsonArray employeeRolesList = employeeRolesListObject.get("employeeRolesList").getAsJsonArray();
			for (int i =0; i< employeeRolesList.size(); i++)
			{
				JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
				int employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
				
			    employeeRolesObjectsList.add(employeeRolesDataObject);
			}
		}
		return employeeRolesObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray employeeRolesList)
	{
		String errorMessage = "employeeRolesList: \n";
		for (int i =0; i< employeeRolesList.size(); i++)
		{
			JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
			JsonObject employeeRoles = employeeRolesDataObject.get("dataObject").getAsJsonObject();
			
			if(!employeeRolesDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "employeeRoles could not be uploaded verify data \n"; 
			}
			else if(employeeRolesDataObject.has("isSuccessfullyUploaded") 
					&& employeeRolesDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += employeeRolesDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("EmployeeRoles".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND employeeRolesId = :employeeRolesId ";
			return loginBasedWhereClause;
		}
				else if("PrivilegeGroup".equalsIgnoreCase(loginEntityType))
		{
			String neutralAccessClause = "";
			loginBasedWhereClause = " AND (privilegeGroupId = :privilegeGroupId )";
			return loginBasedWhereClause;
		}

		return "";		
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
		else if("EmployeeRoles".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("employeeRolesId", userId);
		}
				else if("PrivilegeGroup".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("privilegeGroupId", userId);
		}

	}
	public String getParentFilterClauseForEmployeeRoles(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";		String userInfoFilterClause = " select userInfoId from UserInfo where 1=1  ";
		int userInfoId = -1;
		if(paramsMap.containsKey("userInfoId"))
		{
			userInfoId = Integer.parseInt(paramsMap.get("userInfoId"));			
		}
		if(userInfoId>0)
		{
			userInfoFilterClause += " and userInfoId = :userInfoId  ";
		}
					
		
parentFilterClause = " and userInfoId in (" + userInfoFilterClause + ")";
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
	public void setParentFilterClauseParamsForEmployeeRoles(java.util.Map<String, String> paramsMap, Query query)
	{		int userInfoId = -1;
		if(paramsMap.containsKey("userInfoId"))
		{
			userInfoId = Integer.parseInt(paramsMap.get("userInfoId"));			
		}
		if(userInfoId>0)
		{
			query.setParameter("userInfoId", userInfoId);
		}			

	}
	public JsonObject retrieveEmployeeRolesList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> employeeRolesIdsList = new ArrayList<>();
		if(paramsMap.containsKey("employeeRolesId"))
		{
			int employeeRolesId = Integer.parseInt(paramsMap.get("employeeRolesId"));
			if(employeeRolesId>0)
			{
				employeeRolesIdsList.add(employeeRolesId);
			}
		}
		return retrieveEmployeeRolesList(paramsMap, employeeRolesIdsList);
	}
	public JsonObject retrieveEmployeeRolesList(java.util.Map<String, String> paramsMap, List<Integer> employeeRolesIdsList)
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
						String description = paramsMap.get("description");
			if(description==null)
			{
				description = "";
			}

						Integer privilegeGroupId = -2;
	    	if(paramsMap.containsKey("privilegeGroupId"))
	    	{
				privilegeGroupId = Integer.parseInt(paramsMap.get("privilegeGroupId"));
	    	}

			String hql = "FROM EmployeeRoles where 1 = 1 ";
			String orderByClauseText = "  ";
			String employeeRolesIdFilterClass = "";
			if (employeeRolesIdsList != null && employeeRolesIdsList.size() > 0)
			{
				employeeRolesIdFilterClass = " AND employeeRolesId in (:idsList) ";
			}
						String descriptionFilterClass = "";
			if (description.length() > 0)
			{		
				
				descriptionFilterClass = " AND description LIKE :description ";	
				
			}

						String privilegeGroupFilterClass = "";
			if (privilegeGroupId >= -1)
			{
				privilegeGroupFilterClass = " AND privilegeGroupId = :privilegeGroupId";
			}

			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForEmployeeRoles(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					employeeRolesIdFilterClass +
										privilegeGroupFilterClass +
					descriptionFilterClass +

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
			if (employeeRolesIdsList != null && employeeRolesIdsList.size() > 0)
			{
				query.setParameterList("idsList", employeeRolesIdsList);
			}
						if (description.length() > 0)
			{		
				
				query.setParameter("description", description);	
				
			}

						if (privilegeGroupId >= -1)
			{
				query.setParameter("privilegeGroupId", privilegeGroupId);
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
	    	setParentFilterClauseParamsForEmployeeRoles(paramsMap, query);
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
			JsonArray employeeRolesList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmployeeRoles employeeRoles = (EmployeeRoles) it.next();
				JsonObject employeeRolesDataObject = employeeRoles.getDataObject(getDBSession());
				employeeRolesDataObject.addProperty("nextAction", getActionForCurrentStatus(employeeRoles.getVwTxnStatus()));
				employeeRolesList.add(employeeRolesDataObject);
				doAfterSearchResultRowAddedImpl(employeeRolesDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from EmployeeRoles where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (employeeRolesIdsList != null && employeeRolesIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", employeeRolesIdsList);
				}
								if (description.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("description", description);
					
					
					
					
				}

								if (privilegeGroupId >= -1)
				{
					countQuery.setParameter("privilegeGroupId", privilegeGroupId);
				}

				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForEmployeeRoles(paramsMap, countQuery);
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
			dataObject.add("employeeRolesList",   employeeRolesList);
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
				+ "   from EmployeeRoles E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from EmployeeRoles E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentEmployeeRolesList(java.util.Map<String, String> paramsMap)
	{
		return retrieveEmployeeRolesList(paramsMap);
	}
	public EmployeeRoles getEmployeeRolesByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from EmployeeRoles where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			EmployeeRoles employeeRoles = (EmployeeRoles) it.next();
			return employeeRoles;
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
		EmployeeRoles employeeRoles = (EmployeeRoles)getManagedBean();
		JsonObject employeeRolesDataObject = employeeRoles.getDataObject(getDBSession());copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject);
		setManagedBean(employeeRoles);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData EmployeeRolesContollerBase");
			EmployeeRoles currentBean = (EmployeeRoles)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "Description".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setDescription(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData EmployeeRolesContollerBase");
	}
	// end Test Data
	public void copyEmployeeRolesFromJson(EmployeeRoles employeeRoles, JsonObject employeeRolesDataObject)
	{
		copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject, false);
	}
	public void copyEmployeeRolesFromJson(EmployeeRoles employeeRoles, JsonObject employeeRolesDataObject, boolean excludePasswordFields)
	{	
				if(employeeRolesDataObject.has("privilegeGroupId"))
		{
			Integer privilegeGroupId = employeeRolesDataObject.get("privilegeGroupId").getAsInt();
			employeeRoles.setPrivilegeGroupId(privilegeGroupId);
		}if(employeeRolesDataObject.has("description"))
		{
			String description = employeeRolesDataObject.get("description").getAsString();
			employeeRoles.setDescription(description);
		}
		
	}
		
	public JsonObject createEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		return createEmployeeRoles(employeeRolesDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject employeeRolesDataObject)
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
			employeeRolesDataObject.addProperty("privilegeGroupId", userId);
		}

	}
	public JsonObject createEmployeeRoles(JsonObject employeeRolesDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		EmployeeRoles employeeRoles = new EmployeeRoles();
		setLoginBasedValues(paramsInfoObj, employeeRolesDataObject);
		Session session = getDBSession();				
		copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject);
		if(employeeRolesDataObject.has("legacyRecordId"))
		{
			employeeRoles.setLegacyRecordId(employeeRolesDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer userInfoId = employeeRolesDataObject.get("userInfoId").getAsInt();
		com.patientapp.bean.UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
		employeeRoles.setUserInfoId(userInfoId);
		UserInfoControllerImpl userInfoImplObj = new UserInfoControllerImpl(session);
		setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setPrivateManagedBean(userInfo);
		if(!userInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(userInfoImplObj.getTransactionFailureMessage());
		}employeeRoles.setVwCreatedBy(createdBy);
		employeeRoles.setVwCreationDate(new Date());
		setPrivateManagedBean(employeeRoles);
		setManagedBean("EmployeeRolesBean", employeeRoles);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		employeeRoles = (EmployeeRoles) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("employeeRolesId", employeeRoles.getEmployeeRolesId());
			JsonObject employeeRolesObj = employeeRoles.getDataObject(getDBSession());
			employeeRolesObj.addProperty("nextAction", getActionForCurrentStatus(employeeRoles.getVwTxnStatus()));
			dataObject.add("employeeRolesDataObject", employeeRolesObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("employeeRolesId", employeeRolesId);
		JsonObject responseData = retrieveEmployeeRoles(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Employee Roles' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
		if(employeeRoles == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(employeeRoles.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromEmployeeRoles(employeeRolesDataObject);
		boolean excludePasswordFields = true;
		copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject, excludePasswordFields);
			
		com.patientapp.bean.UserInfo userInfo = (UserInfo) session.get(UserInfo.class, employeeRoles.getUserInfoId());
		UserInfoControllerImpl userInfoImplObj = new UserInfoControllerImpl(session);
		setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setPrivateManagedBean(userInfo);
		if(!userInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(userInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(employeeRoles);
		setManagedBean("EmployeeRolesBean", employeeRoles);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		employeeRoles.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("employeeRolesId", employeeRolesId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
	}
	public JsonObject deleteEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("employeeRolesId", employeeRolesId);
		JsonObject responseData = retrieveEmployeeRoles(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Employee Roles' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
			
		com.patientapp.bean.UserInfo userInfo = (UserInfo) session.get(UserInfo.class, employeeRoles.getUserInfoId());
		UserInfoControllerImpl userInfoImplObj = new UserInfoControllerImpl(session);
		setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setManagedBean(userInfoImplObj.getManagedBeanName(), userInfo);
		userInfoImplObj.setPrivateManagedBean(userInfo);
		if(!userInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(userInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(employeeRoles);
		setManagedBean("EmployeeRoles", employeeRoles);
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
	public JsonObject fetchEmployeeRolesDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		EmployeeRoles employeeRoles = new EmployeeRoles();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRoles", employeeRoles);
			doAfterEmployeeRolesLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("employeeRoles", employeeRoles.getDataObject(getDBSession()));
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
	public JsonObject fetchEmployeeRolesTestData(int obj, JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		EmployeeRoles employeeRoles = new EmployeeRoles();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject);
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRoles", employeeRoles);
			doAfterEmployeeRolesLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("employeeRoles", employeeRoles.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		EmployeeRoles employeeRoles = new EmployeeRoles();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject);	
			
			Integer userInfoId = employeeRolesDataObject.get("userInfoId").getAsInt();
			employeeRoles.setUserInfoId(userInfoId);
			
			String attributeName = employeeRolesDataObject.get("attributeName").getAsString();
			Integer attributeId = employeeRolesDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRoles", employeeRoles);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("employeeRoles", employeeRoles.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		EmployeeRoles employeeRoles = new EmployeeRoles();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmployeeRolesFromJson(employeeRoles, employeeRolesDataObject);	
			String attributeName = employeeRolesDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRoles", employeeRoles);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("employeeRoles", employeeRoles.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
			String customEventName = employeeRolesDataObject.get("customEventName").getAsString();
			EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRolesBean", employeeRoles);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("employeeRoles", employeeRoles.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnEmployeeRoles(JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer employeeRolesId = employeeRolesDataObject.get("employeeRolesId").getAsInt();
			String currentStatus = employeeRolesDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(employeeRolesDataObject.has("currentAction"))
			{
				currentAction = employeeRolesDataObject.get("currentAction").getAsString();
			}
			EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
			setPrivateManagedBean(employeeRoles);
			setManagedBean("EmployeeRolesBean", employeeRoles);
			if(employeeRoles.getIsRequestUnderProcesss() == 1)
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
				executeAction(employeeRoles, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(employeeRoles, "ClassInfoBean", currentStatus);
			}
//			executeAction(employeeRoles, "EmployeeRolesBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedemployeeRolesDataObject = employeeRoles.getDataObject(getDBSession());
				updatedemployeeRolesDataObject.addProperty("nextAction", getActionForCurrentStatus(employeeRoles.getVwTxnStatus()));
				dataObject.add("employeeRoles", updatedemployeeRolesDataObject);
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
		EmployeeRoles employeeRoles = (EmployeeRoles) getManagedBean();
		String currentStatus = employeeRoles.getVwTxnStatus();
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
	
	
	public JsonObject downloadEmployeeRolesData() throws Exception
	{
		return downloadEmployeeRolesData(null);
	}
	public JsonObject downloadEmployeeRolesData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadEmployeeRolesData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadEmployeeRolesData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer userInfoId) throws Exception
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
			headerName = "EmployeeRoles";
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
			headerName = "employeeRolesId";
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
			headerName = "privilegeGroup_privilegeGroupName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "privilegeGroup_privilegeCode";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

		

			
			cell = row.createCell(headerCellCount++);
			headerName = "description";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From EmployeeRoles ";
			
			hql += " WHERE userInfoId  = :userInfoId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("userInfoId", userInfoId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				EmployeeRoles employeeRoles = (EmployeeRoles) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer employeeRolesId = employeeRoles.getEmployeeRolesId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(employeeRolesId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	Integer privilegeGroupId = employeeRoles.getPrivilegeGroupId();
				com.patientapp.bean.PrivilegeGroup  privilegeGroupObj = null;
				if(privilegeGroupId > 0)
				{   
					cell.setCellValue(String.valueOf(privilegeGroupId));
					privilegeGroupObj = (com.patientapp.bean.PrivilegeGroup) session.get(com.patientapp.bean.PrivilegeGroup.class, privilegeGroupId);
				}   
		        				cell = row.createCell(dataCellCount++);
		cell.setCellStyle(dataStyle);if(privilegeGroupObj != null && privilegeGroupObj.getPrivilegeGroupName() !=null)
		{
			cell.setCellValue(privilegeGroupObj.getPrivilegeGroupName());
		}
		cell = row.createCell(dataCellCount++);
		cell.setCellStyle(dataStyle);if(privilegeGroupObj != null && privilegeGroupObj.getPrivilegeCode() !=null)
		{
			cell.setCellValue(privilegeGroupObj.getPrivilegeCode());
		}

				

				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String description = employeeRoles.getDescription();
				cell.setCellValue(description);

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
	public JsonObject uploadEmployeeRolesData(JsonObject employeeRolesDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = employeeRolesDataObject.get("fileId").getAsInt();
		String inputFilesZip = employeeRolesDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Employee Roles Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		employeeRolesDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadEmployeeRolesData(workbook, employeeRolesDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Employee Roles Data uploaded successfully.");
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
	public JsonObject uploadEmployeeRolesData(HSSFWorkbook workbook, JsonObject employeeRolesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("EmployeeRoles");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = employeeRolesDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = employeeRolesDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = employeeRolesDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Employee Roles Data uploaded successfully.");
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
			JsonObject employeeRoles = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject employeeRolesListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray employeeRolesList = employeeRolesListObj.get("employeeRolesList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadEmployeeRolesList(employeeRolesList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(employeeRolesList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(employeeRolesList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray employeeRolesList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject employeeRoles = new JsonObject();
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
				JsonObject employeeRolesUploadObj	= new JsonObject();
				employeeRolesUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					employeeRoles = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("employeeRolesId"))
						{
							String employeeRolesId = row.getCell(cellIndex++).getStringCellValue();
							if(employeeRolesId != null && employeeRolesId.trim().length() > 0)
							{
								try
								{
									Integer iEmployeeRolesId = Integer.parseInt(employeeRolesId);
									if(areSourceDestinationSame == 1)
									{
										EmployeeRoles employeeRolesObj = (EmployeeRoles)session.get(EmployeeRoles.class, iEmployeeRolesId);
										if(employeeRolesObj != null)
										{ 
											employeeRoles.addProperty("employeeRolesId", iEmployeeRolesId);
										}
										else
										{
											employeeRolesUploadObj.addProperty("isDataFetched", 0);
											employeeRolesUploadObj.addProperty("msg", "This Employee Roles could not be uploaded as there appears to be some problem with the data(EmployeeRoles Id is not exist). ");
											continue;
										}
									}
									else
									{
										EmployeeRoles employeeRolesObj = getEmployeeRolesByLegacyRecordId(session, iEmployeeRolesId);
										if(employeeRolesObj != null)
										{ 
											employeeRoles.addProperty("employeeRolesId", employeeRolesObj.getEmployeeRolesId());
										}
										employeeRoles.addProperty("legacyRecordId", iEmployeeRolesId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									employeeRolesUploadObj.addProperty("isDataFetched", 0);
									employeeRolesUploadObj.addProperty("msg", "This Employee Roles could not be uploaded as there appears to be some problem with the data(EmployeeRoles Id). ");
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
							employeeRoles.addProperty(parameterName, parameterValue);
						}
					}
					employeeRolesUploadObj.add("dataObject", employeeRoles);		    
					employeeRolesList.add(employeeRolesUploadObj);
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
			dataObject.add("employeeRolesList", employeeRolesList);
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
		if(previousRetryCountMap.has("EmployeeRoles") && previousRetryCountMap.get("EmployeeRoles").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("EmployeeRoles").getAsInt();
		}
		if(retryCountMap.has("EmployeeRoles") && retryCountMap.get("EmployeeRoles").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("EmployeeRoles").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForEmployeeRolesList(JsonArray employeeRolesList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < employeeRolesList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
			JsonObject employeeRoles = employeeRolesDataObject.get("dataObject").getAsJsonObject();
			if(employeeRolesDataObject.has("retryUpload") && employeeRolesDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = employeeRolesDataObject.get("retryUpload").getAsInt();
			}
			if(employeeRolesDataObject.has("retryChildEntitiesUpload") && employeeRolesDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = employeeRolesDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(employeeRolesDataObject.has("partialUploadUnderProcess") && employeeRolesDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = employeeRolesDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("EmployeeRoles", retryCount);
	}
	public JsonObject uploadEmployeeRolesList(JsonArray employeeRolesList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(employeeRolesList);
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
	public JsonObject updateStatusMsg(JsonArray employeeRolesList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < employeeRolesList.size(); i++)
			{
				JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
				JsonObject employeeRoles = employeeRolesDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = employeeRolesDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = employeeRolesDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = employeeRolesDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray employeeRolesList) throws Exception
	
	{
		return uploadData(employeeRolesList, -1);	
	}
	public JsonObject uploadData(JsonArray employeeRolesList, Integer userInfoId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < employeeRolesList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject employeeRolesDataObject = employeeRolesList.get(i).getAsJsonObject();
				JsonObject employeeRoles = employeeRolesDataObject.get("dataObject").getAsJsonObject();
				employeeRolesDataObject.addProperty("retryUpload", 0);
				employeeRolesDataObject.addProperty("retryChildEntitiesUpload", 0);
				employeeRolesDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = employeeRolesImplObj.getEntityInfoUpdatedWithLookupIds(session, employeeRoles, retValObject);
				if(areAllLookupsFound!=1)
				{
					employeeRolesDataObject.addProperty("retryUpload", 1);
					employeeRolesDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					employeeRolesDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					employeeRolesDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int employeeRolesId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !employeeRoles.has("employeeRolesId"))
				{
					employeeRoles.addProperty("attributeNamePrefix", "");
					
					employeeRoles.addProperty("userInfoId", userInfoId);
					
					employeeRoles.addProperty("attributeNamePrefix", "");
					JsonObject responseData = employeeRolesImplObj.getEmployeeRolesByLookupFields(session,  employeeRoles);
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
						JsonObject employeeRolesObject = responseData.get("employeeRolesDataObject").getAsJsonObject();
						employeeRolesId = employeeRolesObject.get("employeeRolesId").getAsInt();
						employeeRoles.addProperty("employeeRolesId", employeeRolesId);
						updateEntity = true;
					}
				}
				else if(employeeRoles.has("employeeRolesId"))
				{
					updateEntity = true;
				}
				
				if(userInfoId > 0)
				{
					employeeRoles.addProperty("userInfoId", userInfoId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = employeeRolesImplObj.createEmployeeRoles(employeeRoles);
				}
				else
				{
					responseData = employeeRolesImplObj.updateEmployeeRoles(employeeRoles);
				}
				employeeRolesDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					employeeRolesId =-1;
					employeeRolesDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					employeeRolesDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				employeeRolesId = responseData.get("employeeRolesId").getAsInt();
				employeeRolesDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject employeeRoles, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;
				hasParamsForLookup = com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl.hasParamsForLookup(employeeRoles, "privilegeGroup");
		if(hasParamsForLookup==0)
		{
			employeeRoles.addProperty("privilegeGroupId", -1);
		}
		else
		{
			dataObject = com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl.getQueryParamsForLookupSearch(employeeRoles, "privilegeGroup");
			requestParams = new JsonObject();
			if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
			{		
				requestParams = dataObject.get("requestParams").getAsJsonObject();
			}
			requestParams.addProperty("attributeNamePrefix", "privilegeGroup");
			requestParams.add("attributesInfo", employeeRoles);
			com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl privilegeGroupImplObj = new com.patientapp.controller.forms.impl.PrivilegeGroupControllerImpl(session);
			JsonObject privilegeGroupResponseData = privilegeGroupImplObj.getPrivilegeGroupByLookupFields(session,  requestParams);
			if(privilegeGroupResponseData!=null && privilegeGroupResponseData.has("success") && privilegeGroupResponseData.get("success").getAsInt()==1)
			{
				JsonObject privilegeGroupDataObject = privilegeGroupResponseData.get("privilegeGroupDataObject").getAsJsonObject();
				int privilegeGroupId = privilegeGroupDataObject.get("privilegeGroupId").getAsInt();
				employeeRoles.addProperty("privilegeGroupId", privilegeGroupId);
			}
			else
			{
				int optionalForUpload = 0;
				
				if(optionalForUpload==1)
				{
					employeeRoles.addProperty("privilegeGroupId", -1);
					retValObject.addProperty("partialUploadUnderProcess", 1);
				}
				else
				{
					return 0;
				}
			}
		}

		return 1;		
	}
	public JsonObject getEmployeeRolesByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer userInfoId = -1;
			if(requestParams.has("userInfoId") && requestParams.get("userInfoId").isJsonNull()==false)
			{
				userInfoId = requestParams.get("userInfoId").getAsInt();
			}
			
			String hql = "From EmployeeRoles where 1 = 1   and userInfoId = :userInfoId ";
			String countHql = "select count(*)  from EmployeeRoles where 1 = 1  and userInfoId = :userInfoId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("userInfoId", userInfoId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Employee Roles could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("userInfoId", userInfoId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmployeeRoles employeeRoles = (EmployeeRoles) it.next();
				JsonObject employeeRolesDataObject = employeeRoles.getDataObject(session);
				dataObject.add("employeeRolesDataObject", employeeRolesDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Employee Roles could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			
			
			if(searchObject.has("userInfoId") && searchObject.get("userInfoId").isJsonNull()==false)
			{
				requestParams.addProperty("userInfoId", searchObject.get("userInfoId").getAsInt());
			}
			
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Employee Roles' could not be retrieved");
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
	
	public JsonObject deleteEmployeeRolesListIfKeyColumnsNotFound(Session session, Integer userInfoId)
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
			String hql = "From EmployeeRoles WHERE userInfoId = :userInfoId ";
			Query query = session.createQuery(hql);
			query.setParameter("userInfoId", userInfoId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmployeeRoles employeeRoles = (EmployeeRoles) it.next();
				int employeeRolesId = employeeRoles.getEmployeeRolesId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
			    employeeRolesImplObj.setPrivateManagedBean(employeeRoles);
			    employeeRolesImplObj.setManagedBean("EmployeeRoles", employeeRoles);
			    employeeRolesImplObj.delete();
				if (employeeRolesImplObj.getHasTransactionFailed() == true)
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
		dataObject.addProperty("alert", "Your request could not be processed as Employee Roles could not be deleted");
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
			else if(apiName.equals("getEmployeeRolesPropertyValue"))
			{
				JsonObject inputDataObject = getEmployeeRolesPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getEmployeeRoles"))
			{
				JsonObject inputDataObject = getEmployeeRoles(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForEmployeeRoles"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForEmployeeRoles"))
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
			Integer employeeRolesId = requestReceivedParametersInfo.get("employeeRolesId").getAsInt();
			EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
			employeeRoles.setIsRequestUnderProcesss(0);
			session.merge(employeeRoles);
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
	public JsonObject getEmployeeRolesPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer employeeRolesId = inputForGetAPI.get("employeeRolesId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		EmployeeRoles employeeRoles = (EmployeeRoles) session.get(EmployeeRoles.class, employeeRolesId);
		employeeRoles.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getEmployeeRoles(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer employeeRolesId = inputForGetAPI.get("employeeRolesId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("employeeRolesId", String.valueOf(employeeRolesId));
		JsonObject employeeRolesListObject = retrieveEmployeeRolesList(paramsMap);
		if(employeeRolesListObject!=null && employeeRolesListObject.has("success") && employeeRolesListObject.get("success").getAsInt()==1)
		{
			JsonArray employeeRolesList = employeeRolesListObject.get("employeeRolesList").getAsJsonArray();
			if(employeeRolesList.size()>0)
			{
				dataObject.add("employeeRoles", employeeRolesList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Employee Roles' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject employeeRolesDataObject, EmployeeRoles employeeRoles);
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
	public abstract void doAfterLookupRowSelectedImpl(EmployeeRoles employeeRoles, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(EmployeeRoles employeeRoles, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(EmployeeRoles employeeRoles, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
