package retrofit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Данные по сотрудникам
 * @author adms-Ahmetshin-RM
 */
public class EmployeeNew implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private StaffInfo staffInfo;
	private Placement placement;
	private PhotoInfo photoInfo;
	private CommonInfo commonInfo;
	
	
	
	public EmployeeNew() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public StaffInfo getStaffInfo() {
		return staffInfo;
	}
	
	
	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}
	
	public Placement getPlacement() {
		return placement;
	}
	
	public void setPlacement(Placement placement) {
		this.placement = placement;
	}
	
	
	public PhotoInfo getPhotoInfo() {
		return photoInfo;
	}
	
	public void setPhotoInfo(PhotoInfo photoInfo) {
		this.photoInfo = photoInfo;
	}
	
	public CommonInfo getCommonInfo() {
		return commonInfo;
	}
	
	public void setCommonInfo(CommonInfo commonInfo) {
		this.commonInfo = commonInfo;
	}
	
	/**
	 * Общие данные по сотруднику
	 * @author Ahmetshin-RM
	 *
	 */
	public class CommonInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String firstName;
		private String lastName;
		private String middleName;
		private String email;
		private String login;
		private Date birthDate;
		private String sex;
		private String notesName;
		private String extID;
		private boolean decree;
		private boolean dismiss;
		
		public CommonInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public Date getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getNotesName() {
			return notesName;
		}
		public void setNotesName(String notesName) {
			this.notesName = notesName;
		}
		public String getExtID() {
			return extID;
		}
		public void setExtID(String extID) {
			this.extID = extID;
		}
		public boolean isDecree() {
			return decree;
		}
		public void setDecree(boolean decree) {
			this.decree = decree;
		}
		public boolean isDismiss() {
			return dismiss;
		}
		public void setDismiss(boolean dismiss) {
			this.dismiss = dismiss;
		}
		
		
		
	}
	
	
	/**
	 * Данные по  фото сотрудника
	 * @author Ahmetshin-RM
	 *
	 */
	public class PhotoInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		private String photofileName;
		private String photoFileExtension;
		private byte [] photoFileBytes;
		public PhotoInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getPhotofileName() {
			return photofileName;
		}
		public void setPhotofileName(String photofileName) {
			this.photofileName = photofileName;
		}
		public String getPhotoFileExtension() {
			return photoFileExtension;
		}
		public void setPhotoFileExtension(String photoFileExtension) {
			this.photoFileExtension = photoFileExtension;
		}
		public byte[] getPhotoFileBytes() {
			return photoFileBytes;
		}
		public void setPhotoFileBytes(byte[] photoFileBytes) {
			this.photoFileBytes = photoFileBytes;
		}
		
		
	}
	
	
	/**
	 * Данные о месторасположении сотрудника
	 * @author Ahmetshin-RM
	 *
	 */
	public class Placement implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private String city;
		private String branchCode;
		private String office;
		private String phone;
		private String extPhone;
		private String mobilePhone;
		public Placement() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getBranchCode() {
			return branchCode;
		}
		public void setBranchCode(String branchCode) {
			this.branchCode = branchCode;
		}
		public String getOffice() {
			return office;
		}
		public void setOffice(String office) {
			this.office = office;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getExtPhone() {
			return extPhone;
		}
		public void setExtPhone(String extPhone) {
			this.extPhone = extPhone;
		}
		public String getMobilePhone() {
			return mobilePhone;
		}
		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}
		
		
	}
	
	
	/**
	 * Кадровые данные по сотруднику
	 * @author Ahmetshin-RM
	 *
	 */
	public class StaffInfo  implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private String post;
		private String tabNumber;
		
		private Date wordDate;
		private Date dismissDate;
		private Date startDecreeDate;
		private Date endDecreeDate;
		private EmployeeNew chief;
		private List <String> orgUnitsList;
		private List <String> postsList;
		
		public StaffInfo() {
			super();
			
		}
		
		public String getPost() {
			return post;
		}
		
		public void setPost(String post) {
			this.post = post;
		}
		
		public String getTabNumber() {
			return tabNumber;
		}
		
		public void setTabNumber(String tabNumber) {
			this.tabNumber = tabNumber;
		}
		
		public Date getWordDate() {
			return wordDate;
		}
		
		public void setWordDate(Date wordDate) {
			this.wordDate = wordDate;
		}
		
		public Date getDismissDate() {
			return dismissDate;
		}
		
		public void setDismissDate(Date dismissDate) {
			this.dismissDate = dismissDate;
		}
		
		public Date getStartDecreeDate() {
			return startDecreeDate;
		}
		
		public void setStartDecreeDate(Date startDecreeDate) {
			this.startDecreeDate = startDecreeDate;
		}
		
		public Date getEndDecreeDate() {
			return endDecreeDate;
		}
		
		public void setEndDecreeDate(Date endDecreeDate) {
			this.endDecreeDate = endDecreeDate;
		}
		
		public EmployeeNew getChief() {
			return chief;
		}
		
		public void setChief(EmployeeNew chief) {
			this.chief = chief;
		}
		
		public List<String> getOrgUnitsList() {
			return orgUnitsList;
		}
		
		public void setOrgUnitsList(List<String> orgUnitsList) {
			this.orgUnitsList = orgUnitsList;
		}
		
		public List<String> getPostsList() {
			return postsList;
		}
		
		public void setPostsList(List<String> postsList) {
			this.postsList = postsList;
		}
		
	}
	
}
