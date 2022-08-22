/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.heliport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class heliport {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String HeliportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String AtaIataDesignator;
    String PADIdentifier;
    String ContinuationRecordNo;
    String SpeedLimitAltitude;
    String DatumCode;
    String IFRIndicator;
//    String LongestRunwaySurfaceCode;
    String HeliportLatitude;  // have to convert
    String HeliportLongitude; // have to convert
    String MagneticVariation;
    int HeliportElevation;
    int SpeedLimit;
    String RecommendedNavaid;
    String ICAOCode2;
    int TransitionsAltitude;
    int TransitionLevel;
    String PublicMilitaryIndicator;
    String TimeZone;
    String DaylightIndicator;
    String PADDimensions;
    String MagneticTrueIndicator;
//    String DatumCode;
    String HeliportName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public heliport(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.HeliportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.AtaIataDesignator = temp.substring(13,16).trim();
        this.PADIdentifier = temp.substring(16,21).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.SpeedLimitAltitude = temp.substring(22,27).trim();
        this.DatumCode = temp.substring(27,30).trim();
        this.IFRIndicator = temp.substring(30,31).trim();
//        this.LongestRunwaySurfaceCode = temp.substring(31,32).trim();
        this.HeliportLatitude = temp.substring(32,41).trim();
        this.HeliportLongitude = temp.substring(41,51).trim();
        this.MagneticVariation = temp.substring(51,56).trim();
        this.HeliportElevation = ((temp.substring(56,61).trim().isEmpty())?0:Integer.parseInt(temp.substring(56,61).trim()));
        this.SpeedLimit = ((temp.substring(61,64).trim().isEmpty())?0:Integer.parseInt(temp.substring(61,64).trim()));
        this.RecommendedNavaid = temp.substring(64,68).trim();
        this.ICAOCode2 = temp.substring(68,70).trim();
        this.TransitionsAltitude = ((temp.substring(70,75).trim().isEmpty())?0:Integer.parseInt(temp.substring(70,75).trim()));
        this.TransitionLevel = ((temp.substring(75,80).trim().isEmpty())?0:Integer.parseInt(temp.substring(21,22).trim()));
        this.PublicMilitaryIndicator = temp.substring(80,81).trim();
        this.TimeZone = temp.substring(81,84).trim();
        this.DaylightIndicator = temp.substring(84,85).trim();
        this.PADDimensions = temp.substring(85,91).trim();
        this.MagneticTrueIndicator = temp.substring(91,92).trim();
//        this.DatumCode = temp.substring(86,89).trim();
        this.HeliportName = temp.substring(93,123).trim();
        this.HeliportName = this.HeliportName.replace("\'"," ");
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_heliport();
    }

    public heliport() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_heliport(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'heliport' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table heliport add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update heliport set geolocation = ST_MakePoint(heliport_longitude, heliport_latitude);";
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
    private void CalculateLongAndLat_heliport(){
        if(HeliportLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(HeliportLatitude.substring(1,3));
            float min = Integer.parseInt(HeliportLatitude.substring(3,5));
            float sec = Integer.parseInt(HeliportLatitude.substring(5,7));
            float msec = Integer.parseInt(HeliportLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(HeliportLatitude.substring(1,3));
            float min = Integer.parseInt(HeliportLatitude.substring(3,5));
            float sec = Integer.parseInt(HeliportLatitude.substring(5,7));
            float msec = Integer.parseInt(HeliportLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(HeliportLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(HeliportLongitude.substring(1,4));
            float min = Integer.parseInt(HeliportLongitude.substring(4,6));
            float sec = Integer.parseInt(HeliportLongitude.substring(6,8));
            float msec = Integer.parseInt(HeliportLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(HeliportLongitude.substring(1,4));
            float min = Integer.parseInt(HeliportLongitude.substring(4,6));
            float sec = Integer.parseInt(HeliportLongitude.substring(6,8));
            float msec = Integer.parseInt(HeliportLongitude.substring(8,10));
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
            String ins = "INSERT INTO heliport VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+HeliportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+AtaIataDesignator+"\'"+","+
                "\'"+PADIdentifier+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+SpeedLimitAltitude+"\'"+","+
                "\'"+DatumCode+"\'"+","+
                "\'"+IFRIndicator+"\'"+","+
//                "\'"+LongestRunwaySurfaceCode+"\'"+","+
                latitude+","+
                longitude+","+     
                "\'"+MagneticVariation+"\'"+","+  
                HeliportElevation+","+
                SpeedLimit+","+        
                "\'"+RecommendedNavaid+"\'"+","+        
                "\'"+ICAOCode2+"\'"+","+
                TransitionsAltitude+","+
                TransitionLevel+","+
                "\'"+PublicMilitaryIndicator+"\'"+","+
                "\'"+TimeZone+"\'"+","+
                "\'"+DaylightIndicator+"\'"+","+
                "\'"+PADDimensions+"\'"+","+
                "\'"+MagneticTrueIndicator+"\'"+","+
//                "\'"+DatumCode+"\'"+","+        
                "\'"+HeliportName+"\'"+","+        
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
