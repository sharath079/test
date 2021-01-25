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
@Table(name = "UserInfo")
public class UserInfo extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "userInfoId")
	private java.lang.Integer userInfoId;
	
			
		
	@Column(name = "userFirstName")
	private java.lang.String userFirstName;
	
	@Column(name = "userLastName")
	private java.lang.String userLastName;
	@Column(name = "organisationsUserOrgId")
	private java.lang.Integer organisationsUserOrgId;
	
	@Column(name = "loginId")
	private java.lang.String loginId;
	
	@Column(name = "loginEmailId")
	private java.lang.String loginEmailId;
	
	@Column(name = "contactNo")
	private java.lang.String contactNo;
	
	@Column(name = "loginPassword")
	private java.lang.String loginPassword;
	
	@Column(name = "resetToken")
	private java.lang.String resetToken;

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
	public UserInfo()
	{
	}
	public Integer getUserInfoId()
	{
		return this.userInfoId;
	}
	public void setUserInfoId(Integer userInfoId)
	{
		this.userInfoId = userInfoId;
	}
		
	public String getUserFirstName()
	{
		return this.userFirstName;
	}
	public void setUserFirstName(String userFirstName)
	{
		this.userFirstName = userFirstName;
	}
	
	public String getUserLastName()
	{
		return this.userLastName;
	}
	public void setUserLastName(String userLastName)
	{
		this.userLastName = userLastName;
	}
	public Integer getOrganisationsUserOrgId()
	{
		return this.organisationsUserOrgId;
	}
	public void setOrganisationsUserOrgId(Integer organisationsUserOrgId)
	{
		this.organisationsUserOrgId = organisationsUserOrgId;
	}
	
	public String getLoginId()
	{
		return this.loginId;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	
	public String getLoginEmailId()
	{
		return this.loginEmailId;
	}
	public void setLoginEmailId(String loginEmailId)
	{
		this.loginEmailId = loginEmailId;
	}
	
	public String getContactNo()
	{
		return this.contactNo;
	}
	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}
	
	public String getLoginPassword()
	{
		return this.loginPassword;
	}
	public void setLoginPassword(String loginPassword)
	{
		this.loginPassword = loginPassword;
	}
	
	public String getResetToken()
	{
		return this.resetToken;
	}
	public void setResetToken(String resetToken)
	{
		this.resetToken = resetToken;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("userInfoId", userInfoId);
				
		if(userFirstName!=null)
		{
			dataObject.addProperty("userFirstName", userFirstName);
		}
		else
		{
			dataObject.addProperty("userFirstName", "");
		}
		
		if(userLastName!=null)
		{
			dataObject.addProperty("userLastName", userLastName);
		}
		else
		{
			dataObject.addProperty("userLastName", "");
		}
		if(fetchForLookup==false)
		{
			if(organisationsUserOrgId != null && organisationsUserOrgId>0)
			{
				dataObject.addProperty("organisationsUserOrgId", organisationsUserOrgId);
				
				
				
				
				Organisations  organisationsUserOrgObj = (com.patientapp.bean.Organisations) session.get(com.patientapp.bean.Organisations.class, organisationsUserOrgId);   
				dataObject.addProperty("organisationsUserOrgDisplayVal", organisationsUserOrgObj.getLookupDisplayText(session));
				dataObject.add("organisationsUserOrg", organisationsUserOrgObj.getDataObject(true, session));
				
				
			}
			else
			{
				dataObject.addProperty("organisationsUserOrg", "");
			}
		}
		if(organisationsUserOrgId != null && organisationsUserOrgId>0)
		{
			dataObject.addProperty("organisationsUserOrgId", organisationsUserOrgId);
		}
		
		if(loginId!=null)
		{
			dataObject.addProperty("loginId", loginId);
		}
		else
		{
			dataObject.addProperty("loginId", "");
		}
		
		if(loginEmailId!=null)
		{
			dataObject.addProperty("loginEmailId", loginEmailId);
		}
		else
		{
			dataObject.addProperty("loginEmailId", "");
		}
		
		if(contactNo!=null)
		{
			dataObject.addProperty("contactNo", contactNo);
		}
		else
		{
			dataObject.addProperty("contactNo", "");
		}
		
		if(loginPassword!=null)
		{
			dataObject.addProperty("loginPassword", loginPassword);
		}
		else
		{
			dataObject.addProperty("loginPassword", "");
		}
		
		if(resetToken!=null)
		{
			dataObject.addProperty("resetToken", resetToken);
		}
		else
		{
			dataObject.addProperty("resetToken", "");
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
				
				
				
				
								else if (propertyName.equals("OrganisationsUserOrgId"))
				{
					Integer organisationsUserOrgId = getOrganisationsUserOrgId();
					if(organisationsUserOrgId != null && organisationsUserOrgId > 0)
					{
						
						
						
						
						com.patientapp.bean.Organisations organisationsUserOrgObj = (com.patientapp.bean.Organisations) session.get(com.patientapp.bean.Organisations.class, organisationsUserOrgId);
						return organisationsUserOrgObj.getPropertyValue(session, popertyNameEL.substring(propertyName.length() + 1));
						
						
					}
				}

				
				
				
				
				
				
				
				
				
				
			}
			else
			{
				Method instanceMethod = UserInfo.class.getMethod("get" + popertyNameEL);
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
