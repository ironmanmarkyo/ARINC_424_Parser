/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airport;
//importing libraries

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author yohan
 */
public class airport {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportICAOIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String AtaIataDesignator;
    String ContinuationRecordNo;
    String SpeedLimitAltitude;
    String LongestRunway;
    String IFRCapability;
    String LongestRunwaySurfaceCode;
    String AirportReferencePointLatitude;  // have to convert
    String AirportReferencePointLongitude; // have to convert
    String MagneticVariation;
    int AirportElevation;
    int SpeedLimit;
    String RecommendedNavaid;
    String ICAOCode2;
    int TransitionsAltitude;
    int TransitionLevel;
    String PublicMilitaryIndicator;
    String TimeZone;
    String DaylightIndicator;
    String MagneticTrueIndicator;
    String DatumCode;
    String AirportName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public airport(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportICAOIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.AtaIataDesignator = temp.substring(13,16).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.SpeedLimitAltitude = temp.substring(22,27).trim();
        this.LongestRunway = temp.substring(27,30).trim();
        this.IFRCapability = temp.substring(30,31).trim();
        this.LongestRunwaySurfaceCode = temp.substring(31,32).trim();
        this.AirportReferencePointLatitude = temp.substring(32,41).trim();
        this.AirportReferencePointLongitude = temp.substring(41,51).trim();
        this.MagneticVariation = temp.substring(51,56).trim();
        this.AirportElevation = ((temp.substring(56,61).trim().isEmpty())?0:Integer.parseInt(temp.substring(56,61).trim()));
        this.SpeedLimit = ((temp.substring(61,64).trim().isEmpty())?0:Integer.parseInt(temp.substring(61,64).trim()));
        this.RecommendedNavaid = temp.substring(64,68).trim();
        this.ICAOCode2 = temp.substring(68,70).trim();
        this.TransitionsAltitude = ((temp.substring(70,75).trim().isEmpty())?0:Integer.parseInt(temp.substring(70,75).trim()));
        this.TransitionLevel = ((temp.substring(75,80).trim().isEmpty())?0:Integer.parseInt(temp.substring(21,22).trim()));
        this.PublicMilitaryIndicator = temp.substring(80,81).trim();
        this.TimeZone = temp.substring(81,84).trim();
        this.DaylightIndicator = temp.substring(84,85).trim();
        this.MagneticTrueIndicator = temp.substring(85,86).trim();
        this.DatumCode = temp.substring(86,89).trim();
        this.AirportName = temp.substring(93,123).trim();
        this.AirportName = this.AirportName.replace("\'"," ");
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_airport();
    }

    public airport() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_airport(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'airport' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table airport add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update airport set geolocation = ST_MakePoint(airport_reference_point_longitude, airport_reference_point_latitude);";
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
    private void CalculateLongAndLat_airport(){
        if(AirportReferencePointLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(AirportReferencePointLatitude.substring(1,3));
            float min = Integer.parseInt(AirportReferencePointLatitude.substring(3,5));
            float sec = Integer.parseInt(AirportReferencePointLatitude.substring(5,7));
            float msec = Integer.parseInt(AirportReferencePointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(AirportReferencePointLatitude.substring(1,3));
            float min = Integer.parseInt(AirportReferencePointLatitude.substring(3,5));
            float sec = Integer.parseInt(AirportReferencePointLatitude.substring(5,7));
            float msec = Integer.parseInt(AirportReferencePointLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(AirportReferencePointLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(AirportReferencePointLongitude.substring(1,4));
            float min = Integer.parseInt(AirportReferencePointLongitude.substring(4,6));
            float sec = Integer.parseInt(AirportReferencePointLongitude.substring(6,8));
            float msec = Integer.parseInt(AirportReferencePointLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(AirportReferencePointLongitude.substring(1,4));
            float min = Integer.parseInt(AirportReferencePointLongitude.substring(4,6));
            float sec = Integer.parseInt(AirportReferencePointLongitude.substring(6,8));
            float msec = Integer.parseInt(AirportReferencePointLongitude.substring(8,10));
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
            String ins = "INSERT INTO airport VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportICAOIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+AtaIataDesignator+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+SpeedLimitAltitude+"\'"+","+
                "\'"+LongestRunway+"\'"+","+
                "\'"+IFRCapability+"\'"+","+
                "\'"+LongestRunwaySurfaceCode+"\'"+","+
                latitude+","+
                longitude+","+     
                "\'"+MagneticVariation+"\'"+","+  
                AirportElevation+","+
                SpeedLimit+","+        
                "\'"+RecommendedNavaid+"\'"+","+        
                "\'"+ICAOCode2+"\'"+","+
                TransitionsAltitude+","+
                TransitionLevel+","+
                "\'"+PublicMilitaryIndicator+"\'"+","+
                "\'"+TimeZone+"\'"+","+
                "\'"+DaylightIndicator+"\'"+","+
                "\'"+MagneticTrueIndicator+"\'"+","+
                "\'"+DatumCode+"\'"+","+        
                "\'"+AirportName+"\'"+","+        
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

//Constructor from a given line
