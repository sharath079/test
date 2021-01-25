/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'FullName' : 'FormWBEntity:ContactUs_fullName' 
 *	 * 'EmailId' : 'FormWBEntity:ContactUs_emailId' 
 *	 * 'ContactNo' : 'FormWBEntity:ContactUs_contactNo' 
 *	
 */
var gInitParamsObjForContactUs = {};
var gContactUsRequestUnderProcess = 0;
function selectThisContactUsForEdit(contactUsRowElement)
{
    var contactUsDataObject  = $(contactUsRowElement).data('contactUs');
    var contactUsList = $('[data-name="contactUsSearchResults"]').find('[data-name="contactUsRow"]');
	contactUsList.data("is-selected", 0);	
	$(contactUsRowElement).data("is-selected", 1);
	setContactUsInViewPage(contactUsDataObject);
}

function setContactUsInViewPage(contactUsDataObject, paramsMap)
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
	var contactUsId = contactUsDataObject['contactUsId'];
	$('#'+prefix+'idValueForContactUs').val(contactUsId);
		
	//Input
	if(contactUsDataObject.hasOwnProperty('fullName'))
	{
		var fullName = contactUsDataObject['fullName'];            		
		$('#'+prefix+'ContactUs_fullName').val(fullName);
	}
	
	//Input
	if(contactUsDataObject.hasOwnProperty('emailId'))
	{
		var emailId = contactUsDataObject['emailId'];            		
		$('#'+prefix+'ContactUs_emailId').val(emailId);
	}
	
	//Input
	if(contactUsDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = contactUsDataObject['contactNo'];            		
		$('#'+prefix+'ContactUs_contactNo').val(contactNo);
	}

	if(contactUsDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = contactUsDataObject['vwTxnStatus'];
		$('[data-name="contactUsActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnContactUs(this)" >' +contactUsDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="contactUsActionButtonDiv"]').append(buttonElement);
		$('[data-name="contactUsActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="contactUsActionButtonDiv"]').hide();
	}
	$('[data-name="contactUsCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="contactUsViewFormButtonsDiv"]').css("display", "inline");
	//loadContactUsViewData(contactUsDataObject);
	disbaleContactUsUpdateDisallowedFields(paramsMap);
	doAfterContactUsPanelRefreshed();
    
    
}
function disbaleContactUsUpdateDisallowedFields(paramsMap)
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
function loadContactUsViewData(contactUsDataObject, paramsMap)
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
	var contactUsId = contactUsDataObject['contactUsId'];
	$('#'+prefix+'idValueForContactUs').val(contactUsId);
		
	if(contactUsDataObject.hasOwnProperty('fullName'))
	{
		var fullName = contactUsDataObject['fullName'];            		
		parentElement.find('[data-name="'+prefix+'ContactUs_fullName"]').text(fullName);
	}
	
	if(contactUsDataObject.hasOwnProperty('emailId'))
	{
		var emailId = contactUsDataObject['emailId'];            		
		parentElement.find('[data-name="'+prefix+'ContactUs_emailId"]').text(emailId);
	}
	
	if(contactUsDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = contactUsDataObject['contactNo'];            		
		parentElement.find('[data-name="'+prefix+'ContactUs_contactNo"]').text(contactNo);
	}

}
function ajaxDemoForContactUs()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForContactUs').textContent;	
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
			refreshPanelForContactUs();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForContactUs(msg)
{
	var executeCustomAPIButtonForContactUs = document.getElementById("FormWBEntity:executeCustomAPIButtonForContactUs");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:ContactUs_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForContactUs.click();
}
function refreshPanelForContactUs()
{
	var demoRefreshButtonForContactUs = document.getElementById("FormWBEntity:demoRefreshButtonContactUs");
	demoRefreshButtonForContactUs.click();
}
function initializePanelOnLoadForCreateContactUs(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForContactUs = initParamsObj;
	initializeContactUsPage();	
	doAfterContactUsPanelRefreshed();
	initializeContactUsLookupFields(initParamsObj);
	doAfterPanelInitializedForContactUsExt();
	fetchContactUsDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="contactUsCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForContactUs = 0;
function initializePanelOnLoadForSearchContactUs()
{
	initializeContactUsPage();	
	initializeContactUsLookupFields();
	doAfterPanelInitializedForContactUsExt();
	gLoadDashboardResultsForContactUs =1;
	//retrieveContactUsList();
}
function initializePanelOnLoadForViewContactUs(urlParams)
{
	initializeContactUsPage();	
	doAfterContactUsPanelRefreshed();
	initializeContactUsLookupFields(urlParams);
	doAfterPanelInitializedForContactUsExt();
	retrieveContactUs(urlParams);
	initDatePicker();
	$('[data-name="contactUsViewFormButtonsDiv"]').css("display", "inline");
}
function initializeContactUsPage()
{
	initializePageOnload();	
	//initializeContactUsTemplateVariables();
}
function initializeContactUsTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="contactUsSearchResults"]').find('[data-name="contactUsRow"]');
	if(searchResultsRowObj.length > 0 && gContactUsSearchResultsTableRowTemplate.length == 0)
	{
		gContactUsSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveContactUs(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var contactUsId = paramsMap['contactUsId'];
	var contactUs = {};
	contactUs['contactUsId'] = contactUsId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				contactUs[key] = paramsMap[key];
			}
	}
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
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
                	var contactUsDataObject = responseData['contactUsDataObject'];
    				setContactUsInViewPage(contactUsDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForContactUs()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchContactUsDefaultData();
}
function fetchContactUsDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'ContactUs', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var contactUs = responseData['contactUs'];
            	document.getElementById('idValueForContactUs').value = '';
			    
            	setContactUsData(contactUs);
            }
        }
    });	
}
function fetchContactUsTestData() 
{
	var contactUs = getDataForContactUs();
    var searchJsonData = {'objectType' : 'ContactUs', 'paramsInfo' : JSON.stringify(contactUs)};
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
            	var contactUs = responseData['contactUs'];
            	document.getElementById('idValueForContactUs').value = '';
			    
            	setContactUsData(contactUs);
            }
        }
    });	
}
function setContactUsData(contactUsDataObject)
{
	var prefix = "";
		
	//Input
	if(contactUsDataObject.hasOwnProperty('fullName'))
	{
		var fullName = contactUsDataObject['fullName'];            		
		$('#'+prefix+'ContactUs_fullName').val(fullName);
	}
	
	//Input
	if(contactUsDataObject.hasOwnProperty('emailId'))
	{
		var emailId = contactUsDataObject['emailId'];            		
		$('#'+prefix+'ContactUs_emailId').val(emailId);
	}
	
	//Input
	if(contactUsDataObject.hasOwnProperty('contactNo'))
	{
		var contactNo = contactUsDataObject['contactNo'];            		
		$('#'+prefix+'ContactUs_contactNo').val(contactNo);
	}

	$('[data-name="contactUsActionButtonDiv"]').hide();
}
function initializeContactUsLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="contactUsSearchResultsDiv"]');
	
    
}

function doAfterContactUsPanelRefreshed()
{
    //doAfterPanelRefreshedForContactUsExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForContactUs(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForContactUsExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForContactUs(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForContactUsExt(fieldName)
}
function getEntityIdForContactUs()
{
	var idValue = document.getElementById('FormWBEntity:idValueForContactUs').textContent;	
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
function openPrintPageForContactUs()
{
	var entityId = getEntityIdForContactUs();
	if(entityId>0)
	{
	    window.open("/reports/generated/ContactUs.jsp?contactUsId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForContactUs(paramsMap)
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
	var contactUs = {};
		
	//Input
	if($("#"+prefix+"ContactUs_fullName").length == 1)
	{
		var fullName = $('#'+prefix+'ContactUs_fullName').val();
		contactUs['fullName'] = fullName;
	}
	
	//Input
	if($("#"+prefix+"ContactUs_emailId").length == 1)
	{
		var emailId = $('#'+prefix+'ContactUs_emailId').val();
		contactUs['emailId'] = emailId;
	}
	
	//Input
	if($("#"+prefix+"ContactUs_contactNo").length == 1)
	{
		var contactNo = $('#'+prefix+'ContactUs_contactNo').val();
		contactUs['contactNo'] = contactNo;
	}

	
	return contactUs;
}
function createContactUs(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var contactUs = getDataForContactUs(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				contactUs[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForContactUs)
	{
		paramsInfo[key] = gInitParamsObjForContactUs[key];
	}
	var validationObject = doContactUsValidation(contactUs, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	contactUs['additionalParamsInfo'] = paramsInfo;
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
	var urlContextPath = "";//getContextPath();
	if(gContactUsRequestUnderProcess == 1)
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
            	gContactUsRequestUnderProcess = 0;
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gContactUsRequestUnderProcess = 0;
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
            	var contactUsId = responseData['contactUsId'];
            	var popupElement = $('[data-name="ContactUsPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var contactUsDataObject = responseData['contactUsDataObject'];
    				setContactUsInViewPage(contactUsDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewContactUs.html?contactUsId="+contactUsId; 
        		}
				
            }
        }
    });
}
function resetTableForContactUs()
{
	var contactUsListElement = $("[data-name='contactUsList']");
	var previousDataRowList  = contactUsListElement.find('[data-name="contactUsRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForContactUs(paramsMap)
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
	$('#'+prefix+'idValueForContactUs').val('');
		
	//Input
	$('#'+prefix+'ContactUs_fullName').val('');
	
	//Input
	$('#'+prefix+'ContactUs_emailId').val('');
	
	//Input
	$('#'+prefix+'ContactUs_contactNo').val('');

	$('[data-name="contactUsCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="contactUsViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="contactUsActionButtonDiv"]').hide();
	enableContactUsUpdateDisallowedFields(paramsMap);
    
}
function enableContactUsUpdateDisallowedFields(paramsMap)
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
function updateContactUs(paramsMap)
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
	var contactUs = getDataForContactUs(paramsMap)
	if($("#"+prefix+"idValueForContactUs").length == 1)
	{
		var contactUsId = $("#"+prefix+"idValueForContactUs").val();
		contactUs['contactUsId'] = contactUsId;
	}
	var validationObject = doContactUsValidation(contactUs, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
	var urlContextPath = "";//getContextPath();
	if(gContactUsRequestUnderProcess == 1)
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
                    	gContactUsRequestUnderProcess = 0;
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gContactUsRequestUnderProcess = 0;
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
function deleteContactUs(paramsMap)
{		
	var contactUsId = document.getElementById('idValueForContactUs').value;
	deleteSelectedContactUs(contactUsId, paramsMap);
}
function deleteSelectedContactUs(contactUsId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var contactUs = {};
	contactUs['contactUsId'] = contactUsId;	
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
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
function loadSearchResultsForContactUs()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'ContactUs'};
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
            	var contactUsSearchResultsElement = $("[data-name='contactUsSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var contactUsDataObject = searchResultObjectsList[i];
					            		var fullName = contactUsDataObject['fullName'];            		
            		var emailId = contactUsDataObject['emailId'];            		
            		var contactNo = contactUsDataObject['contactNo'];            		

            		var resultRowTemnplate = $(gContactUsSearchResultsTableRowTemplate);
					            		var fullName = contactUsDataObject['fullName'];            		
            	    resultRowTemnplate.find("[data-name='fullName']").text(fullName);
            		var emailId = contactUsDataObject['emailId'];            		
            	    resultRowTemnplate.find("[data-name='emailId']").text(emailId);
            		var contactNo = contactUsDataObject['contactNo'];            		
            	    resultRowTemnplate.find("[data-name='contactNo']").text(contactNo);

            	    resultRowTemnplate.data('contactUs', contactUsDataObject);
            	    contactUsSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gContactUsSearchResultsTableRowTemplate = ""; 
function openViewPageForContactUsForEdit(contactUsLinkElement)
{
	var contactUsRowElement = $(contactUsLinkElement).parents('[data-name="contactUsRow"]');
    var contactUsDataObject  = contactUsRowElement.data('contactUs');
	var contactUsId = contactUsDataObject['contactUsId'];
	var contactUsPopupElement = $('[data-name="ContactUsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setContactUsInViewPage(contactUsDataObject);
	    $("#ContactUs-tab").trigger("click");
	}
	else if(contactUsPopupElement.length > 0)
	{
	    setContactUsInViewPage(contactUsDataObject);
		$('[data-name="ContactUsPopup"]').find('[data-name="contactUsCreateFormButtonsDiv"]').hide();
		$('[data-name="ContactUsPopup"]').find('[data-name="contactUsViewFormButtonsDiv"]').show();
	    showPopup('ContactUsPopup', contactUsLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewContactUs.html?contactUsId="+contactUsId;
		window.open(viewLink, "_blank"); 	
	}
}
function openContactUsCreatePageForNew(linkElement)
{
	var contactUsPopupElement = $('[data-name="ContactUsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForContactUs();
	    $("#ContactUs-tab").trigger("click");
    }
	else if(contactUsPopupElement.length > 0)
	{
		resetFormForContactUs();
	    showPopup('ContactUsPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateContactUs.html";
    }
}
function showContactUsPopupForEdit(contactUsLinkElement)
{
	var contactUsRowElement = $(contactUsLinkElement).parents('[data-name="contactUsRow"]');
    var contactUsDataObject  = contactUsRowElement.data('contactUs');
    setContactUsInViewPage(contactUsDataObject);
    showPopup('ContactUsPopup', contactUsLinkElement, 1);
    $("#ContactUs-tab").trigger("click");
}
function showContactUsPopupForNew(buttonElement)
{
	resetFormForContactUs();
    showPopup('ContactUsPopup', buttonElement, 1);
    $("#ContactUs-tab").trigger("click");
}
function deleteThisContactUs(contactUsLinkElement, paramsMap)
{
	var contactUsRowElement = $(contactUsLinkElement).parents('[data-name="contactUsRow"]');
    var contactUsDataObject  = contactUsRowElement.data('contactUs');
	var contactUsId = contactUsDataObject['contactUsId'];
	deleteSelectedContactUs(contactUsId, paramsMap);
}
function displayContactUsList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + contactUsSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var contactUsSearchResults = searchResultsDiv.find('[data-name="contactUsSearchResults"]');
	//contactUsSearchResults.find('[data-name="contactUsRow"]').remove();
	var previousDataRowList  = contactUsSearchResults.find('[data-name="contactUsRow"]');
	contactUsSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = contactUsSearchResults.find('[data-name="contactUsRow"]');
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
        var contactUsDataObject = searchResultObjectsList[i];
        loadContactUsViewData(contactUsDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = contactUsDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='contactUsVwTxnStatus']").text(vwTxnStatus);
		if(contactUsDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="contactUsActionButton"]').text(contactUsDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="contactUsActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="contactUsActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="contactUsRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="contactUsRejectButton"]').show();
		}
	    resultRowTemplate.data('contactUs', contactUsDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    contactUsSearchResults.append(resultRowTemplate);
	    processResultRowForContactUsExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForContactUs == 1)
	{
    	getDashboardResultsForContactUs();
	}
}
var contactUsSearchResultsDivName = "contactUsSearchResultsDiv";
var gContactUsSearchInputParamsList = [];
function retrieveContactUsList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + contactUsSearchResultsDivName + '"]');
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
    fetchContactUsSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchContactUsSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getContactUsSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = contactUsSearchResultsDivName; 
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
        'objectType': "ContactUs"
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
            	this.showPreviousRecords = "showPreviousContactUsRecords";
            	this.showCurrentPageRecords = "showCurrentPageContactUsRecords";
            	this.showNextRecords = "showNextContactUsRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchContactUsResults";
                var contactUsList = responseData['contactUsList'];
                var currContextObj = this; 
                var successCallbackFunction = displayContactUsList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'contactUsList', 'matchingSearchResultsCount', this.searchResultsDivName, 'contactUsSearchResults', 'contactUsRow', setContactUsSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getContactUsSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + contactUsSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="contactUsDB_fullName"]').length == 1)
		{
		    var fullName = searchDiv.find('[data-name="contactUsDB_fullName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'fullName', 'userInputValue':fullName});
		}
		
		//Input
		if(searchDiv.find('[data-name="contactUsDB_emailId"]').length == 1)
		{
		    var emailId = searchDiv.find('[data-name="contactUsDB_emailId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'emailId', 'userInputValue':emailId});
		}
		
		//Input
		if(searchDiv.find('[data-name="contactUsDB_contactNo"]').length == 1)
		{
		    var contactNo = searchDiv.find('[data-name="contactUsDB_contactNo"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'contactNo', 'userInputValue':contactNo});
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
        gContactUsSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gContactUsSearchInputParamsList;
    }
    return searchInputParams;
}
function setContactUsSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + contactUsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gContactUsSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gContactUsSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageContactUsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = contactUsSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchContactUsSearchResultsList);
}
function showPreviousContactUsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = contactUsSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchContactUsSearchResultsList);
}
function showNextContactUsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = contactUsSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchContactUsSearchResultsList);
}
function showPrevOrNextSearchContactUsResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousContactUsRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextContactUsRecords(e);
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
function lookupRowSelectedForContactUs(attributeName, attributeId)
{
	var contactUs = getDataForContactUs();
	contactUs['attributeName'] = attributeName;
	contactUs['attributeId'] = attributeId;
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var contactUs = responseData['contactUs'];
            	setContactUsData(contactUs);
            }
        }
    });	
}
function selectOptionChangedForContactUs(attributeName)
{
	var contactUs = getDataForContactUs();
	contactUs['attributeName'] = attributeName;
    var contactUsJsonData = {'paramsInfo': JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var contactUs = responseData['contactUs'];
            	setContactUsData(contactUs);
            	doAfterContactUsPanelRefreshed();
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
function retrieveDependentContactUsList(paramsMap)
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
    var searchJsonData = {'objectType' : 'ContactUs', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var contactUsList = responseData['contactUsList'];
            	var contactUsListElement = $("[data-name='contactUsList']");
            	var previousDataRowList  = contactUsListElement.find('[data-name="contactUsRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = contactUsListElement.find('[data-name="contactUsRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//contactUsListElement.empty();
            	for(var i=0; i<contactUsList.length; i++)
        		{
            		var contactUsDataObject = contactUsList[i];
            		//var resultRowTemplate = $(gContactUsSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var fullName = contactUsDataObject['fullName'];            		
				    resultRowTemplate.find("[data-name='fullName']").text(fullName);
					var emailId = contactUsDataObject['emailId'];            		
				    resultRowTemplate.find("[data-name='emailId']").text(emailId);
					var contactNo = contactUsDataObject['contactNo'];            		
				    resultRowTemplate.find("[data-name='contactNo']").text(contactNo);

					
					
            	    resultRowTemplate.data('contactUs', contactUsDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    contactUsListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForContactUs(customEventName)
{
	var contactUsId = document.getElementById('idValueForContactUs').value;
	var contactUs = {'contactUsId':contactUsId, 'customEventName':customEventName};
    var contactUsJsonData = {'paramsInfo':JSON.stringify(contactUs), 'objectType' : 'ContactUs'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForContactUsExt(customEventName);
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var contactUs = responseData['contactUs'];
        		setContactUsInViewPage(contactUs);
            }
    		doAfterExecuteCustomAPIForContactUsExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnContactUs(e, paramsMap)
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
	var contactUsId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var contactUsDataObject  = rowObj.data('contactUs');
    	contactUsId = contactUsDataObject['contactUsId'];
	}
    else
	{
    	contactUsId = document.getElementById('idValueForContactUs').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var contactUsSearchParams = {'contactUsId':contactUsId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	contactUsSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var contactUsJsonData = {'paramsInfo':JSON.stringify(contactUsSearchParams),  'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var contactUs = responseData['contactUs'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="contactUsRowActionButtonDiv"]').hide();
            		if(contactUs.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = contactUs['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(contactUs['vwTxnStatus']);
            			selectedRowObj.find('[data-name="contactUsActionButton"]').text(contactUs["nextAction"]);
            			selectedRowObj.find('[data-name="contactUsActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setContactUsInViewPage(contactUs);
    			}
            }
        }
    });	
}
function downloadContactUsData()
{		
	var contactUs = {};
    var contactUsJsonData = {'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=ContactUs");
            }
        }
    });
}
function uploadContactUsData(fileInfo)
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
	var contactUs = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var contactUsJsonData = {'paramsInfo':JSON.stringify(contactUs),  'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=ContactUs");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForContactUs()
{
    var contactUsJsonData = {'objectType' : 'ContactUs'};
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
        data: contactUsJsonData,
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



function doContactUsLengthValidation(contactUsDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(contactUsDataObject['fullName'], 50))
	{
		alertMessage += "\n FullName length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(contactUsDataObject['emailId'], 70))
	{
		alertMessage += "\n Email Id length is more than allowed(70)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(contactUsDataObject['contactNo'], 20))
	{
		alertMessage += "\n Contact No length is more than allowed(20)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doContactUsManadatoryValidation(contactUsDataObject, paramsMap)
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
		
	var fullName = contactUsDataObject['fullName'];
	if(!fullName || fullName.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"ContactUs_fullName").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter FullName";
		    validationPassed = false;
		}
	}
	
	var emailId = contactUsDataObject['emailId'];
	if(!emailId || emailId.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"ContactUs_emailId").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Email Id";
		    validationPassed = false;
		}
	}
	
	var contactNo = contactUsDataObject['contactNo'];
	if(!contactNo || contactNo.trim().length == 0)
	{
		if((considerExistingFieldsOnly == true && $("#"+prefix+"ContactUs_contactNo").length == 1)
				|| considerExistingFieldsOnly == false)
		{
			alertMessage += "\n Enter Contact No";
		    validationPassed = false;
		}
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doContactUsValidation(contactUsDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doContactUsLengthValidation(contactUsDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doContactUsManadatoryValidation(contactUsDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
