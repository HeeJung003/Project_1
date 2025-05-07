package com.shinhan.common;

import com.shinhan.doc.DocController;
import com.shinhan.pat.PatController;
import com.shinhan.reception.RecController;

public class ControllerFactory {
	
	public static CommonControllerInterface make(int business) {
		CommonControllerInterface controller = null;
		
		switch(business) {
		case 1 -> {controller = new RecController();}
		case 2 -> {controller = new PatController();}
		case 3-> {controller = new DocController();}
		}
		return controller;
	}

}
