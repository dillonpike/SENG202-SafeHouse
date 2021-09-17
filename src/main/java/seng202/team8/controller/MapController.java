package seng202.team8.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
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
        webEngine = new WebEngine(getClass().getResource("/map.html").toString());
        mapView = new WebView(webEngine);

    }
}
