package com.svb.empl.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import com.svb.empl.core.EmployeeCreator;
import com.svb.empl.entity.*;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service(DominoService.NAME)
public class DominoServiceBean implements DominoService {
	private static Logger logger = LoggerFactory.getLogger(DominoServiceBean.class);
	
	
	@Inject
	private Persistence persistence;
	
	
	@Inject
	private Metadata metadata;
	
	@Inject
	private DataManager dataManager;
	private String userSearchbyLoginQuery = "select e from sec$User e where e.login = :userlogin";
	
	private String defaultGroupID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
	
	@Inject
	private EmployeeCreator employeeCreator;
	
	@Inject
	private FileLoader fileLoader;
	
	@Inject
	private TimeSource timeSource;
	
	private CommitContext commitContext;
	
	@Inject
	private TransactionalDataManager txDataManager;
	
	private ArrayList<Empl> processedLogins;
	
	@Inject
	private EmplService emplService;
	
	@Override
	public void getDominoEmployees() {
		
		try {
			
			/*String authToken = this.getAuthToken();
			
			logger.info("Start run retrofit");
			
			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.connectTimeout(12000, TimeUnit.SECONDS)
					.writeTimeout(12000, TimeUnit.SECONDS)
					.readTimeout(13000, TimeUnit.SECONDS)
					.build();
			Gson gson = new GsonBuilder()
					.setLenient()
					.create();
			
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(emplServiceURL)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(okHttpClient)
					.build();
			
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			
			Call<List<Employee>> employeeList = service.getEmployeeList(authToken);
			
			
			employeeList.enqueue(new Callback<List<Employee>>() {
				@Override
				public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
					List<Employee> body = response.body();
					
					if (body == null) {
						logger.error("Не указано тело ответа");
						return;
					}
					
					if ( ! response.isSuccessful()) {
						try {
							logger.error("Запрос отработал некорректно: "+ response.errorBody().
									string());
							return;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					try {
						for (Employee employee : body) {
							
							String login = employee.getLogin();
							
							if (login != null) {
								try (Transaction transaction = persistence.getTransaction()) {
									Query query = persistence.getEntityManager().createQuery(userSearchbyLoginQuery);
									query.setParameter("userlogin", login);
									
									List resultList = query.getResultList();

									if (resultList.isEmpty()) {
										logger.info("Need create user: "+ login);
										
										User user = metadata.create(User.class);
										
										CommitContext commitContext = new CommitContext();
										user.setActive (true);
										user.setEmail (employee.getEmail ());
										user.setFirstName (employee.getFirstName ());
										user.setLastName (employee.getLastName ());
										user.setMiddleName (employee.getMiddleName ());
										user.setLogin (employee.getLogin ());
										
										user.setName (employee.getLastName () + " " +
												employee.getFirstName () + " " +
												employee.getMiddleName ());
										
										user.setPosition (employee.getPosition()); // должность
										
										UUID groupUuid = UUID.fromString (defaultGroupID);
										Group group  = persistence.getEntityManager().
												find(Group.class, groupUuid, View.LOCAL);
										if (group != null) {
											logger.info ("found group " + group.getName ());
											user.setGroup (group);
											commitContext.addInstanceToCommit(group);
										}
										commitContext.addInstanceToCommit(user);
										dataManager.commit(commitContext);
									}
									
									transaction.commit ();
								}
								
							}
							
							
						}
						
						logger.info("End process users");
						
						
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getLocalizedMessage());
					}
				
					
					
				}
				
				@Override
				public void onFailure(Call<List<Employee>> call, Throwable t) {
					logger.info("on Failure");
					logger.error(t.getMessage());
				}
				
				
			});*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		
		
	}
	
	@Override
	public Employee getDominoEmployeebyTabNumber(String personTab) {
		
		Employee employee = null;
		try {
			String authToken = this.getAuthToken();
			
			logger.info("Start run retrofit");
			
			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.connectTimeout(12000, TimeUnit.SECONDS)
					.writeTimeout(12000, TimeUnit.SECONDS)
					.readTimeout(13000, TimeUnit.SECONDS)
					.build();
			
			
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(emplTabSearchURL)
					.addConverterFactory(ScalarsConverterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.client(okHttpClient)
					.build();
			
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			
			JSONObject jsonObject = new JSONObject();
			
			
			jsonObject.put("TabNumber", personTab);
			jsonObject.put("status", "123123011");
			
			String personTabNumber = jsonObject.toString();
			
			logger.info(personTabNumber);
			Call<Employee> employeebyTab = service.getEmployeebyTab(authToken, personTabNumber);
			
			Response<Employee> execute = employeebyTab.execute();
			
			if (execute.isSuccessful()) {
				
				employee = execute.body();
				
				
			}
			
			return employee;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			
			return null;
		}
	}
	
	
	/**
	 * Получение токена авторизации
	 *
	 * @return возвращает преобразованную в Base64 пару логин: пароль
	 * @author adms-Ahmetshin-RM
	 */
	private String getAuthToken() {
		byte[] data = new byte[0];
		try {
			data = (user + ":" + password).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "Basic " + Base64.getEncoder().encodeToString(data);
	}
	
	/**
	 * Создание сотрудников
	 *
	 * @author adms-Ahmetshin-RM
	 */
	@Override
	
	
	public void createEmployees() {
		try {
			String authToken = this.getAuthToken();
			
			logger.info("Начало вызова сервиса");
			
			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.connectTimeout(12000, TimeUnit.SECONDS)
					.writeTimeout(12000, TimeUnit.SECONDS)
					.readTimeout(13000, TimeUnit.SECONDS)
					.build();
			
			GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
			gsonBuilder.registerTypeAdapter(Date.class,
					new DateDeserializer());
			Gson gson = gsonBuilder.create();
			
			
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(emplServiceURL)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(okHttpClient)
					.build();
			
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			
			Call<List<Employee>> employees = service.createEmployees(authToken);
			
			logger.info("Вызов асинхронного сервиса");
			Response<List<Employee>> listResponse = employees.execute();
			
			if (listResponse.isSuccessful()) {
				logger.info("Получили данные по сотрудникам");
				List<Employee> employeeList = listResponse.body();
				this.processedLogins = new ArrayList<Empl>();
				
				if (employeeList == null) throw new Exception("Не получены данные по сотрудникам");
				
				commitContext = new CommitContext();
				
				for (Employee employee : employeeList) {
					String userLogin = employee.getCommonDatas().getLogin();
					
					Empl empl = this.getEmplbyLogin(userLogin);
					if (empl == null) {
						logger.info("Need create new employee: " + userLogin);
						empl = this.createEmpl(employee);
						dataManager.commit(empl);
						
						
					}
				}
				
				//dataManager.commit(commitContext);
				
				
			} else {
				logger.info("Retrofit обработал с ошибкой");
				logger.error("Retrofit error body:" +
						listResponse.errorBody().string());
			}
			
			logger.info("End creating users");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Создание Подразделений
	 */
	@Override
	public void createOrgUnits() {
		try {
			String authToken = this.getAuthToken();
			
			logger.info("Start run retrofit");
			
			OkHttpClient okHttpClient = new OkHttpClient.Builder()
					.connectTimeout(12000, TimeUnit.SECONDS)
					.writeTimeout(12000, TimeUnit.SECONDS)
					.readTimeout(13000, TimeUnit.SECONDS)
					.build();
			Gson gson = new GsonBuilder()
					.setLenient()
					.create();
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(emplServiceURL)
					.addConverterFactory(GsonConverterFactory.create(gson))
					.client(okHttpClient)
					.build();
			
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			
			Call<List<EmplOrgUnit>> orgUnits = service.createOrgUnits(authToken);
			
			logger.info("Получение данных по подразделениям");
			Response<List<EmplOrgUnit>> listResponse = orgUnits.execute();
			
			if (listResponse.isSuccessful()) {
				logger.info("listResponse.isSuccessful");
				commitContext = new CommitContext();
				List<EmplOrgUnit> emplOrgUnits = listResponse.body();
				
				logger.info(emplOrgUnits.get(0).toString());
				for (EmplOrgUnit emplOrgUnit : emplOrgUnits) {
					OrgUnit orgUnit = createOrgUnit(emplOrgUnit);
					if (orgUnit != null) {
						dataManager.commit(orgUnit);
					}
					
				}
				
				//dataManager.commit(this.commitContext);
			} else {
				logger.error("listResponse error:" +
						listResponse.errorBody().string());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private User getUserbyLogin(String userLogin) {
		
		User user = null;
		Optional<User> optionalUser = dataManager.load(User.class).
				query(userSearchbyLoginQuery).
				parameter("userlogin", userLogin).optional();
		
		
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		
		
		return user;
	}
	
	private Empl getEmplbyLogin(String userLogin) {
		Empl empl = null;
		
		Optional<Empl> optionalEmpl = dataManager.load(Empl.class).
				query(emplSearchLoginQuery).
				parameter("userlogin", userLogin).
				optional();
		
		if (optionalEmpl.isPresent()) {
			empl = optionalEmpl.get();
		}
		return empl;
	}
	
	/**
	 * Создание нового сотрудника
	 *
	 * @param employee объект с данными по сотруднику
	 */
	private Empl createEmpl(Employee employee) {
		Empl empl = metadata.create(Empl.class);
		
		String emplLogin = employee.getCommonDatas().getLogin();
		
		User user = this.getUserbyLogin(emplLogin);
		if (user == null) {
			logger.info("Creating new user");
			user = this.createUser(employee);
			dataManager.commit(user);
		}
		empl.setUser(user);
		logger.info("Adding common emplyee datas");
		empl.setBirthdate(employee.getCommonDatas().getBirthDate());
		
		String branchCode = employee.getPlacement().getBranchCode();
		
		Branch emplBranch = this.getBranchByCode(branchCode);
		if (emplBranch != null) empl.setBranch(emplBranch);
		
		empl.setCity(employee.getPlacement().getCity());
		empl.setExtphone(employee.getPlacement().getExtPhone());
		empl.setMobilephone(employee.getPlacement().getMobilePhone());
		empl.setPhone(employee.getPlacement().getPhone());
		empl.setOffice(employee.getPlacement().getOffice());
		empl.setNotesname(employee.getCommonDatas().getNotesName());
		empl.setTabnumber(employee.getStaffDatas().getTabNumber());
		empl.setExtid(employee.getCommonDatas().getExtID());
		
		Sex sex = Sex.fromId(employee.getCommonDatas().getSex());
		if (sex != null) empl.setSex(sex);
		
		
		PhotoDatas photoDatas = employee.getPhotoDatas();
		if (photoDatas != null) {
			FileDescriptor descriptor = this.getPhotoDescriptor(photoDatas);
			if (descriptor != null) {
				logger.info("Adding employee photo datas");
				dataManager.commit(descriptor);
				empl.setPhoto(descriptor);
			}
			
		}
		
		logger.info("Adding employee OrgUnits datas");
		ArrayList<String> employeeOrgUnitsList = (ArrayList<String>)
				employee.getStaffDatas().getOrgUnitsList();
		List<OrgUnit> unitList = this.getEmplOrgUnitList(employeeOrgUnitsList);
		
		if (!unitList.isEmpty()) {
			empl.setOrgunits(unitList);
			logger.info("Adding employee postPath");
			String postPath = "";
			for (OrgUnit emplOrgUnit : unitList) {
				if ("".equalsIgnoreCase(postPath)) {
					postPath = emplOrgUnit.getShortname();
					
				} else {
					postPath += "\\" + emplOrgUnit.getShortname();
				}
			}
			
			empl.setPostpath(postPath);
		}
		
		
		// получение данных по руководителю
		Employee chiefEmployee = employee.getStaffDatas().getChief();
		Empl chiefEmpl = null;
		if (chiefEmployee != null) {
			logger.info("Need Add chief to  employee");
			String chiefEmplLogin = chiefEmployee.getCommonDatas().getLogin();
			chiefEmpl = this.getEmplbyLogin(chiefEmplLogin);
			
			if (chiefEmpl == null) {
				chiefEmpl = this.createEmpl(chiefEmployee);
				dataManager.commit(chiefEmpl);
				
			}
			
			if (chiefEmpl != null) {
				logger.info("Adding chief to Employee");
				empl.setChief(chiefEmpl);
			}
		}
		
		return empl;
	}
	
	
	private List<Post> getEmplPostList(ArrayList<String> employeePostArrayList) {
		ArrayList<Post> postArrayList = new ArrayList<Post>();
		
		for (String postUnid : employeePostArrayList) {
			Post post = null;
			if (post != null) {
				if (!postArrayList.contains(post)) postArrayList.add(post);
			}
		}
		
		return postArrayList;
	}
	
	
	/**
	 * Получение массива подразделений по сотруднику
	 *
	 * @param employeeOrgUnitsList
	 * @return
	 */
	private List<OrgUnit> getEmplOrgUnitList(ArrayList<String> employeeOrgUnitsList) {
		ArrayList<OrgUnit> units = new ArrayList<OrgUnit>();
		
		for (String employeeOrgUnitUnid : employeeOrgUnitsList) {
			OrgUnit orgUnit = this.getOrgUnitbyNotesUNID(employeeOrgUnitUnid);
			if (orgUnit != null) {
				if (!units.contains(orgUnit)) units.add(orgUnit);
			}
		}
		
		return units;
	}
	
	/**
	 * Создание нового пользователя
	 *
	 * @param employee данные по сотруднику
	 * @return объект класса User
	 */
	private User createUser(Employee employee) {
		User user = metadata.create(User.class);
		user.setActive(Boolean.TRUE);
		String fullName = employee.getCommonDatas().getLastName() + " " +
				employee.getCommonDatas().getFirstName() + " " +
				employee.getCommonDatas().getMiddleName();
		
		user.setName(fullName);
		user.setEmail(employee.getCommonDatas().getEmail());
		user.setLastName(employee.getCommonDatas().getLastName());
		user.setMiddleName(employee.getCommonDatas().getMiddleName());
		user.setFirstName(employee.getCommonDatas().getFirstName());
		user.setLogin(employee.getCommonDatas().getLogin());
		
		user.setPosition(employee.getStaffDatas().getPost());
		Group group = this.getGroupbyUUID(defaultGroupID);
		if (group != null) {
			user.setGroup(group);
		}
		return user;
	}
	
	
	private Group getGroupbyUUID(String groupUUIDString) {
		UUID groupUuid = UUID.fromString(groupUUIDString);
		Group group = null;
		Optional<Group> groupOptional = dataManager.load(Group.class).id(groupUuid).optional();
		
		if (groupOptional.isPresent()) {
			group = groupOptional.get();
		}
		
		return group;
	}
	
	
	/**
	 * Создаг
	 *
	 * @param photoDatas данные фото сотрудника
	 * @return Объект FileDescriptor
	 */
	private FileDescriptor getPhotoDescriptor(PhotoDatas photoDatas) {
		byte[] filaData = photoDatas.getPhotoFileBytes();
		if (filaData == null) return null;
		
		FileDescriptor fileDescriptor = metadata.create(FileDescriptor.class);
		fileDescriptor.setName(photoDatas.getPhotofileName());
		fileDescriptor.setExtension(photoDatas.getPhotoFileExtension());
		
		fileDescriptor.setSize((long) photoDatas.getPhotoFileBytes().length);
		fileDescriptor.setCreateDate(timeSource.currentTimestamp());
		
		try {
			fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(filaData));
		} catch (FileStorageException e) {
			e.printStackTrace();
		}
		
		
		return fileDescriptor;
	}
	
	/**
	 * Создание новой записи подразделения
	 *
	 * @param emplOrgUnit данные по  подразделению
	 */
	private OrgUnit createOrgUnit(EmplOrgUnit emplOrgUnit) {
		String orgUnitUnid = emplOrgUnit.getOrgUnitUnid();
		OrgUnit orgUnit = null;
		if ((!orgUnitUnid.isEmpty()) && (orgUnitUnid != null)) {
			orgUnit = this.getOrgUnitbyNotesUNID(orgUnitUnid);
			
			if (orgUnit == null) {
				orgUnit = metadata.create(OrgUnit.class);
				
				orgUnit.setExtid(orgUnitUnid);
				orgUnit.setFullname(emplOrgUnit.getFullName());
				orgUnit.setShortname(emplOrgUnit.getShortName());
				
				EmplOrgUnit parentEmplOrgUnit = emplOrgUnit.getParentOrgUnit();
				if (parentEmplOrgUnit != null) {
					orgUnitUnid = parentEmplOrgUnit.getOrgUnitUnid();
					OrgUnit parentorgUnit = this.getOrgUnitbyNotesUNID(orgUnitUnid);
					if (parentorgUnit == null) {
						logger.info("Creating new parent OrgUnit");
						parentorgUnit = this.createOrgUnit(parentEmplOrgUnit);
						dataManager.commit(parentorgUnit);
					}
					if (parentorgUnit != null) {
						logger.info("Adding parentorgUnit");
						orgUnit.setParent(parentorgUnit);
						//dataManager.commit(orgUnit);
					}
				}
			}
		}
		
		return orgUnit;
	}
	
	/**
	 * Получение данных по подразделению по UNID
	 *
	 * @param docUNID UNID карточки подразделения
	 * @return запись сущности Подразделения
	 */
	private OrgUnit getOrgUnitbyNotesUNID(String docUNID) {
		Optional<OrgUnit> orgUnitOptional = txDataManager.load(OrgUnit.class).
				query(orgUnitbyUNIDSearchQuery).
				parameter("extid", docUNID).
				optional();
		if (orgUnitOptional.isPresent()) return orgUnitOptional.get();
		
		return null;
	}
	
	/**
	 * Получение данных по филиалу сотрудника
	 *
	 * @param branchCode код филиала
	 * @return объект Branch
	 */
	private Branch getBranchByCode(String branchCode) {
		Branch branch = null;
		Optional<Branch> optionalBranch = dataManager.load(Branch.class).
				query(emplBranchSearchQuery).
				parameter("branchcode", branchCode).
				optional();
		
		if (optionalBranch.isPresent()) {
			branch = optionalBranch.get();
		}
		
		return branch;
	}
	
	@Override
	public void processEmployees() {
		try {
			logger.info("Start process Employees");
			String authToken = this.getAuthToken();
			
			if (authToken == null) throw new Exception("Cannot get Autentification Token");
			
			logger.info("Getting OkHttpClient");
			OkHttpClient httpClient = this.getHttpClient();
			
			logger.info("Getting Gson");
			Gson gson = this.getGson();
			
			logger.info("Getting Retrofit");
			Retrofit retrofit = this.getRetrofit(httpClient, gson);
			
			logger.info("Calling Service");
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			Call<List<Employee>> employees = service.createEmployees(authToken);
			
			Response<List<Employee>> listResponse = employees.execute();
			
			if (!listResponse.isSuccessful()) throw new Exception("Service Returned Error: \"+" +
					listResponse.errorBody().string());
			
			logger.info("Process Service Response Results");
			
			List<Employee> emplResponseList = listResponse.body();
			for (Employee employee : emplResponseList) {
				try {
					//Создание/ обновление данных по сотрудникам
					this.processEmployee(employee);
				} catch (Exception e) {
					logger.error("Error response process: " + e.getLocalizedMessage())
					;
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			logger.info("End process Employees");
		}
	}
	
	private OkHttpClient getHttpClient() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(12000, TimeUnit.SECONDS)
				.writeTimeout(12000, TimeUnit.SECONDS)
				.readTimeout(13000, TimeUnit.SECONDS)
				.build();
		
		return okHttpClient;
	}
	
	
	private Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder().setLenient();
		gsonBuilder.registerTypeAdapter(Date.class,
				new DateDeserializer());
		Gson gson = gsonBuilder.create();
		
		return gson;
	}
	
	private Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(emplServiceURL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.client(okHttpClient)
				.build();
		return retrofit;
	}
	
	
	/**
	 * Processing employee
	 *
	 * @param employee Employee for processing
	 * @throws Exception
	 */
	
	private void processEmployee(Employee employee) throws Exception {
		try {
			logger.info("Search Empl");
			Empl empl = emplService.getEmplbyOuterEmployee(employee);
			
			if (empl == null) {
				logger.info("Create new Empl");
				this.createNewEmpl(employee);
			}
		} catch (Exception e) {
			throw new Exception("Error processEmployee: \n" +
					employee.getCommonDatas().getLogin() + "\n" +
					e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Creating new Empl Entity record
	 *
	 * @param employee Employee or processing
	 * @throws Exception
	 */
	@Transactional
	private Empl createNewEmpl(Employee employee) throws Exception {
		try {
			String emplLogin = employee.getCommonDatas().getLogin();
			User user = this.getUserbyLogin(emplLogin);
			if (user == null) {
				logger.info("Creating new user");
				user = this.createUser(employee);
				txDataManager.save(user);
			}
			
			Empl empl = txDataManager.create(Empl.class);
			empl.setUser(user);
			
			logger.info("Writing common Empl datas");
			this.writeEmplCommonDatas(employee.getCommonDatas(), empl);
			
			logger.info("Writing Placement Empl datas");
			this.writeEmplPlaacementDatas(employee.getPlacement(), empl);
			
			logger.info("Writing Photo Empl datas");
			this.writeEmplPhotoDatas(employee.getPhotoDatas(), empl);
			
			logger.info("Writing Staff Empl datas");
			this.writeEmplStaffdatas(employee.getStaffDatas(), empl);
			
			txDataManager.save(empl);
			
			Employee chiefEmployee = employee.getStaffDatas().getChief();
			if (chiefEmployee != null) {
				String chiefLogin = chiefEmployee.getCommonDatas().getLogin();
				logger.info("Processing Empl Chief: " + chiefLogin);
				Empl chiefEmpl = emplService.getEmplbyLogin(chiefLogin);
				
				if (chiefEmpl == null) {
					logger.info("Creating new chief to  Employee");
					chiefEmpl = this.createNewEmpl(chiefEmployee);
					txDataManager.save(chiefEmpl);
				} else {
					logger.info("found chief to  Employee");
				}
				
				if (chiefEmpl != null) {
					empl.setChief(chiefEmpl);
				}
				
				txDataManager.save(empl);
			}
			
			
			return empl;
		} catch (Exception e) {
			throw new Exception("Error createNewEmpl: \n" +
					e.getLocalizedMessage());
		}
	}
	
	private void writeEmplCommonDatas(CommonDatas emplCommonDatas,
	                                  Empl empl) throws Exception {
		try {
			empl.setBirthdate(emplCommonDatas.getBirthDate());
			empl.setNotesname(emplCommonDatas.getNotesName());
			empl.setExtid(emplCommonDatas.getExtID());
			
			logger.info("Employee Sex: " + emplCommonDatas.getSex());
			Sex sex = Sex.fromId(emplCommonDatas.getSex());
			if (sex != null) empl.setSex(sex);
			empl.setBirthdate(emplCommonDatas.getBirthDate());
			
			
		} catch (Exception e) {
			throw new Exception("Error writeEmplCommonDatas: \n" +
					e.getLocalizedMessage());
		}
	}
	
	
	private void writeEmplPlaacementDatas(Placement emplPlacementDatas,
	                                      Empl empl) throws Exception {
		try {
			String branchCode = emplPlacementDatas.getBranchCode();
			
			Branch emplBranch = this.getBranchByCode(branchCode);
			if (emplBranch != null) empl.setBranch(emplBranch);
			
			empl.setCity(emplPlacementDatas.getCity());
			empl.setExtphone(emplPlacementDatas.getExtPhone());
			empl.setMobilephone(emplPlacementDatas.getMobilePhone());
			empl.setPhone(emplPlacementDatas.getPhone());
			empl.setOffice(emplPlacementDatas.getOffice());
			empl.setRoom(emplPlacementDatas.getRoom());
		} catch (Exception e) {
			throw new Exception("Error writeEmplPlaacementDatas: \n" +
					e.getLocalizedMessage());
		}
	}
	
	private void writeEmplPhotoDatas(PhotoDatas emplPhotoDatas,
	                                 Empl empl) throws Exception {
		try {
			FileDescriptor fileDescriptor = this.getFileDescriptorbyName(emplPhotoDatas);
			
			if (fileDescriptor != null) empl.setPhoto(fileDescriptor);
			
		} catch (Exception e) {
			throw new Exception("Error writeEmplPhotoDatas: \n" +
					e.getLocalizedMessage());
		}
		
	}
	
	
	private FileDescriptor getFileDescriptorbyName(PhotoDatas emplPhotoDatas) throws Exception {
		FileDescriptor descriptor = null;
		String fileName = emplPhotoDatas.getPhotofileName();
		Optional<FileDescriptor> fileDescriptorOptional = txDataManager.load(FileDescriptor.class)
				.query(emplFileDescriptorSearchQuery)
				.parameter("fileName", fileName)
				.optional();
		
		if (fileDescriptorOptional.isPresent()) {
			logger.info("Found Existing PhotoFileDescriptor");
			descriptor = fileDescriptorOptional.get();
		} else {
			logger.info("Creating new PhotoFileDescriptor");
			descriptor = this.createFileDescriptor(emplPhotoDatas);
		}
		
		return descriptor;
	}
	
	@Transactional
	private FileDescriptor createFileDescriptor(PhotoDatas emplPhotoDatas) throws Exception {
		try {
			FileDescriptor fileDescriptor = null;
			
			byte[] filaData = emplPhotoDatas.getPhotoFileBytes();
			if (filaData == null) return null;
			
			fileDescriptor = txDataManager.create(FileDescriptor.class);
			fileDescriptor.setName(emplPhotoDatas.getPhotofileName());
			fileDescriptor.setExtension(emplPhotoDatas.getPhotoFileExtension());
			
			fileDescriptor.setSize((long) emplPhotoDatas.getPhotoFileBytes().length);
			fileDescriptor.setCreateDate(timeSource.currentTimestamp());
			try {
				fileLoader.saveStream(fileDescriptor, () -> new ByteArrayInputStream(filaData));
			} catch (FileStorageException e) {
				e.printStackTrace();
			}
			
			txDataManager.save(fileDescriptor);
			
			return fileDescriptor;
		} catch (Exception e) {
			throw new Exception("Error createFileDescriptor: \n" +
					e.getLocalizedMessage());
		}
	}
	
	
	private void writeEmplStaffdatas(StaffDatas staffDatas, Empl empl) throws Exception {
		try {
			empl.getUser().setPosition(staffDatas.getPost());
			empl.setTabnumber(staffDatas.getTabNumber());
			
			// Processing Empl OrgUnits
			List<OrgUnit> unitList = this.getEmplOrgUnitList((ArrayList<String>)
					staffDatas.getOrgUnitsList());
			if (!unitList.isEmpty()) {
				empl.setOrgunits(unitList);
				
				String postPath = "";
				for (OrgUnit emplOrgUnit : unitList) {
					if ("".equalsIgnoreCase(postPath)) {
						postPath = emplOrgUnit.getShortname();
						
					} else {
						postPath += "\\" + emplOrgUnit.getShortname();
					}
				}
				
				empl.setPostpath(postPath);
				
			}
			
		} catch (Exception e) {
			throw new Exception("Error writeEmplStaffdatas: \n" +
					e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Update Employees
	 */
	@Override
	public void updateEmployees() {
		try {
			logger.info("Start updating Employees");
			String authToken = this.getAuthToken();
			
			if (authToken == null) throw new Exception("Cannot get Autentification Token");
			
			logger.info("Getting OkHttpClient");
			OkHttpClient httpClient = this.getHttpClient();
			
			logger.info("Getting Gson");
			Gson gson = this.getGson();
			
			logger.info("Getting Retrofit");
			Retrofit retrofit = this.getRetrofit(httpClient, gson);
			
			logger.info("Calling Service");
			DominoIntegration service = retrofit.create(DominoIntegration.class);
			Call<List<Employee>> employees = service.createEmployees(authToken);
			
			Response<List<Employee>> listResponse = employees.execute();
			
			if (!listResponse.isSuccessful()) throw new Exception("Service Returned Error: \"+" +
					listResponse.errorBody().string());
			
			logger.info("Process Service Response Results");
			
			List<Employee> emplResponseList = listResponse.body();
			for (Employee employee : emplResponseList) {
				try {
					//Обновление данных по сотрудникам
					this.updateEmployee(employee);
				} catch (Exception e) {
					logger.error("Error response process: " + e.getLocalizedMessage())
					;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			logger.info("End process updating Employees");
		}
	}
	
	/**
	 * Updating Empl Datas
	 *
	 * @param employee Employee object
	 * @throws Exception
	 */
	private void updateEmployee(Employee employee) throws Exception {
		try {
			ArrayList<String> updateAttributes = new ArrayList<String>();
			//1. Поиск записи сущности
			Empl empl = emplService.getEmplbyOuterEmployee(employee);
			if (empl == null) return;
			
			//2. Формирование массива с данными для обновления
			CommonDatas employeeCommonDatas = employee.getCommonDatas();
			if (employeeCommonDatas != null) {
				ArrayList<String> commonUpdateAttributesList =
						this.getCommonUpdateAttributesArrayList(empl, employeeCommonDatas);
				updateAttributes.addAll(commonUpdateAttributesList);
			}
			
			// кадровые атрибуты
			StaffDatas employeeStaffDatas = employee.getStaffDatas();
			if (employeeStaffDatas != null) {
				ArrayList<String> staffUpdateAttributesList = this.getStaffUpdateAttributesList(empl,
						employeeStaffDatas);
				updateAttributes.addAll(staffUpdateAttributesList);
				
			}
			
			// атрибуты месторасположения
			Placement emplPlacement = employee.getPlacement();
			if (emplPlacement != null) {
				ArrayList<String> placementUpdateAttributesList = this.getPlacementUpdateAttributesList(empl,
						emplPlacement);
				updateAttributes.addAll(placementUpdateAttributesList);
			}
			
			// фото сотрудника
			FileDescriptor emplPhotoFileDescriptor = empl.getPhoto();
			long photoFileSize = employee.getPhotoDatas().getPhotoFileBytes().length;
			if (!emplPhotoFileDescriptor.getSize().equals(photoFileSize)) {
				updateAttributes.add("PHOTO");
			}
			
			
			// проходимся по полученному массиву и изменяем указанные атрибуты
			if (!updateAttributes.isEmpty()) {
				logger.info("Updating Empl: " + employee.getCommonDatas().getLogin());
				this.updateEmplAttributes(empl, employee, updateAttributes);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error updateEmployee: \n" +
					e.getLocalizedMessage());
		}
	}
	
	
	/**
	 * Получение массива общих изменяемых атрибутов
	 *
	 * @param empl                запись в сущности Empl
	 * @param employeeCommonDatas - общие данные из LN по сотруднику
	 * @return массив изменяемых атрибутов
	 * @throws Exception
	 */
	private ArrayList<String> getCommonUpdateAttributesArrayList(Empl empl,
	                                                             CommonDatas employeeCommonDatas) throws Exception {
		try {
			
			ArrayList<String> attrList = new ArrayList<>();
			User emplUser = empl.getUser();
			String notesName = empl.getNotesname();
			String email = emplUser.getEmail();
			String lastName = emplUser.getLastName();
			String firstName = emplUser.getFirstName();
			String middleName = emplUser.getMiddleName();
			String sex = null;
			if (empl.getSex() != null) {
				sex = empl.getSex().getId();
			}
			
			if (!StringUtils.equalsIgnoreCase(sex, employeeCommonDatas.getSex())) {
				logger.info("StringUtils not equals");
				attrList.add("SEX");
			}
			
			
			if (!StringUtils.equalsIgnoreCase(notesName, employeeCommonDatas.getNotesName())) {
				attrList.add("NOTESNAME");
			}
			
			if (!StringUtils.equalsIgnoreCase(email, employeeCommonDatas.getEmail())) {
				attrList.add("EMAIL");
			}
			
			if (!StringUtils.equalsIgnoreCase(lastName, employeeCommonDatas.getLastName())) {
				attrList.add("LASTNAME");
			}
			
			
			if (!StringUtils.equalsIgnoreCase(firstName, employeeCommonDatas.getFirstName())) {
				attrList.add("FIRSTNAME");
			}
			
			if (!StringUtils.equalsIgnoreCase(middleName, employeeCommonDatas.getMiddleName())) {
				attrList.add("MIDDLENAME");
			}
			
			return attrList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error getCommonUpdateAttributesArrayList: \n" +
					e.getMessage());
		}
	}
	
	/**
	 * Получение кадровых атрибутов для изменения
	 *
	 * @param empl               запись в сущности Empl
	 * @param employeeStaffDatas кадровые данные по сотруднику из LN
	 * @return массив кадровых  атрибутов для изменения
	 * @throws Exception
	 */
	private ArrayList<String> getStaffUpdateAttributesList(Empl empl,
	                                                       StaffDatas employeeStaffDatas) throws Exception {
		try {
			ArrayList<String> resultList = new ArrayList<String>();
			User emplUser = empl.getUser();
			
			
			String position = employeeStaffDatas.getPost();
			ArrayList<String> orgUnitesArrayList = (ArrayList<String>) employeeStaffDatas.getOrgUnitsList();
			Employee emplChief = employeeStaffDatas.getChief();
			
			if (emplChief != null) {
				String chiefName = emplChief.getCommonDatas().getNotesName();
				
				Empl chief = empl.getChief();
				if (chief != null) {
					if (!StringUtils.equalsIgnoreCase(chiefName, chief.getNotesname())) {
						resultList.add("CHIEF");
					}
				}
			}
			if (!StringUtils.equalsIgnoreCase(position, emplUser.getPosition())) {
				resultList.add("POSITION");
			}
			
			List<OrgUnit> orgunits = empl.getOrgunits();
			
			for (OrgUnit orgUnit : orgunits) {
				String orgUnitExtId = orgUnit.getExtid();
				if (!orgUnitesArrayList.contains(orgUnitExtId)) {
					resultList.add("ORGUNIT");
				}
			}
			
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error getStaffUpdateAttributesList: \n" +
					e.getMessage());
		}
		
	}
	
	
	private ArrayList<String> getPlacementUpdateAttributesList(Empl empl,
	                                                           Placement emplPlacement) throws Exception {
		try {
			ArrayList<String> resulList = new ArrayList<>();
			
			Branch emplBranch = empl.getBranch();
			if (emplBranch != null) {
				
				String emplBranchCode = emplBranch.getCode();
				if (!StringUtils.equalsIgnoreCase(emplBranchCode, emplPlacement.getBranchCode())) {
					resulList.add("BRANCH");
				}
			}
			
			String city = empl.getCity();
			if (!StringUtils.equalsIgnoreCase(city, emplPlacement.getCity())) {
				resulList.add("CITY");
			}
			
			String phone = empl.getPhone();
			if (!StringUtils.equalsIgnoreCase(phone, emplPlacement.getPhone())) {
				resulList.add("PHONE");
			}
			
			String office = empl.getOffice();
			if (!StringUtils.equalsIgnoreCase(office, emplPlacement.getOffice())) {
				resulList.add("OFFICE");
			}
			
			String extPhone = empl.getExtphone();
			if (!StringUtils.equalsIgnoreCase(extPhone, emplPlacement.getExtPhone())) {
				resulList.add("EXTPHONE");
			}
			
			String mobilePhone = empl.getMobilephone();
			String employeeMobile = emplPlacement.getMobilePhone();
			if (employeeMobile.isEmpty()) employeeMobile = null;
			if (!StringUtils.equalsIgnoreCase(mobilePhone, employeeMobile)) {
				resulList.add("MOBILEPHONE");
			}
			
			String room = empl.getRoom();
			String employeeRoom = emplPlacement.getRoom();
			if (employeeRoom.isEmpty()) employeeRoom = null;
			if (!StringUtils.equalsIgnoreCase(room, employeeRoom)) {
				resulList.add("ROOM");
			}
			
			return resulList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error getPlacementUpdateAttributesList: \n" +
					e.getMessage());
		}
	}
	
	/**
	 * Обновление атрибутов сотрудника
	 *
	 * @param updateAttrsArrayList массив атрибутов для обновления
	 * @param empl                 запись по сотруднику в сущности Empl
	 * @param employee             данные по сотруднику из LN
	 */
	@Transactional
	private void updateEmplAttributes(Empl empl, Employee employee,
	                                  ArrayList<String> updateAttrsArrayList) throws Exception {
		try {
			
			User emplUser = empl.getUser();
			for (String updateAttributeName : updateAttrsArrayList) {
				this.updateEmplAttribute(updateAttributeName, empl, emplUser, employee);
			}
			
			txDataManager.save(emplUser);
			txDataManager.save(empl);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error updateEmplAttributes: \n" +
					e.getLocalizedMessage());
		}
		
	}
	
	private void updateEmplAttribute(String attributeName, Empl empl, User emplUser, Employee employee) throws Exception {
		try {
			String emplFullName = employee.getCommonDatas().getLastName() + " " +
					employee.getCommonDatas().getFirstName() + " " +
					employee.getCommonDatas().getMiddleName();
			
			switch (attributeName) {
				
				case "SEX":
					Sex sex = Sex.fromId(employee.getCommonDatas().getSex());
					if (sex != null) {
						logger.info("Обновляем пол");
						empl.setSex(sex);
					}
					break;
				case "NOTESNAME":
					logger.info("Обновленеи notes-имени");
					empl.setNotesname(employee.getCommonDatas().getNotesName());
					break;
				case "EMAIL":
					logger.info("Обновление email");
					emplUser.setEmail(employee.getCommonDatas().getEmail());
					break;
				case "LASTNAME":
					logger.info("Обновление фамилиии");
					emplUser.setLastName(employee.getCommonDatas().getLastName());
					emplUser.setName(emplFullName);
					break;
				case "FIRSTNAME":
					logger.info("Обновление имени");
					emplUser.setFirstName(employee.getCommonDatas().getFirstName());
					emplUser.setName(emplFullName);
					break;
				case "MIDDLENAME":
					logger.info("Обновление отчества");
					emplUser.setMiddleName(employee.getCommonDatas().getMiddleName());
					emplUser.setName(emplFullName);
					break;
				case "POSITION":
					logger.info("Обновление должности");
					emplUser.setPosition(employee.getStaffDatas().getPost());
					break;
				case "CITY":
					logger.info("Обновление города");
					empl.setCity(employee.getPlacement().getCity());
					break;
				case "PHONE":
					logger.info("Обновление телефона");
					empl.setPhone(employee.getPlacement().getPhone());
					break;
				case "EXTPHONE":
					logger.info("Обновление добавочного телефона");
					empl.setExtphone(employee.getPlacement().getExtPhone());
					break;
				case "MOBILEPHONE":
					logger.info("Обновление мобильного телефона");
					empl.setMobilephone(employee.getPlacement().getMobilePhone());
					break;
				
				case "ROOM":
					logger.info("Обновление номера комнаты");
					empl.setRoom(employee.getPlacement().getRoom());
					break;
				case "OFFICE":
					logger.info("Обновление офиса");
					empl.setOffice(employee.getPlacement().getOffice());
					break;
				case "CHIEF":
					Empl emplChief = emplService.getEmplbyOuterEmployee(employee.getStaffDatas().getChief());
					if (emplChief != null) {
						logger.info("Обновление руководителя");
						empl.setChief(emplChief);
					}
					break;
				
				case "ORGUNIT":
					ArrayList<String> employeeOrgUnitsList = (ArrayList<String>)
							employee.getStaffDatas().getOrgUnitsList();
					List<OrgUnit> unitList = this.getEmplOrgUnitList(employeeOrgUnitsList);
					if (!unitList.isEmpty()) {
						logger.info("Обновление подраздедений");
						empl.setOrgunits(unitList);
					}
					break;
				
				case "BRANCH":
					String branchCode = employee.getPlacement().getBranchCode();
					
					Branch emplBranch = this.getBranchByCode(branchCode);
					if (emplBranch != null) {
						logger.info("Обновление филиала");
						empl.setBranch(emplBranch);
					}
					break;
				
				case "PHOTO":
					FileDescriptor descriptor = this.getPhotoDescriptor(employee.getPhotoDatas());
					if (descriptor != null) {
						logger.info("Обновление фото сотрудника");
						empl.setPhoto(descriptor);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error updateEmplAttribute: \n" +
					e.getMessage());
		}
	}
}