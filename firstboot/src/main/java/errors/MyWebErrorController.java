package errors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
@Controller
public class MyWebErrorController implements ErrorController {
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);//에러코드가 몇번인지 알려줌
		System.out.println("오류코드 = " + status);
		System.out.println("오류메시지 = " + request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
		System.out.println("파라미터 정보 = " + request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING));
		System.out.println("오류파일 = " + request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
		
		//int statusCode = Integer.parseInt(status.toString());
		return "errors/"+ status.toString(); //view지정. ex) errors/404 
		//return "error";
		
	}
}
