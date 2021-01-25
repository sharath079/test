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
import com.patientapp.bean.TaskInfo;
import com.patientapp.controller.forms.impl.TaskInfoControllerImpl;
import com.patientapp.controller.forms.base.TaskInfoLabel;
import com.patientapp.bean.TaskInfo;
import com.patientapp.controller.forms.impl.TaskInfoControllerImpl;
//

import com.patientapp.bean.TaskExecutionInfo;
import com.patientapp.controller.forms.base.TaskExecutionInfoLabel;
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
public abstract class TaskExecutionInfoControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'TaskTimeCalculationType' 
	 *		 * 'FirstRunDateCalculationMethod' 
	 *		 * 'FirstRunDateGapInYears' 
	 *		 * 'FirstRunDateGapInMonths' 
	 *		 * 'FirstRunDateGapInDays' 
	 *		 * 'FirstRunDateGapInHours' 
	 *		 * 'FirstRunDateGapInMinutes' 
	 *		 * 'FirstRunDateGapInSeconds' 
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
	protected TaskExecutionInfoLabel TaskExecutionInfoLabelLocal = new TaskExecutionInfoLabel();
	protected TaskExecutionInfo TaskExecutionInfoLocal = new TaskExecutionInfo();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public TaskExecutionInfoControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public TaskExecutionInfoControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public TaskExecutionInfoControllerBase()
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
		return "TaskExecutionInfo" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((TaskExecutionInfo)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((TaskExecutionInfo)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((TaskExecutionInfo)obj).getTaskExecutionInfoId();
	}
	public String getManagedBeanName()
    {
		return "TaskExecutionInfoBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "TaskExecutionInfoLabelBean";
    }
	protected Class<TaskExecutionInfo> setBeanClass()
	{
		return TaskExecutionInfo.class;
	}
	protected Class<TaskExecutionInfoLabel> setBeanLabelClass()
	{
		return TaskExecutionInfoLabel.class;
	}
	protected TaskExecutionInfo getLocalManagedBean()
    {
		return (TaskExecutionInfo)getManagedBean();
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
		/*TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		String areChangesEffected = taskExecutionInfo.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			taskExecutionInfo.setAreChangesEffected("YES");			
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
		/*TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		String areChangesEffected = taskExecutionInfo.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			taskExecutionInfo.setAreChangesEffected("NO");			
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
	{TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = taskInfoControllerImpl.isTransactionUpdatable();
		return isParentTransactionUpdatable;
		
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		String sCurrentStatus = taskExecutionInfo.getVwTxnStatus();
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
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("taskTimeCalculationType".equalsIgnoreCase(attributeName))
		{
			  			
		}
		else if("firstRunDateCalculationMethod".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(taskExecutionInfo, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterTaskExecutionInfoLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("taskTimeCalculationType") && initParamsInfo.get("taskTimeCalculationType").isJsonNull()==false)
		{
			String taskTimeCalculationType = initParamsInfo.get("taskTimeCalculationType").getAsString();
			taskExecutionInfo.setTaskTimeCalculationType(taskTimeCalculationType);			
		}if(initParamsInfo.has("firstRunDateCalculationMethod") && initParamsInfo.get("firstRunDateCalculationMethod").isJsonNull()==false)
		{
			String firstRunDateCalculationMethod = initParamsInfo.get("firstRunDateCalculationMethod").getAsString();
			taskExecutionInfo.setFirstRunDateCalculationMethod(firstRunDateCalculationMethod);			
		}if(initParamsInfo.has("firstRunDateGapInYears") && initParamsInfo.get("firstRunDateGapInYears").isJsonNull()==false)
		{
			Integer firstRunDateGapInYears = null;
			try
			{
			 	firstRunDateGapInYears = initParamsInfo.get("firstRunDateGapInYears").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInYears(firstRunDateGapInYears);
		}if(initParamsInfo.has("firstRunDateGapInMonths") && initParamsInfo.get("firstRunDateGapInMonths").isJsonNull()==false)
		{
			Integer firstRunDateGapInMonths = null;
			try
			{
			 	firstRunDateGapInMonths = initParamsInfo.get("firstRunDateGapInMonths").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInMonths(firstRunDateGapInMonths);
		}if(initParamsInfo.has("firstRunDateGapInDays") && initParamsInfo.get("firstRunDateGapInDays").isJsonNull()==false)
		{
			Integer firstRunDateGapInDays = null;
			try
			{
			 	firstRunDateGapInDays = initParamsInfo.get("firstRunDateGapInDays").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInDays(firstRunDateGapInDays);
		}if(initParamsInfo.has("firstRunDateGapInHours") && initParamsInfo.get("firstRunDateGapInHours").isJsonNull()==false)
		{
			Integer firstRunDateGapInHours = null;
			try
			{
			 	firstRunDateGapInHours = initParamsInfo.get("firstRunDateGapInHours").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInHours(firstRunDateGapInHours);
		}if(initParamsInfo.has("firstRunDateGapInMinutes") && initParamsInfo.get("firstRunDateGapInMinutes").isJsonNull()==false)
		{
			Integer firstRunDateGapInMinutes = null;
			try
			{
			 	firstRunDateGapInMinutes = initParamsInfo.get("firstRunDateGapInMinutes").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInMinutes(firstRunDateGapInMinutes);
		}if(initParamsInfo.has("firstRunDateGapInSeconds") && initParamsInfo.get("firstRunDateGapInSeconds").isJsonNull()==false)
		{
			Integer firstRunDateGapInSeconds = null;
			try
			{
			 	firstRunDateGapInSeconds = initParamsInfo.get("firstRunDateGapInSeconds").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			taskExecutionInfo.setFirstRunDateGapInSeconds(firstRunDateGapInSeconds);
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(taskExecutionInfo, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(taskExecutionInfo, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = taskInfoControllerImpl.isTransactionUpdatable();
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
			TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "TaskExecutionInfo", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() TaskExecutionInfoContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (TaskExecutionInfoSearch)getManagedBean("TaskExecutionInfoSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getTaskTimeCalculationType()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.gettaskTimeCalculationTypeFieldName(),searchBeanLocal.getTaskTimeCalculationType());
			}	
			if (isExists(searchBeanLocal.getFirstRunDateCalculationMethod()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getfirstRunDateCalculationMethodFieldName(),searchBeanLocal.getFirstRunDateCalculationMethod());
			}	
			if (isExists(searchBeanLocal.getFirstRunDateGapInYears()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getfirstRunDateGapInYearsFieldName(),searchBeanLocal.getFirstRunDateGapInYears());
			}	
			if (isExists(searchBeanLocal.getFirstRunDateGapInMonths()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getfirstRunDateGapInMonthsFieldName(),searchBeanLocal.getFirstRunDateGapInMonths());
			}	
			if (isExists(searchBeanLocal.getFirstRunDateGapInDays()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getfirstRunDateGapInDaysFieldName(),searchBeanLocal.getFirstRunDateGapInDays());
			}	
			if (isExists(searchBeanLocal.getFirstRunDateGapInHours()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getfirstRunDateGapInHoursFieldName(),searchBeanLocal.getFirstRunDateGapInHours());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(TaskExecutionInfoLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() TaskExecutionInfoContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues TaskExecutionInfoContollerBase");
		targetBean = (TaskExecutionInfo)targetBean;TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
		((TaskExecutionInfo)targetBean).setTaskInfoId(TaskInfoLocal.getTaskInfoId());
		((TaskExecutionInfo)targetBean).setTaskExecutionInfoId(((TaskExecutionInfo)sourceBean).getTaskExecutionInfoId());
				((TaskExecutionInfo)targetBean).setTaskTimeCalculationType(((TaskExecutionInfo)sourceBean).getTaskTimeCalculationType());
		((TaskExecutionInfo)targetBean).setFirstRunDateCalculationMethod(((TaskExecutionInfo)sourceBean).getFirstRunDateCalculationMethod());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInYears(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInYears());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInMonths(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInMonths());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInDays(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInDays());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInHours(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInHours());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInMinutes(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInMinutes());
		((TaskExecutionInfo)targetBean).setFirstRunDateGapInSeconds(((TaskExecutionInfo)sourceBean).getFirstRunDateGapInSeconds());

		doAfterSetValues();
		debugCode("Out setValues TaskExecutionInfoContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.TaskExecutionInfo entityBean = (TaskExecutionInfo)entityObject;
	 	if (sFieldName.equalsIgnoreCase("taskExecutionInfoId")) 
	 	{
			return entityBean.getTaskExecutionInfoId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("TaskTimeCalculationType")) 
	 	{
			return entityBean.getTaskTimeCalculationType();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateCalculationMethod")) 
	 	{
			return entityBean.getFirstRunDateCalculationMethod();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInYears")) 
	 	{
			return entityBean.getFirstRunDateGapInYears();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInMonths")) 
	 	{
			return entityBean.getFirstRunDateGapInMonths();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInDays")) 
	 	{
			return entityBean.getFirstRunDateGapInDays();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInHours")) 
	 	{
			return entityBean.getFirstRunDateGapInHours();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInMinutes")) 
	 	{
			return entityBean.getFirstRunDateGapInMinutes();
		}
	 	if (sFieldName.equalsIgnoreCase("FirstRunDateGapInSeconds")) 
	 	{
			return entityBean.getFirstRunDateGapInSeconds();
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
		debugCode("In doEnrichSystemValues(String sAction) TaskExecutionInfoControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((TaskExecutionInfo) localManagedBean).setVwLastModifiedDate(new Date());
		((TaskExecutionInfo) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((TaskExecutionInfo) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((TaskExecutionInfo) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((TaskExecutionInfo) localManagedBean).setVwTxnStatus("CREATED");
			((TaskExecutionInfo) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((TaskExecutionInfo) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((TaskExecutionInfo) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) TaskExecutionInfoControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) TaskExecutionInfoControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) TaskExecutionInfoControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations TaskExecutionInfoControllerBase");
		//NG: Important comment
		//managedBean = (TaskExecutionInfo) getManagedBean();
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
		debugCode("Out doValidations TaskExecutionInfoControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation TaskExecutionInfoControllerBase");
		debugCode("Out doAllowedDecimalValidation TaskExecutionInfoControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation TaskExecutionInfoControllerBase");
				
		String sFieldValue2 = ((TaskExecutionInfo) localManagedBean).getTaskTimeCalculationType();if (!isExists(sFieldValue2,localMasters.getTaskTimeCalculationType())) addAllowedValuesResponse(TaskExecutionInfoLabelLocal.gettaskTimeCalculationTypeFieldName());String sFieldValue3 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateCalculationMethod();if (!isExists(sFieldValue3,localMasters.getFirstRunDateCalculationMethod())) addAllowedValuesResponse(TaskExecutionInfoLabelLocal.getfirstRunDateCalculationMethodFieldName());

		debugCode("Out doAllowedValuesValidation TaskExecutionInfoControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation TaskExecutionInfoControllerBase");
				
		String sFieldValue2 = ((TaskExecutionInfo) localManagedBean).getTaskTimeCalculationType();
		if (!isExists(sFieldValue2)) addMandatoryResponse(TaskExecutionInfoLabelLocal.gettaskTimeCalculationTypeFieldName());
debugCode("Out doMandatoryValidation TaskExecutionInfoControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation TaskExecutionInfoControllerBase");
				
		String sFieldValue2 = ((TaskExecutionInfo) localManagedBean).getTaskTimeCalculationType();String sFieldValue3 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateCalculationMethod();Integer sFieldValue4 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInYears();		Integer sFieldValue5 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInMonths();		Integer sFieldValue6 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInDays();		Integer sFieldValue7 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInHours();		Integer sFieldValue8 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInMinutes();		Integer sFieldValue9 = ((TaskExecutionInfo) localManagedBean).getFirstRunDateGapInSeconds();		
		if (!isLengthAllowed(sFieldValue2,"20")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.gettaskTimeCalculationTypeFieldName(),"20");
		if (!isLengthAllowed(sFieldValue3,"20")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateCalculationMethodFieldName(),"20");
		if (!isLengthAllowed(sFieldValue4,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInYearsFieldName(),"10");
		if (!isLengthAllowed(sFieldValue5,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInMonthsFieldName(),"10");
		if (!isLengthAllowed(sFieldValue6,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInDaysFieldName(),"10");
		if (!isLengthAllowed(sFieldValue7,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInHoursFieldName(),"10");
		if (!isLengthAllowed(sFieldValue8,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInMinutesFieldName(),"10");
		if (!isLengthAllowed(sFieldValue9,"10")) addMaxLengthResponse(TaskExecutionInfoLabelLocal.getfirstRunDateGapInSecondsFieldName(),"10");
debugCode("Out doLengthValidation TaskExecutionInfoControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation TaskExecutionInfoControllerBase");
		debugCode("Out doDataTypeValidation TaskExecutionInfoControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation TaskExecutionInfoContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
			if (TaskInfoLocal!=null)
			{
				iFieldValueFK = TaskInfoLocal.getTaskInfoId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation TaskExecutionInfoContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel TaskExecutionInfoContollerBase");
		String sLabel = new TaskExecutionInfoLabel().getLabel(sResponseField); 
		debugCode("Out getLabel TaskExecutionInfoContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int taskExecutionInfoId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("taskExecutionInfoId", taskExecutionInfoId);
			JsonObject responseData = retrieveTaskExecutionInfo(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Task Execution Info' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject taskExecutionInfoDataObject = responseData.get("taskExecutionInfoDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, taskExecutionInfoDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Task Execution Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveTaskExecutionInfo(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer taskExecutionInfoId = -1;
		if(requestParams.has("taskExecutionInfoId"))
		{
			taskExecutionInfoId = requestParams.get("taskExecutionInfoId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("taskExecutionInfoId", String.valueOf(taskExecutionInfoId));
				String taskTimeCalculationType = "";
		if(requestParams.has("taskTimeCalculationType"))
		{
			paramsMap.put("taskTimeCalculationType", requestParams.get("taskTimeCalculationType").getAsString());
		}
		String firstRunDateCalculationMethod = "";
		if(requestParams.has("firstRunDateCalculationMethod"))
		{
			paramsMap.put("firstRunDateCalculationMethod", requestParams.get("firstRunDateCalculationMethod").getAsString());
		}
		String firstRunDateGapInYears = "";
		if(requestParams.has("firstRunDateGapInYears"))
		{
			paramsMap.put("firstRunDateGapInYears", requestParams.get("firstRunDateGapInYears").getAsString());
		}
		String firstRunDateGapInMonths = "";
		if(requestParams.has("firstRunDateGapInMonths"))
		{
			paramsMap.put("firstRunDateGapInMonths", requestParams.get("firstRunDateGapInMonths").getAsString());
		}
		String firstRunDateGapInDays = "";
		if(requestParams.has("firstRunDateGapInDays"))
		{
			paramsMap.put("firstRunDateGapInDays", requestParams.get("firstRunDateGapInDays").getAsString());
		}
		String firstRunDateGapInHours = "";
		if(requestParams.has("firstRunDateGapInHours"))
		{
			paramsMap.put("firstRunDateGapInHours", requestParams.get("firstRunDateGapInHours").getAsString());
		}
		String firstRunDateGapInMinutes = "";
		if(requestParams.has("firstRunDateGapInMinutes"))
		{
			paramsMap.put("firstRunDateGapInMinutes", requestParams.get("firstRunDateGapInMinutes").getAsString());
		}
		String firstRunDateGapInSeconds = "";
		if(requestParams.has("firstRunDateGapInSeconds"))
		{
			paramsMap.put("firstRunDateGapInSeconds", requestParams.get("firstRunDateGapInSeconds").getAsString());
		}
	
		Integer taskInfoId = -1;;
		if(requestParams.has("taskInfoId"))
		{
			paramsMap.put("taskInfoId", requestParams.get("taskInfoId").getAsString());
		}JsonObject taskExecutionInfoListObject = retrieveTaskExecutionInfoList(paramsMap);
		if(taskExecutionInfoListObject!=null && taskExecutionInfoListObject.has("success") && taskExecutionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskExecutionInfoList = taskExecutionInfoListObject.get("taskExecutionInfoList").getAsJsonArray();
			if(taskExecutionInfoList.size()>0)
			{
				dataObject.add("taskExecutionInfoDataObject", taskExecutionInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Execution Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getTaskExecutionInfo(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String taskTimeCalculationType = "";
			if(searchParams.has("taskTimeCalculationType"))
			{
				paramsMap.put("taskTimeCalculationType", searchParams.get("taskTimeCalculationType").getAsString());
			}
			String firstRunDateCalculationMethod = "";
			if(searchParams.has("firstRunDateCalculationMethod"))
			{
				paramsMap.put("firstRunDateCalculationMethod", searchParams.get("firstRunDateCalculationMethod").getAsString());
			}
			String firstRunDateGapInYears = "";
			if(searchParams.has("firstRunDateGapInYears"))
			{
				paramsMap.put("firstRunDateGapInYears", searchParams.get("firstRunDateGapInYears").getAsString());
			}
			String firstRunDateGapInMonths = "";
			if(searchParams.has("firstRunDateGapInMonths"))
			{
				paramsMap.put("firstRunDateGapInMonths", searchParams.get("firstRunDateGapInMonths").getAsString());
			}
			String firstRunDateGapInDays = "";
			if(searchParams.has("firstRunDateGapInDays"))
			{
				paramsMap.put("firstRunDateGapInDays", searchParams.get("firstRunDateGapInDays").getAsString());
			}
			String firstRunDateGapInHours = "";
			if(searchParams.has("firstRunDateGapInHours"))
			{
				paramsMap.put("firstRunDateGapInHours", searchParams.get("firstRunDateGapInHours").getAsString());
			}
			String firstRunDateGapInMinutes = "";
			if(searchParams.has("firstRunDateGapInMinutes"))
			{
				paramsMap.put("firstRunDateGapInMinutes", searchParams.get("firstRunDateGapInMinutes").getAsString());
			}
			String firstRunDateGapInSeconds = "";
			if(searchParams.has("firstRunDateGapInSeconds"))
			{
				paramsMap.put("firstRunDateGapInSeconds", searchParams.get("firstRunDateGapInSeconds").getAsString());
			}

			
				
			Integer taskInfoId = -1;;
			if(searchParams.has("taskInfoId"))
			{
				paramsMap.put("taskInfoId", searchParams.get("taskInfoId").getAsString());
			}
			
			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject taskExecutionInfoListObject = retrieveTaskExecutionInfoList(paramsMap);
			if(taskExecutionInfoListObject!=null && taskExecutionInfoListObject.has("success") && taskExecutionInfoListObject.get("success").getAsInt()==1)
			{
				JsonArray taskExecutionInfoList = taskExecutionInfoListObject.get("taskExecutionInfoList").getAsJsonArray();
				if(taskExecutionInfoList.size()>0)
				{
					dataObject.add("taskExecutionInfo", taskExecutionInfoList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Task Execution Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getTaskExecutionInfoInJson(int taskExecutionInfoId)
	{
		JsonObject TaskExecutionInfoData = null;
		List<Integer> taskExecutionInfoIdsList = new ArrayList<>();
		taskExecutionInfoIdsList.add(taskExecutionInfoId);
		JsonArray taskExecutionInfoList = getTaskExecutionInfoListInJson(taskExecutionInfoIdsList);
		if(taskExecutionInfoList!=null && taskExecutionInfoList.size()>0)
		{
			TaskExecutionInfoData = taskExecutionInfoList.get(0).getAsJsonObject();
		}
		return TaskExecutionInfoData;
	}
	public JsonArray getTaskExecutionInfoListInJson(List<Integer> taskExecutionInfoIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray taskExecutionInfoObjectsList = new JsonArray();
		JsonObject taskExecutionInfoListObject = retrieveTaskExecutionInfoList(paramsMap, taskExecutionInfoIdsList);
		if(taskExecutionInfoListObject!=null && taskExecutionInfoListObject.has("success") && taskExecutionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskExecutionInfoList = taskExecutionInfoListObject.get("taskExecutionInfoList").getAsJsonArray();
			for (int i =0; i< taskExecutionInfoList.size(); i++)
			{
				JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
				int taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
				
				taskExecutionInfoObjectsList.add(taskExecutionInfoDataObject);
			}
		}
		return taskExecutionInfoObjectsList;
	}
	
	public JsonArray getTaskExecutionInfoListFromParentInJson(int taskInfoId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskInfoId", String.valueOf(taskInfoId));
		JsonArray taskExecutionInfoObjectsList = new JsonArray();
		JsonObject taskExecutionInfoListObject = retrieveTaskExecutionInfoList(paramsMap);
		if(taskExecutionInfoListObject!=null && taskExecutionInfoListObject.has("success") && taskExecutionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskExecutionInfoList = taskExecutionInfoListObject.get("taskExecutionInfoList").getAsJsonArray();
			for (int i =0; i< taskExecutionInfoList.size(); i++)
			{
				JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
				int taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
				
			    taskExecutionInfoObjectsList.add(taskExecutionInfoDataObject);
			}
		}
		return taskExecutionInfoObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray taskExecutionInfoList)
	{
		String errorMessage = "taskExecutionInfoList: \n";
		for (int i =0; i< taskExecutionInfoList.size(); i++)
		{
			JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
			JsonObject taskExecutionInfo = taskExecutionInfoDataObject.get("dataObject").getAsJsonObject();
			
			if(!taskExecutionInfoDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "taskExecutionInfo could not be uploaded verify data \n"; 
			}
			else if(taskExecutionInfoDataObject.has("isSuccessfullyUploaded") 
					&& taskExecutionInfoDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += taskExecutionInfoDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("TaskExecutionInfo".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND taskExecutionInfoId = :taskExecutionInfoId ";
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
		else if("TaskExecutionInfo".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskExecutionInfoId", userId);
		}
		
	}
	public String getParentFilterClauseForTaskExecutionInfo(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";		String taskInfoFilterClause = " select taskInfoId from TaskInfo where 1=1  ";
		int taskInfoId = -1;
		if(paramsMap.containsKey("taskInfoId"))
		{
			taskInfoId = Integer.parseInt(paramsMap.get("taskInfoId"));			
		}
		if(taskInfoId>0)
		{
			taskInfoFilterClause += " and taskInfoId = :taskInfoId  ";
		}
					
		
parentFilterClause = " and taskInfoId in (" + taskInfoFilterClause + ")";
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
	public void setParentFilterClauseParamsForTaskExecutionInfo(java.util.Map<String, String> paramsMap, Query query)
	{		int taskInfoId = -1;
		if(paramsMap.containsKey("taskInfoId"))
		{
			taskInfoId = Integer.parseInt(paramsMap.get("taskInfoId"));			
		}
		if(taskInfoId>0)
		{
			query.setParameter("taskInfoId", taskInfoId);
		}			

	}
	public JsonObject retrieveTaskExecutionInfoList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> taskExecutionInfoIdsList = new ArrayList<>();
		if(paramsMap.containsKey("taskExecutionInfoId"))
		{
			int taskExecutionInfoId = Integer.parseInt(paramsMap.get("taskExecutionInfoId"));
			if(taskExecutionInfoId>0)
			{
				taskExecutionInfoIdsList.add(taskExecutionInfoId);
			}
		}
		return retrieveTaskExecutionInfoList(paramsMap, taskExecutionInfoIdsList);
	}
	public JsonObject retrieveTaskExecutionInfoList(java.util.Map<String, String> paramsMap, List<Integer> taskExecutionInfoIdsList)
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
						String taskTimeCalculationType = paramsMap.get("taskTimeCalculationType");
			if(taskTimeCalculationType==null)
			{
				taskTimeCalculationType = "";
			}
			String firstRunDateCalculationMethod = paramsMap.get("firstRunDateCalculationMethod");
			if(firstRunDateCalculationMethod==null)
			{
				firstRunDateCalculationMethod = "";
			}
			String firstRunDateGapInYears = paramsMap.get("firstRunDateGapInYears");
			if(firstRunDateGapInYears==null)
			{
				firstRunDateGapInYears = "";
			}
			String firstRunDateGapInMonths = paramsMap.get("firstRunDateGapInMonths");
			if(firstRunDateGapInMonths==null)
			{
				firstRunDateGapInMonths = "";
			}
			String firstRunDateGapInDays = paramsMap.get("firstRunDateGapInDays");
			if(firstRunDateGapInDays==null)
			{
				firstRunDateGapInDays = "";
			}
			String firstRunDateGapInHours = paramsMap.get("firstRunDateGapInHours");
			if(firstRunDateGapInHours==null)
			{
				firstRunDateGapInHours = "";
			}
			String firstRunDateGapInMinutes = paramsMap.get("firstRunDateGapInMinutes");
			if(firstRunDateGapInMinutes==null)
			{
				firstRunDateGapInMinutes = "";
			}
			String firstRunDateGapInSeconds = paramsMap.get("firstRunDateGapInSeconds");
			if(firstRunDateGapInSeconds==null)
			{
				firstRunDateGapInSeconds = "";
			}

			
			String hql = "FROM TaskExecutionInfo where 1 = 1 ";
			String orderByClauseText = "  ";
			String taskExecutionInfoIdFilterClass = "";
			if (taskExecutionInfoIdsList != null && taskExecutionInfoIdsList.size() > 0)
			{
				taskExecutionInfoIdFilterClass = " AND taskExecutionInfoId in (:idsList) ";
			}
						String taskTimeCalculationTypeFilterClass = "";
			if (taskTimeCalculationType.length() > 0)
			{		
				
				taskTimeCalculationTypeFilterClass = " AND taskTimeCalculationType LIKE :taskTimeCalculationType ";	
				
			}
			String firstRunDateCalculationMethodFilterClass = "";
			if (firstRunDateCalculationMethod.length() > 0)
			{		
				
				firstRunDateCalculationMethodFilterClass = " AND firstRunDateCalculationMethod LIKE :firstRunDateCalculationMethod ";	
				
			}
			String firstRunDateGapInYearsFilterClass = "";
			if (firstRunDateGapInYears.length() > 0)
			{			
				
				firstRunDateGapInYearsFilterClass = " AND firstRunDateGapInYears = :firstRunDateGapInYears ";
				
			}
			String firstRunDateGapInMonthsFilterClass = "";
			if (firstRunDateGapInMonths.length() > 0)
			{			
				
				firstRunDateGapInMonthsFilterClass = " AND firstRunDateGapInMonths = :firstRunDateGapInMonths ";
				
			}
			String firstRunDateGapInDaysFilterClass = "";
			if (firstRunDateGapInDays.length() > 0)
			{			
				
				firstRunDateGapInDaysFilterClass = " AND firstRunDateGapInDays = :firstRunDateGapInDays ";
				
			}
			String firstRunDateGapInHoursFilterClass = "";
			if (firstRunDateGapInHours.length() > 0)
			{			
				
				firstRunDateGapInHoursFilterClass = " AND firstRunDateGapInHours = :firstRunDateGapInHours ";
				
			}
			String firstRunDateGapInMinutesFilterClass = "";
			if (firstRunDateGapInMinutes.length() > 0)
			{			
				
				firstRunDateGapInMinutesFilterClass = " AND firstRunDateGapInMinutes = :firstRunDateGapInMinutes ";
				
			}
			String firstRunDateGapInSecondsFilterClass = "";
			if (firstRunDateGapInSeconds.length() > 0)
			{			
				
				firstRunDateGapInSecondsFilterClass = " AND firstRunDateGapInSeconds = :firstRunDateGapInSeconds ";
				
			}

			
			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForTaskExecutionInfo(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					taskExecutionInfoIdFilterClass +
										taskTimeCalculationTypeFilterClass +
					firstRunDateCalculationMethodFilterClass +
					firstRunDateGapInYearsFilterClass +
					firstRunDateGapInMonthsFilterClass +
					firstRunDateGapInDaysFilterClass +
					firstRunDateGapInHoursFilterClass +
					firstRunDateGapInMinutesFilterClass +
					firstRunDateGapInSecondsFilterClass +

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
			if (taskExecutionInfoIdsList != null && taskExecutionInfoIdsList.size() > 0)
			{
				query.setParameterList("idsList", taskExecutionInfoIdsList);
			}
						if (taskTimeCalculationType.length() > 0)
			{		
				
				query.setParameter("taskTimeCalculationType", taskTimeCalculationType);	
				
			}
			if (firstRunDateCalculationMethod.length() > 0)
			{		
				
				query.setParameter("firstRunDateCalculationMethod", firstRunDateCalculationMethod);	
				
			}
			if (firstRunDateGapInYears.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInYears", Integer.parseInt(firstRunDateGapInYears));
				
			}
			if (firstRunDateGapInMonths.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInMonths", Integer.parseInt(firstRunDateGapInMonths));
				
			}
			if (firstRunDateGapInDays.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInDays", Integer.parseInt(firstRunDateGapInDays));
				
			}
			if (firstRunDateGapInHours.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInHours", Integer.parseInt(firstRunDateGapInHours));
				
			}
			if (firstRunDateGapInMinutes.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInMinutes", Integer.parseInt(firstRunDateGapInMinutes));
				
			}
			if (firstRunDateGapInSeconds.length() > 0)
			{			
				
				query.setParameter("firstRunDateGapInSeconds", Integer.parseInt(firstRunDateGapInSeconds));
				
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
	    	setParentFilterClauseParamsForTaskExecutionInfo(paramsMap, query);
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
			JsonArray taskExecutionInfoList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) it.next();
				JsonObject taskExecutionInfoDataObject = taskExecutionInfo.getDataObject(getDBSession());
				taskExecutionInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskExecutionInfo.getVwTxnStatus()));
				taskExecutionInfoList.add(taskExecutionInfoDataObject);
				doAfterSearchResultRowAddedImpl(taskExecutionInfoDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from TaskExecutionInfo where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (taskExecutionInfoIdsList != null && taskExecutionInfoIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", taskExecutionInfoIdsList);
				}
								if (taskTimeCalculationType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("taskTimeCalculationType", taskTimeCalculationType);
					
					
					
					
				}
				if (firstRunDateCalculationMethod.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateCalculationMethod", firstRunDateCalculationMethod);
					
					
					
					
				}
				if (firstRunDateGapInYears.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInYears", Integer.parseInt(firstRunDateGapInYears));
					
				}
				if (firstRunDateGapInMonths.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInMonths", Integer.parseInt(firstRunDateGapInMonths));
					
				}
				if (firstRunDateGapInDays.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInDays", Integer.parseInt(firstRunDateGapInDays));
					
				}
				if (firstRunDateGapInHours.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInHours", Integer.parseInt(firstRunDateGapInHours));
					
				}
				if (firstRunDateGapInMinutes.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInMinutes", Integer.parseInt(firstRunDateGapInMinutes));
					
				}
				if (firstRunDateGapInSeconds.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("firstRunDateGapInSeconds", Integer.parseInt(firstRunDateGapInSeconds));
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForTaskExecutionInfo(paramsMap, countQuery);
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
			dataObject.add("taskExecutionInfoList",   taskExecutionInfoList);
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
				+ "   from TaskExecutionInfo E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from TaskExecutionInfo E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentTaskExecutionInfoList(java.util.Map<String, String> paramsMap)
	{
		return retrieveTaskExecutionInfoList(paramsMap);
	}
	public TaskExecutionInfo getTaskExecutionInfoByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from TaskExecutionInfo where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) it.next();
			return taskExecutionInfo;
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
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo)getManagedBean();
		JsonObject taskExecutionInfoDataObject = taskExecutionInfo.getDataObject(getDBSession());copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject);
		setManagedBean(taskExecutionInfo);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData TaskExecutionInfoContollerBase");
			TaskExecutionInfo currentBean = (TaskExecutionInfo)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "TaskTimeCalculationType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setTaskTimeCalculationType(sStringTestData);iFieldLength = 0;
		sFieldLength = "20";
		sStringTestData = "FirstRunDateCalculationMethod".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setFirstRunDateCalculationMethod(sStringTestData);currentBean.setFirstRunDateGapInYears(1);currentBean.setFirstRunDateGapInMonths(1);currentBean.setFirstRunDateGapInDays(1);currentBean.setFirstRunDateGapInHours(1);currentBean.setFirstRunDateGapInMinutes(1);currentBean.setFirstRunDateGapInSeconds(1);

		setManagedBean(currentBean);
		debugCode("Out setTestData TaskExecutionInfoContollerBase");
	}
	// end Test Data
	public void copyTaskExecutionInfoFromJson(TaskExecutionInfo taskExecutionInfo, JsonObject taskExecutionInfoDataObject)
	{
		copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject, false);
	}
	public void copyTaskExecutionInfoFromJson(TaskExecutionInfo taskExecutionInfo, JsonObject taskExecutionInfoDataObject, boolean excludePasswordFields)
	{	
				
		if(taskExecutionInfoDataObject.has("taskTimeCalculationType"))
		{
			String taskTimeCalculationType = taskExecutionInfoDataObject.get("taskTimeCalculationType").getAsString();
			taskExecutionInfo.setTaskTimeCalculationType(taskTimeCalculationType);
		}if(taskExecutionInfoDataObject.has("firstRunDateCalculationMethod"))
		{
			String firstRunDateCalculationMethod = taskExecutionInfoDataObject.get("firstRunDateCalculationMethod").getAsString();
			taskExecutionInfo.setFirstRunDateCalculationMethod(firstRunDateCalculationMethod);
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInYears"))
		{
			Integer firstRunDateGapInYears = null;
			try
			{
			 	firstRunDateGapInYears = taskExecutionInfoDataObject.get("firstRunDateGapInYears").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInYears != null)
			{
				taskExecutionInfo.setFirstRunDateGapInYears(firstRunDateGapInYears);
			}
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInMonths"))
		{
			Integer firstRunDateGapInMonths = null;
			try
			{
			 	firstRunDateGapInMonths = taskExecutionInfoDataObject.get("firstRunDateGapInMonths").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInMonths != null)
			{
				taskExecutionInfo.setFirstRunDateGapInMonths(firstRunDateGapInMonths);
			}
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInDays"))
		{
			Integer firstRunDateGapInDays = null;
			try
			{
			 	firstRunDateGapInDays = taskExecutionInfoDataObject.get("firstRunDateGapInDays").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInDays != null)
			{
				taskExecutionInfo.setFirstRunDateGapInDays(firstRunDateGapInDays);
			}
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInHours"))
		{
			Integer firstRunDateGapInHours = null;
			try
			{
			 	firstRunDateGapInHours = taskExecutionInfoDataObject.get("firstRunDateGapInHours").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInHours != null)
			{
				taskExecutionInfo.setFirstRunDateGapInHours(firstRunDateGapInHours);
			}
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInMinutes"))
		{
			Integer firstRunDateGapInMinutes = null;
			try
			{
			 	firstRunDateGapInMinutes = taskExecutionInfoDataObject.get("firstRunDateGapInMinutes").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInMinutes != null)
			{
				taskExecutionInfo.setFirstRunDateGapInMinutes(firstRunDateGapInMinutes);
			}
		}if(taskExecutionInfoDataObject.has("firstRunDateGapInSeconds"))
		{
			Integer firstRunDateGapInSeconds = null;
			try
			{
			 	firstRunDateGapInSeconds = taskExecutionInfoDataObject.get("firstRunDateGapInSeconds").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(firstRunDateGapInSeconds != null)
			{
				taskExecutionInfo.setFirstRunDateGapInSeconds(firstRunDateGapInSeconds);
			}
		}
		
	}
		
	public JsonObject createTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		return createTaskExecutionInfo(taskExecutionInfoDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject taskExecutionInfoDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createTaskExecutionInfo(JsonObject taskExecutionInfoDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		TaskExecutionInfo taskExecutionInfo = new TaskExecutionInfo();
		setLoginBasedValues(paramsInfoObj, taskExecutionInfoDataObject);
		Session session = getDBSession();				
		copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject);
		if(taskExecutionInfoDataObject.has("legacyRecordId"))
		{
			taskExecutionInfo.setLegacyRecordId(taskExecutionInfoDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer taskInfoId = taskExecutionInfoDataObject.get("taskInfoId").getAsInt();
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
		taskExecutionInfo.setTaskInfoId(taskInfoId);
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}taskExecutionInfo.setVwCreatedBy(createdBy);
		taskExecutionInfo.setVwCreationDate(new Date());
		setPrivateManagedBean(taskExecutionInfo);
		setManagedBean("TaskExecutionInfoBean", taskExecutionInfo);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		taskExecutionInfo = (TaskExecutionInfo) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("taskExecutionInfoId", taskExecutionInfo.getTaskExecutionInfoId());
			JsonObject taskExecutionInfoObj = taskExecutionInfo.getDataObject(getDBSession());
			taskExecutionInfoObj.addProperty("nextAction", getActionForCurrentStatus(taskExecutionInfo.getVwTxnStatus()));
			dataObject.add("taskExecutionInfoDataObject", taskExecutionInfoObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskExecutionInfoId", taskExecutionInfoId);
		JsonObject responseData = retrieveTaskExecutionInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Execution Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
		if(taskExecutionInfo == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(taskExecutionInfo.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromTaskExecutionInfo(taskExecutionInfoDataObject);
		boolean excludePasswordFields = true;
		copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject, excludePasswordFields);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskExecutionInfo.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(taskExecutionInfo);
		setManagedBean("TaskExecutionInfoBean", taskExecutionInfo);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		taskExecutionInfo.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("taskExecutionInfoId", taskExecutionInfoId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
	}
	public JsonObject deleteTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskExecutionInfoId", taskExecutionInfoId);
		JsonObject responseData = retrieveTaskExecutionInfo(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Execution Info' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskExecutionInfo.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(taskExecutionInfo);
		setManagedBean("TaskExecutionInfo", taskExecutionInfo);
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
	public JsonObject fetchTaskExecutionInfoDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		TaskExecutionInfo taskExecutionInfo = new TaskExecutionInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfo", taskExecutionInfo);
			doAfterTaskExecutionInfoLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfo.getDataObject(getDBSession()));
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
	public JsonObject fetchTaskExecutionInfoTestData(int obj, JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		TaskExecutionInfo taskExecutionInfo = new TaskExecutionInfo();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject);
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfo", taskExecutionInfo);
			doAfterTaskExecutionInfoLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfo.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		TaskExecutionInfo taskExecutionInfo = new TaskExecutionInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject);	
			
			Integer taskInfoId = taskExecutionInfoDataObject.get("taskInfoId").getAsInt();
			taskExecutionInfo.setTaskInfoId(taskInfoId);
			
			String attributeName = taskExecutionInfoDataObject.get("attributeName").getAsString();
			Integer attributeId = taskExecutionInfoDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfo", taskExecutionInfo);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfo.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		TaskExecutionInfo taskExecutionInfo = new TaskExecutionInfo();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskExecutionInfoFromJson(taskExecutionInfo, taskExecutionInfoDataObject);	
			String attributeName = taskExecutionInfoDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfo", taskExecutionInfo);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfo.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
			String customEventName = taskExecutionInfoDataObject.get("customEventName").getAsString();
			TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfoBean", taskExecutionInfo);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfo.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnTaskExecutionInfo(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskExecutionInfoId = taskExecutionInfoDataObject.get("taskExecutionInfoId").getAsInt();
			String currentStatus = taskExecutionInfoDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(taskExecutionInfoDataObject.has("currentAction"))
			{
				currentAction = taskExecutionInfoDataObject.get("currentAction").getAsString();
			}
			TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
			setPrivateManagedBean(taskExecutionInfo);
			setManagedBean("TaskExecutionInfoBean", taskExecutionInfo);
			if(taskExecutionInfo.getIsRequestUnderProcesss() == 1)
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
				executeAction(taskExecutionInfo, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(taskExecutionInfo, "ClassInfoBean", currentStatus);
			}
//			executeAction(taskExecutionInfo, "TaskExecutionInfoBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedtaskExecutionInfoDataObject = taskExecutionInfo.getDataObject(getDBSession());
				updatedtaskExecutionInfoDataObject.addProperty("nextAction", getActionForCurrentStatus(taskExecutionInfo.getVwTxnStatus()));
				dataObject.add("taskExecutionInfo", updatedtaskExecutionInfoDataObject);
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
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) getManagedBean();
		String currentStatus = taskExecutionInfo.getVwTxnStatus();
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
	
	
	public JsonObject downloadTaskExecutionInfoData() throws Exception
	{
		return downloadTaskExecutionInfoData(null);
	}
	public JsonObject downloadTaskExecutionInfoData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadTaskExecutionInfoData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadTaskExecutionInfoData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer taskInfoId) throws Exception
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
			headerName = "TaskExecutionInfo";
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
			headerName = "taskExecutionInfoId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "taskTimeCalculationType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateCalculationMethod";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInYears";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInMonths";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInDays";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInHours";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInMinutes";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "firstRunDateGapInSeconds";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From TaskExecutionInfo ";
			
			hql += " WHERE taskInfoId  = :taskInfoId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer taskExecutionInfoId = taskExecutionInfo.getTaskExecutionInfoId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(taskExecutionInfoId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String taskTimeCalculationType = taskExecutionInfo.getTaskTimeCalculationType();
				cell.setCellValue(taskTimeCalculationType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String firstRunDateCalculationMethod = taskExecutionInfo.getFirstRunDateCalculationMethod();
				cell.setCellValue(firstRunDateCalculationMethod);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInYears = taskExecutionInfo.getFirstRunDateGapInYears();
				if(firstRunDateGapInYears!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInYears));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInMonths = taskExecutionInfo.getFirstRunDateGapInMonths();
				if(firstRunDateGapInMonths!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInMonths));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInDays = taskExecutionInfo.getFirstRunDateGapInDays();
				if(firstRunDateGapInDays!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInDays));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInHours = taskExecutionInfo.getFirstRunDateGapInHours();
				if(firstRunDateGapInHours!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInHours));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInMinutes = taskExecutionInfo.getFirstRunDateGapInMinutes();
				if(firstRunDateGapInMinutes!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInMinutes));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer firstRunDateGapInSeconds = taskExecutionInfo.getFirstRunDateGapInSeconds();
				if(firstRunDateGapInSeconds!=null)
				{
					cell.setCellValue(String.valueOf(firstRunDateGapInSeconds));
				}

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
	public JsonObject uploadTaskExecutionInfoData(JsonObject taskExecutionInfoDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = taskExecutionInfoDataObject.get("fileId").getAsInt();
		String inputFilesZip = taskExecutionInfoDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Task Execution Info Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		taskExecutionInfoDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadTaskExecutionInfoData(workbook, taskExecutionInfoDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Task Execution Info Data uploaded successfully.");
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
	public JsonObject uploadTaskExecutionInfoData(HSSFWorkbook workbook, JsonObject taskExecutionInfoDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("TaskExecutionInfo");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = taskExecutionInfoDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = taskExecutionInfoDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = taskExecutionInfoDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Task Execution Info Data uploaded successfully.");
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
			JsonObject taskExecutionInfo = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject taskExecutionInfoListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray taskExecutionInfoList = taskExecutionInfoListObj.get("taskExecutionInfoList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadTaskExecutionInfoList(taskExecutionInfoList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(taskExecutionInfoList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(taskExecutionInfoList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray taskExecutionInfoList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject taskExecutionInfo = new JsonObject();
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
				JsonObject taskExecutionInfoUploadObj	= new JsonObject();
				taskExecutionInfoUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					taskExecutionInfo = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("taskExecutionInfoId"))
						{
							String taskExecutionInfoId = row.getCell(cellIndex++).getStringCellValue();
							if(taskExecutionInfoId != null && taskExecutionInfoId.trim().length() > 0)
							{
								try
								{
									Integer iTaskExecutionInfoId = Integer.parseInt(taskExecutionInfoId);
									if(areSourceDestinationSame == 1)
									{
										TaskExecutionInfo taskExecutionInfoObj = (TaskExecutionInfo)session.get(TaskExecutionInfo.class, iTaskExecutionInfoId);
										if(taskExecutionInfoObj != null)
										{ 
											taskExecutionInfo.addProperty("taskExecutionInfoId", iTaskExecutionInfoId);
										}
										else
										{
											taskExecutionInfoUploadObj.addProperty("isDataFetched", 0);
											taskExecutionInfoUploadObj.addProperty("msg", "This Task Execution Info could not be uploaded as there appears to be some problem with the data(TaskExecutionInfo Id is not exist). ");
											continue;
										}
									}
									else
									{
										TaskExecutionInfo taskExecutionInfoObj = getTaskExecutionInfoByLegacyRecordId(session, iTaskExecutionInfoId);
										if(taskExecutionInfoObj != null)
										{ 
											taskExecutionInfo.addProperty("taskExecutionInfoId", taskExecutionInfoObj.getTaskExecutionInfoId());
										}
										taskExecutionInfo.addProperty("legacyRecordId", iTaskExecutionInfoId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									taskExecutionInfoUploadObj.addProperty("isDataFetched", 0);
									taskExecutionInfoUploadObj.addProperty("msg", "This Task Execution Info could not be uploaded as there appears to be some problem with the data(TaskExecutionInfo Id). ");
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
							taskExecutionInfo.addProperty(parameterName, parameterValue);
						}
					}
					taskExecutionInfoUploadObj.add("dataObject", taskExecutionInfo);		    
					taskExecutionInfoList.add(taskExecutionInfoUploadObj);
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
			dataObject.add("taskExecutionInfoList", taskExecutionInfoList);
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
		if(previousRetryCountMap.has("TaskExecutionInfo") && previousRetryCountMap.get("TaskExecutionInfo").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("TaskExecutionInfo").getAsInt();
		}
		if(retryCountMap.has("TaskExecutionInfo") && retryCountMap.get("TaskExecutionInfo").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("TaskExecutionInfo").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForTaskExecutionInfoList(JsonArray taskExecutionInfoList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < taskExecutionInfoList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
			JsonObject taskExecutionInfo = taskExecutionInfoDataObject.get("dataObject").getAsJsonObject();
			if(taskExecutionInfoDataObject.has("retryUpload") && taskExecutionInfoDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = taskExecutionInfoDataObject.get("retryUpload").getAsInt();
			}
			if(taskExecutionInfoDataObject.has("retryChildEntitiesUpload") && taskExecutionInfoDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = taskExecutionInfoDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(taskExecutionInfoDataObject.has("partialUploadUnderProcess") && taskExecutionInfoDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = taskExecutionInfoDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("TaskExecutionInfo", retryCount);
	}
	public JsonObject uploadTaskExecutionInfoList(JsonArray taskExecutionInfoList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(taskExecutionInfoList);
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
	public JsonObject updateStatusMsg(JsonArray taskExecutionInfoList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < taskExecutionInfoList.size(); i++)
			{
				JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
				JsonObject taskExecutionInfo = taskExecutionInfoDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = taskExecutionInfoDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = taskExecutionInfoDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = taskExecutionInfoDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray taskExecutionInfoList) throws Exception
	
	{
		return uploadData(taskExecutionInfoList, -1);	
	}
	public JsonObject uploadData(JsonArray taskExecutionInfoList, Integer taskInfoId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < taskExecutionInfoList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject taskExecutionInfoDataObject = taskExecutionInfoList.get(i).getAsJsonObject();
				JsonObject taskExecutionInfo = taskExecutionInfoDataObject.get("dataObject").getAsJsonObject();
				taskExecutionInfoDataObject.addProperty("retryUpload", 0);
				taskExecutionInfoDataObject.addProperty("retryChildEntitiesUpload", 0);
				taskExecutionInfoDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = taskExecutionInfoImplObj.getEntityInfoUpdatedWithLookupIds(session, taskExecutionInfo, retValObject);
				if(areAllLookupsFound!=1)
				{
					taskExecutionInfoDataObject.addProperty("retryUpload", 1);
					taskExecutionInfoDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					taskExecutionInfoDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					taskExecutionInfoDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int taskExecutionInfoId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !taskExecutionInfo.has("taskExecutionInfoId"))
				{
					taskExecutionInfo.addProperty("attributeNamePrefix", "");
					
					taskExecutionInfo.addProperty("taskInfoId", taskInfoId);
					
					taskExecutionInfo.addProperty("attributeNamePrefix", "");
					JsonObject responseData = taskExecutionInfoImplObj.getTaskExecutionInfoByLookupFields(session,  taskExecutionInfo);
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
						JsonObject taskExecutionInfoObject = responseData.get("taskExecutionInfoDataObject").getAsJsonObject();
						taskExecutionInfoId = taskExecutionInfoObject.get("taskExecutionInfoId").getAsInt();
						taskExecutionInfo.addProperty("taskExecutionInfoId", taskExecutionInfoId);
						updateEntity = true;
					}
				}
				else if(taskExecutionInfo.has("taskExecutionInfoId"))
				{
					updateEntity = true;
				}
				
				if(taskInfoId > 0)
				{
					taskExecutionInfo.addProperty("taskInfoId", taskInfoId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = taskExecutionInfoImplObj.createTaskExecutionInfo(taskExecutionInfo);
				}
				else
				{
					responseData = taskExecutionInfoImplObj.updateTaskExecutionInfo(taskExecutionInfo);
				}
				taskExecutionInfoDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					taskExecutionInfoId =-1;
					taskExecutionInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					taskExecutionInfoDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				taskExecutionInfoId = responseData.get("taskExecutionInfoId").getAsInt();
				taskExecutionInfoDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject taskExecutionInfo, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getTaskExecutionInfoByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer taskInfoId = -1;
			if(requestParams.has("taskInfoId") && requestParams.get("taskInfoId").isJsonNull()==false)
			{
				taskInfoId = requestParams.get("taskInfoId").getAsInt();
			}
			
			String hql = "From TaskExecutionInfo where 1 = 1   and taskInfoId = :taskInfoId ";
			String countHql = "select count(*)  from TaskExecutionInfo where 1 = 1  and taskInfoId = :taskInfoId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("taskInfoId", taskInfoId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Task Execution Info could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) it.next();
				JsonObject taskExecutionInfoDataObject = taskExecutionInfo.getDataObject(session);
				dataObject.add("taskExecutionInfoDataObject", taskExecutionInfoDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Task Execution Info could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			
			
			if(searchObject.has("taskInfoId") && searchObject.get("taskInfoId").isJsonNull()==false)
			{
				requestParams.addProperty("taskInfoId", searchObject.get("taskInfoId").getAsInt());
			}
			
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Task Execution Info' could not be retrieved");
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
	
	public JsonObject deleteTaskExecutionInfoListIfKeyColumnsNotFound(Session session, Integer taskInfoId)
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
			String hql = "From TaskExecutionInfo WHERE taskInfoId = :taskInfoId ";
			Query query = session.createQuery(hql);
			query.setParameter("taskInfoId", taskInfoId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) it.next();
				int taskExecutionInfoId = taskExecutionInfo.getTaskExecutionInfoId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl taskExecutionInfoImplObj = new com.patientapp.controller.forms.impl.TaskExecutionInfoControllerImpl(session);
			    taskExecutionInfoImplObj.setPrivateManagedBean(taskExecutionInfo);
			    taskExecutionInfoImplObj.setManagedBean("TaskExecutionInfo", taskExecutionInfo);
			    taskExecutionInfoImplObj.delete();
				if (taskExecutionInfoImplObj.getHasTransactionFailed() == true)
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
		dataObject.addProperty("alert", "Your request could not be processed as Task Execution Info could not be deleted");
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
			else if(apiName.equals("getTaskExecutionInfoPropertyValue"))
			{
				JsonObject inputDataObject = getTaskExecutionInfoPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getTaskExecutionInfo"))
			{
				JsonObject inputDataObject = getTaskExecutionInfo(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForTaskExecutionInfo"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForTaskExecutionInfo"))
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
			Integer taskExecutionInfoId = requestReceivedParametersInfo.get("taskExecutionInfoId").getAsInt();
			TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
			taskExecutionInfo.setIsRequestUnderProcesss(0);
			session.merge(taskExecutionInfo);
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
	public JsonObject getTaskExecutionInfoPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskExecutionInfoId = inputForGetAPI.get("taskExecutionInfoId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		TaskExecutionInfo taskExecutionInfo = (TaskExecutionInfo) session.get(TaskExecutionInfo.class, taskExecutionInfoId);
		taskExecutionInfo.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getTaskExecutionInfo(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskExecutionInfoId = inputForGetAPI.get("taskExecutionInfoId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskExecutionInfoId", String.valueOf(taskExecutionInfoId));
		JsonObject taskExecutionInfoListObject = retrieveTaskExecutionInfoList(paramsMap);
		if(taskExecutionInfoListObject!=null && taskExecutionInfoListObject.has("success") && taskExecutionInfoListObject.get("success").getAsInt()==1)
		{
			JsonArray taskExecutionInfoList = taskExecutionInfoListObject.get("taskExecutionInfoList").getAsJsonArray();
			if(taskExecutionInfoList.size()>0)
			{
				dataObject.add("taskExecutionInfo", taskExecutionInfoList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Execution Info' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskExecutionInfoDataObject, TaskExecutionInfo taskExecutionInfo);
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
	public abstract void doAfterLookupRowSelectedImpl(TaskExecutionInfo taskExecutionInfo, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(TaskExecutionInfo taskExecutionInfo, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(TaskExecutionInfo taskExecutionInfo, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
