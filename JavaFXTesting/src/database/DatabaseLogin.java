package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import poscontrol.LoginScreenController;

public class DatabaseLogin {

	static ArrayList<String> al = new ArrayList<String>();

	static public ArrayList<String> retrieveUserDetails() {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "select Staff_Id, FName from staff where Password = ?;";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Restaurant", "root", "");
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, LoginScreenController.getInput());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String staffId = rs.getString(1);
				String fName = rs.getString(2);
				// StringBuilder staffDetails = new StringBuilder();
				// staffDetails.append(staffId + " ");
				// staffDetails.append(fName);

				al.add(staffId);
				al.add(fName);
			}
		} catch (Exception e) {
		}
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		return al;
	}

	static public ArrayList<String> getALInfo() {
		return al;
	}

}
