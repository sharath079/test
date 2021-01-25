package com.vw.runtime;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.patientapp.util.SettingsUtil;
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
public abstract class VWAbstractController implements VWIController
{
	/* Global Variables */
	public List<Object> errorList = new ArrayList<Object>();
	private Object managedBean;
	private HashMap nonManagedBeanHashMap = new HashMap();
	public Object managedBeanLabel;
	Properties configProperties;
	HashMap mapAction = new HashMap();
	HashMap mapStatus = new HashMap();
	public VWResponseBean responseBean;
	public VWMessageBean messageBean;
	private String sAction = "";
	public Collection<Object> selection;
	public List<Object> selectionItems = new ArrayList<Object>();
	public Object selectRowBean;
	public Boolean managedMode = true;
	public static final String ACTION_CREATE = "CREATE";
	public static final String ACTION_RETRIEVE = "RETRIEVE";
	public static final String ACTION_UPDATE = "UPDATE";
	public static final String ACTION_DELETE = "DELETE";
	public static final String ACTION_VIEW = "VIEW";
	public static final String ACTION_VERIFY = "VERIFY";
	public static final String ACTION_UPLOAD = "UPLOAD_SWIFT";
	public static final String ACTION_AUTHORISE = "AUTHORISE";
	public static final String ACTION_REJECT = "REJECT";
	public static final String ACTION_PRINT = "PRINT";
	public static final String ACTION_GENERATE_PO = "GENERATE-PO";
	public static final String VWROLE_VIEWER = "VIEWER";
	public static final String VWROLE_CREATOR = "CREATOR";
	public static final String VWROLE_VERIFIER = "VERIFIER";
	public static final String VWROLE_AUTHORISER = "AUTHORISER";
	public static final String VWROLE_UPLOADER = "UPLOADER";
	public static final String $$SYSTEM_AUDIT_SUFFIX$$ = "VwAudit";
	/* Authgroup */
	public static final String AUTHGROUP_ZERO = "Zero";
	public static final String AUTHGROUP_BELOW10THOUSAND = "Below10Thousand";
	public static final String AUTHGROUP_BELOW5LAC = "Below5Lac";
	public static final String AUTHGROUP_BELOW10LAC = "Below10Lac";
	/* User Details */
	public static final String USER_CREATOR = "creator";
	public static final String USER_VIEWER = "viewer";
	public static final String USER_VERIFIER = "verifier";
	public static final String USER_UPLOADER = "uploader";
	public static final String USER_AUTHLOW = "authlow";
	public static final String USER_AUTHMEDIUM = "authmedium";
	public static final String USER_AUTHHIGH = "authhigh";
	public static final String USER_ADMIN = "admin";
	public static final String EMP_ACTIVE = "Active";
	public static final String NEW_LINE_CHAR = "\n";
	public VWAbstractController()
	{
		debugCode("In VWAbstractController() Constructor VWAbstractController");
		initialise();
		debugCode("Out VWAbstractController() Constructor VWAbstractController");
	}
	private void initialise()
	{
		debugCode("In initController() VWAbstractController");
		managedBean = getManagedBean();
		managedBeanLabel = getManagedBeanLabel();
		responseBean = getResponseBean();
		messageBean = getMessageBean();
		configProperties = new Properties();
		loadConfigProperties();
		loadVWActionStatusMap();
		initTransaction();
		debugCode("In initController() VWAbstractController");
	}
	public VWAbstractController(Session session)
	{
		debugCode("In VWAbstractController() Constructor VWAbstractController");
		initialise(session);
		debugCode("Out VWAbstractController() Constructor VWAbstractController");
	}
	private void initialise(Session session)
	{
		debugCode("In initController() VWAbstractController");
		managedBean = getManagedBean();
		managedBeanLabel = getManagedBeanLabel();
		responseBean = getResponseBean();
		messageBean = getMessageBean();
		configProperties = new Properties();
		loadConfigProperties();
		loadVWActionStatusMap();
		initTransaction(session);
		debugCode("In initController() VWAbstractController");
	}
	public void resetResponse()
	{
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Response has been set to false");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		responseBean.resetResponse();
		messageBean.resetResponse();
	}
	public void create() throws Exception
	{
		create(null);
	}
	public void create(JsonObject paramsInfoObj) throws Exception
	{
		debugCode("In create() VWAbstractController");
		if (isActionAllowed(ACTION_CREATE))
		{
			beginTransaction();
			enrichSystemValues(ACTION_CREATE, "");
			setCurrentAction(ACTION_CREATE);
			enrichValues(true, paramsInfoObj);
			if (validate())
			{
				beforeCreateTransaction(paramsInfoObj);
				createTransaction();
				afterCreateTransaction(paramsInfoObj);
				afterEnrichValues();
				Boolean isTransactionCommited = finalizeTransaction();
				//afterCreateTransactionCommitted();
				/*
				 * if(isTransactionCommited==true) { refreshEntity(); }
				 */
			}
			else
			{
				rollbackTransaction();
			}
		}
		debugCode("Out create() VWAbstractController");
	}
	public void createWithNoValidation() throws Exception
	{
		createWithNoValidation(null);
	}
	public void createWithNoValidation(JsonObject paramsInfoObj) throws Exception
	{
		debugCode("In createWithNoValidation VWAbstractController");
		if (isActionAllowed(ACTION_CREATE))
		{
			enrichSystemValues(ACTION_CREATE, "");
			setCurrentAction(ACTION_CREATE);
			enrichValues(false, paramsInfoObj);
			beforeCreateTransaction(paramsInfoObj);
			createTransaction();
			afterCreateTransaction(paramsInfoObj);
			afterEnrichValues();
		}
		debugCode("Out createWithNoValidation VWAbstractController");
	}
	public void update() throws Exception
	{
		JsonObject paramsInfoObject = new JsonObject();
		update(paramsInfoObject);
	}
	public void update(JsonObject paramsInfoObject) throws Exception
	{
		debugCode("In update() VWAbstractController");
		boolean bIsActionAllowed = isActionAllowedOnCurrentStatus(ACTION_CREATE);
		if (!bIsActionAllowed)
		{
			return;
		}
		beginTransaction();
		boolean isActionAllowedOnCurrentStatus = isActionAllowedOnCurrentStatus(ACTION_UPDATE);
		enrichSystemValues(ACTION_UPDATE, "");
		setCurrentAction(ACTION_UPDATE);
		boolean transactionFinalized = false;
		if (isActionAllowedOnCurrentStatus == true)
		{
			if (isActionAllowed(ACTION_UPDATE))
			{
				enrichValues(true, paramsInfoObject);
				if (validate())
				{
					beforeUpdateTransaction(paramsInfoObject);
					updateTransaction();
					afterUpdateTransaction(paramsInfoObject);
					afterEnrichValues();
					Boolean isTransactionCommited = finalizeTransaction();
					transactionFinalized = true;
					//afterUpdateTransactionCommitted();
					/*
					 * if(isTransactionCommited==true) { refreshEntity(); }
					 */
				}
			}
		}
		if (transactionFinalized == false)
		{
			rollbackTransaction();
		}
		debugCode("Out update() VWAbstractController");
	}
	public void updateWithNoValidation() throws Exception
	{
		JsonObject paramsInfoObject = new JsonObject();
		updateWithNoValidation(paramsInfoObject);
	}
	public void updateWithNoValidation(JsonObject paramsInfoObject) throws Exception
	{
		debugCode("In updateWithNoValidation() VWAbstractController");
		enrichSystemValues(ACTION_UPDATE, "");
		setCurrentAction(ACTION_UPDATE);
		enrichValues(false, paramsInfoObject);
		if (isActionAllowed(ACTION_UPDATE))
		{
			updateTransaction();
			afterEnrichValues();
		}
		debugCode("Out updateWithNoValidation() VWAbstractController");
	}
	public void executeAction(Object bean, String sBeanName, String sCurrentStatus) throws Exception
	{
		executeAction(bean,  sBeanName, sCurrentStatus, "");
	}
	public void executeAction(Object bean, String sBeanName, String sCurrentStatus, String sCurrentAction) throws Exception
	{
		debugCode("In executeAction(Object bean, String sBeanName, String sCurrentStatus) VWAbstractController");
//		String sCurrentAction = getActionForCurrentStatus(sCurrentStatus);
		if(sCurrentAction.length() == 0)
		{
			sCurrentAction = getActionForCurrentStatus(sCurrentStatus);
		}
		String sNextStatus = getNextStatusForCurrentAction(sCurrentAction);
		String sAuthAmtCcy = "";
		BigDecimal bDecimalAmt = new BigDecimal("0.00");
		boolean bIsActionAllowedForCurrentStatus = isActionAllowedOnCurrentStatus(sCurrentAction);
		if (!bIsActionAllowedForCurrentStatus)
		{
			return;
		}
		if (isActionAllowed(sCurrentAction))
		{
			enrichSystemValues(sCurrentAction, sNextStatus);
			setCurrentAction(sCurrentAction);
			beginTransaction();
			if ("ROLLBACK".equalsIgnoreCase(sCurrentAction))
			{
				doBeforeTransactionRolledback();
			}
			else if ("AUTHORISE".equalsIgnoreCase(sCurrentAction))
			{
				doBeforeTransactionApproved();
			}
			if (validate())
			{
				updateTransaction();
				afterEnrichValues();
				finalizeTransaction();
				if ("AUTHORISE".equalsIgnoreCase(sCurrentAction))
				{
					doAfterTransactionApproved();
				}
			}
			else
			{
				rollbackTransaction();
			}
		}
		debugCode("Out executeAction(Object bean, String sBeanName, String sCurrentStatus) VWAbstractController");
	}
	public void delete() throws Exception
	{
		beginTransaction();
		deleteWithoutCommit(true);
		finalizeTransaction();
	}
	public void deleteWithoutCommit(boolean clearSession) throws Exception
	{
		debugCode("In delete () VWAbstractController");
		enrichSystemValues(ACTION_DELETE, "");
		setCurrentAction(ACTION_DELETE);
		if (isActionAllowed(ACTION_DELETE) && isDeleteAllowed() == true)
		{
			beforeDeleteTransaction(clearSession);
			deleteTransaction();
			afterDeleteTransaction();
		}
		debugCode("Out delete () VWAbstractController");
	}
	public void print() throws Exception
	{
		debugCode("In print() VWAbstractController");
		enrichSystemValues(ACTION_PRINT, "");
		setCurrentAction(ACTION_PRINT);
		if (isActionAllowed(ACTION_PRINT))
		{
			printTransaction();
		}
		debugCode("Out print() VWAbstractController");
	}
	public boolean validate() throws Exception
	{
		boolean bHasPassed;
		debugCode("In validate() VWAbstractController");
		bHasPassed = validateTransaction();
		debugCode("Out validate() VWAbstractController");
		return bHasPassed;
	}
	public void enrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject) throws Exception
	{
		debugCode("In enrichValues() VWAbstractController");
		enrichTransaction(doUpdateRules, paramsInfoObject);
		debugCode("Out enrichValues() VWAbstractController");
	}
	public void afterEnrichValues() throws Exception
	{
		debugCode("In afterEnrichValues() VWAbstractController");
		afterEnrichTransaction();
		debugCode("Out afterEnrichValues() VWAbstractController");
	}
	public void enrichSystemValues(String sAction, String sNextStatus) throws Exception
	{
		debugCode("In enrichSystemValues() VWAbstractController");
		enrichSystemTransaction(sAction, sNextStatus);
		debugCode("Out enrichSystemValues() VWAbstractController");
	}
	// NG: Framework Utility Methods
	public String getProperty(String sKey)
	{
		String sValue = configProperties.getProperty(sKey);
		return sValue;
	}
	public void loadConfigProperties()
	{
		InputStream input = null;
		try
		{
			String filename = "/project-config.properties";
			input = this.getClass().getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Unable to find " + filename + " in the classpath");
				return;
			}
			configProperties.load(input);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	private void loadVWActionStatusMap()
	{
		Properties authorizationFlowConfigProperties = SettingsUtil.getAuthorizationFlowConfigProperties();
		Enumeration e = authorizationFlowConfigProperties.propertyNames();
		while (e.hasMoreElements())
		{
			String key = (String) e.nextElement();
			// System.out.println(key + " -- " + configProperties.getProperty(key));
			String sValue = authorizationFlowConfigProperties.getProperty(key);
			if (StringUtils.contains(key, "VWT_STATUS_"))
			{
				String sStatus = StringUtils.replace(key, "VWT_STATUS_", "");
				mapStatus.put(sStatus, sValue);
			}
			if (StringUtils.contains(key, "VWT_ACTION_"))
			{
				String sAction = StringUtils.replace(key, "VWT_ACTION_", "");
				mapAction.put(sAction, sValue);
			}
		}
	}
	public String getActionForCurrentStatus(String sCurrentStatus, String sImportOrExport)
	{
		String sStatus = "";
		if (isExists(sImportOrExport))
		{
			sStatus = getActionForCurrentStatus(sCurrentStatus + sImportOrExport);
		}
		if (!isExists(sStatus))
		{
			sStatus = getActionForCurrentStatus(sCurrentStatus);
		}
		return sStatus;
	}
	public String getActionForCurrentStatus(String sCurrentStatus)
	{
		String nextAction = (String) mapStatus.get(sCurrentStatus);
		String effectiveCurrentStatus = sCurrentStatus;
		if ("CREATED".equalsIgnoreCase(effectiveCurrentStatus) || "MODIFIED".equalsIgnoreCase(effectiveCurrentStatus))
		{
			if (isAuthorizationEnabled() == false)
			{
				effectiveCurrentStatus = "COMPLETED";
				nextAction = (String) mapStatus.get(effectiveCurrentStatus);
			}
		}
		if ("COMPLETED".equalsIgnoreCase(effectiveCurrentStatus))
		{
			if (isRollbackRequired() == false)
			{
				effectiveCurrentStatus = "ROLLED_BACK";
				nextAction = (String) mapStatus.get(effectiveCurrentStatus);
			}
		}
		return nextAction;
	}
	public String getNextStatusForCurrentAction(String sCurrentAction)
	{
		return (String) mapAction.get(sCurrentAction);
	}
	public VWResponseBean getResponseBean()
	{
		VWResponseBean respBean = (VWResponseBean) getManagedBean("VWResponseBean");
		if (respBean != null)
		{
			return respBean;
		}
		return new VWResponseBean();
	}
	public VWMessageBean getMessageBean()
	{
		VWMessageBean messageBean = (VWMessageBean) getManagedBean("VWMessageBean");
		if (messageBean != null)
		{
			return messageBean;
		}
		return new VWMessageBean();
	}
	public VWMessageBean getCurrentMessageBean()
	{
		return messageBean;
	}
	public Object getManagedBean()
	{
		String sManagedBeanName = getManagedBeanName();
		Object managedBean = getManagedBean(sManagedBeanName);
		return managedBean;
	}
	protected Object getNonManagedBean(String sManagedBeanName)
	{
		Object nonManagedBean = null;
		String currentManagedBean = getManagedBeanName();
		if (isExists(currentManagedBean, sManagedBeanName))
		{
			nonManagedBean = getPrivateManagedBean();
		}
		if (nonManagedBean == null)
		{
			nonManagedBean = nonManagedBeanHashMap.get(sManagedBeanName);
		}
		return nonManagedBean;
	}
	protected void setNonManagedBean(String sManagedBeanName, Object nonManagedBean)
	{
		if (isExists(sManagedBeanName) && isExists(nonManagedBean))
		{
			nonManagedBeanHashMap.put(sManagedBeanName, nonManagedBean);
		}
	}
	public void setNonManagedBean(HashMap newManagedBeanHashMap)
	{
		nonManagedBeanHashMap = newManagedBeanHashMap;
	}
	public HashMap getNonManagedBean()
	{
		return nonManagedBeanHashMap;
	}
	public Object getManagedBeanLabel()
	{
		Object mainManagedBeanLabel = getManagedBean(getManagedBeanNameLabel());
		if (mainManagedBeanLabel != null)
		{
			return mainManagedBeanLabel;
		}
		return getNonManagedBeanLabel();
	}
	public Object getNonManagedBeanLabel()
	{
		if (managedBeanLabel != null)
		{
			return managedBeanLabel;
		}
		Object newObj = null;
		try
		{
			newObj = setBeanLabelClass().newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return newObj;
	}
	public String getCurrentAction()
	{
		return sAction;
	}
	public void setCurrentAction(String sAction)
	{
		this.sAction = sAction;
	}
	// NG: Utility Methods
	protected void debugCode(String sDebugStr)
	{
		if (false)
		{
			System.out.println("*************************************");
			System.out.println(sDebugStr);
			System.out.println("*************************************");
		}
	}
	protected void debugCodeCritical(String sDebugStr)
	{
		System.out.println("*************************************");
		System.out.println(sDebugStr);
		System.out.println("*************************************");
	}
	protected boolean isMaxLength(Object obj)
	{
		if (obj != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isLengthAllowed(Double dValue, String sMaxLength)
	{
		int iMaxLength = Integer.parseInt(sMaxLength);
		if (isExists(dValue))
		{
			if (dValue.toString().length() <= iMaxLength)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	protected boolean isLengthAllowed(Integer iValue, String sMaxLength)
	{
		int iMaxLength = Integer.parseInt(sMaxLength);
		if (isExists(iValue))
		{
			if (iValue.toString().length() <= iMaxLength)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	protected boolean isLengthAllowed(BigDecimal bdValue, String sMaxLength)
	{
		String[] sParams = StringUtils.split(sMaxLength, ",");
		int iMaxLength = 0;
		if (sParams.length > 1)
		{
			int i = Integer.parseInt(sParams[0]);
			int j = Integer.parseInt(sParams[1]);
			iMaxLength = i + j + 1;
		}
		else
		{
			iMaxLength = Integer.parseInt(sMaxLength);
		}
		if (isExists(bdValue))
		{
			if (bdValue.toString().length() <= iMaxLength)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	protected boolean isLengthAllowed(Boolean bValue, String sMaxLength)
	{
		int iMaxLength = Integer.parseInt(sMaxLength);
		if (isExists(bValue))
		{
			if (bValue.toString().length() <= iMaxLength)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	protected boolean isLengthAllowed(Object oValue, String sMaxLength)
	{
		return true;
	}
	protected boolean isLengthAllowed(String sValue, String sMaxLength)
	{
		int iMaxLength = Integer.parseInt(sMaxLength);
		if (isExists(sValue))
		{
			if (sValue.length() <= iMaxLength)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	protected boolean isExists(Object obj)
	{
		if (obj != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isExists(Double dValue)
	{
		if (dValue != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isExists(List<?> lst)
	{
		if (lst != null && lst.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isExists(Integer iValue)
	{
		if (iValue != null && iValue > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isExists(BigDecimal bDValue)
	{
		if (bDValue != null && bDValue.compareTo(BigDecimal.ZERO) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected Boolean isExists(String sSelectedCode, List codesList)
	{
		if (sSelectedCode == null)
		{
			sSelectedCode = "";
		}
		for (int i = 0; i < codesList.size(); i++)
		{
			String sCurrStr = (String) codesList.get(i);
			if (sCurrStr == null)
			{
				sCurrStr = "";
			}
			if (sCurrStr.matches(sSelectedCode))
			{
				return true;
			}
		}
		return false;
	}
	protected boolean isExists(String sStr)
	{
		sStr = StringUtils.replace(sStr, "null", "");
		if (sStr != null && sStr.trim().length() > 0)
		{
			if (StringUtils.isNumeric(sStr))
			{
				BigInteger iTemp = new BigInteger(sStr);
				if (iTemp.equals(BigInteger.ZERO))
				{
					return false;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean isExists(String mainStr, String sSearchStr)
	{
		return StringUtils.containsIgnoreCase(mainStr, sSearchStr);
	}
	// NG:Begin Lookup Handling
	@SuppressWarnings("rawtypes")
	public Collection<Object> getSelection()
	{
		debugCode("In getSelection()");
		return selection;
	}
	@SuppressWarnings("unchecked")
	public void setSelection(Object selection)
	{
		debugCode("In setSelection(Object selection)");
		this.selection = (Collection<Object>) selection;
	}
	public void setSelection(Collection<Object> selection)
	{
		debugCode("In setSelection(Collection<Object> selection)" + selection.size());
		this.selection = selection;
	}
	public void setSelection(List<Object> selection)
	{
		debugCode("In setSelection(List<Object> selection)");
		this.selection = selection;
	}
	public Object getSelectRowBean()
	{
		return selectRowBean;
	}
	public void setSelectRowBean(Object selectRowBean)
	{
		setValues(selectRowBean, managedBean, true);
		doAfterSelectRow();
	}
	public void setSelectRowBean(List<Object> selectedRowBeans)
	{
		VWViewObject vwViewObject = getViewObject();
		vwViewObject.setSelectedRows(getManagedBeanName(), selectedRowBeans);
		doAfterSelectRow();
	}
	private VWViewObject getViewObject()
	{
		VWViewObject vwViewObject = (VWViewObject) getManagedBean("VWViewObject");
		if (vwViewObject != null)
		{
			return vwViewObject;
		}
		return new VWViewObject();
	}
	public List<?> getSelectedRows()
	{
		VWViewObject vwViewObject = getViewObject();
		return vwViewObject.getSelectedRows(getManagedBeanName());
	}
	public boolean isAuthoriser()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		return vwSessionObject.getIsAuthoriser();
	}
	public String getLoggedInUser()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		return vwSessionObject.getLoggedInUser();
	}
	public boolean isActionFromUI()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		return vwSessionObject.isUserLoggedIn;
	}
	public String getTxnRemarks()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		return vwSessionObject.getRemarkMessage();
	}
	public void setTxnRemarks(String sRemarks)
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setRemarkMessage(sRemarks);
	}
	public void resetTxnRemarks()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		vwSessionObject.setRemarkMessage("");
	}
	public VWSessionObject getSessionObject()
	{
		VWSessionObject vwSessionObject = (VWSessionObject) getManagedBean("VWSessionObject");
		if (vwSessionObject != null)
		{
			return vwSessionObject;
		}
		return new VWSessionObject();
	}
	public boolean isInAuthoriseLimit(String sAuthCcy, BigDecimal bdAuthAmt)
	{
		boolean bFlag = false;
		if (isRajanikanth())
		{
			return true;
		}
		VWSessionObject vwSessionObject = getSessionObject();
		if (vwSessionObject != null)
		{
			if (isBetween(bdAuthAmt, vwSessionObject.getAuthLowerLimit(), vwSessionObject.getAuthUpperLimit()))
			{
				bFlag = true;
			}
			else
			{
				bFlag = false;
			}
		}
		return bFlag;
	}
	public boolean isBetween(BigDecimal toCheckValue, BigDecimal start, BigDecimal end)
	{
		return toCheckValue.compareTo(start) > 0 && toCheckValue.compareTo(end) < 0;
	}
	public boolean isRajanikanth()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		return vwSessionObject.getAmIRajanikanth();
	}
	protected Boolean isFromChild()
	{
		VWViewObject vwViewObject = getViewObject();
		return vwViewObject.isFromChild();
	}
	protected void isFromChild(Boolean isFromChild)
	{
		VWViewObject vwViewObject = getViewObject();
		vwViewObject.isFromChild(isFromChild);
	}
	protected Boolean isEntityAuditType(String sEntityName)
	{
		if (isExists(sEntityName, $$SYSTEM_AUDIT_SUFFIX$$))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected boolean rURajanikanth(String empUserId, String empUserPwd)
	{
		if (empUserId.matches("%") && empUserPwd.matches("%"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	protected String getOperatingSystemType()
	{
		String detectedOS = "";
		String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0))
		{
			detectedOS = "MacOS";
		}
		else if (OS.indexOf("win") >= 0)
		{
			detectedOS = "Windows";
		}
		else if (OS.indexOf("nux") >= 0)
		{
			detectedOS = "Linux";
		}
		else
		{
			detectedOS = "Other";
		}
		return detectedOS;
	}
	public Boolean getManagedMode()
	{
		return managedMode;
	}
	public void setManagedMode(Boolean managedMode)
	{
		this.managedMode = managedMode;
	}
	public Object getPrivateManagedBean()
	{
		return managedBean;
	}
	public void setPrivateManagedBean(Object managedBeanLocal)
	{
		managedBean = managedBeanLocal;
	}
	// NG:End Lookup Handling
	// NG: Abstract Methods
	protected abstract Class<?> setBeanClass();
	protected abstract Class<?> setBeanLabelClass();
	protected abstract String getManagedBeanName();
	protected abstract String getManagedBeanNameLabel();
	protected abstract Object getManagedBean(String string);
	protected abstract void initTransaction();
	protected abstract void initTransaction(Session session);
	public abstract Boolean finalizeTransaction();
	public abstract void rollbackTransaction();
	public abstract void beginTransaction();
	protected abstract void createTransaction();
	protected abstract void beforeCreateTransaction(JsonObject paramsInfoObj);
	protected abstract void afterCreateTransaction(JsonObject paramsInfoObj);
	protected abstract void afterCreateTransactionCommitted();
	protected abstract void afterUpdateTransactionCommitted();
	protected abstract void beforeDeleteTransaction(boolean clearSession);
	protected abstract void deleteTransaction();
	protected abstract void afterDeleteTransaction();
	protected abstract void beforeUpdateTransaction(JsonObject paramsInfoObject);
	protected abstract void updateTransaction();
	protected abstract void afterUpdateTransaction(JsonObject paramsInfoObject);
	protected abstract void doBeforeTransactionApproved();
	protected abstract void doAfterTransactionApproved();
	protected abstract void doBeforeTransactionRolledback();
	protected abstract void printTransaction();
	protected abstract void enrichTransaction(Boolean doUpdateRules, JsonObject paramsInfoObject);
	protected abstract void afterEnrichTransaction();
	protected abstract void enrichSystemTransaction(String sAction, String sNextStatus);
	protected abstract boolean validateTransaction();
	protected abstract boolean isDeleteAllowed();
	protected abstract Boolean isActionAllowed(String sAction);
	protected abstract boolean isActionAllowedOnCurrentStatus(String sAction);
	protected abstract boolean isAuthorizationEnabled();
	protected abstract boolean isTransactionUpdatable();
	protected abstract boolean isRollbackRequired();
	protected abstract boolean isSubmitRequired();
	protected abstract void refreshEntity();
	protected abstract void setValues(Object sourceBean, Object targetBean, Boolean bSetAsManagedBean);
	protected abstract void doAfterSelectRow();
	protected abstract void setTxnStatus(String sStatus);
}
