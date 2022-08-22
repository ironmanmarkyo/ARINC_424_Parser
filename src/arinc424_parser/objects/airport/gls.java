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
public class gls {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportHeliportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String GLSRefPathIdentifier;
    String GLSCategory;
    String ContinuationRecordNo;
    String GLSChannel;
    String RunwayIdentifier;
    String GLSApproachBearing;
    String StationLatitude;  // have to convert
    String StationLongitude; // have to convert
    String GLSStationIddent;
    String ServiceVolumeRadius;
    String TDMASlots;
    String GLSApproachSlope;
    String MagneticVariation;
    String StationElevation;
    String DatumCode;
    String StationType;
    String StationElevationWGS84;
//    String DatumCode2;
//    String RunwayDescription;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public gls(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportHeliportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.GLSRefPathIdentifier = temp.substring(13,17).trim();
        this.GLSCategory = temp.substring(17,18).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.GLSChannel = temp.substring(22,27).trim();
        this.RunwayIdentifier = temp.substring(27,32).trim();
        this.GLSRefPathIdentifier = temp.substring(51,55).trim();
        this.StationLatitude = temp.substring(55,64).trim();
        this.StationLongitude = temp.substring(64,74).trim();
        this.GLSStationIddent = temp.substring(74,78).trim();
        this.ServiceVolumeRadius = temp.substring(83,85).trim();
        this.TDMASlots = temp.substring(85,87).trim();
        this.GLSApproachSlope = temp.substring(87,90).trim();
        this.MagneticVariation = temp.substring(90,95).trim();
        this.StationElevation = temp.substring(97,102).trim();
        this.DatumCode = temp.substring(102,105).trim();
        this.StationType = temp.substring(105,108).trim();
        this.StationElevationWGS84 = temp.substring(110,115).trim();
//        this.DatumCode2 = temp.substring(94,95).trim();
//        this.RunwayDescription = temp.substring(102,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_gls();
    }

    public gls() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_gls(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'gls' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table gls add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update gls set geolocation = ST_MakePoint(station_longitude, station_latitude);";
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
    private void CalculateLongAndLat_gls(){
        if(StationLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(StationLatitude.substring(1,3));
            float min = Integer.parseInt(StationLatitude.substring(3,5));
            float sec = Integer.parseInt(StationLatitude.substring(5,7));
            float msec = Integer.parseInt(StationLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(StationLatitude.substring(1,3));
            float min = Integer.parseInt(StationLatitude.substring(3,5));
            float sec = Integer.parseInt(StationLatitude.substring(5,7));
            float msec = Integer.parseInt(StationLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(StationLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(StationLongitude.substring(1,4));
            float min = Integer.parseInt(StationLongitude.substring(4,6));
            float sec = Integer.parseInt(StationLongitude.substring(6,8));
            float msec = Integer.parseInt(StationLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(StationLongitude.substring(1,4));
            float min = Integer.parseInt(StationLongitude.substring(4,6));
            float sec = Integer.parseInt(StationLongitude.substring(6,8));
            float msec = Integer.parseInt(StationLongitude.substring(8,10));
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
            String ins = "INSERT INTO gls VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportHeliportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+GLSRefPathIdentifier+"\'"+","+
                "\'"+GLSCategory+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+GLSChannel+"\'"+","+
                "\'"+RunwayIdentifier+"\'"+","+
                "\'"+GLSApproachBearing+"\'"+","+
                latitude+","+
                longitude+","+    
                "\'"+GLSStationIddent+"\'"+","+  
                "\'"+ServiceVolumeRadius+"\'"+","+
                "\'"+TDMASlots+"\'"+","+      
                "\'"+GLSApproachSlope+"\'"+","+        
                "\'"+MagneticVariation+"\'"+","+
                "\'"+StationElevation+"\'"+","+
                "\'"+DatumCode+"\'"+","+
                "\'"+StationType+"\'"+","+
                "\'"+StationElevationWGS84+"\'"+","+
//                "\'"+DatumCode2+"\'"+","+
//                "\'"+RunwayDescription+"\'"+","+        
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
