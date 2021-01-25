/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'OrganisationName' : 'FormWBEntity:Organisations_organisationName' 
 *	 * 'AddressLine1' : 'FormWBEntity:Organisations_addressLine1' 
 *	 * 'AddressLine2' : 'FormWBEntity:Organisations_addressLine2' 
 *	 * 'City' : 'FormWBEntity:Organisations_city' 
 *	 * 'ResidentState' : 'FormWBEntity:Organisations_residentState' 
 *	 * 'PinCode' : 'FormWBEntity:Organisations_pinCode' 
 *	 * 'DatabaseName' : 'FormWBEntity:Organisations_databaseName' 
 *	 * 'Country' : 'FormWBEntity:Organisations_country' 
 *	
 */
var gInitParamsObjForOrganisations = {};
var gOrganisationsRequestUnderProcess = 0;
function selectThisOrganisationsForEdit(organisationsRowElement)
{
    var organisationsDataObject  = $(organisationsRowElement).data('organisations');
    var organisationsList = $('[data-name="organisationsSearchResults"]').find('[data-name="organisationsRow"]');
	organisationsList.data("is-selected", 0);	
	$(organisationsRowElement).data("is-selected", 1);
	setOrganisationsInViewPage(organisationsDataObject);
}

function setOrganisationsInViewPage(organisationsDataObject, paramsMap)
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
	var organisationsId = organisationsDataObject['organisationsId'];
	$('#'+prefix+'idValueForOrganisations').val(organisationsId);
		
	//Input
	if(organisationsDataObject.hasOwnProperty('organisationName'))
	{
		var organisationName = organisationsDataObject['organisationName'];            		
		$('#'+prefix+'Organisations_organisationName').val(organisationName);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('addressLine1'))
	{
		var addressLine1 = organisationsDataObject['addressLine1'];            		
		$('#'+prefix+'Organisations_addressLine1').val(addressLine1);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('addressLine2'))
	{
		var addressLine2 = organisationsDataObject['addressLine2'];            		
		$('#'+prefix+'Organisations_addressLine2').val(addressLine2);
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('city'))
	{
		var city = organisationsDataObject['city'];            		
		$('#'+prefix+'Organisations_city').val(city)
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('residentState'))
	{
		var residentState = organisationsDataObject['residentState'];            		
		$('#'+prefix+'Organisations_residentState').val(residentState)
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('pinCode'))
	{
		var pinCode = organisationsDataObject['pinCode'];            		
		$('#'+prefix+'Organisations_pinCode').val(pinCode);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('databaseName'))
	{
		var databaseName = organisationsDataObject['databaseName'];            		
		$('#'+prefix+'Organisations_databaseName').val(databaseName);
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('country'))
	{
		var country = organisationsDataObject['country'];            		
		$('#'+prefix+'Organisations_country').val(country)
	}

	if(organisationsDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = organisationsDataObject['vwTxnStatus'];
		$('[data-name="organisationsActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnOrganisations(this)" >' +organisationsDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="organisationsActionButtonDiv"]').append(buttonElement);
		$('[data-name="organisationsActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="organisationsActionButtonDiv"]').hide();
	}
	$('[data-name="organisationsCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="organisationsViewFormButtonsDiv"]').css("display", "inline");
	//loadOrganisationsViewData(organisationsDataObject);
	disbaleOrganisationsUpdateDisallowedFields(paramsMap);
	doAfterOrganisationsPanelRefreshed();
    
    
}
function disbaleOrganisationsUpdateDisallowedFields(paramsMap)
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
function loadOrganisationsViewData(organisationsDataObject, paramsMap)
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
	var organisationsId = organisationsDataObject['organisationsId'];
	$('#'+prefix+'idValueForOrganisations').val(organisationsId);
		
	if(organisationsDataObject.hasOwnProperty('organisationName'))
	{
		var organisationName = organisationsDataObject['organisationName'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_organisationName"]').text(organisationName);
	}
	
	if(organisationsDataObject.hasOwnProperty('addressLine1'))
	{
		var addressLine1 = organisationsDataObject['addressLine1'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_addressLine1"]').text(addressLine1);
	}
	
	if(organisationsDataObject.hasOwnProperty('addressLine2'))
	{
		var addressLine2 = organisationsDataObject['addressLine2'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_addressLine2"]').text(addressLine2);
	}
	
	if(organisationsDataObject.hasOwnProperty('city'))
	{
		var city = organisationsDataObject['city'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_city"]').text(city);
	}
	
	if(organisationsDataObject.hasOwnProperty('residentState'))
	{
		var residentState = organisationsDataObject['residentState'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_residentState"]').text(residentState);
	}
	
	if(organisationsDataObject.hasOwnProperty('pinCode'))
	{
		var pinCode = organisationsDataObject['pinCode'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_pinCode"]').text(pinCode);
	}
	
	if(organisationsDataObject.hasOwnProperty('databaseName'))
	{
		var databaseName = organisationsDataObject['databaseName'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_databaseName"]').text(databaseName);
	}
	
	if(organisationsDataObject.hasOwnProperty('country'))
	{
		var country = organisationsDataObject['country'];            		
		parentElement.find('[data-name="'+prefix+'Organisations_country"]').text(country);
	}

}
function ajaxDemoForOrganisations()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForOrganisations').textContent;	
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
			refreshPanelForOrganisations();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForOrganisations(msg)
{
	var executeCustomAPIButtonForOrganisations = document.getElementById("FormWBEntity:executeCustomAPIButtonForOrganisations");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Organisations_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForOrganisations.click();
}
function refreshPanelForOrganisations()
{
	var demoRefreshButtonForOrganisations = document.getElementById("FormWBEntity:demoRefreshButtonOrganisations");
	demoRefreshButtonForOrganisations.click();
}
function initializePanelOnLoadForCreateOrganisations(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForOrganisations = initParamsObj;
	initializeOrganisationsPage();	
	doAfterOrganisationsPanelRefreshed();
	initializeOrganisationsLookupFields(initParamsObj);
	doAfterPanelInitializedForOrganisationsExt();
	fetchOrganisationsDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="organisationsCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForOrganisations = 0;
function initializePanelOnLoadForSearchOrganisations()
{
	initializeOrganisationsPage();	
	initializeOrganisationsLookupFields();
	doAfterPanelInitializedForOrganisationsExt();
	gLoadDashboardResultsForOrganisations =1;
	//retrieveOrganisationsList();
}
function initializePanelOnLoadForViewOrganisations(urlParams)
{
	initializeOrganisationsPage();	
	doAfterOrganisationsPanelRefreshed();
	initializeOrganisationsLookupFields(urlParams);
	doAfterPanelInitializedForOrganisationsExt();
	retrieveOrganisations(urlParams);
	initDatePicker();
	$('[data-name="organisationsViewFormButtonsDiv"]').css("display", "inline");
}
function initializeOrganisationsPage()
{
	initializePageOnload();	
	//initializeOrganisationsTemplateVariables();
}
function initializeOrganisationsTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="organisationsSearchResults"]').find('[data-name="organisationsRow"]');
	if(searchResultsRowObj.length > 0 && gOrganisationsSearchResultsTableRowTemplate.length == 0)
	{
		gOrganisationsSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveOrganisations(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var organisationsId = paramsMap['organisationsId'];
	var organisations = {};
	organisations['organisationsId'] = organisationsId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				organisations[key] = paramsMap[key];
			}
	}
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
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
                	var organisationsDataObject = responseData['organisationsDataObject'];
    				setOrganisationsInViewPage(organisationsDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForOrganisations()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchOrganisationsDefaultData();
}
function fetchOrganisationsDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'Organisations', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var organisations = responseData['organisations'];
            	document.getElementById('idValueForOrganisations').value = '';
			    
            	setOrganisationsData(organisations);
            }
        }
    });	
}
function fetchOrganisationsTestData() 
{
	var organisations = getDataForOrganisations();
    var searchJsonData = {'objectType' : 'Organisations', 'paramsInfo' : JSON.stringify(organisations)};
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
            	var organisations = responseData['organisations'];
            	document.getElementById('idValueForOrganisations').value = '';
			    
            	setOrganisationsData(organisations);
            }
        }
    });	
}
function setOrganisationsData(organisationsDataObject)
{
	var prefix = "";
		
	//Input
	if(organisationsDataObject.hasOwnProperty('organisationName'))
	{
		var organisationName = organisationsDataObject['organisationName'];            		
		$('#'+prefix+'Organisations_organisationName').val(organisationName);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('addressLine1'))
	{
		var addressLine1 = organisationsDataObject['addressLine1'];            		
		$('#'+prefix+'Organisations_addressLine1').val(addressLine1);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('addressLine2'))
	{
		var addressLine2 = organisationsDataObject['addressLine2'];            		
		$('#'+prefix+'Organisations_addressLine2').val(addressLine2);
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('city'))
	{
		var city = organisationsDataObject['city'];            		
		$('#'+prefix+'Organisations_city').val(city);
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('residentState'))
	{
		var residentState = organisationsDataObject['residentState'];            		
		$('#'+prefix+'Organisations_residentState').val(residentState);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('pinCode'))
	{
		var pinCode = organisationsDataObject['pinCode'];            		
		$('#'+prefix+'Organisations_pinCode').val(pinCode);
	}
	
	//Input
	if(organisationsDataObject.hasOwnProperty('databaseName'))
	{
		var databaseName = organisationsDataObject['databaseName'];            		
		$('#'+prefix+'Organisations_databaseName').val(databaseName);
	}
	
	//Combo
	if(organisationsDataObject.hasOwnProperty('country'))
	{
		var country = organisationsDataObject['country'];            		
		$('#'+prefix+'Organisations_country').val(country);
	}

	$('[data-name="organisationsActionButtonDiv"]').hide();
}
function initializeOrganisationsLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="organisationsSearchResultsDiv"]');
	
    
}

function doAfterOrganisationsPanelRefreshed()
{
    //doAfterPanelRefreshedForOrganisationsExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForOrganisations(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'city')
	{
	}
	else if(fieldName == 'residentState')
	{
	}
	else if(fieldName == 'country')
	{
	}

	doAfterSelectOptionChangedForOrganisationsExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForOrganisations(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForOrganisationsExt(fieldName)
}
function getEntityIdForOrganisations()
{
	var idValue = document.getElementById('FormWBEntity:idValueForOrganisations').textContent;	
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
function openPrintPageForOrganisations()
{
	var entityId = getEntityIdForOrganisations();
	if(entityId>0)
	{
	    window.open("/reports/generated/Organisations.jsp?organisationsId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForOrganisations(paramsMap)
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
	var organisations = {};
		
	//Input
	if($("#"+prefix+"Organisations_organisationName").length == 1)
	{
		var organisationName = $('#'+prefix+'Organisations_organisationName').val();
		organisations['organisationName'] = organisationName;
	}
	
	//Input
	if($("#"+prefix+"Organisations_addressLine1").length == 1)
	{
		var addressLine1 = $('#'+prefix+'Organisations_addressLine1').val();
		organisations['addressLine1'] = addressLine1;
	}
	
	//Input
	if($("#"+prefix+"Organisations_addressLine2").length == 1)
	{
		var addressLine2 = $('#'+prefix+'Organisations_addressLine2').val();
		organisations['addressLine2'] = addressLine2;
	}
	
	//Combo
	if($("#"+prefix+"Organisations_city").length == 1)
	{
		var city = $('#'+prefix+'Organisations_city').val();
		organisations['city'] = city;
	}
	
	//Combo
	if($("#"+prefix+"Organisations_residentState").length == 1)
	{
		var residentState = $('#'+prefix+'Organisations_residentState').val();
		organisations['residentState'] = residentState;
	}
	
	//Input
	if($("#"+prefix+"Organisations_pinCode").length == 1)
	{
		var pinCode = $('#'+prefix+'Organisations_pinCode').val();
		organisations['pinCode'] = pinCode;
	}
	
	//Input
	if($("#"+prefix+"Organisations_databaseName").length == 1)
	{
		var databaseName = $('#'+prefix+'Organisations_databaseName').val();
		organisations['databaseName'] = databaseName;
	}
	
	//Combo
	if($("#"+prefix+"Organisations_country").length == 1)
	{
		var country = $('#'+prefix+'Organisations_country').val();
		organisations['country'] = country;
	}

	
	return organisations;
}
function createOrganisations(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var organisations = getDataForOrganisations(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				organisations[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForOrganisations)
	{
		paramsInfo[key] = gInitParamsObjForOrganisations[key];
	}
	var validationObject = doOrganisationsValidation(organisations, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	organisations['additionalParamsInfo'] = paramsInfo;
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
	var urlContextPath = "";//getContextPath();
	if(gOrganisationsRequestUnderProcess == 1)
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
            	gOrganisationsRequestUnderProcess = 0;
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gOrganisationsRequestUnderProcess = 0;
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
            	var organisationsId = responseData['organisationsId'];
            	var popupElement = $('[data-name="OrganisationsPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var organisationsDataObject = responseData['organisationsDataObject'];
    				setOrganisationsInViewPage(organisationsDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewOrganisations.html?organisationsId="+organisationsId; 
        		}
				
            }
        }
    });
}
function resetTableForOrganisations()
{
	var organisationsListElement = $("[data-name='organisationsList']");
	var previousDataRowList  = organisationsListElement.find('[data-name="organisationsRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForOrganisations(paramsMap)
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
	$('#'+prefix+'idValueForOrganisations').val('');
		
	//Input
	$('#'+prefix+'Organisations_organisationName').val('');
	
	//Input
	$('#'+prefix+'Organisations_addressLine1').val('');
	
	//Input
	$('#'+prefix+'Organisations_addressLine2').val('');
	
	//Combo
	$('#'+prefix+'Organisations_city').val('');
	
	//Combo
	$('#'+prefix+'Organisations_residentState').val('');
	
	//Input
	$('#'+prefix+'Organisations_pinCode').val('');
	
	//Input
	$('#'+prefix+'Organisations_databaseName').val('');
	
	//Combo
	$('#'+prefix+'Organisations_country').val('');

	$('[data-name="organisationsCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="organisationsViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="organisationsActionButtonDiv"]').hide();
	enableOrganisationsUpdateDisallowedFields(paramsMap);
    
}
function enableOrganisationsUpdateDisallowedFields(paramsMap)
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
function updateOrganisations(paramsMap)
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
	var organisations = getDataForOrganisations(paramsMap)
	if($("#"+prefix+"idValueForOrganisations").length == 1)
	{
		var organisationsId = $("#"+prefix+"idValueForOrganisations").val();
		organisations['organisationsId'] = organisationsId;
	}
	var validationObject = doOrganisationsValidation(organisations, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
	var urlContextPath = "";//getContextPath();
	if(gOrganisationsRequestUnderProcess == 1)
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
                    	gOrganisationsRequestUnderProcess = 0;
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gOrganisationsRequestUnderProcess = 0;
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
function deleteOrganisations(paramsMap)
{		
	var organisationsId = document.getElementById('idValueForOrganisations').value;
	deleteSelectedOrganisations(organisationsId, paramsMap);
}
function deleteSelectedOrganisations(organisationsId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var organisations = {};
	organisations['organisationsId'] = organisationsId;	
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
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
function loadSearchResultsForOrganisations()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Organisations'};
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
            	var organisationsSearchResultsElement = $("[data-name='organisationsSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var organisationsDataObject = searchResultObjectsList[i];
					            		var organisationName = organisationsDataObject['organisationName'];            		
            		var addressLine1 = organisationsDataObject['addressLine1'];            		
            		var addressLine2 = organisationsDataObject['addressLine2'];            		
            		var city = organisationsDataObject['city'];            		
            		var residentState = organisationsDataObject['residentState'];            		
            		var pinCode = organisationsDataObject['pinCode'];            		
            		var databaseName = organisationsDataObject['databaseName'];            		
            		var country = organisationsDataObject['country'];            		

            		var resultRowTemnplate = $(gOrganisationsSearchResultsTableRowTemplate);
					            		var organisationName = organisationsDataObject['organisationName'];            		
            	    resultRowTemnplate.find("[data-name='organisationName']").text(organisationName);
            		var addressLine1 = organisationsDataObject['addressLine1'];            		
            	    resultRowTemnplate.find("[data-name='addressLine1']").text(addressLine1);
            		var addressLine2 = organisationsDataObject['addressLine2'];            		
            	    resultRowTemnplate.find("[data-name='addressLine2']").text(addressLine2);
            		var city = organisationsDataObject['city'];            		
            	    resultRowTemnplate.find("[data-name='city']").text(city);
            		var residentState = organisationsDataObject['residentState'];            		
            	    resultRowTemnplate.find("[data-name='residentState']").text(residentState);
            		var pinCode = organisationsDataObject['pinCode'];            		
            	    resultRowTemnplate.find("[data-name='pinCode']").text(pinCode);
            		var databaseName = organisationsDataObject['databaseName'];            		
            	    resultRowTemnplate.find("[data-name='databaseName']").text(databaseName);
            		var country = organisationsDataObject['country'];            		
            	    resultRowTemnplate.find("[data-name='country']").text(country);

            	    resultRowTemnplate.data('organisations', organisationsDataObject);
            	    organisationsSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gOrganisationsSearchResultsTableRowTemplate = ""; 
function openViewPageForOrganisationsForEdit(organisationsLinkElement)
{
	var organisationsRowElement = $(organisationsLinkElement).parents('[data-name="organisationsRow"]');
    var organisationsDataObject  = organisationsRowElement.data('organisations');
	var organisationsId = organisationsDataObject['organisationsId'];
	var organisationsPopupElement = $('[data-name="OrganisationsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setOrganisationsInViewPage(organisationsDataObject);
	    $("#Organisations-tab").trigger("click");
	}
	else if(organisationsPopupElement.length > 0)
	{
	    setOrganisationsInViewPage(organisationsDataObject);
		$('[data-name="OrganisationsPopup"]').find('[data-name="organisationsCreateFormButtonsDiv"]').hide();
		$('[data-name="OrganisationsPopup"]').find('[data-name="organisationsViewFormButtonsDiv"]').show();
	    showPopup('OrganisationsPopup', organisationsLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewOrganisations.html?organisationsId="+organisationsId;
		window.open(viewLink, "_blank"); 	
	}
}
function openOrganisationsCreatePageForNew(linkElement)
{
	var organisationsPopupElement = $('[data-name="OrganisationsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForOrganisations();
	    $("#Organisations-tab").trigger("click");
    }
	else if(organisationsPopupElement.length > 0)
	{
		resetFormForOrganisations();
	    showPopup('OrganisationsPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateOrganisations.html";
    }
}
function showOrganisationsPopupForEdit(organisationsLinkElement)
{
	var organisationsRowElement = $(organisationsLinkElement).parents('[data-name="organisationsRow"]');
    var organisationsDataObject  = organisationsRowElement.data('organisations');
    setOrganisationsInViewPage(organisationsDataObject);
    showPopup('OrganisationsPopup', organisationsLinkElement, 1);
    $("#Organisations-tab").trigger("click");
}
function showOrganisationsPopupForNew(buttonElement)
{
	resetFormForOrganisations();
    showPopup('OrganisationsPopup', buttonElement, 1);
    $("#Organisations-tab").trigger("click");
}
function deleteThisOrganisations(organisationsLinkElement, paramsMap)
{
	var organisationsRowElement = $(organisationsLinkElement).parents('[data-name="organisationsRow"]');
    var organisationsDataObject  = organisationsRowElement.data('organisations');
	var organisationsId = organisationsDataObject['organisationsId'];
	deleteSelectedOrganisations(organisationsId, paramsMap);
}
function displayOrganisationsList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + organisationsSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var organisationsSearchResults = searchResultsDiv.find('[data-name="organisationsSearchResults"]');
	//organisationsSearchResults.find('[data-name="organisationsRow"]').remove();
	var previousDataRowList  = organisationsSearchResults.find('[data-name="organisationsRow"]');
	organisationsSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = organisationsSearchResults.find('[data-name="organisationsRow"]');
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
        var organisationsDataObject = searchResultObjectsList[i];
        loadOrganisationsViewData(organisationsDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = organisationsDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='organisationsVwTxnStatus']").text(vwTxnStatus);
		if(organisationsDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="organisationsActionButton"]').text(organisationsDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="organisationsActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="organisationsActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="organisationsRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="organisationsRejectButton"]').show();
		}
	    resultRowTemplate.data('organisations', organisationsDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    organisationsSearchResults.append(resultRowTemplate);
	    processResultRowForOrganisationsExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForOrganisations == 1)
	{
    	getDashboardResultsForOrganisations();
	}
}
var organisationsSearchResultsDivName = "organisationsSearchResultsDiv";
var gOrganisationsSearchInputParamsList = [];
function retrieveOrganisationsList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + organisationsSearchResultsDivName + '"]');
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
    fetchOrganisationsSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchOrganisationsSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getOrganisationsSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = organisationsSearchResultsDivName; 
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
        'objectType': "Organisations"
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
            	this.showPreviousRecords = "showPreviousOrganisationsRecords";
            	this.showCurrentPageRecords = "showCurrentPageOrganisationsRecords";
            	this.showNextRecords = "showNextOrganisationsRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchOrganisationsResults";
                var organisationsList = responseData['organisationsList'];
                var currContextObj = this; 
                var successCallbackFunction = displayOrganisationsList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'organisationsList', 'matchingSearchResultsCount', this.searchResultsDivName, 'organisationsSearchResults', 'organisationsRow', setOrganisationsSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getOrganisationsSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + organisationsSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="organisationsDB_organisationName"]').length == 1)
		{
		    var organisationName = searchDiv.find('[data-name="organisationsDB_organisationName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'organisationName', 'userInputValue':organisationName});
		}
		
		//Input
		if(searchDiv.find('[data-name="organisationsDB_addressLine1"]').length == 1)
		{
		    var addressLine1 = searchDiv.find('[data-name="organisationsDB_addressLine1"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'addressLine1', 'userInputValue':addressLine1});
		}
		
		//Input
		if(searchDiv.find('[data-name="organisationsDB_addressLine2"]').length == 1)
		{
		    var addressLine2 = searchDiv.find('[data-name="organisationsDB_addressLine2"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'addressLine2', 'userInputValue':addressLine2});
		}
		
		//Combo
		if(searchDiv.find('[data-name="organisationsDB_city"]').length == 1)
		{
		    var city = searchDiv.find('[data-name="organisationsDB_city"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'city', 'userInputValue':city});
		}
		
		//Combo
		if(searchDiv.find('[data-name="organisationsDB_residentState"]').length == 1)
		{
		    var residentState = searchDiv.find('[data-name="organisationsDB_residentState"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'residentState', 'userInputValue':residentState});
		}
		
		//Input
		if(searchDiv.find('[data-name="organisationsDB_pinCode"]').length == 1)
		{
		    var pinCode = searchDiv.find('[data-name="organisationsDB_pinCode"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'pinCode', 'userInputValue':pinCode});
		}
		
		//Input
		if(searchDiv.find('[data-name="organisationsDB_databaseName"]').length == 1)
		{
		    var databaseName = searchDiv.find('[data-name="organisationsDB_databaseName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'databaseName', 'userInputValue':databaseName});
		}
		
		//Combo
		if(searchDiv.find('[data-name="organisationsDB_country"]').length == 1)
		{
		    var country = searchDiv.find('[data-name="organisationsDB_country"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'country', 'userInputValue':country});
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
        gOrganisationsSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gOrganisationsSearchInputParamsList;
    }
    return searchInputParams;
}
function setOrganisationsSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + organisationsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gOrganisationsSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gOrganisationsSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageOrganisationsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = organisationsSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchOrganisationsSearchResultsList);
}
function showPreviousOrganisationsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = organisationsSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchOrganisationsSearchResultsList);
}
function showNextOrganisationsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = organisationsSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchOrganisationsSearchResultsList);
}
function showPrevOrNextSearchOrganisationsResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousOrganisationsRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextOrganisationsRecords(e);
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
function lookupRowSelectedForOrganisations(attributeName, attributeId)
{
	var organisations = getDataForOrganisations();
	organisations['attributeName'] = attributeName;
	organisations['attributeId'] = attributeId;
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var organisations = responseData['organisations'];
            	setOrganisationsData(organisations);
            }
        }
    });	
}
function selectOptionChangedForOrganisations(attributeName)
{
	var organisations = getDataForOrganisations();
	organisations['attributeName'] = attributeName;
    var organisationsJsonData = {'paramsInfo': JSON.stringify(organisations), 'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var organisations = responseData['organisations'];
            	setOrganisationsData(organisations);
            	doAfterOrganisationsPanelRefreshed();
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
function retrieveDependentOrganisationsList(paramsMap)
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
    var searchJsonData = {'objectType' : 'Organisations', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var organisationsList = responseData['organisationsList'];
            	var organisationsListElement = $("[data-name='organisationsList']");
            	var previousDataRowList  = organisationsListElement.find('[data-name="organisationsRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = organisationsListElement.find('[data-name="organisationsRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//organisationsListElement.empty();
            	for(var i=0; i<organisationsList.length; i++)
        		{
            		var organisationsDataObject = organisationsList[i];
            		//var resultRowTemplate = $(gOrganisationsSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
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
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    organisationsListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForOrganisations(customEventName)
{
	var organisationsId = document.getElementById('idValueForOrganisations').value;
	var organisations = {'organisationsId':organisationsId, 'customEventName':customEventName};
    var organisationsJsonData = {'paramsInfo':JSON.stringify(organisations), 'objectType' : 'Organisations'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForOrganisationsExt(customEventName);
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var organisations = responseData['organisations'];
        		setOrganisationsInViewPage(organisations);
            }
    		doAfterExecuteCustomAPIForOrganisationsExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnOrganisations(e, paramsMap)
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
	var organisationsId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var organisationsDataObject  = rowObj.data('organisations');
    	organisationsId = organisationsDataObject['organisationsId'];
	}
    else
	{
    	organisationsId = document.getElementById('idValueForOrganisations').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var organisationsSearchParams = {'organisationsId':organisationsId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	organisationsSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var organisationsJsonData = {'paramsInfo':JSON.stringify(organisationsSearchParams),  'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var organisations = responseData['organisations'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="organisationsRowActionButtonDiv"]').hide();
            		if(organisations.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = organisations['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(organisations['vwTxnStatus']);
            			selectedRowObj.find('[data-name="organisationsActionButton"]').text(organisations["nextAction"]);
            			selectedRowObj.find('[data-name="organisationsActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setOrganisationsInViewPage(organisations);
    			}
            }
        }
    });	
}
function downloadOrganisationsData()
{		
	var organisations = {};
    var organisationsJsonData = {'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=Organisations");
            }
        }
    });
}
function uploadOrganisationsData(fileInfo)
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
	var organisations = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var organisationsJsonData = {'paramsInfo':JSON.stringify(organisations),  'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Organisations");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForOrganisations()
{
    var organisationsJsonData = {'objectType' : 'Organisations'};
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
        data: organisationsJsonData,
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



function doOrganisationsLengthValidation(organisationsDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(organisationsDataObject['organisationName'], 50))
	{
		alertMessage += "\n Company Name length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['addressLine1'], 50))
	{
		alertMessage += "\n Address Line1 length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['addressLine2'], 50))
	{
		alertMessage += "\n Address Line2 length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['city'], 50))
	{
		alertMessage += "\n City length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['residentState'], 50))
	{
		alertMessage += "\n Resident State length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['pinCode'], 50))
	{
		alertMessage += "\n Pin Code length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['databaseName'], 50))
	{
		alertMessage += "\n Database Name length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(organisationsDataObject['country'], 50))
	{
		alertMessage += "\n Country length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doOrganisationsManadatoryValidation(organisationsDataObject, paramsMap)
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
		
	var organisationName = organisationsDataObject['organisationName'];
	if(!organisationName || organisationName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_organisationName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Company Name";
		    validationPassed = false;
		}
	}
	
	var addressLine1 = organisationsDataObject['addressLine1'];
	if(!addressLine1 || addressLine1.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_addressLine1").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Address Line1";
		    validationPassed = false;
		}
	}
	
	var city = organisationsDataObject['city'];
	if(!city || city.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_city").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter City";
		    validationPassed = false;
		}
	}
	
	var residentState = organisationsDataObject['residentState'];
	if(!residentState || residentState.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_residentState").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Resident State";
		    validationPassed = false;
		}
	}
	
	var pinCode = organisationsDataObject['pinCode'];
	if(!pinCode || pinCode.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_pinCode").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Pin Code";
		    validationPassed = false;
		}
	}
	
	var databaseName = organisationsDataObject['databaseName'];
	if(!databaseName || databaseName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_databaseName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Database Name";
		    validationPassed = false;
		}
	}
	
	var country = organisationsDataObject['country'];
	if(!country || country.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Organisations_country").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Country";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doOrganisationsValidation(organisationsDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doOrganisationsLengthValidation(organisationsDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doOrganisationsManadatoryValidation(organisationsDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
