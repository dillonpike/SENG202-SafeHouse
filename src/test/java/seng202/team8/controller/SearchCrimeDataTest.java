package seng202.team8.controller;

import junit.framework.TestCase;
import seng202.team8.model.CrimeRecord;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A test class that checks SearchCrimeData class methods, whether their input and output are
 * working correctly
 */
public class SearchCrimeDataTest extends TestCase {

    /**
     * Testing filter by date method. First, it specifies the start and end date, which is passed to the method
     * to get the sorted crime data list. At last, it checks the expected list with actual list.
     */
    public void testFilterByDate() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting date range for testing and running filter by date method, where setting it to an arraylist
        String startDate = "05/04/2021";
        String endDate = "09/10/2021";
        ArrayList<CrimeRecord> actualList = new ArrayList<>();
        try {
            actualList = SearchCrimeData.filterByDate(crimeRecordData, startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(0));
        expectedList.add(crimeRecordData.get(1));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by primary crime description method. First, it creates the primary description string,
     * which is passed to the method to get the filtered data list by the description. Then, it compares and
     * checks this list to the expected list.
     */
    public void testFilterByPrimaryDesc() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting the crime primary description, and getting the crime data list where it matches the primary
        // description
        String crimeDescription = "THEFT";
        ArrayList<CrimeRecord> actualList =  SearchCrimeData.filterByPrimaryDesc(crimeRecordData, crimeDescription);

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(0));
        expectedList.add(crimeRecordData.get(2));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by crime location description method. First, it creates the location description string,
     * which is passed to the method to get the filtered data list by the description. Then, it compares and
     * checks this list to the expected list.
     */
    public void testFilterByCrimeLocation() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting the crime location, and getting the crime data list where it matches the location description
        String locationDescription = "APARTMENT";
        ArrayList<CrimeRecord> actualList = SearchCrimeData.filterByCrimeLocation(crimeRecordData, locationDescription);

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(1));
        expectedList.add(crimeRecordData.get(2));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by crime beat method. First, it creates the start and end beat number, which is
     * passed to the method to get the filtered data list by beat. Then, it compares and checks this list to the
     * expected list.
     */
    public void testFilterByCrimeLocationBeat() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting the start and end beat num range, and getting the crime data list within that beat range
        int startBeatNum = 300;
        int endBeatNum = 900;
        ArrayList<CrimeRecord> actualList = SearchCrimeData.filterByCrimeLocationBeat(crimeRecordData, startBeatNum,
                endBeatNum);

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(0));
        expectedList.add(crimeRecordData.get(1));
        expectedList.add(crimeRecordData.get(2));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by crime ward method. First, it creates the start and end ward number ranges, which is
     * passed to the method to get the filtered data list by ward. Then, it compares and checks this list to the
     * expected list.
     */
    public void testFilterByCrimeWard() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting the start and end ward num range, and getting the crime data list within that ward range
        int startWardNum = 30;
        int endWardNum = 40;
        ArrayList<CrimeRecord> actualList = SearchCrimeData.filterByCrimeWard(crimeRecordData, startWardNum,
                endWardNum);


        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(3));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by crime arrest method. First, it passes true to the method to get the filtered data list
     * by arrest made. Then, it compares and checks this list to the expected list.
     */
    public void testFilterByArrest() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting arrest made is true, and getting the crime data list where arrest has been made
        ArrayList<CrimeRecord> actualList = SearchCrimeData.filterByArrest(crimeRecordData, true);

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(0));
        expectedList.add(crimeRecordData.get(3));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the filter by domestic violence method. First, it passes true to the method to get the filtered data
     * list by domestic violence. Then, it compares and checks this list to the expected list.
     */
    public void testFilterByDomesticViolence() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Setting domestic violence to true, and getting the crime data list where there has been domestic violence
        ArrayList<CrimeRecord> actualList = SearchCrimeData.filterByDomesticViolence(crimeRecordData, true);

        // Expected data list to be returned
        ArrayList<CrimeRecord> expectedList = new ArrayList<>();
        expectedList.add(crimeRecordData.get(2));

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }


    /**
     * Testing the rank by most dangerous areas method by ward. First, it passes crime data list to the method.
     * Then, it compares and checks this list to the expected list by ward frequency.
     */
    public void testRankByMostDangerousAreas() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Getting the set of most dangerous areas by ward
        Set<Integer> actualList = SearchCrimeData.rankByMostDangerousAreas(crimeRecordData);

        // Expected data list of most dangerous areas to be returned
        Set<Integer> expectedList = new HashSet<>();
        expectedList.add(crimeRecordData.get(0).getWard());
        expectedList.add(crimeRecordData.get(1).getWard());
        expectedList.add(crimeRecordData.get(3).getWard());

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the rank by most dangerous areas method by primary description. First, it passes crime data list to
     * the method. Then, it compares and checks this list to the expected list by primary description frequency.
     */
    public void testRankByCrimeTypeFrequency() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Getting the set of most dangerous areas by primary description
        Set<String> actualList = SearchCrimeData.rankByCrimeTypeFrequency(crimeRecordData);

        // Expected data list of most dangerous areas to be returned
        Set<String> expectedList = new HashSet<>();
        expectedList.add(crimeRecordData.get(0).getPrimary());
        expectedList.add(crimeRecordData.get(1).getPrimary());
        expectedList.add(crimeRecordData.get(3).getPrimary());

        // Checking whether the expected list matches the actual list
        assertEquals(expectedList, actualList);
    }

    /**
     * Testing the rank by high and low crime rates method by ward. First, it passes crime data list to the method.
     * Then, it compares and checks, high and low crime rates list to the two expected list by ward frequency.
     */
    public void testWardByHighAndLowCrimeRates() {
        // The dummy crime data list
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        // Getting the list of list where at index 0, it contains high crime rates list. Next, at index 1,
        // it contains the low crime rates list
        List<List<Integer>> actualList = SearchCrimeData.wardByHighAndLowCrimeRates(crimeRecordData);

        // Expected high crime rates data list
        List<Integer> expectedHighRatesList = new ArrayList<>();
        expectedHighRatesList.add(crimeRecordData.get(0).getWard());
        expectedHighRatesList.add(crimeRecordData.get(1).getWard());
        expectedHighRatesList.add(crimeRecordData.get(3).getWard());

        // Expected low crime rates data list
        List<Integer> expectedLowRatesList = new ArrayList<>();
        expectedLowRatesList.add(crimeRecordData.get(3).getWard());
        expectedLowRatesList.add(crimeRecordData.get(1).getWard());
        expectedLowRatesList.add(crimeRecordData.get(0).getWard());

        // Checking whether the expected list matches the actual list
        assertEquals(expectedHighRatesList, actualList.get(0));
        assertEquals(expectedLowRatesList, actualList.get(1));
    }


    /**
     * For testing the methods in SearchCrimeData class
     * @return crimeRecordData, a crime record data list of CrimeRecord
     */
    public ArrayList<CrimeRecord> getCrimeDataForTesting() {
        ArrayList<CrimeRecord> crimeRecordData = new ArrayList<>();
        crimeRecordData.add(new CrimeRecord("JE266628", 8, 26, 2021, LocalTime.of(9,30),
                "080XX S DREXEL AVE", "0820", "THEFT", "$500 AND UNDER", "STREET", 1, 0,
                631, 7, "06", 41.748486365, -87.602675062));
        crimeRecordData.add(new CrimeRecord("JE266473",6, 15, 2021, LocalTime.of(7,47),
                "062XX S MORGAN ST","0110","HOMICIDE","FIRST DEGREE MURDER","APARTMENT",0,0,
                712,16,"01A",41.780850996,-87.649674221));
        crimeRecordData.add(new CrimeRecord("JE163990", 11, 23, 2020, LocalTime.of(3,5),
                "073XX S SOUTH SHORE DR", "4386", "THEFT", "$500 AND UNDER",
                "APARTMENT", 0, 1, 334, 7, "6", 41.748486365, -87.602675062));
        crimeRecordData.add(new CrimeRecord("JE266959",2, 15, 2021, LocalTime.of(1,30), "018XX N DAMEN AVE", "0460",
                "BATTERY", "SIMPLE", "PARK PROPERTY", 1, 0, 1434, 32,"08B",41.914562993,-87.677553434));
        return crimeRecordData;
    }

}