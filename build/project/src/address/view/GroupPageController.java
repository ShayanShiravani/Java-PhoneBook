package address.view;

import address.model.Contact;
import address.model.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
		allGroupsColumn.setCellValueFactory(
				 cellData -> cellData.getValue().gpNameProperty());
		
		allContactsGroupColumn.setCellValueFactory(
				 cellData -> cellData.getValue().nameProperty());
		
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
    	if(allContacts.getSelectionModel().getSelectedItem() == null){
    		return;
    	}
    	group = allGroups.getSelectionModel().getSelectedItem();
    	group.addContact(allContacts.getSelectionModel().getSelectedItem());
    	showGroupDetails(group);
    	
    }
    
    @FXML
    private void handleDelContact(){
    	if(allContacts.getSelectionModel().getSelectedItem() == null){
    		return;
    	}
    	group = allGroups.getSelectionModel().getSelectedItem();
    	group.deleteContact(allContacts.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void hanleAddGroup(){
    	if(groupName.getText().isEmpty()){
    		return;
    	}
    	Group g = new Group();
    	g.setNmaeGroup(groupName.getText());
    	mainApp.getpBook().AddGroup(g);
    	mainApp.getGroupData().add(g);
    	groupName.clear();
    }
    
    @FXML
    private void handleDelGroup(){
    	if(allGroups.getSelectionModel().getSelectedItem() == null){
    		return;
    	}
    	Group gp = allGroups.getSelectionModel().getSelectedItem();
    	mainApp.getpBook().DeleteGroup(gp);
    	mainApp.getGroupData().remove(gp);
    }
    
    
    private void showGroupDetails(Group group) {
	    if (!group.contactsObservable().isEmpty()) {
	    	allContactsGroup.setItems(group.contactsObservable());
	    } else {
	    	return;	
	    }
	}
   
    
	

}
