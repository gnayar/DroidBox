package com.example.droidbox;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
	private Context context
	;
	public Song pickedSong;//For Clicking
	
	public static String ID, ALBUM_NAME, ARTIST_NAME, SONG_NAME;
	public SongList songs = new SongList();
	public SongListAdapter adapter;
	public File file, myDir;//To write music library
	public testFileWriter t1;
	//JSON
	public static int check = 1;
	private Handler m_Handler;
	private int m_interval = 10000;//Auto Refresh Interval in MilliSeconds
	boolean libInit = false;//
	
	//Inputted variables from the user, stored in the login activity and copied here for use. 
	public String tableNumber;
	public String tablePasscode;
	public String tableNickname;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //load login values
        SharedPreferences loginSettings = getSharedPreferences("LoginDetails",0);
        tableNumber = loginSettings.getString("tableNumber", "FAIL");
        tablePasscode = loginSettings.getString("tablePassword", "FAIL");
        tableNickname = loginSettings.getString("nickname", "FAIL");
        libInit = loginSettings.getBoolean("libInit", false);
        //create handler to do autoupdate
        m_Handler = new Handler();
        
        Log.v("Return", "onCreate");
        try{
	    	songs = getQueue();
	        }catch(Exception e1){
	        	try{
	        		songs = getQueue();
	        	}catch(Exception e2){
	        		Log.e("JSONParser", "Could not initialize queue");
	        	}
	        }
        
        //Setup Listview
        setContentView(R.layout.activity_main);
        context = this;
        listViewSong = (ListView)findViewById(R.id.song_list);
        adapter = new SongListAdapter(context, R.layout.song_row_item, songs);
        listViewSong.setAdapter(adapter);
        Log.v("Return", "onCreate Finished");
        
        //Setup Clicking System
        //allows a short click on a list item when set to TRUE
        listViewSong.setClickable(true);
        registerForContextMenu(listViewSong);
        //what do when setClickable == true and when an item is clicked. should pop up a menu
        listViewSong.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {              
        		Song song = (Song) listViewSong.getItemAtPosition(position);
        		//CharSequence test = (CharSequence) song.getAlbum();
        		//Toast.makeText(context, test, 15).show();
            }
            
        });
        //Start autoupdater
        startRepeatingTask();
	}
    
    Runnable m_statusChecker = new Runnable(){
    	//autoupdate timer
    	@Override
    	public void run(){
    		refresh();
    		m_Handler.postDelayed(m_statusChecker, m_interval);
    	}
    };
    
    void startRepeatingTask(){
    	m_statusChecker.run();
    }
    void stopRepeatingTask(){
    	m_Handler.removeCallbacks(m_statusChecker);
    }


    public void onResume() {
    	super.onResume();
    	Log.v("Return", "Resumed");
    	refresh();         	

    }
    
    public void goToLibrary(View view){
    	//where the JSON should start to get the music library
    	if(libInit == false){
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
	    	SharedPreferences loginSettings = getSharedPreferences("LoginDetails",0);
	    	SharedPreferences.Editor edit = loginSettings.edit();
	    	edit.putBoolean("libInit", true);//set to true so even if activity is restarted, status is maintained
	    	edit.commit();
	    	libInit = true;//wont redownload library now
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

    				String url = "http://"+ this.getString(R.string.ip_address)+"/db-wa/requestSong.php";
    		    	
    		    	 JSONParser jParser = new JSONParser();
    		    	 JSONObject json = new JSONObject();
    		         try {
    		        
    		        	String songID = songs.get(info.position).getID();
    		        	jParser.execute("4",url,"songID",songID,"t_num",tableNumber,"t_code",tablePasscode,"req_type","1");
    		        	
    		        	
    		 		} catch (Exception e1) {
    		 			// TODO Auto-generated catch block
    		 			e1.printStackTrace();
    		 		} 
    				updateQueue();
    				return true;
    				
    			}
    		default:
    			return super.onContextItemSelected(item);
    	}


    }

    
    public void refresh() {
    	//to refresh the Queue
    	try {
    		updateQueue();
	
    	} catch (Exception e) {
    		try {
    			updateQueue();
    	
        	} catch (Exception e2) {
        		Log.e("JSONParser", "Could not refresh queue");
        	}
    	}
    	adapter.refreshSongs(songs);
    	adapter.notifyDataSetChanged();
    }
    	
    	
    
    
    public SongList getLibrary() 
    {
    	//creating jParser object. 
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://"+this.getString(R.string.ip_address)+"/db-wa/getLibrary.php";
    	JSONObject json = new JSONObject();
        try {
			json = jParser.execute("0",url).get();
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
    	//Initializes the Queue
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://"+ this.getString(R.string.ip_address)+"/db-wa/getQueue.php";
    	JSONObject json = new JSONObject();
        try {
			json = jParser.execute("0",url).get();
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

    	JSONParser jParser = new JSONParser();
    	jParser.setContext(this);
        // getting JSON string from URL
    	String url = "http://"+ this.getString(R.string.ip_address)+"/db-wa/getQueue.php";;
        try {
			jParser.execute("1",url);//will update UI since 1
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

    }
}

