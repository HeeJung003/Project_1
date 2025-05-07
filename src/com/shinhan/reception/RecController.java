package com.shinhan.reception;

import java.util.List;
import java.util.Scanner;

import com.shinhan.common.CommonControllerInterface;


public class RecController implements CommonControllerInterface{
	static Scanner sc = new Scanner(System.in);
	static RecService recService = new RecService();

	@Override
	public void execute() {
		boolean isStop = false;
		while (!isStop) {
			menudisplay();
			int job = sc.nextInt();
			switch(job) {	
			case 1 -> {f_selectAllReception();}//접수 모두 보기
			case 2 -> {f_selectReception();}//접수 등록하기
			case 3 -> {f_deleteReception();}//접수 취소
			case 9 -> {isStop = true; System.out.println(); break;}
			}
		}
	}

	//3. 접수 취소
	private static void f_deleteReception() {
		System.out.println();
		System.out.println("==접수 취소 진행 중==");
		System.out.print("취소할 환자 성함 : ");
		String patient_name = sc.next();
		System.out.print("취소할 환자 주민번호 : ");
		String rrn = sc.next();
		System.out.println();
		
		recService.deleteRec(patient_name, rrn);
		RecView.display("접수가 취소되었습니다");
	}

	//2. 접수 
	private static void f_selectReception() {
		int result = recService.reception(makeRec());

		if(result >= 1) {
			RecView.display("접수되었습니다.");
		}
		
	}

	//1. 접수 모두 보기
	private static void f_selectAllReception() {
		System.out.println();
		System.out.println("**접수 현황입니다**");
		List<RecDTO> reclist = recService.selectAllRec();
		RecView.display(reclist);		
	}
	
	private static void menudisplay() {
		System.out.println("==============접수(Rec)==============");
		System.out.println("1. 접수 모두 조회  2. 접수  3. 접수 삭제");
		System.out.println("9. 종료");
		System.out.println("====================================");
		System.out.print("입력 >> ");
	}
	

	private static RecDTO makeRec() {
		System.out.println();
		System.out.println("==접수 진행 중==");
		System.out.print("환자 성함 : ");
		String patient_name = sc.next();
		System.out.print("환자 주민번호 : ");
		String rrn = sc.next();
		System.out.print("희망하는 의사 성함 : ");
		String doctor_name = sc.next();
		System.out.println();
		
		RecDTO recDTO = RecDTO.builder()
				.patient_name(patient_name)
				.rrn(rrn)
				.doctor_name(doctor_name)
				.build();
		return recDTO;
	}
}
