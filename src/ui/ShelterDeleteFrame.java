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

public class ShelterDeleteFrame extends JDialog {
	
	private JTextField pwTxt;
	ShelterService shelService = new ShelterServiceImpl();
	
	public ShelterDeleteFrame(MypageFrame m){
		
		setTitle("회원탈퇴");
		getContentPane().setLayout(null);
		
		JLabel pwLbl = new JLabel("현재 비밀번호를 입력하세요");
		pwLbl.setBounds(141, 90, 152, 15);
		getContentPane().add(pwLbl);
		
		this.pwTxt = new JTextField();
		pwTxt.setBounds(121, 116, 192, 21);
		getContentPane().add(pwTxt);
		pwTxt.setColumns(10);
		
//		회원탈퇴 버튼 -------------------------------------------------------------------- //
		JButton deleteBtn = new JButton("탈퇴하기");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ret = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(ret == 0) {
				
					Shelter shelter = new Shelter();
					shelter.setShelterId(Config.shelter.getShelterId());
					shelter.setShelterPw(pwTxt.getText());
					
					int result = shelService.shelterDelete(shelter);
					
						if(result == 1) {
							
							int ret2 = JOptionPane.showConfirmDialog(null, "탈퇴가 완료됐습니다", "회원탈퇴 완료", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
							if(ret2 == 0) {
								dispose();
								Config.shelter = null;
								new LoginFrame(new MainFrame());
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, "비밀번호 입력 오류");
						}
				
				}
				else {
					dispose();
					m.setVisible(true);
				}
				
			}
		});
		deleteBtn.setBounds(168, 167, 97, 23);
		getContentPane().add(deleteBtn);

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
