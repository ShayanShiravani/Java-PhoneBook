package address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhoneNumber {
	
	private String phoneNum;
	private TypeNumber Type ;
	private String customLable;
	
	
	
	
	public String getCustomLable() {
		return customLable;
	}
	public void setCustomLable(String customLable) {
		this.customLable = customLable;
	}
	public TypeNumber getType() {
		return Type;
	}
	public void setType(TypeNumber type) {
		Type = type;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
	public StringProperty phoneNumberProperty(){
		StringProperty pN = new SimpleStringProperty(getPhoneNum());
		return pN;
	}
	
}
