package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Animal {
	  
	  private long 		animalNo; 			// 등록번호(PK) => default : seq_animal_no.nextval 
	  private String 	animalSpecies; 		// 품종 ex) [개] 믹스견  
	  private String 	animalGender;  		// 성별 ex) 암컷/수컷 
	  private String 	animalAge; 			// 나이 ex) 1살 추정, 12개월
	  private String 	animalWeight; 		// 체중 ex) 2.48kg
	  private String 	animalNeutering; 	// 중성화여부 ex) 예/아니오/알 수 없음
	  private String 	animalRescueSpot; 	// 구조지역 ex) 부산시 남구 
	  private Date 		animalRescuedate = new Date();  // 구조일자 => default : current_date
	  private String 	animalContent; 		// 특징명(질병상세, 특이사항)
	  private int 		animalState; 		// 상태 1(보호중), 0(입양), 2(안락사),3(자연사) => default:1
	  private Date 		animalLimit; 		// 보호기한
	  private String 	shelterId;			// 보호소 아이디(FK)
}
