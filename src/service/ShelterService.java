package service;

import connection.MyBatisContext;
import dto.Shelter;
import mapper.ShelterMapper;

public interface ShelterService {

	final ShelterMapper mapper
	= MyBatisContext.getSqlSession().getMapper(ShelterMapper.class);
	
	// 회원가입
	public int shelterJoin(Shelter shelter); 
	
	// 회원가입 아이디 중복확인 
	public Shelter shelterIdCheck(String id); 
	
	// 회원정보 수정
	public int shelterUpdate(Shelter shelter); 

	// 비밀번호 변경(로그인 상태에서)
	public int shelterPwUpdate(Shelter shelter); 
	
	// 회원탈퇴
	public int shelterDelete(Shelter shelter); 
	
	// 로그인
	public Shelter shelterLogin(Shelter shelter); 
	
	// 아이디 찾기 // 이메일을 치면 아이디를 반환
	public String findShelterId(String email); 
	
	// 비밀번호 찾기 // 이메일과, 아이디를 입력하면 새로 설정된 비밀번호를 반환
	public int findShelterPw(Shelter shelter);
	
	// 비밀번호 새로 발급
	public String showShelterpw(Shelter shelter);
}
