package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Waitadp {
	 
	 private int waitadpNo; // 입양 대기 고유키(PK), default: seq_waitadp_no.nextval
	 private Date waitadpDate = new Date(); // 생성 날짜, default : current_date
	 private long animalNo; // 동물 번호(FK)
	 private long brdNo; // 게시판 번호(FK)

}
