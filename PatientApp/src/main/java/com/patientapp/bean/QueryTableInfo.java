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
@Table(name = "QueryTableInfo")
public class QueryTableInfo
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "queryTableInfoId")
	private java.lang.Integer queryTableInfoId;
	@Column(name = "queryInfoId")
	private java.lang.Integer queryInfoId;
	public Integer getQueryInfoId()
	{
		return this.queryInfoId;
	}
	public void setQueryInfoId(Integer queryInfoId)
	{
		this.queryInfoId = queryInfoId;
	}
	@Column(name = "queryTableName")
	private java.lang.String queryTableName;
	@Column(name = "queryTableAlias")
	private java.lang.String queryTableAlias;
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
	public QueryTableInfo()
	{
	}
	public Integer getQueryTableInfoId()
	{
		return this.queryTableInfoId;
	}
	public void setQueryTableInfoId(Integer queryTableInfoId)
	{
		this.queryTableInfoId = queryTableInfoId;
	}
	public String getQueryTableName()
	{
		return this.queryTableName;
	}
	public void setQueryTableName(String queryTableName)
	{
		this.queryTableName = queryTableName;
	}
	public String getQueryTableAlias()
	{
		return this.queryTableAlias;
	}
	public void setQueryTableAlias(String queryTableAlias)
	{
		this.queryTableAlias = queryTableAlias;
	}
}
