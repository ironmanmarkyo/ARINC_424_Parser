/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.airways;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class marker {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String MarkerIdentifier;
    String ICAOCode;
    String ContinuationRecordNo;
    String MarkerCode;
    String MarkerShape;
    String MarkerPower;
    String MarkerLatitude;  
    String MarkerLongitude; 
    String MinorAxis;
    String MagneticVariation;
    String FacilityElevation;
    String DatumCode;
    String MarkerName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    
    public marker(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.MarkerIdentifier = temp.substring(13,17).trim();
        this.ICAOCode = temp.substring(19,21).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.MarkerCode = temp.substring(22,26).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.MarkerShape = temp.substring(27,28).trim();
        this.MarkerPower = temp.substring(28,29).trim();
        this.MarkerLatitude = temp.substring(32,41).trim();
        this.MarkerLongitude = temp.substring(41,51).trim();
        this.MinorAxis = temp.substring(51,55).trim();
        this.MagneticVariation = temp.substring(74,79).trim();
        this.FacilityElevation = temp.substring(79,84).trim();
        this.DatumCode = temp.substring(84,87).trim();
        this.MarkerName = temp.substring(93,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_marker();
    }

    public marker() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_marker(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'marker' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table marker add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update marker set geolocation = ST_MakePoint(marker_longitude, marker_latitude);";
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
    private void CalculateLongAndLat_marker(){
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
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO marker VALUES(" +
                    "\'"+RecordType+"\'" +","+
                    "\'"+ CustomerAreaCode+"\'" +","+
                    "\'"+ SectionCode+"\'" +","+
                    "\'"+ SubsectionCode+"\'" +","+
                    "\'"+ MarkerIdentifier+"\'" +","+
                    "\'"+ ICAOCode+"\'" +","+
                    "\'"+ ContinuationRecordNo+"\'" +","+
                    "\'"+ MarkerCode+"\'" +","+
                    "\'"+ MarkerShape+"\'" +","+
                    "\'"+ MarkerPower+"\'" +","+
                    "\'"+ MarkerLatitude+"\'" +","+  
                    "\'"+ MarkerLongitude+"\'" +","+ 
                    "\'"+ MinorAxis+"\'" +","+
                    "\'"+ MagneticVariation+"\'" +","+
                    "\'"+ FacilityElevation+"\'" +","+
                    "\'"+ DatumCode+"\'" +","+
                    "\'"+ MarkerName+"\'" +","+
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

