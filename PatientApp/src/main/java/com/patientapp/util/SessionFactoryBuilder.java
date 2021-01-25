package com.patientapp.util;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.patientapp.util.CommonUtil;
public class SessionFactoryBuilder
{
	private static HashMap<String, SessionFactory> sessionFactoryMap = new HashMap<String, SessionFactory>();
	public static Session getDBSession(String dbName)
	{
		return getDBSession(dbName, false);
	}
	public static Session getDBSession(String dbName, boolean createNewSession)
	{
		try
		{
			if (sessionFactoryMap.containsKey(dbName) && createNewSession == false)
			{
				SessionFactory sessionFactory = sessionFactoryMap.get(dbName);
				Session session = sessionFactory.openSession();
				return session;
			}
			Configuration config = new Configuration();
			config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			config.setProperty("hibernate.connection.url", "jdbc:mysql://" + CommonUtil.getPropertyValue("DB_HOST_NAME") + ":" + CommonUtil.getPropertyValue("DB_HOST_PORT") + "/" + dbName);
			config.setProperty("hibernate.connection.username", CommonUtil.getPropertyValue("DB_USER_NAME"));
			config.setProperty("hibernate.connection.password", CommonUtil.getPropertyValue("DB_PASSWORD"));
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			config.setProperty("hibernate.show_sql", "true");
						config.addAnnotatedClass(com.patientapp.bean.Organisations.class);
			config.addAnnotatedClass(com.patientapp.bean.UserInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.PrivilegeGroup.class);
			config.addAnnotatedClass(com.patientapp.bean.PrivilegeGroupItems.class);
			config.addAnnotatedClass(com.patientapp.bean.EmployeeRoles.class);
			config.addAnnotatedClass(com.patientapp.bean.LoginSessionInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.ConfigProperties.class);
			config.addAnnotatedClass(com.patientapp.bean.TaskInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.TaskExecutionInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.TaskLayoutParameters.class);
			config.addAnnotatedClass(com.patientapp.bean.EmailAttachmentLayout.class);
			config.addAnnotatedClass(com.patientapp.bean.TaskScheduleInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.TaskSentInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.Patient.class);
			config.addAnnotatedClass(com.patientapp.bean.Doctor.class);
			config.addAnnotatedClass(com.patientapp.bean.Hospital.class);
			config.addAnnotatedClass(com.patientapp.bean.ContactUs.class);

			config.addAnnotatedClass(com.patientapp.bean.DualTableInfo.class);
			config.addAnnotatedClass(com.patientapp.request.service.RequestReceived.class);
			config.addAnnotatedClass(com.patientapp.request.service.RequestSent.class);
			config.addAnnotatedClass(com.patientapp.bean.FileUpload.class);
			config.addAnnotatedClass(com.patientapp.bean.QueryInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.QueryTableInfo.class);
			config.addAnnotatedClass(com.patientapp.bean.QueryColumnInfo.class);
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			if(createNewSession == false)
			{
				sessionFactoryMap.put(dbName, sessionFactory);
			}
			return session;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
}
