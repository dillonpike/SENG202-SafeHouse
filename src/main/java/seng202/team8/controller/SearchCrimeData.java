package seng202.team8.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import seng202.team8.model.CrimeRecord;

/**
 * A class of static methods that filters the various type of crimes, such as date of crime, crime primary
 * description, crime location, etc.
 * TODO add param javadocs for crimeRecordData
 */
public class SearchCrimeData {

    /**
     * Date format used for comparing dates.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Filters the crime record data list by checking that each crime date is within the
     * given two date. If the date is within the dates, then it adds to the new list, and at the
     * end it returns the list.
     * @param crimeRecordData crime record data list
     * @param startDate start date of valid date range
     * @param endDate end date of valid date range
     * @return a new list made based on the dates between start and end date included
     * @throws ParseException If fail to parse string that is going to be saved as a specific format.
     */
    public static ArrayList<CrimeRecord> filterByDate(ArrayList<CrimeRecord> crimeRecordData, String startDate,
                                                      String endDate) throws ParseException {

        Date startDateNum = dateFormat.parse(startDate);
        Date endDateNum = dateFormat.parse(endDate);

        ArrayList<CrimeRecord> filterByDateList = new ArrayList<>();
        for (CrimeRecord crimeData : crimeRecordData) {
            LocalDate date = crimeData.getDate();
            String setDateAtIndex = date.getDayOfMonth() + "/" +
                    date.getMonthValue() + "/" +
                    date.getYear();

            Date dateAtIndex = dateFormat.parse(setDateAtIndex);

            // startDateNum <= dateAtIndex <= endDateNum
            if (!startDateNum.after(dateAtIndex) && !endDateNum.before(dateAtIndex)) {
                filterByDateList.add(crimeData);
            }
        }
        return filterByDateList;
    }

    /**
     * Checks whether the given date follows the dd/MM/yyyy format used by filterByDate.
     * @param date date to be checked
     * @return true if valid, otherwise false
     */
    public static boolean isValidDate(String date) {
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Filters the crime record data list by checking that each crime description
     * starts with the given crime description (case-insensitive). If this is the case, then it adds to the
     * new list and returns the list.
     * @param crimeRecordData crime record data list
     * @param crimeDescription crime description of a location
     * @return the new list based on the crime description type
     */
    public static ArrayList<CrimeRecord> filterByPrimaryDesc(ArrayList<CrimeRecord> crimeRecordData, String crimeDescription) {
        ArrayList<CrimeRecord> filterByCrimeTypeList = new ArrayList<>();
        crimeDescription = crimeDescription.toLowerCase(); // Set to lowercase to be case-insensitive
        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getPrimary().toLowerCase().startsWith(crimeDescription)) {
                filterByCrimeTypeList.add(crimeData);
            }
        }
        return filterByCrimeTypeList;
    }

    /**
     * Filters the crime record data list by checking that each crime location
     * starts with the given crime location (case-insensitive). If this is the case, then it adds to the
     * new list and returns the list.
     * @param crimeRecordData crime record data list
     * @param locationName name of the valid crime location
     * @return the new list based on the crime location
     */
    public static ArrayList<CrimeRecord> filterByCrimeLocation(ArrayList<CrimeRecord> crimeRecordData, String locationName) {
        ArrayList<CrimeRecord> filterByCrimeLocationList = new ArrayList<>();
        locationName = locationName.toLowerCase(); // Set to lowercase to be case-insensitive
        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getLocDescription().toLowerCase().startsWith(locationName)) {
                filterByCrimeLocationList.add(crimeData);
            }
        }
        return filterByCrimeLocationList;
    }

    /**
     * Filters the crime record data list by checking that each crime beat number is
     * within the given two beat numbers. If the date is within the numbers, then it adds to the
     * new list, and returns the list.
     * @param crimeRecordData crime record data list
     * @param startBeatNum start beat number of valid beat range
     * @param endBeatNum end beat number of valid beat range
     * @return the new list based on the beat numbers between start
     * and end beat numbers included
     */
    public static ArrayList<CrimeRecord> filterByCrimeLocationBeat(ArrayList<CrimeRecord> crimeRecordData,
                                                                   int startBeatNum, int endBeatNum) {
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
     * @param crimeRecordData crime record data list
     * @param startWardNum start ward number of valid ward range
     * @param endWardNum end ward number of valid ward range
     * @return the new list based on the ward numbers between start
     * and end ward numbers included
     */
    public static ArrayList<CrimeRecord> filterByCrimeWard(ArrayList<CrimeRecord> crimeRecordData,
                                                           int startWardNum, int endWardNum) {
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
     * @param crimeRecordData valid crime record data list
     * @param arrestMade boolean as true for 'Y' (Yes) and false for 'N' (No)
     * @return a new list based on the arrest has been made or not
     */
    public static ArrayList<CrimeRecord> filterByArrest(ArrayList<CrimeRecord> crimeRecordData, boolean arrestMade) {
        ArrayList<CrimeRecord> filterByArrestList = new ArrayList<>();
        int arrestMadeInt = arrestMade ? 1 : 0;
        for (CrimeRecord crimeData : crimeRecordData) {
            if (crimeData.getWasArrestValue() == arrestMadeInt) {
                filterByArrestList.add(crimeData);
            }
        }
        return filterByArrestList;
    }

    /**
     * Filters the crime record data list by checking whether there was any
     * domestic violence at the location, and compares with the given boolean. If the
     * boolean matches, then the crime data is added to the new list, and returns the list.
     * @param crimeRecordData crime record data list
     * @param wasDomesticViolence gets the boolean as true for 'Y' (Yes) or false for 'N' (No)
     * @return a new list based on whether there was domestic
     * violence or not
     */
    public static ArrayList<CrimeRecord> filterByDomesticViolence(ArrayList<CrimeRecord> crimeRecordData, boolean wasDomesticViolence) {
        ArrayList<CrimeRecord> filterByDomesticViolenceList = new ArrayList<>();
        int wasDomesticViolenceInt = wasDomesticViolence ? 1 : 0;
        for (CrimeRecord crimeData : crimeRecordData)
            if (crimeData.getWasDomesticValue() == wasDomesticViolenceInt) {
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
     * @param crimeRecordData crime record data list
     * @return a set containing ranked data based on the ward areas
     */
    public static Set<Integer> rankByMostDangerousAreas(ArrayList<CrimeRecord> crimeRecordData) {
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

        return sortedRankedMap.keySet();
    }

    /**
     * Creates the hashmap of string and integers, where string is the key representing the
     * primary description and integer represents the value. Here the method runs a for loop
     * on crimeRecordData list and checks each time the key appears, the value is incremented
     * by 1. Next, the data is sorted in decreasing order, and then converts the key values
     * as sets and returns it.
     * @param crimeRecordData crime record data list
     * @return a set containing ranked data based on the crime primary
     * description type
     */
    public static Set<String> rankByCrimeTypeFrequency(ArrayList<CrimeRecord> crimeRecordData) {
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

        return sortedRankedMap.keySet();
    }

    /**
     * Creates the hashmap of two integers, where first integer is the key that represents the
     * Ward area and second integer represents the value. Here the method runs a for loop on
     * crimeRecordData list and checks each time the ward appears, the value is incremented by
     * 1. Next, the data is sorted in decreasing order, and then converts the keys as
     * a list. After this, it creates two lists, one is high crime rates list (contains top 10 ward
     * by frequency) and the other is low crime rates list (contains bottom 10 ward by frequency).
     * low crime rates
     * @param crimeRecordData crime data list
     * @return highAndLowCrimeRatesList, a list[] containing high crime rates ward list at
     * index 0, and low crime rates ward list at index 1
     */
    public static List<List<Integer>> wardByHighAndLowCrimeRates(ArrayList<CrimeRecord> crimeRecordData) {
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
        List<Integer> highCrimeRatesWardList  = new ArrayList<>();
        List<Integer> lowCrimeRatesWardList  = new ArrayList<>();

        // Checks if there are 10 or more crime data record
        if (rankedKeyList.size() < 10) {
            highCrimeRatesWardList = rankedKeyList;

            // Creating a new copy, so it doesn't affect the original list
            lowCrimeRatesWardList = new ArrayList<>(rankedKeyList);
            Collections.reverse(lowCrimeRatesWardList);
        } else {
            highCrimeRatesWardList = rankedKeyList.subList(0, 10);
            lowCrimeRatesWardList = rankedKeyList.subList(rankedKeyList.size()-10, rankedKeyList.size());
        }

        // Creates a list of list that contains two list of high/low crime rates
        List<List<Integer>> highAndLowCrimeRatesList = new ArrayList<>();
        highAndLowCrimeRatesList.add(highCrimeRatesWardList);
        highAndLowCrimeRatesList.add(lowCrimeRatesWardList);
        return highAndLowCrimeRatesList;
    }
}

