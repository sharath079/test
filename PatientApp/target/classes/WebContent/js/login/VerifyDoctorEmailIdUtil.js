function initializeOnLoadForVerifyEmailId()
{
	var paramsMap = getQueryParams();
	var emailId = paramsMap['emailid'];
	var token = paramsMap['token'];
	var paramsInfo = {'emailId':emailId, 'token':token};
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
    	url : urlContextPath + '/validatedoctoremailid',
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
            	location.href = "/";
            }
        }
    });		
}
