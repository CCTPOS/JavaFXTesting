package testdb;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderEntry {

	public SimpleStringProperty itemDescription = new SimpleStringProperty();
	public SimpleIntegerProperty itemQty = new SimpleIntegerProperty();
	public SimpleDoubleProperty itemPrice = new SimpleDoubleProperty();
	public SimpleDoubleProperty itemSubTotal = new SimpleDoubleProperty();

	public String getItemDescription() {
		return itemDescription.get();
	}

	public int getItemQty() {
		return itemQty.get();
	}

	public double getItemPrice() {
		return itemPrice.get();
	}

	public double getItemSubTotal() {
		return itemSubTotal.get();
	}

}