package com.vw.runtime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
/*@ManagedBean(name = "VWRequestObject")
@RequestScoped*/
public class VWRequestObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	String chatQuestion;
	public String getChatQuestion()
	{
		return chatQuestion;
	}
	public void setChatQuestion(String chatQuestion)
	{
		this.chatQuestion = chatQuestion;
	}
}
