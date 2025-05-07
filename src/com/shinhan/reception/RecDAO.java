package com.shinhan.reception;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.doc.DBUtil;

public class RecDAO {
	//1. 접수 모두 보기
	public List<RecDTO> selectAllRec() {
		List<RecDTO> reclist = new ArrayList<RecDTO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet  rs = null;
		String sql = """
				select reg_id, 
				recp_time,
				pat_name, 
				rrn, 
				doc_name 
				
				from reception
				order by reg_id desc
				""";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				RecDTO rec = makeRec(rs);
				reclist.add(rec);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return reclist;
	}

	private RecDTO makeRec(ResultSet rs) throws SQLException {
		RecDTO rec = RecDTO.builder()
				.reception_time(rs.getDate("recp_time"))
				.patient_name(rs.getString("pat_name"))
				.rrn(rs.getString("rrn"))
				.doctor_name(rs.getString("doc_name"))
				.build();
		return rec;
	}
	
	//2. 접수하기
	public int reception(RecDTO rec) {		
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String checkSql = "select * from patientID where patient_name = ? and rrn = ? ";
			
			st = conn.prepareStatement(checkSql);
			st.setString(1, rec.getPatient_name());
			st.setString(2, rec.getRrn());
			rs = st.executeQuery();
			
			if(!rs.next()) {
				System.out.println("아직 등록되지 않은 환자입니다.\n");
				return 0;
			}
			
			String checkSql2 = "select * from doctorID where doctor_name = ? ";
			
			st = conn.prepareStatement(checkSql2);
			st.setString(1, rec.getDoctor_name());
			rs = st.executeQuery();
			
			if(!rs.next()) {
				System.out.println("등록되지 않은 의사입니다.\n");
				return 0;
			}
	
			Date currentDate = new Date(System.currentTimeMillis());
			
		String sql = """
				insert into reception (reg_id, recp_time, pat_name, rrn, doc_name) 
				values (reg_id.nextval, ?, ?, ?, ?)
				""";
	
			st = conn.prepareStatement(sql);
			
			st.setDate(1, currentDate);
			st.setString(2, rec.getPatient_name());
			st.setString(3, rec.getRrn());
			st.setString(4, rec.getDoctor_name());
			
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}			
		return result;
	}
	
	
	//3. 접수 취소
	public int deleteRec(String patient_name, String rrn) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		String sql = """
				delete from reception 
				where pat_name = ? and rrn = ?
				""";	
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, patient_name);
			st.setString(2, rrn);
		
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}			
		return result;
	}
}
