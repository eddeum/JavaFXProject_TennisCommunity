package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class Board implements Initializable {
	
	@FXML
    private TableView<dto.Board> boardtable;
		
    @FXML
    private Button btnwrite;

    @FXML
    private Button btnback;
    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt3;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt1;

    @FXML
    void accwrite(ActionEvent event) { //
    	// 글작성 버튼을 누르면 글작성 페이지로
    	Home.instance.loadpage("/view/board/boardwrite.fxml");
    }

   
	
    public static dto.Board board;
    public static ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist();
    
    public void list() {
    	
    	// 1. db에서 모든 게시글 가져오기

    	// 2. tableview 에 추가 
    	TableColumn tc = boardtable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("게시판번호"));
    	
    	tc = boardtable.getColumns().get(1); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("게시판제목"));
    	
    	tc = boardtable.getColumns().get(2); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("작성자아이디"));
    	
    	tc = boardtable.getColumns().get(3); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("게시판작성일"));
    	
    	tc = boardtable.getColumns().get(4); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("게시판조회수"));
    	
    	// 3. tableview 에 list 연결
    	boardtable.setItems(boardlist);
    		//	테이블명(fxid).setItems( Observablelist ) ; // 테이블에 표시할 리스트(일반적 arraylist x) 설정
    	
    	// tableview 에서 해당 셀을 클릭했을때 이벤트
    	// boardtable.setOnMouseClicked(e -> {실행코드}); 테이블을 클릭했을때'
    	
    	boardtable.setOnMouseClicked(e -> {
    	// 1. 테이블에서 클릭한 객체를 임시객체에 저장	
    	board = boardtable.getSelectionModel().getSelectedItem(); // 클릭된 board 객체 호출
    	// 2. 조회수 증가
    	if(board ==null) {}
    	else {
			boolean result = BoardDao.boardDao.viewlog(Login.member.get회원아이디());
			if(result) {
				BoardDao.boardDao.view(board.get게시판번호());
				Home.instance.loadpage("/view/board/boardview.fxml");
			}
    	
    	else {
    		// 3. 페이지전환
    		Home.instance.loadpage("/view/board/boardview.fxml");
    	}
    	}
    	
    	
    	});
    	
    	
    }
    
    
  public void list2() {
	 
	 
	// 1. db에서 모든 게시글 가져오기
  	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist2(1);
  
  	// 2. tableview 에 추가 
  	TableColumn tc = boardtable.getColumns().get(0);
  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판번호"));
  	
  	tc = boardtable.getColumns().get(1); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판제목"));
  	
  	tc = boardtable.getColumns().get(2); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("작성자아이디"));
  	
  	tc = boardtable.getColumns().get(3); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판작성일"));
  	
  	tc = boardtable.getColumns().get(4); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판조회수"));
  	
  	// 3. tableview 에 list 연결
  	boardtable.setItems(boardlist);
  		//	테이블명(fxid).setItems( Observablelist ) ; // 테이블에 표시할 리스트(일반적 arraylist x) 설정
  	
  	// tableview 에서 해당 셀을 클릭했을때 이벤트
  	// boardtable.setOnMouseClicked(e -> {실행코드}); 테이블을 클릭했을때'
  	
  	boardtable.setOnMouseClicked(e -> {
  	// 1. 테이블에서 클릭한 객체를 임시객체에 저장	
  	board = boardtable.getSelectionModel().getSelectedItem(); // 클릭된 board 객체 호출
  	
  	if(board ==null) {}
  	else {
  		// 3. 페이지전환
  		Home.instance.loadpage("/view/board/boardview.fxml");
  	}
  	
  	
  	});

    }
  
  public void list3() {
		 
		 
		// 1. db에서 모든 게시글 가져오기
	  	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist2(2);
	  
	  	// 2. tableview 에 추가 
	  	TableColumn tc = boardtable.getColumns().get(0);
	  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판번호"));
	  	
	  	tc = boardtable.getColumns().get(1); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판제목"));
	  	
	  	tc = boardtable.getColumns().get(2); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("작성자아이디"));
	  	
	  	tc = boardtable.getColumns().get(3); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판작성일"));
	  	
	  	tc = boardtable.getColumns().get(4); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("게시판조회수"));
	  	
	  	// 3. tableview 에 list 연결
	  	boardtable.setItems(boardlist);
	  		//	테이블명(fxid).setItems( Observablelist ) ; // 테이블에 표시할 리스트(일반적 arraylist x) 설정
	  	
	  	// tableview 에서 해당 셀을 클릭했을때 이벤트
	  	// boardtable.setOnMouseClicked(e -> {실행코드}); 테이블을 클릭했을때'
	  	
	  	boardtable.setOnMouseClicked(e -> {
	  	// 1. 테이블에서 클릭한 객체를 임시객체에 저장	
	  	board = boardtable.getSelectionModel().getSelectedItem(); // 클릭된 board 객체 호출
	  	
	  	if(board ==null) {}
	  	else {
	  		// 3. 페이지전환
	  		Home.instance.loadpage("/view/board/boardview.fxml");
	  	}
	  	
	  	
	  	});

	    }
    
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boardlist = BoardDao.boardDao.blist();
		list();	
		
	}
	
	public void viewall(){
		list();
	}
	public void view1(){
		list2();
	}
	public void view2(){
		list3();
	}
	

}
