package dto;

public class Point {

	private int 포인트지급회원번호;
	private int 지급포인트;
	private String 지급날짜;
	
	public Point() {
		
	}

	public Point(int 포인트지급회원번호, int 지급포인트, String 지급날짜) {
		super();
		this.포인트지급회원번호 = 포인트지급회원번호;
		this.지급포인트 = 지급포인트;
		this.지급날짜 = 지급날짜;
	}

	public int get포인트지급회원번호() {
		return 포인트지급회원번호;
	}

	public void set포인트지급회원번호(int 포인트지급회원번호) {
		this.포인트지급회원번호 = 포인트지급회원번호;
	}

	public int get지급포인트() {
		return 지급포인트;
	}

	public void set지급포인트(int 지급포인트) {
		this.지급포인트 = 지급포인트;
	}

	public String get지급날짜() {
		return 지급날짜;
	}

	public void set지급날짜(String 지급날짜) {
		this.지급날짜 = 지급날짜;
	}
	
	
	
}
