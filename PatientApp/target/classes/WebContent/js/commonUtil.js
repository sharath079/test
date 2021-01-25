function getUserSessionInfo()
{	
	var userType = getCookie('usertype');
	var userId = getCookie('userid');
	var employeeId = getCookie('employeeid');
	var isUserLoggedIn = 0;
	if(1>2)
	{
		//do nothing
	}
	
	else if(employeeId && employeeId > 0)
	{
		isUserLoggedIn = 1;		
	}
	var userSessionInfo = {'isUserLoggedIn' : isUserLoggedIn, 'userId' : userId, 'employeeId' : employeeId, 'userType' : userType};
	return userSessionInfo;
}
function configureHtmlElements()
{
	var configurableElements = $('[data-is-configurable-element="1"]')
	var userSessionInfo = getUserSessionInfo();
	var isUserLoggedIn = userSessionInfo['isUserLoggedIn'];
	for(var i=0; i<configurableElements.length; i++)
	{
		var requiredQualificationCount = 0;
		var configurableElement = $(configurableElements[i]);
		var isLoginRequired = configurableElement.data('is-login-required')
		var loginUserType = configurableElement.data('login-user-type')
		var hideWhenLoggedIn = configurableElement.data('hide-when-logged-in')
		if(isLoginRequired==1 && isUserLoggedIn==1)
		{
			var loggedInUserType = userSessionInfo['userType'];
			if(isUserLoggedIn==1)
			{
				if(1>2)
				{
					//do nothing
				}
				
				else if(!loginUserType || loginUserType.length==0)
				{
					if(!loggedInUserType || loggedInUserType.length==0)
					{
						configurableElement.show();														
					}
				}				
			}
		}
		if(hideWhenLoggedIn==1)
		{
			if(isUserLoggedIn==1)
			{
				configurableElement.hide();																	
			}
			else
			{
				configurableElement.show();
			}
		}		
	}
}
