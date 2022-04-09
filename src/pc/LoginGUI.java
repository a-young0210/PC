//�α��� GUI
package pc;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class LoginGUI extends JFrame{
	static Management ac = null;
	static int index1;
	public LoginGUI() {
		setTitle("Login");
		setBounds(10, 50, 400, 300);
		//x��ư Ŭ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		JTextField id = new JTextField();	//���̵� ���� �ؽ�Ʈ �ڽ�
		id.setColumns(10);
		JPasswordField pw = new JPasswordField(); //��й�ȣ�� ���� �ؽ�Ʈ �ڽ�
		pw.setColumns(10);
		panel.add(new JLabel("ID")); //���̵� �� �ֱ�
		panel.add(id);
		panel.add(new JLabel("Password")); //��й�ȣ �� �ֱ�
		panel.add(pw);
		JButton signupbtn = new JButton("ȸ������"); //"ȸ������"��ư �����
		signupbtn.setOpaque(false);	//��ư�� �����ϰ� true->������
		signupbtn.setContentAreaFilled(false);	//��ư ��� ���ֱ�
		signupbtn.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		panel.add(signupbtn);
		signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpGUI();
			}
		});
		JButton findbtn = new JButton("���̵�/��й�ȣ ã��");	//"���̵�/��й�ȣ ã��"��ư �����
		findbtn.setOpaque(false);	//��ư�� �����ϰ� true->������
		findbtn.setContentAreaFilled(false);	//��ư ��� ���ֱ�
		findbtn.setBorderPainted(false);	//��ư �׵θ� ���ֱ�
		panel.add(findbtn);
		findbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindGUI();
			}
		});
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		//Ȯ��, ��� ��ư �����
		JPanel panel_1 = new JPanel();
		JButton okbtn = new JButton("Login");
		panel_1.add(okbtn);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		//Login��ư
		okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"���̵� �Է����ּ���.");
				}
				else if(pw.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"��й�ȣ�� �Է����ּ���.");
				}
				else {
					try {
						index1 = ac.searchId(id.getText());	//id �ؽ�Ʈ�ʵ忡 �ִ� ���� �˻��ؼ� �ε��� �� ����
						if(pw.getText().equals(ac.getPerson(index1).getPw())) {	//�ε��� ���� �̿��� pw�� �ҷ��ͼ� pw �ؽ�Ʈ�ʵ� ���� ���Ͽ� ������ �α��� ��
							//������̸� �����ȭ�� ����
							if(ac.getPerson(index1).getUser().equals("������")) {
								id.setText("");
								pw.setText("");
								new AdministratorGUI();
							}
							else {
								id.setText("");
								pw.setText("");
								new MainGUI();
							}
						}	
						else {
							JOptionPane.showMessageDialog(null,"��й�ȣ�� �߸��Ǿ����ϴ�.");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"���̵� �߸��Ǿ����ϴ�.");
					}	
					
				}
			}
		});
		
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) throws Exception {
		ac = new Management("org.mariadb.jdbc.Driver" ,"jdbc:mariadb://localhost:3306/PC", "1234");
		new LoginGUI();
	}
}
