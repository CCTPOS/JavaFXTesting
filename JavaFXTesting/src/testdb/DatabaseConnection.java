package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseConnection {
  public void insertDatabase1(int id, String name, String surname, String address, String purchases ){
	try {
          
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
            Statement stmt = (Statement) con.createStatement();
            
            String sql = "insert into customerinfo values("+id+",'"+name+"','"+surname+"','"+address+"','"+purchases+"');";
            stmt.executeUpdate(sql);

	} catch (Exception e) {  
		e.printStackTrace();
        }
    }
  	
  public ArrayList<String> retrieveDatabase1() {
      
      ArrayList<String> al = new ArrayList<String>();
 
      
	try {
          
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
          Statement stmt = (Statement) con.createStatement();
          String sql;
          sql = "select name from customerinfo;";
          ResultSet rs = stmt.executeQuery(sql);
          
          while (rs.next()){
              
              al.add(rs.getString("name"));
          }                
	}catch (Exception e) {    
      }
 return al;
   }
  
 public ArrayList<String> retrieveSurnameCol() {
      
      ArrayList<String> al = new ArrayList<String>();
 
      
	try {
          
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
          Statement stmt = (Statement) con.createStatement();
          String sql;
          sql = "select surname from customerinfo;";
          ResultSet rs = stmt.executeQuery(sql);
          
          while (rs.next()){
              
              al.add(rs.getString("surname"));
          }                
	}catch (Exception e) {    
      }
 return al;
   }
 
 public ArrayList<String> retrieveAddressCol() {
     
     ArrayList<String> al = new ArrayList<String>();

     
	try {
         
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
         Statement stmt = (Statement) con.createStatement();
         String sql;
         sql = "select address from customerinfo;";
         ResultSet rs = stmt.executeQuery(sql);
         
         while (rs.next()){
             
             al.add(rs.getString("address"));
         }                
	}catch (Exception e) {    
     }
return al;
  }
 
 public ArrayList<String> retrievePurchasesCol() {
     
     ArrayList<String> al = new ArrayList<String>();

     
	try {
         
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","");
         Statement stmt = (Statement) con.createStatement();
         String sql;
         sql = "select purchases from customerinfo;";
         ResultSet rs = stmt.executeQuery(sql);
         
         while (rs.next()){
             
             al.add(rs.getString("purchases"));
         }                
	}catch (Exception e) {    
     }
return al;
  }
}
