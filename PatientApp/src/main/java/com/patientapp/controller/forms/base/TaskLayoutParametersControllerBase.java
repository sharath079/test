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

import com.patientapp.bean.TaskLayoutParameters;
import com.patientapp.controller.forms.base.TaskLayoutParametersLabel;
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
public abstract class TaskLayoutParametersControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'ParameterName' 
	 *		 * 'ParameterValueType' 
	 *		 * 'ParameterValue' 
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
	protected TaskLayoutParametersLabel TaskLayoutParametersLabelLocal = new TaskLayoutParametersLabel();
	protected TaskLayoutParameters TaskLayoutParametersLocal = new TaskLayoutParameters();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public TaskLayoutParametersControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public TaskLayoutParametersControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public TaskLayoutParametersControllerBase()
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
		return "TaskLayoutParameters" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((TaskLayoutParameters)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((TaskLayoutParameters)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((TaskLayoutParameters)obj).getTaskLayoutParametersId();
	}
	public String getManagedBeanName()
    {
		return "TaskLayoutParametersBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "TaskLayoutParametersLabelBean";
    }
	protected Class<TaskLayoutParameters> setBeanClass()
	{
		return TaskLayoutParameters.class;
	}
	protected Class<TaskLayoutParametersLabel> setBeanLabelClass()
	{
		return TaskLayoutParametersLabel.class;
	}
	protected TaskLayoutParameters getLocalManagedBean()
    {
		return (TaskLayoutParameters)getManagedBean();
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
		/*TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		String areChangesEffected = taskLayoutParameters.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			taskLayoutParameters.setAreChangesEffected("YES");			
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
		/*TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		String areChangesEffected = taskLayoutParameters.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			taskLayoutParameters.setAreChangesEffected("NO");			
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
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		String sCurrentStatus = taskLayoutParameters.getVwTxnStatus();
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
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("parameterValueType".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(taskLayoutParameters, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterTaskLayoutParametersLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("parameterName") && initParamsInfo.get("parameterName").isJsonNull()==false)
		{
			String parameterName = initParamsInfo.get("parameterName").getAsString();
			taskLayoutParameters.setParameterName(parameterName);			
		}if(initParamsInfo.has("parameterValueType") && initParamsInfo.get("parameterValueType").isJsonNull()==false)
		{
			String parameterValueType = initParamsInfo.get("parameterValueType").getAsString();
			taskLayoutParameters.setParameterValueType(parameterValueType);			
		}if(initParamsInfo.has("parameterValue") && initParamsInfo.get("parameterValue").isJsonNull()==false)
		{
			String parameterValue = initParamsInfo.get("parameterValue").getAsString();
			taskLayoutParameters.setParameterValue(parameterValue);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(taskLayoutParameters, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(taskLayoutParameters, attributeName);
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
			TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "TaskLayoutParameters", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() TaskLayoutParametersContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (TaskLayoutParametersSearch)getManagedBean("TaskLayoutParametersSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getParameterName()))
			{
				searchParams.put(TaskLayoutParametersLabelLocal.getparameterNameFieldName(),searchBeanLocal.getParameterName());
			}	
			if (isExists(searchBeanLocal.getParameterValueType()))
			{
				searchParams.put(TaskLayoutParametersLabelLocal.getparameterValueTypeFieldName(),searchBeanLocal.getParameterValueType());
			}	
			if (isExists(searchBeanLocal.getParameterValue()))
			{
				searchParams.put(TaskLayoutParametersLabelLocal.getparameterValueFieldName(),searchBeanLocal.getParameterValue());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(TaskLayoutParametersLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() TaskLayoutParametersContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues TaskLayoutParametersContollerBase");
		targetBean = (TaskLayoutParameters)targetBean;TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
		((TaskLayoutParameters)targetBean).setTaskInfoId(TaskInfoLocal.getTaskInfoId());
		((TaskLayoutParameters)targetBean).setTaskLayoutParametersId(((TaskLayoutParameters)sourceBean).getTaskLayoutParametersId());
				((TaskLayoutParameters)targetBean).setParameterName(((TaskLayoutParameters)sourceBean).getParameterName());
		((TaskLayoutParameters)targetBean).setParameterValueType(((TaskLayoutParameters)sourceBean).getParameterValueType());
		((TaskLayoutParameters)targetBean).setParameterValue(((TaskLayoutParameters)sourceBean).getParameterValue());

		doAfterSetValues();
		debugCode("Out setValues TaskLayoutParametersContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.TaskLayoutParameters entityBean = (TaskLayoutParameters)entityObject;
	 	if (sFieldName.equalsIgnoreCase("taskLayoutParametersId")) 
	 	{
			return entityBean.getTaskLayoutParametersId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("ParameterName")) 
	 	{
			return entityBean.getParameterName();
		}
	 	if (sFieldName.equalsIgnoreCase("ParameterValueType")) 
	 	{
			return entityBean.getParameterValueType();
		}
	 	if (sFieldName.equalsIgnoreCase("ParameterValue")) 
	 	{
			return entityBean.getParameterValue();
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
		debugCode("In doEnrichSystemValues(String sAction) TaskLayoutParametersControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((TaskLayoutParameters) localManagedBean).setVwLastModifiedDate(new Date());
		((TaskLayoutParameters) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((TaskLayoutParameters) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((TaskLayoutParameters) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((TaskLayoutParameters) localManagedBean).setVwTxnStatus("CREATED");
			((TaskLayoutParameters) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((TaskLayoutParameters) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((TaskLayoutParameters) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) TaskLayoutParametersControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) TaskLayoutParametersControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) TaskLayoutParametersControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations TaskLayoutParametersControllerBase");
		//NG: Important comment
		//managedBean = (TaskLayoutParameters) getManagedBean();
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
		debugCode("Out doValidations TaskLayoutParametersControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation TaskLayoutParametersControllerBase");
		debugCode("Out doAllowedDecimalValidation TaskLayoutParametersControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation TaskLayoutParametersControllerBase");
				
		String sFieldValue3 = ((TaskLayoutParameters) localManagedBean).getParameterValueType();if (!isExists(sFieldValue3,localMasters.getLayoutParameterValueType())) addAllowedValuesResponse(TaskLayoutParametersLabelLocal.getparameterValueTypeFieldName());

		debugCode("Out doAllowedValuesValidation TaskLayoutParametersControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation TaskLayoutParametersControllerBase");
				
		String sFieldValue2 = ((TaskLayoutParameters) localManagedBean).getParameterName();String sFieldValue3 = ((TaskLayoutParameters) localManagedBean).getParameterValueType();
		if (!isExists(sFieldValue2)) addMandatoryResponse(TaskLayoutParametersLabelLocal.getparameterNameFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(TaskLayoutParametersLabelLocal.getparameterValueTypeFieldName());
debugCode("Out doMandatoryValidation TaskLayoutParametersControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation TaskLayoutParametersControllerBase");
				
		String sFieldValue2 = ((TaskLayoutParameters) localManagedBean).getParameterName();String sFieldValue3 = ((TaskLayoutParameters) localManagedBean).getParameterValueType();String sFieldValue4 = ((TaskLayoutParameters) localManagedBean).getParameterValue();
		if (!isLengthAllowed(sFieldValue2,"500")) addMaxLengthResponse(TaskLayoutParametersLabelLocal.getparameterNameFieldName(),"500");
		if (!isLengthAllowed(sFieldValue3,"40")) addMaxLengthResponse(TaskLayoutParametersLabelLocal.getparameterValueTypeFieldName(),"40");
		if (!isLengthAllowed(sFieldValue4,"40")) addMaxLengthResponse(TaskLayoutParametersLabelLocal.getparameterValueFieldName(),"40");
debugCode("Out doLengthValidation TaskLayoutParametersControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation TaskLayoutParametersControllerBase");
		debugCode("Out doDataTypeValidation TaskLayoutParametersControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation TaskLayoutParametersContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
			if (TaskInfoLocal!=null)
			{
				iFieldValueFK = TaskInfoLocal.getTaskInfoId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation TaskLayoutParametersContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel TaskLayoutParametersContollerBase");
		String sLabel = new TaskLayoutParametersLabel().getLabel(sResponseField); 
		debugCode("Out getLabel TaskLayoutParametersContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int taskLayoutParametersId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("taskLayoutParametersId", taskLayoutParametersId);
			JsonObject responseData = retrieveTaskLayoutParameters(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Task Layout Parameters' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject taskLayoutParametersDataObject = responseData.get("taskLayoutParametersDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, taskLayoutParametersDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Task Layout Parameters' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveTaskLayoutParameters(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer taskLayoutParametersId = -1;
		if(requestParams.has("taskLayoutParametersId"))
		{
			taskLayoutParametersId = requestParams.get("taskLayoutParametersId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("taskLayoutParametersId", String.valueOf(taskLayoutParametersId));
				String parameterName = "";
		if(requestParams.has("parameterName"))
		{
			paramsMap.put("parameterName", requestParams.get("parameterName").getAsString());
		}
		String parameterValueType = "";
		if(requestParams.has("parameterValueType"))
		{
			paramsMap.put("parameterValueType", requestParams.get("parameterValueType").getAsString());
		}
		String parameterValue = "";
		if(requestParams.has("parameterValue"))
		{
			paramsMap.put("parameterValue", requestParams.get("parameterValue").getAsString());
		}
	
		Integer taskInfoId = -1;;
		if(requestParams.has("taskInfoId"))
		{
			paramsMap.put("taskInfoId", requestParams.get("taskInfoId").getAsString());
		}JsonObject taskLayoutParametersListObject = retrieveTaskLayoutParametersList(paramsMap);
		if(taskLayoutParametersListObject!=null && taskLayoutParametersListObject.has("success") && taskLayoutParametersListObject.get("success").getAsInt()==1)
		{
			JsonArray taskLayoutParametersList = taskLayoutParametersListObject.get("taskLayoutParametersList").getAsJsonArray();
			if(taskLayoutParametersList.size()>0)
			{
				dataObject.add("taskLayoutParametersDataObject", taskLayoutParametersList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Layout Parameters' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getTaskLayoutParameters(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String parameterName = "";
			if(searchParams.has("parameterName"))
			{
				paramsMap.put("parameterName", searchParams.get("parameterName").getAsString());
			}
			String parameterValueType = "";
			if(searchParams.has("parameterValueType"))
			{
				paramsMap.put("parameterValueType", searchParams.get("parameterValueType").getAsString());
			}
			String parameterValue = "";
			if(searchParams.has("parameterValue"))
			{
				paramsMap.put("parameterValue", searchParams.get("parameterValue").getAsString());
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
			JsonObject taskLayoutParametersListObject = retrieveTaskLayoutParametersList(paramsMap);
			if(taskLayoutParametersListObject!=null && taskLayoutParametersListObject.has("success") && taskLayoutParametersListObject.get("success").getAsInt()==1)
			{
				JsonArray taskLayoutParametersList = taskLayoutParametersListObject.get("taskLayoutParametersList").getAsJsonArray();
				if(taskLayoutParametersList.size()>0)
				{
					dataObject.add("taskLayoutParameters", taskLayoutParametersList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Task Layout Parameters' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getTaskLayoutParametersInJson(int taskLayoutParametersId)
	{
		JsonObject TaskLayoutParametersData = null;
		List<Integer> taskLayoutParametersIdsList = new ArrayList<>();
		taskLayoutParametersIdsList.add(taskLayoutParametersId);
		JsonArray taskLayoutParametersList = getTaskLayoutParametersListInJson(taskLayoutParametersIdsList);
		if(taskLayoutParametersList!=null && taskLayoutParametersList.size()>0)
		{
			TaskLayoutParametersData = taskLayoutParametersList.get(0).getAsJsonObject();
		}
		return TaskLayoutParametersData;
	}
	public JsonArray getTaskLayoutParametersListInJson(List<Integer> taskLayoutParametersIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray taskLayoutParametersObjectsList = new JsonArray();
		JsonObject taskLayoutParametersListObject = retrieveTaskLayoutParametersList(paramsMap, taskLayoutParametersIdsList);
		if(taskLayoutParametersListObject!=null && taskLayoutParametersListObject.has("success") && taskLayoutParametersListObject.get("success").getAsInt()==1)
		{
			JsonArray taskLayoutParametersList = taskLayoutParametersListObject.get("taskLayoutParametersList").getAsJsonArray();
			for (int i =0; i< taskLayoutParametersList.size(); i++)
			{
				JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
				int taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
				
				taskLayoutParametersObjectsList.add(taskLayoutParametersDataObject);
			}
		}
		return taskLayoutParametersObjectsList;
	}
	
	public JsonArray getTaskLayoutParametersListFromParentInJson(int taskInfoId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskInfoId", String.valueOf(taskInfoId));
		JsonArray taskLayoutParametersObjectsList = new JsonArray();
		JsonObject taskLayoutParametersListObject = retrieveTaskLayoutParametersList(paramsMap);
		if(taskLayoutParametersListObject!=null && taskLayoutParametersListObject.has("success") && taskLayoutParametersListObject.get("success").getAsInt()==1)
		{
			JsonArray taskLayoutParametersList = taskLayoutParametersListObject.get("taskLayoutParametersList").getAsJsonArray();
			for (int i =0; i< taskLayoutParametersList.size(); i++)
			{
				JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
				int taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
				
			    taskLayoutParametersObjectsList.add(taskLayoutParametersDataObject);
			}
		}
		return taskLayoutParametersObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray taskLayoutParametersList)
	{
		String errorMessage = "taskLayoutParametersList: \n";
		for (int i =0; i< taskLayoutParametersList.size(); i++)
		{
			JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
			JsonObject taskLayoutParameters = taskLayoutParametersDataObject.get("dataObject").getAsJsonObject();
			
			if(!taskLayoutParametersDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "taskLayoutParameters could not be uploaded verify data \n"; 
			}
			else if(taskLayoutParametersDataObject.has("isSuccessfullyUploaded") 
					&& taskLayoutParametersDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += taskLayoutParametersDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("TaskLayoutParameters".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND taskLayoutParametersId = :taskLayoutParametersId ";
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
		else if("TaskLayoutParameters".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("taskLayoutParametersId", userId);
		}
		
	}
	public String getParentFilterClauseForTaskLayoutParameters(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForTaskLayoutParameters(java.util.Map<String, String> paramsMap, Query query)
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
	public JsonObject retrieveTaskLayoutParametersList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> taskLayoutParametersIdsList = new ArrayList<>();
		if(paramsMap.containsKey("taskLayoutParametersId"))
		{
			int taskLayoutParametersId = Integer.parseInt(paramsMap.get("taskLayoutParametersId"));
			if(taskLayoutParametersId>0)
			{
				taskLayoutParametersIdsList.add(taskLayoutParametersId);
			}
		}
		return retrieveTaskLayoutParametersList(paramsMap, taskLayoutParametersIdsList);
	}
	public JsonObject retrieveTaskLayoutParametersList(java.util.Map<String, String> paramsMap, List<Integer> taskLayoutParametersIdsList)
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
						String parameterName = paramsMap.get("parameterName");
			if(parameterName==null)
			{
				parameterName = "";
			}
			String parameterValueType = paramsMap.get("parameterValueType");
			if(parameterValueType==null)
			{
				parameterValueType = "";
			}
			String parameterValue = paramsMap.get("parameterValue");
			if(parameterValue==null)
			{
				parameterValue = "";
			}

			
			String hql = "FROM TaskLayoutParameters where 1 = 1 ";
			String orderByClauseText = "  ";
			String taskLayoutParametersIdFilterClass = "";
			if (taskLayoutParametersIdsList != null && taskLayoutParametersIdsList.size() > 0)
			{
				taskLayoutParametersIdFilterClass = " AND taskLayoutParametersId in (:idsList) ";
			}
						String parameterNameFilterClass = "";
			if (parameterName.length() > 0)
			{		
				
				parameterNameFilterClass = " AND parameterName LIKE :parameterName ";	
				
			}
			String parameterValueTypeFilterClass = "";
			if (parameterValueType.length() > 0)
			{		
				
				parameterValueTypeFilterClass = " AND parameterValueType LIKE :parameterValueType ";	
				
			}
			String parameterValueFilterClass = "";
			if (parameterValue.length() > 0)
			{		
				
				parameterValueFilterClass = " AND parameterValue LIKE :parameterValue ";	
				
			}

			
			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForTaskLayoutParameters(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					taskLayoutParametersIdFilterClass +
										parameterNameFilterClass +
					parameterValueTypeFilterClass +
					parameterValueFilterClass +

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
			if (taskLayoutParametersIdsList != null && taskLayoutParametersIdsList.size() > 0)
			{
				query.setParameterList("idsList", taskLayoutParametersIdsList);
			}
						if (parameterName.length() > 0)
			{		
				
				query.setParameter("parameterName", parameterName);	
				
			}
			if (parameterValueType.length() > 0)
			{		
				
				query.setParameter("parameterValueType", parameterValueType);	
				
			}
			if (parameterValue.length() > 0)
			{		
				
				query.setParameter("parameterValue", parameterValue);	
				
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
	    	setParentFilterClauseParamsForTaskLayoutParameters(paramsMap, query);
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
			JsonArray taskLayoutParametersList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) it.next();
				JsonObject taskLayoutParametersDataObject = taskLayoutParameters.getDataObject(getDBSession());
				taskLayoutParametersDataObject.addProperty("nextAction", getActionForCurrentStatus(taskLayoutParameters.getVwTxnStatus()));
				taskLayoutParametersList.add(taskLayoutParametersDataObject);
				doAfterSearchResultRowAddedImpl(taskLayoutParametersDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from TaskLayoutParameters where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (taskLayoutParametersIdsList != null && taskLayoutParametersIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", taskLayoutParametersIdsList);
				}
								if (parameterName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("parameterName", parameterName);
					
					
					
					
				}
				if (parameterValueType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("parameterValueType", parameterValueType);
					
					
					
					
				}
				if (parameterValue.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("parameterValue", parameterValue);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForTaskLayoutParameters(paramsMap, countQuery);
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
			dataObject.add("taskLayoutParametersList",   taskLayoutParametersList);
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
				+ "   from TaskLayoutParameters E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from TaskLayoutParameters E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentTaskLayoutParametersList(java.util.Map<String, String> paramsMap)
	{
		return retrieveTaskLayoutParametersList(paramsMap);
	}
	public TaskLayoutParameters getTaskLayoutParametersByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from TaskLayoutParameters where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) it.next();
			return taskLayoutParameters;
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
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters)getManagedBean();
		JsonObject taskLayoutParametersDataObject = taskLayoutParameters.getDataObject(getDBSession());copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject);
		setManagedBean(taskLayoutParameters);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData TaskLayoutParametersContollerBase");
			TaskLayoutParameters currentBean = (TaskLayoutParameters)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "ParameterName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setParameterName(sStringTestData);iFieldLength = 0;
		sFieldLength = "40";
		sStringTestData = "ParameterValueType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setParameterValueType(sStringTestData);iFieldLength = 0;
		sFieldLength = "40";
		sStringTestData = "ParameterValue".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setParameterValue(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData TaskLayoutParametersContollerBase");
	}
	// end Test Data
	public void copyTaskLayoutParametersFromJson(TaskLayoutParameters taskLayoutParameters, JsonObject taskLayoutParametersDataObject)
	{
		copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject, false);
	}
	public void copyTaskLayoutParametersFromJson(TaskLayoutParameters taskLayoutParameters, JsonObject taskLayoutParametersDataObject, boolean excludePasswordFields)
	{	
				
		if(taskLayoutParametersDataObject.has("parameterName"))
		{
			String parameterName = taskLayoutParametersDataObject.get("parameterName").getAsString();
			taskLayoutParameters.setParameterName(parameterName);
		}if(taskLayoutParametersDataObject.has("parameterValueType"))
		{
			String parameterValueType = taskLayoutParametersDataObject.get("parameterValueType").getAsString();
			taskLayoutParameters.setParameterValueType(parameterValueType);
		}if(taskLayoutParametersDataObject.has("parameterValue"))
		{
			String parameterValue = taskLayoutParametersDataObject.get("parameterValue").getAsString();
			taskLayoutParameters.setParameterValue(parameterValue);
		}
		
	}
		
	public JsonObject createTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		return createTaskLayoutParameters(taskLayoutParametersDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject taskLayoutParametersDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createTaskLayoutParameters(JsonObject taskLayoutParametersDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		TaskLayoutParameters taskLayoutParameters = new TaskLayoutParameters();
		setLoginBasedValues(paramsInfoObj, taskLayoutParametersDataObject);
		Session session = getDBSession();				
		copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject);
		if(taskLayoutParametersDataObject.has("legacyRecordId"))
		{
			taskLayoutParameters.setLegacyRecordId(taskLayoutParametersDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer taskInfoId = taskLayoutParametersDataObject.get("taskInfoId").getAsInt();
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
		taskLayoutParameters.setTaskInfoId(taskInfoId);
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}taskLayoutParameters.setVwCreatedBy(createdBy);
		taskLayoutParameters.setVwCreationDate(new Date());
		setPrivateManagedBean(taskLayoutParameters);
		setManagedBean("TaskLayoutParametersBean", taskLayoutParameters);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		taskLayoutParameters = (TaskLayoutParameters) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("taskLayoutParametersId", taskLayoutParameters.getTaskLayoutParametersId());
			JsonObject taskLayoutParametersObj = taskLayoutParameters.getDataObject(getDBSession());
			taskLayoutParametersObj.addProperty("nextAction", getActionForCurrentStatus(taskLayoutParameters.getVwTxnStatus()));
			dataObject.add("taskLayoutParametersDataObject", taskLayoutParametersObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskLayoutParametersId", taskLayoutParametersId);
		JsonObject responseData = retrieveTaskLayoutParameters(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Layout Parameters' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
		if(taskLayoutParameters == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(taskLayoutParameters.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromTaskLayoutParameters(taskLayoutParametersDataObject);
		boolean excludePasswordFields = true;
		copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject, excludePasswordFields);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskLayoutParameters.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(taskLayoutParameters);
		setManagedBean("TaskLayoutParametersBean", taskLayoutParameters);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		taskLayoutParameters.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("taskLayoutParametersId", taskLayoutParametersId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
	}
	public JsonObject deleteTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("taskLayoutParametersId", taskLayoutParametersId);
		JsonObject responseData = retrieveTaskLayoutParameters(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Task Layout Parameters' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskLayoutParameters.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(taskLayoutParameters);
		setManagedBean("TaskLayoutParameters", taskLayoutParameters);
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
	public JsonObject fetchTaskLayoutParametersDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		TaskLayoutParameters taskLayoutParameters = new TaskLayoutParameters();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParameters", taskLayoutParameters);
			doAfterTaskLayoutParametersLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParameters.getDataObject(getDBSession()));
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
	public JsonObject fetchTaskLayoutParametersTestData(int obj, JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		TaskLayoutParameters taskLayoutParameters = new TaskLayoutParameters();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject);
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParameters", taskLayoutParameters);
			doAfterTaskLayoutParametersLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParameters.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		TaskLayoutParameters taskLayoutParameters = new TaskLayoutParameters();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject);	
			
			Integer taskInfoId = taskLayoutParametersDataObject.get("taskInfoId").getAsInt();
			taskLayoutParameters.setTaskInfoId(taskInfoId);
			
			String attributeName = taskLayoutParametersDataObject.get("attributeName").getAsString();
			Integer attributeId = taskLayoutParametersDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParameters", taskLayoutParameters);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParameters.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		TaskLayoutParameters taskLayoutParameters = new TaskLayoutParameters();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyTaskLayoutParametersFromJson(taskLayoutParameters, taskLayoutParametersDataObject);	
			String attributeName = taskLayoutParametersDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParameters", taskLayoutParameters);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParameters.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
			String customEventName = taskLayoutParametersDataObject.get("customEventName").getAsString();
			TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParametersBean", taskLayoutParameters);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParameters.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnTaskLayoutParameters(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer taskLayoutParametersId = taskLayoutParametersDataObject.get("taskLayoutParametersId").getAsInt();
			String currentStatus = taskLayoutParametersDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(taskLayoutParametersDataObject.has("currentAction"))
			{
				currentAction = taskLayoutParametersDataObject.get("currentAction").getAsString();
			}
			TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
			setPrivateManagedBean(taskLayoutParameters);
			setManagedBean("TaskLayoutParametersBean", taskLayoutParameters);
			if(taskLayoutParameters.getIsRequestUnderProcesss() == 1)
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
				executeAction(taskLayoutParameters, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(taskLayoutParameters, "ClassInfoBean", currentStatus);
			}
//			executeAction(taskLayoutParameters, "TaskLayoutParametersBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedtaskLayoutParametersDataObject = taskLayoutParameters.getDataObject(getDBSession());
				updatedtaskLayoutParametersDataObject.addProperty("nextAction", getActionForCurrentStatus(taskLayoutParameters.getVwTxnStatus()));
				dataObject.add("taskLayoutParameters", updatedtaskLayoutParametersDataObject);
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
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) getManagedBean();
		String currentStatus = taskLayoutParameters.getVwTxnStatus();
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
	
	
	public JsonObject downloadTaskLayoutParametersData() throws Exception
	{
		return downloadTaskLayoutParametersData(null);
	}
	public JsonObject downloadTaskLayoutParametersData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadTaskLayoutParametersData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadTaskLayoutParametersData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer taskInfoId) throws Exception
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
			headerName = "TaskLayoutParameters";
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
			headerName = "taskLayoutParametersId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "parameterName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "parameterValueType";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "parameterValue";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From TaskLayoutParameters ";
			
			hql += " WHERE taskInfoId  = :taskInfoId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer taskLayoutParametersId = taskLayoutParameters.getTaskLayoutParametersId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(taskLayoutParametersId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String parameterName = taskLayoutParameters.getParameterName();
				cell.setCellValue(parameterName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String parameterValueType = taskLayoutParameters.getParameterValueType();
				cell.setCellValue(parameterValueType);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String parameterValue = taskLayoutParameters.getParameterValue();
				cell.setCellValue(parameterValue);

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
	public JsonObject uploadTaskLayoutParametersData(JsonObject taskLayoutParametersDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = taskLayoutParametersDataObject.get("fileId").getAsInt();
		String inputFilesZip = taskLayoutParametersDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Task Layout Parameters Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		taskLayoutParametersDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadTaskLayoutParametersData(workbook, taskLayoutParametersDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Task Layout Parameters Data uploaded successfully.");
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
	public JsonObject uploadTaskLayoutParametersData(HSSFWorkbook workbook, JsonObject taskLayoutParametersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("TaskLayoutParameters");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = taskLayoutParametersDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = taskLayoutParametersDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = taskLayoutParametersDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Task Layout Parameters Data uploaded successfully.");
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
			JsonObject taskLayoutParameters = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject taskLayoutParametersListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray taskLayoutParametersList = taskLayoutParametersListObj.get("taskLayoutParametersList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadTaskLayoutParametersList(taskLayoutParametersList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(taskLayoutParametersList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(taskLayoutParametersList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray taskLayoutParametersList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject taskLayoutParameters = new JsonObject();
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
				JsonObject taskLayoutParametersUploadObj	= new JsonObject();
				taskLayoutParametersUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					taskLayoutParameters = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("taskLayoutParametersId"))
						{
							String taskLayoutParametersId = row.getCell(cellIndex++).getStringCellValue();
							if(taskLayoutParametersId != null && taskLayoutParametersId.trim().length() > 0)
							{
								try
								{
									Integer iTaskLayoutParametersId = Integer.parseInt(taskLayoutParametersId);
									if(areSourceDestinationSame == 1)
									{
										TaskLayoutParameters taskLayoutParametersObj = (TaskLayoutParameters)session.get(TaskLayoutParameters.class, iTaskLayoutParametersId);
										if(taskLayoutParametersObj != null)
										{ 
											taskLayoutParameters.addProperty("taskLayoutParametersId", iTaskLayoutParametersId);
										}
										else
										{
											taskLayoutParametersUploadObj.addProperty("isDataFetched", 0);
											taskLayoutParametersUploadObj.addProperty("msg", "This Task Layout Parameters could not be uploaded as there appears to be some problem with the data(TaskLayoutParameters Id is not exist). ");
											continue;
										}
									}
									else
									{
										TaskLayoutParameters taskLayoutParametersObj = getTaskLayoutParametersByLegacyRecordId(session, iTaskLayoutParametersId);
										if(taskLayoutParametersObj != null)
										{ 
											taskLayoutParameters.addProperty("taskLayoutParametersId", taskLayoutParametersObj.getTaskLayoutParametersId());
										}
										taskLayoutParameters.addProperty("legacyRecordId", iTaskLayoutParametersId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									taskLayoutParametersUploadObj.addProperty("isDataFetched", 0);
									taskLayoutParametersUploadObj.addProperty("msg", "This Task Layout Parameters could not be uploaded as there appears to be some problem with the data(TaskLayoutParameters Id). ");
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
							taskLayoutParameters.addProperty(parameterName, parameterValue);
						}
					}
					taskLayoutParametersUploadObj.add("dataObject", taskLayoutParameters);		    
					taskLayoutParametersList.add(taskLayoutParametersUploadObj);
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
			dataObject.add("taskLayoutParametersList", taskLayoutParametersList);
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
		if(previousRetryCountMap.has("TaskLayoutParameters") && previousRetryCountMap.get("TaskLayoutParameters").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("TaskLayoutParameters").getAsInt();
		}
		if(retryCountMap.has("TaskLayoutParameters") && retryCountMap.get("TaskLayoutParameters").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("TaskLayoutParameters").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForTaskLayoutParametersList(JsonArray taskLayoutParametersList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < taskLayoutParametersList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
			JsonObject taskLayoutParameters = taskLayoutParametersDataObject.get("dataObject").getAsJsonObject();
			if(taskLayoutParametersDataObject.has("retryUpload") && taskLayoutParametersDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = taskLayoutParametersDataObject.get("retryUpload").getAsInt();
			}
			if(taskLayoutParametersDataObject.has("retryChildEntitiesUpload") && taskLayoutParametersDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = taskLayoutParametersDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(taskLayoutParametersDataObject.has("partialUploadUnderProcess") && taskLayoutParametersDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = taskLayoutParametersDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("TaskLayoutParameters", retryCount);
	}
	public JsonObject uploadTaskLayoutParametersList(JsonArray taskLayoutParametersList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(taskLayoutParametersList);
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
	public JsonObject updateStatusMsg(JsonArray taskLayoutParametersList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < taskLayoutParametersList.size(); i++)
			{
				JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
				JsonObject taskLayoutParameters = taskLayoutParametersDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = taskLayoutParametersDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = taskLayoutParametersDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = taskLayoutParametersDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray taskLayoutParametersList) throws Exception
	
	{
		return uploadData(taskLayoutParametersList, -1);	
	}
	public JsonObject uploadData(JsonArray taskLayoutParametersList, Integer taskInfoId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < taskLayoutParametersList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject taskLayoutParametersDataObject = taskLayoutParametersList.get(i).getAsJsonObject();
				JsonObject taskLayoutParameters = taskLayoutParametersDataObject.get("dataObject").getAsJsonObject();
				taskLayoutParametersDataObject.addProperty("retryUpload", 0);
				taskLayoutParametersDataObject.addProperty("retryChildEntitiesUpload", 0);
				taskLayoutParametersDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = taskLayoutParametersImplObj.getEntityInfoUpdatedWithLookupIds(session, taskLayoutParameters, retValObject);
				if(areAllLookupsFound!=1)
				{
					taskLayoutParametersDataObject.addProperty("retryUpload", 1);
					taskLayoutParametersDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					taskLayoutParametersDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					taskLayoutParametersDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int taskLayoutParametersId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !taskLayoutParameters.has("taskLayoutParametersId"))
				{
					taskLayoutParameters.addProperty("attributeNamePrefix", "");
					
					taskLayoutParameters.addProperty("taskInfoId", taskInfoId);
					
					taskLayoutParameters.addProperty("attributeNamePrefix", "");
					JsonObject responseData = taskLayoutParametersImplObj.getTaskLayoutParametersByLookupFields(session,  taskLayoutParameters);
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
						JsonObject taskLayoutParametersObject = responseData.get("taskLayoutParametersDataObject").getAsJsonObject();
						taskLayoutParametersId = taskLayoutParametersObject.get("taskLayoutParametersId").getAsInt();
						taskLayoutParameters.addProperty("taskLayoutParametersId", taskLayoutParametersId);
						updateEntity = true;
					}
				}
				else if(taskLayoutParameters.has("taskLayoutParametersId"))
				{
					updateEntity = true;
				}
				
				if(taskInfoId > 0)
				{
					taskLayoutParameters.addProperty("taskInfoId", taskInfoId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = taskLayoutParametersImplObj.createTaskLayoutParameters(taskLayoutParameters);
				}
				else
				{
					responseData = taskLayoutParametersImplObj.updateTaskLayoutParameters(taskLayoutParameters);
				}
				taskLayoutParametersDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					taskLayoutParametersId =-1;
					taskLayoutParametersDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					taskLayoutParametersDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				taskLayoutParametersId = responseData.get("taskLayoutParametersId").getAsInt();
				taskLayoutParametersDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject taskLayoutParameters, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getTaskLayoutParametersByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer taskInfoId = -1;
			if(requestParams.has("taskInfoId") && requestParams.get("taskInfoId").isJsonNull()==false)
			{
				taskInfoId = requestParams.get("taskInfoId").getAsInt();
			}
			
			String hql = "From TaskLayoutParameters where 1 = 1   and taskInfoId = :taskInfoId ";
			String countHql = "select count(*)  from TaskLayoutParameters where 1 = 1  and taskInfoId = :taskInfoId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("taskInfoId", taskInfoId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Task Layout Parameters could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) it.next();
				JsonObject taskLayoutParametersDataObject = taskLayoutParameters.getDataObject(session);
				dataObject.add("taskLayoutParametersDataObject", taskLayoutParametersDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Task Layout Parameters could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Task Layout Parameters' could not be retrieved");
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
	
	public JsonObject deleteTaskLayoutParametersListIfKeyColumnsNotFound(Session session, Integer taskInfoId)
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
			String hql = "From TaskLayoutParameters WHERE taskInfoId = :taskInfoId ";
			Query query = session.createQuery(hql);
			query.setParameter("taskInfoId", taskInfoId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) it.next();
				int taskLayoutParametersId = taskLayoutParameters.getTaskLayoutParametersId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl taskLayoutParametersImplObj = new com.patientapp.controller.forms.impl.TaskLayoutParametersControllerImpl(session);
			    taskLayoutParametersImplObj.setPrivateManagedBean(taskLayoutParameters);
			    taskLayoutParametersImplObj.setManagedBean("TaskLayoutParameters", taskLayoutParameters);
			    taskLayoutParametersImplObj.delete();
				if (taskLayoutParametersImplObj.getHasTransactionFailed() == true)
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
		dataObject.addProperty("alert", "Your request could not be processed as Task Layout Parameters could not be deleted");
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
			else if(apiName.equals("getTaskLayoutParametersPropertyValue"))
			{
				JsonObject inputDataObject = getTaskLayoutParametersPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getTaskLayoutParameters"))
			{
				JsonObject inputDataObject = getTaskLayoutParameters(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForTaskLayoutParameters"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForTaskLayoutParameters"))
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
			Integer taskLayoutParametersId = requestReceivedParametersInfo.get("taskLayoutParametersId").getAsInt();
			TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
			taskLayoutParameters.setIsRequestUnderProcesss(0);
			session.merge(taskLayoutParameters);
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
	public JsonObject getTaskLayoutParametersPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskLayoutParametersId = inputForGetAPI.get("taskLayoutParametersId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		TaskLayoutParameters taskLayoutParameters = (TaskLayoutParameters) session.get(TaskLayoutParameters.class, taskLayoutParametersId);
		taskLayoutParameters.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getTaskLayoutParameters(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer taskLayoutParametersId = inputForGetAPI.get("taskLayoutParametersId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskLayoutParametersId", String.valueOf(taskLayoutParametersId));
		JsonObject taskLayoutParametersListObject = retrieveTaskLayoutParametersList(paramsMap);
		if(taskLayoutParametersListObject!=null && taskLayoutParametersListObject.has("success") && taskLayoutParametersListObject.get("success").getAsInt()==1)
		{
			JsonArray taskLayoutParametersList = taskLayoutParametersListObject.get("taskLayoutParametersList").getAsJsonArray();
			if(taskLayoutParametersList.size()>0)
			{
				dataObject.add("taskLayoutParameters", taskLayoutParametersList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Task Layout Parameters' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskLayoutParametersDataObject, TaskLayoutParameters taskLayoutParameters);
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
	public abstract void doAfterLookupRowSelectedImpl(TaskLayoutParameters taskLayoutParameters, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(TaskLayoutParameters taskLayoutParameters, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(TaskLayoutParameters taskLayoutParameters, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
