package com.example.droidbox;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;



public class FragmentTwo extends ListFragment
{
	
	Song temp;
	public String tableNumber;
	public String tablePasscode;
	public String tableNickname;
	
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

		ArrayList<String> artists = new ArrayList<String>();
		String current = "";
		for(int i = 0; i<song2.size();i++){
			String temp = song2.get(i).getArtist();
			if(!temp.equals(current)){
				artists.add(temp);
				current = temp;
			}
		}
		
		setListAdapter((ListAdapter)new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,artists));
        return test;
    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id)
	{
		SongList artistList = new SongList();
		
		String artist = (String) l.getItemAtPosition(position);
		
		for(int i = 0; i < songs.size(); i++)
		{
			if( songs.get(i).getArtist().equals(artist) )
			{
				artistList.add(songs.get(i));
			}
		}

		Intent intent = new Intent(getActivity(), SongsInArtistActivity.class);
		int size = artistList.size();
		intent.putExtra("size", size);
		
		for(int i = 0; i < artistList.size(); i++)
		{
			String message = "Message"+i;
			intent.putExtra (message, artistList.get(i) );
		}
		
		startActivity(intent);
         
	}
}
