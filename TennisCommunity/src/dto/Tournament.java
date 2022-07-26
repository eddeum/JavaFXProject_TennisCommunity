package dto;

public class Tournament {

	private int 대회번호;
	private String 대회이름;
	private String 대회날짜;
	private int 참가인원;
	private String 대회이미지경로;
	private int 상금1위;
	private int 상금2위;
	private int 상금3위;
	private int 참가비;
	
	
	public Tournament() {
		// TODO Auto-generated constructor stub
	}


	public Tournament(int 대회번호, String 대회이름, String 대회날짜, int 참가인원, String 대회이미지경로, int 상금1위, int 상금2위, int 상금3위,
			int 참가비) {
		super();
		this.대회번호 = 대회번호;
		this.대회이름 = 대회이름;
		this.대회날짜 = 대회날짜;
		this.참가인원 = 참가인원;
		this.대회이미지경로 = 대회이미지경로;
		this.상금1위 = 상금1위;
		this.상금2위 = 상금2위;
		this.상금3위 = 상금3위;
		this.참가비 = 참가비;
	}
	

	public int get대회번호() {
		return 대회번호;
	}


	public void set대회번호(int 대회번호) {
		this.대회번호 = 대회번호;
	}


	public String get대회이름() {
		return 대회이름;
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


	public int get참가인원() {
		return 참가인원;
	}


	public void set참가인원(int 참가인원) {
		this.참가인원 = 참가인원;
	}


	public String get대회이미지경로() {
		return 대회이미지경로;
	}


	public void set대회이미지경로(String 대회이미지경로) {
		this.대회이미지경로 = 대회이미지경로;
	}


	public int get상금1위() {
		return 상금1위;
	}


	public void set상금1위(int 상금1위) {
		this.상금1위 = 상금1위;
	}


	public int get상금2위() {
		return 상금2위;
	}


	public void set상금2위(int 상금2위) {
		this.상금2위 = 상금2위;
	}


	public int get상금3위() {
		return 상금3위;
	}


	public void set상금3위(int 상금3위) {
		this.상금3위 = 상금3위;
	}


	public int get참가비() {
		return 참가비;
	}


	public void set참가비(int 참가비) {
		this.참가비 = 참가비;
	}
	
	
	
}
