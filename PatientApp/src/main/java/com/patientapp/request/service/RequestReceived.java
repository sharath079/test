package com.patientapp.request.service;
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
@Table(name = "RequestReceived")
public class RequestReceived
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "requestReceivedId")
	private java.lang.Integer requestReceivedId;
	@Column(name = "currentRequestStatus")
	private java.lang.String currentRequestStatus;
	@Column(name = "sentRequestsStatus")
	private java.lang.String sentRequestsStatus;
	@Column(name = "requestType")
	private java.lang.String requestType;
	@Column(name = "apiName")
	private java.lang.String apiName;
	@Column(name = "rollbackAPIName")
	private java.lang.String rollbackAPIName;
	@Column(name = "serviceName")
	private java.lang.String serviceName;
	@Column(name = "paramsInfo")
	private java.lang.String paramsInfo;
	@Column(name = "referenceRequestId")
	private java.lang.Integer referenceRequestId;
	@Column(name = "rollBackRequest")
	private java.lang.Integer rollBackRequest;
	@Column(name = "isRequestRolledBack")
	private java.lang.Integer isRequestRolledBack;
	@Column(name = "areSentRequestsRolledBack")
	private java.lang.Integer areSentRequestsRolledBack;
	@Column(name = "transactionReferenceId")
	private java.lang.Integer transactionReferenceId;
	public RequestReceived()
	{
	}
	public java.lang.Integer getAreSentRequestsRolledBack()
	{
		return areSentRequestsRolledBack;
	}
	public void setAreSentRequestsRolledBack(java.lang.Integer areSentRequestsRolledBack)
	{
		this.areSentRequestsRolledBack = areSentRequestsRolledBack;
	}
	public Integer getRequestReceivedId()
	{
		return this.requestReceivedId;
	}
	public void setRequestReceivedId(Integer requestReceivedId)
	{
		this.requestReceivedId = requestReceivedId;
	}
	public java.lang.String getCurrentRequestStatus()
	{
		return currentRequestStatus;
	}
	public void setCurrentRequestStatus(java.lang.String currentRequestStatus)
	{
		this.currentRequestStatus = currentRequestStatus;
	}
	public java.lang.String getSentRequestsStatus()
	{
		return sentRequestsStatus;
	}
	public void setSentRequestsStatus(java.lang.String sentRequestsStatus)
	{
		this.sentRequestsStatus = sentRequestsStatus;
	}
	public java.lang.String getServiceName()
	{
		return serviceName;
	}
	public void setServiceName(java.lang.String serviceName)
	{
		this.serviceName = serviceName;
	}
	public String getRequestType()
	{
		return this.requestType;
	}
	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
	}
	public String getApiName()
	{
		return this.apiName;
	}
	public void setApiName(String apiName)
	{
		this.apiName = apiName;
	}
	public String getParamsInfo()
	{
		return this.paramsInfo;
	}
	public void setParamsInfo(String paramsInfo)
	{
		this.paramsInfo = paramsInfo;
	}
	public Integer getReferenceRequestId()
	{
		return this.referenceRequestId;
	}
	public void setReferenceRequestId(Integer referenceRequestId)
	{
		this.referenceRequestId = referenceRequestId;
	}
	public Integer getRollBackRequest()
	{
		return this.rollBackRequest;
	}
	public void setRollBackRequest(Integer rollBackRequest)
	{
		this.rollBackRequest = rollBackRequest;
	}
	public Integer getIsRequestRolledBack()
	{
		return this.isRequestRolledBack;
	}
	public void setIsRequestRolledBack(Integer isRequestRolledBack)
	{
		this.isRequestRolledBack = isRequestRolledBack;
	}
	public Integer getTransactionReferenceId()
	{
		return transactionReferenceId;
	}
	public void setTransactionReferenceId(Integer transactionReferenceId)
	{
		this.transactionReferenceId = transactionReferenceId;
	}
	public java.lang.String getRollbackAPIName()
	{
		return rollbackAPIName;
	}
	public void setRollbackAPIName(java.lang.String rollbackAPIName)
	{
		this.rollbackAPIName = rollbackAPIName;
	}
}
