package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Loginpane implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	private TextField txtid;
	
	@FXML
	private Button btnlogin;
	
	@FXML
	private Button btnsignup;
	
	@FXML
	private Button btnfindid;

	@FXML
	private Button btnfindpassword;
	
	@FXML
	private PasswordField txtpassword;
	
	@FXML
	private Label lblconfirm;
	
	@FXML
	void findid(ActionEvent event) {
		Login.instance.loadpage("/view/login/findidpane.fxml");
	}
	
	@FXML
	void findpassword(ActionEvent event) {
		Login.instance.loadpage("/view/login/findpasswordpane.fxml");
	}
	
	@FXML
	void login(ActionEvent event) {
		String id = txtid.getText();
		String password = txtpassword.getText();
		boolean result = MemberDao.memberDao.login(id, password);
		if(result) {
			Login.member = MemberDao.memberDao.getmember(id);
			MemberDao.memberDao.pointlog2(id);
			Main.instance.loadpage("/view/home/home.fxml");
			
			lblconfirm.setText("로그인성공");
		}else {
			lblconfirm.setText("동일한 회원정보가 없습니다.");
		} // else end
	
	} // login end
	
	@FXML
	void signup(ActionEvent event) {
		Login.instance.loadpage("/view/login/signuppane.fxml");
	}

    @FXML
    void enterlogin(ActionEvent event) {
    	login(event);
    }
}
