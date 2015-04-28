package testdb;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderEntry {

	private final StringProperty itemDescription;
	private final IntegerProperty itemQty;
	private final DoubleProperty itemPrice;
	private final DoubleProperty itemSubTotal;
	private final StringProperty itemId;

	public OrderEntry(String desc, int qty, Double price, Double subtotal,
			String id) {
		this.itemDescription = new SimpleStringProperty(desc);
		this.itemQty = new SimpleIntegerProperty(qty);
		this.itemPrice = new SimpleDoubleProperty(price);
		this.itemSubTotal = new SimpleDoubleProperty(subtotal);
		this.itemId = new SimpleStringProperty(id);
	}

	public String getItemDescription() {
		return itemDescription.get();
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription.set(itemDescription);
	}

	public StringProperty descProperty() {
		return itemDescription;
	}

	public int getItemQty() {
		return itemQty.get();
	}

	public void setItemQty(int qty) {
		this.itemQty.set(qty);
	}

	public IntegerProperty qtyProperty() {
		return itemQty;
	}

	public double getItemPrice() {
		return itemPrice.get();
	}

	public void setItemPrice(Double price) {
		this.itemPrice.set(price);
	}

	public DoubleProperty itemPriceProperty() {
		return itemPrice;
	}

	public double getItemSubTotal() {
		return itemSubTotal.get();
	}

	public void setItemSubTotal(Double subTotal) {
		this.itemSubTotal.set(subTotal);
	}

	public DoubleProperty subtotalProperty() {
		return itemSubTotal;
	}

	public String getItemId() {
		return itemId.get();
	}

	public void setItemId(String id) {
		this.itemId.set(id);
	}

	public StringProperty itemIdProperty() {
		return itemId;
	}
}