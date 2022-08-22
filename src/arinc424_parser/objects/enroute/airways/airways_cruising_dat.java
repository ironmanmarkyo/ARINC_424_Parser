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
public class airways_cruising_dat {
    
    
    public static void SetupTable_cruising_tr(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'cruising_tr' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE cruising_tr" +
                            "(Record_type                  VARCHAR(1)," +
                            " Customer_Area_Code           VARCHAR(3)," +
                            " Section_Code                 VARCHAR(1)," +
                            " Subsection_Code              VARCHAR(1)," +
                            "Route_identifier              VARCHAR(6)," +
                            "Restriction_Identifier        VARCHAR(3),"+
                            "Restriction_Type              VARCHAR(2),"+
                            "Continuation_Record_No        VARCHAR(1),"+
                            "Start_Fix_Identifier          VARCHAR(5)," +
                            "Start_Fix_ICAO_Code           VARCHAR(2)," +
                            "Start_Fix_Section_Code        VARCHAR(1),"+
                            "Start_Fix_Subsection_Code     VARCHAR(1),"+
                            "End_Fix_Identifier            VARCHAR(5)," +
                            "End_Fix_ICAO_Code             VARCHAR(2)," +
                            "End_Fix_Section_Code          VARCHAR(1),"+
                            "End_Fix_Subsection_Code       VARCHAR(1),"+
                            "Start_Date                    VARCHAR(7),"+
                            "End_Date                      VARCHAR(7),"+
                            "Time_Code                     VARCHAR(1),"+
                            "TIme_Indicator                VARCHAR(1),"+
                            "Time_Of_Operation_1           VARCHAR(10),"+
                            "Time_Of_Operation_2           VARCHAR(10),"+
                            "Time_Of_Operation_3           VARCHAR(10),"+
                            "Time_Of_Operation_4           VARCHAR(10),"+
                            "Cruise_Table_Ident            VARCHAR(2),"+
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
    
    public static void deleteTable_cruising_tr(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE cruising_tr;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_cruising_tr(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from cruising_tr;";
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
    
    public static void CleanUpTableDuplicates_cruising_tr(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "cruising_table_replacement x " +
                    "USING cruising_table_replacement y " +
                    "WHERE x.cruising_table_replacement_icao_identifier = y.cruising_table_replacement_icao_identifier " +
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
