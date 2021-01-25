$(document).ready(function() {
	initializeReportInfo();
	initializePageLevelData();
});

var employeeRolesDataTableRowTemplate = "";
employeeRolesTbodyTag = "";

function initializePageLevelData()
{
	
	
	
	var employeeRolesDiv = $('[data-name="EmployeeRoles"]');
	employeeRolesTbodyTag = employeeRolesDiv.find('tbody').first();
	employeeRolesDataTableRowTemplate = employeeRolesTbodyTag.find('tr').first()[0].outerHTML;
	
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
	dataObj.objectType = "UserInfo";
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
                   
                	var userInfo = fieldsDataWithoutOverrideWhereClause['userInfo'];                
		                
		                
		                var userFirstName = userInfo['userFirstName'];
		                $('[data-name="UserFirstName"]').text(userFirstName);
		                
		                
		                var userLastName = userInfo['userLastName'];
		                $('[data-name="UserLastName"]').text(userLastName);
		                
		                
		                var organisationsUserOrg = userInfo['organisationsUserOrg'];
		                $('[data-name="OrganisationsUserOrg"]').text(organisationsUserOrg);
		                
		                
		                var loginId = userInfo['loginId'];
		                $('[data-name="LoginId"]').text(loginId);
		                
		                
		                var loginEmailId = userInfo['loginEmailId'];
		                $('[data-name="LoginEmailId"]').text(loginEmailId);
		                
		                
		                var contactNo = userInfo['contactNo'];
		                $('[data-name="ContactNo"]').text(contactNo);
		                
		                
		                var loginPassword = userInfo['loginPassword'];
		                $('[data-name="LoginPassword"]').text(loginPassword);
		                
		                
		                var resetToken = userInfo['resetToken'];
		                $('[data-name="ResetToken"]').text(resetToken);
		                                
                
                var fieldsDataWithOverrideWhereClause = responseData['fieldsDataWithOverrideWhereClause'];                
	                                
			    var layoutCustomDataFieldsObject = responseData['layoutCustomDataFieldsObject'];               
                
                var tablesData = responseData['tablesData'];
                                  
                var employeeRolesDataRowsList = tablesData['employeeRolesDataRowsList'];                
                displayEmployeeRoles(employeeRolesDataRowsList);
                
                var graphsData = responseData['graphsData'];
                
            }
        }
    });
}

 


function displayEmployeeRoles(employeeRoles, parentElement)
{
	var paramsMap = "";
    var employeeRolesDiv = $('[data-name="EmployeeRoles"]');
	if(!(typeof parentElement === 'string' || parentElement instanceof String || typeof parentElement === 'undefined'))
	{
	        employeeRolesDiv = parentElement.find('[data-name="EmployeeRoles"]');
	        employeeRolesTbodyTag = employeeRolesDiv.find('tbody').first();
			employeeRolesDataTableRowTemplate = employeeRolesTbodyTag.find('tr').first()[0].outerHTML;	        	       	  
    }
    employeeRolesTbodyTag.empty();
    for (var i = 0; i < employeeRoles.length; i++)
    {
        var employeeRolesObject = employeeRoles[i];
          
        var privilegeGroup = employeeRolesObject['privilegeGroup'];    	
          
        var description = employeeRolesObject['description'];    	
                   
             
		         
        var lineItemsListRowObj = $(employeeRolesDataTableRowTemplate); 
          
        	lineItemsListRowObj.find('[data-name="PrivilegeGroup"]').text(privilegeGroup);
          
        	lineItemsListRowObj.find('[data-name="Description"]').text(description);
                   
         
    	lineItemsListRowObj[0].setAttribute('data-row-info', JSON.stringify(employeeRolesObject));
        processQueryResultItemDataObject(employeeRolesObject, "employeeRoles", lineItemsListRowObj, parentElement);
            
    	employeeRolesTbodyTag.append(lineItemsListRowObj);
     }
     processQueryResultList(employeeRoles, "employeeRoles");
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


