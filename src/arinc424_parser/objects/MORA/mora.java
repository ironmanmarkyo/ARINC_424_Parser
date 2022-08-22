/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arinc424_parser.objects.MORA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yohan
 */
public class mora {
    String RecordType;
    String SectionCode;
    String SubsectionCode;
    String StartingLatitude;
    String StartingLongitude;
    String[] MORA = new String[30];
    int FileRecordNo;
    int CycleDate;
    
    float latitude;
    float longitude;
    
    public mora() { super(); }
    
    public mora(String temp){
        
        this.RecordType = temp.substring(0,1).trim();
        this.SectionCode = temp.substring(4,5).trim();
        this.SubsectionCode = temp.substring(5,6).trim();
        this.StartingLatitude = temp.substring(13,16).trim();
        this.StartingLongitude = temp.substring(16,20).trim();
        int j = 30;
        for(int i=0;i<30;i++){
            this.MORA[i] = temp.substring(j,j+3).trim();
            j+=3;
        }
        this.FileRecordNo = ((temp.substring(123,128).trim().isEmpty())?0:Integer.parseInt(temp.substring(123,128).trim()));
        this.CycleDate = ((temp.substring(128,132).trim().isEmpty())?0:Integer.parseInt(temp.substring(128,132).trim()));
        this.CalculateLongAndLat_mora();
    }
    
    public void InsertDatabase(Connection a){
        Statement stmt = null;
        try {
            stmt = a.createStatement();
            
            StringBuilder sb = new StringBuilder(90);
            for(int i=0;i<30;i++){
                sb.append("\'").append(MORA[i]).append("\',");
            }
            String temp = new String(sb);
            String ins = "INSERT INTO mora VALUES(" +
                "\'"+RecordType+"\'" +","+
                "\'"+SectionCode+"\'" +","+
                "\'"+SubsectionCode+"\'" +","+
                latitude+","+
                longitude+","+
                temp+        
                FileRecordNo+","+           
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
    
    public static void CalculateGeolocations_mora(Connection a){
        try {
            Statement stmt = a.createStatement();
            String sql = "Select exists(select from information_schema.columns where table_name = 'mora' and column_name = 'geolocation_mora');";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String result = rs.getString("exists");
            rs.close();
            if (!result.equalsIgnoreCase("t")) {
//                System.out.println("postgis not there");
                String cre = "alter table mora add column geolocation_mora geography(point);";
                stmt.execute(cre);
            }
            String up = "update mora set geolocation_mora = ST_MakePoint(starting_longitude, starting_latitude);";
            stmt.executeUpdate(up);
            stmt.close();
            a.commit();
//            a.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    private void CalculateLongAndLat_mora(){
        if(StartingLatitude.charAt(0)=='N'){
//            System.out.println("true");
            float deg = Integer.parseInt(StartingLatitude.substring(1,3));
//            float min = Integer.parseInt(StartingLatitude.substring(3,5));
//            float sec = Integer.parseInt(StartingLatitude.substring(5,7));
//            float msec = Integer.parseInt(StartingLatitude.substring(7,9));
//            sec = sec + (msec)/100;
            latitude = deg;// + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(StartingLatitude.substring(1,3));
//            float min = Integer.parseInt(StartingLatitude.substring(3,5));
//            float sec = Integer.parseInt(StartingLatitude.substring(5,7));
//            float msec = Integer.parseInt(StartingLatitude.substring(7,9));
//            sec = sec + (msec)/100;
            latitude = -(deg);// + min/60 + sec/3600);
//            System.out.println(latitude);
        }
        
        if(StartingLongitude.charAt(0)=='E'){
//            System.out.println("true");
            float deg = Integer.parseInt(StartingLongitude.substring(1,4));
//            float min = Integer.parseInt(StartingLongitude.substring(4,6));
//            float sec = Integer.parseInt(StartingLongitude.substring(6,8));
//            float msec = Integer.parseInt(StartingLongitude.substring(8,10));
//            sec = sec + (msec)/100;
            longitude = deg;// + min/60 + sec/3600;
//            System.out.println(latitude);
        } else {
            float deg = Integer.parseInt(StartingLongitude.substring(1,4));
//            float min = Integer.parseInt(StartingLongitude.substring(4,6));
//            float sec = Integer.parseInt(StartingLongitude.substring(6,8));
//            float msec = Integer.parseInt(StartingLongitude.substring(8,10));
//            sec = sec + (msec)/100;
            longitude = -(deg);// + min/60 + sec/3600);
//            System.out.println(latitude);
        }
    }
}
