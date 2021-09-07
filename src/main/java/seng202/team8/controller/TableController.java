package seng202.team8.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Contains attributes and methods for the table page in the application's GUI.
 */
public class TableController extends Controller implements Initializable {

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
     * Date picker that the user uses to select a start date.
     */
    @FXML
    private DatePicker startDatePicker;

    /**
     * Date picker that the user uses to select an end date.
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
        System.out.println(getManager());
		try {
			getManager().importFile("src/test/java/seng202/team8/controller/5kRecords.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        populateTable(getManager().getLocalCopy());
    }

    /**
     *
     * @return
     */
    public TableView<CrimeRecord> getTable() {
    	return recordTable;
    }

    /**
     * Displays the crimes given in recordTable.
     * @param crimes ArrayList of crime records
     */
    public void populateTable(ArrayList<CrimeRecord> crimes) {
    	recordTable.setItems(FXCollections.observableArrayList(crimes));
    }

    /**
     * Updates recordTable by only showing crimes that occurred within the dates in startDatePicker and endDatePicker.
     * Displays an error message for the date pickers if they contain invalid dates.
     */
    public void filterDates() {
        String startDate = startDatePicker.getEditor().getText();
        String endDate = endDatePicker.getEditor().getText();
        try {
            ArrayList<CrimeRecord> filteredRecords = SearchCrimeData.filterByDate(
                    getManager().getLocalCopy(),
                    startDatePicker.getEditor().getText(),
                    endDatePicker.getEditor().getText());

            startDateText.setText("");
            endDateText.setText("");
            populateTable(filteredRecords);
        } catch (ParseException e) { // Display error messages for appropriate dates
            if (!SearchCrimeData.isValidDate(startDate)) {
                startDateText.setText("Invalid Date");
            }
            if (!SearchCrimeData.isValidDate(endDate)) {
                endDateText.setText("Invalid Date");
            }
        }
    }

}
