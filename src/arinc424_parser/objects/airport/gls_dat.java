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
public class gls_dat {
    
    
    public static void SetupTable_gls(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'gls' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE gls" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_Heliport_Identifier  VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "GLS_Ref_Path_Identifier       VARCHAR(4),"+
                    "GLS_Category                  VARCHAR(1),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "GLS_Channel                   VARCHAR(5),"+
                    "Runway_Identifier             VARCHAR(5),"+
                    "GLS_Approach_Bearing          VARCHAR(4),"+
                    "Station_Latitude              DECIMAL (12,6),"+     
                    "Station_Longitude             DECIMAL (12,6),"+  
                    "GLS_Station_Ident             VARCHAR(4),"+
                    "Service_Volume_Radius         VARCHAR(2),"+        
                    "TDMA_Slots                    VARCHAR(2),"+        
                    "GLS_Appraoch_Slope            VARCHAR(3),"+
                    "Magnetic_Variation            VARCHAR(5),"+
                    "Station_Elevation             VARCHAR(5),"+
                    "Datum_Code                    VARCHAR(3),"+
                    "Station_Type                  VARCHAR(3),"+
                    "Station_Elevation_WGS_84      VARCHAR(5),"+    
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
    
    public static void deleteTable_gls(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE gls;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_gls(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from gls;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_gls(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "gls x " +
                    "USING gls y " +
                    "WHERE x.gls_identifier = y.gls_identifier " +
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
