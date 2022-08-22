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
public class airport_communications_dat {
    
    public static void SetupTable_communications(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'communications' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE communications" +
                    "(Record_type                  VARCHAR(1)," +
                    "Customer_Area_Code VARCHAR(3),"+
                            "Section_Code VARCHAR(1),"+
                            "Airport_Identifier VARCHAR(4),"+
                            "ICAO_Code VARCHAR(2),"+
                            "Subsection_Code VARCHAR(1),"+
                            "Communications_Type VARCHAR(3),"+
                            "Communications_Frequency VARCHAR(7),"+
                            "Guard_Transmit VARCHAR(1),"+
                            "Frequency_Units VARCHAR(1),"+
                            "Continuation_Record_No VARCHAR(1),"+
                            "Service_Indicator VARCHAR(3),"+
                            "Radar_Service VARCHAR(1),"+
                            "Modulation VARCHAR(1),"+
                            "Signal_Emission VARCHAR(1),"+
                            "Latitude DECIMAL (12,6),"+
                            "Longitude DECIMAL (12,6),"+
                            "Magnetic_Variation VARCHAR(5),"+
                            "Facility_Elevation VARCHAR(5),"+
                            "H24_Indicator VARCHAR(1),"+
                            "Sectorization VARCHAR(6),"+
                            "Altitude_Description VARCHAR(1),"+
                            "Communication_Altitude VARCHAR(5),"+
                            "Communication_Altitude_2 VARCHAR(5),"+
                            "Sector_Facility VARCHAR(4),"+
                            "ICAO_Code_2 VARCHAR(2),"+
                            "Section_Code_2 VARCHAR(1),"+
                            "SubSection_Code_2 VARCHAR(1),"+
                            "Distance_Description VARCHAR(1),"+
                            "Communications_Distance VARCHAR(2),"+
                            "Remote_Facility VARCHAR(4),"+
                            "ICAO_Code_3 VARCHAR(2),"+
                            "Section_Code_3 VARCHAR(1),"+
                            "SubSection_Code_3 VARCHAR(1),"+
                            "Call_Sign VARCHAR(25),"+
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
    
    public static void deleteTable_communications(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE communications;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_communications(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from communications;";
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
    public static void CleanUpTableDuplicates_communications(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "communications x " +
                    "USING communications y " +
                    "WHERE x.communications_icao_identifier = y.communications_icao_identifier " +
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

    
