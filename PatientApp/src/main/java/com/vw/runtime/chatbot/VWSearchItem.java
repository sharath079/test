package com.vw.runtime.chatbot;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
public class VWSearchItem
{
	 private String tokenString;
	 private String matchedValue;
	 public VWSearchItem(String tokenString)
	 {
	     this.tokenString = tokenString;
	     this.matchedValue ="";
	 }
	 public String getValue()
	 {
		 return this.tokenString;
	 }
	 public String getMatchedValue()
	 {
		 return this.matchedValue;
	 }
	 @Override
	 public boolean equals(Object o)
	 {
		 VWSearchItem t = (VWSearchItem)(o);
		 String toCompare = t.getValue();
		 if (tokenString.length()<=2) 
		 {
			 if (tokenString.equalsIgnoreCase(toCompare)) 
			 {
				 matchedValue=tokenString;
				 return true;
			 }	 
			 return false;
		 }else 
		 {
			 int i =	0;
			 try
			 {
				 Soundex sound = new Soundex();
				 i = sound.difference(tokenString,toCompare);
				 matchedValue=toCompare;
			 } catch (EncoderException e)
			 {
			 }
			 if (i==4)
			 {
				 matchedValue=toCompare;
				 return true;
			 }else 
			 {
				 matchedValue="";
				 return false;
			 }
		 }	 
	 }
}
