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

    /**
     * List of crime records.
     */
    private static ArrayList<CrimeRecord> crimeRecordData;

    public SearchCrimeData() {}

    /**
     * A constructor, that sets the ArrayList of the given CrimeRecord list.
     * @param crimeRecordData, the param gets the crime record data list
     */
    public SearchCrimeData(ArrayList<CrimeRecord> crimeRecordData) {
        SearchCrimeData.crimeRecordData = crimeRecordData;
    }


    /**
     * Returns the crime record data list
     * @return crimeRecordData, it returns the crime record data list
     */
    public static ArrayList<CrimeRecord> getCrimeRecordData() {
        return crimeRecordData;
    }

    /**
     * Sets the given crime data list to the new crime record list
     * @param crimeRecordData, the param gets crime record data list
     */
    public void setCrimeRecordData(ArrayList<CrimeRecord> crimeRecordData) {
        SearchCrimeData.crimeRecordData = crimeRecordData;
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
    public static ArrayList<CrimeRecord> filterByDate(String startDate, String endDate) throws ParseException {
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
    public static ArrayList<CrimeRecord> filterByCrimeType(String crimeDescription) {
        ArrayList<CrimeRecord> filterByCrimeTypeList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getPrimary().equals(crimeDescription)) {
                filterByCrimeTypeList.add(crimeData);
            }
        }
        return filterByCrimeTypeList;
    }


    /**
     * Filters the crime record data list by checking that each crime location
     * matches the given crime location. If the location matches, then it adds the crime
     * location to the new list and returns the list.
     * @param locationName, gets the name of the crime location
     * @return filterByCrimeLocationList, the new list based on the crime location
     */
    public static ArrayList<CrimeRecord> filterByCrimeLocation(String locationName) {
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
    public static ArrayList<CrimeRecord> filterByCrimeLocationBeat(int startBeatNum, int endBeatNum) {
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
    public static ArrayList<CrimeRecord> filterByCrimeWard(int startWardNum, int endWardNum) {
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
    public static ArrayList<CrimeRecord> filterByArrest(boolean arrestMade) {
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
    public static ArrayList<CrimeRecord> filterByDomesticViolence(boolean wasDomesticViolence) {
        ArrayList<CrimeRecord> filterByDomesticViolenceList = new ArrayList<>();

        for (CrimeRecord crimeData : crimeRecordData)
            if (crimeData.getWasDomestic() == 1) {
                filterByDomesticViolenceList.add(crimeData);
            }
        return filterByDomesticViolenceList;
    }

    /**
     * Creates the hashmap of two integers, where first integer is the key that represents the
     * Ward area and second integer represents the value. Here the method runs a for loop on
     * crimeRecordData list and checks each time the ward appears, the value is incremented by
     * 1. Next, the data is sorted in decreasing order, and then converts the key values as
     * sets and returns it.
     * @return rankedKeySet, a set containing ranked data based on the ward areas
     */
    public static Set rankByMostDangerousAreas() {
        HashMap<Integer, Integer> rankByMostDangerousAreasMap = new HashMap<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (rankByMostDangerousAreasMap.size() == 0) {
                rankByMostDangerousAreasMap.put(crimeData.getWard(), 1);
            } else if (!rankByMostDangerousAreasMap.containsKey(crimeData.getWard())) {
                rankByMostDangerousAreasMap.put(crimeData.getWard(), 1);
            } else {
                int count = rankByMostDangerousAreasMap.get(crimeData.getWard());
                rankByMostDangerousAreasMap.put(crimeData.getWard(), count + 1);
            }
        }

        HashMap<Integer, Integer> sortedRankedMap = rankByMostDangerousAreasMap.entrySet().stream()
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

    /**
     * Creates the hashmap of string and integers, where string is the key representing the
     * primary description and integer represents the value. Here the method runs a for loop
     * on crimeRecordData list and checks each time the key appears, the value is incremented
     * by 1. Next, the data is sorted in decreasing order, and then converts the key values
     * as sets and returns it.
     * @return rankedKeySet, a set containing ranked data based on the crime primary
     * description type
     */
    public static Set rankByCrimeTypeFrequency() {
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

    /**
     * Creates the hashmap of two integers, where first integer is the key that represents the
     * Ward area and second integer represents the value. Here the method runs a for loop on
     * crimeRecordData list and checks each time the ward appears, the value is incremented by
     * 1. Next, the data is sorted in decreasing order, and then converts the keys as
     * a list. After this, it creates two lists, one is high crime rates list (contains top 10 ward
     * by frequency) and the other is low crime rates list (contains bottom 10 ward by frequency).
     * low crime rates
     * @return highAndLowCrimeRatesList, a list[] containing high crime rates ward list at
     * index 0, and low crime rates ward list at index 1
     */
    public static List[] wardByHighAndLowCrimeRates() {
        HashMap<Integer, Integer> rankByMostDangerousAreasMap = new HashMap<>();

        for (CrimeRecord crimeData : crimeRecordData) {
            if (rankByMostDangerousAreasMap.size() == 0) {
                rankByMostDangerousAreasMap.put(crimeData.getWard(), 1);
            } else if (!rankByMostDangerousAreasMap.containsKey(crimeData.getWard())) {
                rankByMostDangerousAreasMap.put(crimeData.getWard(), 1);
            } else {
                int count = rankByMostDangerousAreasMap.get(crimeData.getWard());
                rankByMostDangerousAreasMap.put(crimeData.getWard(), count + 1);
            }
        }

        HashMap<Integer, Integer> sortedRankedMap = rankByMostDangerousAreasMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        ArrayList<Integer> rankedKeyList = new ArrayList<>(sortedRankedMap.keySet());
        List<Integer> highCrimeRatesWardList = rankedKeyList.subList(0, 10);
        List<Integer> lowCrimeRatesWardList = rankedKeyList.subList(rankedKeyList.size()-10, rankedKeyList.size());
        List[] highAndLowCrimeRatesList = new List[] {highCrimeRatesWardList, lowCrimeRatesWardList};

        return highAndLowCrimeRatesList;
    }

    public static void main(String[] args) {
        ArrayList<CrimeRecord> crimeData = new ArrayList<CrimeRecord>();
        crimeData.add(new CrimeRecord("JE163990", 11, 23, 2020, "03:05:00 PM",
                "073XX S SOUTH SHORE DR", "4386", "THEFT", "$500 AND UNDER",
                "APARTMENT", 0, 0, 334, 7, "6", 41.748486365, -87.602675062));
        crimeData.add(new CrimeRecord("JE266959",6, 15, 2021, "01:30:00 PM", "018XX N DAMEN AVE", "0460",
                "BATTERY", "SIMPLE", "PARK PROPERTY", 0, 0, 1434, 32,"08B",41.914562993,-87.677553434));
        crimeData.add(new CrimeRecord("JE266568",6, 15, 2021, "01:30:00 AM", "055XX N MC VICKER AVE", "0810", "THEFT", "OVER $500",
                "STREET",0,0,1622,45,"06", 41.982167364,-87.779228689));
        crimeData.add(new CrimeRecord("JE267494", 6, 15, 2021, "11:04:00 PM","052XX S ARTESIAN AVE","0110","HOMICIDE",
                "FIRST DEGREE MURDER","STREET",0,0,923,7,"01A",41.798854705,-87.685390881));
        crimeData.add(new CrimeRecord("JE267206",6, 15, 2021, "05:20:00 PM","048XX N PULASKI RD","0460","BATTERY",
                "SIMPLE","PARKING LOT / GARAGE (NON RESIDENTIAL)",0,0,1712,32,"08B",41.969090767,-87.728052386));

        SearchCrimeData sr = new SearchCrimeData(crimeData);
        Set data = sr.rankByMostDangerousAreas();
        System.out.println(data);
    }


}

