package com.hk.member;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hk.member.service.MemberService;
import com.hk.member.vo.Member;

//Rest를 지원 ->화면이 필요없다 그래서 리턴하는 방식이 다르다 클라이언트에게 text,json로 주던지..(화면을 너가 알아서 만들어라 데이터만 줄게 근데 최조 1회는 쥰다)->그전에는 html,css,js로 만들어서 줬는데

//@RestController만쓰면 데이터만 주겠다 @Controller로 주면 데이터와 화면을 같이 주겠다 ->리슨하고 있는 url이 필요한데  톰겟이 기다리고ㅓ있는거다 클라이언트요청에 따라 맞는것을 준다 얘는 @RequestMapping메소드에다가 쓸수 있고ㅗ class에도 쓸수 있다
@RestController
@RequestMapping(value="/member/rest",produces="text/plain;charset=UTF-8")//url을 기준으로 url을 요청해주면 값을줄꺼야 text/plain(그냥text)형식으로
public class RestMemberController {
	 private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);

	   @Autowired
	   MemberService memberService; //dao방식인지 mybatis인지 규칙만 맞으면 아무거나 사용해도된다
	   
	   @GetMapping("/list")//url들이 붙는다
	   public String memberRestList(Locale locale, Model model) {
	      logger.info("/member/rest/list ----------");
	      return "이건 서버에서 보내준 /member/rest/list";//클라이언트에 이걸 보내준다 받아서 나중에 클라이언트가 text로 할지 html로 보여줄지결정
	   }
	   
	   //에러가 나면 먼ㄴ저 개발자도구 Header에서 보면 response headers(서버가 보내준다)에서 content-type을 확인 
	   //json을 쓸려면 별도의 모듈이 필요하다(미디어타입을 제대로 추가해야한다) ->jackson이 필요(jackson databind를 주면 jackson관련 연결 모둘도 같이 다운받아준다)
	   @GetMapping(path = "/listJson", produces = MediaType.APPLICATION_JSON_VALUE)//xml의 축약한게 json방식이다 MediaType.APPLICATION_JSON_VALUE상수니깐 그냥 외우기
	   public List<Member> memberRestListJson(Locale locale, Model model) { //우리는 그전에 for으로 받아서 찍어봤다
	      logger.info("/member/rest/listJson ----------");
	      return memberService.memberList(); 
	   }   
	   
	   //MediaType을 xml로 바꿀려면 produces 컨트롤러 메소드는이를 생성 할 수있는 미디어 유형으로 선언해야한다 ->데이터포맷에 맞는 묘듈을 설치
	   @GetMapping(path = "/listXml", produces = MediaType.APPLICATION_XML_VALUE)
	   public List<Member> memberRestListXml(Locale locale, Model model) { 
	      logger.info("/member/rest/listJson ----------");
	      return memberService.memberList(); 
	   }
	   
	   
	   //집에서 확일할 떄 먼저 addJson?name=aaa&pwd=1111 url주소창에 get방식으로 확인해보기 아직 add코드가 구현되지 않아서 ->화면에 뿌려주는거
	   @GetMapping(path="/addJson" , produces = MediaType.APPLICATION_JSON_VALUE)
	   public List<Member> memberRestAddJSon(@RequestParam("name") String name , @RequestParam("pwd") String pwd) { 
		   //니가 나한테name이라는 이름으로 form에주면 내가 알아서 꺼낼게 (@RequestParam("name")을 꺼네서 sring name에 넣어라 ->알아서 add해준다 - 
		   //전에는 add로 작성해서 가져왔다?
	     //그전에는 String name=requst.getParameter("name");
		   
		  logger.info("-----------------");
	      logger.info("Client에서 보내온 값은 === " + name + ":::" + pwd);
	      
	      return memberService.memberList();
	   }
}
