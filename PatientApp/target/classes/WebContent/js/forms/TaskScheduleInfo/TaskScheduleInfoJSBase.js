/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'TaskInfo' : 'FormWBEntity:TaskScheduleInfo_taskInfo' 
 *	 * 'TargetEntityId' : 'FormWBEntity:TaskScheduleInfo_targetEntityId' 
 *	 * 'TargetUserId' : 'FormWBEntity:TaskScheduleInfo_targetUserId' 
 *	 * 'NotificationMedium' : 'FormWBEntity:TaskScheduleInfo_notificationMedium' 
 *	 * 'NotificationLastSentTime' : 'FormWBEntity:TaskScheduleInfo_notificationLastSentTime' 
 *	 * 'NextNotificationTime' : 'FormWBEntity:TaskScheduleInfo_nextNotificationTime' 
 *	
 */
var gInitParamsObjForTaskScheduleInfo = {};
var gTaskScheduleInfoRequestUnderProcess = 0;
function selectThisTaskScheduleInfoForEdit(taskScheduleInfoRowElement)
{
    var taskScheduleInfoDataObject  = $(taskScheduleInfoRowElement).data('taskScheduleInfo');
    var taskScheduleInfoList = $('[data-name="taskScheduleInfoSearchResults"]').find('[data-name="taskScheduleInfoRow"]');
	taskScheduleInfoList.data("is-selected", 0);	
	$(taskScheduleInfoRowElement).data("is-selected", 1);
	setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject);
}

function setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject, paramsMap)
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
	var taskScheduleInfoId = taskScheduleInfoDataObject['taskScheduleInfoId'];
	$('#'+prefix+'idValueForTaskScheduleInfo').val(taskScheduleInfoId);
		
	//Lookup
	if(taskScheduleInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskScheduleInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskInfo['taskInfoId'];
			var taskInfoLinkElement = $('#'+prefix+'TaskScheduleInfo_taskInfoId');
			taskInfoLinkElement.data("taskInfo-id", taskInfoId);
			var taskInfoNextElements = taskInfoLinkElement.parents(".form-group").next();
			for(var i=0; i< taskInfoNextElements.length; i++)
			{
				var inputElement = $(taskInfoNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(taskInfo[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(taskScheduleInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
		$('#'+prefix+'TaskScheduleInfo_targetEntityId').val(targetEntityId);
	}
	
	//Input
	if(taskScheduleInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
		$('#'+prefix+'TaskScheduleInfo_targetUserId').val(targetUserId);
	}
	
	//Combo
	if(taskScheduleInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
		$('#'+prefix+'TaskScheduleInfo_notificationMedium').val(notificationMedium)
	}
	
	//DateTime
	if(taskScheduleInfoDataObject.hasOwnProperty('notificationLastSentTime'))
	{
		var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
		$('#'+prefix+'TaskScheduleInfo_notificationLastSentTime').val(notificationLastSentTime);
	}
	
	//DateTime
	if(taskScheduleInfoDataObject.hasOwnProperty('nextNotificationTime'))
	{
		var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		
		$('#'+prefix+'TaskScheduleInfo_nextNotificationTime').val(nextNotificationTime);
	}

	if(taskScheduleInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = taskScheduleInfoDataObject['vwTxnStatus'];
		$('[data-name="taskScheduleInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnTaskScheduleInfo(this)" >' +taskScheduleInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="taskScheduleInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="taskScheduleInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="taskScheduleInfoActionButtonDiv"]').hide();
	}
	$('[data-name="taskScheduleInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskScheduleInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadTaskScheduleInfoViewData(taskScheduleInfoDataObject);
	disbaleTaskScheduleInfoUpdateDisallowedFields(paramsMap);
	doAfterTaskScheduleInfoPanelRefreshed();
    
    
}
function disbaleTaskScheduleInfoUpdateDisallowedFields(paramsMap)
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
function loadTaskScheduleInfoViewData(taskScheduleInfoDataObject, paramsMap)
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
	var taskScheduleInfoId = taskScheduleInfoDataObject['taskScheduleInfoId'];
	$('#'+prefix+'idValueForTaskScheduleInfo').val(taskScheduleInfoId);
		
	if(taskScheduleInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskScheduleInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskScheduleInfoDataObject['taskInfoId'];
			var taskInfoDisplayVal = taskScheduleInfoDataObject['taskInfoDisplayVal'];
			parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_taskInfo"]').text(taskInfoDisplayVal);
			//taskInfoLinkElement.data("taskInfo-id", taskInfoId);
		}
	}
	
	if(taskScheduleInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
		parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_targetEntityId"]').text(targetEntityId);
	}
	
	if(taskScheduleInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
		parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_targetUserId"]').text(targetUserId);
	}
	
	if(taskScheduleInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
		parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_notificationMedium"]').text(notificationMedium);
	}
	
	var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
	parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_notificationLastSentTime"]').text(notificationLastSentTime);
	
	var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		
	parentElement.find('[data-name="'+prefix+'TaskScheduleInfo_nextNotificationTime"]').text(nextNotificationTime);

}
function ajaxDemoForTaskScheduleInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForTaskScheduleInfo').textContent;	
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
			refreshPanelForTaskScheduleInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForTaskScheduleInfo(msg)
{
	var executeCustomAPIButtonForTaskScheduleInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForTaskScheduleInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:TaskScheduleInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForTaskScheduleInfo.click();
}
function refreshPanelForTaskScheduleInfo()
{
	var demoRefreshButtonForTaskScheduleInfo = document.getElementById("FormWBEntity:demoRefreshButtonTaskScheduleInfo");
	demoRefreshButtonForTaskScheduleInfo.click();
}
function initializePanelOnLoadForCreateTaskScheduleInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForTaskScheduleInfo = initParamsObj;
	initializeTaskScheduleInfoPage();	
	doAfterTaskScheduleInfoPanelRefreshed();
	initializeTaskScheduleInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForTaskScheduleInfoExt();
	fetchTaskScheduleInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="taskScheduleInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForTaskScheduleInfo = 0;
function initializePanelOnLoadForSearchTaskScheduleInfo()
{
	initializeTaskScheduleInfoPage();	
	initializeTaskScheduleInfoLookupFields();
	doAfterPanelInitializedForTaskScheduleInfoExt();
	gLoadDashboardResultsForTaskScheduleInfo =1;
	//retrieveTaskScheduleInfoList();
}
function initializePanelOnLoadForViewTaskScheduleInfo(urlParams)
{
	initializeTaskScheduleInfoPage();	
	doAfterTaskScheduleInfoPanelRefreshed();
	initializeTaskScheduleInfoLookupFields(urlParams);
	doAfterPanelInitializedForTaskScheduleInfoExt();
	retrieveTaskScheduleInfo(urlParams);
	initDatePicker();
	$('[data-name="taskScheduleInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeTaskScheduleInfoPage()
{
	initializePageOnload();	
	//initializeTaskScheduleInfoTemplateVariables();
}
function initializeTaskScheduleInfoTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="taskScheduleInfoSearchResults"]').find('[data-name="taskScheduleInfoRow"]');
	if(searchResultsRowObj.length > 0 && gTaskScheduleInfoSearchResultsTableRowTemplate.length == 0)
	{
		gTaskScheduleInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveTaskScheduleInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var taskScheduleInfoId = paramsMap['taskScheduleInfoId'];
	var taskScheduleInfo = {};
	taskScheduleInfo['taskScheduleInfoId'] = taskScheduleInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				taskScheduleInfo[key] = paramsMap[key];
			}
	}
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
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
                	var taskScheduleInfoDataObject = responseData['taskScheduleInfoDataObject'];
    				setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForTaskScheduleInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchTaskScheduleInfoDefaultData();
}
function fetchTaskScheduleInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'TaskScheduleInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
            	document.getElementById('idValueForTaskScheduleInfo').value = '';
			    
            	setTaskScheduleInfoData(taskScheduleInfo);
            }
        }
    });	
}
function fetchTaskScheduleInfoTestData() 
{
	var taskScheduleInfo = getDataForTaskScheduleInfo();
    var searchJsonData = {'objectType' : 'TaskScheduleInfo', 'paramsInfo' : JSON.stringify(taskScheduleInfo)};
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
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
            	document.getElementById('idValueForTaskScheduleInfo').value = '';
			    
            	setTaskScheduleInfoData(taskScheduleInfo);
            }
        }
    });	
}
function setTaskScheduleInfoData(taskScheduleInfoDataObject)
{
	var prefix = "";
		
	//Lookup
	if(taskScheduleInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskScheduleInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskInfo['taskInfoId'];
			var taskInfoLinkElement = $('#'+prefix+'TaskScheduleInfo_taskInfoId');
			taskInfoLinkElement.data("taskInfo-id", taskInfoId);
			var taskInfoNextElements = taskInfoLinkElement.parents(".form-group").next();
			for(var i=0; i< taskInfoNextElements.length; i++)
			{
				var inputElement = $(taskInfoNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(taskInfo[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(taskScheduleInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
		$('#'+prefix+'TaskScheduleInfo_targetEntityId').val(targetEntityId);
	}
	
	//Input
	if(taskScheduleInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
		$('#'+prefix+'TaskScheduleInfo_targetUserId').val(targetUserId);
	}
	
	//Combo
	if(taskScheduleInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
		$('#'+prefix+'TaskScheduleInfo_notificationMedium').val(notificationMedium);
	}
	
	//DateTime
	if(taskScheduleInfoDataObject.hasOwnProperty('notificationLastSentTime'))
	{
		var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
		$('#'+prefix+'TaskScheduleInfo_notificationLastSentTime').val(notificationLastSentTime);
	}
	
	//DateTime
	if(taskScheduleInfoDataObject.hasOwnProperty('nextNotificationTime'))
	{
		var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		
		$('#'+prefix+'TaskScheduleInfo_nextNotificationTime').val(nextNotificationTime);
	}

	$('[data-name="taskScheduleInfoActionButtonDiv"]').hide();
}
function initializeTaskScheduleInfoLookupFields(paramsMap) 
{
		
	$("#TaskScheduleInfo_taskInfoId").data("taskInfo-id", -1);
  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="taskScheduleInfoSearchResultsDiv"]');
		
	
    searchDiv.find('[data-name="taskScheduleInfoDB_taskInfoId"]').data("taskInfo-id", -1);
  
	
	
	  
	
	
	  
	
	
	  
	
	
	  
	
	
	
    
}

function doAfterTaskScheduleInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForTaskScheduleInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForTaskScheduleInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'notificationMedium')
	{
	}

	doAfterSelectOptionChangedForTaskScheduleInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForTaskScheduleInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'taskInfo')
	{
	}

	doAfterLookupRowChangedForTaskScheduleInfoExt(fieldName)
}
function getEntityIdForTaskScheduleInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForTaskScheduleInfo').textContent;	
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
function openPrintPageForTaskScheduleInfo()
{
	var entityId = getEntityIdForTaskScheduleInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/TaskScheduleInfo.jsp?taskScheduleInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}
function hideTaskInfoForTaskScheduleInfo()
{
		
		document.getElementById('TaskScheduleInfo_taskInfoId').parentElement.style.display = "none";
	   	
		document.getElementById('TaskScheduleInfo_taskInfotaskName').parentElement.style.display = "none";	
	   	
		//document.getElementById('TaskScheduleInfo_taskInfoNewLinkID').parentElement.style.display = "none";
}

function showTaskInfoForTaskScheduleInfo()
{
		
		document.getElementById('TaskScheduleInfo_taskInfoId').parentElement.style.display = "table-row";
	   	
		document.getElementById('TaskScheduleInfo_taskInfotaskName').parentElement.style.display = "table-row";	
	   	
		//document.getElementById('TaskScheduleInfo_taskInfoNewLinkID').parentElement.style.display = "table-row";
}


function getDataForTaskScheduleInfo(paramsMap)
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
	var taskScheduleInfo = {};
		
	//Lookup
	if($("#"+prefix+"TaskScheduleInfo_taskInfoId").length == 1)
	{
		var taskInfoId = $('#'+prefix+'TaskScheduleInfo_taskInfoId').data("taskInfo-id");
		taskScheduleInfo['taskInfoId'] = taskInfoId;
	}
	
	//Input
	if($("#"+prefix+"TaskScheduleInfo_targetEntityId").length == 1)
	{
		var targetEntityId = $('#'+prefix+'TaskScheduleInfo_targetEntityId').val();
		taskScheduleInfo['targetEntityId'] = targetEntityId;
	}
	
	//Input
	if($("#"+prefix+"TaskScheduleInfo_targetUserId").length == 1)
	{
		var targetUserId = $('#'+prefix+'TaskScheduleInfo_targetUserId').val();
		taskScheduleInfo['targetUserId'] = targetUserId;
	}
	
	//Combo
	if($("#"+prefix+"TaskScheduleInfo_notificationMedium").length == 1)
	{
		var notificationMedium = $('#'+prefix+'TaskScheduleInfo_notificationMedium').val();
		taskScheduleInfo['notificationMedium'] = notificationMedium;
	}
	
	//Datetime
	if($("#"+prefix+"TaskScheduleInfo_notificationLastSentTime").length == 1)
	{
		var notificationLastSentTime = $('#'+prefix+'TaskScheduleInfo_notificationLastSentTime').val();
		taskScheduleInfo['notificationLastSentTime'] = notificationLastSentTime;
	}
	
	//Datetime
	if($("#"+prefix+"TaskScheduleInfo_nextNotificationTime").length == 1)
	{
		var nextNotificationTime = $('#'+prefix+'TaskScheduleInfo_nextNotificationTime').val();
		taskScheduleInfo['nextNotificationTime'] = nextNotificationTime;
	}

	
	return taskScheduleInfo;
}
function createTaskScheduleInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var taskScheduleInfo = getDataForTaskScheduleInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				taskScheduleInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForTaskScheduleInfo)
	{
		paramsInfo[key] = gInitParamsObjForTaskScheduleInfo[key];
	}
	var validationObject = doTaskScheduleInfoValidation(taskScheduleInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	taskScheduleInfo['additionalParamsInfo'] = paramsInfo;
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskScheduleInfoRequestUnderProcess == 1)
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
            	gTaskScheduleInfoRequestUnderProcess = 0;
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gTaskScheduleInfoRequestUnderProcess = 0;
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
            	var taskScheduleInfoId = responseData['taskScheduleInfoId'];
            	var popupElement = $('[data-name="TaskScheduleInfoPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var taskScheduleInfoDataObject = responseData['taskScheduleInfoDataObject'];
    				setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewTaskScheduleInfo.html?taskScheduleInfoId="+taskScheduleInfoId; 
        		}
				
            }
        }
    });
}
function resetTableForTaskScheduleInfo()
{
	var taskScheduleInfoListElement = $("[data-name='taskScheduleInfoList']");
	var previousDataRowList  = taskScheduleInfoListElement.find('[data-name="taskScheduleInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForTaskScheduleInfo(paramsMap)
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
	$('#'+prefix+'idValueForTaskScheduleInfo').val('');
		
	//Lookup
	var taskInfoLinkElement = $('#'+prefix+'TaskScheduleInfo_taskInfoId');
	taskInfoLinkElement.data("taskInfo-id", -1);
	var taskInfoNextElements = taskInfoLinkElement.parents(".form-group").next();
	for(var i=0; i< taskInfoNextElements.length; i++)
	{
		var inputElement = $(taskInfoNextElements[i]).find('input');
		if(inputElement.data("is-lookup-field") != 1)
		{
			break;
		}
		inputElement.val('');		
	}
	
	//Input
	$('#'+prefix+'TaskScheduleInfo_targetEntityId').val('');
	
	//Input
	$('#'+prefix+'TaskScheduleInfo_targetUserId').val('');
	
	//Combo
	$('#'+prefix+'TaskScheduleInfo_notificationMedium').val('');
	
	//DateTime
	$('#'+prefix+'TaskScheduleInfo_notificationLastSentTime').val('');
	
	//DateTime
	$('#'+prefix+'TaskScheduleInfo_nextNotificationTime').val('');

	$('[data-name="taskScheduleInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="taskScheduleInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskScheduleInfoActionButtonDiv"]').hide();
	enableTaskScheduleInfoUpdateDisallowedFields(paramsMap);
    
}
function enableTaskScheduleInfoUpdateDisallowedFields(paramsMap)
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
function updateTaskScheduleInfo(paramsMap)
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
	var taskScheduleInfo = getDataForTaskScheduleInfo(paramsMap)
	if($("#"+prefix+"idValueForTaskScheduleInfo").length == 1)
	{
		var taskScheduleInfoId = $("#"+prefix+"idValueForTaskScheduleInfo").val();
		taskScheduleInfo['taskScheduleInfoId'] = taskScheduleInfoId;
	}
	var validationObject = doTaskScheduleInfoValidation(taskScheduleInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskScheduleInfoRequestUnderProcess == 1)
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
                    	gTaskScheduleInfoRequestUnderProcess = 0;
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gTaskScheduleInfoRequestUnderProcess = 0;
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
function deleteTaskScheduleInfo(paramsMap)
{		
	var taskScheduleInfoId = document.getElementById('idValueForTaskScheduleInfo').value;
	deleteSelectedTaskScheduleInfo(taskScheduleInfoId, paramsMap);
}
function deleteSelectedTaskScheduleInfo(taskScheduleInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var taskScheduleInfo = {};
	taskScheduleInfo['taskScheduleInfoId'] = taskScheduleInfoId;	
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
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
function loadSearchResultsForTaskScheduleInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'TaskScheduleInfo'};
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
            	var taskScheduleInfoSearchResultsElement = $("[data-name='taskScheduleInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var taskScheduleInfoDataObject = searchResultObjectsList[i];
					            		var taskInfo = taskScheduleInfoDataObject['taskInfo'];            		
            		var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
            		var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
            		var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
            		var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
            		var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		

            		var resultRowTemnplate = $(gTaskScheduleInfoSearchResultsTableRowTemplate);
					            		var taskInfo = taskScheduleInfoDataObject['taskInfo'];            		
            	    resultRowTemnplate.find("[data-name='taskInfo']").text(taskInfo);
            		var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
            	    resultRowTemnplate.find("[data-name='targetEntityId']").text(targetEntityId);
            		var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
            	    resultRowTemnplate.find("[data-name='targetUserId']").text(targetUserId);
            		var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
            	    resultRowTemnplate.find("[data-name='notificationMedium']").text(notificationMedium);
            		var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
            	    resultRowTemnplate.find("[data-name='notificationLastSentTime']").text(notificationLastSentTime);
            		var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		
            	    resultRowTemnplate.find("[data-name='nextNotificationTime']").text(nextNotificationTime);

            	    resultRowTemnplate.data('taskScheduleInfo', taskScheduleInfoDataObject);
            	    taskScheduleInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gTaskScheduleInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForTaskScheduleInfoForEdit(taskScheduleInfoLinkElement)
{
	var taskScheduleInfoRowElement = $(taskScheduleInfoLinkElement).parents('[data-name="taskScheduleInfoRow"]');
    var taskScheduleInfoDataObject  = taskScheduleInfoRowElement.data('taskScheduleInfo');
	var taskScheduleInfoId = taskScheduleInfoDataObject['taskScheduleInfoId'];
	var taskScheduleInfoPopupElement = $('[data-name="TaskScheduleInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject);
	    $("#TaskScheduleInfo-tab").trigger("click");
	}
	else if(taskScheduleInfoPopupElement.length > 0)
	{
	    setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject);
		$('[data-name="TaskScheduleInfoPopup"]').find('[data-name="taskScheduleInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="TaskScheduleInfoPopup"]').find('[data-name="taskScheduleInfoViewFormButtonsDiv"]').show();
	    showPopup('TaskScheduleInfoPopup', taskScheduleInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewTaskScheduleInfo.html?taskScheduleInfoId="+taskScheduleInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openTaskScheduleInfoCreatePageForNew(linkElement)
{
	var taskScheduleInfoPopupElement = $('[data-name="TaskScheduleInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForTaskScheduleInfo();
	    $("#TaskScheduleInfo-tab").trigger("click");
    }
	else if(taskScheduleInfoPopupElement.length > 0)
	{
		resetFormForTaskScheduleInfo();
	    showPopup('TaskScheduleInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateTaskScheduleInfo.html";
    }
}
function showTaskScheduleInfoPopupForEdit(taskScheduleInfoLinkElement)
{
	var taskScheduleInfoRowElement = $(taskScheduleInfoLinkElement).parents('[data-name="taskScheduleInfoRow"]');
    var taskScheduleInfoDataObject  = taskScheduleInfoRowElement.data('taskScheduleInfo');
    setTaskScheduleInfoInViewPage(taskScheduleInfoDataObject);
    showPopup('TaskScheduleInfoPopup', taskScheduleInfoLinkElement, 1);
    $("#TaskScheduleInfo-tab").trigger("click");
}
function showTaskScheduleInfoPopupForNew(buttonElement)
{
	resetFormForTaskScheduleInfo();
    showPopup('TaskScheduleInfoPopup', buttonElement, 1);
    $("#TaskScheduleInfo-tab").trigger("click");
}
function deleteThisTaskScheduleInfo(taskScheduleInfoLinkElement, paramsMap)
{
	var taskScheduleInfoRowElement = $(taskScheduleInfoLinkElement).parents('[data-name="taskScheduleInfoRow"]');
    var taskScheduleInfoDataObject  = taskScheduleInfoRowElement.data('taskScheduleInfo');
	var taskScheduleInfoId = taskScheduleInfoDataObject['taskScheduleInfoId'];
	deleteSelectedTaskScheduleInfo(taskScheduleInfoId, paramsMap);
}
function displayTaskScheduleInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskScheduleInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var taskScheduleInfoSearchResults = searchResultsDiv.find('[data-name="taskScheduleInfoSearchResults"]');
	//taskScheduleInfoSearchResults.find('[data-name="taskScheduleInfoRow"]').remove();
	var previousDataRowList  = taskScheduleInfoSearchResults.find('[data-name="taskScheduleInfoRow"]');
	taskScheduleInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = taskScheduleInfoSearchResults.find('[data-name="taskScheduleInfoRow"]');
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
        var taskScheduleInfoDataObject = searchResultObjectsList[i];
        loadTaskScheduleInfoViewData(taskScheduleInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = taskScheduleInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='taskScheduleInfoVwTxnStatus']").text(vwTxnStatus);
		if(taskScheduleInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="taskScheduleInfoActionButton"]').text(taskScheduleInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="taskScheduleInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="taskScheduleInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="taskScheduleInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="taskScheduleInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('taskScheduleInfo', taskScheduleInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    taskScheduleInfoSearchResults.append(resultRowTemplate);
	    processResultRowForTaskScheduleInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForTaskScheduleInfo == 1)
	{
    	getDashboardResultsForTaskScheduleInfo();
	}
}
var taskScheduleInfoSearchResultsDivName = "taskScheduleInfoSearchResultsDiv";
var gTaskScheduleInfoSearchInputParamsList = [];
function retrieveTaskScheduleInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + taskScheduleInfoSearchResultsDivName + '"]');
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
    fetchTaskScheduleInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchTaskScheduleInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskScheduleInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskScheduleInfoSearchResultsDivName; 
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
        'objectType': "TaskScheduleInfo"
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
            	this.showPreviousRecords = "showPreviousTaskScheduleInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskScheduleInfoRecords";
            	this.showNextRecords = "showNextTaskScheduleInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskScheduleInfoResults";
                var taskScheduleInfoList = responseData['taskScheduleInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayTaskScheduleInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'taskScheduleInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'taskScheduleInfoSearchResults', 'taskScheduleInfoRow', setTaskScheduleInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getTaskScheduleInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskScheduleInfoSearchResultsDivName + '"]');
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
				
    	
		//Lookup		
		if(searchDiv.find('[data-name="taskScheduleInfoDB_taskInfoId"]').length == 1)
		{
		    var taskInfoId = searchDiv.find('[data-name="taskScheduleInfoDB_taskInfoId"]').data("taskInfo-id");
		    if(taskInfoId > -1)
	    	{
	    		parameterNameAndValuesList.push({ 'parameterName':'taskInfoId', 'userInputValue':taskInfoId});
	    	}
		}
		
		//Input
		if(searchDiv.find('[data-name="taskScheduleInfoDB_targetEntityId"]').length == 1)
		{
		    var targetEntityId = searchDiv.find('[data-name="taskScheduleInfoDB_targetEntityId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetEntityId', 'userInputValue':targetEntityId});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskScheduleInfoDB_targetUserId"]').length == 1)
		{
		    var targetUserId = searchDiv.find('[data-name="taskScheduleInfoDB_targetUserId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetUserId', 'userInputValue':targetUserId});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskScheduleInfoDB_notificationMedium"]').length == 1)
		{
		    var notificationMedium = searchDiv.find('[data-name="taskScheduleInfoDB_notificationMedium"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'notificationMedium', 'userInputValue':notificationMedium});
		}
		
		//Datetime
		if(searchDiv.find('[data-name="taskScheduleInfoDB_notificationLastSentTime"]').length == 1)
		{
		    var notificationLastSentTime = searchDiv.find('[data-name="taskScheduleInfoDB_notificationLastSentTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'notificationLastSentTime', 'userInputValue':notificationLastSentTime});
		}
		
		//Datetime
		if(searchDiv.find('[data-name="taskScheduleInfoDB_nextNotificationTime"]').length == 1)
		{
		    var nextNotificationTime = searchDiv.find('[data-name="taskScheduleInfoDB_nextNotificationTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'nextNotificationTime', 'userInputValue':nextNotificationTime});
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
        gTaskScheduleInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskScheduleInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskScheduleInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + taskScheduleInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskScheduleInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskScheduleInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskScheduleInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskScheduleInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchTaskScheduleInfoSearchResultsList);
}
function showPreviousTaskScheduleInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskScheduleInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchTaskScheduleInfoSearchResultsList);
}
function showNextTaskScheduleInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskScheduleInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchTaskScheduleInfoSearchResultsList);
}
function showPrevOrNextSearchTaskScheduleInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskScheduleInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextTaskScheduleInfoRecords(e);
    }
}

var gTaskScheduleInfo_taskInfoSearchResultsTableRowTemplate =""; 
function initializeTaskInfoPopup_TaskScheduleInfo_taskInfoLookupFields() 
{	
    var searchDiv = $('[data-name="TaskScheduleInfo_taskInfoSearchDiv"]');
	
    
	if(gTaskScheduleInfo_taskInfoSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="TaskScheduleInfo_taskInfoSearchResults"]').find('[data-name="TaskScheduleInfo_taskInfoRow"]');
		gTaskScheduleInfo_taskInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayTaskScheduleInfo_taskInfoList(searchResultObjectsList, parentElement)
{
    var taskInfoSearchResults = $('[data-name="TaskScheduleInfo_taskInfoSearchResults"]');
	taskInfoSearchResults.find('[data-name="TaskScheduleInfo_taskInfoRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gTaskScheduleInfo_taskInfoSearchResultsTableRowTemplate);
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
var TaskScheduleInfo_taskInfoSearchResultsDivName = "TaskScheduleInfo_taskInfoSearchResultsDiv";
var gTaskScheduleInfo_taskInfoSearchInputParamsList = [];
function getTaskScheduleInfo_taskInfoSearchResults()
{
    var searchDiv = $('[data-name="TaskScheduleInfo_taskInfoSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + TaskScheduleInfo_taskInfoSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchTaskScheduleInfo_taskInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchTaskScheduleInfo_taskInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskScheduleInfo_taskInfoSearchInputParams();
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
            	this.showCurrentPageRecords = "showCurrentPageTaskScheduleInfo_taskInfoRecords";
            	this.showPreviousRecords = "showPreviousTaskScheduleInfo_taskInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskScheduleInfo_taskInfoRecords";
            	this.showNextRecords = "showNextTaskScheduleInfo_taskInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskScheduleInfo_taskInfoResults";
                var taskInfoList = responseData['taskInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', TaskScheduleInfo_taskInfoSearchResultsDivName, 'TaskScheduleInfo_taskInfoSearchResults', 'TaskScheduleInfo_taskInfoRow', setTaskScheduleInfo_taskInfoSearchInputParams, displayTaskScheduleInfo_taskInfoList);
            }
        }
    });
}
function getTaskScheduleInfo_taskInfoSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + TaskScheduleInfo_taskInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="TaskScheduleInfo_taskInfoSearchDiv"]');
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

	    
        gTaskScheduleInfo_taskInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskScheduleInfo_taskInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskScheduleInfo_taskInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + TaskScheduleInfo_taskInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskScheduleInfo_taskInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskScheduleInfo_taskInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskScheduleInfo_taskInfoRecords(e)
{
    getCurrentPageSearchResults(e, TaskScheduleInfo_taskInfoSearchResultsDivName, fetchTaskScheduleInfo_taskInfoSearchResultsList);
}
function showPreviousTaskScheduleInfo_taskInfoRecords()
{
    getPreviousPageSearchResults(TaskScheduleInfo_taskInfoSearchResultsDivName, fetchTaskScheduleInfo_taskInfoSearchResultsList);
}
function showNextTaskScheduleInfo_taskInfoRecords()
{
    getNextPageSearchResults(TaskScheduleInfo_taskInfoSearchResultsDivName, fetchTaskScheduleInfo_taskInfoSearchResultsList);
}
function showPrevOrNextSearchTaskScheduleInfo_taskInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskScheduleInfo_taskInfoRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextTaskScheduleInfo_taskInfoRecords();
    }
}
function setTaskScheduleInfo_taskInfo(taskInfoRowElement) 
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var parentElement = $(taskInfoRowElement).parents('[data-name="TaskInfoPopup_TaskScheduleInfo_taskInfo"]');
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
		var entityName = "TaskScheduleInfo_taskInfo";
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
function lookupRowSelectedForTaskScheduleInfo(attributeName, attributeId)
{
	var taskScheduleInfo = getDataForTaskScheduleInfo();
	taskScheduleInfo['attributeName'] = attributeName;
	taskScheduleInfo['attributeId'] = attributeId;
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
            	setTaskScheduleInfoData(taskScheduleInfo);
            }
        }
    });	
}
function selectOptionChangedForTaskScheduleInfo(attributeName)
{
	var taskScheduleInfo = getDataForTaskScheduleInfo();
	taskScheduleInfo['attributeName'] = attributeName;
    var taskScheduleInfoJsonData = {'paramsInfo': JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
            	setTaskScheduleInfoData(taskScheduleInfo);
            	doAfterTaskScheduleInfoPanelRefreshed();
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
function retrieveDependentTaskScheduleInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'TaskScheduleInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskScheduleInfoList = responseData['taskScheduleInfoList'];
            	var taskScheduleInfoListElement = $("[data-name='taskScheduleInfoList']");
            	var previousDataRowList  = taskScheduleInfoListElement.find('[data-name="taskScheduleInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = taskScheduleInfoListElement.find('[data-name="taskScheduleInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//taskScheduleInfoListElement.empty();
            	for(var i=0; i<taskScheduleInfoList.length; i++)
        		{
            		var taskScheduleInfoDataObject = taskScheduleInfoList[i];
            		//var resultRowTemplate = $(gTaskScheduleInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];            		
				    resultRowTemplate.find("[data-name='targetEntityId']").text(targetEntityId);
					var targetUserId = taskScheduleInfoDataObject['targetUserId'];            		
				    resultRowTemplate.find("[data-name='targetUserId']").text(targetUserId);
					var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];            		
				    resultRowTemplate.find("[data-name='notificationMedium']").text(notificationMedium);
					var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];            		
				    resultRowTemplate.find("[data-name='notificationLastSentTime']").text(notificationLastSentTime);
					var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];            		
				    resultRowTemplate.find("[data-name='nextNotificationTime']").text(nextNotificationTime);

					
										var taskInfo = taskScheduleInfoDataObject['taskInfoDisplayVal'];            		
				    resultRowTemplate.find("[data-name='taskInfo']").text(taskInfo);

            	    resultRowTemplate.data('taskScheduleInfo', taskScheduleInfoDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    taskScheduleInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForTaskScheduleInfo(customEventName)
{
	var taskScheduleInfoId = document.getElementById('idValueForTaskScheduleInfo').value;
	var taskScheduleInfo = {'taskScheduleInfoId':taskScheduleInfoId, 'customEventName':customEventName};
    var taskScheduleInfoJsonData = {'paramsInfo':JSON.stringify(taskScheduleInfo), 'objectType' : 'TaskScheduleInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForTaskScheduleInfoExt(customEventName);
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
        		setTaskScheduleInfoInViewPage(taskScheduleInfo);
            }
    		doAfterExecuteCustomAPIForTaskScheduleInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnTaskScheduleInfo(e, paramsMap)
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
	var taskScheduleInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var taskScheduleInfoDataObject  = rowObj.data('taskScheduleInfo');
    	taskScheduleInfoId = taskScheduleInfoDataObject['taskScheduleInfoId'];
	}
    else
	{
    	taskScheduleInfoId = document.getElementById('idValueForTaskScheduleInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var taskScheduleInfoSearchParams = {'taskScheduleInfoId':taskScheduleInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	taskScheduleInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var taskScheduleInfoJsonData = {'paramsInfo':JSON.stringify(taskScheduleInfoSearchParams),  'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskScheduleInfo = responseData['taskScheduleInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="taskScheduleInfoRowActionButtonDiv"]').hide();
            		if(taskScheduleInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = taskScheduleInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(taskScheduleInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="taskScheduleInfoActionButton"]').text(taskScheduleInfo["nextAction"]);
            			selectedRowObj.find('[data-name="taskScheduleInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setTaskScheduleInfoInViewPage(taskScheduleInfo);
    			}
            }
        }
    });	
}
function downloadTaskScheduleInfoData()
{		
	var taskScheduleInfo = {};
    var taskScheduleInfoJsonData = {'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=TaskScheduleInfo");
            }
        }
    });
}
function uploadTaskScheduleInfoData(fileInfo)
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
	var taskScheduleInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var taskScheduleInfoJsonData = {'paramsInfo':JSON.stringify(taskScheduleInfo),  'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=TaskScheduleInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForTaskScheduleInfo()
{
    var taskScheduleInfoJsonData = {'objectType' : 'TaskScheduleInfo'};
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
        data: taskScheduleInfoJsonData,
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



function doTaskScheduleInfoLengthValidation(taskScheduleInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	if(!isFieldLengthAllowed(taskScheduleInfoDataObject['targetEntityId'], 10))
	{
		alertMessage += "\n target Entity Id length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskScheduleInfoDataObject['targetUserId'], 10))
	{
		alertMessage += "\n target User Id length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskScheduleInfoDataObject['notificationMedium'], 30))
	{
		alertMessage += "\n NotificationMedium length is more than allowed(30)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskScheduleInfoDataObject['notificationLastSentTime'], 50))
	{
		alertMessage += "\n NotificationLastSentTime length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskScheduleInfoDataObject['nextNotificationTime'], 50))
	{
		alertMessage += "\n NextNotificationTime length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskScheduleInfoManadatoryValidation(taskScheduleInfoDataObject, paramsMap)
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
		
	var taskInfoId = taskScheduleInfoDataObject['taskInfoId'];
	if(!taskInfoId || taskInfoId < 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_taskInfo").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Select Task";
		    validationPassed = false;
		}
	}
	
	var targetEntityId = taskScheduleInfoDataObject['targetEntityId'];
	if(!targetEntityId || targetEntityId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_targetEntityId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter target Entity Id";
		    validationPassed = false;
		}
	}
	
	var targetUserId = taskScheduleInfoDataObject['targetUserId'];
	if(!targetUserId || targetUserId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_targetUserId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter target User Id";
		    validationPassed = false;
		}
	}
	
	var notificationMedium = taskScheduleInfoDataObject['notificationMedium'];
	if(!notificationMedium || notificationMedium.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_notificationMedium").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter NotificationMedium";
		    validationPassed = false;
		}
	}
	
	var notificationLastSentTime = taskScheduleInfoDataObject['notificationLastSentTime'];
	if(!notificationLastSentTime || notificationLastSentTime.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_notificationLastSentTime").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter NotificationLastSentTime";
		    validationPassed = false;
		}
	}
	
	var nextNotificationTime = taskScheduleInfoDataObject['nextNotificationTime'];
	if(!nextNotificationTime || nextNotificationTime.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskScheduleInfo_nextNotificationTime").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter NextNotificationTime";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskScheduleInfoValidation(taskScheduleInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doTaskScheduleInfoLengthValidation(taskScheduleInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doTaskScheduleInfoManadatoryValidation(taskScheduleInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
