/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.navaid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class vhf_dat {
    
    public static void SetupTable_vhf_navaid(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'vhf_navaid' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE vhf_navaid" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    "Subsection_Code               VARCHAR(1),"+
                    " Airport_ICAO_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "VOR_Identifier                VARCHAR(4),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "VOR_Frequency                 VARCHAR(5),"+
                    "NAVAID_Class                  VARCHAR(5),"+
                    "VOR_Latitude                  DECIMAL (12,6),"+
                    "VOR_Longitude                 DECIMAL (12,6),"+
                    "DME_Ident                     VARCHAR(4),"+
                    "DME_Latitude                  DECIMAL (12,6),"+
                    "DME_Longitude                 DECIMAL (12,6),"+
                    "Station_Declination           VARCHAR(5),"+
                    "DME_Elevation                 VARCHAR(5),"+        
                    "Figure_of_Merit               VARCHAR(1),"+        
                    "ILS_DME_Bias                  VARCHAR(2),"+
                    "Frequency_Protection          VARCHAR(3),"+
                    "Datum_Code                    VARCHAR(3),"+        
                    "VOR_Name                      VARCHAR(30),"+        
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
    
    public static void deleteTable_vhf_navaid(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE vhf_navaid;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_vhf_navaid(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from vhf_navaid;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_vhf_navaid(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "vhf_navaid x " +
                    "USING vhf_navaid y " +
                    "WHERE x.vor_identifier = y.vor_identifier " +
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
