package com.example.droidbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
//@SuppressLint("NewApi")
public class Main extends Activity {
	private ListView listViewSong;
	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
		String path = context.getFilesDir().getAbsolutePath();//returns current directory.
//		File file = new File(path, "update.txt");
        //
		
		File file = this.getFileStreamPath("update.txt");
		if(!file.exists()){
			testFileWriter t1 = new testFileWriter(this);
		}
		File myDir = this.getFilesDir();
		ReadFile scan = new ReadFile();
        ArrayList<Song> songs = scan.read(myDir,"/update.txt",this);
        
        if(scan.isSynced() == false) {
        	
        	CharSequence updatedText = scan.getNewSyncCode();
        	
        	//Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
        	//toast.show();
        }
        else {
        	CharSequence updatedText = scan.getNewSyncCode();
        	//Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
        	//toast.show();
        }    
        
        
        

        
     //  ArrayList<Song> songs = new ArrayList<Song>();
        for(int i=0;i<9;i++){
       	songs.add(new Song("title","artist","album"));
        }
         
        listViewSong = (ListView)findViewById(R.id.song_list);
        listViewSong.setAdapter(new SongListAdapter(context, R.layout.song_row_item, songs));
        
        //allows the listview to have the popupmenu after a long press.
        registerForContextMenu(listViewSong);
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
    
    //creates a long press menu. so now you can long press and a menu pops up: will be to add songs to queue
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, view, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.actions, menu);
    }
    	
    	
    	
    	
    
}
