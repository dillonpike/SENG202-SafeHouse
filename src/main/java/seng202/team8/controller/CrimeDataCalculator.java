package seng202.team8.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class that calculates the distance between two crimes and the time difference between
 * two crime timestamps
 */
public class CrimeDataCalculator {

    public CrimeDataCalculator() {
    }

    /**
     * Gets the two locations containing its latitude (x-axis) and longitude (y-axis). Using the
     * "Haversine Formula", the distance between these two locations is calculated.
     * @param firstLocation first crime location of valid location range
     * @param secondLocation second crime location of valid location range
     * @return a crime distance between two crime locations
     */
    public double calculateTwoCrimeDistance(double[] firstLocation, double[] secondLocation) {
        if ((firstLocation[0] == secondLocation[0]) && (firstLocation[1] == secondLocation[1])) {
            return 0;
        }

        // Haversine Formula to calculate distance between two locations
        double latitudeDistance = Math.toRadians(firstLocation[0] - secondLocation[0]);
        double longitudeDistance = Math.toRadians(firstLocation[1] - secondLocation[1]);
        double a = Math.pow(Math.sin(latitudeDistance / 2), 2)
                + Math.cos(Math.toRadians(firstLocation[0])) * Math.cos(Math.toRadians(secondLocation[0]))
                * Math.pow(Math.sin(longitudeDistance / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers
        double radiusOfEarth = 6371;

        // Converting the crime distance to string to 2 decimal places and then converting
        // float and returning it
        String stringCrimeDistance = String.format("%.2f", (c * radiusOfEarth));
        return Double.parseDouble(stringCrimeDistance);
    }

    /**
     * Calculates the time difference between two crime timestamps given.
     * @param startTime first date of crime as a string in 12-hour format
     * @param endTime second date of crime as a string in 12-hour format
     * @return a string containing year, days, and time, in hours, minutes and seconds
     * @throws ParseException If fail to parse string that is going to be saved as a specific format.
     */
    public String calculateTwoCrimeTimeDifference(String startTime, String endTime) throws ParseException {
        // Converts the given timestamps input to Date object
        Date startTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa").parse(startTime);
        Date endTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa").parse(endTime);

        // Calculate time difference
        long differenceTime = Math.abs(startTimeFormat.getTime() - endTimeFormat.getTime());

        // Calculate time difference in seconds, minutes, hours, years, and days
        long differenceSeconds = (differenceTime / 1000) % 60;
        long differenceMinutes = (differenceTime / (1000 * 60)) % 60;
        long differenceHours = differenceTime / (1000 * 60 * 60) % 24;
        long differenceYears = (differenceTime / (1000L * 60 * 60 * 24 * 365));
        long differenceDays = (differenceTime / (1000 * 60 * 60 * 24)) % 365;

        return differenceYears + " years, " +
                differenceDays + " days, " + differenceHours + " hours, " +
                differenceMinutes + " minutes and " + differenceSeconds + " seconds";
    }

}