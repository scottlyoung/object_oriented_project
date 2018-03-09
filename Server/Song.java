public class Song extends Item
{
	private String genre;
	private float duration;
	private Artist artist;
	private String album;

	public Song()
	{

	}

	public String getGenre()
	{
		return genre;
	}

	public float getDuration()
	{
		return duration;
	}

	public Artist getArtist()
	{
		return artist;
	}

	public String getAlbum()
	{
		return album;
	}
}