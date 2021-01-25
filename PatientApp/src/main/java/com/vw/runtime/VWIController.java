package com.vw.runtime;
import java.util.HashMap;
import java.util.List;
public interface VWIController 
{
	public abstract void create() throws Exception;
	public abstract void update() throws Exception;
	public abstract void delete() throws Exception;
	public abstract void print() throws Exception;
	public Object getFieldValue(Object entityBean,String sFieldName);
	public String getLabel(String sFieldName);
	public List retrieveUniqueTransactionAbstract(String sEntityName,HashMap hashParams);
}
