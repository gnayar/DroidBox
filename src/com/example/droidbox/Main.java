package com.example.droidbox;

import java.util.ArrayList;
import java.util.List;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
public class Main extends Activity {
	private ListView listViewSong;
	private Context ctx;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        
        //what I want to do but it won't print anything. I believe my scanner class is working
        //it works when I put use the console and put the file outside of this project (something with the package is screwing up)
        ScanIn scan = new ScanIn();
        ArrayList<Song> songs = scan.read();
        //but it isn't adding the songs I have in update.txt? it only adds what is in this for loop below
        
     //  ArrayList<Song> songs = new ArrayList<Song>();
        for(int i=0;i<9;i++){
       	songs.add(new Song("title","artist","album"));
        }
         
        listViewSong = (ListView)findViewById(R.id.song_list);
        listViewSong.setAdapter(new SongListAdapter(ctx, R.layout.song_row_item, songs));
    }
    
    public void goToLibrary(View view){
    	Intent intent = new Intent(this,MusicLibrary.class);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
