$(document).ready(function() {
	initializeReportInfo();
	initializePageLevelData();
});

var privilegeGroupItemsDataTableRowTemplate = "";
privilegeGroupItemsTbodyTag = "";

function initializePageLevelData()
{
	
	
	
	var privilegeGroupItemsDiv = $('[data-name="PrivilegeGroupItems"]');
	privilegeGroupItemsTbodyTag = privilegeGroupItemsDiv.find('tbody').first();
	privilegeGroupItemsDataTableRowTemplate = privilegeGroupItemsTbodyTag.find('tr').first()[0].outerHTML;
	
}
function getContextPath()
{
    return "";
}
function initializeReportInfo()
{
	initializePageOnload();
	var paramsMap = getQueryParams();
	
	initDatePicker();
	//initMonthDatePicker();
	//initYearDatePicker();
	
    getPageDataInfo();
}
function getPageDataInfo()
{
    var urlContextPath = getContextPath();
	var dataObj = getQueryParams();	
	dataObj.noOfRecordsAlreadyFetched = 0;
	dataObj.noOfRecordsToFetch = 1000000;
	dataObj.objectType = "PrivilegeGroup";
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData) {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',        
        url : urlContextPath + '/getReportInfo' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: dataObj,
        success: function (responseData) {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                var fieldsDataWithoutOverrideWhereClause = responseData['fieldsDataWithoutOverrideWhereClause'];                
                   
                	var privilegeGroup = fieldsDataWithoutOverrideWhereClause['privilegeGroup'];                
		                
		                
		                var privilegeGroupName = privilegeGroup['privilegeGroupName'];
		                $('[data-name="PrivilegeGroupName"]').text(privilegeGroupName);
		                
		                
		                var privilegeCode = privilegeGroup['privilegeCode'];
		                $('[data-name="PrivilegeCode"]').text(privilegeCode);
		                
		                
		                var applicableUserType = privilegeGroup['applicableUserType'];
		                $('[data-name="ApplicableUserType"]').text(applicableUserType);
		                
		                
		                var selfServiceUser = privilegeGroup['selfServiceUser'];
		                $('[data-name="SelfServiceUser"]').text(selfServiceUser);
		                
		                
		                var privilegeGroupDescription = privilegeGroup['privilegeGroupDescription'];
		                $('[data-name="PrivilegeGroupDescription"]').text(privilegeGroupDescription);
		                                
                
                var fieldsDataWithOverrideWhereClause = responseData['fieldsDataWithOverrideWhereClause'];                
	                                
			    var layoutCustomDataFieldsObject = responseData['layoutCustomDataFieldsObject'];               
                
                var tablesData = responseData['tablesData'];
                                  
                var privilegeGroupItemsDataRowsList = tablesData['privilegeGroupItemsDataRowsList'];                
                displayPrivilegeGroupItems(privilegeGroupItemsDataRowsList);
                
                var graphsData = responseData['graphsData'];
                
            }
        }
    });
}

 


function displayPrivilegeGroupItems(privilegeGroupItems, parentElement)
{
	var paramsMap = "";
    var privilegeGroupItemsDiv = $('[data-name="PrivilegeGroupItems"]');
	if(!(typeof parentElement === 'string' || parentElement instanceof String || typeof parentElement === 'undefined'))
	{
	        privilegeGroupItemsDiv = parentElement.find('[data-name="PrivilegeGroupItems"]');
	        privilegeGroupItemsTbodyTag = privilegeGroupItemsDiv.find('tbody').first();
			privilegeGroupItemsDataTableRowTemplate = privilegeGroupItemsTbodyTag.find('tr').first()[0].outerHTML;	        	       	  
    }
    privilegeGroupItemsTbodyTag.empty();
    for (var i = 0; i < privilegeGroupItems.length; i++)
    {
        var privilegeGroupItemsObject = privilegeGroupItems[i];
          
        var privilegeActionType = privilegeGroupItemsObject['privilegeActionType'];    	
          
        var privilegeObjectType = privilegeGroupItemsObject['privilegeObjectType'];    	
          
        var privilegeObjectName = privilegeGroupItemsObject['privilegeObjectName'];    	
                   
             
		         
        var lineItemsListRowObj = $(privilegeGroupItemsDataTableRowTemplate); 
          
        	lineItemsListRowObj.find('[data-name="PrivilegeActionType"]').text(privilegeActionType);
          
        	lineItemsListRowObj.find('[data-name="PrivilegeObjectType"]').text(privilegeObjectType);
          
        	lineItemsListRowObj.find('[data-name="PrivilegeObjectName"]').text(privilegeObjectName);
                   
         
    	lineItemsListRowObj[0].setAttribute('data-row-info', JSON.stringify(privilegeGroupItemsObject));
        processQueryResultItemDataObject(privilegeGroupItemsObject, "privilegeGroupItems", lineItemsListRowObj, parentElement);
            
    	privilegeGroupItemsTbodyTag.append(lineItemsListRowObj);
     }
     processQueryResultList(privilegeGroupItems, "privilegeGroupItems");
}

function getParametersFromURLAndSearchFields()
{
	var paramsMap = getQueryParams();
	//Override URL parameters values With search field values if same parameter name exists
	var searchDiv = $('[data-name="BalanceSheetReportSearchDiv"]');
	var searchFieldsList = searchDiv.find('[data-is-search-field="1"]');
	for (var i = 0; i < searchFieldsList.length; i++)
	{
		var searchFieldDiv = $(searchFieldsList[i]);
		var parameterName = searchFieldDiv.data('parameter-name');
		var userInputValue = searchFieldDiv.find('[data-is-search-input-field ="1"]').val();
		if(paramsMap.hasOwnProperty(parameterName))
		{
			paramsMap.parameterName = userInputValue;
		}
		else
		{
			paramsMap[parameterName] = userInputValue;
		}
	}
	return paramsMap;	
}


