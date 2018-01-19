package serverEnd;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerTest {
	private static String hostName = "127.0.0.1";
	private static int portNo = 8086;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		ServerSocket service = null;
		Socket serverSocket = null;
		
		int clientCounter = 0;
		service = new ServerSocket(portNo);
		System.out.println("Server up and running on port: "+portNo);
		while(true)//be ever ready to handle client requests
			try 
			{
				serverSocket = service.accept();
				clientCounter ++;
				ServerThread serverThread = new ServerThread(serverSocket, clientCounter); //instantiate a new Server Thread to serve the newly connected client
				serverThread.start();
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
//		-test cases
//		-execute using junit fw
		}
		
		/*
		String serverInput = "";
		String clientResponse = "";
		while(!clientResponse.equals("exit")){
			if((clientResponse = dIn.readUTF()) != null)
			{
				System.out.println("Client Says : " + clientResponse);
				dOut.writeUTF("noted.");
				//dOut.flush();
			}
		}*/
		
	}
