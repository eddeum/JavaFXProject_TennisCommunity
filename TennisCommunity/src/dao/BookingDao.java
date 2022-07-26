package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import controller.booking.Bookinglist;
import controller.login.Login;
import dto.Booking;


public class BookingDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static BookingDao bookingDao = new BookingDao();
	
	public BookingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("BookingDao DB����" + e);
		}
	}
	
	// �޼ҵ� ����
	
	// �����ϱ� �޼ҵ�
	public boolean booking (String bookingday, String bookingtime, int cash) { 
		try {
			String sql = "insert into ����(����ȸ����ȣ,���೯¥,����ð�,����ݾ�) values (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Login.member.getȸ����ȣ());
			ps.setString(2, bookingday);
			ps.setString(3, bookingtime);
			ps.setInt(4, cash);
			ps.executeUpdate();
			return true;		
		}
		catch(Exception e) {
			System.out.println("�����ϱ� �޼ҵ�" + e);
		}
		return false;			
	}
	
	// �������� �ҷ����� �޼ҵ� (�����û ȭ��� �����ߺ�üũ ������)
	public TreeMap<String, String> bookingcheck (int year, int month, int day) {
		TreeMap<String, String> map = new TreeMap<>();
		String �� = null ;
		if(month<=9) {
			�� = "0"+month;
		}
		else {
			�� = month+"";
		}
		try {
			String sql = "select trim(leading '0' from substring_index(���೯¥, '-',-1)), ����ð�, �����ȣ from ���� where ���೯¥ like '%"+year+"-"+��+"%' order by ���೯¥ asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(Bookinglist.selectedbooking!=null) {
				while(rs.next()) {
					if(rs.getInt(3)!=Bookinglist.selectedbooking.get�����ȣ()) {
						if(map.containsKey(rs.getString(1))){
							map.replace(rs.getString(1), (map.get(rs.getString(1))+rs.getString(2)));
						}	
						else{
							map.put(rs.getString(1), rs.getString(2));
						}
					}
					else {}
				}
				
			}
			else {
				while(rs.next()) {
					if(map.containsKey(rs.getString(1))){
						map.replace(rs.getString(1), (map.get(rs.getString(1))+rs.getString(2)));
					}	
					else{
						map.put(rs.getString(1), rs.getString(2));
					}				
				}
			}
			return map;
		}
		catch(Exception e) {
			System.out.println("���������ҷ����� �޼ҵ�" + e);
		}
		return null;
	}
	
	// �������� �ҷ����� �޼ҵ� (���ຯ�� ȭ��� ȸ���� ������ ���� �ҷ������)
	public ArrayList<Booking> bookinglist(int mnum) {
		try {
			ArrayList<Booking> list = new ArrayList<>();
			String sql = "select * from ���� where ����ȸ����ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Booking book = new Booking(mnum, rs.getInt(1), rs.getString(3), rs.getString(4) , rs.getInt(5));
				list.add(book);
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("���������ҷ�����(���ຯ��)" + e);
		}	
		return null;
	}
	
	// �������(����)�޼ҵ�
	public void deletebooking(int bnum) {
		try {
			String sql = "delete from ���� where �����ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	// ���� ���� �޼ҵ�
	public void changebooking(int bnum, String bookingday, String bookingtime, int cash) {
		try {
			String sql = "update ���� set ���೯¥=?, ����ð�=?, ����ݾ�=����ݾ�+? where �����ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookingday);
			ps.setString(2, bookingtime);
			ps.setInt(3, cash);
			ps.setInt(4, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("����޼ҵ忡��" + e);
		}
	}
	

}
