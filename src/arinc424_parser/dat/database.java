/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.dat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author yohan
 */

//Importing Files for creating tables

//Mora
import arinc424_parser.objects.MORA.mora_dat;

//NAVAID
import arinc424_parser.objects.navaid.ndb_dat;
import arinc424_parser.objects.navaid.vhf_dat;

//Enroute
import arinc424_parser.objects.enroute.enroute_dat;
import arinc424_parser.objects.enroute.airways.marker_dat;
import arinc424_parser.objects.enroute.holding.holding_dat;
import arinc424_parser.objects.enroute.airways.airways_dat;
import arinc424_parser.objects.enroute.preferred_dat;
import arinc424_parser.objects.enroute.airways.note_dat;
import arinc424_parser.objects.enroute.airways.seasonal_dat;
import arinc424_parser.objects.enroute.airways.altitude_exclusion_dat;
import arinc424_parser.objects.enroute.airways.airways_cruising_dat;
import arinc424_parser.objects.enroute.communications_dat;

//Heliport
import arinc424_parser.objects.heliport.heliport_dat;
import arinc424_parser.objects.heliport.heliport_waypoint_dat;
import arinc424_parser.objects.heliport.heliport_SID_dat;
import arinc424_parser.objects.heliport.heliport_STAR_dat;
import arinc424_parser.objects.heliport.heliport_approach_dat;
import arinc424_parser.objects.heliport.heliport_msa_dat;
import arinc424_parser.objects.heliport.heliport_communications_dat;

//Airport
import arinc424_parser.objects.airport.airport_dat;
import arinc424_parser.objects.airport.airport_gate_dat;
import arinc424_parser.objects.airport.airport_waypoint_dat;
import arinc424_parser.objects.airport.airport_SID_dat;
import arinc424_parser.objects.airport.airport_STAR_dat;
import arinc424_parser.objects.airport.airport_approach_dat;
import arinc424_parser.objects.airport.runway_dat;
import arinc424_parser.objects.airport.localizer_glide_slope_dat;
import arinc424_parser.objects.airport.mls_dat;
import arinc424_parser.objects.airport.localizer_dat;
import arinc424_parser.objects.airport.airport_ndb_dat;
import arinc424_parser.objects.airport.path_point_dat;
import arinc424_parser.objects.airport.flt_planning_dat;
import arinc424_parser.objects.airport.msa_dat;
import arinc424_parser.objects.airport.gls_dat;
import arinc424_parser.objects.airport.airport_communications_dat;

//Company Routes
import arinc424_parser.objects.company_routes.company_routes_dat;
import arinc424_parser.objects.company_routes.alternate_dat;

//Tables
import arinc424_parser.objects.tables.cruising_dat;
import arinc424_parser.objects.tables.geoprahical_dat;

//Airspace
import arinc424_parser.objects.airspace.controlled_dat;
import arinc424_parser.objects.airspace.fir_uir_dat;
import arinc424_parser.objects.airspace.restricted_dat;


public class database {
    
    public void SetupPostgis(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "select exists (select extname from pg_extension where extname = 'postgis');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "create extension postgis;";
                stmt.executeUpdate(cre);
            }
//            stmt.executeUpdate(sql);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public Connection SetupConnection (String databas, String username, String password){
         Connection c = null;
         String connect = "jdbc:postgresql://localhost:5432/" + databas;
//        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection(connect,username, password);
            c.setAutoCommit(false);
            
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        
        return c;
    }
    
    public void SetUpTables(Connection a){
        
        //MORA
        mora_dat.SetupTable_mora(a);
        
        //NAVAID
        vhf_dat.SetupTable_vhf_navaid(a);
        ndb_dat.SetupTable_ndb_navaid(a);
        
        //Enroute
        enroute_dat.SetupTable_enroute_waypoint(a);
        marker_dat.SetupTable_marker(a);
        holding_dat.SetupTable_holding(a);        
        airways_dat.SetupTable_airways(a);
        preferred_dat.SetupTable_preferred_routes(a);
        note_dat.SetupTable_note_restriction(a);
        seasonal_dat.SetupTable_seasonal_closure(a);
        altitude_exclusion_dat.SetupTable_altitude_exclusion(a);
        airways_cruising_dat.SetupTable_cruising_tr(a);
        communications_dat.SetupTable_communications(a);
        
        //Heliport
        heliport_dat.SetupTable_heliport(a);
        heliport_waypoint_dat.SetupTable_heliport_waypoint(a);
        heliport_SID_dat.SetupTable_heliport_SID(a);
        heliport_STAR_dat.SetupTable_heliport_STAR(a);
        heliport_approach_dat.SetupTable_heliport_approach(a);
        heliport_msa_dat.SetupTable_heliport_msa(a);
        heliport_communications_dat.SetupTable_heliport_communications(a);
        
        //Airport
        airport_dat.SetupTable_airport(a);
        airport_gate_dat.SetupTable_airport_gate(a);
        airport_waypoint_dat.SetupTable_airport_waypoint(a);
        airport_SID_dat.SetupTable_airport_SID(a);
        airport_STAR_dat.SetupTable_airport_STAR(a);
        airport_approach_dat.SetupTable_airport_approach(a);
        runway_dat.SetupTable_runway(a);
        localizer_glide_slope_dat.SetupTable_localizer_glide_slope(a);
        mls_dat.SetupTable_mls(a);
        localizer_dat.SetupTable_localizer(a);
        airport_ndb_dat.SetupTable_airport_ndb(a);
        path_point_dat.SetupTable_path_point(a);
        flt_planning_dat.SetupTable_flt_planning_arr_dept(a);
        msa_dat.SetupTable_msa(a);
        gls_dat.SetupTable_gls(a);
        airport_communications_dat.SetupTable_communications(a);
        
        
        //Company Routes
        company_routes_dat.SetupTable_company_routes(a);
        alternate_dat.SetupTable_company_routes_alternate(a);
        
        //Tables
        cruising_dat.SetupTable_cruising(a);
        geoprahical_dat.SetupTable_geographical(a);
        
        //Airspace
        controlled_dat.SetupTable_controlled_airspace(a);
        fir_uir_dat.SetupTable_fir_uir(a);
        restricted_dat.SetupTable_restricted_airspace(a);
        
        
    }
    
    public void DeleteTables(Connection a){
        
        //MORA
        mora_dat.deleteTable_mora(a);
        
        //NAVAID
        vhf_dat.deleteTable_vhf_navaid(a);
        ndb_dat.deleteTable_ndb_navaid(a);
        
        //Enroute
        enroute_dat.deleteTable_enroute_waypoint(a);
        marker_dat.deleteTable_marker(a);
        holding_dat.deleteTable_holding(a);        
        airways_dat.deleteTable_airways(a);
        preferred_dat.deleteTable_preferred_routes(a);
        note_dat.deleteTable_note_restriction(a);
        seasonal_dat.deleteTable_seasonal_closure(a);
        altitude_exclusion_dat.deleteTable_altitude_exclusion(a);
        airways_cruising_dat.deleteTable_cruising_tr(a);
        communications_dat.deleteTable_communications(a);
        
        //Heliport
        heliport_dat.deleteTable_heliport(a);
        heliport_waypoint_dat.deleteTable_heliport_waypoint(a);
        heliport_SID_dat.deleteTable_heliport_SID(a);
        heliport_STAR_dat.deleteTable_heliport_STAR(a);
        heliport_approach_dat.deleteTable_heliport_approach(a);
        heliport_msa_dat.deleteTable_heliport_msa(a);
        heliport_communications_dat.deleteTable_heliport_communications(a);
        
        //Airport
        airport_dat.deleteTable_airport(a);
        airport_gate_dat.deleteTable_airport_gate(a);
        airport_waypoint_dat.deleteTable_airport_waypoint(a);
        airport_SID_dat.deleteTable_airport_SID(a);
        airport_STAR_dat.deleteTable_airport_STAR(a);
        airport_approach_dat.deleteTable_airport_approach(a);
        runway_dat.deleteTable_runway(a);
        localizer_glide_slope_dat.deleteTable_localizer_glide_slope(a);
        mls_dat.deleteTable_mls(a);
        localizer_dat.deleteTable_localizer(a);
        airport_ndb_dat.deleteTable_airport_ndb(a);
        path_point_dat.deleteTable_path_point(a);
        flt_planning_dat.deleteTable_flt_planning_arr_dept(a);
        msa_dat.deleteTable_msa(a);
        gls_dat.deleteTable_gls(a);
        airport_communications_dat.deleteTable_communications(a);
        
        
        //Company Routes
        company_routes_dat.deleteTable_company_routes(a);
        alternate_dat.deleteTable_company_routes_alternate(a);
        
        //Tables
        cruising_dat.deleteTable_cruising(a);
        geoprahical_dat.deleteTable_geographical(a);
        
        //Airspace
        controlled_dat.deleteTable_controlled_airspace(a);
        fir_uir_dat.deleteTable_fir_uir(a);
        restricted_dat.deleteTable_restricted_airspace(a);
        
        
    }
    
    public void CleanTables(Connection a){
        
        //MORA
        mora_dat.CleanUpTable_mora(a);
        
        //NAVAID
        vhf_dat.CleanUpTable_vhf_navaid(a);
        ndb_dat.CleanUpTable_ndb_navaid(a);
        
        //Enroute
        enroute_dat.CleanUpTable_enroute_waypoint(a);
        marker_dat.CleanUpTable_marker(a);
        holding_dat.CleanUpTable_holding(a);        
        airways_dat.CleanUpTable_airways(a);
        preferred_dat.CleanUpTable_preferred_routes(a);
        note_dat.CleanUpTable_note_restriction(a);
        seasonal_dat.CleanUpTable_seasonal_closure(a);
        altitude_exclusion_dat.CleanUpTable_altitude_exclusion(a);
        airways_cruising_dat.CleanUpTable_cruising_tr(a);
        communications_dat.CleanUpTable_communications(a);
        
        //Heliport
        heliport_dat.CleanUpTable_heliport(a);
        heliport_waypoint_dat.CleanUpTable_heliport_waypoint(a);
        heliport_SID_dat.CleanUpTable_heliport_SID(a);
        heliport_STAR_dat.CleanUpTable_heliport_STAR(a);
        heliport_approach_dat.CleanUpTable_heliport_approach(a);
        heliport_msa_dat.CleanUpTable_heliport_msa(a);
        heliport_communications_dat.CleanUpTable_heliport_communications(a);
        
        //Airport
        airport_dat.CleanUpTable_airport(a);
        airport_gate_dat.CleanUpTable_airport_gate(a);
        airport_waypoint_dat.CleanUpTable_airport_waypoint(a);
        airport_SID_dat.CleanUpTable_airport_SID(a);
        airport_STAR_dat.CleanUpTable_airport_STAR(a);
        airport_approach_dat.CleanUpTable_airport_approach(a);
        runway_dat.CleanUpTable_runway(a);
        localizer_glide_slope_dat.CleanUpTable_localizer_glide_slope(a);
        mls_dat.CleanUpTable_mls(a);
        localizer_dat.CleanUpTable_localizer(a);
        airport_ndb_dat.CleanUpTable_airport_ndb(a);
        path_point_dat.CleanUpTable_path_point(a);
        flt_planning_dat.CleanUpTable_flt_planning_arr_dept(a);
        msa_dat.CleanUpTable_msa(a);
        gls_dat.CleanUpTable_gls(a);
        airport_communications_dat.CleanUpTable_communications(a);
        
        
        //Company Routes
        company_routes_dat.CleanUpTable_company_routes(a);
        alternate_dat.CleanUpTable_company_routes_alternate(a);
        
        //Tables
        cruising_dat.CleanUpTable_cruising(a);
        geoprahical_dat.CleanUpTable_geographical(a);
        
        //Airspace
        controlled_dat.CleanUpTable_controlled_airspace(a);
        fir_uir_dat.CleanUpTable_fir_uir(a);
        restricted_dat.CleanUpTable_restricted_airspace(a);
        
        
    }
}


/*
public static void main(String[] args)throws Exception{
        // TODO code application logic here
        Connection c = null;
//        Statement stmt = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://localhost:5432/test_gis",
               "postgres", "yohan2002");
            c.setAutoCommit(false);
            deleteTable(c);
            SetupPostgis(c);
            SetupTable(c);
//            /*
            FileReader fr = new FileReader("C:\\Users\\yohan\\Desktop\\arinc\\test\\src\\test\\airport_data.txt");
            int i,j;
            char[] ch = {'s'};
            while((i=fr.read())!=-1){
                StringBuilder sb = new StringBuilder(132);
                for(j=0;j<132;j++){
    //                System.out.println((char)i);
                    ch[0]=(char)i;
                    String a = new String(ch);
                    sb.insert(j,a);
                    i=fr.read();
                }
                String temp = new String(sb);
                airport a1 = new airport(temp);

    //            a1.CalculateLongAndLat();
                a1.InsertDatabase(c);
//                System.out.println(temp);
    //            i=fr.read();
    //            char b=(char)i;
                if(i==10){
                    continue;
                }
            }
            
            CleanUpDatabaseDuplicates(c);
            CalculateGeolocations(c);
//            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
*/
