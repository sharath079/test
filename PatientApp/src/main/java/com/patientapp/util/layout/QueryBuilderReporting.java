package com.patientapp.util.layout;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.VWTCoreCommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author 
 */
public class QueryBuilderReporting
{
    static Logger logger = Logger.getLogger(QueryBuilderReporting.class);
    public static JsonObject getQueryInfoNew(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        int queryId = -1;
        if(paramsMap.containsKey("queryId"))
        {
            queryId = Integer.parseInt(paramsMap.get("queryId"));
        }
        else
        {
            String queryCode = paramsMap.get("queryCode");
            String selectQueryIdString = "select queryInfoId from QueryInfo where queryCode = ? ";
            int matchingQueryId = VWTCoreCommonUtil.getMatchingEntityId(organisationSession, selectQueryIdString, queryCode);
            if(matchingQueryId<=0)
            {
                JsonObject dataObject = new JsonObject();
                dataObject.addProperty("alert", "Matching query information could not be retrieved.");
                dataObject.addProperty("success", 0);
                return dataObject;
            }
            queryId = matchingQueryId;
        }
        return getQueryInfoNew(organisationSession, queryId);
    }
    public static JsonObject getQueryInfoNew(Session organisationSession, int queryId) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            String selectQuery = "	SELECT  "
                    + "		queryInfoId, "
                    + "		queryName, "
                    + "		queryCode, "
                    + "		groupByClause, "
                    + "		orderByClause, "
                    + "		limitClause, "                    
                    + "		queryWhereClause "
                    + "	FROM "
                    + "		QueryInfo "
                    + "	WHERE queryInfoId = ? ";
            Query query = organisationSession.createSQLQuery(selectQuery);
            query.setParameter(0, queryId);            
            JsonObject queryInfo = new JsonObject();
            String querySelectColumnsText = "";
            String queryTablesListText = "";
            String queryWhereClause = "";
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         if(iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   
                queryInfo.addProperty("queryId", String.valueOf(resultRowColumnsList[0]));
                queryInfo.addProperty("queryName", String.valueOf(resultRowColumnsList[1]));
                queryInfo.addProperty("queryCode", String.valueOf(resultRowColumnsList[2]));
                queryInfo.addProperty("groupByClause", String.valueOf(resultRowColumnsList[3]));                
                queryInfo.addProperty("orderByClause", String.valueOf(resultRowColumnsList[4]));                
                queryInfo.addProperty("limitClause", String.valueOf(resultRowColumnsList[5]));                
                queryInfo.addProperty("whereClauseParameterSerialNos", "");
                queryWhereClause = String.valueOf(resultRowColumnsList[6]);
                queryInfo.addProperty("queryWhereClause", queryWhereClause);
                queryInfo.addProperty("queryOrderByGroupByString", String.valueOf(resultRowColumnsList[3]) +  " " + String.valueOf(resultRowColumnsList[4]));
            }
            JsonObject queryTablesListObj = getQueryTablesListNew(organisationSession, queryId);
            if (!(queryTablesListObj.has("success") && queryTablesListObj.get("success").getAsInt() == 1))
            {
                return queryTablesListObj;
            }
            JsonArray queryTablesList = queryTablesListObj.get("queryTablesList").getAsJsonArray();
            queryInfo.add("queryTablesList", queryTablesList);
            JsonObject queryColumnsListObj = getQueryColumnsListNew(organisationSession, queryId);
            if (!(queryColumnsListObj.has("success") && queryColumnsListObj.get("success").getAsInt() == 1))
            {
                return queryColumnsListObj;
            }
            JsonArray queryColumnsList = queryColumnsListObj.get("queryColumnsList").getAsJsonArray();
            queryInfo.add("queryColumnsList", queryColumnsList);
            for(int i=0; i<queryColumnsList.size(); i++)
            {
                JsonObject queryColumnInfo = queryColumnsList.get(i).getAsJsonObject();
                String queryColumnText = queryColumnInfo.get("columnDBName").getAsString();
                String queryColumnAlias = queryColumnInfo.get("columnAlias").getAsString();
                querySelectColumnsText += queryColumnText + " as " + queryColumnAlias + " ";
                if(i<queryColumnsList.size()-1)
                {
                    querySelectColumnsText += ", ";
                }
            }
            for(int i=0; i<queryTablesList.size(); i++)
            {
                JsonObject queryTableInfo = queryTablesList.get(i).getAsJsonObject();
                String queryTableName = queryTableInfo.get("tableDBName").getAsString();
                String queryTableAlias = queryTableInfo.get("tableName").getAsString();
                queryTablesListText += queryTableName + " " + queryTableAlias + " ";
                if(i<queryTablesList.size()-1)
                {
                    queryTablesListText += ", ";
                }
            }
            String queryString = "select " + querySelectColumnsText + " from " + queryTablesListText + queryWhereClause;
            String queryStringWithoutWhereClause = "select " + querySelectColumnsText + " from " + queryTablesListText;
            String countQueryString = "select count(*)  from " + queryTablesListText + queryWhereClause;
            queryInfo.addProperty("countQueryString", countQueryString);
            queryInfo.addProperty("queryString", queryString);
            queryInfo.addProperty("queryStringWithoutWhereClause", queryStringWithoutWhereClause);            
            queryInfo.add("queryCustomColumnsList", new JsonArray());
            queryInfo.add("queryBuilderParamsList", new JsonArray());
            queryInfo.add("queryTablesColumnsList", new JsonArray());
            dataObject.add("queryInfo", queryInfo);
            dataObject.addProperty("success", 1);
            return dataObject;
        }
        catch (Exception e)
        {
			VWTCoreCommonUtil.writeTolog(QueryBuilderReporting.class, e);	
            logger.error(VWTCoreCommonUtil.getStackTrace(e));
            logger.debug("Query info could not be retrieved!!");
            dataObject.addProperty("alert", "Query info could not be retrieved");
            dataObject.addProperty("success", 0);
            return dataObject;
        }
        finally
        {            
        }
    }
    public static JsonObject getQueryTablesListNew(Session organisationSession, int queryId) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            String selectQuery = "	SELECT  "
                    + " queryTableInfoId, "
                    + " queryTableName,"
                    + " queryInfoId,"
                    + " queryTableAlias " 
                    + "	FROM "
                    + " QueryTableInfo qtl " 
                    + "	WHERE "
                    + " qtl.queryInfoId = ? " 
                    + "	order by qtl.queryTableInfoId ";
            Query query = organisationSession.createSQLQuery(selectQuery);
            query.setParameter(0, queryId);            
            JsonArray queryTablesList = new JsonArray();
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         while (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   
                JsonObject columnInfo = new JsonObject();
                columnInfo.addProperty("tableId", String.valueOf(resultRowColumnsList[0]));
                columnInfo.addProperty("tableDBName", String.valueOf(resultRowColumnsList[1]));
                columnInfo.addProperty("queryId", String.valueOf(resultRowColumnsList[2]));                
                columnInfo.addProperty("tableName", String.valueOf(resultRowColumnsList[3]));
                queryTablesList.add(columnInfo);
            }
            dataObject.add("queryTablesList", queryTablesList);
            dataObject.addProperty("success", 1);
            return dataObject;
        }
        catch (Exception e)
        {
			VWTCoreCommonUtil.writeTolog(QueryBuilderReporting.class, e);	
            logger.error(VWTCoreCommonUtil.getStackTrace(e));
            logger.debug("Query tables list could not be retrieved!!");
            dataObject.addProperty("alert", "Query tables list could not be retrieved");
            dataObject.addProperty("success", 0);
            return dataObject;
        }
        finally
        {
        }
    }
    public static String getQueryColumnStringFromAliasNew(Session organisationSession, int queryId, String columnAlias) throws Exception
    {
        try
        {
            String selectQuery = "	SELECT  "
                    + "    queryColumnText "
                    + "	FROM "
                    + "		QueryColumnInfo qcl "
                    + "	WHERE "
                    + " qcl.queryInfoId = ? "
                    + " and qcl.queryColumnAlias = ? ";
            Query query = organisationSession.createSQLQuery(selectQuery);
			int paramsPosIndex = 0;
			query.setParameter(paramsPosIndex++, queryId);
			query.setParameter(paramsPosIndex++, columnAlias);
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         if (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   			
				String queryColumnText = String.valueOf(resultRowColumnsList[0]);
				return queryColumnText;
			}
			else
			{
				return "";
			}
        }
        catch (Exception e)
        {
			VWTCoreCommonUtil.writeTolog(QueryBuilderReporting.class, e);	
            logger.error(VWTCoreCommonUtil.getStackTrace(e));
            logger.debug("Query table columns list could not be retrieved!!");
        }
        finally
        {            
        }
        return null;
    }
    public static JsonObject getQueryColumnsListNew(Session organisationSession, int queryId) throws Exception
    {
        JsonObject dataObject = new JsonObject();        
        try
        {
            String selectQuery = "	SELECT  "
                    + "		qcl.queryInfoId, "
                    + "		qcl.queryColumnInfoId, "
                    + "		qcl.queryColumnAlias, "                    
                    + "		qcl.queryColumnText "
                    + "	FROM "
                    + "		QueryColumnInfo qcl "
                    + "	WHERE qcl.queryInfoId = ? ";
            Query query = organisationSession.createSQLQuery(selectQuery);
            query.setParameter(0, queryId);
            JsonArray queryColumnsList =  new JsonArray();
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         while (iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   
                JsonObject columnInfo = new JsonObject();
                columnInfo.addProperty("queryId", String.valueOf(resultRowColumnsList[0]));
                columnInfo.addProperty("tableId", -1);
                columnInfo.addProperty("columnId", String.valueOf(resultRowColumnsList[1]));
                columnInfo.addProperty("orderBy", 0);
                columnInfo.addProperty("groupBy", 0);
                columnInfo.addProperty("tableName", "");
                columnInfo.addProperty("columnLabel", String.valueOf(resultRowColumnsList[2]));
                columnInfo.addProperty("tableDBName", "");
                columnInfo.addProperty("columnDBName", String.valueOf(resultRowColumnsList[3]));
                columnInfo.addProperty("tableColumnType", "");
                columnInfo.addProperty("columnAlias", String.valueOf(resultRowColumnsList[2]));
                queryColumnsList.add(columnInfo);
            }
            dataObject.add("queryColumnsList", queryColumnsList);
            dataObject.addProperty("success", 1);
            return dataObject;
        }
        catch (Exception e)
        {
			VWTCoreCommonUtil.writeTolog(QueryBuilderReporting.class, e);	
            logger.error(VWTCoreCommonUtil.getStackTrace(e));
            logger.debug("Query table columns list could not be retrieved!!");
            dataObject.addProperty("alert", "Query table columns list could not be retrieved");
            dataObject.addProperty("success", 0);
            return dataObject;
        }
        finally
        {            
        }
    }
  }
