import java.util.*;

public class Song extends Item
{
	private String artist;
	private String genre;
	private String album;
	private float duration;

	public Song(String _name, String _artist, String _genre, String _album, float _duration)
	{
		super(_name);
		this.artist = _artist;
		this.genre = _genre;
		this.album = _album;
		this.duration = _duration;
	}

	public String getArtist()
	{
		return this.artist;
	}

	public String getGenre()
	{
		return this.genre;
	}

	public String getAlbum()
	{
		return this.album;
	}

	public float getDuration()
	{
		return this.duration;
	}
}
