package com.example.droidbox;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
//@SuppressLint("NewApi")
public class Main extends Activity {
	private ListView listViewSong;
	private Context context;
	public QueuedList queue;
	public Song pickedSong;
	public Intent intent;
	public static String ID, ALBUM_NAME, ARTIST_NAME, SONG_NAME;
	public SongList songs = new SongList();
	public SongListAdapter adapter;
	public File file, myDir;
	public testFileWriter t1;
	//JSON
	public static int check = 1;
	int TIMEOUT_MILLISEC = 10000;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v("Return", "onCreate");
    	updateQueue();
//    	UpdateTask autoRefresh = new UpdateTask(this);  AutoRefresh mechanism, currently causes crash
//    	autoRefresh.execute();
        setContentView(R.layout.activity_main);
        context = this;
        listViewSong = (ListView)findViewById(R.id.song_list);
        adapter = new SongListAdapter(context, R.layout.song_row_item, songs);
        listViewSong.setAdapter(adapter);
    	//testFileWriter writeLibrary = new testFileWriter();
    	//writeLibrary.update(this,songs);
        Log.v("Return", "onCreate Finished");
        //allows a short click on a list item when set to TRUE
        listViewSong.setClickable(true);
        registerForContextMenu(listViewSong);
        //what do when setClickable == true and when an item is clicked. should pop up a menu
        listViewSong.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
        		Song song = (Song) listViewSong.getItemAtPosition(position);
        		CharSequence test = (CharSequence) song.getAlbum();
        		Toast.makeText(context, test, 15).show();
            }
            
        });
    
	}

    public void onResume() {
    	super.onResume();
    	Log.v("Return", "Resumed");
    	updateQueue();
    	adapter.setNotifyOnChange(false);
    	adapter.notifyDataSetChanged();
        
        
    	
    	
              	
    }
    public void goToLibrary(View view){
    	//where the JSON should start to get the music library
    	SongList temp = new SongList();
    	try {
    		temp = getLibrary();
    		testFileWriter writeLibrary = new testFileWriter();
        	writeLibrary.update(this,temp);	
    	} catch (Exception e) {
    		try {
    			temp = getLibrary();
    			testFileWriter writeLibrary = new testFileWriter();
            	writeLibrary.update(this,temp);	
    		}
    		catch (Exception m) {
    			try {
    				temp = getLibrary();
    				testFileWriter writeLibrary = new testFileWriter();
    	        	writeLibrary.update(this,temp);	
    			} catch(Exception n) {
    				Log.e("JSON Parser", "not connecting to data");

    			}
    			
    		}
    		
    		
        	
    	}
    	
    	
    	
    	//Toast.makeText(this, "jump", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(this,MusicLibrary.class);
    	//this.finish();
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                
                return true;
            case R.id.search:
            	onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    //creates a long press menu. so now you can long press and a menu pops up: will be to add songs to queue
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, view, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.actions, menu);
    }
    	
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch(item.getItemId()) {
    		case R.id.vote_up:
    			if(info.position == 0) {
    				return false;
    			} else {
    				songs.moveUp(songs, info.position);
    				//method call will be in the songlist class
    				adapter.notifyDataSetChanged();
    				listViewSong.requestLayout();
    				return true;
    				
    			}
    		default:
    			return super.onContextItemSelected(item);
    	}


    }

    
    public void buttonRefresh(View view){
    	Log.v("Return", "Refresh");
    	refresh();
    }
    
    public void refresh() {
    	updateQueue();
    	adapter.refreshSongs(songs);
    	adapter.notifyDataSetChanged();
    }
    	
    	
    
    
    public SongList getLibrary() 
    {
    	
    	String test = "nothing";
    	
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://192.168.1.103/db-wa/getLibrary.php";
    	 JSONObject json = new JSONObject();
        try {
			json = jParser.execute(url).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //JSONObject json = jParser.getTempHolder();
     
     
        return jParser.createList(json);
    }

    public SongList getQueue() 
    {
    	String test = "nothing";
    	
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://192.168.1.103/db-wa/getQueue.php";
    	JSONObject json = new JSONObject();
        try {
			json = jParser.execute(url).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
        return jParser.createList(json);
    }
   

    public void updateQueue() {
    	try {
    		songs = getQueue();
    		adapter.notifyDataSetChanged();
	
    	} catch (Exception e) {
    		try {
        		songs = getQueue();
        		adapter.notifyDataSetChanged();
    	
        	} catch (Exception e2) {
        		
        	}
    	}
  
    }
    

    public void onPause() {
    	super.onPause();
    	//testFileWriter writeLibrary = new testFileWriter();
    	//writeLibrary.update(this,songs);
    }
    
    public void onStop() {
    	super.onStop();
    	
    }
    
    public void onDestroy() {
    	super.onDestroy();
    	//file.delete();
    	//myDir.delete();
    }
    
    
    
}

