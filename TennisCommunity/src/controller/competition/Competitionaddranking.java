package controller.competition;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.PointDao;
import dao.RecordDao;
import dto.Point;
import dto.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Competitionaddranking implements Initializable{

	public static Record record;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		int cnum= Competition.selectcnum.get대회번호();
		
		ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
		
		///db에 모든 게시글 가져오기
		TableColumn tc = entrytable.getColumns().get(0);
		
		tc.setCellValueFactory(new PropertyValueFactory<>("기록번호"));
		
		tc = entrytable.getColumns().get(1);
		
		tc.setCellValueFactory(new PropertyValueFactory<>("회원아이디"));
		
		tc = entrytable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("기록순위"));
		
		entrytable.setItems(recordlist);
		
		///table 속 객체를 선택했을 때
		entrytable.setOnMouseClicked(e->{
			
			record = entrytable.getSelectionModel().getSelectedItem();
			System.out.println(record);
		});
		
	}
	
	 @FXML
	    private TableView<Record> entrytable;
	 
	 @FXML
	    private Label recordnum;

	
	  @FXML
	    private Button btnadd;

	    @FXML
	    private TextField txtscore;

	    @FXML
	    private Button btnback;

	    @FXML
	    void add(ActionEvent event) {
	    	
	    	
	    	int 기록번호 = record.get기록번호();
	    	int 기록순위 = Integer.parseInt(txtscore.getText());
	    	int 기록1등상금 = Competition.selectcnum.get상금1위();
	    	int 기록2등상금 = Competition.selectcnum.get상금2위();
	    	int 기록3등상금 = Competition.selectcnum.get상금3위();
	    	
	    	boolean result = RecordDao.recordDao.rankingadd(기록순위, 기록번호);
	    	
	    	if(result) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	String pdate = sdf.format(new Date());
	    		int 기록회원번호 = record.get기록회원번호();
	    		////순위입력시 상금 자동 db저장
	    		if(기록순위==1){
	    			RecordDao.recordDao.addreward1(기록1등상금,기록번호);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			
	    			Point point = new Point(기록회원번호, 기록1등상금, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    			
	    		}
	    		else if(기록순위==2){
	    			RecordDao.recordDao.addreward2(기록2등상금,기록번호);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			Point point = new Point(기록회원번호, 기록2등상금, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    		
	    		}
	    		else if(기록순위==3) {
	    			RecordDao.recordDao.addreward3(기록3등상금,기록번호);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			Point point = new Point(기록회원번호, 기록3등상금, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    		}
	    		else {
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    		}
	    		
	    	}
	    	
	    
	    	
	    }

	    @FXML
	    void back(ActionEvent event) {
	    	Home.instance.loadpage("/view/competition/competitionview.fxml");
	    }

	
}
