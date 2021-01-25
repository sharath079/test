/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'PropertyName' : 'FormWBEntity:ConfigProperties_propertyName' 
 *	 * 'PropertyValue' : 'FormWBEntity:ConfigProperties_propertyValue' 
 *	
 */
var gInitParamsObjForConfigProperties = {};
var gConfigPropertiesRequestUnderProcess = 0;
function selectThisConfigPropertiesForEdit(configPropertiesRowElement)
{
    var configPropertiesDataObject  = $(configPropertiesRowElement).data('configProperties');
    var configPropertiesList = $('[data-name="configPropertiesSearchResults"]').find('[data-name="configPropertiesRow"]');
	configPropertiesList.data("is-selected", 0);	
	$(configPropertiesRowElement).data("is-selected", 1);
	setConfigPropertiesInViewPage(configPropertiesDataObject);
}

function setConfigPropertiesInViewPage(configPropertiesDataObject, paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var configPropertiesId = configPropertiesDataObject['configPropertiesId'];
	$('#'+prefix+'idValueForConfigProperties').val(configPropertiesId);
		
	//Input
	if(configPropertiesDataObject.hasOwnProperty('propertyName'))
	{
		var propertyName = configPropertiesDataObject['propertyName'];            		
		$('#'+prefix+'ConfigProperties_propertyName').val(propertyName);
	}
	
	//Input
	if(configPropertiesDataObject.hasOwnProperty('propertyValue'))
	{
		var propertyValue = configPropertiesDataObject['propertyValue'];            		
		$('#'+prefix+'ConfigProperties_propertyValue').val(propertyValue);
	}

	if(configPropertiesDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = configPropertiesDataObject['vwTxnStatus'];
		$('[data-name="configPropertiesActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnConfigProperties(this)" >' +configPropertiesDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="configPropertiesActionButtonDiv"]').append(buttonElement);
		$('[data-name="configPropertiesActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="configPropertiesActionButtonDiv"]').hide();
	}
	$('[data-name="configPropertiesCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="configPropertiesViewFormButtonsDiv"]').css("display", "inline");
	//loadConfigPropertiesViewData(configPropertiesDataObject);
	disbaleConfigPropertiesUpdateDisallowedFields(paramsMap);
	doAfterConfigPropertiesPanelRefreshed();
    
    
}
function disbaleConfigPropertiesUpdateDisallowedFields(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("attributeNamePrefix"))
	{
		prefix = paramsMap['attributeNamePrefix'];
	}
	var parentElement =$('body');
	if(paramsMap.hasOwnProperty("parentElement"))
	{
		parentElement = paramsMap['parentElement'];
	}
	
}
function loadConfigPropertiesViewData(configPropertiesDataObject, paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("attributeNamePrefix"))
	{
		prefix = paramsMap['attributeNamePrefix'];
	}
	var parentElement =$('body');
	if(paramsMap.hasOwnProperty("parentElement"))
	{
		parentElement = paramsMap['parentElement'];
	}
	var configPropertiesId = configPropertiesDataObject['configPropertiesId'];
	$('#'+prefix+'idValueForConfigProperties').val(configPropertiesId);
		
	if(configPropertiesDataObject.hasOwnProperty('propertyName'))
	{
		var propertyName = configPropertiesDataObject['propertyName'];            		
		parentElement.find('[data-name="'+prefix+'ConfigProperties_propertyName"]').text(propertyName);
	}
	
	if(configPropertiesDataObject.hasOwnProperty('propertyValue'))
	{
		var propertyValue = configPropertiesDataObject['propertyValue'];            		
		parentElement.find('[data-name="'+prefix+'ConfigProperties_propertyValue"]').text(propertyValue);
	}

}
function ajaxDemoForConfigProperties()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForConfigProperties').textContent;	
	alert('Entity id : ' + idValue);
	$.ajax({
		url : urlContextPath + '/AjaxServlet' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
		data : {
			param1 : 'param1 Val',
			param2 : 'param2 Val',
			requestType : 'ajaxDemo'
		},
		success : function(responseText) {
			alert('Response message : ' + responseText['msg']);
			refreshPanelForConfigProperties();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForConfigProperties(msg)
{
	var executeCustomAPIButtonForConfigProperties = document.getElementById("FormWBEntity:executeCustomAPIButtonForConfigProperties");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:ConfigProperties_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForConfigProperties.click();
}
function refreshPanelForConfigProperties()
{
	var demoRefreshButtonForConfigProperties = document.getElementById("FormWBEntity:demoRefreshButtonConfigProperties");
	demoRefreshButtonForConfigProperties.click();
}
function initializePanelOnLoadForCreateConfigProperties(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForConfigProperties = initParamsObj;
	initializeConfigPropertiesPage();	
	doAfterConfigPropertiesPanelRefreshed();
	initializeConfigPropertiesLookupFields(initParamsObj);
	doAfterPanelInitializedForConfigPropertiesExt();
	fetchConfigPropertiesDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="configPropertiesCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForConfigProperties = 0;
function initializePanelOnLoadForSearchConfigProperties()
{
	initializeConfigPropertiesPage();	
	initializeConfigPropertiesLookupFields();
	doAfterPanelInitializedForConfigPropertiesExt();
	gLoadDashboardResultsForConfigProperties =1;
	//retrieveConfigPropertiesList();
}
function initializePanelOnLoadForViewConfigProperties(urlParams)
{
	initializeConfigPropertiesPage();	
	doAfterConfigPropertiesPanelRefreshed();
	initializeConfigPropertiesLookupFields(urlParams);
	doAfterPanelInitializedForConfigPropertiesExt();
	retrieveConfigProperties(urlParams);
	initDatePicker();
	$('[data-name="configPropertiesViewFormButtonsDiv"]').css("display", "inline");
}
function initializeConfigPropertiesPage()
{
	initializePageOnload();	
	//initializeConfigPropertiesTemplateVariables();
}
function initializeConfigPropertiesTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="configPropertiesSearchResults"]').find('[data-name="configPropertiesRow"]');
	if(searchResultsRowObj.length > 0 && gConfigPropertiesSearchResultsTableRowTemplate.length == 0)
	{
		gConfigPropertiesSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveConfigProperties(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var configPropertiesId = paramsMap['configPropertiesId'];
	var configProperties = {};
	configProperties['configPropertiesId'] = configPropertiesId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				configProperties[key] = paramsMap[key];
			}
	}
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/retrieveEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            else
        	{
                if (responseData['alert'])
                {
                    showAlert(responseData['alert']);
                }
                if (responseData['success'] == 1)
                {
                	var configPropertiesDataObject = responseData['configPropertiesDataObject'];
    				setConfigPropertiesInViewPage(configPropertiesDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForConfigProperties()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchConfigPropertiesDefaultData();
}
function fetchConfigPropertiesDefaultData(initParamsObj) 
{
	if(!initParamsObj)
	{
		initParamsObj = {};
	}
	var paramsMap = {};
	for (var key in initParamsObj)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsMap[key] = initParamsObj[key];
			}
	}
	var paramsInfo = {'initParams' : paramsMap};
    var searchJsonData = {'objectType' : 'ConfigProperties', 'paramsInfo': JSON.stringify(paramsInfo)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':initParamsObj
        },
        error: function (responseData)
        {
            closePopUp();
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/fetchEntityDefaultData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
            	document.getElementById('idValueForConfigProperties').value = '';
			    
            	setConfigPropertiesData(configProperties);
            }
        }
    });	
}
function fetchConfigPropertiesTestData() 
{
	var configProperties = getDataForConfigProperties();
    var searchJsonData = {'objectType' : 'ConfigProperties', 'paramsInfo' : JSON.stringify(configProperties)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/fetchEntityTestData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
            	document.getElementById('idValueForConfigProperties').value = '';
			    
            	setConfigPropertiesData(configProperties);
            }
        }
    });	
}
function setConfigPropertiesData(configPropertiesDataObject)
{
	var prefix = "";
		
	//Input
	if(configPropertiesDataObject.hasOwnProperty('propertyName'))
	{
		var propertyName = configPropertiesDataObject['propertyName'];            		
		$('#'+prefix+'ConfigProperties_propertyName').val(propertyName);
	}
	
	//Input
	if(configPropertiesDataObject.hasOwnProperty('propertyValue'))
	{
		var propertyValue = configPropertiesDataObject['propertyValue'];            		
		$('#'+prefix+'ConfigProperties_propertyValue').val(propertyValue);
	}

	$('[data-name="configPropertiesActionButtonDiv"]').hide();
}
function initializeConfigPropertiesLookupFields(paramsMap) 
{
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="configPropertiesSearchResultsDiv"]');
	
    
}

function doAfterConfigPropertiesPanelRefreshed()
{
    //doAfterPanelRefreshedForConfigPropertiesExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForConfigProperties(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForConfigPropertiesExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForConfigProperties(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForConfigPropertiesExt(fieldName)
}
function getEntityIdForConfigProperties()
{
	var idValue = document.getElementById('FormWBEntity:idValueForConfigProperties').textContent;	
	if(idValue && idValue.length>0)
	{
		var idNum = Number(idValue);	
		return idNum;
	}
	else
	{
		return -1;	
	}
}
function openPrintPageForConfigProperties()
{
	var entityId = getEntityIdForConfigProperties();
	if(entityId>0)
	{
	    window.open("/reports/generated/ConfigProperties.jsp?configPropertiesId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForConfigProperties(paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var configProperties = {};
		
	//Input
	if($("#"+prefix+"ConfigProperties_propertyName").length == 1)
	{
		var propertyName = $('#'+prefix+'ConfigProperties_propertyName').val();
		configProperties['propertyName'] = propertyName;
	}
	
	//Input
	if($("#"+prefix+"ConfigProperties_propertyValue").length == 1)
	{
		var propertyValue = $('#'+prefix+'ConfigProperties_propertyValue').val();
		configProperties['propertyValue'] = propertyValue;
	}

	
	return configProperties;
}
function createConfigProperties(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var configProperties = getDataForConfigProperties(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				configProperties[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForConfigProperties)
	{
		paramsInfo[key] = gInitParamsObjForConfigProperties[key];
	}
	var validationObject = doConfigPropertiesValidation(configProperties, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	configProperties['additionalParamsInfo'] = paramsInfo;
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
	if(gConfigPropertiesRequestUnderProcess == 1)
	{
        showAlert("There is already a pending request being processed by server.");
        return;
	}
	showSpinnerPopUp();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gConfigPropertiesRequestUnderProcess = 0;
            }, 1000);
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/createEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gConfigPropertiesRequestUnderProcess = 0;
            }, 1000);
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configPropertiesId = responseData['configPropertiesId'];
            	var popupElement = $('[data-name="ConfigPropertiesPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var configPropertiesDataObject = responseData['configPropertiesDataObject'];
    				setConfigPropertiesInViewPage(configPropertiesDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewConfigProperties.html?configPropertiesId="+configPropertiesId; 
        		}
				
            }
        }
    });
}
function resetTableForConfigProperties()
{
	var configPropertiesListElement = $("[data-name='configPropertiesList']");
	var previousDataRowList  = configPropertiesListElement.find('[data-name="configPropertiesRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForConfigProperties(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	$('#'+prefix+'idValueForConfigProperties').val('');
		
	//Input
	$('#'+prefix+'ConfigProperties_propertyName').val('');
	
	//Input
	$('#'+prefix+'ConfigProperties_propertyValue').val('');

	$('[data-name="configPropertiesCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="configPropertiesViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="configPropertiesActionButtonDiv"]').hide();
	enableConfigPropertiesUpdateDisallowedFields(paramsMap);
    
}
function enableConfigPropertiesUpdateDisallowedFields(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("prefix"))
	{
		prefix = paramsMap['prefix'];
	}
	
}
function updateConfigProperties(paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var configProperties = getDataForConfigProperties(paramsMap)
	if($("#"+prefix+"idValueForConfigProperties").length == 1)
	{
		var configPropertiesId = $("#"+prefix+"idValueForConfigProperties").val();
		configProperties['configPropertiesId'] = configPropertiesId;
	}
	var validationObject = doConfigPropertiesValidation(configProperties, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
	if(gConfigPropertiesRequestUnderProcess == 1)
	{
        showAlert("There is already a pending request being processed by server.");
        return;
	}
	showSpinnerPopUp();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gConfigPropertiesRequestUnderProcess = 0;
                    }, 1000);
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/updateEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gConfigPropertiesRequestUnderProcess = 0;
                    }, 1000);
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
				
            }
        }
    });
}
function deleteConfigProperties(paramsMap)
{		
	var configPropertiesId = document.getElementById('idValueForConfigProperties').value;
	deleteSelectedConfigProperties(configPropertiesId, paramsMap);
}
function deleteSelectedConfigProperties(configPropertiesId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var configProperties = {};
	configProperties['configPropertiesId'] = configPropertiesId;	
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/deleteEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            closePopUp();
            if (responseData['success'] == 1)
            {
            }
        }
    });
}
function loadSearchResultsForConfigProperties()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/AjaxServlet'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var searchResultObjectsList = responseData['searchResultObjectsList'];
            	var configPropertiesSearchResultsElement = $("[data-name='configPropertiesSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var configPropertiesDataObject = searchResultObjectsList[i];
					            		var propertyName = configPropertiesDataObject['propertyName'];            		
            		var propertyValue = configPropertiesDataObject['propertyValue'];            		

            		var resultRowTemnplate = $(gConfigPropertiesSearchResultsTableRowTemplate);
					            		var propertyName = configPropertiesDataObject['propertyName'];            		
            	    resultRowTemnplate.find("[data-name='propertyName']").text(propertyName);
            		var propertyValue = configPropertiesDataObject['propertyValue'];            		
            	    resultRowTemnplate.find("[data-name='propertyValue']").text(propertyValue);

            	    resultRowTemnplate.data('configProperties', configPropertiesDataObject);
            	    configPropertiesSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gConfigPropertiesSearchResultsTableRowTemplate = ""; 
function openViewPageForConfigPropertiesForEdit(configPropertiesLinkElement)
{
	var configPropertiesRowElement = $(configPropertiesLinkElement).parents('[data-name="configPropertiesRow"]');
    var configPropertiesDataObject  = configPropertiesRowElement.data('configProperties');
	var configPropertiesId = configPropertiesDataObject['configPropertiesId'];
	var configPropertiesPopupElement = $('[data-name="ConfigPropertiesPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setConfigPropertiesInViewPage(configPropertiesDataObject);
	    $("#ConfigProperties-tab").trigger("click");
	}
	else if(configPropertiesPopupElement.length > 0)
	{
	    setConfigPropertiesInViewPage(configPropertiesDataObject);
		$('[data-name="ConfigPropertiesPopup"]').find('[data-name="configPropertiesCreateFormButtonsDiv"]').hide();
		$('[data-name="ConfigPropertiesPopup"]').find('[data-name="configPropertiesViewFormButtonsDiv"]').show();
	    showPopup('ConfigPropertiesPopup', configPropertiesLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewConfigProperties.html?configPropertiesId="+configPropertiesId;
		window.open(viewLink, "_blank"); 	
	}
}
function openConfigPropertiesCreatePageForNew(linkElement)
{
	var configPropertiesPopupElement = $('[data-name="ConfigPropertiesPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForConfigProperties();
	    $("#ConfigProperties-tab").trigger("click");
    }
	else if(configPropertiesPopupElement.length > 0)
	{
		resetFormForConfigProperties();
	    showPopup('ConfigPropertiesPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateConfigProperties.html";
    }
}
function showConfigPropertiesPopupForEdit(configPropertiesLinkElement)
{
	var configPropertiesRowElement = $(configPropertiesLinkElement).parents('[data-name="configPropertiesRow"]');
    var configPropertiesDataObject  = configPropertiesRowElement.data('configProperties');
    setConfigPropertiesInViewPage(configPropertiesDataObject);
    showPopup('ConfigPropertiesPopup', configPropertiesLinkElement, 1);
    $("#ConfigProperties-tab").trigger("click");
}
function showConfigPropertiesPopupForNew(buttonElement)
{
	resetFormForConfigProperties();
    showPopup('ConfigPropertiesPopup', buttonElement, 1);
    $("#ConfigProperties-tab").trigger("click");
}
function deleteThisConfigProperties(configPropertiesLinkElement, paramsMap)
{
	var configPropertiesRowElement = $(configPropertiesLinkElement).parents('[data-name="configPropertiesRow"]');
    var configPropertiesDataObject  = configPropertiesRowElement.data('configProperties');
	var configPropertiesId = configPropertiesDataObject['configPropertiesId'];
	deleteSelectedConfigProperties(configPropertiesId, paramsMap);
}
function displayConfigPropertiesList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + configPropertiesSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var configPropertiesSearchResults = searchResultsDiv.find('[data-name="configPropertiesSearchResults"]');
	//configPropertiesSearchResults.find('[data-name="configPropertiesRow"]').remove();
	var previousDataRowList  = configPropertiesSearchResults.find('[data-name="configPropertiesRow"]');
	configPropertiesSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = configPropertiesSearchResults.find('[data-name="configPropertiesRow"]');
	var searchResultsTableRowTemplate = searchResultsTableRowTemplateObj[0].outerHTML;
    var currentPageIndex = 1;
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
    if(searchResultsDiv.length == 1)
    {
		currentPageIndex = searchResultsDiv.data("selected-page-index");
		noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
    }
	var currentPageStartingRecordNo = ((currentPageIndex - 1) * noOfRecordsToFetch) + 1;
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(searchResultsTableRowTemplate);
        var configPropertiesDataObject = searchResultObjectsList[i];
        loadConfigPropertiesViewData(configPropertiesDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = configPropertiesDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='configPropertiesVwTxnStatus']").text(vwTxnStatus);
		if(configPropertiesDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="configPropertiesActionButton"]').text(configPropertiesDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="configPropertiesActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="configPropertiesActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="configPropertiesRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="configPropertiesRejectButton"]').show();
		}
	    resultRowTemplate.data('configProperties', configPropertiesDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    configPropertiesSearchResults.append(resultRowTemplate);
	    processResultRowForConfigPropertiesExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForConfigProperties == 1)
	{
    	getDashboardResultsForConfigProperties();
	}
}
var configPropertiesSearchResultsDivName = "configPropertiesSearchResultsDiv";
var gConfigPropertiesSearchInputParamsList = [];
function retrieveConfigPropertiesList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + configPropertiesSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = 0;
    if(searchResultsDiv.find('[data-name="rowsPerPage"]').length>0)
	{
        noOfRecordsToFetch = searchResultsDiv.find('[data-name="rowsPerPage"]').val();
	}
    if(customParamsMap.hasOwnProperty('noOfRecordsToFetch'))
	{
    	noOfRecordsToFetch = customParamsMap['noOfRecordsToFetch'];
	}
    searchResultsDiv.data("is-searched", 0);
    fetchConfigPropertiesSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchConfigPropertiesSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getConfigPropertiesSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = configPropertiesSearchResultsDivName; 
	}
	if(customParamsMap && customParamsMap.hasOwnProperty("overrideWhereClause"))
	{
		searchInputParamsList['overrideWhereClause'] = customParamsMap['overrideWhereClause']; 
		searchInputParamsList['whereClause'] = customParamsMap['whereClause']; 
	}
    var contextObj = {
		'nextCurrentPageIndex': nextCurrentPageIndex,
        'noOfRecordsToFetch': noOfRecordsToFetch,
        'refreshIndex': refreshIndex,
        'refreshStartIndex': refreshStartIndex,
        'replacePaginationFunctions':1,
        'searchResultsDivName':searchResultsDivName,        
        'errorMessage': "abcd"
        };
    if(customParamsMap && customParamsMap.hasOwnProperty('successCallbackFunction'))
	{
        contextObj['successCallbackFunction'] = customParamsMap['successCallbackFunction'];
	}
    if(customParamsMap && customParamsMap.hasOwnProperty('suppressAlert'))
	{
        contextObj['suppressAlert'] = customParamsMap['suppressAlert'];
	}
    for (var i = 0; i < searchInputParamsList.length; i++)
    {
        var searchInputParamsInfoObj = searchInputParamsList[i];
        var parameterName = searchInputParamsInfoObj.parameterName;
        var userInputValue = searchInputParamsInfoObj.userInputValue;
        contextObj[parameterName] = userInputValue;
    }
    searchInputParamsList.push({'parameterName': 'noOfRecordsAlreadyFetched', 'userInputValue': noOfRecordsAlreadyFetched});
    searchInputParamsList.push({'parameterName': 'noOfRecordsToFetch', 'userInputValue': noOfRecordsToFetch})
    var searchData = {
        'paramsInfo': JSON.stringify({'searchInputParamsList':searchInputParamsList}),
        'objectType': "ConfigProperties"
    };    
    $.ajax({
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/retrieveEntityList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchData,
        context: contextObj,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                var loadAllTablesData =this.loadAllTablesData;
            	this.showPreviousRecords = "showPreviousConfigPropertiesRecords";
            	this.showCurrentPageRecords = "showCurrentPageConfigPropertiesRecords";
            	this.showNextRecords = "showNextConfigPropertiesRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchConfigPropertiesResults";
                var configPropertiesList = responseData['configPropertiesList'];
                var currContextObj = this; 
                var successCallbackFunction = displayConfigPropertiesList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'configPropertiesList', 'matchingSearchResultsCount', this.searchResultsDivName, 'configPropertiesSearchResults', 'configPropertiesRow', setConfigPropertiesSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getConfigPropertiesSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + configPropertiesSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = searchResultsDiv;
        var parameterNameAndValuesList = [];
        var additionalParamsList = customParamsMap['additionalParamsList'];
        if(!additionalParamsList)
    	{
        	additionalParamsList = [];
    	}
				
		//Input
		if(searchDiv.find('[data-name="configPropertiesDB_propertyName"]').length == 1)
		{
		    var propertyName = searchDiv.find('[data-name="configPropertiesDB_propertyName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'propertyName', 'userInputValue':propertyName});
		}
		
		//Input
		if(searchDiv.find('[data-name="configPropertiesDB_propertyValue"]').length == 1)
		{
		    var propertyValue = searchDiv.find('[data-name="configPropertiesDB_propertyValue"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'propertyValue', 'userInputValue':propertyValue});
		}

	    
	    for(var key in customParamsMap)
    	{
			if(key != "errorCallbackFunction"
				&& key != "successCallbackFunction")
				{
					parameterNameAndValuesList.push({ 'parameterName':key, 'userInputValue':customParamsMap[key]});
				}
    	}
	    parameterNameAndValuesList.push({'parameterName':'additionalParamsList', 'userInputValue':JSON.stringify(additionalParamsList)});
        gConfigPropertiesSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gConfigPropertiesSearchInputParamsList;
    }
    return searchInputParams;
}
function setConfigPropertiesSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + configPropertiesSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gConfigPropertiesSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gConfigPropertiesSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageConfigPropertiesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = configPropertiesSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchConfigPropertiesSearchResultsList);
}
function showPreviousConfigPropertiesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = configPropertiesSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchConfigPropertiesSearchResultsList);
}
function showNextConfigPropertiesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = configPropertiesSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchConfigPropertiesSearchResultsList);
}
function showPrevOrNextSearchConfigPropertiesResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousConfigPropertiesRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextConfigPropertiesRecords(e);
    }
}


function lookupRowSelected(fieldName)
{
	if(fieldName == 'customerAccountInformationCustomerName')
	{
		customerAccountInformationCustomerNameSelected();
	}
	//else if
}
function lookupRowSelectedForConfigProperties(attributeName, attributeId)
{
	var configProperties = getDataForConfigProperties();
	configProperties['attributeName'] = attributeName;
	configProperties['attributeId'] = attributeId;
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/entityLookupRowSelected'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
            	setConfigPropertiesData(configProperties);
            }
        }
    });	
}
function selectOptionChangedForConfigProperties(attributeName)
{
	var configProperties = getDataForConfigProperties();
	configProperties['attributeName'] = attributeName;
    var configPropertiesJsonData = {'paramsInfo': JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/selectOptionChanged'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
            	setConfigPropertiesData(configProperties);
            	doAfterConfigPropertiesPanelRefreshed();
            }
        }
    });	
}
function updateLookupDisplayFieldsForTestProductInfo(lookupLinkElement, selectedLookupObject)
{
}
function lookupRowSelected(fieldName)
{
	if(fieldName == 'customerAccountInformationCustomerName')
	{
		customerAccountInformationCustomerNameSelected();
	}
	//else if
}
function retrieveDependentConfigPropertiesList(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var Id = $('#'+prefix+'idValueFor').val();
	if(paramsMap.hasOwnProperty('Id'))
	{
		Id = paramsMap['Id'];
	}
	var paramsInfo = {'Id':Id};
    var searchJsonData = {'objectType' : 'ConfigProperties', 'paramsInfo': JSON.stringify(paramsInfo)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'paramsMap': paramsMap,
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
    	url : urlContextPath + '/retrieveDependentEntityList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['success'] == 1)
            {
            	var configPropertiesList = responseData['configPropertiesList'];
            	var configPropertiesListElement = $("[data-name='configPropertiesList']");
            	var previousDataRowList  = configPropertiesListElement.find('[data-name="configPropertiesRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = configPropertiesListElement.find('[data-name="configPropertiesRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//configPropertiesListElement.empty();
            	for(var i=0; i<configPropertiesList.length; i++)
        		{
            		var configPropertiesDataObject = configPropertiesList[i];
            		//var resultRowTemplate = $(gConfigPropertiesSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var propertyName = configPropertiesDataObject['propertyName'];            		
				    resultRowTemplate.find("[data-name='propertyName']").text(propertyName);
					var propertyValue = configPropertiesDataObject['propertyValue'];            		
				    resultRowTemplate.find("[data-name='propertyValue']").text(propertyValue);

					
					
            	    resultRowTemplate.data('configProperties', configPropertiesDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    configPropertiesListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForConfigProperties(customEventName)
{
	var configPropertiesId = document.getElementById('idValueForConfigProperties').value;
	var configProperties = {'configPropertiesId':configPropertiesId, 'customEventName':customEventName};
    var configPropertiesJsonData = {'paramsInfo':JSON.stringify(configProperties), 'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForConfigPropertiesExt(customEventName);
	showSpinnerPopUp("Your request is being processed.");
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'customEventName': customEventName
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/executeCustomAPI'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
        		setConfigPropertiesInViewPage(configProperties);
            }
    		doAfterExecuteCustomAPIForConfigPropertiesExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnConfigProperties(e, paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var isSearchResult = 0;
    if(paramsMap.hasOwnProperty("isSearchResult"))
	{
    	isSearchResult = paramsMap['isSearchResult'];
	}
	var configPropertiesId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var configPropertiesDataObject  = rowObj.data('configProperties');
    	configPropertiesId = configPropertiesDataObject['configPropertiesId'];
	}
    else
	{
    	configPropertiesId = document.getElementById('idValueForConfigProperties').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var configPropertiesSearchParams = {'configPropertiesId':configPropertiesId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	configPropertiesSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var configPropertiesJsonData = {'paramsInfo':JSON.stringify(configPropertiesSearchParams),  'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'isSearchResult':isSearchResult,
            'rowObj':rowObj
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/executeEntityAuthorisationAction'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var configProperties = responseData['configProperties'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="configPropertiesRowActionButtonDiv"]').hide();
            		if(configProperties.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = configProperties['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(configProperties['vwTxnStatus']);
            			selectedRowObj.find('[data-name="configPropertiesActionButton"]').text(configProperties["nextAction"]);
            			selectedRowObj.find('[data-name="configPropertiesActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setConfigPropertiesInViewPage(configProperties);
    			}
            }
        }
    });	
}
function downloadConfigPropertiesData()
{		
	var configProperties = {};
    var configPropertiesJsonData = {'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/downloadEntityData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
    			window.open("/download?fileId=" + fileId+"&objectType=ConfigProperties");
            }
        }
    });
}
function uploadConfigPropertiesData(fileInfo)
{
	var fileId = fileInfo['fileId'];
	var areSourceDestinationSameVal = 0;
    var areSourceDestinationSame = $('[data-name="fileUploadPopup"]').find('[data-name="areSourceDestinationSame"]').is(":checked");
	if(areSourceDestinationSame == true)
	{
		areSourceDestinationSameVal = 1;
	}
    var inputFilesZip = $('[data-name="fileUploadPopup"]').find('[data-name="upoadDataInputFilesZip"]').data("inputFilesZip");
    if(!inputFilesZip)
	{
    	inputFilesZip = "";
	}
	var configProperties = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var configPropertiesJsonData = {'paramsInfo':JSON.stringify(configProperties),  'objectType' : 'ConfigProperties'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/uploadEntityData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=ConfigProperties");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForConfigProperties()
{
    var configPropertiesJsonData = {'objectType' : 'ConfigProperties'};
	var urlContextPath = "";
	return;
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/getDashboardGraphsData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: configPropertiesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var monthlyTransactionList = responseData['monthlyTransactionList'];
            	//alert(JSON.stringify(monthlyTransactionList));
            	var transactionStatusList = responseData['transactionStatusList'];
            	//alert(JSON.stringify(transactionStatusList));
            }
        }
    });	
}



function doConfigPropertiesLengthValidation(configPropertiesDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(configPropertiesDataObject['propertyName'], 100))
	{
		alertMessage += "\n Property Name length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(configPropertiesDataObject['propertyValue'], 50))
	{
		alertMessage += "\n Property Value length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doConfigPropertiesManadatoryValidation(configPropertiesDataObject, paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
    var considerExistingFieldsOnly = false;
    if(paramsMap.hasOwnProperty("considerExistingFieldsOnly"))
	{
    	considerExistingFieldsOnly = paramsMap['considerExistingFieldsOnly'];
	}
    var alertMessage = "";
    var validationPassed = true;
		
	var propertyName = configPropertiesDataObject['propertyName'];
	if(!propertyName || propertyName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"ConfigProperties_propertyName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Property Name";
		    validationPassed = false;
		}
	}
	
	var propertyValue = configPropertiesDataObject['propertyValue'];
	if(!propertyValue || propertyValue.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"ConfigProperties_propertyValue").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Property Value";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doConfigPropertiesValidation(configPropertiesDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doConfigPropertiesLengthValidation(configPropertiesDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doConfigPropertiesManadatoryValidation(configPropertiesDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
