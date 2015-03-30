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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import testdb.OrderEntry;
import testdb.ScreenController;
import testdb.ScreenPane;
import database.DB_Tables;

public class POS_Control implements Initializable, ScreenController {

	private ScreenPane myScreenPane;

	@FXML
	private FlowPane p4;
	@FXML
	private HBox hboxMenuSelectionButtons;
	@FXML
	private Button tablesDisplayButton, reportButton, stockButton,
			LogoutButton, userButton;
	@FXML
	private TextArea orderPane;
	@FXML
	private TextField userDetails, billTableNo, billTotal;

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

	Button b, hboxMenuButtons;

	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> menuButtonNames = new ArrayList<String>();

	DB_Tables dbc = new DB_Tables();

	ObservableList<OrderEntry> data;

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

		data = FXCollections.observableArrayList();
		orderView.setItems(data);

	}

	@Override
	public void setScreenPane(ScreenPane screenPage) {
		myScreenPane = screenPage;
	}

	@FXML
	private void retrieveTables() {

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
		}
	};

	EventHandler<ActionEvent> menuButtonHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			b = (Button) event.getSource();
			p4.getChildren().clear();

			if (b.getId() == "Drinks") {

				al = dbc.retrieveDrinksSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button(al.get(i));
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}
			} else if (b.getId() == "Starter") {

				al = dbc.retrieveStarterSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button(al.get(i));
					// System.out.println(al.get(i));
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Main") {

				al = dbc.retrieveMainSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button(al.get(i));
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Desserts") {

				al = dbc.retrieveDessertsSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button(al.get(i));
					b.setText(al.get(1));
					b.setMinSize(170, 100);
					b.setOnAction(addSelectionOrderPane);
					p4.setVisible(true);
					p4.getChildren().addAll(b);
				}

			} else if (b.getId() == "Sides") {

				al = dbc.retrieveSidesSelection();

				for (int i = 0; i < al.size(); i++) {
					b = new Button();
					b.setId(al.get(i));
					System.out.println("Button id = " + al.get(i));
					String[] nickname = ((String) al.get(i)).split(",");
					b.setText(nickname[1]);
					System.out.println(al.get(i));
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

	EventHandler<ActionEvent> addSelectionOrderPane = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			OrderEntry entry = new OrderEntry();

			// gets the source text of clicked button
			System.out.println("Source = " + event.getSource());
			String clickedButton = event.getSource().toString();
			// divides the source text into what we want and what we dont
			String[] splitSingleQuotes = clickedButton.split("'");
			// takes the part we want and splits it for orderPane manipulation
			String[] bContent = splitSingleQuotes[1].split(",");
			System.out.println(event.getSource());
			double price = Double.parseDouble(bContent[2]);

			int qtyCount = 1;
			entry.itemDescription.setValue(bContent[1]);
			entry.itemQty.setValue(qtyCount);
			entry.itemPrice.set(price);
			entry.itemSubTotal.set(price);

			data.add(entry);

			for (int i = 0; i < data.size() - 1; i++) {

				if (data.get(i).getItemDescription().equals(bContent[1])) {
					int xQty = data.get(i).getItemQty();
					data.get(i).itemQty.setValue(xQty + 1);
					double xPrice = data.get(i).getItemPrice();
					data.get(i).itemSubTotal.set(xPrice * xQty);
					data.remove(data.size() - 1);
				}
			}

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
		}

	};
}