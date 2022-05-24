package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/***********************
 * Dao(Data Access Object) DataBase(오라클) 관련된 일을 하는 클래스
 ***********************/

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String password = "webdb";

	// 생성자

	// GS

	// 읿반

	// DB연결 메소드
	private void getConnection() {
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName(driver); // 오라클 접속
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, password); // 주소 , 아이디 , 패스워드
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
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

	// 작가 등록 메소드
	public int authorInsert(AuthorVo authorVo) {
		int count = -1;

		this.getConnection();

		try {

			// SQL 문 준비
			String query = "";
			query += " insert into author";
			query += " values(seq_author_id.nextval, ?, ?)";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	// 작가 삭제 메소드
	public int authorDelete(int authorId) {
		int count = -1;

		getConnection();

		try {

			// SQL 문 준비
			String query = "";
			query += " delete from author";
			query += " where author_id = ?";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

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

	// 업데이트
	public int authorUpdate(AuthorVo authorVo) {
		int count = -1;

		getConnection();

		try {

			// SQL문준비
			String query = "";
			query += " update author";
			query += " set author_name = ?,";
			query += "     author_desc = ?";
			query += " where author_id = ?";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정 되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// select
	public List<AuthorVo> authorSelect() {

		// 리스트

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, password);
			// 3. SQL문준비/ 바인딩/ 실행

			// SQL 문 준비
			String query = "";
			query += " select  author_id,";
			query += "         author_name,";
			query += "         author_desc";
			query += " from author";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행***
			// ResultSet 가져오기
			rs = pstmt.executeQuery();

			// 4.결과처리
			// 리스트로 만들기

			// 반복문으로 Vo 만들기 list에 추가하기
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);

				authorList.add(authorVo);
			}

			// 리스트를 출력해보기
			// System.out.println(authorList.toString());
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
		return authorList;
	}
}
