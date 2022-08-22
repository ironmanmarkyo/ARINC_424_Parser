/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.airport;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class flt_planning_arr_dept {
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String SIDSTARApproachIdentifier;
    String ProcedureType;
    String RunwayTransitionIdentifier;
    String RunwayTransitionFix;
//    String FixIdentifier;
    String ICAOCode2;
    String SectionCode2;
    String SubSectionCode2;
    String RunwayTransitionAlongTrackDist;
    String CommonSegmentTransitionFix;
    String ICAOCode3;
    String SectionCode3;
    String SubSectionCode3;
    String CommonSegmentAlongTrackDist;
    String EnrouteTransitionIdentifier;
    String EnrouteTransitionFix;
    
    String ICAOCode4;
    String SectionCode4;
    String SubSectionCode4;
    String EnrouteTransitionAlongTrackDist;
    String SequenceNo;
//    String ICAOCode3;
    String ContinuationNo;
    String NumberOfEngines;
    String TurbopropJetIndicator;
    String RNAVFlag;
    String ATCWeightCategory;
    String ATCIdentitfier;
    String TimeCode;
    String ProcedureDescription;
    String LegTypeCode;
    String ReportingCode;
    String InitialDepartureMagneticCourse;
    String AltitudeDescription;
    String Altitude;
    String Altitude2;
    String SpeedLimit;
    String InitialCruiseTable;
//    String ICAOCode4;
//    String SectionCode3;
//    String SubSectionCode3;
//    String GPSFMSIndicator;
//    String APCHRouteQualifier1;
//    String APCHRouteQualifier2;
    int FileRecordNumber;
    int CycleDate;
    
    public flt_planning_arr_dept(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.SIDSTARApproachIdentifier = temp.substring(13,19).trim();
        this.ProcedureType = temp.substring(19,20).trim();
        this.RunwayTransitionIdentifier = temp.substring(20,25).trim();
        this.RunwayTransitionFix = temp.substring(25,30).trim();
        this.ICAOCode2 = temp.substring(30,32).trim();
        this.SectionCode2 = temp.substring(32,33).trim();
        this.SubSectionCode2 = temp.substring(33,34).trim();
        this.RunwayTransitionAlongTrackDist = temp.substring(34,37).trim();
        this.CommonSegmentTransitionFix = temp.substring(37,42).trim();
        this.ICAOCode3 = temp.substring(42,44).trim();
        this.SectionCode3 = temp.substring(44,45).trim();
        this.SubSectionCode3 = temp.substring(45,46).trim();
        this.CommonSegmentAlongTrackDist = temp.substring(46,49).trim();
        this.EnrouteTransitionIdentifier = temp.substring(49,54).trim();
        this.EnrouteTransitionFix = temp.substring(54,59).trim();
        this.ICAOCode4 = temp.substring(59,61).trim();
        this.SectionCode4 = temp.substring(61,62).trim();
        this.SubSectionCode4 = temp.substring(62,63).trim();
        this.EnrouteTransitionAlongTrackDist = temp.substring(63,66).trim();
        this.SequenceNo = temp.substring(66,69).trim();
        this.ContinuationNo = temp.substring(69,70).trim();
        this.NumberOfEngines = temp.substring(70,74).trim();
        this.TurbopropJetIndicator = temp.substring(74,75).trim();
        this.RNAVFlag = temp.substring(75,76).trim();
        this.ATCWeightCategory = temp.substring(76,77).trim();
        this.ATCIdentitfier = temp.substring(77,84).trim();
        this.TimeCode = temp.substring(84,85).trim();
        this.ProcedureDescription = temp.substring(85,100).trim();
        this.LegTypeCode = temp.substring(100,102).trim();
        this.ReportingCode = temp.substring(102,103).trim();
        this.InitialDepartureMagneticCourse = temp.substring(103,107).trim();
        this.AltitudeDescription = temp.substring(107,108).trim();
        this.Altitude = temp.substring(108,111).trim();
        this.Altitude2 = temp.substring(111,114).trim();
        this.SpeedLimit = temp.substring(114,117).trim();
        this.InitialCruiseTable = temp.substring(117,119).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public flt_planning_arr_dept() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO flt_planning_arr_dept VALUES(" +
                "\'"+RecordType+"\'" +","+    
                "\'"+CustomerAreaCode+"\'" +","+    
                "\'"+SectionCode+"\'" +","+    
                "\'"+AirportIdentifier+"\'" +","+    
                "\'"+ICAOCode+"\'" +","+    
                "\'"+SubsectionCode+"\'" +","+    
                "\'"+SIDSTARApproachIdentifier+"\'" +","+    
                "\'"+ProcedureType+"\'" +","+    
                "\'"+RunwayTransitionIdentifier+"\'" +","+    
                "\'"+RunwayTransitionFix+"\'" +","+    
                "\'"+ICAOCode2+"\'" +","+    
                "\'"+SectionCode2+"\'" +","+    
                "\'"+SubSectionCode2+"\'" +","+    
                "\'"+RunwayTransitionAlongTrackDist+"\'" +","+    
                "\'"+CommonSegmentTransitionFix+"\'" +","+    
                "\'"+ICAOCode3+"\'" +","+    
                "\'"+SectionCode3+"\'" +","+    
                "\'"+SubSectionCode3+"\'" +","+    
                "\'"+CommonSegmentAlongTrackDist+"\'" +","+    
                "\'"+EnrouteTransitionIdentifier+"\'" +","+    
                "\'"+EnrouteTransitionFix+"\'" +","+    
                "\'"+ICAOCode4+"\'" +","+    
                "\'"+SectionCode4+"\'" +","+    
                "\'"+SubSectionCode4+"\'" +","+    
                "\'"+EnrouteTransitionAlongTrackDist+"\'" +","+    
                "\'"+SequenceNo+"\'" +","+    
                "\'"+ContinuationNo+"\'" +","+    
                "\'"+NumberOfEngines+"\'" +","+    
                "\'"+TurbopropJetIndicator+"\'" +","+    
                "\'"+RNAVFlag+"\'" +","+    
                "\'"+ATCWeightCategory+"\'" +","+    
                "\'"+ATCIdentitfier+"\'" +","+    
                "\'"+TimeCode+"\'" +","+    
                "\'"+ProcedureDescription+"\'" +","+    
                "\'"+LegTypeCode+"\'" +","+    
                "\'"+ReportingCode+"\'" +","+    
                "\'"+InitialDepartureMagneticCourse+"\'" +","+    
                "\'"+AltitudeDescription+"\'" +","+    
                "\'"+Altitude+"\'" +","+    
                "\'"+Altitude2+"\'" +","+    
                "\'"+SpeedLimit+"\'" +","+    
                "\'"+InitialCruiseTable+"\'" +","+          
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