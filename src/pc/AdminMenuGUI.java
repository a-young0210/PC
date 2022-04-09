package pc;

import java.awt.*;
import java.awt.Container;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AdminMenuGUI extends JFrame {

	// �����̳� ��ü
	Container container = getContentPane();

	// ��ȸ��ư�� �������� Ȯ��
	private boolean doSearch = false;

	// ��񿡼� ���õ� �˻��� ����� ����
	private ArrayList<Menu> list = new ArrayList<Menu>();
	private DefaultTableModel dtm;
	private JTable jt;
	private JTextField typeField, productField, textFieldType, textFieldProduct, textFieldPrice, textFieldCount,
			textFieldDate, textFieldRemarks;
	private JComboBox<String> comboType;



	public static void main(String[] args) {
		new AdminMenuGUI();
	}

	// ManageMember������ ����
	@SuppressWarnings("serial")
	public AdminMenuGUI() {
		// �÷����� ����
		String[] culumnName = { "����", "ǰ��", "����", "���", "�԰���", "���" };
		String[] typeName = {"����", "�����", "�Ļ��", "���"};
		
		// ������Ʈ ����
		JFrame menuFrame = new JFrame("��� ��ȸ");
		dtm = new DefaultTableModel(culumnName, 0) {
			// �������� ���ϰ� �ϴ� �ʵ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JLabel labelType = new JLabel("���� : ", JLabel.RIGHT); // ���������� ���ĵ�
		JLabel labelProduct = new JLabel("��ǰ�� : ", JLabel.RIGHT);
		JLabel labelPrice = new JLabel("���� : ", JLabel.RIGHT);
		JLabel labelCount = new JLabel("��� : ", JLabel.RIGHT);
		JLabel labelDate = new JLabel("�԰��� : ", JLabel.RIGHT);
		JLabel labelRemarks = new JLabel("��� : ", JLabel.RIGHT);

		// ��� �߰� �Է� �ʵ�
		comboType = new JComboBox<String>(typeName);

		textFieldProduct = new JTextField(14);
		textFieldPrice = new JTextField(14);
		textFieldCount = new JTextField(14);
		textFieldDate = new JTextField(14);
		textFieldRemarks = new JTextField(14);
		// �˻� �ʵ�
		typeField = new JTextField(10);
		productField = new JTextField(14);

		JPanel panelLabel = new JPanel();
		JPanel panelTextField = new JPanel();

		jt = new JTable(dtm);
		JScrollPane panel = new JScrollPane(jt);
		JPanel center = new JPanel();
		JPanel north = new JPanel();
		JPanel east = new JPanel();
		JPanel panelL = new JPanel();
		JPanel panelLU = new JPanel();
		JLabel productLabel = new JLabel("��ǰ��");
		JButton searchBtn = new JButton("   ��ü ��ȸ   ");
		JButton insertBtn = new JButton("�� ��");
		JButton deleteBtn = new JButton("�� ��");
		JButton tableSearchBtn2 = new JButton("�� ��");

		// ������Ʈ ���պ�
		center.add(panel);
		north.add(productLabel);
		north.add(productField);
		north.add(tableSearchBtn2);
		north.add(deleteBtn);
		north.add(searchBtn);
		east.add(panelL);

		/** panelL ���� */
		panelL.setLayout(new BorderLayout());
		panelL.add("Center", panelLU);

		/* ������ ��ܺ� (��� ��ǰ �߰� ��) */

		panelLU.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED), "��� �߰�")); // ��ü�� ������

		panelLU.setLayout(new BorderLayout());
		panelLU.setPreferredSize(new Dimension(200, 290));
		panelLU.add("West", panelLabel);
		panelLU.add("Center", panelTextField);
		panelLU.add("South", insertBtn);

		// panelLabel ����
		panelLabel.setLayout(new GridLayout(6, 1, 5, 8));
		panelLabel.add(labelType);
		panelLabel.add(labelProduct);
		panelLabel.add(labelPrice);
		panelLabel.add(labelCount);
		panelLabel.add(labelDate);
		panelLabel.add(labelRemarks);

		// panelTextField ����
		panelTextField.setLayout(new GridLayout(6, 1, 5, 8));
		panelTextField.add(comboType);
		panelTextField.add(textFieldProduct);
		panelTextField.add(textFieldPrice);
		panelTextField.add(textFieldCount);
		panelTextField.add(textFieldDate);
		panelTextField.add(textFieldRemarks);

		menuFrame.add(north, "North");
		menuFrame.add(panel, "Center");
		menuFrame.add(east, "East");

		// ��ư �̺�Ʈ ����
		tableSearchBtn2.addActionListener(new ProductSearchEvent());
		searchBtn.addActionListener(new SearchEvent());
		insertBtn.addActionListener(new InsertEvent());
		deleteBtn.addActionListener(new DeleteEvent());

		// ������ ��ġ ����
		int width = Toolkit.getDefaultToolkit().getScreenSize().width / 5;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height / 7;

		// �������� �⺻���� ��ġ�� ������ ����
		menuFrame.setResizable(false);
		menuFrame.setBounds(width, height, 700, 370);
		menuFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		menuFrame.setVisible(true);

	}

	// ��ȸ��ư�� �����ÿ� menuInfo���̺��� DB���о�ͼ� ���̺� �����ִ� �̺�Ʈó�� Ŭ����
	private class SearchEvent implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			doSearch = true;
			if (list.size() > 0) {
				list.clear();
			}
			try {
				LoginGUI.ac.readMenu(list);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� �����ϴ�.");
				e1.printStackTrace();
			}
			
			// �����ͻ���
			resetRow();

			for (int i = 0; i < list.size(); i++) {
				// String type, String product, String price, String count, String date, String remarks
				String type = list.get(i).getType();
				String product = list.get(i).getProduct();
				String price = list.get(i).getPrice();
				String count = list.get(i).getCount();
				String date = list.get(i).getDate();
				String remarks = list.get(i).getRemarks();
				String[] str = { type, product, price, count, date, remarks };
				dtm.addRow(str);
			}
		}
	}
	// ��ȸ��ư�� �����ÿ� menuInfo���̺��� DB���о�ͼ� ���̺� �����ִ� �̺�Ʈó�� Ŭ��������

	
	// �޴� ǰ����� ������ ���� �̺�Ʈ Ŭ���� ����
	private class DeleteEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int index = jt.getSelectedRow();
			if (index != -1) {
				// getSelectedRow() - ���õ� ��/ 1��° ��
				String product = (String) dtm.getValueAt(jt.getSelectedRow(), 1);
				try {
					LoginGUI.ac.delMenu(product);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getProduct().equals(product)) {
						list.remove(i);
					}
				}
				dtm.removeRow(jt.getSelectedRow());
				JOptionPane.showMessageDialog(null, "ǰ�� " + product + "�� �����߽��ϴ�.");

			} else {
				JOptionPane.showMessageDialog(null, "���̺��� ������ ���� ���� ���� �ϼ���");
			}

		}
	}
	// �޴� ǰ����� ������ ���� �̺�Ʈ Ŭ���� ����
	
	// �����ϰ� �����ϱ� ���� ǰ���� �˻��ϰ� �����ϱ� ���� �̺�Ʈ ó�� Ŭ���� ����
	private class ProductSearchEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String searchProduct = productField.getText();
			if (doSearch == true) {
				if (searchProduct.equals("")) {
					JOptionPane.showMessageDialog(null, "�˻��� ǰ���� �Է��ϼ���");
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getProduct().equals(searchProduct)) {
							resetRow();
							doSearch = false;
							String type = list.get(i).getType();
							String product = list.get(i).getProduct();
							String price = list.get(i).getPrice();
							String count = list.get(i).getCount();
							String date = list.get(i).getDate();
							String remarks = list.get(i).getRemarks();
							String[] str = { type, product, price, count, date, remarks };
							dtm.addRow(str);
						} 
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "��ȸ ���� Ŭ�����ֽʽÿ�");
			}
		}
	}


	// �߰� ��ư�� ������ �߻��ϴ� �̺�Ʈ
	private class InsertEvent implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int flag = 0;

			if (textFieldProduct.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "ǰ���� �Է��Ͻʽÿ�");
			} else if (textFieldPrice.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "������ �Է��Ͻʽÿ�");
			} else if (textFieldCount.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "����� �Է��Ͻʽÿ�");
			} else {
				// ��й�ȣ �ؽ���ȣ ����
				try {
					LoginGUI.ac.insertMenu(comboType.getSelectedItem().toString(), textFieldProduct.getText(), textFieldPrice.getText(),
							textFieldCount.getText(), textFieldDate.getText(), textFieldRemarks.getText());
					JOptionPane.showMessageDialog(null,"�߰��Ǿ����ϴ�.");
					
					textFieldProduct.setText("");
					textFieldPrice.setText("");
					textFieldCount.setText("");
					textFieldDate.setText("");
					textFieldRemarks.setText("");
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "��� ��Ͽ� �����ϼ̽��ϴ�");
				}
			}

		}
	}

	// ���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����
	private void resetRow() {
		if (dtm.getRowCount() > 0) {
			for (int i = dtm.getRowCount() - 1; i > -1; i--) {
				dtm.removeRow(i);
			}
		}
	}
	// /���̺��� ��� �����͸� �����ϴ� �޼ҵ� ����

}
