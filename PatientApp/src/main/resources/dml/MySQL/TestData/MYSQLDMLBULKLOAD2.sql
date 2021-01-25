SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO Organisations(OrganisationsId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO UserInfo(UserInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO PrivilegeGroup(PrivilegeGroupId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO PrivilegeGroupItems(PrivilegeGroupItemsId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO EmployeeRoles(EmployeeRolesId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO LoginSessionInfo(LoginSessionInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO ConfigProperties(ConfigPropertiesId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO TaskInfo(TaskInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO TaskExecutionInfo(TaskExecutionInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO TaskLayoutParameters(TaskLayoutParametersId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO EmailAttachmentLayout(EmailAttachmentLayoutId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO TaskScheduleInfo(TaskScheduleInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO TaskSentInfo(TaskSentInfoId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO Patient(PatientId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO Doctor(DoctorId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO Hospital(HospitalId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE (v_counter_parent <= (v_max_parent*v_max_child)) DO
	INSERT INTO ContactUs(ContactUsId) VALUES(v_counter_parent);
	SET v_counter_parent=v_counter_parent+1;	
END WHILE;	

