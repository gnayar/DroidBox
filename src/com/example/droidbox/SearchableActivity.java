package com.example.droidbox;

import org.json.JSONObject;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.view.MenuItem;

public class SearchableActivity extends Activity {
	private ListView listViewSongSearch;
	SongList results;
	private static SongList songs;
	Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_searchable);
        handleIntent(getIntent());
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
          String query = intent.getStringExtra(SearchManager.QUERY);
          Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
          if(appData != null) {
        	  songs = appData.getParcelable("library");
          }
          doMySearch(query,songs);
          listViewSongSearch = (ListView)findViewById(R.id.song_list);
          SongListAdapter adapter = new SongListAdapter(getBaseContext(), R.layout.song_row_item, results);
          listViewSongSearch.setAdapter(adapter);
          registerForContextMenu(listViewSongSearch);
          listViewSongSearch.setClickable(true);
          
          listViewSongSearch.setOnItemClickListener(new OnItemClickListener() {
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
          		Song song = (Song) listViewSongSearch.getItemAtPosition(position);
				SharedPreferences loginSettings = getSharedPreferences("LoginDetails",0);
		        String tableNumber = loginSettings.getString("tableNumber", "FAIL");
		        String tablePasscode = loginSettings.getString("tablePassword", "FAIL");
		        String tableNickname = loginSettings.getString("nickname", "FAIL");
		        
				
				String songID = song.getID();
		        String url = "http://9.12.10.1/db-wa/requestSong.php";
		    	//songs.add(new Song(artist, title, album, ID));
		    	 JSONParser jParser = new JSONParser();
		    	 JSONObject json = new JSONObject();
		         try {
		        	jParser.execute(url,"songID",songID,"t_num",tableNumber,"t_code",tablePasscode,"req_type","0");
		        	
		 		} catch (Exception e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		} 

		     	Intent intent = new Intent(getBaseContext(), Main.class);
		     	startActivity(intent);
              }
              
          });
        }
    }
    
    
   
    
    
    private void doMySearch(String query, SongList songs) {
		//Insert search logic
    	results = songs.searchByTitle(query);
    	Log.v("title search", "titles have been searched");
    	SongList temp = songs.searchByAlbum(query);
    	for(int i = 0; i<temp.size(); i++) {
    		results.add(temp.get(i));
    	}
    	SongList temp2 = songs.searchByArtist(query);
    	for(int i = 0; i < temp2.size(); i++) {
    		results.add(temp2.get(i));
    	}
    	if (results.get(0) == null) {
    		results.add(new Song("nothing","is","here", "12"));
    	}
	}
	

}
