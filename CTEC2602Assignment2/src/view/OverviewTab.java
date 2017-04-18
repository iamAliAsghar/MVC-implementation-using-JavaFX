package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class OverviewTab extends Tab {
	
	
	TextArea textArea;
	Button saveOverviewButton;


	OverviewTab(){
		setText("Overview Results");
		
		  GridPane hbox = new GridPane();
	      hbox.setPadding(new Insets(10,10,10,10));
	      hbox.setHgap(50);
	      hbox.setVgap(10);
	      hbox.setAlignment(Pos.CENTER);
		
	      textArea = new TextArea();
	      saveOverviewButton = new Button("Save Overview");
	      
	      hbox.add(textArea, 0, 0);
	      GridPane.setHalignment(saveOverviewButton, HPos.CENTER);
	      hbox.add(saveOverviewButton, 0, 1);
	      setContent(hbox);
		
	}
	
	public Button getSaveOverviewButton(){
		return saveOverviewButton;
	}
	public TextArea getTextArea(){
		
		return textArea;
	}
}
