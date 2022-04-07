//ȸ������ GUI
package pc;
import pc.LoginGUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignUpGUI extends JFrame {
	public SignUpGUI() {
		setTitle("Sign Up");
		setBounds(10, 50, 400, 300);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(5,2)); //����2, ����4�� ������ ���� ĭ �����
		JTextField name = new JTextField(); //"�̸�"���� �ؽ�Ʈ �ڽ�
		name.setColumns(10); //�ؽ�Ʈ�ʵ� ���� ������ ����
		JTextField phonenum = new JTextField(); //"��ȭ��ȣ"���� �ؽ�Ʈ �ڽ�
		phonenum.setColumns(10); //�ؽ�Ʈ�ʵ� ���� ������ ����
		JTextField id = new JTextField(); //"���̵�"���� �ؽ�Ʈ �ڽ�
		id.setColumns(10); //�ؽ�Ʈ�ʵ� ���� ������ ����
		JPasswordField pw = new JPasswordField(); //"��й�ȣ"���� �ؽ�Ʈ �ڽ�
		pw.setColumns(10); //�ؽ�Ʈ�ʵ� ���� ������ ����
		JPasswordField pwcheck = new JPasswordField(); //"��й�ȣ Ȯ��"���� �ؽ�Ʈ �ڽ�
		pwcheck.setColumns(10); //�ؽ�Ʈ�ʵ� ���� ������ ����
		panel_1.add(new JLabel("�̸�")); //"�̸�"�� �ֱ�
		panel_1.add(name);
		panel_1.add(new JLabel("��ȭ��ȣ")); //"��ȭ��ȣ"�� �ֱ�
		panel_1.add(phonenum);
		panel_1.add(new JLabel("���̵�")); //"���̵�"�� �ֱ�
		panel_1.add(id);
		panel_1.add(new JLabel("�ٹй�ȣ")); //"��й�ȣ"�� �ֱ�
		panel_1.add(pw);
		panel_1.add(new JLabel("�ٹй�ȣ Ȯ��")); //"��й�ȣ Ȯ��"�� �ֱ�
		panel_1.add(pwcheck);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		JButton savebutton = new JButton("����"); //"����"��ư �����
		//���� ��ư�� ������ ��ü�� ����� ���̺� ������ �����ִ� ������
		savebutton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//�̸��� ���� �ʾ��� �� �˾�â ����
				if(name.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"�̸��� �Է����ּ���.");
				}
				//��ȭ��ȣ�� ���� �ʾ��� �� �˾�â ����
				else if(phonenum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է����ּ���.");
				}
				//���̵� ���� �ʾ��� �� �˾�â ����
				else if(id.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"���̵� �Է����ּ���.");
				}
				//��й�ȣ�� ���� �ʾ��� �� �˾�â ����
				else if(pw.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"��й�ȣ�� �Է����ּ���.");
				}
				//��й�ȣ Ȯ�ζ��� ���� �ʾ��� �� �˾�â ����
				else if(pwcheck.getText().isEmpty()) { 
					JOptionPane.showMessageDialog(null,"��й�ȣ�� Ȯ�����ּ���.");
				}
				//��� ������ ������ ���
				else {
					boolean check = false;
					//��й�ȣ �ؽ�Ʈ�ڽ��� ��й�ȣ Ȯ�� �ؽ�Ʈ �ڽ��� ��ġ�ϴ��� Ȯ��
					if(pw.getText().equals(pwcheck.getText()) ) {
						check = true;
					} else {
					}
					
					//check�� false�̸� �˾�â ����
					if(check == false) {
						JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					}
					
					//���̵� �ߺ��� �� �˾�â ����
					else if(LoginGUI.ac.checkId(id.getText())==true) {
						JOptionPane.showMessageDialog(null,"�̹� ��ϵ� ���̵��Դϴ�. �ٸ� ���̵� �Է����ּ���");
					}
					
					//��ȭ��ȣ�� �ߺ��� �� �˾�â ����
					else if(LoginGUI.ac.checkPhoneNum(phonenum.getText())==true) { 
						JOptionPane.showMessageDialog(null,"�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
					}
					
					else {
						//ȸ������ ����ϱ�
						try {
							LoginGUI.ac.add(new Member("�����", name.getText(), phonenum.getText(), id.getText(), pw.getText(), 0, "��밡��", "0"));	//�ּҷ� ��� �޼ҵ�
							JOptionPane.showMessageDialog(null,"���ԵǾ����ϴ�."); //��ü�� ���������� �߰� �Ǿ��� �� �ߴ� �˾�â
							dispose();
						} catch (Exception e1) { //�Է��� ���������� ���� �ʾ��� ���
							JOptionPane.showMessageDialog(null,"���Կ� �����߽��ϴ�."); //��ü�� ���������� �߰����� �ʾ��� �� �ߴ� �˾�â
						}
					}
				}
				//�Է� �� �ؽ�Ʈ �ʵ� �� ����
				name.setText("");
				phonenum.setText("");
				id.setText("");
				pw.setText("");
				pwcheck.setText("");
			}
			
		});
		JButton cancelbutton = new JButton("���"); //"���"��ư �����
		//"���"��ư�� ������ ���� â�� ������.
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel_2.add(savebutton);
		panel_2.add(cancelbutton);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		setVisible(true);
		setResizable(false);
	}
}
