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
public class alternate {
    
    
    String RecordType;
    String CustomerCode;
    String SectionCode;
    String SubsectionCode;
    String AlternateRelatedAirport; 
    String AlternateRelatedICAOCode;
    String AlternateRelatedSectionCode;
    String AlternateRelatedSubsectionCode;
    String AlternateRecordType;
    String[] DistanceToAlternate = new String[6];
    String PrimaryAlternateIdentifier;
    String[] AlternateType = new String[6];
    String[] AdditinalAlternateIdentifier = new String[5];
    int FileRecordNumber;
    int CycleDate;
    
    public alternate(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.AlternateRelatedAirport = temp.substring(6,11).trim();
        this.AlternateRelatedICAOCode = temp.substring(11,13).trim();
        this.AlternateRelatedSectionCode = temp.substring(13,14).trim();
        this.AlternateRelatedSubsectionCode = temp.substring(14,15).trim();
        this.AlternateRecordType = temp.substring(15,17).trim();
        this.DistanceToAlternate[0] = temp.substring(19,22).trim();
        this.AlternateType[0] = temp.substring(22,23).trim();
        this.PrimaryAlternateIdentifier = temp.substring(23,33).trim();
        int j = 35;
        for(int i=1;i<6;i++){
            this.DistanceToAlternate[i] = temp.substring(j,j+3).trim();
            this.AlternateType[i] = temp.substring(j+3,j+4).trim();
            this.AdditinalAlternateIdentifier[i-1] = temp.substring(j+4,j+14).trim();
            j+=16;
        }
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public alternate() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            
            StringBuilder sb = new StringBuilder(138);
            sb.append("\'").append(DistanceToAlternate[0]).append("\',");
            sb.append("\'").append(AlternateType[0]).append("\',");
            sb.append("\'").append(PrimaryAlternateIdentifier).append("\',");
            for(int i=0;i<5;i++){
                sb.append("\'").append(DistanceToAlternate[i+1]).append("\',");
                sb.append("\'").append(AlternateType[i+1]).append("\',");
                sb.append("\'").append(AdditinalAlternateIdentifier[i]).append("\',");
            }
            String temp = new String(sb);
            
            stmt = a.createStatement();
            String ins = "INSERT INTO alternate VALUES(" +
                "\'"+ RecordType+"\'" +","+
                "\'"+ CustomerCode+"\'" +","+
                "\'"+ SectionCode+"\'" +","+
                "\'"+ SubsectionCode+"\'" +","+
                "\'"+ AlternateRelatedAirport+"\'" +","+ 
                "\'"+ AlternateRelatedICAOCode+"\'" +","+
                "\'"+ AlternateRelatedSectionCode+"\'" +","+
                "\'"+ AlternateRelatedSubsectionCode+"\'" +","+
                "\'"+ AlternateRecordType+"\'" +","+
                temp+","+
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
