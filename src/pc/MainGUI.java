//����� GUI
//�ڸ� ������ �� �� ����
package pc;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.*;

public class MainGUI extends JFrame {
	public MainGUI() {
		setTitle("PC��");
		setBounds(10, 50, 650, 510);
		//���� �г�
		JPanel panel = new JPanel();
		
		//ù°��(�����г�)
		JPanel panel_1 = new JPanel();
		JButton btn_1 = new JButton();
		btn_1.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_1.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_1, "1��", 1);
			}
		});
		usePC(btn_1, "1��");
		JButton btn_2 = new JButton();
		btn_2.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_2.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_2, "2��", 2);
			}
		});
		usePC(btn_2, "2��");
		JButton btn_3 = new JButton();
		btn_3.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_3.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_3, "3��", 3);
			}
		});
		usePC(btn_3, "3��");
		JButton btn_4 = new JButton();
		btn_4.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_4.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_4, "4��", 4);
			}
		});
		usePC(btn_4, "4��");
		panel_1.add(btn_1);
		panel_1.add(btn_2);
		panel_1.add(btn_3);
		panel_1.add(btn_4);
		
		//��°��(�����г�)
		JPanel panel_2 = new JPanel();
		JButton btn_5 = new JButton();
		btn_5.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_5.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_5, "5��", 5);
			}
		});
		usePC(btn_5, "5��");
		JButton btn_6 = new JButton();
		btn_6.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_6.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_6, "6��", 6);
			}
		});
		usePC(btn_6, "6��");
		JButton btn_7 = new JButton();
		btn_7.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_7.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_7, "7��", 7);
			}
		});
		usePC(btn_7, "7��");
		JButton btn_8 = new JButton();
		btn_8.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_8.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_8, "8��", 8);
			}
		});
		usePC(btn_8, "8��");
		panel_2.add(btn_5);
		panel_2.add(btn_6);
		panel_2.add(btn_7);
		panel_2.add(btn_8);
				
		//��°��(�����г�)
		JPanel panel_3 = new JPanel();
		JButton btn_9 = new JButton();
		btn_9.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_9.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_9, "9��", 9);
			}
		});
		usePC(btn_9, "9��");
		JButton btn_10 = new JButton();
		btn_10.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_10.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_10, "10��", 10);
			}
		});
		usePC(btn_10, "10��");
		JButton btn_11 = new JButton();
		btn_11.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_11.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_11, "11��", 11);
			}
		});
		usePC(btn_11, "11��");
		JButton btn_12 = new JButton();
		btn_12.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_12.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_12, "12��", 12);
			}
		});
		usePC(btn_12, "12��");
		panel_3.add(btn_9);
		panel_3.add(btn_10);
		panel_3.add(btn_11);
		panel_3.add(btn_12);
		
		//��°��(�����г�)
		JPanel panel_4 = new JPanel();
		JButton btn_13 = new JButton();
		btn_13.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_13.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_13, "13��", 13);
			}
		});
		usePC(btn_13, "13��");
		JButton btn_14 = new JButton();
		btn_14.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_14.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_14, "14��", 14);
			}
		});
		usePC(btn_14, "14��");
		JButton btn_15 = new JButton();
		btn_15.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_15.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_15, "15��", 15);
			}
		});
		usePC(btn_15, "15��");
		JButton btn_16 = new JButton();
		btn_16.setPreferredSize(new Dimension(100, 100));	//��ư ������
		btn_16.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		btn_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPC(btn_16, "16��", 16);
			}
		});
		usePC(btn_16, "16��");
		panel_4.add(btn_13);
		panel_4.add(btn_14);
		panel_4.add(btn_15);
		panel_4.add(btn_16);
		
		//���� �гο� ���� �гε��� �־ CENTER�� ��ġ�ϱ�
		panel.add(panel_1);
		panel.add(panel_2);
		panel.add(panel_3);
		panel.add(panel_4);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		setVisible(true);
		setResizable(false);
	}
	
	//�ڸ��� �̿밡������ Ȯ���ϴ� �Լ�
	void checkPC(Object btn, String PCNum, int num) {
		if(((AbstractButton) btn).getText() == "��� ����") {
			int accountOption = JOptionPane.showConfirmDialog(null, "�ڸ��� �����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION);
			if(accountOption == JOptionPane.YES_OPTION) {
				if(LoginGUI.ac.getPerson(LoginGUI.index1).getState().equals("��밡��")) {
					try {
						((Management) LoginGUI.ac).stateModify(LoginGUI.index1, PCNum);
						String ipAd = getServerIp();
						LoginGUI.ac.getIp(LoginGUI.index1, ipAd);
						new UserGUI();
						dispose();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "����.", "����",JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "���� �α��εǾ� �־� �ڸ��� ������ �� �����ϴ�.", "����",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
		else 
			JOptionPane.showMessageDialog(null, "�̹� ������� �¼��Դϴ�.", "����",JOptionPane.WARNING_MESSAGE);
	}
	
	void usePC(Object btn, String PCNum) {
		try {
			LoginGUI.ac.useCheck(PCNum);
			((AbstractButton) btn).setText("�����");
			((Component) btn).setBackground(Color.lightGray);	//���� ����
			((AbstractButton) btn).setBorderPainted(false);	//��ư �׵θ� ���ֱ�
			((Component) btn).setForeground(Color.red);	//���ڻ� ����
		} catch (Exception e) {
			((AbstractButton) btn).setText("��� ����");
			((Component) btn).setBackground(Color.white);	//���� ����
			((Component) btn).setForeground(Color.black);	//���ڻ� ����
		}
	}
	
	private String getServerIp() {
		
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		}
		catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
			
		if( local == null ) {
			return "";
		}
		else {
			String ip = local.getHostAddress();
			return ip;
		}
			
	}
}