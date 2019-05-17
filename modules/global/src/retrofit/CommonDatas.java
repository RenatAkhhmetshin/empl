package retrofit;

import java.util.Date;

/**
 * Общие данные по сотрулнику
 * @author adms-Ahmetshin-RM
 */
public class CommonDatas {
	
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
	
	public CommonDatas() {
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
