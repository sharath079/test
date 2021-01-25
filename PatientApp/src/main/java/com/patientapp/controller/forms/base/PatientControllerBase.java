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
import com.patientapp.bean.Doctor;
import com.patientapp.controller.forms.impl.DoctorControllerImpl;
import com.patientapp.controller.forms.base.DoctorLabel;
import com.patientapp.bean.Doctor;
import com.patientapp.controller.forms.impl.DoctorControllerImpl;
//

import com.patientapp.bean.Patient;
import com.patientapp.controller.forms.base.PatientLabel;
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
public abstract class PatientControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'PatientName' 
	 *		 * 'PatientGender' 
	 *		 * 'SelectDoctor' 
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
	protected PatientLabel PatientLabelLocal = new PatientLabel();
	protected Patient PatientLocal = new Patient();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public PatientControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public PatientControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public PatientControllerBase()
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
		return "Patient" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((Patient)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((Patient)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((Patient)obj).getPatientId();
	}
	public String getManagedBeanName()
    {
		return "PatientBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "PatientLabelBean";
    }
	protected Class<Patient> setBeanClass()
	{
		return Patient.class;
	}
	protected Class<PatientLabel> setBeanLabelClass()
	{
		return PatientLabel.class;
	}
	protected Patient getLocalManagedBean()
    {
		return (Patient)getManagedBean();
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
		/*Patient patient = (Patient)getManagedBean();
		String areChangesEffected = patient.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			patient.setAreChangesEffected("YES");			
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
		/*Patient patient = (Patient)getManagedBean();
		String areChangesEffected = patient.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			patient.setAreChangesEffected("NO");			
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
	{DoctorControllerImpl doctorControllerImpl = new DoctorControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = doctorControllerImpl.isTransactionUpdatable();
		return isParentTransactionUpdatable;
		
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		Patient patient = (Patient)getManagedBean();
		String sCurrentStatus = patient.getVwTxnStatus();
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
		Patient patient = (Patient)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
				else if("patientGender".equalsIgnoreCase(attributeName))
		{
			  			
		}

		//doAfterSelectOptionChangedImpl(patient, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterPatientLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		Patient patient = (Patient)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("patientName") && initParamsInfo.get("patientName").isJsonNull()==false)
		{
			String patientName = initParamsInfo.get("patientName").getAsString();
			patient.setPatientName(patientName);			
		}if(initParamsInfo.has("patientGender") && initParamsInfo.get("patientGender").isJsonNull()==false)
		{
			String patientGender = initParamsInfo.get("patientGender").getAsString();
			patient.setPatientGender(patientGender);			
		}if(initParamsInfo.has("selectDoctor") && initParamsInfo.get("selectDoctor").isJsonNull()==false)
		{
			String selectDoctor = initParamsInfo.get("selectDoctor").getAsString();
			patient.setSelectDoctor(selectDoctor);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(patient, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		Patient patient = (Patient)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		Patient patient = (Patient)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}
				else if("selectDoctor".equalsIgnoreCase(attributeName))
		{
			  
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(patient, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{DoctorControllerImpl doctorControllerImpl = new DoctorControllerImpl(getDBSession());
		boolean isParentTransactionUpdatable = doctorControllerImpl.isTransactionUpdatable();
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
			Patient patient = (Patient)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "Patient", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() PatientContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (PatientSearch)getManagedBean("PatientSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getPatientName()))
			{
				searchParams.put(PatientLabelLocal.getpatientNameFieldName(),searchBeanLocal.getPatientName());
			}	
			if (isExists(searchBeanLocal.getPatientGender()))
			{
				searchParams.put(PatientLabelLocal.getpatientGenderFieldName(),searchBeanLocal.getPatientGender());
			}	
			if (isExists(searchBeanLocal.getSelectDoctor()))
			{
				searchParams.put(PatientLabelLocal.getselectDoctorFieldName(),searchBeanLocal.getSelectDoctor());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(PatientLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() PatientContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues PatientContollerBase");
		targetBean = (Patient)targetBean;Doctor DoctorLocal = (Doctor)getManagedBean("DoctorBean");
		((Patient)targetBean).setDoctorId(DoctorLocal.getDoctorId());
		((Patient)targetBean).setPatientId(((Patient)sourceBean).getPatientId());
				((Patient)targetBean).setPatientName(((Patient)sourceBean).getPatientName());
		((Patient)targetBean).setPatientGender(((Patient)sourceBean).getPatientGender());
		((Patient)targetBean).setSelectDoctor(((Patient)sourceBean).getSelectDoctor());

				((Patient)targetBean).setDoctorId(((Patient)sourceBean).getDoctorId());

				/*if (bSetAsManagedBean)
		{			
			SelectDoctor SelectDoctorLocal = (SelectDoctor)(( Patient)sourceBean).getSelectDoctor();
			setManagedBean("SelectDoctorBean", SelectDoctorLocal);
		}*/

		doAfterSetValues();
		debugCode("Out setValues PatientContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.Patient entityBean = (Patient)entityObject;
	 	if (sFieldName.equalsIgnoreCase("patientId")) 
	 	{
			return entityBean.getPatientId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("PatientName")) 
	 	{
			return entityBean.getPatientName();
		}
	 	if (sFieldName.equalsIgnoreCase("PatientGender")) 
	 	{
			return entityBean.getPatientGender();
		}
	 	if (sFieldName.equalsIgnoreCase("SelectDoctor")) 
	 	{
			return entityBean.getSelectDoctor();
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
		debugCode("In doEnrichSystemValues(String sAction) PatientControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((Patient) localManagedBean).setVwLastModifiedDate(new Date());
		((Patient) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((Patient) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((Patient) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((Patient) localManagedBean).setVwTxnStatus("CREATED");
			((Patient) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((Patient) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((Patient) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) PatientControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) PatientControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) PatientControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations PatientControllerBase");
		//NG: Important comment
		//managedBean = (Patient) getManagedBean();
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
		debugCode("Out doValidations PatientControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation PatientControllerBase");
		debugCode("Out doAllowedDecimalValidation PatientControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation PatientControllerBase");
				
		String sFieldValue3 = ((Patient) localManagedBean).getPatientGender();if (!isExists(sFieldValue3,localMasters.getGender())) addAllowedValuesResponse(PatientLabelLocal.getpatientGenderFieldName());

		debugCode("Out doAllowedValuesValidation PatientControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation PatientControllerBase");
				
		String sFieldValue2 = ((Patient) localManagedBean).getPatientName();
		if (!isExists(sFieldValue2)) addMandatoryResponse(PatientLabelLocal.getpatientNameFieldName());
debugCode("Out doMandatoryValidation PatientControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation PatientControllerBase");
				
		String sFieldValue2 = ((Patient) localManagedBean).getPatientName();String sFieldValue3 = ((Patient) localManagedBean).getPatientGender();String sFieldValue4 = ((Patient) localManagedBean).getSelectDoctor();

				Integer iFieldValue4 = ((Patient) localManagedBean).getDoctorId();

				if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(PatientLabelLocal.getpatientNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"10")) addMaxLengthResponse(PatientLabelLocal.getpatientGenderFieldName(),"10");
		if (!isLengthAllowed(sFieldValue4,"50")) addMaxLengthResponse(PatientLabelLocal.getselectDoctorFieldName(),"50");

				//if (!isLengthAllowed(iFieldValue4,"50")) addMaxLengthResponse(PatientLabelLocal.getselectDoctorFieldName(),"50");

		debugCode("Out doLengthValidation PatientControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation PatientControllerBase");
		debugCode("Out doDataTypeValidation PatientControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation PatientContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
			Doctor DoctorLocal = (Doctor)getManagedBean("DoctorBean");
			if (DoctorLocal!=null)
			{
				iFieldValueFK = DoctorLocal.getDoctorId();
			}
			
			
			
			
		}	
		debugCode("In doUniqueValidation PatientContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel PatientContollerBase");
		String sLabel = new PatientLabel().getLabel(sResponseField); 
		debugCode("Out getLabel PatientContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int patientId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("patientId", patientId);
			JsonObject responseData = retrievePatient(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Patient' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject patientDataObject = responseData.get("patientDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, patientDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Patient' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrievePatient(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer patientId = -1;
		if(requestParams.has("patientId"))
		{
			patientId = requestParams.get("patientId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("patientId", String.valueOf(patientId));
				String patientName = "";
		if(requestParams.has("patientName"))
		{
			paramsMap.put("patientName", requestParams.get("patientName").getAsString());
		}
		String patientGender = "";
		if(requestParams.has("patientGender"))
		{
			paramsMap.put("patientGender", requestParams.get("patientGender").getAsString());
		}
		String selectDoctor = "";
		if(requestParams.has("selectDoctor"))
		{
			paramsMap.put("selectDoctor", requestParams.get("selectDoctor").getAsString());
		}

				Integer selectDoctorId = -1;
		if(requestParams.has("selectDoctorId"))
		{
			paramsMap.put("selectDoctorId", requestParams.get("selectDoctorId").getAsString());
		}

			
		Integer doctorId = -1;;
		if(requestParams.has("doctorId"))
		{
			paramsMap.put("doctorId", requestParams.get("doctorId").getAsString());
		}JsonObject patientListObject = retrievePatientList(paramsMap);
		if(patientListObject!=null && patientListObject.has("success") && patientListObject.get("success").getAsInt()==1)
		{
			JsonArray patientList = patientListObject.get("patientList").getAsJsonArray();
			if(patientList.size()>0)
			{
				dataObject.add("patientDataObject", patientList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Patient' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getPatient(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String patientName = "";
			if(searchParams.has("patientName"))
			{
				paramsMap.put("patientName", searchParams.get("patientName").getAsString());
			}
			String patientGender = "";
			if(searchParams.has("patientGender"))
			{
				paramsMap.put("patientGender", searchParams.get("patientGender").getAsString());
			}
			String selectDoctor = "";
			if(searchParams.has("selectDoctor"))
			{
				paramsMap.put("selectDoctor", searchParams.get("selectDoctor").getAsString());
			}

						Integer selectDoctorId = -1;
			if(searchParams.has("selectDoctorId"))
			{
				paramsMap.put("selectDoctorId", searchParams.get("selectDoctorId").getAsString());
			}

				
			Integer doctorId = -1;;
			if(searchParams.has("doctorId"))
			{
				paramsMap.put("doctorId", searchParams.get("doctorId").getAsString());
			}
			
			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject patientListObject = retrievePatientList(paramsMap);
			if(patientListObject!=null && patientListObject.has("success") && patientListObject.get("success").getAsInt()==1)
			{
				JsonArray patientList = patientListObject.get("patientList").getAsJsonArray();
				if(patientList.size()>0)
				{
					dataObject.add("patient", patientList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Patient' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getPatientInJson(int patientId)
	{
		JsonObject PatientData = null;
		List<Integer> patientIdsList = new ArrayList<>();
		patientIdsList.add(patientId);
		JsonArray patientList = getPatientListInJson(patientIdsList);
		if(patientList!=null && patientList.size()>0)
		{
			PatientData = patientList.get(0).getAsJsonObject();
		}
		return PatientData;
	}
	public JsonArray getPatientListInJson(List<Integer> patientIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray patientObjectsList = new JsonArray();
		JsonObject patientListObject = retrievePatientList(paramsMap, patientIdsList);
		if(patientListObject!=null && patientListObject.has("success") && patientListObject.get("success").getAsInt()==1)
		{
			JsonArray patientList = patientListObject.get("patientList").getAsJsonArray();
			for (int i =0; i< patientList.size(); i++)
			{
				JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
				int patientId = patientDataObject.get("patientId").getAsInt();
				
				patientObjectsList.add(patientDataObject);
			}
		}
		return patientObjectsList;
	}
	
	public JsonArray getPatientListFromParentInJson(int doctorId)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("doctorId", String.valueOf(doctorId));
		JsonArray patientObjectsList = new JsonArray();
		JsonObject patientListObject = retrievePatientList(paramsMap);
		if(patientListObject!=null && patientListObject.has("success") && patientListObject.get("success").getAsInt()==1)
		{
			JsonArray patientList = patientListObject.get("patientList").getAsJsonArray();
			for (int i =0; i< patientList.size(); i++)
			{
				JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
				int patientId = patientDataObject.get("patientId").getAsInt();
				
			    patientObjectsList.add(patientDataObject);
			}
		}
		return patientObjectsList;
	}	
	public String getUploadedDataErrorMessage(Session session, JsonArray patientList)
	{
		String errorMessage = "patientList: \n";
		for (int i =0; i< patientList.size(); i++)
		{
			JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
			JsonObject patient = patientDataObject.get("dataObject").getAsJsonObject();
			
			if(!patientDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "patient could not be uploaded verify data \n"; 
			}
			else if(patientDataObject.has("isSuccessfullyUploaded") 
					&& patientDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += patientDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("Patient".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND patientId = :patientId ";
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
		else if("Patient".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("patientId", userId);
		}
		
	}
	public String getParentFilterClauseForPatient(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";		String doctorFilterClause = " select doctorId from Doctor where 1=1  ";
		int doctorId = -1;
		if(paramsMap.containsKey("doctorId"))
		{
			doctorId = Integer.parseInt(paramsMap.get("doctorId"));			
		}
		if(doctorId>0)
		{
			doctorFilterClause += " and doctorId = :doctorId  ";
		}
					
		
parentFilterClause = " and doctorId in (" + doctorFilterClause + ")";
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
	public void setParentFilterClauseParamsForPatient(java.util.Map<String, String> paramsMap, Query query)
	{		int doctorId = -1;
		if(paramsMap.containsKey("doctorId"))
		{
			doctorId = Integer.parseInt(paramsMap.get("doctorId"));			
		}
		if(doctorId>0)
		{
			query.setParameter("doctorId", doctorId);
		}			

	}
	public JsonObject retrievePatientList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> patientIdsList = new ArrayList<>();
		if(paramsMap.containsKey("patientId"))
		{
			int patientId = Integer.parseInt(paramsMap.get("patientId"));
			if(patientId>0)
			{
				patientIdsList.add(patientId);
			}
		}
		return retrievePatientList(paramsMap, patientIdsList);
	}
	public JsonObject retrievePatientList(java.util.Map<String, String> paramsMap, List<Integer> patientIdsList)
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
						String patientName = paramsMap.get("patientName");
			if(patientName==null)
			{
				patientName = "";
			}
			String patientGender = paramsMap.get("patientGender");
			if(patientGender==null)
			{
				patientGender = "";
			}
			String selectDoctor = paramsMap.get("selectDoctor");
			if(selectDoctor==null)
			{
				selectDoctor = "";
			}

						Integer selectDoctorId = -2;
	    	if(paramsMap.containsKey("selectDoctorId"))
	    	{
				selectDoctorId = Integer.parseInt(paramsMap.get("selectDoctorId"));
	    	}

			String hql = "FROM Patient where 1 = 1 ";
			String orderByClauseText = "  ";
			String patientIdFilterClass = "";
			if (patientIdsList != null && patientIdsList.size() > 0)
			{
				patientIdFilterClass = " AND patientId in (:idsList) ";
			}
						String patientNameFilterClass = "";
			if (patientName.length() > 0)
			{		
				
				patientNameFilterClass = " AND patientName LIKE :patientName ";	
				
			}
			String patientGenderFilterClass = "";
			if (patientGender.length() > 0)
			{		
				
				patientGenderFilterClass = " AND patientGender LIKE :patientGender ";	
				
			}
			String selectDoctorFilterClass = "";
			if (selectDoctor.length() > 0)
			{		
				
				selectDoctorFilterClass = " AND selectDoctor LIKE :selectDoctor ";	
				
			}
			if (selectDoctorId >= -1)
			{
				selectDoctorFilterClass = " AND selectDoctorId = :selectDoctorId";
			}

			String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForPatient(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					patientIdFilterClass +
										patientNameFilterClass +
					patientGenderFilterClass +
					selectDoctorFilterClass +

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
			if (patientIdsList != null && patientIdsList.size() > 0)
			{
				query.setParameterList("idsList", patientIdsList);
			}
						if (patientName.length() > 0)
			{		
				
				query.setParameter("patientName", patientName);	
				
			}
			if (patientGender.length() > 0)
			{		
				
				query.setParameter("patientGender", patientGender);	
				
			}
			if (selectDoctor.length() > 0)
			{		
				
				query.setParameter("selectDoctor", selectDoctor);	
				
			}

						if (selectDoctorId >= -1)
			{
				query.setParameter("selectDoctorId", selectDoctorId);
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
	    	setParentFilterClauseParamsForPatient(paramsMap, query);
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
			JsonArray patientList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Patient patient = (Patient) it.next();
				JsonObject patientDataObject = patient.getDataObject(getDBSession());
				patientDataObject.addProperty("nextAction", getActionForCurrentStatus(patient.getVwTxnStatus()));
				patientList.add(patientDataObject);
				doAfterSearchResultRowAddedImpl(patientDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from Patient where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (patientIdsList != null && patientIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", patientIdsList);
				}
								if (patientName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("patientName", patientName);
					
					
					
					
				}
				if (patientGender.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("patientGender", patientGender);
					
					
					
					
				}
				if (selectDoctor.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("selectDoctor", selectDoctor);
					
					
					
					
				}

								if (selectDoctorId >= -1)
				{
					countQuery.setParameter("selectDoctorId", selectDoctorId);
				}

				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForPatient(paramsMap, countQuery);
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
			dataObject.add("patientList",   patientList);
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
				+ "   from Patient E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from Patient E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentPatientList(java.util.Map<String, String> paramsMap)
	{
		return retrievePatientList(paramsMap);
	}
	public Patient getPatientByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from Patient where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Patient patient = (Patient) it.next();
			return patient;
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
		Patient patient = (Patient)getManagedBean();
		JsonObject patientDataObject = patient.getDataObject(getDBSession());copyPatientFromJson(patient, patientDataObject);
		setManagedBean(patient);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData PatientContollerBase");
			Patient currentBean = (Patient)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "PatientName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPatientName(sStringTestData);iFieldLength = 0;
		sFieldLength = "10";
		sStringTestData = "PatientGender".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setPatientGender(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "SelectDoctor".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setSelectDoctor(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData PatientContollerBase");
	}
	// end Test Data
	public void copyPatientFromJson(Patient patient, JsonObject patientDataObject)
	{
		copyPatientFromJson(patient, patientDataObject, false);
	}
	public void copyPatientFromJson(Patient patient, JsonObject patientDataObject, boolean excludePasswordFields)
	{	
				
		if(patientDataObject.has("patientName"))
		{
			String patientName = patientDataObject.get("patientName").getAsString();
			patient.setPatientName(patientName);
		}if(patientDataObject.has("patientGender"))
		{
			String patientGender = patientDataObject.get("patientGender").getAsString();
			patient.setPatientGender(patientGender);
		}if(patientDataObject.has("selectDoctor"))
		{
			String selectDoctor = patientDataObject.get("selectDoctor").getAsString();
			patient.setSelectDoctor(selectDoctor);
		}
		
	}
		
	public JsonObject createPatient(JsonObject patientDataObject) throws Exception
	{
		return createPatient(patientDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject patientDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createPatient(JsonObject patientDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		Patient patient = new Patient();
		setLoginBasedValues(paramsInfoObj, patientDataObject);
		Session session = getDBSession();				
		copyPatientFromJson(patient, patientDataObject);
		if(patientDataObject.has("legacyRecordId"))
		{
			patient.setLegacyRecordId(patientDataObject.get("legacyRecordId").getAsInt());
		}
				
			
		Integer doctorId = patientDataObject.get("doctorId").getAsInt();
		com.patientapp.bean.Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
		patient.setDoctorId(doctorId);
		DoctorControllerImpl doctorImplObj = new DoctorControllerImpl(session);
		setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setPrivateManagedBean(doctor);
		if(!doctorImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(doctorImplObj.getTransactionFailureMessage());
		}patient.setVwCreatedBy(createdBy);
		patient.setVwCreationDate(new Date());
		setPrivateManagedBean(patient);
		setManagedBean("PatientBean", patient);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		patient = (Patient) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("patientId", patient.getPatientId());
			JsonObject patientObj = patient.getDataObject(getDBSession());
			patientObj.addProperty("nextAction", getActionForCurrentStatus(patient.getVwTxnStatus()));
			dataObject.add("patientDataObject", patientObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updatePatient(JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer patientId = patientDataObject.get("patientId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("patientId", patientId);
		JsonObject responseData = retrievePatient(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Patient' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Patient patient = (Patient) session.get(Patient.class, patientId);
		if(patient == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(patient.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromPatient(patientDataObject);
		boolean excludePasswordFields = true;
		copyPatientFromJson(patient, patientDataObject, excludePasswordFields);
			
		com.patientapp.bean.Doctor doctor = (Doctor) session.get(Doctor.class, patient.getDoctorId());
		DoctorControllerImpl doctorImplObj = new DoctorControllerImpl(session);
		setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setPrivateManagedBean(doctor);
		if(!doctorImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(doctorImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(patient);
		setManagedBean("PatientBean", patient);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		patient.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("patientId", patientId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromPatient(JsonObject patientDataObject) throws Exception
	{
	}
	public JsonObject deletePatient(JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer patientId = patientDataObject.get("patientId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("patientId", patientId);
		JsonObject responseData = retrievePatient(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Patient' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Patient patient = (Patient) session.get(Patient.class, patientId);
			
		com.patientapp.bean.Doctor doctor = (Doctor) session.get(Doctor.class, patient.getDoctorId());
		DoctorControllerImpl doctorImplObj = new DoctorControllerImpl(session);
		setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setManagedBean(doctorImplObj.getManagedBeanName(), doctor);
		doctorImplObj.setPrivateManagedBean(doctor);
		if(!doctorImplObj.isActionAllowedOnCurrentStatus(ACTION_UPDATE))
		{
			setTransactionFailureMessage(doctorImplObj.getTransactionFailureMessage());
		}setPrivateManagedBean(patient);
		setManagedBean("Patient", patient);
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
	public JsonObject fetchPatientDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		Patient patient = new Patient();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(patient);
			setManagedBean("Patient", patient);
			doAfterPatientLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("patient", patient.getDataObject(getDBSession()));
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
	public JsonObject fetchPatientTestData(int obj, JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		Patient patient = new Patient();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPatientFromJson(patient, patientDataObject);
			setPrivateManagedBean(patient);
			setManagedBean("Patient", patient);
			doAfterPatientLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("patient", patient.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForPatient(JsonObject patientDataObject) throws Exception
	{
		Patient patient = new Patient();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPatientFromJson(patient, patientDataObject);	
			
			Integer doctorId = patientDataObject.get("doctorId").getAsInt();
			patient.setDoctorId(doctorId);
			
			String attributeName = patientDataObject.get("attributeName").getAsString();
			Integer attributeId = patientDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(patient);
			setManagedBean("Patient", patient);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("patient", patient.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForPatient(JsonObject patientDataObject) throws Exception
	{
		Patient patient = new Patient();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyPatientFromJson(patient, patientDataObject);	
			String attributeName = patientDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(patient);
			setManagedBean("Patient", patient);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("patient", patient.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer patientId = patientDataObject.get("patientId").getAsInt();
			String customEventName = patientDataObject.get("customEventName").getAsString();
			Patient patient = (Patient) session.get(Patient.class, patientId);
			setPrivateManagedBean(patient);
			setManagedBean("PatientBean", patient);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("patient", patient.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnPatient(JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer patientId = patientDataObject.get("patientId").getAsInt();
			String currentStatus = patientDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(patientDataObject.has("currentAction"))
			{
				currentAction = patientDataObject.get("currentAction").getAsString();
			}
			Patient patient = (Patient) session.get(Patient.class, patientId);
			setPrivateManagedBean(patient);
			setManagedBean("PatientBean", patient);
			if(patient.getIsRequestUnderProcesss() == 1)
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
				executeAction(patient, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(patient, "ClassInfoBean", currentStatus);
			}
//			executeAction(patient, "PatientBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedpatientDataObject = patient.getDataObject(getDBSession());
				updatedpatientDataObject.addProperty("nextAction", getActionForCurrentStatus(patient.getVwTxnStatus()));
				dataObject.add("patient", updatedpatientDataObject);
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
		Patient patient = (Patient) getManagedBean();
		String currentStatus = patient.getVwTxnStatus();
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
	
	
	public JsonObject downloadPatientData() throws Exception
	{
		return downloadPatientData(null);
	}
	public JsonObject downloadPatientData(HSSFWorkbook workbook) throws Exception
	
	{
		return downloadPatientData(null, null, null, new JsonObject(), -1);
	}
	public JsonObject downloadPatientData(HSSFSheet sheet, CellStyle headerStyle, CellStyle dataStyle, JsonObject rowColumnIndexObject,Integer doctorId) throws Exception
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
			headerName = "Patient";
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
			headerName = "patientId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "patientName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "patientGender";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			
			cell = row.createCell(headerCellCount++);
			headerName = "selectDoctor";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			String hql = "From Patient ";
			
			hql += " WHERE doctorId  = :doctorId ";
						
			Query query = getDBSession().createQuery(hql);
			
			query.setParameter("doctorId", doctorId);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				Patient patient = (Patient) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer patientId = patient.getPatientId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(patientId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String patientName = patient.getPatientName();
				cell.setCellValue(patientName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String patientGender = patient.getPatientGender();
				cell.setCellValue(patientGender);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String selectDoctor = patient.getSelectDoctor();
				cell.setCellValue(selectDoctor);

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
	public JsonObject uploadPatientData(JsonObject patientDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = patientDataObject.get("fileId").getAsInt();
		String inputFilesZip = patientDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Patient Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		patientDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadPatientData(workbook, patientDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Patient Data uploaded successfully.");
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
	public JsonObject uploadPatientData(HSSFWorkbook workbook, JsonObject patientDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("Patient");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = patientDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = patientDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = patientDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Patient Data uploaded successfully.");
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
			JsonObject patient = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject patientListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray patientList = patientListObj.get("patientList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadPatientList(patientList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(patientList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(patientList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray patientList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject patient = new JsonObject();
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
				JsonObject patientUploadObj	= new JsonObject();
				patientUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					patient = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("patientId"))
						{
							String patientId = row.getCell(cellIndex++).getStringCellValue();
							if(patientId != null && patientId.trim().length() > 0)
							{
								try
								{
									Integer iPatientId = Integer.parseInt(patientId);
									if(areSourceDestinationSame == 1)
									{
										Patient patientObj = (Patient)session.get(Patient.class, iPatientId);
										if(patientObj != null)
										{ 
											patient.addProperty("patientId", iPatientId);
										}
										else
										{
											patientUploadObj.addProperty("isDataFetched", 0);
											patientUploadObj.addProperty("msg", "This Patient could not be uploaded as there appears to be some problem with the data(Patient Id is not exist). ");
											continue;
										}
									}
									else
									{
										Patient patientObj = getPatientByLegacyRecordId(session, iPatientId);
										if(patientObj != null)
										{ 
											patient.addProperty("patientId", patientObj.getPatientId());
										}
										patient.addProperty("legacyRecordId", iPatientId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									patientUploadObj.addProperty("isDataFetched", 0);
									patientUploadObj.addProperty("msg", "This Patient could not be uploaded as there appears to be some problem with the data(Patient Id). ");
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
							patient.addProperty(parameterName, parameterValue);
						}
					}
					patientUploadObj.add("dataObject", patient);		    
					patientList.add(patientUploadObj);
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
			dataObject.add("patientList", patientList);
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
		if(previousRetryCountMap.has("Patient") && previousRetryCountMap.get("Patient").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("Patient").getAsInt();
		}
		if(retryCountMap.has("Patient") && retryCountMap.get("Patient").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("Patient").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForPatientList(JsonArray patientList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < patientList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
			JsonObject patient = patientDataObject.get("dataObject").getAsJsonObject();
			if(patientDataObject.has("retryUpload") && patientDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = patientDataObject.get("retryUpload").getAsInt();
			}
			if(patientDataObject.has("retryChildEntitiesUpload") && patientDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = patientDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(patientDataObject.has("partialUploadUnderProcess") && patientDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = patientDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("Patient", retryCount);
	}
	public JsonObject uploadPatientList(JsonArray patientList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(patientList);
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
	public JsonObject updateStatusMsg(JsonArray patientList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < patientList.size(); i++)
			{
				JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
				JsonObject patient = patientDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = patientDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = patientDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = patientDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray patientList) throws Exception
	
	{
		return uploadData(patientList, -1);	
	}
	public JsonObject uploadData(JsonArray patientList, Integer doctorId) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < patientList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject patientDataObject = patientList.get(i).getAsJsonObject();
				JsonObject patient = patientDataObject.get("dataObject").getAsJsonObject();
				patientDataObject.addProperty("retryUpload", 0);
				patientDataObject.addProperty("retryChildEntitiesUpload", 0);
				patientDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = patientImplObj.getEntityInfoUpdatedWithLookupIds(session, patient, retValObject);
				if(areAllLookupsFound!=1)
				{
					patientDataObject.addProperty("retryUpload", 1);
					patientDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					patientDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					patientDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int patientId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !patient.has("patientId"))
				{
					patient.addProperty("attributeNamePrefix", "");
					
					patient.addProperty("doctorId", doctorId);
					
					patient.addProperty("attributeNamePrefix", "");
					JsonObject responseData = patientImplObj.getPatientByLookupFields(session,  patient);
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
						JsonObject patientObject = responseData.get("patientDataObject").getAsJsonObject();
						patientId = patientObject.get("patientId").getAsInt();
						patient.addProperty("patientId", patientId);
						updateEntity = true;
					}
				}
				else if(patient.has("patientId"))
				{
					updateEntity = true;
				}
				
				if(doctorId > 0)
				{
					patient.addProperty("doctorId", doctorId);
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = patientImplObj.createPatient(patient);
				}
				else
				{
					responseData = patientImplObj.updatePatient(patient);
				}
				patientDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					patientId =-1;
					patientDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					patientDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				patientId = responseData.get("patientId").getAsInt();
				patientDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject patient, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getPatientByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			
			Integer doctorId = -1;
			if(requestParams.has("doctorId") && requestParams.get("doctorId").isJsonNull()==false)
			{
				doctorId = requestParams.get("doctorId").getAsInt();
			}
			
			String hql = "From Patient where 1 = 1   and doctorId = :doctorId ";
			String countHql = "select count(*)  from Patient where 1 = 1  and doctorId = :doctorId ";
			
			
			Query countQuery = session.createQuery(countHql);
			
			countQuery.setParameter("doctorId", doctorId);
			
			
			
			Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Patient could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);
			
			query.setParameter("doctorId", doctorId);
			
			
			
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Patient patient = (Patient) it.next();
				JsonObject patientDataObject = patient.getDataObject(session);
				dataObject.add("patientDataObject", patientDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Patient could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			
			
			if(searchObject.has("doctorId") && searchObject.get("doctorId").isJsonNull()==false)
			{
				requestParams.addProperty("doctorId", searchObject.get("doctorId").getAsInt());
			}
			
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Patient' could not be retrieved");
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
	
	public JsonObject deletePatientListIfKeyColumnsNotFound(Session session, Integer doctorId)
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
			String hql = "From Patient WHERE doctorId = :doctorId ";
			Query query = session.createQuery(hql);
			query.setParameter("doctorId", doctorId);
			List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Patient patient = (Patient) it.next();
				int patientId = patient.getPatientId();
				JsonObject responseData = new JsonObject();
			    
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
			    patientImplObj.setPrivateManagedBean(patient);
			    patientImplObj.setManagedBean("Patient", patient);
			    patientImplObj.delete();
				if (patientImplObj.getHasTransactionFailed() == true)
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
		dataObject.addProperty("alert", "Your request could not be processed as Patient could not be deleted");
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
			else if(apiName.equals("getPatientPropertyValue"))
			{
				JsonObject inputDataObject = getPatientPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getPatient"))
			{
				JsonObject inputDataObject = getPatient(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForPatient"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForPatient"))
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
			Integer patientId = requestReceivedParametersInfo.get("patientId").getAsInt();
			Patient patient = (Patient) session.get(Patient.class, patientId);
			patient.setIsRequestUnderProcesss(0);
			session.merge(patient);
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
	public JsonObject getPatientPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer patientId = inputForGetAPI.get("patientId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		Patient patient = (Patient) session.get(Patient.class, patientId);
		patient.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getPatient(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer patientId = inputForGetAPI.get("patientId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("patientId", String.valueOf(patientId));
		JsonObject patientListObject = retrievePatientList(paramsMap);
		if(patientListObject!=null && patientListObject.has("success") && patientListObject.get("success").getAsInt()==1)
		{
			JsonArray patientList = patientListObject.get("patientList").getAsJsonArray();
			if(patientList.size()>0)
			{
				dataObject.add("patient", patientList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Patient' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject patientDataObject, Patient patient);
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
	public abstract void doAfterLookupRowSelectedImpl(Patient patient, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(Patient patient, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(Patient patient, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
