var DATE_FORMAT = 'dd/mm/yy';
var ROW_ITEM_BG_COLOR = "#dfeaf4";
var EMAIL_ID_REGEX = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
function showAlert2()
{

	alert("Is Sub Branch option changed");
}


function closePopUp()
{

	var spinnerPopUp = $('[data-name="spinnerPopUp"]')
	spinnerPopUp.data("is-displayed", 0);
	spinnerPopUp.data("is-processing", 0);
	spinnerPopUp.hide();
}
function getEndingIdFromUrl()
{

	var currentUrl = getUrlPathBeforQueryParams();
	var urlSplit = currentUrl.split("/");
	var i = urlSplit.length;
	var endingId = urlSplit[i - 1];
	var anchorIndex = endingId.indexOf("#");
	if (anchorIndex > 0)
	{
		endingId = endingId.substring(0, anchorIndex);
	}
	return endingId;
}
function getUrlPathBeforQueryParams()
{

	var currentUrl = document.URL;
	var decodedUrl = decodeURIComponent(currentUrl);
	var urlSplit = decodedUrl.split("?");
	return urlSplit[0];
}
function resetFocusableElementsTabIndex()
{

	// $(':focusable, :tabbable').attr("tabindex", "10");
	$('select, input, button, textarea, a[href], area[href], iframe, [tabindex]').attr("tabindex", "10");
}

function showAlert(msg)
{

	if (msg == 'abcd')
	{
		msg = "Your request could not be processed by the server. Contact your system administrator if this issue repeats.";
	}
	alert(msg);
}
function stopEventPropagation(evt)
{

	if (!evt) return;

	if (evt.stopPropagation)
	{
		evt.stopPropagation();
	}
	else
	{
		evt.cancelBubble = true;
	}
	if (evt.hasOwnProperty("preventBubble"))
	{
		evt.preventBubble = true;
	}
}

function showMatchingDropDownItems(event, e, isKeyDownEvent, paramsMap, urlRequestParamsInfo)
{

	var debug = 0;
	if (debug == 1)
	{
		console.log("=======================");
	}
	var itemIdDataName = paramsMap['itemIdDataName'];
	var itemInputDataName = paramsMap['itemInputDataName'];
	var responseObjKeyName = paramsMap['responseObjKeyName'];
	var resultItemIdKeyName = paramsMap['resultItemIdKeyName'];
	var resultItemNameKeyName = paramsMap['resultItemNameKeyName'];
	var resultItemNameKeyName2 = paramsMap['resultItemNameKeyName2'];
	var callbackFunctionName = paramsMap['callbackFunctionName'];
	var useCallBack = paramsMap['useCallBack'];
	var useCallBackForLoadingItems = paramsMap['useCallBackForLoadingItems'];
	var callBackFunctionForLoadingItems = paramsMap['callBackFunctionForLoadingItems'];
	var attachListItemObject = 0;
	if (paramsMap.hasOwnProperty("attachListItemObject"))
	{
		attachListItemObject = paramsMap['attachListItemObject'];
	}

	if (isKeyDownEvent != true || event.keyCode != 9)
	{
		stopEventPropagation(event);
	}
	var functionalKeyPressed = 0;

	if (event.keyCode == 40)
	{
		// down key pressed
		functionalKeyPressed = 1;
		selectNextDropDownItem(e);
	}
	else if (event.keyCode == 38)
	{
		// up key pressed
		functionalKeyPressed = 1;
		selectPreviousDropDownItem(e);
	}
	else if (event.keyCode == 13 || (event.keyCode == 9 && isKeyDownEvent == true))
	{
		// enter key pressed
		var isKeyDown = 0;
		if (isKeyDownEvent == true)
		{
			isKeyDown = 1;
		}
		if (debug == 1)
		{
			console.log("else if (event.keyCode == 13 || (event.keyCode == 9 && isKeyDownEvent == true))");
		}
		stopEventPropagation(event);
		if (window.preventDuplicateKeyPresses)
		{
			return;
		}
		window.preventDuplicateKeyPresses = true;
		window.setTimeout(function()
		{

			window.preventDuplicateKeyPresses = false;
		}, 1500);
		functionalKeyPressed = 1;
		var highlightedDropDownItem = getHighlightedDropDownItem(e);
		if (highlightedDropDownItem)
		{
			var containerDropDownDivElement = $(e).next("[data-name='containerDropDownDiv']");
			if (useCallBack == 1)
			{
				eval(callbackFunctionName + '(event, highlightedDropDownItem, {"isKeyDownEvent" : "' + isKeyDown + '"})');
			}
			else
			{
				var dropDownItemId = $(highlightedDropDownItem).data(itemIdDataName);
				var itemName = $(highlightedDropDownItem).text();
				var itemInfoObj = containerDropDownDivElement.prev('[data-name="' + itemInputDataName + '"]');
				itemInfoObj.val(itemName);
				itemInfoObj.data(itemIdDataName, dropDownItemId);
			}
			var itemName = $(highlightedDropDownItem).text();
			containerDropDownDivElement.data('prev-search-prefix', itemName);
			containerDropDownDivElement.hide();
			if (debug == 1)
			{
				console.log("prev search prefix : " + itemName)
			}
		}
	}

	if (functionalKeyPressed == 1)
	{
		if (debug == 1)
		{
			console.log("functionalKeyPressed == 1")
		}
		var showSelectedItem = paramsMap['showSelectedItem'];
		if (showSelectedItem == 1)
		{
			var highlightedDropDownItem = getHighlightedDropDownItem(e);
			if (highlightedDropDownItem)
			{
				var itemName = $(highlightedDropDownItem).data("item-name");
				var containerDropDownDivElement = $(e).next("[data-name='containerDropDownDiv']");
				var itemInfoObj = containerDropDownDivElement.prev('[data-name="' + itemInputDataName + '"]');
				itemInfoObj.val(itemName);
			}
		}
		return;
	}

	var currentSearchPrefix = paramsMap['currentSearchPrefix'];

	var containerDropDownDivElement = $(e).next("[data-name='containerDropDownDiv']");
	var containerDropDownUlElement = containerDropDownDivElement.find("[data-name='containerDropDownUl']");

	//

	if (event.keyCode == 8 || event.keyCode == 46)
	{
		// backspace delete
		/*
		 * if onfocus not defined
		 */
		var onfocusAttributeValue = $(e).attr("onfocus");
		if (!onfocusAttributeValue && containerDropDownDivElement.data('initial-focus') != 0)
		{
			containerDropDownDivElement.data('initial-focus', 0);
			if (debug == 1)
			{
				console.log("onfocus not defined")
			}
		}
	}
	//
	var initialFocus = containerDropDownDivElement.data('initial-focus');
	// if (currentSearchPrefix == null || (currentSearchPrefix.length < 0 &&
	// $(e).data("show-drop-down-onfocus") != 1))
	if (currentSearchPrefix == null || (currentSearchPrefix.length < 0 && initialFocus == 0))
	{
		$(e).data(itemIdDataName, -1);
		containerDropDownUlElement.html('');
		containerDropDownDivElement.data('prev-search-prefix', '');
		containerDropDownDivElement.hide();
	}
	else
	{
		/*
		 * var prevSearchPrefix =
		 * containerDropDownDivElement.data('prev-search-prefix'); if
		 * (prevSearchPrefix == currentSearchPrefix) { if
		 * ($(e).data(itemIdDataName) <= 0) {
		 * containerDropDownDivElement.show(); if
		 * ($(e).data("show-drop-down-onfocus") != 1) {
		 * selectCurrentDropDownLi(containerDropDownUlElement.find('[data-name="containerDropDownLi"]')[0]); } }
		 * return; }
		 */
		if (initialFocus != 0 && currentSearchPrefix.length == 0)
		{
			if (debug == 1)
			{
				console.log("initialFocus != 0 && currentSearchPrefix.length == 0");
			}
			containerDropDownDivElement.data('initial-focus', 0);
			containerDropDownDivElement.data('prev-search-prefix', '');
			$(e).data(itemIdDataName, -1);
			return;
		}
		if (initialFocus != 0 && currentSearchPrefix.length > 0)
		{
			if (debug == 1)
			{
				console.log("initialFocus != 0 && currentSearchPrefix.length > 0");
			}
			containerDropDownDivElement.data('initial-focus', 0);
			if ($(e).data(itemIdDataName) > 0)
			{
				containerDropDownDivElement.data('prev-search-prefix', currentSearchPrefix);
				return;
			}
		}
		var prevSearchPrefix = containerDropDownDivElement.data('prev-search-prefix');
		if (prevSearchPrefix === currentSearchPrefix)
		{
			if (debug == 1)
			{
				console.log("prevSearchPrefix === currentSearchPrefix");
			}
			if (initialFocus == 0 && $(e).data(itemIdDataName) > 0)
			{
				return;
			}
		}
		$(e).data(itemIdDataName, -1);
		if (debug == 1)
		{
			console.log("$(e).data(itemIdDataName, -1)")
		}
		containerDropDownUlElement.html('');
		containerDropDownDivElement.data('prev-search-prefix', currentSearchPrefix);
		var requestUrl = paramsMap['requestUrl'];
		var errorMessage = paramsMap['errorMessage'];

		var contextInfo = { 'errorMessage' : errorMessage, 'eventElement' : e }
		contextInfo['itemIdDataName'] = itemIdDataName;
		contextInfo['itemInputDataName'] = itemInputDataName;
		contextInfo['responseObjKeyName'] = responseObjKeyName;
		contextInfo['resultItemIdKeyName'] = resultItemIdKeyName;
		contextInfo['resultItemNameKeyName'] = resultItemNameKeyName;
		contextInfo['useCallBack'] = useCallBack;
		contextInfo['attachListItemObject'] = attachListItemObject;
		contextInfo['callbackFunctionName'] = callbackFunctionName;
		contextInfo['useCallBackForLoadingItems'] = useCallBackForLoadingItems;
		contextInfo['callBackFunctionForLoadingItems'] = callBackFunctionForLoadingItems;

		$.ajax({ context : contextInfo, error : function(responseData)
		{

			showAlert("Error retrieving the data from server!!");
		}, dataType : 'json', type : 'GET', 
		url : requestUrl  +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
		data : urlRequestParamsInfo, success : function(responseData)
		{

			if (responseData['alert'])
			{
				showAlert(responseData['alert']);
			}

			if (responseData['success'] == 1)
			{
				if (this['useCallBackForLoadingItems'] == 1)
				{
					this['callBackFunctionForLoadingItems'](this, responseData);
				}
				else
				{
					loadDropDownItems(this, responseData);
				}
			}
		} });
	}
}

function loadDropDownItems(contextInfo, responseData)
{

	var itemIdDataName = contextInfo['itemIdDataName'];
	var itemInputDataName = contextInfo['itemInputDataName'];
	var responseObjKeyName = contextInfo['responseObjKeyName'];
	var resultItemIdKeyName = contextInfo['resultItemIdKeyName'];
	var resultItemNameKeyName = contextInfo['resultItemNameKeyName'];
	var attachListItemObject = contextInfo["attachListItemObject"];
	var containerDropDownDivElement = $(contextInfo.eventElement).next("[data-name='containerDropDownDiv']");
	var containerDropDownInnerDivElement = containerDropDownDivElement.find("[data-name='containerDropDownInnerDiv']");
	var containerDropDownUlElement = containerDropDownDivElement.find("[data-name='containerDropDownUl']");
	containerDropDownUlElement.html('');
	var itemsList = responseData[responseObjKeyName];
	if (itemsList.length > 0)
	{
		for (var i = 0; i < itemsList.length; i++)
		{
			var dropDownItemInfo = itemsList[i];
			var callbackFunctionName = 'fetchDropDownItemInfo';
			if (contextInfo.useCallBack == 1)
			{
				callbackFunctionName = contextInfo.callbackFunctionName;
			}
			var dropDownItemInfo = itemsList[i];
			var column2Html = '';
			/*
			 * if (resultItemNameKeyName2) { var column2Text =
			 * dropDownItemInfo[resultItemNameKeyName2]; if
			 * (resultItemNameKeyName2 == 'availableStock') { column2Text =
			 * getNumberWithCommas(column2Text.toFixed(QUANTITY_SCALE_FOR_DISPLAY));
			 * if (IS_MULTIPLE_LOCATION_INV_REQUIRED == true) { var
			 * warehouseAvailableStock =
			 * dropDownItemInfo["warehouseAvailableStock"];
			 * warehouseAvailableStock =
			 * getNumberWithCommas(warehouseAvailableStock.toFixed(QUANTITY_SCALE_FOR_DISPLAY));
			 * column2Text = warehouseAvailableStock + " (" + column2Text + "
			 * )"; } } column2Html = '<div style="float:right;">' + column2Text + '</div>' }
			 */
			var liTemplate = '<li class="containerDropDownLi" data-name="containerDropDownLi" onmouseover="selectCurrentDropDownLi(this)" data-item-name= "' + dropDownItemInfo[resultItemNameKeyName] + '" onclick="' + callbackFunctionName + '(event, this, ' + "eval({'itemIdDataName' : '" + itemIdDataName + "', 'itemInputDataName' : '" + itemInputDataName + "'})" + ')"  data-item-id-data-name="' + itemIdDataName + '"  data-item-input-data-name="' + itemInputDataName + '" >' + dropDownItemInfo[resultItemNameKeyName] + column2Html + '</li>';
			var liTemplateObj = $(liTemplate);
			liTemplateObj.data(itemIdDataName, dropDownItemInfo[resultItemIdKeyName]);
			if (attachListItemObject == 1)
			{
				liTemplateObj.data("object-info", dropDownItemInfo);
			}
			containerDropDownUlElement.append(liTemplateObj);
		}

		containerDropDownInnerDivElement[0].style.left = "0px";
		containerDropDownInnerDivElement[0].style.top = "0px";
		containerDropDownInnerDivElement.show();
		containerDropDownDivElement.show();
	}
	else
	{
		containerDropDownDivElement.hide();
	}

}

function selectNextDropDownItem(e)
{

	var containerDropDownDivElement = $(e).next('[data-name="containerDropDownDiv"]');
	var dropDownItemsList = containerDropDownDivElement.find('[data-name="containerDropDownLi"]');
	var previousItemSelected = 0;
	for (var i = 0; i < dropDownItemsList.length; i++)
	{
		var dropDownItemInfoRowElement = $(dropDownItemsList[i]);
		if (dropDownItemInfoRowElement.data("is-highlighted") == 1)
		{
			if (i < dropDownItemsList.length - 1)
			{
				selectCurrentDropDownLi(dropDownItemsList[i + 1]);
				previousItemSelected = 1;
			}
			return;
		}
	}
	if (previousItemSelected == 0)
	{
		if (dropDownItemsList.length > 0)
		{
			selectCurrentDropDownLi(dropDownItemsList[0]);
		}
	}
}
function selectPreviousDropDownItem(e)
{

	var containerDropDownDivElement = $(e).next('[data-name="containerDropDownDiv"]');
	var dropDownItemsList = containerDropDownDivElement.find('[data-name="containerDropDownLi"]');
	var previousItemSelected = 0;
	for (var i = 0; i < dropDownItemsList.length; i++)
	{
		var dropDownItemInfoRowElement = $(dropDownItemsList[i]);
		if (dropDownItemInfoRowElement.data("is-highlighted") == 1)
		{
			if (i > 0)
			{
				selectCurrentDropDownLi(dropDownItemsList[i - 1]);
				previousItemSelected = 1;
			}
			return;
		}
	}
	if (previousItemSelected == 0)
	{
		if (dropDownItemsList.length > 0)
		{
			selectCurrentDropDownLi(dropDownItemsList[0]);
		}
	}
}
function selectCurrentDropDownLi(e, isUpDownKeyEvent)
{

	var containerDropDownUlElement = $(e).parents("[data-name='containerDropDownUl']");
	var dropDownItemsList = containerDropDownUlElement.find("[data-name='containerDropDownLi']");
	dropDownItemsList.data("is-highlighted", 0);
	dropDownItemsList.css("background-color", "");
	dropDownItemsList.css("color", "#000");
	var currentRow = $(e);
	currentRow.css("background-color", "#337ab7");// ROW_ITEM_BG_COLOR
	currentRow.css("color", "#fff");
	currentRow.data("is-highlighted", 1);

	configureDropDownScrollTop(containerDropDownUlElement, currentRow);
	/*
	 * if (isUpDownKeyEvent == true) {
	 * configureDropDownScrollTop(containerDropDownUlElement, currentRow); }
	 */
}
function configureDropDownScrollTop(containerDropDownUlElement, currentRow)
{

	var scrollbarRelativeTopPosition = containerDropDownUlElement.scrollTop();
	var selectedElementRelativeTopPosition = currentRow.position().top;
	var containerHeight = containerDropDownUlElement.height();

	if (selectedElementRelativeTopPosition < 0)
	{
		scrollbarRelativeTopPosition += selectedElementRelativeTopPosition;
		containerDropDownUlElement.scrollTop(scrollbarRelativeTopPosition);
	}
	else if (selectedElementRelativeTopPosition > containerHeight - 30)
	{
		scrollbarRelativeTopPosition += (selectedElementRelativeTopPosition - (containerHeight - 30));
		containerDropDownUlElement.scrollTop(scrollbarRelativeTopPosition);
	}

}
function getHighlightedDropDownItem(e)
{

	var parentElement = $(e).next('[data-name="containerDropDownDiv"]');
	var dropDownItemsList = parentElement.find('[data-name="containerDropDownLi"]');
	for (var i = 0; i < dropDownItemsList.length; i++)
	{
		var itemInfoRowElement = $(dropDownItemsList[i]);
		if (itemInfoRowElement.data("is-highlighted") == 1)
		{
			return itemInfoRowElement[0];
		}
	}
}

function fetchDropDownItemInfo(event, e, paramsMap)
{

	var itemElement = $(e);
	var itemIdDataName = paramsMap['itemIdDataName'];
	var itemInputDataName = paramsMap['itemInputDataName'];
	var selectedItemId = itemElement.data(itemIdDataName);
	var selectedItemName = itemElement.text();
	var containerDropDownDivElement = itemElement.parents('[data-name="containerDropDownDiv"]');
	var inputElement = containerDropDownDivElement.prev('[data-name="' + itemInputDataName + '"]');
	inputElement.val(selectedItemName);
	inputElement.data(itemIdDataName, selectedItemId);
	containerDropDownDivElement.data('prev-search-prefix', selectedItemName);
	containerDropDownDivElement.hide();
}
function dynamicDropDownInputOnBlur(event, e)
{

	setTimeout(function()
	{

		hideContainerDropDown(e);
	}, 500);
}

function hideContainerDropDown(e)
{

	var inputElement = $(e);
	deselectAllDropDownLi(e);
	var containerDropDownDivElement = inputElement.next('[data-name="containerDropDownDiv"]');
	var containerDropDownUlElement = containerDropDownDivElement.find("[data-name='containerDropDownUl']");
	containerDropDownUlElement.html('');
	containerDropDownDivElement.hide();
}
function deselectAllDropDownLi(e)
{

	var containerDropDownDivElement = $(e).next("[data-name='containerDropDownDiv']");
	var containerDropDownUlElement = containerDropDownDivElement.find("[data-name='containerDropDownUl']");
	var dropDownItemsList = containerDropDownUlElement.find("[data-name='containerDropDownLi']");
	dropDownItemsList.data("is-highlighted", 0);
	dropDownItemsList.css("background-color", "");
}
function selectTableRowItemOnFocus(currentRow, tableRowName, event)
{

	/*
	 * if ($(currentRow).data("is-selected") != 1) {
	 * selectCurrentTableRowItem(currentRow, tableRowName); }
	 */
	stopEventPropagation(event);
	selectCurrentTableRowItem(currentRow, tableRowName);
}

var gPopupZIndex =1000;
function showPopup(dataName, e, isDEPopup)
{

	var popupElement = $('[data-name="' + dataName + '"]');
	popupElement.data("selected-element", $(e));
	popupElement.data("last-selected-element", $(e));
	if(isDEPopup != 1)
	{
		var functionName = "initialize" + dataName + "LookupFields";
		eval(functionName + "()");
	}
	gPopupZIndex = gPopupZIndex+100;
	popupElement.css('z-index', gPopupZIndex);
	popupElement.show();
	popupElement.focus();
}

function selectCurrentTableRowItem(e, tableRowName, focusRow)
{

	var itemsList = $(e.parentNode).find('[data-name="' + tableRowName + '"]');
	itemsList.data("is-selected", 0);
	itemsList.css("background-color", "");
	var currentRow = $(e);
	currentRow.css("background-color", ROW_ITEM_BG_COLOR);
	currentRow.data("is-selected", 1);
	if (focusRow == 1)
	{
		currentRow.focus();
	}
}
function closeThisPopupOnEscape(e)
{

	var parentElement = $(e);
	parentElement.hide();
	var lastSelectedElement = parentElement.data("line-item-row");
	if (lastSelectedElement)
	{
		lastSelectedElement.focus();
	}
	var lastSelectedElement = parentElement.data("last-selected-element");
	if (lastSelectedElement)
	{
		lastSelectedElement.focus();
	}
}

function closeThisPopup(e)
{

	var parentElement = $(e).parents('.modal');
	if ($(e).hasClass("modal"))
	{
		parentElement = $(e);
	}
	parentElement.hide();
	var lastSelectedElement = parentElement.data("last-selected-element");
	if (lastSelectedElement)
	{
		lastSelectedElement.focus();
	}
}

function getQueryParams()
{

	var currentUrl = document.URL;
	var decodedUrl = decodeURIComponent(currentUrl);
	var urlSplit = decodedUrl.split("?");
	var paramsList = {};
	if (urlSplit.length > 1)
	{
		var queryParamsUrl = urlSplit[1];
		var queryParamsList = queryParamsUrl.split("&");
		for (var i = 0; i < queryParamsList.length; i++)
		{
			var paramInfo = queryParamsList[i].split("=");
			if (paramInfo.length > 1)
			{
				var paramName = paramInfo[0];
				var paramValue = paramInfo[1];
				paramsList[paramName] = paramValue;
			}
		}
	}
	return paramsList;
}
function showMatchingPageLinksOnTabout(event, e)
{

	showMatchingPageLinks(event, e, true);
}

function showMatchingPageLinks(event, e, isKeyDownEvent)
{

	var itemIdDataName = "pageLinkUrl";
	var itemInputDataName = "pageLinkName";
	var responseObjKeyName = "pageLinksList";
	var resultItemIdKeyName = "pageLinkUrl";
	var resultItemNameKeyName = "pageLinkName";

	var urlContextPath = getContextPath();
	var url = urlContextPath + '/AjaxServlet';

	var requestUrl = url;
	var errorMessage = "abcd";
	var currentSearchPrefix = $.trim($(e).val());

	var urlRequestParamsInfo = { 'pageLinkNameDisplayPrefix' : currentSearchPrefix, 'requestType' : "getPageLinksList" };

	var paramsMap = {};
	paramsMap['itemIdDataName'] = itemIdDataName;
	paramsMap['itemInputDataName'] = itemInputDataName;
	paramsMap['responseObjKeyName'] = responseObjKeyName;
	paramsMap['resultItemIdKeyName'] = resultItemIdKeyName;
	paramsMap['resultItemNameKeyName'] = resultItemNameKeyName;
	paramsMap['currentSearchPrefix'] = currentSearchPrefix;
	paramsMap['requestUrl'] = requestUrl;
	paramsMap['errorMessage'] = errorMessage;
	paramsMap['attachListItemObject'] = 1;
	paramsMap['callbackFunctionName'] = 'getSelectedPageLinkInfo';
	paramsMap['useCallBack'] = 1;

	showMatchingDropDownItems(event, e, isKeyDownEvent, paramsMap, urlRequestParamsInfo);
}

function getSelectedPageLinkInfo(event, e)
{

	stopEventPropagation(event);
	var containerLiElement = $(e);
	var pageLinkInfo = containerLiElement.data("object-info");
	var pageLinkUrl = pageLinkInfo['pageLinkUrl'];
	var containerDropDownDivElement = $(this.containerDropDownDivElement);
	containerDropDownDivElement.find("[data-name='containerDropDownUl']").empty();
	containerDropDownDivElement.hide();
	location.href = pageLinkUrl;
}

function getCurrentPageSearchResults(e, searchResultsDivName, fetchSearchResultsCallbackFunction)
{
	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var selectedPageIndex = searchResultsDiv.data("selected-page-index");
	var nextCurrentPageIndex = $(e).data("current-page-index");
	if (selectedPageIndex == nextCurrentPageIndex)
	{
		return;
	}
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
	var noOfRecordsAlreadyFetched = noOfRecordsToFetch * (nextCurrentPageIndex - 1);
	fetchSearchResultsCallbackFunction(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 0, -1, {}, searchResultsDivName);
}
function getPreviousPageSearchResults(searchResultsDivName, fetchSearchResultsCallbackFunction)
{

	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var currentPageIndex = searchResultsDiv.data("selected-page-index");
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
	var noOfRecordsAlreadyFetched = (currentPageIndex - 2) * noOfRecordsToFetch;
	if (noOfRecordsAlreadyFetched < 0)
	{
		showAlert("This is the first page of search results. No more results exist before this page.");
		return;
	}
	var refreshIndex = 0;
	var refreshStartIndex = -1;
	var nextCurrentPageIndex = currentPageIndex - 1
	if (currentPageIndex != 1 && currentPageIndex % 10 == 1)
	{
		refreshIndex = 1;
		refreshStartIndex = currentPageIndex - 10;
	}
	fetchSearchResultsCallbackFunction(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, {}, searchResultsDivName);
}

function getNextPageSearchResults(searchResultsDivName, fetchSearchResultsCallbackFunction)
{

	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var currentPageIndex = searchResultsDiv.data("selected-page-index");
	var searchResultsTotalPagesCount = searchResultsDiv.data("total-no-of-pages");
	if (currentPageIndex >= searchResultsTotalPagesCount)
	{
		alert("No more results to display.");
		return;
	}
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
	var noOfRecordsAlreadyFetched = currentPageIndex * noOfRecordsToFetch;
	var refreshIndex = 0;
	var refreshStartIndex = -1;
	if (currentPageIndex < searchResultsTotalPagesCount && currentPageIndex % 10 == 0)
	{
		refreshIndex = 1;
		refreshStartIndex = currentPageIndex + 1;
	}
	fetchSearchResultsCallbackFunction(parseInt(currentPageIndex) + 1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, {}, searchResultsDivName);
}
function initDatePicker()
{
    $('[data-name="datePicker"]').daterangepicker({singleDatePicker: true, locale: { format: 'DD/MM/YYYY' }});
    $('[data-name="dateTimePicker"]').daterangepicker({singleDatePicker: true, timePicker: true, timePicker24Hour: true, locale: { format: 'DD/MM/YYYY hh:mm' }});
    $('[data-name="dateTimeWithSecondsPicker"]').daterangepicker({singleDatePicker: true, timePicker: true, timePicker24Hour: true, timePickerSeconds:true, locale: { format: 'DD/MM/YYYY  hh:mm:ss' }});
    $('[data-name="timePicker"]').daterangepicker({singleDatePicker: true, timePicker: true, locale: { format: 'hh:mm A' }}).on('show.daterangepicker', function(ev, picker) {picker.container.find(".calendar-table").hide();});
    $('[data-name="timeWithSecondsPicker"]').daterangepicker({singleDatePicker: true, timePicker: true, timePickerSeconds:true, locale: { format: 'hh:mm:ss A' }}).on('show.daterangepicker', function(ev, picker) {picker.container.find(".calendar-table").hide();});
    $('.date-picker').daterangepicker({singleDatePicker: true, locale: { format: 'DD/MM/YYYY' }});

    $('.date-picker').val('');
    $('[data-name="datePicker"]').val('');
    $('[data-name="dateTimePicker"]').val('');
    $('[data-name="dateTimeWithSecondsPicker"]').val('');
    $('[data-name="timePicker"]').val('');
    $('[data-name="timeWithSecondsPicker"]').val('');
}

function initMonthDatePicker()
{

	$('.date-picker-month').datepicker({ changeMonth : true, changeYear : true, showButtonPanel : true, dateFormat : 'mm/yy', onClose : function(dateText, inst)
	{

		var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		$(this).datepicker('setDate', new Date(year, month));
	}, beforeShow : function(dateText, inst)
	{

		var temp = $(this).val().split('/');
		var month = temp[0];
		var year = temp[1];
		$(this).datepicker('option', 'defaultDate', new Date(year, month - 1));
		$(this).datepicker('setDate', new Date(year, month - 1));
		$('.ui-datepicker-month').show();
	} });
	$('.date-picker-month').focus(function()
	{

		// datePickup();
		$('.ui-datepicker-month').show();
	});
}

function datePickup()
{

	var temp = $('.date-picker-month').val().split('/');
	var month = temp[0];
	var year = temp[1];
	$('date-picker-month').datepicker('option', 'defaultDate', new Date(year, month - 1));
	$('date-picker-month').datepicker('setDate', new Date(year, month - 1));
}

function initYearDatePicker()
{

	$('.date-picker-year').datepicker({ changeMonth : true, changeYear : true, showButtonPanel : true, dateFormat : 'yy', onClose : function(dateText, inst)
	{

		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		$(this).datepicker('setDate', new Date(year));
	}, beforeShow : function(dateText, inst)
	{

		var temp = $(this).val().split('/');
		var year = temp[0];
		$(this).datepicker('option', 'defaultDate', new Date(year));
		$(this).datepicker('setDate', new Date(year));
	} });
}
function getSelectedTableRowElement(tableRowName)
{

	var lineItemsList = $('[data-name="' + tableRowName + '"]');
	for (var i = 0; i < lineItemsList.length; i++)
	{
		var lineItemElement = $(lineItemsList[i]);
		if (lineItemElement.data("is-selected") == 1)
		{
			return lineItemElement;
		}
	}
}

var PAGINATION_NEXT_LINK_TEMPLATE =
    '<li data-name="paginationNextLinkLi" class="paginate_button page-item next" >' +
    '<a href="javascript:void(0);" aria-label="Next" class="page-link" data-is-next="0" onclick="showNextRecords();">' +
    'Next' +
    '</a>' +
    '</li>';

function handleSearchResultsResponse(contextObject, responseObject, responseDataKeyName, totalRecordsCountKeyName, searchResultsDivName, searchResultsTableName, searchResultsTableRowName, setSearchResultsInputsCallbackFunction, loadResultsCallbackFunction, alertMessage)
{

	var refreshIndex = contextObject.refreshIndex;
	var highlightIndex = 1;
	var currentPageIndex = contextObject.nextCurrentPageIndex;
	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var isSearched = searchResultsDiv.data("is-searched");
	var recordCountAndPaginationBlockDiv = searchResultsDiv.find('[data-name="recordCountAndPaginationBlock"]');
	recordCountAndPaginationBlockDiv.data("search-results-div-name", searchResultsDivName);
	var transactionReportList = responseObject[responseDataKeyName];
	if (transactionReportList.length > 0)
	{
		if (isSearched != 1)
		{
			searchResultsDiv.data("no-of-records-to-fetch", contextObject.noOfRecordsToFetch);
			setSearchResultsInputsCallbackFunction(contextObject);
			var totalRecordsCount = responseObject[totalRecordsCountKeyName];// change
			// to
			// be
			// made
			// here
			var searchResultsTotalPagesCount = 0;
			if(contextObject.noOfRecordsToFetch > 0)
			{
				searchResultsTotalPagesCount = totalRecordsCount / contextObject.noOfRecordsToFetch;
			}
			searchResultsDiv.data("total-no-of-pages", searchResultsTotalPagesCount);
			searchResultsDiv.data("is-searched", 1);
			searchResultsDiv.data('search-results-total-records-count', totalRecordsCount);
			recordCountAndPaginationBlockDiv.show();
			recordCountAndPaginationBlockDiv.find('[data-name="totalRecordsCount"]').text(totalRecordsCount);
			if (totalRecordsCount > contextObject.noOfRecordsToFetch
					&& contextObject.noOfRecordsToFetch > 0)
			{
				searchResultsDiv.find('[data-name="paginationUl"]').show();
			}
			else
			{
				searchResultsDiv.find('[data-name="paginationUl"]').hide();
				highlightIndex = 0;
			}

		}

		searchResultsDiv.data("selected-page-index", currentPageIndex);
		loadResultsCallbackFunction(transactionReportList, searchResultsDivName);
		if (refreshIndex == 1)
		{
			refreshTransactionsListSearchResultsIndex(searchResultsDivName, contextObject);
		}
		resultsPageRefreshed(searchResultsDivName, currentPageIndex, transactionReportList.length, highlightIndex);
	}
	else
	{
		var transactionTableList = $("[data-name='" + searchResultsDivName + "']"); // list
		// parent
		// element
		//transactionTableList.find('[data-name="' + searchResultsTableRowName + '"]').remove(); // row
		transactionTableList.find('[data-name="' + searchResultsTableRowName + '"]').hide(); // row
		// of
		// the
		// list
		// to
		// deleted
		recordCountAndPaginationBlockDiv.hide();
		if (isNaN(currentPageIndex) || currentPageIndex == 1)
		{
			if (alertMessage)
			{
				alert(alertMessage);
			}
			else
			{
				var suppressAlert = false;
				if(contextObject.hasOwnProperty("suppressAlert"))
				{
					suppressAlert = contextObject['suppressAlert'];
				}
				if(suppressAlert == false)
				{
					alert("No matching results to display");
				}
			}
		}
		else
		{
			alert("No more results to display.");
		}
		loadResultsCallbackFunction(transactionReportList, searchResultsDivName);
		return;
	}
}

function refreshCurrentResultsSectionStats(searchResultsDivName, currentPageIndex, currentPageRecordsCount)
{

	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
	var currentPageStartingRecordNo = ((currentPageIndex - 1) * noOfRecordsToFetch) + 1;
	var currentPageEndingRecordNo = ((currentPageIndex - 1) * noOfRecordsToFetch) + currentPageRecordsCount;
	searchResultsDiv.find('[data-name="startingRecordNo"]').text(currentPageStartingRecordNo);
	searchResultsDiv.find('[data-name="endingRecordNo"]').text(currentPageEndingRecordNo);
}

function resultsPageRefreshed(searchResultsDivName, currentPageIndex, currentPageRecordsCount, highlightIndex)
{

	refreshCurrentResultsSectionStats(searchResultsDivName, currentPageIndex, currentPageRecordsCount);
	if (highlightIndex == 1)
	{
		var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
		var paginationUlObj = searchResultsDiv.find('[data-name="paginationUl"]');
		var paginationLiObj = paginationUlObj.find('[data-name="paginationLi"]');

		var searchResultsTotalPagesCount = searchResultsDiv.data("total-no-of-pages");
		if (currentPageIndex == 1)
		{
			paginationUlObj.find('[data-name="paginationPreviousLinkLi"]').addClass("disabled");
		}
		else
		{
			paginationUlObj.find('[data-name="paginationPreviousLinkLi"]').removeClass("disabled");
		}
		if (currentPageIndex >= searchResultsTotalPagesCount)
		{
			paginationUlObj.find('[data-name="paginationNextLinkLi"]').addClass("disabled");
		}
		else
		{
			paginationUlObj.find('[data-name="paginationNextLinkLi"]').removeClass("disabled");
		}
		for (var i = 1; i <= paginationLiObj.length; i++)
		{
			var paginationLiObject = $(paginationLiObj[i - 1]);
			if (i == currentPageIndex % 10 || (currentPageIndex % 10 == 0 && i == 10))
			{
				paginationLiObject.addClass("active");
				// paginationLiObject.find("a").focus();
			}
			else
			{
				paginationLiObject.removeClass("active");
			}
		}
	}
}

function refreshTransactionsListSearchResultsIndex(searchResultsDivName, contextObject)
{

	var startPageIndex = contextObject.refreshStartIndex;

	var searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	var paginationUlObj = searchResultsDiv.find('[data-name="paginationUl"]');
	
	var paginationPrevLinkTemplateObj = paginationUlObj.find('[data-name="paginationPreviousLinkLi"]');
	var paginationNextLinkTemplateObj = paginationUlObj.find('[data-name="paginationNextLinkLi"]');
	var paginationIndexLinkTemplateObj = paginationUlObj.find('[data-name="paginationLi"]');
	
	var paginationPrevLinkTemplate = '';
	var paginationNextLinkTemplate = '';
	var paginationIndexLinkTemplate = '';
	
	if(paginationPrevLinkTemplateObj.length>0)
	{
		paginationPrevLinkTemplate = paginationPrevLinkTemplateObj[0].outerHTML;	
	}
	if(paginationNextLinkTemplateObj.length>0)
	{
		paginationNextLinkTemplate = paginationNextLinkTemplateObj[0].outerHTML;
	}
	if(paginationIndexLinkTemplateObj.length>0)
	{
		paginationIndexLinkTemplate = paginationIndexLinkTemplateObj[0].outerHTML;
	}	
	
	paginationUlObj.find('[data-name="paginationNextLinkLi"]').remove();
	paginationUlObj.find('[data-name="paginationLi"]').remove();
	var searchResultsTotalPagesCount = searchResultsDiv.data("total-no-of-pages");
	var replacePaginationFunctions = contextObject['replacePaginationFunctions'];
	for (var pageIndex = 1; pageIndex <= 10; pageIndex++)
	{
		var newIndexCurrentPageNumber = startPageIndex + pageIndex - 1;
		var showPrevOrNextSearchResults = "showPrevOrNextSearchResults";
		var showCurrentPageRecords = "showCurrentPageRecords";
		if (replacePaginationFunctions == 1)
		{
			showPrevOrNextSearchResults = contextObject['showPrevOrNextSearchResults'];
			showCurrentPageRecords = contextObject['showCurrentPageRecords'];
		}
		if(!paginationIndexLinkTemplate || paginationIndexLinkTemplate.length==0)
		{
			paginationIndexLinkTemplate = '<li data-name="paginationLi" class="paginate_button page-item "  ><a href="javascript:void(0);" class="page-link" onkeyup="' + showPrevOrNextSearchResults + '(event, this)" onfocus="' + showCurrentPageRecords + '(this);" data-name="paginationLiLink" >1</a></li>';		
		}
		var paginationLiHtmlObj = $(paginationIndexLinkTemplate);
		var paginationLiLinkObj = paginationLiHtmlObj.find('[data-name="paginationLiLink"]');
		paginationLiLinkObj.data('current-page-index', newIndexCurrentPageNumber);
		paginationLiLinkObj.text(newIndexCurrentPageNumber);
		paginationUlObj.append(paginationLiHtmlObj);
		if (newIndexCurrentPageNumber >= searchResultsTotalPagesCount)
		{
			break;
		}
	}
	if(!paginationNextLinkTemplate || paginationNextLinkTemplate.length==0)
	{
		paginationNextLinkTemplate = PAGINATION_NEXT_LINK_TEMPLATE;	
	}
	if (replacePaginationFunctions == 1)
	{
		paginationNextLinkTemplate = paginationNextLinkTemplate.replace("showNextRecords", contextObject.showNextRecords);
	}
	var paginationNextHtmlObj = $(paginationNextLinkTemplate);
	paginationUlObj.append(paginationNextHtmlObj);
}

function TestEmail()
{

	var errorMessage = "abcd";

	var urlContextPath = getContextPath();
	var url = urlContextPath + '/AjaxServlet';

	var urlRequestParamsInfo = { 'requestType' : "sendEmail" };

	$.ajax({ error : function(responseData)
	{

		showAlert("Error retrieving the data from server!!");
	}, dataType : 'json', type : 'GET', url : url, data : urlRequestParamsInfo, success : function(responseData)
	{

		if (responseData['alert'])
		{
			showAlert(responseData['alert']);
		}

		if (responseData['success'] == 1)
		{
			if (this['useCallBackForLoadingItems'] == 1)
			{
				this['callBackFunctionForLoadingItems'](this, responseData);
			}
			else
			{
				loadDropDownItems(this, responseData);
			}
		}
	} });
}

function isEmptyObj(obj)
{

	for ( var key in obj)
	{
		if (obj.hasOwnProperty(key)) return false;
	}
	return true;
}

function showMenu(menuId)
{

	var moduleMenuDivElements = $('[data-name="moduleMenuDiv"]');
	moduleMenuDivElements.hide();
	$("#" + menuId).show();
	$('[data-name="moduleName"]').text(menuId);
}

function showMenuPanel(e)
{

	var panelBodyElement = $(e).parent().parent().next();
	var isExpanded = panelBodyElement.data('is-expanded');

	var panelBodyElements = $('[data-name="panelBodyDiv"]');
	panelBodyElements.hide();

	for (var i = 0; i < panelBodyElements.length; i++)
	{
		$(panelBodyElements[i]).hide();
		$(panelBodyElements[i]).data('is-expanded', "0");
		// $(panelBodyElements[i]).parent().find('[data-name="downArrow"]').show();
		// $(panelBodyElements[i]).parent().find('[data-name="upArrow"]').hide();
	}

	if (isExpanded == 0)
	{
		$(e).parent().parent().next().show();
		panelBodyElement.data('is-expanded', "1");
		// $(e).parent().find('[data-name="upArrow"]').show();
		// $(e).parent().find('[data-name="downArrow"]').hide();
	}
	else
	{
		// $(e).parent().find('[data-name="upArrow"]').hide();
		// $(e).parent().find('[data-name="downArrow"]').show();
	}

}

function initializeMenu()
{

	var currentUrl = document.URL;
	var decodedUrl = decodeURIComponent(currentUrl);
	var urlSplit = decodedUrl.split("?");
	var urlPath = urlSplit[0];
	var slashLastIndex = urlPath.lastIndexOf("/");
	var pageName = urlPath.substring(slashLastIndex);
	var menuLinkElements = $('[data-name="MenuLink"]');
	for (var i = 0; i < menuLinkElements.length; i++)
	{
		var menuLinkElement = $(menuLinkElements[i]);
		var linkHref = menuLinkElement.attr("href");
		if (linkHref.indexOf(pageName) > 0)
		{
			var moduleMenuDivElement = menuLinkElement.parents('[data-name="moduleMenuDiv"]');
			var menuLevel = menuLinkElement.data("menu-level");

			if (menuLevel == 2)
			{
				var subMenuLinkElement = menuLinkElement.parents('[data-name="menuPanelDiv"]').find('[data-name="subMenuLink"]');
				subMenuLinkElement.click();
				menuLinkElement.parents("li").addClass("active");
			}
			else
			{
				menuLinkElement.parents(".menu-link-item").addClass("active");
			}

			if (moduleMenuDivElement.length > 0)
			{
				var moduleMenuId = moduleMenuDivElement[0].id;
				var moduleLinkElements = $('[data-name="moduleLink"]');
				for (var j = 0; j < moduleLinkElements.length; j++)
				{
					var moduleLinkElement = $(moduleLinkElements[j]);
					var moduleName = moduleLinkElement.data('module-name');
					if (moduleName == moduleMenuId)
					{
						$('[data-name="moduleName"]').text(moduleName);
						showMenu(moduleName);
					}
				}
			}
		}
	}

}

function loadSelectedMenu(e) 
{
	var selectedMenu = $(e).val();
	showMenuNew(selectedMenu);
}

function showMenuNew(menuId)
{
	var moduleMenuDivElements = $('[data-name="moduleMenuDiv"]');
	moduleMenuDivElements.hide();
	$('[data-module-name="' + menuId + '"]').show();
	$('[data-name="modulesList"]').val(menuId);
}

function showSelectedModuleMenu(menuId)
{
	var moduleElement = $('[data-module-name="' + menuId + '"]');
	var moduleMenuLinkElements = moduleElement.find('[data-name="MenuLink"]');
	if(moduleMenuLinkElements.length > 0)
	{
		location.href = $(moduleMenuLinkElements[0]).attr('href');  
	}
}


function initializeMenuNew()
{
	var currentUrl = document.URL;
	var decodedUrl = decodeURIComponent(currentUrl);
	var urlSplit = decodedUrl.split("?");
	var urlPath = urlSplit[0];
	var slashLastIndex = urlPath.lastIndexOf("/");
	var pageName = urlPath.substring(slashLastIndex);
	if(pageName.indexOf("/Create") > -1)
	{
		var entityName = pageName.substring(("/Create").length);
		pageName = "/"+entityName+"Dashboard"; 
	}
	else if(pageName.indexOf("/View") > -1)
	{
		var entityName = pageName.substring(("/View").length);
		pageName = "/"+entityName+"Dashboard"; 
	}
	var menuLinkElements = $('[data-name="MenuLink"]');
	for (var i = 0; i < menuLinkElements.length; i++)
	{
		var menuLinkElement = $(menuLinkElements[i]);
		var linkHref = menuLinkElement.attr("href");
		if (linkHref.indexOf(pageName) > 0)
		{
			var moduleMenuDivElement = menuLinkElement.parents('[data-name="moduleMenuDiv"]');
			//remove below line once SideMenu style finalized
			menuLinkElement.parents("li").addClass("active");
			menuLinkElement.addClass("active"); //for xacadpro layout sidemenu
			var moduleName = moduleMenuDivElement.data('module-name');
			showMenuNew(moduleName);
		}
	}
}

function downloadReportPDF(printFormatCode)
{

	var urlContextPath = getContextPath();
	var paramsMap = getQueryParams();
	var URLParameterNameAndValuesList = [];
	if (isEmptyObj(paramsMap) == false)
	{
		for ( var parameterKey in paramsMap)
		{
			var parameterValue = paramsMap[parameterKey];
			var paramInfoObj = { 'parameterName' : parameterKey, 'parameterValue' : parameterValue };
			URLParameterNameAndValuesList.push(paramInfoObj);
		}
	}
	URLParameterNameAndValuesList = getSearchFielsParams(URLParameterNameAndValuesList);
	var printFormatInfoObj = { 'printFormatCode' : printFormatCode, 'fetchData' : 1, 'URLParameterNameAndValuesList' : URLParameterNameAndValuesList };

	$.ajax({ context : { 'errorMessage' : "abcd" }, error : function(responseData)
	{

		showAlert(this.errorMessage);
	}, dataType : 'json', type : 'GET',
	url : urlContextPath + '/AjaxServlet' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
	data : { 'printFormatInfoObj' : JSON.stringify(printFormatInfoObj), "requestType" : "generateReportPDF", }, success : function(responseData)
	{

		if (responseData['success'] == 1)
		{
			var fileId = responseData["fileId"];
			var urlContextPath = getContextPath();			
			window.open(urlContextPath + "/download?fileId=" + fileId  +'&loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"));
		}
		else
		{
			showAlert(responseData['alert']);
		}
	} });
}
function  getSearchFielsParams(parameterNameAndValuesList)
{    	
	var searchFieldsList = $('[data-name="seachFieldsList"]').first().find('[data-name="fieldDiv"]');    
    for (var i = 0; i < searchFieldsList.length; i++)
    {
        var searchFieldDiv = $(searchFieldsList[i]);
        var parameterName = searchFieldDiv.data('parametername');
        var userInputValue = searchFieldDiv.find('[data-name="userInputValue"]').val();
        var lookupid = searchFieldDiv.find('[data-field-type="LOOKUP"]').data('lookupid');
        if (lookupid)
        {
        	userInputValue = lookupid;
        	if(lookupid=="-1")
    		{
        		userInputValue = "";
    		}                
        }
        var searchFieldInfo = {};
        searchFieldInfo.parameterName = parameterName;
        searchFieldInfo.parameterValue = userInputValue;
        parameterNameAndValuesList.push(searchFieldInfo);
    }
    return parameterNameAndValuesList;
}

function isValidDate(dateString)
{

	try
	{
		if (!dateString || dateString.length == 0)
		{
			return false;
		}
		$.datepicker.parseDate(DATE_FORMAT, dateString);
		return true;
	}
	catch (e)
	{
		return false;
	}
}

function selectTableRowItemOnFocus(currentRow, tableRowName, event)
{

	/*
	 * if ($(currentRow).data("is-selected") != 1) {
	 * selectCurrentTableRowItem(currentRow, tableRowName); }
	 */
	stopEventPropagation(event);
	selectCurrentTableRowItem(currentRow, tableRowName);
}

function selectCurrentTableRowItem(e, tableRowName, focusRow)
{

	var itemsList = $(e.parentNode).find('[data-name="' + tableRowName + '"]');
	itemsList.data("is-selected", 0);
	itemsList.css("background-color", "");
	var currentRow = $(e);
	currentRow.css("background-color", ROW_ITEM_BG_COLOR);
	currentRow.data("is-selected", 1);
	if (focusRow == 1)
	{
		currentRow.focus();
	}
}

function getSelectedTableRowElement(tableRowName)
{

	var lineItemsList = $('[data-name="' + tableRowName + '"]');
	for (var i = 0; i < lineItemsList.length; i++)
	{
		var lineItemElement = $(lineItemsList[i]);
		if (lineItemElement.data("is-selected") == 1)
		{
			return lineItemElement;
		}
	}
}

function selectNextTableRowItem(e, tableRowName)
{

	var lineItemsList = $(e.parentNode).find('[data-name="' + tableRowName + '"]');
	for (var i = 0; i < lineItemsList.length; i++)
	{
		var salesLineItemElement = $(lineItemsList[i]);
		if (salesLineItemElement.data("is-selected") == 1)
		{
			if (i < lineItemsList.length - 1)
			{
				selectCurrentTableRowItem(lineItemsList[i + 1], tableRowName, 1);
			}
			return;
		}
	}
}

function selectPreviousTableRowItem(e, tableRowName)
{

	var lineItemsList = $(e.parentNode).find('[data-name="' + tableRowName + '"]');
	for (var i = 0; i < lineItemsList.length; i++)
	{
		var salesLineItemElement = $(lineItemsList[i]);
		if (salesLineItemElement.data("is-selected") == 1)
		{
			if (i > 0)
			{
				selectCurrentTableRowItem(lineItemsList[i - 1], tableRowName, 1);
			}
			return;
		}
	}
}
function randomRGB() 
{
    var red = randomNum();
    var green = randomNum();
    var blue = randomNum();        
    return rgbToHex(red, green, blue);
  }
function randomNum()
{
    return Math.floor(Math.random() * 256);
}
function componentToHex(c) 
{
	  var hex = c.toString(16);
	  return hex.length == 1 ? "0" + hex : hex;
}

function rgbToHex(r, g, b) 
{
  return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
}

function groupedBarGraph(barGraphInfo)
{
	
	// var colors = [ "#BDB76B", "#556B2F", "#8FBC8F" ];
	var dataList = barGraphInfo.dataList;


	var colors = [];

	var xaxisColumnKeyName = barGraphInfo.xaxisColumn;

	var svgWidth = 400;
	var svgHeight = 400;
	var svgGroupMarginLeft = 40;
	var svgGroupMarginTop = 80;
	var svgGroupMarginBottom = 40;
	var svgGroupMarginRight = 20;
	var barPaddingLeft = 10;
	var barPaddingRight = 10;
	var graphTicks = 10;
	
	var enableSplittedBarGraph = 0;
	if(barGraphInfo.hasOwnProperty("enableSplittedBarGraph"))
	{
		enableSplittedBarGraph = Number(barGraphInfo.enableSplittedBarGraph);
	}

	var commonPropertiesObj = {};
	commonPropertiesObj['svgWidth'] =svgWidth; 
	commonPropertiesObj['svgHeight'] =svgHeight; 
	commonPropertiesObj['svgGroupMarginLeft'] =svgGroupMarginLeft; 
	commonPropertiesObj['svgGroupMarginTop'] =svgGroupMarginTop; 
	commonPropertiesObj['svgGroupMarginBottom'] =svgGroupMarginBottom; 
	commonPropertiesObj['svgGroupMarginRight'] =svgGroupMarginRight;
	
	commonPropertiesObj = graphsCommonProperties(commonPropertiesObj, barGraphInfo);
	
	svgWidth = commonPropertiesObj['svgWidth']
	svgHeight = commonPropertiesObj['svgHeight'];
	svgGroupMarginLeft = commonPropertiesObj['svgGroupMarginLeft'];
	svgGroupMarginTop = commonPropertiesObj['svgGroupMarginTop'];
	svgGroupMarginBottom = commonPropertiesObj['svgGroupMarginBottom'];
	svgGroupMarginRight = commonPropertiesObj['svgGroupMarginRight'];	
	
	if (barGraphInfo.hasOwnProperty('graphTicks'))
	{
		var graphTicksTemp = barGraphInfo.graphTicks.trim();
		if (graphTicksTemp.length > 0)
		{
			if (!isNaN(graphTicksTemp))
			{
				graphTicks = Number(graphTicksTemp);
			}
		}
	}
	if (barGraphInfo.hasOwnProperty('barPaddingLeft'))
	{
		var barPaddingLeftTemp = barGraphInfo.barPaddingLeft.trim();
		if (barPaddingLeftTemp.length > 0)
		{
			var unitType = "PX";
			if (barPaddingLeftTemp.includes("%"))
			{
				unitType = "%";
			}
			barPaddingLeftTemp = barPaddingLeftTemp.replace(unitType, "");

			if (!isNaN(barPaddingLeftTemp))
			{
				barPaddingLeft = Number(barPaddingLeftTemp);
			}
		}
	}
	if (barGraphInfo.hasOwnProperty('barPaddingRight'))
	{
		var barPaddingRightTemp = barGraphInfo.barPaddingRight.trim();
		if (barPaddingRightTemp.length > 0)
		{
			var unitType = "PX";
			if (barPaddingRightTemp.includes("%"))
			{
				unitType = "%";
			}
			barPaddingRightTemp = barPaddingRightTemp.replace(unitType, "");

			if (!isNaN(barPaddingRightTemp))
			{
				barPaddingRight = Number(barPaddingRightTemp);
			}
		}
	}

	var svgDivDataName = barGraphInfo.svgDivDataName;
	
	var graphDataName = svgDivDataName;
	var graphElement = $('[data-name="'+graphDataName+'"]');
	var svgTagsLength = graphElement.find('svg').length;	
	if(svgTagsLength == 0)
	{
		var svgTemplate =  '<svg> ' + '</svg>';
		graphElement[0].insertAdjacentHTML("beforeend", svgTemplate);
	}	
	var svgDiv = graphElement.find('svg').first()[0];	
	
	svgDiv.style.height = svgHeight;
	svgDiv.style.width = svgWidth;
	svgDiv.innerHTML = "";

	var largestData = 0;
	var dataCount = dataList.length;

	var xaxisPathWidth = (svgWidth - svgGroupMarginLeft - svgGroupMarginRight);
	var yaxisPathHeight = (svgHeight - svgGroupMarginTop - svgGroupMarginBottom);

	
	// graphGroupTemplate
	var graphGroupTemplate = '            <g transform="translate(' + svgGroupMarginLeft + ',' + svgGroupMarginTop + ')" data-name="svgGroup" id ="svgGroup"   fill="green" >  ' + ' <g transform="translate(0,' + (svgHeight - svgGroupMarginTop - svgGroupMarginBottom) + ')" id="xaxispath"  fill="green">             ' + '     <path d="M 0 0 L ' + xaxisPathWidth + ' 0 " stroke="#000" />                               ' + ' </g>                                       '
			+ ' <g transform="translate(0,0)" id="yaxispath"  fill="green">             ' + '      <path d="M 0 0 L 0 ' + yaxisPathHeight + ' " stroke="#000" />                            ' + ' </g>                                   ' + '     </g>  ';
	svgDiv.insertAdjacentHTML("beforeend", graphGroupTemplate);
	
	
	var toolTipsTemplate = '<div class="tooltip1"  data-name="toolTipDiv" ></div>';
	svgDiv.insertAdjacentHTML("beforebegin", toolTipsTemplate);


	var noOfBarsInAGroup = 1;
	var isDataInNegative = 0;
	
	
	// Calculating largest data
	var totalGroupedBarGraphs = 0;
	if(enableSplittedBarGraph==0)
	{
		for (var i = 0; i < dataCount; i++)
		{
			var dataInfoObj = dataList[i];
			var isBarExisted = true;
			var barIndex = 1;
			while (isBarExisted)
			{

				if (barGraphInfo.hasOwnProperty('bar' + barIndex))
				{
					var dataKeyName = barGraphInfo['bar' + barIndex];
					var data = Number(dataInfoObj[dataKeyName]);
	                if(data < 0)
	                {
	                   isDataInNegative = 1;
	                }
	                if(data < 0)
	                {
	                   data = data * -1;
	                }
					if (largestData < data)
					{
						largestData = data;
					}
				}
				else
				{
					break;
				}
				noOfBarsInAGroup = barIndex;
				barIndex++;
			}
			totalGroupedBarGraphs = barIndex;

		}
	}
	else
	{
		for (var i = 0; i < dataCount; i++)
		{
			var dataInfoObj = dataList[i];
			var isBarExisted = true;
			var barIndex = 1;
			var barGraphTotalData = 0;
			while (isBarExisted)
			{

				if (barGraphInfo.hasOwnProperty('bar' + barIndex))
				{
					var dataKeyName = barGraphInfo['bar' + barIndex];
					var data = Number(dataInfoObj[dataKeyName]);
					barGraphTotalData += data;
				}
				else
				{
					break;
				}
				noOfBarsInAGroup = barIndex;
				barIndex++;
			}
			dataInfoObj['barGraphTotalValue'] = barGraphTotalData;
			if (largestData < barGraphTotalData)
			{
				largestData = barGraphTotalData;
			}
			totalGroupedBarGraphs = barIndex;
		}
	}
	totalGroupedBarGraphs = totalGroupedBarGraphs -1;
	for(var i=0;i<totalGroupedBarGraphs;i++)
	{
		var colorCode = randomRGB();
		colors.push(colorCode);
	}
	// Processing X axis Ticks
	for (var i = 0; i < dataCount; i++)
	{
		var dataInfoObj = dataList[i];
		var data = dataInfoObj[xaxisColumnKeyName];
		var groupedBarsWidth = (xaxisPathWidth / dataCount);
		var XCoordinateOfTickGroup = ((i) * groupedBarsWidth) + ((groupedBarsWidth + barPaddingLeft - barPaddingRight) / 2);
		var tickGroupTemplate = '<g id ="group" class="tick" opacity="1" transform="translate(' + XCoordinateOfTickGroup + ',0)"> ' + ' <line stroke="#000" y2="6" x1="0.5" x2="0.5"></line> ' + ' <text fill="#000" y="9" x="0.5" dy="0.71em">' + data + '</text> ' + ' </g>';

		var xaxispathDivElement = svgDiv.getElementById("xaxispath");
		xaxispathDivElement.insertAdjacentHTML("beforeend", tickGroupTemplate);
	}
    if(isDataInNegative==1)
    {
        yaxisPathHeight = (yaxisPathHeight/2);
    }

	// Processing Y axis Ticks
	for (var i = 0; i < graphTicks; i++)
	{
		var yaxisTickCalculatedValue = ((largestData / graphTicks) * (graphTicks - i));
		yaxisTickCalculatedValue = yaxisTickCalculatedValue.toFixed(2);
		var YCoordinateOfTickGroup = ((yaxisPathHeight / graphTicks) * i);
		var yaxisTickCalculatedValueString = yaxisTickCalculatedValue + "";

		//var textXCoordinate = ((15 + ((yaxisTickCalculatedValueString.length - 1) * 8)) * -1) + "";
		var textXCoordinate = -(yaxisTickCalculatedValueString.length*8);
		var tickGroupTemplate = '<g id ="group" class="tick" opacity="1" transform="translate(' + 0 + ', ' + YCoordinateOfTickGroup + ')"> ' + ' <line stroke="#000" x2="-6" y1="0.5" y2="0.5"></line> ' + ' <text fill="#000" x= "' + textXCoordinate + '" y="0.5" dy="0.32em">' + yaxisTickCalculatedValue + '</text> ' + ' </g>';

		var yaxispathDivElement = svgDiv.getElementById("yaxispath");
		yaxispathDivElement.insertAdjacentHTML("beforeend", tickGroupTemplate);
	}

    if(isDataInNegative == 1)
    {
        for(var i=0;i <= graphTicks;i++)
        {
            var yaxisTickCalculatedValue = ((largestData / graphTicks) * (i));
            yaxisTickCalculatedValue = yaxisTickCalculatedValue *-1;
            yaxisTickCalculatedValue = yaxisTickCalculatedValue.toFixed(2);
            var YCoordinateOfTickGroup = ((yaxisPathHeight/graphTicks)* (i));
            YCoordinateOfTickGroup = YCoordinateOfTickGroup + yaxisPathHeight;
            var textXCoordinate = -(yaxisTickCalculatedValueString.length*8);
            var tickGroupTemplate = '<g id ="group" class="tick" opacity="1" transform="translate(' + 0 + ', ' + YCoordinateOfTickGroup + ')"> ' 
                                    + ' <line stroke="#000" x2="-6" y1="0.5" y2="0.5"></line> '
                                    + ' <text fill="#000" x="'+textXCoordinate+'" y="0.5" dy="0.32em">'+yaxisTickCalculatedValue+'</text> '
                                    + ' </g>';
            var yaxispathDivElement = document.getElementById("yaxispath");  	
            yaxispathDivElement.insertAdjacentHTML("beforeend", tickGroupTemplate);
        }
     }
	// Bar calculation

	var groupedBarsXCoordinate = 0;
	var barYCoordinate = 0;
	var barHeight = 0;
	var groupedBarsWidth = 0;

	var yAxisSingleTickHeightInPixel = (yaxisPathHeight / graphTicks);
	var yAxisSingleTickHeightInData = (largestData / graphTicks);

	// Processing Rectangles
	if(enableSplittedBarGraph ==0)
	{
		for (var i = 0; i < dataCount; i++)
		{
	
			var dataInfoObj = dataList[i];
			var isBarExisted = true;
			var barIndex = 1;
			var colorIndex = 0;
			while (isBarExisted)
			{
				if (barGraphInfo.hasOwnProperty('bar' + barIndex))
				{
					var dataKeyName = barGraphInfo['bar' + barIndex];
					var data = Number(dataInfoObj[dataKeyName]);				
	
					// Formula to calculate bar height
					// 5px = 250Data so 1 Data = (5/250) px 100 Data = 5/250 *
					// 100
					// px
					if(data > 0 )
					{
						barHeight = ((yAxisSingleTickHeightInPixel / yAxisSingleTickHeightInData) * data);
						if(!barHeight)
						{
							barHeight = 0;
						}
						barYCoordinate = (yaxisPathHeight - barHeight);
					}
					else
					{
						barHeight = ((yAxisSingleTickHeightInPixel / yAxisSingleTickHeightInData) * (data * -1));
						if(!barHeight)
						{
							barHeight = 0;
						}
						barYCoordinate = (yaxisPathHeight);
					}
					
					
					groupedBarsWidth = (xaxisPathWidth / dataCount);
					groupedBarsWidth = groupedBarsWidth - barPaddingLeft;
					groupedBarsWidth = groupedBarsWidth - barPaddingRight;
					var singleBarWidth = (groupedBarsWidth / noOfBarsInAGroup);
					groupedBarsXCoordinate = ((xaxisPathWidth / dataCount) * i);
					groupedBarsXCoordinate = groupedBarsXCoordinate + barPaddingLeft;
					var singBarXCoordinate = (groupedBarsXCoordinate + (singleBarWidth * (barIndex - 1)));
					var colorListLength = colors.length;
					if (colorListLength == colorIndex)
					{
						colorIndex = 0;
					}
					var barColor = colors[colorIndex];
					colorIndex++;
	
					var XCoordinate = (groupedBarsWidth * i);
					var barTemplate = '<rect  id ="barIndex'+i+'" fill = ' + barColor + ' x="' + singBarXCoordinate + '" width="' + singleBarWidth + '" y="' + barYCoordinate + '" height="' + barHeight + '" onmousemove="barMouseMoveFuntion(this)"></rect>';
	
					var svgGroupGTagElement = svgDiv.getElementById("svgGroup");
					svgGroupGTagElement.insertAdjacentHTML("beforeend", barTemplate);
					var element = svgDiv.getElementById("barIndex"+i +"");	
					var barInfoObj = {};
					barInfoObj['displayText'] = dataInfoObj[xaxisColumnKeyName];
					barInfoObj['dataText'] = data;	
					element.setAttribute("data-object", JSON.stringify(barInfoObj));				
				}
				else
				{
					break;
				}
				barIndex++;
			}
		}
	}
	else
	{
		for (var i = 0; i < dataCount; i++)
		{
	
			var dataInfoObj = dataList[i];
			var barGraphTotalValue = dataInfoObj.barGraphTotalValue;
			var isBarExisted = true;
			var barIndex = 1;
			var colorIndex = 0;			
			barHeight = ((yAxisSingleTickHeightInPixel / yAxisSingleTickHeightInData) * barGraphTotalValue);
			if(!barHeight)
			{
				barHeight = 0;
			}	
			var previousSplittedBarGraphYCoordinate = yaxisPathHeight - barHeight;
			while (isBarExisted)
			{				
				if (barGraphInfo.hasOwnProperty('bar' + barIndex))
				{
					var dataKeyName = barGraphInfo['bar' + barIndex];
					var data = Number(dataInfoObj[dataKeyName]);				
	
					// Formula to calculate bar height
					// 5px = 250Data so 1 Data = (5/250) px 100 Data = 5/250 *
					// 100
					// px
							
					
					groupedBarsWidth = (xaxisPathWidth / dataCount);
					groupedBarsWidth = groupedBarsWidth - barPaddingLeft;
					groupedBarsWidth = groupedBarsWidth - barPaddingRight;
					
					var singleBarWidth = groupedBarsWidth;
					
					groupedBarsXCoordinate = ((xaxisPathWidth / dataCount) * i);
					groupedBarsXCoordinate = groupedBarsXCoordinate + barPaddingLeft;
					var singBarXCoordinate = groupedBarsXCoordinate;
					
					
					var colorListLength = colors.length;
					if (colorListLength == colorIndex)
					{
						colorIndex = 0;
					}
					var barColor = colors[colorIndex];
					colorIndex++;
										
					// 1000Data = 400px
					barYCoordinate = previousSplittedBarGraphYCoordinate;					
					var splittedGraphHeight = (barHeight/barGraphTotalValue) * data;						
					previousSplittedBarGraphYCoordinate = previousSplittedBarGraphYCoordinate + splittedGraphHeight;
														
					var XCoordinate = (groupedBarsWidth * i);
					var barTemplate = '<rect  id ="barIndex_' + i + "_" + barIndex+'" fill = ' + barColor + ' x="' + singBarXCoordinate + '" width="' + singleBarWidth + '" y="' + barYCoordinate + '" height="' + splittedGraphHeight + '" onmousemove="barMouseMoveFuntion(this)"></rect>';
	
					var svgGroupGTagElement = svgDiv.getElementById("svgGroup");
					svgGroupGTagElement.insertAdjacentHTML("beforeend", barTemplate);
					var element = svgDiv.getElementById("barIndex_" + i + "_" + barIndex +"");	
					var barInfoObj = {};
					barInfoObj['displayText'] = dataInfoObj[xaxisColumnKeyName];
					barInfoObj['dataText'] = data;	
					element.setAttribute("data-object", JSON.stringify(barInfoObj));				
				}
				else
				{
					break;
				}
				barIndex++;
			}
		}

	}
    if(isDataInNegative == 1)
    {
        var barTemplate = ' <g transform="translate(0,'+((svgHeight - svgGroupMarginTop - svgGroupMarginBottom)/2)+')"   fill="blue">' +
        '     <path d="M 0 0 L '+xaxisPathWidth+' 0  " stroke="#000" />                               ' +             
        ' </g>';								                
		var svgGroupGTagElement = svgDiv.getElementById("svgGroup");  			
        svgGroupGTagElement.insertAdjacentHTML("beforeend", barTemplate);
    }    
    var parentElement = svgDiv.parentNode;
    $('[data-name="barGraphLedgendDiv"]').remove();
    var legendsDiv = 
    	  '<div style="width:'+svgWidth+'px;background-color:  floralwhite;" data-name="barGraphLedgendDiv"> '
    	+ '		<div style="margin-left:  20px;margin-right:  10px"> '                    
    	+ '			$$LEGEND_TEMPLATES$$ '
    	+ '     </div> ' 
    	+ '</div>';
       
    var legendTemplate = 
    	'<span class="mr-2"> '
    	+ '<i class="fas fa-circle" style="color:  $$CIRCLE_COLOR$$;"></i> $$LEGEND_TEXT$$ '
    	+ '</span> ';
    var legendElements = "";
    for(var i=0;i<totalGroupedBarGraphs;i++)
	{
    	var colorCode = colors[i];
    	var legendElement = legendTemplate;
    	var legendText = barGraphInfo['legendOfBar' + (i+1)];		
    	legendElement = legendElement.replace("$$CIRCLE_COLOR$$", colorCode);
    	legendElement = legendElement.replace("$$LEGEND_TEXT$$", legendText);
    	legendElements += legendElement;
	}
    
    legendsDiv = legendsDiv.replace("$$LEGEND_TEMPLATES$$", legendElements);
    parentElement.insertAdjacentHTML("beforeend", legendsDiv);
}
function scatterPlotGraph(scatterPlotGraphInfo)
{

	var dataList = scatterPlotGraphInfo.dataList;
	
	var svgWidth = 800;
	var svgHeight = 400;
	var svgGroupMarginLeft = 50;
	var svgGroupMarginTop = 5;
	var svgGroupMarginBottom = 80;
	var svgGroupMarginRight = 80;
	var graphTicks = 10;

	var xaxisColumnDataName = scatterPlotGraphInfo.xaxisColumnDataName;
	var yaxisColumnDataName = scatterPlotGraphInfo.yaxisColumnDataName;
	
	if (scatterPlotGraphInfo.hasOwnProperty('graphTicks'))
	{
		var graphTicksTemp = scatterPlotGraphInfo.graphTicks.trim();
		if (graphTicksTemp.length > 0)
		{
			if (!isNaN(graphTicksTemp))
			{
				graphTicks = Number(graphTicksTemp);
			}
		}
	}
	
	var commonPropertiesObj = {};
	commonPropertiesObj['svgWidth'] =svgWidth; 
	commonPropertiesObj['svgHeight'] =svgHeight; 
	commonPropertiesObj['svgGroupMarginLeft'] =svgGroupMarginLeft; 
	commonPropertiesObj['svgGroupMarginTop'] =svgGroupMarginTop; 
	commonPropertiesObj['svgGroupMarginBottom'] =svgGroupMarginBottom; 
	commonPropertiesObj['svgGroupMarginRight'] =svgGroupMarginRight;
	
	commonPropertiesObj = graphsCommonProperties(commonPropertiesObj, scatterPlotGraphInfo);
	
	svgWidth = commonPropertiesObj['svgWidth']
	svgHeight = commonPropertiesObj['svgHeight'];
	svgGroupMarginLeft = commonPropertiesObj['svgGroupMarginLeft'];
	svgGroupMarginTop = commonPropertiesObj['svgGroupMarginTop'];
	svgGroupMarginBottom = commonPropertiesObj['svgGroupMarginBottom'];
	svgGroupMarginRight = commonPropertiesObj['svgGroupMarginRight'];	
	


	var svgDivDataName = scatterPlotGraphInfo.svgDivDataName;
	
	var graphDataName = svgDivDataName;
	var graphElement = $('[data-name="'+graphDataName+'"]');
	var svgTagsLength = graphElement.find('svg').length;	
	if(svgTagsLength == 0)
	{
		var svgTemplate =  '<svg> ' + '</svg>';
		graphElement[0].insertAdjacentHTML("beforeend", svgTemplate);
	}	
	var svgDiv = graphElement.find('svg').first()[0];
	
	svgDiv.style.height = svgHeight;
	svgDiv.style.width = svgWidth;
	svgDiv.innerHTML = "";

	var largestData = 0;
	var dataCount = dataList.length;

	var xaxisPathWidth = (svgWidth - svgGroupMarginLeft - svgGroupMarginRight);
	var yaxisPathHeight = (svgHeight - svgGroupMarginTop - svgGroupMarginBottom);

	// graphGroupTemplate
	var graphGroupTemplate = '            <g transform="translate(' + svgGroupMarginLeft + ',' + svgGroupMarginTop + ')" data-name="svgGroup" id ="svgGroup"   fill="green" >  ' + ' <g transform="translate(0,' + (svgHeight - svgGroupMarginTop - svgGroupMarginBottom) + ')" id="xaxispath"  fill="green">             ' + '     <path d="M 0 0 L ' + xaxisPathWidth + ' 0 " stroke="#000" />                               ' + ' </g>                                       '
			+ ' <g transform="translate(0,0)" id="yaxispath"  fill="green">             ' + '      <path d="M 0 0 L 0 ' + yaxisPathHeight + ' " stroke="#000" />                            ' + ' </g>                                   ' + '     </g>  ';
	svgDiv.insertAdjacentHTML("beforeend", graphGroupTemplate);

	// Calculating largest data
	for (var i = 0; i < dataCount; i++)
	{
		var dataInfoObj = dataList[i];
		
		var data = Number(dataInfoObj[yaxisColumnDataName]);
		if (largestData < data)
		{
			largestData = data;
		}
	}

	
	
    // Processing X axis Ticks
    for(var i=0;i<dataCount;i++)
    {
		var dataInfoObj = dataList[i];
		var data = dataInfoObj[xaxisColumnDataName];
        var barWidth = (xaxisPathWidth/dataCount);
    	var XCoordinateOfTickGroup = ((i) * barWidth);
        var tickGroupTemplate = '<g id ="group" class="tick" opacity="1" transform="translate(' + XCoordinateOfTickGroup + ',0)"> ' 
									+ ' <line stroke="#000" y2="6" x1="0.5" x2="0.5"></line> '
									+ ' <text fill="#000" y="9" x="0.5" dy="0.71em">'+data+'</text> '
								+ ' </g>';
						                
		
		var xaxispathDivElement = svgDiv.getElementById("xaxispath");  						
        xaxispathDivElement.insertAdjacentHTML("beforeend", tickGroupTemplate);
    }

	// Processing Y axis Ticks
	for (var i = 0; i < graphTicks; i++)
	{
		var yaxisTickCalculatedValue = ((largestData / graphTicks) * (graphTicks - i));
		yaxisTickCalculatedValue = yaxisTickCalculatedValue.toFixed(2);
		var YCoordinateOfTickGroup = ((yaxisPathHeight / graphTicks) * i);
		var yaxisTickCalculatedValueString = yaxisTickCalculatedValue + "";

		//var textXCoordinate = ((15 + ((yaxisTickCalculatedValueString.length - 1) * 8)) * -1) + "";
		var textXCoordinate = -(yaxisTickCalculatedValueString.length*8);
		var tickGroupTemplate = '<g id ="group" class="tick" opacity="1" transform="translate(' + 0 + ', ' + YCoordinateOfTickGroup + ')"> ' + ' <line stroke="#000" x2="-6" y1="0.5" y2="0.5"></line> ' + ' <text fill="#000" x= "' + textXCoordinate + '" y="0.5" dy="0.32em">' + yaxisTickCalculatedValue + '</text> ' + ' </g>';

		var yaxispathDivElement = svgDiv.getElementById("yaxispath");
		yaxispathDivElement.insertAdjacentHTML("beforeend", tickGroupTemplate);
	}

	// Processing Circles

	var yAxisEachDivisionHeightInPixel = (yaxisPathHeight / graphTicks);
	var yAxisEachDivisionHeightInData = (largestData / graphTicks);

	var linePath = "";
	for (var i = 0; i < dataCount; i++)
	{

		// Formula to calculate bar height
		// 5px = 250Data so 1 Data = (5/250) px 100 Data = 5/250 * 100 px
		var dataInfoObj = dataList[i];
		var data = Number(dataInfoObj[yaxisColumnDataName]);
		var heightInPixelBasedOnData = ((yAxisEachDivisionHeightInPixel / yAxisEachDivisionHeightInData) * data);
		var cy = (yaxisPathHeight - heightInPixelBasedOnData);
		var barWidth = (xaxisPathWidth / dataCount);
		var cx = (barWidth * i);
		var barTemplate = '<circle r="4" id="pointIndex'+i+'" cursor="pointer" fill="rgb(88, 173, 229)" cx="' + cx + '" cy="' + cy + '" onmousemove="scatterPlotMouseMoveFuntion(this)"></circle>';

		if (i == 0)
		{
			linePath += "M" + cx + "," + cy;
		}
		else
		{
			linePath += "L" + cx + "," + cy;
		}
		var svgGroupGTagElement = svgDiv.getElementById("svgGroup");
		svgGroupGTagElement.insertAdjacentHTML("beforeend", barTemplate);
		
		var element = svgDiv.getElementById("pointIndex"+i +"");	
		var pointInfoObj = {};
		pointInfoObj['displayText'] = dataInfoObj[xaxisColumnDataName];		
		pointInfoObj['dataText'] = data;	
		element.setAttribute("data-object", JSON.stringify(pointInfoObj));
		
	}
	var linePathTemplate = '<path stroke="#41b6f1" stroke-width="2" fill="none" d="' + linePath + '"></path>';
	var svgGroupGTagElement = svgDiv.getElementById("svgGroup");
	svgGroupGTagElement.insertAdjacentHTML("beforeend", linePathTemplate);
}

function pieChart(pieChartGraphInfo)
{        
    var arc;
    var colorArr = ["#468966", "#008B8B", "#00FFFF"];

    var pieDataList = pieChartGraphInfo.dataList;
    
        
// pieDataList = [
// {"ei_expenseAmount":100, "ei_expenseDate":"jan 1"},
// {"ei_expenseAmount":200, "ei_expenseDate":"jan 2"},
// {"ei_expenseAmount":300, "ei_expenseDate":"jan 3"},
// {"ei_expenseAmount":400, "ei_expenseDate":"jan 4"},
// ];
    for(var i=0;i<pieDataList.length;i++)
	{
		var colorCode = randomRGB();
		colorArr.push(colorCode);
	}
    
    var sectorAngleArr = [];
    var total = 0;
    var startAngle = 0;
    var endAngle = 0;
    var x1,x2,y1,y2 = 0;
	
	    
	var svgWidth = 400;
	var svgHeight = 400;
	var svgGroupMarginLeft = 10;
	var svgGroupMarginTop = 20;
	var	svgGroupMarginRight = 30;
	var	svgGroupMarginBottom = 40;
	var radius = 100;			
	var innerPieRadius = 0;			
	
	var commonPropertiesObj = {};
	commonPropertiesObj['svgWidth'] =svgWidth; 
	commonPropertiesObj['svgHeight'] =svgHeight; 
	commonPropertiesObj['svgGroupMarginLeft'] =svgGroupMarginLeft; 
	commonPropertiesObj['svgGroupMarginTop'] =svgGroupMarginTop; 
	commonPropertiesObj['svgGroupMarginBottom'] =svgGroupMarginBottom; 
	commonPropertiesObj['svgGroupMarginRight'] =svgGroupMarginRight;
	
	commonPropertiesObj = graphsCommonProperties(commonPropertiesObj, pieChartGraphInfo);
	
	svgWidth = commonPropertiesObj['svgWidth']
	svgHeight = commonPropertiesObj['svgHeight'];
	svgGroupMarginLeft = commonPropertiesObj['svgGroupMarginLeft'];
	svgGroupMarginTop = commonPropertiesObj['svgGroupMarginTop'];
	svgGroupMarginBottom = commonPropertiesObj['svgGroupMarginBottom'];
	svgGroupMarginRight = commonPropertiesObj['svgGroupMarginRight'];	
	
	if (pieChartGraphInfo.hasOwnProperty('pieRadius'))
	{
		var pieRadiusTemp = pieChartGraphInfo.pieRadius.trim();
		if (pieRadiusTemp.length > 0)
		{
			if (!isNaN(pieRadiusTemp))
			{
				radius = Number(pieRadiusTemp);
			}
		}
	}
	if (pieChartGraphInfo.hasOwnProperty('innerPieRadius'))
	{
		var innerPieRadiusTemp = pieChartGraphInfo.innerPieRadius.trim();
		if (innerPieRadiusTemp.length > 0)
		{
			if (!isNaN(innerPieRadiusTemp))
			{
				innerPieRadius = Number(innerPieRadiusTemp);
			}
		}
	}
	
	var svgDivDataName = pieChartGraphInfo.svgDivDataName;
	
	var graphDataName = svgDivDataName;
	var graphElement = $('[data-name="'+graphDataName+'"]');
	var svgTagsLength = graphElement.find('svg').length;	
	if(svgTagsLength == 0)
	{
		var svgTemplate =  '<svg> ' + '</svg>';
		graphElement[0].insertAdjacentHTML("beforeend", svgTemplate);
	}	
	var svgDiv = graphElement.find('svg').first()[0];
	
	svgDiv.style.height = svgHeight;
	svgDiv.style.width = svgWidth;
	svgDiv.innerHTML = "";
		
	var svgWidthAfterCuttings = (svgWidth - svgGroupMarginLeft - svgGroupMarginRight);            
	var svgHeightAfterCuttings = (svgHeight - svgGroupMarginTop - svgGroupMarginBottom); 

	var centerX = svgGroupMarginLeft + svgWidthAfterCuttings/2;
	var centerY = svgGroupMarginTop + svgHeightAfterCuttings/2;
	

	// graphGroupTemplate
	var graphGroupTemplate =
		'            <g transform="translate('+svgGroupMarginLeft+','+svgGroupMarginTop+')" data-name="svgGroup" id ="svgGroup"   fill="green" >  ' +
		'     </g>  ';                		                    
		svgDiv.insertAdjacentHTML("beforeend", graphGroupTemplate);
	init();

	function init()
	{

		// CALCULATE THE TOTAL
		for (var k = 0; k < pieDataList.length; k++)
		{
			var pieDataObj = pieDataList[k];
			var dataColumnName = pieChartGraphInfo['pieChartSectorColumn'];
			total += Number(pieDataObj[dataColumnName]);
		}
		// CALCULATE THE ANGLES THAT EACH SECTOR SWIPES AND STORE IN AN ARRAY
		for (var i = 0; i < pieDataList.length; i++)
		{
			var pieDataObj = pieDataList[i];
			var dataColumnName = pieChartGraphInfo['pieChartSectorColumn'];
			var angle = 360 * Number(pieDataObj[dataColumnName]) / total;
			sectorAngleArr.push(angle);
		}
		drawArcs();
	}               

	// https://jbkflex.wordpress.com/2011/07/28/creating-a-svg-pie-chart-html5/
	// this is important link which gives us full information about code
    function drawArcs()
	{
		var colorIndex = 0;
        for(var i=0; i <sectorAngleArr.length; i++)
		{
            startAngle = endAngle;
            endAngle = startAngle + sectorAngleArr[i];
			
											   
			// x = rx + radius * cos(theta) and y = ry + radius * sin(theta)
			
				x1 = centerX + radius*Math.cos(Math.PI*startAngle/180);
			x1 = fixToFiveDecimals(x1);
            y1 = centerY + radius*Math.sin(Math.PI*startAngle/180);
			y1 = fixToFiveDecimals(y1);
            x2 = centerX + radius*Math.cos(Math.PI*endAngle/180);
			x2 = fixToFiveDecimals(x2);
            y2 = centerY + radius*Math.sin(Math.PI*endAngle/180);  												   
			y2 = fixToFiveDecimals(y2);
											   
			// You need to check the sector angle and set the large_arc_flag to
			// 1 if the angle is > 180.
		
			var large_arc_flag=0;
			if (sectorAngleArr[i]>180)
			{
				large_arc_flag = 1;
			}
											   
            var d = "M"+centerX+","+centerY+"  L" + x1 + "," + y1 + "  A " + radius + ", " + radius + " 0 " + large_arc_flag + ",1 " + x2 + "," + y2 + " z"; // 1
																																								// means
																																								// clockwise
											   				
			var colorListLength  = colorArr.length;
			if(colorListLength == colorIndex)
			{
				colorIndex = 0;
			}								
			var colorCode = colorArr[colorIndex];			
		
			if((i+1) == sectorAngleArr.length)
			{		
				if(colorIndex==0)
				{
					if(colorListLength == 2)
					{
						colorCode = "#F08080";
					}	
					if(colorListLength > 2)
					{
						colorCode = colorArr[1];;
					}				
				}								
			}
			colorIndex++;
				
			var arcTemplate = '<g cursor="pointer" class="arc"> ' + 
               ' <path d="'+d+'" fill="'+colorCode+'" onmousemove="pieChartMouseMoveFuntion(this)" id="segmentIndex'+i+'"></path> ' + 
           	'</g>';
            
			var svgGroupGTagElement = svgDiv.getElementById("svgGroup");  			
            svgGroupGTagElement.insertAdjacentHTML("beforeend", arcTemplate);            
			var circleElement ='<circle  cx="'+centerX+'" cy="'+centerY+'" r="'+innerPieRadius+'"  fill="white"  />';
			var svgGroupGTagElement = svgDiv.getElementById("svgGroup");  			
            svgGroupGTagElement.insertAdjacentHTML("beforeend", circleElement);
            
			var element = svgDiv.getElementById("segmentIndex"+i +"");			
			var dataColumnName = pieChartGraphInfo	['pieChartSectorColumn'];
			var sectorDataInfoObj = pieDataList[i];
			var sectorInfoObj = {};
			sectorInfoObj['displayText'] = sectorDataInfoObj[dataColumnName];			
			element.setAttribute("data-object", JSON.stringify(sectorInfoObj));            
        }              
    }

    var parentElement = svgDiv.parentNode;
    $('[data-name="pieChartLedgendDiv"]').remove();
    var legendsDiv = 
    	  '<div style="width:'+svgWidth+'px;background-color:  floralwhite;" data-name="pieChartLedgendDiv"> '
    	+ '		<div style="margin-left:  20px;margin-right:  10px"> '                    
    	+ '			$$LEGEND_TEMPLATES$$ '
    	+ '     </div> ' 
    	+ '</div>';
       
    var legendTemplate = 
    	'<span class="mr-2"> '
    	+ '<i class="fas fa-circle" style="color:  $$CIRCLE_COLOR$$;"></i> $$LEGEND_TEXT$$ '
    	+ '</span> ';
    var legendElements = "";
    
    for(var i=0;i<pieDataList.length;i++)
	{
    	var colorCode = colorArr[i];
    	var legendElement = legendTemplate;
    	var pieChartLegendDescriptionColumnDataName = pieChartGraphInfo['pieChartLegendDescriptionColumn'];
    	var pieDataInfoObj = pieDataList[i];    	
    	var legendText = pieDataInfoObj[pieChartLegendDescriptionColumnDataName];		
    	legendElement = legendElement.replace("$$CIRCLE_COLOR$$", colorCode);
    	legendElement = legendElement.replace("$$LEGEND_TEXT$$", legendText);
    	legendElements += legendElement;
	}
    
    legendsDiv = legendsDiv.replace("$$LEGEND_TEMPLATES$$", legendElements);
    parentElement.insertAdjacentHTML("beforeend", legendsDiv);
    

}           


function fixToFiveDecimals(number)
{
	var finalValue = number;
	var numberString = number + "";
	if(numberString.includes("."))
	{
		var res = numberString.split(".");
		var digitsAfterPoint = res[1];
		if(digitsAfterPoint.length>5)
		{
			digitsAfterPoint = digitsAfterPoint.substring(0, 5);
		}
		finalValue = res[0] + "." + digitsAfterPoint;
	}
	return parseFloat(finalValue);		
}
function graphsCommonProperties(commonPropertiesObj, graphInfo)
{
	
	
	if (graphInfo.hasOwnProperty('svgWidth'))
	{
		var svgWidthTemp = graphInfo.svgWidth.trim();
		if (svgWidthTemp.length > 0)
		{
			var unitType = "PX";
			if (svgWidthTemp.includes("%"))
			{
				unitType = "%";
			}
			svgWidthTemp = svgWidthTemp.replace(unitType, "");

			if (!isNaN(svgWidthTemp))
			{
				svgWidth = Number(svgWidthTemp);
				commonPropertiesObj['svgWidth'] = svgWidth;
			}
		}
	}
	if (graphInfo.hasOwnProperty('svgHeight'))
	{
		var svgHeightTemp = graphInfo.svgHeight.trim();
		if (svgHeightTemp.length > 0)
		{
			var unitType = "PX";
			if (svgHeightTemp.includes("%"))
			{
				unitType = "%";
			}
			svgHeightTemp = svgHeightTemp.replace(unitType, "");

			if (!isNaN(svgHeightTemp))
			{
				svgHeight = Number(svgHeightTemp);
				commonPropertiesObj['svgHeight'] = svgHeight;
			}
		}
	}
	if (graphInfo.hasOwnProperty('svgGroupMarginLeft'))
	{
		var svgGroupMarginLeftTemp = graphInfo.svgGroupMarginLeft.trim();
		if (svgGroupMarginLeftTemp.length > 0)
		{
			var unitType = "PX";
			if (svgGroupMarginLeftTemp.includes("%"))
			{
				unitType = "%";
			}
			svgGroupMarginLeftTemp = svgGroupMarginLeftTemp.replace(unitType, "");

			if (!isNaN(svgGroupMarginLeftTemp))
			{
				svgGroupMarginLeft = Number(svgGroupMarginLeftTemp);
				commonPropertiesObj['svgGroupMarginLeft'] = svgGroupMarginLeft;
			}
		}
	}
	if (graphInfo.hasOwnProperty('svgGroupMarginTop'))
	{
		var svgGroupMarginTopTemp = graphInfo.svgGroupMarginTop.trim();
		if (svgGroupMarginTopTemp.length > 0)
		{
			var unitType = "PX";
			if (svgGroupMarginTopTemp.includes("%"))
			{
				unitType = "%";
			}
			svgGroupMarginTopTemp = svgGroupMarginTopTemp.replace(unitType, "");

			if (!isNaN(svgGroupMarginTopTemp))
			{
				svgGroupMarginTop = Number(svgGroupMarginTopTemp);
				commonPropertiesObj['svgGroupMarginTop'] = svgGroupMarginTop;
			}
		}
	}
	if (graphInfo.hasOwnProperty('svgGroupMarginBottom'))
	{
		var svgGroupMarginBottomTemp = graphInfo.svgGroupMarginBottom.trim();
		if (svgGroupMarginBottomTemp.length > 0)
		{
			var unitType = "PX";
			if (svgGroupMarginBottomTemp.includes("%"))
			{
				unitType = "%";
			}
			svgGroupMarginBottomTemp = svgGroupMarginBottomTemp.replace(unitType, "");

			if (!isNaN(svgGroupMarginBottomTemp))
			{
				svgGroupMarginBottom = Number(svgGroupMarginBottomTemp);
				commonPropertiesObj['svgGroupMarginBottom'] = svgGroupMarginBottom;
			}
		}
	}
	if (graphInfo.hasOwnProperty('svgGroupMarginRight'))
	{
		var svgGroupMarginRightTemp = graphInfo.svgGroupMarginRight.trim();
		if (svgGroupMarginRightTemp.length > 0)
		{
			var unitType = "PX";
			if (svgGroupMarginRightTemp.includes("%"))
			{
				unitType = "%";
			}
			svgGroupMarginRightTemp = svgGroupMarginRightTemp.replace(unitType, "");

			if (!isNaN(svgGroupMarginRightTemp))
			{
				svgGroupMarginRight = Number(svgGroupMarginRightTemp);
				commonPropertiesObj['svgGroupMarginRight'] = svgGroupMarginRight;
			}
		}
	}
	return commonPropertiesObj;

} 


function funnelChart(funnelChartInfo)
{
	var funnelChartDataColumn = funnelChartInfo.funnelChartDataColumn;
	var funnelChartDisplayTextColumn = funnelChartInfo.funnelChartDisplayTextColumn;
    var dataList =   funnelChartInfo.dataList;    
// dataList = [
// {"ei_expenseAmount":100, "ei_expenseDate":"jan 1", "ei_referenceNo" : "Funnel
// Sector 1"},
// {"ei_expenseAmount":200, "ei_expenseDate":"jan 2", "ei_referenceNo" : "Funnel
// Sector 2"},
// {"ei_expenseAmount":300, "ei_expenseDate":"jan 3", "ei_referenceNo" : "Funnel
// Sector 3"},
// {"ei_expenseAmount":400, "ei_expenseDate":"jan 4", "ei_referenceNo" : "Funnel
// Sector 4"},
// ];
	dataList.sort(function(a, b)
				  {
					return b[funnelChartDataColumn]-a[funnelChartDataColumn]
				  }
				 );
	var colors = ["#BDB76B", "#556B2F", "#8FBC8F", "#2F4F4F", "#228B22"];
    for(var i=0;i<dataList.length;i++)
	{
		var colorCode = randomRGB();
		colors.push(colorCode);
	}
    var svgWidth = 700;
    var svgHeight = 400;
    var svgGroupMarginLeft = 0;
    var svgGroupMarginTop = 0;
    var svgGroupMarginBottom = 0;
    var svgGroupMarginRight = 0;
	var funnelTipWidth = 100;
	var funnelTipHeight = 50;

	var commonPropertiesObj = {};
	commonPropertiesObj['svgWidth'] =svgWidth; 
	commonPropertiesObj['svgHeight'] =svgHeight; 
	commonPropertiesObj['svgGroupMarginLeft'] =svgGroupMarginLeft; 
	commonPropertiesObj['svgGroupMarginTop'] =svgGroupMarginTop; 
	commonPropertiesObj['svgGroupMarginBottom'] =svgGroupMarginBottom; 
	commonPropertiesObj['svgGroupMarginRight'] =svgGroupMarginRight;
	
	commonPropertiesObj = graphsCommonProperties(commonPropertiesObj, funnelChartInfo);
	
	svgWidth = commonPropertiesObj['svgWidth']
	svgHeight = commonPropertiesObj['svgHeight'];
	svgGroupMarginLeft = commonPropertiesObj['svgGroupMarginLeft'];
	svgGroupMarginTop = commonPropertiesObj['svgGroupMarginTop'];
	svgGroupMarginBottom = commonPropertiesObj['svgGroupMarginBottom'];
	svgGroupMarginRight = commonPropertiesObj['svgGroupMarginRight'];	
	
	if (funnelChartInfo.hasOwnProperty('funnelTipWidth'))
	{
		var funnelTipWidthTemp = funnelChartInfo.funnelTipWidth.trim();
		if (funnelTipWidthTemp.length > 0)
		{
			var unitType = "PX";
			if (funnelTipWidthTemp.includes("%"))
			{
				unitType = "%";
			}
			funnelTipWidthTemp = funnelTipWidthTemp.replace(unitType, "");

			if (!isNaN(funnelTipWidthTemp))
			{
				funnelTipWidth = Number(funnelTipWidthTemp);
				commonPropertiesObj['funnelTipWidth'] = funnelTipWidth;
			}
		}
	}
	if (funnelChartInfo.hasOwnProperty('funnelTipHeight'))
	{
		var funnelTipHeightTemp = funnelChartInfo.funnelTipHeight.trim();
		if (funnelTipHeightTemp.length > 0)
		{
			var unitType = "PX";
			if (funnelTipHeightTemp.includes("%"))
			{
				unitType = "%";
			}
			funnelTipHeightTemp = funnelTipHeightTemp.replace(unitType, "");

			if (!isNaN(funnelTipHeightTemp))
			{
				funnelTipHeight = Number(funnelTipHeightTemp);
				commonPropertiesObj['funnelTipHeight'] = funnelTipHeight;
			}
		}
	}

	
	var svgDivDataName = funnelChartInfo.svgDivDataName;
	var graphDataName = svgDivDataName;
	var graphElement = $('[data-name="'+graphDataName+'"]');
	var svgTagsLength = graphElement.find('svg').length;	
	if(svgTagsLength == 0)
	{
		var svgTemplate =  '<svg> ' + '</svg>';
		graphElement[0].insertAdjacentHTML("beforeend", svgTemplate);
	}	
	var svgDiv = graphElement.find('svg').first()[0];
	svgDiv.style.height = svgHeight;
	svgDiv.style.width = svgWidth;
	svgDiv.innerHTML = "";

        
    var dataCount = dataList.length;            
    var funnelWidth = (svgWidth - svgGroupMarginLeft - svgGroupMarginRight);            
    var funnelHeight = (svgHeight - svgGroupMarginTop - svgGroupMarginBottom);   

    // graphGroupTemplate
    var graphGroupTemplate =
        ' <g transform="translate('+svgGroupMarginLeft+','+svgGroupMarginTop+')" data-name="svgGroup" id ="svgGroup"   fill="green" >  ' +                
        '     </g>  ';                		                    
        svgDiv.insertAdjacentHTML("beforeend", graphGroupTemplate);

	var segementGap = ((funnelWidth- funnelTipWidth)/ dataCount)/2;
	var segementHeight = (funnelHeight-funnelTipHeight)/ dataCount;
	var colorIndex = 0;
	var colorListLength = colors.length;
	for(var i=0;i<dataCount;i++)
	{
		var MX = (i*segementGap);
		var MY = (i*segementHeight);				
		// Line 1
		var L1X = ((i+1)*segementGap);
		var L1Y = ((i+1)*segementHeight);
		// Line 2
		var L2X = (funnelWidth - ((i+1)*segementGap));
		var L2Y = ((i+1)*segementHeight);
		// Line 3
		var L3X = (funnelWidth - (i*segementGap));
		var L3Y = (i*segementHeight);		
		
		var funnelTipDownLine = "";
		var funnelTipUpLine = "";
		if(i==(dataCount-1))
	   	{
			funnelTipDownLine = "L" + L1X + "," + (L1Y+funnelTipHeight) ;
			funnelTipUpLine = "L" +  L2X + "," + (L2Y);	
			L2Y = L2Y + funnelTipHeight;
	   	}
		if(colorListLength == colorIndex)
		{
			colorIndex = 0;
		}							
		
		var segmentColor = colors[colorIndex];
		colorIndex++;
		var dataInfoObj = dataList[i];
		var data = dataInfoObj[funnelChartDisplayTextColumn] + "  ("+dataInfoObj[funnelChartDataColumn]+")";
		// Text Coordinates
		var textXCoordinate = 0;
		var textYCoordinate = 0;
		textXCoordinate = funnelWidth/2;
		textYCoordinate = (i*segementHeight) + (segementHeight / 2);
		
		var segmentTemplate = '<g class="funnel-group"> ' +
       ' <path id="funnelChartSectionIndex'+i+'" d="M'+MX+','+MY+'L'+L1X+','+L1Y+funnelTipDownLine+'L'+L2X+','+L2Y+ funnelTipUpLine+'L'+L3X+','+L3Y+'" fill="'+segmentColor+'" onmousemove="funnelChartMouseMoveFuntion(this)"></path> ' +
       ' <text fill= "#171d61" text-anchor="middle" y="'+textYCoordinate+'" x="'+textXCoordinate+'">'+data+'</text> ' +
       ' </g>';
		var svgGroupGTagElement = svgDiv.getElementById("svgGroup");  
		svgGroupGTagElement.insertAdjacentHTML("beforeend", segmentTemplate);
		
		var element = svgDiv.getElementById("funnelChartSectionIndex"+i +"");	
		var funnelChartSectionInfoObj = {};
		funnelChartSectionInfoObj['displayText'] = dataInfoObj[funnelChartDisplayTextColumn];
		funnelChartSectionInfoObj['dataText'] = dataInfoObj[funnelChartDataColumn];	
		element.setAttribute("data-object", JSON.stringify(funnelChartSectionInfoObj));
	}

    var parentElement = svgDiv.parentNode;
    var legendsDiv = 
    	  '<div style="width:'+svgWidth+'px;background-color:  floralwhite;" > '
    	+ '		<div style="margin-left:  20px;margin-right:  10px"> '                    
    	+ '			$$LEGEND_TEMPLATES$$ '
    	+ '     </div> ' 
    	+ '</div>';
       
    var legendTemplate = 
    	'<span class="mr-2"> '
    	+ '<i class="fas fa-circle" style="color:  $$CIRCLE_COLOR$$;"></i> $$LEGEND_TEXT$$ '
    	+ '</span> ';
    var legendElements = "";
    
    for(var i=0;i<dataList.length;i++)
	{
    	var colorCode = colors[i];
    	var legendElement = legendTemplate;
    	var funnelChartLegendDescriptionColumnDataName = funnelChartInfo['funnelChartLegendDescriptionColumn'];
    	var funnelChartDataInfoObj = dataList[i];    	
    	var legendText = funnelChartDataInfoObj[funnelChartLegendDescriptionColumnDataName];		
    	legendElement = legendElement.replace("$$CIRCLE_COLOR$$", colorCode);
    	legendElement = legendElement.replace("$$LEGEND_TEXT$$", legendText);
    	legendElements += legendElement;
	}
    
    legendsDiv = legendsDiv.replace("$$LEGEND_TEMPLATES$$", legendElements);
    parentElement.insertAdjacentHTML("beforeend", legendsDiv);
	
	
					         		      		    
}

function progressBar(progressBarInfo)
{		
	
	var totalAmount = 1000;
	var receivedAmount = 500;	
	
	var dataList = progressBarInfo.dataList;
	if(dataList.length > 0)
	{
		var totalAmountKeyName = progressBarInfo.progressBarTotalColumn;
		var receivedAmountKeyName = progressBarInfo.progressBarReceivedColumn;
		var dataObj = dataList[0];
		totalAmount = dataObj[totalAmountKeyName];
		receivedAmount = dataObj[receivedAmountKeyName];
	}
	else
	{
		return;
	}
	totalAmount = Number(totalAmount);
	receivedAmount = Number(receivedAmount);
	var svgWidth = 500;
	var svgHeight = 80;    
	var svgGroupMarginLeft = 20;
	var svgGroupMarginTop = 25;
	var svgGroupMarginBottom = 60;
	var svgGroupMarginRight = 20;	
	var progressBarHeight  = 16;
	
	var commonPropertiesObj = {};
	commonPropertiesObj['svgWidth'] =svgWidth; 
	commonPropertiesObj['svgHeight'] =svgHeight; 
	commonPropertiesObj['svgGroupMarginLeft'] =svgGroupMarginLeft; 
	commonPropertiesObj['svgGroupMarginTop'] =svgGroupMarginTop; 
	commonPropertiesObj['svgGroupMarginBottom'] =svgGroupMarginBottom; 
	commonPropertiesObj['svgGroupMarginRight'] =svgGroupMarginRight;
	
	commonPropertiesObj = graphsCommonProperties(commonPropertiesObj, progressBarInfo);
	
	svgWidth = commonPropertiesObj['svgWidth']
	svgHeight = commonPropertiesObj['svgHeight'];
	svgGroupMarginLeft = commonPropertiesObj['svgGroupMarginLeft'];
	svgGroupMarginTop = commonPropertiesObj['svgGroupMarginTop'];
	svgGroupMarginBottom = commonPropertiesObj['svgGroupMarginBottom'];
	svgGroupMarginRight = commonPropertiesObj['svgGroupMarginRight'];	
	
	if (progressBarInfo.hasOwnProperty('progressBarHeight'))
	{
		var progressBarHeightTemp = progressBarInfo.progressBarHeight.trim();
		if (progressBarHeightTemp.length > 0)
		{
			var unitType = "PX";
			if (progressBarHeightTemp.includes("%"))
			{
				unitType = "%";
			}
			progressBarHeightTemp = progressBarHeightTemp.replace(unitType, "");

			if (!isNaN(progressBarHeightTemp))
			{
				progressBarHeight = Number(progressBarHeightTemp);
				commonPropertiesObj['progressBarHeight'] = progressBarHeight;
			}
		}
	}
	
	
	var svgDivDataName = progressBarInfo.svgDivDataName;
	var svgDiv = document.getElementById(svgDivDataName);
	svgDiv.style.height = svgHeight;
	svgDiv.style.width = svgWidth;
	svgDiv.innerHTML = "";
	
	svgWidth = (svgWidth - svgGroupMarginLeft - svgGroupMarginRight);
	

	
	// graphGroupTemplate
	var graphGroupTemplate =
		'            <g transform="translate('+svgGroupMarginLeft+','+svgGroupMarginTop+')" data-name="svgGroup" id ="svgGroup"   fill="green" >  ' +			
		'     </g>  ';                		                    
		svgDiv.insertAdjacentHTML("beforeend", graphGroupTemplate);
			
	var firstRectangularWidth = svgWidth ;
	// Formula to calculate second rectangular width
	// 1000 Data = 460 Px
	// Data = 460 PX / 1000
	var secondRectangularWidth =  ((svgWidth / totalAmount) * receivedAmount);
	if(!secondRectangularWidth)
	{
		secondRectangularWidth = 0;
	}
	var firstBarTemplate = '<rect rx="5" ry="5" cursor="pointer" fill="rgb(236, 202, 110)"  width="'+firstRectangularWidth+'" height="'+progressBarHeight+'"></rect> ' ;
	var secondBarTemplate =  ' <rect rx="5" ry="5" cursor="pointer" fill = "rgb(117, 204, 204)" stroke = "transparent"  width="'+secondRectangularWidth+'" height="'+progressBarHeight+'"></rect>';
	
	var svgGroupGTagElement = svgDiv.getElementById("svgGroup");  			
	svgGroupGTagElement.insertAdjacentHTML("beforeend", firstBarTemplate);		
	svgGroupGTagElement.insertAdjacentHTML("beforeend", secondBarTemplate);		
}
function barMouseMoveFuntion(element)
{
	var barInfoObj = element.getAttribute("data-object");
	var barInfoObj = JSON.parse(barInfoObj);
	var displayText = barInfoObj['displayText'];
	var dataText = barInfoObj['dataText'];
    var div = $('[data-name="toolTipDiv"]');    
    div.html(""+dataText+"<br> "+displayText+"");    
    
    var x = event.clientX;
    var y = event.clientY;    
    div.css("left", (x + 15) + "px")
    div.css("top", (y - 50) + "px");    
}
function scatterPlotMouseMoveFuntion(element)
{
	var barInfoObj = element.getAttribute("data-object");
	var barInfoObj = JSON.parse(barInfoObj);
	var displayText = barInfoObj['displayText'];
	var dataText = barInfoObj['dataText'];
    var div = $('[data-name="toolTipDiv"]');    
    div.html(""+dataText+"<br> "+displayText+"");    
    
    var x = event.clientX;
    var y = event.clientY;    
    div.css("left", (x + 15) + "px")
    div.css("top", (y - 50) + "px");    
}
function pieChartMouseMoveFuntion(element)
{
	var sectorInfoObj = element.getAttribute("data-object");
	var sectorInfoObj = JSON.parse(sectorInfoObj);
	var displayText = sectorInfoObj['displayText'];
	var dataText = "";// sectorInfoObj['dataText'];
    var div = $('[data-name="toolTipDiv"]');    
    div.html(""+dataText+"<br> "+displayText+"");    
    
    var x = event.clientX;
    var y = event.clientY;    
    div.css("left", (x + 15) + "px")
    div.css("top", (y - 50) + "px");    
}
function funnelChartMouseMoveFuntion(element)
{
	var sectorInfoObj = element.getAttribute("data-object");
	var sectorInfoObj = JSON.parse(sectorInfoObj);
	var displayText = sectorInfoObj['displayText'];
	var dataText = sectorInfoObj['dataText'];
    var div = $('[data-name="toolTipDiv"]');    
    div.html(""+dataText+"<br> "+displayText+"");    
    
    var x = event.clientX;
    var y = event.clientY;    
    div.css("left", (x + 15) + "px")
    div.css("top", (y - 50) + "px");    
}
function progressBarMouseMoveFuntion(element)
{
	var barInfoObj = element.getAttribute("data-object");
	var barInfoObj = JSON.parse(barInfoObj);
	var displayText = barInfoObj['displayText'];
	var dataText = barInfoObj['dataText'];
    var div = $('[data-name="toolTipDiv"]');    
    div.html(""+dataText+"<br> "+displayText+"");    
    
    var x = event.clientX;
    var y = event.clientY;    
    div.css("left", (x + 15) + "px")
    div.css("top", (y - 50) + "px");    
}

function getCookie(cname)
{
    var name = cname + "=";
    var cookiesList = document.cookie.split(';');
    for (var i = 0; i < cookiesList.length; i++)
    {
        var cookieInfo = $.trim(cookiesList[i]);
        if (cookieInfo.indexOf(name) == 0)
            return cookieInfo.substring(name.length, cookieInfo.length);
    }
    return "";
}

function scrollToSpecifiedElement(dataName)
{
    $('html, body').animate({
        scrollTop: ($('[data-name="' + dataName + '"]').offset().top) - 50
    }, 1000);
}

function showModulesMenuPopup()
{
	var modulesMenuPopup = $('[data-name="modulesMenuPopup"]');
	if(modulesMenuPopup.length == 1)
	{
		modulesMenuPopup.show();
		modulesMenuPopup.focus();
	}
	else
	{
		location.href = "/home"
	}
}


function showUploadPoup()
{
	var modulesMenuPopup = $('[data-name="fileUploadPopup"]');
	modulesMenuPopup.show();
	modulesMenuPopup.focus();	
}

function showAllEntitiesUploadPoup()
{
	var modulesMenuPopup = $('[data-name="allEntitiesUploadPopup"]');
	modulesMenuPopup.show();
	modulesMenuPopup.focus();	
}

function downloadAllEntities()
{		
    var requestInfoJsonData = {};
	var urlContextPath = "";// getContextPath();
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
		url : urlContextPath + APPLICATION_URL_PREFIX + '/downloadAllEntities'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: requestInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
    			window.open(APPLICATION_URL_PREFIX + "/download?fileId=" + fileId);
            }
        }
    });
}

function uploadAllEntities(fileInfo)
{
	var fileId = fileInfo['fileId'];
	
	var areSourceDestinationSameVal = 0;
    var areSourceDestinationSame = $('[data-name="allEntitiesUploadPopup"]').find('[data-name="areSourceDestinationSame"]').is(":checked");
	if(areSourceDestinationSame == true)
	{
		areSourceDestinationSameVal = 1;
	}
	var paramsInfo = {'areSourceDestinationSame':areSourceDestinationSameVal, 'fileId':fileId};
    var requestInfoJsonData = {'paramsInfo':JSON.stringify(paramsInfo)};
	var urlContextPath = "";// getContextPath();
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
		url : urlContextPath +  APPLICATION_URL_PREFIX + '/uploadAllEntities'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: requestInfoJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
            	$('[data-name="allEntitiesUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", APPLICATION_URL_PREFIX + "/download?fileId=" + fileId);
            	$('[data-name="allEntitiesUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function viewAppNotificationLayout(e)
{
	var rowObject = $(e).parents('tr').first().data('row-info');
	var nsiLayoutInfoText = rowObject['nsiLayoutInfoText'];
	var layoutInfo = JSON.parse(nsiLayoutInfoText);
	var layoutName = layoutInfo['layoutName'];
	var notificationLayoutParameterNameAndValuesList = layoutInfo['notificationLayoutParameterNameAndValuesList'];
	var parametersText = "";
	for(var i=0;i<notificationLayoutParameterNameAndValuesList.length;i++)
	{
		var paramInfo = notificationLayoutParameterNameAndValuesList[i];
		var parameterName = paramInfo['parameterName'];
		var parameterValue = paramInfo['parameterValue'];
		if(i==0)
		{
			parametersText += "?" + parameterName + "=" + parameterValue;
		}
		else
		{
			parametersText += "&" + parameterName + "=" + parameterValue;
		}
	}
	
	window.open("/report/"+layoutName+"" + parametersText);;					
}



function viewAppNotificationLayout(e)
{
	var rowObject = $(e).parents('tr').first().data('row-info');
	var nsiLayoutInfoText = rowObject['nsiLayoutInfoText'];
	var layoutInfo = JSON.parse(nsiLayoutInfoText);
	var layoutName = layoutInfo['layoutName'];
	var notificationLayoutParameterNameAndValuesList = layoutInfo['notificationLayoutParameterNameAndValuesList'];
	var parametersText = "";
	for(var i=0;i<notificationLayoutParameterNameAndValuesList.length;i++)
	{
		var paramInfo = notificationLayoutParameterNameAndValuesList[i];
		var parameterName = paramInfo['parameterName'];
		var parameterValue = paramInfo['parameterValue'];
		if(i==0)
		{
			parametersText += "?" + parameterName + "=" + parameterValue;
		}
		else
		{
			parametersText += "&" + parameterName + "=" + parameterValue;
		}
	}
	
	window.open("/report/"+layoutName+"" + parametersText);;					
}

function displaySpinnerPopup()
{
    var spinnerPopUp = $('[data-name="spinnerPopUp"]');
    var isPopupDisplayed = spinnerPopUp.data("is-displayed");
    if (isPopupDisplayed == 1)
    {
        spinnerPopUp.show();
    }
}

function showSpinnerPopUp(processingMessage)
{
    if (!processingMessage || processingMessage == null || processingMessage == 'abcd')
    {
        processingMessage = "Processing request";
    }
    var spinnerPopUp = $('[data-name="spinnerPopUp"]');
    spinnerPopUp.data("is-displayed", 1);
    var isProcessing = spinnerPopUp.data("is-processing");
    var processingMsgElement = spinnerPopUp.find('[data-name="processingMessage"]');
    var messageSuffix = " ....";
    if (isProcessing == 0)
    {
        spinnerPopUp.data("is-processing", 1);
        processingMsgElement.text(processingMessage + messageSuffix);
        displaySpinnerPopup();
        //setTimeout(displaySpinnerPopup, 1000);
    }
    else
    {
        processingMsgElement.append("</br>");
        processingMsgElement.append(processingMessage + messageSuffix);
    }
}



function fetchEntityData(paramsMap)
{		
	var requestUrl = paramsMap['requestUrl'];
	var requestType = paramsMap['requestType'];
	var callbackFunction = paramsMap['callbackFunction'];
    var queryParamsJsonData = {'queryParams': JSON.stringify(paramsMap), 'requestType':requestType};
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
		url : urlContextPath+ APPLICATION_URL_PREFIX + requestUrl +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: queryParamsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	callbackFunction(responseData);
            }
        }
    });
}


function updateEntityInfo(paramsMap)
{		
	var requestUrl = paramsMap['requestUrl'];
	var requestType = paramsMap['requestType'];
	var entityInfoDataObject = paramsMap['entityInfoDataObject'];
	var callbackFunction = paramsMap['callbackFunction'];
    var entityInfoJsonData = {'entityInfoDataObject': JSON.stringify(entityInfoDataObject), 'requestType':requestType};
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
		url : urlContextPath+ APPLICATION_URL_PREFIX + requestUrl +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: entityInfoJsonData,
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

function isFieldLengthAllowed(fieldValue, fieldLength)
{
	if(fieldValue != null)
	{
		if(fieldValue.length > fieldLength)
		{
			return false;
		}
	}
	return true;
}

var SPINNER_POPUP_TEMPLATE = 
'<div  class="spinner-background" data-is-processing="0" data-name="spinnerPopUp"  style="display:none; z-index: 100000" data-is-displayed="0" >' +    
'	<div class="popup medium-width" style=" background: rgba(0,0,0,0)">    ' +
'    <div class="content">    ' +
'	        <div class="loader">' +    
'	        </div>               ' +
'	        <div data-name="processingMessage" style="text-align: center; color: #fff; font-size: 20px;" >' +    
'	        </div>                ' +
'	    </div>    ' +
'	</div>    ' +
'</div>';
	

$(document).ready(function ()
{
	var userId = getCookie('userid');
	if(userId && userId > 0)
	{
		$('[data-name="logoutLink"]').show();
	}
	$('body').append(SPINNER_POPUP_TEMPLATE);
});

function updateTextLength(e) 
{
	var maxLength = $(e).data("max-length");
	var currentLength = $(e).val().length;
	var currentCountObj =  $(e).next().find('[data-name="currentCount"]');
	var maxCountObj =  $(e).next().find('[data-name="maximumCount"]');
	if(currentLength >= maxLength)
	{
		currentCountObj.css('color', '#8f0001');
		maxCountObj.css('color', '#8f0001');
	}
	else
	{
		currentCountObj.css('color', '#6d5555');
		maxCountObj.css('color', '#6d5555');
	}
	currentCountObj.text(currentLength);
}

$(document).ready(function() {
	$('[data-element-type="textArea"]').keyup(function(event) {	
		var e = this;
		var maxLength = $(e).data("max-length");
		var currentLength = $(e).val().length;
		var currentCountObj =  $(e).next().find('[data-name="currentCount"]');
		var maxCountObj =  $(e).next().find('[data-name="maximumCount"]');
		if(currentLength >= maxLength)
		{
			currentCountObj.css('color', '#8f0001');
			maxCountObj.css('color', '#8f0001');
		}
		else
		{
			currentCountObj.css('color', '#6d5555');
			maxCountObj.css('color', '#6d5555');
		}
		currentCountObj.text(currentLength);
	});
});


function setUploadDataInputFiles(fileInfo)
{
	var fileName = fileInfo['fileName'];
	$('[data-name="upoadDataInputFilesZip"]').data("inputFilesZip", fileName);
	$('[data-name="upoadDataInputFilesZip"]').parents('[data-name="fileUploadDivElement"]').find('[data-name="fileUploadSuccessMessageDiv"]').show();
}
