package GUI_Calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for the main code that starts the interface
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI_Calculator.fxml")); 
        primaryStage.setTitle("Math Expressions");  // the title of the project on the interface
        primaryStage.setScene(new Scene(root, 400, 430));  // the measurements of the interface
        primaryStage.show(); // it shows the interface
    }
    public static void main(String[] args) { launch(args); }
}
