package com.example.droidbox;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MusicLibrary extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_library);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_music_library, menu);
        return true;
    }
}
