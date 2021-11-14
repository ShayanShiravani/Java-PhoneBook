package address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
	private String address;
	private CommonType typeAddress;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CommonType getType() {
		return typeAddress;
	}
	public void setType(CommonType type) {
		typeAddress = type;
	}
	
	public StringProperty	addressProperty(){
		StringProperty addr = new SimpleStringProperty(getAddress());
		return addr;
	}
	
}	
