/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.company_routes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class company_routes_dat {
    public static void SetupTable_company_routes(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'company_routes' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE company_routes" +
                    " (RecordType                    VARCHAR(1),"+
                    " CustomerCode                    VARCHAR(3),"+
                    " SectionCode                    VARCHAR(1),"+
                    " SubsectionCode                    VARCHAR(1),"+
                    " FromAirport                    VARCHAR(5),"+ 
                    " ICAOCode                    VARCHAR(2),"+
                    " SectionCode2                    VARCHAR(1),"+
                    " SubsectionCode2                    VARCHAR(1),"+
                    " ToAirport                    VARCHAR(5),"+
                    " ICAOCode2                    VARCHAR(2),"+
                    " SectionCode3                    VARCHAR(1),"+
                    " SubsectionCode3                    VARCHAR(1),"+
                    " CompanyRouteID                    VARCHAR(10),"+
                    " SequenceNo                    VARCHAR(3),"+
                    " Via                    VARCHAR(3),"+
                    " SID_STAR_APP_AWY                    VARCHAR(6),"+  
                    " AreaCode                    VARCHAR(3),"+
                    " ToFix                    VARCHAR(6),"+
                    " ICAOCode3                    VARCHAR(2),"+
                    " SectionCode4                    VARCHAR(1),"+
                    " SubsectionCode4                    VARCHAR(1),"+
                    " RunwayTrans                    VARCHAR(5),"+
                    " ENRTTrans                    VARCHAR(5),"+
                    " CruiseAltitude                    VARCHAR(5),"+
                    " TerminalAlternateAirport                    VARCHAR(4),"+
                    " ICAOCode4                    VARCHAR(2),"+
                    " AlternateDistance                    VARCHAR(4),"+
                    " CostIndex                    VARCHAR(3),"+
                    " EnrouteAlternateAirport                    VARCHAR(4),"+    
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
    
    public static void deleteTable_company_routes(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE company_routes;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_company_routes(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from company_routes;";
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
    public static void CleanUpTableDuplicates_company_routes(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "company_routes x " +
                    "USING company_routes y " +
                    "WHERE x.company_routes_icao_identifier = y.company_routes_icao_identifier " +
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
