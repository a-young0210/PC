package pc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MemberGUI extends JFrame {
	public static int money;
	public static int selectindex;
	public static DefaultTableModel model;

	public MemberGUI() {
		setTitle("ȸ�� ��ȸ");
		setBounds(10, 50, 600, 550);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		//�̸�, ���̵� ������ �� �ִ� �޺��ڽ� ����
		JComboBox comboBox = new JComboBox(); 
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"�̸�", "���̵�"}));
		comboBox.setBackground(Color.white);
		panel_1.add(comboBox);
		
		//�˻�â �����
		JTextField searchtextField = new JTextField("�˻��� ������ �Է��ϼ���.");
		panel_1.add(searchtextField);
		searchtextField.setColumns(15); //�˻�â ���� ������ ����
		searchtextField.addMouseListener(new MouseAdapter(){  //�ؽ�Ʈ�ʵ� Ŭ�� �� ���� �ʱ�ȭ
			public void mouseClicked(MouseEvent e){ 
				   searchtextField.setText(""); 
			   } 
		});
		
		//"�˻�"��ư �����
		JButton searchbutton = new JButton("�˻�"); 
		JButton totalbutton = new JButton("��ü �˻�");
		panel_1.add(searchbutton);
		panel_1.add(totalbutton);
		
		//����� ȸ�� ����Ʈ�� ��Ÿ���� ���̺�
		String colNames[] = {"�̸�", "���̵�", "��ȭ��ȣ", "�����/���X", "���"}; //���̺� ���� �̸���
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model); //�ּҷ� ���̺� �����
		panel.add(new JScrollPane(table));
		
		totalbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ü �ּҷ� ���̱�
				model.setNumRows(0);
				int c = LoginGUI.ac.getCount();
				if(c == 0) {
					JOptionPane.showMessageDialog(null,"��ϵ� ȸ���� �����ϴ�.");
				}
				else {
					for(int i=1; i<c; i++) {
						Object[] row;
						try {
							String state;
							int paytime = (int) ((Math.round(LoginGUI.ac.getPerson(i).getTime())) / 60);
							money = 0;
							if(paytime == 0) {
								money = 0;
							} else if(paytime > 0 && paytime <= 60) {
								money = 1000;
							} else {
								money = paytime * 1000;
							}
							
							if(LoginGUI.ac.getPerson(i).getState().equals("��밡��")) {
								state = "���X";
							} else {
								state = LoginGUI.ac.getPerson(i).getState();
							}
							row = new Object[] {LoginGUI.ac.getPerson(i).getName(), LoginGUI.ac.getPerson(i).getId(), LoginGUI.ac.getPerson(i).getPhoneNum(), state, money};
							model.addRow(row); // �߰�
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		
		//�˻� ��ư�� ������ �ش� �ּҷϸ� ���̺� ���̰� �Ѵ�.
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				String num, name, id, phonenum, state;
				int index = 0;
				Object[] arr;
				try {			
					if(comboBox.getSelectedItem().toString() == "���̵�") {
						index=LoginGUI.ac.searchId(searchtextField.getText());
					}
					else if(comboBox.getSelectedItem().toString() == "�̸�") {
						index=LoginGUI.ac.searchName(searchtextField.getText());	
					}
					num = Integer.toString(index);
					name = LoginGUI.ac.getPerson(index).getName();
					id = LoginGUI.ac.getPerson(index).getId();
					int paytime = (int) ((Math.round(LoginGUI.ac.getPerson(index).getTime())) / 60);
					int money = 0;
					if(LoginGUI.ac.getPerson(index).getTime() == 0.00) {
						money = 0;
					} else if(LoginGUI.ac.getPerson(index).getTime() > 0 && LoginGUI.ac.getPerson(index).getTime() <= 60) {
						money = 1000;
					} else {
						money = paytime * 1000;
					}
					phonenum = LoginGUI.ac.getPerson(index).getPhoneNum();
					if(LoginGUI.ac.getPerson(index).getState().equals("��밡��")) {
						state = "���X";
					} else {
						state = LoginGUI.ac.getPerson(index).getState();
					}
					arr = new Object[] {name, id, phonenum, state, money};
					model.addRow(arr);
				} catch (Exception e1) {
					if(comboBox.getSelectedItem().toString() == "�̸�") {
						JOptionPane.showMessageDialog(null,"��ϵ� �̸��� �����ϴ�.");	
					}
					else if(comboBox.getSelectedItem().toString() == "���̵�") {
						JOptionPane.showMessageDialog(null,"��ϵ� ���̵� �����ϴ�.");	
					}		
				}						
			}
		});
		JPanel panel1 = new JPanel();
		getContentPane().add(panel1, BorderLayout.SOUTH);
		JButton paybutton = new JButton("�����ϱ�");
		panel1.add(paybutton);
		
		//��ư Ŭ���ϸ� ����Ǿ �ð��� 0���� �ʱ�ȭ
		paybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int accountOption = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION);
				if(accountOption == JOptionPane.YES_OPTION) {
					try {
						selectindex = table.getSelectedRow();
						LoginGUI.ac.zerotime(selectindex + 1, 0);
						LoginGUI.ac.logoutModify(selectindex + 1, "��밡��");
						if(LoginGUI.ac.getPerson(selectindex + 1).getTime() == 0) {
							JOptionPane.showMessageDialog(null,"�����Ǿ����ϴ�.");
							LoginGUI.ac.salesmodify((int)model.getValueAt(selectindex, 4));
							AdministratorGUI.salesLabel.setText(LoginGUI.ac.getSales());
							model.setValueAt(0, selectindex, 4);
						}
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"������ �����߽��ϴ�.");
					}
				}
				else if((accountOption == JOptionPane.NO_OPTION) || (accountOption == JOptionPane.CLOSED_OPTION)) {
					return;
				}
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
}
