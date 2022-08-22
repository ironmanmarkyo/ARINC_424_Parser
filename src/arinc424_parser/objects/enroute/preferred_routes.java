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
public class preferred_routes {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String RouteIdentifier;
    String PreferredRouteUseIndicator;
    String SequenceNumber;
    String ContinuationRecordNumber;
    String ToFixIdentifier;
    String ICAOCode;
    String SectionCode2;
    String SubsectionCode2;  // have to convert
    String ViaCode; // have to convert
    String SIDSTARAWYident;
    String AreaCode;
    String Level;
    String RouteType;
    String InitialAirportFix;
    String ICAOCode2;
    String SectionCode3;
    String SubsectionCode3;
    String TerminusAirportFix;
    String ICAOCode3;
    String SectionCode4;
    String SubsectionCode4;
    String MinimumAltitude;
    String MaximumAltitude;
    String TimeCode;
    String AircraftUseGroup;
    String DirectionRestriction;
    String AltitudeDescription;
    String AltitudeOne;
    String ALtitudeTwo;    
    int FileRecordNumber;
    int CycleDate;
    
    
    public preferred_routes(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.RouteIdentifier = temp.substring(13,23).trim();
        this.PreferredRouteUseIndicator = temp.substring(23,25).trim();
        this.SequenceNumber = temp.substring(25,29).trim();
        this.ContinuationRecordNumber = temp.substring(38,39).trim();
        this.ToFixIdentifier = temp.substring(39,44).trim();
        this.ICAOCode = temp.substring(44,46).trim();
        this.SectionCode2 = temp.substring(46,47).trim();
        this.SubsectionCode2 = temp.substring(47,48).trim();
        this.ViaCode = temp.substring(48,51).trim();
        this.SIDSTARAWYident = temp.substring(51,57).trim();
        this.AreaCode = temp.substring(57,60).trim();
        this.Level = temp.substring(60,61).trim();
        this.RouteType = temp.substring(61,62).trim();
        this.InitialAirportFix = temp.substring(62,67).trim();
        this.ICAOCode2 = temp.substring(67,69).trim();
        this.SectionCode3 = temp.substring(69,70).trim();
        this.SubsectionCode3 = temp.substring(70,71).trim();
        this.TerminusAirportFix = temp.substring(71,76).trim();
        this.ICAOCode3 = temp.substring(76,78).trim();
        this.SectionCode4 = temp.substring(78,79).trim();
        this.SubsectionCode4 = temp.substring(79,80).trim();
        this.MinimumAltitude = temp.substring(80,85).trim();
        this.MaximumAltitude = temp.substring(85,90).trim();
        this.TimeCode = temp.substring(90,91).trim();
        this.AircraftUseGroup = temp.substring(91,93).trim();
        this.DirectionRestriction = temp.substring(93,94).trim();
        this.AltitudeDescription = temp.substring(94,95).trim();
        this.AltitudeOne = temp.substring(95,100).trim();
        this.ALtitudeTwo = temp.substring(100,105).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public preferred_routes() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
        
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO preferred_routes VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+ RouteIdentifier+"\'"+","+
                "\'"+ PreferredRouteUseIndicator+"\'"+","+
                "\'"+ SequenceNumber+"\'"+","+
                "\'"+ ContinuationRecordNumber+"\'"+","+
                "\'"+ ToFixIdentifier+"\'"+","+
                "\'"+ ICAOCode+"\'"+","+
                "\'"+ SectionCode2+"\'"+","+
                "\'"+ SubsectionCode2+"\'"+","+ 
                "\'"+ ViaCode+"\'"+","+ 
                "\'"+ SIDSTARAWYident+"\'"+","+
                "\'"+ AreaCode+"\'"+","+
                "\'"+ Level+"\'"+","+
                "\'"+ RouteType+"\'"+","+
                "\'"+ InitialAirportFix+"\'"+","+
                "\'"+ ICAOCode2+"\'"+","+
                "\'"+ SectionCode3+"\'"+","+
                "\'"+ SubsectionCode3+"\'"+","+
                "\'"+ TerminusAirportFix+"\'"+","+
                "\'"+ ICAOCode3+"\'"+","+
                "\'"+ SectionCode4+"\'"+","+
                "\'"+ SubsectionCode4+"\'"+","+
                "\'"+ MinimumAltitude+"\'"+","+
                "\'"+ MaximumAltitude+"\'"+","+
                "\'"+ TimeCode+"\'"+","+
                "\'"+ AircraftUseGroup+"\'"+","+
                "\'"+ DirectionRestriction+"\'"+","+
                "\'"+ AltitudeDescription+"\'"+","+
                "\'"+ AltitudeOne+"\'"+","+
                "\'"+ ALtitudeTwo+"\'"+","+       
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


    