package seng202.team8.controller.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.team8.model.CrimeRecord;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contains attributes and methods for the table page in the application's GUI.
 */
public class TableController extends RecordController implements Initializable {

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
     * Column for a crime record's primary description.
     */
    @FXML
    private TableColumn<CrimeRecord, String> clmPrimaryDesc;

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
     * Label that displays the selected crime record's date in the crime details panel.
     */
    @FXML
    private Label lblDate;

    /**
     * Label that displays the selected crime record's case number in the crime details panel.
     */
    @FXML
    private Label lblCaseNum;

    /**
     * Label that displays the selected crime record's block in the crime details panel.
     */
    @FXML
    private Label lblBlock;

    /**
     * Label that displays the selected crime record's IUCR code in the crime details panel.
     */
    @FXML
    private Label lblUCR;

    /**
     * Label that displays the selected crime record's primary description in the crime details panel.
     */
    @FXML
    private Label lblPrimary;

    /**
     * Label that displays the selected crime record's secondary description in the crime details panel.
     */
    @FXML
    private Label lblSecondary;

    /**
     * Label that displays the selected crime record's location in the crime details panel.
     */
    @FXML
    private Label lblLocation;

    /**
     * Label that displays the selected crime record's arrest stats in the crime details panel.
     */
    @FXML
    private Label lblArrest;

    /**
     * Label that displays the selected crime record's domestic status in the crime details panel.
     */
    @FXML
    private Label lblDomestic;

    /**
     * Label that displays the selected crime record's beat in the crime details panel.
     */
    @FXML
    private Label lblBeat;

    /**
     * Label that displays the selected crime record's ward in the crime details panel.
     */
    @FXML
    private Label lblWard;

    /**
     * Label that displays the selected crime record's FBI CD in the crime details panel.
     */
    @FXML
    private Label lblFbi;

    /**
     * Label that displays the selected crime record's latitude in the crime details panel.
     */
    @FXML
    private Label lblLat;

    /**
     * Label that displays the selected crime record's longitude in the crime details panel.
     */
    @FXML
    private Label lblLon;

    /**
     * Links recordTable columns to attributes of CrimeRecord.
     * Parameter description from JavaFX's Initializable Javadoc.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNum.setCellValueFactory(new PropertyValueFactory<>("caseNum"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmPrimaryDesc.setCellValueFactory(new PropertyValueFactory<>("primary"));
        clmLocation.setCellValueFactory(new PropertyValueFactory<>("locDescription"));
        clmArrest.setCellValueFactory(new PropertyValueFactory<>("wasArrest"));
        clmDomestic.setCellValueFactory(new PropertyValueFactory<>("wasDomestic"));
        clmBeat.setCellValueFactory(new PropertyValueFactory<>("beat"));
        clmWard.setCellValueFactory(new PropertyValueFactory<>("ward"));
        initializeAttributes();
        updateRecordDisplay();
    }

    /**
     * Stores nothing because it has no information to store that's unique to RecordController.
     */
    @Override
    protected void storeUniqueInfo() {
        // Do nothing
    }

    /**
     * Resets and filters the current list of records stored in the controller then updates the screen's record
     * display (table in this case) with them.
     */
    @Override
    public void updateRecordDisplay() {
        lblFeedback.setText("");
        filterRecords();
        recordTable.setItems(FXCollections.observableArrayList(records));
        recordTable.refresh();
    }

    /**
     * Resets the list of records stored in the controller to the full dataset, applies filters based on
     * the user's input in the GUI's attributes, then updates the table.
     * Only runs if realTimeCheckBox is checked.
     */
    public void filterTable() {
        if (realTimeCheckBox.isSelected()) {
            updateRecordDisplay();
        }
    }

    /**
     * Removes a selected record from the local copy of records and updates the table, maintains selected position
     * on the screen.
     */
    public void deleteRecord() {
    	int index = recordTable.getSelectionModel().getSelectedIndex();
    	getManager().removeRecord(recordTable.getSelectionModel().getSelectedItem());
        filterTable();
        updateRecordDisplay();
    	recordTable.getSelectionModel().select(index);
    }

    /**
     * Calls the method to open the window for adding a new record, then sets the title to EDIT RECORD and passes
     * through the current crime tothe newly created window. The case number field is locked to prevent editing and
     * a flag is set to let the window know it is in edit mode.
     */
    public void editRecord() {
        lblFeedback.setText("");
    	if (recordTable.getSelectionModel().getSelectedItem() != null) {
    		AddRecordController editController = openAddRecord();
    		editController.lblTitle.setText("EDIT RECORD");
    		editController.fldCaseNum.setEditable(false);
    		editController.editing = true;
    		editController.toEdit = recordTable.getSelectionModel().getSelectedItem();
    		editController.currentController = this;
    		editController.fillFields();
    	}
    }

    /**
     * Updates the extended information panel with the attributes of the
     * selected record
     */
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
            lblLat.setText(String.valueOf(String.format("%.4f", selectedRecord.getLatitude())));
            lblLon.setText(String.valueOf(String.format("%.4f", selectedRecord.getLongitude())));
        } catch (NullPointerException e) {
            // Do nothing if the table was clicked in an empty spot
        }
    }

    /**
     * Gets the TableView object that contains the records
     * @return The TableView object storing CrimeRecords
     */
    public TableView<CrimeRecord> getTable() {
    	return recordTable;
    }
}
