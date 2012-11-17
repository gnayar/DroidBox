package com.example.droidbox;

import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;



public class FragmentOne extends ListFragment
{
	
	Song temp;
	public String tableNumber;
	public String tablePasscode;
	public String tableNickname;
	
	
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
		
		Song song = (Song) l.getItemAtPosition(position);
		tableNickname = ((MusicLibrary)getActivity()).tableNickname;
		tableNumber = ((MusicLibrary)getActivity()).tableNumber;
		tablePasscode = ((MusicLibrary)getActivity()).tablePasscode;
		
		
		String songID = song.getID();
        String url = "http://"+ this.getString(R.string.ip_address)+"/db-wa/requestSong.php";
    	//songs.add(new Song(artist, title, album, ID));
    	 JSONParser jParser = new JSONParser();
    	 JSONObject json = new JSONObject();
         try {
        	((MusicLibrary)getActivity()).chosen = true;
        	
        	jParser.execute("0",url,"songID",songID,"t_num",tableNumber,"t_code",tablePasscode,"req_type","0");
        	
        	((MusicLibrary)getActivity()).sendToMain();
        	
 		} catch (Exception e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		} 
         
	}
}
