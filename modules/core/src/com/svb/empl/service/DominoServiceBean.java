package com.svb.empl.service;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import com.svb.empl.core.EmployeeCreator;
import com.svb.empl.entity.*;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
	private UserService userService;
	
	@Inject
	private Persistence persistence;
	
	
	@Inject
	private Metadata metadata;
	
	@Inject
	private DataManager dataManager;
	private String userSearchbyLoginQuery = "select e from sec$User e where e.login = :userlogin";
	
	private  String defaultGroupID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
	
	@Inject
	private EmployeeCreator employeeCreator;
	
	@Inject
	private FileLoader fileLoader;
	
	@Inject
	private TimeSource timeSource;
	
	private CommitContext commitContext;
	
	
	private ArrayList<Empl> processedLogins;
	
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
			
		}catch (Exception e) {
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
						logger.info("Need create new employee: "+ userLogin);
						empl = this.createEmpl(employee);
						dataManager.commit(empl);
						
					}
				}
				
				//dataManager.commit(commitContext);
				
				
			} else {
				logger.info("Retrofit обработал с ошибкой");
				logger.error("Retrofit error body:"+
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
				logger.error("listResponse error:"+
						listResponse.errorBody().string());
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private User getUserbyLogin (String userLogin) {
		
		User user = null;
		Optional<User> optionalUser = dataManager.load(User.class).
				query(userSearchbyLoginQuery).
				parameter("userlogin", userLogin).optional();
		
		
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		
		
		return user;
	}
	
	private Empl getEmplbyLogin (String userLogin) {
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
	 * @param employee объект с данными по сотруднику
	 *
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
		
		return  empl;
	}
	
	
	private List<Post> getEmplPostList (ArrayList<String> employeePostArrayList ) {
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
	 * @param employeeOrgUnitsList
	 * @return
	 */
	private List<OrgUnit> getEmplOrgUnitList (ArrayList<String> employeeOrgUnitsList) {
		ArrayList<OrgUnit> units = new ArrayList<OrgUnit>();
		
		for (String employeeOrgUnitUnid : employeeOrgUnitsList) {
			OrgUnit orgUnit = this.getOrgUnitbyNotesUNID(employeeOrgUnitUnid);
			if (orgUnit != null) {
				if (! units.contains(orgUnit)) units.add(orgUnit);
			}
		}
		
		return  units;
	}
	
	/**
	 * Создание нового пользователя
	 * @param employee данные по сотруднику
	 * @return объект класса User
	 */
	private User createUser (Employee employee) {
		User user = metadata.create(User.class);
		user.setActive(Boolean.TRUE);
		String fullName = employee.getCommonDatas().getLastName()+" "+
				employee.getCommonDatas().getFirstName()+" "+
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
	 * @param photoDatas данные фото сотрудника
	 * @return Объект FileDescriptor
	 */
	private FileDescriptor getPhotoDescriptor (PhotoDatas photoDatas) {
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
	 * @param emplOrgUnit данные по  подразделению
	 */
	private OrgUnit createOrgUnit (EmplOrgUnit emplOrgUnit) {
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
	 * @param docUNID UNID карточки подразделения
	 * @return запись сущности Подразделения
	 */
	private OrgUnit getOrgUnitbyNotesUNID (String docUNID) {
		Optional<OrgUnit> orgUnitOptional = dataManager.load(OrgUnit.class).
				query(orgUnitbyUNIDSearchQuery).
				parameter("extid", docUNID).
				optional();
		if (orgUnitOptional.isPresent()) return orgUnitOptional.get();
		
		return null;
	}
	
	/**
	 * Получение данных по филиалу сотрудника
	 * @param branchCode код филиала
	 * @return объект Branch
	 */
	private Branch getBranchByCode (String branchCode) {
	    Branch branch = null;
	    Optional<Branch> optionalBranch = dataManager.load(Branch.class).
			    query(emplBranchSearchQuery).
			    parameter("branchcode" , branchCode).
			    optional();
	    
	    if (optionalBranch.isPresent()) {
	    	branch = optionalBranch.get();
	    }
	    
	    return branch;
	}
	
}