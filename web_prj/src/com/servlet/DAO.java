package com.servlet;

import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;

public class DAO {
	
	String root = this.getClass().getResource("/..").getPath();
	InputStream reader = getClass().getResourceAsStream(root + "db.properties");
    Properties prop = new Properties();
    
    
    
    
    

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
	
	public void Insert (String org_sentence, String tag_sentence) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			pstmt = con.prepareStatement(
					"INSERT INTO sr_pjt(sen_id, sen_org, sen_tag) "
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