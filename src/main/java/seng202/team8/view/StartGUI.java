package seng202.team8.view;
	
import javafx.application.Application;
import javafx.fxml.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.net.*;


public class StartGUI extends Application {
	public static Stage primaryStage;
	public static Controller control;
	public static javafx.scene.layout.BorderPane root; 
	
	@Override
	public void start(Stage s) {
		try {
			primaryStage = s;
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//Parent root = FXMLLoader.load(getClass().getResource("frame.fxml"));
			//Scene scene = new Scene(root,1280,720);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setScene(scene);
			//primaryStage.show();
			control = new Controller();
			control.start(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
}
