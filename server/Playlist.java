package server;

import java.util.*;

public class Playlist
{
	private String name;
	private List<Song> songs;

	public Playlist(String _name)
	{
		this.name = _name;
		this.songs = new ArrayList<Song>();
	}

	public List<String> listSongs()
	{
		List<String> strSongs = new ArrayList<String>();
		for(Song s : songs)
		{
			strSongs.add(s.getName());
		}
		return strSongs;
	}

	public List<Song> getSongs()
	{
		return songs;
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

	public void addSong(Song song)
	{
		songs.add(song);
	}

	public void removeSong(String song)
	{
		for(int i = 0; i < songs.size(); i++)
		{
			if(songs.get(i).getName().equals(song))
			{
				songs.remove(i);
				break;
			}
		}
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
