package seng202.team8.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import seng202.team8.model.CrimeRecord;

/**
 * A class that contains the local copy of the crime records
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
     * Gets the local copy of our crime records
     * @return The local copy of CrimeRecord objects, in the form of an ArrayList
     */
    public ArrayList<CrimeRecord> getLocalCopy() {
        return localCopy;
    }

    /**
     * Goes through the file and adds each line to the localCopy as a CrimeRecord object
     * @param filename The name/filepath of the file to be imported
     * @throws FileNotFoundException If the filename cannot be found, this is thrown.
     * This should prompt the user to try again, so it will not be handled in this method
     * @return Returns an ArrayList of all lines that contain errors/could not be imported
     */
    public ArrayList<Integer> importFile(String filename) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        String row;
        ArrayList<Integer> linesWithErrors = new ArrayList<>();
        int counter = 1;
        try {
            //Skip the first line since it's just header info
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",", -1);
                //Each data array should be 17 entries long
                CrimeRecord newCrime = new CrimeRecord();
                /*
                Try to add the essential stuff to the record
                */
                if (!(addEssentials(data, newCrime))) {
                    /*If we couldn't, skip this iteration
                    and add this line to the lines with errors
                     */
                    linesWithErrors.add(counter);
                    continue;
                }
                //Check if the crime is already in there.
                if (containedRecords.contains(newCrime.getCaseNum())) {
                    //The crime is already in here, so don't add it
                    continue;
                } else {
                    containedRecords.add(newCrime.getCaseNum());
                }
                //Add the non-essential stuff
                addSkipables(data, newCrime);

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
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
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
            newCrime.setIucr(data[3]);
            newCrime.setPrimary(data[4]);
            newCrime.setLongitude(Float.parseFloat(data[14]));
            newCrime.setLatitude(Float.parseFloat(data[15]));
            return true;
        } catch (Exception ex) {
            return false;
        }
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
        } catch (NumberFormatException ex) {
            //Do nothing and leave the field(s) empty.
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
            //Leave the ward with no value
        }
        try {
            newCrime.setBeat(Integer.parseInt(data[9]));
        } catch (NumberFormatException ex) {
            //Leave the beat with no value
        }
        newCrime.setFbiCD(data[11]);
        try {
            newCrime.setXCoord(Integer.parseInt(data[12]));
            newCrime.setYCoord(Integer.parseInt(data[13]));
        }
        catch (NumberFormatException ex) {
            //Do nothing and leave the two fields blank.
        } catch (ArrayIndexOutOfBoundsException ex) {
            //These values don't seem to exist!
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
     * @param Date The Date and Time string (should be data[1]) containing the date and time
     */
    private void addDateAndTime(CrimeRecord crime, String Date) {
        //Should split entries into [0] Date, [1] Time [2] AM/PM
        String[] dateAndTime = Date.split(" ");
        String[] dateNums = dateAndTime[0].split("/");
        int month = Integer.parseInt(dateNums[0]);
        int day = Integer.parseInt(dateNums[1]);
        int year = Integer.parseInt(dateNums[2]);
        crime.setDate(month, day, year);
        crime.setTimeOfCrime((dateAndTime[1] + " " + dateAndTime[2]));
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
     * Adds a crime to the local copy
     * Does NOT check for duplicates.
     * @param crime The crime being added.
     */
    public void addRecord(CrimeRecord crime) {
        localCopy.add(crime);
    }

    /**
     * Removes a crime object from the local copy.
     * Make sure you're using the actual crime object
     * as we haven't overridden the equals method of CrimeRecord
     *
     * Does nothing if the crime is not in the copy
     * @param crime The crime to be removed.
     */
    public void removeRecord(CrimeRecord crime) {
        localCopy.remove(crime);
    }


}
