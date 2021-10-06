package seng202.team8.controller.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.*;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class RecordController extends GUIController {

    /**
     * Crime records displayed in recordTable.
     */
    protected ArrayList<CrimeRecord> records;

    /**
     * Date picker that the user uses to select a start date for filtering records.
     */
    @FXML
    protected DatePicker startDatePicker;

    /**
     * Date picker that the user uses to select an end date for filtering records.
     */
    @FXML
    protected DatePicker endDatePicker;

    /**
     * Text corresponding to startDatePicker.
     */
    @FXML
    protected Text startDateText;

    /**
     * Text corresponding to endDatePicker.
     */
    @FXML
    protected Text endDateText;

    /**
     * Text field that the user types a primary description into for filtering records.
     */
    @FXML
    protected TextField primaryDescField;

    /**
     * Text field that the user types a location description into for filtering records.
     */
    @FXML
    protected TextField locationField;

    /**
     * Text field that the user uses to write a start beat for filtering records.
     */
    @FXML
    protected TextField startBeatField;

    /**
     * Text field that the user uses to write a end beat for filtering records.
     */
    @FXML
    protected TextField endBeatField;

    /**
     *  Text field that the user uses to write a start beat for filtering records.
     */
    @FXML
    protected Text startBeatText;

    /**
     *
     */
    @FXML
    protected Text endBeatText;

    /**
     *
     */
    @FXML
    protected TextField startWardField;

    /**
     *
     */
    @FXML
    protected TextField endWardField;

    /**
     *
     */
    @FXML
    protected Text startWardText;

    /**
     *
     */
    @FXML
    protected Text endWardText;

    @FXML
    protected ComboBox<String> arrestComboBox;

    @FXML
    protected ComboBox<String> domesticComboBox;

    @FXML
    protected CheckBox realTimeCheckBox;

    /**
     *
     */
    protected void initializeAttributes() {
        // Filter text fields
        startDateText.setText("");
        endDateText.setText("");
        startBeatText.setText("");
        endBeatText.setText("");
        startWardText.setText("");
        endWardText.setText("");

        // Combo box initialization
        arrestComboBox.getItems().addAll("Don't filter", "Yes", "No");
        domesticComboBox.getItems().addAll("Don't filter", "Yes", "No");
        arrestComboBox.getSelectionModel().select("Don't filter");
        domesticComboBox.getSelectionModel().select("Don't filter");

        records = getManager().getLocalCopy();
    }

    protected void filterRecords() {
        records = getManager().getLocalCopy(); // Reset records to complete dataset
        records = GUIFiltering.filterDates(records, startDatePicker, endDatePicker, startDateText, endDateText);
        records = SearchCrimeData.filterByPrimaryDesc(records, primaryDescField.getText());
        records = SearchCrimeData.filterByCrimeLocation(records, locationField.getText());
        records = GUIFiltering.filterBeats(records, startBeatField, endBeatField, startBeatText, endBeatText);
        records = GUIFiltering.filterWards(records, startWardField, endWardField, startWardText, endWardText);
        if (!arrestComboBox.getValue().equals("Don't filter")) {
            records = SearchCrimeData.filterByArrest(records, arrestComboBox.getValue().equals("Yes"));
        }
        if (!domesticComboBox.getValue().equals("Don't filter")) {
            records = SearchCrimeData.filterByDomesticViolence(records, domesticComboBox.getValue().equals("Yes"));
        }
    }

    /**
     * Opens a popup window for adding a new record
     */
    public AddRecordController openAddRecord() {
        AddRecordController newController = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addRecord.fxml"));
            Parent root;
            root = loader.load();
            addRecordPopup = new Stage();
            addRecordPopup.initStyle(StageStyle.UNDECORATED);
            makeDraggable(root, addRecordPopup);
            addRecordPopup.initModality(Modality.APPLICATION_MODAL);
            Scene popup = new Scene(root, 400, 700);
            addRecordPopup.setScene(popup);
            addRecordPopup.show();
            newController = loader.getController();
            return newController;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return newController;
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
        String path = null;
        try {
            path = toImport.getAbsolutePath();
        } catch (NullPointerException e) {

        }
        return path;
    }

    public String openDirectoryLocation() {
        DirectoryChooser openFolder = new DirectoryChooser();
        openFolder.setTitle("Select folder for export");
        File selectedFolder = openFolder.showDialog(stage);
        String path = null;
        try {
            path = selectedFolder.getAbsolutePath();
        } catch (NullPointerException e) {

        }
        return path;
    }
}
