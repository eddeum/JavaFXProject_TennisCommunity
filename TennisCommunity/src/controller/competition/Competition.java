package controller.competition;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import controller.login.Login;
import controller.login.Loginpane;
import dao.TournamentDao;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Competition implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
    	if(!Login.member.get회원아이디().equals("admin")) {

        		btncompetition.setVisible(false);
       	}
    	//대회리스트 가져오기
    	ArrayList<Tournament> tournamentlist = TournamentDao.tournamentDao.list();
    	
    	///간격만들기
    	GridPane gridPane = new GridPane();
    	
    	gridPane.setPadding(new Insets(10));
    	
    	gridPane.setHgap(20);
    	gridPane.setVgap(20);
    	
    	//반복문
    	int i = 0;
    	for(int row =0; row <tournamentlist.size(); row++) {//행

    		for(int col =0; col<1; col++) {//열

    			///이미지
    			ImageView imageView = new ImageView(new Image(tournamentlist.get(i).get대회이미지경로()));
    			imageView.setFitHeight(200);
    			imageView.setFitWidth(1090);
    			
    			
    			Button button = new Button(null, imageView);
    			
    			button.setStyle("-fx-background-color:transparent");
    			
    			//버튼에 id값 넣기
    			button.setId(i+"");
    			//버튼 클릭이벤트
    			button.setOnAction(e -> {
    				System.out.println(e.toString());
    				///클릭한 버튼의 id 가져오기
    				int id =Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
    				//클릭한 대회기록 저장
    				selectcnum =  tournamentlist.get(id);
    				
    				Home.instance.loadpage("/view/competition/competitionview.fxml");
    				
    			} );
    			
    			gridPane.add(button, col, row);
    			
    			i++;
    			}
    		}
    	
    	///vbox에 그리드 넣기
    		vbox.getChildren().add(gridPane);
    	
	}

	public static Tournament selectcnum;///선택된 버튼의 기록 저장
	
    @FXML
    private Button btncompetition;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;

    @FXML
    void addcompetition(ActionEvent event) {
    	if(Login.member.get회원아이디().equals("admin")) {
    		Home.instance.loadpage("/view/competition/competitionadd.fxml");
    	}
    	else {
    		btncompetition.setVisible(false);
    	}	
   	}

}
