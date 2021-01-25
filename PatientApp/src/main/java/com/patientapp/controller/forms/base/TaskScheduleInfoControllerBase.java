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

import com.patientapp.bean.TaskScheduleInfo;
import com.patientapp.controller.forms.base.TaskScheduleInfoLabel;
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
public abstract class TaskScheduleInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'TaskInfo' 
	 *		 * 'TargetEntityId' 
	 *		 * 'TargetUserId' 
	 *		 * 'NotificationMedium' 
	 *		 * 'NotificationLastSentTime' 
	 *		 * 'NextNotificationTime' 
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
	protected TaskScheduleInfoLabel TaskScheduleInfoLabelLocal = new TaskScheduleInfoLabel();
	protected TaskScheduleInfo TaskScheduleInfoLocal = new TaskScheduleInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public TaskScheduleInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public TaskScheduleInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public TaskScheduleInfoControllerBase()
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
		return "TaskScheduleInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((TaskScheduleInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((TaskScheduleInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((TaskScheduleInfo)obj).getTaskScheduleInfoId();
	}
	public String getManagedBeanName()
    {
		return "TaskScheduleInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "TaskScheduleInfoLabelBean";
    }
	protected Class<TaskScheduleInfo> setBeanClass()
	{
		return TaskScheduleInfo.class;
	}
	protected Class<TaskScheduleInfoLabel> setBeanLabelClass()
	{
		return TaskScheduleInfoLabel.class;
	}
	protected TaskScheduleInfo getLocalManagedBean()
    {
		return (TaskScheduleInfo)getManagedBean();
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
		/*TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		String areChangesEffected = taskScheduleInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			taskScheduleInfo.setAreChangesEffected("YES");			
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
		/*TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		String areChangesEffected = taskScheduleInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			taskScheduleInfo.setAreChangesEffected("NO");			
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
		/*TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		String areChangesEffected = taskScheduleInfo.getAreChangesEffected();
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
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		String sCurrentStatus = taskScheduleInfo.getVwTxnStatus();
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
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("notificationMedium".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(taskScheduleInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterTaskScheduleInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				if(initParamsInfo.has("taskInfo") && initParamsInfo.get("taskInfo").isJsonNull()==false)
		{
			Integer taskInfoId = initParamsInfo.get("taskInfoId").getAsInt();
			taskScheduleInfo.setTaskInfoId(taskInfoId);
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
			taskScheduleInfo.setTargetEntityId(targetEntityId);
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
			taskScheduleInfo.setTargetUserId(targetUserId);
		}if(initParamsInfo.has("notificationMedium") && initParamsInfo.get("notificationMedium").isJsonNull()==false)
		{
			String notificationMedium = initParamsInfo.get("notificationMedium").getAsString();
			taskScheduleInfo.setNotificationMedium(notificationMedium);			
		}
		
		if(initParamsInfo.has("notificationLastSentTime") && initParamsInfo.get("notificationLastSentTime").isJsonNull()==false)
		{
			String notificationLastSentTime = initParamsInfo.get("notificationLastSentTime").getAsString();
			if(notificationLastSentTime.length() > 0)
			{
				try
				{
					taskScheduleInfo.setNotificationLastSentTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(notificationLastSentTime));
				}
				catch(Exception e)
				{
				}
			}
		}
		
		if(initParamsInfo.has("nextNotificationTime") && initParamsInfo.get("nextNotificationTime").isJsonNull()==false)
		{
			String nextNotificationTime = initParamsInfo.get("nextNotificationTime").getAsString();
			if(nextNotificationTime.length() > 0)
			{
				try
				{
					taskScheduleInfo.setNextNotificationTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(nextNotificationTime));
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
		doAfterEntityLoadedImpl(taskScheduleInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();  
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
		doAfterLookupRowSelectedImpl(taskScheduleInfo, attributeName);
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
			TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "TaskScheduleInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() TaskScheduleInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (TaskScheduleInfoSearch)getManagedBean("TaskScheduleInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getTaskInfo()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.gettaskInfoFieldName(),searchBeanLocal.getTaskInfo());
			}	
			if (isExists(searchBeanLocal.getTargetEntityId()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.gettargetEntityIdFieldName(),searchBeanLocal.getTargetEntityId());
			}	
			if (isExists(searchBeanLocal.getTargetUserId()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.gettargetUserIdFieldName(),searchBeanLocal.getTargetUserId());
			}	
			if (isExists(searchBeanLocal.getNotificationMedium()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.getnotificationMediumFieldName(),searchBeanLocal.getNotificationMedium());
			}	
			if (isExists(searchBeanLocal.getNotificationLastSentTime()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.getnotificationLastSentTimeFieldName(),searchBeanLocal.getNotificationLastSentTime());
			}	
			if (isExists(searchBeanLocal.getNextNotificationTime()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.getnextNotificationTimeFieldName(),searchBeanLocal.getNextNotificationTime());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(TaskScheduleInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() TaskScheduleInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues TaskScheduleInfoContollerBase");
		targetBean = (TaskScheduleInfo)targetBean;((TaskScheduleInfo)targetBean).setTaskScheduleInfoId(((TaskScheduleInfo)sourceBean).getTaskScheduleInfoId());
				((TaskScheduleInfo)targetBean).setTargetEntityId(((TaskScheduleInfo)sourceBean).getTargetEntityId());
		((TaskScheduleInfo)targetBean).setTargetUserId(((TaskScheduleInfo)sourceBean).getTargetUserId());
		((TaskScheduleInfo)targetBean).setNotificationMedium(((TaskScheduleInfo)sourceBean).getNotificationMedium());
		((TaskScheduleInfo)targetBean).setNotificationLastSentTime(((TaskScheduleInfo)sourceBean).getNotificationLastSentTime());
		((TaskScheduleInfo)targetBean).setNextNotificationTime(((TaskScheduleInfo)sourceBean).getNextNotificationTime());

				((TaskScheduleInfo)targetBean).setTaskInfoId(((TaskScheduleInfo)sourceBean).getTaskInfoId());

				/*if (bSetAsManagedBean)
		{			
			TaskInfo TaskInfoLocal = (TaskInfo)(( TaskScheduleInfo)sourceBean).getTaskInfo();
			setManagedBean("TaskInfoBean", TaskInfoLocal);
		}*/

		doAfterSetValues();
		debugCode("Out setValues TaskScheduleInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.TaskScheduleInfo entityBean = (TaskScheduleInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("taskScheduleInfoId")) 
	 	{
			return entityBean.getTaskScheduleInfoId();
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
	 	if (sFieldName.equalsIgnoreCase("NotificationLastSentTime")) 
	 	{
			return entityBean.getNotificationLastSentTime();
		}
	 	if (sFieldName.equalsIgnoreCase("NextNotificationTime")) 
	 	{
			return entityBean.getNextNotificationTime();
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
		debugCode("In doEnrichSystemValues(String sAction) TaskScheduleInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((TaskScheduleInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((TaskScheduleInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((TaskScheduleInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((TaskScheduleInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((TaskScheduleInfo) localManagedBean).setVwTxnStatus("CREATED");
			((TaskScheduleInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((TaskScheduleInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((TaskScheduleInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) TaskScheduleInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) TaskScheduleInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) TaskScheduleInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations TaskScheduleInfoControllerBase");
		//NG: Important comment
		//managedBean = (TaskScheduleInfo) getManagedBean();
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
		debugCode("Out doValidations TaskScheduleInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation TaskScheduleInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation TaskScheduleInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation TaskScheduleInfoControllerBase");
				
		String sFieldValue5 = ((TaskScheduleInfo) localManagedBean).getNotificationMedium();if (!isExists(sFieldValue5,localMasters.getNotificationMedium())) addAllowedValuesResponse(TaskScheduleInfoLabelLocal.getnotificationMediumFieldName());

		debugCode("Out doAllowedValuesValidation TaskScheduleInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation TaskScheduleInfoControllerBase");
				
		Integer sFieldValue3 = ((TaskScheduleInfo) localManagedBean).getTargetEntityId();		Integer sFieldValue4 = ((TaskScheduleInfo) localManagedBean).getTargetUserId();		String sFieldValue5 = ((TaskScheduleInfo) localManagedBean).getNotificationMedium();
		
		Date sFieldValue6 = ((TaskScheduleInfo) localManagedBean).getNotificationLastSentTime();
		
		Date sFieldValue7 = ((TaskScheduleInfo) localManagedBean).getNextNotificationTime();

				Integer iFieldValue2 = ((TaskScheduleInfo) localManagedBean).getTaskInfoId();

				if (!isExists(sFieldValue3)) addMandatoryResponse(TaskScheduleInfoLabelLocal.gettargetEntityIdFieldName());
		if (!isExists(sFieldValue4)) addMandatoryResponse(TaskScheduleInfoLabelLocal.gettargetUserIdFieldName());
		if (!isExists(sFieldValue5)) addMandatoryResponse(TaskScheduleInfoLabelLocal.getnotificationMediumFieldName());
		if (!isExists(sFieldValue6)) addMandatoryResponse(TaskScheduleInfoLabelLocal.getnotificationLastSentTimeFieldName());
		if (!isExists(sFieldValue7)) addMandatoryResponse(TaskScheduleInfoLabelLocal.getnextNotificationTimeFieldName());

				if (iFieldValue2 <= 0) addMandatoryResponse(TaskScheduleInfoLabelLocal.gettaskInfoFieldName());

		debugCode("Out doMandatoryValidation TaskScheduleInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation TaskScheduleInfoControllerBase");
				
		Integer sFieldValue3 = ((TaskScheduleInfo) localManagedBean).getTargetEntityId();		Integer sFieldValue4 = ((TaskScheduleInfo) localManagedBean).getTargetUserId();		String sFieldValue5 = ((TaskScheduleInfo) localManagedBean).getNotificationMedium();
		
		Date sFieldValue6 = ((TaskScheduleInfo) localManagedBean).getNotificationLastSentTime();
		
		Date sFieldValue7 = ((TaskScheduleInfo) localManagedBean).getNextNotificationTime();

				Integer iFieldValue2 = ((TaskScheduleInfo) localManagedBean).getTaskInfoId();

				if (!isLengthAllowed(sFieldValue3,"10")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.gettargetEntityIdFieldName(),"10");
		if (!isLengthAllowed(sFieldValue4,"10")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.gettargetUserIdFieldName(),"10");
		if (!isLengthAllowed(sFieldValue5,"30")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.getnotificationMediumFieldName(),"30");
		if (!isLengthAllowed(sFieldValue6,"50")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.getnotificationLastSentTimeFieldName(),"50");
		if (!isLengthAllowed(sFieldValue7,"50")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.getnextNotificationTimeFieldName(),"50");

				//if (!isLengthAllowed(iFieldValue2,"")) addMaxLengthResponse(TaskScheduleInfoLabelLocal.gettaskInfoFieldName(),"");

		debugCode("Out doLengthValidation TaskScheduleInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation TaskScheduleInfoControllerBase");
		debugCode("Out doDataTypeValidation TaskScheduleInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation TaskScheduleInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation TaskScheduleInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel TaskScheduleInfoContollerBase");
		String sLabel = new TaskScheduleInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel TaskScheduleInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int taskScheduleInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("taskScheduleInfoId", taskScheduleInfoId);
			JsonObject responseData = retrieveTaskScheduleInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Task Schedule Info' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject taskScheduleInfoDataObject = responseData.get("taskScheduleInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, taskScheduleInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Task Schedule Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveTaskScheduleInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer taskScheduleInfoId = -1;
		if(requestParams.has("taskScheduleInfoId"))
		{
			taskScheduleInfoId = requestParams.get("taskScheduleInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("taskScheduleInfoId", String.valueOf(taskScheduleInfoId));
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
		String notificationLastSentTime = "";
		if(requestParams.has("notificationLastSentTime"))
		{
			paramsMap.put("notificationLastSentTime", requestParams.get("notificationLastSentTime").getAsString());
		}
		String nextNotificationTime = "";
		if(requestParams.has("nextNotificationTime"))
		{
			paramsMap.put("nextNotificationTime", requestParams.get("nextNotificationTime").getAsString());
		}

				Integer taskInfoId = -1;
		if(requestParams.has("taskInfoId"))
		{
			paramsMap.put("taskInfoId", requestParams.get("taskInfoId").getAsString());
		}
JsonObject taskScheduleInfoListObject = retrieveTaskScheduleInfoList(paramsMap);
		if(taskScheduleInfoListObject!=null && taskScheduleInfoListObject.has("success") && taskScheduleInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskScheduleInfoList = taskScheduleInfoListObject.get("taskScheduleInfoList").getAsJsonArray();
			if(taskScheduleInfoList.size()>0)
			{
				dataObject.add("taskScheduleInfoDataObject", taskScheduleInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Schedule Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getTaskScheduleInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
			String notificationLastSentTime = "";
			if(searchParams.has("notificationLastSentTime"))
			{
				paramsMap.put("notificationLastSentTime", searchParams.get("notificationLastSentTime").getAsString());
			}
			String nextNotificationTime = "";
			if(searchParams.has("nextNotificationTime"))
			{
				paramsMap.put("nextNotificationTime", searchParams.get("nextNotificationTime").getAsString());
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
			JsonObject taskScheduleInfoListObject = retrieveTaskScheduleInfoList(paramsMap);
			if(taskScheduleInfoListObject!=null && taskScheduleInfoListObject.has("success") && taskScheduleInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray taskScheduleInfoList = taskScheduleInfoListObject.get("taskScheduleInfoList").getAsJsonArray();
				if(taskScheduleInfoList.size()>0)
				{
					dataObject.add("taskScheduleInfo", taskScheduleInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Task Schedule Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getTaskScheduleInfoInJson(int taskScheduleInfoId)
	{
		JsonObject TaskScheduleInfoData = null;
		List<Integer> taskScheduleInfoIdsList = new ArrayList<>();
		taskScheduleInfoIdsList.add(taskScheduleInfoId);
		JsonArray taskScheduleInfoList = getTaskScheduleInfoListInJson(taskScheduleInfoIdsList);
		if(taskScheduleInfoList!=null && taskScheduleInfoList.size()>0)
		{
			TaskScheduleInfoData = taskScheduleInfoList.get(0).getAsJsonObject();
		}
		return TaskScheduleInfoData;
	}
	public JsonArray getTaskScheduleInfoListInJson(List<Integer> taskScheduleInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray taskScheduleInfoObjectsList = new JsonArray();
		JsonObject taskScheduleInfoListObject = retrieveTaskScheduleInfoList(paramsMap, taskScheduleInfoIdsList);
		if(taskScheduleInfoListObject!=null && taskScheduleInfoListObject.has("success") && taskScheduleInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskScheduleInfoList = taskScheduleInfoListObject.get("taskScheduleInfoList").getAsJsonArray();
			for (int i =0; i< taskScheduleInfoList.size(); i++)
			{
				JsonObject taskScheduleInfoDataObject = taskScheduleInfoList.get(i).getAsJsonObject();
				int taskScheduleInfoId = taskScheduleInfoDataObject.get("taskScheduleInfoId").getAsInt();
				
				taskScheduleInfoObjectsList.add(taskScheduleInfoDataObject);
			}
		}
		return taskScheduleInfoObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray taskScheduleInfoList)
	{
		String errorMessage = "taskScheduleInfoList: \n";
		for (int i =0; i< taskScheduleInfoList.size(); i++)
		{
			JsonObject taskScheduleInfoDataObject = taskScheduleInfoList.get(i).getAsJsonObject();
			JsonObject taskScheduleInfo = taskScheduleInfoDataObject.get("dataObject").getAsJsonObject();if(!taskScheduleInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "taskScheduleInfo could not be uploaded verify data \n"; 
			}
			else if(taskScheduleInfoDataObject.has("isSuccessfullyUploaded") 
					&& taskScheduleInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += taskScheduleInfoDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("TaskScheduleInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND taskScheduleInfoId = :taskScheduleInfoId ";
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
		else if("TaskScheduleInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskScheduleInfoId", userId);
		}
				else if("TaskInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskInfoId", userId);
		}

	}
	public String getParentFilterClauseForTaskScheduleInfo(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForTaskScheduleInfo(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveTaskScheduleInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> taskScheduleInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("taskScheduleInfoId"))
		{
			int taskScheduleInfoId = Integer.parseInt(paramsMap.get("taskScheduleInfoId"));
			if(taskScheduleInfoId>0)
			{
				taskScheduleInfoIdsList.add(taskScheduleInfoId);
			}
		}
		return retrieveTaskScheduleInfoList(paramsMap, taskScheduleInfoIdsList);
	}
	public JsonObject retrieveTaskScheduleInfoList(java.util.Map<String, String> paramsMap, List<Integer> taskScheduleInfoIdsList)
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
			String notificationLastSentTime = paramsMap.get("notificationLastSentTime");
			if(notificationLastSentTime==null)
			{
				notificationLastSentTime = "";
			}
			String nextNotificationTime = paramsMap.get("nextNotificationTime");
			if(nextNotificationTime==null)
			{
				nextNotificationTime = "";
			}

						Integer taskInfoId = -2;
	    	if(paramsMap.containsKey("taskInfoId"))
	    	{
				taskInfoId = Integer.parseInt(paramsMap.get("taskInfoId"));
	    	}

			String hql = "FROM TaskScheduleInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String taskScheduleInfoIdFilterClass = "";
			if (taskScheduleInfoIdsList != null && taskScheduleInfoIdsList.size() > 0)
			{
				taskScheduleInfoIdFilterClass = " AND taskScheduleInfoId in (:idsList) ";
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
			String notificationLastSentTimeFilterClass = "";
			if (notificationLastSentTime.length() > 0)
			{
				
				
				notificationLastSentTimeFilterClass = " AND notificationLastSentTime = :notificationLastSentTime ";			
			}
			String nextNotificationTimeFilterClass = "";
			if (nextNotificationTime.length() > 0)
			{
				
				
				nextNotificationTimeFilterClass = " AND nextNotificationTime = :nextNotificationTime ";			
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
			String parentFilterClause  = getParentFilterClauseForTaskScheduleInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					taskScheduleInfoIdFilterClass +
										taskInfoFilterClass +
					targetEntityIdFilterClass +
					targetUserIdFilterClass +
					notificationMediumFilterClass +
					notificationLastSentTimeFilterClass +
					nextNotificationTimeFilterClass +

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
			if (taskScheduleInfoIdsList != null && taskScheduleInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", taskScheduleInfoIdsList);
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
			if (notificationLastSentTime.length() > 0)
			{
				
				
				query.setParameter("notificationLastSentTime", CommonUtil.getDBFormattedDateTimeStamp(notificationLastSentTime));			
			}
			if (nextNotificationTime.length() > 0)
			{
				
				
				query.setParameter("nextNotificationTime", CommonUtil.getDBFormattedDateTimeStamp(nextNotificationTime));			
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
	    	setParentFilterClauseParamsForTaskScheduleInfo(paramsMap, query);
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
			JsonArray taskScheduleInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) it.next();
				JsonObject taskScheduleInfoDataObject = taskScheduleInfo.getDataObject(getDBSession());
				taskScheduleInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskScheduleInfo.getVwTxnStatus()));
				taskScheduleInfoList.add(taskScheduleInfoDataObject);
				doAfterSearchResultRowAddedImpl(taskScheduleInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from TaskScheduleInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (taskScheduleInfoIdsList != null && taskScheduleInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", taskScheduleInfoIdsList);
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
				if (notificationLastSentTime.length() > 0)
				{
					
					
					countQuery.setParameter("notificationLastSentTime", CommonUtil.getDBFormattedDateTimeStamp(notificationLastSentTime));
					
					
					
					
					
					
					
					
					
				}
				if (nextNotificationTime.length() > 0)
				{
					
					
					countQuery.setParameter("nextNotificationTime", CommonUtil.getDBFormattedDateTimeStamp(nextNotificationTime));
					
					
					
					
					
					
					
					
					
				}

								if (taskInfoId >= -1)
				{
					countQuery.setParameter("taskInfoId", taskInfoId);
				}

				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForTaskScheduleInfo(paramsMap, countQuery);
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
			dataObject.add("taskScheduleInfoList",   taskScheduleInfoList);
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
				+ "   from TaskScheduleInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from TaskScheduleInfo E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentTaskScheduleInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveTaskScheduleInfoList(paramsMap);
	}
	public TaskScheduleInfo getTaskScheduleInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from TaskScheduleInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) it.next();
			return taskScheduleInfo;
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
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo)getManagedBean();
		JsonObject taskScheduleInfoDataObject = taskScheduleInfo.getDataObject(getDBSession());copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject);
		setManagedBean(taskScheduleInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData TaskScheduleInfoContollerBase");
			TaskScheduleInfo currentBean = (TaskScheduleInfo)getManagedBean();
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
		currentBean.setNotificationMedium(sStringTestData);
		
		currentBean.setNotificationLastSentTime(new Date());
		
		currentBean.setNextNotificationTime(new Date());

		setManagedBean(currentBean);
		debugCode("Out setTestData TaskScheduleInfoContollerBase");
	}
	// end Test Data
	public void copyTaskScheduleInfoFromJson(TaskScheduleInfo taskScheduleInfo, JsonObject taskScheduleInfoDataObject)
	{
		copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject, false);
	}
	public void copyTaskScheduleInfoFromJson(TaskScheduleInfo taskScheduleInfo, JsonObject taskScheduleInfoDataObject, boolean excludePasswordFields)
	{	
				if(taskScheduleInfoDataObject.has("taskInfoId"))
		{
			Integer taskInfoId = taskScheduleInfoDataObject.get("taskInfoId").getAsInt();
			taskScheduleInfo.setTaskInfoId(taskInfoId);
		}if(taskScheduleInfoDataObject.has("targetEntityId"))
		{
			Integer targetEntityId = null;
			try
			{
			 	targetEntityId = taskScheduleInfoDataObject.get("targetEntityId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(targetEntityId != null)
			{
				taskScheduleInfo.setTargetEntityId(targetEntityId);
			}
		}if(taskScheduleInfoDataObject.has("targetUserId"))
		{
			Integer targetUserId = null;
			try
			{
			 	targetUserId = taskScheduleInfoDataObject.get("targetUserId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(targetUserId != null)
			{
				taskScheduleInfo.setTargetUserId(targetUserId);
			}
		}if(taskScheduleInfoDataObject.has("notificationMedium"))
		{
			String notificationMedium = taskScheduleInfoDataObject.get("notificationMedium").getAsString();
			taskScheduleInfo.setNotificationMedium(notificationMedium);
		}
		
		if(taskScheduleInfoDataObject.has("notificationLastSentTime"))
		{
			String notificationLastSentTime = taskScheduleInfoDataObject.get("notificationLastSentTime").getAsString();
			if(notificationLastSentTime.length() > 0)
			{
				try
				{
					taskScheduleInfo.setNotificationLastSentTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(notificationLastSentTime));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid NotificationLastSentTime");
				}
			}
		}
		
		if(taskScheduleInfoDataObject.has("nextNotificationTime"))
		{
			String nextNotificationTime = taskScheduleInfoDataObject.get("nextNotificationTime").getAsString();
			if(nextNotificationTime.length() > 0)
			{
				try
				{
					taskScheduleInfo.setNextNotificationTime(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(nextNotificationTime));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid NextNotificationTime");
				}
			}
		}
		
	}
		
	public JsonObject createTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		return createTaskScheduleInfo(taskScheduleInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject taskScheduleInfoDataObject)
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
			taskScheduleInfoDataObject.addProperty("taskInfoId", userId);
		}

	}
	public JsonObject createTaskScheduleInfo(JsonObject taskScheduleInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
		setLoginBasedValues(paramsInfoObj, taskScheduleInfoDataObject);
		Session session = getDBSession();				
		copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject);
		if(taskScheduleInfoDataObject.has("legacyRecordId"))
		{
			taskScheduleInfo.setLegacyRecordId(taskScheduleInfoDataObject.get("legacyRecordId").getAsInt());
		}
				taskScheduleInfo.setVwCreatedBy(createdBy);
		taskScheduleInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(taskScheduleInfo);
		setManagedBean("TaskScheduleInfoBean", taskScheduleInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		taskScheduleInfo = (TaskScheduleInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("taskScheduleInfoId", taskScheduleInfo.getTaskScheduleInfoId());
			JsonObject taskScheduleInfoObj = taskScheduleInfo.getDataObject(getDBSession());
			taskScheduleInfoObj.addProperty("nextAction", getActionForCurrentStatus(taskScheduleInfo.getVwTxnStatus()));
			dataObject.add("taskScheduleInfoDataObject", taskScheduleInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskScheduleInfoId = taskScheduleInfoDataObject.get("taskScheduleInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskScheduleInfoId", taskScheduleInfoId);
		JsonObject responseData = retrieveTaskScheduleInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Schedule Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);
		if(taskScheduleInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(taskScheduleInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromTaskScheduleInfo(taskScheduleInfoDataObject);
		boolean excludePasswordFields = true;
		copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject, excludePasswordFields);setPrivateManagedBean(taskScheduleInfo);
		setManagedBean("TaskScheduleInfoBean", taskScheduleInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		taskScheduleInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("taskScheduleInfoId", taskScheduleInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
	}
	public JsonObject deleteTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskScheduleInfoId = taskScheduleInfoDataObject.get("taskScheduleInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskScheduleInfoId", taskScheduleInfoId);
		JsonObject responseData = retrieveTaskScheduleInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Schedule Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);setPrivateManagedBean(taskScheduleInfo);
		setManagedBean("TaskScheduleInfo", taskScheduleInfo);
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
	public JsonObject fetchTaskScheduleInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfo", taskScheduleInfo);
			doAfterTaskScheduleInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfo.getDataObject(getDBSession()));
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
	public JsonObject fetchTaskScheduleInfoTestData(int obj, JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject);
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfo", taskScheduleInfo);
			doAfterTaskScheduleInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfo.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject);	String attributeName = taskScheduleInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = taskScheduleInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfo", taskScheduleInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfo.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		TaskScheduleInfo taskScheduleInfo = new TaskScheduleInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskScheduleInfoFromJson(taskScheduleInfo, taskScheduleInfoDataObject);	
			String attributeName = taskScheduleInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfo", taskScheduleInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfo.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskScheduleInfoId = taskScheduleInfoDataObject.get("taskScheduleInfoId").getAsInt();
			String customEventName = taskScheduleInfoDataObject.get("customEventName").getAsString();
			TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfoBean", taskScheduleInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfo.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnTaskScheduleInfo(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskScheduleInfoId = taskScheduleInfoDataObject.get("taskScheduleInfoId").getAsInt();
			String currentStatus = taskScheduleInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(taskScheduleInfoDataObject.has("currentAction"))
			{
				currentAction = taskScheduleInfoDataObject.get("currentAction").getAsString();
			}
			TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);
			setPrivateManagedBean(taskScheduleInfo);
			setManagedBean("TaskScheduleInfoBean", taskScheduleInfo);
			if(taskScheduleInfo.getIsRequestUnderProcesss() == 1)
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
				executeAction(taskScheduleInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(taskScheduleInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(taskScheduleInfo, "TaskScheduleInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedtaskScheduleInfoDataObject = taskScheduleInfo.getDataObject(getDBSession());
				updatedtaskScheduleInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskScheduleInfo.getVwTxnStatus()));
				dataObject.add("taskScheduleInfo", updatedtaskScheduleInfoDataObject);
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
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) getManagedBean();
		String currentStatus = taskScheduleInfo.getVwTxnStatus();
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
	
	
	public JsonObject downloadTaskScheduleInfoData() throws Exception
	{
		return downloadTaskScheduleInfoData(null);
	}
	public JsonObject downloadTaskScheduleInfoData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "TaskScheduleInfo");
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
			headerName = "taskScheduleInfoId";
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
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "notificationLastSentTime";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "nextNotificationTime";
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
			String hql = "From TaskScheduleInfo ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer taskScheduleInfoId = taskScheduleInfo.getTaskScheduleInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(taskScheduleInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	Integer taskInfoId = taskScheduleInfo.getTaskInfoId();
				com.patientapp.bean.TaskInfo  taskInfoObj = null;
				if(taskInfoId > 0)
				{   
					cell.setCellValue(String.valueOf(taskInfoId));
					taskInfoObj = (com.patientapp.bean.TaskInfo) session.get(com.patientapp.bean.TaskInfo.class, taskInfoId);
				}   
		        
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer targetEntityId = taskScheduleInfo.getTargetEntityId();
				if(targetEntityId!=null)
				{
					cell.setCellValue(String.valueOf(targetEntityId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer targetUserId = taskScheduleInfo.getTargetUserId();
				if(targetUserId!=null)
				{
					cell.setCellValue(String.valueOf(targetUserId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String notificationMedium = taskScheduleInfo.getNotificationMedium();
				cell.setCellValue(notificationMedium);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	
				
				Date notificationLastSentTime = taskScheduleInfo.getNotificationLastSentTime();
				if(notificationLastSentTime!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateTimeStampFormat(notificationLastSentTime));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	
				
				Date nextNotificationTime = taskScheduleInfo.getNextNotificationTime();
				if(nextNotificationTime!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateTimeStampFormat(nextNotificationTime));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "TaskScheduleInfo.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "TaskScheduleInfo.xls";
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
	public JsonObject uploadTaskScheduleInfoData(JsonObject taskScheduleInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = taskScheduleInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = taskScheduleInfoDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Task Schedule Info Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		taskScheduleInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadTaskScheduleInfoData(workbook, taskScheduleInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Task Schedule Info Data uploaded successfully.");
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
	public JsonObject uploadTaskScheduleInfoData(HSSFWorkbook workbook, JsonObject taskScheduleInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("TaskScheduleInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = taskScheduleInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = taskScheduleInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = taskScheduleInfoDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Task Schedule Info Data uploaded successfully.");
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
			JsonObject taskScheduleInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject taskScheduleInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray taskScheduleInfoList = taskScheduleInfoListObj.get("taskScheduleInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadTaskScheduleInfoList(taskScheduleInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(taskScheduleInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(taskScheduleInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray taskScheduleInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject taskScheduleInfo = new JsonObject();
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
				JsonObject taskScheduleInfoUploadObj	= new JsonObject();
				taskScheduleInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					taskScheduleInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("taskScheduleInfoId"))
						{
							String taskScheduleInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(taskScheduleInfoId != null && taskScheduleInfoId.trim().length() > 0)
							{
								try
								{
									Integer iTaskScheduleInfoId = Integer.parseInt(taskScheduleInfoId);
									if(areSourceDestinationSame == 1)
									{
										TaskScheduleInfo taskScheduleInfoObj = (TaskScheduleInfo)session.get(TaskScheduleInfo.class, iTaskScheduleInfoId);
										if(taskScheduleInfoObj != null)
										{ 
											taskScheduleInfo.addProperty("taskScheduleInfoId", iTaskScheduleInfoId);
										}
										else
										{
											taskScheduleInfoUploadObj.addProperty("isDataFetched", 0);
											taskScheduleInfoUploadObj.addProperty("msg", "This Task Schedule Info could not be uploaded as there appears to be some problem with the data(TaskScheduleInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										TaskScheduleInfo taskScheduleInfoObj = getTaskScheduleInfoByLegacyRecordId(session, iTaskScheduleInfoId);
										if(taskScheduleInfoObj != null)
										{ 
											taskScheduleInfo.addProperty("taskScheduleInfoId", taskScheduleInfoObj.getTaskScheduleInfoId());
										}
										taskScheduleInfo.addProperty("legacyRecordId", iTaskScheduleInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									taskScheduleInfoUploadObj.addProperty("isDataFetched", 0);
									taskScheduleInfoUploadObj.addProperty("msg", "This Task Schedule Info could not be uploaded as there appears to be some problem with the data(TaskScheduleInfo Id). ");
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
							taskScheduleInfo.addProperty(parameterName, parameterValue);
						}
					}
					taskScheduleInfoUploadObj.add("dataObject", taskScheduleInfo);		    
					taskScheduleInfoList.add(taskScheduleInfoUploadObj);
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
			dataObject.add("taskScheduleInfoList", taskScheduleInfoList);
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
		if(previousRetryCountMap.has("TaskScheduleInfo") && previousRetryCountMap.get("TaskScheduleInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("TaskScheduleInfo").getAsInt();
		}
		if(retryCountMap.has("TaskScheduleInfo") && retryCountMap.get("TaskScheduleInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("TaskScheduleInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForTaskScheduleInfoList(JsonArray taskScheduleInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < taskScheduleInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject taskScheduleInfoDataObject = taskScheduleInfoList.get(i).getAsJsonObject();
			JsonObject taskScheduleInfo = taskScheduleInfoDataObject.get("dataObject").getAsJsonObject();
			if(taskScheduleInfoDataObject.has("retryUpload") && taskScheduleInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = taskScheduleInfoDataObject.get("retryUpload").getAsInt();
			}
			if(taskScheduleInfoDataObject.has("retryChildEntitiesUpload") && taskScheduleInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = taskScheduleInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(taskScheduleInfoDataObject.has("partialUploadUnderProcess") && taskScheduleInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = taskScheduleInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("TaskScheduleInfo", retryCount);
	}
	public JsonObject uploadTaskScheduleInfoList(JsonArray taskScheduleInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(taskScheduleInfoList);
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
	public JsonObject updateStatusMsg(JsonArray taskScheduleInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < taskScheduleInfoList.size(); i++)
			{
				JsonObject taskScheduleInfoDataObject = taskScheduleInfoList.get(i).getAsJsonObject();
				JsonObject taskScheduleInfo = taskScheduleInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = taskScheduleInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = taskScheduleInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = taskScheduleInfoDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray taskScheduleInfoList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < taskScheduleInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject taskScheduleInfoDataObject = taskScheduleInfoList.get(i).getAsJsonObject();
				JsonObject taskScheduleInfo = taskScheduleInfoDataObject.get("dataObject").getAsJsonObject();
				taskScheduleInfoDataObject.addProperty("retryUpload", 0);
				taskScheduleInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				taskScheduleInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl taskScheduleInfoImplObj = new com.patientapp.controller.forms.impl.TaskScheduleInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = taskScheduleInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, taskScheduleInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					taskScheduleInfoDataObject.addProperty("retryUpload", 1);
					taskScheduleInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					taskScheduleInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					taskScheduleInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int taskScheduleInfoId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !taskScheduleInfo.has("taskScheduleInfoId"))
				{
					taskScheduleInfo.addProperty("attributeNamePrefix", "");
					
					taskScheduleInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = taskScheduleInfoImplObj.getTaskScheduleInfoByLookupFields(session,  taskScheduleInfo);
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
						JsonObject taskScheduleInfoObject = responseData.get("taskScheduleInfoDataObject").getAsJsonObject();
						taskScheduleInfoId = taskScheduleInfoObject.get("taskScheduleInfoId").getAsInt();
						taskScheduleInfo.addProperty("taskScheduleInfoId", taskScheduleInfoId);
						updateEntity = true;
					}
				}
				else if(taskScheduleInfo.has("taskScheduleInfoId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = taskScheduleInfoImplObj.createTaskScheduleInfo(taskScheduleInfo);
				}
				else
				{
					responseData = taskScheduleInfoImplObj.updateTaskScheduleInfo(taskScheduleInfo);
				}
				taskScheduleInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					taskScheduleInfoId =-1;
					taskScheduleInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					taskScheduleInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				taskScheduleInfoId = responseData.get("taskScheduleInfoId").getAsInt();
				taskScheduleInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject taskScheduleInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;
				hasParamsForLookup = com.patientapp.controller.forms.impl.TaskInfoControllerImpl.hasParamsForLookup(taskScheduleInfo, "taskInfo");
		if(hasParamsForLookup==0)
		{
			taskScheduleInfo.addProperty("taskInfoId", -1);
		}
		else
		{
			dataObject = com.patientapp.controller.forms.impl.TaskInfoControllerImpl.getQueryParamsForLookupSearch(taskScheduleInfo, "taskInfo");
			requestParams = new JsonObject();
			if(dataObject!=null && dataObject.has("success") && dataObject.get("success").getAsInt()==1)
			{		
				requestParams = dataObject.get("requestParams").getAsJsonObject();
			}
			requestParams.addProperty("attributeNamePrefix", "taskInfo");
			requestParams.add("attributesInfo", taskScheduleInfo);
			com.patientapp.controller.forms.impl.TaskInfoControllerImpl taskInfoImplObj = new com.patientapp.controller.forms.impl.TaskInfoControllerImpl(session);
			JsonObject taskInfoResponseData = taskInfoImplObj.getTaskInfoByLookupFields(session,  requestParams);
			if(taskInfoResponseData!=null && taskInfoResponseData.has("success") && taskInfoResponseData.get("success").getAsInt()==1)
			{
				JsonObject taskInfoDataObject = taskInfoResponseData.get("taskInfoDataObject").getAsJsonObject();
				int taskInfoId = taskInfoDataObject.get("taskInfoId").getAsInt();
				taskScheduleInfo.addProperty("taskInfoId", taskInfoId);
			}
			else
			{
				int optionalForUpload = 0;
				
				if(optionalForUpload==1)
				{
					taskScheduleInfo.addProperty("taskInfoId", -1);
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
	public JsonObject getTaskScheduleInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From TaskScheduleInfo where 1 = 1  ";
			String countHql = "select count(*)  from TaskScheduleInfo where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Task Schedule Info could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) it.next();
				JsonObject taskScheduleInfoDataObject = taskScheduleInfo.getDataObject(session);
				dataObject.add("taskScheduleInfoDataObject", taskScheduleInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Task Schedule Info could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Task Schedule Info' could not be retrieved");
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
		else if(apiName.equals("getTaskScheduleInfoPropertyValue"))
			{
				JsonObject inputDataObject = getTaskScheduleInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getTaskScheduleInfo"))
			{
				JsonObject inputDataObject = getTaskScheduleInfo(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForTaskScheduleInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForTaskScheduleInfo"))
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
			Integer taskScheduleInfoId = requestReceivedParametersInfo.get("taskScheduleInfoId").getAsInt();
			TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);
			taskScheduleInfo.setIsRequestUnderProcesss(0);
			session.merge(taskScheduleInfo);
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
	public JsonObject getTaskScheduleInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskScheduleInfoId = inputForGetAPI.get("taskScheduleInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		TaskScheduleInfo taskScheduleInfo = (TaskScheduleInfo) session.get(TaskScheduleInfo.class, taskScheduleInfoId);
		taskScheduleInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getTaskScheduleInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskScheduleInfoId = inputForGetAPI.get("taskScheduleInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskScheduleInfoId", String.valueOf(taskScheduleInfoId));
		JsonObject taskScheduleInfoListObject = retrieveTaskScheduleInfoList(paramsMap);
		if(taskScheduleInfoListObject!=null && taskScheduleInfoListObject.has("success") && taskScheduleInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskScheduleInfoList = taskScheduleInfoListObject.get("taskScheduleInfoList").getAsJsonArray();
			if(taskScheduleInfoList.size()>0)
			{
				dataObject.add("taskScheduleInfo", taskScheduleInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Schedule Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskScheduleInfoDataObject, TaskScheduleInfo taskScheduleInfo);
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
	public abstract void doAfterLookupRowSelectedImpl(TaskScheduleInfo taskScheduleInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(TaskScheduleInfo taskScheduleInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(TaskScheduleInfo taskScheduleInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
