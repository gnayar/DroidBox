package com.example.droidbox;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
public class Main extends Activity {
	private ListView listViewSong;
	private Context ctx;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
		String path = ctx.getFilesDir().getAbsolutePath();//returns current directory.
//		File file = new File(path, "update.txt");
       ReadFile scan = new ReadFile();
        ArrayList<Song> songs = scan.read("update.txt",this);
        
        if(scan.isSynced() == false) {
        	
        	CharSequence updatedText = scan.getNewSyncCode();
        	
        	Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
        	toast.show();
        }
        else {
        	CharSequence updatedText = scan.getNewSyncCode();
        	Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
        	toast.show();
        }
        
        //not reading in correctly
        
        
        
        

        
     //  ArrayList<Song> songs = new ArrayList<Song>();
        for(int i=0;i<9;i++){
       	songs.add(new Song("title","artist","album"));
        }
         
        listViewSong = (ListView)findViewById(R.id.song_list);
        listViewSong.setAdapter(new SongListAdapter(ctx, R.layout.song_row_item, songs));
    }
    
    public void goToLibrary(View view){
    	Toast.makeText(this, "jump", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(this,MusicLibrary.class);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
