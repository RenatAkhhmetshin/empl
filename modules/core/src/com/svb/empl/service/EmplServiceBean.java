package com.svb.empl.service;

import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.security.entity.User;
import com.svb.empl.entity.Empl;
import org.springframework.stereotype.Service;
import retrofit.Employee;

import javax.inject.Inject;
import java.util.Optional;

@Service(EmplService.NAME)
public class EmplServiceBean implements EmplService {
	@Inject
	private TransactionalDataManager transDataManager;
	
	@Override
	public Empl getEmplbyLogin(String userLogin) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = transDataManager.load(Empl.class)
				.query(emplSearchbyLoginQuery)
				.parameter("userLogin" , userLogin)
				.view("empl-view_1")
				.optional();
		
		if (optionalEmpl.isPresent()) empl = optionalEmpl.get();
		
		return empl;
	}
	
	@Override
	public Empl getEmplbyEmail(String userEmail) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = transDataManager.load(Empl.class)
				.query(emplSearchbyEmailQuery)
				.parameter("useremail" , userEmail)
				.view("empl-view_1")
				.optional();
		
		if (optionalEmpl.isPresent()) empl = optionalEmpl.get();
		
		return empl;
	}
	
	@Override
	public Empl getEmplByTabNumber(String userTabNumber) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = transDataManager.load(Empl.class)
				.query(emplSearchbyTabNumberQuery)
				.parameter("usertabnumber" , userTabNumber)
				.view("empl-view_1")
				.optional();
		
		if (optionalEmpl.isPresent()) empl = optionalEmpl.get();
		
		return empl;
	}
	
	
	/**
	 * Searching Empl by ExtID
	 * @param userExtID - employee external ID
	 * @return entity Empl
	 */
	@Override
	public Empl getEmplByExtID(String userExtID) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = transDataManager.load(Empl.class)
				.query(emplSearchbyExtIDQuery)
				.parameter("userextid" , userExtID)
		        .view("empl-view_1")
				.optional();
		
		if (optionalEmpl.isPresent()) empl = optionalEmpl.get();
		
		return empl;
	}
	
	
	@Override
	public Empl getEmplByUser(User user) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = transDataManager.load(Empl.class)
				.query(emplSearchbyUserQuesry)
				.parameter("user" , user)
				.view("empl-view_1")
				.optional();
		
		if (optionalEmpl.isPresent()) empl = optionalEmpl.get();
		
		return empl;
	}
	
	@Override
	public Empl getEmplbyOuterEmployee(Employee employee) {
		Empl empl = null;
		
		// Поиск по логину
		empl = this.getEmplbyLogin(employee.getCommonDatas().getLogin());
		if (empl != null) return empl;
		
		// поиск по табельному
		empl = this.getEmplByTabNumber(employee.getStaffDatas().getTabNumber());
		if (empl != null) return empl;
		
		// поиск по ExtID
		empl = this.getEmplByExtID(employee.getCommonDatas().getExtID());
		if (empl != null) return empl;
		
		// поиск по email
		empl = this.getEmplbyEmail(employee.getCommonDatas().getEmail());
		if (empl != null) return empl;
		
		return empl;
	}
}