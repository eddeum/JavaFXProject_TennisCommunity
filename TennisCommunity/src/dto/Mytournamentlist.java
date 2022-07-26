package dto;

public class Mytournamentlist {
	
	// 필드
	private int 대회번호;
	private String 대회이름;
	private String 대회날짜;
	private int 기록순위;
	private int 기록받은상금;
	
	// 생성자
	public Mytournamentlist() {}

	public Mytournamentlist(int 대회번호, String 대회이름, String 대회날짜, int 기록순위, int 기록받은상금) {
		super();
		this.대회번호 = 대회번호;
		this.대회이름 = 대회이름;
		this.대회날짜 = 대회날짜;
		this.기록순위 = 기록순위;
		this.기록받은상금 = 기록받은상금;
	}



	// 메소드

	public String get대회이름() {
		return 대회이름;
	}

	public int get대회번호() {
		return 대회번호;
	}

	public void set대회번호(int 대회번호) {
		this.대회번호 = 대회번호;
	}

	public void set대회이름(String 대회이름) {
		this.대회이름 = 대회이름;
	}

	public String get대회날짜() {
		return 대회날짜;
	}

	public void set대회날짜(String 대회날짜) {
		this.대회날짜 = 대회날짜;
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
