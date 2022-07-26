package controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Login implements Initializable {
	
	public static Login instance;
	
	public static Member member;
	
	public Login() {
		instance = this;
	}
	
	public static Login getinstance() {
		return instance;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadpage("/view/login/loginpane.fxml");
	}
	
    @FXML
    private BorderPane borderpane;
    
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		}
		catch (IOException e) {
			System.out.println("[Login/loadpage]" + e);
		}
	}

}
