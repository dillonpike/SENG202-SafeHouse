package seng202.team8.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Static class that stores the data of the application
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
}
