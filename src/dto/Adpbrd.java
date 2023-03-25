package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Adpbrd {

	  private Long 		brdNo; 			// 게시글번호(PK), default : seq_adpbrd_no.nextval 
	  private Date 		brdRegdate = new Date(); // 등록일자, default : current_date
	  private String 	brdTitle; 		// 글제목
	  private String 	brdContent; 	// 글내용
	  private Long 		brdHit; 		// 조회수 default : 0
	  private Long 		brdSympathy; 	// 하트 default : 0
}
