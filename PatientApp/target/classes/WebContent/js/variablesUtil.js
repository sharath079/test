var APPLICATION_URL_PREFIX = "";
var gHomePageBeforeLoginUrl = '';

var gHomePageAfterLoginUrl = '';

var gLayoutType = "";
var CORE_ENTITIES = new Set();
CORE_ENTITIES.add("Organisations");
CORE_ENTITIES.add("UserInfo");
CORE_ENTITIES.add("PrivilegeGroup");
CORE_ENTITIES.add("PrivilegeGroupItems");
CORE_ENTITIES.add("EmployeeRoles");
CORE_ENTITIES.add("LoginSessionInfo");

function initializePageOnload()
{
		
	setLoginUserName();
	initializeMenuNew();
	getLoginUserPrivilegeItems();
	configureHtmlElements();
}
function setLoginUserName()
{
	var userName = getCookie("username");
	userName = userName.replace(/\+/g, " ");
	$('[data-name="loginUserName"]').text(userName);
}
function initialiezMenuOnload()
{
	var moduleMenuDiv = $('[data-name="moduleMenuDiv"]');
	var menuLinks = moduleMenuDiv.find('[data-name="MenuLink"]');
	var organisationsId =   getCookie("organisationsid");
	for(var i =0; i< menuLinks.length; i++)
	{
		var menuLinkObj = $(menuLinks[i]);
		var menuLinkType = menuLinkObj.data("link-type"); 
		var entityName = menuLinkObj.data("entity-name");
		if(menuLinkType == "entity")
		{
			if(organisationsId < 0 && !CORE_ENTITIES.has(entityName))
			{
				menuLinkObj.parent('li').remove();
			}
			else if(organisationsId > 0 && CORE_ENTITIES.has(entityName))
			{
				menuLinkObj.parent('li').remove();
			}
		}
	}
}
function getLoginUserPrivilegeItems()
{
	var useriId = getCookie("userid");
	if(!useriId  || useriId.length ==  0)
	{
		return;
	}
	var urlContextPath = "";
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
    	url : urlContextPath + '/getLoginUserPrivilegeItems'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: {},
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var privilegeGroupItemsList = responseData['privilegeGroupItemsList'];
            	showUserActionItemsInPage(privilegeGroupItemsList);
            }
        }
    });		
}
function showUserActionItemsInPage(privilegeGroupItemsList)
{
	for(var i =0; i< privilegeGroupItemsList.length; i++)
	{
		var privilegeGroupItemInfo = privilegeGroupItemsList[i];
		var privilegeActionType = privilegeGroupItemInfo['privilegeActionType']; 
		var privilegeObjectType = privilegeGroupItemInfo['privilegeObjectType'];
		var privilegeObjectName = privilegeGroupItemInfo['privilegeObjectName'];
		$('[data-privilege-item="'+privilegeActionType+privilegeObjectType+privilegeObjectName+'"]').show();
	}
/*	if(getCookie("issuperuser") == 1)
	{
		$('[data-element-name="userActionItem"]').show();
		return;
	}*/	
}
function userLogout()
{
    var loginInfoJsonData = {};
	var urlContextPath = "";// getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'loginUserType': getCookie("loginusertype"),
            'selfServiceUserType': getCookie("selfserviceusertype")
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
    	url : urlContextPath + '/logoutUser'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
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
            	var landingUrl = "/";
            	if(this.loginUserType == 'Admin')
        		{
            		landingUrl = gHomePageBeforeLoginUrl;
            		if(!landingUrl || landingUrl.length == 0)
        			{
            			landingUrl = "/home";
        			}
        		}
            	else
        		{
            		if(1 > 2)
        			{
        			}
            		
        			if(!landingUrl || landingUrl.length == 0)
    				{
        				landingUrl = "/"
    				}
        		}
            	location.href =  landingUrl;
            }
        }
    });		
}
function getUserSessionInfo()
{	
	var userId = getCookie('userid');
	var isUserLoggedIn = 0;
	if(userId && userId > 0)
	{
		isUserLoggedIn = 1;
	}
	var userSessionInfo = {'isUserLoggedIn' : isUserLoggedIn, 'userId' : userId};
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
		var hideWhenLoggedIn = configurableElement.data('hide-when-logged-in')
		if(isLoginRequired==1 && isUserLoggedIn==1)
		{
			if(isUserLoggedIn==1)
			{
				configurableElement.show();														
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
