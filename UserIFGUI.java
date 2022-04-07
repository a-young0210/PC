//������ GUI
//������� ������ ������ �� �ִ� GUI
package pc;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserIFGUI extends JFrame{
	public UserIFGUI(String PCNum) {
		setTitle("User Information");
		setBounds(10, 50, 200, 200);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		JPanel namepanel = new JPanel();
		JPanel idpanel = new JPanel();
		JPanel timepanel = new JPanel();
		try {
			namepanel.add(new JLabel("�̸�: "));
			namepanel.add(new JLabel(LoginGUI.ac.getPerson(LoginGUI.ac.useCheck(PCNum)).getName()));
			idpanel.add(new JLabel("���̵�: "));
			idpanel.add(new JLabel(LoginGUI.ac.getPerson(LoginGUI.ac.useCheck(PCNum)).getId()));
			timepanel.add(new JLabel("�ð�: "));
			timepanel.add(new Label(Double.toString(LoginGUI.ac.getPerson(LoginGUI.ac.useCheck(PCNum)).getTime())));
		} catch (Exception e) {
		}
		panel.add(namepanel);
		panel.add(idpanel);
		panel.add(timepanel);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		JButton chatbtn = new JButton("ä���ϱ�");
		
		// ä�� ��ư Ŭ���ϸ� �ش� ����ڿ��� ä��
		chatbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ServerFrame("8888");
			}
		});
		
		
		panel_1.add(chatbtn);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		setVisible(true);
		setResizable(false);
	}

}
