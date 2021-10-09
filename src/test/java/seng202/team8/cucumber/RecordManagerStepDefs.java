package seng202.team8.cucumber;

import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Step definitions for the manipulating a CrimeRecordManager
 * This includes operations such as
 * Importing, Exporting, and manual addition/deletion/editing of records
 */
public class RecordManagerStepDefs {

    /**
     * A manager/dataset, which we will use for importing.
     */
    private CrimeRecordManager manager;

    /**
     * Step definition for ensuring our manager exists and is empty.
     */
    @Given("I have an empty dataset")
    public void iHaveAnEmptyDataset() {
        manager = new CrimeRecordManager();
    }

    /**
     * Step definition for importing a file
     * This imports the given file into our manager.
     * @param filename The name/filepath of the file to be imported.
     */
    @When("I import {string}")
    public void iImport(String filename) {
        try {
            manager.importFile(filename);
        } catch (FileNotFoundException ex) {
            System.out.println("The file wasn't found!");
            ex.printStackTrace();
        } catch (IOException | CsvValidationException ex) {
            System.out.println("The file couldn't be imported!");
            ex.printStackTrace();
        }
    }

    /**
     * A two-in-one step definition of creating a dataset and loading stuff into it
     * Mainly for tests on already-occupied datasets
     * @param filename The name/filepath of the file to be imported.
     */
    @Given("I have a dataset imported from {string}")
    public void iHaveADatasetImportedFrom(String filename) {
        iHaveAnEmptyDataset();
        iImport(filename);
    }

    /**
     * An assertion that checks that the manager has the expected number of records.
     * @param expected The number of records you expect to see.
     */
    @Then("The dataset has {int} records")
    public void theDatasetHasRecords(int expected) {
        Assert.assertEquals(expected, manager.getLocalCopy().size());
    }

    /**
     * Step definition for exporting a file to a given location
     * @param filename The filename/filepath to be exported. Should end with .csv
     */
    @When("I export the dataset to {string}")
    public void iExportTheDatasetTo(String filename) {
        try {
            manager.exportFile(filename);
        } catch (IOException ex) {
            System.out.println("An IOException has occurred! Can we write to that location?");
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            // This happens if our filename doesn't end in the proper format
            System.out.println(ex.getMessage());
        }
    }

    /**
     * An assertion that checks whether or not the given filename/filepath exists
     * @param filename The name of the file to be checked
     *                 This should start from the src/ directory.
     *                 as an example, THIS file is in
     *                 src/test/java/seng202/team8/cucumber/ImportExportStepDefs.java
     */
    @Then("The file {string} exists")
    public void theFileExists(String filename) {
        Assert.assertTrue(Files.exists(Path.of(filename)));
    }

    /**
     * An assertion to test if the records in the current dataset
     * are equal to those from a given file
     * @param filename The filename/path of the csv record to be tested against
     */
    @Then("The dataset has the same records as {string}")
    public void theDatasetHasTheSameRecordsAs(String filename) {
        // First, make a copy of the records we need
        ArrayList<CrimeRecord> crimes = manager.getLocalCopy();
        // Then, change our manager to the given file
        iHaveADatasetImportedFrom(filename);
        // And then test!
        for (int i = 0; i < crimes.size(); i++) {
            Assert.assertEquals(manager.getLocalCopy().get(0), crimes.get(0));
        }
    }

    /**
     * Adds a pre-made record to the dataset
     * This is pre-made because there are a lot of fields for
     * creating a crime record, so much that it would
     * make the cucumber tests very hard to read
     * Which defeats the point of cucumber
     */
    @When("I add an arbitrary valid record")
    public void iAddAnArbitraryValidRecord() {
        // Make an arbitrary, but valid, test record
        CrimeRecord testRecord = new CrimeRecord("JE266628", 6, 15, 2021,
                LocalTime.of(9,30), "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        manager.addRecord(testRecord);
    }

    /**
     * Deletes the last record in the dataset
     */
    @When("I delete the last record from the dataset")
    public void iDeleteTheLastRecordFromTheDataset() {
        int last_Index = manager.getLocalCopy().size() - 1;
        manager.removeRecord(manager.getLocalCopy().get(last_Index));
    }

    /**
     * Changes the primary description of the 'place'-th record in the list
     * (that's the item at index: place - 1)
     * to the given description
     * @param place The place of the record in the list to be edited - NOT its index
     * @param primary The primary description it is being changed to
     */
    @When("I change the {int} th record's primary description to {string}")
    public void iChangeTheThRecordSPrimaryDescriptionTo(int place, String primary) {
        CrimeRecord crime = manager.getLocalCopy().get(place - 1);
        manager.changeRecord(crime, crime.getCaseNum(), crime.getDate().getMonthValue(),
                crime.getDate().getDayOfMonth(), crime.getDate().getYear(), crime.getTime(),
                crime.getBlock(), crime.getIucr(), primary, crime.getSecondary(), crime.getLocDescription(),
                crime.getWasArrestValue(), crime.getWasDomesticValue(), crime.getBeat(), crime.getWard(),
                crime.getFbiCD(), crime.getLatitude(), crime.getLongitude());
    }

    /**
     * Checks the primary description of the 'place'-th record in the list
     * (that's the item at index place: - 1)
     * against the expected description
     * @param place The place of the record in the list to be checked - NOT its index
     * @param expected The primary description it is being checked against
     */
    @Then("The primary description of the {int} th record is {string}")
    public void thePrimaryDescriptionOfTheThRecordIs(int place, String expected) {
        CrimeRecord crime = manager.getLocalCopy().get(place - 1);
        Assert.assertEquals(expected, crime.getPrimary());
    }
}
