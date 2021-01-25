package com.patientapp.util;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class SmtpAuthenticator extends Authenticator
{
	String emailUserName;
	String emailPassword;
	public SmtpAuthenticator(String userName, String password)
	{
		emailUserName = userName;
		emailPassword = password;
	}
	@Override
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(emailUserName, emailPassword);
	}
}
