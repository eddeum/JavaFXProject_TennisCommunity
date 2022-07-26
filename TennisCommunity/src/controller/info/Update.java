package controller.info;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.home.Home;
import controller.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

public class Update implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtemail.setText(Login.member.get회원이메일());
		txtmname.setText(Login.member.get회원이름());
		txtphone.setText(Login.member.get회원전화번호());
		txtpwupdate.setText(Login.member.get회원비밀번호());
		txtemail.setDisable(true);
		txtmname.setDisable(true);
		txtphone.setDisable(true);
		txtpwupdate.setDisable(true);
	}

    @FXML
    void clear1(MouseEvent event) {txtmname.setText("");}

    @FXML
    void clear2(MouseEvent event) {txtemail.setText("");}

    @FXML
    void clear3(MouseEvent event) {txtphone.setText("");}

    @FXML
    void clear4(MouseEvent event) {txtpwupdate.setText("");}

	   @FXML
	    private TextField txtemail;

	    @FXML
	    private TextField txtmname;

	    @FXML
	    private Button btnupdate;

	    @FXML
	    private Label lblpwerror;

	    @FXML
	    private Button btnmpwconfirm;

	    @FXML
	    private Button btnback;

	    @FXML
	    private TextField txtphone;

	    @FXML
	    private PasswordField txtmpassword;

	    @FXML
	    private PasswordField txtpwupdate;
	    


    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/info/info.fxml");
    }

    @FXML
    void pwconfirm(ActionEvent event) {
    	if(txtmpassword.getText().equals(Login.member.get회원비밀번호())) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("수정할 정보를 입력해주시기 바랍니다");
    		alert.showAndWait();
    		txtmpassword.setText("");
    		txtmpassword.setDisable(true);
    		txtemail.setDisable(false);
    		txtmname.setDisable(false);
    		txtphone.setDisable(false);
    		txtpwupdate.setDisable(false);
    		
    	}else { // 현재 로그인된 회원비밀번호와 입력비밀번호가 다르면
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("비밀번호 확인해주시기 바랍니다.");
    		alert.showAndWait();
    		txtmpassword.setText("");
    
    	} // else e

    }

    @FXML
    void update(ActionEvent event) {
    	int mnum = Login.member.get회원번호();
    	String name = txtmname.getText();
    	String email = txtemail.getText();
    	String phone = txtphone.getText();
    	String password = txtpwupdate.getText();
    	
    	boolean result = MemberDao.memberDao.memberupdate(mnum, password, name, email, phone);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("회원정보가 수정되었습니다.[다시 로그인해주세요]");
    		alert.showAndWait();
    		Login.member = null;
    		Main.instance.loadpage("/view/login/login.fxml");
    		
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("회원정보 수정 실패[관리자에게 문의");
    		alert.showAndWait();
    		txtmname.setText("");
    		txtemail.setText("");
    		txtphone.setText("");
    		txtpwupdate.setText("");
    	} // else e

    }
	
}
