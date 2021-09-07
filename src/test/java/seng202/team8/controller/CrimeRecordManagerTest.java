package seng202.team8.controller;

import java.io.FileNotFoundException;

import seng202.team8.controller.CrimeRecordManager;
import junit.framework.TestCase;
import seng202.team8.model.CrimeRecord;

/**
 * Test if the manager can import things correctly
 */
public class CrimeRecordManagerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tests to see if the manager can import data
     */
    public void testImporting() {
        CrimeRecordManager manager = new CrimeRecordManager();
        try {
            manager.importFile("src/test/java/seng202/team8/controller/5kRecords.csv");

            //The number is somewhat arbitrary, this is just making sure stuff is in it
            assertTrue(manager.getLocalCopy().size() > 10);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Your test's filename/filepath is incorrect and/or missing!");
            ex.printStackTrace();
        }

    }

    public void testAdding() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Create the record
        CrimeRecord loitering = new CrimeRecord("JE266628", 6, 15, 2021,
                "09:30:00 AM", "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        //Add the record
        manager.addRecord(loitering);
        //Check to see if it has been added
        assertEquals(1, manager.getLocalCopy().size());

    }

    public void testDeletion() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Create the record
        CrimeRecord loitering = new CrimeRecord("JE266628", 6, 15, 2021,
                "09:30:00 AM", "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        manager.addRecord(loitering);

        manager.removeRecord(loitering);
        assertEquals(0, manager.getLocalCopy().size());
    }

    public void testEdit() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Create the record
        CrimeRecord loitering = new CrimeRecord("JE266628", 6, 15, 2021,
                "09:30:00 AM", "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        manager.addRecord(loitering);

        manager.changeRecord(loitering, "JE266628", 6, 15, 2021,
                "10:00:00 PM", "080XX S SHOPPING STREET" , "0820",
                "OTHER OFFENSE", "LOITERING", "MALL",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);

        assertEquals("MALL", loitering.getLocDescription());
    }

    public void testFreq() {
        CrimeRecordManager manager = new CrimeRecordManager();
        //Create the record
        CrimeRecord loitering = new CrimeRecord("JE266628", 6, 15, 2021,
                "09:30:00 AM", "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        manager.addRecord(loitering);

        int actual = manager.getPrimaryFreq("OTHER OFFENSE");
        assertEquals(1, actual);
    }


}