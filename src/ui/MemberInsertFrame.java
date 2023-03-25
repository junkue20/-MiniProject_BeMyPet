package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

public class MemberInsertFrame extends JDialog {
	
	private JTextField idTxt;
	private JPasswordField pwTxt;
	private JTextField emailTxt;
	private JTextField nameTxt;
	private JTextField addrTxt;
	private JTextField phoneTxt;
	
	ShelterService shelService = new ShelterServiceImpl(); 
	
	public MemberInsertFrame(LoginFrame r) {
		
		getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		idLabel.setForeground(Color.BLACK);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(33, 112, 98, 24);
		getContentPane().add(idLabel);
		
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwLabel.setForeground(Color.BLACK);
		pwLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		pwLabel.setBounds(33, 170, 98, 24);
		getContentPane().add(pwLabel);
		
		JLabel nameLabel = new JLabel("기관명");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		nameLabel.setBounds(33, 286, 98, 24);
		getContentPane().add(nameLabel);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setForeground(Color.BLACK);
		emailLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		emailLabel.setBounds(33, 228, 98, 24);
		getContentPane().add(emailLabel);
		
		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		phoneLabel.setForeground(Color.BLACK);
		phoneLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		phoneLabel.setBounds(33, 404, 98, 24);
		getContentPane().add(phoneLabel);
		
		JLabel addrLabel = new JLabel("주소");
		addrLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addrLabel.setForeground(Color.BLACK);
		addrLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		addrLabel.setBounds(33, 346, 98, 24);
		getContentPane().add(addrLabel);
		
		this.idTxt = new JTextField();
		idTxt.setBounds(135, 113, 181, 24);
		getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		this.pwTxt = new JPasswordField();
		pwTxt.setBounds(135, 171, 181, 24);
		getContentPane().add(pwTxt);
		
		this.emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(135, 229, 181, 24);
		getContentPane().add(emailTxt);
		
		this.nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(135, 287, 181, 24);
		getContentPane().add(nameTxt);
		
		this.addrTxt = new JTextField();
		addrTxt.setColumns(10);
		addrTxt.setBounds(135, 347, 181, 24);
		getContentPane().add(addrTxt);
		
		this.phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(135, 405, 181, 24);
		getContentPane().add(phoneTxt);
		
		// 아이디 중복확인 버튼
		JButton idCheckBtn = new JButton("중복확인");
		idCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = idTxt.getText();
				Shelter shelter = shelService.shelterIdCheck(id);
				
				if(id.length() != 0) {
					
					if(shelter != null) {
						JOptionPane.showMessageDialog(null, "중복된 아이디가 있습니다");
					}
					else{
						JOptionPane.showMessageDialog(null, "사용할 수 있는 아이디입니다");
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
				}
				
			}
		});
		idCheckBtn.setBounds(328, 112, 83, 24);
		getContentPane().add(idCheckBtn);
		
		
		// 가입하기
		JButton insertBtn = new JButton("가입하기");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Shelter shelter = new Shelter();
				String id = idTxt.getText();
				String pw = pwTxt.getText();
				String email = emailTxt.getText();
				String name = nameTxt.getText();
				String addr = addrTxt.getText();
				String phone = phoneTxt.getText();
				
				if(id.isBlank() || pw.isBlank() || email.isBlank() || name.isBlank() || addr.isBlank() || phone.isBlank()) {
					
					JOptionPane.showMessageDialog(null, "공백은 입력할 수 없습니다");
					
				}
				else {
					
					shelter.setShelterId(id);
					shelter.setShelterPw(pw);
					shelter.setShelterEmail(email);
					shelter.setShelterName(name);
					shelter.setShelterRegion(addr);
					shelter.setShelterPhone(phone);
					
					int result = shelService.shelterJoin(shelter);
					
					if(result == 1) {
						
						int ret = JOptionPane.showConfirmDialog(null, "회원가입이 완료됐습니다", "회원가입", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						if(ret == 0) { // OK 클릭시
							dispose();
							r.setVisible(true);
						} 
						
					}
					else {
						JOptionPane.showMessageDialog(null, "정보입력 오류");
					}
				
				}
				
			}
		});
		insertBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		insertBtn.setBounds(91, 471, 112, 36);
		getContentPane().add(insertBtn);
		
		JButton deleteBtn = new JButton("취소하기");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose(); // 현재 창 닫기
					r.setVisible(true);
			}
		});
		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		deleteBtn.setBounds(232, 471, 112, 36);
		getContentPane().add(deleteBtn);
		
		JLabel insertLabel = new JLabel("회원가입");
		insertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		insertLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		insertLabel.setBounds(153, 38, 127, 44);
		getContentPane().add(insertLabel);

		// 윈도우팝업 종료 버튼
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				r.setVisible(true);
			}
		});
		
		this.setVisible(true);
		this.setSize(450, 600);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // false : 창 사이즈 고정
		
	}
	

}


