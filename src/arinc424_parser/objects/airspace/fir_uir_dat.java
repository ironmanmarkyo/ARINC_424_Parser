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
public class fir_uir_dat {
    
    
    public static void SetupTable_fir_uir(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'fir_uir' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE fir_uir" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " FIR_UIR_Identifier           VARCHAR(4)," +
                    " FIR_UIR_Address              VARCHAR(4)," +
                    " FIR_UIR_Indicator            VARCHAR(1)," +
                    " Sequence_Number              VARCHAR(4)," +
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "Adjacent_FIR_Identifier       VARCHAR(4),"+
                    "Adjacent_UIR_Identifier       VARCHAR(4),"+
                    "Reporting_Units_Speed         VARCHAR(1),"+
                    "Reporting_Units_Altitude      VARCHAR(1)," +
                    "Entry_Report                  VARCHAR(1)," +
                    "Boundary_Via                  VARCHAR(2),"+
                    "FIR_UIR_Latitude              DECIMAL(12,6),"+
                    "FIR_UIR_Longitude             DECIMAL(12,6),"+
                    "Arc_Latitude                  DECIMAL(12,6),"+
                    "Arc_Longitude                 DECIMAL(12,6),"+
                    "Arc_Distance                  VARCHAR(4)," +
                    "Arc_Bearing                   VARCHAR(4)," +
                    "FIR_Upper_Limit               VARCHAR(5),"+
                    "UIR_Lower_Limit               VARCHAR(5)," +
                    "UIR_Upper_Limit               VARCHAR(5)," +
                    "Cruise_Table_Ind              VARCHAR(2)," +
                    "FIR_UIR_Name                  VARCHAR(25),"+
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
    
    public static void deleteTable_fir_uir(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE fir_uir;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_fir_uir(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from fir_uir;";
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
    public static void CleanUpTableDuplicates_fir_uir(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "fir_uir x " +
                    "USING fir_uir y " +
                    "WHERE x.fir_uir_icao_identifier = y.fir_uir_icao_identifier " +
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