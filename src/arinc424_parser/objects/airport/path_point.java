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
public class path_point {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String ApproachIdentifier1;
    String Runway_Helipad_Identifier;
    String OperationType;
    String ApproachIndicator;
    String ContinuationRecordNo;
    String RouteID;
    String SBASSPI;
    String RefPathDataSelector;
    String RefpathDataIdentifier;
    String ApproachPO;
    String LandingThresholdPointLatitude;  // have to convert
    String LandingThresholdPointLongitude; // have to convert
    String LTPEllipsoidHeight;
    String GlidePathAngle;
    String FLightPathAlignmentPointLatitude;
    String FLightPathAlignmentPointLongitude;
    String CourseWidthAtThreshold;
    String LengthOffset;
    String ThresholdCrossingHeight;
    String TCHIndicator;
    String HAL;
    String VAL;
    String PathPointDataCRC;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    float latitude2;
    float longitude2;
    
    public path_point(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.ApproachIdentifier1 = temp.substring(13,19).trim();
        this.Runway_Helipad_Identifier = temp.substring(19,24).trim();
        this.OperationType = temp.substring(24,26).trim();
        this.ContinuationRecordNo = temp.substring(26,27).trim();
        this.RouteID = temp.substring(27,28).trim(); 
        this.SBASSPI = temp.substring(28,30).trim();
        this.RefPathDataSelector = temp.substring(30,32).trim();
        this.RefpathDataIdentifier = temp.substring(32,36).trim();
        this.ApproachPO = temp.substring(36,37).trim();
        this.LandingThresholdPointLatitude = temp.substring(37,48).trim();
        this.LandingThresholdPointLongitude = temp.substring(48,60).trim();
        this.LTPEllipsoidHeight = temp.substring(60,66).trim();
        this.GlidePathAngle = temp.substring(66,70).trim();
        this.FLightPathAlignmentPointLatitude = temp.substring(70,81).trim();
        this.FLightPathAlignmentPointLongitude = temp.substring(81,93).trim();
        this.CourseWidthAtThreshold = temp.substring(93,98).trim();
        this.LengthOffset = temp.substring(98,102).trim();
        this.ThresholdCrossingHeight = temp.substring(102,108).trim();
        this.TCHIndicator = temp.substring(108,109).trim();
        this.HAL = temp.substring(109,112).trim();
        this.VAL = temp.substring(112,115).trim();
        this.PathPointDataCRC = temp.substring(115,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_Landing();
        this.CalculateLongAndLat_FlightPath();
    }

    public path_point() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_Landing(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'path_point' and column_name = 'geolocation_l');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table path_point add column geolocation_l geography(point);";
                stmt.execute(cre);
            }
            String up = "update path_point set geolocation_l = ST_MakePoint(landing_threshold_point_longitude, landing_threshold_point_latitude);";
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
    private void CalculateLongAndLat_Landing(){
        if(LandingThresholdPointLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(LandingThresholdPointLatitude.substring(1,3));
            float min = Integer.parseInt(LandingThresholdPointLatitude.substring(3,5));
            float sec = Integer.parseInt(LandingThresholdPointLatitude.substring(5,7));
            float msec = Integer.parseInt(LandingThresholdPointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LandingThresholdPointLatitude.substring(1,3));
            float min = Integer.parseInt(LandingThresholdPointLatitude.substring(3,5));
            float sec = Integer.parseInt(LandingThresholdPointLatitude.substring(5,7));
            float msec = Integer.parseInt(LandingThresholdPointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(LandingThresholdPointLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(LandingThresholdPointLongitude.substring(1,4));
            float min = Integer.parseInt(LandingThresholdPointLongitude.substring(4,6));
            float sec = Integer.parseInt(LandingThresholdPointLongitude.substring(6,8));
            float msec = Integer.parseInt(LandingThresholdPointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(LandingThresholdPointLongitude.substring(1,4));
            float min = Integer.parseInt(LandingThresholdPointLongitude.substring(4,6));
            float sec = Integer.parseInt(LandingThresholdPointLongitude.substring(6,8));
            float msec = Integer.parseInt(LandingThresholdPointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    //Calculate locations
    public static void CalculateGeolocations_FlightPath(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'path_point' and column_name = 'geolocation_fl');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table path_point add column geolocation_fl geography(point);";
                stmt.execute(cre);
            }
            String up = "update path_point set geolocation_fl = ST_MakePoint(flight_path_alignment_longitude, flight_path_alignment_latitude);";
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
    private void CalculateLongAndLat_FlightPath(){
        if(FLightPathAlignmentPointLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(1,3));
            float min = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(3,5));
            float sec = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(5,7));
            float msec = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(1,3));
            float min = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(3,5));
            float sec = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(5,7));
            float msec = Integer.parseInt(FLightPathAlignmentPointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(FLightPathAlignmentPointLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(1,4));
            float min = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(4,6));
            float sec = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(6,8));
            float msec = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(1,4));
            float min = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(4,6));
            float sec = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(6,8));
            float msec = Integer.parseInt(FLightPathAlignmentPointLongitude.substring(8,10));
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
            String ins = "INSERT INTO path_point VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                "\'"+ApproachIdentifier1+"\'" +","+
                "\'"+Runway_Helipad_Identifier+"\'" +","+
                "\'"+OperationType+"\'" +","+
                "\'"+ApproachIndicator+"\'" +","+
                "\'"+ContinuationRecordNo+"\'" +","+
                "\'"+RouteID+"\'" +","+
                "\'"+SBASSPI+"\'" +","+
                "\'"+RefPathDataSelector+"\'" +","+
                "\'"+RefpathDataIdentifier+"\'" +","+
                "\'"+ApproachPO+"\'" +","+
                latitude+","+
                longitude+","+    
                "\'"+LTPEllipsoidHeight+"\'" +","+
                "\'"+GlidePathAngle+"\'" +","+
                latitude2+","+
                longitude2+","+      
                "\'"+CourseWidthAtThreshold+"\'" +","+
                "\'"+LengthOffset+"\'" +","+
                "\'"+ThresholdCrossingHeight+"\'" +","+
                "\'"+TCHIndicator+"\'" +","+
                "\'"+HAL+"\'" +","+
                "\'"+VAL+"\'" +","+
                "\'"+PathPointDataCRC+"\'"+","+
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


    