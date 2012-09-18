package com.example.droidbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			testFileWriter();
		}
		File myDir = this.getFilesDir();
		ReadFile scan = new ReadFile();
        ArrayList<Song> songs = scan.read(myDir,"/update.txt",this);
        
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
        listViewSong.setAdapter(new SongListAdapter(context, R.layout.song_row_item, songs));
    }
    
    public void testFileWriter(){
    		final String TESTSTRING = new String("1\nArtist: Artist1\nSong: Song1\nAlbum: Album1");
    		File myDir = new File(getFilesDir().getAbsolutePath());
    		try {
    			FileWriter write = new FileWriter(myDir + "/update.txt");
				write.write(TESTSTRING);
				write.close();
				Toast.makeText(this, "file created", Toast.LENGTH_SHORT).show();
				
    		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
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
