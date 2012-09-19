package com.example.droidbox;

import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class MusicLibrary extends Activity {
	int currentScreen = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);
        MyPagerAdapter adapter = new MyPagerAdapter();
        ViewPager tabs = (ViewPager) findViewById(R.id.viewpager);
        tabs.setAdapter(adapter);
        tabs.setCurrentItem(currentScreen);
        Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show();
        //loadLibraries(currentScreen);
    }

    private void loadLibraries(int currentScreen) {
    	Toast.makeText(this, "lib loading", Toast.LENGTH_SHORT).show();
		File myDir = this.getFilesDir();
		ReadFile scan = new ReadFile();
        ArrayList<Song> songs = scan.read(myDir,"/update.txt",this);
        Toast.makeText(this, "song array created", Toast.LENGTH_SHORT).show();
        int resId = 0;
        switch (currentScreen) {
        case 0:
            resId = R.id.titleSort;
            break;
        case 1:
            resId = R.id.artistSort;
            break;
        case 2:
            resId = R.id.albumSort;
            break;
        case 3:
            resId = R.id.genreSort;
            break;
        }
    	//ListView listViewSong = (ListView)findViewById(resId);
        //listViewSong.setAdapter(new SongListAdapter(this, R.layout.song_row_item, songs));
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_music_library, menu);
        return true;
    }
    
}
