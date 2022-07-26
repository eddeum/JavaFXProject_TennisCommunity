package controller.competition;

import java.awt.JobAttributes.DialogType;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import controller.home.Homemain;
import controller.login.Login;
import dao.MemberDao;
import dao.RecordDao;
import dao.TournamentDao;
import dto.Member;
import dto.Record;
import dto.Tournament;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Competitionview implements Initializable {
	
	
	public static Tournament tournament;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
    	
    	
    	Tournament tournament = Competition.selectcnum ;
    	System.out.println(tournament);
    	txtctitle.setText(tournament.get대회이름());
    	txtcdate.setText(tournament.get대회날짜());
    	txtctitle.setText(tournament.get대회이름());
    	txtcdate.setText("대회날짜: " + tournament.get대회날짜());
    	txtccount.setText("참가인원: "+tournament.get참가인원()+"명");
    	txtcreward1.setText("1등상금: "+tournament.get상금1위()+"원");
    	txtcreward2.setText("1등상금: "+tournament.get상금2위()+"원");
    	txtcreward3.setText("1등상금: "+tournament.get상금3위()+"원");
    	txtcprice.setText("참가비: "+tournament.get참가비()+"원");
    	img.setImage(new Image(Competition.selectcnum.get대회이미지경로()));
    	
    	entrytable.setVisible(false);
    		
    		///대회종료여부
    		String 대회날 = tournament.get대회날짜();
    		
    		String [] 대회당일 = 대회날.split(" ");
    		
    		String 대회날짜 = 대회당일[0]; ////yyyy-MM-dd
    		String 대회일시 = 대회당일[1]; ////HH:mm
    		
    		String [] yMd = 대회날짜.split("-");
    		String [] Hm = 대회일시.split(":");
    		
    		int 대회년 = Integer.parseInt(yMd[0]);
    		int 대회월 = Integer.parseInt(yMd[1]);
    		int 대회일 = Integer.parseInt(yMd[2]);
    		
    		int 대회시 = Integer.parseInt(Hm[0]);
    		int 대회분 = Integer.parseInt(Hm[0]);
    		
    		Date date = new Date();
    		SimpleDateFormat 오늘날짜표기 = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
    		String 오늘날짜 = 오늘날짜표기.format(date);
    		
    		String []  오늘날 = 오늘날짜.split(",");
    		
    		int 오늘년도 = Integer.parseInt(오늘날[0]);
    		int 오늘월 = Integer.parseInt(오늘날[1]);
    		int 오늘일 = Integer.parseInt(오늘날[2]);
    		int 오늘시 = Integer.parseInt(오늘날[3]);
    		int 오늘분 = Integer.parseInt(오늘날[4]);
    		
    		LocalDateTime 오늘 = LocalDateTime.of(오늘년도,오늘월, 오늘일, 오늘시, 오늘분);
    		LocalDateTime 대회하는날 = LocalDateTime.of(대회년, 대회월, 대회일 , 대회시, 대회분);
    		
    		Duration duration = Duration.between(오늘, 대회하는날);
    		long milliSecond = duration.toMinutes();
    		///관리자가 로그인 한 경우
        	if(Login.member.get회원아이디().equals("admin")) {
        		btnapply.setVisible(false);
        		btnapplycancel.setVisible(false);
    		///대회전
    		if(milliSecond>0) {
    			btnactivation.setText("참가신청기간");
    		}
    		
    		///대회날짜가 지났을 경우
    		else if(milliSecond<=0) {
    			btnactivation.setText("대회종료");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("대회일정이 종료되었습니다.");
    			alert.showAndWait();
    			
    			btnapply.setVisible(false);
    			btnapplycancel.setVisible(false);
    			
    			entrytable.setVisible(true);
    			
    			int cnum= Competition.selectcnum.get대회번호();
    			
    			
    			ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
    			
    			///db에 모든 게시글 가져오기
    			TableColumn tc = entrytable.getColumns().get(0);
    			
    			tc.setCellValueFactory(new PropertyValueFactory<>("기록순위"));
    			
    			tc = entrytable.getColumns().get(1);
    			
//    			int 회원번호 = Integer.parseInt("기록회원번호");
//    			String 아이디 = RecordDao.recordDao.searchid(회원번호);
    			tc.setCellValueFactory(new PropertyValueFactory<>("회원아이디"));
    			
    			tc = entrytable.getColumns().get(2);
    			tc.setCellValueFactory(new PropertyValueFactory<>("기록받은상금"));
    			
    			entrytable.setItems(recordlist);
    			
    			///table 속 객체를 선택했을 때
    			entrytable.setOnMouseClicked(e->{
    				
    				entrytable.getSelectionModel().getSelectedItem();
    				
    			});
    			
    		}
    	}
    	
    	///일반회원이 로그인한 경우
    	else {
    		btndelete.setVisible(false);
    		btnupdate.setVisible(false);
    		addranking.setVisible(false);
    		
    		///대회전
    		if(milliSecond>0) {
    			btnactivation.setText("참가신청기간");
    		}
    		
    		///대회날짜가 지났을 경우
    		else if(milliSecond<=0) {
    			btnactivation.setText("대회종료");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("대회일정이 종료되었습니다.");
    			alert.showAndWait();
    			
    			btnapply.setVisible(false);
    			btnapplycancel.setVisible(false);
    			
    			entrytable.setVisible(true);
    			
    			int cnum= Competition.selectcnum.get대회번호();
    			
    			
    			ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
    			System.out.println(recordlist.toString());
    			///db에 모든 게시글 가져오기
    			TableColumn tc = entrytable.getColumns().get(0);
    			
    			tc.setCellValueFactory(new PropertyValueFactory<>("기록순위"));
    			
    			tc = entrytable.getColumns().get(1);
    			
//    			int 회원번호 = Integer.parseInt("기록회원번호");
//    			String 아이디 = RecordDao.recordDao.searchid(회원번호);
    			tc.setCellValueFactory(new PropertyValueFactory<>("회원아이디"));
    			
    			tc = entrytable.getColumns().get(2);
    			tc.setCellValueFactory(new PropertyValueFactory<>("기록받은상금"));
    			
    			entrytable.setItems(recordlist);
    			
    			///table 속 객체를 선택했을 때
    			entrytable.setOnMouseClicked(e->{
    				
    				entrytable.getSelectionModel().getSelectedItem();
    				
    			});
    		}
    		
    	}
	}
	
	@FXML
    private Button btndelete;

    @FXML
    private Button btnback;

    @FXML
    private Button btnupdate;

    @FXML
    private ImageView img;

    @FXML
    private Text txtcprice;

    @FXML
    private Text txtcreward2;

    @FXML
    private Text txtccount;

    @FXML
    private Button btnapply;

    @FXML
    private Button btnapplycancel;

    @FXML
    private Label txtcdate;

    @FXML
    private TableView<Record> entrytable;
    
    
    
    @FXML
    private Button btnactivation;

    @FXML
    private TextField txtctitle;

    @FXML
    private Button addranking;

    @FXML
    private Text txtcreward1;

    @FXML
    private Text txtcreward3;

    @FXML
    void activation(ActionEvent event) {
    	
    }

    @FXML
    void add(ActionEvent event) {
    	
    	Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
    }
   

    @FXML
    void apply(ActionEvent event) {
    	
    	int 대회번호 = Competition.selectcnum.get대회번호();
    	int 참가인원 = Competition.selectcnum.get참가인원();
    	
    	////참가인원제한
    	String result = RecordDao.recordDao.cmember(대회번호);
    	
    	////참가인원이 더 많은 경우
    	if(Integer.parseInt(result)>=참가인원) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("신청 마감되었습니다.");
    		alert.showAndWait();
    	}
    	////참가인원이 미달일 경우
    	else {
    		
    		int 신청회원번호 = Login.member.get회원번호();
    
    		boolean 기존신청자 = RecordDao.recordDao.cmemberapply(신청회원번호,대회번호);
   
    	
    		///만약 회원이 이미 참가신청을 했을 경우
    		if(기존신청자) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("신청한 이력이 있습니다.");
    			alert.showAndWait();
    		}
    		
    		else{Home.instance.loadpage("/view/competition/competitionpay.fxml");}
    	}
    }

    @FXML
    void back(ActionEvent event) {
	Home.instance.loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void cancel(ActionEvent event) {
    	
    	
    	
    	int 신청회원번호 = Login.member.get회원번호();
    	int 대회번호 = Competition.selectcnum.get대회번호();
		boolean 기존신청자 = RecordDao.recordDao.cmemberdelete(신청회원번호, 대회번호);
    	
		System.out.println(기존신청자);
		
    	if(기존신청자) {
    		Alert alert = new Alert( AlertType.CONFIRMATION );
    		alert.setHeaderText("해당 대회신청을 취소하시겠습니까?");
    		Optional<ButtonType> optional = alert.showAndWait();
    		if( optional.get() == ButtonType.OK ) {
    		Home.instance.loadpage("/view/competition/competition.fxml");
    			}
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("해당 대회에 참가신청한 이력이 없습니다.");
    		alert.showAndWait();
    	}
    }
	
	////관리자 대회삭제
    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert( AlertType.CONFIRMATION );
		alert.setHeaderText("해당 대회를 삭제하시겠습니까?");
		Optional<ButtonType> optional = alert.showAndWait();
		if( optional.get() == ButtonType.OK ) {
		TournamentDao.tournamentDao.cdelete( Competition.selectcnum.get대회번호() );
		
		Home.instance.loadpage("/view/competition/competition.fxml");
	}
    }
    ////관리자대회수정
    @FXML
    void update(ActionEvent event) {
    	Home.instance.loadpage("/view/competition/competitionupdate.fxml");
    }

   

}
