package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.competition.Competition;
import controller.competition.Competitionview;
import controller.login.Login;
import dao.BoardDao;
import dao.TournamentDao;
import dto.Board;
import dto.Tournament;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Homemain implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		boardlist = BoardDao.boardDao.blist();
		list();	
		
		
		tournamentlist = TournamentDao.tournamentDao.clist();
		list2();	
	}
	
	@FXML
    private ImageView mainimg;

    @FXML
    private TableView<Board> boardtable;

    @FXML
    private TableView<Tournament> ctable;
    
    public static ObservableList<Tournament> tournamentlist = TournamentDao.tournamentDao.clist();
    
    public static ObservableList<Board> boardlist = BoardDao.boardDao.blist();
    
    public void list() {
   	 
    	// 1. db에서 모든 게시글 가져오기
      	ObservableList<Board> boardlist = BoardDao.boardDao.blist2(1);
      
      	// 2. tableview 에 추가 
      	TableColumn tc = boardtable.getColumns().get(0);
      	tc.setCellValueFactory(new PropertyValueFactory<>("게시판번호"));
      	
      	tc = boardtable.getColumns().get(1); 
      	tc.setCellValueFactory(new PropertyValueFactory<>("게시판제목"));
      	
      	tc = boardtable.getColumns().get(2); 
      	tc.setCellValueFactory(new PropertyValueFactory<>("게시판작성일"));
      	
      	
      	// 3. tableview 에 list 연결
      	boardtable.setItems(boardlist);
      		//	테이블명(fxid).setItems( Observablelist ) ; // 테이블에 표시할 리스트(일반적 arraylist x) 설정
      	
      	// tableview 에서 해당 셀을 클릭했을때 이벤트
      	// boardtable.setOnMouseClicked(e -> {실행코드}); 테이블을 클릭했을때'
      	
      	boardtable.setOnMouseClicked(e -> {
      	// 1. 테이블에서 클릭한 객체를 임시객체에 저장	
      	controller.board.Board.board = boardtable.getSelectionModel().getSelectedItem(); // 클릭된 board 객체 호출
      	
      	if(controller.board.Board.board ==null) {}
      	else {
      		System.out.println("1");
			boolean result = BoardDao.boardDao.viewlog(Login.member.get회원아이디());
			if(result) {
				
				BoardDao.boardDao.view(controller.board.Board.board.get게시판번호());
				Home.instance.loadpage("/view/board/boardview.fxml");
			}
      	else {
      		// 3. 페이지전환
      		Home.instance.loadpage("/view/board/boardview.fxml");
      		}
      		}
      		}
      	);

        }
    
    public void list2(){
    	ObservableList<Tournament> tournamentlist = TournamentDao.tournamentDao.clist();
		
    	////db에ㅛㅓ 모든 게시글 가져오기
    	///tableview에 추가
    	
    	TableColumn tc = ctable.getColumns().get(0);
    	
    	tc.setCellValueFactory(new PropertyValueFactory<>("대회번호"));
    	
    	tc = ctable.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("대회이름"));
    	
    	tc = ctable.getColumns().get(2);
    	tc.setCellValueFactory(new PropertyValueFactory<>("대회날짜"));
    	
    	
    	
    	
    	
    	ctable.setItems(tournamentlist);
    	
    	
    	/// tableview에서해당 셀을 클릭했을때 이벤트
    	ctable.setOnMouseClicked( e -> { 
    		
    		int 대회번호 = ctable.getSelectionModel().getSelectedItem().get대회번호();
    		String 대회이름 = ctable.getSelectionModel().getSelectedItem().get대회이름();
    		String 대회날짜 = ctable.getSelectionModel().getSelectedItem().get대회날짜();
    		int 참가인원 = ctable.getSelectionModel().getSelectedItem().get참가인원();
    		String 대회이미지경로 = ctable.getSelectionModel().getSelectedItem().get대회이미지경로();
    		int 상금1위 = ctable.getSelectionModel().getSelectedItem().get상금1위();
    		int 상금2위 = ctable.getSelectionModel().getSelectedItem().get상금2위();
    		int 상금3위 = ctable.getSelectionModel().getSelectedItem().get상금3위();
    		int 참가비  = ctable.getSelectionModel().getSelectedItem().get참가비();
    		
    		Tournament tn = new Tournament(대회번호, 대회이름, 대회날짜, 참가인원, 대회이미지경로, 상금1위, 상금2위, 상금3위, 참가비);
    		
    		
    		
    		Competition.selectcnum = ctable.getSelectionModel().getSelectedItem();

    		Home.instance.loadpage("/view/competition/competitionview.fxml");
    		
    	});
    }
    
    
    
    @FXML
    void maininfo(MouseEvent event) {
    	Home.instance.loadpage("/view/home/maininfo.fxml");
    }

	
	
}
