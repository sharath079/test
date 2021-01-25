/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'LoginUserType' : 'FormWBEntity:LoginSessionInfo_loginUserType' 
 *	 * 'SelfServiceUserType' : 'FormWBEntity:LoginSessionInfo_selfServiceUserType' 
 *	 * 'SessionId' : 'FormWBEntity:LoginSessionInfo_sessionId' 
 *	 * 'UserId' : 'FormWBEntity:LoginSessionInfo_userId' 
 *	 * 'LoginTime' : 'FormWBEntity:LoginSessionInfo_loginTime' 
 *	
 */
var gInitParamsObjForLoginSessionInfo = {};
var gLoginSessionInfoRequestUnderProcess = 0;
function selectThisLoginSessionInfoForEdit(loginSessionInfoRowElement)
{
    var loginSessionInfoDataObject  = $(loginSessionInfoRowElement).data('loginSessionInfo');
    var loginSessionInfoList = $('[data-name="loginSessionInfoSearchResults"]').find('[data-name="loginSessionInfoRow"]');
	loginSessionInfoList.data("is-selected", 0);	
	$(loginSessionInfoRowElement).data("is-selected", 1);
	setLoginSessionInfoInViewPage(loginSessionInfoDataObject);
}

function setLoginSessionInfoInViewPage(loginSessionInfoDataObject, paramsMap)
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
	var loginSessionInfoId = loginSessionInfoDataObject['loginSessionInfoId'];
	$('#'+prefix+'idValueForLoginSessionInfo').val(loginSessionInfoId);
		
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('loginUserType'))
	{
		var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
		$('#'+prefix+'LoginSessionInfo_loginUserType').val(loginUserType);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('selfServiceUserType'))
	{
		var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
		$('#'+prefix+'LoginSessionInfo_selfServiceUserType').val(selfServiceUserType);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('sessionId'))
	{
		var sessionId = loginSessionInfoDataObject['sessionId'];            		
		$('#'+prefix+'LoginSessionInfo_sessionId').val(sessionId);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('userId'))
	{
		var userId = loginSessionInfoDataObject['userId'];            		
		$('#'+prefix+'LoginSessionInfo_userId').val(userId);
	}
	
	//DateTime
	if(loginSessionInfoDataObject.hasOwnProperty('loginTime'))
	{
		var loginTime = loginSessionInfoDataObject['loginTime'];            		
		$('#'+prefix+'LoginSessionInfo_loginTime').val(loginTime);
	}

	if(loginSessionInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = loginSessionInfoDataObject['vwTxnStatus'];
		$('[data-name="loginSessionInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnLoginSessionInfo(this)" >' +loginSessionInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="loginSessionInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="loginSessionInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="loginSessionInfoActionButtonDiv"]').hide();
	}
	$('[data-name="loginSessionInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="loginSessionInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadLoginSessionInfoViewData(loginSessionInfoDataObject);
	disbaleLoginSessionInfoUpdateDisallowedFields(paramsMap);
	doAfterLoginSessionInfoPanelRefreshed();
    
    
}
function disbaleLoginSessionInfoUpdateDisallowedFields(paramsMap)
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
function loadLoginSessionInfoViewData(loginSessionInfoDataObject, paramsMap)
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
	var loginSessionInfoId = loginSessionInfoDataObject['loginSessionInfoId'];
	$('#'+prefix+'idValueForLoginSessionInfo').val(loginSessionInfoId);
		
	if(loginSessionInfoDataObject.hasOwnProperty('loginUserType'))
	{
		var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
		parentElement.find('[data-name="'+prefix+'LoginSessionInfo_loginUserType"]').text(loginUserType);
	}
	
	if(loginSessionInfoDataObject.hasOwnProperty('selfServiceUserType'))
	{
		var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
		parentElement.find('[data-name="'+prefix+'LoginSessionInfo_selfServiceUserType"]').text(selfServiceUserType);
	}
	
	if(loginSessionInfoDataObject.hasOwnProperty('sessionId'))
	{
		var sessionId = loginSessionInfoDataObject['sessionId'];            		
		parentElement.find('[data-name="'+prefix+'LoginSessionInfo_sessionId"]').text(sessionId);
	}
	
	if(loginSessionInfoDataObject.hasOwnProperty('userId'))
	{
		var userId = loginSessionInfoDataObject['userId'];            		
		parentElement.find('[data-name="'+prefix+'LoginSessionInfo_userId"]').text(userId);
	}
	
	var loginTime = loginSessionInfoDataObject['loginTime'];            		
	parentElement.find('[data-name="'+prefix+'LoginSessionInfo_loginTime"]').text(loginTime);

}
function ajaxDemoForLoginSessionInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForLoginSessionInfo').textContent;	
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
			refreshPanelForLoginSessionInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForLoginSessionInfo(msg)
{
	var executeCustomAPIButtonForLoginSessionInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForLoginSessionInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:LoginSessionInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForLoginSessionInfo.click();
}
function refreshPanelForLoginSessionInfo()
{
	var demoRefreshButtonForLoginSessionInfo = document.getElementById("FormWBEntity:demoRefreshButtonLoginSessionInfo");
	demoRefreshButtonForLoginSessionInfo.click();
}
function initializePanelOnLoadForCreateLoginSessionInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForLoginSessionInfo = initParamsObj;
	initializeLoginSessionInfoPage();	
	doAfterLoginSessionInfoPanelRefreshed();
	initializeLoginSessionInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForLoginSessionInfoExt();
	fetchLoginSessionInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="loginSessionInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForLoginSessionInfo = 0;
function initializePanelOnLoadForSearchLoginSessionInfo()
{
	initializeLoginSessionInfoPage();	
	initializeLoginSessionInfoLookupFields();
	doAfterPanelInitializedForLoginSessionInfoExt();
	gLoadDashboardResultsForLoginSessionInfo =1;
	//retrieveLoginSessionInfoList();
}
function initializePanelOnLoadForViewLoginSessionInfo(urlParams)
{
	initializeLoginSessionInfoPage();	
	doAfterLoginSessionInfoPanelRefreshed();
	initializeLoginSessionInfoLookupFields(urlParams);
	doAfterPanelInitializedForLoginSessionInfoExt();
	retrieveLoginSessionInfo(urlParams);
	initDatePicker();
	$('[data-name="loginSessionInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeLoginSessionInfoPage()
{
	initializePageOnload();	
	//initializeLoginSessionInfoTemplateVariables();
}
function initializeLoginSessionInfoTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="loginSessionInfoSearchResults"]').find('[data-name="loginSessionInfoRow"]');
	if(searchResultsRowObj.length > 0 && gLoginSessionInfoSearchResultsTableRowTemplate.length == 0)
	{
		gLoginSessionInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveLoginSessionInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var loginSessionInfoId = paramsMap['loginSessionInfoId'];
	var loginSessionInfo = {};
	loginSessionInfo['loginSessionInfoId'] = loginSessionInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				loginSessionInfo[key] = paramsMap[key];
			}
	}
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
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
                	var loginSessionInfoDataObject = responseData['loginSessionInfoDataObject'];
    				setLoginSessionInfoInViewPage(loginSessionInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForLoginSessionInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchLoginSessionInfoDefaultData();
}
function fetchLoginSessionInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'LoginSessionInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var loginSessionInfo = responseData['loginSessionInfo'];
            	document.getElementById('idValueForLoginSessionInfo').value = '';
			    
            	setLoginSessionInfoData(loginSessionInfo);
            }
        }
    });	
}
function fetchLoginSessionInfoTestData() 
{
	var loginSessionInfo = getDataForLoginSessionInfo();
    var searchJsonData = {'objectType' : 'LoginSessionInfo', 'paramsInfo' : JSON.stringify(loginSessionInfo)};
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
            	var loginSessionInfo = responseData['loginSessionInfo'];
            	document.getElementById('idValueForLoginSessionInfo').value = '';
			    
            	setLoginSessionInfoData(loginSessionInfo);
            }
        }
    });	
}
function setLoginSessionInfoData(loginSessionInfoDataObject)
{
	var prefix = "";
		
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('loginUserType'))
	{
		var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
		$('#'+prefix+'LoginSessionInfo_loginUserType').val(loginUserType);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('selfServiceUserType'))
	{
		var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
		$('#'+prefix+'LoginSessionInfo_selfServiceUserType').val(selfServiceUserType);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('sessionId'))
	{
		var sessionId = loginSessionInfoDataObject['sessionId'];            		
		$('#'+prefix+'LoginSessionInfo_sessionId').val(sessionId);
	}
	
	//Input
	if(loginSessionInfoDataObject.hasOwnProperty('userId'))
	{
		var userId = loginSessionInfoDataObject['userId'];            		
		$('#'+prefix+'LoginSessionInfo_userId').val(userId);
	}
	
	//DateTime
	if(loginSessionInfoDataObject.hasOwnProperty('loginTime'))
	{
		var loginTime = loginSessionInfoDataObject['loginTime'];            		
		$('#'+prefix+'LoginSessionInfo_loginTime').val(loginTime);
	}

	$('[data-name="loginSessionInfoActionButtonDiv"]').hide();
}
function initializeLoginSessionInfoLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="loginSessionInfoSearchResultsDiv"]');
	
    
}

function doAfterLoginSessionInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForLoginSessionInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForLoginSessionInfo(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForLoginSessionInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForLoginSessionInfo(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForLoginSessionInfoExt(fieldName)
}
function getEntityIdForLoginSessionInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForLoginSessionInfo').textContent;	
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
function openPrintPageForLoginSessionInfo()
{
	var entityId = getEntityIdForLoginSessionInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/LoginSessionInfo.jsp?loginSessionInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForLoginSessionInfo(paramsMap)
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
	var loginSessionInfo = {};
		
	//Input
	if($("#"+prefix+"LoginSessionInfo_loginUserType").length == 1)
	{
		var loginUserType = $('#'+prefix+'LoginSessionInfo_loginUserType').val();
		loginSessionInfo['loginUserType'] = loginUserType;
	}
	
	//Input
	if($("#"+prefix+"LoginSessionInfo_selfServiceUserType").length == 1)
	{
		var selfServiceUserType = $('#'+prefix+'LoginSessionInfo_selfServiceUserType').val();
		loginSessionInfo['selfServiceUserType'] = selfServiceUserType;
	}
	
	//Input
	if($("#"+prefix+"LoginSessionInfo_sessionId").length == 1)
	{
		var sessionId = $('#'+prefix+'LoginSessionInfo_sessionId').val();
		loginSessionInfo['sessionId'] = sessionId;
	}
	
	//Input
	if($("#"+prefix+"LoginSessionInfo_userId").length == 1)
	{
		var userId = $('#'+prefix+'LoginSessionInfo_userId').val();
		loginSessionInfo['userId'] = userId;
	}
	
	//Datetime
	if($("#"+prefix+"LoginSessionInfo_loginTime").length == 1)
	{
		var loginTime = $('#'+prefix+'LoginSessionInfo_loginTime').val();
		loginSessionInfo['loginTime'] = loginTime;
	}

	
	return loginSessionInfo;
}
function createLoginSessionInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var loginSessionInfo = getDataForLoginSessionInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				loginSessionInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForLoginSessionInfo)
	{
		paramsInfo[key] = gInitParamsObjForLoginSessionInfo[key];
	}
	var validationObject = doLoginSessionInfoValidation(loginSessionInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	loginSessionInfo['additionalParamsInfo'] = paramsInfo;
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
	var urlContextPath = "";//getContextPath();
	if(gLoginSessionInfoRequestUnderProcess == 1)
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
            	gLoginSessionInfoRequestUnderProcess = 0;
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gLoginSessionInfoRequestUnderProcess = 0;
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
            	var loginSessionInfoId = responseData['loginSessionInfoId'];
            	var popupElement = $('[data-name="LoginSessionInfoPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var loginSessionInfoDataObject = responseData['loginSessionInfoDataObject'];
    				setLoginSessionInfoInViewPage(loginSessionInfoDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewLoginSessionInfo.html?loginSessionInfoId="+loginSessionInfoId; 
        		}
				
            }
        }
    });
}
function resetTableForLoginSessionInfo()
{
	var loginSessionInfoListElement = $("[data-name='loginSessionInfoList']");
	var previousDataRowList  = loginSessionInfoListElement.find('[data-name="loginSessionInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForLoginSessionInfo(paramsMap)
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
	$('#'+prefix+'idValueForLoginSessionInfo').val('');
		
	//Input
	$('#'+prefix+'LoginSessionInfo_loginUserType').val('');
	
	//Input
	$('#'+prefix+'LoginSessionInfo_selfServiceUserType').val('');
	
	//Input
	$('#'+prefix+'LoginSessionInfo_sessionId').val('');
	
	//Input
	$('#'+prefix+'LoginSessionInfo_userId').val('');
	
	//DateTime
	$('#'+prefix+'LoginSessionInfo_loginTime').val('');

	$('[data-name="loginSessionInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="loginSessionInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="loginSessionInfoActionButtonDiv"]').hide();
	enableLoginSessionInfoUpdateDisallowedFields(paramsMap);
    
}
function enableLoginSessionInfoUpdateDisallowedFields(paramsMap)
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
function updateLoginSessionInfo(paramsMap)
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
	var loginSessionInfo = getDataForLoginSessionInfo(paramsMap)
	if($("#"+prefix+"idValueForLoginSessionInfo").length == 1)
	{
		var loginSessionInfoId = $("#"+prefix+"idValueForLoginSessionInfo").val();
		loginSessionInfo['loginSessionInfoId'] = loginSessionInfoId;
	}
	var validationObject = doLoginSessionInfoValidation(loginSessionInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
	var urlContextPath = "";//getContextPath();
	if(gLoginSessionInfoRequestUnderProcess == 1)
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
                    	gLoginSessionInfoRequestUnderProcess = 0;
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gLoginSessionInfoRequestUnderProcess = 0;
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
function deleteLoginSessionInfo(paramsMap)
{		
	var loginSessionInfoId = document.getElementById('idValueForLoginSessionInfo').value;
	deleteSelectedLoginSessionInfo(loginSessionInfoId, paramsMap);
}
function deleteSelectedLoginSessionInfo(loginSessionInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var loginSessionInfo = {};
	loginSessionInfo['loginSessionInfoId'] = loginSessionInfoId;	
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
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
function loadSearchResultsForLoginSessionInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'LoginSessionInfo'};
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
            	var loginSessionInfoSearchResultsElement = $("[data-name='loginSessionInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var loginSessionInfoDataObject = searchResultObjectsList[i];
					            		var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
            		var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
            		var sessionId = loginSessionInfoDataObject['sessionId'];            		
            		var userId = loginSessionInfoDataObject['userId'];            		
            		var loginTime = loginSessionInfoDataObject['loginTime'];            		

            		var resultRowTemnplate = $(gLoginSessionInfoSearchResultsTableRowTemplate);
					            		var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
            	    resultRowTemnplate.find("[data-name='loginUserType']").text(loginUserType);
            		var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
            	    resultRowTemnplate.find("[data-name='selfServiceUserType']").text(selfServiceUserType);
            		var sessionId = loginSessionInfoDataObject['sessionId'];            		
            	    resultRowTemnplate.find("[data-name='sessionId']").text(sessionId);
            		var userId = loginSessionInfoDataObject['userId'];            		
            	    resultRowTemnplate.find("[data-name='userId']").text(userId);
            		var loginTime = loginSessionInfoDataObject['loginTime'];            		
            	    resultRowTemnplate.find("[data-name='loginTime']").text(loginTime);

            	    resultRowTemnplate.data('loginSessionInfo', loginSessionInfoDataObject);
            	    loginSessionInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gLoginSessionInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForLoginSessionInfoForEdit(loginSessionInfoLinkElement)
{
	var loginSessionInfoRowElement = $(loginSessionInfoLinkElement).parents('[data-name="loginSessionInfoRow"]');
    var loginSessionInfoDataObject  = loginSessionInfoRowElement.data('loginSessionInfo');
	var loginSessionInfoId = loginSessionInfoDataObject['loginSessionInfoId'];
	var loginSessionInfoPopupElement = $('[data-name="LoginSessionInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setLoginSessionInfoInViewPage(loginSessionInfoDataObject);
	    $("#LoginSessionInfo-tab").trigger("click");
	}
	else if(loginSessionInfoPopupElement.length > 0)
	{
	    setLoginSessionInfoInViewPage(loginSessionInfoDataObject);
		$('[data-name="LoginSessionInfoPopup"]').find('[data-name="loginSessionInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="LoginSessionInfoPopup"]').find('[data-name="loginSessionInfoViewFormButtonsDiv"]').show();
	    showPopup('LoginSessionInfoPopup', loginSessionInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewLoginSessionInfo.html?loginSessionInfoId="+loginSessionInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openLoginSessionInfoCreatePageForNew(linkElement)
{
	var loginSessionInfoPopupElement = $('[data-name="LoginSessionInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForLoginSessionInfo();
	    $("#LoginSessionInfo-tab").trigger("click");
    }
	else if(loginSessionInfoPopupElement.length > 0)
	{
		resetFormForLoginSessionInfo();
	    showPopup('LoginSessionInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateLoginSessionInfo.html";
    }
}
function showLoginSessionInfoPopupForEdit(loginSessionInfoLinkElement)
{
	var loginSessionInfoRowElement = $(loginSessionInfoLinkElement).parents('[data-name="loginSessionInfoRow"]');
    var loginSessionInfoDataObject  = loginSessionInfoRowElement.data('loginSessionInfo');
    setLoginSessionInfoInViewPage(loginSessionInfoDataObject);
    showPopup('LoginSessionInfoPopup', loginSessionInfoLinkElement, 1);
    $("#LoginSessionInfo-tab").trigger("click");
}
function showLoginSessionInfoPopupForNew(buttonElement)
{
	resetFormForLoginSessionInfo();
    showPopup('LoginSessionInfoPopup', buttonElement, 1);
    $("#LoginSessionInfo-tab").trigger("click");
}
function deleteThisLoginSessionInfo(loginSessionInfoLinkElement, paramsMap)
{
	var loginSessionInfoRowElement = $(loginSessionInfoLinkElement).parents('[data-name="loginSessionInfoRow"]');
    var loginSessionInfoDataObject  = loginSessionInfoRowElement.data('loginSessionInfo');
	var loginSessionInfoId = loginSessionInfoDataObject['loginSessionInfoId'];
	deleteSelectedLoginSessionInfo(loginSessionInfoId, paramsMap);
}
function displayLoginSessionInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + loginSessionInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var loginSessionInfoSearchResults = searchResultsDiv.find('[data-name="loginSessionInfoSearchResults"]');
	//loginSessionInfoSearchResults.find('[data-name="loginSessionInfoRow"]').remove();
	var previousDataRowList  = loginSessionInfoSearchResults.find('[data-name="loginSessionInfoRow"]');
	loginSessionInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = loginSessionInfoSearchResults.find('[data-name="loginSessionInfoRow"]');
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
        var loginSessionInfoDataObject = searchResultObjectsList[i];
        loadLoginSessionInfoViewData(loginSessionInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = loginSessionInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='loginSessionInfoVwTxnStatus']").text(vwTxnStatus);
		if(loginSessionInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="loginSessionInfoActionButton"]').text(loginSessionInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="loginSessionInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="loginSessionInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="loginSessionInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="loginSessionInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('loginSessionInfo', loginSessionInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    loginSessionInfoSearchResults.append(resultRowTemplate);
	    processResultRowForLoginSessionInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForLoginSessionInfo == 1)
	{
    	getDashboardResultsForLoginSessionInfo();
	}
}
var loginSessionInfoSearchResultsDivName = "loginSessionInfoSearchResultsDiv";
var gLoginSessionInfoSearchInputParamsList = [];
function retrieveLoginSessionInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + loginSessionInfoSearchResultsDivName + '"]');
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
    fetchLoginSessionInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchLoginSessionInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getLoginSessionInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = loginSessionInfoSearchResultsDivName; 
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
        'objectType': "LoginSessionInfo"
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
            	this.showPreviousRecords = "showPreviousLoginSessionInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageLoginSessionInfoRecords";
            	this.showNextRecords = "showNextLoginSessionInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchLoginSessionInfoResults";
                var loginSessionInfoList = responseData['loginSessionInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayLoginSessionInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'loginSessionInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'loginSessionInfoSearchResults', 'loginSessionInfoRow', setLoginSessionInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getLoginSessionInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + loginSessionInfoSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="loginSessionInfoDB_loginUserType"]').length == 1)
		{
		    var loginUserType = searchDiv.find('[data-name="loginSessionInfoDB_loginUserType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'loginUserType', 'userInputValue':loginUserType});
		}
		
		//Input
		if(searchDiv.find('[data-name="loginSessionInfoDB_selfServiceUserType"]').length == 1)
		{
		    var selfServiceUserType = searchDiv.find('[data-name="loginSessionInfoDB_selfServiceUserType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'selfServiceUserType', 'userInputValue':selfServiceUserType});
		}
		
		//Input
		if(searchDiv.find('[data-name="loginSessionInfoDB_sessionId"]').length == 1)
		{
		    var sessionId = searchDiv.find('[data-name="loginSessionInfoDB_sessionId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'sessionId', 'userInputValue':sessionId});
		}
		
		//Input
		if(searchDiv.find('[data-name="loginSessionInfoDB_userId"]').length == 1)
		{
		    var userId = searchDiv.find('[data-name="loginSessionInfoDB_userId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'userId', 'userInputValue':userId});
		}
		
		//Datetime
		if(searchDiv.find('[data-name="loginSessionInfoDB_loginTime"]').length == 1)
		{
		    var loginTime = searchDiv.find('[data-name="loginSessionInfoDB_loginTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'loginTime', 'userInputValue':loginTime});
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
        gLoginSessionInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gLoginSessionInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setLoginSessionInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + loginSessionInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gLoginSessionInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gLoginSessionInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageLoginSessionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = loginSessionInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchLoginSessionInfoSearchResultsList);
}
function showPreviousLoginSessionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = loginSessionInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchLoginSessionInfoSearchResultsList);
}
function showNextLoginSessionInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = loginSessionInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchLoginSessionInfoSearchResultsList);
}
function showPrevOrNextSearchLoginSessionInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousLoginSessionInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextLoginSessionInfoRecords(e);
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
function lookupRowSelectedForLoginSessionInfo(attributeName, attributeId)
{
	var loginSessionInfo = getDataForLoginSessionInfo();
	loginSessionInfo['attributeName'] = attributeName;
	loginSessionInfo['attributeId'] = attributeId;
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var loginSessionInfo = responseData['loginSessionInfo'];
            	setLoginSessionInfoData(loginSessionInfo);
            }
        }
    });	
}
function selectOptionChangedForLoginSessionInfo(attributeName)
{
	var loginSessionInfo = getDataForLoginSessionInfo();
	loginSessionInfo['attributeName'] = attributeName;
    var loginSessionInfoJsonData = {'paramsInfo': JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var loginSessionInfo = responseData['loginSessionInfo'];
            	setLoginSessionInfoData(loginSessionInfo);
            	doAfterLoginSessionInfoPanelRefreshed();
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
function retrieveDependentLoginSessionInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'LoginSessionInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var loginSessionInfoList = responseData['loginSessionInfoList'];
            	var loginSessionInfoListElement = $("[data-name='loginSessionInfoList']");
            	var previousDataRowList  = loginSessionInfoListElement.find('[data-name="loginSessionInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = loginSessionInfoListElement.find('[data-name="loginSessionInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//loginSessionInfoListElement.empty();
            	for(var i=0; i<loginSessionInfoList.length; i++)
        		{
            		var loginSessionInfoDataObject = loginSessionInfoList[i];
            		//var resultRowTemplate = $(gLoginSessionInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var loginUserType = loginSessionInfoDataObject['loginUserType'];            		
				    resultRowTemplate.find("[data-name='loginUserType']").text(loginUserType);
					var selfServiceUserType = loginSessionInfoDataObject['selfServiceUserType'];            		
				    resultRowTemplate.find("[data-name='selfServiceUserType']").text(selfServiceUserType);
					var sessionId = loginSessionInfoDataObject['sessionId'];            		
				    resultRowTemplate.find("[data-name='sessionId']").text(sessionId);
					var userId = loginSessionInfoDataObject['userId'];            		
				    resultRowTemplate.find("[data-name='userId']").text(userId);
					var loginTime = loginSessionInfoDataObject['loginTime'];            		
				    resultRowTemplate.find("[data-name='loginTime']").text(loginTime);

					
					
            	    resultRowTemplate.data('loginSessionInfo', loginSessionInfoDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    loginSessionInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForLoginSessionInfo(customEventName)
{
	var loginSessionInfoId = document.getElementById('idValueForLoginSessionInfo').value;
	var loginSessionInfo = {'loginSessionInfoId':loginSessionInfoId, 'customEventName':customEventName};
    var loginSessionInfoJsonData = {'paramsInfo':JSON.stringify(loginSessionInfo), 'objectType' : 'LoginSessionInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForLoginSessionInfoExt(customEventName);
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var loginSessionInfo = responseData['loginSessionInfo'];
        		setLoginSessionInfoInViewPage(loginSessionInfo);
            }
    		doAfterExecuteCustomAPIForLoginSessionInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnLoginSessionInfo(e, paramsMap)
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
	var loginSessionInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var loginSessionInfoDataObject  = rowObj.data('loginSessionInfo');
    	loginSessionInfoId = loginSessionInfoDataObject['loginSessionInfoId'];
	}
    else
	{
    	loginSessionInfoId = document.getElementById('idValueForLoginSessionInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var loginSessionInfoSearchParams = {'loginSessionInfoId':loginSessionInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	loginSessionInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var loginSessionInfoJsonData = {'paramsInfo':JSON.stringify(loginSessionInfoSearchParams),  'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var loginSessionInfo = responseData['loginSessionInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="loginSessionInfoRowActionButtonDiv"]').hide();
            		if(loginSessionInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = loginSessionInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(loginSessionInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="loginSessionInfoActionButton"]').text(loginSessionInfo["nextAction"]);
            			selectedRowObj.find('[data-name="loginSessionInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setLoginSessionInfoInViewPage(loginSessionInfo);
    			}
            }
        }
    });	
}
function downloadLoginSessionInfoData()
{		
	var loginSessionInfo = {};
    var loginSessionInfoJsonData = {'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=LoginSessionInfo");
            }
        }
    });
}
function uploadLoginSessionInfoData(fileInfo)
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
	var loginSessionInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var loginSessionInfoJsonData = {'paramsInfo':JSON.stringify(loginSessionInfo),  'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=LoginSessionInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForLoginSessionInfo()
{
    var loginSessionInfoJsonData = {'objectType' : 'LoginSessionInfo'};
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
        data: loginSessionInfoJsonData,
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



function doLoginSessionInfoLengthValidation(loginSessionInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(loginSessionInfoDataObject['loginUserType'], 50))
	{
		alertMessage += "\n Login User Type length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(loginSessionInfoDataObject['selfServiceUserType'], 50))
	{
		alertMessage += "\n SelfServiceUserType length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(loginSessionInfoDataObject['sessionId'], 50))
	{
		alertMessage += "\n SessionId length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(loginSessionInfoDataObject['userId'], 10))
	{
		alertMessage += "\n UserId length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(loginSessionInfoDataObject['loginTime'], 20))
	{
		alertMessage += "\n LoginTime length is more than allowed(20)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doLoginSessionInfoManadatoryValidation(loginSessionInfoDataObject, paramsMap)
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
		
	var loginUserType = loginSessionInfoDataObject['loginUserType'];
	if(!loginUserType || loginUserType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"LoginSessionInfo_loginUserType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Login User Type";
		    validationPassed = false;
		}
	}
	
	var userId = loginSessionInfoDataObject['userId'];
	if(!userId || userId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"LoginSessionInfo_userId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter UserId";
		    validationPassed = false;
		}
	}
	
	var loginTime = loginSessionInfoDataObject['loginTime'];
	if(!loginTime || loginTime.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"LoginSessionInfo_loginTime").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter LoginTime";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doLoginSessionInfoValidation(loginSessionInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doLoginSessionInfoLengthValidation(loginSessionInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doLoginSessionInfoManadatoryValidation(loginSessionInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
