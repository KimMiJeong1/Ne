package com.hk.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.member.dao.MemberDAO;
import com.hk.member.mapper.MemberMapper;
import com.hk.member.vo.Member;

@Service
public class MemberService {
	//파라메터 받을 필요 없다
	
	//spring에서 쓸려면 @Autowired줘야한다 
	@Autowired
	MemberDAO memberDao;
	
	public List<Member> memberList(){
		//member라는 arry의 dto를 줘야하는데 지금은 없으니간 String 나중에는  arryList로
		return memberDao.memberList();
	}
	
	//이제 이 애를 컨트롤러가 부른다
}


//Datasource를 가지고 mapper대신 Dao로 디비에 정보 가져오기
//->Service에서 Dao를 부르고 Dao를 추가 한다음에 List를 부른다(method(memberList())의 결과 값을 리턴)