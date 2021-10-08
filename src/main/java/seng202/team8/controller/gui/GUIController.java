package seng202.team8.controller.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.controller.DataManager;
import seng202.team8.StartGUI;

/**
 * Contains controller methods and attributes used across all scenes of the GUI.
 */
public class GUIController {

	/**
	 * Stage for the main window of the application.
	 */
	protected Stage stage;

	/**
	 * Scene graph containing the elements currently being displayed and their layout.
	 */
	private Scene scene;

	/**
	 * Stage of a popup window for adding crime records.
	 */
	protected Stage addRecordPopup;

	/**
	 * Root node of scene graph.
	 */
	private Parent root;

	/**
	 * Window's x-coordinate offset from centre.
	 */
	private double xOffset;

	/**
	 * Window's y-coordinate offset from centre.
	 */
	private double yOffset;
	
	public GUIController() {
		stage = StartGUI.primaryStage;
	}
	
	/**
	 * Starts the UI and loads the home page, sets the style of the stage to undecorated.
	 * @param stage stage passed by the main function to open
	 */
	public void start(Stage stage) {
		try {
			this.stage = stage;
			stage.initStyle(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/frame.fxml"));
			root = loader.load();
			makeDraggable(root, stage);
			scene = new Scene(root,1280,720);
			stage.setScene(scene);
			makeDraggable(root, stage);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes the displayed page of the UI to the home page.
	 */
	public void home()  {
		try {
			root = FXMLLoader.load(getClass().getResource("/frame.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.getScene().setRoot(root);
		makeDraggable(root, stage);
	}

	/**
	 * Changes the displayed page of the UI to the map page.
	 */
	public void map() {
		try {
			root = FXMLLoader.load(getClass().getResource("/map.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.getScene().setRoot(root);
		makeDraggable(root, stage);
	}

	/**
	 * Changes the displayed page of the UI to the table page.
	 */
	public void table() {
		try {
			root = FXMLLoader.load(getClass().getResource("/table.fxml"));
		} catch (IOException e) {
			System.out.println("FXML failed to load");
			e.printStackTrace();
		}
		stage.getScene().setRoot(root);
		makeDraggable(root, stage);
	}
	
	/**
	 * Changes the displayed page of the UI to the graph page.
	 */
	public void graph()  {
		try {
			root = FXMLLoader.load(getClass().getResource("/graph.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.getScene().setRoot(root);
		makeDraggable(root, stage);
	}
	
	/**
	 * Exits the window.
	 */
	public void exit() {
		stage.close();
	}
	
	/**
	 * Minimises the window.
	 */
	public void minimise() {
		stage.setIconified(true);
	}

	/**
	 * Makes a given node become a draggable anchor for the given stage.
	 * @param n node to be made draggable
	 * @param s stage to be made draggable
	 */
	public void makeDraggable(Node n, Stage s){

        n.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });

        n.setOnMouseDragged(mouseEvent -> {
            s.setX(mouseEvent.getScreenX() - xOffset);
            s.setY(mouseEvent.getScreenY() - yOffset);
        });
    }

	/**
	 * Returns the controller's crime record manager.
	 * @return crime record manager
	 */
	public CrimeRecordManager getManager() {
		return DataManager.getCurrentDataset();
	}
}
