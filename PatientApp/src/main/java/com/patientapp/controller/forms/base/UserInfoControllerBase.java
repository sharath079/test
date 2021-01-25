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
import com.patientapp.bean.EmployeeRoles;
import com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl;

import com.patientapp.bean.UserInfo;
import com.patientapp.controller.forms.base.UserInfoLabel;
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
public abstract class UserInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'UserFirstName' 
	 *		 * 'UserLastName' 
	 *		 * 'OrganisationsUserOrg' 
	 *		 * 'LoginId' 
	 *		 * 'LoginEmailId' 
	 *		 * 'ContactNo' 
	 *		 * 'LoginPassword' 
	 *		 * 'ResetToken' 
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
	protected UserInfoLabel UserInfoLabelLocal = new UserInfoLabel();
	protected UserInfo UserInfoLocal = new UserInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public UserInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public UserInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public UserInfoControllerBase()
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
		return "UserInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((UserInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((UserInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((UserInfo)obj).getUserInfoId();
	}
	public String getManagedBeanName()
    {
		return "UserInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "UserInfoLabelBean";
    }
	protected Class<UserInfo> setBeanClass()
	{
		return UserInfo.class;
	}
	protected Class<UserInfoLabel> setBeanLabelClass()
	{
		return UserInfoLabel.class;
	}
	protected UserInfo getLocalManagedBean()
    {
		return (UserInfo)getManagedBean();
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
		/*UserInfo userInfo = (UserInfo)getManagedBean();
		String areChangesEffected = userInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			userInfo.setAreChangesEffected("YES");			
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
		/*UserInfo userInfo = (UserInfo)getManagedBean();
		String areChangesEffected = userInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			userInfo.setAreChangesEffected("NO");			
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
		/*UserInfo userInfo = (UserInfo)getManagedBean();
		String areChangesEffected = userInfo.getAreChangesEffected();
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
		UserInfo userInfo = (UserInfo)getManagedBean();
		String sCurrentStatus = userInfo.getVwTxnStatus();
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
		UserInfo userInfo = (UserInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(userInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterUserInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		UserInfo userInfo = (UserInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("userFirstName") && initParamsInfo.get("userFirstName").isJsonNull()==false)
		{
			String userFirstName = initParamsInfo.get("userFirstName").getAsString();
			userInfo.setUserFirstName(userFirstName);			
		}if(initParamsInfo.has("userLastName") && initParamsInfo.get("userLastName").isJsonNull()==false)
		{
			String userLastName = initParamsInfo.get("userLastName").getAsString();
			userInfo.setUserLastName(userLastName);			
		}
		if(initParamsInfo.has("organisationsUserOrg") && initParamsInfo.get("organisationsUserOrg").isJsonNull()==false)
		{
			Integer organisationsUserOrgId = initParamsInfo.get("organisationsUserOrgId").getAsInt();
			userInfo.setOrganisationsUserOrgId(organisationsUserOrgId);
		}if(initParamsInfo.has("loginId") && initParamsInfo.get("loginId").isJsonNull()==false)
		{
			String loginId = initParamsInfo.get("loginId").getAsString();
			userInfo.setLoginId(loginId);			
		}if(initParamsInfo.has("loginEmailId") && initParamsInfo.get("loginEmailId").isJsonNull()==false)
		{
			String loginEmailId = initParamsInfo.get("loginEmailId").getAsString();
			userInfo.setLoginEmailId(loginEmailId);			
		}if(initParamsInfo.has("contactNo") && initParamsInfo.get("contactNo").isJsonNull()==false)
		{
			String contactNo = initParamsInfo.get("contactNo").getAsString();
			userInfo.setContactNo(contactNo);			
		}if(initParamsInfo.has("loginPassword") && initParamsInfo.get("loginPassword").isJsonNull()==false)
		{
			String loginPassword = initParamsInfo.get("loginPassword").getAsString();
			userInfo.setLoginPassword(loginPassword);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(userInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		UserInfo userInfo = (UserInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		UserInfo userInfo = (UserInfo)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}
				else if("organisationsUserOrg".equalsIgnoreCase(attributeName))
		{
			  
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(userInfo, attributeName);
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
			UserInfo userInfo = (UserInfo)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*			Set<Object> employeeRolesList = userInfo.getEmployeeRoleses();
			Iterator<Object> employeeRolesListIterator = employeeRolesList.iterator();
			while(employeeRolesListIterator.hasNext())
			{
				EmployeeRoles employeeRoles = (EmployeeRoles)employeeRolesListIterator.next();
				EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(getDBSession());
				employeeRolesControllerImpl.setPrivateManagedBean(employeeRoles);
				employeeRolesControllerImpl.deleteWithoutCommit(false);
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "UserInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() UserInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (UserInfoSearch)getManagedBean("UserInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getUserFirstName()))
			{
				searchParams.put(UserInfoLabelLocal.getuserFirstNameFieldName(),searchBeanLocal.getUserFirstName());
			}	
			if (isExists(searchBeanLocal.getUserLastName()))
			{
				searchParams.put(UserInfoLabelLocal.getuserLastNameFieldName(),searchBeanLocal.getUserLastName());
			}	
			if (isExists(searchBeanLocal.getOrganisationsUserOrg()))
			{
				searchParams.put(UserInfoLabelLocal.getorganisationsUserOrgFieldName(),searchBeanLocal.getOrganisationsUserOrg());
			}	
			if (isExists(searchBeanLocal.getLoginId()))
			{
				searchParams.put(UserInfoLabelLocal.getloginIdFieldName(),searchBeanLocal.getLoginId());
			}	
			if (isExists(searchBeanLocal.getLoginEmailId()))
			{
				searchParams.put(UserInfoLabelLocal.getloginEmailIdFieldName(),searchBeanLocal.getLoginEmailId());
			}	
			if (isExists(searchBeanLocal.getContactNo()))
			{
				searchParams.put(UserInfoLabelLocal.getcontactNoFieldName(),searchBeanLocal.getContactNo());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(UserInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() UserInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues UserInfoContollerBase");
		targetBean = (UserInfo)targetBean;((UserInfo)targetBean).setUserInfoId(((UserInfo)sourceBean).getUserInfoId());
				((UserInfo)targetBean).setUserFirstName(((UserInfo)sourceBean).getUserFirstName());
		((UserInfo)targetBean).setUserLastName(((UserInfo)sourceBean).getUserLastName());
		((UserInfo)targetBean).setLoginId(((UserInfo)sourceBean).getLoginId());
		((UserInfo)targetBean).setLoginEmailId(((UserInfo)sourceBean).getLoginEmailId());
		((UserInfo)targetBean).setContactNo(((UserInfo)sourceBean).getContactNo());
		((UserInfo)targetBean).setLoginPassword(((UserInfo)sourceBean).getLoginPassword());
		((UserInfo)targetBean).setResetToken(((UserInfo)sourceBean).getResetToken());

				((UserInfo)targetBean).setOrganisationsUserOrgId(((UserInfo)sourceBean).getOrganisationsUserOrgId());

				/*if (bSetAsManagedBean)
		{			
			OrganisationsUserOrg OrganisationsUserOrgLocal = (OrganisationsUserOrg)(( UserInfo)sourceBean).getOrganisationsUserOrg();
			setManagedBean("OrganisationsUserOrgBean", OrganisationsUserOrgLocal);
		}*/

		doAfterSetValues();
		debugCode("Out setValues UserInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.UserInfo entityBean = (UserInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("userInfoId")) 
	 	{
			return entityBean.getUserInfoId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("UserFirstName")) 
	 	{
			return entityBean.getUserFirstName();
		}
	 	if (sFieldName.equalsIgnoreCase("UserLastName")) 
	 	{
			return entityBean.getUserLastName();
		}
	 	if (sFieldName.equalsIgnoreCase("LoginId")) 
	 	{
			return entityBean.getLoginId();
		}
	 	if (sFieldName.equalsIgnoreCase("LoginEmailId")) 
	 	{
			return entityBean.getLoginEmailId();
		}
	 	if (sFieldName.equalsIgnoreCase("ContactNo")) 
	 	{
			return entityBean.getContactNo();
		}
	 	if (sFieldName.equalsIgnoreCase("LoginPassword")) 
	 	{
			return entityBean.getLoginPassword();
		}
	 	if (sFieldName.equalsIgnoreCase("ResetToken")) 
	 	{
			return entityBean.getResetToken();
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
		debugCode("In doEnrichSystemValues(String sAction) UserInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((UserInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((UserInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((UserInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((UserInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((UserInfo) localManagedBean).setVwTxnStatus("CREATED");
			((UserInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((UserInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((UserInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) UserInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) UserInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) UserInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations UserInfoControllerBase");
		//NG: Important comment
		//managedBean = (UserInfo) getManagedBean();
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
		debugCode("Out doValidations UserInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation UserInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation UserInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation UserInfoControllerBase");debugCode("Out doAllowedValuesValidation UserInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation UserInfoControllerBase");
				
		String sFieldValue2 = ((UserInfo) localManagedBean).getUserFirstName();String sFieldValue3 = ((UserInfo) localManagedBean).getUserLastName();String sFieldValue5 = ((UserInfo) localManagedBean).getLoginId();String sFieldValue6 = ((UserInfo) localManagedBean).getLoginEmailId();String sFieldValue8 = ((UserInfo) localManagedBean).getLoginPassword();
		if (!isExists(sFieldValue2)) addMandatoryResponse(UserInfoLabelLocal.getuserFirstNameFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(UserInfoLabelLocal.getuserLastNameFieldName());
		if (!isExists(sFieldValue5)) addMandatoryResponse(UserInfoLabelLocal.getloginIdFieldName());
		if (!isExists(sFieldValue6)) addMandatoryResponse(UserInfoLabelLocal.getloginEmailIdFieldName());
		if (!isExists(sFieldValue8)) addMandatoryResponse(UserInfoLabelLocal.getloginPasswordFieldName());
debugCode("Out doMandatoryValidation UserInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation UserInfoControllerBase");
				
		String sFieldValue2 = ((UserInfo) localManagedBean).getUserFirstName();String sFieldValue3 = ((UserInfo) localManagedBean).getUserLastName();String sFieldValue5 = ((UserInfo) localManagedBean).getLoginId();String sFieldValue6 = ((UserInfo) localManagedBean).getLoginEmailId();String sFieldValue7 = ((UserInfo) localManagedBean).getContactNo();String sFieldValue8 = ((UserInfo) localManagedBean).getLoginPassword();String sFieldValue9 = ((UserInfo) localManagedBean).getResetToken();

				Integer iFieldValue4 = ((UserInfo) localManagedBean).getOrganisationsUserOrgId();

				if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(UserInfoLabelLocal.getuserFirstNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(UserInfoLabelLocal.getuserLastNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue5,"50")) addMaxLengthResponse(UserInfoLabelLocal.getloginIdFieldName(),"50");
		if (!isLengthAllowed(sFieldValue6,"100")) addMaxLengthResponse(UserInfoLabelLocal.getloginEmailIdFieldName(),"100");
		if (!isLengthAllowed(sFieldValue7,"20")) addMaxLengthResponse(UserInfoLabelLocal.getcontactNoFieldName(),"20");
		if (!isLengthAllowed(sFieldValue8,"100")) addMaxLengthResponse(UserInfoLabelLocal.getloginPasswordFieldName(),"100");
		if (!isLengthAllowed(sFieldValue9,"10")) addMaxLengthResponse(UserInfoLabelLocal.getresetTokenFieldName(),"10");

				//if (!isLengthAllowed(iFieldValue4,"")) addMaxLengthResponse(UserInfoLabelLocal.getorganisationsUserOrgFieldName(),"");

		debugCode("Out doLengthValidation UserInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation UserInfoControllerBase");
		debugCode("Out doDataTypeValidation UserInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation UserInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
						
			String sFieldValue6Uniq = ((UserInfo) localManagedBean).getLoginEmailId();

						handleUniqueValidation(UserInfoLabelLocal.getloginEmailIdFieldName(),sFieldValue6Uniq);
		}	
		debugCode("In doUniqueValidation UserInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel UserInfoContollerBase");
		String sLabel = new UserInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel UserInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int userInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("userInfoId", userInfoId);
			JsonObject responseData = retrieveUserInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'User Info' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject userInfoDataObject = responseData.get("userInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, userInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'User Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveUserInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer userInfoId = -1;
		if(requestParams.has("userInfoId"))
		{
			userInfoId = requestParams.get("userInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("userInfoId", String.valueOf(userInfoId));
				String userFirstName = "";
		if(requestParams.has("userFirstName"))
		{
			paramsMap.put("userFirstName", requestParams.get("userFirstName").getAsString());
		}
		String userLastName = "";
		if(requestParams.has("userLastName"))
		{
			paramsMap.put("userLastName", requestParams.get("userLastName").getAsString());
		}
		String loginId = "";
		if(requestParams.has("loginId"))
		{
			paramsMap.put("loginId", requestParams.get("loginId").getAsString());
		}
		String loginEmailId = "";
		if(requestParams.has("loginEmailId"))
		{
			paramsMap.put("loginEmailId", requestParams.get("loginEmailId").getAsString());
		}
		String contactNo = "";
		if(requestParams.has("contactNo"))
		{
			paramsMap.put("contactNo", requestParams.get("contactNo").getAsString());
		}
		String loginPassword = "";
		if(requestParams.has("loginPassword"))
		{
			paramsMap.put("loginPassword", requestParams.get("loginPassword").getAsString());
		}
		String resetToken = "";
		if(requestParams.has("resetToken"))
		{
			paramsMap.put("resetToken", requestParams.get("resetToken").getAsString());
		}

				Integer organisationsUserOrgId = -1;
		if(requestParams.has("organisationsUserOrgId"))
		{
			paramsMap.put("organisationsUserOrgId", requestParams.get("organisationsUserOrgId").getAsString());
		}
JsonObject userInfoListObject = retrieveUserInfoList(paramsMap);
		if(userInfoListObject!=null && userInfoListObject.has("success") && userInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray userInfoList = userInfoListObject.get("userInfoList").getAsJsonArray();
			if(userInfoList.size()>0)
			{
				dataObject.add("userInfoDataObject", userInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'User Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getUserInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String userFirstName = "";
			if(searchParams.has("userFirstName"))
			{
				paramsMap.put("userFirstName", searchParams.get("userFirstName").getAsString());
			}
			String userLastName = "";
			if(searchParams.has("userLastName"))
			{
				paramsMap.put("userLastName", searchParams.get("userLastName").getAsString());
			}
			String loginId = "";
			if(searchParams.has("loginId"))
			{
				paramsMap.put("loginId", searchParams.get("loginId").getAsString());
			}
			String loginEmailId = "";
			if(searchParams.has("loginEmailId"))
			{
				paramsMap.put("loginEmailId", searchParams.get("loginEmailId").getAsString());
			}
			String contactNo = "";
			if(searchParams.has("contactNo"))
			{
				paramsMap.put("contactNo", searchParams.get("contactNo").getAsString());
			}
			String loginPassword = "";
			if(searchParams.has("loginPassword"))
			{
				paramsMap.put("loginPassword", searchParams.get("loginPassword").getAsString());
			}
			String resetToken = "";
			if(searchParams.has("resetToken"))
			{
				paramsMap.put("resetToken", searchParams.get("resetToken").getAsString());
			}

						Integer organisationsUserOrgId = -1;
			if(searchParams.has("organisationsUserOrgId"))
			{
				paramsMap.put("organisationsUserOrgId", searchParams.get("organisationsUserOrgId").getAsString());
			}
paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject userInfoListObject = retrieveUserInfoList(paramsMap);
			if(userInfoListObject!=null && userInfoListObject.has("success") && userInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray userInfoList = userInfoListObject.get("userInfoList").getAsJsonArray();
				if(userInfoList.size()>0)
				{
					dataObject.add("userInfo", userInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'User Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getUserInfoInJson(int userInfoId)
	{
		JsonObject UserInfoData = null;
		List<Integer> userInfoIdsList = new ArrayList<>();
		userInfoIdsList.add(userInfoId);
		JsonArray userInfoList = getUserInfoListInJson(userInfoIdsList);
		if(userInfoList!=null && userInfoList.size()>0)
		{
			UserInfoData = userInfoList.get(0).getAsJsonObject();
		}
		return UserInfoData;
	}
	public JsonArray getUserInfoListInJson(List<Integer> userInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray userInfoObjectsList = new JsonArray();
		JsonObject userInfoListObject = retrieveUserInfoList(paramsMap, userInfoIdsList);
		if(userInfoListObject!=null && userInfoListObject.has("success") && userInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray userInfoList = userInfoListObject.get("userInfoList").getAsJsonArray();
			for (int i =0; i< userInfoList.size(); i++)
			{
				JsonObject userInfoDataObject = userInfoList.get(i).getAsJsonObject();
				int userInfoId = userInfoDataObject.get("userInfoId").getAsInt();
								com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray employeeRolesList = employeeRolesImplObj.getEmployeeRolesListFromParentInJson(userInfoId);
				userInfoDataObject.add("employeeRolesList", employeeRolesList);

				userInfoObjectsList.add(userInfoDataObject);
			}
		}
		return userInfoObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray userInfoList)
	{
		String errorMessage = "userInfoList: \n";
		for (int i =0; i< userInfoList.size(); i++)
		{
			JsonObject userInfoDataObject = userInfoList.get(i).getAsJsonObject();
			JsonObject userInfo = userInfoDataObject.get("dataObject").getAsJsonObject();
						
			errorMessage += "First Name : "+ userInfo.get("userFirstName").getAsString();

			if(!userInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "userInfo could not be uploaded verify data \n"; 
			}
			else if(userInfoDataObject.has("isSuccessfullyUploaded") 
					&& userInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += userInfoDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    		    if(userInfo.has("employeeRolesList"))
		    {
			    JsonArray employeeRolesList = userInfo.get("employeeRolesList").getAsJsonArray();
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);		    
				errorMessage += employeeRolesImplObj.getUploadedDataErrorMessage(session, employeeRolesList);
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
		else if("UserInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND userInfoId = :userInfoId ";
			return loginBasedWhereClause;
		}
				else if("Organisations".equalsIgnoreCase(loginEntityType))
		{
			String neutralAccessClause = "";
			loginBasedWhereClause = " AND (organisationsUserOrgId = :organisationsUserOrgId )";
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
		else if("UserInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("userInfoId", userId);
		}
				else if("Organisations".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("organisationsUserOrgId", userId);
		}

	}
	public String getParentFilterClauseForUserInfo(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForUserInfo(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveUserInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> userInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("userInfoId"))
		{
			int userInfoId = Integer.parseInt(paramsMap.get("userInfoId"));
			if(userInfoId>0)
			{
				userInfoIdsList.add(userInfoId);
			}
		}
		return retrieveUserInfoList(paramsMap, userInfoIdsList);
	}
	public JsonObject retrieveUserInfoList(java.util.Map<String, String> paramsMap, List<Integer> userInfoIdsList)
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
						String userFirstName = paramsMap.get("userFirstName");
			if(userFirstName==null)
			{
				userFirstName = "";
			}
			String userLastName = paramsMap.get("userLastName");
			if(userLastName==null)
			{
				userLastName = "";
			}
			String loginId = paramsMap.get("loginId");
			if(loginId==null)
			{
				loginId = "";
			}
			String loginEmailId = paramsMap.get("loginEmailId");
			if(loginEmailId==null)
			{
				loginEmailId = "";
			}
			String contactNo = paramsMap.get("contactNo");
			if(contactNo==null)
			{
				contactNo = "";
			}
			String loginPassword = paramsMap.get("loginPassword");
			if(loginPassword==null)
			{
				loginPassword = "";
			}
			String resetToken = paramsMap.get("resetToken");
			if(resetToken==null)
			{
				resetToken = "";
			}

						Integer organisationsUserOrgId = -2;
	    	if(paramsMap.containsKey("organisationsUserOrgId"))
	    	{
				organisationsUserOrgId = Integer.parseInt(paramsMap.get("organisationsUserOrgId"));
	    	}

			String hql = "FROM UserInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String userInfoIdFilterClass = "";
			if (userInfoIdsList != null && userInfoIdsList.size() > 0)
			{
				userInfoIdFilterClass = " AND userInfoId in (:idsList) ";
			}
						String userFirstNameFilterClass = "";
			if (userFirstName.length() > 0)
			{
				
				
				
				
				
				
				
				userFirstNameFilterClass = " AND userFirstName LIKE :userFirstName ";
				
				
				
				
			}
			String userLastNameFilterClass = "";
			if (userLastName.length() > 0)
			{
				
				
				
				
				
				
				
				userLastNameFilterClass = " AND userLastName LIKE :userLastName ";
				
				
				
				
			}
			String loginIdFilterClass = "";
			if (loginId.length() > 0)
			{
				
				
				
				
				
				
				
				loginIdFilterClass = " AND loginId LIKE :loginId ";
				
				
				
				
			}
			String loginEmailIdFilterClass = "";
			if (loginEmailId.length() > 0)
			{
				
				
				
				
				
				
				
				loginEmailIdFilterClass = " AND loginEmailId LIKE :loginEmailId ";
				
				
				
				
			}
			String contactNoFilterClass = "";
			if (contactNo.length() > 0)
			{
				
				
				
				
				
				
				
				contactNoFilterClass = " AND contactNo LIKE :contactNo ";
				
				
				
				
			}
			String loginPasswordFilterClass = "";
			if (loginPassword.length() > 0)
			{
				
				
				
				
				
				
				
				loginPasswordFilterClass = " AND loginPassword LIKE :loginPassword ";
				
				
				
				
			}
			String resetTokenFilterClass = "";
			if (resetToken.length() > 0)
			{
				
				
				
				
				
				
				
				resetTokenFilterClass = " AND resetToken LIKE :resetToken ";
				
				
				
				
			}

						String organisationsUserOrgFilterClass = "";
			if (organisationsUserOrgId >= -1)
			{
				organisationsUserOrgFilterClass = " AND organisationsUserOrgId = :organisationsUserOrgId";
			}

			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForUserInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					userInfoIdFilterClass +
										userFirstNameFilterClass +
					userLastNameFilterClass +
					organisationsUserOrgFilterClass +
					loginIdFilterClass +
					loginEmailIdFilterClass +
					contactNoFilterClass +
					loginPasswordFilterClass +
					resetTokenFilterClass +

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
			if (userInfoIdsList != null && userInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", userInfoIdsList);
			}
						if (userFirstName.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("userFirstName", userFirstName);
				
				
				
				
			}
			if (userLastName.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("userLastName", userLastName);
				
				
				
				
			}
			if (loginId.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("loginId", loginId);
				
				
				
				
			}
			if (loginEmailId.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("loginEmailId", loginEmailId);
				
				
				
				
			}
			if (contactNo.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("contactNo", contactNo);
				
				
				
				
			}
			if (loginPassword.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("loginPassword", loginPassword);
				
				
				
				
			}
			if (resetToken.length() > 0)
			{
				
				
				
				
				
				
				
				query.setParameter("resetToken", resetToken);
				
				
				
				
			}

						if (organisationsUserOrgId >= -1)
			{
				query.setParameter("organisationsUserOrgId", organisationsUserOrgId);
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
	    	setParentFilterClauseParamsForUserInfo(paramsMap, query);
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
			JsonArray userInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				UserInfo userInfo = (UserInfo) it.next();
				JsonObject userInfoDataObject = userInfo.getDataObject(getDBSession());
				userInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(userInfo.getVwTxnStatus()));
				userInfoList.add(userInfoDataObject);
				doAfterSearchResultRowAddedImpl(userInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from UserInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (userInfoIdsList != null && userInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", userInfoIdsList);
				}
								if (userFirstName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("userFirstName", userFirstName);
					
					
					
					
				}
				if (userLastName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("userLastName", userLastName);
					
					
					
					
				}
				if (loginId.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("loginId", loginId);
					
					
					
					
				}
				if (loginEmailId.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("loginEmailId", loginEmailId);
					
					
					
					
				}
				if (contactNo.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("contactNo", contactNo);
					
					
					
					
				}
				if (loginPassword.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("loginPassword", loginPassword);
					
					
					
					
				}
				if (resetToken.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("resetToken", resetToken);
					
					
					
					
				}

								if (organisationsUserOrgId >= -1)
				{
					countQuery.setParameter("organisationsUserOrgId", organisationsUserOrgId);
				}

				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForUserInfo(paramsMap, countQuery);
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
			dataObject.add("userInfoList",   userInfoList);
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
				+ "   from UserInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from UserInfo E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentUserInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveUserInfoList(paramsMap);
	}
	public UserInfo getUserInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from UserInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			UserInfo userInfo = (UserInfo) it.next();
			return userInfo;
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
		UserInfo userInfo = (UserInfo)getManagedBean();
		JsonObject userInfoDataObject = userInfo.getDataObject(getDBSession());copyUserInfoFromJson(userInfo, userInfoDataObject);
		setManagedBean(userInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData UserInfoContollerBase");
			UserInfo currentBean = (UserInfo)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "UserFirstName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setUserFirstName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "UserLastName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setUserLastName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "LoginId".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setLoginId(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "LoginEmailId".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setLoginEmailId(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "ContactNo".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setContactNo(sStringTestData);iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "LoginPassword".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setLoginPassword(sStringTestData);iFieldLength = 0;
		sFieldLength = "10";
		sStringTestData = "ResetToken".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setResetToken(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData UserInfoContollerBase");
	}
	// end Test Data
	public void copyUserInfoFromJson(UserInfo userInfo, JsonObject userInfoDataObject)
	{
		copyUserInfoFromJson(userInfo, userInfoDataObject, false);
	}
	public void copyUserInfoFromJson(UserInfo userInfo, JsonObject userInfoDataObject, boolean excludePasswordFields)
	{	
				
		if(userInfoDataObject.has("userFirstName"))
		{
			String userFirstName = userInfoDataObject.get("userFirstName").getAsString();
			userInfo.setUserFirstName(userFirstName);
		}if(userInfoDataObject.has("userLastName"))
		{
			String userLastName = userInfoDataObject.get("userLastName").getAsString();
			userInfo.setUserLastName(userLastName);
		}
		if(userInfoDataObject.has("organisationsUserOrgId"))
		{
			Integer organisationsUserOrgId = userInfoDataObject.get("organisationsUserOrgId").getAsInt();
			userInfo.setOrganisationsUserOrgId(organisationsUserOrgId);
		}if(userInfoDataObject.has("loginId"))
		{
			String loginId = userInfoDataObject.get("loginId").getAsString();
			userInfo.setLoginId(loginId);
		}if(userInfoDataObject.has("loginEmailId"))
		{
			String loginEmailId = userInfoDataObject.get("loginEmailId").getAsString();
			userInfo.setLoginEmailId(loginEmailId);
		}if(userInfoDataObject.has("contactNo"))
		{
			String contactNo = userInfoDataObject.get("contactNo").getAsString();
			userInfo.setContactNo(contactNo);
		}if(userInfoDataObject.has("loginPassword") && excludePasswordFields == false)
		{
			String loginPassword = userInfoDataObject.get("loginPassword").getAsString();
			userInfo.setLoginPassword(loginPassword);
		}if(userInfoDataObject.has("resetToken"))
		{
			String resetToken = userInfoDataObject.get("resetToken").getAsString();
			userInfo.setResetToken(resetToken);
		}
		
	}
		
	public JsonObject createUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		return createUserInfo(userInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject userInfoDataObject)
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
			userInfoDataObject.addProperty("organisationsUserOrgId", userId);
		}

	}
	public JsonObject createUserInfo(JsonObject userInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		UserInfo userInfo = new UserInfo();
		setLoginBasedValues(paramsInfoObj, userInfoDataObject);
		Session session = getDBSession();				
		copyUserInfoFromJson(userInfo, userInfoDataObject);
		if(userInfoDataObject.has("legacyRecordId"))
		{
			userInfo.setLegacyRecordId(userInfoDataObject.get("legacyRecordId").getAsInt());
		}
				userInfo.setVwCreatedBy(createdBy);
		userInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(userInfo);
		setManagedBean("UserInfoBean", userInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		userInfo = (UserInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("userInfoId", userInfo.getUserInfoId());
			JsonObject userInfoObj = userInfo.getDataObject(getDBSession());
			userInfoObj.addProperty("nextAction", getActionForCurrentStatus(userInfo.getVwTxnStatus()));
			dataObject.add("userInfoDataObject", userInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer userInfoId = userInfoDataObject.get("userInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("userInfoId", userInfoId);
		JsonObject responseData = retrieveUserInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'User Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
		if(userInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(userInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromUserInfo(userInfoDataObject);
		boolean excludePasswordFields = true;
		copyUserInfoFromJson(userInfo, userInfoDataObject, excludePasswordFields);setPrivateManagedBean(userInfo);
		setManagedBean("UserInfoBean", userInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		userInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("userInfoId", userInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromUserInfo(JsonObject userInfoDataObject) throws Exception
	{
				if(userInfoDataObject.has("loginPassword"))
		{
			userInfoDataObject.remove("loginPassword");
		}

		
	}
	public JsonObject deleteUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer userInfoId = userInfoDataObject.get("userInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("userInfoId", userInfoId);
		JsonObject responseData = retrieveUserInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'User Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);setPrivateManagedBean(userInfo);
		setManagedBean("UserInfo", userInfo);
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
	public JsonObject fetchUserInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		UserInfo userInfo = new UserInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfo", userInfo);
			doAfterUserInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("userInfo", userInfo.getDataObject(getDBSession()));
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
	public JsonObject fetchUserInfoTestData(int obj, JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		UserInfo userInfo = new UserInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyUserInfoFromJson(userInfo, userInfoDataObject);
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfo", userInfo);
			doAfterUserInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("userInfo", userInfo.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		UserInfo userInfo = new UserInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyUserInfoFromJson(userInfo, userInfoDataObject);	String attributeName = userInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = userInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfo", userInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("userInfo", userInfo.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		UserInfo userInfo = new UserInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyUserInfoFromJson(userInfo, userInfoDataObject);	
			String attributeName = userInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfo", userInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("userInfo", userInfo.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer userInfoId = userInfoDataObject.get("userInfoId").getAsInt();
			String customEventName = userInfoDataObject.get("customEventName").getAsString();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfoBean", userInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("userInfo", userInfo.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnUserInfo(JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer userInfoId = userInfoDataObject.get("userInfoId").getAsInt();
			String currentStatus = userInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(userInfoDataObject.has("currentAction"))
			{
				currentAction = userInfoDataObject.get("currentAction").getAsString();
			}
			UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
			setPrivateManagedBean(userInfo);
			setManagedBean("UserInfoBean", userInfo);
			if(userInfo.getIsRequestUnderProcesss() == 1)
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
				executeAction(userInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(userInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(userInfo, "UserInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updateduserInfoDataObject = userInfo.getDataObject(getDBSession());
				updateduserInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(userInfo.getVwTxnStatus()));
				dataObject.add("userInfo", updateduserInfoDataObject);
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
		UserInfo userInfo = (UserInfo) getManagedBean();
		String currentStatus = userInfo.getVwTxnStatus();
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
	
	
	public JsonObject downloadUserInfoData() throws Exception
	{
		return downloadUserInfoData(null);
	}
	public JsonObject downloadUserInfoData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "UserInfo");
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
			headerName = "userInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "userFirstName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "userLastName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "organisationsUserOrgId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
	        					cell = row.createCell(headerCellCount++);
			headerName = "organisationsUserOrg_organisationName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

		
cell = row.createCell(headerCellCount++);
			headerName = "loginId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "loginEmailId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "contactNo";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "loginPassword";
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
			String hql = "From UserInfo ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				UserInfo userInfo = (UserInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer userInfoId = userInfo.getUserInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(userInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String userFirstName = userInfo.getUserFirstName();
				cell.setCellValue(userFirstName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String userLastName = userInfo.getUserLastName();
				cell.setCellValue(userLastName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				
				Integer organisationsUserOrgId = userInfo.getOrganisationsUserOrgId();
				com.patientapp.bean.Organisations  organisationsUserOrgObj = null;
				if(organisationsUserOrgId > 0)
				{   
					cell.setCellValue(String.valueOf(organisationsUserOrgId));
					organisationsUserOrgObj = (com.patientapp.bean.Organisations) session.get(com.patientapp.bean.Organisations.class, organisationsUserOrgId);
				}   
		        				cell = row.createCell(dataCellCount++);
		cell.setCellStyle(dataStyle);if(organisationsUserOrgObj != null && organisationsUserOrgObj.getOrganisationName() !=null)
		{
			cell.setCellValue(organisationsUserOrgObj.getOrganisationName());
		}

				

				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String loginId = userInfo.getLoginId();
				cell.setCellValue(loginId);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String loginEmailId = userInfo.getLoginEmailId();
				cell.setCellValue(loginEmailId);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String contactNo = userInfo.getContactNo();
				cell.setCellValue(contactNo);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String loginPassword = userInfo.getLoginPassword();
				cell.setCellValue(loginPassword);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
				employeeRolesImplObj.downloadEmployeeRolesData(sheet, headerStyle, dataStyle, rowColumnIndexObject, userInfoId);

			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "UserInfo.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "UserInfo.xls";
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
	public JsonObject uploadUserInfoData(JsonObject userInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = userInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = userInfoDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "User Info Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		userInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadUserInfoData(workbook, userInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "User Info Data uploaded successfully.");
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
	public JsonObject uploadUserInfoData(HSSFWorkbook workbook, JsonObject userInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("UserInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = userInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = userInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = userInfoDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "User Info Data uploaded successfully.");
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
			JsonObject userInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject userInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray userInfoList = userInfoListObj.get("userInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadUserInfoList(userInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(userInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(userInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray userInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject userInfo = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("employeeRoles"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session);
					JsonObject responseData  = employeeRolesImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray employeeRolesList = responseData.get("employeeRolesList").getAsJsonArray();
					userInfo.add("employeeRolesList", employeeRolesList);
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
				JsonObject userInfoUploadObj	= new JsonObject();
				userInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					userInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("userInfoId"))
						{
							String userInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(userInfoId != null && userInfoId.trim().length() > 0)
							{
								try
								{
									Integer iUserInfoId = Integer.parseInt(userInfoId);
									if(areSourceDestinationSame == 1)
									{
										UserInfo userInfoObj = (UserInfo)session.get(UserInfo.class, iUserInfoId);
										if(userInfoObj != null)
										{ 
											userInfo.addProperty("userInfoId", iUserInfoId);
										}
										else
										{
											userInfoUploadObj.addProperty("isDataFetched", 0);
											userInfoUploadObj.addProperty("msg", "This User Info could not be uploaded as there appears to be some problem with the data(UserInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										UserInfo userInfoObj = getUserInfoByLegacyRecordId(session, iUserInfoId);
										if(userInfoObj != null)
										{ 
											userInfo.addProperty("userInfoId", userInfoObj.getUserInfoId());
										}
										userInfo.addProperty("legacyRecordId", iUserInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									userInfoUploadObj.addProperty("isDataFetched", 0);
									userInfoUploadObj.addProperty("msg", "This User Info could not be uploaded as there appears to be some problem with the data(UserInfo Id). ");
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
							userInfo.addProperty(parameterName, parameterValue);
						}
					}
					userInfoUploadObj.add("dataObject", userInfo);		    
					userInfoList.add(userInfoUploadObj);
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
			dataObject.add("userInfoList", userInfoList);
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
		if(previousRetryCountMap.has("UserInfo") && previousRetryCountMap.get("UserInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("UserInfo").getAsInt();
		}
		if(retryCountMap.has("UserInfo") && retryCountMap.get("UserInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("UserInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    		com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

		return 0;
	}
	public void updateRetryCountMapForUserInfoList(JsonArray userInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < userInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject userInfoDataObject = userInfoList.get(i).getAsJsonObject();
			JsonObject userInfo = userInfoDataObject.get("dataObject").getAsJsonObject();
			if(userInfoDataObject.has("retryUpload") && userInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = userInfoDataObject.get("retryUpload").getAsInt();
			}
			if(userInfoDataObject.has("retryChildEntitiesUpload") && userInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = userInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(userInfoDataObject.has("partialUploadUnderProcess") && userInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = userInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    		    if(userInfo.has("employeeRolesList"))
		    {
				com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(getDBSession());
				JsonArray employeeRolesList = userInfo.get("employeeRolesList").getAsJsonArray();
				employeeRolesImplObj.updateRetryCountMapForEmployeeRolesList(employeeRolesList, retryCountMap);
		    }

		}
	    retryCountMap.addProperty("UserInfo", retryCount);
	}
	public JsonObject uploadUserInfoList(JsonArray userInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(userInfoList);
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
	public JsonObject updateStatusMsg(JsonArray userInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < userInfoList.size(); i++)
			{
				JsonObject userInfoDataObject = userInfoList.get(i).getAsJsonObject();
				JsonObject userInfo = userInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = userInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = userInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = userInfoDataObject.get("errorMessage").getAsString();
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
			    			    if(isSuccessfullyUploaded == 1 && userInfo.has("employeeRolesList"))
			    {
					com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(getDBSession());
					JsonArray employeeRolesList = userInfo.get("employeeRolesList").getAsJsonArray(); 
					responseData  = employeeRolesImplObj.updateStatusMsg(employeeRolesList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
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
	public JsonObject uploadData(JsonArray userInfoList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < userInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject userInfoDataObject = userInfoList.get(i).getAsJsonObject();
				JsonObject userInfo = userInfoDataObject.get("dataObject").getAsJsonObject();
				userInfoDataObject.addProperty("retryUpload", 0);
				userInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				userInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.UserInfoControllerImpl userInfoImplObj = new com.patientapp.controller.forms.impl.UserInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = userInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, userInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					userInfoDataObject.addProperty("retryUpload", 1);
					userInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					userInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					userInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int userInfoId = -1;
				int keyColumnsCount = 0;
								keyColumnsCount++;

				if(keyColumnsCount > 0 && !userInfo.has("userInfoId"))
				{
					userInfo.addProperty("attributeNamePrefix", "");
					
					userInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = userInfoImplObj.getUserInfoByLookupFields(session,  userInfo);
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
						JsonObject userInfoObject = responseData.get("userInfoDataObject").getAsJsonObject();
						userInfoId = userInfoObject.get("userInfoId").getAsInt();
						userInfo.addProperty("userInfoId", userInfoId);
						updateEntity = true;
					}
				}
				else if(userInfo.has("userInfoId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = userInfoImplObj.createUserInfo(userInfo);
				}
				else
				{
					responseData = userInfoImplObj.updateUserInfo(userInfo);
				}
				userInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					userInfoId =-1;
					userInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					userInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				userInfoId = responseData.get("userInfoId").getAsInt();
				userInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    			    if(userInfo.has("employeeRolesList"))
			    {
				    JsonArray employeeRolesList = userInfo.get("employeeRolesList").getAsJsonArray();
					com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl employeeRolesImplObj = new com.patientapp.controller.forms.impl.EmployeeRolesControllerImpl(session, getUserSessionInfo());
					responseData  = employeeRolesImplObj.deleteEmployeeRolesListIfKeyColumnsNotFound(session, userInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = employeeRolesImplObj.uploadData(employeeRolesList, userInfoId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int employeeRolesListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += employeeRolesListRetryCount;
					if(employeeRolesListRetryCount>0)
					{
						userInfoDataObject.addProperty("retryChildEntitiesUpload", 1);
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject userInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;
				hasParamsForLookup = com.patientapp.controller.forms.impl.OrganisationsControllerImpl.hasParamsForLookup(userInfo, "organisationsUserOrg");
		if(hasParamsForLookup==0)
		{
			userInfo.addProperty("organisationsUserOrgId", -1);
		}
		else
		{
			dataObject = com.patientapp.controller.forms.impl.OrganisationsControllerImpl.getQueryParamsForLookupSearch(userInfo, "organisationsUserOrg");
			requestParams = new JsonObject();
			if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
			{		
				requestParams = dataObject.get("requestParams").getAsJsonObject();
			}
			requestParams.addProperty("attributeNamePrefix", "organisationsUserOrg");
			requestParams.add("attributesInfo", userInfo);
			com.patientapp.controller.forms.impl.OrganisationsControllerImpl organisationsUserOrgImplObj = new com.patientapp.controller.forms.impl.OrganisationsControllerImpl(session);
			JsonObject organisationsUserOrgResponseData = organisationsUserOrgImplObj.getOrganisationsByLookupFields(session,  requestParams);
			if(organisationsUserOrgResponseData!=null && organisationsUserOrgResponseData.has("success") && organisationsUserOrgResponseData.get("success").getAsInt()==1)
			{
				JsonObject organisationsUserOrgDataObject = organisationsUserOrgResponseData.get("organisationsDataObject").getAsJsonObject();
				int organisationsUserOrgId = organisationsUserOrgDataObject.get("organisationsId").getAsInt();
				userInfo.addProperty("organisationsUserOrgId", organisationsUserOrgId);
			}
			else
			{
				int optionalForUpload = 0;
				
				if(optionalForUpload==1)
				{
					userInfo.addProperty("organisationsUserOrgId", -1);
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
	public JsonObject getUserInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From UserInfo where 1 = 1  ";
			String countHql = "select count(*)  from UserInfo where 1 = 1 ";
						
			String userFirstName = requestParams.get("userFirstName").getAsString();
			hql += " and userFirstName = :userFirstName ";
			countHql += " and userFirstName = :userFirstName ";
Query countQuery = session.createQuery(countHql);			countQuery.setParameter("userFirstName", userFirstName);
Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as User Info could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);			query.setParameter("userFirstName", userFirstName);
List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				UserInfo userInfo = (UserInfo) it.next();
				JsonObject userInfoDataObject = userInfo.getDataObject(session);
				dataObject.add("userInfoDataObject", userInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as User Info could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			String userFirstName = searchObject.get(attributeNamePrefix + "_" + "userFirstName").getAsString();
			requestParams.addProperty("userFirstName", userFirstName);
dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'User Info' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			if(searchObject.has(attributeNamePrefix + "_" + "userFirstName"))
			{
				String userFirstName = searchObject.get(attributeNamePrefix + "_" + "userFirstName").getAsString();
				if(userFirstName!=null && userFirstName.length()>0)
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
		else if(apiName.equals("getUserInfoPropertyValue"))
			{
				JsonObject inputDataObject = getUserInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getUserInfo"))
			{
				JsonObject inputDataObject = getUserInfo(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForUserInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForUserInfo"))
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
			Integer userInfoId = requestReceivedParametersInfo.get("userInfoId").getAsInt();
			UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
			userInfo.setIsRequestUnderProcesss(0);
			session.merge(userInfo);
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
	public JsonObject getUserInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer userInfoId = inputForGetAPI.get("userInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, userInfoId);
		userInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getUserInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer userInfoId = inputForGetAPI.get("userInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userInfoId", String.valueOf(userInfoId));
		JsonObject userInfoListObject = retrieveUserInfoList(paramsMap);
		if(userInfoListObject!=null && userInfoListObject.has("success") && userInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray userInfoList = userInfoListObject.get("userInfoList").getAsJsonArray();
			if(userInfoList.size()>0)
			{
				dataObject.add("userInfo", userInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'User Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject userInfoDataObject, UserInfo userInfo);
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
	public abstract void doAfterLookupRowSelectedImpl(UserInfo userInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(UserInfo userInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(UserInfo userInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
