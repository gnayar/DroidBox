package com.example.droidbox;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;



public class FragmentThree extends ListFragment
{
	
	
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	       	View test = super.onCreateView(inflater, container, savedInstanceState);
			SongList songs = ((MusicLibrary)getActivity()).getSongsAlbumSort();

	       	//songs.sortByTitle();
			
		 	/** Creating an array adapter to store the list of countries **/
	        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,FRUITS);
	 
	        /** Setting the list adapter for the ListFragment */
	        setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,songs));

	        return test;
	    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		Context context = (MusicLibrary)getActivity();
		Toast.makeText(context, (CharSequence) "test", 15).show();

	}



	
}
