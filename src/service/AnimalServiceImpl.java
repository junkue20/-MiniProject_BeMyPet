package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Animal;

public class AnimalServiceImpl implements AnimalService{

	// 보호 동물 추가
	@Override
	public int insertOneAnimal(Animal animal) {
		return mapper.insertOneAnimal(animal);
	}
	
	// 보호 동물 수정
	@Override
	public int updateOneAnimal(Animal animal) {
		return mapper.updateOneAnimal(animal);
	}

	// 동물 조회 시 상태 별로 조회하기 기능
	@Override
	public List<Animal> selectByState(int stateNo, String shelterId) {
		return mapper.selectByState(stateNo, shelterId);
	}
	
	// 동물 한 마리만 조회
	@Override
	public Animal selectOneAnimal(Long no) {
		return mapper.selectOneAnimal(no);
	}
	
	// 검색 시 보호 동물 조회
	@Override
	public List<Animal> searchAnimal(String keyword, String shelterId) {
		return mapper.searchAnimal(keyword, shelterId);
	}


}
