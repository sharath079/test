function retrieveHospitalList()
{
    //message();
}

function doAfterPanelRefreshedForHospitalExt()
{
     message();
    //Custom handling
}



function doAfterPanelInitializedForHospitalExt()
{
    //Custom handling
}

/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */


function doAfterSelectOptionChangedForHospitalExt(fieldName)
{
    //Custom handling
}

/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */


function doAfterLookupRowChangedForHospitalExt(fieldName)
{
    //Custom handling
}



function processResultRowForHospitalExt(rowObj)
{
    var hospital=rowObj.data("hospital");
    var hospName=hospital['hospName'];
    

    if(hospName=='Nims')
    {
        rowObj.css("color","red");
    }
    else if(hospName=='yashoda')
    {
        rowObj.css("color","green");
    }
    else{
        rowObj.css("color","blue");
    }
    
    //Custom handling
}



function doAfterLookupOptionLoadedForHospitalExt(lookupOptionElement, paramsMap)
{
    //Custom handling
}



function doBeforeExecuteCustomAPIForHospitalExt(customEventName)
{
    //Custom handling
}



function doAfterExecuteCustomAPIForHospitalExt(customEventName)
{
    //Custom handling
}

