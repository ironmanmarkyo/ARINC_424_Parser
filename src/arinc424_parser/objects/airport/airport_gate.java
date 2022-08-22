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
public class airport_gate{
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportICAOIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String GateIdentifier;
    String ContinuationRecordNo;
    String GateLatitude;  // have to convert
    String GateLongitude; // have to convert
    String Notes;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    
    public airport_gate(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportICAOIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.GateIdentifier = temp.substring(13,18).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.GateLatitude = temp.substring(32,41).trim();
        this.GateLongitude = temp.substring(41,51).trim();
        this.Notes = temp.substring(98,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_airport_gate();
    }

    public airport_gate() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_airport_gate(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'airport_gate' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table airport_gate add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update airport_gate set geolocation = ST_MakePoint(gate_longitude, gate_latitude);";
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
    private void CalculateLongAndLat_airport_gate(){
        if(GateLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(GateLatitude.substring(1,3));
            float min = Integer.parseInt(GateLatitude.substring(3,5));
            float sec = Integer.parseInt(GateLatitude.substring(5,7));
            float msec = Integer.parseInt(GateLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(GateLatitude.substring(1,3));
            float min = Integer.parseInt(GateLatitude.substring(3,5));
            float sec = Integer.parseInt(GateLatitude.substring(5,7));
            float msec = Integer.parseInt(GateLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(GateLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(GateLongitude.substring(1,4));
            float min = Integer.parseInt(GateLongitude.substring(4,6));
            float sec = Integer.parseInt(GateLongitude.substring(6,8));
            float msec = Integer.parseInt(GateLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(GateLongitude.substring(1,4));
            float min = Integer.parseInt(GateLongitude.substring(4,6));
            float sec = Integer.parseInt(GateLongitude.substring(6,8));
            float msec = Integer.parseInt(GateLongitude.substring(8,10));
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
            String ins = "INSERT INTO airport_gate VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportICAOIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+GateIdentifier+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                latitude+","+
                longitude+","+     
                "\'"+Notes+"\'"+","+        
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
