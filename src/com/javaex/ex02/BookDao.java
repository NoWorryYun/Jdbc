package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";

	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// Gs

	// 일반
	private void getConnection() {

		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// 3. SQL문준비/ 바인딩/ 실행
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// Book Insert
	public int bookInsert(BookVo bookVo) {
		int count = -1;
		// 0. import java.sql.*;

		getConnection();

		try {

			// SQL 문 준비
			String query = "";
			query += " insert into book";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?)";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

	// Select 가져오기
	public List<BookVo> bookSelect() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		// 0. import java.sql.*;

		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문준비/ 바인딩/ 실행

			// SQL 문 준비
			String query = "";
			query += " select  book_id 책번호,";
			query += "         title 이름,";
			query += "         pubs 퍼블리셔,";
			query += "         to_char(pub_date, 'yy-mm-dd') 발매일,";
			query += "         a.author_id 작가번호,";
			query += "         author_name 이름,";
			query += "         author_desc 작가소개";
			query += " from book b, author a";
			query += " where a.author_id = b.author_id";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("책번호");
				String title = rs.getString("이름");
				String pubs = rs.getString("퍼블리셔");
				String pubDate = rs.getString("발매일");

				AuthorVo authorVo = new AuthorVo();

				authorVo.setAuthorId(rs.getInt("작가번호"));
				authorVo.setAuthorName(rs.getString("이름"));
				authorVo.setAuthorDesc(rs.getString("작가소개"));

				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorVo);
				bookList.add(bookVo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리

			try {

				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return bookList;

	}

	// Delete
	public int bookDelete(int bookId) {
		int count = -1;
		// 0. import java.sql.*;
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문준비/ 바인딩/ 실행

			// SQL 문 준비
			String query = "";
			query += " delete from book";
			query += " where book_id = ?";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}
		return count;

	}

	// Update
	public int bookUpdate(BookVo bookVo) {
		int count = -1;
		// 0. import java.sql.*;
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 주소
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3. SQL문준비/ 바인딩/ 실행
			// SQL문준비
			String query = "";
			query += " update book";
			query += " set title = ?";
			query += " where author_id = ?";
			query += " and pubs = ?";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setInt(2, bookVo.getAuthorId());
			pstmt.setString(3, bookVo.getPubs());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return count;

	}

}
