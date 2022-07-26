package controller.booking;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import controller.home.Home;
import dao.BookingDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Bookingctr implements Initializable {

	Calendar calendar = Calendar.getInstance();
	int checkyear = calendar.get(Calendar.YEAR); // 현재 날짜 기록 (연)
	int checkmonth = calendar.get(Calendar.MONTH)+1; // 현재 날짜 기록 (월)
	int checkday = calendar.get(Calendar.DATE) ; // 현재 날짜 기록 (일)
	int year = calendar.get(Calendar.YEAR); // 유저가 버튼으로 월 변경했을때 기록되는 연도
	int month = calendar.get(Calendar.MONTH)+1; // 유저가 버튼으로 월 변경했을때 기록되는 월
	int day = calendar.get(Calendar.DATE) ; // 유저가 버튼으로 날짜 클릭했을때 저장되는 날
	public static String bookingday ; // 예약할 날짜
	public static String bookingtime ; // 예약 내용 (코트-시간)
	public static int pagecheck= 0 ; // 예약변경일때를 체크하기 위한 스위치
	
	/**
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ; // 페이지 시작시 null (날짜 선택했다가 다른메뉴(ex게시판) 갔다왔을때 값이 남아있는경우 대비
    	Bookingctr.bookingtime = null ;	// 페이지 시작시 null (날짜 선택했다가 다른메뉴(ex게시판) 갔다왔을때 값이 남아있는경우 대비
		lblmonth.setText(month+" 월");
		lblyear.setText(year+" 년");
		if(Bookinglist.selectedbooking != null) { // selectedbooking = 예약내역에서 변경하기 클릭하면 selectedbooking에 저장됨
			if(pagecheck==1) { // 예약변경시 버튼이름 및 화면에 표시되는 글자 변경
				btnpay.setText("변경하기");
				lblbooking.setText("예약변경");
				lblselected.setText("예약날짜 : " + Bookinglist.selectedbooking.get예약날짜());
				String[] date = Bookinglist.selectedbooking.get예약날짜().split("-"); // 예약날짜 가져와서 문자열자르기 "2022-04-20"
				year = Integer.parseInt(date[0]) ; // 숫자로 저장 
				month = Integer.parseInt(date[1]) ; // 숫자로 저장
				day = Integer.parseInt(date[2]) ;	// 숫자로 저장
				lblmonth.setText(month+"월");
				lblyear.setText(year +" 년");
				if(Integer.parseInt(date[0])>checkyear) { // 예약한 연도가 현재 시점보다 늦으면
					show(year,month,1); // 예약한시점 달력보여줌 (1을 넣은이유-> day기준으로 그 전에는 버튼이 비활성화됨 1일부터 버튼이 다 활성화되어야함)
				}
				else if(Integer.parseInt(date[0])==checkyear){  // 연도가 같으면
					if(checkmonth< month) { // 예약한 월이 현재 시점보다 늦으면
						show(year,month,1); // 1일부터 전부 버튼활성화시킴
					}
					else if(checkmonth==month){ // 예약한 월이 현재 시점이면
						show(year,month,checkday); // 오늘부터 버튼활성화시킴 checkday = 오늘
					}	
				}
				else {  // 예약한 연도가 현재시점보다 빠르면 (예약날짜가 이미 지나있어서 리스트에 안뜨기때문에 사실상 진입안되는 분기) 
					show(year,day,32); // 32으로 버튼 전부 비활성화
				}
				showtable(day); // 예약변경화면일때는 테이블을 띄워줌 (원래는 날짜선택해야 테이블 뜸)
				pagecheck=0; // 스위치 초기화
			}
			else { // 예약내역에서 변경버튼을 안누르고 다시 예약하기 화면을누르면 selectedbooking값은있지만 pagecheck값은 없음				
				Bookinglist.selectedbooking = null; // 그래서 다시 selectedbooking을 null로 변경
				show(year,month,checkday);  // 예약변경눌렀다가 다른메뉴 갔다왔으므로 일반 예약하기 화면이랑 동일
			}
		}
		else{ // 일반 예약 (selectedbooking 없을때)
			show(year,month,checkday);
		}
	}
	
	@FXML
	private GridPane gridpane2;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private AnchorPane anchorpane2;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private Label lblmonth;
    
    @FXML
    private Label lblyear;

    @FXML
    private Label lblbooking;
    
    @FXML
    private Label lblselected;
    
    @FXML
    private Button btnnext;

    @FXML
    private Button btnprevious;

    @FXML
    private Button btnpay;
    
    @FXML
    private Button btnback;
    
    
    @FXML
    void back(ActionEvent event) {	
    	if(Bookinglist.selectedbooking!=null) {
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    		Bookinglist.selectedbooking = null ;
    	}
    	else{
    		Home.instance.loadpage("/view/home/homemain.fxml");
    	}
    	
    }
	
    @FXML
    void pay(ActionEvent event) {
    	if(month<=9) { // 달력 날짜가 MM 형태가 아니라 M형태라서 DB저장전에 앞자리에 0추가
    		// 이렇게 하지 않으려면 달력에 01, 02, 03 으로 표시해야하는데... 달력이 안이뻐서...
    		bookingday = year+"-0"+month+"-"+day ;
    	}
    	else {
    		bookingday = year+"-"+month+"-"+day ;
    	}
    	if(day<=9) {
    		bookingday = year+"-0"+month+"-0"+day ;
    	}
    	else {
    		bookingday = year+"-0"+month+"-"+day ;
    	}
    		
    	if(lblbooking.getText().equals("예약변경")) { // 예약변경일경우
    		if (bookingtime==null){	 // 코트랑 시간을 선택하지 않았을경우
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("예약할 시간과 코트를 시간표에서 선택해주세요.");
	    		alert.showAndWait();
    		}
	    	else { 
	    		pagecheck=2; // 예약변경일경우 결제 페이지 전환을 위한 스위치
    			Home.instance.loadpage("/view/booking/bookingpay.fxml");
	    	}
    	}
    	else {
	    	if( lblselected.getText().equals("날짜를 선택해주세요")) { // 예약변경 아닐경우
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("날짜를 선택해주세요");
	    		alert.showAndWait();
	    	}	
	    	else if (bookingtime==null){	
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("예약할 시간과 코트를 시간표에서 선택해주세요.");
	    		alert.showAndWait();
	    	}
	    	else {
	    		Home.instance.loadpage("/view/booking/bookingpay.fxml");
	    	}
    	}
    }
    
    @FXML
    void next(ActionEvent event) { // 다음 달로 변경버튼
    	gridpane2.getChildren().clear(); // 코트, 시간 테이블 초기화
    	bookingtime = null; // 선택한 코트,시간 초기화
    	vbox.getChildren().remove(0);   // 달력 초기화 (달력 다시 생성하기 위해)  	
    	if(month+1 == 13) { year +=1 ; month = 1 ;} // 해가 넘어갈경우 year에 +1
    	else { month +=1 ;} // 해가 안넘어가면 월+1
    	lblmonth.setText(month+" 월");
    	lblyear.setText(year + " 년");
    	if(checkmonth > month){ // 현재시점보다 클릭한 후 월이 작으면 ex) 지금 4월인데 2월->3월 변경할경우
    		if(year > checkyear) { // 현재 시점 년도보다 뒤면 
    			show(year,month,1);  // 달력 1일부터 활성화
    			lblyear.setText(year + " 년");
    		}
    		else {
    			show(year,month,32); // 현재 시점년도보다 앞이면 ex) 지금 2022년 인데 2021년이면 달력 버튼 비활성화
    		}
    	}
    	else if (checkmonth == month){ show(year,month,checkday); } // 현재시점이면 현재 일부터 달력활성화
    	else  { // 현재시점보다 클릭한 월이 크면 
    		if(year < checkyear) { // 연도도 체크해야함 현재 4월인데 2021년 5월일경우 이거 안해놓으면 버튼 다 활성화됨
    			show(year,month,32); // 연도 늦었으니 달력 비활성화
    			lblyear.setText(year + " 년");
    			}
    		else{
    			show(year,month,1); // 연도가 안늦었으면 달력 1일부터 활성화
    		}
    	}
    }

    @FXML
    void previous(ActionEvent event) { // 이전버튼 (next랑 원리 같음)
    	gridpane2.getChildren().clear();
    	bookingtime = null;
    	vbox.getChildren().remove(0);
    	if( month-1 == 0) { year -= 1 ; month = 12 ; }
    	else{ month -=1 ;}
    	lblmonth.setText(month+" 월");
    	lblyear.setText(year + " 년");
    	if(checkmonth > month){
    		if(year > checkyear) { 
    			show(year,month,1); 
    			lblyear.setText(year + " 년");
    		}
    		else { show(year,month,32); }
    	}
    	else if (checkmonth == month){ show(year,month,checkday); }
    	else  {
    		if(year < checkyear) {
    			show(year,month,32);
    			lblyear.setText(year + " 년");
    		}
    		else{
    			show(year,month,1);
    		}
    	}	
    }
    
    
    public void show(int year, int month, int day) { // 달력표시
    	TreeMap<String, String> map = BookingDao.bookingDao.bookingcheck(year, month, day); 
    	// 현재 년, 월 예약내역 가져오기 (예약 꽉찼을경우 버튼 비활성화 하기 위해)
    	calendar.set(year, month-1, 1); // 버튼을 눌러서 받은날짜로 달력 표시 (1일의 시작요일을 구하기위해 날짜에 1넣고)
    	int sweek = calendar.get(Calendar.DAY_OF_WEEK); // 시작 요일
    	int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당하는 월의 마지막날
    	calendar.set(year, month-1, day); // 다시 원래 날짜로 돌려놓음
		GridPane gridPane = new GridPane();
		int i = 1 ;
		for(int row = 0 ; row < 6 ; row++) { // 행        5행로하면 가끔 30~31일이 한줄 더 튀어나오는경우가 있어서 6행으로...
			for(int col = 0 ; col < 7 ; col++) { // 열		월~일 7열	
				Button button = new Button();
				button.setPrefWidth(70); // vbox사이즈 나누기 해서 딱 맞게 계산
				button.setPrefHeight(60); // vbox사이즈 나누기 해서 딱 맞게 계산
				button.setId("btn"+i); // css 적용시키려고 아이디 달아놓음
				button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
				// hover기능 적용시키려고 css파일 불러오기
				button.setOnAction( e-> // 클릭했을때
					{lblselected.setText("예약날짜 : " + year+"-"+month+"-"+button.getText()); // 버튼눌렀을때 화면에 날짜 표시
					this.day = Integer.parseInt(button.getText()); // 해당하는 날짜의 시간표를 출력하기 위해 날짜 저장
					bookingtime = null ; // 날짜를 여러번 클릭했을경우 테이블에서 클릭했던 날짜 초기화(20일 1-5 선택했다가 22일 클릭했을경우 기존 1-5 삭제)
					showtable(this.day); // 클릭한날짜의 예약테이블 보여주기
					}
				);
				if(i<sweek) { i++; } // 1일의 요일 (금요일이면 목요일까지는 버튼 안만듬)
				else if(i>=eday+sweek) { i++; } // 마지막날 넘어가면 버튼 안만듬 ( gridpane이 6*7 42칸이므로 말일이후엔 버튼 안만듬)
				else { // 1~말일 버튼만듬
					button.setText((i-sweek+1)+""); // 날짜 표시
					button.setFont(Font.font(18));
					i++ ;
				}		
				if(year < checkyear) { button.setDisable(true);	} // 현재 기준보다 전 년도 일경우 위에서 만든 버튼들 전부 비활성화
				else if (year == checkyear) { // 현재 기준년도일경우
					if(button.getText()=="" || Integer.parseInt(button.getText()) < day) { button.setDisable(true); }					
				} // 달력에 글씨가 없거나 현재 시점 보다 전날 일경우 버튼비활성화
				else { if(button.getText()=="") { button.setDisable(true); }	} // 현재 기준보다 후 년도 일경우 ex)2023년 글씨 없는 버튼만 비활성화
				
				
				// map = 메소드 맨위에 예약내역 가져온거 <날짜, 예약타임> 으로 저장되어있음
				if(map.containsKey(button.getText())) {  // map에 버튼 날짜가 있을경우
					if(map.get(button.getText()).length()>=100) { // 그날짜의 예약타임 길이가 100이상일경우 x-x, 단위로 저장되기때문에 4*25= 100
						button.setDisable(true); // 예약이 다 찼으므로 버튼 비활성화
						button.setText("예약완료"); // 텍스트 변경
						button.setFont(Font.font(12));
					}
				}
				gridPane.add(button, col, row);  // 위 조건을 모두 거치고나서 그리드팬에 버튼 추가
			}
		}	
		vbox.getChildren().add(gridPane);		 // vbox에 그리드팬을 추가
	}	

    public void showtable(int day) {
    	gridpane2.getChildren().clear(); // 날짜를 여러개 클릭할경우 기존 내역은 초기화하고 새로 표시함
    	Map<String, String> map = BookingDao.bookingDao.bookingcheck(year, month, day); // 예약내역 가져오기
    	int i = 0 ;

    	for(int row = 0 ; row  <=5 ; row++ ) { // 6X6 그리드팬에 1열은 코트번호, 1행은 시간
    		for(int col = 0 ; col <=5 ; col++) {
    			i++;
    			if(i==1) {}
    			else if(i==7) { // 1행엔 시간
    				Label label = new Label();
					label.setText("08:00~10:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==13) { // 1행엔 시간
    				Label label = new Label();
					label.setText("10:00~12:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==19) { // 1행엔 시간
    				Label label = new Label();
					label.setText("12:00~14:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==25) { // 1행엔 시간
    				Label label = new Label();
					label.setText("14:00~16:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==31) { // 1행엔 시간
    				Label label = new Label();
					label.setText("16:00~18:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==2) { // 1열엔 코트번호
    				Label label = new Label();
					label.setText("A코트");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==3) { // 1열엔 코트번호
    				Label label = new Label();
					label.setText("B코트");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==4) { // 1열엔 코트번호
    				Label label = new Label();
					label.setText("C코트");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==5) { // 1열엔 코트번호
    				Label label = new Label();
					label.setText("D코트");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==6) { // 1열엔 코트번호
    				Label label = new Label();
					label.setText("E코트");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}

    			else{ // 그 외엔 예약 버튼
    				ToggleButton tbutton = new ToggleButton();
	    			tbutton.setPrefWidth(100);
	    			tbutton.setPrefHeight(40);
	    			tbutton.setId(row+"-"+col); // 행, 열로 구성된 아이디 만듬 ex)1-1, 2-3 
	    			tbutton.setText("예약가능");
					tbutton.setOnAction( e->
						{
							if(tbutton.isSelected()) { // 버튼을 눌렀을때
								
								tbutton.setText("예약하기"); // 예약하기로 텍스트 변경
								if(bookingtime==null) {bookingtime = tbutton.getId()+"," ;} // 기존에 선택한 타임이 없으면 추가
								else  {bookingtime += tbutton.getId()+"," ;} // 있으면 기존 문자열에다가 새로 추가된 아이디 추가
							}
							else if(!(tbutton.isSelected())) { // 누른버튼을 다시 눌렀을때
								tbutton.setText("예약가능"); // 텍스트 원래대로 변경
								bookingtime = bookingtime.replace((tbutton.getId()+","), ""); // 문자열 제거
							}
						}
					);					
					if(map.get(day+"")!=null) { // 가져온 예약내역에서 해당하는시간에 값이 있으면
						if(map.get(day+"").contains(tbutton.getId())) {
			    			tbutton.setText("예약중"); // 텍스트 변경
			    			tbutton.setDisable(true); // 버튼 비활성화   			
						}
					}
					tbutton.setFont(Font.font(15));
	    			gridpane2.add(tbutton, col, row);  	// 그리드팬에 버튼 추가
    			}    			
    		} 				
    	}
    }    
}
