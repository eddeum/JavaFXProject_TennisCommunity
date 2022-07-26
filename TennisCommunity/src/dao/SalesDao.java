package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Calendar;
import java.util.TreeMap;


public class SalesDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static SalesDao salesDao = new SalesDao();
	
	public SalesDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("SalesDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	public void salesbooking (String day, int amount,int bookingnum) {
		try {
			String sql = "insert into ����(���⳯¥,�����,���⿹���ȣ) values (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, day);
			ps.setInt(2, amount);
			ps.setInt(3, bookingnum);
			ps.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public int callbnum (String bookingday, String bookingtime) { // ���⿹���ȣȮ�ο� �޼ҵ�
		try {
			String sql = "select �����ȣ from ���� where ���೯¥=? and ����ð�=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookingday);
			ps.setString(2, bookingtime);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {			
		}
		return 0;		
	}
	
	// �������(����)�޼ҵ�
	public void deletesales(int bnum) {
		try {
			String sql = "delete from ���� where ���⿹���ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	// ���ຯ��޼ҵ�
	public void changesales(int bnum, int cash) {
		try {
			String sql = "update ���� set �����=�����+? where ���⿹���ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cash);
			ps.setInt(2, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
			System.out.println("����޼ҵ忡��" + e);
		}
	}
	
	// ����Ʈ������ �������� �޼ҵ�
	public void pointaddsales(String pdate, int pointcash, int pnum) {
		String sql = "insert into ����(���⳯¥, �����, ��������Ʈ��ȣ) values (?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pdate);
			ps.setInt(2, pointcash);
			ps.setInt(3, pnum);
			ps.executeUpdate();
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ������ ��������]"+e);}
	} // ����Ʈ������ �������� end
	
	public int callpnum(int bnum, String pointadddate) {
		String sql = "select ����Ʈ��ȣ from ����Ʈ���� where ����Ʈ����ȸ����ȣ =? and ���޳�¥=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.setString(2, pointadddate);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} // if end
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ��ȣ ȣ��]"+e);}
		return 0;
	} // ����Ʈ��ȣȣ�� end
	
	public TreeMap<String, Integer> salestotal(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1); 
		int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String �� ;
		if(month<=9) {
			�� = "0"+month;
		}
		else {
			��=""+month;
		}
		TreeMap<String, Integer> map = new TreeMap<String, Integer>(); 
			try {
			String sql = "select substring_index(���⳯¥,'-',-1), sum(�����) from ���� where ���⳯¥ like '%"+year+"-"+��+"%' group by ���⳯¥ order by ���⳯¥ asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for(int i = 1 ; i <= eday ; i++) {
				if(i<=9) {
					map.put("0"+i, 0);
				}
				else{
					map.put(""+i, 0);
				}
			}	
			while(rs.next()) {
				if (map.containsKey(rs.getString(1))) {
					map.put(rs.getString(1), rs.getInt(2));	
				}
				
			}
			return map;
		}
		catch(Exception e) {
			
		}
		return null;
	}
	

	public int yearsales(String year) { // ������ �����
		try {
			String sql = "select sum(�����) from ���� where substring_index(���⳯¥,'-',1)=?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, year);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}
		catch(Exception e) {
		}
		return 0;
	}
	
	
	
	
	
}