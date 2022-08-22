/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.heliport;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class heliport_approach {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String HeliportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String approachIdentifier;
    String RouteType;
    String TransitionIdentifier;
    String MultipleCode;
    String SequenceNumber;
    String FixIdentifier;
    String ICAOCode2;
    String SectionCode2;
    String SubSectionCode2;
    String ContinuationNo;
    String WaypointDescriptionCode;
    String TurnDirection;
    String RNP;
    String PathAndTermination;
    String TurnDirectionValid;
    String RecommendedNAVAID;
    String ICAOCode3;
    String ARCRadius;
    String Theta;
    String Rho;
    String MagneticCourse;
    String RouteDistance;
    String RECDNAVSect;
    String RECDNAVSubSect;
    String AltitudeDescription;
    String ATCIndicator;
    String Altitude;
    String Altitude2;
    String TransitionAltitude;
    String SpeedLimit;
    String VerticalAngle;
    String CenterFIx;
    String MultipleCode2;
    String ICAOCode4;
    String SectionCode3;
    String SubSectionCode3;
    String GPSFMSIndicator;
    String APCHRouteQualifier1;
    String APCHRouteQualifier2;
    int FileRecordNumber;
    int CycleDate;
    
    public heliport_approach(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.HeliportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.approachIdentifier = temp.substring(13,19).trim();
        this.RouteType = temp.substring(19,20).trim();
        this.TransitionIdentifier = temp.substring(20,25).trim();
        this.MultipleCode = temp.substring(25,26).trim();
        this.SequenceNumber = temp.substring(26,29).trim();
        this.FixIdentifier = temp.substring(29,34).trim();
        this.ICAOCode2 = temp.substring(34,36).trim();
        this.SectionCode2 = temp.substring(36,37).trim();
        this.SubSectionCode2 = temp.substring(37,38).trim();
        this.ContinuationNo = temp.substring(38,39).trim();
        this.WaypointDescriptionCode = temp.substring(39,43).trim();
        this.TurnDirection = temp.substring(43,44).trim();
        this.RNP = temp.substring(44,47).trim();
        this.PathAndTermination = temp.substring(47,49).trim();
        this.TurnDirectionValid = temp.substring(49,50).trim();
        this.RecommendedNAVAID = temp.substring(50,54).trim();
        this.ICAOCode3 = temp.substring(54,56).trim();
        this.ARCRadius = temp.substring(56,62).trim();
        this.Theta = temp.substring(62,66).trim();
        this.Rho = temp.substring(66,70).trim();
        this.MagneticCourse = temp.substring(70,74).trim();
        this.RouteDistance = temp.substring(74,78).trim();
        this.RECDNAVSect = temp.substring(78,79).trim();
        this.RECDNAVSubSect = temp.substring(79,80).trim();
        this.AltitudeDescription = temp.substring(82,83).trim();
        this.ATCIndicator = temp.substring(83,84).trim();
        this.Altitude = temp.substring(84,89).trim();
        this.Altitude2 = temp.substring(89,94).trim();
        this.TransitionAltitude = temp.substring(94,99).trim();
        this.SpeedLimit = temp.substring(99,102).trim();
        this.VerticalAngle = temp.substring(102,106).trim();
        this.CenterFIx = temp.substring(106,111).trim();
        this.MultipleCode2 = temp.substring(111,112).trim();
        this.ICAOCode4 = temp.substring(112,114).trim();
        this.SectionCode3 = temp.substring(114,115).trim();
        this.SubSectionCode3 = temp.substring(115,116).trim();
        this.GPSFMSIndicator = temp.substring(116,117).trim();
        this.APCHRouteQualifier1 = temp.substring(118,119).trim();
        this.APCHRouteQualifier2 = temp.substring(119,120).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public heliport_approach() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO heliport_approach VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+HeliportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                "\'"+approachIdentifier+"\'" +","+
                "\'"+RouteType+"\'" +","+
                "\'"+TransitionIdentifier+"\'" +","+
                "\'"+MultipleCode+"\'" +","+
                "\'"+SequenceNumber+"\'" +","+
                "\'"+FixIdentifier+"\'" +","+
                "\'"+ICAOCode2+"\'" +","+
                "\'"+SectionCode2+"\'" +","+
                "\'"+SubSectionCode2+"\'" +","+
                "\'"+ContinuationNo+"\'" +","+
                "\'"+WaypointDescriptionCode+"\'" +","+
                "\'"+TurnDirection+"\'" +","+
                "\'"+RNP+"\'" +","+
                "\'"+PathAndTermination+"\'" +","+
                "\'"+TurnDirectionValid+"\'" +","+
                "\'"+RecommendedNAVAID+"\'" +","+
                "\'"+ICAOCode3+"\'" +","+
                "\'"+ARCRadius+"\'" +","+
                "\'"+Theta+"\'" +","+
                "\'"+Rho+"\'" +","+
                "\'"+MagneticCourse+"\'" +","+
                "\'"+RouteDistance+"\'" +","+
                "\'"+RECDNAVSect+"\'" +","+
                "\'"+RECDNAVSubSect+"\'" +","+
                "\'"+AltitudeDescription+"\'" +","+
                "\'"+ATCIndicator+"\'" +","+
                "\'"+Altitude+"\'" +","+
                "\'"+Altitude2+"\'" +","+
                "\'"+TransitionAltitude+"\'" +","+
                "\'"+SpeedLimit+"\'" +","+
                "\'"+VerticalAngle+"\'" +","+
                "\'"+CenterFIx+"\'" +","+
                "\'"+MultipleCode2+"\'" +","+
                "\'"+ICAOCode4+"\'" +","+
                "\'"+SectionCode3+"\'" +","+
                "\'"+SubSectionCode3+"\'" +","+
                "\'"+GPSFMSIndicator+"\'" +","+
                "\'"+APCHRouteQualifier1+"\'" +","+
                "\'"+APCHRouteQualifier2+"\'" +","+       
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
