package seng202.team8.controller;

/**
 * A class of static methods that validate the various
 * attributes of a CrimeRecord
 * Some attributes, such as a location description,
 * are just strings and don't need to be validated.
 * The following attributes should just be 'good to go':
 * Date (as there's a calendar selection)
 * Secondary Description
 * Location (as there's a dropBox of options)
 */
public class ValidateCrime {

    /**
     * Checks if a case number is valid or not
     * The first two characters should be letters
     * And the rest should be numbers
     * @param caseNum The string to be validated
     * @return True if the case number is valid, false otherwise
     */
    public static boolean validateCaseNum (String caseNum) {
        boolean letters = caseNum.substring(0, 2).matches("[a-zA-Z]+");
        boolean numbers = caseNum.substring(2).matches("[0-9]+");

        return (letters && numbers);
    }

    /**
     * An IUCR is a 4-digit code
     * so if it's not 4 digits, it's invalid
     * Some may have letters, however.
     * You may have to remind users not to forget leading 0's
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validateIucr (String candidate) {
        return (candidate.length() == 4);
    }

    /**
     * Checks if a primary description is valid or not
     * A primary should only contain letters
     * Since it's related to the IUCR, the number of different
     * primaries could be in the hundreds, so it is
     * assumed that any valid string is a valid primary
     * @param primary The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validatePrimary (String primary) {
        return primary.matches("[a-zA-Z]+");
    }

    /**
     * Validates if a string can be read as an int
     * This is for all integer-type fields like
     * Beat, Ward, and the map coordinates
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validateInt (String candidate) {
        try {
            Integer.parseInt(candidate);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string can be read as a double
     * This is for latitude and longitude fields
     * Any valid double is considered a valid lat/lon
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validateDouble (String candidate) {
        try {
            Double.parseDouble(candidate);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string is a valid FBI crime code
     * The FBI crime code is a category of the crime,
     * and is three digits long.
     * For some, the final digit is a letter.
     *
     * You may have to remind users to add leading 0's
     * @param candidate the String to be validated
     * @return True if valid, false if not
     */
    public static boolean validateFbiCD (String candidate) {
        boolean suffix = candidate.substring(2).matches("[a-zA-Z0-9]");
        boolean prefix = candidate.substring(0, 2).matches("[0-9]+");

        return (suffix && prefix && (candidate.length() == 3));
    }
}
