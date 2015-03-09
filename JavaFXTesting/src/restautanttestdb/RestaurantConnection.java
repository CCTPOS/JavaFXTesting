package restautanttestdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Date;

public class RestaurantConnection {
	public void insertRestaurant(String orderNo, String date, double total, String status, String pay_type ){
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        Date dateTime = new java.sql.Date(timeStamp.getTime()); 
		
		try {
	          
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","");
	            Statement stmt = (Statement) con.createStatement();
	            
	            String sql = "insert into orders values("+orderNo+",'"+dateTime+"','"+total+"','"+status+"','"+pay_type+"');";
	            stmt.executeUpdate(sql);

		}catch (Exception e) {  
			e.printStackTrace();
	    }
	 }
	  	
	  public ArrayList<String> retrieveRestaurant() {
	      
	    ArrayList<String> al = new ArrayList<String>();
	 	      
		try {	          
	          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","");
	          Statement stmt = (Statement) con.createStatement();
	          String sql;
	          sql = "select Order_No from orders;";
	          ResultSet rs = stmt.executeQuery(sql);
	          
	          while (rs.next()){
	              
	              al.add(rs.getString("orderNo"));
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


