package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CommentDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static CommentDao commentDao = new CommentDao();
	
	public CommentDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("CommentDao DB����" + e);
		}
	}
	
	// �޼ҵ� ����
	
	// 1. ��� �ۼ� �޼ҵ�
	public boolean rwrite(Comment comment) {
		String sql = "insert into ���(��۰Խ��ǹ�ȣ, ����ۼ���, ��۳���) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, comment.get��۰Խ��ǹ�ȣ());
			ps.setInt(2, comment.get����ۼ���());
			ps.setString(3, comment.get��۳���());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-��� �ۼ�]"+e);}
		return false;
	} // ��� �ۼ� end
	
	// 2. �ش� �Խù� ��� ��� ȣ�� �޼ҵ�
	public ObservableList<Comment> clist(int bnum){
		ObservableList<Comment> commentlist = FXCollections.observableArrayList();
		String sql = "select a.* ,b.ȸ�����̵� from ��� a left join ȸ�� b on a.����ۼ��� = b.ȸ����ȣ where ��۰Խ��ǹ�ȣ=? order by ��۹�ȣ asc";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			rs = ps.executeQuery();
			while(rs.next()) { // ��۹�ȣ, ��۰Խ��ǹ�ȣ, ����ۼ���, �ۼ���(���̵�), ��۳���, ����ۼ���
				Comment comment = new Comment(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getString(6), ///////////////??????
						rs.getString(4), 
						rs.getString(5) );
				commentlist.add(comment);
			} // while e
			return commentlist;
			}catch(Exception e) {System.out.println("[SQL ����-��� ȣ��]"+e);}
		return null;
	} // �Խù� �� ��� ��� ȣ�� end
	
	// 3.��ۼ��� �޼ҵ�
	public boolean rupdate( String rcontent, int rnum) {
		String sql = "update ��� set ��۳���=? where ��۹�ȣ=?";
		try {
			ps =con.prepareStatement(sql);
			ps.setString(1,rcontent );
			ps.setInt(2, rnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("��ۼ��� ����_CommentDao" + e);	}
		return false;
		
	}
	
	// 4. ��ۻ��� �޼ҵ�
	public boolean rdelete(int rnum) {
		String sql = "delete from ��� where ��۹�ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("��ۻ���_sql����" + e);}
		return false;
	}
	
	
	
	
	
}
