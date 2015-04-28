package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import poscontrol.POS_Control;

public class DB_Tables {

	Timestamp timeStamp;
	POS_Control posc;
	String tableNo = null;

	public ArrayList<String> retrieveTableDetails() {

		ArrayList<String> al = new ArrayList<String>();

		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Table_No from tables;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String tableNo = rs.getString("Table_No");
				al.add(tableNo);
			}
		} catch (Exception e) {
		}
		return al;
	}

	public String retrieveTableStatus() {
		String tableStatus = null;
		tableNo = POS_Control.tableSource;
		System.out.println(tableNo);

		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "SELECT Table_Status FROM tables where Table_No = '"
					+ tableNo + "';"; // Now called Table_Status in database
			ResultSet rs = stmt.executeQuery(sql); // to distinguish from
													// Ord_Status

			while (rs.next()) {
				tableStatus = rs.getString("Table_Status");
			}
		} catch (Exception e) {
		}
		return tableStatus;
	}

	public ArrayList<String> retrieveDrinksSelection() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Drinks';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String price2 = rs.getString("Price");
				String nickname = rs.getString("NickName");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				StringBuilder itemDetails = new StringBuilder();
				itemDetails.append(price2);
				itemDetails.append("," + nickname);
				itemDetails.append("," + price);
				itemDetails.append("," + itemId);

				al.add(itemDetails.toString());
			}
			// System.out.println(al);
		} catch (Exception e) {
		}
		return al;
	}

	public ArrayList<String> retrieveStarterSelection() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Starter';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String price2 = rs.getString("Price");
				String nickname = rs.getString("NickName");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				StringBuilder itemDetails = new StringBuilder();
				itemDetails.append(price2);
				itemDetails.append("," + nickname);
				itemDetails.append("," + price);
				itemDetails.append("," + itemId);

				al.add(itemDetails.toString());
			}
		} catch (Exception e) {
		}
		return al;
	}

	public ArrayList<String> retrieveMainSelection() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Main';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String price2 = rs.getString("Price");
				String nickname = rs.getString("NickName");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				StringBuilder itemDetails = new StringBuilder();
				itemDetails.append(price2);
				itemDetails.append("," + nickname);
				itemDetails.append("," + price);
				itemDetails.append("," + itemId);

				al.add(itemDetails.toString());
			}
		} catch (Exception e) {
		}
		return al;
	}

	public ArrayList<String> retrieveDessertSelection() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Dessert';"; // No
																									// 's'
																									// on
																									// end
																									// of
																									// word
																									// in
			ResultSet rs = stmt.executeQuery(sql); // the database

			while (rs.next()) {
				String price2 = rs.getString("Price");
				String nickname = rs.getString("NickName");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				StringBuilder itemDetails = new StringBuilder();
				itemDetails.append(price2);
				itemDetails.append("," + nickname);
				itemDetails.append("," + price);
				itemDetails.append("," + itemId);

				al.add(itemDetails.toString());
			}
		} catch (Exception e) {
		}
		return al;
	}

	public ArrayList<String> retrieveSidesSelection() {
		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Sides';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String price2 = rs.getString("Price");
				String nickname = rs.getString("NickName");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				StringBuilder itemDetails = new StringBuilder();
				itemDetails.append(price2);
				itemDetails.append("," + nickname);
				itemDetails.append("," + price);
				itemDetails.append("," + itemId);

				al.add(itemDetails.toString());
			}
		} catch (Exception e) {
		}
		return al;
	}

	public void updateTableStatus(String tableNo) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "update tables set Table_Status = 'Occupied' where Table_No = '"
					+ tableNo + "';";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Retrieving details from database using Table_No. Not sure yet how much to
	// take on first select statement
	public ArrayList<String> retrieveAllOrderDetails() {
		tableNo = POS_Control.tableSource;
		System.out.println(tableNo);

		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Table_No, Order_No, Date_Time, Total_Amount from Involved_In "
					+ "natural join Orders where Table_No = '"
					+ tableNo
					+ "' order by Date_Time desc;";
			/*
			 * sql =
			 * "select Table_No, Order_No, Date_Time, Staff_Id, FName, Total_Amount, Ord_Status, Table_Status from Staff"
			 * +
			 * " natural join Partakes_In natural join Orders natural join Involved_In natural join Tables2 "
			 * + "where Table_No = '"+tableNo+"' order by Date_Time desc;";
			 */

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String orderNo = rs.getString("Order_No");
				String tableNo = rs.getString("Table_No");
				String dateTime = rs.getString("Date_Time");
				String totalAmount = rs.getString("Total_Amount");
				/*
				 * String staffId = rs.getString("Staff_Id"); String fName =
				 * rs.getString("FName"); String orderStatus =
				 * rs.getString("Ord_Status"); String tableStatus =
				 * rs.getString("Table_Status");
				 */
				StringBuilder detailsRetrieved = new StringBuilder();
				detailsRetrieved.append(orderNo);
				detailsRetrieved.append("," + tableNo);
				detailsRetrieved.append("," + dateTime);
				detailsRetrieved.append("," + totalAmount);
				/*
				 * detailsRetrieved.append("," + staffId);
				 * detailsRetrieved.append("," + fName);
				 * detailsRetrieved.append("," + orderStatus);
				 * detailsRetrieved.append("," + tableStatus);
				 */

				al.add(detailsRetrieved.toString());
			}

		} catch (Exception e) {
		}
		return al;
	}

	// Retrieving remaining details from database using Order_No. Which info
	// taken will depend on first select statement
	public ArrayList<String> retrieveBillInfo(int orderNo) {

		ArrayList<String> al = new ArrayList<String>();
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;

			sql = "select Price, Nickname, Item_Id, QuantityOrdered from Items "
					+ "natural join Needed_For where Order_No = "
					+ orderNo
					+ ";";
			/*
			 * sql =
			 * "select Price, Nickname, Item_Id, Staff_Id, FName, QuantityOrdered, Ord_Status, Table_Status from Items"
			 * +
			 * " natural join Needed_For natural join Orders natural join Involved_In natural join Tables2 "
			 * + "where Order_No = "+orderNo+";";
			 */// Still to be sorted

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String nickname = rs.getString("Nickname");
				String quantity = rs.getString("QuantityOrdered");
				String price = rs.getString("Price");
				String itemId = rs.getString("Item_Id");
				// String staffId = rs.getString("Staff_Id");
				// String fName = rs.getString("FName");
				// String orderStatus = rs.getString("Ord_Status");
				// String tableStatus = rs.getString("Table_Status");
				StringBuilder allOrderDetails = new StringBuilder();
				allOrderDetails.append(nickname);
				allOrderDetails.append("," + quantity);
				allOrderDetails.append("," + price);
				allOrderDetails.append("," + itemId);
				// allOrderDetails.append("," + staffId);
				// allOrderDetails.append("," + fName);
				// allOrderDetails.append("," + orderStatus);//
				// allOrderDetails.append("," + tableStatus);

				al.add(allOrderDetails.toString());
			}

		} catch (Exception e) {
		}
		return al;
	}

	// Retrieving Order_No from database using the most recent entry
	public int retrieveOrderNo() {
		String orderNo = null;
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "SELECT Order_No FROM orders ORDER BY Order_No DESC LIMIT 1;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				orderNo = rs.getString("Order_No");
			}
		} catch (Exception e) {
		}
		return Integer.parseInt(orderNo);
	}

	// Inserting into all tables in database on clicking Order button the first
	// time for a table
	public void insertIntoOrdersTable(double totalAmount, String status,
			String pay_type) {
		timeStamp = new Timestamp(System.currentTimeMillis());
		// Date dateTime = new java.sql.Date(timeStamp.getTime());
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "insert into orders(Date_Time, Total_Amount, Ord_Status, TypeOfPayment)"
					+ " values('"
					+ timeStamp
					+ "',"
					+ totalAmount
					+ ",'"
					+ status + "','" + pay_type + "');";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertIntoInvolvedInTable(int orderNo, String tableNo) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "insert into involved_in values(" + orderNo + ",'" + tableNo
					+ "');";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertIntoNeededForTable(int orderNo, String itemId,
			int quantityOrdered) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql = "insert into needed_for values(" + orderNo + ",'"
					+ itemId + "'," + quantityOrdered + ");";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertIntoGetsPrintedTable(int orderNo, int printerId) {
		timeStamp = new Timestamp(System.currentTimeMillis());
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql = "insert into gets_printed values(" + orderNo + ","
					+ printerId + ",'" + timeStamp + "');";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertIntoPartakesInTable(int orderNo, int staffId) {
		timeStamp = new Timestamp(System.currentTimeMillis());
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql = "insert into partakes_in values(" + orderNo + ","
					+ staffId + ",'" + timeStamp + "');";
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * public void updateOrdersTable(int orderNo, double totalAmount){ timeStamp
	 * = new Timestamp(System.currentTimeMillis()); try { Connection con =
	 * DriverManager.getConnection( "jdbc:mysql://localhost:3306/restaurant",
	 * "root", ""); Statement stmt = (Statement) con.createStatement(); String
	 * sql; sql =
	 * "update orders set Total_Amount = "+totalAmount+", Date_Time = '"
	 * +timeStamp+"' where Order_No = "+orderNo+";"; stmt.executeUpdate(sql);
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } }
	 */

}
