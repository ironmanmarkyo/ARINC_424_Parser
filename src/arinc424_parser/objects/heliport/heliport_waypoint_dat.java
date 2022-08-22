/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.heliport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class heliport_waypoint_dat {
    public static void SetupTable_heliport_waypoint(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'heliport_waypoint' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE heliport_waypoint" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " Heliport_Identifier          VARCHAR(4)," +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Waypoint_Identifier           VARCHAR(5),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Waypoint_Type                 VARCHAR(3),"+
                    "Waypoint_Usage                VARCHAR(2),"+
                    "Waypoint_Latitude             DECIMAL (12,6),"+     
                    "Waypoint_Longitude            DECIMAL (12,6),"+ 
                    "Dynamic_Mag_Variation         VARCHAR(5),"+
                    "Datum_Code                    VARCHAR(3),"+
                    "Name_Format_Indicator         VARCHAR(3),"+           
                    "Waypoint_Name                 VARCHAR(25),"+        
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
    
    public static void deleteTable_heliport_waypoint(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE heliport_waypoint;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_heliport_waypoint(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from heliport_waypoint;";
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
    public static void CleanUpTableDuplicates_heliport_waypoint(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "heliport_waypoint x " +
                    "USING heliport_waypoint y " +
                    "WHERE x.heliport_waypoint_icao_identifier = y.heliport_waypoint_icao_identifier " +
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
