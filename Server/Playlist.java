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
		songs.add(song);
	}

	public void removeSong(String song)
	{
		// implement
	}

	public Song getSong(String song)
	{
		for(int i = 0; i < songs.size(); i++)
		{
			if(songs.get(i).getName().equals(song))
			{
				return songs.get(i);
			}
		}
		return null;
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
