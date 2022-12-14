package seng202.team8.controller.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng202.team8.controller.DataManager;
import seng202.team8.controller.ValidateCrime;
import seng202.team8.model.CrimeRecord;
import javafx.fxml.Initializable;

/**
 * A pop up window to collect user input for editing or adding a new crime record, functionality is shared between the two and determined by a toEdit boolean.
 */
public class AddRecordController extends GUIController implements Initializable {

	/**
     * Text field for the user to input a crime record's case number.
     */
	@FXML
    public TextField fldCaseNum;

	/**
     * Date selector for the user to input a crime record's date of occurrence.
     */
    @FXML
    private DatePicker fldDate;

    /**
     * Date selector for the user to input a crime record's date of occurrence.
     */
    @FXML
    private TextField fldBlock;

    /**
     * Text field for the user to input a crime record's IUCR.
     */
    @FXML
    private TextField fldIUCR;

    /**
     * Text field for the user to input a crime record's primary description.
     */
    @FXML
    private TextField fldPrimaryDesc;

    /**
     * Text field for the user to input a crime record's secondary description.
     */
    @FXML
    private TextField fldSecondaryDesc;

    /**
     * Text field for the user to input a crime record's secondary description.
     */
    @FXML
    private ComboBox<String> fldLocation;

    /**
     * Check box for the user to select if a crime resulted in an arrest.
     */
    @FXML
    private CheckBox fldArrest;

    /**
     * Check box for the user to select if a crime was a domestic incident.
     */
    @FXML
    private CheckBox fldDomestic;

    /**
     * Text field for the user to input a crime record's beat.
     */
    @FXML
    private TextField fldBeat;

    /**
     * Text field for the user to input a crime record's ward.
     */
    @FXML
    private TextField fldWard;

    /**
     * Text field for the user to input a crime record's FBICD.
     */
    @FXML
    private TextField fldFBI;

    /**
     * Text field for the user to input a crime record's latitude.
     */
    @FXML
    private TextField fldLat;

    /**
     * Text field for the user to input a crime record's longitude.
     */
    @FXML
    private TextField fldLon;
    
    /**
     * Spinner for the user to select the hour a crime occurred.
     */
    @FXML
    private Spinner<Integer> fldHour;
    
    /**
     * Spinner for the user to select the minute a crime occurred.
     */
    @FXML private Spinner<Integer> fldMinute;
    
    /**
     * Title at the top of the add record pop up, is conditionally "ADD RECORD" or "EDIT RECORD".
     */
    @FXML
    public Label lblTitle;
    
    /**
     * Boolean to let functions know is the user is editing a crime or adding a new one, true if editing, false if adding.
     */
    public boolean editing = false;
    
    /**
     * The selected crime record to be edited.
     */
    public CrimeRecord toEdit = null;
    
    /**
     * The controller of the current window, used to notify the table something has changed.
     */
    public RecordController currentController;

    /**
     * Warning message shown to the user in the event they input invalid data for case#.
     */
    @FXML
    private Label lblCaseWarning;

    /**
     * Warning message shown to the user in the event they input no data for date.
     */
    @FXML
    private Label lblDateWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for IUCR.
     */
    @FXML
    private Label lblIUCRWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for the primary description.
     */
    @FXML
    private Label lblPrimaryWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for beat.
     */
    @FXML
    private Label lblBeatWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for ward.
     */
    @FXML
    private Label lblWardWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for FBICD.
     */
    @FXML
    private Label lblFBIWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for latitude.
     */
    @FXML
    private Label lblLatWarning;

    /**
     * Warning message shown to the user in the event they input invalid data for longitude.
     */
    @FXML
    private Label lblLonWarning;

	/**
	 * Stores no info because the add record controller has no information to store.
	 */
	@Override
	protected void storeInfo() {
		// Do nothing
	}
    
    /**
     * Initialises the fields for all values to that of the crime record to be edited.
     */
    public void fillFields() {
    	fldCaseNum.setText(toEdit.getCaseNum());
    	fldDate.setValue(toEdit.getDate());
    	fldHour.getValueFactory().setValue(toEdit.getTime().getHour());
		fldMinute.getValueFactory().setValue(toEdit.getTime().getMinute());
    	fldBlock.setText(toEdit.getBlock());
    	fldIUCR.setText(toEdit.getIucr());
    	fldPrimaryDesc.setText(toEdit.getPrimary());
    	fldSecondaryDesc.setText(toEdit.getSecondary());
    	fldLocation.getSelectionModel().select(toEdit.getLocDescription());
    	if (toEdit.getWasArrestValue() == 1) {
    		fldArrest.setSelected(true);
    	}
    	if (toEdit.getWasDomesticValue() == 1) {
    		fldDomestic.setSelected(true);
    	}
    	fldBeat.setText(String.valueOf(toEdit.getBeat()));
    	fldWard.setText(String.valueOf(toEdit.getWard()));
    	fldFBI.setText(toEdit.getFbiCD());
    	fldLat.setText(String.valueOf(toEdit.getLatitude()));
    	fldLon.setText(String.valueOf(toEdit.getLongitude()));
    }
	
    /**
     * Calls all validation methods, and if none fail either a new record will be created using data from every field of the window, or the given record will
     * be updated to the values present in all fields. Editing or creating is determined by the editing boolean flag. After this the table is updated and the 
     * stage is closed.
     */
    public void createRecord() {
    	if (checkFields()) {
    		CrimeRecord newRecord = new CrimeRecord(fldCaseNum.getText(), fldDate.getValue().getMonthValue(), fldDate.getValue().getDayOfMonth(),
    		fldDate.getValue().getYear(), LocalTime.of(fldHour.getValue(), fldMinute.getValue()), fldBlock.getText(), fldIUCR.getText(), fldPrimaryDesc.getText(), fldSecondaryDesc.getText(),
    		fldLocation.getSelectionModel().getSelectedItem(), parseCheckbox(fldArrest), parseCheckbox(fldDomestic), Integer.parseInt(fldBeat.getText()),
    		Integer.parseInt(fldWard.getText()), fldFBI.getText(), Double.parseDouble(fldLat.getText()), Double.parseDouble(fldLon.getText()));
    		if (!editing) {
    			DataManager.getCurrentDataset().addRecord(newRecord);
    		} else {
    			LocalDate date = newRecord.getDate();
    			DataManager.getCurrentDataset().changeRecord(toEdit, newRecord.getCaseNum(), date.getMonthValue(), date.getDayOfMonth(), date.getYear(),
    					newRecord.getTime(), newRecord.getBlock(), newRecord.getIucr(), newRecord.getPrimary(), newRecord.getSecondary(), newRecord.getLocDescription(),
    					newRecord.getWasArrestValue(), newRecord.getWasDomesticValue(), newRecord.getBeat(), newRecord.getWard(), newRecord.getFbiCD(), newRecord.getLatitude(),
    					newRecord.getLongitude());
    		}
    		Stage addRecordPopup = (Stage) fldArrest.getScene().getWindow();
    		addRecordPopup.close();

			currentController.updateRecordDisplay();
    	}
    }
    
    /**
     * Parses a checkbox into a 0 or 1 depending on if it is ticked.
     * @param c checkbox to be inspected
     * @return 0 if the checkbox is not checked, 1 if it is.
     */
    public int parseCheckbox(CheckBox c) {
    	if (c.isSelected()) {
    		return 1;
    	} else {
    		return 0;
    	}
    }

	/**
	 * Checks all the fields that must have valid entries and returns true if they are all valid, otherwise false.
	 * @return true if all necessary fields have valid entries, otherwise false.
	 */
	private boolean checkFields() {
    	boolean caseNum = checkCaseNum();
    	boolean date = checkDate();
    	boolean fbi = checkFBI();
    	boolean iucr = checkIUCR();
    	boolean primary = checkPrimary();
    	boolean ward = checkWard();
    	boolean beat = checkBeat();
    	boolean lat = checkLat();
    	boolean lon = checkLon();
    	return caseNum && date && fbi && iucr && primary && ward && beat && lat && lon;
	}

    /**
     * Calls the case number validation method to compare with the current value in the case number field.
     * @return true if the value is accepted, false otherwise.
     */
    private boolean checkCaseNum() {
    	if (!ValidateCrime.validateCaseNum(fldCaseNum.getText())) {
    		fldCaseNum.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblCaseWarning.setText("Must be two letters followed by numbers.");
    		lblCaseWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblCaseWarning.setText("");
    		fldCaseNum.setStyle(null);
    		return true;
    	}
    }

	/**
	 * Checks if the date field contains a valid date. If it doesn't it sets the field to have a red border, otherwise
	 * it resets the border.
	 * @return true if the date field contains a valid date, otherwise false.
	 */
	private boolean checkDate() {
		if (fldDate.getValue() == null) {
			fldDate.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
			lblDateWarning.setText("Must select a date.");
			lblDateWarning.setStyle("-fx-text-fill: red");
			return false;
		} else {
			lblDateWarning.setText("");
			fldDate.setStyle(null);
			return true;
		}
	}

	/**
     * Calls the FBICD validation method to compare with the current value in the FBICD field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkFBI() {
		String fbiCD = fldFBI.getText();
    	if (fbiCD != null && !fbiCD.equals("") && !ValidateCrime.validateFbiCD(fbiCD)) {
    		fldFBI.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblFBIWarning.setText("Length not 3, only last char can be letter.");
    		lblFBIWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblFBIWarning.setText("");
    		fldFBI.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the IUCR validation method to compare with the current value in the IUCR field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkIUCR() {
    	if (!ValidateCrime.validateIucr(fldIUCR.getText())) {
    		fldIUCR.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblIUCRWarning.setText("Must be 4 characters.");
    		lblIUCRWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblIUCRWarning.setText("");
    		fldIUCR.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the primary description validation method to compare with the current value in the primaryDesc field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkPrimary() {
    	if (!ValidateCrime.validatePrimary(fldPrimaryDesc.getText())) {
    		fldPrimaryDesc.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblPrimaryWarning.setText("Can only include letters A-Z, a-z.");
    		lblPrimaryWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblPrimaryWarning.setText("");
    		fldPrimaryDesc.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the ward validation method to compare with the current value in the ward field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkWard() {
    	if (!ValidateCrime.validateInt(fldWard.getText())) {
    		fldWard.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblWardWarning.setText("Must be a whole number.");
    		lblWardWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblWardWarning.setText("");
    		fldWard.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the beat validation method to compare with the current value in the beat field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkBeat() {
    	if (!ValidateCrime.validateInt(fldBeat.getText())) {
    		fldBeat.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblBeatWarning.setText("Must be a whole number.");
    		lblBeatWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblBeatWarning.setText("");
    		fldBeat.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the latitude validation method to compare with the current value in the latitude field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkLat() {
    	if (!ValidateCrime.validateLatitude(fldLat.getText())) {
    		fldLat.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
    		lblLatWarning.setText("Must be decimal between " + ValidateCrime.MIN_LATITUDE + " and " +
								  ValidateCrime.MAX_LATITUDE + ".");
    		lblLatWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblLatWarning.setText("");
    		fldLat.setStyle(null);
    		return true;
    	}
    }

    /**
     * Calls the latitude validation method to compare with the current value in the latitude field.
     * @return true if the value is accepted, false otherwise.
     */
	private boolean checkLon() {
    	if (!ValidateCrime.validateLongitude(fldLon.getText())) {
    		fldLon.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 1; -fx-background-radius: 1px, 0px");
			lblLonWarning.setText("Must be a decimal between " + ValidateCrime.MIN_LONGITUDE + " and " +
								  ValidateCrime.MAX_LONGITUDE + ".");
    		lblLonWarning.setStyle("-fx-text-fill: red");
    		return false;
    	} else {
    		lblLonWarning.setText("");
    		fldLon.setStyle(null);
    		return true;
    	}
    }
    
    /**
     * Initialises the combo box for location with all possible values, also makes time spinners directly editable
     */
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
			fldLocation.getSelectionModel().select(fldLocation.getItems().get(0));
			fldHour.setEditable(true);
			fldMinute.setEditable(true);
		
	}

	/**
	 * Closes the add record popup window
	 * @param e Node to collect the attached stage from
	 */
	public void closeAddRecord(ActionEvent e) {
		Stage addRecordPopup = (Stage)((Node)e.getSource()).getScene().getWindow();
		addRecordPopup.close();
	}
}
