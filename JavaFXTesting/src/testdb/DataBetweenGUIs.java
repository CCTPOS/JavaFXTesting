package testdb;


public class DataBetweenGUIs {

	static String userName;
	static int staffId;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		DataBetweenGUIs.staffId = staffId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		DataBetweenGUIs.userName = userName;
		System.out.println("The users name is: " + userName);
	}

}
