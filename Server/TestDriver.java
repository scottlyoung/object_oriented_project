import java.util.*;

public class TestDriver
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		// at beginning of main execution I will load all song and artist objects from database
		while(true)
		{
			System.out.println(
				"---Welcome---\n" +
				"1: Log In\n" +
				"2: Create New Account"
			);
			int choice = input.nextInt();
			input.nextLine();

			if(choice == 1)
			{
				// check with database, if account credentials pass then create new Account object and all Playlist objects by pulling info from database
				Account user = new Account("jjjacobsen", "pass123");
				user.setIsAdmin(true);
				// have two control flows for normal users and admins
				if(user.getIsAdmin())
				{
					while(true)
					{
						System.out.println("\n\n\n");
						System.out.println(
							"---Welcome " + user.getName() + "---\n" +
							"1: List Playlists\n" +
							"2: Select Playlist\n" +
							"3: Create Playlist\n" +
							"4: Remove Playlist\n" +
							"5: Search For Songs or Artists\n" +
							"6: Add Song to DB\n" +
							"7: Remove Song from DB\n" +
							"8: Set Account to Admin"
						);
						int choice2 = input.nextInt();
						input.nextLine();

						if(choice2 == 1)
						{
							System.out.println("Playlists: " + user.listPlaylists());
						}

						else if(choice2 == 2)
						{
							System.out.print("Enter Name of Playlist: ");
							String playlistName = input.next();
							input.nextLine();
							Playlist currPlaylist = user.getPlaylist(playlistName);
							if(currPlaylist == null)
							{
								System.out.println("Playlist Not Found");
							}
							else
							{
								boolean contRun = true;
								while(contRun)
								{
									System.out.println("\n\n\n");
									System.out.println(
										"---Playlist Menu---\n" +
										"1: Play Playlist\n" +
										"2: Play Song (from playlist)\n" +
										"3: Remove Song\n" +
										"4: Exit"
									);
									int choice3 = input.nextInt();
									input.nextLine();

									if(choice3 == 1)
									{
										// invoke player here
										System.out.println("option1");
									}
									else if(choice3 == 2)
									{
										// invoke player here
										System.out.println("option2");
									}
									else if(choice3 == 3)
									{
										System.out.println("option3");
									}
									else if(choice3 == 4)
									{
										contRun = false;
									}
								}
							}
						}

						else if(choice2 == 3)
						{
							System.out.print("Enter Name of New Playlist: ");
							String playlistName = input.next();
							input.nextLine();
							user.addPlaylist(playlistName);
						}

						else if(choice2 == 4)
						{
							System.out.println("option4");
						}

						else if(choice2 == 5)
						{
							System.out.println("option5");
						}

						else if(choice2 == 6)
						{
							System.out.println("option6");
						}

						else if(choice2 == 7)
						{
							System.out.println("option7");
						}
						else if(choice2 == 8)
						{
							System.out.println("option8");
						}
					}
				}
				else
				{
					// normal user menu
				}
			}

			else if(choice == 2)
			{
				// collect info, check if clean, then create new Account object and add info to database
				System.out.print("Enter Username: ");
				String name = input.next();
				input.nextLine();
				System.out.print("Enter Password: ");
				String pass = input.next();
				input.nextLine();
				System.out.println("\n\n\n");
				Account user = new Account(name, pass);

				// this will be normal user menu
				while(true)
				{
					System.out.println(
						"---Welcome " + user.getName() + "---\n" +
						"1: List Playlists\n" +
						"2: Select Playlist\n" +
						"3: Create Playlist\n" +
						"4: Search For Songs or Artists\n" +
						"These are all the options for normal user, this chain is not implemented yet"
					);
					int choice2 = input.nextInt();
					input.nextLine();
				}
			}
		}
	}
}
