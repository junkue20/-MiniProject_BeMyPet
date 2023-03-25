package service;

import connection.MyBatisContext;
import dto.Shelter;

public class ShelterServiceImpl implements ShelterService{

	// 보호소 회원가입
	@Override
	public int shelterJoin(Shelter shelter) {
		return mapper.shelterJoin(shelter); 
	}

	// 보호소 id 중복체크
	@Override
	public Shelter shelterIdCheck(String id) {
		return mapper.shelterIdCheck(id); 
	}

	// 보호소 회원정보 변경
	@Override
	public int shelterUpdate(Shelter shelter) {
		return mapper.shelterUpdate(shelter);
	}

	// 보호소 pw 변경(재설정)
	@Override
	public int shelterPwUpdate(Shelter shelter) {
		return mapper.shelterPwUpdate(shelter); 
	}

	// 보호소 탈퇴
	@Override
	public int shelterDelete(Shelter shelter) {
		return mapper.shelterDelete(shelter); 
	}

	// 보호소 로그인
	@Override
	public Shelter shelterLogin(Shelter shelter) {
		try {
			return mapper.shelterLogin(shelter); 
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
		
	}

	// 보호소 id 찾기
	@Override
	public String findShelterId(String email) {
		return mapper.findShelterId(email);
	}

	// 보호소 비밀번호 찾기
	@Override
	public int findShelterPw(Shelter shelter) {
		return mapper.findShelterPw(shelter);
	}

	// 비밀번호 새로 발급
	@Override
	public String showShelterpw(Shelter shelter) {
		return mapper.showShelterpw(shelter);
	}

}
