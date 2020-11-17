package com.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "saerom";
	String pw = "1111";
	
	// Table ����
	// sr_pjt : ���� ���̺�
	// tmp_pjt : ���ε�� �ӽ� ���̺�
	
	public DAO() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<DTO> select() {
		
		ArrayList<DTO> list = new ArrayList<DTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM sr_pjt";
			
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			while (res.next()) {
				int sen_id = res.getInt("sen_id");
				String sen_org = res.getString("sen_org");
				String sen_tag = res.getString("sen_tag");
				
				DTO dto = new DTO(sen_id, sen_org, sen_tag);
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
	
	
	public ArrayList<DTO> where(String where) {
			
			ArrayList<DTO> list = new ArrayList<DTO>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet res = null;
			
			try {
				con = DriverManager.getConnection(url, id, pw);
				String sql = "SELECT * FROM sr_pjt WHERE " + where;
				
				pstmt = con.prepareStatement(sql);
				res = pstmt.executeQuery();
				while (res.next()) {
					int sen_id = res.getInt("sen_id");
					String sen_org = res.getString("sen_org");
					String sen_tag = res.getString("sen_tag");
					
					DTO dto = new DTO(sen_id, sen_org, sen_tag);
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
	

	public void InsertTemp(String sentence_id, String sentence) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement("INSERT INTO tmp_pjt(sen_id, sen_org) "
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
	
public void DeleteTemp() {
		
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
	
	public ArrayList<DTO_TMP> selectTemp() {
			
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
					String sen_org = res.getString("sen_org");
					
					DTO_TMP dto = new DTO_TMP(sen_id, sen_org);
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