package com.example.droidbox;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class MusicAdapter extends FragmentStatePagerAdapter {
	SongList songs = new SongList();
	private static final String[] CONTENT = new String[] { "Title", "Artists", "Albums" };
    public MusicAdapter(FragmentManager fm) {
        super(fm);
    }
    
    public void recieveSong(SongList songs){
    	this.songs = songs;
    }
    public SongList giveSong(){
    	return songs;
    }
    
    @Override
    public Fragment getItem(int position) {
    	  switch(position % CONTENT.length){
          case 0:
              FragmentOne fragment = FragmentOne.newInstance("Title");
              fragment.songs = this.songs;
              return fragment;

          case 1:
        	  
        	  FragmentTwo fragment2 = FragmentTwo.newInstance("Artist");  
              fragment2.songs = this.songs;
              return fragment2;

          
          case 2:
        	
	          FragmentThree fragment3 = FragmentThree.newInstance("Album"); 
	          fragment3.songs = this.songs;
	          return fragment3;
    	  }
    	  return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
      return CONTENT.length;
    }
}