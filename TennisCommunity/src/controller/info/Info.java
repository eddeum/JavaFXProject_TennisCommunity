package controller.info;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Info implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblid.setText(Login.member.get회원아이디());
		lblemail.setText(Login.member.get회원이메일());
		lblname.setText(Login.member.get회원이름());
		lblphone.setText(Login.member.get회원전화번호());
		lblpoint.setText(String.valueOf(Login.member.get회원포인트())+"점");
	}
	
		@FXML
	    private Label lblid;

	    @FXML
	    private Label lblemail;

	    @FXML
	    private Label lblname; // 회원이름

	    @FXML
	    private Label lblpoint;

	    @FXML
	    private Label lblphone;// 회원전화번호

	    @FXML
	    private Button btnpoint;

	    @FXML
	    private Button btncview;

	    @FXML
	    private Button btnmupdate;

	    @FXML
	    private Button btnmdelete;


	    @FXML
	    void cview(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/competitionlist.fxml");
	    }

	    @FXML
	    void delete(ActionEvent event) { // 회원탈퇴 버튼 클릭
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setHeaderText("회원 탈퇴를 진행하시겠습니까?");
	    	Optional<ButtonType> optional = alert.showAndWait();
	    	if(optional.get()==ButtonType.OK) { // 확인버튼 클릭
	    		boolean result = MemberDao.memberDao.memberdelete(Login.member.get회원번호());
	    		if(result) { // 탈퇴성공이면
	    			Login.member = null;
	    			Main.instance.loadpage("/view/login/login.fxml");
	    		}else { // 
	    			alert.setHeaderText("탈퇴실패");
	    		}
	    	}
	    }

	    @FXML
	    void mupdate(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/update.fxml");
	    }

	    @FXML
	    void point(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/pointadd.fxml");
	    }
	    

}
