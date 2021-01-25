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

import com.patientapp.bean.LoginSessionInfo;
import com.patientapp.controller.forms.base.LoginSessionInfoLabel;
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
public abstract class LoginSessionInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'LoginUserType' 
	 *		 * 'SelfServiceUserType' 
	 *		 * 'SessionId' 
	 *		 * 'UserId' 
	 *		 * 'LoginTime' 
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
	protected LoginSessionInfoLabel LoginSessionInfoLabelLocal = new LoginSessionInfoLabel();
	protected LoginSessionInfo LoginSessionInfoLocal = new LoginSessionInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public LoginSessionInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public LoginSessionInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public LoginSessionInfoControllerBase()
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
		return "LoginSessionInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((LoginSessionInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((LoginSessionInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((LoginSessionInfo)obj).getLoginSessionInfoId();
	}
	public String getManagedBeanName()
    {
		return "LoginSessionInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "LoginSessionInfoLabelBean";
    }
	protected Class<LoginSessionInfo> setBeanClass()
	{
		return LoginSessionInfo.class;
	}
	protected Class<LoginSessionInfoLabel> setBeanLabelClass()
	{
		return LoginSessionInfoLabel.class;
	}
	protected LoginSessionInfo getLocalManagedBean()
    {
		return (LoginSessionInfo)getManagedBean();
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
		/*LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		String areChangesEffected = loginSessionInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			loginSessionInfo.setAreChangesEffected("YES");			
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
		/*LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		String areChangesEffected = loginSessionInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			loginSessionInfo.setAreChangesEffected("NO");			
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
		/*LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		String areChangesEffected = loginSessionInfo.getAreChangesEffected();
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
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		String sCurrentStatus = loginSessionInfo.getVwTxnStatus();
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
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(loginSessionInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterLoginSessionInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("loginUserType") && initParamsInfo.get("loginUserType").isJsonNull()==false)
		{
			String loginUserType = initParamsInfo.get("loginUserType").getAsString();
			loginSessionInfo.setLoginUserType(loginUserType);			
		}if(initParamsInfo.has("selfServiceUserType") && initParamsInfo.get("selfServiceUserType").isJsonNull()==false)
		{
			String selfServiceUserType = initParamsInfo.get("selfServiceUserType").getAsString();
			loginSessionInfo.setSelfServiceUserType(selfServiceUserType);			
		}if(initParamsInfo.has("sessionId") && initParamsInfo.get("sessionId").isJsonNull()==false)
		{
			String sessionId = initParamsInfo.get("sessionId").getAsString();
			loginSessionInfo.setSessionId(sessionId);			
		}if(initParamsInfo.has("userId") && initParamsInfo.get("userId").isJsonNull()==false)
		{
			Integer userId = null;
			try
			{
			 	userId = initParamsInfo.get("userId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			loginSessionInfo.setUserId(userId);
		}
		
		if(initParamsInfo.has("loginTime") && initParamsInfo.get("loginTime").isJsonNull()==false)
		{
			String loginTime = initParamsInfo.get("loginTime").getAsString();
			if(loginTime.length() > 0)
			{
				try
				{
					loginSessionInfo.setLoginTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(loginTime));
				}
				catch(Exception e)
				{
				}
			}
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(loginSessionInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(loginSessionInfo, attributeName);
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
			LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "LoginSessionInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() LoginSessionInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (LoginSessionInfoSearch)getManagedBean("LoginSessionInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getLoginUserType()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getloginUserTypeFieldName(),searchBeanLocal.getLoginUserType());
			}	
			if (isExists(searchBeanLocal.getSelfServiceUserType()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getselfServiceUserTypeFieldName(),searchBeanLocal.getSelfServiceUserType());
			}	
			if (isExists(searchBeanLocal.getSessionId()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getsessionIdFieldName(),searchBeanLocal.getSessionId());
			}	
			if (isExists(searchBeanLocal.getUserId()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getuserIdFieldName(),searchBeanLocal.getUserId());
			}	
			if (isExists(searchBeanLocal.getLoginTime()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getloginTimeFieldName(),searchBeanLocal.getLoginTime());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(LoginSessionInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() LoginSessionInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues LoginSessionInfoContollerBase");
		targetBean = (LoginSessionInfo)targetBean;((LoginSessionInfo)targetBean).setLoginSessionInfoId(((LoginSessionInfo)sourceBean).getLoginSessionInfoId());
				((LoginSessionInfo)targetBean).setLoginUserType(((LoginSessionInfo)sourceBean).getLoginUserType());
		((LoginSessionInfo)targetBean).setSelfServiceUserType(((LoginSessionInfo)sourceBean).getSelfServiceUserType());
		((LoginSessionInfo)targetBean).setSessionId(((LoginSessionInfo)sourceBean).getSessionId());
		((LoginSessionInfo)targetBean).setUserId(((LoginSessionInfo)sourceBean).getUserId());
		((LoginSessionInfo)targetBean).setLoginTime(((LoginSessionInfo)sourceBean).getLoginTime());

		doAfterSetValues();
		debugCode("Out setValues LoginSessionInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.LoginSessionInfo entityBean = (LoginSessionInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("loginSessionInfoId")) 
	 	{
			return entityBean.getLoginSessionInfoId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("LoginUserType")) 
	 	{
			return entityBean.getLoginUserType();
		}
	 	if (sFieldName.equalsIgnoreCase("SelfServiceUserType")) 
	 	{
			return entityBean.getSelfServiceUserType();
		}
	 	if (sFieldName.equalsIgnoreCase("SessionId")) 
	 	{
			return entityBean.getSessionId();
		}
	 	if (sFieldName.equalsIgnoreCase("UserId")) 
	 	{
			return entityBean.getUserId();
		}
	 	if (sFieldName.equalsIgnoreCase("LoginTime")) 
	 	{
			return entityBean.getLoginTime();
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
		debugCode("In doEnrichSystemValues(String sAction) LoginSessionInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((LoginSessionInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((LoginSessionInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((LoginSessionInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((LoginSessionInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((LoginSessionInfo) localManagedBean).setVwTxnStatus("CREATED");
			((LoginSessionInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((LoginSessionInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((LoginSessionInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) LoginSessionInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) LoginSessionInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) LoginSessionInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations LoginSessionInfoControllerBase");
		//NG: Important comment
		//managedBean = (LoginSessionInfo) getManagedBean();
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
		debugCode("Out doValidations LoginSessionInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation LoginSessionInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation LoginSessionInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation LoginSessionInfoControllerBase");debugCode("Out doAllowedValuesValidation LoginSessionInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation LoginSessionInfoControllerBase");
				
		String sFieldValue2 = ((LoginSessionInfo) localManagedBean).getLoginUserType();Integer sFieldValue5 = ((LoginSessionInfo) localManagedBean).getUserId();		
		
		Date sFieldValue6 = ((LoginSessionInfo) localManagedBean).getLoginTime();
		if (!isExists(sFieldValue2)) addMandatoryResponse(LoginSessionInfoLabelLocal.getloginUserTypeFieldName());
		if (!isExists(sFieldValue5)) addMandatoryResponse(LoginSessionInfoLabelLocal.getuserIdFieldName());
		if (!isExists(sFieldValue6)) addMandatoryResponse(LoginSessionInfoLabelLocal.getloginTimeFieldName());
debugCode("Out doMandatoryValidation LoginSessionInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation LoginSessionInfoControllerBase");
				
		String sFieldValue2 = ((LoginSessionInfo) localManagedBean).getLoginUserType();String sFieldValue3 = ((LoginSessionInfo) localManagedBean).getSelfServiceUserType();String sFieldValue4 = ((LoginSessionInfo) localManagedBean).getSessionId();Integer sFieldValue5 = ((LoginSessionInfo) localManagedBean).getUserId();		
		
		Date sFieldValue6 = ((LoginSessionInfo) localManagedBean).getLoginTime();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(LoginSessionInfoLabelLocal.getloginUserTypeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(LoginSessionInfoLabelLocal.getselfServiceUserTypeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue4,"50")) addMaxLengthResponse(LoginSessionInfoLabelLocal.getsessionIdFieldName(),"50");
		if (!isLengthAllowed(sFieldValue5,"10")) addMaxLengthResponse(LoginSessionInfoLabelLocal.getuserIdFieldName(),"10");
		if (!isLengthAllowed(sFieldValue6,"20")) addMaxLengthResponse(LoginSessionInfoLabelLocal.getloginTimeFieldName(),"20");
debugCode("Out doLengthValidation LoginSessionInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation LoginSessionInfoControllerBase");
		debugCode("Out doDataTypeValidation LoginSessionInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation LoginSessionInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
						
			String sFieldValue4Uniq = ((LoginSessionInfo) localManagedBean).getSessionId();
			
			Date sFieldValue6Uniq = ((LoginSessionInfo) localManagedBean).getLoginTime();

						handleUniqueValidation(LoginSessionInfoLabelLocal.getsessionIdFieldName(),sFieldValue4Uniq);
			handleUniqueValidation(LoginSessionInfoLabelLocal.getloginTimeFieldName(),sFieldValue6Uniq);
		}	
		debugCode("In doUniqueValidation LoginSessionInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel LoginSessionInfoContollerBase");
		String sLabel = new LoginSessionInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel LoginSessionInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int loginSessionInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("loginSessionInfoId", loginSessionInfoId);
			JsonObject responseData = retrieveLoginSessionInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Login Session Info' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject loginSessionInfoDataObject = responseData.get("loginSessionInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, loginSessionInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Login Session Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveLoginSessionInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer loginSessionInfoId = -1;
		if(requestParams.has("loginSessionInfoId"))
		{
			loginSessionInfoId = requestParams.get("loginSessionInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("loginSessionInfoId", String.valueOf(loginSessionInfoId));
				String loginUserType = "";
		if(requestParams.has("loginUserType"))
		{
			paramsMap.put("loginUserType", requestParams.get("loginUserType").getAsString());
		}
		String selfServiceUserType = "";
		if(requestParams.has("selfServiceUserType"))
		{
			paramsMap.put("selfServiceUserType", requestParams.get("selfServiceUserType").getAsString());
		}
		String sessionId = "";
		if(requestParams.has("sessionId"))
		{
			paramsMap.put("sessionId", requestParams.get("sessionId").getAsString());
		}
		String userId = "";
		if(requestParams.has("userId"))
		{
			paramsMap.put("userId", requestParams.get("userId").getAsString());
		}
		String loginTime = "";
		if(requestParams.has("loginTime"))
		{
			paramsMap.put("loginTime", requestParams.get("loginTime").getAsString());
		}

		JsonObject loginSessionInfoListObject = retrieveLoginSessionInfoList(paramsMap);
		if(loginSessionInfoListObject!=null && loginSessionInfoListObject.has("success") && loginSessionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray loginSessionInfoList = loginSessionInfoListObject.get("loginSessionInfoList").getAsJsonArray();
			if(loginSessionInfoList.size()>0)
			{
				dataObject.add("loginSessionInfoDataObject", loginSessionInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Login Session Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getLoginSessionInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String loginUserType = "";
			if(searchParams.has("loginUserType"))
			{
				paramsMap.put("loginUserType", searchParams.get("loginUserType").getAsString());
			}
			String selfServiceUserType = "";
			if(searchParams.has("selfServiceUserType"))
			{
				paramsMap.put("selfServiceUserType", searchParams.get("selfServiceUserType").getAsString());
			}
			String sessionId = "";
			if(searchParams.has("sessionId"))
			{
				paramsMap.put("sessionId", searchParams.get("sessionId").getAsString());
			}
			String userId = "";
			if(searchParams.has("userId"))
			{
				paramsMap.put("userId", searchParams.get("userId").getAsString());
			}
			String loginTime = "";
			if(searchParams.has("loginTime"))
			{
				paramsMap.put("loginTime", searchParams.get("loginTime").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject loginSessionInfoListObject = retrieveLoginSessionInfoList(paramsMap);
			if(loginSessionInfoListObject!=null && loginSessionInfoListObject.has("success") && loginSessionInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray loginSessionInfoList = loginSessionInfoListObject.get("loginSessionInfoList").getAsJsonArray();
				if(loginSessionInfoList.size()>0)
				{
					dataObject.add("loginSessionInfo", loginSessionInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Login Session Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getLoginSessionInfoInJson(int loginSessionInfoId)
	{
		JsonObject LoginSessionInfoData = null;
		List<Integer> loginSessionInfoIdsList = new ArrayList<>();
		loginSessionInfoIdsList.add(loginSessionInfoId);
		JsonArray loginSessionInfoList = getLoginSessionInfoListInJson(loginSessionInfoIdsList);
		if(loginSessionInfoList!=null && loginSessionInfoList.size()>0)
		{
			LoginSessionInfoData = loginSessionInfoList.get(0).getAsJsonObject();
		}
		return LoginSessionInfoData;
	}
	public JsonArray getLoginSessionInfoListInJson(List<Integer> loginSessionInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray loginSessionInfoObjectsList = new JsonArray();
		JsonObject loginSessionInfoListObject = retrieveLoginSessionInfoList(paramsMap, loginSessionInfoIdsList);
		if(loginSessionInfoListObject!=null && loginSessionInfoListObject.has("success") && loginSessionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray loginSessionInfoList = loginSessionInfoListObject.get("loginSessionInfoList").getAsJsonArray();
			for (int i =0; i< loginSessionInfoList.size(); i++)
			{
				JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
				int loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
				
				loginSessionInfoObjectsList.add(loginSessionInfoDataObject);
			}
		}
		return loginSessionInfoObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray loginSessionInfoList)
	{
		String errorMessage = "loginSessionInfoList: \n";
		for (int i =0; i< loginSessionInfoList.size(); i++)
		{
			JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
			JsonObject loginSessionInfo = loginSessionInfoDataObject.get("dataObject").getAsJsonObject();if(!loginSessionInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "loginSessionInfo could not be uploaded verify data \n"; 
			}
			else if(loginSessionInfoDataObject.has("isSuccessfullyUploaded") 
					&& loginSessionInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += loginSessionInfoDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("LoginSessionInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND loginSessionInfoId = :loginSessionInfoId ";
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
		else if("LoginSessionInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("loginSessionInfoId", userId);
		}
		
	}
	public String getParentFilterClauseForLoginSessionInfo(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";return parentFilterClause;
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
	public void setParentFilterClauseParamsForLoginSessionInfo(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveLoginSessionInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> loginSessionInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("loginSessionInfoId"))
		{
			int loginSessionInfoId = Integer.parseInt(paramsMap.get("loginSessionInfoId"));
			if(loginSessionInfoId>0)
			{
				loginSessionInfoIdsList.add(loginSessionInfoId);
			}
		}
		return retrieveLoginSessionInfoList(paramsMap, loginSessionInfoIdsList);
	}
	public JsonObject retrieveLoginSessionInfoList(java.util.Map<String, String> paramsMap, List<Integer> loginSessionInfoIdsList)
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
						String loginUserType = paramsMap.get("loginUserType");
			if(loginUserType==null)
			{
				loginUserType = "";
			}
			String selfServiceUserType = paramsMap.get("selfServiceUserType");
			if(selfServiceUserType==null)
			{
				selfServiceUserType = "";
			}
			String sessionId = paramsMap.get("sessionId");
			if(sessionId==null)
			{
				sessionId = "";
			}
			String userId = paramsMap.get("userId");
			if(userId==null)
			{
				userId = "";
			}
			String loginTime = paramsMap.get("loginTime");
			if(loginTime==null)
			{
				loginTime = "";
			}
String hql = "FROM LoginSessionInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String loginSessionInfoIdFilterClass = "";
			if (loginSessionInfoIdsList != null && loginSessionInfoIdsList.size() > 0)
			{
				loginSessionInfoIdFilterClass = " AND loginSessionInfoId in (:idsList) ";
			}
						String loginUserTypeFilterClass = "";
			if (loginUserType.length() > 0)
			{
				
				
				
				
				
				
				
				loginUserTypeFilterClass = " AND loginUserType LIKE :loginUserType ";
				
				
				
				
			}
			String selfServiceUserTypeFilterClass = "";
			if (selfServiceUserType.length() > 0)
			{
				
				
				
				
				
				
				
				selfServiceUserTypeFilterClass = " AND selfServiceUserType LIKE :selfServiceUserType ";
				
				
				
				
			}
			String sessionIdFilterClass = "";
			if (sessionId.length() > 0)
			{
				
				
				
				
				
				
				
				sessionIdFilterClass = " AND sessionId LIKE :sessionId ";
				
				
				
				
			}
			String userIdFilterClass = "";
			if (userId.length() > 0)
			{
				
				
				
				
				
				
				
				
				
				
				userIdFilterClass = " AND userId = :userId ";
				
			}
			String loginTimeFilterClass = "";
			if (loginTime.length() > 0)
			{
				
				
				loginTimeFilterClass = " AND loginTime = :loginTime ";
				
				
				
				
				
				
				
				
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForLoginSessionInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					loginSessionInfoIdFilterClass +
										loginUserTypeFilterClass +
					selfServiceUserTypeFilterClass +
					sessionIdFilterClass +
					userIdFilterClass +
					loginTimeFilterClass +

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
			if (loginSessionInfoIdsList != null && loginSessionInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", loginSessionInfoIdsList);
			}
						if (loginUserType.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("loginUserType", loginUserType);
				
				
				
				
			}
			if (selfServiceUserType.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("selfServiceUserType", selfServiceUserType);
				
				
				
				
			}
			if (sessionId.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("sessionId", sessionId);
				
				
				
				
			}
			if (userId.length() > 0)
			{
				
				
				
				
				
				
				
				
				
				
				query.setParameter("userId", Integer.parseInt(userId));
				
			}
			if (loginTime.length() > 0)
			{
				
				
				query.setParameter("loginTime", CommonUtil.getDBFormattedDateTimeStamp(loginTime));
				
				
				
				
				
				
				
				
				
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
	    	setParentFilterClauseParamsForLoginSessionInfo(paramsMap, query);
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
			JsonArray loginSessionInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				LoginSessionInfo loginSessionInfo = (LoginSessionInfo) it.next();
				JsonObject loginSessionInfoDataObject = loginSessionInfo.getDataObject(getDBSession());
				loginSessionInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(loginSessionInfo.getVwTxnStatus()));
				loginSessionInfoList.add(loginSessionInfoDataObject);
				doAfterSearchResultRowAddedImpl(loginSessionInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from LoginSessionInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (loginSessionInfoIdsList != null && loginSessionInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", loginSessionInfoIdsList);
				}
								if (loginUserType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("loginUserType", loginUserType);
					
					
					
					
				}
				if (selfServiceUserType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("selfServiceUserType", selfServiceUserType);
					
					
					
					
				}
				if (sessionId.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("sessionId", sessionId);
					
					
					
					
				}
				if (userId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("userId", Integer.parseInt(userId));
					
				}
				if (loginTime.length() > 0)
				{
					
					
					countQuery.setParameter("loginTime", CommonUtil.getDBFormattedDateTimeStamp(loginTime));
					
					
					
					
					
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForLoginSessionInfo(paramsMap, countQuery);
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
			dataObject.add("loginSessionInfoList",   loginSessionInfoList);
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
				+ "   from LoginSessionInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from LoginSessionInfo E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentLoginSessionInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveLoginSessionInfoList(paramsMap);
	}
	public LoginSessionInfo getLoginSessionInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from LoginSessionInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			LoginSessionInfo loginSessionInfo = (LoginSessionInfo) it.next();
			return loginSessionInfo;
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
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo)getManagedBean();
		JsonObject loginSessionInfoDataObject = loginSessionInfo.getDataObject(getDBSession());copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject);
		setManagedBean(loginSessionInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData LoginSessionInfoContollerBase");
			LoginSessionInfo currentBean = (LoginSessionInfo)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "LoginUserType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setLoginUserType(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "SelfServiceUserType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSelfServiceUserType(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "SessionId".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSessionId(sStringTestData);currentBean.setUserId(1);
		
		currentBean.setLoginTime(new Date());

		setManagedBean(currentBean);
		debugCode("Out setTestData LoginSessionInfoContollerBase");
	}
	// end Test Data
	public void copyLoginSessionInfoFromJson(LoginSessionInfo loginSessionInfo, JsonObject loginSessionInfoDataObject)
	{
		copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject, false);
	}
	public void copyLoginSessionInfoFromJson(LoginSessionInfo loginSessionInfo, JsonObject loginSessionInfoDataObject, boolean excludePasswordFields)
	{	
				
		if(loginSessionInfoDataObject.has("loginUserType"))
		{
			String loginUserType = loginSessionInfoDataObject.get("loginUserType").getAsString();
			loginSessionInfo.setLoginUserType(loginUserType);
		}if(loginSessionInfoDataObject.has("selfServiceUserType"))
		{
			String selfServiceUserType = loginSessionInfoDataObject.get("selfServiceUserType").getAsString();
			loginSessionInfo.setSelfServiceUserType(selfServiceUserType);
		}if(loginSessionInfoDataObject.has("sessionId"))
		{
			String sessionId = loginSessionInfoDataObject.get("sessionId").getAsString();
			loginSessionInfo.setSessionId(sessionId);
		}if(loginSessionInfoDataObject.has("userId"))
		{
			Integer userId = null;
			try
			{
			 	userId = loginSessionInfoDataObject.get("userId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(userId != null)
			{
				loginSessionInfo.setUserId(userId);
			}
		}
		
		if(loginSessionInfoDataObject.has("loginTime"))
		{
			String loginTime = loginSessionInfoDataObject.get("loginTime").getAsString();
			if(loginTime.length() > 0)
			{
				try
				{
					loginSessionInfo.setLoginTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(loginTime));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid LoginTime");
				}
			}
		}
		
	}
		
	public JsonObject createLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		return createLoginSessionInfo(loginSessionInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject loginSessionInfoDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createLoginSessionInfo(JsonObject loginSessionInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
		setLoginBasedValues(paramsInfoObj, loginSessionInfoDataObject);
		Session session = getDBSession();				
		copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject);
		if(loginSessionInfoDataObject.has("legacyRecordId"))
		{
			loginSessionInfo.setLegacyRecordId(loginSessionInfoDataObject.get("legacyRecordId").getAsInt());
		}
				loginSessionInfo.setVwCreatedBy(createdBy);
		loginSessionInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(loginSessionInfo);
		setManagedBean("LoginSessionInfoBean", loginSessionInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		loginSessionInfo = (LoginSessionInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("loginSessionInfoId", loginSessionInfo.getLoginSessionInfoId());
			JsonObject loginSessionInfoObj = loginSessionInfo.getDataObject(getDBSession());
			loginSessionInfoObj.addProperty("nextAction", getActionForCurrentStatus(loginSessionInfo.getVwTxnStatus()));
			dataObject.add("loginSessionInfoDataObject", loginSessionInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("loginSessionInfoId", loginSessionInfoId);
		JsonObject responseData = retrieveLoginSessionInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Login Session Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
		if(loginSessionInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(loginSessionInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromLoginSessionInfo(loginSessionInfoDataObject);
		boolean excludePasswordFields = true;
		copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject, excludePasswordFields);setPrivateManagedBean(loginSessionInfo);
		setManagedBean("LoginSessionInfoBean", loginSessionInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		loginSessionInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("loginSessionInfoId", loginSessionInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
	}
	public JsonObject deleteLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("loginSessionInfoId", loginSessionInfoId);
		JsonObject responseData = retrieveLoginSessionInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Login Session Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);setPrivateManagedBean(loginSessionInfo);
		setManagedBean("LoginSessionInfo", loginSessionInfo);
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
	public JsonObject fetchLoginSessionInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfo", loginSessionInfo);
			doAfterLoginSessionInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("loginSessionInfo", loginSessionInfo.getDataObject(getDBSession()));
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
	public JsonObject fetchLoginSessionInfoTestData(int obj, JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject);
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfo", loginSessionInfo);
			doAfterLoginSessionInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("loginSessionInfo", loginSessionInfo.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject);	String attributeName = loginSessionInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = loginSessionInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfo", loginSessionInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("loginSessionInfo", loginSessionInfo.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		LoginSessionInfo loginSessionInfo = new LoginSessionInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyLoginSessionInfoFromJson(loginSessionInfo, loginSessionInfoDataObject);	
			String attributeName = loginSessionInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfo", loginSessionInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("loginSessionInfo", loginSessionInfo.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
			String customEventName = loginSessionInfoDataObject.get("customEventName").getAsString();
			LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfoBean", loginSessionInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("loginSessionInfo", loginSessionInfo.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnLoginSessionInfo(JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer loginSessionInfoId = loginSessionInfoDataObject.get("loginSessionInfoId").getAsInt();
			String currentStatus = loginSessionInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(loginSessionInfoDataObject.has("currentAction"))
			{
				currentAction = loginSessionInfoDataObject.get("currentAction").getAsString();
			}
			LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
			setPrivateManagedBean(loginSessionInfo);
			setManagedBean("LoginSessionInfoBean", loginSessionInfo);
			if(loginSessionInfo.getIsRequestUnderProcesss() == 1)
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
				executeAction(loginSessionInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(loginSessionInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(loginSessionInfo, "LoginSessionInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedloginSessionInfoDataObject = loginSessionInfo.getDataObject(getDBSession());
				updatedloginSessionInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(loginSessionInfo.getVwTxnStatus()));
				dataObject.add("loginSessionInfo", updatedloginSessionInfoDataObject);
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
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo) getManagedBean();
		String currentStatus = loginSessionInfo.getVwTxnStatus();
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
	
	
	public JsonObject downloadLoginSessionInfoData() throws Exception
	{
		return downloadLoginSessionInfoData(null);
	}
	public JsonObject downloadLoginSessionInfoData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "LoginSessionInfo");
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
			headerName = "loginSessionInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "loginUserType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "selfServiceUserType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "sessionId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "userId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "loginTime";
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
			String hql = "From LoginSessionInfo ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				LoginSessionInfo loginSessionInfo = (LoginSessionInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer loginSessionInfoId = loginSessionInfo.getLoginSessionInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(loginSessionInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String loginUserType = loginSessionInfo.getLoginUserType();
				cell.setCellValue(loginUserType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String selfServiceUserType = loginSessionInfo.getSelfServiceUserType();
				cell.setCellValue(selfServiceUserType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String sessionId = loginSessionInfo.getSessionId();
				cell.setCellValue(sessionId);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				
				
				
				
				
				
				
				Integer userId = loginSessionInfo.getUserId();
				if(userId!=null)
				{
					cell.setCellValue(String.valueOf(userId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				
				
				
				Date loginTime = loginSessionInfo.getLoginTime();
				if(loginTime!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateTimeStampFormat(loginTime));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "LoginSessionInfo.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "LoginSessionInfo.xls";
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
	public JsonObject uploadLoginSessionInfoData(JsonObject loginSessionInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = loginSessionInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = loginSessionInfoDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Login Session Info Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		loginSessionInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadLoginSessionInfoData(workbook, loginSessionInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Login Session Info Data uploaded successfully.");
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
	public JsonObject uploadLoginSessionInfoData(HSSFWorkbook workbook, JsonObject loginSessionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("LoginSessionInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = loginSessionInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = loginSessionInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = loginSessionInfoDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Login Session Info Data uploaded successfully.");
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
			JsonObject loginSessionInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject loginSessionInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray loginSessionInfoList = loginSessionInfoListObj.get("loginSessionInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadLoginSessionInfoList(loginSessionInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(loginSessionInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(loginSessionInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray loginSessionInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject loginSessionInfo = new JsonObject();
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
				JsonObject loginSessionInfoUploadObj	= new JsonObject();
				loginSessionInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					loginSessionInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("loginSessionInfoId"))
						{
							String loginSessionInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(loginSessionInfoId != null && loginSessionInfoId.trim().length() > 0)
							{
								try
								{
									Integer iLoginSessionInfoId = Integer.parseInt(loginSessionInfoId);
									if(areSourceDestinationSame == 1)
									{
										LoginSessionInfo loginSessionInfoObj = (LoginSessionInfo)session.get(LoginSessionInfo.class, iLoginSessionInfoId);
										if(loginSessionInfoObj != null)
										{ 
											loginSessionInfo.addProperty("loginSessionInfoId", iLoginSessionInfoId);
										}
										else
										{
											loginSessionInfoUploadObj.addProperty("isDataFetched", 0);
											loginSessionInfoUploadObj.addProperty("msg", "This Login Session Info could not be uploaded as there appears to be some problem with the data(LoginSessionInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										LoginSessionInfo loginSessionInfoObj = getLoginSessionInfoByLegacyRecordId(session, iLoginSessionInfoId);
										if(loginSessionInfoObj != null)
										{ 
											loginSessionInfo.addProperty("loginSessionInfoId", loginSessionInfoObj.getLoginSessionInfoId());
										}
										loginSessionInfo.addProperty("legacyRecordId", iLoginSessionInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									loginSessionInfoUploadObj.addProperty("isDataFetched", 0);
									loginSessionInfoUploadObj.addProperty("msg", "This Login Session Info could not be uploaded as there appears to be some problem with the data(LoginSessionInfo Id). ");
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
							loginSessionInfo.addProperty(parameterName, parameterValue);
						}
					}
					loginSessionInfoUploadObj.add("dataObject", loginSessionInfo);		    
					loginSessionInfoList.add(loginSessionInfoUploadObj);
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
			dataObject.add("loginSessionInfoList", loginSessionInfoList);
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
		if(previousRetryCountMap.has("LoginSessionInfo") && previousRetryCountMap.get("LoginSessionInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("LoginSessionInfo").getAsInt();
		}
		if(retryCountMap.has("LoginSessionInfo") && retryCountMap.get("LoginSessionInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("LoginSessionInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForLoginSessionInfoList(JsonArray loginSessionInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < loginSessionInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
			JsonObject loginSessionInfo = loginSessionInfoDataObject.get("dataObject").getAsJsonObject();
			if(loginSessionInfoDataObject.has("retryUpload") && loginSessionInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = loginSessionInfoDataObject.get("retryUpload").getAsInt();
			}
			if(loginSessionInfoDataObject.has("retryChildEntitiesUpload") && loginSessionInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = loginSessionInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(loginSessionInfoDataObject.has("partialUploadUnderProcess") && loginSessionInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = loginSessionInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("LoginSessionInfo", retryCount);
	}
	public JsonObject uploadLoginSessionInfoList(JsonArray loginSessionInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(loginSessionInfoList);
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
	public JsonObject updateStatusMsg(JsonArray loginSessionInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < loginSessionInfoList.size(); i++)
			{
				JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
				JsonObject loginSessionInfo = loginSessionInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = loginSessionInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = loginSessionInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = loginSessionInfoDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray loginSessionInfoList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < loginSessionInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject loginSessionInfoDataObject = loginSessionInfoList.get(i).getAsJsonObject();
				JsonObject loginSessionInfo = loginSessionInfoDataObject.get("dataObject").getAsJsonObject();
				loginSessionInfoDataObject.addProperty("retryUpload", 0);
				loginSessionInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				loginSessionInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl loginSessionInfoImplObj = new com.patientapp.controller.forms.impl.LoginSessionInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = loginSessionInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, loginSessionInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					loginSessionInfoDataObject.addProperty("retryUpload", 1);
					loginSessionInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					loginSessionInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					loginSessionInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int loginSessionInfoId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !loginSessionInfo.has("loginSessionInfoId"))
				{
					loginSessionInfo.addProperty("attributeNamePrefix", "");
					
					loginSessionInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = loginSessionInfoImplObj.getLoginSessionInfoByLookupFields(session,  loginSessionInfo);
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
						JsonObject loginSessionInfoObject = responseData.get("loginSessionInfoDataObject").getAsJsonObject();
						loginSessionInfoId = loginSessionInfoObject.get("loginSessionInfoId").getAsInt();
						loginSessionInfo.addProperty("loginSessionInfoId", loginSessionInfoId);
						updateEntity = true;
					}
				}
				else if(loginSessionInfo.has("loginSessionInfoId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = loginSessionInfoImplObj.createLoginSessionInfo(loginSessionInfo);
				}
				else
				{
					responseData = loginSessionInfoImplObj.updateLoginSessionInfo(loginSessionInfo);
				}
				loginSessionInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					loginSessionInfoId =-1;
					loginSessionInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					loginSessionInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				loginSessionInfoId = responseData.get("loginSessionInfoId").getAsInt();
				loginSessionInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject loginSessionInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getLoginSessionInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From LoginSessionInfo where 1 = 1  ";
			String countHql = "select count(*)  from LoginSessionInfo where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Login Session Info could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				LoginSessionInfo loginSessionInfo = (LoginSessionInfo) it.next();
				JsonObject loginSessionInfoDataObject = loginSessionInfo.getDataObject(session);
				dataObject.add("loginSessionInfoDataObject", loginSessionInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Login Session Info could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Login Session Info' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{return 0;
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
		else if(apiName.equals("getLoginSessionInfoPropertyValue"))
			{
				JsonObject inputDataObject = getLoginSessionInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getLoginSessionInfo"))
			{
				JsonObject inputDataObject = getLoginSessionInfo(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForLoginSessionInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForLoginSessionInfo"))
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
			Integer loginSessionInfoId = requestReceivedParametersInfo.get("loginSessionInfoId").getAsInt();
			LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
			loginSessionInfo.setIsRequestUnderProcesss(0);
			session.merge(loginSessionInfo);
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
	public JsonObject getLoginSessionInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer loginSessionInfoId = inputForGetAPI.get("loginSessionInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		LoginSessionInfo loginSessionInfo = (LoginSessionInfo) session.get(LoginSessionInfo.class, loginSessionInfoId);
		loginSessionInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getLoginSessionInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer loginSessionInfoId = inputForGetAPI.get("loginSessionInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("loginSessionInfoId", String.valueOf(loginSessionInfoId));
		JsonObject loginSessionInfoListObject = retrieveLoginSessionInfoList(paramsMap);
		if(loginSessionInfoListObject!=null && loginSessionInfoListObject.has("success") && loginSessionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray loginSessionInfoList = loginSessionInfoListObject.get("loginSessionInfoList").getAsJsonArray();
			if(loginSessionInfoList.size()>0)
			{
				dataObject.add("loginSessionInfo", loginSessionInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Login Session Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject loginSessionInfoDataObject, LoginSessionInfo loginSessionInfo);
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
	public abstract void doAfterLookupRowSelectedImpl(LoginSessionInfo loginSessionInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(LoginSessionInfo loginSessionInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(LoginSessionInfo loginSessionInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
