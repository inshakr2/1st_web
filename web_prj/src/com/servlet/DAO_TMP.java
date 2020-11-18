package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_TMP {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "saerom";
	String pw = "1111";
	
	// Table 정보
	// tmp_pjt : 업로드용 임시 테이블
	
	public DAO_TMP() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Insert(String sentence_id, String sentence) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement("INSERT INTO tmp_pjt(sen_id, sen_tag) "
						+"VALUES (?, ?) ");
			
			pstmt.setString(1, sentence_id);
			pstmt.setString(2, sentence);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void Delete() {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DriverManager.getConnection(url, id, pw);
				pstmt = con.prepareStatement("DELETE FROM tmp_pjt"
											+ " WHERE sen_id > ?");
				pstmt.setString(1, "0");
				pstmt.executeUpdate();
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	
	public void DeleteIndex(String sentence_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement("DELETE FROM tmp_pjt"
										+ " WHERE sen_id = ?");
			pstmt.setString(1, sentence_id);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void Update(String sentence_id, String sentence) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement("UPDATE tmp_pjt"
										+ " SET sen_tag = ?"
										+ " WHERE sen_id = ?");
			pstmt.setString(1, sentence);
			pstmt.setString(2, sentence_id);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public ArrayList<DTO_TMP> select() {
			
			ArrayList<DTO_TMP> list = new ArrayList<DTO_TMP>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;
			
			try {
				con = DriverManager.getConnection(url, id, pw);
				String sql = "SELECT * FROM tmp_pjt ORDER BY sen_id";
				
				pstmt = con.prepareStatement(sql);
				res = pstmt.executeQuery();
				while (res.next()) {
					int sen_id = res.getInt("sen_id");
					String sen_tag = res.getString("sen_tag");
					
					DTO_TMP dto = new DTO_TMP(sen_id, sen_tag);
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(res != null) res.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			return list;
		}
		
	
}