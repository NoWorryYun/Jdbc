package com.javaex.ex02;

public class BookVo {

	// 필드
	private int bookId;
	private String title;
	private String pubs;
	private int authorId;
	private String pubDate;
	private AuthorVo authorVo;

	// 생성자
	public BookVo() {
	}

	public BookVo(int bookId, String title, String pubs, String pubDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
	}

	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId, AuthorVo authorVo) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.authorId = authorId;
		this.pubDate = pubDate;
		this.authorVo = authorVo;
	}
	public BookVo(int bookId, String title, String pubs, String pubDate, AuthorVo authorVo) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorVo = authorVo;
	}

	// GS

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getPubs() {
		return pubs;
	}

	public int getAuthorId() {
		return authorId;
	}

	public String getPubDate() {
		return pubDate;
	}

	public AuthorVo getAuthorVo() {
		return authorVo;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setAuthorVo(AuthorVo authorVo) {
		this.authorVo = authorVo;
	}

	

	// 일반

	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", authorId=" + authorId
				+ ", pubDate=" + pubDate + ", authorVo=" + authorVo + "]";
	}


	
	
}
