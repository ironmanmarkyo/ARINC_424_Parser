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
public class mls_dat {
    public static void SetupTable_mls(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'mls' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE mls" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_Identifier           VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "MLS_Identifier                VARCHAR(4),"+
                    "MLS_Category                  VARCHAR(1),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Channel                       VARCHAR(5),"+
                    "Runway_Identifier             VARCHAR(5),"+
                    "Azimuth_Latitude              DECIMAL (12,6),"+     
                    "Azimuth_Longitude             DECIMAL (12,6),"+  
                    "Azimuth_Bearing               VARCHAR(5),"+
                    "Elevation_Latitude            DECIMAL (12,6),"+     
                    "Elevation_Longitude           DECIMAL (12,6),"+
                    "Azimuth_Position              VARCHAR(4),"+
                    "Azimuth_Postion_Ref           VARCHAR(1),"+
                    "Elevation_Position            VARCHAR(4),"+
                    "Azimuth_Proportional_Angle_Right           VARCHAR(3),"+
                    "Azimuth_Proportional_Angle_Left            VARCHAR(3),"+
                    "Azimuth_Coverage_Right        VARCHAR(3),"+
                    "Azimuth_Coverage_Left         VARCHAR(3),"+
                    "Elevation_Angle_Span          VARCHAR(3),"+
                    "Magnetic_Variation            VARCHAR(5),"+
                    "EL_Elevation                  VARCHAR(5),"+
                    "Nominal_Elevation_Angle       VARCHAR(4),"+  
                    "Minimum_Glide_Path_Angle      VARCHAR(3),"+       
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
    
    public static void deleteTable_mls(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE mls;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_mls(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from mls;";
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
    public static void CleanUpTableDuplicates_mls(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "mls x " +
                    "USING mls y " +
                    "WHERE x.mls_identifier = y.mls_identifier " +
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
