package seng202.team8.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
    	if (checkCaseNum() && checkFBI() && checkIUCR() && checkPrimary() && checkWard() && checkBeat() && checkLat() && checkLon() == true) {
    		CrimeRecord newRecord = new CrimeRecord(fldCaseNum.getText(), fldDate.getValue().getMonthValue(), fldDate.getValue().getDayOfMonth(),
    		fldDate.getValue().getYear(), "0:00", fldBlock.getText(), fldIUCR.getText(), fldPrimaryDesc.getText(), fldSecondaryDesc.getText(),
    		"tempLoc", -1, -1, (int)Integer.valueOf(fldBeat.getText()), 
    		(int)Integer.valueOf(fldWard.getText()), fldFBI.getText(), (double)Double.valueOf(fldLat.getText()), (double)Double.valueOf(fldLon.getText()));
    		System.out.println(newRecord.toString());
    	}
    }
    
    public boolean checkCaseNum() {
    	if (ValidateCrime.validateCaseNum(fldCaseNum.getText())== false) {
    		fldCaseNum.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldCaseNum.setStyle(null);
    		return true;
    	}
    }
    
    public boolean checkFBI() {
    	if (ValidateCrime.validateFbiCD(fldFBI.getText()) == false) {
    		fldFBI.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldFBI.setStyle(null);
    		return true;
    	}
    }
    
    public boolean checkIUCR() {
    	if (ValidateCrime.validateIucr(fldIUCR.getText()) == false) {
    		fldIUCR.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldIUCR.setStyle(null);
    		return true;
    	}
    }
    	
    public boolean checkPrimary() {
    	if (ValidateCrime.validatePrimary(fldPrimaryDesc.getText()) == false) {
    		fldPrimaryDesc.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldPrimaryDesc.setStyle(null);
    		return true;
    	}
    }
    
    public boolean checkWard() {
    	if (ValidateCrime.validateInt(fldWard.getText()) == false) { 
    		fldWard.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldWard.setStyle(null);
    		return true;
    	}
    }
    	
    public boolean checkBeat() {
    	if (ValidateCrime.validateInt(fldBeat.getText()) == false) {
    		fldBeat.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldBeat.setStyle(null);
    		return true;
    	}
    }
    
    public boolean checkLat() {
    	if (ValidateCrime.validateDouble(fldLat.getText()) == false) {
    		fldLat.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldLat.setStyle(null);
    		return true;
    	}
    }
    
    public boolean checkLon() {
    	if (ValidateCrime.validateDouble(fldLon.getText()) == false) {
    		fldLon.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		return false;
    	} else {
    		fldLon.setStyle(null);
    		return true;
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
