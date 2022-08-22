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
public class path_point_dat {
    public static void SetupTable_path_point(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'path_point' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE path_point" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_Identifier           VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Approach_Identifier           VARCHAR(6),"+
                    "Runway_Helipad_Identifier     VARCHAR(5),"+
                    "Operation_Type                VARCHAR(2),"+
                    "Approach_Indicator            VARCHAR(1),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Landing_Threshold_Point_Latitude               DECIMAL (12,6),"+     
                    "Landing_Threshold_Point_Longitude              DECIMAL (12,6),"+  
                    "WGS_84_Ellipsoid_Height_1     VARCHAR(6),"+
                    "Threshold_Crossing_Height     VARCHAR(6),"+
                    "Glide_Path_Angle              VARCHAR(4),"+
                    "Flight_Path_ALignment_Latitude               DECIMAL (12,6),"+     
                    "Flight_Path_ALignment_Longitude              DECIMAL (12,6),"+
                    "WGS_84_Ellipsoid_Height_2     VARCHAR(6),"+
                    "Orthometric_Height_1          VARCHAR(6),"+
                    "Orthometric_Height_2          VARCHAR(6),"+
                    "Unit_Of_Height                VARCHAR(1),"+
                    "Path_Point_Data_CRC           VARCHAR(8),"+
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
    
    public static void deleteTable_path_point(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE path_point;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_path_point(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from path_point;";
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
    public static void CleanUpTableDuplicates_path_point(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "path_point x " +
                    "USING path_point y " +
                    "WHERE x.path_point_identifier = y.path_point_identifier " +
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
