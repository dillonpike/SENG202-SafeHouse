package seng202.team8.controller;


import java.io.IOException;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seng202.team8.view.StartGUI;

import java.net.*;
import java.util.ResourceBundle;

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
	
	public void home(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/frame.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	public void map(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/map.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	public void table(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/table.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	public void graph(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/graph.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable(root, stage);
		stage.show();
	}

	public void openAddRecord(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/addRecord.fxml"));
		addRecordPopup = new Stage();
		addRecordPopup.initStyle(StageStyle.UNDECORATED);
		makeDraggable(root, addRecordPopup);
		addRecordPopup.initModality(Modality.APPLICATION_MODAL);
		Scene popup = new Scene(root, 400, 600);
		addRecordPopup.setScene(popup);
		addRecordPopup.show();
	}
	
	public void closeAddRecord(ActionEvent e) {
		System.out.println("Closing");
		addRecordPopup = (Stage)((Node)e.getSource()).getScene().getWindow();
		addRecordPopup.close();
	}
	
	public void exit(ActionEvent e) {
		stage.close();
	}
	
	public void minimise(ActionEvent e) {
		stage.setIconified(true);
	}

	
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
}
