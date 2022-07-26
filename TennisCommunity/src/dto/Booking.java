package dto;

public class Booking {
	private int 예약회원번호;
	private int 예약번호;
	private String 예약날짜;
	private String 예약시간; // 08시~10시 : 1 / 10시~12시 : 2 / ~~~~
//	private int 코트번호;
	private int 예약금액;
	
	public Booking() {
		
	}

	public Booking(int 예약회원번호, int 예약번호, String 예약날짜, String 예약시간, /*int 코트번호,*/ int 예약금액) {
		super();
		this.예약회원번호 = 예약회원번호;
		this.예약번호 = 예약번호;
		this.예약날짜 = 예약날짜;
		this.예약시간 = 예약시간;
//		this.코트번호 = 코트번호;
		this.예약금액 = 예약금액;
	}

	public int get예약회원번호() {
		return 예약회원번호;
	}

	public void set예약회원번호(int 예약회원번호) {
		this.예약회원번호 = 예약회원번호;
	}

	public int get예약번호() {
		return 예약번호;
	}

	public void set예약번호(int 예약번호) {
		this.예약번호 = 예약번호;
	}

	public String get예약날짜() {
		return 예약날짜;
	}

	public void set예약날짜(String 예약날짜) {
		this.예약날짜 = 예약날짜;
	}

	public String get예약시간() {
		return 예약시간;
	}

	public void set예약시간(String 예약시간) {
		this.예약시간 = 예약시간;
	}

//	public int get코트번호() {
//		return 코트번호;
//	}
//
//	public void set코트번호(int 코트번호) {
//		this.코트번호 = 코트번호;
//	}

	public int get예약금액() {
		return 예약금액;
	}

	public void set예약금액(int 예약금액) {
		this.예약금액 = 예약금액;
	}
	
	
}
