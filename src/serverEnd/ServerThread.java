package serverEnd;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import calculatorLogic.BusinessLogic;


public class ServerThread extends Thread {
	Socket currentThreadSocket;
	String currentClientID;
	ArrayList <String> calculations = new ArrayList<String>();
	public ServerThread(Socket socket,int count)
	{
		currentThreadSocket = socket;
		currentClientID = "Client"+ count;
	}
	public void run()
	{
		try
		{
			//create streams for data flow between client-server
			DataInputStream dIn = new DataInputStream(currentThreadSocket.getInputStream());
			DataOutputStream dOut = new DataOutputStream(currentThreadSocket.getOutputStream());
			dOut.flush();
			String clientInput = "";
			BusinessLogic solver = new BusinessLogic();
			while(!clientInput.equals("exit")){
				if((clientInput = dIn.readUTF()) != null)
				{
					double result = solver.solve(clientInput);
					dOut.writeUTF("Result = " + result);
				}
			}
			dOut.writeUTF("Client " + currentClientID + " disconnected!");
			dIn.close();
			dOut.close();
			currentThreadSocket.close();
			
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
