package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.booking.Bookingctr;
import controller.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Homedetail implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
    	if(!Login.member.get회원아이디().equals("admin")) {
    		btnsales.setVisible(false);
    		adminline.setVisible(false);
    	}
	}
	@FXML
	private Line adminline;
	
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
    private Button btndbooking1;

    @FXML
    private Button btndbooking2;

    @FXML
    private Button btndbooking3;

    @FXML
    private Button btnsales;
    
    @FXML
    private ImageView home;
    
    @FXML
    void home(MouseEvent event) {
    	Home.instance.loadpage("/view/home/homemain.fxml");
    }
    

    @FXML
    void viewsales(ActionEvent event) { //
    	Home.instance.loadpage("/view/info/sales.fxml");
    }
    
    @FXML
    void info(ActionEvent event) {
    	Home.instance.loadpage("/view/home/maininfo.fxml");
    }
 
    @FXML
    void board(ActionEvent event) {
    	Main.instance.loadpage("/view/home/home.fxml");
    	Home.instance.loadpage("/view/board/board.fxml");
    }

    @FXML
    void booking(ActionEvent event) {
    	Home.instance.loadpage("/view/booking/booking.fxml");
    }

    @FXML
    void bookinginfo(ActionEvent event) {
    	Home.instance.loadpage("/view/booking/bookinginfo.fxml");
    }

    @FXML
    void bookinglist(ActionEvent event) {
    	Home.instance.loadpage("/view/booking/bookinglist.fxml");
    }

    @FXML
    void competition(ActionEvent event) {
    	Main.instance.loadpage("/view/home/home.fxml");
    	Home.instance.loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void logout(ActionEvent event) {
    	Login.member = null ;
    	Main.instance.loadpage("/view/login/login.fxml");

    }

    @FXML
    void myinfo(ActionEvent event) {
    	Main.instance.loadpage("/view/home/home.fxml");
    	Home.instance.loadpage("/view/info/info.fxml");
    }

    @FXML
    void opendetail(ActionEvent event) {
    	Home.instance.loadtoppage("/view/home/homedetail.fxml");
    }
}
