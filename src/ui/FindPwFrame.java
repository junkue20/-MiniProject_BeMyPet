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

public class FindPwFrame extends JDialog {
	
	private JTextField idTxt;
	private JTextField emailTxt;
	ShelterService shelService = new ShelterServiceImpl(); 
	
	public FindPwFrame(LoginFrame r) {
		
		this.setTitle("비밀번호 찾기");
		
		getContentPane().setLayout(null);
		
		// 아이디 라벨
		JLabel IdLbl = new JLabel("아이디");
		IdLbl.setBounds(116, 78, 57, 15);
		getContentPane().add(IdLbl);
		
		// 이메일 라벨
		JLabel emailLbl = new JLabel("이메일");
		emailLbl.setBounds(116, 119, 57, 15);
		getContentPane().add(emailLbl);
		
		// 아이디 입력창
		this.idTxt = new JTextField();
		idTxt.setBounds(214, 75, 116, 21);
		getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		// 이메일 입력창
		this.emailTxt = new JTextField();
		emailTxt.setBounds(214, 116, 116, 21);
		getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		// 입력하기 버튼
		JButton insertBtn = new JButton("입력하기");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Shelter shelter = new Shelter();
				shelter.setShelterId(idTxt.getText());
				shelter.setShelterEmail(emailTxt.getText());
				
				int result = shelService.findShelterPw(shelter);
				
				if(result == 1) {
					
					String pw = shelService.showShelterpw(shelter);
					
					int ret = JOptionPane.showConfirmDialog(null, "새로 설정된 비밀번호는 "+pw+" 입니다", "비밀번호 확인", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					
						if(ret == 0) {
							dispose();
							r.setVisible(true);
						}
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디 혹은 이메일 입력 오류");
				}
			}
		});
		insertBtn.setBounds(162, 165, 97, 23);
		getContentPane().add(insertBtn);

		// 윈도우팝업 종료 버튼
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				r.setVisible(true);
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