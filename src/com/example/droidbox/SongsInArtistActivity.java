package com.example.droidbox;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class SongsInArtistActivity extends Activity {
	
	private ListView listViewSongSearch;
	SongList artistList = new SongList();
	Context ctx;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_in_artist);
        
       Bundle b = getIntent().getExtras();
        
        int s = b.getInt("size");

        Song tempSong = new Song();
        for(int i = 0; i < s ; i++)
        {
    		tempSong = b.getParcelable("Message"+i);
    		artistList.add(tempSong);
        }
		
          
        listViewSongSearch = (ListView)findViewById(R.id.song_list2);
          SongListAdapter adapter = new SongListAdapter(getBaseContext(), R.layout.song_row_item, artistList);
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
		        String url = "http://"+ getString(R.string.ip_address)+"/db-wa/requestSong.php";
		    	//songs.add(new Song(artist, title, album, ID));
		    	 JSONParser jParser = new JSONParser();
		    	 JSONObject json = new JSONObject();
		         try {
		        	jParser.execute("3",url,"songID",songID,"t_num",tableNumber,"t_code",tablePasscode,"req_type","0");
		        	
		 		} catch (Exception e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		} 

		     	Intent intent = new Intent(getBaseContext(), Main.class);
		     	startActivity(intent);
              }
              
          });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_songs_in_album, menu);
        return true;
    }
}


