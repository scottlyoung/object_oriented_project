import java.util.*;

public class Playlist
{
	private List<Song> songs;
	private String name;

	public Playlist(String _name)
	{
		this.name = _name;
		this.songs = new ArrayList<Song>();
	}

	public List<Song> getSongs()
	{
		return songs;
	}

	public void addSong(Song song)
	{
		// this assumes the passed song object is good, probably want to check input before blindly adding to the List
		songs.add(song);
	}

	public void setName(String _name)
	{
		this.name = _name;
	}

	public String getName()
	{
		return name;
	}
}
