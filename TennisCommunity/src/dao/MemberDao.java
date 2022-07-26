package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dto.Member;


public class MemberDao {

	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static MemberDao memberDao = new MemberDao();
	
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("");
		}
		catch(Exception e) {
			System.out.println("MemberDao DB����" + e);
		}
	}

	// �޼ҵ� ����
	
	// 1. ȸ������ �޼ҵ�
	public boolean signup(Member member) {
		String sql = "insert into ȸ��(ȸ�����̵�, ȸ����й�ȣ, ȸ���̸�, ȸ���������, ȸ���̸���, ȸ����ȭ��ȣ, ȸ������Ʈ) values(?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getȸ�����̵�());
			ps.setString(2, member.getȸ����й�ȣ());
			ps.setString(3, member.getȸ���̸�());
			ps.setString(4, member.getȸ���������());
			ps.setString(5, member.getȸ���̸���());
			ps.setString(6, member.getȸ����ȭ��ȣ());
			ps.setInt(7, member.getȸ������Ʈ());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB ���� ����-ȸ������]"+e);}
		return false;
	} // ȸ������ end
	
	// 1-2. ���̵� �ߺ�üũ �޼ҵ�(�μ� : ���̵�)
	public boolean idcheck(String id) {
		String sql = "select * from ȸ�� where ȸ�����̵�=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-���̵��ߺ�üũ]"+e);}
		return false;
	} // ���̵� �ߺ�üũ end
	
//	// 1-3. ����������� üũ �޼ҵ�
//	public void birthcheck(String birth) {
//		Calendar cal = Calendar.getInstance(); // �Է¹��� ���� ���������� ���ϱ�����
//		int year = Integer.parseInt(birth.substring(0, 2));
//		int month = Integer.parseInt(birth.substring(2, 4));
//		int day = Integer.parseInt(birth.substring(4, 6));
//		int eday = cal.getActualMaximum(cal.DAY_OF_MONTH); // �Է¹��� ���� ���������� ����
//		if(month>=1 && month<=12 && day>=1 && day<=eday) {
//			if(year>=00 && year<=22) {
//				int year2 = 2000+year;
//			}else {
//				int year2 = 1900+year;
//			} // else e
//			int birth2 = year2+month+day;
//		}
//		
//	}
	

	// 2. �α��� �޼ҵ�(�μ� : ���̵�, ��й�ȣ)
	public boolean login(String id, String password) {
		String sql = "select * from ȸ�� where ȸ�����̵�=? and ȸ����й�ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-�α���]"+e);}
		return false;
	} // �α��� end
	
	// 3. ���̵�ã�� �޼ҵ�(�μ� : �̸�, �������, ��ȭ��ȣ)
	public String findid(String name, String phone) {
		String sql = "select * from ȸ�� where ȸ���̸�=? and ȸ����ȭ��ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-���̵�ã��]"+e);}
		return null;
	} // ���̵�ã��  end
	
	// 4. ��й�ȣã�� �޼ҵ�(�μ� : ���̵�, �̸���, ��ȭ��ȣ)
	public String findpassword(String name, String id, String phone) {
		String sql = "select * from ȸ�� where ȸ���̸�=? and ȸ�����̵�=? and ȸ����ȭ��ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-��й�ȣã��]"+e);}
		return null;
	} // ��й�ȣã�� end
	
	// 5. ȸ���������� �޼ҵ�
	public boolean memberupdate(int mnum, String password, String name, String email, String phone) {
		String sql = "update ȸ�� set ȸ����й�ȣ=?, ȸ���̸�=?, ȸ���̸���=?, ȸ����ȭ��ȣ=? where ȸ����ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, mnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB ���� ����-ȸ����������]"+e);}
		return false;
	} // ȸ���������� end
	
	// 6. ȸ��Ż�� �޼ҵ�
	public boolean memberdelete(int mnum) {
		String sql = "delete from ȸ�� where ȸ����ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB ���� ����-ȸ��Ż��]"+e);}
		return false;
	} // ȸ��Ż�� end
	
	// 7. ���̵�� ȸ������ ȣ��
	public Member getmember(String id) {
		String sql = "select * from ȸ�� where ȸ�����̵�=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9) );
				return member;
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-ȸ������ȣ��]"+e);}
		return null;
	}
	
	// 8. ȸ����ȣ�� ȸ�����̵� ȣ��
	public String getid(int mnum) {
		String sql = "select ȸ�����̵� from ȸ�� where ȸ����ȣ=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			} // if e
		}catch(Exception e) {System.out.println("[DB ���� ����-���̵�ȣ��]"+e);}
		return null;
	} // ȸ����ȣ�� ���̵� ȣ��
	
	///��ȸ��û�� ����� ����Ʈ�� �ִٸ� ������ ����Ʈ���� ���̳ʽ�
	public boolean cminuspoint(int ȸ����ȣ,int ����Ʈ����) {
		try {
			String sql = "update ȸ�� set ȸ������Ʈ=?  where ȸ����ȣ=?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, ����Ʈ����);
			ps.setInt(2, ȸ����ȣ);
			ps.executeUpdate();
			return true;
			
		}catch(Exception e) {}
		return false;
	}
	
	public void pointlog2 (String id) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String pointdate = sdf.format(date);
			String sql = "select * from ��ȸ�� where ��ȸ�����̵�=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()==false) { // �α��γ�¥ ����� ������ ���� ����
				String sql2 = "insert into ��ȸ�� (��ȸ�����̵�,��ȸ���α�) values(?,?)";
				ps = con.prepareStatement(sql2);
				ps.setString(1, id);
				ps.setString(2, pointdate);
				ps.executeUpdate();
				return;
			}
			else { // �α��� ����� ������
				if(rs.getString(2).equals(pointdate)) { // �α��� ��ϰ� �α����� ��¥�� ������
					return; //
				}
				else if(!rs.getString(2).equals(pointdate)) { // �α��α�ϰ� �α����ѳ�¥�� �ٸ��� �α��γ�¥ ����
					String sql3 = "update ��ȸ�� set ��ȸ���α�=?, ��ȸ���Խ��ǹ�ȣ=? where ��ȸ�����̵�=?";
					ps = con.prepareStatement(sql3);
					ps.setString(1, pointdate);
					ps.setString(2, null); // �α��α�Ͽ� �о��� �Խñ� ��ȣ�� �ʱ�ȭ
					ps.setString(3, id);
					ps.executeUpdate();
					return;
				}
			}
		}
		catch(Exception e) {
			System.out.println("[SQL ����/����Ʈ�α�]" + e );
		}
	}
}