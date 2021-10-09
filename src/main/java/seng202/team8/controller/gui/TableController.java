package seng202.team8.controller.gui;

import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.controller.DataManager;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * ChoiceBox for selecting a dataset to view.
     */
    @FXML
    private ChoiceBox<String> cbDataset;

    /**
     * Label for displaying user feedback that's not associated with a GUI element.
     */
    @FXML
    private Label lblTableWarning;

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
        updateTable();
        for (int i=1; i <= DataManager.getDatasets().size(); i++) {
            cbDataset.getItems().add("Dataset " + i);
        }
        cbDataset.getSelectionModel().select(DataManager.getDatasets().indexOf(DataManager.getCurrentDataset()));
        cbDataset.setOnAction(event -> {
    		DataManager.setCurrentDataset(DataManager.getDatasets().get(cbDataset.getSelectionModel().getSelectedIndex()));
            updateTable();
    	});
        
    }

    /**
     * Calls the method to locate a file through Windows Explorer then passes it to the CrimeRecordManager
     * for importing. Updates the table after.
     */
    public void importFile() {
        String filename = openFileLocation();
        if (filename != null) {
            if (!filename.endsWith(".csv")) { // If the filetype is not csv displays an error message
                lblTableWarning.setText("Invalid file type");
                lblTableWarning.setStyle("-fx-text-fill: red");
            } else if (DataManager.getCurrentDataset().isEmpty()) { // If the current dataset is empty, automatically appends to it
                lblTableWarning.setText("");
                try {
                    getManager().importFile(filename);
                } catch (FileNotFoundException e) {
                    // File not found
                    lblTableWarning.setText("File not found");
                } catch (IOException | CsvValidationException ex) {
                    // An error occured with importing
                    lblTableWarning.setText("An error has occurred with importing");
                }
                updateTable();
            } else { // Else displays an alert asking the user if they want to append to dataset or make a new one, then does that
                lblTableWarning.setText("");
                Alert importAlert = new Alert(Alert.AlertType.NONE);
                importAlert.setTitle("Choose dataset for import");
                ButtonType btnNew = new ButtonType("Create new dataset", ButtonBar.ButtonData.YES);
                ButtonType btnExisting = new ButtonType("Add to current dataset", ButtonBar.ButtonData.NO);
                ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                importAlert.getButtonTypes().setAll(btnNew, btnExisting, btnCancel);
                importAlert.showAndWait().ifPresent(type -> {
                try {
                	if (type == btnNew) {
                        CrimeRecordManager newDataset = new CrimeRecordManager();
                        DataManager.addToDatasets(newDataset);
                        DataManager.setCurrentDataset(newDataset);

                        newDataset.importFile(filename);
                        updateTable();
                        cbDataset.getItems().add("Dataset " + DataManager.getDatasets().size());
                        cbDataset.getSelectionModel().select(DataManager.getDatasets().size() - 1);

                    } else if (type == btnExisting) {
                        getManager().importFile(filename);
                        //Update the screen
                        updateTable();
                    }
                } catch (FileNotFoundException e) {
                    // File not found
                    lblTableWarning.setText("File not found");
                } catch (IOException | CsvValidationException ex) {
                    // An error occured with importing
                    lblTableWarning.setText("An error has occurred with importing");
                }
                });
            }
        }
    }
    
    public void exportFile() {
    	String targetLocation = openDirectoryLocation();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");  
    	LocalDateTime now = LocalDateTime.now();
    	try {
    		lblTableWarning.setText("");
			DataManager.getCurrentDataset().exportFile(targetLocation + "/" + dtf.format(now) + ".csv");
		} catch (IOException e) {
			// Bad directory
			lblTableWarning.setText("Invalid directory for export");
		}
    }

    /**
     * Filters the crimes in the module's records ArrayList and updates recordTable with them.
     */
    public void updateTable() {
    	lblTableWarning.setText("");
        filterRecords();
    	recordTable.setItems(FXCollections.observableArrayList(records));
    }

    /**
     * Resets the list of records stored in the controller to the full dataset, applies filters based on
     * the user's input in the GUI's attributes, then updates the table.
     * Only runs if realTimeCheckBox is checked.
     */
    public void filterTable() {
        if (realTimeCheckBox.isSelected()) {
            updateTable();
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
    	updateTable();
    	recordTable.getSelectionModel().select(index);
    }
    
    /**
     * Calls the method to open the window for adding a new record, then sets the title to EDIT RECORD and passes
     * through the current crime tothe newly created window. The case number field is locked to prevent editing and
     * a flag is set to let the window know it is in edit mode.
     */
    public void editRecord() {
    	lblTableWarning.setText("");
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
    	lblTableWarning.setText("");
    	AddRecordController addController = openAddRecord();
    	addController.currentTable = this;
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
