/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.company_routes;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class company_routes {
    
    String RecordType;
    String CustomerCode;
    String SectionCode;
    String SubsectionCode;
    String FromAirport; 
    String ICAOCode;
    String SectionCode2;
    String SubsectionCode2;
    String ToAirport;
    String ICAOCode2;
    String SectionCode3;
    String SubsectionCode3;
    String CompanyRouteID;
    String SequenceNo;
    String Via;
    String SID_STAR_APP_AWY;  
    String AreaCode;
    String ToFix;
    String ICAOCode3;
    String SectionCode4;
    String SubsectionCode4;
    String RunwayTrans;
    String ENRTTrans;
    String CruiseAltitude;
    String TerminalAlternateAirport;
    String ICAOCode4;
    String AlternateDistance;
    String CostIndex;
    String EnrouteAlternateAirport;
    int FileRecordNumber;
    int CycleDate;
    
    public company_routes(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.FromAirport = temp.substring(6,11).trim();
        this.ICAOCode = temp.substring(12,14).trim();
        this.SectionCode2 = temp.substring(14,15).trim();
        this.SubsectionCode2 = temp.substring(15,16).trim();
        this.ToAirport = temp.substring(16,21).trim();
        this.ICAOCode2 = temp.substring(22,24).trim();
        this.SectionCode3 = temp.substring(24,25).trim();
        this.SubsectionCode3 = temp.substring(25,26).trim();
        this.CompanyRouteID = temp.substring(26,36).trim();
        this.SequenceNo = temp.substring(36,39).trim();
        this.Via = temp.substring(39,42).trim();
        this.SID_STAR_APP_AWY = temp.substring(42,48).trim();
        this.AreaCode = temp.substring(48,51).trim();
        this.ToFix = temp.substring(51,57).trim();
        this.ICAOCode3 = temp.substring(57,59).trim();
        this.SectionCode4 = temp.substring(59,60).trim();
        this.SubsectionCode4 = temp.substring(60,61).trim();
        this.RunwayTrans = temp.substring(61,66).trim();
        this.ENRTTrans = temp.substring(66,71).trim();
        this.CruiseAltitude = temp.substring(72,77).trim();
        this.TerminalAlternateAirport = temp.substring(77,81).trim();
        this.ICAOCode4 = temp.substring(81,83).trim();
        this.AlternateDistance = temp.substring(83,87).trim();
        this.CostIndex = temp.substring(87,90).trim();
        this.EnrouteAlternateAirport = temp.substring(90,94).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public company_routes() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO company_routes VALUES(" +
                "\'"+ RecordType+"\'" +","+
                "\'"+ CustomerCode+"\'" +","+
                "\'"+ SectionCode+"\'" +","+
                "\'"+ SubsectionCode+"\'" +","+
                "\'"+ FromAirport+"\'" +","+ 
                "\'"+ ICAOCode+"\'" +","+
                "\'"+ SectionCode2+"\'" +","+
                "\'"+ SubsectionCode2+"\'" +","+
                "\'"+ ToAirport+"\'" +","+
                "\'"+ ICAOCode2+"\'" +","+
                "\'"+ SectionCode3+"\'" +","+
                "\'"+ SubsectionCode3+"\'" +","+
                "\'"+ CompanyRouteID+"\'" +","+
                "\'"+ SequenceNo+"\'" +","+
                "\'"+ Via+"\'" +","+
                "\'"+ SID_STAR_APP_AWY+"\'" +","+  
                "\'"+ AreaCode+"\'" +","+
                "\'"+ ToFix+"\'" +","+
                "\'"+ ICAOCode3+"\'" +","+
                "\'"+ SectionCode4+"\'" +","+
                "\'"+ SubsectionCode4+"\'" +","+
                "\'"+ RunwayTrans+"\'" +","+
                "\'"+ ENRTTrans+"\'" +","+
                "\'"+ CruiseAltitude+"\'" +","+
                "\'"+ TerminalAlternateAirport+"\'" +","+
                "\'"+ ICAOCode4+"\'" +","+
                "\'"+ AlternateDistance+"\'" +","+
                "\'"+ CostIndex+"\'" +","+
                "\'"+ EnrouteAlternateAirport+"\'" +","+      
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

    
