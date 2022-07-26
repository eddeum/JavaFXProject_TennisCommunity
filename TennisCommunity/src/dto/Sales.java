package dto;

public class Sales {

	private String 매출날짜;
	private int 매출액;
	private int 매출예약번호;
	private int 매출대회번호;
	
	public Sales() {
		
	}

	public Sales(String 매출날짜, int 매출액, int 매출예약번호, int 매출대회번호) {
		super();
		this.매출날짜 = 매출날짜;
		this.매출액 = 매출액;
		this.매출예약번호 = 매출예약번호;
		this.매출대회번호 = 매출대회번호;
	}

	public String get매출날짜() {
		return 매출날짜;
	}

	public void set매출날짜(String 매출날짜) {
		this.매출날짜 = 매출날짜;
	}

	public int get매출액() {
		return 매출액;
	}

	public void set매출액(int 매출액) {
		this.매출액 = 매출액;
	}

	public int get매출예약번호() {
		return 매출예약번호;
	}

	public void set매출예약번호(int 매출예약번호) {
		this.매출예약번호 = 매출예약번호;
	}

	public int get매출대회번호() {
		return 매출대회번호;
	}

	public void set매출대회번호(int 매출대회번호) {
		this.매출대회번호 = 매출대회번호;
	}
	
	

}
