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
@Table(name = "RequestSent")
public class RequestSent
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "requestSentId")
	private java.lang.Integer requestSentId;
	@Column(name = "referenceRequestId")
	private java.lang.Integer referenceRequestId;
	@Column(name = "requestStatus")
	private java.lang.String requestStatus;
	@Column(name = "targetServiceRequestId")
	private java.lang.Integer targetServiceRequestId;
	@Column(name = "rollBackRequest")
	private java.lang.Integer rollBackRequest;
	@Column(name = "isRequestRolledBack")
	private java.lang.Integer isRequestRolledBack;
	@Column(name = "apiName")
	private java.lang.String apiName;
	@Column(name = "serviceName")
	private java.lang.String serviceName;
	public RequestSent()
	{
	}
	public java.lang.String getServiceName()
	{
		return serviceName;
	}
	public void setServiceName(java.lang.String serviceName)
	{
		this.serviceName = serviceName;
	}
	public String getApiName()
	{
		return this.apiName;
	}
	public void setApiName(String apiName)
	{
		this.apiName = apiName;
	}
	public Integer getRequestSentId()
	{
		return this.requestSentId;
	}
	public void setRequestSentId(Integer requestSentId)
	{
		this.requestSentId = requestSentId;
	}
	public Integer getReferenceRequestId()
	{
		return this.referenceRequestId;
	}
	public void setReferenceRequestId(Integer referenceRequestId)
	{
		this.referenceRequestId = referenceRequestId;
	}
	public String getRequestStatus()
	{
		return this.requestStatus;
	}
	public void setRequestStatus(String requestStatus)
	{
		this.requestStatus = requestStatus;
	}
	public Integer getTargetServiceRequestId()
	{
		return this.targetServiceRequestId;
	}
	public void setTargetServiceRequestId(Integer targetServiceRequestId)
	{
		this.targetServiceRequestId = targetServiceRequestId;
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
}
