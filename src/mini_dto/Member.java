package mini_dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	
	  // 아이디 - PK
	  private String memId;
	  // 비번
	  private String memPw;
	  // 이름
	  private String memName;
	  // 연락처
	  private String memPhone;
	  // 주소
	  private String memAddress;
	  // 고객등록일
	  private Date memRegdate = new Date();
	  // 블랙리스트 체크(0,1)
	  private Long memBlockChk = 1L;
	  // 탈퇴유무확인(0,1)
	  private Long memQuitChk = 1L;
}
