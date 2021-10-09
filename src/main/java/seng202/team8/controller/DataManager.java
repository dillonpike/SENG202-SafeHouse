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
     *
     */
    private static String startDate = "";

    /**
     *
     */
    private static String endDate = "";


    private static String primaryDesc = "";

    private static String location = "";

    private static String startBeat = "";

    private static String endBeat = "";

    private static String startWard = "";

    private static String endWard = "";

    private static int arrest = 0;

    private static int domestic = 0;

    private static boolean realTime = true;

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

    public static String getStartDate() {
        return startDate;
    }

    public static void setStartDate(String startDate) {
        DataManager.startDate = startDate;
    }

    public static String getEndDate() {
        return endDate;
    }

    public static void setEndDate(String endDate) {
        DataManager.endDate = endDate;
    }

    public static String getPrimaryDesc() {
        return primaryDesc;
    }

    public static void setPrimaryDesc(String primaryDesc) {
        DataManager.primaryDesc = primaryDesc;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        DataManager.location = location;
    }

    public static String getStartBeat() {
        return startBeat;
    }

    public static void setStartBeat(String startBeat) {
        DataManager.startBeat = startBeat;
    }

    public static String getEndBeat() {
        return endBeat;
    }

    public static void setEndBeat(String endBeat) {
        DataManager.endBeat = endBeat;
    }

    public static String getStartWard() {
        return startWard;
    }

    public static void setStartWard(String startWard) {
        DataManager.startWard = startWard;
    }

    public static String getEndWard() {
        return endWard;
    }

    public static void setEndWard(String endWard) {
        DataManager.endWard = endWard;
    }

    public static int getArrest() {
        return arrest;
    }

    public static void setArrest(int arrest) {
        DataManager.arrest = arrest;
    }

    public static int getDomestic() {
        return domestic;
    }

    public static void setDomestic(int domestic) {
        DataManager.domestic = domestic;
    }

    public static boolean getRealTime() {
        return realTime;
    }

    public static void setRealTime(boolean realTime) {
        DataManager.realTime = realTime;
    }

    public static String getMarkNumber() {
        return markNumber;
    }

    public static void setMarkNumber(String markNumber) {
        DataManager.markNumber = markNumber;
    }
}
