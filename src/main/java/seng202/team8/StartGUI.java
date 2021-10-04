package seng202.team8;
	
import javafx.application.Application;
import javafx.stage.Stage;
import seng202.team8.controller.gui.GUIController;
import seng202.team8.controller.DataManager;


public class StartGUI extends Application {
	public static Stage primaryStage;
	public static GUIController control;
	public static javafx.scene.layout.BorderPane root; 
	
	
	@Override
	public void start(Stage s) {
		try {
			primaryStage = s;
			control = new GUIController();
			DataManager.addToDatasets(DataManager.getCurrentDataset());
			control.start(primaryStage);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
}
