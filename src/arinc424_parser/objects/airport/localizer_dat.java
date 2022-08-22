/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class localizer_dat {
    
    public static void SetupTable_localizer(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'localizer' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE localizer" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Localizer_Identifier          VARCHAR(4),"+
                    "Marker_Type                  VARCHAR(3),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Locator_Frequency                 VARCHAR(5),"+
                    "Runway_Identifier      VARCHAR(5),"+
                    "Marker_Latitude               DECIMAL (12,6),"+     
                    "Marker_Longitude              DECIMAL (12,6),"+  
                    "Minor_Axis_Bearing               VARCHAR(5),"+
                    "Locator_Latitude               DECIMAL (12,6),"+     
                    "Locator_Longitude              DECIMAL (12,6),"+
                    "Locator_Class         VARCHAR(5),"+
                    "Locator_Facility_Characterstics                 VARCHAR(5),"+
                    "Locator_Identifier           VARCHAR(4),"+
                    "Magnetic_Variation                VARCHAR(5),"+
                    "Facility_Elevation                      VARCHAR(5),"+
                    "File_Record_Number            INT,"+           
                    "Cycle_Date         INT);";
//                System.out.print(sql);
                stmt.executeUpdate(sql);
           }
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void deleteTable_localizer(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE localizer;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_localizer(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from localizer;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    /*
    public static void CleanUpTableDuplicates_localizer(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "localizer x " +
                    "USING localizer y " +
                    "WHERE x.localizer_identifier = y.localizer_identifier " +
                    "AND x.ctid < y.ctid;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    */
}
