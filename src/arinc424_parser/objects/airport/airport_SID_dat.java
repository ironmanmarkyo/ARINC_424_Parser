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
public class airport_SID_dat {
    public static void SetupTable_airport_SID(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'airport_sid' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE airport_sid" +
                    "(RecordType                        VARCHAR(1),"+
                    "CustomerAreaCode                   VARCHAR(3),"+
                    "SectionCode                        VARCHAR(1),"+
                    "AirportIdentifier                  VARCHAR(4),"+
                    "ICAOCode                           VARCHAR(2),"+
                    "SubsectionCode                     VARCHAR(1),"+
                    "SIDIdentifier                      VARCHAR(6),"+
                    "RouteType                          VARCHAR(1),"+
                    "TransitionIdentifier               VARCHAR(5),"+
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
                    "CenterFIx                          VARCHAR(5),"+
                    "MultipleCode                       VARCHAR(1),"+
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
    
    public static void deleteTable_airport_SID(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE airport_SID;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_airport_SID(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from airport_SID;";
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
    public static void CleanUpTableDuplicates_airport_SID(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "airport_SID x " +
                    "USING airport_SID y " +
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
