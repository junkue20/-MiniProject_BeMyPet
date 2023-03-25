package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import dto.Animal;
import service.AdpbrdService;
import service.AdpbrdServiceImpl;
import service.AnimalService;
import service.AnimalServiceImpl;
import session.Config;


public class AnimalFrame extends JDialog implements MouseListener, ActionListener {
	
	AnimalService aniService = new AnimalServiceImpl();
	AdpbrdService abService = new AdpbrdServiceImpl();
	
	private JComboBox<String> combo;
	private JScrollPane scrollPane;
	private JTextField serchTxt;
	private DefaultTableModel model = null;
	private JTable table;
	
	private int stateNo = 1;
	
	private JButton addBtn;
	private JButton updateBtn;
	private JButton serchBtn;
	
	private List<Animal> l = null;
	
	private String[] column = new String[] { "번호", "품종", "성별", "나이", 
			 								 "무게", "중성화 여부", "구조지역", 
			 								 "특이사항", "구조일자", "보호기한"
			 								};
	
	
	public AnimalFrame(MainFrame r) {
		
		List<Animal> list = new ArrayList<>();
		
		this.setTitle("센터 소속 동물 조회");
		
		getContentPane().setLayout(null);
		
/* table, combo 설정 ~ -------------------------------------------------------------------------*/		
		table = new JTable();
 		table.setRowHeight(180);
 		table.setBounds(12, 56, 758, 535);
		

 		model = new DefaultTableModel(column, 0) {
 			public boolean isCellEditable(int rowIndex, int mCollIndex) {
 				return false;
 			}
 		};
 		
 		list = aniService.selectByState(1, Config.shelter.getShelterId());
 		
 		for(Animal a : list) {
 			Date rescuedate = a.getAnimalRescuedate();
 			Date limitdate = a.getAnimalLimit();
 			
 			SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd");
 			
 			String rescueStr = transFormat.format(rescuedate);
 			String limitStr = transFormat.format(limitdate);
 			
 			
 			String[] data = {
					a.getAnimalNo() + "",
					a.getAnimalSpecies(),
					a.getAnimalGender(),
					a.getAnimalAge(),
					a.getAnimalWeight(),
					a.getAnimalNeutering(),
					a.getAnimalRescueSpot(),
					a.getAnimalContent(),
					rescueStr,
					limitStr
					};
 			model.addRow(data);
 		}
 		table.setModel(model);
 		table.setRowHeight(25);
 		this.resizeColumnWidth(table);
 		
 		scrollPane = new JScrollPane(table);
 		scrollPane.setBounds(12, 77, 920, 394);
 		getContentPane().add(scrollPane);
		
		combo = new JComboBox<>();
		combo.addItem("보호 중");
		combo.addItem("입양 완료");
		combo.addItem("안락사");
		combo.addItem("자연사");
		
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				
				
				l = new ArrayList<>();
				if(e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(combo.getSelectedItem());
					

					model = new DefaultTableModel(column, 0) {

						public boolean isCellEditable(int i, int c) {
							return false;
						}

					};
			 		if(combo.getSelectedItem().toString().equals("보호 중")) {
			 			l = aniService.selectByState(1, Config.shelter.getShelterId());
			 			stateNo = 1;
			 		} else if(combo.getSelectedItem().toString().equals("입양 완료")) {
			 			l = aniService.selectByState(0, Config.shelter.getShelterId());
			 			stateNo = 0;
			 		} else if(combo.getSelectedItem().toString().equals("안락사")){
			 			l = aniService.selectByState(2, Config.shelter.getShelterId());
			 			stateNo = 2;
			 		} else if(combo.getSelectedItem().toString().equals("자연사")) {
			 			l = aniService.selectByState(3, Config.shelter.getShelterId());
			 			stateNo = 3;
			 			
			 		}
			 		
			 		for(Animal a : l) {
			 			Date rescuedate = a.getAnimalRescuedate();
			 			Date limitdate = a.getAnimalLimit();
			 			
			 			SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd");
			 			
			 			String rescueStr = transFormat.format(rescuedate);
			 			String limitStr = transFormat.format(limitdate);
			 			
			 			
			 			String[] data = {
			 					a.getAnimalNo() + ""	, a.getAnimalSpecies(),
								a.getAnimalGender()		,  a.getAnimalAge(),
								a.getAnimalWeight()		,  a.getAnimalNeutering(),
								a.getAnimalRescueSpot() , a.getAnimalContent(),
								rescueStr				, limitStr
								};
			 			model.addRow(data);
					}
					
					table.setModel(model);
			 		
				}

			}
		});

		combo.setBounds(28, 23, 135, 23);
		getContentPane().add(combo);
/* ~ table, combo 설정 -------------------------------------------------------------------------*/		

		serchTxt = new JTextField();
		serchTxt.setBounds(703, 24, 145, 21);
		getContentPane().add(serchTxt);
		serchTxt.setColumns(10);
		this.serchBtn = new JButton("검색");
		serchBtn.setBounds(846, 23, 65, 23);
		this.serchBtn.addActionListener(this);
		getContentPane().add(serchBtn);
		
/* 검색 부분 ~ -------------------------------------------------------------------------*/
		setTablebyState(1);
/* ~ 검색 부분 -------------------------------------------------------------------------*/		
	
		
/* 동물 등록, 수정, 게시글 등록 부분 ~ -------------------------------------------------------------------------*/		
		JPanel rightPanel=new JPanel();
		rightPanel.setBounds(190, 10, 501, 57);
		
		addBtn=new JButton("새 동물 등록");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimalFrame.this.setVisible(false);
				new AnimalInsertFrame(AnimalFrame.this, r);
			}
		});
		addBtn.setBounds(10, 10, 141, 30);
		
		
		updateBtn=new JButton("동물 정보 수정");			
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Config.animal != null) {
					new AnimalUpdateFrame(AnimalFrame.this, r);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "수정 할 동물을 클릭 후 수정하기 버튼을 눌러주세요.");
				}
			}
		});

		updateBtn.setBounds(163, 10, 141, 30);
		rightPanel.setLayout(null);
		
		
		rightPanel.add(addBtn);
		rightPanel.add(updateBtn);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		getContentPane().add(rightPanel); 
		
		JButton insertAdpbrdBtn = new JButton("게시글 등록");
		insertAdpbrdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Config.animal != null) {
					
					if(abService.insertCheck(Config.animal.getAnimalNo()) == null) {
						
						new AdpbrdInsertFrame(AnimalFrame.this);
						AnimalFrame.this.setVisible(false);
						
						
					} else {
						
						JOptionPane.showMessageDialog(null, "해당 동물의 게시글은 이미 작성되었습니다.");
						
					}
				} else {
					JOptionPane.showMessageDialog(null, "동물을 클릭 후 게시글 등록하기 버튼을 눌러주세요.");
				}
				
				
				
				
				
			}
		});
		insertAdpbrdBtn.setBounds(316, 10, 141, 30);
		rightPanel.add(insertAdpbrdBtn);
/* ~ 동물 등록, 수정, 게시글 등록 부분 -------------------------------------------------------------------------*/		

		
/* 창 끄면 실행될 액션 ~ -------------------------------------------------------------------------*/	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				r.setVisible(true);
			}
		});
/* ~ 창 끄면 실행될 액션 -------------------------------------------------------------------------*/		

 		
		
/* ~ 기본 설정 -------------------------------------------------------------------------*/		
		this.setVisible(true);
		this.setSize(960, 540);
		this.setLocationRelativeTo(null);  // null로 설정해주면 JFrame창이 가운데서 실행됨
		this.setResizable(false); // 창 사이즈 고정
/* 기본 설정 ~ -------------------------------------------------------------------------*/		


		//이벤트 추가
		addBtn.addMouseListener(this);	//추가 처리
		updateBtn.addMouseListener(this);	//삭제 처리
		table.addMouseListener(this);	//셀 읽기 처리

	}


	
	// JTable column size 내용길이에 맞춰 조절하기
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public void mouseClicked(MouseEvent e) {
		Object src=e.getSource();

		if(src==table) {
			int row=table.getSelectedRow();	
			printCell(row);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void removeRecord(int index) {
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		if(index<0) {
			if(table.getRowCount()==0)//비어있는 테이블이면
				return;
			index=0;
		}
		model.removeRow(index);
	}
	
	/* 읽기  : 읽으려면 cell의 위치를 알아야합니다. 
	   그래서 선택된 행의 위치, 선택된 열의 위치를 얻어와서 
	   DefaultTableModel의 getValueAt()에 전달해주면 셀을 읽어올 수 있습니다. 
	   내용을 출력해주는 메소드를 printCell()이라고 하여 정의 */
	public void printCell(int row) {
		this.model=(DefaultTableModel)table.getModel();
//		System.out.println(model.getValueAt(row, 0));
		
		Config.animal = new Animal();
		Config.animal.setAnimalNo(Long.parseLong(model.getValueAt(row, 0).toString()));
		
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("검색")) {
			setTablebyState(stateNo);
		}
		
	}
	
	public void setTablebyState(int state) {
		model = new DefaultTableModel(column, 0) {

			public boolean isCellEditable(int i, int c) {
				return false;
			}

		};
 		
 		List<Animal> list = aniService.searchAnimal(serchTxt.getText(), Config.shelter.getShelterId());
 		
 		for(Animal a : list) {
 			
 			if ( a.getAnimalState() == state) {
 				Date rescuedate = a.getAnimalRescuedate();
 	 			Date limitdate = a.getAnimalLimit();
 	 			
 	 			SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd");
 	 			
 	 			String rescueStr = transFormat.format(rescuedate);
 	 			String limitStr = transFormat.format(limitdate);
 	 			
 	 			
 	 			String[] data = {
 						a.getAnimalNo() + ""	, a.getAnimalSpecies(),
 						a.getAnimalGender()		,  a.getAnimalAge(),
 						a.getAnimalWeight()		,  a.getAnimalNeutering(),
 						a.getAnimalRescueSpot() , a.getAnimalContent(),
 						rescueStr				, limitStr
 						};
 	 			model.addRow(data);
 				
 				
 			}
 			table.setModel(model);
 			
 		}
 		
 		
 		
	}
}
