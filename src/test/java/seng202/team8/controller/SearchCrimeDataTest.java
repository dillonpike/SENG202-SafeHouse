package seng202.team8.controller;

import junit.framework.TestCase;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A test class that checks SearchCrimeData class methods, whether their input and output are
 * working correctly
 */
public class SearchCrimeDataTest extends TestCase {

    /**
     *
     */
    public void testFilterByDate() {
        ArrayList<CrimeRecord> crimeRecordData = getCrimeDataForTesting();

        SearchCrimeData filterData = new SearchCrimeData();

        //assertEquals();

    }

    public void testFilterByCrimeType() {

    }

    public void testFilterByCrimeLocation() {

    }

    public void testFilterByCrimeLocationBeat() {

    }

    public void testFilterByArrest() {

    }

    public void testFilterByDomesticViolence() {

    }

    /**
     * For testing the method from SearchCrimeData class
     * @return crimeRecordData, a crime record data list of CrimeRecord
     */
    public ArrayList<CrimeRecord> getCrimeDataForTesting() {
        ArrayList<CrimeRecord> crimeRecordData = new ArrayList<>();
        crimeRecordData.add(new CrimeRecord("JE266628", 6, 15, 2021, "09:30:00 AM",
                "080XX S DREXEL AVE", "0820", "THEFT", "$500 AND UNDER", "STREET", 0, 0,
                631, 8, "06", 41.748486365, -87.602675062));
        crimeRecordData.add(new CrimeRecord("JE266473",6, 15, 2021, "07:47:00 AM",
                "062XX S MORGAN ST","0110","HOMICIDE","FIRST DEGREE MURDER","APARTMENT",0,0,
                712,16,"01A",41.780850996,-87.649674221));
        crimeRecordData.add(new CrimeRecord("JE163990", 11, 23, 2020, "03:05:00 PM",
                "073XX S SOUTH SHORE DR", "4386", "THEFT", "$500 AND UNDER",
                "APARTMENT", 0, 0, 334, 7, "6", 41.748486365, -87.602675062));
        crimeRecordData.add(new CrimeRecord("JE266959",6, 15, 2021, "01:30:00 PM", "018XX N DAMEN AVE", "0460",
                "BATTERY", "SIMPLE", "PARK PROPERTY", 0, 0, 1434, 32,"08B",41.914562993,-87.677553434));
        return crimeRecordData;
    }
}