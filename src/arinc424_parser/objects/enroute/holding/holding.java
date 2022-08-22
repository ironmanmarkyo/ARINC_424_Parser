/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.holding;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class holding {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String RegionCode; 
    /*In Enroute Fix Holding Pattern records, the code
of “ENRT” is used in the Region Code field and
the ICAO Code field is blank. In Terminal Fix
Holding record, the Region Code field contains
the identifier of the Airport or Heliport with
which the holding is associated.
    */
    String ICAOCode;
    String DuplicateIdentifier;
    String FixIdentifier;
    String ICAOCode2;
    String SectionCode2;
    String SubsectionCode2;
    String ContinuationRecordNo;
    String InboundHoldingCourse;
    String TurnDirection;
    String LegLength;  // have to convert
    String LegTime; // have to convert
    String MinAltitude;
    String MaxAltitude;
    String HoldingSpeed;
    String Notes;
    int FileRecordNumber;
    int CycleDate;
    
    public holding(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.RegionCode = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.DuplicateIdentifier = temp.substring(27,29).trim();
        this.FixIdentifier = temp.substring(29,34).trim();
        this.ICAOCode2 = temp.substring(34,36).trim();
        this.SectionCode2 = temp.substring(36,37).trim();
        this.SubsectionCode2 = temp.substring(37,38).trim();
        this.ContinuationRecordNo = temp.substring(38,39).trim();
        this.InboundHoldingCourse = temp.substring(39,43).trim();
        this.TurnDirection = temp.substring(43,44).trim();
        this.LegLength = temp.substring(44,47).trim();
        this.LegTime = temp.substring(47,49).trim();
        this.MinAltitude = temp.substring(49,54).trim();
        this.MaxAltitude = temp.substring(54,59).trim();
        this.HoldingSpeed = temp.substring(59,62).trim();
        this.Notes = temp.substring(98,123).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public holding() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO holding VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+RegionCode+"\'" +","+
                "\'"+ICAOCode+"\'"+","+
                "\'"+DuplicateIdentifier+"\'"+","+
                "\'"+FixIdentifier+"\'"+","+
                "\'"+ICAOCode2+"\'"+","+
                "\'"+SectionCode2+"\'"+","+
                "\'"+SubsectionCode2+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                "\'"+InboundHoldingCourse+"\'"+","+
                "\'"+TurnDirection+"\'"+","+
                "\'"+LegLength+"\'"+","+
                "\'"+LegTime+"\'"+","+
                "\'"+MinAltitude+"\'"+","+  
                "\'"+MaxAltitude+"\'"+","+
                "\'"+HoldingSpeed+"\'"+","+        
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
