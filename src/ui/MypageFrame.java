package ui;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import session.Config;
import java.awt.Font;

public class MypageFrame extends JDialog {
	
	public MypageFrame(MainFrame m) {
		
		this.setTitle("마이페이지");
		getContentPane().setLayout(null);
		
//		보호소 정보 수정 -------------------------------------------------------------------- //
		JButton updateBtn = new JButton("보호소정보수정");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ShelterUpdateFrame(MypageFrame.this, m);
				MypageFrame.this.setVisible(false);				
			}
		});
		updateBtn.setBounds(433, 250, 124, 23);
		getContentPane().add(updateBtn);
		
//		비밀번호 변경 -------------------------------------------------------------------- //
		JButton pwUpdateBtn = new JButton("비밀번호변경");
		pwUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ShelterPwUpdateFrame(MypageFrame.this);
				MypageFrame.this.setVisible(false);
				
			}
		});
		pwUpdateBtn.setBounds(433, 283, 124, 23);
		getContentPane().add(pwUpdateBtn);
		
//		회원탈퇴 -------------------------------------------------------------------- //
		JButton deleteBtn = new JButton("회원탈퇴");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShelterDeleteFrame(MypageFrame.this);
				MypageFrame.this.setVisible(false);
			}
		});
		deleteBtn.setBounds(433, 316, 124, 23);
		getContentPane().add(deleteBtn);
		
		// 환영 문구
		JLabel hello = new JLabel("반갑습니다! "+Config.shelter.getShelterName()+" 님");
		hello.setFont(new Font("굴림", Font.PLAIN, 14));
		hello.setBounds(20, 25, 426, 23);
		getContentPane().add(hello);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 69, 560, 182);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		// 보호소 정보 출력
		// 이름
		JLabel nameLbl = new JLabel("이름: "+Config.shelter.getShelterName());
		nameLbl.setBounds(30, 5, 518, 15);
		panel.add(nameLbl);
		// 주소
		JLabel addrLbl = new JLabel("주소: "+Config.shelter.getShelterRegion());
		addrLbl.setLocation(30, 30);
		panel.add(addrLbl);
		addrLbl.setSize(503, 15);
		// 이메일
		JLabel emailLbl = new JLabel("이메일: "+Config.shelter.getShelterEmail());
		emailLbl.setBounds(30, 55, 518, 15);
		panel.add(emailLbl);
		// 연락처
		JLabel phoneLbl = new JLabel("연락처: "+Config.shelter.getShelterPhone());
		phoneLbl.setBounds(30, 80, 503, 15);
		panel.add(phoneLbl);

		// 윈도우팝업 종료 버튼
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				m.setVisible(true);
			}
		});
		
//		---------------------------------------------------------------------
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null); 
		this.setResizable(false);
//		---------------------------------------------------------------------
	}
	
	
}
