package board.spring.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;

	//회원정보조회
	public MemberDTO oneMember(String id) {
		return dao.oneMember(id);
	}
	
	
}//class

