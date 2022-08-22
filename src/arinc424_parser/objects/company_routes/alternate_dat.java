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
public class alternate_dat {
    public static void SetupTable_company_routes_alternate(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'alternate' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE alternate" +
                    " (Record_Type                    VARCHAR(1),"+
                    " Customer_Area_Code              VARCHAR(3),"+
                    " Section_Code                    VARCHAR(1),"+
                    " Subsection_Code                 VARCHAR(1),"+
                    " Alternate_Related_Airport         VARCHAR(5),"+ 
                    " Alternate_Related_ICAO_Code       VARCHAR(2),"+
                    " Alternate_Related_Section_Code    VARCHAR(1),"+
                    " Alternate_Related_Subsection_Code VARCHAR(1),"+
                    " Alternate_Related_Record_Type     VARCHAR(1),"+
                    " Distance_To_Alternate_1           VARCHAR(3),"+
                    " ALternate_Type_1                  VARCHAR(1),"+
                    " Primary_Alternate_Identifier      VARCHAR(10),"+
                    " Distance_To_Alternate_2           VARCHAR(3),"+
                    " ALternate_Type_2                  VARCHAR(1),"+
                    " Additional_Alternate_Identifier_1 VARCHAR(10),"+
                    " Distance_To_Alternate_3           VARCHAR(3),"+
                    " ALternate_Type_3                  VARCHAR(1),"+
                    " Additional_Alternate_Identifier_2 VARCHAR(10),"+
                    " Distance_To_Alternate_4           VARCHAR(3),"+
                    " ALternate_Type_4                  VARCHAR(1),"+
                    " Additional_Alternate_Identifier_3 VARCHAR(10),"+
                    " Distance_To_Alternate_5           VARCHAR(3),"+
                    " ALternate_Type_5                  VARCHAR(1),"+
                    " Additional_Alternate_Identifier_4 VARCHAR(10),"+
                    " Distance_To_Alternate_6           VARCHAR(3),"+
                    " ALternate_Type_6                  VARCHAR(1),"+
                    " Additional_Alternate_Identifier_5 VARCHAR(10),"+
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
    
    public static void deleteTable_company_routes_alternate(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE alternate;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_company_routes_alternate(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from alternate;";
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
                    "alternate x " +
                    "USING alternate y " +
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
