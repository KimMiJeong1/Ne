package com.hk.member.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

//@data 모든 필드를 대상으로 접근자와 설정자가 자동으로 생성된다
@Data
public class Member {
	int mno;
	String email;
	String pwd;
	String mname;
	Date cre_date;
	Date mod_date;
	
	//3가지를 기본 생성해야됨
		//1.getter
		//2.setter ->값을 넣고 뺼 때
		//3.toString->전체 값 확인 할 때
	
	
//mybatis가 알아서  List<Member> members = new ArrayList<Member>();만들어서 통에 담는다
	
	
	
	
}
