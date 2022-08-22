/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airspace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class controlled_airspace {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String ICAOCode; 
    String AirspaceType;
    String AirspaceCenter;
    String SectionCode2;
    String SubsectionCode2;
    String AirspaceClassification;
    String MultipleCode;
    String SequenceNumber;
    String ContinuationRecordNo;
    String Level;
    String TimeCode;
    String NOTAM;
//    String EntryReport;
    String BoundaryVia;  // have to convert
    String Latitude; // have to convert
    String Longitude;
    String ArcOriginLatitude;
    String ArcOrginLongitude;
    String ArcDistance;
    String ArcBearing;
    String RNP;
    String LowerLimit;
    String UnitIndicator;
    String UpperLimit;
    String UnitIndicator2;
    String ControlledAirspaceName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    float latitude2;
    float longitude2;
    
    
    public controlled_airspace(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.ICAOCode = temp.substring(6,8).trim();
        this.AirspaceType = temp.substring(8,9).trim();
        this.AirspaceCenter = temp.substring(9,14).trim();
        this.SectionCode2 = temp.substring(14,15).trim();
        this.SubsectionCode2 = temp.substring(15,16).trim();
        this.AirspaceClassification = temp.substring(16,17).trim();
        this.MultipleCode = temp.substring(19,20).trim();
        this.SequenceNumber = temp.substring(20,24).trim();
        this.ContinuationRecordNo = temp.substring(24,25).trim();
        this.Level = temp.substring(25,26).trim();
        this.TimeCode = temp.substring(26,27).trim();
        this.NOTAM = temp.substring(27,28).trim();
//        this.EntryReport = temp.substring(30,31).trim();
        this.BoundaryVia = temp.substring(30,32).trim();
        this.Latitude = temp.substring(32,41).trim();
        this.Longitude = temp.substring(41,51).trim();
        this.ArcOriginLatitude = temp.substring(51,60).trim();
        this.ArcOrginLongitude = temp.substring(60,70).trim();
        this.ArcDistance = temp.substring(70,74).trim();
        this.ArcBearing = temp.substring(74,78).trim();
        this.RNP = temp.substring(78,81).trim();
        this.LowerLimit = temp.substring(81,86).trim();
        this.UnitIndicator = temp.substring(86,87).trim();
        this.UpperLimit = temp.substring(87,92).trim();
        this.UnitIndicator2 = temp.substring(92,93).trim();
        this.ControlledAirspaceName = temp.substring(93,123).trim();
        this.ControlledAirspaceName = this.ControlledAirspaceName.replace("\'", " ");
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        
        if(Latitude.length()!=0){
            this.CalculateLongAndLat();
        }
        if(ArcOriginLatitude.length()!=0){
            this.CalculateLongAndLat_arc();
        }
        
    }

    public controlled_airspace() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO controlled_airspace VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+ICAOCode+"\'" +","+
                "\'"+AirspaceType+"\'"+","+
                "\'"+AirspaceCenter+"\'"+","+
                "\'"+SectionCode2+"\'" +","+
                "\'"+SubsectionCode2+"\'"+","+
                "\'"+AirspaceClassification+"\'" +","+
                "\'"+MultipleCode+"\'"+","+
                "\'"+SequenceNumber+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+Level+"\'"+","+
                "\'"+TimeCode+"\'"+","+
                "\'"+NOTAM+"\'"+","+
//                "\'"+EntryReport+"\'"+","+
                "\'"+BoundaryVia+"\'"+","+
                latitude+","+
                longitude+","+  
                latitude2+","+
                longitude2+","+        
                "\'"+ArcDistance+"\'"+","+          
                "\'"+ArcBearing+"\'"+","+            
                "\'"+RNP+"\'"+","+   
                "\'"+LowerLimit+"\'"+","+
                "\'"+UnitIndicator+"\'"+","+
                "\'"+UpperLimit+"\'"+","+
                "\'"+UnitIndicator2+"\'"+","+
                "\'"+ControlledAirspaceName+"\'"+","+
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
    
    
    //Calculate locations
    public static void CalculateGeolocations_arc(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'controlled_airspace' and column_name = 'geolocation_arc');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table controlled_airspace add column geolocation_arc geography(point);";
                stmt.execute(cre);
            }
            String up = "update controlled_airspace set geolocation_arc = ST_MakePoint(arc_longitude, arc_latitude);";
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
    private void CalculateLongAndLat(){
        if(Latitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(Latitude.substring(1,3));
            float min = Integer.parseInt(Latitude.substring(3,5));
            float sec = Integer.parseInt(Latitude.substring(5,7));
            float msec = Integer.parseInt(Latitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(Latitude.substring(1,3));
            float min = Integer.parseInt(Latitude.substring(3,5));
            float sec = Integer.parseInt(Latitude.substring(5,7));
            float msec = Integer.parseInt(Latitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(Longitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(Longitude.substring(1,4));
            float min = Integer.parseInt(Longitude.substring(4,6));
            float sec = Integer.parseInt(Longitude.substring(6,8));
            float msec = Integer.parseInt(Longitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(Longitude.substring(1,4));
            float min = Integer.parseInt(Longitude.substring(4,6));
            float sec = Integer.parseInt(Longitude.substring(6,8));
            float msec = Integer.parseInt(Longitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    
    //Calculate locations
    public static void CalculateGeolocations(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'controlled_airspace' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table controlled_airspace add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update controlled_airspace set geolocation = ST_MakePoint(longitude, latitude);";
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
    private void CalculateLongAndLat_arc(){
        if(ArcOriginLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(ArcOriginLatitude.substring(1,3));
            float min = Integer.parseInt(ArcOriginLatitude.substring(3,5));
            float sec = Integer.parseInt(ArcOriginLatitude.substring(5,7));
            float msec = Integer.parseInt(ArcOriginLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(ArcOriginLatitude.substring(1,3));
            float min = Integer.parseInt(ArcOriginLatitude.substring(3,5));
            float sec = Integer.parseInt(ArcOriginLatitude.substring(5,7));
            float msec = Integer.parseInt(ArcOriginLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(ArcOrginLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(ArcOrginLongitude.substring(1,4));
            float min = Integer.parseInt(ArcOrginLongitude.substring(4,6));
            float sec = Integer.parseInt(ArcOrginLongitude.substring(6,8));
            float msec = Integer.parseInt(ArcOrginLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(ArcOrginLongitude.substring(1,4));
            float min = Integer.parseInt(ArcOrginLongitude.substring(4,6));
            float sec = Integer.parseInt(ArcOrginLongitude.substring(6,8));
            float msec = Integer.parseInt(ArcOrginLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude2 = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
}
