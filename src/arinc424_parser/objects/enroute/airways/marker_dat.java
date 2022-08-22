/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.airways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class marker_dat {
    
    public static void SetupTable_marker(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'marker' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE marker" +
                            "(Record_type                  VARCHAR(1)," +
                            " Customer_Area_Code           VARCHAR(3)," +
                            " Section_Code                 VARCHAR(1)," +
                            " Subsection_Code              VARCHAR(1)," +
                            "Marker_identifier             VARCHAR(4)," +
                            "ICAO_Code                     VARCHAR(2),"+
                            "Continuation_Record_Number    VARCHAR(1),"+
                            "Marker_Code                   VARCHAR(4),"+
                            "Marker_Shape                  VARCHAR(1),"+
                            "Marker_Power                  VARCHAR(1),"+
                            "Marker_Latitude               DECIMAL(12,6),"+
                            "Marker_Longitude              DECIMAL(12,6),"+
                            "Minor_Axis                    VARCHAR(4),"+
                            "Magnetic_Variation            VARCHAR(5),"+
                            "Facility_Elevation            VARCHAR(5),"+
                            "Datum_Code                    VARCHAR(3),"+
                            "Marker_Name                   VARCHAR(30),"+
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
    
    public static void deleteTable_marker(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE marker;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_marker(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from marker;";
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
    Need to find the unique one
    maybe record no and cyckle date?
    
    public static void CleanUpTableDuplicates_marker(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "marker x " +
                    "USING marker y " +
                    "WHERE x.marker_icao_identifier = y.marker_icao_identifier " +
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
