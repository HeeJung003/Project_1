package com.shinhan.doc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocDAO {
	//1. 모든 의사 조회
	public List<DocDTO> selectAll() {
		List<DocDTO> doclist = new ArrayList<DocDTO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from doctorID";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DocDTO doc = makeDoc(rs);
				doclist.add(doc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		} 
		return doclist;
		
	}
	
	//2. 의사 이름으로 조회
	public List<DocDTO> selectByName(String doc_name) {
		List<DocDTO> doclist = new ArrayList<DocDTO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			String checkSql = "select * from doctorID where doctor_name = ? ";
			
			st = conn.prepareStatement(checkSql);
			st.setString(1, doc_name);
			rs = st.executeQuery();
			
			if(!rs.next()) {
				System.out.println("등록되지 않은 의사입니다.");
			}
	
			String sql = "select * from doctorID where doctor_name = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, doc_name);
			rs = st.executeQuery();
			
			
			
			while(rs.next()) {
				DocDTO doc = makeDoc(rs);
				doclist.add(doc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		return doclist;
	} 
	
	//3. 의사 등록
	public int insertDoc(DocDTO doc) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				insert into doctorID(
				doctor_id, 
				doctor_name, 
				dept) 
				values (?, ?, ?)
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, doc.getDoctor_id());
			st.setString(2, doc.getDoctor_name());
			st.setString(3, doc.getDept());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		return result;
	}
	
	//4. 의사 삭제 
	public int deleteDoc(String doc_name, String dept) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = "delete from doctorID where doctor_name = ? and dept = ? ";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, doc_name);
			st.setString(2, dept);
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		return result;
	}
	
	//5. 의사 정보 수정
	public int updateDoc(DocDTO doc, String oldname, String olddept) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				update doctorId set 
				doctor_name = ?,
				dept = ?
				where doctor_name = ?
					and dept = ?
				""";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, doc.getDoctor_name());
			st.setString(2, doc.getDept());
			st.setString(3, oldname);
			st.setString(4, olddept);
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}
		return result;
	}
	
	private DocDTO makeDoc(ResultSet rs) throws SQLException {
		DocDTO doc = DocDTO.builder()
				.doctor_id(rs.getInt("doctor_id"))
				.doctor_name(rs.getString("doctor_name"))
				.dept(rs.getString("dept"))
				.build();

		return doc;
	}
}