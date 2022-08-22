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
public class cruising {
    
    String RecordType;
    String SectionCode;
    String SubsectionCode;
    String CruiseTableIdentifier; 
    String SequenceNumber;
    String CourseFrom;
    String CourseTo;
    String MagTrue;
    String CruiseLevelFrom;
    String VerticalSeparation;
    String CruiseLevelTo;
    String CruiseLevelFrom2;
    String VerticalSeparation2;
    String CruiseLevelTo2;  // have to convert
    String CruiseLevelFrom3; // have to convert
    String VerticalSeparation3;
    String CruiseLevelTo3;
    String CruiseLevelFrom4;
    String VerticalSeparation4;
    String CruiseLevelTo4;
    int FileRecordNumber;
    int CycleDate;
    
    public cruising(String temp){
        this.RecordType = temp.substring(0,1).trim();
//        this.CustomerAreaCode = temp.substring(1,4).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.CruiseTableIdentifier = temp.substring(6,8).trim();
        this.SequenceNumber = temp.substring(8,9).trim();
        this.CourseFrom = temp.substring(28,32).trim();
        this.CourseTo = temp.substring(32,36).trim();
        this.MagTrue = temp.substring(36,37).trim();
        this.CruiseLevelFrom = temp.substring(39,44).trim();
        this.VerticalSeparation = temp.substring(44,49).trim();
        this.CruiseLevelTo = temp.substring(49,54).trim();
        this.CruiseLevelFrom2 = temp.substring(54,59).trim();
        this.VerticalSeparation2 = temp.substring(59,64).trim();
        this.CruiseLevelTo2 = temp.substring(64,69).trim();
        this.CruiseLevelFrom3 = temp.substring(69,74).trim();
        this.VerticalSeparation3 = temp.substring(74,79).trim();
        this.CruiseLevelTo3 = temp.substring(79,84).trim();
        this.CruiseLevelFrom4 = temp.substring(84,89).trim();
        this.VerticalSeparation4 = temp.substring(89,94).trim();
        this.CruiseLevelTo4 = temp.substring(94, 99).trim();
        this.FileRecordNumber = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
    }

    public cruising() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        super();
    }
    
    //Insert value to database
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            String ins = "INSERT INTO cruising VALUES(" +
                "\'"+RecordType+"\'" +","+
//                "\'"+CustomerAreaCode+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'"+","+
                "\'"+CruiseTableIdentifier+"\'" +","+
                "\'"+SequenceNumber+"\'"+","+
                "\'"+CourseFrom+"\'"+","+
                "\'"+CourseTo+"\'"+","+
                "\'"+MagTrue+"\'"+","+
                "\'"+CruiseLevelFrom+"\'"+","+
                "\'"+VerticalSeparation+"\'"+","+
                "\'"+CruiseLevelTo+"\'"+","+
                "\'"+CruiseLevelFrom2+"\'"+","+
                "\'"+VerticalSeparation2+"\'"+","+
                "\'"+CruiseLevelTo2+"\'"+","+
                "\'"+CruiseLevelFrom3+"\'"+","+
                "\'"+VerticalSeparation3+"\'"+","+  
                "\'"+CruiseLevelTo3+"\'"+","+
                "\'"+CruiseLevelFrom4+"\'"+","+        
                "\'"+VerticalSeparation4+"\'"+","+          
                "\'"+CruiseLevelTo4+"\'"+","+     
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
