import java.util.*;

public class Account
{
	private String name;
	private String password;
	private boolean isAdmin;
	private List<Playlist> playlists;

	Account()
	{

	}

	public boolean isPassValid(String pass)
	{
		return true;
	}

	public List<Playlist> getAllPlaylists()
	{
		return playlists;
	}

	public List<String> listPlaylists()
	{
		return null;
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