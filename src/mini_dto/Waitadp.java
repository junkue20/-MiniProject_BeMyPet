package mini_dto;

import java.util.Date;

import lombok.Data;

@Data
public class Waitadp {

	  // 등록번호
	  private Long animalNo;
	  // 게시글번호
	  private Long brdNo;
	  private Date waitadpDate = new Date();
}
