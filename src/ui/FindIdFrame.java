package ui;

import javax.swing.JDialog;
import javax.swing.JTextField;

import service.ShelterService;
import service.ShelterServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FindIdFrame extends JDialog {
	
	private JTextField emailTxt;
	ShelterService shelService = new ShelterServiceImpl(); 
	
	public FindIdFrame(LoginFrame r) {
		
		this.setTitle("아이디 찾기");
		
		getContentPane().setLayout(null);
		
		// 이메일 입력창
		this.emailTxt = new JTextField();
		emailTxt.setBounds(189, 102, 116, 21);
		getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		// 이메일 라벨
		JLabel emailLbl = new JLabel("이메일");
		emailLbl.setBounds(120, 105, 57, 15);
		getContentPane().add(emailLbl);
		
		// 입력하기 버튼
		JButton insertBtn = new JButton("입력하기");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = emailTxt.getText();
				String id = shelService.findShelterId(email);
				
				if(id != null) {
					int ret = JOptionPane.showConfirmDialog(null, "아이디는 "+id+" 입니다", "아이디 확인", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	
					if(ret == 0) { // OK 클릭시
						dispose();
						r.setVisible(true);
					} 
				}
				else {
					JOptionPane.showMessageDialog(null, "이메일 입력 오류");
				}
					
					
			}
		});
		insertBtn.setBounds(165, 148, 97, 23);
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