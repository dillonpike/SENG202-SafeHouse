package seng202.team8.controller;


import com.sun.javafx.binding.StringFormatter;

public class CrimeDataCalculator {


    public CrimeDataCalculator() {
    }

    /**
     * @param firstLocation
     * @param secondLocation
     * @return
     */
    public float calculateTwoCrimeDistance(double[] firstLocation, double[] secondLocation) {
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
        float crimeDistance = Float.parseFloat(stringCrimeDistance);
        return crimeDistance;
    }

    public static void main(String args[]) {

    }
}