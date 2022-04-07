//�ڸ� �����ϸ� ����ڿ��� ���̴� GUI
package pc;

import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class UserGUI extends JFrame {
	public static String name;

	public UserGUI() {
		long start = System.currentTimeMillis();
		setTitle("User");
		setBounds(10, 50, 400, 1000);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH�� mm�� ss�� ");

		// x��ư Ŭ�� �� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int index = LoginGUI.index1;
		name = LoginGUI.ac.getPerson(LoginGUI.index1).getName(); // �α��� �� ������ �̸��� �ҷ��� ������ ����
		JPanel panel_1 = new JPanel();
		JPanel panel_2 = new JPanel();
		JLabel label = new JLabel(name); // �α��� �� ������ �̸��� �־ �۾� ����
		label.setFont(new Font("�����ٸ����", Font.BOLD, 14)); // ��Ʈ �Ӽ� ����
		JLabel label_1 = new JLabel("�� �ȳ��ϼ���"); // �α��� �� ������ �̸��� �־ �۾� ����
		label_1.setFont(new Font("�����ٸ����", Font.PLAIN, 12)); // ��Ʈ �Ӽ� ����
		panel_2.add(label);
		panel_2.add(label_1);

		// �α׾ƿ� ��ư �����
		JPanel panel_3 = new JPanel();
		JButton logoutbutton = new JButton("Log out"); // "Log out"��ư �����
		logoutbutton.setOpaque(false); // ��ư�� �����ϰ� true->������
		logoutbutton.setContentAreaFilled(false); // ��ư ��� ���ֱ�
		logoutbutton.setBorderPainted(false); // ��ư �׵θ� ���ֱ�
		panel_3.add(logoutbutton);
		// �α׾ƿ� ��ư�� ������ "�α׾ƿ� �Ǿ����ϴ�"�˸�â�� ���ÿ� â�� �������.
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long logout = System.currentTimeMillis ();
				long usertime = (long) ((logout - start)/1000.0); // / 60000;
				try {
					LoginGUI.ac.modifytime(index,usertime);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					LoginGUI.ac.logoutModify(index, "��밡��");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�. ī���Ϳ��� �������ּ���.");
				dispose();
			}
		});
		panel_1.add(panel_2);
		panel_1.add(panel_3);
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JPanel panel_4 = new JPanel();
		JLabel starttimelabel = new JLabel("���� �ð� : ");
		panel_4.add(starttimelabel);
		String startt = timeFormat.format(new Date(start));
		JLabel starttime = new JLabel(startt);
		panel_4.add(starttime);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(1, 2));
		JButton goodsbtn = new JButton("����");
		JButton chatbtn = new JButton("�����ڿ� ä��");
		panel_5.add(goodsbtn);
		panel_5.add(chatbtn);
		panel_4.add(panel_5);
		getContentPane().add(panel_4, BorderLayout.CENTER);

		// ��ư �̺�Ʈ
		goodsbtn.addActionListener(new MenuMgrEvent());
		chatbtn.addActionListener(new ChatMgrEvent());

		// ���� ��ܿ� ������ â ����
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width), (screenSize.height - screenSize.height));

		setVisible(true);
		setResizable(false);
	}

	// �Ŵ� ��ư�� ������ �߻��ϴ� �̺�Ʈ
	private class MenuMgrEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new UserMenuGUI();

		}
	}

	// ä�� ��ư�� ������ �߻��ϴ� �̺�Ʈ
	private class ChatMgrEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String ipAdress = LoginGUI.ac.getPerson(LoginGUI.index1).getIp();
			new ClientFrame(ipAdress, "8888");
		}
	}


}
