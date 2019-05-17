package com.svb.empl.core;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import retrofit.EmployeeNew;

import javax.inject.Inject;
import java.util.List;

@Component(EmployeeCreator.NAME)
public class EmployeeCreator {
	public static final String NAME = "empl_EmployeeCreator";
	
	@Inject
	private DataManager dataManager;
	
	@Inject
	private Metadata metadata;
	

	
	public void createEmployees(List<EmployeeNew> employeeNewList) {
	
	}
	
}