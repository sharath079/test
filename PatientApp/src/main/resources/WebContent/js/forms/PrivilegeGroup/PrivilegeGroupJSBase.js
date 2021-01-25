/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'PrivilegeGroupName' : 'FormWBEntity:PrivilegeGroup_privilegeGroupName' 
 *	 * 'PrivilegeCode' : 'FormWBEntity:PrivilegeGroup_privilegeCode' 
 *	 * 'ApplicableUserType' : 'FormWBEntity:PrivilegeGroup_applicableUserType' 
 *	 * 'SelfServiceUser' : 'FormWBEntity:PrivilegeGroup_selfServiceUser' 
 *	 * 'PrivilegeGroupDescription' : 'FormWBEntity:PrivilegeGroup_privilegeGroupDescription' 
 *	
 */
var gInitParamsObjForPrivilegeGroup = {};
var gPrivilegeGroupRequestUnderProcess = 0;
function selectThisPrivilegeGroupForEdit(privilegeGroupRowElement)
{
    var privilegeGroupDataObject  = $(privilegeGroupRowElement).data('privilegeGroup');
    var privilegeGroupList = $('[data-name="privilegeGroupSearchResults"]').find('[data-name="privilegeGroupRow"]');
	privilegeGroupList.data("is-selected", 0);	
	$(privilegeGroupRowElement).data("is-selected", 1);
	setPrivilegeGroupInViewPage(privilegeGroupDataObject);
}

function setPrivilegeGroupInViewPage(privilegeGroupDataObject, paramsMap)
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
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	$('#'+prefix+'idValueForPrivilegeGroup').val(privilegeGroupId);
		
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupName'))
	{
		var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeGroupName').val(privilegeGroupName);
	}
	
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('privilegeCode'))
	{
		var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeCode').val(privilegeCode);
	}
	
	//Combo
	if(privilegeGroupDataObject.hasOwnProperty('applicableUserType'))
	{
		var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
		$('#'+prefix+'PrivilegeGroup_applicableUserType').val(applicableUserType)
	}
	
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('selfServiceUser'))
	{
		var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
		$('#'+prefix+'PrivilegeGroup_selfServiceUser').val(selfServiceUser);
	}
	
	//Text
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupDescription'))
	{
		var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeGroupDescription').val(privilegeGroupDescription);
	}

	if(privilegeGroupDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = privilegeGroupDataObject['vwTxnStatus'];
		$('[data-name="privilegeGroupActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnPrivilegeGroup(this)" >' +privilegeGroupDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="privilegeGroupActionButtonDiv"]').append(buttonElement);
		$('[data-name="privilegeGroupActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="privilegeGroupActionButtonDiv"]').hide();
	}
	$('[data-name="privilegeGroupCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="privilegeGroupViewFormButtonsDiv"]').css("display", "inline");
	//loadPrivilegeGroupViewData(privilegeGroupDataObject);
	disbalePrivilegeGroupUpdateDisallowedFields(paramsMap);
	doAfterPrivilegeGroupPanelRefreshed();
    
    resetFormForPrivilegeGroupItems();
    resetTableForPrivilegeGroupItems();
    
        retrieveDependentPrivilegeGroupItemsList(paramsMap);

}
function disbalePrivilegeGroupUpdateDisallowedFields(paramsMap)
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
function loadPrivilegeGroupViewData(privilegeGroupDataObject, paramsMap)
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
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	$('#'+prefix+'idValueForPrivilegeGroup').val(privilegeGroupId);
		
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupName'))
	{
		var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroup_privilegeGroupName"]').text(privilegeGroupName);
	}
	
	if(privilegeGroupDataObject.hasOwnProperty('privilegeCode'))
	{
		var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroup_privilegeCode"]').text(privilegeCode);
	}
	
	if(privilegeGroupDataObject.hasOwnProperty('applicableUserType'))
	{
		var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroup_applicableUserType"]').text(applicableUserType);
	}
	
	if(privilegeGroupDataObject.hasOwnProperty('selfServiceUser'))
	{
		var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroup_selfServiceUser"]').text(selfServiceUser);
	}
	
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupDescription'))
	{
		var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroup_privilegeGroupDescription"]').text(privilegeGroupDescription);
	}

}
function ajaxDemoForPrivilegeGroup()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForPrivilegeGroup').textContent;	
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
			refreshPanelForPrivilegeGroup();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForPrivilegeGroup(msg)
{
	var executeCustomAPIButtonForPrivilegeGroup = document.getElementById("FormWBEntity:executeCustomAPIButtonForPrivilegeGroup");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:PrivilegeGroup_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForPrivilegeGroup.click();
}
function refreshPanelForPrivilegeGroup()
{
	var demoRefreshButtonForPrivilegeGroup = document.getElementById("FormWBEntity:demoRefreshButtonPrivilegeGroup");
	demoRefreshButtonForPrivilegeGroup.click();
}
function initializePanelOnLoadForCreatePrivilegeGroup(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForPrivilegeGroup = initParamsObj;
	initializePrivilegeGroupPage();	
	doAfterPrivilegeGroupPanelRefreshed();
	initializePrivilegeGroupLookupFields(initParamsObj);
	doAfterPanelInitializedForPrivilegeGroupExt();
	fetchPrivilegeGroupDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="privilegeGroupCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForPrivilegeGroup = 0;
function initializePanelOnLoadForSearchPrivilegeGroup()
{
	initializePrivilegeGroupPage();	
	initializePrivilegeGroupLookupFields();
	doAfterPanelInitializedForPrivilegeGroupExt();
	gLoadDashboardResultsForPrivilegeGroup =1;
	//retrievePrivilegeGroupList();
}
function initializePanelOnLoadForViewPrivilegeGroup(urlParams)
{
	initializePrivilegeGroupPage();	
	doAfterPrivilegeGroupPanelRefreshed();
	initializePrivilegeGroupLookupFields(urlParams);
	doAfterPanelInitializedForPrivilegeGroupExt();
	retrievePrivilegeGroup(urlParams);
	initDatePicker();
	$('[data-name="privilegeGroupViewFormButtonsDiv"]').css("display", "inline");
}
function initializePrivilegeGroupPage()
{
	initializePageOnload();	
	//initializePrivilegeGroupTemplateVariables();
}
function initializePrivilegeGroupTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="privilegeGroupSearchResults"]').find('[data-name="privilegeGroupRow"]');
	if(searchResultsRowObj.length > 0 && gPrivilegeGroupSearchResultsTableRowTemplate.length == 0)
	{
		gPrivilegeGroupSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	    initializePrivilegeGroupItemsTemplateVariables();

}
function retrievePrivilegeGroup(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var privilegeGroupId = paramsMap['privilegeGroupId'];
	var privilegeGroup = {};
	privilegeGroup['privilegeGroupId'] = privilegeGroupId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				privilegeGroup[key] = paramsMap[key];
			}
	}
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
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
                	var privilegeGroupDataObject = responseData['privilegeGroupDataObject'];
    				setPrivilegeGroupInViewPage(privilegeGroupDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForPrivilegeGroup()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchPrivilegeGroupDefaultData();
}
function fetchPrivilegeGroupDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'PrivilegeGroup', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var privilegeGroup = responseData['privilegeGroup'];
            	document.getElementById('idValueForPrivilegeGroup').value = '';
			    
			    resetFormForPrivilegeGroupItems();
			    resetTableForPrivilegeGroupItems();
			    
            	setPrivilegeGroupData(privilegeGroup);
            }
        }
    });	
}
function fetchPrivilegeGroupTestData() 
{
	var privilegeGroup = getDataForPrivilegeGroup();
    var searchJsonData = {'objectType' : 'PrivilegeGroup', 'paramsInfo' : JSON.stringify(privilegeGroup)};
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
            	var privilegeGroup = responseData['privilegeGroup'];
            	document.getElementById('idValueForPrivilegeGroup').value = '';
			    
			    resetFormForPrivilegeGroupItems();
			    resetTableForPrivilegeGroupItems();
			    
            	setPrivilegeGroupData(privilegeGroup);
            }
        }
    });	
}
function setPrivilegeGroupData(privilegeGroupDataObject)
{
	var prefix = "";
		
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupName'))
	{
		var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeGroupName').val(privilegeGroupName);
	}
	
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('privilegeCode'))
	{
		var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeCode').val(privilegeCode);
	}
	
	//Combo
	if(privilegeGroupDataObject.hasOwnProperty('applicableUserType'))
	{
		var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
		$('#'+prefix+'PrivilegeGroup_applicableUserType').val(applicableUserType);
	}
	
	//Input
	if(privilegeGroupDataObject.hasOwnProperty('selfServiceUser'))
	{
		var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
		$('#'+prefix+'PrivilegeGroup_selfServiceUser').val(selfServiceUser);
	}
	
	//Text
	if(privilegeGroupDataObject.hasOwnProperty('privilegeGroupDescription'))
	{
		var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		
		$('#'+prefix+'PrivilegeGroup_privilegeGroupDescription').val(privilegeGroupDescription);
	}

	$('[data-name="privilegeGroupActionButtonDiv"]').hide();
}
function initializePrivilegeGroupLookupFields(paramsMap) 
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
    
    initializePrivilegeGroupItemsLookupSelectList();
    
    var searchDiv = $('[data-name="privilegeGroupSearchResultsDiv"]');
	
    
}

function doAfterPrivilegeGroupPanelRefreshed()
{
    //doAfterPanelRefreshedForPrivilegeGroupExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForPrivilegeGroup(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'applicableUserType')
	{
	}

	doAfterSelectOptionChangedForPrivilegeGroupExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForPrivilegeGroup(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForPrivilegeGroupExt(fieldName)
}
function getEntityIdForPrivilegeGroup()
{
	var idValue = document.getElementById('FormWBEntity:idValueForPrivilegeGroup').textContent;	
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
function openPrintPageForPrivilegeGroup()
{
	var entityId = getEntityIdForPrivilegeGroup();
	if(entityId>0)
	{
	    window.open("/reports/generated/PrivilegeGroup.jsp?privilegeGroupId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForPrivilegeGroup(paramsMap)
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
	var privilegeGroup = {};
		
	//Input
	if($("#"+prefix+"PrivilegeGroup_privilegeGroupName").length == 1)
	{
		var privilegeGroupName = $('#'+prefix+'PrivilegeGroup_privilegeGroupName').val();
		privilegeGroup['privilegeGroupName'] = privilegeGroupName;
	}
	
	//Input
	if($("#"+prefix+"PrivilegeGroup_privilegeCode").length == 1)
	{
		var privilegeCode = $('#'+prefix+'PrivilegeGroup_privilegeCode').val();
		privilegeGroup['privilegeCode'] = privilegeCode;
	}
	
	//Combo
	if($("#"+prefix+"PrivilegeGroup_applicableUserType").length == 1)
	{
		var applicableUserType = $('#'+prefix+'PrivilegeGroup_applicableUserType').val();
		privilegeGroup['applicableUserType'] = applicableUserType;
	}
	
	//Input
	if($("#"+prefix+"PrivilegeGroup_selfServiceUser").length == 1)
	{
		var selfServiceUser = $('#'+prefix+'PrivilegeGroup_selfServiceUser').val();
		privilegeGroup['selfServiceUser'] = selfServiceUser;
	}
	
	//Text
	if($("#"+prefix+"PrivilegeGroup_privilegeGroupDescription").length == 1)
	{
		var privilegeGroupDescription = $('#'+prefix+'PrivilegeGroup_privilegeGroupDescription').val();
		privilegeGroup['privilegeGroupDescription'] = privilegeGroupDescription;
	}

	
	return privilegeGroup;
}
function createPrivilegeGroup(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var privilegeGroup = getDataForPrivilegeGroup(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				privilegeGroup[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForPrivilegeGroup)
	{
		paramsInfo[key] = gInitParamsObjForPrivilegeGroup[key];
	}
	var validationObject = doPrivilegeGroupValidation(privilegeGroup, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	privilegeGroup['additionalParamsInfo'] = paramsInfo;
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
	var urlContextPath = "";//getContextPath();
	if(gPrivilegeGroupRequestUnderProcess == 1)
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
            	gPrivilegeGroupRequestUnderProcess = 0;
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gPrivilegeGroupRequestUnderProcess = 0;
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
            	var privilegeGroupId = responseData['privilegeGroupId'];
            	var popupElement = $('[data-name="PrivilegeGroupPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var privilegeGroupDataObject = responseData['privilegeGroupDataObject'];
    				setPrivilegeGroupInViewPage(privilegeGroupDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewPrivilegeGroup.html?privilegeGroupId="+privilegeGroupId; 
        		}
				
            }
        }
    });
}
function resetTableForPrivilegeGroup()
{
	var privilegeGroupListElement = $("[data-name='privilegeGroupList']");
	var previousDataRowList  = privilegeGroupListElement.find('[data-name="privilegeGroupRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForPrivilegeGroup(paramsMap)
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
	$('#'+prefix+'idValueForPrivilegeGroup').val('');
		
	//Input
	$('#'+prefix+'PrivilegeGroup_privilegeGroupName').val('');
	
	//Input
	$('#'+prefix+'PrivilegeGroup_privilegeCode').val('');
	
	//Combo
	$('#'+prefix+'PrivilegeGroup_applicableUserType').val('');
	
	//Input
	$('#'+prefix+'PrivilegeGroup_selfServiceUser').val('');
	
	//Text
	$('#'+prefix+'PrivilegeGroup_privilegeGroupDescription').val('');

	$('[data-name="privilegeGroupCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="privilegeGroupViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="privilegeGroupActionButtonDiv"]').hide();
	enablePrivilegeGroupUpdateDisallowedFields(paramsMap);
    
    resetFormForPrivilegeGroupItems();
    resetTableForPrivilegeGroupItems();
    
}
function enablePrivilegeGroupUpdateDisallowedFields(paramsMap)
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
function updatePrivilegeGroup(paramsMap)
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
	var privilegeGroup = getDataForPrivilegeGroup(paramsMap)
	if($("#"+prefix+"idValueForPrivilegeGroup").length == 1)
	{
		var privilegeGroupId = $("#"+prefix+"idValueForPrivilegeGroup").val();
		privilegeGroup['privilegeGroupId'] = privilegeGroupId;
	}
	var validationObject = doPrivilegeGroupValidation(privilegeGroup, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
	var urlContextPath = "";//getContextPath();
	if(gPrivilegeGroupRequestUnderProcess == 1)
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
                    	gPrivilegeGroupRequestUnderProcess = 0;
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gPrivilegeGroupRequestUnderProcess = 0;
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
function deletePrivilegeGroup(paramsMap)
{		
	var privilegeGroupId = document.getElementById('idValueForPrivilegeGroup').value;
	deleteSelectedPrivilegeGroup(privilegeGroupId, paramsMap);
}
function deleteSelectedPrivilegeGroup(privilegeGroupId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var privilegeGroup = {};
	privilegeGroup['privilegeGroupId'] = privilegeGroupId;	
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
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
function loadSearchResultsForPrivilegeGroup()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'PrivilegeGroup'};
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
            	var privilegeGroupSearchResultsElement = $("[data-name='privilegeGroupSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var privilegeGroupDataObject = searchResultObjectsList[i];
					            		var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
            		var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
            		var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
            		var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
            		var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		

            		var resultRowTemnplate = $(gPrivilegeGroupSearchResultsTableRowTemplate);
					            		var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
            	    resultRowTemnplate.find("[data-name='privilegeGroupName']").text(privilegeGroupName);
            		var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
            	    resultRowTemnplate.find("[data-name='privilegeCode']").text(privilegeCode);
            		var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
            	    resultRowTemnplate.find("[data-name='applicableUserType']").text(applicableUserType);
            		var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
            	    resultRowTemnplate.find("[data-name='selfServiceUser']").text(selfServiceUser);
            		var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		
            	    resultRowTemnplate.find("[data-name='privilegeGroupDescription']").text(privilegeGroupDescription);

            	    resultRowTemnplate.data('privilegeGroup', privilegeGroupDataObject);
            	    privilegeGroupSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gPrivilegeGroupSearchResultsTableRowTemplate = ""; 
function openViewPageForPrivilegeGroupForEdit(privilegeGroupLinkElement)
{
	var privilegeGroupRowElement = $(privilegeGroupLinkElement).parents('[data-name="privilegeGroupRow"]');
    var privilegeGroupDataObject  = privilegeGroupRowElement.data('privilegeGroup');
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	var privilegeGroupPopupElement = $('[data-name="PrivilegeGroupPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setPrivilegeGroupInViewPage(privilegeGroupDataObject);
	    $("#PrivilegeGroup-tab").trigger("click");
	}
	else if(privilegeGroupPopupElement.length > 0)
	{
	    setPrivilegeGroupInViewPage(privilegeGroupDataObject);
		$('[data-name="PrivilegeGroupPopup"]').find('[data-name="privilegeGroupCreateFormButtonsDiv"]').hide();
		$('[data-name="PrivilegeGroupPopup"]').find('[data-name="privilegeGroupViewFormButtonsDiv"]').show();
	    showPopup('PrivilegeGroupPopup', privilegeGroupLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewPrivilegeGroup.html?privilegeGroupId="+privilegeGroupId;
		window.open(viewLink, "_blank"); 	
	}
}
function openPrivilegeGroupCreatePageForNew(linkElement)
{
	var privilegeGroupPopupElement = $('[data-name="PrivilegeGroupPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForPrivilegeGroup();
	    $("#PrivilegeGroup-tab").trigger("click");
    }
	else if(privilegeGroupPopupElement.length > 0)
	{
		resetFormForPrivilegeGroup();
	    showPopup('PrivilegeGroupPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreatePrivilegeGroup.html";
    }
}
function showPrivilegeGroupPopupForEdit(privilegeGroupLinkElement)
{
	var privilegeGroupRowElement = $(privilegeGroupLinkElement).parents('[data-name="privilegeGroupRow"]');
    var privilegeGroupDataObject  = privilegeGroupRowElement.data('privilegeGroup');
    setPrivilegeGroupInViewPage(privilegeGroupDataObject);
    showPopup('PrivilegeGroupPopup', privilegeGroupLinkElement, 1);
    $("#PrivilegeGroup-tab").trigger("click");
}
function showPrivilegeGroupPopupForNew(buttonElement)
{
	resetFormForPrivilegeGroup();
    showPopup('PrivilegeGroupPopup', buttonElement, 1);
    $("#PrivilegeGroup-tab").trigger("click");
}
function deleteThisPrivilegeGroup(privilegeGroupLinkElement, paramsMap)
{
	var privilegeGroupRowElement = $(privilegeGroupLinkElement).parents('[data-name="privilegeGroupRow"]');
    var privilegeGroupDataObject  = privilegeGroupRowElement.data('privilegeGroup');
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	deleteSelectedPrivilegeGroup(privilegeGroupId, paramsMap);
}
function displayPrivilegeGroupList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var privilegeGroupSearchResults = searchResultsDiv.find('[data-name="privilegeGroupSearchResults"]');
	//privilegeGroupSearchResults.find('[data-name="privilegeGroupRow"]').remove();
	var previousDataRowList  = privilegeGroupSearchResults.find('[data-name="privilegeGroupRow"]');
	privilegeGroupSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = privilegeGroupSearchResults.find('[data-name="privilegeGroupRow"]');
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
        var privilegeGroupDataObject = searchResultObjectsList[i];
        loadPrivilegeGroupViewData(privilegeGroupDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = privilegeGroupDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='privilegeGroupVwTxnStatus']").text(vwTxnStatus);
		if(privilegeGroupDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="privilegeGroupActionButton"]').text(privilegeGroupDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="privilegeGroupActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="privilegeGroupActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="privilegeGroupRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="privilegeGroupRejectButton"]').show();
		}
	    resultRowTemplate.data('privilegeGroup', privilegeGroupDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    privilegeGroupSearchResults.append(resultRowTemplate);
	    processResultRowForPrivilegeGroupExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForPrivilegeGroup == 1)
	{
    	getDashboardResultsForPrivilegeGroup();
	}
}
var privilegeGroupSearchResultsDivName = "privilegeGroupSearchResultsDiv";
var gPrivilegeGroupSearchInputParamsList = [];
function retrievePrivilegeGroupList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + privilegeGroupSearchResultsDivName + '"]');
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
    fetchPrivilegeGroupSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchPrivilegeGroupSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getPrivilegeGroupSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupSearchResultsDivName; 
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
        'objectType': "PrivilegeGroup"
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
            	this.showPreviousRecords = "showPreviousPrivilegeGroupRecords";
            	this.showCurrentPageRecords = "showCurrentPagePrivilegeGroupRecords";
            	this.showNextRecords = "showNextPrivilegeGroupRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchPrivilegeGroupResults";
                var privilegeGroupList = responseData['privilegeGroupList'];
                var currContextObj = this; 
                var successCallbackFunction = displayPrivilegeGroupList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'privilegeGroupList', 'matchingSearchResultsCount', this.searchResultsDivName, 'privilegeGroupSearchResults', 'privilegeGroupRow', setPrivilegeGroupSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getPrivilegeGroupSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupName"]').length == 1)
		{
		    var privilegeGroupName = searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupName', 'userInputValue':privilegeGroupName});
		}
		
		//Input
		if(searchDiv.find('[data-name="privilegeGroupDB_privilegeCode"]').length == 1)
		{
		    var privilegeCode = searchDiv.find('[data-name="privilegeGroupDB_privilegeCode"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeCode', 'userInputValue':privilegeCode});
		}
		
		//Combo
		if(searchDiv.find('[data-name="privilegeGroupDB_applicableUserType"]').length == 1)
		{
		    var applicableUserType = searchDiv.find('[data-name="privilegeGroupDB_applicableUserType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'applicableUserType', 'userInputValue':applicableUserType});
		}
		
		//Input
		if(searchDiv.find('[data-name="privilegeGroupDB_selfServiceUser"]').length == 1)
		{
		    var selfServiceUser = searchDiv.find('[data-name="privilegeGroupDB_selfServiceUser"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'selfServiceUser', 'userInputValue':selfServiceUser});
		}
		
		//Text
		if(searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupDescription"]').length == 1)
		{
		    var privilegeGroupDescription = searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupDescription"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupDescription', 'userInputValue':privilegeGroupDescription});
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
        gPrivilegeGroupSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gPrivilegeGroupSearchInputParamsList;
    }
    return searchInputParams;
}
function setPrivilegeGroupSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gPrivilegeGroupSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gPrivilegeGroupSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPagePrivilegeGroupRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchPrivilegeGroupSearchResultsList);
}
function showPreviousPrivilegeGroupRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchPrivilegeGroupSearchResultsList);
}
function showNextPrivilegeGroupRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchPrivilegeGroupSearchResultsList);
}
function showPrevOrNextSearchPrivilegeGroupResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousPrivilegeGroupRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextPrivilegeGroupRecords(e);
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
function lookupRowSelectedForPrivilegeGroup(attributeName, attributeId)
{
	var privilegeGroup = getDataForPrivilegeGroup();
	privilegeGroup['attributeName'] = attributeName;
	privilegeGroup['attributeId'] = attributeId;
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroup = responseData['privilegeGroup'];
            	setPrivilegeGroupData(privilegeGroup);
            }
        }
    });	
}
function selectOptionChangedForPrivilegeGroup(attributeName)
{
	var privilegeGroup = getDataForPrivilegeGroup();
	privilegeGroup['attributeName'] = attributeName;
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroup = responseData['privilegeGroup'];
            	setPrivilegeGroupData(privilegeGroup);
            	doAfterPrivilegeGroupPanelRefreshed();
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
function retrieveDependentPrivilegeGroupList(paramsMap)
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
    var searchJsonData = {'objectType' : 'PrivilegeGroup', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var privilegeGroupList = responseData['privilegeGroupList'];
            	var privilegeGroupListElement = $("[data-name='privilegeGroupList']");
            	var previousDataRowList  = privilegeGroupListElement.find('[data-name="privilegeGroupRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = privilegeGroupListElement.find('[data-name="privilegeGroupRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//privilegeGroupListElement.empty();
            	for(var i=0; i<privilegeGroupList.length; i++)
        		{
            		var privilegeGroupDataObject = privilegeGroupList[i];
            		//var resultRowTemplate = $(gPrivilegeGroupSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];            		
				    resultRowTemplate.find("[data-name='privilegeGroupName']").text(privilegeGroupName);
					var privilegeCode = privilegeGroupDataObject['privilegeCode'];            		
				    resultRowTemplate.find("[data-name='privilegeCode']").text(privilegeCode);
					var applicableUserType = privilegeGroupDataObject['applicableUserType'];            		
				    resultRowTemplate.find("[data-name='applicableUserType']").text(applicableUserType);
					var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];            		
				    resultRowTemplate.find("[data-name='selfServiceUser']").text(selfServiceUser);
					var privilegeGroupDescription = privilegeGroupDataObject['privilegeGroupDescription'];            		
				    resultRowTemplate.find("[data-name='privilegeGroupDescription']").text(privilegeGroupDescription);

					
					
            	    resultRowTemplate.data('privilegeGroup', privilegeGroupDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    privilegeGroupListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForPrivilegeGroup(customEventName)
{
	var privilegeGroupId = document.getElementById('idValueForPrivilegeGroup').value;
	var privilegeGroup = {'privilegeGroupId':privilegeGroupId, 'customEventName':customEventName};
    var privilegeGroupJsonData = {'paramsInfo':JSON.stringify(privilegeGroup), 'objectType' : 'PrivilegeGroup'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForPrivilegeGroupExt(customEventName);
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroup = responseData['privilegeGroup'];
        		setPrivilegeGroupInViewPage(privilegeGroup);
            }
    		doAfterExecuteCustomAPIForPrivilegeGroupExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnPrivilegeGroup(e, paramsMap)
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
	var privilegeGroupId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var privilegeGroupDataObject  = rowObj.data('privilegeGroup');
    	privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	}
    else
	{
    	privilegeGroupId = document.getElementById('idValueForPrivilegeGroup').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var privilegeGroupSearchParams = {'privilegeGroupId':privilegeGroupId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	privilegeGroupSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var privilegeGroupJsonData = {'paramsInfo':JSON.stringify(privilegeGroupSearchParams),  'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroup = responseData['privilegeGroup'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="privilegeGroupRowActionButtonDiv"]').hide();
            		if(privilegeGroup.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = privilegeGroup['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(privilegeGroup['vwTxnStatus']);
            			selectedRowObj.find('[data-name="privilegeGroupActionButton"]').text(privilegeGroup["nextAction"]);
            			selectedRowObj.find('[data-name="privilegeGroupActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setPrivilegeGroupInViewPage(privilegeGroup);
    			}
            }
        }
    });	
}
function downloadPrivilegeGroupData()
{		
	var privilegeGroup = {};
    var privilegeGroupJsonData = {'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=PrivilegeGroup");
            }
        }
    });
}
function uploadPrivilegeGroupData(fileInfo)
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
	var privilegeGroup = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var privilegeGroupJsonData = {'paramsInfo':JSON.stringify(privilegeGroup),  'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=PrivilegeGroup");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForPrivilegeGroup()
{
    var privilegeGroupJsonData = {'objectType' : 'PrivilegeGroup'};
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
        data: privilegeGroupJsonData,
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



function doPrivilegeGroupLengthValidation(privilegeGroupDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(privilegeGroupDataObject['privilegeGroupName'], 50))
	{
		alertMessage += "\n Privilege Group Name length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupDataObject['privilegeCode'], 50))
	{
		alertMessage += "\n Privilege Code length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupDataObject['applicableUserType'], 50))
	{
		alertMessage += "\n Applicable User Type length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupDataObject['selfServiceUser'], 50))
	{
		alertMessage += "\n Self Service User length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupDataObject['privilegeGroupDescription'], 50))
	{
		alertMessage += "\n Privilege Group Description length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPrivilegeGroupManadatoryValidation(privilegeGroupDataObject, paramsMap)
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
		
	var privilegeGroupName = privilegeGroupDataObject['privilegeGroupName'];
	if(!privilegeGroupName || privilegeGroupName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroup_privilegeGroupName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Privilege Group Name";
		    validationPassed = false;
		}
	}
	
	var privilegeCode = privilegeGroupDataObject['privilegeCode'];
	if(!privilegeCode || privilegeCode.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroup_privilegeCode").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Privilege Code";
		    validationPassed = false;
		}
	}
	
	var applicableUserType = privilegeGroupDataObject['applicableUserType'];
	if(!applicableUserType || applicableUserType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroup_applicableUserType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Applicable User Type";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPrivilegeGroupValidation(privilegeGroupDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doPrivilegeGroupLengthValidation(privilegeGroupDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doPrivilegeGroupManadatoryValidation(privilegeGroupDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
