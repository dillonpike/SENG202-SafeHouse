package seng202.team8.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class DataManager {

    /**
     * The general controller
     */
    private Controller control = new Controller();


    /**
     * An ArrayList of all the different Managers that the application uses
     * Since each CrimeRecordManager acts as a dataset
     * This allows us to store multiple datasets
     */
    private static ArrayList<CrimeRecordManager> datasets = new ArrayList<>();

    /**
     * The current CrimeRecordManager object in use
     * Effectively which dataset we are using
     */
    private static CrimeRecordManager currentDataset = new CrimeRecordManager();

    //Stuff for each scene going up/going down

    public void startGUI(Stage primaryStage) {
        control.start(primaryStage);
    }

    public static CrimeRecordManager getCurrentDataset() {
        return currentDataset;
    }

    public static ArrayList<CrimeRecordManager> getDatasets() { return datasets;}

    public static void setCurrentDataset(CrimeRecordManager dataset) {
        currentDataset = dataset;
    }


}
