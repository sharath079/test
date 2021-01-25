
function initialiseUserTypePrivilegesList()
{
	
	
}
function configurePrivilegesToDisplay(privilegeGroupDataObject)
{
	var privilegeRows = $('[date-name="privilegeRow"]');
	privilegeRows.hide();
	var applicableUserType = privilegeGroupDataObject['applicableUserType'];
	var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];
	if(applicableUserType=='Admin')
	{
		privilegeRows.show();
	}
	else
	{
		if(1>2)
		{
		}
		
	}
}
function isPrivilegeItemExists(privilegeItemInfo, selfServicePrivilegeItemsList)
{
	var privilegeActionType = privilegeItemInfo['privilegeActionType'];
	var privilegeObjectType = privilegeItemInfo['privilegeObjectType'];
	var privilegeObjectName = privilegeItemInfo['privilegeObjectName'];
	for(var i = 0; i< selfServicePrivilegeItemsList.length; i++)
	{
		var selfServicePrivilegeItemInfo = selfServicePrivilegeItemsList[i];
		var selfServicePrivilegeActionType = selfServicePrivilegeItemInfo['privilegeActionType'];
		var selfServicePrivilegeObjectType = selfServicePrivilegeItemInfo['privilegeObjectType'];
		var selfServicePrivilegeObjectName = selfServicePrivilegeItemInfo['privilegeObjectName'];
		if(selfServicePrivilegeActionType == privilegeActionType
				 && selfServicePrivilegeObjectType == privilegeObjectType
				 && selfServicePrivilegeObjectName == privilegeObjectName)
		{
			return true;
		}
	}
	return false;
}
function getSelectedPrivilegesList(privilegeGroupDataObject)
{
	var applicableUserType = privilegeGroupDataObject['applicableUserType'];
	var selfServiceUser = privilegeGroupDataObject['selfServiceUser'];
	var selectedPrivilegesList = getAllSelectedPrivilegesList();
	if(applicableUserType=='Admin')
	{
		return selectedPrivilegesList;
	}
	else
	{
		if(1>2)
		{
		}
		
	}
}
function getAllSelectedPrivilegesList()
{
	var privilegeGroupItemsList = [];
	var privilegeGroupItemElements = $('[data-name="privilegeGroupItem"]');
	for(var i =0; i< privilegeGroupItemElements.length; i++)
	{
		var privilegeGroupItemObj = $(privilegeGroupItemElements[i]);
		var isChecked = privilegeGroupItemObj.is(':checked');
		if(isChecked == true)
		{
			var privilegeActionType = privilegeGroupItemObj.data("privilege-action-type");
			var privilegeObjectType = privilegeGroupItemObj.data("object-type");
			var privilegeObjectName = privilegeGroupItemObj.parents('[date-name="privilegeRow"]').data("object-name");
			var privilegeItemInfo = {
					'privilegeActionType':privilegeActionType,
					'privilegeObjectType':privilegeObjectType,
					'privilegeObjectName':privilegeObjectName
			};
			privilegeGroupItemsList.push(privilegeItemInfo);
		}
	}
	return privilegeGroupItemsList;
}
function toggleEntityActionTypes(e) 
{
	var entityCheckboxValue = $(e).is(":checked");
	$(e).parents('[date-name="privilegeRow"]').find('[data-name="privilegeGroupItem"]').prop("checked", entityCheckboxValue);
}
function toggleEntityCheckbox(e) 
{
	var entityCheckbox = $(e).parents('[date-name="privilegeRow"]').find('[data-name="entityCheckbox"]');
	var privilegeItems = $(e).parents('[date-name="privilegeRow"]').find('[data-name="privilegeGroupItem"]');
	var entityCheckboxValue = true;
	for(var i =0; i< privilegeItems.length; i++)
	{
		if($(privilegeItems[i]).is(":checked") == false)
		{
			entityCheckboxValue = false;
		}
	}
	entityCheckbox.prop("checked", entityCheckboxValue);
}
function selectAllPrivilegeItems()
{
	$('[data-name="entityCheckbox"]').prop("checked", true);
	$('[data-name="privilegeGroupItem"]').prop("checked", true);
}
function unselectAllPrivilegeItems()
{
	$('[data-name="entityCheckbox"]').prop("checked", false);
	$('[data-name="privilegeGroupItem"]').prop("checked", false);
}
function toggleSelectedActionTypePrivileges(e) 
{
	var privilegeActionType = $(e).data("privilege-action-type");
	var checkboxValue = $(e).is(":checked");
	$(e).parents('table').find('[data-privilege-action-type="'+privilegeActionType+'"]').prop("checked", checkboxValue);
}
