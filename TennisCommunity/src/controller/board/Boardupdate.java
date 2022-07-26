package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boardupdate implements Initializable {
	
	@FXML
    private Button btnupdate;

    @FXML
    private TextField txtbtitle;

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
    private Button btnimgdelete;
    
    int bnum = Board.board.get게시판번호();
    private String pimage = null;
    
    
    @FXML
    void imgdelete(ActionEvent event) {
    	// 버튼이클릭되면 이미지 비우기 (set메소드)
    
    	img.setImage(null);
    	
    	
    	// 이미지 경로값을 null로 넣기
    	BoardDao.boardDao.updateimgdel(controller.board.Boardview.board.get게시판번호());
    	Board.board.set이미지경로(null);
    	// 경로값은 null이 되는데 이미지는 왜 그대로??
    	
    
    }

    @FXML
    void back(ActionEvent event) {
    	// 수정하기페이지에서 뒤로가기 누르면 게시글보기 페이지
    	Home.instance.loadpage("/view/board/boardview.fxml");
    	

    }
   

    @FXML
    void imgadd(ActionEvent event) {
    	
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
    	controller.board.Board.board.set이미지경로(pimage);
    	
    	}catch(Exception e) {System.out.println(e);}
    	
    }

    @FXML
    void update(ActionEvent event) {
    	int category ;
    	if(opt1.isSelected()) {
    		category = 1;
    	}
    	else if(opt2.isSelected()) {
    		category = 2;
    	}
    	else {
    		category = 1;
    	}
    	if( pimage == null ) pimage = Board.board.get이미지경로();
    	
    	// 수정하기페이지에서 수정이 완료되면 게시글보기 페이지
    	String 수정후제목 =txtbtitle.getText();
    	String 수정후내용 =txtbcontent.getText();
//    	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist();
//    	controller.board.Board.board =  boardlist.get(Board.board.get게시판번호());
    	
    	//String 수정후이미지 = img.getImage();
    	
    	//Home.instance.loadpage("/view/board/boardview.fxml");
    	
    	boolean result = BoardDao.boardDao.bupdate(category, txtbtitle.getText(), txtbcontent.getText(), pimage, Board.board.get게시판번호());
    	if( result ) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(" 글 수정 성공");
				if(Boardview.board.get이미지경로()!=null) {
					System.out.println("이미지경로 있음");
					Board.board.set게시판제목(수정후제목);
					Board.board.set게시판내용(수정후내용);
					Board.board.set이미지경로(pimage);
					
				}else {
					
					System.out.println("이미지경로 없음");
			
			Board.board.set게시판제목(수정후제목);
			Board.board.set게시판내용(수정후내용);
			Board.board.set이미지경로(null);
			img.setImage(null);
				}
			alert.showAndWait();
			
    		Home.instance.loadpage("/view/board/boardview.fxml");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText(" 글 수정 실패 [관리자에게문의]");
    		alert.showAndWait();
    	}

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	txtbtitle.setText(Board.board.get게시판제목());
    	txtbcontent.setText(Board.board.get게시판내용());
    	img.setImage(null);
    	if(Board.board.get이미지경로() != null) {
    		img.setImage(new Image(Board.board.get이미지경로()));
    		
    	}
    	if(Board.board.get게시판카테고리() == 1) {
    		category.selectToggle(opt1);
    	}else {category.selectToggle(opt2);}
    	
    }

}