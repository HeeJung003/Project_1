package com.shinhan.pat;

import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;

public class PatController implements CommonControllerInterface {
	static Scanner sc = new Scanner(System.in);
	static PatService patService = new PatService();

	@Override
	public void execute() {
		boolean isStop = false;
		PatDTO pat = new PatDTO();
		while (!isStop) {
			menudisplay();
			int job = sc.nextInt();
			switch(job) {	
			case 1 -> {f_selectAllPatient();}//환자 모두 조회
			case 2 -> {f_selectPatByName();}//특정 환자 조회
			case 3 -> {f_insertPatient(pat);}//추가
			case 4 -> {f_deletePatient();}//삭제
			case 5 -> {f_updatePatient();}//수정
			case 9 -> {isStop = true; break;}
			}
		}
	}

	//5. 환자 정보 수정
	private static void f_updatePatient() {
		System.out.println("==환자 정보 수정 중==");
		System.out.print("수정할 환자 이름 : ");
		String oldname = sc.next();
		System.out.print("수정할 환자 주민번호 : ");
		String oldrrn = sc.next();
		PatView.display("확인되었습니다");
		System.out.println();
		System.out.print("환자 이름 재입력: ");
		String patient_name = sc.next();
		System.out.print("환자 주민번호 재입력 : ");
		String rrn = sc.next();
		System.out.print("환자 전화번호 재입력: ");
		String phone = sc.next();
		System.out.print("환자 주소 재입력: ");
		String addr = sc.next();
		
		PatDTO patDTO = PatDTO.builder()
				.patient_name(patient_name)
				.rrn(rrn)
				.phone(phone)
				.addr(addr)
				.build();
		
		patService.updatePat(patDTO, oldname, oldrrn);
		PatView.display("수정이 완료되었습니다.");
	}
	
	//4. 환자 정보 삭제
	private static void f_deletePatient() {
		System.out.println();
		System.out.println("==환자 정보 삭제 중==");
		System.out.print("삭제할 환자의 이름 : ");
		String patient_name = sc.next();
		System.out.print("삭제할 환자의 주민번호 : ");
		String rrn = sc.next();
		patService.deletePat(patient_name, rrn);
		PatView.display("삭제되었습니다.");
	}
	
	
	//3. 환자 등록
	private static void f_insertPatient(PatDTO pat) {
		System.out.println();
		System.out.println("==초진 환자 등록 중==");
		int result  = patService.insertPat(makePat());
		
		if(result > 0) {
			PatView.display("추가되었습니다");
		}

	}
	
	//2. 환자 이름으로 조회
	private static void f_selectPatByName() {
		System.out.print("조회할 환자 이름을 입력하세요 : ");
		String patient_name = sc.next();
		System.out.println();
		System.out.println("**입력하신 환자 정보입니다**");
		List<PatDTO> patlist = patService.selectByName(patient_name);
		PatView.display(patlist);
	}

	//1. 환자 모두 조회
	private static void f_selectAllPatient() {
		System.out.println();
		System.out.println("**환자 정보 입니다**");
		List<PatDTO> patlist = patService.selectAllPat();
		PatView.display(patlist);	
	}

	private static void menudisplay() {
		System.out.println("==============환자(Pat) 관리==============");
		System.out.println("1. 환자 모두 조회 | 2. 환자 조회");
		System.out.println("3. 환자 등록 | 4. 환자 정보 삭제 | 5. 환자 정보 수정");
		System.out.println("9. 종료");
		System.out.println("========================================");
		System.out.print("입력 >> ");
	}
	
	private static PatDTO makePat() {
		System.out.print("patient_id : ");
		int patient_id = sc.nextInt();
		System.out.print("patient_name : ");
		String patient_name = sc.next();
		System.out.print("rrn(000000-000000) : ");
		String rrn = sc.next();
		System.out.print("phone : ");
		String phone = sc.next();
		System.out.print("addr : ");
		String addr = sc.next();
		
		PatDTO patDTO = PatDTO.builder()
				.patient_id(patient_id)
				.patient_name(patient_name)
				.rrn(rrn)
				.phone(phone)
				.addr(addr)
				.build();

		return patDTO;
	}
}
