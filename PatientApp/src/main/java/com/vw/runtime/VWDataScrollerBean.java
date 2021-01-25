package com.vw.runtime;
import java.io.Serializable;
import java.util.HashMap;
public class VWDataScrollerBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	public HashMap<String, Integer> vwDataScrollerMap = new HashMap<String, Integer>();
	private int maxRows=5;
	public int getMaxRows()
	{
		return maxRows;
	}
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}
	public int getBeginIndex(String sEntityName)
	{
		Integer beginIndex	= vwDataScrollerMap.get(sEntityName);	
		if(beginIndex==null)
		{
			beginIndex=0;
		}
		return (int)beginIndex;
	}
	public void setBeginIndex(String sEntityName , int beginIndex)
	{
		vwDataScrollerMap.put(sEntityName,new Integer(beginIndex));
	}
	public void nextPage(String sEntityName)
	{
		this.setBeginIndex(sEntityName, this.getBeginIndex(sEntityName) + getMaxRows());
	}
	public void prevPage(String sEntityName)
	{
		this.setBeginIndex(sEntityName, this.getBeginIndex(sEntityName) - getMaxRows());
	}
}
