package seng202.team8.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import seng202.team8.model.CrimeRecord;
import javafx.fxml.Initializable;

public class AddRecordController extends Controller implements Initializable {

	@FXML
    private TextField fldCaseNum;

    @FXML
    private DatePicker fldDate;

    @FXML
    private TextField fldBlock;

    @FXML
    private TextField fldIUCR;

    @FXML
    private TextField fldPrimaryDesc;

    @FXML
    private TextField fldSecondaryDesc;

    @FXML
    private ComboBox<?> fldLocation;

    @FXML
    private CheckBox fldArrest;

    @FXML
    private CheckBox fldDomestic;

    @FXML
    private TextField fldBeat;

    @FXML
    private TextField fldWard;

    @FXML
    private TextField fldFBI;

    @FXML
    private TextField fldX;

    @FXML
    private TextField fldY;

    @FXML
    private TextField fldLat;

    @FXML
    private TextField fldLon;

    @FXML
    private Button btnClose;
	
    public void createRecord() {
    	CrimeRecord newRecord = new CrimeRecord(fldCaseNum.getText(), fldDate.getValue().getMonthValue(), fldDate.getValue().getDayOfMonth(),
    			fldDate.getValue().getYear(), "0:00", fldBlock.getText(), fldIUCR.getText(), fldPrimaryDesc.getText(), fldSecondaryDesc.getText(),
    			"tempLoc", -1, -1, (int)Integer.valueOf(fldBeat.getText()), 
    			(int)Integer.valueOf(fldWard.getText()), fldFBI.getText(), (double)Double.valueOf(fldLat.getText()), (double)Double.valueOf(fldLon.getText()));
    	System.out.println(newRecord.toString());
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
