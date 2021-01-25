/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'HospName' : 'FormWBEntity:Hospital_hospName' 
 *	 * 'HospAddress' : 'FormWBEntity:Hospital_hospAddress' 
 *	
 */
var gInitParamsObjForHospital = {};
var gHospitalRequestUnderProcess = 0;
function selectThisHospitalForEdit(hospitalRowElement)
{
    var hospitalDataObject  = $(hospitalRowElement).data('hospital');
    var hospitalList = $('[data-name="hospitalSearchResults"]').find('[data-name="hospitalRow"]');
	hospitalList.data("is-selected", 0);	
	$(hospitalRowElement).data("is-selected", 1);
	setHospitalInViewPage(hospitalDataObject);
}

function setHospitalInViewPage(hospitalDataObject, paramsMap)
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
	var hospitalId = hospitalDataObject['hospitalId'];
	$('#'+prefix+'idValueForHospital').val(hospitalId);
		
	//Input
	if(hospitalDataObject.hasOwnProperty('hospName'))
	{
		var hospName = hospitalDataObject['hospName'];            		
		$('#'+prefix+'Hospital_hospName').val(hospName);
	}
	
	//Text
	if(hospitalDataObject.hasOwnProperty('hospAddress'))
	{
		var hospAddress = hospitalDataObject['hospAddress'];            		
		$('#'+prefix+'Hospital_hospAddress').val(hospAddress);
	}

	if(hospitalDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = hospitalDataObject['vwTxnStatus'];
		$('[data-name="hospitalActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnHospital(this)" >' +hospitalDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="hospitalActionButtonDiv"]').append(buttonElement);
		$('[data-name="hospitalActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="hospitalActionButtonDiv"]').hide();
	}
	$('[data-name="hospitalCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="hospitalViewFormButtonsDiv"]').css("display", "inline");
	//loadHospitalViewData(hospitalDataObject);
	disbaleHospitalUpdateDisallowedFields(paramsMap);
	doAfterHospitalPanelRefreshed();
    
    
}
function disbaleHospitalUpdateDisallowedFields(paramsMap)
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
function loadHospitalViewData(hospitalDataObject, paramsMap)
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
	var hospitalId = hospitalDataObject['hospitalId'];
	$('#'+prefix+'idValueForHospital').val(hospitalId);
		
	if(hospitalDataObject.hasOwnProperty('hospName'))
	{
		var hospName = hospitalDataObject['hospName'];            		
		parentElement.find('[data-name="'+prefix+'Hospital_hospName"]').text(hospName);
	}
	
	if(hospitalDataObject.hasOwnProperty('hospAddress'))
	{
		var hospAddress = hospitalDataObject['hospAddress'];            		
		parentElement.find('[data-name="'+prefix+'Hospital_hospAddress"]').text(hospAddress);
	}

}
function ajaxDemoForHospital()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForHospital').textContent;	
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
			refreshPanelForHospital();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForHospital(msg)
{
	var executeCustomAPIButtonForHospital = document.getElementById("FormWBEntity:executeCustomAPIButtonForHospital");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Hospital_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForHospital.click();
}
function refreshPanelForHospital()
{
	var demoRefreshButtonForHospital = document.getElementById("FormWBEntity:demoRefreshButtonHospital");
	demoRefreshButtonForHospital.click();
}
function initializePanelOnLoadForCreateHospital(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForHospital = initParamsObj;
	initializeHospitalPage();	
	doAfterHospitalPanelRefreshed();
	initializeHospitalLookupFields(initParamsObj);
	doAfterPanelInitializedForHospitalExt();
	fetchHospitalDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="hospitalCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForHospital = 0;
function initializePanelOnLoadForSearchHospital()
{
	initializeHospitalPage();	
	initializeHospitalLookupFields();
	doAfterPanelInitializedForHospitalExt();
	gLoadDashboardResultsForHospital =1;
	//retrieveHospitalList();
}
function initializePanelOnLoadForViewHospital(urlParams)
{
	initializeHospitalPage();	
	doAfterHospitalPanelRefreshed();
	initializeHospitalLookupFields(urlParams);
	doAfterPanelInitializedForHospitalExt();
	retrieveHospital(urlParams);
	initDatePicker();
	$('[data-name="hospitalViewFormButtonsDiv"]').css("display", "inline");
}
function initializeHospitalPage()
{
	initializePageOnload();	
	//initializeHospitalTemplateVariables();
}
function initializeHospitalTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="hospitalSearchResults"]').find('[data-name="hospitalRow"]');
	if(searchResultsRowObj.length > 0 && gHospitalSearchResultsTableRowTemplate.length == 0)
	{
		gHospitalSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveHospital(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var hospitalId = paramsMap['hospitalId'];
	var hospital = {};
	hospital['hospitalId'] = hospitalId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				hospital[key] = paramsMap[key];
			}
	}
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
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
                	var hospitalDataObject = responseData['hospitalDataObject'];
    				setHospitalInViewPage(hospitalDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForHospital()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchHospitalDefaultData();
}
function fetchHospitalDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'Hospital', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var hospital = responseData['hospital'];
            	document.getElementById('idValueForHospital').value = '';
			    
            	setHospitalData(hospital);
            }
        }
    });	
}
function fetchHospitalTestData() 
{
	var hospital = getDataForHospital();
    var searchJsonData = {'objectType' : 'Hospital', 'paramsInfo' : JSON.stringify(hospital)};
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
            	var hospital = responseData['hospital'];
            	document.getElementById('idValueForHospital').value = '';
			    
            	setHospitalData(hospital);
            }
        }
    });	
}
function setHospitalData(hospitalDataObject)
{
	var prefix = "";
		
	//Input
	if(hospitalDataObject.hasOwnProperty('hospName'))
	{
		var hospName = hospitalDataObject['hospName'];            		
		$('#'+prefix+'Hospital_hospName').val(hospName);
	}
	
	//Text
	if(hospitalDataObject.hasOwnProperty('hospAddress'))
	{
		var hospAddress = hospitalDataObject['hospAddress'];            		
		$('#'+prefix+'Hospital_hospAddress').val(hospAddress);
	}

	$('[data-name="hospitalActionButtonDiv"]').hide();
}
function initializeHospitalLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="hospitalSearchResultsDiv"]');
	
    
}

function doAfterHospitalPanelRefreshed()
{
    //doAfterPanelRefreshedForHospitalExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForHospital(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForHospitalExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForHospital(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForHospitalExt(fieldName)
}
function getEntityIdForHospital()
{
	var idValue = document.getElementById('FormWBEntity:idValueForHospital').textContent;	
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
function openPrintPageForHospital()
{
	var entityId = getEntityIdForHospital();
	if(entityId>0)
	{
	    window.open("/reports/generated/Hospital.jsp?hospitalId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForHospital(paramsMap)
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
	var hospital = {};
		
	//Input
	if($("#"+prefix+"Hospital_hospName").length == 1)
	{
		var hospName = $('#'+prefix+'Hospital_hospName').val();
		hospital['hospName'] = hospName;
	}
	
	//Text
	if($("#"+prefix+"Hospital_hospAddress").length == 1)
	{
		var hospAddress = $('#'+prefix+'Hospital_hospAddress').val();
		hospital['hospAddress'] = hospAddress;
	}

	
	return hospital;
}
function createHospital(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var hospital = getDataForHospital(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				hospital[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForHospital)
	{
		paramsInfo[key] = gInitParamsObjForHospital[key];
	}
	var validationObject = doHospitalValidation(hospital, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	hospital['additionalParamsInfo'] = paramsInfo;
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
	var urlContextPath = "";//getContextPath();
	if(gHospitalRequestUnderProcess == 1)
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
            	gHospitalRequestUnderProcess = 0;
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gHospitalRequestUnderProcess = 0;
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
            	var hospitalId = responseData['hospitalId'];
            	var popupElement = $('[data-name="HospitalPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var hospitalDataObject = responseData['hospitalDataObject'];
    				setHospitalInViewPage(hospitalDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewHospital.html?hospitalId="+hospitalId; 
        		}
				
            }
        }
    });
}
function resetTableForHospital()
{
	var hospitalListElement = $("[data-name='hospitalList']");
	var previousDataRowList  = hospitalListElement.find('[data-name="hospitalRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForHospital(paramsMap)
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
	$('#'+prefix+'idValueForHospital').val('');
		
	//Input
	$('#'+prefix+'Hospital_hospName').val('');
	
	//Text
	$('#'+prefix+'Hospital_hospAddress').val('');

	$('[data-name="hospitalCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="hospitalViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="hospitalActionButtonDiv"]').hide();
	enableHospitalUpdateDisallowedFields(paramsMap);
    
}
function enableHospitalUpdateDisallowedFields(paramsMap)
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
function updateHospital(paramsMap)
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
	var hospital = getDataForHospital(paramsMap)
	if($("#"+prefix+"idValueForHospital").length == 1)
	{
		var hospitalId = $("#"+prefix+"idValueForHospital").val();
		hospital['hospitalId'] = hospitalId;
	}
	var validationObject = doHospitalValidation(hospital, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
	var urlContextPath = "";//getContextPath();
	if(gHospitalRequestUnderProcess == 1)
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
                    	gHospitalRequestUnderProcess = 0;
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gHospitalRequestUnderProcess = 0;
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
function deleteHospital(paramsMap)
{		
	var hospitalId = document.getElementById('idValueForHospital').value;
	deleteSelectedHospital(hospitalId, paramsMap);
}
function deleteSelectedHospital(hospitalId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var hospital = {};
	hospital['hospitalId'] = hospitalId;	
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
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
function loadSearchResultsForHospital()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Hospital'};
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
            	var hospitalSearchResultsElement = $("[data-name='hospitalSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var hospitalDataObject = searchResultObjectsList[i];
					            		var hospName = hospitalDataObject['hospName'];            		
            		var hospAddress = hospitalDataObject['hospAddress'];            		

            		var resultRowTemnplate = $(gHospitalSearchResultsTableRowTemplate);
					            		var hospName = hospitalDataObject['hospName'];            		
            	    resultRowTemnplate.find("[data-name='hospName']").text(hospName);
            		var hospAddress = hospitalDataObject['hospAddress'];            		
            	    resultRowTemnplate.find("[data-name='hospAddress']").text(hospAddress);

            	    resultRowTemnplate.data('hospital', hospitalDataObject);
            	    hospitalSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gHospitalSearchResultsTableRowTemplate = ""; 
function openViewPageForHospitalForEdit(hospitalLinkElement)
{
	var hospitalRowElement = $(hospitalLinkElement).parents('[data-name="hospitalRow"]');
    var hospitalDataObject  = hospitalRowElement.data('hospital');
	var hospitalId = hospitalDataObject['hospitalId'];
	var hospitalPopupElement = $('[data-name="HospitalPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setHospitalInViewPage(hospitalDataObject);
	    $("#Hospital-tab").trigger("click");
	}
	else if(hospitalPopupElement.length > 0)
	{
	    setHospitalInViewPage(hospitalDataObject);
		$('[data-name="HospitalPopup"]').find('[data-name="hospitalCreateFormButtonsDiv"]').hide();
		$('[data-name="HospitalPopup"]').find('[data-name="hospitalViewFormButtonsDiv"]').show();
	    showPopup('HospitalPopup', hospitalLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewHospital.html?hospitalId="+hospitalId;
		window.open(viewLink, "_blank"); 	
	}
}
function openHospitalCreatePageForNew(linkElement)
{
	var hospitalPopupElement = $('[data-name="HospitalPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForHospital();
	    $("#Hospital-tab").trigger("click");
    }
	else if(hospitalPopupElement.length > 0)
	{
		resetFormForHospital();
	    showPopup('HospitalPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateHospital.html";
    }
}
function showHospitalPopupForEdit(hospitalLinkElement)
{
	var hospitalRowElement = $(hospitalLinkElement).parents('[data-name="hospitalRow"]');
    var hospitalDataObject  = hospitalRowElement.data('hospital');
    setHospitalInViewPage(hospitalDataObject);
    showPopup('HospitalPopup', hospitalLinkElement, 1);
    $("#Hospital-tab").trigger("click");
}
function showHospitalPopupForNew(buttonElement)
{
	resetFormForHospital();
    showPopup('HospitalPopup', buttonElement, 1);
    $("#Hospital-tab").trigger("click");
}
function deleteThisHospital(hospitalLinkElement, paramsMap)
{
	var hospitalRowElement = $(hospitalLinkElement).parents('[data-name="hospitalRow"]');
    var hospitalDataObject  = hospitalRowElement.data('hospital');
	var hospitalId = hospitalDataObject['hospitalId'];
	deleteSelectedHospital(hospitalId, paramsMap);
}
function displayHospitalList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + hospitalSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var hospitalSearchResults = searchResultsDiv.find('[data-name="hospitalSearchResults"]');
	//hospitalSearchResults.find('[data-name="hospitalRow"]').remove();
	var previousDataRowList  = hospitalSearchResults.find('[data-name="hospitalRow"]');
	hospitalSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = hospitalSearchResults.find('[data-name="hospitalRow"]');
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
        var hospitalDataObject = searchResultObjectsList[i];
        loadHospitalViewData(hospitalDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = hospitalDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='hospitalVwTxnStatus']").text(vwTxnStatus);
		if(hospitalDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="hospitalActionButton"]').text(hospitalDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="hospitalActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="hospitalActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="hospitalRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="hospitalRejectButton"]').show();
		}
	    resultRowTemplate.data('hospital', hospitalDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    hospitalSearchResults.append(resultRowTemplate);
	    processResultRowForHospitalExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForHospital == 1)
	{
    	getDashboardResultsForHospital();
	}
}
var hospitalSearchResultsDivName = "hospitalSearchResultsDiv";
var gHospitalSearchInputParamsList = [];
function retrieveHospitalList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + hospitalSearchResultsDivName + '"]');
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
    fetchHospitalSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchHospitalSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getHospitalSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = hospitalSearchResultsDivName; 
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
        'objectType': "Hospital"
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
            	this.showPreviousRecords = "showPreviousHospitalRecords";
            	this.showCurrentPageRecords = "showCurrentPageHospitalRecords";
            	this.showNextRecords = "showNextHospitalRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchHospitalResults";
                var hospitalList = responseData['hospitalList'];
                var currContextObj = this; 
                var successCallbackFunction = displayHospitalList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'hospitalList', 'matchingSearchResultsCount', this.searchResultsDivName, 'hospitalSearchResults', 'hospitalRow', setHospitalSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getHospitalSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + hospitalSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="hospitalDB_hospName"]').length == 1)
		{
		    var hospName = searchDiv.find('[data-name="hospitalDB_hospName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'hospName', 'userInputValue':hospName});
		}
		
		//Text
		if(searchDiv.find('[data-name="hospitalDB_hospAddress"]').length == 1)
		{
		    var hospAddress = searchDiv.find('[data-name="hospitalDB_hospAddress"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'hospAddress', 'userInputValue':hospAddress});
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
        gHospitalSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gHospitalSearchInputParamsList;
    }
    return searchInputParams;
}
function setHospitalSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + hospitalSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gHospitalSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gHospitalSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageHospitalRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = hospitalSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchHospitalSearchResultsList);
}
function showPreviousHospitalRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = hospitalSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchHospitalSearchResultsList);
}
function showNextHospitalRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = hospitalSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchHospitalSearchResultsList);
}
function showPrevOrNextSearchHospitalResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousHospitalRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextHospitalRecords(e);
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
function lookupRowSelectedForHospital(attributeName, attributeId)
{
	var hospital = getDataForHospital();
	hospital['attributeName'] = attributeName;
	hospital['attributeId'] = attributeId;
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var hospital = responseData['hospital'];
            	setHospitalData(hospital);
            }
        }
    });	
}
function selectOptionChangedForHospital(attributeName)
{
	var hospital = getDataForHospital();
	hospital['attributeName'] = attributeName;
    var hospitalJsonData = {'paramsInfo': JSON.stringify(hospital), 'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var hospital = responseData['hospital'];
            	setHospitalData(hospital);
            	doAfterHospitalPanelRefreshed();
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
function retrieveDependentHospitalList(paramsMap)
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
    var searchJsonData = {'objectType' : 'Hospital', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var hospitalList = responseData['hospitalList'];
            	var hospitalListElement = $("[data-name='hospitalList']");
            	var previousDataRowList  = hospitalListElement.find('[data-name="hospitalRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = hospitalListElement.find('[data-name="hospitalRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//hospitalListElement.empty();
            	for(var i=0; i<hospitalList.length; i++)
        		{
            		var hospitalDataObject = hospitalList[i];
            		//var resultRowTemplate = $(gHospitalSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var hospName = hospitalDataObject['hospName'];            		
				    resultRowTemplate.find("[data-name='hospName']").text(hospName);
					var hospAddress = hospitalDataObject['hospAddress'];            		
				    resultRowTemplate.find("[data-name='hospAddress']").text(hospAddress);

					
					
            	    resultRowTemplate.data('hospital', hospitalDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    hospitalListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForHospital(customEventName)
{
	var hospitalId = document.getElementById('idValueForHospital').value;
	var hospital = {'hospitalId':hospitalId, 'customEventName':customEventName};
    var hospitalJsonData = {'paramsInfo':JSON.stringify(hospital), 'objectType' : 'Hospital'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForHospitalExt(customEventName);
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var hospital = responseData['hospital'];
        		setHospitalInViewPage(hospital);
            }
    		doAfterExecuteCustomAPIForHospitalExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnHospital(e, paramsMap)
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
	var hospitalId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var hospitalDataObject  = rowObj.data('hospital');
    	hospitalId = hospitalDataObject['hospitalId'];
	}
    else
	{
    	hospitalId = document.getElementById('idValueForHospital').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var hospitalSearchParams = {'hospitalId':hospitalId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	hospitalSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var hospitalJsonData = {'paramsInfo':JSON.stringify(hospitalSearchParams),  'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var hospital = responseData['hospital'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="hospitalRowActionButtonDiv"]').hide();
            		if(hospital.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = hospital['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(hospital['vwTxnStatus']);
            			selectedRowObj.find('[data-name="hospitalActionButton"]').text(hospital["nextAction"]);
            			selectedRowObj.find('[data-name="hospitalActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setHospitalInViewPage(hospital);
    			}
            }
        }
    });	
}
function downloadHospitalData()
{		
	var hospital = {};
    var hospitalJsonData = {'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=Hospital");
            }
        }
    });
}
function uploadHospitalData(fileInfo)
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
	var hospital = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var hospitalJsonData = {'paramsInfo':JSON.stringify(hospital),  'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Hospital");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForHospital()
{
    var hospitalJsonData = {'objectType' : 'Hospital'};
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
        data: hospitalJsonData,
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



function doHospitalLengthValidation(hospitalDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(hospitalDataObject['hospName'], 50))
	{
		alertMessage += "\n HospName length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(hospitalDataObject['hospAddress'], 300))
	{
		alertMessage += "\n HospAddress length is more than allowed(300)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doHospitalManadatoryValidation(hospitalDataObject, paramsMap)
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
function doHospitalValidation(hospitalDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doHospitalLengthValidation(hospitalDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doHospitalManadatoryValidation(hospitalDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
