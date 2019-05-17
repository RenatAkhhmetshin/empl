package retrofit;

import java.io.Serializable;

/**
 * Данные по фото сотрулника
 * @author adms-Ahmetshin-RM
 */
public class PhotoDatas implements Serializable {
	
	
	private String photofileName;
	private String photoFileExtension;
	private byte [] photoFileBytes;
	
	
	public PhotoDatas() {
	
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
