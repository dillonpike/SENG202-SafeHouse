package application;

import java.io.IOException;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.*;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private double xOffset;
	private double yOffset;
	
	public Controller() {
		stage = Main.primaryStage;
	}
	
	public void start(Stage s) {
		try {
			stage = s;
			stage.initStyle(StageStyle.UNDECORATED);
			root = FXMLLoader.load(getClass().getResource("frame.fxml"));
			scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			makeDraggable();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void home(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("frame.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable();
		stage.show();
	}

	public void map(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("map.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable();
		stage.show();
	}

	public void table(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("table.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable();
		stage.show();
	}

	public void graph(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("graph.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root, 1280,720);
		stage.setScene(scene);
		makeDraggable();
		stage.show();
	}
	
	public void exit(ActionEvent e) {
		stage.close();
	}
	
	public void minimise(ActionEvent e) {
		stage.setIconified(true);
	}
	
	public void makeDraggable(){

        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });
    }
}
