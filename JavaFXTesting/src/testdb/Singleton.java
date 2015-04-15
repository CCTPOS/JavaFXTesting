package testdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Singleton {

	private static Singleton instance = new Singleton();

	public static Singleton getInstance() {
		return instance;
	}

	private TextField txtField1;

	public String getTxtField1() {
		return txtField1.getText();
	}

	public void setTxtField1(String txtField1) {
		this.txtField1.setText(txtField1);
	}

	ObservableList<String> userName = FXCollections.observableArrayList();

	public ObservableList<String> getUserName() {
		return userName;
	}

	public void setUserName(ObservableList<String> userName) {
		this.userName = userName;
	}

}