package upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {
	@RequestMapping("/filedownloadlist")
	public ModelAndView downloadlist() {
		//파일 시스템 정보 = 폴더와 파일
		File f = new File("c:/kdt/upload/");
		String [] filearray = f.list(); //위 폴더에 있는 파일 리스트를 가져와라
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("filearray", filearray);
		mv.setViewName("upload/downloadlist");
		return mv;
	}
	
	//파일 다운로드받기
	@RequestMapping("/filedownloadresult")
	public void downloadresult(String filename, HttpServletResponse response ) throws IOException {
		response.setContentType("application/download"); //응답으로 줄 것이 다운로드 받아야할 파일이다.
		response.setContentLength( (int)(new File("c:/kdt/upload/" + filename).length()) ); //다운로드할 파일 길이
		response.setHeader("Content-Disposition", "attachment;filename=\""+ filename +"\"" ); //다운로드할 파일 이름
		
		OutputStream out = response.getOutputStream();
		FileInputStream fin = new FileInputStream(new File("c:/kdt/upload/" + filename));
		FileCopyUtils.copy(fin, out);
		fin.close();
		out.close();
		
	}
	
	

}
