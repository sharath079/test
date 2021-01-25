package com.vw.runtime;
import java.io.Serializable;
import java.util.HashMap;
import com.google.gson.JsonObject;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
/*@ManagedBean(name = "VWViewController")
@ViewScoped*/
public class VWViewController extends VWDefaultController implements Serializable
{
	String sViewBeanName = null;
	Object sViewBeanObject = null;
	public VWViewController()
	{
	}
	public String getMode()
	{
		VWSessionObject vwSessionObject = getSessionObject();
		sViewBeanName =  vwSessionObject.getParamValue("VIEW_BEAN_NAME");
		sViewBeanObject = vwSessionObject.getParamObject("VIEW_BEAN_OBJECT");
		if (isExists(sViewBeanName) && isExists(sViewBeanObject))
		{
			setManagedBean(sViewBeanName, sViewBeanObject);
		}	
		return "View Mode";
	}
	public Class<?> setBeanClass()
	{
		return ("com.patientapp.bean."+sViewBeanName).getClass();
	}
	public Class<?> setBeanLabelClass()
	{
		return ( "com.patientapp.controller.base." + sViewBeanName +"Label").getClass();
	}
	public String getManagedBeanName()
	{
		return sViewBeanName+"Bean";
	}
	public String getManagedBeanNameLabel()
	{
		return sViewBeanName+"LabelBean";
	}
	public void setValues(Object sourceBean, Object targetBean, Boolean bSetAsManagedBean)
	{
	}
	public void doValidations(){}
	public void doAfterEnrichValues(){}
	public void doEnrichSystemValues(String sAction){}
	public void setPKValue(Object entityObj){}
	public String getLabel(String sResponseField)
	{
		return null;
	}
	public HashMap getSearchParams()
	{
		return null;
	}
	public void doAfterSelectRow()
	{
	}
	public void afterCreateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void beforeCreateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void afterCreateTransactionCommitted()
	{
	}
	public void afterUpdateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void beforeUpdateTransaction(JsonObject paramsInfoObj)
	{
	}
	public void afterUpdateTransactionCommitted()
	{
	}
	public String getPkFieldName()
	{
		return sViewBeanName + "Id";
	}
	public Object getFieldValue(Object entityBean, String sFieldName)
	{
		return null;
	}
	@Override
	protected void setTxnStatus(String sStatus)
	{
		// TODO Auto-generated method stub
	}
	@Override
	protected void doEnrichSystemValues(String sAction, String sNextStatus)
	{
		// TODO Auto-generated method stub
	}
	@Override
	protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObj)
	{
		// TODO Auto-generated method stub
	}
}
