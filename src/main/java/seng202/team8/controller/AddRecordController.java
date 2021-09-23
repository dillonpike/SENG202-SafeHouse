package seng202.team8.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import seng202.team8.model.CrimeRecord;
import javafx.fxml.Initializable;

public class AddRecordController extends GUIController implements Initializable {

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
    private ComboBox<String> fldLocation;

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
		fldLocation.setItems(FXCollections.observableArrayList(
				"ABANDONED BUILDING",
				"AIRCRAFT",
				"AIRPORT BUILDING NON-TERMINAL - NON-SECURE AREA",
				"AIRPORT BUILDING NON-TERMINAL - SECURE AREA",
				"AIRPORT EXTERIOR - NON-SECURE AREA",
				"AIRPORT PARKING LOT",
				"AIRPORT TERMINAL LOWER LEVEL - NON-SECURE AREA",
				"AIRPORT TERMINAL UPPER LEVEL - NON-SECURE AREA",
				"AIRPORT TERMINAL UPPER LEVEL - SECURE AREA",
				"AIRPORT VENDING ESTABLISHMENT",
				"ALLEY",
				"ANIMAL HOSPITAL",
				"APARTMENT",
				"APPLIANCE STORE",
				"ATHLETIC CLUB",
				"ATM (AUTOMATIC TELLER MACHINE)",
				"AUTO",
				"AUTO / BOAT / RV DEALERSHIP",
				"BANK",
				"BAR OR TAVERN",
				"BARBERSHOP",
				"BOAT / WATERCRAFT",
				"BOWLING ALLEY",
				"BRIDGE",
				"CAR WASH",
				"CEMETARY",
				"CHA APARTMENT",
				"CHA HALLWAY / STAIRWELL / ELEVATOR",
				"CHA PARKING LOT / GROUNDS",
				"CHURCH / SYNAGOGUE / PLACE OF WORSHIP",
				"CLEANING STORE",
				"COIN OPERATED MACHINE",
				"COLLEGE / UNIVERSITY - GROUNDS",
				"COLLEGE / UNIVERSITY - RESIDENCE HALL",
				"COMMERCIAL / BUSINESS OFFICE",
				"CONSTRUCTION SITE",
				"CONVENIENCE STORE",
				"CTA BUS STOP",
				"CTA PARKING LOT / GARAGE / OTHER PROPERTY",
				"CTA PLATFORM",
				"CTA STATION",
				"CTA TRAIN",
				"CURRENCY EXCHANGE",
				"DAY CARE CENTER",
				"DEPARTMENT STORE",
				"DRIVEWAY - RESIDENTIAL",
				"DRUG STORE",
				"FACTORY / MANUFACTURING BUILDING",
				"GAS STATION",
				"GOVERNMENT BUILDING / PROPERTY",
				"GROCERY FOOD STORE",
				"HIGHWAY / EXPRESSWAY",
				"HOSPITAL BUILDING / GROUNDS",
				"HOTEL / MOTEL",
				"HOUSE",
				"JAIL / LOCK-UP FACILITY",
				"LAKEFRONT / WATERFRONT / RIVERBANK",
				"LIBRARY",
				"MEDICAL / DENTAL OFFICE",
				"MOVIE HOUSE / THEATER",
				"NURSING / RETIREMENT HOME",
				"OTHER (SPECIFY)",
				"OTHER COMMERCIAL TRANSPORTATION",
				"OTHER RAILROAD PROPERTY / TRAIN DEPOT",
				"PARK PROPERTY",
				"PARKING LOT",
				"PARKING LOT / GARAGE (NON RESIDENTIAL)",
				"PAWN SHOP",
				"POLICE FACILITY / VEHICLE PARKING LOT",
				"POOL ROOM",
				"PORCH",
				"RESIDENCE",
				"RESIDENCE - GARAGE",
				"RESIDENCE - PORCH / HALLWAY",
				"RESIDENCE - YARD (FRONT / BACK)",
				"RESTAURANT",
				"SCHOOL - PRIVATE BUILDING",
				"SCHOOL - PRIVATE GROUNDS",
				"SCHOOL - PUBLIC BUILDING",
				"SCHOOL - PUBLIC GROUNDS",
				"SIDEWALK",
				"SMALL RETAIL STORE",
				"SPORTS ARENA / STADIUM",
				"STREET",
				"TAVERN / LIQUOR STORE",
				"TAXICAB",
				"VACANT LOT",
				"VACANT LOT / LAND",
				"VEHICLE - COMMERCIAL",
				"VEHICLE - OTHER RIDE SHARE SERVICE (LYFT, UBER, ETC.)",
				"VEHICLE NON-COMMERCIAL",
				"WAREHOUSE"));
		
	}

}
