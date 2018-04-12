import java.util.*;
import java.net.*;
import java.io.*;

public class Session 
{
	private DataOutputStream out;
	private Socket client;

	public String Session(String serverName, Integer port)
	{
		try
		{
			System.out.println("Connecting to " + serverName + " on port " + port);
			this.client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = this.client.getOutputStream();
			this.out = new DataOutputStream(outToServer);


			return ;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public void LogOut()
	{
		try
		{
			out.writeUTF("q");
			this.client.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public <T> T callRemote(String object_, String meathod_, List<String> paramaters_)
	{
		try
		{
			String message = "c" + object_ + "." + meathod_;
			for (String s : paramaters_)
			{
				message += "." + s;
			}
			out.writeUTF(message);
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			return (T)(in.readUTF());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}