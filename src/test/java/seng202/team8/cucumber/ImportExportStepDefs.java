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
import java.util.ArrayList;

/**
 * Step definitions for the Importing and Exporting cucumber tests
 * Although separate feature files, Exporting needs to use
 * some importing functionality, so it can test stuff
 * Hence, they use the same step definition class
 */
public class ImportExportStepDefs {

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
}
