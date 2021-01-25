/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'TaskTimeCalculationType' : 'FormWBEntity:TaskExecutionInfo_taskTimeCalculationType' 
 *	 * 'FirstRunDateCalculationMethod' : 'FormWBEntity:TaskExecutionInfo_firstRunDateCalculationMethod' 
 *	 * 'FirstRunDateGapInYears' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInYears' 
 *	 * 'FirstRunDateGapInMonths' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInMonths' 
 *	 * 'FirstRunDateGapInDays' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInDays' 
 *	 * 'FirstRunDateGapInHours' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInHours' 
 *	 * 'FirstRunDateGapInMinutes' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInMinutes' 
 *	 * 'FirstRunDateGapInSeconds' : 'FormWBEntity:TaskExecutionInfo_firstRunDateGapInSeconds' 
 *	
 */
var gInitParamsObjForTaskExecutionInfo = {};
var gTaskExecutionInfoRequestUnderProcess = 0;
function selectThisTaskExecutionInfoForEdit(taskExecutionInfoRowElement)
{
    var taskExecutionInfoDataObject  = $(taskExecutionInfoRowElement).data('taskExecutionInfo');
    var taskExecutionInfoList = $('[data-name="taskExecutionInfoSearchResults"]').find('[data-name="taskExecutionInfoRow"]');
	taskExecutionInfoList.data("is-selected", 0);	
	$(taskExecutionInfoRowElement).data("is-selected", 1);
	setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject);
}

function setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject, paramsMap)
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
	var taskExecutionInfoId = taskExecutionInfoDataObject['taskExecutionInfoId'];
	$('#'+prefix+'idValueForTaskExecutionInfo').val(taskExecutionInfoId);
		
	//Combo
	if(taskExecutionInfoDataObject.hasOwnProperty('taskTimeCalculationType'))
	{
		var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
		$('#'+prefix+'TaskExecutionInfo_taskTimeCalculationType').val(taskTimeCalculationType)
	}
	
	//Combo
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateCalculationMethod').val(firstRunDateCalculationMethod)
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInYears').val(firstRunDateGapInYears);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMonths').val(firstRunDateGapInMonths);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInDays').val(firstRunDateGapInDays);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInHours').val(firstRunDateGapInHours);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMinutes').val(firstRunDateGapInMinutes);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInSeconds').val(firstRunDateGapInSeconds);
	}

	if(taskExecutionInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = taskExecutionInfoDataObject['vwTxnStatus'];
		$('[data-name="taskExecutionInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnTaskExecutionInfo(this)" >' +taskExecutionInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="taskExecutionInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="taskExecutionInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="taskExecutionInfoActionButtonDiv"]').hide();
	}
	$('[data-name="taskExecutionInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskExecutionInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadTaskExecutionInfoViewData(taskExecutionInfoDataObject);
	disbaleTaskExecutionInfoUpdateDisallowedFields(paramsMap);
	doAfterTaskExecutionInfoPanelRefreshed();
    
    
}
function disbaleTaskExecutionInfoUpdateDisallowedFields(paramsMap)
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
function loadTaskExecutionInfoViewData(taskExecutionInfoDataObject, paramsMap)
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
	var taskExecutionInfoId = taskExecutionInfoDataObject['taskExecutionInfoId'];
	$('#'+prefix+'idValueForTaskExecutionInfo').val(taskExecutionInfoId);
		
	if(taskExecutionInfoDataObject.hasOwnProperty('taskTimeCalculationType'))
	{
		var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_taskTimeCalculationType"]').text(taskTimeCalculationType);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateCalculationMethod"]').text(firstRunDateCalculationMethod);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInYears"]').text(firstRunDateGapInYears);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInMonths"]').text(firstRunDateGapInMonths);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInDays"]').text(firstRunDateGapInDays);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInHours"]').text(firstRunDateGapInHours);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInMinutes"]').text(firstRunDateGapInMinutes);
	}
	
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		
		parentElement.find('[data-name="'+prefix+'TaskExecutionInfo_firstRunDateGapInSeconds"]').text(firstRunDateGapInSeconds);
	}

}
function ajaxDemoForTaskExecutionInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForTaskExecutionInfo').textContent;	
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
			refreshPanelForTaskExecutionInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForTaskExecutionInfo(msg)
{
	var executeCustomAPIButtonForTaskExecutionInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForTaskExecutionInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:TaskExecutionInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForTaskExecutionInfo.click();
}
function refreshPanelForTaskExecutionInfo()
{
	var demoRefreshButtonForTaskExecutionInfo = document.getElementById("FormWBEntity:demoRefreshButtonTaskExecutionInfo");
	demoRefreshButtonForTaskExecutionInfo.click();
}
function initializePanelOnLoadForCreateTaskExecutionInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForTaskExecutionInfo = initParamsObj;
	initializeTaskExecutionInfoPage();	
	doAfterTaskExecutionInfoPanelRefreshed();
	initializeTaskExecutionInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForTaskExecutionInfoExt();
	fetchTaskExecutionInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="taskExecutionInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForTaskExecutionInfo = 0;
function initializePanelOnLoadForSearchTaskExecutionInfo()
{
	initializeTaskExecutionInfoPage();	
	initializeTaskExecutionInfoLookupFields();
	doAfterPanelInitializedForTaskExecutionInfoExt();
	gLoadDashboardResultsForTaskExecutionInfo =1;
	//retrieveTaskExecutionInfoList();
}
function initializePanelOnLoadForViewTaskExecutionInfo(urlParams)
{
	initializeTaskExecutionInfoPage();	
	doAfterTaskExecutionInfoPanelRefreshed();
	initializeTaskExecutionInfoLookupFields(urlParams);
	doAfterPanelInitializedForTaskExecutionInfoExt();
	retrieveTaskExecutionInfo(urlParams);
	initDatePicker();
	$('[data-name="taskExecutionInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeTaskExecutionInfoPage()
{
	initializePageOnload();	
	//initializeTaskExecutionInfoTemplateVariables();
}
function initializeTaskExecutionInfoTemplateVariables()
{	
	
	
	var taskExecutionInfoRowObj = $('[data-name="taskExecutionInfoList"]').find('[data-name="taskExecutionInfoRow"]');
	if(taskExecutionInfoRowObj.length > 0 && gTaskExecutionInfoSearchResultsTableRowTemplate.length == 0)
	{
		gTaskExecutionInfoSearchResultsTableRowTemplate = taskExecutionInfoRowObj[0].outerHTML;
		//taskExecutionInfoRowObj.remove();
	}
	if(gTaskExecutionInfoSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="taskExecutionInfoSearchResults"]').find('[data-name="taskExecutionInfoRow"]');
		if(searchResultsRowObj.length > 0 && gTaskExecutionInfoSearchResultsTableRowTemplate.length == 0)
		{
			gTaskExecutionInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrieveTaskExecutionInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var taskExecutionInfoId = paramsMap['taskExecutionInfoId'];
	var taskExecutionInfo = {};
	taskExecutionInfo['taskExecutionInfoId'] = taskExecutionInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				taskExecutionInfo[key] = paramsMap[key];
			}
	}
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
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
                	var taskExecutionInfoDataObject = responseData['taskExecutionInfoDataObject'];
    				setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForTaskExecutionInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchTaskExecutionInfoDefaultData();
}
function fetchTaskExecutionInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'TaskExecutionInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
            	document.getElementById('idValueForTaskExecutionInfo').value = '';
			    
            	setTaskExecutionInfoData(taskExecutionInfo);
            }
        }
    });	
}
function fetchTaskExecutionInfoTestData() 
{
	var taskExecutionInfo = getDataForTaskExecutionInfo();
    var searchJsonData = {'objectType' : 'TaskExecutionInfo', 'paramsInfo' : JSON.stringify(taskExecutionInfo)};
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
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
            	document.getElementById('idValueForTaskExecutionInfo').value = '';
			    
            	setTaskExecutionInfoData(taskExecutionInfo);
            }
        }
    });	
}
function setTaskExecutionInfoData(taskExecutionInfoDataObject)
{
	var prefix = "";
		
	//Combo
	if(taskExecutionInfoDataObject.hasOwnProperty('taskTimeCalculationType'))
	{
		var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
		$('#'+prefix+'TaskExecutionInfo_taskTimeCalculationType').val(taskTimeCalculationType);
	}
	
	//Combo
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateCalculationMethod').val(firstRunDateCalculationMethod);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInYears').val(firstRunDateGapInYears);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMonths').val(firstRunDateGapInMonths);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInDays').val(firstRunDateGapInDays);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInHours').val(firstRunDateGapInHours);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMinutes').val(firstRunDateGapInMinutes);
	}
	
	//Input
	if(taskExecutionInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		
		$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInSeconds').val(firstRunDateGapInSeconds);
	}

	$('[data-name="taskExecutionInfoActionButtonDiv"]').hide();
}
function initializeTaskExecutionInfoLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="taskExecutionInfoSearchResultsDiv"]');
	
        searchDiv.find('[data-name="taskInfoDBId"]').data("taskInfo-id", -1);

}

function initializeTaskExecutionInfoLookupSelectList(paramsMap)
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
function doAfterTaskExecutionInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForTaskExecutionInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForTaskExecutionInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'taskTimeCalculationType')
	{
	}
	else if(fieldName == 'firstRunDateCalculationMethod')
	{
	}

	doAfterSelectOptionChangedForTaskExecutionInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForTaskExecutionInfo(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForTaskExecutionInfoExt(fieldName)
}
function getEntityIdForTaskExecutionInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForTaskExecutionInfo').textContent;	
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
function openPrintPageForTaskExecutionInfo()
{
	var entityId = getEntityIdForTaskExecutionInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/TaskExecutionInfo.jsp?taskExecutionInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForTaskExecutionInfo(paramsMap)
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
	var taskExecutionInfo = {};
		
	//Combo
	if($("#"+prefix+"TaskExecutionInfo_taskTimeCalculationType").length == 1)
	{
		var taskTimeCalculationType = $('#'+prefix+'TaskExecutionInfo_taskTimeCalculationType').val();
		taskExecutionInfo['taskTimeCalculationType'] = taskTimeCalculationType;
	}
	
	//Combo
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateCalculationMethod").length == 1)
	{
		var firstRunDateCalculationMethod = $('#'+prefix+'TaskExecutionInfo_firstRunDateCalculationMethod').val();
		taskExecutionInfo['firstRunDateCalculationMethod'] = firstRunDateCalculationMethod;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInYears").length == 1)
	{
		var firstRunDateGapInYears = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInYears').val();
		taskExecutionInfo['firstRunDateGapInYears'] = firstRunDateGapInYears;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInMonths").length == 1)
	{
		var firstRunDateGapInMonths = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMonths').val();
		taskExecutionInfo['firstRunDateGapInMonths'] = firstRunDateGapInMonths;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInDays").length == 1)
	{
		var firstRunDateGapInDays = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInDays').val();
		taskExecutionInfo['firstRunDateGapInDays'] = firstRunDateGapInDays;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInHours").length == 1)
	{
		var firstRunDateGapInHours = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInHours').val();
		taskExecutionInfo['firstRunDateGapInHours'] = firstRunDateGapInHours;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInMinutes").length == 1)
	{
		var firstRunDateGapInMinutes = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMinutes').val();
		taskExecutionInfo['firstRunDateGapInMinutes'] = firstRunDateGapInMinutes;
	}
	
	//Input
	if($("#"+prefix+"TaskExecutionInfo_firstRunDateGapInSeconds").length == 1)
	{
		var firstRunDateGapInSeconds = $('#'+prefix+'TaskExecutionInfo_firstRunDateGapInSeconds').val();
		taskExecutionInfo['firstRunDateGapInSeconds'] = firstRunDateGapInSeconds;
	}

	
	
	if($("#"+prefix+"idValueForTaskInfo").length == 1)
	{
		var taskInfoId = $("#"+prefix+"idValueForTaskInfo").val();
		taskExecutionInfo['taskInfoId'] = taskInfoId; 
	}
	
	return taskExecutionInfo;
}
function createTaskExecutionInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var taskExecutionInfo = getDataForTaskExecutionInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				taskExecutionInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForTaskExecutionInfo)
	{
		paramsInfo[key] = gInitParamsObjForTaskExecutionInfo[key];
	}
	var validationObject = doTaskExecutionInfoValidation(taskExecutionInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	taskExecutionInfo['additionalParamsInfo'] = paramsInfo;
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskExecutionInfoRequestUnderProcess == 1)
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
            	gTaskExecutionInfoRequestUnderProcess = 0;
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gTaskExecutionInfoRequestUnderProcess = 0;
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
            	var taskExecutionInfoId = responseData['taskExecutionInfoId'];
            	var popupElement = $('[data-name="TaskExecutionInfoPopup"]');
            	
				
            	var taskExecutionInfoDataObject = responseData['taskExecutionInfoDataObject'];
				setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject);
				retrieveDependentTaskExecutionInfoList();
            }
        }
    });
}
function resetTableForTaskExecutionInfo()
{
	var taskExecutionInfoListElement = $("[data-name='taskExecutionInfoList']");
	var previousDataRowList  = taskExecutionInfoListElement.find('[data-name="taskExecutionInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForTaskExecutionInfo(paramsMap)
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
	$('#'+prefix+'idValueForTaskExecutionInfo').val('');
		
	//Combo
	$('#'+prefix+'TaskExecutionInfo_taskTimeCalculationType').val('');
	
	//Combo
	$('#'+prefix+'TaskExecutionInfo_firstRunDateCalculationMethod').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInYears').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMonths').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInDays').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInHours').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInMinutes').val('');
	
	//Input
	$('#'+prefix+'TaskExecutionInfo_firstRunDateGapInSeconds').val('');

	$('[data-name="taskExecutionInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="taskExecutionInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskExecutionInfoActionButtonDiv"]').hide();
	enableTaskExecutionInfoUpdateDisallowedFields(paramsMap);
    
}
function enableTaskExecutionInfoUpdateDisallowedFields(paramsMap)
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
function updateTaskExecutionInfo(paramsMap)
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
	var taskExecutionInfo = getDataForTaskExecutionInfo(paramsMap)
	if($("#"+prefix+"idValueForTaskExecutionInfo").length == 1)
	{
		var taskExecutionInfoId = $("#"+prefix+"idValueForTaskExecutionInfo").val();
		taskExecutionInfo['taskExecutionInfoId'] = taskExecutionInfoId;
	}
	var validationObject = doTaskExecutionInfoValidation(taskExecutionInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskExecutionInfoRequestUnderProcess == 1)
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
                    	gTaskExecutionInfoRequestUnderProcess = 0;
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gTaskExecutionInfoRequestUnderProcess = 0;
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
				
				retrieveDependentTaskExecutionInfoList();
            }
        }
    });
}
function deleteTaskExecutionInfo(paramsMap)
{		
	var taskExecutionInfoId = document.getElementById('idValueForTaskExecutionInfo').value;
	deleteSelectedTaskExecutionInfo(taskExecutionInfoId, paramsMap);
}
function deleteSelectedTaskExecutionInfo(taskExecutionInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var taskExecutionInfo = {};
	taskExecutionInfo['taskExecutionInfoId'] = taskExecutionInfoId;	
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
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
function loadSearchResultsForTaskExecutionInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'TaskExecutionInfo'};
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
            	var taskExecutionInfoSearchResultsElement = $("[data-name='taskExecutionInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var taskExecutionInfoDataObject = searchResultObjectsList[i];
					            		var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
            		var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
            		var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
            		var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
            		var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
            		var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
            		var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
            		var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		

            		var resultRowTemnplate = $(gTaskExecutionInfoSearchResultsTableRowTemplate);
					            		var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
            	    resultRowTemnplate.find("[data-name='taskTimeCalculationType']").text(taskTimeCalculationType);
            		var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateCalculationMethod']").text(firstRunDateCalculationMethod);
            		var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInYears']").text(firstRunDateGapInYears);
            		var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInMonths']").text(firstRunDateGapInMonths);
            		var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInDays']").text(firstRunDateGapInDays);
            		var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInHours']").text(firstRunDateGapInHours);
            		var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInMinutes']").text(firstRunDateGapInMinutes);
            		var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInSeconds']").text(firstRunDateGapInSeconds);

            	    resultRowTemnplate.data('taskExecutionInfo', taskExecutionInfoDataObject);
            	    taskExecutionInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gTaskExecutionInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForTaskExecutionInfoForEdit(taskExecutionInfoLinkElement)
{
	var taskExecutionInfoRowElement = $(taskExecutionInfoLinkElement).parents('[data-name="taskExecutionInfoRow"]');
    var taskExecutionInfoDataObject  = taskExecutionInfoRowElement.data('taskExecutionInfo');
	var taskExecutionInfoId = taskExecutionInfoDataObject['taskExecutionInfoId'];
	var taskExecutionInfoPopupElement = $('[data-name="TaskExecutionInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject);
	    $("#TaskExecutionInfo-tab").trigger("click");
	}
	else if(taskExecutionInfoPopupElement.length > 0)
	{
	    setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject);
		$('[data-name="TaskExecutionInfoPopup"]').find('[data-name="taskExecutionInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="TaskExecutionInfoPopup"]').find('[data-name="taskExecutionInfoViewFormButtonsDiv"]').show();
	    showPopup('TaskExecutionInfoPopup', taskExecutionInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewTaskExecutionInfo.html?taskExecutionInfoId="+taskExecutionInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openTaskExecutionInfoCreatePageForNew(linkElement)
{
	var taskExecutionInfoPopupElement = $('[data-name="TaskExecutionInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForTaskExecutionInfo();
	    $("#TaskExecutionInfo-tab").trigger("click");
    }
	else if(taskExecutionInfoPopupElement.length > 0)
	{
		resetFormForTaskExecutionInfo();
	    showPopup('TaskExecutionInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateTaskExecutionInfo.html";
    }
}
function showTaskExecutionInfoPopupForEdit(taskExecutionInfoLinkElement)
{
	var taskExecutionInfoRowElement = $(taskExecutionInfoLinkElement).parents('[data-name="taskExecutionInfoRow"]');
    var taskExecutionInfoDataObject  = taskExecutionInfoRowElement.data('taskExecutionInfo');
    setTaskExecutionInfoInViewPage(taskExecutionInfoDataObject);
    showPopup('TaskExecutionInfoPopup', taskExecutionInfoLinkElement, 1);
    $("#TaskExecutionInfo-tab").trigger("click");
}
function showTaskExecutionInfoPopupForNew(buttonElement)
{
	resetFormForTaskExecutionInfo();
    showPopup('TaskExecutionInfoPopup', buttonElement, 1);
    $("#TaskExecutionInfo-tab").trigger("click");
}
function deleteThisTaskExecutionInfo(taskExecutionInfoLinkElement, paramsMap)
{
	var taskExecutionInfoRowElement = $(taskExecutionInfoLinkElement).parents('[data-name="taskExecutionInfoRow"]');
    var taskExecutionInfoDataObject  = taskExecutionInfoRowElement.data('taskExecutionInfo');
	var taskExecutionInfoId = taskExecutionInfoDataObject['taskExecutionInfoId'];
	deleteSelectedTaskExecutionInfo(taskExecutionInfoId, paramsMap);
}
function displayTaskExecutionInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskExecutionInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var taskExecutionInfoSearchResults = searchResultsDiv.find('[data-name="taskExecutionInfoSearchResults"]');
	//taskExecutionInfoSearchResults.find('[data-name="taskExecutionInfoRow"]').remove();
	var previousDataRowList  = taskExecutionInfoSearchResults.find('[data-name="taskExecutionInfoRow"]');
	taskExecutionInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = taskExecutionInfoSearchResults.find('[data-name="taskExecutionInfoRow"]');
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
        var taskExecutionInfoDataObject = searchResultObjectsList[i];
        loadTaskExecutionInfoViewData(taskExecutionInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var taskInfoDisplayVal = taskExecutionInfoDataObject['taskInfoDisplayVal'];            		
	    resultRowTemplate.find("[data-name='taskInfoDisplayVal']").text(taskInfoDisplayVal);

		var vwTxnStatus = taskExecutionInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='taskExecutionInfoVwTxnStatus']").text(vwTxnStatus);
		if(taskExecutionInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="taskExecutionInfoActionButton"]').text(taskExecutionInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="taskExecutionInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="taskExecutionInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="taskExecutionInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="taskExecutionInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('taskExecutionInfo', taskExecutionInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    taskExecutionInfoSearchResults.append(resultRowTemplate);
	    processResultRowForTaskExecutionInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForTaskExecutionInfo == 1)
	{
    	getDashboardResultsForTaskExecutionInfo();
	}
}
var taskExecutionInfoSearchResultsDivName = "taskExecutionInfoSearchResultsDiv";
var gTaskExecutionInfoSearchInputParamsList = [];
function retrieveTaskExecutionInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + taskExecutionInfoSearchResultsDivName + '"]');
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
    fetchTaskExecutionInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchTaskExecutionInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskExecutionInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskExecutionInfoSearchResultsDivName; 
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
        'objectType': "TaskExecutionInfo"
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
            	this.showPreviousRecords = "showPreviousTaskExecutionInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskExecutionInfoRecords";
            	this.showNextRecords = "showNextTaskExecutionInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskExecutionInfoResults";
                var taskExecutionInfoList = responseData['taskExecutionInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayTaskExecutionInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'taskExecutionInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'taskExecutionInfoSearchResults', 'taskExecutionInfoRow', setTaskExecutionInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getTaskExecutionInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskExecutionInfoSearchResultsDivName + '"]');
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
				
		//Combo
		if(searchDiv.find('[data-name="taskExecutionInfoDB_taskTimeCalculationType"]').length == 1)
		{
		    var taskTimeCalculationType = searchDiv.find('[data-name="taskExecutionInfoDB_taskTimeCalculationType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskTimeCalculationType', 'userInputValue':taskTimeCalculationType});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateCalculationMethod"]').length == 1)
		{
		    var firstRunDateCalculationMethod = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateCalculationMethod"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateCalculationMethod', 'userInputValue':firstRunDateCalculationMethod});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInYears"]').length == 1)
		{
		    var firstRunDateGapInYears = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInYears"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInYears', 'userInputValue':firstRunDateGapInYears});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInMonths"]').length == 1)
		{
		    var firstRunDateGapInMonths = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInMonths"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInMonths', 'userInputValue':firstRunDateGapInMonths});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInDays"]').length == 1)
		{
		    var firstRunDateGapInDays = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInDays"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInDays', 'userInputValue':firstRunDateGapInDays});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInHours"]').length == 1)
		{
		    var firstRunDateGapInHours = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInHours"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInHours', 'userInputValue':firstRunDateGapInHours});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInMinutes"]').length == 1)
		{
		    var firstRunDateGapInMinutes = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInMinutes"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInMinutes', 'userInputValue':firstRunDateGapInMinutes});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInSeconds"]').length == 1)
		{
		    var firstRunDateGapInSeconds = searchDiv.find('[data-name="taskExecutionInfoDB_firstRunDateGapInSeconds"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInSeconds', 'userInputValue':firstRunDateGapInSeconds});
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
        gTaskExecutionInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskExecutionInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskExecutionInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + taskExecutionInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskExecutionInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskExecutionInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskExecutionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskExecutionInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchTaskExecutionInfoSearchResultsList);
}
function showPreviousTaskExecutionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskExecutionInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchTaskExecutionInfoSearchResultsList);
}
function showNextTaskExecutionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskExecutionInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchTaskExecutionInfoSearchResultsList);
}
function showPrevOrNextSearchTaskExecutionInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskExecutionInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextTaskExecutionInfoRecords(e);
    }
}

var gTaskInfo_TaskExecutionInfoSearchResultsTableRowTemplate =""; 
function initializeTaskInfoPopup_TaskInfo_TaskExecutionInfoLookupFields() 
{	
    var searchDiv = $('[data-name="TaskInfo_TaskExecutionInfoSearchDiv"]');
	
    
	if(gTaskInfo_TaskExecutionInfoSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="TaskInfo_TaskExecutionInfoSearchResults"]').find('[data-name="TaskInfo_TaskExecutionInfoRow"]');
		gTaskInfo_TaskExecutionInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayTaskInfo_TaskExecutionInfoList(searchResultObjectsList, parentElement)
{
    var taskInfoSearchResults = $('[data-name="TaskInfo_TaskExecutionInfoSearchResults"]');
	taskInfoSearchResults.find('[data-name="TaskInfo_TaskExecutionInfoRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gTaskInfo_TaskExecutionInfoSearchResultsTableRowTemplate);
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
var TaskInfo_TaskExecutionInfoSearchResultsDivName = "TaskInfo_TaskExecutionInfoSearchResultsDiv";
var gTaskInfo_TaskExecutionInfoSearchInputParamsList = [];
function getTaskInfo_TaskExecutionInfoSearchResults()
{
    var searchDiv = $('[data-name="TaskInfo_TaskExecutionInfoSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskExecutionInfoSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchTaskInfo_TaskExecutionInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchTaskInfo_TaskExecutionInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskInfo_TaskExecutionInfoSearchInputParams();
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
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_TaskExecutionInfoRecords";
            	this.showPreviousRecords = "showPreviousTaskInfo_TaskExecutionInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_TaskExecutionInfoRecords";
            	this.showNextRecords = "showNextTaskInfo_TaskExecutionInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskInfo_TaskExecutionInfoResults";
                var taskInfoList = responseData['taskInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', TaskInfo_TaskExecutionInfoSearchResultsDivName, 'TaskInfo_TaskExecutionInfoSearchResults', 'TaskInfo_TaskExecutionInfoRow', setTaskInfo_TaskExecutionInfoSearchInputParams, displayTaskInfo_TaskExecutionInfoList);
            }
        }
    });
}
function getTaskInfo_TaskExecutionInfoSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskExecutionInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="TaskInfo_TaskExecutionInfoSearchDiv"]');
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

	    
        gTaskInfo_TaskExecutionInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskInfo_TaskExecutionInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskInfo_TaskExecutionInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_TaskExecutionInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskInfo_TaskExecutionInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskInfo_TaskExecutionInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskInfo_TaskExecutionInfoRecords(e)
{
    getCurrentPageSearchResults(e, TaskInfo_TaskExecutionInfoSearchResultsDivName, fetchTaskInfo_TaskExecutionInfoSearchResultsList);
}
function showPreviousTaskInfo_TaskExecutionInfoRecords()
{
    getPreviousPageSearchResults(TaskInfo_TaskExecutionInfoSearchResultsDivName, fetchTaskInfo_TaskExecutionInfoSearchResultsList);
}
function showNextTaskInfo_TaskExecutionInfoRecords()
{
    getNextPageSearchResults(TaskInfo_TaskExecutionInfoSearchResultsDivName, fetchTaskInfo_TaskExecutionInfoSearchResultsList);
}
function showPrevOrNextSearchTaskInfo_TaskExecutionInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskInfo_TaskExecutionInfoRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextTaskInfo_TaskExecutionInfoRecords();
    }
}
function setTaskInfo_TaskExecutionInfo(taskInfoRowElement) 
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var parentElement = $(taskInfoRowElement).parents('[data-name="TaskInfoPopup_TaskInfo_TaskExecutionInfo"]');
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
		var entityName = "TaskInfo_TaskExecutionInfo";
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
function lookupRowSelectedForTaskExecutionInfo(attributeName, attributeId)
{
	var taskExecutionInfo = getDataForTaskExecutionInfo();
	taskExecutionInfo['attributeName'] = attributeName;
	taskExecutionInfo['attributeId'] = attributeId;
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
            	setTaskExecutionInfoData(taskExecutionInfo);
            }
        }
    });	
}
function selectOptionChangedForTaskExecutionInfo(attributeName)
{
	var taskExecutionInfo = getDataForTaskExecutionInfo();
	taskExecutionInfo['attributeName'] = attributeName;
    var taskExecutionInfoJsonData = {'paramsInfo': JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
            	setTaskExecutionInfoData(taskExecutionInfo);
            	doAfterTaskExecutionInfoPanelRefreshed();
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
function retrieveDependentTaskExecutionInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'TaskExecutionInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskExecutionInfoList = responseData['taskExecutionInfoList'];
            	var taskExecutionInfoListElement = $("[data-name='taskExecutionInfoList']");
            	var previousDataRowList  = taskExecutionInfoListElement.find('[data-name="taskExecutionInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = taskExecutionInfoListElement.find('[data-name="taskExecutionInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//taskExecutionInfoListElement.empty();
            	for(var i=0; i<taskExecutionInfoList.length; i++)
        		{
            		var taskExecutionInfoDataObject = taskExecutionInfoList[i];
            		//var resultRowTemplate = $(gTaskExecutionInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];            		
				    resultRowTemplate.find("[data-name='taskTimeCalculationType']").text(taskTimeCalculationType);
					var firstRunDateCalculationMethod = taskExecutionInfoDataObject['firstRunDateCalculationMethod'];            		
				    resultRowTemplate.find("[data-name='firstRunDateCalculationMethod']").text(firstRunDateCalculationMethod);
					var firstRunDateGapInYears = taskExecutionInfoDataObject['firstRunDateGapInYears'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInYears']").text(firstRunDateGapInYears);
					var firstRunDateGapInMonths = taskExecutionInfoDataObject['firstRunDateGapInMonths'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInMonths']").text(firstRunDateGapInMonths);
					var firstRunDateGapInDays = taskExecutionInfoDataObject['firstRunDateGapInDays'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInDays']").text(firstRunDateGapInDays);
					var firstRunDateGapInHours = taskExecutionInfoDataObject['firstRunDateGapInHours'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInHours']").text(firstRunDateGapInHours);
					var firstRunDateGapInMinutes = taskExecutionInfoDataObject['firstRunDateGapInMinutes'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInMinutes']").text(firstRunDateGapInMinutes);
					var firstRunDateGapInSeconds = taskExecutionInfoDataObject['firstRunDateGapInSeconds'];            		
				    resultRowTemplate.find("[data-name='firstRunDateGapInSeconds']").text(firstRunDateGapInSeconds);

					
					
            	    resultRowTemplate.data('taskExecutionInfo', taskExecutionInfoDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    taskExecutionInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForTaskExecutionInfo(customEventName)
{
	var taskExecutionInfoId = document.getElementById('idValueForTaskExecutionInfo').value;
	var taskExecutionInfo = {'taskExecutionInfoId':taskExecutionInfoId, 'customEventName':customEventName};
    var taskExecutionInfoJsonData = {'paramsInfo':JSON.stringify(taskExecutionInfo), 'objectType' : 'TaskExecutionInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForTaskExecutionInfoExt(customEventName);
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
        		setTaskExecutionInfoInViewPage(taskExecutionInfo);
            }
    		doAfterExecuteCustomAPIForTaskExecutionInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnTaskExecutionInfo(e, paramsMap)
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
	var taskExecutionInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var taskExecutionInfoDataObject  = rowObj.data('taskExecutionInfo');
    	taskExecutionInfoId = taskExecutionInfoDataObject['taskExecutionInfoId'];
	}
    else
	{
    	taskExecutionInfoId = document.getElementById('idValueForTaskExecutionInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var taskExecutionInfoSearchParams = {'taskExecutionInfoId':taskExecutionInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	taskExecutionInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var taskExecutionInfoJsonData = {'paramsInfo':JSON.stringify(taskExecutionInfoSearchParams),  'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskExecutionInfo = responseData['taskExecutionInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="taskExecutionInfoRowActionButtonDiv"]').hide();
            		if(taskExecutionInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = taskExecutionInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(taskExecutionInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="taskExecutionInfoActionButton"]').text(taskExecutionInfo["nextAction"]);
            			selectedRowObj.find('[data-name="taskExecutionInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setTaskExecutionInfoInViewPage(taskExecutionInfo);
    			}
            }
        }
    });	
}
function downloadTaskExecutionInfoData()
{		
	var taskExecutionInfo = {};
    var taskExecutionInfoJsonData = {'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=TaskExecutionInfo");
            }
        }
    });
}
function uploadTaskExecutionInfoData(fileInfo)
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
	var taskExecutionInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var taskExecutionInfoJsonData = {'paramsInfo':JSON.stringify(taskExecutionInfo),  'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=TaskExecutionInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForTaskExecutionInfo()
{
    var taskExecutionInfoJsonData = {'objectType' : 'TaskExecutionInfo'};
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
        data: taskExecutionInfoJsonData,
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



function doTaskExecutionInfoLengthValidation(taskExecutionInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['taskTimeCalculationType'], 20))
	{
		alertMessage += "\n Task Time Calculation Type length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateCalculationMethod'], 20))
	{
		alertMessage += "\n First Run Date Calculation Method length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInYears'], 10))
	{
		alertMessage += "\n First Run Date Gap In Years length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInMonths'], 10))
	{
		alertMessage += "\n First Run Date Gap In Months length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInDays'], 10))
	{
		alertMessage += "\n First Run Date Gap In Days length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInHours'], 10))
	{
		alertMessage += "\n First Run Date Gap In Hours length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInMinutes'], 10))
	{
		alertMessage += "\n First Run Date Gap In Minutes length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskExecutionInfoDataObject['firstRunDateGapInSeconds'], 10))
	{
		alertMessage += "\n First Run Date Gap In Seconds length is more than allowed(10)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskExecutionInfoManadatoryValidation(taskExecutionInfoDataObject, paramsMap)
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
		
	var taskTimeCalculationType = taskExecutionInfoDataObject['taskTimeCalculationType'];
	if(!taskTimeCalculationType || taskTimeCalculationType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskExecutionInfo_taskTimeCalculationType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Task Time Calculation Type";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskExecutionInfoValidation(taskExecutionInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doTaskExecutionInfoLengthValidation(taskExecutionInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doTaskExecutionInfoManadatoryValidation(taskExecutionInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
