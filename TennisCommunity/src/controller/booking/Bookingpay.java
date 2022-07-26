package controller.booking;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BookingDao;
import dao.MemberDao;
import dao.PointDao;
import dao.SalesDao;
import dto.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Bookingpay implements Initializable {
	
	String[] fee = Bookingctr.bookingtime.split(","); // 예약날짜 잘라두기 1-1,1-2,1-3, 이런식으로 저장되니까 , 잘라서 배열길이 구하기 위해 저장
														// 배열길이 * 10000 이 이용요금
	DecimalFormat df = new DecimalFormat("#,##0"); // 포인트랑 금액 쉼표 설정
	int 이용요금 ; // 
	int 예약변경이용요금 ;
	int 결제해야할금액 ;
	int 입력한금액 ;
	int 사용한포인트 ;
	
	public void 금액들초기화() {
		이용요금 = 0 ;
		예약변경이용요금 = 0 ;
		결제해야할금액  = 0;
		입력한금액 = 0 ;
		사용한포인트 = 0 ;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		이용요금 = 10000*fee.length; // 배열길이 * 10000 이용요금
		결제해야할금액 = 이용요금; // 결제해야할 금액 = 이용요금
		txtpoint.setText("0");
		if(Bookingctr.pagecheck==2) { // 예약변경일때
			예약변경이용요금 = 이용요금-Bookinglist.selectedbooking.get예약금액(); // 이용요금에서 기존 예약금액 뺀만큼
			결제해야할금액 = 예약변경이용요금 ; // 결제해야할금액 을 또 만드는 이유는 포인트 사용때문에
			lblpay.setText("예약변경");
			if(예약변경이용요금<=0) { // 기존예약금액이 변경예약금액보다 클때
				if(예약변경이용요금==0) {
					lblfee.setText("결제금액 없음");
				}
				else {
					lblfee.setText("기존 예약 결제금액보다 이용요금이 작아 차액은 포인트로 환급됩니다.");
				}
				lblamount.setText("결제금액 없음");
				txtcash.setText("0");
				txtcash.setDisable(true);
				txtpoint.setDisable(true);
				btnpay.setText("예약변경하기");	
			}
			else {  // 기존예약금액보다 변경예약이 금액이 더 클때
				lblfee.setText("이용요금 : " + df.format(예약변경이용요금)+" 원");
				lblamount.setText("결제금액 : " + df.format(결제해야할금액)+" 원");
				txtcash.setText("0");
			}	
			lblpoint.setText("현재 포인트 : " + df.format(Login.member.get회원포인트()) + " 점");	
			Bookingctr.pagecheck=0; // 예약변경 진입 스위치 초기화
		}
		else {	// 일반 예약일때 (변경이 아닐때)	
			lblfee.setText("이용요금 : " + df.format(이용요금)+" 원");
			lblpoint.setText("현재 포인트 : " + df.format(Login.member.get회원포인트()) + " 점");
			lblamount.setText("결제금액 : " + df.format(결제해야할금액)+" 원");
			txtcash.setText("0");
		}
	}
	
    @FXML
    private Label lblfee;

    @FXML
    private Label lblpoint;
    
    @FXML
    private Label lblpay;

    @FXML
    private TextField txtpoint;

    @FXML
    private Button btnpoint;

    @FXML
    private Label lblamount;

    @FXML
    private Label lblconfirm;
    
    @FXML
    private TextField txtcash;

    @FXML
    private Button btnpay;
    
    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	if(lblpay.getText().equals("예약변경")) {
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    	}
    	Home.instance.loadpage("/view/booking/booking.fxml");
    }
    
    
    @FXML
    void pay(ActionEvent event) {
    	try { // try 넣은이유 -> 숫자외 다른거 입력받으면 오류남
    		입력한금액 = Integer.parseInt(txtcash.getText());
    		if(lblfee.getText().equals("기존 예약 결제금액보다 이용요금이 작아 차액은 포인트로 환급됩니다.")) { // 포인트 환급일경우
    			SalesDao.salesDao.changesales(Bookinglist.selectedbooking.get예약번호(), 결제해야할금액); // 결제해야할금액이 -이므로 DB에 그만큼 차감시킴
    			BookingDao.bookingDao.changebooking(Bookinglist.selectedbooking.get예약번호(), Bookingctr.bookingday, Bookingctr.bookingtime, 결제해야할금액); // 예약변경
    			Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setHeaderText("예약변경 완료. 차액은 포인트로 환급되었습니다.");
		    	alert.showAndWait();
		    	Bookingctr.bookingday = null ; // 예약과정에서 클릭해여 저장되었던 값들 초기화
		    	Bookingctr.bookingtime = null ; // 예약과정에서 클릭해여 저장되었던 값들 초기화
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	Point point = new Point(Login.member.get회원번호(), (-결제해야할금액), sdf.format(new Date())); // 포인트 환급을 위한 dto객체 생성
		    	PointDao.pointDao.pointadd(point);
		    	Login.member = MemberDao.memberDao.getmember(Login.member.get회원아이디()); // 포인트 증가
		    	
		    	금액들초기화(); // 이용요금, 결제금액 등등.... 초기화시킴
		    	Home.instance.loadpage("/view/booking/booking.fxml");	
    			
    		}
    		else {
	    		if(lblpay.getText().equals("예약변경")){ // 예약 변경일때
		    		if( 결제해야할금액 <= 입력한금액 ){
		    			SalesDao.salesDao.changesales(Bookinglist.selectedbooking.get예약번호(), 결제해야할금액);
		    			BookingDao.bookingDao.changebooking(Bookinglist.selectedbooking.get예약번호(), Bookingctr.bookingday, Bookingctr.bookingtime, 결제해야할금액);
		    			Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setHeaderText("예약변경 완료. 예약해주셔서 감사합니다.");
				    	alert.showAndWait();
				    	Bookingctr.bookingday = null ;
				    	Bookingctr.bookingtime = null ;
				    	if(사용한포인트>0) {
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    	Point point = new Point(Login.member.get회원번호(), (-사용한포인트), sdf.format(new Date())); // 포인트 환급을 위한 dto객체 생성
					    	PointDao.pointDao.pointadd(point);
				    	}
				    	Home.instance.loadpage("/view/booking/booking.fxml");	
				    	금액들초기화();
		    			
		    		}
		    		else { // 금액이 부족하면 
			    		Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setHeaderText("금액이 부족합니다.");
			    		alert.showAndWait();
		    		}
		    		
		    	}
		    	else { // 일반예약 (예약변경 아닐때)
			    	if(결제해야할금액 <= 입력한금액) {
				    	BookingDao.bookingDao.booking(Bookingctr.bookingday,Bookingctr.bookingtime, 결제해야할금액);
				    	int bookingnum = SalesDao.salesDao.callbnum(Bookingctr.bookingday, Bookingctr.bookingtime); 	
				    	SalesDao.salesDao.salesbooking(Bookingctr.bookingday, 결제해야할금액, bookingnum);
				    	Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setHeaderText("결제 완료. 예약해주셔서 감사합니다.");
				    	alert.showAndWait();
				    	Bookingctr.bookingday = null ;
				    	Bookingctr.bookingtime = null ;
				    	if(사용한포인트>0) {
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    	Point point = new Point(Login.member.get회원번호(), (-사용한포인트), sdf.format(new Date())); // 포인트 환급을 위한 dto객체 생성
					    	PointDao.pointDao.pointadd(point);
				    	}
				    	금액들초기화();
				    	Home.instance.loadpage("/view/booking/booking.fxml");	    
			    	}
			    	else {
			    		Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setHeaderText("금액이 부족합니다.");
			    		alert.showAndWait();
			    	}
		    	}
    		}
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("금액을 제대로 입력해주세요");
    		alert.showAndWait();
    	}
    }

    @FXML
    void point(ActionEvent event) {	
		try {
	    	if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText()) > Login.member.get회원포인트() ) {
	    		lblconfirm.setText("포인트를 잘못 입력하셨습니다.");		
	    	}
	    	else if(Integer.parseInt(txtpoint.getText())%100 != 0){
	    		lblconfirm.setText("포인트는 100점 단위로만 사용가능합니다.");	   		
	    	}
	    	else {
	    		사용한포인트 = Integer.parseInt(txtpoint.getText());
	    		if(lblpay.getText().equals("예약변경")) {
	    			if((예약변경이용요금-사용한포인트)<0 ) { // 고쳐
			    		결제해야할금액 = 0;
			    		사용한포인트=예약변경이용요금; 		
			    		lblconfirm.setText(df.format(사용한포인트)+ "포인트 사용합니다.");
			    		txtpoint.setText(사용한포인트+"");
			    		lblamount.setText("결제금액없음");
	    			}
		    		else {
			    		결제해야할금액 = 예약변경이용요금-사용한포인트;
			    		lblconfirm.setText(df.format(사용한포인트)+ "포인트 사용합니다.");
			    		lblamount.setText("결제금액 : " + df.format(결제해야할금액)+" 원");
		    		}
	    		}
	    		else{
	    			if((이용요금-사용한포인트)<0) { // 고쳐
			    		결제해야할금액 = 0;
			    		사용한포인트=이용요금;
			    		lblconfirm.setText(df.format(사용한포인트) + "포인트 사용합니다.");
			    		txtpoint.setText(사용한포인트+"");
			    		lblamount.setText("결제금액 : " + df.format(결제해야할금액)+" 원");
	    			}
	    			else {
			    		결제해야할금액 = 이용요금-사용한포인트;
			    		lblconfirm.setText(df.format(사용한포인트) + "포인트 사용합니다.");
			    		lblamount.setText("결제금액 : " + df.format(결제해야할금액)+" 원");
	    			}
	    		}
	    	}
		}
		catch(Exception e) {
			lblconfirm.setText("포인트를 잘못 입력하셨습니다.");	
		} 		
    }
}
