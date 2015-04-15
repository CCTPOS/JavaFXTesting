package testdb;

public class DataBetweenGUIs {

	static String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		DataBetweenGUIs.userName = userName;
		System.out.println("The users name is: " + userName);
	}

}
