/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airspace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class restricted_dat {
    
    
    
    public static void SetupTable_restricted_airspace(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'restricted_airspace' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE restricted_airspace" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " ICAO_Code                    VARCHAR(2)," +
                    " Restrictive_Type             VARCHAR(1)," +
                    " Restrictive_Airspace_Designation         VARCHAR(10)," +
                    " Multiple_Code                VARCHAR(1)," +
                    " Sequence_Number              VARCHAR(4)," +
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Level                         VARCHAR(1),"+
                    "Time_Code                     VARCHAR(1),"+
                    "NOTAM                         VARCHAR(1),"+
                    "Boundary_Via                  VARCHAR(2),"+
                    "Latitude                      DECIMAL(12,6),"+
                    "Longitude                     DECIMAL(12,6),"+
                    "Arc_Latitude                  DECIMAL(12,6),"+
                    "Arc_Longitude                 DECIMAL(12,6),"+
                    "Arc_Distance                  VARCHAR(4)," +
                    "Arc_Bearing                   VARCHAR(4)," +
                    "Lower_Limit                   VARCHAR(5),"+
                    "Unit_Indicator_1              VARCHAR(1)," +
                    "Upper_Limit                   VARCHAR(5)," +
                    "Unit_Indicator_2              VARCHAR(1)," +
                    "Restricted_Airspace_Name      VARCHAR(30),"+
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
    
    public static void deleteTable_restricted_airspace(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE restricted_airspace;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_restricted_airspace(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from restricted_airspace;";
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
    public static void CleanUpTableDuplicates_restricted_airspace(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "restricted_airspace x " +
                    "USING restricted_airspace y " +
                    "WHERE x.restricted_airspace_icao_identifier = y.restricted_airspace_icao_identifier " +
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
