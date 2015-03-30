package testdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NewMainScreenAPP extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root1 = FXMLLoader.load(getClass().getResource(
					"/view/NewMainScreen.fxml"));

			Rectangle2D primaryScreenBounds = Screen.getPrimary()
					.getVisualBounds();

			// set Stage boundaries to visible bounds of the main screen

			primaryStage.setX(primaryScreenBounds.getMinX());
			primaryStage.setY(primaryScreenBounds.getMinY());
			primaryStage.setWidth(primaryScreenBounds.getWidth());
			primaryStage.setHeight(primaryScreenBounds.getHeight());

			Scene scene = new Scene(root1);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login Screen");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
