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
public class flt_planning_dat {
    public static void SetupTable_flt_planning_arr_dept(Connection a){
        try {
            Statement stmt = a.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM pg_tables WHERE schemaname = 'public' AND tablename  = 'flt_planning_arr_dept' );");
            rs.next();
            String result = rs.getString("exists");
            rs.close();
//            System.out.println(result);
            if (!result.equalsIgnoreCase("t")) {
//               System.out.println("true");
                    String sql = "CREATE TABLE flt_planning_arr_dept" +
                    "(Record_Type                        VARCHAR(1),"+
                    "Customer_Area_Code                   VARCHAR(3),"+
                    "Section_Code                        VARCHAR(1),"+
                    "Airport_Identifier                  VARCHAR(4),"+
                    "ICAO_Code                           VARCHAR(2),"+
                    "Subsection_Code                     VARCHAR(1),"+
                    "SID_STAR_Approach_Identifier       VARCHAR(6),"+
                    "Procedure_Type                     VARCHAR(1),"+
                    "Runway_Transition_Identifier       VARCHAR(5),"+
                    "Runway_Transition_Fix              VARCHAR(5),"+
                    "ICAO_Code_2                        VARCHAR(2),"+
                    "Section_Code_2                     VARCHAR(1),"+
                    "Sub_Section_Code_2                 VARCHAR(1),"+
                    "Runway_Transition_Along_Track_Distance                    VARCHAR(3),"+
                    "Common_Segment_Transition_Fix              VARCHAR(5),"+
                    "ICAO_Code_3                        VARCHAR(2),"+
                    "Section_Code_3                     VARCHAR(1),"+
                    "Sub_Section_Code_3                 VARCHAR(1),"+
                    "Common_Segment_Along_Track_Distance                    VARCHAR(3),"+
                    "Enroute_Transition_Identifier       VARCHAR(5),"+
                    "Enroute_Transition_Fix              VARCHAR(5),"+
                    "ICAO_Code_4                        VARCHAR(2),"+
                    "Section_Code_4                     VARCHAR(1),"+
                    "Sub_Section_Code_4                 VARCHAR(1),"+
                    "Enroute_Transition_Along_Track_Distance                    VARCHAR(3),"+
                    "Sequence_Number                    VARCHAR(3),"+
                    "Continuation_No                     VARCHAR(1),"+
                    "Number_Of_Engines                  VARCHAR(4),"+
                    "Turboprop_Jet_Indicator            VARCHAR(1),"+
                    "RNAV_Flag                          VARCHAR(1),"+
                    "ATC_Weight_Category                VARCHAR(1),"+
                    "ATC_Identifier                     VARCHAR(7),"+
                    "Time_Code                          VARCHAR(1),"+
                    "Procedure_Description              VARCHAR(15),"+
                    "Leg_Tyep_Code                      VARCHAR(2),"+
                    "Reporting_Code                     VARCHAR(1),"+
                    "Initial_Departure_Magnetic_Course  VARCHAR(4),"+
                    "Altitude_Description               VARCHAR(1),"+
                    "Altitude_1                         VARCHAR(3),"+
                    "Altitude_2                         VARCHAR(3),"+
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
    
    public static void deleteTable_flt_planning_arr_dept(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "DROP TABLE flt_planning_arr_dept;";
            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CleanUpTable_flt_planning_arr_dept(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE from flt_planning_arr_dept;";
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
    public static void CleanUpTableDuplicates_flt_planning_arr_dept(Connection a){
        Statement stmt = null;
        try {
            String del = "DELETE FROM " +
                    "flt_planning_arr_dept x " +
                    "USING flt_planning_arr_dept y " +
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
