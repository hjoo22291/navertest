package board.spring.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberBoardController {
	
	@Autowired
	@Qualifier("memberServiceImpl")
	MemberService service;
	
	@GetMapping("/boardlogin")
	public String loginform() {
		return "board/loginform";
	}
	
	@PostMapping("/boardlogin")
	public String loginprocess(String memberid, int pw, HttpSession session) { //매개변수선언으로 HttpSession 객체 생성
		//1. c_member id,pw확인 
		MemberDTO dto = service.oneMember(memberid);
		if(dto != null) {
			if(dto.getPw() == pw) {
				//2. HttpSession 객체 "sessionid" : id 저장
				session.setAttribute("sessionid", memberid);
			}
			else { //암호가 다르다
				session.setAttribute("sessionid", "비밀번호가 틀렸습니다.");
			}
		}
		else { //해당 id가 없다
			session.setAttribute("sessionid", "미가입자입니다. 회원가입부터 진행해주세요.");
		}
		
		return "board/start";
	}
	
	@RequestMapping("/boardlogout")
	public String logout(HttpSession session) {
		if(session.getAttribute("sessionid") != null) { //로그인한 세션 정보가 있으면
			session.removeAttribute("sessionid"); //세션정보 없애라 = 로그아웃해라
		}//if
		
		return "board/start";
	}
	
	
}//class




