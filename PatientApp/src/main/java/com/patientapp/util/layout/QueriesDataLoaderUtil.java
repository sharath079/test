package com.patientapp.util.layout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.patientapp.bean.QueryColumnInfo;
import com.patientapp.bean.QueryInfo;
import com.patientapp.bean.QueryTableInfo;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.VWTCoreCommonUtil;
public class QueriesDataLoaderUtil
{
	public static List<QueryInfo> allQueriesList = new ArrayList<QueryInfo>();
	public static List<QueryTableInfo> allQueryTablesList = new ArrayList<QueryTableInfo>();
	public static List<QueryColumnInfo> allQueryColumnsList = new ArrayList<QueryColumnInfo>();
	public static HashMap queryTablesHashMap = new HashMap();
	public static HashMap queryColumnsHashMap = new HashMap();
	String sQueriesMetaDataFile;
	String sGeneratedQueriesMetaDataFile;
	String sCustomQueriesMetaDataFile;
	public static void main(String[] args)
	{
		try
		{
			QueriesDataLoaderUtil qdl = new QueriesDataLoaderUtil();
			//qdl.processQueries();
			System.out.println("Processing completed !!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public int deleteQueriesInfo(Session organisationSession) throws Exception
	{
		try
		{
			// Query Columns
			int existedQueriesColumnsCount = 0;
			String queriesColumnsCountString = "select count(*) from QueryColumnInfo";
            Query query = organisationSession.createSQLQuery(queriesColumnsCountString);
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
				existedQueriesColumnsCount = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
			}
			String deleteQueriesColumnsString = " delete from QueryColumnInfo ";
			Query deleteColumnQuery = organisationSession.createSQLQuery(deleteQueriesColumnsString);							
			int deletedQueriesColumnsCount = deleteColumnQuery.executeUpdate();
			if (deletedQueriesColumnsCount != existedQueriesColumnsCount)
			{
				System.out.print("Queries Columns could not be deleted");
				return 0;
			}
			// Tables
			int existedTablesCount = 0;
			String tablesCountString = "select count(*) from QueryTableInfo";
		    Query tableCountQuery = organisationSession.createSQLQuery(tablesCountString);
	         resultRowsList = tableCountQuery.list();
	         iterator = resultRowsList.iterator(); 	    
	         singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
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
				existedTablesCount = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
			}
			String deleteTablesString = " delete from QueryTableInfo ";
			Query deleteTablesQuery = organisationSession.createSQLQuery(deleteTablesString);			
			int deletedTablesCount = deleteTablesQuery.executeUpdate();
			if (deletedTablesCount != existedTablesCount)
			{
				System.out.print("Tables could not be deleted");
				return 0;
			}
			// Queries
			int existedQueriesCount = 0;
			String queriesCountString = "select count(*) from QueryInfo";
			query = organisationSession.createSQLQuery(queriesCountString);
 	         resultRowsList = query.list();
	         iterator = resultRowsList.iterator(); 	    
	         singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
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
				existedQueriesCount = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
			}
			String deleteQueriesString = " delete from QueryInfo ";
			query = organisationSession.createSQLQuery(deleteQueriesString);
			int deletedQueriedCount = query.executeUpdate();
			if (deletedQueriedCount != existedQueriesCount)
			{
				System.out.print("Queries could not be deleted");
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
			return 0;
		}
		finally
		{
		}
	}
	public int processQuery(Session organisationSession, QueryInfo queryInfo, List<QueryColumnInfo> queryColumnsList, List<QueryTableInfo> queryTablesList) throws Exception
	{
		try
		{
			int queryInfoId = -1;
			try
			{
				queryInfoId = updateQueryInfo(organisationSession, queryInfo);
				if (queryInfoId <= 0)
				{
					System.out.println(queryInfo.getQueryName() + " query could not be added.");
					return 0;
				}
			}
			catch (Exception e)
			{
				VWTCoreCommonUtil.writeTolog(e);
				return 0;
			}
			if (queryInfoId > 0)
			{
				for (int i = 0; i < queryColumnsList.size(); i++)
				{
					QueryColumnInfo queryColumnInfo = queryColumnsList.get(i);
					try
					{
						int isUpdated = updateQueryColumnInfo(organisationSession, queryInfoId, queryColumnInfo);
						if (isUpdated <= 0)
						{
							System.out.println(" query column " + " (Query = " + queryInfo.getQueryName() + " and Query Column = " + queryColumnInfo.getQueryColumnAlias() + ")" + " could not be added.");
							return 0;
						}
					}
					catch (Exception e)
					{
						VWTCoreCommonUtil.writeTolog(e);
						return 0;
					}
				}
				for (int i = 0; i < queryTablesList.size(); i++)
				{
					QueryTableInfo queryTableInfo = queryTablesList.get(i);
					try
					{
						int isUpdated = updateQueryTableInfo(organisationSession, queryInfoId, queryTableInfo);
						if (isUpdated <= 0)
						{
							System.out.println(" query table " + " (Query = " + queryInfo.getQueryName() + " and Query table = " + queryTableInfo.getQueryTableName() + ")" + " could not be added.");
							return 0;
						}
					}
					catch (Exception e)
					{
						VWTCoreCommonUtil.writeTolog(e);
						return 0;
					}
				}
			}
			return 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static int updateQueryInfo(Session organisationSession, QueryInfo queryInfo) throws Exception
	{
		String queryCode = queryInfo.getQueryCode();
		int queryInfoId = getQueryId(organisationSession, queryCode);
		if (queryInfoId > 0)
		{
			updateQuery(organisationSession, queryInfoId, queryInfo);
		}
		else
		{
			queryInfoId = addQuery(organisationSession, queryInfo);
		}
		return queryInfoId;
	}
	public static int getQueryId(Session organisationSession, String queryCode) throws Exception
	{				
		try
		{
			String selectQuery = "select queryInfoId " + "from QueryInfo where queryCode = ?";
			Query query = organisationSession.createSQLQuery(selectQuery);
			query.setParameter(0, queryCode);
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
				int queryInfoId = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
				return queryInfoId;
			}
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
			return -1;
		}		
		return -1;
	}
	public static int addQuery(Session organisationSession, QueryInfo queryInfo) throws Exception
	{
		int queryInfoId = -1;
		try
		{			
			return (int) organisationSession.save(queryInfo);
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}
		return queryInfoId;
	}
	public static int updateQuery(Session organisationSession, int queryInfoId, QueryInfo queryInfo) throws Exception
	{		
		try
		{
			String queryName = queryInfo.getQueryName();
			String queryWhereClause = queryInfo.getQueryWhereClause();
			String groupByClause = queryInfo.getGroupByClause();
			String orderByClause = queryInfo.getOrderByClause();
			String limitClause = queryInfo.getLimitClause();
			Query query = organisationSession.createSQLQuery("update QueryInfo " + " set " + " queryName = ? , " + " queryWhereClause = ? , " + " groupByClause = ? , " + " orderByClause = ? , " + " limitClause = ? " + " where queryInfoId = ? ");
			int paramPosIndex = 0;
			query.setParameter(paramPosIndex++, queryName);
			query.setParameter(paramPosIndex++, queryWhereClause);
			query.setParameter(paramPosIndex++, groupByClause);
			query.setParameter(paramPosIndex++, orderByClause);
			query.setParameter(paramPosIndex++, limitClause);
			query.setParameter(paramPosIndex++, queryInfoId);
			int updateCount = query.executeUpdate();
			if (updateCount != 1)
			{
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}
		return 0;
	}
	public static int updateQueryColumnInfo(Session organisationSession, int queryInfoId, QueryColumnInfo queryColumnInfo) throws Exception
	{
		String queryColumnAlias = queryColumnInfo.getQueryColumnAlias();
		int queryColumnInfoId = getQueryColumnId(organisationSession, queryInfoId, queryColumnAlias);
		if (queryColumnInfoId > 0)
		{
			int isUpdated = updateQueryColumn(organisationSession, queryColumnInfoId, queryColumnInfo);
			return isUpdated;
		}
		else
		{
			/*
			 * addQueryColumn returns 1 if success
			 */
			queryColumnInfoId = addQueryColumn(organisationSession, queryInfoId, queryColumnInfo);
		}
		return queryColumnInfoId;
	}
	public static int getQueryColumnId(Session organisationSession, int queryInfoId, String queryColumnAlias) throws Exception
	{
		try
		{
			String selectQuery = "select queryColumnInfoId " + " from QueryColumnInfo " + " where queryInfoId = ? " + " and queryColumnAlias = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);					
			int paramPosIndex = 0;
			query.setParameter(paramPosIndex++, queryInfoId);
			query.setParameter(paramPosIndex++, queryColumnAlias);
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
				int queryColumnInfoId = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
				return queryColumnInfoId;
			}
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
			return -1;
		}
		return -1;
	}
	public static int addQueryColumn(Session organisationSession, int queryInfoId, QueryColumnInfo queryColumnInfo) throws Exception
	{		
		try
		{			
			queryColumnInfo.setQueryInfoId(queryInfoId);
			int insertCount = (int) organisationSession.save(queryColumnInfo);
			if (insertCount <= 0)
			{
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}
		return 0;
	}
	public static int updateQueryColumn(Session organisationSession, int queryColumnInfoId, QueryColumnInfo queryColumnInfo) throws Exception
	{		
		try
		{
			String queryColumnText = queryColumnInfo.getQueryColumnText();
			String updateQueryText = "update QueryColumnInfo " + " set " + " queryColumnText = ? " + " where queryColumnInfoId = ? ";
			Query query = organisationSession.createSQLQuery(updateQueryText);
			int paramPosIndex = 0;
			query.setParameter(paramPosIndex++, queryColumnText);
			query.setParameter(paramPosIndex++, queryColumnInfoId);
			int updateCount = query.executeUpdate();
			if (updateCount != 1)
			{
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}		
		return 0;
	}
	public static int updateQueryTableInfo(Session organisationSession, int queryInfoId, QueryTableInfo queryTableInfo) throws Exception
	{
		String queryTableAlias = queryTableInfo.getQueryTableAlias();
		int queryTableInfoId = getQueryTableId(organisationSession, queryInfoId, queryTableAlias);
		if (queryTableInfoId > 0)
		{
			int isUpdated = updateQueryTable(organisationSession, queryTableInfoId, queryTableInfo);
			return isUpdated;
		}
		else
		{
			/*
			 * addQueryTable returns 1 if success
			 */
			queryTableInfoId = addQueryTable(organisationSession, queryInfoId, queryTableInfo);
		}
		return queryTableInfoId;
	}
	public static int getQueryTableId(Session organisationSession, int queryInfoId, String queryTableAlias) throws Exception
	{		
		try
		{
			String selectQuery = "select queryTableInfoId " + "from QueryTableInfo " + " where queryInfoId = ? " + " and queryTableAlias = ? ";
			Query query = organisationSession.createSQLQuery(selectQuery);			
			int paramPosIndex = 0;
			query.setParameter(paramPosIndex++, queryInfoId);
			query.setParameter(paramPosIndex++, queryTableAlias);
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
				int queryTableInfoId = Integer.parseInt((String.valueOf(resultRowColumnsList[0])));
				return queryTableInfoId;
			}
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
			return -1;
		}
		return -1;
	}
	public static int addQueryTable(Session organisationSession, int queryInfoId, QueryTableInfo queryTableInfo) throws Exception
	{		
		try
		{		
			queryTableInfo.setQueryInfoId(queryInfoId);
			int insertCount =  (int) organisationSession.save(queryTableInfo);
			if (insertCount <= 0)
			{
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}		
		return 0;
	}
	public static int updateQueryTable(Session organisationSession, int queryTableInfoId, QueryTableInfo queryTableInfo) throws Exception
	{		
		try
		{
			String queryTableName = queryTableInfo.getQueryTableName();
			String updateQueryText = "update QueryTableInfo " + " set " + " queryTableName = ? " + " where queryTableInfoId = ? ";
			Query query = organisationSession.createSQLQuery(updateQueryText);
			int paramPosIndex = 0;
			query.setParameter(paramPosIndex++, queryTableName);
			query.setParameter(paramPosIndex++, queryTableInfoId);
			int updateCount = query.executeUpdate();
			if (updateCount != 1)
			{
				return 0;
			}
			return 1;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(e);
		}
		finally
		{			
		}
		return 0;
	}
}
