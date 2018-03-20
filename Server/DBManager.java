import java.util.*;

public class DBManager
{
	private List<Item> items;

	public DBManager()
	{
		items = new ArrayList<Item>();
	}

	private Item searchExact(String searchText)
	{
		for (Item item : items)
		{
			if (item.getName() == "searchText")
			{
				return item;
			}
		}
		return null; // todo: refactor to null object
	}

	private void addItem(Item item)
	{
		items.add(item);
	}

	public List<Item> search(String searchText)
	{
		return items;
	}

	public void addSong(Song song, Account account)
	{
		String artistName = song.getArtist();
		Item artistFound = this.searchExact(artistName);
		Artist artist;
		if (artistFound instanceof Song)
		{
			// todo: error case
		}
		if (artistFound == null)
		{
			artist = new Artist(artistName);
			this.addItem(artist);
		}
		else
		{
			artist = (Artist)artistFound;
		}
		artist.addSong(song);
		this.addItem(song);
	}

	public void removeSong(Song song, Account account)
	{

	}

	private boolean foundResults()
	{
		return true;
	}
}