package com.vw.runtime.exception;
import com.vw.runtime.VWCoreResponseBean;
/**
 * Core java Exception
 * 
 * @author dandumu
 *
 */
public class VWException extends Exception
{
	private String message = null;
	public VWException()
	{
		super();
		VWCoreResponseBean vwResponseBean = new VWCoreResponseBean();
		vwResponseBean.setShowResponse(true);
	}
	public VWException(String message)
	{
		super(message);
		this.message = message;
	}
	public VWException(Throwable cause)
	{
		super(cause);
	}
	@Override
	public String toString()
	{
		return message;
	}
	@Override
	public String getMessage()
	{
		return message;
	}
}
