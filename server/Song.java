package server;

public class Song extends Item
{
	private String artist;
	private String genre;
	private String album;
	private String fileName;

	public Song(String _name, String _artist, String _genre, String _album, String _fileName)
	{
		super(_name);
		this.artist = _artist;
		this.genre = _genre;
		this.album = _album;
		this.fileName = _fileName;
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

	public String getFileName()
	{
		return this.fileName;
	}

	@Override
	public String toString() {
		return super.getName()+"\n"+getArtist()+"\n"+getAlbum()+", "+getGenre()+", "+getFileName();
	}
}
