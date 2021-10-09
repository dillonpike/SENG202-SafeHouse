package seng202.team8.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Static class that stores the data of the application.
 */
public class DataManager {

    /**
     * The current CrimeRecordManager object in use
     * Effectively which dataset we are using
     */
    private static CrimeRecordManager currentDataset = new CrimeRecordManager();

    /**
     * An ArrayList of all the different Managers that the application uses
     * Since each CrimeRecordManager acts as a dataset
     * This allows us to store multiple datasets
     *
     * Make sure that we have our current dataset in here as well!
     */
    private static ArrayList<CrimeRecordManager> datasets = new ArrayList<>(List.of(currentDataset));

    /**
     * Start date for filtering records.
     */
    private static String startDate = "";

    /**
     * End date for filtering records.
     */
    private static String endDate = "";

    /**
     * Primary description for filtering records.
     */
    private static String primaryDesc = "";

    /**
     * Location for filtering records.
     */
    private static String location = "";

    /**
     * Start beat for filtering records.
     */
    private static String startBeat = "";

    /**
     * End beat for filtering records.
     */
    private static String endBeat = "";

    /**
     * Start ward for filtering records.
     */
    private static String startWard = "";

    /**
     * End ward for filtering records.
     */
    private static String endWard = "";

    /**
     * Arrest filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     */
    private static int arrest = 0;

    /**
     * Domestic filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     */
    private static int domestic = 0;

    /**
     * True if real time updating is turned on, otherwise false.
     */
    private static boolean realTime = true;

    /**
     * Number of markers to be mapped.
     */
    private static String markNumber = "";

    /**
     * gets which dataset is currently in use
     * @return the current dataset
     */
    public static CrimeRecordManager getCurrentDataset() {
        return currentDataset;
    }

    /**
     * Gets the list of datasets
     * @return An ArrayList of the datasets
     */
    public static ArrayList<CrimeRecordManager> getDatasets() { return datasets;}

    /**
     * Sets the current dataset
     * @param dataset The dataset being made into the current dataset
     */
    public static void setCurrentDataset(CrimeRecordManager dataset) {
        currentDataset = dataset;
    }

    /**
     * Adds the given dataset/CrimeRecordManager to the list of datasets
     * @param dataset The CrimeRecordManager object to be added
     */
    public static void addToDatasets(CrimeRecordManager dataset) {
        datasets.add(dataset);
    }

    /**
     * Returns the start date for filtering records.
     * @return start date
     */
    public static String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date for filtering records.
     * @param startDate start date
     */
    public static void setStartDate(String startDate) {
        DataManager.startDate = startDate;
    }

    /**
     * Returns the end date for filtering records.
     * @return end date
     */
    public static String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for filtering records.
     * @param endDate end date
     */
    public static void setEndDate(String endDate) {
        DataManager.endDate = endDate;
    }

    /**
     * Returns the primary description for filtering records.
     * @return primary description
     */
    public static String getPrimaryDesc() {
        return primaryDesc;
    }

    /**
     * Sets the primary description for filtering records.
     * @param primaryDesc primary description
     */
    public static void setPrimaryDesc(String primaryDesc) {
        DataManager.primaryDesc = primaryDesc;
    }

    /**
     * Returns the location for filtering records.
     * @return location
     */
    public static String getLocation() {
        return location;
    }

    /**
     * Sets the location for filtering records.
     * @param location location
     */
    public static void setLocation(String location) {
        DataManager.location = location;
    }

    /**
     * Returns the start beat for filtering records.
     * @return start beat
     */
    public static String getStartBeat() {
        return startBeat;
    }

    /**
     * Sets the start beat for filtering records.
     * @param startBeat start beat
     */
    public static void setStartBeat(String startBeat) {
        DataManager.startBeat = startBeat;
    }

    /**
     * Returns the end beat for filtering records.
     * @return end beat
     */
    public static String getEndBeat() {
        return endBeat;
    }

    /**
     * Sets the end beat for filtering records.
     * @param endBeat end beat
     */
    public static void setEndBeat(String endBeat) {
        DataManager.endBeat = endBeat;
    }

    /**
     * Returns the start ward for filtering records.
     * @return start ward
     */
    public static String getStartWard() {
        return startWard;
    }

    /**
     * Sets the start ward for filtering records.
     * @param startWard start ward
     */
    public static void setStartWard(String startWard) {
        DataManager.startWard = startWard;
    }

    /**
     * Returns the end ward for filtering records.
     * @return end ward
     */
    public static String getEndWard() {
        return endWard;
    }

    /**
     * Sets the end ward for filtering records.
     * @param endWard end ward
     */
    public static void setEndWard(String endWard) {
        DataManager.endWard = endWard;
    }

    /**
     * Returns the arrest filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     * @return arrest filtering option
     */
    public static int getArrest() {
        return arrest;
    }

    /**
     * Sets the arrest filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     * @param arrest arrest filtering option
     */
    public static void setArrest(int arrest) {
        DataManager.arrest = arrest;
    }

    /**
     * Returns the domestic filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     * @return domestic filtering option
     */
    public static int getDomestic() {
        return domestic;
    }

    /**
     * Sets the domestic filtering option.
     * 0: Don't filter
     * 1: Yes
     * 2: No
     * @param domestic domestic filtering option
     */
    public static void setDomestic(int domestic) {
        DataManager.domestic = domestic;
    }

    /**
     * Returns true if real time updating is turned on, otherwise false.
     * @return true if real time updating is turned on, otherwise false
     */
    public static boolean getRealTime() {
        return realTime;
    }

    /**
     * Sets the real time updating option.
     * @param realTime true to turn real time updating on, otherwise false
     */
    public static void setRealTime(boolean realTime) {
        DataManager.realTime = realTime;
    }

    /**
     * Returns the number of markers to be displayed on the map.
     * @return number of markers to be displayed on the map
     */
    public static String getMarkNumber() {
        return markNumber;
    }

    /**
     * Sets the number of markers to be displayed on the map.
     * @param markNumber number of markers to be displayed on the map
     */
    public static void setMarkNumber(String markNumber) {
        DataManager.markNumber = markNumber;
    }
}
