package controller.competition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import dao.TournamentDao;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Competitionadd implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
	}
	
	@FXML
    private Button btnadd;

    @FXML
    private TextField txtcname;

   

    @FXML
    private TextField txtcprice;

    @FXML
    private Button btnimg;

    @FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    private DatePicker txtcdate;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup timecategory;

    @FXML
    private RadioButton opt2;

    @FXML
    private TextField txtccount;

    @FXML
    private TextField txtcreward1;

    @FXML
    private TextField txtcreward2;

    @FXML
    private TextField txtcreward3;

    @FXML ///대회등록
    void add(ActionEvent event) {
    	String cname = txtcname.getText();
    	int ccount = Integer.parseInt(txtccount.getText()) ;
    	int cprice = Integer.parseInt(txtcprice.getText());
    	int creward1 = Integer.parseInt(txtcreward1.getText());
    	int creward2 = Integer.parseInt(txtcreward2.getText());
    	int creward3 = Integer.parseInt(txtcreward3.getText());
    	
    	
    	String ctime = null;
    		if(opt1.isSelected()) {
    			ctime="18:00";
    		}
    		else if(opt2.isSelected()) {
    			ctime="20:00";
    		}
    	
    	String cdate = txtcdate.getValue().toString()+" "+ctime;
    	
    	
    	///DB처리
    	 String result2 = TournamentDao.tournamentDao.addcheck(cdate);
    	
    	
    	///만약에 해당 날짜, 시간에 등록된 대회가 있다면
    	if(cdate==result2) {
    		System.out.println(result2);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("해당 날짜에 등록된 대회가 있습니다.");
    		alert.showAndWait();
    		Home.instance.loadpage("/view/competition/competitionadd.fxml");
    	}
    	else {
    		
    		if(addcimg==null) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("이미지를 등록해주세요");
    			alert.showAndWait();
    		}
    		else {
    		///객체화
    		Tournament tournament = new Tournament(0, cname, cdate, ccount, addcimg ,creward1,creward2,creward3, cprice);
    		
    		//DB처리
    		boolean result = TournamentDao.tournamentDao.add(tournament);
    		
    		if(result) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("대회등록 완료");
    			alert.showAndWait();
    			Home.instance.loadpage("/view/competition/competition.fxml");
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("대회등록 실패");
    			alert.showAndWait();
    		}
    		}
    	}
    		
    }

    private String addcimg = null;

    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void imgadd(ActionEvent event) {
    	//파일선택 클래스
    	FileChooser fileChooser = new FileChooser();
    	//파일 선택 형식
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("이미지파일:img file", "*png","*jpeg","*jpg","*gif"));
    	//파일선택 화면 실행
    	File file = fileChooser.showOpenDialog(new Stage());
    	//선택한 화면 경로 텍스트
    	txtpath.setText("파일경로: " + file.getPath());
    	//파일경로
    	addcimg = file.toURI().toString();
    	//미리보기
    	Image image = new Image(addcimg);
    	img.setImage(image);
    	
    	//현재 프로젝트 폴더에 선택 파일 이동하기
    	try {
    	//파일입력 
    		FileInputStream inputStream = new FileInputStream(file);
    	//파일 출력
    		File copyfile = new File("C:/Users/504/git/Team2EZEN/Team2EZEN/src/img/"+file.getName());
    		FileOutputStream outputStream = new FileOutputStream(copyfile);
    	//바이트 배열 선언
    		byte [] bytes =  new byte[1024*1024*1024];
    	//반복문을 이용한 입력값 모두 읽어오기
    		int size;
    		while((size=inputStream.read(bytes))>0) {
    			outputStream.write(bytes,0,size);
    		}
    		inputStream.close();
    		outputStream.close();
    	//파일명 db저장
    		addcimg = copyfile.toURI().toString();
    	}
    	catch(Exception e) {System.out.println(e);}
    }

}
