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
import com.patientapp.bean.Patient;
import com.patientapp.controller.forms.impl.PatientControllerImpl;

import com.patientapp.bean.Doctor;
import com.patientapp.controller.forms.base.DoctorLabel;
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
public abstract class DoctorControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'DoctorName' 
	 *		 * 'HospitalName' 
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
	protected DoctorLabel DoctorLabelLocal = new DoctorLabel();
	protected Doctor DoctorLocal = new Doctor();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public DoctorControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public DoctorControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public DoctorControllerBase()
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
		return "Doctor" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((Doctor)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((Doctor)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((Doctor)obj).getDoctorId();
	}
	public String getManagedBeanName()
    {
		return "DoctorBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "DoctorLabelBean";
    }
	protected Class<Doctor> setBeanClass()
	{
		return Doctor.class;
	}
	protected Class<DoctorLabel> setBeanLabelClass()
	{
		return DoctorLabel.class;
	}
	protected Doctor getLocalManagedBean()
    {
		return (Doctor)getManagedBean();
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
		/*Doctor doctor = (Doctor)getManagedBean();
		String areChangesEffected = doctor.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			doctor.setAreChangesEffected("YES");			
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
		/*Doctor doctor = (Doctor)getManagedBean();
		String areChangesEffected = doctor.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			doctor.setAreChangesEffected("NO");			
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
		/*Doctor doctor = (Doctor)getManagedBean();
		String areChangesEffected = doctor.getAreChangesEffected();
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
		Doctor doctor = (Doctor)getManagedBean();
		String sCurrentStatus = doctor.getVwTxnStatus();
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
		Doctor doctor = (Doctor)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(doctor, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterDoctorLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		Doctor doctor = (Doctor)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("doctorName") && initParamsInfo.get("doctorName").isJsonNull()==false)
		{
			String doctorName = initParamsInfo.get("doctorName").getAsString();
			doctor.setDoctorName(doctorName);			
		}if(initParamsInfo.has("hospitalName") && initParamsInfo.get("hospitalName").isJsonNull()==false)
		{
			String hospitalName = initParamsInfo.get("hospitalName").getAsString();
			doctor.setHospitalName(hospitalName);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(doctor, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		Doctor doctor = (Doctor)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		Doctor doctor = (Doctor)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(doctor, attributeName);
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
			Doctor doctor = (Doctor)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*			Set<Object> patientList = doctor.getPatientes();
			Iterator<Object> patientListIterator = patientList.iterator();
			while(patientListIterator.hasNext())
			{
				Patient patient = (Patient)patientListIterator.next();
				PatientControllerImpl patientControllerImpl = new PatientControllerImpl(getDBSession());
				patientControllerImpl.setPrivateManagedBean(patient);
				patientControllerImpl.deleteWithoutCommit(false);
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "Doctor", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() DoctorContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (DoctorSearch)getManagedBean("DoctorSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getDoctorName()))
			{
				searchParams.put(DoctorLabelLocal.getdoctorNameFieldName(),searchBeanLocal.getDoctorName());
			}	
			if (isExists(searchBeanLocal.getHospitalName()))
			{
				searchParams.put(DoctorLabelLocal.gethospitalNameFieldName(),searchBeanLocal.getHospitalName());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(DoctorLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() DoctorContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues DoctorContollerBase");
		targetBean = (Doctor)targetBean;((Doctor)targetBean).setDoctorId(((Doctor)sourceBean).getDoctorId());
				((Doctor)targetBean).setDoctorName(((Doctor)sourceBean).getDoctorName());
		((Doctor)targetBean).setHospitalName(((Doctor)sourceBean).getHospitalName());

		doAfterSetValues();
		debugCode("Out setValues DoctorContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.patientapp.bean.Doctor entityBean = (Doctor)entityObject;
	 	if (sFieldName.equalsIgnoreCase("doctorId")) 
	 	{
			return entityBean.getDoctorId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("DoctorName")) 
	 	{
			return entityBean.getDoctorName();
		}
	 	if (sFieldName.equalsIgnoreCase("HospitalName")) 
	 	{
			return entityBean.getHospitalName();
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
		debugCode("In doEnrichSystemValues(String sAction) DoctorControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((Doctor) localManagedBean).setVwLastModifiedDate(new Date());
		((Doctor) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((Doctor) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((Doctor) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((Doctor) localManagedBean).setVwTxnStatus("CREATED");
			((Doctor) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((Doctor) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((Doctor) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) DoctorControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) DoctorControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) DoctorControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations DoctorControllerBase");
		//NG: Important comment
		//managedBean = (Doctor) getManagedBean();
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
		debugCode("Out doValidations DoctorControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation DoctorControllerBase");
		debugCode("Out doAllowedDecimalValidation DoctorControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation DoctorControllerBase");debugCode("Out doAllowedValuesValidation DoctorControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation DoctorControllerBase");
				
		String sFieldValue2 = ((Doctor) localManagedBean).getDoctorName();
		if (!isExists(sFieldValue2)) addMandatoryResponse(DoctorLabelLocal.getdoctorNameFieldName());
debugCode("Out doMandatoryValidation DoctorControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation DoctorControllerBase");
				
		String sFieldValue2 = ((Doctor) localManagedBean).getDoctorName();String sFieldValue3 = ((Doctor) localManagedBean).getHospitalName();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(DoctorLabelLocal.getdoctorNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(DoctorLabelLocal.gethospitalNameFieldName(),"50");
debugCode("Out doLengthValidation DoctorControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation DoctorControllerBase");
		debugCode("Out doDataTypeValidation DoctorControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation DoctorContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation DoctorContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel DoctorContollerBase");
		String sLabel = new DoctorLabel().getLabel(sResponseField); 
		debugCode("Out getLabel DoctorContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int doctorId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("doctorId", doctorId);
			JsonObject responseData = retrieveDoctor(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Doctor' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject doctorDataObject = responseData.get("doctorDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, doctorDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Doctor' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveDoctor(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer doctorId = -1;
		if(requestParams.has("doctorId"))
		{
			doctorId = requestParams.get("doctorId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("doctorId", String.valueOf(doctorId));
				String doctorName = "";
		if(requestParams.has("doctorName"))
		{
			paramsMap.put("doctorName", requestParams.get("doctorName").getAsString());
		}
		String hospitalName = "";
		if(requestParams.has("hospitalName"))
		{
			paramsMap.put("hospitalName", requestParams.get("hospitalName").getAsString());
		}

		JsonObject doctorListObject = retrieveDoctorList(paramsMap);
		if(doctorListObject!=null && doctorListObject.has("success") && doctorListObject.get("success").getAsInt()==1)
		{
			JsonArray doctorList = doctorListObject.get("doctorList").getAsJsonArray();
			if(doctorList.size()>0)
			{
				dataObject.add("doctorDataObject", doctorList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Doctor' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getDoctor(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String doctorName = "";
			if(searchParams.has("doctorName"))
			{
				paramsMap.put("doctorName", searchParams.get("doctorName").getAsString());
			}
			String hospitalName = "";
			if(searchParams.has("hospitalName"))
			{
				paramsMap.put("hospitalName", searchParams.get("hospitalName").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject doctorListObject = retrieveDoctorList(paramsMap);
			if(doctorListObject!=null && doctorListObject.has("success") && doctorListObject.get("success").getAsInt()==1)
			{
				JsonArray doctorList = doctorListObject.get("doctorList").getAsJsonArray();
				if(doctorList.size()>0)
				{
					dataObject.add("doctor", doctorList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Doctor' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getDoctorInJson(int doctorId)
	{
		JsonObject DoctorData = null;
		List<Integer> doctorIdsList = new ArrayList<>();
		doctorIdsList.add(doctorId);
		JsonArray doctorList = getDoctorListInJson(doctorIdsList);
		if(doctorList!=null && doctorList.size()>0)
		{
			DoctorData = doctorList.get(0).getAsJsonObject();
		}
		return DoctorData;
	}
	public JsonArray getDoctorListInJson(List<Integer> doctorIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray doctorObjectsList = new JsonArray();
		JsonObject doctorListObject = retrieveDoctorList(paramsMap, doctorIdsList);
		if(doctorListObject!=null && doctorListObject.has("success") && doctorListObject.get("success").getAsInt()==1)
		{
			JsonArray doctorList = doctorListObject.get("doctorList").getAsJsonArray();
			for (int i =0; i< doctorList.size(); i++)
			{
				JsonObject doctorDataObject = doctorList.get(i).getAsJsonObject();
				int doctorId = doctorDataObject.get("doctorId").getAsInt();
								com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(getDBSession(), getUserSessionInfo());
				JsonArray patientList = patientImplObj.getPatientListFromParentInJson(doctorId);
				doctorDataObject.add("patientList", patientList);

				doctorObjectsList.add(doctorDataObject);
			}
		}
		return doctorObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray doctorList)
	{
		String errorMessage = "doctorList: \n";
		for (int i =0; i< doctorList.size(); i++)
		{
			JsonObject doctorDataObject = doctorList.get(i).getAsJsonObject();
			JsonObject doctor = doctorDataObject.get("dataObject").getAsJsonObject();if(!doctorDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "doctor could not be uploaded verify data \n"; 
			}
			else if(doctorDataObject.has("isSuccessfullyUploaded") 
					&& doctorDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += doctorDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    		    if(doctor.has("patientList"))
		    {
			    JsonArray patientList = doctor.get("patientList").getAsJsonArray();
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);		    
				errorMessage += patientImplObj.getUploadedDataErrorMessage(session, patientList);
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
		else if("Doctor".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND doctorId = :doctorId ";
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
		else if("Doctor".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("doctorId", userId);
		}
		
	}
	public String getParentFilterClauseForDoctor(java.util.Map<String, String> paramsMap)
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
		lookupDisplayQueryColumn += "doctorName";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForDoctor(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveDoctorList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> doctorIdsList = new ArrayList<>();
		if(paramsMap.containsKey("doctorId"))
		{
			int doctorId = Integer.parseInt(paramsMap.get("doctorId"));
			if(doctorId>0)
			{
				doctorIdsList.add(doctorId);
			}
		}
		return retrieveDoctorList(paramsMap, doctorIdsList);
	}
	public JsonObject retrieveDoctorList(java.util.Map<String, String> paramsMap, List<Integer> doctorIdsList)
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
						String doctorName = paramsMap.get("doctorName");
			if(doctorName==null)
			{
				doctorName = "";
			}
			String hospitalName = paramsMap.get("hospitalName");
			if(hospitalName==null)
			{
				hospitalName = "";
			}
String hql = "FROM Doctor where 1 = 1 ";
			String orderByClauseText = "  ";
			String doctorIdFilterClass = "";
			if (doctorIdsList != null && doctorIdsList.size() > 0)
			{
				doctorIdFilterClass = " AND doctorId in (:idsList) ";
			}
						String doctorNameFilterClass = "";
			if (doctorName.length() > 0)
			{		
				
				doctorNameFilterClass = " AND doctorName LIKE :doctorName ";	
				
			}
			String hospitalNameFilterClass = "";
			if (hospitalName.length() > 0)
			{		
				
				hospitalNameFilterClass = " AND hospitalName LIKE :hospitalName ";	
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForDoctor(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					doctorIdFilterClass +
										doctorNameFilterClass +
					hospitalNameFilterClass +

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
			if (doctorIdsList != null && doctorIdsList.size() > 0)
			{
				query.setParameterList("idsList", doctorIdsList);
			}
						if (doctorName.length() > 0)
			{		
				
				query.setParameter("doctorName", doctorName);	
				
			}
			if (hospitalName.length() > 0)
			{		
				
				query.setParameter("hospitalName", hospitalName);	
				
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
	    	setParentFilterClauseParamsForDoctor(paramsMap, query);
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
			JsonArray doctorList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Doctor doctor = (Doctor) it.next();
				JsonObject doctorDataObject = doctor.getDataObject(getDBSession());
				doctorDataObject.addProperty("nextAction", getActionForCurrentStatus(doctor.getVwTxnStatus()));
				doctorList.add(doctorDataObject);
				doAfterSearchResultRowAddedImpl(doctorDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from Doctor where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (doctorIdsList != null && doctorIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", doctorIdsList);
				}
								if (doctorName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("doctorName", doctorName);
					
					
					
					
				}
				if (hospitalName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("hospitalName", hospitalName);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForDoctor(paramsMap, countQuery);
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
			dataObject.add("doctorList",   doctorList);
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
				+ "   from Doctor E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from Doctor E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentDoctorList(java.util.Map<String, String> paramsMap)
	{
		return retrieveDoctorList(paramsMap);
	}
	public Doctor getDoctorByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from Doctor where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Doctor doctor = (Doctor) it.next();
			return doctor;
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
		Doctor doctor = (Doctor)getManagedBean();
		JsonObject doctorDataObject = doctor.getDataObject(getDBSession());copyDoctorFromJson(doctor, doctorDataObject);
		setManagedBean(doctor);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData DoctorContollerBase");
			Doctor currentBean = (Doctor)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "DoctorName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setDoctorName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "HospitalName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setHospitalName(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData DoctorContollerBase");
	}
	// end Test Data
	public void copyDoctorFromJson(Doctor doctor, JsonObject doctorDataObject)
	{
		copyDoctorFromJson(doctor, doctorDataObject, false);
	}
	public void copyDoctorFromJson(Doctor doctor, JsonObject doctorDataObject, boolean excludePasswordFields)
	{	
				
		if(doctorDataObject.has("doctorName"))
		{
			String doctorName = doctorDataObject.get("doctorName").getAsString();
			doctor.setDoctorName(doctorName);
		}if(doctorDataObject.has("hospitalName"))
		{
			String hospitalName = doctorDataObject.get("hospitalName").getAsString();
			doctor.setHospitalName(hospitalName);
		}
		
	}
		
	public JsonObject createDoctor(JsonObject doctorDataObject) throws Exception
	{
		return createDoctor(doctorDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject doctorDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createDoctor(JsonObject doctorDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		Doctor doctor = new Doctor();
		setLoginBasedValues(paramsInfoObj, doctorDataObject);
		Session session = getDBSession();				
		copyDoctorFromJson(doctor, doctorDataObject);
		if(doctorDataObject.has("legacyRecordId"))
		{
			doctor.setLegacyRecordId(doctorDataObject.get("legacyRecordId").getAsInt());
		}
				doctor.setVwCreatedBy(createdBy);
		doctor.setVwCreationDate(new Date());
		setPrivateManagedBean(doctor);
		setManagedBean("DoctorBean", doctor);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		doctor = (Doctor) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("doctorId", doctor.getDoctorId());
			JsonObject doctorObj = doctor.getDataObject(getDBSession());
			doctorObj.addProperty("nextAction", getActionForCurrentStatus(doctor.getVwTxnStatus()));
			dataObject.add("doctorDataObject", doctorObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateDoctor(JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer doctorId = doctorDataObject.get("doctorId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("doctorId", doctorId);
		JsonObject responseData = retrieveDoctor(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Doctor' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
		if(doctor == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(doctor.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromDoctor(doctorDataObject);
		boolean excludePasswordFields = true;
		copyDoctorFromJson(doctor, doctorDataObject, excludePasswordFields);setPrivateManagedBean(doctor);
		setManagedBean("DoctorBean", doctor);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		doctor.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("doctorId", doctorId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromDoctor(JsonObject doctorDataObject) throws Exception
	{
	}
	public JsonObject deleteDoctor(JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer doctorId = doctorDataObject.get("doctorId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("doctorId", doctorId);
		JsonObject responseData = retrieveDoctor(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Doctor' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);setPrivateManagedBean(doctor);
		setManagedBean("Doctor", doctor);
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
	public JsonObject fetchDoctorDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		Doctor doctor = new Doctor();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(doctor);
			setManagedBean("Doctor", doctor);
			doAfterDoctorLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("doctor", doctor.getDataObject(getDBSession()));
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
	public JsonObject fetchDoctorTestData(int obj, JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		Doctor doctor = new Doctor();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyDoctorFromJson(doctor, doctorDataObject);
			setPrivateManagedBean(doctor);
			setManagedBean("Doctor", doctor);
			doAfterDoctorLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("doctor", doctor.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForDoctor(JsonObject doctorDataObject) throws Exception
	{
		Doctor doctor = new Doctor();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyDoctorFromJson(doctor, doctorDataObject);	String attributeName = doctorDataObject.get("attributeName").getAsString();
			Integer attributeId = doctorDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(doctor);
			setManagedBean("Doctor", doctor);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("doctor", doctor.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForDoctor(JsonObject doctorDataObject) throws Exception
	{
		Doctor doctor = new Doctor();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyDoctorFromJson(doctor, doctorDataObject);	
			String attributeName = doctorDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(doctor);
			setManagedBean("Doctor", doctor);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("doctor", doctor.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer doctorId = doctorDataObject.get("doctorId").getAsInt();
			String customEventName = doctorDataObject.get("customEventName").getAsString();
			Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
			setPrivateManagedBean(doctor);
			setManagedBean("DoctorBean", doctor);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("doctor", doctor.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnDoctor(JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer doctorId = doctorDataObject.get("doctorId").getAsInt();
			String currentStatus = doctorDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(doctorDataObject.has("currentAction"))
			{
				currentAction = doctorDataObject.get("currentAction").getAsString();
			}
			Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
			setPrivateManagedBean(doctor);
			setManagedBean("DoctorBean", doctor);
			if(doctor.getIsRequestUnderProcesss() == 1)
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
				executeAction(doctor, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(doctor, "ClassInfoBean", currentStatus);
			}
//			executeAction(doctor, "DoctorBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updateddoctorDataObject = doctor.getDataObject(getDBSession());
				updateddoctorDataObject.addProperty("nextAction", getActionForCurrentStatus(doctor.getVwTxnStatus()));
				dataObject.add("doctor", updateddoctorDataObject);
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
		Doctor doctor = (Doctor) getManagedBean();
		String currentStatus = doctor.getVwTxnStatus();
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
	
	
	public JsonObject downloadDoctorData() throws Exception
	{
		return downloadDoctorData(null);
	}
	public JsonObject downloadDoctorData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Doctor");
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
			headerName = "doctorId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "doctorName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "hospitalName";
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
			String hql = "From Doctor ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				Doctor doctor = (Doctor) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer doctorId = doctor.getDoctorId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(doctorId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String doctorName = doctor.getDoctorName();
				cell.setCellValue(doctorName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String hospitalName = doctor.getHospitalName();
				cell.setCellValue(hospitalName);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    				rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
				patientImplObj.downloadPatientData(sheet, headerStyle, dataStyle, rowColumnIndexObject, doctorId);

			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "Doctor.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "Doctor.xls";
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
	public JsonObject uploadDoctorData(JsonObject doctorDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = doctorDataObject.get("fileId").getAsInt();
		String inputFilesZip = doctorDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Doctor Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		doctorDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadDoctorData(workbook, doctorDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Doctor Data uploaded successfully.");
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
	public JsonObject uploadDoctorData(HSSFWorkbook workbook, JsonObject doctorDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("Doctor");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.patientapp.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = doctorDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = doctorDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = doctorDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Doctor Data uploaded successfully.");
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
			JsonObject doctor = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject doctorListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray doctorList = doctorListObj.get("doctorList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadDoctorList(doctorList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(doctorList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(doctorList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray doctorList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject doctor = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    				if(rowFirstCellValue != null && rowFirstCellValue.equalsIgnoreCase("LineItem") 
				&& dependentEntityName != null && dependentEntityName.equalsIgnoreCase("patient"))
				{
					headerRow = sheet.getRow(currentRowPosition);
					childEntityCellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex+2);
					currentRowPosition++;
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex+2);
					com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session);
					JsonObject responseData  = patientImplObj.fetchData(workbook, sheet, totalDefinedRowsInSheet, 0, rowColumnIndexObject, childEntityCellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					JsonArray patientList = responseData.get("patientList").getAsJsonArray();
					doctor.add("patientList", patientList);
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
				JsonObject doctorUploadObj	= new JsonObject();
				doctorUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					doctor = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("doctorId"))
						{
							String doctorId = row.getCell(cellIndex++).getStringCellValue();
							if(doctorId != null && doctorId.trim().length() > 0)
							{
								try
								{
									Integer iDoctorId = Integer.parseInt(doctorId);
									if(areSourceDestinationSame == 1)
									{
										Doctor doctorObj = (Doctor)session.get(Doctor.class, iDoctorId);
										if(doctorObj != null)
										{ 
											doctor.addProperty("doctorId", iDoctorId);
										}
										else
										{
											doctorUploadObj.addProperty("isDataFetched", 0);
											doctorUploadObj.addProperty("msg", "This Doctor could not be uploaded as there appears to be some problem with the data(Doctor Id is not exist). ");
											continue;
										}
									}
									else
									{
										Doctor doctorObj = getDoctorByLegacyRecordId(session, iDoctorId);
										if(doctorObj != null)
										{ 
											doctor.addProperty("doctorId", doctorObj.getDoctorId());
										}
										doctor.addProperty("legacyRecordId", iDoctorId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									doctorUploadObj.addProperty("isDataFetched", 0);
									doctorUploadObj.addProperty("msg", "This Doctor could not be uploaded as there appears to be some problem with the data(Doctor Id). ");
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
							doctor.addProperty(parameterName, parameterValue);
						}
					}
					doctorUploadObj.add("dataObject", doctor);		    
					doctorList.add(doctorUploadObj);
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
			dataObject.add("doctorList", doctorList);
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
		if(previousRetryCountMap.has("Doctor") && previousRetryCountMap.get("Doctor").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("Doctor").getAsInt();
		}
		if(retryCountMap.has("Doctor") && retryCountMap.get("Doctor").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("Doctor").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    		com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(getDBSession());
		areSomeRecordsUploaded = 0;
		if(areSomeRecordsUploaded==1)
		{
			return 1;
		}

		return 0;
	}
	public void updateRetryCountMapForDoctorList(JsonArray doctorList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < doctorList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject doctorDataObject = doctorList.get(i).getAsJsonObject();
			JsonObject doctor = doctorDataObject.get("dataObject").getAsJsonObject();
			if(doctorDataObject.has("retryUpload") && doctorDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = doctorDataObject.get("retryUpload").getAsInt();
			}
			if(doctorDataObject.has("retryChildEntitiesUpload") && doctorDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = doctorDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(doctorDataObject.has("partialUploadUnderProcess") && doctorDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = doctorDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    		    if(doctor.has("patientList"))
		    {
				com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(getDBSession());
				JsonArray patientList = doctor.get("patientList").getAsJsonArray();
				patientImplObj.updateRetryCountMapForPatientList(patientList, retryCountMap);
		    }

		}
	    retryCountMap.addProperty("Doctor", retryCount);
	}
	public JsonObject uploadDoctorList(JsonArray doctorList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(doctorList);
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
	public JsonObject updateStatusMsg(JsonArray doctorList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < doctorList.size(); i++)
			{
				JsonObject doctorDataObject = doctorList.get(i).getAsJsonObject();
				JsonObject doctor = doctorDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = doctorDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = doctorDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = doctorDataObject.get("errorMessage").getAsString();
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
			    			    if(isSuccessfullyUploaded == 1 && doctor.has("patientList"))
			    {
					com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(getDBSession());
					JsonArray patientList = doctor.get("patientList").getAsJsonArray(); 
					responseData  = patientImplObj.updateStatusMsg(patientList, sheet, successCellStyle,  errorCellStyle, statusCellIndex);
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
	public JsonObject uploadData(JsonArray doctorList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < doctorList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject doctorDataObject = doctorList.get(i).getAsJsonObject();
				JsonObject doctor = doctorDataObject.get("dataObject").getAsJsonObject();
				doctorDataObject.addProperty("retryUpload", 0);
				doctorDataObject.addProperty("retryChildEntitiesUpload", 0);
				doctorDataObject.addProperty("partialUploadUnderProcess", 0);
				com.patientapp.controller.forms.impl.DoctorControllerImpl doctorImplObj = new com.patientapp.controller.forms.impl.DoctorControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = doctorImplObj.getEntityInfoUpdatedWithLookupIds(session, doctor, retValObject);
				if(areAllLookupsFound!=1)
				{
					doctorDataObject.addProperty("retryUpload", 1);
					doctorDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					doctorDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					doctorDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int doctorId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !doctor.has("doctorId"))
				{
					doctor.addProperty("attributeNamePrefix", "");
					
					doctor.addProperty("attributeNamePrefix", "");
					JsonObject responseData = doctorImplObj.getDoctorByLookupFields(session,  doctor);
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
						JsonObject doctorObject = responseData.get("doctorDataObject").getAsJsonObject();
						doctorId = doctorObject.get("doctorId").getAsInt();
						doctor.addProperty("doctorId", doctorId);
						updateEntity = true;
					}
				}
				else if(doctor.has("doctorId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = doctorImplObj.createDoctor(doctor);
				}
				else
				{
					responseData = doctorImplObj.updateDoctor(doctor);
				}
				doctorDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					doctorId =-1;
					doctorDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					doctorDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				doctorId = responseData.get("doctorId").getAsInt();
				doctorDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    			    if(doctor.has("patientList"))
			    {
				    JsonArray patientList = doctor.get("patientList").getAsJsonArray();
					com.patientapp.controller.forms.impl.PatientControllerImpl patientImplObj = new com.patientapp.controller.forms.impl.PatientControllerImpl(session, getUserSessionInfo());
					responseData  = patientImplObj.deletePatientListIfKeyColumnsNotFound(session, doctorId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					responseData  = patientImplObj.uploadData(patientList, doctorId);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						dataObject.addProperty("alert", responseData.get("alert").getAsString());
						dataObject.addProperty("success", 0);
						return dataObject;
					}
					int patientListRetryCount = responseData.get("retryCount").getAsInt();
					//retryCount += patientListRetryCount;
					if(patientListRetryCount>0)
					{
						doctorDataObject.addProperty("retryChildEntitiesUpload", 1);
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject doctor, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getDoctorByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From Doctor where 1 = 1  ";
			String countHql = "select count(*)  from Doctor where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Doctor could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Doctor doctor = (Doctor) it.next();
				JsonObject doctorDataObject = doctor.getDataObject(session);
				dataObject.add("doctorDataObject", doctorDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Doctor could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Doctor' could not be retrieved");
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
		else if(apiName.equals("getDoctorPropertyValue"))
			{
				JsonObject inputDataObject = getDoctorPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getDoctor"))
			{
				JsonObject inputDataObject = getDoctor(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForDoctor"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForDoctor"))
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
			Integer doctorId = requestReceivedParametersInfo.get("doctorId").getAsInt();
			Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
			doctor.setIsRequestUnderProcesss(0);
			session.merge(doctor);
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
	public JsonObject getDoctorPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer doctorId = inputForGetAPI.get("doctorId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		Doctor doctor = (Doctor) session.get(Doctor.class, doctorId);
		doctor.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getDoctor(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer doctorId = inputForGetAPI.get("doctorId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("doctorId", String.valueOf(doctorId));
		JsonObject doctorListObject = retrieveDoctorList(paramsMap);
		if(doctorListObject!=null && doctorListObject.has("success") && doctorListObject.get("success").getAsInt()==1)
		{
			JsonArray doctorList = doctorListObject.get("doctorList").getAsJsonArray();
			if(doctorList.size()>0)
			{
				dataObject.add("doctor", doctorList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Doctor' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject doctorDataObject, Doctor doctor);
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
	public abstract void doAfterLookupRowSelectedImpl(Doctor doctor, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(Doctor doctor, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(Doctor doctor, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
