function resendActivationLink()
{
	var loginEmailId = $('[data-name="loginEmailId"]').val();
	if(loginEmailId.length == 0)
	{
		alert("Enter Email Id");
		return;
	}
	if(!loginEmailId.match(EMAIL_ID_REGEX))
	{
		showAlert("Enter valid Emaild");
		return;
	}
    var paramsInfo = {'loginEmailId':loginEmailId};
    var loginInfoJsonData = {'paramsInfo':JSON.stringify(paramsInfo)};
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
    	url : urlContextPath + '/resendActivationLink',
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
            	location.href =  gHomePageBeforeLoginUrl;
            }
        }
    });		
}
function sendResetPasswordLink()
{
	var loginEmailId = $('[data-name="loginEmailId"]').val();
	if(loginEmailId.length == 0)
	{
		alert("Enter Mail Id");
		return;
	}
	if(!loginEmailId.match(EMAIL_ID_REGEX))
	{
		showAlert("Enter valid Mail Id");
		return;
	}
	var paramsInfo = {'loginEmailId':loginEmailId};
    var loginInfoJsonData = {'paramsInfo':JSON.stringify(paramsInfo)};
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
    	url : urlContextPath + '/sendResetPasswordLink',
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
            	location.href =  gHomePageBeforeLoginUrl;
            }
        }
    });		
}
function resetPassword()
{
	var paramsMap = getQueryParams();
	var emailId = paramsMap['emailId'];
	var loginContactNo = paramsMap['loginContactNo'];
	var token = paramsMap['token'];
	var loginPassword = $('[data-name="loginPassword"]').val();
	var retypePassword = $('[data-name="retypePassword"]').val();
	if(loginPassword.length == 0)
	{
		showAlert("Enter New Password");
		return;
	}
	if(loginPassword != retypePassword)
	{
		showAlert("Enter same Password in two fields");
		return;
	}
    var paramsInfo = {'loginContactNo':loginContactNo, 'loginPassword':loginPassword, 'loginEmailId':emailId, 'token':token};
    var loginInfoJsonData = {'paramsInfo':JSON.stringify(paramsInfo)};
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
    	url : urlContextPath + '/resetPassword',
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
            	location.href =  gHomePageBeforeLoginUrl;
            }
        }
    });		
}
