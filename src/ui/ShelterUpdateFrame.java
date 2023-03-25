package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Shelter;
import service.ShelterService;
import service.ShelterServiceImpl;
import session.Config;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;

public class ShelterUpdateFrame extends JDialog {
	
	private JTextField emailTxt;
	private JTextField nameTxt;
	private JTextField addrTxt;
	private JTextField phoneTxt;
	
	ShelterService shelService = new ShelterServiceImpl();
	
	public ShelterUpdateFrame(MypageFrame my, MainFrame main) {
		
		
		setTitle("보호소 정보 수정");
		getContentPane().setLayout(null);
		
		JLabel emailLbl = new JLabel("이메일");
		emailLbl.setBounds(81, 45, 57, 15);
		getContentPane().add(emailLbl);
		
		JLabel nameLbl = new JLabel("기관명");
		nameLbl.setBounds(81, 80, 57, 15);
		getContentPane().add(nameLbl);
		
		JLabel addrLbl = new JLabel("주소");
		addrLbl.setBounds(81, 115, 57, 15);
		getContentPane().add(addrLbl);
		
		JLabel phoneLbl = new JLabel("연락처");
		phoneLbl.setBounds(81, 150, 57, 15);
		getContentPane().add(phoneLbl);
		
		this.emailTxt = new JTextField();
		this.emailTxt.setText(Config.shelter.getShelterEmail()); // null 값 방지
		emailTxt.setBounds(150, 42, 203, 21);
		getContentPane().add(emailTxt);
		emailTxt.setColumns(10);
		
		this.nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(150, 77, 203, 21);
		getContentPane().add(nameTxt);
		
		this.addrTxt = new JTextField();
		addrTxt.setColumns(10);
		addrTxt.setBounds(150, 112, 203, 21);
		getContentPane().add(addrTxt);
		
		this.phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(150, 147, 203, 21);
		getContentPane().add(phoneTxt);
		
		// 변경하기 버튼
		JButton updateBtn = new JButton("변경하기");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = Config.shelter.getShelterId();
				String pw = Config.shelter.getShelterPw();
				String email = emailTxt.getText();
				String name = nameTxt.getText();
				if( name.isBlank()) {
					name = null;
				}
				String addr = addrTxt.getText();
				if( addr.isBlank()) {
					addr = null;
				}
				String phone = phoneTxt.getText();
				if( phone.isBlank()) {
					phone = null;
				}
				
				Shelter shelter = new Shelter();
				shelter.setShelterId(id);
				shelter.setShelterPw(pw);
				shelter.setShelterEmail(email);
				shelter.setShelterName(name);
				shelter.setShelterRegion(addr);
				shelter.setShelterPhone(phone);
				
				
				int result = shelService.shelterUpdate(shelter);
				
				if(result == 1) {
					int ret = JOptionPane.showConfirmDialog(null, "정보 변경이 완료됐습니다", "정보 변경 완료", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if(ret == 0) { // OK 클릭시
						
						Config.shelter.setShelterEmail(email);
						if(name != null) {
							Config.shelter.setShelterName(name);
						}
						if(addr != null) {
							Config.shelter.setShelterRegion(addr);
							}
						if(phone != null) {
							Config.shelter.setShelterPhone(phone);
							}
						
						System.out.println(Config.shelter.toString());
						
						dispose();
						new MypageFrame(main);
						
					} 
				}
				else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다");
				}
				
			}
		});
		updateBtn.setBounds(168, 190, 97, 23);
		getContentPane().add(updateBtn);
		
		JLabel warningLbl = new JLabel("※이메일은 필수 입력항목입니다");
		warningLbl.setForeground(new Color(255, 69, 0));
		warningLbl.setFont(new Font("굴림", Font.PLAIN, 11));
		warningLbl.setBounds(149, 28, 188, 15);
		getContentPane().add(warningLbl);
		
		// 윈도우 종료시
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				my.setVisible(true);
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
