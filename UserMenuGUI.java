package pc;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.sql.SQLException;

class UserMenuGUI {

	private int total = 0; // �� �ݾ׿� �� ����

	// ��ҹ�ư�� �������� Ȯ��
	private boolean doDelete = false;
	
	// ���� Ŭ�� count
	private int[] sCnt = new int[20];
	// ����� Ŭ�� count
	private int[] jCnt = new int[20];
	// ��� Ŭ�� count
	private int[] nCnt = new int[20];
	// �Ļ� Ŭ�� count
	private int[] mCnt = new int[12];

	// ��񿡼� ���õ� �˻��� ����� ����
	private ArrayList<Member> list = new ArrayList<Member>();
	private ArrayList<Menu> typeList = new ArrayList<Menu>();
	private Menu productList = null;
	private DefaultTableModel dtm;
	private JTable jt;
	private JTextField idField;
	private JLabel totalPrice;
	
	public static void main(String[] args) {
		new UserMenuGUI();
	}
	
	public UserMenuGUI() {
		// �÷����� ����
		String[] orderCulumnName = { "��ǰ��", "����", "����" };

		// ������Ʈ ����
		dtm = new DefaultTableModel(orderCulumnName, 0) {
			// �������� ���ϰ� �ϴ� �ʵ�
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		JTabbedPane tap = new JTabbedPane(); // JTabbedPane����

		// ������Ʈ ����
		JFrame orderFrame = new JFrame("���� �ֹ�");

		// ��ũ�� �г� ���� �� ���̺� �ֱ�?
		jt = new JTable(dtm);
		JScrollPane orderPanel = new JScrollPane(jt);
		orderPanel.setPreferredSize(new Dimension(270, 300));

		// �г� ����
		JPanel pSnack = new JPanel();
		JPanel pJuice = new JPanel();
		JPanel pNoodle = new JPanel();
		JPanel pMeal = new JPanel();
		JPanel east = new JPanel();
		JPanel north = new JPanel();
		JPanel orderU = new JPanel();
		JPanel orderD = new JPanel();
		JPanel sum = new JPanel();

		int orderSum = 0;

		// ��ư
		JButton delBtn = new JButton("�� ��");
		JButton buyBtn = new JButton("�� ��");

		// ��
		totalPrice = new JLabel("  ��  �� ��  :  0 ");

		// �� ����
		tap.add(" �� �� ", pSnack); // JTabbedPane�� ���߰�
		tap.add(" �� �� �� ", pJuice);
		tap.add(" �� �� ", pNoodle);
		tap.add(" �� �� ( �� �� ) ", pMeal);

		// ������ orderâ �Ʒ� / ��ȸ+���� ��ư ���̾ƿ�
		orderD.setLayout(new GridLayout(1, 2, 3, 3));
		orderD.setPreferredSize(new Dimension(270, 60));
		orderD.add(delBtn);
		orderD.add(buyBtn);

		orderFrame.add(east, "East");
		orderFrame.add(tap, "Center");

		// ������ order â �� / ���� ����
		orderU.setLayout(new BorderLayout());
		orderU.add(orderPanel, "Center");
		orderU.add(sum, "South");

		// east ���̾ƿ�(�ֹ� ��ϰ� ���� â) ����
		east.setLayout(new BorderLayout());
		east.add(orderU, "Center");
		east.add(orderD, "South");

		// orderlist�� �� �ݾ� ������ sum Panel
		sum.setLayout(new GridLayout(1, 2, 4, 3));
		sum.setPreferredSize(new Dimension(270, 30));
		sum.add(totalPrice);
		// sum.add();

		// �̺�Ʈ ������
		buyBtn.addActionListener(new sumEvent());
		delBtn.addActionListener(new delEvent());

		/* ���� �� ���� */
		LoginGUI.ac.readType("����", typeList);
		
		ArrayList<String> snackName = new ArrayList<String>();
		ArrayList<String> snackPrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			snackName.add(product);
			snackPrice.add(price);			
		}
		
		JButton[] sButtons = new JButton[20];

		for (int i = 0; i < snackName.size(); i++) {
			sButtons[i] = new JButton(snackName.get(i));
		}
		// �����ڳ� ���̾ƿ�(���� ���̴� â) ����
		pSnack.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < snackName.size(); i++) {
			pSnack.add(sButtons[i]);
		}

		// ���� Ŭ�� �̺�Ʈ
		for (int i = 0; i < snackName.size(); i++) {
			final int idx1;
			idx1 = i;

			sButtons[idx1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sCnt[idx1]++;
					JOptionPane.showMessageDialog(null, snackName.get(idx1) + "+" + sCnt[idx1] + " �ֹ� ��Ͽ� ����ϴ�.");
					total += Integer.parseInt(snackPrice.get(idx1));
					totalPrice.setText("  ��  �� ��  :  " + total);
					if (sCnt[idx1] <= 1)
						dtm.addRow(new Object[] { snackName.get(idx1), snackPrice.get(idx1), sCnt[idx1] });
					else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == snackName.get(idx1)) {
									dtm.setValueAt(sCnt[idx1], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* ����� �� ���� */
		typeList.clear();
		LoginGUI.ac.readType("�����", typeList);
		
		ArrayList<String> juiceName = new ArrayList<String>();
		ArrayList<String> juicePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			juiceName.add(product);
			juicePrice.add(price);
		}
		
		JButton[] jButtons = new JButton[20];

		for (int i = 0; i < juiceName.size(); i++) {
			jButtons[i] = new JButton(juiceName.get(i));
		}
		// ���� �ڳ� ���̾ƿ�(���� ���̴� â) ����
		pJuice.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < juiceName.size(); i++) {
			pJuice.add(jButtons[i]);
		}

		// ���� Ŭ�� �̺�Ʈ
		for (int i = 0; i < juiceName.size(); i++) {
			final int idx2;
			idx2 = i;

			jButtons[idx2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jCnt[idx2]++;
					JOptionPane.showMessageDialog(null, juiceName.get(idx2) + "+" + jCnt[idx2] + " �ֹ� ��Ͽ� ����ϴ�.");
					total += Integer.parseInt(juicePrice.get(idx2));
					totalPrice.setText("  ��  �� ��  :  " + total);
					if (jCnt[idx2] <= 1)
						dtm.addRow(new Object[] { juiceName.get(idx2), juicePrice.get(idx2), jCnt[idx2] });
					else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == juiceName.get(idx2)) {
									dtm.setValueAt(jCnt[idx2], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* ��� �� ���� */
		typeList.clear();
		LoginGUI.ac.readType("���", typeList);
		
		ArrayList<String> NoodleName = new ArrayList<String>();
		ArrayList<String> noodlePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			NoodleName.add(product);
			noodlePrice.add(price);
		}
		JButton[] nButtons = new JButton[20];

		for (int i = 0; i < NoodleName.size(); i++) {
			nButtons[i] = new JButton(NoodleName.get(i));
		}
		// ��� �ڳ� ���̾ƿ�(���� ���̴� â) ����
		pNoodle.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < NoodleName.size(); i++) {
			pNoodle.add(nButtons[i]);
		}
		// ��� Ŭ�� �̺�Ʈ
		for (int i = 0; i < NoodleName.size(); i++) {
			final int idx3;
			idx3 = i;

			nButtons[idx3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					nCnt[idx3]++;
					JOptionPane.showMessageDialog(null, NoodleName.get(idx3) + "+" + nCnt[idx3] + " �ֹ� ��Ͽ� ����ϴ�.");
					total += Integer.parseInt(noodlePrice.get(idx3));
					totalPrice.setText("  ��  �� ��  :  " + total);
					if (nCnt[idx3] <= 1) {
						dtm.addRow(new Object[] { NoodleName.get(idx3), noodlePrice.get(idx3), nCnt[idx3] });
					} else {

						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == NoodleName.get(idx3)) {
									dtm.setValueAt(nCnt[idx3], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		/* �Ļ� �� ���� */
		typeList.clear();
		LoginGUI.ac.readType("�Ļ��", typeList);
		
		ArrayList<String> MealName = new ArrayList<String>();
		ArrayList<String> mealePrice = new ArrayList<String>();
		
		for (int i = 0; i < typeList.size(); i++) {
			// String type, String product, String price, String count, String date, String remarks
			String product = typeList.get(i).getProduct();
			String price = typeList.get(i).getPrice();
			
			MealName.add(product);
			mealePrice.add(price);
		}
		
		JButton[] mButtons = new JButton[11];

		for (int i = 0; i < MealName.size(); i++) {
			mButtons[i] = new JButton(MealName.get(i));
		}
		// �Ļ� �ڳ� ���̾ƿ�(���� ���̴� â) ����
		pMeal.setLayout(new GridLayout(4, 5, 3, 3));
		for (int i = 0; i < MealName.size(); i++) {
			pMeal.add(mButtons[i]);
		}

		// �Ļ� Ŭ�� �̺�Ʈ
		for (int i = 0; i < MealName.size(); i++) {
			final int idx4;
			idx4 = i;
			int j = -1;
			int k = -1;

			mButtons[idx4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					mCnt[idx4]++;
					JOptionPane.showMessageDialog(null, MealName.get(idx4) + "+" + mCnt[idx4] + " �ֹ� ��Ͽ� ����ϴ�.");
					total += Integer.parseInt(mealePrice.get(idx4));
					totalPrice.setText("  ��  �� ��  :  " + total);
					if (mCnt[idx4] <= 1)
						dtm.addRow(new Object[] { MealName.get(idx4), mealePrice.get(idx4), mCnt[idx4] });
					else {
						for (int j = 0; j < dtm.getRowCount(); j++) {
							for (int k = 0; k < dtm.getColumnCount(); k++) {
								if (dtm.getValueAt(j, k) == MealName.get(idx4)) {
									dtm.setValueAt(mCnt[idx4], j, 2);
									break;
								}
							}
						}
					}
				}
			});
		}

		// ������ ��ġ ����
		int width = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 7;

		// �������� �⺻���� ��ġ�� ������ ����
		orderFrame.setResizable(false);
		orderFrame.setBounds(width, height, 800, 450);
		orderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		orderFrame.setVisible(true);
	}

	// ���� ��ư�� ������ �߻��ϴ� �̺�Ʈ
	private class sumEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int flag = 0;
			
			// ������ �ѱݾ� ������ ����Ǿ��ϴ� �ڵ�
			if (dtm.getRowCount() > 0) {
				for(int i=0; i<dtm.getRowCount(); i++) {
					
					// getSelectedRow() - ���õ� ��/ 1��° ��
					String sellProduct = (String) dtm.getValueAt(i, 0);
					int sellCount = (int) dtm.getValueAt(i, 2);
					System.out.println(sellProduct+sellCount);
					
					productList = LoginGUI.ac.readProduct(sellProduct);
					// �Ȱ� �� ���� ���� ����
					int afterCount = Integer.parseInt(productList.getCount()) - sellCount;
						
						LoginGUI.ac.sellMenu(sellProduct, Integer.toString(afterCount));
				}
				try {
					LoginGUI.ac.salesmodify(total);
					try {
						AdministratorGUI.salesLabel.setText(LoginGUI.ac.getSales());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					JOptionPane.showMessageDialog(null, "���� �Ϸ�Ǿ����ϴ�.");				
					resetRow();
					total = 0;
					totalPrice.setText("  ��  �� ��  :  " + total);
			}

		}
	}

	// ��� ��ư�� ������ �߻��ϴ� �̺�Ʈ
	private class delEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resetRow();
			total = 0;
			totalPrice.setText("  ��  �� ��  :  " + total);
		}
	}

	// ���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			sCnt = new int[20];
			jCnt = new int[20];
			nCnt = new int[20];
			mCnt = new int[12];
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	// /���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����

}
