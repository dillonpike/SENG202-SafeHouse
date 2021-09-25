package seng202.team8.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Contains attributes and methods for the table page in the application's GUI.
 */
public class TableController extends GUIController implements Initializable {

    /**
     * Crime records displayed in recordTable.
     */
    private ArrayList<CrimeRecord> records;

    /**
     * Table that displays crime records.
     */
    @FXML
    private TableView<CrimeRecord> recordTable;

    /**
     * Column for a crime record's case number.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmNum;

    /**
     * Column for a crime record's date.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmDate;

    /**
     * Column for a crime record's block.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmBlock;

    /**
     * Column for a crime record's IUCR (Illinois Uniform Crime Reporting code).
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmIUCR;

    /**
     * Column for a crime record's primary description.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmPrimaryDesc;

    /**
     * Column for a crime record's IUCR (Illinois Uniform Crime Reporting code).
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmSecondaryDesc;

    /**
     * Column for a crime record's location.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmLocation;

    /**
     * Column for a crime record's arrest status.
     */
    @FXML
    private TableColumn<CrimeRecord, Integer> clmArrest;

    /**
     * Column for a crime record's domestic status.
     */
    @FXML
    private TableColumn<CrimeRecord, Integer> clmDomestic;

    /**
     * Column for a crime record's beat (police district).
     */
    @FXML
    private TableColumn<CrimeRecord, Integer> clmBeat;

    /**
     * Column for a crime record's ward (election precinct).
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmWard;

    /**
     * Column for a crime record's ward (election precinct).
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmFBI;

    /**
     * Column for a crime record's latitude.
     */
    @FXML
    private TableColumn<CrimeRecord, Double> clmLat;

    /**
     * Column for a crime record's longitude.
     */
    @FXML
    private TableColumn<CrimeRecord, Double> clmLon;

    /**
     * Date picker that the user uses to select a start date for filtering records.
     */
    @FXML
    private DatePicker startDatePicker;

    /**
     * Date picker that the user uses to select an end date for filtering records.
     */
    @FXML
    private DatePicker endDatePicker;

    /**
     * Text corresponding to startDatePicker.
     */
    @FXML
    private Text startDateText;

    /**
     * Text corresponding to endDatePicker.
     */
    @FXML
    private Text endDateText;

    /**
     * Text field that the user types a primary description into for filtering records.
     */
    @FXML
    private TextField primaryDescField;

    /**
     * Text field that the user types a location description into for filtering records.
     */
    @FXML
    private TextField locationField;

    /**
     *
     */
    @FXML
    private TextField startBeatField;

    /**
     *
     */
    @FXML
    private TextField endBeatField;

    /**
     *
     */
    @FXML
    private Text startBeatText;

    /**
     *
     */
    @FXML
    private Text endBeatText;


    /**
     *
     */
    @FXML
    private TextField startWardField;

    /**
     *
     */
    @FXML
    private TextField endWardField;

    /**
     *
     */
    @FXML
    private Text startWardText;

    /**
     *
     */
    @FXML
    private Text endWardText;

    @FXML
    private ComboBox<String> arrestComboBox;

    @FXML
    private ComboBox<String> domesticComboBox;
    
    @FXML
    private Label lblDate;

    @FXML
    private Label lblCaseNum;

    @FXML
    private Label lblBlock;

    @FXML
    private Label lblUCR;

    @FXML
    private Label lblPrimary;

    @FXML
    private Label lblSecondary;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblArrest;

    @FXML
    private Label lblDomestic;

    @FXML
    private Label lblBeat;

    @FXML
    private Label lblWard;

    @FXML
    private Label lblFbi;

    @FXML
    private Label lblLat;

    @FXML
    private Label lblLon;
    
    @FXML
    private Pane dragBar;



    /**
     * Links recordTable columns to attributes of CrimeRecord.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNum.setCellValueFactory(new PropertyValueFactory<>("caseNum"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmPrimaryDesc.setCellValueFactory(new PropertyValueFactory<>("primary"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("locDescription"));
        clmArrest.setCellValueFactory(new PropertyValueFactory<>("wasArrest"));
        clmDomestic.setCellValueFactory(new PropertyValueFactory<>("wasDomestic"));

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
        updateTable();
    }

    /**
     * Calls the method to locate a file through windows explorer then passes it to the CrimeRecordManager for importing. Updates the table after.
     */
    public void importFile() {
        String filename = openFileLocation();
        try {
            getManager().importFile(filename);
            //Update the screen
            updateTable();
        } catch (FileNotFoundException e) {
            // The file wasn't found!
            e.printStackTrace();
        }
    }

    /**
     * Updates the crimes given in recordTable with the module's records ArrayList.
     */
    public void updateTable() {
    	recordTable.setItems(FXCollections.observableArrayList(records));
    }

    /**
     * Applies filters to the list of records displayed in recordTable.
     */
    public void filterTable() {
        records = getManager().getLocalCopy(); // Reset records to complete dataset
        filterDates();
        records = SearchCrimeData.filterByPrimaryDesc(records, primaryDescField.getText());
        records = SearchCrimeData.filterByCrimeLocation(records, locationField.getText());
        filterBeats();
        filterWards();
        if (!arrestComboBox.getValue().equals("Don't filter")) {
            records = SearchCrimeData.filterByArrest(records, arrestComboBox.getValue().equals("Yes"));
        }
        if (!domesticComboBox.getValue().equals("Don't filter")) {
            records = SearchCrimeData.filterByDomesticViolence(records, domesticComboBox.getValue().equals("Yes"));
        }
        updateTable();
    }

    /**
     * Removes a selected record from the local copy of records and updates the table, maintains selected position on the screen.
     */
    public void deleteRecord() {
    	int index = recordTable.getSelectionModel().getSelectedIndex();
    	getManager().removeRecord(recordTable.getSelectionModel().getSelectedItem());
        filterTable();
    	updateTable();
    	recordTable.getSelectionModel().select(index);
    }
    
    /**
     * Calls the method to open the window for adding a new record, then sets the title to EDIT RECORD and passes through the current crime to
     * the newly created window. The case number field is locked to prevent editing and a flag is set to let the window know it is in edit mode.
     */
    public void editRecord() {
    	if (recordTable.getSelectionModel().getSelectedItem() != null) {
    		AddRecordController editController = openAddRecord();
    		editController.lblTitle.setText("EDIT RECORD");
    		editController.fldCaseNum.setEditable(false);
    		editController.editing = true;
    		editController.toEdit = recordTable.getSelectionModel().getSelectedItem();
    		editController.currentTable = this;
    		editController.fillFields();
    	}
    }
    
    /**
     * Calls the method to open the window for adding a new record, then initialises the table controller in the window as this current object.
     */
    public void addRecord() {
    	AddRecordController addController = openAddRecord();
    	addController.currentTable = this;
    	
    }

    /**
     * Filters the module's records ArrayList by removing crime record's that didn't occur between the dates provided
     * in the GUI's date pickers. Displays an error message for the date pickers if they contain invalid dates.
     */
    private void filterDates() {
        String startDate = startDatePicker.getEditor().getText();
        String endDate = endDatePicker.getEditor().getText();
        try {
            records = SearchCrimeData.filterByDate(records, startDate, endDate);
            startDateText.setText("");
            endDateText.setText("");
        } catch (ParseException e) { // Display error messages for appropriate date pickers
            if (!startDate.equals("") && !SearchCrimeData.isValidDate(startDate)) {
                startDateText.setText("Invalid Date");
            } else {
                startDateText.setText("");
            }
            if (!endDate.equals("") && !SearchCrimeData.isValidDate(endDate)) {
                endDateText.setText("Invalid Date");
            } else {
                endDateText.setText("");
            }
        }
    }

    private void filterBeats() {
        String startBeatString = startBeatField.getText();
        String endBeatString = endBeatField.getText();
        try {
            records = SearchCrimeData.filterByCrimeLocationBeat(records,
                    Integer.parseInt(startBeatString), Integer.parseInt(endBeatString));
            startBeatText.setText("");
            endBeatText.setText("");
        } catch (NumberFormatException e) { // Display error messages for appropriate beat fields
            if (!startBeatString.equals("") && !isInteger(startBeatString)) {
                startBeatText.setText("Invalid Beat");
            } else {
                startBeatText.setText("");
            }
            if (!endBeatString.equals("") && !isInteger(endBeatString)) {
                endBeatText.setText("Invalid Beat");
            } else {
                endBeatText.setText("");
            }
        }
    }

    private void filterWards() {
        String startWardString = startWardField.getText();
        String endWardString = endWardField.getText();
        try {
            records = SearchCrimeData.filterByCrimeWard(records,
                    Integer.parseInt(startWardString), Integer.parseInt(endWardString));
            startWardText.setText("");
            endWardText.setText("");
        } catch (NumberFormatException e) { // Display error messages for appropriate ward fields
            if (!startWardString.equals("") && !isInteger(startWardString)) {
                startWardText.setText("Invalid Ward");
            } else {
                startWardText.setText("");
            }
            if (!endWardString.equals("") && !isInteger(endWardString)) {
                endWardText.setText("Invalid Ward");
            } else {
                endWardText.setText("");
            }
        }
    }
    
    public void updateExtendedInfo() {
        try {
            CrimeRecord selectedRecord = recordTable.getSelectionModel().getSelectedItem();
            lblCaseNum.setText(selectedRecord.getCaseNum());
            lblDate.setText(selectedRecord.getDate().toString() + " " + selectedRecord.getTime().toString());
            lblBlock.setText(selectedRecord.getBlock());
            lblUCR.setText(selectedRecord.getIucr());
            lblPrimary.setText(selectedRecord.getPrimary());
            lblSecondary.setText(selectedRecord.getSecondary());
            lblLocation.setText(selectedRecord.getLocDescription());
            lblArrest.setText(String.valueOf(selectedRecord.getWasArrest()));
            lblDomestic.setText(String.valueOf(selectedRecord.getWasDomestic()));
            lblBeat.setText(String.valueOf(selectedRecord.getBeat()));
            lblWard.setText(String.valueOf(selectedRecord.getWard()));
            lblFbi.setText(selectedRecord.getFbiCD());
            lblLat.setText(String.valueOf(selectedRecord.getLatitude()));
            lblLon.setText(String.valueOf(selectedRecord.getLongitude()));
        } catch (NullPointerException e) {
            // Do nothing if the table was clicked in an empty spot
        }
    }
    
    
    public TableView<CrimeRecord> getTable() {
    	return recordTable;
    }

}
