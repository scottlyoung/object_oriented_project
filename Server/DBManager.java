import java.util.*;

public class DBManager
{
	private List<Item> items;

	public DBManager()
	{
		items = new ArrayList<Item>();
	}

	private Item searchExact(String search_text)
	{
		for (Item item : items)
		{
			if (item.getName() == search_text)
			{
				return item;
			}
		}
		return null; // todo: refactor to null object
	}

	private Artist searchExactArtist(String search_text)
	{
		Item search = this.searchExact(search_text);
		if (search instanceof Song)
		{
			// error case
		}
		return (Artist)search;
	}

	private Song searchExactSong(String search_text)
	{
		Item search = this.searchExact(search_text);
		if (search instanceof Artist)
		{
			// error case
		}
		return (Song)search;
	}

	private void addItem(Item item)
	{
		items.add(item);
	}

	public List<Item> search(String search_text)
	{
		return items;
	}

	public void addSong(Song song, Account account)
	{
		String artistName = song.getArtist();
		Artist artist = this.searchExactArtist(artistName);
		if (artist == null)
		{
			artist = new Artist(artistName);
			this.addItem(artist);
		}
		artist.addSong(song);
		this.addItem(song);
	}

	public void removeSong(String song_name, Account account)
	{
		Song song = this.searchExactSong(song_name);
		if (song == null)
		{
			return;
		}
		String artist_name = song.getArtist();
		Artist artist = this.searchExactArtist(artist_name);
		artist.removeSong(song);
		this.items.remove(song);
	}
}