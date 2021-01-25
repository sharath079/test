package com.patientapp.bean;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.vw.runtime.RulesBean;
@SuppressWarnings("unused")
/**
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
public class TaskInfoSearch extends RulesBean implements java.io.Serializable
{
	/*private static final long serialVersionUID = 1L;
	private java.lang.Integer taskInfoId ;
		private $$JAVA_FIELD_TYPE$$ taskName $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ taskDescription $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ taskType $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ apiName $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ targetEntityQuery $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ targetUserIdColumnAlias $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ targetEntityIdColumnAlias $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ enableInAppNotification $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ inAppNotificationLayout $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ enableEmailNotification $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ emailColumnAlias $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ emailNotificationLayout $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ emailSubject $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ enableSmsNotification $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ smsColumnAlias $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ smsNotificationLayout $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ sMSText $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ isActive $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ taskStartDate $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ taskFrequency $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ taskFrequencyUnit $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ isRecurring $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunType $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ dateColumnAlias $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateCalculationMethod $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInYears $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInMonths $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInDays $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInHours $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInMinutes $$HASH_SET$$;
	private $$JAVA_FIELD_TYPE$$ firstRunDateGapInSeconds $$HASH_SET$$;

	public TaskInfoSearch()
	{
	}
	public java.lang.Integer getTaskInfoSearchId()
	{
		return this.taskInfoId;
	}
	public void setTaskInfoSearchId(java.lang.Integer messageQueueId)
	{
		this.taskInfoId = taskInfoId;
	}
		public $$JAVA_FIELD_TYPE$$ getTaskName()
	{
		return this.taskName;
	}
	public void setTaskName($$JAVA_FIELD_TYPE$$ taskName)
	{
		this.taskName = taskName;
	}
	public $$JAVA_FIELD_TYPE$$ getTaskDescription()
	{
		return this.taskDescription;
	}
	public void setTaskDescription($$JAVA_FIELD_TYPE$$ taskDescription)
	{
		this.taskDescription = taskDescription;
	}
	public $$JAVA_FIELD_TYPE$$ getTaskType()
	{
		return this.taskType;
	}
	public void setTaskType($$JAVA_FIELD_TYPE$$ taskType)
	{
		this.taskType = taskType;
	}
	public $$JAVA_FIELD_TYPE$$ getApiName()
	{
		return this.apiName;
	}
	public void setApiName($$JAVA_FIELD_TYPE$$ apiName)
	{
		this.apiName = apiName;
	}
	public $$JAVA_FIELD_TYPE$$ getTargetEntityQuery()
	{
		return this.targetEntityQuery;
	}
	public void setTargetEntityQuery($$JAVA_FIELD_TYPE$$ targetEntityQuery)
	{
		this.targetEntityQuery = targetEntityQuery;
	}
	public $$JAVA_FIELD_TYPE$$ getTargetUserIdColumnAlias()
	{
		return this.targetUserIdColumnAlias;
	}
	public void setTargetUserIdColumnAlias($$JAVA_FIELD_TYPE$$ targetUserIdColumnAlias)
	{
		this.targetUserIdColumnAlias = targetUserIdColumnAlias;
	}
	public $$JAVA_FIELD_TYPE$$ getTargetEntityIdColumnAlias()
	{
		return this.targetEntityIdColumnAlias;
	}
	public void setTargetEntityIdColumnAlias($$JAVA_FIELD_TYPE$$ targetEntityIdColumnAlias)
	{
		this.targetEntityIdColumnAlias = targetEntityIdColumnAlias;
	}
	public $$JAVA_FIELD_TYPE$$ getEnableInAppNotification()
	{
		return this.enableInAppNotification;
	}
	public void setEnableInAppNotification($$JAVA_FIELD_TYPE$$ enableInAppNotification)
	{
		this.enableInAppNotification = enableInAppNotification;
	}
	public $$JAVA_FIELD_TYPE$$ getInAppNotificationLayout()
	{
		return this.inAppNotificationLayout;
	}
	public void setInAppNotificationLayout($$JAVA_FIELD_TYPE$$ inAppNotificationLayout)
	{
		this.inAppNotificationLayout = inAppNotificationLayout;
	}
	public $$JAVA_FIELD_TYPE$$ getEnableEmailNotification()
	{
		return this.enableEmailNotification;
	}
	public void setEnableEmailNotification($$JAVA_FIELD_TYPE$$ enableEmailNotification)
	{
		this.enableEmailNotification = enableEmailNotification;
	}
	public $$JAVA_FIELD_TYPE$$ getEmailColumnAlias()
	{
		return this.emailColumnAlias;
	}
	public void setEmailColumnAlias($$JAVA_FIELD_TYPE$$ emailColumnAlias)
	{
		this.emailColumnAlias = emailColumnAlias;
	}
	public $$JAVA_FIELD_TYPE$$ getEmailNotificationLayout()
	{
		return this.emailNotificationLayout;
	}
	public void setEmailNotificationLayout($$JAVA_FIELD_TYPE$$ emailNotificationLayout)
	{
		this.emailNotificationLayout = emailNotificationLayout;
	}
	public $$JAVA_FIELD_TYPE$$ getEmailSubject()
	{
		return this.emailSubject;
	}
	public void setEmailSubject($$JAVA_FIELD_TYPE$$ emailSubject)
	{
		this.emailSubject = emailSubject;
	}
	public $$JAVA_FIELD_TYPE$$ getEnableSmsNotification()
	{
		return this.enableSmsNotification;
	}
	public void setEnableSmsNotification($$JAVA_FIELD_TYPE$$ enableSmsNotification)
	{
		this.enableSmsNotification = enableSmsNotification;
	}
	public $$JAVA_FIELD_TYPE$$ getSmsColumnAlias()
	{
		return this.smsColumnAlias;
	}
	public void setSmsColumnAlias($$JAVA_FIELD_TYPE$$ smsColumnAlias)
	{
		this.smsColumnAlias = smsColumnAlias;
	}
	public $$JAVA_FIELD_TYPE$$ getSmsNotificationLayout()
	{
		return this.smsNotificationLayout;
	}
	public void setSmsNotificationLayout($$JAVA_FIELD_TYPE$$ smsNotificationLayout)
	{
		this.smsNotificationLayout = smsNotificationLayout;
	}
	public $$JAVA_FIELD_TYPE$$ getSMSText()
	{
		return this.sMSText;
	}
	public void setSMSText($$JAVA_FIELD_TYPE$$ sMSText)
	{
		this.sMSText = sMSText;
	}
	public $$JAVA_FIELD_TYPE$$ getIsActive()
	{
		return this.isActive;
	}
	public void setIsActive($$JAVA_FIELD_TYPE$$ isActive)
	{
		this.isActive = isActive;
	}
	public $$JAVA_FIELD_TYPE$$ getTaskStartDate()
	{
		return this.taskStartDate;
	}
	public void setTaskStartDate($$JAVA_FIELD_TYPE$$ taskStartDate)
	{
		this.taskStartDate = taskStartDate;
	}
	public $$JAVA_FIELD_TYPE$$ getTaskFrequency()
	{
		return this.taskFrequency;
	}
	public void setTaskFrequency($$JAVA_FIELD_TYPE$$ taskFrequency)
	{
		this.taskFrequency = taskFrequency;
	}
	public $$JAVA_FIELD_TYPE$$ getTaskFrequencyUnit()
	{
		return this.taskFrequencyUnit;
	}
	public void setTaskFrequencyUnit($$JAVA_FIELD_TYPE$$ taskFrequencyUnit)
	{
		this.taskFrequencyUnit = taskFrequencyUnit;
	}
	public $$JAVA_FIELD_TYPE$$ getIsRecurring()
	{
		return this.isRecurring;
	}
	public void setIsRecurring($$JAVA_FIELD_TYPE$$ isRecurring)
	{
		this.isRecurring = isRecurring;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunType()
	{
		return this.firstRunType;
	}
	public void setFirstRunType($$JAVA_FIELD_TYPE$$ firstRunType)
	{
		this.firstRunType = firstRunType;
	}
	public $$JAVA_FIELD_TYPE$$ getDateColumnAlias()
	{
		return this.dateColumnAlias;
	}
	public void setDateColumnAlias($$JAVA_FIELD_TYPE$$ dateColumnAlias)
	{
		this.dateColumnAlias = dateColumnAlias;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateCalculationMethod()
	{
		return this.firstRunDateCalculationMethod;
	}
	public void setFirstRunDateCalculationMethod($$JAVA_FIELD_TYPE$$ firstRunDateCalculationMethod)
	{
		this.firstRunDateCalculationMethod = firstRunDateCalculationMethod;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInYears()
	{
		return this.firstRunDateGapInYears;
	}
	public void setFirstRunDateGapInYears($$JAVA_FIELD_TYPE$$ firstRunDateGapInYears)
	{
		this.firstRunDateGapInYears = firstRunDateGapInYears;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInMonths()
	{
		return this.firstRunDateGapInMonths;
	}
	public void setFirstRunDateGapInMonths($$JAVA_FIELD_TYPE$$ firstRunDateGapInMonths)
	{
		this.firstRunDateGapInMonths = firstRunDateGapInMonths;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInDays()
	{
		return this.firstRunDateGapInDays;
	}
	public void setFirstRunDateGapInDays($$JAVA_FIELD_TYPE$$ firstRunDateGapInDays)
	{
		this.firstRunDateGapInDays = firstRunDateGapInDays;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInHours()
	{
		return this.firstRunDateGapInHours;
	}
	public void setFirstRunDateGapInHours($$JAVA_FIELD_TYPE$$ firstRunDateGapInHours)
	{
		this.firstRunDateGapInHours = firstRunDateGapInHours;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInMinutes()
	{
		return this.firstRunDateGapInMinutes;
	}
	public void setFirstRunDateGapInMinutes($$JAVA_FIELD_TYPE$$ firstRunDateGapInMinutes)
	{
		this.firstRunDateGapInMinutes = firstRunDateGapInMinutes;
	}
	public $$JAVA_FIELD_TYPE$$ getFirstRunDateGapInSeconds()
	{
		return this.firstRunDateGapInSeconds;
	}
	public void setFirstRunDateGapInSeconds($$JAVA_FIELD_TYPE$$ firstRunDateGapInSeconds)
	{
		this.firstRunDateGapInSeconds = firstRunDateGapInSeconds;
	}

	private Date vwLastModifiedDate;
	private java.lang.Integer vwLastModifiedTime;
	private java.lang.String vwLastAction;
	private java.lang.String vwModifiedBy;
	private java.lang.String vwTxnRemarks;
	private java.lang.String vwTxnStatus;
	private java.lang.Integer isRequestUnderProcesss;
	private java.lang.Integer legacyRecordId;
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
	}*/
}
