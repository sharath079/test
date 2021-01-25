/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'ParameterName' : 'FormWBEntity:TaskLayoutParameters_parameterName' 
 *	 * 'ParameterValueType' : 'FormWBEntity:TaskLayoutParameters_parameterValueType' 
 *	 * 'ParameterValue' : 'FormWBEntity:TaskLayoutParameters_parameterValue' 
 *	
 */
var gInitParamsObjForTaskLayoutParameters = {};
var gTaskLayoutParametersRequestUnderProcess = 0;
function selectThisTaskLayoutParametersForEdit(taskLayoutParametersRowElement)
{
    var taskLayoutParametersDataObject  = $(taskLayoutParametersRowElement).data('taskLayoutParameters');
    var taskLayoutParametersList = $('[data-name="taskLayoutParametersSearchResults"]').find('[data-name="taskLayoutParametersRow"]');
	taskLayoutParametersList.data("is-selected", 0);	
	$(taskLayoutParametersRowElement).data("is-selected", 1);
	setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject);
}

function setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject, paramsMap)
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
	var taskLayoutParametersId = taskLayoutParametersDataObject['taskLayoutParametersId'];
	$('#'+prefix+'idValueForTaskLayoutParameters').val(taskLayoutParametersId);
		
	//Input
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterName'))
	{
		var parameterName = taskLayoutParametersDataObject['parameterName'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterName').val(parameterName);
	}
	
	//Combo
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValueType'))
	{
		var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterValueType').val(parameterValueType)
	}
	
	//Input
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValue'))
	{
		var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterValue').val(parameterValue);
	}

	if(taskLayoutParametersDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = taskLayoutParametersDataObject['vwTxnStatus'];
		$('[data-name="taskLayoutParametersActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnTaskLayoutParameters(this)" >' +taskLayoutParametersDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="taskLayoutParametersActionButtonDiv"]').append(buttonElement);
		$('[data-name="taskLayoutParametersActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="taskLayoutParametersActionButtonDiv"]').hide();
	}
	$('[data-name="taskLayoutParametersCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskLayoutParametersViewFormButtonsDiv"]').css("display", "inline");
	//loadTaskLayoutParametersViewData(taskLayoutParametersDataObject);
	disbaleTaskLayoutParametersUpdateDisallowedFields(paramsMap);
	doAfterTaskLayoutParametersPanelRefreshed();
    
    
}
function disbaleTaskLayoutParametersUpdateDisallowedFields(paramsMap)
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
function loadTaskLayoutParametersViewData(taskLayoutParametersDataObject, paramsMap)
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
	var taskLayoutParametersId = taskLayoutParametersDataObject['taskLayoutParametersId'];
	$('#'+prefix+'idValueForTaskLayoutParameters').val(taskLayoutParametersId);
		
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterName'))
	{
		var parameterName = taskLayoutParametersDataObject['parameterName'];            		
		parentElement.find('[data-name="'+prefix+'TaskLayoutParameters_parameterName"]').text(parameterName);
	}
	
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValueType'))
	{
		var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
		parentElement.find('[data-name="'+prefix+'TaskLayoutParameters_parameterValueType"]').text(parameterValueType);
	}
	
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValue'))
	{
		var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		
		parentElement.find('[data-name="'+prefix+'TaskLayoutParameters_parameterValue"]').text(parameterValue);
	}

}
function ajaxDemoForTaskLayoutParameters()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForTaskLayoutParameters').textContent;	
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
			refreshPanelForTaskLayoutParameters();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForTaskLayoutParameters(msg)
{
	var executeCustomAPIButtonForTaskLayoutParameters = document.getElementById("FormWBEntity:executeCustomAPIButtonForTaskLayoutParameters");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:TaskLayoutParameters_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForTaskLayoutParameters.click();
}
function refreshPanelForTaskLayoutParameters()
{
	var demoRefreshButtonForTaskLayoutParameters = document.getElementById("FormWBEntity:demoRefreshButtonTaskLayoutParameters");
	demoRefreshButtonForTaskLayoutParameters.click();
}
function initializePanelOnLoadForCreateTaskLayoutParameters(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForTaskLayoutParameters = initParamsObj;
	initializeTaskLayoutParametersPage();	
	doAfterTaskLayoutParametersPanelRefreshed();
	initializeTaskLayoutParametersLookupFields(initParamsObj);
	doAfterPanelInitializedForTaskLayoutParametersExt();
	fetchTaskLayoutParametersDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="taskLayoutParametersCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForTaskLayoutParameters = 0;
function initializePanelOnLoadForSearchTaskLayoutParameters()
{
	initializeTaskLayoutParametersPage();	
	initializeTaskLayoutParametersLookupFields();
	doAfterPanelInitializedForTaskLayoutParametersExt();
	gLoadDashboardResultsForTaskLayoutParameters =1;
	//retrieveTaskLayoutParametersList();
}
function initializePanelOnLoadForViewTaskLayoutParameters(urlParams)
{
	initializeTaskLayoutParametersPage();	
	doAfterTaskLayoutParametersPanelRefreshed();
	initializeTaskLayoutParametersLookupFields(urlParams);
	doAfterPanelInitializedForTaskLayoutParametersExt();
	retrieveTaskLayoutParameters(urlParams);
	initDatePicker();
	$('[data-name="taskLayoutParametersViewFormButtonsDiv"]').css("display", "inline");
}
function initializeTaskLayoutParametersPage()
{
	initializePageOnload();	
	//initializeTaskLayoutParametersTemplateVariables();
}
function initializeTaskLayoutParametersTemplateVariables()
{	
	
	
	var taskLayoutParametersRowObj = $('[data-name="taskLayoutParametersList"]').find('[data-name="taskLayoutParametersRow"]');
	if(taskLayoutParametersRowObj.length > 0 && gTaskLayoutParametersSearchResultsTableRowTemplate.length == 0)
	{
		gTaskLayoutParametersSearchResultsTableRowTemplate = taskLayoutParametersRowObj[0].outerHTML;
		//taskLayoutParametersRowObj.remove();
	}
	if(gTaskLayoutParametersSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="taskLayoutParametersSearchResults"]').find('[data-name="taskLayoutParametersRow"]');
		if(searchResultsRowObj.length > 0 && gTaskLayoutParametersSearchResultsTableRowTemplate.length == 0)
		{
			gTaskLayoutParametersSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrieveTaskLayoutParameters(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var taskLayoutParametersId = paramsMap['taskLayoutParametersId'];
	var taskLayoutParameters = {};
	taskLayoutParameters['taskLayoutParametersId'] = taskLayoutParametersId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				taskLayoutParameters[key] = paramsMap[key];
			}
	}
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
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
                	var taskLayoutParametersDataObject = responseData['taskLayoutParametersDataObject'];
    				setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForTaskLayoutParameters()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchTaskLayoutParametersDefaultData();
}
function fetchTaskLayoutParametersDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'TaskLayoutParameters', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
            	document.getElementById('idValueForTaskLayoutParameters').value = '';
			    
            	setTaskLayoutParametersData(taskLayoutParameters);
            }
        }
    });	
}
function fetchTaskLayoutParametersTestData() 
{
	var taskLayoutParameters = getDataForTaskLayoutParameters();
    var searchJsonData = {'objectType' : 'TaskLayoutParameters', 'paramsInfo' : JSON.stringify(taskLayoutParameters)};
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
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
            	document.getElementById('idValueForTaskLayoutParameters').value = '';
			    
            	setTaskLayoutParametersData(taskLayoutParameters);
            }
        }
    });	
}
function setTaskLayoutParametersData(taskLayoutParametersDataObject)
{
	var prefix = "";
		
	//Input
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterName'))
	{
		var parameterName = taskLayoutParametersDataObject['parameterName'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterName').val(parameterName);
	}
	
	//Combo
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValueType'))
	{
		var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterValueType').val(parameterValueType);
	}
	
	//Input
	if(taskLayoutParametersDataObject.hasOwnProperty('parameterValue'))
	{
		var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		
		$('#'+prefix+'TaskLayoutParameters_parameterValue').val(parameterValue);
	}

	$('[data-name="taskLayoutParametersActionButtonDiv"]').hide();
}
function initializeTaskLayoutParametersLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="taskLayoutParametersSearchResultsDiv"]');
	
        searchDiv.find('[data-name="taskInfoDBId"]').data("taskInfo-id", -1);

}

function initializeTaskLayoutParametersLookupSelectList(paramsMap)
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
}
function doAfterTaskLayoutParametersPanelRefreshed()
{
    //doAfterPanelRefreshedForTaskLayoutParametersExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForTaskLayoutParameters(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'parameterValueType')
	{
	}

	doAfterSelectOptionChangedForTaskLayoutParametersExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForTaskLayoutParameters(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForTaskLayoutParametersExt(fieldName)
}
function getEntityIdForTaskLayoutParameters()
{
	var idValue = document.getElementById('FormWBEntity:idValueForTaskLayoutParameters').textContent;	
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
function openPrintPageForTaskLayoutParameters()
{
	var entityId = getEntityIdForTaskLayoutParameters();
	if(entityId>0)
	{
	    window.open("/reports/generated/TaskLayoutParameters.jsp?taskLayoutParametersId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForTaskLayoutParameters(paramsMap)
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
	var taskLayoutParameters = {};
		
	//Input
	if($("#"+prefix+"TaskLayoutParameters_parameterName").length == 1)
	{
		var parameterName = $('#'+prefix+'TaskLayoutParameters_parameterName').val();
		taskLayoutParameters['parameterName'] = parameterName;
	}
	
	//Combo
	if($("#"+prefix+"TaskLayoutParameters_parameterValueType").length == 1)
	{
		var parameterValueType = $('#'+prefix+'TaskLayoutParameters_parameterValueType').val();
		taskLayoutParameters['parameterValueType'] = parameterValueType;
	}
	
	//Input
	if($("#"+prefix+"TaskLayoutParameters_parameterValue").length == 1)
	{
		var parameterValue = $('#'+prefix+'TaskLayoutParameters_parameterValue').val();
		taskLayoutParameters['parameterValue'] = parameterValue;
	}

	
	
	if($("#"+prefix+"idValueForTaskInfo").length == 1)
	{
		var taskInfoId = $("#"+prefix+"idValueForTaskInfo").val();
		taskLayoutParameters['taskInfoId'] = taskInfoId; 
	}
	
	return taskLayoutParameters;
}
function createTaskLayoutParameters(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var taskLayoutParameters = getDataForTaskLayoutParameters(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				taskLayoutParameters[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForTaskLayoutParameters)
	{
		paramsInfo[key] = gInitParamsObjForTaskLayoutParameters[key];
	}
	var validationObject = doTaskLayoutParametersValidation(taskLayoutParameters, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	taskLayoutParameters['additionalParamsInfo'] = paramsInfo;
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
	var urlContextPath = "";//getContextPath();
	if(gTaskLayoutParametersRequestUnderProcess == 1)
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
            	gTaskLayoutParametersRequestUnderProcess = 0;
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gTaskLayoutParametersRequestUnderProcess = 0;
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
            	var taskLayoutParametersId = responseData['taskLayoutParametersId'];
            	var popupElement = $('[data-name="TaskLayoutParametersPopup"]');
            	
				
            	var taskLayoutParametersDataObject = responseData['taskLayoutParametersDataObject'];
				setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject);
				retrieveDependentTaskLayoutParametersList();
            }
        }
    });
}
function resetTableForTaskLayoutParameters()
{
	var taskLayoutParametersListElement = $("[data-name='taskLayoutParametersList']");
	var previousDataRowList  = taskLayoutParametersListElement.find('[data-name="taskLayoutParametersRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForTaskLayoutParameters(paramsMap)
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
	$('#'+prefix+'idValueForTaskLayoutParameters').val('');
		
	//Input
	$('#'+prefix+'TaskLayoutParameters_parameterName').val('');
	
	//Combo
	$('#'+prefix+'TaskLayoutParameters_parameterValueType').val('');
	
	//Input
	$('#'+prefix+'TaskLayoutParameters_parameterValue').val('');

	$('[data-name="taskLayoutParametersCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="taskLayoutParametersViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskLayoutParametersActionButtonDiv"]').hide();
	enableTaskLayoutParametersUpdateDisallowedFields(paramsMap);
    
}
function enableTaskLayoutParametersUpdateDisallowedFields(paramsMap)
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
function updateTaskLayoutParameters(paramsMap)
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
	var taskLayoutParameters = getDataForTaskLayoutParameters(paramsMap)
	if($("#"+prefix+"idValueForTaskLayoutParameters").length == 1)
	{
		var taskLayoutParametersId = $("#"+prefix+"idValueForTaskLayoutParameters").val();
		taskLayoutParameters['taskLayoutParametersId'] = taskLayoutParametersId;
	}
	var validationObject = doTaskLayoutParametersValidation(taskLayoutParameters, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
	var urlContextPath = "";//getContextPath();
	if(gTaskLayoutParametersRequestUnderProcess == 1)
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
                    	gTaskLayoutParametersRequestUnderProcess = 0;
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gTaskLayoutParametersRequestUnderProcess = 0;
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
				
				retrieveDependentTaskLayoutParametersList();
            }
        }
    });
}
function deleteTaskLayoutParameters(paramsMap)
{		
	var taskLayoutParametersId = document.getElementById('idValueForTaskLayoutParameters').value;
	deleteSelectedTaskLayoutParameters(taskLayoutParametersId, paramsMap);
}
function deleteSelectedTaskLayoutParameters(taskLayoutParametersId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var taskLayoutParameters = {};
	taskLayoutParameters['taskLayoutParametersId'] = taskLayoutParametersId;	
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
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
function loadSearchResultsForTaskLayoutParameters()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'TaskLayoutParameters'};
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
            	var taskLayoutParametersSearchResultsElement = $("[data-name='taskLayoutParametersSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var taskLayoutParametersDataObject = searchResultObjectsList[i];
					            		var parameterName = taskLayoutParametersDataObject['parameterName'];            		
            		var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
            		var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		

            		var resultRowTemnplate = $(gTaskLayoutParametersSearchResultsTableRowTemplate);
					            		var parameterName = taskLayoutParametersDataObject['parameterName'];            		
            	    resultRowTemnplate.find("[data-name='parameterName']").text(parameterName);
            		var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
            	    resultRowTemnplate.find("[data-name='parameterValueType']").text(parameterValueType);
            		var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		
            	    resultRowTemnplate.find("[data-name='parameterValue']").text(parameterValue);

            	    resultRowTemnplate.data('taskLayoutParameters', taskLayoutParametersDataObject);
            	    taskLayoutParametersSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gTaskLayoutParametersSearchResultsTableRowTemplate = ""; 
function openViewPageForTaskLayoutParametersForEdit(taskLayoutParametersLinkElement)
{
	var taskLayoutParametersRowElement = $(taskLayoutParametersLinkElement).parents('[data-name="taskLayoutParametersRow"]');
    var taskLayoutParametersDataObject  = taskLayoutParametersRowElement.data('taskLayoutParameters');
	var taskLayoutParametersId = taskLayoutParametersDataObject['taskLayoutParametersId'];
	var taskLayoutParametersPopupElement = $('[data-name="TaskLayoutParametersPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject);
	    $("#TaskLayoutParameters-tab").trigger("click");
	}
	else if(taskLayoutParametersPopupElement.length > 0)
	{
	    setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject);
		$('[data-name="TaskLayoutParametersPopup"]').find('[data-name="taskLayoutParametersCreateFormButtonsDiv"]').hide();
		$('[data-name="TaskLayoutParametersPopup"]').find('[data-name="taskLayoutParametersViewFormButtonsDiv"]').show();
	    showPopup('TaskLayoutParametersPopup', taskLayoutParametersLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewTaskLayoutParameters.html?taskLayoutParametersId="+taskLayoutParametersId;
		window.open(viewLink, "_blank"); 	
	}
}
function openTaskLayoutParametersCreatePageForNew(linkElement)
{
	var taskLayoutParametersPopupElement = $('[data-name="TaskLayoutParametersPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForTaskLayoutParameters();
	    $("#TaskLayoutParameters-tab").trigger("click");
    }
	else if(taskLayoutParametersPopupElement.length > 0)
	{
		resetFormForTaskLayoutParameters();
	    showPopup('TaskLayoutParametersPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateTaskLayoutParameters.html";
    }
}
function showTaskLayoutParametersPopupForEdit(taskLayoutParametersLinkElement)
{
	var taskLayoutParametersRowElement = $(taskLayoutParametersLinkElement).parents('[data-name="taskLayoutParametersRow"]');
    var taskLayoutParametersDataObject  = taskLayoutParametersRowElement.data('taskLayoutParameters');
    setTaskLayoutParametersInViewPage(taskLayoutParametersDataObject);
    showPopup('TaskLayoutParametersPopup', taskLayoutParametersLinkElement, 1);
    $("#TaskLayoutParameters-tab").trigger("click");
}
function showTaskLayoutParametersPopupForNew(buttonElement)
{
	resetFormForTaskLayoutParameters();
    showPopup('TaskLayoutParametersPopup', buttonElement, 1);
    $("#TaskLayoutParameters-tab").trigger("click");
}
function deleteThisTaskLayoutParameters(taskLayoutParametersLinkElement, paramsMap)
{
	var taskLayoutParametersRowElement = $(taskLayoutParametersLinkElement).parents('[data-name="taskLayoutParametersRow"]');
    var taskLayoutParametersDataObject  = taskLayoutParametersRowElement.data('taskLayoutParameters');
	var taskLayoutParametersId = taskLayoutParametersDataObject['taskLayoutParametersId'];
	deleteSelectedTaskLayoutParameters(taskLayoutParametersId, paramsMap);
}
function displayTaskLayoutParametersList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskLayoutParametersSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var taskLayoutParametersSearchResults = searchResultsDiv.find('[data-name="taskLayoutParametersSearchResults"]');
	//taskLayoutParametersSearchResults.find('[data-name="taskLayoutParametersRow"]').remove();
	var previousDataRowList  = taskLayoutParametersSearchResults.find('[data-name="taskLayoutParametersRow"]');
	taskLayoutParametersSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = taskLayoutParametersSearchResults.find('[data-name="taskLayoutParametersRow"]');
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
        var taskLayoutParametersDataObject = searchResultObjectsList[i];
        loadTaskLayoutParametersViewData(taskLayoutParametersDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var taskInfoDisplayVal = taskLayoutParametersDataObject['taskInfoDisplayVal'];            		
	    resultRowTemplate.find("[data-name='taskInfoDisplayVal']").text(taskInfoDisplayVal);

		var vwTxnStatus = taskLayoutParametersDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='taskLayoutParametersVwTxnStatus']").text(vwTxnStatus);
		if(taskLayoutParametersDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="taskLayoutParametersActionButton"]').text(taskLayoutParametersDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="taskLayoutParametersActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="taskLayoutParametersActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="taskLayoutParametersRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="taskLayoutParametersRejectButton"]').show();
		}
	    resultRowTemplate.data('taskLayoutParameters', taskLayoutParametersDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    taskLayoutParametersSearchResults.append(resultRowTemplate);
	    processResultRowForTaskLayoutParametersExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForTaskLayoutParameters == 1)
	{
    	getDashboardResultsForTaskLayoutParameters();
	}
}
var taskLayoutParametersSearchResultsDivName = "taskLayoutParametersSearchResultsDiv";
var gTaskLayoutParametersSearchInputParamsList = [];
function retrieveTaskLayoutParametersList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + taskLayoutParametersSearchResultsDivName + '"]');
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
    fetchTaskLayoutParametersSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchTaskLayoutParametersSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskLayoutParametersSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskLayoutParametersSearchResultsDivName; 
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
        'objectType': "TaskLayoutParameters"
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
            	this.showPreviousRecords = "showPreviousTaskLayoutParametersRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskLayoutParametersRecords";
            	this.showNextRecords = "showNextTaskLayoutParametersRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskLayoutParametersResults";
                var taskLayoutParametersList = responseData['taskLayoutParametersList'];
                var currContextObj = this; 
                var successCallbackFunction = displayTaskLayoutParametersList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'taskLayoutParametersList', 'matchingSearchResultsCount', this.searchResultsDivName, 'taskLayoutParametersSearchResults', 'taskLayoutParametersRow', setTaskLayoutParametersSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getTaskLayoutParametersSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskLayoutParametersSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="taskLayoutParametersDB_parameterName"]').length == 1)
		{
		    var parameterName = searchDiv.find('[data-name="taskLayoutParametersDB_parameterName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'parameterName', 'userInputValue':parameterName});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskLayoutParametersDB_parameterValueType"]').length == 1)
		{
		    var parameterValueType = searchDiv.find('[data-name="taskLayoutParametersDB_parameterValueType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'parameterValueType', 'userInputValue':parameterValueType});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskLayoutParametersDB_parameterValue"]').length == 1)
		{
		    var parameterValue = searchDiv.find('[data-name="taskLayoutParametersDB_parameterValue"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'parameterValue', 'userInputValue':parameterValue});
		}

	    		if(searchDiv.find('[data-name="taskInfoDBId"]').length == 1)
		{
		    var taskInfoId = searchDiv.find('[data-name="taskInfoDBId"]').data("taskInfo-id");
		    parameterNameAndValuesList.push({ 'parameterName':'taskInfoId', 'userInputValue':taskInfoId});
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
        gTaskLayoutParametersSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskLayoutParametersSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskLayoutParametersSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + taskLayoutParametersSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskLayoutParametersSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskLayoutParametersSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskLayoutParametersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskLayoutParametersSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchTaskLayoutParametersSearchResultsList);
}
function showPreviousTaskLayoutParametersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskLayoutParametersSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchTaskLayoutParametersSearchResultsList);
}
function showNextTaskLayoutParametersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskLayoutParametersSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchTaskLayoutParametersSearchResultsList);
}
function showPrevOrNextSearchTaskLayoutParametersResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskLayoutParametersRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextTaskLayoutParametersRecords(e);
    }
}

var gTaskInfo_TaskLayoutParametersSearchResultsTableRowTemplate =""; 
function initializeTaskInfoPopup_TaskInfo_TaskLayoutParametersLookupFields() 
{	
    var searchDiv = $('[data-name="TaskInfo_TaskLayoutParametersSearchDiv"]');
	
    
	if(gTaskInfo_TaskLayoutParametersSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="TaskInfo_TaskLayoutParametersSearchResults"]').find('[data-name="TaskInfo_TaskLayoutParametersRow"]');
		gTaskInfo_TaskLayoutParametersSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayTaskInfo_TaskLayoutParametersList(searchResultObjectsList, parentElement)
{
    var taskInfoSearchResults = $('[data-name="TaskInfo_TaskLayoutParametersSearchResults"]');
	taskInfoSearchResults.find('[data-name="TaskInfo_TaskLayoutParametersRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gTaskInfo_TaskLayoutParametersSearchResultsTableRowTemplate);
        var taskInfoDataObject = searchResultObjectsList[i];
				var taskName = taskInfoDataObject['taskName'];            		
	    resultRowTemplate.find("[data-name='taskName']").text(taskName);
		var taskDescription = taskInfoDataObject['taskDescription'];            		
	    resultRowTemplate.find("[data-name='taskDescription']").text(taskDescription);
		var taskType = taskInfoDataObject['taskType'];            		
	    resultRowTemplate.find("[data-name='taskType']").text(taskType);
		var apiName = taskInfoDataObject['apiName'];            		
	    resultRowTemplate.find("[data-name='apiName']").text(apiName);
		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
	    resultRowTemplate.find("[data-name='targetEntityQuery']").text(targetEntityQuery);
		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
	    resultRowTemplate.find("[data-name='targetUserIdColumnAlias']").text(targetUserIdColumnAlias);
		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
	    resultRowTemplate.find("[data-name='targetEntityIdColumnAlias']").text(targetEntityIdColumnAlias);
		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
	    resultRowTemplate.find("[data-name='enableInAppNotification']").text(enableInAppNotification);
		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
	    resultRowTemplate.find("[data-name='inAppNotificationLayout']").text(inAppNotificationLayout);
		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
	    resultRowTemplate.find("[data-name='enableEmailNotification']").text(enableEmailNotification);
		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
	    resultRowTemplate.find("[data-name='emailColumnAlias']").text(emailColumnAlias);
		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
	    resultRowTemplate.find("[data-name='emailNotificationLayout']").text(emailNotificationLayout);
		var emailSubject = taskInfoDataObject['emailSubject'];            		
	    resultRowTemplate.find("[data-name='emailSubject']").text(emailSubject);
		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
	    resultRowTemplate.find("[data-name='enableSmsNotification']").text(enableSmsNotification);
		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
	    resultRowTemplate.find("[data-name='smsColumnAlias']").text(smsColumnAlias);
		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
	    resultRowTemplate.find("[data-name='smsNotificationLayout']").text(smsNotificationLayout);
		var sMSText = taskInfoDataObject['sMSText'];            		
	    resultRowTemplate.find("[data-name='sMSText']").text(sMSText);
		var isActive = taskInfoDataObject['isActive'];            		
	    resultRowTemplate.find("[data-name='isActive']").text(isActive);
		var taskStartDate = taskInfoDataObject['taskStartDate'];            		
	    resultRowTemplate.find("[data-name='taskStartDate']").text(taskStartDate);
		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
	    resultRowTemplate.find("[data-name='taskFrequency']").text(taskFrequency);
		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
	    resultRowTemplate.find("[data-name='taskFrequencyUnit']").text(taskFrequencyUnit);
		var isRecurring = taskInfoDataObject['isRecurring'];            		
	    resultRowTemplate.find("[data-name='isRecurring']").text(isRecurring);
		var firstRunType = taskInfoDataObject['firstRunType'];            		
	    resultRowTemplate.find("[data-name='firstRunType']").text(firstRunType);
		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
	    resultRowTemplate.find("[data-name='dateColumnAlias']").text(dateColumnAlias);
		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
	    resultRowTemplate.find("[data-name='firstRunDateCalculationMethod']").text(firstRunDateCalculationMethod);
		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInYears']").text(firstRunDateGapInYears);
		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInMonths']").text(firstRunDateGapInMonths);
		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInDays']").text(firstRunDateGapInDays);
		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInHours']").text(firstRunDateGapInHours);
		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInMinutes']").text(firstRunDateGapInMinutes);
		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		
	    resultRowTemplate.find("[data-name='firstRunDateGapInSeconds']").text(firstRunDateGapInSeconds);

		
	    
	    resultRowTemplate.data('taskInfo', taskInfoDataObject);
	    taskInfoSearchResults.append(resultRowTemplate);            	    
    }
}
var TaskInfo_TaskLayoutParametersSearchResultsDivName = "TaskInfo_TaskLayoutParametersSearchResultsDiv";
var gTaskInfo_TaskLayoutParametersSearchInputParamsList = [];
function getTaskInfo_TaskLayoutParametersSearchResults()
{
    var searchDiv = $('[data-name="TaskInfo_TaskLayoutParametersSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskLayoutParametersSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchTaskInfo_TaskLayoutParametersSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchTaskInfo_TaskLayoutParametersSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskInfo_TaskLayoutParametersSearchInputParams();
    var contextObj = {
		'nextCurrentPageIndex': nextCurrentPageIndex,
        'noOfRecordsToFetch': noOfRecordsToFetch,
        'refreshIndex': refreshIndex,
        'refreshStartIndex': refreshStartIndex,
        'replacePaginationFunctions':1,
        'errorMessage': "abcd"
        };
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
        'objectType': "TaskInfo",
    };    
    $.ajax({
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
        url: urlContextPath + '/retrieveEntityList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),        
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
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_TaskLayoutParametersRecords";
            	this.showPreviousRecords = "showPreviousTaskInfo_TaskLayoutParametersRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_TaskLayoutParametersRecords";
            	this.showNextRecords = "showNextTaskInfo_TaskLayoutParametersRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskInfo_TaskLayoutParametersResults";
                var taskInfoList = responseData['taskInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', TaskInfo_TaskLayoutParametersSearchResultsDivName, 'TaskInfo_TaskLayoutParametersSearchResults', 'TaskInfo_TaskLayoutParametersRow', setTaskInfo_TaskLayoutParametersSearchInputParams, displayTaskInfo_TaskLayoutParametersList);
            }
        }
    });
}
function getTaskInfo_TaskLayoutParametersSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskLayoutParametersSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="TaskInfo_TaskLayoutParametersSearchDiv"]');
        var parameterNameAndValuesList = [];
				
		//Input
	    var taskName = searchDiv.find('[data-name="taskInfoDB_taskName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'taskName', 'userInputValue':taskName});
		
		//Input
	    var taskDescription = searchDiv.find('[data-name="taskInfoDB_taskDescription"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'taskDescription', 'userInputValue':taskDescription});
		
		//Combo
	    var taskType = searchDiv.find('[data-name="taskInfoDB_taskType"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'taskType', 'userInputValue':taskType});
		
		//Input
	    var apiName = searchDiv.find('[data-name="taskInfoDB_apiName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'apiName', 'userInputValue':apiName});
		
		//Input
	    var targetEntityQuery = searchDiv.find('[data-name="taskInfoDB_targetEntityQuery"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'targetEntityQuery', 'userInputValue':targetEntityQuery});

	    
        gTaskInfo_TaskLayoutParametersSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskInfo_TaskLayoutParametersSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskInfo_TaskLayoutParametersSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskLayoutParametersSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskInfo_TaskLayoutParametersSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskInfo_TaskLayoutParametersSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskInfo_TaskLayoutParametersRecords(e)
{
    getCurrentPageSearchResults(e, TaskInfo_TaskLayoutParametersSearchResultsDivName, fetchTaskInfo_TaskLayoutParametersSearchResultsList);
}
function showPreviousTaskInfo_TaskLayoutParametersRecords()
{
    getPreviousPageSearchResults(TaskInfo_TaskLayoutParametersSearchResultsDivName, fetchTaskInfo_TaskLayoutParametersSearchResultsList);
}
function showNextTaskInfo_TaskLayoutParametersRecords()
{
    getNextPageSearchResults(TaskInfo_TaskLayoutParametersSearchResultsDivName, fetchTaskInfo_TaskLayoutParametersSearchResultsList);
}
function showPrevOrNextSearchTaskInfo_TaskLayoutParametersResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskInfo_TaskLayoutParametersRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextTaskInfo_TaskLayoutParametersRecords();
    }
}
function setTaskInfo_TaskLayoutParameters(taskInfoRowElement) 
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var parentElement = $(taskInfoRowElement).parents('[data-name="TaskInfoPopup_TaskInfo_TaskLayoutParameters"]');
	var linkElement = parentElement.data("selected-element");
	linkElement.data("taskInfo-id", taskInfoId);
	var isSearchLink = linkElement.data("is-search-link");  
	if(isSearchLink != 1)
	{
		var nextElements = linkElement.parents(".form-group").next();
		for(var i=0; i< nextElements.length; i++)
		{
			var inputElement = $(nextElements[i]).find('input');
			if(inputElement.data("is-lookup-field") != 1)
			{
				break;
			}
			var el = inputElement.data("value-el");
			var lookupDisplayValueName = el.split('.')[2];
			inputElement.val(taskInfoDataObject[lookupDisplayValueName]);		
		}
		var entityName = "TaskInfo_TaskLayoutParameters";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+taskInfoId+")";
	    eval(functionName);
    }		
	parentElement.hide();
}





function lookupRowSelected(fieldName)
{
	if(fieldName == 'customerAccountInformationCustomerName')
	{
		customerAccountInformationCustomerNameSelected();
	}
	//else if
}
function lookupRowSelectedForTaskLayoutParameters(attributeName, attributeId)
{
	var taskLayoutParameters = getDataForTaskLayoutParameters();
	taskLayoutParameters['attributeName'] = attributeName;
	taskLayoutParameters['attributeId'] = attributeId;
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
            	setTaskLayoutParametersData(taskLayoutParameters);
            }
        }
    });	
}
function selectOptionChangedForTaskLayoutParameters(attributeName)
{
	var taskLayoutParameters = getDataForTaskLayoutParameters();
	taskLayoutParameters['attributeName'] = attributeName;
    var taskLayoutParametersJsonData = {'paramsInfo': JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
            	setTaskLayoutParametersData(taskLayoutParameters);
            	doAfterTaskLayoutParametersPanelRefreshed();
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
function retrieveDependentTaskLayoutParametersList(paramsMap)
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
	var taskInfoId = $('#'+prefix+'idValueForTaskInfo').val();
	if(paramsMap.hasOwnProperty('taskInfoId'))
	{
		taskInfoId = paramsMap['taskInfoId'];
	}
	var paramsInfo = {'taskInfoId':taskInfoId};
    var searchJsonData = {'objectType' : 'TaskLayoutParameters', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskLayoutParametersList = responseData['taskLayoutParametersList'];
            	var taskLayoutParametersListElement = $("[data-name='taskLayoutParametersList']");
            	var previousDataRowList  = taskLayoutParametersListElement.find('[data-name="taskLayoutParametersRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = taskLayoutParametersListElement.find('[data-name="taskLayoutParametersRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//taskLayoutParametersListElement.empty();
            	for(var i=0; i<taskLayoutParametersList.length; i++)
        		{
            		var taskLayoutParametersDataObject = taskLayoutParametersList[i];
            		//var resultRowTemplate = $(gTaskLayoutParametersSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var parameterName = taskLayoutParametersDataObject['parameterName'];            		
				    resultRowTemplate.find("[data-name='parameterName']").text(parameterName);
					var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];            		
				    resultRowTemplate.find("[data-name='parameterValueType']").text(parameterValueType);
					var parameterValue = taskLayoutParametersDataObject['parameterValue'];            		
				    resultRowTemplate.find("[data-name='parameterValue']").text(parameterValue);

					
					
            	    resultRowTemplate.data('taskLayoutParameters', taskLayoutParametersDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    taskLayoutParametersListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForTaskLayoutParameters(customEventName)
{
	var taskLayoutParametersId = document.getElementById('idValueForTaskLayoutParameters').value;
	var taskLayoutParameters = {'taskLayoutParametersId':taskLayoutParametersId, 'customEventName':customEventName};
    var taskLayoutParametersJsonData = {'paramsInfo':JSON.stringify(taskLayoutParameters), 'objectType' : 'TaskLayoutParameters'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForTaskLayoutParametersExt(customEventName);
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
        		setTaskLayoutParametersInViewPage(taskLayoutParameters);
            }
    		doAfterExecuteCustomAPIForTaskLayoutParametersExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnTaskLayoutParameters(e, paramsMap)
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
	var taskLayoutParametersId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var taskLayoutParametersDataObject  = rowObj.data('taskLayoutParameters');
    	taskLayoutParametersId = taskLayoutParametersDataObject['taskLayoutParametersId'];
	}
    else
	{
    	taskLayoutParametersId = document.getElementById('idValueForTaskLayoutParameters').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var taskLayoutParametersSearchParams = {'taskLayoutParametersId':taskLayoutParametersId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	taskLayoutParametersSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var taskLayoutParametersJsonData = {'paramsInfo':JSON.stringify(taskLayoutParametersSearchParams),  'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskLayoutParameters = responseData['taskLayoutParameters'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="taskLayoutParametersRowActionButtonDiv"]').hide();
            		if(taskLayoutParameters.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = taskLayoutParameters['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(taskLayoutParameters['vwTxnStatus']);
            			selectedRowObj.find('[data-name="taskLayoutParametersActionButton"]').text(taskLayoutParameters["nextAction"]);
            			selectedRowObj.find('[data-name="taskLayoutParametersActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setTaskLayoutParametersInViewPage(taskLayoutParameters);
    			}
            }
        }
    });	
}
function downloadTaskLayoutParametersData()
{		
	var taskLayoutParameters = {};
    var taskLayoutParametersJsonData = {'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=TaskLayoutParameters");
            }
        }
    });
}
function uploadTaskLayoutParametersData(fileInfo)
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
	var taskLayoutParameters = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var taskLayoutParametersJsonData = {'paramsInfo':JSON.stringify(taskLayoutParameters),  'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=TaskLayoutParameters");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForTaskLayoutParameters()
{
    var taskLayoutParametersJsonData = {'objectType' : 'TaskLayoutParameters'};
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
        data: taskLayoutParametersJsonData,
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



function doTaskLayoutParametersLengthValidation(taskLayoutParametersDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(taskLayoutParametersDataObject['parameterName'], 500))
	{
		alertMessage += "\n Parameter Name length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskLayoutParametersDataObject['parameterValueType'], 40))
	{
		alertMessage += "\n Parameter Value Type length is more than allowed(40)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskLayoutParametersDataObject['parameterValue'], 40))
	{
		alertMessage += "\n Parameter Value length is more than allowed(40)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskLayoutParametersManadatoryValidation(taskLayoutParametersDataObject, paramsMap)
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
		
	var parameterName = taskLayoutParametersDataObject['parameterName'];
	if(!parameterName || parameterName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskLayoutParameters_parameterName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Parameter Name";
		    validationPassed = false;
		}
	}
	
	var parameterValueType = taskLayoutParametersDataObject['parameterValueType'];
	if(!parameterValueType || parameterValueType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskLayoutParameters_parameterValueType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Parameter Value Type";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskLayoutParametersValidation(taskLayoutParametersDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doTaskLayoutParametersLengthValidation(taskLayoutParametersDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doTaskLayoutParametersManadatoryValidation(taskLayoutParametersDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
