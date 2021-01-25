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
@Table(name = "EmailAttachmentLayout")
public class EmailAttachmentLayout extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "emailAttachmentLayoutId")
	private java.lang.Integer emailAttachmentLayoutId;
		
	@Column(name = "taskInfoId")
	private java.lang.Integer taskInfoId;
	public Integer getTaskInfoId()
	{
		return this.taskInfoId;
	}
	public void setTaskInfoId(Integer taskInfoId)
	{
		this.taskInfoId = taskInfoId;
	}
	
			
		
	@Column(name = "emailAttachmentLayoutName")
	private java.lang.String emailAttachmentLayoutName;
	
	@Column(name = "comments")
	private java.lang.String comments;

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
	public EmailAttachmentLayout()
	{
	}
	public Integer getEmailAttachmentLayoutId()
	{
		return this.emailAttachmentLayoutId;
	}
	public void setEmailAttachmentLayoutId(Integer emailAttachmentLayoutId)
	{
		this.emailAttachmentLayoutId = emailAttachmentLayoutId;
	}
		
	public String getEmailAttachmentLayoutName()
	{
		return this.emailAttachmentLayoutName;
	}
	public void setEmailAttachmentLayoutName(String emailAttachmentLayoutName)
	{
		this.emailAttachmentLayoutName = emailAttachmentLayoutName;
	}
	
	public String getComments()
	{
		return this.comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("emailAttachmentLayoutId", emailAttachmentLayoutId);
				
		if(emailAttachmentLayoutName!=null)
		{
			dataObject.addProperty("emailAttachmentLayoutName", emailAttachmentLayoutName);
		}
		else
		{
			dataObject.addProperty("emailAttachmentLayoutName", "");
		}
		
		if(comments!=null)
		{
			dataObject.addProperty("comments", comments);
		}
		else
		{
			dataObject.addProperty("comments", "");
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
			
		dataObject.addProperty("taskInfoId",taskInfoId);
		if(taskInfoId != null && taskInfoId > 0)
		{
			com.patientapp.bean.TaskInfo  taskInfo = (com.patientapp.bean.TaskInfo) session.get(com.patientapp.bean.TaskInfo.class, taskInfoId);   
			JsonObject taskInfoObj = taskInfo.getDataObject(true, session);
			dataObject.addProperty("taskInfoDisplayVal", taskInfoObj.get("lookupDisplayText").getAsString());
			
		}
		
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
				
			}
			else
			{
				Method instanceMethod = EmailAttachmentLayout.class.getMethod("get" + popertyNameEL);
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
