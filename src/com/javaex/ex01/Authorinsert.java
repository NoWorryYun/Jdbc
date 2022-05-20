package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Authorinsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		try {
			// 1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");		//오라클 접속
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		//주소
			conn = DriverManager.getConnection(url, "webdb", "webdb");		//주소 , 아이디 , 패스워드
			// 3. SQL문준비/ 바인딩/ 실행
			
			//SQL 문 준비
			String query = "";
			query += " insert into author";
			query += " values(seq_author_id.nextval, ?, ?)";
			System.out.println(query);
		
			//바인딩
			pstmt = conn.prepareStatement(query);	//문자열을 쿼리로 만들기
			pstmt.setString(1, "이문열");				//query의 ? 위치, 내용 // 순서 중요
			pstmt.setString(2, "경북 영양");
			//String str = "insert into author values(seq_author_id.nextval, '강풀', '온라인 만화가 1세대')";
			
			
			//실행
			int count = pstmt.executeUpdate();			//쿼리문 실행  -->  성공갯수 return
			
			// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패-" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			
			try {
				/*
				if (rs != null) {
					rs.close();
				}
				*/
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

	}

}
