package seng202.team8.controller.gui;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.team8.controller.DataManager;
import seng202.team8.model.CrimeRecord;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A controller for GUI elements of the map screen
 * Contains attributes and methods for controlling the map screen
 * of the GUI
 */
public class MapController extends RecordController implements Initializable {

    /**
     * TextField where the user enters how many map markers to display.
     */
    @FXML
    private TextField markNumberField;

    /**
     * Text corresponding to markNumberField used to display user feedback
     */
    @FXML
    private Text markNumberText;

    /**
     * GUI object that contains the map.
     */
    @FXML
    private WebView mapView;

    /**
     * Manages the web page used to display the map.
     */
    private WebEngine webEngine;

    /**
     * Initializes attributes of the map screen.
     * Parameter description from JavaFX's Initializable Javadoc.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        markNumberText.setText("");
        webEngine = mapView.getEngine();
        webEngine.load(Objects.requireNonNull(getClass().getResource("/map.html")).toExternalForm());

        // Maps the records once the web engine has loaded
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                mapRecords();
            }
        });

        initializeAttributes();
        filterRecords();
        markNumberField.setText(DataManager.getMarkNumber());
    }

    /**
     * Stores the contents of markNumberField in DataManager.
     */
    @Override
    protected void storeUniqueInfo() {
        DataManager.setMarkNumber(markNumberField.getText());
    }

    /**
     * Resets and filters list of records stored in the controller then updates the screen's record display (map
     * in this case) with them.
     */
    @Override
    public void updateRecordDisplay() {
        lblFeedback.setText("");
        filterRecords();
        mapRecords();
    }

    /**
     * Places a marker on the map for the first 'number' crime records in the given ArrayList.
     * Performance drops quite low when 1000 or so markers are placed.
     * @param records ArrayList of CrimeRecords to be marked on the map
     * @param number number of records to place
     */
    public void placeNumMarkers(ArrayList<CrimeRecord> records, int number) {
        // Lowers number to the total number of records
        number = Math.min(records.size(), number);

        // Go through a for loop a set number of times
        for (int i = 0; i < number; i++) {
            CrimeRecord crime = records.get(i);
            /*
            Creates the following string:
            The values in <> are replaced with their actual values
            But note that the double-quotes are actually in the string
            placeMarker(<latitude>, <longitude>, "<caseNum>", "<crime.toString>");
             */
            String scriptToExecute = "placeMarker(" + crime.getLatitude() + ", " +
                    crime.getLongitude() + ", \"" +
                    crime.getCaseNum() + "\"" + ", \"" +
                    crime.toScript() + "\"" + ");";
            webEngine.executeScript(scriptToExecute);
        }
    }

    /**
     * Resets the list of records stored in the controller to the full dataset, applies filters based on
     * the user's input in the GUI's attributes, then updates the map by placing a marker for each record.
     * Only runs if realTimeCheckBox is checked.
     */
    public void filterMap() {
        if (realTimeCheckBox.isSelected()) {
            updateRecordDisplay();
        }
    }

    /**
     * Places a marker for each record stored in the controller.
     */
    public void mapRecords() {
        try {
            String input = markNumberField.getText();
            int quantity = Integer.parseInt(input);
            if ((quantity < 1) || (quantity > 1000)) {
                /*
                We do the same thing in this case as we do for a wrongly parsed int
                so just throw the same kind of exception
                 */
                throw new NumberFormatException("Wrong Size!");
            }
            // Clear the text
            markNumberText.setText("");
            // Clear any current markers
            String scriptToExecute = "deleteMarkers();";
            webEngine.executeScript(scriptToExecute);
            // Map them
            placeNumMarkers(records, quantity);

        } catch (NumberFormatException e) {
            // Display error message if user input invalid number
            if (!markNumberField.getText().equals("")) {
                markNumberText.setText("Please enter a number between 1 and 1000");
            }
        }
    }

}
