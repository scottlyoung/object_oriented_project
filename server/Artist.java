package server;

import java.util.*;

public class Artist extends Item
{
	private List<Song> songs;

	public Artist(String _name)
	{
		super(_name);
		songs = new ArrayList<Song>();
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

	public void addSong(Song song)
	{
		if(song.getArtist().equals(this.getName()))
		{
			songs.add(song);
		}
	}

	public void removeSong(Song song)
	{
		songs.remove(song);
	}

	public boolean songsIsEmpty()
	{
		return this.songs.isEmpty();
	}
}
