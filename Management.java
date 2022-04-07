package pc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Management {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	int num; // ������ �޴µ� ����
	double time = 0;

	public Management(String url, String con, String rootpw) throws Exception {// UI���� ������ ����� �� �����ͼ� ��ü ����
		Class.forName(url);
		conn = DriverManager.getConnection(con, "root", rootpw);
		stmt = conn.createStatement();
	}

	public int getCount() { // ��ϵ� ��� �� ������
		try {
			rs = stmt.executeQuery("select * from member;");
			rs.last(); // Ŀ���� �� ������ ������ �̵�
			num = rs.getRow(); // ���� ����
		} catch (Exception se) {
			// System.out.println(se.getMessage());
		} // throw exception
		return num;
	}

	// ���̵� �ߺ� Ȯ�� �޼ҵ�
	public boolean checkId(String id) {
		try {
			// �Ķ���ͷ� �޾ƿ� id�� �˻��Ѵ�.
			rs = stmt.executeQuery("select * from member where id='" + id + "'");

			if (rs.next()) {
				rs.close();
				return true; // true ��ȯ
			} else
				rs.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return false;
	}

	// ��ȭ��ȣ �ߺ� Ȯ�� �޼ҵ�
	public boolean checkPhoneNum(String phoneNum) {
		try {
			// �Ķ���ͷ� �޾ƿ� phoneNum�� �˻��Ѵ�.
			rs = stmt.executeQuery("select * from member where phoneNum='" + phoneNum + "'");

			if (rs.next()) {
				rs.close();
				return true;// true ��ȯ
			} else
				rs.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}

		return false;
	}

	// ���� ��� �޼ҵ�
	public void add(Member P) throws Exception {
		try {
			num = getCount();
			// �޾ƿ� Account ��ü P�� db�� �߰��� �ش�.
			rs = stmt.executeQuery("insert into member(num, user, name, phoneNum, id, pw, time, state) values(" + num
					+ ",'" + P.getUser() + "','" + P.getName() + "','" + P.getPhoneNum() + "','" + P.getId() + "','"
					+ P.getPw() + "','" + P.getTime() + "','" + P.getState() + "');");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ��������� Ȯ��
	public int useCheck(String PCNum) throws Exception {
		int i = -1;
		// �޾ƿ� id�� db���� ã�´�.
		rs = stmt.executeQuery("select * from member where state = '" + PCNum + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // �о�� num �����͸� i�� int������ ����
			return i;
		}
		throw new Exception("��ϵ� �̸� ����");
	}

	// �̸����� ȸ�� ���� ã��
	public int searchName(String name) throws Exception {
		int i = -1;
		// �޾ƿ� id�� db���� ã�´�.
		rs = stmt.executeQuery("select * from member where name = '" + name + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // �о�� num �����͸� i�� int������ ����
			return i;
		}
		throw new Exception("��ϵ� �̸� ����");
	}

	// ���̵�� ����ȸ������ �˻�, ��ϵ� ���̵� ���� ��� �ͼ���
	public int searchId(String id) throws Exception {
		int i = -1;
		// �޾ƿ� id�� db���� ã�´�.
		rs = stmt.executeQuery("select * from member where id = '" + id + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // �о�� num �����͸� i�� int������ ����
			return i;
		}
		throw new Exception("��ϵ� ���̵� ����");
	}

	// ��ȭ��ȣ�� ����ȸ������ �˻�, ��ϵ� ��ȭ��ȣ�� ���� ��� �ͼ���
	public int searchPhone(String phoneNum) throws Exception {
		int i = -1;
		// �޾ƿ� phoneNum�� db���� ã�´�.
		rs = stmt.executeQuery("select * from member where phoneNum = '" + phoneNum + "'");
		while (rs.next()) {
			i = rs.getInt("num"); // �о�� num �����͸� i�� int������ ����
			return i;
		}
		throw new Exception("��ϵ� ��ȭ��ȣ ����");
	}

	// �ð� ���� �޼ҵ�
	public void modifytime(int index, double time) throws SQLException {
		try {
			double newtime = 0;
			rs = stmt.executeQuery("select *from member where num='" + index + "'");
			while (rs.next()) {
				newtime = time + rs.getDouble("time");
			}
			rs = stmt.executeQuery("update member set time='" + newtime + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �ð��� 0���� �����ϴ� �޼ҵ�
	public void zerotime(int index, double time) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set time='" + time + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// state�� ��밡������ �����ϴ� �޼ҵ�
	public void logoutModify(int index, String state) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set state='" + state + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// �ش� ������ ip�� �������� �޼ҵ�
	public void getIp(int index, String ip) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set ip='" + ip + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �������� ���� �޼ҵ�
	public void modify(int index, String name, String phonenum, String id, String pw) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set name='" + name + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set phoneNum='" + phonenum + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set id='" + id + "'where num='" + index + "'");
			rs = stmt.executeQuery("update member set pw='" + pw + "'where num='" + index + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// state�� ��������� �ٲٴ� �޼ҵ�
	public void stateModify(int index, String PCNum) throws SQLException {
		try {
			rs = stmt.executeQuery("update member set state='" + PCNum + "'where num='" + index + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Person ��ü �Ѱ��ִ� �޼ҵ�
	public Member getPerson(int index) {
		String user = "", name = "", phoneNum = "", id = "", pw = "", state = "", ip="";
		try {
			rs = stmt.executeQuery("select * from member where num='" + index + "'");
			while (rs.next()) {
				user = rs.getString("user");
				name = rs.getString("name");
				phoneNum = rs.getString("phoneNum");
				id = rs.getString("id");
				pw = rs.getString("pw");
				time = rs.getDouble("time");
				state = rs.getString("state");
				ip = rs.getString("ip");
			}
			Member person = new Member(user, name, phoneNum, id, pw, time, state, ip);
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// �޴� ��Ͻ����ִ� �޼ҵ�
	public void insertMenu(String type, String product, String price, String count, String date, String remarks)
			throws Exception {
		try {
			// "����", "ǰ��", "����", "���", "�԰���", "���"
			rs = stmt.executeQuery("insert into menu(type, product, price, count, date, remarks) "
					+ "values('" + type + "','" + product + "','" + price + "','" + count + "','"
					+ date + "','" + remarks + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �޴� ���������ִ� �޼ҵ�
	public void delMenu(String product) throws SQLException {
		try {
			rs = stmt.executeQuery("delete from menu where product='" + product + "'");

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�޴� ������ �����߽��ϴ�.");
		}
	}

	// �޴� �Ǹ� �޼ҵ�
	public void sellMenu(String product, String count) {
		try {
			rs = stmt.executeQuery("update menu set count='" + count + "' where product='" + product + "'");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�.");
		}
	}

	// �޴� DB�� ���� ���� �о�´�
	public void readMenu(ArrayList<Menu> list) throws Exception {
		int num = 0;
		try {
			// type, product, price, count, date, remarks
			rs = stmt.executeQuery("select * from menu");
			while (rs.next()) {
				String type = rs.getString("type");
				String product = rs.getString("product");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				list.add(new Menu(type, product, price, count, date, remarks));
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� �������� �ʽ��ϴ�.");
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// �޴�DB���� ���о� ���� �޼ҵ� ����

	// Ư�� ��ǰ ���� �ҷ��´�.
	public Menu readProduct(String product) {

		Menu menuInfo = null;
		try {
			int num = 0;
			rs = stmt.executeQuery("select * from menu where product='" + product + "'");

			while (rs.next()) {

				String type = rs.getString("type");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				menuInfo = new Menu(type, product, price, count, date, remarks);
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� ���� ���� �ʽ��ϴ�.");
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuInfo;
	}
	// �޴�DB���� ���о� ���� �޼ҵ� ����

	// Ư�� ���� ������ �ҷ��´�.
	public Menu[] readType(String type, ArrayList<Menu> typeList) {

		Menu menuInfo[] = null;

		try {

			int num = 0;
			rs = stmt.executeQuery("select * from menu where type='" + type + "'");
			while (rs.next()) {
				String product = rs.getString("product");
				String price = rs.getString("price");
				String count = rs.getString("count");
				String date = rs.getString("date");
				String remarks = rs.getString("remarks");

				typeList.add(new Menu(type, product, price, count, date, remarks));
				num++;
			}
			/*
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� ���� ���� �ʽ��ϴ�.");
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuInfo;

	}
	// �޴�DB���� ���о� ���� �޼ҵ� ����
	
	// ���� ���� �޼ҵ�
	public void salesmodify(int sales) throws SQLException {
		int totalsales = 0;
		try {
			rs = stmt.executeQuery("select sales from sales;");
			while (rs.next()) {
				totalsales = rs.getInt("sales");
			}
			totalsales += sales;
			rs = stmt.executeQuery("update sales set sales='" + totalsales + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���� ��������
	public String getSales() throws Exception {
		int i = -1;
		String salesText = null;
		
		rs = stmt.executeQuery("select sales from sales;");
		while (rs.next()) {
			salesText = String.valueOf(rs.getInt("sales"));
		}
		return salesText;
	}
}
