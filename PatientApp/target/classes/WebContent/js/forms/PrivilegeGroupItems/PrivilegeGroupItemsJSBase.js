/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'PrivilegeActionType' : 'FormWBEntity:PrivilegeGroupItems_privilegeActionType' 
 *	 * 'PrivilegeObjectType' : 'FormWBEntity:PrivilegeGroupItems_privilegeObjectType' 
 *	 * 'PrivilegeObjectName' : 'FormWBEntity:PrivilegeGroupItems_privilegeObjectName' 
 *	
 */
var gInitParamsObjForPrivilegeGroupItems = {};
var gPrivilegeGroupItemsRequestUnderProcess = 0;
function selectThisPrivilegeGroupItemsForEdit(privilegeGroupItemsRowElement)
{
    var privilegeGroupItemsDataObject  = $(privilegeGroupItemsRowElement).data('privilegeGroupItems');
    var privilegeGroupItemsList = $('[data-name="privilegeGroupItemsSearchResults"]').find('[data-name="privilegeGroupItemsRow"]');
	privilegeGroupItemsList.data("is-selected", 0);	
	$(privilegeGroupItemsRowElement).data("is-selected", 1);
	setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject);
}

function setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject, paramsMap)
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
	var privilegeGroupItemsId = privilegeGroupItemsDataObject['privilegeGroupItemsId'];
	$('#'+prefix+'idValueForPrivilegeGroupItems').val(privilegeGroupItemsId);
		
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeActionType'))
	{
		var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeActionType').val(privilegeActionType);
	}
	
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectType'))
	{
		var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeObjectType').val(privilegeObjectType);
	}
	
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectName'))
	{
		var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeObjectName').val(privilegeObjectName);
	}

	if(privilegeGroupItemsDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = privilegeGroupItemsDataObject['vwTxnStatus'];
		$('[data-name="privilegeGroupItemsActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnPrivilegeGroupItems(this)" >' +privilegeGroupItemsDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="privilegeGroupItemsActionButtonDiv"]').append(buttonElement);
		$('[data-name="privilegeGroupItemsActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="privilegeGroupItemsActionButtonDiv"]').hide();
	}
	$('[data-name="privilegeGroupItemsCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="privilegeGroupItemsViewFormButtonsDiv"]').css("display", "inline");
	//loadPrivilegeGroupItemsViewData(privilegeGroupItemsDataObject);
	disbalePrivilegeGroupItemsUpdateDisallowedFields(paramsMap);
	doAfterPrivilegeGroupItemsPanelRefreshed();
    
    
}
function disbalePrivilegeGroupItemsUpdateDisallowedFields(paramsMap)
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
function loadPrivilegeGroupItemsViewData(privilegeGroupItemsDataObject, paramsMap)
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
	var privilegeGroupItemsId = privilegeGroupItemsDataObject['privilegeGroupItemsId'];
	$('#'+prefix+'idValueForPrivilegeGroupItems').val(privilegeGroupItemsId);
		
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeActionType'))
	{
		var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroupItems_privilegeActionType"]').text(privilegeActionType);
	}
	
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectType'))
	{
		var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroupItems_privilegeObjectType"]').text(privilegeObjectType);
	}
	
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectName'))
	{
		var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		
		parentElement.find('[data-name="'+prefix+'PrivilegeGroupItems_privilegeObjectName"]').text(privilegeObjectName);
	}

}
function ajaxDemoForPrivilegeGroupItems()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForPrivilegeGroupItems').textContent;	
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
			refreshPanelForPrivilegeGroupItems();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForPrivilegeGroupItems(msg)
{
	var executeCustomAPIButtonForPrivilegeGroupItems = document.getElementById("FormWBEntity:executeCustomAPIButtonForPrivilegeGroupItems");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:PrivilegeGroupItems_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForPrivilegeGroupItems.click();
}
function refreshPanelForPrivilegeGroupItems()
{
	var demoRefreshButtonForPrivilegeGroupItems = document.getElementById("FormWBEntity:demoRefreshButtonPrivilegeGroupItems");
	demoRefreshButtonForPrivilegeGroupItems.click();
}
function initializePanelOnLoadForCreatePrivilegeGroupItems(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForPrivilegeGroupItems = initParamsObj;
	initializePrivilegeGroupItemsPage();	
	doAfterPrivilegeGroupItemsPanelRefreshed();
	initializePrivilegeGroupItemsLookupFields(initParamsObj);
	doAfterPanelInitializedForPrivilegeGroupItemsExt();
	fetchPrivilegeGroupItemsDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="privilegeGroupItemsCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForPrivilegeGroupItems = 0;
function initializePanelOnLoadForSearchPrivilegeGroupItems()
{
	initializePrivilegeGroupItemsPage();	
	initializePrivilegeGroupItemsLookupFields();
	doAfterPanelInitializedForPrivilegeGroupItemsExt();
	gLoadDashboardResultsForPrivilegeGroupItems =1;
	//retrievePrivilegeGroupItemsList();
}
function initializePanelOnLoadForViewPrivilegeGroupItems(urlParams)
{
	initializePrivilegeGroupItemsPage();	
	doAfterPrivilegeGroupItemsPanelRefreshed();
	initializePrivilegeGroupItemsLookupFields(urlParams);
	doAfterPanelInitializedForPrivilegeGroupItemsExt();
	retrievePrivilegeGroupItems(urlParams);
	initDatePicker();
	$('[data-name="privilegeGroupItemsViewFormButtonsDiv"]').css("display", "inline");
}
function initializePrivilegeGroupItemsPage()
{
	initializePageOnload();	
	//initializePrivilegeGroupItemsTemplateVariables();
}
function initializePrivilegeGroupItemsTemplateVariables()
{	
	
	
	var privilegeGroupItemsRowObj = $('[data-name="privilegeGroupItemsList"]').find('[data-name="privilegeGroupItemsRow"]');
	if(privilegeGroupItemsRowObj.length > 0 && gPrivilegeGroupItemsSearchResultsTableRowTemplate.length == 0)
	{
		gPrivilegeGroupItemsSearchResultsTableRowTemplate = privilegeGroupItemsRowObj[0].outerHTML;
		//privilegeGroupItemsRowObj.remove();
	}
	if(gPrivilegeGroupItemsSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="privilegeGroupItemsSearchResults"]').find('[data-name="privilegeGroupItemsRow"]');
		if(searchResultsRowObj.length > 0 && gPrivilegeGroupItemsSearchResultsTableRowTemplate.length == 0)
		{
			gPrivilegeGroupItemsSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrievePrivilegeGroupItems(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var privilegeGroupItemsId = paramsMap['privilegeGroupItemsId'];
	var privilegeGroupItems = {};
	privilegeGroupItems['privilegeGroupItemsId'] = privilegeGroupItemsId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				privilegeGroupItems[key] = paramsMap[key];
			}
	}
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
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
                	var privilegeGroupItemsDataObject = responseData['privilegeGroupItemsDataObject'];
    				setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForPrivilegeGroupItems()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchPrivilegeGroupItemsDefaultData();
}
function fetchPrivilegeGroupItemsDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'PrivilegeGroupItems', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
            	document.getElementById('idValueForPrivilegeGroupItems').value = '';
			    
            	setPrivilegeGroupItemsData(privilegeGroupItems);
            }
        }
    });	
}
function fetchPrivilegeGroupItemsTestData() 
{
	var privilegeGroupItems = getDataForPrivilegeGroupItems();
    var searchJsonData = {'objectType' : 'PrivilegeGroupItems', 'paramsInfo' : JSON.stringify(privilegeGroupItems)};
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
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
            	document.getElementById('idValueForPrivilegeGroupItems').value = '';
			    
            	setPrivilegeGroupItemsData(privilegeGroupItems);
            }
        }
    });	
}
function setPrivilegeGroupItemsData(privilegeGroupItemsDataObject)
{
	var prefix = "";
		
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeActionType'))
	{
		var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeActionType').val(privilegeActionType);
	}
	
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectType'))
	{
		var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeObjectType').val(privilegeObjectType);
	}
	
	//Input
	if(privilegeGroupItemsDataObject.hasOwnProperty('privilegeObjectName'))
	{
		var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		
		$('#'+prefix+'PrivilegeGroupItems_privilegeObjectName').val(privilegeObjectName);
	}

	$('[data-name="privilegeGroupItemsActionButtonDiv"]').hide();
}
function initializePrivilegeGroupItemsLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="privilegeGroupItemsSearchResultsDiv"]');
	
        searchDiv.find('[data-name="privilegeGroupDBId"]').data("privilegeGroup-id", -1);

}

function initializePrivilegeGroupItemsLookupSelectList(paramsMap)
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
function doAfterPrivilegeGroupItemsPanelRefreshed()
{
    //doAfterPanelRefreshedForPrivilegeGroupItemsExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForPrivilegeGroupItems(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForPrivilegeGroupItemsExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForPrivilegeGroupItems(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForPrivilegeGroupItemsExt(fieldName)
}
function getEntityIdForPrivilegeGroupItems()
{
	var idValue = document.getElementById('FormWBEntity:idValueForPrivilegeGroupItems').textContent;	
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
function openPrintPageForPrivilegeGroupItems()
{
	var entityId = getEntityIdForPrivilegeGroupItems();
	if(entityId>0)
	{
	    window.open("/reports/generated/PrivilegeGroupItems.jsp?privilegeGroupItemsId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForPrivilegeGroupItems(paramsMap)
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
	var privilegeGroupItems = {};
		
	//Input
	if($("#"+prefix+"PrivilegeGroupItems_privilegeActionType").length == 1)
	{
		var privilegeActionType = $('#'+prefix+'PrivilegeGroupItems_privilegeActionType').val();
		privilegeGroupItems['privilegeActionType'] = privilegeActionType;
	}
	
	//Input
	if($("#"+prefix+"PrivilegeGroupItems_privilegeObjectType").length == 1)
	{
		var privilegeObjectType = $('#'+prefix+'PrivilegeGroupItems_privilegeObjectType').val();
		privilegeGroupItems['privilegeObjectType'] = privilegeObjectType;
	}
	
	//Input
	if($("#"+prefix+"PrivilegeGroupItems_privilegeObjectName").length == 1)
	{
		var privilegeObjectName = $('#'+prefix+'PrivilegeGroupItems_privilegeObjectName').val();
		privilegeGroupItems['privilegeObjectName'] = privilegeObjectName;
	}

	
	
	if($("#"+prefix+"idValueForPrivilegeGroup").length == 1)
	{
		var privilegeGroupId = $("#"+prefix+"idValueForPrivilegeGroup").val();
		privilegeGroupItems['privilegeGroupId'] = privilegeGroupId; 
	}
	
	return privilegeGroupItems;
}
function createPrivilegeGroupItems(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var privilegeGroupItems = getDataForPrivilegeGroupItems(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				privilegeGroupItems[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForPrivilegeGroupItems)
	{
		paramsInfo[key] = gInitParamsObjForPrivilegeGroupItems[key];
	}
	var validationObject = doPrivilegeGroupItemsValidation(privilegeGroupItems, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	privilegeGroupItems['additionalParamsInfo'] = paramsInfo;
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
	var urlContextPath = "";//getContextPath();
	if(gPrivilegeGroupItemsRequestUnderProcess == 1)
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
            	gPrivilegeGroupItemsRequestUnderProcess = 0;
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gPrivilegeGroupItemsRequestUnderProcess = 0;
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
            	var privilegeGroupItemsId = responseData['privilegeGroupItemsId'];
            	var popupElement = $('[data-name="PrivilegeGroupItemsPopup"]');
            	
				
            	var privilegeGroupItemsDataObject = responseData['privilegeGroupItemsDataObject'];
				setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject);
				retrieveDependentPrivilegeGroupItemsList();
            }
        }
    });
}
function resetTableForPrivilegeGroupItems()
{
	var privilegeGroupItemsListElement = $("[data-name='privilegeGroupItemsList']");
	var previousDataRowList  = privilegeGroupItemsListElement.find('[data-name="privilegeGroupItemsRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForPrivilegeGroupItems(paramsMap)
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
	$('#'+prefix+'idValueForPrivilegeGroupItems').val('');
		
	//Input
	$('#'+prefix+'PrivilegeGroupItems_privilegeActionType').val('');
	
	//Input
	$('#'+prefix+'PrivilegeGroupItems_privilegeObjectType').val('');
	
	//Input
	$('#'+prefix+'PrivilegeGroupItems_privilegeObjectName').val('');

	$('[data-name="privilegeGroupItemsCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="privilegeGroupItemsViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="privilegeGroupItemsActionButtonDiv"]').hide();
	enablePrivilegeGroupItemsUpdateDisallowedFields(paramsMap);
    
}
function enablePrivilegeGroupItemsUpdateDisallowedFields(paramsMap)
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
function updatePrivilegeGroupItems(paramsMap)
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
	var privilegeGroupItems = getDataForPrivilegeGroupItems(paramsMap)
	if($("#"+prefix+"idValueForPrivilegeGroupItems").length == 1)
	{
		var privilegeGroupItemsId = $("#"+prefix+"idValueForPrivilegeGroupItems").val();
		privilegeGroupItems['privilegeGroupItemsId'] = privilegeGroupItemsId;
	}
	var validationObject = doPrivilegeGroupItemsValidation(privilegeGroupItems, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
	var urlContextPath = "";//getContextPath();
	if(gPrivilegeGroupItemsRequestUnderProcess == 1)
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
                    	gPrivilegeGroupItemsRequestUnderProcess = 0;
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gPrivilegeGroupItemsRequestUnderProcess = 0;
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
				
				retrieveDependentPrivilegeGroupItemsList();
            }
        }
    });
}
function deletePrivilegeGroupItems(paramsMap)
{		
	var privilegeGroupItemsId = document.getElementById('idValueForPrivilegeGroupItems').value;
	deleteSelectedPrivilegeGroupItems(privilegeGroupItemsId, paramsMap);
}
function deleteSelectedPrivilegeGroupItems(privilegeGroupItemsId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var privilegeGroupItems = {};
	privilegeGroupItems['privilegeGroupItemsId'] = privilegeGroupItemsId;	
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
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
function loadSearchResultsForPrivilegeGroupItems()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'PrivilegeGroupItems'};
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
            	var privilegeGroupItemsSearchResultsElement = $("[data-name='privilegeGroupItemsSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var privilegeGroupItemsDataObject = searchResultObjectsList[i];
					            		var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
            		var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
            		var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		

            		var resultRowTemnplate = $(gPrivilegeGroupItemsSearchResultsTableRowTemplate);
					            		var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
            	    resultRowTemnplate.find("[data-name='privilegeActionType']").text(privilegeActionType);
            		var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
            	    resultRowTemnplate.find("[data-name='privilegeObjectType']").text(privilegeObjectType);
            		var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		
            	    resultRowTemnplate.find("[data-name='privilegeObjectName']").text(privilegeObjectName);

            	    resultRowTemnplate.data('privilegeGroupItems', privilegeGroupItemsDataObject);
            	    privilegeGroupItemsSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gPrivilegeGroupItemsSearchResultsTableRowTemplate = ""; 
function openViewPageForPrivilegeGroupItemsForEdit(privilegeGroupItemsLinkElement)
{
	var privilegeGroupItemsRowElement = $(privilegeGroupItemsLinkElement).parents('[data-name="privilegeGroupItemsRow"]');
    var privilegeGroupItemsDataObject  = privilegeGroupItemsRowElement.data('privilegeGroupItems');
	var privilegeGroupItemsId = privilegeGroupItemsDataObject['privilegeGroupItemsId'];
	var privilegeGroupItemsPopupElement = $('[data-name="PrivilegeGroupItemsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject);
	    $("#PrivilegeGroupItems-tab").trigger("click");
	}
	else if(privilegeGroupItemsPopupElement.length > 0)
	{
	    setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject);
		$('[data-name="PrivilegeGroupItemsPopup"]').find('[data-name="privilegeGroupItemsCreateFormButtonsDiv"]').hide();
		$('[data-name="PrivilegeGroupItemsPopup"]').find('[data-name="privilegeGroupItemsViewFormButtonsDiv"]').show();
	    showPopup('PrivilegeGroupItemsPopup', privilegeGroupItemsLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewPrivilegeGroupItems.html?privilegeGroupItemsId="+privilegeGroupItemsId;
		window.open(viewLink, "_blank"); 	
	}
}
function openPrivilegeGroupItemsCreatePageForNew(linkElement)
{
	var privilegeGroupItemsPopupElement = $('[data-name="PrivilegeGroupItemsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForPrivilegeGroupItems();
	    $("#PrivilegeGroupItems-tab").trigger("click");
    }
	else if(privilegeGroupItemsPopupElement.length > 0)
	{
		resetFormForPrivilegeGroupItems();
	    showPopup('PrivilegeGroupItemsPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreatePrivilegeGroupItems.html";
    }
}
function showPrivilegeGroupItemsPopupForEdit(privilegeGroupItemsLinkElement)
{
	var privilegeGroupItemsRowElement = $(privilegeGroupItemsLinkElement).parents('[data-name="privilegeGroupItemsRow"]');
    var privilegeGroupItemsDataObject  = privilegeGroupItemsRowElement.data('privilegeGroupItems');
    setPrivilegeGroupItemsInViewPage(privilegeGroupItemsDataObject);
    showPopup('PrivilegeGroupItemsPopup', privilegeGroupItemsLinkElement, 1);
    $("#PrivilegeGroupItems-tab").trigger("click");
}
function showPrivilegeGroupItemsPopupForNew(buttonElement)
{
	resetFormForPrivilegeGroupItems();
    showPopup('PrivilegeGroupItemsPopup', buttonElement, 1);
    $("#PrivilegeGroupItems-tab").trigger("click");
}
function deleteThisPrivilegeGroupItems(privilegeGroupItemsLinkElement, paramsMap)
{
	var privilegeGroupItemsRowElement = $(privilegeGroupItemsLinkElement).parents('[data-name="privilegeGroupItemsRow"]');
    var privilegeGroupItemsDataObject  = privilegeGroupItemsRowElement.data('privilegeGroupItems');
	var privilegeGroupItemsId = privilegeGroupItemsDataObject['privilegeGroupItemsId'];
	deleteSelectedPrivilegeGroupItems(privilegeGroupItemsId, paramsMap);
}
function displayPrivilegeGroupItemsList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupItemsSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var privilegeGroupItemsSearchResults = searchResultsDiv.find('[data-name="privilegeGroupItemsSearchResults"]');
	//privilegeGroupItemsSearchResults.find('[data-name="privilegeGroupItemsRow"]').remove();
	var previousDataRowList  = privilegeGroupItemsSearchResults.find('[data-name="privilegeGroupItemsRow"]');
	privilegeGroupItemsSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = privilegeGroupItemsSearchResults.find('[data-name="privilegeGroupItemsRow"]');
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
        var privilegeGroupItemsDataObject = searchResultObjectsList[i];
        loadPrivilegeGroupItemsViewData(privilegeGroupItemsDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var privilegeGroupDisplayVal = privilegeGroupItemsDataObject['privilegeGroupDisplayVal'];            		
	    resultRowTemplate.find("[data-name='privilegeGroupDisplayVal']").text(privilegeGroupDisplayVal);

		var vwTxnStatus = privilegeGroupItemsDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='privilegeGroupItemsVwTxnStatus']").text(vwTxnStatus);
		if(privilegeGroupItemsDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="privilegeGroupItemsActionButton"]').text(privilegeGroupItemsDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="privilegeGroupItemsActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="privilegeGroupItemsActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="privilegeGroupItemsRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="privilegeGroupItemsRejectButton"]').show();
		}
	    resultRowTemplate.data('privilegeGroupItems', privilegeGroupItemsDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    privilegeGroupItemsSearchResults.append(resultRowTemplate);
	    processResultRowForPrivilegeGroupItemsExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForPrivilegeGroupItems == 1)
	{
    	getDashboardResultsForPrivilegeGroupItems();
	}
}
var privilegeGroupItemsSearchResultsDivName = "privilegeGroupItemsSearchResultsDiv";
var gPrivilegeGroupItemsSearchInputParamsList = [];
function retrievePrivilegeGroupItemsList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + privilegeGroupItemsSearchResultsDivName + '"]');
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
    fetchPrivilegeGroupItemsSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchPrivilegeGroupItemsSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getPrivilegeGroupItemsSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupItemsSearchResultsDivName; 
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
        'objectType': "PrivilegeGroupItems"
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
            	this.showPreviousRecords = "showPreviousPrivilegeGroupItemsRecords";
            	this.showCurrentPageRecords = "showCurrentPagePrivilegeGroupItemsRecords";
            	this.showNextRecords = "showNextPrivilegeGroupItemsRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchPrivilegeGroupItemsResults";
                var privilegeGroupItemsList = responseData['privilegeGroupItemsList'];
                var currContextObj = this; 
                var successCallbackFunction = displayPrivilegeGroupItemsList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'privilegeGroupItemsList', 'matchingSearchResultsCount', this.searchResultsDivName, 'privilegeGroupItemsSearchResults', 'privilegeGroupItemsRow', setPrivilegeGroupItemsSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getPrivilegeGroupItemsSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupItemsSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeActionType"]').length == 1)
		{
		    var privilegeActionType = searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeActionType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeActionType', 'userInputValue':privilegeActionType});
		}
		
		//Input
		if(searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeObjectType"]').length == 1)
		{
		    var privilegeObjectType = searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeObjectType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeObjectType', 'userInputValue':privilegeObjectType});
		}
		
		//Input
		if(searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeObjectName"]').length == 1)
		{
		    var privilegeObjectName = searchDiv.find('[data-name="privilegeGroupItemsDB_privilegeObjectName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeObjectName', 'userInputValue':privilegeObjectName});
		}

	    		if(searchDiv.find('[data-name="privilegeGroupDBId"]').length == 1)
		{
		    var privilegeGroupId = searchDiv.find('[data-name="privilegeGroupDBId"]').data("privilegeGroup-id");
		    parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupId', 'userInputValue':privilegeGroupId});
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
        gPrivilegeGroupItemsSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gPrivilegeGroupItemsSearchInputParamsList;
    }
    return searchInputParams;
}
function setPrivilegeGroupItemsSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + privilegeGroupItemsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gPrivilegeGroupItemsSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gPrivilegeGroupItemsSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPagePrivilegeGroupItemsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupItemsSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchPrivilegeGroupItemsSearchResultsList);
}
function showPreviousPrivilegeGroupItemsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupItemsSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchPrivilegeGroupItemsSearchResultsList);
}
function showNextPrivilegeGroupItemsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = privilegeGroupItemsSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchPrivilegeGroupItemsSearchResultsList);
}
function showPrevOrNextSearchPrivilegeGroupItemsResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousPrivilegeGroupItemsRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextPrivilegeGroupItemsRecords(e);
    }
}

var gPrivilegeGroup_PrivilegeGroupItemsSearchResultsTableRowTemplate =""; 
function initializePrivilegeGroupPopup_PrivilegeGroup_PrivilegeGroupItemsLookupFields() 
{	
    var searchDiv = $('[data-name="PrivilegeGroup_PrivilegeGroupItemsSearchDiv"]');
	
    
	if(gPrivilegeGroup_PrivilegeGroupItemsSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="PrivilegeGroup_PrivilegeGroupItemsSearchResults"]').find('[data-name="PrivilegeGroup_PrivilegeGroupItemsRow"]');
		gPrivilegeGroup_PrivilegeGroupItemsSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayPrivilegeGroup_PrivilegeGroupItemsList(searchResultObjectsList, parentElement)
{
    var privilegeGroupSearchResults = $('[data-name="PrivilegeGroup_PrivilegeGroupItemsSearchResults"]');
	privilegeGroupSearchResults.find('[data-name="PrivilegeGroup_PrivilegeGroupItemsRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gPrivilegeGroup_PrivilegeGroupItemsSearchResultsTableRowTemplate);
        var privilegeGroupDataObject = searchResultObjectsList[i];
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
	    privilegeGroupSearchResults.append(resultRowTemplate);            	    
    }
}
var PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName = "PrivilegeGroup_PrivilegeGroupItemsSearchResultsDiv";
var gPrivilegeGroup_PrivilegeGroupItemsSearchInputParamsList = [];
function getPrivilegeGroup_PrivilegeGroupItemsSearchResults()
{
    var searchDiv = $('[data-name="PrivilegeGroup_PrivilegeGroupItemsSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchPrivilegeGroup_PrivilegeGroupItemsSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchPrivilegeGroup_PrivilegeGroupItemsSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getPrivilegeGroup_PrivilegeGroupItemsSearchInputParams();
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
        'objectType': "PrivilegeGroup",
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
            	this.showCurrentPageRecords = "showCurrentPagePrivilegeGroup_PrivilegeGroupItemsRecords";
            	this.showPreviousRecords = "showPreviousPrivilegeGroup_PrivilegeGroupItemsRecords";
            	this.showCurrentPageRecords = "showCurrentPagePrivilegeGroup_PrivilegeGroupItemsRecords";
            	this.showNextRecords = "showNextPrivilegeGroup_PrivilegeGroupItemsRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchPrivilegeGroup_PrivilegeGroupItemsResults";
                var privilegeGroupList = responseData['privilegeGroupList'];  
        		handleSearchResultsResponse(this, responseData, 'privilegeGroupList', 'matchingSearchResultsCount', PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName, 'PrivilegeGroup_PrivilegeGroupItemsSearchResults', 'PrivilegeGroup_PrivilegeGroupItemsRow', setPrivilegeGroup_PrivilegeGroupItemsSearchInputParams, displayPrivilegeGroup_PrivilegeGroupItemsList);
            }
        }
    });
}
function getPrivilegeGroup_PrivilegeGroupItemsSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="PrivilegeGroup_PrivilegeGroupItemsSearchDiv"]');
        var parameterNameAndValuesList = [];
				
		//Input
	    var privilegeGroupName = searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupName', 'userInputValue':privilegeGroupName});
		
		//Input
	    var privilegeCode = searchDiv.find('[data-name="privilegeGroupDB_privilegeCode"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'privilegeCode', 'userInputValue':privilegeCode});
		
		//Combo
	    var applicableUserType = searchDiv.find('[data-name="privilegeGroupDB_applicableUserType"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'applicableUserType', 'userInputValue':applicableUserType});
		
		//Input
	    var selfServiceUser = searchDiv.find('[data-name="privilegeGroupDB_selfServiceUser"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'selfServiceUser', 'userInputValue':selfServiceUser});
		
		//Text
	    var privilegeGroupDescription = searchDiv.find('[data-name="privilegeGroupDB_privilegeGroupDescription"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupDescription', 'userInputValue':privilegeGroupDescription});

	    
        gPrivilegeGroup_PrivilegeGroupItemsSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gPrivilegeGroup_PrivilegeGroupItemsSearchInputParamsList;
    }
    return searchInputParams;
}
function setPrivilegeGroup_PrivilegeGroupItemsSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gPrivilegeGroup_PrivilegeGroupItemsSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gPrivilegeGroup_PrivilegeGroupItemsSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPagePrivilegeGroup_PrivilegeGroupItemsRecords(e)
{
    getCurrentPageSearchResults(e, PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName, fetchPrivilegeGroup_PrivilegeGroupItemsSearchResultsList);
}
function showPreviousPrivilegeGroup_PrivilegeGroupItemsRecords()
{
    getPreviousPageSearchResults(PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName, fetchPrivilegeGroup_PrivilegeGroupItemsSearchResultsList);
}
function showNextPrivilegeGroup_PrivilegeGroupItemsRecords()
{
    getNextPageSearchResults(PrivilegeGroup_PrivilegeGroupItemsSearchResultsDivName, fetchPrivilegeGroup_PrivilegeGroupItemsSearchResultsList);
}
function showPrevOrNextSearchPrivilegeGroup_PrivilegeGroupItemsResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousPrivilegeGroup_PrivilegeGroupItemsRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextPrivilegeGroup_PrivilegeGroupItemsRecords();
    }
}
function setPrivilegeGroup_PrivilegeGroupItems(privilegeGroupRowElement) 
{
    var privilegeGroupDataObject  = $(privilegeGroupRowElement).data('privilegeGroup');
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	var parentElement = $(privilegeGroupRowElement).parents('[data-name="PrivilegeGroupPopup_PrivilegeGroup_PrivilegeGroupItems"]');
	var linkElement = parentElement.data("selected-element");
	linkElement.data("privilegeGroup-id", privilegeGroupId);
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
			inputElement.val(privilegeGroupDataObject[lookupDisplayValueName]);		
		}
		var entityName = "PrivilegeGroup_PrivilegeGroupItems";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+privilegeGroupId+")";
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
function lookupRowSelectedForPrivilegeGroupItems(attributeName, attributeId)
{
	var privilegeGroupItems = getDataForPrivilegeGroupItems();
	privilegeGroupItems['attributeName'] = attributeName;
	privilegeGroupItems['attributeId'] = attributeId;
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
            	setPrivilegeGroupItemsData(privilegeGroupItems);
            }
        }
    });	
}
function selectOptionChangedForPrivilegeGroupItems(attributeName)
{
	var privilegeGroupItems = getDataForPrivilegeGroupItems();
	privilegeGroupItems['attributeName'] = attributeName;
    var privilegeGroupItemsJsonData = {'paramsInfo': JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
            	setPrivilegeGroupItemsData(privilegeGroupItems);
            	doAfterPrivilegeGroupItemsPanelRefreshed();
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
function retrieveDependentPrivilegeGroupItemsList(paramsMap)
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
	var privilegeGroupId = $('#'+prefix+'idValueForPrivilegeGroup').val();
	if(paramsMap.hasOwnProperty('privilegeGroupId'))
	{
		privilegeGroupId = paramsMap['privilegeGroupId'];
	}
	var paramsInfo = {'privilegeGroupId':privilegeGroupId};
    var searchJsonData = {'objectType' : 'PrivilegeGroupItems', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var privilegeGroupItemsList = responseData['privilegeGroupItemsList'];
            	var privilegeGroupItemsListElement = $("[data-name='privilegeGroupItemsList']");
            	var previousDataRowList  = privilegeGroupItemsListElement.find('[data-name="privilegeGroupItemsRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = privilegeGroupItemsListElement.find('[data-name="privilegeGroupItemsRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//privilegeGroupItemsListElement.empty();
            	for(var i=0; i<privilegeGroupItemsList.length; i++)
        		{
            		var privilegeGroupItemsDataObject = privilegeGroupItemsList[i];
            		//var resultRowTemplate = $(gPrivilegeGroupItemsSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];            		
				    resultRowTemplate.find("[data-name='privilegeActionType']").text(privilegeActionType);
					var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];            		
				    resultRowTemplate.find("[data-name='privilegeObjectType']").text(privilegeObjectType);
					var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];            		
				    resultRowTemplate.find("[data-name='privilegeObjectName']").text(privilegeObjectName);

					
					
            	    resultRowTemplate.data('privilegeGroupItems', privilegeGroupItemsDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    privilegeGroupItemsListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForPrivilegeGroupItems(customEventName)
{
	var privilegeGroupItemsId = document.getElementById('idValueForPrivilegeGroupItems').value;
	var privilegeGroupItems = {'privilegeGroupItemsId':privilegeGroupItemsId, 'customEventName':customEventName};
    var privilegeGroupItemsJsonData = {'paramsInfo':JSON.stringify(privilegeGroupItems), 'objectType' : 'PrivilegeGroupItems'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForPrivilegeGroupItemsExt(customEventName);
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
        		setPrivilegeGroupItemsInViewPage(privilegeGroupItems);
            }
    		doAfterExecuteCustomAPIForPrivilegeGroupItemsExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnPrivilegeGroupItems(e, paramsMap)
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
	var privilegeGroupItemsId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var privilegeGroupItemsDataObject  = rowObj.data('privilegeGroupItems');
    	privilegeGroupItemsId = privilegeGroupItemsDataObject['privilegeGroupItemsId'];
	}
    else
	{
    	privilegeGroupItemsId = document.getElementById('idValueForPrivilegeGroupItems').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var privilegeGroupItemsSearchParams = {'privilegeGroupItemsId':privilegeGroupItemsId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	privilegeGroupItemsSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var privilegeGroupItemsJsonData = {'paramsInfo':JSON.stringify(privilegeGroupItemsSearchParams),  'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroupItems = responseData['privilegeGroupItems'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="privilegeGroupItemsRowActionButtonDiv"]').hide();
            		if(privilegeGroupItems.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = privilegeGroupItems['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(privilegeGroupItems['vwTxnStatus']);
            			selectedRowObj.find('[data-name="privilegeGroupItemsActionButton"]').text(privilegeGroupItems["nextAction"]);
            			selectedRowObj.find('[data-name="privilegeGroupItemsActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setPrivilegeGroupItemsInViewPage(privilegeGroupItems);
    			}
            }
        }
    });	
}
function downloadPrivilegeGroupItemsData()
{		
	var privilegeGroupItems = {};
    var privilegeGroupItemsJsonData = {'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=PrivilegeGroupItems");
            }
        }
    });
}
function uploadPrivilegeGroupItemsData(fileInfo)
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
	var privilegeGroupItems = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var privilegeGroupItemsJsonData = {'paramsInfo':JSON.stringify(privilegeGroupItems),  'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=PrivilegeGroupItems");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForPrivilegeGroupItems()
{
    var privilegeGroupItemsJsonData = {'objectType' : 'PrivilegeGroupItems'};
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
        data: privilegeGroupItemsJsonData,
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



function doPrivilegeGroupItemsLengthValidation(privilegeGroupItemsDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(privilegeGroupItemsDataObject['privilegeActionType'], 50))
	{
		alertMessage += "\n Action Type length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupItemsDataObject['privilegeObjectType'], 50))
	{
		alertMessage += "\n Object Type length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(privilegeGroupItemsDataObject['privilegeObjectName'], 50))
	{
		alertMessage += "\n Object Name length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPrivilegeGroupItemsManadatoryValidation(privilegeGroupItemsDataObject, paramsMap)
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
		
	var privilegeActionType = privilegeGroupItemsDataObject['privilegeActionType'];
	if(!privilegeActionType || privilegeActionType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroupItems_privilegeActionType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Action Type";
		    validationPassed = false;
		}
	}
	
	var privilegeObjectType = privilegeGroupItemsDataObject['privilegeObjectType'];
	if(!privilegeObjectType || privilegeObjectType.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroupItems_privilegeObjectType").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Object Type";
		    validationPassed = false;
		}
	}
	
	var privilegeObjectName = privilegeGroupItemsDataObject['privilegeObjectName'];
	if(!privilegeObjectName || privilegeObjectName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"PrivilegeGroupItems_privilegeObjectName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Object Name";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPrivilegeGroupItemsValidation(privilegeGroupItemsDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doPrivilegeGroupItemsLengthValidation(privilegeGroupItemsDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doPrivilegeGroupItemsManadatoryValidation(privilegeGroupItemsDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
