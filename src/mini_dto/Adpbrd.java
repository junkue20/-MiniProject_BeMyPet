package mini_dto;

import java.util.Date;

import lombok.Data;

@Data
public class Adpbrd {

	  // 게시글번호 - PK
	  private Long brdNo;
	  // 등록일자
	  private Date brdRegdate = new Date();
	  // 글제목
	  private String brdTitle;
	  // 글내용
	  private String brdContent;
	  // 조회수
	  private Long brdHit;
	  // 하트
	  private Long brdSympathy;
}
