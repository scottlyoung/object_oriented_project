package Server;

import java.net.*;
import java.io.*;

public class Session extends Thread
{
	private ServerSocket serverSocket;

	public Session(int port) throws IOException 
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
				DBManager db = new DBManager();
				System.out.println((int)db);
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				DataInputStream in = new DataInputStream(server.getInputStream());

				out.writeUTF("o" + db.toString());

				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				while(true)
				{
					String inputString = in.readUTF();
					System.out.println(inputString);
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
			Thread t = new Session(port);
			t.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}