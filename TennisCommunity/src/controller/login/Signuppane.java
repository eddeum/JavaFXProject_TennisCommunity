package controller.login;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.booking.Bookingctr;
import controller.booking.Bookinglist;
import dao.MemberDao;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable {
	
	// 입력오류체크, 발생시 예외처리 해야함
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
    @FXML
    private TextField txtid;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnback;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Label lblconfirm;

    @FXML
    private PasswordField txtpasswordconfirm;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtphone;

    @FXML
    private TextField txtid1;
    
    @FXML
    private TextField txtyear;
    
    @FXML
    private TextField txtmonth;
    
    @FXML
    private TextField txtday;

    @FXML
    private Button btnidconfirm;
    


    @FXML
    void back(ActionEvent event) {
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }

    @FXML
    void idconfirm(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String id = txtid.getText();    	
    	if(txtid.getText().equals("")) {
    		alert.setHeaderText("아이디를 입력하세요");
    		alert.showAndWait();
    		return;
    	}
    	else {
        	boolean result = MemberDao.memberDao.idcheck(id);
	    	if(result) {
	    		alert.setHeaderText("사용중인 아이디 입니다. 다시입력해주세요");
	    		alert.showAndWait();
	    		txtid.setText("");
	    		return;
	    	}
	    	else {
	    		alert.setHeaderText("사용가능한 아이디입니다");
	    		alert.showAndWait();
	    		return;
	    	}
    	}
    }
    

    @FXML
    void signup(ActionEvent event) {
    	try {
	    	String id = txtid.getText();
	    	String password = txtpassword.getText();
	    	String passwordconfirm = txtpasswordconfirm.getText();
	    	String name = txtid1.getText();
	    	String email = txtemail.getText();
	    	String year = txtyear.getText();
	    	String month = txtmonth.getText();
	    	String day = txtday.getText();
	    	String phone = txtphone.getText();
	    //	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    //	String since = sdf.format(new Date() );
	    	Calendar calendar = Calendar.getInstance();
	    	int thisyear = calendar.get(calendar.YEAR);
	    	calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
	    	int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    	if(month.length()==1) {
	    		month = "0"+month;
	    	}
	    	if(day.length()==1) {
	    		day = "0"+day;
	    	}
	    	
	    	
	    	// 아이디 중복체크
	    	boolean result = MemberDao.memberDao.idcheck(id);
	    	if(result) {
	    		lblconfirm.setText("사용중인 아이디입니다.");
	    		return;
	    	}
	    	
	    	// 비밀번호 길이 체크
	    	if(password.length()<6 || password.length()>10 || passwordconfirm.length()<6 || passwordconfirm.length()>10) {
	    		lblconfirm.setText("패스워드는 6~10자리 사이로 입력해주세요");
	    		return;
	    	}
	    	
	    	// 비밀번호 일치 체크
	    	if(!password.equals(passwordconfirm)) {
	    		lblconfirm.setText("비밀번호가 일치하지 않습니다.");
	    		return;
	    	}
	    	
	    	// 이메일 형식체크
	    	boolean email_check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
	    	if(email_check==false) { // 이메일입력에 @가 없으면
	    		lblconfirm.setText("잘못된 형식의 이메일주소입니다");
	    		return;
	    	}
	    	// 전화번호 형식체크
	    	boolean phone_check = Pattern.matches("01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
	    	if(phone_check==false) {
	    		lblconfirm.setText("잘못된 형식의 전화번호입니다");
	    		return;
	    	}
	    	// 생년월일 형식체크
	    	if(1900>Integer.parseInt(year) || (Integer.parseInt(year) > thisyear)) {
	    		lblconfirm.setText("생년월일-'연도'를 제대로 입력해주세요");
	    		return;
	    	}
	    	
	    	if(Integer.parseInt(month) <=0 || Integer.parseInt(month) >= 13 ) {
	    		lblconfirm.setText("생년월일-'월'을 제대로 입력해주세요");
	    		return;
	    	}
	    	
	    	if(Integer.parseInt(day) <=0 || Integer.parseInt(day) >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
	    		lblconfirm.setText("생년월일-'일'을 제대로 입력해주세요");
	    		return;
	    	}
	    	
	    	//* 모든 유효성 통과 -> DB저장
	    	// 1. 객체화
	    	Member member = new Member(0, id, password, name, year+"-"+month+"-"+day, email, phone, 0, "");
	    	boolean result2 = MemberDao.memberDao.signup(member);
	    	if(result2) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("알림");
	    			alert.setHeaderText("테니스 예약시스템에 가입을 축하합니다.");
	    			alert.setContentText("회원가입 성공");
	    		alert.showAndWait();
	        	Login.instance.loadpage("/view/login/loginpane.fxml");
	    	}else {
	    		lblconfirm.setText("[회원가입실패]DB 오류(관리자에게 문의)");
	    		return;
	    	} // else e
    	}
    	catch(Exception e) {
    		lblconfirm.setText("잘못입력하셨습니다.");
    	}
    }

}
