package board.spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	@Qualifier("boardServiceImpl")
	BoardService service;
	
	@RequestMapping("/")
	public String start() {
		return "board/start";
	}
	
	@GetMapping("/boardwrite")
	public String writeform() {
		return "board/writingform";
	}
	
	@PostMapping("/boardwrite")
	public ModelAndView writeprocess(BoardDTO dto) {
		int insertcount = service.insertBoard(dto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertcount", insertcount);
		mv.setViewName("board/writeresult");
		return mv;
	}
	
	@RequestMapping("/boardlist")
	public ModelAndView boardlist(@RequestParam(value="page", required=false, defaultValue="1" ) int page) {
		//전체 게시물 갯수 가져와서 몇페이지까지 가져올수있는지 (1페이지당 4개씩 보여주도록 할때)
		int totalBoard = service.getTotalBoard();
		
		//page번호 해당 게시물 4개 리스트 조회
		int limitcount = 4;
		int limitindex = (page-1)*limitcount;
		
		ArrayList mypage = new ArrayList();
		mypage.add(limitindex);
		mypage.add(limitcount);
		List<BoardDTO> list = service.boardPagingList(mypage);
		
//		//강사님 풀이
//		int limit [] = new int[2];
//		limit[0] = limitindex;
//		limit[1] = limitcount;
//		List<BoardDTO> list = service.boardList(limit);		

		ModelAndView mv = new ModelAndView();
		mv.addObject("totalBoard", totalBoard );
		mv.addObject("boardPagingList", list );
		mv.setViewName("board/list");
		return mv;	
	}	
	
	//선택된 게시물 조회&조회수증가
	@RequestMapping("/boarddetail")
	public ModelAndView boarddetail(int seq) {
		ModelAndView mv = new ModelAndView();
		BoardDTO dto = service.updateViewcntAndGetDetail(seq);
		mv.addObject("detaildto", dto);
		mv.setViewName("board/detail");
		return mv;
	}
	
	//게시물 삭제
	@RequestMapping("/boarddelete")
	public String boarddelete(int seq){
		service.deleteBoard(seq);
		//return "board/list"; //list.jsp로 이동(모델 전달 없다 - Nullpointer..500오류 발생함)
		return "redirect:/boardlist"; //boardlist에 매핑된 메소드 실행 호출 - redirect : 컨트롤러에서 해당 url에 매핑된 곳으로 가라.
	}
	
	//게시물 수정
	@RequestMapping("/boardupdate")
	public String boardupdate(BoardDTO dto){
		service.updateBoard(dto);
		return "redirect:/boardlist";
	}
	
	//검색
	@RequestMapping("/boardsearchlist")
	public ModelAndView boardsearchlist(
			@RequestParam(value="item", required=false, defaultValue="all") String item,
			@RequestParam(value="searchword", required=false, defaultValue="") String searchword, 
			@RequestParam(value="page", required=false, defaultValue="1") int page
			) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> map = new HashMap();
		if(item.equals("all")) {
			item=null;
		}
		map.put("colname", item);
		map.put("colvalue", "%"+searchword+"%");
		//페이징처리 추가하려면
//		map.put("limitindex", (page-1)*4);
//		map.put("limitcount", page);		
		List<BoardDTO> searchlist = service.searchList(map);
		int searchcount = service.getSearchBoard(map);
		mv.addObject("boardList", searchlist); //이미 구현해놓은것이 있으니까 그 변수에 그냥 새로운 값 넣어주면됨.
		mv.addObject("totalBoard", searchcount);
		mv.setViewName("board/searchlist");
		return mv;
	}
	
	//테이블 조인해서 조회하기
	@RequestMapping("/boardwriterinform")
	public ModelAndView boardwriterinform(int seq) {
		BoardMemberDTO writerdto = service.boardWriterInform(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("writerdto", writerdto);
		mv.setViewName("board/writerinform");
		return mv;
	}
	
	
	
}//class




