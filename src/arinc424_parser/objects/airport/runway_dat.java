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
public class runway_dat {
    
    public static void SetupTable_runway(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'runway' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE runway" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    " Airport_ICAO_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Subsection_Code               VARCHAR(1),"+
                    "Runway_Identifier             VARCHAR(5),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Runway_Length                 VARCHAR(5),"+
                    "Runway_Magnetic_Bearing       VARCHAR(4),"+
                    "Runway_Latitude               DECIMAL (12,6),"+     
                    "Runway_Longitude              DECIMAL (12,6),"+  
                    "Runway_Gradient               VARCHAR(5),"+
                    "Landing_Threshold_Elevation   VARCHAR(5),"+        
                    "Displaced_Threshold_Elevation VARCHAR(4),"+        
                    "Threshold_Crossing_Height     VARCHAR(2),"+
                    "Runway_Width                  VARCHAR(3),"+
                    "Localizer_MLS_GLS_Identifier  VARCHAR(4),"+
                    "Localizer_MLS_GLS_Category    VARCHAR(1),"+
                    "Stopway                       VARCHAR(4),"+
                    "Localizer_MLS_GLS_Identifier_2    VARCHAR(4),"+
                    "Localizer_MLS_GLS_Category_2      VARCHAR(1),"+
                    "Runway_description                VARCHAR(22),"+        
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
    
    public static void deleteTable_runway(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE runway;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_runway(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from runway;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_runway(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "runway x " +
                    "USING runway y " +
                    "WHERE x.runway_identifier = y.runway_identifier " +
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
