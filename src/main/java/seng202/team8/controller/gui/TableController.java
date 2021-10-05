package seng202.team8.controller.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.controller.DataManager;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private ChoiceBox<String> cbDataset;


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
        clmBeat.setCellValueFactory(new PropertyValueFactory<>("beat"));
        clmWard.setCellValueFactory(new PropertyValueFactory<>("ward"));

        initializeAttributes();
        updateTable();
        if (DataManager.getDatasets().size() > 1 || DataManager.getCurrentDataset().isEmpty() == false) {
        	for (int i=1; i <= DataManager.getDatasets().size(); i++) {
            	cbDataset.getItems().add("Dataset " + i);
            }
        	cbDataset.getSelectionModel().select(DataManager.getDatasets().indexOf(DataManager.getCurrentDataset()));
        }
        cbDataset.setOnAction(event -> {
    		DataManager.setCurrentDataset(DataManager.getDatasets().get(cbDataset.getSelectionModel().getSelectedIndex()));
            updateTable();
    	});
        
    }

    /**
     * Calls the method to locate a file through windows explorer then passes it to the CrimeRecordManager for importing. Updates the table after.
     */
    public void importFile() {
        String filename = openFileLocation();
        if (filename == null) {
        	return;
        } else if (DataManager.getCurrentDataset().isEmpty()) {
        	try {
				getManager().importFile(filename);
			} catch (FileNotFoundException e) {
				// File not found
				e.printStackTrace();
			}
        	cbDataset.getItems().add("Dataset 1");
        	cbDataset.getSelectionModel().select(0);
            updateTable();
        } else {
		    Alert importAlert = new Alert(Alert.AlertType.NONE);
		    importAlert.setTitle("Choose dataset for import");
		    ButtonType btnNew = new ButtonType("Create new dataset", ButtonBar.ButtonData.YES);
		    ButtonType btnExisting = new ButtonType("Add to current dataset", ButtonBar.ButtonData.NO);
		    ButtonType btnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
		    importAlert.getButtonTypes().setAll(btnNew, btnExisting, btnCancel);
		    importAlert.showAndWait().ifPresent(type-> {
		    	if (type == btnNew) {
		    		CrimeRecordManager newDataset = new CrimeRecordManager();
		    		DataManager.addToDatasets(newDataset);
		    		DataManager.setCurrentDataset(newDataset);
		    		try {
						newDataset.importFile(filename);
						updateTable();
						cbDataset.getItems().add("Dataset " + DataManager.getDatasets().size());
						cbDataset.getSelectionModel().select(DataManager.getDatasets().size() - 1);
					} catch (FileNotFoundException e) {
						// File not found
						e.printStackTrace();
					}
		    	} else if (type == btnExisting) {
		    		try {
		                getManager().importFile(filename);
		                //Update the screen
                        updateTable();
		            } catch (FileNotFoundException e) {
		                // The file wasn't found!
		                e.printStackTrace();
		            }
		    	} else {
		    	}
		    });
        }
    }
    
    public void exportFile() {
    	String targetLocation = openDirectoryLocation();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");  
    	LocalDateTime now = LocalDateTime.now();
    	try {
			DataManager.getCurrentDataset().exportFile(targetLocation + "/" + dtf.format(now) + ".csv");
		} catch (IOException e) {
			// Bad directory
			e.printStackTrace();
		}
    }

    /**
     * Filters the crimes in the module's records ArrayList and updates recordTable with them.
     */
    public void updateTable() {
        filterRecords();
    	recordTable.setItems(FXCollections.observableArrayList(records));
    }

    /**
     * Applies filters to the list of records displayed in recordTable and updates the table if realTimeCheckBox
     * is selected.
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
