import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Player
{
	private Song currentSong;
	private Song nextSong;
	private Playlist currentPlaylist;
	private int positionInPlaylist = 0;

	private String playStatus = "paused";
  private Clip clip;
  private AudioInputStream audioInputStream;
  private File songFile;

	public Player()
	{

	}

	public void playPlaylist(Playlist playlist)
	{
		currentPlaylist = playlist;
		positionInPlaylist = 0;
		playPlaylistSong();
	}

	void playPlaylistSong()
	{
		//--commenting line below out because doesn't compile as is
		//playSong(currentPlaylist.getSongs()[positionInPlaylist]);
	}

	public void playNewSong(Song song)
		throws UnsupportedAudioFileException,
        IOException, LineUnavailableException
	{
		if (playStatus == "playing")
		{
			pauseSong();
		}

		//--Intended implementation below
		//audioInputStream = AudioSystem.getAudioInputStream(song.getFile());
		//--Temporary implementation
		audioInputStream = AudioSystem.getAudioInputStream(new File("test_audio.wav").getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);

		resumeSong();

		//We need a way to tell when a song ends, maybe put the play method in the Song class and use observer
		//pattern to tell when it finishes? Then this method would be used to subscribe to the song object
	}

	public void resumeSong()
	{
		if (playStatus == "playing")
		{
			System.out.println("Audio already playing...");
			return;
		}

		//Currently just restarts song, will have to implement timeframe tracking later
		clip.start();
		playStatus = "playing";
	}

	public void pauseSong() {
		if (playStatus == "paused")
		{
			System.out.println("Audio already paused...");
			return;
		}
		clip.stop();
		playStatus = "paused";
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
