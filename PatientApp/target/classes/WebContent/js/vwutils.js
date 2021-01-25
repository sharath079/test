	<script type="text/javascript">
	
	//window.onload = function() {showAlert123();} 
	
	
	function showAlert123()
	{
		alert("alert 123 45 !!");
	}
	
	window.onunload = refreshParent;
	
	/*$("#tabs").tabs({
	    active: localStorage.getItem("currentTabIndex"),
	    activate: function(event, ui) {
	        localStorage.setItem("currentTabIndex", ui.newPanel[0].dataset["tabIndex"]);
	    }
	});*/
	
	jQuery(document).on( "ready", function() 
	{
		refreshTabPanel();
	});
	
	function refreshTabPanel()
   	{  
		//jQuery(".rf-tab-hdr-spcr").css("width", "100");
		//jQuery(".rf-tab-lbl").css("white-space", "normal");
		jQuery(".rf-tab-cnt").css("border", "none");
   	}

	
   	function popupwindow(url, title)
   	{      
   	    window.open(url , title, "fullscreen=0, toolbar=0, scrollbars=0, resizable=0, top=150, left=170, width=1300, height=800; modal=yes");
	   	// NG: for god's sake do not return true here, trust me...
   	    return false;
   	}    

	function popupInSameWindow(url)
   	{      
   	    window.open(url , title, "fullscreen=0, toolbar=0, scrollbars=0, resizable=0, top=170, left=170, width=1100, height=700; modal=yes");
	   	// NG: for god's sake do not return true here, trust me...
   	    return false;
   	}    
   	
   	function popupwindowParams(url, title, top, left, width, height)
   	{      
   	   	var sParams="top="+top+",left="+left+",width="+width+",height="+height;
   	    window.open(url ,title, "toolbar=No,scrollbars=No,resizable=No,fullscreen=No"+sParams);
	   	// NG: for god's sake do not return true here, trust me...
   	    return false;
   	}    
   	
   	function refreshParent()
   	{
   		// window.opener.location.reload();
   		try
   		{
   			window.opener.location.href = window.opener.location.href;
   		}catch(e)
   		{
   			return true;
   	   	}	
   		return true;
   	}

   	function clickSelectControlAll(sFormName, sType, rowNum)
   	{
		try
		{
			//alert ('in clickSelectControlAll: '+selectControl);
   			//var selectControl = sFormName + ":" +sType+ "Table:" +rowNum+":selectRowRadio:0";
			//alert ('in clickSelectControlAll: '+selectControl);
			var oControl = document.getElementById(selectControl);
			oControl.click();
			//alert ('out clickSelectControlAll')
		}catch(e)
		{
			//alert ('in exception clickSelectControlAll')
			return true;
		}	 
		return true;
   	}

   	function clickSelectControl(sFormName, rowNum)
   	{
		try
		{
	   		var selectControl = sFormName+":MessageQueueExtLookupTable:"+rowNum+":selectRowRadio:0";
   			//alert(selectControl);
			var oControl = document.getElementById(selectControl);
			oControl.click();
		}catch(e)
		{
			return true;
		}	 
		 
		return true;
   	}

   	function clickSelectControlNew(sFormName, rowNum)
   	{
		try
		{
	   		var selectControl = sFormName + rowNum + ":selectRowRadio:0";
   			//alert('IN clickSelectControlNew : ' + selectControl);
			var oControl = document.getElementById(selectControl);
			oControl.click();
			//alert('OUT clickSelectControlNew');
		}catch(e)
		{
			alert('OUT clickSelectControlNew - Exception');
			return true;
		}	 
		 
		return true;
   	}

   	function PrintContent(divName)
   	{
		try
		{   		
			var PrintReadyString=document.getElementById("PrintDivMain");
			var WindowObject = window.open("", "PrintWindow" ,"height=600,width=600,toolbars=no,scrollbars=yes,status=no,resizable=yes");
			var printString = PrintReadyString.innerHTML;
//			printString = printString.replace("overflow-x: hidden;","overflow-x: visible;");
//			printString = printString.replace("overflow-y: hidden;","overflow-y: visible;");
//			debugCode(printString,3);
			WindowObject.document.write(printString);
			WindowObject.document.close();
			WindowObject.focus();
			setInterval(function () 
	   	   	{ 
				WindowObject.print();
		    	WindowObject.close();
	   	   	}, 100);
		}catch(e)
		{
			alert("In ERROR" + e);
			return true;
		}	 
		return true;     
   	}

   	function makePrettyPrint(PrintReadyString)
   	{
		inform("************************************************" );
		inform("Inside prettry:" + PrintReadyString );

   		var begin = "--BEGIN_REMOVE--";
   		var end = "--END_REMOVE--";   			
		var c = PrintReadyString.indexOf(begin); 
		var L = PrintReadyString.indexOf(end);
		var final = PrintReadyString.substring (c,L) ;
		if (final.indexOf('PrintReadyString')==-1)
		{
			if (final!="")
			{
				inform("************************************************" );
				inform("Before:" + final );
				PrintReadyString = PrintReadyString.replace(final, '');
				inform("************************************************" );
				inform("after:" + final );
			}	
		}
		return PrintReadyString;
   	}   		    

   	function debugCode(str,option)
   	{
   		if (option=="1")
   		{
   			alert(str);
   		}
   		
   		if (option=="2")
   		{
   			console.log(str);
   		}
   		
   		if (option=="3")
   		{
   			alert(str);
   			console.log(str);
   		}	
   	}
   	
   	function count(main_str, sub_str) 
    {
   		return 0;
    }
   	
   	
   	function dataTableSelectOneRadio(radio, sControls)
	{
   		try
		{   		
   			//alert ('Inside dataTableSelectOneRadio:' + sControls);
   			var id = radio.name.substring(radio.name.lastIndexOf(':'));
		    var el = radio.form.elements;
		    var aControls = sControls.split(',');
		    for (var i = 0; i &lt; el.length; i++)
		    {
		    	    if (el[i].name.substring(el[i].name.lastIndexOf(':')) == id)
				    {
				            el[i].checked = false;
				            
				            for (var j = 0; j&lt; aControls.length; j++)
				    	    {
				            	dataTableSelectOneClick(el[i].name,id,1,':'+ aControls[j]);
				    	    }	
				    }
		    }
	
			radio.checked=true;
	
			for (var k = 0; k&lt; aControls.length; k++)
		    {
	        	dataTableSelectOneClick(radio.name ,id,2,':'+ aControls[k]);
		    }

			//alert ('outside dataTableSelectOneRadio');
		}catch(e)
		{
			//alert ('inside execption dataTableSelectOneRadio');
			return true;
		}	 

		return true;
	}
	
	function dataTableSelectOneClick(fullPath, sRadio, enable, sAction)
	{
		try
		{
			var sControl = fullPath.replace(sRadio, sAction);
			var radio = document.getElementById(sControl);
			if (enable==2)
			{
				radio.removeAttribute('style');
			}else
			{
				radio.setAttribute('style','pointer-events: none;text-decoration:none');
			}
			
		}catch(e)
		{
			return true;
		}	 
		return true;
	}
   	
	function printValues(selectedRow)
	{
		try
		{
			var h = selectedRow;
			// show the values stored
			for (var k in h)
			{
			    // use hasOwnProperty to filter out keys from the
				// Object.prototype
			    if (h.hasOwnProperty(k)) 
			    {
			        alert('child key is: ' + k + ', value is: ' + h[k]);
			    }
			}
		}catch(e)
		{
			return true;
		}	
		return true;
	}

   function convertAmountToWords(inputElementId,displayElementId)
   	{
		try
		{
		    var junkVal=document.getElementById(inputElementId).value;
   	        var finalWord1 = test_value(junkVal,displayElementId);
   	        var finalWord2 = "";
   	
   	        var val = document.getElementById(inputElementId).value;
   	        var actual_val  = document.getElementById(inputElementId).value; 
   	        document.getElementById(inputElementId).value = val;
   	        
   	        if(val.indexOf('.')!=-1)
   	       {
   	              val = val.substring(val.indexOf('.')+1,val.length);

   	              if(val.length==0 || val.length=='00')
   	   	          {
   	                 finalWord2 = "zero paisa only";
   	              }
   	              else
   	   	          {
   	               	 document.getElementById(inputElementId).value = val;
   	                 finalWord2 = test_value() + " paisa only";
   	               }
  	               
   	             document.getElementById(displayElementId).innerHTML=finalWord1 +" Rupees and "+finalWord2;
   	       }
      	  else
   	   	  {
       	   	  	if (finalWord1!='')
           	   	  {
   	             	document.getElementById(displayElementId).innerHTML=finalWord1 +" Rupees Only";
           	   	  }	
   	       }

   	        document.getElementById(inputElementId).value = actual_val;
		}catch(e)
		{
			return true;
		}     
		return true;
   	}

   function test_value(junkVal,displayElementId)
   	{
		try
		{
		    junkVal  = Math.floor(junkVal);
	   	    var obStr = new String(junkVal);
	   	    numReversed= obStr.split("");
	   	    actnumber=numReversed.reverse();
	
	   	 
	   	    if(Number(junkVal)&gt;=0)
	   	    {
	   	        // do nothing
	   	    }
	   	    else{
	   	        // alert('Wrong Number cannot be converted');
	   	        return false;
	   	    }
	   	    
	   	    if(Number(junkVal)==0)
	   	    {
	   	        document.getElementById(displayElementId).innerHTML=obStr+''+'Rupees Zero Only';
	   	        return false;
	   	    }
	   	    
	   	    if(actnumber.length&gt;9)
	   	    {
	   	        alert('Oops!!!! The Number is too big to covert to words');
	   	        document.getElementById(displayElementId).innerHTML='';
	   	        return false;
	   	    }
	   	    
	   	    var iWords=["Zero", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine"];
	   	    var ePlace=['Ten', ' Eleven', ' Twelve', ' Thirteen', ' Fourteen', ' Fifteen', ' Sixteen', ' Seventeen', ' Eighteen', 'Nineteen'];
	   	    var tensPlace=['dummy',' Ten',' Twenty',' Thirty', ' Forty', ' Fifty', ' Sixty', ' Seventy', ' Eighty', ' Ninety' ];
	   	    var iWordsLength=numReversed.length;
	   	    var totalWords="";
	   	    var inWords=new Array();
	   	    var finalWord="";
	   	    j=0;
	
	   	    for(i=0 ; i&lt;iWordsLength; i++)
	   	    {
	   	   		switch(i)
	   	        {
	   	        case 0:
	   	       		if(actnumber[i]==0 || actnumber[i+1]==1 )
	   	            {
	   	                inWords[j]='';
	   	            }
	   	            else {
	   	                inWords[j]=iWords[actnumber[i]];
	   	            }
	   	            inWords[j]=inWords[j];
	   	            break;
				
	   	        case 1:
	   	            tens_complication();
	   	            break;
	   	         
	   	   	     case 2:
	   	            if(actnumber[i]==0) 
	   	   	        {
	   	                inWords[j]='';
	   	            }
	
	   	            else if(actnumber[i-1]!=0 &amp;&amp; actnumber[i-2]!=0)
	   	   	        {
	   	               inWords[j]=iWords[actnumber[i]]+" Hundred and";
	   	            }
	   	            else 
	   	   	          {
	   	                inWords[j]=iWords[actnumber[i]]+ " Hundred";
	   	              }
	   	            break;
	   	 
	   	        case 3:
	   	            if(actnumber[i]==0 || actnumber[i+1]==1) 
	   	   	        {
	   	                inWords[j]='';
	   	            }
	   	            else
	   	   	        {
	   	                inWords[j]=iWords[actnumber[i]];
	   	            }
	
	   	         	if(actnumber[i+1]!=0 || actnumber[i] &gt; 0)
	   	            { 
	   	                inWords[j]=inWords[j]+" Thousand";
	   	            }
	   	            break;
	   	          
	   	        case 4:
	   	            tens_complication();
	   	            break;
	   	        case 5:
	   	            if(actnumber[i]=="0" || actnumber[i+1]==1 ) {
	   	                inWords[j]='';
	   	            }
	   	            else {
	   	                inWords[j]=iWords[actnumber[i]];
	   	            }
	   	            if(actnumber[i+1] != 0 || actnumber[i] &gt; 0){   // here
	   	                inWords[j]=inWords[j]+" Lakh";
	   	            }
	   	            break;
	   	             
	   	        case 6:
	   	            tens_complication();
	   	            break;
	   	             
	   	        case 7:
	   	            if(actnumber[i]=="0" || actnumber[i+1]==1 ){
	   	                inWords[j]='';
	   	            }
	   	            else {
	   	                inWords[j]=iWords[actnumber[i]];
	   	            }
	   	            if(actnumber[i+1] != 0 || actnumber[i] &gt; 0){ // changed
																	// here
	   	                inWords[j]=inWords[j]+" Crore";
	   	            }
	   	            break;
	   	          
	   	        case 8:
	   	            tens_complication();
	      	         break;
	      	           
	   	        default:
	   	            break;
	   	        }
	   	    	j++;
	
	   	    }

	   	    function tens_complication() 
	   	    {
	   	        if(actnumber[i]==0) {
	   	            inWords[j]='';
	   	        }
	   	        else if(actnumber[i]==1) {
	   	            inWords[j]=ePlace[actnumber[i-1]];
	   	        }
	   	        else {
	   	            inWords[j]=tensPlace[actnumber[i]];
	   	        }
	   	    }
	
	   	    inWords.reverse();
	   	    	 
	   	    for(i=0; i&lt;inWords.length; i++)
	   	    {
	   	        finalWord+=inWords[i];
	   	    }
	   	    
		}catch(e)
		{
			return true;
		}   
   	    return finalWord;
   	}

   function sleep(milliseconds)
   {
	 var start = new Date().getTime();
	 for (var i = 0; i&lt;milliseconds; i++) 
	 {
	   if ((new Date().getTime() - start) > milliseconds)
	   {
	      break;
	    }
      }
   } 
	  /* Disable Back and Forword */
	javascript:window.history.forward(1);

   </script>

