package address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class RoutLayoutController {
	@FXML	private	MenuButton Option;
	@FXML	private MenuItem sync;
	@FXML	private MenuItem imp;
	@FXML	private MenuItem exp;
	@FXML	private	Button	groups;
	@FXML	private	Button	contacts;
	
	
	private  Main mainApp;
	
	public RoutLayoutController(){
		
	}
	 
	 
	 
	  @FXML
	  private void initialize() {
		  
	  }
	 
	  
	  public void setMain(Main mainApp) {
	        this.mainApp = mainApp;
	        

	  }
	  
	  
	  public void ContactButton(){
		  mainApp.showContactPage();
	  }
	  
	  public void GroupButton(){
		  mainApp.showGroupPage();
	  }
	  
	  
	  public void handleOptionButton(){
		  
	  }

}
