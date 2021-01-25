/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'PatientName' : 'FormWBEntity:Patient_patientName' 
 *	 * 'PatientGender' : 'FormWBEntity:Patient_patientGender' 
 *	 * 'SelectDoctor' : 'FormWBEntity:Patient_selectDoctor' 
 *	
 */
var gInitParamsObjForPatient = {};
var gPatientRequestUnderProcess = 0;
function selectThisPatientForEdit(patientRowElement)
{
    var patientDataObject  = $(patientRowElement).data('patient');
    var patientList = $('[data-name="patientSearchResults"]').find('[data-name="patientRow"]');
	patientList.data("is-selected", 0);	
	$(patientRowElement).data("is-selected", 1);
	setPatientInViewPage(patientDataObject);
}

function setPatientInViewPage(patientDataObject, paramsMap)
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
	var patientId = patientDataObject['patientId'];
	$('#'+prefix+'idValueForPatient').val(patientId);
		
	//Input
	if(patientDataObject.hasOwnProperty('patientName'))
	{
		var patientName = patientDataObject['patientName'];            		
		$('#'+prefix+'Patient_patientName').val(patientName);
	}
	
	//Combo
	if(patientDataObject.hasOwnProperty('patientGender'))
	{
		var patientGender = patientDataObject['patientGender'];            		
		$('#'+prefix+'Patient_patientGender').val(patientGender)
	}
	
	//LK Dropdown
	if(patientDataObject.hasOwnProperty('selectDoctor'))
	{
		var selectDoctor = patientDataObject['selectDoctor'];
		if(selectDoctor != "")
		{
			var selectDoctorId = selectDoctor['$$LOOKUP_ENTITY_NAME_INIT_LOWER$$Id'];
			var selectDoctorDisplayText = selectDoctor['lookupDisplayText'];
			var selectDoctorLinkElement = $('#'+prefix+'Patient_selectDoctor');
			selectDoctorLinkElement.val(selectDoctorDisplayText);
			selectDoctorLinkElement.data("selectDoctor-id", selectDoctorId);
		}
	}

	if(patientDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = patientDataObject['vwTxnStatus'];
		$('[data-name="patientActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnPatient(this)" >' +patientDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="patientActionButtonDiv"]').append(buttonElement);
		$('[data-name="patientActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="patientActionButtonDiv"]').hide();
	}
	$('[data-name="patientCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="patientViewFormButtonsDiv"]').css("display", "inline");
	//loadPatientViewData(patientDataObject);
	disbalePatientUpdateDisallowedFields(paramsMap);
	doAfterPatientPanelRefreshed();
    
    
}
function disbalePatientUpdateDisallowedFields(paramsMap)
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
function loadPatientViewData(patientDataObject, paramsMap)
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
	var patientId = patientDataObject['patientId'];
	$('#'+prefix+'idValueForPatient').val(patientId);
		
	if(patientDataObject.hasOwnProperty('patientName'))
	{
		var patientName = patientDataObject['patientName'];            		
		parentElement.find('[data-name="'+prefix+'Patient_patientName"]').text(patientName);
	}
	
	if(patientDataObject.hasOwnProperty('patientGender'))
	{
		var patientGender = patientDataObject['patientGender'];            		
		parentElement.find('[data-name="'+prefix+'Patient_patientGender"]').text(patientGender);
	}
	
	if(patientDataObject.hasOwnProperty('selectDoctor'))
	{
		var selectDoctor = patientDataObject['selectDoctor'];
		if(selectDoctor != "")
		{
			var selectDoctorId = patientDataObject['selectDoctorId'];
			var selectDoctorDisplayVal = patientDataObject['selectDoctorDisplayVal'];
			parentElement.find('[data-name="'+prefix+'Patient_selectDoctor"]').text(selectDoctorDisplayVal);
			//selectDoctorLinkElement.data("$$LOOKUP_ENTITY_NAME_INIT_LOWER$$-id", selectDoctorId);
		}
	}

}
function ajaxDemoForPatient()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForPatient').textContent;	
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
			refreshPanelForPatient();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForPatient(msg)
{
	var executeCustomAPIButtonForPatient = document.getElementById("FormWBEntity:executeCustomAPIButtonForPatient");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Patient_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForPatient.click();
}
function refreshPanelForPatient()
{
	var demoRefreshButtonForPatient = document.getElementById("FormWBEntity:demoRefreshButtonPatient");
	demoRefreshButtonForPatient.click();
}
function initializePanelOnLoadForCreatePatient(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForPatient = initParamsObj;
	initializePatientPage();	
	doAfterPatientPanelRefreshed();
	initializePatientLookupFields(initParamsObj);
	doAfterPanelInitializedForPatientExt();
	fetchPatientDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="patientCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForPatient = 0;
function initializePanelOnLoadForSearchPatient()
{
	initializePatientPage();	
	initializePatientLookupFields();
	doAfterPanelInitializedForPatientExt();
	gLoadDashboardResultsForPatient =1;
	//retrievePatientList();
}
function initializePanelOnLoadForViewPatient(urlParams)
{
	initializePatientPage();	
	doAfterPatientPanelRefreshed();
	initializePatientLookupFields(urlParams);
	doAfterPanelInitializedForPatientExt();
	retrievePatient(urlParams);
	initDatePicker();
	$('[data-name="patientViewFormButtonsDiv"]').css("display", "inline");
}
function initializePatientPage()
{
	initializePageOnload();	
	//initializePatientTemplateVariables();
}
function initializePatientTemplateVariables()
{	
	
	
	var patientRowObj = $('[data-name="patientList"]').find('[data-name="patientRow"]');
	if(patientRowObj.length > 0 && gPatientSearchResultsTableRowTemplate.length == 0)
	{
		gPatientSearchResultsTableRowTemplate = patientRowObj[0].outerHTML;
		//patientRowObj.remove();
	}
	if(gPatientSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="patientSearchResults"]').find('[data-name="patientRow"]');
		if(searchResultsRowObj.length > 0 && gPatientSearchResultsTableRowTemplate.length == 0)
		{
			gPatientSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
			//searchResultsRowObj.remove();
		}
	}
	
	
}
function retrievePatient(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var patientId = paramsMap['patientId'];
	var patient = {};
	patient['patientId'] = patientId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				patient[key] = paramsMap[key];
			}
	}
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
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
        data: patientJsonData,
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
                	var patientDataObject = responseData['patientDataObject'];
    				setPatientInViewPage(patientDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForPatient()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchPatientDefaultData();
}
function fetchPatientDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'Patient', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var patient = responseData['patient'];
            	document.getElementById('idValueForPatient').value = '';
			    
            	setPatientData(patient);
            }
        }
    });	
}
function fetchPatientTestData() 
{
	var patient = getDataForPatient();
    var searchJsonData = {'objectType' : 'Patient', 'paramsInfo' : JSON.stringify(patient)};
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
            	var patient = responseData['patient'];
            	document.getElementById('idValueForPatient').value = '';
			    
            	setPatientData(patient);
            }
        }
    });	
}
function setPatientData(patientDataObject)
{
	var prefix = "";
		
	//Input
	if(patientDataObject.hasOwnProperty('patientName'))
	{
		var patientName = patientDataObject['patientName'];            		
		$('#'+prefix+'Patient_patientName').val(patientName);
	}
	
	//Combo
	if(patientDataObject.hasOwnProperty('patientGender'))
	{
		var patientGender = patientDataObject['patientGender'];            		
		$('#'+prefix+'Patient_patientGender').val(patientGender);
	}
	
	//LK Dropdown
	if(patientDataObject.hasOwnProperty('selectDoctor'))
	{
		var selectDoctor = patientDataObject['selectDoctor'];
		if(selectDoctor != "")
		{
			var selectDoctorId = selectDoctor['$$LOOKUP_ENTITY_NAME_INIT_LOWER$$Id'];
			var selectDoctorDisplayText = selectDoctor['lookupDisplayText'];
			var selectDoctorLinkElement = $('#'+prefix+'Patient_selectDoctor');
			selectDoctorLinkElement.val(selectDoctorDisplayText);
			selectDoctorLinkElement.data("selectDoctor-id", selectDoctorId);
		}
	}

	$('[data-name="patientActionButtonDiv"]').hide();
}
function initializePatientLookupFields(paramsMap) 
{
	  
	
	
	
	  
	
	
	
		
	$("#Patient_selectDoctor").data("selectDoctor-id", -1);

	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="patientSearchResultsDiv"]');
	  
	
	
	  
	
	
		
    searchDiv.find('[data-name="patientDB_selectDoctor"]').data("selectDoctor-id", -1);

        searchDiv.find('[data-name="doctorDBId"]').data("doctor-id", -1);

}

function initializePatientLookupSelectList(paramsMap)
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
function doAfterPatientPanelRefreshed()
{
    //doAfterPanelRefreshedForPatientExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForPatient(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'patientGender')
	{
	}

	doAfterSelectOptionChangedForPatientExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForPatient(fieldName)
{
	if(1>2)
	{
	}
		else if(fieldName == 'selectDoctor')
	{
	}

	doAfterLookupRowChangedForPatientExt(fieldName)
}
function getEntityIdForPatient()
{
	var idValue = document.getElementById('FormWBEntity:idValueForPatient').textContent;	
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
function openPrintPageForPatient()
{
	var entityId = getEntityIdForPatient();
	if(entityId>0)
	{
	    window.open("/reports/generated/Patient.jsp?patientId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}
function hideSelectDoctorForPatient()
{
		
}

function showSelectDoctorForPatient()
{
		
}


function getDataForPatient(paramsMap)
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
	var patient = {};
		
	//Input
	if($("#"+prefix+"Patient_patientName").length == 1)
	{
		var patientName = $('#'+prefix+'Patient_patientName').val();
		patient['patientName'] = patientName;
	}
	
	//Combo
	if($("#"+prefix+"Patient_patientGender").length == 1)
	{
		var patientGender = $('#'+prefix+'Patient_patientGender').val();
		patient['patientGender'] = patientGender;
	}
	
	//LK Dropdown
	if($("#"+prefix+"Patient_selectDoctor").length == 1)
	{
		var selectDoctorId = $("#"+prefix+"Patient_selectDoctor").data("selectDoctor-id");
		patient['selectDoctorId'] = selectDoctorId;
	}

	
	
	if($("#"+prefix+"idValueForDoctor").length == 1)
	{
		var doctorId = $("#"+prefix+"idValueForDoctor").val();
		patient['doctorId'] = doctorId; 
	}
	
	return patient;
}
function createPatient(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var patient = getDataForPatient(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				patient[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForPatient)
	{
		paramsInfo[key] = gInitParamsObjForPatient[key];
	}
	var validationObject = doPatientValidation(patient, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	patient['additionalParamsInfo'] = paramsInfo;
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
	var urlContextPath = "";//getContextPath();
	if(gPatientRequestUnderProcess == 1)
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
            	gPatientRequestUnderProcess = 0;
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gPatientRequestUnderProcess = 0;
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
            	var patientId = responseData['patientId'];
            	var popupElement = $('[data-name="PatientPopup"]');
            	
				
            	var patientDataObject = responseData['patientDataObject'];
				setPatientInViewPage(patientDataObject);
				retrieveDependentPatientList();
            }
        }
    });
}
function resetTableForPatient()
{
	var patientListElement = $("[data-name='patientList']");
	var previousDataRowList  = patientListElement.find('[data-name="patientRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForPatient(paramsMap)
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
	$('#'+prefix+'idValueForPatient').val('');
		
	//Input
	$('#'+prefix+'Patient_patientName').val('');
	
	//Combo
	$('#'+prefix+'Patient_patientGender').val('');
	
	//LK Dropdown
	$("#"+prefix+"Patient_selectDoctor").val("");
	$("#"+prefix+"Patient_selectDoctor").data("selectDoctor-id", -1);

	$('[data-name="patientCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="patientViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="patientActionButtonDiv"]').hide();
	enablePatientUpdateDisallowedFields(paramsMap);
    
}
function enablePatientUpdateDisallowedFields(paramsMap)
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
function updatePatient(paramsMap)
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
	var patient = getDataForPatient(paramsMap)
	if($("#"+prefix+"idValueForPatient").length == 1)
	{
		var patientId = $("#"+prefix+"idValueForPatient").val();
		patient['patientId'] = patientId;
	}
	var validationObject = doPatientValidation(patient, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
	var urlContextPath = "";//getContextPath();
	if(gPatientRequestUnderProcess == 1)
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
                    	gPatientRequestUnderProcess = 0;
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gPatientRequestUnderProcess = 0;
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
				
				retrieveDependentPatientList();
            }
        }
    });
}
function deletePatient(paramsMap)
{		
	var patientId = document.getElementById('idValueForPatient').value;
	deleteSelectedPatient(patientId, paramsMap);
}
function deleteSelectedPatient(patientId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var patient = {};
	patient['patientId'] = patientId;	
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
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
        data: patientJsonData,
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
function loadSearchResultsForPatient()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Patient'};
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
            	var patientSearchResultsElement = $("[data-name='patientSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var patientDataObject = searchResultObjectsList[i];
					            		var patientName = patientDataObject['patientName'];            		
            		var patientGender = patientDataObject['patientGender'];            		
            		var selectDoctor = patientDataObject['selectDoctor'];            		

            		var resultRowTemnplate = $(gPatientSearchResultsTableRowTemplate);
					            		var patientName = patientDataObject['patientName'];            		
            	    resultRowTemnplate.find("[data-name='patientName']").text(patientName);
            		var patientGender = patientDataObject['patientGender'];            		
            	    resultRowTemnplate.find("[data-name='patientGender']").text(patientGender);
            		var selectDoctor = patientDataObject['selectDoctor'];            		
            	    resultRowTemnplate.find("[data-name='selectDoctor']").text(selectDoctor);

            	    resultRowTemnplate.data('patient', patientDataObject);
            	    patientSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gPatientSearchResultsTableRowTemplate = ""; 
function openViewPageForPatientForEdit(patientLinkElement)
{
	var patientRowElement = $(patientLinkElement).parents('[data-name="patientRow"]');
    var patientDataObject  = patientRowElement.data('patient');
	var patientId = patientDataObject['patientId'];
	var patientPopupElement = $('[data-name="PatientPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setPatientInViewPage(patientDataObject);
	    $("#Patient-tab").trigger("click");
	}
	else if(patientPopupElement.length > 0)
	{
	    setPatientInViewPage(patientDataObject);
		$('[data-name="PatientPopup"]').find('[data-name="patientCreateFormButtonsDiv"]').hide();
		$('[data-name="PatientPopup"]').find('[data-name="patientViewFormButtonsDiv"]').show();
	    showPopup('PatientPopup', patientLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewPatient.html?patientId="+patientId;
		window.open(viewLink, "_blank"); 	
	}
}
function openPatientCreatePageForNew(linkElement)
{
	var patientPopupElement = $('[data-name="PatientPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForPatient();
	    $("#Patient-tab").trigger("click");
    }
	else if(patientPopupElement.length > 0)
	{
		resetFormForPatient();
	    showPopup('PatientPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreatePatient.html";
    }
}
function showPatientPopupForEdit(patientLinkElement)
{
	var patientRowElement = $(patientLinkElement).parents('[data-name="patientRow"]');
    var patientDataObject  = patientRowElement.data('patient');
    setPatientInViewPage(patientDataObject);
    showPopup('PatientPopup', patientLinkElement, 1);
    $("#Patient-tab").trigger("click");
}
function showPatientPopupForNew(buttonElement)
{
	resetFormForPatient();
    showPopup('PatientPopup', buttonElement, 1);
    $("#Patient-tab").trigger("click");
}
function deleteThisPatient(patientLinkElement, paramsMap)
{
	var patientRowElement = $(patientLinkElement).parents('[data-name="patientRow"]');
    var patientDataObject  = patientRowElement.data('patient');
	var patientId = patientDataObject['patientId'];
	deleteSelectedPatient(patientId, paramsMap);
}
function displayPatientList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + patientSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var patientSearchResults = searchResultsDiv.find('[data-name="patientSearchResults"]');
	//patientSearchResults.find('[data-name="patientRow"]').remove();
	var previousDataRowList  = patientSearchResults.find('[data-name="patientRow"]');
	patientSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = patientSearchResults.find('[data-name="patientRow"]');
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
        var patientDataObject = searchResultObjectsList[i];
        loadPatientViewData(patientDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    		var doctorDisplayVal = patientDataObject['doctorDisplayVal'];            		
	    resultRowTemplate.find("[data-name='doctorDisplayVal']").text(doctorDisplayVal);

		var vwTxnStatus = patientDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='patientVwTxnStatus']").text(vwTxnStatus);
		if(patientDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="patientActionButton"]').text(patientDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="patientActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="patientActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="patientRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="patientRejectButton"]').show();
		}
	    resultRowTemplate.data('patient', patientDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    patientSearchResults.append(resultRowTemplate);
	    processResultRowForPatientExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForPatient == 1)
	{
    	getDashboardResultsForPatient();
	}
}
var patientSearchResultsDivName = "patientSearchResultsDiv";
var gPatientSearchInputParamsList = [];
function retrievePatientList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + patientSearchResultsDivName + '"]');
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
    fetchPatientSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchPatientSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getPatientSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = patientSearchResultsDivName; 
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
        'objectType': "Patient"
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
            	this.showPreviousRecords = "showPreviousPatientRecords";
            	this.showCurrentPageRecords = "showCurrentPagePatientRecords";
            	this.showNextRecords = "showNextPatientRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchPatientResults";
                var patientList = responseData['patientList'];
                var currContextObj = this; 
                var successCallbackFunction = displayPatientList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'patientList', 'matchingSearchResultsCount', this.searchResultsDivName, 'patientSearchResults', 'patientRow', setPatientSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getPatientSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + patientSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="patientDB_patientName"]').length == 1)
		{
		    var patientName = searchDiv.find('[data-name="patientDB_patientName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'patientName', 'userInputValue':patientName});
		}
		
		//Combo
		if(searchDiv.find('[data-name="patientDB_patientGender"]').length == 1)
		{
		    var patientGender = searchDiv.find('[data-name="patientDB_patientGender"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'patientGender', 'userInputValue':patientGender});
		}
        
		if(searchDiv.find('[data-name="patientDB_selectDoctor"]').length == 1)
		{
		    var selectDoctorId = searchDiv.find('[data-name="patientDB_selectDoctor"]').data("selectDoctor-id");
		    if(selectDoctorId > -1)
	    	{
		    	parameterNameAndValuesList.push({ 'parameterName':'selectDoctorId', 'userInputValue':selectDoctorId});
	    	}
		}

	    		if(searchDiv.find('[data-name="doctorDBId"]').length == 1)
		{
		    var doctorId = searchDiv.find('[data-name="doctorDBId"]').data("doctor-id");
		    parameterNameAndValuesList.push({ 'parameterName':'doctorId', 'userInputValue':doctorId});
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
        gPatientSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gPatientSearchInputParamsList;
    }
    return searchInputParams;
}
function setPatientSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + patientSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gPatientSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gPatientSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPagePatientRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = patientSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchPatientSearchResultsList);
}
function showPreviousPatientRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = patientSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchPatientSearchResultsList);
}
function showNextPatientRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = patientSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchPatientSearchResultsList);
}
function showPrevOrNextSearchPatientResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousPatientRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextPatientRecords(e);
    }
}

var gDoctor_PatientSearchResultsTableRowTemplate =""; 
function initializeDoctorPopup_Doctor_PatientLookupFields() 
{	
    var searchDiv = $('[data-name="Doctor_PatientSearchDiv"]');
	
    
	if(gDoctor_PatientSearchResultsTableRowTemplate.length == 0)
	{
		var searchResultsRowObj = $('[data-name="Doctor_PatientSearchResults"]').find('[data-name="Doctor_PatientRow"]');
		gDoctor_PatientSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		searchResultsRowObj.remove(); 
	}
}
function displayDoctor_PatientList(searchResultObjectsList, parentElement)
{
    var doctorSearchResults = $('[data-name="Doctor_PatientSearchResults"]');
	doctorSearchResults.find('[data-name="Doctor_PatientRow"]').remove();
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(gDoctor_PatientSearchResultsTableRowTemplate);
        var doctorDataObject = searchResultObjectsList[i];
				var doctorName = doctorDataObject['doctorName'];            		
	    resultRowTemplate.find("[data-name='doctorName']").text(doctorName);
		var hospitalName = doctorDataObject['hospitalName'];            		
	    resultRowTemplate.find("[data-name='hospitalName']").text(hospitalName);

		
	    
	    resultRowTemplate.data('doctor', doctorDataObject);
	    doctorSearchResults.append(resultRowTemplate);            	    
    }
}
var Doctor_PatientSearchResultsDivName = "Doctor_PatientSearchResultsDiv";
var gDoctor_PatientSearchInputParamsList = [];
function getDoctor_PatientSearchResults()
{
    var searchDiv = $('[data-name="Doctor_PatientSearchDiv"]');
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = searchDiv.find('[data-name="rowsPerPage"]').val();
    var searchResultsDiv = $('[data-name="' + Doctor_PatientSearchResultsDivName + '"]');
    searchResultsDiv.data("is-searched", 0);
    fetchDoctor_PatientSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1);
}
function fetchDoctor_PatientSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex)
{
    var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getDoctor_PatientSearchInputParams();
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
        'objectType': "Doctor",
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
            	this.showCurrentPageRecords = "showCurrentPageDoctor_PatientRecords";
            	this.showPreviousRecords = "showPreviousDoctor_PatientRecords";
            	this.showCurrentPageRecords = "showCurrentPageDoctor_PatientRecords";
            	this.showNextRecords = "showNextDoctor_PatientRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchDoctor_PatientResults";
                var doctorList = responseData['doctorList'];  
        		handleSearchResultsResponse(this, responseData, 'doctorList', 'matchingSearchResultsCount', Doctor_PatientSearchResultsDivName, 'Doctor_PatientSearchResults', 'Doctor_PatientRow', setDoctor_PatientSearchInputParams, displayDoctor_PatientList);
            }
        }
    });
}
function getDoctor_PatientSearchInputParams()
{
    var searchResultsDiv = $('[data-name="' + Doctor_PatientSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = $('[data-name="Doctor_PatientSearchDiv"]');
        var parameterNameAndValuesList = [];
				
		//Input
	    var doctorName = searchDiv.find('[data-name="doctorDB_doctorName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'doctorName', 'userInputValue':doctorName});
		
		//Input
	    var hospitalName = searchDiv.find('[data-name="doctorDB_hospitalName"]').val();
	    parameterNameAndValuesList.push({ 'parameterName':'hospitalName', 'userInputValue':hospitalName});

	    
        gDoctor_PatientSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gDoctor_PatientSearchInputParamsList;
    }
    return searchInputParams;
}
function setDoctor_PatientSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + Doctor_PatientSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gDoctor_PatientSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gDoctor_PatientSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageDoctor_PatientRecords(e)
{
    getCurrentPageSearchResults(e, Doctor_PatientSearchResultsDivName, fetchDoctor_PatientSearchResultsList);
}
function showPreviousDoctor_PatientRecords()
{
    getPreviousPageSearchResults(Doctor_PatientSearchResultsDivName, fetchDoctor_PatientSearchResultsList);
}
function showNextDoctor_PatientRecords()
{
    getNextPageSearchResults(Doctor_PatientSearchResultsDivName, fetchDoctor_PatientSearchResultsList);
}
function showPrevOrNextSearchDoctor_PatientResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousDoctor_PatientRecords();
    }
    else if (event.keyCode == 39)
    {
        showNextDoctor_PatientRecords();
    }
}
function setDoctor_Patient(doctorRowElement) 
{
    var doctorDataObject  = $(doctorRowElement).data('doctor');
	var doctorId = doctorDataObject['doctorId'];
	var parentElement = $(doctorRowElement).parents('[data-name="DoctorPopup_Doctor_Patient"]');
	var linkElement = parentElement.data("selected-element");
	linkElement.data("doctor-id", doctorId);
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
			inputElement.val(doctorDataObject[lookupDisplayValueName]);		
		}
		var entityName = "Doctor_Patient";
		var functionName = "lookupRowSelectedFor"+ entityName.split('_')[0]+"('"+entityName.split('_')[1]+"',"+doctorId+")";
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
function lookupRowSelectedForPatient(attributeName, attributeId)
{
	var patient = getDataForPatient();
	patient['attributeName'] = attributeName;
	patient['attributeId'] = attributeId;
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var patient = responseData['patient'];
            	setPatientData(patient);
            }
        }
    });	
}
function selectOptionChangedForPatient(attributeName)
{
	var patient = getDataForPatient();
	patient['attributeName'] = attributeName;
    var patientJsonData = {'paramsInfo': JSON.stringify(patient), 'objectType' : 'Patient'};
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var patient = responseData['patient'];
            	setPatientData(patient);
            	doAfterPatientPanelRefreshed();
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
function retrieveDependentPatientList(paramsMap)
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
	var doctorId = $('#'+prefix+'idValueForDoctor').val();
	if(paramsMap.hasOwnProperty('doctorId'))
	{
		doctorId = paramsMap['doctorId'];
	}
	var paramsInfo = {'doctorId':doctorId};
    var searchJsonData = {'objectType' : 'Patient', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var patientList = responseData['patientList'];
            	var patientListElement = $("[data-name='patientList']");
            	var previousDataRowList  = patientListElement.find('[data-name="patientRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = patientListElement.find('[data-name="patientRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//patientListElement.empty();
            	for(var i=0; i<patientList.length; i++)
        		{
            		var patientDataObject = patientList[i];
            		//var resultRowTemplate = $(gPatientSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var patientName = patientDataObject['patientName'];            		
				    resultRowTemplate.find("[data-name='patientName']").text(patientName);
					var patientGender = patientDataObject['patientGender'];            		
				    resultRowTemplate.find("[data-name='patientGender']").text(patientGender);
					var selectDoctor = patientDataObject['selectDoctor'];            		
				    resultRowTemplate.find("[data-name='selectDoctor']").text(selectDoctor);

					
										var selectDoctor = patientDataObject['selectDoctorDisplayVal'];            		
				    resultRowTemplate.find("[data-name='selectDoctor']").text(selectDoctor);

            	    resultRowTemplate.data('patient', patientDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    patientListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForPatient(customEventName)
{
	var patientId = document.getElementById('idValueForPatient').value;
	var patient = {'patientId':patientId, 'customEventName':customEventName};
    var patientJsonData = {'paramsInfo':JSON.stringify(patient), 'objectType' : 'Patient'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForPatientExt(customEventName);
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var patient = responseData['patient'];
        		setPatientInViewPage(patient);
            }
    		doAfterExecuteCustomAPIForPatientExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnPatient(e, paramsMap)
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
	var patientId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var patientDataObject  = rowObj.data('patient');
    	patientId = patientDataObject['patientId'];
	}
    else
	{
    	patientId = document.getElementById('idValueForPatient').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var patientSearchParams = {'patientId':patientId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	patientSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var patientJsonData = {'paramsInfo':JSON.stringify(patientSearchParams),  'objectType' : 'Patient'};
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
        data: patientJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var patient = responseData['patient'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="patientRowActionButtonDiv"]').hide();
            		if(patient.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = patient['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(patient['vwTxnStatus']);
            			selectedRowObj.find('[data-name="patientActionButton"]').text(patient["nextAction"]);
            			selectedRowObj.find('[data-name="patientActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setPatientInViewPage(patient);
    			}
            }
        }
    });	
}
function downloadPatientData()
{		
	var patient = {};
    var patientJsonData = {'objectType' : 'Patient'};
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
        data: patientJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=Patient");
            }
        }
    });
}
function uploadPatientData(fileInfo)
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
	var patient = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var patientJsonData = {'paramsInfo':JSON.stringify(patient),  'objectType' : 'Patient'};
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
        data: patientJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Patient");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForPatient()
{
    var patientJsonData = {'objectType' : 'Patient'};
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
        data: patientJsonData,
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

  


  



function showMatchingPatient_selectDoctorOnTabout(event, e)
{
    showMatchingPatient_selectDoctor(event, e, true);
}
function showMatchingPatient_selectDoctor(event, e, isKeyDownEvent)
{
	var itemIdDataName = "selectDoctor-id";
    var itemInputDataName = $(e).data("name");//"Patient_selectDoctor";
    var responseObjKeyName = "$$LOOKUP_ENTITY_NAME_INIT_LOWER$$List";
    var resultItemIdKeyName = "$$LOOKUP_ENTITY_NAME_INIT_LOWER$$Id";
    var resultItemNameKeyName = "lookupDisplayText";
	var urlContextPath = "";//getContextPath();
    var url =  urlContextPath + '/retrieveEntityList';
    var requestUrl = url;
    var errorMessage = "abcd";
    var currentSearchPrefix = $.trim($(e).val());
    var paramsList = [];
    paramsList.push({ 'parameterName':'lookupDisplayPrefix', 'userInputValue':currentSearchPrefix});
	var urlRequestParamsInfo = {
		    'searchInputParamsList': JSON.stringify(paramsList),
		    'objectType': "$$LOOKUP_ENTITY_NAME$$"
		};
    var paramsMap = {};
    paramsMap['itemIdDataName'] = itemIdDataName;
    paramsMap['itemInputDataName'] = itemInputDataName;
    paramsMap['responseObjKeyName'] = responseObjKeyName;
    paramsMap['resultItemIdKeyName'] = resultItemIdKeyName;
    paramsMap['resultItemNameKeyName'] = resultItemNameKeyName;
    paramsMap['currentSearchPrefix'] = currentSearchPrefix;
    paramsMap['requestUrl'] = requestUrl;
    paramsMap['errorMessage'] = errorMessage;
    showMatchingDropDownItems(event, e, isKeyDownEvent, paramsMap, urlRequestParamsInfo);    
}


function doPatientLengthValidation(patientDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(patientDataObject['patientName'], 50))
	{
		alertMessage += "\n PatientName length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(patientDataObject['patientGender'], 10))
	{
		alertMessage += "\n PatientGender length is more than allowed(10)";
	    validationPassed = false;
	}
  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPatientManadatoryValidation(patientDataObject, paramsMap)
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
		
	var patientName = patientDataObject['patientName'];
	if(!patientName || patientName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"Patient_patientName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter PatientName";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doPatientValidation(patientDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doPatientLengthValidation(patientDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doPatientManadatoryValidation(patientDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
