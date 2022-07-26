package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.login.Login;
import dto.Point;


public class PointDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static PointDao pointDao = new PointDao();
	
	public PointDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("PointDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	
	// 1. ����Ʈ���� �޼ҵ�
	public boolean pointadd(Point point) {
		String sql = "insert into ����Ʈ����(����Ʈ����ȸ����ȣ, ���޳�¥, ��������Ʈ) values(?,?,?)";
		System.out.println(point.get��������Ʈ());
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get����Ʈ����ȸ����ȣ());
			ps.setString(2, point.get���޳�¥());
			ps.setInt(3, point.get��������Ʈ());
			ps.executeUpdate();
				String sql2 = "update ȸ�� set ȸ������Ʈ=? where ȸ����ȣ=? ";
				int mpoint = Login.member.getȸ������Ʈ();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get��������Ʈ());
				ps.setInt(2, Login.member.getȸ����ȣ());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ����]"+e);}
		return false;
	} // ����Ʈ���� end
	
	//1�� ��� ����Ʈ����
	public boolean rewardpointadd1(Point point) {
		String sql = "insert into ����Ʈ����(����Ʈ����ȸ����ȣ, ���޳�¥, ��������Ʈ) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get����Ʈ����ȸ����ȣ());
			ps.setString(2, point.get���޳�¥());
			ps.setInt(3, point.get��������Ʈ());
			ps.executeUpdate();
				String sql2 = "update ȸ�� set ȸ������Ʈ=? where ȸ����ȣ=? ";
				int mpoint = Login.member.getȸ������Ʈ();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get��������Ʈ());
				ps.setInt(2, point.get����Ʈ����ȸ����ȣ());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ����]"+e);}
		return false;
	}
	
	public boolean rewardpointadd2(Point point) {
		String sql = "insert into ����Ʈ����(����Ʈ����ȸ����ȣ, ���޳�¥, ��������Ʈ) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get����Ʈ����ȸ����ȣ());
			ps.setString(2, point.get���޳�¥());
			ps.setInt(3, point.get��������Ʈ());
			ps.executeUpdate();
				String sql2 = "update ȸ�� set ȸ������Ʈ=? where ȸ����ȣ=? ";
				int mpoint = Login.member.getȸ������Ʈ();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get��������Ʈ());
				ps.setInt(2, point.get����Ʈ����ȸ����ȣ());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ����]"+e);}
		return false;
	}
	
	public boolean rewardpointadd3(Point point) {
		String sql = "insert into ����Ʈ����(����Ʈ����ȸ����ȣ, ���޳�¥, ��������Ʈ) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get����Ʈ����ȸ����ȣ());
			ps.setString(2, point.get���޳�¥());
			ps.setInt(3, point.get��������Ʈ());
			ps.executeUpdate();
				String sql2 = "update ȸ�� set ȸ������Ʈ=? where ȸ����ȣ=? ";
				int mpoint = Login.member.getȸ������Ʈ();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get��������Ʈ());
				ps.setInt(2, point.get����Ʈ����ȸ����ȣ());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-����Ʈ����]"+e);}
		return false;
	}

}