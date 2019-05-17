package retrofit;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Кадровые данные по сотруднику
 * @author adms-Ahmetshin-RM
 *
 */
public class StaffDatas implements Serializable {
	private String post;
	private String tabNumber;
	
	private Date wordDate;
	private Date dismissDate;
	private Date startDecreeDate;
	private Date endDecreeDate;
	private Employee chief;
	private List<String> orgUnitsList;
	private List<String> postsList;
	
	public StaffDatas() {
	
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
	
	public Employee getChief() {
		return chief;
	}
	
	public void setChief(Employee chief) {
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
