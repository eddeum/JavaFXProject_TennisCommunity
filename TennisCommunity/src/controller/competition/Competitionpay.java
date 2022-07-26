package controller.competition;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.MemberDao;
import dao.RecordDao;
import dto.Member;
import dto.Record;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Competitionpay implements Initializable{

	public static Record selectnum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
		///현재 포인트 안내
		lblfee.setText("참가비: "+Competition.selectcnum.get참가비()+"원");
		lblpoint.setText("현재포인트: " + Login.member.get회원포인트()+"원");
		txtpoint.setText("0");
		
		lblerror.setVisible(false);
		lblamount.setText("결제금액: " + Competition.selectcnum.get참가비() +" 원");
		
	}
	  @FXML
	    private Label lblfee;

	    @FXML
	    private Label lblpoint;

	    @FXML
	    private TextField txtpoint;

	    @FXML
	    private Button btnpoint;

	    @FXML
	    private Label lblamount;

	    @FXML
	    private Label lblerror;
	    
	    @FXML
	    private TextField txtcash;

	    @FXML
	    private Button btnpay;

	    @FXML
	    void pay(ActionEvent event) {
	    	
	    	////view의 객체 가져오기
	    	int member = Login.member.get회원번호();
	    	Tournament tournament  = Competition.selectcnum;
	    	
	    	///결제금액 
	    	String [] 결제금액 = lblamount.getText().split(" ");
	    	int 결제액 = Integer.parseInt(결제금액[1]);
	    	System.out.println(결제액);
	    	////
	    	if( (Competition.selectcnum.get참가비()-Integer.parseInt(txtpoint.getText()))>Integer.parseInt(txtcash.getText())) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("결제금액이 부족합니다.");
	    		alert.showAndWait();
	    	}
	    	else {
	    
	    	int 회원번호 =Login.member.get회원번호();
	    	int 포인트갱신 = (Login.member.get회원포인트()-Integer.parseInt(txtpoint.getText()));
	    	////사용한 포인트만큼 멤버포인트에서 마이너스 
	    	///db처리
	    	MemberDao.memberDao.cminuspoint(회원번호,포인트갱신);
	    		
	    	//객체화
	    	Record record = new Record(0,MemberDao.memberDao.getid(회원번호), tournament.get대회번호(),회원번호, 0, 0);
	    		///db처리
	    	boolean result = RecordDao.recordDao.apply(record);
	    	
	    	if(result) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("대회참가신청 완료");
	    		alert.showAndWait();
	    		
	    		Home.instance.loadpage("/view/competition/competition.fxml");
	    		
	    		
	    		
	    		}
	    	}
	    }

	    @FXML
	    void point(ActionEvent event) {
	    	lblerror.setVisible(true);
	    	
	    	try {
		    	if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText()) > Login.member.get회원포인트() ) {
		    		lblerror.setText("포인트를 잘못 입력하셨습니다.");
		    		txtpoint.setText("0");
		    		lblamount.setText("결제금액: " +( Competition.selectcnum.get참가비()-Integer.parseInt(txtpoint.getText()))+" 원");
		    	
		    	}
		    	else if(Integer.parseInt(txtpoint.getText())%100 != 0){
		    		lblerror.setText("포인트는 100점 단위로만 사용가능합니다.");	   	
		    		txtpoint.setText("0");
		    		lblamount.setText("결제금액: " +( Competition.selectcnum.get참가비()-Integer.parseInt(txtpoint.getText()))+" 원");
		    	}
		    	else if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText())>Competition.selectcnum.get참가비()) {
		    		lblerror.setText("결제금액을 초과하셨습니다.");
		    		txtpoint.setText("0");
		    		lblamount.setText("결제금액: " +( Competition.selectcnum.get참가비()-Integer.parseInt(txtpoint.getText()))+" 원");
		    		
		    	}
		    	else {
		    		lblerror.setText(txtpoint.getText() + "포인트 사용합니다.");
		    		lblamount.setText("결제금액: " +( Competition.selectcnum.get참가비()-Integer.parseInt(txtpoint.getText()))+" 원");
		    		
		    	}
    		}
    		catch(Exception e) {
    			lblerror.setText("포인트를 잘못 입력하셨습니다.");	
    		} 		
	    }
	
	
}
