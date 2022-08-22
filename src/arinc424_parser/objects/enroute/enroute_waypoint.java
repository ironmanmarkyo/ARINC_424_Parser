/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TimeZone;

/**
 *
 * @author yohan
 */
public class enroute_waypoint {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String RegionCode;
    String ICAOCode;
    String WaypointIdentifier;
    String ICAOCode2;
    String ContinuationRecordNo;
    String WaypointType;
    String WaypointUsage;
    String WaypointLatitude;  // have to convert
    String WaypointLongitude; // have to convert
    String DynamicMagVariation;
    String DatumCode;
    String NameFormatIndicator;
    String WaypointName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public enroute_waypoint(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.RegionCode = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.WaypointIdentifier = temp.substring(13,18).trim();
        this.ICAOCode2 = temp.substring(19,21).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.WaypointType = temp.substring(26,29).trim();
        this.WaypointUsage = temp.substring(29,31).trim();
        this.WaypointLatitude = temp.substring(32,41).trim();
        this.WaypointLongitude = temp.substring(41,51).trim();
        this.DynamicMagVariation = temp.substring(74,79).trim();
        this.DatumCode = temp.substring(84,87).trim();
        this.NameFormatIndicator = temp.substring(95,98).trim();
        this.WaypointName = temp.substring(93,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_enroute_waypoint();
    }

    public enroute_waypoint() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_enroute_waypoint(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'enroute_waypoint' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table enroute_waypoint add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update enroute_waypoint set geolocation = ST_MakePoint(waypoint_longitude, waypoint_latitude);";
            stmt.executeUpdate(up);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    //Calculate longitudes and latitudes
    private void CalculateLongAndLat_enroute_waypoint(){
        if(WaypointLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(WaypointLatitude.substring(1,3));
            float min = Integer.parseInt(WaypointLatitude.substring(3,5));
            float sec = Integer.parseInt(WaypointLatitude.substring(5,7));
            float msec = Integer.parseInt(WaypointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(WaypointLatitude.substring(1,3));
            float min = Integer.parseInt(WaypointLatitude.substring(3,5));
            float sec = Integer.parseInt(WaypointLatitude.substring(5,7));
            float msec = Integer.parseInt(WaypointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(WaypointLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(WaypointLongitude.substring(1,4));
            float min = Integer.parseInt(WaypointLongitude.substring(4,6));
            float sec = Integer.parseInt(WaypointLongitude.substring(6,8));
            float msec = Integer.parseInt(WaypointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(WaypointLongitude.substring(1,4));
            float min = Integer.parseInt(WaypointLongitude.substring(4,6));
            float sec = Integer.parseInt(WaypointLongitude.substring(6,8));
            float msec = Integer.parseInt(WaypointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO enroute_waypoint VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+RegionCode+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+WaypointIdentifier+"\'"+","+
                "\'"+ICAOCode2+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+WaypointType+"\'"+","+
                "\'"+WaypointUsage+"\'"+","+
                latitude+","+
                longitude+","+     
                "\'"+DynamicMagVariation+"\'"+","+  
                "\'"+DatumCode+"\'"+","+        
                "\'"+NameFormatIndicator+"\'"+","+
                "\'"+WaypointName+"\'"+","+        
                FileRecordNumber+","+           
                CycleDate+");";
//                System.out.println(ins);
            stmt.executeUpdate(ins);
            stmt.close();
            a.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
