import java.util.*;
import java.net.*;
import java.io.*;

public class testClient 
{
	private static Scanner input = new Scanner(System.in);

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
}