package seng202.team8.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Models a crime record. Contains attributes, getters and setters, and conversion methods.
 */
public class CrimeRecord {

    /**
     * The case number. A string, as it has a two-letter prefix.
     */
    private String caseNum;

    /**
     * Date of the crime.
     */
    private LocalDate date;

    /**
     * Time of the crime.
     */
    private LocalTime time;

    /**
     * The block - contains numbers and letters so is a string.
     */
    private String block;

    /**
     * The Illinois Uniform Crime Reporting code. Some entries contain letters, so it is a string.
     */
    private String iucr;

    /**
     * The primary description of the crime.
     * This is essentially the 'type' of crime that was committed.
     */
    private String primary;

    /**
     * The secondary, and more detailed, report of the crime.
     * This is the crime's 'description'.
     */
    private String secondary;

    /**
     * A description of the location.
     * More or less a location 'type'.
     */
    private String locDescription;

    /**
     * If an arrest was made.
     * 0 for false
     * 1 for true
     * -1 for no value
     */
    private int wasArrest;

    /**
     * If the crime was domestic.
     * 0 for false
     * 1 for true
     * -1 for no value
     */
    private int wasDomestic;

    /**
     * The police district.
     */
    private int beat;

    /**
     * Election precinct.
     */
    private int ward;

    /**
     * The FBI crime code. Sometimes contains letters, so is a string.
     */
    private String fbiCD;

    /**
     * X map coordinate.
     */
    private int xCoord;

    /**
     * Y map coordinate.
     */
    private int yCoord;

    /**
     * Latitude location of crime.
     */
    private double latitude;

    /**
     * Longitude location of crime.
     */
    private double longitude;

    /**
     * Location of crime in [Latitude, Longitude].
     */
    private double[] location = {latitude, longitude};

    /**
     * Constructor that initializes an empty crime entry
     */
    public CrimeRecord() {
        super();
    }

    /**
     * Constructor to make a new crime record.
     * @param caseNum The Case Number of the Crime
     * @param month The month the crime occurred (in int form)
     * @param date The day of the month the crime occurred (in int form)
     * @param year The year the crime occurred (in int form)
     * @param time The time the crime occurred
     * @param block The block of the crime
     * @param iucr The IUCR of the crime
     * @param primary The primary description of the crime
     * @param secondary The secondary, and more detailed, description of the crime
     * @param locdesc The description of the location
     * @param arrest Whether an arrest was made
     *               0 for false
     *               1 for true
     *               -1 for unknown
     * @param domestic Whether the crime was domestic
     *                 0 for false
     *                 1 for true
     *                 -1 for unknown
     * @param beat The beat of the crime
     * @param ward The ward of the crime
     * @param fbiCD The FBI case number of the crime
     * @param lat The latitude of the crime
     * @param lon The longitude of the crime
     */
    public CrimeRecord(String caseNum, int month, int date, int year, LocalTime time, String block,
                       String iucr, String primary, String secondary, String locdesc,
                       int arrest, int domestic, int beat, int ward, String fbiCD,
                       double lat, double lon) {
        this.caseNum = caseNum;
        this.date = LocalDate.of(year, month, date);
        this.time = time;
        this.block = block;
        this.iucr = iucr;
        this.primary = primary;
        this.secondary = secondary;
        this.locDescription = locdesc;
        this.wasArrest = arrest;
        this.wasDomestic = domestic;
        this.beat = beat;
        this.ward = ward;
        this.fbiCD = fbiCD;
        this.latitude = lat;
        this.longitude = lon;
    }

    /**
     * Replaces all the attributes of a given crime with the provided ones.
     * Mainly used for editing crimes in the Local Copy of CrimeRecordManager
     *
     * THIS SHOULD NOT BE CALLED
     * CALL THE ONE IN CrimeRecordManager INSTEAD
     *
     * @param crime The crime being changed
     * @param caseNum The Case Number of the Crime
     * @param month The month the crime occurred (in int form)
     * @param date The day of the month the crime occurred (in int form)
     * @param year The year the crime occurred (in int form)
     * @param time The time the crime occurred
     * @param block The block of the crime
     * @param iucr The IUCR of the crime
     * @param primary The primary description of the crime
     * @param secondary The secondary, and more detailed, description of the crime
     * @param locdesc The description of the location
     * @param arrest Whether an arrest was made
     *               0 for false
     *               1 for true
     *               -1 for unknown
     * @param domestic Whether the crime was domestic
     *                 0 for false
     *                 1 for true
     *                 -1 for unknown
     * @param beat The beat of the crime
     * @param ward The ward of the crime
     * @param fbiCD The FBI case number of the crime
     * @param lat The latitude of the crime
     * @param lon The longitude of the crime
     */
    public static void changeRecord(CrimeRecord crime, String caseNum, int month, int date, int year,
                             LocalTime time, String block, String iucr, String primary,
                             String secondary, String locdesc, int arrest, int domestic,
                             int beat, int ward, String fbiCD, double lat, double lon) {
        crime.setCaseNum(caseNum);
        crime.setDate(month, date, year);
        crime.setTime(time);
        crime.setBlock(block);
        crime.setIucr(iucr);
        crime.setPrimary(primary);
        crime.setSecondary(secondary);
        crime.setLocDescription(locdesc);
        crime.setWasArrest(arrest);
        crime.setWasDomestic(domestic);
        crime.setBeat(beat);
        crime.setWard(ward);
        crime.setFbiCD(fbiCD);
        crime.setLatitude(lat);
        crime.setLongitude(lon);
    }

    /**
     * Gets the case number
     * @return The case number, in String Form
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * Gets the date
     * @return Returns LocalDate object of the crime's date
     */
    public LocalDate getDate() { return date; }

    /**
     * Gets the date
     * @return Returns LocalDate object of the crime's date
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets the block.
     * @return The block, in string form.
     */
    public String getBlock() {
        return block;
    }

    /**
     * Gets the IUCR
     * @return the IUCR, in string form.
     */
    public String getIucr() {
        return iucr;
    }

    /**
     * gets the Primary description
     * @return The Primary Description, in String form.
     */
    public String getPrimary() {
        return primary;
    }

    /**
     * gets the secondary description
     * @return the secondary description, in string form
     */
    public String getSecondary() {
        return secondary;
    }

    /**
     * gets the location description
     * @return the location description, in string form
     */
    public String getLocDescription() {
        return locDescription;
    }

    /**
     * gets the arrest status
     * @return "Yes" if an arrest was made, "No" if there was not, and "N/A" if it's unknown
     */
    public String getWasArrest() {
        if (wasArrest == 1) {
            return "Yes";
        } else if (wasArrest == 0) {
            return "No";
        } else {
            return "N/A";
        }
    }

    /**
     * gets the arrest status
     * @return 1 if an arrest was made, 0 if there was not, and -1 if it's unknown
     */
    public int getWasArrestValue() {
        return wasArrest;
    }

    /**
     * gets the domestic status
     * @return "Yes" if the crime was domestic, "No" if it was not, and "N/A" if it's unknown
     */
    public String getWasDomestic() {
        if (wasDomestic == 1) {
            return "Yes";
        } else if (wasDomestic == 0) {
            return "No";
        } else {
            return "N/A";
        }
    }

    /**
     * gets the domestic status
     * @return 1 if the crime was domestic, 0 if it was not, and -1 if it's unknown
     */
    public int getWasDomesticValue() {
        return wasDomestic;
    }

    /**
     * gets the beat number
     * @return the beat, in integer form
     */
    public int getBeat() {
        return beat;
    }

    /**
     * gets the ward number
     * @return the ward number, in integer form
     */
    public int getWard() {
        return ward;
    }

    /**
     * gets the FBI CD
     * @return the FBI CD, in string form.
     */
    public String getFbiCD() {
        return fbiCD;
    }

    /**
     * gets the X coord
     * @return the X coord, in integer form.
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * gets the y coord
     * @return the y coord, in integer form.
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * gets the latitude
     * @return the latitude, in float form.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * gets the longitude
     * @return the longitude, in float form.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * gets the location
     * @return the location, in the form of an array of [latitude, longitude]
     */
    public double[] getLocation() {
        return location;
    }

    /**
     * sets the case number
     * @param caseNum The case number of the crime
     */
    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    /**
     * Sets the date
     * @param month The month of the crime
     * @param day The day of the crime
     * @param year The year of the crime
     */
    public void setDate(int month, int day, int year) {
        this.date = LocalDate.of(year, month, day);
    }

    /**
     * Sets the time
     * @param time The time of the crime
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Sets the block
     * @param block The block of the crime
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     * Sets the IUCR code
     * @param iucr the IUCR code of the crime
     */
    public void setIucr(String iucr) {
        this.iucr = iucr;
    }

    /**
     * sets the primary description
     * @param prim The primary description of the crime
     */
    public void setPrimary(String prim) {
        this.primary = prim;
    }

    /**
     * sets the secondary description
     * @param sec The secondary description of the crime
     */
    public void setSecondary(String sec) {
        this.secondary = sec;
    }

    /**
     * sets the location description
     * @param desc The location description of the crime.
     */
    public void setLocDescription(String desc) {
        this.locDescription = desc;
    }

    /**
     * sets the arrest status
     * @param arrest the arrest staus of the crime
     *               0 for false
     *               1 for true
     *               -1 for no value
     */
    public void setWasArrest(int arrest) {
        this.wasArrest = arrest;
    }

    /**
     * sets the domestic status of the crime
     * @param domestic the domestic status of the crime
     *                 0 for false
     *                 1 for true
     *                 -1 for no value
     */
    public void setWasDomestic(int domestic) {
        this.wasDomestic = domestic;
    }

    /**
     * sets the beat
     * @param beat the beat number of the crime
     */
    public void setBeat(int beat) {
        this.beat = beat;
    }

    /**
     * sets the ward of the crime
     * @param ward the ward number of the crime
     */
    public void setWard(int ward) {
        this.ward = ward;
    }

    /**
     * sets the FBI CD of the crime
     * @param code the FBI CD of the crime
     */
    public void setFbiCD(String code) {
        this.fbiCD = code;
    }

    /**
     * sets the x coordinate of the crime
     * @param coord the x coordinate of the crime
     */
    public void setXCoord(int coord) {
        this.xCoord = coord;
    }

    /**
     * sets the y coordinate
     * @param coord the y coordinate of the crime
     */
    public void setYCoord(int coord) {
        this.yCoord = coord;
    }

    /**
     * sets the latitude
     * @param lat the latitude of the crime
     */
    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    /**
     * sets the longitude
     * @param lon the longitude of the crime
     */
    public void setLongitude(double lon) {
        this.longitude = lon;
    }

    /**
     * sets both the latitude and longitude of the crime, and thereby the location
     * @param lat the latitude of the crime
     * @param lon the longitude of the crime
     */
    public void setLocation(double lat, double lon) {
        setLatitude(lat);
        setLongitude(lon);
    }

    /**
     * Returns a formatted string version of the record's information,
     * primarily for use in javascript/html functions.
     * Here it is used for map information display.
     * @return A formatted string summary of the record's information
     */
    public String toScript() {
        return ("Case Number: " + caseNum + "<br>Date: " + date.getMonthValue() + "/" + date.getDayOfMonth() + "/"
                + date.getYear() + "<br>Time: " + time.toString() + "<br>Block: " + block + "<br>IUCR: " + iucr +
                "<br>Primary Description: " + primary + "<br>Secondary Description: " + secondary + "<br>Location: " +
                locDescription + "<br>Arrest Made: " + getWasArrest() + "<br>Domestic: " + getWasDomestic()
                + "<br>Beat: " + beat + "<br>Ward: " + ward + "<br>FBI CD: " + fbiCD
                + "<br>X Coord: " + xCoord + "<br>Y Coord: " + yCoord +
                "<br>Latitude: " + String.format("%.4f", latitude)
                + "<br>Longitude: " + String.format("%.4f", longitude));
    }


    /**
     * Returns a version of this crime record in an appropriate csv-format
     * @return The string representation of this record's crime values, as comma seperated values
     */
    public String toCSV() {
        /*
        Note for readers:
        As you can see, I've broken up the format string into line segments
        The values of those formats are also broken up into line segments
        As you might guess, these are related: each line of values
        directly corresponds to a line of the format string.
         */
        return String.format("%s,%02d/%02d/%04d %s," +
                        "%s,%s,%s,%s,%s," +
                        "%s,%s," +
                        "%d,%d,%s,%d,%d," +
                        "%.9f,%.9f,\"(%18$.9f, %19$.9f)\"",
                caseNum, date.getMonthValue(), date.getDayOfMonth(), date.getYear(), timeToCSV(time),
                block, iucr, primary, secondary, locDescription,
                wasLetter(wasArrest), wasLetter(wasDomestic),
                beat, ward, fbiCD, xCoord, yCoord,
                latitude, longitude);
    }

    /**
     * Helper function that turns a wasArrest or wasDomestic value into a Y, N or X.
     * Used for string conversion, particularly to a CSV format.
     * @param wasValue The wasArrest or wasDomestic value being converted
     * @return Y if there was an arrest, N if there was not, and X if unknown.
     */
    private String wasLetter(int wasValue) {
        if (wasValue == 1) {
            return "Y";
        } else if (wasValue == 0) {
            return "N";
        } else {
            return "X";
        }
    }

    /**
     * Converts the local time into the same format that we expect from the CSVs.
     * This means we can later import this section.
     * @param localTime The localTime object to be converted into a csv-friendly format
     * @return A string representation of the time
     */
    private String timeToCSV(LocalTime localTime) {
        //Check if we're in AM or PM
        int hour;
        String suffix;
        if (localTime.getHour() > 12) {
            //The time is PM
            hour = time.getHour() - 12;
            suffix = "PM";
        } else {
            //The time is AM
            hour = time.getHour();
            suffix = "AM";
        }
        //Now create our output string
        return String.format("%02d:%02d:%02d %s",
                hour, localTime.getMinute(), localTime.getSecond(), suffix);
    }

    /**
     * An overridden equals method to check if two crime records are the same
     * Generated by IntelliJ, but manually formatted for (slightly) improved
     * readability
     * @param o The object you are comparing to this one.
     * @return True if the crime records are equal in all their attributes,
     * excluding location since it's a tuple of latitude and longitude
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrimeRecord that = (CrimeRecord) o;
        return Objects.equals(getWasArrest(),
                that.getWasArrest()) && Objects.equals(getWasDomestic(),
                that.getWasDomestic()) && getBeat() == that.getBeat() && getWard() == that.getWard()
                && xCoord == that.xCoord && yCoord == that.yCoord && Double.compare(that.getLatitude(),
                getLatitude()) == 0 && Double.compare(that.getLongitude(), getLongitude()) == 0
                && getCaseNum().equals(that.getCaseNum()) && Objects.equals(getDate(),
                that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getBlock(),
                that.getBlock()) && getIucr().equals(that.getIucr()) && getPrimary().equals(that.getPrimary())
                && Objects.equals(getSecondary(), that.getSecondary()) && Objects.equals(getLocDescription(),
                that.getLocDescription()) && Objects.equals(getFbiCD(), that.getFbiCD());
    }


}
