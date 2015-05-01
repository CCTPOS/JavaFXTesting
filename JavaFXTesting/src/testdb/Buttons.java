package testdb;

import java.util.ArrayList;

public class Buttons {

	ArrayList<String> buttons = new ArrayList<String>();

	public ArrayList<String> menuSelectionButtons() {
		buttons.clear();
		buttons.add("DRINKS");
		buttons.add("STARTER");
		buttons.add("MAIN");
		buttons.add("DESSERT");
		buttons.add("SIDES");
		buttons.add("LOGOUT");

		return buttons;

	}

	public ArrayList<String> paySelectionButtons() {
		buttons.clear();
		buttons.add("CASH");
		buttons.add("CREDITCARD");
		buttons.add("VOUCHER");

		return buttons;
	}
}
