import java.util.*;
import java.net.*;
import java.io.*;

public class Session 
{
	private static Scanner input = new Scanner(System.in);
	private DataOutputStream out;
	private Socket client ;


	public Session(String serverName, Integer port)
	{
		try
		{
			System.out.println("Connecting to " + serverName + " on port " + port);
			this.client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = this.client.getOutputStream();
			this.out = new DataOutputStream(outToServer);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public LogOut()
	{
		this.client.close();
	}

	public static void main(String [] args) 
	{
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try 
		{
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			while(true)
			{
				String userInput = input.nextLine();
				out.writeUTF(userInput);
				InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);
				System.out.println("Server says " + in.readUTF());
				if (userInput.equals("quit"))
				{
					break;  	
				}	
			}

			client.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public <T> T callRemote(String object_, String meathod_)
	{
		try
		{
			out.writeUTF(object_ + "." + meathod_);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			return (T)(in.readUTF());
		}
		catch (IOException e)
		{
			d.printStackTrace();
		}

	}
}