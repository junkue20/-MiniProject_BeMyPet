package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Shelter {

	  private String shelterId; 	// 아이디(PK)
	  private String shelterPw;		// 비밀번호
	  private String shelterPhone;  // 연락처
	  private String shelterName; 	// 보호소이름
	  private String shelterRegion; // 보호소위치
	  private Date 	 shelterDate = new Date();   // 생성날짜 => default : current_date
	  private String shelterEmail;  // 이메일
	  
	  
	  // 임시 변수 생성
	  private String newPW;
}
