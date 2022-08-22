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
public class localizer_glide_slope {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String LocalizerIdentifier;
    String ILSCategory;
    String ContinuationRecordNo;
    String LocalizerFrequency;
    String RunwayIdentifier;
    String LocalizerLatitude;  // have to convert
    String LocalizerLongitude; // have to convert
    String LocalizerBearing;
    String GlideSlopeLatitude;
    String GlideSlopeLongitude;
    String LocalizerPosition;
    String LocalizerPositionReference;
    String GlideSlopePosition;
    String LocalizerWidth;
    String GlideSlopeAngle;
    String StationDeclination;
    String GlideSlopeHeight;
    String GlideSlopeElevation;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    float latitude2;
    float longitude2;
    
    public localizer_glide_slope(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.LocalizerIdentifier = temp.substring(13,17).trim();
        this.ILSCategory = temp.substring(17,18).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.LocalizerFrequency = temp.substring(22,27).trim();
        this.RunwayIdentifier = temp.substring(27,32).trim();
        this.LocalizerLatitude = temp.substring(32,41).trim();
        this.LocalizerLongitude = temp.substring(41,51).trim();
        this.LocalizerBearing = temp.substring(51,55).trim();
        this.GlideSlopeLatitude = temp.substring(55,64).trim();
        this.GlideSlopeLongitude = temp.substring(64,74).trim();
        this.LocalizerPosition = temp.substring(74,78).trim();
        this.LocalizerPositionReference = temp.substring(78,79).trim();
        this.GlideSlopePosition = temp.substring(79,83).trim();
        this.LocalizerWidth = temp.substring(83,87).trim();
        this.GlideSlopeAngle = temp.substring(87,90).trim();
        this.StationDeclination = temp.substring(90,95).trim();
        this.GlideSlopeHeight = temp.substring(95,97).trim();
        this.GlideSlopeElevation = temp.substring(97,102).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_localizer();
    }

    public localizer_glide_slope() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_localizer(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'localizer_glide_slope' and column_name = 'geolocation_l');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table localizer_glide_slope add column geolocation_l geography(point);";
                stmt.execute(cre);
            }
            String up = "update localizer_glide_slope set geolocation_l = ST_MakePoint(localizer_longitude, localizer_latitude);";
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
        if(LocalizerLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(LocalizerLatitude.substring(1,3));
            float min = Integer.parseInt(LocalizerLatitude.substring(3,5));
            float sec = Integer.parseInt(LocalizerLatitude.substring(5,7));
            float msec = Integer.parseInt(LocalizerLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LocalizerLatitude.substring(1,3));
            float min = Integer.parseInt(LocalizerLatitude.substring(3,5));
            float sec = Integer.parseInt(LocalizerLatitude.substring(5,7));
            float msec = Integer.parseInt(LocalizerLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(LocalizerLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(LocalizerLongitude.substring(1,4));
            float min = Integer.parseInt(LocalizerLongitude.substring(4,6));
            float sec = Integer.parseInt(LocalizerLongitude.substring(6,8));
            float msec = Integer.parseInt(LocalizerLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LocalizerLongitude.substring(1,4));
            float min = Integer.parseInt(LocalizerLongitude.substring(4,6));
            float sec = Integer.parseInt(LocalizerLongitude.substring(6,8));
            float msec = Integer.parseInt(LocalizerLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Calculate locations
    public static void CalculateGeolocations_glide_slope(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'localizer_glide_slope' and column_name = 'geolocation_gs');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table localizer_glide_slope add column geolocation_gs geography(point);";
                stmt.execute(cre);
            }
            String up = "update localizer_glide_slope set geolocation_gs = ST_MakePoint(glideslope_longitude, glideslope_latitude);";
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
        if(GlideSlopeLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(GlideSlopeLatitude.substring(1,3));
            float min = Integer.parseInt(GlideSlopeLatitude.substring(3,5));
            float sec = Integer.parseInt(GlideSlopeLatitude.substring(5,7));
            float msec = Integer.parseInt(GlideSlopeLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(GlideSlopeLatitude.substring(1,3));
            float min = Integer.parseInt(GlideSlopeLatitude.substring(3,5));
            float sec = Integer.parseInt(GlideSlopeLatitude.substring(5,7));
            float msec = Integer.parseInt(GlideSlopeLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(GlideSlopeLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(GlideSlopeLongitude.substring(1,4));
            float min = Integer.parseInt(GlideSlopeLongitude.substring(4,6));
            float sec = Integer.parseInt(GlideSlopeLongitude.substring(6,8));
            float msec = Integer.parseInt(GlideSlopeLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(GlideSlopeLongitude.substring(1,4));
            float min = Integer.parseInt(GlideSlopeLongitude.substring(4,6));
            float sec = Integer.parseInt(GlideSlopeLongitude.substring(6,8));
            float msec = Integer.parseInt(GlideSlopeLongitude.substring(8,10));
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
            String ins = "INSERT INTO localizer_glide_slope VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+LocalizerIdentifier+"\'"+","+
                "\'"+ILSCategory+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+LocalizerFrequency+"\'"+","+
                "\'"+RunwayIdentifier+"\'"+","+
                latitude+","+
                longitude+","+    
                "\'"+LocalizerBearing+"\'"+","+  
                latitude2+","+
                longitude2+","+      
                "\'"+LocalizerPosition+"\'"+","+        
                "\'"+LocalizerPositionReference+"\'"+","+
                "\'"+GlideSlopePosition+"\'"+","+
                "\'"+LocalizerWidth+"\'"+","+
                "\'"+GlideSlopeAngle+"\'"+","+
                "\'"+StationDeclination+"\'"+","+
                "\'"+GlideSlopeHeight+"\'"+","+
                "\'"+GlideSlopeElevation+"\'"+","+        
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
