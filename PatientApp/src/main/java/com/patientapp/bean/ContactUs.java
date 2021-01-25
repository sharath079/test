/**
 * 
 */
package com.patientapp.bean;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.patientapp.util.CommonUtil;
import com.vw.runtime.RulesBean;
import com.google.gson.JsonObject;
/**
 * @author admin
 *
 */
@Entity
@Table(name = "ContactUs")
public class ContactUs extends RulesBean implements java.io.Serializable{
	@Id
	@GeneratedValue
	@Column(name = "contactId")
	private java.lang.Integer contactId;
	@Column(name="fullName")
	private java.lang.String fullName;
	@Column(name = "emailId")
	private java.lang.String emailId;
	@Column(name = "contactNo")
	private java.lang.Integer contactNo;
	//@Column(name = "message")
	//private java.lang.String message;
	
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
	
	public ContactUs() {
	
	}
	public java.lang.Integer getContactId() {
		return contactId;
	}
	public void setContactId(java.lang.Integer contactId) {
		this.contactId = contactId;
	}
	public java.lang.String getFullName() {
		return fullName;
	}
	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}
	public java.lang.String getEmailId() {
		return emailId;
	}
	public void setEmailId(java.lang.String emailId) {
		this.emailId = emailId;
	}
	public Integer getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(java.lang.Integer contactNo) {
		this.contactNo = contactNo;
	}
//	public java.lang.String getMessage() {
//		return message;
//	}
//	public void setMessage(java.lang.String message) {
//		this.message = message;
//	}
	public Date getVwLastModifiedDate() {
		return vwLastModifiedDate;
	}
	public void setVwLastModifiedDate(Date vwLastModifiedDate) {
		this.vwLastModifiedDate = vwLastModifiedDate;
	}
	public java.lang.Integer getVwLastModifiedTime() {
		return vwLastModifiedTime;
	}
	public void setVwLastModifiedTime(java.lang.Integer vwLastModifiedTime) {
		this.vwLastModifiedTime = vwLastModifiedTime;
	}
	public java.lang.String getVwLastAction() {
		return vwLastAction;
	}
	public void setVwLastAction(java.lang.String vwLastAction) {
		this.vwLastAction = vwLastAction;
	}
	public java.lang.String getVwModifiedBy() {
		return vwModifiedBy;
	}
	public void setVwModifiedBy(java.lang.String vwModifiedBy) {
		this.vwModifiedBy = vwModifiedBy;
	}
	public java.lang.String getVwTxnRemarks() {
		return vwTxnRemarks;
	}
	public void setVwTxnRemarks(java.lang.String vwTxnRemarks) {
		this.vwTxnRemarks = vwTxnRemarks;
	}
	public java.lang.String getVwTxnStatus() {
		return vwTxnStatus;
	}
	public void setVwTxnStatus(java.lang.String vwTxnStatus) {
		this.vwTxnStatus = vwTxnStatus;
	}
	public java.lang.Integer getIsRequestUnderProcesss() {
		return isRequestUnderProcesss;
	}
	public void setIsRequestUnderProcesss(java.lang.Integer isRequestUnderProcesss) {
		this.isRequestUnderProcesss = isRequestUnderProcesss;
	}
	public java.lang.Integer getLegacyRecordId() {
		return legacyRecordId;
	}
	public void setLegacyRecordId(java.lang.Integer legacyRecordId) {
		this.legacyRecordId = legacyRecordId;
	}
	public java.lang.Integer getVwCreatedBy() {
		return vwCreatedBy;
	}
	public void setVwCreatedBy(java.lang.Integer vwCreatedBy) {
		this.vwCreatedBy = vwCreatedBy;
	}
	public Date getVwCreationDate() {
		return vwCreationDate;
	}
	public void setVwCreationDate(Date vwCreationDate) {
		this.vwCreationDate = vwCreationDate;
	}
	
	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("contactId", contactId);
				
		if(fullName!=null)
		{
			dataObject.addProperty("fullName", fullName);
		}
		else
		{
			dataObject.addProperty("fullName", "");
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
				
		if(fullName!=null)
		{
			displayText += fullName;
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
				Method instanceMethod = ContactUs.class.getMethod("get" + popertyNameEL);
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
