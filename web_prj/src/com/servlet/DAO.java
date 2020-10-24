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
	
}
