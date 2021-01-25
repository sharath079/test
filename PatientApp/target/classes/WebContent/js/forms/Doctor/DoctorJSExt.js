
function fetchDoctorSearchResultsList()
{
    message2();
}
function doAfterPanelRefreshedForDoctorExt()
{
    message();
    //Custom handling
}



function doAfterPanelInitializedForDoctorExt()
{
    message();
    //Custom handling
}

/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */


function doAfterSelectOptionChangedForDoctorExt(fieldName)
{
    //Custom handling
}

/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */


function doAfterLookupRowChangedForDoctorExt(fieldName)
{

    //Custom handling
}



function processResultRowForDoctorExt(rowObj)
{
    //message2();
     var doctor=rowObj.data("doctor");
     var doctName=doctor['doctorName'];

     $("th").css("background","white");
    //$("tr:even").css("background","green");

    if(doctName[0]=='P')
    {
    rowObj.css("background","yellow");
    }
    else
    {
        rowObj.css("background","green");
    }
    //Custom handling
    
}



function doAfterLookupOptionLoadedForDoctorExt(lookupOptionElement, paramsMap)
{
    //Custom handling
}



function doBeforeExecuteCustomAPIForDoctorExt(customEventName)
{
    //Custom handling
}



function doAfterExecuteCustomAPIForDoctorExt(customEventName)
{
    //Custom handling
}

