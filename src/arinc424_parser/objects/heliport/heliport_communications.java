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
public class heliport_communications {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String HeliportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String CommunicationsType;
    String CommunicationsFrequency;
    String GuardTransmit;
    String FrequencyUnits;
    String ContinuationRecordNo;
    String ServiceIndicator;
    String RadarService;
    String Modulation;
    String SignalEmission;
    String Latitude;  // have to convert
    String Longitude; // have to convert
    String MagnecticVariation;
    String FacilityElevation;
    String H24Indicator;
    String Sectorization;
    String AltitudeDescription;
    String CommunicationAltitude;
    String CommunicationAltitude2;
    String SectorFacility;
    String ICAOCode2;
    String SectionCode2;
    String SubsectionCode2;
    String DistanceDescription;
    String CommunicationDistance;
    String RemoteFacility;
    String ICAOCode3;
    String SectionCode3;
    String SubsectionCode3;
    String CallSign;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public heliport_communications(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.HeliportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.CommunicationsType = temp.substring(13,16).trim();
        this.CommunicationsFrequency = temp.substring(16,23).trim();
        this.GuardTransmit = temp.substring(23,24).trim();
        this.FrequencyUnits = temp.substring(24,25).trim();
        this.ContinuationRecordNo = temp.substring(25,26).trim();
        this.ServiceIndicator = temp.substring(26,29).trim();
        this.RadarService = temp.substring(29,30).trim();
        this.Modulation= temp.substring(30,31).trim();
        this.SignalEmission = temp.substring(31,32).trim();
        this.Latitude = temp.substring(32,41).trim();
        this.Longitude = temp.substring(41,51).trim();
        this.MagnecticVariation = temp.substring(51,56).trim();
        this.FacilityElevation = temp.substring(56,61).trim();
        this.H24Indicator = temp.substring(61,62).trim();
        this.Sectorization = temp.substring(62,68).trim();
        this.AltitudeDescription = temp.substring(68,69).trim();
        this.CommunicationAltitude = temp.substring(69,74).trim();
        this.CommunicationAltitude2 = temp.substring(74,79).trim();
        this.SectorFacility = temp.substring(79,83).trim();
        this.ICAOCode2 = temp.substring(83,85).trim();
        this.SectionCode2 = temp.substring(85,86).trim();
        this.SubsectionCode2 = temp.substring(86,87).trim();
        this.DistanceDescription = temp.substring(87,88).trim();
        this.CommunicationDistance = temp.substring(88,90).trim();
        this.RemoteFacility = temp.substring(90,94).trim();
        this.ICAOCode3 = temp.substring(94,96).trim();
        this.SectionCode3 = temp.substring(96,97).trim();
        this.SubsectionCode3 = temp.substring(97,98).trim();
        this.CallSign = temp.substring(98,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_heliport_communications();
    }

    public heliport_communications() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_heliport_communications(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'heliport_communications' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table heliport_communications add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update heliport_communications set geolocation = ST_MakePoint(longitude, latitude);";
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
    private void CalculateLongAndLat_heliport_communications(){
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
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO heliport_communications VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+ CustomerAreaCode+"\'"+","+ 
                "\'"+ SectionCode+"\'"+","+ 
                "\'"+ HeliportIdentifier+"\'"+","+ 
                "\'"+ ICAOCode+"\'"+","+ 
                "\'"+ SubsectionCode+"\'"+","+ 
                "\'"+ CommunicationsType+"\'"+","+ 
                "\'"+ CommunicationsFrequency+"\'"+","+ 
                "\'"+ GuardTransmit+"\'"+","+ 
                "\'"+ FrequencyUnits+"\'"+","+ 
                "\'"+ ContinuationRecordNo+"\'"+","+ 
                "\'"+ ServiceIndicator+"\'"+","+ 
                "\'"+ RadarService+"\'"+","+  
                "\'"+ Modulation+"\'"+","+ 
                "\'"+ SignalEmission+"\'"+","+ 
                latitude+","+ 
                longitude+","+ 
                "\'"+ MagnecticVariation+"\'"+","+ 
                "\'"+ FacilityElevation+"\'"+","+ 
                "\'"+ H24Indicator+"\'"+","+ 
                "\'"+ Sectorization+"\'"+","+ 
                "\'"+ AltitudeDescription+"\'"+","+ 
                "\'"+ CommunicationAltitude+"\'"+","+ 
                "\'"+ CommunicationAltitude2+"\'"+","+ 
                "\'"+ SectorFacility+"\'"+","+ 
                "\'"+ ICAOCode2+"\'"+","+ 
                "\'"+ SectionCode2+"\'"+","+ 
                "\'"+ SubsectionCode2+"\'"+","+ 
                "\'"+ DistanceDescription+"\'"+","+ 
                "\'"+ CommunicationDistance+"\'"+","+ 
                "\'"+ RemoteFacility+"\'"+","+ 
                "\'"+ ICAOCode3+"\'"+","+ 
                "\'"+ SectionCode3+"\'"+","+ 
                "\'"+ SubsectionCode3+"\'"+","+ 
                "\'"+ CallSign+"\'"+","+    
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
