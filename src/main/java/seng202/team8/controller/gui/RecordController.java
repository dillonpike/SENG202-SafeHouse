package seng202.team8.controller.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 * Parent class for GUI controllers that can display, filter, and manipulate records.
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
     * Text corresponding to startDatePicker used to display user feedback.
     */
    @FXML
    protected Text startDateText;

    /**
     * Text corresponding to endDatePicker used to display user feedback.
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
     * Text field that the user uses to write an end beat for filtering records.
     */
    @FXML
    protected TextField endBeatField;

    /**
     * Text corresponding to startBeatField used to display user feedback.
     */
    @FXML
    protected Text startBeatText;

    /**
     * Text corresponding to endBeatField used to display user feedback.
     */
    @FXML
    protected Text endBeatText;

    /**
     * Text field that the user uses to write a start ward for filtering records.
     */
    @FXML
    protected TextField startWardField;

    /**
     * Text field that the user uses to write an end ward for filtering records.
     */
    @FXML
    protected TextField endWardField;

    /**
     * Text corresponding to startWardText used to display user feedback.
     */
    @FXML
    protected Text startWardText;

    /**
     * Text corresponding to endWardText used to display user feedback.
     */
    @FXML
    protected Text endWardText;

    /**
     * ComboBox that allows the user to select options to filter by arrest.
     */
    @FXML
    protected ComboBox<String> arrestComboBox;

    /**
     * ComboBox that allows the user to select options to filter by domestic.
     */
    @FXML
    protected ComboBox<String> domesticComboBox;

    /**
     * CheckBox that determines whether filtering is done in real-time (as the user types/selects).
     */
    @FXML
    protected CheckBox realTimeCheckBox;

    /**
     * Initializes the attributes of the GUI screen.
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

    /**
     * Resets the list of records stored in the controller to the full dataset and then applies filters based on
     * the user's input in the GUI's attributes.
     */
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
     * Opens a popup window for adding a new record.
     * @return controller of the popup window
     */
    public AddRecordController openAddRecord() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addRecord.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            return null;
        }
        addRecordPopup = new Stage();
        addRecordPopup.initStyle(StageStyle.UNDECORATED);
        makeDraggable(root, addRecordPopup);
        addRecordPopup.initModality(Modality.APPLICATION_MODAL);
        Scene popup = new Scene(root, 400, 700);
        addRecordPopup.setScene(popup);
        addRecordPopup.show();
        return loader.getController();
    }

    /**
     * Opens the file browser for the user to choose a file, then returns the file path as a string.
     * Returns null if the user exited without selecting a file, or the file could not be accessed.
     * @return file path of selected file as a string
     */
    public String openFileLocation()  {
        FileChooser openRecords = new FileChooser();
        openRecords.setTitle("Import Crime Data");
        File toImport = openRecords.showOpenDialog(stage);
        String path = null;
        if (toImport != null) {
        	path = toImport.getAbsolutePath();
        }
        return path;
    }

    /**
     * Opens the file browser for the user to choose a location and name for the export.
     * Returns null if the user exited without selecting a file, or the file could not be accessed.
     * @return file path chosen by the user
     */
    public String openDirectoryLocation() {
        DirectoryChooser openFolder = new DirectoryChooser();
        openFolder.setTitle("Select Folder for Export");
        File selectedFolder = openFolder.showDialog(stage);
        String path = null;
        if (selectedFolder != null) {
        	path = selectedFolder.getAbsolutePath();
        }
        return path;
    }
}
