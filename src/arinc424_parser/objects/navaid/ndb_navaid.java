/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.navaid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class ndb_navaid {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String AirportICAOIdentifier;
    String ICAOCode;
    String NDBIdentifier;
    String ICAOCode2;
    String ContinuationRecordNo;
    String NDBFrequency;
    String NDBClass;
    String NDBLatitude;
    String NDBLongitude;
    String MagneticVariation;
    String DatumCode;
    String NDBName;
    int FileRecordNo;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public ndb_navaid() { super(); }
    
    public ndb_navaid(String temp){
        
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.AirportICAOIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.NDBIdentifier = temp.substring(13,17).trim();
        this.ICAOCode2 = temp.substring(19,21).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.NDBFrequency = temp.substring(22,27).trim();
        this.NDBClass = temp.substring(27,32).trim();
        this.NDBLatitude = temp.substring(32,41).trim();
        this.NDBLongitude = temp.substring(41,51).trim();
        this.MagneticVariation = temp.substring(74,79).trim();
        this.DatumCode = temp.substring(90,93).trim();
        this.NDBName = temp.substring(93,123).trim();
        this.FileRecordNo = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        
        this.CalculateLongAndLat_NDB();
    }
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO ndb_navaid VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                "\'"+AirportICAOIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+NDBIdentifier+"\'"+","+
                "\'"+ICAOCode2+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+NDBFrequency+"\'"+","+
                "\'"+NDBClass+"\'"+","+
                latitude+","+
                longitude+","+
                "\'"+MagneticVariation+"\'"+","+
                "\'"+DatumCode+"\'"+","+        
                "\'"+NDBName+"\'"+","+        
                FileRecordNo+","+           
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
    
    public static void CalculateGeolocations_NDB(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'ndb_navaid' and column_name = 'geolocation_ndb');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table ndb_navaid add column geolocation_ndb geography(point);";
                stmt.execute(cre);
            }
            String up = "update ndb_navaid set geolocation_ndb = ST_MakePoint(ndb_longitude, ndb_latitude);";
            stmt.executeUpdate(up);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    private void CalculateLongAndLat_NDB(){
        if(NDBLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(NDBLatitude.substring(1,3));
            float min = Integer.parseInt(NDBLatitude.substring(3,5));
            float sec = Integer.parseInt(NDBLatitude.substring(5,7));
            float msec = Integer.parseInt(NDBLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(NDBLatitude.substring(1,3));
            float min = Integer.parseInt(NDBLatitude.substring(3,5));
            float sec = Integer.parseInt(NDBLatitude.substring(5,7));
            float msec = Integer.parseInt(NDBLatitude.substring(7,9));
            sec = sec + (msec)/100;
            latitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(NDBLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(NDBLongitude.substring(1,4));
            float min = Integer.parseInt(NDBLongitude.substring(4,6));
            float sec = Integer.parseInt(NDBLongitude.substring(6,8));
            float msec = Integer.parseInt(NDBLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(NDBLongitude.substring(1,4));
            float min = Integer.parseInt(NDBLongitude.substring(4,6));
            float sec = Integer.parseInt(NDBLongitude.substring(6,8));
            float msec = Integer.parseInt(NDBLongitude.substring(8,10));
            sec = sec + (msec)/100;
            longitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
}
