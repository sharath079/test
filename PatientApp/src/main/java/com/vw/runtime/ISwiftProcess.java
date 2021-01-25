package com.vw.runtime;
import java.util.List;
public interface ISwiftProcess
{
	abstract List<?> validateEntity(String sFileContent) throws Exception;
	abstract Integer createEntity(String sFileContent) throws Exception;
	abstract String getEntityName();
	abstract String getLcNo();
	abstract String getEntityLabel();
	abstract void StatusUpdate(Integer iEntityId,String sMsgStatus) throws Exception;
	abstract String getSwiftBlock4(Integer iEntityId);
	abstract Boolean finalizeTransaction();
}
