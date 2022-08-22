/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arinc424_parser;

//importing dependencies

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


//importing objects
import arinc424_parser.dat.database;
//MORA
import arinc424_parser.objects.MORA.mora;


//NAVAID
import arinc424_parser.objects.navaid.ndb_navaid;
import arinc424_parser.objects.navaid.vhf_navaid;

//Enroute
import arinc424_parser.objects.enroute.enroute_waypoint;
import arinc424_parser.objects.enroute.airways.marker;
import arinc424_parser.objects.enroute.holding.holding;
import arinc424_parser.objects.enroute.airways.airways;
import arinc424_parser.objects.enroute.preferred_routes;
import arinc424_parser.objects.enroute.airways.note_restriction;
import arinc424_parser.objects.enroute.airways.seasonal_closure;
import arinc424_parser.objects.enroute.airways.altitude_exclusion;
import arinc424_parser.objects.enroute.airways.cruising_tr;
import arinc424_parser.objects.enroute.communications_enroute;

//Heliport
import arinc424_parser.objects.heliport.heliport;
import arinc424_parser.objects.heliport.heliport_waypoint;
import arinc424_parser.objects.heliport.heliport_SID;
import arinc424_parser.objects.heliport.heliport_STAR;
import arinc424_parser.objects.heliport.heliport_approach;
import arinc424_parser.objects.heliport.heliport_msa;
import arinc424_parser.objects.heliport.heliport_communications;

//Airport
import arinc424_parser.objects.airport.airport;
import arinc424_parser.objects.airport.airport_gate;
import arinc424_parser.objects.airport.airport_waypoint;
import arinc424_parser.objects.airport.airport_SID;
import arinc424_parser.objects.airport.airport_STAR;
import arinc424_parser.objects.airport.airport_approach;
import arinc424_parser.objects.airport.runway;
import arinc424_parser.objects.airport.localizer_glide_slope;
import arinc424_parser.objects.airport.mls;
import arinc424_parser.objects.airport.localizer;
import arinc424_parser.objects.airport.airport_ndb;
import arinc424_parser.objects.airport.path_point;
import arinc424_parser.objects.airport.flt_planning_arr_dept;
import arinc424_parser.objects.airport.msa;
import arinc424_parser.objects.airport.gls;
import arinc424_parser.objects.airport.communications;

//Company Routes
import arinc424_parser.objects.company_routes.company_routes;
import arinc424_parser.objects.company_routes.alternate;

//Tables
import arinc424_parser.objects.tables.cruising;
import arinc424_parser.objects.tables.geographical;

//Airspace
import arinc424_parser.objects.airspace.controlled_airspace;
import arinc424_parser.objects.airspace.fir_uir;
import arinc424_parser.objects.airspace.restricted_airspace;
import java.sql.SQLException;
/**
 *
 * @author yohan
 */
public class Arinc424_parser {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            database A = new database();
            Connection c;
            c = A.SetupConnection("arinc_424_data", "postgres", "yohan2002");
            A.SetupPostgis(c);
            A.SetUpTables(c);
            A.CleanTables(c);
            
            
            fr = new FileReader("C:\\Users\\yohan\\OneDrive\\Desktop\\ARINC 424 DRDO Project\\arinc424_parser\\src\\arinc424_parser\\arinc_424_data.txt");
            int i,j;
            char[] ch = {'s'};
            while((i=fr.read())!=-1){
                StringBuilder sb = new StringBuilder(132);
                for(j=0;j<132;j++){
                    ch[0]=(char)i;
                    String a = new String(ch);
                    sb.insert(j,a);
                    i=fr.read();
                }
                String temp = new String(sb);
                String Section = temp.substring(4,5).trim();
                String Subsection = new String();
//                /*
                switch(Section){
                    case "A" -> {
                        //MORA
                        Subsection = temp.substring(5,6).trim();
                        mora test = new mora(temp);
                        test.InsertDatabase(c);
//                        System.out.println("\nMORA");
                    }
                    case "D" -> {
                        //NAVAID
                        Subsection = temp.substring(5,6).trim();
                        if ("B".equals(Subsection)){
                            ndb_navaid test = new ndb_navaid(temp);
                            test.InsertDatabase(c);
                        }else{
                            vhf_navaid test = new vhf_navaid(temp);
                            test.InsertDatabase(c);
                        }
//                        System.out.println("\nNAVAID");
                    }
                    case "E" -> {
                        //Enroute
                        Subsection = temp.substring(5,6).trim();
//                        System.out.println(temp);
                        switch(Subsection){
                            case "A" -> {
                                enroute_waypoint test = new enroute_waypoint(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Waypoint");
                            
                        }
                            case "M" -> {
                                marker test = new marker(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Marker");
                            
                        }
                            case "P" -> {
                                holding test = new holding(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Holding");
                            
                        }
                            case "R" -> {
                                airways test = new airways(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Airways");
                            
                        }
                            case "T" -> {
                                preferred_routes test = new preferred_routes(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Preferred Routes");
                            
                        }
                            case "U" -> {                                
                                String restriction_type = temp.substring(15,17).trim();
//                                System.out.println("\nRestriction = "+restriction_type);
                                if("AE".equals(restriction_type)){
                                    altitude_exclusion test = new altitude_exclusion(temp);
                                    test.InsertDatabase(c);
//                                    System.out.println("\nAltitude Exclusion");
                                }else if("TC".equals(restriction_type)){
                                    cruising_tr test = new cruising_tr(temp);
                                    test.InsertDatabase(c);
                                }else if("SC".equals(restriction_type)){
                                    seasonal_closure test = new seasonal_closure(temp);
                                    test.InsertDatabase(c);
                                }else if("NR".equals(restriction_type)){
                                    note_restriction test = new note_restriction(temp);
                                    test.InsertDatabase(c);
                                }
                            
                        }
                            case "V" -> {
                                communications_enroute test = new communications_enroute(temp);
                                test.InsertDatabase(c);
//                                System.out.println("\nEnroute Communications");
                            
                        }
                        }
                                               
//                        System.out.println("\nEnroute");
                    }
                    case "H" -> {
                        //Heliport
                        Subsection = temp.substring(12,13).trim();
//                        System.out.println(temp);
                        switch(Subsection){
                            case "A" -> {
                                heliport test = new heliport(temp);
                                test.InsertDatabase(c);
                            }
                            case "C" -> {
                                heliport_waypoint test = new heliport_waypoint(temp);
                                test.InsertDatabase(c);
                            }
                            case "D" -> {
                                heliport_SID test = new heliport_SID(temp);
                                test.InsertDatabase(c);
                            }
                            case "E" -> {
                                heliport_STAR test = new heliport_STAR(temp);
                                test.InsertDatabase(c);
                            }
                            case "F" -> {
                                heliport_approach test = new heliport_approach(temp);
                                test.InsertDatabase(c);
                            }
                            case "S" -> {
                                heliport_msa test = new heliport_msa(temp);
                                test.InsertDatabase(c);
                            }
                            case "V" -> {
                                heliport_communications test = new heliport_communications(temp);
                                test.InsertDatabase(c);
                            }
                        }
//                        System.out.println("\nHeliport");
                    }
                    case "P" -> {
                        //Airport
                        Subsection = temp.substring(12,13).trim();
//                        System.out.println("\nSubsection = "+Subsection);
//                        System.out.println(temp);
                        switch(Subsection){
                            case "A" -> {
                                airport test = new airport(temp);
                                test.InsertDatabase(c);
                            }
                            case "B" -> {
                                airport_gate test = new airport_gate(temp);
                                test.InsertDatabase(c);
                            }
                            case "C" -> {
                                airport_waypoint test = new airport_waypoint(temp);                                
                                test.InsertDatabase(c);
                            }
                            case "D" -> {
                                airport_SID test = new airport_SID(temp);
                                test.InsertDatabase(c);
                            }
                            case "E" -> {
                                airport_STAR test = new airport_STAR(temp);
                                test.InsertDatabase(c);
                            }
                            case "F" -> {
                                airport_approach test = new airport_approach(temp);
                                test.InsertDatabase(c);
                            }
                            case "G" -> {
                                runway test = new runway(temp);
                                test.InsertDatabase(c);
                            }
                            case "I" -> {
                                localizer_glide_slope test = new localizer_glide_slope(temp);
                                test.InsertDatabase(c);
                            }
                            case "L" -> {
                                mls test = new mls(temp);
                                test.InsertDatabase(c);
                            }
                            case "M" -> {
                                localizer test = new localizer(temp);
                                test.InsertDatabase(c);
                            }
                            case "N" -> {
                                airport_ndb test = new airport_ndb(temp);
                                test.InsertDatabase(c);
                            }
                            case "P" -> {
//                                System.out.println(temp);
//                                path_point test = new path_point(temp);
                                
                            }
                            case "R" -> {
                                flt_planning_arr_dept test = new flt_planning_arr_dept(temp);
                                test.InsertDatabase(c);
                            }
                            case "S" -> {
                                msa test = new msa(temp);
                                test.InsertDatabase(c);
                            }
                            case "T" -> {
                                gls test = new gls(temp);
                                test.InsertDatabase(c);                                
                            }
                            case "V" -> {
                                communications test = new communications(temp);
                                test.InsertDatabase(c);
                            }
                        }
//                        System.out.println("\nAiport");
                    }
                    case "R" -> {
                        //Company Routes
                        Subsection = temp.substring(5,6).trim();
                        if("A".equals(Subsection)){
                            alternate test = new alternate(temp);
                            test.InsertDatabase(c);
                        }else{
                            company_routes test = new company_routes(temp);
                            test.InsertDatabase(c);
                        }
//                        System.out.println("\nCompany Route");
                    }
                    case "T" -> {
                        //Tables
                        Subsection = temp.substring(5,6).trim();
                        if("C".equals(Subsection)){
                            cruising test = new cruising(temp);
                            test.InsertDatabase(c);
                        }else if ("G".equals(Subsection)){
                            geographical test = new geographical(temp);
                            test.InsertDatabase(c);
                        }
//                        System.out.println("\nTable");
                    }
                    case "U" -> {
                        //Airspave
                        Subsection = temp.substring(5,6).trim();
                        if("C".equals(Subsection)){
                            controlled_airspace test = new controlled_airspace(temp);
                            test.InsertDatabase(c);
                        }else if ("F".equals(Subsection)){
                            fir_uir test = new fir_uir(temp);
                            test.InsertDatabase(c);
                        }else if ("R".equals(Subsection)){
                            restricted_airspace test = new restricted_airspace(temp);
                            test.InsertDatabase(c);
                        }
//                        System.out.println("\nAirspace");
                    }
                    default -> {
                        System.out.println("\nInvalid input");
                        //Code
                    }
                }
                if(i==10){
                        continue;
                    }
                i=fr.read();
                
            }
            /*
            //Removing Duplicates
            airport_dat.CleanUpTableDuplicates_airport(c);
            ndb_dat.CleanUpTableDuplicates_ndb_navaid(c);
            vhf_dat.CleanUpTableDuplicates_vhf_navaid(c);
            */
            //Calculating Geolocation
            
            //MORA
            mora.CalculateGeolocations_mora(c);
            //NAVAID
            ndb_navaid.CalculateGeolocations_NDB(c);
            vhf_navaid.CalculateGeolocations_DME(c);
            vhf_navaid.CalculateGeolocations_VOR(c);
            
            //Enroute
            enroute_waypoint.CalculateGeolocations_enroute_waypoint(c);
            marker.CalculateGeolocations_marker(c);
            communications_enroute.CalculateGeolocations_communications(c);
            
            //Heliport
            heliport.CalculateGeolocations_heliport(c);
            heliport_waypoint.CalculateGeolocations_heliport_waypoint(c);
            heliport_communications.CalculateGeolocations_heliport_communications(c);
            
            //Airport
            airport.CalculateGeolocations_airport(c);
            airport_gate.CalculateGeolocations_airport_gate(c);
            airport_ndb.CalculateGeolocations_NDB(c);
            airport_waypoint.CalculateGeolocations_airport_waypoint(c);
            communications.CalculateGeolocations_communications(c);
            gls.CalculateGeolocations_gls(c);
            localizer.CalculateGeolocations_Locator(c);
            localizer.CalculateGeolocations_Marker(c);
            localizer_glide_slope.CalculateGeolocations_glide_slope(c);
            localizer_glide_slope.CalculateGeolocations_localizer(c);
            mls.CalculateGeolocations_glide_slope(c);
            mls.CalculateGeolocations_localizer(c);
//            path_point.CalculateGeolocations_FlightPath(c);
//            path_point.CalculateGeolocations_Landing(c);
            runway.CalculateGeolocations_runway(c);
            
            //Airspace
            controlled_airspace.CalculateGeolocations(c);
            controlled_airspace.CalculateGeolocations_arc(c);
            restricted_airspace.CalculateGeolocations(c);
            restricted_airspace.CalculateGeolocations_arc(c);
            fir_uir.CalculateGeolocations_arc(c);
            fir_uir.CalculateGeolocations_fu(c);

            
            c.close();         
        
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Arinc424_parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
