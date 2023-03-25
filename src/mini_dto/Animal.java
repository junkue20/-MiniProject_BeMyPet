package mini_dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class Animal {

	  // 등록번호 - PK
	  private Long animalNo;
	  // 종 (강아지, 고양이, 거북이, ...)
	  private String animalSpecies;
	  // 구조일자
	  private Date animalRescuedate = new Date();
	  // 나이
	  private Long animalAge;
	  // 성별 
	  private String animalGender;
	  // 설명(중성화여부, 질병유무, 특이사항)
	  private String animalContent;
	  // 구조지역
	  private String animalRescueSpot;
	  // 질병유무(질병O:0, 질병X:1)
	  private int animalDisease;
	  // 이름
	  private String animalName;
	  // 보호기한
	  private LocalDateTime animalLimit;
	  // 연락처
	  private String shelterPhone;
	  // 동물상태(1: 보호중, 0 : 입양, 1 : 악락사, 2 : 자연사 , ...)
	  private int animalState;
	  
}
