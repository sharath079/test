package com.vw.runtime;
public class VWMessageBean 
{
	private String responseMessage;
	private String remarksMessage;
	public VWMessageBean() 
	{
	}
	public String getResponseMessage() 
	{
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}
	public String getRemarksMessage() 
	{
		return remarksMessage;
	}
	public void setRemarksMessage(String remarksMessage)
	{
		this.remarksMessage = remarksMessage;
	}
	public void resetResponse()
	{
		setResponseMessage("");
	}
	public void resetRemarks()
	{
		setRemarksMessage("");
	}
}
