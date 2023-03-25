package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.Animal;
import service.AnimalService;
import service.AnimalServiceImpl;
import session.Config;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AnimalUpdateFrame extends JDialog {

	private JTextField speciesTxt;
	private JTextField genderTxt;
	private JTextField ageTxt;
	private JTextField weightTxt;
	private JTextField neutralizationTxt;
	private JTextField contentTxt;

	private JLabel noLabel;
	private JLabel titleLabel;
	private JLabel speciesLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JLabel stateLabel;
	private JLabel neutralizationLabel;
	private JLabel contentLabel;

	private JComboBox<String> combo;
	private JTextField noTxt;

	
	public AnimalUpdateFrame(AnimalFrame af, MainFrame r) {

		AnimalService aniService = new AnimalServiceImpl();
		Animal ani = aniService.selectOneAnimal(Config.animal.getAnimalNo());

		getContentPane().setLayout(null);
		this.setTitle("동물 정보 수정");
		
/* label ~ ------------------------------------------------------------------ */
		noLabel = new JLabel("번호");
		noLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		noLabel.setBounds(73, 118, 79, 25);
		getContentPane().add(noLabel);

		titleLabel = new JLabel("동물 정보 수정");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
		titleLabel.setBounds(42, 66, 339, 32);
		getContentPane().add(titleLabel);

		speciesLabel = new JLabel("품종");
		speciesLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		speciesLabel.setBounds(73, 153, 79, 25);
		getContentPane().add(speciesLabel);

		genderLabel = new JLabel("성별");
		genderLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		genderLabel.setBounds(73, 188, 79, 25);
		getContentPane().add(genderLabel);

		ageLabel = new JLabel("나이");
		ageLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		ageLabel.setBounds(73, 221, 79, 25);
		getContentPane().add(ageLabel);

		weightLabel = new JLabel("무게");
		weightLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		weightLabel.setBounds(73, 256, 79, 25);
		getContentPane().add(weightLabel);

		stateLabel = new JLabel("상태");
		stateLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		stateLabel.setBounds(73, 326, 79, 25);
		getContentPane().add(stateLabel);

		neutralizationLabel = new JLabel("중성화여부");
		neutralizationLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		neutralizationLabel.setBounds(73, 291, 97, 25);
		getContentPane().add(neutralizationLabel);

		contentLabel = new JLabel("내용 (특이사항)");
		contentLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		contentLabel.setBounds(73, 381, 119, 25);
		getContentPane().add(contentLabel);

/* ~ label ------------------------------------------------------------------ */

/* text ~ ------------------------------------------------------------------ */
		noTxt = new JTextField();
		noTxt.setEditable(false);
		noTxt.setText(ani.getAnimalNo() + "");
		noTxt.setColumns(10);
		noTxt.setBounds(191, 121, 171, 21);
		getContentPane().add(noTxt);

		speciesTxt = new JTextField();
		speciesTxt.setText(ani.getAnimalSpecies());
		speciesTxt.setColumns(10);
		speciesTxt.setBounds(191, 153, 171, 21);
		getContentPane().add(speciesTxt);

		genderTxt = new JTextField();
		genderTxt.setText(ani.getAnimalGender());
		genderTxt.setColumns(10);
		genderTxt.setBounds(191, 190, 171, 21);
		getContentPane().add(genderTxt);

		ageTxt = new JTextField();
		ageTxt.setText(ani.getAnimalAge());
		ageTxt.setColumns(10);
		ageTxt.setBounds(191, 223, 171, 21);
		getContentPane().add(ageTxt);

		weightTxt = new JTextField();
		weightTxt.setText(ani.getAnimalWeight());
		weightTxt.setColumns(10);
		weightTxt.setBounds(191, 258, 171, 21);
		getContentPane().add(weightTxt);

		neutralizationTxt = new JTextField();
		neutralizationTxt.setText(ani.getAnimalNeutering());
		neutralizationTxt.setColumns(10);
		neutralizationTxt.setBounds(191, 293, 171, 21);
		getContentPane().add(neutralizationTxt);

		contentTxt = new JTextField();
		contentTxt.setText(ani.getAnimalContent());
		contentTxt.setColumns(10);
		contentTxt.setBounds(191, 384, 171, 21);
		getContentPane().add(contentTxt);
		
/* ~ text --------------------------------------------------------------------*/

		
/* button ~ ------------------------------------------------------------------*/

		JButton updateBtn = new JButton("수정하기");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "OK", JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (ret == 0) {
					ani.setAnimalSpecies(speciesTxt.getText());
					ani.setAnimalGender(genderTxt.getText());
					ani.setAnimalAge(ageTxt.getText());
					ani.setAnimalWeight(weightTxt.getText());
					ani.setAnimalNeutering(neutralizationTxt.getText());
					ani.setAnimalState(Config.animal.getAnimalState());
					ani.setAnimalContent(contentTxt.getText());
					
					int ret1 = aniService.updateOneAnimal(ani);

					if (ret1 == 1) {
						int ret2 = JOptionPane.showConfirmDialog(null, "수정 완료!", "수정 성공", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE);
						if (ret2 == 0) {
							dispose();
							new AnimalFrame(r);
						}
					}
				}
			}
		});
		
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 14));
		updateBtn.setBounds(88, 449, 97, 23);
		getContentPane().add(updateBtn);

		JButton cancelBtn = new JButton("취소하기");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				af.setVisible(true); 
			}
		});
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 14));
		cancelBtn.setBounds(236, 449, 97, 23);
		getContentPane().add(cancelBtn);

		combo = new JComboBox<>();
		combo.addItem("");
		combo.addItem("보호 중");
		combo.addItem("입양 완료");
		combo.addItem("안락사");
		combo.addItem("자연사");
		combo.setBounds(191, 328, 171, 23);

		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(combo.getSelectedItem());

					if (combo.getSelectedItem().toString().equals("보호 중")) {
						Config.animal.setAnimalState(1);
					} else if (combo.getSelectedItem().toString().equals("입양 완료")) {
						Config.animal.setAnimalState(0);
					} else if (combo.getSelectedItem().toString().equals("안락사")) {
						Config.animal.setAnimalState(2);
					} else if (combo.getSelectedItem().toString().equals("자연사")) {
						Config.animal.setAnimalState(3);
					}

				}
			}
		});

		getContentPane().add(combo);

/* ~ button ------------------------------------------------------------------ */
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				af.setVisible(true);
				dispose();
			}
		});

/* 기본 설정 ~ ------------------------------------------------------------------ */
		this.setVisible(true);
		this.setSize(450, 600);
		this.setLocationRelativeTo(null); // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // false : 창 사이즈 고정
/* ~ 기본 설정 ------------------------------------------------------------------ */
		
	}

}
