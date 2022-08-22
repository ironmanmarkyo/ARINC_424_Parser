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
public class fir_uir {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String FIRUIRIdentifier; 
    String FIRUIRAddress;
    String FIRUIRIndicator;
    String SequenceNumber;
    String ContinuationRecordNo;
    String AdjacentFIRIdentifier;
    String AdjacentUIRIdentifier;
    String ReportingUnitsSpeed;
    String ReportingUnitsAltitude;
    String EntryReport;
    String BoundaryVia;  // have to convert
    String FIRUIRLatitude; // have to convert
    String FIRUIRLongitude;
    String ArcOriginLatitude;
    String ArcOrginLongitude;
    String ArcDistance;
    String ArcBearing;
    String FIRUpperLimit;
    String UIRLowerLimit;
    String UIRUpperLimit;
    String CruiseTableInd;
    String FIRUIRName;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    float latitude2;
    float longitude2;
    
    
    public fir_uir(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.FIRUIRIdentifier = temp.substring(6,10).trim();
        this.FIRUIRAddress = temp.substring(10,14).trim();
        this.FIRUIRIndicator = temp.substring(14,15).trim();
        this.SequenceNumber = temp.substring(15,19).trim();
        this.ContinuationRecordNo = temp.substring(19,20).trim();
        this.AdjacentFIRIdentifier = temp.substring(20,24).trim();
        this.AdjacentUIRIdentifier = temp.substring(24,28).trim();
        this.ReportingUnitsSpeed = temp.substring(28,29).trim();
        this.ReportingUnitsAltitude = temp.substring(29,30).trim();
        this.EntryReport = temp.substring(30,31).trim();
        this.BoundaryVia = temp.substring(32,34).trim();
        this.FIRUIRLatitude = temp.substring(34,43).trim();
        this.FIRUIRLongitude = temp.substring(43,53).trim();
        this.ArcOriginLatitude = temp.substring(53,62).trim();
        this.ArcOrginLongitude = temp.substring(62,72).trim();
        this.ArcDistance = temp.substring(72,76).trim();
        this.ArcBearing = temp.substring(76,80).trim();
        this.FIRUpperLimit = temp.substring(80,85).trim();
        this.UIRLowerLimit = temp.substring(85,90).trim();
        this.UIRUpperLimit = temp.substring(90,95).trim();
        this.CruiseTableInd = temp.substring(95,97).trim();
        this.FIRUIRName = temp.substring(98,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        
        this.CalculateLongAndLat_arc();
        this.CalculateLongAndLat_fu();
    }

    public fir_uir() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO fir_uir VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+FIRUIRIdentifier+"\'" +","+
                "\'"+FIRUIRAddress+"\'"+","+
                "\'"+FIRUIRIndicator+"\'"+","+
                "\'"+SequenceNumber+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+AdjacentFIRIdentifier+"\'"+","+
                "\'"+AdjacentUIRIdentifier+"\'"+","+
                "\'"+ReportingUnitsSpeed+"\'"+","+
                "\'"+ReportingUnitsAltitude+"\'"+","+
                "\'"+EntryReport+"\'"+","+
                "\'"+BoundaryVia+"\'"+","+
                latitude+","+
                longitude+","+  
                latitude2+","+
                longitude2+","+        
                "\'"+ArcDistance+"\'"+","+          
                "\'"+ArcBearing+"\'"+","+    
                "\'"+FIRUpperLimit+"\'"+","+
                "\'"+UIRLowerLimit+"\'"+","+
                "\'"+UIRUpperLimit+"\'"+","+
                "\'"+CruiseTableInd+"\'"+","+
                "\'"+FIRUIRName+"\'"+","+
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
            String sql = "Select exists(select from information_schema.columns where table_name = 'fir_uir' and column_name = 'geolocation_arc');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table fir_uir add column geolocation_arc geography(point);";
                stmt.execute(cre);
            }
            String up = "update fir_uir set geolocation_arc = ST_MakePoint(arc_longitude, arc_latitude);";
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
    private void CalculateLongAndLat_fu(){
        if(FIRUIRLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(FIRUIRLatitude.substring(1,3));
            float min = Integer.parseInt(FIRUIRLatitude.substring(3,5));
            float sec = Integer.parseInt(FIRUIRLatitude.substring(5,7));
            float msec = Integer.parseInt(FIRUIRLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(FIRUIRLatitude.substring(1,3));
            float min = Integer.parseInt(FIRUIRLatitude.substring(3,5));
            float sec = Integer.parseInt(FIRUIRLatitude.substring(5,7));
            float msec = Integer.parseInt(FIRUIRLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(FIRUIRLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(FIRUIRLongitude.substring(1,4));
            float min = Integer.parseInt(FIRUIRLongitude.substring(4,6));
            float sec = Integer.parseInt(FIRUIRLongitude.substring(6,8));
            float msec = Integer.parseInt(FIRUIRLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(FIRUIRLongitude.substring(1,4));
            float min = Integer.parseInt(FIRUIRLongitude.substring(4,6));
            float sec = Integer.parseInt(FIRUIRLongitude.substring(6,8));
            float msec = Integer.parseInt(FIRUIRLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    
    //Calculate locations
    public static void CalculateGeolocations_fu(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'fir_uir' and column_name = 'geolocation_fu');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table fir_uir add column geolocation_fu geography(point);";
                stmt.execute(cre);
            }
            String up = "update fir_uir set geolocation_fu = ST_MakePoint(fir_uir_longitude, fir_uir_latitude);";
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
