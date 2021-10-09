package seng202.team8.controller.gui;

import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.controller.DataManager;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parent class for GUI controllers that can display, filter, and manipulate records.
 */
public abstract class RecordController extends GUIController {

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
     * ChoiceBox for selecting a dataset to view.
     */
    @FXML
    protected ChoiceBox<String> cbDataset;

    /**
     * Label for displaying user feedback that's not associated with a GUI element.
     */
    @FXML
    protected Label lblFeedback;

    /**
     * Initializes the attributes of the GUI screen.
     */
    protected void initializeAttributes() {
        // Adds filtering combo box options
        arrestComboBox.getItems().addAll("Don't filter", "Yes", "No");
        domesticComboBox.getItems().addAll("Don't filter", "Yes", "No");

        // Enters stored information into filtering widgets
        startDatePicker.getEditor().setText(DataManager.getStartDate());
        endDatePicker.getEditor().setText(DataManager.getEndDate());
        primaryDescField.setText(DataManager.getPrimaryDesc());
        locationField.setText(DataManager.getLocation());
        startBeatField.setText(DataManager.getStartBeat());
        endBeatField.setText(DataManager.getEndBeat());
        startWardField.setText(DataManager.getStartWard());
        endWardField.setText(DataManager.getEndWard());
        arrestComboBox.getSelectionModel().select(DataManager.getArrest());
        domesticComboBox.getSelectionModel().select(DataManager.getDomestic());
        realTimeCheckBox.setSelected(DataManager.getRealTime());

        records = getManager().getLocalCopy();

        // Initialises stored dataset selection
        for (int i=1; i <= DataManager.getDatasets().size(); i++) {
            cbDataset.getItems().add("Dataset " + i);
        }
        cbDataset.getSelectionModel().select(DataManager.getDatasets().indexOf(DataManager.getCurrentDataset()));
        cbDataset.setOnAction(event -> {
            DataManager.setCurrentDataset(DataManager.getDatasets().get(cbDataset.getSelectionModel().getSelectedIndex()));
            updateRecordDisplay();
        });
    }

    /**
     * Stores the information in the filtering widgets that the user has entered/selected as well as any information
     * unique to children classes.
     */
    @Override
    protected void storeInfo() {
        DataManager.setStartDate(startDatePicker.getEditor().getText());
        DataManager.setEndDate(endDatePicker.getEditor().getText());
        DataManager.setPrimaryDesc(primaryDescField.getText());
        DataManager.setLocation(locationField.getText());
        DataManager.setStartBeat(startBeatField.getText());
        DataManager.setEndBeat(endBeatField.getText());
        DataManager.setStartWard(startWardField.getText());
        DataManager.setEndWard(endWardField.getText());
        DataManager.setArrest(arrestComboBox.getSelectionModel().getSelectedIndex());
        DataManager.setDomestic(domesticComboBox.getSelectionModel().getSelectedIndex());
        DataManager.setRealTime(realTimeCheckBox.isSelected());
        storeUniqueInfo();
    }

    /**
     * Abstract method that allows children classes to store information when swapping to another GUI screen.
     */
    protected abstract void storeUniqueInfo();

    /**
     * Resets and filters the current list of records stored in the controller then updates the screen's record
     * display with them.
     */
    public abstract void updateRecordDisplay();

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
     * Calls the method to open the window for adding a new record, then initialises the table controller in the
     * window as this current object.
     */
    public void addRecord() {
        lblFeedback.setText("");
        AddRecordController addController = openAddRecord();
        addController.currentController = this;
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

    /**
     * Calls the method to locate a file through the file browser then passes it to the CrimeRecordManager
     * for importing. Updates the table after.
     */
    public void importFile() {
        String filename = openFileLocation();
        if (filename != null) {
            lblFeedback.setStyle("-fx-text-fill: black");
            if (!filename.endsWith(".csv")) { // If the filetype is not csv displays an error message
                setErrorFeedback("Invalid file type. Please choose a .csv file.");
            } else if (DataManager.getCurrentDataset().isEmpty()) { // If the current dataset is empty, automatically appends to it
                lblFeedback.setText("");
                try {
                    int invalidCount = getManager().importFile(filename).size();
                    updateRecordDisplay();
                    importFeedback(getManager().getLocalCopy().size(), invalidCount);
                } catch (FileNotFoundException e) {
                    // File not found
                    setErrorFeedback("File not found");
                } catch (IOException | CsvValidationException ex) {
                    // An error occured with importing
                    setErrorFeedback("An error has occurred with importing");
                }
            } else { // Else displays an alert asking the user if they want to append to dataset or make a new one, then does that
                lblFeedback.setText("");
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
                            int invalidCount = newDataset.importFile(filename).size();
                            updateRecordDisplay();
                            cbDataset.getItems().add("Dataset " + DataManager.getDatasets().size());
                            cbDataset.getSelectionModel().select(DataManager.getDatasets().size() - 1);
                            importFeedback(getManager().getLocalCopy().size(), invalidCount);

                        } else if (type == btnExisting) {
                            int previousSize = getManager().getLocalCopy().size();
                            int invalidCount = getManager().importFile(filename).size();
                            int newRecordCount = getManager().getLocalCopy().size() - previousSize;
                            // Update the screen
                            updateRecordDisplay();
                            importFeedback(newRecordCount, invalidCount);
                        }
                    } catch (FileNotFoundException e) {
                        // File not found
                        setErrorFeedback("File not found");
                    } catch (IOException | CsvValidationException ex) {
                        // An error occurred with importing
                        setErrorFeedback("An error has occurred with importing");
                    }
                });
            }
        }
    }

    /**
     * Sets the feedback label to display how many records were imported, and how many records were skipped due to
     * being invalid.
     * @param newRecordCount records added
     * @param invalidCount invalid records skipped
     */
    private void importFeedback(int newRecordCount, int invalidCount) {
        if (invalidCount > 0) {
            lblFeedback.setText("Imported " + (newRecordCount) + " new records. Skipped " + invalidCount +
                    " invalid records.");
        } else {
            lblFeedback.setText("Imported " + (newRecordCount) + " new records.");
        }
    }

    /**
     * Sets lblFeedback to display an error message and makes the text red.
     * @param message message to be displayed
     */
    private void setErrorFeedback(String message) {
        lblFeedback.setText(message);
        lblFeedback.setStyle("-fx-text-fill: red");
    }

    /**
     * Allows the user to select a folder through the file browser then exports the current dataset to the chosen
     * folder.
     */
    public void exportFile() {
        String targetLocation = openDirectoryLocation();
        if (targetLocation != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
            LocalDateTime now = LocalDateTime.now();
            try {
                lblFeedback.setText("");
                DataManager.getCurrentDataset().exportFile(targetLocation + "/" + dtf.format(now) + ".csv");
            } catch (IOException e) {
                // Bad directory
                lblFeedback.setText("Invalid directory for export");
            }
        }
    }
}
