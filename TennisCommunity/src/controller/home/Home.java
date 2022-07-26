package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.booking.Bookingctr;
import controller.login.Login;
import dao.BoardDao;
import dao.TournamentDao;
import dto.Board;
import dto.Tournament;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

public class Home implements Initializable{
	
	public static Home instance ;
	
	public Home() {
		instance = this;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null;
    	if(!Login.member.get회원아이디().equals("admin")) {
    		btnsales.setVisible(false);
    		adminline.setVisible(false);
    	}
    	
    	loadpage("/view/home/homemain.fxml");
    	
	}
	
	@FXML
    private BorderPane borderpane;

	 @FXML
	    private ImageView home;
	
    @FXML
    private Button btnmyinfo;

    @FXML
    private Button btncompetition;

    @FXML
    private Button btnboard;

    @FXML
    private Button btnbooking;

    @FXML
    private Button btninfo;
    
    @FXML
    private Button btnlogout;
    
    @FXML
    private Button btnsales;
    
    @FXML
    private Line adminline;

    @FXML
    private ImageView mainimg;
    
   
    @FXML
    void home(MouseEvent event) {
    	loadpage("/view/home/homemain.fxml");
    }
    

    @FXML
    void viewsales(ActionEvent event) { //
    	loadpage("/view/info/sales.fxml");
    }
    
    @FXML
    void board(ActionEvent event) { //
    	loadpage("/view/board/board.fxml");

    }
    
    @FXML
    void myinfo(ActionEvent event) {
    	loadpage("/view/info/info.fxml");
    }

    @FXML
    void opendetail(ActionEvent event) { //
    	loadtoppage("/view/home/homedetail.fxml");
    }

    @FXML
    void competition(ActionEvent event) { //
    	loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void info(ActionEvent event) { //	
    	loadpage("/view/home/maininfo.fxml");
    	
    }

   
    
    @FXML
    void logout(ActionEvent event) { //
    	Login.member = null;
    	Main.instance.loadpage("/view/login/login.fxml");
    }
    
    
    
    
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setCenter(parent);
		}
		catch(Exception e) {
			System.out.println("[Home/loadpage]" + e);
			
		}
	}
	
	public void loadtoppage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			borderpane.setTop(parent);
		}
		catch(Exception e) {
			System.out.println("[Home/loadtoppage]" + e);
		}
	}

}