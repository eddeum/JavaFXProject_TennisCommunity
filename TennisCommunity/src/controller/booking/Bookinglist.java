package controller.booking;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BookingDao;
import dto.Booking;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Bookinglist implements Initializable{
	
	public static Booking selectedbooking ;
	ArrayList<Booking> list ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = BookingDao.bookingDao.bookinglist(Login.member.get회원번호()); // 로그인한 회원의 예약내역 가져와서 저장
		show();
	}
	
	@FXML
	private Label lbltest;
	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;
	
    
	void show() {
		GridPane gridpane = new GridPane();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE) ; // 현재 날짜 저장해놓음 (현재 날짜 이전 예약내역은 안보여주려고)
		int month = calendar.get(Calendar.MONTH)+1 ; // 현재 날짜 저장해놓음 (현재 날짜 이전 예약내역은 안보여주려고)
		int year = calendar.get(Calendar.YEAR) ; // 현재 날짜 저장해놓음 (현재 날짜 이전 예약내역은 안보여주려고)
		int i = 0 ;
		for(Booking temp : list) { // 예약내역만큼 반복문
			String[] checkday = temp.get예약날짜().split("-"); // 날짜 자르기 2022-04-20 -> 2022    04    20		
			if(Integer.parseInt(checkday[0]) == year){ // 예약날짜와 현재 시점이 같으면 (연)
				if(Integer.parseInt(checkday[1]) == month) { // 예약날짜와 현재 시점이 같으면 (월)
					if(Integer.parseInt(checkday[2])>=day) { // 예약날짜가 현재 시점 이후면 (일)
						Button button = new Button();
						button.setPrefWidth(470);					
						button.setPrefHeight(70);
						button.setId("test");
						button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
						button.setText("예약번호 : " + temp.get예약번호()+"\n예약날짜 : "+temp.get예약날짜()+"\n예약금액 : "+temp.get예약금액()+" 원");
						button.setOnAction(e -> {
							selectedbooking = temp ;
							Home.instance.loadpage("/view/booking/bookingview.fxml");
						});
						button.setFont(Font.font(15));
						gridpane.add(button, 0, i);
						i++;
					}
					else {} // 예약날짜와 연 월 같은데 날짜가 이전이면 내역 안만듬
				}
				else if (Integer.parseInt(checkday[1]) > month){ // 예약날짜가 지금 시점 이후면 (월)
					Button button = new Button();
					button.setPrefWidth(470);
					button.setPrefHeight(70);
					button.setId("test");
					button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
					button.setText("예약번호 : " + temp.get예약번호()+"\n예약날짜 : "+temp.get예약날짜()+"\n예약금액 : "+temp.get예약금액()+" 원");
					button.setOnAction(e -> {
						selectedbooking = temp ;
						Home.instance.loadpage("/view/booking/bookingview.fxml");
					});
					button.setFont(Font.font(15));
					gridpane.add(button, 0, i);
					i++;		
				}
				else {} // 예약날짜가 지금 시점 이전이면 (월) 버튼 안만듬
			}
			else if(Integer.parseInt(checkday[0]) > year){ // 같은원리... ㅠㅠ
				Button button = new Button();
				button.setPrefWidth(470);
				button.setPrefHeight(70);
				button.setId("test");
				button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
				button.setText("예약번호 : " + temp.get예약번호()+"\n예약날짜 : "+temp.get예약날짜()+"\n예약금액 : "+temp.get예약금액()+" 원");
				button.setOnAction(e -> {
					selectedbooking = temp ;
					Home.instance.loadpage("/view/booking/bookingview.fxml");
				});
				button.setFont(Font.font(15));
				gridpane.add(button, 0, i);
				i++;
				
			}
			
			else {}
		}
		vbox.getChildren().add(gridpane);	
	}
}
