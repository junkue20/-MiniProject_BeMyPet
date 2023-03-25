package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dto.Animal;

@Mapper
public interface AnimalMapper {

	// 동물 한 마리 추가
	@Insert( value = {
	"  INSERT INTO animal (  ",
		  "  animal_no, animal_species, animal_gender,  ",
		  "  animal_age, animal_weight, animal_neutering,  ",
		  "  animal_rescue_spot, animal_content,  ",
		  "  animal_limit, SHELTER_ID, animal_rescuedate)  ",
	  "  VALUES ( ",
		  "  seq_animal_no.NEXTVAL,  ",
		  "  #{animalSpecies}, #{animalGender}, ",
		  "  #{animalAge}, #{animalWeight}, ",
		  "  #{animalNeutering}, #{animalRescueSpot},  ",
		  "  #{animalContent},  ",
		  "  #{animalLimit}, #{shelterId}, #{animalRescuedate} ) "
	} )
	public int insertOneAnimal(Animal animal);

/*-----------------------------------------------------------------------------------*/
	
	// 보호동물 정보 업데이트
	@Results({
		   @Result( column = "ANIMAL_CONTENT", property = "animalContent",
		   javaType = String.class, jdbcType = JdbcType.CLOB)
		})
	@Update( value = {
	" <script> ",
		" UPDATE animal ",
			"SET animal_content = #{obj.animalContent} ",
		
		 	" <if test = 'obj.animalSpecies != null'> ",
		 		" , animal_species = #{obj.animalSpecies}  ",
			" </if> ",
			
			" <if test = 'obj.animalGender != null'> ",
				" , animal_gender = #{obj.animalGender} ",
			" </if> ",
			
			" <if test = 'obj.animalAge != null'> ",
				" , animal_age = #{obj.animalAge} ",
			" </if> ",
			
			" <if test = 'obj.animalWeight != null'> ",
				" , animal_weight = #{obj.animalWeight} ",
			" </if> ",
			
			" <if test = 'obj.animalNeutering != null'> ",
				" , animal_neutering = #{obj.animalNeutering} ",
			" </if> ",
			
			" <if test = 'obj.animalState != null'> ",
				" , animal_state = #{obj.animalState} ",
			" </if> ",
			
		" WHERE animal_no = #{obj.animalNo} AND shelter_id = #{obj.shelterId}",
	" </script> "
	} )
	public int updateOneAnimal(@Param("obj") Animal animal);
	
/*-----------------------------------------------------------------------------------*/

	// 동물 한마리만 조회
	@Select( value = { 
	        "  SELECT *  ",
	        "  FROM  ",
	           "  animal  ",
	        "  WHERE  ",
	           "  animal_no = #{no}  ",
 		    " ORDER BY animal_no DESC " 
	        } )
		public Animal selectOneAnimal(Long no);
	
/*-----------------------------------------------------------------------------------*/
	
	// 보호동물 검색
	@Select( value = {
		"  SELECT *  ",
	    "  FROM  ",
		   "  animal  ",
	    "  WHERE  ",
	       "  shelter_id = #{shelterId}  ",
	    "  AND  ",
	       "  animal_No ||  ",
		   "  animal_Species ||  ",
		   "  animal_Gender ||  ",
		   "  animal_Rescue_Spot  ",
	    "  LIKE  ",
		   "  '%' || #{keyword} || '%'  ",
	    " ORDER BY animal_no DESC "                
		} )
	public List<Animal> searchAnimal(@Param("keyword") String keyword, @Param("shelterId") String shelterId);
	
/*-----------------------------------------------------------------------------------*/
	
	// 동물 조회 시 상태 별로 조회하기 기능
	@Select( value = {
		"  SELECT animal_no, ",
			"  ANIMAL_SPECIES, ANIMAL_GENDER,  ",
			"  ANIMAL_AGE, ANIMAL_WEIGHT,  ",
			"  ANIMAL_NEUTERING, ANIMAL_RESCUE_SPOT,  ",
			"  ANIMAL_RESCUEDATE, ANIMAL_CONTENT,  ",
			"  ANIMAL_LIMIT  ",
		"  FROM  ",
			"  ANIMAL  ",
		"  WHERE  ",
		   	"  shelter_id =#{shelterId} ",
		"  AND  ",
		   	"  animal_state =#{stateNo} ",
		"  ORDER BY  ",
			"  animal_no DESC "
	})
	public List<Animal> selectByState(@Param("stateNo") int stateNo, @Param("shelterId") String shelterId);	

}
