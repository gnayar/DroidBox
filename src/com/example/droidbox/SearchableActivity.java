package com.example.droidbox;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

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
    	if (results.get(0) == null) {
    		results.add(new Song("nothing","is","here", "12"));
    	}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_searchable, menu);
        return true;
    }
	

}
