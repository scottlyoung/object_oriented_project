package Server;

import java.util.*;

public class TestDriver
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		// at beginning of main execution, load all song and artist objects from database into server memory
		DBManager db = new DBManager();

		// initialize player here
		Player player = Player.getPlayer();

		// create a hard coded admin account (temporary untill proper admin account creation is implemented)
		Account accTemp = new Account("admin", "pass");
		accTemp.setIsAdmin(true);
		db.addAccount(accTemp);

		/*
		List of function in Server.DBManager
		List<String> listDB()
		Server.Item searchExact(String)
		Server.Artist searchExactArtist(String)
		Server.Song searchExactSong(String)
		List<Server.Item> search(String)
		List<String> searchStr(String)
		void addItem(Server.Item)
		void addSong(Server.Song)
		void removeSong(String)
		*/

		while(true)
		{
			System.out.println(
				"---Welcome---\n" +
				"1: Log In\n" +
				"2: Create New Server.Account\n" +
				"3. Quit"
			);
			int choice = input.nextInt();
			input.nextLine();

			if(choice == 1)
			{
				// check with database, if account credentials pass then load Server.Account object and all Server.Playlist/Server.Song objects by pulling info from database
				// dont pull playlist and song data directly from database, just use the database to reference objects already in memory
				// this is default account until I get db stuff working

				System.out.print("Enter Username: ");
				String name = input.nextLine();
				System.out.print("Enter Password: ");
				String pass = input.nextLine();

				Account userTemp = db.getAccount(name);
				Account user;
				if (userTemp != null && userTemp.isPassValid(pass))
				{
					user = userTemp;
				}
				else
				{
					System.out.print("Invalid Login\n");
					user = null;
				}

				//Server.Account user = new Server.Account("Admin", "pass123");
				//user.setIsAdmin(true);
				//System.out.println("\nPress ctrl + c to exit");

				if(user != null)
				{
					while(true)
					{
						System.out.println("\n\n\n");
						System.out.println(
							"---Welcome " + user.getName() + "---\n" +
							"1: List Playlists\n" +
							"2: Select Server.Playlist\n" +
							"3: Create Server.Playlist\n" +
							"4: Remove Server.Playlist\n" +
							"5: Search For Songs or Artists\n" +
							"6: Log Out\n" +
							// this line separates normal from admin functions
							"------------------------------\n" +
							"7: Add Server.Song to DB\n" +
							"8: Remove Server.Song from DB\n" +
							"9: Set Server.Account to Admin"
						);
						int choice2 = input.nextInt();
						input.nextLine();

						if(choice2 == 1)
						{
							System.out.println("\nPlaylists: " + user.listPlaylists());
						}

						else if(choice2 == 2)
						{
							System.out.print("Enter Name of Server.Playlist: ");
							String playlistName = input.nextLine();
							Playlist currPlaylist = user.getPlaylist(playlistName);
							if(currPlaylist == null)
							{
								System.out.println("\nServer.Playlist Not Found");
							}
							else
							{
								boolean contRun = true;
								while(contRun)
								{
									System.out.println("\n\n\n");
									System.out.println(
										"---" + currPlaylist.getName() + " Server.Playlist Menu---\n" +
										"1: List Songs\n" +
										"2: Play Server.Playlist\n" +
										"3: Play Server.Song (from playlist)\n" +
										"4: Remove Server.Song\n" +
										"5: Rename Server.Playlist\n" +
										"6: Exit"
									);
									int choice3 = input.nextInt();
									input.nextLine();

									if(choice3 == 1)
									{
										System.out.println("\nSongs: " + currPlaylist.listSongs());
									}
									else if(choice3 == 2)
									{
										//System.out.println("\nInvoke player here");
										player.playPlaylist(currPlaylist);
									}
									else if(choice3 == 3)
									{
										//System.out.println("\nInvoke player here");
										System.out.println("Enter song index in playlist: ");
										int indexChoice = input.nextInt();
										input.nextLine();
										player.playPlaylist(currPlaylist, indexChoice);
									}
									else if(choice3 == 4)
									{
										System.out.print("Enter Name of Server.Song to Remove: ");
										String songName = input.nextLine();
										currPlaylist.removeSong(songName);
										// update database info
										System.out.println("\nRemoved Server.Song (Void function so cant check for success)");
									}
									else if(choice3 == 5)
									{
										System.out.print("Enter New Server.Playlist Name: ");
										String newName = input.nextLine();
										currPlaylist.setName(newName);
										// update database info
										System.out.println("\nChanged Server.Playlist Name");
									}
									else if(choice3 == 6)
									{
										System.out.println("\nExiting Server.Playlist Menu");
										contRun = false;
									}
								}
							}
						}

						else if(choice2 == 3)
						{
							System.out.print("Enter Name of New Server.Playlist: ");
							String playlistName = input.nextLine();
							user.addPlaylist(playlistName);
							// update database info
							System.out.println("\nAdded New Server.Playlist");
						}

						else if(choice2 == 4)
						{
							System.out.print("Enter Name of Server.Playlist to Remove: ");
							String playlistName = input.nextLine();
							user.removePlaylist(playlistName);
							// update database info
							System.out.println("\nRemoved Server.Playlist (Void function so cant check for success)");
						}

						else if(choice2 == 5)
						{
							System.out.print("Enter Search ('' returns whole DB): ");
							String inputStr = input.nextLine();
							List<String> resultsStr = db.listDB();
							List<Item> results = db.search(inputStr);

							boolean contRun = true;
							while(contRun)
							{
								System.out.println("\n---Search Results---\n" + resultsStr);
								System.out.println("\n\n\n");
								System.out.println(
									"---Search Menu---\n" +
									"1: Select Server.Song or Server.Artist\n" +
									"2: Exit"
								);
								int choice3 = input.nextInt();
								input.nextLine();

								if(choice3 == 1)
								{
									System.out.print("Enter Name of Selection: ");
									String inputStr2 = input.nextLine();
									Item searchRes = null;
									for(Item i : results)
									{
										if(inputStr2.equals(i.getName()))
										{
											searchRes = i;
											break;
										}
									}
									if(searchRes == null)
									{
										System.out.println("\nEntry Not In Search Results");
									}
									else
									{
										System.out.println("\n" + searchRes.getName() + " Exists In Search Results");

										if(searchRes instanceof Song)
										{
											boolean contRun2 = true;
											Song tmpSong = (Song)searchRes;
											while(contRun2)
											{
												System.out.println("\n\n\n");
												System.out.println(
													"---Server.Song Menu---\n" +
													"1: Play Server.Song\n" +
													"2: Pause Server.Song\n" +
													"3: Display Server.Song Details\n" +
													"4: Add Server.Song to Server.Playlist\n" +
													"5: Exit"
												);
												int choice4 = input.nextInt();
												input.nextLine();

												if(choice4 == 1)
												{
													player.playSong(tmpSong);
												}
												else if (choice4 == 2)
												{
													player.pauseSong();
												}
												else if(choice4 == 3)
												{
													System.out.println(
													"\nTitle: " + tmpSong.getName() +
													"\nServer.Artist: " + tmpSong.getArtist() +
													"\nGenre: " + tmpSong.getGenre() +
													"\nAlbum: " + tmpSong.getAlbum() +
													"\nDuration: " + tmpSong.getDuration() + " seconds"
													);
												}
												else if(choice4 == 4)
												{
													int index = 0;
													for(String playlistIndex : user.listPlaylists())
													{
														System.out.println(index++ + ": " + playlistIndex);
													}

													System.out.print("\nWhich Server.Playlist Would You Like To Add To: ");
													int choice5 = input.nextInt();
													input.nextLine();

													Playlist playlistChoice = null;
													index = 0;
													for(String playlistIndex : user.listPlaylists())
													{
														if(index++ == choice5)
														{
															playlistChoice = user.getPlaylist(playlistIndex);
															break;
														}
													}

													playlistChoice.addSong(tmpSong);
													System.out.println("\nAdded Server.Song to Server.Playlist");
												}
												else if(choice4 == 5)
												{
													System.out.println("\nGoing Back To Search Menu");
													contRun2 = false;
												}
											}
										}
										else if(searchRes instanceof Artist)
										{
											boolean contRun2 = true;
											Artist tmpArtist = (Artist)searchRes;
											while(contRun2)
											{
												System.out.println("\n\n\n");
												System.out.println(
													"---Server.Artist Menu---\n" +
													"1: Display Songs\n" +
													"2: Play Server.Song\n" +
													"3: Play All Songs\n" +
													"4: Exit"
												);
												int choice4 = input.nextInt();
												input.nextLine();

												if(choice4 == 1)
												{
													System.out.println("\nSongs: " + tmpArtist.listSongs());
												}
												else if(choice4 == 2)
												{
													System.out.println("\nInvoke Server.Player Here");

												}
												else if(choice4 == 3)
												{
													System.out.println("\nInvoke Server.Player Here");
												}
												else if(choice4 == 4)
												{
													System.out.println("\nGoing Back To Search Menu");
													contRun2 = false;
												}
											}
										}
									}
								}
								else if(choice3 == 2)
								{
									System.out.println("\nGoing Back To Main Menu");
									contRun = false;
								}
							}
						}

						else if(choice2 == 6)
						{
							break;
						}

						else if(choice2 == 7)
						{
							boolean contRun = true;
							while(contRun)
							{
								System.out.println("\n\n\n");
								System.out.println(
									"---Add Songs Menu---\n" +
									"1: Manually Add A Server.Song\n" +
									"2: Add Hardcoded Songs (For Testing)\n" +
									"3: Exit"
								);
								int choice3 = input.nextInt();
								input.nextLine();

								if(choice3 == 1)
								{
									System.out.print("Enter Title: ");
									String title = input.nextLine();
									System.out.print("Enter Server.Artist: ");
									String artist = input.nextLine();
									System.out.print("Enter Genre: ");
									String genre = input.nextLine();
									System.out.print("Enter Album: ");
									String album = input.nextLine();
									Song a = new Song(title, artist, genre, album, 250);
									db.addSong(a);
									System.out.println("\nAdded Server.Song To DB");
									contRun = false;
								}
								else if(choice3 == 2)
								{
									Song a = new Song("Pain", "Three Days Grace", "Hard Rock", "One-X", 250);
									Song b = new Song("Riot", "Three Days Grace", "Hard Rock", "One-X", 250);
									Song c = new Song("Never Too Late", "Three Days Grace", "Hard Rock", "One-X", 250);
									Song d = new Song("Roundabout", "Yes", "Classic Rock", "Fragile (Deluxe Version)", 250);
									Song e = new Song("Lights", "Journey", "80's Rock", "Infinity", 250);
									Song f = new Song("Whos Crying Now", "Journey", "80's Rock", "Escape", 250);
									Song g = new Song("Smooth", "Santana", "Classic Rock", "Supernatural", 250);
									Song h = new Song("Who Are You", "The Who", "Classic Rock", "Who Are You", 250);
									Song i = new Song("Danger Zone", "Kenny Rogers", "Classic Rock", "Danger Zone", 250, "test_song.wav");
									db.addSong(a);
									db.addSong(b);
									db.addSong(c);
									db.addSong(d);
									db.addSong(e);
									db.addSong(f);
									db.addSong(g);
									db.addSong(h);
									db.addSong(i);
									System.out.println("\nAdded song: " + i.getName());
									System.out.println("\nAdded Precoded Songs");
									contRun = false;
								}
								else if(choice3 == 3)
								{
									System.out.println("\nGoing Back To Main Menu");
									contRun = false;
								}
							}
						}

						else if(choice2 == 8)
						{
							System.out.print("Enter Name of Server.Song to Remove: ");
							String title = input.nextLine();
							db.removeSong(title);
							System.out.println("\nRemoved Server.Song From DB");
						}

						else if(choice2 == 9)
						{
							System.out.println("\nThis will directly affect the database. Server.Account objects are not stored in Server.DBManager class");
						}
					}
				}
			}

			else if(choice == 2)
			{
				// collect info, check if clean, then add info to database
				System.out.print("Enter Username: ");
				String name = input.nextLine();
				System.out.print("Enter Password: ");
				String pass = input.nextLine();
				if (db.addAccount(new Account(name, pass)))
				{
					System.out.println("\nServer.Account Has Been Created, Try Logging In\n\n");
				}
				else
				{
					System.out.println("\nServer.Account Name ALready Exists, Can't Create Server.Account\n\n");
				}
			}

			else if(choice == 3)
			{
				break;
			}
		}
	}
}
