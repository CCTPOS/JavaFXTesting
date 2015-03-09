
package restautanttestdb;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import testdb.DatabaseConnection;

public class RestaurantGUIdbController implements Initializable{
	
	@FXML private Pane pane;
	@FXML private Button btnRetrieve, dbSubmit, btnRetrieveSurname, btnRetrieveAddress, btnRetrievePurchases;
	@FXML private TextField orderNo, dateTime, total, status, pay_type;
	
	ArrayList<String> al = new ArrayList<String>();
	
	RestaurantConnection dbc = new RestaurantConnection();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Application Started");
		
	}
	
	@FXML private void insertRestaurant(ActionEvent event){
		
		String s_id, s_name, s_surname, s_address, s_purchases;
		
		s_id = orderNo.getText();
		orderNo.clear();
		s_name = dateTime.getText();
		dateTime.clear();
		s_surname = total.getText();
		total.clear();
		s_address = status.getText();
		status.clear();
		s_purchases = pay_type.getText();
		pay_type.clear();
        
		dbc.insertRestaurant(sId, s_name, s_surname, s_address, s_purchases);
		
	}
	
	@FXML private void retrieveItem(){
		al = dbc.retrieveDatabase1();
		pane.getChildren().clear();
		
		for(int i = 0; i < al.size(); i++){
			Button b = new Button(al.get(i));
			pane.getChildren().addAll(b);
		}
		//btnRetrieve.setDisable(true);
	}
	
	@FXML private void retrieveSurname(){
		al = dbc.retrieveSurnameCol();
		pane.getChildren().clear();
		
		for(int i = 0; i < al.size(); i++){
			Button b = new Button(al.get(i));
			pane.getChildren().addAll(b);
		}
		//btnRetrieve.setDisable(true);
	}
	
	@FXML private void retrieveAddress(){
		al = dbc.retrieveAddressCol();
		pane.getChildren().clear();
		
		for(int i = 0; i < al.size(); i++){
			Button b = new Button(al.get(i));
			pane.getChildren().addAll(b);
		}
		//btnRetrieve.setDisable(true);
	}
	
	@FXML private void retrievePurchases(){
		al = dbc.retrievePurchasesCol();
		pane.getChildren().clear();
		
		for(int i = 0; i < al.size(); i++){
			Button b = new Button(al.get(i));
			pane.getChildren().addAll(b);
		}
		//btnRetrieve.setDisable(true);
	}
	
	/*@FXML private void retrieveItem(){
		Button b = new Button();
		b.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//pane.getOnMouseClicked().toString().
	}*/
}
{

}
