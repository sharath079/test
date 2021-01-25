package com.patientapp.bean;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.patientapp.util.CommonUtil;
import com.vw.runtime.RulesBean;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import com.patientapp.request.service.ServiceAPIUtil;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("unused")
/**
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
@Entity
@Table(name = "Organisations")
public class Organisations extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "organisationsId")
	private java.lang.Integer organisationsId;
	
			
		
	@Column(name = "organisationName")
	private java.lang.String organisationName;
	
	@Column(name = "addressLine1")
	private java.lang.String addressLine1;
	
	@Column(name = "addressLine2")
	private java.lang.String addressLine2;
	
	@Column(name = "city")
	private java.lang.String city;
	
	@Column(name = "residentState")
	private java.lang.String residentState;
	
	@Column(name = "pinCode")
	private java.lang.String pinCode;
	
	@Column(name = "databaseName")
	private java.lang.String databaseName;
	
	@Column(name = "country")
	private java.lang.String country;

	@Column(name = "vwLastModifiedDate")
	private Date vwLastModifiedDate;
	@Column(name = "vwLastModifiedTime")
	private java.lang.Integer vwLastModifiedTime;
	@Column(name = "vwLastAction")
	private java.lang.String vwLastAction;
	@Column(name = "vwModifiedBy")
	private java.lang.String vwModifiedBy;
	@Column(name = "vwTxnRemarks")
	private java.lang.String vwTxnRemarks;
	@Column(name = "vwTxnStatus")
	private java.lang.String vwTxnStatus;
	@Column(name = "isRequestUnderProcesss")
	private java.lang.Integer isRequestUnderProcesss;
	@Column(name = "legacyRecordId")
	private java.lang.Integer legacyRecordId;
	@Column(name = "vwCreatedBy")
	private java.lang.Integer vwCreatedBy;
	@Column(name = "vwCreationDate")
	private Date vwCreationDate;
	public Date getVwLastModifiedDate()
	{
		return this.vwLastModifiedDate;
	}
	public void setVwLastModifiedDate(Date vwLastModifiedDate)
	{
		this.vwLastModifiedDate = vwLastModifiedDate;
	}
	public Integer getVwLastModifiedTime()
	{
		return this.vwLastModifiedTime;
	}
	public void setVwLastModifiedTime(Integer vwLastModifiedTime)
	{
		this.vwLastModifiedTime = vwLastModifiedTime;
	}
	public String getVwLastAction()
	{
		return this.vwLastAction;
	}
	public void setVwLastAction(String vwLastAction)
	{
		this.vwLastAction = vwLastAction;
	}
	public String getVwModifiedBy()
	{
		return this.vwModifiedBy;
	}
	public void setVwModifiedBy(String vwModifiedBy)
	{
		this.vwModifiedBy = vwModifiedBy;
	}
	public String getVwTxnRemarks()
	{
		return this.vwTxnRemarks;
	}
	public void setVwTxnRemarks(String vwTxnRemarks)
	{
		this.vwTxnRemarks = vwTxnRemarks;
	}
	public String getVwTxnStatus()
	{
		return this.vwTxnStatus;
	}
	public void setVwTxnStatus(String vwTxnStatus)
	{
		this.vwTxnStatus = vwTxnStatus;
	}
	public Integer getIsRequestUnderProcesss()
	{
		return this.isRequestUnderProcesss;
	}
	public void setIsRequestUnderProcesss(Integer isRequestUnderProcesss)
	{
		this.isRequestUnderProcesss = isRequestUnderProcesss;
	}
	public Integer getLegacyRecordId()
	{
		return this.legacyRecordId;
	}
	public void setLegacyRecordId(Integer legacyRecordId)
	{
		this.legacyRecordId = legacyRecordId;
	}
	public Integer getVwCreatedBy()
	{
		return this.vwCreatedBy;
	}
	public void setVwCreatedBy(Integer vwCreatedBy)
	{
		this.vwCreatedBy = vwCreatedBy;
	}
	public Date getVwCreationDate()
	{
		return this.vwCreationDate;
	}
	public void setVwCreationDate(Date vwCreationDate)
	{
		this.vwCreationDate = vwCreationDate;
	}
	public Organisations()
	{
	}
	public Integer getOrganisationsId()
	{
		return this.organisationsId;
	}
	public void setOrganisationsId(Integer organisationsId)
	{
		this.organisationsId = organisationsId;
	}
		
	public String getOrganisationName()
	{
		return this.organisationName;
	}
	public void setOrganisationName(String organisationName)
	{
		this.organisationName = organisationName;
	}
	
	public String getAddressLine1()
	{
		return this.addressLine1;
	}
	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2()
	{
		return this.addressLine2;
	}
	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}
	
	public String getCity()
	{
		return this.city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getResidentState()
	{
		return this.residentState;
	}
	public void setResidentState(String residentState)
	{
		this.residentState = residentState;
	}
	
	public String getPinCode()
	{
		return this.pinCode;
	}
	public void setPinCode(String pinCode)
	{
		this.pinCode = pinCode;
	}
	
	public String getDatabaseName()
	{
		return this.databaseName;
	}
	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("organisationsId", organisationsId);
				
		if(organisationName!=null)
		{
			dataObject.addProperty("organisationName", organisationName);
		}
		else
		{
			dataObject.addProperty("organisationName", "");
		}
		
		if(addressLine1!=null)
		{
			dataObject.addProperty("addressLine1", addressLine1);
		}
		else
		{
			dataObject.addProperty("addressLine1", "");
		}
		
		if(addressLine2!=null)
		{
			dataObject.addProperty("addressLine2", addressLine2);
		}
		else
		{
			dataObject.addProperty("addressLine2", "");
		}
		
		if(city!=null)
		{
			dataObject.addProperty("city", city);
		}
		else
		{
			dataObject.addProperty("city", "");
		}
		
		if(residentState!=null)
		{
			dataObject.addProperty("residentState", residentState);
		}
		else
		{
			dataObject.addProperty("residentState", "");
		}
		
		if(pinCode!=null)
		{
			dataObject.addProperty("pinCode", pinCode);
		}
		else
		{
			dataObject.addProperty("pinCode", "");
		}
		
		if(databaseName!=null)
		{
			dataObject.addProperty("databaseName", databaseName);
		}
		else
		{
			dataObject.addProperty("databaseName", "");
		}
		
		if(country!=null)
		{
			dataObject.addProperty("country", country);
		}
		else
		{
			dataObject.addProperty("country", "");
		}

		if (vwLastModifiedDate != null)
		{
			dataObject.addProperty("vwLastModifiedDate", CommonUtil.getDateInRegularDateFormat(vwLastModifiedDate));
		}
		else
		{
			dataObject.addProperty("vwLastModifiedDate", "");
		}
		if (vwLastModifiedTime != null)
		{
			dataObject.addProperty("vwLastModifiedTime", CommonUtil.getNumberToTime(vwLastModifiedTime));
		}
		else
		{
			dataObject.addProperty("vwLastModifiedTime", "");
		}
		if (vwLastAction != null)
		{
			dataObject.addProperty("vwLastAction", vwLastAction);
		}
		else
		{
			dataObject.addProperty("vwLastAction", "");
		}
		if (vwModifiedBy != null)
		{
			dataObject.addProperty("vwModifiedBy", vwModifiedBy);
		}
		else
		{
			dataObject.addProperty("vwModifiedBy", "");
		}
		if (vwTxnRemarks != null)
		{
			dataObject.addProperty("vwTxnRemarks", vwTxnRemarks);
		}
		else
		{
			dataObject.addProperty("vwTxnRemarks", "");
		}
		if (vwTxnStatus != null)
		{
			dataObject.addProperty("vwTxnStatus", vwTxnStatus);
		}
		else
		{
			dataObject.addProperty("vwTxnStatus", "");
		}
		dataObject.addProperty("isRequestUnderProcesss", isRequestUnderProcesss);
		dataObject.addProperty("lookupDisplayText", getLookupDisplayText(session));
		
		return dataObject;
	}
	public String getLookupDisplayText(Session session)
	{
		String displayText = "";
				
		if(organisationName!=null)
		{
			displayText += organisationName;
		}
		
		
		
		
		
		if(displayText.length()>0)
		{
			displayText += " - ";
		}

		if(displayText.endsWith(" - "))
		{
			displayText = displayText.substring(0, displayText.length()-3);
		}
		return displayText;
	}
	public Object getPropertyValue(Session session, String popertyNameEL)
	{
		JsonObject dataObject = null;
		return getPropertyValue(session, popertyNameEL, dataObject);
	}
	public Object getPropertyValue(Session session, String popertyNameEL, JsonObject dataObject)
	{
		try
		{
			String[] propertyHierarchy = StringUtils.split(popertyNameEL, ".");
			if (propertyHierarchy.length > 1)
			{
				String propertyName = propertyHierarchy[0];
				if(1 > 2)
				{
				}
				
			}
			else
			{
				Method instanceMethod = Organisations.class.getMethod("get" + popertyNameEL);
				return instanceMethod.invoke(this);
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
}
