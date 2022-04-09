package pc;
import java.io.Serializable;

public class Member implements Serializable{
	private String user;		 //��������� ���������� �����ϴ� �ʵ�
	private String name;		 // �̸� �ʵ�
	private String phoneNum;	 // ��ȭ��ȣ �ʵ�
	//private int ip;				 // ������ �ʵ�
	private String id;			 // ���̵� �ʵ�
	private String pw;			 // ��й�ȣ �ʵ�
	private double time;		 // ���ð� �ʵ�
	private String state;		//��������� �ƴ��� �����ϴ� �ʵ�
	private String ip;
	
	//�⺻ ������
	public Member(String user, String name, String phoneNum, String id, String pw, double time, String state, String ip){ 
		this.user=user;
		this.name=name;
		this.phoneNum = phoneNum;
		this.ip = ip;
		this.id = id;
		this.pw = pw;
		this.time = time;
		this.state = state;
	}
	public Member() {
		this.user = user;
		this.name=null;
		this.phoneNum = null;
		this.ip = ip;
		this.id = id;
		this.pw = pw;
		this.time = time;
		this.state = state;
	}
	
	public void setUser(String user){ 		// ���� ������
		this.user = user;
	}
	public void setName(String name){ 		// �̸� ������
		this.name=name;
	}		
	public void setPhoneNum(String phoneNum){ 	// ��ȭ��ȣ ������
		this.phoneNum = phoneNum;
	}
	public void setIp(String ip) {	//ip ������
		this.ip = ip;
	}
	public void setId(String id) {	// ���̵� ������
		this.id = id;
	}
	public void setPw(String pw){ 		// ��й�ȣ ������
		this.pw = pw;
	}
	public void setTime(double time) {	//�̿�ð� ������
		this.time = time;
	}
	public void setState(String state) {	//���� ������
		this.state = state;
	}
	public String getUser(){ 			// ���� ������
		return user;
	}
	public String getName(){ 			// �̸� ������
		return name;
	}
	public String getPhoneNum(){ 		// ��ȭ��ȣ ������
		return phoneNum;
	}
	public String getIp() {	//ip ������
		return ip;
	}
	public String getId() {		//���̵� ������
		return id;
	}
	public String getPw(){ 		// ��й�ȣ ������
		return pw;
	}
	public double getTime() {	//�̿�ð� ������
		return time;
	}
	public String getState() {	//���� ������
		return state;
	}
}
