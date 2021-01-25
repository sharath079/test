/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'PrivilegeGroup' : 'FormWBEntity:EmployeeRoles_privilegeGroup' 
 *	 * 'Description' : 'FormWBEntity:EmployeeRoles_description' 
 *	
 */
var gInitParamsObjForEmployeeRoles = {};
var gEmployeeRolesRequestUnderProcess = 0;
function selectThisEmployeeRolesForEdit(employeeRolesRowElement)
{
    var employeeRolesDataObject  = $(employeeRolesRowElement).data('employeeRoles');
    var employeeRolesList = $('[data-name="employeeRolesSearchResults"]').find('[data-name="employeeRolesRow"]');
	employeeRolesList.data("is-selected", 0);	
	$(employeeRolesRowElement).data("is-selected", 1);
	setEmployeeRolesInViewPage(employeeRolesDataObject);
}function setEmployeeRolesInViewPage(employeeRolesDataObject, paramsMap)
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
	var employeeRolesId = employeeRolesDataObject['employeeRolesId'];
	$('#'+prefix+'idValueForEmployeeRoles').val(employeeRolesId);
		
	//Lookup
	if(employeeRolesDataObject.hasOwnProperty('privilegeGroup'))
	{
		var privilegeGroup = employeeRolesDataObject['privilegeGroup'];
		if(privilegeGroup != "")
		{
			var privilegeGroupId = privilegeGroup['privilegeGroupId'];
			var privilegeGroupLinkElement = $('#'+prefix+'EmployeeRoles_privilegeGroupId');
			privilegeGroupLinkElement.data("privilegeGroup-id", privilegeGroupId);
			var privilegeGroupNextElements = privilegeGroupLinkElement.parents(".form-group").next();
			for(var i=0; i< privilegeGroupNextElements.length; i++)
			{
				var inputElement = $(privilegeGroupNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(privilegeGroup[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(employeeRolesDataObject.hasOwnProperty('description'))
	{
		var description = employeeRolesDataObject['description'];            		
		$('#'+prefix+'EmployeeRoles_description').val(description);
	}	if(employeeRolesDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = employeeRolesDataObject['vwTxnStatus'];
		$('[data-name="employeeRolesActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnEmployeeRoles(this)" >' +employeeRolesDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="employeeRolesActionButtonDiv"]').append(buttonElement);
		$('[data-name="employeeRolesActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="employeeRolesActionButtonDiv"]').hide();
	}
	$('[data-name="employeeRolesCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="employeeRolesViewFormButtonsDiv"]').css("display", "inline");
	//loadEmployeeRolesViewData(employeeRolesDataObject);
	disbaleEmployeeRolesUpdateDisallowedFields(paramsMap);
	doAfterEmployeeRolesPanelRefreshed();
    
    
}
function disbaleEmployeeRolesUpdateDisallowedFields(paramsMap)
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
function loadEmployeeRolesViewData(employeeRolesDataObject, paramsMap)
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
	var employeeRolesId = employeeRolesDataObject['employeeRolesId'];
	$('#'+prefix+'idValueForEmployeeRoles').val(employeeRolesId);
		
	if(employeeRolesDataObject.hasOwnProperty('privilegeGroup'))
	{
		var privilegeGroup = employeeRolesDataObject['privilegeGroup'];
		if(privilegeGroup != "")
		{
			var privilegeGroupId = employeeRolesDataObject['privilegeGroupId'];
			var privilegeGroupDisplayVal = employeeRolesDataObject['privilegeGroupDisplayVal'];
			parentElement.find('[data-name="'+prefix+'EmployeeRoles_privilegeGroup"]').text(privilegeGroupDisplayVal);
			//privilegeGroupLinkElement.data("privilegeGroup-id", privilegeGroupId);
		}
	}
	
	if(employeeRolesDataObject.hasOwnProperty('description'))
	{
		var description = employeeRolesDataObject['description'];            		
		parentElement.find('[data-name="'+prefix+'EmployeeRoles_description"]').text(description);
	}}
function ajaxDemoForEmployeeRoles()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForEmployeeRoles').textContent;	
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
			refreshPanelForEmployeeRoles();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForEmployeeRoles(msg)
{
	var executeCustomAPIButtonForEmployeeRoles = document.getElementById("FormWBEntity:executeCustomAPIButtonForEmployeeRoles");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:EmployeeRoles_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForEmployeeRoles.click();
}
function refreshPanelForEmployeeRoles()
{
	var demoRefreshButtonForEmployeeRoles = document.getElementById("FormWBEntity:demoRefreshButtonEmployeeRoles");
	demoRefreshButtonForEmployeeRoles.click();
}
function initializePanelOnLoadForCreateEmployeeRoles(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForEmployeeRoles = initParamsObj;
	initializeEmployeeRolesPage();	
	doAfterEmployeeRolesPanelRefreshed();
	initializeEmployeeRolesLookupFields(initParamsObj);
	doAfterPanelInitializedForEmployeeRolesExt();
	fetchEmployeeRolesDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="employeeRolesCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForEmployeeRoles = 0;
function initializePanelOnLoadForSearchEmployeeRoles()
{
	initializeEmployeeRolesPage();	
	initializeEmployeeRolesLookupFields();
	doAfterPanelInitializedForEmployeeRolesExt();
	gLoadDashboardResultsForEmployeeRoles =1;
	//retrieveEmployeeRolesList();
}
function initializePanelOnLoadForViewEmployeeRoles(urlParams)
{
	initializeEmployeeRolesPage();	
	doAfterEmployeeRolesPanelRefreshed();
	initializeEmployeeRolesLookupFields(urlParams);
	doAfterPanelInitializedForEmployeeRolesExt();
	retrieveEmployeeRoles(urlParams);
	initDatePicker();
	$('[data-name="employeeRolesViewFormButtonsDiv"]').css("display", "inline");
}
function initializeEmployeeRolesPage()
{
	initializePageOnload();	
	//initializeEmployeeRolesTemplateVariables();
}
function initializeEmployeeRolesTemplateVariables()
{	
	
	
	var employeeRolesRowObj = $('[data-name="employeeRolesList"]').find('[data-name="employeeRolesRow"]');
	if(employeeRolesRowObj.length > 0 && gEmployeeRolesSearchResultsTableRowTemplate.length == 0)
	{
		gEmployeeRolesSearchResultsTableRowTemplate = employeeRolesRowObj[0].outerHTML;
		//employeeRolesRowObj.remove();
	}
	if(gEmployeeRolesSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="employeeRolesSearchResults"]').find('[data-name="employeeRolesRow"]');
		if(searchResultsRowObj.length > 0 && gEmployeeRolesSearchResultsTableRowTemplate.length == 0)
		{
			gEmployeeRolesSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrieveEmployeeRoles(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var employeeRolesId = paramsMap['employeeRolesId'];
	var employeeRoles = {};
	employeeRoles['employeeRolesId'] = employeeRolesId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				employeeRoles[key] = paramsMap[key];
			}
	}
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
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
                	var employeeRolesDataObject = responseData['employeeRolesDataObject'];
    				setEmployeeRolesInViewPage(employeeRolesDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForEmployeeRoles()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchEmployeeRolesDefaultData();
}
function fetchEmployeeRolesDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'EmployeeRoles', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var employeeRoles = responseData['employeeRoles'];
            	document.getElementById('idValueForEmployeeRoles').value = '';
			    
            	setEmployeeRolesData(employeeRoles);
            }
        }
    });	
}
function fetchEmployeeRolesTestData() 
{
	var employeeRoles = getDataForEmployeeRoles();
    var searchJsonData = {'objectType' : 'EmployeeRoles', 'paramsInfo' : JSON.stringify(employeeRoles)};
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
            	var employeeRoles = responseData['employeeRoles'];
            	document.getElementById('idValueForEmployeeRoles').value = '';
			    
            	setEmployeeRolesData(employeeRoles);
            }
        }
    });	
}
function setEmployeeRolesData(employeeRolesDataObject)
{
	var prefix = "";
		
	//Lookup
	if(employeeRolesDataObject.hasOwnProperty('privilegeGroup'))
	{
		var privilegeGroup = employeeRolesDataObject['privilegeGroup'];
		if(privilegeGroup != "")
		{
			var privilegeGroupId = privilegeGroup['privilegeGroupId'];
			var privilegeGroupLinkElement = $('#'+prefix+'EmployeeRoles_privilegeGroupId');
			privilegeGroupLinkElement.data("privilegeGroup-id", privilegeGroupId);
			var privilegeGroupNextElements = privilegeGroupLinkElement.parents(".form-group").next();
			for(var i=0; i< privilegeGroupNextElements.length; i++)
			{
				var inputElement = $(privilegeGroupNextElements[i]).find('input');
				if(inputElement.data("is-lookup-field") != 1)
				{
					break;
				}
				var el = inputElement.data("value-el");
				var lookupDisplayValueName = el.split('.')[2];
				inputElement.val(privilegeGroup[lookupDisplayValueName]);		
			}
		}
	}
	
	//Input
	if(employeeRolesDataObject.hasOwnProperty('description'))
	{
		var description = employeeRolesDataObject['description'];            		
		$('#'+prefix+'EmployeeRoles_description').val(description);
	}	$('[data-name="employeeRolesActionButtonDiv"]').hide();
}
function initializeEmployeeRolesLookupFields(paramsMap) 
{
		
	$("#EmployeeRoles_privilegeGroupId").data("privilegeGroup-id", -1);
  
	
	
	
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="employeeRolesSearchResultsDiv"]');
		
	
    searchDiv.find('[data-name="employeeRolesDB_privilegeGroupId"]').data("privilegeGroup-id", -1);
  
	
	
	
        searchDiv.find('[data-name="userInfoDBId"]').data("userInfo-id", -1);}function initializeEmployeeRolesLookupSelectList(paramsMap)
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
function doAfterEmployeeRolesPanelRefreshed()
{
    //doAfterPanelRefreshedForEmployeeRolesExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForEmployeeRoles(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForEmployeeRolesExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForEmployeeRoles(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'privilegeGroup')
	{
	}	doAfterLookupRowChangedForEmployeeRolesExt(fieldName)
}
function getEntityIdForEmployeeRoles()
{
	var idValue = document.getElementById('FormWBEntity:idValueForEmployeeRoles').textContent;	
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
function openPrintPageForEmployeeRoles()
{
	var entityId = getEntityIdForEmployeeRoles();
	if(entityId>0)
	{
	    window.open("/reports/generated/EmployeeRoles.jsp?employeeRolesId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}
function hidePrivilegeGroupForEmployeeRoles()
{
		
		document.getElementById('EmployeeRoles_privilegeGroupId').parentElement.style.display = "none";
	   	
		document.getElementById('EmployeeRoles_privilegeGroupprivilegeGroupName').parentElement.style.display = "none";	
	   	
		//document.getElementById('EmployeeRoles_privilegeGroupNewLinkID').parentElement.style.display = "none";
}function showPrivilegeGroupForEmployeeRoles()
{
		
		document.getElementById('EmployeeRoles_privilegeGroupId').parentElement.style.display = "table-row";
	   	
		document.getElementById('EmployeeRoles_privilegeGroupprivilegeGroupName').parentElement.style.display = "table-row";	
	   	
		//document.getElementById('EmployeeRoles_privilegeGroupNewLinkID').parentElement.style.display = "table-row";
}
function getDataForEmployeeRoles(paramsMap)
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
	var employeeRoles = {};
		
	//Lookup
	if($("#"+prefix+"EmployeeRoles_privilegeGroupId").length == 1)
	{
		var privilegeGroupId = $('#'+prefix+'EmployeeRoles_privilegeGroupId').data("privilegeGroup-id");
		employeeRoles['privilegeGroupId'] = privilegeGroupId;
	}
	
	//Input
	if($("#"+prefix+"EmployeeRoles_description").length == 1)
	{
		var description = $('#'+prefix+'EmployeeRoles_description').val();
		employeeRoles['description'] = description;
	}	
	
	if($("#"+prefix+"idValueForUserInfo").length == 1)
	{
		var userInfoId = $("#"+prefix+"idValueForUserInfo").val();
		employeeRoles['userInfoId'] = userInfoId; 
	}
	
	return employeeRoles;
}
function createEmployeeRoles(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var employeeRoles = getDataForEmployeeRoles(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				employeeRoles[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForEmployeeRoles)
	{
		paramsInfo[key] = gInitParamsObjForEmployeeRoles[key];
	}
	var validationObject = doEmployeeRolesValidation(employeeRoles, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	employeeRoles['additionalParamsInfo'] = paramsInfo;
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
	var urlContextPath = "";//getContextPath();
	if(gEmployeeRolesRequestUnderProcess == 1)
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
            	gEmployeeRolesRequestUnderProcess = 0;
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gEmployeeRolesRequestUnderProcess = 0;
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
            	var employeeRolesId = responseData['employeeRolesId'];
            	var popupElement = $('[data-name="EmployeeRolesPopup"]');
            	
				
            	var employeeRolesDataObject = responseData['employeeRolesDataObject'];
				setEmployeeRolesInViewPage(employeeRolesDataObject);
				retrieveDependentEmployeeRolesList();
            }
        }
    });
}
function resetTableForEmployeeRoles()
{
	var employeeRolesListElement = $("[data-name='employeeRolesList']");
	var previousDataRowList  = employeeRolesListElement.find('[data-name="employeeRolesRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForEmployeeRoles(paramsMap)
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
	$('#'+prefix+'idValueForEmployeeRoles').val('');
		
	//Lookup
	var privilegeGroupLinkElement = $('#'+prefix+'EmployeeRoles_privilegeGroupId');
	privilegeGroupLinkElement.data("privilegeGroup-id", -1);
	var privilegeGroupNextElements = privilegeGroupLinkElement.parents(".form-group").next();
	for(var i=0; i< privilegeGroupNextElements.length; i++)
	{
		var inputElement = $(privilegeGroupNextElements[i]).find('input');
		if(inputElement.data("is-lookup-field") != 1)
		{
			break;
		}
		inputElement.val('');		
	}
	
	//Input
	$('#'+prefix+'EmployeeRoles_description').val('');	$('[data-name="employeeRolesCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="employeeRolesViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="employeeRolesActionButtonDiv"]').hide();
	enableEmployeeRolesUpdateDisallowedFields(paramsMap);
    
}
function enableEmployeeRolesUpdateDisallowedFields(paramsMap)
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
function updateEmployeeRoles(paramsMap)
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
	var employeeRoles = getDataForEmployeeRoles(paramsMap)
	if($("#"+prefix+"idValueForEmployeeRoles").length == 1)
	{
		var employeeRolesId = $("#"+prefix+"idValueForEmployeeRoles").val();
		employeeRoles['employeeRolesId'] = employeeRolesId;
	}
	var validationObject = doEmployeeRolesValidation(employeeRoles, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
	var urlContextPath = "";//getContextPath();
	if(gEmployeeRolesRequestUnderProcess == 1)
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
                    	gEmployeeRolesRequestUnderProcess = 0;
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gEmployeeRolesRequestUnderProcess = 0;
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
				
				retrieveDependentEmployeeRolesList();
            }
        }
    });
}
function deleteEmployeeRoles(paramsMap)
{		
	var employeeRolesId = document.getElementById('idValueForEmployeeRoles').value;
	deleteSelectedEmployeeRoles(employeeRolesId, paramsMap);
}
function deleteSelectedEmployeeRoles(employeeRolesId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var employeeRoles = {};
	employeeRoles['employeeRolesId'] = employeeRolesId;	
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
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
function loadSearchResultsForEmployeeRoles()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'EmployeeRoles'};
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
            	var employeeRolesSearchResultsElement = $("[data-name='employeeRolesSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var employeeRolesDataObject = searchResultObjectsList[i];
					            		var privilegeGroup = employeeRolesDataObject['privilegeGroup'];            		
            		var description = employeeRolesDataObject['description'];            		            		var resultRowTemnplate = $(gEmployeeRolesSearchResultsTableRowTemplate);
					            		var privilegeGroup = employeeRolesDataObject['privilegeGroup'];            		
            	    resultRowTemnplate.find("[data-name='privilegeGroup']").text(privilegeGroup);
            		var description = employeeRolesDataObject['description'];            		
            	    resultRowTemnplate.find("[data-name='description']").text(description);            	    resultRowTemnplate.data('employeeRoles', employeeRolesDataObject);
            	    employeeRolesSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gEmployeeRolesSearchResultsTableRowTemplate = ""; 
function openViewPageForEmployeeRolesForEdit(employeeRolesLinkElement)
{
	var employeeRolesRowElement = $(employeeRolesLinkElement).parents('[data-name="employeeRolesRow"]');
    var employeeRolesDataObject  = employeeRolesRowElement.data('employeeRoles');
	var employeeRolesId = employeeRolesDataObject['employeeRolesId'];
	var employeeRolesPopupElement = $('[data-name="EmployeeRolesPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setEmployeeRolesInViewPage(employeeRolesDataObject);
	    $("#EmployeeRoles-tab").trigger("click");
	}
	else if(employeeRolesPopupElement.length > 0)
	{
	    setEmployeeRolesInViewPage(employeeRolesDataObject);
		$('[data-name="EmployeeRolesPopup"]').find('[data-name="employeeRolesCreateFormButtonsDiv"]').hide();
		$('[data-name="EmployeeRolesPopup"]').find('[data-name="employeeRolesViewFormButtonsDiv"]').show();
	    showPopup('EmployeeRolesPopup', employeeRolesLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewEmployeeRoles.html?employeeRolesId="+employeeRolesId;
		window.open(viewLink, "_blank"); 	
	}
}
function openEmployeeRolesCreatePageForNew(linkElement)
{
	var employeeRolesPopupElement = $('[data-name="EmployeeRolesPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForEmployeeRoles();
	    $("#EmployeeRoles-tab").trigger("click");
    }
	else if(employeeRolesPopupElement.length > 0)
	{
		resetFormForEmployeeRoles();
	    showPopup('EmployeeRolesPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateEmployeeRoles.html";
    }
}
function showEmployeeRolesPopupForEdit(employeeRolesLinkElement)
{
	var employeeRolesRowElement = $(employeeRolesLinkElement).parents('[data-name="employeeRolesRow"]');
    var employeeRolesDataObject  = employeeRolesRowElement.data('employeeRoles');
    setEmployeeRolesInViewPage(employeeRolesDataObject);
    showPopup('EmployeeRolesPopup', employeeRolesLinkElement, 1);
    $("#EmployeeRoles-tab").trigger("click");
}
function showEmployeeRolesPopupForNew(buttonElement)
{
	resetFormForEmployeeRoles();
    showPopup('EmployeeRolesPopup', buttonElement, 1);
    $("#EmployeeRoles-tab").trigger("click");
}
function deleteThisEmployeeRoles(employeeRolesLinkElement, paramsMap)
{
	var employeeRolesRowElement = $(employeeRolesLinkElement).parents('[data-name="employeeRolesRow"]');
    var employeeRolesDataObject  = employeeRolesRowElement.data('employeeRoles');
	var employeeRolesId = employeeRolesDataObject['employeeRolesId'];
	deleteSelectedEmployeeRoles(employeeRolesId, paramsMap);
}
function displayEmployeeRolesList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + employeeRolesSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var employeeRolesSearchResults = searchResultsDiv.find('[data-name="employeeRolesSearchResults"]');
	//employeeRolesSearchResults.find('[data-name="employeeRolesRow"]').remove();
	var previousDataRowList  = employeeRolesSearchResults.find('[data-name="employeeRolesRow"]');
	employeeRolesSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = employeeRolesSearchResults.find('[data-name="employeeRolesRow"]');
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
        var employeeRolesDataObject = searchResultObjectsList[i];
        loadEmployeeRolesViewData(employeeRolesDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var userInfoDisplayVal = employeeRolesDataObject['userInfoDisplayVal'];            		
	    resultRowTemplate.find("[data-name='userInfoDisplayVal']").text(userInfoDisplayVal);		var vwTxnStatus = employeeRolesDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='employeeRolesVwTxnStatus']").text(vwTxnStatus);
		if(employeeRolesDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="employeeRolesActionButton"]').text(employeeRolesDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="employeeRolesActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="employeeRolesActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="employeeRolesRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="employeeRolesRejectButton"]').show();
		}
	    resultRowTemplate.data('employeeRoles', employeeRolesDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    employeeRolesSearchResults.append(resultRowTemplate);
	    processResultRowForEmployeeRolesExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForEmployeeRoles == 1)
	{
    	getDashboardResultsForEmployeeRoles();
	}
}
var employeeRolesSearchResultsDivName = "employeeRolesSearchResultsDiv";
var gEmployeeRolesSearchInputParamsList = [];
function retrieveEmployeeRolesList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + employeeRolesSearchResultsDivName + '"]');
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
    fetchEmployeeRolesSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchEmployeeRolesSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getEmployeeRolesSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = employeeRolesSearchResultsDivName; 
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
        'objectType': "EmployeeRoles"
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
            	this.showPreviousRecords = "showPreviousEmployeeRolesRecords";
            	this.showCurrentPageRecords = "showCurrentPageEmployeeRolesRecords";
            	this.showNextRecords = "showNextEmployeeRolesRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchEmployeeRolesResults";
                var employeeRolesList = responseData['employeeRolesList'];
                var currContextObj = this; 
                var successCallbackFunction = displayEmployeeRolesList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'employeeRolesList', 'matchingSearchResultsCount', this.searchResultsDivName, 'employeeRolesSearchResults', 'employeeRolesRow', setEmployeeRolesSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getEmployeeRolesSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + employeeRolesSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="employeeRolesDB_privilegeGroupId"]').length == 1)
		{
		    var privilegeGroupId = searchDiv.find('[data-name="employeeRolesDB_privilegeGroupId"]').data("privilegeGroup-id");
		    if(privilegeGroupId > -1)
	    	{
	    		parameterNameAndValuesList.push({ 'parameterName':'privilegeGroupId', 'userInputValue':privilegeGroupId});
	    	}
		}
		
		//Input
		if(searchDiv.find('[data-name="employeeRolesDB_description"]').length == 1)
		{
		    var description = searchDiv.find('[data-name="employeeRolesDB_description"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'description', 'userInputValue':description});
		}	    		if(searchDiv.find('[data-name="userInfoDBId"]').length == 1)
		{
		    var userInfoId = searchDiv.find('[data-name="userInfoDBId"]').data("userInfo-id");
		    parameterNameAndValuesList.push({ 'parameterName':'userInfoId', 'userInputValue':userInfoId});
		}	    for(var key in customParamsMap)
    	{
			if(key != "errorCallbackFunction"
				&& key != "successCallbackFunction")
				{
					parameterNameAndValuesList.push({ 'parameterName':key, 'userInputValue':customParamsMap[key]});
				}
    	}
	    parameterNameAndValuesList.push({'parameterName':'additionalParamsList', 'userInputValue':JSON.stringify(additionalParamsList)});
        gEmployeeRolesSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gEmployeeRolesSearchInputParamsList;
    }
    return searchInputParams;
}
function setEmployeeRolesSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + employeeRolesSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gEmployeeRolesSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gEmployeeRolesSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageEmployeeRolesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = employeeRolesSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchEmployeeRolesSearchResultsList);
}
function showPreviousEmployeeRolesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = employeeRolesSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchEmployeeRolesSearchResultsList);
}
function showNextEmployeeRolesRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = employeeRolesSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchEmployeeRolesSearchResultsList);
}
function showPrevOrNextSearchEmployeeRolesResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousEmployeeRolesRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextEmployeeRolesRecords(e);
    }
}var gEmployeeRoles_privilegeGroupSearchResultsTableRowTemplate =""; 
function initializePrivilegeGroupPopup_EmployeeRoles_privilegeGroupLookupFields() 
{	
    var searchDiv = $('[data-name="EmployeeRoles_privilegeGroupSearchDiv"]');
	
    
	if(gEmployeeRoles_privilegeGroupSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="EmployeeRoles_privilegeGroupSearchResults"]').find('[data-name="EmployeeRoles_privilegeGroupRow"]');
		gEmployeeRoles_privilegeGroupSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayEmployeeRoles_privilegeGroupList(searchResultObjectsList, parentElement)
{
    var privilegeGroupSearchResults = $('[data-name="EmployeeRoles_privilegeGroupSearchResults"]');
	privilegeGroupSearchResults.find('[data-name="EmployeeRoles_privilegeGroupRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gEmployeeRoles_privilegeGroupSearchResultsTableRowTemplate);
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
var EmployeeRoles_privilegeGroupSearchResultsDivName = "EmployeeRoles_privilegeGroupSearchResultsDiv";
var gEmployeeRoles_privilegeGroupSearchInputParamsList = [];
function getEmployeeRoles_privilegeGroupSearchResults()
{
    var searchDiv = $('[data-name="EmployeeRoles_privilegeGroupSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + EmployeeRoles_privilegeGroupSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchEmployeeRoles_privilegeGroupSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchEmployeeRoles_privilegeGroupSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getEmployeeRoles_privilegeGroupSearchInputParams();
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
            	this.showCurrentPageRecords = "showCurrentPageEmployeeRoles_privilegeGroupRecords";
            	this.showPreviousRecords = "showPreviousEmployeeRoles_privilegeGroupRecords";
            	this.showCurrentPageRecords = "showCurrentPageEmployeeRoles_privilegeGroupRecords";
            	this.showNextRecords = "showNextEmployeeRoles_privilegeGroupRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchEmployeeRoles_privilegeGroupResults";
                var privilegeGroupList = responseData['privilegeGroupList'];  
        		handleSearchResultsResponse(this, responseData, 'privilegeGroupList', 'matchingSearchResultsCount', EmployeeRoles_privilegeGroupSearchResultsDivName, 'EmployeeRoles_privilegeGroupSearchResults', 'EmployeeRoles_privilegeGroupRow', setEmployeeRoles_privilegeGroupSearchInputParams, displayEmployeeRoles_privilegeGroupList);
            }
        }
    });
}
function getEmployeeRoles_privilegeGroupSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + EmployeeRoles_privilegeGroupSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="EmployeeRoles_privilegeGroupSearchDiv"]');
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
        gEmployeeRoles_privilegeGroupSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gEmployeeRoles_privilegeGroupSearchInputParamsList;
    }
    return searchInputParams;
}
function setEmployeeRoles_privilegeGroupSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + EmployeeRoles_privilegeGroupSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gEmployeeRoles_privilegeGroupSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gEmployeeRoles_privilegeGroupSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageEmployeeRoles_privilegeGroupRecords(e)
{
    getCurrentPageSearchResults(e, EmployeeRoles_privilegeGroupSearchResultsDivName, fetchEmployeeRoles_privilegeGroupSearchResultsList);
}
function showPreviousEmployeeRoles_privilegeGroupRecords()
{
    getPreviousPageSearchResults(EmployeeRoles_privilegeGroupSearchResultsDivName, fetchEmployeeRoles_privilegeGroupSearchResultsList);
}
function showNextEmployeeRoles_privilegeGroupRecords()
{
    getNextPageSearchResults(EmployeeRoles_privilegeGroupSearchResultsDivName, fetchEmployeeRoles_privilegeGroupSearchResultsList);
}
function showPrevOrNextSearchEmployeeRoles_privilegeGroupResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousEmployeeRoles_privilegeGroupRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextEmployeeRoles_privilegeGroupRecords();
    }
}
function setEmployeeRoles_privilegeGroup(privilegeGroupRowElement) 
{
    var privilegeGroupDataObject  = $(privilegeGroupRowElement).data('privilegeGroup');
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	var parentElement = $(privilegeGroupRowElement).parents('[data-name="PrivilegeGroupPopup_EmployeeRoles_privilegeGroup"]');
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
		var entityName = "EmployeeRoles_privilegeGroup";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+privilegeGroupId+")";
	    eval(functionName);
    }		
	parentElement.hide();
}
  
var gUserInfo_EmployeeRolesSearchResultsTableRowTemplate =""; 
function initializeUserInfoPopup_UserInfo_EmployeeRolesLookupFields() 
{	
    var searchDiv = $('[data-name="UserInfo_EmployeeRolesSearchDiv"]');
	  
	
	
	
	  
	
	
	
		
	
    searchDiv.find('[data-name="userInfoDB_organisationsUserOrgId"]').data("organisations-id", -1);
  
	
	
	
	  
	
	
	
	  
	
	
	
	  
	
	
	
	
    
	if(gUserInfo_EmployeeRolesSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="UserInfo_EmployeeRolesSearchResults"]').find('[data-name="UserInfo_EmployeeRolesRow"]');
		gUserInfo_EmployeeRolesSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayUserInfo_EmployeeRolesList(searchResultObjectsList, parentElement)
{
    var userInfoSearchResults = $('[data-name="UserInfo_EmployeeRolesSearchResults"]');
	userInfoSearchResults.find('[data-name="UserInfo_EmployeeRolesRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gUserInfo_EmployeeRolesSearchResultsTableRowTemplate);
        var userInfoDataObject = searchResultObjectsList[i];
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
	    resultRowTemplate.find("[data-name='loginPassword']").text(loginPassword);				var organisationsUserOrg = userInfoDataObject['organisationsUserOrgDisplayVal'];            		
	    resultRowTemplate.find("[data-name='organisationsUserOrg']").text(organisationsUserOrg);	    
	    resultRowTemplate.data('userInfo', userInfoDataObject);
	    userInfoSearchResults.append(resultRowTemplate);            	    
    }
}
var UserInfo_EmployeeRolesSearchResultsDivName = "UserInfo_EmployeeRolesSearchResultsDiv";
var gUserInfo_EmployeeRolesSearchInputParamsList = [];
function getUserInfo_EmployeeRolesSearchResults()
{
    var searchDiv = $('[data-name="UserInfo_EmployeeRolesSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + UserInfo_EmployeeRolesSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchUserInfo_EmployeeRolesSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchUserInfo_EmployeeRolesSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getUserInfo_EmployeeRolesSearchInputParams();
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
        'objectType': "UserInfo",
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
            	this.showCurrentPageRecords = "showCurrentPageUserInfo_EmployeeRolesRecords";
            	this.showPreviousRecords = "showPreviousUserInfo_EmployeeRolesRecords";
            	this.showCurrentPageRecords = "showCurrentPageUserInfo_EmployeeRolesRecords";
            	this.showNextRecords = "showNextUserInfo_EmployeeRolesRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchUserInfo_EmployeeRolesResults";
                var userInfoList = responseData['userInfoList'];  
        		handleSearchResultsResponse(this, responseData, 'userInfoList', 'matchingSearchResultsCount', UserInfo_EmployeeRolesSearchResultsDivName, 'UserInfo_EmployeeRolesSearchResults', 'UserInfo_EmployeeRolesRow', setUserInfo_EmployeeRolesSearchInputParams, displayUserInfo_EmployeeRolesList);
            }
        }
    });
}
function getUserInfo_EmployeeRolesSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + UserInfo_EmployeeRolesSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="UserInfo_EmployeeRolesSearchDiv"]');
        var parameterNameAndValuesList = [];
				
		//Input
	    var userFirstName = searchDiv.find('[data-name="userInfoDB_userFirstName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'userFirstName', 'userInputValue':userFirstName});
		
		//Input
	    var userLastName = searchDiv.find('[data-name="userInfoDB_userLastName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'userLastName', 'userInputValue':userLastName});
		
    	
		//Lookup
	    var organisationsUserOrgId = searchDiv.find('[data-name="userInfoDB_organisationsUserOrgId"]').data("organisations-id");
	    if(organisationsUserOrgId > -1)
    	{
		    parameterNameAndValuesList.push({ 'parameterName':'organisationsUserOrgId', 'userInputValue':organisationsUserOrgId});
	    }
		
		//Input
	    var loginId = searchDiv.find('[data-name="userInfoDB_loginId"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'loginId', 'userInputValue':loginId});
		
		//Input
	    var loginEmailId = searchDiv.find('[data-name="userInfoDB_loginEmailId"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'loginEmailId', 'userInputValue':loginEmailId});	    
        gUserInfo_EmployeeRolesSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gUserInfo_EmployeeRolesSearchInputParamsList;
    }
    return searchInputParams;
}
function setUserInfo_EmployeeRolesSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + UserInfo_EmployeeRolesSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gUserInfo_EmployeeRolesSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gUserInfo_EmployeeRolesSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageUserInfo_EmployeeRolesRecords(e)
{
    getCurrentPageSearchResults(e, UserInfo_EmployeeRolesSearchResultsDivName, fetchUserInfo_EmployeeRolesSearchResultsList);
}
function showPreviousUserInfo_EmployeeRolesRecords()
{
    getPreviousPageSearchResults(UserInfo_EmployeeRolesSearchResultsDivName, fetchUserInfo_EmployeeRolesSearchResultsList);
}
function showNextUserInfo_EmployeeRolesRecords()
{
    getNextPageSearchResults(UserInfo_EmployeeRolesSearchResultsDivName, fetchUserInfo_EmployeeRolesSearchResultsList);
}
function showPrevOrNextSearchUserInfo_EmployeeRolesResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousUserInfo_EmployeeRolesRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextUserInfo_EmployeeRolesRecords();
    }
}
function setUserInfo_EmployeeRoles(userInfoRowElement) 
{
    var userInfoDataObject  = $(userInfoRowElement).data('userInfo');
	var userInfoId = userInfoDataObject['userInfoId'];
	var parentElement = $(userInfoRowElement).parents('[data-name="UserInfoPopup_UserInfo_EmployeeRoles"]');
	var linkElement = parentElement.data("selected-element");
	linkElement.data("userInfo-id", userInfoId);
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
			inputElement.val(userInfoDataObject[lookupDisplayValueName]);		
		}
		var entityName = "UserInfo_EmployeeRoles";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+userInfoId+")";
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
function lookupRowSelectedForEmployeeRoles(attributeName, attributeId)
{
	var employeeRoles = getDataForEmployeeRoles();
	employeeRoles['attributeName'] = attributeName;
	employeeRoles['attributeId'] = attributeId;
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var employeeRoles = responseData['employeeRoles'];
            	setEmployeeRolesData(employeeRoles);
            }
        }
    });	
}
function selectOptionChangedForEmployeeRoles(attributeName)
{
	var employeeRoles = getDataForEmployeeRoles();
	employeeRoles['attributeName'] = attributeName;
    var employeeRolesJsonData = {'paramsInfo': JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var employeeRoles = responseData['employeeRoles'];
            	setEmployeeRolesData(employeeRoles);
            	doAfterEmployeeRolesPanelRefreshed();
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
function retrieveDependentEmployeeRolesList(paramsMap)
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
	var userInfoId = $('#'+prefix+'idValueForUserInfo').val();
	if(paramsMap.hasOwnProperty('userInfoId'))
	{
		userInfoId = paramsMap['userInfoId'];
	}
	var paramsInfo = {'userInfoId':userInfoId};
    var searchJsonData = {'objectType' : 'EmployeeRoles', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var employeeRolesList = responseData['employeeRolesList'];
            	var employeeRolesListElement = $("[data-name='employeeRolesList']");
            	var previousDataRowList  = employeeRolesListElement.find('[data-name="employeeRolesRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = employeeRolesListElement.find('[data-name="employeeRolesRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//employeeRolesListElement.empty();
            	for(var i=0; i<employeeRolesList.length; i++)
        		{
            		var employeeRolesDataObject = employeeRolesList[i];
            		//var resultRowTemplate = $(gEmployeeRolesSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var description = employeeRolesDataObject['description'];            		
				    resultRowTemplate.find("[data-name='description']").text(description);					
										var privilegeGroup = employeeRolesDataObject['privilegeGroupDisplayVal'];            		
				    resultRowTemplate.find("[data-name='privilegeGroup']").text(privilegeGroup);            	    resultRowTemplate.data('employeeRoles', employeeRolesDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    employeeRolesListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForEmployeeRoles(customEventName)
{
	var employeeRolesId = document.getElementById('idValueForEmployeeRoles').value;
	var employeeRoles = {'employeeRolesId':employeeRolesId, 'customEventName':customEventName};
    var employeeRolesJsonData = {'paramsInfo':JSON.stringify(employeeRoles), 'objectType' : 'EmployeeRoles'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForEmployeeRolesExt(customEventName);
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var employeeRoles = responseData['employeeRoles'];
        		setEmployeeRolesInViewPage(employeeRoles);
            }
    		doAfterExecuteCustomAPIForEmployeeRolesExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnEmployeeRoles(e, paramsMap)
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
	var employeeRolesId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var employeeRolesDataObject  = rowObj.data('employeeRoles');
    	employeeRolesId = employeeRolesDataObject['employeeRolesId'];
	}
    else
	{
    	employeeRolesId = document.getElementById('idValueForEmployeeRoles').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var employeeRolesSearchParams = {'employeeRolesId':employeeRolesId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	employeeRolesSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var employeeRolesJsonData = {'paramsInfo':JSON.stringify(employeeRolesSearchParams),  'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var employeeRoles = responseData['employeeRoles'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="employeeRolesRowActionButtonDiv"]').hide();
            		if(employeeRoles.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = employeeRoles['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(employeeRoles['vwTxnStatus']);
            			selectedRowObj.find('[data-name="employeeRolesActionButton"]').text(employeeRoles["nextAction"]);
            			selectedRowObj.find('[data-name="employeeRolesActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setEmployeeRolesInViewPage(employeeRoles);
    			}
            }
        }
    });	
}
function downloadEmployeeRolesData()
{		
	var employeeRoles = {};
    var employeeRolesJsonData = {'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=EmployeeRoles");
            }
        }
    });
}
function uploadEmployeeRolesData(fileInfo)
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
	var employeeRoles = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var employeeRolesJsonData = {'paramsInfo':JSON.stringify(employeeRoles),  'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=EmployeeRoles");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}function getDashboardResultsForEmployeeRoles()
{
    var employeeRolesJsonData = {'objectType' : 'EmployeeRoles'};
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
        data: employeeRolesJsonData,
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
}function doEmployeeRolesLengthValidation(employeeRolesDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	if(!isFieldLengthAllowed(employeeRolesDataObject['description'], 50))
	{
		alertMessage += "\n Description length is more than allowed(50)";
	    validationPassed = false;
	}	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doEmployeeRolesManadatoryValidation(employeeRolesDataObject, paramsMap)
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
		
	var privilegeGroupId = employeeRolesDataObject['privilegeGroupId'];
	if(!privilegeGroupId || privilegeGroupId < 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"EmployeeRoles_privilegeGroup").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Select Privilege Group";
		    validationPassed = false;
		}
	}	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doEmployeeRolesValidation(employeeRolesDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doEmployeeRolesLengthValidation(employeeRolesDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doEmployeeRolesManadatoryValidation(employeeRolesDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
