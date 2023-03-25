package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.Adpbrd;
import service.AdpbrdService;
import service.AdpbrdServiceImpl;
import session.Config;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class AdpbrdInsertFrame extends JDialog {

	
	AdpbrdService abService = new AdpbrdServiceImpl();
	
	private JTextField titleTxt;

	public AdpbrdInsertFrame(AnimalFrame af) {
	
		setTitle("입양 게시글 등록하기");
		getContentPane().setLayout(null);
		
		titleTxt = new JTextField();
		titleTxt.setFont(new Font("굴림", Font.PLAIN, 16));
		titleTxt.setBounds(23, 43, 388, 25);
		getContentPane().add(titleTxt);
		titleTxt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 110, 388, 318);
		getContentPane().add(scrollPane);
		
		JTextArea contentTxt = new JTextArea();

		scrollPane.setViewportView(contentTxt);
		contentTxt.setFont(new Font("Monospaced", Font.PLAIN, 16));
	
		JButton insertBtn = new JButton("등록하기");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Adpbrd ab = new Adpbrd();
					
					ab.setBrdNo(abService.selectAdpbrdSeq());
					ab.setBrdTitle(titleTxt.getText());
					ab.setBrdContent(contentTxt.getText());
					int result = abService.insertOneAdpbrd(Config.animal.getAnimalNo(), ab);
					
					if( result == 1 ) {
						
						int ret = JOptionPane.showConfirmDialog(null, "게시글 등록 완료", "게시글 등록", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(ret == 0) {
							dispose();
							af.setVisible(true);
						}
						
						
					} else {
						JOptionPane.showMessageDialog(null, "대박 웬 오류?");
					}
				
			}
		});
		insertBtn.setBounds(151, 448, 132, 36);
		getContentPane().add(insertBtn);
		
		JLabel titleLbl = new JLabel("게시글 제목");
		titleLbl.setFont(new Font("굴림", Font.PLAIN, 14));
		titleLbl.setBounds(23, 18, 110, 25);
		getContentPane().add(titleLbl);
		
		JLabel contentLbl = new JLabel("게시글 내용");
		contentLbl.setFont(new Font("굴림", Font.PLAIN, 14));
		contentLbl.setBounds(23, 81, 99, 25);
		getContentPane().add(contentLbl);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				af.setVisible(true);
			}
		});
		
/* ~ 기본 설정 -------------------------------------------------------------------------*/		
		this.setVisible(true);
		this.setSize(450, 550);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // false : 창 사이즈 고정
/* 기본 설정 ~ -------------------------------------------------------------------------*/	
	
	}
}
