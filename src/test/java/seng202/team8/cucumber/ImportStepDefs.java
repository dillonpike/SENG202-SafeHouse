package seng202.team8.cucumber;

import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import seng202.team8.controller.CrimeRecordManager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Step definitions for the Importing.feature Cucumber file.
 */
public class ImportStepDefs {

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
     * An assertion that checks that the manager has the expected number of records.
     * @param expected The number of records you expect to see.
     */
    @Then("The dataset has {int} records")
    public void theDatasetHasRecords(int expected) {
        Assert.assertEquals(expected, manager.getLocalCopy().size());
    }
}
