package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import session.Config;

public class MainFrame extends JFrame implements ActionListener  {
	
	private JButton animalBtn; // 보호동물 관리
	private JButton imgBtn;    // 랜덤 이미지 불러오기 버튼
	private JButton adpbrdBtn; // 입양게시글 관리
	private JButton mypageBtn; // 마이페이지
	private JButton rogoutBtn; // 로그아웃
	
	
	public MainFrame() {
		
		this.setTitle("메인페이지");
		
		getContentPane().setLayout(null);
		
		this.animalBtn = new JButton("보호 동물 관리");
		animalBtn.setBounds(27, 47, 350, 250);
		this.animalBtn.addActionListener(this);
		getContentPane().add(animalBtn);
		
		this.imgBtn = new JButton();
		Random random = new Random();
		
	      ImageIcon imageicon[] = {new ImageIcon("./image/1.jpg"), new ImageIcon("./image/2.jpg"), new ImageIcon("./image/3.jpg"),
	    		  				   new ImageIcon("./image/4.jpg"), new ImageIcon("./image/5.jpg"), new ImageIcon("./image/6.jpg")};      
	      imgBtn.setIcon(imageicon[1]);
	      imgBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 imgBtn.setIcon(imageicon[random.nextInt(6)]);
	         }
	      });
		imgBtn.setBounds(404, 48, 350, 250);
		this.imgBtn.addActionListener(this);
		getContentPane().add(imgBtn);
		
		
		this.adpbrdBtn = new JButton("입양 공고 관리");
		adpbrdBtn.setBounds(27, 318, 350, 250);
		this.adpbrdBtn.addActionListener(this);
		getContentPane().add(adpbrdBtn);
		
		this.mypageBtn = new JButton("마이페이지");
		mypageBtn.setBounds(404, 318, 350, 250);
		this.mypageBtn.addActionListener(this);
		getContentPane().add(mypageBtn);
		
		this.rogoutBtn = new JButton("로그아웃");
		rogoutBtn.setBounds(657, 21, 97, 23);
		this.rogoutBtn.addActionListener(this);
		getContentPane().add(rogoutBtn);

		
//		---------------------------------------------------------------------
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame 창을 끄면 백그라운드에서도 종료
		this.setSize(800, 640);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // 창 사이즈 고정
//		---------------------------------------------------------------------
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("보호 동물 관리")) {
		
			new AnimalFrame(MainFrame.this);
			MainFrame.this.setVisible(false);
			
		}
		else if (e.getActionCommand().equals("접수된 입양 요청 게시글 관리")) {
			
			JOptionPane.showMessageDialog(null, "접수된 입양 요청 게시글 관리 페이지로 가는 법 아시는 분");
		
		}
		else if (e.getActionCommand().equals("입양 공고 관리")) {
			
			new AdpbrdFrame(MainFrame.this);
			MainFrame.this.setVisible(false);
		
		}
		else if (e.getActionCommand().equals("마이페이지")) {
			
			new MypageFrame(MainFrame.this);
			MainFrame.this.setVisible(false);
		
		}
		else if (e.getActionCommand().equals("로그아웃")) {
			
			dispose();
			new LoginFrame(new MainFrame());
			Config.shelter = null;
			
		}
		
	}

	
}