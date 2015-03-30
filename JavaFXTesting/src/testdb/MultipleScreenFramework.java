package testdb;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MultipleScreenFramework extends Application {
	public static String loginFXML = "/view/LoginScreen.fxml";
	public static String mainScreenFXML = "/view/NewMainScreen.fxml";;

	@Override
	public void start(Stage stage) throws Exception {
		ScreenPane mainContainer = new ScreenPane();
		mainContainer.loadScreen("login", MultipleScreenFramework.loginFXML);
		mainContainer
				.loadScreen("main", MultipleScreenFramework.mainScreenFXML);
		mainContainer.setScreen("login");

		Scene scene = new Scene(mainContainer);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// set Stage boundaries to visible bounds of the main screen

		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}