/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.MORA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class mora_dat {
     public static void SetupTable_mora(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'mora' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE mora" +
                            "(Record_type                  VARCHAR(1)," +
                            " Section_Code                 VARCHAR(1), " +
                            "Subsection_Code               VARCHAR(1),"+
                            "Starting_Latitude                  DECIMAL (12,6),"+
                            "Starting_Longitude                 DECIMAL (12,6),"+
                            "MORA_1                        VARCHAR(3),"+
                            "MORA_2                        VARCHAR(3),"+
                            "MORA_3                        VARCHAR(3),"+
                            "MORA_4                        VARCHAR(3),"+
                            "MORA_5                        VARCHAR(3),"+
                            "MORA_6                        VARCHAR(3),"+
                            "MORA_7                        VARCHAR(3),"+
                            "MORA_8                        VARCHAR(3),"+
                            "MORA_9                        VARCHAR(3),"+
                            "MORA_10                        VARCHAR(3),"+
                            "MORA_11                        VARCHAR(3),"+
                            "MORA_12                        VARCHAR(3),"+
                            "MORA_13                        VARCHAR(3),"+
                            "MORA_14                        VARCHAR(3),"+
                            "MORA_15                        VARCHAR(3),"+
                            "MORA_16                        VARCHAR(3),"+
                            "MORA_17                        VARCHAR(3),"+
                            "MORA_18                        VARCHAR(3),"+
                            "MORA_19                        VARCHAR(3),"+
                            "MORA_20                        VARCHAR(3),"+
                            "MORA_21                        VARCHAR(3),"+
                            "MORA_22                        VARCHAR(3),"+
                            "MORA_23                        VARCHAR(3),"+
                            "MORA_24                        VARCHAR(3),"+
                            "MORA_25                        VARCHAR(3),"+
                            "MORA_26                        VARCHAR(3),"+
                            "MORA_27                        VARCHAR(3),"+
                            "MORA_28                        VARCHAR(3),"+
                            "MORA_29                        VARCHAR(3),"+
                            "MORA_30                        VARCHAR(3),"+
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
    
    public static void deleteTable_mora(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE mora;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_mora(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from mora;";
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
    public static void CleanUpTableDuplicates_mora(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "mora x " +
                    "USING mora y " +
                    "WHERE x.ndb_identifier = y.ndb_identifier " +
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
