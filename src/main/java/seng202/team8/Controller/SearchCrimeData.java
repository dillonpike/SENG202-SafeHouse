package seng202.team8.Controller;

import seng202.team8.Model.CrimeRecord;

import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchCrimeData {

    private ArrayList<CrimeRecord> crimeRecordData;

    public SearchCrimeData() {}

    /**
     * A constructor, that sets the ArrayList of the given CrimeRecord list.
     * @param crimeRecordData, the param gets the crime record data list
     */
    public SearchCrimeData(ArrayList<CrimeRecord> crimeRecordData) {
        this.crimeRecordData = crimeRecordData;
    }


    /**
     * The method returns the crime record data list
     * @return crimeRecordData, it returns the crime record data list
     */
    public ArrayList<CrimeRecord> getCrimeRecordData() {
        return crimeRecordData;
    }

    /**
     * The method sets the given crime data list to the new crime record list
     * @param crimeRecordData, the param gets crime record data list
     */
    public void setCrimeRecordData(ArrayList<CrimeRecord> crimeRecordData) {
        this.crimeRecordData = crimeRecordData;
    }


    /**
     * The method filters the crime record data list by checking that each crime date is within the
     * given two date. If the date is within the dates, then it adds to the new list, and at the
     * end it returns the list.
     * @param startDate, gets the start date chosen by the user
     * @param endDate, gets the end date chosen by the user
     * @return filterByDateList, a new list made based on the dates between start and end date included
     * @throws ParseException If fail to parse string that is going to be saved as a specific format.
     */
    public ArrayList<CrimeRecord> filterByDate(String startDate, String endDate) throws ParseException {
        ArrayList<CrimeRecord> filterByDateList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            String setDateAtIndex = crimeData.getDate()[1] + "/" +
                    crimeData.getDate()[0] + "/" +
                    crimeData.getDate()[2];

            Date dateAtIndex = new SimpleDateFormat("dd/MM/yyyy").parse(setDateAtIndex);
            Date startDateNum = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            Date endDateNum = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);

            // startDateNum <= dateAtIndex <= endDateNum
            if (!startDateNum.after(dateAtIndex) && !endDateNum.before(dateAtIndex)) {
                filterByDateList.add(crimeData);
            }
        }
        return filterByDateList;
    }


    /**
     * The method filters the crime record data list by checking that each crime description
     * matches the given crime description. If the description matches, then it adds to the
     * new list and therefore returns the list.
     * @param crimeDescription, gets the crime description of a location
     * @return filterByCrimeTypeList, the new list based on the crime description type
     */
    public ArrayList<CrimeRecord> filterByCrimeType(String crimeDescription) {
        ArrayList<CrimeRecord> filterByCrimeTypeList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getPrimary().equals(crimeDescription)) {
                filterByCrimeTypeList.add(crimeData);
            }
        }
        return filterByCrimeTypeList;
    }


    /**
     * The method filters the crime record data list by checking that each crime location
     * matches the given crime location. If the location matches, then it adds the crime
     * location to the new list and returns the list.
     * @param locationName, gets the name of the crime location
     * @return filterByCrimeLocationList, the new list based on the crime location
     */
    public ArrayList<CrimeRecord> filterByCrimeLocation(String locationName) {
        ArrayList<CrimeRecord> filterByCrimeLocationList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getLocDescription().equals(locationName)) {
                filterByCrimeLocationList.add(crimeData);
            }
        }
        return filterByCrimeLocationList;
    }


    /**
     * The method filters the crime record data list by checking that each crime beat number is
     * within the given two beat numbers. If the date is within the numbers, then it adds to the
     * new list, and returns the list.
     * @param startBeatNum, gets the start beat number chosen
     * @param endBeatNum, gets the end beat number chosen
     * @return filterByCrimeLocationBeat, the new list based on the beat numbers between start
     * and end beat numbers included
     */
    public ArrayList<CrimeRecord> filterByCrimeLocationBeat(int startBeatNum, int endBeatNum) {
        ArrayList<CrimeRecord> filterByCrimeLocationBeat = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getBeat() >= startBeatNum && crimeData.getBeat() <= endBeatNum) {
                filterByCrimeLocationBeat.add(crimeData);
            }
        }
        return filterByCrimeLocationBeat;
    }


    /**
     * The method filters the crime record data list by checking whether the arrest has
     * been made or not based on the given boolean. If the boolean matches, then the crime
     * data is added to the new list, and returns the list.
     * @param arrestMade, gets the boolean as true for 'Y' (Yes) and false for 'N' (No)
     * @return filterByArrestList, a new list based on the arrest has been made or not
     */
    public ArrayList<CrimeRecord> filterByArrest(boolean arrestMade) {
        ArrayList<CrimeRecord> filterByArrestList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getWasArrest() == arrestMade) {
                filterByArrestList.add(crimeData);
            }
        }
        return filterByArrestList;
    }


    /**
     * The method filters the crime record data list by checking whether there was any
     * domestic violence at the location, and compares with the given boolean. If the
     * boolean matches, then the crime data is added to the new list, and returns the list.
     * @param wasDomesticViolence, gets the boolean as true for 'Y' (Yes) or false for 'N' (No)
     * @return filterByDomesticViolenceList, a new list based on whether there was domestic
     * violence or not
     */
    public ArrayList<CrimeRecord> filterByDomesticViolence(boolean wasDomesticViolence) {
        ArrayList<CrimeRecord> filterByDomesticViolenceList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData)
            if (crimeData.getWasDomestic() == wasDomesticViolence) {
                filterByDomesticViolenceList.add(crimeData);
            }
        return filterByDomesticViolenceList;
    }

}

