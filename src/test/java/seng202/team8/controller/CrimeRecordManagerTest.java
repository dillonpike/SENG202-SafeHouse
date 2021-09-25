package seng202.team8.controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seng202.team8.model.CrimeRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests CrimeRecordManager methods.
 */
class CrimeRecordManagerTest {

    /**
     * Manager used in various tests with test data loaded in.
     */
    private static CrimeRecordManager testManager;

    /**
     * An example crime record used for testing.
     */
    private static CrimeRecord testRecord1;

    /**
     * An example crime record used for testing. Identical to testRecord1 except it has a different case number.
     */
    private static CrimeRecord testRecord2;

    /**
     * Set up a CrimeRecordManager with test data and create two crime records for testing.
     */
    @BeforeAll
    static void setUp() {
        //Initialise testManager and import test data
        testManager = new CrimeRecordManager();
        try {
            testManager.importFile("src/main/resources/testdata.csv");
        } catch (FileNotFoundException ex) {
            System.out.println("Test data filename/filepath is incorrect and/or missing!");
            ex.printStackTrace();
        }

        //Create similar records used in tests with different case numbers
        testRecord1 = new CrimeRecord("JE266628", 6, 15, 2021,
                LocalTime.of(9,30), "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        testRecord2 = new CrimeRecord("JD393294", 6, 15, 2021,
                LocalTime.of(9,30), "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
    }

    /**
     * Tests to see if the manager can import data.
     */
    @Test
    void testImporting() {
        CrimeRecordManager manager = new CrimeRecordManager();
        try {
            manager.importFile("src/main/resources/5kRecords.csv");
        } catch (FileNotFoundException ex) {
            System.out.println("Import test's filename/filepath is incorrect and/or missing!");
            ex.printStackTrace();
        }
        //The number is somewhat arbitrary, this is just making sure stuff is in it
        assertTrue(manager.getLocalCopy().size() > 10);
    }

    /**
     * Tests that the CrimeRecordManager addRecord method adds a crime record to its local copy.
     */
    @Test
    void testAdding() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);
        //Check to see if it has been added
        assertEquals(1, manager.getLocalCopy().size());
    }

    /**
     * Tests if the IUCR numbers being imported are padded correctly
     * Note that the test data contains some 3-length IUCRS
     * and also an empty IUCR - which should not make it into the records
     */
    @Test
    void testIucrPadding() {
        //Check that every single IUCR that was imported is padded correctly
        boolean result = false;
        for (CrimeRecord crime: testManager.getLocalCopy()) {
            //If it's padded correctly, it should be valid
            result = ValidateCrime.validateIucr(crime.getIucr());
        }
        assertTrue(result);
    }

    /**
     * Checks that a record with an empty IUCR is not padded, and is rejected for import
     * Just does this by checking that no record has '0000' as its IUCR
     * using test data that contains a record with an empty IUCR
     *
     * If you add a record with "0000" as an IUCR, this test will break.
     */
    @Test
    void testEmptyIucr() {
        boolean result = true;
        for (CrimeRecord crime: testManager.getLocalCopy()) {
            result = (Objects.equals(crime.getIucr(), "0000"));
        }
        assertFalse(result);
    }

    /**
     * Tests that the CrimeRecordManager addRecord method does nothing when adding a duplicate crime.
     */
    @Test
    void testAddingDuplicate() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record twice
        manager.addRecord(testRecord1);
        manager.addRecord(testRecord1);
        //Check to see if only one has been added
        assertEquals(1, manager.getLocalCopy().size());
    }

    /**
     * Tests that the CrimeRecordManager removeRecord method removes crime records.
     */
    @Test
    void testDeletion() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);
        //Remove the same record
        manager.removeRecord(testRecord1);
        assertEquals(0, manager.getLocalCopy().size());
    }

    /**
     * Tests that the CrimeRecordManager removeRecord method does nothing when trying to delete a crime record that
     * doesn't exist.
     */
    @Test
    void testAbsentDeletion() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);
        //Attempt to remove a different record
        manager.removeRecord(testRecord2);
        assertEquals(1, manager.getLocalCopy().size());
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method edits a crime record.
     */
    @Test
    void testEdit() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);

        //Change the record's location description to "MALL"
        manager.changeRecord(testRecord1, testRecord1.getCaseNum(), 6, 15, 2021,
                testRecord1.getTime(), testRecord1.getBlock(), testRecord1.getIucr(),
                testRecord1.getPrimary(), testRecord1.getSecondary(), "MALL",
                0, 0, testRecord1.getBeat(), testRecord1.getWard(), testRecord1.getFbiCD(),
                testRecord1.getLatitude(), testRecord1.getLongitude());

        //Check that the location description has changed
        assertEquals("MALL", testRecord1.getLocDescription());
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method returns false when given a record that doesn't exist.
     */
    @Test
    void testAbsentEdit() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);

        //Attempt to edit record that doesn't exist in the manager
        boolean result = manager.changeRecord(testRecord2, testRecord1.getCaseNum(), 6, 15, 2021,
                testRecord1.getTime(), testRecord1.getBlock(), testRecord1.getIucr(),
                testRecord1.getPrimary(), testRecord1.getSecondary(), "MALL",
                0, 0, testRecord1.getBeat(), testRecord1.getWard(), testRecord1.getFbiCD(),
                testRecord1.getLatitude(), testRecord1.getLongitude());
        //Check that changeRecord returned false
        assertFalse(result);
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method edits a crime record's case number if the new case number
     * is available.
     */
    @Test
    void testEditVacantCaseNum() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);

        String newCaseNum = "JD364770";

        //Change the record's case number to a vacant one
        manager.changeRecord(testRecord1, newCaseNum, 6, 15, 2021,
                testRecord1.getTime(), testRecord1.getBlock(), testRecord1.getIucr(),
                testRecord1.getPrimary(), testRecord1.getSecondary(), testRecord1.getLocDescription(),
                0, 0, testRecord1.getBeat(), testRecord1.getWard(), testRecord1.getFbiCD(),
                testRecord1.getLatitude(), testRecord1.getLongitude());

        //Check that the case number has changed
        assertEquals(newCaseNum, testRecord1.getCaseNum());
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method doesn't edit a crime record's case number if the new case
     * number is not available.
     */
    @Test
    void testEditUsedCaseNum() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);
        manager.addRecord(testRecord2);

        //Store the original case number
        String caseNum = testRecord1.getCaseNum();

        //Attempt to change the record's case number to a testRecord2's case number
        manager.changeRecord(testRecord1, testRecord2.getCaseNum(), 6, 15, 2021,
                testRecord1.getTime(), testRecord1.getBlock(), testRecord1.getIucr(),
                testRecord1.getPrimary(), testRecord1.getSecondary(), testRecord1.getLocDescription(),
                0, 0, testRecord1.getBeat(), testRecord1.getWard(), testRecord1.getFbiCD(),
                testRecord1.getLatitude(), testRecord1.getLongitude());

        //Check that the case number has not changed
        assertEquals(caseNum, testRecord1.getCaseNum());
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method adds to the crime primary frequencies when adding a record.
     */
    @Test
    void testFreqAdding() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);

        //Get the frequency of crime records with primary description: "OTHER OFFENSE" and check that it's equal to 1
        int actual = manager.getPrimaryFreq("OTHER OFFENSE");
        assertEquals(1, actual);
    }

    /**
     * Tests that the CrimeRecordManager changeRecord method removes from the crime primary frequencies when removing a
     * record.
     */
    @Test
    void testFreqRemoving() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Add the record
        manager.addRecord(testRecord1);
        //Remove the record
        manager.removeRecord(testRecord1);

        //Get the frequency of crime records with primary description: "OTHER OFFENSE" and check that it's equal to 0
        int actual = manager.getPrimaryFreq("OTHER OFFENSE");
        assertEquals(0, actual);
    }

    /**
     * Tests that the CrimeRecordManager properly imports the case number.
     */
    @Test
    void testCaseNum() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("JE267023", records.get(0).getCaseNum());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the date.
     */
    @Test
    void testDate() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(LocalDate.of(2021, 6, 15), records.get(0).getDate());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the time.
     */
    @Test
    void testTime() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(LocalTime.of(3, 3), records.get(0).getTime());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the block.
     */
    @Test
    void testBlock() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("082XX S STONY ISLAND AVE", records.get(0).getBlock());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the IUCR.
     */
    @Test
    void testIUCR() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("0460", records.get(0).getIucr());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the primary description.
     */
    @Test
    void testPrimaryDesc() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("BATTERY", records.get(0).getPrimary());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the secondary description.
     */
    @Test
    void testSecondaryDesc() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("SIMPLE", records.get(0).getSecondary());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the location description.
     */
    @Test
    void testLocationDesc() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("STREET", records.get(0).getLocDescription());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the arrest value.
     */
    @Test
    void testArrest() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("Yes", records.get(0).getWasArrest());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the domestic value.
     */
    @Test
    void testDomestic() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("No", records.get(0).getWasDomestic());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the beat.
     */
    @Test
    void testBeat() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(411, records.get(0).getBeat());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the ward.
     */
    @Test
    void testWard() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(8, records.get(0).getWard());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the FBI CD.
     */
    @Test
    void testFbiCD() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals("08B", records.get(0).getFbiCD());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the x-coordinate.
     */
    @Test
    void testXCoord() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(1188236, records.get(0).getXCoord());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the y-coordinate.
     */
    @Test
    void testYCoord() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(1850761, records.get(0).getYCoord());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the latitude.
     */
    @Test
    void testLatitude() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(41.74556517, records.get(0).getLatitude());
    }

    /**
     * Tests that the CrimeRecordManager properly imports the longitude.
     */
    @Test
    void testLongitude() {
        ArrayList<CrimeRecord> records = testManager.getLocalCopy();
        assertEquals(-87.58584092, records.get(0).getLongitude());
    }

}