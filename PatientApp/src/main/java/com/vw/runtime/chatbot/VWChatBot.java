package com.vw.runtime.chatbot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;
import com.vw.runtime.VWIAppConstants;
import com.vw.runtime.VWIController;
import com.vw.runtime.VWRequestObject;
import com.vw.runtime.VWSessionObject;
import com.vw.runtime.VWSimpleController;
@SuppressWarnings({"unused", "resource"})
public class VWChatBot extends VWSimpleController
{
	private List<String> qParamsList = new ArrayList<String>();
	VWSessionObject session_local = new VWSessionObject();
	public static void main(String[] args) throws IOException, URISyntaxException
	{
		VWChatBot test = new VWChatBot();
		Scanner dataIn = new Scanner(System.in);
		String sentence = "";
		String sResp = "";
		while (!sentence.equalsIgnoreCase("Bye"))
		{
		    System.out.println("Question:>>");
		    sentence = dataIn.nextLine().trim();
		    if (!sentence.equalsIgnoreCase("Bye"))
		    {
		    	sResp = test.handleResponse(sentence, test.session_local , new VWIAppConstants()
				{
					@Override
					public Object getSwiftMapperProcessObject(String sEntityName)
					{
						return null;
					}
					@Override
					public Object getController(String sEntityName)
					{
						return null;
					}
				});
				System.out.println("Question: " + sentence);
				System.out.println("Response: " + sResp);
				dataIn = new Scanner(System.in);
				System.out.println();
		    }else
		    {
				System.out.println("Question: " + sentence);
				System.out.println("Response: " + sResp);
		    }
		}
	}
	public String handleResponse(String sentence, VWSessionObject session, Object appConstants) throws FileNotFoundException, URISyntaxException
	{
		//Begin Make this a singleton
		String nounRepositry = "/resources/chatbot/noun.repositry";
		String contextRepositry = "/resources/chatbot/context.repositry";
		String verbRepositry = "/resources/chatbot/verb.repositry";
		String actionRepositry = "/resources/chatbot/action.repositry";
		String guidedRepositry = "/resources/chatbot/guided.repositry";
		List<VWSearchItem> nounList = new ArrayList<VWSearchItem>();
		List<VWSearchItem> contextList =  new ArrayList<VWSearchItem>();
		List<VWSearchItem> verbList =  new ArrayList<VWSearchItem>();
		List<VWSearchItem> actionSet =  new ArrayList<VWSearchItem>();
		HashMap<String, String[]> guidedMap =  new HashMap<String, String[]>();
		nounList = loadRepositry(nounRepositry);
		contextList = loadRepositry(contextRepositry);
		verbList = loadRepositry(verbRepositry);
		actionSet = loadRepositry(actionRepositry);
		guidedMap = loadGuidedRepositry(guidedRepositry);
		HashMap<Object, Object> actionMap = loadActions(actionSet);
		//End Make this a singleton
		String sResp = "";
		if (!isExists(session.getSequence())) 
		{
			qParamsList = new ArrayList<String>(); // Very Important code. Do not move/change it
		}else 
		{
			qParamsList.add(Integer.parseInt(session.getSequence()), sentence);
		}
		if (!sentence.equalsIgnoreCase("Bye"))
		{
			if (isExists(session.getGuidedPath()))
			{
				sResp = session.getGuidedPath();
			}else 
			{
				List<VWSearchItem> tokens = tokenize(sentence);
				String sTuple = buildTuple(tokens, nounList, contextList, verbList);
				sResp = chatResponse(sTuple, actionMap);
			}	
			if (!isExists(sResp))
			{
	    		sResp = "Oops, I did not understand that, I am still learning. Please rephrase and try again or click here for assistance with help desk ";
			}
			if (sResp.contains("GUIDED_PATH"))
			{
				if (!isExists(session.getSequence())) // continuing in guided path
				{
					session.setSequence("0");
					session.setGuidedPath(sResp);
				}else 
				{
					session.setSequence(Integer.toString(Integer.parseInt(session.getSequence()) + 1));
				}
				sResp = processGuidedResponse(guidedMap, sResp, session.getSequence());
				// Begin Handle API calls
				if (sResp.contains("INVOKE")) 
				{
					for (int iParams=0;iParams<qParamsList.size();iParams++) 
					{
						sResp = StringUtils.replace(sResp, iParams+ "%",(String)qParamsList.get(iParams));
					}
					sResp = handleAPICalls(sResp,appConstants);
					session.setSequence(""); // Important clear all
					session.setGuidedPath("");// Important clear all
				}
				// End Handle API calls
			}
		}else
		{
			sResp = "Bye, Thank you for chatting with me. Have a nice day ahead";
		}
		VWRequestObject vwRequestObject = (VWRequestObject) getManagedBean("VWRequestObject");
		vwRequestObject.setChatQuestion("");
		return sResp;
	}
	private String handleAPICalls(String sResp,Object appConstants)
	{
		VWIAppConstants newAppConstants = (VWIAppConstants)appConstants;
		HashMap<String, String> hashParams = new HashMap<String, String>();
		String actionString = StringUtils.substringBetween(sResp,"INVOKE[", "]");
		String[] queryParams = StringUtils.split(actionString,",");
		String sEntityName = queryParams[0];
		if (sEntityName.equalsIgnoreCase("SESSION")) 
		{
			sResp = handleInvokeSession(sResp, newAppConstants, hashParams, queryParams, sEntityName);
		}else 
		{
			sResp = handleInvokeEntity(sResp, newAppConstants, hashParams, queryParams, sEntityName);
		}	
		return sResp;
	}
	private String handleInvokeSession(String sResp, VWIAppConstants newAppConstants, HashMap<String, String> hashParams, String[] queryParams, String sEntityName) 
	{
		VWSessionObject session = getSessionObject();
		for (int h=1;h<queryParams.length;h++) 
		{
			String sTemp = queryParams[h];
			String sValues[] = StringUtils.split(sTemp,"=");
			String sKey = sValues[0];
			String sValue = sValues[1];
			VWSessionObject vwSessionObject = getSessionObject();
			vwSessionObject.setParamValue(sKey, sValue);
		}
		sResp = StringUtils.substringBetween(sResp,"DISPLAY{", "}");
		return sResp;
	}
	private String handleInvokeEntity(String sResp, VWIAppConstants newAppConstants, HashMap<String, String> hashParams, String[] queryParams, String sEntityName)
	{
		String displayString = StringUtils.substringBetween(sResp,"DISPLAY{", "}");
		if (displayString.contains("APPLICATION_IMPL_URL")) 
		{
		 	sResp=displayString;
		}
		else
		{
			VWIController controller= (VWIController)newAppConstants.getController(sEntityName);
			String[] displayParams = StringUtils.split(displayString,",");
			for (int h=1;h<queryParams.length;h++) 
			{
				String sTemp = queryParams[h];
				String sValues[] = StringUtils.split(sTemp,"=");
				String sKey = sValues[0];
				String sValue = sValues[1];
				hashParams.put(sKey, sValue);
			}
			List<?> apiResult = controller.retrieveUniqueTransactionAbstract(sEntityName,hashParams);
			String sNewResp= "";
			if (apiResult!=null && apiResult.size() >0) 
			{
				Object bean = apiResult.get(0);
				for (int j=0;j<displayParams.length;j++) 
				{
					String sDField = displayParams[j];
					String sLabel =  controller.getLabel(sDField);
					String sValue = (String) controller.getFieldValue(bean, sDField);
					if (sValue==null) 
					{
						sValue="-NA-";
					}
					sNewResp = sNewResp + sLabel+" :"+sValue+ "$$HTML_NEWLINE$$";
				}
				sResp = sNewResp;
			}else 
			{
				sResp = "No record found("+ hashParams +") !!!";
			}
		}
		return sResp;
	}
	private String processGuidedResponse(HashMap<String, String[]> guidedMap, String sResp, String sequence)
	{
		if (sResp.contains("GUIDED_PATH"))
		{
			String[] lst = (String[]) guidedMap.get(sResp);
			sResp = (String) lst[Integer.parseInt(sequence)];
		}
		return sResp;
	}
	public List<VWSearchItem> tokenize(String sLineMsg) throws FileNotFoundException
	{
		List<VWSearchItem> list = new ArrayList<VWSearchItem>();
		String sTemp[] = StringUtils.split(sLineMsg);
		for (String line:sTemp)
		{
			VWSearchItem item = new VWSearchItem(line);
			list.add(item);	
		}
		list.addAll(list);
		return list;
	}
	public HashMap<String, String[]> loadGuidedRepositry(String sFile) throws FileNotFoundException, URISyntaxException
	{
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		InputStream input = null;
		input = this.getClass().getResourceAsStream(sFile);
		String sFileContent = "";
		try (Scanner sc = new Scanner(input))
		{
			while (sc.hasNextLine())
			{
				sFileContent = sFileContent + "\n"+ sc.nextLine();
			}
			String[] allStrings = StringUtils.split(sFileContent,"\n");
			int i=0;
			for (String line:allStrings) 
			{
				String sGuide = "GUIDED_PATH[" + i + "]";
				String sBeginGuide = "BEGIN_"+ sGuide;
				String sEndGuide = "END_"+ sGuide;
				if (line.contains(sGuide))
				{
					String sValue = StringUtils.substringBetween(sFileContent, sBeginGuide, sEndGuide);
					String[] sValues = StringUtils.split(sValue,"\n");
					map.put(sGuide, sValues);
					i++;
				}
			}
		}
		return map;
	}
	public List<VWSearchItem> loadRepositry(String sFile) throws FileNotFoundException, URISyntaxException
	{
		List<VWSearchItem> list = new ArrayList<VWSearchItem>();
		InputStream input = null;
		input = this.getClass().getResourceAsStream(sFile);
		try (Scanner sc = new Scanner(input))
		{
			while (sc.hasNextLine())
			{
				String[] lines = StringUtils.substringsBetween(sc.nextLine(), "(", ")");
				for (String line:lines)
				{
					VWSearchItem item = new VWSearchItem(line);
					list.add(item);	
				}
			}
		}
		return list;
	}
	public HashMap<Object, Object> loadActions(List<VWSearchItem> actionSet) throws FileNotFoundException
	{
		HashMap<Object, Object> hashMap = new HashMap<>();
		for(VWSearchItem sRow:actionSet)
		{
			String sTemp[] = StringUtils.split(sRow.getValue(), ",");
			hashMap.put(sTemp[0], sTemp[1]);
		}
		return hashMap;
	}
	public String buildTuple(List<VWSearchItem> tokens, List<VWSearchItem> nounSet, List<VWSearchItem> contextSet, List<VWSearchItem> verbSet)
	{
		String sTuple = "";
		String sNoun = "";
		String sContext = "";
		String sVerb = "";
		for(VWSearchItem sTempItem:tokens)
		{
			if (nounSet.contains(sTempItem))
			{
				sNoun = sTempItem.getMatchedValue();
			}
			if (verbSet.contains(sTempItem))
			{
				sVerb = sTempItem.getMatchedValue();
			}
			if (contextSet.contains(sTempItem))
			{
				sContext = sTempItem.getMatchedValue();
			}
		}
		//VERB:CONTEXT:NOUN
		sTuple = "(VERB:"+ sVerb + ")" + "(CONTEXT:" + sContext + ")" + "(NOUN:" + sNoun + ")";
		return sTuple;
	}
	public String chatResponse(String sTuple, HashMap<Object, Object> actionMap)
	{
		String sAction ="";
		String sTuples[] = StringUtils.substringsBetween(sTuple,"(",")");
		String sVerb = sTuples[0];
		String sContext = sTuples[1];
		String sNoun = sTuples[2];
		sVerb = StringUtils.replace(sVerb, "VERB:", "");
		sContext = StringUtils.replace(sContext, "CONTEXT:", "");
		sNoun = StringUtils.replace(sNoun, "NOUN:", "");
		if(isExists(sVerb))
		{
			sAction = sVerb;
		}
		if(isExists(sContext))
		{
			if(isExists(sAction))
			{
				sAction = sAction + "+" + sContext;
			}else
			{
				sAction = sContext;
			}
		}
		if(isExists(sNoun))
		{
			if(isExists(sAction))
			{
				sAction = sAction + "+" + sNoun;
			}else
			{
				sAction = sNoun;
			}
		}
		String sResp = (String) actionMap.get(sAction);
		if (!isExists(sResp))
		{
			if (isExists(sVerb) && (!isExists(sContext) && (!isExists(sNoun))))
			{
				sResp = "I have received a request on `What to do` ("+ sVerb + ") but you have not mentioned the context that I understand, retry after rephrasing ";
			}
			if (isExists(sVerb) && (isExists(sContext) && (!isExists(sNoun))))
			{
				sResp = "I have received a request on `What to do` ("+ sVerb + ") with a 'Context' ("+ sContext + ") but the I still need to know 'on what is the request' , retry after rephrasing ";
			}
			if (isExists(sVerb) && (isExists(sContext) && (isExists(sNoun))))
			{
				sResp = "I am not yet trained to handle this scenario, retry with a different request";
			}
			// Context Scenario
			if (!isExists(sVerb) && (isExists(sContext) && (!isExists(sNoun))))
			{
				sResp = "I have not received a request on `What to do` ("+ sVerb + ") with this 'Context' ("+ sContext + ") that I understand, retry after rephrasing ";
			}
			// Noun scenario
			if (!isExists(sVerb) && (!isExists(sContext) && (isExists(sNoun))))
			{
				sResp = "I have not received a request on `What to do` nor a 'Context' for ("+ sNoun + ") that I understand, retry after rephrasing ";
			}
			if (!isExists(sResp))
			{
				sResp = "OOPS !!!, I am not yet trained to answer that, please try another one.";
			}
		}
		return sResp;
	}
	protected boolean isExists(String sStr)
	{
		if (sStr != null && sStr.trim().length() > 0)
		{
			return true;
		}else 
		{
			return false;
		}
	}
}
