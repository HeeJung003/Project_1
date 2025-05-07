package com.shinhan.reception;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class RecDTO { 
	private String patient_name; //환자 이름   
	private Date reception_time; //접수 시간
	private String rrn; //주민번호
	private String  doctor_name; //의사 이름     
	
	
	@Override
	public String toString() {
		return "환자 이름 = " + patient_name + ", 접수 날짜 = " + reception_time + ", 주민번호= " + rrn
				+ ", 담당의 = " + doctor_name;
	}    
}
