package com.example.droidbox;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



public class FragmentTwo extends ListFragment
{
	
	Song temp;
	
	public static FragmentTwo newInstance(String content){
		FragmentTwo fragment = new FragmentTwo();
		return fragment;
	}
	
	public  SongList songs = new SongList();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       	View test = super.onCreateView(inflater, container, savedInstanceState);
       	
		SongList song2 = new SongList();
		for(int i = 0;i<songs.size();i++ ){
			song2.add(songs.get(i));
		}
		song2.sortByArtist();
		
		setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,song2));

        return test;
    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		Context context = (MusicLibrary)getActivity();
		Song song = (Song) l.getItemAtPosition(position);
		temp = song;
		((MusicLibrary) context).setCurrentSong(temp);
		((MusicLibrary) context).sendSongToMain();
	
		
	}

	public Song getSong() {
		return temp;
	}	
}