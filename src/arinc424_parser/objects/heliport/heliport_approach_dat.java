/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.heliport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class heliport_approach_dat {
    
    public static void SetupTable_heliport_approach(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'heliport_approach' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE heliport_approach" +
                    "(RecordType                        VARCHAR(1),"+
                    "CustomerAreaCode                   VARCHAR(3),"+
                    "SectionCode                        VARCHAR(1),"+
                    "HeliportIdentifier                  VARCHAR(4),"+
                    "ICAOCode                           VARCHAR(2),"+
                    "SubsectionCode                     VARCHAR(1),"+
                    "ApproachIdentifier                 VARCHAR(6),"+
                    "RouteType                          VARCHAR(1),"+
                    "TransitionIdentifier               VARCHAR(5),"+
                    "MultipleCode                       VARCHAR(1),"+
                    "SequenceNumber                     VARCHAR(3),"+
                    "FixIdentifier                      VARCHAR(5),"+
                    "ICAOCode2                          VARCHAR(2),"+
                    "SectionCode2                       VARCHAR(1),"+
                    "SubSectionCode2                    VARCHAR(1),"+
                    "ContinuationNo                     VARCHAR(1),"+
                    "WaypointDescriptionCode            VARCHAR(4),"+
                    "TurnDirection                      VARCHAR(1),"+
                    "RNP                                VARCHAR(3),"+
                    "PathAndTermination                 VARCHAR(2),"+
                    "TurnDirectionValid                 VARCHAR(1),"+
                    "RecommendedNAVAID                  VARCHAR(4),"+
                    "ICAOCode3                          VARCHAR(2),"+
                    "ARCRadius                          VARCHAR(6),"+
                    "Theta                              VARCHAR(4),"+
                    "Rho                                VARCHAR(4),"+
                    "MagneticCourse                     VARCHAR(4),"+
                    "RouteDistance                      VARCHAR(4),"+
                    "RECDNAVSect                        VARCHAR(1),"+
                    "RECDNAVSubSect                     VARCHAR(1),"+
                    "AltitudeDescription                VARCHAR(1),"+
                    "ATCIndicator                       VARCHAR(1),"+
                    "Altitude                           VARCHAR(5),"+
                    "Altitude2                          VARCHAR(5),"+
                    "TransitionAltitude                 VARCHAR(5),"+
                    "SpeedLimit                         VARCHAR(3),"+
                    "VerticalAngle                      VARCHAR(4),"+
                    "CenterFix                          VARCHAR(5),"+
                    "MultipleCode_2                     VARCHAR(1),"+
                    "ICAOCode4                          VARCHAR(2),"+
                    "SectionCode3                       VARCHAR(1),"+
                    "SubSectionCode3                    VARCHAR(1),"+
                    "GPSFMSIndicator                    VARCHAR(1),"+
                    "APCHRouteQualifier1                VARCHAR(1),"+
                    "APCHRouteQualifier2                VARCHAR(1),"+       
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
    
    public static void deleteTable_heliport_approach(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE heliport_approach;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_heliport_approach(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from heliport_approach;";
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
    public static void CleanUpTableDuplicates_heliport_approach(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "heliport_approach x " +
                    "USING heliport_approach y " +
                    "WHERE x.heliport_icao_identifier = y.heliport_icao_identifier " +
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
