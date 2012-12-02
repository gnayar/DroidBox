package com.example.droidbox;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;



public class FragmentThree extends ListFragment
{
	
	Song temp;
	public String tableNumber;
	public String tablePasscode;
	public String tableNickname;
	
	public static FragmentThree newInstance(String content){
		FragmentThree fragment = new FragmentThree();
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
       	
		SongList song3 = new SongList();
		for(int i = 0;i<songs.size();i++ ){
			song3.add(songs.get(i));
		}
		song3.sortByAlbum();
		ArrayList<String> albums = new ArrayList<String>();
		String current = "";
		for(int i = 0; i<song3.size();i++){
			String temp = song3.get(i).getAlbum();
			if(!temp.equals(current)){
				albums.add(temp);
				current = temp;
			}
		}
		
		setListAdapter((ListAdapter)new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,albums));
        return test;
    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id)
	{
		SongList albumsList = new SongList();
		
		String album =(String) l.getItemAtPosition(position);
		
		for(int i = 0; i < songs.size(); i++)
		{
			if( songs.get(i).getAlbum().equals(album) )
			{
				albumsList.add(songs.get(i));
			}
		}

		Intent intent = new Intent(getActivity(), SongsInAlbumActivity.class);
		int size = albumsList.size();
		intent.putExtra("size", size);
		
		for(int i = 0; i < albumsList.size(); i++)
		{
			String message = "Message"+i;
			intent.putExtra (message, albumsList.get(i) );
		}
		
		startActivity(intent);
	
	}
}
