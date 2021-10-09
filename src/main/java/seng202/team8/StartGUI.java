package seng202.team8;
	
import javafx.application.Application;
import javafx.stage.Stage;
import seng202.team8.controller.gui.BasicController;

/**
 * Initializes and loads the main window of the application.
 */
public class StartGUI extends Application {

	/**
	 * Stage of the application's main window.
	 */
	public static Stage primaryStage;

	/**
	 * Controller of the main window.
	 */
	public static BasicController controller;

	/**
	 * Starts the application by instantiating the controller for the initial screen and starting it with primaryStage.
	 * @param primaryStage stage of the application's main window.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			StartGUI.primaryStage = primaryStage;
			controller = new BasicController();
			controller.start(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launches a standalone application (From JavaFX's Application Javadoc).
	 * @param args arguments given when starting the application
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
