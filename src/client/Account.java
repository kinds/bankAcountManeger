package client;

public class  Account {
	private int ID;
	private String name;
	private int code;
	private static float amount; //amount�Ǿ�̬�ģ�������ʵ���ı�
	private boolean isOpen = false;
	private Sop sop;
	
	/**
	 * ��½�˻�
	 * @param ID
	 * @param code
	 * @return 
	 * @return
	 */
	public  synchronized boolean   login(int ID,int code)
	{
		if(this.ID == ID && this.code == code)
		{
			isOpen = true;
			sop.out(name+"�˻���½");
		}
		else {
			isOpen = false;
			sop.out(name+"�˻���½ʧ��");
		}
		return isOpen;
	}
	/**
	 * �ǳ��˻�
	 * @return
	 */
	public synchronized boolean logOut()
	{
		if(isOpen == true)
		{
			isOpen = false;
			sop.out(name+"�˻��ǳ�");
		}
		else {
			sop.out(name+"�˻�û�е�½");
		}
		return isOpen;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public float getCount() {
		return amount;
	}
	public void setCount(float count) {
		this.amount = count;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, String name, int code, float count) {
		super();
		ID = id;
		this.name = name;
		this.code = code;
		this.amount = count;
		sop = new Sop(name);
	}
	
	public synchronized boolean countAdd(float addnumber)
	{
		if(isOpen == true)
		{
			amount+= addnumber;
			sop.out("����޸ģ�"+amount+"   ���ӣ�"+addnumber);
			return true;
		}
		else {
			sop.out("�˻�û�д򿪣�û�н�������Ȩ�޲���");
			return false;
		}
		
	}
	public synchronized boolean countsubtraction(float subtractionnumber)
	{
		if(isOpen == true)
		{
			if(amount-subtractionnumber>=0)
			{
				amount -= subtractionnumber;
				sop.out("����޸�Ϊ��"+amount+"   ���٣�"+subtractionnumber);
			}
			else {
				sop.out("�˻�����,���Ϊ��"+amount);
				return false;
			}
			return true;
		}
		else {
			sop.out("�˻�û�д򿪣�û�н��м���Ȩ�޲���");
			return false;
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Count----->ID="+ID+ " name=" +name+ " code=" +code+ " count=" +amount;
	}
	
	public boolean makeProfit(float profit)
	{
		
		return true;
	}
	
	
	
}
