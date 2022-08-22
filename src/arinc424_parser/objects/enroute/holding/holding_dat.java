/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.holding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class holding_dat {
    public static void SetupTable_holding(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'holding' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE holding" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " Region_Code                  VARCHAR(4)," +
                    "ICAO_Code                     VARCHAR(2),"+
                    "Duplicate_Identifier          VARCHAR(2),"+
                    "Fix_Identifier                VARCHAR(5),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    " Section_Code_2               VARCHAR(1)," +
                    " Subsection_Code_2            VARCHAR(1)," +
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Inbound_Holding_Course        VARCHAR(4),"+
                    "Turn_Directiom                VARCHAR(1),"+
                    "Leg_Length                    VARCHAR(3),"+
                    "Leg_Time                      VARCHAR(2),"+
                    "Min_Altitude                  VARCHAR(5),"+
                    "Max_Alitude                   VARCHAR(5),"+
                    "Holding_Speed                 VARCHAR(3),"+        
                    "Notes                         VARCHAR(25),"+        
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
    
    public static void deleteTable_holding(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE holding;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_holding(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from holding;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_holding(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "holding x " +
                    "USING holding y " +
                    "WHERE x.holding_icao_identifier = y.holding_icao_identifier " +
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
