package mini_dto;

import java.util.Date;

import lombok.Data;

@Data
public class Askadp {

	  // 아이디 - FK
	  private String memId;
	  // 게시글번호 - FK
	  private Long brdNo;
	  // 내용(어필)
	  private String askContent;
	  // 신청일자
	  private Date askDate = new Date();
}
