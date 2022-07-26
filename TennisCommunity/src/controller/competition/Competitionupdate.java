package controller.competition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.TournamentDao;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Competitionupdate implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtcname.setText(Competition.selectcnum.get대회이름());
		txtccount.setText(Competition.selectcnum.get참가인원()+"");
		txtcprice.setText(Competition.selectcnum.get참가비()+"");
		txtcreward1.setText(Competition.selectcnum.get상금1위()+"");
		txtcreward2.setText(Competition.selectcnum.get상금2위()+"");
		txtcreward3.setText(Competition.selectcnum.get상금3위()+"");
		
	}
	
	 @FXML
	    private Button btnupdate;

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

	    @FXML
	    void adddate(ActionEvent event) {
	    	
	    }

	    @FXML
	    void back(ActionEvent event) {
	    	Home.instance.loadpage("/view/competition/competitionview.fxml");
	    }

	    private String addcimg = null;
	    
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
	    

	    @FXML
	    void update(ActionEvent event) {
	    	
	    	String 제목 = txtcname.getText();
	    	int 참가인원 = Integer.parseInt(txtccount.getText());
	    	int 참가비 = Integer.parseInt(txtcprice.getText());
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
	    		
	    		int 대회번호 = Competition.selectcnum.get대회번호();
	    		
	    		//DB처리
	    		boolean result = TournamentDao.tournamentDao.cupdate(대회번호 , 제목, cdate, 참가인원, addcimg ,creward1,creward2,creward3, 참가비);
	    		
	    		if(result) {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setHeaderText("대회수정 완료");
	    			alert.showAndWait();
	    			Home.instance.loadpage("/view/competition/competition.fxml");
	    		}
	    		else {
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setHeaderText("대회수정 실패");
	    			alert.showAndWait();
	    		}
	    	
	    	}
	    }
	
}
