package controller.info;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TreeMap;

import dao.SalesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Sales implements Initializable {
	
	Calendar calendar = Calendar.getInstance();
	int checkyear = calendar.get(Calendar.YEAR);
	int checkmonth = calendar.get(Calendar.MONTH)+1;
	int checkday = calendar.get(Calendar.DATE) ;
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH)+1;
	int day = calendar.get(Calendar.DATE) ;
	DecimalFormat df = new DecimalFormat("#,##0");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		show(year,month,day);
		lblyear.setText(year+ " 년");
		lblmonth.setText(month+" 월");
	}
	
    @FXML
    private BarChart barchart;

    @FXML
    private Button btnprevious;

    @FXML
    private Button btnnext;

    @FXML
    private Label monthsales;

    @FXML
    private Label yearsales;    

    @FXML
    private Label lblyear;    

    @FXML
    private Label lblmonth;

    @FXML
    void next(ActionEvent event) {
    	if(month+1 == 13) { year +=1 ; month = 1 ;}
    	else { month +=1 ;}

    	if(checkmonth > month){
    		if(year > checkyear) {
    			show(year,month,1);
    			
    		}
    		else {
    			show(year,month,32);
    			
    		}
    	}
    	else if (checkmonth == month){
    		show(year,month,checkday);
    		}
    	else  {
    		if(year < checkyear) {
    			show(year,month,32);
    		}
    		else{
    			show(year,month,1);
    		}
    	}
    	lblyear.setText(year+" 년");
    	lblmonth.setText(month+" 월");
    }

    @FXML
    void previous(ActionEvent event) {
    	if( month-1 == 0) { year -= 1 ; month = 12 ; }
    	else{ month -=1 ;}

    	if(checkmonth > month){
    		if(year > checkyear) {
    			show(year,month,1);
    		}
    		else {
    			show(year,month,32);
    		}
    	}
    	else if (checkmonth == month){
    		show(year,month,checkday);
    	}
    	else  {
    		if(year < checkyear) {
    			show(year,month,32);
    		}
    		else{
    			show(year,month,1);
    		}
    	}
    	lblyear.setText(year+" 년");
    	lblmonth.setText(month+" 월");

    }
    
    public void show(int year, int month , int day) {
    	barchart.getData().clear();
		XYChart.Series series = new XYChart.Series<>();
		series.setName("매출");
		TreeMap<String, Integer> salestotal = SalesDao.salesDao.salestotal(year,month,day);
		int 매출 = 0 ;
		for(String key : salestotal.keySet()) {
			XYChart.Data data = new XYChart.Data<>(key,salestotal.get(key));
			series.getData().add(data);
			매출 += salestotal.get(key);
		}
		barchart.getData().add(series);
		barchart.getXAxis().setAnimated(false);
		monthsales.setText(month+" 월 매출액 : " + df.format(매출) + " 원");
		yearsales.setText(year+" 년 총 매출액 : "+df.format(SalesDao.salesDao.yearsales(year+""))+ " 원");		
    }
}
