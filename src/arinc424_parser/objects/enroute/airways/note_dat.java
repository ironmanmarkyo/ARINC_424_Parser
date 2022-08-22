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
public class note_dat {
    
    
    public static void SetupTable_note_restriction(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'note_restriction' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE note_restriction" +
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
                            "Restriction_Notes             VARCHAR(69),"+
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
    
    public static void deleteTable_note_restriction(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE note_restriction;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_note_restriction(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from note_restriction;";
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
    
    public static void CleanUpTableDuplicates_note_restriction(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "note_restriction x " +
                    "USING note_restriction y " +
                    "WHERE x.note_restriction_icao_identifier = y.note_restriction_icao_identifier " +
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
