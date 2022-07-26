package app;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class Start extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {	
		Parent parent = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		Scene scene = new Scene(parent);
		Font.loadFont(getClass().getResourceAsStream("twayair.ttf"), 15);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		Image image = new Image("/img/æ∆¿Ãƒ‹.png");
		stage.getIcons().add(image);
		stage.setResizable(false);
		stage.setTitle("EZEN Tennis Club");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
