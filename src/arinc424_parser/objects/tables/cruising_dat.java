/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class cruising_dat {
    
    public static void SetupTable_cruising(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'cruising' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE cruising" +
                    "(Record_type                  VARCHAR(1)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " Cruise_Table_Identifier      VARCHAR(2)," +
                    " Sequence_Number              VARCHAR(1)," +
                    "Course_From                   VARCHAR(4),"+
                    "Coruse_To                     VARCHAR(4),"+
                    "Mag_True                      VARCHAR(1),"+
                    "Cruise_Level_From             VARCHAR(5),"+
                    "Vertical_Separation           VARCHAR(5)," +
                    "Cruise_Level_To               VARCHAR(5)," +
                    "Cruise_Level_From_2           VARCHAR(5),"+
                    "Vertical_Separation_2         VARCHAR(5)," +
                    "Cruise_Level_To_2             VARCHAR(5)," +
                    "Cruise_Level_From_3           VARCHAR(5),"+
                    "Vertical_Separation_3         VARCHAR(5)," +
                    "Cruise_Level_To_3             VARCHAR(5)," +
                    "Cruise_Level_From_4           VARCHAR(5),"+
                    "Vertical_Separation_4         VARCHAR(5)," +
                    "Cruise_Level_To_4             VARCHAR(5)," +
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
    
    public static void deleteTable_cruising(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE cruising;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_cruising(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from cruising;";
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
    public static void CleanUpTableDuplicates_cruising(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "cruising x " +
                    "USING cruising y " +
                    "WHERE x.cruising_icao_identifier = y.cruising_icao_identifier " +
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
