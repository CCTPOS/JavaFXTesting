package poscontrol;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import testdb.DataBetweenGUIs;
import testdb.OrderEntry;
import testdb.ScreenController;
import testdb.ScreenPane;
import database.DatabaseLogin;

public class LoginScreenController implements Initializable, ScreenController {
	OrderEntry oe;

	private ScreenPane myScreenPane;

	DataBetweenGUIs dataTransfer = new DataBetweenGUIs();

	// Label and buttons for the login screen
	@FXML
	PasswordField password;

	public String getPassword() {
		return password.getText();
	}

	@FXML
	private Button one, two, three, four, five, six, seven, eight, nine, zero,
			clear, enter;
	@FXML
	private GridPane loginpanel;
	@FXML
	private Label incorrectPassword;

	static ArrayList<String> al = new ArrayList<String>();

	DatabaseLogin dbl = new DatabaseLogin();

	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	// checking login details
	static String input = "";

	static public String getInput() {
		return input;
	}

	String user;

	@FXML
	private void enterNumber(ActionEvent event) {
		incorrectPassword.setText("");
		String clickedButton = event.getSource().toString();
		// divides the source text into what we want and what we dont
		String[] splitSingleQuotes = clickedButton.split("'");
		// takes the part we want and splits it for orderPane manipulation
		input = input + splitSingleQuotes[1];
		password.setText(input);

	}

	@FXML
	private void enterLoginCode(ActionEvent event) {
		int x = 0;
		al = DatabaseLogin.retrieveUserDetails();

		if (al.isEmpty()) {
			password.clear();
			incorrectPassword.setText("Incorrect Password!!");
			incorrectPassword.setTextFill(Color.RED);
			password.setStyle("-fx-border-color:red;");
			password.clear();
			input = "";
		} else {
			dataTransfer.setUserName(al.get(1));
			dataTransfer.setStaffId(x = Integer.parseInt(al.get(0)));
			password.clear();
			input = "";
			myScreenPane.setScreen("main");
		}
	}

	@FXML
	private void clearInput(ActionEvent event) {
		password.clear();
		input = "";
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}