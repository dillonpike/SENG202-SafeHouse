package seng202.team8.controller.gui;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Provides static methods that allows the GUI to filter crime records.
 */
public class GUIFiltering {

    /**
     * Text displayed when user types an invalid date.
     */
    private static final String INVALID_DATE_TEXT = "Invalid Date";

    /**
     * Text displayed when user types an invalid beat.
     */
    private static final String INVALID_BEAT_TEXT = "Invalid Beat";

    /**
     * Text displayed when user types an invalid ward.
     */
    private static final String INVALID_WARD_TEXT = "Invalid Ward";

    /**
     * Filters records ArrayList by removing crime records that didn't occur between the dates provided
     * in the given date pickers. Displays an error message for the date pickers if they contain invalid dates.
     * @param records ArrayList of crime records to be filtered
     * @param startDatePicker DatePicker in the GUI that allows the user to choose a start date
     * @param endDatePicker DatePicker in the GUI that allows the user to choose an end date
     * @param startDateText Text object corresponding to startDatePicker
     * @param endDateText Text object corresponding to endDatePicker
     * @return filtered records
     */
    static ArrayList<CrimeRecord> filterDates(ArrayList<CrimeRecord> records, DatePicker startDatePicker,
                                               DatePicker endDatePicker, Text startDateText, Text endDateText) {
        String startDate = startDatePicker.getEditor().getText();
        String endDate = endDatePicker.getEditor().getText();
        try {
            records = SearchCrimeData.filterByDate(records, startDate, endDate);
            startDateText.setText("");
            endDateText.setText("");
        } catch (ParseException e) { // Display error messages for appropriate date pickers
            if (!startDate.equals("") && !SearchCrimeData.isValidDate(startDate)) {
                startDateText.setText(INVALID_DATE_TEXT);
            } else {
                startDateText.setText("");
            }
            if (!endDate.equals("") && !SearchCrimeData.isValidDate(endDate)) {
                endDateText.setText(INVALID_DATE_TEXT);
            } else {
                endDateText.setText("");
            }
        }
        return records;
    }

    /**
     * Filters records ArrayList by removing crime records that do not occur within the beat range provided in the beat
     * fields. Displays an error message for the fields if they contain invalid beats.
     * @param records ArrayList of crime records to be filtered
     * @param startBeatField TextField in the GUI that allows the user to pick the start beat
     * @param endBeatField TextField in the GUI that allows the user to pick the end beat
     * @param startBeatText Text object corresponding to startBeatField
     * @param endBeatText Text object corresponding to endBeatField
     * @return filtered records
     */
    static ArrayList<CrimeRecord> filterBeats(ArrayList<CrimeRecord> records, TextField startBeatField,
                                               TextField endBeatField, Text startBeatText, Text endBeatText) {
        String startBeatString = startBeatField.getText();
        String endBeatString = endBeatField.getText();
        try {
            records = SearchCrimeData.filterByCrimeLocationBeat(records,
                    Integer.parseInt(startBeatString), Integer.parseInt(endBeatString));
            startBeatText.setText("");
            endBeatText.setText("");
        } catch (NumberFormatException e) { // Display error messages for appropriate beat fields
            integerRangeChecker(startBeatString, endBeatString, startBeatText, endBeatText, INVALID_BEAT_TEXT);
        }
        return records;
    }

    /**
     * Filters records ArrayList by removing crime records that do not occur within the ward range provided in the ward
     * fields. Displays an error message for the fields if they contain invalid wards.
     * @param records ArrayList of crime records to be filtered
     * @param startWardField TextField in the GUI that allows the user to pick the start ward
     * @param endWardField TextField in the GUI that allows the user to pick the end ward
     * @param startWardText Text object corresponding to startWardField
     * @param endWardText Text object corresponding to endWardField
     * @return filtered records
     */
    static ArrayList<CrimeRecord> filterWards(ArrayList<CrimeRecord> records, TextField startWardField,
                                              TextField endWardField, Text startWardText, Text endWardText) {
        String startWardString = startWardField.getText();
        String endWardString = endWardField.getText();
        try {
            records = SearchCrimeData.filterByCrimeWard(records,
                    Integer.parseInt(startWardString), Integer.parseInt(endWardString));
            startWardText.setText("");
            endWardText.setText("");
        } catch (NumberFormatException e) { // Display error messages for appropriate ward fields
            integerRangeChecker(startWardString, endWardString, startWardText, endWardText, INVALID_WARD_TEXT);
        }
        return records;
    }

    /**
     * Checks that the strings given represent valid integers. If they don't, the corresponding Text object given is
     * set to display an error message given by invalidString.
     * @param startString first string to be checked
     * @param endString second string to be checked
     * @param startText Text object corresponding to startString
     * @param endText Text object corresponding to endString
     * @param invalidString invalid error message
     */
    private static void integerRangeChecker(String startString, String endString, Text startText,
                                            Text endText, String invalidString) {
        if (!startString.equals("") && !isInteger(startString)) {
            startText.setText(invalidString);
        } else {
            startText.setText("");
        }
        if (!endString.equals("") && !isInteger(endString)) {
            endText.setText(invalidString);
        } else {
            endText.setText("");
        }
    }

    /**
     * Returns true if string can be converted to an integer, otherwise false.
     * @param string string that is checked
     * @return true if string can be converted to an integer, otherwise false
     */
    private static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
