package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Shelter;

@Mapper
public interface ShelterMapper {

	// 보호소 회원가입
	@Insert( value = {
			" INSERT INTO shelter ",
			    "  ( shelter_id, shelter_pw,  ",
			    "    shelter_email, shelter_name,  ",
			    "    shelter_region, shelter_phone )  ",
			  "  VALUES (  ",
			    "  #{obj.shelterId}, #{obj.shelterPw},  ",
			    "  #{obj.shelterEmail}, #{obj.shelterName},  ",
			    "  #{obj.shelterRegion} ,#{obj.shelterPhone} )  "
	})
	public int shelterJoin(@Param("obj") Shelter s); 

/*------------------------------------------------------------------------------------------*/

	// 아이디 중복확인
	@Select( value = {
		"  SELECT shelter_id ",
		  "  FROM  ",
		    "  shelter  ",
		  "  WHERE  ",
		    "  shelter_id = #{str}  ",
	})
	public Shelter shelterIdCheck(String str);
	
/*------------------------------------------------------------------------------------------*/
	
	// 보호소 로그인 하기
	@Select(value = { 
			"  SELECT * FROM shelter  ",
			  "  WHERE shelter_id = #{shelterId}  ",
				  " AND  ",
			  "  shelter_pw = #{shelterPw}  "
		})
	public Shelter shelterLogin(Shelter s); 

/*------------------------------------------------------------------------------------------*/

	// 보호소 회원정보 수정
	@Update(value = {
		" <script> ",
		" UPDATE shelter SET shelter_email = #{obj.shelterEmail} ",
		
		    " <if test = 'obj.shelterName != null'> ",
			" , shelter_name =#{obj.shelterName}  ",
			" </if> ",
		
			" <if test = 'obj.shelterRegion != null'> ",
			" , shelter_region =#{obj.shelterRegion} ",
			" </if> ",
			
			" <if test = 'obj.shelterPhone != null'> ",
			" , shelter_phone =#{obj.shelterPhone} ",
			" </if> ",
			
		" WHERE shelter_pw =#{obj.shelterPw} AND shelter_id =#{obj.shelterId}",
		" </script> "
	})
	public int shelterUpdate(@Param("obj") Shelter s); 
	
/*------------------------------------------------------------------------------------------*/

	// 보호소 비밀번호 변경하기(로그인상태에서)
	@Update( value = {
		"  UPDATE shelter  ",
	    "  SET  ",
		  "  shelter_pw =#{obj.newPW}  ",
	    "  WHERE  ",
		  "  shelter_pw =#{obj.shelterPw}  ",
	    "  AND  ",
		  "  shelter_id =#{obj.shelterId}  "
	})
	public int shelterPwUpdate(@Param("obj") Shelter obj); 
	
/*------------------------------------------------------------------------------------------*/
	
	// 보호소 아이디 찾기(일단 전체출력)
	@Select( value = { 
		"  SELECT shelter_id ",
        "  FROM  ",
		  "  shelter   ",
	    "  WHERE  ",
	      "  shelter_email = #{email}  " 
	})
	public String findShelterId(@Param("email") String email); 

	
/*------------------------------------------------------------------------------------------*/

	// 회원탈퇴
	@Update( value = {
		"  UPDATE shelter  ",
	    "  SET  ",
	      "  shelter_name = 'null',  ",
	      "  shelter_region = 'null',  ",
	      "  shelter_pw = 'null',  ",
	      "  shelter_email = 'null',  ",
	      "  shelter_phone = 'null'  ",
	    "  WHERE  ",
		  "  shelter_id =#{obj.shelterId}  ",
	    "  AND  ",
		  "  shelter_pw =#{obj.shelterPw}  "
	})
	public int shelterDelete(@Param("obj") Shelter s); 
	
	
/*------------------------------------------------------------------------------------------*/

	//비밀번호 찾기(재설정)
	@Update( value = {
		"  UPDATE shelter  ",
	    "  SET  ",
	      "  shelter_pw =  ",
	    	  "  ( SELECT 100000+ROUND(DBMS_RANDOM.VALUE () * 999999) ",
	    	    "  AS RanNum FROM DUAL )  ",
	  
	    "  WHERE  ",
	  	  "shelter_id = #{obj.shelterId} ",
	    "  AND  ",
	  	  "shelter_email = #{obj.shelterEmail} "
	})
	public int findShelterPw(@Param("obj") Shelter s); 
	
	// 비밀번호 찾기(재설정된 비번 표시)
	@Select( value = {
		" SELECT shelter_pw ",
		  " FROM shelter ",
		  " WHERE shelter_id =#{obj.shelterId} "
	})
	public String showShelterpw(@Param("obj") Shelter s); 
	
}
