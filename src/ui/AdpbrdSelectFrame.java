package ui;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;

import service.AdpbrdService;
import service.AdpbrdServiceImpl;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AdpbrdSelectFrame extends JDialog {
	
	AdpbrdService abSerivce = new AdpbrdServiceImpl();
	
	public AdpbrdSelectFrame(Long brdNo) {
		
		Map<String, Object> map = abSerivce.selectOneAdpbrd(brdNo);
		
		setTitle("개별조회");
		
		getContentPane().setLayout(null);
		
/* Label ~ -------------------------------------------------------------*/
		JLabel aniNoLbl = new JLabel("동물등록번호");
		aniNoLbl.setBounds(24, 74, 100, 15);
		getContentPane().add(aniNoLbl);
		
		JLabel aniSpeLbl = new JLabel("품종");
		aniSpeLbl.setBounds(24, 108, 57, 15);
		getContentPane().add(aniSpeLbl);
		
		JLabel aniGenderLbl = new JLabel("성별");
		aniGenderLbl.setBounds(24, 142, 57, 15);
		getContentPane().add(aniGenderLbl);
		
		JLabel aniAgeLbl = new JLabel("나이");
		aniAgeLbl.setBounds(24, 210, 57, 15);
		getContentPane().add(aniAgeLbl);
		
		JLabel aniWeightLbl = new JLabel("체중");
		aniWeightLbl.setBounds(24, 244, 57, 15);
		getContentPane().add(aniWeightLbl);
		
		JLabel aniNeutLbl = new JLabel("중성화 여부");
		aniNeutLbl.setBounds(24, 176, 111, 15);
		getContentPane().add(aniNeutLbl);
		
		JLabel aniSpotLbl = new JLabel("구조장소");
		aniSpotLbl.setBounds(24, 430, 111, 15);
		getContentPane().add(aniSpotLbl);
		
		JLabel aniRescueDateLbl = new JLabel("구조일자");
		aniRescueDateLbl.setBounds(24, 396, 111, 15);
		getContentPane().add(aniRescueDateLbl);
		
		JLabel aniLimitLbl = new JLabel("보호기한");
		aniLimitLbl.setBounds(24, 362, 111, 15);
		getContentPane().add(aniLimitLbl);
		
		JLabel aniContentLbl = new JLabel("동물상세정보");
		aniContentLbl.setBounds(24, 278, 111, 15);
		getContentPane().add(aniContentLbl);
		
		JLabel brdNoLbl = new JLabel("게시글번호");
		brdNoLbl.setBounds(457, 74, 100, 15);
		getContentPane().add(brdNoLbl);
		
		JLabel brdDateLbl = new JLabel("게시일자");
		brdDateLbl.setBounds(457, 108, 100, 15);
		getContentPane().add(brdDateLbl);
		
		JLabel brdTitleLbl = new JLabel("글 제목");
		brdTitleLbl.setFont(new Font("굴림", Font.BOLD, 17));
		brdTitleLbl.setBounds(12, 10, 422, 34);
		getContentPane().add(brdTitleLbl);
		
		JLabel brdContentLbl = new JLabel("글 상세");
		brdContentLbl.setBounds(457, 142, 100, 15);
		getContentPane().add(brdContentLbl);
		
		JLabel brdHitLbl = new JLabel("조회수");
		brdHitLbl.setBounds(770, 431, 100, 15);
		getContentPane().add(brdHitLbl);
		
		JLabel brdSymLbl = new JLabel("관심");
		brdSymLbl.setBounds(770, 465, 100, 15);
		getContentPane().add(brdSymLbl);
/* ~ Label -------------------------------------------------------------*/
		
		
/* Text ~ -------------------------------------------------------------*/
		JLabel aniNoTxt = new JLabel(map.get("ANIMAL_NO").toString());
		aniNoTxt.setBounds(169, 74, 225, 15);
		getContentPane().add(aniNoTxt);
		
		JLabel aniSpeTxt = new JLabel(map.get("ANIMAL_SPECIES").toString());
		aniSpeTxt.setBounds(169, 108, 225, 15);
		getContentPane().add(aniSpeTxt);
		
		JLabel aniGenderTxt = new JLabel(map.get("ANIMAL_GENDER").toString());
		aniGenderTxt.setBounds(169, 142, 225, 15);
		getContentPane().add(aniGenderTxt);
		
		JLabel aniAgeTxt = new JLabel(map.get("ANIMAL_AGE").toString());
		aniAgeTxt.setBounds(169, 210, 225, 15);
		getContentPane().add(aniAgeTxt);
		
		JLabel aniWeightTxt = new JLabel(map.get("ANIMAL_WEIGHT").toString());
		aniWeightTxt.setBounds(169, 244, 225, 15);
		getContentPane().add(aniWeightTxt);
		
		JLabel aniNeutTxt = new JLabel(map.get("ANIMAL_NEUTERING").toString());
		aniNeutTxt.setBounds(169, 176, 111, 15);
		getContentPane().add(aniNeutTxt);
		
		JLabel aniSpotTxt = new JLabel(map.get("ANIMAL_RESCUE_SPOT").toString());
		aniSpotTxt.setBounds(169, 430, 225, 15);
		getContentPane().add(aniSpotTxt);
		
		JLabel aniContentTxt = new JLabel(map.get("ANIMAL_CONTENT").toString());
		aniContentTxt.setBounds(169, 278, 225, 74);
		getContentPane().add(aniContentTxt);
		
		JLabel brdNoTxt = new JLabel(map.get("BRD_NO").toString());
		brdNoTxt.setBounds(587, 74, 283, 15);
		getContentPane().add(brdNoTxt);
		
		JLabel brdHitTxt = new JLabel(map.get("BRD_HIT").toString());
		brdHitTxt.setBounds(832, 431, 100, 15);
		getContentPane().add(brdHitTxt);
		
		JLabel brdSymTxt = new JLabel(map.get("BRD_SYMPATHY").toString());
		brdSymTxt.setBounds(832, 465, 100, 15);
		getContentPane().add(brdSymTxt);
		
		try {
			String oldstring1 = map.get("ANIMAL_LIMIT").toString();
			String oldstring2 = map.get("BRD_REGDATE").toString();
			String oldstring3 = map.get("ANIMAL_RESCUEDATE").toString();
		
			Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring1);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring2);
			Date date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring3);

			String newstring1 = new SimpleDateFormat("yy/MM/dd").format(date1);
			String newstring2 = new SimpleDateFormat("yy/MM/dd").format(date2);
			String newstring3 = new SimpleDateFormat("yy/MM/dd").format(date3);

		
			
			JLabel aniLimitTxt = new JLabel(newstring1);
			aniLimitTxt.setBounds(169, 362, 111, 15);
			getContentPane().add(aniLimitTxt);
			
			JLabel brdDateTxt = new JLabel(newstring2);
			brdDateTxt.setBounds(587, 108, 283, 15);
			getContentPane().add(brdDateTxt);
			
			JLabel aniRescueDateTxt = new JLabel(newstring3);
			aniRescueDateTxt.setBounds(169, 396, 111, 15);
			getContentPane().add(aniRescueDateTxt);
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(587, 133, 301, 236);
			getContentPane().add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			scrollPane.setViewportView(textArea);
			textArea.setText(map.get("BRD_CONTENT").toString());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
/* ~ Text ~ -------------------------------------------------------------*/
	
		
		
/* ~ 기본 설정 -------------------------------------------------------------------------*/		
		this.setVisible(true);
		this.setSize(960, 540);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false);
/* 기본 설정 ~ -------------------------------------------------------------------------*/	
	}
}
