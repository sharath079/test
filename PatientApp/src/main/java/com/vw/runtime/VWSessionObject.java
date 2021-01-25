package com.vw.runtime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
public class VWSessionObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	public HashMap<String, String> VWEntitiesRoles = new HashMap<String, String>();
	public HashMap<String, String> params = new HashMap<String, String>();
	public HashMap<String, Object> paramObject = new HashMap<String, Object>();
	public Integer employeeId;
	public Boolean amIRajanikanth=false;
	public BigDecimal authLowerLimit = new BigDecimal(0.00);
	public BigDecimal authUpperLimit = new BigDecimal(0.00);
	public Boolean isAuthoriser;
	public Boolean isUserLoggedIn=false;
	public String loggedInUser="";
	public String loggedInBranch="";
	public String remarkMessage;
	public String chatTextArea;
	public String chatQuestion;
	private String sequence = "";
	private String guidedPath = "";
	public String getSequence()
	{
		return sequence;
	}
	public void setSequence(String sequence)
	{
		this.sequence = sequence;
	}
	public String getGuidedPath()
	{
		return guidedPath;
	}
	public void setGuidedPath(String guidedPath)
	{
		this.guidedPath = guidedPath;
	}
	public String getChatQuestion()
	{
		return chatQuestion;
	}
	public void setChatQuestion(String chatQuestion)
	{
		this.chatQuestion = chatQuestion;
	}
	public String getChatTextArea()
	{
		return chatTextArea;
	}
	public void setChatTextArea(String chatTextArea)
	{
		this.chatTextArea = chatTextArea;
	}
	public String getRemarkMessage()
	{
		return remarkMessage;
	}
	public void setRemarkMessage(String remarkMessage)
	{
		this.remarkMessage = remarkMessage;
	}
	public String getLoggedInUser()
	{
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser)
	{
		this.loggedInUser = loggedInUser;
	}
	public Boolean getIsUserLoggedIn()
	{
		return isUserLoggedIn;
	}
	public HashMap<String, String> getVWEntitiesRoles()
	{
		return VWEntitiesRoles;
	}
	public void setIsUserLoggedIn(Boolean isUserLoggedIn) 
	{
		this.isUserLoggedIn = isUserLoggedIn;
	}
	public Boolean getAmIRajanikanth()
	{
		return amIRajanikanth;
	}
	public void setAmIRajanikanth(Boolean amIRajanikanth)
	{
		this.amIRajanikanth = amIRajanikanth;
	}
	public void addEntitiesRoles(String sEntityName,String sActions)
	{
		VWEntitiesRoles.put(sEntityName, sActions);
	}
	public void clearEntitiesRoles()
	{
		VWEntitiesRoles.clear();
	}
	public String getAllowedActions(String sEntityBeanName)
	{
		String allAllowedActions = VWEntitiesRoles.get(sEntityBeanName);
		return allAllowedActions;
	}		
	public void setParamValue(String sKey,String sValue)
	{
		params.put(sKey, sValue);
	}
	public String getParamValue(String sKey)
	{
		return params.get(sKey);
	}
	public Object getParamObject(String sKey)
	{
		return paramObject.get(sKey);
	}
	public void setParamObject(String sKey,Object obj)
	{
		paramObject.put(sKey, obj);
	}
	public String getAllowedActions()
	{
		return VWEntitiesRoles.toString();
	}		
	public BigDecimal getAuthLowerLimit()
	{
		return authLowerLimit;
	}
	public void setAuthLowerLimit(BigDecimal authLowerLimit)
	{
		this.authLowerLimit = authLowerLimit;
	}
	public BigDecimal getAuthUpperLimit()
	{
		return authUpperLimit;
	}
	public void setAuthUpperLimit(BigDecimal authUpperLimit)
	{
		this.authUpperLimit = authUpperLimit;
	}
	public Boolean getIsAuthoriser()
	{
		return isAuthoriser;
	}
	public void setIsAuthoriser(Boolean isAuthoriser)
	{
		this.isAuthoriser = isAuthoriser;
	}
	public String getLoggedInBranch()
	{
		return loggedInBranch;
	}
	public void setLoggedInBranch(String loggedInBranch)
	{
		this.loggedInBranch = loggedInBranch;
	}
}
