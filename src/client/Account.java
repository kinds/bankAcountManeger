package client;

public class  Account {
	private int ID;
	private String name;
	private int code;
	private static float amount; //amount是静态的，不随着实例改变
	private boolean isOpen = false;
	private Sop sop;
	
	/**
	 * 登陆账户
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
			sop.out(name+"账户登陆");
		}
		else {
			isOpen = false;
			sop.out(name+"账户登陆失败");
		}
		return isOpen;
	}
	/**
	 * 登出账户
	 * @return
	 */
	public synchronized boolean logOut()
	{
		if(isOpen == true)
		{
			isOpen = false;
			sop.out(name+"账户登出");
		}
		else {
			sop.out(name+"账户没有登陆");
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
			sop.out("余额修改："+amount+"   增加："+addnumber);
			return true;
		}
		else {
			sop.out("账户没有打开，没有进行增加权限操作");
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
				sop.out("余额修改为："+amount+"   减少："+subtractionnumber);
			}
			else {
				sop.out("账户余额不足,余额为："+amount);
				return false;
			}
			return true;
		}
		else {
			sop.out("账户没有打开，没有进行减少权限操作");
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
