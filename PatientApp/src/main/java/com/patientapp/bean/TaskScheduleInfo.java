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
@Table(name = "TaskScheduleInfo")
public class TaskScheduleInfo extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "taskScheduleInfoId")
	private java.lang.Integer taskScheduleInfoId;
	
			
		@Column(name = "taskInfoId")
	private java.lang.Integer taskInfoId;
	
	@Column(name = "targetEntityId")
	private java.lang.Integer targetEntityId;
	
	@Column(name = "targetUserId")
	private java.lang.Integer targetUserId;
	
	@Column(name = "notificationMedium")
	private java.lang.String notificationMedium;
	
	@Column(name = "notificationLastSentTime")
	private Date notificationLastSentTime;
	
	@Column(name = "nextNotificationTime")
	private Date nextNotificationTime;

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
	public TaskScheduleInfo()
	{
	}
	public Integer getTaskScheduleInfoId()
	{
		return this.taskScheduleInfoId;
	}
	public void setTaskScheduleInfoId(Integer taskScheduleInfoId)
	{
		this.taskScheduleInfoId = taskScheduleInfoId;
	}
		public Integer getTaskInfoId()
	{
		return this.taskInfoId;
	}
	public void setTaskInfoId(Integer taskInfoId)
	{
		this.taskInfoId = taskInfoId;
	}
	
	public Integer getTargetEntityId()
	{
		return this.targetEntityId;
	}
	public void setTargetEntityId(Integer targetEntityId)
	{
		this.targetEntityId = targetEntityId;
	}
	
	public Integer getTargetUserId()
	{
		return this.targetUserId;
	}
	public void setTargetUserId(Integer targetUserId)
	{
		this.targetUserId = targetUserId;
	}
	
	public String getNotificationMedium()
	{
		return this.notificationMedium;
	}
	public void setNotificationMedium(String notificationMedium)
	{
		this.notificationMedium = notificationMedium;
	}
	
	public Date getNotificationLastSentTime()
	{
		return this.notificationLastSentTime;
	}
	public void setNotificationLastSentTime(Date notificationLastSentTime)
	{
		this.notificationLastSentTime = notificationLastSentTime;
	}
	
	public Date getNextNotificationTime()
	{
		return this.nextNotificationTime;
	}
	public void setNextNotificationTime(Date nextNotificationTime)
	{
		this.nextNotificationTime = nextNotificationTime;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("taskScheduleInfoId", taskScheduleInfoId);
				if(fetchForLookup==false)
		{
			if(taskInfoId != null && taskInfoId>0)
			{
				dataObject.addProperty("taskInfoId", taskInfoId);
				
				
				
				
				TaskInfo  taskInfoObj = (com.patientapp.bean.TaskInfo) session.get(com.patientapp.bean.TaskInfo.class, taskInfoId);   
				dataObject.addProperty("taskInfoDisplayVal", taskInfoObj.getLookupDisplayText(session));
				dataObject.add("taskInfo", taskInfoObj.getDataObject(true, session));
				
				
			}
			else
			{
				dataObject.addProperty("taskInfo", "");
			}
		}
		if(taskInfoId != null && taskInfoId>0)
		{
			dataObject.addProperty("taskInfoId", taskInfoId);
		}
		
		dataObject.addProperty("targetEntityId", targetEntityId);
		
		dataObject.addProperty("targetUserId", targetUserId);
		
		if(notificationMedium!=null)
		{
			dataObject.addProperty("notificationMedium", notificationMedium);
		}
		else
		{
			dataObject.addProperty("notificationMedium", "");
		}
		
		if(notificationLastSentTime!=null)
		{
			dataObject.addProperty("notificationLastSentTime", CommonUtil.getDateInRegularDateTimeStampFormat(notificationLastSentTime));
		}
		else
		{
			dataObject.addProperty("notificationLastSentTime", "");
		}
		
		if(nextNotificationTime!=null)
		{
			dataObject.addProperty("nextNotificationTime", CommonUtil.getDateInRegularDateTimeStampFormat(nextNotificationTime));
		}
		else
		{
			dataObject.addProperty("nextNotificationTime", "");
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
								else if (propertyName.equals("TaskInfoId"))
				{
					Integer taskInfoId = getTaskInfoId();
					if(taskInfoId != null && taskInfoId > 0)
					{
						
						
						
						
						com.patientapp.bean.TaskInfo taskInfoObj = (com.patientapp.bean.TaskInfo) session.get(com.patientapp.bean.TaskInfo.class, taskInfoId);
						return taskInfoObj.getPropertyValue(session, popertyNameEL.substring(propertyName.length() + 1));
						
						
					}
				}

				
				
				
				
				
				
				
				
				
				
			}
			else
			{
				Method instanceMethod = TaskScheduleInfo.class.getMethod("get" + popertyNameEL);
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
