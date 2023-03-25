package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Animal;
import mapper.AnimalMapper;

public interface AnimalService {

	final AnimalMapper mapper
		= MyBatisContext.getSqlSession().getMapper(AnimalMapper.class);
	
	
	
	// 보호 동물 추가
	public int insertOneAnimal(Animal animal);
	
	// 보호 동물 수정
	public int updateOneAnimal(Animal animal);
	
	// 보호 동물 한마리만 조회
	public Animal selectOneAnimal(Long no);
	
	// 동물 조회 시 상태 별로 조회하기 기능
	public List<Animal> selectByState(int stateNo, String shelterId);
	
	// 검색 시 보호 동물 조회
	public List<Animal> searchAnimal(String keyword, String shelterId);
	
	

}
