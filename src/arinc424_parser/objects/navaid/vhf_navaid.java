/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.navaid;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author yohan
 */
public class vhf_navaid {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String AirportICAOIdentifier;
    String ICAOCode;
    String VORIdentifier;
    String ICAOCode2;
    String ContinuationRecordNo;
    String VORFrequency;
    String NAVAIDClass;
    String VORLatitude;
    String VORLongitude;
    String DMEIdent;
    String DMELatitude;
    String DMELongitude;
    String StationDeclination;
    String DMEElevation;
    String FigureofMerit;
    String ILSDMEBias;
    String FrequnecyProtection;
    String DatumCode;
    String VORName;
    int FileRecordNo;
    int CycleDate;
    
    float Dlatitude;
    float Dlongitude;
    
    float Vlatitude;
    float Vlongitude;
    
    public vhf_navaid() { super(); }
    
    public vhf_navaid(String temp){
        
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.AirportICAOIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.VORIdentifier = temp.substring(13,17).trim();
        this.ICAOCode2 = temp.substring(19,21).trim();
        this.ContinuationRecordNo = temp.substring(21,22).trim();
        this.VORFrequency = temp.substring(22,27).trim();
        this.NAVAIDClass = temp.substring(27,32).trim();
        this.VORLatitude = temp.substring(32,41).trim();
        this.VORLongitude = temp.substring(41,51).trim();
        this.DMEIdent = temp.substring(51,55).trim();
        this.DMELatitude = temp.substring(55,64).trim();
        this.DMELongitude = temp.substring(64,74).trim();
        this.StationDeclination = temp.substring(74,79).trim();
        this.DMEElevation = temp.substring(79,84).trim();
        this.FigureofMerit = temp.substring(84,85).trim();
        this.ILSDMEBias = temp.substring(85,87).trim();
        this.FrequnecyProtection = temp.substring(87,90).trim();
        this.DatumCode = temp.substring(90,93).trim();
        this.VORName = temp.substring(93,123).trim();
        this.VORName = this.VORName.replace("\'"," ");
        this.FileRecordNo = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        
        
        if(DMELatitude.length()!=0){
            this.CalculateLongAndLat_DME();
        }else{
            Dlatitude = 0;
            Dlongitude = 0;
        }
        if(VORLatitude.length()!=0){
            this.CalculateLongAndLat_VOR(); 
        }else{
            Vlatitude = 0;
            Vlongitude = 0;
        }
    }
    
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO vhf_navaid VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                "\'"+AirportICAOIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+VORIdentifier+"\'"+","+
                "\'"+ICAOCode2+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+VORFrequency+"\'"+","+
                "\'"+NAVAIDClass+"\'"+","+
                Vlatitude+","+
                Vlongitude+","+     
                "\'"+DMEIdent+"\'"+","+
                Dlatitude+","+
                Dlongitude+","+     
                "\'"+StationDeclination+"\'"+","+        
                "\'"+DMEElevation+"\'"+","+
                "\'"+FigureofMerit+"\'"+","+
                "\'"+ILSDMEBias+"\'"+","+
                "\'"+FrequnecyProtection+"\'"+","+
                "\'"+DatumCode+"\'"+","+        
                "\'"+VORName+"\'"+","+        
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

    public static void CalculateGeolocations_DME(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'vhf_navaid' and column_name = 'geolocation_dme');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table vhf_navaid add column geolocation_dme geography(point);";
                stmt.execute(cre);
            }
            String up = "update vhf_navaid set geolocation_dme = ST_MakePoint(dme_longitude, dme_latitude);";
            stmt.executeUpdate(up);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public static void CalculateGeolocations_VOR(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'vhf_navaid' and column_name = 'geolocation_vor');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table vhf_navaid add column geolocation_vor geography(point);";
                stmt.execute(cre);
            }
            String up = "update vhf_navaid set geolocation_vor = ST_MakePoint(vor_longitude, vor_latitude);";
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
    private void CalculateLongAndLat_DME(){
        if(DMELatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(DMELatitude.substring(1,3));
            float min = Integer.parseInt(DMELatitude.substring(3,5));
            float sec = Integer.parseInt(DMELatitude.substring(5,7));
            float msec = Integer.parseInt(DMELatitude.substring(7,9));
            sec = sec + (msec)/100;
            Dlatitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(DMELatitude.substring(1,3));
            float min = Integer.parseInt(DMELatitude.substring(3,5));
            float sec = Integer.parseInt(DMELatitude.substring(5,7));
            float msec = Integer.parseInt(DMELatitude.substring(7,9));
            sec = sec + (msec)/100;
            Dlatitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(DMELongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(DMELongitude.substring(1,4));
            float min = Integer.parseInt(DMELongitude.substring(4,6));
            float sec = Integer.parseInt(DMELongitude.substring(6,8));
            float msec = Integer.parseInt(DMELongitude.substring(8,10));
            sec = sec + (msec)/100;
            Dlongitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(DMELongitude.substring(1,4));
            float min = Integer.parseInt(DMELongitude.substring(4,6));
            float sec = Integer.parseInt(DMELongitude.substring(6,8));
            float msec = Integer.parseInt(DMELongitude.substring(8,10));
            sec = sec + (msec)/100;
            Dlongitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
    private void CalculateLongAndLat_VOR(){
        if(VORLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(VORLatitude.substring(1,3));
            float min = Integer.parseInt(VORLatitude.substring(3,5));
            float sec = Integer.parseInt(VORLatitude.substring(5,7));
            float msec = Integer.parseInt(VORLatitude.substring(7,9));
            sec = sec + (msec)/100;
            Dlatitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(VORLatitude.substring(1,3));
            float min = Integer.parseInt(VORLatitude.substring(3,5));
            float sec = Integer.parseInt(VORLatitude.substring(5,7));
            float msec = Integer.parseInt(VORLatitude.substring(7,9));
            sec = sec + (msec)/100;
            Dlatitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(VORLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(VORLongitude.substring(1,4));
            float min = Integer.parseInt(VORLongitude.substring(4,6));
            float sec = Integer.parseInt(VORLongitude.substring(6,8));
            float msec = Integer.parseInt(VORLongitude.substring(8,10));
            sec = sec + (msec)/100;
            Dlongitude = deg + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(VORLongitude.substring(1,4));
            float min = Integer.parseInt(VORLongitude.substring(4,6));
            float sec = Integer.parseInt(VORLongitude.substring(6,8));
            float msec = Integer.parseInt(VORLongitude.substring(8,10));
            sec = sec + (msec)/100;
            Dlongitude = -(deg + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
    
}