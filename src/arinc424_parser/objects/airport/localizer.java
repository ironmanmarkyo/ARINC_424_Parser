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
public class localizer {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String LocalizerIdentifier;
    String MarkerType;
    String ContinuationRecordNo;
    String LocatorFrequency;
    String RunwayIdentifier;
    String MarkerLatitude;  // have to convert
    String MarkerLongitude; // have to convert
    String MinorAxisBearing;
    String LocatorLatitude;
    String LocatorLongitude;
    String LocatorClass;
    String LocatorFacilityCharacterstics;
    String LocatorIdentifier;
    String MagneticVariation;
    String FacilityElevation;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    float latitude2;
    float longitude2;
    
    public localizer(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.LocalizerIdentifier = temp.substring(13,17).trim();
        this.MarkerType = temp.substring(17,20).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.LocatorFrequency = temp.substring(22,27).trim();
        this.RunwayIdentifier = temp.substring(27,32).trim();
        this.MarkerLatitude = temp.substring(32,41).trim();
        this.MarkerLongitude = temp.substring(41,51).trim();
        this.MinorAxisBearing = temp.substring(51,55).trim();
        this.LocatorLatitude = temp.substring(55,64).trim();
        this.LocatorLongitude = temp.substring(64,74).trim();
        this.LocatorClass = temp.substring(74,79).trim();
        this.LocatorFacilityCharacterstics = temp.substring(79,84).trim();
        this.LocatorIdentifier = temp.substring(84,88).trim();
        this.MagneticVariation = temp.substring(90,95).trim();
        this.FacilityElevation = temp.substring(91,102).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_Marker();
        this.CalculateLongAndLat_Locator();
    }

    public localizer() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_Marker(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'localizer' and column_name = 'geolocation_m');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table localizer add column geolocation_m geography(point);";
                stmt.execute(cre);
            }
            String up = "update localizer set geolocation_m = ST_MakePoint(marker_longitude, marker_latitude);";
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
    private void CalculateLongAndLat_Marker(){
        if(MarkerLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(MarkerLatitude.substring(1,3));
            float min = Integer.parseInt(MarkerLatitude.substring(3,5));
            float sec = Integer.parseInt(MarkerLatitude.substring(5,7));
            float msec = Integer.parseInt(MarkerLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(MarkerLatitude.substring(1,3));
            float min = Integer.parseInt(MarkerLatitude.substring(3,5));
            float sec = Integer.parseInt(MarkerLatitude.substring(5,7));
            float msec = Integer.parseInt(MarkerLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(MarkerLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(MarkerLongitude.substring(1,4));
            float min = Integer.parseInt(MarkerLongitude.substring(4,6));
            float sec = Integer.parseInt(MarkerLongitude.substring(6,8));
            float msec = Integer.parseInt(MarkerLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(MarkerLongitude.substring(1,4));
            float min = Integer.parseInt(MarkerLongitude.substring(4,6));
            float sec = Integer.parseInt(MarkerLongitude.substring(6,8));
            float msec = Integer.parseInt(MarkerLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Calculate locations
    public static void CalculateGeolocations_Locator(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'localizer' and column_name = 'geolocation_l');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table localizer add column geolocation_l geography(point);";
                stmt.execute(cre);
            }
            String up = "update localizer set geolocation_l = ST_MakePoint(locator_longitude, locator_latitude);";
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
    private void CalculateLongAndLat_Locator(){
        if(LocatorLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(LocatorLatitude.substring(1,3));
            float min = Integer.parseInt(LocatorLatitude.substring(3,5));
            float sec = Integer.parseInt(LocatorLatitude.substring(5,7));
            float msec = Integer.parseInt(LocatorLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LocatorLatitude.substring(1,3));
            float min = Integer.parseInt(LocatorLatitude.substring(3,5));
            float sec = Integer.parseInt(LocatorLatitude.substring(5,7));
            float msec = Integer.parseInt(LocatorLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(LocatorLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(LocatorLongitude.substring(1,4));
            float min = Integer.parseInt(LocatorLongitude.substring(4,6));
            float sec = Integer.parseInt(LocatorLongitude.substring(6,8));
            float msec = Integer.parseInt(LocatorLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LocatorLongitude.substring(1,4));
            float min = Integer.parseInt(LocatorLongitude.substring(4,6));
            float sec = Integer.parseInt(LocatorLongitude.substring(6,8));
            float msec = Integer.parseInt(LocatorLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO localizer VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+LocalizerIdentifier+"\'"+","+
                "\'"+MarkerType+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+LocatorFrequency+"\'"+","+
                "\'"+RunwayIdentifier+"\'"+","+
                latitude+","+
                longitude+","+    
                "\'"+MinorAxisBearing+"\'"+","+  
                latitude2+","+
                longitude2+","+      
                "\'"+LocatorClass+"\'"+","+        
                "\'"+LocatorFacilityCharacterstics+"\'"+","+
                "\'"+LocatorIdentifier+"\'"+","+
                "\'"+MagneticVariation+"\'"+","+
                "\'"+FacilityElevation+"\'"+","+
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
