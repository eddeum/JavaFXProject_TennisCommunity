package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;



import dao.BoardDao;
import dao.MemberDao;
import dto.Board;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class Boardwrite implements Initializable {
	
    @FXML
    private Button btnwrite;

    @FXML
    private TextField txtbname;

    @FXML
    private TextArea txtbcontent;

    @FXML
    private Button btnimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt2;

    @FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    void back(ActionEvent event) {
    	// 글작성페이지에서 뒤로가기 누르면 게시글 리스트로
    	Home.instance.loadpage("/view/board/board.fxml");

    }
    
    

    private String pimage = null;
    @FXML
    void imgadd(ActionEvent event) { // 이미지등록 버튼 클릭했을때
    	javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
    	fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("이미지파일:image file","*png","*jpeg","*jpg","*gif" ));
    	
    	File file = fileChooser.showOpenDialog(new javafx.stage.Stage());
    	
    	txtpath.setText("파일경로 : " + file.getPath());
    	
    	pimage = file.toURI().toString();
    	
    	javafx.scene.image.Image image = new javafx.scene.image.Image(pimage);
    	img.setImage(image);
    	
    	// *선택한 파일을 현재프로젝트 폴더로 복사(이동)
    	
    	try {
    	// 1. 파일 입력스트림
    	FileInputStream inputStream = new FileInputStream(file);
    	
    	// 2. 파일 출력 스트림
    	
		File copyfile = new File("C:/Users/504/git/Team2EZEN/Team2EZEN/src/img/"+file.getName());
    	FileOutputStream outputStream = new FileOutputStream(copyfile);
    	byte[] bytes = new byte[1024*1024*1024];
    	int size;
    	while( (size = inputStream.read(bytes)) > 0) {
    		outputStream.write(bytes,0,size);
    		
    	}
    	pimage = copyfile.toURI().toString();
    	// 용량이 큰 경우에는 스트림 종료 필수
    	inputStream.close();
    	outputStream.close();
    	
    	}catch(Exception e) {System.out.println(e);}
    	
    }
    
    @FXML
    void write(ActionEvent event) {
    	
    	// 1.컨트롤에 입력된 데이터 가져오기
	    	String bname = txtbname.getText();
	    	String bcontent = txtbcontent.getText();
	    	   if(bname.equals("") || bcontent.equals("")) {
	   	    	Alert alert = new Alert(AlertType.INFORMATION);
	   			alert.setHeaderText("내용을 입력하세요");
	   			alert.showAndWait();
	       		return;
	   	    }
	    
	    		// * 카테고리
	    	int category = 0 ;
	    	if(opt1.isSelected()) {category=1;}
	    	if(opt2.isSelected()) {category=2;}
	    int mnum = Login.member.get회원번호();
	    	
	    	// 2.객체화
	    Board board = new Board(0,mnum,MemberDao.memberDao.getid(mnum),category,bname,bcontent,pimage,0,null);
	    
	    	// 3. db처리
	    boolean result = BoardDao.boardDao.bwrite(board);
	    
	 
	    	
	      	// 4. 결과처리
		    if(result) {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setHeaderText("게시글 등록 완료");
		    	alert.showAndWait();
		    	Home.instance.loadpage("/view/board/board.fxml");
		    }
	    
	  
 	    	
    	
    	
    	// 글작성페이지에서 뒤로가기 누르면 게시글 리스트로
    	Home.instance.loadpage("/view/board/board.fxml");

    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	category.selectToggle(opt1);
	    	
	    }

}