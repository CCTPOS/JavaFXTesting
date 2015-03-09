
package restautanttestdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAPP extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)throws Exception {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/RestaurantTest.fxml"));
			
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/StyleSheets/TestDB.css");
            //scene.getStylesheets().add(getClass().getResource("/YouTubeFX/src/StyleSheets/TestDB.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("FXML is Simple");
            primaryStage.show();
        } catch (Exception e) {
			e.printStackTrace();
        }														
	
	}
}
