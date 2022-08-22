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
public class seasonal_closure {
    
    
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
    String TimeCode;
    String TimeIndicator;
    String[] TimeOfOperation = new String[4];
    String CruiseTableIdent;
    int FileRecordNumber;
    int CycleDate;
    
    
    public seasonal_closure(String temp){
        
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
        this.TimeCode = temp.substring(51,52).trim();
        this.TimeIndicator = temp.substring(52,53).trim();
        int j = 53;
        for(int i=0;i<4;i++){
            this.TimeOfOperation[i] = temp.substring(j,j+10).trim();
            j+=10;
        }
        this.CruiseTableIdent = temp.substring(93,95).trim();
        
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }
    
    public seasonal_closure(){
        super();
    }
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            
            StringBuilder sb = new StringBuilder(90);
            for(int i=0;i<4;i++){
                sb.append("\'").append(TimeOfOperation[i]).append("\',");
            }
            String temp = new String(sb);
            
            
            stmt = a.createStatement();
            String ins = "INSERT INTO seasonal_closure VALUES(" +
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
                    "\'"+TimeCode+"\'" +","+
                    "\'"+TimeIndicator+"\'" +","+
                    temp+
                    "\'"+CruiseTableIdent+"\'" +","+
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
