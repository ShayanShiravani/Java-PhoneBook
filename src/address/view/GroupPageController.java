package address.view;

import org.controlsfx.dialog.Dialogs;

import address.model.Contact;
import address.model.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@SuppressWarnings("deprecation")
public class GroupPageController {

	@FXML
	private TableView<Contact> allContacts;
	@FXML
	private TableView<Group> allGroups;
	@FXML
	private TableView<Contact> allContactsGroup;
	@FXML
	private Button addContact;
	@FXML
	private Button delContact;
	@FXML
	private Button addGroup;
	@FXML
	private Button delGroup;
	
	@FXML
	private TableColumn<Contact, String> allContactsColumn;
	@FXML
	private TableColumn<Group, String>	allGroupsColumn;
	@FXML
	private TableColumn<Contact, String> allContactsGroupColumn;
	@FXML
	private TextField groupName;
	
	private Contact contact;
	private Group group;
    private Main mainApp;
    
    
    @FXML
    private void initialize() {
    	
    	allContactsColumn.setCellValueFactory(
				 cellData -> cellData.getValue().nameProperty());
    	showGroupDetails(null);
		allGroupsColumn.setCellValueFactory(
				 cellData -> cellData.getValue().gpNameProperty());
		allGroups.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showGroupDetails(newValue));
    }
    
    public void setMain(Main mainApp) {
		 this.mainApp = mainApp;
		 
	    	
		 allContacts.setItems(mainApp.getContctData());
		 allGroups.setItems(mainApp.getGroupData());
	 }
    
    
    
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    
   
    
    @FXML
    private void hanleAddContact(){
    	if(allContacts.getSelectionModel().getSelectedItem() == null
    			|| allGroups.getSelectionModel().getSelectedItem() == null){
    			Dialogs.create()
    			.title("No contact or group selected")
    			.masthead("No contact or group selected")
    			.message("Please select contact and group.")
    			.showWarning();
    	}else{
    		group = allGroups.getSelectionModel().getSelectedItem();
    		group.addContact(allContacts.getSelectionModel().getSelectedItem());
    		showGroupDetails(group);
    	}
    	
    }
    
    @FXML
    private void handleDelContact(){
    	if(allContactsGroup.getSelectionModel().getSelectedItem() == null ||
    			allGroups.getSelectionModel().getSelectedItem() == null ||
    			!allGroups.getSelectionModel().getSelectedItem().contactsObservable().isEmpty()){
    		Dialogs.create()
    		.title("No contact or group selected")
    		.masthead("No contact or group selected")
    		.message("Please select contact and group.")
    		.showWarning();
    	}else{
    		group = allGroups.getSelectionModel().getSelectedItem();
    		group.deleteContact(allContactsGroup.getSelectionModel().getSelectedItem());
    		showGroupDetails(group);
    	}
    }
    
    @FXML
    private void hanleAddGroup(){
    	if(groupName.getText().isEmpty()){
    		Dialogs.create()
    		.title("Name is empty")
    		.masthead("Name should not empty")
    		.message("Please enter name.")
    		.showWarning();
    	}else{
    	Group g = new Group();
    		g.setNmaeGroup(groupName.getText());
    		mainApp.getpBook().AddGroup(g);
    		mainApp.getGroupData().add(g);
    		groupName.clear();
    	}
    }
    
    @FXML
    private void handleDelGroup(){
    	
    	int selectedIndex = allGroups.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			mainApp.getpBook().DeleteGroup(allGroups.getSelectionModel().getSelectedItem());
			allGroups.getItems().remove(selectedIndex);
			
		} else {
			Dialogs.create()
    		.title("No group selected")
    		.masthead("No group selected")
    		.message("Please select one group.")
    		.showWarning();
		}
    }
    
    
    private void showGroupDetails(Group group) {
	    if (group != null ) {
	    	allContactsGroup.setItems(group.contactsObservable());
	    	allContactsGroupColumn.setCellValueFactory(
					 cellData -> cellData.getValue().nameProperty());
	    } else {
	    	allContactsGroupColumn.setCellFactory(null);
	    }
	}
   
    
	

}
