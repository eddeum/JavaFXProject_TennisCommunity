package controller.booking;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.BookingDao;
import dao.SalesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Bookingview implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtbnum.setText("예약번호 : " + Bookinglist.selectedbooking.get예약번호());
		txtbdate.setText("예약날짜 : " + Bookinglist.selectedbooking.get예약날짜());
		txtbprice.setText("예약금액 : " + Bookinglist.selectedbooking.get예약금액());
		String[] booking = Bookinglist.selectedbooking.get예약시간().split(","); // 선택한 예약 내역 불러와서 문자열자르기 
		// 예약내역은 1-1,2-1,3-1,2-5,3-4 이런형태로 저장되어있음
		Arrays.sort(booking);
		// 정렬 (클릭했던 순서대로 저장되기때문에 뒤죽박죽임 그래서 정렬시킴)
		int i = 0 ;
		String text = "";
		for(String temp : booking) { // 잘라온 문자열 만큼 반복문 X-Y -> X 코트번호 Y 시간   반복해서 text에 저장해놓고 text를 label에 set시킴 
			if(i==(booking.length)) {
				break;
			}
			String[] 예약내역 = temp.split("-");
			if(예약내역[0].equals("1")) {
				if(예약내역[1].equals("1")) {
					text += "A코트 - 08:00~10:00\n";
				}
				else if(예약내역[1].equals("2")) {
					text += "A코트 - 10:00~12:00\n";
				}
				else if(예약내역[1].equals("3")) {
					text += "A코트 - 12:00~14:00\n";
				}
				else if(예약내역[1].equals("4")) {
					text += "A코트 - 14:00~16:00\n";
				}
				else {
					text += "A코트 - 16:00~18:00\n";
				}
			}
			else if(예약내역[0].equals("2")) {
				if(예약내역[1].equals("1")) {
					text += "B코트 - 08:00~10:00\n";
				}
				else if(예약내역[1].equals("2")) {
					text += "B코트 - 10:00~12:00\n";
				}
				else if(예약내역[1].equals("3")) {
					text += "B코트 - 12:00~14:00\n";
				}
				else if(예약내역[1].equals("4")) {
					text += "B코트 - 14:00~16:00\n";
				}
				else {
					text += "B코트 - 16:00~18:00\n";
				}
			}
			else if(예약내역[0].equals("3")) {
				if(예약내역[1].equals("1")) {
					text += "C코트 - 08:00~10:00\n";
				}
				else if(예약내역[1].equals("2")) {
					text += "C코트 - 10:00~12:00\n";
				}
				else if(예약내역[1].equals("3")) {
					text += "C코트 - 12:00~14:00\n";
				}
				else if(예약내역[1].equals("4")) {
					text += "C코트 - 14:00~16:00\n";
				}
				else {
					text += "C코트 - 16:00~18:00\n";
				}
			}
			else if(예약내역[0].equals("4")) {
				if(예약내역[1].equals("1")) {
					text += "D코트 - 08:00~10:00\n";
				}
				else if(예약내역[1].equals("2")) {
					text += "D코트 - 10:00~12:00\n";
				}
				else if(예약내역[1].equals("3")) {
					text += "D코트 - 12:00~14:00\n";
				}
				else if(예약내역[1].equals("4")) {
					text += "D코트 - 14:00~16:00\n";
				}
				else {
					text += "D코트 - 16:00~18:00\n";
				}
			}
			else {
				if(예약내역[1].equals("1")) {
					text += "E코트 - 08:00~10:00\n";
				}
				else if(예약내역[1].equals("2")) {
					text += "E코트 - 10:00~12:00\n";
				}
				else if(예약내역[1].equals("3")) {
					text += "E코트 - 12:00~14:00\n";
				}
				else if(예약내역[1].equals("4")) {
					text += "E코트 - 14:00~16:00\n";
				}
				else {
					text += "E코트 - 16:00~18:00\n";
				}
			}
			i++;
		}
		txtbtime.setText(text);  // 잘라온 문자열 만큼 반복문 X-Y -> X 코트번호 Y 시간   반복해서 text에 저장해놓고 text를 label에 set시킴 
	}
	
    @FXML
    private Label txtbnum;

    @FXML
    private Label txtbdate;

    @FXML
    private Label txtbtime;

    @FXML
    private Label txtbprice;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnback;

    @FXML
    private Button btnchange;

    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/booking/bookinglist.fxml");
    }

    @FXML
    void cancel(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("정말 예약을 취소하시겠습니까?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		SalesDao.salesDao.deletesales(Bookinglist.selectedbooking.get예약번호()); // 예약취소시 매출에서도 삭제
    		BookingDao.bookingDao.deletebooking(Bookinglist.selectedbooking.get예약번호()); // 예약취소시 예약내역 삭제
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    	}
    	
    }

    @FXML
    void change(ActionEvent event) {
    	Bookingctr.pagecheck = 1 ; // 예약 변경으로 진입하기위한 스위치
    	Home.instance.loadpage("/view/booking/booking.fxml"); // 예약 페이지로
    }

}
