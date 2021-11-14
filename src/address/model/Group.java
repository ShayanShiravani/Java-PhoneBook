package address.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Group {
	private String NmaeGroup;
	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	
	
	public String getNmaeGroup() {
		return NmaeGroup;
	}
	public void setNmaeGroup(String nmaeGroup) {
		NmaeGroup = nmaeGroup;
	}
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public void	addContact(Contact contact){
		contacts.add(contact);
	}
	public void deleteContact(Contact contact){
		contacts.remove(contact);
	}
	public void showContactOfGp(){
		for(Contact entry : contacts){
			System.out.println(entry.getFirstName());
			System.out.println(entry.getLastName());
		}
	}
	
	public StringProperty	gpNameProperty(){
		StringProperty gpName = new SimpleStringProperty(getNmaeGroup());
		return gpName;
	}
	
	public ObservableList<Contact> contactsObservable(){
		ObservableList<Contact> contactssss = FXCollections.observableArrayList();
		for(Contact entry : contacts ){
			contactssss.add(entry);
		}
		return contactssss;
	}

}
