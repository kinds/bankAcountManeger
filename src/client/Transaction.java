package client;

public class Transaction {
	
	public enum TransactionTpye{w,d}
	
	private int TimeStamp;
	private int accountNumber;
	private TransactionTpye transactionTpye;
	private float amount;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int timeStamp, int accountNumber,
			TransactionTpye transactionTpye, float amount) {
		super();
		this.TimeStamp = timeStamp;
		this.accountNumber = accountNumber;
		this.transactionTpye = transactionTpye;
		this.amount = amount;
	}
	
	private TransactionTpye getRandomTsType()
	{
		if(Math.random() > 0.50){
			return TransactionTpye.w;
		}
		else {
			return TransactionTpye.d;
		}
	}
	
	private TransactionTpye getTsTypeFromString(String s)
	{
		if(s.equals("w")){
			return TransactionTpye.w;
		}
		else {
			return TransactionTpye.d;
		}
	}
	
	public Transaction(int random)
	{
		this.TimeStamp = random;
		this.accountNumber = (int)(Math.random()*50);
		this.transactionTpye = getRandomTsType();
		this.amount = (float)(Math.random()*1000);
		//return new Transaction(i,(int)(Math.random()*50),getRandomTsType(),(float)(Math.random()*10));
	}
	
	private Transaction getTransactionFromString(String ts)
	{
		String[] s = ts.split("#");
		Transaction transaction = new Transaction();
		transaction.TimeStamp = Integer.parseInt(s[0]);
		transaction.accountNumber = Integer.parseInt(s[1]);
		transaction.transactionTpye = getTsTypeFromString(s[2]);
		transaction.amount = Float.parseFloat(s[3]);
		return transaction;
	}
	
	public Transaction(String ts)
	{
		String[] s = ts.split("#");
		this.TimeStamp = Integer.parseInt(s[0]);
		this.accountNumber = Integer.parseInt(s[1]);
		this.transactionTpye = getTsTypeFromString(s[2]);
		this.amount = Float.parseFloat(s[3]);
		//return new Transaction(i,(int)(Math.random()*50),getRandomTsType(),(float)(Math.random()*10));
	}
	
	/**
	 * @return the timeStamp
	 */
	public int getTimeStamp() {
		return TimeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(int timeStamp) {
		TimeStamp = timeStamp;
	}
	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the transactionTpye
	 */
	public TransactionTpye getTransactionTpye() {
		return transactionTpye;
	}
	/**
	 * @param transactionTpye the transactionTpye to set
	 */
	public void setTransactionTpye(TransactionTpye transactionTpye) {
		this.transactionTpye = transactionTpye;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TimeStamp="+TimeStamp+"  accountNumber="+accountNumber+"  transactionTpye="+transactionTpye+"  amount="+amount;
	}
	
	public String TSString() {
		return TimeStamp+"#"+accountNumber+"#"+transactionTpye+"#"+amount;
	}
	
	
	
}
