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
public class mls {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String MLSIdentifier;
    String MLSCategory;
    String ContinuationRecordNo;
    String Channel;
    String RunwayIdentifier;
    String AzimuthLatitude;  // have to convert
    String AzimuthLongitude; // have to convert
    String AzimuthBearing;
    String ElevationLatitude;
    String ElevationLongitude;
    String AzimuthPosition;
    String AzimuthPositionReference;
    String ElevationPosition;
    String AzimuthProportionalAngleRight;
    String AzimuthProportionalAngleLeft;
    String AzimuthCoverageRight;
    String AzimuthCoverageLeft;
    String ElevationAngleSpan;
    String MagneticVariation;
    String ELElevation;
    String NominalElevationAngle;
    String MinimumGlidePathAngle;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    float latitude2;
    float longitude2;
    
    public mls(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.MLSIdentifier = temp.substring(13,17).trim();
        this.MLSCategory = temp.substring(17,18).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.Channel = temp.substring(22,25).trim();
        this.RunwayIdentifier = temp.substring(27,32).trim();
        this.AzimuthLatitude = temp.substring(32,41).trim();
        this.AzimuthLongitude = temp.substring(41,51).trim();
        this.AzimuthBearing = temp.substring(51,55).trim();
        this.ElevationLatitude = temp.substring(55,64).trim();
        this.ElevationLongitude = temp.substring(64,74).trim();
        this.AzimuthPosition = temp.substring(74,78).trim();
        this.AzimuthPositionReference = temp.substring(78,79).trim();
        this.ElevationPosition = temp.substring(79,83).trim();
        this.AzimuthProportionalAngleRight = temp.substring(83,86).trim();
        this.AzimuthProportionalAngleLeft = temp.substring(86,89).trim();
        this.AzimuthCoverageRight = temp.substring(89,92).trim();
        this.AzimuthCoverageLeft = temp.substring(92,95).trim();
        this.ElevationAngleSpan = temp.substring(95,98).trim();
        this.MagneticVariation = temp.substring(98,103).trim();
        this.ELElevation = temp.substring(103,108).trim();
        this.NominalElevationAngle = temp.substring(108,112).trim();
        this.MinimumGlidePathAngle = temp.substring(112,115).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_localizer();
    }

    public mls() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_localizer(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'mls' and column_name = 'geolocation_azi');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table mls add column geolocation_azi geography(point);";
                stmt.execute(cre);
            }
            String up = "update mls set geolocation_azi = ST_MakePoint(azimuth_longitude, azimuth_latitude);";
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
    private void CalculateLongAndLat_localizer(){
        if(AzimuthLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(AzimuthLatitude.substring(1,3));
            float min = Integer.parseInt(AzimuthLatitude.substring(3,5));
            float sec = Integer.parseInt(AzimuthLatitude.substring(5,7));
            float msec = Integer.parseInt(AzimuthLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(AzimuthLatitude.substring(1,3));
            float min = Integer.parseInt(AzimuthLatitude.substring(3,5));
            float sec = Integer.parseInt(AzimuthLatitude.substring(5,7));
            float msec = Integer.parseInt(AzimuthLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(AzimuthLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(AzimuthLongitude.substring(1,4));
            float min = Integer.parseInt(AzimuthLongitude.substring(4,6));
            float sec = Integer.parseInt(AzimuthLongitude.substring(6,8));
            float msec = Integer.parseInt(AzimuthLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(AzimuthLongitude.substring(1,4));
            float min = Integer.parseInt(AzimuthLongitude.substring(4,6));
            float sec = Integer.parseInt(AzimuthLongitude.substring(6,8));
            float msec = Integer.parseInt(AzimuthLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Calculate locations
    public static void CalculateGeolocations_glide_slope(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'mls' and column_name = 'geolocation_el');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table mls add column geolocation_el geography(point);";
                stmt.execute(cre);
            }
            String up = "update mls set geolocation_el = ST_MakePoint(elevation_longitude, elevation_latitude);";
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
    private void CalculateLongAndLat_glide_slope(){
        if(ElevationLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(ElevationLatitude.substring(1,3));
            float min = Integer.parseInt(ElevationLatitude.substring(3,5));
            float sec = Integer.parseInt(ElevationLatitude.substring(5,7));
            float msec = Integer.parseInt(ElevationLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(ElevationLatitude.substring(1,3));
            float min = Integer.parseInt(ElevationLatitude.substring(3,5));
            float sec = Integer.parseInt(ElevationLatitude.substring(5,7));
            float msec = Integer.parseInt(ElevationLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(ElevationLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(ElevationLongitude.substring(1,4));
            float min = Integer.parseInt(ElevationLongitude.substring(4,6));
            float sec = Integer.parseInt(ElevationLongitude.substring(6,8));
            float msec = Integer.parseInt(ElevationLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(ElevationLongitude.substring(1,4));
            float min = Integer.parseInt(ElevationLongitude.substring(4,6));
            float sec = Integer.parseInt(ElevationLongitude.substring(6,8));
            float msec = Integer.parseInt(ElevationLongitude.substring(8,10));
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
            String ins = "INSERT INTO mls VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+MLSIdentifier+"\'"+","+
                "\'"+MLSCategory+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+Channel+"\'"+","+
                "\'"+RunwayIdentifier+"\'"+","+
                latitude+","+
                longitude+","+    
                "\'"+AzimuthBearing+"\'"+","+  
                latitude2+","+
                longitude2+","+      
                "\'"+AzimuthPosition+"\'"+","+        
                "\'"+AzimuthPositionReference+"\'"+","+
                "\'"+ElevationPosition+"\'"+","+
                "\'"+AzimuthProportionalAngleRight+"\'"+","+
                "\'"+AzimuthProportionalAngleLeft+"\'"+","+
                "\'"+AzimuthCoverageRight+"\'"+","+
                "\'"+AzimuthCoverageLeft+"\'"+","+
                "\'"+ElevationAngleSpan+"\'"+","+
                "\'"+MagneticVariation+"\'"+","+
                "\'"+ELElevation+"\'"+","+
                "\'"+NominalElevationAngle+"\'"+","+
                "\'"+MinimumGlidePathAngle+"\'"+","+         
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
