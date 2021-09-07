package seng202.team8.controller;

import junit.framework.TestCase;
import seng202.team8.model.CrimeRecord;

import java.util.ArrayList;

/**
 * A test class that checks SearchCrimeData class methods, whether their input and output are
 * working correctly
 */
public class SearchCrimeDataTest extends TestCase {

    /**
     * A test case where the test checks whether the given data returns the
     * correct data or not
     */
    public void testGetCrimeRecordData() {
//        ArrayList<CrimeRecord> crimeRecordData = new ArrayList<CrimeRecord>();
//        crimeRecordData.add(new CrimeRecord());
//        crimeRecordData.add(new CrimeRecord());
//
//        SearchCrimeData filterData = new SearchCrimeData(crimeRecordData);
//
//        assertEquals(crimeRecordData, filterData.getCrimeRecordData());

    }

    /**
     * A test, where it checks whether the given set crime data returns the correct data
     */
    public void testSetCrimeRecordData() {
//        ArrayList<CrimeRecord> crimeRecordData = new ArrayList<CrimeRecord>();
//        crimeRecordData.add(new CrimeRecord());
//        crimeRecordData.add(new CrimeRecord());
//
//        SearchCrimeData filterData = new SearchCrimeData();
//        filterData.setCrimeRecordData(crimeRecordData);
//
//        assertEquals(crimeRecordData, filterData.getCrimeRecordData());
    }

    /**
     *
     */
    public void testFilterByDate() {

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
        crimeRecordData.add(new CrimeRecord("JE266568",5, 15, 2021, "02:45:00 AM", "055XX N MC VICKER AVE", "0810", "THEFT", "OVER $500",
                "STREET",0,0,1622,45,"06", 41.867038835,-87.656992959));
        crimeRecordData.add(new CrimeRecord("JE267494", 6, 15, 2021, "11:04:00 PM","052XX S ARTESIAN AVE","0110","HOMICIDE",
                "FIRST DEGREE MURDER","STREET",0,0,923,7,"01A",41.798854705,-87.685390881));
        crimeRecordData.add(new CrimeRecord("JE267206",6, 15, 2021, "05:20:00 PM","048XX N PULASKI RD","0460","BATTERY",
                "SIMPLE","PARKING LOT / GARAGE (NON RESIDENTIAL)",0,0,1712,32,"08B",41.969090767,-87.728052386));
        return crimeRecordData;
    }
}