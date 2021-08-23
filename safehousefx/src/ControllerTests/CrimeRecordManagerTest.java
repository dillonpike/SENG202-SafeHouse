package ControllerTests;
import java.io.FileNotFoundException;

import Controller.CrimeRecordManager;
import junit.framework.TestCase;

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
            manager.importFile("src/test/java/seng202/team8/ControllerTests/5kRecords.csv");

            //The number is somewhat arbitrary, this is just making sure stuff is in it
            assertTrue(manager.getLocalCopy().size() > 10);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Your test's filename/filepath is incorrect and/or missing!");
            ex.printStackTrace();
        }

    }


}