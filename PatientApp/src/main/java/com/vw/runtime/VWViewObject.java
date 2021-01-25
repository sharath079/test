package com.vw.runtime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class VWViewObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List selectedRows = new ArrayList();
	private HashMap viewMaps = new HashMap();
	private Boolean hasGetListTriggered = false;
	private Boolean isFromChild = false;
	public List getSelectedRows(String selectedEntity)
	{
		return (List) viewMaps.get(selectedEntity);
	}
	public void setSelectedRows(String selectedEntity, List selectedRows)
	{
		this.viewMaps.put(selectedEntity, selectedRows);
	}
	public void clearSelectedRows()
	{
		this.selectedRows.clear();
	}
	public Boolean getHasGetListTriggered()
	{
		return hasGetListTriggered;
	}
	public void setHasGetListTriggered(Boolean hasGetListTriggered)
	{
		this.hasGetListTriggered = hasGetListTriggered;
	}
	public Boolean isFromChild()
	{
		return isFromChild;
	}
	public void isFromChild(Boolean isFromChild)
	{
		this.isFromChild = isFromChild;
	}
}
