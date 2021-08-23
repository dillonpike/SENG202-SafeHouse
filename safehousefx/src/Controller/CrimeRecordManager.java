package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import Model.CrimeRecord;

/**
 * A class that contains the local copy of the crime records
 */
public class CrimeRecordManager {

    private ArrayList<CrimeRecord> localCopy = new ArrayList<>();

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
     */
    public void importFile(String filename) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        String row;
        try {
            //Skip the first line since it's just header info
            csvReader.readLine();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                //Each data array should be 17 entries long
                CrimeRecord newCrime = new CrimeRecord();
                /*
                I'm going to manually add the entries
                so that we can do tweaking for missing entries later
                */
                newCrime.setCaseNum(data[0]);
                addDateAndTime(newCrime, data[1]);
                newCrime.setBlock(data[2]);
                newCrime.setIucr(data[3]);
                newCrime.setPrimary(data[4]);
                newCrime.setSecondary(data[5]);
                newCrime.setLocDescription(data[6]);
                newCrime.setWasArrest(yesOrNo(data[7]));
                newCrime.setWasDomestic(yesOrNo(data[8]));
                try {
                    newCrime.setBeat(Integer.parseInt(data[9]));
                    newCrime.setWard(Integer.parseInt(data[10]));
                }
                catch (NumberFormatException ex) {
                    //We haven't done error handling yet, so do nothing!
                }
                newCrime.setFbiCD(data[11]);
                try {
                    newCrime.setXCoord(Integer.parseInt(data[12]));
                    newCrime.setYCoord(Integer.parseInt(data[13]));
                    newCrime.setLongitude(Float.parseFloat(data[14]));
                    newCrime.setLatitude(Float.parseFloat(data[15]));
                }
                catch (NumberFormatException ex) {
                    //We haven't done error handling yet, so do nothing!
                } catch (ArrayIndexOutOfBoundsException ex) {
                    //These values don't seem to exist!
                    /*
                    Why this happens is that if the location data is missing
                    we get ',,,,,' which doesn't even get put into data
                     */
                }
                /*
                And that's all the information for the row!
                There was one final entry, data[16], the location
                but, as we derive that from lat and lon
                We don't need to access it

                As an aside, in future if we want to handle invalid data
                I think we will need to surround each entry with a try/catch block
                for the ones we want to handle
                - Olly
                 */

                localCopy.add(newCrime);



            }
            csvReader.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
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
    private boolean yesOrNo(String character) {
        return Objects.equals(character, "Y");
    }
}
