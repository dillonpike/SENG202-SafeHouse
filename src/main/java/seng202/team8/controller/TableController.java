package seng202.team8.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Contains attributes and methods for the table page in the application's GUI.
 */
public class TableController extends Controller implements Initializable {

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
    private TableColumn<CrimeRecord, int[]> clmDate;

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
    
    



    /**
     * Links recordTable columns to attributes of CrimeRecord.
     */
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

        startDateText.setText("");
        endDateText.setText("");
        startBeatText.setText("");
        endBeatText.setText("");
        startWardText.setText("");
        endWardText.setText("");

		try {
			getManager().importFile("src/test/java/seng202/team8/controller/5kRecords.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        records = getManager().getLocalCopy();
        updateTable();

        arrestComboBox.getItems().addAll("Don't filter", "Yes", "No");
        domesticComboBox.getItems().addAll("Don't filter", "Yes", "No");
        arrestComboBox.getSelectionModel().select("Don't filter");
        domesticComboBox.getSelectionModel().select("Don't filter");
    }

    /**
     * Updates the crimes given in recordTable with the module's records ArrayList.
     */
    private void updateTable() {
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
            boolean arrestMade = arrestComboBox.getValue() == "Yes" ? true : false;
            records = SearchCrimeData.filterByArrest(records, arrestMade);
        }
        System.out.println(domesticComboBox.getValue());
        if (!domesticComboBox.getValue().equals("Don't filter")) {
            boolean wasDomestic = domesticComboBox.getValue() == "Yes" ? true : false;
            records = SearchCrimeData.filterByDomesticViolence(records, wasDomestic);
        }
        updateTable();
    }

    public void deleteRecord() {
    	int index = recordTable.getSelectionModel().getSelectedIndex();
    	getManager().removeRecord(recordTable.getSelectionModel().getSelectedItem());
    	updateTable();
    	recordTable.getSelectionModel().select(index);
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

    /**
     * Returns true if string can be converted to an integer, otherwise false.
     * @param string string that is checked
     * @return true if string can be converted to an integer, otherwise false
     */
    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
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

    /**
     * Might be used to combine filter functions
     */
    private void filterTextFieldRange(TextField startField, TextField endField, Text startText, Text endText) {
        String startString = startField.getText();
        String endString = endField.getText();
        try {
            records = SearchCrimeData.filterByCrimeLocationBeat(records,
                    Integer.parseInt(startString), Integer.parseInt(endString));
            startText.setText("");
            endText.setText("");
        } catch (NumberFormatException e) { // Display error messages for appropriate beat fields
            if (!startString.equals("") && !isInteger(startString)) {
                startText.setText("Invalid Date");
            } else {
                startText.setText("");
            }
            if (!endString.equals("") && !isInteger(endString)) {
                endText.setText("Invalid Date");
            } else {
                endText.setText("");
            }
        }
    }
    
    
    
    public TableView<CrimeRecord> getTable() {
    	return recordTable;
    }

}
