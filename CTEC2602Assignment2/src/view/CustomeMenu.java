package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class CustomeMenu extends MenuBar {

	Menu menu ;
	Menu help;
	
	MenuItem saveItem ;
	MenuItem loadItem ;
	MenuItem exitItem ;
   
	MenuItem aboutItem ;
	
	
	public CustomeMenu() {
		super();
		menu = new Menu("File");
		help = new Menu("Help");
		
		saveItem = new MenuItem("Save Student Data");
		loadItem = new MenuItem("Load Studnet Data");
	    exitItem = new MenuItem("Exit");
	   
		aboutItem = new MenuItem("About");
	    
	    help.getItems().add(aboutItem);
	    
	    menu.getItems().add(loadItem);
	    menu.getItems().add(saveItem);
	    menu.getItems().add(new SeparatorMenuItem());
	    menu.getItems().add(exitItem);
	    getMenus().add(menu);
	    getMenus().add(help);
	}
	
	
	public void setActionListeners(EventHandler<ActionEvent> action){
		
		aboutItem.setOnAction(action);
		exitItem.setOnAction(action);
		saveItem.setOnAction(action);
		loadItem.setOnAction(action);
	}

}
