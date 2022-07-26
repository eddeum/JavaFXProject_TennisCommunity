package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Main implements Initializable {
	
	public static Main instance;
	
	public Main() {
		instance = this;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadpage("/view/login/login.fxml");
		
	}
	
	@FXML
    private BorderPane borderpane;


	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		}
		catch (IOException e) {
			System.out.println("[Main/loadpage]" + e);
		}
	}
}
