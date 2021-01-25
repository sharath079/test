/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'EmailAttachmentLayoutName' : 'FormWBEntity:EmailAttachmentLayout_emailAttachmentLayoutName' 
 *	 * 'Comments' : 'FormWBEntity:EmailAttachmentLayout_comments' 
 *	
 */
var gInitParamsObjForEmailAttachmentLayout = {};
var gEmailAttachmentLayoutRequestUnderProcess = 0;
function selectThisEmailAttachmentLayoutForEdit(emailAttachmentLayoutRowElement)
{
    var emailAttachmentLayoutDataObject  = $(emailAttachmentLayoutRowElement).data('emailAttachmentLayout');
    var emailAttachmentLayoutList = $('[data-name="emailAttachmentLayoutSearchResults"]').find('[data-name="emailAttachmentLayoutRow"]');
	emailAttachmentLayoutList.data("is-selected", 0);	
	$(emailAttachmentLayoutRowElement).data("is-selected", 1);
	setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject);
}

function setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject, paramsMap)
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
	var emailAttachmentLayoutId = emailAttachmentLayoutDataObject['emailAttachmentLayoutId'];
	$('#'+prefix+'idValueForEmailAttachmentLayout').val(emailAttachmentLayoutId);
		
	//Input
	if(emailAttachmentLayoutDataObject.hasOwnProperty('emailAttachmentLayoutName'))
	{
		var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
		$('#'+prefix+'EmailAttachmentLayout_emailAttachmentLayoutName').val(emailAttachmentLayoutName);
	}
	
	//Text
	if(emailAttachmentLayoutDataObject.hasOwnProperty('comments'))
	{
		var comments = emailAttachmentLayoutDataObject['comments'];            		
		$('#'+prefix+'EmailAttachmentLayout_comments').val(comments);
	}

	if(emailAttachmentLayoutDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = emailAttachmentLayoutDataObject['vwTxnStatus'];
		$('[data-name="emailAttachmentLayoutActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnEmailAttachmentLayout(this)" >' +emailAttachmentLayoutDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="emailAttachmentLayoutActionButtonDiv"]').append(buttonElement);
		$('[data-name="emailAttachmentLayoutActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="emailAttachmentLayoutActionButtonDiv"]').hide();
	}
	$('[data-name="emailAttachmentLayoutCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="emailAttachmentLayoutViewFormButtonsDiv"]').css("display", "inline");
	//loadEmailAttachmentLayoutViewData(emailAttachmentLayoutDataObject);
	disbaleEmailAttachmentLayoutUpdateDisallowedFields(paramsMap);
	doAfterEmailAttachmentLayoutPanelRefreshed();
    
    
}
function disbaleEmailAttachmentLayoutUpdateDisallowedFields(paramsMap)
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
function loadEmailAttachmentLayoutViewData(emailAttachmentLayoutDataObject, paramsMap)
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
	var emailAttachmentLayoutId = emailAttachmentLayoutDataObject['emailAttachmentLayoutId'];
	$('#'+prefix+'idValueForEmailAttachmentLayout').val(emailAttachmentLayoutId);
		
	if(emailAttachmentLayoutDataObject.hasOwnProperty('emailAttachmentLayoutName'))
	{
		var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
		parentElement.find('[data-name="'+prefix+'EmailAttachmentLayout_emailAttachmentLayoutName"]').text(emailAttachmentLayoutName);
	}
	
	if(emailAttachmentLayoutDataObject.hasOwnProperty('comments'))
	{
		var comments = emailAttachmentLayoutDataObject['comments'];            		
		parentElement.find('[data-name="'+prefix+'EmailAttachmentLayout_comments"]').text(comments);
	}

}
function ajaxDemoForEmailAttachmentLayout()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForEmailAttachmentLayout').textContent;	
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
			refreshPanelForEmailAttachmentLayout();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForEmailAttachmentLayout(msg)
{
	var executeCustomAPIButtonForEmailAttachmentLayout = document.getElementById("FormWBEntity:executeCustomAPIButtonForEmailAttachmentLayout");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:EmailAttachmentLayout_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForEmailAttachmentLayout.click();
}
function refreshPanelForEmailAttachmentLayout()
{
	var demoRefreshButtonForEmailAttachmentLayout = document.getElementById("FormWBEntity:demoRefreshButtonEmailAttachmentLayout");
	demoRefreshButtonForEmailAttachmentLayout.click();
}
function initializePanelOnLoadForCreateEmailAttachmentLayout(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForEmailAttachmentLayout = initParamsObj;
	initializeEmailAttachmentLayoutPage();	
	doAfterEmailAttachmentLayoutPanelRefreshed();
	initializeEmailAttachmentLayoutLookupFields(initParamsObj);
	doAfterPanelInitializedForEmailAttachmentLayoutExt();
	fetchEmailAttachmentLayoutDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="emailAttachmentLayoutCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForEmailAttachmentLayout = 0;
function initializePanelOnLoadForSearchEmailAttachmentLayout()
{
	initializeEmailAttachmentLayoutPage();	
	initializeEmailAttachmentLayoutLookupFields();
	doAfterPanelInitializedForEmailAttachmentLayoutExt();
	gLoadDashboardResultsForEmailAttachmentLayout =1;
	//retrieveEmailAttachmentLayoutList();
}
function initializePanelOnLoadForViewEmailAttachmentLayout(urlParams)
{
	initializeEmailAttachmentLayoutPage();	
	doAfterEmailAttachmentLayoutPanelRefreshed();
	initializeEmailAttachmentLayoutLookupFields(urlParams);
	doAfterPanelInitializedForEmailAttachmentLayoutExt();
	retrieveEmailAttachmentLayout(urlParams);
	initDatePicker();
	$('[data-name="emailAttachmentLayoutViewFormButtonsDiv"]').css("display", "inline");
}
function initializeEmailAttachmentLayoutPage()
{
	initializePageOnload();	
	//initializeEmailAttachmentLayoutTemplateVariables();
}
function initializeEmailAttachmentLayoutTemplateVariables()
{	
	
	
	var emailAttachmentLayoutRowObj = $('[data-name="emailAttachmentLayoutList"]').find('[data-name="emailAttachmentLayoutRow"]');
	if(emailAttachmentLayoutRowObj.length > 0 && gEmailAttachmentLayoutSearchResultsTableRowTemplate.length == 0)
	{
		gEmailAttachmentLayoutSearchResultsTableRowTemplate = emailAttachmentLayoutRowObj[0].outerHTML;
		//emailAttachmentLayoutRowObj.remove();
	}
	if(gEmailAttachmentLayoutSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="emailAttachmentLayoutSearchResults"]').find('[data-name="emailAttachmentLayoutRow"]');
		if(searchResultsRowObj.length > 0 && gEmailAttachmentLayoutSearchResultsTableRowTemplate.length == 0)
		{
			gEmailAttachmentLayoutSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrieveEmailAttachmentLayout(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var emailAttachmentLayoutId = paramsMap['emailAttachmentLayoutId'];
	var emailAttachmentLayout = {};
	emailAttachmentLayout['emailAttachmentLayoutId'] = emailAttachmentLayoutId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				emailAttachmentLayout[key] = paramsMap[key];
			}
	}
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
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
                	var emailAttachmentLayoutDataObject = responseData['emailAttachmentLayoutDataObject'];
    				setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForEmailAttachmentLayout()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchEmailAttachmentLayoutDefaultData();
}
function fetchEmailAttachmentLayoutDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'EmailAttachmentLayout', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
            	document.getElementById('idValueForEmailAttachmentLayout').value = '';
			    
            	setEmailAttachmentLayoutData(emailAttachmentLayout);
            }
        }
    });	
}
function fetchEmailAttachmentLayoutTestData() 
{
	var emailAttachmentLayout = getDataForEmailAttachmentLayout();
    var searchJsonData = {'objectType' : 'EmailAttachmentLayout', 'paramsInfo' : JSON.stringify(emailAttachmentLayout)};
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
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
            	document.getElementById('idValueForEmailAttachmentLayout').value = '';
			    
            	setEmailAttachmentLayoutData(emailAttachmentLayout);
            }
        }
    });	
}
function setEmailAttachmentLayoutData(emailAttachmentLayoutDataObject)
{
	var prefix = "";
		
	//Input
	if(emailAttachmentLayoutDataObject.hasOwnProperty('emailAttachmentLayoutName'))
	{
		var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
		$('#'+prefix+'EmailAttachmentLayout_emailAttachmentLayoutName').val(emailAttachmentLayoutName);
	}
	
	//Text
	if(emailAttachmentLayoutDataObject.hasOwnProperty('comments'))
	{
		var comments = emailAttachmentLayoutDataObject['comments'];            		
		$('#'+prefix+'EmailAttachmentLayout_comments').val(comments);
	}

	$('[data-name="emailAttachmentLayoutActionButtonDiv"]').hide();
}
function initializeEmailAttachmentLayoutLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="emailAttachmentLayoutSearchResultsDiv"]');
	
        searchDiv.find('[data-name="taskInfoDBId"]').data("taskInfo-id", -1);

}

function initializeEmailAttachmentLayoutLookupSelectList(paramsMap)
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
function doAfterEmailAttachmentLayoutPanelRefreshed()
{
    //doAfterPanelRefreshedForEmailAttachmentLayoutExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForEmailAttachmentLayout(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForEmailAttachmentLayoutExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForEmailAttachmentLayout(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForEmailAttachmentLayoutExt(fieldName)
}
function getEntityIdForEmailAttachmentLayout()
{
	var idValue = document.getElementById('FormWBEntity:idValueForEmailAttachmentLayout').textContent;	
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
function openPrintPageForEmailAttachmentLayout()
{
	var entityId = getEntityIdForEmailAttachmentLayout();
	if(entityId>0)
	{
	    window.open("/reports/generated/EmailAttachmentLayout.jsp?emailAttachmentLayoutId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForEmailAttachmentLayout(paramsMap)
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
	var emailAttachmentLayout = {};
		
	//Input
	if($("#"+prefix+"EmailAttachmentLayout_emailAttachmentLayoutName").length == 1)
	{
		var emailAttachmentLayoutName = $('#'+prefix+'EmailAttachmentLayout_emailAttachmentLayoutName').val();
		emailAttachmentLayout['emailAttachmentLayoutName'] = emailAttachmentLayoutName;
	}
	
	//Text
	if($("#"+prefix+"EmailAttachmentLayout_comments").length == 1)
	{
		var comments = $('#'+prefix+'EmailAttachmentLayout_comments').val();
		emailAttachmentLayout['comments'] = comments;
	}

	
	
	if($("#"+prefix+"idValueForTaskInfo").length == 1)
	{
		var taskInfoId = $("#"+prefix+"idValueForTaskInfo").val();
		emailAttachmentLayout['taskInfoId'] = taskInfoId; 
	}
	
	return emailAttachmentLayout;
}
function createEmailAttachmentLayout(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var emailAttachmentLayout = getDataForEmailAttachmentLayout(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				emailAttachmentLayout[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForEmailAttachmentLayout)
	{
		paramsInfo[key] = gInitParamsObjForEmailAttachmentLayout[key];
	}
	var validationObject = doEmailAttachmentLayoutValidation(emailAttachmentLayout, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	emailAttachmentLayout['additionalParamsInfo'] = paramsInfo;
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
	var urlContextPath = "";//getContextPath();
	if(gEmailAttachmentLayoutRequestUnderProcess == 1)
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
            	gEmailAttachmentLayoutRequestUnderProcess = 0;
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gEmailAttachmentLayoutRequestUnderProcess = 0;
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
            	var emailAttachmentLayoutId = responseData['emailAttachmentLayoutId'];
            	var popupElement = $('[data-name="EmailAttachmentLayoutPopup"]');
            	
				
            	var emailAttachmentLayoutDataObject = responseData['emailAttachmentLayoutDataObject'];
				setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject);
				retrieveDependentEmailAttachmentLayoutList();
            }
        }
    });
}
function resetTableForEmailAttachmentLayout()
{
	var emailAttachmentLayoutListElement = $("[data-name='emailAttachmentLayoutList']");
	var previousDataRowList  = emailAttachmentLayoutListElement.find('[data-name="emailAttachmentLayoutRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForEmailAttachmentLayout(paramsMap)
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
	$('#'+prefix+'idValueForEmailAttachmentLayout').val('');
		
	//Input
	$('#'+prefix+'EmailAttachmentLayout_emailAttachmentLayoutName').val('');
	
	//Text
	$('#'+prefix+'EmailAttachmentLayout_comments').val('');

	$('[data-name="emailAttachmentLayoutCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="emailAttachmentLayoutViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="emailAttachmentLayoutActionButtonDiv"]').hide();
	enableEmailAttachmentLayoutUpdateDisallowedFields(paramsMap);
    
}
function enableEmailAttachmentLayoutUpdateDisallowedFields(paramsMap)
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
function updateEmailAttachmentLayout(paramsMap)
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
	var emailAttachmentLayout = getDataForEmailAttachmentLayout(paramsMap)
	if($("#"+prefix+"idValueForEmailAttachmentLayout").length == 1)
	{
		var emailAttachmentLayoutId = $("#"+prefix+"idValueForEmailAttachmentLayout").val();
		emailAttachmentLayout['emailAttachmentLayoutId'] = emailAttachmentLayoutId;
	}
	var validationObject = doEmailAttachmentLayoutValidation(emailAttachmentLayout, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
	var urlContextPath = "";//getContextPath();
	if(gEmailAttachmentLayoutRequestUnderProcess == 1)
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
                    	gEmailAttachmentLayoutRequestUnderProcess = 0;
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gEmailAttachmentLayoutRequestUnderProcess = 0;
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
				
				retrieveDependentEmailAttachmentLayoutList();
            }
        }
    });
}
function deleteEmailAttachmentLayout(paramsMap)
{		
	var emailAttachmentLayoutId = document.getElementById('idValueForEmailAttachmentLayout').value;
	deleteSelectedEmailAttachmentLayout(emailAttachmentLayoutId, paramsMap);
}
function deleteSelectedEmailAttachmentLayout(emailAttachmentLayoutId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var emailAttachmentLayout = {};
	emailAttachmentLayout['emailAttachmentLayoutId'] = emailAttachmentLayoutId;	
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
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
function loadSearchResultsForEmailAttachmentLayout()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'EmailAttachmentLayout'};
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
            	var emailAttachmentLayoutSearchResultsElement = $("[data-name='emailAttachmentLayoutSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var emailAttachmentLayoutDataObject = searchResultObjectsList[i];
					            		var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
            		var comments = emailAttachmentLayoutDataObject['comments'];            		

            		var resultRowTemnplate = $(gEmailAttachmentLayoutSearchResultsTableRowTemplate);
					            		var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
            	    resultRowTemnplate.find("[data-name='emailAttachmentLayoutName']").text(emailAttachmentLayoutName);
            		var comments = emailAttachmentLayoutDataObject['comments'];            		
            	    resultRowTemnplate.find("[data-name='comments']").text(comments);

            	    resultRowTemnplate.data('emailAttachmentLayout', emailAttachmentLayoutDataObject);
            	    emailAttachmentLayoutSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gEmailAttachmentLayoutSearchResultsTableRowTemplate = ""; 
function openViewPageForEmailAttachmentLayoutForEdit(emailAttachmentLayoutLinkElement)
{
	var emailAttachmentLayoutRowElement = $(emailAttachmentLayoutLinkElement).parents('[data-name="emailAttachmentLayoutRow"]');
    var emailAttachmentLayoutDataObject  = emailAttachmentLayoutRowElement.data('emailAttachmentLayout');
	var emailAttachmentLayoutId = emailAttachmentLayoutDataObject['emailAttachmentLayoutId'];
	var emailAttachmentLayoutPopupElement = $('[data-name="EmailAttachmentLayoutPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject);
	    $("#EmailAttachmentLayout-tab").trigger("click");
	}
	else if(emailAttachmentLayoutPopupElement.length > 0)
	{
	    setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject);
		$('[data-name="EmailAttachmentLayoutPopup"]').find('[data-name="emailAttachmentLayoutCreateFormButtonsDiv"]').hide();
		$('[data-name="EmailAttachmentLayoutPopup"]').find('[data-name="emailAttachmentLayoutViewFormButtonsDiv"]').show();
	    showPopup('EmailAttachmentLayoutPopup', emailAttachmentLayoutLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewEmailAttachmentLayout.html?emailAttachmentLayoutId="+emailAttachmentLayoutId;
		window.open(viewLink, "_blank"); 	
	}
}
function openEmailAttachmentLayoutCreatePageForNew(linkElement)
{
	var emailAttachmentLayoutPopupElement = $('[data-name="EmailAttachmentLayoutPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForEmailAttachmentLayout();
	    $("#EmailAttachmentLayout-tab").trigger("click");
    }
	else if(emailAttachmentLayoutPopupElement.length > 0)
	{
		resetFormForEmailAttachmentLayout();
	    showPopup('EmailAttachmentLayoutPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateEmailAttachmentLayout.html";
    }
}
function showEmailAttachmentLayoutPopupForEdit(emailAttachmentLayoutLinkElement)
{
	var emailAttachmentLayoutRowElement = $(emailAttachmentLayoutLinkElement).parents('[data-name="emailAttachmentLayoutRow"]');
    var emailAttachmentLayoutDataObject  = emailAttachmentLayoutRowElement.data('emailAttachmentLayout');
    setEmailAttachmentLayoutInViewPage(emailAttachmentLayoutDataObject);
    showPopup('EmailAttachmentLayoutPopup', emailAttachmentLayoutLinkElement, 1);
    $("#EmailAttachmentLayout-tab").trigger("click");
}
function showEmailAttachmentLayoutPopupForNew(buttonElement)
{
	resetFormForEmailAttachmentLayout();
    showPopup('EmailAttachmentLayoutPopup', buttonElement, 1);
    $("#EmailAttachmentLayout-tab").trigger("click");
}
function deleteThisEmailAttachmentLayout(emailAttachmentLayoutLinkElement, paramsMap)
{
	var emailAttachmentLayoutRowElement = $(emailAttachmentLayoutLinkElement).parents('[data-name="emailAttachmentLayoutRow"]');
    var emailAttachmentLayoutDataObject  = emailAttachmentLayoutRowElement.data('emailAttachmentLayout');
	var emailAttachmentLayoutId = emailAttachmentLayoutDataObject['emailAttachmentLayoutId'];
	deleteSelectedEmailAttachmentLayout(emailAttachmentLayoutId, paramsMap);
}
function displayEmailAttachmentLayoutList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + emailAttachmentLayoutSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var emailAttachmentLayoutSearchResults = searchResultsDiv.find('[data-name="emailAttachmentLayoutSearchResults"]');
	//emailAttachmentLayoutSearchResults.find('[data-name="emailAttachmentLayoutRow"]').remove();
	var previousDataRowList  = emailAttachmentLayoutSearchResults.find('[data-name="emailAttachmentLayoutRow"]');
	emailAttachmentLayoutSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = emailAttachmentLayoutSearchResults.find('[data-name="emailAttachmentLayoutRow"]');
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
        var emailAttachmentLayoutDataObject = searchResultObjectsList[i];
        loadEmailAttachmentLayoutViewData(emailAttachmentLayoutDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var taskInfoDisplayVal = emailAttachmentLayoutDataObject['taskInfoDisplayVal'];            		
	    resultRowTemplate.find("[data-name='taskInfoDisplayVal']").text(taskInfoDisplayVal);

		var vwTxnStatus = emailAttachmentLayoutDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='emailAttachmentLayoutVwTxnStatus']").text(vwTxnStatus);
		if(emailAttachmentLayoutDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="emailAttachmentLayoutActionButton"]').text(emailAttachmentLayoutDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="emailAttachmentLayoutActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="emailAttachmentLayoutActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="emailAttachmentLayoutRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="emailAttachmentLayoutRejectButton"]').show();
		}
	    resultRowTemplate.data('emailAttachmentLayout', emailAttachmentLayoutDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    emailAttachmentLayoutSearchResults.append(resultRowTemplate);
	    processResultRowForEmailAttachmentLayoutExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForEmailAttachmentLayout == 1)
	{
    	getDashboardResultsForEmailAttachmentLayout();
	}
}
var emailAttachmentLayoutSearchResultsDivName = "emailAttachmentLayoutSearchResultsDiv";
var gEmailAttachmentLayoutSearchInputParamsList = [];
function retrieveEmailAttachmentLayoutList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + emailAttachmentLayoutSearchResultsDivName + '"]');
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
    fetchEmailAttachmentLayoutSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchEmailAttachmentLayoutSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getEmailAttachmentLayoutSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = emailAttachmentLayoutSearchResultsDivName; 
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
        'objectType': "EmailAttachmentLayout"
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
            	this.showPreviousRecords = "showPreviousEmailAttachmentLayoutRecords";
            	this.showCurrentPageRecords = "showCurrentPageEmailAttachmentLayoutRecords";
            	this.showNextRecords = "showNextEmailAttachmentLayoutRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchEmailAttachmentLayoutResults";
                var emailAttachmentLayoutList = responseData['emailAttachmentLayoutList'];
                var currContextObj = this; 
                var successCallbackFunction = displayEmailAttachmentLayoutList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'emailAttachmentLayoutList', 'matchingSearchResultsCount', this.searchResultsDivName, 'emailAttachmentLayoutSearchResults', 'emailAttachmentLayoutRow', setEmailAttachmentLayoutSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getEmailAttachmentLayoutSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + emailAttachmentLayoutSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="emailAttachmentLayoutDB_emailAttachmentLayoutName"]').length == 1)
		{
		    var emailAttachmentLayoutName = searchDiv.find('[data-name="emailAttachmentLayoutDB_emailAttachmentLayoutName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'emailAttachmentLayoutName', 'userInputValue':emailAttachmentLayoutName});
		}
		
		//Text
		if(searchDiv.find('[data-name="emailAttachmentLayoutDB_comments"]').length == 1)
		{
		    var comments = searchDiv.find('[data-name="emailAttachmentLayoutDB_comments"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'comments', 'userInputValue':comments});
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
        gEmailAttachmentLayoutSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gEmailAttachmentLayoutSearchInputParamsList;
    }
    return searchInputParams;
}
function setEmailAttachmentLayoutSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + emailAttachmentLayoutSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gEmailAttachmentLayoutSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gEmailAttachmentLayoutSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageEmailAttachmentLayoutRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = emailAttachmentLayoutSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchEmailAttachmentLayoutSearchResultsList);
}
function showPreviousEmailAttachmentLayoutRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = emailAttachmentLayoutSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchEmailAttachmentLayoutSearchResultsList);
}
function showNextEmailAttachmentLayoutRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = emailAttachmentLayoutSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchEmailAttachmentLayoutSearchResultsList);
}
function showPrevOrNextSearchEmailAttachmentLayoutResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousEmailAttachmentLayoutRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextEmailAttachmentLayoutRecords(e);
    }
}

var gTaskInfo_EmailAttachmentLayoutSearchResultsTableRowTemplate =""; 
function initializeTaskInfoPopup_TaskInfo_EmailAttachmentLayoutLookupFields() 
{	
    var searchDiv = $('[data-name="TaskInfo_EmailAttachmentLayoutSearchDiv"]');
	
    
	if(gTaskInfo_EmailAttachmentLayoutSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="TaskInfo_EmailAttachmentLayoutSearchResults"]').find('[data-name="TaskInfo_EmailAttachmentLayoutRow"]');
		gTaskInfo_EmailAttachmentLayoutSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayTaskInfo_EmailAttachmentLayoutList(searchResultObjectsList, parentElement)
{
    var taskInfoSearchResults = $('[data-name="TaskInfo_EmailAttachmentLayoutSearchResults"]');
	taskInfoSearchResults.find('[data-name="TaskInfo_EmailAttachmentLayoutRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gTaskInfo_EmailAttachmentLayoutSearchResultsTableRowTemplate);
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
var TaskInfo_EmailAttachmentLayoutSearchResultsDivName = "TaskInfo_EmailAttachmentLayoutSearchResultsDiv";
var gTaskInfo_EmailAttachmentLayoutSearchInputParamsList = [];
function getTaskInfo_EmailAttachmentLayoutSearchResults()
{
    var searchDiv = $('[data-name="TaskInfo_EmailAttachmentLayoutSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + TaskInfo_EmailAttachmentLayoutSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchTaskInfo_EmailAttachmentLayoutSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchTaskInfo_EmailAttachmentLayoutSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getTaskInfo_EmailAttachmentLayoutSearchInputParams();
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
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_EmailAttachmentLayoutRecords";
            	this.showPreviousRecords = "showPreviousTaskInfo_EmailAttachmentLayoutRecords";
            	this.showCurrentPageRecords = "showCurrentPageTaskInfo_EmailAttachmentLayoutRecords";
            	this.showNextRecords = "showNextTaskInfo_EmailAttachmentLayoutRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchTaskInfo_EmailAttachmentLayoutResults";
                var taskInfoList = responseData['taskInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'taskInfoList', 'matchingSearchResultsCount', TaskInfo_EmailAttachmentLayoutSearchResultsDivName, 'TaskInfo_EmailAttachmentLayoutSearchResults', 'TaskInfo_EmailAttachmentLayoutRow', setTaskInfo_EmailAttachmentLayoutSearchInputParams, displayTaskInfo_EmailAttachmentLayoutList);
            }
        }
    });
}
function getTaskInfo_EmailAttachmentLayoutSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_EmailAttachmentLayoutSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="TaskInfo_EmailAttachmentLayoutSearchDiv"]');
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

	    
        gTaskInfo_EmailAttachmentLayoutSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gTaskInfo_EmailAttachmentLayoutSearchInputParamsList;
    }
    return searchInputParams;
}
function setTaskInfo_EmailAttachmentLayoutSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + TaskInfo_EmailAttachmentLayoutSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gTaskInfo_EmailAttachmentLayoutSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gTaskInfo_EmailAttachmentLayoutSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageTaskInfo_EmailAttachmentLayoutRecords(e)
{
    getCurrentPageSearchResults(e, TaskInfo_EmailAttachmentLayoutSearchResultsDivName, fetchTaskInfo_EmailAttachmentLayoutSearchResultsList);
}
function showPreviousTaskInfo_EmailAttachmentLayoutRecords()
{
    getPreviousPageSearchResults(TaskInfo_EmailAttachmentLayoutSearchResultsDivName, fetchTaskInfo_EmailAttachmentLayoutSearchResultsList);
}
function showNextTaskInfo_EmailAttachmentLayoutRecords()
{
    getNextPageSearchResults(TaskInfo_EmailAttachmentLayoutSearchResultsDivName, fetchTaskInfo_EmailAttachmentLayoutSearchResultsList);
}
function showPrevOrNextSearchTaskInfo_EmailAttachmentLayoutResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousTaskInfo_EmailAttachmentLayoutRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextTaskInfo_EmailAttachmentLayoutRecords();
    }
}
function setTaskInfo_EmailAttachmentLayout(taskInfoRowElement) 
{
    var taskInfoDataObject  = $(taskInfoRowElement).data('taskInfo');
	var taskInfoId = taskInfoDataObject['taskInfoId'];
	var parentElement = $(taskInfoRowElement).parents('[data-name="TaskInfoPopup_TaskInfo_EmailAttachmentLayout"]');
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
		var entityName = "TaskInfo_EmailAttachmentLayout";
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
function lookupRowSelectedForEmailAttachmentLayout(attributeName, attributeId)
{
	var emailAttachmentLayout = getDataForEmailAttachmentLayout();
	emailAttachmentLayout['attributeName'] = attributeName;
	emailAttachmentLayout['attributeId'] = attributeId;
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
            	setEmailAttachmentLayoutData(emailAttachmentLayout);
            }
        }
    });	
}
function selectOptionChangedForEmailAttachmentLayout(attributeName)
{
	var emailAttachmentLayout = getDataForEmailAttachmentLayout();
	emailAttachmentLayout['attributeName'] = attributeName;
    var emailAttachmentLayoutJsonData = {'paramsInfo': JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
            	setEmailAttachmentLayoutData(emailAttachmentLayout);
            	doAfterEmailAttachmentLayoutPanelRefreshed();
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
function retrieveDependentEmailAttachmentLayoutList(paramsMap)
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
    var searchJsonData = {'objectType' : 'EmailAttachmentLayout', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var emailAttachmentLayoutList = responseData['emailAttachmentLayoutList'];
            	var emailAttachmentLayoutListElement = $("[data-name='emailAttachmentLayoutList']");
            	var previousDataRowList  = emailAttachmentLayoutListElement.find('[data-name="emailAttachmentLayoutRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = emailAttachmentLayoutListElement.find('[data-name="emailAttachmentLayoutRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//emailAttachmentLayoutListElement.empty();
            	for(var i=0; i<emailAttachmentLayoutList.length; i++)
        		{
            		var emailAttachmentLayoutDataObject = emailAttachmentLayoutList[i];
            		//var resultRowTemplate = $(gEmailAttachmentLayoutSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var emailAttachmentLayoutName = emailAttachmentLayoutDataObject['emailAttachmentLayoutName'];            		
				    resultRowTemplate.find("[data-name='emailAttachmentLayoutName']").text(emailAttachmentLayoutName);
					var comments = emailAttachmentLayoutDataObject['comments'];            		
				    resultRowTemplate.find("[data-name='comments']").text(comments);

					
					
            	    resultRowTemplate.data('emailAttachmentLayout', emailAttachmentLayoutDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    emailAttachmentLayoutListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForEmailAttachmentLayout(customEventName)
{
	var emailAttachmentLayoutId = document.getElementById('idValueForEmailAttachmentLayout').value;
	var emailAttachmentLayout = {'emailAttachmentLayoutId':emailAttachmentLayoutId, 'customEventName':customEventName};
    var emailAttachmentLayoutJsonData = {'paramsInfo':JSON.stringify(emailAttachmentLayout), 'objectType' : 'EmailAttachmentLayout'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForEmailAttachmentLayoutExt(customEventName);
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
        		setEmailAttachmentLayoutInViewPage(emailAttachmentLayout);
            }
    		doAfterExecuteCustomAPIForEmailAttachmentLayoutExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnEmailAttachmentLayout(e, paramsMap)
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
	var emailAttachmentLayoutId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var emailAttachmentLayoutDataObject  = rowObj.data('emailAttachmentLayout');
    	emailAttachmentLayoutId = emailAttachmentLayoutDataObject['emailAttachmentLayoutId'];
	}
    else
	{
    	emailAttachmentLayoutId = document.getElementById('idValueForEmailAttachmentLayout').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var emailAttachmentLayoutSearchParams = {'emailAttachmentLayoutId':emailAttachmentLayoutId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	emailAttachmentLayoutSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var emailAttachmentLayoutJsonData = {'paramsInfo':JSON.stringify(emailAttachmentLayoutSearchParams),  'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var emailAttachmentLayout = responseData['emailAttachmentLayout'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="emailAttachmentLayoutRowActionButtonDiv"]').hide();
            		if(emailAttachmentLayout.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = emailAttachmentLayout['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(emailAttachmentLayout['vwTxnStatus']);
            			selectedRowObj.find('[data-name="emailAttachmentLayoutActionButton"]').text(emailAttachmentLayout["nextAction"]);
            			selectedRowObj.find('[data-name="emailAttachmentLayoutActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setEmailAttachmentLayoutInViewPage(emailAttachmentLayout);
    			}
            }
        }
    });	
}
function downloadEmailAttachmentLayoutData()
{		
	var emailAttachmentLayout = {};
    var emailAttachmentLayoutJsonData = {'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=EmailAttachmentLayout");
            }
        }
    });
}
function uploadEmailAttachmentLayoutData(fileInfo)
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
	var emailAttachmentLayout = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var emailAttachmentLayoutJsonData = {'paramsInfo':JSON.stringify(emailAttachmentLayout),  'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=EmailAttachmentLayout");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForEmailAttachmentLayout()
{
    var emailAttachmentLayoutJsonData = {'objectType' : 'EmailAttachmentLayout'};
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
        data: emailAttachmentLayoutJsonData,
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



function doEmailAttachmentLayoutLengthValidation(emailAttachmentLayoutDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(emailAttachmentLayoutDataObject['emailAttachmentLayoutName'], 500))
	{
		alertMessage += "\n Email Attachment Layout Name length is more than allowed(500)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(emailAttachmentLayoutDataObject['comments'], 500))
	{
		alertMessage += "\n Comments length is more than allowed(500)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doEmailAttachmentLayoutManadatoryValidation(emailAttachmentLayoutDataObject, paramsMap)
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
	
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doEmailAttachmentLayoutValidation(emailAttachmentLayoutDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doEmailAttachmentLayoutLengthValidation(emailAttachmentLayoutDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doEmailAttachmentLayoutManadatoryValidation(emailAttachmentLayoutDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
