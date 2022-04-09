package pc;

public class Menu {
	private String type;		//���� �ʵ�
	private String product;		//ǰ�� �ʵ�
	private String price; 		//���� �ʵ�
	private String count;		//��� �ʵ�
	private String date;		//�԰��� �ʵ�
	private String remarks;		//��� �ʵ�
	
	//"����", "ǰ��", "����", "���", "�԰���", "���" 
	public Menu(String type, String product, String price, String count, String date, String remarks){ 
		this.type=type;
		this.product=product;
		this.price=price;
		this.count=count;
		this.date=date;
		this.remarks=remarks;
	}

	public String getType() {
		return type;
	}
	public String getProduct() {
		return product;
	}
	public String getPrice() {
		return price;
	}
	public String getCount() {
		return count;
	}
	public String getDate(){ 			
		return date;
	}
	public String getRemarks(){ 			
		return remarks;
	}
}
