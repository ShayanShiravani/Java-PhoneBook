package address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {
	private String emailAddress;
	private CommonType TypeEmail;
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public CommonType getType() {
		return TypeEmail;
	}
	public void setType(CommonType type) {
		TypeEmail = type;
	}
	
	public StringProperty	emailProperty(){
		StringProperty mail = new SimpleStringProperty(getEmailAddress());
		return mail;
	}
}
