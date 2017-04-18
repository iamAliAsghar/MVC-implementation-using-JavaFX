package view;
import model.*;

import java.time.LocalDate;

import controller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class View {
	
	public CreatProfileTab creatProfileTab;
	public InputMarksTab  inputMarksTab;
	public OverviewTab overviewTab;
	CustomeMenu menuBar;
    TabPane tabPane;
    Group root;
    BorderPane borderPane;
    
	public Group getRoot(){
		
		return root;
	}
	
	
	private void initUI() {
		
		
        root = new Group();
        menuBar = new CustomeMenu();
        
        
        tabPane = new TabPane();

        borderPane = new BorderPane();
        
        
        creatProfileTab = new CreatProfileTab(tabPane);
        inputMarksTab   = new InputMarksTab() ;
        overviewTab 	= new OverviewTab();
        tabPane.getTabs().add(creatProfileTab);
        tabPane.getTabs().add(inputMarksTab);    
        tabPane.getTabs().add(overviewTab);    
        
       
      
        borderPane.setCenter(tabPane);
        borderPane.setTop(menuBar);
        root.getChildren().add(borderPane);
        
    }
	
	public void bindBorder(Scene scene){
		 // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
	}
	
	public CustomeMenu getCustomMenu(){
		return menuBar;
	}
	
	
	public View(){
		
		initUI();
	}
	
	public Button getCreateProfileButton(){
		return creatProfileTab.getButton();
	}
	
	public void goToNextTab(){
		
	    	tabPane.getSelectionModel().selectNext();
	}
	
	 public String getpNumber(){
		 
			return creatProfileTab.pNumberField.getText(); 
		 }
	public String getFirstName(){
				
				return creatProfileTab.getFirstName();
	}
    public String getSurName(){
				return creatProfileTab.getSurName();
				 
	 }
	public String getEmail(){
	return creatProfileTab.getEmail();
	}

	public LocalDate getDate(){
	return creatProfileTab.getDate();
	 }
	
	 public Course getCourse(){
	
			return creatProfileTab.getCourse();
		 }
	 
	 
}
