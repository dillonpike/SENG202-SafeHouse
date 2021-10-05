package seng202.team8.controller.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MapController extends RecordController implements Initializable {

    @FXML
    public TextField markNumberField;

    @FXML
    public Text markNumberText;

    @FXML
    private WebView mapView;
    
    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = mapView.getEngine();
        webEngine.load(Objects.requireNonNull(getClass().getResource("/map.html")).toExternalForm());
        initializeAttributes();
    }

    /**
     * Places a marker on the map for
     * the first 'number' Crime Records in the
     * given ArrayList
     * This breaks if too many markers are put together
     * That number being about 1000 or so
     * @param records The ArrayList of CrimeRecords to be marked on the map
     * @param number The number of records to place
     */
    public void placeNumMarkers(ArrayList<CrimeRecord> records, int number) {
        // Lowers number to the total number of records
        number = Math.min(records.size(), number);

        //Go through a for loop a set number of times
        for (int i = 0; i < number; i++) {
            CrimeRecord crime = records.get(i);
            String scriptToExecute = "placeMarker(" + crime.getLatitude() + ", " +
                    crime.getLongitude() + ", \"" +
                    crime.getCaseNum() + "\");";
            webEngine.executeScript(scriptToExecute);
        }
    }

    /**
     * Filters the records contained within this controller
     * Which determines what it mapped
     */
    public void filterMap() {
        if (realTimeCheckBox.isSelected()) {
            mapRecords();
        }
    }

    /**
     * Places markers for all the crimes
     * in this controller's records variable
     * which is all records that abide by the filter
     *
     */
    public void mapRecords() {
        filterRecords();
        try {
            String input = markNumberField.getText();
            int quantity = Integer.parseInt(input);
            if ((quantity < 1) || (quantity > 1000)) {
                /*
                We do the same thing in this case
                as we do for a wrongly parsed int
                so just throw the same kind of exception
                 */
                throw new NumberFormatException("Wrong Size!");
            }
            //Clear the text
            markNumberText.setText("");
            //Clear any current markers
            String scriptToExecute = "deleteMarkers();";
            webEngine.executeScript(scriptToExecute);
            //Map them
            placeNumMarkers(records, quantity);

        } catch (NumberFormatException e) {
            //Display the text
            markNumberText.setText("Please enter a number between 1 and 1000");
        }

    }

}
