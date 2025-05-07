package com.shinhan.pat;

import java.time.LocalDate;
import java.util.List;

public class PatService {
	static PatDAO patDAO = new PatDAO();
	
	//1. 환자 모두 조회
	public List<PatDTO> selectAllPat() {
		return patDAO.selectAllPat();
	}
		
	//2. 환자 이름으로 조회
	public List<PatDTO> selectByName(String patient_name) {
		return patDAO.selectByName(patient_name);
	}

	//3. 환자 등록
	public int insertPat(PatDTO pat) {
		String rrn = pat.getRrn();
		String birth = rrn.substring(0, 6);

		String gen = String.valueOf(rrn.charAt(7));
		String gender = "";
		if (gen.equals("1") || gen.equals("3")) {
			gender = "M";
		} else if (gen.equals("2") || gen.equals("4")) {
			gender = "F";
		}

		int age = getAmericanAge(birth)-2000;
		pat.setBirth(birth);
		pat.setGender(gender);
		pat.setAge(age);

		return patDAO.insertPat(pat);
	}
	
	//4. 환자 삭제
	public int deletePat(String patient_name, String rrn) {
		return patDAO.deletePat(patient_name, rrn);
	}
	
	//환자 정보 수정
	public int updatePat(PatDTO pat, String oldname, String oldrrn) {
		String rrn = pat.getRrn();
		String birth = rrn.substring(0, 6);

		String gen = String.valueOf(rrn.charAt(7));
		String gender = "";
		if (gen.equals("1") || gen.equals("3")) {
			gender = "M";
		} else if (gen.equals("2") || gen.equals("4")) {
			gender = "F";
		}

		int age = getAmericanAge(birth)-2000;
		
		pat.setBirth(birth);
		pat.setGender(gender);
		pat.setAge(age);

		return patDAO.updatePat(pat, oldname, oldrrn);
	}

	private static int getAmericanAge(String birth) {
		// 오늘 날짜
		LocalDate today = LocalDate.now();
		int todayYear = today.getYear();
		int todayMonth = today.getMonthValue();
		int todayDay = today.getDayOfMonth();

		// 주민등록번호를 통해 입력 받은 날짜
		int year = Integer.parseInt(birth.substring(0, 2));
		int month = Integer.parseInt(birth.substring(2, 4));
		int day = Integer.parseInt(birth.substring(4, 6));

		// 올해 - 태어난년도
		int americanAge = todayYear - year;

		// 생일이 안지났으면 - 1
		if (month > todayMonth) {
			americanAge--;
		} else if (month == todayMonth) {
			if (day > todayDay) {
				americanAge--;
			}
		}
		return americanAge;
	}
}
