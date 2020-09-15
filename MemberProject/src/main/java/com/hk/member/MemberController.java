 package com.hk.member;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;//이걸 쓸려면 new해서 집어넣어햐는데 @Autowired했으니 찾아서 넣어줘
	
	@Autowired
	ServletContext sc;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	@GetMapping("/member/list") //@RequestMapping method get까지 주는거를 축약하기 위해
	public String memberList(Locale locale, Model model) {
		//컨트롤러 역할
		//1.url 설정 (@RequestMapping, @GetMapping, @postMapping)
		//2.DB관련작업
		//-service
		//-Repository
		//3. 통 틀어서 비지니스로직(덧셈밸셈 등 뭐든지...)
		//4.사용자가 입력한 정보가져오기(ex.예전에는  request.getparameter("name"))
		//5.보관소에 값 저장(예전에는 sc,session,request-->지금은 model)
		//6.jsp 호출-->메소드명하고 일치-->정의(규칙) 한거는 servlet-context.xml
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		//web.xml에서 <listener>가 관장한다?
		model.addAttribute("members", memberService.memberList());
		
		// memberService에서 memberList()를 부르는데
		
		return "memberList";
	}
	
	//파일업로드를 처리할려면 별도의 모듈이 필요하다(commons-io,fileupload) //저장위치는 sc 그래서 sc를 쓰기위에 @Autowired로 ServletContext sc선언 이 정보를 스타트업하면서 불러왔다 나는 서블릿은 아니지만 너의 정보를 가져다가 쓸게
	@RequestMapping(value = "/upload", method = RequestMethod.POST , headers = "content-type=multipart/*") //post headers는 외우기(처음 파일올릴 때는 이런식으로 올린다)
	//@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile,Model model) {
		   logger.info("### upload");
		      logger.info("실제 파일이름은 ? " + multipartFile.getOriginalFilename());
		      File targetFile = new File(sc.getRealPath("/resources/fileupload/") + multipartFile.getOriginalFilename()); //path가 달라질수 있으니 sc.getRealPath확인 톰캣이 어디에 저장하지몰라서 지정 ->만약 c에 저장하라고 따로 지정하면 톰캣이 못읽어 화면에 띄울 수 없다
		      
		      logger.info("파일 저장위치는 :  " + targetFile);
		      try {
		         InputStream fileStream = multipartFile.getInputStream();
		         FileUtils.copyInputStreamToFile(fileStream, targetFile);
		      } catch (IOException e) {
		         FileUtils.deleteQuietly(targetFile);
		         e.printStackTrace();
		      }

		
		//여기까지는 외우기 저장하기위해서 실제 path가 다르니 찾아서 저장해줄게 ....
		      // 실제 디렉토리와 URL은 다르다.. 
		      // URL은 http://localhost:9999/resources/fileupload/실제파일명
		      // model에 담아서 jsp에서 img로 출력 
		      
		      model.addAttribute("imgSrc", "/resources/fileupload/" + multipartFile.getOriginalFilename()); //multipartFile에사용자가 선택한 파일이름이 저장되어있다
		      return "upload";

		 //사용자가 선택한 파일이름이 저장되어있다
		
	//jsp에서 받는 거니깐 jsp에서 실행
	}

}
