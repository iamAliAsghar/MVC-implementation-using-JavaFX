package application;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StudentProfile;
import view.View;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
    //	setTitle("Studnet Mark Submission Tool");
    	
    	
    	View view = new View();
    	StudentProfile model = new StudentProfile();
    	Controller controller = new Controller(view,model);
    	
    	
    	
        Scene scene = new Scene(view.getRoot(), 800, 400);
        view.bindBorder(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}