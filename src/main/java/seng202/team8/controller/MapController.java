package seng202.team8.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController extends Controller implements Initializable {


    @FXML
    private WebView mapView;
    
    private WebEngine webEngine;


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
     * Places a marker on the map
     * at the specified position
     * the first number Crime Records in the
     * given ArrayList
     * This breaks if too many markers are put together
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
     * Places markers for all the crimes
     * in the manager's local copy
     *
     * Externalized so that other functions can map
     * specific ArrayLists of records
     */
    public void mapManagerRecords() {
        placeNumMarkers(getManager().getLocalCopy(), 100);
    }

    public void testMapping() {
        try {
            getManager().importFile("src/test/java/seng202/team8/controller/5kRecords.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        placeNumMarkers(getManager().getLocalCopy(), 100);
    }
}
