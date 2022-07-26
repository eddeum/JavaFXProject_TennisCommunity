package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TournamentDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static TournamentDao tournamentDao = new TournamentDao();
	
	public TournamentDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("TournamentDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	
	
	////��ȸ���
	public boolean add(Tournament tournament) {
		try {
			String sql= "insert into ��ȸ(��ȸ�̸�,��ȸ��¥,�����ο�,��ȸ�̹������,���1��,���2��,���3��,������)values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, tournament.get��ȸ�̸�());
			ps.setString(2, tournament.get��ȸ��¥());
			ps.setInt(3, tournament.get�����ο�());
			ps.setString(4, tournament.get��ȸ�̹������());
			ps.setInt(5, tournament.get���1��());
			ps.setInt(6, tournament.get���2��());
			ps.setInt(7, tournament.get���3��());
			ps.setInt(8, tournament.get������());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("��ȸ���" + e);}
		return false;
	}
	
	////��ȸ��¥,�ð� �ߺ�
	public String addcheck(String cdate) {
		try {
			String sql = "select * from ��ȸ where ��ȸ��¥=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, cdate);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		}
		catch(Exception e) {System.out.println("��¥�ߺ�: "+ e);}
		return null;
	}
	
	////��� ��ȸ ���
	public ArrayList<Tournament> list(){
		
		ArrayList<Tournament> tournamentlist = new ArrayList<>();
		try {
			String sql = "select * from ��ȸ";
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				Tournament tournament = new Tournament(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getInt(6),
				rs.getInt(7),
				rs.getInt(8),
				rs.getInt(9));
				tournamentlist.add(tournament);
			}
			return tournamentlist;
		}
		catch(Exception e) {System.out.println("��ȸ����Ʈ" + e);}
		return null;
	}
	
	///�����ڴ�ȸ����
	public boolean cupdate(int ��ȸ��ȣ, String ����,String cdate,int �����ο�,String addcimg ,int creward1,int creward2,int creward3,int ������) {
		try {
			String sql = "update ��ȸ set ��ȸ�̸�=? , ��ȸ��¥=?, �����ο�=?, ��ȸ�̹������=?, ���1��=?, ���2��=?, ���3��=?, ������=? where ��ȸ��ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setString( 1 , ���� );
			ps.setString( 2 , cdate );
			ps.setInt( 3 , �����ο� );
			ps.setString( 4 , addcimg );
			ps.setInt( 5 , creward1 );
			ps.setInt( 6 , creward2  );
			ps.setInt( 7 , creward3  );
			ps.setInt( 8 , ������  );
			ps.setInt(9, ��ȸ��ȣ);
			ps.executeUpdate();
			return true;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false;
		}
	
	///������ ��ȸ����
	public boolean cdelete(int cnum) {
		try {
			String sql = "delete from ��ȸ where ��ȸ��ȣ=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cnum);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {
		}return false;
	}
	
	///����Ȩ ��ȸ���̺� �ҷ�����
	public ObservableList<Tournament> clist() {
		
		ObservableList<Tournament> clist  = FXCollections.observableArrayList();
		
			try {
			///sql�ۼ�
			String sql = "select * from ��ȸ";
			////����
			ps=con.prepareStatement(sql);
			///����executeQuery();
			rs = ps.executeQuery();
			///���
			while(rs.next()) {
				Tournament tournament = new Tournament(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9)
						);
						
						clist.add(tournament);	
			}
			return clist;
		}
		catch(Exception e) {System.out.println("main"+e);}
		return null;}
	
	
	
}