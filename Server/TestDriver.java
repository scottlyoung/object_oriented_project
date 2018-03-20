import java.util.*;

public class TestDriver
{
	public static void main(String[] args)
	{
		DBManager db = new DBManager();
		Account acc = new Account("john doe", "password123");
		Song song1 = new Song("s1", "a1", "g", "alb", 0);
		Song song2 = new Song("s2", "a1", "g", "alb", 0);
		Song song3 = new Song("s3", "a2", "g", "alb", 0);
		db.addSong(song1, acc);
		db.addSong(song2, acc);
		db.addSong(song3, acc);

		System.out.println(db.search("s"));
		System.out.println(db.search("a"));

		// start with account
		Account test = new Account("janedoe12", "password123");

		// test methods
		System.out.println(test.isPassValid("password123"));
		test.addPlaylist("Blues");
		test.addPlaylist("Rock");
		System.out.println(test.getPlaylist("Rock"));
		System.out.println(test.getAllPlaylists());
		System.out.println(test.listPlaylists());
		test.setIsAdmin(true);
		System.out.println(test.getIsAdmin());

		// now implement playlists
		Playlist test2 = new Playlist("Indie asdf");
		test2.setName("Indie Beats");
		System.out.println(test2.getName());
		Song tmp = new Song("Kangaroo Court", "Capital Cities", "Indie", "In a Tidal Wave of Mystery", 223);
		Song tmp2 = new Song("Renegades", "X Ambassadors", "Indie", "VHS", 195);
		test2.addSong(tmp);
		test2.addSong(tmp2);
		System.out.println(test2.getSongs());
	}
}
