package address.view;

import java.util.ArrayList;

import address.model.Address;
import address.model.Contact;
import address.model.Email;
import address.model.PhoneNumber;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ContactPageController {
	@FXML
	private TextField searchContact;
	@FXML
	private TableView<Contact> allContacts;
	@FXML
    private TableColumn<Contact, String> allContactsColumn;
	@FXML
	private TableView<PhoneNumber> phoneNumbers;
	@FXML
    private TableColumn<PhoneNumber, String> phoneNumbersColumn;
	@FXML
	private TableView<Address> addresses;
	@FXML
    private TableColumn<Address, String> addressesColumn;
	@FXML
	private	TableView<Email> emails;
	@FXML
    private TableColumn<Email, String> emailsColumn;
	@FXML
	private	Label nameLabel;
	@FXML
	private	Button	editContact;
	@FXML
	private Button	deleteContact;
	@FXML
	private Button	addContact;
	
	
	private Main mainApp;
	
	private Contact contact;
	

	public ContactPageController() {
		// TODO Auto-generated constructor stub
		 
	}
	
	
	
	@FXML
	private void initialize() {
		
		allContactsColumn.setCellValueFactory(
        		cellData -> cellData.getValue().nameProperty());
        
        
        showContactDetails(null);

        
		allContacts.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showContactDetails(newValue));
		
	 
	}
	
	public void setMain(Main mainApp) {
		this.mainApp = mainApp;
		allContacts.setItems(mainApp.getContctData());

	}
	
	 
	 
	private void showContactDetails(Contact contact) {
	    if (contact != null) {
	    	nameLabel.setText(contact.getFirstName() + " " + contact.getLastName());
	    	phoneNumbers.setItems(contact.phoneNumberObservable());
			emails.setItems(contact.emailObservable());
			addresses.setItems(contact.adressObservable());
			
	    	phoneNumbersColumn.setCellValueFactory(
	    			cellData -> cellData.getValue().phoneNumberProperty());
	    	emailsColumn.setCellValueFactory(
	    			cellData -> cellData.getValue().emailProperty());
	    	addressesColumn.setCellValueFactory(
	    			cellData -> cellData.getValue().addressProperty());
	    	} else {
	    		phoneNumbersColumn.setCellValueFactory(null);
	    		
	    		emailsColumn.setCellValueFactory(null);
	    		
	    		addressesColumn.setCellValueFactory(null);
	    		
	    	}
	}
	 
	 
	@FXML
	private void handleDeleteContact() {
		int selectedIndex = allContacts.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			mainApp.getpBook().DeleteContact(allContacts.getSelectionModel().getSelectedItem());
			allContacts.getItems().remove(selectedIndex);
			
		} else {
			return;
		}
	}
	 
	 
	@FXML
	private void handleNewContact() {
		Contact temp = new Contact();
		boolean saveClicked = mainApp.showContactEditDialog(temp);
		if (saveClicked) {
			mainApp.getContctData().add(temp);
			mainApp.getpBook().AddContact(temp);
			allContacts.getSelectionModel().select(temp);
		}
	}
	 
	 
	@FXML
	private void handleEditContact() {
		Contact selectedContact = allContacts.getSelectionModel().getSelectedItem();
		if (selectedContact != null) {
			boolean saveClicked = mainApp.showContactEditDialog(selectedContact);
			if (saveClicked) {
				//mainApp.showContactPage();
				showContactDetails(selectedContact);
			}

		} else {
			return;
		}
	}
	
	@FXML
	private void handleSearchContact(){
		
		String key = searchContact.getText();
		ObservableList<Contact> res = FXCollections.observableArrayList();
		ArrayList<Contact> contacts = new ArrayList<>();
		contacts = mainApp.getpBook().instanceSearch(key);
	 	for(Contact entry : contacts){
			res.add(entry);
		}
		allContacts.setItems(res);
		
		
	}
	
	

}
