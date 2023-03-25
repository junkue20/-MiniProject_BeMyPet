package mini_dto;

import java.util.Date;

import lombok.Data;

@Data
public class Shelter {

	  // 연락처 - PK
	  private String shelterPhone;
	  // 보호소이름
	  private String shelterName;
	  // 보호소위치
	  private String shelterRegion;
	  // 날짜
	  private Date shelterDate = new Date();
}
