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
public class airport_ndb_dat {
    
    public static void SetupTable_airport_ndb(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'airport_ndb' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE airport_ndb" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3), " +
                    " Section_Code                 VARCHAR(1), " +
                    "Subsection_Code               VARCHAR(1),"+
                    " Airport_ICAO_Identifier      VARCHAR(4), " +
                    "ICAO_Code                     VARCHAR(2),"+
                    "NDB_Identifier                VARCHAR(4),"+
                    "ICAO_Code2                    VARCHAR(2),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "NDB_Frequency                 VARCHAR(5),"+
                    "NDB_Class                     VARCHAR(5),"+
                    "NDB_Latitude                  DECIMAL (12,6),"+
                    "NDB_Longitude                 DECIMAL (12,6),"+
                    "Magnetic_Variation            VARCHAR(5),"+
                    "Datum_Code                    VARCHAR(3),"+        
                    "NDB_Name                      VARCHAR(30),"+        
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
    
    public static void deleteTable_airport_ndb(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE airport_ndb;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_airport_ndb(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from airport_ndb;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_airport_ndb(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "airport_ndb x " +
                    "USING airport_ndb y " +
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
}
