package poscontrol;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import testdb.DataBetweenGUIs;
import testdb.OrderEntry;
import testdb.ScreenController;
import testdb.ScreenPane;
import testdb.Singleton;
import database.DB_Tables;

public class POS_Control implements Initializable, ScreenController {

	private ScreenPane myScreenPane;
	DataBetweenGUIs dbg;
	Singleton single;
	OrderEntry oe;
	@FXML
	private FlowPane p4;
	@FXML
	private HBox hboxMenuSelectionButtons;
	@FXML
	private Button tablesDisplayButton, reportButton, stockButton,
			LogoutButton, userButton, order, back;
	@FXML
	private Label incorrectPasswordController;
	@FXML
	private TextArea orderPane;
	@FXML
	private TextField Username, billTableNo, billTotal, userDetails;

	@FXML
	private TableView<OrderEntry> orderView;

	@FXML
	private TableColumn<OrderEntry, String> itemDescription;

	@FXML
	private TableColumn<OrderEntry, Integer> itemQty;

	@FXML
	private TableColumn<OrderEntry, Double> itemPrice;

	@FXML
	private TableColumn<OrderEntry, Double> itemSubTotal;

	@FXML
	private TableColumn<OrderEntry, String> itemID;

	Button b, hboxMenuButtons;

	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> menuButtonNames = new ArrayList<String>();
	String[] nickname;
	String[] bContent;
	// back button counter and array for storage
	int backCounter = 1;
	ArrayList<Integer> backHistory = new ArrayList<Integer>();

	DB_Tables dbc = new DB_Tables();

	ObservableList<OrderEntry> data;
	boolean bool = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Application Started");

		itemDescription
				.setCellValueFactory(new PropertyValueFactory<OrderEntry, String>(
						"itemDescription"));
		itemQty.setCellValueFactory(new PropertyValueFactory<OrderEntry, Integer>(
				"itemQty"));
		itemPrice
				.setCellValueFactory(new PropertyValueFactory<OrderEntry, Double>(
						"itemPrice"));
		itemSubTotal
				.setCellValueFactory(new PropertyValueFactory<OrderEntry, Double>(
						"itemSubTotal"));
		itemID.setCellValueFactory(new PropertyValueFactory<OrderEntry, String>(
				"itemID"));

		data = FXCollections.observableArrayList();
		orderView.setItems(data);

	}

	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	@FXML
	private void retrieveTables() {
		backHistory.add(backCounter);// gives us the 1 screen
		dbg = new DataBetweenGUIs();
		userDetails.setText(dbg.getUserName());
		al = dbc.retrieveTableDetails();
		p4.getChildren().clear();

		for (int i = 0; i < al.size(); i++) {
			b = new Button(al.get(i));
			String bId = al.get(i).substring(0, 8);
			b.setId(bId);
			b.setMinSize(170, 100);
			b.setOnAction(tableSelectionHandler);
			p4.getChildren().addAll(b);
			p4.setVgap(10);
			p4.setHgap(10);
		}
		backCounter = 2;
		backHistory.add(backCounter);// gives us the second screen
		for (Integer a : backHistory) {
			System.out.println("Retrieve Tables: " + a);
		}
	}

	@FXML
	private void logout() {
		myScreenPane.setScreen("login");
	}

	EventHandler<ActionEvent> tableSelectionHandler = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {

			String tableSource = event.getSource().toString().substring(10, 18);
			billTableNo.setText(tableSource);

			menuButtonNames.add("Drinks");
			menuButtonNames.add("Starter");
			menuButtonNames.add("Main");
			menuButtonNames.add("Desserts");
			menuButtonNames.add("Sides");
			menuButtonNames.add("Logout");

			hboxMenuSelectionButtons.getChildren().clear();

			for (int i = 0; i < menuButtonNames.size(); i++) {
				hboxMenuButtons = new Button(menuButtonNames.get(i));
				hboxMenuButtons.setMinSize(233, 185);
				hboxMenuButtons.setId(menuButtonNames.get(i));
				hboxMenuSelectionButtons.setSpacing(10);
				hboxMenuSelectionButtons.setPadding(new Insets(0, 10, 10, 0));
				hboxMenuButtons.setOnAction(menuButtonHandler);
				hboxMenuSelectionButtons.getChildren().addAll(hboxMenuButtons);
			}
			p4.getChildren().clear();
			backCounter = 3;
			backHistory.add(backCounter);
			for (Integer a : backHistory) {
				System.out.println("Retrieve Menu Selection: " + a);
			}
		}
	};

	EventHandler<ActionEvent> menuButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			b = (Button) event.getSource();
			p4.getChildren().clear();

			if (b.getId() == "Drinks") {

				backCounter = 4;
				backHistory.add(backCounter);
				for (Integer a : backHistory) {
					System.out.println("Retrieve Drinks: " + a);
				}

				al = dbc.retrieveDrinksSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}
			} else if (b.getId() == "Starter") {

				backCounter = 5;
				backHistory.add(backCounter);
				for (Integer a : backHistory) {
					System.out.println("Retrieve Starters: " + a);
				}

				al = dbc.retrieveStarterSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Main") {

				backCounter = 6;
				backHistory.add(backCounter);
				for (Integer a : backHistory) {
					System.out.println("Retrieve Main: " + a);
				}

				al = dbc.retrieveMainSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Desserts") {

				backCounter = 7;
				backHistory.add(backCounter);
				for (Integer a : backHistory) {
					System.out.println("Retrieve Desserts: " + a);
				}

				al = dbc.retrieveDessertsSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Sides") {

				backCounter = 8;
				backHistory.add(backCounter);

				for (Integer a : backHistory) {
					System.out.println("Retrieve Sides: " + a);
				}

				al = dbc.retrieveSidesSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Logout") {
				myScreenPane.setScreen("login");
			}
		}
	};
	OrderEntry entry;
	int counter = 0;
	double price = 0.00;
	EventHandler<ActionEvent> addSelectionOrderPane = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			String[] splitSingleQuotes;

			entry = new OrderEntry();

			// gets the source text of clicked button
			String clickedButton = event.getSource().toString();
			System.out.println("Clicked button = " + clickedButton);
			// divides the source text into what we want and what we dont
			splitSingleQuotes = clickedButton.split("=");
			// takes the part we want and splits it for orderPane manipulation
			bContent = splitSingleQuotes[1].split(",");

			// looks after the sum of the subTotal fields
			double billTotalAllRows = 0.00;
			for (OrderEntry o : data) {
				billTotalAllRows = billTotalAllRows + o.getItemSubTotal();
			}
			// looks after the formatting of the subTotal fields
			DecimalFormat df = new DecimalFormat("#.##");
			billTotalAllRows = Double.valueOf(df.format(billTotalAllRows));
			String parseBillTotalAllRows = String.valueOf(billTotalAllRows);
			billTotal.setText("€" + parseBillTotalAllRows);

			price = Double.valueOf(df.format(price));
			price = Double.parseDouble(bContent[2]);

			checkDuplicates();

			if (bool == true) {

				int qtyCount = 1;
				entry.itemDescription.setValue(bContent[1]);
				entry.itemQty.setValue(qtyCount);
				entry.itemPrice.set(price);
				entry.itemSubTotal.set(price);
				entry.itemID.setValue(bContent[3]);
				data.add(entry);
			}

			bool = true;
		}

	};

	private void checkDuplicates() {
		int j = 0;
		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).getItemDescription().equals(bContent[1])) {
				do {
					entry.itemDescription.setValue(bContent[1]);

					int xQty = data.get(i).getItemQty();
					xQty++;
					entry.itemQty.setValue(xQty);

					// data.get(i).itemQty.setValue(xQty);
					price = data.get(i).getItemPrice();
					entry.itemPrice.set(price);
					entry.itemSubTotal.set(price * xQty);
					entry.itemID.setValue(bContent[3]);
					data.set(i, entry);
					bool = false;
					System.out.println("duplicate "
							+ data.get(i).itemDescription.toString());
					j++;
				} while (j < 1);
			}
		}
	}

	@FXML
	public void createOrder() {
		String orderString = "";

		for (int i = 0; i < data.size(); i++) {

			orderString = orderString + data.get(i).getItemDescription() + ","
					+ data.get(i).getItemQty() + ","
					+ data.get(i).getItemPrice() + ","
					+ data.get(i).getItemSubTotal() + ","
					+ data.get(i).getItemID();
		}
		System.out.println(orderString);
	}

	@FXML
	public void back() {
		int head = backHistory.size() - 2;

		System.out.println(head);
	}
}