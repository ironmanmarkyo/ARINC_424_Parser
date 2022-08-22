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
public class runway {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportICAOIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String RunwayIdentifier;
    String ContinuationRecordNo;
    String RunwayLength;
    String RunwayMagneticBearing;
    String RunwayLatitude;  // have to convert
    String RunwayLongitude; // have to convert
    String RunwayGradient;
    String LandingThresholdElevation;
    String DisplacedThresholdDistance;
    String ThresholdCrossingHeight;
    String RunwayWidth;
    String LocallizerMLSGLSRefPathIdentifier;
    String LocallizerMLSGLSCategory;
    String Stopway;
    String LocallizerMLSGLSRefPathIdentifier2;
    String LocallizerMLSGLSCategory2;
    String RunwayDescription;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public runway(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportICAOIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.RunwayIdentifier = temp.substring(13,18).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.RunwayLength = temp.substring(22,27).trim();
        this.RunwayMagneticBearing = temp.substring(27,31).trim();
        this.RunwayLatitude = temp.substring(32,41).trim();
        this.RunwayLongitude = temp.substring(41,51).trim();
        this.RunwayGradient = temp.substring(51,56).trim();
        this.LandingThresholdElevation = temp.substring(66,71).trim();
        this.DisplacedThresholdDistance = temp.substring(71,75).trim();
        this.ThresholdCrossingHeight = temp.substring(75,77).trim();
        this.RunwayWidth = temp.substring(77,80).trim();
        this.LocallizerMLSGLSRefPathIdentifier = temp.substring(81,85).trim();
        this.LocallizerMLSGLSCategory = temp.substring(85,86).trim();
        this.Stopway = temp.substring(86,90).trim();
        this.LocallizerMLSGLSRefPathIdentifier2 = temp.substring(90,94).trim();
        this.LocallizerMLSGLSCategory2 = temp.substring(94,95).trim();
        this.RunwayDescription = temp.substring(102,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_runway();
    }

    public runway() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_runway(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'runway' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table runway add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update runway set geolocation = ST_MakePoint(runway_longitude, runway_latitude);";
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
    private void CalculateLongAndLat_runway(){
        if(RunwayLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(RunwayLatitude.substring(1,3));
            float min = Integer.parseInt(RunwayLatitude.substring(3,5));
            float sec = Integer.parseInt(RunwayLatitude.substring(5,7));
            float msec = Integer.parseInt(RunwayLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(RunwayLatitude.substring(1,3));
            float min = Integer.parseInt(RunwayLatitude.substring(3,5));
            float sec = Integer.parseInt(RunwayLatitude.substring(5,7));
            float msec = Integer.parseInt(RunwayLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(RunwayLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(RunwayLongitude.substring(1,4));
            float min = Integer.parseInt(RunwayLongitude.substring(4,6));
            float sec = Integer.parseInt(RunwayLongitude.substring(6,8));
            float msec = Integer.parseInt(RunwayLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(RunwayLongitude.substring(1,4));
            float min = Integer.parseInt(RunwayLongitude.substring(4,6));
            float sec = Integer.parseInt(RunwayLongitude.substring(6,8));
            float msec = Integer.parseInt(RunwayLongitude.substring(8,10));
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
            String ins = "INSERT INTO runway VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportICAOIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+RunwayIdentifier+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+RunwayLength+"\'"+","+
                "\'"+RunwayMagneticBearing+"\'"+","+
                latitude+","+
                longitude+","+    
                "\'"+RunwayGradient+"\'"+","+  
                "\'"+LandingThresholdElevation+"\'"+","+
                "\'"+DisplacedThresholdDistance+"\'"+","+      
                "\'"+ThresholdCrossingHeight+"\'"+","+        
                "\'"+RunwayWidth+"\'"+","+
                "\'"+LocallizerMLSGLSRefPathIdentifier+"\'"+","+
                "\'"+LocallizerMLSGLSCategory+"\'"+","+
                "\'"+Stopway+"\'"+","+
                "\'"+LocallizerMLSGLSRefPathIdentifier2+"\'"+","+
                "\'"+LocallizerMLSGLSCategory2+"\'"+","+
                "\'"+RunwayDescription+"\'"+","+        
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
