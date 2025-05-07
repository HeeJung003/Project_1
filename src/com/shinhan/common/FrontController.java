package com.shinhan.common;

import java.util.Scanner;

public class FrontController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean isStop = false;
		CommonControllerInterface controller = null;
		
		while(!isStop) {
			System.out.println("------------------------<관리작업 선택>------------------------");
			System.out.println("1. 접수");
			System.out.println("2. 환자 정보 관리");
			System.out.println("3. 의사 정보 관리");
			System.out.println("4. 작업종료\n");
			System.out.print("입력 >>");
			
			int job = sc.nextInt();
			
			switch(job) {
			case 1 -> {controller = ControllerFactory.make(1);}
			case 2 -> {controller = ControllerFactory.make(2);}
			case 3 -> {controller = ControllerFactory.make(3);}
			case 4 -> {isStop = true; continue;}
			default -> {continue;}
			}
			controller.execute();
		}
		sc.close();
	}
}
