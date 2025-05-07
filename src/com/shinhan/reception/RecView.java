package com.shinhan.reception;

import java.util.List;

public class RecView {
	
	public static void display(List<RecDTO> patlist) {
		patlist.stream().forEach(rec -> System.out.println(rec));
		System.out.println();
	}

	public static void display(RecDTO rec) {
		System.out.println(rec);
		System.out.println();
	}

	public static void display(String message) {
		System.out.println("**알림** \n" + message);
	}
}
