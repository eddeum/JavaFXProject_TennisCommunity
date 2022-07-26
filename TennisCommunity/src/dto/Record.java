package dto;

public class Record {

	private int 기록번호; // DB테이블 pk
	private String 회원아이디;
	private int 기록대회번호;
	private int 기록회원번호;
	private int 기록순위;
	private int 기록받은상금;
		
	public Record() {
		// TODO Auto-generated constructor stub
	}
	
	public Record(int 기록번호, String 회원아이디, int 기록대회번호, int 기록회원번호, int 기록순위, int 기록받은상금) {
		super();
		this.기록번호 = 기록번호;
		this.회원아이디 = 회원아이디;
		this.기록대회번호 = 기록대회번호;
		this.기록회원번호 = 기록회원번호;
		this.기록순위 = 기록순위;
		this.기록받은상금 = 기록받은상금;
	}


	public int get기록번호() {
		return 기록번호;
	}

	public void set기록번호(int 기록번호) {
		this.기록번호 = 기록번호;
	}



	public String get회원아이디() {
		return 회원아이디;
	}



	public void set회원아이디(String 회원아이디) {
		this.회원아이디 = 회원아이디;
	}



	public int get기록대회번호() {
		return 기록대회번호;
	}



	public void set기록대회번호(int 기록대회번호) {
		this.기록대회번호 = 기록대회번호;
	}



	public int get기록회원번호() {
		return 기록회원번호;
	}



	public void set기록회원번호(int 기록회원번호) {
		this.기록회원번호 = 기록회원번호;
	}



	public int get기록순위() {
		return 기록순위;
	}



	public void set기록순위(int 기록순위) {
		this.기록순위 = 기록순위;
	}



	public int get기록받은상금() {
		return 기록받은상금;
	}



	public void set기록받은상금(int 기록받은상금) {
		this.기록받은상금 = 기록받은상금;
	}



	

	
}
