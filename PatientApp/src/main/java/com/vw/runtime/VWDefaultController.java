package com.vw.runtime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.richfaces.component.UIExtendedDataTable;
import com.google.gson.JsonObject;
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
public abstract class VWDefaultController extends VWAbstractController
{
	private String VWT_ORDER_BY = " order by PK_FIELD_NAME DESC";
	private SessionFactory sessionFactory;
	private Session session;
	//private UIComponent component;
	private boolean hasTransactionFailed;
	private String transactionFailureMessage;
	String[] sParams;
	public VWDefaultController()
	{
		debugCode("In VWDefaultController Constructor");
	}
	public VWDefaultController(Session session)
	{
		super(session);
		debugCode("Out VWDefaultController Constructor : With Session parameter");
	}
	public void initTransaction()
	{
		debugCode("In initTransaction() Constructor VWDefaultController");
		session = VWDBUtil.currentSession();
		session.clear();
		debugCode("Out initTransaction() Constructor VWDefaultController");
	}
	public void initTransaction(Session session)
	{
		debugCode("In initTransaction() Constructor VWDefaultController");
		this.session = session;
		debugCode("Out initTransaction() Constructor VWDefaultController");
	}
	public boolean getHasTransactionFailed()
	{
		return hasTransactionFailed;
	}
	public void setHasTransactionFailed(boolean hasTransactionFailed)
	{
		this.hasTransactionFailed = hasTransactionFailed;
	}
	public String getTransactionFailureMessage()
	{
		return transactionFailureMessage;
	}
	public void setTransactionFailureMessage(String transactionFailureMessage)
	{
		setHasTransactionFailed(true);
		if (this.transactionFailureMessage != null && this.transactionFailureMessage.length() > 0)
		{
			this.transactionFailureMessage = this.transactionFailureMessage + transactionFailureMessage;
		}
		else
		{
			this.transactionFailureMessage = transactionFailureMessage;
		}
	}
	public void beginTransaction()
	{
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
	}
	public void rollbackTransaction()
	{
		Transaction tx = getDBSession().getTransaction();
		if (tx.isActive())
		{
			tx.rollback();
		}
	}
	public Boolean finalizeTransaction()
	{
		Transaction tx = getDBSession().getTransaction();
		if (tx.isActive())
		{
			return finalizeTransaction(tx);
		}
		return false;
	}
	public Boolean finalizeTransaction(Transaction tx)
	{
		boolean bHasPassed;
		bHasPassed = handleResponse();
		if (bHasPassed == true)
		{
			//tx.commit();
			return true;
		}
		else
		{
			//tx.rollback();
			return false;
		}
	}
	public void createTransaction()
	{
		debugCode("In createTransaction() VWDefaultController");
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive()) 
		{
			tx.begin();
		}		 
		Object managedBeanLocal = getPrivateManagedBean();
		Object obj = getDBSession().merge(managedBeanLocal);
		setPKValue(obj);
		setManagedBean(getManagedBeanName(), obj);
		// setPrivateManagedBean(obj);
		// tx.commit();
		debugCode("Out createTransaction() VWDefaultController");
	}
	public void refreshEntity()
	{
		// getDBSession().refresh(getManagedBean());
	}
	public String refresh() throws Exception
	{
		debugCode("In refresh() VWAbstractController");
		getDBSession().flush();
		//FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
		debugCode("Out refresh() VWAbstractController");
		return "";
	}
	public String refresh(String sManagedBean) throws Exception
	{
		debugCode("In refresh(String sManagedBean) VWAbstractController");
		getDBSession().flush();
		//FacesContext.getCurrentInstance().getViewRoot().getViewMap().put(sManagedBean, null);
		debugCode("Out refresh(String sManagedBean) VWAbstractController");
		return "";
	}
	public String refreshSession(String sManagedBeanName) throws Exception
	{
		debugCode("In refreshSession(String sManagedBean) VWAbstractController");
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(sManagedBeanName, null);
		debugCode("Out refreshSession(String sManagedBean) VWAbstractController");
		return "";
	}
	public List retrieveEntities(Boolean bPagination)
	{
		return retrieveEntities(bPagination, "", "");
	}
	public List retrieveEntities(Boolean bPagination, String callingEntityName, String callingAttributeName)
	{
		debugCode("In retrieveEntities() VWDefaultController bPagination=" + bPagination);
		List emptyList = new ArrayList();
		if (isActionAllowed(ACTION_RETRIEVE))
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			VWDataScrollerBean vwDataScrollerBean = (VWDataScrollerBean) getManagedBean("VWDataScrollerBean");
			vwDataScrollerBean.setMaxRows(getMaxRows());
			String pktable = this.setBeanClass().getSimpleName();
			pktable = pktable.replace("com.patientapp.bean.", "");
			String hql = "from " + pktable;
			HashMap customSearchParams = doBeforeLookupEntitiesRetrieved(callingEntityName, callingAttributeName);
			Iterator customSearchParamKeysIterator = customSearchParams.keySet().iterator();
			HashMap searchParams = getSearchParams();
			while (customSearchParamKeysIterator.hasNext())
			{
				String customSearchParamKey = (String) customSearchParamKeysIterator.next();
				Object customSearchParamValue = customSearchParams.get(customSearchParamKey);
				searchParams.put(customSearchParamKey, customSearchParamValue);
			}
			// hql = handleSearchParms(hql);
			hql = handleSearchParms(hql, searchParams);
			String sPk = getPkFieldName();
			if (isExists(sPk))
			{
				VWT_ORDER_BY = VWT_ORDER_BY.replaceAll("PK_FIELD_NAME", sPk);
				hql = hql + VWT_ORDER_BY;
			}
			Query query = getDBSession().createQuery(hql);
			query = handleSearchParms(query);
			customSearchParamKeysIterator = customSearchParams.keySet().iterator();
			while (customSearchParamKeysIterator.hasNext())
			{
				String customSearchParamKey = (String) customSearchParamKeysIterator.next();
				Object customSearchParamValue = customSearchParams.get(customSearchParamKey);
				query.setParameter(customSearchParamKey, customSearchParamValue);
			}
			if (bPagination)
			{
				debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				debugCode("Begin Index : " + vwDataScrollerBean.getBeginIndex(getManagedBeanName()));
				debugCode("Max Rows: " + getMaxRows());
				debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				query.setFirstResult(vwDataScrollerBean.getBeginIndex(getManagedBeanName()));
				query.setMaxResults(getMaxRows());
			}
			debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			debugCode("No of Rows for current page: " + query.list().size());
			debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			//tx.commit();
			return query.list();
		}
		debugCode("Out retrieveEntities() VWDefaultController bPagination=" + bPagination);
		return emptyList;
	}
	public List retrieveEntities(String sPrimaryFieldName, Object oPrimaryFieldValue, Boolean bPagination)
	{
		debugCode("In retrieveEntities(String sPrimaryFieldName, Object oPrimaryFieldValue, Boolean bPagination) VWDefaultController bPagination=" + bPagination);
		List emptyList = new ArrayList();
		if (isActionAllowed(ACTION_RETRIEVE))
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			VWDataScrollerBean vwDataScrollerBean = (VWDataScrollerBean) getManagedBean("VWDataScrollerBean");
			String pktable = this.setBeanClass().getSimpleName();
			pktable = pktable.replace("com.patientapp.bean.", "");
			String hql = "from " + pktable + " where " + sPrimaryFieldName + "=:sPrimaryFieldValue";
			hql = handleSearchParms(hql);
			String sPk = getPkFieldName();
			if (isExists(sPk))
			{
				VWT_ORDER_BY = VWT_ORDER_BY.replaceAll("PK_FIELD_NAME", sPk);
				hql = hql + VWT_ORDER_BY;
			}
			Query query = getDBSession().createQuery(hql);
			handleSearchParms(query);
			debugCode("hqllllllllllllllll" + hql);
			if (bPagination)
			{
				debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				debugCode("Begin Index : " + vwDataScrollerBean.getBeginIndex(getManagedBeanName()));
				debugCode("Max Rows: " + vwDataScrollerBean.getMaxRows());
				debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				query.setFirstResult(vwDataScrollerBean.getBeginIndex(getManagedBeanName()));
				// query.setMaxResults(vwDataScrollerBean.getMaxRows());
				query.setMaxResults(getMaxRows());
			}
			query.setParameter("sPrimaryFieldValue", oPrimaryFieldValue);
			debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			debugCode("No of Rows for current page: " + query.list().size());
			debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			//tx.commit();
			return query.list();
		}
		debugCode("Out retrieveEntities(String sPrimaryFieldName, Object oPrimaryFieldValue, Boolean bPagination) VWDefaultController");
		return emptyList;
	}
	public int getMaxRows()
	{
		return 5;
	}
	public List retrieveUniqueTransaction(String sPrimaryFieldName, Object oPrimaryFieldValue, String sFKFieldName, Object oFKFieldValue)
	{
		HashMap searchParams = new HashMap();
		searchParams.put(sPrimaryFieldName, oPrimaryFieldValue);
		searchParams.put(sFKFieldName, oFKFieldValue);
		return retrieveUniqueTransactionAbstract("", searchParams);
	}
	public List retrieveUniqueTransaction(String sPrimaryFieldName, Object oPrimaryFieldValue)
	{
		return retrieveUniqueTransactionAbstract("", sPrimaryFieldName, oPrimaryFieldValue, true);
	}
	public List retrieveUniqueTransaction(String sPrimaryFieldName, Object oPrimaryFieldValue, Boolean bIncludeSearchParams)
	{
		return retrieveUniqueTransactionAbstract("", sPrimaryFieldName, oPrimaryFieldValue, bIncludeSearchParams);
	}
	public List retrieveUniqueTransaction(String pktable, String sPrimaryFieldName, Object oPrimaryFieldValue)
	{
		return retrieveUniqueTransactionAbstract(pktable, sPrimaryFieldName, oPrimaryFieldValue, false);
	}
	public List retrieveUniqueTransactionAbstract(String pktable, HashMap searchParams)
	{
		debugCode("In retrieveUniqueTransaction(String sPrimaryFieldName, Object oPrimaryFieldValue) VWDefaultController ");
		List emptyList = new ArrayList();
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		if (!isExists(pktable))
		{
			pktable = this.setBeanClass().getSimpleName();
			pktable = pktable.replace("com.patientapp.bean.", "");
		}
		String hql = "from " + pktable;
		hql = handleSearchParms(hql, searchParams);
		Query query = getDBSession().createQuery(hql);
		handleSearchParms(query, searchParams);
		debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		debugCode("No of Rows for current page: " + query.list().size());
		debugCode("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		/*
		 * Must be the responsibility of the calling api
		 * to take care of commiting the transaction
		 */
		// tx.commit();
		return query.list();
	}
	private List retrieveUniqueTransactionAbstract(String pktable, String sPrimaryFieldName, Object oPrimaryFieldValue, Boolean bIncludeSearchParams)
	{
		HashMap searchParams = new HashMap();
		searchParams.put(sPrimaryFieldName, oPrimaryFieldValue);
		if (bIncludeSearchParams)
		{
			searchParams.putAll(getSearchParams());
		}
		return retrieveUniqueTransactionAbstract(pktable, searchParams);
	}
	public String handleSearchParms(String sInPutHql)
	{
		HashMap searchParams = getSearchParams();
		return handleSearchParms(sInPutHql, searchParams);
	}
	public Query handleSearchParms(Query query)
	{
		HashMap searchParams = getSearchParams();
		return handleSearchParms(query, searchParams);
	}
	public String handleSearchParms(String sInPutHql, HashMap searchParams)
	{
		String sHql = "";
		String sSearchOpertor = " and ";
		String sWhereOpertor = " where ";
		Iterator it = searchParams.entrySet().iterator();
		boolean addDefaultAnd = false;
		if (sInPutHql.contains(sWhereOpertor))
		{
			addDefaultAnd = true;
		}
		while (it.hasNext())
		{
			Map.Entry pair = (Entry) it.next();
			String sSearchField = (String) pair.getKey();
			Object oSearchValue = (Object) pair.getValue();
			if (isExists(sSearchField))
			{
				if (isExists(oSearchValue))
				{
					if (isExists(sHql) || addDefaultAnd)
					{
						sHql = sHql + " " + sSearchOpertor;
						addDefaultAnd = false;
					}
					sHql = sHql + " " + sSearchField + "=:" + sSearchField;
				}
			}
		}
		if (isExists(sHql))
		{
			if (!sInPutHql.contains(sWhereOpertor))
			{
				sInPutHql = sInPutHql + " " + sWhereOpertor + " " + sHql + " ";
			}
			else
			{
				sInPutHql = sInPutHql + sHql;
			}
		}
		debugCode("Final SQl :" + sInPutHql);
		return sInPutHql;
	}
	public Query handleSearchParms(Query query, HashMap searchParams)
	{
		Iterator it = searchParams.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pair = (Entry) it.next();
			String sSearchField = (String) pair.getKey();
			Object oSearchValue = (Object) pair.getValue();
			if (isExists(sSearchField))
			{
				if (isExists(oSearchValue))
				{
					query.setParameter(sSearchField, oSearchValue);
				}
			}
		}
		return query;
	}
	public java.sql.Date convertUtilDateToSqlDate(java.util.Date date)
	{
		if (date != null && !(date.equals("")))
		{
			Date sqlDate = new Date(date.getTime());
			return (java.sql.Date) sqlDate;
		}
		return null;
	}
	public void updateTransaction()
	{
		debugCode("In updateTransaction() VWDefaultController");
		Object managedBeanLocal = getPrivateManagedBean();
		System.out.println("in updateTransaction: " + managedBeanLocal);
		if (managedBeanLocal != null)
		{
			/*
			 * Transaction tx = getDBSession().getTransaction();
			 * 
			 * if (!tx.isActive())
			 * {
			 * tx.begin();
			 * }
			 */
			// Object obj = getDBSession().merge(managedBean);
			//getDBSession().clear();
			getDBSession().update(managedBeanLocal);
			setManagedBean(getManagedBeanName(), managedBeanLocal);
			setPKValue(managedBeanLocal);
			setManagedBean(getManagedBeanName(), managedBeanLocal);
			// setPrivateManagedBean(managedBeanLocal);
			// tx.commit();
		}
		debugCode("Out updateTransaction() VWDefaultController");
	}
	protected void updateTransactionStatus(String sNextStatus)
	{
		debugCode("In updateTransactionStatus(String sNextStatus)");
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		Object managedBeanLocal = getPrivateManagedBean();
		// Object obj = getDBSession().merge(managedBean);
		getDBSession().update(managedBeanLocal);
		setManagedBean(getManagedBeanName(), managedBeanLocal);
		setPKValue(managedBeanLocal);
		setManagedBean(getManagedBeanName(), managedBeanLocal);
		// setPrivateManagedBean(managedBeanLocal);
		//tx.commit();
		debugCode("Out updateTransactionStatus(String sNextStatus) VWDefaultController");
	}
	public boolean isDeleteAllowed()
	{
		return false;
	}
	public void beforeDeleteTransaction(boolean clearSession)
	{
		if (isTransactionUpdatable() == false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");
		}
	}
	public void afterDeleteTransaction()
	{
	}
	public void deleteTransaction()
	{
		debugCode("In deleteTransaction() VWDefaultController");
		/*
		 * Transaction tx = getDBSession().getTransaction();
		 * 
		 * if (!tx.isActive())
		 * {
		 * tx.begin();
		 * }
		 */
		Object managedBeanLocal = getPrivateManagedBean();
		// Begin NG: Session Clear to avoid duplicate Object ID Issue. TDB:
		// Performance impact of same to be evaluated
		// not sure why "clear" is called. But hierarchial delete would face issues.
		// This code now moved to "beforeDeleteTransaction" in ControllerBase.java
		// getDBSession().clear();
		// End NG:
		getDBSession().delete(managedBeanLocal);
		// tx.commit();
		debugCode("Out deleteTransaction() VWDefaultController");
	}
	public void printTransaction()
	{
		debugCode("In printTransaction() VWDefaultController");
		debugCode("Out printTransaction() VWDefaultController");
	}
	public boolean validateTransaction()
	{
		debugCode("In validateTransaction() VWDefaultController");
		boolean bHasPassed;
		doValidations();
		bHasPassed = handleResponse();
		debugCode("Out validateTransaction()  VWDefaultController");
		return bHasPassed;
	}
	public void enrichTransaction(Boolean doUpdateRules, JsonObject paramsInfoObject)
	{
		debugCode("In enrichTransaction() VWDefaultController");
		doEnrichValues(doUpdateRules, paramsInfoObject);
		debugCode("Out enrichTransaction()  VWDefaultController");
	}
	public void afterEnrichTransaction()
	{
		debugCode("In afterEnrichTransaction() VWDefaultController");
		doAfterEnrichValues();
		debugCode("Out afterEnrichTransaction()  VWDefaultController");
	}
	public void enrichSystemTransaction(String sAction, String sNextStatus)
	{
		debugCode("In enrichSystemTransaction(String sAction) VWDefaultController");
		doEnrichSystemValues(sAction, sNextStatus);
		debugCode("Out enrichSystemTransaction(String sAction)  VWDefaultController");
	}
	public Boolean isActionAllowed(String sAction)
	{
		Boolean bIsActionAllowed = false;
		if (!isActionFromUI())
		{
			return true;
		}
		if (isExists(sAction))
		{
			if (isRajanikanth())
			{
				return true;
			}
		}
		VWSessionObject sessionObject = (VWSessionObject) getManagedBean("VWSessionObject");
		String currentEntity = getManagedBeanName();
		String sAllowedActions = sessionObject.getAllowedActions(currentEntity);
		if (isExists(sAllowedActions))
		{
			bIsActionAllowed = sAllowedActions.contains(":" + sAction + ":");
			if (!bIsActionAllowed)
			{
				String sParams[] = new String[1];
				sParams[0] = sAction;
				addApplicationResponse("VWT", "ERR0003", sParams);
				handleResponse(); // GDN: do not move it outside
			}
		}
		else
		{
			addApplicationResponse("VWT", "ERR0010", sParams);
			handleResponse();// GDN: do not move it outside
		}
		debugCode("*******************loginhandler.getAllowedActions()*********************************");
		debugCode("Entity :" + currentEntity + " Allowed Actions: " + sAllowedActions);
		debugCode("*******************loginhandler.getAllowedActions()*********************************");
		return bIsActionAllowed;
	}
	public void handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue)
	{
		debugCode("In handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue) VWDefaultCoXAQntroller");
		if (isExists(oPrimaryFieldValue))
		{
			List list = retrieveUniqueTransaction(sPrimaryFieldName, oPrimaryFieldValue);
			if (isExists(list))
			{
				if (list.size() > 0)
				{
					addUniqueResponse(sPrimaryFieldName);
				}
			}
		}
		debugCode("Out handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue) VWDefaultCoXAQntroller");
	}
	public void handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue, String sFKFieldName, Object oFKFieldValue)
	{
		debugCode("In handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue,String sFKFieldName, Object oFKFieldValue) VWDefaultCoXAQntroller");
		if (isExists(oPrimaryFieldValue))
		{
			List list = retrieveUniqueTransaction(sPrimaryFieldName, oPrimaryFieldValue, sFKFieldName, oFKFieldValue);
			if (isExists(list))
			{
				if (list.size() > 0)
				{
					addUniqueResponse(sPrimaryFieldName);
				}
			}
		}
		debugCode("Out handleUniqueValidation(String sPrimaryFieldName, Object oPrimaryFieldValue,String sFKFieldName, Object oFKFieldValue) VWDefaultCoXAQntroller");
	}
	public boolean handleResponse()
	{
		debugCode("In handleResponse() VWDefaultController");
		boolean bHasPassed = true;
		String sResponseMessage = "";
		if (hasTransactionFailed == true)
		{
			if (transactionFailureMessage != null && transactionFailureMessage.length() > 0)
			{
				addCustomResponse(transactionFailureMessage);
			}
			else
			{
				addCustomResponse("Error while processing the request !!");
			}
		}
		if (isExists(errorList))
		{
			VWResponseBean respLine = new VWResponseBean();
			for (int i = 0; i < errorList.size(); i++)
			{
				respLine = (VWResponseBean) errorList.get(i);
				sResponseMessage = sResponseMessage + (i + 1) + "). " + respLine.getResponseDesc();
			}
			// below code added by ranjith to handle transaction status and error message
			hasTransactionFailed = true;
			transactionFailureMessage = sResponseMessage;
			bHasPassed = false;
			errorList.clear();
		}
		if (bHasPassed)
		{
			sResponseMessage = responseBean.getResponseMessage("SUCESS0002");
		}
		messageBean.setResponseMessage(sResponseMessage);
		responseBean.setShowResponse(true);
		debugCode("Out handleResponse() VWDefaultController");
		return bHasPassed;
	}
	protected void addResponse(String responseType, String responseSubType, String responseField, String responseValue, String responseCode, String[] sResponseParams)
	{
		debugCode("In addResponse() VWDefaultController");
		VWResponseBean responseBeanLocal = new VWResponseBean();
		String sResponseType = responseType;
		String sResponseSubType = responseSubType;
		String sResponseField = responseField;
		String sResponseFieldLabel = null;
		String sResponseCode = responseCode;
		String sResponseMsg = responseValue;
		String[] sParams = sResponseParams;
		if (sResponseType.matches(responseBeanLocal.RESPONSE_TYPE_FAILED))
		{
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_MANDATORY))
			{
				sResponseCode = "ERR0001";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_MAXLENGTH))
			{
				sResponseCode = "ERR0004";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_UNIQUE))
			{
				sResponseCode = "ERR0005";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_LOGIN))
			{
				sResponseCode = "ERR0002";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_ACTIONNOTALLOWED))
			{
				sResponseCode = "ERR0003";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_NOT_AUTHORISER))
			{
				sResponseCode = "ERR0006";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_AUTHLIMIT))
			{
				sResponseCode = "ERR0007";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_ALLOWED_VALUES))
			{
				sResponseCode = "ERR0008";
			}
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_FAILED_ALLOWED_DECIMAL_VALUES))
			{
				sResponseCode = "ERR0009";
			}
		}
		if (sResponseType.matches(responseBeanLocal.RESPONSE_TYPE_SUCESS))
		{
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_SUCESS_LOGIN))
			{
				sResponseCode = "SUCESS0001";
			}
		}
		if (sResponseType.matches(responseBeanLocal.RESPONSE_TYPE_APPLICATION))
		{
			sResponseCode = responseCode;
		}
		responseBeanLocal.setResponseType(sResponseType);
		responseBeanLocal.setResponseCode(sResponseCode);
		if (isExists(sParams) && (sParams.length > 0))
		{
			sResponseMsg = responseBeanLocal.getMessage(isActionFromUI(), sResponseCode, sParams);
		}
		else
		{
			sResponseMsg = responseBeanLocal.getResponseMessage(sResponseCode);
		}
		sResponseFieldLabel = getLabel(sResponseField);
		if (!isExists(sResponseFieldLabel))
		{
			sResponseFieldLabel = sResponseField;
		}
		if (sResponseType.matches(responseBeanLocal.RESPONSE_TYPE_CUSTOM))
		{
			if (sResponseSubType.matches(responseBeanLocal.RESPONSE_SUBTYPE_CUSTOM_MESSAGE))
			{
				sResponseMsg = sResponseField;
			}
		}
		else
		{
			sResponseMsg = StringUtils.replace(sResponseMsg, responseBeanLocal.RESPONSE_FIELD_KEY, sResponseFieldLabel);
		}
		responseBeanLocal.setResponseDesc(sResponseMsg);
		responseBeanLocal.setResponseField(sResponseField);
		responseBeanLocal.setResponseFieldLabel(sResponseFieldLabel);
		responseBeanLocal.setResponseLang(responseBean.RESPONSE_DEF_LANGUAGE);
		errorList.add(responseBeanLocal);
		debugCode("Out addResponse() VWDefaultController");
	}
	public List getResponseList()
	{
		return errorList;
	}
	// ******************Begin JSF Lifecycle Has to be moved to JSF Handler***************************************
	public Object getManagedBean(String sBeanName)
	{
		debugCode("In getManagedBean() VWDefaultController " + sBeanName);
		Object bean = null;
//		FacesContext fc = FacesContext.getCurrentInstance();
//
//		ELContext elContext = null;
//		if ((fc != null) && isExists(sBeanName))
//		{
//			elContext = fc.getELContext();
//			try
//			{
//				bean = elContext.getELResolver().getValue(elContext, null, sBeanName);
//			} catch (NullPointerException e)
//			{
//				// do something other
//			}
//		}
		if (bean == null)
		{
			bean = getNonManagedBean(sBeanName);
		}
		debugCode("Out getManagedBean() VWDefaultController " + sBeanName);
		return bean;
	}
	public void setManagedBean(Object bean)
	{
		debugCode("In setManagedBean() VWDefaultController ");
		/*
		 * FacesContext fc = FacesContext.getCurrentInstance();
		 * ELContext elContext = null;
		 * if (fc != null)
		 * {
		 * elContext = fc.getELContext();
		 * elContext.getELResolver().setValue(elContext, bean, null, null);
		 * }
		 */
		setPrivateManagedBean(bean);
		setNonManagedBean(getManagedBeanName(), bean);
		debugCode("Out setManagedBean() VWDefaultController ");
	}
	public void setManagedBean(String sManagedBeanName, Object managedBean)
	{
		debugCode("In setManagedBean() VWDefaultController ");
//		FacesContext fc = FacesContext.getCurrentInstance();
//		ELContext elContext = null;
//		if (fc != null)
//		{
//			elContext = fc.getELContext();
//			elContext.getELResolver().setValue(elContext, null, sManagedBeanName, null);
//			elContext.getELResolver().setValue(elContext, null, sManagedBeanName, managedBean);
//		}
		setNonManagedBean(sManagedBeanName, managedBean);
		setPrivateManagedBean(managedBean);
		debugCode("Out setManagedBean() VWDefaultController ");
	}
	/*public void selectionListener(AjaxBehaviorEvent event)
	{
		debugCode("IN Abstract selectionListener ");
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
		Object originalKey = dataTable.getRowKey();
		selectionItems.clear();
		try
		{
			for (Object selectionKey : selection)
			{
				debugCode("Selection Key" + selectionKey.toString());
				dataTable.setRowKey(selectionKey);
				if (dataTable.isRowAvailable())
				{
					selectionItems.add((Object) dataTable.getRowData());
				}
			}
		}
		catch (Exception e)
		{
			// do nothing
		}
		if (selectionItems != null)
		{
			if (selectionItems.size() > 0)
			{
				Object selectRowBean = selectionItems.get(0);
				debugCode("dataTable Object" + selectRowBean);
				debugCode("In select Row Bean selectionItems.size() " + selectionItems.size());
				setSelectRowBean(selectRowBean);
				setSelectRowBean(selectionItems);
			}
		}
		if (isExists(originalKey))
		{
			dataTable.setRowKey(originalKey);
		}
		debugCode("Out Abstract selectionListener ");
	}*/
	public void setManagedBeanFromUI(Object uiRowObject)
	{
		debugCode("IN VWDefaultController setManagedBeanFromUI(Object uiRowObject) ");
		if (selectionItems == null)
		{
			selectionItems = new ArrayList<Object>();
		}
		// getDBSession().refresh(uiRowObject);
		selectionItems.clear();
		selectionItems.add(uiRowObject);
		setSelectRowBean(uiRowObject);
		debugCode("OUT VWDefaultController setManagedBeanFromUI(Object uiRowObject) ");
	}
	public void setManagedBeanFromUI(String sEntityName, Object uiRowObject, String sClearEntities) throws Exception
	{
		String[] sClearEntitityList = StringUtils.split(sClearEntities, ",");
		for (String sEntity : sClearEntitityList)
		{
			refresh(sEntity);
		}
		setManagedBean(sEntityName, uiRowObject);
	}
	public void refreshSession() throws Exception
	{
		debugCode("In refreshSession() VWAbstractController");
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		debugCode("Out refreshSession() VWAbstractController");
	}
	// ******************End JSF Lifecycle Has to be moved to JSF
	// Handler**************************************
	public Session getDBSession()
	{
		return session;
	}
	public void clearDBSession()
	{
		getDBSession().clear();
	}
	public List getDBQuery(String sSql)
	{
		Query query = getDBSession().createQuery(sSql);
		return query.list();
	}
	public Object getDBQuery(String sSql, String sParam1, Object object1)
	{
		return (Object) getDBSession().createQuery(sSql).setParameter(sParam1, object1).uniqueResult();
	}
	public Object getDBQuery(String sSql, String sParam1, Object object1, String sParam2, Object object2)
	{
		return (Object) getDBSession().createQuery(sSql).setParameter(sParam1, object1).setParameter(sParam2, object2).uniqueResult();
	}
	public Object getDBQuery(String sSql, String sParam1, Object object1, String sParam2, Object object2, String sParam3, Object object3)
	{
		return (Object) getDBSession().createQuery(sSql).setParameter(sParam1, object1).setParameter(sParam2, object2).setParameter(sParam3, object3).uniqueResult();
	}
	// NG: Framework Utility Method
	public String getNumberToTime(Integer iTime)
	{
		String sTime = iTime.toString();
		if (isExists(sTime))
		{
			Integer iHowManyToAdd = 9 - sTime.length();
			sTime = StringUtils.leftPad(sTime, sTime.length() + iHowManyToAdd, "0");
		}
		String sTimeHH = sTime.substring(0, 2);
		String sTimeMM = sTime.substring(2, 4);
		String sTimeSS = sTime.substring(4, 6);
		String sTimeMMM = sTime.substring(6, 9);
		sTime = sTimeHH + ":" + sTimeMM + ":" + sTimeSS + ":" + sTimeMMM;
		return sTime;
	}
	public Integer getCurrentTime()
	{
		String sTimePattern = "HHmmssSSS";
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(sTimePattern);
		String sTime = simpleTimeFormat.format(new Date());
		return Integer.parseInt(sTime);
	}
	protected void addActionNotAllowedResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_ACTIONNOTALLOWED;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		sParams = new String[1];
		sParams[0] = responseField;
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addMaxLengthResponse(String responseField, String sMaxLength)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_MAXLENGTH;
		String sResponseField = responseField;
		String sResponseValue = sMaxLength;
		String sResponseCode = "";
		sParams = new String[2];
		sParams[0] = getLabel(sResponseField);
		sParams[1] = sMaxLength;
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addMandatoryResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_MANDATORY;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		sParams = new String[1];
		sParams[0] = getLabel(sResponseField);
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addAllowedValuesResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_ALLOWED_VALUES;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		sParams = new String[1];
		sParams[0] = getLabel(sResponseField);
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addAllowedDecimalValuesResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_ALLOWED_DECIMAL_VALUES;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		sParams = new String[1];
		sParams[0] = getLabel(sResponseField);
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addUniqueResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_UNIQUE;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		sParams = new String[1];
		sParams[0] = getLabel(sResponseField);
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addFailedLoginResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_LOGIN;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addSucessLoginResponse(String responseField)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_SUCESS;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_SUCESS_LOGIN;
		String sResponseField = responseField;
		String sResponseValue = "";
		String sResponseCode = "";
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addNotAuthoriserResponse()
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_NOT_AUTHORISER;
		String sResponseField = "";
		String sResponseValue = "";
		String sResponseCode = "";
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addFailedAuthLimitResponse()
	{
		String sResponseType = responseBean.RESPONSE_TYPE_FAILED;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_FAILED_AUTHLIMIT;
		String sResponseField = "";
		String sResponseValue = "";
		String sResponseCode = "";
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	public void addCustomResponse(String sReponseMessage)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_CUSTOM;
		String sResponseSubType = responseBean.RESPONSE_SUBTYPE_CUSTOM_MESSAGE;
		String sResponseField = sReponseMessage;
		String sResponseValue = "";
		String sResponseCode = "";
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addApplicationResponse(String sApplicationName, String sRespCode, String[] sParams)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_APPLICATION;
		String sResponseSubType = "";
		String sResponseField = "";
		String sResponseValue = "";
		String sResponseCode = sRespCode;
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	protected void addApplicationResponse(String sApplicationName, String sRespCode)
	{
		String sResponseType = responseBean.RESPONSE_TYPE_APPLICATION;
		String sResponseSubType = "";
		String sResponseField = "";
		String sResponseValue = "";
		String sResponseCode = sRespCode;
		addResponse(sResponseType, sResponseSubType, sResponseField, sResponseValue, sResponseCode, sParams);
	}
	public void addCriteria(Criteria criteria, String sField, String sValue)
	{
		if (isExists(sValue))
			criteria.add(addRestrictions(sField, sValue));
	}
	public void addCriteria(Criteria criteria, String sField, Date dValue)
	{
		if (isExists(dValue))
			criteria.add(addRestrictionsEqual(sField, dValue));
	}
	public void addCriteriaEqual(Criteria criteria, String sField, Object oValue)
	{
		if (isExists(oValue))
			criteria.add(addRestrictionsEqual(sField, oValue));
	}
	public void addCriteriaEqual(Criteria criteria, String sField, String sValue)
	{
		if (isExists(sValue))
			criteria.add(addRestrictionsEqual(sField, sValue));
	}
	public void addCriteria(Criteria criteria, String sField, Integer iValue)
	{
		if (isExists(iValue))
			criteria.add(addRestrictions(sField, iValue));
	}
	public void addCriteriaEqual(Criteria criteria, String sField, Integer iValue)
	{
		if (isExists(iValue))
			criteria.add(addRestrictionsEqual(sField, iValue));
	}
	public Criterion addRestrictions(String sFieldName, String sValue)
	{
		return Restrictions.like(sFieldName, sValue);
	}
	public Criterion addRestrictions(String sFieldName, Integer iValue)
	{
		return Restrictions.in(sFieldName, new Integer[]
		{ iValue });
	}
	public Criterion addRestrictionsEqual(String sFieldName, String sValue)
	{
		return Restrictions.eq(sFieldName, sValue);
	}
	public Criterion addRestrictionsEqual(String sFieldName, Object oValue)
	{
		return Restrictions.eq(sFieldName, oValue);
	}
	public Criterion addRestrictionsEqual(String sFieldName, Integer iValue)
	{
		return Restrictions.eq(sFieldName, new Integer[]
		{ iValue });
	}
	public String getMode()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		String sViewBeanName = vwSessionObject.getParamValue("VIEW_BEAN_NAME");
		Object sViewBeanObject = vwSessionObject.getParamObject("VIEW_BEAN_OBJECT");
		if (isExists(sViewBeanName) && isExists(sViewBeanObject))
		{
			setManagedBean(sViewBeanName, sViewBeanObject);
		}
		return "View Mode";
	}
	public boolean isTransactionUpdatable()
	{
		return true;
	}
	public void doBeforeTransactionApproved()
	{
	}
	public void doAfterTransactionApproved()
	{
	}
	public void doBeforeTransactionRolledback()
	{
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		return true;
	}
	public boolean isAuthorizationEnabled()
	{
		return false;
	}
	public boolean isRollbackRequired()
	{
		return false;
	}
	public boolean isSubmitRequired()
	{
		return false;
	}
	public HashMap doBeforeLookupEntitiesRetrieved(String callingEntityName, String callingAttributeName)
	{
		System.out.println("Before lookup entities retrieved in default controller : " + getManagedBeanName());
		HashMap customSearchParams = new HashMap();
		return customSearchParams;
	}
	public void setSession(Session session)
	{
		this.session = session;
	}
	// NG: Abstract Methods
	abstract public void doValidations();
	abstract protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject);
	abstract protected void doAfterEnrichValues();
	abstract protected void doEnrichSystemValues(String sAction, String sNextStatus);
	abstract protected HashMap getSearchParams();
	abstract protected void setPKValue(Object entityObj);
	abstract public String getLabel(String sResponseField);
	abstract protected String getPkFieldName();
}
