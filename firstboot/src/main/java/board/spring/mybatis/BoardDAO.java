package board.spring.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardDAO {
	//글쓰기
	public int insertBoard(BoardDTO dto);
	
	//전체 글 갯수
	public int getTotalBoard();
	
	//페이징처리
	public List<BoardDTO> boardPagingList(ArrayList limit);
	
	//선택된 게시물 조회수 +1
	public int updateViewcnt(int seq);
	
	//선택된 게시물 조회
	public BoardDTO getDetail(int seq);
	
	//게시물 삭제
	public int deleteBoard(int seq);
	
	//게시물 수정
	public int updateBoard(BoardDTO dto);
	
	//게시물 검색
	public List<BoardDTO> searchList(HashMap<String, String> map);
	
	//검색결과 글 갯수
	public int getSearchBoard(HashMap map);
	
	//테이블 조인해서 조회
	public BoardMemberDTO boardWriterInform(int seq);
}
