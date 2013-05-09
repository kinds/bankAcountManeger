package bank;
//JISUE
import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.org.apache.bcel.internal.generic.NEW;

import client.Transaction;

public class Server extends JFrame{

	private int serverPort;
	
	
	
	private JTextArea jta = new JTextArea();
	private BankServer bankServer = new BankServer();
	public Server(int serverPort)
	{
		setLayout(new BorderLayout());
		add(new JScrollPane(jta),BorderLayout.CENTER);
		setTitle("server");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			jta.append("server started at" + new java.util.Date() + "\n");
			
			//监听来自客户端的请求
			Socket socket = serverSocket.accept();
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			
			while (true) {
				String ts = inputFromClient.readUTF();
				jta.append(ts+"\r\n");
				Transaction transaction = new Transaction(ts);
				boolean b = bankServer.doTs(transaction);
				jta.append(b+"\r\n");
				//double area = Math.PI*r*r;
				//outputToClient.writeDouble(area);
				//jta.append("Area found" + area+ "\n");
			}
			
		} catch (Exception e) {
			System.out.println("setver error");
			e.printStackTrace();
		}
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable(){
			public void run() {
				new Server(7000);	
			}
		}).start();
		
		new Thread(new Runnable(){
			public void run() {
				new Server(7001);	
			}
		}).start();
		
		new Thread(new Runnable(){
			public void run() {
				new Server(7002);	
			}
		}).start();

	}

}
