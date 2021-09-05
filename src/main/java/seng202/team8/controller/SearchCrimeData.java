package seng202.team8.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import seng202.team8.model.CrimeRecord;

/**
 * A class that contains methods to filter the crime data list based on the command given
 */
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
     * Filters the crime record data list by checking that each crime date is within the
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
     * Filters the crime record data list by checking that each crime description
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
     * Filters the crime record data list by checking that each crime beat number is
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
     * Filters the crime record data list by checking that each crime ward number is
     * within the given two ward numbers. If the ward is within the numbers, then it adds to the
     * new list, and returns the list.
     * @param startWardNum, gets the start ward number chosen
     * @param endWardNum, gets the end ward number chosen
     * @return filterByCrimeWard, the new list based on the ward numbers between start
     * and end ward numbers included
     */
    public ArrayList<CrimeRecord> filterByCrimeWard(int startWardNum, int endWardNum) {
        ArrayList<CrimeRecord> filterByCrimeWard = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getWard() >= startWardNum && crimeData.getWard() <= endWardNum) {
                filterByCrimeWard.add(crimeData);
            }
        }
        return filterByCrimeWard;
    }


    /**
     * Filters the crime record data list by checking whether the arrest has
     * been made or not based on the given boolean. If the boolean matches, then the crime
     * data is added to the new list, and returns the list.
     * @param arrestMade, gets the boolean as true for 'Y' (Yes) and false for 'N' (No)
     * @return filterByArrestList, a new list based on the arrest has been made or not
     */
    public ArrayList<CrimeRecord> filterByArrest(boolean arrestMade) {
        ArrayList<CrimeRecord> filterByArrestList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getWasArrest() == 1) {
                filterByArrestList.add(crimeData);
            }
        }
        return filterByArrestList;
    }


    /**
     * Filters the crime record data list by checking whether there was any
     * domestic violence at the location, and compares with the given boolean. If the
     * boolean matches, then the crime data is added to the new list, and returns the list.
     * @param wasDomesticViolence, gets the boolean as true for 'Y' (Yes) or false for 'N' (No)
     * @return filterByDomesticViolenceList, a new list based on whether there was domestic
     * violence or not
     */
    public ArrayList<CrimeRecord> filterByDomesticViolence(boolean wasDomesticViolence) {
        ArrayList<CrimeRecord> filterByDomesticViolenceList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData)
            if (crimeData.getWasDomestic() == 1) {
                filterByDomesticViolenceList.add(crimeData);
            }
        return filterByDomesticViolenceList;
    }

    /**
     * Creates the hashmap of string and integers, where string represents the key and integer
     * represents the value. Here the method runs a for loop on crimeRecordData list and checks
     * each time the key appears, the value is incremented by 1. Next, the data is sorted in
     * decreasing order, and then converts the key values as sets and returns it.
     * @return rankedKeySet, a set containing ranked data based on the crime primary
     * description type
     */
    public Set rankByCrimeTypeFrequency() {
        HashMap<String, Integer> rankByCrimeTypeFrequencyMap = new HashMap<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (rankByCrimeTypeFrequencyMap.size() == 0) {
                rankByCrimeTypeFrequencyMap.put(crimeData.getPrimary(), 1);
            } else if (!rankByCrimeTypeFrequencyMap.containsKey(crimeData.getPrimary())) {
                rankByCrimeTypeFrequencyMap.put(crimeData.getPrimary(), 1);
            } else {
                int count = rankByCrimeTypeFrequencyMap.get(crimeData.getPrimary());
                rankByCrimeTypeFrequencyMap.put(crimeData.getPrimary(), count + 1);
            }
        }

        HashMap<String, Integer> sortedRankedMap = rankByCrimeTypeFrequencyMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        Set rankedKeySet = sortedRankedMap.keySet();
        return rankedKeySet;
    }


}

