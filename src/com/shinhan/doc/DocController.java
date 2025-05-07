package com.shinhan.doc;

import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;

public class DocController implements CommonControllerInterface{
	static Scanner sc = new Scanner(System.in);
	static DocService docService = new DocService();

	@Override
	public void execute() {
		boolean isStop = false;
		while (!isStop) {
			manudisplay();
			int job = sc.nextInt();
			switch (job) {
			case 1 -> {f_selectDocAll();}
			case 2 -> {f_selectDocByName();}
			case 3 -> {f_insertDoc();}
			case 4 -> {f_deleteDoc();}
			case 5 -> {f_updateDoc();}
			case 9 -> {isStop = true;break;}
			}
		}
	}

	private static void f_updateDoc() {
		System.out.println();
		System.out.println("==의사 정보 수정 중==");
		System.out.print("수정할 의사 이름 : ");
		String oldname = sc.next();
		System.out.print("수정할 의사 진료과 : ");
		String olddept = sc.next();
		System.out.println();
		DocView.display("확인되었습니다");
		System.out.println();
		
		System.out.print("의사 이름 재입력 : ");
		String doctor_name = sc.next();
		System.out.print("의사 진료과 재입력 : ");
		String dept = sc.next();
		
		DocDTO docDTO = DocDTO.builder()
				.doctor_name(doctor_name)
				.dept(dept)
				.build();
		
		docService.updateDoc(docDTO, oldname, olddept);
		System.out.println();
		DocView.display("수정되었습니다");
	}

	private static void f_deleteDoc() {
		System.out.println();
		System.out.println("==의사 정보 삭제 중==");
		System.out.print("삭제할 의사 이름 : ");
		String doctor_name = sc.next();
		System.out.print("삭제할 의사 진료과 : ");
		String dept = sc.next();
		System.out.println();
		docService.deleteDoc(doctor_name, dept);
		DocView.display("삭제되었습니다");
	}

	private static void f_insertDoc() {
		System.out.println();
		System.out.println("==의사 정보 등록 중==");
		docService.insertDoc(makeDoc());
		System.out.println();
		DocView.display("추가되었습니다");
	}

	
	private static void f_selectDocByName() {
		System.out.print("조회할 의사 이름을 입력하세요 : ");
		String doctor_name = sc.next();
		System.out.println();
		System.out.println("**입력하신 의사 정보입니다**");
		List<DocDTO> doclist = docService.selectByName(doctor_name);
		DocView.display(doclist);
	}

	private static void f_selectDocAll() {
		System.out.println();
		System.out.println("병원에 있는 모든 의사의 정보를 조회합니다.");
		List<DocDTO> doclist = docService.selectAll();
		DocView.display(doclist);
	}

	private static void manudisplay() {
		System.out.println("==============의사(Doc) 관리==============");
		System.out.println("1. 의사 모두 조회 | 2. 의사 조회");
		System.out.println("3. 의사 등록 | 4. 의사 정보 삭제 | 5. 의사 정보 수정");
		System.out.println("9. 종료");
		System.out.println("=======================================");
		System.out.print("입력 >> ");
	}
	
	private static DocDTO makeDoc() {
		System.out.print("doctor_id : ");
		int doc_id = sc.nextInt();
		System.out.print("doctor_name : ");
		String doc_name = sc.next();
		System.out.print("dept : ");
		String dept = sc.next();
		
		DocDTO docDTO = DocDTO.builder()
				.doctor_id(doc_id)
				.doctor_name(doc_name)
				.dept(dept)
				.build();
		
		return docDTO;
	}
}