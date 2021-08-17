package seng202.team8.Model;


/**
 * A basic crime record class, currently consisting of only attributes
 * as well as getters and setters
 */
public class CrimeRecord {
    /**
     * The case number. A string, as it has a two-letter prefix.
     */
    private String caseNum;
    /**
     * The date, in the form of a three-piece ineteger array. Arranged to american date format.
     * The 0 index is the month
     * The 1 index is the date
     * The 2 index is the year
     */
    private int[] date = new int[3];
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
     * if an arrest was made.
     */
    private boolean wasArrest;
    /**
     * if the crime was domestic.
     */
    private boolean wasDomestic;
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
    private float latitude;
    /**
     * Longitude location of crime.
     */
    private float longitude;
    /**
     * Location of crime in [Latitude, Longitude].
     */
    private float[] location = {latitude, longitude};

    /**
     * Gets the case number
     * @return The case number, in String Form
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * Gets the date
     * @return Returns an integer array of the date, in [month, day, year] format.
     */
    public int[] getDate() {
        return date;
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
     * Gets the arrest status
     * @return true if an arrest was made, false if there was not
     */
    public boolean getWasArrest() {
        return wasArrest;
    }

    /**
     * gets the domestic status
     * @return true if the crime was domestic, false otherwise
     */
    public boolean getWasDomestic() {
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
    public float getLatitude() {
        return latitude;
    }

    /**
     * gets the longitude
     * @return the longitude, in float form.
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * gets the location
     * @return the location, in the form of an array of [latitude, longitude]
     */
    public float[] getLocation() {
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
        this.date[0] = month;
        this.date[1] = day;
        this.date[2] = year;
    }

    /**
     * Sets the block
     * @param block The block of the crime
     */
    public void setBlock(String block) {
        this.block = block;
    }

    /**
     * Sets the IUCR
     * @param iucr the IUCR of the crime
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
     */
    public void setWasArrest(boolean arrest) {
        this.wasArrest = arrest;
    }

    /**
     * sets the domestic status of the crime
     * @param domestic the domestic status of the crime
     */
    public void setWasDomestic(boolean domestic) {
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
    public void setLatitude(float lat) {
        this.latitude = lat;
    }

    /**
     * sets the longitude
     * @param lon the longitude of the crime
     */
    public void setLongitude(float lon) {
        this.longitude = lon;
    }

    /**
     * sets both the latitude and longitude of the crime, and thereby the location
     * @param lat the latitude of the crime
     * @param lon the longitude of the crime
     */
    public void setLocation(float lat, float lon) {
        setLatitude(lat);
        setLongitude(lon);
    }
}