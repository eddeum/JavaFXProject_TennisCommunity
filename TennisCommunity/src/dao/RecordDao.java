package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.login.Login;
import dto.Member;
import dto.Mytournamentlist;
import dto.Record;
import dto.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class RecordDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static RecordDao recordDao = new RecordDao();
	
	public RecordDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("RecordDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	
	
	////��û�� ȸ�� ��ϵ��
	public boolean apply(Record record) {
		try {
		String sql = "insert into ��ȸ���(��ȸ��ȣ,��ȸ���ȸ����ȣ)values(?,?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, record.get��ϴ�ȸ��ȣ());
		ps.setInt(2, record.get���ȸ����ȣ());
		ps.executeUpdate();
		return true;
		}catch(Exception e) {System.out.println("��ȸ��û" + e);}
		return false;
	}
	
	
	////��û�� ȸ�� ���
	public ObservableList<Record> list(int cnum){
		try {
			ObservableList<Record> recordlist = FXCollections.observableArrayList();
			
			String sql = "select a.* ,b.ȸ�����̵� from ��ȸ��� a left join ȸ�� b on a.��ȸ���ȸ����ȣ = b.ȸ����ȣ where ��ȸ��ȣ=? order by ��ȸ��ϼ��� asc";
			ps=con.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			
		while(rs.next()) {
			Record record = new Record(
					rs.getInt(1), 
					rs.getString(6),
					rs.getInt(2), 
					rs.getInt(3), 
					rs.getInt(4), 
					rs.getInt(5));
		
			recordlist.add(record);
		}
		return recordlist;
			
		}catch(Exception e) {}
		return null;
		
	}
	
	
	/////�����Է�
	public boolean rankingadd(int ��ϼ���, int ��Ϲ�ȣ) {
		try {
			String sql = "update ��ȸ��� set ��ȸ��ϼ���=? where ��ȸ��Ϲ�ȣ=?";
			// 2. SQL ����
						ps = con.prepareStatement(sql);
						ps.setInt( 1 , ��ϼ��� );
						ps.setInt(2, ��Ϲ�ȣ);
					// 3. SQL ����
						ps.executeUpdate();
					// 4. SQL ���
						return true;
					}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
					return false; 
				
	}
	
	////1��������
	public boolean addreward1(int ���1����,int ��Ϲ�ȣ) {
		try {
			String sql = "update ��ȸ��� set ��ȸ��Ϲ������=? where ��ȸ��Ϲ�ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , ���1���� );
			ps.setInt(2, ��Ϲ�ȣ);
			ps.executeUpdate();
			// 4. SQL ���
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	
	public boolean addreward2(int ���2����,int ��Ϲ�ȣ) {
		try {
			String sql = "update ��ȸ��� set ��ȸ��Ϲ������=? where ��ȸ��Ϲ�ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , ���2���� );
			ps.setInt(2, ��Ϲ�ȣ);
			ps.executeUpdate();
			// 4. SQL ���
				return true;
		}catch(Exception e) {}
		return false;
	}
	public boolean addreward3(int ���3����,int ��Ϲ�ȣ) {
		try {
			String sql = "update ��ȸ��� set ��ȸ��Ϲ������=? where ��ȸ��Ϲ�ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , ���3���� );
			ps.setInt(2, ��Ϲ�ȣ);
			ps.executeUpdate();
			// 4. SQL ���
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	
	////��û�� �ο��� ã��
	public String cmember(int ��ȸ��ȣ) {
		try {
		String sql = "select count(*) from ��ȸ��� where ��ȸ��ȣ =?;";
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, ��ȸ��ȣ);
		rs =ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		
		}catch(Exception e) {}
		return null;
	}
	
	
	////���� ȸ���� �ش� ��ȸ�� �̹� ������û�� ����� ���� ��
	public boolean cmemberapply(int ��ûȸ����ȣ,int ��ȸ��ȣ) {
		try {
			String sql = "select * from ��ȸ��� where ��ȸ���ȸ����ȣ=? && ��ȸ��ȣ=?";
			ps= con.prepareStatement(sql);
			ps.setInt(1, ��ûȸ����ȣ);
			ps.setInt(2, ��ȸ��ȣ);
			rs = ps.executeQuery();
			if(rs.next()) return true;
			
		}catch(Exception e) {System.out.println(e);}
		return false;
	}
	
	///��ȸ��û���
	public boolean cmemberdelete(int ��ûȸ����ȣ, int ��ȸ��ȣ) {
		try {
			String sql = "delete from ��ȸ��� where ��ȸ��ȣ=? && ��ȸ���ȸ����ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ��ȸ��ȣ);
			ps.setInt(2, ��ûȸ����ȣ);
			ps.executeUpdate();
			return true;
			
		}catch (Exception e) {
			System.out.println(��ȸ��ȣ+ ��ûȸ����ȣ);
		}
		return false;
	}
	
	
	// ȸ������ ��ȸ����Ʈ ȣ��
	public ObservableList<Mytournamentlist> mtlist(int mnum){
		ObservableList<Mytournamentlist> myrecordlist = FXCollections.observableArrayList();
		String sql = "SELECT a.* ,b.��ȸ�̸�, b.��ȸ��¥  FROM ��ȸ��� a left join ��ȸ b on a.��ȸ��ȣ = b.��ȸ��ȣ where ��ȸ���ȸ����ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {

				Mytournamentlist mtl = new Mytournamentlist(rs.getInt(2), 
						rs.getString(6), 
						rs.getString(7), 
						rs.getInt(4), 
						rs.getInt(5) );
				myrecordlist.add(mtl);
			} // while e
			return myrecordlist;
		}catch(Exception e) {System.out.println("[SQL ����-ȸ������ȸ����Ʈ]"+e);}
		return null;
	} // ȸ���� ��ȸ����Ʈ ȣ�� end
	
	///ȸ����ȣ�� ���� ���̵�����
	public String searchid(int ȸ����ȣ){
		String sql = "select * from ȸ�� where ȸ����ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ȸ����ȣ);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9) );
				return rs.getString(2);
			}
		}catch(Exception e) {System.out.println(e);}
			return null;
		}
	
	///
	
}