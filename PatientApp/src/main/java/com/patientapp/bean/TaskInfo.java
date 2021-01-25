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
@Table(name = "TaskInfo")
public class TaskInfo extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "taskInfoId")
	private java.lang.Integer taskInfoId;
	
			
		
	@Column(name = "taskName")
	private java.lang.String taskName;
	
	@Column(name = "taskDescription")
	private java.lang.String taskDescription;
	
	@Column(name = "taskType")
	private java.lang.String taskType;
	
	@Column(name = "apiName")
	private java.lang.String apiName;
	
	@Column(name = "targetEntityQuery")
	private java.lang.String targetEntityQuery;
	
	@Column(name = "targetUserIdColumnAlias")
	private java.lang.String targetUserIdColumnAlias;
	
	@Column(name = "targetEntityIdColumnAlias")
	private java.lang.String targetEntityIdColumnAlias;
	
	@Column(name = "enableInAppNotification")
	private java.lang.String enableInAppNotification;
	
	@Column(name = "inAppNotificationLayout")
	private java.lang.String inAppNotificationLayout;
	
	@Column(name = "enableEmailNotification")
	private java.lang.String enableEmailNotification;
	
	@Column(name = "emailColumnAlias")
	private java.lang.String emailColumnAlias;
	
	@Column(name = "emailNotificationLayout")
	private java.lang.String emailNotificationLayout;
	
	@Column(name = "emailSubject")
	private java.lang.String emailSubject;
	
	@Column(name = "enableSmsNotification")
	private java.lang.String enableSmsNotification;
	
	@Column(name = "smsColumnAlias")
	private java.lang.String smsColumnAlias;
	
	@Column(name = "smsNotificationLayout")
	private java.lang.String smsNotificationLayout;
	
	@Column(name = "sMSText")
	private java.lang.String sMSText;
	
	@Column(name = "isActive")
	private java.lang.String isActive;
	
	@Column(name = "taskStartDate")
	private Date taskStartDate;
	
	@Column(name = "taskFrequency")
	private java.lang.Integer taskFrequency;
	
	@Column(name = "taskFrequencyUnit")
	private java.lang.String taskFrequencyUnit;
	
	@Column(name = "isRecurring")
	private java.lang.String isRecurring;
	
	@Column(name = "firstRunType")
	private java.lang.String firstRunType;
	
	@Column(name = "dateColumnAlias")
	private java.lang.String dateColumnAlias;
	
	@Column(name = "firstRunDateCalculationMethod")
	private java.lang.String firstRunDateCalculationMethod;
	
	@Column(name = "firstRunDateGapInYears")
	private java.lang.Integer firstRunDateGapInYears;
	
	@Column(name = "firstRunDateGapInMonths")
	private java.lang.Integer firstRunDateGapInMonths;
	
	@Column(name = "firstRunDateGapInDays")
	private java.lang.Integer firstRunDateGapInDays;
	
	@Column(name = "firstRunDateGapInHours")
	private java.lang.Integer firstRunDateGapInHours;
	
	@Column(name = "firstRunDateGapInMinutes")
	private java.lang.Integer firstRunDateGapInMinutes;
	
	@Column(name = "firstRunDateGapInSeconds")
	private java.lang.Integer firstRunDateGapInSeconds;

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
	public TaskInfo()
	{
	}
	public Integer getTaskInfoId()
	{
		return this.taskInfoId;
	}
	public void setTaskInfoId(Integer taskInfoId)
	{
		this.taskInfoId = taskInfoId;
	}
		
	public String getTaskName()
	{
		return this.taskName;
	}
	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}
	
	public String getTaskDescription()
	{
		return this.taskDescription;
	}
	public void setTaskDescription(String taskDescription)
	{
		this.taskDescription = taskDescription;
	}
	
	public String getTaskType()
	{
		return this.taskType;
	}
	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}
	
	public String getApiName()
	{
		return this.apiName;
	}
	public void setApiName(String apiName)
	{
		this.apiName = apiName;
	}
	
	public String getTargetEntityQuery()
	{
		return this.targetEntityQuery;
	}
	public void setTargetEntityQuery(String targetEntityQuery)
	{
		this.targetEntityQuery = targetEntityQuery;
	}
	
	public String getTargetUserIdColumnAlias()
	{
		return this.targetUserIdColumnAlias;
	}
	public void setTargetUserIdColumnAlias(String targetUserIdColumnAlias)
	{
		this.targetUserIdColumnAlias = targetUserIdColumnAlias;
	}
	
	public String getTargetEntityIdColumnAlias()
	{
		return this.targetEntityIdColumnAlias;
	}
	public void setTargetEntityIdColumnAlias(String targetEntityIdColumnAlias)
	{
		this.targetEntityIdColumnAlias = targetEntityIdColumnAlias;
	}
	
	public String getEnableInAppNotification()
	{
		return this.enableInAppNotification;
	}
	public void setEnableInAppNotification(String enableInAppNotification)
	{
		this.enableInAppNotification = enableInAppNotification;
	}
	
	public String getInAppNotificationLayout()
	{
		return this.inAppNotificationLayout;
	}
	public void setInAppNotificationLayout(String inAppNotificationLayout)
	{
		this.inAppNotificationLayout = inAppNotificationLayout;
	}
	
	public String getEnableEmailNotification()
	{
		return this.enableEmailNotification;
	}
	public void setEnableEmailNotification(String enableEmailNotification)
	{
		this.enableEmailNotification = enableEmailNotification;
	}
	
	public String getEmailColumnAlias()
	{
		return this.emailColumnAlias;
	}
	public void setEmailColumnAlias(String emailColumnAlias)
	{
		this.emailColumnAlias = emailColumnAlias;
	}
	
	public String getEmailNotificationLayout()
	{
		return this.emailNotificationLayout;
	}
	public void setEmailNotificationLayout(String emailNotificationLayout)
	{
		this.emailNotificationLayout = emailNotificationLayout;
	}
	
	public String getEmailSubject()
	{
		return this.emailSubject;
	}
	public void setEmailSubject(String emailSubject)
	{
		this.emailSubject = emailSubject;
	}
	
	public String getEnableSmsNotification()
	{
		return this.enableSmsNotification;
	}
	public void setEnableSmsNotification(String enableSmsNotification)
	{
		this.enableSmsNotification = enableSmsNotification;
	}
	
	public String getSmsColumnAlias()
	{
		return this.smsColumnAlias;
	}
	public void setSmsColumnAlias(String smsColumnAlias)
	{
		this.smsColumnAlias = smsColumnAlias;
	}
	
	public String getSmsNotificationLayout()
	{
		return this.smsNotificationLayout;
	}
	public void setSmsNotificationLayout(String smsNotificationLayout)
	{
		this.smsNotificationLayout = smsNotificationLayout;
	}
	
	public String getSMSText()
	{
		return this.sMSText;
	}
	public void setSMSText(String sMSText)
	{
		this.sMSText = sMSText;
	}
	
	public String getIsActive()
	{
		return this.isActive;
	}
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}
	
	public Date getTaskStartDate()
	{
		return this.taskStartDate;
	}
	public void setTaskStartDate(Date taskStartDate)
	{
		this.taskStartDate = taskStartDate;
	}
	
	public Integer getTaskFrequency()
	{
		return this.taskFrequency;
	}
	public void setTaskFrequency(Integer taskFrequency)
	{
		this.taskFrequency = taskFrequency;
	}
	
	public String getTaskFrequencyUnit()
	{
		return this.taskFrequencyUnit;
	}
	public void setTaskFrequencyUnit(String taskFrequencyUnit)
	{
		this.taskFrequencyUnit = taskFrequencyUnit;
	}
	
	public String getIsRecurring()
	{
		return this.isRecurring;
	}
	public void setIsRecurring(String isRecurring)
	{
		this.isRecurring = isRecurring;
	}
	
	public String getFirstRunType()
	{
		return this.firstRunType;
	}
	public void setFirstRunType(String firstRunType)
	{
		this.firstRunType = firstRunType;
	}
	
	public String getDateColumnAlias()
	{
		return this.dateColumnAlias;
	}
	public void setDateColumnAlias(String dateColumnAlias)
	{
		this.dateColumnAlias = dateColumnAlias;
	}
	
	public String getFirstRunDateCalculationMethod()
	{
		return this.firstRunDateCalculationMethod;
	}
	public void setFirstRunDateCalculationMethod(String firstRunDateCalculationMethod)
	{
		this.firstRunDateCalculationMethod = firstRunDateCalculationMethod;
	}
	
	public Integer getFirstRunDateGapInYears()
	{
		return this.firstRunDateGapInYears;
	}
	public void setFirstRunDateGapInYears(Integer firstRunDateGapInYears)
	{
		this.firstRunDateGapInYears = firstRunDateGapInYears;
	}
	
	public Integer getFirstRunDateGapInMonths()
	{
		return this.firstRunDateGapInMonths;
	}
	public void setFirstRunDateGapInMonths(Integer firstRunDateGapInMonths)
	{
		this.firstRunDateGapInMonths = firstRunDateGapInMonths;
	}
	
	public Integer getFirstRunDateGapInDays()
	{
		return this.firstRunDateGapInDays;
	}
	public void setFirstRunDateGapInDays(Integer firstRunDateGapInDays)
	{
		this.firstRunDateGapInDays = firstRunDateGapInDays;
	}
	
	public Integer getFirstRunDateGapInHours()
	{
		return this.firstRunDateGapInHours;
	}
	public void setFirstRunDateGapInHours(Integer firstRunDateGapInHours)
	{
		this.firstRunDateGapInHours = firstRunDateGapInHours;
	}
	
	public Integer getFirstRunDateGapInMinutes()
	{
		return this.firstRunDateGapInMinutes;
	}
	public void setFirstRunDateGapInMinutes(Integer firstRunDateGapInMinutes)
	{
		this.firstRunDateGapInMinutes = firstRunDateGapInMinutes;
	}
	
	public Integer getFirstRunDateGapInSeconds()
	{
		return this.firstRunDateGapInSeconds;
	}
	public void setFirstRunDateGapInSeconds(Integer firstRunDateGapInSeconds)
	{
		this.firstRunDateGapInSeconds = firstRunDateGapInSeconds;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("taskInfoId", taskInfoId);
				
		if(taskName!=null)
		{
			dataObject.addProperty("taskName", taskName);
		}
		else
		{
			dataObject.addProperty("taskName", "");
		}
		
		if(taskDescription!=null)
		{
			dataObject.addProperty("taskDescription", taskDescription);
		}
		else
		{
			dataObject.addProperty("taskDescription", "");
		}
		
		if(taskType!=null)
		{
			dataObject.addProperty("taskType", taskType);
		}
		else
		{
			dataObject.addProperty("taskType", "");
		}
		
		if(apiName!=null)
		{
			dataObject.addProperty("apiName", apiName);
		}
		else
		{
			dataObject.addProperty("apiName", "");
		}
		
		if(targetEntityQuery!=null)
		{
			dataObject.addProperty("targetEntityQuery", targetEntityQuery);
		}
		else
		{
			dataObject.addProperty("targetEntityQuery", "");
		}
		
		if(targetUserIdColumnAlias!=null)
		{
			dataObject.addProperty("targetUserIdColumnAlias", targetUserIdColumnAlias);
		}
		else
		{
			dataObject.addProperty("targetUserIdColumnAlias", "");
		}
		
		if(targetEntityIdColumnAlias!=null)
		{
			dataObject.addProperty("targetEntityIdColumnAlias", targetEntityIdColumnAlias);
		}
		else
		{
			dataObject.addProperty("targetEntityIdColumnAlias", "");
		}
		
		if(enableInAppNotification!=null)
		{
			dataObject.addProperty("enableInAppNotification", enableInAppNotification);
		}
		else
		{
			dataObject.addProperty("enableInAppNotification", "");
		}
		
		if(inAppNotificationLayout!=null)
		{
			dataObject.addProperty("inAppNotificationLayout", inAppNotificationLayout);
		}
		else
		{
			dataObject.addProperty("inAppNotificationLayout", "");
		}
		
		if(enableEmailNotification!=null)
		{
			dataObject.addProperty("enableEmailNotification", enableEmailNotification);
		}
		else
		{
			dataObject.addProperty("enableEmailNotification", "");
		}
		
		if(emailColumnAlias!=null)
		{
			dataObject.addProperty("emailColumnAlias", emailColumnAlias);
		}
		else
		{
			dataObject.addProperty("emailColumnAlias", "");
		}
		
		if(emailNotificationLayout!=null)
		{
			dataObject.addProperty("emailNotificationLayout", emailNotificationLayout);
		}
		else
		{
			dataObject.addProperty("emailNotificationLayout", "");
		}
		
		if(emailSubject!=null)
		{
			dataObject.addProperty("emailSubject", emailSubject);
		}
		else
		{
			dataObject.addProperty("emailSubject", "");
		}
		
		if(enableSmsNotification!=null)
		{
			dataObject.addProperty("enableSmsNotification", enableSmsNotification);
		}
		else
		{
			dataObject.addProperty("enableSmsNotification", "");
		}
		
		if(smsColumnAlias!=null)
		{
			dataObject.addProperty("smsColumnAlias", smsColumnAlias);
		}
		else
		{
			dataObject.addProperty("smsColumnAlias", "");
		}
		
		if(smsNotificationLayout!=null)
		{
			dataObject.addProperty("smsNotificationLayout", smsNotificationLayout);
		}
		else
		{
			dataObject.addProperty("smsNotificationLayout", "");
		}
		
		if(sMSText!=null)
		{
			dataObject.addProperty("sMSText", sMSText);
		}
		else
		{
			dataObject.addProperty("sMSText", "");
		}
		
		if(isActive!=null)
		{
			dataObject.addProperty("isActive", isActive);
		}
		else
		{
			dataObject.addProperty("isActive", "");
		}
		
		if(taskStartDate!=null)
		{
			dataObject.addProperty("taskStartDate", CommonUtil.getDateInRegularDateTimeStampFormat(taskStartDate));
		}
		else
		{
			dataObject.addProperty("taskStartDate", "");
		}
		
		dataObject.addProperty("taskFrequency", taskFrequency);
		
		if(taskFrequencyUnit!=null)
		{
			dataObject.addProperty("taskFrequencyUnit", taskFrequencyUnit);
		}
		else
		{
			dataObject.addProperty("taskFrequencyUnit", "");
		}
		
		if(isRecurring!=null)
		{
			dataObject.addProperty("isRecurring", isRecurring);
		}
		else
		{
			dataObject.addProperty("isRecurring", "");
		}
		
		if(firstRunType!=null)
		{
			dataObject.addProperty("firstRunType", firstRunType);
		}
		else
		{
			dataObject.addProperty("firstRunType", "");
		}
		
		if(dateColumnAlias!=null)
		{
			dataObject.addProperty("dateColumnAlias", dateColumnAlias);
		}
		else
		{
			dataObject.addProperty("dateColumnAlias", "");
		}
		
		if(firstRunDateCalculationMethod!=null)
		{
			dataObject.addProperty("firstRunDateCalculationMethod", firstRunDateCalculationMethod);
		}
		else
		{
			dataObject.addProperty("firstRunDateCalculationMethod", "");
		}
		
		dataObject.addProperty("firstRunDateGapInYears", firstRunDateGapInYears);
		
		dataObject.addProperty("firstRunDateGapInMonths", firstRunDateGapInMonths);
		
		dataObject.addProperty("firstRunDateGapInDays", firstRunDateGapInDays);
		
		dataObject.addProperty("firstRunDateGapInHours", firstRunDateGapInHours);
		
		dataObject.addProperty("firstRunDateGapInMinutes", firstRunDateGapInMinutes);
		
		dataObject.addProperty("firstRunDateGapInSeconds", firstRunDateGapInSeconds);

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
				
		if(taskName!=null)
		{
			displayText += taskName;
		}
		
		
		
		
		
		if(displayText.length()>0)
		{
			displayText += " - ";
		}
		
		if(taskDescription!=null)
		{
			displayText += taskDescription;
		}
		
		
		
		
		
		if(displayText.length()>0)
		{
			displayText += " - ";
		}
		
		if(apiName!=null)
		{
			displayText += apiName;
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
				Method instanceMethod = TaskInfo.class.getMethod("get" + popertyNameEL);
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
