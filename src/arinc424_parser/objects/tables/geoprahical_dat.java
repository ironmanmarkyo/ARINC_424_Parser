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
public class geoprahical_dat {
    public static void SetupTable_geographical(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'geographical' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE geographical" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " Geographical_Table_Ref_ID    VARCHAR(2)," +
                    " Sequence_Number              VARCHAR(1)," +
                    "Geographical_Entity           VARCHAR(29),"+
                    "Continuation_Record_No        VARCHAR(1),"+
                    "Preferred_Route_Ident_1       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_1      VARCHAR(2)," +
                    "Preferred_Route_Ident_2       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_2      VARCHAR(2)," +
                    "Preferred_Route_Ident_3       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_3      VARCHAR(2)," +
                    "Preferred_Route_Ident_4       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_4      VARCHAR(2)," +
                    "Preferred_Route_Ident_5       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_5      VARCHAR(2)," +
                    "Preferred_Route_Ident_6       VARCHAR(10),"+
                    "Preferred_Route_Use_Indi_6      VARCHAR(2)," +
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
    
    public static void deleteTable_geographical(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE geographical;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_geographical(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from geographical;";
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
    public static void CleanUpTableDuplicates_geographical(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "geographical x " +
                    "USING geographical y " +
                    "WHERE x.geographical_icao_identifier = y.geographical_icao_identifier " +
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
