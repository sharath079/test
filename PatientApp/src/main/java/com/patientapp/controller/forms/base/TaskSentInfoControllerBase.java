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

import com.patientapp.bean.TaskSentInfo;
import com.patientapp.controller.forms.base.TaskSentInfoLabel;
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
public abstract class TaskSentInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'TaskInfo' 
	 *		 * 'TargetEntityId' 
	 *		 * 'TargetUserId' 
	 *		 * 'NotificationMedium' 
	 *		 * 'LayoutInfoText' 
	 *		 * 'NotificationSentTime' 
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
	protected TaskSentInfoLabel TaskSentInfoLabelLocal = new TaskSentInfoLabel();
	protected TaskSentInfo TaskSentInfoLocal = new TaskSentInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public TaskSentInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public TaskSentInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public TaskSentInfoControllerBase()
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
		return "TaskSentInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((TaskSentInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((TaskSentInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((TaskSentInfo)obj).getTaskSentInfoId();
	}
	public String getManagedBeanName()
    {
		return "TaskSentInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "TaskSentInfoLabelBean";
    }
	protected Class<TaskSentInfo> setBeanClass()
	{
		return TaskSentInfo.class;
	}
	protected Class<TaskSentInfoLabel> setBeanLabelClass()
	{
		return TaskSentInfoLabel.class;
	}
	protected TaskSentInfo getLocalManagedBean()
    {
		return (TaskSentInfo)getManagedBean();
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
		/*TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		String areChangesEffected = taskSentInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			taskSentInfo.setAreChangesEffected("YES");			
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
		/*TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		String areChangesEffected = taskSentInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			taskSentInfo.setAreChangesEffected("NO");			
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
		/*TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		String areChangesEffected = taskSentInfo.getAreChangesEffected();
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
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		String sCurrentStatus = taskSentInfo.getVwTxnStatus();
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
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("notificationMedium".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(taskSentInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterTaskSentInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				if(initParamsInfo.has("taskInfo") && initParamsInfo.get("taskInfo").isJsonNull()==false)
		{
			Integer taskInfoId = initParamsInfo.get("taskInfoId").getAsInt();
			taskSentInfo.setTaskInfoId(taskInfoId);
		}if(initParamsInfo.has("targetEntityId") && initParamsInfo.get("targetEntityId").isJsonNull()==false)
		{
			Integer targetEntityId = null;
			try
			{
			 	targetEntityId = initParamsInfo.get("targetEntityId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskSentInfo.setTargetEntityId(targetEntityId);
		}if(initParamsInfo.has("targetUserId") && initParamsInfo.get("targetUserId").isJsonNull()==false)
		{
			Integer targetUserId = null;
			try
			{
			 	targetUserId = initParamsInfo.get("targetUserId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskSentInfo.setTargetUserId(targetUserId);
		}if(initParamsInfo.has("notificationMedium") && initParamsInfo.get("notificationMedium").isJsonNull()==false)
		{
			String notificationMedium = initParamsInfo.get("notificationMedium").getAsString();
			taskSentInfo.setNotificationMedium(notificationMedium);			
		}if(initParamsInfo.has("layoutInfoText") && initParamsInfo.get("layoutInfoText").isJsonNull()==false)
		{
			String layoutInfoText = initParamsInfo.get("layoutInfoText").getAsString();
			taskSentInfo.setLayoutInfoText(layoutInfoText);			
		}
		
		if(initParamsInfo.has("notificationSentTime") && initParamsInfo.get("notificationSentTime").isJsonNull()==false)
		{
			String notificationSentTime = initParamsInfo.get("notificationSentTime").getAsString();
			if(notificationSentTime.length() > 0)
			{
				try
				{
					taskSentInfo.setNotificationSentTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(notificationSentTime));
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
		doAfterEntityLoadedImpl(taskSentInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}
				else if("taskInfo".equalsIgnoreCase(attributeName))
		{
			  
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(taskSentInfo, attributeName);
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
		{JsonObject dataObject = new JsonObject();		if(isTransactionUpdatable()==false)
			{
				addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
			}
			TaskSentInfo taskSentInfo = (TaskSentInfo)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "TaskSentInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() TaskSentInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (TaskSentInfoSearch)getManagedBean("TaskSentInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getTaskInfo()))
			{
				searchParams.put(TaskSentInfoLabelLocal.gettaskInfoFieldName(),searchBeanLocal.getTaskInfo());
			}	
			if (isExists(searchBeanLocal.getTargetEntityId()))
			{
				searchParams.put(TaskSentInfoLabelLocal.gettargetEntityIdFieldName(),searchBeanLocal.getTargetEntityId());
			}	
			if (isExists(searchBeanLocal.getTargetUserId()))
			{
				searchParams.put(TaskSentInfoLabelLocal.gettargetUserIdFieldName(),searchBeanLocal.getTargetUserId());
			}	
			if (isExists(searchBeanLocal.getNotificationMedium()))
			{
				searchParams.put(TaskSentInfoLabelLocal.getnotificationMediumFieldName(),searchBeanLocal.getNotificationMedium());
			}	
			if (isExists(searchBeanLocal.getLayoutInfoText()))
			{
				searchParams.put(TaskSentInfoLabelLocal.getlayoutInfoTextFieldName(),searchBeanLocal.getLayoutInfoText());
			}	
			if (isExists(searchBeanLocal.getNotificationSentTime()))
			{
				searchParams.put(TaskSentInfoLabelLocal.getnotificationSentTimeFieldName(),searchBeanLocal.getNotificationSentTime());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(TaskSentInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() TaskSentInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues TaskSentInfoContollerBase");
		targetBean = (TaskSentInfo)targetBean;((TaskSentInfo)targetBean).setTaskSentInfoId(((TaskSentInfo)sourceBean).getTaskSentInfoId());
				((TaskSentInfo)targetBean).setTargetEntityId(((TaskSentInfo)sourceBean).getTargetEntityId());
		((TaskSentInfo)targetBean).setTargetUserId(((TaskSentInfo)sourceBean).getTargetUserId());
		((TaskSentInfo)targetBean).setNotificationMedium(((TaskSentInfo)sourceBean).getNotificationMedium());
		((TaskSentInfo)targetBean).setLayoutInfoText(((TaskSentInfo)sourceBean).getLayoutInfoText());
		((TaskSentInfo)targetBean).setNotificationSentTime(((TaskSentInfo)sourceBean).getNotificationSentTime());

				((TaskSentInfo)targetBean).setTaskInfoId(((TaskSentInfo)sourceBean).getTaskInfoId());

				/*if (bSetAsManagedBean)
		{			
			TaskInfo TaskInfoLocal = (TaskInfo)(( TaskSentInfo)sourceBean).getTaskInfo();
			setManagedBean("TaskInfoBean", TaskInfoLocal);
		}*/

		doAfterSetValues();
		debugCode("Out setValues TaskSentInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.TaskSentInfo entityBean = (TaskSentInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("taskSentInfoId")) 
	 	{
			return entityBean.getTaskSentInfoId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("TargetEntityId")) 
	 	{
			return entityBean.getTargetEntityId();
		}
	 	if (sFieldName.equalsIgnoreCase("TargetUserId")) 
	 	{
			return entityBean.getTargetUserId();
		}
	 	if (sFieldName.equalsIgnoreCase("NotificationMedium")) 
	 	{
			return entityBean.getNotificationMedium();
		}
	 	if (sFieldName.equalsIgnoreCase("LayoutInfoText")) 
	 	{
			return entityBean.getLayoutInfoText();
		}
	 	if (sFieldName.equalsIgnoreCase("NotificationSentTime")) 
	 	{
			return entityBean.getNotificationSentTime();
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
		debugCode("In doEnrichSystemValues(String sAction) TaskSentInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((TaskSentInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((TaskSentInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((TaskSentInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((TaskSentInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((TaskSentInfo) localManagedBean).setVwTxnStatus("CREATED");
			((TaskSentInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((TaskSentInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((TaskSentInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) TaskSentInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) TaskSentInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) TaskSentInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations TaskSentInfoControllerBase");
		//NG: Important comment
		//managedBean = (TaskSentInfo) getManagedBean();
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
		debugCode("Out doValidations TaskSentInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation TaskSentInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation TaskSentInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation TaskSentInfoControllerBase");
				
		String sFieldValue5 = ((TaskSentInfo) localManagedBean).getNotificationMedium();if (!isExists(sFieldValue5,localMasters.getNotificationMedium())) addAllowedValuesResponse(TaskSentInfoLabelLocal.getnotificationMediumFieldName());

		debugCode("Out doAllowedValuesValidation TaskSentInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation TaskSentInfoControllerBase");
				
		Integer sFieldValue3 = ((TaskSentInfo) localManagedBean).getTargetEntityId();		Integer sFieldValue4 = ((TaskSentInfo) localManagedBean).getTargetUserId();		String sFieldValue5 = ((TaskSentInfo) localManagedBean).getNotificationMedium();
		
		Date sFieldValue7 = ((TaskSentInfo) localManagedBean).getNotificationSentTime();

				Integer iFieldValue2 = ((TaskSentInfo) localManagedBean).getTaskInfoId();

				if (!isExists(sFieldValue3)) addMandatoryResponse(TaskSentInfoLabelLocal.gettargetEntityIdFieldName());
		if (!isExists(sFieldValue4)) addMandatoryResponse(TaskSentInfoLabelLocal.gettargetUserIdFieldName());
		if (!isExists(sFieldValue5)) addMandatoryResponse(TaskSentInfoLabelLocal.getnotificationMediumFieldName());
		if (!isExists(sFieldValue7)) addMandatoryResponse(TaskSentInfoLabelLocal.getnotificationSentTimeFieldName());

				if (iFieldValue2 <= 0) addMandatoryResponse(TaskSentInfoLabelLocal.gettaskInfoFieldName());

		debugCode("Out doMandatoryValidation TaskSentInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation TaskSentInfoControllerBase");
				
		Integer sFieldValue3 = ((TaskSentInfo) localManagedBean).getTargetEntityId();		Integer sFieldValue4 = ((TaskSentInfo) localManagedBean).getTargetUserId();		String sFieldValue5 = ((TaskSentInfo) localManagedBean).getNotificationMedium();String sFieldValue6 = ((TaskSentInfo) localManagedBean).getLayoutInfoText();
		
		Date sFieldValue7 = ((TaskSentInfo) localManagedBean).getNotificationSentTime();

				Integer iFieldValue2 = ((TaskSentInfo) localManagedBean).getTaskInfoId();

				if (!isLengthAllowed(sFieldValue3,"10")) addMaxLengthResponse(TaskSentInfoLabelLocal.gettargetEntityIdFieldName(),"10");
		if (!isLengthAllowed(sFieldValue4,"10")) addMaxLengthResponse(TaskSentInfoLabelLocal.gettargetUserIdFieldName(),"10");
		if (!isLengthAllowed(sFieldValue5,"30")) addMaxLengthResponse(TaskSentInfoLabelLocal.getnotificationMediumFieldName(),"30");
		if (!isLengthAllowed(sFieldValue6,"1000")) addMaxLengthResponse(TaskSentInfoLabelLocal.getlayoutInfoTextFieldName(),"1000");
		if (!isLengthAllowed(sFieldValue7,"20")) addMaxLengthResponse(TaskSentInfoLabelLocal.getnotificationSentTimeFieldName(),"20");

				//if (!isLengthAllowed(iFieldValue2,"")) addMaxLengthResponse(TaskSentInfoLabelLocal.gettaskInfoFieldName(),"");

		debugCode("Out doLengthValidation TaskSentInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation TaskSentInfoControllerBase");
		debugCode("Out doDataTypeValidation TaskSentInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation TaskSentInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation TaskSentInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel TaskSentInfoContollerBase");
		String sLabel = new TaskSentInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel TaskSentInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int taskSentInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("taskSentInfoId", taskSentInfoId);
			JsonObject responseData = retrieveTaskSentInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Task Sent Info' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject taskSentInfoDataObject = responseData.get("taskSentInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, taskSentInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Task Sent Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveTaskSentInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer taskSentInfoId = -1;
		if(requestParams.has("taskSentInfoId"))
		{
			taskSentInfoId = requestParams.get("taskSentInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("taskSentInfoId", String.valueOf(taskSentInfoId));
				String targetEntityId = "";
		if(requestParams.has("targetEntityId"))
		{
			paramsMap.put("targetEntityId", requestParams.get("targetEntityId").getAsString());
		}
		String targetUserId = "";
		if(requestParams.has("targetUserId"))
		{
			paramsMap.put("targetUserId", requestParams.get("targetUserId").getAsString());
		}
		String notificationMedium = "";
		if(requestParams.has("notificationMedium"))
		{
			paramsMap.put("notificationMedium", requestParams.get("notificationMedium").getAsString());
		}
		String layoutInfoText = "";
		if(requestParams.has("layoutInfoText"))
		{
			paramsMap.put("layoutInfoText", requestParams.get("layoutInfoText").getAsString());
		}
		String notificationSentTime = "";
		if(requestParams.has("notificationSentTime"))
		{
			paramsMap.put("notificationSentTime", requestParams.get("notificationSentTime").getAsString());
		}

				Integer taskInfoId = -1;
		if(requestParams.has("taskInfoId"))
		{
			paramsMap.put("taskInfoId", requestParams.get("taskInfoId").getAsString());
		}
JsonObject taskSentInfoListObject = retrieveTaskSentInfoList(paramsMap);
		if(taskSentInfoListObject!=null && taskSentInfoListObject.has("success") && taskSentInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskSentInfoList = taskSentInfoListObject.get("taskSentInfoList").getAsJsonArray();
			if(taskSentInfoList.size()>0)
			{
				dataObject.add("taskSentInfoDataObject", taskSentInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Sent Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getTaskSentInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String targetEntityId = "";
			if(searchParams.has("targetEntityId"))
			{
				paramsMap.put("targetEntityId", searchParams.get("targetEntityId").getAsString());
			}
			String targetUserId = "";
			if(searchParams.has("targetUserId"))
			{
				paramsMap.put("targetUserId", searchParams.get("targetUserId").getAsString());
			}
			String notificationMedium = "";
			if(searchParams.has("notificationMedium"))
			{
				paramsMap.put("notificationMedium", searchParams.get("notificationMedium").getAsString());
			}
			String layoutInfoText = "";
			if(searchParams.has("layoutInfoText"))
			{
				paramsMap.put("layoutInfoText", searchParams.get("layoutInfoText").getAsString());
			}
			String notificationSentTime = "";
			if(searchParams.has("notificationSentTime"))
			{
				paramsMap.put("notificationSentTime", searchParams.get("notificationSentTime").getAsString());
			}

						Integer taskInfoId = -1;
			if(searchParams.has("taskInfoId"))
			{
				paramsMap.put("taskInfoId", searchParams.get("taskInfoId").getAsString());
			}
paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject taskSentInfoListObject = retrieveTaskSentInfoList(paramsMap);
			if(taskSentInfoListObject!=null && taskSentInfoListObject.has("success") && taskSentInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray taskSentInfoList = taskSentInfoListObject.get("taskSentInfoList").getAsJsonArray();
				if(taskSentInfoList.size()>0)
				{
					dataObject.add("taskSentInfo", taskSentInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Task Sent Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getTaskSentInfoInJson(int taskSentInfoId)
	{
		JsonObject TaskSentInfoData = null;
		List<Integer> taskSentInfoIdsList = new ArrayList<>();
		taskSentInfoIdsList.add(taskSentInfoId);
		JsonArray taskSentInfoList = getTaskSentInfoListInJson(taskSentInfoIdsList);
		if(taskSentInfoList!=null && taskSentInfoList.size()>0)
		{
			TaskSentInfoData = taskSentInfoList.get(0).getAsJsonObject();
		}
		return TaskSentInfoData;
	}
	public JsonArray getTaskSentInfoListInJson(List<Integer> taskSentInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray taskSentInfoObjectsList = new JsonArray();
		JsonObject taskSentInfoListObject = retrieveTaskSentInfoList(paramsMap, taskSentInfoIdsList);
		if(taskSentInfoListObject!=null && taskSentInfoListObject.has("success") && taskSentInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskSentInfoList = taskSentInfoListObject.get("taskSentInfoList").getAsJsonArray();
			for (int i =0; i< taskSentInfoList.size(); i++)
			{
				JsonObject taskSentInfoDataObject = taskSentInfoList.get(i).getAsJsonObject();
				int taskSentInfoId = taskSentInfoDataObject.get("taskSentInfoId").getAsInt();
				
				taskSentInfoObjectsList.add(taskSentInfoDataObject);
			}
		}
		return taskSentInfoObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray taskSentInfoList)
	{
		String errorMessage = "taskSentInfoList: \n";
		for (int i =0; i< taskSentInfoList.size(); i++)
		{
			JsonObject taskSentInfoDataObject = taskSentInfoList.get(i).getAsJsonObject();
			JsonObject taskSentInfo = taskSentInfoDataObject.get("dataObject").getAsJsonObject();if(!taskSentInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "taskSentInfo could not be uploaded verify data \n"; 
			}
			else if(taskSentInfoDataObject.has("isSuccessfullyUploaded") 
					&& taskSentInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += taskSentInfoDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("TaskSentInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND taskSentInfoId = :taskSentInfoId ";
			return loginBasedWhereClause;
		}
				else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			String neutralAccessClause = "";
			loginBasedWhereClause = " AND (taskInfoId = :taskInfoId )";
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
		else if("TaskSentInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskSentInfoId", userId);
		}
				else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskInfoId", userId);
		}

	}
	public String getParentFilterClauseForTaskSentInfo(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForTaskSentInfo(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveTaskSentInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> taskSentInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("taskSentInfoId"))
		{
			int taskSentInfoId = Integer.parseInt(paramsMap.get("taskSentInfoId"));
			if(taskSentInfoId>0)
			{
				taskSentInfoIdsList.add(taskSentInfoId);
			}
		}
		return retrieveTaskSentInfoList(paramsMap, taskSentInfoIdsList);
	}
	public JsonObject retrieveTaskSentInfoList(java.util.Map<String, String> paramsMap, List<Integer> taskSentInfoIdsList)
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
						String targetEntityId = paramsMap.get("targetEntityId");
			if(targetEntityId==null)
			{
				targetEntityId = "";
			}
			String targetUserId = paramsMap.get("targetUserId");
			if(targetUserId==null)
			{
				targetUserId = "";
			}
			String notificationMedium = paramsMap.get("notificationMedium");
			if(notificationMedium==null)
			{
				notificationMedium = "";
			}
			String layoutInfoText = paramsMap.get("layoutInfoText");
			if(layoutInfoText==null)
			{
				layoutInfoText = "";
			}
			String notificationSentTime = paramsMap.get("notificationSentTime");
			if(notificationSentTime==null)
			{
				notificationSentTime = "";
			}

						Integer taskInfoId = -2;
	    	if(paramsMap.containsKey("taskInfoId"))
	    	{
				taskInfoId = Integer.parseInt(paramsMap.get("taskInfoId"));
	    	}

			String hql = "FROM TaskSentInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String taskSentInfoIdFilterClass = "";
			if (taskSentInfoIdsList != null && taskSentInfoIdsList.size() > 0)
			{
				taskSentInfoIdFilterClass = " AND taskSentInfoId in (:idsList) ";
			}
						String targetEntityIdFilterClass = "";
			if (targetEntityId.length() > 0)
			{			
				
				targetEntityIdFilterClass = " AND targetEntityId = :targetEntityId ";
				
			}
			String targetUserIdFilterClass = "";
			if (targetUserId.length() > 0)
			{			
				
				targetUserIdFilterClass = " AND targetUserId = :targetUserId ";
				
			}
			String notificationMediumFilterClass = "";
			if (notificationMedium.length() > 0)
			{		
				
				notificationMediumFilterClass = " AND notificationMedium LIKE :notificationMedium ";	
				
			}
			String layoutInfoTextFilterClass = "";
			if (layoutInfoText.length() > 0)
			{		
				
				layoutInfoTextFilterClass = " AND layoutInfoText LIKE :layoutInfoText ";	
				
			}
			String notificationSentTimeFilterClass = "";
			if (notificationSentTime.length() > 0)
			{
				
				
				notificationSentTimeFilterClass = " AND notificationSentTime = :notificationSentTime ";			
			}

						String taskInfoFilterClass = "";
			if (taskInfoId >= -1)
			{
				taskInfoFilterClass = " AND taskInfoId = :taskInfoId";
			}

			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForTaskSentInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					taskSentInfoIdFilterClass +
										taskInfoFilterClass +
					targetEntityIdFilterClass +
					targetUserIdFilterClass +
					notificationMediumFilterClass +
					layoutInfoTextFilterClass +
					notificationSentTimeFilterClass +

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
			if (taskSentInfoIdsList != null && taskSentInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", taskSentInfoIdsList);
			}
						if (targetEntityId.length() > 0)
			{			
				
				query.setParameter("targetEntityId", Integer.parseInt(targetEntityId));
				
			}
			if (targetUserId.length() > 0)
			{			
				
				query.setParameter("targetUserId", Integer.parseInt(targetUserId));
				
			}
			if (notificationMedium.length() > 0)
			{		
				
				query.setParameter("notificationMedium", notificationMedium);	
				
			}
			if (layoutInfoText.length() > 0)
			{		
				
				query.setParameter("layoutInfoText", layoutInfoText);	
				
			}
			if (notificationSentTime.length() > 0)
			{
				
				
				query.setParameter("notificationSentTime", CommonUtil.getDBFormattedDateTimeStamp(notificationSentTime));			
			}

						if (taskInfoId >= -1)
			{
				query.setParameter("taskInfoId", taskInfoId);
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
	    	setParentFilterClauseParamsForTaskSentInfo(paramsMap, query);
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
			JsonArray taskSentInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskSentInfo taskSentInfo = (TaskSentInfo) it.next();
				JsonObject taskSentInfoDataObject = taskSentInfo.getDataObject(getDBSession());
				taskSentInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskSentInfo.getVwTxnStatus()));
				taskSentInfoList.add(taskSentInfoDataObject);
				doAfterSearchResultRowAddedImpl(taskSentInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from TaskSentInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (taskSentInfoIdsList != null && taskSentInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", taskSentInfoIdsList);
				}
								if (targetEntityId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("targetEntityId", Integer.parseInt(targetEntityId));
					
				}
				if (targetUserId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("targetUserId", Integer.parseInt(targetUserId));
					
				}
				if (notificationMedium.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("notificationMedium", notificationMedium);
					
					
					
					
				}
				if (layoutInfoText.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("layoutInfoText", layoutInfoText);
					
					
					
					
				}
				if (notificationSentTime.length() > 0)
				{
					
					
					countQuery.setParameter("notificationSentTime", CommonUtil.getDBFormattedDateTimeStamp(notificationSentTime));
					
					
					
					
					
					
					
					
					
				}

								if (taskInfoId >= -1)
				{
					countQuery.setParameter("taskInfoId", taskInfoId);
				}

				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForTaskSentInfo(paramsMap, countQuery);
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
			dataObject.add("taskSentInfoList",   taskSentInfoList);
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
				+ "   from TaskSentInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from TaskSentInfo E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentTaskSentInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveTaskSentInfoList(paramsMap);
	}
	public TaskSentInfo getTaskSentInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from TaskSentInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			TaskSentInfo taskSentInfo = (TaskSentInfo) it.next();
			return taskSentInfo;
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
		TaskSentInfo taskSentInfo = (TaskSentInfo)getManagedBean();
		JsonObject taskSentInfoDataObject = taskSentInfo.getDataObject(getDBSession());copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject);
		setManagedBean(taskSentInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData TaskSentInfoContollerBase");
			TaskSentInfo currentBean = (TaskSentInfo)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		currentBean.setTargetEntityId(1);currentBean.setTargetUserId(1);iFieldLength = 0;
		sFieldLength = "30";
		sStringTestData = "NotificationMedium".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setNotificationMedium(sStringTestData);iFieldLength = 0;
		sFieldLength = "1000";
		sStringTestData = "LayoutInfoText".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setLayoutInfoText(sStringTestData);
		
		currentBean.setNotificationSentTime(new Date());

		setManagedBean(currentBean);
		debugCode("Out setTestData TaskSentInfoContollerBase");
	}
	// end Test Data
	public void copyTaskSentInfoFromJson(TaskSentInfo taskSentInfo, JsonObject taskSentInfoDataObject)
	{
		copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject, false);
	}
	public void copyTaskSentInfoFromJson(TaskSentInfo taskSentInfo, JsonObject taskSentInfoDataObject, boolean excludePasswordFields)
	{	
				if(taskSentInfoDataObject.has("taskInfoId"))
		{
			Integer taskInfoId = taskSentInfoDataObject.get("taskInfoId").getAsInt();
			taskSentInfo.setTaskInfoId(taskInfoId);
		}if(taskSentInfoDataObject.has("targetEntityId"))
		{
			Integer targetEntityId = null;
			try
			{
			 	targetEntityId = taskSentInfoDataObject.get("targetEntityId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(targetEntityId != null)
			{
				taskSentInfo.setTargetEntityId(targetEntityId);
			}
		}if(taskSentInfoDataObject.has("targetUserId"))
		{
			Integer targetUserId = null;
			try
			{
			 	targetUserId = taskSentInfoDataObject.get("targetUserId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(targetUserId != null)
			{
				taskSentInfo.setTargetUserId(targetUserId);
			}
		}if(taskSentInfoDataObject.has("notificationMedium"))
		{
			String notificationMedium = taskSentInfoDataObject.get("notificationMedium").getAsString();
			taskSentInfo.setNotificationMedium(notificationMedium);
		}if(taskSentInfoDataObject.has("layoutInfoText"))
		{
			String layoutInfoText = taskSentInfoDataObject.get("layoutInfoText").getAsString();
			taskSentInfo.setLayoutInfoText(layoutInfoText);
		}
		
		if(taskSentInfoDataObject.has("notificationSentTime"))
		{
			String notificationSentTime = taskSentInfoDataObject.get("notificationSentTime").getAsString();
			if(notificationSentTime.length() > 0)
			{
				try
				{
					taskSentInfo.setNotificationSentTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(notificationSentTime));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid NotificationLastSentTime");
				}
			}
		}
		
	}
		
	public JsonObject createTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		return createTaskSentInfo(taskSentInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject taskSentInfoDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
				else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			taskSentInfoDataObject.addProperty("taskInfoId", userId);
		}

	}
	public JsonObject createTaskSentInfo(JsonObject taskSentInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		TaskSentInfo taskSentInfo = new TaskSentInfo();
		setLoginBasedValues(paramsInfoObj, taskSentInfoDataObject);
		Session session = getDBSession();				
		copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject);
		if(taskSentInfoDataObject.has("legacyRecordId"))
		{
			taskSentInfo.setLegacyRecordId(taskSentInfoDataObject.get("legacyRecordId").getAsInt());
		}
				taskSentInfo.setVwCreatedBy(createdBy);
		taskSentInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(taskSentInfo);
		setManagedBean("TaskSentInfoBean", taskSentInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		taskSentInfo = (TaskSentInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("taskSentInfoId", taskSentInfo.getTaskSentInfoId());
			JsonObject taskSentInfoObj = taskSentInfo.getDataObject(getDBSession());
			taskSentInfoObj.addProperty("nextAction", getActionForCurrentStatus(taskSentInfo.getVwTxnStatus()));
			dataObject.add("taskSentInfoDataObject", taskSentInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskSentInfoId = taskSentInfoDataObject.get("taskSentInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskSentInfoId", taskSentInfoId);
		JsonObject responseData = retrieveTaskSentInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Sent Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);
		if(taskSentInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(taskSentInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromTaskSentInfo(taskSentInfoDataObject);
		boolean excludePasswordFields = true;
		copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject, excludePasswordFields);setPrivateManagedBean(taskSentInfo);
		setManagedBean("TaskSentInfoBean", taskSentInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		taskSentInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("taskSentInfoId", taskSentInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
	}
	public JsonObject deleteTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskSentInfoId = taskSentInfoDataObject.get("taskSentInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskSentInfoId", taskSentInfoId);
		JsonObject responseData = retrieveTaskSentInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Sent Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);setPrivateManagedBean(taskSentInfo);
		setManagedBean("TaskSentInfo", taskSentInfo);
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
	public JsonObject fetchTaskSentInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		TaskSentInfo taskSentInfo = new TaskSentInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfo", taskSentInfo);
			doAfterTaskSentInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskSentInfo", taskSentInfo.getDataObject(getDBSession()));
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
	public JsonObject fetchTaskSentInfoTestData(int obj, JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		TaskSentInfo taskSentInfo = new TaskSentInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject);
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfo", taskSentInfo);
			doAfterTaskSentInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskSentInfo", taskSentInfo.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		TaskSentInfo taskSentInfo = new TaskSentInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject);	String attributeName = taskSentInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = taskSentInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfo", taskSentInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskSentInfo", taskSentInfo.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		TaskSentInfo taskSentInfo = new TaskSentInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskSentInfoFromJson(taskSentInfo, taskSentInfoDataObject);	
			String attributeName = taskSentInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfo", taskSentInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskSentInfo", taskSentInfo.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskSentInfoId = taskSentInfoDataObject.get("taskSentInfoId").getAsInt();
			String customEventName = taskSentInfoDataObject.get("customEventName").getAsString();
			TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfoBean", taskSentInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskSentInfo", taskSentInfo.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnTaskSentInfo(JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskSentInfoId = taskSentInfoDataObject.get("taskSentInfoId").getAsInt();
			String currentStatus = taskSentInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(taskSentInfoDataObject.has("currentAction"))
			{
				currentAction = taskSentInfoDataObject.get("currentAction").getAsString();
			}
			TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);
			setPrivateManagedBean(taskSentInfo);
			setManagedBean("TaskSentInfoBean", taskSentInfo);
			if(taskSentInfo.getIsRequestUnderProcesss() == 1)
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
				executeAction(taskSentInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(taskSentInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(taskSentInfo, "TaskSentInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedtaskSentInfoDataObject = taskSentInfo.getDataObject(getDBSession());
				updatedtaskSentInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskSentInfo.getVwTxnStatus()));
				dataObject.add("taskSentInfo", updatedtaskSentInfoDataObject);
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
		TaskSentInfo taskSentInfo = (TaskSentInfo) getManagedBean();
		String currentStatus = taskSentInfo.getVwTxnStatus();
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
	
	
	public JsonObject downloadTaskSentInfoData() throws Exception
	{
		return downloadTaskSentInfoData(null);
	}
	public JsonObject downloadTaskSentInfoData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "TaskSentInfo");
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
			headerName = "taskSentInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						cell = row.createCell(headerCellCount++);
			headerName = "taskInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
	        cell = row.createCell(headerCellCount++);
			headerName = "targetEntityId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "targetUserId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "notificationMedium";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "layoutInfoText";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "notificationSentTime";
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
			String hql = "From TaskSentInfo ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				TaskSentInfo taskSentInfo = (TaskSentInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer taskSentInfoId = taskSentInfo.getTaskSentInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(taskSentInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	Integer taskInfoId = taskSentInfo.getTaskInfoId();
				com.patientapp.bean.TaskInfo  taskInfoObj = null;
				if(taskInfoId > 0)
				{   
					cell.setCellValue(String.valueOf(taskInfoId));
					taskInfoObj = (com.patientapp.bean.TaskInfo) session.get(com.patientapp.bean.TaskInfo.class, taskInfoId);
				}   
		        
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer targetEntityId = taskSentInfo.getTargetEntityId();
				if(targetEntityId!=null)
				{
					cell.setCellValue(String.valueOf(targetEntityId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer targetUserId = taskSentInfo.getTargetUserId();
				if(targetUserId!=null)
				{
					cell.setCellValue(String.valueOf(targetUserId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String notificationMedium = taskSentInfo.getNotificationMedium();
				cell.setCellValue(notificationMedium);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String layoutInfoText = taskSentInfo.getLayoutInfoText();
				cell.setCellValue(layoutInfoText);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	
				
				Date notificationSentTime = taskSentInfo.getNotificationSentTime();
				if(notificationSentTime!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateTimeStampFormat(notificationSentTime));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "TaskSentInfo.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "TaskSentInfo.xls";
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
	public JsonObject uploadTaskSentInfoData(JsonObject taskSentInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = taskSentInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = taskSentInfoDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Task Sent Info Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		taskSentInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadTaskSentInfoData(workbook, taskSentInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Task Sent Info Data uploaded successfully.");
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
	public JsonObject uploadTaskSentInfoData(HSSFWorkbook workbook, JsonObject taskSentInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("TaskSentInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = taskSentInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = taskSentInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = taskSentInfoDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Task Sent Info Data uploaded successfully.");
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
			JsonObject taskSentInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject taskSentInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray taskSentInfoList = taskSentInfoListObj.get("taskSentInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadTaskSentInfoList(taskSentInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(taskSentInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(taskSentInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray taskSentInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject taskSentInfo = new JsonObject();
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
				JsonObject taskSentInfoUploadObj	= new JsonObject();
				taskSentInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					taskSentInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("taskSentInfoId"))
						{
							String taskSentInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(taskSentInfoId != null && taskSentInfoId.trim().length() > 0)
							{
								try
								{
									Integer iTaskSentInfoId = Integer.parseInt(taskSentInfoId);
									if(areSourceDestinationSame == 1)
									{
										TaskSentInfo taskSentInfoObj = (TaskSentInfo)session.get(TaskSentInfo.class, iTaskSentInfoId);
										if(taskSentInfoObj != null)
										{ 
											taskSentInfo.addProperty("taskSentInfoId", iTaskSentInfoId);
										}
										else
										{
											taskSentInfoUploadObj.addProperty("isDataFetched", 0);
											taskSentInfoUploadObj.addProperty("msg", "This Task Sent Info could not be uploaded as there appears to be some problem with the data(TaskSentInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										TaskSentInfo taskSentInfoObj = getTaskSentInfoByLegacyRecordId(session, iTaskSentInfoId);
										if(taskSentInfoObj != null)
										{ 
											taskSentInfo.addProperty("taskSentInfoId", taskSentInfoObj.getTaskSentInfoId());
										}
										taskSentInfo.addProperty("legacyRecordId", iTaskSentInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									taskSentInfoUploadObj.addProperty("isDataFetched", 0);
									taskSentInfoUploadObj.addProperty("msg", "This Task Sent Info could not be uploaded as there appears to be some problem with the data(TaskSentInfo Id). ");
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
							taskSentInfo.addProperty(parameterName, parameterValue);
						}
					}
					taskSentInfoUploadObj.add("dataObject", taskSentInfo);		    
					taskSentInfoList.add(taskSentInfoUploadObj);
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
			dataObject.add("taskSentInfoList", taskSentInfoList);
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
		if(previousRetryCountMap.has("TaskSentInfo") && previousRetryCountMap.get("TaskSentInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("TaskSentInfo").getAsInt();
		}
		if(retryCountMap.has("TaskSentInfo") && retryCountMap.get("TaskSentInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("TaskSentInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForTaskSentInfoList(JsonArray taskSentInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < taskSentInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject taskSentInfoDataObject = taskSentInfoList.get(i).getAsJsonObject();
			JsonObject taskSentInfo = taskSentInfoDataObject.get("dataObject").getAsJsonObject();
			if(taskSentInfoDataObject.has("retryUpload") && taskSentInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = taskSentInfoDataObject.get("retryUpload").getAsInt();
			}
			if(taskSentInfoDataObject.has("retryChildEntitiesUpload") && taskSentInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = taskSentInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(taskSentInfoDataObject.has("partialUploadUnderProcess") && taskSentInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = taskSentInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("TaskSentInfo", retryCount);
	}
	public JsonObject uploadTaskSentInfoList(JsonArray taskSentInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(taskSentInfoList);
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
	public JsonObject updateStatusMsg(JsonArray taskSentInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < taskSentInfoList.size(); i++)
			{
				JsonObject taskSentInfoDataObject = taskSentInfoList.get(i).getAsJsonObject();
				JsonObject taskSentInfo = taskSentInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = taskSentInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = taskSentInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = taskSentInfoDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray taskSentInfoList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < taskSentInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject taskSentInfoDataObject = taskSentInfoList.get(i).getAsJsonObject();
				JsonObject taskSentInfo = taskSentInfoDataObject.get("dataObject").getAsJsonObject();
				taskSentInfoDataObject.addProperty("retryUpload", 0);
				taskSentInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				taskSentInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl taskSentInfoImplObj = new com.patientapp.controller.forms.impl.TaskSentInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = taskSentInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, taskSentInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					taskSentInfoDataObject.addProperty("retryUpload", 1);
					taskSentInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					taskSentInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					taskSentInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int taskSentInfoId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !taskSentInfo.has("taskSentInfoId"))
				{
					taskSentInfo.addProperty("attributeNamePrefix", "");
					
					taskSentInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = taskSentInfoImplObj.getTaskSentInfoByLookupFields(session,  taskSentInfo);
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
						JsonObject taskSentInfoObject = responseData.get("taskSentInfoDataObject").getAsJsonObject();
						taskSentInfoId = taskSentInfoObject.get("taskSentInfoId").getAsInt();
						taskSentInfo.addProperty("taskSentInfoId", taskSentInfoId);
						updateEntity = true;
					}
				}
				else if(taskSentInfo.has("taskSentInfoId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = taskSentInfoImplObj.createTaskSentInfo(taskSentInfo);
				}
				else
				{
					responseData = taskSentInfoImplObj.updateTaskSentInfo(taskSentInfo);
				}
				taskSentInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					taskSentInfoId =-1;
					taskSentInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					taskSentInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				taskSentInfoId = responseData.get("taskSentInfoId").getAsInt();
				taskSentInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject taskSentInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;
				hasParamsForLookup = com.patientapp.controller.forms.impl.TaskInfoControllerImpl.hasParamsForLookup(taskSentInfo, "taskInfo");
		if(hasParamsForLookup==0)
		{
			taskSentInfo.addProperty("taskInfoId", -1);
		}
		else
		{
			dataObject = com.patientapp.controller.forms.impl.TaskInfoControllerImpl.getQueryParamsForLookupSearch(taskSentInfo, "taskInfo");
			requestParams = new JsonObject();
			if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
			{		
				requestParams = dataObject.get("requestParams").getAsJsonObject();
			}
			requestParams.addProperty("attributeNamePrefix", "taskInfo");
			requestParams.add("attributesInfo", taskSentInfo);
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
			JsonObject taskInfoResponseData = taskInfoImplObj.getTaskInfoByLookupFields(session,  requestParams);
			if(taskInfoResponseData!=null && taskInfoResponseData.has("success") && taskInfoResponseData.get("success").getAsInt()==1)
			{
				JsonObject taskInfoDataObject = taskInfoResponseData.get("taskInfoDataObject").getAsJsonObject();
				int taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
				taskSentInfo.addProperty("taskInfoId", taskInfoId);
			}
			else
			{
				int optionalForUpload = 0;
				
				if(optionalForUpload==1)
				{
					taskSentInfo.addProperty("taskInfoId", -1);
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
	public JsonObject getTaskSentInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From TaskSentInfo where 1 = 1  ";
			String countHql = "select count(*)  from TaskSentInfo where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Task Sent Info could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskSentInfo taskSentInfo = (TaskSentInfo) it.next();
				JsonObject taskSentInfoDataObject = taskSentInfo.getDataObject(session);
				dataObject.add("taskSentInfoDataObject", taskSentInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Task Sent Info could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Task Sent Info' could not be retrieved");
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
		else if(apiName.equals("getTaskSentInfoPropertyValue"))
			{
				JsonObject inputDataObject = getTaskSentInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getTaskSentInfo"))
			{
				JsonObject inputDataObject = getTaskSentInfo(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForTaskSentInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForTaskSentInfo"))
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
			Integer taskSentInfoId = requestReceivedParametersInfo.get("taskSentInfoId").getAsInt();
			TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);
			taskSentInfo.setIsRequestUnderProcesss(0);
			session.merge(taskSentInfo);
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
	public JsonObject getTaskSentInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskSentInfoId = inputForGetAPI.get("taskSentInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		TaskSentInfo taskSentInfo = (TaskSentInfo) session.get(TaskSentInfo.class, taskSentInfoId);
		taskSentInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getTaskSentInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskSentInfoId = inputForGetAPI.get("taskSentInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskSentInfoId", String.valueOf(taskSentInfoId));
		JsonObject taskSentInfoListObject = retrieveTaskSentInfoList(paramsMap);
		if(taskSentInfoListObject!=null && taskSentInfoListObject.has("success") && taskSentInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskSentInfoList = taskSentInfoListObject.get("taskSentInfoList").getAsJsonArray();
			if(taskSentInfoList.size()>0)
			{
				dataObject.add("taskSentInfo", taskSentInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Sent Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskSentInfoDataObject, TaskSentInfo taskSentInfo);
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
	public abstract void doAfterLookupRowSelectedImpl(TaskSentInfo taskSentInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(TaskSentInfo taskSentInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(TaskSentInfo taskSentInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
