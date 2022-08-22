/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class preferred_dat {
    
    
    public static void SetupTable_preferred_routes(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'preferred_routes' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE preferred_routes" +
                    "(Record_type                  VARCHAR(1)," +
                    " Customer_Area_Code           VARCHAR(3)," +
                    " Section_Code                 VARCHAR(1)," +
                    " Subsection_Code              VARCHAR(1)," +
                    " Route_Identifier             VARCHAR(10)," +
                    "Preferred_Route_Indicator     VARCHAR(2),"+
                    "Sequence_Number               VARCHAR(4),"+
                    "Continuation_Record_Number    VARCHAR(1),"+
                    "To_Fix_Identifier             VARCHAR(5),"+
                    "ICAO_Code                     VARCHAR(2),"+
                    "Section_Code_2                VARCHAR(1),"+
                    "Subsection_Code_2             VARCHAR(1),"+
                    "Via_Code                      VARCHAR(3),"+
                    "SID_STAR_AWY_Identifier       VARCHAR(6),"+
                    "Area_Code                     VARCHAR(3),"+           
                    "Level                         VARCHAR(1),"+              
                    "Route_Type                    VARCHAR(1),"+            
                    "Initial_Airport_Fix           VARCHAR(5),"+
                    "ICAO_Code_2                   VARCHAR(2),"+
                    "Section_Code_3                VARCHAR(1),"+
                    "Subsection_Code_3             VARCHAR(1),"+            
                    "Terminus_Airport_Fix          VARCHAR(5),"+ 
                    "ICAO_Code_3                   VARCHAR(2),"+
                    "Section_Code_4                VARCHAR(1),"+
                    "Subsection_Code_4             VARCHAR(1),"+           
                    "Minimum_Altitude              VARCHAR(5),"+            
                    "Maximum_Altitude              VARCHAR(5),"+            
                    "Time_Code                     VARCHAR(1),"+            
                    "Aircraft_Use_Group            VARCHAR(2),"+           
                    "Direction_Restriction         VARCHAR(1),"+            
                    "Altitude_Description          VARCHAR(1),"+            
                    "Altitude_One                  VARCHAR(5),"+            
                    "Altitude_Two                  VARCHAR(5),"+     
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
    
    public static void deleteTable_preferred_routes(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE preferred_routes;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_preferred_routes(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from preferred_routes;";
            stmt = a.createStatement();
            stmt.executeUpdate(del);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTableDuplicates_preferred_routes(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "preferred_routes x " +
                    "USING preferred_routes y " +
                    "WHERE x.preferred_routes_icao_identifier = y.preferred_routes_icao_identifier " +
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
