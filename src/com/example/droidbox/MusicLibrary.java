package com.example.droidbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MusicLibrary extends SherlockFragmentActivity
{


	ViewPager viewPager;
	TabsAdapter tabsAdapter;
	Song current;
	SongList songs = new SongList();
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		this.setTheme(R.style.Theme_Sherlock_Light);
		//addSongs();//Once JSON is implemented just call a ReadFile here to read in the library. Current Situation is for Testing Only
		super.onCreate(savedInstanceState);
		Context context = this;


		viewPager = new ViewPager(this);
		viewPager.setId(R.id.pager);

		setContentView(viewPager);
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabsAdapter = new TabsAdapter(this, viewPager);
		
		tabsAdapter.addTab(
				bar.newTab().setText("Title"),
				FragmentOne.class, null);
		tabsAdapter.addTab(
				bar.newTab()
						.setText("Artist"),
				FragmentTwo.class, null);
		tabsAdapter.addTab(
				bar.newTab()
						.setText("Album"),
				FragmentThree.class, null);
		if(savedInstanceState != null){
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab",0));
		}
		//Toast.makeText(context, current.getArtist(), 15).show();

	}
	
	public SongList getSongsTitleSort(){
	
		return songs.sortByTitle();
	}
	public SongList getSongsArtistSort(){
	
		return songs.sortByArtist();
	}
	public SongList getSongsAlbumSort(){
	
		return songs.sortByAlbum();
	}
	
	
	public void setCurrentSong(Song song) {
		current = song;
	}
	public Song getCurrentSong() {
		return current;
	}
	
	
	public void sendSongToMain() {
		  Intent intent = new Intent(this, Main.class);
		  intent.putExtra(Main.ID,current.getID());
		  intent.putExtra(Main.ALBUM_NAME, current.getAlbum());
		  intent.putExtra(Main.ARTIST_NAME, current.getArtist());
		  intent.putExtra(Main.SONG_NAME, current.getTitle());
		  startActivity(intent);
		  
	}
	

	
	public void addSongs(){
		songs.add(new Song("song 1","g","h", 15));
        songs.add(new Song("song 2","e","g", 16));
       	songs.add(new Song("song 3","d","f", 17));
       	songs.add(new Song("song 4","a","e",19));
       	songs.add(new Song("song 5","c","d", 18));
       	songs.add(new Song("song 6","b","c", 13));
       	songs.add(new Song("song 7","f","b", 6));
       	songs.add(new Song("song 8","h","a", 125));
	}
	
}