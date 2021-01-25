package com.patientapp.util.layout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.patientapp.util.layout.LayoutParser;
import com.patientapp.util.VWTCoreCommonUtil;
public class LayoutParser
{
	private static final Logger logger = Logger.getLogger(LayoutParser.class);		
	public static HashMap<String, JsonObject> htmlLayoutQueriesMetadataMap = new HashMap<String, JsonObject>();
	public static HashMap<String, JsonObject> htmlLayoutComponentMetadataMap = new HashMap<String, JsonObject>();
	public static HashMap<String, JsonObject> htmlLayoutDataFieldsMetadataMap = new HashMap<String, JsonObject>();	
	public static HashMap<String, JsonObject> tagAttributesMap = new HashMap<String, JsonObject>();
	public static JsonObject DATA_FIELDS_TOKENS_OBJ = new JsonObject();
	static 
	{
		//Anchor Tag href
		JsonObject achorTagObj = new JsonObject();
		JsonArray anchorTagAttibutesList = new JsonArray();
		JsonObject anchorTagAttibuteInfo = new JsonObject();
		anchorTagAttibuteInfo.addProperty("attributeName", "href");
		anchorTagAttibutesList.add(anchorTagAttibuteInfo);		
		achorTagObj.add("attributesList", anchorTagAttibutesList);		
		tagAttributesMap.put("a", achorTagObj);
		//Img Tag src
		JsonObject imgTagObj = new JsonObject();
		JsonArray imgTagAttibutesList = new JsonArray();
		JsonObject imgTagAttibuteInfo = new JsonObject();
		imgTagAttibuteInfo.addProperty("attributeName", "src");
		imgTagAttibutesList.add(imgTagAttibuteInfo);		
		imgTagObj.add("attributesList", imgTagAttibutesList);		
		tagAttributesMap.put("img", imgTagObj);
		//Td Tag
		JsonObject tdTagObj = new JsonObject();
		JsonArray tdTagAttibutesList = new JsonArray();
		JsonObject tdTagAttibuteInfo = new JsonObject();
		tdTagAttibuteInfo.addProperty("attributeName", "align");
		tdTagAttibuteInfo.addProperty("attributeName", "valign");
		tdTagAttibuteInfo.addProperty("attributeName", "width");
		tdTagAttibuteInfo.addProperty("attributeName", "height");
		tdTagAttibuteInfo.addProperty("attributeName", "bgcolor");
		tdTagAttibutesList.add(tdTagAttibuteInfo);		
		tdTagObj.add("attributesList", tdTagAttibutesList);		
		tagAttributesMap.put("td", tdTagObj);
	}
public static JsonObject getJsonFromHTML(String layoutName, String searchParameterListText)throws Exception
	{
		Element bodyElement = getBodyElementFromFile(layoutName);
		JsonObject bodyInfoObj = getHTMLTagHierarchy(bodyElement, layoutName);		
		JsonArray ChildElements = new JsonArray();		
		ChildElements.add(bodyInfoObj);
		JsonArray layoutQueriesList =  new JsonArray();
		getLayoutQueries(layoutName, layoutQueriesList, bodyElement);
		JsonArray URLParametersList =  new JsonArray();		
		getURLParametersList(layoutName, searchParameterListText, URLParametersList);
		JsonObject htmlLayoutDataObj = new JsonObject();
		htmlLayoutDataObj.add("layoutQueriesList", layoutQueriesList);
		htmlLayoutDataObj.add("URLParametersList", URLParametersList);
		htmlLayoutDataObj.add("ChildElements", ChildElements);
		htmlLayoutDataObj.add("dataFieldsTokensObj", DATA_FIELDS_TOKENS_OBJ);	
		DATA_FIELDS_TOKENS_OBJ = new JsonObject();
		return htmlLayoutDataObj;
	}
private static Element getBodyElementFromFile(String layoutName)throws Exception
{
	String layoutHTMLFilePathString = LayoutParserUtil.getLayoutHTMLFilePath(layoutName);
	URL url = LayoutParser.class.getResource(layoutHTMLFilePathString);		
	if(url ==  null)
	{
		System.err.println("HTML layout file could not be found where file name is " + layoutName + ".html");
	}
	String filePath = url.getPath();						
	String htmlFileContent = getFileContent(filePath);			
	Document doc = getDocumentParser(htmlFileContent);	
	NodeList bodyTags = doc.getElementsByTagName("body");
	Node  bodyTag = bodyTags.item(0);		
	Element bodyElement = (Element)bodyTag;
	return bodyElement;
}
public static Document getDocumentParser(String htmlFileContent)
{
	try
	{
		String xmlContentStandard = "<?xml version=\"1.0\"?> " + htmlFileContent;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(xmlContentStandard);
		ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
		Document doc = dBuilder.parse(input);
		doc.getDocumentElement().normalize();
		return doc;
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return null;
}
public static JsonObject getHTMLTagHierarchy(Element element, String layoutName)
{
	JsonArray htmlTagChildElementsList = new JsonArray();
	NodeList elementChildElements = element.getChildNodes();
	for (int i = 0; i < elementChildElements.getLength(); i++)
	{
		Node childNode = elementChildElements.item(i);
		if (childNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element childElement = (Element) childNode;
			JsonObject childInfoObj = getHTMLTagHierarchy(childElement, layoutName);
			htmlTagChildElementsList.add(childInfoObj);
		}
		else
		{
			String nodeNameString = childNode.getNodeName();
			if(nodeNameString.equalsIgnoreCase("#text"))
			{
				String textTagText = childNode.getTextContent();
				//System.out.println(textTagText);		
				if(textTagText.length()==0)
				{
					continue;
				}
				JsonObject elementInfoObj = new JsonObject();
				JsonObject elementPropertiesInfo = new JsonObject();					
				JsonArray htmlTagCSSPropertysList = new JsonArray();
				htmlTagCSSPropertysList = getTagCSSPropertiesList(element);			
				elementPropertiesInfo.addProperty("htmlTagName", "");
				elementPropertiesInfo.addProperty("htmlTagType", "text");
				elementPropertiesInfo.addProperty("htmlTagId", "");
				elementPropertiesInfo.addProperty("componentType", "");
				elementPropertiesInfo.addProperty("textTagText", textTagText);
				elementInfoObj.addProperty("elementType", "HTML_TAG");
				elementInfoObj.add("htmlTagChildElementsList", new JsonArray());
				elementInfoObj.add("elementPropertiesInfo", elementPropertiesInfo);
				elementInfoObj.add("htmlTagCSSPropertysList", htmlTagCSSPropertysList);
				elementInfoObj.add("queryInfo", new JsonObject());
				elementInfoObj.add("graphQueryColumnsList", new JsonArray());					
				htmlTagChildElementsList.add(elementInfoObj);
			}
		}
	}		
	return getElementInfoObject(element, layoutName, htmlTagChildElementsList);	
}
private static JsonObject getElementInfoObject(Element element, String layoutName, JsonArray htmlTagChildElementsList)
{
	String htmlTagType = element.getTagName().toLowerCase();
	String componentType = getComponentType(element);
	String htmlTagId = element.getAttribute("id");	
	String dataName = element.getAttribute("data-name");
	String classAttributeText = element.getAttribute("class");
	JsonObject elementInfoObj = new JsonObject();
	JsonObject elementPropertiesInfo = new JsonObject();
	JsonObject elementAttributesInfo = getElementAttributesInfo(element, layoutName);
	elementInfoObj.add("elementAttributesInfo", elementAttributesInfo);
	JsonArray htmlTagCSSPropertysList = new JsonArray();
	htmlTagCSSPropertysList = getTagCSSPropertiesList(element);	
	elementPropertiesInfo.addProperty("htmlTagName", dataName);
	elementPropertiesInfo.addProperty("htmlTagType", htmlTagType);
	elementPropertiesInfo.addProperty("htmlTagId", htmlTagId);
	elementPropertiesInfo.addProperty("componentType", componentType);
	elementPropertiesInfo.addProperty("classAttributeText", classAttributeText);
	JsonArray graphQueryColumnsList = new JsonArray();	
	if(componentType.equalsIgnoreCase("DATA_FIELD"))
	{
		addDataFieldElementProperties(elementPropertiesInfo, element, layoutName);			
	}
	else if(componentType.equalsIgnoreCase("DATA_TABLE"))
	{
		addDataTableElementProperties(elementPropertiesInfo, element);	
		String subSequentLevelQueryInfoString = element.getAttribute("data-sub-sequent-level-query-info").trim();
		JsonObject subSequentLevelQueryInfo = new JsonObject();
		if(elementPropertiesInfo.get("enableHierarchialData").getAsString().equalsIgnoreCase("1"))
		{
			if(subSequentLevelQueryInfoString.length()>0)
			{
				subSequentLevelQueryInfo = new Gson().fromJson(subSequentLevelQueryInfoString, JsonObject.class);
			}
			else
			{
				subSequentLevelQueryInfo = getQueryInfoBasedOnComponentTypeFromXLSFile("hierarchy", dataName, layoutName);
			}	
		}			
		elementInfoObj.add("subSequentLevelQueryInfo", subSequentLevelQueryInfo);
		//Leaf Node Query Info
		String leafNodeQueryInfoString = element.getAttribute("data-leaf-node-query").trim();
		JsonObject leafNodeQueryInfo = new JsonObject();
		if(elementPropertiesInfo.get("enableLeafNodes").getAsString().equalsIgnoreCase("1"))
		{
			if(leafNodeQueryInfoString.length()>0)
			{
				leafNodeQueryInfo = new Gson().fromJson(leafNodeQueryInfoString, JsonObject.class);
			}
			else
			{
				leafNodeQueryInfo = getQueryInfoBasedOnComponentTypeFromXLSFile("leafnode", dataName, layoutName);
			}
		}
		elementInfoObj.add("leafNodeQueryInfo", leafNodeQueryInfo);
	}
	else if(componentType.equalsIgnoreCase("GRAPH"))
	{
		addGraphElementProperties(elementPropertiesInfo, element);		
		graphQueryColumnsList = getGraphQueryColumnsList(element);
	}
	else if(componentType.equalsIgnoreCase("SEARCH_FORM"))
	{
		addSearchFormElementProperties(elementPropertiesInfo, element);		
	}	
	else if(componentType.equalsIgnoreCase("SEARCH_FIELD"))
	{
		addSearchFieldElementProperties(elementPropertiesInfo, element);		
	}
	else if(componentType.equalsIgnoreCase("SEARCH_BUTTON"))
	{
		addSearchButtonElementProperties(elementPropertiesInfo, element);		
	}
	String queryInfoString = element.getAttribute("data-query-info").trim();
	JsonObject queryInfo = new JsonObject();
	if(queryInfoString.length()>0)
	{
		queryInfo = new Gson().fromJson(queryInfoString, JsonObject.class);
	}
	else
	{
		queryInfo = getQueryInfoBasedOnComponentTypeFromXLSFile(componentType, dataName, layoutName);
	}		
	elementInfoObj.addProperty("elementType", "HTML_TAG");
	elementInfoObj.add("htmlTagChildElementsList", htmlTagChildElementsList);
	elementInfoObj.add("elementPropertiesInfo", elementPropertiesInfo);
	elementInfoObj.add("htmlTagCSSPropertysList", htmlTagCSSPropertysList);
	elementInfoObj.add("queryInfo", queryInfo);
	elementInfoObj.add("graphQueryColumnsList", graphQueryColumnsList);	
	return elementInfoObj;
}
private static JsonObject getElementAttributesInfo(Element element, String layoutName)
{
	JsonObject elementAttributeInfo = new JsonObject();
	try
	{
		String htmlTagName = element.getTagName();
		if(tagAttributesMap.containsKey(htmlTagName))
		{
			JsonObject tagAtrributeInfo = tagAttributesMap.get(htmlTagName);
			JsonArray attributesList = tagAtrributeInfo.get("attributesList").getAsJsonArray();
			for (int i = 0; i < attributesList.size(); i++)
			{
				JsonObject attributeInfo = attributesList.get(i).getAsJsonObject();
				String attributeName = attributeInfo.get("attributeName").getAsString();
				String attributeValue = element.getAttribute(attributeName);
				if(attributeValue.trim().length()>0)
				{
					elementAttributeInfo.addProperty(attributeName, attributeValue);
				}		
				prepareDataFieldTokensQueryInfoFromText(attributeValue, layoutName, DATA_FIELDS_TOKENS_OBJ);
			}	
		}			
		return elementAttributeInfo;
	}
	catch (Exception e)
	{
		e.printStackTrace();			
	}
	return null;
}
public static int prepareDataFieldTokensQueryInfoFromText(String textToProcess, String layoutName, JsonObject dataFieldTokensQueryInfo)
{
	String[] dataFieldTokens = StringUtils.substringsBetween(textToProcess, "$$DATA_FIELD_", "$$");
	if (dataFieldTokens != null)
	{
		for (int j = 0; j < dataFieldTokens.length; j++)
		{
			String dataFieldDataName = dataFieldTokens[j];														
			JsonObject dataFieldMetadataInfoObj = htmlLayoutDataFieldsMetadataMap.get(layoutName + "_" + dataFieldDataName);
			if(dataFieldMetadataInfoObj == null)
			{
				System.err.println(" Record not existed in LayoutMetadata.xls where Layout Name = " + layoutName + " Data Name = " + dataFieldDataName);
				return 0;
			}		
			String queryCode = dataFieldMetadataInfoObj.get("queryCode").getAsString();
			String bindingType = dataFieldMetadataInfoObj.get("bindingType").getAsString();							
			String overrideWhereClause = dataFieldMetadataInfoObj.get("overrideWhereClause").getAsString();
			String queryColumnAlias = dataFieldMetadataInfoObj.get("queryColumnAlias").getAsString();
			JsonArray whereClausesList = new JsonArray();
			JsonArray parameterBindingsList = new JsonArray();
			if(overrideWhereClause.equalsIgnoreCase("Yes"))
			{			
				whereClausesList = dataFieldMetadataInfoObj.get("whereClausesList").getAsJsonArray();
				parameterBindingsList = dataFieldMetadataInfoObj.get("parameterBindingsList").getAsJsonArray();
			}	
			JsonObject dataFieldInfoObj = new JsonObject();
			dataFieldInfoObj.addProperty("overrideWhereClause", overrideWhereClause);
			dataFieldInfoObj.addProperty("overrideWhereClause", overrideWhereClause);
			dataFieldInfoObj.addProperty("bindingType", bindingType);
			dataFieldInfoObj.addProperty("queryCode", queryCode);
			dataFieldInfoObj.addProperty("queryColumnAlias", queryColumnAlias);
			dataFieldInfoObj.add("whereClausesList", whereClausesList);
			dataFieldInfoObj.add("parameterBindingsList", parameterBindingsList);
			dataFieldTokensQueryInfo.add(dataFieldDataName, dataFieldInfoObj);							
		}
	}
	return 1;
}
private static void addSearchButtonElementProperties(JsonObject elementPropertiesInfo, Element searchButtonElement)
{
	String searchFormName = searchButtonElement.getAttribute("data-search-form-name");
	elementPropertiesInfo.addProperty("searchFormName", searchFormName);
}
private static JsonArray getGraphQueryColumnsList(Element graphElement)
	{
		JsonArray graphQueryColumnsList = new JsonArray();
		String graphType = graphElement.getAttribute("data-graph-type");
		if (graphType.equalsIgnoreCase("BAR_GRAPH") || graphType.equalsIgnoreCase("SCATTER_PLOT_GRAPH"))
		{
			String queryColumnsTextWithCommaSeperated = graphElement.getAttribute("data-query-columns");
			String graphPropertyBindingTextWithCommaSeperated = graphElement.getAttribute("data-graph-property-binding");
			String legendsDescriptionTextWithCommaSeperated = graphElement.getAttribute("data-legends-description");
			String[] queryColumnsTokensList = queryColumnsTextWithCommaSeperated.split(",");
			String[] properyBindingsTokensList = graphPropertyBindingTextWithCommaSeperated.split(",");
			String[] legendsDescriptionTokensList = legendsDescriptionTextWithCommaSeperated.split(",");
			if (queryColumnsTokensList.length != properyBindingsTokensList.length)
			{
				System.err.println("Query columns and property bindings should be in  same length.");
				return null;
			}
			for (int i = 0; i < queryColumnsTokensList.length; i++)
			{
				String graphQueryColumnAlias = queryColumnsTokensList[i].trim();
				String graphQueryColumnMappingTo = properyBindingsTokensList[i].trim();
				String graphQueryColumnLegendDescription = "";
				if (i != 0 && legendsDescriptionTokensList.length >= i)
				{
					graphQueryColumnLegendDescription = legendsDescriptionTokensList[i - 1];
				}
				JsonObject graphQueryColumnInfo = new JsonObject();
				graphQueryColumnInfo.addProperty("graphQueryColumnAlias", graphQueryColumnAlias);
				graphQueryColumnInfo.addProperty("graphQueryColumnDataName", "");
				graphQueryColumnInfo.addProperty("graphQueryColumnMappingTo", graphQueryColumnMappingTo);
				graphQueryColumnInfo.addProperty("graphQueryColumnLegendDescription",graphQueryColumnLegendDescription);
				graphQueryColumnsList.add(graphQueryColumnInfo);
			}
		}
		if(graphType.equalsIgnoreCase("PIE_CHART"))
		{			
			String pieChartDataQueryColumn = graphElement.getAttribute("data-pie-chart-data-column");
			String pieChartLegendQueryColumn = graphElement.getAttribute("data-pie-chart-legend-column");
			JsonObject graphQueryColumnInfo = new JsonObject();
			graphQueryColumnInfo.addProperty("graphQueryColumnAlias", pieChartDataQueryColumn);
			graphQueryColumnInfo.addProperty("graphQueryColumnDataName", "");
			graphQueryColumnInfo.addProperty("graphQueryColumnMappingTo", "PIE_CHART_SECTOR_DATA_COLUMN");
			graphQueryColumnInfo.addProperty("graphQueryColumnLegendDescription","");
			graphQueryColumnsList.add(graphQueryColumnInfo);
			graphQueryColumnInfo = new JsonObject();
			graphQueryColumnInfo.addProperty("graphQueryColumnAlias", pieChartLegendQueryColumn);
			graphQueryColumnInfo.addProperty("graphQueryColumnDataName", "");
			graphQueryColumnInfo.addProperty("graphQueryColumnMappingTo", "LEGEND_DESCRIPTION_COLUMN");
			graphQueryColumnInfo.addProperty("graphQueryColumnLegendDescription","");
			graphQueryColumnsList.add(graphQueryColumnInfo);
		}
		return graphQueryColumnsList;
	}
private static void addSearchFieldElementProperties(JsonObject elementPropertiesInfo, Element searchFieldElement)
{
	String searchFieldParameterName = searchFieldElement.getAttribute("data-parameter-name");			
	String searchFieldType = searchFieldElement.getAttribute("data-search-field-type");		
	String lookUpEntityName = searchFieldElement.getAttribute("data-lookup-entity-name");
	String searchFormName = searchFieldElement.getAttribute("data-search-form-name");
	String isSearchFieldMandatory = searchFieldElement.getAttribute("data-is-search-field-mandatory");
	elementPropertiesInfo.addProperty("searchFormName", searchFormName);
	elementPropertiesInfo.addProperty("isSearchFieldMandatory", isSearchFieldMandatory);
	elementPropertiesInfo.addProperty("searchFieldParameterName", searchFieldParameterName);
	elementPropertiesInfo.addProperty("lookUpEntityName", lookUpEntityName);
	elementPropertiesInfo.addProperty("searchFieldType", searchFieldType);
}
private static void addSearchFormElementProperties(JsonObject elementPropertiesInfo, Element element)
{
}
private static void addGraphElementProperties(JsonObject elementPropertiesInfo, Element graphElement)
{
	String queryCode = graphElement.getAttribute("data-query-code");
	String graphType = graphElement.getAttribute("data-graph-type");
	String svgWidth = graphElement.getAttribute("data-graph-height");
	String svgHeight = graphElement.getAttribute("data-graph-width");	
	String svgGroupMarginLeft = graphElement.getAttribute("data-graph-margin-left");
	String svgGroupMarginTop = graphElement.getAttribute("data-graph-margin-top");
	String svgGroupMarginBottom = graphElement.getAttribute("data-graph-margin-bottom");
	String svgGroupMarginRight = graphElement.getAttribute("data-graph-margin-right");
	String barPaddingLeft = graphElement.getAttribute("data-left-padding");
	String barPaddingRight = graphElement.getAttribute("data-right-padding");
	String enableSplittedBarGraph = graphElement.getAttribute("data-split-bars");
	String graphTicks = graphElement.getAttribute("data-yaxis-ticks-count");
	String pieRadius = graphElement.getAttribute("data-pie-radius");
	String innerPieRadius = graphElement.getAttribute("data-inner-pie-radius");
	String graphSearchFormName = graphElement.getAttribute("data-search-form-name");
	String pieChartDataQueryColumn = graphElement.getAttribute("data-pie-chart-data-column");
	String pieChartLegendQueryColumn = graphElement.getAttribute("data-pie-chart-legend-column");	
	elementPropertiesInfo.addProperty("htmlTagGraphType", graphType);
	elementPropertiesInfo.addProperty("pieChartDataQueryColumn", pieChartDataQueryColumn);
	elementPropertiesInfo.addProperty("pieChartLegendQueryColumn", pieChartLegendQueryColumn);
	if(enableSplittedBarGraph.trim().length()==0)
	{
		enableSplittedBarGraph = "0";
	}	
	elementPropertiesInfo.addProperty("queryCode", queryCode);
	JsonObject propertyObj =  new JsonObject();
	String value = svgWidth;
	String unit = "";
	if(svgWidth.endsWith("px"))
	{
		value = svgWidth.substring(0, svgWidth.length()-2);
		unit = "px";
	}
	if(svgWidth.endsWith("%"))
	{
		value = svgWidth.substring(0, svgWidth.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgWidth", propertyObj);		
	propertyObj =  new JsonObject();
	value = svgHeight;
	unit = "";
	if(svgHeight.endsWith("px"))
	{
		value = svgHeight.substring(0, svgHeight.length()-2);
		unit = "px";
	}
	if(svgHeight.endsWith("%"))
	{
		value = svgHeight.substring(0, svgHeight.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgHeight", propertyObj);
	propertyObj =  new JsonObject();
	value = svgGroupMarginLeft;
	unit = "";
	if(svgGroupMarginLeft.endsWith("px"))
	{
		value = svgGroupMarginLeft.substring(0, svgGroupMarginLeft.length()-2);
		unit = "px";
	}
	if(svgGroupMarginLeft.endsWith("%"))
	{
		value = svgGroupMarginLeft.substring(0, svgGroupMarginLeft.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgGroupMarginLeft", propertyObj);
	propertyObj =  new JsonObject();
	value = svgGroupMarginTop;
	unit = "";
	if(svgGroupMarginTop.endsWith("px"))
	{
		value = svgGroupMarginTop.substring(0, svgGroupMarginTop.length()-2);
		unit = "px";
	}
	if(svgGroupMarginTop.endsWith("%"))
	{
		value = svgGroupMarginTop.substring(0, svgGroupMarginTop.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgGroupMarginTop", propertyObj);
	propertyObj =  new JsonObject();
	value = svgGroupMarginBottom;
	unit = "";
	if(svgGroupMarginBottom.endsWith("px"))
	{
		value = svgGroupMarginBottom.substring(0, svgGroupMarginBottom.length()-2);
		unit = "px";
	}
	if(svgGroupMarginBottom.endsWith("%"))
	{
		value = svgGroupMarginBottom.substring(0, svgGroupMarginBottom.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgGroupMarginBottom", propertyObj);
	propertyObj =  new JsonObject();
	value = svgGroupMarginRight;
	unit = "";
	if(svgGroupMarginRight.endsWith("px"))
	{
		value = svgGroupMarginRight.substring(0, svgGroupMarginRight.length()-2);
		unit = "px";
	}
	if(svgGroupMarginRight.endsWith("%"))
	{
		value = svgGroupMarginRight.substring(0, svgGroupMarginRight.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("svgGroupMarginRight", propertyObj);
	propertyObj =  new JsonObject();
	value = barPaddingLeft;
	unit = "";
	if(barPaddingLeft.endsWith("px"))
	{
		value = barPaddingLeft.substring(0, barPaddingLeft.length()-2);
		unit = "px";
	}
	if(barPaddingLeft.endsWith("%"))
	{
		value = barPaddingLeft.substring(0, barPaddingLeft.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("barPaddingLeft", propertyObj);
	propertyObj =  new JsonObject();
	value = barPaddingRight;
	unit = "";
	if(barPaddingRight.endsWith("px"))
	{
		value = barPaddingRight.substring(0, barPaddingRight.length()-2);
		unit = "px";
	}
	if(barPaddingRight.endsWith("%"))
	{
		value = barPaddingRight.substring(0, barPaddingRight.length()-1);
		unit = "%";
	}
	propertyObj.addProperty("value", value);
	propertyObj.addProperty("unit", unit);
	elementPropertiesInfo.add("barPaddingRight", propertyObj);
	propertyObj =  new JsonObject();
	propertyObj.addProperty("value", "");
	propertyObj.addProperty("unit", "");
	elementPropertiesInfo.add("funnelTipHeight", propertyObj);
	propertyObj =  new JsonObject();
	propertyObj.addProperty("value", "");
	propertyObj.addProperty("unit", "");
	elementPropertiesInfo.add("funnelTipWidth", propertyObj);
	propertyObj =  new JsonObject();
	propertyObj.addProperty("value", "");
	propertyObj.addProperty("unit", "");
	elementPropertiesInfo.add("progressBarHeight", propertyObj);
	elementPropertiesInfo.addProperty("enableSplittedBarGraph", enableSplittedBarGraph);
	elementPropertiesInfo.addProperty("graphTicks", graphTicks);
	elementPropertiesInfo.addProperty("pieRadius", pieRadius);
	elementPropertiesInfo.addProperty("innerPieRadius", innerPieRadius);
	elementPropertiesInfo.addProperty("searchFormName", graphSearchFormName);
}
private static void addDataTableElementProperties(JsonObject elementPropertiesInfo, Element dataTableElement)
{
	String tableName = dataTableElement.getAttribute("data-name");
	String searchFormName = dataTableElement.getAttribute("data-search-form-name");
	String queryCode = dataTableElement.getAttribute("data-query-code");
	String enablePaginationInTableLevel = dataTableElement.getAttribute("data-enable-pagination");
	String leafNodeEntityName = dataTableElement.getAttribute("data-leaf-node-entity-name");
	String enableHierarchialData = dataTableElement.getAttribute("data-enable-hierarchial-data").trim();
	if(enableHierarchialData.length()==0)
	{
		enableHierarchialData = "0";
	}
	String enableLeafNodes = dataTableElement.getAttribute("data-enable-leaf-nodes").trim();
	if(enableLeafNodes.length()==0)
	{
		enableLeafNodes = "0";
	}
	elementPropertiesInfo.addProperty("tableDataName", tableName);
	elementPropertiesInfo.addProperty("leafNodeEntityName", leafNodeEntityName);
	elementPropertiesInfo.addProperty("searchFormName", searchFormName);
	elementPropertiesInfo.addProperty("queryCode", queryCode);
	elementPropertiesInfo.addProperty("enableHierarchialData", enableHierarchialData);
	elementPropertiesInfo.addProperty("enableLeafNodes", enableLeafNodes);
	elementPropertiesInfo.addProperty("enablePaginationInTableLevel", enablePaginationInTableLevel);
}
private static void addDataFieldElementProperties(JsonObject elementPropertiesInfo, Element dataFieldElement, String layoutName)
{	
	String fieldAliasName = dataFieldElement.getAttribute("data-query-column-alias");		
	String searchFormName = dataFieldElement.getAttribute("data-search-form-name");
	String queryCode = dataFieldElement.getAttribute("data-query-code");
	String dataFieldDataName = dataFieldElement.getAttribute("data-name");
	String overrideWhereClause = dataFieldElement.getAttribute("data-override-where-clause");
	String dataFieldBoundToTable = dataFieldElement.getAttribute("data-bind-to-table");
	String dataBoundTableName = dataFieldElement.getAttribute("data-table-name");
	String dataFieldBindType = dataFieldElement.getAttribute("data-data-field-bind-type");
	String leafNodeQueryColumnAlias = dataFieldElement.getAttribute("data-leaf-node-query-column-alias");
	elementPropertiesInfo.addProperty("overrideWhereClause", overrideWhereClause);
	elementPropertiesInfo.addProperty("dataFieldBoundToTable", dataFieldBoundToTable);
	elementPropertiesInfo.addProperty("searchFormName", searchFormName);
	elementPropertiesInfo.addProperty("queryCode", queryCode);
	elementPropertiesInfo.addProperty("queryColumnId", fieldAliasName);
	elementPropertiesInfo.addProperty("dataFieldDataTableTagName", dataBoundTableName);
	elementPropertiesInfo.addProperty("dataFieldBindType", dataFieldBindType);
	elementPropertiesInfo.addProperty("leafNodeQueryColumnId", leafNodeQueryColumnAlias);
	String leafNodeEntityName = "";
	String leafNodeQueryCode = "";
	if(leafNodeQueryColumnAlias.trim().length() > 0)
	{
		JsonObject dataFieldTableInfo = getDataFieldTableInfo(dataFieldElement, dataBoundTableName, layoutName);
		if(dataFieldTableInfo == null)
		{
			System.err.println("Data field data table information could not be retrieved where layout name = " + layoutName + " data field name " + dataFieldDataName);
		}	
		leafNodeEntityName = dataFieldTableInfo.get("leafNodeEntityName").getAsString();//From Table Info
		leafNodeQueryCode = dataFieldTableInfo.get("leafNodeQueryCode").getAsString();//From Table Leaf Node query Info
	}
	elementPropertiesInfo.addProperty("leafNodeEntityName", leafNodeEntityName);
	elementPropertiesInfo.addProperty("leafNodeQueryCode", leafNodeQueryCode);
}
private static JsonObject getDataFieldTableInfo(Element dataFieldElement, String dataTableDataName, String layoutName)
{	
	try
	{
		Element bodyElement = getBodyElementFromFile(layoutName);
		NodeList elementChildElements = bodyElement.getChildNodes();
		for (int i = 0; i < elementChildElements.getLength(); i++)
		{
			Node childNode = elementChildElements.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element childElement = (Element) childNode;
				JsonObject tableInfo =  getTableInfo(childElement, dataTableDataName, layoutName);
				if(tableInfo != null)
				{
					return tableInfo;
				}
			}
		}		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return null;
}
private static JsonObject getTableInfo(Element element, String dataTableDataName, String layoutName)
{
	String dataTableDataNameFromElement = element.getAttribute("data-name");
	String isDataTable = element.getAttribute("data-is-data-table");
	if(dataTableDataNameFromElement.equalsIgnoreCase(dataTableDataName) && isDataTable.equalsIgnoreCase("1")) 
	{
		JsonObject tableInfo = new JsonObject();
		String leafNodeEntityName = element.getAttribute("data-leaf-node-entity-name");		
		String leafNodeQueryInfoString = element.getAttribute("data-leaf-node-query").trim();
		JsonObject leafNodeQueryInfo = new JsonObject();
		if(leafNodeQueryInfoString.length()>0)
		{
			leafNodeQueryInfo = new Gson().fromJson(leafNodeQueryInfoString, JsonObject.class);
		}
		else
		{
			leafNodeQueryInfo = getQueryInfoBasedOnComponentTypeFromXLSFile("leafnode", dataTableDataName, layoutName);
		}		
		String leafNodeQueryCode = leafNodeQueryInfo.get("queryCode").getAsString();
		tableInfo.addProperty("leafNodeEntityName", leafNodeEntityName);
		tableInfo.addProperty("leafNodeQueryCode", leafNodeQueryCode);
		return tableInfo;
	}
	else
	{
		NodeList elementChildElements = element.getChildNodes();
		for (int i = 0; i < elementChildElements.getLength(); i++)
		{
			Node childNode = elementChildElements.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element childElement = (Element) childNode;
				getTableInfo(childElement, dataTableDataName, layoutName);						
			}
		}		
	}
	return null;
}
private static JsonObject getQueryInfoBasedOnComponentTypeFromXLSFile(String componentType, String dataName, String layoutName)
{
	JsonObject queryInfo = new JsonObject();
	String queryCode = "";
	JsonArray whereClausesList = new JsonArray();
	JsonArray parameterBindingsList = new JsonArray();
	JsonArray parentRowDataParameterBindingsList = new JsonArray();
	if(componentType.equalsIgnoreCase("DATA_FIELD"))
	{
		JsonObject dataFieldMetadataInfoObj = htmlLayoutDataFieldsMetadataMap.get(layoutName + "_" + dataName);
		if(dataFieldMetadataInfoObj == null)
		{
			System.err.println(" Record not existed in LayoutMetadata.xls where Layout Name = " + layoutName + " Data Name = " + dataName);
		}		
		queryCode = dataFieldMetadataInfoObj.get("queryCode").getAsString();
		String overrideWhereClause = dataFieldMetadataInfoObj.get("overrideWhereClause").getAsString();
		if(overrideWhereClause.equalsIgnoreCase("1"))
		{			
			whereClausesList = dataFieldMetadataInfoObj.get("whereClausesList").getAsJsonArray();
			parameterBindingsList = dataFieldMetadataInfoObj.get("parameterBindingsList").getAsJsonArray();
		}					
	}
	if(componentType.equalsIgnoreCase("DATA_TABLE") || componentType.equalsIgnoreCase("hierarchy") || componentType.equalsIgnoreCase("leafnode"))
	{
		if(componentType.equalsIgnoreCase("DATA_TABLE"))
		{
			componentType = "table";
		}
		JsonObject tableMetadataInfoObj = htmlLayoutComponentMetadataMap.get(layoutName + "_" + dataName + "_" + componentType);
		if(tableMetadataInfoObj == null)
		{
			System.err.println(" Record not existed in LayoutMetadata.xls where Layout Name = " + layoutName + " Data Name = " + dataName + " component type = " + componentType );
		}		
		queryCode = tableMetadataInfoObj.get("queryCode").getAsString();		
		whereClausesList = tableMetadataInfoObj.get("whereClausesList").getAsJsonArray();
		parameterBindingsList = tableMetadataInfoObj.get("parameterBindingsList").getAsJsonArray();
		parentRowDataParameterBindingsList = tableMetadataInfoObj.get("parentRowDataParameterBindingsList").getAsJsonArray();
	}
	if(componentType.equalsIgnoreCase("GRAPH"))
	{
		JsonObject graphMetadataInfoObj = htmlLayoutComponentMetadataMap.get(layoutName + "_" + dataName +  "_" + "graph");
		if(graphMetadataInfoObj == null)
		{
			System.err.println(" Record not existed in LayoutMetadata.xls where Layout Name = " + layoutName + " Data Name = " + dataName);
		}				
		whereClausesList = graphMetadataInfoObj.get("whereClausesList").getAsJsonArray();
		parameterBindingsList = graphMetadataInfoObj.get("parameterBindingsList").getAsJsonArray();	
		queryCode = graphMetadataInfoObj.get("queryCode").getAsString();
	}
	queryInfo.addProperty("queryCode", queryCode);
	queryInfo.add("whereClausesList", whereClausesList);
	queryInfo.add("parameterBindingsList", parameterBindingsList);
	queryInfo.add("parentRowDataParameterBindingsList", parentRowDataParameterBindingsList);
	return queryInfo;
}
private static JsonArray getTagCSSPropertiesList(Element element)
{
	JsonArray htmlTagCSSPropertysList = new JsonArray();
	String styleAttribute = element.getAttribute("style");//background-color: #800080; padding: 10px;
	if(styleAttribute.trim().length()>0)
	{
		String[] stylePropTokens = styleAttribute.split(";");
		for(int i=0;i< stylePropTokens.length;i++)
		{
			String stylePropToken = stylePropTokens[i].trim();
			if(stylePropToken.length()==0)
			{
				continue;
			}
			String[] propertyNameAndValueTokens = stylePropToken.split(":");
			String cssProperyName = propertyNameAndValueTokens[0];
			String cssProperyValue = propertyNameAndValueTokens[1];
			JsonObject cssPropertyInfoObj = new JsonObject();
			cssPropertyInfoObj.addProperty("cssProperyName", cssProperyName);
			cssPropertyInfoObj.addProperty("cssProperyValue", cssProperyValue);
			htmlTagCSSPropertysList.add(cssPropertyInfoObj);		
		}
	}
	return htmlTagCSSPropertysList;
}
private static String getComponentType(Element element)
{
	String componentType = "";
	String isSearchForm = element.getAttribute("data-is-search-form");
	if(isSearchForm.equalsIgnoreCase("1"))
	{
		componentType ="SEARCH_FORM";
	}
	String isSearchField = element.getAttribute("data-is-search-field");
	if(isSearchField.equalsIgnoreCase("1"))
	{
		componentType ="SEARCH_FIELD";
	}
	String isDataTable = element.getAttribute("data-is-data-table");
	if(isDataTable.equalsIgnoreCase("1"))
	{
		componentType ="DATA_TABLE";
	}
	String isDataField = element.getAttribute("data-is-data-field");
	if(isDataField.equalsIgnoreCase("1"))
	{
		componentType ="DATA_FIELD";
	}
	String isGraphElement = element.getAttribute("data-is-graph");
	if(isGraphElement.equalsIgnoreCase("1"))
	{
		componentType ="GRAPH";
	}
	String isSearchButton = element.getAttribute("data-is-search-button");
	if(isSearchButton.equalsIgnoreCase("1"))
	{
		componentType ="SEARCH_BUTTON";
	}
	return componentType;
}
public static void getURLParametersList(String layoutName, String searchParameterListText, JsonArray URLParametersList)
{
	String[] searchParamsList = searchParameterListText.split(",");
	for (int j = 0; j < searchParamsList.length; j++)
	{
		String parameterName = searchParamsList[j].trim();
		JsonObject parameterInfoObj = new JsonObject();
		parameterInfoObj.addProperty("parameterName", parameterName);
		URLParametersList.add(parameterInfoObj);
	}	
}
public static void getLayoutQueries(String layoutName, JsonArray layoutQueriesList, Element bodyElement)
	{		
		String layoutQueriesListText = bodyElement.getAttribute("data-layout-queries-list");
		if(layoutQueriesListText.trim().length()==0)
		{
			getLayoutQueriesListFromMetadata(layoutName, layoutQueriesList);
		}
		else
		{
			JsonArray layoutQueriesListJsonArray = new Gson().fromJson(layoutQueriesListText, JsonArray.class);
			layoutQueriesList.addAll(layoutQueriesListJsonArray);
		}		
	}
public static void getLayoutQueriesListFromMetadata(String layoutName, JsonArray layoutQueriesList)
{
	Iterator<Map.Entry<String, JsonObject>> itr = htmlLayoutQueriesMetadataMap.entrySet().iterator();
	while (itr.hasNext())
	{
		Map.Entry<String, JsonObject> entry = itr.next();			
		JsonObject layoutQueryMetadataInfoObj = entry.getValue();
		String pageQueryLayoutName = layoutQueryMetadataInfoObj.get("layoutName").getAsString();
		String queryCode = layoutQueryMetadataInfoObj.get("queryCode").getAsString();
		if(layoutName.equalsIgnoreCase(pageQueryLayoutName))
		{
			JsonArray whereClausesList = layoutQueryMetadataInfoObj.get("whereClausesList").getAsJsonArray();
			JsonArray parameterBindingsList = layoutQueryMetadataInfoObj.get("parameterBindingsList").getAsJsonArray();
			JsonObject layoutQueryInfoObj = new JsonObject();
			layoutQueryInfoObj.addProperty("queryCodeDataName", queryCode);
			layoutQueryInfoObj.addProperty("queryCode", queryCode);
			layoutQueryInfoObj.add("whereClausesList", whereClausesList);
			layoutQueryInfoObj.add("parameterBindingsList", parameterBindingsList);
			layoutQueriesList.add(layoutQueryInfoObj);
		}
	}	
}
protected static boolean isExists(String sString)
{
	return (sString != null && sString.trim().length() > 0);
}
public static String getHTMLLayoutFileContent(String layoutName)throws Exception
{
	String layoutHTMLFilePathString = LayoutParserUtil.getLayoutHTMLFilePath(layoutName);
	URL url = LayoutParser.class.getResource(layoutHTMLFilePathString);		
	String filePath = url.getPath();							
	String htmlFileContent = getFileContent(filePath);
	return htmlFileContent;
}
public static void populateHTMLLayoutQueriesList(HSSFRow row, Boolean isItCoreEntity)
{
	int cellIndex = 0;
	String layoutName = row.getCell(cellIndex++).getStringCellValue();
	String queryCode = row.getCell(cellIndex++).getStringCellValue();
	String whereClausesListText = row.getCell(cellIndex++).getStringCellValue();
	String parameterBindingsListText = row.getCell(cellIndex++).getStringCellValue();
	if(whereClausesListText.trim().length()==0)
	{
		whereClausesListText = "[]";
	}
	if(parameterBindingsListText.trim().length()==0)
	{
		parameterBindingsListText = "[]";
	}
	JsonObject layoutInfoObj = new JsonObject();
	layoutInfoObj.addProperty("layoutName", layoutName);
	layoutInfoObj.addProperty("queryCode", queryCode);
	JsonArray whereClausesList = new Gson().fromJson(whereClausesListText, JsonArray.class);
	JsonArray parameterBindingsList = new Gson().fromJson(parameterBindingsListText, JsonArray.class);
	layoutInfoObj.add("whereClausesList", whereClausesList);
	layoutInfoObj.add("parameterBindingsList", parameterBindingsList);		
	htmlLayoutQueriesMetadataMap.put(layoutName + "_" + queryCode, layoutInfoObj);
}
public static void populateHTMLLayoutComponentsList(HSSFRow row, Boolean isItCoreEntity)
{
	int cellIndex = 0;
	String layoutName = row.getCell(cellIndex++).getStringCellValue();
	String componentName = row.getCell(cellIndex++).getStringCellValue();
	String componentType = row.getCell(cellIndex++).getStringCellValue();
	String queryCode = row.getCell(cellIndex++).getStringCellValue();
	String whereClausesListText = row.getCell(cellIndex++).getStringCellValue();
	String parameterBindingsListText = row.getCell(cellIndex++).getStringCellValue();
	String parentRowDataParameterBindingsListText = row.getCell(cellIndex++).getStringCellValue();			
	if(whereClausesListText.length()==0)
	{
		whereClausesListText = "[]";
	}
	if(parameterBindingsListText.length()==0)
	{
		parameterBindingsListText = "[]";
	}
	JsonArray whereClausesList = new Gson().fromJson(whereClausesListText, JsonArray.class);
	JsonArray parameterBindingsList = new Gson().fromJson(parameterBindingsListText, JsonArray.class);
	JsonArray parentRowDataParameterBindingsList = new Gson().fromJson(parentRowDataParameterBindingsListText, JsonArray.class);			
	JsonObject layoutComponentInfoObj = new JsonObject();
	layoutComponentInfoObj.addProperty("layoutName", layoutName);
	layoutComponentInfoObj.addProperty("componentName", componentName);
	layoutComponentInfoObj.addProperty("componentType", componentType);		
	layoutComponentInfoObj.addProperty("queryCode", queryCode);
	layoutComponentInfoObj.add("whereClausesList", whereClausesList);
	layoutComponentInfoObj.add("parameterBindingsList", parameterBindingsList);
	layoutComponentInfoObj.add("parentRowDataParameterBindingsList", parentRowDataParameterBindingsList);
	String componentTypeLowerCase = componentType.toLowerCase();
	htmlLayoutComponentMetadataMap.put(layoutName + "_" + componentName + "_" + componentTypeLowerCase, layoutComponentInfoObj);
}
public static void populateHTMLLayoutDataFieldsList(HSSFRow row, Boolean isItCoreEntity)
	{
		int cellIndex = 0;
		String layoutName = row.getCell(cellIndex++).getStringCellValue();
		String dataFieldName = row.getCell(cellIndex++).getStringCellValue();
		String bindingType = row.getCell(cellIndex++).getStringCellValue();
		String dataBoundTableName = row.getCell(cellIndex++).getStringCellValue();
		String dataSourceType = row.getCell(cellIndex++).getStringCellValue();						
		String queryCode = row.getCell(cellIndex++).getStringCellValue();
		String overrideWhereClause = row.getCell(cellIndex++).getStringCellValue();
		String queryColumnAlias = row.getCell(cellIndex++).getStringCellValue();
		String whereClausesListText = row.getCell(cellIndex++).getStringCellValue();
		String parameterBindingsListText = row.getCell(cellIndex++).getStringCellValue();
		if(whereClausesListText.length()==0)
		{
			whereClausesListText = "[]";
		}
		if(parameterBindingsListText.length()==0)
		{
			parameterBindingsListText = "[]";
		}
		JsonArray whereClausesList = new Gson().fromJson(whereClausesListText, JsonArray.class);
		JsonArray parameterBindingsList = new Gson().fromJson(parameterBindingsListText, JsonArray.class);
		JsonObject layoutDataFieldInfoObj = new JsonObject();
		layoutDataFieldInfoObj.addProperty("layoutName", layoutName);
		layoutDataFieldInfoObj.addProperty("dataFieldName", dataFieldName);
		layoutDataFieldInfoObj.addProperty("queryColumnAlias", queryColumnAlias);		
		layoutDataFieldInfoObj.addProperty("bindingType", bindingType);
		layoutDataFieldInfoObj.addProperty("dataBoundTableName", dataBoundTableName);
		layoutDataFieldInfoObj.addProperty("dataSourceType", dataSourceType);					
		layoutDataFieldInfoObj.addProperty("queryCode", queryCode);
		layoutDataFieldInfoObj.addProperty("overrideWhereClause", overrideWhereClause);
		layoutDataFieldInfoObj.add("whereClausesList", whereClausesList);
		layoutDataFieldInfoObj.add("parameterBindingsList", parameterBindingsList);		
		htmlLayoutDataFieldsMetadataMap.put(layoutName + "_" + dataFieldName, layoutDataFieldInfoObj);
	}
	//HTML From JSON
public static JsonObject getHTMLFromJson(Session organisationSession, String layoutName, JsonObject printFormatDataObject, JsonArray pageQueriesList, JsonArray dataFieldsList, JsonArray tablesList, JsonArray graphsList, JsonArray searchFormsList, JsonArray searchFieldsList,  JsonObject metadataInfo, JsonArray URLParametersList, JsonArray layoutQueriesList, JsonObject fetchDataInfoObj, JsonObject defaultProperties)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			if (printFormatDataObject.has("URLParametersList"))
			{
				URLParametersList = printFormatDataObject.get("URLParametersList").getAsJsonArray();
			}
			if (printFormatDataObject.has("layoutQueriesList"))
			{
				layoutQueriesList = printFormatDataObject.get("layoutQueriesList").getAsJsonArray();
			}
			if (printFormatDataObject.has("defaultProperties"))
			{
				defaultProperties = printFormatDataObject.get("defaultProperties").getAsJsonObject();
			}
			JsonObject dataFieldsTokensObj = new JsonObject();
			if (printFormatDataObject.has("dataFieldsTokensObj"))
			{
				dataFieldsTokensObj = printFormatDataObject.get("dataFieldsTokensObj").getAsJsonObject();
			}
			int fetchData = 0;
			if (fetchDataInfoObj.has("fetchData"))
			{
				fetchData = fetchDataInfoObj.get("fetchData").getAsInt();
			}
			if(fetchData == 1)
			{
				URLParametersList = fetchDataInfoObj.get("URLParameterNameAndValuesList").getAsJsonArray();
			}			
			JsonArray ChildElements = printFormatDataObject.get("ChildElements").getAsJsonArray();
			String rootHTMLWithOutData = "";			
			for (int sectionIndex = 0; sectionIndex < ChildElements.size(); sectionIndex++)
			{
				JsonObject childElementInfoObj = ChildElements.get(sectionIndex).getAsJsonObject();
				JsonObject elementHMTMInfoObj = getElementHTML(organisationSession, layoutName, childElementInfoObj, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, fetchDataInfoObj, dataFieldsTokensObj);
				if (elementHMTMInfoObj.get("success").getAsInt() != 1)
				{
					return elementHMTMInfoObj;
				}
				String elementHTMLWithOutData = elementHMTMInfoObj.get("htmlWithOutData").getAsString();
				rootHTMLWithOutData += elementHTMLWithOutData;
			}
			rootHTMLWithOutData += "";
			dataObject.addProperty("success", 1);
			dataObject.addProperty("rootHTMLWithOutData", rootHTMLWithOutData);
			return dataObject;
		}
		catch (Exception e)
		{
			VWTCoreCommonUtil.writeTolog(LayoutParser.class, e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Print format meta data information could not be retrieved.");
		return dataObject;
	}
public static JsonObject getElementHTML(Session organisationSession, String layoutName, JsonObject elementInfoObj, JsonArray pageQueriesList, JsonArray dataFieldsList, JsonArray tablesList, JsonArray graphsList, JsonArray searchFormsList, JsonArray searchFieldsList, JsonArray URLParametersList, JsonArray layoutQueriesList, JsonObject fetchDataInfoObj, JsonObject dataFieldsTokensObj)
{
	JsonObject dataObject = new JsonObject();	
	try
	{
		String elementType = elementInfoObj.get("elementType").getAsString();
		String htmlWithOutData = "";
		if (elementType.equals("HTML_TAG"))
		{
			return LayoutParserUtil.getHTMLTagHTML(organisationSession, layoutName, elementInfoObj, pageQueriesList, dataFieldsList, tablesList, graphsList, searchFormsList, searchFieldsList, URLParametersList, layoutQueriesList, fetchDataInfoObj, dataFieldsTokensObj);				
		}
		dataObject.addProperty("success", 1);
		dataObject.addProperty("htmlWithOutData", htmlWithOutData);
		return dataObject;
	}
	catch (Exception e)
	{
		VWTCoreCommonUtil.writeTolog(LayoutParser.class, e);
		logger.error(VWTCoreCommonUtil.getStackTrace(e));
		logger.debug("element html code could not be generated.");
	}
	dataObject.addProperty("alert", "element html code could not be generated.");
	dataObject.addProperty("success", 0);
	return dataObject;
}
public static String getFileContent(String fileName) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try
		{
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null)
			{
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			return everything;
		}
		finally
		{
			br.close();
		}
	}
}
