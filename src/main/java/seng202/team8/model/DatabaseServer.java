package seng202.team8.model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class DatabaseServer {

    /**
     * Connects Java to the oracle database
     */
    public Connection databaseConnection() {
        Connection connection = null;
        try{
            // loading the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // connecting to the database
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","","");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Creates a new crime record table
     * @param tableName crime data table name
     */
    public void createCrimeRecordTable(String tableName) {
        try (Connection connection = databaseConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (CaseNum varchar(50) primary key unique, " +
                    "Date_Of_Occurrence varchar(50), Block varchar(100), IUCR varchar(30), Primary_Desc varchar(150), " +
                    "Secondary_Desc varchar(150), Location_Desc varchar(100), Arrest int, Domestic int, Beat int," +
                    "Ward int, Fbi_Cd varchar(100), Latitude number, Longitude number)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new crime data into the specified table
     * @param crimeRecord a new crime data for insertion
     * @param tableName crime data table name
     */
    public void insertCrimeData(CrimeRecord crimeRecord, String tableName) {
        // protects from SQL injection attacks

        try (Connection connection = databaseConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO " + tableName + " (CaseNum, Date_Of_Occurrence, " +
                "Block, IUCR, Primary_Desc, Secondary_Desc, Location_Desc, Arrest, Domestic, Beat, Ward, Fbi_Cd, " +
                "Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, crimeRecord.getCaseNum());
            preparedStatement.setString(2, crimeRecord.getTimeOfCrime());
            preparedStatement.setString(3, crimeRecord.getBlock());
            preparedStatement.setString(4, crimeRecord.getIucr());
            preparedStatement.setString(5, crimeRecord.getPrimary());
            preparedStatement.setString(6, crimeRecord.getSecondary());
            preparedStatement.setString(7, crimeRecord.getLocDescription());
            preparedStatement.setInt(8, crimeRecord.getWasArrest());
            preparedStatement.setInt(9, crimeRecord.getWasDomestic());
            preparedStatement.setInt(10, crimeRecord.getBeat());
            preparedStatement.setInt(11, crimeRecord.getWard());
            preparedStatement.setString(12, crimeRecord.getFbiCD());
            preparedStatement.setDouble(13, crimeRecord.getLatitude());
            preparedStatement.setDouble(14, crimeRecord.getLongitude());
            preparedStatement.executeUpdate();

            System.out.println("INSERT INTO Crime_Record (CaseNum, Date_Of_Occurrence, " +
                    "Block, IUCR, Primary_Desc, Secondary_Desc, Location_Desc, Arrest, Domestic, Beat, Ward, Fbi_Cd, " +
                    "Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Converts all the table data to array list and returns it
     * @param tableName crime data table name
     * @return the array list of crime record data
     */
    public ArrayList<CrimeRecord> getAllCrimeData(String tableName) {
        ArrayList<CrimeRecord> crimeRecordList = new ArrayList<>();

        try (Connection connection = databaseConnection(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            while (resultSet.next()) {
                CrimeRecord crimeRecord = new CrimeRecord();
                crimeRecord.setCaseNum(resultSet.getString("CaseNum"));
                crimeRecord.setTimeOfCrime(resultSet.getString("Date_Of_Occurrence"));
                crimeRecord.setBlock(resultSet.getString("Block"));
                crimeRecord.setIucr(resultSet.getString("IUCR"));
                crimeRecord.setPrimary(resultSet.getString("Primary_Desc"));
                crimeRecord.setSecondary(resultSet.getString("Secondary_Desc"));
                crimeRecord.setLocDescription(resultSet.getString("Location_Desc"));
                crimeRecord.setWasArrest(resultSet.getInt("Arrest"));
                crimeRecord.setWasDomestic(resultSet.getInt("Domestic"));
                crimeRecord.setBeat(resultSet.getInt("Beat"));
                crimeRecord.setWard(resultSet.getInt("Ward"));
                crimeRecord.setFbiCD(resultSet.getString("Fbi_Cd"));
                crimeRecord.setLatitude(resultSet.getDouble("Latitude"));
                crimeRecord.setLongitude(resultSet.getDouble("Longitude"));

                crimeRecordList.add(crimeRecord);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return crimeRecordList;
    }

    /**
     * Deletes the selected crime data row from the table
     * @param caseNumber unique crime case number
     * @param tableName crime data table name
     */
    public void deleteCrimeData(String caseNumber, String tableName) {

        try (Connection connection = databaseConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM " + tableName + " WHERE CaseNum = ?")) {
            preparedStatement.setString(1, caseNumber);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM Crime_Record WHERE CaseNum = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the crime data at the given case number
     * @param crimeRecord new crime data to add
     * @param tableName crime data table name
     * @param caseNumber unique crime case number
     */
    public void updateCrimeData(CrimeRecord crimeRecord, String tableName, String caseNumber) {

        try (Connection connection = databaseConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE " + tableName + " SET CaseNum = ?, Date_Of_Occurrence" +
                " = ?, Block = ?, IUCR = ?, Primary_Desc = ?, Secondary_Desc = ?, Location_Desc = ?, Arrest = ?, " +
                "Domestic = ?, Beat = ?, Ward = ?, Fbi_Cd = ?, Latitude = ?, Longitude = ? WHERE CaseNum = ?")) {
            preparedStatement.setString(1, crimeRecord.getCaseNum());
            preparedStatement.setString(2, crimeRecord.getTimeOfCrime());
            preparedStatement.setString(3, crimeRecord.getBlock());
            preparedStatement.setString(4, crimeRecord.getIucr());
            preparedStatement.setString(5, crimeRecord.getPrimary());
            preparedStatement.setString(6, crimeRecord.getSecondary());
            preparedStatement.setString(7, crimeRecord.getLocDescription());
            preparedStatement.setInt(8, crimeRecord.getWasArrest());
            preparedStatement.setInt(9, crimeRecord.getWasDomestic());
            preparedStatement.setInt(10, crimeRecord.getBeat());
            preparedStatement.setInt(11, crimeRecord.getWard());
            preparedStatement.setString(12, crimeRecord.getFbiCD());
            preparedStatement.setDouble(13, crimeRecord.getLatitude());
            preparedStatement.setDouble(14, crimeRecord.getLongitude());
            preparedStatement.executeUpdate();

            System.out.println("UPDATE Crime_Record SET CaseNum = ?, Date_Of_Occurrence" +
                    " = ?, Block = ?, IUCR = ?, Primary_Desc = ?, Secondary_Desc = ?, Location_Desc = ?, Arrest = ?, " +
                    "Domestic = ?, Beat = ?, Ward = ?, Fbi_Cd = ?, Latitude = ?, Longitude = ? WHERE CaseNum = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //DatabaseServer db = new DatabaseServer();

        // (1) Check for whether it creates the table
        //db.createCrimeRecordTable();

        // (2) Check for insert
        //CrimeRecord crimeRecord = new CrimeRecord(//data here);
        //db.insertCrimeData(crimeRecord);

        // (3) Testing to get all data
        //ArrayList<CrimeRecord> allData = db.getAllCrimeData();
        //for (CrimeRecord crimeData : allData) {
        //    System.out.println(crimeData.getCaseNum() + ", " + crimeData.getTimeOfCrime());
        //}

        // (4) Delete method check
        // db.deleteCrimeData("JKL1223");

        // (5) Update method check
        //CrimeRecord newData = new CrimeRecord(// data here);
        //db.updateCrimeData(newData, 1);
        // Check the changes
        // ArrayList<CrimeRecord> allData1 = db.getAllCrimeData();
        //for (CrimeRecord crimeData : allData1) {
        //    System.out.println(crimeData.getCaseNum() + ", " + crimeData.getTimeOfCrime());
        //}

    }

}
