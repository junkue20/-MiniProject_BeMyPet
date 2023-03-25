package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.AdpbrdService;
import service.AdpbrdServiceImpl;
import session.Config;

public class AdpbrdFrame extends JDialog implements MouseListener {
	
	AdpbrdService abService = new AdpbrdServiceImpl();
	
	private JTable table;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane;
	
	List<Map<String, Object>> list = null;
	
	private JButton selectBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	
	int row;
	
	
	private String[] column = new String[] {
			"게시글 번호", "글제목", "글내용", 
			"동물 등록번호", "품종", "성별", "구조지역", "보호기간", 
			"조회수", "관심", "게시글 등록일"};
	
	public AdpbrdFrame(MainFrame main) {
		
		
		getContentPane().setLayout(null);
		this.setTitle("입양 공고 관리");
		
		table = new JTable();
 		table.setRowHeight(180);
 		table.setBounds(12, 56, 758, 535);
 				
		model = new DefaultTableModel(column, 0);
		
		list = abService.selectAllAdpbrd(Config.shelter.getShelterId());
		
		for(Map<String, Object> map : list) {
			
			boolean check = map.get("BRD_NO") == null			 || map.get("BRD_TITLE")== null 	||
							map.get("BRD_CONTENT") == null 		 || map.get("ANIMAL_NO") == null 	||
							map.get("ANIMAL_SPECIES")== null 	 || map.get("ANIMAL_GENDER")== null ||
							map.get("ANIMAL_RESCUE_SPOT")== null || map.get("ANIMAL_LIMIT")== null 	||
							map.get("BRD_HIT")== null 			 || map.get("BRD_SYMPATHY")== null 	||
							map.get("BRD_REGDATE")== null;
			
			if(!check) {
				try {
					String oldstring1 = map.get("ANIMAL_LIMIT").toString();
					String oldstring2 = map.get("BRD_REGDATE").toString();
					Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring1);
					Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(oldstring2);

					String newstring1 = new SimpleDateFormat("yy/MM/dd").format(date1);
					String newstring2 = new SimpleDateFormat("yy/MM/dd").format(date2);

					String[] data = { map.get("BRD_NO").toString(), map.get("BRD_TITLE").toString(),
							map.get("BRD_CONTENT").toString(), map.get("ANIMAL_NO").toString(),
							map.get("ANIMAL_SPECIES").toString(), map.get("ANIMAL_GENDER").toString(),
							map.get("ANIMAL_RESCUE_SPOT").toString(), newstring1, map.get("BRD_HIT").toString(),
							map.get("BRD_SYMPATHY").toString(), newstring2 };
					model.addRow(data);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
 		table.setModel(model);
 		table.setRowHeight(25);
		
 		scrollPane = new JScrollPane(table);
 		scrollPane.setEnabled(false);
 		scrollPane.setBounds(12, 77, 920, 394);
 		getContentPane().add(scrollPane);
 		
 		selectBtn = new JButton("개별 조회");
 		selectBtn.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				new AdpbrdSelectFrame(printCell(row));

 			}
 		});
 		selectBtn.setBounds(404, 25, 150, 35);
 		getContentPane().add(selectBtn);
 		
 		updateBtn = new JButton("수정하기");
 		updateBtn.setBounds(580, 25, 150, 35);
 		getContentPane().add(updateBtn);
 		
 		deleteBtn = new JButton("삭제하기");
 	
 		deleteBtn.setBounds(756, 25, 150, 35);
 		getContentPane().add(deleteBtn);
 		
 		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				main.setVisible(true);
				dispose();
			}
		});
		
		
		/* ~ 기본 설정 -------------------------------------------------------------------------*/		
		this.setVisible(true);
		this.setSize(960, 540);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false);
		/* 기본 설정 ~ -------------------------------------------------------------------------*/		

		//이벤트 추가
		selectBtn.addMouseListener(this);	// 개별 조회 처리
		updateBtn.addMouseListener(this);	// 수정 처리
		deleteBtn.addMouseListener(this);	// 삭제 처리
		table.addMouseListener(this);	//셀 읽기 처리
		
		
	}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			Object src=e.getSource();
			if(src==deleteBtn) {
				int selected=table.getSelectedRow();
				int result = abService.deleteOneAdpbrd(printCell(row));
				if(result == 1 ) {
					removeRecord(selected);
					JOptionPane.showMessageDialog(null, "게시글 삭제 완료");
				}
				else {
					JOptionPane.showMessageDialog(null, "오류발생");
				}
			}
			if(src==updateBtn) {
				new AdpbrdUpdateFrame(AdpbrdFrame.this, printCell(row));
				AdpbrdFrame.this.setVisible(false);
			}
			if(src==table) {
				row=table.getSelectedRow();	
				printCell(row);
				
				
				
			}
		}
		
		// Frame에서 선택된 테이블의 행 제거
		public void removeRecord(int index) {
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			if(index<0) {
				if(table.getRowCount()==0)//비어있는 테이블이면
					return;
				index=0;
			}
			model.removeRow(index);
		}
		
		// 테이블의 선택된 행의 첫번째 열을 반환
		public Long printCell(int row) {
			this.model=(DefaultTableModel)table.getModel();
			System.out.println(model.getValueAt(row, 0));
			
			
			return Long.parseLong(model.getValueAt(row, 0).toString());
			
		}

}
