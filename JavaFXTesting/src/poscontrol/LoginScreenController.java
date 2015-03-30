package poscontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import testdb.ScreenController;
import testdb.ScreenPane;

public class LoginScreenController implements Initializable, ScreenController {

	private ScreenPane myScreenPane;

	// Label and buttons for the login screen
	@FXML
	PasswordField password;
	@FXML
	private Button one, two, three, four, five, six, seven, eight, nine, zero,
			clear, enter;
	@FXML
	private GridPane loginpanel;
	@FXML
	private Label incorrectPassword;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	// checking login details
	String input = "";

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
		if (password.getText().equals("5290")
				|| password.getText().equals("2275")
				|| password.getText().equals("8462")) {
			password.clear();
			input = "";
			myScreenPane.setScreen("main");

		} else {
			password.clear();
			incorrectPassword.setText("Incorrect Password!!");
			incorrectPassword.setTextFill(Color.RED);
			password.setStyle("-fx-border-color:red;");
			input = "";
		}
	}

	@FXML
	private void clearInput(ActionEvent event) {
		password.clear();
		input = "";
	}

}