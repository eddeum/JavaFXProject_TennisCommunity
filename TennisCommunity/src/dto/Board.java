package dto;

import dao.MemberDao;

public class Board {

	private int 게시판번호;
	private int 게시판작성자; // 회원번호
	private String 작성자아이디;
	private int 게시판카테고리;
	private String 게시판제목;
	private String 게시판내용;
	private String 이미지경로;
	private int 게시판조회수;
	private String 게시판작성일;
	
	public Board() {
	}

	public Board(int 게시판번호, int 게시판작성자, String 작성자아이디, int 게시판카테고리, String 게시판제목, String 게시판내용, String 이미지경로,
			int 게시판조회수, String 게시판작성일) {
		super();
		this.게시판번호 = 게시판번호;
		this.게시판작성자 = 게시판작성자;
		this.작성자아이디 = 작성자아이디;
		this.게시판카테고리 = 게시판카테고리;
		this.게시판제목 = 게시판제목;
		this.게시판내용 = 게시판내용;
		this.이미지경로 = 이미지경로;
		this.게시판조회수 = 게시판조회수;
		this.게시판작성일 = 게시판작성일;
	}

	public int get게시판번호() {
		return 게시판번호;
	}

	public void set게시판번호(int 게시판번호) {
		this.게시판번호 = 게시판번호;
	}

	public int get게시판작성자() {
		return 게시판작성자;
	}

	public void set게시판작성자(int 게시판작성자) {
		this.게시판작성자 = 게시판작성자;
	}

	public String get작성자아이디() {
		return 작성자아이디;
	}

	public void set작성자아이디(String 작성자아이디) {
		this.작성자아이디 = 작성자아이디;
	}

	public int get게시판카테고리() {
		return 게시판카테고리;
	}

	public void set게시판카테고리(int 게시판카테고리) {
		this.게시판카테고리 = 게시판카테고리;
	}

	public String get게시판제목() {
		return 게시판제목;
	}

	public void set게시판제목(String 게시판제목) {
		this.게시판제목 = 게시판제목;
	}

	public String get게시판내용() {
		return 게시판내용;
	}

	public void set게시판내용(String 게시판내용) {
		this.게시판내용 = 게시판내용;
	}

	public String get이미지경로() {
		return 이미지경로;
	}

	public void set이미지경로(String 이미지경로) {
		this.이미지경로 = 이미지경로;
	}

	public int get게시판조회수() {
		return 게시판조회수;
	}

	public void set게시판조회수(int 게시판조회수) {
		this.게시판조회수 = 게시판조회수;
	}

	public String get게시판작성일() {
		return 게시판작성일;
	}

	public void set게시판작성일(String 게시판작성일) {
		this.게시판작성일 = 게시판작성일;
	}
	
	
	
}