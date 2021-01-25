function initializeOnLoadForLoginSuperUser() 
{
	
	$("#branchInfoBranchId").data("branchInfo-id", -1);		
	$('[data-name="employeeName"]').focus();
}
function lookupRowSelectedFor()
{
}
function userLogin(paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var employeeName = $('[data-name="employeeName"]').val();
	var password = $('[data-name="password"]').val();
	var paramsInfo = {'loginId':employeeName, 'loginPassword':password};
	
    var loginInfoJsonData = {'paramsInfo': JSON.stringify(paramsInfo)};
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
    	url : urlContextPath + '/loginAdmin',
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
        		var landingUrl = gHomePageAfterLoginUrl;
        		if(!landingUrl || landingUrl.length == 0)
    			{
        			landingUrl = "/home";
    			}
            	if(getCookie("issuperuser") == 1)
        		{
                	location.href = landingUrl;
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
	var paramsInfo = {'branchId': branchId};
	userLogin(paramsInfo);
}

