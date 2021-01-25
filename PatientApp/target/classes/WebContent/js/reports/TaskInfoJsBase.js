$(document).ready(function() {
	initializeReportInfo();
	initializePageLevelData();
});

var taskLayoutParametersDataTableRowTemplate = "";
taskLayoutParametersTbodyTag = "";

function initializePageLevelData()
{
	
	
	
	var taskLayoutParametersDiv = $('[data-name="TaskLayoutParameters"]');
	taskLayoutParametersTbodyTag = taskLayoutParametersDiv.find('tbody').first();
	taskLayoutParametersDataTableRowTemplate = taskLayoutParametersTbodyTag.find('tr').first()[0].outerHTML;
	
}
function getContextPath()
{
    return "";
}
function initializeReportInfo()
{
	initializePageOnload();
	var paramsMap = getQueryParams();
	
	initDatePicker();
	//initMonthDatePicker();
	//initYearDatePicker();
	
    getPageDataInfo();
}
function getPageDataInfo()
{
    var urlContextPath = getContextPath();
	var dataObj = getQueryParams();	
	dataObj.noOfRecordsAlreadyFetched = 0;
	dataObj.noOfRecordsToFetch = 1000000;
	dataObj.objectType = "TaskInfo";
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData) {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',        
        url : urlContextPath + '/getReportInfo' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: dataObj,
        success: function (responseData) {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                var fieldsDataWithoutOverrideWhereClause = responseData['fieldsDataWithoutOverrideWhereClause'];                
                   
                	var taskInfo = fieldsDataWithoutOverrideWhereClause['taskInfo'];                
		                
		                
		                var taskName = taskInfo['taskName'];
		                $('[data-name="TaskName"]').text(taskName);
		                
		                
		                var taskDescription = taskInfo['taskDescription'];
		                $('[data-name="TaskDescription"]').text(taskDescription);
		                
		                
		                var taskType = taskInfo['taskType'];
		                $('[data-name="TaskType"]').text(taskType);
		                
		                
		                var apiName = taskInfo['apiName'];
		                $('[data-name="ApiName"]').text(apiName);
		                
		                
		                var targetEntityQuery = taskInfo['targetEntityQuery'];
		                $('[data-name="TargetEntityQuery"]').text(targetEntityQuery);
		                
		                
		                var targetUserIdColumnAlias = taskInfo['targetUserIdColumnAlias'];
		                $('[data-name="TargetUserIdColumnAlias"]').text(targetUserIdColumnAlias);
		                
		                
		                var targetEntityIdColumnAlias = taskInfo['targetEntityIdColumnAlias'];
		                $('[data-name="TargetEntityIdColumnAlias"]').text(targetEntityIdColumnAlias);
		                
		                
		                var enableInAppNotification = taskInfo['enableInAppNotification'];
		                $('[data-name="EnableInAppNotification"]').text(enableInAppNotification);
		                
		                
		                var inAppNotificationLayout = taskInfo['inAppNotificationLayout'];
		                $('[data-name="InAppNotificationLayout"]').text(inAppNotificationLayout);
		                
		                
		                var enableEmailNotification = taskInfo['enableEmailNotification'];
		                $('[data-name="EnableEmailNotification"]').text(enableEmailNotification);
		                
		                
		                var emailColumnAlias = taskInfo['emailColumnAlias'];
		                $('[data-name="EmailColumnAlias"]').text(emailColumnAlias);
		                
		                
		                var emailNotificationLayout = taskInfo['emailNotificationLayout'];
		                $('[data-name="EmailNotificationLayout"]').text(emailNotificationLayout);
		                
		                
		                var emailSubject = taskInfo['emailSubject'];
		                $('[data-name="EmailSubject"]').text(emailSubject);
		                
		                
		                var enableSmsNotification = taskInfo['enableSmsNotification'];
		                $('[data-name="EnableSmsNotification"]').text(enableSmsNotification);
		                
		                
		                var smsColumnAlias = taskInfo['smsColumnAlias'];
		                $('[data-name="SmsColumnAlias"]').text(smsColumnAlias);
		                
		                
		                var smsNotificationLayout = taskInfo['smsNotificationLayout'];
		                $('[data-name="SmsNotificationLayout"]').text(smsNotificationLayout);
		                
		                
		                var sMSText = taskInfo['sMSText'];
		                $('[data-name="SMSText"]').text(sMSText);
		                
		                
		                var isActive = taskInfo['isActive'];
		                $('[data-name="IsActive"]').text(isActive);
		                
		                
		                var taskStartDate = taskInfo['taskStartDate'];
		                $('[data-name="TaskStartDate"]').text(taskStartDate);
		                
		                
		                var taskFrequency = taskInfo['taskFrequency'];
		                $('[data-name="TaskFrequency"]').text(taskFrequency);
		                
		                
		                var taskFrequencyUnit = taskInfo['taskFrequencyUnit'];
		                $('[data-name="TaskFrequencyUnit"]').text(taskFrequencyUnit);
		                
		                
		                var isRecurring = taskInfo['isRecurring'];
		                $('[data-name="IsRecurring"]').text(isRecurring);
		                
		                
		                var firstRunType = taskInfo['firstRunType'];
		                $('[data-name="FirstRunType"]').text(firstRunType);
		                
		                
		                var dateColumnAlias = taskInfo['dateColumnAlias'];
		                $('[data-name="DateColumnAlias"]').text(dateColumnAlias);
		                
		                
		                var firstRunDateCalculationMethod = taskInfo['firstRunDateCalculationMethod'];
		                $('[data-name="FirstRunDateCalculationMethod"]').text(firstRunDateCalculationMethod);
		                
		                
		                var firstRunDateGapInYears = taskInfo['firstRunDateGapInYears'];
		                $('[data-name="FirstRunDateGapInYears"]').text(firstRunDateGapInYears);
		                
		                
		                var firstRunDateGapInMonths = taskInfo['firstRunDateGapInMonths'];
		                $('[data-name="FirstRunDateGapInMonths"]').text(firstRunDateGapInMonths);
		                
		                
		                var firstRunDateGapInDays = taskInfo['firstRunDateGapInDays'];
		                $('[data-name="FirstRunDateGapInDays"]').text(firstRunDateGapInDays);
		                
		                
		                var firstRunDateGapInHours = taskInfo['firstRunDateGapInHours'];
		                $('[data-name="FirstRunDateGapInHours"]').text(firstRunDateGapInHours);
		                
		                
		                var firstRunDateGapInMinutes = taskInfo['firstRunDateGapInMinutes'];
		                $('[data-name="FirstRunDateGapInMinutes"]').text(firstRunDateGapInMinutes);
		                
		                
		                var firstRunDateGapInSeconds = taskInfo['firstRunDateGapInSeconds'];
		                $('[data-name="FirstRunDateGapInSeconds"]').text(firstRunDateGapInSeconds);
		                                
                
                var fieldsDataWithOverrideWhereClause = responseData['fieldsDataWithOverrideWhereClause'];                
	                                
			    var layoutCustomDataFieldsObject = responseData['layoutCustomDataFieldsObject'];               
                
                var tablesData = responseData['tablesData'];
                                  
                var taskLayoutParametersDataRowsList = tablesData['taskLayoutParametersDataRowsList'];                
                displayTaskLayoutParameters(taskLayoutParametersDataRowsList);
                
                var graphsData = responseData['graphsData'];
                
            }
        }
    });
}

 


function displayTaskLayoutParameters(taskLayoutParameters, parentElement)
{
	var paramsMap = "";
    var taskLayoutParametersDiv = $('[data-name="TaskLayoutParameters"]');
	if(!(typeof parentElement === 'string' || parentElement instanceof String || typeof parentElement === 'undefined'))
	{
	        taskLayoutParametersDiv = parentElement.find('[data-name="TaskLayoutParameters"]');
	        taskLayoutParametersTbodyTag = taskLayoutParametersDiv.find('tbody').first();
			taskLayoutParametersDataTableRowTemplate = taskLayoutParametersTbodyTag.find('tr').first()[0].outerHTML;	        	       	  
    }
    taskLayoutParametersTbodyTag.empty();
    for (var i = 0; i < taskLayoutParameters.length; i++)
    {
        var taskLayoutParametersObject = taskLayoutParameters[i];
          
        var parameterName = taskLayoutParametersObject['parameterName'];    	
          
        var parameterValueType = taskLayoutParametersObject['parameterValueType'];    	
          
        var parameterValue = taskLayoutParametersObject['parameterValue'];    	
                   
             
		         
        var lineItemsListRowObj = $(taskLayoutParametersDataTableRowTemplate); 
          
        	lineItemsListRowObj.find('[data-name="ParameterName"]').text(parameterName);
          
        	lineItemsListRowObj.find('[data-name="ParameterValueType"]').text(parameterValueType);
          
        	lineItemsListRowObj.find('[data-name="ParameterValue"]').text(parameterValue);
                   
         
    	lineItemsListRowObj[0].setAttribute('data-row-info', JSON.stringify(taskLayoutParametersObject));
        processQueryResultItemDataObject(taskLayoutParametersObject, "taskLayoutParameters", lineItemsListRowObj, parentElement);
            
    	taskLayoutParametersTbodyTag.append(lineItemsListRowObj);
     }
     processQueryResultList(taskLayoutParameters, "taskLayoutParameters");
}

function getParametersFromURLAndSearchFields()
{
	var paramsMap = getQueryParams();
	//Override URL parameters values With search field values if same parameter name exists
	var searchDiv = $('[data-name="BalanceSheetReportSearchDiv"]');
	var searchFieldsList = searchDiv.find('[data-is-search-field="1"]');
	for (var i = 0; i < searchFieldsList.length; i++)
	{
		var searchFieldDiv = $(searchFieldsList[i]);
		var parameterName = searchFieldDiv.data('parameter-name');
		var userInputValue = searchFieldDiv.find('[data-is-search-input-field ="1"]').val();
		if(paramsMap.hasOwnProperty(parameterName))
		{
			paramsMap.parameterName = userInputValue;
		}
		else
		{
			paramsMap[parameterName] = userInputValue;
		}
	}
	return paramsMap;	
}


