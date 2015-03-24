package poscontrol;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import database.DB_Tables;

public class POS_Control implements Initializable {
	//tina changed this at 17.05
	@FXML
	private FlowPane p4;
	@FXML
	private HBox p2;
	@FXML
	private Button tablesDisplayButton, reportButton, stockButton,
			LogoutButton, userButton;
	@FXML
	private TextArea orderPane;

	Button b, p2MenuButtons;

	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> menuButtonNames = new ArrayList<String>();

	DB_Tables dbc = new DB_Tables();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Application Started");

	}

	/*
	 * @FXML private void retrieveTables(){ al = dbc.retrieveTableDetails();
	 * p4.getChildren().clear();
	 * 
	 * for(int i = 0; i < al.size(); i++){ b = new Button(al.get(i)); String
	 * buttonId = Integer.toString(count); b.setId(buttonId);
	 * p4.setVisible(true); p4.getChildren().addAll(b);
	 * 
	 * b.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent event) {
	 * if(b.idProperty().equals("12") || b.idProperty().equals("11") ||
	 * b.idProperty().equals("10") || b.idProperty().equals("9") ||
	 * b.idProperty().equals("8") || b.idProperty().equals("7") ||
	 * b.idProperty().equals("6") || b.idProperty().equals("5") ||
	 * b.idProperty().equals("4") || b.idProperty().equals("3") ||
	 * b.idProperty().equals("2") || b.idProperty().equals("1")){
	 * 
	 * String s = b.idProperty().toString(); System.out.println(s);
	 * 
	 * Button butClicked = b; handleTableSelection(butClicked);
	 * 
	 * } System.out.println("Hello World!"); String s =
	 * b.idProperty().toString(); System.out.println(s);
	 * orderPane.setText(b.idProperty().toString());
	 * System.out.println(b.idProperty()); } }); }count++;
	 * //btnRetrieve.setDisable(true); }
	 */
	@FXML
	private void retrieveTables() {

		al = dbc.retrieveTableDetails();
		p4.getChildren().clear();

		int count = 1;

		for (int i = 0; i < al.size(); i++) {
			b = new Button(al.get(i));
			b.setMinSize(90, 90);
			String tableNo = Integer.toString(count);
			b.setId(tableNo);
			System.out.println(tableNo);
			b.setOnAction(tableSelectionHandler);
			p4.setVisible(true);
			p4.getChildren().addAll(b);
			count++;

		}
	}

	/*
	 * b.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * @Override public void handle(ActionEvent evt) { if
	 * (evt.getSource().equals("12") || evt.getSource().equals("11") ||
	 * evt.getSource().equals("10") || evt.getSource().equals("9")
	 * ||evt.getSource().equals("8") || evt.getSource().equals("7") ||
	 * evt.getSource().equals("6") || evt.getSource().equals("5") ||
	 * evt.getSource().equals("4") || evt.getSource().equals("3") ||
	 * evt.getSource().equals("2") || evt.getSource().equals("1")){
	 * 
	 * Button butClicked = b; handleTableSelection(butClicked);
	 * 
	 * } System.out.println("Hello World!");
	 * orderPane.setText(b.getText().toString()+"\n");
	 * //System.out.println(b.idProperty()); } }); }count++;
	 */
	// btnRetrieve.setDisable(true);

	@FXML
	private void logout() {
		System.exit(0);
	}

	EventHandler<ActionEvent> tableSelectionHandler = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {
			orderPane.setText(b.getId());
			menuButtonNames.add("Evening Menu");
			menuButtonNames.add("Lunch Menu");
			menuButtonNames.add("Main Course");
			menuButtonNames.add("Desserts");
			menuButtonNames.add("Logout");
			p2.getChildren().clear();
			for (int i = 0; i < menuButtonNames.size(); i++) {
				p2MenuButtons = new Button(menuButtonNames.get(i));
				p2MenuButtons.setMinSize(90, 90);
				p2MenuButtons.setOnAction(menuButtonHandler);
				p2.getChildren().addAll(p2MenuButtons);

			}
			p4.getChildren().clear();
		}
	};

	EventHandler<ActionEvent> menuButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			al = dbc.retrieveDrinksSelection();
			p4.getChildren().clear();

			for (int i = 0; i < al.size(); i++) {
				b = new Button(al.get(i));
				b.setMinSize(90, 90);
				b.setOnAction(addSelectionOrderPane);
				p4.setVisible(true);
				p4.getChildren().addAll(b);
			}
		}

	};

	EventHandler<ActionEvent> addSelectionOrderPane = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {

		}

	};

	/*
	 * @FXML private void handlePane4Events(ActionEvent event) {
	 * System.out.println("Hello"); // p4.getChildren(); if
	 * (b.idProperty().equals("12") || b.idProperty().equals("11") ||
	 * b.idProperty().equals("10") || b.idProperty().equals("9") ||
	 * b.idProperty().equals("8") || b.idProperty().equals("7") ||
	 * b.idProperty().equals("6") || b.idProperty().equals("5") ||
	 * b.idProperty().equals("4") || b.idProperty().equals("3") ||
	 * b.idProperty().equals("2") || b.idProperty().equals("1")) {
	 * 
	 * String s = b.idProperty().toString(); System.out.println(s);
	 * System.out.println("Makes it to here!!"); orderPane.setText(b.getId());
	 * Button butClicked = b; handleTableSelection(butClicked);
	 * 
	 * } }
	 * 
	 * private void handleTableSelection(Button b) {
	 * System.out.println("handleTableSelection....." + b.idProperty()); }
	 */
}
