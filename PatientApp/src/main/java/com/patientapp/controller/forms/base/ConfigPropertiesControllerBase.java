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

import com.patientapp.bean.ConfigProperties;
import com.patientapp.controller.forms.base.ConfigPropertiesLabel;
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
public abstract class ConfigPropertiesControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'PropertyName' 
	 *		 * 'PropertyValue' 
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
	protected ConfigPropertiesLabel ConfigPropertiesLabelLocal = new ConfigPropertiesLabel();
	protected ConfigProperties ConfigPropertiesLocal = new ConfigProperties();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public ConfigPropertiesControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public ConfigPropertiesControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public ConfigPropertiesControllerBase()
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
		return "ConfigProperties" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((ConfigProperties)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((ConfigProperties)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((ConfigProperties)obj).getConfigPropertiesId();
	}
	public String getManagedBeanName()
    {
		return "ConfigPropertiesBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "ConfigPropertiesLabelBean";
    }
	protected Class<ConfigProperties> setBeanClass()
	{
		return ConfigProperties.class;
	}
	protected Class<ConfigPropertiesLabel> setBeanLabelClass()
	{
		return ConfigPropertiesLabel.class;
	}
	protected ConfigProperties getLocalManagedBean()
    {
		return (ConfigProperties)getManagedBean();
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
		/*ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		String areChangesEffected = configProperties.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			configProperties.setAreChangesEffected("YES");			
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
		/*ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		String areChangesEffected = configProperties.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			configProperties.setAreChangesEffected("NO");			
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
		/*ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		String areChangesEffected = configProperties.getAreChangesEffected();
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
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		String sCurrentStatus = configProperties.getVwTxnStatus();
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
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(configProperties, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterConfigPropertiesLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("propertyName") && initParamsInfo.get("propertyName").isJsonNull()==false)
		{
			String propertyName = initParamsInfo.get("propertyName").getAsString();
			configProperties.setPropertyName(propertyName);			
		}if(initParamsInfo.has("propertyValue") && initParamsInfo.get("propertyValue").isJsonNull()==false)
		{
			String propertyValue = initParamsInfo.get("propertyValue").getAsString();
			configProperties.setPropertyValue(propertyValue);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(configProperties, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(configProperties, attributeName);
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
			ConfigProperties configProperties = (ConfigProperties)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "ConfigProperties", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() ConfigPropertiesContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (ConfigPropertiesSearch)getManagedBean("ConfigPropertiesSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getPropertyName()))
			{
				searchParams.put(ConfigPropertiesLabelLocal.getpropertyNameFieldName(),searchBeanLocal.getPropertyName());
			}	
			if (isExists(searchBeanLocal.getPropertyValue()))
			{
				searchParams.put(ConfigPropertiesLabelLocal.getpropertyValueFieldName(),searchBeanLocal.getPropertyValue());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(ConfigPropertiesLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() ConfigPropertiesContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues ConfigPropertiesContollerBase");
		targetBean = (ConfigProperties)targetBean;((ConfigProperties)targetBean).setConfigPropertiesId(((ConfigProperties)sourceBean).getConfigPropertiesId());
				((ConfigProperties)targetBean).setPropertyName(((ConfigProperties)sourceBean).getPropertyName());
		((ConfigProperties)targetBean).setPropertyValue(((ConfigProperties)sourceBean).getPropertyValue());

		doAfterSetValues();
		debugCode("Out setValues ConfigPropertiesContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.ConfigProperties entityBean = (ConfigProperties)entityObject;
	 	if (sFieldName.equalsIgnoreCase("configPropertiesId")) 
	 	{
			return entityBean.getConfigPropertiesId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("PropertyName")) 
	 	{
			return entityBean.getPropertyName();
		}
	 	if (sFieldName.equalsIgnoreCase("PropertyValue")) 
	 	{
			return entityBean.getPropertyValue();
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
		debugCode("In doEnrichSystemValues(String sAction) ConfigPropertiesControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((ConfigProperties) localManagedBean).setVwLastModifiedDate(new Date());
		((ConfigProperties) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((ConfigProperties) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((ConfigProperties) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((ConfigProperties) localManagedBean).setVwTxnStatus("CREATED");
			((ConfigProperties) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((ConfigProperties) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((ConfigProperties) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) ConfigPropertiesControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) ConfigPropertiesControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) ConfigPropertiesControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations ConfigPropertiesControllerBase");
		//NG: Important comment
		//managedBean = (ConfigProperties) getManagedBean();
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
		debugCode("Out doValidations ConfigPropertiesControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation ConfigPropertiesControllerBase");
		debugCode("Out doAllowedDecimalValidation ConfigPropertiesControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation ConfigPropertiesControllerBase");debugCode("Out doAllowedValuesValidation ConfigPropertiesControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation ConfigPropertiesControllerBase");
				
		String sFieldValue2 = ((ConfigProperties) localManagedBean).getPropertyName();String sFieldValue3 = ((ConfigProperties) localManagedBean).getPropertyValue();
		if (!isExists(sFieldValue2)) addMandatoryResponse(ConfigPropertiesLabelLocal.getpropertyNameFieldName());
		if (!isExists(sFieldValue3)) addMandatoryResponse(ConfigPropertiesLabelLocal.getpropertyValueFieldName());
debugCode("Out doMandatoryValidation ConfigPropertiesControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation ConfigPropertiesControllerBase");
				
		String sFieldValue2 = ((ConfigProperties) localManagedBean).getPropertyName();String sFieldValue3 = ((ConfigProperties) localManagedBean).getPropertyValue();
		if (!isLengthAllowed(sFieldValue2,"100")) addMaxLengthResponse(ConfigPropertiesLabelLocal.getpropertyNameFieldName(),"100");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(ConfigPropertiesLabelLocal.getpropertyValueFieldName(),"50");
debugCode("Out doLengthValidation ConfigPropertiesControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation ConfigPropertiesControllerBase");
		debugCode("Out doDataTypeValidation ConfigPropertiesControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation ConfigPropertiesContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation ConfigPropertiesContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel ConfigPropertiesContollerBase");
		String sLabel = new ConfigPropertiesLabel().getLabel(sResponseField); 
		debugCode("Out getLabel ConfigPropertiesContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int configPropertiesId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("configPropertiesId", configPropertiesId);
			JsonObject responseData = retrieveConfigProperties(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Properties' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject configPropertiesDataObject = responseData.get("configPropertiesDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, configPropertiesDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Properties' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveConfigProperties(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer configPropertiesId = -1;
		if(requestParams.has("configPropertiesId"))
		{
			configPropertiesId = requestParams.get("configPropertiesId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("configPropertiesId", String.valueOf(configPropertiesId));
				String propertyName = "";
		if(requestParams.has("propertyName"))
		{
			paramsMap.put("propertyName", requestParams.get("propertyName").getAsString());
		}
		String propertyValue = "";
		if(requestParams.has("propertyValue"))
		{
			paramsMap.put("propertyValue", requestParams.get("propertyValue").getAsString());
		}

		JsonObject configPropertiesListObject = retrieveConfigPropertiesList(paramsMap);
		if(configPropertiesListObject!=null && configPropertiesListObject.has("success") && configPropertiesListObject.get("success").getAsInt()==1)
		{
			JsonArray configPropertiesList = configPropertiesListObject.get("configPropertiesList").getAsJsonArray();
			if(configPropertiesList.size()>0)
			{
				dataObject.add("configPropertiesDataObject", configPropertiesList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Properties' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getConfigProperties(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String propertyName = "";
			if(searchParams.has("propertyName"))
			{
				paramsMap.put("propertyName", searchParams.get("propertyName").getAsString());
			}
			String propertyValue = "";
			if(searchParams.has("propertyValue"))
			{
				paramsMap.put("propertyValue", searchParams.get("propertyValue").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject configPropertiesListObject = retrieveConfigPropertiesList(paramsMap);
			if(configPropertiesListObject!=null && configPropertiesListObject.has("success") && configPropertiesListObject.get("success").getAsInt()==1)
			{
				JsonArray configPropertiesList = configPropertiesListObject.get("configPropertiesList").getAsJsonArray();
				if(configPropertiesList.size()>0)
				{
					dataObject.add("configProperties", configPropertiesList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Properties' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getConfigPropertiesInJson(int configPropertiesId)
	{
		JsonObject ConfigPropertiesData = null;
		List<Integer> configPropertiesIdsList = new ArrayList<>();
		configPropertiesIdsList.add(configPropertiesId);
		JsonArray configPropertiesList = getConfigPropertiesListInJson(configPropertiesIdsList);
		if(configPropertiesList!=null && configPropertiesList.size()>0)
		{
			ConfigPropertiesData = configPropertiesList.get(0).getAsJsonObject();
		}
		return ConfigPropertiesData;
	}
	public JsonArray getConfigPropertiesListInJson(List<Integer> configPropertiesIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray configPropertiesObjectsList = new JsonArray();
		JsonObject configPropertiesListObject = retrieveConfigPropertiesList(paramsMap, configPropertiesIdsList);
		if(configPropertiesListObject!=null && configPropertiesListObject.has("success") && configPropertiesListObject.get("success").getAsInt()==1)
		{
			JsonArray configPropertiesList = configPropertiesListObject.get("configPropertiesList").getAsJsonArray();
			for (int i =0; i< configPropertiesList.size(); i++)
			{
				JsonObject configPropertiesDataObject = configPropertiesList.get(i).getAsJsonObject();
				int configPropertiesId = configPropertiesDataObject.get("configPropertiesId").getAsInt();
				
				configPropertiesObjectsList.add(configPropertiesDataObject);
			}
		}
		return configPropertiesObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray configPropertiesList)
	{
		String errorMessage = "configPropertiesList: \n";
		for (int i =0; i< configPropertiesList.size(); i++)
		{
			JsonObject configPropertiesDataObject = configPropertiesList.get(i).getAsJsonObject();
			JsonObject configProperties = configPropertiesDataObject.get("dataObject").getAsJsonObject();
						
			errorMessage += "Property Name : "+ configProperties.get("propertyName").getAsString();errorMessage += "Property Value : "+ configProperties.get("propertyValue").getAsString();

			if(!configPropertiesDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "configProperties could not be uploaded verify data \n"; 
			}
			else if(configPropertiesDataObject.has("isSuccessfullyUploaded") 
					&& configPropertiesDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += configPropertiesDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("ConfigProperties".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND configPropertiesId = :configPropertiesId ";
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
		else if("ConfigProperties".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("configPropertiesId", userId);
		}
		
	}
	public String getParentFilterClauseForConfigProperties(java.util.Map<String, String> paramsMap)
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
		lookupDisplayQueryColumn += "propertyName";
		i++;
		if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "propertyValue";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForConfigProperties(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveConfigPropertiesList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> configPropertiesIdsList = new ArrayList<>();
		if(paramsMap.containsKey("configPropertiesId"))
		{
			int configPropertiesId = Integer.parseInt(paramsMap.get("configPropertiesId"));
			if(configPropertiesId>0)
			{
				configPropertiesIdsList.add(configPropertiesId);
			}
		}
		return retrieveConfigPropertiesList(paramsMap, configPropertiesIdsList);
	}
	public JsonObject retrieveConfigPropertiesList(java.util.Map<String, String> paramsMap, List<Integer> configPropertiesIdsList)
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
						String propertyName = paramsMap.get("propertyName");
			if(propertyName==null)
			{
				propertyName = "";
			}
			String propertyValue = paramsMap.get("propertyValue");
			if(propertyValue==null)
			{
				propertyValue = "";
			}
String hql = "FROM ConfigProperties where 1 = 1 ";
			String orderByClauseText = "  ";
			String configPropertiesIdFilterClass = "";
			if (configPropertiesIdsList != null && configPropertiesIdsList.size() > 0)
			{
				configPropertiesIdFilterClass = " AND configPropertiesId in (:idsList) ";
			}
						String propertyNameFilterClass = "";
			if (propertyName.length() > 0)
			{		
				
				propertyNameFilterClass = " AND propertyName LIKE :propertyName ";	
				
			}
			String propertyValueFilterClass = "";
			if (propertyValue.length() > 0)
			{		
				
				propertyValueFilterClass = " AND propertyValue LIKE :propertyValue ";	
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForConfigProperties(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					configPropertiesIdFilterClass +
										propertyNameFilterClass +
					propertyValueFilterClass +

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
			if (configPropertiesIdsList != null && configPropertiesIdsList.size() > 0)
			{
				query.setParameterList("idsList", configPropertiesIdsList);
			}
						if (propertyName.length() > 0)
			{		
				
				query.setParameter("propertyName", propertyName);	
				
			}
			if (propertyValue.length() > 0)
			{		
				
				query.setParameter("propertyValue", propertyValue);	
				
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
	    	setParentFilterClauseParamsForConfigProperties(paramsMap, query);
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
			JsonArray configPropertiesList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				ConfigProperties configProperties = (ConfigProperties) it.next();
				JsonObject configPropertiesDataObject = configProperties.getDataObject(getDBSession());
				configPropertiesDataObject.addProperty("nextAction", getActionForCurrentStatus(configProperties.getVwTxnStatus()));
				configPropertiesList.add(configPropertiesDataObject);
				doAfterSearchResultRowAddedImpl(configPropertiesDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from ConfigProperties where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (configPropertiesIdsList != null && configPropertiesIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", configPropertiesIdsList);
				}
								if (propertyName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("propertyName", propertyName);
					
					
					
					
				}
				if (propertyValue.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("propertyValue", propertyValue);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForConfigProperties(paramsMap, countQuery);
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
			dataObject.add("configPropertiesList",   configPropertiesList);
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
				+ "   from ConfigProperties E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from ConfigProperties E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentConfigPropertiesList(java.util.Map<String, String> paramsMap)
	{
		return retrieveConfigPropertiesList(paramsMap);
	}
	public ConfigProperties getConfigPropertiesByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from ConfigProperties where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			ConfigProperties configProperties = (ConfigProperties) it.next();
			return configProperties;
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
		ConfigProperties configProperties = (ConfigProperties)getManagedBean();
		JsonObject configPropertiesDataObject = configProperties.getDataObject(getDBSession());copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject);
		setManagedBean(configProperties);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData ConfigPropertiesContollerBase");
			ConfigProperties currentBean = (ConfigProperties)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "100";
		sStringTestData = "PropertyName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPropertyName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PropertyValue".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPropertyValue(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData ConfigPropertiesContollerBase");
	}
	// end Test Data
	public void copyConfigPropertiesFromJson(ConfigProperties configProperties, JsonObject configPropertiesDataObject)
	{
		copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject, false);
	}
	public void copyConfigPropertiesFromJson(ConfigProperties configProperties, JsonObject configPropertiesDataObject, boolean excludePasswordFields)
	{	
				
		if(configPropertiesDataObject.has("propertyName"))
		{
			String propertyName = configPropertiesDataObject.get("propertyName").getAsString();
			configProperties.setPropertyName(propertyName);
		}if(configPropertiesDataObject.has("propertyValue"))
		{
			String propertyValue = configPropertiesDataObject.get("propertyValue").getAsString();
			configProperties.setPropertyValue(propertyValue);
		}
		
	}
		
	public JsonObject createConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		return createConfigProperties(configPropertiesDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject configPropertiesDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createConfigProperties(JsonObject configPropertiesDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		ConfigProperties configProperties = new ConfigProperties();
		setLoginBasedValues(paramsInfoObj, configPropertiesDataObject);
		Session session = getDBSession();				
		copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject);
		if(configPropertiesDataObject.has("legacyRecordId"))
		{
			configProperties.setLegacyRecordId(configPropertiesDataObject.get("legacyRecordId").getAsInt());
		}
				configProperties.setVwCreatedBy(createdBy);
		configProperties.setVwCreationDate(new Date());
		setPrivateManagedBean(configProperties);
		setManagedBean("ConfigPropertiesBean", configProperties);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		configProperties = (ConfigProperties) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("configPropertiesId", configProperties.getConfigPropertiesId());
			JsonObject configPropertiesObj = configProperties.getDataObject(getDBSession());
			configPropertiesObj.addProperty("nextAction", getActionForCurrentStatus(configProperties.getVwTxnStatus()));
			dataObject.add("configPropertiesDataObject", configPropertiesObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer configPropertiesId = configPropertiesDataObject.get("configPropertiesId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("configPropertiesId", configPropertiesId);
		JsonObject responseData = retrieveConfigProperties(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Properties' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);
		if(configProperties == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(configProperties.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromConfigProperties(configPropertiesDataObject);
		boolean excludePasswordFields = true;
		copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject, excludePasswordFields);setPrivateManagedBean(configProperties);
		setManagedBean("ConfigPropertiesBean", configProperties);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		configProperties.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("configPropertiesId", configPropertiesId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
	}
	public JsonObject deleteConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer configPropertiesId = configPropertiesDataObject.get("configPropertiesId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("configPropertiesId", configPropertiesId);
		JsonObject responseData = retrieveConfigProperties(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Properties' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);setPrivateManagedBean(configProperties);
		setManagedBean("ConfigProperties", configProperties);
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
	public JsonObject fetchConfigPropertiesDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		ConfigProperties configProperties = new ConfigProperties();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigProperties", configProperties);
			doAfterConfigPropertiesLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("configProperties", configProperties.getDataObject(getDBSession()));
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
	public JsonObject fetchConfigPropertiesTestData(int obj, JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		ConfigProperties configProperties = new ConfigProperties();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject);
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigProperties", configProperties);
			doAfterConfigPropertiesLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("configProperties", configProperties.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		ConfigProperties configProperties = new ConfigProperties();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject);	String attributeName = configPropertiesDataObject.get("attributeName").getAsString();
			Integer attributeId = configPropertiesDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigProperties", configProperties);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("configProperties", configProperties.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		ConfigProperties configProperties = new ConfigProperties();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyConfigPropertiesFromJson(configProperties, configPropertiesDataObject);	
			String attributeName = configPropertiesDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigProperties", configProperties);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("configProperties", configProperties.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer configPropertiesId = configPropertiesDataObject.get("configPropertiesId").getAsInt();
			String customEventName = configPropertiesDataObject.get("customEventName").getAsString();
			ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigPropertiesBean", configProperties);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("configProperties", configProperties.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnConfigProperties(JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer configPropertiesId = configPropertiesDataObject.get("configPropertiesId").getAsInt();
			String currentStatus = configPropertiesDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(configPropertiesDataObject.has("currentAction"))
			{
				currentAction = configPropertiesDataObject.get("currentAction").getAsString();
			}
			ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);
			setPrivateManagedBean(configProperties);
			setManagedBean("ConfigPropertiesBean", configProperties);
			if(configProperties.getIsRequestUnderProcesss() == 1)
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
				executeAction(configProperties, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(configProperties, "ClassInfoBean", currentStatus);
			}
//			executeAction(configProperties, "ConfigPropertiesBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedconfigPropertiesDataObject = configProperties.getDataObject(getDBSession());
				updatedconfigPropertiesDataObject.addProperty("nextAction", getActionForCurrentStatus(configProperties.getVwTxnStatus()));
				dataObject.add("configProperties", updatedconfigPropertiesDataObject);
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
		ConfigProperties configProperties = (ConfigProperties) getManagedBean();
		String currentStatus = configProperties.getVwTxnStatus();
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
	
	
	public JsonObject downloadConfigPropertiesData() throws Exception
	{
		return downloadConfigPropertiesData(null);
	}
	public JsonObject downloadConfigPropertiesData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "ConfigProperties");
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
			headerName = "configPropertiesId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "propertyName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "propertyValue";
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
			String hql = "From ConfigProperties ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				ConfigProperties configProperties = (ConfigProperties) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer configPropertiesId = configProperties.getConfigPropertiesId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(configPropertiesId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String propertyName = configProperties.getPropertyName();
				cell.setCellValue(propertyName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String propertyValue = configProperties.getPropertyValue();
				cell.setCellValue(propertyValue);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "ConfigProperties.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "ConfigProperties.xls";
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
	public JsonObject uploadConfigPropertiesData(JsonObject configPropertiesDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = configPropertiesDataObject.get("fileId").getAsInt();
		String inputFilesZip = configPropertiesDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Properties Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		configPropertiesDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadConfigPropertiesData(workbook, configPropertiesDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Properties Data uploaded successfully.");
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
	public JsonObject uploadConfigPropertiesData(HSSFWorkbook workbook, JsonObject configPropertiesDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("ConfigProperties");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = configPropertiesDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = configPropertiesDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = configPropertiesDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Properties Data uploaded successfully.");
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
			JsonObject configProperties = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject configPropertiesListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray configPropertiesList = configPropertiesListObj.get("configPropertiesList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadConfigPropertiesList(configPropertiesList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(configPropertiesList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(configPropertiesList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray configPropertiesList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject configProperties = new JsonObject();
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
				JsonObject configPropertiesUploadObj	= new JsonObject();
				configPropertiesUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					configProperties = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("configPropertiesId"))
						{
							String configPropertiesId = row.getCell(cellIndex++).getStringCellValue();
							if(configPropertiesId != null && configPropertiesId.trim().length() > 0)
							{
								try
								{
									Integer iConfigPropertiesId = Integer.parseInt(configPropertiesId);
									if(areSourceDestinationSame == 1)
									{
										ConfigProperties configPropertiesObj = (ConfigProperties)session.get(ConfigProperties.class, iConfigPropertiesId);
										if(configPropertiesObj != null)
										{ 
											configProperties.addProperty("configPropertiesId", iConfigPropertiesId);
										}
										else
										{
											configPropertiesUploadObj.addProperty("isDataFetched", 0);
											configPropertiesUploadObj.addProperty("msg", "This Properties could not be uploaded as there appears to be some problem with the data(ConfigProperties Id is not exist). ");
											continue;
										}
									}
									else
									{
										ConfigProperties configPropertiesObj = getConfigPropertiesByLegacyRecordId(session, iConfigPropertiesId);
										if(configPropertiesObj != null)
										{ 
											configProperties.addProperty("configPropertiesId", configPropertiesObj.getConfigPropertiesId());
										}
										configProperties.addProperty("legacyRecordId", iConfigPropertiesId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									configPropertiesUploadObj.addProperty("isDataFetched", 0);
									configPropertiesUploadObj.addProperty("msg", "This Properties could not be uploaded as there appears to be some problem with the data(ConfigProperties Id). ");
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
							configProperties.addProperty(parameterName, parameterValue);
						}
					}
					configPropertiesUploadObj.add("dataObject", configProperties);		    
					configPropertiesList.add(configPropertiesUploadObj);
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
			dataObject.add("configPropertiesList", configPropertiesList);
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
		if(previousRetryCountMap.has("ConfigProperties") && previousRetryCountMap.get("ConfigProperties").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("ConfigProperties").getAsInt();
		}
		if(retryCountMap.has("ConfigProperties") && retryCountMap.get("ConfigProperties").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("ConfigProperties").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForConfigPropertiesList(JsonArray configPropertiesList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < configPropertiesList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject configPropertiesDataObject = configPropertiesList.get(i).getAsJsonObject();
			JsonObject configProperties = configPropertiesDataObject.get("dataObject").getAsJsonObject();
			if(configPropertiesDataObject.has("retryUpload") && configPropertiesDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = configPropertiesDataObject.get("retryUpload").getAsInt();
			}
			if(configPropertiesDataObject.has("retryChildEntitiesUpload") && configPropertiesDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = configPropertiesDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(configPropertiesDataObject.has("partialUploadUnderProcess") && configPropertiesDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = configPropertiesDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("ConfigProperties", retryCount);
	}
	public JsonObject uploadConfigPropertiesList(JsonArray configPropertiesList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(configPropertiesList);
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
	public JsonObject updateStatusMsg(JsonArray configPropertiesList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < configPropertiesList.size(); i++)
			{
				JsonObject configPropertiesDataObject = configPropertiesList.get(i).getAsJsonObject();
				JsonObject configProperties = configPropertiesDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = configPropertiesDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = configPropertiesDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = configPropertiesDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray configPropertiesList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < configPropertiesList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject configPropertiesDataObject = configPropertiesList.get(i).getAsJsonObject();
				JsonObject configProperties = configPropertiesDataObject.get("dataObject").getAsJsonObject();
				configPropertiesDataObject.addProperty("retryUpload", 0);
				configPropertiesDataObject.addProperty("retryChildEntitiesUpload", 0);
				configPropertiesDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl configPropertiesImplObj = new com.patientapp.controller.forms.impl.ConfigPropertiesControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = configPropertiesImplObj.getEntityInfoUpdatedWithLookupIds(session, configProperties, retValObject);
				if(areAllLookupsFound!=1)
				{
					configPropertiesDataObject.addProperty("retryUpload", 1);
					configPropertiesDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					configPropertiesDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					configPropertiesDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int configPropertiesId = -1;
				int keyColumnsCount = 0;
								keyColumnsCount++;
				keyColumnsCount++;

				if(keyColumnsCount > 0 && !configProperties.has("configPropertiesId"))
				{
					configProperties.addProperty("attributeNamePrefix", "");
					
					configProperties.addProperty("attributeNamePrefix", "");
					JsonObject responseData = configPropertiesImplObj.getConfigPropertiesByLookupFields(session,  configProperties);
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
						JsonObject configPropertiesObject = responseData.get("configPropertiesDataObject").getAsJsonObject();
						configPropertiesId = configPropertiesObject.get("configPropertiesId").getAsInt();
						configProperties.addProperty("configPropertiesId", configPropertiesId);
						updateEntity = true;
					}
				}
				else if(configProperties.has("configPropertiesId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = configPropertiesImplObj.createConfigProperties(configProperties);
				}
				else
				{
					responseData = configPropertiesImplObj.updateConfigProperties(configProperties);
				}
				configPropertiesDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					configPropertiesId =-1;
					configPropertiesDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					configPropertiesDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				configPropertiesId = responseData.get("configPropertiesId").getAsInt();
				configPropertiesDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject configProperties, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getConfigPropertiesByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From ConfigProperties where 1 = 1  ";
			String countHql = "select count(*)  from ConfigProperties where 1 = 1 ";
						
			String propertyName = requestParams.get("propertyName").getAsString();
			hql += " and propertyName = :propertyName ";
			countHql += " and propertyName = :propertyName ";String propertyValue = requestParams.get("propertyValue").getAsString();
			hql += " and propertyValue = :propertyValue ";
			countHql += " and propertyValue = :propertyValue ";
Query countQuery = session.createQuery(countHql);			countQuery.setParameter("propertyName", propertyName);
			countQuery.setParameter("propertyValue", propertyValue);
Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Properties could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);			query.setParameter("propertyName", propertyName);
			query.setParameter("propertyValue", propertyValue);
List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				ConfigProperties configProperties = (ConfigProperties) it.next();
				JsonObject configPropertiesDataObject = configProperties.getDataObject(session);
				dataObject.add("configPropertiesDataObject", configPropertiesDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Properties could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			String propertyName = searchObject.get(attributeNamePrefix + "_" + "propertyName").getAsString();
			requestParams.addProperty("propertyName", propertyName);String propertyValue = searchObject.get(attributeNamePrefix + "_" + "propertyValue").getAsString();
			requestParams.addProperty("propertyValue", propertyValue);
dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Properties' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			if(searchObject.has(attributeNamePrefix + "_" + "propertyName"))
			{
				String propertyName = searchObject.get(attributeNamePrefix + "_" + "propertyName").getAsString();
				if(propertyName!=null && propertyName.length()>0)
				{
					return 1;
				}
			}if(searchObject.has(attributeNamePrefix + "_" + "propertyValue"))
			{
				String propertyValue = searchObject.get(attributeNamePrefix + "_" + "propertyValue").getAsString();
				if(propertyValue!=null && propertyValue.length()>0)
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
		else if(apiName.equals("getConfigPropertiesPropertyValue"))
			{
				JsonObject inputDataObject = getConfigPropertiesPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getConfigProperties"))
			{
				JsonObject inputDataObject = getConfigProperties(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForConfigProperties"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForConfigProperties"))
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
			Integer configPropertiesId = requestReceivedParametersInfo.get("configPropertiesId").getAsInt();
			ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);
			configProperties.setIsRequestUnderProcesss(0);
			session.merge(configProperties);
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
	public JsonObject getConfigPropertiesPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer configPropertiesId = inputForGetAPI.get("configPropertiesId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		ConfigProperties configProperties = (ConfigProperties) session.get(ConfigProperties.class, configPropertiesId);
		configProperties.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getConfigProperties(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer configPropertiesId = inputForGetAPI.get("configPropertiesId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("configPropertiesId", String.valueOf(configPropertiesId));
		JsonObject configPropertiesListObject = retrieveConfigPropertiesList(paramsMap);
		if(configPropertiesListObject!=null && configPropertiesListObject.has("success") && configPropertiesListObject.get("success").getAsInt()==1)
		{
			JsonArray configPropertiesList = configPropertiesListObject.get("configPropertiesList").getAsJsonArray();
			if(configPropertiesList.size()>0)
			{
				dataObject.add("configProperties", configPropertiesList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Properties' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject configPropertiesDataObject, ConfigProperties configProperties);
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
	public abstract void doAfterLookupRowSelectedImpl(ConfigProperties configProperties, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(ConfigProperties configProperties, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(ConfigProperties configProperties, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
