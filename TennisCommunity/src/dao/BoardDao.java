package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import dto.Board;
import dto.Comment;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BoardDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("BoardDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	
	// 1. �� �ۼ� �޼ҵ�
	public boolean bwrite(Board board) {
		String sql = "insert into �Խ���(�Խ����ۼ���,�Խ���ī�װ�, �Խ�������, �Խ��ǳ���,�Խ����̹������,�Խ�����ȸ��) values(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, board.get�Խ����ۼ���());
			ps.setInt(2, board.get�Խ���ī�װ�());
			ps.setString(3, board.get�Խ�������());
			ps.setString(4, board.get�Խ��ǳ���());
			ps.setString(5, board.get�̹������());
			ps.setInt(6, board.get�Խ�����ȸ��());			
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-�� �ۼ�]"+e);}
		return false;
	} // �� �ۼ� end
	
	// 2. �� ���� �޼ҵ�
	public boolean bdelete(int bnum) {
		String sql = "delete from �Խ��� where �Խ��ǹ�ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-�� ����]"+e);}
		return false;
	} // �� ���� end
	
	// 3. �� ���� �޼ҵ�
	public boolean bupdate(int category, String title, String contents, String image, int bnum) {
		String sql = "update �Խ��� set �Խ���ī�װ�=?, �Խ�������=?, �Խ��ǳ���=? ,�Խ����̹������=? where �Խ��ǹ�ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			ps.setString(2, title);
			ps.setString(3, contents);
			ps.setString(4, image);
			ps.setInt(5, bnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL ����-�� ����]"+e);}
		return false;
	} // �� ���� end
	
	// 4. ��� �ۼ� �� ȣ�� �޼ҵ�
	public ObservableList<Board> blist() {
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		
		try {
			String sql = "SELECT a.* ,b.ȸ�����̵�  FROM �Խ��� a left join ȸ�� b on a.�Խ����ۼ��� = b.ȸ����ȣ order by �Խ��ǹ�ȣ desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { // �Խ��ǹ�ȣ, �ۼ���, �ۼ��ھ��̵�,�Խ���ī�װ�, ����, ����, ���, ��ȸ��, �ۼ���
				Board board = new Board(rs.getInt(1),
						rs.getInt(2),
						rs.getString(9),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8)
						);
				if(board.get�ۼ��ھ��̵�()!=null) { boardlist.add(board);}
			} // while e
			return boardlist;
			}catch(Exception e) {System.out.println("[SQL ����-�� ȣ��]"+e);}
		return null;
	} // ��� �ۼ� �� ȣ�� end
	
	// 4. ��� �ۼ� �� ȣ�� �޼ҵ�
	public ObservableList<Board> blist2(int category) {
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		
		try {
			String sql = "SELECT a.* ,b.ȸ�����̵�  FROM �Խ��� a left join ȸ�� b on a.�Խ����ۼ��� = b.ȸ����ȣ where �Խ���ī�װ�=? order by �Խ��ǹ�ȣ desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			rs = ps.executeQuery();
			while(rs.next()) { // �Խ��ǹ�ȣ, �ۼ���, �ۼ��ھ��̵�,�Խ���ī�װ�, ����, ����, ���, ��ȸ��, �ۼ���
				Board board = new Board(rs.getInt(1),
						rs.getInt(2),
						rs.getString(9),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8)
						);
				if(board.get�ۼ��ھ��̵�()!=null) { boardlist.add(board);}	
			} // while e
			return boardlist;
			}catch(Exception e) {System.out.println("[SQL ����-�� ȣ��]"+e);}
		return null;
	} // ��� �ۼ� �� ȣ�� end
	
	
	
	
	// ��ȸ�� �޼ҵ�
		public boolean view(int bnum) {
			try {
				String sql = "update �Խ��� set �Խ�����ȸ��=? where �Խ��ǹ�ȣ=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, controller.board.Board.board.get�Խ�����ȸ��()+1);
				ps.setInt(2, bnum);
				ps.executeUpdate();
				return true;
			}
			catch(Exception e) {
				System.out.println("[��ȸ�� �޼ҵ�]" + e);
			}
			return false;
		}
		
		// 9. ��ȸ�� �α� �޼ҵ� (DB�Ἥ �����)
		public boolean viewlog (String id) {
			try {
				String sql = "select * from ��ȸ�� where ��ȸ�����̵�=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String pointdate = sdf.format(date);
			// 4. SQL ���
				if(rs.next()==false) {  // ???????? �̺κ��� ���ָ� ������ �� ������ �α����Ҷ� pointlog table�� ���� �����ǹǷ�
										// rs.next�� false�϶��� ���µ�....
					String sql2 = "insert into ��ȸ�� (��ȸ�����̵�,��ȸ���α�,��ȸ���Խ��ǹ�ȣ) values(?,?,?)";
					ps = con.prepareStatement(sql2);
					ps.setString(1, id);
					ps.setString(2, pointdate);
					ps.setString(3, controller.board.Board.board.get�Խ��ǹ�ȣ()+"");
					ps.executeUpdate();
					return true;
				}
				else { 
					if(rs.getString(3)==null) { // �Խñ� ���� ������ ������
						String sql3 = "update ��ȸ�� set ��ȸ���Խ��ǹ�ȣ=? where ��ȸ�����̵�=?";
						ps = con.prepareStatement(sql3);
						ps.setString(1, controller.board.Board.board.get�Խ��ǹ�ȣ()+"");
						ps.setString(2, id);
						ps.executeUpdate();
						return true;
					}
					// �Խñ� ���� ������ ������
					String[] nums = rs.getString(3).split(","); // �Խñ۹�ȣ���� ���ڿ��迭�� ����
					for(String temp : nums) { // �迭���� Ŭ���� �Խù���ȣ�� ������  false
						if(temp.equals(controller.board.Board.board.get�Խ��ǹ�ȣ()+"")) { // �о���						
							return false;
						}
					} 
					// ������ ��ȣ �߰�
					String sql3 = "update ��ȸ�� set ��ȸ���Խ��ǹ�ȣ=? where ��ȸ�����̵�=?";
					ps = con.prepareStatement(sql3);
					ps.setString(1, rs.getString(3)+","+controller.board.Board.board.get�Խ��ǹ�ȣ());
					ps.setString(2, id);
					ps.executeUpdate();
					return true;
				}
			}
			catch(Exception e) {
				System.out.println("[SQL ����/��ȸ���α�]" + e );
			}
			return false;
		} // ��ȸ�� �α� �޼ҵ� (DB�Ἥ �����)
		public void updateimgdel(int bnum){
			
			String sql = "update �Խ��� set �Խ����̹������ = ? where �Խ��ǹ�ȣ =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, null);
				ps.setInt(2, bnum);
				ps.executeUpdate();
			} catch (Exception e) {System.out.println("sql���� �̹�������"+e);}
			
		}
	
		
} // class end
