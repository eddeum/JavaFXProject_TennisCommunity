package controller.info;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.Main;
import controller.home.Home;
import controller.login.Login;
import dao.PointDao;
import dao.SalesDao;
import dto.Member;
import dto.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Pointadd implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblpoint.setText(String.valueOf(Login.member.get회원포인트())+"포인트");
		lblallpoint.setText(String.valueOf(Login.member.get회원포인트())+"포인트");
		lbladdpointconfirm.setText("");
	
	}
	
	  @FXML
	    private Label lblpoint;

	    @FXML
	    private TextField txtpoint;

	    @FXML
	    private Label lbladdpointconfirm;

	    @FXML
	    private Button btnaddpoint;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Label lblallpoint;

	    @FXML
	    void addpoint(ActionEvent event) {
	    	int mnum = Login.member.get회원번호();
	    	int inputpoint = Integer.parseInt(txtpoint.getText());
	    	int chargepoint = inputpoint+(inputpoint/10);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String pdate = sdf.format(new Date());
	    	
	    	Point point = new Point(mnum, chargepoint, pdate);
	    	
	    	PointDao.pointDao.pointadd(point);
	    	int pnum = SalesDao.salesDao.callpnum(mnum, pdate);
	    	SalesDao.salesDao.pointaddsales(pdate, inputpoint, pnum);
	  
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setHeaderText("포인트 충전 완료!");
	    		alert.showAndWait();	
	    			lbladdpointconfirm.setText(txtpoint.getText()+"원이  충전되었습니다.");
	    			lblallpoint.setText(String.valueOf(Login.member.get회원포인트()+chargepoint)+"포인트");
	    			Login.member.set회원포인트(Login.member.get회원포인트()+chargepoint);

	    	

	    }

	    @FXML
	    void back(ActionEvent event) { // 뒤로가기버튼클릭시 내정보화면으로
	    	Home.instance.loadpage("/view/info/info.fxml");
	    	
	    }

	
}
