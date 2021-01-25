package com.patientapp.bean;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.vw.runtime.RulesBean;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
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
@Table(name = "QueryInfo")
public class QueryInfo
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "queryInfoId")
	private java.lang.Integer queryInfoId;
	@Column(name = "queryName")
	private java.lang.String queryName;
	@Column(name = "queryCode")
	private java.lang.String queryCode;
	@Column(name = "useNativeQuery")
	private java.lang.String useNativeQuery;
	@Column(name = "queryWhereClause")
	private java.lang.String queryWhereClause;
	@Column(name = "groupByClause")
	private java.lang.String groupByClause;
	@Column(name = "orderByClause")
	private java.lang.String orderByClause;
	@Column(name = "limitClause")
	private java.lang.String limitClause;
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
	public QueryInfo()
	{
	}
	public Integer getQueryInfoId()
	{
		return this.queryInfoId;
	}
	public void setQueryInfoId(Integer queryInfoId)
	{
		this.queryInfoId = queryInfoId;
	}
	public String getQueryName()
	{
		return this.queryName;
	}
	public void setQueryName(String queryName)
	{
		this.queryName = queryName;
	}
	public String getQueryCode()
	{
		return this.queryCode;
	}
	public void setQueryCode(String queryCode)
	{
		this.queryCode = queryCode;
	}
	public String getUseNativeQuery()
	{
		return this.useNativeQuery;
	}
	public void setUseNativeQuery(String useNativeQuery)
	{
		this.useNativeQuery = useNativeQuery;
	}
	public String getQueryWhereClause()
	{
		return this.queryWhereClause;
	}
	public void setQueryWhereClause(String queryWhereClause)
	{
		this.queryWhereClause = queryWhereClause;
	}
	public String getGroupByClause()
	{
		return this.groupByClause;
	}
	public void setGroupByClause(String groupByClause)
	{
		this.groupByClause = groupByClause;
	}
	public String getOrderByClause()
	{
		return this.orderByClause;
	}
	public void setOrderByClause(String orderByClause)
	{
		this.orderByClause = orderByClause;
	}
	public String getLimitClause()
	{
		return this.limitClause;
	}
	public void setLimitClause(String limitClause)
	{
		this.limitClause = limitClause;
	}
}
