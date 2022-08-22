/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class communications_enroute {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String FIRRDOIdent;
    String FIRUIRAddress;
    String Indicator;
    String RemoteName;
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
    String AltitudeDescription;
    String CommunicationAltitude;
    String CommunicationAltitude2;
    String RemoteFacility;
    String ICAOCode;
    String SectionCode2;
    String SubsectionCode2;
    int FileRecordNumber;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public communications_enroute(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.FIRRDOIdent = temp.substring(6,10).trim();
        this.FIRUIRAddress = temp.substring(11,14).trim();
        this.Indicator = temp.substring(14,15).trim();
        this.RemoteName = temp.substring(18,43).trim();        
        this.CommunicationsType = temp.substring(43,46).trim();
        this.CommunicationsFrequency = temp.substring(46,53).trim();
        this.GuardTransmit = temp.substring(53,54).trim();
        this.FrequencyUnits = temp.substring(54,55).trim();
        this.ContinuationRecordNo = temp.substring(55,56).trim();
        this.ServiceIndicator = temp.substring(56,59).trim();
        this.RadarService = temp.substring(59,60).trim();
        this.Modulation= temp.substring(60,61).trim();
        this.SignalEmission = temp.substring(61,62).trim();
        this.Latitude = temp.substring(62,71).trim();
        this.Longitude = temp.substring(71,81).trim();
        this.MagnecticVariation = temp.substring(81,86).trim();
        this.FacilityElevation = temp.substring(86,91).trim();
        this.H24Indicator = temp.substring(91,92).trim();
        this.AltitudeDescription = temp.substring(92,93).trim();
        this.CommunicationAltitude = temp.substring(93,98).trim();
        this.CommunicationAltitude2 = temp.substring(98,103).trim();
        this.RemoteFacility = temp.substring(104,107).trim();
        this.ICAOCode = temp.substring(107,109).trim();
        this.SectionCode2 = temp.substring(109,110).trim();
        this.SubsectionCode2 = temp.substring(110,111).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        
        if(Latitude.length()!=0){
            this.CalculateLongAndLat_communications();
        }
    }

    public communications_enroute() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    //Calculate locations
    public static void CalculateGeolocations_communications(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'communications_enroute' and column_name = 'geolocation');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table communications_enroute add column geolocation geography(point);";
                stmt.execute(cre);
            }
            String up = "update communications_enroute set geolocation = ST_MakePoint(longitude, latitude);";
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
    private void CalculateLongAndLat_communications(){
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
            String ins = "INSERT INTO communications_enroute VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+ CustomerAreaCode+"\'"+","+ 
                "\'"+ SectionCode+"\'"+","+ 
                "\'"+ SubsectionCode+"\'"+","+ 
                "\'"+ FIRRDOIdent+"\'"+","+ 
                "\'"+ FIRUIRAddress+"\'"+","+
                "\'"+ Indicator+"\'"+","+ 
                "\'"+ RemoteName+"\'"+","+  
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
                "\'"+ AltitudeDescription+"\'"+","+ 
                "\'"+ CommunicationAltitude+"\'"+","+ 
                "\'"+ CommunicationAltitude2+"\'"+","+ 
                "\'"+ RemoteFacility+"\'"+","+ 
                "\'"+ ICAOCode+"\'"+","+ 
                "\'"+ SectionCode2+"\'"+","+ 
                "\'"+ SubsectionCode2+"\'"+","+ 
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
