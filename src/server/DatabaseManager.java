package pep.attendance.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class DatabaseManager {

    private String connUrl;
    private String connUsername;
    private String connPassword;
    private Connection conn;
    private String propertySyncFile = "./resources/sync.properties";
    private String propertyQueriesFile = "./resources/queries.properties";

    private boolean debugMode = false;

    public DatabaseManager() {
        this.connUrl = "jdbc:postgresql://localhost:5432/PEP_DB_TEST";
        this.connUsername = "marcos";
        this.connPassword = "@AlphaGuitar96";
        this.setConnection();
    }

    /**
     *
     */
    public void initDriver() {
        try {
            // Create a new instance of the postgres driver
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            if (this.debugMode)
                e.printStackTrace();
            // Do nothing because they may not be connected to the network
        }
    }

    /**
     * Connection setter. Will catch any errors when initializing the connection.
     * Ideally we want to read in credentials and URL from properties file
     */
    public void setConnection() {
        try {
            // Create a new instance of the connection
            this.conn = DriverManager.getConnection(this.connUrl, this.connUsername, this.connPassword);
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            if (this.debugMode)
                e.printStackTrace();
            // Do nothing because they may not be connected to the network
        }
    }

    /**
     * Returns the active connection
     * @return conn
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Will keep track of the different enums for the form dropdowns
     */
    public void updateProperties() {
        try {
            // Put strategy into properties object
            Properties props = new Properties();
            props.load(new FileInputStream(this.propertyQueriesFile));

            for (Map.Entry<Object, Object> e: props.entrySet()) {
                if (this.debugMode)
                    System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());

                PreparedStatement stmt = this.getConnection().prepareStatement(e.getValue().toString());

                ResultSet results = stmt.executeQuery();

                ArrayList<String> tempArr = new ArrayList<>();
                // go through each result in the set and add them to a temporary array for convenience
                tempArr.add("Choose");
                while (results.next()) {
                    tempArr.add(results.getArray(e.getKey().toString()).toString());
                }

                Properties syncProps = new Properties();
                syncProps.load(new FileInputStream(this.propertySyncFile));

                // need to joint he array elements separated by commas
                StringBuilder outStr = new StringBuilder();
                for (String aTempArr : tempArr) {
                    System.out.println(aTempArr);
                    outStr.append(aTempArr).append(",");
                }

                if (outStr.length() > 0) {
                    // Set the possible values for a specific field in the `sync.properties` file
                    syncProps.setProperty(e.getKey().toString(), outStr.substring(0, outStr.length()-1));
                } else {
                    syncProps.setProperty(e.getKey().toString(), outStr.toString());
                }

                // write the newly obtained properties back to the file
                syncProps.store(new FileOutputStream(this.propertySyncFile), null);
            }
        } catch(IOException err) {
            System.err.println(err);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager myManager = new DatabaseManager();
        myManager.updateProperties();

    }
}
