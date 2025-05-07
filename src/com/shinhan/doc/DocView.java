package com.shinhan.doc;

import java.util.List;

public class DocView {
	public static void display(List<DocDTO> doclist) {
		doclist.stream().forEach(doc -> System.out.println(doc));
		System.out.println();
	}
	
	public static void display(DocDTO doc) {
		System.out.println(doc);
		System.out.println();
	}
	
	public static void display(String message) {
		System.out.println("**알림** \n"+ message);
	}
}
