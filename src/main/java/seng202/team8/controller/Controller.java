package seng202.team8.controller;


import java.io.File;
import java.io.IOException;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seng202.team8.view.StartGUI;

/**
 *  Contains controller methods and attributes used across multiple scenes of the GUI.
 */
public class Controller {

	/**
	 * Stage for the main window of the application.
	 */
	private Stage stage;

	/**
	 * Scene graph containing the elements currently being displayed and their layout.
	 */
	private Scene scene;

	/**
	 * Stage of a popup window for adding crime records.
	 */
	private Stage addRecordPopup;

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

	
	public Controller() {
		stage = StartGUI.primaryStage;
	}
	
	/**
	 * Starts the UI and loads the home page, sets the style of the stage to undecorated
	 * @param s stage passed by the main function to open
	 */
	public void start(Stage s) {
		try {
			stage = s;
			stage.initStyle(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/frame.fxml"));
			root = loader.load();
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
	 * Changes the displayed page of the UI to the home page
	 */
	public void home()  {
		try {
			root = FXMLLoader.load(getClass().getResource("/frame.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	/**
	 * Changes the displayed page of the UI to the map page
	 */
	public void map() {
		try {
			root = FXMLLoader.load(getClass().getResource("/map.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	/**
	 * Changes the displayed page of the UI to the table page
	 */
	public void table() {
		try {
			root = FXMLLoader.load(getClass().getResource("/table.fxml"));
		} catch (IOException e) {
			System.out.println("FXML failed to load");
			e.printStackTrace();
		}
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}
	
	/**
	 * Changes the displayed page of the UI to the graph page
	 */
	public void graph()  {
		try {
			root = FXMLLoader.load(getClass().getResource("/graph.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	/**
	 * Opens a popup window for adding a new record
	 * @param e
	 */
	public void openAddRecord(ActionEvent e) {
		try {
			root = FXMLLoader.load(getClass().getResource("/addRecord.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addRecordPopup = new Stage();
		addRecordPopup.initStyle(StageStyle.UNDECORATED);
		makeDraggable(root, addRecordPopup);
		addRecordPopup.initModality(Modality.APPLICATION_MODAL);
		Scene popup = new Scene(root, 400, 600);
		addRecordPopup.setScene(popup);
		addRecordPopup.show();
	}
	
	/**
	 * Closes the add record popup window
	 * @param e Node to collect the attached stage from
	 */
	public void closeAddRecord(ActionEvent e) {
		System.out.println("Closing");
		addRecordPopup = (Stage)((Node)e.getSource()).getScene().getWindow();
		addRecordPopup.close();
	}
	
	/**
	 * 
	 * Gets the path of a file selected by the file browser as a string
	 * @return file path of selected file as a string
	 */
	public String openFileLocation()  {
		FileChooser openRecords = new FileChooser();
		openRecords.setTitle("Import Crime Data");
		File toImport = openRecords.showOpenDialog(stage);
		System.out.println(toImport.getAbsolutePath());
		return toImport.getAbsolutePath();
	}
	
	/**
	 * Exits the window
	 */
	public void exit() {
		stage.close();
	}
	
	/**
	 * Minimises the window
	 */
	public void minimise() {
		stage.setIconified(true);
	}

	/**
	 * Makes a given node become a draggable anchor for the given stage
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
