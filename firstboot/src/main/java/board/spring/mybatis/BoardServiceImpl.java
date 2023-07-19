package board.spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;
	
	//글쓰기
	public int insertBoard(BoardDTO dto) {
		return dao.insertBoard(dto);
	}
	
	//전체글갯수
	public int getTotalBoard() {
		return dao.getTotalBoard();
	}
	
	//페이징처리
	public List<BoardDTO> boardPagingList(ArrayList limit) {
		return dao.boardPagingList(limit);
	}


//	//페이징처리 - 강사님 풀이
//	public List<BoardDTO> boardList(int [] limit) {
//		return dao.boardList(limit);
//	}
	
	//선택된 게시물 조회수 증가&조회
	public BoardDTO updateViewcntAndGetDetail(int seq) {
		int updaterow = dao.updateViewcnt(seq);
		return dao.getDetail(seq);
	}
	
	//게시물 삭제
	public int deleteBoard(int seq) {
		return dao.deleteBoard(seq);
	}
	
	//게시물 수정
	public int updateBoard(BoardDTO dto) {
		return dao.updateBoard(dto);
	}
	
	//게시물 검색
	public List<BoardDTO> searchList(HashMap<String, String> map){
		return dao.searchList(map);
	}
	
	//검색 결과 글 갯수
	public int getSearchBoard(HashMap map) {
		return dao.getSearchBoard(map);
	}
	
	//테이블 조인해서 조회
	public BoardMemberDTO boardWriterInform(int seq) {
		return dao.boardWriterInform(seq);
	}
}
