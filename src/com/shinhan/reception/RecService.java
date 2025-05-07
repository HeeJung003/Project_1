package com.shinhan.reception;

import java.util.List;

public class RecService {
	static RecDAO recDAO = new RecDAO();
	
	//1. 접수 모두 보기
	public List<RecDTO> selectAllRec() {
		return recDAO.selectAllRec();
	}
		
	//2. 접수하기
	public int reception(RecDTO rec) {
		return recDAO.reception(rec);
	}
	
	//3. 접수 취소
	public int deleteRec(String patient_name, String rrn) {
		return recDAO.deleteRec(patient_name, rrn);
	}
}
