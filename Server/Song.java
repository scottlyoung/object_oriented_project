import java.util.*;

public class Song extends Item
{
	private String genre;
	private float duration;
	private String artist;
	private String album;

	public Song(String _name, String _artist, String _genre, String _album, float _duration)
	{
		super(_name);
		this.artist = _artist;
		this.genre = _genre;
		this.album = _album;
		this.duration = _duration;
	}

	public String getGenre()
	{
		return this.genre;
	}

	public float getDuration()
	{
		return this.duration;
	}

	public String getArtist()
	{
		return this.artist;
	}

	public String getAlbum()
	{
		return this.album;
	}
}
