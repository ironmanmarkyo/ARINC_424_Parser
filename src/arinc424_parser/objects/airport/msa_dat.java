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
public class msa_dat {
    
    public static void SetupTable_msa(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'msa' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE msa" +
                    "(RecordType                        VARCHAR(1),"+
                    "CustomerAreaCode                   VARCHAR(3),"+
                    "SectionCode                        VARCHAR(1),"+
                    "AirportIdentifier                  VARCHAR(4),"+
                    "ICAOCode                           VARCHAR(2),"+
                    "SubsectionCode                     VARCHAR(1),"+
                    "MSA_Center                         VARCHAR(5),"+
                    "ICAO_Code_2                        VARCHAR(2),"+
                    "SectionCode_2                      VARCHAR(1),"+
                    "SubSectionCode_2                   VARCHAR(1),"+
                    "Multiple_Code                      VARCHAR(1),"+
                    "ContinuationNo                     VARCHAR(1),"+
                    "Radius_Limit                       VARCHAR(2),"+
                    "Sector_Bearing_1                   VARCHAR(3),"+
                    "Sector_Altitude_1                  VARCHAR(3),"+
                    "Sector_Bearing_2                   VARCHAR(3),"+
                    "Sector_Altitude_2                  VARCHAR(3),"+
                    "Sector_Bearing_3                   VARCHAR(3),"+
                    "Sector_Altitude_3                  VARCHAR(3),"+
                    "Sector_Bearing_4                   VARCHAR(3),"+
                    "Sector_Altitude_4                  VARCHAR(3),"+
                    "Sector_Bearing_5                   VARCHAR(3),"+
                    "Sector_Altitude_5                  VARCHAR(3),"+
                    "Magnetic_True_Indicator            VARCHAR(1),"+       
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
    
    public static void deleteTable_msa(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE msa;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_msa(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from msa;";
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
    to find the unqiue one
    public static void CleanUpTableDuplicates_msa(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "msa x " +
                    "USING msa y " +
                    "WHERE x.airport_icao_identifier = y.airport_icao_identifier " +
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
