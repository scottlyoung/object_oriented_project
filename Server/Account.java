import java.util.*;

public class Account
{
	private String name;
	private String password;
	private boolean isAdmin;
	private List<Playlist> playlists;

	public Account(String _name, String _password)
	{
		// only create the account if the username is not already in the database
		this.name = _name;
		this.password = _password;
		this.isAdmin = false;
		// playlist initializes to null without line below, now it initializes to an empty list
		this.playlists = new ArrayList<Playlist>();
	}

	public boolean isPassValid(String pass)
	{
		// consider storing a hash of the password instead of plaintext, then compare hashes
		if(pass.equals(password))
		{
			return true;
		}
		return false;
	}

	public List<Playlist> getAllPlaylists()
	{
		return playlists;
	}

	public List<String> listPlaylists()
	{
		List<String> strPlaylists = new ArrayList<String>();
		for(Playlist p : playlists)
		{
			strPlaylists.add(p.getName());
		}
		return strPlaylists;
	}

	public Playlist getPlaylist(String playlist)
	{
		for(int i = 0; i < playlists.size(); i++)
		{
			if(playlists.get(i).getName().equals(playlist))
			{
				return playlists.get(i);
			}
		}
		return null;
	}

	public void addPlaylist(String _name)
	{
		Playlist tmp = new Playlist(_name);
		playlists.add(tmp);
	}

	public boolean getIsAdmin()
	{
		return isAdmin;
	}

	public void setIsAdmin(boolean _isAdmin)
	{
		this.isAdmin = _isAdmin;
	}
}