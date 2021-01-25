function initializeOnLoadForLoginSuperUser() 
{
	
	$("#branchInfoBranchId").data("branchInfo-id", -1);		
	$('[data-name="employeeName"]').focus();
}
function lookupRowSelectedFor()
{
}
function userLogin()
{
	var employeeName = $('[data-name="employeeName"]').val();
	var password = $('[data-name="password"]').val();
	var paramsInfo = {'loginEmailId':employeeName, 'loginPassword':password};
    var loginInfoJsonData = { 'paramsInfo': JSON.stringify(paramsInfo)};
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
    	url : urlContextPath + '/loginSelfServiceUserEmployeeRoles',
        data: loginInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
    			var landingUrl =  gHomePageBeforeLoginUrlForEmployeeRoles;
    			if(!landingUrl || landingUrl.length == 0)
				{
    				landingUrl = "/"
				}
            	location.href = landingUrl;
            }
        }
    });		
}
function userLoginWithBranch()
{
	var branchId = $("#branchInfoBranchId").data("branchInfo-id");
	if(branchId < 0)
	{
		alert("Select Branch");
		return;	
	}
    var loginInfoJsonData = {'branchId': branchId, 'requestType' : 'loginWithBranchFromUI'};
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
    	url : urlContextPath + '/AjaxServlet',
        data: loginInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
    			var landinUrl =  gHomePageBeforeLoginUrlForEmployeeRoles;
    			if(!landinUrl || landinUrl.length == 0)
				{
    				landinUrl = "/"
				}
            	location.href = landingUrl;
            }
        }
    });		
}

