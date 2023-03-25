package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.Adpbrd;
import service.AdpbrdService;
import service.AdpbrdServiceImpl;

public class AdpbrdUpdateFrame extends JDialog {

		AdpbrdService abService = new AdpbrdServiceImpl();
		
		private JTextField titleTxt;

		public AdpbrdUpdateFrame(AdpbrdFrame af, Long brdNo) {
			
			Map<String, Object> map = abService.selectOneAdpbrd(brdNo);
			
			setTitle("입양 게시글 수정하기");
			getContentPane().setLayout(null);
			
			titleTxt = new JTextField("글 제목");
			titleTxt.setText(map.get("BRD_TITLE").toString());
			titleTxt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					titleTxt.setText("");
				}
			});
			titleTxt.setFont(new Font("굴림", Font.PLAIN, 16));
			titleTxt.setBounds(23, 26, 388, 36);
			getContentPane().add(titleTxt);
			titleTxt.setColumns(10);
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(23, 75, 388, 353);
			getContentPane().add(scrollPane);
			
			JTextArea contentTxt = new JTextArea("글 내용");
			contentTxt.setText(map.get("BRD_CONTENT").toString());
			contentTxt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contentTxt.setText("");
				}
			});
			scrollPane.setViewportView(contentTxt);
			contentTxt.setFont(new Font("Monospaced", Font.PLAIN, 16));
			

			JButton updateBtn = new JButton("수정하기");
			updateBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!titleTxt.getText().isBlank() && !contentTxt.getText().isBlank()) {
						Adpbrd a = new Adpbrd();
						a.setBrdTitle(titleTxt.getText());
						a.setBrdContent(contentTxt.getText());
						a.setBrdNo(brdNo);
						int result = abService.updateOneAdpbrd(a);
						
						if(result == 1) {
							int ret = JOptionPane.showConfirmDialog(null, "게시글 수정 완료", "게시글 수정", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
							if(ret == 0) {
								MainFrame main = new MainFrame();
								new AdpbrdFrame(main);
								main.setVisible(false);
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "변경 오류");
						}
					} else {
						JOptionPane.showMessageDialog(null, "공백 입력 불가");
					}
				}
			});
			updateBtn.setBounds(151, 448, 132, 36);
			getContentPane().add(updateBtn);
			
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
