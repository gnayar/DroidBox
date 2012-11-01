package com.example.droidbox;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuInflater;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class MusicLibrary extends SherlockFragmentActivity
{

	Song current;
	SongList songs = new SongList();
	boolean chosen = false;
	Context context = this;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		
		chosen = false;
		this.setTheme(R.style.Theme_Sherlock_Light);
		//addSongs();//Once JSON is implemented just call a ReadFile here to read in the library. Current Situation is for Testing Only
		
     	File file = this.getFileStreamPath("update.txt");
     	if(!file.exists()){
     		testFileWriter t = new testFileWriter(context);
     	}
     	
		File myDir = new File(context.getFilesDir().getAbsolutePath());
		ReadFile scan = new ReadFile();
		songs = scan.read(myDir,"/update.txt",this);

		//addSongs();
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

         MusicAdapter mAdapter = new MusicAdapter(getSupportFragmentManager());
         mAdapter.recieveSong(songs);
        
         FragmentStatePagerAdapter adapter = (FragmentStatePagerAdapter) mAdapter;
        
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        pager.setCurrentItem(1);
        indicator.setCurrentItem(1);
        //Colors
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0xFFC0C0C0);
        indicator.setFooterColor(0xFFC0C0C0);
        indicator.setFooterLineHeight(1 * density); //1dp
        indicator.setFooterIndicatorHeight(3 * density); //3dp
        indicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
        indicator.setTextColor(0xAA000000);
        indicator.setSelectedColor(0xFF000000);
        indicator.setSelectedBold(true);
        
        
        

	}
	
	
    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
    	com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
    	   inflater.inflate(R.menu.activity_main, (com.actionbarsherlock.view.Menu) menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                
                return true;
            case R.id.search:
            	Bundle b = new Bundle();
            	b.putParcelable("library",songs);
            	Intent intent = new Intent(this, SearchableActivity.class);
            	intent.putExtras(b);
            	//startActivity(intent);
            	//startSearch(null,false,b,false);
            	onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	
	public void setCurrentSong(Song song) {
		current = song;
	}
	public Song getCurrentSong() {
		return current;
	}
	
	
	public void sendToMain() {
		  Intent intent = new Intent(this, Main.class);
		  startActivity(intent);
		  
	}
	
	public void onResume(){
		super.onResume();
		Log.v("Return", "Resumed Music Library");
		if(chosen){
			sendToMain();
		}
	}
	@Override
	public boolean onSearchRequested() {
    	Bundle b = new Bundle();
    	b.putParcelable("library",songs);
    	startSearch(null, false, b, false);
    	return true;
	}
	
}