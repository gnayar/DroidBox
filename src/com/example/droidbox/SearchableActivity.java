package com.example.droidbox;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;

public class SearchableActivity extends Activity {
	private ListView listViewSongSearch;
	SongList songs = new SongList();
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
          doMySearch(query);
          listViewSongSearch = (ListView)findViewById(R.id.song_list);
          SongListAdapter adapter = new SongListAdapter(getBaseContext(), R.layout.song_row_item, songs);
          listViewSongSearch.setAdapter(adapter);
        }
    }
    
    private void doMySearch(String query) {
		//Insert search logic
    	songs.add(new Song("Title1","Artist","Album", "4"));
    	songs.add(new Song("Title2","Artist","Album", "4"));
    	songs.add(new Song("Title3","Artist","Album", "4"));
    	songs.add(new Song("Title4","Artist","Album", "4"));
    	songs.add(new Song("Title5","Artist","Album", "4"));

    	//add to SongList songs
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_searchable, menu);
        return true;
    }
}
