package com.example.droidbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		
		setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,song3));

        return test;
    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		
		SongList albumsList = new SongList();
		songs.sortByAlbum();
		
		Song song = (Song) l.getItemAtPosition(position);
		String album = song.getAlbum();
		
		for(int i = 0; i < songs.size(); i++)
		{
			if( songs.get(i).getAlbum().equals(album) )
			{
				albumsList.add(songs.get(i));
			}
		}

		Intent intent = new Intent(getActivity(), SongsInAlbumActivity.class);
		int s = albumsList.size();
		intent.putExtra("size", s);
		
		for(int i = 0; i < albumsList.size(); i++)
		{
			String message = "Message"+i;
			intent.putExtra (message, albumsList.get(i) );
		}
		
		startActivity(intent);
	
	}
}
