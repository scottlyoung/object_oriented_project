import java.util.*;

public class DBManager
{
	private List<Item> items;
	private List<Account> accounts;

	public DBManager()
	{
		items = new ArrayList<Item>();
		accounts = new ArrayList<Account>();
	}

	public List<String> listDB()
	{
		List<String> strDB = new ArrayList<String>();
		for(Item item : items)
		{
			strDB.add(item.getName());
		}
		return strDB;
	}

	private Item searchExact(String search_text)
	{
		for (Item item : items)
		{
			if (item.getName().equals(search_text))
			{
				return item;
			}
		}
		return null; // todo: refactor to null object
	}

	private Artist searchExactArtist(String search_text)
	{
		Item search = this.searchExact(search_text);
		if (search instanceof Artist)
		{
			return (Artist)search;
		}
		return null;
	}

	private Song searchExactSong(String search_text)
	{
		Item search = this.searchExact(search_text);
		if (search instanceof Song)
		{
			return (Song)search;
		}
		return null;
	}

	public List<Item> search(String search_text)
	{
		List<Item> results = new ArrayList<Item>();
		for (Item item : items)
		{
			if (item.getName().toLowerCase().contains(search_text.toLowerCase()))
			{
				results.add(item);
			}
		}
		return results;
	}

	public List<String> searchStr(String search_text)
	{
		List<String> results = new ArrayList<String>();
		for (Item item : items)
		{
			if (item.getName().toLowerCase().contains(search_text.toLowerCase()))
			{
				results.add(item.getName());
			}
		}
		return results;
	}

	private void addItem(Item item)
	{
		items.add(item);
	}

	public void addSong(Song song)
	{
		// Should check to see if exact song exists already, don't want duplicates
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

	// if we plan on having the ability for songs from different artist to have the same name
	// we will need to change this so that deleting requires more specifics of the song
	public void removeSong(String song_name)
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
		if(artist.songsIsEmpty())
		{
			this.items.remove(artist);
		}
	}

	public Boolean addAccount(Account account_)
	{
		if (this.getAccount(account_.getName()) == null)
		{
			this.accounts.add(account_);
			return true;
		}
		return false;
	}

	public Account getAccount(String name)
	{
		for (Account a : this.accounts)
		{
			if (a.getName().equals(name))
			{
				return a;
			}
		}
		return null;
	}
}
