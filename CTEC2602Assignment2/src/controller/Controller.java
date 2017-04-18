package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Collection;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.*;
import view.View;


public class Controller implements EventHandler {
	
	View view;
	StudentProfile model;
	
	public Controller(View view, StudentProfile studentProfile){
		
		
		this.view= view;
		this.model =studentProfile;
		view.getCreateProfileButton().setOnAction(this);
		view.inputMarksTab.getSubmitButton().setOnAction(this);
		view.overviewTab.getSaveOverviewButton().setOnAction(this);
		view.getCustomMenu().setActionListeners(getMenuItemListeners());
	}
	
	
	
	public static Course[] setupAndGetCourses() {
		Module ctec2121 = new Module("CTEC2121", "Organisations, Project Management and Research", true);
		Module ctec2122 = new Module("CTEC2122", "Forensics and Security", false);
		Module ctec2602 = new Module("CTEC2602", "OO Software Design and Development", false);
		Module ctec2701 = new Module("CTEC2701", "Multi-tier Web Applications", true);
		Module ctec2901 = new Module("CTEC2901", "Data Structures and Algorithms", false);
		Module lawg2003 = new Module("LAWG2003", "Issues in Criminal Justice", false);
		Module ctec2903 = new Module("CTEC2903", "System Defence Strategies", true);
		Course compSci = new Course("Computer Science");
		compSci.addModule(ctec2121);
		compSci.addModule(ctec2602);
		compSci.addModule(ctec2701);
		compSci.addModule(ctec2901);
		Course softEng = new Course("Software Engineering");
		softEng.addModule(ctec2121);
		softEng.addModule(ctec2602);
		softEng.addModule(ctec2701);
		softEng.addModule(ctec2901);
		Course compSecu = new Course("Computer Security");
		compSecu.addModule(ctec2121);
		compSecu.addModule(ctec2122);
		compSecu.addModule(ctec2701);
		compSecu.addModule(ctec2903);
		Course forenComp = new Course("Forensic Computing");
		forenComp.addModule(ctec2121);
		forenComp.addModule(ctec2122);
		forenComp.addModule(ctec2701);
		forenComp.addModule(lawg2003);
		Course[] courses = new Course[4];
		courses[0] = compSci;
		courses[1] = softEng;
		courses[2] = compSecu;
		courses[3] = forenComp;
		return courses;
		}



	@Override
	public void handle(Event event) {
		
		if(event.getSource() == view.creatProfileTab.getButton()){
				//check no field is empty
				if(view.getDate()!=null && !view.getEmail().equals("")&&!view.getFirstName().equals("")&&!view.getSurName().equals("")&&!view.getpNumber().equals("")){
				model.setCourse(view.getCourse());
				model.setDate(view.getDate());
				model.setEmail(view.getEmail());	
				model.setpNumber(view.getpNumber());
				model.setStudentName(new Name(view.getFirstName(),view.getSurName()));
				setModules();
				view.goToNextTab();
				
				
				}
				else{
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Please, fill in all the fields");
					alert.showAndWait();
				}
		}
		
		else if(event.getSource() == view.inputMarksTab.getSubmitButton()){
			
			
			TextField[] moduleFieldE = view.inputMarksTab.getModuleFieldE();
			TextField[] moduleFieldC = view.inputMarksTab.getModuleFieldC();
			Collection<Module> x = view.getCourse().getModulesOnCourse();
			Module[] modules = x.toArray(new Module[x.size()]);
			Course course = view.getCourse();
			 for(int i=0; i<4; i++){
				 		modules[i].setCwkMark(Integer.parseInt(moduleFieldC[i].getText()));
				 		if(!modules[i].isCwkOnly())
				 		modules[i].setExamMark(Integer.parseInt(moduleFieldE[i].getText()));
				 		
				 		course.addModule(modules[i]);
			 }
			
			model.setCourse(course);
			view.overviewTab.getTextArea().setText(overViewResult());
			view.goToNextTab();
		}
		else {
			
			
			 directoryChooser();
		}
	}
	
	
	
	private void setModules(){
		Text[] moduleText = view.inputMarksTab.getModuleText();
		TextField[] moduleFieldE = view.inputMarksTab.getModuleFieldE();
		Collection<Module> x = view.getCourse().getModulesOnCourse();
		Module[] module = x.toArray(new Module[x.size()]);
		
		 for(int i=0; i<4; i++){
		        moduleText[i].setText(module[i].getModuleCode()+" "+module[i].getModuleName());
		        moduleFieldE[i].setVisible(!module[i].isCwkOnly());
		        }
		 
		 
		
	}
	
	
	private void repopulateModule(){
		
		TextField[] moduleFieldE = view.inputMarksTab.getModuleFieldE();
		TextField[] moduleFieldC = view.inputMarksTab.getModuleFieldC();
		Collection<Module> x = model.getCourse().getModulesOnCourse();
		Module[] modules = x.toArray(new Module[x.size()]);
		
		 for(int i=0; i<4; i++){
			 moduleFieldC[i].setText(modules[i].getCwkMark()+"");
			 if(!modules[i].isCwkOnly())
		      moduleFieldE[i].setText(modules[i].getExamMark()+"");
			 		
			 		
		 }
		
		
		
	}
	
	
	private String overViewResult(){
		String name="Name: "+model.getStudentName().getFamilyName()+" "+model.getStudentName().getFamilyName()+"\n";
		String pNo ="pNo: "+model.getpNumber()+"\n";
		String email = "Email "+model.getEmail()+"\n";
		String date = "Date: "+model.getDate().toString()+"\n";
		String courseName = "Course: "+model.getCourse().getCourseName();
		
		String top =name+pNo+email+date+courseName;
		
		String year = "\n2nd year average \n ========== \n";
		String credit = "Credits passed: "+model.getCourse().creditsPassed()+"\n";
		String aveMark = "Year average marks: "+model.getCourse().yearAverageMark();
		
		String bottom = year+credit+aveMark;
		
		return top+bottom;
	}
	
	
	public void directoryChooser() {
	    FileChooser chooser = new FileChooser();
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	    chooser.getExtensionFilters().add(extFilter);
	    chooser.setTitle("Choose location To Save");
	    File selectedFile = null;
	    selectedFile = chooser.showSaveDialog(null);
	    
	    if(selectedFile!= null){
	        
	    

	    File file = new File(selectedFile.getAbsolutePath());
	    PrintWriter outFile = null;
	    try {
	        outFile = new PrintWriter(file);
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    
	    outFile.println(view.overviewTab.getTextArea().getText());
	    
	    outFile.close();
	    }

	    
	}

	
	  private EventHandler<ActionEvent> getMenuItemListeners() {
	        return new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
					
				 String side = mItem.getText();
	                if ("about".equalsIgnoreCase(side)) {
	                	showAboutBox();
	                } else if ("Load Studnet Data".equalsIgnoreCase(side)) {
	                	try {
							loadFile();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                } else if ("Save Student Data".equalsIgnoreCase(side)) {
	                	try {
							saveFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                } else if ("exit".equalsIgnoreCase(side)) {
	                	Platform.exit();
	                }
				
				
				}

	        };
	    }
	
	  private void showAboutBox(){
		  Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About this Application");
			alert.setHeaderText(null);
			alert.setContentText("This is an application for student marks submission");
			alert.showAndWait();
		  
	  }
	  
	  private void saveFile() throws IOException{
		  
		  FileChooser chooser = new FileChooser();
		  FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BIN files (*.bin)", "*.bin");
		  chooser.getExtensionFilters().add(extFilter);
		  chooser.setTitle("Choose location To Save");
		  File selectedFile = null;
		  selectedFile = chooser.showSaveDialog(null);
		    
		  if(selectedFile!= null){
		
		    FileOutputStream out = null;
		    
			try {
				out = new FileOutputStream(selectedFile.getAbsolutePath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObjectOutputStream oout = null;
		    try {
				oout = new ObjectOutputStream(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    oout.writeObject(model);
		    
		    
		    }
		  
	  }
	  
	  
	  
 private void loadFile() throws IOException, ClassNotFoundException{
		  
		  FileChooser chooser = new FileChooser();
		  FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BIN files (*.bin)", "*.bin");
		  chooser.getExtensionFilters().add(extFilter);
		  chooser.setTitle("Choose location To Save");
		  File selectedFile = null;
		  selectedFile = chooser.showOpenDialog(null);
		    
		  if(selectedFile!= null){
		
		    FileInputStream out = null;
		    
			try {
				out = new FileInputStream(selectedFile.getAbsolutePath());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 ObjectInputStream oout = null;
		    try {
				oout = new  ObjectInputStream(out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    model= (StudentProfile) oout.readObject();
		    reloadInterface();
		    setModules();
		    view.overviewTab.getTextArea().setText(overViewResult());
		    repopulateModule();
		  }
		  
	  }
	
 
 
 
 private void reloadInterface(){
	 
	view.creatProfileTab.setCourseForCombo(model.getCourse());
	view.creatProfileTab.setDate(model.getDate());
	view.creatProfileTab.setEmail(model.getEmail());
	view.creatProfileTab.setpNumber(model.getpNumber());
	view.creatProfileTab.setFirstName(model.getStudentName().getFirstName());
	view.creatProfileTab.setSurName(model.getStudentName().getFamilyName());
	
	
	
 }
 
 
 
 
 
}
