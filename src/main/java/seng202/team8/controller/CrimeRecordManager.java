package seng202.team8.controller;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.jdi.AbsentInformationException;
import seng202.team8.model.CrimeRecord;

/**
 * A class that contains the local copy of the crime records.
 */
public class CrimeRecordManager {

    /**
     * The crime records
     */
    private ArrayList<CrimeRecord> localCopy = new ArrayList<>();
    /**
     * A hashset of the crime record case numbers
     * Mainly for checking membership
     */
    private HashSet<String> containedRecords = new HashSet<>();

    /**
     * A hashtable, with the Primary description of a crime as the key,
     * and the value being the number of crimes in the local copy
     * with that description
     */
    private Hashtable<String, Integer> primaryFreq = new Hashtable<>();

    /**
     * A hashtable, with the Ward of a crime as the key,
     * and the value being the number of crimes in the local copy
     * with that ward
     */
    private Hashtable<Integer, Integer> wardFreq = new Hashtable<>();

    /**
     * A hashtable, with the beat of a crime as the key,
     * and the value being the number of crimes in the local copy
     * with that beat
     */
    private Hashtable<Integer, Integer> beatFreq = new Hashtable<>();

    /**
     * Gets the local copy of crime records
     * @return The local copy of CrimeRecord objects, in the form of an ArrayList
     */
    public ArrayList<CrimeRecord> getLocalCopy() {
        return localCopy;
    }

    /**
     * Gets the number of crimes in the local copy
     * with the given Primary Description
     * @param key The description
     * @return The number of crimes with that description
     */
    public Integer getPrimaryFreq(String key) {
        return primaryFreq.getOrDefault(key, 0);
    }

    /**
     * Gets the number of crimes in the local copy
     * with the given ward
     * @param key The ward
     * @return The number of crimes with that ward
     */
    public Integer getWardFreq(Integer key) {
        return wardFreq.getOrDefault(key, 0);
    }

    /**
     * Gets the number of crimes in the local copy
     * with the given beat
     * @param key The beat
     * @return The number of crimes with that beat
     */
    public Integer getBeatFreq(Integer key) {
        return beatFreq.getOrDefault(key, 0);
    }

    /**
     * Returns whether the record list is empty
     * @return True if this manager has no records
     */
    public boolean isEmpty() {
        return containedRecords.isEmpty();
    }
    
    /**
     * Goes through the file and adds each line to the localCopy as a CrimeRecord object
     * @param filename The name/filepath of the file to be imported
     * @throws FileNotFoundException If the filename cannot be found, this is thrown.
     * This should prompt the user to try again, so it will not be handled in this method
     * @return Returns an ArrayList of all lines that contain errors/could not be imported
     */
    public ArrayList<Integer> importFile(String filename) throws IOException, CsvValidationException {
        if (filename == null) {
            return new ArrayList<>();
        } else if (!filename.endsWith(".csv")) {
            // This is done so we can be sure the filename isn't null
            return new ArrayList<>();
        }
        CSVReader csvReader = new CSVReader(new FileReader(filename));
        String[] row;
        ArrayList<Integer> linesWithErrors = new ArrayList<>();
        int counter = 1;

        // Skip the first line since it's just header info
        csvReader.readNext();
        while ((row = csvReader.readNext()) != null) {
            // Each data array should be 17 entries long
            CrimeRecord newCrime = new CrimeRecord();
            // Try to add the essential stuff to the record
            if (!(addEssentials(row, newCrime))) {
                /*
                If we couldn't, skip this iteration
                and add this line to the lines with errors
                 */
                linesWithErrors.add(counter);
                continue;
            }
            // Check if the crime is already in there.
            if (containedRecords.contains(newCrime.getCaseNum())) {
                //The crime is already in here, so don't add it
                continue;
            } else {
                containedRecords.add(newCrime.getCaseNum());
            }
            // Add the non-essential stuff
            addSkipables(row, newCrime);

            // Increment the tables
            incrementFreqs(newCrime);

            /*
            And that's all the information for the row!
            There was one final entry, data[16], the location
            but, as we derive that from lat and lon
            We don't need to access it
            - Olly
             */
            counter++;
            localCopy.add(newCrime);
        }
        csvReader.close();

        return linesWithErrors;
    }

    /**
     * Helper function that adds the 'essentials' to a crime record entry
     * If any one of these are missing or invalid, the record will not
     * be imported
     * @param data The split string array of the line of the entry
     * @param newCrime The crime being modified
     * @return Returns true if successful, false if not
     */
    private boolean addEssentials(String[] data, CrimeRecord newCrime) {
        try {
            newCrime.setCaseNum(data[0]);
            String iucr = padIUCR(data[3]);
            newCrime.setIucr(iucr);
            newCrime.setPrimary(data[4]);
            newCrime.setLatitude(Double.parseDouble(data[14]));
            newCrime.setLongitude(Double.parseDouble(data[15]));
            return true;
        } catch (Exception ex) {
            /*
            We couldn't add essential data
            So return false and skip this record
             */
            return false;
        }
    }

    /**
     * Checks the given IUCR and attempts to pad it
     * with zero's if it's too short
     *
     * Will reject empty strings, but not trivial
     * strings like '1'
     *
     * used in importing as programs like excel
     * may chop off leading zeros
     * @param iucr The iucr to be padded
     */
    private String padIUCR(String iucr) throws AbsentInformationException {
        // The stringbuilder is a mutable string
        if (iucr.length() == 0) {
            // String is empty, so should be rejected
            throw new AbsentInformationException("Empty IUCR!");
        }
        StringBuilder paddedIucr = new StringBuilder(iucr);
        while (paddedIucr.length() < 4) {
            // add a '0' to the front
            paddedIucr.insert(0, '0');
        }
        return paddedIucr.toString();
    }

    /**
     * Adds the values to a crime record that can be missing
     * and the record will still be added
     * @param data The split string array of the line of the entry
     * @param newCrime The crime being modified
     */
    private void addSkipables(String[] data, CrimeRecord newCrime) {
        try {
            addDateAndTime(newCrime, data[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            // Do nothing and leave the field(s) empty.
        }
        /*
        These are formatted as/from strings, so
        if they're some wacky value that's just what they'll be
        Since we're not checking validity yet
         */
        newCrime.setBlock(data[2]);
        newCrime.setSecondary(data[5]);
        newCrime.setLocDescription(data[6]);
        newCrime.setWasArrest(yesOrNo(data[7]));
        newCrime.setWasDomestic(yesOrNo(data[8]));
        try {
            newCrime.setWard(Integer.parseInt(data[10]));
        }
        catch (NumberFormatException ex) {
            // Leave the ward with no value
        }
        try {
            newCrime.setBeat(Integer.parseInt(data[9]));
        } catch (NumberFormatException ex) {
            // Leave the beat with no value
        }
        newCrime.setFbiCD(data[11]);
        try {
            newCrime.setXCoord(Integer.parseInt(data[12]));
            newCrime.setYCoord(Integer.parseInt(data[13]));
        }
        catch (NumberFormatException ex) {
            // Do nothing and leave the two fields blank.
        } catch (ArrayIndexOutOfBoundsException ex) {
            // These values don't seem to exist!
            /*
            This hopefully shouldn't happen thanks to the limit settings
            of the split method.
             */
        }

    }

    /**
     * Helper method for importFile.
     * Adds the date and time entries.
     * @param crime The CrimeRecord object being modified
     * @param date The date and Time string (should be data[1]) containing the date and time
     */
    private void addDateAndTime(CrimeRecord crime, String date) {
        // Should split entries into [0] Date, [1] Time [2] AM/PM
        String[] dateAndTime = date.split(" ");
        String[] dateNums = dateAndTime[0].split("/");
        int month = Integer.parseInt(dateNums[0]);
        int day = Integer.parseInt(dateNums[1]);
        int year = Integer.parseInt(dateNums[2]);
        crime.setDate(month, day, year);
        crime.setTime(stringToLocalTime((dateAndTime[1] + " " + dateAndTime[2])));
    }

    /**
     * Helper method for importing that converts
     * a string of times into a LocalTime object
     * @param timeString The string to be converted
     *                   Should be in the format HH:MM:SS
     * @return A LocalTime object of the given time string
     */
    private LocalTime stringToLocalTime(String timeString) {
        String[] timeStrings = timeString.split(":");
        int hour = Integer.parseInt(timeStrings[0]);
        int minute = Integer.parseInt(timeStrings[1]);
        int second = Integer.parseInt(timeStrings[2].substring(0, 2));
        if (timeStrings[2].length() > 2 && timeStrings[2].substring(3).equals("PM") && hour < 12) {
            hour += 12;
        }
        return LocalTime.of(hour, minute, second);
    }

    /**
     * Helper function for ImportFile
     * Checks for Y
     * @param character The character it is checking
     * @return true if the character is Y, and false otherwise
     */
    private int yesOrNo(String character) {
        if (Objects.equals(character, "Y")) {
            return 1;
        } else if (Objects.equals(character, "N")) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * A helper function that increments the frequency hash tables
     * related to the given crime
     * Used for adding a crime to the local copy
     * @param crime The crime which is being added to the copy
     */
    private void incrementFreqs(CrimeRecord crime) {
        // Add the hashtable values
        if (primaryFreq.containsKey(crime.getPrimary())) {
            int oldfreq = primaryFreq.get(crime.getPrimary());
            primaryFreq.put(crime.getPrimary(), (oldfreq + 1));
        } else {
            primaryFreq.put(crime.getPrimary(), 1);
        }

        if (wardFreq.containsKey(crime.getWard())) {
            int oldfreq = wardFreq.get(crime.getWard());
            wardFreq.put(crime.getWard(), (oldfreq + 1));
        } else {
            wardFreq.put(crime.getWard(), 1);
        }

        if (beatFreq.containsKey(crime.getBeat())) {
            int oldfreq = beatFreq.get(crime.getBeat());
            beatFreq.put(crime.getBeat(), (oldfreq + 1));
        } else {
            beatFreq.put(crime.getBeat(), 1);
        }
    }

    /**
     * Decrements the frequency hash tables
     * related to the given crime
     * Used for deletion of a crime
     * Does nothing if the key is not in the table
     * @param crime The crime which is being removed from the copy
     */
    private void decrementFreqs(CrimeRecord crime) {
        if (primaryFreq.containsKey(crime.getPrimary())) {
            int oldfreq = primaryFreq.get(crime.getPrimary());
            primaryFreq.put(crime.getPrimary(), (oldfreq - 1));
        }

        if (wardFreq.containsKey(crime.getWard())) {
            int oldfreq = wardFreq.get(crime.getWard());
            wardFreq.put(crime.getWard(), (oldfreq - 1));
        }

        if (beatFreq.containsKey(crime.getBeat())) {
            int oldfreq = beatFreq.get(crime.getBeat());
            beatFreq.put(crime.getBeat(), (oldfreq - 1));
        }

    }

    /**
     * Adds a crime to the local copy
     * Does nothing if the crime is already in the local copy
     * @param crime The crime being added.
     */
    public void addRecord(CrimeRecord crime) {
        if (!(containedRecords.contains(crime.getCaseNum()))) {
            containedRecords.add(crime.getCaseNum());
            localCopy.add(crime);
            incrementFreqs(crime);
        }
    }

    /**
     * Method for checking if a duplicate case number exists
     * @param crime The crime that may or may not be in the copy
     * @return True if the crime/case number is in the copy, false otherwise
     */
    public boolean checkDuplicate(CrimeRecord crime) {
        return containedRecords.contains(crime.getCaseNum());
    }

    /**
     * Removes a crime object from the local copy.
     * Make sure you're using the actual crime object
     * as we haven't overridden the equals method of CrimeRecord
     *
     * Does nothing if the crime is not in the copy
     * Also removes the crime's case number from the containedRecords
     * and calls a helper function to decrement the frequency tables
     * @param crime The crime to be removed.
     */
    public void removeRecord(CrimeRecord crime) {
        if (crime != null) {
            localCopy.remove(crime);
            if (containedRecords.contains((crime.getCaseNum()))) {
                decrementFreqs(crime);
            }
            containedRecords.remove(crime.getCaseNum());
        }
    }

    /**
     * The function for editing a crime record in the local copy
     *
     * It checks that the given crime record exists and
     * if the case number is vacant (either if it is unchanged or
     * we don't already have a record with the new case number)
     *
     * If the crime record doesn't exist or the number is not vacant,
     * the function does nothing and returns false
     *
     * @param crime The crime being changed
     * @param caseNum The Case Number of the Crime
     * @param month The month the crime occurred (in int form)
     * @param date The day of the month the crime occurred (in int form)
     * @param year The year the crime occurred (in int form)
     * @param time The time the crime occurred
     * @param block The block of the crime
     * @param iucr The IUCR of the crime
     * @param primary The primary description of the crime
     * @param secondary The secondary, and more detailed, description of the crime
     * @param locdesc The description of the location
     * @param arrest Whether an arrest was made
     *               0 for false
     *               1 for true
     *               -1 for unknown
     * @param domestic Whether the crime was domestic
     *                 0 for false
     *                 1 for true
     *                 -1 for unknown
     * @param beat The beat of the crime
     * @param ward The ward of the crime
     * @param fbiCD The FBI case number of the crime
     * @param lat The latitude of the crime
     * @param lon The longitude of the crime
     * @return True if the edit was made, false if the case number was not vacant and
     * consequently, the edit was not made
     */
    public boolean changeRecord(CrimeRecord crime, String caseNum, int month, int date, int year,
                             LocalTime time, String block, String iucr, String primary,
                             String secondary, String locdesc, int arrest, int domestic,
                             int beat, int ward, String fbiCD, double lat, double lon) {

        // True if the new case number isn't being used, or if the case number is not going to be changed
        boolean willChange = !(containedRecords.contains(caseNum)) || (Objects.equals(crime.getCaseNum(), caseNum));

        if (willChange) {
            decrementFreqs(crime);
            CrimeRecord.changeRecord(crime, caseNum, month, date, year,
                    time, block, iucr, primary, secondary, locdesc, arrest, domestic,
                    beat, ward, fbiCD, lat, lon);
            incrementFreqs(crime);
        }

        return willChange;
    }

    /**
     * Exports the Crime Record entries of this manager
     * into a csv at the given filename.
     *
     * @param filename The name of the file that is being exported to
     * @throws IOException When an I/O error occurs when writing to the file
     * @throws IllegalArgumentException if the filename doesn't end with ".csv"
     */
    public void exportFile(String filename) throws IOException {
        if (!filename.endsWith(".csv")) {
            // It isn't a CSV file!
            throw new IllegalArgumentException("This filename doesn't end with .csv!");
        }
        // Open up the file for writing
        FileWriter csvWriter = new FileWriter(filename);
        // Write the first line of the CSV
        csvWriter.append("CASE#,DATE  OF OCCURRENCE,BLOCK, IUCR, " +
                "PRIMARY DESCRIPTION, SECONDARY DESCRIPTION, LOCATION DESCRIPTION," +
                "ARREST,DOMESTIC,BEAT,WARD,FBI CD,X COORDINATE,Y COORDINATE," +
                "LATITUDE,LONGITUDE,LOCATION\n");
        // Now loop through all the records and write their csv values
        for (CrimeRecord crime : localCopy) {
            csvWriter.append(crime.toCSV());
            //Also append a newline character
            csvWriter.append("\n");
        }
        // And then close the file
        csvWriter.flush();
        csvWriter.close();
    }

}
