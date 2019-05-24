package com.svb.empl.service;

import retrofit.Employee;

public interface DominoService {
	String NAME = "empl_DominoService";
	String emplServiceURL = "http://srv-lotus-oktp/svbank.nsf/devapi.xsp/";
	String employeeServiceURL = "http://srv-lotus-oktp/svbank.nsf/personapi.xsp/";
	String emplTabSearchURL = "http://srv-lotus-oktp/svbank.nsf/api.xsp/";
	String user = "Designer";
	String password = "CDGjkzrjd";
	String defGroupUUID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
	
	String userSearchLoginQuery ="select e from sec$User e \n" +
			"where e.login =:userlogin";
	
	String orgUnitbyUNIDSearchQuery = "select e from empl_OrgUnit e where e.extid =:extid";
	
	String emplSearchLoginQuery = "select e from empl_Empl e where e.user.login =:userlogin";
	
	String emplBranchSearchQuery = "select e from empl_Branch e where e.code =:branchcode";
	
	String emplFileDescriptorSearchQuery = "select e from sys$FileDescriptor e where e.name = :fileName";
	void getDominoEmployees ();
	
	Employee getDominoEmployeebyTabNumber(String personTab);
	
	void createEmployees();
	
	void createOrgUnits();
	
	void processEmployees();
	
	void updateEmployees();
}