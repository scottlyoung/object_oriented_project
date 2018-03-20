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
	}
}
