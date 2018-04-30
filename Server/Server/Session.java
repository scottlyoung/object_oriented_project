package Server;

import java.util.*;
import java.net.*;
import java.io.*;

public class Session extends Thread
{
	private ServerSocket serverSocket;

	public Session(int port) throws IOException
	{
		System.out.println("New Server.Session\n");
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(30000);
	}

	public void run() 
	{
		while(true) 
		{
			try 
			{
				DBManager db = DBManager.getInstance();
				HashMap objectMap = new HashMap();
				objectMap.put("0", db);
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				DataInputStream in = new DataInputStream(server.getInputStream());

				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				while(true)
				{
					String inputString = in.readUTF();
					System.out.println(inputString);
					String queryType = inputString.substring(0,1);
					System.out.println(queryType);
					if (queryType.equals("c"))
                    {
                        System.out.println("Case");
                        String query = inputString.substring(1);
                        System.out.println(query);
                        String[] queryParts = query.split("\\.");
                        System.out.println(queryParts.length);
                        for (String n : queryParts)
                        {
                            System.out.println(n);
                        }
                        out.writeUTF("Thank you for the message.");
                    }
                    else if (queryType.equals("q"))
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