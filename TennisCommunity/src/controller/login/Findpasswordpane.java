package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Findpasswordpane implements Initializable {

	// 입력오류체크, 발생시 예외처리 해야함
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
    @FXML
    private Button btnfindid;

    @FXML
    private Button btnback;

    @FXML
    private Label lblconfirm;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtphone;

    @FXML
    void back(ActionEvent event) {
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }
    
    @FXML
    void findpassword(ActionEvent event) {
    	String name = txtname.getText();
    	String id = txtid.getText();
    	String phone = txtphone.getText();
    	String password = MemberDao.memberDao.findpassword(name, id, phone);
    	System.out.println(password);
    	if(password != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("회원님의 비밀번호는 "+"\""+password+"\""+"입니다");
    		alert.showAndWait();
    	}else {
    		lblconfirm.setText("정보를 잘못입력하셨습니다.");
    	}


    }
}
