//���̵�, ��й�ȣ ã�� GUI
package pc;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FindGUI extends JFrame{
	public FindGUI() {
		setTitle("ID/PW Find");
		setBounds(10, 50, 400, 300);
		
		JTabbedPane tab = new JTabbedPane();
		JPanel IDfind = new JPanel();
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(2, 2));
		JTextField name = new JTextField();
		name.setColumns(10);
		JTextField phone = new JTextField();
		phone.setColumns(10);
		panel_1.add(new JLabel("�̸��� �����ּ���."));
		panel_1.add(name);
		panel_1.add(new JLabel("��ȭ��ȣ�� �����ּ���."));
		panel_1.add(phone);
		IDfind.add(panel_1);
		JPanel panel_2 = new JPanel();
		JButton findbutton = new JButton("ã��"); //"ã��"��ư �����
		findbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"�̸��� �Է����ּ���.");
				}
				else if(phone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"��ȭ��ȣ�� �Է����ּ���.");
				}
				else {
					int index;
					try {
						index = LoginGUI.ac.searchPhone(phone.getText());
						if(name.getText().equals(LoginGUI.ac.getPerson(index).getName())) {
							JOptionPane.showMessageDialog(null,LoginGUI.ac.getPerson(index).getId());
						}
						else {
							JOptionPane.showMessageDialog(null,"��ϵ� ȸ���� �ƴմϴ�.");
						}
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"��ϵ� ȸ���� �ƴմϴ�.");
					}
				}
			}
		});
		JButton cancelbutton = new JButton("���"); //"���"��ư �����
		//"���"��ư�� ������ ���� â�� ������.
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_2.add(findbutton);
		panel_2.add(cancelbutton);
		IDfind.add(panel_2);
		
		
		JPanel PWfind = new JPanel();
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(2, 2));
		JTextField name1 = new JTextField();
		name1.setColumns(10);
		JTextField id = new JTextField();
		id.setColumns(10);
		panel_3.add(new JLabel("�̸��� �����ּ���."));
		panel_3.add(name1);
		panel_3.add(new JLabel("���̵� �����ּ���."));
		panel_3.add(id);
		PWfind.add(panel_3);
		JPanel panel_4 = new JPanel();
		JButton findbutton1 = new JButton("ã��"); //"ã��"��ư �����
		findbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"�̸��� �Է����ּ���.");
				}
				else if(id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"���̵� �Է����ּ���.");
				}
				else {
					int index;
					try {
						index = LoginGUI.ac.searchId(id.getText());
						if(name1.getText().equals(LoginGUI.ac.getPerson(index).getName())) {
							JOptionPane.showMessageDialog(null,LoginGUI.ac.getPerson(index).getPw());
						}
						else {
							JOptionPane.showMessageDialog(null,"��ϵ� ȸ���� �ƴմϴ�.");
						}
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null,"��ϵ� ȸ���� �ƴմϴ�.");
					}
				}
			}
		});
		JButton cancelbutton1 = new JButton("���"); //"���"��ư �����
		//"���"��ư�� ������ ���� â�� ������.
		cancelbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_4.add(findbutton1);
		panel_4.add(cancelbutton1);
		PWfind.add(panel_4);
		
		//�ǻ���
		tab.add("���̵� ã��", IDfind);
		tab.add("��й�ȣ ã��", PWfind);
		add(tab);
		
		setVisible(true);
	}
}