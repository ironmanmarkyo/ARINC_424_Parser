/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.airways;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class airways_dat {
    public static void SetupTable_airways(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'airways' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE airways" +
                            "(Record_type                  VARCHAR(1)," +
                            " Customer_Area_Code           VARCHAR(3)," +
                            " Section_Code                 VARCHAR(1)," +
                            " Subsection_Code              VARCHAR(1)," +
                            "Route_identifier              VARCHAR(6)," +
                            "Sequence_Number               VARCHAR(4),"+
                            "Fix_Identifier                VARCHAR(5),"+
                            "ICAO_Code                     VARCHAR(2),"+
                            " Section_Code_2               VARCHAR(1)," +
                            " Subsection_Code_2            VARCHAR(1)," +
                            "Continuation_Record_Number    VARCHAR(1),"+
                            "Waypoint_Description_Code     VARCHAR(4),"+
                            "Boundary_Code                 VARCHAR(1),"+
                            "Route_Type                    VARCHAR(1),"+
                            "Level                         VARCHAR(1),"+
                            "Driection_Restriction         VARCHAR(1),"+
                            "Cruise_Table_Indicator        VARCHAR(2),"+
                            "EU_Indicator                  VARCHAR(1),"+
                            "Recommended_NAVAID            VARCHAR(4),"+
                            "ICAO_Code_2                   VARCHAR(2),"+
                            "RNP                           VARCHAR(3),"+
                            "Theta                         VARCHAR(4),"+
                            "Rho                           VARCHAR(4),"+
                            "Outbound_Magnetic_Course      VARCHAR(4),"+
                            "Route_Distance_From           VARCHAR(4),"+
                            "Inbound_Magnetic_Course       VARCHAR(4),"+
                            "Min_Altitude                  VARCHAR(5),"+
                            "Min_Altitude_2                VARCHAR(5),"+   
                            "Max_Altitude                  VARCHAR(5),"+      
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
    
    public static void deleteTable_airways(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE airways;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_airways(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from airways;";
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
    Need to find the unique one
    maybe record no and cyckle date?
    
    public static void CleanUpTableDuplicates_airways(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "airways x " +
                    "USING airways y " +
                    "WHERE x.airways_icao_identifier = y.airways_icao_identifier " +
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
