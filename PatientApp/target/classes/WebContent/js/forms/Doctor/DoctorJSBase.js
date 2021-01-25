/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'DoctorName' : 'FormWBEntity:Doctor_doctorName' 
 *	 * 'HospitalName' : 'FormWBEntity:Doctor_hospitalName' 
 *	
 */
var gInitParamsObjForDoctor = {};
var gDoctorRequestUnderProcess = 0;
function selectThisDoctorForEdit(doctorRowElement)
{
    var doctorDataObject  = $(doctorRowElement).data('doctor');
    var doctorList = $('[data-name="doctorSearchResults"]').find('[data-name="doctorRow"]');
	doctorList.data("is-selected", 0);	
	$(doctorRowElement).data("is-selected", 1);
	setDoctorInViewPage(doctorDataObject);
}

function setDoctorInViewPage(doctorDataObject, paramsMap)
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
	var doctorId = doctorDataObject['doctorId'];
	$('#'+prefix+'idValueForDoctor').val(doctorId);
		
	//Input
	if(doctorDataObject.hasOwnProperty('doctorName'))
	{
		var doctorName = doctorDataObject['doctorName'];            		
		$('#'+prefix+'Doctor_doctorName').val(doctorName);
	}
	
	//Input
	if(doctorDataObject.hasOwnProperty('hospitalName'))
	{
		var hospitalName = doctorDataObject['hospitalName'];            		
		$('#'+prefix+'Doctor_hospitalName').val(hospitalName);
	}

	if(doctorDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = doctorDataObject['vwTxnStatus'];
		$('[data-name="doctorActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnDoctor(this)" >' +doctorDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="doctorActionButtonDiv"]').append(buttonElement);
		$('[data-name="doctorActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="doctorActionButtonDiv"]').hide();
	}
	$('[data-name="doctorCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="doctorViewFormButtonsDiv"]').css("display", "inline");
	//loadDoctorViewData(doctorDataObject);
	disbaleDoctorUpdateDisallowedFields(paramsMap);
	doAfterDoctorPanelRefreshed();
    
    resetFormForPatient();
    resetTableForPatient();
    
        retrieveDependentPatientList(paramsMap);

}
function disbaleDoctorUpdateDisallowedFields(paramsMap)
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
function loadDoctorViewData(doctorDataObject, paramsMap)
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
	var doctorId = doctorDataObject['doctorId'];
	$('#'+prefix+'idValueForDoctor').val(doctorId);
		
	if(doctorDataObject.hasOwnProperty('doctorName'))
	{
		var doctorName = doctorDataObject['doctorName'];            		
		parentElement.find('[data-name="'+prefix+'Doctor_doctorName"]').text(doctorName);
	}
	
	if(doctorDataObject.hasOwnProperty('hospitalName'))
	{
		var hospitalName = doctorDataObject['hospitalName'];            		
		parentElement.find('[data-name="'+prefix+'Doctor_hospitalName"]').text(hospitalName);
	}

}
function ajaxDemoForDoctor()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForDoctor').textContent;	
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
			refreshPanelForDoctor();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForDoctor(msg)
{
	var executeCustomAPIButtonForDoctor = document.getElementById("FormWBEntity:executeCustomAPIButtonForDoctor");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Doctor_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForDoctor.click();
}
function refreshPanelForDoctor()
{
	var demoRefreshButtonForDoctor = document.getElementById("FormWBEntity:demoRefreshButtonDoctor");
	demoRefreshButtonForDoctor.click();
}
function initializePanelOnLoadForCreateDoctor(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForDoctor = initParamsObj;
	initializeDoctorPage();	
	doAfterDoctorPanelRefreshed();
	initializeDoctorLookupFields(initParamsObj);
	doAfterPanelInitializedForDoctorExt();
	fetchDoctorDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="doctorCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForDoctor = 0;
function initializePanelOnLoadForSearchDoctor()
{
	initializeDoctorPage();	
	initializeDoctorLookupFields();
	doAfterPanelInitializedForDoctorExt();
	gLoadDashboardResultsForDoctor =1;
	//retrieveDoctorList();
}
function initializePanelOnLoadForViewDoctor(urlParams)
{
	initializeDoctorPage();	
	doAfterDoctorPanelRefreshed();
	initializeDoctorLookupFields(urlParams);
	doAfterPanelInitializedForDoctorExt();
	retrieveDoctor(urlParams);
	initDatePicker();
	$('[data-name="doctorViewFormButtonsDiv"]').css("display", "inline");
}
function initializeDoctorPage()
{
	initializePageOnload();	
	//initializeDoctorTemplateVariables();
}
function initializeDoctorTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="doctorSearchResults"]').find('[data-name="doctorRow"]');
	if(searchResultsRowObj.length > 0 && gDoctorSearchResultsTableRowTemplate.length == 0)
	{
		gDoctorSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	    initializePatientTemplateVariables();

}
function retrieveDoctor(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var doctorId = paramsMap['doctorId'];
	var doctor = {};
	doctor['doctorId'] = doctorId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				doctor[key] = paramsMap[key];
			}
	}
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
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
        data: doctorJsonData,
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
                	var doctorDataObject = responseData['doctorDataObject'];
    				setDoctorInViewPage(doctorDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForDoctor()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchDoctorDefaultData();
}
function fetchDoctorDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'Doctor', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var doctor = responseData['doctor'];
            	document.getElementById('idValueForDoctor').value = '';
			    
			    resetFormForPatient();
			    resetTableForPatient();
			    
            	setDoctorData(doctor);
            }
        }
    });	
}
function fetchDoctorTestData() 
{
	var doctor = getDataForDoctor();
    var searchJsonData = {'objectType' : 'Doctor', 'paramsInfo' : JSON.stringify(doctor)};
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
            	var doctor = responseData['doctor'];
            	document.getElementById('idValueForDoctor').value = '';
			    
			    resetFormForPatient();
			    resetTableForPatient();
			    
            	setDoctorData(doctor);
            }
        }
    });	
}
function setDoctorData(doctorDataObject)
{
	var prefix = "";
		
	//Input
	if(doctorDataObject.hasOwnProperty('doctorName'))
	{
		var doctorName = doctorDataObject['doctorName'];            		
		$('#'+prefix+'Doctor_doctorName').val(doctorName);
	}
	
	//Input
	if(doctorDataObject.hasOwnProperty('hospitalName'))
	{
		var hospitalName = doctorDataObject['hospitalName'];            		
		$('#'+prefix+'Doctor_hospitalName').val(hospitalName);
	}

	$('[data-name="doctorActionButtonDiv"]').hide();
}
function initializeDoctorLookupFields(paramsMap) 
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
    
    initializePatientLookupSelectList();
    
    var searchDiv = $('[data-name="doctorSearchResultsDiv"]');
	
    
}

function doAfterDoctorPanelRefreshed()
{
    //doAfterPanelRefreshedForDoctorExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForDoctor(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForDoctorExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForDoctor(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForDoctorExt(fieldName)
}
function getEntityIdForDoctor()
{
	var idValue = document.getElementById('FormWBEntity:idValueForDoctor').textContent;	
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
function openPrintPageForDoctor()
{
	var entityId = getEntityIdForDoctor();
	if(entityId>0)
	{
	    window.open("/reports/generated/Doctor.jsp?doctorId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForDoctor(paramsMap)
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
	var doctor = {};
		
	//Input
	if($("#"+prefix+"Doctor_doctorName").length == 1)
	{
		var doctorName = $('#'+prefix+'Doctor_doctorName').val();
		doctor['doctorName'] = doctorName;
	}
	
	//Input
	if($("#"+prefix+"Doctor_hospitalName").length == 1)
	{
		var hospitalName = $('#'+prefix+'Doctor_hospitalName').val();
		doctor['hospitalName'] = hospitalName;
	}

	
	return doctor;
}
function createDoctor(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var doctor = getDataForDoctor(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				doctor[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForDoctor)
	{
		paramsInfo[key] = gInitParamsObjForDoctor[key];
	}
	var validationObject = doDoctorValidation(doctor, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	doctor['additionalParamsInfo'] = paramsInfo;
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
	var urlContextPath = "";//getContextPath();
	if(gDoctorRequestUnderProcess == 1)
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
            	gDoctorRequestUnderProcess = 0;
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gDoctorRequestUnderProcess = 0;
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
            	var doctorId = responseData['doctorId'];
            	var popupElement = $('[data-name="DoctorPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var doctorDataObject = responseData['doctorDataObject'];
    				setDoctorInViewPage(doctorDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewDoctor.html?doctorId="+doctorId; 
        		}
				
            }
        }
    });
}
function resetTableForDoctor()
{
	var doctorListElement = $("[data-name='doctorList']");
	var previousDataRowList  = doctorListElement.find('[data-name="doctorRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForDoctor(paramsMap)
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
	$('#'+prefix+'idValueForDoctor').val('');
		
	//Input
	$('#'+prefix+'Doctor_doctorName').val('');
	
	//Input
	$('#'+prefix+'Doctor_hospitalName').val('');

	$('[data-name="doctorCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="doctorViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="doctorActionButtonDiv"]').hide();
	enableDoctorUpdateDisallowedFields(paramsMap);
    
    resetFormForPatient();
    resetTableForPatient();
    
}
function enableDoctorUpdateDisallowedFields(paramsMap)
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
function updateDoctor(paramsMap)
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
	var doctor = getDataForDoctor(paramsMap)
	if($("#"+prefix+"idValueForDoctor").length == 1)
	{
		var doctorId = $("#"+prefix+"idValueForDoctor").val();
		doctor['doctorId'] = doctorId;
	}
	var validationObject = doDoctorValidation(doctor, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
	var urlContextPath = "";//getContextPath();
	if(gDoctorRequestUnderProcess == 1)
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
                    	gDoctorRequestUnderProcess = 0;
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gDoctorRequestUnderProcess = 0;
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
function deleteDoctor(paramsMap)
{		
	var doctorId = document.getElementById('idValueForDoctor').value;
	deleteSelectedDoctor(doctorId, paramsMap);
}
function deleteSelectedDoctor(doctorId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var doctor = {};
	doctor['doctorId'] = doctorId;	
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
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
        data: doctorJsonData,
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
function loadSearchResultsForDoctor()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Doctor'};
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
            	var doctorSearchResultsElement = $("[data-name='doctorSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var doctorDataObject = searchResultObjectsList[i];
					            		var doctorName = doctorDataObject['doctorName'];            		
            		var hospitalName = doctorDataObject['hospitalName'];            		

            		var resultRowTemnplate = $(gDoctorSearchResultsTableRowTemplate);
					            		var doctorName = doctorDataObject['doctorName'];            		
            	    resultRowTemnplate.find("[data-name='doctorName']").text(doctorName);
            		var hospitalName = doctorDataObject['hospitalName'];            		
            	    resultRowTemnplate.find("[data-name='hospitalName']").text(hospitalName);

            	    resultRowTemnplate.data('doctor', doctorDataObject);
            	    doctorSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gDoctorSearchResultsTableRowTemplate = ""; 
function openViewPageForDoctorForEdit(doctorLinkElement)
{
	var doctorRowElement = $(doctorLinkElement).parents('[data-name="doctorRow"]');
    var doctorDataObject  = doctorRowElement.data('doctor');
	var doctorId = doctorDataObject['doctorId'];
	var doctorPopupElement = $('[data-name="DoctorPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setDoctorInViewPage(doctorDataObject);
	    $("#Doctor-tab").trigger("click");
	}
	else if(doctorPopupElement.length > 0)
	{
	    setDoctorInViewPage(doctorDataObject);
		$('[data-name="DoctorPopup"]').find('[data-name="doctorCreateFormButtonsDiv"]').hide();
		$('[data-name="DoctorPopup"]').find('[data-name="doctorViewFormButtonsDiv"]').show();
	    showPopup('DoctorPopup', doctorLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewDoctor.html?doctorId="+doctorId;
		window.open(viewLink, "_blank"); 	
	}
}
function openDoctorCreatePageForNew(linkElement)
{
	var doctorPopupElement = $('[data-name="DoctorPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForDoctor();
	    $("#Doctor-tab").trigger("click");
    }
	else if(doctorPopupElement.length > 0)
	{
		resetFormForDoctor();
	    showPopup('DoctorPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateDoctor.html";
    }
}
function showDoctorPopupForEdit(doctorLinkElement)
{
	var doctorRowElement = $(doctorLinkElement).parents('[data-name="doctorRow"]');
    var doctorDataObject  = doctorRowElement.data('doctor');
    setDoctorInViewPage(doctorDataObject);
    showPopup('DoctorPopup', doctorLinkElement, 1);
    $("#Doctor-tab").trigger("click");
}
function showDoctorPopupForNew(buttonElement)
{
	resetFormForDoctor();
    showPopup('DoctorPopup', buttonElement, 1);
    $("#Doctor-tab").trigger("click");
}
function deleteThisDoctor(doctorLinkElement, paramsMap)
{
	var doctorRowElement = $(doctorLinkElement).parents('[data-name="doctorRow"]');
    var doctorDataObject  = doctorRowElement.data('doctor');
	var doctorId = doctorDataObject['doctorId'];
	deleteSelectedDoctor(doctorId, paramsMap);
}
function displayDoctorList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + doctorSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var doctorSearchResults = searchResultsDiv.find('[data-name="doctorSearchResults"]');
	//doctorSearchResults.find('[data-name="doctorRow"]').remove();
	var previousDataRowList  = doctorSearchResults.find('[data-name="doctorRow"]');
	doctorSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = doctorSearchResults.find('[data-name="doctorRow"]');
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
        var doctorDataObject = searchResultObjectsList[i];
        loadDoctorViewData(doctorDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = doctorDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='doctorVwTxnStatus']").text(vwTxnStatus);
		if(doctorDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="doctorActionButton"]').text(doctorDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="doctorActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="doctorActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="doctorRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="doctorRejectButton"]').show();
		}
	    resultRowTemplate.data('doctor', doctorDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    doctorSearchResults.append(resultRowTemplate);
	    processResultRowForDoctorExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForDoctor == 1)
	{
    	getDashboardResultsForDoctor();
	}
}
var doctorSearchResultsDivName = "doctorSearchResultsDiv";
var gDoctorSearchInputParamsList = [];
function retrieveDoctorList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + doctorSearchResultsDivName + '"]');
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
    fetchDoctorSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchDoctorSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getDoctorSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = doctorSearchResultsDivName; 
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
        'objectType': "Doctor"
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
            	this.showPreviousRecords = "showPreviousDoctorRecords";
            	this.showCurrentPageRecords = "showCurrentPageDoctorRecords";
            	this.showNextRecords = "showNextDoctorRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchDoctorResults";
                var doctorList = responseData['doctorList'];
                var currContextObj = this; 
                var successCallbackFunction = displayDoctorList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'doctorList', 'matchingSearchResultsCount', this.searchResultsDivName, 'doctorSearchResults', 'doctorRow', setDoctorSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getDoctorSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + doctorSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="doctorDB_doctorName"]').length == 1)
		{
		    var doctorName = searchDiv.find('[data-name="doctorDB_doctorName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'doctorName', 'userInputValue':doctorName});
		}
		
		//Input
		if(searchDiv.find('[data-name="doctorDB_hospitalName"]').length == 1)
		{
		    var hospitalName = searchDiv.find('[data-name="doctorDB_hospitalName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'hospitalName', 'userInputValue':hospitalName});
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
        gDoctorSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gDoctorSearchInputParamsList;
    }
    return searchInputParams;
}
function setDoctorSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + doctorSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gDoctorSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gDoctorSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageDoctorRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = doctorSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchDoctorSearchResultsList);
}
function showPreviousDoctorRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = doctorSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchDoctorSearchResultsList);
}
function showNextDoctorRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = doctorSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchDoctorSearchResultsList);
}
function showPrevOrNextSearchDoctorResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousDoctorRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextDoctorRecords(e);
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
function lookupRowSelectedForDoctor(attributeName, attributeId)
{
	var doctor = getDataForDoctor();
	doctor['attributeName'] = attributeName;
	doctor['attributeId'] = attributeId;
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var doctor = responseData['doctor'];
            	setDoctorData(doctor);
            }
        }
    });	
}
function selectOptionChangedForDoctor(attributeName)
{
	var doctor = getDataForDoctor();
	doctor['attributeName'] = attributeName;
    var doctorJsonData = {'paramsInfo': JSON.stringify(doctor), 'objectType' : 'Doctor'};
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var doctor = responseData['doctor'];
            	setDoctorData(doctor);
            	doAfterDoctorPanelRefreshed();
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
function retrieveDependentDoctorList(paramsMap)
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
    var searchJsonData = {'objectType' : 'Doctor', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var doctorList = responseData['doctorList'];
            	var doctorListElement = $("[data-name='doctorList']");
            	var previousDataRowList  = doctorListElement.find('[data-name="doctorRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = doctorListElement.find('[data-name="doctorRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//doctorListElement.empty();
            	for(var i=0; i<doctorList.length; i++)
        		{
            		var doctorDataObject = doctorList[i];
            		//var resultRowTemplate = $(gDoctorSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var doctorName = doctorDataObject['doctorName'];            		
				    resultRowTemplate.find("[data-name='doctorName']").text(doctorName);
					var hospitalName = doctorDataObject['hospitalName'];            		
				    resultRowTemplate.find("[data-name='hospitalName']").text(hospitalName);

					
					
            	    resultRowTemplate.data('doctor', doctorDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    doctorListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForDoctor(customEventName)
{
	var doctorId = document.getElementById('idValueForDoctor').value;
	var doctor = {'doctorId':doctorId, 'customEventName':customEventName};
    var doctorJsonData = {'paramsInfo':JSON.stringify(doctor), 'objectType' : 'Doctor'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForDoctorExt(customEventName);
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var doctor = responseData['doctor'];
        		setDoctorInViewPage(doctor);
            }
    		doAfterExecuteCustomAPIForDoctorExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnDoctor(e, paramsMap)
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
	var doctorId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var doctorDataObject  = rowObj.data('doctor');
    	doctorId = doctorDataObject['doctorId'];
	}
    else
	{
    	doctorId = document.getElementById('idValueForDoctor').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var doctorSearchParams = {'doctorId':doctorId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	doctorSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var doctorJsonData = {'paramsInfo':JSON.stringify(doctorSearchParams),  'objectType' : 'Doctor'};
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
        data: doctorJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var doctor = responseData['doctor'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="doctorRowActionButtonDiv"]').hide();
            		if(doctor.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = doctor['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(doctor['vwTxnStatus']);
            			selectedRowObj.find('[data-name="doctorActionButton"]').text(doctor["nextAction"]);
            			selectedRowObj.find('[data-name="doctorActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setDoctorInViewPage(doctor);
    			}
            }
        }
    });	
}
function downloadDoctorData()
{		
	var doctor = {};
    var doctorJsonData = {'objectType' : 'Doctor'};
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
        data: doctorJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=Doctor");
            }
        }
    });
}
function uploadDoctorData(fileInfo)
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
	var doctor = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var doctorJsonData = {'paramsInfo':JSON.stringify(doctor),  'objectType' : 'Doctor'};
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
        data: doctorJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Doctor");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForDoctor()
{
    var doctorJsonData = {'objectType' : 'Doctor'};
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
        data: doctorJsonData,
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



function doDoctorLengthValidation(doctorDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(doctorDataObject['doctorName'], 50))
	{
		alertMessage += "\n DoctorName length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(doctorDataObject['hospitalName'], 50))
	{
		alertMessage += "\n HospitalName length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doDoctorManadatoryValidation(doctorDataObject, paramsMap)
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
function doDoctorValidation(doctorDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doDoctorLengthValidation(doctorDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doDoctorManadatoryValidation(doctorDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
