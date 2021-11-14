package address.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Contact {
	
	private String firstName;
	private String lastName;
	private Favourite favourite;
	
	private ArrayList<PhoneNumber> pNumbers;
	private ArrayList<Email> mails;
	private ArrayList<Address> Adrs;
	private ArrayList<Group> groups;
	
	
	public Contact () {
		this.pNumbers = new ArrayList<PhoneNumber>();
		this.mails  = new ArrayList<Email> ();
		this.Adrs = new ArrayList<Address>();
		this.groups = new ArrayList<Group> ();
		
		firstName = "";
		lastName = "";
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

	public Favourite getFavourite() {
		return favourite;
	}
	public void setFavourite(Favourite favourite) {
		this.favourite = favourite;
	}
	
	public Favourite getTypeFavo() {
		return favourite;
	}
	public void setTypeFavo(Favourite typeFavo) {
		favourite = typeFavo;
	}
	public ArrayList<PhoneNumber> getpNumbers() {
		return pNumbers;
	}
	
	public ArrayList<Email> getMails() {
		return mails;
	}
	public void setMails(ArrayList<Email> mails) {
		this.mails = mails;
	}
	public ArrayList<Address> getAdrs() {
		return Adrs;
	}
	public void setAdrs(ArrayList<Address> adrs) {
		Adrs = adrs;
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void addNewGroup(Group group){
		groups.add(group);
	}
	
	public void addNewPhoneNum(PhoneNumber number){
		
		pNumbers.add(number);
		
	}
	public void AddNewAddress(Address address){
		
		Adrs.add(address);
	}
	
	public void DeletPhoneNum(PhoneNumber num){
		pNumbers.remove(num);
	}
	public void DeletAddress(Address ads){
		Adrs.remove(ads);
	}
	public void DeleteGroup(Group group){
		groups.remove(group);
	}
	
	public StringProperty firstNameProperty() {
		StringProperty fn = new SimpleStringProperty(getFirstName());
		return fn;
	}
	
	public StringProperty lastNameProperty() {
		StringProperty ln = new SimpleStringProperty(getFirstName());
		return ln;
	}
	
	public ObservableList<PhoneNumber> phoneNumberObservable(){
		ObservableList<PhoneNumber> pNum = FXCollections.observableArrayList();
		
		for(PhoneNumber entry : pNumbers ){
			pNum.add(entry);
		}
		return pNum;
	}
	
	public ObservableList<Address> adressObservable(){
		ObservableList<Address > addr = FXCollections.observableArrayList();
		
		for(Address entry : Adrs ){
			addr.add(entry);
		}
		return addr;
	}
	
	public ObservableList<Email> emailObservable(){
		ObservableList<Email> mail = FXCollections.observableArrayList();
		
		for(Email entry : mails ){
			mail.add(entry);
		}
		return mail;
	}
	
	public ObservableList<Group> groupObservable(){
		ObservableList<Group> gp = FXCollections.observableArrayList();
		
		for(Group entry : groups ){
			gp.add(entry);
		}
		return gp;
	}
	
	public void AddNewEmail(Email mail){
		
		mails.add(mail);
	}
	
	public void DeleteEmail(Email mail){
		
		mails.remove(mail);
	}
	
	public StringProperty nameProperty(){
		StringProperty name = new SimpleStringProperty(getFirstName() + " " + getLastName());
		return name;
	}
	
	
}
