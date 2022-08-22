/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.enroute.airways;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class airways {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String RouteIdentifier; 
    /*
    The standard length for the Route Identifier
is five characters. Some users envisage the
need for a six-character field. This reserved
column will permit this usage. Some data
suppliers may use this position for the ATS
Service suffix associated with some Route
Identifiers.
    */
    String SequenceNumber;
    String FixIdentifier;
    String ICAOCode;
    String SectionCode2;
    String SubsectionCode2;
    String ContinuationRecordNo;
    String WaypointDescriptionCode;
    String BoundaryCode;
    String RouteType;
    String Level;
    String DirectionRestriction;
    String CruiseTableIndicator;
    String EUIndicator;
    String RecommendedNAVAID;
    String ICAOCode2;
    String RNP;
    String Theta;
    String RHo;
    String OutboundMagneticCourse;
    String RouteDistanceFrom;
    String InboundMagneticCourse;
    String MinAltitude;
    String MinAltitude2;
    String MaxAltitude;
    int FileRecordNumber;
    int CycleDate;
    
    
    public airways(String temp){
        
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.RouteIdentifier = temp.substring(13,19).trim();
        this.SequenceNumber = temp.substring(25,29).trim();
        this.FixIdentifier = temp.substring(29,34).trim();
        this.ICAOCode = temp.substring(34,36).trim();
        this.SectionCode2 = temp.substring(36,37).trim();
        this.SubsectionCode2 = temp.substring(37,38).trim();
        this.ContinuationRecordNo = temp.substring(38,39).trim();
        this.WaypointDescriptionCode = temp.substring(39,43).trim();
        this.BoundaryCode = temp.substring(43,44).trim();
        this.RouteType = temp.substring(44,45).trim();
        this.Level = temp.substring(45,46).trim();
        this.DirectionRestriction = temp.substring(46,47).trim();
        this.CruiseTableIndicator = temp.substring(47,49).trim();
        this.EUIndicator = temp.substring(49,50).trim();
        this.RecommendedNAVAID = temp.substring(50,54).trim();
        this.ICAOCode2 = temp.substring(54,56).trim();
        this.RNP = temp.substring(56,59).trim();
        this.Theta = temp.substring(62,66).trim();
        this.RHo = temp.substring(66,69).trim();
        this.OutboundMagneticCourse = temp.substring(70,74).trim();
        this.RouteDistanceFrom = temp.substring(74,78).trim();
        this.InboundMagneticCourse = temp.substring(78,82).trim();
        this.MinAltitude = temp.substring(83,88).trim();
        this.MinAltitude2 = temp.substring(88,93).trim();
        this.MaxAltitude = temp.substring(93,98).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }
    
    public airways(){
        super();
    }
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO airways VALUES(" +
                    "\'"+RecordType+"\'" +","+
                    "\'"+CustomerAreaCode+"\'" +","+
                    "\'"+SectionCode+"\'" +","+
                    "\'"+SubsectionCode+"\'"+","+
                    "\'"+RouteIdentifier+"\'" +","+
                    "\'"+SequenceNumber+"\'" +","+
                    "\'"+FixIdentifier+"\'"+","+
                    "\'"+ICAOCode+"\'"+","+
                    "\'"+SectionCode2+"\'"+","+
                    "\'"+SubsectionCode2+"\'"+","+
                    "\'"+ContinuationRecordNo+"\'"+","+
                    "\'"+WaypointDescriptionCode+"\'" +","+
                    "\'"+BoundaryCode+"\'" +","+
                    "\'"+RouteType+"\'" +","+
                    "\'"+Level+"\'" +","+
                    "\'"+DirectionRestriction+"\'" +","+
                    "\'"+CruiseTableIndicator+"\'" +","+
                    "\'"+EUIndicator+"\'" +","+
                    "\'"+RecommendedNAVAID+"\'" +","+
                    "\'"+ICAOCode2+"\'" +","+
                    "\'"+RNP+"\'" +","+
                    "\'"+Theta+"\'" +","+
                    "\'"+RHo+"\'" +","+
                    "\'"+OutboundMagneticCourse+"\'" +","+
                    "\'"+RouteDistanceFrom+"\'" +","+
                    "\'"+InboundMagneticCourse+"\'" +","+
                    "\'"+MinAltitude+"\'"+","+ 
                    "\'"+MinAltitude2+"\'"+","+  
                    "\'"+MaxAltitude+"\'"+","+
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
