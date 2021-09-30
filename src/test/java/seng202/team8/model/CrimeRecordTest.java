package seng202.team8.model;

import junit.framework.TestCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CrimeRecordTest extends TestCase {

    private static CrimeRecord crimeRecordTestRecord;

    /**
     * Creates a test record to test with before any tests take place
     */
    @BeforeAll
    public void setUp() {
        crimeRecordTestRecord = new CrimeRecord("JE266628", 6, 15, 2021,
                LocalTime.of(9,30), "080XX S DREXEL AVE" , "0820",
                "OTHER OFFENSE", "LOITERING", "STREET",
                0, 0, 631, 8, "06",
                41.748486365f, (float) -87.602675062);
        crimeRecordTestRecord.setXCoord(1183633);
        crimeRecordTestRecord.setYCoord(1851786);
    }

    /**
     * Tests the method toCSV to check if it generates the expected line
     * This test has been done on a record containing only expected values.
     */
    @Test
    public void testToCSV() {
        String expected = "JE266628,06/15/2021 09:30:00 AM,080XX S DREXEL AVE," +
                "0820,OTHER OFFENSE,LOITERING,STREET,N,N,631,8,06," +
                "1183633,1851786,";
        /*
        Due to the behaviour of doubles, I've had to not only round these values
        but also get them from the crime record and format them in a string.
        the toCSV method also rounds these values to 9 decimal places.
         */
        expected += String.format("%.9f,%.9f,\"(%1$.9f, %2$.9f)\"",
                crimeRecordTestRecord.getLatitude(), crimeRecordTestRecord.getLongitude());
        Assertions.assertEquals(expected, crimeRecordTestRecord.toCSV());
    }
}