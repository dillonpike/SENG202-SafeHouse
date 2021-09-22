package seng202.team8.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController extends Controller implements Initializable {


    @FXML
    public TextField startBeatField;

    @FXML
    public DatePicker startDatePicker;

    @FXML
    public DatePicker endDatePicker;

    @FXML
    public Text startDateText;

    @FXML
    public Text endDateText;

    @FXML
    public TextField primaryDescField;

    @FXML
    public TextField locationField;

    @FXML
    public TextField endBeatField;

    @FXML
    public Text startBeatText;

    @FXML
    public Text endBeatText;

    @FXML
    public TextField startWardField;

    @FXML
    public TextField endWardField;

    @FXML
    public Text startWardText;

    @FXML
    public Text endWardText;

    @FXML
    public ComboBox arrestComboBox;

    @FXML
    public ComboBox domesticComboBox;

    @FXML
    public TextField markNumberField;

    @FXML
    public Text markNumberText;

    @FXML
    private WebView mapView;
    
    private WebEngine webEngine;

    private ArrayList<CrimeRecord> records = getManager().getLocalCopy();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();
    }

    private void initMap() {
    	webEngine = mapView.getEngine();
    	System.out.println("Map");
        webEngine.load(getClass().getResource("/map.html").toExternalForm());
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

        //Go through a for loop a set number of times
        for (int i = 0; i < number; i++) {
            CrimeRecord crime = records.get(i);
            String scriptToExecute = "placeMarker(" + crime.getLongitude() + ", " +
                    crime.getLatitude() + ", \"" +
                    crime.getCaseNum() + "\");";
            webEngine.executeScript(scriptToExecute);
        }
    }

    /**
     * Filters the map
     */
    public void filterMap() {

    }

    /**
     * Places markers for all the crimes
     * in this controller's records variable
     * which is all records that abide by the filter
     *
     */
    public void mapRecords() {
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
            placeNumMarkers(getManager().getLocalCopy(), quantity);

        } catch (NumberFormatException e) {
            //Display the text
            markNumberText.setText("Please enter a number between 1 and 1000");
        }

    }

}
