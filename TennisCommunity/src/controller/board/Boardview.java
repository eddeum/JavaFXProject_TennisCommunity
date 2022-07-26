package controller.board;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.home.Homemain;
import controller.login.Login;
import dao.BoardDao;

import dto.Board;
import dao.CommentDao;
import dao.MemberDao;
import dto.Comment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Boardview implements Initializable {
	
	@FXML
    private Button btnredelete;
	
	@FXML
    private Label lbltitle;

    @FXML
    private Button btnback;

    @FXML
    private ImageView img;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private TextArea txtbcontent;

    @FXML
    private Label txtbdate;

    @FXML
    private Label txtmid;

    @FXML
    private Button btnrewrite;

    @FXML
    private TextArea txtrecontent;

    @FXML
    private TableView<Comment> replytable;

    @FXML
    private Button btnreplyupdate;
    
    public static Comment reply;
    

    @FXML
    public void prompt(MouseEvent event) {
    	txtrecontent.setText("");
    }
    public static Board board = controller.board.Board.board ;

    // 게시글 상세 메소드
    public void boardviewinfo() {
    	Board board = controller.board.Board.board;
		System.out.println(board.get게시판카테고리());
		if(board.get이미지경로() == null) {
		lbltitle.setText("제목 : "  + board.get게시판제목());
		txtbdate.setText("등록시간 : " +board.get게시판작성일() );
		txtmid.setText("아이디 : " + board.get작성자아이디() );
		txtbcontent.setText(board.get게시판내용());
		img.setImage(null);
		}
		txtbcontent.setEditable(false);
		if(board.get이미지경로() != null) {
			lbltitle.setText("제목 : "  + board.get게시판제목());
			txtbdate.setText("등록시간 : " +board.get게시판작성일() );
			txtmid.setText("아이디 : " + board.get작성자아이디());
			txtbcontent.setText(board.get게시판내용());
			img.setImage(new Image(board.get이미지경로()));
		}
		if( !board.get작성자아이디().equals(Login.member.get회원아이디()) ) {
			txtrecontent.setText("");
			btnupdate.setVisible(false);
			btndelete.setVisible(false);
			
			commenttableshow();
			
		}
    	
    }

    // 댓글 테이블 메소드
    public void commenttableshow() {
    	// 1. 현 게시물 번호
    	int bnum = controller.board.Board.board.get게시판번호();
    	
    	// 2. 현 게시물에 대한 댓글리스트
    	ObservableList<Comment> commentlist = CommentDao.commentDao.clist(bnum);
    	
    	System.out.println(commentlist.toString());
    	TableColumn tc = replytable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("댓글번호"));
    	
    	tc = replytable.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("작성자"));
    	
    	tc = replytable.getColumns().get(2);
    	tc.setCellValueFactory(new PropertyValueFactory<>("댓글작성일"));
    	
    	tc = replytable.getColumns().get(3);
    	tc.setCellValueFactory(new PropertyValueFactory<>("댓글내용"));
    	btnreplyupdate.setVisible(false);
    	btnredelete.setVisible(false);
		
    	
    	// 댓글 선택시 텍스트상자에 댓글내용 표시
    	replytable.setItems(commentlist);
    	replytable.setOnMouseClicked( e ->{ 
    		reply = replytable.getSelectionModel().getSelectedItem(); 
    		txtrecontent.setText(replytable.getSelectionModel().getSelectedItem().get댓글내용());
    		
    		
    		
    		if(!reply.get작성자().equals(Login.member.get회원아이디())) {
    			btnreplyupdate.setVisible(false);
    			txtrecontent.setText("");
    			btnredelete.setVisible(false);
    			//txtrecontent.setEditable(false);
    			
    			
    			//btnrewrite.setVisible(false);
    		}
    		else {btnreplyupdate.setVisible(true);
    		btnrewrite.setVisible(true);
    		btnredelete.setVisible(true);
    		}
    	}
    			
    	);
    	
    }
    
    @FXML
    void back(ActionEvent event) {
    	// 게시글보기 페이지에서 뒤로가기 누르면 게시글리스트 
    	Home.instance.loadpage("/view/board/board.fxml");

    }

    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("해당 게시물 삭제할까요?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		
    		BoardDao.boardDao.bdelete(controller.board.Board.board.get게시판번호());
    		// 게시글보기 페이지에서 삭제 누르면 게시글 리스트
        	Home.instance.loadpage("/view/board/board.fxml");
    	}
    	

    }
    boolean upcheck = true; // 수정 버튼 스위치 변수
    @FXML
    void replyupdate(ActionEvent event) {
    	int bnum = reply.get댓글번호();
    	String 댓글수정내용 = txtrecontent.getText();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	boolean result = CommentDao.commentDao.rupdate(댓글수정내용, bnum);
    	
    	
    	if(upcheck) {
    		alert.setHeaderText("댓글 수정후 버튼 눌러주세요");
	    	alert.showAndWait();
	    	txtrecontent.setEditable(true);
	    	btnrewrite.setVisible(false);
	    	btnupdate.setText("수정완료");
    		upcheck = false;
    	}
    	else {
    		if(result ) {
        		
    			alert.setHeaderText(" 글 수정 성공");
    		
    		alert.showAndWait();
    		reply.set댓글내용(댓글수정내용);
    		btnupdate.setText("수정");
			upcheck = true;
    		Home.instance.loadpage("/view/board/boardview.fxml");
        	}
    		
    	}
    	

    }

    @FXML
    void rewrite(ActionEvent event) {
    	// 게시글보기 페이지에서 댓글작성하면 게시글보기페이지


    	String rcontent = txtrecontent.getText();
    	if(rcontent.equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("내용을 입력하세요");
			alert.showAndWait();
			//Home.instance.loadpage("/view/board/boardview.fxml");
    		System.out.println("안돼 돌아가");
    		return;
    	}
    	int mnum = Login.member.get회원번호();
    	int bnum = controller.board.Board.board.get게시판번호();    	
    	
    	Comment comment = new Comment(0, bnum, mnum, MemberDao.memberDao.getid(mnum), rcontent, null);
    	
    	boolean result = CommentDao.commentDao.rwrite(comment);
    	
    	
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("댓글 등록 성공");
    		alert.showAndWait();
    		txtrecontent.setText("");
    		commenttableshow();
    		//Home.instance.loadpage("/view/board/boardview.fxml");
    	}
    	
    	
    }
    
    @FXML
    void redelete(ActionEvent event) {
    	
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("해당 댓글을 삭제할까요?");
	Optional<ButtonType> optional = alert.showAndWait();
	if(optional.get() == ButtonType.OK) {
		
		CommentDao.commentDao.rdelete(reply.get댓글번호());
		// 게시글보기 페이지에서 삭제 누르면 게시글 리스트
    	Home.instance.loadpage("/view/board/boardview.fxml");
	}
    	

    }

    @FXML
    void update(ActionEvent event) {
    	// 게시글보기 페이지에서 수정하기 누르면 수정페이지로 이동
    	Home.instance.loadpage("/view/board/boardupdate.fxml");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		commenttableshow();
		boardviewinfo();
		
		
		
		
	}

}