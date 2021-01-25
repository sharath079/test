package com.vw.runtime;
import java.util.HashMap;
import com.google.gson.JsonObject;
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
public class VWSimpleController extends VWDefaultController 
{
	public VWSimpleController() 
	{
	}
	public String getManagedBeanName()
	{
		return "VWRequestObject";
	}
	public String getManagedBeanNameLabel()
	{
		return "VWRequestObject";
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
	}
	public void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObj)
	{
	}
	public void doAfterEnrichValues()
	{
	}
	public Class<?> setBeanLabelClass()
	{
		return VWRequestObject.class;
	}
	public HashMap getSearchParams()
	{
		return null;
	}
	public void setPKValue(Object entityObj)
	{
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
		return "EmployeesId";
	}
	@Override
	public void doValidations()
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void doEnrichSystemValues(String sAction, String sNextStatus)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public String getLabel(String sResponseField)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Class<?> setBeanClass()
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getFieldValue(Object entityBean, String sFieldName)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void setTxnStatus(String sStatus)
	{
		// TODO Auto-generated method stub
	}
}
