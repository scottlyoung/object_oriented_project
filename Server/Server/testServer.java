package Server;

import java.net.*;
import java.io.*;

public class testServer extends Thread 
{
	private ServerSocket serverSocket;

	public testServer(int port) throws IOException 
	{
		System.out.println("New Server.Session\n");
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run() 
	{
		while(true) 
		{
			try 
			{
				System.out.println("Waiting for client on port " + 
				serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();

				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				while(true)
				{
					DataInputStream in = new DataInputStream(server.getInputStream());
					String inputString = in.readUTF();
					System.out.println(inputString);
					DataOutputStream out = new DataOutputStream(server.getOutputStream());
					out.writeUTF("Thank you for the message.");
					if (inputString.equals("q"))
					{
						break;
					}
				}
				server.close();

			}
			catch (SocketTimeoutException s) 
			{
				System.out.println("Socket timed out!");
				break;
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String [] args) 
	{
		int port = Integer.parseInt(args[0]);
		try 
		{
			Thread t = new testServer(port);
			t.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}