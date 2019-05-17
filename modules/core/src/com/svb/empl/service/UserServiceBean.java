package com.svb.empl.service;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit.Employee;

import javax.inject.Inject;
import java.util.Optional;

@Service(UserService.NAME)
public class UserServiceBean implements UserService {
	@Inject
	private DataManager dataManager;
	
	@Inject
	private Metadata metadata;
	
	
	@Inject
	private Persistence persistence;
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User getUserbyLogin(String userLogin) {
		User user = null;
		try {
			Optional<User> optionalUser = dataManager.load(User.class).
					query(userSearchbyLoginQuery).
					parameter("userlogin", userLogin).
					optional();
			
			if (optionalUser.isPresent()) user = optionalUser.get();
			
			return user;
			
			
		} catch (Exception err) {
			logger.error(err.getLocalizedMessage());
			return null;
		}
	
		
		
		
		
	}
	
	@Override
	public void registerUser(Employee employee) {
	
	}
}