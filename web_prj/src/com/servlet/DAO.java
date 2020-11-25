package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;


public class DAO {
	
	InputStream reader = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
    Properties prop = new Properties();
    
    public String get(String key) {
    	try {
			prop.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return prop.getProperty(key);
    }
    
	String driver = this.get("driver");
	String url = this.get("url");
	String id = this.get("id");
	String pw = this.get("pw");
	String table = this.get("main_table");

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	String id = "saerom";
//	String pw = "1111";
	
	// Table 정보
	// sr_pjt : 메인 테이블
	
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
			String sql = "SELECT * FROM " + table;
			
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
				String sql = "SELECT * FROM "+ table +" WHERE " + where;
				
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
	
	public void Insert (String org_sentence, String tag_sentence) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(
					"INSERT INTO " + table + "(sen_id, sen_org, sen_tag) "
					+"VALUES (sen_seq.NEXTVAL, ?, ?) ");
			
			pstmt.setString(1, org_sentence);
			pstmt.setString(2, tag_sentence);
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
}