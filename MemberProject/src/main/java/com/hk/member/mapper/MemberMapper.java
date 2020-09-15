package com.hk.member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hk.member.vo.Member;

//Mtbatis scan해서 알아서 해주는데 개념상 넣어준다
@Repository
public interface MemberMapper {

	
	//CRUD를 구현한다
	//dao에서 5가지를 구현한다
	//1.전체 list
	//@Selet("Select*from members")여러개면 가독성이 떨어진다
	//메소드명이 MemberMapper.xml에 id로 memberList로 저장을 해놔서
	//service,MemberMapper(Repository),MemberMapper.xml 이렇게 3개가 연결됨
	//그래서 꼭 통일 그리고 이 메소드 결과값을 MemberService를 불러 거기에 값을 주고 return값을 컨트로럴에 던져준다
	public List<Member> memberList();
	
	
	//2.한개만 보고
	//3.한개만 insert하기
	//4.수정
	//5.삭제
	//이렇게 해주면 controller,service에 붙였고 service가  리턴해ㅅ서  mybatis 불러서 데이터소스를 가져와서 준다
}
