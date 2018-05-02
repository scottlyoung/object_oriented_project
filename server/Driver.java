package server;

import java.util.*;

public class Driver
{
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		// at beginning of main execution, load all song and artist objects from database into server memory
		DBManager db = DBManager.getInstance();

		// initialize a few songs here, DELETE WHEN DATABASE WORKS
		Song one = new Song("Fade", "Alan Walker", "Electronic", "asdf", "Alan_Walker_-_Fade.wav");
		Song two = new Song("Broke For Free", "Layers", "Instrumental", "As Colorful As Ever", "Broke_For_Free_-_As_Colorful_As_Ever_-_Layers.wav");
		Song three = new Song("Jahzzar", "Travellers Guide", "asdf", "Siesta", "Jahzzar_-_Siesta_-_Traveller's_Guide.wav");
		Song four = new Song("Slow Burn", "Kevin MacLeod", "Blues", "asdf", "Kevin_MacLeod_-_Slow_Burn_-_Blues_Sampler.wav");
		Song five = new Song("Become", "Stephan Siebert", "asdf", "Personal", "Stephan_Siebert_-_become_-_Personal_Soundwaves.wav");
		Song six = new Song("When", "Stephan Siebert", "asdf", "Personal", "Stephan_Siebert_-_when_-_Personal_Soundwaves.wav");
		db.addSong(one);db.addSong(two);db.addSong(three);db.addSong(four);db.addSong(five);db.addSong(six);

		// initialize player here
		// should the player be client or server side, I'm thinking client but don't know enough to know
		Player player = Player.getPlayer();

		// create a hard coded admin account (temporary untill proper admin account creation is implemented)
		Account accTemp = new Account("admin", "pass");
		accTemp.setIsAdmin(true);
		db.addAccount(accTemp);

		while(true)
		{
			System.out.println(
				"---Welcome---\n" +
				"1: Log In\n" +
				"2: Create New server.Account\n" +
				"3. Quit"
			);
			int choice = input.nextInt();
			input.nextLine();

			if(choice == 1)
			{
				System.out.print("Enter Username: ");
				String name = input.nextLine();
				System.out.print("Enter Password: ");
				String pass = input.nextLine();

				// checks if account exists and password matches
				Account user = db.getAccount(name);
				if (user == null || !user.isPassValid(pass))
				{
					System.out.print("\nInvalid Login\n\n\n");
					user = null;
				}

				if(user != null && user.getIsAdmin())
				{
					// admin menu
					while(true)
					{
						System.out.println("\n\n\n");
						System.out.println(
							"---Welcome " + user.getName() + "---\n" +
							"1: List Playlists\n" +
							"2: Select server.Playlist\n" +
							"3: Create server.Playlist\n" +
							"4: Remove server.Playlist\n" +
							"5: Search For Songs or Artists\n" +
							"6: Log Out\n" +
							"------------------------------\n" +
							"7: Add server.Song to DB\n" +
							"8: Remove server.Song from DB\n" +
							"9: Set server.Account to Admin"
						);
						int choice2 = input.nextInt();
						input.nextLine();

						if(choice2 == 1)
						{
							System.out.println("\nPlaylists: " + user.listPlaylists());
						}

						else if(choice2 == 2)
						{
							System.out.print("Enter Name of server.Playlist: ");
							String playlistName = input.nextLine();
							Playlist currPlaylist = user.getPlaylist(playlistName);
							if(currPlaylist == null)
							{
								System.out.println("\nserver.Playlist Not Found");
							}
							else
							{
								while(true)
								{
									System.out.println("\n\n\n");
									System.out.println(
										"---" + currPlaylist.getName() + " server.Playlist Menu---\n" +
										"1: List Songs\n" +
										"2: Play server.Playlist\n" +
										"3: Play server.Song (from playlist)\n" +
										"4: Stop playing\n" +
										"5: Remove server.Song\n" +
										"6: Rename server.Playlist\n" +
										"7: Exit"
									);
									int choice3 = input.nextInt();
									input.nextLine();

									if(choice3 == 1)
									{
										System.out.println("\nSongs: " + currPlaylist.listSongs());
									}
									else if(choice3 == 2)
									{
										player.playPlaylist(currPlaylist);
									}
									else if(choice3 == 3)
									{
										System.out.println("Enter song index in playlist: ");
										int indexChoice = input.nextInt();
										input.nextLine();
										player.playPlaylist(currPlaylist, indexChoice);
									}
									else if(choice3 == 4)
									{
										player.pauseSong();
									}
									else if(choice3 == 5)
									{
										System.out.print("Enter Name of server.Song to Remove: ");
										String songName = input.nextLine();
										currPlaylist.removeSong(songName);
										System.out.println("\nRemoved server.Song");
									}
									else if(choice3 == 6)
									{
										System.out.print("Enter New server.Playlist Name: ");
										String newName = input.nextLine();
										currPlaylist.setName(newName);
										System.out.println("\nChanged server.Playlist Name");
									}
									else if(choice3 == 7)
									{
										System.out.println("\nExiting server.Playlist Menu");
										break;
									}
								}
							}
						}

						else if(choice2 == 3)
						{
							System.out.print("\nEnter Name of New server.Playlist: ");
							String playlistName = input.nextLine();
							user.addPlaylist(playlistName);
							System.out.println("\nAdded New server.Playlist");
						}

						else if(choice2 == 4)
						{
							System.out.print("Enter Name of server.Playlist to Remove: ");
							String playlistName = input.nextLine();
							user.removePlaylist(playlistName);
							System.out.println("\nRemoved server.Playlist");
						}

						else if(choice2 == 5)
						{
							System.out.print("Enter Search ('' returns whole DB): ");
							String inputStr = input.nextLine();
							List<String> resultsStr = db.searchStr(inputStr);
							List<Item> results = db.search(inputStr);

							while(true)
							{
								System.out.println("\n---Search Results---\n" + resultsStr);
								System.out.println("\n\n\n");
								System.out.println(
									"---Search Menu---\n" +
									"1: Select server.Song or server.Artist\n" +
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
													"---server.Song Menu---\n" +
													"1: Play server.Song\n" +
													"2: Pause server.Song\n" +
													"3: Display server.Song Details\n" +
													"4: Add server.Song to server.Playlist\n" +
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
													System.out.println(tmpSong);
												}
												else if(choice4 == 4)
												{
													int index = 0;
													for(String playlistIndex : user.listPlaylists())
													{
														System.out.println(index++ + ": " + playlistIndex);
													}

													System.out.print("\nWhich server.Playlist Would You Like To Add To: ");
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
													System.out.println("\nAdded server.Song to server.Playlist");
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
													"---server.Artist Menu---\n" +
													"1: Display Songs\n" +
													"2: Play server.Song\n" +
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
													System.out.println("\nInvoke server.Player Here");

												}
												else if(choice4 == 3)
												{
													System.out.println("\nInvoke server.Player Here");
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
									break;
								}
							}
						}

						else if(choice2 == 6)
						{
							break;
						}

						else if(choice2 == 7)
						{
							System.out.print("Enter Title: ");
							String title = input.nextLine();
							System.out.print("Enter server.Artist: ");
							String artist = input.nextLine();
							System.out.print("Enter Genre: ");
							String genre = input.nextLine();
							System.out.print("Enter Album: ");
							String album = input.nextLine();
							System.out.print("Enter Filename: ");
							String filename = input.nextLine();
							Song tmp = new Song(title, artist, genre, album, filename);
							db.addSong(tmp);
							System.out.println("\nAdded server.Song To DB");
						}

						else if(choice2 == 8)
						{
							System.out.print("Enter Name of server.Song to Remove: ");
							String title = input.nextLine();
							db.removeSong(title);
							System.out.println("\nRemoved server.Song From DB");
						}

						else if(choice2 == 9)
						{
							System.out.print("Enter name of Account to make admin: ");
							String accName = input.nextLine();
							Account acc = db.getAccount(accName);
							if(acc != null)
							{
								acc.setIsAdmin(true);
								System.out.println("\nAccount made into admin\n");
							}
						}
					}
				}
				else if(user != null)
				{
					System.out.println("\nNot admin\n\n\n");
					// make normal user menu here
				}
			}

			else if(choice == 2)
			{
				System.out.print("Enter Username: ");
				String name = input.nextLine();
				System.out.print("Enter Password: ");
				String pass = input.nextLine();
				if (db.addAccount(new Account(name, pass)))
				{
					System.out.println("\nserver.Account Has Been Created, Try Logging In\n\n");
				}
				else
				{
					System.out.println("\nserver.Account Name Already Exists, Can't Create server.Account\n\n");
				}
			}

			else if(choice == 3)
			{
				break;
			}
		}
	}
}
