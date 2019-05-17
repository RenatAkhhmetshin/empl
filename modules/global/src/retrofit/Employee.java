package retrofit;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private StaffDatas staffDatas;
	private Placement placement;
	private CommonDatas commonDatas;
	private  PhotoDatas photoDatas;
	
	public Employee() {
	
	}
	
	
	public StaffDatas getStaffDatas() {
		return staffDatas;
	}
	
	public void setStaffDatas(StaffDatas staffDatas) {
		this.staffDatas = staffDatas;
	}
	
	public Placement getPlacement() {
		return placement;
	}
	
	public void setPlacement(Placement placement) {
		this.placement = placement;
	}
	
	public CommonDatas getCommonDatas() {
		return commonDatas;
	}
	
	public void setCommonDatas(CommonDatas commonDatas) {
		this.commonDatas = commonDatas;
	}
	
	public PhotoDatas getPhotoDatas() {
		return photoDatas;
	}
	
	public void setPhotoDatas(PhotoDatas photoDatas) {
		this.photoDatas = photoDatas;
	}
}
	

