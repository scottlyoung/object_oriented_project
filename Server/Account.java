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

	public String getName()
	{
		return name;
	}
}


/*
this is code I wrote for if we have extra time, it is a way to hash the passwords

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public static String sha256Hash(String arg)
{
	try
	{
		MessageDigest hashAlgo = MessageDigest.getInstance("SHA-256");
		hashAlgo.update(arg.getBytes());
		byte[] hash = hashAlgo.digest();
		StringBuffer hexValue = new StringBuffer();
		for (byte b : hash) hexValue.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		return hexValue.toString();
	}
	catch(NoSuchAlgorithmException e)
	{
		return null;
	}
}
*/
