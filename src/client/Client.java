package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
	
	private List<Transaction> tsList1 = new ArrayList<Transaction>();
	private List<Transaction> tsList2 = new ArrayList<Transaction>();
	private List<Transaction> tsList3 = new ArrayList<Transaction>();
	private static Sop sop = new Sop("client");
	
	//private DataOutputStream toServer;
	//private DataInputStream fromServer;
	
	
	/**
	 * 新建三个list，每个里面存放100个对账户的造作事务
	 */
	private void initialList()
	{
		for (int i = 0; i < 100; i++) {
			tsList1.add(new Transaction(i));
			tsList2.add(new Transaction(i+20));
			tsList3.add(new Transaction(i+40));
		}
	}
	/**
	 * 输出事务列表
	 * @param tsList
	 */
	private void sopList(List<Transaction> tsList)
	{
		Sop sop = new Sop("client"+tsList.hashCode());
		for (Iterator iterator = tsList.iterator(); iterator.hasNext();) {
			Transaction transaction = (Transaction) iterator.next();
			sop.out(transaction.toString());
		 }
	}
	
	
	private boolean postTs(Transaction t,int list,DataOutputStream toServer)
	{
		try {
			toServer.writeUTF(t.TSString());
			toServer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sop.out(list+t.toString());
		return true;
	}
	
	private Thread threadStart(final List<Transaction> tsList,final int port) 
	{
		Thread thread = new Thread(){
			DataOutputStream toServer;
			DataInputStream fromServer;
			public void run() {
				
				try {
					
					Socket socket = new Socket("127.0.0.1",port);
					fromServer = new DataInputStream(socket.getInputStream());
					toServer = new DataOutputStream(socket.getOutputStream());
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				for (Iterator iterator = tsList.iterator(); iterator.hasNext();) {
					Transaction transaction = (Transaction) iterator.next();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					postTs(transaction,tsList.hashCode(),toServer);
				}
				try {
					toServer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		return thread;
	}
	
	public Client(){
		initialList();
//		Socket socket;
//		try {
//			socket = new Socket("127.0.0.1",7000);
//			fromServer = new DataInputStream(socket.getInputStream());
//			toServer = new DataOutputStream(socket.getOutputStream());
//			//System.out.println(toServer);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static enum ClientType
	{
		ATM,
		TS_OL,//online transition
	}
	
	 public static void main(String[] args)
	 {
		 Client client = new Client();
		 
		 client.threadStart(client.getTsList1(),7000).start(); 
		 client.threadStart(client.getTsList1(),7001).start();
		 client.threadStart(client.getTsList1(),7002).start();
		 
		 
		 //double area = fromServer.readDouble();
		 sop.out("");
	 }
	 
	 
	 /**
		 * @return the tsList1
		 */
		public List<Transaction> getTsList1() {
			return tsList1;
		}

		/**
		 * @param tsList1 the tsList1 to set
		 */
		public void setTsList1(List<Transaction> tsList1) {
			this.tsList1 = tsList1;
		}

		/**
		 * @return the tsList2
		 */
		public List<Transaction> getTsList2() {
			return tsList2;
		}

		/**
		 * @param tsList2 the tsList2 to set
		 */
		public void setTsList2(List<Transaction> tsList2) {
			this.tsList2 = tsList2;
		}

		/**
		 * @return the tsList3
		 */
		public List<Transaction> getTsList3() {
			return tsList3;
		}

		/**
		 * @param tsList3 the tsList3 to set
		 */
		public void setTsList3(List<Transaction> tsList3) {
			this.tsList3 = tsList3;
		}
	
}
