import java.util.*;

public class Player
{
	private Song currentSong;
	private Song nextSong;
	private Playlist currentPlaylist;
	private int positionInPlaylist = 0;

	public Player()
	{
		//Possibly use singleton pattern since there should only be one player in the program?
	}

	public void playPlaylist(Playlist playlist)
	{
		currentPlaylist = playlist;
		playPlaylistSong();
	}

	void playPlaylistSong()
	{
		playSong(currentPlaylist.getSongs()[positionInPlaylist]);
	}

	public void playSong(Song song)
	{
		//song.play();
		//
		//We need a way to tell when a song ends, maybe put the play method in the Song class and use observer
		//pattern to tell when it finishes? Then this method would be used to subscribe to the song object
	}

	public void skipForward()
	{
		if (positionInPlaylist >= currentPlaylist.getSongs().size() - 1) 
		{
			System.out.println("At end of playlist, can't skip forward.");
			return;
		}

		positionInPlaylist ++;
		playPlaylistSong();
	}

	public void skipBackward()
	{
		if (positionInPlaylist <= 0)
		{
			System.out.println("At beginning of playlist, can't skip backward.");
			return;
		}

		positionInPlaylist --;
		playPlaylistSong();
	}
}