create table RequestReceived
(
 requestReceivedId int(10) primary key auto_increment
 , currentRequestStatus varchar(20)
 , sentRequestsStatus varchar(20)
 , requestType varchar(20)
 , serviceName varchar(100)
 , apiName varchar(200)
 , rollbackAPIName varchar(200)       
 , paramsInfo varchar(4000)
 , referenceRequestId int(10)
 , transactionReferenceId int(10)
 , rollBackRequest int(10)
 , isRequestRolledBack int(10)
 , areSentRequestsRolledBack int(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table RequestSent
(
 requestSentId int(10) primary key auto_increment
 , referenceRequestId int(10)
 , requestStatus varchar(20)
 , targetServiceRequestId int(10)
 , rollBackRequest int(10)
 , isRequestRolledBack int(10)
 , serviceName varchar(100)
 , apiName varchar(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table DualTableInfo
(
 dualTableColumn varchar(50)              
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into DualTableInfo(dualTableColumn) values('testColumn');
create table QueryInfo
(
 queryInfoId int(10) primary key auto_increment
 , queryName varchar(100)
 , queryCode varchar(100)
 , useNativeQuery varchar(100)
 , queryWhereClause varchar(500)
 , groupByClause varchar(500)
 , orderByClause varchar(500)
 , limitClause varchar(500)
 , vwLastModifiedDate date
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
create table QueryColumnInfo
(
 queryColumnInfoId int(10) primary key auto_increment
 , queryColumnText varchar(2000)
 , queryColumnAlias varchar(200)
 , queryInfoId int(10)	
 , vwLastModifiedDate date
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
create table QueryTableInfo
(
 queryTableInfoId int(10) primary key auto_increment
 , queryTableName varchar(2000)
 , queryTableAlias varchar(200)
 , queryInfoId int(10)	
 , vwLastModifiedDate date
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
create table FileUpload
(
 fileUploadId int(10) primary key auto_increment
 , fileName varchar(100)
 , fileBlob LONGBLOB
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
