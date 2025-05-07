package com.shinhan.pat;

import java.util.List;

public class PatView {

	public static void display(List<PatDTO> patlist) {
		patlist.stream().forEach(pat -> System.out.println(pat));
		System.out.println();
	}
	
	public static void display(PatDTO pat) {
		System.out.println(pat);
		System.out.println();
	}
	
	public static void display(String message) {
		System.out.println();
		System.out.println("**알림** \n"+ message);
	}
}
