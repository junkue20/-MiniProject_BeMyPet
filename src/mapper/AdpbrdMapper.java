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

import dto.Adpbrd;


@Mapper
public interface AdpbrdMapper {

	// 게시글 한 개 수정
	@Update ( value =  {
			" <script> ",
				"  UPDATE adpbrd SET brd_title = #{obj.brdTitle} ",
					
					"  <if test = 'obj.brdContent != null'>  ",
					" , brd_content =#{obj.brdContent}  ",
					"  </if>  ",
						
				"  WHERE brd_no =#{obj.brdNo}  ",
			" </script> "
			} )
			public int updateOneAdpbrd(@Param("obj") Adpbrd adpbrd);
	
	
/*--------------------------------------------------------------------*/

	// 게시글 한 개 삭제
	@Update( value = {
			"  DELETE FROM waitadp  ",
			"  WHERE  ",
				"  brd_no = #{brdNo}  "
	} )
	public int deleteOneAdpbrd(Long brdNo);
	
/*--------------------------------------------------------------------*/
	
	// adpbrd와 waitadp를 추가하기 위한 시퀀스 불러오기
	@Select({" SELECT SEQ_ADPBRD_NO.NEXTVAL FROM DUAL "})
	public Long selectAdpbrdSeq();
	
/*--------------------------------------------------------------------*/
	
	// adpbrd가 추가되었는지 확인하는 기능
	@Select({
		" select * from adp_all_view where animal_no = #{no} "
	})
	public Map<String, Object> insertCheck(@Param("no") Long animal_no);
	
	
/*--------------------------------------------------------------------*/
	
	// adpbrd 한 개 추가
	@Insert( value = { 
			"  INSERT INTO adpbrd(  ",
				"  brd_no, brd_title, brd_content )",
			"  VALUES (  ",
				"  #{obj.brdNo}, #{obj.brdTitle}, #{obj.brdContent})  "
	})
	public int insertOneAdpbrd(@Param("obj") Adpbrd adpbrd);

/*--------------------------------------------------------------------*/
	
	// waitadp 한 개 추가
	@Insert( value = {
			"  INSERT INTO waitadp (  ",
				"  animal_no, brd_no ) ",
			"  VALUES (  ",
				"  #{animalNo}, #{obj.brdNo}  )  "
			
	} )
	public int insertWaitadp (@Param("animalNo") Long animalNo, @Param("obj") Adpbrd adpbrd);
	


/*--------------------------------------------------------------------*/

	// 모든 adpbrd 불러오기
	@Results({
		@Result( column = "BRD_CONTENT", property = "BRD_CONTENT",
				javaType = String.class, jdbcType = JdbcType.CLOB)
	})
	@Select(value = { " select brd_no, brd_title, brd_content, ",
					  " animal_no, animal_species, animal_gender, ",
					  " animal_rescue_spot, animal_limit, ",
					  " brd_hit, brd_sympathy, brd_regdate ",
			 		  " from adp_all_view ",
			 		  " where shelter_id = #{shelterId} ",
			 		  " order by brd_no desc "           
			 		  })
	 public List<Map<String, Object>> selectAllAdpbrd(String shelterId);

/*--------------------------------------------------------------------*/

	// 한 개의 adpbrd 불러오기
	@Results({
		@Result( column = "ANIMAL_CONTENT", property = "ANIMAL_CONTENT",
				javaType = String.class, jdbcType = JdbcType.CLOB),
		@Result( column = "BRD_CONTENT", property = "BRD_CONTENT",
				javaType = String.class, jdbcType = JdbcType.CLOB)
		})
	@Select( value = {
	         "  SELECT *  ",
	         "  FROM  ",
	              "  adp_all_view a ",
	         "  WHERE  ", 
	              "  brd_no = #{brdNo}  ",
	 		  " order by brd_no desc "          
			} )
	  public Map<String, Object> selectOneAdpbrd(Long brdNo);
	
}
