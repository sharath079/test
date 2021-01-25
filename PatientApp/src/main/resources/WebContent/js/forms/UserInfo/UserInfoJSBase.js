/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'UserFirstName' : 'FormWBEntity:UserInfo_userFirstName' 
 *	 * 'UserLastName' : 'FormWBEntity:UserInfo_userLastName' 
 *	 * 'OrganisationsUserOrg' : 'FormWBEntity:UserInfo_organisationsUserOrg' 
 *	 * 'LoginId' : 'FormWBEntity:UserInfo_loginId' 
 *	 * 'LoginEmailId' : 'FormWBEntity:UserInfo_loginEmailId' 
 *	 * 'ContactNo' : 'FormWBEntity:UserInfo_contactNo' 
 *	 * 'LoginPassword' : 'FormWBEntity:UserInfo_loginPassword' 
 *	 * 'ResetToken' : 'FormWBEntity:UserInfo_resetToken' 
 *	
 */
var gInitParamsObjForUserInfo = {};
var gUserInfoRequestUnderProcess = 0;
function selectThisUserInfoForEdit(userInfoRowElement)
{
    var userInfoDataObject  = $(userInfoRowElement).data('userInfo');
    var userInfoList = $('[data-name="userInfoSearchResults"]').find('[data-name="userInfoRow"]');
	userInfoList.data("is-selected", 0);	
	$(userInfoRowElement).data("is-selected", 1);
	setUserInfoInViewPage(userInfoDataObject);
}

function setUserInfoInViewPage(userInfoDataObject, paramsMap)
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
	var userInfoId = userInfoDataObject['userInfoId'];
	$('#'+prefix+'idValueForUserInfo').val(userInfoId);
		
	//Input
	if(userInfoDataObject.hasOwnProperty('userFirstName'))
	{
		var userFirstName = userInfoDataObject['userFirstName'];            		
		$('#'+prefix+'UserInfo_userFirstName').val(userFirstName);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('userLastName'))
	{
		var userLastName = userInfoDataObject['userLastName'];            		
		$('#'+prefix+'UserInfo_userLastName').val(userLastName);
	}
	
	//Lookup
	if(userInfoDataObject.hasOwnProperty('organisationsUserOrg'))
	{
		var organisationsUserOrg = userInfoDataObject['organisationsUserOrg'];
		if(organisationsUserOrg != "")
		{
			var organisationsUserOrgId = organisationsUserOrg['organisationsId'];
			var organisationsUserOrgLinkElement = $('#'+prefix+'UserInfo_organisationsUserOrgId');
			organisationsUserOrgLinkElement.data("organisations-id", organisationsUserOrgId);
			var organisationsUserOrgNextElements = organisationsUserOrgLinkElement.parents(".form-group").next();
			for(var i=0; i< organisationsUserOrgNextElements.length; i++)
			{
				var inputElement = $(organisationsUserOrgNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(organisationsUserOrg[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('loginId'))
	{
		var loginId = userInfoDataObject['loginId'];            		
		$('#'+prefix+'UserInfo_loginId').val(loginId);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('loginEmailId'))
	{
		var loginEmailId = userInfoDataObject['loginEmailId'];            		
		$('#'+prefix+'UserInfo_loginEmailId').val(loginEmailId);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = userInfoDataObject['contactNo'];            		
		$('#'+prefix+'UserInfo_contactNo').val(contactNo);
	}
	
	//Password
	if(userInfoDataObject.hasOwnProperty('loginPassword'))
	{
		var loginPassword = userInfoDataObject['loginPassword'];            		
		$('#'+prefix+'UserInfo_loginPassword').val(loginPassword)
	}

	if(userInfoDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = userInfoDataObject['vwTxnStatus'];
		$('[data-name="userInfoActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnUserInfo(this)" >' +userInfoDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="userInfoActionButtonDiv"]').append(buttonElement);
		$('[data-name="userInfoActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="userInfoActionButtonDiv"]').hide();
	}
	$('[data-name="userInfoCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="userInfoViewFormButtonsDiv"]').css("display", "inline");
	//loadUserInfoViewData(userInfoDataObject);
	disbaleUserInfoUpdateDisallowedFields(paramsMap);
	doAfterUserInfoPanelRefreshed();
    
    resetFormForEmployeeRoles();
    resetTableForEmployeeRoles();
    
        retrieveDependentEmployeeRolesList(paramsMap);

}
function disbaleUserInfoUpdateDisallowedFields(paramsMap)
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
		parentElement.find('#'+prefix+'UserInfo_loginPassword').attr("disabled", "disabled");

}
function loadUserInfoViewData(userInfoDataObject, paramsMap)
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
	var userInfoId = userInfoDataObject['userInfoId'];
	$('#'+prefix+'idValueForUserInfo').val(userInfoId);
		
	if(userInfoDataObject.hasOwnProperty('userFirstName'))
	{
		var userFirstName = userInfoDataObject['userFirstName'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_userFirstName"]').text(userFirstName);
	}
	
	if(userInfoDataObject.hasOwnProperty('userLastName'))
	{
		var userLastName = userInfoDataObject['userLastName'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_userLastName"]').text(userLastName);
	}
	
	if(userInfoDataObject.hasOwnProperty('organisationsUserOrg'))
	{
		var organisationsUserOrg = userInfoDataObject['organisationsUserOrg'];
		if(organisationsUserOrg != "")
		{
			var organisationsUserOrgId = userInfoDataObject['organisationsUserOrgId'];
			var organisationsUserOrgDisplayVal = userInfoDataObject['organisationsUserOrgDisplayVal'];
			parentElement.find('[data-name="'+prefix+'UserInfo_organisationsUserOrg"]').text(organisationsUserOrgDisplayVal);
			//organisationsUserOrgLinkElement.data("organisations-id", organisationsUserOrgId);
		}
	}
	
	if(userInfoDataObject.hasOwnProperty('loginId'))
	{
		var loginId = userInfoDataObject['loginId'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_loginId"]').text(loginId);
	}
	
	if(userInfoDataObject.hasOwnProperty('loginEmailId'))
	{
		var loginEmailId = userInfoDataObject['loginEmailId'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_loginEmailId"]').text(loginEmailId);
	}
	
	if(userInfoDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = userInfoDataObject['contactNo'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_contactNo"]').text(contactNo);
	}
	
	if(userInfoDataObject.hasOwnProperty('loginPassword'))
	{
		var loginPassword = userInfoDataObject['loginPassword'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_loginPassword"]').text(loginPassword);
	}
	
	if(userInfoDataObject.hasOwnProperty('resetToken'))
	{
		var resetToken = userInfoDataObject['resetToken'];            		
		parentElement.find('[data-name="'+prefix+'UserInfo_resetToken"]').text(resetToken);
	}

}
function ajaxDemoForUserInfo()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForUserInfo').textContent;	
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
			refreshPanelForUserInfo();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForUserInfo(msg)
{
	var executeCustomAPIButtonForUserInfo = document.getElementById("FormWBEntity:executeCustomAPIButtonForUserInfo");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:UserInfo_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForUserInfo.click();
}
function refreshPanelForUserInfo()
{
	var demoRefreshButtonForUserInfo = document.getElementById("FormWBEntity:demoRefreshButtonUserInfo");
	demoRefreshButtonForUserInfo.click();
}
function initializePanelOnLoadForCreateUserInfo(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForUserInfo = initParamsObj;
	initializeUserInfoPage();	
	doAfterUserInfoPanelRefreshed();
	initializeUserInfoLookupFields(initParamsObj);
	doAfterPanelInitializedForUserInfoExt();
	fetchUserInfoDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="userInfoCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForUserInfo = 0;
function initializePanelOnLoadForSearchUserInfo()
{
	initializeUserInfoPage();	
	initializeUserInfoLookupFields();
	doAfterPanelInitializedForUserInfoExt();
	gLoadDashboardResultsForUserInfo =1;
	//retrieveUserInfoList();
}
function initializePanelOnLoadForViewUserInfo(urlParams)
{
	initializeUserInfoPage();	
	doAfterUserInfoPanelRefreshed();
	initializeUserInfoLookupFields(urlParams);
	doAfterPanelInitializedForUserInfoExt();
	retrieveUserInfo(urlParams);
	initDatePicker();
	$('[data-name="userInfoViewFormButtonsDiv"]').css("display", "inline");
}
function initializeUserInfoPage()
{
	initializePageOnload();	
	//initializeUserInfoTemplateVariables();
}
function initializeUserInfoTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="userInfoSearchResults"]').find('[data-name="userInfoRow"]');
	if(searchResultsRowObj.length > 0 && gUserInfoSearchResultsTableRowTemplate.length == 0)
	{
		gUserInfoSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	    initializeEmployeeRolesTemplateVariables();

}
function retrieveUserInfo(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var userInfoId = paramsMap['userInfoId'];
	var userInfo = {};
	userInfo['userInfoId'] = userInfoId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				userInfo[key] = paramsMap[key];
			}
	}
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
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
                	var userInfoDataObject = responseData['userInfoDataObject'];
    				setUserInfoInViewPage(userInfoDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForUserInfo()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchUserInfoDefaultData();
}
function fetchUserInfoDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'UserInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var userInfo = responseData['userInfo'];
            	document.getElementById('idValueForUserInfo').value = '';
			    
			    resetFormForEmployeeRoles();
			    resetTableForEmployeeRoles();
			    
            	setUserInfoData(userInfo);
            }
        }
    });	
}
function fetchUserInfoTestData() 
{
	var userInfo = getDataForUserInfo();
    var searchJsonData = {'objectType' : 'UserInfo', 'paramsInfo' : JSON.stringify(userInfo)};
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
            	var userInfo = responseData['userInfo'];
            	document.getElementById('idValueForUserInfo').value = '';
			    
			    resetFormForEmployeeRoles();
			    resetTableForEmployeeRoles();
			    
            	setUserInfoData(userInfo);
            }
        }
    });	
}
function setUserInfoData(userInfoDataObject)
{
	var prefix = "";
		
	//Input
	if(userInfoDataObject.hasOwnProperty('userFirstName'))
	{
		var userFirstName = userInfoDataObject['userFirstName'];            		
		$('#'+prefix+'UserInfo_userFirstName').val(userFirstName);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('userLastName'))
	{
		var userLastName = userInfoDataObject['userLastName'];            		
		$('#'+prefix+'UserInfo_userLastName').val(userLastName);
	}
	
	//Lookup
	if(userInfoDataObject.hasOwnProperty('organisationsUserOrg'))
	{
		var organisationsUserOrg = userInfoDataObject['organisationsUserOrg'];
		if(organisationsUserOrg != "")
		{
			var organisationsUserOrgId = organisationsUserOrg['organisationsId'];
			var organisationsUserOrgLinkElement = $('#'+prefix+'UserInfo_organisationsUserOrgId');
			organisationsUserOrgLinkElement.data("organisations-id", organisationsUserOrgId);
			var organisationsUserOrgNextElements = organisationsUserOrgLinkElement.parents(".form-group").next();
			for(var i=0; i< organisationsUserOrgNextElements.length; i++)
			{
				var inputElement = $(organisationsUserOrgNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(organisationsUserOrg[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('loginId'))
	{
		var loginId = userInfoDataObject['loginId'];            		
		$('#'+prefix+'UserInfo_loginId').val(loginId);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('loginEmailId'))
	{
		var loginEmailId = userInfoDataObject['loginEmailId'];            		
		$('#'+prefix+'UserInfo_loginEmailId').val(loginEmailId);
	}
	
	//Input
	if(userInfoDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = userInfoDataObject['contactNo'];            		
		$('#'+prefix+'UserInfo_contactNo').val(contactNo);
	}
	
	//Password
	if(userInfoDataObject.hasOwnProperty('loginPassword'))
	{
		var loginPassword = userInfoDataObject['loginPassword'];            		
		$('#'+prefix+'UserInfo_loginPassword').val(loginPassword);
	}

	$('[data-name="userInfoActionButtonDiv"]').hide();
}
function initializeUserInfoLookupFields(paramsMap) 
{
	  
	
	
	
	  
	
	
	
		
	$("#UserInfo_organisationsUserOrgId").data("organisations-id", -1);
  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    initializeEmployeeRolesLookupSelectList();
    
    var searchDiv = $('[data-name="userInfoSearchResultsDiv"]');
	  
	
	
	  
	
	
		
	
    searchDiv.find('[data-name="userInfoDB_organisationsUserOrgId"]').data("organisations-id", -1);
  
	
	
	  
	
	
	  
	
	
	  
	
	
	
    
}

function doAfterUserInfoPanelRefreshed()
{
    //doAfterPanelRefreshedForUserInfoExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForUserInfo(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForUserInfoExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForUserInfo(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'organisationsUserOrg')
	{
	}

	doAfterLookupRowChangedForUserInfoExt(fieldName)
}
function getEntityIdForUserInfo()
{
	var idValue = document.getElementById('FormWBEntity:idValueForUserInfo').textContent;	
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
function openPrintPageForUserInfo()
{
	var entityId = getEntityIdForUserInfo();
	if(entityId>0)
	{
	    window.open("/reports/generated/UserInfo.jsp?userInfoId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}
function hideOrganisationsUserOrgForUserInfo()
{
		
		document.getElementById('UserInfo_organisationsUserOrgId').parentElement.style.display = "none";
	   	
		document.getElementById('UserInfo_organisationsUserOrgorganisationName').parentElement.style.display = "none";	
	   	
		//document.getElementById('UserInfo_organisationsUserOrgNewLinkID').parentElement.style.display = "none";
}

function showOrganisationsUserOrgForUserInfo()
{
		
		document.getElementById('UserInfo_organisationsUserOrgId').parentElement.style.display = "table-row";
	   	
		document.getElementById('UserInfo_organisationsUserOrgorganisationName').parentElement.style.display = "table-row";	
	   	
		//document.getElementById('UserInfo_organisationsUserOrgNewLinkID').parentElement.style.display = "table-row";
}


function getDataForUserInfo(paramsMap)
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
	var userInfo = {};
		
	//Input
	if($("#"+prefix+"UserInfo_userFirstName").length == 1)
	{
		var userFirstName = $('#'+prefix+'UserInfo_userFirstName').val();
		userInfo['userFirstName'] = userFirstName;
	}
	
	//Input
	if($("#"+prefix+"UserInfo_userLastName").length == 1)
	{
		var userLastName = $('#'+prefix+'UserInfo_userLastName').val();
		userInfo['userLastName'] = userLastName;
	}
	
	//Lookup
	if($("#"+prefix+"UserInfo_organisationsUserOrgId").length == 1)
	{
		var organisationsUserOrgId = $('#'+prefix+'UserInfo_organisationsUserOrgId').data("organisations-id");
		userInfo['organisationsUserOrgId'] = organisationsUserOrgId;
	}
	
	//Input
	if($("#"+prefix+"UserInfo_loginId").length == 1)
	{
		var loginId = $('#'+prefix+'UserInfo_loginId').val();
		userInfo['loginId'] = loginId;
	}
	
	//Input
	if($("#"+prefix+"UserInfo_loginEmailId").length == 1)
	{
		var loginEmailId = $('#'+prefix+'UserInfo_loginEmailId').val();
		userInfo['loginEmailId'] = loginEmailId;
	}
	
	//Input
	if($("#"+prefix+"UserInfo_contactNo").length == 1)
	{
		var contactNo = $('#'+prefix+'UserInfo_contactNo').val();
		userInfo['contactNo'] = contactNo;
	}
	
	//Password
	if($("#"+prefix+"UserInfo_loginPassword").length == 1)
	{
		var loginPassword = $('#'+prefix+'UserInfo_loginPassword').val();
		userInfo['loginPassword'] = loginPassword;
	}

	
	return userInfo;
}
function createUserInfo(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var userInfo = getDataForUserInfo(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				userInfo[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForUserInfo)
	{
		paramsInfo[key] = gInitParamsObjForUserInfo[key];
	}
	var validationObject = doUserInfoValidation(userInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	userInfo['additionalParamsInfo'] = paramsInfo;
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
	var urlContextPath = "";//getContextPath();
	if(gUserInfoRequestUnderProcess == 1)
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
            	gUserInfoRequestUnderProcess = 0;
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gUserInfoRequestUnderProcess = 0;
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
            	var userInfoId = responseData['userInfoId'];
            	var popupElement = $('[data-name="UserInfoPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var userInfoDataObject = responseData['userInfoDataObject'];
    				setUserInfoInViewPage(userInfoDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewUserInfo.html?userInfoId="+userInfoId; 
        		}
				
            }
        }
    });
}
function resetTableForUserInfo()
{
	var userInfoListElement = $("[data-name='userInfoList']");
	var previousDataRowList  = userInfoListElement.find('[data-name="userInfoRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForUserInfo(paramsMap)
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
	$('#'+prefix+'idValueForUserInfo').val('');
		
	//Input
	$('#'+prefix+'UserInfo_userFirstName').val('');
	
	//Input
	$('#'+prefix+'UserInfo_userLastName').val('');
	
	//Lookup
	var organisationsUserOrgLinkElement = $('#'+prefix+'UserInfo_organisationsUserOrgId');
	organisationsUserOrgLinkElement.data("organisations-id", -1);
	var organisationsUserOrgNextElements = organisationsUserOrgLinkElement.parents(".form-group").next();
	for(var i=0; i< organisationsUserOrgNextElements.length; i++)
	{
		var inputElement = $(organisationsUserOrgNextElements[i]).find('input');
		if(inputElement.data("is-lookup-field") != 1)
		{
			break;
		}
		inputElement.val('');		
	}
	
	//Input
	$('#'+prefix+'UserInfo_loginId').val('');
	
	//Input
	$('#'+prefix+'UserInfo_loginEmailId').val('');
	
	//Input
	$('#'+prefix+'UserInfo_contactNo').val('');
	
	//Password
	$('#'+prefix+'UserInfo_loginPassword').val('');

	$('[data-name="userInfoCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="userInfoViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="userInfoActionButtonDiv"]').hide();
	enableUserInfoUpdateDisallowedFields(paramsMap);
    
    resetFormForEmployeeRoles();
    resetTableForEmployeeRoles();
    
}
function enableUserInfoUpdateDisallowedFields(paramsMap)
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
		$('#'+prefix+'UserInfo_loginPassword').removeAttr("disabled");

}
function updateUserInfo(paramsMap)
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
	var userInfo = getDataForUserInfo(paramsMap)
	if($("#"+prefix+"idValueForUserInfo").length == 1)
	{
		var userInfoId = $("#"+prefix+"idValueForUserInfo").val();
		userInfo['userInfoId'] = userInfoId;
	}
	var validationObject = doUserInfoValidation(userInfo, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
	var urlContextPath = "";//getContextPath();
	if(gUserInfoRequestUnderProcess == 1)
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
                    	gUserInfoRequestUnderProcess = 0;
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gUserInfoRequestUnderProcess = 0;
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
function deleteUserInfo(paramsMap)
{		
	var userInfoId = document.getElementById('idValueForUserInfo').value;
	deleteSelectedUserInfo(userInfoId, paramsMap);
}
function deleteSelectedUserInfo(userInfoId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var userInfo = {};
	userInfo['userInfoId'] = userInfoId;	
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
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
function loadSearchResultsForUserInfo()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'UserInfo'};
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
            	var userInfoSearchResultsElement = $("[data-name='userInfoSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var userInfoDataObject = searchResultObjectsList[i];
					            		var userFirstName = userInfoDataObject['userFirstName'];            		
            		var userLastName = userInfoDataObject['userLastName'];            		
            		var organisationsUserOrg = userInfoDataObject['organisationsUserOrg'];            		
            		var loginId = userInfoDataObject['loginId'];            		
            		var loginEmailId = userInfoDataObject['loginEmailId'];            		
            		var contactNo = userInfoDataObject['contactNo'];            		
            		var loginPassword = userInfoDataObject['loginPassword'];            		

            		var resultRowTemnplate = $(gUserInfoSearchResultsTableRowTemplate);
					            		var userFirstName = userInfoDataObject['userFirstName'];            		
            	    resultRowTemnplate.find("[data-name='userFirstName']").text(userFirstName);
            		var userLastName = userInfoDataObject['userLastName'];            		
            	    resultRowTemnplate.find("[data-name='userLastName']").text(userLastName);
            		var organisationsUserOrg = userInfoDataObject['organisationsUserOrg'];            		
            	    resultRowTemnplate.find("[data-name='organisationsUserOrg']").text(organisationsUserOrg);
            		var loginId = userInfoDataObject['loginId'];            		
            	    resultRowTemnplate.find("[data-name='loginId']").text(loginId);
            		var loginEmailId = userInfoDataObject['loginEmailId'];            		
            	    resultRowTemnplate.find("[data-name='loginEmailId']").text(loginEmailId);
            		var contactNo = userInfoDataObject['contactNo'];            		
            	    resultRowTemnplate.find("[data-name='contactNo']").text(contactNo);
            		var loginPassword = userInfoDataObject['loginPassword'];            		
            	    resultRowTemnplate.find("[data-name='loginPassword']").text(loginPassword);

            	    resultRowTemnplate.data('userInfo', userInfoDataObject);
            	    userInfoSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gUserInfoSearchResultsTableRowTemplate = ""; 
function openViewPageForUserInfoForEdit(userInfoLinkElement)
{
	var userInfoRowElement = $(userInfoLinkElement).parents('[data-name="userInfoRow"]');
    var userInfoDataObject  = userInfoRowElement.data('userInfo');
	var userInfoId = userInfoDataObject['userInfoId'];
	var userInfoPopupElement = $('[data-name="UserInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setUserInfoInViewPage(userInfoDataObject);
	    $("#UserInfo-tab").trigger("click");
	}
	else if(userInfoPopupElement.length > 0)
	{
	    setUserInfoInViewPage(userInfoDataObject);
		$('[data-name="UserInfoPopup"]').find('[data-name="userInfoCreateFormButtonsDiv"]').hide();
		$('[data-name="UserInfoPopup"]').find('[data-name="userInfoViewFormButtonsDiv"]').show();
	    showPopup('UserInfoPopup', userInfoLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewUserInfo.html?userInfoId="+userInfoId;
		window.open(viewLink, "_blank"); 	
	}
}
function openUserInfoCreatePageForNew(linkElement)
{
	var userInfoPopupElement = $('[data-name="UserInfoPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForUserInfo();
	    $("#UserInfo-tab").trigger("click");
    }
	else if(userInfoPopupElement.length > 0)
	{
		resetFormForUserInfo();
	    showPopup('UserInfoPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateUserInfo.html";
    }
}
function showUserInfoPopupForEdit(userInfoLinkElement)
{
	var userInfoRowElement = $(userInfoLinkElement).parents('[data-name="userInfoRow"]');
    var userInfoDataObject  = userInfoRowElement.data('userInfo');
    setUserInfoInViewPage(userInfoDataObject);
    showPopup('UserInfoPopup', userInfoLinkElement, 1);
    $("#UserInfo-tab").trigger("click");
}
function showUserInfoPopupForNew(buttonElement)
{
	resetFormForUserInfo();
    showPopup('UserInfoPopup', buttonElement, 1);
    $("#UserInfo-tab").trigger("click");
}
function deleteThisUserInfo(userInfoLinkElement, paramsMap)
{
	var userInfoRowElement = $(userInfoLinkElement).parents('[data-name="userInfoRow"]');
    var userInfoDataObject  = userInfoRowElement.data('userInfo');
	var userInfoId = userInfoDataObject['userInfoId'];
	deleteSelectedUserInfo(userInfoId, paramsMap);
}
function displayUserInfoList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + userInfoSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var userInfoSearchResults = searchResultsDiv.find('[data-name="userInfoSearchResults"]');
	//userInfoSearchResults.find('[data-name="userInfoRow"]').remove();
	var previousDataRowList  = userInfoSearchResults.find('[data-name="userInfoRow"]');
	userInfoSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = userInfoSearchResults.find('[data-name="userInfoRow"]');
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
        var userInfoDataObject = searchResultObjectsList[i];
        loadUserInfoViewData(userInfoDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = userInfoDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='userInfoVwTxnStatus']").text(vwTxnStatus);
		if(userInfoDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="userInfoActionButton"]').text(userInfoDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="userInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="userInfoActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="userInfoRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="userInfoRejectButton"]').show();
		}
	    resultRowTemplate.data('userInfo', userInfoDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    userInfoSearchResults.append(resultRowTemplate);
	    processResultRowForUserInfoExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForUserInfo == 1)
	{
    	getDashboardResultsForUserInfo();
	}
}
var userInfoSearchResultsDivName = "userInfoSearchResultsDiv";
var gUserInfoSearchInputParamsList = [];
function retrieveUserInfoList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + userInfoSearchResultsDivName + '"]');
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
    fetchUserInfoSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchUserInfoSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getUserInfoSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = userInfoSearchResultsDivName; 
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
        'objectType': "UserInfo"
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
            	this.showPreviousRecords = "showPreviousUserInfoRecords";
            	this.showCurrentPageRecords = "showCurrentPageUserInfoRecords";
            	this.showNextRecords = "showNextUserInfoRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchUserInfoResults";
                var userInfoList = responseData['userInfoList'];
                var currContextObj = this; 
                var successCallbackFunction = displayUserInfoList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'userInfoList', 'matchingSearchResultsCount', this.searchResultsDivName, 'userInfoSearchResults', 'userInfoRow', setUserInfoSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getUserInfoSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + userInfoSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="userInfoDB_userFirstName"]').length == 1)
		{
		    var userFirstName = searchDiv.find('[data-name="userInfoDB_userFirstName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'userFirstName', 'userInputValue':userFirstName});
		}
		
		//Input
		if(searchDiv.find('[data-name="userInfoDB_userLastName"]').length == 1)
		{
		    var userLastName = searchDiv.find('[data-name="userInfoDB_userLastName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'userLastName', 'userInputValue':userLastName});
		}
		
    	
		//Lookup		
		if(searchDiv.find('[data-name="userInfoDB_organisationsUserOrgId"]').length == 1)
		{
		    var organisationsUserOrgId = searchDiv.find('[data-name="userInfoDB_organisationsUserOrgId"]').data("organisations-id");
		    if(organisationsUserOrgId > -1)
	    	{
	    		parameterNameAndValuesList.push({ 'parameterName':'organisationsUserOrgId', 'userInputValue':organisationsUserOrgId});
	    	}
		}
		
		//Input
		if(searchDiv.find('[data-name="userInfoDB_loginId"]').length == 1)
		{
		    var loginId = searchDiv.find('[data-name="userInfoDB_loginId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'loginId', 'userInputValue':loginId});
		}
		
		//Input
		if(searchDiv.find('[data-name="userInfoDB_loginEmailId"]').length == 1)
		{
		    var loginEmailId = searchDiv.find('[data-name="userInfoDB_loginEmailId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'loginEmailId', 'userInputValue':loginEmailId});
		}
		
		//Input
		if(searchDiv.find('[data-name="userInfoDB_contactNo"]').length == 1)
		{
		    var contactNo = searchDiv.find('[data-name="userInfoDB_contactNo"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'contactNo', 'userInputValue':contactNo});
		}
		
		//Password
		if(searchDiv.find('[data-name="userInfoDB_loginPassword"]').length == 1)
		{
		    var loginPassword = searchDiv.find('[data-name="userInfoDB_loginPassword"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'loginPassword', 'userInputValue':loginPassword});
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
        gUserInfoSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gUserInfoSearchInputParamsList;
    }
    return searchInputParams;
}
function setUserInfoSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + userInfoSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gUserInfoSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gUserInfoSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageUserInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userInfoSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchUserInfoSearchResultsList);
}
function showPreviousUserInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userInfoSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchUserInfoSearchResultsList);
}
function showNextUserInfoRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userInfoSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchUserInfoSearchResultsList);
}
function showPrevOrNextSearchUserInfoResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousUserInfoRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextUserInfoRecords(e);
    }
}
  

  


var gUserInfo_organisationsUserOrgSearchResultsTableRowTemplate =""; 
function initializeOrganisationsPopup_UserInfo_organisationsUserOrgLookupFields() 
{	
    var searchDiv = $('[data-name="UserInfo_organisationsUserOrgSearchDiv"]');
	
    
	if(gUserInfo_organisationsUserOrgSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="UserInfo_organisationsUserOrgSearchResults"]').find('[data-name="UserInfo_organisationsUserOrgRow"]');
		gUserInfo_organisationsUserOrgSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayUserInfo_organisationsUserOrgList(searchResultObjectsList, parentElement)
{
    var organisationsSearchResults = $('[data-name="UserInfo_organisationsUserOrgSearchResults"]');
	organisationsSearchResults.find('[data-name="UserInfo_organisationsUserOrgRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gUserInfo_organisationsUserOrgSearchResultsTableRowTemplate);
        var organisationsDataObject = searchResultObjectsList[i];
				var organisationName = organisationsDataObject['organisationName'];            		
	    resultRowTemplate.find("[data-name='organisationName']").text(organisationName);
		var addressLine1 = organisationsDataObject['addressLine1'];            		
	    resultRowTemplate.find("[data-name='addressLine1']").text(addressLine1);
		var addressLine2 = organisationsDataObject['addressLine2'];            		
	    resultRowTemplate.find("[data-name='addressLine2']").text(addressLine2);
		var city = organisationsDataObject['city'];            		
	    resultRowTemplate.find("[data-name='city']").text(city);
		var residentState = organisationsDataObject['residentState'];            		
	    resultRowTemplate.find("[data-name='residentState']").text(residentState);
		var pinCode = organisationsDataObject['pinCode'];            		
	    resultRowTemplate.find("[data-name='pinCode']").text(pinCode);
		var databaseName = organisationsDataObject['databaseName'];            		
	    resultRowTemplate.find("[data-name='databaseName']").text(databaseName);
		var country = organisationsDataObject['country'];            		
	    resultRowTemplate.find("[data-name='country']").text(country);

		
	    
	    resultRowTemplate.data('organisations', organisationsDataObject);
	    organisationsSearchResults.append(resultRowTemplate);            	    
    }
}
var UserInfo_organisationsUserOrgSearchResultsDivName = "UserInfo_organisationsUserOrgSearchResultsDiv";
var gUserInfo_organisationsUserOrgSearchInputParamsList = [];
function getUserInfo_organisationsUserOrgSearchResults()
{
    var searchDiv = $('[data-name="UserInfo_organisationsUserOrgSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + UserInfo_organisationsUserOrgSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchUserInfo_organisationsUserOrgSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchUserInfo_organisationsUserOrgSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getUserInfo_organisationsUserOrgSearchInputParams();
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
        'objectType': "Organisations",
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
            	this.showCurrentPageRecords = "showCurrentPageUserInfo_organisationsUserOrgRecords";
            	this.showPreviousRecords = "showPreviousUserInfo_organisationsUserOrgRecords";
            	this.showCurrentPageRecords = "showCurrentPageUserInfo_organisationsUserOrgRecords";
            	this.showNextRecords = "showNextUserInfo_organisationsUserOrgRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchUserInfo_organisationsUserOrgResults";
                var organisationsList = responseData['organisationsList'];  
        		handleSearchResultsResponse(this, responseData, 'organisationsList', 'matchingSearchResultsCount', UserInfo_organisationsUserOrgSearchResultsDivName, 'UserInfo_organisationsUserOrgSearchResults', 'UserInfo_organisationsUserOrgRow', setUserInfo_organisationsUserOrgSearchInputParams, displayUserInfo_organisationsUserOrgList);
            }
        }
    });
}
function getUserInfo_organisationsUserOrgSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + UserInfo_organisationsUserOrgSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="UserInfo_organisationsUserOrgSearchDiv"]');
        var parameterNameAndValuesList = [];
				
		//Input
	    var organisationName = searchDiv.find('[data-name="organisationsDB_organisationName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'organisationName', 'userInputValue':organisationName});
		
		//Input
	    var addressLine1 = searchDiv.find('[data-name="organisationsDB_addressLine1"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'addressLine1', 'userInputValue':addressLine1});
		
		//Input
	    var addressLine2 = searchDiv.find('[data-name="organisationsDB_addressLine2"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'addressLine2', 'userInputValue':addressLine2});
		
		//Combo
	    var city = searchDiv.find('[data-name="organisationsDB_city"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'city', 'userInputValue':city});
		
		//Combo
	    var residentState = searchDiv.find('[data-name="organisationsDB_residentState"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'residentState', 'userInputValue':residentState});

	    
        gUserInfo_organisationsUserOrgSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gUserInfo_organisationsUserOrgSearchInputParamsList;
    }
    return searchInputParams;
}
function setUserInfo_organisationsUserOrgSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + UserInfo_organisationsUserOrgSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gUserInfo_organisationsUserOrgSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gUserInfo_organisationsUserOrgSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageUserInfo_organisationsUserOrgRecords(e)
{
    getCurrentPageSearchResults(e, UserInfo_organisationsUserOrgSearchResultsDivName, fetchUserInfo_organisationsUserOrgSearchResultsList);
}
function showPreviousUserInfo_organisationsUserOrgRecords()
{
    getPreviousPageSearchResults(UserInfo_organisationsUserOrgSearchResultsDivName, fetchUserInfo_organisationsUserOrgSearchResultsList);
}
function showNextUserInfo_organisationsUserOrgRecords()
{
    getNextPageSearchResults(UserInfo_organisationsUserOrgSearchResultsDivName, fetchUserInfo_organisationsUserOrgSearchResultsList);
}
function showPrevOrNextSearchUserInfo_organisationsUserOrgResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousUserInfo_organisationsUserOrgRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextUserInfo_organisationsUserOrgRecords();
    }
}
function setUserInfo_organisationsUserOrg(organisationsRowElement) 
{
    var organisationsDataObject  = $(organisationsRowElement).data('organisations');
	var organisationsId = organisationsDataObject['organisationsId'];
	var parentElement = $(organisationsRowElement).parents('[data-name="OrganisationsPopup_UserInfo_organisationsUserOrg"]');
	var linkElement = parentElement.data("selected-element");
	linkElement.data("organisations-id", organisationsId);
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
			inputElement.val(organisationsDataObject[lookupDisplayValueName]);		
		}
		var entityName = "UserInfo_organisationsUserOrg";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+organisationsId+")";
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
function lookupRowSelectedForUserInfo(attributeName, attributeId)
{
	var userInfo = getDataForUserInfo();
	userInfo['attributeName'] = attributeName;
	userInfo['attributeId'] = attributeId;
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userInfo = responseData['userInfo'];
            	setUserInfoData(userInfo);
            }
        }
    });	
}
function selectOptionChangedForUserInfo(attributeName)
{
	var userInfo = getDataForUserInfo();
	userInfo['attributeName'] = attributeName;
    var userInfoJsonData = {'paramsInfo': JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userInfo = responseData['userInfo'];
            	setUserInfoData(userInfo);
            	doAfterUserInfoPanelRefreshed();
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
function retrieveDependentUserInfoList(paramsMap)
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
    var searchJsonData = {'objectType' : 'UserInfo', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var userInfoList = responseData['userInfoList'];
            	var userInfoListElement = $("[data-name='userInfoList']");
            	var previousDataRowList  = userInfoListElement.find('[data-name="userInfoRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = userInfoListElement.find('[data-name="userInfoRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//userInfoListElement.empty();
            	for(var i=0; i<userInfoList.length; i++)
        		{
            		var userInfoDataObject = userInfoList[i];
            		//var resultRowTemplate = $(gUserInfoSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var userFirstName = userInfoDataObject['userFirstName'];            		
				    resultRowTemplate.find("[data-name='userFirstName']").text(userFirstName);
					var userLastName = userInfoDataObject['userLastName'];            		
				    resultRowTemplate.find("[data-name='userLastName']").text(userLastName);
					var loginId = userInfoDataObject['loginId'];            		
				    resultRowTemplate.find("[data-name='loginId']").text(loginId);
					var loginEmailId = userInfoDataObject['loginEmailId'];            		
				    resultRowTemplate.find("[data-name='loginEmailId']").text(loginEmailId);
					var contactNo = userInfoDataObject['contactNo'];            		
				    resultRowTemplate.find("[data-name='contactNo']").text(contactNo);
					var loginPassword = userInfoDataObject['loginPassword'];            		
				    resultRowTemplate.find("[data-name='loginPassword']").text(loginPassword);

					
										var organisationsUserOrg = userInfoDataObject['organisationsUserOrgDisplayVal'];            		
				    resultRowTemplate.find("[data-name='organisationsUserOrg']").text(organisationsUserOrg);

            	    resultRowTemplate.data('userInfo', userInfoDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    userInfoListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForUserInfo(customEventName)
{
	var userInfoId = document.getElementById('idValueForUserInfo').value;
	var userInfo = {'userInfoId':userInfoId, 'customEventName':customEventName};
    var userInfoJsonData = {'paramsInfo':JSON.stringify(userInfo), 'objectType' : 'UserInfo'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForUserInfoExt(customEventName);
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userInfo = responseData['userInfo'];
        		setUserInfoInViewPage(userInfo);
            }
    		doAfterExecuteCustomAPIForUserInfoExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnUserInfo(e, paramsMap)
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
	var userInfoId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var userInfoDataObject  = rowObj.data('userInfo');
    	userInfoId = userInfoDataObject['userInfoId'];
	}
    else
	{
    	userInfoId = document.getElementById('idValueForUserInfo').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var userInfoSearchParams = {'userInfoId':userInfoId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	userInfoSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var userInfoJsonData = {'paramsInfo':JSON.stringify(userInfoSearchParams),  'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userInfo = responseData['userInfo'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="userInfoRowActionButtonDiv"]').hide();
            		if(userInfo.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = userInfo['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(userInfo['vwTxnStatus']);
            			selectedRowObj.find('[data-name="userInfoActionButton"]').text(userInfo["nextAction"]);
            			selectedRowObj.find('[data-name="userInfoActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setUserInfoInViewPage(userInfo);
    			}
            }
        }
    });	
}
function downloadUserInfoData()
{		
	var userInfo = {};
    var userInfoJsonData = {'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=UserInfo");
            }
        }
    });
}
function uploadUserInfoData(fileInfo)
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
	var userInfo = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var userInfoJsonData = {'paramsInfo':JSON.stringify(userInfo),  'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=UserInfo");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForUserInfo()
{
    var userInfoJsonData = {'objectType' : 'UserInfo'};
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
        data: userInfoJsonData,
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



function doUserInfoLengthValidation(userInfoDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(userInfoDataObject['userFirstName'], 50))
	{
		alertMessage += "\n First Name length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(userInfoDataObject['userLastName'], 50))
	{
		alertMessage += "\n Last Name length is more than allowed(50)";
	    validationPassed = false;
	}
  
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	if(!isFieldLengthAllowed(userInfoDataObject['loginId'], 50))
	{
		alertMessage += "\n Login Id length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(userInfoDataObject['loginEmailId'], 100))
	{
		alertMessage += "\n Email ID length is more than allowed(100)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(userInfoDataObject['contactNo'], 20))
	{
		alertMessage += "\n Contact No length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(userInfoDataObject['loginPassword'], 100))
	{
		alertMessage += "\n Login Password length is more than allowed(100)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doUserInfoManadatoryValidation(userInfoDataObject, paramsMap)
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
		
	var userFirstName = userInfoDataObject['userFirstName'];
	if(!userFirstName || userFirstName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"UserInfo_userFirstName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter First Name";
		    validationPassed = false;
		}
	}
	
	var userLastName = userInfoDataObject['userLastName'];
	if(!userLastName || userLastName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"UserInfo_userLastName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Last Name";
		    validationPassed = false;
		}
	}
	
	var loginId = userInfoDataObject['loginId'];
	if(!loginId || loginId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"UserInfo_loginId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Login Id";
		    validationPassed = false;
		}
	}
	
	var loginEmailId = userInfoDataObject['loginEmailId'];
	if(!loginEmailId || loginEmailId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"UserInfo_loginEmailId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Email ID";
		    validationPassed = false;
		}
	}
	
	var loginPassword = userInfoDataObject['loginPassword'];
	if(!loginPassword || loginPassword.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"UserInfo_loginPassword").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Login Password";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doUserInfoValidation(userInfoDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doUserInfoLengthValidation(userInfoDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doUserInfoManadatoryValidation(userInfoDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
