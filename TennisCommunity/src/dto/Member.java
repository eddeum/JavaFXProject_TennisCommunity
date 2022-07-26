package dto;

public class Member {
	
	private int 회원번호;
	private String 회원아이디;
	private String 회원비밀번호;
	private String 회원이름;
	private String 회원생년월일;
	private String 회원이메일;
	private String 회원전화번호;
	private int 회원포인트;
	private String 회원가입일;
	
	public Member() {

	}
	
	public Member(int 회원번호, String 회원아이디, String 회원비밀번호, String 회원이름, String 회원생년월일, String 회원이메일, String 회원전화번호,
			int 회원포인트, String 회원가입일) {
		super();
		this.회원번호 = 회원번호;
		this.회원아이디 = 회원아이디;
		this.회원비밀번호 = 회원비밀번호;
		this.회원이름 = 회원이름;
		this.회원생년월일 = 회원생년월일;
		this.회원이메일 = 회원이메일;
		this.회원전화번호 = 회원전화번호;
		this.회원포인트 = 회원포인트;
		this.회원가입일 = 회원가입일;
	}
	
	
	public int get회원번호() {
		return 회원번호;
	}

	public void set회원번호(int 회원번호) {
		this.회원번호 = 회원번호;
	}
	public String get회원아이디() {
		return 회원아이디;
	}
	public void set회원아이디(String 회원아이디) {
		this.회원아이디 = 회원아이디;
	}
	public String get회원비밀번호() {
		return 회원비밀번호;
	}
	public void set회원비밀번호(String 회원비밀번호) {
		this.회원비밀번호 = 회원비밀번호;
	}
	public String get회원이름() {
		return 회원이름;
	}
	public void set회원이름(String 회원이름) {
		this.회원이름 = 회원이름;
	}
	public String get회원생년월일() {
		return 회원생년월일;
	}
	public void set회원생년월일(String 회원생년월일) {
		this.회원생년월일 = 회원생년월일;
	}
	public String get회원이메일() {
		return 회원이메일;
	}
	public void set회원이메일(String 회원이메일) {
		this.회원이메일 = 회원이메일;
	}
	public String get회원전화번호() {
		return 회원전화번호;
	}
	public void set회원전화번호(String 회원전화번호) {
		this.회원전화번호 = 회원전화번호;
	}
	public int get회원포인트() {
		return 회원포인트;
	}
	public void set회원포인트(int 회원포인트) {
		this.회원포인트 = 회원포인트;
	}
	public String get회원가입일() {
		return 회원가입일;
	}
	public void set회원가입일(String 회원가입일) {
		this.회원가입일 = 회원가입일;
	}
	
}
