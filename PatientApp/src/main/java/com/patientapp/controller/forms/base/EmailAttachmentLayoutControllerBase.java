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

import com.patientapp.bean.EmailAttachmentLayout;
import com.patientapp.controller.forms.base.EmailAttachmentLayoutLabel;
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
public abstract class EmailAttachmentLayoutControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'EmailAttachmentLayoutName' 
	 *		 * 'Comments' 
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
	protected EmailAttachmentLayoutLabel EmailAttachmentLayoutLabelLocal = new EmailAttachmentLayoutLabel();
	protected EmailAttachmentLayout EmailAttachmentLayoutLocal = new EmailAttachmentLayout();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public EmailAttachmentLayoutControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public EmailAttachmentLayoutControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public EmailAttachmentLayoutControllerBase()
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
		return "EmailAttachmentLayout" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((EmailAttachmentLayout)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((EmailAttachmentLayout)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((EmailAttachmentLayout)obj).getEmailAttachmentLayoutId();
	}
	public String getManagedBeanName()
    {
		return "EmailAttachmentLayoutBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "EmailAttachmentLayoutLabelBean";
    }
	protected Class<EmailAttachmentLayout> setBeanClass()
	{
		return EmailAttachmentLayout.class;
	}
	protected Class<EmailAttachmentLayoutLabel> setBeanLabelClass()
	{
		return EmailAttachmentLayoutLabel.class;
	}
	protected EmailAttachmentLayout getLocalManagedBean()
    {
		return (EmailAttachmentLayout)getManagedBean();
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
		/*EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		String areChangesEffected = emailAttachmentLayout.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			emailAttachmentLayout.setAreChangesEffected("YES");			
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
		/*EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		String areChangesEffected = emailAttachmentLayout.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			emailAttachmentLayout.setAreChangesEffected("NO");			
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
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		String sCurrentStatus = emailAttachmentLayout.getVwTxnStatus();
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
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(emailAttachmentLayout, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterEmailAttachmentLayoutLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("emailAttachmentLayoutName") && initParamsInfo.get("emailAttachmentLayoutName").isJsonNull()==false)
		{
			String emailAttachmentLayoutName = initParamsInfo.get("emailAttachmentLayoutName").getAsString();
			emailAttachmentLayout.setEmailAttachmentLayoutName(emailAttachmentLayoutName);			
		}if(initParamsInfo.has("comments") && initParamsInfo.get("comments").isJsonNull()==false)
		{
			String comments = initParamsInfo.get("comments").getAsString();
			emailAttachmentLayout.setComments(comments);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(emailAttachmentLayout, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(emailAttachmentLayout, attributeName);
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
			EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "EmailAttachmentLayout", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() EmailAttachmentLayoutContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (EmailAttachmentLayoutSearch)getManagedBean("EmailAttachmentLayoutSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getEmailAttachmentLayoutName()))
			{
				searchParams.put(EmailAttachmentLayoutLabelLocal.getemailAttachmentLayoutNameFieldName(),searchBeanLocal.getEmailAttachmentLayoutName());
			}	
			if (isExists(searchBeanLocal.getComments()))
			{
				searchParams.put(EmailAttachmentLayoutLabelLocal.getcommentsFieldName(),searchBeanLocal.getComments());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(EmailAttachmentLayoutLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() EmailAttachmentLayoutContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues EmailAttachmentLayoutContollerBase");
		targetBean = (EmailAttachmentLayout)targetBean;TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
		((EmailAttachmentLayout)targetBean).setTaskInfoId(TaskInfoLocal.getTaskInfoId());
		((EmailAttachmentLayout)targetBean).setEmailAttachmentLayoutId(((EmailAttachmentLayout)sourceBean).getEmailAttachmentLayoutId());
				((EmailAttachmentLayout)targetBean).setEmailAttachmentLayoutName(((EmailAttachmentLayout)sourceBean).getEmailAttachmentLayoutName());
		((EmailAttachmentLayout)targetBean).setComments(((EmailAttachmentLayout)sourceBean).getComments());

		doAfterSetValues();
		debugCode("Out setValues EmailAttachmentLayoutContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.EmailAttachmentLayout entityBean = (EmailAttachmentLayout)entityObject;
	 	if (sFieldName.equalsIgnoreCase("emailAttachmentLayoutId")) 
	 	{
			return entityBean.getEmailAttachmentLayoutId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("EmailAttachmentLayoutName")) 
	 	{
			return entityBean.getEmailAttachmentLayoutName();
		}
	 	if (sFieldName.equalsIgnoreCase("Comments")) 
	 	{
			return entityBean.getComments();
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
		debugCode("In doEnrichSystemValues(String sAction) EmailAttachmentLayoutControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((EmailAttachmentLayout) localManagedBean).setVwLastModifiedDate(new Date());
		((EmailAttachmentLayout) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((EmailAttachmentLayout) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((EmailAttachmentLayout) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((EmailAttachmentLayout) localManagedBean).setVwTxnStatus("CREATED");
			((EmailAttachmentLayout) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((EmailAttachmentLayout) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((EmailAttachmentLayout) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) EmailAttachmentLayoutControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) EmailAttachmentLayoutControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) EmailAttachmentLayoutControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations EmailAttachmentLayoutControllerBase");
		//NG: Important comment
		//managedBean = (EmailAttachmentLayout) getManagedBean();
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
		debugCode("Out doValidations EmailAttachmentLayoutControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation EmailAttachmentLayoutControllerBase");
		debugCode("Out doAllowedDecimalValidation EmailAttachmentLayoutControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation EmailAttachmentLayoutControllerBase");debugCode("Out doAllowedValuesValidation EmailAttachmentLayoutControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation EmailAttachmentLayoutControllerBase");
		debugCode("Out doMandatoryValidation EmailAttachmentLayoutControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation EmailAttachmentLayoutControllerBase");
				
		String sFieldValue2 = ((EmailAttachmentLayout) localManagedBean).getEmailAttachmentLayoutName();String sFieldValue3 = ((EmailAttachmentLayout) localManagedBean).getComments();
		if (!isLengthAllowed(sFieldValue2,"500")) addMaxLengthResponse(EmailAttachmentLayoutLabelLocal.getemailAttachmentLayoutNameFieldName(),"500");
		if (!isLengthAllowed(sFieldValue3,"500")) addMaxLengthResponse(EmailAttachmentLayoutLabelLocal.getcommentsFieldName(),"500");
debugCode("Out doLengthValidation EmailAttachmentLayoutControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation EmailAttachmentLayoutControllerBase");
		debugCode("Out doDataTypeValidation EmailAttachmentLayoutControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation EmailAttachmentLayoutContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			TaskInfo TaskInfoLocal = (TaskInfo)getManagedBean("TaskInfoBean");
			if (TaskInfoLocal!=null)
			{
				iFieldValueFK = TaskInfoLocal.getTaskInfoId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation EmailAttachmentLayoutContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel EmailAttachmentLayoutContollerBase");
		String sLabel = new EmailAttachmentLayoutLabel().getLabel(sResponseField); 
		debugCode("Out getLabel EmailAttachmentLayoutContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int emailAttachmentLayoutId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
			JsonObject responseData = retrieveEmailAttachmentLayout(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Email Attachment Layout' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject emailAttachmentLayoutDataObject = responseData.get("emailAttachmentLayoutDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, emailAttachmentLayoutDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Email Attachment Layout' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveEmailAttachmentLayout(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer emailAttachmentLayoutId = -1;
		if(requestParams.has("emailAttachmentLayoutId"))
		{
			emailAttachmentLayoutId = requestParams.get("emailAttachmentLayoutId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("emailAttachmentLayoutId", String.valueOf(emailAttachmentLayoutId));
				String emailAttachmentLayoutName = "";
		if(requestParams.has("emailAttachmentLayoutName"))
		{
			paramsMap.put("emailAttachmentLayoutName", requestParams.get("emailAttachmentLayoutName").getAsString());
		}
		String comments = "";
		if(requestParams.has("comments"))
		{
			paramsMap.put("comments", requestParams.get("comments").getAsString());
		}
	
		Integer taskInfoId = -1;;
		if(requestParams.has("taskInfoId"))
		{
			paramsMap.put("taskInfoId", requestParams.get("taskInfoId").getAsString());
		}JsonObject emailAttachmentLayoutListObject = retrieveEmailAttachmentLayoutList(paramsMap);
		if(emailAttachmentLayoutListObject!=null && emailAttachmentLayoutListObject.has("success") && emailAttachmentLayoutListObject.get("success").getAsInt()==1)
		{
			JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObject.get("emailAttachmentLayoutList").getAsJsonArray();
			if(emailAttachmentLayoutList.size()>0)
			{
				dataObject.add("emailAttachmentLayoutDataObject", emailAttachmentLayoutList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Email Attachment Layout' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getEmailAttachmentLayout(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String emailAttachmentLayoutName = "";
			if(searchParams.has("emailAttachmentLayoutName"))
			{
				paramsMap.put("emailAttachmentLayoutName", searchParams.get("emailAttachmentLayoutName").getAsString());
			}
			String comments = "";
			if(searchParams.has("comments"))
			{
				paramsMap.put("comments", searchParams.get("comments").getAsString());
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
			JsonObject emailAttachmentLayoutListObject = retrieveEmailAttachmentLayoutList(paramsMap);
			if(emailAttachmentLayoutListObject!=null && emailAttachmentLayoutListObject.has("success") && emailAttachmentLayoutListObject.get("success").getAsInt()==1)
			{
				JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObject.get("emailAttachmentLayoutList").getAsJsonArray();
				if(emailAttachmentLayoutList.size()>0)
				{
					dataObject.add("emailAttachmentLayout", emailAttachmentLayoutList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Email Attachment Layout' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getEmailAttachmentLayoutInJson(int emailAttachmentLayoutId)
	{
		JsonObject EmailAttachmentLayoutData = null;
		List<Integer> emailAttachmentLayoutIdsList = new ArrayList<>();
		emailAttachmentLayoutIdsList.add(emailAttachmentLayoutId);
		JsonArray emailAttachmentLayoutList = getEmailAttachmentLayoutListInJson(emailAttachmentLayoutIdsList);
		if(emailAttachmentLayoutList!=null && emailAttachmentLayoutList.size()>0)
		{
			EmailAttachmentLayoutData = emailAttachmentLayoutList.get(0).getAsJsonObject();
		}
		return EmailAttachmentLayoutData;
	}
	public JsonArray getEmailAttachmentLayoutListInJson(List<Integer> emailAttachmentLayoutIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray emailAttachmentLayoutObjectsList = new JsonArray();
		JsonObject emailAttachmentLayoutListObject = retrieveEmailAttachmentLayoutList(paramsMap, emailAttachmentLayoutIdsList);
		if(emailAttachmentLayoutListObject!=null && emailAttachmentLayoutListObject.has("success") && emailAttachmentLayoutListObject.get("success").getAsInt()==1)
		{
			JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObject.get("emailAttachmentLayoutList").getAsJsonArray();
			for (int i =0; i< emailAttachmentLayoutList.size(); i++)
			{
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
				int emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
				
				emailAttachmentLayoutObjectsList.add(emailAttachmentLayoutDataObject);
			}
		}
		return emailAttachmentLayoutObjectsList;
	}
	
	public JsonArray getEmailAttachmentLayoutListFromParentInJson(int taskInfoId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("taskInfoId", String.valueOf(taskInfoId));
		JsonArray emailAttachmentLayoutObjectsList = new JsonArray();
		JsonObject emailAttachmentLayoutListObject = retrieveEmailAttachmentLayoutList(paramsMap);
		if(emailAttachmentLayoutListObject!=null && emailAttachmentLayoutListObject.has("success") && emailAttachmentLayoutListObject.get("success").getAsInt()==1)
		{
			JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObject.get("emailAttachmentLayoutList").getAsJsonArray();
			for (int i =0; i< emailAttachmentLayoutList.size(); i++)
			{
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
				int emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
				
			    emailAttachmentLayoutObjectsList.add(emailAttachmentLayoutDataObject);
			}
		}
		return emailAttachmentLayoutObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray emailAttachmentLayoutList)
	{
		String errorMessage = "emailAttachmentLayoutList: \n";
		for (int i =0; i< emailAttachmentLayoutList.size(); i++)
		{
			JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
			JsonObject emailAttachmentLayout = emailAttachmentLayoutDataObject.get("dataObject").getAsJsonObject();
			
			if(!emailAttachmentLayoutDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "emailAttachmentLayout could not be uploaded verify data \n"; 
			}
			else if(emailAttachmentLayoutDataObject.has("isSuccessfullyUploaded") 
					&& emailAttachmentLayoutDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += emailAttachmentLayoutDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("EmailAttachmentLayout".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND emailAttachmentLayoutId = :emailAttachmentLayoutId ";
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
		else if("EmailAttachmentLayout".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("emailAttachmentLayoutId", userId);
		}
		
	}
	public String getParentFilterClauseForEmailAttachmentLayout(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForEmailAttachmentLayout(java.util.Map<String, String> paramsMap, Query query)
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
	public JsonObject retrieveEmailAttachmentLayoutList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> emailAttachmentLayoutIdsList = new ArrayList<>();
		if(paramsMap.containsKey("emailAttachmentLayoutId"))
		{
			int emailAttachmentLayoutId = Integer.parseInt(paramsMap.get("emailAttachmentLayoutId"));
			if(emailAttachmentLayoutId>0)
			{
				emailAttachmentLayoutIdsList.add(emailAttachmentLayoutId);
			}
		}
		return retrieveEmailAttachmentLayoutList(paramsMap, emailAttachmentLayoutIdsList);
	}
	public JsonObject retrieveEmailAttachmentLayoutList(java.util.Map<String, String> paramsMap, List<Integer> emailAttachmentLayoutIdsList)
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
						String emailAttachmentLayoutName = paramsMap.get("emailAttachmentLayoutName");
			if(emailAttachmentLayoutName==null)
			{
				emailAttachmentLayoutName = "";
			}
			String comments = paramsMap.get("comments");
			if(comments==null)
			{
				comments = "";
			}

			
			String hql = "FROM EmailAttachmentLayout where 1 = 1 ";
			String orderByClauseText = "  ";
			String emailAttachmentLayoutIdFilterClass = "";
			if (emailAttachmentLayoutIdsList != null && emailAttachmentLayoutIdsList.size() > 0)
			{
				emailAttachmentLayoutIdFilterClass = " AND emailAttachmentLayoutId in (:idsList) ";
			}
						String emailAttachmentLayoutNameFilterClass = "";
			if (emailAttachmentLayoutName.length() > 0)
			{		
				
				emailAttachmentLayoutNameFilterClass = " AND emailAttachmentLayoutName LIKE :emailAttachmentLayoutName ";	
				
			}
			String commentsFilterClass = "";
			if (comments.length() > 0)
			{		
				
				commentsFilterClass = " AND comments LIKE :comments ";	
				
			}

			
			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForEmailAttachmentLayout(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					emailAttachmentLayoutIdFilterClass +
										emailAttachmentLayoutNameFilterClass +
					commentsFilterClass +

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
			if (emailAttachmentLayoutIdsList != null && emailAttachmentLayoutIdsList.size() > 0)
			{
				query.setParameterList("idsList", emailAttachmentLayoutIdsList);
			}
						if (emailAttachmentLayoutName.length() > 0)
			{		
				
				query.setParameter("emailAttachmentLayoutName", emailAttachmentLayoutName);	
				
			}
			if (comments.length() > 0)
			{		
				
				query.setParameter("comments", comments);	
				
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
	    	setParentFilterClauseParamsForEmailAttachmentLayout(paramsMap, query);
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
			JsonArray emailAttachmentLayoutList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) it.next();
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayout.getDataObject(getDBSession());
				emailAttachmentLayoutDataObject.addProperty("nextAction", getActionForCurrentStatus(emailAttachmentLayout.getVwTxnStatus()));
				emailAttachmentLayoutList.add(emailAttachmentLayoutDataObject);
				doAfterSearchResultRowAddedImpl(emailAttachmentLayoutDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from EmailAttachmentLayout where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (emailAttachmentLayoutIdsList != null && emailAttachmentLayoutIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", emailAttachmentLayoutIdsList);
				}
								if (emailAttachmentLayoutName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("emailAttachmentLayoutName", emailAttachmentLayoutName);
					
					
					
					
				}
				if (comments.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("comments", comments);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForEmailAttachmentLayout(paramsMap, countQuery);
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
			dataObject.add("emailAttachmentLayoutList",   emailAttachmentLayoutList);
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
				+ "   from EmailAttachmentLayout E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from EmailAttachmentLayout E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentEmailAttachmentLayoutList(java.util.Map<String, String> paramsMap)
	{
		return retrieveEmailAttachmentLayoutList(paramsMap);
	}
	public EmailAttachmentLayout getEmailAttachmentLayoutByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from EmailAttachmentLayout where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) it.next();
			return emailAttachmentLayout;
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
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout)getManagedBean();
		JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayout.getDataObject(getDBSession());copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject);
		setManagedBean(emailAttachmentLayout);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData EmailAttachmentLayoutContollerBase");
			EmailAttachmentLayout currentBean = (EmailAttachmentLayout)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "EmailAttachmentLayoutName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setEmailAttachmentLayoutName(sStringTestData);iFieldLength = 0;
		sFieldLength = "500";
		sStringTestData = "Comments".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setComments(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData EmailAttachmentLayoutContollerBase");
	}
	// end Test Data
	public void copyEmailAttachmentLayoutFromJson(EmailAttachmentLayout emailAttachmentLayout, JsonObject emailAttachmentLayoutDataObject)
	{
		copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject, false);
	}
	public void copyEmailAttachmentLayoutFromJson(EmailAttachmentLayout emailAttachmentLayout, JsonObject emailAttachmentLayoutDataObject, boolean excludePasswordFields)
	{	
				
		if(emailAttachmentLayoutDataObject.has("emailAttachmentLayoutName"))
		{
			String emailAttachmentLayoutName = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutName").getAsString();
			emailAttachmentLayout.setEmailAttachmentLayoutName(emailAttachmentLayoutName);
		}if(emailAttachmentLayoutDataObject.has("comments"))
		{
			String comments = emailAttachmentLayoutDataObject.get("comments").getAsString();
			emailAttachmentLayout.setComments(comments);
		}
		
	}
		
	public JsonObject createEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		return createEmailAttachmentLayout(emailAttachmentLayoutDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject emailAttachmentLayoutDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		EmailAttachmentLayout emailAttachmentLayout = new EmailAttachmentLayout();
		setLoginBasedValues(paramsInfoObj, emailAttachmentLayoutDataObject);
		Session session = getDBSession();				
		copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject);
		if(emailAttachmentLayoutDataObject.has("legacyRecordId"))
		{
			emailAttachmentLayout.setLegacyRecordId(emailAttachmentLayoutDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer taskInfoId = emailAttachmentLayoutDataObject.get("taskInfoId").getAsInt();
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, taskInfoId);
		emailAttachmentLayout.setTaskInfoId(taskInfoId);
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}emailAttachmentLayout.setVwCreatedBy(createdBy);
		emailAttachmentLayout.setVwCreationDate(new Date());
		setPrivateManagedBean(emailAttachmentLayout);
		setManagedBean("EmailAttachmentLayoutBean", emailAttachmentLayout);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		emailAttachmentLayout = (EmailAttachmentLayout) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("emailAttachmentLayoutId", emailAttachmentLayout.getEmailAttachmentLayoutId());
			JsonObject emailAttachmentLayoutObj = emailAttachmentLayout.getDataObject(getDBSession());
			emailAttachmentLayoutObj.addProperty("nextAction", getActionForCurrentStatus(emailAttachmentLayout.getVwTxnStatus()));
			dataObject.add("emailAttachmentLayoutDataObject", emailAttachmentLayoutObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
		JsonObject responseData = retrieveEmailAttachmentLayout(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Email Attachment Layout' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
		if(emailAttachmentLayout == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(emailAttachmentLayout.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromEmailAttachmentLayout(emailAttachmentLayoutDataObject);
		boolean excludePasswordFields = true;
		copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject, excludePasswordFields);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, emailAttachmentLayout.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(emailAttachmentLayout);
		setManagedBean("EmailAttachmentLayoutBean", emailAttachmentLayout);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		emailAttachmentLayout.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
	}
	public JsonObject deleteEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
		JsonObject responseData = retrieveEmailAttachmentLayout(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Email Attachment Layout' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
			
		com.patientapp.bean.TaskInfo taskInfo = (TaskInfo) session.get(TaskInfo.class, emailAttachmentLayout.getTaskInfoId());
		TaskInfoControllerImpl taskInfoImplObj = new TaskInfoControllerImpl(session);
		setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setManagedBean(taskInfoImplObj.getManagedBeanName(), taskInfo);
		taskInfoImplObj.setPrivateManagedBean(taskInfo);
		if(!taskInfoImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(taskInfoImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(emailAttachmentLayout);
		setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
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
	public JsonObject fetchEmailAttachmentLayoutDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		EmailAttachmentLayout emailAttachmentLayout = new EmailAttachmentLayout();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
			doAfterEmailAttachmentLayoutLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayout.getDataObject(getDBSession()));
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
	public JsonObject fetchEmailAttachmentLayoutTestData(int obj, JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		EmailAttachmentLayout emailAttachmentLayout = new EmailAttachmentLayout();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject);
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
			doAfterEmailAttachmentLayoutLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayout.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		EmailAttachmentLayout emailAttachmentLayout = new EmailAttachmentLayout();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject);	
			
			Integer taskInfoId = emailAttachmentLayoutDataObject.get("taskInfoId").getAsInt();
			emailAttachmentLayout.setTaskInfoId(taskInfoId);
			
			String attributeName = emailAttachmentLayoutDataObject.get("attributeName").getAsString();
			Integer attributeId = emailAttachmentLayoutDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayout.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		EmailAttachmentLayout emailAttachmentLayout = new EmailAttachmentLayout();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyEmailAttachmentLayoutFromJson(emailAttachmentLayout, emailAttachmentLayoutDataObject);	
			String attributeName = emailAttachmentLayoutDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayout.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
			String customEventName = emailAttachmentLayoutDataObject.get("customEventName").getAsString();
			EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayoutBean", emailAttachmentLayout);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayout.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnEmailAttachmentLayout(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer emailAttachmentLayoutId = emailAttachmentLayoutDataObject.get("emailAttachmentLayoutId").getAsInt();
			String currentStatus = emailAttachmentLayoutDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(emailAttachmentLayoutDataObject.has("currentAction"))
			{
				currentAction = emailAttachmentLayoutDataObject.get("currentAction").getAsString();
			}
			EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
			setPrivateManagedBean(emailAttachmentLayout);
			setManagedBean("EmailAttachmentLayoutBean", emailAttachmentLayout);
			if(emailAttachmentLayout.getIsRequestUnderProcesss() == 1)
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
				executeAction(emailAttachmentLayout, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(emailAttachmentLayout, "ClassInfoBean", currentStatus);
			}
//			executeAction(emailAttachmentLayout, "EmailAttachmentLayoutBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedemailAttachmentLayoutDataObject = emailAttachmentLayout.getDataObject(getDBSession());
				updatedemailAttachmentLayoutDataObject.addProperty("nextAction", getActionForCurrentStatus(emailAttachmentLayout.getVwTxnStatus()));
				dataObject.add("emailAttachmentLayout", updatedemailAttachmentLayoutDataObject);
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
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) getManagedBean();
		String currentStatus = emailAttachmentLayout.getVwTxnStatus();
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
	
	
	public JsonObject downloadEmailAttachmentLayoutData() throws Exception
	{
		return downloadEmailAttachmentLayoutData(null);
	}
	public JsonObject downloadEmailAttachmentLayoutData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadEmailAttachmentLayoutData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadEmailAttachmentLayoutData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer taskInfoId) throws Exception
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
			headerName = "EmailAttachmentLayout";
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
			headerName = "emailAttachmentLayoutId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "emailAttachmentLayoutName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "comments";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From EmailAttachmentLayout ";
			
			hql += " WHERE taskInfoId  = :taskInfoId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer emailAttachmentLayoutId = emailAttachmentLayout.getEmailAttachmentLayoutId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(emailAttachmentLayoutId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String emailAttachmentLayoutName = emailAttachmentLayout.getEmailAttachmentLayoutName();
				cell.setCellValue(emailAttachmentLayoutName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String comments = emailAttachmentLayout.getComments();
				cell.setCellValue(comments);

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
	public JsonObject uploadEmailAttachmentLayoutData(JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = emailAttachmentLayoutDataObject.get("fileId").getAsInt();
		String inputFilesZip = emailAttachmentLayoutDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Email Attachment Layout Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		emailAttachmentLayoutDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadEmailAttachmentLayoutData(workbook, emailAttachmentLayoutDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Email Attachment Layout Data uploaded successfully.");
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
	public JsonObject uploadEmailAttachmentLayoutData(HSSFWorkbook workbook, JsonObject emailAttachmentLayoutDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("EmailAttachmentLayout");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = emailAttachmentLayoutDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = emailAttachmentLayoutDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = emailAttachmentLayoutDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Email Attachment Layout Data uploaded successfully.");
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
			JsonObject emailAttachmentLayout = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject emailAttachmentLayoutListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObj.get("emailAttachmentLayoutList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadEmailAttachmentLayoutList(emailAttachmentLayoutList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(emailAttachmentLayoutList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(emailAttachmentLayoutList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray emailAttachmentLayoutList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject emailAttachmentLayout = new JsonObject();
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
				JsonObject emailAttachmentLayoutUploadObj	= new JsonObject();
				emailAttachmentLayoutUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					emailAttachmentLayout = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("emailAttachmentLayoutId"))
						{
							String emailAttachmentLayoutId = row.getCell(cellIndex++).getStringCellValue();
							if(emailAttachmentLayoutId != null && emailAttachmentLayoutId.trim().length() > 0)
							{
								try
								{
									Integer iEmailAttachmentLayoutId = Integer.parseInt(emailAttachmentLayoutId);
									if(areSourceDestinationSame == 1)
									{
										EmailAttachmentLayout emailAttachmentLayoutObj = (EmailAttachmentLayout)session.get(EmailAttachmentLayout.class, iEmailAttachmentLayoutId);
										if(emailAttachmentLayoutObj != null)
										{ 
											emailAttachmentLayout.addProperty("emailAttachmentLayoutId", iEmailAttachmentLayoutId);
										}
										else
										{
											emailAttachmentLayoutUploadObj.addProperty("isDataFetched", 0);
											emailAttachmentLayoutUploadObj.addProperty("msg", "This Email Attachment Layout could not be uploaded as there appears to be some problem with the data(EmailAttachmentLayout Id is not exist). ");
											continue;
										}
									}
									else
									{
										EmailAttachmentLayout emailAttachmentLayoutObj = getEmailAttachmentLayoutByLegacyRecordId(session, iEmailAttachmentLayoutId);
										if(emailAttachmentLayoutObj != null)
										{ 
											emailAttachmentLayout.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutObj.getEmailAttachmentLayoutId());
										}
										emailAttachmentLayout.addProperty("legacyRecordId", iEmailAttachmentLayoutId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									emailAttachmentLayoutUploadObj.addProperty("isDataFetched", 0);
									emailAttachmentLayoutUploadObj.addProperty("msg", "This Email Attachment Layout could not be uploaded as there appears to be some problem with the data(EmailAttachmentLayout Id). ");
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
							emailAttachmentLayout.addProperty(parameterName, parameterValue);
						}
					}
					emailAttachmentLayoutUploadObj.add("dataObject", emailAttachmentLayout);		    
					emailAttachmentLayoutList.add(emailAttachmentLayoutUploadObj);
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
			dataObject.add("emailAttachmentLayoutList", emailAttachmentLayoutList);
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
		if(previousRetryCountMap.has("EmailAttachmentLayout") && previousRetryCountMap.get("EmailAttachmentLayout").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("EmailAttachmentLayout").getAsInt();
		}
		if(retryCountMap.has("EmailAttachmentLayout") && retryCountMap.get("EmailAttachmentLayout").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("EmailAttachmentLayout").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForEmailAttachmentLayoutList(JsonArray emailAttachmentLayoutList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < emailAttachmentLayoutList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
			JsonObject emailAttachmentLayout = emailAttachmentLayoutDataObject.get("dataObject").getAsJsonObject();
			if(emailAttachmentLayoutDataObject.has("retryUpload") && emailAttachmentLayoutDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = emailAttachmentLayoutDataObject.get("retryUpload").getAsInt();
			}
			if(emailAttachmentLayoutDataObject.has("retryChildEntitiesUpload") && emailAttachmentLayoutDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = emailAttachmentLayoutDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(emailAttachmentLayoutDataObject.has("partialUploadUnderProcess") && emailAttachmentLayoutDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = emailAttachmentLayoutDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("EmailAttachmentLayout", retryCount);
	}
	public JsonObject uploadEmailAttachmentLayoutList(JsonArray emailAttachmentLayoutList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(emailAttachmentLayoutList);
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
	public JsonObject updateStatusMsg(JsonArray emailAttachmentLayoutList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < emailAttachmentLayoutList.size(); i++)
			{
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
				JsonObject emailAttachmentLayout = emailAttachmentLayoutDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = emailAttachmentLayoutDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = emailAttachmentLayoutDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = emailAttachmentLayoutDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray emailAttachmentLayoutList) throws Exception
	
	{
		return uploadData(emailAttachmentLayoutList, -1);	
	}
	public JsonObject uploadData(JsonArray emailAttachmentLayoutList, Integer taskInfoId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < emailAttachmentLayoutList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayoutList.get(i).getAsJsonObject();
				JsonObject emailAttachmentLayout = emailAttachmentLayoutDataObject.get("dataObject").getAsJsonObject();
				emailAttachmentLayoutDataObject.addProperty("retryUpload", 0);
				emailAttachmentLayoutDataObject.addProperty("retryChildEntitiesUpload", 0);
				emailAttachmentLayoutDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = emailAttachmentLayoutImplObj.getEntityInfoUpdatedWithLookupIds(session, emailAttachmentLayout, retValObject);
				if(areAllLookupsFound!=1)
				{
					emailAttachmentLayoutDataObject.addProperty("retryUpload", 1);
					emailAttachmentLayoutDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					emailAttachmentLayoutDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					emailAttachmentLayoutDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int emailAttachmentLayoutId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !emailAttachmentLayout.has("emailAttachmentLayoutId"))
				{
					emailAttachmentLayout.addProperty("attributeNamePrefix", "");
					
					emailAttachmentLayout.addProperty("taskInfoId", taskInfoId);
					
					emailAttachmentLayout.addProperty("attributeNamePrefix", "");
					JsonObject responseData = emailAttachmentLayoutImplObj.getEmailAttachmentLayoutByLookupFields(session,  emailAttachmentLayout);
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
						JsonObject emailAttachmentLayoutObject = responseData.get("emailAttachmentLayoutDataObject").getAsJsonObject();
						emailAttachmentLayoutId = emailAttachmentLayoutObject.get("emailAttachmentLayoutId").getAsInt();
						emailAttachmentLayout.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
						updateEntity = true;
					}
				}
				else if(emailAttachmentLayout.has("emailAttachmentLayoutId"))
				{
					updateEntity = true;
				}
				
				if(taskInfoId > 0)
				{
					emailAttachmentLayout.addProperty("taskInfoId", taskInfoId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = emailAttachmentLayoutImplObj.createEmailAttachmentLayout(emailAttachmentLayout);
				}
				else
				{
					responseData = emailAttachmentLayoutImplObj.updateEmailAttachmentLayout(emailAttachmentLayout);
				}
				emailAttachmentLayoutDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					emailAttachmentLayoutId =-1;
					emailAttachmentLayoutDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					emailAttachmentLayoutDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				emailAttachmentLayoutId = responseData.get("emailAttachmentLayoutId").getAsInt();
				emailAttachmentLayoutDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject emailAttachmentLayout, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getEmailAttachmentLayoutByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer taskInfoId = -1;
			if(requestParams.has("taskInfoId") && requestParams.get("taskInfoId").isJsonNull()==false)
			{
				taskInfoId = requestParams.get("taskInfoId").getAsInt();
			}
			
			String hql = "From EmailAttachmentLayout where 1 = 1   and taskInfoId = :taskInfoId ";
			String countHql = "select count(*)  from EmailAttachmentLayout where 1 = 1  and taskInfoId = :taskInfoId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("taskInfoId", taskInfoId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Email Attachment Layout could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("taskInfoId", taskInfoId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) it.next();
				JsonObject emailAttachmentLayoutDataObject = emailAttachmentLayout.getDataObject(session);
				dataObject.add("emailAttachmentLayoutDataObject", emailAttachmentLayoutDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Email Attachment Layout could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Email Attachment Layout' could not be retrieved");
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
	
	public JsonObject deleteEmailAttachmentLayoutListIfKeyColumnsNotFound(Session session, Integer taskInfoId)
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
			String hql = "From EmailAttachmentLayout WHERE taskInfoId = :taskInfoId ";
			Query query = session.createQuery(hql);
			query.setParameter("taskInfoId", taskInfoId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) it.next();
				int emailAttachmentLayoutId = emailAttachmentLayout.getEmailAttachmentLayoutId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl emailAttachmentLayoutImplObj = new com.patientapp.controller.forms.impl.EmailAttachmentLayoutControllerImpl(session);
			    emailAttachmentLayoutImplObj.setPrivateManagedBean(emailAttachmentLayout);
			    emailAttachmentLayoutImplObj.setManagedBean("EmailAttachmentLayout", emailAttachmentLayout);
			    emailAttachmentLayoutImplObj.delete();
				if (emailAttachmentLayoutImplObj.getHasTransactionFailed() == true)
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
		dataObject.addProperty("alert", "Your request could not be processed as Email Attachment Layout could not be deleted");
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
			else if(apiName.equals("getEmailAttachmentLayoutPropertyValue"))
			{
				JsonObject inputDataObject = getEmailAttachmentLayoutPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getEmailAttachmentLayout"))
			{
				JsonObject inputDataObject = getEmailAttachmentLayout(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForEmailAttachmentLayout"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForEmailAttachmentLayout"))
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
			Integer emailAttachmentLayoutId = requestReceivedParametersInfo.get("emailAttachmentLayoutId").getAsInt();
			EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
			emailAttachmentLayout.setIsRequestUnderProcesss(0);
			session.merge(emailAttachmentLayout);
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
	public JsonObject getEmailAttachmentLayoutPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer emailAttachmentLayoutId = inputForGetAPI.get("emailAttachmentLayoutId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		EmailAttachmentLayout emailAttachmentLayout = (EmailAttachmentLayout) session.get(EmailAttachmentLayout.class, emailAttachmentLayoutId);
		emailAttachmentLayout.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getEmailAttachmentLayout(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer emailAttachmentLayoutId = inputForGetAPI.get("emailAttachmentLayoutId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("emailAttachmentLayoutId", String.valueOf(emailAttachmentLayoutId));
		JsonObject emailAttachmentLayoutListObject = retrieveEmailAttachmentLayoutList(paramsMap);
		if(emailAttachmentLayoutListObject!=null && emailAttachmentLayoutListObject.has("success") && emailAttachmentLayoutListObject.get("success").getAsInt()==1)
		{
			JsonArray emailAttachmentLayoutList = emailAttachmentLayoutListObject.get("emailAttachmentLayoutList").getAsJsonArray();
			if(emailAttachmentLayoutList.size()>0)
			{
				dataObject.add("emailAttachmentLayout", emailAttachmentLayoutList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Email Attachment Layout' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject emailAttachmentLayoutDataObject, EmailAttachmentLayout emailAttachmentLayout);
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
	public abstract void doAfterLookupRowSelectedImpl(EmailAttachmentLayout emailAttachmentLayout, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(EmailAttachmentLayout emailAttachmentLayout, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(EmailAttachmentLayout emailAttachmentLayout, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
