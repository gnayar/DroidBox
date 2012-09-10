package com.example.droidbox;

import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ListView;

public class SongList extends Activity {
	private ListView listViewSong;
	private Context ctx;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        List<Song> songs = new ArrayList<Song>();
        for(int i=0;i<9;i++){
        	songs.add(new Song("title","artist","album"));
        }
        listViewSong = (ListView)findViewById(R.id.song_list);
        listViewSong.setAdapter(new SongListAdapter(ctx, R.layout.song_row_item, songs));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
