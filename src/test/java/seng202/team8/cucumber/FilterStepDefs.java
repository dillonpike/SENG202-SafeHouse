package seng202.team8.cucumber;

import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import seng202.team8.controller.CrimeRecordManager;
import seng202.team8.controller.SearchCrimeData;
import seng202.team8.model.CrimeRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Step definition class for filtering through a crime record manager's records
 */
public class FilterStepDefs {

    /**
     * A manager to contain records, which we then filter
     */
    private CrimeRecordManager manager = new CrimeRecordManager();

    /**
     * A more easily-accessible arrayList of records than manager.getLocalCopy
     */
    private ArrayList<CrimeRecord> records;


    @Given("I have no filters")
    public void iHaveNoFilters() {
        // Because we do not add duplicates, this will re-fill our manager
        try {
            manager.importFile("src/test/resources/TenValidRecords.csv");
        } catch (FileNotFoundException ex) {
            System.out.println("The file wasn't found!");
            ex.printStackTrace();
        } catch (IOException | CsvValidationException ex) {
            System.out.println("The file couldn't be imported!");
            ex.printStackTrace();
        }
        // Reset our records
        records = manager.getLocalCopy();
    }

    /**
     * Filters the dates of records down to the given range
     * @param startDate The start of the date range, in DD/MM/YYYY format
     * @param endDate The end of the date range, in DD/MM/YYYY format
     */
    @When("I filter for dates {string} to {string}")
    public void iFilterForDatesTo(String startDate, String endDate) {
        try {
            records = SearchCrimeData.filterByDate(records, startDate, endDate);
        } catch (ParseException e) {
            System.out.println("We couldn't parse those dates!");
            e.printStackTrace();
        }

    }

    /**
     * An assertion that checks the size of the records
     * @param expected The expected number of records
     */
    @Then("There are {int} records left")
    public void thereAreRecordsLeft(int expected) {
        Assert.assertEquals(expected, records.size());
    }

    /**
     * Filters the records down to those matching the given primary description
     * @param primary The description to filter by
     */
    @When("I filter for primary description for {string}")
    public void iFilterForPrimaryDescriptionFor(String primary) {
        records = SearchCrimeData.filterByPrimaryDesc(records, primary);
    }

    /**
     * Filters the records down to those matching the given location description
     * @param location The description to filter by
     */
    @When("I filter for location description {string}")
    public void iFilterForLocationDescription(String location) {
        records = SearchCrimeData.filterByCrimeLocation(records, location);
    }

    /**
     * Filters the records down to those within the beat range
     * @param startBeat The start of the beat range
     * @param endBeat The end of the beat range
     */
    @When("I filter for beats {int} to {int}")
    public void iFilterForBeatsTo(int startBeat, int endBeat) {
        records = SearchCrimeData.filterByCrimeLocationBeat(records, startBeat, endBeat);
    }

    /**
     * Filters the records down to those within the ward range
     * @param startWard The start of the ward range
     * @param endWard The end of the ward range
     */
    @When("I filter for wards between {int} to {int}")
    public void iFilterForWardsBetweenTo(int startWard, int endWard) {
        records = SearchCrimeData.filterByCrimeWard(records, startWard, endWard);
    }


    /**
     * Filters the records down to those with arrests made
     */
    @When("I filter for Yes Arrests")
    public void iFilterForYesArrests() {
        records = SearchCrimeData.filterByArrest(records, true);
    }

    /**
     * Filters the records down to those that were domestic
     */
    @When("I filter for Yes Domestic")
    public void iFilterForYesDomestic() {
        records = SearchCrimeData.filterByDomesticViolence(records, true);
    }
}
