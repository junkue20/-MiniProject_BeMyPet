package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Shelter;
import service.ShelterService;
import service.ShelterServiceImpl;
import session.Config;

public class ShelterPwUpdateFrame extends JDialog {
	
	private JTextField pwTxt;
	private JTextField newPwTxt;
	
	ShelterService shelService = new ShelterServiceImpl();
	
	public ShelterPwUpdateFrame(MypageFrame m) {
		
		setTitle("비밀번호 변경");
		getContentPane().setLayout(null);
		
		JLabel pwLbl = new JLabel("현재 비밀번호");
		pwLbl.setBounds(91, 91, 85, 15);
		getContentPane().add(pwLbl);
		
		JLabel newPwLbl = new JLabel("새로운 비밀번호");
		newPwLbl.setBounds(91, 124, 99, 15);
		getContentPane().add(newPwLbl);
		
		this.pwTxt = new JTextField();
		pwTxt.setBounds(200, 88, 145, 21);
		getContentPane().add(pwTxt);
		pwTxt.setColumns(10);
		
		this.newPwTxt = new JTextField();
		newPwTxt.setColumns(10);
		newPwTxt.setBounds(200, 121, 145, 21);
		getContentPane().add(newPwTxt);
		
		JButton updateBtn = new JButton("변경하기");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pw = pwTxt.getText();
				String newPw = newPwTxt.getText();
				
				Shelter shelter = new Shelter();
				shelter.setShelterId(Config.shelter.getShelterId());
				shelter.setShelterPw(pw);
				shelter.setNewPW(newPw);
				
				int result = shelService.shelterPwUpdate(shelter);
				
				if(result == 1) {
					
					int ret = JOptionPane.showConfirmDialog(null, "비밀번호 변경이 완료됐습니다", "비밀번호 변경 완료", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					
						if(ret == 0) {
							
							dispose();
							m.setVisible(true);

						}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "정보 입력 오류");
				}
				
				
			}
		});
		updateBtn.setBounds(168, 171, 97, 23);
		getContentPane().add(updateBtn);


		// 윈도우팝업 종료 버튼
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				m.setVisible(true);
			}
		});
		
//		---------------------------------------------------------------------
		this.setVisible(true);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		---------------------------------------------------------------------
	}
	
	
}
