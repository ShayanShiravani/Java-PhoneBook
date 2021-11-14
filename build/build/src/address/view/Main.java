package address.view;
	
import java.io.IOException;

import address.model.Contact;
import address.model.Group;
import address.model.PhoneBook;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	private ObservableList<Contact> contactData = FXCollections.observableArrayList();
	private ObservableList<Group> groupData = FXCollections.observableArrayList();
	
	private PhoneBook pBook = new PhoneBook(); 
	
	@Override
	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,1000,600);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		this.primaryStage = primaryStage;
		this.primaryStage.setMaxHeight(640);
		this.primaryStage.setMinHeight(640);
		this.primaryStage.setMaxWidth(1020);
		this.primaryStage.setMinWidth(1020);
        this.primaryStage.setTitle("Phone Book");
        this.primaryStage.getIcons().add(new Image("file:build/package/windows/PhoneBook.ico"));

        initRootLayout();
        
        showContactPage();
        
	}
	
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
           
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            
            
            rootLayout = (BorderPane) loader.load();
            
           
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            RoutLayoutController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public void showContactPage() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ContactPage.fxml"));
            AnchorPane ContactPage = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(ContactPage);
            
            // Give the controller access to the main app.
            ContactPageController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showGroupPage() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("GroupPage.fxml"));
            AnchorPane GroupPage = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(GroupPage);
            
            // Give the controller access to the main app.
            GroupPageController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showContactEditDialog(Contact contact) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("EditContactPage.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Contact");
			dialogStage.setMaxHeight(430);
			dialogStage.setMinHeight(430);
			dialogStage.setMaxWidth(1020);
			dialogStage.setMinWidth(1020);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			EditContactController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setContact(contact);
			controller.setMain(this);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isSaveClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public ObservableList<Contact> getContctData() {
    	return contactData;
    }
	
	
	
	public ObservableList<Group> getGroupData() {
    	return groupData;
    }
	
	public PhoneBook getpBook() {
		return pBook;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
