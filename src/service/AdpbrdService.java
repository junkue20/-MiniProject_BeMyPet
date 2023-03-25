package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Adpbrd;
import mapper.AdpbrdMapper;

public interface AdpbrdService {

	final AdpbrdMapper mapper = MyBatisContext.getSqlSession().getMapper(AdpbrdMapper.class);

	// seq_adpbrd_no 불러오기
	public Long selectAdpbrdSeq();
	
	// 동물등록번호 중복체크
	public Map<String, Object> insertCheck (Long animal_no);

	// 입양 게시글 추가
	public int insertOneAdpbrd(Long animal_no, Adpbrd adpbrd);
	
	// 입양 게시글 삭제
	public int deleteOneAdpbrd(Long brdNo);
	
	// 입양 게시글 전체조회
	public List<Map<String, Object>> selectAllAdpbrd(String shelterId);

	// 입양 대기 추가
	public int insertWaitadp(Long animal_no, Adpbrd adpbrd);

	// adp_all_view의 컬럼 모두 불러오기 [(animal + waitadp + adpbrd) INNER JOIN 뷰]
	public Map<String, Object> selectOneAdpbrd(Long brdNo);
	
	// 입양 게시글 수정
	public int updateOneAdpbrd (Adpbrd adpbrd);
}
