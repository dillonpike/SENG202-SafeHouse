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
     * Minimum latitude value.
     */
     public static final int MIN_LATITUDE = -90;

    /**
     * Maximum latitude value.
     */
    public static final int MAX_LATITUDE = 90;

    /**
     * Minimum longitude value.
     */
    public static final int MIN_LONGITUDE = -180;

    /**
     * Maximum longitude value.
     */
    public static final int MAX_LONGITUDE = 180;

    /**
     * Checks if a case number is valid or not
     * The first two characters should be letters
     * And the rest should be numbers
     * @param caseNum The string to be validated
     * @return True if the case number is valid, false otherwise
     */
    public static boolean validateCaseNum(String caseNum) {
        try {
            boolean letters = caseNum.substring(0, 2).matches("[a-zA-Z]+");
            boolean numbers = caseNum.substring(2).matches("[0-9]+");

            return (letters && numbers);
        } catch(StringIndexOutOfBoundsException e) {
            // The string is not long enough to be valid
            return false;
        }
    }

    /**
     * An IUCR is a 4-digit code
     * so if it's not 4 digits, it's invalid
     * Some may have letters, however.
     * You may have to remind users not to forget leading 0's
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validateIucr(String candidate) {
        return (candidate.length() == 4);
    }

    /**
     * Checks if a primary description is valid or not
     * A primary should only contain letters
     * Since it's related to the IUCR, the number of different
     * primaries could be in the hundreds, so it is
     * assumed that any valid string is a valid primary
     *
     * NOTE:
     * The current regex check is "characters or a whitespace one or more times".
     * This would make double whitespaces valid
     * The regex will need to be 'refurbished' into
     * (characters one or more times) followed by zero to one whitespaces,
     * with that pattern occuring one or more times.
     *
     * @param primary The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validatePrimary(String primary) {
        // Is valid if it conforms to the regex and isn't empty
        boolean format = primary.matches("[a-zA-Z\\s]+");
        boolean onlyWhitespace = primary.matches("[\\s]+");
        return format && !(onlyWhitespace);
    }

    /**
     * Validates if a string can be read as an int
     * This is for all integer-type fields like
     * Beat, Ward, and the map coordinates
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    public static boolean validateInt(String candidate) {
        try {
            Integer.parseInt(candidate);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if latitudeString can be converted to a double and is a valid latitude value,
     * otherwise returns false.
     * @param latitudeString latitude as a string
     * @return true if latitudeString is valid, otherwise false
     */
    public static boolean validateLatitude(String latitudeString) {
        if (validateDouble(latitudeString)) {
            double latitude = Double.parseDouble(latitudeString);
            return MIN_LATITUDE <= latitude && latitude <= MAX_LATITUDE;
        }
        return false;
    }

    /**
     * Returns true if longitudeString can be converted to a double and is a valid longitude value,
     * otherwise returns false.
     * @param longitudeString longitude as a string
     * @return true if longitudeString is valid, otherwise false
     */
    public static boolean validateLongitude(String longitudeString) {
        if (validateDouble(longitudeString)) {
            double longitude = Double.parseDouble(longitudeString);
            return MIN_LONGITUDE <= longitude && longitude <= MAX_LONGITUDE;
        }
        return false;
    }

    /**
     * Validates if a string can be read as a double
     * This is for latitude and longitude fields.
     * @param candidate The string to be validated
     * @return True if valid, false otherwise
     */
    private static boolean validateDouble(String candidate) {
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
     * and is often three digits long
     * but sometimes can be two
     * For some, the final digit is a letter.
     *
     * You may have to remind users to add leading 0's
     * @param candidate the String to be validated
     * @return True if valid, false if not
     */
    public static boolean validateFbiCD(String candidate) {
        try {
            int length = candidate.length();
            // The final character of the candidate
            boolean suffix = candidate.substring(length - 1).matches("[a-zA-Z0-9]");
            boolean prefix = candidate.substring(0, length - 1).matches("[0-9]+");

            return (suffix && prefix && (length <= 3));
        } catch (StringIndexOutOfBoundsException e) {
            /*
            Caused if we try to access a string index that doesn't exist
            Which would be when the candidate is empty
             */
            return false;
        }
    }
}
