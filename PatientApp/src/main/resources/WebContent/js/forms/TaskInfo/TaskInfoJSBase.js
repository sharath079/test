/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'TaskName' : 'FormWBEntity:TaskInfo_taskName' 
 *	 * 'TaskDescription' : 'FormWBEntity:TaskInfo_taskDescription' 
 *	 * 'TaskType' : 'FormWBEntity:TaskInfo_taskType' 
 *	 * 'ApiName' : 'FormWBEntity:TaskInfo_apiName' 
 *	 * 'TargetEntityQuery' : 'FormWBEntity:TaskInfo_targetEntityQuery' 
 *	 * 'TargetUserIdColumnAlias' : 'FormWBEntity:TaskInfo_targetUserIdColumnAlias' 
 *	 * 'TargetEntityIdColumnAlias' : 'FormWBEntity:TaskInfo_targetEntityIdColumnAlias' 
 *	 * 'EnableInAppNotification' : 'FormWBEntity:TaskInfo_enableInAppNotification' 
 *	 * 'InAppNotificationLayout' : 'FormWBEntity:TaskInfo_inAppNotificationLayout' 
 *	 * 'EnableEmailNotification' : 'FormWBEntity:TaskInfo_enableEmailNotification' 
 *	 * 'EmailColumnAlias' : 'FormWBEntity:TaskInfo_emailColumnAlias' 
 *	 * 'EmailNotificationLayout' : 'FormWBEntity:TaskInfo_emailNotificationLayout' 
 *	 * 'EmailSubject' : 'FormWBEntity:TaskInfo_emailSubject' 
 *	 * 'EnableSmsNotification' : 'FormWBEntity:TaskInfo_enableSmsNotification' 
 *	 * 'SmsColumnAlias' : 'FormWBEntity:TaskInfo_smsColumnAlias' 
 *	 * 'SmsNotificationLayout' : 'FormWBEntity:TaskInfo_smsNotificationLayout' 
 *	 * 'SMSText' : 'FormWBEntity:TaskInfo_sMSText' 
 *	 * 'IsActive' : 'FormWBEntity:TaskInfo_isActive' 
 *	 * 'TaskStartDate' : 'FormWBEntity:TaskInfo_taskStartDate' 
 *	 * 'TaskFrequency' : 'FormWBEntity:TaskInfo_taskFrequency' 
 *	 * 'TaskFrequencyUnit' : 'FormWBEntity:TaskInfo_taskFrequencyUnit' 
 *	 * 'IsRecurring' : 'FormWBEntity:TaskInfo_isRecurring' 
 *	 * 'FirstRunType' : 'FormWBEntity:TaskInfo_firstRunType' 
 *	 * 'DateColumnAlias' : 'FormWBEntity:TaskInfo_dateColumnAlias' 
 *	 * 'FirstRunDateCalculationMethod' : 'FormWBEntity:TaskInfo_firstRunDateCalculationMethod' 
 *	 * 'FirstRunDateGapInYears' : 'FormWBEntity:TaskInfo_firstRunDateGapInYears' 
 *	 * 'FirstRunDateGapInMonths' : 'FormWBEntity:TaskInfo_firstRunDateGapInMonths' 
 *	 * 'FirstRunDateGapInDays' : 'FormWBEntity:TaskInfo_firstRunDateGapInDays' 
 *	 * 'FirstRunDateGapInHours' : 'FormWBEntity:TaskInfo_firstRunDateGapInHours' 
 *	 * 'FirstRunDateGapInMinutes' : 'FormWBEntity:TaskInfo_firstRunDateGapInMinutes' 
 *	 * 'FirstRunDateGapInSeconds' : 'FormWBEntity:TaskInfo_firstRunDateGapInSeconds' 
 *	
 */
var gInitParamsObjForTaskInfo = {};
var gTaskInfoRequestUnderProcess = 0;
function selectThisTaskInfoForEdit(taskInfoRowElement)
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
    var taskInfoList = $('[data-name="taskInfoSearchResults"]').find('[data-name="taskInfoRow"]');
	taskInfoList.data("is-selected", 0);	
	$(taskInfoRowElement).data("is-selected", 1);
	setTaskInfoInViewPage(taskInfoDataObject);
}

function setTaskInfoInViewPage(taskInfoDataObject, paramsMap)
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
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	$('#'+prefix+'idValueForTaskInfo').val(taskInfoId);
		
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskName'))
	{
		var taskName = taskInfoDataObject['taskName'];            		
		$('#'+prefix+'TaskInfo_taskName').val(taskName);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskDescription'))
	{
		var taskDescription = taskInfoDataObject['taskDescription'];            		
		$('#'+prefix+'TaskInfo_taskDescription').val(taskDescription);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('taskType'))
	{
		var taskType = taskInfoDataObject['taskType'];            		
		$('#'+prefix+'TaskInfo_taskType').val(taskType)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('apiName'))
	{
		var apiName = taskInfoDataObject['apiName'];            		
		$('#'+prefix+'TaskInfo_apiName').val(apiName);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetEntityQuery'))
	{
		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
		$('#'+prefix+'TaskInfo_targetEntityQuery').val(targetEntityQuery);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetUserIdColumnAlias'))
	{
		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
		$('#'+prefix+'TaskInfo_targetUserIdColumnAlias').val(targetUserIdColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetEntityIdColumnAlias'))
	{
		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
		$('#'+prefix+'TaskInfo_targetEntityIdColumnAlias').val(targetEntityIdColumnAlias);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableInAppNotification'))
	{
		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
		$('#'+prefix+'TaskInfo_enableInAppNotification').val(enableInAppNotification)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('inAppNotificationLayout'))
	{
		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_inAppNotificationLayout').val(inAppNotificationLayout);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableEmailNotification'))
	{
		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
		$('#'+prefix+'TaskInfo_enableEmailNotification').val(enableEmailNotification)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailColumnAlias'))
	{
		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
		$('#'+prefix+'TaskInfo_emailColumnAlias').val(emailColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailNotificationLayout'))
	{
		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_emailNotificationLayout').val(emailNotificationLayout);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailSubject'))
	{
		var emailSubject = taskInfoDataObject['emailSubject'];            		
		$('#'+prefix+'TaskInfo_emailSubject').val(emailSubject);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableSmsNotification'))
	{
		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
		$('#'+prefix+'TaskInfo_enableSmsNotification').val(enableSmsNotification)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('smsColumnAlias'))
	{
		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
		$('#'+prefix+'TaskInfo_smsColumnAlias').val(smsColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('smsNotificationLayout'))
	{
		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_smsNotificationLayout').val(smsNotificationLayout);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('sMSText'))
	{
		var sMSText = taskInfoDataObject['sMSText'];            		
		$('#'+prefix+'TaskInfo_sMSText').val(sMSText);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('isActive'))
	{
		var isActive = taskInfoDataObject['isActive'];            		
		$('#'+prefix+'TaskInfo_isActive').val(isActive)
	}
	
	//DateTime
	if(taskInfoDataObject.hasOwnProperty('taskStartDate'))
	{
		var taskStartDate = taskInfoDataObject['taskStartDate'];            		
		$('#'+prefix+'TaskInfo_taskStartDate').val(taskStartDate);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskFrequency'))
	{
		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
		$('#'+prefix+'TaskInfo_taskFrequency').val(taskFrequency);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('taskFrequencyUnit'))
	{
		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
		$('#'+prefix+'TaskInfo_taskFrequencyUnit').val(taskFrequencyUnit)
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('isRecurring'))
	{
		var isRecurring = taskInfoDataObject['isRecurring'];            		
		$('#'+prefix+'TaskInfo_isRecurring').val(isRecurring)
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('firstRunType'))
	{
		var firstRunType = taskInfoDataObject['firstRunType'];            		
		$('#'+prefix+'TaskInfo_firstRunType').val(firstRunType)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('dateColumnAlias'))
	{
		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
		$('#'+prefix+'TaskInfo_dateColumnAlias').val(dateColumnAlias);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
		$('#'+prefix+'TaskInfo_firstRunDateCalculationMethod').val(firstRunDateCalculationMethod)
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInYears').val(firstRunDateGapInYears);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInMonths').val(firstRunDateGapInMonths);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInDays').val(firstRunDateGapInDays);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInHours').val(firstRunDateGapInHours);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInMinutes').val(firstRunDateGapInMinutes);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInSeconds').val(firstRunDateGapInSeconds);
	}

	if(taskInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = taskInfoDataObject['vwTxnStatus'];
		$('[data-name="taskInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnTaskInfo(this)" >' +taskInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="taskInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="taskInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="taskInfoActionButtonDiv"]').hide();
	}
	$('[data-name="taskInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadTaskInfoViewData(taskInfoDataObject);
	disbaleTaskInfoUpdateDisallowedFields(paramsMap);
	doAfterTaskInfoPanelRefreshed();
    
    resetFormForTaskExecutionInfo();
    resetTableForTaskExecutionInfo();
    
    resetFormForTaskLayoutParameters();
    resetTableForTaskLayoutParameters();
    
    resetFormForEmailAttachmentLayout();
    resetTableForEmailAttachmentLayout();
    
        retrieveDependentTaskExecutionInfoList(paramsMap);
    retrieveDependentTaskLayoutParametersList(paramsMap);
    retrieveDependentEmailAttachmentLayoutList(paramsMap);

}
function disbaleTaskInfoUpdateDisallowedFields(paramsMap)
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
function loadTaskInfoViewData(taskInfoDataObject, paramsMap)
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
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	$('#'+prefix+'idValueForTaskInfo').val(taskInfoId);
		
	if(taskInfoDataObject.hasOwnProperty('taskName'))
	{
		var taskName = taskInfoDataObject['taskName'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_taskName"]').text(taskName);
	}
	
	if(taskInfoDataObject.hasOwnProperty('taskDescription'))
	{
		var taskDescription = taskInfoDataObject['taskDescription'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_taskDescription"]').text(taskDescription);
	}
	
	if(taskInfoDataObject.hasOwnProperty('taskType'))
	{
		var taskType = taskInfoDataObject['taskType'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_taskType"]').text(taskType);
	}
	
	if(taskInfoDataObject.hasOwnProperty('apiName'))
	{
		var apiName = taskInfoDataObject['apiName'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_apiName"]').text(apiName);
	}
	
	if(taskInfoDataObject.hasOwnProperty('targetEntityQuery'))
	{
		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_targetEntityQuery"]').text(targetEntityQuery);
	}
	
	if(taskInfoDataObject.hasOwnProperty('targetUserIdColumnAlias'))
	{
		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_targetUserIdColumnAlias"]').text(targetUserIdColumnAlias);
	}
	
	if(taskInfoDataObject.hasOwnProperty('targetEntityIdColumnAlias'))
	{
		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_targetEntityIdColumnAlias"]').text(targetEntityIdColumnAlias);
	}
	
	if(taskInfoDataObject.hasOwnProperty('enableInAppNotification'))
	{
		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_enableInAppNotification"]').text(enableInAppNotification);
	}
	
	if(taskInfoDataObject.hasOwnProperty('inAppNotificationLayout'))
	{
		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_inAppNotificationLayout"]').text(inAppNotificationLayout);
	}
	
	if(taskInfoDataObject.hasOwnProperty('enableEmailNotification'))
	{
		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_enableEmailNotification"]').text(enableEmailNotification);
	}
	
	if(taskInfoDataObject.hasOwnProperty('emailColumnAlias'))
	{
		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_emailColumnAlias"]').text(emailColumnAlias);
	}
	
	if(taskInfoDataObject.hasOwnProperty('emailNotificationLayout'))
	{
		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_emailNotificationLayout"]').text(emailNotificationLayout);
	}
	
	if(taskInfoDataObject.hasOwnProperty('emailSubject'))
	{
		var emailSubject = taskInfoDataObject['emailSubject'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_emailSubject"]').text(emailSubject);
	}
	
	if(taskInfoDataObject.hasOwnProperty('enableSmsNotification'))
	{
		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_enableSmsNotification"]').text(enableSmsNotification);
	}
	
	if(taskInfoDataObject.hasOwnProperty('smsColumnAlias'))
	{
		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_smsColumnAlias"]').text(smsColumnAlias);
	}
	
	if(taskInfoDataObject.hasOwnProperty('smsNotificationLayout'))
	{
		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_smsNotificationLayout"]').text(smsNotificationLayout);
	}
	
	if(taskInfoDataObject.hasOwnProperty('sMSText'))
	{
		var sMSText = taskInfoDataObject['sMSText'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_sMSText"]').text(sMSText);
	}
	
	if(taskInfoDataObject.hasOwnProperty('isActive'))
	{
		var isActive = taskInfoDataObject['isActive'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_isActive"]').text(isActive);
	}
	
	var taskStartDate = taskInfoDataObject['taskStartDate'];            		
	parentElement.find('[data-name="'+prefix+'TaskInfo_taskStartDate"]').text(taskStartDate);
	
	if(taskInfoDataObject.hasOwnProperty('taskFrequency'))
	{
		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_taskFrequency"]').text(taskFrequency);
	}
	
	if(taskInfoDataObject.hasOwnProperty('taskFrequencyUnit'))
	{
		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_taskFrequencyUnit"]').text(taskFrequencyUnit);
	}
	
	if(taskInfoDataObject.hasOwnProperty('isRecurring'))
	{
		var isRecurring = taskInfoDataObject['isRecurring'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_isRecurring"]').text(isRecurring);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunType'))
	{
		var firstRunType = taskInfoDataObject['firstRunType'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunType"]').text(firstRunType);
	}
	
	if(taskInfoDataObject.hasOwnProperty('dateColumnAlias'))
	{
		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_dateColumnAlias"]').text(dateColumnAlias);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateCalculationMethod"]').text(firstRunDateCalculationMethod);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInYears"]').text(firstRunDateGapInYears);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInMonths"]').text(firstRunDateGapInMonths);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInDays"]').text(firstRunDateGapInDays);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInHours"]').text(firstRunDateGapInHours);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInMinutes"]').text(firstRunDateGapInMinutes);
	}
	
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		
		parentElement.find('[data-name="'+prefix+'TaskInfo_firstRunDateGapInSeconds"]').text(firstRunDateGapInSeconds);
	}

}
function ajaxDemoForTaskInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForTaskInfo').textContent;	
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
			refreshPanelForTaskInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForTaskInfo(msg)
{
	var executeCustomAPIButtonForTaskInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForTaskInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:TaskInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForTaskInfo.click();
}
function refreshPanelForTaskInfo()
{
	var demoRefreshButtonForTaskInfo = document.getElementById("FormWBEntity:demoRefreshButtonTaskInfo");
	demoRefreshButtonForTaskInfo.click();
}
function initializePanelOnLoadForCreateTaskInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForTaskInfo = initParamsObj;
	initializeTaskInfoPage();	
	doAfterTaskInfoPanelRefreshed();
	initializeTaskInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForTaskInfoExt();
	fetchTaskInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="taskInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForTaskInfo = 0;
function initializePanelOnLoadForSearchTaskInfo()
{
	initializeTaskInfoPage();	
	initializeTaskInfoLookupFields();
	doAfterPanelInitializedForTaskInfoExt();
	gLoadDashboardResultsForTaskInfo =1;
	//retrieveTaskInfoList();
}
function initializePanelOnLoadForViewTaskInfo(urlParams)
{
	initializeTaskInfoPage();	
	doAfterTaskInfoPanelRefreshed();
	initializeTaskInfoLookupFields(urlParams);
	doAfterPanelInitializedForTaskInfoExt();
	retrieveTaskInfo(urlParams);
	initDatePicker();
	$('[data-name="taskInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeTaskInfoPage()
{
	initializePageOnload();	
	//initializeTaskInfoTemplateVariables();
}
function initializeTaskInfoTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="taskInfoSearchResults"]').find('[data-name="taskInfoRow"]');
	if(searchResultsRowObj.length > 0 && gTaskInfoSearchResultsTableRowTemplate.length == 0)
	{
		gTaskInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	    initializeTaskExecutionInfoTemplateVariables();
    initializeTaskLayoutParametersTemplateVariables();
    initializeEmailAttachmentLayoutTemplateVariables();

}
function retrieveTaskInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var taskInfoId = paramsMap['taskInfoId'];
	var taskInfo = {};
	taskInfo['taskInfoId'] = taskInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				taskInfo[key] = paramsMap[key];
			}
	}
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
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
                	var taskInfoDataObject = responseData['taskInfoDataObject'];
    				setTaskInfoInViewPage(taskInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForTaskInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchTaskInfoDefaultData();
}
function fetchTaskInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'TaskInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskInfo = responseData['taskInfo'];
            	document.getElementById('idValueForTaskInfo').value = '';
			    
			    resetFormForTaskExecutionInfo();
			    resetTableForTaskExecutionInfo();
			    
			    resetFormForTaskLayoutParameters();
			    resetTableForTaskLayoutParameters();
			    
			    resetFormForEmailAttachmentLayout();
			    resetTableForEmailAttachmentLayout();
			    
            	setTaskInfoData(taskInfo);
            }
        }
    });	
}
function fetchTaskInfoTestData() 
{
	var taskInfo = getDataForTaskInfo();
    var searchJsonData = {'objectType' : 'TaskInfo', 'paramsInfo' : JSON.stringify(taskInfo)};
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
            	var taskInfo = responseData['taskInfo'];
            	document.getElementById('idValueForTaskInfo').value = '';
			    
			    resetFormForTaskExecutionInfo();
			    resetTableForTaskExecutionInfo();
			    
			    resetFormForTaskLayoutParameters();
			    resetTableForTaskLayoutParameters();
			    
			    resetFormForEmailAttachmentLayout();
			    resetTableForEmailAttachmentLayout();
			    
            	setTaskInfoData(taskInfo);
            }
        }
    });	
}
function setTaskInfoData(taskInfoDataObject)
{
	var prefix = "";
		
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskName'))
	{
		var taskName = taskInfoDataObject['taskName'];            		
		$('#'+prefix+'TaskInfo_taskName').val(taskName);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskDescription'))
	{
		var taskDescription = taskInfoDataObject['taskDescription'];            		
		$('#'+prefix+'TaskInfo_taskDescription').val(taskDescription);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('taskType'))
	{
		var taskType = taskInfoDataObject['taskType'];            		
		$('#'+prefix+'TaskInfo_taskType').val(taskType);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('apiName'))
	{
		var apiName = taskInfoDataObject['apiName'];            		
		$('#'+prefix+'TaskInfo_apiName').val(apiName);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetEntityQuery'))
	{
		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
		$('#'+prefix+'TaskInfo_targetEntityQuery').val(targetEntityQuery);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetUserIdColumnAlias'))
	{
		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
		$('#'+prefix+'TaskInfo_targetUserIdColumnAlias').val(targetUserIdColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('targetEntityIdColumnAlias'))
	{
		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
		$('#'+prefix+'TaskInfo_targetEntityIdColumnAlias').val(targetEntityIdColumnAlias);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableInAppNotification'))
	{
		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
		$('#'+prefix+'TaskInfo_enableInAppNotification').val(enableInAppNotification);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('inAppNotificationLayout'))
	{
		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_inAppNotificationLayout').val(inAppNotificationLayout);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableEmailNotification'))
	{
		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
		$('#'+prefix+'TaskInfo_enableEmailNotification').val(enableEmailNotification);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailColumnAlias'))
	{
		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
		$('#'+prefix+'TaskInfo_emailColumnAlias').val(emailColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailNotificationLayout'))
	{
		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_emailNotificationLayout').val(emailNotificationLayout);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('emailSubject'))
	{
		var emailSubject = taskInfoDataObject['emailSubject'];            		
		$('#'+prefix+'TaskInfo_emailSubject').val(emailSubject);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('enableSmsNotification'))
	{
		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
		$('#'+prefix+'TaskInfo_enableSmsNotification').val(enableSmsNotification);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('smsColumnAlias'))
	{
		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
		$('#'+prefix+'TaskInfo_smsColumnAlias').val(smsColumnAlias);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('smsNotificationLayout'))
	{
		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
		$('#'+prefix+'TaskInfo_smsNotificationLayout').val(smsNotificationLayout);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('sMSText'))
	{
		var sMSText = taskInfoDataObject['sMSText'];            		
		$('#'+prefix+'TaskInfo_sMSText').val(sMSText);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('isActive'))
	{
		var isActive = taskInfoDataObject['isActive'];            		
		$('#'+prefix+'TaskInfo_isActive').val(isActive);
	}
	
	//DateTime
	if(taskInfoDataObject.hasOwnProperty('taskStartDate'))
	{
		var taskStartDate = taskInfoDataObject['taskStartDate'];            		
		$('#'+prefix+'TaskInfo_taskStartDate').val(taskStartDate);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('taskFrequency'))
	{
		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
		$('#'+prefix+'TaskInfo_taskFrequency').val(taskFrequency);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('taskFrequencyUnit'))
	{
		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
		$('#'+prefix+'TaskInfo_taskFrequencyUnit').val(taskFrequencyUnit);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('isRecurring'))
	{
		var isRecurring = taskInfoDataObject['isRecurring'];            		
		$('#'+prefix+'TaskInfo_isRecurring').val(isRecurring);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('firstRunType'))
	{
		var firstRunType = taskInfoDataObject['firstRunType'];            		
		$('#'+prefix+'TaskInfo_firstRunType').val(firstRunType);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('dateColumnAlias'))
	{
		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
		$('#'+prefix+'TaskInfo_dateColumnAlias').val(dateColumnAlias);
	}
	
	//Combo
	if(taskInfoDataObject.hasOwnProperty('firstRunDateCalculationMethod'))
	{
		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
		$('#'+prefix+'TaskInfo_firstRunDateCalculationMethod').val(firstRunDateCalculationMethod);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInYears'))
	{
		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInYears').val(firstRunDateGapInYears);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMonths'))
	{
		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInMonths').val(firstRunDateGapInMonths);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInDays'))
	{
		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInDays').val(firstRunDateGapInDays);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInHours'))
	{
		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInHours').val(firstRunDateGapInHours);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInMinutes'))
	{
		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInMinutes').val(firstRunDateGapInMinutes);
	}
	
	//Input
	if(taskInfoDataObject.hasOwnProperty('firstRunDateGapInSeconds'))
	{
		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		
		$('#'+prefix+'TaskInfo_firstRunDateGapInSeconds').val(firstRunDateGapInSeconds);
	}

	$('[data-name="taskInfoActionButtonDiv"]').hide();
}
function initializeTaskInfoLookupFields(paramsMap) 
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
    
    initializeTaskExecutionInfoLookupSelectList();
    
    initializeTaskLayoutParametersLookupSelectList();
    
    initializeEmailAttachmentLayoutLookupSelectList();
    
    var searchDiv = $('[data-name="taskInfoSearchResultsDiv"]');
	
    
}

function doAfterTaskInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForTaskInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForTaskInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'taskType')
	{
	}
	else if(fieldName == 'enableInAppNotification')
	{
	}
	else if(fieldName == 'enableEmailNotification')
	{
	}
	else if(fieldName == 'enableSmsNotification')
	{
	}
	else if(fieldName == 'isActive')
	{
	}
	else if(fieldName == 'taskFrequencyUnit')
	{
	}
	else if(fieldName == 'isRecurring')
	{
	}
	else if(fieldName == 'firstRunType')
	{
	}
	else if(fieldName == 'firstRunDateCalculationMethod')
	{
	}

	doAfterSelectOptionChangedForTaskInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForTaskInfo(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForTaskInfoExt(fieldName)
}
function getEntityIdForTaskInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForTaskInfo').textContent;	
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
function openPrintPageForTaskInfo()
{
	var entityId = getEntityIdForTaskInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/TaskInfo.jsp?taskInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForTaskInfo(paramsMap)
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
	var taskInfo = {};
		
	//Input
	if($("#"+prefix+"TaskInfo_taskName").length == 1)
	{
		var taskName = $('#'+prefix+'TaskInfo_taskName').val();
		taskInfo['taskName'] = taskName;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_taskDescription").length == 1)
	{
		var taskDescription = $('#'+prefix+'TaskInfo_taskDescription').val();
		taskInfo['taskDescription'] = taskDescription;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_taskType").length == 1)
	{
		var taskType = $('#'+prefix+'TaskInfo_taskType').val();
		taskInfo['taskType'] = taskType;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_apiName").length == 1)
	{
		var apiName = $('#'+prefix+'TaskInfo_apiName').val();
		taskInfo['apiName'] = apiName;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_targetEntityQuery").length == 1)
	{
		var targetEntityQuery = $('#'+prefix+'TaskInfo_targetEntityQuery').val();
		taskInfo['targetEntityQuery'] = targetEntityQuery;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_targetUserIdColumnAlias").length == 1)
	{
		var targetUserIdColumnAlias = $('#'+prefix+'TaskInfo_targetUserIdColumnAlias').val();
		taskInfo['targetUserIdColumnAlias'] = targetUserIdColumnAlias;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_targetEntityIdColumnAlias").length == 1)
	{
		var targetEntityIdColumnAlias = $('#'+prefix+'TaskInfo_targetEntityIdColumnAlias').val();
		taskInfo['targetEntityIdColumnAlias'] = targetEntityIdColumnAlias;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_enableInAppNotification").length == 1)
	{
		var enableInAppNotification = $('#'+prefix+'TaskInfo_enableInAppNotification').val();
		taskInfo['enableInAppNotification'] = enableInAppNotification;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_inAppNotificationLayout").length == 1)
	{
		var inAppNotificationLayout = $('#'+prefix+'TaskInfo_inAppNotificationLayout').val();
		taskInfo['inAppNotificationLayout'] = inAppNotificationLayout;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_enableEmailNotification").length == 1)
	{
		var enableEmailNotification = $('#'+prefix+'TaskInfo_enableEmailNotification').val();
		taskInfo['enableEmailNotification'] = enableEmailNotification;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_emailColumnAlias").length == 1)
	{
		var emailColumnAlias = $('#'+prefix+'TaskInfo_emailColumnAlias').val();
		taskInfo['emailColumnAlias'] = emailColumnAlias;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_emailNotificationLayout").length == 1)
	{
		var emailNotificationLayout = $('#'+prefix+'TaskInfo_emailNotificationLayout').val();
		taskInfo['emailNotificationLayout'] = emailNotificationLayout;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_emailSubject").length == 1)
	{
		var emailSubject = $('#'+prefix+'TaskInfo_emailSubject').val();
		taskInfo['emailSubject'] = emailSubject;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_enableSmsNotification").length == 1)
	{
		var enableSmsNotification = $('#'+prefix+'TaskInfo_enableSmsNotification').val();
		taskInfo['enableSmsNotification'] = enableSmsNotification;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_smsColumnAlias").length == 1)
	{
		var smsColumnAlias = $('#'+prefix+'TaskInfo_smsColumnAlias').val();
		taskInfo['smsColumnAlias'] = smsColumnAlias;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_smsNotificationLayout").length == 1)
	{
		var smsNotificationLayout = $('#'+prefix+'TaskInfo_smsNotificationLayout').val();
		taskInfo['smsNotificationLayout'] = smsNotificationLayout;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_sMSText").length == 1)
	{
		var sMSText = $('#'+prefix+'TaskInfo_sMSText').val();
		taskInfo['sMSText'] = sMSText;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_isActive").length == 1)
	{
		var isActive = $('#'+prefix+'TaskInfo_isActive').val();
		taskInfo['isActive'] = isActive;
	}
	
	//Datetime
	if($("#"+prefix+"TaskInfo_taskStartDate").length == 1)
	{
		var taskStartDate = $('#'+prefix+'TaskInfo_taskStartDate').val();
		taskInfo['taskStartDate'] = taskStartDate;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_taskFrequency").length == 1)
	{
		var taskFrequency = $('#'+prefix+'TaskInfo_taskFrequency').val();
		taskInfo['taskFrequency'] = taskFrequency;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_taskFrequencyUnit").length == 1)
	{
		var taskFrequencyUnit = $('#'+prefix+'TaskInfo_taskFrequencyUnit').val();
		taskInfo['taskFrequencyUnit'] = taskFrequencyUnit;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_isRecurring").length == 1)
	{
		var isRecurring = $('#'+prefix+'TaskInfo_isRecurring').val();
		taskInfo['isRecurring'] = isRecurring;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_firstRunType").length == 1)
	{
		var firstRunType = $('#'+prefix+'TaskInfo_firstRunType').val();
		taskInfo['firstRunType'] = firstRunType;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_dateColumnAlias").length == 1)
	{
		var dateColumnAlias = $('#'+prefix+'TaskInfo_dateColumnAlias').val();
		taskInfo['dateColumnAlias'] = dateColumnAlias;
	}
	
	//Combo
	if($("#"+prefix+"TaskInfo_firstRunDateCalculationMethod").length == 1)
	{
		var firstRunDateCalculationMethod = $('#'+prefix+'TaskInfo_firstRunDateCalculationMethod').val();
		taskInfo['firstRunDateCalculationMethod'] = firstRunDateCalculationMethod;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInYears").length == 1)
	{
		var firstRunDateGapInYears = $('#'+prefix+'TaskInfo_firstRunDateGapInYears').val();
		taskInfo['firstRunDateGapInYears'] = firstRunDateGapInYears;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInMonths").length == 1)
	{
		var firstRunDateGapInMonths = $('#'+prefix+'TaskInfo_firstRunDateGapInMonths').val();
		taskInfo['firstRunDateGapInMonths'] = firstRunDateGapInMonths;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInDays").length == 1)
	{
		var firstRunDateGapInDays = $('#'+prefix+'TaskInfo_firstRunDateGapInDays').val();
		taskInfo['firstRunDateGapInDays'] = firstRunDateGapInDays;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInHours").length == 1)
	{
		var firstRunDateGapInHours = $('#'+prefix+'TaskInfo_firstRunDateGapInHours').val();
		taskInfo['firstRunDateGapInHours'] = firstRunDateGapInHours;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInMinutes").length == 1)
	{
		var firstRunDateGapInMinutes = $('#'+prefix+'TaskInfo_firstRunDateGapInMinutes').val();
		taskInfo['firstRunDateGapInMinutes'] = firstRunDateGapInMinutes;
	}
	
	//Input
	if($("#"+prefix+"TaskInfo_firstRunDateGapInSeconds").length == 1)
	{
		var firstRunDateGapInSeconds = $('#'+prefix+'TaskInfo_firstRunDateGapInSeconds').val();
		taskInfo['firstRunDateGapInSeconds'] = firstRunDateGapInSeconds;
	}

	
	return taskInfo;
}
function createTaskInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var taskInfo = getDataForTaskInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				taskInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForTaskInfo)
	{
		paramsInfo[key] = gInitParamsObjForTaskInfo[key];
	}
	var validationObject = doTaskInfoValidation(taskInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	taskInfo['additionalParamsInfo'] = paramsInfo;
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskInfoRequestUnderProcess == 1)
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
            	gTaskInfoRequestUnderProcess = 0;
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gTaskInfoRequestUnderProcess = 0;
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
            	var taskInfoId = responseData['taskInfoId'];
            	var popupElement = $('[data-name="TaskInfoPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var taskInfoDataObject = responseData['taskInfoDataObject'];
    				setTaskInfoInViewPage(taskInfoDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewTaskInfo.html?taskInfoId="+taskInfoId; 
        		}
				
            }
        }
    });
}
function resetTableForTaskInfo()
{
	var taskInfoListElement = $("[data-name='taskInfoList']");
	var previousDataRowList  = taskInfoListElement.find('[data-name="taskInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForTaskInfo(paramsMap)
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
	$('#'+prefix+'idValueForTaskInfo').val('');
		
	//Input
	$('#'+prefix+'TaskInfo_taskName').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_taskDescription').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_taskType').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_apiName').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_targetEntityQuery').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_targetUserIdColumnAlias').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_targetEntityIdColumnAlias').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_enableInAppNotification').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_inAppNotificationLayout').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_enableEmailNotification').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_emailColumnAlias').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_emailNotificationLayout').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_emailSubject').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_enableSmsNotification').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_smsColumnAlias').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_smsNotificationLayout').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_sMSText').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_isActive').val('');
	
	//DateTime
	$('#'+prefix+'TaskInfo_taskStartDate').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_taskFrequency').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_taskFrequencyUnit').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_isRecurring').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_firstRunType').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_dateColumnAlias').val('');
	
	//Combo
	$('#'+prefix+'TaskInfo_firstRunDateCalculationMethod').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInYears').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInMonths').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInDays').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInHours').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInMinutes').val('');
	
	//Input
	$('#'+prefix+'TaskInfo_firstRunDateGapInSeconds').val('');

	$('[data-name="taskInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="taskInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="taskInfoActionButtonDiv"]').hide();
	enableTaskInfoUpdateDisallowedFields(paramsMap);
    
    resetFormForTaskExecutionInfo();
    resetTableForTaskExecutionInfo();
    
    resetFormForTaskLayoutParameters();
    resetTableForTaskLayoutParameters();
    
    resetFormForEmailAttachmentLayout();
    resetTableForEmailAttachmentLayout();
    
}
function enableTaskInfoUpdateDisallowedFields(paramsMap)
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
function updateTaskInfo(paramsMap)
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
	var taskInfo = getDataForTaskInfo(paramsMap)
	if($("#"+prefix+"idValueForTaskInfo").length == 1)
	{
		var taskInfoId = $("#"+prefix+"idValueForTaskInfo").val();
		taskInfo['taskInfoId'] = taskInfoId;
	}
	var validationObject = doTaskInfoValidation(taskInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
	var urlContextPath = "";//getContextPath();
	if(gTaskInfoRequestUnderProcess == 1)
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
                    	gTaskInfoRequestUnderProcess = 0;
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gTaskInfoRequestUnderProcess = 0;
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
function deleteTaskInfo(paramsMap)
{		
	var taskInfoId = document.getElementById('idValueForTaskInfo').value;
	deleteSelectedTaskInfo(taskInfoId, paramsMap);
}
function deleteSelectedTaskInfo(taskInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var taskInfo = {};
	taskInfo['taskInfoId'] = taskInfoId;	
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
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
function loadSearchResultsForTaskInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'TaskInfo'};
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
            	var taskInfoSearchResultsElement = $("[data-name='taskInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var taskInfoDataObject = searchResultObjectsList[i];
					            		var taskName = taskInfoDataObject['taskName'];            		
            		var taskDescription = taskInfoDataObject['taskDescription'];            		
            		var taskType = taskInfoDataObject['taskType'];            		
            		var apiName = taskInfoDataObject['apiName'];            		
            		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
            		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
            		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
            		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
            		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
            		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
            		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
            		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
            		var emailSubject = taskInfoDataObject['emailSubject'];            		
            		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
            		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
            		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
            		var sMSText = taskInfoDataObject['sMSText'];            		
            		var isActive = taskInfoDataObject['isActive'];            		
            		var taskStartDate = taskInfoDataObject['taskStartDate'];            		
            		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
            		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
            		var isRecurring = taskInfoDataObject['isRecurring'];            		
            		var firstRunType = taskInfoDataObject['firstRunType'];            		
            		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
            		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
            		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
            		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
            		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
            		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
            		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
            		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		

            		var resultRowTemnplate = $(gTaskInfoSearchResultsTableRowTemplate);
					            		var taskName = taskInfoDataObject['taskName'];            		
            	    resultRowTemnplate.find("[data-name='taskName']").text(taskName);
            		var taskDescription = taskInfoDataObject['taskDescription'];            		
            	    resultRowTemnplate.find("[data-name='taskDescription']").text(taskDescription);
            		var taskType = taskInfoDataObject['taskType'];            		
            	    resultRowTemnplate.find("[data-name='taskType']").text(taskType);
            		var apiName = taskInfoDataObject['apiName'];            		
            	    resultRowTemnplate.find("[data-name='apiName']").text(apiName);
            		var targetEntityQuery = taskInfoDataObject['targetEntityQuery'];            		
            	    resultRowTemnplate.find("[data-name='targetEntityQuery']").text(targetEntityQuery);
            		var targetUserIdColumnAlias = taskInfoDataObject['targetUserIdColumnAlias'];            		
            	    resultRowTemnplate.find("[data-name='targetUserIdColumnAlias']").text(targetUserIdColumnAlias);
            		var targetEntityIdColumnAlias = taskInfoDataObject['targetEntityIdColumnAlias'];            		
            	    resultRowTemnplate.find("[data-name='targetEntityIdColumnAlias']").text(targetEntityIdColumnAlias);
            		var enableInAppNotification = taskInfoDataObject['enableInAppNotification'];            		
            	    resultRowTemnplate.find("[data-name='enableInAppNotification']").text(enableInAppNotification);
            		var inAppNotificationLayout = taskInfoDataObject['inAppNotificationLayout'];            		
            	    resultRowTemnplate.find("[data-name='inAppNotificationLayout']").text(inAppNotificationLayout);
            		var enableEmailNotification = taskInfoDataObject['enableEmailNotification'];            		
            	    resultRowTemnplate.find("[data-name='enableEmailNotification']").text(enableEmailNotification);
            		var emailColumnAlias = taskInfoDataObject['emailColumnAlias'];            		
            	    resultRowTemnplate.find("[data-name='emailColumnAlias']").text(emailColumnAlias);
            		var emailNotificationLayout = taskInfoDataObject['emailNotificationLayout'];            		
            	    resultRowTemnplate.find("[data-name='emailNotificationLayout']").text(emailNotificationLayout);
            		var emailSubject = taskInfoDataObject['emailSubject'];            		
            	    resultRowTemnplate.find("[data-name='emailSubject']").text(emailSubject);
            		var enableSmsNotification = taskInfoDataObject['enableSmsNotification'];            		
            	    resultRowTemnplate.find("[data-name='enableSmsNotification']").text(enableSmsNotification);
            		var smsColumnAlias = taskInfoDataObject['smsColumnAlias'];            		
            	    resultRowTemnplate.find("[data-name='smsColumnAlias']").text(smsColumnAlias);
            		var smsNotificationLayout = taskInfoDataObject['smsNotificationLayout'];            		
            	    resultRowTemnplate.find("[data-name='smsNotificationLayout']").text(smsNotificationLayout);
            		var sMSText = taskInfoDataObject['sMSText'];            		
            	    resultRowTemnplate.find("[data-name='sMSText']").text(sMSText);
            		var isActive = taskInfoDataObject['isActive'];            		
            	    resultRowTemnplate.find("[data-name='isActive']").text(isActive);
            		var taskStartDate = taskInfoDataObject['taskStartDate'];            		
            	    resultRowTemnplate.find("[data-name='taskStartDate']").text(taskStartDate);
            		var taskFrequency = taskInfoDataObject['taskFrequency'];            		
            	    resultRowTemnplate.find("[data-name='taskFrequency']").text(taskFrequency);
            		var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];            		
            	    resultRowTemnplate.find("[data-name='taskFrequencyUnit']").text(taskFrequencyUnit);
            		var isRecurring = taskInfoDataObject['isRecurring'];            		
            	    resultRowTemnplate.find("[data-name='isRecurring']").text(isRecurring);
            		var firstRunType = taskInfoDataObject['firstRunType'];            		
            	    resultRowTemnplate.find("[data-name='firstRunType']").text(firstRunType);
            		var dateColumnAlias = taskInfoDataObject['dateColumnAlias'];            		
            	    resultRowTemnplate.find("[data-name='dateColumnAlias']").text(dateColumnAlias);
            		var firstRunDateCalculationMethod = taskInfoDataObject['firstRunDateCalculationMethod'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateCalculationMethod']").text(firstRunDateCalculationMethod);
            		var firstRunDateGapInYears = taskInfoDataObject['firstRunDateGapInYears'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInYears']").text(firstRunDateGapInYears);
            		var firstRunDateGapInMonths = taskInfoDataObject['firstRunDateGapInMonths'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInMonths']").text(firstRunDateGapInMonths);
            		var firstRunDateGapInDays = taskInfoDataObject['firstRunDateGapInDays'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInDays']").text(firstRunDateGapInDays);
            		var firstRunDateGapInHours = taskInfoDataObject['firstRunDateGapInHours'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInHours']").text(firstRunDateGapInHours);
            		var firstRunDateGapInMinutes = taskInfoDataObject['firstRunDateGapInMinutes'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInMinutes']").text(firstRunDateGapInMinutes);
            		var firstRunDateGapInSeconds = taskInfoDataObject['firstRunDateGapInSeconds'];            		
            	    resultRowTemnplate.find("[data-name='firstRunDateGapInSeconds']").text(firstRunDateGapInSeconds);

            	    resultRowTemnplate.data('taskInfo', taskInfoDataObject);
            	    taskInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gTaskInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForTaskInfoForEdit(taskInfoLinkElement)
{
	var taskInfoRowElement = $(taskInfoLinkElement).parents('[data-name="taskInfoRow"]');
    var taskInfoDataObject  = taskInfoRowElement.data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var taskInfoPopupElement = $('[data-name="TaskInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setTaskInfoInViewPage(taskInfoDataObject);
	    $("#TaskInfo-tab").trigger("click");
	}
	else if(taskInfoPopupElement.length > 0)
	{
	    setTaskInfoInViewPage(taskInfoDataObject);
		$('[data-name="TaskInfoPopup"]').find('[data-name="taskInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="TaskInfoPopup"]').find('[data-name="taskInfoViewFormButtonsDiv"]').show();
	    showPopup('TaskInfoPopup', taskInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewTaskInfo.html?taskInfoId="+taskInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openTaskInfoCreatePageForNew(linkElement)
{
	var taskInfoPopupElement = $('[data-name="TaskInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForTaskInfo();
	    $("#TaskInfo-tab").trigger("click");
    }
	else if(taskInfoPopupElement.length > 0)
	{
		resetFormForTaskInfo();
	    showPopup('TaskInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateTaskInfo.html";
    }
}
function showTaskInfoPopupForEdit(taskInfoLinkElement)
{
	var taskInfoRowElement = $(taskInfoLinkElement).parents('[data-name="taskInfoRow"]');
    var taskInfoDataObject  = taskInfoRowElement.data('taskInfo');
    setTaskInfoInViewPage(taskInfoDataObject);
    showPopup('TaskInfoPopup', taskInfoLinkElement, 1);
    $("#TaskInfo-tab").trigger("click");
}
function showTaskInfoPopupForNew(buttonElement)
{
	resetFormForTaskInfo();
    showPopup('TaskInfoPopup', buttonElement, 1);
    $("#TaskInfo-tab").trigger("click");
}
function deleteThisTaskInfo(taskInfoLinkElement, paramsMap)
{
	var taskInfoRowElement = $(taskInfoLinkElement).parents('[data-name="taskInfoRow"]');
    var taskInfoDataObject  = taskInfoRowElement.data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	deleteSelectedTaskInfo(taskInfoId, paramsMap);
}
function displayTaskInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var taskInfoSearchResults = searchResultsDiv.find('[data-name="taskInfoSearchResults"]');
	//taskInfoSearchResults.find('[data-name="taskInfoRow"]').remove();
	var previousDataRowList  = taskInfoSearchResults.find('[data-name="taskInfoRow"]');
	taskInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = taskInfoSearchResults.find('[data-name="taskInfoRow"]');
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
        var taskInfoDataObject = searchResultObjectsList[i];
        loadTaskInfoViewData(taskInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = taskInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='taskInfoVwTxnStatus']").text(vwTxnStatus);
		if(taskInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="taskInfoActionButton"]').text(taskInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="taskInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="taskInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="taskInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="taskInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('taskInfo', taskInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    taskInfoSearchResults.append(resultRowTemplate);
	    processResultRowForTaskInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForTaskInfo == 1)
	{
    	getDashboardResultsForTaskInfo();
	}
}
var taskInfoSearchResultsDivName = "taskInfoSearchResultsDiv";
var gTaskInfoSearchInputParamsList = [];
function retrieveTaskInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + taskInfoSearchResultsDivName + '"]');
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
    fetchTaskInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchTaskInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskInfoSearchResultsDivName; 
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
        'objectType': "TaskInfo"
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
            	this.showPreviousRecords = "showPreviousTaskInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskInfoRecords";
            	this.showNextRecords = "showNextTaskInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskInfoResults";
                var taskInfoList = responseData['taskInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayTaskInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'taskInfoSearchResults', 'taskInfoRow', setTaskInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getTaskInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + taskInfoSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="taskInfoDB_taskName"]').length == 1)
		{
		    var taskName = searchDiv.find('[data-name="taskInfoDB_taskName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskName', 'userInputValue':taskName});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_taskDescription"]').length == 1)
		{
		    var taskDescription = searchDiv.find('[data-name="taskInfoDB_taskDescription"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskDescription', 'userInputValue':taskDescription});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_taskType"]').length == 1)
		{
		    var taskType = searchDiv.find('[data-name="taskInfoDB_taskType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskType', 'userInputValue':taskType});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_apiName"]').length == 1)
		{
		    var apiName = searchDiv.find('[data-name="taskInfoDB_apiName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'apiName', 'userInputValue':apiName});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_targetEntityQuery"]').length == 1)
		{
		    var targetEntityQuery = searchDiv.find('[data-name="taskInfoDB_targetEntityQuery"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetEntityQuery', 'userInputValue':targetEntityQuery});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_targetUserIdColumnAlias"]').length == 1)
		{
		    var targetUserIdColumnAlias = searchDiv.find('[data-name="taskInfoDB_targetUserIdColumnAlias"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetUserIdColumnAlias', 'userInputValue':targetUserIdColumnAlias});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_targetEntityIdColumnAlias"]').length == 1)
		{
		    var targetEntityIdColumnAlias = searchDiv.find('[data-name="taskInfoDB_targetEntityIdColumnAlias"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'targetEntityIdColumnAlias', 'userInputValue':targetEntityIdColumnAlias});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_enableInAppNotification"]').length == 1)
		{
		    var enableInAppNotification = searchDiv.find('[data-name="taskInfoDB_enableInAppNotification"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'enableInAppNotification', 'userInputValue':enableInAppNotification});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_inAppNotificationLayout"]').length == 1)
		{
		    var inAppNotificationLayout = searchDiv.find('[data-name="taskInfoDB_inAppNotificationLayout"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'inAppNotificationLayout', 'userInputValue':inAppNotificationLayout});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_enableEmailNotification"]').length == 1)
		{
		    var enableEmailNotification = searchDiv.find('[data-name="taskInfoDB_enableEmailNotification"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'enableEmailNotification', 'userInputValue':enableEmailNotification});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_emailColumnAlias"]').length == 1)
		{
		    var emailColumnAlias = searchDiv.find('[data-name="taskInfoDB_emailColumnAlias"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'emailColumnAlias', 'userInputValue':emailColumnAlias});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_emailNotificationLayout"]').length == 1)
		{
		    var emailNotificationLayout = searchDiv.find('[data-name="taskInfoDB_emailNotificationLayout"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'emailNotificationLayout', 'userInputValue':emailNotificationLayout});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_emailSubject"]').length == 1)
		{
		    var emailSubject = searchDiv.find('[data-name="taskInfoDB_emailSubject"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'emailSubject', 'userInputValue':emailSubject});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_enableSmsNotification"]').length == 1)
		{
		    var enableSmsNotification = searchDiv.find('[data-name="taskInfoDB_enableSmsNotification"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'enableSmsNotification', 'userInputValue':enableSmsNotification});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_smsColumnAlias"]').length == 1)
		{
		    var smsColumnAlias = searchDiv.find('[data-name="taskInfoDB_smsColumnAlias"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'smsColumnAlias', 'userInputValue':smsColumnAlias});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_smsNotificationLayout"]').length == 1)
		{
		    var smsNotificationLayout = searchDiv.find('[data-name="taskInfoDB_smsNotificationLayout"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'smsNotificationLayout', 'userInputValue':smsNotificationLayout});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_sMSText"]').length == 1)
		{
		    var sMSText = searchDiv.find('[data-name="taskInfoDB_sMSText"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'sMSText', 'userInputValue':sMSText});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_isActive"]').length == 1)
		{
		    var isActive = searchDiv.find('[data-name="taskInfoDB_isActive"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'isActive', 'userInputValue':isActive});
		}
		
		//Datetime
		if(searchDiv.find('[data-name="taskInfoDB_taskStartDate"]').length == 1)
		{
		    var taskStartDate = searchDiv.find('[data-name="taskInfoDB_taskStartDate"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskStartDate', 'userInputValue':taskStartDate});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_taskFrequency"]').length == 1)
		{
		    var taskFrequency = searchDiv.find('[data-name="taskInfoDB_taskFrequency"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskFrequency', 'userInputValue':taskFrequency});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_taskFrequencyUnit"]').length == 1)
		{
		    var taskFrequencyUnit = searchDiv.find('[data-name="taskInfoDB_taskFrequencyUnit"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'taskFrequencyUnit', 'userInputValue':taskFrequencyUnit});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_isRecurring"]').length == 1)
		{
		    var isRecurring = searchDiv.find('[data-name="taskInfoDB_isRecurring"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'isRecurring', 'userInputValue':isRecurring});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_firstRunType"]').length == 1)
		{
		    var firstRunType = searchDiv.find('[data-name="taskInfoDB_firstRunType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunType', 'userInputValue':firstRunType});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_dateColumnAlias"]').length == 1)
		{
		    var dateColumnAlias = searchDiv.find('[data-name="taskInfoDB_dateColumnAlias"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'dateColumnAlias', 'userInputValue':dateColumnAlias});
		}
		
		//Combo
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateCalculationMethod"]').length == 1)
		{
		    var firstRunDateCalculationMethod = searchDiv.find('[data-name="taskInfoDB_firstRunDateCalculationMethod"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateCalculationMethod', 'userInputValue':firstRunDateCalculationMethod});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInYears"]').length == 1)
		{
		    var firstRunDateGapInYears = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInYears"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInYears', 'userInputValue':firstRunDateGapInYears});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInMonths"]').length == 1)
		{
		    var firstRunDateGapInMonths = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInMonths"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInMonths', 'userInputValue':firstRunDateGapInMonths});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInDays"]').length == 1)
		{
		    var firstRunDateGapInDays = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInDays"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInDays', 'userInputValue':firstRunDateGapInDays});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInHours"]').length == 1)
		{
		    var firstRunDateGapInHours = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInHours"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInHours', 'userInputValue':firstRunDateGapInHours});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInMinutes"]').length == 1)
		{
		    var firstRunDateGapInMinutes = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInMinutes"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInMinutes', 'userInputValue':firstRunDateGapInMinutes});
		}
		
		//Input
		if(searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInSeconds"]').length == 1)
		{
		    var firstRunDateGapInSeconds = searchDiv.find('[data-name="taskInfoDB_firstRunDateGapInSeconds"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'firstRunDateGapInSeconds', 'userInputValue':firstRunDateGapInSeconds});
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
        gTaskInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + taskInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchTaskInfoSearchResultsList);
}
function showPreviousTaskInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchTaskInfoSearchResultsList);
}
function showNextTaskInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = taskInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchTaskInfoSearchResultsList);
}
function showPrevOrNextSearchTaskInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextTaskInfoRecords(e);
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
function lookupRowSelectedForTaskInfo(attributeName, attributeId)
{
	var taskInfo = getDataForTaskInfo();
	taskInfo['attributeName'] = attributeName;
	taskInfo['attributeId'] = attributeId;
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskInfo = responseData['taskInfo'];
            	setTaskInfoData(taskInfo);
            }
        }
    });	
}
function selectOptionChangedForTaskInfo(attributeName)
{
	var taskInfo = getDataForTaskInfo();
	taskInfo['attributeName'] = attributeName;
    var taskInfoJsonData = {'paramsInfo': JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskInfo = responseData['taskInfo'];
            	setTaskInfoData(taskInfo);
            	doAfterTaskInfoPanelRefreshed();
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
function retrieveDependentTaskInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'TaskInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var taskInfoList = responseData['taskInfoList'];
            	var taskInfoListElement = $("[data-name='taskInfoList']");
            	var previousDataRowList  = taskInfoListElement.find('[data-name="taskInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = taskInfoListElement.find('[data-name="taskInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//taskInfoListElement.empty();
            	for(var i=0; i<taskInfoList.length; i++)
        		{
            		var taskInfoDataObject = taskInfoList[i];
            		//var resultRowTemplate = $(gTaskInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
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
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    taskInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForTaskInfo(customEventName)
{
	var taskInfoId = document.getElementById('idValueForTaskInfo').value;
	var taskInfo = {'taskInfoId':taskInfoId, 'customEventName':customEventName};
    var taskInfoJsonData = {'paramsInfo':JSON.stringify(taskInfo), 'objectType' : 'TaskInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForTaskInfoExt(customEventName);
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskInfo = responseData['taskInfo'];
        		setTaskInfoInViewPage(taskInfo);
            }
    		doAfterExecuteCustomAPIForTaskInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnTaskInfo(e, paramsMap)
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
	var taskInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var taskInfoDataObject  = rowObj.data('taskInfo');
    	taskInfoId = taskInfoDataObject['taskInfoId'];
	}
    else
	{
    	taskInfoId = document.getElementById('idValueForTaskInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var taskInfoSearchParams = {'taskInfoId':taskInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	taskInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var taskInfoJsonData = {'paramsInfo':JSON.stringify(taskInfoSearchParams),  'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var taskInfo = responseData['taskInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="taskInfoRowActionButtonDiv"]').hide();
            		if(taskInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = taskInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(taskInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="taskInfoActionButton"]').text(taskInfo["nextAction"]);
            			selectedRowObj.find('[data-name="taskInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setTaskInfoInViewPage(taskInfo);
    			}
            }
        }
    });	
}
function downloadTaskInfoData()
{		
	var taskInfo = {};
    var taskInfoJsonData = {'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=TaskInfo");
            }
        }
    });
}
function uploadTaskInfoData(fileInfo)
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
	var taskInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var taskInfoJsonData = {'paramsInfo':JSON.stringify(taskInfo),  'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=TaskInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForTaskInfo()
{
    var taskInfoJsonData = {'objectType' : 'TaskInfo'};
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
        data: taskInfoJsonData,
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



function doTaskInfoLengthValidation(taskInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(taskInfoDataObject['taskName'], 500))
	{
		alertMessage += "\n Task Name length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['taskDescription'], 500))
	{
		alertMessage += "\n Task Description length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['taskType'], 20))
	{
		alertMessage += "\n Task Type length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['apiName'], 500))
	{
		alertMessage += "\n API Name length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['targetEntityQuery'], 100))
	{
		alertMessage += "\n Target Entity Query length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['targetUserIdColumnAlias'], 100))
	{
		alertMessage += "\n Target User Id Column Alias length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['targetEntityIdColumnAlias'], 100))
	{
		alertMessage += "\n Target Entity Id Column Alias length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['enableInAppNotification'], 20))
	{
		alertMessage += "\n Enable In App Notification length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['inAppNotificationLayout'], 100))
	{
		alertMessage += "\n InApp Notification Layout length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['enableEmailNotification'], 20))
	{
		alertMessage += "\n Enable Email Notification length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['emailColumnAlias'], 100))
	{
		alertMessage += "\n Email Column Alias length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['emailNotificationLayout'], 100))
	{
		alertMessage += "\n Email Notifaction Layout length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['emailSubject'], 500))
	{
		alertMessage += "\n Email Subject length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['enableSmsNotification'], 20))
	{
		alertMessage += "\n EnableSms Notification  length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['smsColumnAlias'], 100))
	{
		alertMessage += "\n Sms Column Alias length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['smsNotificationLayout'], 100))
	{
		alertMessage += "\n Sms Notifaction Layout length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['sMSText'], 500))
	{
		alertMessage += "\n SMS Text length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['isActive'], 20))
	{
		alertMessage += "\n Is Active length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['taskStartDate'], 50))
	{
		alertMessage += "\n Task Start Date length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['taskFrequency'], 10))
	{
		alertMessage += "\n Task Frequency length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['taskFrequencyUnit'], 20))
	{
		alertMessage += "\n Task Frequency Unit length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['isRecurring'], 20))
	{
		alertMessage += "\n Is Recurring length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunType'], 20))
	{
		alertMessage += "\n First Run Type length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['dateColumnAlias'], 100))
	{
		alertMessage += "\n Date Column Alias length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateCalculationMethod'], 20))
	{
		alertMessage += "\n First Run Date Calculation Method length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInYears'], 10))
	{
		alertMessage += "\n First Run Date Gap In Years length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInMonths'], 10))
	{
		alertMessage += "\n First Run Date Gap In Months length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInDays'], 10))
	{
		alertMessage += "\n First Run Date Gap In Days length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInHours'], 10))
	{
		alertMessage += "\n First Run Date Gap In Hours length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInMinutes'], 10))
	{
		alertMessage += "\n First Run Date Gap In Minutes length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(taskInfoDataObject['firstRunDateGapInSeconds'], 10))
	{
		alertMessage += "\n First Run Date Gap In Seconds length is more than allowed(10)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskInfoManadatoryValidation(taskInfoDataObject, paramsMap)
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
		
	var taskName = taskInfoDataObject['taskName'];
	if(!taskName || taskName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_taskName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Task Name";
		    validationPassed = false;
		}
	}
	
	var taskType = taskInfoDataObject['taskType'];
	if(!taskType || taskType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_taskType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Task Type";
		    validationPassed = false;
		}
	}
	
	var isActive = taskInfoDataObject['isActive'];
	if(!isActive || isActive.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_isActive").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Is Active";
		    validationPassed = false;
		}
	}
	
	var taskFrequency = taskInfoDataObject['taskFrequency'];
	if(!taskFrequency || taskFrequency.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_taskFrequency").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Task Frequency";
		    validationPassed = false;
		}
	}
	
	var taskFrequencyUnit = taskInfoDataObject['taskFrequencyUnit'];
	if(!taskFrequencyUnit || taskFrequencyUnit.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_taskFrequencyUnit").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Task Frequency Unit";
		    validationPassed = false;
		}
	}
	
	var isRecurring = taskInfoDataObject['isRecurring'];
	if(!isRecurring || isRecurring.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_isRecurring").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Is Recurring";
		    validationPassed = false;
		}
	}
	
	var firstRunType = taskInfoDataObject['firstRunType'];
	if(!firstRunType || firstRunType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"TaskInfo_firstRunType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter First Run Type";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doTaskInfoValidation(taskInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doTaskInfoLengthValidation(taskInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doTaskInfoManadatoryValidation(taskInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
