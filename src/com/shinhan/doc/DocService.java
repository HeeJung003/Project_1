package com.shinhan.doc;

import java.util.List;

public class DocService {
	static DocDAO docDAO = new DocDAO();

	//1. 모두 조회
	public List<DocDTO> selectAll() {
		return docDAO.selectAll();
	}

	//2. 의사 이름으로 조회
	public List<DocDTO> selectByName(String doc_name) {
		return docDAO.selectByName(doc_name);
	}

	//3. 의사 등록
	public int insertDoc(DocDTO doc) {
		return docDAO.insertDoc(doc);
	}

	//4. 의사 삭제
	public int deleteDoc(String doc_name, String dept) {
		return docDAO.deleteDoc(doc_name, dept);
	}

	//5. 의사 정보 수정
	public int updateDoc(DocDTO doc, String oldname, String olddept) {
		return docDAO.updateDoc(doc, oldname, olddept);
	}
}