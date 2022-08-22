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
public class localizer_glide_slope_dat {
    public static void SetupTable_localizer_glide_slope(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'localizer_glide_slope' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE localizer_glide_slope" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Localizer_Identifier          VARCHAR(4),"+
                    "ILS_Category                  VARCHAR(1),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Localizer_Frequency                 VARCHAR(5),"+
                    "Runway_Identifier      VARCHAR(5),"+
                    "Localizer_Latitude               DECIMAL (12,6),"+     
                    "Localizer_Longitude              DECIMAL (12,6),"+  
                    "Localizer_Bearing               VARCHAR(5),"+
                    "GlideSlope_Latitude               DECIMAL (12,6),"+     
                    "GlideSlope_Longitude              DECIMAL (12,6),"+
                    "Localizer_Position          VARCHAR(4),"+
                    "Localizer_Postion_Ref                 VARCHAR(1),"+
                    "GlideSlope_Postion           VARCHAR(4),"+
                    "Localizer_Width                VARCHAR(4),"+
                    "GlideSlope_Angle                       VARCHAR(3),"+
                    "Station_Declination            VARCHAR(5),"+
                    "GlideSlope_Height              VARCHAR(2),"+
                    "GlideSlope_Elevation                VARCHAR(5),"+        
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
    
    public static void deleteTable_localizer_glide_slope(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE localizer_glide_slope;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_localizer_glide_slope(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from localizer_glide_slope;";
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
    public static void CleanUpTableDuplicates_localizer_glide_slope(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "localizer_glide_slope x " +
                    "USING localizer_glide_slope y " +
                    "WHERE x.localizer_glide_slope_identifier = y.localizer_glide_slope_identifier " +
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
