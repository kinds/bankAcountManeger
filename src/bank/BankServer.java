package bank;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import client.Account;
import client.Transaction;
import client.Transaction.TransactionTpye;


public class BankServer {
	
	
	private Map<Integer,Account> countMap = new HashMap<Integer,Account>();
	//private static List<Count> countList = new ArrayList<Count>();
	/**
	 * @param args
	 */
	
	public BankServer() {
		super();
		fillcountMap();
	}
	
	public BankServer(Map<Integer, Account> countMap) {
		super();
		this.countMap = countMap;
	}
	
	public boolean doTs(Transaction transaction)
	{
		
		boolean result = false;
		transaction.getTimeStamp();
		int accountID = transaction.getAccountNumber();
		Account count = countMap.get(accountID);
		count.login(accountID, accountID);
		try {Thread.sleep(100);} catch (InterruptedException e){e.printStackTrace();}
		
		if(transaction.getTransactionTpye().equals(TransactionTpye.d))
			result =  count.countAdd(transaction.getAmount());
			
		else {
			result = count.countsubtraction(transaction.getAmount());
		}
		count.logOut();
		return result;
		
	}
	
	public static void main(String[] args) {
		
		
//		fillcountMap();
//		Account count = countMap.get(4);
//		System.out.println(count.toString());
//		
//		
//		count.countAdd(10);
//		count.logOut();
//		
//		count.login(4,4);
//		System.out.println(count.toString());
//		count.countAdd(10);
//		
//		count.countsubtraction(100);
//		System.out.println(count.toString());
//		count.logOut();
//		
//		count.countAdd(10);
	}
	
	
	


	private  void fillcountMap()
	{
		for (int i = 0; i < 50; i++) {
			Account count = new Account(i,"count"+i,i,i*10);
			countMap.put(i, count);
		}
	}
	
}
