package upload;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	//업로드폼 화면 리턴
	@GetMapping("/fileupload")
	public String uploadform() {
		return "upload/uploadform";
	}
	
	//업로드 파일 처리 결과 화면 리턴
	@PostMapping("/fileupload")
	public ModelAndView uploadresult(UploadDTO dto) throws IOException {
		String savePath = "c:/kdt/upload/"; //프로젝트 외부 경로 - 기본적 브라우저 url 접근 공간 x
		//프로젝트 외부 경로 - http://localhost:8071/first/upload/xxxx
		
		//System.out.println(UUID.randomUUID().toString());//랜덤한 문자들 생성. 이름 중복을 피하는 용도로 쓸 수 있음
		
		MultipartFile file1 = dto.getFile1();
		String newFilename1 = null;
		if(!file1.isEmpty()) {
			//kdt-upload 폴더에 전송된 파일 저장하기
			String originalname1 = file1.getOriginalFilename(); //전송된 파일 이름
			//전송한 파일 이름이 pom.xml 일때 - pom과 확장자부분.xml 을 분리해서 그 사이에 랜덤문자를 넣어 이름 중복 피하도록 만들어주기
			String beforeext1= originalname1.substring(0, originalname1.indexOf(".")); //pom
			String ext1 = originalname1.substring(originalname1.indexOf(".")); //.xml
			newFilename1 = beforeext1 + "(" + UUID.randomUUID().toString() + ")" + ext1 ;
			
			file1.transferTo(new java.io.File(savePath + newFilename1)); 
			//savePath 자리에 newFilename1 이름의 빈파일 하나 만들어서 file1을 저장해라
		}
		
		MultipartFile file2 = dto.getFile2();
		String newFilename2 = null;
		if(!file2.isEmpty()) {
			String originalname2 = file2.getOriginalFilename(); 
			String beforeext2= originalname2.substring(0, originalname2.indexOf("."));
			String ext2 = originalname2.substring(originalname2.indexOf("."));
			newFilename2 = beforeext2 + "(" + UUID.randomUUID().toString() + ")" + ext2 ;
			file2.transferTo(new java.io.File(savePath + newFilename2)); 
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("uploadresult", savePath + "에 "+ newFilename1 + "파일을 저장했습니다.");
		mv.addObject("uploadresult2", savePath + "에 "+ newFilename2 + "파일을 저장했습니다.");
		mv.setViewName("upload/uploadresult");
		return mv;
	}
}
