package com.svb.empl.service;

import com.haulmont.cuba.security.entity.User;
import retrofit.Employee;

public interface UserService {
	String NAME = "empl_UserService";
	
	String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
	
	String DEFAULT_ROLE_ID = "4416f886-9d87-cc44-ba49-e2877fbefff5";
	
	String userSearchbyLoginQuery = "select e from sec$User where e.login = :userlogin";
	
	User getUserbyLogin (String userLogin);
	
	void registerUser (Employee employee);
	
}