package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class InputMarksTab extends Tab {

	Text ModuleText;
	Text courseWorkText;
	Text examMarkText;
	
	Text[] moduleText = new Text[5];
	
	TextField[] moduleFieldC = new TextField[5];
	
	TextField[] moduleFieldE = new TextField[5];
	
	
	Button submitButton;
	Button clearButton;
	
	int FIELDWIDTH= 60;
	
	InputMarksTab(){
		setText("Input Marks");
        GridPane hbox = new GridPane();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setHgap(50);
        hbox.setVgap(10);
        hbox.setAlignment(Pos.CENTER);
        
        for(int i=0; i<4; i++){
        moduleText[i] = new Text("Profile not created");
        moduleFieldC[i] = new TextField();
        moduleFieldE[i] = new TextField();
        moduleFieldC[i].setMaxWidth(FIELDWIDTH);
        moduleFieldE[i].setMaxWidth(FIELDWIDTH);
        
        }
        
    	ModuleText = new Text("Module");
    	courseWorkText = new Text("Cwrk");
    	examMarkText = new Text("Exam");
    	
    	GridPane.setHalignment(ModuleText, HPos.CENTER);
    	GridPane.setHalignment(courseWorkText, HPos.CENTER);
    	GridPane.setHalignment(examMarkText, HPos.CENTER);
    	
    	submitButton = new Button("Submit"); 
    	clearButton = new Button("Clear");

    	clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	 for(int i=0; i<4; i++){
            	  moduleFieldC[i].clear();
                  moduleFieldE[i].clear();
            	        
            	        }
            }
        });
    	
    	
    	
    	
    	hbox.add(ModuleText, 0, 0);
        hbox.add(courseWorkText, 1, 0);
        hbox.add(examMarkText, 2, 0);
        
        
       
        for(int row=1; row<5; row++){
        hbox.add(moduleText[row-1], 0, row);
	    hbox.add(moduleFieldC[row-1], 1, row);
	    hbox.add(moduleFieldE[row-1], 2, row);
        }
       
        hbox.add(clearButton, 1, 5);
        hbox.add(submitButton, 2, 5);


        
        setContent(hbox);
		
		
	}
	
	
	public Text[] getModuleText(){
		
		return moduleText;
	}
	
	public TextField[] getModuleFieldC(){
		
		return moduleFieldC;
	}
	
	public TextField[] getModuleFieldE(){
		
		return moduleFieldE;
	}
	
	
	public Button getSubmitButton(){
		
		
		return submitButton;
	}
	
	
	
	
	
	
	
}
