import java.util.*;

public class Account
{
	private String name;
	private String password;
	private boolean isAdmin;
	private List<Playlist> playlists;

	public Account(String _name, String _password)
	{
		this.name = _name;
		this.password = _password;
		this.isAdmin = false;
		this.playlists = new ArrayList<Playlist>();
	}

	public boolean isPassValid(String pass)
	{
		// consider storing a hash of the password instead of plaintext, then compare hashes
		if(pass == password)
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
		return null;
	}

	public boolean getIsAdmin()
	{
		return isAdmin;
	}
}
