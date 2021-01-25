var filesUploadedCount = 0;

var FILE_UPLOAD_FORM_TEMPLATE =
        '<form target="hiddeniframe" name="uploadForm" method="post" enctype="multipart/form-data"  style="display:inline;">' +
        '</form>';


var FILE_UPLOAD_NAME_TEMPLATE =
        '<div>' +
        '   <div class="col-sm-11"  data-name="fileNameElement" data-file-relative-path ="">Test File 1</div>' +
        '  <a href="javascript:void(0);"  onclick="deleteCurrentFile(this)" class="col-sm-1" data-toggle="tooltip" data-name="deleteIcon"  data-placement="top" title="Delete"><img src="/static/images/delete.png" style="width:15px; height: 15px;"/></a>&nbsp;' +
        '</div>';

function iframeResponseLoaded()
{
    //stopEventPropagation(event);
    //e.onload = null;
    var doc = this.contentWindow ? this.contentWindow.document :
            (this.contentDocument ? this.contentDocument : this.document);
    var root = doc.documentElement ? doc.documentElement : doc.body;
    var text = root ? (root.textContent || root.innerText) : null;
    var iframeElement = $(this);

    var validJson = false;
    try
    {
        var jsonObject = $.parseJSON(text);
        var success = jsonObject['success'];
        if (success == 1)
        {
            var fileId = jsonObject['fileId'];
            var fileName = jsonObject['fileName'];
            var fileUploadElement = iframeElement.data("file-upload-div-element");             
            var callbackFunctionName = fileUploadElement.data("callback-function-name");
            var prefix = fileUploadElement.data("prefix");
            if(!prefix)
        	{
            	prefix = "";
        	}
            var fileInfo = {'fileId':fileId,'fileName': fileName, "prefix":prefix};
            var callbackFunction = callbackFunctionName+ "(" + JSON.stringify(fileInfo) +")"; 
            eval(callbackFunction);
        }
        validJson = true;
    }
    catch (e)
    {
        var userMsg = "File could not be uploaded successfully. Following might be the possible causes : <br><br> " +
                "1) You are not logged in <br>" +
                "2) The file size has exceeded the limit of 5MB <br>";
        showAlert(userMsg);
    }

    if (validJson == true && jsonObject)
    {
        var alertMsg = jsonObject['alert'];
        if (!alertMsg)
        {
            alertMsg = "Upload process could not be completed successfully !!";
        }
        if (jsonObject['success'] && jsonObject['success'] != 1)
        {
            showAlert(alertMsg);
        }
    }
}

$(document).ready(function() { 
	$('input[type="file"]').change(function (){
	    var fileElement = $(this);
	    var fileName = fileElement.val();
	    fileName = fileName.split("\\").pop();
	    if(fileName.length == 0)
    	{
	    	return;
    	}
	    fileElement.parents('[data-name="fileUploadDivElement"]').data("file-name", fileName);
	    fileElement.parents('[data-name="fileUploadDivElement"]').find('[data-name="fileName"]').text(fileName);
	    fileElement.parents('[data-name="fileUploadDivElement"]').find('[data-name="fileUploadProgressDiv"]').text("Attaching "+fileName);
	    fileElement.parents('[data-name="fileUploadDivElement"]').find('[data-name="fileUploadLink"]').trigger("click");
	  });
	});

function uploadFile(event, e)
{
    stopEventPropagation(event);
    var fileUploadDivElement = $(e).parents("[data-name='fileUploadDivElement']");
    var fileElements = fileUploadDivElement.find(":file");
    var objectType = fileUploadDivElement.data("object-type");

    var ieVersion = msieversion();
    if (ieVersion == 0 || ieVersion >= 10)
    {
        var file = fileElements[0].files[0];
        var fileSelected = false;
        if (file)
        {
            var filename = fileElements[0].value;
            if (filename && filename.length > 0)
            {
                fileSelected = true;
            }
        }
        if (fileSelected == false)
        {
            alert('Select a file to upload !!');
            return;
        }
        var filesize = fileElements[0].files[0].size / (1024 * 1024);
        if (filesize > 100)
        {
            alert('Maximum file size allowed is 5MB. Please select a file within this size limit !!');
            return;
        }
    }

    var fileDivElement = fileUploadDivElement.find("[data-name='formDiv']");

    var uploadFormElement = $(FILE_UPLOAD_FORM_TEMPLATE);
    var targetIframeName = "hiddeniframe" + (filesUploadedCount + 1);
    uploadFormElement.attr("target", targetIframeName);
    uploadFormElement.attr('action', APPLICATION_URL_PREFIX + '/UploadServlet?objectType='+objectType);
    fileDivElement.append(uploadFormElement);
    fileElements.appendTo(uploadFormElement);
    uploadFormElement.prevAll("[name='uploadForm']").remove();
    var iframeElement = $('<iframe name="' + targetIframeName + '" style="display:none;"></iframe>');
    $(document.body).append(iframeElement);

    iframeElement.data("file-upload-div-element", fileUploadDivElement);
    iframeElement.on('load', iframeResponseLoaded);
    uploadFormElement[0].submit();
    filesUploadedCount++;
    return;
}

function stopEventPropagation(evt)
{
    if (!evt)
        return;

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


function msieversion()
{
    var ua = window.navigator.userAgent
    var msie = ua.indexOf("MSIE ")

    if (msie > 0)      // If Internet Explorer, return version number
        return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)))
    else                 // If another browser, return 0
        return 0

}

