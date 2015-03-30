package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_Tables {

	public ArrayList<String> retrieveTableDetails() {

		ArrayList<String> al = new ArrayList<String>();

		try {

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Table_No, No_Of_Seats from tables;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String tableNo = rs.getString("Table_No");
				String No_Seats = rs.getString("No_Of_Seats");
				StringBuilder tableDetails = new StringBuilder();
				tableDetails.append("Table " + tableNo);
				tableDetails.append("\nSeats " + No_Seats);

				al.add(tableDetails.toString());
			}
		} catch (Exception e) {
		}
		return al;
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

	public ArrayList<String> retrieveDessertsSelection() {

		ArrayList<String> al = new ArrayList<String>();

		try {

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/restaurant", "root", "");
			Statement stmt = (Statement) con.createStatement();
			String sql;
			sql = "select Item_Id,Nickname,Price, Price from items where item_type ='Desserts';";
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
}
