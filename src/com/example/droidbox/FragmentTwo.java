package com.example.droidbox;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		
		setListAdapter(new SongListAdapter(inflater.getContext(),R.layout.song_row_item,song2));

        return test;
    }

	@Override
	public void onListItemClick (ListView l, View v, int position, long id) {
		
		Song song = (Song) l.getItemAtPosition(position);
		tableNickname = ((MusicLibrary)getActivity()).tableNickname;
		tableNumber = ((MusicLibrary)getActivity()).tableNumber;
		tablePasscode = ((MusicLibrary)getActivity()).tablePasscode;
		
		
		String songID = song.getID();
        String url = "http://9.12.10.1/db-wa/requestSong.php";
    	//songs.add(new Song(artist, title, album, ID));
    	 JSONParser jParser = new JSONParser();
    	 JSONObject json = new JSONObject();
         try {
        	((MusicLibrary)getActivity()).chosen = true;
        	
        	jParser.execute(url,"songID",songID,"t_num",tableNumber,"t_code",tablePasscode,"req_type","0");
        	
        	((MusicLibrary)getActivity()).sendToMain();
        	
 		} catch (Exception e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		} 
         
	}
}
