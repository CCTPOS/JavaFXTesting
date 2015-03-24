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
				tableDetails.append(tableNo);
				tableDetails.append(" " + No_Seats);

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
			sql = "select Nickname from items where item_type ='Drinks';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				al.add(rs.getString("Nickname"));
			}
		} catch (Exception e) {
		}
		return al;
	}

}
