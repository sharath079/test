


TRUNCATE UserInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO UserInfo(userInfoId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	



TRUNCATE PrivilegeGroup;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO PrivilegeGroupItems(privilegeGroupItemsId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	


TRUNCATE UserInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO EmployeeRoles(employeeRolesId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	

TRUNCATE EmployeeRoles;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO EmployeeRoles(employeeRolesId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	







TRUNCATE TaskInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO TaskExecutionInfo(taskExecutionInfoId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	


TRUNCATE TaskInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO TaskLayoutParameters(taskLayoutParametersId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	


TRUNCATE TaskInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO EmailAttachmentLayout(emailAttachmentLayoutId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	



TRUNCATE TaskScheduleInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO TaskScheduleInfo(taskScheduleInfoId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	


TRUNCATE TaskSentInfo;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO TaskSentInfo(taskSentInfoId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	

TRUNCATE Doctor;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO Patient(patientId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	

TRUNCATE Patient;
SET v_counter_parent=1;	
SET v_counter_child=1;
SET v_max_parent=$$MAX_PARENT_TXNS$$;	
SET v_max_child=$$MAX_CHILD_TXNS$$;
WHILE v_counter_parent <= v_max_parent DO
	WHILE v_counter_child <= v_max_child DO
		INSERT INTO Patient(patientId) VALUES(v_counter_parent);
		SET v_counter_child=v_counter_child+1;
	END WHILE;
SET v_counter_parent=v_counter_parent+1;	
SET v_counter_child=1;
END WHILE;	








