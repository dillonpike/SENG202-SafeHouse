package seng202.team8.view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import seng202.team8.controller.GUIController;
import seng202.team8.controller.DataManager;


public class StartGUI extends Application {
	public static Stage primaryStage;
	public static GUIController control;
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
