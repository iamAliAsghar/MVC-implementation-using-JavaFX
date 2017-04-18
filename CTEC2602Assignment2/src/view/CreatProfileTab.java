package view;

import java.time.LocalDate;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Course;

public class CreatProfileTab extends Tab {
	
	
	Text selectCourseText;
	Text pNumberText;
	Text firstNameText;
	Text surnameText;
	Text emailText;
	Text pickDateText;
	
	
	TextField pNumberField;
	TextField firstNameField;
	TextField surNameField;
	TextField emailField;
	
	
	DatePicker date;
	private ComboBox<Course> cboCourses;
	
	Button createProfileButton;
	
	public CreatProfileTab(TabPane tabPane){
		
		setText("Student Profile");
        
        GridPane hbox = new GridPane();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setHgap(50);
        hbox.setVgap(10);
        hbox.setAlignment(Pos.CENTER);
        
        
        createProfileButton = new Button("Create Profile");
        
        selectCourseText = new Text("Select Couurse Text");
        cboCourses = new ComboBox<Course>();
        populateComboBox();
        
        
        
        pNumberText = new Text("P number");
        pNumberField = new TextField();
        
        
        firstNameText = new Text("First Name");
        firstNameField = new TextField();
        
        surnameText = new Text("Input Sur Name");
        surNameField = new TextField();
        
        emailText = new Text("Input email");
        emailField = new TextField();
        
        pickDateText = new Text("Pick date");
        date = new DatePicker();
        
        GridPane.setHalignment(selectCourseText, HPos.RIGHT);
        hbox.add(selectCourseText, 0, 0);
        hbox.add(cboCourses, 1, 0);
       
        GridPane.setHalignment(pNumberText, HPos.RIGHT);
        hbox.add(pNumberText, 0, 1);
        hbox.add(pNumberField, 1, 1);
        
        GridPane.setHalignment(firstNameText, HPos.RIGHT);
        hbox.add(firstNameText, 0, 2);
        hbox.add(firstNameField, 1, 2);
        
        GridPane.setHalignment(surnameText, HPos.RIGHT);
        hbox.add(surnameText, 0, 3);
        hbox.add(surNameField, 1, 3);
        
        GridPane.setHalignment(emailText, HPos.RIGHT);
        hbox.add(emailText, 0, 4);
        hbox.add(emailField, 1, 4);
        
        GridPane.setHalignment(pickDateText, HPos.RIGHT);
        hbox.add(pickDateText, 0, 5);
        hbox.add(date, 1, 5);
        
        hbox.add(createProfileButton, 1, 6);
        
      
        
        setContent(hbox);
		
	}
	 private void populateComboBox() {
		 
		 Course[] courses = Controller.setupAndGetCourses();
		 cboCourses.getItems().addAll(courses);
		 cboCourses.getSelectionModel().select(0);
	}
	
	 public String getpNumber(){
		 
		return pNumberField.getText(); 
	 }
	 
	 public void setpNumber(String pNumer){
		 this.pNumberField.setText(pNumer);
		 
	 }
	 
		public String getFirstName(){
			
			return firstNameField.getText();
		}
		public void setFirstName(String firstName){
			
			firstNameField.setText(firstName);
		}
		
		
		
		 public String getSurName(){
			return surNameField.getText();
		 }
		 public void setSurName(String surN){
			 surNameField.setText(surN);
		 }
			  
		 public String getEmail(){
			return emailField.getText();
		 }

		 public void setEmail(String email){
			emailField.setText(email);
		 }
		 
		 public LocalDate getDate(){
			return date.getValue();
			 
		 }
		 public void setDate(LocalDate date){
			 
			 this.date.setValue(date);
		 }
		 
		 
		 public Button getButton(){
			 
			 return createProfileButton;
		 }
		 public Course getCourse(){
			 
			 
			return cboCourses.getSelectionModel().getSelectedItem();
		 }
		 public void setCourseForCombo(Course Course){
			 
			 cboCourses.getSelectionModel().select(Course);
		 }
		 
}
