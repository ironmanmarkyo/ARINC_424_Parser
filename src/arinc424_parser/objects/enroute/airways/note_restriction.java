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
public class note_restriction {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String RouteIdentifier; 
    String RestrictionIdentifier;
    String RestrictionType;
    String ContinuationRecordNo;
    String StartFixIdentfier;
    String StartFixICAOCode;
    String StartFixSectionCode;
    String StartFixSubsectionCode;
    String EndFixIdentifier;
    String EndFixICAOCode;
    String EndFixSectionCode;
    String EndFixSubsectionCode;
    String StartDate;
    String EndDate;
    String RestrictionNotes;
    int FileRecordNumber;
    int CycleDate;
    
    
    public note_restriction(String temp){
        
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.RouteIdentifier = temp.substring(6,12).trim();
        this.RestrictionIdentifier = temp.substring(12,15).trim();
        this.RestrictionType = temp.substring(15,17).trim();
        this.ContinuationRecordNo = temp.substring(17,18).trim();
        this.StartFixIdentfier = temp.substring(18,23).trim();
        this.StartFixICAOCode = temp.substring(23,25).trim();
        this.StartFixSectionCode = temp.substring(25,26).trim();
        this.StartFixSubsectionCode = temp.substring(26,27).trim();
        this.EndFixIdentifier = temp.substring(27,32).trim();
        this.EndFixICAOCode = temp.substring(32,34).trim();
        this.EndFixSectionCode = temp.substring(34,35).trim();
        this.EndFixSubsectionCode = temp.substring(35,36).trim();
        this.StartDate = temp.substring(37,44).trim();
        this.EndDate = temp.substring(44,51).trim();
        this.RestrictionNotes = temp.substring(51,120).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }
    
    public note_restriction(){
        super();
    }
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO note_restriction VALUES(" +
                    "\'"+RecordType+"\'" +","+
                    "\'"+CustomerAreaCode+"\'" +","+
                    "\'"+SectionCode+"\'" +","+
                    "\'"+SubsectionCode+"\'"+","+
                    "\'"+RouteIdentifier+"\'" +","+
                    "\'"+RestrictionIdentifier+"\'" +","+
                    "\'"+RestrictionType+"\'"+","+
                    "\'"+ContinuationRecordNo+"\'"+","+
                    "\'"+StartFixIdentfier+"\'"+","+
                    "\'"+StartFixICAOCode+"\'"+","+
                    "\'"+StartFixSectionCode+"\'"+","+
                    "\'"+StartFixSubsectionCode+"\'" +","+
                    "\'"+EndFixIdentifier+"\'" +","+
                    "\'"+EndFixICAOCode+"\'" +","+
                    "\'"+EndFixSectionCode+"\'" +","+
                    "\'"+EndFixSubsectionCode+"\'" +","+
                    "\'"+StartDate+"\'" +","+
                    "\'"+EndDate+"\'" +","+
                    "\'"+RestrictionNotes+"\'" +","+
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
