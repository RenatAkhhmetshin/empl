package com.svb.empl.service;

import com.haulmont.cuba.security.entity.User;
import com.svb.empl.entity.Empl;
import retrofit.Employee;

public interface EmplService {
	String NAME = "empl_EmplService";
	
	String emplSearchbyLoginQuery = "select e from empl_Empl e where e.user.login =:userLogin";
	String emplSearchbyTabNumberQuery = "select e from empl_Empl e where e.tabnumber =:usertabnumber";
	String emplSearchbyExtIDQuery = "select e from empl_Empl e where e.extid =:userextid";
	String emplSearchbyEmailQuery = "select e from empl_Empl e where e.user.email =:useremail";
	String emplSearchbyUserQuesry = "select e from empl_Empl e where e.user =: user";
	
	Empl getEmplbyLogin(String userLogin);
	
	Empl getEmplbyEmail(String userEmail);
	
	Empl getEmplByTabNumber(String userTabNumber);
	
	Empl getEmplByExtID(String userExtID);
	
	Empl getEmplByUser (User user);
	
	Empl getEmplbyOuterEmployee(Employee employee);
	
}