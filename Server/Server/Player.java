package Server;

import java.util.*;
import java.io.File;

import javax.sound.sampled.*;

public class Player
{
	private static Player instance = null;

	private String currentSong;
	private Song nextSong;
	private Playlist currentPlaylist;
	private int positionInPlaylist = 0;

	private String playStatus = "paused";
	private Long currentFrame;
	private Clip clip;
	private AudioInputStream audioInputStream;
	private File songFile;

	private Player()
	{
		instance = this;
	}

	public static Player getPlayer() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	public void playPlaylist(Playlist playlist)
		
	{
		currentPlaylist = playlist;
		positionInPlaylist = 0;
		playPlaylistSong();
	}

	public void playPlaylist(Playlist playlist, int index)
		
	{
		currentPlaylist = playlist;
		playPlaylistSong(index);
	}

	void playPlaylistSong()
		
	{
		//--commenting line below out because doesn't compile as is
		List<Song> playlistSongs = currentPlaylist.getSongs();

		if (positionInPlaylist < 0 || positionInPlaylist >= playlistSongs.size()) {
			System.out.println("Invalid position in playlist.");
			return;
		}

		playSong(playlistSongs.get(positionInPlaylist));
	}

	void playPlaylistSong(int index)
		
	{
		List<Song> playlistSongs = currentPlaylist.getSongs();

		if (index < 0 || index >= playlistSongs.size()) {
			System.out.println("Invalid index in playlist. Defaulting to 0");
			positionInPlaylist = 0;
		}
		else 
		{
			positionInPlaylist = index;
		}
		playPlaylistSong();
	}

	public void playSong(Song song)
		
	{
		if (playStatus == "playing")
		{
			pauseSong();
			clip.close();
		}

		if (song.getName() == currentSong) {
			resumeSong();
			return;
		}

		try {
			System.out.println("Loading " + "audio_files/" + song.getFileName());
    		audioInputStream = AudioSystem.getAudioInputStream(new File("audio_files/" + song.getFileName()).getAbsoluteFile());
    		clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			currentSong = song.getName();
			resumeSong();
		} catch (Exception e) {
    		System.out.println("Audio File not valid");
    		e.printStackTrace();
    		return;
		}
	}

	void resumeSong()
	{
		if (playStatus == "playing")
		{
			System.out.println("Audio already playing...");
			return;
		}

		//Currently just restarts song, will have to implement timeframe tracking later
		//jumpToFrame(currentFrame);
		clip.start();
		playStatus = "playing";
	}

	public void pauseSong() {
		if (playStatus == "paused")
		{
			System.out.println("Audio already paused...");
			return;
		}
		currentFrame = clip.getMicrosecondPosition();
		clip.stop();
		playStatus = "paused";
	}

	public void jumpToFrame(Long frame) 
	{
		if (frame <= 0 || frame >= clip.getMicrosecondLength()) 
			return;
		
		clip.setMicrosecondPosition(frame);
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
