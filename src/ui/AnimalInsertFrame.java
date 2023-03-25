package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.Animal;
import service.AnimalService;
import service.AnimalServiceImpl;
import session.Config;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

// 완료
// 191라인에 animal.setShelterId 수정 필요

public class AnimalInsertFrame extends JDialog{
	
	AnimalService aniservice = new AnimalServiceImpl();
	
	private JTextField speciesTxt;
	private JTextField genderTxt;
	private JTextField ageTxt;
	private JTextField weightTxt;
	private JTextField neutralizationTxt;
	private JTextField regionTxt;
	private JTextField rescueTxt;
	private JTextField limitTxt;
	private JTextField contentTxt;
	
	private JLabel titleLabel;
	private JLabel speciesLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JLabel limitLabel;
	private JLabel dayLabel;
	private JLabel regionLabel;
	private JLabel neutralizationLabel;
	private JLabel contentLabel;

	
	private JButton insertBtn;
	private JButton cancelBtn;

	
	
	
	
	public AnimalInsertFrame(AnimalFrame af, MainFrame r) {
		
		getContentPane().setFont(new Font("굴림", Font.PLAIN, 14));
		getContentPane().setLayout(null);
		this.setTitle("새 동물 등록");

		
/* label 영역 ~ --------------------------------------------------------------------------------------------- */
		
		titleLabel = new JLabel("새로운 동물 정보 등록");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
		titleLabel.setBounds(27, 37, 339, 32);
		getContentPane().add(titleLabel);
		
		speciesLabel = new JLabel("품종");
		speciesLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		speciesLabel.setBounds(74, 113, 79, 25);
		getContentPane().add(speciesLabel);
		
		genderLabel = new JLabel("성별");
		genderLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		genderLabel.setBounds(420, 162, 79, 25);
		getContentPane().add(genderLabel);
		
		ageLabel = new JLabel("나이");
		ageLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		ageLabel.setBounds(74, 162, 79, 25);
		getContentPane().add(ageLabel);
		
		weightLabel = new JLabel("무게");
		weightLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		weightLabel.setBounds(420, 214, 79, 25);
		getContentPane().add(weightLabel);
		
		limitLabel = new JLabel("보호기한");
		limitLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		limitLabel.setBounds(420, 316, 79, 25);
		getContentPane().add(limitLabel);
		
		dayLabel = new JLabel("구조일자");
		dayLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		dayLabel.setBounds(77, 316, 79, 25);
		getContentPane().add(dayLabel);
		
		regionLabel = new JLabel("구조지역");
		regionLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		regionLabel.setBounds(77, 265, 79, 25);
		getContentPane().add(regionLabel);
		
		neutralizationLabel = new JLabel("중성화여부");
		neutralizationLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		neutralizationLabel.setBounds(74, 214, 97, 25);
		getContentPane().add(neutralizationLabel);
		
		contentLabel = new JLabel("내용 (상세)");
		contentLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		contentLabel.setBounds(420, 265, 119, 25);
		getContentPane().add(contentLabel);
		
/* ~ label 영역 --------------------------------------------------------------------------------------------------- */

	
/* text 영역 ~ --------------------------------------------------------------------------------------------- */
		speciesTxt = new JTextField();
		speciesTxt.setBounds(192, 113, 171, 21);
		getContentPane().add(speciesTxt);
		speciesTxt.setColumns(10);
		
		
		genderTxt = new JTextField();
		genderTxt.setColumns(10);
		genderTxt.setBounds(538, 164, 171, 21);
		getContentPane().add(genderTxt);
		
		
		ageTxt = new JTextField();
		ageTxt.setColumns(10);
		ageTxt.setBounds(192, 164, 171, 21);
		getContentPane().add(ageTxt);
		
		
		weightTxt = new JTextField();
		weightTxt.setColumns(10);
		weightTxt.setBounds(538, 216, 171, 21);
		getContentPane().add(weightTxt);
		
		
		neutralizationTxt = new JTextField();
		neutralizationTxt.setColumns(10);
		neutralizationTxt.setBounds(192, 216, 171, 21);
		getContentPane().add(neutralizationTxt);
		
		
		regionTxt = new JTextField();
		regionTxt.setColumns(10);
		regionTxt.setBounds(195, 267, 171, 21);
		getContentPane().add(regionTxt);
		
		
		contentTxt = new JTextField();
		contentTxt.setColumns(10);
		contentTxt.setBounds(538, 267, 171, 21);
		getContentPane().add(contentTxt);
		

		
		rescueTxt = new JTextField("YY/MM/DD");
		rescueTxt.setForeground(Color.GRAY);
		rescueTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rescueTxt.setText("");
				rescueTxt.setForeground(Color.BLACK);
			}
		});
		rescueTxt.setColumns(10);
		rescueTxt.setBounds(195, 318, 171, 21);
		getContentPane().add(rescueTxt);
		
			
		
		
		limitTxt = new JTextField("YY/MM/DD");
		limitTxt.setForeground(Color.GRAY);
		limitTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limitTxt.setText("");
				limitTxt.setForeground(Color.BLACK);
			}
		});
		limitTxt.setColumns(10);
		limitTxt.setBounds(538, 318, 171, 21);
		getContentPane().add(limitTxt);

/* ~ text 영역-------------------------------------------------------------------------------------------- */

/* button 영역 ~ --------------------------------------------------------------------------------------------- */
		insertBtn = new JButton("등록하기");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ret = JOptionPane.showConfirmDialog(null, "새로운 동물 정보를 등록하시겠습니까?", "OK", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(ret == 0) {
					Animal animal = new Animal();
					
					animal.setShelterId(Config.shelter.getShelterId());
					
					String species = speciesTxt.getText();
					String gender = genderTxt.getText();
					String age = ageTxt.getText();
					String neutralization = neutralizationTxt.getText();
					String region = regionTxt.getText();
					String content = contentTxt.getText();
					String weight = weightTxt.getText();
					String rescueDate = rescueTxt.getText();
					String limitDate = limitTxt.getText();
					
					// 공백 여부 체크
					boolean check = !species.isBlank() && !gender.isBlank()         && 
									!age.isBlank()     && !neutralization.isBlank() &&  
									!region.isBlank()  && !content.isBlank() 	    &&
									!weight.isBlank()  && !rescueDate.isBlank() 	&&
									!limitDate.isBlank();
					
					if(check) { // 입력란이 모두 공백이 아니면 animal 테이블에 등록해줌
						animal.setAnimalSpecies(species);
						animal.setAnimalGender(gender);
						animal.setAnimalAge(age);
						animal.setAnimalNeutering(neutralization);
						animal.setAnimalRescueSpot(region);
						animal.setAnimalContent(content);
						animal.setAnimalWeight(weight);
						
						SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
						try {
							animal.setAnimalRescuedate(formatter.parse(rescueDate));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						try {
							animal.setAnimalLimit(formatter.parse(limitDate));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						aniservice.insertOneAnimal(animal);
						dispose();
						new AnimalFrame(r);
//						af.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "공백은 입력 할 수 없습니다.");
					}
				}
			}
		});
		insertBtn.setFont(new Font("굴림", Font.PLAIN, 14));
		insertBtn.setBounds(150, 380, 216, 32);
		getContentPane().add(insertBtn);
		
		cancelBtn = new JButton("취소하기");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
					af.setVisible(true);
					
			}
		});
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 14));
		cancelBtn.setBounds(420, 380, 216, 32);
		getContentPane().add(cancelBtn);
		
/* ~ button 영역--------------------------------------------------------------------------------------------- */
		
/* 창 끄면 실행 ~ ---------------------------------------------------------------------------------*/
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				af.setVisible(true);
			}
		});
/* ~ 창 끄면 실행-------------------------------------------------------------------------------*/
		
		
/* 기본 세팅 영역 ~ --------------------------------------------------------------------------------------------- */
		this.setVisible(true);
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // false : 창 사이즈 고정
/* ~ 기본 세팅 영역 -------------------------------------------------------------------------------------------- */
	
	}

	
}
