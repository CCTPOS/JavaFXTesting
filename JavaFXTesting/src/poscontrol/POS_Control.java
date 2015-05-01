package poscontrol;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import testdb.Buttons;
import testdb.DataBetweenGUIs;
import testdb.OrderEntry;
import testdb.ScreenController;
import testdb.ScreenPane;
import database.DB_Tables;

public class POS_Control implements Initializable, ScreenController {

	public static String tableSource = null;

	private ScreenPane myScreenPane;

	Buttons buttons = new Buttons();

	@FXML
	AnchorPane anchorpane, transparentBackground, paymentButtonTypes;

	@FXML
	private FlowPane p4;

	@FXML
	private Pane paymentConfirmationPane;

	@FXML
	private HBox hboxMenuSelectionButtons, paymentButtonPane;

	@FXML
	private Button tablesDisplayButton, reports, stock, LogoutButton, users,
			order, Back, note, clear, payment, yes, no;

	@FXML
	private Label incorrectPasswordController;

	@FXML
	private TextArea orderPane;

	@FXML
	private TextField billTableNo, billTotal, userDetails, userId;

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
	private TableColumn<OrderEntry, String> itemId;

	Button b, b2, hboxMenuButtons, paymentButtons;

	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> menuButtonNames = new ArrayList<String>();
	ArrayList<String> addedOrderItems = new ArrayList<String>();
	ArrayList<Button> buttonArray = new ArrayList<Button>();

	String[] nickname;
	String[] bContent;
	String[] orderDetails;
	String[] billDetails;

	String newItems;
	String backFromOrderView;

	String paymentType;

	// back button counter and array for storage
	int element;
	int backCounter = 1;
	ArrayList<Integer> backHistory = new ArrayList<Integer>();

	DB_Tables dbc = new DB_Tables();
	DataBetweenGUIs dbg;
	OrderEntry oe;

	ObservableList<OrderEntry> data;
	// ObservableList<OrderEntry> extraData;

	boolean isDuplicated = true;
	boolean isOccupied = false;
	boolean paid = false;
	boolean voidingItem = true;

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
						"itemSubtotal"));
		itemId.setCellValueFactory(new PropertyValueFactory<OrderEntry, String>(
				"itemId"));

		data = FXCollections.observableArrayList();
		// extraData = FXCollections.observableArrayList();
		orderView.setItems(data);
		orderView.getColumns().setAll(
				Arrays.asList(itemDescription, itemQty, itemPrice,
						itemSubTotal, itemId));
		paymentButtonPane.setVisible(false);
		paymentConfirmationPane.setVisible(false);
	}

	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	@FXML
	public void setUserDetails() {

	}

	@FXML
	private void retrieveTables() {

		al = dbc.retrieveTableDetails();
		p4.getChildren().clear();

		for (int i = 0; i < al.size(); i++) {
			b = new Button(al.get(i));
			String bId = al.get(i);
			b.setId(bId);
			b.setMinSize(170, 100);
			b.setOnAction(tableSelectionHandler);
			p4.getChildren().addAll(b);
			p4.setVgap(10);
			p4.setHgap(10);
		}

		if (backCounter == 1) {
			backHistory.add(backCounter);// gives us the 1 screen
		}

		if (backCounter > 1 && backHistory.get(backHistory.size() - 1) == 2) {
			return;
		} else {
			backCounter = 2;
			backHistory.add(backCounter);// gives us the second screen
		}
	}

	@FXML
	private void logout() {
		backHistory.clear();
		System.out.println("Clear");
		backCounter = 2;
		backHistory.add(2, 2);
		System.out.println("Logout Back History = " + backHistory.get(0));
		back();
		backHistory.clear();
		myScreenPane.setScreen("login");
	}

	EventHandler<ActionEvent> tableSelectionHandler = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {
			backCounter = 3;
			backHistory.add(backCounter);

			tableSource = event.getSource().toString().substring(10, 18);
			tableSelectionPane(tableSource);
		}
	};

	// replaces tables/stock/report....with MenuSelection Buttons
	private void tableSelectionPane(String tableSource) {

		billTableNo.setText(tableSource);

		String tableStatus = dbc.retrieveTableStatus();
		if (!tableStatus.equals("Vacant")) { // If occupied retrieves the
												// order
			retrieveOrder();
			isOccupied = true;
		} else {
			isOccupied = false;
		}
		menuButtonNames.clear();
		menuButtonNames = buttons.menuSelectionButtons();
		hboxMenuSelectionButtons.getChildren().clear();

		for (int i = 0; i < menuButtonNames.size(); i++) {
			hboxMenuButtons = new Button(menuButtonNames.get(i));
			hboxMenuButtons.setMinSize(233, 185);
			hboxMenuButtons.setId(menuButtonNames.get(i));
			hboxMenuSelectionButtons.setSpacing(10);
			hboxMenuSelectionButtons.setPadding(new Insets(0, 5, 5, 0));
			hboxMenuButtons.setOnAction(menuButtonHandler);
			hboxMenuSelectionButtons.getChildren().addAll(hboxMenuButtons);
		}
		p4.getChildren().clear();
	}

	EventHandler<ActionEvent> menuButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			b = (Button) event.getSource();
			p4.getChildren().clear();

			if (b.getId() == "DRINKS") {

				backCounter = 4;
				backHistory.add(backCounter);

				for (int i : backHistory) {
					System.out.println(i);
				}

				retrieveDrinks();

			} else if (b.getId() == "STARTER") {

				backCounter = 5;
				backHistory.add(backCounter);

				retrieveStarters();

			} else if (b.getId() == "MAIN") {

				backCounter = 6;
				backHistory.add(backCounter);

				retrieveMains();

			} else if (b.getId() == "DESSERT") {
				backCounter = 7;
				backHistory.add(backCounter);

				retrieveDesserts();

			} else if (b.getId() == "SIDES") {

				backCounter = 8;
				backHistory.add(backCounter);

				retrieveSides();

			} else if (b.getId() == "LOGOUT") {
				backHistory.clear();
				backCounter = 1;
				backHistory.add(backCounter);
				back();
				myScreenPane.setScreen("login");
			}
		}
	};

	private void retrieveDrinks() {
		/*
		 * entry = new OrderEntry(); entry.itemDescription.setValue(b.getId());
		 * data.add(entry);
		 */
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

		if (backHistory.get(backHistory.size() - 1) == 4) {
			return;
		} else {
			backCounter = 4;
			backHistory.add(backCounter);
		}
	}

	// retrieves starters when starters is clicked
	private void retrieveStarters() {
		/*
		 * entry = new OrderEntry(); entry.itemDescription.setValue(b.getId());
		 * data.add(entry);
		 */
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
		if (backCounter > 0 && backHistory.get(backHistory.size() - 1) == 5) {
			return;
		} else {
			backCounter = 5;
			backHistory.add(backCounter);
		}
	}

	// retrieves mains when mains is clicked
	private void retrieveMains() {
		/*
		 * entry = new OrderEntry(); entry.itemDescription.setValue(b.getId());
		 * data.add(entry);
		 */
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
		if (backCounter > 0 && backHistory.get(backHistory.size() - 1) == 6) {
			return;
		} else {
			backCounter = 6;
			backHistory.add(backCounter);
		}
	}

	// retrieves desserts when desserts is clicked
	private void retrieveDesserts() {

		/*
		 * entry = new OrderEntry(); entry.itemDescription.setValue(b.getId());
		 * data.add(entry);
		 */
		al = dbc.retrieveDessertSelection();

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
		if (backCounter > 0 && backHistory.get(backHistory.size() - 1) == 7) {
			return;
		} else {
			backCounter = 7;
			backHistory.add(backCounter);
		}
	}

	// retrieves sides when sides is clicked
	private void retrieveSides() {
		/*
		 * entry = new OrderEntry(); entry.itemDescription.setValue(b.getId());
		 * data.add(entry);
		 */
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
		if (backCounter > 0 && backHistory.get(backHistory.size() - 1) == 8) {
			return;
		} else {
			backCounter = 8;
			backHistory.add(backCounter);
		}
	}

	OrderEntry entry; // Code for getting food items
	double price = 0.00;

	EventHandler<ActionEvent> addSelectionOrderPane = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			// orderView = new TableView<OrderEntry>();
			// gets the source text of clicked button
			String clickedButton = event.getSource().toString();
			System.out.println(clickedButton);
			// divides the source text into what we want and what we dont
			String[] splitSingleQuotes = clickedButton.split("=");
			System.out.println(splitSingleQuotes);
			// takes the part we want and splits it for orderPane manipulation
			bContent = splitSingleQuotes[1].split(",");
			newItems = splitSingleQuotes[1];
			// System.out.println(bContent);
			price = Double.parseDouble(bContent[2]);

			// looks after the sum of the subTotal fields
			double billTotalAllRows = 0.00;
			for (OrderEntry o : data) {
				billTotalAllRows = billTotalAllRows + o.getItemSubTotal();
			}
			// looks after the formatting of the subTotal fields
			DecimalFormat df = new DecimalFormat("#.##");
			billTotalAllRows = Double.valueOf(df.format(billTotalAllRows));
			String parseBillTotalAllRows = String.valueOf(billTotalAllRows);
			billTotal.setText(parseBillTotalAllRows);

			checkDuplicates();

			if (isDuplicated) {
				addedOrderItems.add(newItems);
				int qtyCount = 1;

				data.add(new OrderEntry(bContent[1], qtyCount, price, price,
						bContent[3]));
				orderView.setItems(data);
				// orderView.setOnMouseClicked(e -> selectItemFromPane());

			}

			isDuplicated = true;
		}

	};

	private void checkDuplicates() {
		int j = 0;
		for (int i = 0; i < data.size(); i++) {

			if (data.get(i).getItemDescription().equals(bContent[1])) {
				do {
					addedOrderItems.add(newItems);
					String desc = data.get(i).getItemDescription();
					int qty = data.get(i).getItemQty();
					qty = qty + 1;
					double price = data.get(i).getItemPrice();
					String id = data.get(i).getItemId();
					double subtotal = price * qty;
					data.set(i, new OrderEntry(desc, qty, price, subtotal, id));
					orderView.setItems(data);

					isDuplicated = false;

					j++;
				} while (j < 1);
			}
		}
	}

	@FXML
	private void back() {

		if (backHistory.size() < 2) {
			return;
		} else {

			element = backHistory.get(backHistory.size() - 2);

			if (element == 0) {
				System.out.println("No history!!!");
			} else if (element == 1) {

				hboxMenuSelectionButtons.getChildren().clear();
				buttonArray.clear();
				buttonArray.addAll(Arrays.asList(tablesDisplayButton, reports,
						stock, users, LogoutButton));

				for (Button bA : buttonArray) {
					hboxMenuSelectionButtons.getChildren().addAll(bA);
				}

				p4.getChildren().clear();
				backHistory.remove(element);

			} else if (element == 2) {

				if (element + 1 == 3 && orderView.getItems().size() == 0) {
					backFromOrderView = "YES";
					pane3ToPane2();
				} else {
					pane3ToPane2();
				}

			} else if (element == 3) {

				String tabSource = billTableNo.getText();
				tableSelectionPane(tabSource);

				backHistory.remove(element);
				p4.getChildren().clear();

			} else if (element == 4) {

				backHistory.remove(element);
				p4.getChildren().clear();
				retrieveDrinks();

			} else if (element == 5) {

				backHistory.remove(element);
				p4.getChildren().clear();
				retrieveStarters();

			} else if (element == 6) {

				backHistory.remove(element);
				p4.getChildren().clear();
				retrieveMains();

			} else if (element == 7) {

				backHistory.remove(element);
				p4.getChildren().clear();
				retrieveDesserts();

			} else if (element == 8) {

				backHistory.remove(element);
				p4.getChildren().clear();
				retrieveSides();

			} else if (element == 9) {

				backHistory.remove(element);
				paymentConfirmationPane.setVisible(false);
				paymentSelectionScreen();

			}

			System.out.println("The previous screen was :" + element);
		}
	}

	private void pane3ToPane2() {

		String response = "";

		if (backFromOrderView == "YES") {

		} else {

			/*
			 * Optional<ButtonType> result = myHandler.showAndWait(); if
			 * (result.get() == ButtonType.OK){ // ... user chose OK } else { //
			 * ... user chose CANCEL or closed the dialog } }
			 */}
		if (response == "YES" || backFromOrderView == "YES") {
			System.out.println("2 " + response);
			// confirmRequestOD.setVisible(false);

			// need to check out deleted element
			hboxMenuSelectionButtons.getChildren().clear();
			buttonArray.clear();
			buttonArray.addAll(Arrays.asList(tablesDisplayButton, reports,
					stock, users, LogoutButton));

			for (Button bA : buttonArray) {
				System.out.println("Tables/ Stock/Reports...." + bA.toString());
				hboxMenuSelectionButtons.getChildren().addAll(bA);
			}
			// Clears Pane 4 and resets them to Table Selection
			p4.getChildren().clear();
			backHistory.remove(element);

			retrieveTables();
		} else if (backFromOrderView == "NO") {
			System.out.println("2 " + backFromOrderView);
			return;
		}
		backFromOrderView = "";
	}

	@FXML
	public void selectItemFromPane() {

		int i_qty = orderView.getSelectionModel().getSelectedItem()
				.getItemQty();
		int index = orderView.getSelectionModel().selectedIndexProperty()
				.getValue();
		System.out.println("Index is   " + index);
		System.out.println("Quantity is   " + i_qty);

		if (i_qty > 1) {

			String desc = data.get(index).getItemDescription();
			i_qty = i_qty - 1;
			double price = data.get(index).getItemPrice();
			String id = data.get(index).getItemId();
			double subtotal = price * i_qty;
			data.set(index, new OrderEntry(desc, i_qty, price, subtotal, id));
			orderView.setItems(data);

		} else {
			data.remove(index);
		}

	}

	@FXML
	private void voidItem() {
		voidingItem = true;
		selectItemFromPane();
	}

	@FXML
	public void createOrder() { // If table is vacant the first entry goes to
		// database otherwise
		// Stage dialog = null;
		/*
		 * Dialogs.showConfirmDialog(dialog,
		 * "Are you sure you want to create the order", "Confirmation Request");
		 */

		if (!isOccupied) {// database gets updated
			insertIntoDatabase();
		} else {
			// int orderNo = Integer.parseInt(orderDetails[0]);
			// updateDatabase(orderNo);
		}
		// sendToKitchenPrinter(); //Prints to kitchen hopefully!!
	}

	// Code for inserting into the different tables in database when creating
	// initial order
	public void insertIntoDatabase() {
		String pay_type = null, orderStatus = null, itemId = null;
		int quantityOrdered = 0, printerId = 0, staffId = 0;
		double totalAmount = Double.parseDouble(billTotal.getText());
		String tableNo = billTableNo.getText();

		if (!paid) {
			orderStatus = "owed";
			printerId = 1;
		} else {
			orderStatus = "paid";
			printerId = 2; // Needs to set this to true somehow
		}

		dbc.insertIntoOrdersTable(totalAmount, orderStatus, pay_type);
		int orderNo = dbc.retrieveOrderNo();
		dbc.insertIntoInvolvedInTable(orderNo, tableNo);
		dbc.insertIntoGetsPrintedTable(orderNo, printerId);
		staffId = dbg.getStaffId();
		dbc.insertIntoPartakesInTable(orderNo, staffId);
		dbc.updateTableStatus(tableNo);

		try {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).getItemDescription().equals("Drinks")
						|| data.get(i).getItemDescription().equals("Starter")
						|| data.get(i).getItemDescription().equals("Main")
						|| data.get(i).getItemDescription().equals("Dessert")
						|| data.get(i).getItemDescription().equals("Sides")) {
					itemId = data.get(i + 1).getItemId();
					quantityOrdered = data.get(i + 1).getItemQty();
					// System.out.println("value of i is "+i);
					dbc.insertIntoNeededForTable(orderNo, itemId,
							quantityOrdered);
					i++;
					// System.out.println("value of i is "+i);
				} else {
					itemId = data.get(i).getItemId();
					quantityOrdered = data.get(i).getItemQty();
					dbc.insertIntoNeededForTable(orderNo, itemId,
							quantityOrdered);
				}
			}
		} catch (NullPointerException npe) {
			System.out.println("Continue with Error");
		}
		data.clear();
		isOccupied = true; // Set table to now being occupied
	}

	private void retrieveOrder() { // Need to put in headings eg. mains etc.
		al = dbc.retrieveAllOrderDetails(); // Retrieving details from database
											// based on Table_No
											// mainly to get Order_No and
											// Total_Amount
		String orderInfo = al.get(0);
		orderDetails = orderInfo.split(",");
		int orderNo = Integer.parseInt(orderDetails[0]);
		String totalAmount = orderDetails[3];
		billTotal.setText(totalAmount);

		al = dbc.retrieveBillInfo(orderNo); // Retrieving details from database
											// based on Order_No
		/*
		 * for (int i = 0; i < al.size(); i++) { System.out.println(al.get(i));
		 * entry = new OrderEntry(); billDetails = al.get(i).split(",");
		 * entry.itemDescription.setValue(billDetails[0]); int quantity =
		 * Integer.parseInt(billDetails[1]); entry.itemQty.setValue(quantity);
		 * double price = Double.parseDouble(billDetails[2]);
		 * entry.itemPrice.set(price); entry.itemSubTotal.set(quantity * price);
		 * entry.itemId.setValue(billDetails[3]); data.add(entry); }
		 */
		addedOrderItems.clear();
	}

	@FXML
	public void paneChangingTest() {
		for (String s : addedOrderItems) {
			System.out.println(s);
		}
	}

	@FXML
	public void paymentSelectionScreen() {
		menuButtonNames.clear();
		menuButtonNames = buttons.paySelectionButtons();

		// paymentButtonPane.setStyle("-fx-background-color: #336699;");

		for (int i = 0; i < menuButtonNames.size(); i++) {
			paymentButtons = new Button(menuButtonNames.get(i));
			paymentButtons.setMinSize(250, 380);
			paymentButtons.setId(menuButtonNames.get(i));
			paymentButtons.setOnAction(paymentButtonHandler);
			paymentButtonPane.setSpacing(10);
			paymentButtonPane.setPadding(new Insets(10, 10, 10, 10));
			paymentButtonPane.getChildren().addAll(paymentButtons);
			System.out.println("*************************************   "
					+ paymentButtons.getId());
		}
		backCounter = 9;
		backHistory.add(backCounter);
		paymentButtonPane.setVisible(true);
	}

	EventHandler<ActionEvent> paymentButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			b = (Button) event.getSource();
			paymentType = b.getText();
			paymentButtonPane.setVisible(false);
			paymentConfirmationPane.setVisible(true);
		}
	};

	public void confirmedYesNo(ActionEvent e) {
		b = (Button) e.getSource();
		if (b.getText() == "YES") {
			// do the database thing
		} else {
			// do the database thing tell staff order is saved as unpaid???
		}

	}
}

/*
 * public void updateDatabase(int orderNo){ //Also store new user in the
 * database double totalAmount = Double.parseDouble(billTotal.getText()); int
 * staffId = Integer.parseInt(userId.getText()); //Confirm this!!
 * 
 * if(!paid){ status = "owed"; printerId = 1; }else{ printerId = 2; }
 * 
 * //dbc.updateOrdersTable(orderNo, totalAmount);
 * //dbc.insertIntoGetsPrintedTable(orderNo, printerId); //Need to get from
 * database //dbc.insertIntoPartakesInTable(orderNo, staffId); //Need to get
 * from database //Array list here for any new entries!! for (int i = 0; i <
 * data.size(); i++) { itemId = data.get(i).getItemId(); quantityOrdered =
 * data.get(i).getItemQty(); dbc.updateNeededForTable(orderNo, itemId,
 * quantityOrdered); }
 * 
 * }
 * 
 * 
 * /* private void retrieveOrder() { // Need to put in headings eg. mains etc.
 * al = dbc.retrieveAllOrderDetails(); String orderInfo = null; // String
 * billInfo = null; // entry = new OrderEntry();
 * 
 * for (int i = 0; i < al.size(); i++) { orderInfo = al.get(0);
 * System.out.println(al.get(i)); }
 * 
 * orderDetails = orderInfo.split(","); // orderDetails = 70,Table 23,2015-04-18
 * 21:53:15.0,13.50
 * 
 * int orderNo = Integer.parseInt(orderDetails[0]); String totalAmount =
 * orderDetails[3]; billTotal.setText(totalAmount);
 * 
 * al = dbc.retrieveBillInfo(orderNo); // Retrieving details fro
 * 
 * for (int i = 0; i < al.size(); i++) { // Code not working properly
 * System.out.println(al.get(i)); entry = new OrderEntry(); String[] items =
 * al.get(i).split(","); // int qtyCount = 1;
 * entry.itemDescription.setValue(items[0]); int quantity =
 * Integer.parseInt(items[1]); entry.itemQty.setValue(quantity); double price =
 * Double.parseDouble(items[2]); entry.itemPrice.set(price);
 * entry.itemSubTotal.set(quantity * price); entry.itemId.setValue(items[3]);
 * data.add(entry); data.set(i, entry); } }
 */// Jays Code Above

/*
 * String orderString = ""; for (int i = 0; i < data.size(); i++) { orderString
 * = orderString + data.get(i).getItemDescription() + "," +
 * data.get(i).getItemQty() + "," + data.get(i).getItemPrice() + "," +
 * data.get(i).getItemSubTotal() + "," + data.get(i).getItemId() + ","; }
 * System.out.println(orderString);
 */

/*
 * private void sendToKitchenPrinter(){ //Need to put in headings eg. mains etc.
 * String orderString = ""; for (int i = 0; i < data.size(); i++) { orderString
 * = orderString + data.get(i).getItemDescription() + "," +
 * data.get(i).getItemQty() + ","; //Note to be added }
 * System.out.println(orderString); }
 */

