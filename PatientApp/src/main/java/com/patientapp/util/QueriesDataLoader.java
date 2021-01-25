package com.patientapp.util;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.JsonObject;
import com.patientapp.util.SessionFactoryBuilder;
import com.patientapp.util.Tasks;
import com.patientapp.bean.QueryColumnInfo;
import com.patientapp.bean.QueryInfo;
import com.patientapp.bean.QueryTableInfo;
import com.patientapp.util.CommonUtil;
import com.patientapp.util.layout.QueriesDataLoaderUtil;
public class QueriesDataLoader
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QueriesDataLoader.class);
	public static void main(String args[])throws Exception
	{
		String databaseName  = CommonUtil.getMasterDBName();
		executeQueriesScript(databaseName);
	}
	public static JsonObject executeQueriesScript(JsonObject requestParametersInfo) throws Exception
	{
		String databaseName = requestParametersInfo.get("databaseName").getAsString();
		JsonObject dataObject = new JsonObject();
		try
		{	int executed = executeQueriesScript(databaseName);
			if(executed == 1)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeToLog("Your request could not be processed as queries script could not be executed.");
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		dataObject.addProperty("alert", "Your request could not be processed as queries script could not be created.");
		return dataObject;
	}
	public static int executeQueriesScript(String databaseName)throws Exception
	{
		Session organisationSession = null;
		Transaction tx = null;
		try
		{							
			organisationSession = SessionFactoryBuilder.getDBSession(databaseName);
			tx = organisationSession.beginTransaction();
			QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
			int isQueriesDataDeleted = queryLoader.deleteQueriesInfo(organisationSession);
			if(isQueriesDataDeleted !=1)
			{
				System.err.println("Queries could not be deleted.");
				return 0;
			}
			int isQueryAdded = 1;
						isQueryAdded = addOrganisationsQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("Organisations query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addUserInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("UserInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPrivilegeGroupQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PrivilegeGroup query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPrivilegeGroupItemsQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PrivilegeGroupItems query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addEmployeeRolesQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("EmployeeRoles query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addLoginSessionInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("LoginSessionInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addConfigPropertiesQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("ConfigProperties query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addTaskInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("TaskInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addTaskExecutionInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("TaskExecutionInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addTaskLayoutParametersQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("TaskLayoutParameters query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addEmailAttachmentLayoutQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("EmailAttachmentLayout query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addTaskScheduleInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("TaskScheduleInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addTaskSentInfoQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("TaskSentInfo query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPatientQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("Patient query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addDoctorQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("Doctor query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addHospitalQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("Hospital query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addContactUsQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("ContactUs query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addCustomerEmailVerificationLayoutQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("CustomerEmailVerificationLayoutQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addCustomerEmailVerificationTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("CustomerEmailVerificationTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerEmailVerificationLayoutQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerEmailVerificationLayoutQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerEmailVerificationTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerEmailVerificationTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addServiceRequestQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("ServiceRequestQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addCustomerOnlineConsultationTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("CustomerOnlineConsultationTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerAssignedQueriesQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerAssignedQueriesQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerOnlineConsultationAssignedQueriesTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerOnlineConsultationAssignedQueriesTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAdminApprovedReplyTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AdminApprovedReplyTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addCustomerFeedbackTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("CustomerFeedbackTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerReplyNotificationQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerReplyNotificationQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerReplyNotificationTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerReplyNotificationTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerAssignedQueriesTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerAssignedQueriesTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addAstrologerAvailabilityConfigurationQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("AstrologerAvailabilityConfiguration query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addNewServiceRequestNotificationToAdminLayoutTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("NewServiceRequestNotificationToAdminLayoutTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addServiceRequestTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("ServiceRequestTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addIncomingPaymentsQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("IncomingPayments query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesReportQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesReport query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPayoutsQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("Payouts query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesReceivedCountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesReceivedCountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesReceivedConsultationAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesReceivedConsultationAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesRespondedCountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesRespondedCountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesRespondedConsultationAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesRespondedConsultationAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesPendingCountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesPendingCountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesPendingCounsulationAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesPendingCounsulationAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesPaymentFailedCountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesPaymentFailedCountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addQueriesPaymentFailedCounsulationAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("QueriesPaymentFailedCounsulationAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentsToAstrologerPayableAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentsToAstrologerPayableAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentsToAstrologerAmountTransferredQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentsToAstrologerAmountTransferredQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentToOrganisationPayableAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentToOrganisationPayableAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentToOrganisationAmountTransferredQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentToOrganisationAmountTransferredQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentToServiceProviderPayableAmountQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentToServiceProviderPayableAmountQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addPaymentToServiceProviderAmountTransferredQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("PaymentToServiceProviderAmountTransferredQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addCustomerPaymentFaildedNotificationTaskQueryQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("CustomerPaymentFaildedNotificationTaskQuery query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			
			isQueryAdded = addServiceRequestReportQueryInfo(organisationSession);
			if(isQueryAdded ==0)
			{
				System.err.println("ServiceRequestReport query info could not be added.");
				if (tx != null)
					tx.rollback();
				return 0;	
			}			

			System.out.println("Queries are saved successfully.");		
			tx.commit();
			return 1;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(QueriesDataLoader.class, e);
			logger.error(CommonUtil.getStackTrace(e));
			logger.debug("Notification info could not be processed.");
		}	
		finally
		{
			organisationSession.close(); 	
		}
		return 0;			
	}
		private static int addOrganisationsQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("Organisations");
		queryInfo.setQueryName("Organisations");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Organisations");
		queryTableInfo.setQueryTableAlias("o");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.organisationsId");
		queryColumnInfo.setQueryColumnAlias("o_organisationsId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.organisationName");
		queryColumnInfo.setQueryColumnAlias("o_organisationName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.addressLine1");
		queryColumnInfo.setQueryColumnAlias("o_addressLine1");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.addressLine2");
		queryColumnInfo.setQueryColumnAlias("o_addressLine2");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.city");
		queryColumnInfo.setQueryColumnAlias("o_city");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.residentState");
		queryColumnInfo.setQueryColumnAlias("o_residentState");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.pinCode");
		queryColumnInfo.setQueryColumnAlias("o_pinCode");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.databaseName");
		queryColumnInfo.setQueryColumnAlias("o_databaseName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("o.country");
		queryColumnInfo.setQueryColumnAlias("o_country");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addUserInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("UserInfo");
		queryInfo.setQueryName("UserInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("UserInfo");
		queryTableInfo.setQueryTableAlias("ui");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userInfoId");
		queryColumnInfo.setQueryColumnAlias("ui_userInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userFirstName");
		queryColumnInfo.setQueryColumnAlias("ui_userFirstName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userLastName");
		queryColumnInfo.setQueryColumnAlias("ui_userLastName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull((select organisationName from Organisations  where organisationsId = ui.organisationsId), 'Not Found')");
		queryColumnInfo.setQueryColumnAlias("ui_organisationsUserOrgDisplayText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.organisationsUserOrgId");
		queryColumnInfo.setQueryColumnAlias("ui_organisationsUserOrgId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.loginId");
		queryColumnInfo.setQueryColumnAlias("ui_loginId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.loginEmailId");
		queryColumnInfo.setQueryColumnAlias("ui_loginEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.contactNo");
		queryColumnInfo.setQueryColumnAlias("ui_contactNo");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.loginPassword");
		queryColumnInfo.setQueryColumnAlias("ui_loginPassword");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.resetToken");
		queryColumnInfo.setQueryColumnAlias("ui_resetToken");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPrivilegeGroupQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PrivilegeGroup");
		queryInfo.setQueryName("PrivilegeGroup");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("PrivilegeGroup");
		queryTableInfo.setQueryTableAlias("pg");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.privilegeGroupId");
		queryColumnInfo.setQueryColumnAlias("pg_privilegeGroupId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.privilegeGroupName");
		queryColumnInfo.setQueryColumnAlias("pg_privilegeGroupName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.privilegeCode");
		queryColumnInfo.setQueryColumnAlias("pg_privilegeCode");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.applicableUserType");
		queryColumnInfo.setQueryColumnAlias("pg_applicableUserType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.selfServiceUser");
		queryColumnInfo.setQueryColumnAlias("pg_selfServiceUser");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pg.privilegeGroupDescription");
		queryColumnInfo.setQueryColumnAlias("pg_privilegeGroupDescription");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPrivilegeGroupItemsQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PrivilegeGroupItems");
		queryInfo.setQueryName("PrivilegeGroupItems");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("PrivilegeGroupItems");
		queryTableInfo.setQueryTableAlias("pgi");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pgi.privilegeGroupItemsId");
		queryColumnInfo.setQueryColumnAlias("pgi_privilegeGroupItemsId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pgi.privilegeGroupId");
		queryColumnInfo.setQueryColumnAlias("pgi_privilegeGroupId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pgi.privilegeActionType");
		queryColumnInfo.setQueryColumnAlias("pgi_privilegeActionType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pgi.privilegeObjectType");
		queryColumnInfo.setQueryColumnAlias("pgi_privilegeObjectType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("pgi.privilegeObjectName");
		queryColumnInfo.setQueryColumnAlias("pgi_privilegeObjectName");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addEmployeeRolesQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("EmployeeRoles");
		queryInfo.setQueryName("EmployeeRoles");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("EmployeeRoles");
		queryTableInfo.setQueryTableAlias("er");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("er.employeeRolesId");
		queryColumnInfo.setQueryColumnAlias("er_employeeRolesId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("er.userInfoId");
		queryColumnInfo.setQueryColumnAlias("er_userInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull((select privilegeGroupName from PrivilegeGroup  where privilegeGroupId = er.privilegeGroupId), 'Not Found')");
		queryColumnInfo.setQueryColumnAlias("er_privilegeGroupDisplayText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("er.privilegeGroupId");
		queryColumnInfo.setQueryColumnAlias("er_privilegeGroupId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("er.description");
		queryColumnInfo.setQueryColumnAlias("er_description");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addLoginSessionInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("LoginSessionInfo");
		queryInfo.setQueryName("LoginSessionInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("LoginSessionInfo");
		queryTableInfo.setQueryTableAlias("lsi");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.loginSessionInfoId");
		queryColumnInfo.setQueryColumnAlias("lsi_loginSessionInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.loginUserType");
		queryColumnInfo.setQueryColumnAlias("lsi_loginUserType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.selfServiceUserType");
		queryColumnInfo.setQueryColumnAlias("lsi_selfServiceUserType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.sessionId");
		queryColumnInfo.setQueryColumnAlias("lsi_sessionId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.userId");
		queryColumnInfo.setQueryColumnAlias("lsi_userId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("lsi.loginTime");
		queryColumnInfo.setQueryColumnAlias("lsi_loginTime");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addConfigPropertiesQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("ConfigProperties");
		queryInfo.setQueryName("ConfigProperties");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ConfigProperties");
		queryTableInfo.setQueryTableAlias("cp");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cp.configPropertiesId");
		queryColumnInfo.setQueryColumnAlias("cp_configPropertiesId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cp.propertyName");
		queryColumnInfo.setQueryColumnAlias("cp_propertyName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cp.propertyValue");
		queryColumnInfo.setQueryColumnAlias("cp_propertyValue");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addTaskInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("TaskInfo");
		queryInfo.setQueryName("TaskInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TaskInfo");
		queryTableInfo.setQueryTableAlias("ti");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("ti_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskName");
		queryColumnInfo.setQueryColumnAlias("ti_taskName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskDescription");
		queryColumnInfo.setQueryColumnAlias("ti_taskDescription");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskType");
		queryColumnInfo.setQueryColumnAlias("ti_taskType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.apiName");
		queryColumnInfo.setQueryColumnAlias("ti_apiName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.targetEntityQuery");
		queryColumnInfo.setQueryColumnAlias("ti_targetEntityQuery");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.targetUserIdColumnAlias");
		queryColumnInfo.setQueryColumnAlias("ti_targetUserIdColumnAlias");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.targetEntityIdColumnAlias");
		queryColumnInfo.setQueryColumnAlias("ti_targetEntityIdColumnAlias");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.enableInAppNotification");
		queryColumnInfo.setQueryColumnAlias("ti_enableInAppNotification");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.inAppNotificationLayout");
		queryColumnInfo.setQueryColumnAlias("ti_inAppNotificationLayout");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.enableEmailNotification");
		queryColumnInfo.setQueryColumnAlias("ti_enableEmailNotification");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.emailColumnAlias");
		queryColumnInfo.setQueryColumnAlias("ti_emailColumnAlias");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.emailNotificationLayout");
		queryColumnInfo.setQueryColumnAlias("ti_emailNotificationLayout");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.emailSubject");
		queryColumnInfo.setQueryColumnAlias("ti_emailSubject");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.enableSmsNotification");
		queryColumnInfo.setQueryColumnAlias("ti_enableSmsNotification");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.smsColumnAlias");
		queryColumnInfo.setQueryColumnAlias("ti_smsColumnAlias");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.smsNotificationLayout");
		queryColumnInfo.setQueryColumnAlias("ti_smsNotificationLayout");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.sMSText");
		queryColumnInfo.setQueryColumnAlias("ti_sMSText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.isActive");
		queryColumnInfo.setQueryColumnAlias("ti_isActive");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskStartDate");
		queryColumnInfo.setQueryColumnAlias("ti_taskStartDate");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskFrequency");
		queryColumnInfo.setQueryColumnAlias("ti_taskFrequency");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.taskFrequencyUnit");
		queryColumnInfo.setQueryColumnAlias("ti_taskFrequencyUnit");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.isRecurring");
		queryColumnInfo.setQueryColumnAlias("ti_isRecurring");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunType");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.dateColumnAlias");
		queryColumnInfo.setQueryColumnAlias("ti_dateColumnAlias");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateCalculationMethod");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateCalculationMethod");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInYears");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInYears");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInMonths");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInMonths");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInDays");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInDays");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInHours");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInHours");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInMinutes");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInMinutes");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ti.firstRunDateGapInSeconds");
		queryColumnInfo.setQueryColumnAlias("ti_firstRunDateGapInSeconds");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addTaskExecutionInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("TaskExecutionInfo");
		queryInfo.setQueryName("TaskExecutionInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TaskExecutionInfo");
		queryTableInfo.setQueryTableAlias("tei");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.taskExecutionInfoId");
		queryColumnInfo.setQueryColumnAlias("tei_taskExecutionInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("tei_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.taskTimeCalculationType");
		queryColumnInfo.setQueryColumnAlias("tei_taskTimeCalculationType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateCalculationMethod");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateCalculationMethod");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInYears");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInYears");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInMonths");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInMonths");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInDays");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInDays");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInHours");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInHours");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInMinutes");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInMinutes");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tei.firstRunDateGapInSeconds");
		queryColumnInfo.setQueryColumnAlias("tei_firstRunDateGapInSeconds");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addTaskLayoutParametersQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("TaskLayoutParameters");
		queryInfo.setQueryName("TaskLayoutParameters");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TaskLayoutParameters");
		queryTableInfo.setQueryTableAlias("tlp");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tlp.taskLayoutParametersId");
		queryColumnInfo.setQueryColumnAlias("tlp_taskLayoutParametersId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tlp.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("tlp_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tlp.parameterName");
		queryColumnInfo.setQueryColumnAlias("tlp_parameterName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tlp.parameterValueType");
		queryColumnInfo.setQueryColumnAlias("tlp_parameterValueType");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tlp.parameterValue");
		queryColumnInfo.setQueryColumnAlias("tlp_parameterValue");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addEmailAttachmentLayoutQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("EmailAttachmentLayout");
		queryInfo.setQueryName("EmailAttachmentLayout");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("EmailAttachmentLayout");
		queryTableInfo.setQueryTableAlias("eal");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("eal.emailAttachmentLayoutId");
		queryColumnInfo.setQueryColumnAlias("eal_emailAttachmentLayoutId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("eal.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("eal_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("eal.emailAttachmentLayoutName");
		queryColumnInfo.setQueryColumnAlias("eal_emailAttachmentLayoutName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("eal.comments");
		queryColumnInfo.setQueryColumnAlias("eal_comments");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addTaskScheduleInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("TaskScheduleInfo");
		queryInfo.setQueryName("TaskScheduleInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TaskScheduleInfo");
		queryTableInfo.setQueryTableAlias("tsi");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.taskScheduleInfoId");
		queryColumnInfo.setQueryColumnAlias("tsi_taskScheduleInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull((select taskName from TaskInfo  where taskInfoId = tsi.taskInfoId), 'Not Found')");
		queryColumnInfo.setQueryColumnAlias("tsi_taskInfoDisplayText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("tsi_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.targetEntityId");
		queryColumnInfo.setQueryColumnAlias("tsi_targetEntityId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.targetUserId");
		queryColumnInfo.setQueryColumnAlias("tsi_targetUserId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.notificationMedium");
		queryColumnInfo.setQueryColumnAlias("tsi_notificationMedium");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.notificationLastSentTime");
		queryColumnInfo.setQueryColumnAlias("tsi_notificationLastSentTime");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.nextNotificationTime");
		queryColumnInfo.setQueryColumnAlias("tsi_nextNotificationTime");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addTaskSentInfoQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("TaskSentInfo");
		queryInfo.setQueryName("TaskSentInfo");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TaskSentInfo");
		queryTableInfo.setQueryTableAlias("tsi");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.taskSentInfoId");
		queryColumnInfo.setQueryColumnAlias("tsi_taskSentInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull((select taskName from TaskInfo  where taskInfoId = tsi.taskInfoId), 'Not Found')");
		queryColumnInfo.setQueryColumnAlias("tsi_taskInfoDisplayText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.taskInfoId");
		queryColumnInfo.setQueryColumnAlias("tsi_taskInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.targetEntityId");
		queryColumnInfo.setQueryColumnAlias("tsi_targetEntityId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.targetUserId");
		queryColumnInfo.setQueryColumnAlias("tsi_targetUserId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.notificationMedium");
		queryColumnInfo.setQueryColumnAlias("tsi_notificationMedium");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.layoutInfoText");
		queryColumnInfo.setQueryColumnAlias("tsi_layoutInfoText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("tsi.notificationSentTime");
		queryColumnInfo.setQueryColumnAlias("tsi_notificationSentTime");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPatientQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("Patient");
		queryInfo.setQueryName("Patient");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Patient");
		queryTableInfo.setQueryTableAlias("p");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("p.patientId");
		queryColumnInfo.setQueryColumnAlias("p_patientId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("p.doctorId");
		queryColumnInfo.setQueryColumnAlias("p_doctorId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("p.patientName");
		queryColumnInfo.setQueryColumnAlias("p_patientName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("p.patientGender");
		queryColumnInfo.setQueryColumnAlias("p_patientGender");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("p.selectDoctor");
		queryColumnInfo.setQueryColumnAlias("p_selectDoctor");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addDoctorQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("Doctor");
		queryInfo.setQueryName("Doctor");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Doctor");
		queryTableInfo.setQueryTableAlias("d");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("d.doctorId");
		queryColumnInfo.setQueryColumnAlias("d_doctorId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("d.doctorName");
		queryColumnInfo.setQueryColumnAlias("d_doctorName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("d.hospitalName");
		queryColumnInfo.setQueryColumnAlias("d_hospitalName");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addHospitalQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("Hospital");
		queryInfo.setQueryName("Hospital");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Hospital");
		queryTableInfo.setQueryTableAlias("h");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("h.hospitalId");
		queryColumnInfo.setQueryColumnAlias("h_hospitalId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("h.hospName");
		queryColumnInfo.setQueryColumnAlias("h_hospName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("h.hospAddress");
		queryColumnInfo.setQueryColumnAlias("h_hospAddress");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addContactUsQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("ContactUs");
		queryInfo.setQueryName("ContactUs");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" Where 2>1 ");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ContactUs");
		queryTableInfo.setQueryTableAlias("cu");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cu.contactUsId");
		queryColumnInfo.setQueryColumnAlias("cu_contactUsId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cu.fullName");
		queryColumnInfo.setQueryColumnAlias("cu_fullName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cu.emailId");
		queryColumnInfo.setQueryColumnAlias("cu_emailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("cu.contactNo");
		queryColumnInfo.setQueryColumnAlias("cu_contactNo");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addCustomerEmailVerificationLayoutQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("CustomerEmailVerificationLayoutQuery");
		queryInfo.setQueryName("CustomerEmailVerificationLayoutQuery");
		queryInfo.setUseNativeQuery("No");
		queryInfo.setQueryWhereClause(" where  2>1 and c.customerId = ?");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Customer");
		queryTableInfo.setQueryTableAlias("c");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.firstName");
		queryColumnInfo.setQueryColumnAlias("c_firstName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.loginEmailId");
		queryColumnInfo.setQueryColumnAlias("c_loginEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.loginContactNo");
		queryColumnInfo.setQueryColumnAlias("c_loginContactNo");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'DomainURL')");
		queryColumnInfo.setQueryColumnAlias("DomainURL");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.verificationToken");
		queryColumnInfo.setQueryColumnAlias("c_verificationToken");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.resetToken");
		queryColumnInfo.setQueryColumnAlias("c_resetToken");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addCustomerEmailVerificationTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("CustomerEmailVerificationTaskQuery");
		queryInfo.setQueryName("CustomerEmailVerificationTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND c.isEmailIdVerified = 'No'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Customer");
		queryTableInfo.setQueryTableAlias("c");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.customerId");
		queryColumnInfo.setQueryColumnAlias("c_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.loginEmailId");
		queryColumnInfo.setQueryColumnAlias("c_loginEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("c.loginContactNo");
		queryColumnInfo.setQueryColumnAlias("c_loginContactNo");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerEmailVerificationLayoutQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerEmailVerificationLayoutQuery");
		queryInfo.setQueryName("AstrologerEmailVerificationLayoutQuery");
		queryInfo.setUseNativeQuery("No");
		queryInfo.setQueryWhereClause(" where  2>1 and a.astrologerId = ?");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Astrologer");
		queryTableInfo.setQueryTableAlias("a");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.firstName");
		queryColumnInfo.setQueryColumnAlias("a_astrologerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.loginEmailId");
		queryColumnInfo.setQueryColumnAlias("a_loginEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.loginContactNo");
		queryColumnInfo.setQueryColumnAlias("a_loginContactNo");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'DomainURL')");
		queryColumnInfo.setQueryColumnAlias("DomainURL");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.verificationToken");
		queryColumnInfo.setQueryColumnAlias("a_verificationToken");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.resetToken");
		queryColumnInfo.setQueryColumnAlias("a_resetToken");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerEmailVerificationTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerEmailVerificationTaskQuery");
		queryInfo.setQueryName("AstrologerEmailVerificationTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND a.isEmailIdVerified = 'No'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("Astrologer");
		queryTableInfo.setQueryTableAlias("a");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.astrologerId");
		queryColumnInfo.setQueryColumnAlias("a_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.loginEmailId");
		queryColumnInfo.setQueryColumnAlias("a_loginEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("a.loginContactNo");
		queryColumnInfo.setQueryColumnAlias("a_loginContactNo");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addServiceRequestQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("ServiceRequestQuery");
		queryInfo.setQueryName("ServiceRequestQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and serviceRequestId = ?");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.customerId");
		queryColumnInfo.setQueryColumnAlias("sr_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(concat(firstName, ' ', surName))");
		queryColumnInfo.setQueryColumnAlias("customerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select firstName  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.queryText");
		queryColumnInfo.setQueryColumnAlias("sr_queryText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.backgroundText");
		queryColumnInfo.setQueryColumnAlias("sr_backgroundText");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((SELECT count(*) FROM ConfigProperties WHERE propertyName = 'OnlineConsultationLink') = 1, (SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'OnlineConsultationLink'), 'No Link Found')");
		queryColumnInfo.setQueryColumnAlias("onlineConsultationConsultationLink");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((SELECT count(*) FROM ConfigProperties WHERE propertyName = 'DomainURL') = 1, (SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'DomainURL'), 'No Link Found')");
		queryColumnInfo.setQueryColumnAlias("DomainURL");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.feedback");
		queryColumnInfo.setQueryColumnAlias("sr_feedback");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addCustomerOnlineConsultationTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("CustomerOnlineConsultationTaskQuery");
		queryInfo.setQueryName("CustomerOnlineConsultationTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.consultationType = 'ONLINE' and sr.isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.customerId");
		queryColumnInfo.setQueryColumnAlias("sr_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.emailId");
		queryColumnInfo.setQueryColumnAlias("sr_emailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(concat(sr.consultationDate, ' ', if(consultationTimeSlotId > 0, (select startTime from TimeSlot  where timeSlotId =sr.consultationTimeSlotId),'00?00?00')))");
		queryColumnInfo.setQueryColumnAlias("consultationDateTime");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerAssignedQueriesQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerAssignedQueriesQuery");
		queryInfo.setQueryName("AstrologerAssignedQueriesQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and serviceRequestId = ?");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select firstName astrologerName from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((SELECT count(*) FROM ConfigProperties WHERE propertyName = 'OnlineConsultationLink') = 1, (SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'OnlineConsultationLink'), 'No Link Found')");
		queryColumnInfo.setQueryColumnAlias("onlineConsultationConsultationLink");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((SELECT count(*) FROM ConfigProperties WHERE propertyName = 'DomainURL') = 1, (SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'DomainURL'), 'No Link Found')");
		queryColumnInfo.setQueryColumnAlias("DomainURL");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.queryText");
		queryColumnInfo.setQueryColumnAlias("sr_queryText");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerOnlineConsultationAssignedQueriesTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerOnlineConsultationAssignedQueriesTaskQuery");
		queryInfo.setQueryName("AstrologerOnlineConsultationAssignedQueriesTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.consultationType = 'ONLINE' and sr.isPaymentAmountReceived = 1 and sr.astrologerId is not null and sr.astrologerId != -1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginEmailId  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("sr_emailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.cellNo");
		queryColumnInfo.setQueryColumnAlias("sr_cellNo");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(concat(sr.consultationDate, ' ', if(consultationTimeSlotId > 0, (select startTime from TimeSlot where timeSlotId =sr.consultationTimeSlotId),'00?00?00')))");
		queryColumnInfo.setQueryColumnAlias("consultationDateTime");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAdminApprovedReplyTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AdminApprovedReplyTaskQuery");
		queryInfo.setQueryName("AdminApprovedReplyTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.hasAdminApprovedReply = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginEmailId  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginContactNo  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerContactNo");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.customerId");
		queryColumnInfo.setQueryColumnAlias("sr_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.emailId");
		queryColumnInfo.setQueryColumnAlias("sr_customerEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.cellNo");
		queryColumnInfo.setQueryColumnAlias("sr_customerCellNo");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addCustomerFeedbackTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("CustomerFeedbackTaskQuery");
		queryInfo.setQueryName("CustomerFeedbackTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.hasCustomerUpdatedFeedback = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginEmailId  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginContactNo  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerContactNo");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerReplyNotificationQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerReplyNotificationQuery");
		queryInfo.setQueryName("AstrologerReplyNotificationQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and ui.userInfoId = ? and sr.serviceRequestId = ?");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("UserInfo");
		queryTableInfo.setQueryTableAlias("ui");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userInfoId");
		queryColumnInfo.setQueryColumnAlias("ui_userInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(concat(userFirstName, ' ', userLastName))");
		queryColumnInfo.setQueryColumnAlias("adminName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select firstName astrologerName from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("astrologerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((SELECT count(*) FROM ConfigProperties WHERE propertyName = 'DomainURL') = 1, (SELECT propertyValue FROM ConfigProperties WHERE propertyName = 'DomainURL'), 'No Link Found')");
		queryColumnInfo.setQueryColumnAlias("DomainURL");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.queryText");
		queryColumnInfo.setQueryColumnAlias("sr_queryText");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerReplyNotificationTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerReplyNotificationTaskQuery");
		queryInfo.setQueryName("AstrologerReplyNotificationTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and hasAstrologerReplied = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("UserInfo");
		queryTableInfo.setQueryTableAlias("ui");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.loginId");
		queryColumnInfo.setQueryColumnAlias("adminEmailId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userInfoId");
		queryColumnInfo.setQueryColumnAlias("ui_userInfoId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerAssignedQueriesTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerAssignedQueriesTaskQuery");
		queryInfo.setQueryName("AstrologerAssignedQueriesTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 1 and sr.astrologerId is not null and sr.astrologerId != -1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.astrologerId");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select loginEmailId  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("sr_emailId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addAstrologerAvailabilityConfigurationQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("AstrologerAvailabilityConfiguration");
		queryInfo.setQueryName("AstrologerAvailabilityConfiguration");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("TimeSlot");
		queryTableInfo.setQueryTableAlias("ts");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ts.timeSlotId");
		queryColumnInfo.setQueryColumnAlias("ts_timeSlotId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("concat(ts.startTime , ' - ' , ts.endTime)");
		queryColumnInfo.setQueryColumnAlias("slotTime");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((ifnull((SELECT aa.astrologerAvailabilityId FROM AstrologerAvailability aa WHERE 2 > 1 and  aa.astrologerId = ? and  aa.consultationDate =  date_format(STR_TO_DATE(?, '%d/%m/%Y'), '%Y-%m-%d')  and  aa.timeSlotId = ts.timeSlotId), 'Yes')='Yes'), 'No', 'Yes')");
		queryColumnInfo.setQueryColumnAlias("astrologerAvailability");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("if((ifnull((SELECT aa.serviceRequestId FROM AstrologerAvailability aa WHERE 2 > 1 and  aa.astrologerId = ? and  aa.consultationDate =  date_format(STR_TO_DATE(?, '%d/%m/%Y'), '%Y-%m-%d')  and  aa.timeSlotId = ts.timeSlotId), -1)=-1), 'Open', 'Booked')");
		queryColumnInfo.setQueryColumnAlias("slotAvailability");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(if((ifnull((SELECT aa.astrologerAvailabilityId FROM AstrologerAvailability aa WHERE 2 > 1 and  aa.astrologerId = ? and  aa.consultationDate =  date_format(STR_TO_DATE(?, '%d/%m/%Y'), '%Y-%m-%d')  and  aa.timeSlotId = ts.timeSlotId), 'Yes')='Yes'), -1, (SELECT aa.serviceRequestId FROM AstrologerAvailability aa WHERE 2 > 1 and  aa.astrologerId = ? and  aa.consultationDate =  date_format(STR_TO_DATE(?, '%d/%m/%Y'), '%Y-%m-%d')  and  aa.timeSlotId = ts.timeSlotId)), -1)");
		queryColumnInfo.setQueryColumnAlias("serviceRequestId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addNewServiceRequestNotificationToAdminLayoutTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("NewServiceRequestNotificationToAdminLayoutTaskQuery");
		queryInfo.setQueryName("NewServiceRequestNotificationToAdminLayoutTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("UserInfo");
		queryTableInfo.setQueryTableAlias("ui");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.userInfoId");
		queryColumnInfo.setQueryColumnAlias("ui_userInfoId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(concat(userFirstName, '_', userLastName))");
		queryColumnInfo.setQueryColumnAlias("adminName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ui.loginId");
		queryColumnInfo.setQueryColumnAlias("ui_loginId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addServiceRequestTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("ServiceRequestTaskQuery");
		queryInfo.setQueryName("ServiceRequestTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.customerId");
		queryColumnInfo.setQueryColumnAlias("sr_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.emailId");
		queryColumnInfo.setQueryColumnAlias("sr_emailId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addIncomingPaymentsQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("IncomingPayments");
		queryInfo.setQueryName("IncomingPayments");
		queryInfo.setUseNativeQuery("No");
		queryInfo.setQueryWhereClause(" where  2>1 and isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("group by date(sr.vwCreationDate)");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("DATE_FORMAT(sr.vwCreationDate, '%d/%m/%Y')");
		queryColumnInfo.setQueryColumnAlias("creationDate");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sum(sr.amountPayable)");
		queryColumnInfo.setQueryColumnAlias("dayAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesReportQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesReport");
		queryInfo.setQueryName("QueriesReport");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("(select DATE_FORMAT(DATE_ADD(STR_TO_DATE(?,'%d/%m/%Y'), INTERVAL (plqt.h * 100 + plqt.t * 10 + plqt.u + 1) - 1 DAY), '%d/%m/%Y') dates from  (SELECT (h * 100 + t * 10 + u + 1) serialNo, h, t, u FROM (SELECT 0 h UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) A, (SELECT 0 t UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) B, (SELECT 0 u UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) C where (h * 100 + t * 10 + u + 1) <= (datediff(STR_TO_DATE(?, '%d/%m/%Y'), STR_TO_DATE(?, '%d/%m/%Y')) + 1) ORDER BY serialNo) as plqt) as");
		queryTableInfo.setQueryTableAlias("datesListTable");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("datesListTable.dates");
		queryColumnInfo.setQueryColumnAlias("datesListTable_dates");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select count(*) from ServiceRequest sr where 2>1  and date(sr.vwCreationDate) =  date(STR_TO_DATE(datesListTable.dates,'%d/%m/%Y')) and sr.isPaymentAmountReceived = 1)");
		queryColumnInfo.setQueryColumnAlias("newQueriesCount");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select count(*) from ServiceRequest sr where 2>1  and date(sr.adminApprovalTime) =  date(STR_TO_DATE(datesListTable.dates,'%d/%m/%Y')) and sr.isPaymentAmountReceived = 1 and sr.hasAdminApprovedReply = 'Yes' and sr.hasAstrologerReplied = 'Yes')");
		queryColumnInfo.setQueryColumnAlias("solvedQueriesCount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPayoutsQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("Payouts");
		queryInfo.setQueryName("Payouts");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("(select DATE_FORMAT(DATE_ADD(STR_TO_DATE(?,'%d/%m/%Y'), INTERVAL (plqt.h * 100 + plqt.t * 10 + plqt.u + 1) - 1 DAY), '%d/%m/%Y') dates from  (SELECT (h * 100 + t * 10 + u + 1) serialNo, h, t, u FROM (SELECT 0 h UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) A, (SELECT 0 t UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) B, (SELECT 0 u UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) C where (h * 100 + t * 10 + u + 1) <= (datediff(STR_TO_DATE(?, '%d/%m/%Y'), STR_TO_DATE(?, '%d/%m/%Y')) + 1) ORDER BY serialNo) as plqt) as");
		queryTableInfo.setQueryTableAlias("datesListTable");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("datesListTable.dates");
		queryColumnInfo.setQueryColumnAlias("datesListTable_dates");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select sum(astrologerFeesAmount) from ServiceRequest sr where 2>1  and date(sr.adminApprovalTime) =  date(STR_TO_DATE(datesListTable.dates,'%d/%m/%Y')) and sr.isPaymentAmountReceived = 1 and sr.hasAdminApprovedReply = 'Yes' and sr.hasAstrologerReplied = 'Yes')");
		queryColumnInfo.setQueryColumnAlias("payableAmountToAstrologer");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select sum(organisationCommissionAmount) from ServiceRequest sr where 2>1  and date(sr.adminApprovalTime) =  date(STR_TO_DATE(datesListTable.dates,'%d/%m/%Y')) and sr.isPaymentAmountReceived = 1 and sr.hasAdminApprovedReply = 'Yes' and sr.hasAstrologerReplied = 'Yes')");
		queryColumnInfo.setQueryColumnAlias("payableAmountToOrganisation");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select sum(serviceProviderCommissionAmount) from ServiceRequest sr where 2>1  and date(sr.adminApprovalTime) =  date(STR_TO_DATE(datesListTable.dates,'%d/%m/%Y')) and sr.isPaymentAmountReceived = 1 and sr.hasAdminApprovedReply = 'Yes' and sr.hasAstrologerReplied = 'Yes')");
		queryColumnInfo.setQueryColumnAlias("payableAmountToServiceProvider");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesReceivedCountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesReceivedCountQuery");
		queryInfo.setQueryName("QueriesReceivedCountQuery");
		queryInfo.setUseNativeQuery("No");
		queryInfo.setQueryWhereClause(" where  2>1 and isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("count(*)");
		queryColumnInfo.setQueryColumnAlias("queriesReceivedCount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesReceivedConsultationAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesReceivedConsultationAmountQuery");
		queryInfo.setQueryName("QueriesReceivedConsultationAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(SUM(amountPayable), 0)");
		queryColumnInfo.setQueryColumnAlias("queriesReceivedConsultationAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesRespondedCountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesRespondedCountQuery");
		queryInfo.setQueryName("QueriesRespondedCountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 1 and sr.hasAstrologerReplied = 'Yes' and sr.hasAdminApprovedReply = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("count(*)");
		queryColumnInfo.setQueryColumnAlias("queriesRespondedCount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesRespondedConsultationAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesRespondedConsultationAmountQuery");
		queryInfo.setQueryName("QueriesRespondedConsultationAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 1 and sr.hasAstrologerReplied = 'Yes' and sr.hasAdminApprovedReply = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(SUM(amountPayable), 0)");
		queryColumnInfo.setQueryColumnAlias("queriesRespondedConsultationAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesPendingCountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesPendingCountQuery");
		queryInfo.setQueryName("QueriesPendingCountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 1 and sr.hasAdminApprovedReply = 'No'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("count(*)");
		queryColumnInfo.setQueryColumnAlias("queriesPendingCount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesPendingCounsulationAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesPendingCounsulationAmountQuery");
		queryInfo.setQueryName("QueriesPendingCounsulationAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND sr.isPaymentAmountReceived = 1 AND sr.hasAdminApprovedReply = 'No'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(SUM(amountPayable), 0)");
		queryColumnInfo.setQueryColumnAlias("queriesPendingConsultationAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesPaymentFailedCountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesPaymentFailedCountQuery");
		queryInfo.setQueryName("QueriesPaymentFailedCountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 0");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("count(*)");
		queryColumnInfo.setQueryColumnAlias("queriesPaymentFailedCount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addQueriesPaymentFailedCounsulationAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("QueriesPaymentFailedCounsulationAmountQuery");
		queryInfo.setQueryName("QueriesPaymentFailedCounsulationAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND sr.isPaymentAmountReceived = 0");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(SUM(amountPayable), 0)");
		queryColumnInfo.setQueryColumnAlias("queriesPaymentFailedConsultationAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentsToAstrologerPayableAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentsToAstrologerPayableAmountQuery");
		queryInfo.setQueryName("PaymentsToAstrologerPayableAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND sr.isPaymentAmountReceived = 1 AND sr.astrologerId is not null AND sr.astrologerId != -1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(astrologerFeesAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentsToAstrologerPayableAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentsToAstrologerAmountTransferredQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentsToAstrologerAmountTransferredQuery");
		queryInfo.setQueryName("PaymentsToAstrologerAmountTransferredQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 and pa.paymentToAstrologerId = sr.paymentToAstrologerId AND sr.isPaymentAmountReceived = 1 and pa.isPaymentTransferCompleted = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("PaymenttoAstrologer");
		queryTableInfo.setQueryTableAlias("pa");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(astrologerFeesAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentsToAstrologerAmountTransferred");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentToOrganisationPayableAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentToOrganisationPayableAmountQuery");
		queryInfo.setQueryName("PaymentToOrganisationPayableAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND sr.isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(organisationCommissionAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentToOrganisationPayableAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentToOrganisationAmountTransferredQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentToOrganisationAmountTransferredQuery");
		queryInfo.setQueryName("PaymentToOrganisationAmountTransferredQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 and po.paymentToOrganisationId = sr.paymentToOrganisationId AND sr.isPaymentAmountReceived = 1 and po.isPaymentTransferCompleted = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("PaymenttoOrganisation");
		queryTableInfo.setQueryTableAlias("po");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(sr.organisationCommissionAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentToOrganisationAmountTransferred");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentToServiceProviderPayableAmountQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentToServiceProviderPayableAmountQuery");
		queryInfo.setQueryName("PaymentToServiceProviderPayableAmountQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 AND sr.isPaymentAmountReceived = 1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(serviceProviderCommissionAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentToServiceProviderPayableAmount");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addPaymentToServiceProviderAmountTransferredQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("PaymentToServiceProviderAmountTransferredQuery");
		queryInfo.setQueryName("PaymentToServiceProviderAmountTransferredQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2 > 1 and psp.paymentToServiceProviderId = sr.paymentToServiceProviderId AND sr.isPaymentAmountReceived = 1 and psp.isPaymentTransferCompleted = 'Yes'");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);
		queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("PaymenttoServiceProvider");
		queryTableInfo.setQueryTableAlias("psp");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("ifnull(sum(sr.serviceProviderCommissionAmount), 0)");
		queryColumnInfo.setQueryColumnAlias("paymentToServiceProviderAmountTransferred");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addCustomerPaymentFaildedNotificationTaskQueryQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("CustomerPaymentFaildedNotificationTaskQuery");
		queryInfo.setQueryName("CustomerPaymentFaildedNotificationTaskQuery");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1 and sr.isPaymentAmountReceived = 0 and timestamp(date_add(sr.vwCreationDate, interval 20 minute)) <= timestamp(now())");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.serviceRequestId");
		queryColumnInfo.setQueryColumnAlias("sr_serviceRequestId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.customerId");
		queryColumnInfo.setQueryColumnAlias("sr_customerId");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("sr.emailId");
		queryColumnInfo.setQueryColumnAlias("sr_emailId");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}
	private static int addServiceRequestReportQueryInfo(Session organisationSession)throws Exception
	{
		QueryTableInfo queryTableInfo;
		QueryInfo queryInfo;
		QueryColumnInfo queryColumnInfo;
		List<QueryTableInfo> queryTablesList = new ArrayList<QueryTableInfo>();
		List<QueryColumnInfo> queryColumnsList = new ArrayList<QueryColumnInfo>();	
		QueriesDataLoaderUtil queryLoader = new QueriesDataLoaderUtil();
		queryInfo = new QueryInfo();		
		queryInfo.setQueryCode("ServiceRequestReport");
		queryInfo.setQueryName("ServiceRequestReport");
		queryInfo.setUseNativeQuery("Yes");
		queryInfo.setQueryWhereClause(" where  2>1");
		queryInfo.setGroupByClause("");
		queryInfo.setOrderByClause("");
		queryInfo.setLimitClause("");
				queryTableInfo = new QueryTableInfo();
		queryTableInfo.setQueryTableName("ServiceRequest");
		queryTableInfo.setQueryTableAlias("sr");	
		queryTablesList.add(queryTableInfo);

				queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select firstName  from Customer where 2>1 and customerId = sr.customerId)");
		queryColumnInfo.setQueryColumnAlias("sr_cusromerName");
		queryColumnsList.add(queryColumnInfo);
		queryColumnInfo = new QueryColumnInfo();
		queryColumnInfo.setQueryColumnText("(select firstName  from Astrologer where 2>1 and astrologerId = sr.astrologerId)");
		queryColumnInfo.setQueryColumnAlias("sr_astrologerName");
		queryColumnsList.add(queryColumnInfo);

		int isQueryProcessed = queryLoader.processQuery(organisationSession, queryInfo, queryColumnsList, queryTablesList);
		if (isQueryProcessed == 0)
		{			
			System.out.println(queryInfo.getQueryCode() + " query Info Could not be added.");
		}
		return isQueryProcessed;
	}

}
