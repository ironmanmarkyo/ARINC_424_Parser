/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.tables;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class geographical {
    
    
    String RecordType;
    String CustomerAreaCode;
    String SectionCode;
    String SubsectionCode;
    String GeographicaleRefTableID; 
    String SequenceNumber;
    String GeographicalEntity;
    String ContinuationRecordNo;
    String[] PreferredRouteIdentifier = new String[6];
    String[] PreferredRouteUseIndi = new String[6];
//    String VerticalSeparation;
//    String CruiseLevelTo;
//    String CruiseLevelFrom2;
//    String VerticalSeparation2;
//    String CruiseLevelTo2;  // have to convert
//    String CruiseLevelFrom3; // have to convert
//    String VerticalSeparation3;
//    String CruiseLevelTo3;
//    String CruiseLevelFrom4;
//    String VerticalSeparation4;
//    String CruiseLevelTo4;
    int FileRecordNumber;
    int CycleDate;
    
    public geographical(String temp){
        this.RecordType = temp.substring(0,1).trim();
        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.GeographicaleRefTableID = temp.substring(6,8).trim();
        this.SequenceNumber = temp.substring(8,9).trim();
        this.GeographicalEntity = temp.substring(9,38).trim();
        this.ContinuationRecordNo = temp.substring(38,39).trim();
        
        int j = 40;
        for(int i=0;i<6;i++){
            this.PreferredRouteIdentifier[i] = temp.substring(j,j+10).trim();
            this.PreferredRouteUseIndi[i] = temp.substring(j+10,j+12).trim();
            j+=12;
        }
//        this.PreferredRouteIdentifier = temp.substring(36,37).trim();
//        this.PreferredRouteUseIndi = temp.substring(39,44).trim();
//        this.VerticalSeparation = temp.substring(44,49).trim();
//        this.CruiseLevelTo = temp.substring(49,54).trim();
//        this.CruiseLevelFrom2 = temp.substring(54,59).trim();
//        this.VerticalSeparation2 = temp.substring(59,64).trim();
//        this.CruiseLevelTo2 = temp.substring(64,69).trim();
//        this.CruiseLevelFrom3 = temp.substring(69,74).trim();
//        this.VerticalSeparation3 = temp.substring(74,79).trim();
//        this.CruiseLevelTo3 = temp.substring(79,84).trim();
//        this.CruiseLevelFrom4 = temp.substring(84,89).trim();
//        this.VerticalSeparation4 = temp.substring(89,94).trim();
//        this.CruiseLevelTo4 = temp.substring(94, 99).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public geographical() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
                        
            StringBuilder sb = new StringBuilder(72);
            for(int i=0;i<6;i++){
                sb.append("\'").append(PreferredRouteIdentifier[i]).append("\',");
                sb.append("\'").append(PreferredRouteUseIndi[i]).append("\',");
            }
            String temp = new String(sb);
            
            String ins = "INSERT INTO geographical VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+GeographicaleRefTableID+"\'" +","+
                "\'"+SequenceNumber+"\'"+","+
                "\'"+GeographicalEntity+"\'"+","+
                "\'"+ContinuationRecordNo+"\'"+","+
                temp+
//                "\'"+VerticalSeparation+"\'"+","+
//                "\'"+CruiseLevelTo+"\'"+","+
//                "\'"+CruiseLevelFrom2+"\'"+","+
//                "\'"+VerticalSeparation2+"\'"+","+
//                "\'"+CruiseLevelTo2+"\'"+","+
//                "\'"+CruiseLevelFrom3+"\'"+","+
//                "\'"+VerticalSeparation3+"\'"+","+  
//                "\'"+CruiseLevelTo3+"\'"+","+
//                "\'"+CruiseLevelFrom4+"\'"+","+        
//                "\'"+VerticalSeparation4+"\'"+","+          
//                "\'"+CruiseLevelTo4+"\'"+","+     
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
