package com.example.droidbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockListFragment;
import com.example.droidbox.R;



public class FragmentTwo extends SherlockListFragment
{
	

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	        SongList songs = ((MusicLibrary)getActivity()).getSongsArtistSort();
	       	
	       	//songs.sortByArtist();
		 
		 	/** Creating an array adapter to store the list of countries **/
	        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,FRUITS);
	 
	        /** Setting the list adapter for the ListFragment */
	        setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,songs));
	 
	        return super.onCreateView(inflater, container, savedInstanceState);
	    }
    
	
}
