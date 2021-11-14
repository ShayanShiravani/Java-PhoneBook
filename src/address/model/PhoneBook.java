package address.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class PhoneBook implements Synchronize , Serializable {
	
	private ArrayList<Contact> contacts;
	private	ArrayList<Group> groups;
	private ArrayList<Contact>	favoriteContacts;
	private	String email;
	private static final long serialVersionUID = 1L;
	
	
	public PhoneBook(){
		this.contacts = new ArrayList<Contact>();
		this.groups = new ArrayList<Group> ();
		this.favoriteContacts = new ArrayList<Contact>();
	}
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	

	public ArrayList<Group> getGroup() {
		return groups;
	}

	

	public ArrayList<Contact> getFavoriteContacts() {
		return favoriteContacts;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	public void AddContact(Contact contact){
		contacts.add(contact);
	}
	
	
	public void DeleteContact(Contact contact){
		contacts.remove(contact);
	}
	
	
	public void AddGroup(Group group){
		groups.add(group);
	}
	
	
	public void DeleteGroup(Group group){
		groups.remove(group);
	}
	
	
	public void AddToFavor(Contact contact){
		favoriteContacts.add(contact);
	}
	
	
	public void DeleteFromFavor(Contact contact){
		favoriteContacts.remove(contact);
	}
	
	
	public ArrayList<Contact> instanceSearch(String key){
		ArrayList<Contact> containKey = new ArrayList<Contact>();
		String s1Lower;
		String s2Lower;
		String s3Lower;
		s1Lower = key.toLowerCase();
		
		for(Contact entry : contacts ){
			s2Lower = entry.getFirstName().toLowerCase();
			s3Lower = entry.getLastName().toLowerCase();
			if(s2Lower.contains(s1Lower) || s3Lower.contains(s1Lower)){
				containKey.add(entry);
			}
		}
		return containKey;
	}
	
	//sort contact
	private static class ContactComparator implements Comparator<Contact> {
		@Override
	    public int compare(Contact first, Contact two) {
			int f = first.getFirstName().compareTo(two.getFirstName());
			if(first.getFirstName() == two.getLastName()){
				f = first.getLastName().compareTo(two.getLastName());
			}
	        return f;
	    }
	}
	
	public void isort(){
		contacts.sort(new ContactComparator());
	}
	
	
	public void updateContact(Contact oldContact,Contact newContact){
		contacts.remove(oldContact);
		contacts.add(newContact);
		contacts.sort(new ContactComparator());
	}
	
	
	
	public StringProperty	mailProperty(){
		StringProperty mail = new SimpleStringProperty(getEmail());
		return mail;
	}
	
	public ObservableList<Group> groupObservable(){
		ObservableList<Group> gp = FXCollections.observableArrayList();
		
		for(Group entry : groups ){
			gp.add(entry);
		}
		return gp;
	}
	
	public ObservableList<Contact> contactsObservable(){
		ObservableList<Contact> contactssss = FXCollections.observableArrayList();
		
		for(Contact entry : contacts ){
			contactssss.add(entry);
		}
		return contactssss;
	}
	
	public ObservableList<Contact> fvContactsObservable(){
		ObservableList<Contact> fv = FXCollections.observableArrayList();
		
		for(Contact entry : favoriteContacts){
			fv.add(entry);
		}
		return fv;
	}
	
}
