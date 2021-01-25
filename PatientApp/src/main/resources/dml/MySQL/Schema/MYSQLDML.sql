create table Organisations
(
 organisationsId int(10) primary key auto_increment

	
 , organisationName varchar(50)
		
 , addressLine1 varchar(50)
		
 , addressLine2 varchar(50)
		
 , city varchar(50)
		
 , residentState varchar(50)
		
 , pinCode varchar(50)
		
 , databaseName varchar(50)
		
 , country varchar(50)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table UserInfo
(
 userInfoId int(10) primary key auto_increment
 , organisationsUserOrgId int(10)

	
 , userFirstName varchar(50)
		
 , userLastName varchar(50)
		
 , loginId varchar(50)
		
 , loginEmailId varchar(100)
		
 , contactNo varchar(20)
		
 , loginPassword varchar(100)
		
 , resetToken varchar(10)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table PrivilegeGroup
(
 privilegeGroupId int(10) primary key auto_increment

	
 , privilegeGroupName varchar(50)
		
 , privilegeCode varchar(50)
		
 , applicableUserType varchar(50)
		
 , selfServiceUser varchar(50)
		
 , privilegeGroupDescription varchar(50)
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table PrivilegeGroupItems
(
 privilegeGroupItemsId int(10) primary key auto_increment

	
 , privilegeActionType varchar(50)
		
 , privilegeObjectType varchar(50)
		
 , privilegeObjectName varchar(50)
	

 , privilegeGroupId int(10)	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table EmployeeRoles
(
 employeeRolesId int(10) primary key auto_increment
 , privilegeGroupId int(10)

	
 , description varchar(50)
	

 , userInfoId int(10)	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table LoginSessionInfo
(
 loginSessionInfoId int(10) primary key auto_increment

	
 , loginUserType varchar(50)
		
 , selfServiceUserType varchar(50)
		
 , sessionId varchar(50)
		
 , userId int(10)
		
 , loginTime datetime
	

		
 , vwLastModifiedDate datetime
 , vwLastModifiedTime int(9)
 , vwLastAction varchar(20)
 , vwModifiedBy varchar(100)
 , vwTxnRemarks varchar(500)
 , vwTxnStatus varchar(25)
 , isRequestUnderProcesss int(10)
 , legacyRecordId int(10)
 , vwCreationDate datetime
 , vwCreatedBy int(9)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

