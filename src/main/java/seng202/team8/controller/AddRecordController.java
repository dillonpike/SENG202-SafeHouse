package seng202.team8.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.team8.model.CrimeRecord;
import javafx.fxml.Initializable;

public class AddRecordController extends GUIController implements Initializable {

	@FXML
    public TextField fldCaseNum;

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
    
    @FXML
    private Spinner<Integer> fldHour;
    
    @FXML private Spinner<Integer> fldMinute;
    
    @FXML
    public Label lblTitle;
    
    public boolean editing = false;
    
    public CrimeRecord toEdit = null;
    
    public void fillFields() {
    	fldCaseNum.setText(toEdit.getCaseNum());
    	fldBlock.setText(toEdit.getBlock());
    	fldIUCR.setText(toEdit.getIucr());
    	fldPrimaryDesc.setText(toEdit.getPrimary());
    	fldSecondaryDesc.setText(toEdit.getSecondary());
    	fldLocation.getSelectionModel().select(toEdit.getLocDescription());
    	if (toEdit.getWasArrest() == 1) {
    		fldArrest.setSelected(true);
    	}
    	if (toEdit.getWasDomestic() == 1) {
    		fldDomestic.setSelected(true);
    	}
    	fldBeat.setText(String.valueOf(toEdit.getBeat()));
    	fldWard.setText(String.valueOf(toEdit.getWard()));
    	fldFBI.setText(toEdit.getFbiCD());
    	fldLat.setText(String.valueOf(toEdit.getLatitude()));
    	fldLon.setText(String.valueOf(toEdit.getLongitude()));
    }
	
    public void createRecord() {
    	if (checkCaseNum() && checkFBI() && checkIUCR() && checkPrimary() && checkWard() && checkBeat() && checkLat() && checkLon() == true) {
    		CrimeRecord newRecord = new CrimeRecord(fldCaseNum.getText(), fldDate.getValue().getMonthValue(), fldDate.getValue().getDayOfMonth(),
    		fldDate.getValue().getYear(), (String.valueOf(fldHour.getValue()) + ":" + String.valueOf(fldMinute.getValue())), fldBlock.getText(), fldIUCR.getText(), fldPrimaryDesc.getText(), fldSecondaryDesc.getText(),
    		fldLocation.getSelectionModel().getSelectedItem(), parseCheckbox(fldArrest), parseCheckbox(fldDomestic), (int)Integer.valueOf(fldBeat.getText()), 
    		(int)Integer.valueOf(fldWard.getText()), fldFBI.getText(), (double)Double.valueOf(fldLat.getText()), (double)Double.valueOf(fldLon.getText()));
    		if (editing == false) {
    			DataManager.getCurrentDataset().addRecord(newRecord);
    		} else {
    			DataManager.getCurrentDataset().changeRecord(toEdit, newRecord.getCaseNum(), newRecord.getDate()[0], newRecord.getDate()[1], newRecord.getDate()[2], 
    					newRecord.getTimeOfCrime(), newRecord.getBlock(), newRecord.getIucr(), newRecord.getPrimary(), newRecord.getSecondary(), newRecord.getLocDescription(),
    					newRecord.getWasArrest(), newRecord.getWasDomestic(), newRecord.getBeat(), newRecord.getWard(), newRecord.getFbiCD(), newRecord.getLatitude(), 
    					newRecord.getLongitude());
    		}
    		Stage addRecordPopup = (Stage) fldArrest.getScene().getWindow();
    		addRecordPopup.close();
    		
    	}
    }
    
    public int parseCheckbox(CheckBox c) {
    	if (c.isSelected()) {
    		return 1;
    	} else {
    		return 0;
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
			fldHour.setEditable(true);
			fldMinute.setEditable(true);
		
	}

}
