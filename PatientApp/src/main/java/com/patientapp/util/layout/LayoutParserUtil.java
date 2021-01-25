package com.patientapp.util.layout;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.layout.LayoutParser;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.VWTCoreCommonUtil;
import com.patientapp.request.util.ReportRequestHandler;
public class LayoutParserUtil
{
	private static final Logger logger = Logger.getLogger(LayoutParserUtil.class);
	public static JsonObject getHTMLTagHTML(Session organisationSession, String layoutName, JsonObject elementInfoObj,
			JsonArray pageQueriesList, JsonArray dataFieldsList, JsonArray tablesList, JsonArray graphsList,
			JsonArray searchFormsList, JsonArray searchFieldsList, JsonArray URLParametersList,
			JsonArray layoutQueriesList, JsonObject outputdataInfoObj, JsonObject dataFieldsTokensObj)
	{
		int htmlWithData = 0;
		if(outputdataInfoObj.has("fetchData"))
		{
			htmlWithData = outputdataInfoObj.get("fetchData").getAsInt(); 
		}
		JsonObject dataObject =  new JsonObject();
		JsonArray htmlTagChildElementsList = elementInfoObj.get("htmlTagChildElementsList").getAsJsonArray();
		JsonArray htmlTagCSSPropertysList = elementInfoObj.get("htmlTagCSSPropertysList").getAsJsonArray();
		JsonObject elementPropertiesInfo = elementInfoObj.get("elementPropertiesInfo").getAsJsonObject();
		String htmlTagName = elementPropertiesInfo.get("htmlTagName").getAsString();
		String htmlTagType = elementPropertiesInfo.get("htmlTagType").getAsString();
		String htmlTagId = elementPropertiesInfo.get("htmlTagId").getAsString();
		String componentType = elementPropertiesInfo.get("componentType").getAsString();
		String htmlTagChildElementsHTML = "";
		String dataNameAttribute = " data-name=\"" + htmlTagName + "\"";
		if(htmlTagName.trim().length()==0)
		{
			dataNameAttribute = "";
		}
		String htmlTagCSSPropertiesText = getHTMLTagCSSPropertiesText(htmlTagCSSPropertysList);		
		String htmlTagTypeInLowerCase = htmlTagType.toLowerCase();
		String htmlTagHTML =  "";				
		String compoentTypeDecodedText = "";
		if(componentType.equalsIgnoreCase(""))//
		{			
			String layoutQueriesListText = "";		
			String layoutQueriesListDataAttributeText = "";
			if(htmlTagType.equalsIgnoreCase("body"))
			{
				GsonBuilder builder = new GsonBuilder();
				builder.disableHtmlEscaping();
				Gson gson = builder.create();
				layoutQueriesListText = gson.toJson(layoutQueriesList);
				layoutQueriesListText = layoutQueriesListText.replaceAll("\"", "'");
				layoutQueriesListDataAttributeText = " data-layout-queries-list=\"" + layoutQueriesListText + "\"";
			}			
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 			
			String htmlTagAttibutesText = gethtmlElementAttibutesText(organisationSession, elementInfoObj, htmlWithData, dataFieldsTokensObj, layoutName, URLParametersList, pageQueriesList);
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase + " "										
					+ dataNameAttribute + " "
					+ layoutQueriesListDataAttributeText + " "
					+ compoentTypeDecodedText + " "
					+ htmlTagAttibutesText + " " 
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
			if(htmlTagType.equalsIgnoreCase("text"))
			{
				String textTagText = "";
				if(elementPropertiesInfo.has("textTagText"))
				{
					textTagText = elementPropertiesInfo.get("textTagText").getAsString();
				}				
				htmlTagHTML = textTagText;
			}
		}
		if(componentType.equalsIgnoreCase("DATA_FIELD"))
		{
			compoentTypeDecodedText = " data-is-data-field=\"1\"";
			int overrideWhereClause = elementPropertiesInfo.get("overrideWhereClause").getAsInt();
			int dataFieldBoundToTable = elementPropertiesInfo.get("dataFieldBoundToTable").getAsInt();
			String searchFormName = elementPropertiesInfo.get("searchFormName").getAsString();
			String queryCode = elementPropertiesInfo.get("queryCode").getAsString();
			String queryColumnAlias = elementPropertiesInfo.get("queryColumnId").getAsString();
			String dataFieldDataTableTagName = elementPropertiesInfo.get("dataFieldDataTableTagName").getAsString();
			String leafNodeEntityName = elementPropertiesInfo.get("leafNodeEntityName").getAsString();
			String leafNodeQueryColumnAlias = elementPropertiesInfo.get("leafNodeQueryColumnId").getAsString();			
			String dataFieldBindType = elementPropertiesInfo.get("dataFieldBindType").getAsString();			
			if (overrideWhereClause == 0 && dataFieldBoundToTable == 0 && dataFieldBindType.equalsIgnoreCase("QUERY"))
			{
				JsonArray WhereClausesList = new JsonArray();
				JsonArray parameterBindingsList = new JsonArray();
				int isDataFieldWithoutOverrideWhereClauseQueryCodeExisted = 0;
				for (int i = 0; i < layoutQueriesList.size(); i++)
				{
					JsonObject layoutQueryInfo = layoutQueriesList.get(i).getAsJsonObject();
					if (layoutQueryInfo.get("queryCode").getAsString().equals(queryCode))
					{
						isDataFieldWithoutOverrideWhereClauseQueryCodeExisted = 1;
						WhereClausesList = layoutQueryInfo.get("whereClausesList").getAsJsonArray();
						parameterBindingsList = layoutQueryInfo.get("parameterBindingsList").getAsJsonArray();
						break;
					}
				}
				if (isDataFieldWithoutOverrideWhereClauseQueryCodeExisted == 0)
				{
					dataObject.addProperty("success", 0);
					dataObject.addProperty("alert", "Page level data field query code(" + queryCode + ") not existed in layout queries list.");
					return dataObject;
				}
				int isQueryCodeExisted = 0;
				for (int j = 0; j < pageQueriesList.size(); j++)
				{
					JsonObject fieldQueryInfoTemp = pageQueriesList.get(j).getAsJsonObject();
					String queryCodeTemp = fieldQueryInfoTemp.get("queryCode").getAsString();
					if (queryCode.equals(queryCodeTemp))
					{
						isQueryCodeExisted = 1;
					}
				}
				if (isQueryCodeExisted == 0)
				{
					JsonObject pageQueryInfo = new JsonObject();
					pageQueryInfo.addProperty("queryCodeDataName", queryCode);
					pageQueryInfo.addProperty("queryCodeDataName", queryCode);
					pageQueryInfo.addProperty("queryCode", queryCode);
					pageQueryInfo.add("whereClausesList", WhereClausesList);
					pageQueryInfo.add("parameterBindingsList", parameterBindingsList);
					pageQueriesList.add(pageQueryInfo);
				}
			}
			JsonArray whereClausesList =  new JsonArray();
			JsonArray parameterBindingsList =  new JsonArray();
			JsonObject elementQueryInfo = elementInfoObj.get("queryInfo").getAsJsonObject();
			if (elementQueryInfo.has("whereClausesList"))
			{
				whereClausesList = elementQueryInfo.get("whereClausesList").getAsJsonArray();
			}
			if (elementQueryInfo.has("parameterBindingsList"))
			{
				parameterBindingsList = elementQueryInfo.get("parameterBindingsList").getAsJsonArray();
			}					
			JsonObject dataFieldObject = new JsonObject();
			dataFieldObject.addProperty("fieldDataName", htmlTagName);
			dataFieldObject.addProperty("fieldAliasName", queryColumnAlias);		
			dataFieldObject.addProperty("leafNodeQueryColumnAlias", leafNodeQueryColumnAlias);		
			dataFieldObject.addProperty("tableDataName", dataFieldDataTableTagName);
			dataFieldObject.addProperty("leafNodeEntityName", leafNodeEntityName);
			JsonObject dataFieldElementPropertiesInfo = new JsonObject();
			dataFieldElementPropertiesInfo.addProperty("overrideWhereClause", overrideWhereClause);
			dataFieldElementPropertiesInfo.addProperty("dataFieldBoundToTable", dataFieldBoundToTable);
			dataFieldElementPropertiesInfo.addProperty("dataFieldSearchFormToUse", searchFormName);
			dataFieldElementPropertiesInfo.addProperty("queryCode", queryCode);
			dataFieldElementPropertiesInfo.addProperty("dataFieldBindType", dataFieldBindType);
			dataFieldObject.add("elementPropertiesInfo", dataFieldElementPropertiesInfo);
			dataFieldObject.add("whereClausesList", whereClausesList);
			dataFieldObject.add("parameterBindingsList", parameterBindingsList);
			dataFieldsList.add(dataFieldObject);
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String elementQueryInfoText = gson.toJson(elementQueryInfo);	
			elementQueryInfoText = elementQueryInfoText.replaceAll("\"", "'");
			//HTML
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase  + "";
			String dataFieldAttributes = "  data-query-column-alias=\"" + queryColumnAlias  + "\""
					+ " data-override-where-clause=\"" + overrideWhereClause + "\""
					+ " data-query-code=\"" + queryCode   + "\""
					+ " data-search-form-name=\"" + searchFormName  + "\""
					+ " data-bind-to-table=\"" + dataFieldBoundToTable + "\""
					+ " data-table-name=\"" + dataFieldDataTableTagName	 + "\""									
					+ " data-query-info=\"" + elementQueryInfoText  + "\""
					+ " data-data-field-bind-type=\"" + dataFieldBindType  + "\""
					+ " data-leaf-node-query-column-alias=\"" + leafNodeQueryColumnAlias + "\""					
					+ dataNameAttribute 
					+ compoentTypeDecodedText ;
			if (htmlWithData == 1)
			{
				dataFieldAttributes = dataNameAttribute;
			}			
			String htmlTagAttibutesText = gethtmlElementAttibutesText(organisationSession, elementInfoObj, htmlWithData, dataFieldsTokensObj, layoutName, URLParametersList, pageQueriesList);
			htmlTagHTML += dataFieldAttributes + " style=\" " + htmlTagCSSPropertiesText + "\" " + htmlTagAttibutesText + ">";					
			if (htmlWithData == 1)
			{				
				JsonObject dataFieldOutputInfoObj = LayoutParserUtil.getDataFieldOutputText(organisationSession, layoutName,  elementInfoObj, URLParametersList, whereClausesList, parameterBindingsList, pageQueriesList);
				if(dataFieldOutputInfoObj.get("success").getAsInt()!=1)
				{
					System.err.println(dataFieldOutputInfoObj.get("alert").getAsString());
				}
				String dataFieldResultDataText = dataFieldOutputInfoObj.get("dataFieldResultDataText").getAsString();
				htmlTagHTML += dataFieldResultDataText;
			}
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
		}
		else if(componentType.equalsIgnoreCase("DATA_TABLE"))
		{			
			JsonArray whereClausesList = new JsonArray();
			JsonArray parameterBindingsList = new JsonArray();
			JsonArray parentRowDataParameterBindingsList = new JsonArray();
			String searchFormName = elementPropertiesInfo.get("searchFormName").getAsString();
			String queryCode = elementPropertiesInfo.get("queryCode").getAsString();
			String enablePaginationInTableLevel = elementPropertiesInfo.get("enablePaginationInTableLevel").getAsString();
			JsonObject elementQueryInfo = elementInfoObj.get("queryInfo").getAsJsonObject();
			if (elementQueryInfo.has("whereClausesList"))
			{
				whereClausesList = elementQueryInfo.get("whereClausesList").getAsJsonArray();
			}
			if (elementQueryInfo.has("parameterBindingsList"))
			{
				parameterBindingsList = elementQueryInfo.get("parameterBindingsList").getAsJsonArray();
			}		
			if (elementQueryInfo.has("parentRowDataParameterBindingsList"))
			{
				parentRowDataParameterBindingsList = elementQueryInfo.get("parentRowDataParameterBindingsList").getAsJsonArray();
			}	
			int enableHierarchialData = elementPropertiesInfo.get("enableHierarchialData").getAsInt();
			int enableLeafNodes = elementPropertiesInfo.get("enableLeafNodes").getAsInt();
			String leafNodeEntityName = elementPropertiesInfo.get("leafNodeEntityName").getAsString();
			elementPropertiesInfo.addProperty("tableDataName", htmlTagName);
			elementPropertiesInfo.addProperty("searchFormToUse", searchFormName);
			elementPropertiesInfo.addProperty("enableHierarchialData", enableHierarchialData);
			elementPropertiesInfo.addProperty("enableLeafNodes", enableLeafNodes);
			int isTopMostParentTable = 1;
			String parentTableDataName = "";
			if(elementPropertiesInfo.has("isTopMostParentTable") && elementPropertiesInfo.has("parentTableDataName"))
			{
				isTopMostParentTable = elementPropertiesInfo.get("isTopMostParentTable").getAsInt();
				parentTableDataName = elementPropertiesInfo.get("parentTableDataName").getAsString();
			}
			elementPropertiesInfo.addProperty("isTopMostParentTable", isTopMostParentTable);
			elementPropertiesInfo.addProperty("parentTableDataName", parentTableDataName);
			JsonObject subSequentLevelQueryInfo = elementInfoObj.get("subSequentLevelQueryInfo").getAsJsonObject();
			JsonObject leafNodeQueryInfo = elementInfoObj.get("leafNodeQueryInfo").getAsJsonObject();
			JsonObject dataTableObject = new JsonObject();
			dataTableObject.addProperty("tableDataName", htmlTagName);
			dataTableObject.addProperty("leafNodeEntityName", leafNodeEntityName);
			dataTableObject.add("elementPropertiesInfo", elementPropertiesInfo);
			dataTableObject.add("whereClausesList", whereClausesList);
			dataTableObject.add("parameterBindingsList", parameterBindingsList);
			dataTableObject.add("parentRowDataParameterBindingsList", parentRowDataParameterBindingsList);				
			dataTableObject.add("queryInfo", elementQueryInfo);
			dataTableObject.add("subSequentLevelQueryInfo", subSequentLevelQueryInfo);
			dataTableObject.add("leafNodeQueryInfo", leafNodeQueryInfo);
			//Child Data Tables
			for (int i = 0; i < htmlTagChildElementsList.size(); i++)
			{
				JsonObject childHMTLTagInfoObj = htmlTagChildElementsList.get(i).getAsJsonObject();		
				JsonObject childTagElementPropertiesInfo = childHMTLTagInfoObj.get("elementPropertiesInfo").getAsJsonObject();
				String childTagComponentType = childTagElementPropertiesInfo.get("componentType").getAsString();
				if(childTagComponentType.equalsIgnoreCase("DATA_TABLE"))
				{
					childTagElementPropertiesInfo.addProperty("isTopMostParentTable", 0);
					childTagElementPropertiesInfo.addProperty("parentTableDataName", htmlTagName);
				}
				else
				{
					changeChildDataTableProperties(childHMTLTagInfoObj, htmlTagName);
				}				
			}
			tablesList.add(dataTableObject);
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String elementQueryInfoText = gson.toJson(elementQueryInfo);
			elementQueryInfoText = elementQueryInfoText.replaceAll("\"", "'");
			String subSequentQueryInfoText = gson.toJson(subSequentLevelQueryInfo);
			subSequentQueryInfoText = subSequentQueryInfoText.replaceAll("\"", "'");
			String leafNodeQueryInfoText = gson.toJson(leafNodeQueryInfo);
			leafNodeQueryInfoText = leafNodeQueryInfoText.replaceAll("\"", "'");
			//HTML
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			compoentTypeDecodedText = " data-is-data-table=\"1\"";
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase 										
					+ " data-query-code=\"" + queryCode  + "\""
					+ " data-search-form-name=\"" + searchFormName  + "\""
					+ " data-enable-pagination=\"" + enablePaginationInTableLevel + "\""
					+ " data-query-info=\"" + elementQueryInfoText 	+ "\""
					+ " data-sub-sequent-level-query-info=\"" + subSequentQueryInfoText 	+ "\""
					+ " data-leaf-node-query=\"" + leafNodeQueryInfoText 	+ "\""
					+ " data-leaf-node-entity-name=\"" + leafNodeEntityName 	+ "\""
					+ " data-enable-hierarchial-data=\"" + enableHierarchialData 	+ "\""
					+ " data-enable-leaf-nodes=\"" + enableLeafNodes 	+ "\""
					+ dataNameAttribute 
					+ compoentTypeDecodedText 
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
			if (htmlWithData == 1 && isTopMostParentTable == 1)
			{
				JsonObject dataTableWithDataInfoObj = LayoutParserUtil.getDataTableWithData(organisationSession, layoutName, elementInfoObj, URLParametersList, dataFieldsList, htmlTagHTML, tablesList, htmlTagName);
				if(dataTableWithDataInfoObj.get("success").getAsInt()!=1)
				{
					return dataTableWithDataInfoObj;
				}
				String dataTableWithDataHTML = dataTableWithDataInfoObj.get("dataTableWithDataHTML").getAsString();
				htmlTagHTML = dataTableWithDataHTML;
			}
			dataTableObject.addProperty("dataTableHTML", htmlTagHTML);
		}
		else if(componentType.equalsIgnoreCase("GRAPH"))
		{
			JsonObject graphObject = new JsonObject();
			JsonArray whereClausesList = new JsonArray();
			JsonArray parameterBindingsList = new JsonArray();
			JsonArray graphQueryColumnsList = new JsonArray();	
			String searchFormName = elementPropertiesInfo.get("searchFormName").getAsString();
			String queryCode = elementPropertiesInfo.get("queryCode").getAsString();
			String htmlTagGraphType = elementPropertiesInfo.get("htmlTagGraphType").getAsString();					
			JsonObject elementQueryInfo = elementInfoObj.get("queryInfo").getAsJsonObject();
			if (elementQueryInfo.has("whereClausesList"))
			{
				whereClausesList = elementQueryInfo.get("whereClausesList").getAsJsonArray();
			}
			if (elementQueryInfo.has("parameterBindingsList"))
			{
				parameterBindingsList = elementQueryInfo.get("parameterBindingsList").getAsJsonArray();
			}	
			if (elementInfoObj.has("graphQueryColumnsList"))
			{
				graphQueryColumnsList = elementInfoObj.get("graphQueryColumnsList").getAsJsonArray();
			}	
			graphObject.addProperty("graphDataName", htmlTagName);
			graphObject.addProperty("graphType", htmlTagGraphType);				
			graphObject.add("whereClausesList", whereClausesList);
			graphObject.add("parameterBindingsList", parameterBindingsList);
			graphObject.add("graphQueryColumnsList", graphQueryColumnsList);
			graphObject.add("elementPropertiesInfo", elementPropertiesInfo);						
			elementPropertiesInfo.addProperty("graphSearchFormName", searchFormName);
			graphsList.add(graphObject);
			String graphQueryColumnsListText = "";
			String graphPropertyBindingsListText = "";
			String graphLegendDescriptionsText = "";
			if(htmlTagType.equalsIgnoreCase("BAR_GRAPH") || htmlTagType.equalsIgnoreCase("SCATTER_PLOT_GRAPH"))
			{
				for(int i = 0;i <  graphQueryColumnsList.size();i++)
				{
					JsonObject graphQueryColumnInfoObj =  graphQueryColumnsList.get(i).getAsJsonObject();
					String graphQueryColumnAlias = graphQueryColumnInfoObj.get("graphQueryColumnAlias").getAsString();
					String graphQueryColumnMappingTo = graphQueryColumnInfoObj.get("graphQueryColumnMappingTo").getAsString();
					String graphQueryColumnLegendDescription = graphQueryColumnInfoObj.get("graphQueryColumnLegendDescription").getAsString();
					graphQueryColumnsListText = graphQueryColumnsListText + graphQueryColumnAlias;
					graphPropertyBindingsListText = graphPropertyBindingsListText + graphQueryColumnMappingTo;								
					if (i < (graphQueryColumnsList.size() - 1))
					{
						graphQueryColumnsListText += " , ";
						graphPropertyBindingsListText += " , ";					
					}
					if(graphQueryColumnMappingTo.equalsIgnoreCase("Y_AXIS_COLUMN"))
					{
						graphLegendDescriptionsText += graphQueryColumnLegendDescription;
					}
				}				
			}
			if(htmlTagType.equalsIgnoreCase("PIE_CHART"))
			{
				String pieChartDataQueryColumn = elementPropertiesInfo.get("pieChartDataQueryColumn").getAsString();
				String pieChartLegendQueryColumn = elementPropertiesInfo.get("pieChartLegendQueryColumn").getAsString();
				graphQueryColumnsListText = pieChartDataQueryColumn  + " , " + pieChartLegendQueryColumn ;
				graphPropertyBindingsListText = "PIE_CHART_SECTOR_DATA_COLUMN" + " , " + "LEGEND_DESCRIPTION_COLUMN";
			}
			GsonBuilder builder = new GsonBuilder();
			builder.disableHtmlEscaping();
			Gson gson = builder.create();
			String elementQueryInfoText = gson.toJson(elementQueryInfo);
			elementQueryInfoText = elementQueryInfoText.replaceAll("\"", "'");
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			compoentTypeDecodedText = " data-is-graph=\"1\"";
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase 
					+ " data-query-code=\"" + queryCode 
					+ "\" data-search-form-name=\"" + searchFormName 
					+ "\" data-graph-type=\"" + htmlTagGraphType 									
					+ "\" data-query-columns=\"" + graphQueryColumnsListText 
					+ "\" data-graph-property-binding=\"" + graphPropertyBindingsListText 
					+ "\" data-legends-description=\"" + graphLegendDescriptionsText 	
					+ "\" data-query-info=\"" + elementQueryInfoText 	+ "\""
					+ dataNameAttribute 
					+ compoentTypeDecodedText 
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
		}
		else if(componentType.equalsIgnoreCase("SEARCH_FORM"))
		{
			elementPropertiesInfo.addProperty("searchFormName", htmlTagName);
			searchFormsList.add(elementInfoObj);
			//HTML			
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			compoentTypeDecodedText = " data-is-search-form=\"1\"";
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase 
					+ dataNameAttribute 
					+ compoentTypeDecodedText 
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
		}
		else if(componentType.equalsIgnoreCase("SEARCH_FIELD"))
		{
			searchFieldsList.add(elementInfoObj);
			String searchFormName = elementPropertiesInfo.get("searchFormName").getAsString();
			String isSearchFieldMandatory = elementPropertiesInfo.get("isSearchFieldMandatory").getAsString();
			String searchFieldParamterName = elementPropertiesInfo.get("searchFieldParameterName").getAsString();
			String lookUpEntityName = elementPropertiesInfo.get("lookUpEntityName").getAsString();
			String searchFieldType = elementPropertiesInfo.get("searchFieldType").getAsString();
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			compoentTypeDecodedText = " data-is-search-field=\"1\"";
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase 	 	
					+ " data-parameter-name=\"" + searchFieldParamterName + "\""
					+ " data-search-field-type=\"" + searchFieldType + "\""
					+ " data-is-search-field-mandatory=\"" + isSearchFieldMandatory + "\""
					+ " data-search-form-name=\"" + searchFormName + "\""
					+ " data-lookup-entity-name=\"" + lookUpEntityName + "\""	
					+ " data-selected-option-value=\"" + -1 + "\""					
					+ dataNameAttribute 
					+ compoentTypeDecodedText  
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
		}	
		else if(componentType.equalsIgnoreCase("SEARCH_BUTTON"))
		{
			String searchFormName = elementPropertiesInfo.get("searchFormName").getAsString();			
			//HTML			
			JsonObject childElementsInfoObj = getHTMLTagChildElementsHTML(layoutName, htmlTagChildElementsList, organisationSession, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, componentType, dataFieldsTokensObj);
			if(childElementsInfoObj.get("success").getAsInt()!=1)
			{
				return childElementsInfoObj;
			}
			htmlTagChildElementsHTML = childElementsInfoObj.get("htmlTagChildElementsHTML").getAsString(); 
			compoentTypeDecodedText = " data-is-search-button=\"1\"";
			htmlTagHTML =  "<" + htmlTagTypeInLowerCase 
					+ " data-search-form-name=\"" + searchFormName + "\""
					+ dataNameAttribute 
					+ compoentTypeDecodedText 
					+" style=\" " + htmlTagCSSPropertiesText + "\">";
			htmlTagHTML += htmlTagChildElementsHTML;
			htmlTagHTML += "</" + htmlTagTypeInLowerCase + ">";
		}
		dataObject.addProperty("success", 1);				
		dataObject.addProperty("htmlWithOutData", htmlTagHTML);
		return dataObject;
	}
	private static String gethtmlElementAttibutesText(Session organisationSession, JsonObject elementInfoObj, int htmlWithData, JsonObject dataFieldsTokensObj, String layoutName, JsonArray URLParametersList, JsonArray pageQueriesList)
	{
		String htmlElementAttributesText = " ";
		try
		{
			JsonObject elementAttributesInfo = new JsonObject();
			if(!elementInfoObj.has("elementAttributesInfo"))
			{
				return htmlElementAttributesText;
			}
			elementAttributesInfo = elementInfoObj.get("elementAttributesInfo").getAsJsonObject();
			Set set = elementAttributesInfo.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext())
			{
				Map.Entry entry = (Map.Entry) i.next();
				String attributeName = (String) entry.getKey();
				Object attributeValueObj = entry.getValue();
				String attributeValue = attributeValueObj.toString();
				String htmlElementAttributeText = attributeName + " = " + attributeValue + " ";				
				if(htmlWithData == 1)
				{
					htmlElementAttributeText = getDataFieldTokensOutputText(organisationSession, htmlElementAttributeText, dataFieldsTokensObj, layoutName, URLParametersList, pageQueriesList);
				}
				htmlElementAttributesText += htmlElementAttributeText;				
			}
			return htmlElementAttributesText;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static String getDataFieldTokensOutputText(Session organisationSession, String dataFieldTokensText, JsonObject dataFieldTokensQueryInfoObj, String layoutName, JsonArray URLParametersList, JsonArray pageQueriesList)
	{
		String dataFieldTokensOutputText = dataFieldTokensText;
		try
		{
			String[] dataFieldTokens = StringUtils.substringsBetween(dataFieldTokensOutputText, "$$DATA_FIELD_", "$$");
			if (dataFieldTokens != null)
			{
				for (int j = 0; j < dataFieldTokens.length; j++)
				{
					String dataFieldDataName = dataFieldTokens[j];									
					if(!dataFieldTokensQueryInfoObj.has(dataFieldDataName))					
					{
						System.err.println(" Record not existed in datafields tokens info object  where Layout Name = " + layoutName + " Data Name = " + dataFieldDataName);
						return null;
					}
					JsonObject dataFieldTokenInfoObj = dataFieldTokensQueryInfoObj.get(dataFieldDataName).getAsJsonObject();
					String queryColumnAlias = dataFieldTokenInfoObj.get("queryColumnAlias").getAsString();
					String overrideWhereClause = dataFieldTokenInfoObj.get("overrideWhereClause").getAsString();
					String queryCode = dataFieldTokenInfoObj.get("queryCode").getAsString();
					String bindingType = dataFieldTokenInfoObj.get("bindingType").getAsString();					
					String dataFieldResultDataText = "";
					if(overrideWhereClause.equalsIgnoreCase("Yes"))
					{
						JsonArray whereClausesList = dataFieldTokenInfoObj.get("whereClausesList").getAsJsonArray();
						JsonArray parameterBindingsList = dataFieldTokenInfoObj.get("parameterBindingsList").getAsJsonArray();
						JsonObject queryOutputRecordInfoObj = LayoutParserUtil.getQueryOutputSingleRecordObject(organisationSession, queryCode, whereClausesList, parameterBindingsList, URLParametersList);
						if(queryOutputRecordInfoObj == null)
						{
							System.err.println("query output record could not be fetched.");
						}									
						if (queryOutputRecordInfoObj.has(queryColumnAlias))
						{
							dataFieldResultDataText = queryOutputRecordInfoObj.get(queryColumnAlias).getAsString();
						}
					}	
					if(bindingType.equalsIgnoreCase("Layout"))
					{
						JsonObject dataFieldOutputInfoObj = LayoutParserUtil.getPageLevelDataFieldOutputText(organisationSession, pageQueriesList, queryCode, URLParametersList, queryColumnAlias);
						if(dataFieldOutputInfoObj.get("success").getAsInt()!=1)
						{
							System.err.println(dataFieldOutputInfoObj.get("alert").getAsString());
						}
						dataFieldResultDataText = dataFieldOutputInfoObj.get("dataFieldResultDataText").getAsString();
					}
					if (dataFieldResultDataText.trim().length() > 0)
					{
						dataFieldResultDataText = VWTCoreCommonUtil.escapeHTMLText(dataFieldResultDataText);
					}											
					dataFieldTokensOutputText = ReplaceOrRemove(dataFieldTokensOutputText, "$$DATA_FIELD_" + dataFieldDataName + "$$", dataFieldResultDataText);
				}
			}
			return dataFieldTokensOutputText;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	private static JsonObject getHTMLTagChildElementsHTML(String layoutName, JsonArray htmlTagChildElementsList, Session organisationSession,
			JsonArray pageQueriesList, JsonArray dataFieldsList, JsonArray tablesList, JsonArray graphsList,
			JsonArray searchFormsList, JsonArray searchFieldsList, JsonArray URLParametersList,
			JsonArray layoutQueriesList, JsonObject outputdataInfoObj, String componentType, JsonObject dataFieldsTokensObj)
	{
		JsonObject dataObject = new JsonObject();
		String htmlTagChildElementsHTML = "";
		for (int i = 0; i < htmlTagChildElementsList.size(); i++)
		{
			JsonObject childHMTLTagInfoObj = htmlTagChildElementsList.get(i).getAsJsonObject();
			JsonObject elementHMTMInfoObj = LayoutParser.getElementHTML(organisationSession, layoutName,  childHMTLTagInfoObj, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, outputdataInfoObj, dataFieldsTokensObj);
			if (elementHMTMInfoObj.get("success").getAsInt() != 1)
			{
				return elementHMTMInfoObj;
			}
			String elementHTMLWithOutData = elementHMTMInfoObj.get("htmlWithOutData").getAsString();
			//adding data-is-search-input-field ="1"
			if(componentType.equalsIgnoreCase("SEARCH_FIELD"))
			{				
				if(elementHTMLWithOutData.contains("input") || elementHTMLWithOutData.contains("select"))
				{
					//<input "		
					int index = 0;
					if(elementHTMLWithOutData.contains("input"))
					{
						index = elementHTMLWithOutData.indexOf("<input ");
						index += 7;
					}
					if(elementHTMLWithOutData.contains("select"))
					{
						index = elementHTMLWithOutData.indexOf("<select ");
						index += 8;
					}
			        String stringBeforeSpace = elementHTMLWithOutData.substring(0, index + 1);
			        String stringAfterSpace = elementHTMLWithOutData.substring(index + 1, elementHTMLWithOutData.length());
			        elementHTMLWithOutData = stringBeforeSpace + " data-is-search-input-field =\"1\" " + stringAfterSpace;					
				}
			}
			htmlTagChildElementsHTML += elementHTMLWithOutData;
		}
		dataObject.addProperty("success", 1);
		dataObject.addProperty("htmlTagChildElementsHTML", htmlTagChildElementsHTML);
		return dataObject;		
	}
	private static void changeChildDataTableProperties(JsonObject childHMTLTagInfbj, String parentTableDataName)
	{
		JsonArray htmlTagChildElementsList = childHMTLTagInfbj.get("htmlTagChildElementsList").getAsJsonArray();		
		for (int i = 0; i < htmlTagChildElementsList.size(); i++)
		{
			JsonObject childHMTLTagInfoObj = htmlTagChildElementsList.get(i).getAsJsonObject();		
			JsonObject childTagElementPropertiesInfo = childHMTLTagInfoObj.get("elementPropertiesInfo").getAsJsonObject();
			String childTagComponentType = childTagElementPropertiesInfo.get("componentType").getAsString();
			if(childTagComponentType.equalsIgnoreCase("DATA_TABLE"))
			{
				childTagElementPropertiesInfo.addProperty("isTopMostParentTable", 0);
				childTagElementPropertiesInfo.addProperty("parentTableDataName", parentTableDataName);
			}
			else
			{
				changeChildDataTableProperties(childHMTLTagInfoObj, parentTableDataName);
			}				
		}
	}
	private static String getHTMLTagCSSPropertiesText(JsonArray htmlTagCSSPropertysList)
	{
		String htmlTagCSSPropertiesText = "";
		for (int i = 0; i < htmlTagCSSPropertysList.size(); i++)
		{
			JsonObject htmlTagCSSPropertyInfoObj = htmlTagCSSPropertysList.get(i).getAsJsonObject();
			String cssProperyName = htmlTagCSSPropertyInfoObj.get("cssProperyName").getAsString();
			String cssProperyValue = htmlTagCSSPropertyInfoObj.get("cssProperyValue").getAsString();
			htmlTagCSSPropertiesText += cssProperyName + " : " + cssProperyValue + "; ";
		}		
		return htmlTagCSSPropertiesText;
	}
	private static JsonObject getElementStyleText(JsonObject elementPropertiesInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject heightObject = elementPropertiesInfo.get("height").getAsJsonObject();
			String height = heightObject.get("value").getAsString();
			String heightUnit = heightObject.get("unit").getAsString();
			JsonObject widthObject = elementPropertiesInfo.get("width").getAsJsonObject();
			String width = widthObject.get("value").getAsString();
			String widthUnit = widthObject.get("unit").getAsString();
			JsonObject paddingTopObject = elementPropertiesInfo.get("paddingTop").getAsJsonObject();
			String paddingTop = paddingTopObject.get("value").getAsString();
			String paddingTopUnit = paddingTopObject.get("unit").getAsString();
			JsonObject paddingLeftObject = elementPropertiesInfo.get("paddingLeft").getAsJsonObject();
			String paddingLeft = paddingLeftObject.get("value").getAsString();
			String paddingLeftUnit = paddingLeftObject.get("unit").getAsString();
			JsonObject paddingRightObject = elementPropertiesInfo.get("paddingRight").getAsJsonObject();
			String paddingRight = paddingRightObject.get("value").getAsString();
			String paddingRightUnit = paddingRightObject.get("unit").getAsString();
			JsonObject paddingBottomObject = elementPropertiesInfo.get("paddingBottom").getAsJsonObject();
			String paddingBottom = paddingBottomObject.get("value").getAsString();
			String paddingBottomUnit = paddingBottomObject.get("unit").getAsString();
			JsonObject marginTopObject = elementPropertiesInfo.get("marginTop").getAsJsonObject();
			String marginTop = marginTopObject.get("value").getAsString();
			String marginTopUnit = marginTopObject.get("unit").getAsString();
			JsonObject marginBottomObject = elementPropertiesInfo.get("marginBottom").getAsJsonObject();
			String marginBottom = marginBottomObject.get("value").getAsString();
			String marginBottomUnit = marginBottomObject.get("unit").getAsString();
			JsonObject marginLeftObject = elementPropertiesInfo.get("marginLeft").getAsJsonObject();
			String marginLeft = marginLeftObject.get("value").getAsString();
			String marginLeftUnit = marginLeftObject.get("unit").getAsString();
			JsonObject marginRightObject = elementPropertiesInfo.get("marginRight").getAsJsonObject();
			String marginRight = marginRightObject.get("value").getAsString();
			String marginRightUnit = marginRightObject.get("unit").getAsString();
			JsonObject borderStyleObject = elementPropertiesInfo.get("borderStyle").getAsJsonObject();
			String borderStyle = borderStyleObject.get("value").getAsString();
			String borderStyleUnit = borderStyleObject.get("unit").getAsString();
			JsonObject borderColorObject = elementPropertiesInfo.get("borderColor").getAsJsonObject();
			String borderColor = borderColorObject.get("value").getAsString();
			String borderColorUnit = borderColorObject.get("unit").getAsString();
			JsonObject borderWidthObject = elementPropertiesInfo.get("borderWidth").getAsJsonObject();
			String borderWidth = borderWidthObject.get("value").getAsString();
			String borderWidthUnit = borderWidthObject.get("unit").getAsString();
			JsonObject fontSizeObject = elementPropertiesInfo.get("fontSize").getAsJsonObject();
			String fontSize = fontSizeObject.get("value").getAsString();
			String fontSizeUnit = fontSizeObject.get("unit").getAsString();
			JsonObject fontFamilyObject = elementPropertiesInfo.get("fontFamily").getAsJsonObject();
			String fontFamily = fontFamilyObject.get("value").getAsString();
			String fontFamilyUnit = fontFamilyObject.get("unit").getAsString();
			JsonObject textDecorationObject = elementPropertiesInfo.get("textDecoration").getAsJsonObject();
			String textDecoration = textDecorationObject.get("value").getAsString();
			String textDecorationUnit = textDecorationObject.get("unit").getAsString();
			String horizantalAlignment = "";
			if (elementPropertiesInfo.has("horizantalAlignment"))
			{
				JsonObject alignmentObject = elementPropertiesInfo.get("horizantalAlignment").getAsJsonObject();
				horizantalAlignment = alignmentObject.get("value").getAsString();
				// horizantalAlignement = alignmentObject.get("unit").getAsString();
			}
			String fonWeight = "";
			String fonWeightUnit = "";
			if (elementPropertiesInfo.has("fonWeight"))
			{
				JsonObject fonWeightObject = elementPropertiesInfo.get("fonWeight").getAsJsonObject();
				fonWeight = fonWeightObject.get("value").getAsString();
				fonWeightUnit = fonWeightObject.get("unit").getAsString();
			}
			String heightText = " ";
			String widthText = " ";
			String borderText = " ";
			String positionText = " ";
			String fontSizeText = "";
			String fontFamilyText = "";
			String paddingText = "";
			String marginText = "";
			String alignmentText = "";
			String fonWeightText = "";
			String whiteSpaceText = "";
			String textDecorationText = "";
			if (width != null && width.length() > 0)
			{
				widthText = " width : " + width + widthUnit + ";";
			}
			if (height != null && height.length() > 0)
			{
				heightText = " height : " + height + heightUnit + ";";
			}
			if (fontSize != null && fontSize.length() > 0)
			{
				fontSizeText = " font-size : " + fontSize + fontSizeUnit + ";";
			}
			if (fontFamily != null && fontFamily.length() > 0)
			{
				fontFamilyText = " font-family : " + fontFamily + fontFamilyUnit + ";";
			}
			if (borderColor != null && borderColor.length() > 0)
			{
				borderText += " border-color: " + borderColor + borderColorUnit + ";";
			}
			if (borderStyle != null && borderStyle.length() > 0)
			{
				borderText += " border-style: " + borderStyle + borderStyleUnit + ";";
			}
			if (borderWidth != null && borderWidth.length() > 0)
			{
				borderText += " border-width: " + borderWidth + borderWidthUnit + ";";
			}
			if (paddingTop != null && paddingTop.length() > 0)
			{
				paddingText += " padding-top: " + paddingTop + paddingTopUnit + ";";
			}
			if (paddingLeft != null && paddingLeft.length() > 0)
			{
				paddingText += " padding-left: " + paddingLeft + paddingLeftUnit + ";";
			}
			if (paddingRight != null && paddingRight.length() > 0)
			{
				paddingText += " padding-right: " + paddingRight + paddingRightUnit + ";";
			}
			if (paddingBottom != null && paddingBottom.length() > 0)
			{
				paddingText += " padding-bottom: " + paddingBottom + paddingBottomUnit + ";";
			}
			if (marginTop != null && marginTop.length() > 0)
			{
				marginText += " margin-top: " + marginTop + marginTopUnit + ";";
			}
			if (marginLeft != null && marginLeft.length() > 0)
			{
				marginText += " margin-left: " + marginLeft + marginLeftUnit + ";";
			}
			if (marginRight != null && marginRight.length() > 0)
			{
				marginText += " margin-right: " + marginRight + marginRightUnit + ";";
			}
			if (marginBottom != null && marginBottom.length() > 0)
			{
				marginText += " margin-bottom: " + marginBottom + marginBottomUnit + ";";
			}
			if (horizantalAlignment != null && horizantalAlignment.length() > 0)
			{
				alignmentText += " text-align: " + horizantalAlignment + ";";
			}
			if (fonWeight != null && fonWeight.length() > 0)
			{
				fonWeightText += " font-weight: " + fonWeight + fonWeightUnit + ";";
			}
			if (textDecoration != null && textDecoration.length() > 0)
			{
				textDecorationText += " text-decoration: " + textDecoration + textDecorationUnit + ";";
			}
			String styleText = " style=" + "\"" + heightText + widthText + borderText + positionText + fontSizeText + fontFamilyText + marginText + paddingText + alignmentText + fonWeightText + whiteSpaceText + textDecorationText + "\"";
			dataObject.addProperty("success", 1);
			dataObject.addProperty("styleText", styleText);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParserUtil.class, e);
			dataObject.addProperty("success", 0);
			dataObject.addProperty("alert", "Problem has occured while getting element style properties information.");
		}
		return dataObject;
	}
	public static String ReplaceOrRemove(String sReplaceOn, String sReplaceWhat, String sReplaceWith)
	{
		if (isExists(sReplaceWith))
		{
			return StringUtils.replace(sReplaceOn, sReplaceWhat, sReplaceWith);
		}
		else
		{
			return StringUtils.remove(sReplaceOn, sReplaceWhat);
		}
	}
	protected static boolean isExists(String sString)
	{
		return (sString != null && sString.trim().length() > 0);
	}
	//Output Code
	//Result Data	
	 public static JsonObject getDataFieldOutputText(Session organisationSession, String layoutName,  JsonObject elementInfoObj, JsonArray URLParametersList, JsonArray whereClausesList, JsonArray parameterBindingsList, JsonArray pageQueriesList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String dataFieldResultDataText = "";
			JsonObject elementPropertiesInfo = elementInfoObj.get("elementPropertiesInfo").getAsJsonObject();
			String queryCode = elementPropertiesInfo.get("queryCode").getAsString();
			int overrideWhereClause = elementPropertiesInfo.get("overrideWhereClause").getAsInt();
			String queryColumnId = elementPropertiesInfo.get("queryColumnId").getAsString();			
			int dataFieldBoundToTable = elementPropertiesInfo.get("dataFieldBoundToTable").getAsInt();
			String dataFieldBindType = elementPropertiesInfo.get("dataFieldBindType").getAsString();
			String htmlTagName = elementPropertiesInfo.get("htmlTagName").getAsString();
			String queryFieldAlias = queryColumnId;			
			if(dataFieldBindType.equalsIgnoreCase("CUSTOM"))
			{				
				return getCustomDataFieldOutputText(URLParametersList, htmlTagName, layoutName);					
			}
			if (overrideWhereClause ==0 && dataFieldBoundToTable == 0)
			{								
				return getPageLevelDataFieldOutputText(organisationSession, pageQueriesList, queryCode, URLParametersList, queryFieldAlias);
			}	
			if (overrideWhereClause != 1)
			{
				dataObject.addProperty("success", 1);
				dataObject.addProperty("dataFieldResultDataText", "");
				return dataObject;
			}
			JsonObject queryOutputRecordInfoObj = getQueryOutputSingleRecordObject(organisationSession, queryCode, whereClausesList, parameterBindingsList, URLParametersList);
			if(queryOutputRecordInfoObj == null)
			{
				System.err.println("query output record could not be fetched.");
			}					
			if (queryOutputRecordInfoObj.has(queryFieldAlias))
			{
				dataFieldResultDataText = queryOutputRecordInfoObj.get(queryFieldAlias).getAsString();
			}
			if (dataFieldResultDataText.length() > 0)
			{
				dataFieldResultDataText = VWTCoreCommonUtil.escapeHTMLText(dataFieldResultDataText);
			}
			dataObject.addProperty("success", 1);
			dataObject.addProperty("dataFieldResultDataText", dataFieldResultDataText);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParserUtil.class, e);
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("Data field result text could not fetched.");
		}
		finally
		{
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Data field result data text could not be retrieved.");
		return dataObject;
	}	
	 public static JsonObject getCustomDataFieldOutputText(JsonArray URLParametersList, String htmlTagName, String layoutName)
	{
		JsonObject dataObject  = new JsonObject();
		try
		{			
			JsonObject layoutCustomDataFieldsObject = new JsonObject();
			layoutCustomDataFieldsObject.addProperty(htmlTagName, "");
			HashMap<String, String> paramsMap = new HashMap<String, String>();
			for (int i = 0; i < URLParametersList.size(); i++)
			{
				JsonObject parameterNameAndValueObj = URLParametersList.get(i).getAsJsonObject();
				String parameterName = parameterNameAndValueObj.get("parameterName").getAsString();
				String parameterValue = parameterNameAndValueObj.get("parameterValue").getAsString();
				paramsMap.put(parameterName, parameterValue);
			}
			ReportRequestHandler.populateCustomDataFields(paramsMap, layoutCustomDataFieldsObject, layoutName, null, new JsonObject());	
			String dataFieldResultDataText = layoutCustomDataFieldsObject.get(htmlTagName).getAsString();
			dataObject.addProperty("success", 1);
			dataObject.addProperty("dataFieldResultDataText", dataFieldResultDataText);
			return dataObject;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Custom data filed output text could not be retrieved.");
		return dataObject;
	}
	 public static JsonObject getPageLevelDataFieldOutputText(Session organisationSession, JsonArray pageQueriesList, String dataFieldQueryCode, JsonArray URLParametersList, String queryFieldAlias)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String dataFieldResultDataText = "";
			for(int i = 0;i< pageQueriesList.size();i++)
			{
				JsonObject pageQueryInfoObj = pageQueriesList.get(i).getAsJsonObject();
				String queryCode = pageQueryInfoObj.get("queryCode").getAsString();
				JsonArray whereClausesList = pageQueryInfoObj.get("whereClausesList").getAsJsonArray();
				JsonArray parameterBindingsList = pageQueryInfoObj.get("parameterBindingsList").getAsJsonArray();
				if(!dataFieldQueryCode.equalsIgnoreCase(queryCode))
				{
					continue;
				}
				JsonObject queryOutputRecordInfoObj = new JsonObject();
				if(pageQueryInfoObj.has("queryOutputRecordInfoObj"))
				{
					queryOutputRecordInfoObj = pageQueryInfoObj.get("queryOutputRecordInfoObj").getAsJsonObject();
				}
				else
				{
					queryOutputRecordInfoObj = getQueryOutputSingleRecordObject(organisationSession, queryCode, whereClausesList, parameterBindingsList, URLParametersList);
					if(queryOutputRecordInfoObj == null)
					{
						System.err.println("query output record could not be fetched.");
						dataObject.addProperty("success", 0);
						dataObject.addProperty("alert", "query output record could not be fetched.");
						return dataObject;
					}
					pageQueryInfoObj.add("queryOutputRecordInfoObj", queryOutputRecordInfoObj);
				}		
				dataFieldResultDataText = queryOutputRecordInfoObj.get(queryFieldAlias).getAsString();
				dataObject.addProperty("success", 1);
				dataObject.addProperty("dataFieldResultDataText", dataFieldResultDataText);
				return dataObject;				
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Page level data field output text could not be retrieved.");
		return dataObject;
	}
	 public static JsonObject getQueryOutputSingleRecordObject(Session organisationSession, String queryCode, JsonArray whereClausesList,  JsonArray parameterBindingsList, JsonArray URLParametersList)
	{		
		try
		{
			JsonObject parameterNameAndValueMapObj = getQueryParameterNameAndValueslist(parameterBindingsList, URLParametersList);
			if (parameterNameAndValueMapObj.get("success").getAsInt() != 1)
			{
				return parameterNameAndValueMapObj;
			}
			JsonArray queryParameterNameAndValueslist = parameterNameAndValueMapObj.get("queryParameterNameAndValueslist").getAsJsonArray();
			JsonObject queryOutputObj = getQueryOutputNew(organisationSession, queryCode, whereClausesList, queryParameterNameAndValueslist);
			if (queryOutputObj.get("success").getAsInt() != 1)
			{
				return queryOutputObj;
			}			
			JsonArray queryOutputRecordsList = queryOutputObj.get("queryOutputRecordsList").getAsJsonArray();								
			JsonArray outputRecordColumnsList = new JsonArray();
			if (queryOutputRecordsList.size() > 0)
			{
				JsonObject outputRecordColumnsListInfoObj = queryOutputRecordsList.get(0).getAsJsonObject();
				outputRecordColumnsList = outputRecordColumnsListInfoObj.get("outputRecordColumnsList").getAsJsonArray();
			}
			JsonObject queryOutputRecordInfoObj = new JsonObject();
			for (int j = 0; j < outputRecordColumnsList.size(); j++)
			{
				JsonObject queryOutputRecordColumnInfo = outputRecordColumnsList.get(j).getAsJsonObject();
				String columnAlias = queryOutputRecordColumnInfo.get("columnAlias").getAsString();
				String columnValue = "";
				if (queryOutputRecordColumnInfo.has("columnValue") && queryOutputRecordColumnInfo.get("columnValue").isJsonNull() == false)
				{
					columnValue = queryOutputRecordColumnInfo.get("columnValue").getAsString();
				}
				queryOutputRecordInfoObj.addProperty(columnAlias, columnValue);
			}	
			return queryOutputRecordInfoObj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	 public static JsonObject getQueryParameterNameAndValueslist(JsonArray parameterBindingsList, JsonArray URLParameterNameAndValuesList)
	{
		JsonObject dataObject = new JsonObject();
		JsonArray queryParameterNameAndValueslist = new JsonArray();
		for (int i = 0; i < parameterBindingsList.size(); i++)
		{
			JsonObject parameterBindingInfoObj = parameterBindingsList.get(i).getAsJsonObject();
			String parameterName = parameterBindingInfoObj.get("parameterName").getAsString();
			String isConditionalParameter = parameterBindingInfoObj.get("isConditionalParameter").getAsString();
			String parameterValueDataType = "String";
			if(parameterBindingInfoObj.has("parameterValueDataType"))
			{
				parameterValueDataType = parameterBindingInfoObj.get("parameterValueDataType").getAsString();	
			}
			for (int j = 0; j < URLParameterNameAndValuesList.size(); j++)
			{
				JsonObject URLParameterNameAndValueInfoObj = URLParameterNameAndValuesList.get(j).getAsJsonObject();
				String URLParameterName = URLParameterNameAndValueInfoObj.get("parameterName").getAsString();
				String parameterValue = URLParameterNameAndValueInfoObj.get("parameterValue").getAsString();				
				if (parameterName.equals(URLParameterName))
				{
					JsonObject queryParameterNameAndValueInfoObj = new JsonObject();
					queryParameterNameAndValueInfoObj.addProperty("parameterName", parameterName);
					queryParameterNameAndValueInfoObj.addProperty("parameterValue", parameterValue);
					queryParameterNameAndValueInfoObj.addProperty("isConditionalParameter", isConditionalParameter);
					queryParameterNameAndValueInfoObj.addProperty("parameterValueDataType", parameterValueDataType);					
					queryParameterNameAndValueslist.add(queryParameterNameAndValueInfoObj);
				}
			}
		}
		dataObject.addProperty("success", 1);
		dataObject.add("queryParameterNameAndValueslist", queryParameterNameAndValueslist);
		return dataObject;
	}
	public static JsonObject getQueryOutputNew(Session organisationSession, String queryCode, JsonArray WhereClausesList, JsonArray parameterAndValuesList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String selectQueryIdString = "select queryInfoId from QueryInfo where queryCode = ? ";
			int matchingQueryId = VWTCoreCommonUtil.getMatchingEntityId(organisationSession, selectQueryIdString, queryCode);
			if (matchingQueryId <= 0)
			{
				dataObject.addProperty("alert", "Matching query information could not be retrieved.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			int queryId = matchingQueryId;
			JsonObject executedQueryInfo = executeQueryNew(organisationSession, queryId, queryCode, WhereClausesList, parameterAndValuesList);
			if (executedQueryInfo.get("success").getAsInt() != 1)
			{
				return executedQueryInfo;
			}
			JsonArray queryOutputRecordsList = executedQueryInfo.get("queryOutputRecordsList").getAsJsonArray();
			dataObject.addProperty("success", 1);
			dataObject.add("queryOutputRecordsList", queryOutputRecordsList);
			return dataObject;
		}
		catch (Exception e)
		{
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("query output information could not be fetched.");
		}
		finally
		{
		}
		dataObject.addProperty("alert", "query output information could not be fetched.");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	 public static JsonObject executeQueryNew(Session organisationSession, int queryId, String queryCode, JsonArray WhereClausesList, JsonArray parameterAndValuesList) throws Exception
	{
		JsonObject dataObject = new JsonObject();		
		try
		{
			int enableQueryOffset = 0;
			int queryOffsetCount = 0;
			int queryLimitCount = 0;
			JsonObject reportInfoObj = QueryBuilderReporting.getQueryInfoNew(organisationSession, queryId);
			if (!(reportInfoObj.has("success") && reportInfoObj.get("success").getAsInt() == 1))
			{
				return reportInfoObj;
			}
			JsonObject queryInfo = reportInfoObj.get("queryInfo").getAsJsonObject();
			String queryString = queryInfo.get("queryString").getAsString();
			String queryOrderByGroupByString = queryInfo.get("queryOrderByGroupByString").getAsString();
			JsonArray queryColumnsList = queryInfo.get("queryColumnsList").getAsJsonArray();
			JsonArray queryCustomColumnsList = queryInfo.get("queryCustomColumnsList").getAsJsonArray();
			JsonObject buildQueryWithWhereClausesInfoObj = buildQueryNew(organisationSession, queryId, queryString, WhereClausesList);
			if (buildQueryWithWhereClausesInfoObj.get("success").getAsInt() != 1)
			{
				return buildQueryWithWhereClausesInfoObj;
			}
			String finalQuery = buildQueryWithWhereClausesInfoObj.get("finalQuery").getAsString();
			// Adding conditional where clauses
			for (int i = 0; i < WhereClausesList.size(); i++)
			{
				JsonObject whereClauseInfo = WhereClausesList.get(i).getAsJsonObject();
				String whereClauseType = whereClauseInfo.get("whereClauseType").getAsString();
				int isConditionalParameter = 0;
				if (whereClauseInfo.has("isConditionalParameter"))
				{
					isConditionalParameter = whereClauseInfo.get("isConditionalParameter").getAsInt();
				}
				if (isConditionalParameter != 1)
				{
					continue;
				}
				String conditionalParameterName = whereClauseInfo.get("conditionalParameterName").getAsString();
				int returnValue = isConditionalParamValueGreaterThanZero(parameterAndValuesList, conditionalParameterName);
				if (returnValue == 0)
				{
					continue;
				}
				String whereClauseParamValueQueryText = " = ? ";
				String queryWhereClauseColumn = "";
				if ("USER_INPUT".equalsIgnoreCase(whereClauseType))
				{
					whereClauseParamValueQueryText = " ";
					queryWhereClauseColumn = whereClauseInfo.get("userInput").getAsString();
				}
				if ("QUERY_COLUMN".equalsIgnoreCase(whereClauseType))
				{
					String whereClauseColumn = whereClauseInfo.get("queryColumnId").getAsString();
					queryWhereClauseColumn = whereClauseColumn;
					queryWhereClauseColumn = QueryBuilderReporting.getQueryColumnStringFromAliasNew(organisationSession, queryId, whereClauseColumn);
				}
				finalQuery += " and " + queryWhereClauseColumn + whereClauseParamValueQueryText;
			}
			finalQuery += queryOrderByGroupByString;
			Query query;
			String useNativeQuery = LayoutParserUtil.getUseNativeQueryStatus(organisationSession, queryCode);
			if(useNativeQuery.equalsIgnoreCase("Yes"))
			{
				query = organisationSession.createSQLQuery(finalQuery);
			}
			else
			{
				query = organisationSession.createQuery(finalQuery);
			}			
			int paramPosIndex = 0;
			for (int i = 0; i < parameterAndValuesList.size(); i++)
			{
				JsonObject parameterAndValueInfoObj = parameterAndValuesList.get(i).getAsJsonObject();
				String parameterValue = parameterAndValueInfoObj.get("parameterValue").getAsString();
				String parameterName = parameterAndValueInfoObj.get("parameterName").getAsString();
				String parameterValueDataType = "String";
				if(parameterAndValueInfoObj.has("parameterValueDataType"))
				{
					parameterValueDataType = parameterAndValueInfoObj.get("parameterValueDataType").getAsString();	
				}							
				int isConditionalParameter = 0;
				if (parameterAndValueInfoObj.has("isConditionalParameter"))
				{
					isConditionalParameter = parameterAndValueInfoObj.get("isConditionalParameter").getAsInt();
				}
				if (isConditionalParameter == 1)
				{
					boolean status = isConditionalParameterExistedInWhereClause(WhereClausesList, parameterName);
					if (status == false)
					{
						continue;
					}
					if (parameterValue.length() == 0)
					{
						continue;
					}
				}
				if(parameterValueDataType.equalsIgnoreCase("Integer"))
				{
					query.setParameter(paramPosIndex++, Integer.parseInt(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("Number"))
				{
					query.setParameter(paramPosIndex++, Integer.parseInt(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("Date"))
				{					
					query.setParameter(paramPosIndex++, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("DateWithSeconds"))
				{					
					query.setParameter(paramPosIndex++, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("Double"))
				{					
					query.setParameter(paramPosIndex++, Double.parseDouble(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("boolean"))
				{					
					query.setParameter(paramPosIndex++, Boolean.parseBoolean(parameterValue));	
				}
				else if(parameterValueDataType.equalsIgnoreCase("BigDecimal"))
				{					
					query.setParameter(paramPosIndex++, new BigDecimal(parameterValue));	
				}
				else
				{
					query.setParameter(paramPosIndex++, parameterValue);	
				}
			}
			JsonArray queryOutputRecordsList = new JsonArray();
			List resultRowsList = query.list();
			Iterator iterator = resultRowsList.iterator();
			int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
			while (iterator.hasNext())
			{
				Object[] resultRowColumnsList;
				if (singleColumnStatus == 0)
				{
					resultRowColumnsList = (Object[]) iterator.next();
				}
				else
				{
					resultRowColumnsList = new Object[1];
					resultRowColumnsList[0] = (Object) iterator.next();
				}
				JsonArray outputRecordColumnsList = new JsonArray();
				for (int i = 0; i < queryColumnsList.size(); i++)
				{
					JsonObject columnInfo = queryColumnsList.get(i).getAsJsonObject();
					String columnAlias = columnInfo.get("columnAlias").getAsString();
					int queryColumnIndex = getColumnAliasIndex(finalQuery, columnAlias);
					if(queryColumnIndex < 0)
					{
						System.out.println("Query column alias index could not found in select query where query is = " + finalQuery);
					}
					String columnValue = "";
					try
					{
						columnValue = String.valueOf(resultRowColumnsList[queryColumnIndex]);	
					}
					catch (Exception e) {
						int a =10;
						a++;
					}
					JsonObject info = new JsonObject();
					info.addProperty("columnAlias", columnAlias);
					info.addProperty("columnValue", columnValue);
					outputRecordColumnsList.add(info);
				}
				for (int i = 0; i < queryCustomColumnsList.size(); i++)
				{
					JsonObject columnInfo = queryCustomColumnsList.get(i).getAsJsonObject();
					String columnAlias = columnInfo.get("columnAlias").getAsString();
					JsonObject info = new JsonObject();
					String columnValue = "";
					int queryColumnIndex = getColumnAliasIndex(finalQuery, columnAlias);
					if(queryColumnIndex < 0)
					{
						System.out.println("Query column alias index could not found in select query where query is = " + finalQuery);
					}					
					columnValue = String.valueOf(resultRowColumnsList[queryColumnIndex]);
					info.addProperty("columnAlias", columnAlias);
					info.addProperty("columnValue", columnValue);
					outputRecordColumnsList.add(info);
				}
				JsonObject outputRecordInfoObj = new JsonObject();
				outputRecordInfoObj.add("outputRecordColumnsList", outputRecordColumnsList);
				queryOutputRecordsList.add(outputRecordInfoObj);
			}
			dataObject.addProperty("success", 1);
			dataObject.add("queryOutputRecordsList", queryOutputRecordsList);
			return dataObject;
		}
		catch (Exception e)
		{
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("query could not be executed.");
		}
		finally
		{
		}
		dataObject.addProperty("alert", "query could not be executed.");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	private static int getColumnAliasIndex(String finalQuery, String columnAliasText)
	{
		boolean isQueryColumnsCompleted = false;
		int columnAliasIndex = 0;
		while (isQueryColumnsCompleted == false)
		{
			int indexOfasText = finalQuery.indexOf(" as ");
			String textAfterAs = finalQuery.substring(indexOfasText + 4);
			textAfterAs = textAfterAs.trim();
			// System.out.println(textAfterAs);
			int indexOfComma = textAfterAs.indexOf(",");
			if ((!textAfterAs.contains(" as ")))
			{
				String columnAlias = textAfterAs.split(" ")[0].trim();				
				if (columnAlias.equalsIgnoreCase(columnAliasText))
				{
					return columnAliasIndex;
				}
				break;
			}
			String columnAlias = textAfterAs.substring(0, indexOfComma).trim();
			if (columnAlias.equalsIgnoreCase(columnAliasText))
			{
				return columnAliasIndex;
			}			
			finalQuery = textAfterAs;
			columnAliasIndex++;
		}
		return -1;
	}
	public static JsonObject buildQueryNew(Session organisationSession, int queryId, String queryString, JsonArray WhereClausesList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String whereClauseQuery = "";
			for (int i = 0; i < WhereClausesList.size(); i++)
			{
				JsonObject whereClauseInfo = WhereClausesList.get(i).getAsJsonObject();
				String whereClauseType = whereClauseInfo.get("whereClauseType").getAsString();
				int isConditionalParameter = 0;
				if (whereClauseInfo.has("isConditionalParameter"))
				{
					isConditionalParameter = whereClauseInfo.get("isConditionalParameter").getAsInt();
				}
				if (isConditionalParameter == 1)
				{
					continue;
				}
				String whereClauseParamValueQueryText = " = ? ";
				String queryWhereClauseColumn = "";
				if ("USER_INPUT".equalsIgnoreCase(whereClauseType))
				{
					whereClauseParamValueQueryText = " ";
					queryWhereClauseColumn = whereClauseInfo.get("userInput").getAsString();
				}
				if ("QUERY_COLUMN".equalsIgnoreCase(whereClauseType))
				{
					String whereClauseColumn = whereClauseInfo.get("queryColumnId").getAsString();
					queryWhereClauseColumn = whereClauseColumn;
					queryWhereClauseColumn = QueryBuilderReporting.getQueryColumnStringFromAliasNew(organisationSession, queryId, whereClauseColumn);
				}
				whereClauseQuery += " and " + queryWhereClauseColumn + whereClauseParamValueQueryText;
			}
			queryString += whereClauseQuery;
			dataObject.addProperty("success", 1);
			dataObject.addProperty("finalQuery", queryString);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParserUtil.class, e);
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("query could not be built.");
		}
		finally
		{
		}
		dataObject.addProperty("alert", "query could not be built.");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	 public static int isConditionalParamValueGreaterThanZero(JsonArray parameterAndValuesList, String conditionalParameterName)
	{
		for (int i = 0; i < parameterAndValuesList.size(); i++)
		{
			JsonObject parameterAndValueInfoObj = parameterAndValuesList.get(i).getAsJsonObject();
			String parameterValue = parameterAndValueInfoObj.get("parameterValue").getAsString();
			String parameterName = parameterAndValueInfoObj.get("parameterName").getAsString();
			if (parameterName.equalsIgnoreCase(conditionalParameterName))
			{
				if (parameterValue.length() > 0)
				{
					return 1;
				}
			}
		}
		return 0;
	}
	 public static boolean isConditionalParameterExistedInWhereClause(JsonArray whereClausesList, String parameterName)
	{
		for (int i = 0; i < whereClausesList.size(); i++)
		{
			JsonObject whereClauseInfo = whereClausesList.get(i).getAsJsonObject();
			String conditionalParameterName = whereClauseInfo.get("conditionalParameterName").getAsString();
			int isConditionalParameter = whereClauseInfo.get("isConditionalParameter").getAsInt();
			if (isConditionalParameter == 1 && conditionalParameterName.equals(parameterName))
			{
				return true;
			}
		}
		return false;
	}
	//Data Table Result Data
	 public static JsonObject getDataTableWithData(Session organisationSession, String layoutName,  JsonObject elementInfoObj, JsonArray URLParameterNameAndValuesList, JsonArray dataFieldsList, String htmlTagHTMLWithOutData, JsonArray tablesList, String tableDataName)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String rowsDataHTML = "";
			JsonArray whereClausesList = new JsonArray();
			JsonArray parameterBindingsList = new JsonArray();
			JsonObject elementQueryInfo = elementInfoObj.get("queryInfo").getAsJsonObject();
			whereClausesList = new JsonArray();
			if (elementQueryInfo.has("whereClausesList"))
			{
				whereClausesList = elementQueryInfo.get("whereClausesList").getAsJsonArray();
			}
			parameterBindingsList = new JsonArray();
			if (elementQueryInfo.has("parameterBindingsList"))
			{
				parameterBindingsList = elementQueryInfo.get("parameterBindingsList").getAsJsonArray();
			}
			JsonArray parentRowDataParameterBindingsList = new JsonArray();
			if (elementQueryInfo.has("parentRowDataParameterBindingsList"))
			{
				parentRowDataParameterBindingsList = elementQueryInfo.get("parentRowDataParameterBindingsList").getAsJsonArray();
			}
			JsonObject elementPropertiesInfo = elementInfoObj.get("elementPropertiesInfo").getAsJsonObject();
			String queryCode = elementPropertiesInfo.get("queryCode").getAsString();
			JsonObject returnedObj = getQueryParameterNameAndValueslist(parameterBindingsList, URLParameterNameAndValuesList);
			if (returnedObj.get("success").getAsInt() != 1)
			{
				return returnedObj;
			}
			JsonArray queryParameterNameAndValueslist = returnedObj.get("queryParameterNameAndValueslist").getAsJsonArray();
			JsonObject queryOutputObj = getQueryOutputNew(organisationSession, queryCode, whereClausesList, queryParameterNameAndValueslist);
			if (queryOutputObj.get("success").getAsInt() != 1)
			{
				return queryOutputObj;
			}
			JsonArray queryOutputRecordsList = queryOutputObj.get("queryOutputRecordsList").getAsJsonArray();
			JsonArray outputRecordColumnsList = new JsonArray();
			int indexOfFirstTRTag = htmlTagHTMLWithOutData.indexOf("<tr", (htmlTagHTMLWithOutData.indexOf("<tr")+1));
			String trClosingTag = "</tr>";
			int indexOfLastTRTag = htmlTagHTMLWithOutData.lastIndexOf(trClosingTag);
			String textBeforeTrTag = htmlTagHTMLWithOutData.substring(0, indexOfFirstTRTag);
			String textAfterTrTag = htmlTagHTMLWithOutData.substring(indexOfLastTRTag + trClosingTag.length() , htmlTagHTMLWithOutData.length());
			String rowHTML = htmlTagHTMLWithOutData.substring(indexOfFirstTRTag, (indexOfLastTRTag + trClosingTag.length()));			
			if (queryOutputRecordsList.size() > 0)
			{							
				for (int i = 0; i < queryOutputRecordsList.size(); i++)
				{
					JsonObject outputRecordColumnsListInfoObj = queryOutputRecordsList.get(i).getAsJsonObject();
					outputRecordColumnsList = outputRecordColumnsListInfoObj.get("outputRecordColumnsList").getAsJsonArray();
					JsonObject queryOutputRecordInfoObj = new JsonObject();
					for (int j = 0; j < outputRecordColumnsList.size(); j++)
					{
						JsonObject queryOutputRecordColumnInfo = outputRecordColumnsList.get(j).getAsJsonObject();
						String columnAlias = queryOutputRecordColumnInfo.get("columnAlias").getAsString();
						String columnValue = "";
						if (queryOutputRecordColumnInfo.has("columnValue") && queryOutputRecordColumnInfo.get("columnValue").isJsonNull() == false)
						{
							columnValue = queryOutputRecordColumnInfo.get("columnValue").getAsString();
						}
						queryOutputRecordInfoObj.addProperty(columnAlias, columnValue);
					}
					JsonObject dataTableRowDataInfoObj = getDataTableDataRowHTML(organisationSession ,layoutName,  queryOutputRecordInfoObj, dataFieldsList, rowHTML, tablesList, tableDataName, 0, URLParameterNameAndValuesList, parentRowDataParameterBindingsList);
					if (dataTableRowDataInfoObj.get("success").getAsInt() != 1)
					{
						return dataTableRowDataInfoObj;
					}
					String rowDataHTML = dataTableRowDataInfoObj.get("rowDataHTML").getAsString();
					rowsDataHTML += rowDataHTML;
					int enableHierarchialData = elementPropertiesInfo.get("enableHierarchialData").getAsInt();
					int enableLeafNodes = elementPropertiesInfo.get("enableLeafNodes").getAsInt();
					String nextLevelTableRowsDataHTML = "";
					String leafNodeTableRowsDataHTML = "";
					if (enableHierarchialData == 1)
					{
						JsonObject queryInfo = elementInfoObj.get("subSequentLevelQueryInfo").getAsJsonObject();
						JsonObject nextLevelTableInfo = getNextLevelAndLeafNodeTableDataRows(organisationSession,layoutName, elementInfoObj, queryOutputRecordInfoObj, URLParameterNameAndValuesList, rowHTML, dataFieldsList, tablesList, tableDataName, queryInfo, 0, parentRowDataParameterBindingsList);
						if (nextLevelTableInfo.get("success").getAsInt() != 1)
						{
							return nextLevelTableInfo;
						}						
						nextLevelTableRowsDataHTML = nextLevelTableInfo.get("rowsHMTL").getAsString();
					}
					if(enableLeafNodes == 1)
					{
						JsonObject queryInfo = elementInfoObj.get("leafNodeQueryInfo").getAsJsonObject();
						JsonObject leafNodeTableInfo = getNextLevelAndLeafNodeTableDataRows(organisationSession,layoutName, elementInfoObj, queryOutputRecordInfoObj, URLParameterNameAndValuesList, rowHTML, dataFieldsList, tablesList, tableDataName, queryInfo, 1, parentRowDataParameterBindingsList);
						if (leafNodeTableInfo.get("success").getAsInt() != 1)
						{
							return leafNodeTableInfo;
						}						
						leafNodeTableRowsDataHTML = leafNodeTableInfo.get("rowsHMTL").getAsString();
					}
					rowsDataHTML += nextLevelTableRowsDataHTML;
					rowsDataHTML += leafNodeTableRowsDataHTML;
				}
			}			
			String dataTableWithDataHTML = textBeforeTrTag + rowsDataHTML + textAfterTrTag;
			dataObject.addProperty("success", 1);
			dataObject.addProperty("dataTableWithDataHTML", dataTableWithDataHTML);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParserUtil.class, e);
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("Data table rows data html could not be retrieved.");
		}
		finally
		{
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Data table rows data html could not be retrieved.");
		return dataObject;
	}
	 public static JsonObject getNextLevelAndLeafNodeTableDataRows(Session organisationSession,String layoutName, JsonObject elementInfoObj, JsonObject queryOutputRecordInfoObj, JsonArray URLParameterNameAndValuesList, String rowHTML, JsonArray dataFieldsList, JsonArray  tablesList, String tableDataName, JsonObject queryInfo, int isLeafNodeTable, JsonArray parentRowDataParameterBindingsList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String rowsHMTL = "";
			JsonArray whereClausesList = new JsonArray();
			JsonArray parameterBindingsList = new JsonArray();
			JsonArray nextLevelParentRowDataParameterBindingsList = new JsonArray();
			String queryCode = "";
			if(queryInfo.has("queryCode"))
			{
				queryCode = queryInfo.get("queryCode").getAsString();
			}
			if(queryCode.trim().length()==0)
			{
				dataObject.addProperty("success", 1);
				dataObject.addProperty("rowsHMTL", rowsHMTL);
				return dataObject;	
			}
			if (queryInfo.has("whereClausesList"))
			{
				whereClausesList = queryInfo.get("whereClausesList").getAsJsonArray();
			}			
			if (queryInfo.has("parameterBindingsList"))
			{
				parameterBindingsList = queryInfo.get("parameterBindingsList").getAsJsonArray();
			}
			if (queryInfo.has("parentRowDataParameterBindingsList"))
			{
				nextLevelParentRowDataParameterBindingsList = queryInfo.get("parentRowDataParameterBindingsList").getAsJsonArray();
			}			
			getURLParemeterNameAndValueFromParentRow(URLParameterNameAndValuesList, queryOutputRecordInfoObj, parentRowDataParameterBindingsList);
			JsonObject parameterNameAndValueMapObj = getQueryParameterNameAndValueslist(parameterBindingsList, URLParameterNameAndValuesList);
			if (parameterNameAndValueMapObj.get("success").getAsInt() != 1)
			{
				return parameterNameAndValueMapObj;
			}			
			JsonArray queryParameterNameAndValueslist = parameterNameAndValueMapObj.get("queryParameterNameAndValueslist").getAsJsonArray();
			JsonObject queryOutputObj = getQueryOutputNew(organisationSession, queryCode, whereClausesList, queryParameterNameAndValueslist);
			if (queryOutputObj.get("success").getAsInt() != 1)
			{
				return queryOutputObj;
			}			
			JsonArray queryOutputRecordsList = queryOutputObj.get("queryOutputRecordsList").getAsJsonArray();								
			for(int i = 0;i< queryOutputRecordsList.size();i++)
			{
				JsonObject outputRecordColumnsListInfoObj = queryOutputRecordsList.get(i).getAsJsonObject();
				JsonArray outputRecordColumnsList = outputRecordColumnsListInfoObj.get("outputRecordColumnsList").getAsJsonArray();				
				JsonObject outputRecordInfoObj = new JsonObject();
				for (int j = 0; j < outputRecordColumnsList.size(); j++)
				{
					JsonObject queryOutputRecordColumnInfo = outputRecordColumnsList.get(j).getAsJsonObject();
					String columnAlias = queryOutputRecordColumnInfo.get("columnAlias").getAsString();
					String columnValue = "";
					if (queryOutputRecordColumnInfo.has("columnValue") && queryOutputRecordColumnInfo.get("columnValue").isJsonNull() == false)
					{
						columnValue = queryOutputRecordColumnInfo.get("columnValue").getAsString();
					}
					outputRecordInfoObj.addProperty(columnAlias, columnValue);
				}					
				JsonObject dataTableRowDataInfoObj = getDataTableDataRowHTML(organisationSession, layoutName, outputRecordInfoObj, dataFieldsList, rowHTML, tablesList, tableDataName, isLeafNodeTable, URLParameterNameAndValuesList, nextLevelParentRowDataParameterBindingsList);
				if (dataTableRowDataInfoObj.get("success").getAsInt() != 1)
				{
					return dataTableRowDataInfoObj;
				}
				String rowDataHTML = dataTableRowDataInfoObj.get("rowDataHTML").getAsString();
				rowsHMTL += rowDataHTML;
				if(isLeafNodeTable == 1)
				{
					dataObject.addProperty("success", 1);
					dataObject.addProperty("rowsHMTL", rowsHMTL);
					return dataObject;					
				}
				//Sub Groups
				JsonObject subSequentLevelQueryInfo = elementInfoObj.get("subSequentLevelQueryInfo").getAsJsonObject();
				JsonObject nextLevelTableInfo = getNextLevelAndLeafNodeTableDataRows(organisationSession, layoutName, elementInfoObj, outputRecordInfoObj, URLParameterNameAndValuesList, rowHTML, dataFieldsList, tablesList, tableDataName, subSequentLevelQueryInfo, 0, nextLevelParentRowDataParameterBindingsList);
				if (nextLevelTableInfo.get("success").getAsInt() != 1)
				{
					return nextLevelTableInfo;
				}						
				String nextLevelTableRowsDataHTML = nextLevelTableInfo.get("rowsHMTL").getAsString();
				rowsHMTL += nextLevelTableRowsDataHTML;
				JsonObject elementPropertiesInfo = elementInfoObj.get("elementPropertiesInfo").getAsJsonObject();
				int enableLeafNodes = elementPropertiesInfo.get("enableLeafNodes").getAsInt();
				if(enableLeafNodes == 1)
				{
					JsonObject leafNodeQueryInfo = elementInfoObj.get("leafNodeQueryInfo").getAsJsonObject();
					JsonObject leafNodeTableInfo = getNextLevelAndLeafNodeTableDataRows(organisationSession, layoutName, elementInfoObj, outputRecordInfoObj, URLParameterNameAndValuesList, rowHTML, dataFieldsList, tablesList, tableDataName, leafNodeQueryInfo, 1, nextLevelParentRowDataParameterBindingsList);
					if (leafNodeTableInfo.get("success").getAsInt() != 1)
					{
						return leafNodeTableInfo;
					}						
					String leafNodeTableRowsDataHTML = leafNodeTableInfo.get("rowsHMTL").getAsString();
					rowsHMTL += leafNodeTableRowsDataHTML;
				}
			}
			dataObject.addProperty("success", 1);
			dataObject.addProperty("rowsHMTL", rowsHMTL);
			return dataObject;					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "sub level table rows html could not be built.");
		return dataObject;
	}
	 public static void getURLParemeterNameAndValueFromParentRow(JsonArray uRLParameterNameAndValuesList, JsonObject queryOutputRecordInfoObj, JsonArray parentRowDataParameterBindingsList)
	{
		try
		{
			for(int i = 0;i< parentRowDataParameterBindingsList.size();i++)
			{
				JsonObject parentRowDataParamsInfoObj = parentRowDataParameterBindingsList.get(i).getAsJsonObject();
				String parameterName = parentRowDataParamsInfoObj.get("parameterName").getAsString();
				String queryColumnId = parentRowDataParamsInfoObj.get("queryColumnId").getAsString();
				String parameterValue = queryOutputRecordInfoObj.get(queryColumnId).getAsString();
				int isParameterAlreadyExisted =0;
				for(int j = 0;j< uRLParameterNameAndValuesList.size();j++)
				{
					JsonObject urlParameterAndValueInfo = uRLParameterNameAndValuesList.get(j).getAsJsonObject();
					String urlParameterName = urlParameterAndValueInfo.get("parameterName").getAsString();
					if(parameterName.equalsIgnoreCase(urlParameterName))
					{
						urlParameterAndValueInfo.addProperty("parameterValue", parameterValue);
						isParameterAlreadyExisted=1;
					}
				}
				if(isParameterAlreadyExisted==0)
				{
					JsonObject parameterNameAndValueInfoObj = new JsonObject();
					parameterNameAndValueInfoObj.addProperty("parameterName", parameterName);
					parameterNameAndValueInfoObj.addProperty("parameterValue", parameterValue);	
					uRLParameterNameAndValuesList.add(parameterNameAndValueInfoObj);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	 public static JsonObject getDataTableDataRowHTML(Session organisationSession, String layoutName, JsonObject dataTableRowDataObj, JsonArray dataFieldsList, String rowHTML, JsonArray tablesList, String tableDataName, int isLeafNodeTable, JsonArray URLParameterNameAndValuesList, JsonArray parentRowDataParameterBindingsList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			getDataTableCustomData(dataTableRowDataObj, tableDataName, layoutName);
			for(int i = 0;i< tablesList.size();i++)
			{
				JsonObject childDataTableInfo = tablesList.get(i).getAsJsonObject();
				JsonObject elementPropertiesInfo = childDataTableInfo.get("elementPropertiesInfo").getAsJsonObject();
				String parentTableDataName = elementPropertiesInfo.get("parentTableDataName").getAsString();
				String childTableDataName = elementPropertiesInfo.get("tableDataName").getAsString();				
				if(parentTableDataName.equalsIgnoreCase(tableDataName))
				{
					String dataTableHTML = childDataTableInfo.get("dataTableHTML").getAsString();
					if(rowHTML.contains(dataTableHTML))
					{											
						getURLParemeterNameAndValueFromParentRow(URLParameterNameAndValuesList, dataTableRowDataObj, parentRowDataParameterBindingsList);
						JsonObject dataTableWithDataInfoObj =getDataTableWithData(organisationSession, layoutName, childDataTableInfo, URLParameterNameAndValuesList, dataFieldsList, dataTableHTML, tablesList, childTableDataName);
						if(dataTableWithDataInfoObj.get("success").getAsInt()!=1)
						{
							return dataTableWithDataInfoObj;
						}
						String dataTableWithDataHTML = dataTableWithDataInfoObj.get("dataTableWithDataHTML").getAsString();
						rowHTML = rowHTML.replace(dataTableHTML, dataTableWithDataHTML);
					}
				}
			}
			rowHTML = processDataTableDataRowHTMLWithDataFields(rowHTML, tableDataName, dataFieldsList, dataTableRowDataObj, isLeafNodeTable);		
			dataObject.addProperty("success", 1);
			dataObject.addProperty("rowDataHTML", rowHTML);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParserUtil.class, e);
			logger.error(VWTCoreCommonUtil.getStackTrace(e));
			logger.debug("Data table row html could not be fetched.");
		}
		finally
		{
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Data table row data html information could not be retrieved.");
		return dataObject;
	}
	 public static void getDataTableCustomData(JsonObject dataTableRowDataObj, String tableDataName, String layoutName)
	{
		try
		{
			//ReportRequestHandler.processQueryResultItemDataObject(null, dataTableRowDataObj, tableDataName, layoutName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}						
	}
	 public static String processDataTableDataRowHTMLWithDataFields(String rowHTML, String tableDataName, JsonArray dataFieldsList, JsonObject dataTableRowDataObj, int isLeafNodeTable)
	{
		for (int i = 0; i < dataFieldsList.size(); i++)
		{
			JsonObject dataFieldInfoObj = dataFieldsList.get(i).getAsJsonObject();
			JsonObject elementPropertiesInfo = dataFieldInfoObj.get("elementPropertiesInfo").getAsJsonObject();
			String dataTableDataName = dataFieldInfoObj.get("tableDataName").getAsString();
			String fieldAliasName = dataFieldInfoObj.get("fieldAliasName").getAsString();
			if(isLeafNodeTable == 1)
			{
				fieldAliasName = dataFieldInfoObj.get("leafNodeQueryColumnAlias").getAsString();
			}
			String fieldDataName = dataFieldInfoObj.get("fieldDataName").getAsString();			
			int dataFieldBoundToTable = elementPropertiesInfo.get("dataFieldBoundToTable").getAsInt();
			if(!tableDataName.equalsIgnoreCase(dataTableDataName))
			{
				continue;
			}
			if(dataFieldBoundToTable!=1)
			{
				continue;
			}
			String dataFieldOutputText = dataTableRowDataObj.get(fieldAliasName).getAsString();
			if (dataFieldOutputText.length() > 0)
			{
				dataFieldOutputText = VWTCoreCommonUtil.escapeHTMLText(dataFieldOutputText);
			}			
			int indexOfDataFieldDataName = rowHTML.indexOf("\""+fieldDataName+"\"");//pattern = "stateName"
			String rowHTMLBeforeDataFieldDataName = rowHTML.substring(0, indexOfDataFieldDataName );
			String rowHTMLFromDataFieldDataName = rowHTML.substring(indexOfDataFieldDataName);
			int indexOfClosedAngleBracket = rowHTMLFromDataFieldDataName.indexOf(">");
			String textUntilClosedAngleBracket = rowHTMLFromDataFieldDataName.substring(0, indexOfClosedAngleBracket + 1);
			String textAfterClosedAngleBracket = rowHTMLFromDataFieldDataName.substring(indexOfClosedAngleBracket + 1);			
			rowHTMLFromDataFieldDataName = textUntilClosedAngleBracket + dataFieldOutputText + textAfterClosedAngleBracket;
			rowHTML = rowHTMLBeforeDataFieldDataName + rowHTMLFromDataFieldDataName;			
		}
		return rowHTML;
	}
	public static String getLayoutHTMLFilePath(String layoutName)
	{
		String layoutHTMLFilePath = "/Metadata/CustomLayouts/Html/"+layoutName+".html";
		return layoutHTMLFilePath;
	}
	public static String getUseNativeQueryStatus(Session organisationSession, String queryCode)
	{
		String useNativeQuery = "No";
		String selectQueryString = "select qi.useNativeQuery as qi_useNativeQuery  from QueryInfo qi where queryCode = ? ";
        Query query = organisationSession.createQuery(selectQueryString);        
        int parasmPosIndex = 0;
        query.setParameter(parasmPosIndex, queryCode);        
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
	       	String qi_useNativeQuery = String.valueOf(resultRowColumnsList[0]);
        	if(qi_useNativeQuery.equals("null"))
        	{
        		qi_useNativeQuery  = "No";
        	}
        	useNativeQuery = qi_useNativeQuery;
        }
		return useNativeQuery;
	}
}
