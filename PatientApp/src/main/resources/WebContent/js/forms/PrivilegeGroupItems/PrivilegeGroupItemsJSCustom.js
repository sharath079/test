function savePrivilegeGroupItemsList()
{
	var privilegeGroup = {};
    var privilegeGroupDataObject = $('[data-name="PrivilegeGroupConfigurationPopup"]').data("privilegeGroup");
	//var privilegeGroupId = $("#idValueForPrivilegeGroup").val();
	var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
	privilegeGroup['privilegeGroupId'] = privilegeGroupId;
	var privilegeGroupItemsList = getSelectedPrivilegesList(privilegeGroupDataObject);
	privilegeGroup['privilegeGroupItemsList'] = privilegeGroupItemsList;
    var privilegeGroupJsonData = {'paramsInfo': JSON.stringify(privilegeGroup), 'requestType' : 'updatePrivilegeGroupItemsList', 'objectType' : 'PrivilegeGroup'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'POST',
		url : urlContextPath + '/updatePrivilegeGroupItemsList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: privilegeGroupJsonData,
        success: function (responseData)
        {
            closePopUp();
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
function showPrivilegeConfigurationPopup(e)
{
	var privilegeGroupRowElement = $(e).parents('[data-name="privilegeGroupRow"]');
    var privilegeGroupDataObject  = privilegeGroupRowElement.data('privilegeGroup');
	configurePrivilegesToDisplay(privilegeGroupDataObject);
    var privilegeGroupId = privilegeGroupDataObject['privilegeGroupId'];
    retrieveDependentPrivilegeGroupItemsList({'privilegeGroupId':privilegeGroupId, 'successCallbackFunction': setConfiguredPrivilegeGroupItems});
    showPopup('PrivilegeGroupConfigurationPopup', e, 1);
    $('[data-name="PrivilegeGroupConfigurationPopup"]').data("privilegeGroup", privilegeGroupDataObject);
}
function setConfiguredPrivilegeGroupItems(responseData)
{
    if (responseData['alert'])
    {
        showAlert(responseData['alert']);
    }
    if (responseData['success'] == 1)
    {
    	var privilegeGroupItemsList = responseData['privilegeGroupItemsList'];
    	$('[data-name="privilegeGroupItem"]').prop("checked", false);
    	$('[data-name="entityCheckbox"]').prop("checked", false);
    	for(var i =0; i< privilegeGroupItemsList.length; i++)
		{
    		var privilegeGroupItemInfo = privilegeGroupItemsList[i];
    		var privilegeActionType = privilegeGroupItemInfo['privilegeActionType']; 
    		var privilegeObjectType = privilegeGroupItemInfo['privilegeObjectType'];
    		var privilegeObjectName = privilegeGroupItemInfo['privilegeObjectName'];
    		$('[data-object-name="'+privilegeObjectName+'"]').find('[data-privilege-action-type="'+privilegeActionType+'"]').prop("checked", true);
		}
    	var privilegeItems = $('[data-name="entityCheckbox"]');
    	for(var i =0; i< privilegeItems.length; i++)
    	{
    		toggleEntityCheckbox(privilegeItems[i]);
    	}    	
    }
}
