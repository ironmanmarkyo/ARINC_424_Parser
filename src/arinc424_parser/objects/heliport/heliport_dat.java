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
public class heliport_dat {
    
    
    public static void SetupTable_heliport(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'heliport' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE heliport" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Heliport_ICAO_Identifier     VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Ata_Iata_Designator           VARCHAR(3),"+
                    "PAD_Identifier                VARCHAR(5),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Speed_Limit_Altitude          VARCHAR(5),"+
                    "Datum_Code                    VARCHAR(3),"+
//                    "Longest_Runway                VARCHAR(3),"+
                    "IFR_Indicator                 VARCHAR(1),"+
//                    "Longest_Runway_Surface_Code   VARCHAR(1),"+
                    "Heliport_Latitude             DECIMAL (12,6),"+     
                    "Heliport_Longitude            DECIMAL (12,6),"+  
                    "Magnetic_Variation            VARCHAR(5),"+
                    "Heliport_Elevation            INT,"+        
                    "Speed_Limit                   INT,"+        
                    "Recommended_Navaid            VARCHAR(4),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    "Transition_Altitude           INT,"+
                    "Transition_Level              INT,"+
                    "Public_Military_Indicator     VARCHAR(1),"+
                    "Time_Zone                     VARCHAR(3),"+
                    "Day_Light_Indicator           VARCHAR(1),"+
                    "PAD_Dimensions                VARCHAR(6),"+
                    "Magnetic_True_Indicator       VARCHAR(1),"+
                    "Heliport_Name                  VARCHAR(30),"+        
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
    
    public static void deleteTable_heliport(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE heliport;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_heliport(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from heliport;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_heliport(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "heliport x " +
                    "USING heliport y " +
                    "WHERE x.heliport_icao_identifier = y.heliport_icao_identifier " +
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
