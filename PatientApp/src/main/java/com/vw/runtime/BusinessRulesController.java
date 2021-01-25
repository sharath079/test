package com.vw.runtime;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import com.google.gson.JsonObject;
import com.vw.runtime.VWDefaultController;
import com.vw.runtime.IRulesFields;
import com.vw.runtime.IRulesLabels;
@SuppressWarnings({"unused","unchecked","rawtypes"})
public abstract class BusinessRulesController extends BusinessRulesRepositry
{
	VWMastersBean vwMastersBean = new VWMastersBean();
	IRulesFields currentBean = (IRulesFields)getManagedBean();
	IRulesLabels currentLabels = (IRulesLabels)getManagedBeanLabel();
	public BusinessRulesController(Session session)
	{
		super(session);
	}
	public BusinessRulesController()
	{
		super();
	}
	protected void loadInitObjects() 
	{
		vwMastersBean = new VWMastersBean();
		currentBean = (IRulesFields)getManagedBean();
		currentLabels = (IRulesLabels)getManagedBeanLabel();
	}
	protected void setUpdatedBean()
	{
		setManagedBean(currentBean);
	}
	
		
	abstract public void doValidations();
	abstract protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject);
	abstract protected void doAfterEnrichValues();
	abstract protected void doEnrichSystemValues(String sAction, String sNextStatus);
	abstract protected HashMap getSearchParams();
	abstract public String getLabel(String sResponseField);
	// Trade Helper Methods END
}
