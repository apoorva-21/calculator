package clientEnd;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTest {
	private static String hostName = "127.0.0.1";
	private static int portNo = 8086;
	
	public static void main(String[] args) throws IOException
	{
		Socket clientSocket = null;
		DataInputStream dIn = null;
		DataOutputStream dOut = null;
		try 
		{
			clientSocket = new Socket(hostName, portNo);
			dIn = new DataInputStream(clientSocket.getInputStream());
			dOut = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("Data streams at client end initialized!");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		String clientInput = "";
		String serverResponse = "";
		while(serverResponse.indexOf("disconnected") == -1)
		{	
			System.out.println("Enter the expression");
			clientInput = sc.nextLine();
			dOut.writeUTF(clientInput);
		//	dOut.flush();
			if((serverResponse = dIn.readUTF()) != null)
				System.out.println("Server says :" + serverResponse);
		}
		System.out.println("Client shutting down");
		sc.close();
		dIn.close();
		dOut.close();
		clientSocket.close();
	}

}
