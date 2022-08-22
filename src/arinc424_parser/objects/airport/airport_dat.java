/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author yohan
 */
public class airport_dat {
    
    public static void SetupTable_airport(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'airport' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE airport" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_ICAO_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Ata_Iata_Designator           VARCHAR(3),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Speed_Limit_Altitude          VARCHAR(5),"+
                    "Longest_Runway                VARCHAR(3),"+
                    "IFR_Capability                VARCHAR(1),"+
                    "Longest_Runway_Surface_Code   VARCHAR(1),"+
                    "Airport_Reference_Point_Latitude DECIMAL (12,6),"+     
                    "Airport_Reference_Point_Longitude DECIMAL (12,6),"+  
                    "Magnetic_Variation            VARCHAR(5),"+
                    "Airport_Elevation             INT,"+        
                    "Speed_Limit                   INT,"+        
                    "Recommended_Navaid            VARCHAR(4),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    "Transition_Altitude           INT,"+
                    "Transition_Level              INT,"+
                    "Public_Military_Indicator     VARCHAR(1),"+
                    "Time_Zone                     VARCHAR(3),"+
                    "Day_Light_Indicator           VARCHAR(1),"+
                    "Magnetic_True_Indicator       VARCHAR(1),"+
                    "Datum_Code                    VARCHAR(3),"+        
                    "Airport_Name                  VARCHAR(30),"+        
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
    
    public static void deleteTable_airport(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE airport;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_airport(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from airport;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_airport(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "airport x " +
                    "USING airport y " +
                    "WHERE x.airport_icao_identifier = y.airport_icao_identifier " +
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
    
}
