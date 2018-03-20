import java.util.*;

public class Artist extends Item
{
	private List<Song> songs;

	public Artist(String _name)
	{
		super(_name);
		songs = new ArrayList<Song>();
	}

	public List<Song> getSongs()
	{
		return songs;
	}

	public void addSong(Song song)
	{
		songs.add(song);
	}
}