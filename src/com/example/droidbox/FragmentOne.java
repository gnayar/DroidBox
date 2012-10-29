package com.example.droidbox;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



public class FragmentOne extends ListFragment
{
	
	Song temp;
	
	public static FragmentOne newInstance(String content){
		FragmentOne fragment = new FragmentOne();
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
       	
		SongList song1 = new SongList();
		for(int i = 0;i<songs.size();i++ ){
			song1.add(songs.get(i));
		}
		song1.sortByTitle();
		
		setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,song1));

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