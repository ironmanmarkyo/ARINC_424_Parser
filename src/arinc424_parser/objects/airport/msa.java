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
public class msa {
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String AirportIdentifier;
    String ICAOCode;
    String SubsectionCode;
    String MSACenter;
    String ICAOCode2;
    String SectionCode2;
    String SubSectionCode2;
    String MultipleCode;
    String ContinuationNo;
    String RadiusLimit;
    String[] SectorBearing = new String[5];
    String[] SectorAltitude = new String[5];
    String MagneticIndicator;
    int FileRecordNumber;
    int CycleDate;
    
    public msa(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.AirportIdentifier = temp.substring(6,10).trim();
        this.ICAOCode = temp.substring(10,12).trim();
        this.SubsectionCode = temp.substring(12,13).trim();
        this.MSACenter = temp.substring(13,18).trim();
        this.ICAOCode2 = temp.substring(18,20).trim();
        this.SectionCode2 = temp.substring(20,21).trim();
        this.SubSectionCode2 = temp.substring(21,22).trim();
        this.MultipleCode = temp.substring(22,23).trim();
        this.ContinuationNo = temp.substring(38,39).trim();
        this.RadiusLimit = temp.substring(40,42).trim();
        int j = 42;
        for(int i=0;i<5;i++){
            this.SectorBearing[i] = temp.substring(j,j+3).trim();
            j+=6;
        }
        int k = 45;
        for(int i=0;i<5;i++){
            this.SectorAltitude[i] = temp.substring(k,k+3).trim();
            k+=6;
        }
        this.MagneticIndicator = temp.substring(72,73).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public msa() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            StringBuilder sb = new StringBuilder(30);
            for(int i=0;i<5;i++){
                sb.append("\'").append(SectorBearing[i]).append("\',");
                sb.append("\'").append(SectorAltitude[i]).append("\',");
            }
            String temp = new String(sb);
            stmt = a.createStatement();
            String ins = "INSERT INTO msa VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+AirportIdentifier+"\'" +","+
                "\'"+ICAOCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                "\'"+MSACenter+"\'" +","+
                "\'"+ICAOCode2+"\'" +","+
                "\'"+SectionCode2+"\'" +","+
                "\'"+SubSectionCode2+"\'" +","+
                "\'"+MultipleCode+"\'" +","+
                "\'"+ContinuationNo+"\'" +","+
                "\'"+RadiusLimit+"\'" +","+
                temp+
                "\'"+MagneticIndicator+"\'" +","+  
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
