package address.view;

import org.controlsfx.dialog.Dialogs;

import address.model.Address;
import address.model.CommonType;
import address.model.Contact;
import address.model.Email;
import address.model.PhoneNumber;
import address.model.TypeNumber;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class EditContactController {
	@FXML
	private TextField FirstName;
	@FXML
	private TextField LastName;
	@FXML
	private ChoiceBox<TypeNumber> phoneNumberType;
	@FXML
	private TextField	phoneNumber;
	@FXML
	private Button	addPhoneNumber;
	@FXML
	private Button	delPhoneNumber;
	
	@FXML
	private ChoiceBox <CommonType> addressType; 
	@FXML
	private TextField	address;
	@FXML
	private Button	addAddress;
	@FXML
	private Button	delAddress;
	
	@FXML
	private ChoiceBox<CommonType> emailType; 
	@FXML
	private TextField	email;
	@FXML
	private Button	addEmail;
	@FXML
	private Button	delEmail;
	
	@FXML
	private Button	saveChange;
	@FXML
	private Button	cancelChange;
	
	@FXML
	private TableView<PhoneNumber> phoneNumbers;
	@FXML
    private TableColumn<PhoneNumber, String> phoneNumberColumn;
	
	@FXML
	private TableView<Address> addresses;
	@FXML
    private TableColumn<Address, String> addressColumn;
	@FXML
	private TableView<Email> emails;
	@FXML
    private TableColumn<Email, String> emailColumn;
	
	private Stage dialogStage;
    private Contact contact;
    private boolean saveClicked = false;
    private Main mainApp;
   
    private ObservableList<Address> addr = FXCollections.observableArrayList();
    private ObservableList<Email> mail = FXCollections.observableArrayList();
    private ObservableList<PhoneNumber> pNum = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
    	
    	
        phoneNumberColumn.setCellValueFactory(
				 cellData -> cellData.getValue().phoneNumberProperty());
		emailColumn.setCellValueFactory(
				 cellData -> cellData.getValue().emailProperty());
		addressColumn.setCellValueFactory(
				 cellData -> cellData.getValue().addressProperty());
    	
    	
    	ObservableList<TypeNumber> t = FXCollections.observableArrayList(TypeNumber.Mobile,
    			TypeNumber.Home,TypeNumber.Work,TypeNumber.Other);
    	phoneNumberType.setItems(t);
    	phoneNumberType.getSelectionModel().selectFirst();
    	
    	ObservableList<CommonType> c = FXCollections.observableArrayList(CommonType.Home,
    			CommonType.Work,CommonType.Other);
    	addressType.setItems(c);
    	addressType.getSelectionModel().selectFirst();
    	
    	ObservableList<CommonType> co = FXCollections.observableArrayList(CommonType.Home,
    			CommonType.Work,CommonType.Other);
    	emailType.setItems(co);
    	emailType.getSelectionModel().selectFirst();
    	
    	
    	
    	
    	
    }
    
    
    
    public void setMain(Main mainApp) {
		 this.mainApp = mainApp;
		 FirstName.setText(contact.getFirstName());
		 LastName.setText(contact.getLastName());
	     pNum.addAll(contact.phoneNumberObservable());
	     mail.addAll(contact.emailObservable());
	     addr.addAll(contact.adressObservable());
	     phoneNumbers.setItems(pNum);
	     emails.setItems(mail);
	     addresses.setItems(addr);
	     
	 }
    
    
    
    public Contact getContact() {
		return contact;
	}



	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    
    public void setContact(Contact contact) {
        this.contact = contact;
        
      
        
    }
    
    public boolean isSaveClicked() {
        return saveClicked;
    }
    
    
	@FXML
    private void handleSave() {
    	if(FirstName.getText().isEmpty() && LastName.getText().isEmpty()){
    		Dialogs.create()
    		.title("Name is empty")
    		.masthead("Name should not empty")
    		.message("Please enter name.")
    		.showWarning();
    		
    	}else{
    		contact.setFirstName(FirstName.getText());
    		contact.setLastName(LastName.getText());
        	contact.getpNumbers().clear();
        	contact.getpNumbers().addAll(pNum);
        	contact.getMails().clear();
        	contact.getMails().addAll(mail);
        	contact.getAdrs().clear();
        	contact.getAdrs().addAll(addr);
            saveClicked = true;
            dialogStage.close();
    	}
    }
    
    @FXML
    private void handleAddPhoneNumber(){
    	PhoneNumber p = new PhoneNumber();
    	if(phoneNumber.getText() == null){
    		p.setPhoneNum("No Value");
    	}else{
    		p.setPhoneNum(phoneNumber.getText());
    	}
        p.setType(phoneNumberType.getValue());
        pNum.add(p);
        phoneNumber.clear();
        
    }
    
    @FXML
    private void handleAddAddress(){
    	Address a = new Address();
    	if(address.getText() == null){
    		a.setAddress("No Value");
    	}else{
    		a.setAddress(address.getText());
    	}
        a.setType(addressType.getValue());
        addr.add(a);
        address.clear();
        
    }
    
    @FXML
    private void handleAddEmail(){
    	Email e = new Email();
    	if(email.getText() == null){
    		e.setEmailAddress("No Value");
    	}else{
    		e.setEmailAddress(email.getText());
    	}
        e.setType(emailType.getValue());
        mail.add(e);
        email.clear();
    }
    
    @FXML
    private  void handleDelEmail(){
    	if(emails.getSelectionModel().getSelectedItem() != null ){
    		mail.remove(emails.getSelectionModel().getSelectedItem()); 
    	}else{
    		return;
    	}
    }
    
    @FXML
    private  void handleDelPhoneNumber(){
    	if(phoneNumbers.getSelectionModel().getSelectedItem() != null ){
    		pNum.remove(phoneNumbers.getSelectionModel().getSelectedItem()); 
    	}else{
    		return;
    	}
    }
    
    
    @FXML
    private  void handleDelAddress(){
    	if(addresses.getSelectionModel().getSelectedItem() != null ){
    		addr.remove(addresses.getSelectionModel().getSelectedItem()); 
    	}else{
    		return;
    	}
    }
    
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    
    

    

}
