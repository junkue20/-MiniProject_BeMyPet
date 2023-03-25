package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Adpbrd;

public class AdpbrdServiceImpl implements AdpbrdService {

	// seq_adpbrd_no 불러오기
	@Override
	public Long selectAdpbrdSeq() {
		return mapper.selectAdpbrdSeq();
	}
	
	
	// 동물등록번호 중복체크
	@Override
	public Map<String, Object> insertCheck(Long animal_no) {
		return mapper.insertCheck(animal_no);
	}
	
	// 입양 게시글 추가
	@Override
	public int insertOneAdpbrd(Long animal_no, Adpbrd adpbrd) {
		try {
			int ret1 = mapper.insertOneAdpbrd(adpbrd);
			int ret2 = mapper.insertWaitadp(animal_no, adpbrd);
			if (ret1 == 1 && ret2 == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}

	}

	// 입양 게시글 삭제
	@Override
	public int deleteOneAdpbrd(Long brdNo) {
		return mapper.deleteOneAdpbrd(brdNo);
	}

	// 입양 게시글 전체조회
	@Override
	public List<Map<String, Object>> selectAllAdpbrd(String shelterId) {
		return mapper.selectAllAdpbrd(shelterId);
	}

	// 입양 대기 추가
	@Override
	public int insertWaitadp(Long animal_no, Adpbrd adpbrd) {
		return mapper.insertWaitadp(animal_no, adpbrd);
	}

	// adp_all_view의 컬럼 모두 불러오기 [(animal + waitadp + adpbrd) INNER JOIN 뷰]
	@Override
	public Map<String, Object> selectOneAdpbrd(Long brdNo) {
		return mapper.selectOneAdpbrd(brdNo);
	}

	// 입양 게시글 수정
	@Override
	public int updateOneAdpbrd(Adpbrd adpbrd) {
		return mapper.updateOneAdpbrd(adpbrd);
	}

}
