package com.kmii.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kmii.dto.MemberDto;

public class MemberDao {
	
	// DB 커넥션 준비
	private String driverName = "com.mysql.jdbc.Driver";   // MYSQL JDBC 드라이버 이름 - mysql사용시 fix값 고정
	private String url = "jdbc:mysql://localhost:3306/jspdb";   // MYSQL이 설치된 서버의 주소(ip)와 연결할 db(스키마) 이름
	private String userName = "root";
	private String password = "12345";	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static final int MEMBER_JOIN_SUCCESS = 1;  // 상수화 - 대문자로 적어준다
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1; 
	public static final int LOGIN_FAIL = 0;
	
	public int insertMember(MemberDto memberDto) {  // 회원 가입 메서드, ()안에 매개변수 잊지말기
		
		String sql = "INSERT INTO membertbl(membrid, memberpw, membername, memberage, memberemail) Values(?,?,?,?,?)";
		
		int sqlResult = 0;
		
		try {
			Class.forName(driverName); 
			conn = DriverManager.getConnection(url, userName, password);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getMemberid()); 
			pstmt.setString(2, memberDto.getMemberpw()); 
			pstmt.setString(3, memberDto.getMembername()); 
			pstmt.setInt(4, memberDto.getMemberage());
			pstmt.setString(5, memberDto.getMemberemail()); 
			
		
			sqlResult = pstmt.executeUpdate();  // 성공하면 sqlResult값이 1로 변환
		 
			
		} catch (Exception e) {
			System.out.println("db 에러 발생"); 
			e.printStackTrace();  //에러 내용 출력
			
		} finally {  // finally : 에러 유무와 상관없이 무조건 실행할 내용 입력 -> 여기선 에러와 상관없이 커넥션 닫기
			try {
				if(pstmt != null){  // stmt가 null이 아니면 닫기 --- conn보다 먼저 닫아야한다
					pstmt.close();
				}
				
				if(conn != null) {  // 커넥션이 null값이 아닐때만 닫기
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(sqlResult ==1) {
			return MEMBER_JOIN_SUCCESS;
		} else {
			return MEMBER_JOIN_FAIL;
		}
	}

	
	public int loginCheck(String id, String pw) {  // 로그인 성공여부 반환 메서드
	
	String sql = "SELECT * FROM membertbl WHERE memberid = ? AND memberpw = ? ";
	
	int sqlResult = 0;
	
	try {
		Class.forName(driverName); 
		conn = DriverManager.getConnection(url, userName, password);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id); 
		pstmt.setString(2, pw); 
		
	
		rs = pstmt.executeQuery();  // 성공하면 sqlResult값이 1로 변환
		
		if(rs.next()) { // 로그인 성공
			sqlResult = LOGIN_SUCCESS;
			
		} else { // 로그인 실패
			sqlResult = LOGIN_FAIL;
		}
	 
		
	} catch (Exception e) {
		System.out.println("db 에러 발생, 로그인 체크 실패!"); 
		e.printStackTrace();  //에러 내용 출력
		
	} finally {  // finally : 에러 유무와 상관없이 무조건 실행할 내용 입력 -> 여기선 에러와 상관없이 커넥션 닫기
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(pstmt != null){  // stmt가 null이 아니면 닫기 --- conn보다 먼저 닫아야한다
				pstmt.close();
			}
			
			if(conn != null) {  // 커넥션이 null값이 아닐때만 닫기
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	
	return sqlResult;  // 1로 반환되면 로그인성공, 0으로 반환되면 로그인실패
		
	}
	
	
	
	
	
	
}
