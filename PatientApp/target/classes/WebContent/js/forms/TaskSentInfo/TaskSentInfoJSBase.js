/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'TaskInfo' : 'FormWBEntity:TaskSentInfo_taskInfo' 
 *	 * 'TargetEntityId' : 'FormWBEntity:TaskSentInfo_targetEntityId' 
 *	 * 'TargetUserId' : 'FormWBEntity:TaskSentInfo_targetUserId' 
 *	 * 'NotificationMedium' : 'FormWBEntity:TaskSentInfo_notificationMedium' 
 *	 * 'LayoutInfoText' : 'FormWBEntity:TaskSentInfo_layoutInfoText' 
 *	 * 'NotificationSentTime' : 'FormWBEntity:TaskSentInfo_notificationSentTime' 
 *	
 */
var gInitParamsObjForTaskSentInfo = {};
var gTaskSentInfoRequestUnderProcess = 0;
function selectThisTaskSentInfoForEdit(taskSentInfoRowElement)
{
    var taskSentInfoDataObject  = $(taskSentInfoRowElement).data('taskSentInfo');
    var taskSentInfoList = $('[data-name="taskSentInfoSearchResults"]').find('[data-name="taskSentInfoRow"]');
	taskSentInfoList.data("is-selected", 0);	
	$(taskSentInfoRowElement).data("is-selected", 1);
	setTaskSentInfoInViewPage(taskSentInfoDataObject);
}

function setTaskSentInfoInViewPage(taskSentInfoDataObject, paramsMap)
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
	var taskSentInfoId = taskSentInfoDataObject['taskSentInfoId'];
	$('#'+prefix+'idValueForTaskSentInfo').val(taskSentInfoId);
		
	//Lookup
	if(taskSentInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskSentInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskInfo['taskInfoId'];
			var taskInfoLinkElement = $('#'+prefix+'TaskSentInfo_taskInfoId');
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
	if(taskSentInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
		$('#'+prefix+'TaskSentInfo_targetEntityId').val(targetEntityId);
	}
	
	//Input
	if(taskSentInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskSentInfoDataObject['targetUserId'];            		
		$('#'+prefix+'TaskSentInfo_targetUserId').val(targetUserId);
	}
	
	//Combo
	if(taskSentInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
		$('#'+prefix+'TaskSentInfo_notificationMedium').val(notificationMedium)
	}
	
	//Input
	if(taskSentInfoDataObject.hasOwnProperty('layoutInfoText'))
	{
		var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
		$('#'+prefix+'TaskSentInfo_layoutInfoText').val(layoutInfoText);
	}
	
	//DateTime
	if(taskSentInfoDataObject.hasOwnProperty('notificationSentTime'))
	{
		var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		
		$('#'+prefix+'TaskSentInfo_notificationSentTime').val(notificationSentTime);
	}

	if(taskSentInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = taskSentInfoDataObject['vwTxnStatus'];
		$('[data-name="taskSentInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnTaskSentInfo(this)" >' +taskSentInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="taskSentInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="taskSentInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="taskSentInfoActionButtonDiv"]').hide();
	}
	$('[data-name="taskSentInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskSentInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadTaskSentInfoViewData(taskSentInfoDataObject);
	disbaleTaskSentInfoUpdateDisallowedFields(paramsMap);
	doAfterTaskSentInfoPanelRefreshed();
    
    
}
function disbaleTaskSentInfoUpdateDisallowedFields(paramsMap)
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
function loadTaskSentInfoViewData(taskSentInfoDataObject, paramsMap)
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
	var taskSentInfoId = taskSentInfoDataObject['taskSentInfoId'];
	$('#'+prefix+'idValueForTaskSentInfo').val(taskSentInfoId);
		
	if(taskSentInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskSentInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskSentInfoDataObject['taskInfoId'];
			var taskInfoDisplayVal = taskSentInfoDataObject['taskInfoDisplayVal'];
			parentElement.find('[data-name="'+prefix+'TaskSentInfo_taskInfo"]').text(taskInfoDisplayVal);
			//taskInfoLinkElement.data("taskInfo-id", taskInfoId);
		}
	}
	
	if(taskSentInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
		parentElement.find('[data-name="'+prefix+'TaskSentInfo_targetEntityId"]').text(targetEntityId);
	}
	
	if(taskSentInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskSentInfoDataObject['targetUserId'];            		
		parentElement.find('[data-name="'+prefix+'TaskSentInfo_targetUserId"]').text(targetUserId);
	}
	
	if(taskSentInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
		parentElement.find('[data-name="'+prefix+'TaskSentInfo_notificationMedium"]').text(notificationMedium);
	}
	
	if(taskSentInfoDataObject.hasOwnProperty('layoutInfoText'))
	{
		var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
		parentElement.find('[data-name="'+prefix+'TaskSentInfo_layoutInfoText"]').text(layoutInfoText);
	}
	
	var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		
	parentElement.find('[data-name="'+prefix+'TaskSentInfo_notificationSentTime"]').text(notificationSentTime);

}
function ajaxDemoForTaskSentInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForTaskSentInfo').textContent;	
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
			refreshPanelForTaskSentInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForTaskSentInfo(msg)
{
	var executeCustomAPIButtonForTaskSentInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForTaskSentInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:TaskSentInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForTaskSentInfo.click();
}
function refreshPanelForTaskSentInfo()
{
	var demoRefreshButtonForTaskSentInfo = document.getElementById("FormWBEntity:demoRefreshButtonTaskSentInfo");
	demoRefreshButtonForTaskSentInfo.click();
}
function initializePanelOnLoadForCreateTaskSentInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForTaskSentInfo = initParamsObj;
	initializeTaskSentInfoPage();	
	doAfterTaskSentInfoPanelRefreshed();
	initializeTaskSentInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForTaskSentInfoExt();
	fetchTaskSentInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="taskSentInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForTaskSentInfo = 0;
function initializePanelOnLoadForSearchTaskSentInfo()
{
	initializeTaskSentInfoPage();	
	initializeTaskSentInfoLookupFields();
	doAfterPanelInitializedForTaskSentInfoExt();
	gLoadDashboardResultsForTaskSentInfo =1;
	//retrieveTaskSentInfoList();
}
function initializePanelOnLoadForViewTaskSentInfo(urlParams)
{
	initializeTaskSentInfoPage();	
	doAfterTaskSentInfoPanelRefreshed();
	initializeTaskSentInfoLookupFields(urlParams);
	doAfterPanelInitializedForTaskSentInfoExt();
	retrieveTaskSentInfo(urlParams);
	initDatePicker();
	$('[data-name="taskSentInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeTaskSentInfoPage()
{
	initializePageOnload();	
	//initializeTaskSentInfoTemplateVariables();
}
function initializeTaskSentInfoTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="taskSentInfoSearchResults"]').find('[data-name="taskSentInfoRow"]');
	if(searchResultsRowObj.length > 0 && gTaskSentInfoSearchResultsTableRowTemplate.length == 0)
	{
		gTaskSentInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveTaskSentInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var taskSentInfoId = paramsMap['taskSentInfoId'];
	var taskSentInfo = {};
	taskSentInfo['taskSentInfoId'] = taskSentInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				taskSentInfo[key] = paramsMap[key];
			}
	}
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
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
                	var taskSentInfoDataObject = responseData['taskSentInfoDataObject'];
    				setTaskSentInfoInViewPage(taskSentInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForTaskSentInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchTaskSentInfoDefaultData();
}
function fetchTaskSentInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'TaskSentInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskSentInfo = responseData['taskSentInfo'];
            	document.getElementById('idValueForTaskSentInfo').value = '';
			    
            	setTaskSentInfoData(taskSentInfo);
            }
        }
    });	
}
function fetchTaskSentInfoTestData() 
{
	var taskSentInfo = getDataForTaskSentInfo();
    var searchJsonData = {'objectType' : 'TaskSentInfo', 'paramsInfo' : JSON.stringify(taskSentInfo)};
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
            	var taskSentInfo = responseData['taskSentInfo'];
            	document.getElementById('idValueForTaskSentInfo').value = '';
			    
            	setTaskSentInfoData(taskSentInfo);
            }
        }
    });	
}
function setTaskSentInfoData(taskSentInfoDataObject)
{
	var prefix = "";
		
	//Lookup
	if(taskSentInfoDataObject.hasOwnProperty('taskInfo'))
	{
		var taskInfo = taskSentInfoDataObject['taskInfo'];
		if(taskInfo != "")
		{
			var taskInfoId = taskInfo['taskInfoId'];
			var taskInfoLinkElement = $('#'+prefix+'TaskSentInfo_taskInfoId');
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
	if(taskSentInfoDataObject.hasOwnProperty('targetEntityId'))
	{
		var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
		$('#'+prefix+'TaskSentInfo_targetEntityId').val(targetEntityId);
	}
	
	//Input
	if(taskSentInfoDataObject.hasOwnProperty('targetUserId'))
	{
		var targetUserId = taskSentInfoDataObject['targetUserId'];            		
		$('#'+prefix+'TaskSentInfo_targetUserId').val(targetUserId);
	}
	
	//Combo
	if(taskSentInfoDataObject.hasOwnProperty('notificationMedium'))
	{
		var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
		$('#'+prefix+'TaskSentInfo_notificationMedium').val(notificationMedium);
	}
	
	//Input
	if(taskSentInfoDataObject.hasOwnProperty('layoutInfoText'))
	{
		var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
		$('#'+prefix+'TaskSentInfo_layoutInfoText').val(layoutInfoText);
	}
	
	//DateTime
	if(taskSentInfoDataObject.hasOwnProperty('notificationSentTime'))
	{
		var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		
		$('#'+prefix+'TaskSentInfo_notificationSentTime').val(notificationSentTime);
	}

	$('[data-name="taskSentInfoActionButtonDiv"]').hide();
}
function initializeTaskSentInfoLookupFields(paramsMap) 
{
		
	$("#TaskSentInfo_taskInfoId").data("taskInfo-id", -1);
  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="taskSentInfoSearchResultsDiv"]');
		
	
    searchDiv.find('[data-name="taskSentInfoDB_taskInfoId"]').data("taskInfo-id", -1);
  
	
	
	  
	
	
	  
	
	
	  
	
	
	  
	
	
	
    
}

function doAfterTaskSentInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForTaskSentInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForTaskSentInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'notificationMedium')
	{
	}

	doAfterSelectOptionChangedForTaskSentInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForTaskSentInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'taskInfo')
	{
	}

	doAfterLookupRowChangedForTaskSentInfoExt(fieldName)
}
function getEntityIdForTaskSentInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForTaskSentInfo').textContent;	
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
function openPrintPageForTaskSentInfo()
{
	var entityId = getEntityIdForTaskSentInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/TaskSentInfo.jsp?taskSentInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}
function hideTaskInfoForTaskSentInfo()
{
		
		document.getElementById('TaskSentInfo_taskInfoId').parentElement.style.display = "none";
	   	
		document.getElementById('TaskSentInfo_taskInfotaskName').parentElement.style.display = "none";	
	   	
		//document.getElementById('TaskSentInfo_taskInfoNewLinkID').parentElement.style.display = "none";
}

function showTaskInfoForTaskSentInfo()
{
		
		document.getElementById('TaskSentInfo_taskInfoId').parentElement.style.display = "table-row";
	   	
		document.getElementById('TaskSentInfo_taskInfotaskName').parentElement.style.display = "table-row";	
	   	
		//document.getElementById('TaskSentInfo_taskInfoNewLinkID').parentElement.style.display = "table-row";
}


function getDataForTaskSentInfo(paramsMap)
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
	var taskSentInfo = {};
		
	//Lookup
	if($("#"+prefix+"TaskSentInfo_taskInfoId").length == 1)
	{
		var taskInfoId = $('#'+prefix+'TaskSentInfo_taskInfoId').data("taskInfo-id");
		taskSentInfo['taskInfoId'] = taskInfoId;
	}
	
	//Input
	if($("#"+prefix+"TaskSentInfo_targetEntityId").length == 1)
	{
		var targetEntityId = $('#'+prefix+'TaskSentInfo_targetEntityId').val();
		taskSentInfo['targetEntityId'] = targetEntityId;
	}
	
	//Input
	if($("#"+prefix+"TaskSentInfo_targetUserId").length == 1)
	{
		var targetUserId = $('#'+prefix+'TaskSentInfo_targetUserId').val();
		taskSentInfo['targetUserId'] = targetUserId;
	}
	
	//Combo
	if($("#"+prefix+"TaskSentInfo_notificationMedium").length == 1)
	{
		var notificationMedium = $('#'+prefix+'TaskSentInfo_notificationMedium').val();
		taskSentInfo['notificationMedium'] = notificationMedium;
	}
	
	//Input
	if($("#"+prefix+"TaskSentInfo_layoutInfoText").length == 1)
	{
		var layoutInfoText = $('#'+prefix+'TaskSentInfo_layoutInfoText').val();
		taskSentInfo['layoutInfoText'] = layoutInfoText;
	}
	
	//Datetime
	if($("#"+prefix+"TaskSentInfo_notificationSentTime").length == 1)
	{
		var notificationSentTime = $('#'+prefix+'TaskSentInfo_notificationSentTime').val();
		taskSentInfo['notificationSentTime'] = notificationSentTime;
	}

	
	return taskSentInfo;
}
function createTaskSentInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var taskSentInfo = getDataForTaskSentInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				taskSentInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForTaskSentInfo)
	{
		paramsInfo[key] = gInitParamsObjForTaskSentInfo[key];
	}
	var validationObject = doTaskSentInfoValidation(taskSentInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	taskSentInfo['additionalParamsInfo'] = paramsInfo;
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskSentInfoRequestUnderProcess == 1)
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
            	gTaskSentInfoRequestUnderProcess = 0;
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gTaskSentInfoRequestUnderProcess = 0;
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
            	var taskSentInfoId = responseData['taskSentInfoId'];
            	var popupElement = $('[data-name="TaskSentInfoPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var taskSentInfoDataObject = responseData['taskSentInfoDataObject'];
    				setTaskSentInfoInViewPage(taskSentInfoDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewTaskSentInfo.html?taskSentInfoId="+taskSentInfoId; 
        		}
				
            }
        }
    });
}
function resetTableForTaskSentInfo()
{
	var taskSentInfoListElement = $("[data-name='taskSentInfoList']");
	var previousDataRowList  = taskSentInfoListElement.find('[data-name="taskSentInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForTaskSentInfo(paramsMap)
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
	$('#'+prefix+'idValueForTaskSentInfo').val('');
		
	//Lookup
	var taskInfoLinkElement = $('#'+prefix+'TaskSentInfo_taskInfoId');
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
	$('#'+prefix+'TaskSentInfo_targetEntityId').val('');
	
	//Input
	$('#'+prefix+'TaskSentInfo_targetUserId').val('');
	
	//Combo
	$('#'+prefix+'TaskSentInfo_notificationMedium').val('');
	
	//Input
	$('#'+prefix+'TaskSentInfo_layoutInfoText').val('');
	
	//DateTime
	$('#'+prefix+'TaskSentInfo_notificationSentTime').val('');

	$('[data-name="taskSentInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="taskSentInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskSentInfoActionButtonDiv"]').hide();
	enableTaskSentInfoUpdateDisallowedFields(paramsMap);
    
}
function enableTaskSentInfoUpdateDisallowedFields(paramsMap)
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
function updateTaskSentInfo(paramsMap)
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
	var taskSentInfo = getDataForTaskSentInfo(paramsMap)
	if($("#"+prefix+"idValueForTaskSentInfo").length == 1)
	{
		var taskSentInfoId = $("#"+prefix+"idValueForTaskSentInfo").val();
		taskSentInfo['taskSentInfoId'] = taskSentInfoId;
	}
	var validationObject = doTaskSentInfoValidation(taskSentInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskSentInfoRequestUnderProcess == 1)
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
                    	gTaskSentInfoRequestUnderProcess = 0;
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gTaskSentInfoRequestUnderProcess = 0;
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
function deleteTaskSentInfo(paramsMap)
{		
	var taskSentInfoId = document.getElementById('idValueForTaskSentInfo').value;
	deleteSelectedTaskSentInfo(taskSentInfoId, paramsMap);
}
function deleteSelectedTaskSentInfo(taskSentInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var taskSentInfo = {};
	taskSentInfo['taskSentInfoId'] = taskSentInfoId;	
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
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
function loadSearchResultsForTaskSentInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'TaskSentInfo'};
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
            	var taskSentInfoSearchResultsElement = $("[data-name='taskSentInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var taskSentInfoDataObject = searchResultObjectsList[i];
					            		var taskInfo = taskSentInfoDataObject['taskInfo'];            		
            		var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
            		var targetUserId = taskSentInfoDataObject['targetUserId'];            		
            		var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
            		var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
            		var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		

            		var resultRowTemnplate = $(gTaskSentInfoSearchResultsTableRowTemplate);
					            		var taskInfo = taskSentInfoDataObject['taskInfo'];            		
            	    resultRowTemnplate.find("[data-name='taskInfo']").text(taskInfo);
            		var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
            	    resultRowTemnplate.find("[data-name='targetEntityId']").text(targetEntityId);
            		var targetUserId = taskSentInfoDataObject['targetUserId'];            		
            	    resultRowTemnplate.find("[data-name='targetUserId']").text(targetUserId);
            		var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
            	    resultRowTemnplate.find("[data-name='notificationMedium']").text(notificationMedium);
            		var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
            	    resultRowTemnplate.find("[data-name='layoutInfoText']").text(layoutInfoText);
            		var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		
            	    resultRowTemnplate.find("[data-name='notificationSentTime']").text(notificationSentTime);

            	    resultRowTemnplate.data('taskSentInfo', taskSentInfoDataObject);
            	    taskSentInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gTaskSentInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForTaskSentInfoForEdit(taskSentInfoLinkElement)
{
	var taskSentInfoRowElement = $(taskSentInfoLinkElement).parents('[data-name="taskSentInfoRow"]');
    var taskSentInfoDataObject  = taskSentInfoRowElement.data('taskSentInfo');
	var taskSentInfoId = taskSentInfoDataObject['taskSentInfoId'];
	var taskSentInfoPopupElement = $('[data-name="TaskSentInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setTaskSentInfoInViewPage(taskSentInfoDataObject);
	    $("#TaskSentInfo-tab").trigger("click");
	}
	else if(taskSentInfoPopupElement.length > 0)
	{
	    setTaskSentInfoInViewPage(taskSentInfoDataObject);
		$('[data-name="TaskSentInfoPopup"]').find('[data-name="taskSentInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="TaskSentInfoPopup"]').find('[data-name="taskSentInfoViewFormButtonsDiv"]').show();
	    showPopup('TaskSentInfoPopup', taskSentInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewTaskSentInfo.html?taskSentInfoId="+taskSentInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openTaskSentInfoCreatePageForNew(linkElement)
{
	var taskSentInfoPopupElement = $('[data-name="TaskSentInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForTaskSentInfo();
	    $("#TaskSentInfo-tab").trigger("click");
    }
	else if(taskSentInfoPopupElement.length > 0)
	{
		resetFormForTaskSentInfo();
	    showPopup('TaskSentInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateTaskSentInfo.html";
    }
}
function showTaskSentInfoPopupForEdit(taskSentInfoLinkElement)
{
	var taskSentInfoRowElement = $(taskSentInfoLinkElement).parents('[data-name="taskSentInfoRow"]');
    var taskSentInfoDataObject  = taskSentInfoRowElement.data('taskSentInfo');
    setTaskSentInfoInViewPage(taskSentInfoDataObject);
    showPopup('TaskSentInfoPopup', taskSentInfoLinkElement, 1);
    $("#TaskSentInfo-tab").trigger("click");
}
function showTaskSentInfoPopupForNew(buttonElement)
{
	resetFormForTaskSentInfo();
    showPopup('TaskSentInfoPopup', buttonElement, 1);
    $("#TaskSentInfo-tab").trigger("click");
}
function deleteThisTaskSentInfo(taskSentInfoLinkElement, paramsMap)
{
	var taskSentInfoRowElement = $(taskSentInfoLinkElement).parents('[data-name="taskSentInfoRow"]');
    var taskSentInfoDataObject  = taskSentInfoRowElement.data('taskSentInfo');
	var taskSentInfoId = taskSentInfoDataObject['taskSentInfoId'];
	deleteSelectedTaskSentInfo(taskSentInfoId, paramsMap);
}
function displayTaskSentInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskSentInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var taskSentInfoSearchResults = searchResultsDiv.find('[data-name="taskSentInfoSearchResults"]');
	//taskSentInfoSearchResults.find('[data-name="taskSentInfoRow"]').remove();
	var previousDataRowList  = taskSentInfoSearchResults.find('[data-name="taskSentInfoRow"]');
	taskSentInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = taskSentInfoSearchResults.find('[data-name="taskSentInfoRow"]');
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
        var taskSentInfoDataObject = searchResultObjectsList[i];
        loadTaskSentInfoViewData(taskSentInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = taskSentInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='taskSentInfoVwTxnStatus']").text(vwTxnStatus);
		if(taskSentInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="taskSentInfoActionButton"]').text(taskSentInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="taskSentInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="taskSentInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="taskSentInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="taskSentInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('taskSentInfo', taskSentInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    taskSentInfoSearchResults.append(resultRowTemplate);
	    processResultRowForTaskSentInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForTaskSentInfo == 1)
	{
    	getDashboardResultsForTaskSentInfo();
	}
}
var taskSentInfoSearchResultsDivName = "taskSentInfoSearchResultsDiv";
var gTaskSentInfoSearchInputParamsList = [];
function retrieveTaskSentInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + taskSentInfoSearchResultsDivName + '"]');
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
    fetchTaskSentInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchTaskSentInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskSentInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskSentInfoSearchResultsDivName; 
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
        'objectType': "TaskSentInfo"
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
            	this.showPreviousRecords = "showPreviousTaskSentInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskSentInfoRecords";
            	this.showNextRecords = "showNextTaskSentInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskSentInfoResults";
                var taskSentInfoList = responseData['taskSentInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayTaskSentInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'taskSentInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'taskSentInfoSearchResults', 'taskSentInfoRow', setTaskSentInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getTaskSentInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskSentInfoSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="taskSentInfoDB_taskInfoId"]').length == 1)
		{
		    var taskInfoId = searchDiv.find('[data-name="taskSentInfoDB_taskInfoId"]').data("taskInfo-id");
		    if(taskInfoId > -1)
	    	{
	    		parameterNameAndValuesList.push({ 'parameterName':'taskInfoId', 'userInputValue':taskInfoId});
	    	}
		}
		
		//Input
		if(searchDiv.find('[data-name="taskSentInfoDB_targetEntityId"]').length == 1)
		{
		    var targetEntityId = searchDiv.find('[data-name="taskSentInfoDB_targetEntityId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetEntityId', 'userInputValue':targetEntityId});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskSentInfoDB_targetUserId"]').length == 1)
		{
		    var targetUserId = searchDiv.find('[data-name="taskSentInfoDB_targetUserId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetUserId', 'userInputValue':targetUserId});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskSentInfoDB_notificationMedium"]').length == 1)
		{
		    var notificationMedium = searchDiv.find('[data-name="taskSentInfoDB_notificationMedium"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'notificationMedium', 'userInputValue':notificationMedium});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskSentInfoDB_layoutInfoText"]').length == 1)
		{
		    var layoutInfoText = searchDiv.find('[data-name="taskSentInfoDB_layoutInfoText"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'layoutInfoText', 'userInputValue':layoutInfoText});
		}
		
		//Datetime
		if(searchDiv.find('[data-name="taskSentInfoDB_notificationSentTime"]').length == 1)
		{
		    var notificationSentTime = searchDiv.find('[data-name="taskSentInfoDB_notificationSentTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'notificationSentTime', 'userInputValue':notificationSentTime});
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
        gTaskSentInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskSentInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskSentInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + taskSentInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskSentInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskSentInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskSentInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskSentInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchTaskSentInfoSearchResultsList);
}
function showPreviousTaskSentInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskSentInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchTaskSentInfoSearchResultsList);
}
function showNextTaskSentInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskSentInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchTaskSentInfoSearchResultsList);
}
function showPrevOrNextSearchTaskSentInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskSentInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextTaskSentInfoRecords(e);
    }
}

var gTaskSentInfo_taskInfoSearchResultsTableRowTemplate =""; 
function initializeTaskInfoPopup_TaskSentInfo_taskInfoLookupFields() 
{	
    var searchDiv = $('[data-name="TaskSentInfo_taskInfoSearchDiv"]');
	
    
	if(gTaskSentInfo_taskInfoSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="TaskSentInfo_taskInfoSearchResults"]').find('[data-name="TaskSentInfo_taskInfoRow"]');
		gTaskSentInfo_taskInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayTaskSentInfo_taskInfoList(searchResultObjectsList, parentElement)
{
    var taskInfoSearchResults = $('[data-name="TaskSentInfo_taskInfoSearchResults"]');
	taskInfoSearchResults.find('[data-name="TaskSentInfo_taskInfoRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gTaskSentInfo_taskInfoSearchResultsTableRowTemplate);
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
var TaskSentInfo_taskInfoSearchResultsDivName = "TaskSentInfo_taskInfoSearchResultsDiv";
var gTaskSentInfo_taskInfoSearchInputParamsList = [];
function getTaskSentInfo_taskInfoSearchResults()
{
    var searchDiv = $('[data-name="TaskSentInfo_taskInfoSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + TaskSentInfo_taskInfoSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchTaskSentInfo_taskInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchTaskSentInfo_taskInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskSentInfo_taskInfoSearchInputParams();
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
            	this.showCurrentPageRecords = "showCurrentPageTaskSentInfo_taskInfoRecords";
            	this.showPreviousRecords = "showPreviousTaskSentInfo_taskInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskSentInfo_taskInfoRecords";
            	this.showNextRecords = "showNextTaskSentInfo_taskInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskSentInfo_taskInfoResults";
                var taskInfoList = responseData['taskInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', TaskSentInfo_taskInfoSearchResultsDivName, 'TaskSentInfo_taskInfoSearchResults', 'TaskSentInfo_taskInfoRow', setTaskSentInfo_taskInfoSearchInputParams, displayTaskSentInfo_taskInfoList);
            }
        }
    });
}
function getTaskSentInfo_taskInfoSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + TaskSentInfo_taskInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="TaskSentInfo_taskInfoSearchDiv"]');
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

	    
        gTaskSentInfo_taskInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskSentInfo_taskInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskSentInfo_taskInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + TaskSentInfo_taskInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskSentInfo_taskInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskSentInfo_taskInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskSentInfo_taskInfoRecords(e)
{
    getCurrentPageSearchResults(e, TaskSentInfo_taskInfoSearchResultsDivName, fetchTaskSentInfo_taskInfoSearchResultsList);
}
function showPreviousTaskSentInfo_taskInfoRecords()
{
    getPreviousPageSearchResults(TaskSentInfo_taskInfoSearchResultsDivName, fetchTaskSentInfo_taskInfoSearchResultsList);
}
function showNextTaskSentInfo_taskInfoRecords()
{
    getNextPageSearchResults(TaskSentInfo_taskInfoSearchResultsDivName, fetchTaskSentInfo_taskInfoSearchResultsList);
}
function showPrevOrNextSearchTaskSentInfo_taskInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskSentInfo_taskInfoRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextTaskSentInfo_taskInfoRecords();
    }
}
function setTaskSentInfo_taskInfo(taskInfoRowElement) 
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var parentElement = $(taskInfoRowElement).parents('[data-name="TaskInfoPopup_TaskSentInfo_taskInfo"]');
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
		var entityName = "TaskSentInfo_taskInfo";
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
function lookupRowSelectedForTaskSentInfo(attributeName, attributeId)
{
	var taskSentInfo = getDataForTaskSentInfo();
	taskSentInfo['attributeName'] = attributeName;
	taskSentInfo['attributeId'] = attributeId;
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskSentInfo = responseData['taskSentInfo'];
            	setTaskSentInfoData(taskSentInfo);
            }
        }
    });	
}
function selectOptionChangedForTaskSentInfo(attributeName)
{
	var taskSentInfo = getDataForTaskSentInfo();
	taskSentInfo['attributeName'] = attributeName;
    var taskSentInfoJsonData = {'paramsInfo': JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskSentInfo = responseData['taskSentInfo'];
            	setTaskSentInfoData(taskSentInfo);
            	doAfterTaskSentInfoPanelRefreshed();
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
function retrieveDependentTaskSentInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'TaskSentInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskSentInfoList = responseData['taskSentInfoList'];
            	var taskSentInfoListElement = $("[data-name='taskSentInfoList']");
            	var previousDataRowList  = taskSentInfoListElement.find('[data-name="taskSentInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = taskSentInfoListElement.find('[data-name="taskSentInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//taskSentInfoListElement.empty();
            	for(var i=0; i<taskSentInfoList.length; i++)
        		{
            		var taskSentInfoDataObject = taskSentInfoList[i];
            		//var resultRowTemplate = $(gTaskSentInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var targetEntityId = taskSentInfoDataObject['targetEntityId'];            		
				    resultRowTemplate.find("[data-name='targetEntityId']").text(targetEntityId);
					var targetUserId = taskSentInfoDataObject['targetUserId'];            		
				    resultRowTemplate.find("[data-name='targetUserId']").text(targetUserId);
					var notificationMedium = taskSentInfoDataObject['notificationMedium'];            		
				    resultRowTemplate.find("[data-name='notificationMedium']").text(notificationMedium);
					var layoutInfoText = taskSentInfoDataObject['layoutInfoText'];            		
				    resultRowTemplate.find("[data-name='layoutInfoText']").text(layoutInfoText);
					var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];            		
				    resultRowTemplate.find("[data-name='notificationSentTime']").text(notificationSentTime);

					
										var taskInfo = taskSentInfoDataObject['taskInfoDisplayVal'];            		
				    resultRowTemplate.find("[data-name='taskInfo']").text(taskInfo);

            	    resultRowTemplate.data('taskSentInfo', taskSentInfoDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    taskSentInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForTaskSentInfo(customEventName)
{
	var taskSentInfoId = document.getElementById('idValueForTaskSentInfo').value;
	var taskSentInfo = {'taskSentInfoId':taskSentInfoId, 'customEventName':customEventName};
    var taskSentInfoJsonData = {'paramsInfo':JSON.stringify(taskSentInfo), 'objectType' : 'TaskSentInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForTaskSentInfoExt(customEventName);
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskSentInfo = responseData['taskSentInfo'];
        		setTaskSentInfoInViewPage(taskSentInfo);
            }
    		doAfterExecuteCustomAPIForTaskSentInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnTaskSentInfo(e, paramsMap)
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
	var taskSentInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var taskSentInfoDataObject  = rowObj.data('taskSentInfo');
    	taskSentInfoId = taskSentInfoDataObject['taskSentInfoId'];
	}
    else
	{
    	taskSentInfoId = document.getElementById('idValueForTaskSentInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var taskSentInfoSearchParams = {'taskSentInfoId':taskSentInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	taskSentInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var taskSentInfoJsonData = {'paramsInfo':JSON.stringify(taskSentInfoSearchParams),  'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskSentInfo = responseData['taskSentInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="taskSentInfoRowActionButtonDiv"]').hide();
            		if(taskSentInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = taskSentInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(taskSentInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="taskSentInfoActionButton"]').text(taskSentInfo["nextAction"]);
            			selectedRowObj.find('[data-name="taskSentInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setTaskSentInfoInViewPage(taskSentInfo);
    			}
            }
        }
    });	
}
function downloadTaskSentInfoData()
{		
	var taskSentInfo = {};
    var taskSentInfoJsonData = {'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=TaskSentInfo");
            }
        }
    });
}
function uploadTaskSentInfoData(fileInfo)
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
	var taskSentInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var taskSentInfoJsonData = {'paramsInfo':JSON.stringify(taskSentInfo),  'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=TaskSentInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForTaskSentInfo()
{
    var taskSentInfoJsonData = {'objectType' : 'TaskSentInfo'};
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
        data: taskSentInfoJsonData,
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



function doTaskSentInfoLengthValidation(taskSentInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	if(!isFieldLengthAllowed(taskSentInfoDataObject['targetEntityId'], 10))
	{
		alertMessage += "\n target Entity Id length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskSentInfoDataObject['targetUserId'], 10))
	{
		alertMessage += "\n target User Id length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskSentInfoDataObject['notificationMedium'], 30))
	{
		alertMessage += "\n NotificationMedium length is more than allowed(30)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskSentInfoDataObject['layoutInfoText'], 1000))
	{
		alertMessage += "\n LayoutInfoText length is more than allowed(1000)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskSentInfoDataObject['notificationSentTime'], 20))
	{
		alertMessage += "\n NotificationLastSentTime length is more than allowed(20)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskSentInfoManadatoryValidation(taskSentInfoDataObject, paramsMap)
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
		
	var taskInfoId = taskSentInfoDataObject['taskInfoId'];
	if(!taskInfoId || taskInfoId < 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskSentInfo_taskInfo").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Select Task";
		    validationPassed = false;
		}
	}
	
	var targetEntityId = taskSentInfoDataObject['targetEntityId'];
	if(!targetEntityId || targetEntityId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskSentInfo_targetEntityId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter target Entity Id";
		    validationPassed = false;
		}
	}
	
	var targetUserId = taskSentInfoDataObject['targetUserId'];
	if(!targetUserId || targetUserId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskSentInfo_targetUserId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter target User Id";
		    validationPassed = false;
		}
	}
	
	var notificationMedium = taskSentInfoDataObject['notificationMedium'];
	if(!notificationMedium || notificationMedium.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskSentInfo_notificationMedium").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter NotificationMedium";
		    validationPassed = false;
		}
	}
	
	var notificationSentTime = taskSentInfoDataObject['notificationSentTime'];
	if(!notificationSentTime || notificationSentTime.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskSentInfo_notificationSentTime").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter NotificationLastSentTime";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskSentInfoValidation(taskSentInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doTaskSentInfoLengthValidation(taskSentInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doTaskSentInfoManadatoryValidation(taskSentInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
