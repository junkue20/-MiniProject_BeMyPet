package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.Shelter;
import service.ShelterService;
import service.ShelterServiceImpl;
import session.Config;


public class LoginFrame extends JDialog {
	
	private JTextField idTxt;
	private JPasswordField pwTxt;
	
	ShelterService shelService = new ShelterServiceImpl(); 
	
	public LoginFrame(MainFrame m) {
		
		
		this.setTitle("보호소 로그인");
		m.setVisible(false);

		getContentPane().setLayout(null);
		
		// 아이디 입력창
		this.idTxt = new JTextField();
		idTxt.setBounds(131, 74, 116, 21);
		getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		// 비밀번호 입력창
		this.pwTxt = new JPasswordField();
		pwTxt.setBounds(131, 116, 116, 21);
		getContentPane().add(pwTxt);

		// 아이디 라벨
		JLabel idLbl = new JLabel("아이디");
		idLbl.setBounds(62, 77, 57, 15);
		getContentPane().add(idLbl);
		
		// 비밀번호 라벨
		JLabel pwLbl = new JLabel("비밀번호");
		pwLbl.setBounds(62, 119, 57, 15);
		getContentPane().add(pwLbl);
		
		
		// 로그인 버튼
		JButton roginBtn = new JButton("로그인");
		roginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 입력받은 정보를 shelter에 저장
				Shelter shelter = new Shelter();
				String id = idTxt.getText();
				String pw = pwTxt.getText();
				shelter.setShelterId(id);
				shelter.setShelterPw(pw);
				
				Config.shelter = shelService.shelterLogin(shelter);
				
				if(Config.shelter != null) {
				
						int ret = JOptionPane.showConfirmDialog(null, "반갑습니다! "+Config.shelter.getShelterName()+" 님", "로그인 성공", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(ret == 0) {
							new MainFrame();
							dispose();
						}
				
				} else {
					
					JOptionPane.showMessageDialog(null, "로그인 정보 입력 오류");
					
				}
				
				
			}
		});
		roginBtn.setBounds(293, 64, 97, 40);
		getContentPane().add(roginBtn);
		
		
		// 회원가입 버튼
		JButton insertBtn = new JButton("회원가입");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MemberInsertFrame(LoginFrame.this);
				LoginFrame.this.setVisible(false);
			}
		});
		insertBtn.setBounds(293, 115, 97, 23);
		getContentPane().add(insertBtn);
		
		// 아이디 찾기 버튼
		JButton findIdBtn = new JButton("아이디를 잊으셨나요?");
		findIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindIdFrame(LoginFrame.this);
				LoginFrame.this.setVisible(false);
			}
		});
		findIdBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findIdBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		findIdBtn.setBounds(62, 155, 152, 23);
		getContentPane().add(findIdBtn);
		
		// 비밀번호 찾기 버튼
		JButton findPwBtn = new JButton("비밀번호를 잊으셨나요?");
		findPwBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindPwFrame(LoginFrame.this);
				LoginFrame.this.setVisible(false);
			}
		});
		findPwBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findPwBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		findPwBtn.setBounds(226, 154, 164, 23);
		getContentPane().add(findPwBtn);
		
		
//		---------------------------------------------------------------------
		this.setVisible(true);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
//		---------------------------------------------------------------------
		
		
	}

}
