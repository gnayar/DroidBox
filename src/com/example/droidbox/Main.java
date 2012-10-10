package com.example.droidbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
	public SongList songs;
	public File file, myDir;
	public testFileWriter t1;
	//JSON
	public static int check = 1;
	int TIMEOUT_MILLISEC = 10000;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songs = new SongList();
        if(check == 1) {
        	start_loginActivity(listViewSong);
        }
        
        //PROBABLY SHOULDNT BE HERE
        //songs = getLibrary();
        
        
        setContentView(R.layout.activity_main);
        context = this;
//		File file = new File(path, "update.txt");
        //
        ID = "ID";
        
        
		
//     	file = this.getFileStreamPath("update.txt");
//     	if(!file.exists()){
//     		testFileWriter t = new testFileWriter(context);
//     	}
//     	
//		myDir = new File(context.getFilesDir().getAbsolutePath());
//		ReadFile scan = new ReadFile();
//		songs = scan.read(myDir,"/update.txt",this);
//		if(scan.isSynced() == false) {
//			CharSequence updatedText = scan.getNewSyncCode();
//		
//		//Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
//		//toast.show();
//		}
//		else {
//			CharSequence updatedText = scan.getNewSyncCode();
//		//Toast toast = Toast.makeText(this, updatedText, Toast.LENGTH_LONG);
//		//toast.show();
//		}    
// 		
        
        songs.add(new Song("not json", "not json", "sdfsd", 2131));

        listViewSong = (ListView)findViewById(R.id.song_list);
        SongListAdapter adapter = new SongListAdapter(context, R.layout.song_row_item, songs);
        listViewSong.setAdapter(adapter);
    	testFileWriter writeLibrary = new testFileWriter();
    	writeLibrary.update(this,songs);
        
        //allows a short click on a list item when set to TRUE
        listViewSong.setClickable(true);
        //what do when setClickable == true and when an item is clicked. should pop up a menu
        listViewSong.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
        		Song song = (Song) listViewSong.getItemAtPosition(position);
        		CharSequence test = (CharSequence) song.getAlbum();
        		Toast.makeText(context, test, 15).show();
            }
            
        });
    
	}
        //allows the listview to have the popupmenu after a long press.
        
    
    
    public void onResume() {
    	super.onResume();
        //get songs from user selections in the Music Library
        int songID = getIntent().getIntExtra(ID, 0);
        String title = getIntent().getStringExtra(SONG_NAME);
        String artist = getIntent().getStringExtra(ARTIST_NAME);
        String album = getIntent().getStringExtra(ALBUM_NAME);
        
        //double check if there is a new song to be added to the queue and if so add it to the queue (this should only happen after a user makes a selection)
        Song current = new Song(title, artist, album, songID);
        if(songID != 0) {
        	songs.add(current);
        }
        testFileWriter writeLibrary = new testFileWriter();
    	writeLibrary.update(this,songs);

        //TODO: cannot add more than one song! won't update the array properly.  not sure what's wrong
        
    	
    	
              	
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
    	file.delete();
    	myDir.delete();
    }
    
    public void goToLibrary(View view){
    	//where the JSON should start to get the music library
    	
    	
    	
    	
    	
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
    
    //creates a long press menu. so now you can long press and a menu pops up: will be to add songs to queue
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, view, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.actions, menu);
    }
    	
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      CharSequence title2 = item.getTitle();
      // item ID of add to queue 2130968621
     if(item.getItemId() == 2130968621) {
    	 Toast.makeText(context, (CharSequence) "works", 15).show();

    	 //definitely not the best to be calling the menu item id's but couldn't find something easier
    	  
      }

      return true;
    }
    
    //check for hte login screen and send intent if necessary
    public void start_loginActivity(View view) {
    	check = 2;
    	Intent intent = new Intent(this, LoginActivity.class);
    	startActivity(intent);
    }
    
    
    public SongList getLibrary() 
    {
    	
    	String test = "nothing";
    	
    	JSONParser jParser = new JSONParser();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	
    	//adding parameters to send through JSON
    	 params.add(new BasicNameValuePair("songID", "123" ));
    	
        // getting JSON string from URL
    	String url = "http://9.12.10.1/db-wa/getLibrary.php";
        JSONObject json = jParser.makeHttpRequest(url, "POST", params);
       
        try
        {
			test = json.getString("message");
		} 
        
        catch (Exception e) 
        {
			// TODO Auto-generated catch block
	        Log.e("json excep: ", e.toString());
		}
     
        Context context = getApplicationContext();
 
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "toast is: " + test, duration);
        toast.show();
        
        return jParser.createList(json);
    }



    	
    
}

