package seng202.team8.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController extends Controller implements Initializable {

    @FXML
    private TableView<CrimeRecord> recordTable;

    @FXML
    private TableColumn<CrimeRecord, String> clmNum;

    @FXML
    private TableColumn<CrimeRecord, int[]> clmDate;

    @FXML
    private TableColumn<CrimeRecord, String> clmBlock;

    @FXML
    private TableColumn<CrimeRecord, String> clmIUCR;

    @FXML
    private TableColumn<CrimeRecord, String> clmPrimaryDesc;

    @FXML
    private TableColumn<CrimeRecord, String> clmSecondaryDesc;

    @FXML
    private TableColumn<CrimeRecord, String> clmLocation;

    @FXML
    private TableColumn<CrimeRecord, Integer> clmArrest;

    @FXML
    private TableColumn<CrimeRecord, Integer> clmDomestic;

    @FXML
    private TableColumn<CrimeRecord, Integer> clmBeat;

    @FXML
    private TableColumn<CrimeRecord, String> clmWard;

    @FXML
    private TableColumn<CrimeRecord, String> clmFBI;

    @FXML
    private TableColumn<CrimeRecord, Double> clmLat;

    @FXML
    private TableColumn<CrimeRecord, Double> clmLon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNum.setCellValueFactory(new PropertyValueFactory<>("caseNum"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmBlock.setCellValueFactory(new PropertyValueFactory<>("block"));
        clmIUCR.setCellValueFactory(new PropertyValueFactory<>("iucr"));
        clmPrimaryDesc.setCellValueFactory(new PropertyValueFactory<>("primary"));
        clmSecondaryDesc.setCellValueFactory(new PropertyValueFactory<>("secondary"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("locDescription"));
        clmArrest.setCellValueFactory(new PropertyValueFactory<>("wasArrest"));
        clmDomestic.setCellValueFactory(new PropertyValueFactory<>("wasDomestic"));
        clmBeat.setCellValueFactory(new PropertyValueFactory<>("beat"));
        clmWard.setCellValueFactory(new PropertyValueFactory<>("ward"));
        clmFBI.setCellValueFactory(new PropertyValueFactory<>("fbiCD"));
        clmLat.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        clmLon.setCellValueFactory(new PropertyValueFactory<>("longitude"));

        CrimeRecordManager manager = new CrimeRecordManager();
        try {
            manager.importFile("src/test/java/seng202/team8/controller/testdata.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recordTable.setItems(manager.getObservable());
    }
}
