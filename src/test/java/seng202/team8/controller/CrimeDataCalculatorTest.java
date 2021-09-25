package seng202.team8.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class that checks CrimeDataCalculator class methods, whether their input and output are
 * working correctly
 */
class CrimeDataCalculatorTest {

    /**
     * Test for calculating the two crime distance. Firstly, two crime locations are set, and then
     * passes these locations to the calculateTwoCrimeDistance method. Next, it gets the crime distance
     * as a double to 2 decimal places
     */
    @Test
    void testCalculateTwoCrimeDistance() {
        // Setting the first and second location where for each at index 0 is latitude and at index 1 is longitude
        double[] firstLocation = {41.748486365, -87.602675062};
        double[] secondLocation = {41.884654292, -87.771937787};

        // Runs the crime distance calculator method
        CrimeDataCalculator crimeDataCalculator = new CrimeDataCalculator();
        double crimeDistance = crimeDataCalculator.calculateTwoCrimeDistance(firstLocation, secondLocation);

        // Testing whether the expected distance value equals to the actual distance calculated
        assertEquals(20.64, crimeDistance);
    }

    /**
     * Test for calculating the time difference between two crimes. Firstly, sets the start and end time
     * as a string, which contains the date and time. Next, these two are passed as parameters to
     * the calculateTwoCrimeTimeDifference method. This calculates and gets the time difference in a
     * string form as "years, days, hours, minutes and seconds", respectively.
     */
    @Test
    void testCalculateTwoCrimeTimeDifference() {
        // Setting the start and end time occurrence
        String startTime = "11/23/2020 03:05:00 PM";
        String endTime = "06/15/2021 09:30:00 AM";

        // Passing the start and end time as parameters to the crime time difference method
        CrimeDataCalculator crimeDataCalculator = new CrimeDataCalculator();
        String crimeTimeDifference = "";
        try {
            crimeTimeDifference += crimeDataCalculator.calculateTwoCrimeTimeDifference(startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("0 years, 203 days, 19 hours, 25 minutes and 0 seconds", crimeTimeDifference);
    }

}