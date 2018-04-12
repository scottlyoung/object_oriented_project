import java.util.*;

public class TestDriver
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		String userInput;
		String response;
		Session session = new Session("localhost", 8888);
		while (true)
		{
			userInput = input.nextLine();
			if (userInput.equals("q"))
			{
				session.LogOut();
				break;
			}
			List<String> list = new ArrayList<String>();
			list.add(userInput);
			response = session.callRemote("object", "function", list);
			System.out.println(response);
		}
	}
}